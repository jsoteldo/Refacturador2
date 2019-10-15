package mifarma.ptoventa.centromedico.reference.proceso.proceso;

import mifarma.ptoventa.ventas.DlgListaProductos;

public class SubProcesos_Receta extends Thread {
   private int tiempoInactividad;
   String pNombre = "";
   boolean indTerminoProceso = false;
   MiWorker_Receta mWork;
   DlgListaProductos dlgAux;
   
   // asignar nombre a subproceso, llamando al constructor de la superclase

    /**
     * @param dlgCompra
     */
    public SubProcesos_Receta(DlgListaProductos dlgCompra)
   {
       pNombre = "a";
       dlgAux = dlgCompra;
   }        
    
   // el método run es el código a ejecutar por el nuevo subproceso
   public void run()
   { 
     try {
          if(indTerminoProceso){
              Thread.sleep(0);
          }
          operaproceso(pNombre);
      } 
      // si el subproceso se interrumpió durante su inactividad, imprimir rastreo de la pila
      catch ( InterruptedException excepcion ) {
         excepcion.printStackTrace();
      }
      // imprimir nombre del subproceso
      System.err.println(" termino su inactividad" );
       //mWork.
      
   } 
   
   public void operaproceso(String pNombre_2){
       /*
       SubProcesos subproceso1 = new SubProcesos("GET_PROD_VENTA" );
       SubProcesos subproceso2 = new SubProcesos("GET_PROD_ESPECIALES" );
       */
       try {
           
               System.err.println("inicio proceso prueba");
               //DBVentas.cargaListaProductosVenta(VariablesVentas.tableModelListaGlobalProductos);    
               //Collections.sort(VariablesVentas.tableModelListaGlobalProductos.data,new FarmaTableComparator(2,true));
            GlobalProceso_Receta.vEjecutaProceso = "S";
               dlgAux.procesaReceta();
               try {
                   Thread.sleep(1000*2);
               } catch (InterruptedException e) {
               }
               //dlgAux.iniciaListadoPantalla();
            GlobalProceso_Receta.vEjecutaProceso = "T";
               System.err.println("fin proceso prueba");
               indTerminoProceso = true;
           
       }
       //catch (SQLException e) {
       catch (Exception e) {
           e.printStackTrace();
           indTerminoProceso = true;
       }
       finally {
           indTerminoProceso = true;
       }
   }
} 