package vista;

import modelo.BDImplementationAdmin;
import modelo.BDImplementationBoss;
import modelo.BDImplementationWorker;
import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import modelo.InterfaceWorker;
import modelo.User;

public class prueba {

	public static void main(String[] args) {
		User u=new User();
		InterfaceAdministrator datosUser=new BDImplementationAdmin();
		InterfaceBoss datosBoss=new BDImplementationBoss();
		try {
			u=datosUser.searchUser("22762072F");
		} catch (Exception e) {
			
		}
		MainBoss b=new MainBoss(u, datosUser,datosBoss);
		b.setVisible(true);

	}

}
