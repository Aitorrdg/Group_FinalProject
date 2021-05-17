package aplicacion;


import modelo.BDImplementationAdmin;
import modelo.BDImplementationBoss;
import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import vista.LoginWindow;


public class Principal {
	

	public static void main(String[] args) {
	
		InterfaceAdministrator data = new BDImplementationAdmin();
		InterfaceBoss dataBoss = new BDImplementationBoss();
		LoginWindow ventanaPrincipal = new LoginWindow(data,dataBoss);
		ventanaPrincipal.setVisible(true);
//		User u =new User();
//		u.setId("y7397195h");
//		try {
//			u=data.searchUser(u.getId());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(u.getId()+" "+u.getName());
	}

}
