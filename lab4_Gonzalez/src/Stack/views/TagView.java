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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que crea una ventana para crear una etiqueta.
 * @author Jorge
 *
 */
public class TagView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField inputNameTag;
	private JTextField inputDescTag;
	private StackController controller;

	/**
	 * Crear una ventana que solicita los datos para crear una etiqueta.
	 * @param framePadre JFrame ventana anterior.
	 * @param controller StackController controlador del Stack.
	 */
	public TagView(JFrame framePadre,StackController controller) {
		this.controller = controller;
		setBounds(100, 100, 450, 264);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de la etiqueta");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 22, 234, 14);
		contentPanel.add(lblNewLabel);
		
		inputNameTag = new JTextField();
		inputNameTag.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inputNameTag.setBounds(20, 47, 381, 32);
		contentPanel.add(inputNameTag);
		inputNameTag.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n de la etiqueta");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 106, 234, 14);
		contentPanel.add(lblNewLabel_1);
		
		inputDescTag = new JTextField();
		inputDescTag.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inputDescTag.setColumns(10);
		inputDescTag.setBounds(20, 131, 381, 32);
		contentPanel.add(inputDescTag);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						crearTagAction(framePadre);
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
						backToMenuPrincipalAction(framePadre);
					}
				});
				cancelButton.setFont(new Font("SansSerif", Font.BOLD, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Funcion verifica si se puede crear o no una etiqueta,
	 * en el caso que que sea posible se devuelve a la ventana anterior.
	 * @param framePadre JFrame ventana anterior.
	 */
	public void crearTagAction(JFrame framePadre) {
		String nameTag = inputNameTag.getText();
		String descTag = inputDescTag.getText();
		int verificador = controller.crearEtiqueta(nameTag, descTag);
		
		if(verificador == 1) {
			JOptionPane.showMessageDialog(contentPanel, "La etiqueta se ha creado exitosamente.", "", 1);
			dispose();
			framePadre.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(contentPanel, "La etiqueta que desea crear ya existe.", "Error", 0);
		}
	}
	
	/**
	 * Funcion para volver a la ventana anterior.
	 * @param framePadre JFrame ventana anterior.
	 */
	public void backToMenuPrincipalAction(JFrame framePadre) {
		dispose();
		framePadre.setVisible(true);
	}
}
