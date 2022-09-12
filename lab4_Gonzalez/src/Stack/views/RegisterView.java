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

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una ventana que solicita los datos para registrarse.
 * @author Jorge
 *
 */
public class RegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField inputUser;
	private JPasswordField inputPassword;
	private StackController controller;	
	private JFrame framePadre;

	/**
	 * Crear la ventana de registro.
	 * @param framePadre JFrame ventana anterior.
	 * @param controller StackController controlador del Stack.
	 */
	public RegisterView(JFrame framePadre,StackController controller) {
		this.controller = controller;
		this.framePadre = framePadre;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Registro de usuario");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		inputUser = new JTextField();
		inputUser.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.GRAY);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		inputPassword = new JPasswordField();
		inputPassword.setColumns(10);
		
		JButton btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerUserAction();
			}
		});
		btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backToFrameAction();
			}
		});
		btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1)
								.addComponent(inputUser, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputPassword, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
							.addContainerGap(23, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
							.addGap(23))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(23, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addContainerGap(194, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inputUser, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(inputPassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Funcion para verificar si se puede registrar el usuario,
	 * en el caso que sea posible se vuelve al menu principal.
	 */
	public void registerUserAction() {
		String usuario = inputUser.getText();
		String password = inputPassword.getText();
		if((usuario.isEmpty() || password.isEmpty())) {
			JOptionPane.showMessageDialog(contentPane, "Ingrese un nombre de usario y/o contrase\u00f1a v\u00e1lido.", "Error", 0);
		}
		else if(controller.registrarUsuario(usuario,password) == 0 ) {
			JOptionPane.showMessageDialog(contentPane, "El nombre de usario ya se ecuentra ocupado.", "Error", 0);
		}
		else {
			JOptionPane.showMessageDialog(contentPane, "El registro se ha completado de forma exitosa.", "Registro correcto", 1);
			dispose();
			framePadre.setVisible(true);
		}
	}
	/**
	 * Funcion para volver al menu principal.
	 */
	public void backToFrameAction() {
		dispose();
		framePadre.setVisible(true);
	}
}
