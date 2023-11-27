package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import cliente.main;
import controller.EntrenamientoController;



public class VentanaEntrenamiento extends JFrame{

	private int mouseRowEntrenamiento = -1;
	private JTable tablaEntrenamientos;
	private DefaultTableModel modeloDatosEntrenamientos;
	private JScrollPane scrollPaneEntrenamientos;
	private JButton crearEntrenamiento;
	private int filaRaton = -1;
	protected JButton reto;
	protected JButton entrenamiento;
	protected JButton inicio;

	public VentanaEntrenamiento (EntrenamientoController entrenamientoController) {
		setTitle("Entrenamientos");
        setSize(900, 700);
        setLocationRelativeTo(null);

        initTables();
        loadDatos(entrenamientoController);
        
        Container cp = this.getContentPane();
		
		

		inicio = new JButton("");
		inicio.setIcon(new ImageIcon("resources/inicio.png"));
		
		reto = new JButton("");
		reto.setIcon(new ImageIcon("resources/reto.png"));
		
		entrenamiento = new JButton("");
		entrenamiento.setIcon(new ImageIcon("resources/EntrenamientoS.png"));
		
		cp.setBackground(new Color(255,255,255));
		
		inicio.setBackground(cp.getBackground());
		reto.setBackground(cp.getBackground());
		entrenamiento.setBackground(cp.getBackground());
		
		inicio.setBorder(null);
		reto.setBorder(null);
		entrenamiento.setBorder(null);
		
//		south.setLayout(new GridLayout(1,3));
//
//		south.add(inicio);
//		south.add(reto);
//		south.add(entrenamiento);

        crearEntrenamiento = new JButton("CREAR ENTRENAMIENTO");

        scrollPaneEntrenamientos = new JScrollPane(tablaEntrenamientos);
        scrollPaneEntrenamientos.setBorder(new TitledBorder("ENTRENAMIENTOS"));

        
        JPanel casa = new JPanel();
        casa.setLayout(new GridLayout(2,1));
        JPanel panelBoton = new JPanel();
        panelBoton.add(crearEntrenamiento);
        JPanel home = new JPanel();
        home.setLayout(new GridLayout(1,3));
        home.add(inicio);
        home.add(reto);
        home.add(entrenamiento);
        
        casa.add(crearEntrenamiento);
        casa.add(home);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPaneEntrenamientos, BorderLayout.CENTER);
        getContentPane().add(panelBoton, BorderLayout.SOUTH);
        getContentPane().add(casa, BorderLayout.SOUTH);
        

		crearEntrenamiento.addMouseListener(new MouseAdapter() {
			
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
				JComboBox<String> deportesCombo = new JComboBox<String>();
				deportesCombo.addItem("Ciclismo");
				deportesCombo.addItem("Running");
				JSpinner distancia = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
				JDateChooser fecha_i = new JDateChooser();
				JDateChooser fecha_f = new JDateChooser();
				JSpinner duracion = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
				
				JComponent[] inputs = new JComponent[] {
						new JLabel("TITULO: "),
						titulo,
						new JLabel("DEPORTES: "),
						deportesCombo,
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
						try {
							if (titulo.getText() != "" && Integer.parseInt(duracion.getValue().toString()) != 0) {
								entrenamientoController.crearEntrenamiento(titulo.getText(), deportesCombo.getSelectedItem().toString() ,Integer.parseInt(distancia.getValue().toString()) , fecha_i.getDate(), fecha_f.getDate(), Integer.parseInt(duracion.getValue().toString()), main.token);
								loadDatos(entrenamientoController);
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
		
		reto.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.vret = new VentanaReto(main.retoController);
				main.vret.setVisible(true);
				
				main.ve.setVisible(false);
			}
		});
		
		inicio.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.vp = new VentanaPrincipal(main.loginController);
        		
				main.vp.setVisible(true);	
				main.ve.setVisible(false);

				
			}
		});
		
		tablaEntrenamientos.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				mouseRowEntrenamiento = -1;
				tablaEntrenamientos.repaint();
			}
		});

		tablaEntrenamientos.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseRowEntrenamiento = tablaEntrenamientos.rowAtPoint(e.getPoint());
				
				tablaEntrenamientos.repaint();				
			}
			
		});
		

        getContentPane().setBackground(Color.white);



        tablaEntrenamientos.setBackground(Color.white);
        tablaEntrenamientos.setForeground(Color.black);
        tablaEntrenamientos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaEntrenamientos.setRowHeight(30);
        tablaEntrenamientos.getTableHeader().setBackground(new Color(255,128,0));
        tablaEntrenamientos.getTableHeader().setForeground(Color.black);
        tablaEntrenamientos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
      

 
        crearEntrenamiento.setBackground(new Color(255,128,0));
        crearEntrenamiento.setForeground(Color.white);
        crearEntrenamiento.setFont(new Font("Arial", Font.BOLD, 16));
        crearEntrenamiento.setPreferredSize(new Dimension(300, 40));

	}
	
	private void initTables() {
		Vector<String> cabeceraEntrenamiento = new Vector<String>(Arrays.asList( "TITULO", "DEPORTE", "DISTANCIA","FECHA INICIO","FECHA FIN", "DURACION"));

		this.modeloDatosEntrenamientos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraEntrenamiento);
		this.tablaEntrenamientos = new JTable(this.modeloDatosEntrenamientos);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			result.setBackground(Color.white);
						
			if (isSelected ||  row == mouseRowEntrenamiento) {
				
				result.setBackground(new Color(255,128,0));	
			}
			
			
			
			result.setFont(new Font("Arial", Font.PLAIN, 14));
			result.setHorizontalAlignment(SwingConstants.CENTER);
			
			result.setOpaque(true);
			
			return result;
		};
		
		this.tablaEntrenamientos = new JTable(this.modeloDatosEntrenamientos) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tablaEntrenamientos.setDefaultRenderer(Object.class, cellRenderer);
	
}
	
	private void loadDatos(EntrenamientoController entrenamientoController) {
		this.modeloDatosEntrenamientos.setRowCount(0);
		try {
			entrenamientoController.getEntrenamientos(main.token).forEach(d->{
				modeloDatosEntrenamientos.addRow(new Object[] {d.getTitulo(), d.getDeporte(), d.getDistancia() + " km", d.getFechaInicio(), d.getFechaFin(), d.getDuracion() + " minutos"});
			});
		} catch (RemoteException e) {
			e.printStackTrace();
		};
	}




}