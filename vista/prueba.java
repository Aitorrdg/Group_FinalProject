package vista;

import java.time.LocalDate;
import java.time.LocalDateTime;

import modelo.BDImplementationBoss;
import modelo.Boss;
import modelo.InterfaceBoss;
import modelo.Service;
import modelo.Worker;

public class prueba {

	public static void main(String[] args) {
		InterfaceBoss datosBoss=new BDImplementationBoss();
		Worker w;
		try {
			w = datosBoss.searchWorker("222222222");
			CrudWindow cw =new CrudWindow(w,datosBoss);
			cw.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
