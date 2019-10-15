package farmaciasperuanas.reference;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

public class DBRefacturadorElectronico {
    public static ArrayList parametros;
    
    public static String getNumPedidoVenta(String pCodGrupoCia, String pCodLocal, String pNumCompPagoE ) throws Throwable{
        String vReturn = "";
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumCompPagoE);
        vReturn = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_GET_NUMPEDIDOVENTA(?,?,?)",
                                                           parametros);
        return vReturn;
    }
    
    public static boolean isPedidoAnulado(String pCodGrupoCia, String pCodLocal, String pNumPedVenta) throws Throwable{
        boolean vReturn;
        String vVar;
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVenta);
        vVar = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_IS_ANULADO(?,?,?)",
                                                           parametros);
        if(vVar.equalsIgnoreCase("T")){
            vReturn = true;
        }else if(vVar.equalsIgnoreCase("F")){
            vReturn = false;
        }else{
            throw new Exception("Valor retornado inesperado : "+vVar);
        }
        return vReturn;
    }

    public static ArrayList consultaComprobante(String pCodGrupoCia, String pCodLocal, String pNumCompPagoE ) throws Throwable{
        ArrayList vReturn = new ArrayList();
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumCompPagoE);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vReturn,"PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_CONSULTA_COMP(?,?,?)",
                                                           parametros);
        return vReturn;
    }
    public static ArrayList findVentaCabecera(String pCodGrupoCia, String pCodLocal, String pNumPedVenta) throws Throwable{
        ArrayList vReturn = new ArrayList();
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVenta);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vReturn,"PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_GET_VENTA_CAB(?,?,?)",
                                                           parametros);
        return vReturn;
    }
    
    public static void backupPedidoVenta(String pCodGrupoCia, String pCodLocal, String pNumCompPagoE ) throws Throwable{
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumCompPagoE);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.SP_BACKUP_PED_VENTA(?,?,?)", parametros, false);        
    }
    
    public static void actualizacionPedidoVenta(String pCodGrupoCia, String pCodLocal, String pNumPedVta ) throws Throwable{
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.SP_ACT_VENTA_PEDIDO(?,?,?)", parametros, false);        
    }

    public static ArrayList buscarDetallePedidoVenta(String pCodGrupoCia, String pCodLocal)throws Throwable{
        ArrayList vReturn = new ArrayList();
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vReturn,"PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_CONSULTA_COMP_DET(?,?)",
                                                           parametros);
        return vReturn;
    }
    public static void actualizarComprobantePago(String pCodGrupoCia, String pCodLocal, String pNumPedVta ) throws Throwable{
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.SP_ACT_VENTA_COMP(?,?,?)", parametros, false);        
    }
    
    public static void actualizarFormaPago(String pCodGrupoCia, String pCodLocal, String pNumPedVta ) throws Throwable{
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVta);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.SP_ACT_VENTA_FORMAPAGO(?,?,?)", parametros, false);        
    }
    
    public static void actualizarInfoConvenio(String pCodGrupoCia, String pCodLocal, String pNumPedVtaNew ) throws Throwable{
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumPedVtaNew);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.SP_ACT_VENTA_INFOCONVENIO(?,?,?)", parametros, false);      
    }
    
    public static ArrayList findInfoConvenio(String pCodGrupoCia, String pCodLocal) throws Throwable{
        ArrayList vReturn = new ArrayList();
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vReturn,"PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_GET_VENTA_INFOCONVENIO(?,?)",
                                                           parametros);
        return vReturn;
    }
    public static ArrayList findConvenio(String pCodGrupoCia, String pCodLocal) throws Throwable{
        ArrayList vReturn = new ArrayList();
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vReturn,"PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_GET_CONVENIO(?,?)",
                                                           parametros);
        return vReturn;
    }
    public static String findUsuario(String pCodGrupoCia, String pCodLocal, String pNumCompPagoE ) throws Throwable{
        String vReturn = "";
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pNumCompPagoE);
        vReturn = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_GET_VENTA_USUARIO(?,?,?)",
                                                           parametros);
        return vReturn;
    }
    
    public static String getClaveUsu(String pCodGrupoCia, String pCodLocal, String pSecUsuLocal ) throws Throwable{
        String vReturn = "";
        parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(pCodLocal);
        parametros.add(pSecUsuLocal);
        vReturn = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA.REEL_REFACTURADOR_ELECTRONICO.F_GET_VENTA_USUARIO_PASS(?,?,?)",
                                                           parametros);
        return vReturn;
    }
}
