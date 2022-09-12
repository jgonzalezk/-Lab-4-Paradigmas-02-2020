package Stack.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Stack.controllers.StackController;
import Stack.models.Pregunta;

import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una ventana de una pregunta con respecto a una opcion.
 * @author Jorge
 *
 */
public class AskOpciones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Crear la ventana con una pregunta con respecto a una opcion.
	 * @param opcion int con la opcion.
	 * @param framePadre JFrame ventana anterior.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param frameOpciones JFrame ventana del menu de opciones.
	 * @param controller StackController controlador del Stack.
	 * @param pregunta Pregunta pregunta a mostrar.
	 */
	public AskOpciones(int opcion,JFrame framePadre,JFrame frameMenuLogin,JFrame frameOpciones,StackController controller,Pregunta pregunta) {
		JFrame parentFrame = (JFrame) getRootPane().getParent();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtContenido = new JTextArea();
		txtContenido.setBounds(30, 132, 678, 101);
		txtContenido.setText(pregunta.getContenido());
		txtContenido.setOpaque(false);
		txtContenido.setLineWrap(true);
		txtContenido.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtContenido.setFocusable(false);
		txtContenido.setEditable(false);
		txtContenido.setBackground(SystemColor.menu);
		contentPane.add(txtContenido);
		
		JLabel lblRecompensa = new JLabel("Recompensa de la pregunta: "+pregunta.getRecompensa()+" punto(s).");
		lblRecompensa.setBounds(30, 103, 354, 18);
		lblRecompensa.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblRecompensa);
		
		JLabel lblFecha = new JLabel("");
		lblFecha.setText(pregunta.getFecha().getFecha());
		lblFecha.setBounds(30, 81, 99, 16);
		lblFecha.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblFecha);
		
		JLabel lblAutor = new JLabel("");
		lblAutor.setText(pregunta.getAutor());
		lblAutor.setBounds(30, 54, 91, 16);
		lblAutor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAutor.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblAutor);
		
		JLabel lblTitulo = new JLabel("");
		lblTitulo.setText(pregunta.getTitulo());
		lblTitulo.setBounds(30, 11, 304, 37);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		contentPane.add(lblTitulo);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backToMenuOpcionesAction(framePadre);
			}
		});
		btnNewButton.setBounds(626, 562, 82, 27);
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 331, 678, 220);
		contentPane.add(scrollPane);
		
		
		String col [] = {"ID","Respuestas","Usuario","Fecha de publicacion","Votos Positivos","Votos Negativos"};
		this.tableModel = new DefaultTableModel(col, 0);	
		for(int i=0; i< pregunta.getRespuestas().size();i++) {
			if(!controller.getActivo().get(0).getNombre().equals(pregunta.getRespuestas().get(i).getAutor())) {
				int id =  pregunta.getRespuestas().get(i).getId();
				String respuesta = pregunta.getRespuestas().get(i).getContenido();
				String usuario = pregunta.getRespuestas().get(i).getAutor();
				String fecha = pregunta.getRespuestas().get(i).getFecha().getFecha();
				int votosPos = pregunta.getRespuestas().get(i).getVotosPositivos();
				int votosNeg = pregunta.getRespuestas().get(i).getVotosNegativos();
			
				Object[] objs = {id,respuesta,usuario,fecha,votosPos,votosNeg};
				tableModel.addRow(objs);
			}
		}

		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectAnswerOptionAction(e,parentFrame,opcion,frameMenuLogin,frameOpciones,controller,pregunta);
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table);
		
		JLabel lblEstado = new JLabel("Estado: ");
		if(pregunta.getEstado().get(0)==0) {
			lblEstado.setText(lblEstado.getText()+"Sin resolver");
		}
		else {
			lblEstado.setText(lblEstado.getText()+"Respuesta aceptada con ID "+pregunta.getEstado().get(1));
		}
		
		lblEstado.setBounds(30, 244, 194, 14);
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblEstado);
		
		JLabel lblPositivo = new JLabel("Votos positivos: "+pregunta.getVotosPositivos());
		lblPositivo.setBounds(30, 271, 129, 18);
		lblPositivo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblPositivo);
		
		JLabel lblNegativo = new JLabel("Votos negativos: "+pregunta.getVotosNegativos());
		lblNegativo.setBounds(224, 271, 129, 18);
		lblNegativo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(lblNegativo);
		
		JButton btnVotoPNeg = new JButton("+");
		btnVotoPNeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voteNegAskAction(framePadre,frameMenuLogin,frameOpciones,controller,pregunta);				
			}
		});
		btnVotoPNeg.setBounds(348, 271, 48, 20);
		btnVotoPNeg.setFont(new Font("SansSerif", Font.PLAIN, 13));
		contentPane.add(btnVotoPNeg);
		
		JButton btnVotoPPos = new JButton("+");
		btnVotoPPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				votePosAskAction(framePadre,frameMenuLogin,frameOpciones,controller,pregunta);
			}
		});
		btnVotoPPos.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnVotoPPos.setBounds(152, 271, 48, 20);
		contentPane.add(btnVotoPPos);
		
		JLabel lblAccept = new JLabel("Hacer doble click en la respuesta a aceptar.");
		lblAccept.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccept.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblAccept.setBounds(30, 306, 304, 14);
		contentPane.add(lblAccept);
		
		JLabel lblVote = new JLabel("(En el caso de votar a una repuesta ");
		lblVote.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblVote.setBounds(433, 274, 275, 14);
		contentPane.add(lblVote);
		
		JLabel lblVote1 = new JLabel("hacer doble click)");
		lblVote1.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblVote1.setBounds(433, 293, 275, 14);
		contentPane.add(lblVote1);
		
		JLabel lblEtiquetas = new JLabel("Etiquetas: "+pregunta.tagsStr());
		lblEtiquetas.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblEtiquetas.setBounds(300, 245, 408, 14);
		contentPane.add(lblEtiquetas);
		
		if(opcion==1) {
			btnVotoPNeg.setEnabled(false);
			btnVotoPNeg.setVisible(false);
			btnVotoPPos.setEnabled(false);
			btnVotoPPos.setVisible(false);
			lblVote.setVisible(false);
			lblVote1.setVisible(false);
		}
		else{
			lblAccept.setVisible(false);
		}
	}
	
	/**
	 * Funcion para volver al menu de opciones.
	 * @param framePadre JFrame de la ventana anterior.
	 */
	public void backToMenuOpcionesAction(JFrame framePadre) {
		dispose();
		framePadre.setVisible(true);
	}
	/**
	 * Funcion para seleccionar una respuesta y abrir una venta segun la opcion.
	 * @param e respuesta seleccionada.
	 * @param parentFrame JFrame Ventana del panel actual.
	 * @param opcion int opcion.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param frameOpciones JFrame ventana de las opciones.
	 * @param controller StackController controlador de Stack.
	 * @param pregunta Pregunta.
	 */
	public void selectAnswerOptionAction(MouseEvent e,JFrame parentFrame ,int opcion,JFrame frameMenuLogin,JFrame frameOpciones,StackController controller,Pregunta pregunta) {
		JTable table = (JTable) e.getSource();
		Point point = e.getPoint();
		int row = table.rowAtPoint(point);		
		int idSelect = (int)table.getValueAt(row, 0);
		int idPreg = pregunta.getId();
		
		if(e.getClickCount()==2 && table.getSelectedRow() != -1 && opcion==1) {
			AcceptView dialog = new AcceptView(frameOpciones,parentFrame,frameMenuLogin,idPreg,idSelect,controller);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			parentFrame.setVisible(false);
			dialog.setVisible(true);

		}
		else if(e.getClickCount()==2 && table.getSelectedRow() != -1 && opcion==2) {
			VoteAnswerView dialog = new VoteAnswerView(frameOpciones,parentFrame,frameMenuLogin,idPreg,idSelect,controller);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}
	
	/**
	 * Funcion que agrega un voto negativo a la respuesta.
	 * @param framePadre JFrame ventana de la ventana anterior.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param frameOpciones JFrame ventana de las opciones.
	 * @param controller StackController controlador de Stack.
	 * @param pregunta Pregunta.
	 */
	public void voteNegAskAction(JFrame framePadre,JFrame frameMenuLogin,JFrame frameOpciones,StackController controller,Pregunta pregunta) {
		String idPreg = String.valueOf(pregunta.getId());
		int verificador = controller.vote(idPreg, "-");
		if(verificador == 1) {
			dispose();
			framePadre.dispose();
			frameOpciones.dispose();
			JOptionPane.showMessageDialog(contentPane, "Voto agregado correctamente.", "", 1);
			frameMenuLogin.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(contentPane, "No puedes votar a una pregunta tuya.", "Error", 0);
		}
	}
	
	/**
	 * Funcion que agrega un voto positivo a la respuesta.
	 * @param framePadre JFrame ventana de la ventana anterior.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param frameOpciones JFrame ventana de las opciones.
	 * @param controller StackController controlador de Stack.
	 * @param pregunta Pregunta.
	 */
	public void votePosAskAction(JFrame framePadre,JFrame frameMenuLogin,JFrame frameOpciones,StackController controller,Pregunta pregunta) {
		String idPreg = String.valueOf(pregunta.getId());
		int verificador = controller.vote(idPreg, "+");
		if(verificador == 1) {
			dispose();
			framePadre.dispose();
			frameOpciones.dispose();
			JOptionPane.showMessageDialog(contentPane, "Voto agregado correctamente.", "", 1);
			frameMenuLogin.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(contentPane, "No puedes votar a una pregunta tuya.", "Error", 0);
		}
	}
}
