package mifarma.ptoventa.UtilFarma;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;


public class ConstantesUtil {
    public static String simboloSoles="Error";
    public static String describeSoles="Error";
        
    public static void simboloSoles_DB(){
        try{
            ArrayList parametros = new ArrayList();
            simboloSoles=FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_UTILITY.RECUPERA_SIMBOLO_SOLES", 
                                                                     parametros);
        }catch(Exception e){
            System.out.println("Error al recuperar el simbolo de moneda soles");
            e.printStackTrace();
        }
    }
    
    public static void describeSoles_DB(){
        try{
            ArrayList parametros = new ArrayList();
            describeSoles=FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_UTILITY.RECUPERA_DESCRIPCION_SOLES", 
                                                                     parametros);
        }catch(Exception e){
            System.out.println("Error al recuperar la descripcion de moneda soles");
            e.printStackTrace();
        }
    }
}
