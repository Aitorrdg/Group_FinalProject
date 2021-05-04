package modelo;

import java.util.ArrayList;


public interface InterfaceAdministrator {
	public User searchUser(String id) throws Exception; //
	public User addUser(User u) throws Exception; 
	public void deleteUser(User u) throws Exception; //
	public ArrayList<User> listUser() throws Exception; //
	public User modifyUser(User u) throws Exception; 
}
