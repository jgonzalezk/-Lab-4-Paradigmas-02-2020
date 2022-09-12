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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para crear una ventana para iniciar sesion.
 * @author Jorge
 *
 */
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField inputUser;
	private JPasswordField inputPassword;
	
	private JFrame framePadre;
	private StackController controller;

	/**
	 * Crear la ventana para iniciar sesiob.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 * @param framePadre JFrame ventana anterior.
	 * @param controller StackController controlador del Stack.
	 */
	public LoginView(MenuPrincipal menuPrincipalPadre,JFrame framePadre, StackController controller) {
		this.controller = controller;
		this.framePadre = framePadre;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Iniciar sesi\u00F3n");
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
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginVerifyAction(menuPrincipalPadre);
			}
		});
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 13));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backToMainMenuAction();
			}
		});
		btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputUser, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputPassword, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(inputUser, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(inputPassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Funcion para verificar si el inicio de sesion es correcto,
	 * en el caso que si lo sea se genera una ventana nueva con el menu login.
	 * @param menuPrincipalPadre MenuPrincipal clase del menu principal.
	 */
	public void loginVerifyAction(MenuPrincipal menuPrincipalPadre) {
		String usuario = inputUser.getText();
		String password = inputPassword.getText();
		if((usuario.isEmpty() || password.isEmpty())) {
			JOptionPane.showMessageDialog(contentPane, "Ingrese un nombre de usario y/o contrase\u00f1a v\u00e1lido.", "Error", 0);
		}
		else if(controller.loginUsuario(usuario,password) == 0 ) {
			JOptionPane.showMessageDialog(contentPane, "Nombre de usario y/o contrase\u00f1a no coinciden.", "Error", 0);
		}
		else {
			JOptionPane.showMessageDialog(contentPane, "El inicio de sesi\u00f3n se ha completado correctamente.", "Login correcto", 1);
			dispose();
			LoginMenuView frame = new LoginMenuView(menuPrincipalPadre,framePadre,controller);
			frame.setVisible(true);
		}
	}
	
	/**
	 * Funcion para volver al menu principal. 
	 */
	public void backToMainMenuAction() {
		dispose();
		framePadre.setVisible(true);
	}
}
