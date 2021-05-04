package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


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
		Worker w=new Worker();
		w.setId("12345678J");
		w.setSalary(500);
		w.setBossId("22762072F");
		try {
			Set<Worker>workers=new HashSet<Worker>();
			boss.addWorker(w);
			workers=boss.listWorkers();
			Iterator<Worker> i=workers.iterator();
			while(i.hasNext()) {
				System.out.println(i.next());
			}
			boss.deleteWorker(w.getId());
			boss.deleteService(s.getCodeService());
			s.setFinished(true);
			boss.modifyService(s);
			w.setSalary(2000);
			boss.modifyWorker(w);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
