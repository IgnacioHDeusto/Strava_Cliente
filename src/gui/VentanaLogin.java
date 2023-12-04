package gui;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.toedter.calendar.JDateChooser;

import cliente.main;
import controller.LoginController;
import data.dto.UsuarioAssembler;
import data.dto.UsuarioDTO;
import javax.swing.SwingConstants;

public class VentanaLogin extends JFrame {
	protected JLabel correo1;
    protected JTextField correo;
    protected JLabel contrasena1;
    protected JPasswordField contrasena;

    protected JButton iniciarsesion;
    protected JButton registrar;

    public VentanaLogin(LoginController controller) {
        // Set the look and feel to Metal for a more professional appearance
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Container cp = this.getContentPane();
        JPanel center = new JPanel();
        cp.add(center, BorderLayout.CENTER);

        
        center.setBackground(new Color(255, 255, 255));

        
        correo1 = new JLabel("Correo:");
        correo1.setHorizontalAlignment(SwingConstants.CENTER);
        correo1.setFont(new Font("Arial", Font.BOLD, 14));
        correo = new JTextField(20);
        correo.setFont(new Font("Arial", Font.PLAIN, 14));

        contrasena1 = new JLabel("Contrasena:");
        contrasena1.setHorizontalAlignment(SwingConstants.CENTER);
        contrasena1.setFont(new Font("Arial", Font.BOLD, 14));
        contrasena = new JPasswordField(20);
        contrasena.setFont(new Font("Arial", Font.PLAIN, 14));

        
        iniciarsesion = new JButton("Iniciar Sesion");
        iniciarsesion.setFont(new Font("Arial", Font.BOLD, 14));
        iniciarsesion.setBackground(new Color(255,128,0));
        iniciarsesion.setForeground(Color.WHITE);
        
        registrar = new JButton("Registrar");
        registrar.setFont(new Font("Arial", Font.BOLD, 14));
        registrar.setBackground(new Color(255,128,0));
        registrar.setForeground(Color.WHITE);


        iniciarsesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Map<String, String> mapU = new HashMap<>();
            	try {
					mapU = main.loginController.getUsuarios();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	if (mapU.get(correo.getText()) != null) {
                UsuarioDTO usuario = new UsuarioDTO();
                
                char[] passwordChars = contrasena.getPassword();
                String password = new String(passwordChars);
                main.token = controller.login(correo.getText(), password);
                if (main.token != -1) {
                    usuario.setCorreo(correo.getText());
                    main.usuarioActivo = usuario;
                    
						main.vp = new VentanaPrincipal(main.loginController);
						main.vp.setVisible(true);
						main.vl.setVisible(false);
					}
                	
            		
                }
            }
        });
        
        registrar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField correo = new JTextField(30);
				JTextField nombre = new JTextField(30);
				JTextField contrasena = new JTextField(30);
				JSpinner altura = new JSpinner(new SpinnerNumberModel(0, 0, 250, 1));
				JDateChooser fecha = new JDateChooser();
				JSpinner peso = new JSpinner(new SpinnerNumberModel(0, 0, 250, 1));
				JSpinner max = new JSpinner(new SpinnerNumberModel(0, 0, 250, 1));
				JSpinner rep = new JSpinner(new SpinnerNumberModel(0, 0, 250, 1));
				JPanel reg = new JPanel();
				reg.setLayout(new GridLayout(1,3));
				
				JComboBox<String> treg = new JComboBox<String>();
				treg.addItem("Meta");
				treg.addItem("Google");
				reg.add(new JLabel());
				reg.add(treg);
				reg.add(new JLabel());
				
				treg.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
					
					JLabel result3 = new JLabel();
					
					result3.setIcon(new ImageIcon(String.format("resources/%s.png", value)));
					result3.setText("");
					
					return result3;
				});
				
				JComponent[] inputs = new JComponent[] {
						new JLabel("CORREO: "),
						correo,
						new JLabel("NOMBRE: "),
						nombre,
						new JLabel("CONTRASENA: "),
						contrasena,
						new JLabel("FECHA NACIMIENTO: "),
						fecha,						
						new JLabel("ALTURA cm (OPC): "),
						altura,
						new JLabel("PESO kg (OPC): "),
						peso,
						new JLabel("FRECUENCIA CARDIACA MAX (OPC): "),
						max,
						new JLabel("FRECUENCIA CARDIACA EN REPOSO (OPC): "),
						rep,
						new JLabel("REGISTRARSE CON "),
						reg
					};
				
				int result = JOptionPane.showConfirmDialog(null, inputs, 
						"REGISTRO", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (correo.getText() != "" && nombre.getText() != "" && contrasena.getText() != "") {
						if (Integer.parseInt(rep.getValue().toString()) != 0 || Integer.parseInt(max.getValue().toString()) != 0
								|| Integer.parseInt(altura.getValue().toString()) != 0 || Integer.parseInt(peso.getValue().toString()) != 0) {
							controller.registro(nombre.getText(), correo.getText(), fecha.getDate(), treg.getSelectedItem().toString(), Integer.parseInt(altura.toString()), Integer.parseInt(peso.toString()), Integer.parseInt(max.toString()), Integer.parseInt(rep.toString()),  contrasena.getText());
						} else {
							controller.registro(nombre.getText(), correo.getText(), fecha.getDate(),  treg.getSelectedItem().toString(), 0, 0, 0, 0,  contrasena.getText());
						}
					}
				}
					
				
			}
		});
        
        center.setLayout(new GridLayout(7, 2, 10, 10));
        center.setBorder(new LineBorder(Color.BLACK, 1));

        center.add(correo1);
        center.add(correo);
        center.add(contrasena1);
        center.add(contrasena);
        center.add(new JLabel()); 
        center.add(iniciarsesion);
        center.add(registrar);

        
        this.setSize(400, 300);
        setLocationRelativeTo(null);
    }
}
