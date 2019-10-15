package mifarma.ptoventa.recaudacion.reference;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import javax.swing.JDialog;
import javax.swing.JLabel;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.DBCaja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityRecaudacion {

    private static final Logger log = LoggerFactory.getLogger(UtilityRecaudacion.class);

    /**
     * Constructor muestra el usuario y la fecha
     * @author Luigy Terrazos
     * @since 16.04.2013
     */
    public void initMensajesVentana(JDialog pDialog, JLabel jLblFecha, JLabel jLblUsu, String pTipoRcd) {
        //cambiar aqui
        String fechaHora = "", fecha = "", hora = "";
        try {
            fechaHora = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA_HORA);
            fecha = fechaHora.substring(0, 10);
            hora = fechaHora.substring(11, 19);
            ConstantsRecaudacion.FECHA_RCD = fecha;
            ConstantsRecaudacion.HORA_RCD = hora;
            (new FacadeRecaudacion()).obtenerTipoCambio();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialog, "Error al obtener la fecha y hora. \n " + sql.getMessage(), null);
        }
        if (jLblFecha != null && jLblUsu != null) {
            jLblFecha.setText(fecha);
            jLblUsu.setText(FarmaVariables.vIdUsu);
        }

    }

    public static String eliminaComasNumber(String strNumero) {

        String strNumResult = "";
        for (int a = 0; a < strNumero.length(); a++) {
            if (strNumero.charAt(a) != ',')
                strNumResult += strNumero.charAt(a);
        }
        return strNumResult;
    }

    public static String formatNumber(String tmpMontoPagar) {
        //tmpMontoPagar = tmpMontoPagar.replace(",",".");
        double dtmpMontoPagar = FarmaUtility.getDecimalNumber(tmpMontoPagar);
        tmpMontoPagar = FarmaUtility.formatNumber(dtmpMontoPagar);

        return tmpMontoPagar;
    }

    public static double formatNumberDouble(String tmpMontoPagar) {
        tmpMontoPagar = tmpMontoPagar.replace(",", ".");
        double dtmpMontoPagar = FarmaUtility.getDecimalNumber(tmpMontoPagar);
        return dtmpMontoPagar;
    }

    public static String consultarEstadoTrsscSix(Long pCodTrssc, String strModoRecau) {
        //ERIOS 2.2.9 El tiempo de reintento es cada 5 segundos
        TimerRecaudacion timerTask = new TimerRecaudacion();
        String cantIntentosLectura;
        int cantIntentos = 3;
        try {
            cantIntentosLectura = DBCaja.cantidadIntentosRespuestaRecarga().trim();
            cantIntentos = Integer.parseInt(cantIntentosLectura) / 5;
        } catch (Exception e) {
            log.error("", e);
            //cantIntentosLectura = "3";
            cantIntentos = 3;
        }
        timerTask.setCantidadIntentos(cantIntentos);
        timerTask.setCodigoTrssc(pCodTrssc);
        timerTask.setModoRecau(strModoRecau);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000 * 5);
        do {
            ; //log.debug("indicador TIMER DE RECAUDACION :"+timerTask.getIndicador());
        } while (timerTask.getIndicador().trim().equalsIgnoreCase(ConstantsRecaudacion.ESTADO_INICIO_TAREA));

        log.debug("termino el TIMER DE RECAUDACION");
        log.debug("timerTask.getIndicador():" + timerTask.getIndicador());

        return timerTask.getIndicador();
    }

    public static Date fechaConvertirStringToDate(String strFecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/mm/yyyy");

        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            log.error("", ex);
        }
        return fecha;
    }

    public static int convertirStringToInt(String strDato) {
        int strRpt = new Integer(strDato);

        return strRpt;
    }


    public static String obtenerCodigoServRecau(String tipoRecau) {
        String codServRecau = "";

        if (ConstantsRecaudacion.TIPO_REC_CMR.equals(tipoRecau)) {
            codServRecau = ConstantsRecaudacion.RCD_COD_SERV_PAGO_CMR;
        } else if (ConstantsRecaudacion.TIPO_REC_CLARO.equals(tipoRecau)) {
            codServRecau = ConstantsRecaudacion.RCD_COD_SERV_PAGO_CLARO;
        } else if (ConstantsRecaudacion.TIPO_REC_CITI.equals(tipoRecau)) {
            codServRecau = ConstantsRecaudacion.RCD_COD_SERV_PAGO_CITIBANK;
        } else if (ConstantsRecaudacion.TIPO_REC_PRES_CITI.equals(tipoRecau)) {
            codServRecau = ConstantsRecaudacion.RCD_COD_SERV_PREST_CITIBANK;
        } else if (ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(tipoRecau)) {
            codServRecau = ConstantsRecaudacion.RCD_COD_SERV_PAGO_RIPLEY;
        }
        return codServRecau;
    }


    public static String obtenerCodAlianza(String tipoRecau) {
        String codAlianza = "";

        if (ConstantsRecaudacion.TIPO_REC_CMR.equals(tipoRecau)) {
            codAlianza = ConstantsRecaudacion.RCD_COD_ALIANZA_CMR;
        } else if (ConstantsRecaudacion.TIPO_REC_CLARO.equals(tipoRecau)) {
            codAlianza = ConstantsRecaudacion.RCD_COD_ALIANZA_CLARO;
        } else if (ConstantsRecaudacion.TIPO_REC_CITI.equals(tipoRecau) ||
                   ConstantsRecaudacion.TIPO_REC_PRES_CITI.equals(tipoRecau)) {
            codAlianza = ConstantsRecaudacion.RCD_COD_ALIANZA_CITIBANK;
        } else if (ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(tipoRecau)) {
            codAlianza = ConstantsRecaudacion.RCD_COD_ALIANZA_RIPLEY;
        }
        return codAlianza;
    }


    public static String formatoNroCuotas(String strNroCuotas) {
        String strNroCuotasVoucher = "";
        int intNroCuotas = Integer.parseInt(strNroCuotas);
        if (intNroCuotas < 10) {
            strNroCuotasVoucher = "0" + strNroCuotas;
        } else {
            strNroCuotasVoucher = strNroCuotas;
        }
        return strNroCuotasVoucher;
    }


    public static String obtenerNroTraceConciliacion(String strCodTrssc) {

        if (strCodTrssc.length() > 4) {
            strCodTrssc = strCodTrssc.substring(strCodTrssc.length() - 4, strCodTrssc.length());
        }

        return strCodTrssc;
    }

    public static ArrayList<ArrayList<String>> obtenerDatosTrsscSix(Long pCodTrssc, String strModoRecau,
                                                 String indicador) throws Exception {
        //ERIOS 2.2.9 El tiempo de reintento es cada 5 segundos
        TimerRecaudacion timerTask = new TimerRecaudacion();
        String cantIntentosLectura;
        /*try {
            cantIntentosLectura = DBCaja.cantidadIntentosRespuestaRecarga().trim();
        } catch (SQLException e) {
            cantIntentosLectura = "3";
        }*/
        // *TODO Determinar el numero de intentos
        cantIntentosLectura = "10"; //CAMBIAR CAMBIAR CAMBIAR

        timerTask.setCantidadIntentos(Integer.parseInt(cantIntentosLectura));
        timerTask.setCodigoTrssc(pCodTrssc);
        timerTask.setModoRecau(strModoRecau);
        timerTask.setTipoConsulta(2);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000 * 5);
        do {
            ; //log.debug("indicador TIMER DE RECAUDACION :"+timerTask.getIndicador());
        } while (timerTask.getIndicador().trim().equalsIgnoreCase(ConstantsRecaudacion.ESTADO_INICIO_TAREA));

        log.debug("termino el TIMER DE RECAUDACION");
        log.debug("timerTask.getIndicador():" + timerTask.getIndicador());
        indicador = timerTask.getIndicador();
        return timerTask.getDatosTrsscSix();
    }
    
    public static ArrayList<ArrayList> getCantIntentosTiempoEsperaBancoFinanciero() {
        ArrayList<ArrayList> paramTiempo = new ArrayList();
        ArrayList parametros = new ArrayList();
        
        try {
            log.debug("PTOVENTA_GRAL.RECUP_ESPERA_INTEN_BANCO_FINAN: " + parametros);
            FarmaDBUtility.executeSQLStoredProcedureArrayList(paramTiempo, "PTOVENTA_GRAL.RECUP_ESPERA_INTEN_BANCO_FINAN", parametros);
        } catch (SQLException e) {
            log.error("Error: " + e);
        }
        
        return paramTiempo;
    }
    
    public static ArrayList<ArrayList<String>> obtenerDatosTrsscSixFinanciero(Long pCodTrssc, String strModoRecau, 
                                                                              String indicador) throws Exception {
        //ERIOS 2.2.9 El tiempo de reintento es cada 5 segundos
        TimerRecaudacion timerTask = new TimerRecaudacion();
        String cantIntentosLectura;
        /*try {
            cantIntentosLectura = DBCaja.cantidadIntentosRespuestaRecarga().trim();
        } catch (SQLException e) {
            cantIntentosLectura = "3";
        }*/
        //*TODO Determinar el numero de intentos
        cantIntentosLectura = "10"; //50/5 //CAMBIAR CAMBIAR CAMBIAR
        //cantIntentosLectura = ""+VariablesRecaudacion.vNroIntentos;
        
        timerTask.setCantidadIntentos(Integer.parseInt(cantIntentosLectura));
        timerTask.setCodigoTrssc(pCodTrssc);
        timerTask.setModoRecau(strModoRecau);
        timerTask.setTipoConsulta(2);
        timerTask.setTipoConsultaFinanciero(2);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000 * 5);
        do {
            ; //log.debug("indicador TIMER DE RECAUDACION :"+timerTask.getIndicador());
        } while (timerTask.getIndicador().trim().equalsIgnoreCase(ConstantsRecaudacion.ESTADO_INICIO_TAREA));

        log.debug("termino el TIMER DE RECAUDACION");
        log.debug("timerTask.getIndicador():" + timerTask.getIndicador());
        indicador = timerTask.getIndicador();
        VariablesRecaudacion.vIndicadorSix = timerTask.getIndicador();
        return timerTask.getDatosTrsscSix();
    }
    
    public static String obtieneMarcaTarjetaCreditoFinanciero(String pNumTarjeta) {
        String marcaTarjeta = "";
        
        try {
            marcaTarjeta = DBCaja.obtieneMarcaTarjetaCreditoFinanciero(pNumTarjeta);
            return marcaTarjeta;
        } catch (SQLException sqlException) {
            log.error("",sqlException);
            return marcaTarjeta;
        }
    }

    public double recupera_limiteRecacudacionSoles() {
        double nro=0;
        String dato;
        try {
            dato = DBCaja.recupera_limiteRecacudacionSoles();
            nro=Double.parseDouble(dato);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return nro;
    }

    public boolean recupera_indicadorRealiaValidacion() {
        String dato;
        boolean realizarValidacion=false;
        try {
            dato = DBCaja.recupera_indicadorRealiaValidacion();
            if(dato.equalsIgnoreCase("S")){
                realizarValidacion=true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return realizarValidacion;
    }
    
    public double recupera_limiteRecacudacionDolares() {
        double nro = 0;
        String dato;
        try {
            dato = DBCaja.recupera_limiteRecacudacionDolares();
            nro = Double.parseDouble(dato);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        
        return nro;
    }

    public String recuperaDNI_Usuario() {
        String nroDNI ="";
        try {
            nroDNI = DBCaja.recupera_DNI_Usuario();
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return nroDNI;
    }

    public static String recuperaTipoOperacio_BFP(String codTrrsSix, String codRecau) {
        String tipoOperacion ="";
        try {
            tipoOperacion = DBCaja.recupera_TipoOperacioBFP(codTrrsSix,codRecau);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return tipoOperacion;
    }

    public static double recuperaTipoCambio_BFP(String codTrrsSix) {
        Double tipoCambioBFP =0.0d;
        /*
        try {
            String tipoCambio = DBCaja.recupera_TipoCambioBFP(codTrrsSix);
            tipoCambioBFP=Double.parseDouble(tipoCambio);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }*/
        return tipoCambioBFP;
    }
	
	
    public static String recuperaNombreCliente_BFP(String codTrrsSix, String codRecau) {
        String nombreBFP ="";
        try {
            nombreBFP = DBCaja.recupera_NombreClienteBFP(codTrrsSix,codRecau);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return nombreBFP;
    }

    public static String recupera_CuotaPrestamoBFP(String codTrrsSix, String codRecau) {
        String cuotaBFP ="";
        try {
            cuotaBFP = DBCaja.recupera_CuotaPrestamoBFP(codTrrsSix,codRecau);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return cuotaBFP;
    }
    
    public static void saveNombreCliente_BFP(String codSix, String cNum_Recaudacion_in, String vNombre_Cliente_BFP) {
        try {
            DBCaja.saveNombreCliente_BFP(codSix,cNum_Recaudacion_in,vNombre_Cliente_BFP);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveCuotaPrestamo_BFP(String codSix, String cNum_Recaudacion_in, String vCuotaPrestamo_BFP) {
        try {
            DBCaja.saveCuotaPrestamo_BFP(codSix,cNum_Recaudacion_in,vCuotaPrestamo_BFP);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveCliente_CuotaPrestamoBFP(String codSix, 
                                                    String cCodRecaudacion,
                                                    String vNombre_Cliente_BFP, 
                                                    String vCuotaPrestamo_BFP) {
        
        if(vNombre_Cliente_BFP!= null && !vNombre_Cliente_BFP.trim().equalsIgnoreCase(""))
            saveNombreCliente_BFP(codSix,cCodRecaudacion,vNombre_Cliente_BFP);
        
        if(vCuotaPrestamo_BFP!=null && !vCuotaPrestamo_BFP.trim().equalsIgnoreCase(""))
            saveCuotaPrestamo_BFP(codSix,cCodRecaudacion,vCuotaPrestamo_BFP);
    }

    public static String getNuevoTituloBFP(String tipo) {
        String nombreBFP ="Diners club y Banco Financiero";
        try {
            nombreBFP = DBCaja.getNuevoTituloBFP();
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        VariablesRecaudacion.RAZON_SOCIAL_BFP=nombreBFP;
        if(tipo.equalsIgnoreCase("mayus"))
            nombreBFP=nombreBFP.toUpperCase().trim();
        
        return nombreBFP;
    }
}
