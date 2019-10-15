package mifarma.ptoventa.recaudacion.UtilFinanciero;
/**
 * Javier Abell�n. 26 Octubre 2003
 *
 * Clase principal para el ejemplo de uso del JTable.
 */
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Crea la vista, el modelo y el control del ejemplo de uso de la tabla.
 * Hereda de JFrame.
 */
public class PrincipalTablaRender extends JFrame
{
    
    /** Creates a new instance of PrincipalTabla */
    public PrincipalTablaRender() 
    {
        // Crea el modelo
        ModeloTabla modelo = new ModeloTabla();
        
        // Crea el control, pas�ndole el modelo
        ControlTabla control = new ControlTabla (modelo);
        
        // Se crea el render, pas�ndole los iconos a emplear.
        RenderTabla render = new RenderTabla ();
        
        // Se crea el panel, pas�ndole modelo y control. Luego se le pasa el
        // render.
        PanelCompleto panel = new PanelCompleto (modelo, control);
        panel.tomaRender (render);
        
        // Se a�ade el panel a la ventana.
        this.getContentPane().add (panel);
        
        // Hace que la ventana sea visible.
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new PrincipalTablaRender();
    }
    
    /**
     * Devuelve los iconos a utilizar con la tabla. Tendr�s que cambiar la
     * ubicaci�n a donde los tengas puestos.
     */
    private Icon[] dameIconos()
    {
        Icon iconos[] = new Icon [3];
        
        // El home del usuario 
        String directorio = System.getProperty ("user.home");
        
        // Se obtienen los iconos.
        iconos[0] = new ImageIcon (directorio + "/Mis Documentos/java/tabla/futbol.gif");
        iconos[1] = new ImageIcon (directorio + "/Mis Documentos/java/tabla/hombre.gif");
        iconos[2] = new ImageIcon (directorio + "/Mis Documentos/java/tabla/viejo.gif");
        
        return iconos;
    }
    
}