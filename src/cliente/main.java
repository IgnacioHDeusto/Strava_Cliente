package cliente;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
	 ServiceLocator serviceLocator = new ServiceLocator();
	 
	 
//	args[0] = RMIRegistry IP
//	args[1] = RMIRegistry Port
//	args[2] = Service Name
	serviceLocator.setService(args[0], args[1], args[2]);
	
	entrenamientoController = new EntrenamientoController(serviceLocator);
	loginController = new LoginController(serviceLocator);
	retoController = new RetoController(serviceLocator);
	
    long token = loginController.login("A", "A");
    List<String> deportes = new ArrayList<>();
    
    deportes.add("Ciclismo");
    deportes.add("Running");
    
    Calendar c = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    
    c.set(2022, 12, 9);
    c2.set(2024, 9, 1);
    
    Date date = c.getTime();
    Date date2 = c2.getTime();
    
    retoController.crearReto("Reto de colegas", 50, "Tiempo", date, date2, deportes, token);
    retoController.crearReto("Reto de tiempo corrido", 20, "Tiempo", date, date2, deportes, token);
    
    vl = new VentanaLogin(loginController);
    vl.setVisible(true);
	
    

	}
	
	
}
