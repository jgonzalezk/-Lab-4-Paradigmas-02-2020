package Stack.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Stack.controllers.StackController;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una interfaz para realizar una pregunta en el Stack.
 * @author Jorge
 *
 */
public class AskFormView extends JFrame {

	private JPanel contentPane;
	private StackController controller;
	private JFrame framePadre;
	private JTextField inputTitulo;
	private JTextField inputContenido;
	private JTextField inputEtiquetas;

	/**
	 * Crear la ventana que solicita los datos para crear una pregunta.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 * @param framePadre JFrame Ventana anterior.
	 * @param controller StackController controlador del stack.
	 */
	public AskFormView( MenuPrincipal menuPrincipalPadre,JFrame framePadre, StackController controller) {
		this.framePadre = framePadre;
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Crear pregunta");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo de la pregunta");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		inputTitulo = new JTextField();
		inputTitulo.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contenido");
		lblNewLabel_1_1.setForeground(Color.GRAY);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		inputContenido = new JTextField();
		inputContenido.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Etiquetas");
		lblNewLabel_1_1_1.setForeground(Color.GRAY);
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		inputEtiquetas = new JTextField();
		inputEtiquetas.setColumns(10);
		
		JButton btnAsk = new JButton("Enviar pregunta");
		btnAsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendAskAction(menuPrincipalPadre);
			}
		});
		btnAsk.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backToLoginMenu();
			}
		});
		btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(inputTitulo, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(inputContenido, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputEtiquetas, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAsk, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(inputTitulo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inputContenido, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inputEtiquetas, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnAsk, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Crear la pregunta con los datos ingresados en la ventana.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 */
	public void sendAskAction(MenuPrincipal menuPrincipalPadre) {
		String titulo = inputTitulo.getText();
		String contenido = inputContenido.getText();
		String tags =  inputEtiquetas.getText();
		
		if(controller.ask(titulo, contenido, tags)==1) {
			JOptionPane.showMessageDialog(contentPane, "Pregunta creada exitosamente.", "", 1);
			dispose();
			menuPrincipalPadre.agregarPreguntaTabla();
			framePadre.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(contentPane, "Una de las etiquetas ingresadas no existe.", "Error", 0);
		}
	}
	
	/**
	 * Volver a la ventana anterior.
	 */
	public void backToLoginMenu() {
		dispose();
		framePadre.setVisible(true);
	}
}
