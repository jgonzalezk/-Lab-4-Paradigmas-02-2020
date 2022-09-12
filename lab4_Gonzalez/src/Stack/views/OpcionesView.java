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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una ventana para escoger una de las opciones mostradas.
 * @author Jorge
 *
 */
public class OpcionesView extends JFrame {

	private JPanel contentPane;

	/**
	 * Crear la ventana con las opciones que se pueden escoger.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param controller StackController controlador del Stack.
	 */
	public OpcionesView(JFrame frameMenuLogin,StackController controller) {
		JFrame parentFrame = (JFrame) getRootPane().getParent();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 229, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Otras opciones");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("Aceptar respuesta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameAcceptAction(frameMenuLogin,parentFrame,controller);
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		JButton btnAgregarVoto = new JButton("Agregar voto");
		btnAgregarVoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameVoteAction(frameMenuLogin,parentFrame,controller);
			}
		});
		btnAgregarVoto.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backFrameAction(frameMenuLogin);
				
			}
		});
		btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAgregarVoto, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAgregarVoto, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	/**
	 * Funcion para generar el nuevo menu con la opcion escogida de aceptar una respuesta.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param parentFrame JFrame ventana actual.
	 * @param controller StackController controlador del Stack.
	 */
	public void frameAcceptAction(JFrame frameMenuLogin,JFrame parentFrame,StackController controller) {
		MenuOpcionesView frame = new MenuOpcionesView(frameMenuLogin,parentFrame,1,controller);
		frame.setVisible(true);
		parentFrame.setVisible(false);
	}
	
	/**
	 * Funcion para generar el nuevo menu con la opcion escogida de agregar un voto.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param parentFrame JFrame ventana actual.
	 * @param controller StackController controlador del Stack.
	 */
	public void frameVoteAction(JFrame frameMenuLogin,JFrame parentFrame,StackController controller) {
		MenuOpcionesView frame = new MenuOpcionesView(frameMenuLogin,parentFrame,2,controller);
		frame.setVisible(true);
		parentFrame.setVisible(false);
	}
	
	/**
	 * Funcion para volver al menu login.
	 * @param frameMenuLogin
	 */
	public void backFrameAction(JFrame frameMenuLogin) {
		dispose();
		frameMenuLogin.setVisible(true);
	}
}
