package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VentanaPrincipal extends JFrame{
	protected JLabel perfil;
	protected JButton reto;
	protected JButton entrenamiento;
	
	public VentanaPrincipal() {
		Container cp = this.getContentPane();
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		cp.add(north, BorderLayout.NORTH);
		cp.add(center, BorderLayout.CENTER);
		
		perfil = new JLabel("Bienvenido");
		reto = new JButton("RETO");
		entrenamiento = new JButton("ENTRENAMIENTO");
		
		center.setLayout(new GridLayout(1,2));
		north.add(perfil);
		center.add(reto);
		center.add(entrenamiento);
		
		
		entrenamiento.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

				VentanaEntrenamiento ventanaEntrenamiento = new VentanaEntrenamiento();
		        ventanaEntrenamiento.setSize(700, 300);
		        ventanaEntrenamiento.setVisible(true);
				
			}
		});;
		
		reto.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
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
