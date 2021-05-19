package aplicacion;

import modelo.BDImplementationAdmin;
import modelo.BDImplementationBoss;
import modelo.BDImplementationWorker;
import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import modelo.InterfaceWorker;
import vista.LoginWindow;


public class Principal {
	

	public static void main(String[] args) {
	
		InterfaceAdministrator data = new BDImplementationAdmin();
		InterfaceBoss dataBoss = new BDImplementationBoss();
		InterfaceWorker dataWorker=new BDImplementationWorker();
		LoginWindow ventanaPrincipal = new LoginWindow(data,dataBoss,dataWorker);
		ventanaPrincipal.setVisible(true);
	}

}
