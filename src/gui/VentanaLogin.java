package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

public class VentanaLogin extends JFrame{
	protected JLabel correo1;
	protected JTextField correo;
	protected JLabel nombre1;
	protected JTextField nombre;
	protected JLabel fecha_nacimiento1;
	protected JDateChooser fecha_nacimiento;
	protected JLabel peso1;
	protected JSpinner peso;
	protected JLabel altura1;
	protected JSpinner altura;
	protected JLabel frecuenciaCardiacaMax1;
	protected JSpinner frecuenciaCardiacaMax;
	protected JLabel frecuenciaCardiacaReposo1;
	protected JSpinner frecuenciaCardiacaReposo;
	protected JButton registrarse;
	
	public VentanaLogin() {
		Container cp = this.getContentPane();
		JPanel center = new JPanel();
		cp.add(center, BorderLayout.CENTER);
		
		correo1 = new JLabel("Correo:");
        correo = new JTextField();
        nombre1 = new JLabel("Nombre:");
        nombre = new JTextField();
        fecha_nacimiento1 = new JLabel("Fecha de nacimiento:");
        fecha_nacimiento = new JDateChooser();
        peso1 = new JLabel("Peso: (Opcional)");
        peso = new JSpinner();
        altura1 = new JLabel("Altura: (Opcional)");
        altura = new JSpinner();
        frecuenciaCardiacaMax1 = new JLabel("Frecuencia Cardiaca Máxima: (Opcional)");
        frecuenciaCardiacaMax = new JSpinner();
        frecuenciaCardiacaReposo1 = new JLabel("Frecuencia Cardiaca en Reposo: (Opcional)");
        frecuenciaCardiacaReposo = new JSpinner();
        registrarse = new JButton("Registrarse");
		
		center.setLayout(new GridLayout(15, 1));
		center.setAlignmentX(CENTER_ALIGNMENT);
		
		center.add(correo1);
		center.add(correo);
		center.add(nombre1);
		center.add(nombre);
		center.add(fecha_nacimiento1);
		center.add(fecha_nacimiento);
		center.add(peso1);
		center.add(peso);
		center.add(altura1);
		center.add(altura);
		center.add(frecuenciaCardiacaMax1);
		center.add(frecuenciaCardiacaMax);
		center.add(frecuenciaCardiacaReposo1);
		center.add(frecuenciaCardiacaReposo);
		center.add(registrarse);
	}
	
	
	public static void main(String[] args) {
	VentanaLogin v = new VentanaLogin();
    v.setSize(400, 300);
    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    v.setVisible(true);

	}

}
