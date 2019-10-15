package mifarma.ptoventa.cotizarPrecios.modelo;

import java.util.ArrayList;

public class VarCotizacionPrecio {
    public static String vCod_Solic = "";
    public static String vCod_Prod = "";
    public static String vVal_Frac = "";
    public static String vCant_Solic = "";
    public static String vCant_Ingre = "";
    public static String vUnidad = "";
    public static String vValido = "";
    
    public static String vProducto = "";
    public static String vLaboratorio = "";
    
    public static String vRUC_Comp = "";
    public static String vCompetencia = "";
    public static String vFec_Vigencia = "";
    
    //CABECERA
    public static String vTipo_Doc = "";
    public static String vDesc_Doc = "";
    public static String vNum_Doc = "";
    public static String vTipo_Origen = "";
    public static String vCod_Origen = "";
    public static String vFecha_Cotizacion = "";
    public static String vSerieDoc = "";
    public static String vNroGuia = "";
    public static String vCiudadTienda = "";
    public static String vTipo_Proceso = "";
    public static String vRuta_Archivo = "";
    
    //INI VARIABLES PARA INGRESO DE CANTIDAD PARA COTIZACIÓN
    public static String vIC_Cod_Prod = "";
    public static String vIC_Lote = "";
    public static String vIC_Fec_Vigencia = "";
    public static String vIC_Cant_Pendiente = "";
    public static int vIC_Cant_Comprada = 0;
    public static String vIC_Precio_Unitario = "";
    public static String vIC_Total = "";
    /*** INICIO CCASTILLO 31/08/2017 ***/
    public static String vIC_MotivoNoCot = "";
    /*** FIN CCASTILLO 31/08/2017 ***/
    public static ArrayList vIC_ArrayProductosCotizacion = new ArrayList();
    //FIN VARIABLES PARA INGRESO DE CANTIDAD PARA COTIZACIÓN
    
    public static String vNum_Nota_Competencia = "";
    public static int vCondicion = 2;
    
    public static String vValPrecVta = "";
    
   public static final String TIP_COTIZA_COMPRAR = "1";    
   public static final String TIP_COTIZA_COTIZAR = "2";
   public static final String TIP_COTIZA_SIN_SOLICITUD = "3";
        
    public VarCotizacionPrecio() {
        super();
    }
}
