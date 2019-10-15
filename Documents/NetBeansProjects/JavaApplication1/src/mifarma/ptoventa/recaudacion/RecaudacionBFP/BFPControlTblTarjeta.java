package mifarma.ptoventa.recaudacion.RecaudacionBFP;

import java.util.ArrayList;

/*
 * Javier Abell�n. 26 de octubre de 2003
 *
 * ControlTabla.java
 *
 * Clase para el ejemplo de uso del JTable.
 *
 */

/**
 * Clase para modificar el modelo de la tabla.
 */
public class BFPControlTblTarjeta
 {
     /**
      * Constructor. Se le pasa el modelo, al que a�ade varios elementos y
      * se lo guarda.
      */
   
    public BFPControlTblTarjeta(BFPModelTblTarjeta bfpModelTable) {
        this.setModelo(bfpModelTable);        
    }

    public BFPControlTblTarjeta(BFPModelTblTarjeta bfpModelTable, ArrayList<RegistroOperacion> datos) {
        this.setModelo(bfpModelTable);
        
        for(int i=0;i<datos.size();i++){
            getModelo().anhadeDatosPago(datos.get(i));
        }
    }

    /**
     * A�ade una fila en el modelo, al final del mismo
     */
     public void anhadeFila ()
     {
         // Crea una nueva Persona, construy�ndola a base del atributo 
         // numero. La edad es una cuenta para que salgan iconos distintos
         // en cada llamada, m�s o menos.
         RegistroOperacion dato = new RegistroOperacion (
            "tarjeta " + Integer.toString (numero),
            "monto min" + Integer.toString (numero), 
            "monto Mes" + Integer.toString (numero), 
            "monto Total" + Integer.toString (numero), 
            "Otro Monto" + Integer.toString (numero));
         
         // Se a�ade la nueva persona al modelo de datos
        getModelo().anhadeDatosPago (dato);
         
         // Incrementa numero para que la siguiente Persona a a�adir sea
         // distinta.
         numero++;
     }
     
     /** Elimina la primera fila del modelo */
     public void borraFila ()
     {
         if (getModelo().getRowCount() > 0)
            getModelo().borraPersona (0);
     }
     
     /** El modelo de la tabla */
     private BFPModelTblTarjeta modelo = null;
     
     /** Numero que nos servir� para construir personas distintas para la 
      tabla */
     private static int numero = 0;

    public BFPModelTblTarjeta getModelo() {
        return modelo;
    }

    public void setModelo(BFPModelTblTarjeta modelo) {
        this.modelo = modelo;
    }
}
