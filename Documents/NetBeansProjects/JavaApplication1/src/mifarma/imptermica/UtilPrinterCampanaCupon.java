package mifarma.imptermica;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

import mifarma.electronico.UtilityImpCompElectronico;

import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.FacadeCaja;
import mifarma.ptoventa.caja.reference.PrintConsejo;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Clase para elaborar la impresion de cupones
 */

public class UtilPrinterCampanaCupon {
    
    private static final Logger log = LoggerFactory.getLogger(UtilPrinterCampanaCupon.class);
    public UtilPrinterCampanaCupon() {
        super();
    }
    
    
    public static void pruebaImpresion(){
        
        int valor = imprimeCuponOld(new JDialog(),"C784965060027774501");
        
        try {
            //List vListaDatosNew = getDatosPrueba();
            List vListaDatosNew = getImprimeDatos();
            (new UtilityImpCompElectronico()).impresionTermica(vListaDatosNew, null);
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
    
    private static int imprimeCuponOld(JDialog pDialogo, String vCodeCupon) {
        int cant_cupones_impresos = 0;

        try {
            
            VariablesCaja.vNumPedVta = "0000269058";
            String vHTML = DBCaja.obtieneImprCupon(VariablesCaja.vNumPedVta, FarmaVariables.vIPBD, vCodeCupon);
            if (!vHTML.equals("N")) {
                log.info("**** "+vCodeCupon+"****");
                log.info("**** INICIO html ****");
                log.info(""+vHTML);
                log.info("**** FIN html ****");
                
                (new FacadeCaja()).impresionEncabezadoCupon(vCodeCupon);
                
                PrintConsejo.imprimirHtml(vHTML, VariablesPtoVenta.vImpresoraActual,
                                           VariablesPtoVenta.vTipoImpTermicaxIp);
                
                DBCaja.cambiaIndImpresionCupon(VariablesCaja.vNumPedVta, vCodeCupon);
                
                cant_cupones_impresos++;
            }
            
            /// CREAR NUEVO //
            // 27.02.2017
            
            /// CREAR NUEVO //
            
        } catch (Exception sqlException) {
            log.error("",sqlException);
        }

        return cant_cupones_impresos;
    }
    
    private static List getDatosPrueba() throws Exception {
        ArrayList parametros = new ArrayList();
        List pRes = new ArrayList();
        log.info("FARMA_CUPON_ELECTRONICO.IMP_PRUEBA_CUPON"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_CUPON_ELECTRONICO.IMP_PRUEBA_CUPON",parametros);
        return pRes;
    }  
    
    private static List getImprimeDatos() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add("0000277745");
        parametros.add(FarmaVariables.vIPBD);
        parametros.add("C784965060027774501");
        parametros.add(FarmaVariables.vCodCia);
        List pRes = new ArrayList();
        log.info("FARMA_CUPON_ELECTRONICO.IMP_PROCESA_CUPON"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_CUPON_ELECTRONICO.IMP_PROCESA_CUPON(?,?,?,?,?,?)",parametros);
        return pRes;
    }

    public static void imprimeCodBarra() {
        try {
            List vListaDatosNew = getCodBarra("");
            (new UtilityImpCompElectronico()).impresionTermica(vListaDatosNew, null);
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
    
    private static List getImprimePrueba() throws Exception {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        //parametros.add("C7791850600000033");
        //parametros.add("554077");
        List pRes = new ArrayList();
        log.info("FARMA_CUPON_ELECTRONICO.CODIGO_BARRA"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_CUPON_ELECTRONICO.CODIGO_BARRA(?,?)",parametros);
        return pRes;
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
    
    public static void imprimeCuponFormato(String codCampania, int index) {
        try {
            List vListaDatosNew = getCuponFormato(codCampania,index);
            (new UtilityImpCompElectronico()).impresionTermica(vListaDatosNew, null);
            
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

    private static List getCuponFormato(String codCampania, int index) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codCampania);
        parametros.add(index);
        List pRes = new ArrayList();
        log.info("FARMA_TEST_DESA.IMP_CUPON_FORMATO(?,?,?,?)"+ parametros);        
        pRes = FarmaDBUtility.executeSQLStoredProcedureListMap("FARMA_TEST_DESA.IMP_CUPON_FORMATO(?,?,?,?)",parametros);
        return pRes;
    }

}
