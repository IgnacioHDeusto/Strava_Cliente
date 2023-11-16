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
	
	public static LoginController loginController = new LoginController(serviceLocator);
	public static RetoController retoController = new RetoController(serviceLocator);
	public static EntrenamientoController entrenamientoController = new EntrenamientoController(serviceLocator);
	
	
	public static VentanaLogin vl = new VentanaLogin(loginController);
	public static VentanaPrincipal vp = new VentanaPrincipal(loginController);
	public static VentanaEntrenamiento ve = new VentanaEntrenamiento(entrenamientoController);
	public static VentanaReto vret = new VentanaReto(retoController);
	
	public static List<RetoDTO> retos;
	
	public static Map<UsuarioDTO, List<EntrenamientoDTO>> sesionesPorUsuario ;
	
	public static UsuarioDTO usuarioActivo;
	
	public static long token = 0001;
	
	public static Map<UsuarioDTO, Long> usuariosPorToken = new HashMap<>();
	
	public static void main(String[] args) throws RemoteException {
	 ServiceLocator servicelocator = new ServiceLocator();
	 vl.setVisible(true);
	 
//	args[0] = RMIRegistry IP
//	args[1] = RMIRegistry Port
//	args[2] = Service Name
	servicelocator.setService(args[0], args[1], args[2]);
	
	EntrenamientoController entrenamientoController = new EntrenamientoController(servicelocator);
	LoginController loginController = new LoginController(servicelocator);
	RetoController retoController = new RetoController(servicelocator);
	
	Calendar c = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    
    c.set(2023, 12, 9);
    c2.set(2023, 9, 1);
    
    Date date = c.getTime();
    Date date2 = c2.getTime();
    
    List<String> deportes = new ArrayList<>();
    
    deportes.add("Ciclismo");
    deportes.add("Running");
	
	RetoDTO reto = new RetoDTO();
    reto.setNombre("Corre 40 kms");
    reto.setFechaFin(date);
    reto.setFechaInicio(date2);
    reto.setObjetivo(40);
    reto.setDeportes(deportes);
    reto.setTipoDeReto("Distancia");
    
    retoController.crearReto("Paja de hora y media", 5000, "Distancia", date2, date, deportes, token);
	
	
	}
}
