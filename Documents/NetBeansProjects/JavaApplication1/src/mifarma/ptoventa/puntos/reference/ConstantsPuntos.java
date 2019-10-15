package mifarma.ptoventa.puntos.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;


public class ConstantsPuntos {
    public ConstantsPuntos() {
    }
    
    public static final String BONIFICA_CON_TARJETA     = "T";
    public static final String BONIFICA_CON_DNI         = "D";
    public static final String BONIFICA_CON_AMBOS       = "A";
    public static final String BONIFICACION_PRODUCTOS   = "B";
    public static final String REDENCION_PTOS           = "R";
    public static final String SOLICITA_TARJ_OFFLINE    = "O";
    public static final String IND_PROCESO_ORBIS_ACT    = "S";
    public static final String IND_PROCESO_ORBIS_DESACT = "N";
    public static final String DEFAULT_REQUIERE_TARJETA = "I";
    public static final String VALIDA_TARJETA_ASOCIADA  = "TA";
    public static final String BLOQUEO_TARJETA          = "BT";
    public static final String DESAFILIACION_PROGRAMA_X1= "DX";
    
    /*ZVILLAFUERTE*/
    public static final String REDENCION_PTOS_DNI_TARJETA_REQ = "R";
    public static final String REDENCION_PTOS_DNI_TARJETA_OPC = "O";
   /*ZVILLAFUERTE*/
    
    public static final String TRSX_ORBIS_PENDIENTE     = "P";
    public static final String TRSX_ORBIS_ENVIADA       = "E";
    public static final String TRSX_ORBIS_NO_APLICA     = "N";
    public static final String TRSX_ORBIS_DESCARTADA    = "D";
    
    public static final String MNTO_FIDELIZACION = "MOD";    //ASOSA - 28/04/2015 - PTOSYAYAYAYA
    
    public static final int RECHAZA_AFILIACION_TARJ     = 1;
    public static final int TARJETA_NUEVA               = 2;
    public static final int TARJETA_ACTIVA              = 3;
    public static final int TARJETA_BLOQUEADA           = 4;
    public static final int TARJETA_INVALIDA            = 5;
    public static final int ERROR_CONSULTA_TARJETA      = 6;
    public static final int FUNCION_PTOS_DESACTIVA      = 7;
    public static final int TARJETA_OFFLINE             = 8;
    
    public static final String TARJ_PTOS_TIPO_TARJETA_CONVENIO     = "TC";
    public static final String TARJ_PTOS_TIPO_CLUB_PROPIETARIOS    = "TP";

    public static final  class HiloFarmaPuntos {
    public static String HILO_INIT="HILO_INIT";
    public static String HILO_QUOTE="HILO_QUOTE";
    public static String HILO_SALE="HILO_SALE";
    }


    public static final FarmaColumnData columnsListaProductosBonificados[] ={ 
        new FarmaColumnData("Sel", 30, JLabel.CENTER),          //0
        new FarmaColumnData("Código", 50, JLabel.LEFT),         //1
        new FarmaColumnData("Descripción", 200, JLabel.LEFT),   //2    
        new FarmaColumnData("Unidad", 100, JLabel.LEFT),        //3
        new FarmaColumnData("Laboratorio", 150, JLabel.LEFT),   //4
        new FarmaColumnData("# Regalos", 100, JLabel.RIGHT),         //5
        new FarmaColumnData("", 0, JLabel.LEFT)      ,          //6 codigo equivalente
        new FarmaColumnData("", 0, JLabel.LEFT)      ,          //7 codigo de programa 
        new FarmaColumnData("", 0, JLabel.LEFT)      ,          //8 
        new FarmaColumnData("", 0, JLabel.LEFT)      ,          //9 cantidad a bonificar
        new FarmaColumnData("", 0, JLabel.LEFT)                //10 Stock
      };
    

    public static final Object[] defaultValuesListaProductosBonificados =
    { " ", " ", " ", " ", " ", " ", " ", " "};
    
    public static final class COD_MAESTRO {
        public static final String TIPO_MENSAJE_ERROR_LEALTAD = "17";
    }
}

    

