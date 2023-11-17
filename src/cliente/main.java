package cliente;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.EntrenamientoController;
import controller.LoginController;
import controller.RetoController;
import data.dto.EntrenamientoDTO;
import data.dto.RetoDTO;
import data.dto.UsuarioDTO;
import gui.VentanaEntrenamiento;
import gui.VentanaLogin;
import gui.VentanaPrincipal;
import gui.VentanaReto;
import remote.ServiceLocator;


public class main {
public static ServiceLocator serviceLocator = new ServiceLocator();
	
	public static LoginController loginController;
	public static RetoController retoController;
	public static EntrenamientoController entrenamientoController;
	
	
	public static VentanaLogin vl;
	public static VentanaPrincipal vp;
	public static VentanaEntrenamiento ve;
	public static VentanaReto vret;
	
	public static List<RetoDTO> retos;
	
	public static Map<UsuarioDTO, List<EntrenamientoDTO>> sesionesPorUsuario ;
	
	public static UsuarioDTO usuarioActivo;
	
	public static long token = 0001;
	
	public static Map<UsuarioDTO, Long> usuariosPorToken = new HashMap<>();
	
	public static void main(String[] args) throws RemoteException {
	 ServiceLocator servicelocator = new ServiceLocator();
	 
	 
//	args[0] = RMIRegistry IP
//	args[1] = RMIRegistry Port
//	args[2] = Service Name
	servicelocator.setService(args[0], args[1], args[2]);
	
	entrenamientoController = new EntrenamientoController(servicelocator);
	loginController = new LoginController(servicelocator);
	retoController = new RetoController(servicelocator);
	vl = new VentanaLogin(loginController);
	vp = new VentanaPrincipal(loginController);
	vl.setVisible(true);
	Calendar c = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    
    c.set(2023, 12, 9);
    c2.set(2023, 9, 1);
    
    Date date = c.getTime();
    Date date2 = c2.getTime();
    
    List<String> deportes = new ArrayList<>();
    
    deportes.add("Ciclismo");
    deportes.add("Running");
    
    retoController.crearReto("Reto Prueba", 5000, "Distancia", date2, date, deportes, 0001);
	
    entrenamientoController.crearEntrenamiento("Entrenamiento Prueba", "Ciclismo", 100, date2, date, 10, 0001);
    ve = new VentanaEntrenamiento(entrenamientoController);

	}
}
