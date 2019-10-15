package mifarma.ptoventa.recaudacion.RecaudacionBFP;

import java.util.ArrayList;


public class ControlTable
 {
     /**
      * Constructor. Se le pasa el modelo, al que añade varios elementos y
      * se lo guarda.
      */
      public ControlTable(ModelTable modelTable, ArrayList<RegistroOperacion> registroTarjeta) {
          this.modelo = modelo;
          for(int i=0;i<registroTarjeta.size();i++){
                modelo.anhadeTarjeta(new RegistroOperacion (registroTarjeta.get(i).getNroTarjeta(),
                                                          registroTarjeta.get(i).getMontoMinTarj(),
                                                          registroTarjeta.get(i).getMontoMesTarj(),
                                                          registroTarjeta.get(i).getMontoTotalTarj(),
                                                          registroTarjeta.get(i).getOtroMonto()));
            }
      }
     /*
     public ControlTable(ModelTable modelo)
     {
         this.modelo = modelo;
         
         modelo.anhadeTarjeta(new RegistroTarjeta ("Eddison", "Alva", "Alva", "Alva", "Alva"));
         modelo.anhadeTarjeta(new RegistroTarjeta ("Issac", "Newton", "Newton", "Newton", "Newton"));
         modelo.anhadeTarjeta(new RegistroTarjeta ("Albert", "Instain", "Instain", "Instain", "Instain"));
         modelo.anhadeTarjeta(new RegistroTarjeta ("Nicola", "Tesla", "Tesla", "Tesla", "Tesla"));
     }
     */
     /** El modelo de la tabla */
     private ModelTable modelo = null;
     
     /** Numero que nos servirá para construir personas distintas para la 
      tabla */
     private static int numero = 0;

    
}
