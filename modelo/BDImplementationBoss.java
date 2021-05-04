package modelo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;
import java.sql.*;


public class BDImplementationBoss implements InterfaceBoss {

	private Connection con;
	private PreparedStatement stmt;
	

	// Metodo abrir conexion
	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/cleaning_service?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");
			// stmt = (PreparedStatement) con.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	// Metodo cerrar conexion
	private void closeConnection() throws SQLException {
		if (stmt != null)
			stmt.close();

		if (con != null)
			con.close();
	}

	@Override
	public Set<Worker> listWorkers() throws Exception {
		Set<Worker> workers = new HashSet<Worker>();
		Worker worker = null;
		ResultSet rs = null;

		openConnection();
		try {
			stmt = con.prepareStatement("select * from worker");
			rs = stmt.executeQuery();
			while (rs.next()) {
				worker = new Worker();
				worker.setId(rs.getString("code"));
				worker.setSalary(rs.getDouble("salary"));
				worker.setBossId(rs.getString("code_b"));
				workers.add(worker);
			}
		} catch (SQLException e) {

		} finally {
			closeConnection();
			rs.close();
		}
		return workers;
	}

	@Override
	public void addWorker(Worker worker) throws Exception {
		openConnection();
		try {
			stmt = con.prepareStatement("insert into worker values(?,?,?)");
			stmt.setString(1, worker.getId());
			stmt.setDouble(2, worker.getSalary());
			stmt.setString(3, worker.getBossId());
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			closeConnection();
		}

	}

	@Override
	public void deleteWorker(String id) throws Exception {
		openConnection();
		try {
			stmt = con.prepareStatement("delete * from worker where id=?");
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			closeConnection();
		}
	}

	@Override
	public void modifyWorker(Worker worker) throws Exception {
		openConnection();
		try {
			stmt = con.prepareStatement("update worker set code=?,salary=?,code_b=?");
			stmt.setString(1, worker.getId());
			stmt.setDouble(2, worker.getSalary());
			stmt.setString(3, worker.getBossId());
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			closeConnection();
		}

	}

	@Override
	public Worker searchWorker(String id) throws Exception {
		Worker worker = null;
		ResultSet rs = null;

		openConnection();
		try {
			stmt = con.prepareStatement("select * from worker where code=?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				worker = new Worker();
				worker.setId(rs.getString("code"));
				worker.setSalary(rs.getDouble("salary"));
				worker.setBossId(rs.getString("code_b"));
			}
		} catch (SQLException e) {

		} finally {
			closeConnection();
			rs.close();
		}
		return worker;
	}

	@Override
	public Set<Service> listServices() throws Exception {
		Set<Service> services = new HashSet<Service>();
		Service service = null;
		ResultSet rs = null;

		openConnection();
		try {
			stmt = con.prepareStatement(
					"select s.cod_service,s.code,s.price,date_time_start,date_time_end,s.finished from service s,carry_out c where s.cod_service=c.cod_service and s.code=c.code");
			rs = stmt.executeQuery();
			while (rs.next()) {
				service = new Service();
				service.setCodeService(rs.getString(1));
				service.setWorkerId(rs.getString(2));
				service.setPrice(rs.getDouble(3));
				service.setDate_time_start(rs.getDate(4).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				service.setDate_time_end(rs.getDate(5).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				service.setFinished(rs.getBoolean(6));
				services.add(service);
			}
		} catch (SQLException e) {

		} finally {
			closeConnection();
			rs.close();
		}
		return services;
	}

	@Override
	public Service addService(Service service) throws Exception {
		openConnection();

		try {
			CallableStatement cst = con.prepareCall("{CALL generate_service_primary_key(?,?,?,?,?)}");
			cst.setString(1, service.getWorkerId());
			cst.setDouble(2, service.getPrice());
			cst.setBoolean(3, service.isFinished());
			cst.setString(4, service.getDescription());
			cst.registerOutParameter(5, java.sql.Types.VARCHAR);
			cst.execute();
			service.setCodeService(cst.getString(5));

			Timestamp  dateStart = Timestamp.valueOf(service.getDate_time_start());
			Timestamp  dateEnd = Timestamp.valueOf(service.getDate_time_end());
			stmt = con.prepareStatement("insert into carry_out values(?,?,?,?)");
			stmt.setString(1, service.getCodeService());
			stmt.setString(2, service.getWorkerId());
			stmt.setTimestamp(3,dateStart);
			stmt.setTimestamp(4, dateEnd);
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			closeConnection();
		}
		return service;
	}

	@Override
	public void deleteService(String cod_service) throws Exception {
		openConnection();
		try {
			stmt = con.prepareStatement("delete * from service where cod_service=?");
			stmt.setString(1, cod_service);
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			closeConnection();
		}

	}

	@Override
	public void modifyService(Service service) throws Exception {
		openConnection();
		Timestamp  dateStart = Timestamp.valueOf(service.getDate_time_start());
		Timestamp  dateEnd = Timestamp.valueOf(service.getDate_time_end());
		try {
			stmt = con.prepareStatement(
					"update service s,carry_out c set cod_service=?,code=?,price=?,date_time_start=?,date_time_end=?,finished=? where s.cod_service=c.cod_service");
			stmt.setString(1, service.getCodeService());
			stmt.setString(2, service.getWorkerId());
			stmt.setDouble(3, service.getPrice());
			stmt.setTimestamp(4,dateStart);
			stmt.setTimestamp(5,dateEnd);
			stmt.setBoolean(6, service.isFinished());
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			closeConnection();
		}

	}

	@Override
	public int calculateSeniority(LocalDate d) throws Exception {
		int seniority = d.getYear() - LocalDate.now().getYear();
		return seniority;
	}

}