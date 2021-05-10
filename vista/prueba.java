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
		Worker w=new Worker();
		w.setId("12345678F");
		w.setName("Rocio");
		w.setPassword("abcd*1234");
		w.setSurname("Clayre");
		w.setType('W');
		w.setBossId("22762072F");
		CrudWindow cw =new CrudWindow(datosBoss,w.getId(),w.getName(),w.getPassword(),w.getSurname(),w.getBossId());
		cw.setVisible(true);
		
	}

}
