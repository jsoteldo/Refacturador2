package mifarma.ptoventa.reference;

import farmaciasperuanas.reference.DBRefacturadorElectronico;
import farmaciasperuanas.reference.UtilityRefacturadorElectronico;
import farmaciasperuanas.reference.VariablesRefacturadorElectronico;

import java.awt.Frame;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaSecurity;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.SubProcesoCPE;

import mifarma.ptoventa.administracion.usuarios.DlgCambioClave;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.cnx.FarmaCnxPool;
import mifarma.ptoventa.controlAsistencia.reference.ConstantsControlAsistencia;
import mifarma.ptoventa.controlAsistencia.reference.UtilityControlAsistencia;
import mifarma.ptoventa.hilos.SubProcesoAlertUpCotizacionPrecio;
import mifarma.ptoventa.hilos.SubProcesos;
import mifarma.ptoventa.hilos.SubProcesosALertatUp;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.main.FrmEconoFar;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.com.du.JConfirmDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityPtoVenta {

    static final Logger log = LoggerFactory.getLogger(UtilityPtoVenta.class);

    UtilityCaja utilityCaja = new UtilityCaja();
    public static String tituloBaseFrame = "FarmaVenta";
    
    public ArrayList<ArrayList<String>> parsearResultadoMatriz(List<BeanResultado> pListado) {
        return parsearResultadoMatriz(pListado, "Ã");
    }
    
    public ArrayList<ArrayList<String>> parsearResultadoFasaProd(List<BeanResultado> pListado) {
        return parsearResultadoMatriz(pListado, "?");
    }
    
    public ArrayList<ArrayList<String>> parsearResultadoMatriz(List<BeanResultado> pListado, String pSeparador) {

        ArrayList<ArrayList<String>> arrayResultado = new ArrayList<ArrayList<String>>();
        ArrayList<String> tmpArray = null;
        
        for (BeanResultado beanResultado : pListado) {
            tmpArray = parsearResultado(beanResultado,pSeparador);
            arrayResultado.add(tmpArray);
        }
        return arrayResultado;
    }

    public void parsearResultado(List<BeanResultado> pListado, FarmaTableModel pTableModel, boolean pWithCheck) {
        parsearResultado(pListado, pTableModel, pWithCheck, "Ã");
    }
    
    public void parsearResultado(List<BeanResultado> pListado, FarmaTableModel pTableModel, boolean pWithCheck, String pSeparador) {
        
        ArrayList<Object> tmpArray = null;
        
        pTableModel.clearTable();
        for (BeanResultado beanResultado : pListado) {
            tmpArray = parsearResultado(beanResultado,pWithCheck,pSeparador);
            pTableModel.insertRow(tmpArray);
        }
    }
    
    private ArrayList<Object> parsearResultado(BeanResultado beanResultado, boolean pWithCheck, String pSeparador) {
        ArrayList<Object> tmpArray = new ArrayList<Object>();
        StringTokenizer st = null;
        String tmpRes = beanResultado.getStrResultado();
        if (tmpRes != null) {
            st = new StringTokenizer(tmpRes, pSeparador);
            if (pWithCheck)
                tmpArray.add(Boolean.valueOf(false));
            while (st.hasMoreTokens()) {
                tmpArray.add(st.nextToken());
            }
        }
        return tmpArray;
    }
    
    private ArrayList<String> parsearResultado(BeanResultado beanResultado, String pSeparador) {
        ArrayList<String> tmpArray = new ArrayList<>();
        StringTokenizer st = null;
        String tmpRes = beanResultado.getStrResultado();
        if (tmpRes != null) {
            st = new StringTokenizer(tmpRes, pSeparador);
            while (st.hasMoreTokens()) {
                tmpArray.add(st.nextToken());
            }
        }
        return tmpArray;
    }

    public ArrayList<String> parsearResultadoColum(List<BeanResultado> listado) {

        List<BeanResultado> listResultado = listado;
        ArrayList<String> arrayResultado = new ArrayList<>();
        String tmpRes = "";

        for (BeanResultado beanResultado : listResultado) {
            tmpRes = beanResultado.getStrResultado();
            arrayResultado.add(tmpRes);
        }
        return arrayResultado;
    }

    public boolean validarCampTxtField(JDialog pDialog, JTextField txtText) {
        //ERIOS 2.2.8 Validacion de monto
        if (!UtilityCaja.existeCajaUsuarioImpresora(pDialog, null)) {
            return false;
        }
        if (txtText != null) {
            if (txtText.getText().trim().equals("")) {
                FarmaUtility.showMessage(pDialog, "Ingrese la cantidad correcta digitos.", txtText);
                return false;
            }
            double dCuantoPaga = FarmaUtility.getDecimalNumber(txtText.getText());
            if (dCuantoPaga <= 0) {
                FarmaUtility.showMessage(pDialog, "Ingrese un monto válido.", txtText);
                return false;
            }
        }
        return true;
    }

    public boolean validarCampTxtFieldTrj(JDialog pDialog, JTextField txtText) {
        if (txtText != null) {
            if (txtText.getText().trim().length() != 16) {
                FarmaUtility.showMessage(pDialog, "Ingrese la cantidad correcta digitos de su tarjeta.", txtText);
                return false;
            }
        }
        return true;
    }

    public String rutaImpresoraTicket() {
        String tmpRuta = "";
        try {
            tmpRuta = DBCaja.obtieneRutaImpresoraVenta(VariablesCaja.vSecImprLocalTicket);
        } catch (SQLException sqlE) {
            log.error("",sqlE);
        }
        return tmpRuta;
    }

    /**
     * Retorna la ruta de la carpeta de comprobantes
     * @author ERIOS
     * @since 25.06.2013
     * @return
     * @throws SQLException
     */
    public static String obtieneDirectorioComprobantes() throws SQLException {
        String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
        String carpetaComprobantes = DBPtoVenta.getDirectorioComprobantes();
        Path dir = Paths.get(carpetaRaiz, carpetaComprobantes);
        return dir.toString() + File.separator;
    }

    /**
     * Retorna la ruta de la carpeta de imagenes
     * @author ERIOS
     * @since 25.06.2013
     * @return
     * @throws SQLException
     */
    public static String obtieneDirectorioImagenes() throws SQLException {
        String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
        String carpetaComprobantes = DBPtoVenta.getDirectorioImagenes();
        Path dir = Paths.get(carpetaRaiz, carpetaComprobantes);
        return dir.toString() + File.separator;
    }

    /**
     * Limpia la caja de texto.
     * Metodo implementado para los lectores de codigos de barra NCR.
     * @author ERIOS
     * @since 03.07.2013
     * @param txtProducto
     */
    public static void limpiaCadenaAlfanumerica(JTextField txtProducto) {
        String prodTemp = txtProducto.getText();
        //prodTemp = getCadenaAlfanumerica(prodTemp);
        prodTemp = getCodBarraSinCarControl(prodTemp);
        txtProducto.setText(prodTemp);
    }

    /**
     * Elimina caracteres especiales de la cadena.
     * @author LLEIVA
     * @since 03.07.2013
     * @param prodTemp
     * @return
     */
    public static String getCadenaAlfanumerica(String codProd) {
        String codProdTemp = "";
        if (codProd.length() > 0) {
            Pattern patron = Pattern.compile("[^0-9 a-zA-Z.%&_^-]"); //|^-|^.|^+|^\\/|^&|^\\#|^\\%");
            Matcher encaja = patron.matcher(codProd);
            codProdTemp = encaja.replaceAll("");
        }

        return codProdTemp;
    }

    /**
     * Elimina caracteres especiales de la cadena.
     * @author LLEIVA
     * @since 03.07.2013
     * @param prodTemp
     * @return
     */
    public static boolean validarNumero(String cadena) {
        boolean flag = false;
        
        if (cadena.length() > 0) {
            Pattern patron = Pattern.compile("^\\d+$"); //|^-|^.|^+|^\\/|^&|^\\#|^\\%");
            Matcher encaja = patron.matcher(cadena);
            flag = encaja.matches();
        }
        

        return flag;
    }
    public static boolean validarNumero_01(String cadena) {
        boolean flag = true;
        /*
        if (cadena.length() > 0) {
            Pattern patron = Pattern.compile("^\\d+$"); //|^-|^.|^+|^\\/|^&|^\\#|^\\%");
            Matcher encaja = patron.matcher(cadena);
            flag = encaja.matches();
        }
        */

        return flag;
    }
    /**
     * Devuelve el codigo de barras sin el caracter de identificacion al inicio de la cadena
     * @author LLEIVA
     * @since 22.Nov.2013
     * @param cadena
     * @return
     */
    public static String getCodBarraSinCarControl(String cadena) {
        String codProdTemp = ""; //cadena;
        try {
            if (cadena != null && cadena.length() > 2) {
                //se obtienen los dos primero caracteres para verificar el caracter de control
                int cont1 = cadena.substring(0, 1).codePointAt(0);
                int cont2 = cadena.substring(1, 2).codePointAt(0);

                //verifica si el primer caracter es F, el segundo es F y tamaño 9(8+1) con solo numeros, entonces es un EAN-8
                if ((cont1 == 70 && cont2 == 70) && (cadena.length() >= 9)) {
                    if (validarNumero(cadena.substring(2, 10)))
                        codProdTemp = cadena.substring(2, 10);
                    else
                        codProdTemp = cadena;
                }
                //verifica si el primer caracter es ?
                else if (cont1 == 9824) {
                    if (validarNumero(cadena.substring(1, cadena.length())))
                        codProdTemp = cadena.substring(1, cadena.length());
                    else
                        codProdTemp = cadena;
                }
                //verifica si el primer caracter es F y tamaño 14(13+1), entonces es un EAN-13
                else if ((cont1 == 70) && (cadena.length() >= 14)) {
                    if (validarNumero(cadena.substring(1, 14)))
                        codProdTemp = cadena.substring(1, 14);
                    else
                        codProdTemp = cadena;
                }
                //verifica si el primer caracter es A y tamaño 13(12+1), entonces es un UPC-A
                else if ((cont1 == 65) && (cadena.length() >= 13)) {
                    if (validarNumero(cadena.substring(1, 13)))
                        codProdTemp = cadena.substring(1, 13);
                    else
                        codProdTemp = cadena;
                }
                //verifica si el primer caracter es E y tamaño 9(8+1), entonces es un UPC-E
                else if ((cont1 == 69) && (cadena.length() >= 9)) {
                    if (validarNumero(cadena.substring(1, 9)))
                        codProdTemp = cadena.substring(1, 9);
                    else
                        codProdTemp = cadena;
                }
                //verifica si el primer caracter es f, a, b, r, N entonces es un Code 128, Code 39,
                //Interleaved 2 de 5, Databar ó Codabar, que tienen longitud variable
                /*else if(cont1 == 102 ||
                        cont1 == 97  ||
                        cont1 == 98  ||
                        cont1 == 114 ||
                        cont1 == 78)
                {   codProdTemp = cadena.substring(1, cadena.length());
                }*/
                else {
                    codProdTemp = cadena;
                }
            }
        } catch (Exception e) {
            log.error("", e);
            codProdTemp = "";
        }
        return codProdTemp;
    }

    /**
     * Verifica tecla pulsada F1 - NCR
     * @author ERIOS
     * @since 03.07.2013
     * @param keyEvent
     * @return
     */
    public static boolean verificaVK_F1(KeyEvent keyEvent) {
        int m = keyEvent.getModifiers();
        if ((m & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            return false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F1) {
            return true;
        }
        return false;
    }

    /**
     * Verifica tecla pulsada F2 - NCR
     * @author ERIOS
     * @since 03.07.2013
     * @param keyEvent
     * @return
     */
    public static boolean verificaVK_F2(KeyEvent keyEvent) {
        int m = keyEvent.getModifiers();
        if ((m & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            return false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F2) {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica tecla pulsada F3
     * @author JMONZALVE
     * @since 02.01.2018
     * @param keyEvent
     * @return
     */
    public static boolean verificaVK_F3(KeyEvent keyEvent) {
        int m = keyEvent.getModifiers();
        if ((m & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            return false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F3) {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica tecla pulsada F4
     * @author JMONZALVE
     * @since 19.01.2018
     * @param keyEvent
     * @return
     */
    public static boolean verificaVK_F4(KeyEvent keyEvent) {
        int m = keyEvent.getModifiers();
        if ((m & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            return false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F4) {
            return true;
        }
        return false;
    }

    /**
     * Verifica tecla pulsada F10 - NCR
     * @author ERIOS
     * @since 03.07.2013
     * @param keyEvent
     * @return
     */
    public static boolean verificaVK_F10(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_F10) {
            return true;
        }
        return false;
    }
    
    public static boolean verificaVK_F7(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_F7) {
            return true;
        }
        return false;
    }


    /**
     * Verifica tecla pulsada F11 - NCR
     * @author ERIOS
     * @since 03.07.2013
     * @param keyEvent
     * @return
     */
    public static boolean verificaVK_F11(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_F11) {
            return true;
        }
        int m = keyEvent.getModifiers();
        if ((m & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_F1)
                return true;
        }
        /*if ((m & (InputEvent.CTRL_DOWN_MASK | InputEvent.CTRL_MASK)) != 0) {
          log.debug("ctrl ");
        }
        if ((m & (InputEvent.META_DOWN_MASK | InputEvent.META_MASK)) != 0) {
          log.debug("meta ");
        }
        if ((m & (InputEvent.ALT_DOWN_MASK | InputEvent.ALT_MASK)) != 0) {
          log.debug("alt ");
        }
        if ((m & (InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON1_MASK)) != 0) {
          log.debug("button1 ");
        }
        if ((m & (InputEvent.BUTTON2_DOWN_MASK | InputEvent.BUTTON2_MASK)) != 0) {
          log.debug("button2 ");
        }
        if ((m & (InputEvent.BUTTON3_DOWN_MASK | InputEvent.BUTTON3_MASK)) != 0) {
          log.debug("button3 ");
        }*/
        return false;
    }

    /**
     * Verifica tecla pulsada F12 - NCR
     * @author ERIOS
     * @since 03.07.2013
     * @param keyEvent
     * @return
     */
    public static boolean verificaVK_F12(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_F12) {
            return true;
        }
        int m = keyEvent.getModifiers();
        if ((m & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_F2)
                return true;
        }
        return false;
    }

    /**
     * Verifica conexion con servidores en Matriz
     * @author ERIOS
     * @since 21.11.2013
     * @param pConexion
     * @return
     * @throws Exception
     */
    @Deprecated
    public static boolean verificaConexionMatriz(int pConexion) throws Exception {
        boolean bRpt = true;
        String vConexion = FarmaUtility.getIndLineaOnLine(pConexion, FarmaConstants.INDICADOR_S);
        if (vConexion.equals(FarmaConstants.INDICADOR_N)) {
            bRpt = false;
            //ERIOS 2.2.8 Envia correo
            FarmaUtility.enviaCorreoPorBD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                                          VariablesPtoVenta.vDestEmailErrorCobro, "Error de Conexion Central",
                                          "Error de Comunicacion BBDD",
                                          "¡No hay conexión con Matriz" + "<br>" + "Inténtelo nuevamente." + "<br>" +
                                          "Si persiste el error, llame a Mesa de Ayuda." + "<br>" + "IP PC: " +
                                          FarmaVariables.vIpPc + "<br>" + "Error: " + "Conexion :" + pConexion, "");
            throw new Exception("¡No hay conexión con Matriz!\n" +
                    "Inténtelo nuevamente.\n" +
                    "Si persiste el error, llame a Mesa de Ayuda. ");
        }
        return bRpt;
    }

    /**
     * Obtiene la fecha actual de la Base de Datos en formato ddMMYYYY
     * @author LLEIVA
     * @since 07.Feb.2014
     * @param pConexion
     * @return
     * @throws Exception
     */
    public static String fechaActualDDMMYYYY() throws Exception {
        String tmpFecha = "";
        try {
            tmpFecha = DBCaja.fechaActualDDMMYYYY();
        } catch (SQLException sqlE) {
            log.error("",sqlE);
        }
        return tmpFecha;
    }

    public static void mensajeErrorBd(JDialog pDialog, String msgOracle, Object pObject) {
        mensajeErrorBd(pDialog, msgOracle, pObject, false);
    }
    /**
     * Desdoble de mensaje de error de base de datos
     * @author AAMPUERO
     * @since 08.04.2014
     * @param mensageErrorBd
     */
    public static void mensajeErrorBd(JDialog pDialog, String msgOracle, Object pObject, boolean pOnlySQL) {

        /**
              DESDOBLE DE LINEAS X CODIGO DE MENSAJES BD-ORACLE
              AAMPUERO - 04-04-2014
             */
        String pmensaje = "";

        if (msgOracle.indexOf("ORA-") > 0){
        
        String[] splits = msgOracle.split("ORA-");

            if(!pOnlySQL){        
            for (int i = 0; i < splits.length; i++) {
                // titulo del mensaje
                if (i == 0)
                    pmensaje = pmensaje.trim() + splits[i];
                else
                    pmensaje = pmensaje.trim() + "\n" +
                            "ORA-" + splits[i];
            }
            }else{
                pmensaje = splits[1].substring(7);
            }
        }else{
            pmensaje = msgOracle;
        }

        FarmaUtility.showMessage(pDialog, pmensaje, pObject);
    }

    /**
     * Margen de impresion de comprobantes
     * @author ERIOS
     * @since 2.4.3
     * @return
     */
    public static int getMargenImpresionComp() {
        int pResultado = 0;
        try {
            pResultado = DBPtoVenta.getMargenImpresionComp();
        } catch (SQLException err) {
            log.error("Error al ", err);
            pResultado = 0;
        }
        return pResultado;
    }

    public static boolean getIndLoginCajUnicaVez() {
        String pResultado = "";
        try {
            pResultado = DBPtoVenta.getIndLoginCajeroUNICAVEZ();
        } catch (Exception sqle) {
            log.error("", sqle);
            pResultado = "N";
        }
        if (pResultado.trim().equalsIgnoreCase("S"))
            return true;
        else
            return false;
    }
    /**
     * Verifica si una cadena es numerica
     * @author CHUANES
     * @since 10/09/2015
     * @return
     */
    public static boolean isNumeric(String cadena){
            try {
                    Integer.parseInt(cadena);
                    return true;
            } catch (NumberFormatException nfe){
                    return false;
            }
    }

    /**
     * @author ERIOS
     * @since 09.10.2015
     * @param resultList
     * @return
     */
    public List<BeanResultado> retornaBean(List<Map<String, Object>> resultList) {
        List<BeanResultado> pListado = new ArrayList<>();
        for(Map<String, Object> mpReg:resultList){
            String strResultado = mpReg.get("1").toString();
            BeanResultado beanResultado = new BeanResultado();
            beanResultado.setStrResultado(strResultado);
            pListado.add(beanResultado);
        }
        return pListado;
    }

    /**
     * @author KMONCADA
     * @since 14.04.2016
     * @return
     */
    public boolean isCentralizaClientes(){
        log.info("[PERCEPCION] VALIDARA SI LOCAL OPERA DE MANERA CENTRALIZADA LOS CLIENTES");
        boolean isCentralizado = false;
        try{
            isCentralizado = DBPtoVenta.isCentralizaClientes(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
        }catch(Exception ex){
            log.error("", ex);
            isCentralizado = false;
        }
        log.info("[PERCEPCION] OPERA DE MANERA CENTRALIZADA? "+isCentralizado);
        return isCentralizado;
    }
    
    public static boolean isLocalAplicaPercepcion(){
        log.info("[PERCEPCION] VALIDARA SI LOCAL APLICA PERCEPCION");
        boolean isAplicaPercepcion = false;
        try{
            isAplicaPercepcion = DBPtoVenta.isLocalAplicaPercepcion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
        }catch(Exception ex){
            log.error("", ex);
            isAplicaPercepcion = false;
        }
        log.info("[PERCEPCION] LOCAL APLICA? "+isAplicaPercepcion);
        return isAplicaPercepcion;
    }
    
    public static Object[] obtenerDefaultValuesTabla(int longitud){
        Object[] defaultValues = new Object[longitud];
        for(int i=0; i<longitud;i++){
            defaultValues[i] = " ";
        }
        return defaultValues;
    }
    
    
    /**
     * @author CCASTILLO
     * @since 09.05.2016
     * @return boolean
     */
    
    public static boolean isLocalTipoVentaTICO(){
        if(VariablesPtoVenta.vTipoLocalVenta != null && ConstantsPtoVenta.TIPO_LOCAL_VENTA_TICO.equalsIgnoreCase(VariablesPtoVenta.vTipoLocalVenta)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean EsLocalTICO() {
        String indTico="";
        try{
            ArrayList parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodCia);
            parametros.add(FarmaVariables.vCodLocal);
            indTico=FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_UTILITY.VERIFICA_IND_LOCAL_TICO(?,?,?)", parametros);
            if(indTico.equalsIgnoreCase("S")){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Error al recuperar el indicador TICO de local");
            e.printStackTrace();
            return false;
        }
        
    }
    
    public static void cargaVariablesBD() {
        //FarmaVariables.vUsuarioBD = ConstantsPtoVenta.USUARIO_BD;
        //FarmaVariables.vClaveBD = ConstantsPtoVenta.CLAVE_BD;
        //FarmaVariables.vSID = ConstantsPtoVenta.SID;
        FarmaVariables.vPUERTO = ConstantsPtoVenta.PUERTO;
        try {
            (new FacadeRecaudacion()).obtenerTipoCambio();


        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(new JDialog(), "Error al obtener Tipo de Cambio del Dia . \n " + sql.getMessage(), null);
        }
    }
    
    public static String readFileFarmaVentaProperties() {
        Properties prop = new Properties();
        try {
            prop.load(FrmEconoFar.class.getResourceAsStream("/farmaventa.properties"));
            //ERIOS 2.2.9 Se guarda los datos de la version
            FarmaVariables.vNombreModulo = "FarmaVenta";
            FarmaVariables.vVersion = prop.getProperty("version");
            FarmaVariables.vCompilacion = prop.getProperty("compilacion");
            
            FarmaConnection.setVersion();
            DlgProcesar.setVersion();
            
            VariablesPtoVenta.tituloBaseFrame = FarmaVariables.vNombreModulo + " " + FarmaVariables.vVersion + " - " + FarmaVariables.vCompilacion;
            
            return FarmaVariables.vNombreModulo + " " + FarmaVariables.vVersion + " - " + FarmaVariables.vCompilacion;
            //tituloBaseFrame = FarmaVariables.vNombreModulo + " " + FarmaVariables.vVersion + " - " + FarmaVariables.vCompilacion;
            //log.info("Version: " + tituloBaseFrame);
        } catch (IOException|SQLException e) {
            log.error("", e);
            return "N";
        }
        //return "N";
    }
    
    
    public static void muestraLogin(String pcadena) throws Throwable {
          //if ( readFileProperties() )
          if (true) {
              /*
        if(!validaNamePc()){
          FarmaUtility.showMessage(this,"Error al obtener el Nombre de la PC.", null);
          salirSistema();
        }
        try{
          int cantSesiones = DBPtoVenta.obtieneCantidadSesiones(FarmaVariables.vNamePc, FarmaVariables.vUsuarioBD);
          if(cantSesiones > 1){
            FarmaUtility.showMessage(this,"Ya existe una aplicacion iniciada en esta PC.\nPor favor comunicarse con el area de Sistemas", null);
            salirSistema();
          }
        } catch(SQLException sql)
        {
          FarmaUtility.showMessage(this,"Error al obtener cantidad de Sesiones Abiertas.\nPor favor comunicarse con el area de Sistemas", null);
          salirSistema();
        }
        */
              obtieneInfoLocal();
              //obtener info del local
              if (!validaIpPc()) {
                  FarmaUtility.showMessage(new JFrame(), "Error al obtener la IP de la PC.", null);
                  salirSistema();
              }
              BeanVariables.vAccion = "A";
              FarmaCnxPool.loadListaProductos();
              
              
              String strRutaJpg = "";
              try {
                  strRutaJpg = DBPtoVenta.obtieneRutaImagen();
              } catch (Exception e) {
                  log.error("", e);
                  log.debug("Problemas al carga el icono");
                  strRutaJpg = "Mifarma.jpg";
              }
//              ImageIcon imageIcono =
//                  new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/Icono" + strRutaJpg));
              
              
//              String mensajeLogin = "Acceso " +pcadena;// tituloBaseFrame;
              /*** INICIO ARAVELLO 14/10/2019 ***/
              String usuario = DBRefacturadorElectronico.findUsuario(
                                                            VariablesRefacturadorElectronico.vComprobanteActual.getCodGrupoCia(),
                                                            VariablesRefacturadorElectronico.vComprobanteActual.getCodLocal(),
                                                            VariablesRefacturadorElectronico.vComprobanteActual.getNumCompPagoE());
              VariablesRefacturadorElectronico.vComprobanteActual.setUsuario(usuario);
              
              UtilityRefacturadorElectronico.autenticar(VariablesRefacturadorElectronico.vComprobanteActual.getUsuario());
              
//              FarmaSecurity vSecurityLogin = new FarmaSecurity(usuario, clave);
//              if (!vSecurityLogin.getLoginStatus().equalsIgnoreCase("01")) {
//                  throw new Exception("ERROR EN LA AUTENTICACION");
//              }
//              FarmaVariables.vNuSecUsu = vSecurityLogin.getLoginSequential();
//              FarmaVariables.vIdUsu = vSecurityLogin.getLoginCode();
//              FarmaVariables.vNomUsu = vSecurityLogin.getLoginNombre();
//              FarmaVariables.vPatUsu = vSecurityLogin.getLoginPaterno();
//              FarmaVariables.vMatUsu = vSecurityLogin.getLoginMaterno();
//              String clave = DBRefacturadorElectronico.getClaveUsu, pCodLocal, FarmaVariables.vNuSecUsu);
//                  VariablesRefacturadorElectronico.vComprobanteActual.get;
              FarmaVariables.vAceptar = true;
//              if (FarmaVariables.vEconoFar_Matriz) {
//                FarmaVariables.vCodUsuMatriz = FarmaVariables.vNuSecUsu;
//                FarmaVariables.vClaveMatriz = clave; 
//              } 
//              dlgLogin.setIconImage(imageIcono.getImage());
//              dlgLogin.setVisible(true);
              /*** FIN    ARAVELLO 14/10/2019 ***/
              
              if (!FarmaVariables.vAceptar) {
                  FarmaConnection.closeConnection();
                  salirSistema();
              } else {               
                  //inicio
                  //lapaz dubilluz 17.09.2010
                  
                  log.info("Inicio de Hilo");
                  // crear y nombrar a cada subproceso

                  //SubProcesos subproceso1 = new SubProcesos("GET_PROD_VENTA" );
                  SubProcesos subproceso2 = new SubProcesos("GET_PROD_ESPECIALES");
                  SubProcesos subproceso3 = new SubProcesos("CARGA_IMP_TERMICA");

                  log.debug("Iniciando subprocesos");
                  //subproceso1.start();
                  subproceso2.start();
                  subproceso3.start();
                  
                  log.info("Fin de Hilo");
                  //fin
                  //lapaz dubilluz 17.09.2010

                  //JCORTEZ 04.09.09 Se valida cambio de clave para el usuario
                  String valida = "";
                  String numDiasVenc = ""; //CHUANES 25.02.2015
                  String usuDebeCambiarClave = ""; //CHUANES 11.03.2015
                  String recCambioClave = ""; //CHUANES 12.03.2015
                  try {
                      usuDebeCambiarClave = DBPtoVenta.usuDebeCambiarClave().trim();
                      //usuDebeCambiarClave = "S";
                      valida = DBPtoVenta.validaCambioClave().trim();
                      //valida = "S";
                      numDiasVenc = DBPtoVenta.recFecVenClave().trim();
                      //numDiasVenc = "S";
                      recCambioClave = DBPtoVenta.recodCambioClave().trim();
                      //recCambioClave = "N";
                  } catch (SQLException x) {
                      log.error("", x);
                  }
                  //CHUANES 11.03.2015
                  if (usuDebeCambiarClave.equalsIgnoreCase("S")) {
                      if (!recCambioClave.equalsIgnoreCase("N")) { //CHUANES 12.03.2015 SOLO DEBE RECORDAR UNA VEZ AL DIA EL VENCIMIENTO DE LA CLAVE
                          if (!numDiasVenc.equalsIgnoreCase("N")) {
                              if (JConfirmDialog.rptaConfirmDialog(null,
                                                                   "Estimado usuario le falta " + numDiasVenc + " dia(s) para que expire su clave\n" +
                                      "¿Desea cambiarla ahora?")) {
                                  ;

                                  DlgCambioClave dlgcambio = new DlgCambioClave(null, "", true);
                                  dlgcambio.setVisible(true);

                                  if (FarmaVariables.vAceptar) {
                                      FarmaVariables.dlgLogin = null;
                                      recuperaStock();
                                      //INI ASOSA - 14/09/2015 - CTRLASIST
                                      boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(new Frame(),
                                                                                          ConstantsControlAsistencia.ID_ENTRADA);
                                      if (!flagCA) {
                                          llamarDlgLogin();
                                      }
                                      //FIN ASOSA - 24/09/2015 - CTRLASIST                                    
                                  } else {
                                      // salirSistema();no debe salir del sistema porque esta vigente
                                  }
                              }

                          }
                      }

                      log.info("cambiar password :" + valida);
                      if (valida.trim().equalsIgnoreCase("S")) {

                          FarmaUtility.showMessage(new JFrame(), "Usted debera cambiar su clave para poder entrar el sistema.",
                                                   null);
                          DlgCambioClave dlgcambio = new DlgCambioClave(new Frame(), "", true);
                          dlgcambio.setVisible(true);

                          if (FarmaVariables.vAceptar) {
                              FarmaVariables.dlgLogin = null;
                              recuperaStock();
                              //INI ASOSA - 14/09/2015 - CTRLASIST
                              boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(new Frame(),
                                                                                  ConstantsControlAsistencia.ID_ENTRADA);
                              if (!flagCA) {
                                  llamarDlgLogin();
                              }
                              //FIN ASOSA - 24/09/2015 - CTRLASIST                            
                          } else {
                              salirSistema();
                          }

                      } else {
                          FarmaVariables.dlgLogin = null;
                          recuperaStock();
                          //INI ASOSA - 14/09/2015 - CTRLASIST
                          boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(new Frame(),
                                                                              ConstantsControlAsistencia.ID_ENTRADA);
                          if (!flagCA) {
                              llamarDlgLogin();
                          }
                          //FIN ASOSA - 24/09/2015 - CTRLASIST                        
                      }
                  } else { //CHUANES 29/04/2015 SOLUCIONA OPERADOR Y SOBRE PARCIAL
                      FarmaVariables.dlgLogin = null;
                      recuperaStock();

                      //INI ASOSA - 14/09/2015 - CTRLASIST
                      boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(new Frame(),
                                                                          ConstantsControlAsistencia.ID_ENTRADA);
                      if (!flagCA) {
                          llamarDlgLogin();
                      }
                      //FIN ASOSA - 24/09/2015 - CTRLASIST
                  }

                  //FIN CHUANES 11.03.2015
                 

                 
                  SubProcesosALertatUp subprocesoAlertUp = new SubProcesosALertatUp(new Frame());
                  subprocesoAlertUp.start();
                  
                  // KMONCADA 09.08.2016 [FACT.ELECTRONICA 2.0] 
                  
                  SubProcesoCPE subProcesoCompPagoElectronico = new SubProcesoCPE();
                  subProcesoCompPagoElectronico.start();
                  
                  //LTAVARA 2016.07.24 INICIO  ALERTA DE COTIZACIONES PENDIENTES
                  SubProcesoAlertUpCotizacionPrecio subProcesoAlertUpCotizacionPrecio = new SubProcesoAlertUpCotizacionPrecio(new Frame());
                  subProcesoAlertUpCotizacionPrecio.start();
                  
                  //LTAVARA 2016.07.24  FIN  ALERTA DE COTIZACIONES PENDIENTES
              }
          } else
              salirSistema();
          BeanVariables.vAccion = "";
      }
     
    public static void obtieneInfoLocal() {
        try {
            ArrayList infoLocal = DBPtoVenta.obtieneDatosLocal();
            FarmaVariables.vDescCortaLocal = ((String)((ArrayList)infoLocal.get(0)).get(0)).trim();
            FarmaVariables.vDescLocal = ((String)((ArrayList)infoLocal.get(0)).get(1)).trim();
            FarmaVariables.vTipLocal = ((String)((ArrayList)infoLocal.get(0)).get(2)).trim();
            FarmaVariables.vTipCaja = ((String)((ArrayList)infoLocal.get(0)).get(3)).trim();

            FarmaVariables.vNomCia = ((String)((ArrayList)infoLocal.get(0)).get(4)).trim();
            FarmaVariables.vNuRucCia = ((String)((ArrayList)infoLocal.get(0)).get(5)).trim();
            FarmaVariables.vMinutosPedidosPendientes = ((String)((ArrayList)infoLocal.get(0)).get(6)).trim();
            FarmaVariables.vImprReporte = ((String)((ArrayList)infoLocal.get(0)).get(7)).trim();
            FarmaVariables.vIndHabilitado = ((String)((ArrayList)infoLocal.get(0)).get(8)).trim();
            FarmaVariables.vDescCortaDirLocal = ((String)((ArrayList)infoLocal.get(0)).get(9)).trim();

            VariablesPtoVenta.vRazonSocialCia = DBPtoVenta.obtieneRazSoc();
            VariablesPtoVenta.vTelefonoCia = DBPtoVenta.obtieneTelfCia();
            VariablesPtoVenta.vNombreMarcaCia = DBPtoVenta.obtieneNombreMarcaCia();
            
            /*** INICIO CCASTILLO 09/05/2016 ***/
            //Se agrega columna para saber el tipo de venta del local
            VariablesPtoVenta.vTipoLocalVenta = ((String)((ArrayList)infoLocal.get(0)).get(10)).trim();
            /*** FIN  CCASTILLO 09/05/2016 ***/
            /*** INICIO CCASTILLO 16/08/2017 ***/
            VariablesPtoVenta.vIndPinPad = ((String)((ArrayList)infoLocal.get(0)).get(11)).trim();
            /*** Fin ccastillo 16/08/2017 ***/
            
            if (UtilityPtoVenta.isLocalTipoVentaTICO()){
                VariablesVentas.tableModelListaGlobalProductosInsumo =
                        new FarmaTableModel(ConstantsVentas.columnsListaProdPrecios,
                                            ConstantsVentas.defaultValuesListaProdPrecios, 0);
                
                DBVentas.cargaListaProductosInsumo(VariablesVentas.tableModelListaGlobalProductosInsumo);
            }
            
        } catch (SQLException sqlException) {

            log.error("", sqlException);
        }
    }
    
    private static boolean validaIpPc() {
        FarmaVariables.vIpPc = FarmaUtility.getHostAddress();
        if (FarmaVariables.vIpPc.trim().length() == 0)
            return false;
        return true;
    }
    
    private static void salirSistema() {
        UtilityVentas.eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
        System.exit(0);
    }

    private static void recuperaStock() {

        try {
            // RECUPERANDO STOCK COMPROMETIDO
            DBPtoVenta.ejecutaRespaldoStock("", "", ConstantsPtoVenta.TIP_OPERACION_RESPALDO_EJECUTAR, 0, 0, "");
            log.info("RECUPERO STOCK COMPROMETIDO DESDE RESPALDO");
            FarmaUtility.aceptarTransaccion();
        } catch (SQLException sqlException) {
            FarmaUtility.liberarTransaccion();
            log.error("", sqlException);
            FarmaUtility.showMessage(new JFrame(),
                    "Error al recuperar Stock de Respaldo.\nPonganse en contacto con el area de Sistemas",
                                     null);
            salirSistema();
        }

    }

    private static boolean llamarDlgLogin() {
        String mensajeLogin = "Acceso " + tituloBaseFrame;
        DlgLogin dlgLogin = new DlgLogin(new Frame(), mensajeLogin, true);
        FarmaVariables.dlgLogin = dlgLogin;
        dlgLogin.setVisible(true);
        if (!FarmaVariables.vAceptar) {
            FarmaConnection.closeConnection();
            salirSistema();
        }
        boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(new Frame(),
                                                            ConstantsControlAsistencia.ID_ENTRADA);
        if (!flagCA) {
            llamarDlgLogin();
        }
        return FarmaVariables.vAceptar;
    }

    public static void Actualiza_IndicadoresMenu() {
        try {
            DBPtoVenta.actualiza_IndicadoresMenu();
            FarmaUtility.aceptarTransaccion();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            FarmaUtility.liberarTransaccion();
        }
    }
    
    public static void muestraUser(JFrame frmFarmaVenta) {
        String addon = " Usu.Actual : ";
        addon = addon + FarmaVariables.vIdUsu;
        frmFarmaVenta.setTitle(VariablesPtoVenta.tituloBaseFrame + " /  Local : " + FarmaVariables.vDescCortaLocal + " / " + addon + " /  IP : " +
                      FarmaVariables.vIpPc);
    }

    public static String get_URL_Tesoreria() {
        String url= null;
        try {
            url = DBPtoVenta.get_URL_Tesoreria();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return url;
    }

    public static boolean get_habilita_Tesoreria() {
        boolean vEnable = false;
        try {
            String ind = DBPtoVenta.get_habilita_Tesoreria();
            if(ind.equalsIgnoreCase("S"))
                vEnable = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return vEnable;
    }

	// RARGUMEDO 20190725
    public static String getArgumeto_Producto(String codigoProducto) {
        String argumento = "";
        try {
            argumento = DBPtoVenta.getMsjArgumento_Producto(codigoProducto);
        } catch (SQLException e) {
            log.error(e.toString());
        }
        return argumento;
    }
}
