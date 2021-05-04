package modelo;

import java.util.Map;
import java.util.TreeMap;

public class Worker extends User{
	
	private double salary;
	private String bossId;
	private Map<String,Service>servicios=new TreeMap<String,Service>();
	
	//Constructor
	
	public Worker(String id, String password, String name, String surname, char type, double salary) {
		super(id, password, name, surname, type);
		this.salary = salary;
	}

	public Worker(String id, String password, String name, String surname, char type) {
		super(id, password, name, surname, type);
	}
	
	public Worker() {
	
	}
	

	//Getters and Setters
	
	public double getSalary() {
		return salary;
	}

	public Map<String, Service> getServicios() {
		return servicios;
	}

	public void setServicios(Map<String, Service> servicios) {
		this.servicios = servicios;
	}

	public String getBossId() {
		return bossId;
	}

	public void setBossId(String bossId) {
		this.bossId = bossId;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	
}
