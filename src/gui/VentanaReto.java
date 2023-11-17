package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import cliente.main;
import controller.EntrenamientoController;
import controller.RetoController;
import data.dto.RetoDTO;


public class VentanaReto extends JFrame{

	private int mouseRowRetos = -1;
	private JTable tablaRetos;
	private DefaultTableModel modeloDatosRetos;
	private JScrollPane scrollPaneRetos;
	private JTable tablaRetosU;
	private DefaultTableModel modeloDatosRetosU;
	private JScrollPane scrollPaneRetosU;
	private JButton crearReto ;
	private int filaRaton = -1;
	
	public VentanaReto(RetoController retoController) {
		setTitle("Retos");
		setSize(900, 700);
        setLocationRelativeTo(null);
		
		initRetos();
		loadDatos(retoController);
		
		crearReto = new JButton("CREAR RETO");
		
		scrollPaneRetosU = new JScrollPane(tablaRetosU);
        scrollPaneRetosU.setBorder(new TitledBorder("TUS RETOS"));
		
        scrollPaneRetos = new JScrollPane(tablaRetos);
        scrollPaneRetos.setBorder(new TitledBorder("RETOS DE LA COMUNIDAD"));     
		
        JPanel panelBoton = new JPanel();
        panelBoton.add(crearReto);
        
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 1));
        center.add(scrollPaneRetosU);
        center.add(scrollPaneRetos);
        
        JButton inicio = new JButton("");
		inicio.setIcon(new ImageIcon("resources/inicio.png"));
		
		JButton reto = new JButton("");
		reto.setIcon(new ImageIcon("resources/retoS.png"));
		
		JButton entrenamiento = new JButton("");
		entrenamiento.setIcon(new ImageIcon("resources/Entrenamiento.png"));
		
        getContentPane().setBackground(new Color(255,255,255));
        
        inicio.setBackground(getContentPane().getBackground());
		reto.setBackground(getContentPane().getBackground());
		entrenamiento.setBackground(getContentPane().getBackground());
		
		inicio.setBorder(null);
		reto.setBorder(null);
		entrenamiento.setBorder(null);
        
		entrenamiento.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.ve = new VentanaEntrenamiento(main.entrenamientoController);
        		
				main.ve.setVisible(true);	       
				main.vret.setVisible(false);
			}
		});
		
		inicio.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.vp = new VentanaPrincipal(main.loginController);
        		
				main.vp.setVisible(true);
				main.vret.setVisible(false);
			}
		});
		
		JPanel south1 = new JPanel();
        south1.setLayout(new GridLayout(1,3));
        
        south1.add(inicio);
		south1.add(reto);
		south1.add(entrenamiento);
        
        JPanel south = new JPanel();
        south.setLayout(new GridLayout(2, 1));
        south.add(panelBoton);
        south.add(south1);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);
        
        getContentPane().setBackground(Color.white);
	   
        getContentPane().setBackground(Color.white);
        
        tablaRetosU.setBackground(Color.white);
        tablaRetosU.setForeground(Color.black);
	    tablaRetosU.setFont(new Font("Arial", Font.PLAIN, 14));
	    tablaRetosU.setRowHeight(30);
	    tablaRetosU.getTableHeader().setBackground(new Color(255,128,0));
	    tablaRetosU.getTableHeader().setForeground(Color.black);
	    tablaRetosU.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        
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
	    
	    crearReto.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JTextField titulo = new JTextField(30);
				JComboBox<String> tiposCombo = new JComboBox<String>();
				tiposCombo.addItem("Distancia");
				tiposCombo.addItem("Tiempo");
				JSpinner objetivo = new JSpinner(new SpinnerNumberModel(0, 0, 5000, 1));
				JDateChooser fecha_i = new JDateChooser();
				JDateChooser fecha_f = new JDateChooser();
				JRadioButton ciclismo = new JRadioButton("Ciclismo");
				JRadioButton running = new JRadioButton("Running");
			
				List<String> deportes = new ArrayList<>();
				
				
				JComponent[] inputs = new JComponent[] {
						new JLabel("TITULO: "),
						titulo,
						new JLabel("OBJETIVO: "),
						objetivo,
						new JLabel("TIPO DE RETO: "),
						tiposCombo,
						new JLabel("FECHA INICIO: "),
						fecha_i,
						new JLabel("FECHA FIN: "),
						fecha_f,
						new JLabel("DEPORTE(S): "),
						ciclismo,
						running
						
						
						
					};
				
				int result = JOptionPane.showConfirmDialog(null, inputs, 
						"CREAR RETO", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
						try {
							if (titulo.getText() != "" && Integer.parseInt(objetivo.getValue().toString()) != 0) {
								if (ciclismo.isSelected()) {
									deportes.add("Ciclismo");
								}
								if (running.isSelected()) {
									deportes.add("Running");
								}
								
								retoController.crearReto(titulo.getText(), Integer.parseInt(objetivo.getValue().toString()), tiposCombo.getSelectedItem().toString()  , fecha_i.getDate(), fecha_f.getDate(), deportes, main.token);
								loadDatos(retoController);
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				
				}
			
		});
        
        tablaRetos.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				mouseRowRetos = -1;
				tablaRetos.repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				filaRaton = tablaRetos.rowAtPoint(e.getPoint());
				
				List<RetoDTO> retos = new ArrayList<RetoDTO>();
				
				try {
					retos = retoController.getRetos();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				if (filaRaton != -1) {
					String tituloReto = (String) tablaRetos.getValueAt(filaRaton, 0);
		            int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres aceptar el reto '" + tituloReto + "'?", "Confirmación", JOptionPane.YES_NO_OPTION);
		            
		            if (opcion == JOptionPane.YES_OPTION) {
	            		retos.forEach(r -> {
	            			if (r.getNombre().equals(tituloReto)) {
								try {
									
									retoController.ApuntarseReto(main.token, r);
									loadDatos(retoController);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
	            		});
						
						
		                System.out.println("Reto aceptado: " + tituloReto);
		            } else {
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
		Vector<String> cabeceraRetoU = new Vector<String>(Arrays.asList( "TITULO", "OBJETIVO", "PROGRESO"));
		Vector<String> cabeceraReto = new Vector<String>(Arrays.asList( "TITULO", "OBJETIVO"));
		
		this.modeloDatosRetosU = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraRetoU);
		this.modeloDatosRetos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraReto);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			JProgressBar progreso = new JProgressBar();
			
			result.setBackground(Color.white);
						
			if (isSelected ||  row == mouseRowRetos) {
				
				result.setBackground(new Color(255,128,0));	
			}
			
			result.setFont(new Font("Arial", Font.PLAIN, 14));
			result.setHorizontalAlignment(SwingConstants.CENTER);
			
			result.setOpaque(true);

			if (column == 3) {
				return progreso;
			} else {
				return result;
			}
			
		};
		
		//Se crea la tabla de personajes con el modelo de datos
		this.tablaRetos = new JTable(this.modeloDatosRetos) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tablaRetos.setDefaultRenderer(Object.class, cellRenderer);
		this.tablaRetosU = new JTable(this.modeloDatosRetosU) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tablaRetosU.setDefaultRenderer(Object.class, cellRenderer);
		
		
	}
	
	private void loadDatos(RetoController retoController) {
		this.modeloDatosRetos.setRowCount(0);
		this.modeloDatosRetosU.setRowCount(0);
		try {
			List<RetoDTO> retos = retoController.getRetosActivos(main.token);
			List<String> nomR = new ArrayList<String>();
			retos.forEach(r -> nomR.add(r.getNombre()));
			retoController.getRetos().forEach(r->{
				if (!nomR.contains(r.getNombre())) {
					
					if (r.getTipoDeReto().equals("Distancia")) {
					modeloDatosRetos.addRow(new Object[] {r.getNombre(), r.getObjetivo() + " km"});
					} else if (r.getTipoDeReto().equals("Tiempo")) {
						modeloDatosRetos.addRow(new Object[] {r.getNombre(), r.getObjetivo() + " minutos"});
					}
				}
			});
			
			retoController.getRetos(main.token).forEach(r-> {
				JProgressBar progreso = new JProgressBar();
				Float f = null;
				try {
					f = retoController.ComprobarReto(main.token, r);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String s = "";
				if (f < 10) {
					s = f.toString().substring(0, 1);
				} else if (f < 100) {
					s = f.toString().substring(0, 2);
				} else {
					s = f.toString().substring(0, 3);
				}
				progreso.setValue(Integer.parseInt(s));
				if (r.getTipoDeReto().equals("Distancia")) {
					modeloDatosRetosU.addRow(new Object[] {r.getNombre(), r.getObjetivo() + " km", progreso.getValue() + "%"});
				} else if (r.getTipoDeReto().equals("Tiempo")) {
					modeloDatosRetosU.addRow(new Object[] {r.getNombre(), r.getObjetivo() + " minutos", progreso.getValue() + "%"});
				}
			});
		} catch (RemoteException e) {
			e.printStackTrace();
		};
	}
}
