package mifarma.ptoventa.recaudacion.UtilFinanciero;
/*
 * Javier Abellán. 26 de octubre de 2003
 *
 * ControlTabla.java
 *
 * Clase para el ejemplo de uso del JTable.
 *
 */

/**
 * Clase para modificar el modelo de la tabla.
 */
public class ControlTabla
 {
     /**
      * Constructor. Se le pasa el modelo, al que añade varios elementos y
      * se lo guarda.
      */
     public ControlTabla(ModeloTabla modelo)
     {
         this.modelo = modelo;
         
         modelo.anhadePersona(new Persona ("Eddison", "Alva", 38));
         modelo.anhadePersona(new Persona ("Issac", "Newton", 24));
         modelo.anhadePersona(new Persona ("Albert", "Instain", 65));
         modelo.anhadePersona(new Persona ("Nicola", "Tesla", 48));
     }
     
     /**
      * Añade una fila en el modelo, al final del mismo
      */
     public void anhadeFila ()
     {
         // Crea una nueva Persona, construyéndola a base del atributo 
         // numero. La edad es una cuenta para que salgan iconos distintos
         // en cada llamada, más o menos.
         Persona dato = new Persona (
            "Nombre " + Integer.toString (numero),
            "Apellido " + Integer.toString (numero), 
            (numero*25)%100);
         
         // Se añade la nueva persona al modelo de datos
         modelo.anhadePersona (dato);
         
         // Incrementa numero para que la siguiente Persona a añadir sea
         // distinta.
         numero++;
     }
     
     /** Elimina la primera fila del modelo */
     public void borraFila ()
     {
         if (modelo.getRowCount() > 0)
            modelo.borraPersona (0);
     }
     
     /** El modelo de la tabla */
     private ModeloTabla modelo = null;
     
     /** Numero que nos servirá para construir personas distintas para la 
      tabla */
     private static int numero = 0;
}