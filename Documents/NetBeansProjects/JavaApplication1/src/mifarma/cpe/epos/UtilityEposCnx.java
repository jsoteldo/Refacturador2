package mifarma.cpe.epos;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.net.Socket;

import mifarma.common.FarmaUtility;

import mifarma.cpe.UtilityCPE;
import mifarma.cpe.modelo.BeanConexionEPOS;

import mifarma.ptoventa.caja.reference.VariablesCaja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityEposCnx {
    static private final Logger log = LoggerFactory.getLogger(UtilityEposCnx.class);
    boolean muestraTiempos = false; // cambiarla a TRUE para que muestre el LOG.
    private String tipoDocumentoSunat = "";
    private String tramaParaEpos;
   
    // DATOS SERVIDOR EPOS
    private String ipServidorEpos;
    private int puertoEpos;
    private String modoEpos;
    private String nroRUCCIA;
    private String codLocal;
    private String nroComprobanteEPOS;
    private String codPDF417 = null;
    private String codeQR=null;
    // DATOS CAJA
    private String idCajaEpos;
    
   
    private final String COD_DOC_FV_BOL = "01";
    private final String COD_DOC_FV_FAC = "02";
    private final String COD_DOC_FV_NCR = "04";
    private final String COD_DOC_FV_TKB = "05";
    private final String COD_DOC_FV_TKF = "06";
    private final String COD_DOC_FV_GRL = "03";
    
    private final String COD_DOC_SUNAT_BOL = "3";
    private final String COD_DOC_SUNAT_FAC = "1";
    private final String COD_DOC_SUNAT_NCR = "7";
    
    private final String MSJ_EPOS_CONFIGURACION = "1";
    private final String MSJ_EPOS_GENERA_DE = "2";
    private final String MSJ_EPOS_CONFIRMAR_DE = "3";
    private final String MSJ_EPOS_RECONFIRMAR_DE = "10";
    
    private boolean enviaReconfirmacion;
    private String respuestaEPOS;
    /**
     *
     * @param beanConexion
     */
    public UtilityEposCnx(){
    }
    
    /**
     *
     * @param mensajeEnvio
     * @return
     * @throws Exception
     */
    private String comunicacionEPOS(String mensajeEnvio)throws Exception{
        
        Socket socketServidor = null;
        PrintStream escribir = null;
        BufferedReader leer = null;
        String respuestaMsj = "";
        try{
            //Crear conexion con el socket servidor
            try {
                socketServidor = new Socket(getIpServidorEpos(), getPuertoEpos());
            } catch (Exception ex) {
                UtilityCPE.setVolverEjecutar(true);
                throw new Exception("[EPOS] NO SE ESTABLECIO CONEXION CON SERVIDOR EPOS");
            }
            //Crear referencias al canal de escritura
            //escribir = new PrintStream(socketServidor.getOutputStream());
            //LTAVARA 2017.04.06 la trama enviada con formato UTF-8
            escribir = new PrintStream(socketServidor.getOutputStream(), true,"UTF-8");
            //
            //Crear referencias al canal de lectura
            leer = new BufferedReader(new InputStreamReader(socketServidor.getInputStream()));
            //Envia el mensaje por el canal de escritura
            try{
                escribir.append(mensajeEnvio);
            } catch (Exception ex) {
                throw new Exception("[EPOS] ERROR DE COMUNICACION I/O CON SERVIDOR EPOS");
            }
            
            //Espero la respuesta por el canal de lectura
            try{
                InputStream in = socketServidor.getInputStream();
                byte[] b = new byte[1024];
                int data_size = in.read(b);
                for (int i = 0; i < data_size; i++) {
                    String pParte = String.valueOf((char)b[i]);
                    respuestaMsj += pParte;
                }
            } catch (Exception ex) {
                throw new Exception("[EPOS] NO SE PUDO OBTENER RESPUESTA DEL SERVIDOR EPOS.");
            }
            
            if (respuestaMsj == null || (respuestaMsj!=null && respuestaMsj.trim().length()==0)) {
                throw new Exception("[EPOS] Lectura de Respuesta: Mensaje de respuesta vacio");
            }
            //Cerrar las conexiones
            escribir.close();
            leer.close();
            socketServidor.close();
        }finally{
            if(socketServidor!=null){
                //Cerrar las conexiones
                if(!socketServidor.isClosed()){
                    escribir.close();
                    leer.close();
                    socketServidor.close();
                }
            }
        }
        return respuestaMsj;
    }

    /**
     * Método que genera el mensaje de configuración para que el punto de venta pueda emitir comprobantes electrónicos
     * @return respuesta Define repuesta que da el EPOS cuando se envia el mensaje de configuración.
     */
    public boolean registroPCEnEPOS() throws Exception {
        String mensajeEnvio = "@**@" + 
                              MSJ_EPOS_CONFIGURACION + "\t" + 
                              getModoEpos() + "\t" + //modo ejecucion: 1- prueba, <> 1 -produccion
                              getNroRUCCIA() + "\t" + //ruc del emisor
                              getIdCajaEpos() + "\t" + //identificadorPos - identificador pos
                              getCodLocal() + "*@@*";
        
        String rsptaEPOS = comunicacionEPOS(mensajeEnvio);
        boolean isCorrecto = getFormatRpta(rsptaEPOS, MSJ_EPOS_CONFIGURACION);
        return isCorrecto;
    }
    
    
    /**
     * Método que genera el mensaje generarDE para enviar los datos del documento electronico al EPOS y genere el xml del comprobante.
     * @return respuesta          Define repuesta que da el EPOS cuando se envia el mensaje de GenerarDE.
     */
    public void enviarCompPagoAEPOS() throws Exception {
        log.info("TRAMA DE ENVIO-->\n"+
                 "Comprobante de pago --> "+getNroComprobanteEPOS()+"\n"+
                 getTramaParaEpos()); 
        String mensajeEnvio = "@**@" + 
                              MSJ_EPOS_GENERA_DE + "\t" + //Indentificador mensaje
                              getModoEpos() + "\t" + //modo ejecucion: 1- prueba, <> 1 -produccion
                              getNroRUCCIA() + "\t" + //ruc del emisor
                              getIdCajaEpos() + "\t" + //identificador pos
                              getTipoDocumentoSunat() + "\t" + //tipo documento
                              getTramaParaEpos() + "*@@*";
        System.out.println("==> mensajeEnvio "+mensajeEnvio);
        String rsptaEPOS = comunicacionEPOS(mensajeEnvio);
        System.out.println("==> rsptaEPOS "+rsptaEPOS);
        getFormatRpta(rsptaEPOS, MSJ_EPOS_GENERA_DE);
    }

    public void enviaMsjConfirmacionEPOS()throws Exception{
        sentMsjConfirmacion(MSJ_EPOS_CONFIRMAR_DE);
    }
    
    public void enviaMsjREConfirmacionEPOS()throws Exception{
        sentMsjConfirmacion(MSJ_EPOS_RECONFIRMAR_DE);
    }
    
    /**
     * Método que genera el mensaje ConfirmacionDE para enviar los datos del documento electronico al EPOS y  obtener las serie-correlativo y PDF417
     * @param tipoMensaje       Define el número del pedido del comprobante;
     * @return respuesta          Define repuesta que da el EPOS cuando se envia el mensaje de ConfirmacionDE.
     */
    private void sentMsjConfirmacion(String tipoMensaje) throws Exception {
        String nroFormatoEPOS = "";
        nroFormatoEPOS = getNroComprobanteEPOS().substring(0, 4) + "-" + getNroComprobanteEPOS().substring(4, getNroComprobanteEPOS().length());
        String mensajeEnvio = "@**@" + 
                              tipoMensaje + "\t" + //Indentificador mensaje
                              getModoEpos() + "\t" + //modo ejecucion: 1- prueba, <> 1 -produccion
                              getNroRUCCIA() + "\t" + //ruc del emisor
                              getIdCajaEpos() + "\t" + //identificador pos
                              getTipoDocumentoSunat() + "\t" + //identificador pos
                              nroFormatoEPOS + "*@@*";
        String rsptaEPOS = comunicacionEPOS(mensajeEnvio);
        getFormatRpta(rsptaEPOS, tipoMensaje);
    }    

    /**
     * Método que transforma la cadena a la repuesta depende del tipo de mensaje.
     * @param cadena         Define cadena a transformar;
     * @param tipoMensaje    Define el tipo de mensaje;
     * @return respuesta     Define repuesta transformada.
     */
    private boolean getFormatRpta(String cadena, String tipoMensaje) throws Exception {
        Boolean tieneCaracterInicio = cadena.startsWith("\u0002");
        String[] listaRespuesta;
        System.out.println("=> cadena: "+cadena);
        if (tieneCaracterInicio) {
            String repuestaFormatado;
            repuestaFormatado = cadena.substring(1, cadena.indexOf("\u0003"));
            System.out.println("=> repuestaFormatado: "+repuestaFormatado);
            listaRespuesta = repuestaFormatado.split("\t");
        } else {
            listaRespuesta = cadena.split("\t");
        }
        
        String msjEPOS = "";
        System.out.println("------------------------------------------------");
        for(int i=0;i<listaRespuesta.length;i++){
            System.out.println("====> PDF415?  "+listaRespuesta[i]);
            msjEPOS = msjEPOS + "|"+listaRespuesta[i];
        }
        System.out.println("------------------------------------------------");
        setRespuestaEPOS(msjEPOS);
        // MENSAJE DE EXITO //
        if (listaRespuesta[0].toString().equals("0")) { //Mensaje de exito
            switch (tipoMensaje) {
                case MSJ_EPOS_CONFIGURACION://EposConstante.tipoMensaje.CONFIGURACION:
                    //return MSJ_EXITO + "|Exito - Configuración con el EPOS activa.";
                    break;
                case MSJ_EPOS_GENERA_DE://EposConstante.tipoMensaje.GENERA_DE:
                    setNroComprobanteEPOS(darFormatoNumCPE(listaRespuesta[2].toString()));
                    if(VariablesCaja.vInd_ImprimeQR.equalsIgnoreCase("S")){
                        /*int index=listaRespuesta[3].toString().lastIndexOf("|");
                        String aux=listaRespuesta[3].toString().substring(0,index);
                        index=aux.lastIndexOf("|");
                        String codeQR=aux.substring(0,index);
                        codeQR=aux.substring(0,index);
                        setCodeQR(codeQR);*/
                        setCodeQR(listaRespuesta[3].toString());
                    }else{
                        setCodPDF417(listaRespuesta[3].toString());
                    }
                    break;
                case MSJ_EPOS_CONFIRMAR_DE: //return MSJ_EXITO + "|Exito - Se envio el DE correctamente a SUNAT.";
                    break;
                case MSJ_EPOS_RECONFIRMAR_DE: //return MSJ_EXITO + "|Exito - Se envio el DE correctamente a SUNAT.";
                    break;
            }
        } else {
            String mensajeError = "";
            switch (tipoMensaje) {
                case MSJ_EPOS_CONFIGURACION:
                    mensajeError = "[EPOS - CONFIGURACION CAJA] TRAMA DE RESPUESTA CON ERROR : \n" + msjEPOS;
                    break;
                case MSJ_EPOS_GENERA_DE:
                    mensajeError = "[EPOS - ENVIO COMPROBANTE] TRAMA DE RESPUESTA CON ERROR : \n" + msjEPOS;
                    break;
                case MSJ_EPOS_CONFIRMAR_DE: 
                    mensajeError = "[EPOS - CONFIRMAR CP] TRAMA DE RESPUESTA CON ERROR : \n" + msjEPOS;
                    break;
                case MSJ_EPOS_RECONFIRMAR_DE:
                    mensajeError = "[EPOS - RECONFIRMAR CP] TRAMA DE RESPUESTA CON ERROR : \n" + msjEPOS;
                    break;
            }
            throw new Exception(mensajeError);
        }

        return true;
    }

    private String darFormatoNumCPE(String numCompE) throws Exception {
        String serie = numCompE.substring(0,numCompE.indexOf("-"));
        String correlativo = numCompE.substring(numCompE.indexOf("-")+1);
        correlativo = FarmaUtility.completeWithSymbol(correlativo, 8, "0", "I");
        return serie+correlativo;
    }


    public String getTramaParaEpos() {
        return this.tramaParaEpos;
    }
    
    public void setTramaParaEpos(String tramaParaEpos) {
        this.tramaParaEpos = tramaParaEpos;
    }

    public String getNroComprobanteEPOS() {
        return nroComprobanteEPOS;
    }

    public void setIdCajaEpos(String idCajaEpos)throws Exception{
        
        this.idCajaEpos = idCajaEpos;
    }

    public String getIdCajaEpos() {
        return idCajaEpos;
    }

    public void setTipoDocumentoSunat(String tipoDocumentoFV) throws Exception{
        tipoDocumentoSunat = "";
        if (tipoDocumentoFV.equals(COD_DOC_FV_BOL) ||
            tipoDocumentoFV.equals(COD_DOC_FV_TKB)) { //Boleta
            tipoDocumentoSunat = COD_DOC_SUNAT_BOL;
        } else if (tipoDocumentoFV.equals(COD_DOC_FV_FAC) ||
                   tipoDocumentoFV.equals(COD_DOC_FV_TKF)) { //Factura
            tipoDocumentoSunat = COD_DOC_SUNAT_FAC;
        } else if (tipoDocumentoFV.equals(COD_DOC_FV_NCR)) { //Nota de credito
            tipoDocumentoSunat = COD_DOC_SUNAT_NCR;
        }
        if (tipoDocumentoSunat.trim().length() == 0){
            throw new Exception("[ERROR EPOS - GENERA COMPROBANTE] El Tipo Documento SUNAT es NULO.");
        }
    }

    public String getTipoDocumentoSunat() {
        return tipoDocumentoSunat;
    }

    public String getNroRUCCIA() {
        return nroRUCCIA;
    }

    public void setNroRUCCIA(String nroRUCCIA) {
        this.nroRUCCIA = nroRUCCIA;
    }

    public String getModoEpos() {
        return modoEpos;
    }

    public void setModoEpos(String modoEpos) {
        this.modoEpos = modoEpos;
    }

    public String getIpServidorEpos() {
        return ipServidorEpos;
    }

    public void setIpServidorEpos(String ipServidorEpos) {
        this.ipServidorEpos = ipServidorEpos;
    }

    public int getPuertoEpos() {
        return puertoEpos;
    }

    public void setPuertoEpos(int puertoEpos) {
        this.puertoEpos = puertoEpos;
    }

    public void setNroComprobanteEPOS(String nroComprobanteEPOS) {
        this.nroComprobanteEPOS = nroComprobanteEPOS;
    }
    
    public String getCodPDF417() {
        return codPDF417;
    }
    
    public void setCodPDF417(String codPDF417) {
        this.codPDF417 = codPDF417;
    }
    
    public String getCodeQR() {
        return codeQR;
    }
    
    public void setCodeQR(String codPDF417) {
        this.codeQR = codPDF417;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }
    
    public void procesarComprobanteEnEPOS(String pNroComprobanteFV)throws Exception{
        /** 1. Envia trama de CP al EPOS. **/
        enviarCompPagoAEPOS();
        /** 2. valida que EPOS funcione o recepcione el nro del FV **/
        if(!pNroComprobanteFV.equalsIgnoreCase(getNroComprobanteEPOS()))
            throw new Exception("El Número de Comprobante electrónico : " + pNroComprobanteFV +"\n" +
                                " asignado por FV, no coincide con el indicado por el EPOS: "+getNroComprobanteEPOS());
        
        /** 3. envia mensaje de confirmacion del nro CP. **/
        enviaMsjConfirmacionEPOS();
            
        /** 4. en caso de que EPOS espere reconfirmacion **/
        if(isEnviaReconfirmacion())
            enviaMsjREConfirmacionEPOS();
    }

    public boolean isEnviaReconfirmacion() {
        return enviaReconfirmacion;
    }

    public void setEnviaReconfirmacion(boolean enviaReconfirmacion) {
        this.enviaReconfirmacion = enviaReconfirmacion;
    }

    public String getRespuestaEPOS() {
        return respuestaEPOS;
    }

    public void setRespuestaEPOS(String respuestaEPOS) {
        this.respuestaEPOS = respuestaEPOS;
    }

    public void setDatosConexion(BeanConexionEPOS beanConexion) {
        this.setIpServidorEpos(beanConexion.getIpServidor());
        this.setPuertoEpos(beanConexion.getPuerto());
        this.setModoEpos(beanConexion.getModo());
        this.setNroRUCCIA(beanConexion.getRucCia());
        this.setCodLocal(beanConexion.getCodLocal());
    }

}
