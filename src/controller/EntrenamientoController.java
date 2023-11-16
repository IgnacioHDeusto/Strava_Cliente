package controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import data.domain.Deporte;
import remote.ServiceLocator;

public class EntrenamientoController {
	
	//Reference to the Service Locator
		private ServiceLocator serviceLocator;
		
		public EntrenamientoController(ServiceLocator serviceLocator) {
			this.serviceLocator = serviceLocator; 
		}
		
		public void crearEntrenamiento(String titulo, DeporteDTO deporte, Integer distancia, Date fecha_ini, Date fecha_fin, Integer duracion) {
			try {
				return this.serviceLocator.getService().crearEntrenamiento(titulo, deporte, distancia, fecha_ini, fecha_fin, duracion);
			} catch (Exception e) {
				 System.out.println("\t#Error: newEntrenamiento( " + titulo + ", " + deporte + ", "  + distancia + ", "  + fecha_ini + ", "  + fecha_fin + ", "  + duracion + ") ha fallado: " + e);
			}
		}


	
}