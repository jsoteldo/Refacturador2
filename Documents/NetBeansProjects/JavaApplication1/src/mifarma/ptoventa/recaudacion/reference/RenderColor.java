package mifarma.ptoventa.recaudacion.reference;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class RenderColor extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        //JLabel etiqueta = new JLabel();
        if (isSelected) {
            setBackground(Color.CYAN);
        } else {
            setBackground(Color.YELLOW);
        }

        if (value instanceof String) {
            setOpaque(true);
            setText((String)value);
        }

        if (column == 10) {
            setBackground(Color.GREEN);
            setForeground(Color.RED);
        }
        if (hasFocus) {
            setBackground(Color.BLUE);
            setForeground(Color.WHITE);
        }
        System.out.println("************************************");
        System.out.println("************************************");
        System.out.println("==> "+this);
        System.out.println("************************************");
        System.out.println("************************************");
        return this;
    }
}
