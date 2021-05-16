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
	private ConnectionOpenClose conection = new ConnectionOpenClose();

	@Override
	public Set<Worker> listWorkers() throws Exception {
		Set<Worker> workers = new HashSet<Worker>();
		Worker worker = null;
		ResultSet rs = null;

		con=conection.openConnection();
		try {
			stmt = con.prepareStatement("select w.*,u.name,u.surname from worker w,user u where w.code=u.code");
			rs = stmt.executeQuery();
			while (rs.next()) {
				worker = new Worker();
				worker.setId(rs.getString("code"));
				worker.setSalary(rs.getDouble("salary"));
				worker.setBossId(rs.getString("code_b"));
				worker.setName(rs.getString("name"));
				worker.setSurname(rs.getString("surname"));
				workers.add(worker);
			}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
			rs.close();
		}
		return workers;
	}

	@Override
	public void addWorker(Worker worker) throws Exception {
		con=conection.openConnection();
		try {
			stmt = con.prepareStatement("insert into worker values(?,?,?)");
			stmt.setString(1, worker.getId());
			stmt.setDouble(2, worker.getSalary());
			stmt.setString(3, worker.getBossId());
			stmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			conection.closeConnection(stmt,con);
		}

	}

	@Override
	public void deleteWorker(String id) throws Exception {
		con=conection.openConnection();
		try {
			stmt = con.prepareStatement("delete w.*,u.* from worker w,user u where w.code=? and w.code=u.code");
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}
	}

	@Override
	public void modifyWorker(Worker worker) throws Exception {
		con=conection.openConnection();
		try {
			stmt = con.prepareStatement("update worker w,user u set salary=?,name=?,surname=? where w.code=? and w.code=u.code");
			stmt.setDouble(1, worker.getSalary());
			stmt.setString(2,worker.getName());
			stmt.setString(3, worker.getSurname());
			stmt.setString(4,worker.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}

	}

	@Override
	public Worker searchWorker(String id) throws Exception {
		Worker worker = null;
		ResultSet rs = null;

		con=conection.openConnection();
		try {
			stmt = con.prepareStatement("select w.*,u.name,u.surname from worker w,user u where w.code=? and w.code=u.code");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				worker = new Worker();
				worker.setId(rs.getString("code"));
				worker.setSalary(rs.getDouble("salary"));
				worker.setName(rs.getString("name"));
				worker.setSurname(rs.getString("surname"));
			}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
			rs.close();
		}
		return worker;
	}

	@Override
	public Set<Service> listServices() throws Exception {
		Set<Service> services = new HashSet<Service>();
		Service service = null;
		ResultSet rs = null;

		con=conection.openConnection();
		try {
			stmt = con.prepareStatement(
					"select s.*,date_time_start,date_time_end from service s,carry_out c where s.cod_service=c.cod_service and s.code=c.code");
			rs = stmt.executeQuery();
			while (rs.next()) {
				service = new Service();
				service.setCodeService(rs.getString("cod_service"));
				service.setWorkerId(rs.getString("code"));
				service.setPrice(rs.getDouble("price"));
				service.setDate_time_start(rs.getTimestamp("date_time_start").toLocalDateTime());
				service.setDate_time_end(rs.getTimestamp("date_time_end").toLocalDateTime());
				service.setFinished(rs.getBoolean("finished"));
				service.setDescription(rs.getString("description"));
				services.add(service);
			}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
			if(rs!=null)
				rs.close();
		}
		return services;
	}

	@Override
	public Service addService(Service service) throws Exception {
		con=conection.openConnection();

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
			conection.closeConnection(stmt,con);
		}
		return service;
	}

	@Override
	public void deleteService(String cod_service) throws Exception {
		con=conection.openConnection();
		try {
			stmt = con.prepareStatement("delete s.*,c.* from service s,carry_out c where s.cod_service=? and s.cod_service=c.cod_service and s.code=c.code");
			stmt.setString(1, cod_service);
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}

	}

	@Override
	public void modifyService(Service service) throws Exception {
		con=conection.openConnection();
		Timestamp  dateStart = Timestamp.valueOf(service.getDate_time_start());
		Timestamp  dateEnd = Timestamp.valueOf(service.getDate_time_end());
		try {
			stmt = con.prepareStatement(
					"update service s,carry_out c set s.code=?,price=?,date_time_start=?,date_time_end=?,finished=? where s.cod_service=c.cod_service and s.cod_service=?");
			stmt.setString(1, service.getWorkerId());
			stmt.setDouble(2, service.getPrice());
			stmt.setTimestamp(3,dateStart);
			stmt.setTimestamp(4,dateEnd);
			stmt.setBoolean(5, service.isFinished());
			stmt.setString(6, service.getCodeService());
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}

	}

	@Override
	public int calculateSeniority(LocalDate d) throws Exception {
		int seniority = d.getYear() - LocalDate.now().getYear();
		return seniority;
	}

	@Override
	public Service searchService(String code) throws Exception {
		Service service = null ;
		ResultSet rs = null;

		con=conection.openConnection();
		try {
			stmt = con.prepareStatement(
					"select s.*,date_time_start,date_time_end from service s,carry_out c where s.cod_service=? and s.cod_service=c.cod_service and s.code=c.code ");
			stmt.setString(1,code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				service = new Service();
				service.setCodeService(rs.getString("cod_service"));
				service.setWorkerId(rs.getString("code"));
				service.setPrice(rs.getDouble("price"));
				service.setFinished(rs.getBoolean("finished"));
				service.setDescription(rs.getString("description"));
				service.setDate_time_start(rs.getTimestamp("date_time_start").toLocalDateTime());
				service.setDate_time_end(rs.getTimestamp("date_time_end").toLocalDateTime());
			}
		} catch (SQLException e) {
			
		} finally {
			conection.closeConnection(stmt,con);
			rs.close();
		}
		return service;
	}

}
