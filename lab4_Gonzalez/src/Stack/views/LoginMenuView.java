package Stack.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Stack.controllers.StackController;
import Stack.models.Pregunta;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase para crear una interfaz de un menu tras iniciar sesion.
 * @author Jorge
 *
 */
public class LoginMenuView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFrame framePadre;
	private StackController controller;
	private DefaultTableModel tableModel;
	private JLabel lblRecomUsuario;

	/**
	 * Crea la ventana del menu tras el inicio de sesion.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 * @param framePadre JFrame frame de la ventana anterior.
	 * @param controller StackController controlador del Stack.
	 */
	public LoginMenuView(MenuPrincipal menuPrincipalPadre,JFrame framePadre, StackController controller) {
		this.framePadre = framePadre;
		this.controller = controller;
		JFrame parentFrame = (JFrame) getRootPane().getParent();
		LoginMenuView claseActual = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		String nombreActivo = controller.usuarioActivoStr();
		JLabel lblNewLabel = new JLabel("Bienvenido "+nombreActivo);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logoutAction();
			}
		});
		btnLogout.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		JButton btnAsk = new JButton("Hacer una pregunta");
		btnAsk.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAsk.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generateAskAction(menuPrincipalPadre,parentFrame);
			}
		});
		
		int puntosActivo = controller.puntosActivoStr();
		this.lblRecomUsuario = new JLabel("Reputaci\u00F3n: "+puntosActivo+" punto(s).");
		lblRecomUsuario.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRecomUsuario.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		JButton btnOtrasOpciones = new JButton("Otras opciones");
		btnOtrasOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				otherOptionsAction(parentFrame);
			}
		});
		btnOtrasOpciones.setVerticalAlignment(SwingConstants.BOTTOM);
		btnOtrasOpciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnLogout)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblRecomUsuario))
									.addComponent(btnAsk, Alignment.LEADING))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnOtrasOpciones))
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 727, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout)
						.addComponent(lblRecomUsuario))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAsk)
						.addComponent(btnOtrasOpciones))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		

		this.tableModel = menuPrincipalPadre.getTableModel();
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
				selectAskAction(e,menuPrincipalPadre,parentFrame,claseActual,listaPreg);
			}
		});

		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Funcion para actualizar los puntos del usuario activo.
	 */
	public void actualizarRecomUsuario() {
		lblRecomUsuario.setText("Reputaci\u00F3n: "+controller.puntosActivoStr()+" punto(s).");
		lblRecomUsuario.repaint();
	}
	
	/**
	 * Funcion para cerrar sesion y volver a la ventana anterior.
	 */
	public void logoutAction() {
		dispose();
		controller.logoutUsuario();
		framePadre.setVisible(true);
	}
	
	/**
	 * Funcion para generar una ventana para crear una pregunta.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 * @param parentFrame JFrame ventana de la clase actual.
	 */
	public void generateAskAction(MenuPrincipal menuPrincipalPadre,JFrame parentFrame) {
		AskFormView frameAskForm = new AskFormView(menuPrincipalPadre,parentFrame,controller);
		frameAskForm.setVisible(true);
		parentFrame.setVisible(false);
	}
	
	/**
	 * Funcion para generar una ventana con otras opciones.
	 * @param parentFrame JFrame ventana de la clase actual.
	 */
	public void otherOptionsAction(JFrame parentFrame) {
		OpcionesView frame = new OpcionesView(parentFrame,controller);
		frame.setVisible(true);
		parentFrame.setVisible(false);
	}
	
	/**
	 * Funcion para seleccionar una pregunta y abrir una nueva ventana con dicha pregunta.
	 * @param e pregunta seleccionada mediante el mouse.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 * @param parentFrame JFrame ventana de la clase actual.
	 * @param claseActual LoginMenuView clase actual.
	 * @param listaPreg ArrayList<Pregunta> arreglo de las preguntas del Stack.
	 */
	public void selectAskAction(MouseEvent e,MenuPrincipal menuPrincipalPadre,JFrame parentFrame,LoginMenuView claseActual,ArrayList<Pregunta> listaPreg) {
		JTable table = (JTable) e.getSource();
		Point point = e.getPoint();
		int row = table.rowAtPoint(point);
		if(e.getClickCount()==2 && table.getSelectedRow() != -1 && menuPrincipalPadre.interruptor()==1) {
			AskView frameAsk = new AskView(menuPrincipalPadre,claseActual,parentFrame,controller,listaPreg.get(row));
			frameAsk.setVisible(true);
			parentFrame.setVisible(false);
		}	
	}
}
