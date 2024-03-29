package mifarma.ptoventa.fidelizacion.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;


/**
 * Copyright (c) 2008 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : ConstantsFidelizacion.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * DVELIZ      26.09.2008   Creaci�n<br>
 * <br>
 * @author Daniel Fernando Veliz La Rosa<br>
 * @version 1.0<br>
 *
 */

public class ConstantsFidelizacion {
    public ConstantsFidelizacion() {
    }
//INI ASOSA - 03/02/2015 - 
        public static final FarmaColumnData[] columnsListaDatosFidelizacion =
    { new FarmaColumnData("Desc.", 200, JLabel.LEFT), 
      new FarmaColumnData("Dato", 300, JLabel.RIGHT),
      new FarmaColumnData("Codigo", 0, JLabel.CENTER), 
      new FarmaColumnData("TI_DATO", 0, JLabel.CENTER),
      new FarmaColumnData("IN_SOLO_LECTURA", 0, JLabel.CENTER),
      new FarmaColumnData("IN_OBLIGATORIO", 0, JLabel.CENTER), 
      new FarmaColumnData("IND_VISIBLE", 0, JLabel.CENTER) };

    public static final Object[] defaultValuesListaDatosFidelizacion = { " ", " ", " ", " ", " ",
                                                                         " ", " " };

    public static final Boolean[] editableListaDatosFidelizacion =
    { new Boolean(true), new Boolean(true), new Boolean(true), new Boolean(true), new Boolean(true),
      new Boolean(true), new Boolean(true) };
    
//FIN ASOSA - 03/02/2015 - 

    public static final String CODIGO_CLIENTE = "001";
    public static final String DNI_CLIENTE = "002";
    public static final String NOMBRE_CLIENTE = "003";
    public static final String APEPAT_CLIENTE = "004";
    public static final String APEMAT_CLIENTE = "005";
    public static final String TELEFONO_CLIENTE = "006";
    public static final String DIREC_CLIENTE = "007";
    public static final String SEXO_CLIENTE = "008";
    public static final String FECHA_NAC_CLIENTE = "009";
    public static final String EMAIL_CLIENTE = "010";
    //INI ASOSA - 03/02/2015 - 
    public static final String CELULAR_CLIENTE = "011";
    public static final String DEPARTAMENTO = "012";
    public static final String PROVINCIA = "013";
    public static final String DISTRITO = "014";
    public static final String TIPO_DIRECCION = "015";
    public static final String REFERENCIAS = "016";
    public static final String TIPO_LUGAR = "017";
    //FIN ASOSA - 03/02/2015 - 

    public static final FarmaColumnData[] columnsDataTarjeta = { new

        FarmaColumnData("Nro.Tarjeta", 210, JLabel.LEFT) };

    public static final Object[] defaultsDtaTarjeta = { "" };

    /**
     * Constante Prefijo para generacion de nueva tarjeta de fidelizacion
     * @author  dveliz
     * @since   13.02.2009
     */
    public static final String PREFIJO_TARJETA_FIDELIZACION = "999";


    public static String NOM_HASTABLE_CMB_TIP_DOC = "CMB_TIP_DOC";

    public static String COD_FP_EFECTIVO_TOTAL = "E0000";
    public static String COD_FP_TARJETA_TOTAL = "T0000";
    
    public static final String COD_TIPO_DOC_DNI = "01";
    public static final String COD_TIPO_DOC_CARNET_EXTRANJERIA = "02";
    public static final String COD_TIPO_DOC_DNI_EXTRANJERO = "04";
    public static final String COD_TIPO_DOC_RUC= "05";
    public static final String COD_FPAGO_TARJ_OH = "00096";
    

}
