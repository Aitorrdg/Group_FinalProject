package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BDImplementationWorker implements InterfaceWorker {
	// Atributes
	private Connection con;
	private PreparedStatement stmt;
	private ConnectionOpenClose conection = new ConnectionOpenClose();

	// Sentence SQL
	private final String GETser = "Select s.*,c.date_time_start,c.date_time_end from service s,carry_out c where s.code=? and s.code=c.code and c.cod_service=s.cod_service";
	private final String SEARCHser = "Select s.*,c.date_time_start,c.date_time_end from service s,carry_out c where s.cod_service=? and s.cod_service=c.cod_service ";
	private final String SEARCHserUn = "Select s.*,c.date_time_start,c.date_time_end from service s,carry_out c where s.code =? and  s.cod_service=c.cod_service and s.finished=0";
	private final String FINISHser = "Update service set finished=true where cod_service=?";

	// Method to list all services by using the worker id
	@Override
	public ArrayList<Service> listServices(String codW) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Service se;
		ArrayList<Service> servicios=new ArrayList<>();
		con=conection.openConnection();
		try {
		stmt = con.prepareStatement(GETser);

		stmt.setString(1, codW);

		rs = stmt.executeQuery();

		while (rs.next()) {
			se = new Service();
			se.setCodeService(rs.getString("cod_service"));
			se.setDate_time_end(rs.getTimestamp("date_time_end").toLocalDateTime());
			se.setDate_time_start(rs.getTimestamp("date_time_start").toLocalDateTime());
			se.setPrice(rs.getDouble("Price"));
			se.setWorkerId(codW);
			se.setFinished(rs.getBoolean("finished"));
			se.setDescrip(rs.getString("description"));
			servicios.add(se);
		}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
			rs.close();
		}
		return servicios;
	}

	// Method to search one service by the service code
	@Override
	public Service searchService(String code) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Service se = null;
		con=conection.openConnection();
		try {
		stmt = con.prepareStatement(SEARCHser);

		stmt.setString(1, code);

		rs = stmt.executeQuery();

		if (rs.next()) {
			se = new Service();
			se.setCodeService(code);
			se.setDate_time_end(rs.getTimestamp("date_time_end").toLocalDateTime());
			se.setDate_time_start(rs.getTimestamp("date_time_start").toLocalDateTime());
			se.setPrice(rs.getDouble("Price"));
			se.setWorkerId(rs.getString("code"));
			se.setFinished(rs.getBoolean("finished"));
			se.setDescrip(rs.getString("description"));

		}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
			rs.close();
		}
		return se;
	}
	//Method to mark a finished service
	@Override
	public void markFinishedServices(String string) throws Exception {
		// TODO Auto-generated method stub
		con=conection.openConnection();
		try {
		stmt = con.prepareStatement(FINISHser);

		stmt.setString(1, string);

		stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}
	}

	@Override
	public ArrayList<Service> listUnCompletedServices(String codW) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Service se;
		ArrayList<Service> servicios=new ArrayList<>() ;
		con=conection.openConnection();
		try {
		stmt = con.prepareStatement(SEARCHserUn);

		stmt.setString(1, codW);

		rs = stmt.executeQuery();

		while (rs.next()) {
			se = new Service();
			se.setCodeService(rs.getString("cod_service"));
			se.setDate_time_end(rs.getTimestamp("date_time_end").toLocalDateTime());
			se.setDate_time_start(rs.getTimestamp("date_time_start").toLocalDateTime());
			se.setPrice(rs.getDouble("Price"));
			se.setWorkerId(codW);
			se.setFinished(rs.getBoolean("finished"));
			se.setDescrip(rs.getString("description"));
			servicios.add(se);
		}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
			rs.close();
		}
		return servicios;
	}


}
