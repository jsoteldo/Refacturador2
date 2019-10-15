package mifarma.ptoventa.centromedico.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

public class DBAtencionMedica {
    public DBAtencionMedica() {
        super();
    }
    
    public static ArrayList obtenerListadoPacientesEspera(String pCodGrupoCia, String pCodEstado, String pCodMedico) throws SQLException {
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodEstado);
        parametros.add(pCodMedico);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA.CENTRO_MEDICO.F_LISTA_ESPERA(?,?,?)", parametros);
        return resultado;
    }
    
    public static ArrayList obtenerListaTipoInformante()throws Exception{
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA.CENTRO_MEDICO.F_LST_CMB_TIPO_INFORMANTE", parametros);
        return resultado;
    }
    
    public static ArrayList obtenerListaEstadoFuncionesBiologicas()throws Exception{
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA.CENTRO_MEDICO.F_LST_CMB_FUNC_BIOLOGICAS", parametros);
        return resultado;
    }
    
    public static ArrayList obtenerListaEstadoGeneralExaFisico()throws Exception{
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA.CENTRO_MEDICO.F_LST_CMB_EXA_FISICO_ESTADO", parametros);
        return resultado;
    }
    
    public static ArrayList obtenerListaTipoDiagnostico()throws Exception{
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA.CENTRO_MEDICO.F_LST_CMB_TIPO_DIAGNOSTICO", parametros);
        return resultado;
    }
    
    public static ArrayList obtenerListaViaAdministracion()throws Exception{
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA.CENTRO_MEDICO.F_LST_CMB_VIA_ADMINISTRACION", parametros);
        return resultado;
    }
    
    public static List obtenerListaDiagnostico()throws Exception{
        ArrayList parametros = new ArrayList();
        return FarmaDBUtility.executeSQLStoredProcedureListMap("PTOVENTA.CENTRO_MEDICO.F_LISTAR_DIAGNOSTICO", parametros);
    }
    
    public static List obtenerProductoReceta()throws Exception{
        ArrayList parametros = new ArrayList();
        return FarmaDBUtility.executeSQLStoredProcedureListMap("PTOVENTA.CENTRO_MEDICO.F_LISTAR_DIAGNOSTICO", parametros);
    }
}
