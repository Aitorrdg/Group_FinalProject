package modelo;

import java.time.LocalDate;
import java.util.Set;

public interface InterfaceBoss {
	public Set<Worker>listWorkers() throws Exception;
	public void addWorker(Worker w)throws Exception;
	public void deleteWorker(String id)throws Exception;
	public void modifyWorker(Worker w)throws Exception;
	public Worker searchWorker(String id)throws Exception;
	public Set<Service>listServices()throws Exception;
	public Service addService(Service s)throws Exception;
	public void deleteService(String cod_service)throws Exception;
	public void modifyService(Service s)throws Exception;
	public int calculateSeniority(LocalDate d)throws Exception;
}
