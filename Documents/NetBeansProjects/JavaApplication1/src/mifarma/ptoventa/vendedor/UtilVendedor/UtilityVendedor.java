package mifarma.ptoventa.vendedor.UtilVendedor;

import java.sql.SQLException;

import mifarma.ptoventa.vendedor.DAOVendedor.DBVendedor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityVendedor {
    private static final Logger log = LoggerFactory.getLogger(UtilityVendedor.class);
    public UtilityVendedor() {
        super();
    }

    public static String recuperaReporteMetas() {
        String htmlData;
        try {
            htmlData = DBVendedor.recuperaReporteMetas();
        } catch (SQLException e) {
            e.printStackTrace();
            htmlData=null;
        }
        return htmlData;
    }
    
    public static String obtieneDatosGral() {
        String htmlData;
        try {
            htmlData = DBVendedor.obtieneDatosGral();
        } catch (SQLException e) {
            e.printStackTrace();
            htmlData=null;
        }
        return htmlData;
    }
}
