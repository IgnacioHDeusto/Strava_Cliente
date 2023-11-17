package controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import data.dto.EntrenamientoDTO;
import remote.ServiceLocator;

public class EntrenamientoController {
	
	//Reference to the Service Locator
		private ServiceLocator serviceLocator;
		
		public EntrenamientoController(ServiceLocator serviceLocator) {
			this.serviceLocator = serviceLocator; 
		}
		
		public void crearEntrenamiento(String titulo, String deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion, long token) throws RemoteException {
			
			this.serviceLocator.getService().crearEntrenamiento(titulo, deporte, distancia, fecha_ini, fecha_fin, duracion, token);
			try {
				
			} catch (Exception e) {
				 System.out.println("\t#Error: crearEntrenamiento( " + titulo + ", " + deporte + ", "  + distancia + ", "  + fecha_ini + ", "  + fecha_fin + ", "  + duracion + ") ha fallado: " + e);
			}
		}
		
		public List<EntrenamientoDTO> getEntrenamientos(long token) throws RemoteException {
            try {
                return this.serviceLocator.getService().getEntrenamientos(token);
            } catch (Exception e) {
                System.out.println("# Error getting all entrenamientos: " + e);
                return null;
            }

        }


	
}
