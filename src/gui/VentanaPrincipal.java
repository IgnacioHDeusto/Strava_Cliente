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

public class VentanaPrincipal extends JFrame{
	protected JLabel perfil;
	protected JButton reto;
	protected JButton entrenamiento;
	protected JButton inicio;
	
	public VentanaPrincipal() {
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

				VentanaEntrenamiento ventanaEntrenamiento = new VentanaEntrenamiento();
		        ventanaEntrenamiento.setSize(700, 300);
		        ventanaEntrenamiento.setVisible(true);
				
			}
		});
		
		reto.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaReto ventanareto = new VentanaReto();
				ventanareto.setSize(400, 300);
				ventanareto.setVisible(true);				
			}
		});
		
	}

	public static void main(String[] args) {
		VentanaPrincipal v = new VentanaPrincipal();
        v.setSize(400, 300);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);

	}

}
