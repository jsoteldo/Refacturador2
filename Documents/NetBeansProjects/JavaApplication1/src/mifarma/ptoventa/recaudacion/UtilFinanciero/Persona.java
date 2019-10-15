package mifarma.ptoventa.recaudacion.UtilFinanciero;
/**
 * Contiene los datos de una persona: nombre, apellido y edad.
 * Cada fila de la tabla será una persona y tendrá tres columnas,
 * correspondientes a estos tres datos.
 */
public class Persona {
    
    /** Construye una instancia de persona con los datos que se
     * le pasan. */
    public Persona(String nombre, String apellido, int edad) 
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    
    /** Devuelve el campo nombre */
    public String dameNombre() { return nombre; }
    
    /** Devuelve el campo apellido */
    public String dameApellido() { return apellido; }
    
    /** Devuelve el campo edad */
    public int dameEdad() { return edad; }
    
    /** Asigna el campo nombre */
    public void tomaNombre (String nombre) { this.nombre = nombre; }
    
    /** Asigna el campo apellido */
    public void tomaApellido (String apellido ) { this.apellido = apellido; }
    
    /** Asigna el campo edad */
    public void tomaEdad (int edad) {this.edad = edad;}
    
    /** Campo nombre */
    private String nombre = null;
    
    /** Campo apellido */
    private String apellido = null;
    
    /** Campo edad */
    private int edad = 0;
}