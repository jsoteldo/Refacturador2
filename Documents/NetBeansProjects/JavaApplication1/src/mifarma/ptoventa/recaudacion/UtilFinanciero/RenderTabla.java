package mifarma.ptoventa.recaudacion.UtilFinanciero;
/*
 * Javier Abellán. 30 de octubre de 2003
 *
 * RenderTabla.java
 *
 */

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 */
public class RenderTabla extends JLabel implements TableCellRenderer
 {
     /**
      * Constructor por defecto.
      */
     public RenderTabla(Icon[] iconos)
    {
        this.iconos = iconos;
     }

    public RenderTabla() {
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
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) 
     {
         JLabel etiqueta = new JLabel();
         if (isSelected) 
             etiqueta.setBackground (Color.CYAN);
         else
             etiqueta.setBackground (Color.YELLOW);

         if (value instanceof String)
         {
                 etiqueta.setOpaque(true);
                 etiqueta.setText((String)value);
         }
         
         if (value instanceof Integer)
         {
             int valor = ((Integer)value).intValue();
             if (valor > 60)
                etiqueta.setIcon (iconos[2]);
             else if (valor > 30)
                 etiqueta.setIcon (iconos[1]);
             else
                 etiqueta.setIcon (iconos[0]);
             etiqueta.setToolTipText (Integer.toString (valor));
         }
         if(row==0 && column==0){
             etiqueta.setBackground(Color.GREEN);
             etiqueta.setForeground(Color.RED);
         }
         if(hasFocus){
                etiqueta.setBackground(Color.BLUE);
                etiqueta.setForeground(Color.WHITE);
            }
         return etiqueta;
     }
     
     private Icon[] iconos = null;
}