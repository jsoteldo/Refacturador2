package mifarma.ptoventa.convenioBTLMF.reference;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaDBUtilityRemoto;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.UtilityCPE;

import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.sql.ARRAY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBConv_Responsabilidad {
    private static final Logger log = LoggerFactory.getLogger(DBConv_Responsabilidad.class);
    private static ArrayList parametros = new ArrayList();

    public DBConv_Responsabilidad() {

    }
    
    public static boolean verificaConvenioCobroPorResponsabilidad(String codConvenio) throws SQLException{
        parametros = new ArrayList();
        parametros.add(codConvenio);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.IND_CONV_COBRO_RESP(?)",parametros).equalsIgnoreCase("S")?true:false;
    }
    
    public static boolean verificaAcumulacionPuntoCobroPorResp() throws SQLException{
        parametros = new ArrayList();
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.IND_PUNTOS_COBRO_RESP",parametros).equalsIgnoreCase("S")?true:false;
    }
    
    public static String obtenerEmpleado(String codEmpleado) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(codEmpleado);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.GET_DATOS_EMPLEADO(?,?)", parametros);
    }
    
    public static boolean guardarListCobroResponsabilidad(String codTrabajador, String datTrabajador, String codMotivo, String motRegularizacion, String importeTrabajador) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesVentas.vNum_Ped_Vta);
        parametros.add(codTrabajador);
        parametros.add(datTrabajador);
        parametros.add(codMotivo);
        parametros.add(FarmaUtility.getDecimalNumber(importeTrabajador));
        parametros.add(FarmaVariables.vIdUsu);
        
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.CONV_GRABAR_COBRO_RESPONSAB(?,?,?,?,?,?,?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.CONV_GRABAR_COBRO_RESPONSAB(?,?,?,?,?,?,?,?)", parametros).equals("S")?true:false;
    }
    
    public static String obtenerCodigoTrabajador(String codTrabajador) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(codTrabajador);
        
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.GET_COD_TRABAJADOR(?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.GET_COD_TRABAJADOR(?,?)", parametros);
    }
    
    public static void obtenerListadoVentas(FarmaTableModel tableModelListadoVentas, String fechaIni, String fechaFin) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaIni);
        parametros.add(fechaFin);
        
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.GET_LIST_VENTAS(?,?,?,?): " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(tableModelListadoVentas, "PTOVENTA_CONV_COBRO_RESP.GET_LIST_VENTAS(?,?,?,?)", parametros, false);
    }
    
    public static void obtenerDetalleVentas(FarmaTableModel tableModelDetalleVentas, String numPedVenta) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numPedVenta);
        
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.GET_LIST_DETALLE_VENTAS(?,?,?): " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(tableModelDetalleVentas, "PTOVENTA_CONV_COBRO_RESP.GET_LIST_DETALLE_VENTAS(?,?,?)", parametros, false);
    }
    
    public static void obtenerDetalleEmpleadosPorVenta(FarmaTableModel tableModelEmpleadoVentas, String numPedVenta) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numPedVenta);
        
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.GET_LIST_EMPLEADOS_VENTAS(?,?,?): " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(tableModelEmpleadoVentas, "PTOVENTA_CONV_COBRO_RESP.GET_LIST_EMPLEADOS_VENTAS(?,?,?)", parametros, false);
    }
    
    public static String obtenerRutaDestino(String nombreDocumento) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(nombreDocumento);
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.GET_RUTA_DESTINO_DOC_FTP(?,?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.GET_RUTA_DESTINO_DOC_FTP(?,?,?)", parametros);
    }
    
    public static boolean verificaCargaResponsabilidades(String numDocumento) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numDocumento);
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.GET_VERIFICA_CARGA(?,?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.GET_VERIFICA_CARGA(?,?,?)", parametros).equalsIgnoreCase("N") ? true : false;
    }
    
    public static boolean actualizaCargaResponsabilidades(String numDocumento) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numDocumento);
        log.debug("Invocando PTOVENTA_CONV_COBRO_RESP.GET_UPDATE_CARGA_RESP(?,?,?): " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CONV_COBRO_RESP.GET_UPDATE_CARGA_RESP(?,?,?)", parametros).equalsIgnoreCase("S") ? true : false;
    }
    
}
