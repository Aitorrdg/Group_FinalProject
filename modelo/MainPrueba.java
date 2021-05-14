package modelo;

import vista.WindowWorker;

public class MainPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Worker wo = new Worker("16094798L", "abcd*1234", "Alain", "Lozano", "1234", 2000, 'W');
		BDImplementationWorker data = new BDImplementationWorker();
		WindowWorker ventana= new WindowWorker(wo, data);
		ventana.setVisible(true);
		

	}

}
