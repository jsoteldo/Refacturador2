package mifarma.ptoventa.puntos.reference;

import farmapuntos.bean.BeanTarjeta;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiloFarmaPuntos extends Thread{
    
    private static final Logger log = LoggerFactory.getLogger(HiloFarmaPuntos.class);
    Properties pCadenaProperties;
    private int contador;
    private int contCursores;
    ArrayList listaLocalesCia = new ArrayList();
    private String pProceso = "";
    BeanTarjeta tarjetaPuntos=null;
    String pNumPedVta="";
    boolean isAdicionarBonificado=false;
    
    public String RUTA_FILE = "D:\\DBA_FILE\\";


    public HiloFarmaPuntos(String pNombreProceso,BeanTarjeta tarjetaPuntos, String pNumPedVta, boolean isAdicionarBonificado) throws SQLException {
        this.pProceso = pNombreProceso;
        this.tarjetaPuntos=tarjetaPuntos;
        this.pNumPedVta=pNumPedVta;
        this.isAdicionarBonificado=isAdicionarBonificado;
    }

    public void run() {
        log.warn("Inicia Hilo HiloServicioDBAFile "+pProceso);
        try {
            log.debug("-"+pProceso+"-");
           if(pProceso.trim().equalsIgnoreCase(ConstantsPuntos.HiloFarmaPuntos.HILO_QUOTE)){
               procesoQuote(tarjetaPuntos,pNumPedVta,isAdicionarBonificado);
            }
       
        } catch (Exception e) {
            log.error("Exception", e);
        }
        log.debug("Termino proceso.");
    }

    public void procesoQuote(BeanTarjeta tarjetaPuntos, String pNumPedVta, boolean isAdicionarBonificado) {
        log.info("INICIO procesoQuote() EN HILO");
        /*try {
            UtilityProgramaAcumula.procesarQuote(tarjetaPuntos, pNumPedVta, isAdicionarBonificado);
            //   log.debug("EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        } catch (Exception e) {
            // TODO: Add catch code
            log.error("", e);
        }*/
        log.info("FIN procesoQuote()");
    }
   
}
