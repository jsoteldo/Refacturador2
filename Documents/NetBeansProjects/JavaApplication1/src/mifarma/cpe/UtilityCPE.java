package mifarma.cpe;

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.swing.JDialog;

import javax.swing.JOptionPane;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.epos.TestEPOS;
import mifarma.cpe.epos.UtilityEposCnx;
import mifarma.cpe.modelo.BeanConexionEPOS;
import mifarma.cpe.reference.DBCPElectronico;
import mifarma.cpe.reference.FacadeCPE;

import mifarma.ptoventa.caja.reference.UtilityBarCode;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityCPE {

    private static boolean activoFuncionalidad = false;
    private static boolean registradoPCEnEpos = false;
    private static boolean comunicaPCConEPOS = false;
    private static boolean estaContigenciaEPOS = false;
    private static boolean volverEjecutar = false;

    private static final Logger log = LoggerFactory.getLogger(UtilityCPE.class);
    private BeanConexionEPOS beanConexion;
    private FacadeCPE facade;
    
    public UtilityCPE(){
        facade = new FacadeCPE();
    }
    
    /**
     * Activa la funcionalidad de Facturacion Electronica
     * @creado KMONCADA
     * @param activoFuncionalidad
     * @since 15.11.2016
     */
    private void setActivoFuncionalidad(boolean activoFuncionalidad) {
        this.activoFuncionalidad = activoFuncionalidad;
    }
    
    /**
     * Verifica si se encuentra activa la funcionalidad de Facturacion Electronica
     * @creado KMONCADA
     * @return true/false
     * @since 15.11.2016 
     */
    public static boolean isActivoFuncionalidad() {
        return activoFuncionalidad;
    }
    
    /**
     * Configura si la PC se encuentra registrada en el EPOS
     * @creado KMONCADA
     * @param registradoPCEnEpos
     * @since
     */
    private void setRegistradoPCEnEpos(boolean registradoPCEnEpos){
        this.registradoPCEnEpos = registradoPCEnEpos;
    }
    
    /**
     * Indicador si la PC se encuentra registrada en EPOS
     * @creado KMONCADA
     * @return
     * @since
     */
    public boolean isRegistradoPCEnEpos() {
        return this.registradoPCEnEpos;
    }
    
    /**
     * Configura si la PC interactura con el EPOS, para caso de PC que solo consultan data 
     * @creado KMONCADA
     * @param conectaPCConEpos
     * @since
     */
    private void setComunicaPCConEPOS(boolean conectaPCConEpos){
        this.comunicaPCConEPOS = conectaPCConEpos;
    }
    
    /**
     * Indicador si la PC trabajara con el EPOS enviando comprobantes de pago
     * @creado KMONCADA
     * @return
     * @since
     */
    public boolean isComunicaPCConEPOS(){
        return this.comunicaPCConEPOS;
    }
    
    /**
     * Proceso que verifica en BD si se encuentra activo la Facturacion electronica para el Local
     * @creado KMONCADA
     * @since
     */
    public void cargarIndicadorElectronico() {
        setActivoFuncionalidad(false);
        try {
            String rspta = facade.getIndicadorFuncionalidad();
            if(FarmaConstants.INDICADOR_S.equalsIgnoreCase(rspta)){
                setActivoFuncionalidad(true);
            }
        } catch (Exception ex) {
            log.error("", ex);
            setActivoFuncionalidad(false);
        }
    }
    
    /**
     * Captura los parametros para la conexion al EPOS.
     * @creado KMONCADA
     * @return
     * @since
     */
    public boolean obtenerDatosConexion(){
        boolean isExito = true;
        beanConexion = null;
        try{
            beanConexion = facade.obtenerDatosConexion(FarmaVariables.vCodCia);
        }catch(Exception ex){
            log.error("", ex);
            isExito = false;
            beanConexion = null;
        }
        return isExito;
    }
    
    /**
     * Verifica que la PC se encuentra registrada en el EPOS.
     * @creado KMONCADA
     * @since
     */
    public void verificaEstadoRegistroPCEnEpos(){
        String rspta = facade.getPCRegistradaEnEpos(FarmaVariables.vIpPc.trim());
        if("X".equalsIgnoreCase(rspta)){
            setComunicaPCConEPOS(false);
            setRegistradoPCEnEpos(false);
        }else{
            setComunicaPCConEPOS(true);
            setRegistradoPCEnEpos("S".equalsIgnoreCase(rspta));
        }
    }
    
    /**
     * Obtiene el identificador de la PC/Caja para el EPOS.
     * @creado KMONCADA
     * @return ultimo segmento de la IP de la PC.
     * @since
     */
    private static String getIdCajaEpos(){
        String[] cadena = FarmaVariables.vIpPc.split("\\.");
        return cadena[3].toString();
    }
    
    /**
     * Proceso que registra la PC en el EPOS.
     * @creado KMONCADA
     * @since
     */
    public void registrarPCenEpos() {
        String msjError = "OK";
        String estadoPCenEpos = "";
        try{
            UtilityEposCnx sc = new UtilityEposCnx();
            sc.setDatosConexion(beanConexion);
            sc.setIdCajaEpos(getIdCajaEpos());
            if(!sc.registroPCEnEPOS()){
                msjError = "ERROR NO IDENTIFICADO"+"\nIP PC: "+FarmaVariables.vIpPc;
                estadoPCenEpos = "N";
                setRegistradoPCEnEpos(false);
                setEstaContigenciaEPOS(true);
            }else{
                estadoPCenEpos = "S";
                setRegistradoPCEnEpos(true);
            }
        }catch(Exception ex){
            msjError = ex.getMessage()+"\nIP PC: "+FarmaVariables.vIpPc;
            estadoPCenEpos = "N";
        }
        //actualiza estado de IP
        facade.actualizarRegistroPC(FarmaVariables.vIpPc, estadoPCenEpos, msjError);
    }
    
    /**
     * Metodo que verifica datos validos
     * @creado KMONCADA
     * @param dato
     * @param nombreCampo
     * @return
     * @throws Exception
     * @since
     */
    private static String isDatoValido(String dato, String nombreCampo)throws Exception{
        if(dato == null || (dato != null && dato.trim().length()==0)){
            throw new Exception("PARAMETROS "+nombreCampo+" INCOMPLETO.");
        }
        return dato;
    }
    
    /**
     * Verifica validez de las parte de la trama del comprobante de pago
     * @creado KMONCADA
     * @param trama
     * @param parte
     * @throws Exception
     * @since
     */
    public static void isValidaParteTrama(String trama, String parte)throws Exception{
        if(trama == null || (trama != null && trama.trim().length()==0)){
            throw new Exception("COMPROBANTE DE PAGO ELECTRONICO:\n " + parte+" --> SIN DATOS");
        }
    }
    
    /**
     * Verifica validez de las parte de la trama del comprobante de pago para V2
     * @creado KMONCADA
     * @param trama
     * @param parte
     * @throws Exception
     * @since
     */
    public static void isValidaParteTrama_V2(String trama, String parte)throws Exception{
        if(trama == null || (trama != null && trama.length()==0)){
            throw new Exception("COMPROBANTE DE PAGO ELECTRONICO:\n " + parte+" --> SIN DATOS");
        }
    }
    
    /**
     * Obtiene el tiempo de espera para el envio de los comprobantes pendientes al EPOS.
     * @creado KMONCADA
     * @return
     * @since
     */
    public int getTiempoSleepHilo(){
        //FacadeCPE facade = new FacadeCPE();
        return facade.obtenerTiempoInactividadHilo();
    }
        
    /**
     * 
     * @creado KMONCADA
     * @param pTipoCP
     * @return
     * @throws Exception
     * @since
     */
    public static String getSecuencialImpresora(String pTipoCP)throws Exception{
        return DBCPElectronico.getSecuencialImpresora(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, pTipoCP, FarmaVariables.vIpPc);
    }

    private String getTramaDatos(String pNumPedVta, String pSecCompPago, String pTipCompPago,
                                 String pTipClienteConv) throws Exception {
        
        String tramaEPOS = facade.obtenerTramaCPE(pNumPedVta, pSecCompPago, pTipCompPago, pTipClienteConv);
        return tramaEPOS;
    }
    
    public boolean actualizaComprobantePago(String pNumPedVta, String pSecCompPago, String pCodPDF417, String pTipoCP){
        return facade.actualizaComprobantePago(pNumPedVta, pSecCompPago, pCodPDF417, pTipoCP);
    }
    
    public boolean actualizaComprobantePago_code(String pNumPedVta, String pSecCompPago, String pCodPDF417,
                                                 String pTipoCP,String pTipoCode){
        return facade.actualizaComprobantePago_code(pNumPedVta, pSecCompPago, pCodPDF417, pTipoCP,pTipoCode);
    }
    
    public boolean procesarComprobanteAlEPOS(JDialog pJDialog, String pNumPedVta, String pSecCompPago, String pNumComprobante){
        boolean comprobanteProcesado = false;
        try{
            comprobanteProcesado = comprobanteEnviadoEPOS(pNumPedVta, pSecCompPago);
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "No se pudo determinar si el comprobante de pago electronico "+pNumComprobante+
                                               "\nFue enviado al Servidor EPOS.\n"+
                                               "Comprobante se envia a pendiente de impresión.", null);
            return false;
        }
        
        if(!comprobanteProcesado){
            ArrayList lstComprobantes = UtilityCaja.obtieneComprobantesPago(pNumPedVta, pSecCompPago);
            if(lstComprobantes!=null){
                try{
                    for(int i=0;i<lstComprobantes.size();i++){
                        String secCompPago = ((String)((ArrayList)lstComprobantes.get(i)).get(1)).trim();
                        String tipoCP = ((String)((ArrayList)lstComprobantes.get(i)).get(2)).trim();
                        String tipoClienteConv = ((String)((ArrayList)lstComprobantes.get(i)).get(3)).trim();
                        String nroComprobante = ((String)((ArrayList)lstComprobantes.get(i)).get(4)).trim();
                        String indElectronico = ((String)((ArrayList)lstComprobantes.get(i)).get(5)).trim();
                        
                        if("04".equalsIgnoreCase(tipoCP))
                            tipoClienteConv = "";

                        
                        if(nroComprobante.length()==0){
                            throw new Exception("Comunicación EPOS:\n"+
                                                "Comprobante de Pago no tiene asignado numeración."+"\n"+
                                                "No se puede enviar al EPOS."+"\n"+
                                                "Comuniquese con Mesa de Ayuda!!!.");
                        }else{
                            VariablesCaja.vInd_ImprimeQR=UtilityBarCode.ind_ImprimeQR();
                            System.out.println("IMPRIME CODEQR: "+VariablesCaja.vInd_ImprimeQR);
                            comprobanteProcesado = enviarComprobanteAlEPOS(pNumPedVta, secCompPago, tipoCP, 
                                                                           tipoClienteConv, nroComprobante, false);
                        }
                    }
                }catch(Exception ex){
                    log.error("", ex);
                    comprobanteProcesado = false;
                    FarmaUtility.showMessage(pJDialog, ex.getMessage(), null);
                    
                }
            }else{
                FarmaUtility.showMessage(pJDialog, "Comunicación EPOS:\n"+
                                                   "Error inesperado, al consultar los datos del comprobante de pago "+pNumComprobante+"\n"+
                                                   "Comprobante de pago se envia a pendiente de impresion.", null);
            }
        }
        return comprobanteProcesado;
    }
    
    private boolean enviarComprobanteAlEPOS(String pNumPedVta, String pSecCompPago, String pTipCompPago, String pTipClienteConv, 
                                            String nroComprobanteFV, boolean isComprobanteCredito
                                          ) throws Exception{
        boolean enviadoAlEPOS = false;
        UtilityEposCnx sc = new UtilityEposCnx();
        String tipoCode="PDF417";
        try{
            boolean isValidaCodEstabSUNAT = DBCPElectronico.isConfirmaCodigoEstabSUNAT(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
            if(!isValidaCodEstabSUNAT){
                throw new Exception("EL CODIGO DE ESTABLECIMIENTO DE SUNAT NO HA SIDO CONFIGURADO PARA EL LOCAL.\n"+
                                    "COMUNIQUESE CON MESA DE AYUDA!!!");
            }
            if(!isRegistradoPCEnEpos()){
                throw new Exception("PC/CAJA NO SE ENCUENTRA REGISTRADA EN EL EPOS. "+FarmaVariables.vIpPc+"\n"+
                                    "COMUNIQUESE CON MESA DE AYUDA!!!");
            }
            // dato de reconfirmacion
            boolean enviaReConfirmacion = DBCPElectronico.isEnviaDobleConfirmacionEpos();
            if(!isActivoFuncionalidad()){
                throw new Exception("INDICADOR DE FUNCIONALIDAD DE COMPROBANTES ELECTRONICOS INACTIVO PARA EL LOCAL.\n"+
                                    "COMUNIQUESE CON MESA DE AYUDA!!!");
            }
            // obtiene los datos de conexion
            BeanConexionEPOS conexionEPOS = facade.obtenerDatosConexion(FarmaVariables.vCodCia);
            if(conexionEPOS == null)
                throw new Exception("NO SE OBTUVIERON DATOS DE CONEXION AL SERVIDOR EPOS.");
            
            // obtener trama de comprobante
            String tramaEPOS = getTramaDatos(pNumPedVta, pSecCompPago, pTipCompPago, pTipClienteConv);
            CreateFile(tramaEPOS);
            sc.setDatosConexion(conexionEPOS);
            sc.setIdCajaEpos(UtilityCPE.isDatoValido(UtilityCPE.getIdCajaEpos(),"ID CAJA EN EPOS"));
            sc.setTramaParaEpos(tramaEPOS);
            sc.setTipoDocumentoSunat(pTipCompPago);
            sc.setEnviaReconfirmacion(enviaReConfirmacion);
            sc.procesarComprobanteEnEPOS(nroComprobanteFV);
            String codeImpresion="";
            if(VariablesCaja.vInd_ImprimeQR.equalsIgnoreCase("S")){
                codeImpresion=sc.getCodeQR();
                tipoCode="CodeQR";
            }else{
                codeImpresion=sc.getCodPDF417();
            }
            System.out.println("--> sc.getCodPDF417(): "+codeImpresion);
            if(!actualizaComprobantePago_code(pNumPedVta, pSecCompPago, codeImpresion, pTipCompPago,tipoCode)){
                throw new Exception("Error al actualizar Codigo PDF417 al comprobante de pago.");
            }
            enviadoAlEPOS = true;
        }catch(Exception ex){
            log.error("", ex);
            enviadoAlEPOS = false;
            
            String mensajeEPOS = "";
            if(sc.getRespuestaEPOS() != null){
                mensajeEPOS = "Mensaje Epos: " + sc.getRespuestaEPOS();
            }
            
            String mensajeError = ex.getMessage().replaceAll("\n", "<br>");
          
            String mensajeMail = 
                "Error al enviar comprobante de pago al EPOS<br>" + 
                "NroPedido:" + pNumPedVta + "<br>" + 
                "Sec Comp.Pago: " + pSecCompPago + "<br>" + 
                "TipoCompPago: " + pTipCompPago + "<br>" + 
                "Nro de Comprobante : " + nroComprobanteFV+"<br>"+
                "<b>Mensaje de ERROR : " + mensajeError+"</b><br>"+
                "Mensaje de EPOS : " + mensajeEPOS+"<br>"+
                "Trama: "+sc.getTramaParaEpos()+"<br>"+
                "COD_PDF417: " + sc.getCodPDF417();
            
            FacadeCPE facade = new FacadeCPE();
            facade.enviarCorreo(7, mensajeMail);
            throw new Exception("Comunicación con el Servicio EPOS presente errores\n"+
                                "Comprobante de pago :"+nroComprobanteFV+" enviado a pendiente de impresión\n"+
                                ex.getMessage());
        }
        return enviadoAlEPOS;
    }
    
    
    public void CreateFile(String trama) {
           String nombre;
           nombre = JOptionPane.showInputDialog("¿Tipo de Venta?");
           String name = nombre + ".txt";
           File file = new File("D:\\Documentacion3.0\\FacturacionElectronica\\TRAMAS\\FARMAVENTA\\" + name);
           try (PrintWriter out = new PrintWriter(file)) {
               out.println(trama);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    /**
     *
     * @creado KMONCADA
     * @param pNumPedVta
     * @param pSecCompPago
     * @return
     * @since
     */
    public boolean comprobanteEnviadoEPOS(String pNumPedVta, String pSecCompPago)throws Exception{
        boolean enviado = false;
        String rspta = DBCPElectronico.comprobanteEnviadoEPOS(FarmaVariables.vCodGrupoCia, 
                                                              FarmaVariables.vCodLocal, 
                                                              pNumPedVta, 
                                                              pSecCompPago);
        enviado = "S".equalsIgnoreCase(rspta);
        return enviado;
    }
    
    public void verificarEstadoServicioEPOS(){
        boolean isOperativo = true;
        int tiempoTimeOut = 0;
        try{
            tiempoTimeOut = DBCPElectronico.obtenerTiempoTimeOutEPOS();
        }catch(Exception ex){
            tiempoTimeOut = 15;
        }
        isOperativo = TestEPOS.realizarTelnet(beanConexion.getIpServidor(), beanConexion.getPuerto(), tiempoTimeOut);
        setEstaContigenciaEPOS(!isOperativo);
    }

    public static boolean isEstaContigenciaEPOS() {
        return estaContigenciaEPOS;
    }

    private void setEstaContigenciaEPOS(boolean contigenciaEPOS) {
        UtilityCPE.estaContigenciaEPOS = contigenciaEPOS;
    }

    public static boolean isVolverEjecutar() {
        return volverEjecutar;
    }

    public static void setVolverEjecutar(boolean volverEjecutar) {
        UtilityCPE.volverEjecutar = volverEjecutar;
    }
}
