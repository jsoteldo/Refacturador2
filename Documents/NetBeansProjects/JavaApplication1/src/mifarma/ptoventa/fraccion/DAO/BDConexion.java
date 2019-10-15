package mifarma.ptoventa.fraccion.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.ArrayList;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.VariablesPtoVenta;

import oracle.jdbc.OracleDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BDConexion {

    private static ArrayList parametros;
    private static final Logger log = LoggerFactory.getLogger(BDConexion.class);
    public String cadenaConexion = "";
    private static ArrayList prm;

    /**
     * Constructor
     */
    public BDConexion() {
    }

    public Connection getConexionFraccion() {

        String getDatosSF = getCnxRemotoAPPS();

        if (!getDatosSF.trim().equalsIgnoreCase("N")) {
            try {


                String[] pDatosCnx = getDatosSF.split("@");

                String vIpServidorDB = pDatosCnx[0].trim();
                String vIdUsuDB = "LOC_" + FarmaVariables.vCodLocal;
                String vClaveBD = pDatosCnx[2].trim();
                String vSidDB = pDatosCnx[3].trim();
                String vPUERTODB = pDatosCnx[4].trim();
                int pTimeOut = Integer.parseInt(pDatosCnx[5].trim());

                String connect_string_thin =
                    String.format(FarmaConstants.CONNECT_STRING_SID, vIdUsuDB, vClaveBD, vIpServidorDB, vPUERTODB,
                                  vSidDB);
                cadenaConexion = connect_string_thin;
                Connection con;
                DriverManager.setLoginTimeout(pTimeOut);
                DriverManager.registerDriver(new OracleDriver());
                con = DriverManager.getConnection(connect_string_thin);
                con.setAutoCommit(false);
                log.info("getConexionFraccion " + connect_string_thin);
                return con;
            } catch (Exception e) {
                log.error("Error getConexionFraccion: ", e);
                return null;
            }
        }
        return null;
    }

    public String getCnxRemotoAPPS() {
        parametros = new ArrayList();
        String pResultado = "N";

        try {
            //parametros.add(FarmaVariables.vCodGrupoCia);
            //parametros.add(FarmaVariables.vCodCia);
            //parametros.add(FarmaVariables.vCodLocal);
            pResultado =
                    FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_APPS", parametros);
        } catch (Exception sqle) {
            log.error("", sqle);
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorCobro,
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error en Obtener Cadena Conexion" + "<br>" + "Inténtelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }

    public static void enviaCorreoPorCnx(String pCodCia, String pCodLocal, String pReceiverAddress, String pAsunto,
                                         String pTitulo, String pMensaje, String pCCReceiverAddress) {
        log.debug("Enviando Correo.. por BD");

        int tamanio = (pMensaje.length() > 1000) ? 1000 : pMensaje.length();

        prm = new ArrayList();
        prm.add(pCodCia);
        prm.add(pCodLocal);
        prm.add(pReceiverAddress);
        prm.add(pAsunto);
        prm.add(pTitulo);
        prm.add(pMensaje.substring(0, tamanio));
        prm.add(pCCReceiverAddress);

        //ERIOS 19.11.2014 Envia correo por hilo
        new Thread() {
            public void run() {
                try {
                    FarmaDBUtility.executeSQLStoredProcedure(null, "FARMA_UTILITY.envia_correo(?,?,?,?,?,?,?)", prm,
                                                             true);
                } catch (Exception sql) {
                    log.error("", sql);
                }
            }
        }.start();
    }
}
