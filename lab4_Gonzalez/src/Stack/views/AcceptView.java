package Stack.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Stack.controllers.StackController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una interfaz para aceptar una respuesta.
 * @author Jorge
 *
 */
public class AcceptView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Crear la ventana para confirmar la aceptacion de la pregunta.
	 * @param frameOpciones JFrame Ventana del menu de opciones.
	 * @param framePadre JFrame Ventana anterior.
	 * @param frameMenuLogin JFrame Ventana del menu login.
	 * @param idPreg int del id de la pregunta.
	 * @param idRes int del id de la respuesta.
	 * @param controller StackController controlador del stack.
	 */
	public AcceptView(JFrame frameOpciones,JFrame framePadre,JFrame frameMenuLogin,int idPreg, int idRes,StackController controller) {
		setBounds(100, 100, 283, 148);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Confirmar el aceptar la respuesta");
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblNewLabel.setBounds(10, 11, 247, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("con ID: "+idRes);
			lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(10, 36, 247, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						acceptAnswerAction(frameOpciones,framePadre,frameMenuLogin,idPreg,idRes,controller);
					}
				});
				okButton.setFont(new Font("SansSerif", Font.BOLD, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelAcceptAction(framePadre);
					}
				});
				cancelButton.setFont(new Font("SansSerif", Font.BOLD, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Funcion para aceptar una respuesta.
	 * @param frameOpciones JFrame Ventana del menu de opciones.
	 * @param framePadre JFrame Ventana anterior.
	 * @param frameMenuLogin JFrame Ventana del menu login.
	 * @param idPreg int del id de la pregunta.
	 * @param idRes int del id de la respuesta.
	 * @param controller StackController controlador del stack.
	 */
	public void acceptAnswerAction(JFrame frameOpciones,JFrame framePadre,JFrame frameMenuLogin,int idPreg, int idRes,StackController controller) {
		String idPregStr = String.valueOf(idPreg);
		String idResStr = String.valueOf(idRes);
		
		controller.accept(idPregStr, idResStr);
		dispose();
		framePadre.dispose();
		frameOpciones.dispose();
		JOptionPane.showMessageDialog(contentPanel, "Respuesta aceptada.", "", 1);
		frameMenuLogin.setVisible(true);
	}
	/**
	 * Funcion para cancelar la aceptacion y volver a la ventana anterior.
	 * @param framePadre JFrame Ventana anterior.
	 */
	public void cancelAcceptAction(JFrame framePadre) {
		dispose();
		framePadre.setVisible(true);
	}

}
