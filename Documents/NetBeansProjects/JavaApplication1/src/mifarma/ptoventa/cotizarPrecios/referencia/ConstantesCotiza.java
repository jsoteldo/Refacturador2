package mifarma.ptoventa.cotizarPrecios.referencia;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;


public class ConstantesCotiza {
    /*public static Color colorNaranja = new Color(255,130,14);
    public static Color colorPlomo = new Color(237,237,237);
    public static Color colorBlack = new Color(0,0,0);
    public static Color colorWhite = new Color(255,255,255);
    public static Color colorGreen = new Color(43,141,39);   
    private static Color colorYelow = ConstantsColor.amarillo;*/
    
    /*** INICIO CCASTILLO 28/08/2017 ***/
    
   /* public static final int INDEX_RUC_COMPETENCIA = 0;
    public static final int INDEX_COMPETENCIA = 1;
    public static final int INDEX_FECHA_SOLICITUD = 2;
    public static final int INDEX_FECHA_VIGENCIA = 3;
    public static final int INDEX_ITEMS = 4;
    public static final int INDEX_ESTADO = 5;
    public static final int INDEX_COD_SOLIC = 6;*/
    
   public static final int INDEX_COD_SOLIC = 0; 
   public static final int INDEX_RUC_COMPETENCIA = 1;
   public static final int INDEX_COMPETENCIA = 2;
   public static final int INDEX_FECHA_SOLICITUD = 3;
   public static final int INDEX_FECHA_VIGENCIA = 4;
   public static final int INDEX_ITEMS = 5;
   public static final int INDEX_ESTADO = 6;
   public static final int INDEX_PRIORIDAD = 7;
    public static final int INDEX_VAL_PREC_VTA = 12;


    /*public static final FarmaColumnData columnsListaSolicitud[] =
    { new FarmaColumnData("RUC Comp.", 100, JLabel.CENTER),
      new FarmaColumnData("Competencia", 130, JLabel.LEFT),
      new FarmaColumnData("Fec. Solicitud", 90, JLabel.CENTER),
      new FarmaColumnData("Fec. vigencia", 90, JLabel.CENTER),
      new FarmaColumnData("Items", 50, JLabel.CENTER),
      new FarmaColumnData("Estado", 110, JLabel.CENTER),
      new FarmaColumnData("", 0, JLabel.CENTER),//Cod_Solicitud
      new FarmaColumnData("", 0, JLabel.CENTER)//Cod_Prioridad
    };*/
    public static final FarmaColumnData columnsListaSolicitud[] =
    { new FarmaColumnData("Cod. Solicitud", 100, JLabel.CENTER),//Cod_Solicitud
      new FarmaColumnData("RUC Comp.", 100, JLabel.CENTER),
      new FarmaColumnData("Competencia", 130, JLabel.LEFT),
      new FarmaColumnData("Fec. Solicitud", 90, JLabel.CENTER),
      new FarmaColumnData("Fec. vigencia", 90, JLabel.CENTER),
      new FarmaColumnData("Items", 50, JLabel.CENTER),
      new FarmaColumnData("Estado", 110, JLabel.CENTER),
      new FarmaColumnData("", 0, JLabel.CENTER)//Cod_Prioridad
    };
    /*** FIN CCASTILLO 28/08/2017 ***/
    
    public static final Object[] defaultValuesListaSolicitud  =
    { " ", " ", " ", " ", " "," "," "};
    
    public static final int INDEX_COD_PROD = 0;
    public static final int INDEX_PRODUCTO = 1;
    /*** INICIO CCASTILLO 28/08/2017 ***/
    /*public static final int INDEX_UNIDAD = 2;
    public static final int INDEX_LABORATORIO = 3;*/
    public static final int INDEX_UNIDAD = 3;
    public static final int INDEX_LABORATORIO = 2;
    /*** FIN CCASTILLO 28/08/2017 ***/
    public static final int INDEX_CANTIDAD = 4;
    public static final int INDEX_IND_VALIDO = 5;    
    public static final int INDEX_CANT_INGRE = 6;
    public static final int INDEX_PRECIO_UNIT = 7;
    public static final int INDEX_SUB_TOTAL = 8;
    public static final int INDEX_LOTE = 9;
    public static final int INDEX_FECHA_VENC = 10;
    public static final int INDEX_VAL_FRAC = 11;
    
    public static final FarmaColumnData columnsProducto_Solicitud[] =   
    { new FarmaColumnData("Codigo", 80, JLabel.CENTER),
      new FarmaColumnData("Descripcion Producto", 220, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 170, JLabel.LEFT),
      new FarmaColumnData("Unidad", 120, JLabel.LEFT),
      new FarmaColumnData("Cant.Solici",74, JLabel.CENTER),
      new FarmaColumnData("",0, JLabel.CENTER),//ind_valido
      new FarmaColumnData("Cant. Ingre.", 0, JLabel.RIGHT),
      new FarmaColumnData("Prec. Unit.", 0, JLabel.RIGHT), 
      new FarmaColumnData("SubTotal", 0, JLabel.RIGHT),
      new FarmaColumnData("Lote", 0, JLabel.RIGHT), 
      new FarmaColumnData("Fecha Venc.", 0, JLabel.RIGHT),
      new FarmaColumnData("Val. Frac.", 0, JLabel.RIGHT),
      new FarmaColumnData("Val.PrecVenta", 0, JLabel.RIGHT)
    };    
    
    public static final Object[] defaultValuesProducto_Solicitud  =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "," " };
    
    public static final FarmaColumnData columnsListaProducto[] =
    { new FarmaColumnData("Código", 80, JLabel.LEFT),// 1
      new FarmaColumnData("Descripción", 200, JLabel.LEFT), // 2
      new FarmaColumnData("Unidad", 100, JLabel.LEFT),// 3
      new FarmaColumnData("Laboratorio", 180, JLabel.LEFT), // 4
      new FarmaColumnData("", 0, JLabel.RIGHT),//Stock 5
      new FarmaColumnData("", 0, JLabel.RIGHT), //Precio 6
      new FarmaColumnData("", 0, JLabel.RIGHT),//Zan 7
      new FarmaColumnData("", 0, JLabel.LEFT), //indicador producto congelado 8
      new FarmaColumnData("", 0, JLabel.RIGHT), //valor de fraccion de local 9
      new FarmaColumnData("", 0, JLabel.RIGHT), //valor precio lista 10
      new FarmaColumnData("", 0, JLabel.RIGHT), //valor igv producto 11
      new FarmaColumnData("", 0, JLabel.LEFT), //indicador de producto farma 12
      new FarmaColumnData("", 0, JLabel.RIGHT), //indicador prod virtual 13
      new FarmaColumnData("", 0, JLabel.LEFT), //tipo de prod virtual 14
      new FarmaColumnData("", 0, JLabel.LEFT), //indicador prod refrigerado 15
      new FarmaColumnData("", 0, JLabel.LEFT), //indicador tipo producto 16
      new FarmaColumnData("", 0, JLabel.LEFT), //indicador  producto promocion 17 
      new FarmaColumnData("", 0, JLabel.LEFT), // ordLista 18 
      new FarmaColumnData("", 0, JLabel.LEFT), // indProdEncarte 19
      new FarmaColumnData("", 0, JLabel.LEFT) ,// indOrigen 20
      //-------------------------------------------------------
      new FarmaColumnData("", 0, JLabel.RIGHT), //Normal 21
      new FarmaColumnData("", 0, JLabel.RIGHT), //Monedero 22
      new FarmaColumnData("", 0, JLabel.RIGHT)//50 Años 23
      //-------------------------------------------------------
      };
    
    public static final Object[] defaultValuesListaProducto  =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", 
      " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
    
    /*public static final FarmaColumnDataUtil[] columnsListaResumenCotizacion =
    { new FarmaColumnDataUtil("Código", 50, JLabel.LEFT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow), 
      new FarmaColumnDataUtil("Descripción", 200, JLabel.LEFT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow),
      new FarmaColumnDataUtil("Unidad", 100, JLabel.LEFT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow), 
      new FarmaColumnDataUtil("Laboratorio", 150, JLabel.LEFT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow),
      new FarmaColumnDataUtil("Cant. Pend.", 70, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow),
      new FarmaColumnDataUtil("Ind. Válido", 0, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow), 
      //new FarmaColumnData("Cant. Ingre.", 70, JLabel.RIGHT),
      new FarmaColumnDataUtil("Cant. Ingre.",70, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow),
      new FarmaColumnDataUtil("Prec. Unit.", 80, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow), 
      new FarmaColumnDataUtil("SubTotal", 80, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow),
      new FarmaColumnDataUtil("Lote", 0, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow), 
      new FarmaColumnDataUtil("Fecha Venc.", 0, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow),
      new FarmaColumnDataUtil("Val. Frac.", 0, JLabel.RIGHT,false,Double.class,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow,colorYelow)
      };*/
    
    public static final FarmaColumnData[] columnsListaResumenCotizacion =
    { new FarmaColumnData("Código", 50, JLabel.LEFT), 
      new FarmaColumnData("Descripción", 200, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 150, JLabel.LEFT),
      new FarmaColumnData("Unidad", 100, JLabel.LEFT), 
      new FarmaColumnData("Cant. Pend.", 70, JLabel.RIGHT),
      new FarmaColumnData("Ind. Válido", 0, JLabel.RIGHT), 
      new FarmaColumnData("Cant. Ingre.", 70, JLabel.RIGHT),
      new FarmaColumnData("Prec. Unit.", 80, JLabel.RIGHT), 
      new FarmaColumnData("SubTotal", 80, JLabel.RIGHT),
      new FarmaColumnData("Lote", 0, JLabel.RIGHT), 
      new FarmaColumnData("Fecha Venc.", 0, JLabel.RIGHT),
      new FarmaColumnData("Val. Frac.", 0, JLabel.RIGHT),
      new FarmaColumnData("Val. Prec Vta.", 0, JLabel.RIGHT),
      new FarmaColumnData("Cod Solicitud", 0, JLabel.RIGHT)
      ,new FarmaColumnData("Cod Motivo", 0, JLabel.RIGHT)
      ,new FarmaColumnData("Indicador Tiene Cotizacion", 0, JLabel.RIGHT)
      ,new FarmaColumnData("Cantidad Minima", 0, JLabel.RIGHT)
      ,new FarmaColumnData("Cantidad Solicitada divisible local", 0, JLabel.RIGHT)
      };

    public static final Object[] defaultListaResumenCotizacion = 
        { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," "," "," "," "," "};
}
