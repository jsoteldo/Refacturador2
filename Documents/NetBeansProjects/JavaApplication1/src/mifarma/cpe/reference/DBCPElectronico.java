package mifarma.cpe.reference;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.electronico.ElectronicoException;
import mifarma.electronico.impresion.dao.ConstantesDocElectronico;

import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;

import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBCPElectronico {

    private static final Logger log = LoggerFactory.getLogger(DBCPElectronico.class);

    public static String getSecuencialImpresora(String pCodGrupoCia, String pCodLocal, String pTipoCP, String pIpPc)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pTipoCP);
        parametros.add(pIpPc);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_GET_SEC_IMPR_ELETRONICO(?,?,?,?)", parametros);
    }
    
    public static List getDatosImpresion(String pVersionFV, String numPedidoVta, String secCompPago,
                                         boolean isReimpresion) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numPedidoVta);
        parametros.add(secCompPago);
        parametros.add(pVersionFV);
        if (isReimpresion)
            parametros.add("S");
        else
            parametros.add("N");
        if(VariablesPuntos.frmPuntos!=null){ //validacion cuando el objete no fue construido ltavara 01/04/2015
            parametros.add((VariablesPuntos.frmPuntos.getBeanTarjeta() == null) ? 0 : VariablesPuntos.frmPuntos.getBeanTarjeta().getAhorroTotal());    //ASOSA - 24/03/2015 - PTOSYAYAYAYA - CONSECUENCIAS DE REPETIDOS CAMBIOS.
        }else{
            parametros.add(0);    //ASOSA - 24/03/2015 - PTOSYAYAYAYA - CONSECUENCIAS DE REPETIDOS CAMBIOS.
        }
        //KMONCADA 23.06.2015 NUMERO DE DOCUMENTO DE LA TARJETA DE PUNTOS
        if(VariablesPuntos.frmPuntos != null){
            if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                if(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni()!=null){
                    parametros.add(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni());
                }else{
                    parametros.add(" ");
                }
            }else{
                parametros.add(" ");
            }
        }else{
            parametros.add(" ");
        }
        log.info("FARMA_EPOS.IMP_COMP_ELECT(?,?,?,?,?,?,?,?)" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_EPOS.IMP_COMP_ELECT(?,?,?,?,?,?,?,?)", parametros);

    }

    public static String getMarcaLocal(String pCodGrupoCia, String pCodLocal) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        log.info("FARMA_EPOS.GET_MARCA_LOCAL(?,?)" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.GET_MARCA_LOCAL(?,?)", parametros);
    }
    
    public static String getPCRegistradaEnEpos(String pCodGrupoCia, String pCodLocal) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        String result = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VALIDA_REGISTRO_CAJA_EPOS(?,?,?)", parametros);
        return result;
    }
    
    /* public static String getIndElectronicoLocal() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodLocal);
        String result = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_IS_ACT_FUNC_E(?,?,?)", parametros);
        return result;
    } */
    
    /*public static int getTiempoInactivadadHiloEpos() {
        try{
            ArrayList parametros = new ArrayList();
            String result = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_GET_TIMPO_ESPERA_HILO_EPOS", parametros);
            return FarmaUtility.trunc(result);
        }catch(Exception ex){
            log.error("", ex);
            return 10;
        }
        
    }*/
    
    public static void enviarCorreoErrorEPOS(String pCodGrupoCia, String pCodLocal, int pTipoMensaje, String pContenidoMsj){
        try{
            ArrayList parametros = new ArrayList();
            parametros.add(pCodGrupoCia);
            parametros.add(pCodLocal);
            parametros.add(pTipoMensaje);
            parametros.add(pContenidoMsj);
            FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_EPOS.F_ENVIA_MAIL_ERROR(?,?,?,?)", parametros, false);
        }catch(Exception ex){
            log.error("", ex);
        }
    }
    
    public static String comprobanteEnviadoEPOS(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pSecCompPago);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_COMP_PAGO_ENVIDO_EPOS(?,?,?,?)", parametros);
    }
    
    public static void actualizaFlgElectronico(String numPedidoVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numPedidoVta);
        String result =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_UPD_FLAG_COMP_E(?,?,?)", parametros);


    }
    
    public static boolean isEnviaDobleConfirmacionEpos()throws Exception{
        ArrayList parametros = new ArrayList();
        log.info("FARMA_EPOS.F_GET_ENVIO_RECONFIRMA_EPOS :" + parametros);
        String indicador = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_GET_ENVIO_RECONFIRMA_EPOS", parametros);
        if ("S".equalsIgnoreCase(indicador)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isConfirmaCodigoEstabSUNAT(String pCodGrupoCia, String pCodLocal)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        System.out.println("FARMA_EPOS.F_GET_CONFIRMA_ESTAB_SUNAT(?,?) :" + parametros);
        String indicador = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_GET_CONFIRMA_ESTAB_SUNAT(?,?)", parametros);
        if ("S".equalsIgnoreCase(indicador)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isComprobanteElectronico(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pSecCompPago);
        log.info("PTOVENTA_CAJ.F_CHAR_IND_COMP_ELECTRONICO(?,?,?,?) :" + parametros);
        String indicador = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CAJ.F_CHAR_IND_COMP_ELECTRONICO(?,?,?,?)", parametros);
        if (ConstantesDocElectronico.IND_COMPROBANTE_ELECTRONICO.equals(indicador)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static Map obtieneMsgContingencia(String pCodGrupoCia, String pCodLocal, String pNumPedVta,
                                             String pCodTipoComprobante) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pCodTipoComprobante);

        log.info("FARMA_EPOS.F_MSJ_CONTIGENCIA(?,?,?,?) :" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureMap("FARMA_EPOS.F_MSJ_CONTIGENCIA(?,?,?,?)", parametros);
    }
    
    public static Map validaImpresionPendiente(String pCodGrupoCia, String pCodLocal, String pSecCompPago,
                                               String pCodTipoComprobante, String pNumPedVta) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pSecCompPago);
        parametros.add(pCodTipoComprobante);

        log.info("FARMA_EPOS.VALIDA_IMPRESION_PENDIENTE(?,?,?,?,?) :" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureMap("FARMA_EPOS.VALIDA_IMPRESION_PENDIENTE(?,?,?,?,?)",
                                                           parametros);
    }
    
    public static void cambiarComprobanteManual(String pCodGrupoCia, 
                                                String pCodLocal, 
                                                String pNumPedVta,
                                                String pSecCompPago,
                                                boolean isElectronico) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pSecCompPago);
        if(isElectronico)
            parametros.add("1");
        else
            parametros.add("0");
        log.info("FARMA_EPOS.COMP_PAGO_CAMBIA_FLAG_E(?,?,?,?,?) :" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_EPOS.COMP_PAGO_CAMBIA_FLAG_E(?,?,?,?,?)", parametros,
                                                 false);
    }
    
    public static String getDocumentoElectronico(String cGrupoCia, String cCodLocal, String cNumPedidoVta,
                                                 String cSecCompPago, String cTipoDocumento,
                                                 String cTipoClienteConvenio) throws Exception {
        ArrayList vParameters = new ArrayList();
        vParameters.add(cGrupoCia);
        vParameters.add(cCodLocal);
        vParameters.add(cNumPedidoVta);
        vParameters.add(cSecCompPago);
        vParameters.add(cTipoDocumento);
        vParameters.add(cTipoClienteConvenio);

        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_CAB(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_DOC(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_NOTAS(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_DET(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_RG(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_IG(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_REF(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_MSJ_BF(?,?,?,?,?,?); end;" + vParameters);
        log.info("begin ? := FARMA_EPOS.F_VAR_TRM_GET_MSJ_AF(?,?,?,?,?,?); end;" + vParameters);

        String pUNO_Cab =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_CAB(?,?,?,?,?,?)", vParameters);
        String pDOS_Doc =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_DOC(?,?,?,?,?,?)", vParameters);
        String pTRES_Not =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_NOTAS(?,?,?,?,?,?)", vParameters);
        //String pCUATRO_Det = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_DET(?,?,?,?,?,?)", vParameters);

        String pCUATRO_Det = "";
        List lstDetalle =
            FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_EPOS.F_VAR_TRM_GET_DET(?,?,?,?,?,?)", vParameters);
        if (lstDetalle.size() == 0) {
            throw new ElectronicoException("Trama sin Detalle");
        } else {
            Map mapDetalle = new HashMap();
            String valor;
            for (int i = 0; i < lstDetalle.size(); i++) {
                mapDetalle = (HashMap)lstDetalle.get(i);
                valor = (String)mapDetalle.get("VALOR");
                pCUATRO_Det = pCUATRO_Det + valor + "\n";
            }
        }

        // NO SE USARA PORQUE YA EXISTE UN TAG NUEVO DE ANTICIPO
        // PP >> pOCHO_PP
        /*
        String pCINCO_RG =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_RG(?,?,?,?,?,?)", vParameters);
        */
        String pSEIS_IG =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_IG(?,?,?,?,?,?)", vParameters);
        String pSIETE_REF =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_REF(?,?,?,?,?,?)", vParameters);
        String pOCHO_PP =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_PP(?,?,?,?,?,?)", vParameters);

        String pNUEVE_MSJ_BF =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_MSJ_BF(?,?,?,?,?,?)", vParameters);
        String pDIEZ_MSJ_AF =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_MSJ_AF(?,?,?,?,?,?)", vParameters);
        // LTAVARA 17.11.2014 MENSAJES PERZONALIZADOS A LA TRAMA
        String pONCE_MSJ_PERZONALIZADO =
            FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_EPOS.F_VAR_TRM_GET_MSJ_PERSONALIZA(?,?,?,?,?,?)",
                                                        vParameters);

        if (pUNO_Cab != null)
            log.info(" pUNO_Cab  " + pUNO_Cab.trim().length());
        if (pDOS_Doc != null)
            log.info(" pDOS_Doc  " + pDOS_Doc.trim().length());
        if (pTRES_Not != null)
            log.info(" pTRES_Not  " + pTRES_Not.trim().length());
        if (pCUATRO_Det != null)
            log.info(" pCUATRO_Det  " + pCUATRO_Det.trim().length());
        //
        //if (pCINCO_RG != null)
        //    log.info(" pCINCO_RG  " + pCINCO_RG.trim().length());
        if (pSEIS_IG != null)
            log.info(" pSEIS_IG  " + pSEIS_IG.trim().length());
        if (pSIETE_REF != null)
            log.info(" pSIETE_REF  " + pSIETE_REF.trim().length());
        //if (pOCHO_PP != null)
        //    log.info(" pOCHO_MSJ_BF  " + pOCHO_PP.trim().length());
        if (pNUEVE_MSJ_BF != null)
            log.info(" pNUEVE_MSJ_BF  " + pNUEVE_MSJ_BF.trim().length());
        if (pDIEZ_MSJ_AF != null)
            log.info(" pNUEVE_MSJ_AF  " + pDIEZ_MSJ_AF.trim().length());
        // LTAVARA 17.11.2014 MENSAJES PERZONALIZADOS A LA TRAMA
        if (pONCE_MSJ_PERZONALIZADO != null)
            log.info(" pONCE_MSJ_PERZONALIZA  " + pONCE_MSJ_PERZONALIZADO.trim().length());

        if (pUNO_Cab == null || pUNO_Cab.trim().length() == 0)
            throw new ElectronicoException("SIN DATOS trama de Cabecera");
        if (pDOS_Doc == null || pDOS_Doc.trim().length() == 0)
            throw new ElectronicoException("SIN DATOS trama de Documento");
        if (pTRES_Not == null || pTRES_Not.trim().length() == 0)
            throw new ElectronicoException("SIN DATOS trama de Nota");
        if (pCUATRO_Det == null || pCUATRO_Det.trim().length() == 0)
            throw new ElectronicoException("SIN DATOS trama de Detalle");

        //if (pCINCO_RG == null || pCINCO_RG.trim().length() == 0)
        //    pCINCO_RG = ""; //throw new Exception("SIN DATOS trama de Recargo Global");
        if (pSIETE_REF == null || pSIETE_REF.trim().length() == 0)
            pSIETE_REF = ""; // throw new Exception("SIN DATOS trama de Referencia");

        if (pOCHO_PP == null || pOCHO_PP.trim().length() == 0)
            pOCHO_PP = ""; // throw new Exception("SIN DATOS trama de Referencia");

        if (pNUEVE_MSJ_BF == null || pNUEVE_MSJ_BF.trim().length() == 0)
            throw new ElectronicoException("SIN DATOS trama de Mensaje Antes");
        if (pDIEZ_MSJ_AF == null || pDIEZ_MSJ_AF.trim().length() == 0)
            throw new ElectronicoException("SIN DATOS trama de Mensaje Despues");

        return pUNO_Cab + pDOS_Doc + pTRES_Not + pCUATRO_Det +
            //pCINCO_RG +
            pSEIS_IG + pSIETE_REF + /*+ pOCHO_PP*/pNUEVE_MSJ_BF + pDIEZ_MSJ_AF + pONCE_MSJ_PERZONALIZADO;

    }
    
    public static int obtenerTiempoTimeOutEPOS()throws Exception{
        ArrayList parametros = new ArrayList();
        return FarmaDBUtility.executeSQLStoredProcedureInt("FARMA_EPOS.F_GET_TIME_OUT_EPOS", parametros);
    }
}
