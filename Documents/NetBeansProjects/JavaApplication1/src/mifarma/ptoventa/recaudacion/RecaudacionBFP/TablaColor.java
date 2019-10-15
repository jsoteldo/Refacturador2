package mifarma.ptoventa.recaudacion.RecaudacionBFP;


import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class TablaColor {
    protected TableCellRenderer renderer;
    private JTable table;
    protected String text;
    
    public TablaColor(JTable table) {
        this(null, table);
    }
    
    public TablaColor(TableCellRenderer renderer,JTable table) {
        if (renderer == null) {
            this.renderer = new DefaultTableCellRenderer() {
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                                   boolean hasFocus, int row, int column) {
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
                };
        } else {
            this.renderer = renderer;
        }
        this.setTable(table);
    }
    
    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    public void setHeaderRenderer(TableCellRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
