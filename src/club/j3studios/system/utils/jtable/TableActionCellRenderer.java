package club.j3studios.system.utils.jtable;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import club.j3studios.system.utils.PanelAction;

public class TableActionCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 	
		
		PanelAction panel = new PanelAction();
		if (isSelected == false && row % 2 == 0) {
			panel.setBackground(Color.WHITE);
		} else {
			panel.setBackground(com.getBackground());
		}
		
		return panel;
	}
	
}
