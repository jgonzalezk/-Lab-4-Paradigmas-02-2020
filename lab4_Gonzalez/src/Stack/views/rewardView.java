package Stack.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Stack.controllers.StackController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una ventana para agregar una recompensa a la pregunta escogida.
 * @author Jorge
 *
 */
public class rewardView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField inputReward;


	/**
	 * Crear la ventana que solicita la cantidad de recompensa se va agregar.
	 * @param id int con el id de la pregunta.
	 * @param preguntaPadre AskView clase de la pregunta visualizada.
	 * @param loginMenuPadre LoginMenuView clase del login menu.
	 * @param askFrame JFrame ventana de la pregunta.
	 * @param controller StackController controlador del Stack.
	 */
	public rewardView(int id,AskView preguntaPadre,LoginMenuView loginMenuPadre,JFrame askFrame,StackController controller) {
		setBounds(100, 100, 342, 211);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Recomensa m\u00E1xima a otorgar:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel(controller.puntosActivoStr()+" punto(s).");
		
		
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JLabel lblNewLabel_2 = new JLabel("RECOMPENSA A AGREGAR:");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		inputReward = new JTextField();
		inputReward.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inputReward.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(inputReward, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1))
						.addComponent(lblNewLabel_2, Alignment.LEADING))
					.addContainerGap(132, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inputReward, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(108, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						rewardAction(id,preguntaPadre,loginMenuPadre,askFrame,controller);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelAction(askFrame);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Funcion para verificar si se puede agregar la recompensa ingresada,
	 * en el caso que sea posible se devuelve a la ventana de la pregunta.
	 * @param id int entero con el id de la pregunta.
	 * @param preguntaPadre AskView clase de la pregunta visualizada.
	 * @param loginMenuPadre LoginMenuView clase del login menu.
	 * @param askFrame JFrame ventana de la pregunta.
	 * @param controller StackController controlador del Stack.
	 */
	public void rewardAction(int id,AskView preguntaPadre,LoginMenuView loginMenuPadre,JFrame askFrame,StackController controller) {
		String idString = String.valueOf(id);
		String rewardPoints = inputReward.getText();
		int verificador = controller.reward(idString, rewardPoints);
		if(verificador==1) {
			preguntaPadre.actualizarRecompensa();
			loginMenuPadre.actualizarRecomUsuario();
			dispose();
			askFrame.setVisible(true);
		}
		else if(verificador==-2) {
			JOptionPane.showMessageDialog(contentPanel, "La pregunta ya se encuentra resuelta.", "Error", 0);
			dispose();
			askFrame.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(contentPanel, "Ingrese una cantidad v\u00e1lida.", "Error", 0);
		}
	}
	
	/**
	 * Funcion para volver a la pregunta.
	 * @param askFrame JFrame ventana de la pregunta.
	 */
	public void cancelAction(JFrame askFrame) {
		dispose();
		askFrame.setVisible(true);
	}
}
