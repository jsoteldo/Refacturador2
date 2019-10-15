package mifarma.ptoventa.fraccion.modelo;

import java.util.ArrayList;


public class VariableProducto {
    public VariableProducto() {
    }
    
    public static final int INDEX_Cod_Prod = 0;
    public static final int INDEX_Cod_Lab = 1;
    public static final int INDEX_Cod_Motivo_Frac = 2;
    public static final int INDEX_Tipo_Frac = 3;
    public static final int INDEX_Comentario = 4;
    
    public static ArrayList<ArrayList> listaMemoriaProd = new ArrayList();
    public static ArrayList<ArrayList> listaProductos = new ArrayList();
    public static ArrayList<ArrayList> listaSeleccion = new ArrayList();
    //General  
    public static String vCod_Prod = "";
    public static String vCod_Lab = "";
    public static String vCod_Motivo_Frac = "";
    public static String vTipo_Frac = "";
    public static String vComentario = "";
    
    public static String vDesc_Prod = "";
    public static String vCod_Unid_Pres = "";
    public static String vDesc_Unid_Pres = "";
    public static String vDesc_Lab = "";
    public static String vInd_Fracionable = "";
    public static String vDesc_Tip_Frac = "";
    public static String vDesc_FracCambio = "";
}
