package controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;


import data.dto.RetoAssembler;
import data.dto.RetoDTO;
import remote.ServiceLocator;

public class RetoController {

	private ServiceLocator serviceLocator;
	
	public RetoController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator; 
	}
	
	public List<RetoDTO> getRetos() throws RemoteException {
		try {
			return this.serviceLocator.getService().getRetos();
		} catch (Exception e) {
			System.out.println("# Error getting all retos: " + e);
			return null;
		}

	}
	
	public List<RetoDTO> getRetos(long token) throws RemoteException {
		try {
			return this.serviceLocator.getService().getRetos(token);
		} catch (Exception e) {
			System.out.println("# Error getting all retos: " + e);
			return null;
		}

	}

	public List<RetoDTO> getRetosActivos(long token) throws RemoteException {
		try {
			return this.serviceLocator.getService().getRetos(token);
		} catch (Exception e) {
			System.out.println("# Error getting all retos activos: " + e);
			return null;
		}
	}
	
	public boolean ApuntarseReto(long token, RetoDTO retoDTO) throws RemoteException {
		try {
			return this.serviceLocator.getService().ApuntarseReto(token, retoDTO);
		} catch (Exception e) {
			System.out.println("# Error al apuntarse a reto: " + e);
			return false;
		}
	}

	public float ComprobarReto(long token, RetoDTO retoDTO) throws RemoteException {
		try {
			return this.serviceLocator.getService().ComprobarReto(token, retoDTO);
		} catch (Exception e) {
			System.out.println("# Error al comprobar reto: " + e);
			return -1;
		}
	}

	public List<String> ConsultarEstadoRetos(long token) throws RemoteException {
		try {
			return this.serviceLocator.getService().ConsultarEstadoRetos(token);
		} catch (Exception e) {
			System.out.println("# Error al consultar retos: " + e);
			return null;
		}
	}
	
	public void crearReto(String nombre, Integer objetivo, String tipo, Date fecha_ini, Date fecha_fin, List<String> deportes, long token) throws RemoteException {
		this.serviceLocator.getService().crearReto(nombre, objetivo, tipo, fecha_ini, fecha_fin, deportes, token);
		try {
			
		} catch (Exception e) {
			System.out.println("\t#Error: crearReto( " + nombre + ", " + objetivo + ", "  + tipo + ", "  + fecha_ini + ", "  + fecha_fin + ", "  + deportes + ", " + token  + ") ha fallado: " + e);
		}

	}

}
