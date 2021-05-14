package aplicacion;


import modelo.BDImplementationAdmin;
import modelo.InterfaceAdministrator;
import vista.LoginWindow;


public class Principal {

	public static void main(String[] args) {
	
		InterfaceAdministrator data = new BDImplementationAdmin();
		LoginWindow ventanaPrincipal = new LoginWindow(data);
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
