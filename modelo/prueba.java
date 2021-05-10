package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class prueba {

	public static void main(String[] args) {
		InterfaceBoss boss=new BDImplementationBoss();
		Service s=new Service();
		s.setDate_time_end(LocalDateTime.of(2020,5,19,14,59));
		s.setDate_time_start(LocalDateTime.of(2020,5,19,21,35));
		s.setFinished(false);
		s.setWorkerId("Y7397195H");
		s.setPrice(200);
		s.setDescription("Barrendero");
		try {
			s=boss.addService(s);
			System.out.println(s.getCodeService()+" "+s.getWorkerId()+" "+s.getPrice()+" "+s.getDate_time_start()+" "+s.getDate_time_end());
			System.out.println("Procedure correct done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
