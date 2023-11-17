package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cliente.main;
import controller.LoginController;

public class VentanaPrincipal extends JFrame{
	protected JLabel perfil;
	protected JButton reto;
	protected JButton entrenamiento;
	protected JButton inicio;
	
	public VentanaPrincipal(LoginController loginController) {
		setSize(400,300);
		setLocationRelativeTo(null);
		Container cp = this.getContentPane();
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		cp.add(north, BorderLayout.NORTH);
		cp.add(south, BorderLayout.SOUTH);
		
		perfil = new JLabel("Inicio");
		north.setBackground(new Color(255,128,0));
		inicio = new JButton("");
		inicio.setIcon(new ImageIcon("resources/inicioS.png"));
		
		reto = new JButton("");
		reto.setIcon(new ImageIcon("resources/reto.png"));
		
		entrenamiento = new JButton("");
		entrenamiento.setIcon(new ImageIcon("resources/Entrenamiento.png"));
		
		cp.setBackground(new Color(255,255,255));
		
		inicio.setBackground(cp.getBackground());
		reto.setBackground(cp.getBackground());
		entrenamiento.setBackground(cp.getBackground());
		
		inicio.setBorder(null);
		reto.setBorder(null);
		entrenamiento.setBorder(null);
		
		south.setLayout(new GridLayout(1,3));
		north.add(perfil);
		south.add(inicio);
		south.add(reto);
		south.add(entrenamiento);
		
		
		entrenamiento.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.ve = new VentanaEntrenamiento(main.entrenamientoController);
        		
				main.ve.setVisible(true);	       

				
			}
		});
		
		reto.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.vret = new VentanaReto(main.retoController);
				main.vret.setVisible(true);
				
			}
		});
		
	}


}
