package vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import modelo.BDImplementationBoss;
import modelo.Boss;
import modelo.InterfaceBoss;
import modelo.Service;
import modelo.Worker;

public class prueba {

	public static void main(String[] args) {
		InterfaceBoss datosBoss=new BDImplementationBoss();
		try {
			Set<Service> services=datosBoss.listServices();
			AddEditDeleteWindow aedW=new AddEditDeleteWindow(services,datosBoss);
			aedW.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
