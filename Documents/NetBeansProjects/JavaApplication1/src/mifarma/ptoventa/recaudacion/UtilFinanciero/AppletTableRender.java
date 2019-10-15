package mifarma.ptoventa.recaudacion.UtilFinanciero;
/**
 * Javier Abellán. 26 Octubre 2003
 *
 * Clase principal para el ejemplo de uso del JTable.
 */
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;

/**
 * Crea la vista, el modelo y el control del ejemplo de uso de la tabla.
 * Hereda de JFrame.
 */
public class AppletTableRender extends JApplet
{
    
    /** Creates a new instance of PrincipalTabla */
    public void init() 
    {
        // Crea el modelo
        ModeloTabla modelo = new ModeloTabla();
        
        // Crea el control, pasándole el modelo
        ControlTabla control = new ControlTabla (modelo);
        
        // Se crea el panel con todos los botones, pasándole el modelo y el
        // control.
        PanelCompleto panel = new PanelCompleto (modelo, control);
        
        // Se le pasa al panel el renderer para la tabla. Al crear el render,
        // se le pasan los iconos.
        panel.tomaRender(new RenderTabla (dameIconos()));
        
        // Crea la vista, pasándole el control
        this.getContentPane().add (panel);
    }

    /**
     * Devuelve los iconos para utilizar en nuestra tabla.
     * Cada icono representa un rango de edad. Los chavales juegan al 
     * futbol.gif, hasta 60 son hombre.gif y de ahi para arriba son
     * viejo.gif
     */
    private Icon[] dameIconos()
    {
        Icon [] iconos = new Icon [3];
        URL directorio = this.getDocumentBase();
        iconos[0] = new ImageIcon (this.getImage(directorio, "futbol.gif"));
        iconos[1] = new ImageIcon (this.getImage(directorio, "hombre.gif"));
        iconos[2] = new ImageIcon (this.getImage(directorio, "viejo.gif"));
        return iconos;
    }
}