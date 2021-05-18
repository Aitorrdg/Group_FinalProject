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

	final String InsertUser = "INSERT INTO userup (code, password, name, surname, type) VALUES (?, ?, ?, ?, ?)";
	final String InsertBoss = "INSERT INTO boss (code, seniority, speciality,joiningdate) VALUES (?, ?, ?,?)";
	final String GetUserList = "SELECT * FROM userup";
	final String DeleteUser = "DELETE FROM userup WHERE code = ?";
	final String SearchUser = "SELECT * FROM userup WHERE code = ?";
	final String SearchBoss = "SELECT name, surname,password ,type, seniority, speciality, joiningdate FROM boss b, userup u  where u.code=? and u.code=b.code ";
	final String ModifyUser = "UPDATE userup SET  name=?,surname=?,type=? where code=?";
	final String ModifyBoss = "UPDATE boss b , userup u  SET  password=?,name=?,surname=?,type=?, seniority=?, speciality=?,joiningdate=? where u.code=? and b.code=u.code";

	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/cleaning_service?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");
		} catch (SQLException e) {
			System.out.println("An error occurred during opening connection with database");
		}
	}

	private void closeConnection() throws SQLException {
		System.out.println("Connection Closed");
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
		System.out.println("---------------------");
	}

	@Override
	public User searchUser(String id) throws Exception {
		ResultSet rs = null;
		ResultSet rsB= null;
		User u = null;
		User uB= null;
		this.openConnection();

		stmt = con.prepareStatement(SearchUser);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			u = new User();
			u.setType(rs.getString("Type").charAt(0));
			if (u.getType()=='B') {
				stmt = con.prepareStatement(SearchBoss);
				stmt.setString(1, id);
				rsB = stmt.executeQuery();
				if(rsB.next()) {
					uB = new Boss();
					uB.setId(id);
					uB.setName(rsB.getString("name"));
					uB.setSurname(rsB.getString("surname"));
					uB.setPassword(rsB.getString("password"));
					uB.setType(rsB.getString("type").charAt(0));
					((Boss)uB).setJoiningDate(rsB.getTimestamp("joiningdate").toLocalDateTime());
					((Boss)uB).setSeniority(rsB.getInt("seniority"));
				
					((Boss)uB).setSpeciality(rsB.getString("speciality"));
					
					u= uB;
				}
				
			}else {
				
				u.setId(id);
				u.setName(rs.getString("name"));
				u.setSurname(rs.getString("surname"));
				u.setPassword(rs.getString("password"));
				u.setType(rs.getString("type").charAt(0));
			}
		}

		return u;

	}


	@Override
	public void addUser(User u) throws Exception {
		// TODO Auto-generated method stub
		this.openConnection();
		
		if(u.getType()=='B') {
			long milliSeconds = Timestamp.valueOf(((Boss) u).getJoiningDate()).getTime();
			Date joiningDate = new Date(milliSeconds);
			stmt = con.prepareStatement(InsertUser);
			stmtBoss = con.prepareStatement(InsertBoss);

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
			stmt = con.prepareStatement(InsertUser);
			

			stmt.setString(1, u.getId());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getName());
			stmt.setString(4, u.getSurname());
			stmt.setString(5, String.valueOf(u.getType()));
			stmt.executeUpdate();
		}
		
		

		
	}

	@Override
	public void deleteUser(User u) throws Exception {
		// TODO Auto-generated method stub
		this.openConnection();
		try {
			stmt = con.prepareStatement(DeleteUser);

			stmt.setString(1, u.getId());
			stmt.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("An error occured while modifying");
			e1.printStackTrace();
		} finally {
			try {
				this.closeConnection();

			} catch (SQLException e) {
				System.out.println("An error occured while closing Database");
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<User> listUser() throws Exception {
		ResultSet rs = null;
		User user;

		ArrayList<User> users = new ArrayList<User>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(GetUserList);
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
			System.out.println("SQL error");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("An error occured while closing Database");
				}
			}
		}
		return users;
	}

	@Override
	public void modifyUser(User u) throws Exception {
		
		this.openConnection();
		try {		
			if (u.getType() == 'B') {
				long milliSeconds = Timestamp.valueOf(((Boss) u).getJoiningDate()).getTime();
				Date joiningDate = new Date(milliSeconds);
				stmt = con.prepareStatement(ModifyBoss);
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
				stmt=con.prepareStatement(ModifyUser);
				stmt.setString(1, u.getName());
				stmt.setString(2, u.getSurname());
				stmt.setString(3, String.valueOf(u.getType()));
				stmt.setString(4, u.getId());
				if(stmt.executeUpdate()==1) {
				
				}
				
			}
			

		} catch (SQLException e1) {
			System.out.println("An error occured while updating");
			e1.printStackTrace();
		} finally {

			this.closeConnection();

		}

	
	}

}
