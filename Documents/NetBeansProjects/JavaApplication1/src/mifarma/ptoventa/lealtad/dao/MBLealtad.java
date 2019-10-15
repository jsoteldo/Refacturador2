package mifarma.ptoventa.lealtad.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mifarma.common.FarmaDBUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author ERIOS
 * @since 05.02.2015
 */
public class MBLealtad implements DAOLealtad{
    
    private static final Logger log = LoggerFactory.getLogger(MBLealtad.class);
    
    private SqlSession sqlSession = null;
    private MapperLealtad mapper = null;
    
    public MBLealtad() {
        super();
    }

    @Override
    public void commit() {
        sqlSession.commit(true);
        sqlSession.close();
    }

    @Override
    public void rollback() {
        sqlSession.rollback(true);
        sqlSession.close();
    }

    @Override
    public void openConnection() {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        mapper = sqlSession.getMapper(MapperLealtad.class);
    }

    @Override
    public int verificaAcumulaX1(String pCodGrupoCia, String strCodCia, String strCodLocal, String pCodProd) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();

        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cCodProd_in", pCodProd);

        mapper.verificaAcumulaX1(mapParametros);

        return new Integer(mapParametros.get("nRetorno").toString());
    }


    @Override
    public List<BeanResultado> listaAcumulaX1(String pCodGrupoCia, String strCodCia, String strCodLocal,
                                                       String pCodProd)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cCodProd_in", pCodProd);
        
        mapper.listaAcumulaX1(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }
    // lais X+1
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param strCodCia
     * @param strCodLocal
     * @param pCodProd
     * @return List<BeanResultado>
     * @throws Exception
     * @since 16.11.2015
     */
    @Override
    public List<BeanResultado> listaProgramasAcumulaX1(String pCodGrupoCia, String strCodLocal,
                                                       String pListaProgramas)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COGCIA", pCodGrupoCia);
        mapParametros.put("PI_CODLOC", strCodLocal);
        mapParametros.put("PI_LSTPRG", pListaProgramas);
        
        mapper.listaProgramasAcumulaX1(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param strCodLocal
     * @param pCodProd
     * @return List<BeanResultado>
     * @throws Exception
     * @since 06.11.2015
     */
    @Override
    public List<BeanResultado> detalleListaAcumulaX1(String pCodGrupoCia, 
                                                     String strCodLocal,
                                                     String pCodProd)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COGCIA", pCodGrupoCia);
        mapParametros.put("PI_CODLOC", strCodLocal);
        mapParametros.put("PI_CODPRO", pCodProd);
        
        mapper.detalleListaAcumulaX1(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param strCodLocal
     * @param pCodProd
     * @return List<BeanResultado>
     * @throws Exception
     * @since 06.11.2015
     */
    @Override
    public List<BeanResultado> detalleListaProgramasAcumulaX1(String pCodGrupoCia, 
                                                              String strCodLocal,
                                                              String lstProgramas)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COGCIA", pCodGrupoCia);
        mapParametros.put("PI_CODLOC", strCodLocal);
        mapParametros.put("PI_COPROG", lstProgramas);

        mapper.detalleListaProgramasAcumulaX1(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pDniCli
     * @param pEstPrograma
     * @return List<BeanResultado>
     * @throws Exception
     * @since 09.11.2015
     */
    @Override
    public List<BeanResultado> programasTemporalesX1(String pCodGrupoCia, 
                                                     String pCodLocal,
                                                     String pDniCli,
                                                     String pEstPrograma)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COGCIA", pCodGrupoCia);
        mapParametros.put("PI_CODLOC", pCodLocal);
        mapParametros.put("PI_DNICLI", pDniCli);
        mapParametros.put("PI_ESTREG", pEstPrograma);
        
        mapper.programasTemporalesX1(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }

    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pDniCli
     * @param pCodPro
     * @param pEstPrograma
     * @param pUsuReg
     * @throws Exception
     * @since 09.11.2015
     */
    @Override
    public void registrarProgramasTempX1(String pCodGrupoCia, 
                                         String pCodLocal,
                                         String pDniCli,
                                         String pCodPrograma,
                                         String pEstPrograma,
                                         String pUsuReg)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COGCIA", pCodGrupoCia);
        mapParametros.put("PI_CODLOC", pCodLocal);
        mapParametros.put("PI_DNICLI", pDniCli);
        mapParametros.put("PI_COPROG", pCodPrograma);
        mapParametros.put("PI_ESTREG", pEstPrograma);
        mapParametros.put("PI_USUINS", pUsuReg);
        
        mapper.registrarProgramasTempX1(mapParametros);
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pCodPrograma
     * @param pDniCli
     * @param pNumTarjeta
     * @return List
     * @throws Exception
     * @since 17.11.2015
     */
    @Override
    public List voucherDesafiliacionX1(String pCodGrupoCia, 
                                       String pCodLocal, 
                                       String pCodPrograma, 
                                       String pDniCli,
                                       String pNumTarjeta) throws Exception{
        ArrayList parametros = new ArrayList();
        String PI_COGCIA = pCodGrupoCia;
        String PI_CODLOC = pCodLocal;
        String PI_COPROG = pCodPrograma;
        String PI_NUMDOC = pDniCli;
        String PI_NUMTAR = pNumTarjeta;
        
        parametros.add(PI_COGCIA);
        parametros.add(PI_CODLOC);
        parametros.add(PI_NUMDOC);
        parametros.add(PI_COPROG);
        parametros.add(PI_NUMTAR);
        log.debug("PKG_PROM_ACUMULA.FN_VOUCHER_DESAFILIACION_X1(?,?,?,?,?)" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureListMap("PKG_PROM_ACUMULA.FN_VOUCHER_DESAFILIACION_X1(?,?,?,?,?)",
                                                               parametros);  
    }

    /**
     * autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pCodConvenio
     * @return Double
     * @throws Exception
     * @since 13.11.2015
     */
    @Override
    public Double obtenerPorcentajeDescuentoConvenio(String pCodGrupoCia, 
                                                     String pCodLocal,
                                                     String pCodConvenio) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COGCIA", pCodGrupoCia);
        mapParametros.put("PI_CODLOC", pCodLocal);
        mapParametros.put("PI_CODCON", pCodConvenio);
        
        mapper.obtenerPorcentajeDescuentoConvenio(mapParametros);
        
        return new Double(mapParametros.get("nRetorno").toString());
    }

    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param listProgramas
     * @return List<BeanResultado>
     * @throws Exception
     * @since 13.11.2015
     */
    @Override
    public List<BeanResultado> obtenerProductosDeProgramas(String listProgramas) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COPROG", listProgramas);
        
        mapper.obtenerProductosDeProgramas(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }
    
    /**
     * @autor LTAVARA
     * @param listProgramas
     * @return List<BeanResultado>
     * @throws Exception
     * @since 2016.09.26
     * obtener los programas que pertencen a los programas inscritos y en comun por el producto
     */
    @Override
    public List<BeanResultado> obtenerProductosDeProgramasInsComun(String listProgramas) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_COPROG", listProgramas);
        
        mapper.obtenerProductosDeProgramasInsComun(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }


    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodGrupoCia
     * @param strCodLocal
     * @param pNumPedVta
     * @param pCadenaProgramas
     * @throws Exception
     * @since 18.11.2015
     */
    @Override
    public void recuperarProgramasX1(String pCodGrupoCia,
                                     String strCodLocal, 
                                     String pNumPedVta,
                                     String pCadenaProgramas) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pCadenaProgramas);
        log.debug("PKG_PROM_ACUMULA.PRC_RECUPERACION_PROGRAMA_X1(?,?,?,?)" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PKG_PROM_ACUMULA.PRC_RECUPERACION_PROGRAMA_X1(?,?,?,?)", parametros, false);        
    }

    @Override
    public List<BeanResultado> obtenerProgramasTemporalesDeProducto(String codigoProducto,
                                                                    String numeroDocumentoCliente) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("PI_DNICLI", numeroDocumentoCliente);
        mapParametros.put("PI_CODPRO", codigoProducto);
        
        mapper.obtenerProgramasTemporalesDeProducto(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        return lstRetorno;
    }
    // lais X+1
    @Override
    public List voucherAcumulaX1(String pCodGrupoCia, String strCodCia, String strCodLocal, String pCodCamp, String pDniCli, String pCodProd) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodCia);
        parametros.add(strCodLocal);
        parametros.add(pDniCli);
        parametros.add(pCodCamp);
        parametros.add(pCodProd);
        log.debug("PKG_PROM_ACUMULA.VOUCHER_ACUMULA_X1(?,?,?,?,?,?)" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureListMap("PKG_PROM_ACUMULA.VOUCHER_ACUMULA_X1(?,?,?,?,?,?)",
                                                               parametros);        
    }
    
    public String registrarInscripcionX1(String pDniCli, String pCodMatrizAcu, String vIdUsu){
        String vRetorno="";
        SqlSession sqlSession = MyBatisUtil.getRACSqlSessionFactory().openSession();
        Map<String, Object> mapParametros = new HashMap<String, Object>();

        mapParametros.put("vDniCli_in", pDniCli);
        mapParametros.put("vCodMatrizAcu_in", pCodMatrizAcu);
        mapParametros.put("vIdUsu_in", vIdUsu);
        
        try {
            
            MapperLealtadMatriz mapper = sqlSession.getMapper(MapperLealtadMatriz.class);
            mapper.registrarInscripcionX1(mapParametros);
            
            sqlSession.commit(true);
            vRetorno = "1";
        } catch (Exception e) {
            sqlSession.rollback(true);
            log.error("", e);
            vRetorno = e.getMessage();
        } finally {
            sqlSession.close();
        }
        return vRetorno;
    }

    @Override
    public String indImpresionVoucherX1() {
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();

        mapper.indImpresionVoucherX1(mapParametros);

        return mapParametros.get("vRetorno").toString();
    }

    @Override
    public String obtenerParametrosVenta(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);
        
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_LEALTAD.GET_PARAMETROS_VENTA(?,?,?,?)", parametros);
    }

    @Override
    public void actualizarPedido(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta,
                                 String pIdTransaccion, String pNumAutorizacion, String pIdUsu) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);
        parametros.add(pIdTransaccion);
        parametros.add(pNumAutorizacion);
        parametros.add(pIdUsu);
        
        FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_LEALTAD.GET_ACTUALIZAR_VENTA(?,?,?,?,?,?,?)", parametros, false);        
    }

    @Override
    public void eliminaProdBonificacion(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);

        FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_LEALTAD.GET_ELIMINA_BONIFICA(?,?,?,?)", parametros, false);                                                                  
    }

    @Override
    public void descartarPedido(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);
        
        FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_LEALTAD.GET_DESCARTAR_PEDIDO(?,?,?,?)", parametros, false);
    }

    @Override
    public String getSaldoPuntos(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        
        mapper.getSaldoPuntos(mapParametros);
        
        return mapParametros.get("vRetorno").toString();
    }
    
   

    @Override
    public Map getPuntosMaximo(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);
        
        Map executeSQLStoredProcedureMap = FarmaDBUtility.executeSQLStoredProcedureMap("FARMA_PUNTOS.F_LST_MONTOS_REDENCION(?, ?, ?)", parametros);
        return executeSQLStoredProcedureMap;
    }
    
    @Override
    public String getIndicadoresPuntos(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        
        mapper.getIndicadoresPuntos(mapParametros);
        
        return mapParametros.get("vRetorno").toString();
    }

    @Override
    public String verificaUsoNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta, String pTipoBusqueda) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        mapParametros.put("cTipoBusqueda_in",pTipoBusqueda);
        
        mapper.verificaUsoNCR(mapParametros);
        
        return mapParametros.get("vRetorno").toString();
    }

    @Override
    public String verificaFechaNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta,
                                   String pFechaNCR) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        mapParametros.put("cFechaNCR_in", pFechaNCR);
        
        mapper.verificaFechaNCR(mapParametros);
        
        return mapParametros.get("vRetorno").toString();
    }

    @Override
    public String verificaCreditoNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta,
                                     String pFechaNCR) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        mapParametros.put("cFechaNCR_in", pFechaNCR);
        
        mapper.verificaCreditoNCR(mapParametros);
        
        return mapParametros.get("vRetorno").toString();
    }

    @Override
    public String getMontoNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVtaNCR) throws Exception {
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVtaNCR);
        
        mapper.getMontoNCR(mapParametros);
        
        return mapParametros.get("vRetorno").toString();
    }

    @Override
    public String obtenerIndicadoresVenta(String pCodGrupoCia, String strCodCia, String strCodLocal,
                                          String pNumPedVta) throws Exception {
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);
        
        return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_LEALTAD.GET_INDICADORES_VENTA(?,?,?,?)", parametros);
    }

    public String getMultiploPtos(String pCodGrupoCia, String strCodCia, String strCodLocal) throws Exception{
            HashMap<String, Object> mapParametros = new HashMap<String, Object>();
            
            mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
            mapParametros.put("cCodCia_in", strCodCia);
            mapParametros.put("cCodLocal_in", strCodLocal);
            mapper.getMultiploPtos(mapParametros);
            return mapParametros.get("vRetorno").toString();
        
    }

    /**
     * Registra inscripcion por turno
     * @author ERIOS
     * @since 22.06.2015
     * @param pCodGrupoCia
     * @param strCodCia
     * @param strCodLocal
     * @param pSecMovCaja
     * @param pNroTarjetaFidelizado
     * @return
     */
    @Override
    public void registrarInscripcionTurno(String pCodGrupoCia, String strCodCia, String strCodLocal,
                                            String pSecMovCaja, String pNroTarjetaFidelizado, String pIdUsu) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cSecMovCaja_in", pSecMovCaja);
        mapParametros.put("vCodTarjeta_in", pNroTarjetaFidelizado);
        mapParametros.put("cIdUsu_in", pIdUsu);
        mapper.registrarInscripcionTurno(mapParametros);
    }

    /**
     * Obtiene cantidad de voucher de inscripcion
     * @author ERIOS
     * @since 22.06.2015
     * @param pCodGrupoCia
     * @param strCodCia
     * @param strCodLocal
     * @param pSecMovCaja
     * @return
     * @throws Exception
     */
    @Override
    public String getInscripcionTurno(String pCodGrupoCia, String strCodCia, String strCodLocal, String pSecMovCaja) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", strCodCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cSecMovCaja_in", pSecMovCaja);
        mapper.getInscripcionTurno(mapParametros);
        return mapParametros.get("vRetorno").toString();
    }
    
    public void rechazarIncripcionPuntos(String pCodGrupoCia, String pNumDocumento, String pSecMovCaja, String pNroTarjetaFidelizado) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cNumDocumento_in", pNumDocumento);
        mapParametros.put("cNumTarjeta_in", pNroTarjetaFidelizado);
        mapParametros.put("cSecMovCaja_in", pSecMovCaja);
        mapper.rechazarIncripcionPuntos(mapParametros);
    }
	// lais X+1
	/*obtiene los producto que aumularon x+1 en el pedido
	 * */
    @Override
    public List<BeanResultado> getProductoProgramaXmas1Pedido(String pCodGrupoCia, String strCodLocal, String pNumPedVta)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", strCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        
        mapper.getProductoProgramaXmas1Pedido(mapParametros);
        
        List<BeanResultado> lstRetorno = (List<BeanResultado>)mapParametros.get("listado");
        
        return  lstRetorno;
    }
    
    /**
     * Elimiar el codigo del programa que el cliente acepto desafiliarse en la tabla de inscripcion auxiliar.
     * LTAVARA 2016.08.24
     */
    @Override
    public void eliminarProgramaInscripcionAux(
                                         String pDniCli,
                                         String pCodPrograma)  throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
    
        mapParametros.put("PI_DNICLI", pDniCli);
        mapParametros.put("PI_COPROG", pCodPrograma);
        
        mapper.eliminarProgramaInscripcionAux(mapParametros);
    }
	// lais X+1
    
    @Override
    public void isPermiteVtaOffline(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(strCodCia);
        parametros.add(strCodLocal);
        parametros.add(pNumPedVta);

        FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_LEALTAD.IS_PERMITE_VTA_OFFLINE(?,?,?,?)", parametros, false);                                                                  
    }
}
