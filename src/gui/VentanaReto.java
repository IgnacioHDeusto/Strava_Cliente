package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import controller.RetoController;


public class VentanaReto extends JFrame{

	private int mouseRowRetos = -1;
	private JTable tablaRetos;
	private DefaultTableModel modeloDatosRetos;
	private JScrollPane scrollPaneRetos;
	private JButton crearReto ;
	private int filaRaton = -1;
	
	public VentanaReto(RetoController retoController) {
		setTitle("Retos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
		initRetos();
		
		crearReto = new JButton("CREAR RETO");

        agregarRetoPrueba("Reto 1", "10 km");
        agregarRetoPrueba("Reto 2", "90 minutos");
        agregarRetoPrueba("Reto 3", "5 km");
        agregarRetoPrueba("Reto 4", " 30 minutos");
        agregarRetoPrueba("Reto 5", "15 km");
        
        scrollPaneRetos = new JScrollPane(tablaRetos);
        scrollPaneRetos.setBorder(new TitledBorder("RETOS"));
		
        JPanel panelBoton = new JPanel();
        panelBoton.add(crearReto);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPaneRetos, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);
        
        getContentPane().setBackground(Color.white);
	   
        getContentPane().setBackground(Color.white);

        tablaRetos.setBackground(Color.white);
        tablaRetos.setForeground(Color.black);
	    tablaRetos.setFont(new Font("Arial", Font.PLAIN, 14));
	    tablaRetos.setRowHeight(30);
	    tablaRetos.getTableHeader().setBackground(new Color(255,128,0));
	    tablaRetos.getTableHeader().setForeground(Color.black);
	    tablaRetos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
	    
	    crearReto.setBackground(new Color(255,128,0));
	    crearReto.setForeground(Color.white);
	    crearReto.setFont(new Font("Arial", Font.BOLD, 16));
	    crearReto.setPreferredSize(new Dimension(300, 40));
	    
	    crearReto.addMouseListener(new MouseListener() {
			
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
				JTextField objetivo = new JTextField(30);
				
				
				JComponent[] inputs = new JComponent[] {
						new JLabel("TITULO: "),
						titulo,
						new JLabel("DISTANCIA: "),
						objetivo,
						
						
					};
				
				int result = JOptionPane.showConfirmDialog(null, inputs, 
						"CREAR RETO", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					System.out.println(titulo);
							
					}
					
				}
			
		});
        
        tablaRetos.addMouseListener(new MouseListener() {
			
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
				mouseRowRetos = -1;
				tablaRetos.repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				filaRaton = tablaRetos.rowAtPoint(e.getPoint());
				if (filaRaton != -1) {
					String tituloReto = (String) tablaRetos.getValueAt(filaRaton, 0);
		            int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres aceptar el reto '" + tituloReto + "'?", "Confirmación", JOptionPane.YES_NO_OPTION);
		            
		            if (opcion == JOptionPane.YES_OPTION) {
		                // Lógica para aceptar el reto
		                // Puedes agregar aquí lo que quieras hacer al aceptar el reto
		                System.out.println("Reto aceptado: " + tituloReto);
		            } else {
		                // Lógica para cancelar el reto
		                // Puedes agregar aquí lo que quieras hacer al cancelar el reto
		                System.out.println("Reto cancelado");
		            }
		        }    
					
								
			}
		});
        
        tablaRetos.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseRowRetos = tablaRetos.rowAtPoint(e.getPoint());
				
				tablaRetos.repaint();				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void initRetos() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraReto = new Vector<String>(Arrays.asList( "TITULO", "OBJETIVO"));

		this.modeloDatosRetos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraReto);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			result.setBackground(Color.white);
						
			if (isSelected ||  row == mouseRowRetos) {
				
				result.setBackground(new Color(255,128,0));	
			}
			
			result.setFont(new Font("Arial", Font.PLAIN, 14));
			result.setHorizontalAlignment(SwingConstants.CENTER);
			
			result.setOpaque(true);
			
			return result;
		};
		
		//Se crea la tabla de personajes con el modelo de datos
		this.tablaRetos = new JTable(this.modeloDatosRetos);
		this.tablaRetos.setDefaultRenderer(Object.class, cellRenderer);
	}
	
    private void agregarRetoPrueba(String titulo, String objetivo) {
        Vector<Object> reto = new Vector<Object>();
        reto.add(titulo);
        reto.add(objetivo);
        this.modeloDatosRetos.addRow(reto);
    }
	
    
}
