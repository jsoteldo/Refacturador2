package mifarma.ptoventa.fraccion.modelo;

import java.util.ArrayList;

public class VariableSolicitud {
    public VariableSolicitud() {
    }
    //public static final int INDEX_Cod_Solic = 0;
    public static final int INDEX_Nro_Solic = 0;
    public static final int INDEX_Fecha_Solic = 1;
    public static final int INDEX_User_Solic = 2;
    public static final int INDEX_Estado_Solic = 3;
    public static final int INDEX_Fecha_Atenc = 4;
    
    public static ArrayList<ArrayList> listaSolicitud = new ArrayList();
    public static ArrayList<ArrayList> listaProductos = new ArrayList();
    
    //General  
    public static String vCod_GrupCIA = "";
    public static String vCod_Local = "";
    public static String vCod_Solic = "";
    public static String vNro_Solic = "";
    public static String vFecha_Solic = "";
    public static String vUsua_Solic = "";
    public static String vEstad_Solic = "";
    public static String vFecha_Atend = "";
    public static String vUsua_Atend = "";
    public static String vCod_Local_Mod = "";
}
