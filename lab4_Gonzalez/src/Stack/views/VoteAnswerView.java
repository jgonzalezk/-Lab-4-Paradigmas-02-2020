package Stack.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Stack.controllers.StackController;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una ventana para agregar un voto a una respuesta.
 * @author Jorge
 *
 */
public class VoteAnswerView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Crear la ventana que muestra si se quiere votar positivo o negativo a la respuesta.
	 * @param frameOpciones JFrame ventana del menu opciones.
	 * @param framePadre JFrame ventana anterior.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param idPreg int entero con el id de la pregunta.
	 * @param idRes int entero con el id de la respuesta.
	 * @param controller StackController controlador del Stack.
	 */
	public VoteAnswerView(JFrame frameOpciones,JFrame framePadre,JFrame frameMenuLogin,int idPreg, int idRes,StackController controller) {
		setBounds(100, 100, 284, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Voto positivo");
			lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
			lblNewLabel.setBounds(24, 12, 88, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblVotoNegativo = new JLabel("Voto negativo");
			lblVotoNegativo.setFont(new Font("SansSerif", Font.BOLD, 13));
			lblVotoNegativo.setBounds(151, 12, 88, 14);
			contentPanel.add(lblVotoNegativo);
		}
		{
			JButton btnPositivo = new JButton("+");
			btnPositivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					votePosAnswerAction(frameOpciones,framePadre,frameMenuLogin,idPreg,idRes,controller);
				}
			});
			btnPositivo.setBounds(34, 41, 61, 52);
			contentPanel.add(btnPositivo);
		}
		{
			JButton btnNegativo = new JButton("+");
			btnNegativo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					voteNegAnswerAction(frameOpciones,framePadre,frameMenuLogin,idPreg,idRes,controller);
				}
			});
			btnNegativo.setBounds(161, 41, 61, 52);
			contentPanel.add(btnNegativo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						backToOptionsMenuAction(framePadre);
					}
				});
				cancelButton.setFont(new Font("SansSerif", Font.BOLD, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Funcion que agrega un voto positivo a la respuesta escogida.
	 * @param frameOpciones JFrame ventana del menu opciones.
	 * @param framePadre JFrame ventana anterior.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param idPreg int entero con el id de la pregunta.
	 * @param idRes int entero con el id de la respuesta.
	 * @param controller StackController controlador del Stack.
	 */
	public void votePosAnswerAction(JFrame frameOpciones,JFrame framePadre,JFrame frameMenuLogin,int idPreg, int idRes,StackController controller) {
		String idPregStr = String.valueOf(idPreg);
		String idResStr = String.valueOf(idRes);
		String ids = idPregStr + "," + idResStr;
		
		controller.vote(ids,"+");
		dispose();
		framePadre.dispose();
		frameOpciones.dispose();
		JOptionPane.showMessageDialog(contentPanel, "Voto agregado correctamente.", "", 1);
		frameMenuLogin.setVisible(true);
	}
	
	/**
	 * Funcion que agrega un voto negativo a la respuesta escogida.
	 * @param frameOpciones JFrame ventana del menu opciones.
	 * @param framePadre JFrame ventana anterior.
	 * @param frameMenuLogin JFrame ventana del menu login.
	 * @param idPreg int entero con el id de la pregunta.
	 * @param idRes int entero con el id de la respuesta.
	 * @param controller StackController controlador del Stack.
	 */
	public void voteNegAnswerAction(JFrame frameOpciones,JFrame framePadre,JFrame frameMenuLogin,int idPreg, int idRes,StackController controller) {
		String idPregStr = String.valueOf(idPreg);
		String idResStr = String.valueOf(idRes);
		String ids = idPregStr + "," + idResStr;
		
		controller.vote(ids,"-");
		dispose();
		framePadre.dispose();
		frameOpciones.dispose();
		JOptionPane.showMessageDialog(contentPanel, "Voto agregado correctamente.", "", 1);
		frameMenuLogin.setVisible(true);
	}
	
	/**
	 * Funcion para volver a la ventana anterior.
	 * @param framePadre JFrame ventana anterior.
	 */
	public void backToOptionsMenuAction(JFrame framePadre) {
		dispose();
		framePadre.setVisible(true);
	}
}
