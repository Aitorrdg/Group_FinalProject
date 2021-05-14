package modelo;

import java.time.LocalDateTime;

public class Service {
	private String codeService;
	private double price;
	private String workerId;
	private String description;
	private LocalDateTime date_time_start;
	private LocalDateTime date_time_end;
	private boolean finished;
	private String descrip;
	
	//Constructor
	
	public Service(String codeService, double price, String workerId, LocalDateTime date_time_start,
			LocalDateTime date_time_end) {
		super();
		this.codeService = codeService;
		this.price = price;
		this.workerId = workerId;
		this.date_time_start = date_time_start;
		this.date_time_end = date_time_end;
	}
	public Service() {
		super();
	}
	
	//Getters and Setters
	
	
	public String getWorkerId() {
		return workerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public LocalDateTime getDate_time_start() {
		return date_time_start;
	}
	public void setDate_time_start(LocalDateTime date_time_start) {
		this.date_time_start = date_time_start;
	}
	public LocalDateTime getDate_time_end() {
		return date_time_end;
	}
	public void setDate_time_end(LocalDateTime date_time_end) {
		this.date_time_end = date_time_end;
	}
	public String getCodeService() {
		return codeService;
	}
	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	
}
