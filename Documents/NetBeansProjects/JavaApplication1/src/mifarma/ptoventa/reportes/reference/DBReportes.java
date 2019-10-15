package mifarma.ptoventa.reportes.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBReportes {
    private static final Logger log = LoggerFactory.getLogger(DBReportes.class);

    private static ArrayList parametros = new ArrayList();

    public DBReportes() {
    }

    public static void cargaListaRegistroVentas(FarmaTableModel pTableModel, String pFechaInicio,
                                                String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_REGISTRO_VENTA(?,?,?,?)",
                                                 parametros, false);
    }

    /**
     * @since 10.03.2015 ERIOS Se agrega el parametro pTipo
     * @param pTableModel
     * @param pFechaInicio
     * @param pFechaFin
     * @param codConvenios
     * @param pTipo
     * @throws SQLException
     */
    public static void cargaListaRegistroVentasConvenio(FarmaTableModel pTableModel, String pFechaInicio,
                                                        String pFechaFin, String codConvenios, String pTipo) throws SQLException {
        pTableModel.clearTable();
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(codConvenios);
        parametros.add(pTipo);
        log.info("PTOVENTA_REPORTE.REPORTER_REG_VTA_CONVENIO(?,?,?,?,?,?)" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTER_REG_VTA_CONVENIO(?,?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void obtieneDetalleRegistroVentas(FarmaTableModel pTableModel, String pCodigo) throws SQLException {

        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodigo);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_DETALLE_REGISTRO_VENTA(?,?,?)",
                                                 parametros, false);
    }

    public static void obtieneComprobantes_Venta(FarmaTableModel pTableModel, String pCodigo) throws SQLException {

        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodigo);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_COMPROBANTES_VENTA(?,?,?)",
                                                 parametros, false);
    }

    public static void obtieneComprobantes_Venta_Detalle(FarmaTableModel pTableModel, String pCodigo,
                                                         String pNumPedVtaRep, String tipcCompr) throws SQLException {

        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodigo);
        parametros.add(pNumPedVtaRep);
        parametros.add(tipcCompr);
        //log.debug("",parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_DETALLE_COMPROBANTE(?,?,?,?,?)", parametros,
                                                 false);
    }

    public static ArrayList obtieneInfoResumen(String pFechaIni, String pFechaFin) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia.trim());
        parametros.add(FarmaVariables.vCodLocal.trim());
        parametros.add(pFechaIni);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pOutParams,
                                                          "PTOVENTA_REPORTE.REPORTE_RESUMEN_VENTA(?,?,?,?)",
                                                          parametros);
        return pOutParams;
    }

    public static ArrayList obtieneInfoResumenNotaCredito(String pFechaIni, String pFechaFin) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia.trim());
        parametros.add(FarmaVariables.vCodLocal.trim());
        parametros.add(pFechaIni);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pOutParams,
                                                          "PTOVENTA_REPORTE.REPORTE_RESUMEN_VTA_NOT_CREDIT(?,?,?,?)",
                                                          parametros);
        return pOutParams;
    }

    public static void cargaListaFormadePago(FarmaTableModel pTableModel, String pFechaInicio,
                                             String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_FORMAS_DE_PAGO(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaDetalledeVentas(FarmaTableModel pTableModel, String pFechaInicio,
                                                 String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_DETALLE_VENTAS(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaResumenProductosVendidos(FarmaTableModel pTableModel, String pFechaInicio,
                                                          String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_RESUMEN_PRODUCTOS_VEND(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaFiltroDetalleVentas(FarmaTableModel pTableModel, String pFechaInicio,
                                                     String pFechaFin, String pFiltro) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pFiltro);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_FILTRO_PRODUCTOS_VEND(?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaVentasporVendedor(FarmaTableModel pTableModel, String pFechaInicio,
                                                   String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_VENTAS_POR_VENDEDOR(?,?,?,?)",
                                                 parametros, false);
    }

    /**
     * @author ERIOS
     * @since 15.04.2016
     * @param pTableModel
     * @param pFechaInicio
     * @param pFechaFin
     * @throws SQLException
     */
    public static void cargaListaPuntosRentables(FarmaTableModel pTableModel, String pFechaInicio,
                                                   String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_PUNTOS_RENTABLES(?,?,?,?)",
                                                 parametros, false);
    }
    
    public static void cargaListaDetalleVentasporVendedor(FarmaTableModel pTableModel, String pFechaInicio,
                                                          String pFechaFin, String pUsuario) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pUsuario);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_DETALLE_VENTAS_VEND(?,?,?,?,?)", parametros,
                                                 false);
    }

    public static void cargaListaVentasporProducto(FarmaTableModel pTableModel, String pFechaInicio,
                                                   String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_VENTAS_POR_PRODUCTO(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaVentasporProductoFiltro(FarmaTableModel pTableModel, String pFechaInicio,
                                                         String pFechaFin, String pTipoFiltro,
                                                         String pCodFiltro) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pTipoFiltro);
        parametros.add(pCodFiltro);

        log.debug("", parametros);

        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_VENTAS_POR_PRODUCTO_F(?,?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaVentasPorDia(FarmaTableModel pTableModel, String pFechaInicio,
                                              String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_VETAS_POR_DIA(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaResumenVentasDetallado(FarmaTableModel pTableModel, String pFechaInicio,
                                                        String pFechaFin, String pCodProd) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pCodProd);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_DETALLADO_RESUMEN_VTA(?,?,?,?,?)",
                                                 parametros, false);
    }

    //Hist�rico de Creaci�n/Modificaci�n
    //ERIOS      27.03.2005   Creaci�n
    //DlgVentasPorHora

    public static void cargaListaVentasporHora(FarmaTableModel pTableModel, String pFechaInicio, String pFechaFin,
                                               String filtroDia) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(filtroDia);
        log.debug("", parametros);      
            FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "TMP_REP_ERN.REP_VENTAS_POR_HORA(?,?,?,?,?)", parametros,
                                                 false);
      
    }

    public static void cargaListaVentasDiaMes(FarmaTableModel pTableModel) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesReporte.vFechaInicio);
        parametros.add(VariablesReporte.vFechaFin);
        parametros.add(VariablesReporte.vCodFiltro);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "TMP_REP_MHR.REPORTE_VENTAS_DIA_MES(?,?,?,?,?)",
                                                 parametros, false);
    }

    //Hist�rico de Creaci�n/Modificaci�n
    //ERIOS      06.07.2006   Creaci�n
    //DlgProductoFaltaCero

    public static void cargaListaFaltaCero(FarmaTableModel pTableModel, String pFechaInicio, String pFechaFin,
                                           String filtroDia) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(filtroDia);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "TMP_REP_ERN.REP_PRODUCTOS_FALTA_CERO(?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaDetFaltaCero(FarmaTableModel pTableModel, String codProd, String pFechaInicio,
                                              String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codProd);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "TMP_REP_ERN.REP_DET_FALTA_CERO(?,?,?,?,?)", parametros,
                                                 false);
    }

    public static void listaVentasporProductoLab(FarmaTableModel pTableModel, String pFechaInicio,
                                                 String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_VTA_PRODUCTO_LAB(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaProductosABC(FarmaTableModel pTableModel, String filtro, String ind, String fechaIni,
                                              String fechaFin) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(filtro);
        parametros.add(ind);
        parametros.add(fechaIni);
        parametros.add(fechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "TMP_REP_ERN.REP_PRODUCTO_ABC(?,?,?,?,?,?)", parametros,
                                                 false);
    }

    public static void cargaListaProductosABCFiltro(FarmaTableModel pTableModel, String filtro, String ind,
                                                    String filtroTipo, String fechaIni,
                                                    String fechaFin) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(filtro);
        parametros.add(ind);
        parametros.add(filtroTipo);
        parametros.add(fechaIni);
        parametros.add(fechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "TMP_REP_ERN.REP_FILTRO_PRODUCTO_ABC(?,?,?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaConsolidadoVtasProd(FarmaTableModel pTableModel, String fechaIni,
                                                     String fechaFin) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaIni);
        parametros.add(fechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_CONSOLIDADO_VTA_PROD(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaVentasporProductoVirtuales(FarmaTableModel pTableModel, String pFechaInicio,
                                                            String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_VTA_PRODUCTO_VIRTUAL(?,?,?,?)",
                                                 parametros, false);
    }

    public static void borrarRegistroDetFaltaCero(String pCodProd, String pSecUsuLocal,
                                                  String pFecha) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd);
        parametros.add(pFecha);
        parametros.add(pSecUsuLocal);
        log.debug("borrarRegistroDetFaltaCero " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_REPORTE.REPORTE_BORRAR_DET_FALTA_CERO(?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaPedidosAnulNoCob(FarmaTableModel pTableModel, String pFechaInicio,
                                                  String pFechaFin) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("cargaListaPedidosAnulNoCob " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_PEDIDOS_ANUL_NO_COB(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaUnidadVtaLocal(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        log.debug("cargaListaUnidadVtaLocal " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_UNID_VTA_LOCAL", parametros,
                                                 false);
    }

    public static void cargaListaUnidadVtaLocalXFiltro(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(VariablesPtoVenta.vTipoFiltro);
        parametros.add(VariablesPtoVenta.vCodFiltro);
        log.debug("cargaListaUnidadVtaLocalXFiltro " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_UNID_VTA_LOCAL_FILTRO(?,?)",
                                                 parametros, false);
    }
    //

    public static void cargaListaProdSinVtaNDias(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodLocal);
        log.debug("cargaListaProdSinVtaNDias " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_PROD_SIN_VTA_N_DIAS(?)",
                                                 parametros, false);
    }

    public static void cargaListaProdSinVtaNDiasXFiltro(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesPtoVenta.vTipoFiltro);
        parametros.add(VariablesPtoVenta.vCodFiltro);
        log.debug("cargaListaProdSinVtaNDiasXFiltro " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_PROD_SIN_VTA_NDIAS_FIL(?,?,?)",
                                                 parametros, false);
    }

    /** Listado de formas de pago por pedido
     *@author: JCORTEZ
     *@since: 05/08/07
     */

    public static void cargaListaFormasPago(FarmaTableModel pTableModel, String nroPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(nroPedido);
        log.debug("carga lista de fornmas de pago " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_DETALLE_FORMAS_PAGO(?,?,?)",
                                                 parametros, false);

    }

    public static void cargaListaFormasPagoDel(ArrayList pArray, String nroPedido) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(nroPedido);
        log.debug("carga lista de fornmas de pago " + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArray,
                                                          "PTOVENTA_REPORTE.REPORTE_FORMAS_PAGO_RESUMEN(?,?,?)",
                                                          parametros);

    }

    /**
     * Obtiene el Numero de  Dias Sin Ventas
     * @author : dubilluz
     * @since  : 21.08.2007
     */
    public static String obtieneNumeroDiasSinVentas() throws SQLException {
        parametros = new ArrayList();
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_REPORTE.NUMERO_DIAS_SIN_VENTAS", parametros);
    }


    public static ArrayList cargaListaVV_Impr(String pFechaIni, String pFechaFin) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia.trim());
        parametros.add(FarmaVariables.vCodLocal.trim());
        parametros.add(pFechaIni);
        parametros.add(pFechaFin);
        log.debug("PTOVENTA_REPORTE.REPORTE_VENTAS_VENDEDOR_IMP(?,?,?,?)" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pOutParams,
                                                          "PTOVENTA_REPORTE.REPORTE_VENTAS_VENDEDOR_IMP(?,?,?,?)",
                                                          parametros);
        return pOutParams;
    }

    /**
     * Obtiene el Reporte Detalle del vendedor por tipo de Venta
     * @author : asolis-
     * @since  : 26.11.2008
     * */

    public static void cargaListaDetalleVentasporVendedorTipo(FarmaTableModel pTableModel, String pFechaInicio,
                                                              String pFechaFin, String pUsuario,
                                                              String pTipo) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pUsuario);
        parametros.add(pTipo);

        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_DET_VENTAS_VEND_TIPO(?,?,?,?,?,?)",
                                                 parametros, false);


    }

    public static void cargaListaVentasporVendedorTipo(FarmaTableModel pTableModel, String pFechaInicio,
                                                       String pFechaFin, String pTipo) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pTipo);
        log.debug("PTOVENTA_REPORTE.REPORTE_VENTAS_POR_VEND_TIPO(?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_REPORTE.REPORTE_VENTAS_POR_VEND_TIPO(?,?,?,?,?)",
                                                 parametros, false);
    }
    
    //INI RMULLISACA 26/08/2019
    public static String indicadorNuevoReporteVentasVendedorGG(String pFechaInicio, String pFechaFin) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.info("PTOVENTA_REPORTE.IND_NUEVO_REPOR_VENT_X_VEND_GG(?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_REPORTE.IND_NUEVO_REPOR_VENT_X_VEND_GG(?,?)", parametros);
    }
    //FIN RMULLISACA 26/08/2019
    
    public static void listaReporteGuias(FarmaTableModel pTableModel, String pFechaInicio, String pFechaFin,
                                         String pNumGuia) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pNumGuia);
        parametros.add("");
        log.debug("PTOVENTA_REPORTE.REPORTE_GUIAS(?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTE_GUIAS(?,?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaRegistroVentasDelivery(FarmaTableModel pTableModel, String pFechaInicio,
                                                        String pFechaFin, String pWhere) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        parametros.add(pWhere);
        log.info("PTOVENTA_REPORTE.REPORTER_REG_VTA_DELIVERY(?,?,?,?,?)" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_REPORTE.REPORTER_REG_VTA_DELIVERY(?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void listaCamposFiltroReporteVtaDelivery(ArrayList pArray, String pTabla) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(pTabla);
        log.info("PTOVENTA_REPORTE.GET_CAMPOS_REPORTE(?): " + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArray, "PTOVENTA_REPORTE.GET_CAMPOS_REPORTE(?)",
                                                          parametros);

    }

    public static String obtenerTipoDatoColumn(String pTabla, String pColumna) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(pTabla);
        parametros.add(pColumna);
        log.info("PTOVENTA_REPORTE.GET_TIPO_DATO_COLUMN(?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_REPORTE.GET_TIPO_DATO_COLUMN(?,?)", parametros);
    }
    
    public static void obtenerReporteComisionGigante(FarmaTableModel pTableModel, String pCodGrupoCia, String pCodLocal, String pMesId) throws SQLException {
        pTableModel.clearTable();
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pMesId);
        
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA.PTOVENTA_REPORTE.F_CUR_RESUMEN_COMISION_GIGANTE(?,?,?)",parametros, false);
    }

    /**
     * @author ERIOS
     * @since 18.04.2016
     * @return
     * @throws SQLException
     */
    public static String getVerTipoComision() throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_REPORTE.GET_VER_TIPO_COMISION(?,?)", parametros);
    }

    /**
     * @author ERIOS
     * @since 16.09.2016
     * @return
     * @throws SQLException
     */
    static String getVerRentables() throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_REPORTE.GET_VER_RENTABLES(?,?)", parametros);
    }
    
    //INI AOVIEDO 18/04/2017
    public static void obtenerReporteComisionGarantizado(FarmaTableModel pTableModel, String pCodGrupoCia, String pCodLocal, String pMesId) throws SQLException {
        pTableModel.clearTable();
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pMesId);
        log.info("PTOVENTA.PKG_CONCURSO_GARANTIZADOS.F_CUR_RESUMEN_COMISION_GARANTI(?,?,?): " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA.PKG_CONCURSO_GARANTIZADOS.F_CUR_RESUMEN_COMISION_GARANTI(?,?,?)",parametros, false);
    }
    //FIN AOVIEDO 18/04/2017
    
    public static String indicadorNuevoReporteVentasVendedor() throws SQLException {
        ArrayList parametros = new ArrayList();
        log.info("PTOVENTA_REPORTE.IND_NUEVO_REPOR_VENT_X_VEND: " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_REPORTE.IND_NUEVO_REPOR_VENT_X_VEND", parametros).trim();
    }
}
