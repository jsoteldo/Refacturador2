package mifarma.ptoventa.cnx;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mifarma.common.FarmaConnectionRemoto;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.BeanConexion;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import oracle.jdbc.OracleDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FarmaVentaCnxUtility {

    private static ArrayList parametros;
    private static final Logger log = LoggerFactory.getLogger(FarmaVentaCnxUtility.class);

    private static ArrayList prm;

    /**
     * Constructor
     */
    public FarmaVentaCnxUtility() {
    }


    public String getIndLineaRecarga() {
        String valorRetorno = "";
        Connection pCnx = getConexionRecarga();
        //ERIOS 19.11.2014 Valida que exista conexion
        if (pCnx == null) {
            valorRetorno = "N";
        } else {
            try {
                String vQuery = "Select sysdate from dual";
                Statement stmt = pCnx.createStatement();
                ResultSet results = stmt.executeQuery(vQuery.trim());
                while (results.next()) {
                    valorRetorno = results.getString(1);
                }
                stmt.close();
                pCnx.close();
                pCnx = null;

                valorRetorno = "S";
            } catch (Exception sqle) {
                try {
                    if (pCnx.isClosed())
                        pCnx.close();
                } catch (Exception e) {
                    ;
                }
                pCnx = null;
                log.error("", sqle);
                valorRetorno = "N";
                enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                                  VariablesPtoVenta.vDestEmailErrorConexion, "Error de Conexion getIndLineaRecarga",
                                  "Error de Comunicacion getIndLineaRecarga",
                                  "�No hay conexi�n con Recargas" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                                  "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                                  FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            }
        }

        return valorRetorno;
    }


    /**
     * @Author Diego Armando Ubilluz Carrillo
     * Envia la consulta de Recarga en Matriz.
     * @param connect_string_thin
     * @return
     */
    public Connection getConexionRecarga() {

        String getDatosRecarga = getCnxRemotoRecarga();

        if (!getDatosRecarga.trim().equalsIgnoreCase("N")) {
            try {

                setBeanConexionRecargas(getDatosRecarga);

                String connect_string_thin = VariablesPtoVenta.conexionRecargas.getCadenaConexion();

                Connection con;
                DriverManager.setLoginTimeout(VariablesPtoVenta.conexionRecargas.getTimeOut());
                DriverManager.registerDriver(new OracleDriver());
                con = DriverManager.getConnection(connect_string_thin);
                con.setAutoCommit(false);
                log.info("getConexion " + connect_string_thin);
                return con;
            } catch (Exception e) {
                log.error("Error getConexion: ", e);
                enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                                  VariablesPtoVenta.vDestEmailErrorConexion, "Error de Conexion getConexionRecarga",
                                  "Error de Comunicacion getConexionRecarga",
                                  "�No hay conexi�n con Recargas" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                                  "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                                  FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + e.getMessage(), "");
                return null;
            }
        }

        return null;
    }

    /**
     * Obtiene La Ruta de Conexion para Recarga
     * @return
     * @throws SQLException
     */
    public String getCnxRemotoRecarga() {
        parametros = new ArrayList();
        log.debug("invocando a FARMA_UTILITY.GET_FECHA_ACTUAL:" + parametros);
        String pResultado = "N";

        try {
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodLocal);
            pResultado =
                    FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_RECARGAS(?,?)", parametros);
        } catch (Exception sqle) {
            // TODO: Add catch code
            log.info(sqle.getMessage());
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorCobro,
                              "Error de Obtener Cadena Conexion getCnxRemotoRecarga",
                              "Error de Obtener Cadena Conexion getCnxRemotoRecarga",
                              "Error en Obtener Cadena Conexion" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }


    public static void enviaCorreoPorCnx(String pCodCia, String pCodLocal, String pReceiverAddress, String pAsunto,
                                         String pTitulo, String pMensaje, String pCCReceiverAddress) {
        log.debug("Enviando Correo.. por BD");
        
        int tamanio = (pMensaje.length() > 1000)?1000:pMensaje.length();
        
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

    /**
     * Obtiene usuario de conexion al RAC por local
     * @author ERIOS
     * @since 2.3.4
     * @return
     */
    public String getCnxRemotoRAC() {
        parametros = new ArrayList();
        String pResultado = "N";

        try {
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodCia);
            parametros.add(FarmaVariables.vCodLocal);
            pResultado =
                    FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_RAC_LOCAL(?,?,?)",
                                                                parametros);
        } catch (Exception sqle) {
            log.error("", sqle);
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorCobro,
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error en Obtener Cadena Conexion" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }

    /**
     * Obtiene usuario de conexion al TICO por local
     * @author RHERRERA
     * @since 2.4.6
     * @return
     */
    public String getCnxRemotoTICO() {
        parametros = new ArrayList();
        String pResultado = "N";

        try {
            pResultado =
                    FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_LOCAL_MARKET", parametros);
            /*
            pResultado = ""+FarmaVariables.vIpServidorTico+"@" +
                            FarmaVariables.vIdUsuTico+"@" +
                            FarmaVariables.vClaveTico+"@"+
                            FarmaVariables.vSidTico+"@"+
                            "1521@2";*/
            String[] pDatosCnx = pResultado.split("@");
            //RHERRERA 15.09.2014 lee datos del servidor TICO
            FarmaVariables.vIdUsuTico = pDatosCnx[1].trim();
            FarmaVariables.vClaveTico = pDatosCnx[2].trim();
            FarmaVariables.vSidTico = pDatosCnx[3].trim();


        } catch (Exception sqle) {
            log.error("", sqle);
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorCobro,
                              "Error de Obtener Cadena Conexion getCnxRemotoTICO",
                              "Error de Obtener Cadena Conexion getCnxRemotoTICO",
                              "Error en Obtener Cadena Conexion" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }

    /**
     * Obtiene conexion generico
     * @author ERIOS
     * @since 2.3.4
     * @param pDatosConexion
     * @return
     * @throws Exception
     */
    private Connection getConexion(String pDatosConexion) throws Exception {

        if (!pDatosConexion.trim().equalsIgnoreCase("N")) {

            setBeanConexionRAC(pDatosConexion);

            String connect_string_thin = FarmaVariables.conexionRAC.getCadenaConexion();

            Connection con;
            DriverManager.setLoginTimeout(FarmaVariables.conexionRAC.getTimeOut());
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection(connect_string_thin);
            con.setAutoCommit(false);
            log.info("getConexion " + connect_string_thin);
            return con;
        }

        return null;
    }

    private Connection getConexionTICO(String pDatosConexion) throws Exception {

        if (!pDatosConexion.trim().equalsIgnoreCase("N")) {

            String[] pDatosCnx = pDatosConexion.split("@");

            String vIpServidorDB = FarmaVariables.vIpServidorTico; //pDatosCnx[0].trim();//rherrera 22.09.2014
            String vIdUsuDB = pDatosCnx[1].trim();
            String vClaveBD = pDatosCnx[2].trim();
            String vSidDB = pDatosCnx[3].trim();
            String vPUERTODB = pDatosCnx[4].trim();
            int pTimeOut = Integer.parseInt(pDatosCnx[5].trim());

            String connect_string_thin =
                String.format(FarmaConstants.CONNECT_STRING_SID, vIdUsuDB, vClaveBD, vIpServidorDB, vPUERTODB, vSidDB);

            setBeanConexionTICO(pDatosConexion);
            FarmaConnectionRemoto.CON_TICO = connect_string_thin;
            log.warn("Conexion Remota:" + FarmaConnectionRemoto.CON_TICO);

            Connection con;
            DriverManager.setLoginTimeout(pTimeOut);
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection(connect_string_thin);
            con.setAutoCommit(false);
            log.info("getConexion " + connect_string_thin);
            return con;
        }

        return null;
    }

    /**
     * Verifica conexion con RAC
     * @aurhor ERIOS
     * @since 2.3.4
     * @return
     */
    public String getIndLineaRAC() throws Exception {
        String valorRetorno = "";
        Connection pCnx = null;
        Statement stmt = null;
        ResultSet results = null;
        try {
            pCnx = getConexion(getCnxRemotoRAC());
            String vQuery = "Select sysdate from dual";
            stmt = pCnx.createStatement();
            results = stmt.executeQuery(vQuery.trim());
            while (results.next()) {
                valorRetorno = results.getString(1);
            }
            results.close();
            stmt.close();
            pCnx.close();
            pCnx = null;

            valorRetorno = "S";
        } catch (Exception sqle) {
            try {
                if (results != null)
                    results.close();
            } catch (Exception e) {
                ;
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                ;
            }
            try {
                if (pCnx != null)
                    pCnx.close();
            } catch (Exception e) {
                ;
            }
            pCnx = null;
            log.error("Error getConexion: ", sqle);
            valorRetorno = "N";
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorConexion, "Error de Conexion getIndLineaRAC",
                              "Error de Comunicacion getIndLineaRAC",
                              "�No hay conexi�n con RAC" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            throw new Exception("�No hay conexi�n con RAC!\n" +
                    "Int�ntelo nuevamente.\n" +
                    "Si persiste el error, llame a Mesa de Ayuda. ");
        }

        return valorRetorno;
    }

    /**
     * Verifica conexion con TICO
     * @aurhor RHERRERA
     * @since 2.6
     * @return
     */
    public String getIndLineaTICO() throws Exception {
        String valorRetorno = "";
        Connection pCnx = null;
        Statement stmt = null;
        ResultSet results = null;
        try {
            pCnx = getConexionTICO(getCnxRemotoTICO());
            String vQuery = "Select sysdate from dual";
            stmt = pCnx.createStatement();
            results = stmt.executeQuery(vQuery.trim());
            while (results.next()) {
                valorRetorno = results.getString(1);
            }
            results.close();
            stmt.close();
            pCnx.close();
            pCnx = null;

            valorRetorno = "S";
        } catch (Exception sqle) {
            try {
                if (results != null)
                    results.close();
            } catch (Exception e) {
                ;
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                ;
            }
            try {
                if (pCnx != null)
                    pCnx.close();
            } catch (Exception e) {
                ;
            }
            pCnx = null;
            log.error("Error getConexion: ", sqle);
            valorRetorno = "N";
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorConexion, "Error de Conexion getIndLineaTICO",
                              "Error de Comunicacion getIndLineaTICO",
                              "�No hay conexi�n con TICO" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            throw new Exception("�No hay conexi�n con TICO!\n" +
                    "Int�ntelo nuevamente.\n" +
                    "Si persiste el error, llame a Mesa de Ayuda. ");
        }

        return valorRetorno;
    }


    void setBeanConexionRAC(String pDatosConexion) {
        FarmaVariables.conexionRAC = setBeanConexion(pDatosConexion);
    }

    /**
     * Verifica conexion con TICO
     * @aurhor RHERRERA
     * @since 2.6
     * @return
     */
    void setBeanConexionTICO(String pDatosConexion) {
        if (!pDatosConexion.trim().equalsIgnoreCase("N")) {

            String[] pDatosCnx = pDatosConexion.split("@");

            BeanConexion beanRAC = new BeanConexion();
            beanRAC.setUsuarioBD(pDatosCnx[1].trim());
            beanRAC.setClaveBD(pDatosCnx[2].trim());
            //beanRAC.setIPBD(pDatosCnx[0].trim());
            beanRAC.setIPBD(FarmaVariables.vIpServidorTico); //rherrera 22.09.2014
            beanRAC.setSID(pDatosCnx[3].trim());
            beanRAC.setPORT(pDatosCnx[4].trim());

            VariablesPtoVenta.conexionTICO = beanRAC;
        }
    }


    public boolean validarPingIP(String ip) {
        boolean val = true;
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("ping -n 1 " + ip);
            int status = p.waitFor();
            /*log.debug("***********VALIDANDO IP*************");
            log.debug(ip + " is " + "/ " + status + "-->" +
                               (status == 0 ? "alive" : "dead"));*/
            if (status == 1)
                val = false;
            if (val)
                log.info("IP." + ip + " SI VALIDO PING ");
            else
                log.info("IP." + ip + " NO VALIDO PING ");
        } catch (Exception l) {
            log.error("Error al verificar conexcion de IP.");
            //log.error("",l);
            val = false;
        }
        return val;
    }

    public boolean esUnaDireccionIPValida(String dir) {
        StringTokenizer st = new StringTokenizer(dir, ".");
        if (st.countTokens() != 4) {
            return false;
        }
        while (st.hasMoreTokens()) {
            String nro = st.nextToken();
            if ((nro.length() > 3) || (nro.length() < 1)) {
                return false;
            }
            int nroInt = 0;
            try {
                nroInt = Integer.parseInt(nro);
            } catch (NumberFormatException s) {
                return false;
            }
            if ((nroInt < 0) || (nroInt > 255)) {
                return false;
            }
        }
        return true;
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
                              "Error en Obtener Cadena Conexion" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }

    public BeanConexion setBeanConexion(String pDatosConexion) {
        BeanConexion beanCnx = null;
        if (!pDatosConexion.trim().equalsIgnoreCase("N")) {

            String[] pDatosCnx = pDatosConexion.split("@");

            beanCnx = new BeanConexion();
            beanCnx.setUsuarioBD(pDatosCnx[1].trim());
            beanCnx.setClaveBD(pDatosCnx[2].trim());
            beanCnx.setIPBD(pDatosCnx[0].trim());
            beanCnx.setSID(pDatosCnx[3].trim());
            beanCnx.setPORT(pDatosCnx[4].trim());
            beanCnx.setTimeOut(FarmaUtility.trunc(pDatosCnx[5].trim()));
            beanCnx.setServiceName(pDatosCnx[6].trim());
        }
        return beanCnx;
    }

    private void setBeanConexionRecargas(String pDatosConexion) {
        VariablesPtoVenta.conexionRecargas = setBeanConexion(pDatosConexion);
    }

    public String getCnxRemotoMATRIZ() {
        parametros = new ArrayList();
        String pResultado = "N";

        try {
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodCia);
            parametros.add(FarmaVariables.vCodLocal);
            pResultado =
                    FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_MATRIZ(?,?,?)", parametros);
        } catch (Exception sqle) {
            log.error("", sqle);
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorCobro,
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error en Obtener Cadena Conexion" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }

    public String getCnxRemotoDELIVERY() {
        parametros = new ArrayList();
        String pResultado = "N";

        try {
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodCia);
            parametros.add(FarmaVariables.vCodLocal);
            pResultado =
                    FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_DELIVERY(?,?,?)", parametros);
        } catch (Exception sqle) {
            log.error("", sqle);
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorCobro,
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error de Obtener Cadena Conexion getCnxRemotoRAC",
                              "Error en Obtener Cadena Conexion" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }
    
    /**
     * @Author Celso Ever Larico Mullisaca
     * Envia la consulta Gestion de Precios en APPS.
     * @param connect_string_thin
     * @return
     */
    public  Connection getConexionGestionPrecio(){ 
        
        String getDatosGPrecios = getCnxRemotoAPPS();
        
        if(!getDatosGPrecios.trim().equalsIgnoreCase("N")){
          try {


              String[] pDatosCnx = getDatosGPrecios.split("@");
              
              String vIpServidorDB = pDatosCnx[0].trim() ;
              //USUARIO LOC_
              String vIdUsuDB = pDatosCnx[1].trim()+FarmaVariables.vCodLocal ;
              String vClaveBD = pDatosCnx[2].trim() ;
              String vSidDB = pDatosCnx[3].trim() ;
              String vPUERTODB = pDatosCnx[4].trim() ;
              int  pTimeOut = Integer.parseInt(pDatosCnx[5].trim());
              
              String connect_string_thin = String.format(FarmaConstants.CONNECT_STRING_SID, vIdUsuDB,vClaveBD,vIpServidorDB,vPUERTODB,vSidDB);
              
              Connection con;
              DriverManager.setLoginTimeout(pTimeOut);
              DriverManager.registerDriver(new OracleDriver());     
              con = DriverManager.getConnection(connect_string_thin);
              con.setAutoCommit(false);
              log.info("getConexionGestionPrecio "+connect_string_thin);
              return con;
          } catch (Exception e) {
              log.error("Error getConexionGestionPrecio: ",e);
              return null;
          } 
        }   
        
        return null;
    }      
    
    /**
     * @Author Celso Ever Larico Mullisaca
     * Obtiene La Ruta de Conexion para Gestion de Precios APPS
     * @return
     * @throws SQLException
     */
    public  String getCnxRemotoGestionPrecios()
    {
        parametros = new ArrayList();
        String pResultado = "N";
        
        try 
        {
            pResultado = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_GESTION_PRECIOS", parametros);
        } catch (Exception sqle) {
            // TODO: Add catch code
            log.info(sqle.getMessage());
            pResultado = "N";
        }
        return pResultado;
    }    
    
    /**
     * Obtiene servidor Gestor de Transacciones Local
     * @author ERIOS
     * @since 10.08.2015
     * @return
     */
    public String getCnxRemotoGTX() {
        parametros = new ArrayList();
        String pResultado = "N";

        try {
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodCia);
            parametros.add(FarmaVariables.vCodLocal);
            pResultado =
                    FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.FARMA_CNX_REMOTO.F_CNX_GTX(?,?,?)", parametros);
        } catch (Exception sqle) {
            log.error("", sqle);
            enviaCorreoPorCnx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                              VariablesPtoVenta.vDestEmailErrorCobro,
                              "Error de Obtener Cadena Conexion getCnxRemotoGTX",
                              "Error de Obtener Cadena Conexion getCnxRemotoGTX",
                              "Error en Obtener Cadena Conexion" + "<br>" + "Int�ntelo nuevamente." + "<br>" +
                              "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                              FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + sqle.getMessage(), "");
            pResultado = "N";
        }
        return pResultado;
    }
}
