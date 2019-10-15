package mifarma.ptoventa.centromedico.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaVariables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBReceta {
    private static final Logger log = LoggerFactory.getLogger(DBReceta.class);

    private static ArrayList parametros = new ArrayList();

    public DBReceta() {
    }

    public static void cargaListaRegistroRecetas(FarmaTableModel pTableModel, String pFechaInicio,
                                                String pFechaFin) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pFechaInicio);
        parametros.add(pFechaFin);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "ptoventa_cme_vta.REPORTE_REGISTRO_RECETA(?,?,?,?)",
                                                 parametros, false);
    }
    

    public static void obtieneDetalleRegistroReceta(FarmaTableModel pTableModel, String pCia,String pCodLocal,String pNumPed) throws SQLException {

        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPed);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "ptoventa_cme_vta.REPORTE_DETALLE_RECETA(?,?,?,?,?)",
                                                 parametros, false);
    }
    
    public static void getDetalleReceta(ArrayList pLista, String pCia,String pCodLocal,String pNumPed) throws SQLException {

        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPed);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pLista,
                                                          "ptoventa_cme_vta.GET_DET_OPERA_RECETA(?,?,?,?,?)",
                                                          parametros);
    }
    
    public static void getDetRecetaOpera(ArrayList pLista, String pCia,String pCodLocal,String pNumPed) throws SQLException {

        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPed);
        log.debug("ptoventa_cme_vta.GET_DET_OPERA_RECETA ", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pLista, "ptoventa_cme_vta.GET_DET_OPERA_RECETA(?,?,?,?,?)",
                                                 parametros);
    }

    public static String isExisteRecetaBD_Local(String pCiaReceta,String pCodLocalReceta,String pNumPedReceta) throws SQLException {

        parametros = new ArrayList();
        parametros.add(pCiaReceta);
        parametros.add(pCodLocalReceta);
        parametros.add(pNumPedReceta);
        log.debug("ptoventa_cme_vta.IS_EXISTE_RECETA_BD_LOCAL(?,?,?) :", parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("ptoventa_cme_vta.IS_EXISTE_RECETA_BD_LOCAL(?,?,?)",parametros);
    }

    public static String isPedidoConReceta(String vNumPedVta)  throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(vNumPedVta);
        log.debug("ptoventa_cme_vta.IS_PEDIDO_CON_RECETA(?,?,?) :", parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("ptoventa_cme_vta.IS_PEDIDO_CON_RECETA(?,?,?)",parametros);
    }
    
    public static String getDatosRecetaDePedido(String vNumPedVta)  throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(vNumPedVta);
        log.debug("ptoventa_cme_vta.GET_DATOS_RECETA(?,?,?) :", parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("ptoventa_cme_vta.GET_DATOS_RECETA(?,?,?)",parametros);
    }
}
