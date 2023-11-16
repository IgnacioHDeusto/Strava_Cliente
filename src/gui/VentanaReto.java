package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import controller.RetoController;





public class VentanaReto extends JFrame{

	private int mouseRowPersonajes = -1;
	private JTable tablaRetos;
	private DefaultTableModel modeloDatosRetos;
	private JScrollPane scrollPaneRetos;
	private JButton crearReto ;
	private int filaRaton = -1;
	
	public VentanaReto(RetoController retoController) {
		setSize(400,300);
		
		initRetos();
		
		crearReto = new JButton("CREAR RETO");

        agregarRetoPrueba("Reto 1", "10 km");
        agregarRetoPrueba("Reto 2", "90 minutos");
        agregarRetoPrueba("Reto 3", "5 km");
        agregarRetoPrueba("Reto 4", " 30 minutos");
        agregarRetoPrueba("Reto 5", "15 km");
		
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
				mouseRowPersonajes = -1;
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
				mouseRowPersonajes = tablaRetos.rowAtPoint(e.getPoint());
				
				tablaRetos.repaint();				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
   
		
		this.scrollPaneRetos = new JScrollPane(this.tablaRetos);
		this.scrollPaneRetos.setBorder(new TitledBorder("RETOS"));	
		
		this.add(this.scrollPaneRetos);
		
		this.getContentPane().add(crearReto);
		this.setLayout(new BorderLayout());
	    this.add(this.scrollPaneRetos, BorderLayout.CENTER);
	    this.add(crearReto, BorderLayout.SOUTH);
	    // Establecer tamaño del botón
	    crearReto.setPreferredSize(new Dimension(100, 100));

	
		
	}
	
	public void initRetos() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraReto = new Vector<String>(Arrays.asList( "TITULO", "OBJETIVO"));

		this.modeloDatosRetos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraReto);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
						
			//Si el valor es de tipo Editorial: se renderiza con la imagen centrada

			

			
			//La filas pares e impares se renderizan de colores diferentes de la tabla de comics			
			if (table.equals(tablaRetos) || mouseRowPersonajes == -1 ) {
				if (row % 2 == 0) {
					result.setBackground(new Color(250, 249, 249));
				} else {
					result.setBackground(new Color(190, 227, 219));
				}
				
			if (isSelected ||  row == mouseRowPersonajes) {
					
					result.setBackground(new Color(200, 100, 50));	
				}
			
				
				//Se usan los colores por defecto de la tabla para las celdas de la tabla de personajes
			} 
			
			//Si la celda está seleccionada se renderiza con el color de selección por defecto
			
			
			
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
