package modelo;

public class User {
	private String id;
	private String password;
	private String name;
	private String surname;
	private char type;
	//Constructor
	public User(String id, String password, String name, String surname, char type) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.type = type;
	}
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public User() {
		
	}

	//Getters and Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}

}
