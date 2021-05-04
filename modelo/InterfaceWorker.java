package modelo;

import java.util.Map;

public interface InterfaceWorker {
	public Map<String,Service> listServices(String code)throws Exception;
	public Service searchService(String code)throws Exception;
	public  void markFinishedServices(Service se)throws Exception;
}
