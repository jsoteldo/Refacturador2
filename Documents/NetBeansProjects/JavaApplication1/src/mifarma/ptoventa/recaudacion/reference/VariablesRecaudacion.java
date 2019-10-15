package mifarma.ptoventa.recaudacion.reference;

import java.util.ArrayList;

public class VariablesRecaudacion {
    /******************** TIPO TRANSACCION BANCO FINANCIERO ************************************/
    public static final String PAGO_TARJ_BFP = "30";
    public static final String PAGO_PRESTAMO_BFP = "50";
    public static final String DEPOSITO_CTA_BFP = "10";
    
    /******************** TIPO PRESTAMO BANCO FINANCIERO ************************************/
    public static final String PRESTAMO_PERSONAL = "51";
    public static final String PRESTAMO_MICROEMPRESA = "52";
    public static final String PRESTAMO_CONSUMO = "53";
    
    public static String RAZON_SOCIAL_BFP = "Diners Club y Banco Financiero"; // despues del 20/08/2018 BANCO FINACIERO CAMBIA A BANCO PICHINCHA
    
    public static String vVersionBFP="01";
    public static String vTipoMonedaOrigen="01";
    public static double vTipoCambioVenta;
    public static double vTipoCambioCompra;
    public static double vTipoCambioBFP;
    public static String vCuotaPrestamo_BFP;
    public static String vIdCajero_cobro;
    
    public static String vNroRecibo = "";
    
    public static int vNroIntentos = 0;
    public static int vCantSegEspere = 0;
    
    public static String vIndicadorSix = "";
    
    public static String vMensajeError = "";
    
    public static String vNombre_Cliente_BFP = "";
    public static String vDNI_Usuario = "";
    
    public static String vMontoPago = "";
    public static String vCodCabRecau = "";
    
    public static String vTipoOperacion_BFP = "";
    public static ArrayList<Object> arrayCabecera=new ArrayList<Object>();
}
