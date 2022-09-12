package Stack.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Stack.controllers.StackController;
import Stack.models.Pregunta;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una ventana que muestra las preguntas segun la opcion.
 * @author Jorge
 *
 */
public class MenuOpcionesView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private StackController controller;
	private DefaultTableModel tableModel;

	/**
	 * Crea una ventana que muestra las preguntas segun la opcion.
	 * @param frameMenuLogin JFrame ventan del menu login.
	 * @param framePadre JFrame ventana anterior.
	 * @param opcion int entero con la opcion.
	 * @param controller StackController controlador del Stack.
	 */
	public MenuOpcionesView(JFrame frameMenuLogin,JFrame framePadre,int opcion, StackController controller) {
		this.controller = controller;
		JFrame parentFrame = (JFrame) getRootPane().getParent();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Bienvenido "+controller.getActivo().get(0).getNombre());
		lblNewLabel.setBounds(25, 24, 174, 21);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backSelectFrameAction(framePadre);
			}
		});
		btnVolver.setBounds(25, 92, 71, 25);
		btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 128, 727, 227);
		
		
		if(opcion==1) {
			this.tableModel = rellenarModelAccept();
		}
		else {
			this.tableModel = rellenarModelVote();
		}
		table = new JTable(tableModel){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(238);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(167);
		table.getColumnModel().getColumn(4).setPreferredWidth(143);
		scrollPane.setViewportView(table);

		ArrayList<Pregunta> listaPreg = controller.getPreguntas();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectAskForAction(e,opcion,parentFrame,frameMenuLogin,framePadre,listaPreg);
			}
		});
		
		JLabel lblOpcion = new JLabel("Opcion de ");
		lblOpcion.setBounds(25, 63, 289, 18);
		if(opcion == 1) {
			lblOpcion.setText(lblOpcion.getText()+"aceptar una respuesta.");
		}
		else {
			lblOpcion.setText(lblOpcion.getText()+"hacer un voto.");
		}
		contentPane.setLayout(null);
		lblOpcion.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblOpcion);
		contentPane.add(lblNewLabel);
		contentPane.add(btnVolver);
		contentPane.add(scrollPane);
	}
	
	/**
	 * Funcion que rellena el modelo de tabla de las preguntas que se pueden aceptar.
	 * @return DefaultTableModel modelo de la tabla que se mostrara.
	 */
	public DefaultTableModel rellenarModelAccept() {
		String col [] = {"ID","Preguntas","Usuario","Respuestas","Fecha de publicacion"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		ArrayList<Pregunta> listaPreg = controller.getPreguntas();
		for(int i=0; i< listaPreg.size();i++) {
			if(controller.getActivo().get(0).getNombre().equals(listaPreg.get(i).getAutor()) && listaPreg.get(i).getEstado().get(0)==0) {
				int id = listaPreg.get(i).getId();
				String pregunta = listaPreg.get(i).getTitulo();
				String usuario = listaPreg.get(i).getAutor();
				int respuestas = listaPreg.get(i).getRespuestas().size();
				String fecha = listaPreg.get(i).getFecha().getFecha();
				
				if(respuestas==0) {
					Object[] objs = {id,pregunta,usuario,"Todavia no ha sido respondida",fecha};
					tableModel.addRow(objs);
				}
				else {
					Object[] objs = {id,pregunta,usuario,respuestas,fecha};
					tableModel.addRow(objs);
				}
			}
		}
		return tableModel;
	}
	
	/**
	 * Funcion que rellena el modelo de tabla de las preguntas que se pueden votar.
	 * @return DefaultTableModel modelo de la tabla que se mostrara.
	 */
	public DefaultTableModel rellenarModelVote() {
		String col [] = {"ID","Preguntas","Usuario","Respuestas","Fecha de publicacion"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		ArrayList<Pregunta> listaPreg = controller.getPreguntas();
		for(int i=0; i< listaPreg.size();i++) {
			int id = listaPreg.get(i).getId();
			String pregunta = listaPreg.get(i).getTitulo();
			String usuario = listaPreg.get(i).getAutor();
			int respuestas = listaPreg.get(i).getRespuestas().size();
			String fecha = listaPreg.get(i).getFecha().getFecha();
			
			if(respuestas==0) {
				Object[] objs = {id,pregunta,usuario,"Todavia no ha sido respondida",fecha};
				tableModel.addRow(objs);
			}
			else {
				Object[] objs = {id,pregunta,usuario,respuestas,fecha};
				tableModel.addRow(objs);
			}
		}
		return tableModel;
	}
	
	/**
	 * Funcion para volver a la ventana anterior.
	 * @param framePadre
	 */
	public void backSelectFrameAction(JFrame framePadre) {
		dispose();
		framePadre.setVisible(true);
	}
	
	/**
	 * Funcion para seleccionar una pregunta.
	 * @param e pregunta selecionada.
	 * @param opcion int entero con la opcion.
	 * @param parentFrame JFrame ventana de la clase actual.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param framePadre JFrame ventana anterior.
	 * @param listaPreg ArrayList<Pregunta> arreglo con las preguntas.
	 */
	public void selectAskForAction(MouseEvent e, int opcion,JFrame parentFrame,JFrame frameMenuLogin,JFrame framePadre,ArrayList<Pregunta> listaPreg){
		JTable table = (JTable) e.getSource();
		Point point = e.getPoint();
		int row = table.rowAtPoint(point);
		
		int index = 0;
		int idSelect = (int)table.getValueAt(row, 0);
		for(int i=0; i<listaPreg.size();i++) {
			if(listaPreg.get(i).getId()==idSelect) {
				index = i;
				break;
			}
		}
		if(e.getClickCount()==2 && table.getSelectedRow() != -1) {
			AskOpciones frame = new AskOpciones(opcion,parentFrame,frameMenuLogin,framePadre,controller,listaPreg.get(index));
			frame.setVisible(true);
			parentFrame.setVisible(false);
		}
	}
}
