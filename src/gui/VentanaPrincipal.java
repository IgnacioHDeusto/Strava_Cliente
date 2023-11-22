package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import cliente.main;
import controller.LoginController;
import controller.RetoController;
import data.dto.RetoDTO;

public class VentanaPrincipal extends JFrame{
	protected JLabel perfil;
	protected JButton reto;
	protected JButton entrenamiento;
	protected JButton inicio;
	protected JButton logout;
	protected JLabel strava;
	
	private int mouseRowEntrenamiento = -1;
	private int mouseRowReto = -1;
	private JTable tablaRetos;
	private DefaultTableModel modeloDatosRetos;
	private JScrollPane scrollPaneRetos;
	private JTable tablaEntrenamientos;
	private DefaultTableModel modeloDatosEntrenamientos;
	private JScrollPane scrollPaneEntrenamientos;
	
	public VentanaPrincipal(LoginController loginController) {
		setSize(900,700);
		setLocationRelativeTo(null);
		Container cp = this.getContentPane();
		initTablas();
	    loadDatos();
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		JPanel center = new JPanel();
		cp.add(north, BorderLayout.NORTH);
		cp.add(south, BorderLayout.SOUTH);
		cp.add(center, BorderLayout.CENTER);
		
		north.setBackground(new Color(255,255,255));
		
		strava = new JLabel();
		ImageIcon stravaIcon = new ImageIcon("resources/strava.png");
//		strava.setIcon(new ImageIcon("resources/strava.png"));
		Image stravaImage = stravaIcon.getImage().getScaledInstance(195, 40, Image.SCALE_SMOOTH);
		strava.setIcon(new ImageIcon(stravaImage));
		
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
		
		strava.setBorder(null);
		inicio.setBorder(null);
		reto.setBorder(null);
		entrenamiento.setBorder(null);
		
		logout = new JButton("LOG OUT");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logout.setFont(new Font("Arial", Font.BOLD, 14));
		logout.setBackground(new Color(255,128,0));
		logout.setForeground(Color.WHITE);
		
		scrollPaneRetos = new JScrollPane(tablaRetos);
//        scrollPaneRetos.setBorder(new TitledBorder("RETO"));
        
        scrollPaneEntrenamientos = new JScrollPane(tablaEntrenamientos);
//        scrollPaneEntrenamientos.setBorder(new TitledBorder("ENTRENAMIENTO"));
		
       
        
		south.setLayout(new GridLayout(1,3));
		north.setLayout(new GridLayout(1,2));
		center.setLayout(new GridLayout(2,1));
		center.add(scrollPaneRetos);
		center.add(scrollPaneEntrenamientos);
		north.add(strava);
		north.add(logout);
		south.add(inicio);
		south.add(reto);
		south.add(entrenamiento);
		


		
		
		
		entrenamiento.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.ve = new VentanaEntrenamiento(main.entrenamientoController);
        		
				main.vp.setVisible(false);
				main.ve.setVisible(true);	       

				
			}
		});
		
		reto.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				main.vret = new VentanaReto(main.retoController);
				
				main.vp.setVisible(false);
				main.vret.setVisible(true);
				
			}
		});
		
		
		logout.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loginController.logout();
				
		
				main.vp.setVisible(false);
				
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				loginController.logout();
				
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
		
		tablaEntrenamientos.setBackground(Color.white);
        tablaEntrenamientos.setForeground(Color.black);
        tablaEntrenamientos.setFont(new Font("Arial", Font.PLAIN, 14));
		tablaEntrenamientos.setRowHeight(30);
        tablaEntrenamientos.getTableHeader().setBackground(new Color(255,128,0));
        tablaEntrenamientos.getTableHeader().setForeground(Color.black);
        tablaEntrenamientos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		
		tablaRetos.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				mouseRowReto = -1;
				tablaRetos.repaint();
			}
		});

		tablaRetos.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseRowReto = tablaRetos.rowAtPoint(e.getPoint());
				
				tablaRetos.repaint();				
			}
			
		});
		
		tablaRetos.setBackground(Color.white);
		tablaRetos.setForeground(Color.black);
		tablaRetos.setFont(new Font("Arial", Font.PLAIN, 20));
		tablaRetos.setRowHeight(50);
		tablaRetos.getTableHeader().setBackground(new Color(255,128,0));
		tablaRetos.getTableHeader().setForeground(Color.black);
		tablaRetos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
	}
	
	public void initTablas() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraEntrenamiento = new Vector<String>(Arrays.asList( "TITULO", "DITANCIA","FECHA INICIO","FECHA FIN", "DURACION"));
		Vector<String> cabeceraReto = new Vector<String>(Arrays.asList( "TITULO", "OBJETIVO"));
		
		this.modeloDatosEntrenamientos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraEntrenamiento);
		this.tablaEntrenamientos = new JTable(this.modeloDatosEntrenamientos);
		this.modeloDatosRetos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraReto);
		this.tablaRetos = new JTable(this.modeloDatosRetos);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			
			result.setBackground(Color.white);
			
			result.setFont(new Font("Arial", Font.PLAIN, 14));
			result.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (isSelected ||  row == mouseRowEntrenamiento || row == mouseRowReto) {
				result.setBackground(new Color(255,128,0));	
			}
			
			result.setOpaque(true);

			return result;
			
		};
		
		//Se crea la tabla de personajes con el modelo de datos
		this.tablaRetos = new JTable(this.modeloDatosRetos) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tablaRetos.setDefaultRenderer(Object.class, cellRenderer);
		this.tablaEntrenamientos = new JTable(this.modeloDatosEntrenamientos) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tablaEntrenamientos.setDefaultRenderer(Object.class, cellRenderer);
		
		
	}
	private void loadDatos() {

		this.modeloDatosRetos.setRowCount(0);
		this.modeloDatosEntrenamientos.setRowCount(0);
		try {		
			
			List<RetoDTO> retos = main.retoController.getRetosActivos(main.token);
			if (retos.get(0).getTipoDeReto().equals("Distancia")) {
				modeloDatosRetos.addRow(new Object[] {retos.get(0).getNombre(), retos.get(0).getObjetivo() + " km"});
			} else if (retos.get(0).getTipoDeReto().equals("Tiempo")) {
				modeloDatosRetos.addRow(new Object[] {retos.get(0).getNombre(), retos.get(0).getObjetivo() + " minutos"});
			}
			
			try {
				main.entrenamientoController.getEntrenamientos(main.token).forEach(d->{
					modeloDatosEntrenamientos.addRow(new Object[] {d.getTitulo(), d.getDistancia() + " km", d.getFechaInicio(), d.getFechaFin(), d.getDuracion() + " minutos"});
				});
			} catch (RemoteException e) {
				e.printStackTrace();
			};
			
		} catch (RemoteException e) {
			e.printStackTrace();
		};
	}

}