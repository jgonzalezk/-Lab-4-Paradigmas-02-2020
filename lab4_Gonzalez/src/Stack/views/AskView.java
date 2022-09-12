package Stack.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Stack.controllers.StackController;
import Stack.models.Pregunta;
import Stack.models.Respuesta;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;

/**
 * Clase que crea una interfaz para visualizar una pregunta.
 * @author Jorge
 *
 */
public class AskView extends JFrame {

	private JPanel contentPane;
	private JTextField inputAnswer;
	private JTable table;
	
	private StackController controller;
	private JFrame framePadre;
	private JLabel lblRecompensa;
	private Pregunta pregunta;
	private DefaultTableModel tableModel;

	/**
	 * Crea una ventana para visualizar una pregunta.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 * @param loginMenuPadre LoginMenuView clase del menu login.
	 * @param framePadre JFrame ventana de la ventana anterior.
	 * @param controller StackController controlador del stack.
	 * @param pregunta Pregunta pregunta a visualizar.
	 */
	public AskView(MenuPrincipal menuPrincipalPadre,LoginMenuView loginMenuPadre,JFrame framePadre,StackController controller,Pregunta pregunta) {
		this.framePadre = framePadre;
		this.controller = controller;
		this.pregunta = pregunta;
		AskView claseActual = this;
		JFrame parentFrame = (JFrame) getRootPane().getParent();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(625, 653, 82, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backToFramePadre();
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		inputAnswer = new JTextField();
		inputAnswer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inputAnswer.setBounds(36, 604, 252, 38);
		inputAnswer.setColumns(10);
		
		JButton btnAnswer = new JButton("Enviar respuesta");
		btnAnswer.setBounds(36, 653, 129, 27);
		btnAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				answerAction(menuPrincipalPadre);
			}
		});
		btnAnswer.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 336, 671, 220);
		
		JButton btnReward = new JButton("Ofrecer recompensa");
		btnReward.setBounds(36, 291, 194, 27);
		btnReward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				callRewardAction(claseActual,loginMenuPadre,parentFrame);
			}
		});
		btnReward.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblTitulo = new JLabel("");
		lblTitulo.setBounds(36, 16, 304, 37);
		lblTitulo.setText(pregunta.getTitulo());
		
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JLabel lblAutor = new JLabel("");
		lblAutor.setBounds(36, 59, 91, 16);
		lblAutor.setText(pregunta.getAutor());
		
		lblAutor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAutor.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblFecha = new JLabel("");
		lblFecha.setBounds(36, 86, 99, 16);
		lblFecha.setText(pregunta.getFecha().getFecha());
		
		lblFecha.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("Tu respuesta");
		lblNewLabel.setBounds(36, 582, 99, 16);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		this.lblRecompensa = new JLabel("Recompensa de la pregunta: "+ pregunta.getRecompensa()+" punto(s).");
		lblRecompensa.setBounds(36, 108, 309, 18);
		lblRecompensa.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JTextArea txtContenido = new JTextArea();
		txtContenido.setBounds(36, 137, 671, 76);
		txtContenido.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtContenido.setText(pregunta.getContenido());
		txtContenido.setOpaque(false);
		txtContenido.setLineWrap(true);
		txtContenido.setEditable(false);
		txtContenido.setFocusable(false);
		
		txtContenido.setBackground(SystemColor.control);
		if(controller.getActivo().size()==0) {
			btnReward.setEnabled(false);
			btnAnswer.setEnabled(false);
			inputAnswer.setEnabled(false);
		}

		String col [] = {"ID","Respuestas","Usuario","Fecha de publicacion","Votos Positivos","Votos Negativos"};
		this.tableModel = new DefaultTableModel(col, 0);
		
		for(int i=0; i< pregunta.getRespuestas().size();i++) {
			int id =  pregunta.getRespuestas().get(i).getId();
			String respuesta = pregunta.getRespuestas().get(i).getContenido();
			String usuario = pregunta.getRespuestas().get(i).getAutor();
			String fecha = pregunta.getRespuestas().get(i).getFecha().getFecha();
			int votosPos = pregunta.getRespuestas().get(i).getVotosPositivos();
			int votosNeg = pregunta.getRespuestas().get(i).getVotosNegativos();
					
			Object[] objs = {id,respuesta,usuario,fecha,votosPos,votosNeg};
			tableModel.addRow(objs);
		}
		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
		scrollPane.setViewportView(table);
		
		contentPane.setLayout(null);
		contentPane.add(txtContenido);
		contentPane.add(lblRecompensa);
		contentPane.add(lblNewLabel);
		contentPane.add(lblFecha);
		contentPane.add(btnReward);
		contentPane.add(inputAnswer);
		contentPane.add(lblAutor);
		contentPane.add(lblTitulo);
		contentPane.add(btnAnswer);
		contentPane.add(btnNewButton);
		contentPane.add(scrollPane);
		
		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setBounds(36, 224, 232, 14);
		if(pregunta.getEstado().get(0)==0) {
			lblEstado.setText(lblEstado.getText()+"Sin resolver.");
		}
		else {
			lblEstado.setText(lblEstado.getText()+"Respuesta aceptada con ID "+pregunta.getEstado().get(1));
		}
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblEstado);
		
		JLabel lblPositivo = new JLabel("Votos positivos: "+pregunta.getVotosPositivos());
		lblPositivo.setBounds(36, 253, 129, 14);
		lblPositivo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblPositivo);
		
		JLabel lblNegativo = new JLabel("Votos negativos: "+pregunta.getVotosNegativos());
		lblNegativo.setBounds(181, 251, 129, 18);
		lblNegativo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblNegativo);
		
		JLabel lblEtiquetas = new JLabel("Etiquetas: "+pregunta.tagsStr());
		
		lblEtiquetas.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblEtiquetas.setBounds(278, 224, 429, 14);
		contentPane.add(lblEtiquetas);

	}
	
	/**
	 * Funcion para actualizar los puntos de la pregunta visualizados.
	 */
	public void actualizarRecompensa() {
		lblRecompensa.setText("Recompensa de la pregunta: "+ pregunta.getRecompensa()+" punto(s).");
		lblRecompensa.repaint();
	}
	
	/**
	 * Funcion para agregar una nueva respuesta a la tabla de respuestas.
	 */
	public void agregarRespuestaTabla() {
		int index = pregunta.getRespuestas().size();
		Respuesta newRespuesta = pregunta.getRespuestas().get(index-1);
		int id = newRespuesta.getId();
		String respuesta = newRespuesta.getContenido();
		String usuario = newRespuesta.getAutor();
		String fecha = newRespuesta.getFecha().getFecha();
		Object[] objs = {id,respuesta,usuario,fecha,0,0};
		tableModel.addRow(objs);
		table.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
		table.repaint();
	}
	
	/**
	 * Funcion para volver a la ventana anterior.
	 */
	public void backToFramePadre() {
		dispose();
		framePadre.setVisible(true);
	}
	
	/**
	 * Funcion para agregar una respuesta a dicha pregunta.
	 * @param menuPrincipalPadre clase del menu principal.
	 */
	public void answerAction(MenuPrincipal menuPrincipalPadre) {
		String idString = String.valueOf(pregunta.getId());
		String respuesta = inputAnswer.getText();
		if(controller.answer(idString, respuesta)==1) {
			JOptionPane.showMessageDialog(contentPane, "Respuesta agregada exitosamente.", "", 1);
			menuPrincipalPadre.actualizarRespuestas(pregunta.getId());
			agregarRespuestaTabla();
		}
	}
	
	/**
	 * Funcion que crea una ventana para agregar una recompensa a la pregunta.
	 * @param claseActual AskView clase actual.
	 * @param loginMenuPadre LoginMenuView clase de login menu.
	 * @param parentFrame JFrame ventana de la clase actual.
	 */
	public void callRewardAction(AskView claseActual,LoginMenuView loginMenuPadre,JFrame parentFrame) {
		int id = pregunta.getId();
		rewardView dialog = new rewardView(id,claseActual,loginMenuPadre,parentFrame,controller);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		parentFrame.setVisible(false);
		dialog.setVisible(true);
	}
}
