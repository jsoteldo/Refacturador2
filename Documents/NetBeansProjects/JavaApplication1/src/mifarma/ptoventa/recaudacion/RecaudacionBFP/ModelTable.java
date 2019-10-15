package mifarma.ptoventa.recaudacion.RecaudacionBFP;

import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;


public class ModelTable implements TableModel
{
    private int numColumnas;
    
    public ModelTable() {}

    public int getColumnCount() {
        return 5;
    }
    
    public int getRowCount() {
        // Devuelve el número de personas en el modelo, es decir, el número
        // de filas en la tabla.
        return datos.size();
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        RegistroOperacion aux;
        
        // Se obtiene la persona de la fila indicada
        aux = (RegistroOperacion)(datos.get(rowIndex));
        
        // Se obtiene el campo apropiado según el valor de columnIndex
        switch (columnIndex)
        {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 10;
            default:
                return null;
        }
    }
    
    public void anhadeTarjeta (RegistroOperacion tarjeta)
    {
        // Añade la persona al modelo 
        datos.add (tarjeta);
        
        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores (evento);
    }
    
    /** Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    public void addTableModelListener(TableModelListener l) {
        // Añade el suscriptor a la lista de suscriptores
        listeners.add (l);
    }
    
    /** Returns the most specific superclass for all the cell values
     * in the column.  This is used by the <code>JTable</code> to set up a
     * default renderer and editor for the column.
     *
     * @param columnIndex  the index of the column
     * @return the common ancestor class of the object values in the model.
     *
     */
    public Class getColumnClass(int columnIndex) {
        // Devuelve la clase que hay en cada columna.
        switch (columnIndex)
        {
            case 0:
                // La columna cero contiene el nro de tarjeta de la consulta, que es
                // un String
                return String.class;
            case 1:
                // La columna uno contiene el monto minimo de pago, que es
                // un String
                return String.class;
            case 2:
                // La columna dos contine el monto del mes de pago, que es un
                // String
                return String.class;
            case 3:
                // La columna tres contine el monto total de pago, que es un
                // String
                return String.class;
            case 4:
                // La columna cuatro reservado para el ingreso de otro monto, que es un
                // String
                return String.class;
            default:
                // Devuelve una clase Object por defecto.
                return Object.class;
        }
    }
    
    /** Returns the name of the column at <code>columnIndex</code>.  This is used
     * to initialize the table's column header name.  Note: this name does
     * not need to be unique; two columns in a table can have the same name.
     *
     * @param	columnIndex	the index of the column
     * @return  the name of the column
     *
     */
    public String getColumnName(int columnIndex) 
    {
        // Devuelve el nombre de cada columna. Este texto aparecerá en la
        // cabecera de la tabla.
        switch (columnIndex)
        {
            case 0:
                return "Nro de tarjeta";
            case 1:
                return "Monto minimo";
            case 2:
                return "Monto Mes";
            case 3:
                return "Monto Total";
            case 4:
                return "Otro Monto ("+ConstantsRecaudacion.SIMBOLO_SOLES+")";
            default:
                return null;
        }
    }
    
    /** Returns true if the cell at <code>rowIndex</code> and
     * <code>columnIndex</code>
     * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
     * change the value of that cell.
     *
     * @param	rowIndex	the row whose value to be queried
     * @param	columnIndex	the column whose value to be queried
     * @return	true if the cell is editable
     * @see #setValueAt
     *
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permite que la celda sea editable.
        return true;
    }
    
    /** Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    public void removeTableModelListener(TableModelListener l) {
        // Elimina los suscriptores.
        listeners.remove(l);
    }
    
    /** Sets the value in the cell at <code>columnIndex</code> and
     * <code>rowIndex</code> to <code>aValue</code>.
     *
     * @param	aValue		 the new value
     * @param	rowIndex	 the row whose value is to be changed
     * @param	columnIndex 	 the column whose value is to be changed
     * @see #getValueAt
     * @see #isCellEditable
     *
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) 
    {
        // Obtiene la persona de la fila indicada
        RegistroOperacion aux;
        aux = (RegistroOperacion)(datos.get(rowIndex));
        
        // Cambia el campo de Persona que indica columnIndex, poniendole el 
        // aValue que se nos pasa.
        switch (columnIndex)
        {
            case 0:
                aux.setNroTarjeta((String)aValue);
                break;
            case 1:
                aux.setMontoMinTarj((String)aValue);
                break;
            case 2:
                aux.setMontoMesTarj(((String)aValue));
                break;
            case 3:
                aux.setMontoTotalTarj(((String)aValue));
                break;
            case 4:
                aux.setOtroMonto(((String)aValue));
                break;
            default:
                break;
        }
        
        // Avisa a los suscriptores del cambio, creando un TableModelEvent ...
        TableModelEvent evento = new TableModelEvent (this, rowIndex, rowIndex, 
            columnIndex);

        // ... y pasándoselo a los suscriptores.
        avisaSuscriptores (evento);
    }
    
    /**
     * Pasa a los suscriptores el evento.
     */
    private void avisaSuscriptores (TableModelEvent evento)
    {
        int i;
        
        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }
    
    /** Lista con los datos. Cada elemento de la lista es una instancia de
     * RegistroTarjeta */
    private LinkedList datos = new LinkedList();
    
    /** Lista de suscriptores. El JTable será un suscriptor de este modelo de
     * datos */
    private LinkedList listeners = new LinkedList();

    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }

    public int getNumColumnas() {
        return numColumnas;
    }
}
