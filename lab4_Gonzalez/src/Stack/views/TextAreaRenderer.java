package Stack.views;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Clase que transforma el contenido de una columna a JTextArea.
 * @author Jorge
 *
 */
class TextAreaRenderer implements TableCellRenderer {

    private JTextArea renderer;

    /**
     * Constructor de la clase.
     */
    public TextAreaRenderer() {
        renderer = new JTextArea();            
        renderer.setLineWrap(true);
        renderer.setWrapStyleWord(true);
        renderer.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                
        renderer.setFont(table.getFont());
        renderer.setText((value == null) ? "" : value.toString());
        JPanel contentPane = new JPanel(new BorderLayout());            
        contentPane.add(renderer);
        table.setRowHeight(row, contentPane.getPreferredSize().height); // sets row's height
        return contentPane;
    }

}