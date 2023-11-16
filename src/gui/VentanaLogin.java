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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
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


        iniciarsesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UsuarioDTO usuario = new UsuarioDTO();

                if (controller.login(correo.getText(), contrasena.getPassword().toString())) {
                    usuario.setCorreo(correo.getText());
                    main.usuarioActivo = usuario;
                    main.vp.setVisible(true);
                }
            }
        });
        
        
        center.setLayout(new GridLayout(6, 2, 10, 10));
        center.setBorder(new LineBorder(Color.BLACK, 1));

        center.add(correo1);
        center.add(correo);
        center.add(contrasena1);
        center.add(contrasena);
        center.add(new JLabel()); 
        center.add(iniciarsesion);

        
        this.setSize(400, 300);
        setLocationRelativeTo(null);
    }
}
