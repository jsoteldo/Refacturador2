package mifarma.ptoventa.recaudacion.reference;

import java.util.ArrayList;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2006 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicaci�n : TimerRecaudacion.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * GFonseca      09.07.2013   Creaci�n<br>
 * <br>
 * @author Gilder Fonseca S.<br>
 * @version 1.0<br>
 *
 */
public class TimerRecaudacion extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(TimerRecaudacion.class);
    String estTsscRecau = "";
    int cant = 1;
    String indicador = ConstantsRecaudacion.ESTADO_INICIO_TAREA;
    int cantidadIntentos = 0;
    Long codigoTrssc = null;
    String modoRecau = "";
    FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();

    private int tipoConsulta = 1;
    private int tipoConsultaFinanciero = 1;
    private ArrayList<ArrayList<String>> lstDatosTrsccSix = new ArrayList<>();

    public void run() {
        try {
            switch (tipoConsulta) {
            case 1:
                //1. Busqueda estado de respuesta
                consultarEstadoTrsscSix();
                break;
            case 2:
                //2. Busqueda de codigo de respuesta
                obtenerDatosTrsscSix();
                break;
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void setCantidadIntentos(int cantidadIntentos) {
        this.cantidadIntentos = cantidadIntentos;
    }

    public void setCodigoTrssc(Long codigoTrssc) {
        this.codigoTrssc = codigoTrssc;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setModoRecau(String modoRecau) {
        this.modoRecau = modoRecau;
    }

    public String getModoRecau() {
        return modoRecau;
    }

    private void obtenerDatosTrsscSix() throws Exception {
        log.info("intento de datos de transaccion nro: " + cant);

        if (cant++ > cantidadIntentos) {
            log.info("termino el timer de intento de obtener la respuesta de recaudacion");
            indicador = ConstantsRecaudacion.ESTADO_FIN_TAREA;
            cancel();
        }else{
            if(tipoConsultaFinanciero == 2){
                lstDatosTrsccSix = facadeRecaudacion.consultaRptaAnulSix_financiero(codigoTrssc, modoRecau, 
                                                                                    ConstantsRecaudacion.MSJ_SIX_ANULACION_TRSSC_400);
            }else{
                lstDatosTrsccSix = facadeRecaudacion.obtenerDatosTrsscSix(codigoTrssc, modoRecau);   
            }
            if (lstDatosTrsccSix.size() > 0) {
                ConstantsRecaudacion.vCOD_ESTADO_TRSSC_RECAU = ConstantsRecaudacion.ESTADO_SIX_TERMINADO;
                indicador = ConstantsRecaudacion.ESTADO_RESPUESTA_DISPONIBLE;
                cancel();
            }
        }
    }

    private void consultarEstadoTrsscSix() throws Exception {
        log.info("intento de respuesta de Recaudacion nro: " + cant);

        if (cant++ > cantidadIntentos) {
            log.info("termino el timer de intento de obtener la respuesta de recaudacion");
            indicador = ConstantsRecaudacion.ESTADO_FIN_TAREA;
            cancel();
        }else{
            estTsscRecau = facadeRecaudacion.obtenerEstTrsscReacudacion(codigoTrssc, modoRecau);
            if (!estTsscRecau.equals("") && ConstantsRecaudacion.ESTADO_SIX_TERMINADO.equals(estTsscRecau)) {
                ConstantsRecaudacion.vCOD_ESTADO_TRSSC_RECAU = estTsscRecau;
                log.info("Se obtuvo respuesta de transaccion de Recaudacion " + estTsscRecau);
                indicador = ConstantsRecaudacion.ESTADO_RESPUESTA_DISPONIBLE;
                cancel();
            }
        }
    }

    ArrayList<ArrayList<String>> getDatosTrsscSix() {
        return lstDatosTrsccSix;
    }

    void setTipoConsulta(int pTipoConsulta) {
        this.tipoConsulta = pTipoConsulta;
    }

    void setTipoConsultaFinanciero(int tipoConsultaFinanciero) {
        this.tipoConsultaFinanciero = tipoConsultaFinanciero;
    }
}
