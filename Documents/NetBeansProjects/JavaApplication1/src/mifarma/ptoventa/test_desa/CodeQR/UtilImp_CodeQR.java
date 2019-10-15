package mifarma.ptoventa.test_desa.CodeQR;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaPrintServiceTicket;
import mifarma.common.FarmaVariables;

import mifarma.electronico.UtilityImpCompElectronico;
import mifarma.electronico.impresion.FacadeComprobanteElectronico;

import mifarma.ptoventa.administracion.impresoras.reference.DBImpresoras;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.ventas.reference.DBVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import printerFarma.FarmaPrinterFacade;


public class UtilImp_CodeQR {
    
    private static final Logger log = LoggerFactory.getLogger(UtilityImpCompElectronico.class);
    FacadeComprobanteElectronico facade = new FacadeComprobanteElectronico();
    private String tipoImpTermica;
    private String nomImpTermica;
    private String rutaImpTermica;
    private String rutaFileTestigo;
    
    public UtilImp_CodeQR() throws Exception {
        super();
        cargarDatosImpresoras();
        rutaImpTermica = getRuta();
        
    }
    
    private void cargarDatosImpresoras()throws Exception{
        List lstDatosImpresora = DBImpresoras.getDatosImpresoraTerminca(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, FarmaVariables.vIpPc);
        if(lstDatosImpresora.size()>0){
            Map datoImpresora  = (HashMap)lstDatosImpresora.get(0);
            tipoImpTermica = (String)datoImpresora.get("TIPO");
            nomImpTermica = (String)datoImpresora.get("IMPRESORA");
        }else{
            throw new Exception("Error al consultar datos de impresora termica.\n Verifique configuración en \"Mantenimiento de Impresoras\".");
        }
        
    }
    
    public String getRuta() throws SQLException {
        String ruta = null;
        PrintService[] servicio = PrintServiceLookup.lookupPrintServices(null, null);

        if (servicio != null) {
            try {
                for (int i = 0; i < servicio.length; i++) {
                    PrintService impresora = servicio[i];
                    String pRuta = impresora.getName().toString().trim();
                    String pNombre = UtilityCaja.retornaUltimaPalabra(pRuta, "\\");

                    if (pNombre.trim().toUpperCase().equalsIgnoreCase(this.getNombreTermica().trim().toUpperCase())) {
                        boolean resultado = pRuta.startsWith("\\");
                        if (resultado) {
                            ruta = pRuta;
                        } else {
                            ruta = "\\" + "\\" + FarmaVariables.vIpPc + "\\" + pRuta;
                        }

                    }
                }

            } catch (Exception e) {
                log.error("", e);
            }
        }
        return ruta;
    }
    
    public String getNombreTermica() throws SQLException {        
        return this.nomImpTermica;
    }
    
    public String getModelo() throws SQLException {
        return this.tipoImpTermica;
    }
    
    public static void imprimeCodBarra(String listaCodigos) {
        try {
            List vListaDatosNew = getCodBarra(listaCodigos);
            (new UtilityImpCompElectronico()).impresionTermica(vListaDatosNew, null);
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
    
    public static void imprimeCodPDF(String tramaPDF) {
        try {
            List vListaDatosNew = getCodData_QR(tramaPDF);
            //(new UtilityImpCompElectronico()).impresionQR_PDF(tramaPDF, null);
            
            System.out.println("-----------------------------");
            for(int i=0;i<vListaDatosNew.size();i++){
                System.out.println("["+i+"] => "+vListaDatosNew.get(i).toString());
            }
            System.out.println("-----------------------------");
            //(new UtilityImpCompElectronico()).impresionTermica(vListaDatosNew, null);
            (new UtilityImpCompElectronico()).impresionDataQR_PDF(vListaDatosNew, null);
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
    
    private static List getCodBarra(String listaCodigos) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(listaCodigos);
        List pRes = new ArrayList();
        log.info("FARMA_CUPON_ELECTRONICO.CODIGO_BARRA(?,?,?)"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_CUPON_ELECTRONICO.CODIGO_BARRA(?,?,?)",parametros);
        return pRes;
    }

    private static List getCodData_QR(String cadena)throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(cadena);
        List pRes = new ArrayList();
        log.info("FARMA_TEST_DESA.IMPRIME_CODE_QR(?,?,?)"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_TEST_DESA.IMPRIME_CODE_QR(?,?,?)",parametros);
        return pRes;
    }
    
    public boolean impresionTermica(List listDocumento, String pRutaFile)throws Exception {
        FarmaPrinterFacade printer = new FarmaPrinterFacade(tipoImpTermica, rutaImpTermica, false, "", ""); //manda imprimir segun el modelo de impresora
        FarmaPrintServiceTicket vPrintArchivo = null;
        if (pRutaFile != null) {
            vPrintArchivo = new FarmaPrintServiceTicket(666, pRutaFile, false);
            vPrintArchivo.startPrintService();
        }
        if (!printer.startPrintService()) {
            throw new Exception("No se pudo iniciar la Impresión del Documento.\nVerifique su impresora Termica por favor.");
        } else {
            //INICIALIZAR LA IMPRESORA--VALORES POR DEFECTO
            printer.inicializate(); 
            
            for (int i = 0; i < listDocumento.size(); i++) {
                log.info("LINEA --> "+(HashMap)listDocumento.get(i));
                printer.printer((HashMap)listDocumento.get(i));
                if ("-".equalsIgnoreCase(((String)((HashMap)listDocumento.get(i)).get("VALOR")))) {
                    printer.printLineDotted(30);
                    if (pRutaFile != null) {
                        vPrintArchivo.printLine("--------------------------------------------------------------------",
                                                true);
                    }
                } else {
                    
                    if (pRutaFile != null) {
                        vPrintArchivo.printLine(((String)((HashMap)listDocumento.get(i)).get("VALOR")), true); //
                    }
                }
            }
            printer.endPrintService();
            if (pRutaFile != null) {
                vPrintArchivo.endPrintService();
            }
        }
        
        /*** CCASTILLO 05/05/2016 ***/
        //Descuento del papel termico
        calculaPapelTermicoVta(listDocumento);
        /*** CCASTILLO 05/05/2016 ***/
        return true;
    }
    
    public static void calculaPapelTermicoVta(List listComanda){
        
        log.debug("calculaPapelTermicoVta inicio");
        
        double longitud=0,longitudTotal=0,l_aux=0;
        String saltoLinea="N";
        String tipoImpresora=VariablesPtoVenta.vTipoImpTermicaxIp;
        
        try{
            for (int i = 0; i < listComanda.size(); i++) {
                    saltoLinea=(String)((HashMap)listComanda.get(i)).get("SALTO_LINEA");
                    longitud=Double.parseDouble((String)((HashMap)listComanda.get(i)).get("LON_PTERMICO"));
                
                if(saltoLinea.equalsIgnoreCase("S")){
                     if(l_aux>longitud)
                         longitud=l_aux;
                    l_aux=0;   
                }else{ //Si no hay salto de linea esperar a la proxima linea
                    l_aux=longitud;
                    longitud=0;
                }
                    longitudTotal=longitudTotal+longitud;
            }
            
            log.debug("calculaPapelTermicoVta longitud parcial : "+longitudTotal);
            log.debug("calculaPapelTermicoVta tipo impresora   :  "+tipoImpresora);
            
            if(longitudTotal!=0){

                if(tipoImpresora.trim().equalsIgnoreCase("01")){
                    longitudTotal=longitudTotal+2.5; // última linea
                    longitudTotal=longitudTotal+13;  // primera linea (intervalo entre 10 y 13) margen  +-3 mm
                }else if (tipoImpresora.trim().equalsIgnoreCase("02")){ 
                    longitudTotal=longitudTotal+4;  // última linea
                    longitudTotal=longitudTotal+6;  // primera linea (intervalo entre 10 y 13) margen  +-3 mm  
                }
                
                longitudTotal=longitudTotal/10;  // pasar de milimetros a centimetro
                DBVentas.actKardexPapelTermico(longitudTotal);
            }
            
            log.debug("calculaPapelTermicoVta fin - La longitud del papel termico es : "+longitudTotal);
        }catch(Exception ex){
            log.error("Error calculaPapelTermicoVta : ",ex);
        }
    }

    static void imprimeCodQR_PDF() {
        try {
            List vListaDatosNew = getCodDataBD_QR();
            System.out.println("-----------------------------");
            for(int i=0;i<vListaDatosNew.size();i++){
                System.out.println("["+i+"] => "+vListaDatosNew.get(i).toString());
            }
            System.out.println("-----------------------------");
            (new UtilityImpCompElectronico()).impresionDataQR_PDF(vListaDatosNew, null);
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    private static List getCodDataBD_QR()throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        List pRes = new ArrayList();
        log.info("FARMA_TEST_DESA.IMPRIME_CODE_QR(?,?)"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_TEST_DESA.IMPRIME_CODE_QR(?,?)",parametros);
        return pRes;
    }
}
