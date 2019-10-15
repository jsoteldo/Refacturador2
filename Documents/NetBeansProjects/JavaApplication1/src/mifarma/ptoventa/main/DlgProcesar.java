package mifarma.ptoventa.main;


import com.gs.mifarma.worker.JDialogProgress;

import java.awt.Frame;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.sql.SQLException;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.FacadeCaja;
import mifarma.ptoventa.ce.reference.DBCajaElectronica;
import mifarma.ptoventa.cnx.FarmaVentaCnxUtility;
import mifarma.ptoventa.inventario.reference.UtilityInventario;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recepcionCiega.reference.ConstantsRecepCiega;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgProcesar extends JDialogProgress {

    private static final Logger log = LoggerFactory.getLogger(DlgProcesar.class);

    private static Frame myParentFrame;


    public DlgProcesar() {
        this(null, "", false);
    }

    public DlgProcesar(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
    }

    public void cargaIndImpresionRojoTicket() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getIndImprimeRojo();
            log.info("pResultado:" + pResultado);
            if (pResultado.trim().equalsIgnoreCase("S"))
                VariablesPtoVenta.vIndImprimeRojo = true;
            else
                VariablesPtoVenta.vIndImprimeRojo = false;
        } catch (SQLException err) {
            log.error("", err);
            VariablesPtoVenta.vIndImprimeRojo = false;
        }

        log.info("VariablesPtoVenta.vIndImprimeRojo:" + VariablesPtoVenta.vIndImprimeRojo);
    }

    private void cargaDestinatarioEmailErrorCobro() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getDestinatarioErrorCobro();
            VariablesPtoVenta.vDestEmailErrorCobro = pResultado;
        } catch (SQLException err) {
            log.error("", err);
        }
    }

    private void cargaDestinatarioEmailErrorAnulacion() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getDestinatarioErrorAnulacion();
            VariablesPtoVenta.vDestEmailErrorAnulacion = pResultado;
        } catch (SQLException err) {
            log.error("", err);
        }
    }

    private void cargaDestinatarioEmailErrorImpresion() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getDestinatarioErrorImpresion();
            VariablesPtoVenta.vDestEmailErrorImpresion = pResultado;
        } catch (SQLException err) {
            log.error("", err);
        }
    }
    
    /**
     * @author ERIOS
     * @since 22.10.2015
     */
    private void cargaDestinatarioEmailErrorIntegrador() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getDestinatarioFarmaEmail(ConstantsPtoVenta.FARMA_EMAIL_INTEGRADOR);
            VariablesPtoVenta.vDestEmailErrorIntegrador = pResultado;
        } catch (Exception err) {
            log.error("", err);
        }
    }

    private void cargaIndVerStockLocales() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getIndVerStockLocales();
            VariablesPtoVenta.vIndVerStockLocales = pResultado;
        } catch (SQLException err) {
            VariablesPtoVenta.vIndVerStockLocales = "N";
            log.error("", err);
        }
    }

    private void cargaIndVerRecetarioMagistral() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getIndVerRecetarioMagis();
            VariablesPtoVenta.vIndVerReceMagis = pResultado;
        } catch (SQLException err) {
            log.error("", err);
            VariablesPtoVenta.vIndVerReceMagis = "N";
        }
    }

    /**
     * Grabas la imagenes del programa en el disco duro
     * @author ERIOS
     * @since 24.06.2013
     */
    private void grabarImagenesDisco() {
        try {
            String sufijoEmpresa = DBPtoVenta.obtieneRutaImagen();
            String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
            String carpetaImagenes = DBPtoVenta.getDirectorioImagenes();
            String carpetaComprobantes = DBPtoVenta.getDirectorioComprobantes();
            String nombrePlantillas = DBPtoVenta.getDirectorioPlantillas();
            
            String carpetaSonidos = UtilityInventario.obtenerParametroString(ConstantsRecepCiega.COD_RUTA_SAVE_AUDIO);  //ASOSA - 11/06/2015 - RCIEGAM
            // KMONCADA 25.10.2016 [CENTRO MEDICO] GENERA CARPETAS PARA FORMATOS JASPER
            String carpetaFormatoJasper = DBPtoVenta.getDirectorioFormatosJasper();
            String archivosFormatoJasper = DBPtoVenta.getFormatosJasper();
            String carpetaPdfs = DBPtoVenta.getDirectorioPdfsGenerados();

            //Crear carpeta raiz
            Path dir = Paths.get(carpetaRaiz);
            if (Files.notExists(dir)) {
                Files.createDirectory(dir);
            }

            //Crear carpeta comprobantes
            dir = Paths.get(carpetaRaiz, carpetaComprobantes);
            if (Files.notExists(dir)) {
                Files.createDirectory(dir);
            }

            //Crear carpeta imagenes
            dir = Paths.get(carpetaRaiz, carpetaImagenes);
            if (Files.notExists(dir)) {
                Files.createDirectory(dir);
            }
            
            //INI ASOSA - 11/06/2015 - RCIEGAM
            //Crear carpeta imagenes
            dir = Paths.get(carpetaRaiz, carpetaSonidos);
            if (Files.notExists(dir)) {
                Files.createDirectory(dir);
            }
            //FIN ASOSA - 11/06/2015 - RCIEGAM
            
            // KMONCADA 25.10.2016 [CENTRO MEDICO] FORMASTOS JASPER
            dir = Paths.get(carpetaRaiz, carpetaFormatoJasper);
            if (Files.notExists(dir)) {
                Files.createDirectory(dir);
            }
            
            // JMONZALVE 23.02.2018 Carpeta donde se guardaran los reportes pdf generados por el jasper.
            dir = Paths.get(carpetaRaiz, carpetaPdfs);
            if (Files.notExists(dir)) {
                Files.createDirectory(dir);
            }
            
            //INI JMONZALVE 24042019
            dir = Paths.get(carpetaRaiz, "plantillas");
            if (Files.notExists(dir)) {
                Files.createDirectory(dir);
            }
            //FIN JMONZALVE 24042019
            
            //Elimina el contenido del directorio
            //Verificar el metodo que borra codigos de barra
            /*if(Files.exists(dir)){
                try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir) ){
                    for (Path p : ds) {
                        Files.delete(p);
                    }
                } catch (IOException e) {
                    log.error("",e);
                }
            }*/
            UtilityVentas.eliminaImagenesCodBarra();

            //Copiar imagenes
            Path archivo = Paths.get(carpetaRaiz, carpetaImagenes, "Logo" + sufijoEmpresa);
            URL u = FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/Logo" + sufijoEmpresa);
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            //consejos
            archivo = Paths.get(carpetaRaiz, carpetaImagenes, "consejo.jpg");
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/consejo.jpg");
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            /*//ERIOS 24.10.2014 Copiar imagenes de Facturacion Electronica
            archivo = Paths.get(carpetaRaiz, carpetaImagenes, "LogoE" + sufijoEmpresa);
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/LogoE" + sufijoEmpresa);
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }*/
            //INI ASOSA - 10/03/2015 - 
            archivo = Paths.get(carpetaRaiz, carpetaImagenes, "logo-experto.jpg");
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/logo-experto.jpg");
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            //FIN ASOSA - 10/03/2015 - 
            //INI ASOSA - 08/05/2015 - 
            archivo = Paths.get(carpetaRaiz, carpetaImagenes, "imgPunto.jpg");
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/imgPunto.jpg");
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            //FIN ASOSA - 08/05/2015 - 
            
            //INI JMONZALVE - 23/01/2018 - 
            archivo = Paths.get(carpetaRaiz, "plantillas", nombrePlantillas);
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/plantillas/" + nombrePlantillas);
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            //FIN JMONZALVE - 23/01/2018 - 
            
            //INI JMONZALVE 24042019
            archivo = Paths.get(carpetaRaiz, carpetaImagenes, "Logo_reporte.PNG");
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/Logo_reporte.PNG");
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            //FIN JMONZALVE 24042019
            
            //INI ASOSA - 11/06/2015 - RCIEGAM
            archivo = Paths.get(carpetaRaiz, carpetaSonidos, "atendido.wav");
            log.debug(archivo.toString());
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/sonidos/atendido.wav");
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            
            archivo = Paths.get(carpetaRaiz, carpetaSonidos, "error.wav");
            u = FrmEconoFar.class.getResource("/mifarma/ptoventa/sonidos/error.wav");
            try (InputStream in = u.openStream()) {
                Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
            }
            //FIN ASOSA - 11/06/2015 - RCIEGAM
            
            // KMONCADA 25.10.2016 [CENTRO MEDICO] FORMASTOS JASPER
            String archivos[] = archivosFormatoJasper.split("\\|");
            for(int i=0;i<archivos.length;i++){
                archivo = Paths.get(carpetaRaiz, carpetaFormatoJasper, archivos[i].concat(".jasper"));
                u = FrmEconoFar.class.getResource("/mifarma/ptoventa/formatos_jasper/"+archivos[i].concat(".jasper"));
                try (InputStream in = u.openStream()) {
                    Files.copy(in, archivo, StandardCopyOption.REPLACE_EXISTING);
                }
            }
            
            
        } catch (IOException | NullPointerException e) {
            log.error("Error al grabar imagenes al disco", e);
        } catch (SQLException e) {
            log.error("Error al recuperar informacion de la BBDD", e);
        }
    }

    /**
     * Indicador de servicios FarmaSix
     * @author ERIOS
     * @since 16.07.2013
     */
    public static void cargaIndServicioFarmaSix() {
        String pResultado = "";
        try {
            pResultado = DBPtoVenta.getIndServicioFarmaSix();
            VariablesPtoVenta.vIndFarmaSix = pResultado;
        } catch (SQLException err) {
            log.error("Error al ", err);
            VariablesPtoVenta.vIndFarmaSix = "N";
        }
    }

    /**
     * Indicador de Pinpad
     * @author ERIOS
     * @since 16.08.2013
     */
    public static void cargaIndPinpad() {
        String pResultado = "";
        try {
            pResultado = DBPtoVenta.getIndPinpad();
            VariablesPtoVenta.vIndPinpad = pResultado;
        } catch (SQLException err) {
            log.error("Error al ", err);
            VariablesPtoVenta.vIndPinpad = "N";
        }
    }

    /**
     * Indicador de Impresion url web
     * @author ERIOS
     * @since 16.08.2013
     */
    private void cargaIndImprWeb() {
        String pResultado = "";
        try {
            pResultado = DBPtoVenta.getIndImprWeb();
            VariablesPtoVenta.vIndImprWeb = pResultado.trim();
        } catch (SQLException err) {
            log.error("Error al ", err);
            VariablesPtoVenta.vIndImprWeb = "N";
        }
    }

    @Override
    public void ejecutaProceso() {

        try {
            VariablesPtoVenta.vNumeroDiasSinVentas = DBVentas.obtieneNumeroDiasSinVentas();
            cargaDestinatarioEmailErrorCobro();
            cargaDestinatarioEmailErrorAnulacion();
            cargaDestinatarioEmailErrorImpresion();
            cargaDestinatarioEmailErrorConexion();
            cargaDestinatarioEmailErrorIntegrador();
            
            cargaIndVerStockLocales();

            cargaIndVerRecetarioMagistral();
            grabarImagenesDisco();
            cargaIndServicioFarmaSix();
            cargaIndPinpad();
            cargarIndTico(); /// INDICADOR TICO
            cargaIndImprWeb();
            getIndGestorTx();

            //ERIOS 2.2.8 Salida de mensajes ocultos
            //SystemOutLogger.redirect();

            cargarUsuarioRemotoRAC();
            cargarUsuarioRemotoAPPS();
            cargarUsuarioRemotoMATRIZ();
            cargarUsuarioRemotoDELIVERY();            
            cargarUsuarioRemotoGTX();

            //Thread.sleep(1000);
            /*Carga productos en Farmaventa pero no al cargar Control Asistencia
             *@author EMAQUERA
             *@since  06.11.2015
             */
            
            /*if(VariablesVentas.vInd_Carga_Prod_Vta.equalsIgnoreCase("S")){                
                //ERIOS 2.3.3 Carga de listado de productos            
                SubProcesos subproceso1 = new SubProcesos("GET_PROD_VENTA");
                subproceso1.start();
                while (subproceso1.isAlive()) {
                    ;
                }
            }*/
            log.info("Termino procesar");
        } catch (Exception err) {
            log.error("", err);
            FarmaUtility.showMessage(this, "Error al obtener informacion relevante de la aplicacion.", null);
        }
    }

    /**
     * Indicador de Conciliacion En Linea
     * @author ERIOS
     * @since 29.11.2013
     */
    public static String cargaIndConciliaconOnline() {
        String pResultado = "";
        try {
            pResultado = DBPtoVenta.getIndConciliaconOnline();
        } catch (SQLException err) {
            log.error("Error al ", err);
            pResultado = "N";
        }
        return pResultado;
    }

    /**
     * Se indica la version del sistema
     * @author ERIOS
     * @since 2.2.9
     */
    public static void setVersion() {
        try {
            //ERIOS 27.10.2015 La version se actualiza desde el framework.
            //DBPtoVenta.setVersion();
            (new FacadeCaja()).setVersion();
        } catch (Exception err) {
            log.error("", err);
        }
    }

    /**
     * Indicador de recaudacion centralizada
     * @author ERIOS
     * @since 28.05.2014
     * @return
     */
    public static int cargaIndRecaudacionCentralizada() {
        int pResultado = 0;
        try {
            pResultado = DBPtoVenta.getIndRecaudacionCentralizada();
        } catch (SQLException err) {
            log.error("Error al ", err);
            pResultado = 0;
        }
        return pResultado;
    }

    /**
     * Carga usuario por local para conexion al RAC
     * @author ERIOS
     * @since 2.4.4
     */
    private void cargarUsuarioRemotoRAC() {

        try {
            FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();
            facadeRecaudacion.validarConexionRAC();

        } catch (Exception f) {
            log.error("", f);
        }
    }

    /**
     * Creaci�n:    RHERRERA
     * Fecha:       15.09.2014
     * Descruoci�n: M�todo valida si el local es un Market o un Farma
     */

    private void cargarIndTico() {
        String pResultado = "";

        try {
            pResultado = DBCajaElectronica.getIndTico();
            VariablesPtoVenta.vIndTico = pResultado.trim();
            String indPadre = "";

            if (pResultado.equals("N")) {

                indPadre = DBCajaElectronica.getLocalPadre();
                VariablesPtoVenta.vIndLocalPadre = indPadre;

                if (indPadre.equals("S"))

                    DBCajaElectronica.getLocalTicoIP(VariablesPtoVenta.vLocarMarketH);


            } else {
                // 20.01.2015 rherrera obtien indicador si el tico cuenta con un farma padre
                String vIndFarma = DBCajaElectronica.getIndPadre();
                VariablesPtoVenta.vIndConFarma = vIndFarma;        
                    if (vIndFarma.equals("S"))                
                    FarmaVariables.vIpServidorTico = DBCajaElectronica.getLocalTico();
                    
            }

        } catch (SQLException err) {
            log.error("Error al ", err);
            VariablesPtoVenta.vIndTico = "N";
        }
    }

    /**
     * Indicador de mostrar columnas descuentos
     * @author ERIOS
     * @since 2.4.7
     * @return
     */
    public static boolean cargaIndMostrarColumnasDesc() {
        String pResultado = "";
        try {
            pResultado = DBPtoVenta.getIndMostrarColumnasDesc();
        } catch (SQLException err) {
            log.error("Error al ", err);
            pResultado = "S";
        }
        return (pResultado.equals(FarmaConstants.INDICADOR_S)) ? true : false;
    }

    /**
     * Conexion al servidor APPS
     * @author ERIOS
     * @since 09.12.2014
     */
    private void cargarUsuarioRemotoAPPS() {

        try {
            FarmaVentaCnxUtility facade = new FarmaVentaCnxUtility();

            FarmaVariables.conexionAPPS = facade.setBeanConexion(facade.getCnxRemotoAPPS());

            FarmaVariables.conexionAPPS.setUsuarioBD("LOC_" + FarmaVariables.vCodLocal);

        } catch (Exception f) {
            log.error("", f);
        }
    }

    private void cargarUsuarioRemotoMATRIZ() {

        try {
            FarmaVentaCnxUtility facade = new FarmaVentaCnxUtility();

            FarmaVariables.conexionMATRIZ = facade.setBeanConexion(facade.getCnxRemotoMATRIZ());

        } catch (Exception f) {
            log.error("", f);
        }
    }

    private void cargarUsuarioRemotoDELIVERY() {

        try {
            FarmaVentaCnxUtility facade = new FarmaVentaCnxUtility();

            FarmaVariables.conexionDELIVERY = facade.setBeanConexion(facade.getCnxRemotoDELIVERY());

        } catch (Exception f) {
            log.error("", f);
        }
    }

    /**
     * Indicador de pantalla de Garantizados
     * @author ERIOS
     * @since 12.01.2015
     * @return
     */
    public static String cargaIndActGarantizados() {
        String pResultado = "";
        try {
            pResultado = DBPtoVenta.getIndActGarantizados();
        } catch (SQLException err) {
            log.error("Error al ", err);
            pResultado = "N";
        }
        return pResultado;
    }
    
    private void cargaDestinatarioEmailErrorConexion() {
        String pResultado = "";
        try {
            pResultado = DBVentas.getDestinatarioFarmaEmail(ConstantsPtoVenta.FARMA_EMAIL_CONEXION);
            VariablesPtoVenta.vDestEmailErrorConexion = pResultado;
        } catch (Exception err) {
            log.error("", err);
        }
    }

    /**
     * Se obtiene el indicador de Gestor de Transacciones
     * @author ERIOS
     * @since 10.08.2015
     * @return
     */
    public static String getIndGestorTx(boolean pForzarConsulta) {
        //ERIOS 16.09.2015 Para buscar en BBDD
        if(pForzarConsulta){
            try {
                VariablesPtoVenta.strIndGestorTx = DBPtoVenta.getIndGestorTx();
            } catch (SQLException err) {
                log.error("", err);
                VariablesPtoVenta.strIndGestorTx = "N";
            }
        }
        return VariablesPtoVenta.strIndGestorTx;
    }
    
    public static String getIndGestorTx() {
        return getIndGestorTx(true);
    }
    
    /**
     * Carga conexion al Gestor de Transacciones
     * @author ERIOS
     * @since 10.08.2015
     */
    public void cargarUsuarioRemotoGTX() {

        try {
            FarmaVentaCnxUtility facade = new FarmaVentaCnxUtility();

            VariablesPtoVenta.conexionGTX = facade.setBeanConexion(facade.getCnxRemotoGTX());

        } catch (Exception f) {
            log.error("", f);
        }
    }
}
