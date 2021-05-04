package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class BDImplementationWorker implements InterfaceWorker{
	//Atributes 
	private Connection con;
	private PreparedStatement stmt;
	
	
	//Sentence SQL
	private final String GETser= "Select s.*,c.date_time_start,c.date_time_end from service s,carry_out c where s.code=? and s.code=c.code";
	private final String SEARCHser = "Select s.*,c.date_time_start,c.date_time_end from service s,carry_out c where s.cod_service=? and s.cod_service=c.cod_service ";
	private final String FINISHser= "Update service set finished=true where cod_service=?";

	
	//OpenConnection method
	private void openConnection() {
		String url = "jdbc:mysql://127.0.0.1:3306/cleaning_service?serverTimezone=Europe/Madrid&useSSL=false";
		try {
			con = DriverManager.getConnection(url,"root","abcd*1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//CloseConnection method
	private void closeConnection() {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//Method to list al services by using the worker id
	@Override
	public Map<String, Service> listServices(String codW) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Service se;
		Map <String,Service> servicios=null;
		this.openConnection();
		
		stmt=con.prepareStatement(GETser);
		
		stmt.setString(1, codW);
		
		rs=stmt.executeQuery();
		
		while(rs.next()) {
			se = new Service();
			se.setCodeService(rs.getString("cod_service"));
			se.setDate_time_end(rs.getTimestamp("date_time_end").toLocalDateTime());
			se.setDate_time_start(rs.getTimestamp("date_time_start").toLocalDateTime());
			se.setPrice(rs.getDouble("Price"));
			se.setWorkerId(codW);
			se.setFinished(rs.getBoolean("finished"));
			se.setDescription(rs.getString("description"));
			servicios= new TreeMap<>();
			servicios.put(se.getCodeService(), se);
		}
		
		this.closeConnection();
		return servicios;
	}

	//Method to search one service by the service code
	@Override
	public Service searchService(String code) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Service se = null;
		this.openConnection();
		
		stmt=con.prepareStatement(SEARCHser);
		
		stmt.setString(1, code);
		
		rs=stmt.executeQuery();
		
		if(rs.next()) {
			se = new Service();
			se.setCodeService(code);
			se.setDate_time_end(rs.getTimestamp("date_time_end").toLocalDateTime());
			se.setDate_time_start(rs.getTimestamp("date_time_start").toLocalDateTime());
			se.setPrice(rs.getDouble("Price"));
			se.setWorkerId(rs.getString("code"));
			se.setFinished(rs.getBoolean("finished"));
			se.setDescription(rs.getString("description"));
			
		}
		
		this.closeConnection();
		return se;
	}

	@Override
	public void markFinishedServices(Service se) throws Exception {
		// TODO Auto-generated method stub
		this.openConnection();
		
		stmt=con.prepareStatement(FINISHser);
		
		stmt.setString(1, se.getCodeService());
		
		
		stmt.executeUpdate();
		
		
		this.closeConnection();
	}

	//Method to list all finished services of one worker using their code
	
}
