package club.j3studios.system.utils.jtable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class GestionEncabezadoTabla  implements TableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JComponent jcomponent = null;
        
        if( value instanceof String ) {
            jcomponent = new JLabel((String) value);
            ((JLabel)jcomponent).setHorizontalAlignment( SwingConstants.CENTER );
            ((JLabel)jcomponent).setSize(45, jcomponent.getWidth());   
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth()));
        }         
   
        //jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(221, 211, 211)));
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        //jcomponent.setBackground( new Color(236,234,219) ); 32,136,203
        //jcomponent.setBackground( new Color(65,65,65) );
        jcomponent.setBackground( new Color(32, 136, 203) );
        jcomponent.setToolTipText("Tabla Seguimiento");
        jcomponent.setForeground(Color.WHITE);
        
        return jcomponent;
    }


}