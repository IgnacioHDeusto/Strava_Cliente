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
	
	public static void main(String[] args) {
	 ServiceLocator serviceLocator = new ServiceLocator();
	 
	 
//	args[0] = RMIRegistry IP
//	args[1] = RMIRegistry Port
//	args[2] = Service Name
	serviceLocator.setService(args[0], args[1], args[2]);
	
	entrenamientoController = new EntrenamientoController(serviceLocator);
	loginController = new LoginController(serviceLocator);
	retoController = new RetoController(serviceLocator);
	
	loginController.registro("A", "A", new Date(), "Meta", 0, 0, 0, 0, "A");
	
    vl = new VentanaLogin(loginController);
    vl.setVisible(true);
	
    

	}
	
	
}
