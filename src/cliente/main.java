package cliente;

import controller.EntrenamientoController;
import controller.LoginController;
import controller.RetoController;
import remote.ServiceLocator;


public class main {
	
	public static void main(String[] args) {
	 ServiceLocator servicelocator = new ServiceLocator();
	 
//	args[0] = RMIRegistry IP
//	args[1] = RMIRegistry Port
//	args[2] = Service Name
	servicelocator.setService(args[0], args[1], args[2]);
	
	EntrenamientoController entrenamientoController = new EntrenamientoController(servicelocator);
	LoginController loginController = new LoginController(servicelocator);
	RetoController retoController = new RetoController(servicelocator);
	}
}
