package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Boss extends User{
	private String speciality;
	private LocalDateTime joiningDate;
	private int seniority;
	
	
	//Constructor
	public Boss(String id, String password, String name, String surname, char type, String speciality,
			LocalDateTime joiningDate,int seniority) {
		super(id, password, name, surname, type);
		this.speciality = speciality;
		this.joiningDate = joiningDate;
		this.seniority=seniority;
	}
	
	public Boss() {
		// TODO Auto-generated constructor stub
	}

	//Getters and Setters
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public LocalDateTime getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDateTime joiningDate) {
		this.joiningDate = joiningDate;
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	
}
