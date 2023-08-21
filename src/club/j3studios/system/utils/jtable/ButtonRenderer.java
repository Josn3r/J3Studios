package club.j3studios.system.utils.jtable;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ButtonRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof JButton) {
			JButton boton = (JButton)value;
			return boton;
		}
    	return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 	
    }
    
}
