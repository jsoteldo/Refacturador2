package mifarma.ptoventa.caja.daoNuevoCobro;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Map;

import mifarma.common.FarmaConnection;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BDNuevoCobro {
    private static final Logger log = LoggerFactory.getLogger(BDNuevoCobro.class);

    private static ArrayList parametros = new ArrayList();
    
    public BDNuevoCobro() {
        super();
    }
    
    public static String verifica_NuevoProcesoCobroActivo() throws SQLException {
        parametros = new ArrayList();
        log.debug("PTOVENTA_PROCESA_COBRO_GRAL.RECUPERA_IND_PROCESO_ACTIVO" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_PROCESA_COBRO_GRAL.RECUPERA_IND_PROCESO_ACTIVO",
                                                           parametros);
    }
    
    public static String getIndPedConvenio(String vNumPedVta) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(vNumPedVta);
        log.debug("invocando a PTOVENTA_PROCESA_COBRO_GRAL.CAJ_F_VAR_IND_PED_CONVENIO(?,?,?):" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_PROCESA_COBRO_GRAL.CAJ_F_VAR_IND_PED_CONVENIO(?,?,?)",
                                                           parametros);
    }
    
    public static String getPermiteCobrarPedido(String pNumPedVta) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        log.debug("PTOVENTA_PROCESA_COBRO_GRAL.F_EXISTE_STOCK_PEDIDO(?,?,?):" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_PROCESA_COBRO_GRAL.F_EXISTE_STOCK_PEDIDO(?,?,?)",
                                                           parametros);
    }

    public static String getCodFPDolares() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_PROCESA_COBRO_GRAL.CAJ_OBTIENE_FP_DOLARES(?,?)",
                                                           parametros).trim();
    }
    
    public static String getMayuscula(String pCadena) {
        return pCadena.toUpperCase();
    }
    public static String executeSQLStoredProcedureMap_enviarArray(String pStoredProcedure,
                                                                  Map<String, Object> pParameters) throws SQLException {
        if (pStoredProcedure == null || pStoredProcedure.trim().length() == 0){
            throw new SQLException("Expresion del Stored Procedure no Definido", "FarmaError", 9002);}
        
        CallableStatement stmt;
        
        Connection cnx = FarmaConnection.getConnection();
        stmt = cnx.prepareCall("{ call ? := " + pStoredProcedure + " }");
        
        stmt.registerOutParameter(1, OracleTypes.VARCHAR);
        for(int i=0;i<pParameters.size();i++){
            int index=2+i;
            int key=i+1;
            if(key<=5){
                stmt.setString(index, (String)pParameters.get(getMayuscula("a_Dato"+key)));
            }else{
                stmt.setArray(index, (oracle.sql.ARRAY)pParameters.get(getMayuscula("a_Dato"+key)));
            }
        }
        stmt.execute();
        
        String pRpta = stmt.getString(1);
        stmt.close();
        return pRpta;
    }
    
    public static String getPermiteCobrarInsumosPedido(String pNumPedVta) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumPedVta);
        log.debug("PTOVENTA_PROCESA_COBRO_GRAL.F_EXISTE_STOCK_INSUMOS_PEDIDO(?,?,?):" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_PROCESA_COBRO_GRAL.F_EXISTE_STOCK_INSUMOS_PEDIDO(?,?,?)",
                                                           parametros);
    }
}
