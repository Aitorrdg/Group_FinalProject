package modelo;

import java.util.ArrayList;

public interface InterfaceWorker {
	public ArrayList<Service> listServices(String code)throws Exception;
	public Service searchService(String code)throws Exception;
	public ArrayList <Service> listUnCompletedServices(String code)throws Exception;
	public void markFinishedServices(String cod)throws Exception;
}
