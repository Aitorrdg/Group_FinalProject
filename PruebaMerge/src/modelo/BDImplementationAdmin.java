package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BDImplementationAdmin implements InterfaceAdministrator {
	private Connection con;
	private PreparedStatement stmt;
	private PreparedStatement stmtBoss;
	private ConnectionOpenClose conection = new ConnectionOpenClose();

	final String insertUser = "INSERT INTO user (code, password, name, surname, type) VALUES (?, ?, ?, ?, ?)";
	final String insertWorker="INSERT INTO worker (code) values(?)";
	final String insertBoss = "INSERT INTO boss VALUES (?,?,?,?)";
	final String getUserList = "SELECT * FROM user";
	final String deleteUser = "DELETE FROM user where code = ?";
	final String searchUser = "SELECT * FROM user where code=?";
	final String searchWorker = "SELECT u.*,code_b,salary FROM user u,worker w WHERE u.code = ? and u.code=w.code";
	final String searchBoss = "SELECT name, surname,password ,type, seniority, speciality, joining_date FROM boss b, user u  where u.code=? and u.code=b.code ";
	final String modifyUser = "UPDATE user SET  name=?,surname=?,type=? where code=?";
	final String modifyBoss = "UPDATE boss b , user u  SET  password=?,name=?,surname=?,type=?, seniority=?, speciality=?,joining_date=? where u.code=? and b.code=u.code";

	@Override
	public User searchUser(String id) throws Exception {
		ResultSet rs = null;
		ResultSet rsB= null;
		User u = null;
		Boss uB= null;
		Worker uW=null;
		con=conection.openConnection();
		try {
		stmt = con.prepareStatement(searchUser);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			if (rs.getString("type").charAt(0)=='B') {
				stmt = con.prepareStatement(searchBoss);
				stmt.setString(1, id);
				rsB = stmt.executeQuery();
				if(rsB.next()) {
					uB = new Boss();
					uB.setId(id);
					uB.setName(rsB.getString("name"));
					uB.setSurname(rsB.getString("surname"));
					uB.setPassword(rsB.getString("password"));
					uB.setType(rsB.getString("type").charAt(0));
					uB.setJoiningDate(rsB.getTimestamp("joining_date").toLocalDateTime());
					uB.setSeniority(rsB.getInt("seniority"));
					uB.setSpeciality(rsB.getString("speciality"));
					
					u=uB;
				}
				
			}
			else if(rs.getString("type").charAt(0)=='W'){
				stmt = con.prepareStatement(searchWorker);
				stmt.setString(1, id);
				rs.close();
				rs = stmt.executeQuery();
				if(rs.next()) {
				uW=new Worker();
				uW.setId(id);
				uW.setName(rs.getString("name"));
				uW.setSurname(rs.getString("surname"));
				uW.setPassword(rs.getString("password"));
				uW.setType(rs.getString("type").charAt(0));
				uW.setBossId(rs.getString("code_b"));
				uW.setSalary(rs.getDouble("salary"));
				u=uW;
				}
			}
		}
		} catch (SQLException e) {
		} finally {
			conection.closeConnection(stmt,con);
			if(rs!=null)
				rs.close();
			if(rsB!=null)
				rsB.close();
			
		}

		return u;

	}


	@Override
	public void addUser(User u) throws Exception {
		// TODO Auto-generated method stub
		con=conection.openConnection();
		try {
		if(u.getType()=='B') {
			long milliSeconds = Timestamp.valueOf(((Boss) u).getJoiningDate()).getTime();
			Date joiningDate = new Date(milliSeconds);
			stmt = con.prepareStatement(insertUser);
			stmtBoss = con.prepareStatement(insertBoss);

			stmt.setString(1, u.getId());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getName());
			stmt.setString(4, u.getSurname());
			String tipo = String.valueOf(u.getType());
			stmt.setString(5, tipo);
			stmt.executeUpdate();
				stmtBoss.setString(1, u.getId());
				stmtBoss.setInt(2, ((Boss) u).getSeniority());
				stmtBoss.setString(3, ((Boss) u).getSpeciality());
				stmtBoss.setDate(4, joiningDate);
				stmtBoss.executeUpdate();
		}else if(u.getType()=='W') {
			stmt = con.prepareStatement(insertUser);
			

			stmt.setString(1, u.getId());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getName());
			stmt.setString(4, u.getSurname());
			stmt.setString(5, String.valueOf(u.getType()));
			stmt.executeUpdate();
			stmt=con.prepareStatement(insertWorker);
			stmt.setString(1,u.getId());
			stmt.executeUpdate();
		}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}
		

		
	}

	@Override
	public void deleteUser(User u) throws Exception {
		// TODO Auto-generated method stub
		con=conection.openConnection();
		try {
			stmt = con.prepareStatement(deleteUser);
			stmt.setString(1, u.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}
	}

	@Override
	public ArrayList<User> listUser() throws Exception {
		ResultSet rs = null;
		User user;

		ArrayList<User> users = new ArrayList<User>();
		con=conection.openConnection();
		try {
			stmt = con.prepareStatement(getUserList);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getString("code"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type").charAt(0));
				users.add(user);

			}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
			rs.close();
		}
		return users;
	}

	@Override
	public void modifyUser(User u) throws Exception {
		
		con=conection.openConnection();
		try {		
			if (u.getType() == 'B') {
				long milliSeconds = Timestamp.valueOf(((Boss) u).getJoiningDate()).getTime();
				Date joiningDate = new Date(milliSeconds);
				stmt = con.prepareStatement(modifyBoss);
				stmt.setString(1, u.getPassword());
				stmt.setString(2, u.getName());
				stmt.setString(3, u.getSurname());
				stmt.setString(4, String.valueOf(u.getType()));
				stmt.setInt(5, ((Boss) u).getSeniority());
				stmt.setString(6, ((Boss) u).getSpeciality());
				stmt.setDate(7, joiningDate);
				//stmt.setString(8, ((Boss)u).getJoiningDate().toString());
				stmt.setString(8, u.getId());
				if(stmt.executeUpdate()==1) {
				
				}
			}else if (u.getType()=='W') {
				stmt=con.prepareStatement(modifyUser);
				stmt.setString(1, u.getName());
				stmt.setString(2, u.getSurname());
				stmt.setString(3, String.valueOf(u.getType()));
				stmt.setString(4, u.getId());
				if(stmt.executeUpdate()==1) {
				
				}
				
			}
		} catch (SQLException e) {

		} finally {
			conection.closeConnection(stmt,con);
		}

	
	}

}
