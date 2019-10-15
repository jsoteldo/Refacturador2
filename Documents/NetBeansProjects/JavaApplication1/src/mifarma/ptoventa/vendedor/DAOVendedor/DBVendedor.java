package mifarma.ptoventa.vendedor.DAOVendedor;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBVendedor {
    private static ArrayList parametros;
    private static final Logger log = LoggerFactory.getLogger(DBVendedor.class);
    public DBVendedor() {
        super();
    }

    public static String recuperaReporteMetas() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vNuSecUsu);
        parametros.add(FarmaVariables.vIdUsu);
        log.debug("PTOVENTA_BONO_KMS.RECUPERA_INFOR_METAS(?,?,?,?) :" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr( "PTOVENTA_BONO_KMS.RECUPERA_INFOR_METAS(?,?,?,?)",
                                                            parametros);
    }

    public static String obtieneDatosGral() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vNuSecUsu);
        log.debug("PTOVENTA_BONO_KMS.FN_GET_DATOS_GRAL(?,?,?) :" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr( "PTOVENTA_BONO_KMS.FN_GET_DATOS_GRAL(?,?,?)",
                                                            parametros);
    }
    
    public static String verificaEstadoReporteMetas() throws SQLException {
        parametros = new ArrayList();
        log.debug("PTOVENTA_BONO_KMS.FN_GET_VERIFICA_REPORTE:" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr( "PTOVENTA_BONO_KMS.FN_GET_VERIFICA_REPORTE",
                                                            parametros);
    }
}
