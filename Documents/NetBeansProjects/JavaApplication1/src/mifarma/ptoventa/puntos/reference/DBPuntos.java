package mifarma.ptoventa.puntos.reference;

import farmapuntos.bean.BeanOperacion;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.fidelizacion.reference.OperacionDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBPuntos {

    private static ArrayList parametros;
    private static final Logger log = LoggerFactory.getLogger(DBPuntos.class);


    public DBPuntos() {
    }


    public static String getCodAutorizacion() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("FARMA_PUNTOS.F_VAR_KEY_ORBIS(?,?)"+ parametros);                        
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_COD_AUTORIZACION(?,?)", parametros);
        return pRes.trim();
    }
    public static String getWsOrbis() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("FARMA_PUNTOS.F_VAR_KEY_ORBIS(?,?)"+ parametros);                        
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_WS_ORBIS(?,?)", parametros);
        return pRes.trim();
    }
    public static String getIndActPuntos() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("FARMA_PUNTOS.F_VAR_IND_ACT_PUNTOS(?,?)"+ parametros);                
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IND_ACT_PUNTOS(?,?)", parametros);
        return pRes.trim();
    }
    
    public static String isTarjetaValida(String pTarjeta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        if(pTarjeta!=null)
            parametros.add(pTarjeta);
        else
            parametros.add("");
        log.info("FARMA_PUNTOS.F_VAR_TARJ_VALIDA(?,?,?)"+parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_TARJ_VALIDA(?,?,?)", parametros);
        return pRes.trim();
    }    
    
    public static String getDniUsuario() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vNuSecUsu);
        log.info("FARMA_PUNTOS.F_DNI_USU_LOCAL(?,?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_DNI_USU_LOCAL(?,?,?)", parametros);
        return pRes.trim();
    }

    public static boolean getIndImpLogo() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("FARMA_PUNTOS.F_VAR_IND_IMPR_LOGO(?,?)"+ parametros);        
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IND_IMPR_LOGO(?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }    
    
    
    public static List getDatosImprSaldo(String pTarjeta,String pNombreCompleto,double vPuntosAcumulados) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pTarjeta);
        parametros.add(pNombreCompleto);
        parametros.add(FarmaUtility.formatNumber(vPuntosAcumulados));
        List pRes = new ArrayList();
        log.info("FARMA_PUNTOS.IMP_SALDO_TARJETA(?,?,?,?,?)"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.IMP_SALDO_TARJETA(?,?,?,?,?)",parametros);
        return pRes;
    }    

    public static List getDatosImprRecuperaPuntos(String pTarjeta,String pNombreCompleto,String pDNI,double vPuntosAcumulados,
                                         boolean vIsOnline,double pPtoSaldoAct,String pNumPedVta, String pListaProgramas
                                         ) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        if(pTarjeta==null)
            log.info("es null la tarjeta");
        else
        parametros.add(pTarjeta);
        if(pNombreCompleto==null)
            log.info("es null la tarjeta");
        else        
        parametros.add(pNombreCompleto);
        if(pDNI==null)
            log.info("es null la tarjeta");
        else        
        parametros.add(pDNI);
        parametros.add(FarmaUtility.formatNumber(vPuntosAcumulados));
        
        if(vIsOnline)
            parametros.add(FarmaConstants.INDICADOR_S);
        else
            parametros.add(FarmaConstants.INDICADOR_N);
        
        
        parametros.add(FarmaUtility.formatNumber(pPtoSaldoAct));
        parametros.add(pNumPedVta.trim());
        parametros.add(pListaProgramas);
        
        List pRes = new ArrayList();
        log.info("FARMA_PUNTOS.IMP_RECUPERA_PUNTOS(?,?,?,?,?,?,?,')"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.IMP_RECUPERA_PUNTOS(?,?,?,?,?,?,?,?,?,?)",parametros);
        return pRes;
    }    
        

    public static String getBuscaValidaPed_Recupera(String pTipoComp, String pMontoNeto,
                                                   String pNumCompPago,String pFecha, String pCodTarjeta) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pTipoComp);
        parametros.add(pMontoNeto);
        parametros.add(pNumCompPago);
        parametros.add(pFecha);
        parametros.add(pCodTarjeta);        
        log.debug("parametros FARMA_PUNTOS.F_GET_PERMITE_RECUPERA(?,?,?,?,?,?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_GET_PERMITE_RECUPERA(?,?,?,?,?,?,?)",
                                                           parametros);
    }    

    public static void pRecuperaPuntos(String pTarjeta,String pNumPedVta,boolean pOnline,String idTrx,String nNumAutorizacion) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pTarjeta);
        if(pOnline)
            parametros.add(FarmaConstants.INDICADOR_S);
        else
            parametros.add(FarmaConstants.INDICADOR_N);
        parametros.add(idTrx);
        if(nNumAutorizacion!=null)
        parametros.add(nNumAutorizacion);    
        else
            parametros.add(" ");    
        log.info("FARMA_PUNTOS.P_ASOCIA_TARJ_PED(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"FARMA_PUNTOS.P_ASOCIA_TARJ_PED(?,?,?,?,?,?,?)", parametros,false);
    }   
    
    public static boolean getIndPedidoAsocProdMasUno(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_PED_ASOC_X_MAS_1(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_PED_ASOC_X_MAS_1(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

    public static List getPedAsocTarjeta(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        log.info("FARMA_PUNTOS.F_CUR_PED_ASOC_TARJ_PED(?,?,?,?)"+parametros);
        List pLista = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.F_CUR_PED_ASOC_TARJ_PED(?,?,?)", parametros);
        return pLista ;
    }


    public static boolean getIndCobroProcesoOrbis(String pNumPedVta)  throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_PED_PROC_ORBIS(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_PED_PROC_ORBIS(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

    public static boolean getIndPedidoRedimio(String pNumPedVta)  throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_PED_REDIMIO(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_PED_REDIMIO(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

   public static boolean getIndPuntosAcum(String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_PED_PUNTOS_ACUM(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_PED_PUNTOS_ACUM(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

    public static void pRevierteAcumulaPuntos(String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        log.info("FARMA_PUNTOS.P_REVERT_ACUM_PUNTOS(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"FARMA_PUNTOS.P_REVERT_ACUM_PUNTOS(?,?,?)", parametros,false);        
    }
    
    public static void pGrabaProcesoAnulaOrbis(String pNumPedVtaOriginal,boolean pAnuloOrbis,String vIdTrxAnula,String vNroAutorizaAnula) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVtaOriginal);
        if(pAnuloOrbis)
        parametros.add(FarmaConstants.INDICADOR_S);
        else
        parametros.add(FarmaConstants.INDICADOR_N);
        parametros.add(vIdTrxAnula.trim());
        parametros.add(vNroAutorizaAnula.trim());
        log.info("FARMA_PUNTOS.P_SAVE_TRX_ANULA_ORBIS(?,?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"FARMA_PUNTOS.P_SAVE_TRX_ANULA_ORBIS(?,?,?,?,?,?)", parametros,false);        
    }    

    public static ArrayList getListProdPuntosRecupera(String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_CUR_DET_RECUPERA(?,?,?)"+ parametros);
        ArrayList pLista = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pLista,
                                                          "FARMA_PUNTOS.F_CUR_DET_RECUPERA(?,?,?)",
                                                          parametros);
                
        return pLista;
    }
    
    public static ArrayList getListProdPuntosAnula(String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_CUR_DET_ANULA(?,?,?)"+ parametros);
        ArrayList pLista = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pLista,
                                                          "FARMA_PUNTOS.F_CUR_DET_ANULA(?,?,?)",
                                                          parametros);
        return pLista;
    }    
    
    public static boolean getIndPedActuaPuntos(String pNumPedVta)  throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_PED_ACTUA_PUNTOS(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_PED_ACTUA_PUNTOS(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }


    public static boolean isPuntosReloadCero(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_IS_PTO_CERO(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IS_PTO_CERO(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

    static boolean isPuntosReloadNulo(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_IS_PTO_NULO(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IS_PTO_NULO(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

    static boolean isPuntosReloadMayorCero(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_IS_PTO_MAYOR_CERO(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IS_PTO_MAYOR_CERO(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }
    
    static boolean isTieneBonificado(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_IS_BONIFICADO(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IS_BONIFICADO(?,?,?)", parametros);
        if(pRes.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

    static String getCtdPuntosAcumVta(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_IS_CTD_PTO_PED(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IS_CTD_PTO_PED(?,?,?)", parametros);
        return pRes.trim();
    }

    public static List getDataTrnxAnula(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        log.info("FARMA_PUNTOS.F_VAR_GET_DATA_TRANSAC_ORBIS(?,?,?,?)"+parametros);
        List pLista = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.F_VAR_GET_DATA_TRANSAC_ORBIS(?,?,?)", parametros);
        return pLista ;
    }
    
    //DATOS DE PRUEBA
    public static List consultaTarje(String nroTarj)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(nroTarj);
        log.info("FARMA_PUNTOS.CONSULTA_TARJETA(?)"+parametros);
        List pLista = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.CONSULTA_TARJETA(?)", parametros);
        return pLista ;
    }
    
    public static boolean registroCliente(String nroTarjeta, String nroDni, String nombre)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(nroTarjeta);
        parametros.add(nroDni);
        parametros.add(nombre);
        log.info("FARMA_PUNTOS.REGISTRO_TARJETA(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.REGISTRO_TARJETA(?,?,?)", parametros);
        return true;
    }
    
    public static List consultaCliente(String nroTarj)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(nroTarj);
        log.info("FARMA_PUNTOS.CONSULTA_CLIENTE(?)"+parametros);
        List pLista = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.CONSULTA_CLIENTE(?)", parametros);
        return pLista ;
    }
    // FIN DE DATOS DE PRUEBA 
    
    /**
     * @author KMONCADA
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @throws Exception
     */
    public static void calculoPuntos(String pCodGrupoCia, String pCodLocal, String pNumPedVta, boolean isRecalculo, boolean isRedime)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        if(isRecalculo)
            parametros.add("S");
        else
            parametros.add("N");
        if(isRedime)
            parametros.add("S");
        else
            parametros.add("N");
        
        log.info("FARMA_PUNTOS.F_I_CALCULA_PUNTOS --> "+VariablesConvenioBTLMF.vValorSelCopago);
        log.info("FARMA_PUNTOS.F_I_CALCULA_PUNTOS(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_PUNTOS.F_I_CALCULA_PUNTOS(?,?,?,?,?)", parametros, false);
    }
    
    /**
     *
     * @author KMONCADA
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @return
     * @throws Exception
     */
    public static ArrayList obtenerListaProducto(String pCodGrupoCia, String pCodLocal, String pNumPedVta, boolean isAdicionarBonificado)throws Exception{
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        if(isAdicionarBonificado)
            parametros.add("S");
        else
            parametros.add("N");
        log.info("FARMA_PUNTOS.F_CUR_V_LISTA_PRODUCTO(?,?,?,?) "+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "FARMA_PUNTOS.F_CUR_V_LISTA_PRODUCTO(?,?,?,?)", parametros);
        return resultado;
    }
    
    /**
     *
     * @author KMONCADA
     * @return
     * @throws Exception
     */
    public static String obtenerIndicadorDocBonificaPuntos()throws Exception{
        ArrayList parametros = new ArrayList();
        log.info("FARMA_PUNTOS.F_CHAR_IND_DOC_BONIFICA"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_IND_DOC_BONIFICA", parametros);
    }
    
    /**
     *
     * @author KMONCADA
     * @return
     * @throws Exception
     */
    public static boolean indicadorMuestrPantallaBonifica()throws Exception{
        ArrayList parametros = new ArrayList();
        log.info("FARMA_PUNTOS.F_CHAR_IND_PANTALLA_BONIFICA"+parametros);
        String indicador = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_IND_PANTALLA_BONIFICA", parametros);
        if("S".equalsIgnoreCase(indicador))
            return true;
        else
            return false;
    }
    /**
     *
     * @author LTAVARA 2016.11.11
     * @return
     * @throws Exception
     * indicardor si permite canjear bonificacion obligatoria son documentos
     */
    public static boolean indPermitirCanjearBonifobligatoriaSinDocumentos()throws Exception{
        ArrayList parametros = new ArrayList();
        log.info("FARMA_PUNTOS.F_CHAR_IND_BONIF_OBLIG_SIN_DOC"+parametros);
        String indicador = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_IND_BONIF_OBLIG_SIN_DOC", parametros);
        if("S".equalsIgnoreCase(indicador))
            return true;
        else
            return false;
    }
    /**
     *
     * @author KMONCADA
     * @param pModel
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @param pLstProductoQuote
     * @throws Exception
     */
    public static void verificaBonificados(ArrayList arrayList,  String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pLstProductoQuote)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pLstProductoQuote);
        log.info("PKG_PROM_ACUMULA.F_LST_VALIDA_BONIFICADOS(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(arrayList, "PKG_PROM_ACUMULA.F_LST_VALIDA_BONIFICADOS(?,?,?,?)", parametros);
    }

    
    public static String getTimeOutOrbis()throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("FARMA_PUNTOS.F_VAR_TIME_OUT_ORBIS(?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_TIME_OUT_ORBIS(?,?)", parametros);
    }
    
    public static String getMensajeErrorLealtad(String estadoOperacion)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(ConstantsPuntos.COD_MAESTRO.TIPO_MENSAJE_ERROR_LEALTAD);
        parametros.add(estadoOperacion);
        log.info("FARMA_GRAL.GET_MAESTRO_DETALLE (?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_GRAL.GET_MAESTRO_DETALLE(?,?)", parametros);
    } 
    
    /**
     *
     * @return
     * @throws Exception
     */
    public static int getTiempoMaxLectora()throws Exception{
        ArrayList parametros = new ArrayList();
        log.info("FARMA_PUNTOS.F_CHAR_TIEMPO_MAX_LECTORA"+parametros);
        return (int)Integer.parseInt(FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_TIEMPO_MAX_LECTORA", parametros));
    }
    
    /**
     * Opera la respuesta del quote del programa de puntos, determina productos bonificados y promociones.
     * @author KMONCADA 2015.03.19 
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @param pLstProductoQuote
     * @return
     * @throws Exception
     */
    public static String ejecutarQuote(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pLstProductoQuote)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pLstProductoQuote);
        log.info("FARMA_PUNTOS.F_CHAR_OPERA_QUOTE(?,?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_OPERA_QUOTE(?,?,?,?)", parametros);
    }
    
    /**
     * @author KMONCADA
     * @return
     * @throws Exception
     */
    public static String getTextoAhorro() throws Exception{
        ArrayList parametros = new ArrayList();
        log.info("FARMA_PUNTOS.F_TEXTO_AHORRO"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_TEXTO_AHORRO", parametros);
    }
    
    public static void pDescartaAnulaOrbis(String pNumPedVtaOriginal,boolean pDescarta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVtaOriginal);
        if(pDescarta)
        parametros.add(FarmaConstants.INDICADOR_S);
        else
        parametros.add(FarmaConstants.INDICADOR_N);
        log.info("FARMA_PUNTOS.P_DESCARTA_TRX_ANULA_ORBIS(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"FARMA_PUNTOS.P_DESCARTA_TRX_ANULA_ORBIS(?,?,?,?)", parametros,false);        
    }
    
    public static void pDescartaNCnoOrigen(String pNumPedVtaOriginal) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVtaOriginal);
        parametros.add(FarmaConstants.INDICADOR_S);
        parametros.add(FarmaConstants.INDICADOR_N);//No descarta origen
        
        log.info("FARMA_PUNTOS.P_DESCARTA_TRX_ANULA_ORBIS(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"FARMA_PUNTOS.P_DESCARTA_TRX_ANULA_ORBIS(?,?,?,?,?)", parametros,false);        
    }
    
    /**
     * obtiene la lista de los productos que llevara por promociones del programa de puntos (codigo,cantidad@codigo,cantidad...)
     * @author KMONCADA 2015.03.18
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @return
     * @throws Exception
     */
    public static String getDiferenciasPromociones(String pCodGrupoCia, String pCodLocal, String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        log.info("FARMA_PUNTOS.F_VCHAR_OBTIENE_MODIFICADOS(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VCHAR_OBTIENE_MODIFICADOS(?,?,?)", parametros);
    }
    
    /**
     * obtiene la cantidad de productos que llevara por promociones del programa de puntos
     * @author KMONCADA 2015.03.18 
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @return
     * @throws Exception
     */
    public static int getCantidadProductoPromociones(String pCodGrupoCia, String pCodLocal, String pNumPedVta)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        log.info("FARMA_PUNTOS.F_INT_CANTIDAD_PROMOCIONES(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureInt("FARMA_PUNTOS.F_INT_CANTIDAD_PROMOCIONES(?,?,?)", parametros);
    }
    
    /**
     * Agrega los productos bonificados y por promocion al pedido (valor venta 0) del programa de puntos
     * @author KMONCADA 2015.03.18 
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @param pNumPedVtaOrigen para el caso de que copie las promociones de otro pedido, caso contrario enviar el mismo pNumPedVta
     * @return
     * @throws Exception
     */
    public static String agregaPromoBonificados(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pNumPedVtaOrigen)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pNumPedVtaOrigen);
        log.info("FARMA_PUNTOS.F_CHAR_AGREGA_PROMO_BONIF(?,?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_AGREGA_PROMO_BONIF(?,?,?,?)", parametros);
    }
    
    public static String getMensajeSinTarjeta(){
        String mensaje = " ";
        try{
            ArrayList parametros = new ArrayList();
            log.info("FARMA_PUNTOS.F_VARCHAR_MSJ_SIN_TARJETA "+parametros);
            mensaje = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VARCHAR_MSJ_SIN_TARJETA", parametros);
        }catch(Exception ex){
            log.error(" ", ex);
            
        }
        return mensaje;
    }
    
    public static boolean requerieTarjetaAsociada(){
        boolean isRequiere = true;
        String respuesta = "";
        try{
            ArrayList parametros = new ArrayList();
            log.info("FARMA_PUNTOS.F_IND_REQUIERE_TARJETA "+parametros);
            respuesta = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_IND_REQUIERE_TARJETA", parametros);
        }catch(Exception ex){
            respuesta = ConstantsPuntos.DEFAULT_REQUIERE_TARJETA;
        }
        if(ConstantsPuntos.DEFAULT_REQUIERE_TARJETA.equalsIgnoreCase(respuesta.trim()))
            isRequiere = true;
        else
            isRequiere = false;
        return isRequiere;
    }
    
    public static boolean validaTarjetaAsociada(String pNroDocumento, String pNroTarjeta){
        boolean existeAsociada = false;
        try{
            ArrayList parametros = new ArrayList();
            parametros.add(pNroDocumento);
            parametros.add(pNroTarjeta);
            log.info("FARMA_PUNTOS.F_CHAR_EXISTE_TARJETA_CLIENTE(?,?) "+parametros);
            String respuesta = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_EXISTE_TARJETA_CLIENTE(?,?)", parametros);
            if("S".equalsIgnoreCase(respuesta)){
                existeAsociada = true;
            }
        }catch(Exception ex){
            log.error(" ", ex);
            existeAsociada = false;
        }
        return existeAsociada;
    }
    
    public static void actualizaEstadoTrx(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pEstadoTrx)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pEstadoTrx);
        log.info("FARMA_PUNTOS.P_ACTUALIZA_TRX_ORBIS(?,?,?,?) "+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_PUNTOS.P_ACTUALIZA_TRX_ORBIS(?,?,?,?)", parametros, false);
    }
    
    /*
    public static String evaluaTarjeta(String pTarjeta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        if(pTarjeta!=null)
            parametros.add(pTarjeta);
        else
            parametros.add("");
        log.info("FARMA_PUNTOS.F_VAR_TARJ_INVALIDA(?,?,?)"+parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_TARJ_INVALIDA(?,?,?)", parametros);
        return pRes.trim();
    } 
    */
    
    public static String getMensajeTarjetAdicional(){
        String mensaje = " ";
        try{
            ArrayList parametros = new ArrayList();
            log.info("FARMA_PUNTOS.F_VARCHAR_MSJ_TARJ_ADICIONAL "+parametros);
            mensaje = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VARCHAR_MSJ_TARJ_ADICIONAL", parametros);
        }catch(Exception ex){
            log.error(" ", ex);
            
        }
        return mensaje;
    }
    
    public static boolean isValidaDocumentoTarjetaAdicional(){
        boolean flag = true;
        try{
            ArrayList parametros = new ArrayList();
            log.info("FARMA_PUNTOS.F_CHAR_VALIDA_DOC_TARJ_ADICION "+parametros);
            String mensaje = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_VALIDA_DOC_TARJ_ADICION", parametros);
            if("S".equalsIgnoreCase(mensaje)){
                flag = true;
            }else{
                flag = false;
            }
        }catch(Exception ex){
            log.error(" ", ex);
            flag = true;
        }
        return flag;
    }
    
    public static String getMensajePuntosPantallaCobro(){
        String mensaje = " ";
        try{
            ArrayList parametros = new ArrayList();
            log.info("FARMA_PUNTOS.F_CHAR_MSJ_PTOS_PANTALLA_COBRO "+parametros);
            mensaje = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_MSJ_PTOS_PANTALLA_COBRO", parametros);
        }catch(Exception ex){
            log.error(" ", ex);
            
        }
        return mensaje;
    }
    
    public static boolean isTarjetaAsociada(String pNroDocumento, String pNroTarjeta){
        boolean isAsociada = false;
        try{
            ArrayList parametros = new ArrayList();
            parametros.add(pNroDocumento);
            parametros.add(pNroTarjeta);
            log.info("FARMA_PUNTOS.F_IS_TARJETA_ASOCIADA(?,?) "+parametros);
            String respuesta = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_IS_TARJETA_ASOCIADA(?,?)", parametros);
            if("S".equalsIgnoreCase(respuesta)){
                isAsociada = true;
            }
        }catch(Exception ex){
            log.error(" ", ex);
            isAsociada = false;
        }
        return isAsociada;
    }
    
    public static boolean isValidaClienteAfiliadoLocal(String pNroDocumento){
        boolean isAsociada = false;
        try{
            ArrayList parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodLocal);
            parametros.add(pNroDocumento);
            log.info("FARMA_PUNTOS.F_CLIENTE_AFILIADO_PTOS(?,?,?) "+parametros);
            String respuesta = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CLIENTE_AFILIADO_PTOS(?,?,?)", parametros);
            if("S".equalsIgnoreCase(respuesta)){
                isAsociada = true;
            }
        }catch(Exception ex){
            log.error(" ", ex);
            isAsociada = false;
        }
        return isAsociada;
    }
    
    public static String getCtdPuntosExtras(String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        log.info("FARMA_PUNTOS.F_VAR_IS_CTD_PTO_EXTRAS(?,?)"+ parametros);
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_VAR_IS_CTD_PTO_EXTRAS(?,?,?)", parametros);
        return pRes.trim();
    }
    
    /**
     * Metodo para imprimir en ticket boleta.
     * @author KMONCADA
     * @since 24.07.2015
     * @param pNumPedVta
     * @param pSecCompPago
     * @return
     * @throws SQLException
     */
    public static List imprVouPtosTicket(String pNumPedVta, String pSecCompPago) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);        
        parametros.add(pNumPedVta);
        parametros.add(pSecCompPago);
        if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null)
            parametros.add(VariablesPuntos.frmPuntos.getBeanTarjeta().getAhorroTotal());
        else
            parametros.add(0);
        if(VariablesPuntos.frmPuntos != null){
            if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                parametros.add(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni());
            }else{
                parametros.add(" ");
            }
        }else{
            parametros.add(" ");
        }
        log.info("FARMA_PUNTOS.F_GET_VOUCHER_PTOS_NE(?,?,?,?,?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.F_GET_VOUCHER_PTOS_NE(?,?,?,?,?,?)",
                                                               parametros);
    }
    
    public static void totalizaAhorros(String pNumPedVta)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        log.info("FARMA_PUNTOS.P_TOTALIZA_AHORRO(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"FARMA_PUNTOS.P_TOTALIZA_AHORRO(?,?,?)", parametros,false);
    }
    
    public static String getObtieneTarjetaNoMonedero(String pNroDocumento, boolean isMonedero){
        String nroTarjeta = "";
        try{
            ArrayList parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodLocal);
            parametros.add(pNroDocumento);
            if(isMonedero){
                parametros.add("S");
            }else{
                parametros.add("N");
            }
            log.info("FARMA_PUNTOS.F_GET_DNI_TARJETA(?,?,?)"+parametros);
            nroTarjeta = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_GET_DNI_TARJETA(?,?,?)", parametros);
        }catch(Exception ex){
            log.error(" ", ex);
            nroTarjeta =  "";
        }
        return nroTarjeta;
    }
    
    /**
     * @autor Desarrollo5
     * @param pNroTarjeta
     * @since 21.10.2015
     * @return
     */
    public static Integer compruebaRegistroTarjetaLocal(String pNroTarjeta){
		Integer nroTarjeta = 0;
		try{
		    ArrayList parametros = new ArrayList();
		    parametros.add(pNroTarjeta);
	
		    log.info("FARMA_PUNTOS.F_COMPRUEBA_TARJETA(?)"+parametros);
		    nroTarjeta = Integer.parseInt(FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_COMPRUEBA_TARJETA(?)", parametros));
		}catch(Exception ex){
		    log.error(" ", ex);
		    nroTarjeta =  0;
		}
		return nroTarjeta;
    }
    
    /**
     * @author ERIOS
     * @since 23.03.2016
     * @param pNumPedVta
     * @return
     * @throws Exception
     */
    public static String getPagoConvenio(String pNumPedVta) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        
        double pRes = FarmaDBUtility.executeSQLStoredProcedureDouble("PTOVENTA.FARMA_PUNTOS.F_GET_DETERMINA_PAGO_CONV(?,?,?)", parametros);
        return (pRes == 0.0)?"S":"N";
    }

    /**
     * @author LTAVARA
     * @since 04.08.2016
     * @param pTarjeta
     * @param pNombreCompleto
     * @param pListaProducto
     * @return
     * @throws Exception
     */
    public static List voucherContinuaBonificandoProgramaX1(String pNumPedVta,String pProductoAcumualdoProgramaX1) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pProductoAcumualdoProgramaX1);
        List pRes = new ArrayList();
        log.info("FARMA_PUNTOS.F_VOUCHER_CONTINUA_BONIFICANDO(?,?,?,?)"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_PUNTOS.F_VOUCHER_CONTINUA_BONIFICANDO(?,?,?,?)",parametros);
        return pRes;
    }

    static String obtenerIndicadorDocBonificaProgramaXmas1()throws Exception{
                ArrayList parametros = new ArrayList();
                log.info("FARMA_PUNTOS.F_CHAR_IND_DOC_BONIFICA"+parametros);
                return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_IND_DOC_BONIFICA_PROG", parametros);
            }
            
    public static String getCantProdFracLocal(int pCantidad,String pCodProd,String pValFrac,String TipTransaccion) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd);
        parametros.add(pCantidad);
        parametros.add(pValFrac);
        parametros.add(TipTransaccion);
        log.info("FARMA_PUNTOS.F_NUM_CANT_FRAC_LOCAL(?,?,?,?,?,?)"+ parametros);  
        String pRes = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_PUNTOS.F_NUM_CANT_FRAC_LOCAL(?,?,?,?,?,?)", parametros);
        return pRes;
    }
    
    /**
     * @author LTAVARA
     * @since 04.08.2016
     * @param pTarjeta
     * @param pNombreCompleto
     * @param pListaProducto
     * @return
     * @throws Exception
     */
    public static String getIndImprVouContinuaBonificando(String pNumPedVta,String pProductoAcumualdoProgramaX1) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pProductoAcumualdoProgramaX1);
        String pRes = "N";
        log.info("FARMA_PUNTOS.F_IND_IMPR_VOU_CONT_BONIF(?,?,?,?)"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_IND_IMPR_VOU_CONT_BONIF(?,?,?,?)",parametros);
        return pRes;
    }
    
    /**
     * obtiene la cantidad de diferencia entrea las cantidades enviadas y recibidas del QUOTE
     * @author LTAVARA 2015.11.24
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @return
     * @throws Exception
     */
    public static int getCantidadDiferenciaQUOTE(String pCodGrupoCia, String pCodLocal, String pNumPedVta)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        log.info("FARMA_PUNTOS.F_INT_DIFERENCIA_CANT_QUOTE(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureInt("FARMA_PUNTOS.F_INT_DIFERENCIA_CANT_QUOTE(?,?,?)", parametros);
    }

    public static String getTimeOutsOrbis() throws SQLException {
         String vTiempos="";
         ArrayList parametros = new ArrayList();
         log.info("PTOVENTA_GRAL.GET_TIME_OUT_ORBIS"+ parametros);
         vTiempos = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_GRAL.GET_TIME_OUT_ORBIS", parametros);
         log.info("tiempos getTimeOutsOrbis vTiempos : "+vTiempos);

         return vTiempos;
     }      
    
    public static String getCodigoEquivalenteNum(String pCodLocal) throws SQLException {
         String codLocalEquivalente=pCodLocal;
         ArrayList parametros = new ArrayList();
         parametros.add(FarmaVariables.vCodGrupoCia);
         parametros.add(pCodLocal);
         parametros.add(FarmaVariables.vCodCia);
         log.info("FARMA_UTILITY.F_VAR_GET_COD_LOCAL_EQUIV_NUM(?,?,?)"+ parametros);
         codLocalEquivalente = FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_UTILITY.F_VAR_GET_COD_LOCAL_EQUIV_NUM(?,?,?)", parametros);
         log.info("codigo de local equivalente : "+codLocalEquivalente);

         return codLocalEquivalente;
     }

    private static String verificarCampoRegistrar(String valor){
        if(valor!=null){
            if(valor.trim().length()==0){
                return "";
            }else{
                return valor.trim();
            }
        }else{
            return "";
        }
    }
    
    /**
     * Insertar determinados procesos del fv para puntos.
     * @author ASOSA
     * @since 27.02.2015
     *  
     * @param operacionDTO
     * @throws SQLException
     */
    public static void insertarLogOperacion(BeanOperacion operacionDTO) throws SQLException {

        parametros = new ArrayList();
        
        parametros.add(verificarCampoRegistrar(operacionDTO.getCCodGrupoCia_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getCCodCia_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getCCodLocal_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVTipoOperacion_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVOperacion_in()));
        
        parametros.add(verificarCampoRegistrar(operacionDTO.getVInput_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVEmpleado_id_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVTransaccionId_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVNumAutorizacion_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVNumTarjeta_in()));
        
        parametros.add(verificarCampoRegistrar(operacionDTO.getVDocIdentidad_in()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVEstado()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVOutput()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVFecha()));
        parametros.add(verificarCampoRegistrar(operacionDTO.getVNumPedidoVta()));


        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "FARMA_PUNTOS.FID_P_INS_PROC_PTOS(?,?,?,?,?," +
                                                                                            "?,?,?,?,?," +
                                                                                            "?,?,?,?,?)",
                                                 parametros, false);
    }
    
    public static void getListaBonificados(ArrayList arrayList,  
                                           String pCodGrupoCia, 
                                           String pCodLocal,
                                           String pLstProductoQuote)
                                          throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pLstProductoQuote);
        log.info("PKG_PROM_ACUMULA.F_LST_BONIFCADOS(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(arrayList, "PKG_PROM_ACUMULA.F_LST_BONIFCADOS(?,?,?)", parametros);
    }
    
    public static void save_prod_regalo_acumula(String pNumPedVta,
                                               String pCodProd,
                                               String pCantidad,
                                               String pCodEqAcumula,
                                               String pCodCampAcumula,
                                               String pValFracRegalo  ) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta.trim());
        parametros.add(pCodProd.trim());
        parametros.add(pCantidad.trim());
        parametros.add(pCodEqAcumula.trim());
        parametros.add(pCodCampAcumula.trim());
        parametros.add(pValFracRegalo.trim());
        log.info("PKG_PROM_ACUMULA.F_CREA_REGALO_A_PEDIDO(?,?,?,?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"PKG_PROM_ACUMULA.F_CREA_REGALO_A_PEDIDO(?,?,?,?,?,?,?,?)", parametros,false);        
    }
    
    public static void graba_hist_acumula_x_dni(String pNumPedVta, 
                                                String pCodPrograma,
                                                String pCodEquivalente,
                                                String pCtdFaltaParaGanar) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pCodPrograma);
        parametros.add(pCodEquivalente);
        parametros.add(pCtdFaltaParaGanar);
        log.debug("PKG_PROM_ACUMULA.F_GRABA_FALTANTE_PARA_REGALO(?,?,?,?,?,?)" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PKG_PROM_ACUMULA.F_GRABA_FALTANTE_PARA_REGALO(?,?,?,?,?,?)",
                                                 parametros, false);
    }
    
    public static void getCampAcumulaPorProducto(ArrayList arrayList,
                                           String pCodProd)
                                          throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd);
        log.info("pkg_prom_acumula.lista_prog_acum_activo(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(arrayList, "pkg_prom_acumula.lista_prog_acum_activo(?,?,?,?)", parametros);
    }
    
    //INICIO RPASCACIO 15-06-2017
    public static String getConversionFraccion(String pCodProd,String pCodEqui,String pValFrac, String pCant ) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd.trim());
        parametros.add(pCodEqui.trim());
        parametros.add(pValFrac.trim());
        parametros.add(pCant.trim());
        log.info("FARMA_PUNTOS.F_CONVERSION_FRACCION(?,?,?,?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CONVERSION_FRACCION(?,?,?,?,?,?)",
                                                           parametros);
    }
    //FIN RPASCACIO 15-06-2017
    
    //INICIO RPASCACIO 23-06-2017  F_CONVERSION_REGALO_ORBIS
    public static String getConversionRegaloOrbis(String pCodProg,
                                         String pCodProd,
                                         String pCantidad,
                                         String pValFracVta) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProg.trim());        
        parametros.add(pCodProd.trim());        
        parametros.add(pCantidad.trim());        
        parametros.add(pValFracVta.trim());        
        log.info("FARMA_PUNTOS.F_CONVERSION_REGALO_ORBIS(?,?,?," +
                                                    "?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CONVERSION_REGALO_ORBIS(?,?,?," +
                                                                                              "?,?,?)",
                                                           parametros);
    }
    //FIN RPASCACIO 23-06-2017
    
    // DUBILLUZ 20171020
    public static String getCodEquivalente_Prod_PACK(String pCodProd) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd.trim());        
        log.info("FARMA_PUNTOS.GET_COD_EQUI_PROD_PROM_PACK(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.GET_COD_EQUI_PROD_PROM_PACK(?,?,?)",
                                                           parametros);
    }
    
    public static String isExistePACK_VIGENTE_CodEquivalente(String pCOD_EQUIVALENTE) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCOD_EQUIVALENTE.trim());        
        log.info("FARMA_PUNTOS.IS_PROD_EQ_IN_PACK_VIGENTE(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.IS_PROD_EQ_IN_PACK_VIGENTE(?,?,?)",
                                                           parametros);
    }
	
    public static String getTrajDNI_ClienteOrvee(String pCod_DNI,
                                                 String nroTajPedido,
                                                 String codErr_AnulOrbis,
                                                 String nroTarjOrbis) throws SQLException {
        parametros = new ArrayList();
        parametros.add(pCod_DNI.trim());
        parametros.add(nroTajPedido.trim());
        parametros.add(codErr_AnulOrbis.trim());
        parametros.add(nroTarjOrbis.trim());
        log.info("FARMA_PUNTOS.GET_TAJETA_PUNTOS_DNI(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.GET_TAJETA_PUNTOS_DNI(?,?,?,?)",
                                                           parametros);
    }
    
    /**
     *
     * @author ZVILLAFUERTE
     * @return
     * @throws Exception
     */
    public static String getConversionPuntosSoles(double pCantidadPuntos) throws Exception {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCantidadPuntos);
        log.info("FARMA_PUNTOS.GET_CONVER_SOLES_PTOS(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.GET_CONVER_SOLES_PTOS(?,?,?)", parametros);
    }
    /**
     *
     * @author ZVILLAFUERTE
     * @return
     * @throws Exception
     */
    public static String obtenerIndicadorDniTarjetaOpcReq()throws Exception{
        ArrayList parametros = new ArrayList();
        log.info("FARMA_PUNTOS.F_CHAR_IND_DNI_TARJ_OPC_REQ"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.F_CHAR_IND_DNI_TARJ_OPC_REQ", parametros);
    }
}
