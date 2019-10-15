package mifarma.ptoventa.iscbolsas.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DBISCBolsas {

    private static final Log logger = LogFactory.getLog(DBISCBolsas.class);
    private static ArrayList parametros = new ArrayList();

    /**@author JCHAVEZ
     * Obtiene los productos de las bolsas
     * @fecha  10.07.19
     * @throws SQLException
     **/
    public static void obtenerProductosISC(ArrayList pArrayList) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        //parametros.add("");
        logger.debug("PTOVTA_BOLSAS.F_GET_PRODUCTOS_ISC(?,?)" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayList, "PTOVTA_BOLSAS.F_GET_PRODUCTOS_ISC(?,?)",
                                                          parametros);
    }
    
    public static void obtenerProductosISC(ArrayList pArrayList,String pCodProd) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd);
        logger.debug("PTOVTA_BOLSAS.F_GET_PRODUCTOS_ISC(?,?,?)" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayList, "PTOVTA_BOLSAS.F_GET_PRODUCTOS_ISC(?,?,?)",
                                                          parametros);
    }    

    public static boolean getPblTabGral_ICBPer() throws SQLException {
        boolean resul = false;
        String llave;
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        //parametros.add("");
        logger.debug("PTOVTA_BOLSAS.F_GET_PBL_TAB_GRAL_ICBPER(?,?)" + parametros);
        llave =
                FarmaDBUtility.executeSQLStoredProcedureStr("PTOVTA_BOLSAS.F_GET_PBL_TAB_GRAL_ICBPER(?,?)", parametros);

        if (llave.equals("S")) {
            resul = true;
        } else {
            resul = false;
        }

        return resul;
    }

    public static boolean getPblTabGral_BolsasAmarillas() throws SQLException {
        boolean resul = false;
        String llave;
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        //parametros.add("");
        logger.debug("PTOVTA_BOLSAS.F_GET_BOLSAS_AMARILLAS(?,?)" + parametros);
        llave = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVTA_BOLSAS.F_GET_BOLSAS_AMARILLAS(?,?)", parametros);

        if (llave.equals("S")) {
            resul = true;
        } else {
            resul = false;
        }

        return resul;
    }
}
