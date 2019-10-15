package mifarma.ptoventa.recaudacion.RecaudacionBFP;
/*
 * Javier Abellán. 30 de octubre de 2003
 *
 * RenderTabla.java
 *
 */

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 */
public class BFPRenderTable extends JLabel implements TableCellRenderer {
    /**
     * Constructor por defecto.
     */
    public BFPRenderTable() {
    }

    /**  Returns the component used for drawing the cell.  This method is
     *  used to configure the renderer appropriately before drawing.
     *
     * @param	table		the <code>JTable</code> that is asking the
     * 				renderer to draw; can be <code>null</code>
     * @param	value		the value of the cell to be rendered.  It is
     * 				up to the specific renderer to interpret
     * 				and draw the value.  For example, if
     * 				<code>value</code>
     * 				is the string "true", it could be rendered as a
     * 				string or it could be rendered as a check
     * 				box that is checked.  <code>null</code> is a
     * 				valid value
     * @param	isSelected	true if the cell is to be rendered with the
     * 				selection highlighted; otherwise false
     * @param	hasFocus	if true, render cell appropriately.  For
     * 				example, put a special border on the cell, if
     * 				the cell can be edited, render in the color used
     * 				to indicate editing
     * @param	row	        the row index of the cell being drawn.  When
     * 				drawing the header, the value of
     * 				<code>row</code> is -1
     * @param	column	        the column index of the cell being drawn
     *
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JLabel etiqueta = new JLabel();
        if (isSelected){
            etiqueta.setBackground(new Color(162,202,229));
            etiqueta.setForeground(Color.black);
        }
        else{
            etiqueta.setBackground(Color.WHITE);
        }
        if (value instanceof String) {
            etiqueta.setOpaque(true);
            etiqueta.setText((String)value);
            etiqueta.setAlignmentY((float)0.8);
        }
        if (hasFocus) {
            if(column==0){
                etiqueta.setBackground(new Color(162,202,229));
                etiqueta.setForeground(Color.black);
            }else{
                etiqueta.setBackground(new Color(255,130,14));
                etiqueta.setForeground(Color.black);
            }
        }
        return etiqueta;
    }

}
