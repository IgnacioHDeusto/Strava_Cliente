package controller;

import java.rmi.RemoteException;

import remote.ServiceLocator;


public class LoginController {
	
	//Reference to the Service Locator
		private ServiceLocator serviceLocator;
		//This attribute stores the token when login success
		private long token = -1; //-1 = login has not been done or fails

		public LoginController(ServiceLocator serviceLocator) {
			this.serviceLocator = serviceLocator;
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
