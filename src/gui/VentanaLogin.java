package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import cliente.main;
import controller.LoginController;
import data.dto.UsuarioDTO;

public class VentanaLogin extends JFrame{
	protected JLabel correo1;
	protected JTextField correo;
	protected JLabel contrasena1;
	protected JPasswordField contrasena;

	protected JButton iniciarsesion;
	
	public VentanaLogin(LoginController controller) {
		Container cp = this.getContentPane();
		JPanel center = new JPanel();
		cp.add(center, BorderLayout.CENTER);
		
		correo1 = new JLabel("Correo:");
        correo = new JTextField();
        contrasena1 = new JLabel("Contraseña:");
        contrasena = new JPasswordField();
       
        iniciarsesion = new JButton("Iniciar Sesion");
        
        iniciarsesion.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				UsuarioDTO usuario = new UsuarioDTO();
				
				if(controller.login(correo.getText(), contrasena.getPassword().toString())) {
					main.vp.setVisible(true);
				} 
					
				
				  
				
				
			}
		});
		
		center.setLayout(new GridLayout(15, 1));
		center.setAlignmentX(CENTER_ALIGNMENT);
		
		center.add(correo1);
		center.add(correo);
		center.add(contrasena1);
		center.add(contrasena);
		center.add(iniciarsesion);
		this.setSize(400,300);
	}
	
	

//    v.setSize(400, 300);
//    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    v.setVisible(true);
//

}
