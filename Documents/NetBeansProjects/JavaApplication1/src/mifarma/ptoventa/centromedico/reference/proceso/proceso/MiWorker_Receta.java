package mifarma.ptoventa.centromedico.reference.proceso.proceso;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import mifarma.ptoventa.ventas.DlgListaProductos;

public class MiWorker_Receta extends SwingWorker<String, Void>{
        
        boolean pEjecutaProceso =  false;
        DlgListaProductos dlgAuxCompra = new DlgListaProductos();
        JDialog dlgAux = new JDialog();
        JLabel lblAuxOut;
    SubProcesos_Receta subProc ;// = new SubProcesos();
        JProgressBar jpbProceso;
        int i=0;
        String pCodCia;
        String pCodLocal;
        String pNumReceta;
        
        public MiWorker_Receta(){
            this(null,null,null,null,null,null,null);
        }
        
        public MiWorker_Receta(JDialog dlg,DlgListaProductos dlgCompra,JLabel lblAux,JProgressBar jProgBar,
                        String pCodCia,String pCodLocal,String pNumReceta
                        ){
            dlgAux = dlg;
            dlgAuxCompra = dlgCompra;
            lblAuxOut = lblAux;
            jpbProceso = jProgBar;
            this.pCodCia = pCodCia;
            this.pCodLocal=pCodLocal;
            this.pNumReceta=pNumReceta;
            subProc  = new SubProcesos_Receta(dlgCompra);
        }
            
            
            protected String doInBackground() throws Exception {
                    
                    while(i<=100 && !isCancelled()&&operacionBD() )
                    {
                   //while(!isCancelled() && operacionBD() ){
                        
                            jpbProceso.setValue(i);
                            setProgress(i);
                            i++;
                            Thread.sleep(100);
                        
                        if(i==100){
                            if(!GlobalProceso_Receta.vEjecutaProceso.trim().equalsIgnoreCase("T")){
                                i = 1;
                            }
                        }
                            //Thread.sleep(100);
                    }
                    return "Operacion finalizada";
            }
            
            
            public void done(){
                    try {
                            lblAuxOut.setText(get());
                        if(lblAuxOut.getText().trim().equalsIgnoreCase("Operacion finalizada")){
                            //dlgAuxCompra.muestraDatosTabla();
                            dlgAux.setVisible(false);
                GlobalProceso_Receta.vEjecutaProceso = "0";
                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
            }
            
            
            
            public boolean operacionBD(){
                boolean valor = true;
                if(!pEjecutaProceso){
                   pEjecutaProceso = true;
                    subProc.start();
                }
                if(GlobalProceso_Receta.vEjecutaProceso.equalsIgnoreCase("0"))
                    valor = true;
                else{
                    if(GlobalProceso_Receta.vEjecutaProceso.trim().equalsIgnoreCase("T"))
                        valor = false;
                    else
                        valor = true;
                }
                
                return valor;
            }
    } 

