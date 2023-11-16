package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
		setTitle("Gestion de Entrenamientos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initTables();
        
      


        crearEntrenamiento = new JButton("CREAR ENTRENAMIENTO");

        agregarEntrenamientoPrueba("Entrenamiento 1", "5 km", "2023-01-01", "2023-01-05", "1 hora");
        agregarEntrenamientoPrueba("Entrenamiento 2", "10 km", "2023-02-01", "2023-02-10", "1.5 horas");
        agregarEntrenamientoPrueba("Entrenamiento 3", "8 km", "2023-03-01", "2023-03-07", "1 hora");
        agregarEntrenamientoPrueba("Entrenamiento 4", "15 km", "2023-04-01", "2023-04-15", "2 horas");
        agregarEntrenamientoPrueba("Entrenamiento 5", "12 km", "2023-05-01", "2023-05-10", "1.5 horas");

        scrollPaneEntrenamientos = new JScrollPane(tablaEntrenamientos);
        scrollPaneEntrenamientos.setBorder(new TitledBorder("Entrenamientos"));

        JPanel panelBoton = new JPanel();
        panelBoton.add(crearEntrenamiento);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPaneEntrenamientos, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);

        crearEntrenamiento.setPreferredSize(new Dimension(300, 40));

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
		
		//Cambia el color de fondo de la ventana
        getContentPane().setBackground(Color.white);

        //Agrega una imagen o logotipo a la cabecera de la ventana
        JLabel logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(10, 10, 100, 100);
        getContentPane().add(logo);

        //Personaliza la apariencia de la tabla
        tablaEntrenamientos.setBackground(Color.white);
        tablaEntrenamientos.setForeground(Color.black);
        tablaEntrenamientos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaEntrenamientos.setRowHeight(30);
        tablaEntrenamientos.getTableHeader().setBackground(new Color(255,128,0));
        tablaEntrenamientos.getTableHeader().setForeground(Color.black);
        tablaEntrenamientos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tablaEntrenamientos.setDefaultRenderer(Object.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				label.setFont(new Font("Arial", Font.PLAIN, 14));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				return label;
			}
		});

 
        crearEntrenamiento.setBackground(new Color(255,128,0));
        crearEntrenamiento.setForeground(Color.white);
        crearEntrenamiento.setFont(new Font("Arial", Font.BOLD, 16));
        crearEntrenamiento.setPreferredSize(new Dimension(300, 40));


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPaneEntrenamientos, BorderLayout.CENTER);
        getContentPane().add(crearEntrenamiento, BorderLayout.SOUTH);

	}
	
	private void initTables() {
		Vector<String> cabeceraEntrenamiento = new Vector<String>(Arrays.asList( "TITULO", "DITANCIA","FECHA INICIO","FECHA FIN", "DURACION"));

		this.modeloDatosEntrenamientos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraEntrenamiento);
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
