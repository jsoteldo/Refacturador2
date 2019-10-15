package mifarma.ptoventa.tomainventario.reference;

import java.io.File;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Statement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;
import java.util.Map;

import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.administracion.usuarios.reference.DBUsuarios;
import mifarma.ptoventa.administracion.usuarios.reference.VariablesUsuarios;
import mifarma.ptoventa.reference.VariablesPtoVenta;


import mifarma.ptoventa.tomainventario.DlgFiltroEstadoItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBTomaInv {
    //SlEYVA INICIO 05/03/2019
    private static Date date = new Date();
    private static DateFormat hourFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static DateFormat yearFormat = new SimpleDateFormat("yyyy");

    public static String pathOriginal = "";
    private static String dbName = "eckerd.s3db";

    private static String pathMove = "old_bd\\eckerd_" + hourFormat.format(date) + ".s3db";

    private static ArrayList parametros;
    private static final Logger log = LoggerFactory.getLogger(DBTomaInv.class);
    public static int CANT_TOMAS = 0;

    public DBTomaInv() {
    }

    public static int getCantPdaCargadoTomaInv() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma);
        return FarmaDBUtility.executeSQLStoredProcedureInt("PTOVENTA_TOMA_INV.CANT_PDA_CARGADOS(?,?,?)", parametros);
    }

    public static void updateLgtcTomaInventarioCantPda(int cantPdaUp) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma);
        parametros.add(cantPdaUp);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.UPDATE_CANT_PDA_CARG(?,?,?,?)", parametros,
                                                 true);
    }


    //INICIO-SLEYVA-2019

    public static void cantTomaInventarios() {
        try {
            ArrayList lista = new ArrayList();
            DBTomaInv.getListaTomasInv(lista);
            CANT_TOMAS = lista.size();
            lista.clear();
        } catch (SQLException f) {
            f.printStackTrace();
        }

    }
    //INICIO-SLEYVA-2019


    public void createNewDatabase() {

        String url = "jdbc:sqlite:" + pathOriginal + dbName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("El nombre del Driver es: " + meta.getDriverName());
                System.out.println("Base de datos creado, satisfactoriamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void creaBD() {
        File BDSqlite = new File(pathOriginal + dbName);
        try {
            if (BDSqlite.exists() && new File(pathOriginal + "old_bd").exists()) {
                Path origenPath = FileSystems.getDefault().getPath(pathOriginal + dbName);
                Path destinoPath = FileSystems.getDefault().getPath(pathOriginal + pathMove);
                Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                this.createNewDatabase();
                this.crearProducto();
                this.crearProductoBarra();
                this.crearProductoInventario();
                this.crearTablaUsuario();
            } else {
                new File(pathOriginal + "old_bd").mkdirs();
                this.createNewDatabase();
                this.crearProducto();
                this.crearProductoBarra();
                this.crearProductoInventario();
                this.crearTablaUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection connect() {
        String url = "jdbc:sqlite:" + pathOriginal + dbName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion a SQLite establecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void crearProductoBarra() {
        String url = "jdbc:sqlite:" + pathOriginal + dbName;

        // SQL statement for creating a new table
        // String sql = "CREATE TABLE ProductoBarra  ( CO_BARRA nvarchar(20),  CO_PRODUCTO nchar(6) , FOREIGN KEY(CO_PRODUCTO) REFERENCES Producto(CO_PRODUCTO))";
        String sql =
            "CREATE TABLE productobarra( co_barra nvarchar(20), co_producto nchar(6), FOREIGN KEY(co_producto) REFERENCES Producto(co_producto))";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Tabla ProductoBarra creada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void crearProducto() {
        String url = "jdbc:sqlite:" + pathOriginal + dbName;

        // SQL statement for creating a new table
        // String sql = "CREATE TABLE Producto (CO_PRODUCTO nchar(6) , DE_PRODUCTO nvarchar(60), DE_UNIDAD nvarchar(60), DE_UNIDAD_FRACCION nvarchar(60), IN_PROD_FRACCIONADO nchar(1), VA_FRACCION int, CO_LABORATORIO nchar(4), DE_LABORATORIO nvarchar(60) )";
        String sql =
            "CREATE TABLE producto(co_producto nchar(6), de_producto nvarchar(60), de_unidad nvarchar(60), de_unidad_fraccion nvarchar(60), in_prod_fraccionado nchar(1), va_fraccion int, co_laboratorio nchar(4), de_laboratorio nvarchar(60) )";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Tabla Producto creada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void crearProductoInventario() {
        String url = "jdbc:sqlite:" + pathOriginal + dbName;

        // SQL statement for creating a new table
        // String sql = "CREATE TABLE ProductoInventario (CO_PRODUCTO nchar(6) , CO_LABORATORIO nchar(4), IN_PROD_FRACCIONADO nchar(1), VA_FRACCION int, CA_ENTERO int( 3 ) NOT NULL, CA_FRACCION int( 3 ) NOT NULL, NU_ANAQUEL nvarchar(5) NOT NULL, NU_ANAQUEL_CONCAT nvarchar ( 100 ), PRIMARY KEY(CO_PRODUCTO) ) ";
        String sql =
            "CREATE TABLE productoinventario (codigo INTEGER, co_producto nchar(6), de_producto TEXT, co_laboratorio nchar(4), in_prod_fraccionado nchar(1), va_fraccion int, ca_entero int( 3 ) NOT NULL, ca_fraccion int( 3 ) NOT NULL, nu_anaquel nvarchar(5) NOT NULL, nu_anaquel_concat nvarchar ( 100 ) NOT NULL, PRIMARY KEY(codigo) ) ";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Tabla ProductoInventario creada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void crearTablaUsuario() {
        String url = "jdbc:sqlite:" + pathOriginal + dbName;
        String sql = "CREATE TABLE usuario( nombre TEXT, de_clave_pda TEXT )";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Tabla Usuario creada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarUsuario() {
        String url = "jdbc:sqlite:" + pathOriginal + dbName;
        String sql = "DELETE FROM usuario";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getPedidosPendientes() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vNuSecUsu);
        log.debug("PTOVENTA_CAJ.CAJ_CANT_CAB_PEDIDOS_PENDIENT(?,?,?)" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureString("PTOVENTA_CAJ.CAJ_CANT_CAB_PEDIDOS_PENDIENT(?,?,?)",
                                                              parametros);
    }


    public static ArrayList getDataPBarra() throws SQLException {
        ArrayList arrDatos = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(arrDatos, "PTOVENTA_TOMA_INV.GET_DATA_PBARRA(?)",
                                                          parametros);
        System.out.println("extrayendo data de producto barra");
        return arrDatos;
    }

    public static String getCodigoProductoBarra(String pCodProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(pCodProducto);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_TOMA_INV.CODIGOPRODUCTOBARRA(?,?)", parametros);
    }

    public static ArrayList getDataProducto() throws SQLException {
        ArrayList arrDatos = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(arrDatos, "PTOVENTA_TOMA_INV.GET_DATA_PRODUCTO(?)",
                                                          parametros);
        System.out.println("extrayendo data de producto");
        return arrDatos;
    }


    public void setDataPBarra(String cobarra, String coproducto, Connection conn) {
        String sql = "INSERT INTO ProductoBarra(CO_BARRA,CO_PRODUCTO) VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            if (conn == null)
                return;
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cobarra);
            ps.setString(2, coproducto);
            ps.executeUpdate();
        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        }
    }

    public void setDataProducto(String CO_PRODUCTO, String DE_PRODUCTO, String DE_UNIDAD_PRODUCTO,
                                String IN_PROD_FRACCIONADO, String VA_FRACCION, String DE_UNIDAD_FRACCION,
                                String CO_LABORATORIO, String DE_LABORATORIO, Connection conn) {
        String sql =
            "INSERT INTO Producto (CO_PRODUCTO,DE_PRODUCTO,DE_UNIDAD,DE_UNIDAD_FRACCION,IN_PROD_FRACCIONADO,VA_FRACCION,CO_LABORATORIO,DE_LABORATORIO) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            if (conn == null)
                return;
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, CO_PRODUCTO);
            ps.setString(2, DE_PRODUCTO);
            ps.setString(3, DE_UNIDAD_PRODUCTO);
            ps.setString(4, DE_UNIDAD_FRACCION);
            ps.setString(5, IN_PROD_FRACCIONADO);
            ps.setString(6, VA_FRACCION);
            ps.setString(7, CO_LABORATORIO);
            ps.setString(8, DE_LABORATORIO);
            ps.executeUpdate();
        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        }
    }

    public void setPdaUserName(String name, Connection conn) {
        String sql = "INSERT INTO usuario(nombre, de_clave_pda) VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            //String claveUser = DBTomaInv.obtenerClaveLoginUsu();
            String claveUser = "eckerd" + yearFormat.format(date);

            if (conn == null)
                return;
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, claveUser);
            ps.executeUpdate();
        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        }
    }

    public static void limpiarTemporalTomaInv(String codloca, String SecToma) throws SQLException {
        parametros = new ArrayList();
        parametros.add(codloca);
        parametros.add(SecToma);
        log.debug("PKG_INVENTARIO_TERCEROS.sp_limpiar_temporal " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PKG_INVENTARIO_TERCEROS.sp_limpiar_temporal(?,?)", parametros,
                                                 false);
    }

    public static void cargaTemporalTomaInv(String a_cod_local, String a_sec_toma, String a_cod_prod,
                                            Number a_val_frac_local, Number a_cantidad, String a_usuario,
                                            String anaquel, String origen) throws SQLException {
            parametros = new ArrayList();
            parametros.add(a_cod_local.trim());
            parametros.add(a_sec_toma.trim());
            parametros.add(a_cod_prod.trim());
            parametros.add(a_val_frac_local);
            parametros.add(a_cantidad);
            parametros.add(a_usuario.trim());
            parametros.add(anaquel.trim());
            parametros.add(origen.trim());

            FarmaDBUtility.executeSQLStoredProcedure(null,
                                                     "PKG_INVENTARIO_TERCEROS.sp_calc_diferencia_carga(?,?,?,?,?,?,?,?)",
                                                     parametros, true);
    }

    public static void cargaTomaInv(String a_cod_local, String a_sec_toma, String a_usuario) throws SQLException {
        parametros = new ArrayList();
        parametros.add(a_cod_local);
        parametros.add(a_sec_toma);
        parametros.add(a_usuario);
        log.debug("PKG_INVENTARIO_TERCEROS.sp_carga_inventario " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PKG_INVENTARIO_TERCEROS.sp_carga_inventario(?,?,?)",
                                                 parametros, false);
    }

    //SLEYVA FIN 05/03/2019


    public static void getListaLabs(FarmaTableModel pTableModel, String flagServMedicos) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(flagServMedicos);
        log.debug("valor " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_LABS(?)", parametros, true);
    }

    public static String generarCabTomaInv(String pCantLab, String pTipTomaInv, String pIdUsu) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCantLab.trim());
        parametros.add(pTipTomaInv.trim());
        /**
     * Modificado : usuario quien se logueo
     * @author : dubilluz
     * @since  : 20.07.2007
     */
        parametros.add(pIdUsu.trim());
        log.debug("valor " + parametros);
        //parametros.add(FarmaVariables.vIdUsu.trim());
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_TOMA_INV.TI_GUARDAR_CAB_TI(?,?,?,?,?)",
                                                           parametros);
    }

    public static void generarDetTotTomaInv(String pSecToma, String pIdUsu) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecToma.trim());
        /**
       * Modificado : usuario quien se logueo
       * @author : dubilluz
       * @since  : 20.07.2007
       */
        parametros.add(pIdUsu.trim());
        log.debug("valor " + parametros);
        //parametros.add(FarmaVariables.vIdUsu.trim());
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_GUARDAR_DET_TOT_TI(?,?,?,?)", parametros,
                                                 false);
    }

    public static void generarDetTomaInv(String pSecToma, String pCodLab, String pIdUsu) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecToma.trim());
        parametros.add(pCodLab.trim());
        /**
     * Modificado : usuario quien se logueo
     * @author : dubilluz
     * @since  : 20.07.2007
     */
        parametros.add(pIdUsu.trim());
        log.debug("valor " + parametros + pIdUsu);
        //parametros.add(FarmaVariables.vIdUsu.trim());
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_GUARDAR_DET_TI(?,?,?,?,?)", parametros,
                                                 false);
    }

    public static void getListaTomasHist(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_HIST_TOMAS_INV(?,?)",
                                                 parametros, false);
    }

    public static void getListaTomasInv(ArrayList lista) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista, "PTOVENTA_TOMA_INV.TI_LISTA_TOMAS_INV(?,?)",
                                                          parametros);
    }

    public static void getListaTomasInv(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_TOMAS_INV(?,?)", parametros,
                                                 false);
    }

    //SLEYVA 25/01/2019

    public static void ListaTomaInventario(ArrayList lista) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista, "PTOVENTA_TOMA_INV.TI_LISTA_TOMAS_INV(?,?)",
                                                          parametros);
    }

    public static void ListaAnaquelesTomaInventario(ArrayList lista) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(VariablesTomaInv.vCodProd.trim());

        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista, "PTOVENTA_TOMA_INV.LISTA_ANAQUELES_TOMA_INV(?,?,?,?)",
                                                          parametros);
    }
    //SLEYVA 25/01/2019

    public static void getListaLabsXToma(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_LABS_TOMA_INV(?,?,?)",
                                                 parametros, false);
    }

    public static void getListaProdsXLabXToma(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(VariablesTomaInv.vCodLab.trim());
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_PROD_LAB_TOMA_INV(?,?,?,?)",
                                                 parametros, false);
    }


    public static void getListaProdsXToma(ArrayList lista) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vLaboratorioXToma ? VariablesTomaInv.vCodLab.trim() : "");
        parametros.add(VariablesTomaInv.vSecToma.trim());
        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista, "PTOVENTA_TOMA_INV.TI_LISTA_PROD_TOMA_INV(?,?,?,?)",
                                                          parametros);
    }

    public static void getListaDetallePdaDigitacion(ArrayList lista, String prod) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(prod.trim());
        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista, "PTOVENTA_TOMA_INV.DETALLE_PDA_DIGIT_INV(?,?,?,?)",
                                                          parametros);
    }


    public static void ingresaCantidadProdInv(String pCantToma, String anaquel,
                                              String anaquelVerifica,String fraccion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(VariablesTomaInv.vCodLab.trim());
        parametros.add(VariablesTomaInv.vCodProd.trim());
        parametros.add(pCantToma);
        parametros.add(anaquel);
        parametros.add("D");
        parametros.add(anaquelVerifica);
        parametros.add(fraccion);
        log.debug("PTOVENTA_TOMA_INV.TI_INGRESA_CANT_PROD_TI(?,?,?,?,?,?,?,?,?,?" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_INGRESA_CANT_PROD_TI(?,?,?,?,?,?,?,?,?,?)",
                                                 parametros, false);

    }

    public static void getListaProdsDiferencias(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(VariablesTomaInv.vCodLab.trim());
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_TOMA_INV.TI_LISTA_DIF_PROD_LAB_TOMA_INV(?,?,?,?)",
                                                 parametros, false);
    }

    public static void getListaProdsDiferenciasHist(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(VariablesTomaInv.vCodLab.trim());
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,
                                                 "PTOVENTA_TOMA_INV.TI_LISTA_DIF_PROD_LAB_TOMA_I_H(?,?,?,?)",
                                                 parametros, false);
    }

    public static void rellenaCantNoIngresadas() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(VariablesTomaInv.vCodLab.trim());
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_RELLENA_CERO_LAB_TOMA_INV(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargarToma() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(FarmaVariables.vIdUsu.trim());
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_CARGA_TOMA_INV(?,?,?,?)", parametros,
                                                 false);
    }
    ///SLEYVA 29/01/2019 INICIO

    public static void reasignarRolesUsuario() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vIdUsu.trim());
        log.info("PTOVENTA_TOMA_INV.SP_DEV_ROLES_USUARIO " + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.SP_DEV_ROLES_USUARIO(?,?,?)", parametros,
                                                 false);

    }

    public static void insercionRolesUsuario() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.SP_VAL_INSERT(?,?)", parametros, false);
    }

    public static void deleteRolesUsuario() throws SQLException {
        FarmaDBUtility.executeSQLUpdate("DELETE FROM TMP_AUX_PBL_ROL_USU");
    }
    ///SLEYVA 29/01/2019 fin

    public static void activaRolInventariador() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vIdUsu.trim());
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.SP_ACTIVA_ROL_INVENTARIO(?,?,?)", parametros,
                                                 false);
    }
    
    public static void desactivaRolInventariador() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.SP_DESACTIVA_ROL_INVENTARIO(?,?)", parametros,
                                                 false);
    }
    
    public static String obtenerRutaConfigPda() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_TOMA_INV.OBTENER_RUTA_CONFIG_PDA(?)", parametros);
    }

    public static String obtenerClaveLoginUsu() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vIdUsu);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_TOMA_INV.OBTENER_CLAVE_LOGIN(?,?,?)", parametros);
    }

    public static void verificarDescongelamiento() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(FarmaVariables.vIdUsu.trim());
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_VERIFICA_EST_COG_PROD_LOC(?,?,?,?)",
                                                 parametros, false);
    }

    public static void anularToma() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(FarmaVariables.vIdUsu.trim());
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_ANULA_TOMA_INV(?,?,?,?)", parametros,
                                                 false);
    }

    public static String obtenerIndTomaIncompleta() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_TOMA_INV.TI_OBTIENE_IND_TOMA_INCOMPLETA(?,?,?)",
                                                           parametros);
    }

    public static ArrayList getListaLabsTomaIncompleta() throws SQLException {
        parametros = new ArrayList();
        ArrayList rpta = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        FarmaDBUtility.executeSQLStoredProcedureArrayList(rpta,
                                                          "PTOVENTA_TOMA_INV.TI_LISTA_LABS_TOMA_INCOMPLETA(?,?,?)",
                                                          parametros);
        return rpta;
    }

    public static void getListaLabsItemsLab(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_LABS_ITEMS_LAB", parametros,
                                                 false);
    }

    public static void getListaItemsxLab(FarmaTableModel pTableModel) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vCodLab.trim());
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_ITEMS_LAB(?,?,?)",
                                                 parametros, false);
    }

    public static ArrayList obtieneCodigoLaboratorios() throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pOutParams,
                                                          "PTOVENTA_TOMA_INV.TI_LISTA_COD_LABORATORIOS(?,?)",
                                                          parametros);
        return pOutParams;
    }

    public static ArrayList obtieneProductosPorLaboratotio(String pCodLab) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodLab);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pOutParams,
                                                          "PTOVENTA_TOMA_INV.TI_LISTA_PROD_IMPRESION(?,?,?)",
                                                          parametros);
        return pOutParams;
    }

    public static ArrayList obtieneTotalItemsToma(String pSecToma) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecToma);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pOutParams, "PTOVENTA_TOMA_INV.TI_TOTAL_ITEMS_TOMA(?,?,?)",
                                                          parametros);
        return pOutParams;
    }

    public static ArrayList obtieneInformacionValorizada(String pSecToma) throws SQLException {
        ArrayList pOutParams = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecToma);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pOutParams,
                                                          "PTOVENTA_TOMA_INV.TI_INFORMACION_VALORIZADA(?,?,?)",
                                                          parametros);
        return pOutParams;
    }

    public static void listaDiferenciasConsolidado(FarmaTableModel pTableModel,
                                                   String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_DIFERENCIAS_CONSOLIDADO(?,?,?)",
                                                 parametros, false);
    }

    public static void listaDiferenciasConsolidadoExport(FarmaTableModel pTableModel,
                                                         String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_DIFERENCIAS_CONS_EXPORT(?,?,?)",
                                                 parametros, false);
    }

    public static void listaDiferenciasExport(FarmaTableModel pTableModel, String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_DIFERENCIAS_EXPORT(?,?,?)",
                                                 parametros, false);
    }


    public static void listaDiferenciasConsolidadoExport(ArrayList lista, String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista, "PTOVENTA_TOMA_INV.TI_DIFERENCIAS_CONS_EXPORT(?,?,?)",
                                                          parametros);
    }


    public static void listaDiferencias(FarmaTableModel pTableModel, String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("PTOVENTA_TOMA_INV.LISTA_DIFERENCIAS_TOMA(?,?,?)", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.LISTA_DIFERENCIAS_TOMA(?,?,?)",
                                                 parametros, false);
    }


    public static void listaDiferenciasConsolidadoDiario(FarmaTableModel pTableModel,
                                                         String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_DIA.TI_DIFERENCIAS_CONSOLIDADO(?,?,?)",
                                                 parametros, false);
    }

    public static void listaDiferenciasConsolidados(FarmaTableModel pTableModel,
                                                    String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_DIA.TI_DIFERENCIAS_CONTEO(?,?,?)",
                                                 parametros, false);
    }


    public static void listaDiferenciasHistorico(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_DIA.TI_DIFERENCIAS_HISTORICO(?,?)",
                                                 parametros, true);

    }


    public static void listaDiferenciasConsolidadoFiltro(FarmaTableModel pTableModel,
                                                         String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        parametros.add(VariablesPtoVenta.vCodFiltro);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_DIF_CONSOLIDADO_FILTRO(?,?,?,?)",
                                                 parametros, false);
    }

    public static void obtieneIndTomaInvForUpdate(ArrayList pArrayList, String pSecTomaInv,
                                                  String pIndProceso) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        parametros.add(pIndProceso);
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayList,
                                                          "PTOVENTA_TOMA_INV.TI_OBTIENE_IND_FOR_UPDATE(?,?,?,?)",
                                                          parametros);
    }

    public static void getListaItemsxLabMovimiento(FarmaTableModel pTableModel) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vCodLab.trim());
        log.debug("", parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_OBTIENE_PROD_MOVIMIENTO(?,?,?)",
                                                 parametros, false);
    }

    /**
     * Lista los laboratorios segun el estado
     * @author dubilluz
     * @since  08.01.2008
     */
    public static void cargaLabxTomaFiltro(FarmaTableModel pTableModel, String pfiltroEstado) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(pfiltroEstado.trim());
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_LABS_TOMA_FILTRO(?,?,?,?)",
                                                 parametros, false);
    }

    public static String obtieneCodigoProductoBarra(String pCodBarra) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(pCodBarra);
        log.debug("invocando  a PTOVENTA_VTA.VTA_REL_COD_BARRA_COD_PROD(?,?):" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_VTA.VTA_REL_COD_BARRA_COD_PROD(?,?)", parametros);
    }


    /**
     * retorna los datos del producto para ingresar cantidad y Fraccion.
     * @author dubilluz
     * @since  21.12.2009
     * @param pArrayList
     * @param pCodProducto
     * @throws SQLException
     */
    public static void getInfoProd(ArrayList pArrayList, String pCodProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProducto.trim());
        log.debug("load PTOVENTA_TOMA_INV.TI_F_VAR_DATOS_PROD(?,?,?) :" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayList, "PTOVENTA_TOMA_INV.TI_F_VAR_DATOS_PROD(?,?,?)",
                                                          parametros);
    }

    /**
     * INSERTA LOS DATOS PARA EL CONTEO DE PRODUCTOS EN LA TOMA TRADICIONAL
     * @author JMIRANDA
     * @since  21.12.2009
     * @param pSecTomaInv
     * @param pSecAuxConteo
     * @param pCodProd
     * @param pCodBarra
     * @param pCant
     * @param pValFracConteo
     * @param pIndNoFound
     * @throws SQLException
     */
    public static void insertAuxConteo(String pSecTomaInv, int pSecAuxConteo, String pCodProd, String pCodBarra,
                                       String pCant, String pValFracConteo, String pIndNoFound) throws SQLException {

        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv.trim());
        parametros.add(pCodProd.trim());
        parametros.add(pCodBarra.trim());
        parametros.add(pCant.trim());
        parametros.add(pValFracConteo);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(FarmaVariables.vIpPc);
        parametros.add(pIndNoFound);
        parametros.add(new Integer(pSecAuxConteo));
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_P_INS_AUX_CONTEO_TOMA(?,?,?,?,?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PTOVENTA_TOMA_INV.TI_P_INS_AUX_CONTEO_TOMA(?,?,?,?,?,?,?,?,?,?,?)",
                                                 parametros, false);
    }

    //JMIRANDA 23.12.09

    public static String getSecAuxConteo(String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        log.debug("PTOVENTA_TOMA_INV.TI_F_GET_SEC_AUX_CONTEO(?,?,?):" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_TOMA_INV.TI_F_GET_SEC_AUX_CONTEO(?,?,?)",
                                                           parametros);
    }

    public static void obtieneListaConteoToma(FarmaTableModel pTableModel, String pSecTomaInv) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        parametros.add(FarmaVariables.vIdUsu);
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_F_CUR_LIS_CONTEO_TOMA(?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_F_CUR_LIS_CONTEO_TOMA(?,?,?,?)",
                                                 parametros, false);
    }

    public static void updateProdConteo(String pSecTomaInv, String pAuxSecConteo, String pCantEnt,
                                        String pCantFraccion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        parametros.add(pAuxSecConteo);
        parametros.add(pCantEnt);
        parametros.add(pCantFraccion);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(FarmaVariables.vIpPc);
        parametros.add("N");
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_P_UPD_CONTEO_TOMA(?,?,?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_P_UPD_CONTEO_TOMA(?,?,?,?,?,?,?,?,?)",
                                                 parametros, false);

    }

    public static void delProdConteo(String pSecTomaInv, String pAuxSecConteo) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        parametros.add(pAuxSecConteo);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(FarmaVariables.vIpPc);
        parametros.add("S");
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_P_DEL_CONTEO_TOMA(?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_P_DEL_CONTEO_TOMA(?,?,?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void rellenaTomaConCeros() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_RELLENA_CERO_TOMA_INV(?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_RELLENA_CERO_TOMA_INV(?,?,?)", parametros,
                                                 false);
    }


    public static void getListaProdsDiferenciasTotales(FarmaTableModel pTableModel) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV(?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV_NVO(?,?,?)",
                                                 parametros, false);
    }

    public static void getListaProdDifCargado(ArrayList pArrayLista, String ordenar) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV_CARGADO(?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista,
                                                          "PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV_CARGADO(?,?,?)",
                                                          parametros);
    }

    //SLEYVA-INICIO-2019

    public static void getListaProdsDiferenciasTotales(ArrayList pArrayLista, String ordenar) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(ordenar);
        parametros.add(FarmaVariablesdos.vMayorDiferencia);
        parametros.add(VariablesTomaInv.vDiferenciasXToma ? VariablesTomaInv.vCodLab.trim() : "");
        parametros.add(FarmaVariablesdos.vRol);
        
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV_NVO(?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista,
                                                          "PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV_NVO(?,?,?,?,?,?,?)",
                                                          parametros);
    }
    
    public static void getListaImprimirTomaInv(ArrayList pArrayLista, String ordenar) throws SQLException {
        
        
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(ordenar);
        parametros.add(FarmaVariablesdos.cantRegistros);
        parametros.add(FarmaVariablesdos.pagInicio);
        parametros.add(FarmaVariablesdos.pagFin);
       
        
        log.debug("invocando a PTOVENTA_TOMA_INV.FN_IMPRIMIR_REPORTE(?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista,
                                                          "PTOVENTA.PTOVENTA_PRINT_TOMA_INV.FN_IMPRIMIR_REPORTE(?,?,?,?,?,?,?)",
                                                          parametros);
    }


    public static void getListaProdsDiferenciasTotalesToma(ArrayList pArrayLista, String ordenar) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(ordenar);
        parametros.add(FarmaVariablesdos.vMayorDiferencia);
        parametros.add(VariablesTomaInv.vDiferenciasXToma ? VariablesTomaInv.vCodLab.trim() : "");
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV_NVO(?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista,
                                                          "PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_INV_NVO(?,?,?,?,?,?)",
                                                          parametros);
    }

    public static void getListaProdsDiferenciasXEstado(ArrayList pArrayLista, String ordenar,
                                                       String estado) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(estado);
        parametros.add(VariablesTomaInv.vDiferenciasXToma ? VariablesTomaInv.vCodLab.trim() : "");
        log.debug("invocando a PTOVENTA_TOMA_INV.LIST_DIF_TOMA_INV_ESTADO(?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista,
                                                          "PTOVENTA_TOMA_INV.LIST_DIF_TOMA_INV_ESTADO(?,?,?,?,?)",
                                                          parametros);                                  
    }

    public static void getResumenValorizadoInventario(ArrayList pArrayLista) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        log.debug("invocando a PTOVENTA_TOMA_INV.STOCK_VALORIZADO(?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista, "PTOVENTA_TOMA_INV.STOCK_VALORIZADO(?,?,?)",
                                                          parametros);
    }


    public static void getListaPrimerConteo(ArrayList pArrayLista, boolean xLab) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        if (!xLab) {
            parametros.add(VariablesTomaInv.vCodLab);
        } else if (xLab) {
            parametros.add("");
        }
        log.debug("invocando a PTOVENTA_TOMA_INV.LISTA_PRIMERA_CUENTA(?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista,
                                                          "PTOVENTA_TOMA_INV.LISTA_PRIMERA_CUENTA(?,?,?,?)",
                                                          parametros);
    }


    public static void getLabTomaInv(ArrayList pArrayLista) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_lista_LAB_TOMA(?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pArrayLista, "PTOVENTA_TOMA_INV.TI_lista_LAB_TOMA(?,?,?)",
                                                          parametros);
    }
    //SLEYVA-FIN-2019

    public static void getProductoLabTomaInv(ArrayList getProductoLabTomaInv, String pCodLab) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesTomaInv.vSecToma.trim());
        parametros.add(pCodLab.trim());
        log.debug("invocando a PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_LAB_INV(?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(getProductoLabTomaInv,
                                                          "PTOVENTA_TOMA_INV.TI_LISTA_DIF_TOMA_LAB_INV(?,?,?,?)",
                                                          parametros);
    }

    public static void enviaCorreoCodBarraNoExiste(String pSecTomaInv, String pCodBarra,
                                                   String pGlosa) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pSecTomaInv);
        parametros.add(pCodBarra.trim());
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(pGlosa.trim());
        log.debug("PTOVENTA_TOMA_INV.TI_P_ENVIA_EMAIL_NO_FOUND(?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_TOMA_INV.TI_P_ENVIA_EMAIL_NO_FOUND(?,?,?,?,?,?)",
                                                 parametros, false);
    }


    public static void actualizarInidices() throws SQLException {
        parametros = new ArrayList();
        log.debug("Ptoventa_Toma_Inv_AUX.ti_p_actualiza_indices:" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "Ptoventa_Toma_Inv_AUX.ti_p_actualiza_indices", parametros,
                                                 false);
    }

    public static String getIndPermiteImpConStock() throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vNuSecUsu);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_TOMA_INV.IND_IMP_CON_STK(?,?,?)", parametros);
    }

    public static void inicializaLISTADIFEPRODLABTOMAINVLIST(ArrayList lista,
                                                             String vLaboratorio) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(VariablesTomaInv.vSecToma);
        if (VariablesTomaInv.vVistaTotal.equals("N"))
            parametros.add(vLaboratorio);
        else
            parametros.add("");
        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista,
                                                          "PTOVENTA_TOMA_INV.LISTA_DIFE_PROD_LAB_TOMA_INV(?, ?, ?, ?)",
                                                          parametros);
    }


    public static void listaTotalValorizado(ArrayList lista, String vLaboratorio) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(VariablesTomaInv.vSecToma);
        if (VariablesTomaInv.vVistaTotal.equals("N"))
            parametros.add(vLaboratorio);
        else
            parametros.add("");
        FarmaDBUtility.executeSQLStoredProcedureArrayList(lista,
                                                          "PTOVTA_TOMAINVENTARIO.LISTA_TOTAL_VALORIZADO(?, ?, ?, ?)",
                                                          parametros);
    }


}
