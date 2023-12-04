package controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import data.dto.RetoDTO;
import remote.ServiceLocator;


public class LoginController {
	
	//Reference to the Service Locator
		private ServiceLocator serviceLocator;
		//This attribute stores the token when login success
		private long token = -1; //-1 = login has not been done or fails

		public LoginController(ServiceLocator serviceLocator) {
			this.serviceLocator = serviceLocator;
		}
		
		public void registro(String nombre, String correo, Date fecha_nacimiento, String tipoReg, Integer alt, Integer peso, Integer frecMax, Integer frecRep, String contrasena ) {
			try {
				this.serviceLocator.getService().registro(nombre, correo, fecha_nacimiento, tipoReg, alt, peso, frecMax, frecRep, contrasena);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Map<String, String> getUsuarios() throws RemoteException {
			try {
				return this.serviceLocator.getService().getUsuarios();
			} catch (Exception e) {
				System.out.println("# Error getting all retos: " + e);
				return null;
			}

		}
		
		public long login(String email, String password) {
				try {
					this.token = this.serviceLocator.getService().logIn(email, password);
					
					return token;
				} catch (RemoteException e) {
					System.out.println("# Error during login: " + e);
					this.token = -1;
					return token;
				}
			}
		
		public void logout() {
			try {
				this.serviceLocator.getService().logOut(this.token);
				this.token = -1;
			} catch (RemoteException e) {
				System.out.println("# Error during logout: " + e);
			}
		}

		public long getToken() {
			return token;
		}
	
}
