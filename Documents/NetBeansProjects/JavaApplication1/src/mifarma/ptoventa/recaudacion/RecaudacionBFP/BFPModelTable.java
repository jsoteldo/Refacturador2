package mifarma.ptoventa.recaudacion.RecaudacionBFP;
/**
 * Javier Abellán, 26 Oct 2003
 *
 * ModeloTabla.java
 *
 * Modelo de tabla para el ejmplo de uso del JTable
 */
import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;


/**
 * Modelo de personas. Cada fila es una persona y las columnas son los datos
 * de la persona.
 * Implementa TableModel y dos métodos para añadir y eliminar Personas del
 * modelo
 */
public class BFPModelTable implements TableModel
{
    
    /** Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     *
     */
    public int getColumnCount() {
        // Devuelve el número de columnas del modelo, que coincide con el
        // número de datos que tenemos de cada persona.
        return 5;
    }
    
    /** Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     *
     */
    public int getRowCount() {
        // Devuelve el número de personas en el modelo, es decir, el número
        // de filas en la tabla.
        return datos.size();
    }
    
    /** Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param	rowIndex	the row whose value is to be queried
     * @param	columnIndex 	the column whose value is to be queried
     * @return	the value Object at the specified cell
     *
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        RegistroTarjeta aux;
        
        // Se obtiene la persona de la fila indicada
        aux = (RegistroTarjeta)(datos.get(rowIndex));
        
        // Se obtiene el campo apropiado según el valor de columnIndex
        switch (columnIndex)
        {
            case 0:
                return aux.getNroTarjeta();
            case 1:
                return aux.getMontoMin();
            case 2:
                return aux.getMontoMes();
            case 3:
                return aux.getMontoTotal();
            case 4:
                return aux.getOtroMonto();
            default:
                return null;
        }
    }
    
    /**
     * Borra del modelo la persona en la fila indicada 
     */
    public void borraPersona (int fila)
    {
        // Se borra la fila 
        datos.remove(fila);
        
        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, fila, fila, 
            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        
        // ... y pasándoselo a los suscriptores
        avisaSuscriptores (evento);
    }
    
    /**
     * Añade una persona al final de la tabla
     */
    
    public void anhadeDatosPago (RegistroTarjeta nuevoDato)
    {
        // Añade la persona al modelo 
        datos.add (nuevoDato);
        
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
                // La columna cero contiene el nombre de la persona, que es
                // un String
                return String.class;
            case 1:
                // La columna uno contiene el apellido de la persona, que es
                // un String
                return String.class;
            case 2:
                // La columna dos contine la edad de la persona, que es un
                // Integer (no vale int, debe ser una clase)
                return String.class;
            case 3:
                // La columna dos contine la edad de la persona, que es un
                // Integer (no vale int, debe ser una clase)
                return String.class;
            case 4:
                // La columna dos contine la edad de la persona, que es un
                // Integer (no vale int, debe ser una clase)
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
                return "Nro. Tarjeta";
            case 1:
                return "Pago Min";
            case 2:
                return "Pago Mes";
            case 3:
                return "Pago Total";
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
        // true Permite que la celda sea editable.
        return false;
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
        RegistroTarjeta aux;
        aux = (RegistroTarjeta)(datos.get(rowIndex));
        
        // Cambia el campo de Persona que indica columnIndex, poniendole el 
        // aValue que se nos pasa.
        switch (columnIndex)
        {
            case 0:
                aux.setNroTarjeta((String)aValue);
                break;
            case 1:
                aux.setMontoMin((String)aValue);
                break;
            case 2:
                aux.setMontoMes((String)aValue);
                break;
            case 3:
                aux.setMontoTotal((String)aValue);
                break;
            case 4:
                aux.setOtroMonto((String)aValue);
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
     * Persona */
    private LinkedList datos = new LinkedList();
    
    /** Lista de suscriptores. El JTable será un suscriptor de este modelo de
     * datos */
    private LinkedList listeners = new LinkedList();

}
