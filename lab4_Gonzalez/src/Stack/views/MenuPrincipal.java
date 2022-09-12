package Stack.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import Stack.controllers.StackController;
import Stack.models.Pregunta;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea el menu principal.
 * @author Jorge
 *
 */
public class MenuPrincipal {

	private StackController controller;	
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;


	/**
	 * Constructor de la clase.
	 * @param controller StackController controlador del Stack.
	 */
	public MenuPrincipal(StackController controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Funcion que crea la ventana del menu principal.
	 */
	private void initialize() {
		frame = new JFrame();
		MenuPrincipal claseActual = this;
		
		getFrame().setBounds(100, 100, 800, 440);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginAction(claseActual);
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerAction();
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_2 = new JButton("Crear etiqueta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newTagAction();
			}
		});
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 13));
		GroupLayout groupLayout = new GroupLayout(getFrame().getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 727, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_2)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(btnNewButton)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);

		this.tableModel = rellenarModel();
		this.table = new JTable(tableModel) {
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
				selectAsk(e,listaPreg);		
			}
		});
		
		getFrame().getContentPane().setLayout(groupLayout);
	}
	
	/**
	 * Funcion que rellena un modelo de tabla con las preguntas del Stack.
	 * @return DefaultTableModel modelo de tabla con las preguntas del Stack.
	 */
	public DefaultTableModel rellenarModel() {
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
	 * Funcion para obtener la ventana actual.
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Funcion para obtener el modelo de tabla de las preguntas.
	 * @return DefaultTableModel modelo de tabla de las preguntas.
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Funcion para agregar una pregunta a la tabla.
	 */
	public void agregarPreguntaTabla() {
		Pregunta newPregunta = controller.getPreguntas().get(0);
		int id = newPregunta.getId();
		String pregunta = newPregunta.getTitulo();
		String usuario = newPregunta.getAutor();
		String fecha = newPregunta.getFecha().getFecha();
		Object[] objs = {id,pregunta,usuario,"Todavia no ha sido respondida",fecha};
		tableModel.insertRow(0, objs);
		table.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
		table.repaint();
	}
	
	/**
	 * Funcion para saber en que tabla se esta trabajando.
	 * @return int, 0 si no hay sesion iniciada, 1 si lo hay.
	 */
	public int interruptor() {
		if(controller.getActivo().size()==0) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * Funcion para actualizar la cantidad de respuestas de una pregunta.
	 * @param idPreg int con el id de la pregunta a actualizar.
	 */
	public void actualizarRespuestas(int idPreg) {
		for(int i=0; i<controller.getPreguntas().size();i++) {
			int aux = (int)tableModel.getValueAt(i, 0);
			if(aux == idPreg) {
				if(controller.getPreguntas().get(i).getRespuestas().size()==1) {
					tableModel.setValueAt(1,i, 3);
					table.repaint();
				}
				else {
					int numRes = (int)tableModel.getValueAt(i, 3);
					tableModel.setValueAt(numRes+1,i, 3);
					table.repaint();
				}
			}
		}
	}
	
	/**
	 * Funcion que crea la ventana para iniciar sesion.
	 * @param claseActual MenuPrincipal clase del menu principal.
	 */
	public void loginAction(MenuPrincipal claseActual) {
		LoginView loginFrame = new LoginView(claseActual,frame,controller);
		loginFrame.setVisible(true);
		frame.setVisible(false);
	}
	
	/**
	 * Funcion que crea la ventana para registrarse.
	 */
	public void registerAction() {
		RegisterView registerFrame = new RegisterView(frame,controller);
		registerFrame.setVisible(true);
		frame.setVisible(false);
	}
	
	/**
	 * Funcion para seleccionar una pregunta y abrir una ventana de dicha pregunta.
	 * @param e pregunta seleccionada.
	 * @param listaPreg ArrayList<Pregunta> arreglo con las preguntas.
	 */
	public void selectAsk(MouseEvent e, ArrayList<Pregunta> listaPreg) {
		JTable table = (JTable) e.getSource();
		Point point = e.getPoint();
		int row = table.rowAtPoint(point);
		if(e.getClickCount()==2 && table.getSelectedRow() != -1 && interruptor()==0) {
			AskView frameAsk = new AskView(null,null,frame,controller,listaPreg.get(row));
			frameAsk.setVisible(true);
			frame.setVisible(false);
		}
	}
	
	/**
	 * Funcion que crea una ventana para crear etiquetas.
	 */
	public void newTagAction() {
		TagView dialog = new TagView(frame,controller);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		frame.setVisible(false);
	}
}
