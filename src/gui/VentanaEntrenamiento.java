package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import controller.EntrenamientoController;



public class VentanaEntrenamiento extends JFrame{

	private JTable tablaEntrenamientos;
	private DefaultTableModel modeloDatosEntrenamientos;
	private JScrollPane scrollPaneEntrenamientos;
	private JButton crearEntrenamiento ;

	
	public VentanaEntrenamiento (EntrenamientoController entrenamientoController) {
		setSize(400,300);
		
		 initTables();
		 
		 crearEntrenamiento = new JButton("CREAR ENTRENAMIENTO");

		    // Agregar 5 entrenamientos de prueba
		    agregarEntrenamientoPrueba("Entrenamiento 1", "5 km", "2023-01-01", "2023-01-05", "1 hora");
		    agregarEntrenamientoPrueba("Entrenamiento 2", "10 km", "2023-02-01", "2023-02-10", "1.5 horas");
		    agregarEntrenamientoPrueba("Entrenamiento 3", "8 km", "2023-03-01", "2023-03-07", "1 hora");
		    agregarEntrenamientoPrueba("Entrenamiento 4", "15 km", "2023-04-01", "2023-04-15", "2 horas");
		    agregarEntrenamientoPrueba("Entrenamiento 5", "12 km", "2023-05-01", "2023-05-10", "1.5 horas");
		
		
		this.scrollPaneEntrenamientos = new JScrollPane(this.tablaEntrenamientos);
		this.scrollPaneEntrenamientos.setBorder(new TitledBorder("Entrenamientos"));	
		
		this.add(this.scrollPaneEntrenamientos);
		
		this.getContentPane().add(crearEntrenamiento);
		this.setLayout(new BorderLayout());
        this.add(this.scrollPaneEntrenamientos, BorderLayout.CENTER);
        this.add(crearEntrenamiento, BorderLayout.SOUTH);
        // Establecer tamaño del botón
        crearEntrenamiento.setPreferredSize(new Dimension(100, 100));

		crearEntrenamiento.addMouseListener(new MouseListener() {
			
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
				
				JTextField titulo = new JTextField(30);
				JSpinner distancia = new JSpinner();
				JDateChooser fecha_i = new JDateChooser();
				JDateChooser fecha_f = new JDateChooser();
				JSpinner duracion = new JSpinner();
				
				JComponent[] inputs = new JComponent[] {
						new JLabel("TITULO: "),
						titulo,
						new JLabel("DISTANCIA: "),
						distancia,
						new JLabel("FECHA INICIO: "),
						fecha_i,
						new JLabel("FECHA FIN: "),
						fecha_f,
						new JLabel("DURACION: "),
						duracion,
						
					};
				
				int result = JOptionPane.showConfirmDialog(null, inputs, 
						"CREAR ENTRENAMIENTO", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					System.out.println(titulo.getText());
							
					}
					
				}
			
		});

	}
	
	private void initTables() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraEntrenamiento = new Vector<String>(Arrays.asList( "TITULO", "DITANCIA","FECHA INICIO","FECHA FIN", "DURACION"));

		this.modeloDatosEntrenamientos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraEntrenamiento);
		//Se crea la tabla de personajes con el modelo de datos
		this.tablaEntrenamientos = new JTable(this.modeloDatosEntrenamientos);
	
}
	
	private void agregarEntrenamientoPrueba(String titulo, String distancia, String fechaInicio, String fechaFin, String duracion) {
	    Vector<Object> entrenamiento = new Vector<Object>();
	    entrenamiento.add(titulo);
	    entrenamiento.add(distancia);
	    entrenamiento.add(fechaInicio);
	    entrenamiento.add(fechaFin);
	    entrenamiento.add(duracion);
	    this.modeloDatosEntrenamientos.addRow(entrenamiento);
	}




}
