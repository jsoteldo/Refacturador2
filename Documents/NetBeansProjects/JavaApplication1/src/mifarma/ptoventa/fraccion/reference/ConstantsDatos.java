package mifarma.ptoventa.fraccion.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;


public class ConstantsDatos {
    /** Contantes de tabla solicitud*/
    public static final int INDEX_NRO_SOLICITUD = 0;
    public static final int INDEX_FECHA_SOLICITUD = 1;
    public static final int INDEX_SOLICITANTE = 2;
    public static final int INDEX_ESTADO_SOLICITUD = 3;
    public static final int INDEX_FECHA_ATENCION = 4;
    
    
    public static final FarmaColumnData columnsListaSolicitud[] =
    { //new FarmaColumnData("Cod Local", 80, JLabel.CENTER),
      new FarmaColumnData("Nro Solicitud", 80, JLabel.CENTER),
      new FarmaColumnData("Fch Solicitud", 100, JLabel.CENTER),
      new FarmaColumnData("Items", 50, JLabel.CENTER),
      new FarmaColumnData("Solicitante", 200, JLabel.LEFT),
      new FarmaColumnData("Estado", 100, JLabel.CENTER),
      new FarmaColumnData("Fch Atencion", 100, JLabel.CENTER),
      new FarmaColumnData("", 0, JLabel.CENTER)//Local que crea
    };
    
    public static final Object[] defaultValuesListaSolicitud  =
    { " ", " ", " ", " ", " "," ", " "};
    
    /** Contantes de tabla producto_solicitud*/
    public static final int INDEX_CODIGO = 0;
    public static final int INDEX_DESCRIPCION = 1;
    public static final int INDEX_PRESENTACION = 2;
    public static final int INDEX_LABORATORIO = 3;
    public static final int INDEX_ESTADO_PRODUCTO = 4;
    
    
    public static final FarmaColumnData columnsProducto_Solicitud[] =
    { new FarmaColumnData("Codigo", 60, JLabel.CENTER),
      new FarmaColumnData("Descripcion", 120, JLabel.LEFT),
      new FarmaColumnData("Presentacion", 100, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 120, JLabel.LEFT),
      new FarmaColumnData("",0, JLabel.LEFT),//Frac.Hist.
      new FarmaColumnData("",0, JLabel.LEFT),//Frac.Vig.
      new FarmaColumnData("Tipo Frac", 100, JLabel.LEFT),
      new FarmaColumnData("Estado", 70, JLabel.CENTER),        
      new FarmaColumnData("Comentario Atencion", 150, JLabel.LEFT)
    };
    
    public static final Object[] defaultValuesProducto_Solicitud  =
    { " ", " ", " ", " ", " ", " ", " ", " "};
    
    /** Contantes de tabla Lista_producto - en DlgNuevaSolicitud*/
    public static final int INDEX_CODI_PROD = 0;
    public static final int INDEX_DESC_PROD = 1;
    public static final int INDEX_PRES_PROD = 2;
    public static final int INDEX_UN_FRAC_S = 3;
    public static final int INDEX_UN_FRAC_M = 4;
    public static final int INDEX_STOCK_LIS = 5;
    
    public static final FarmaColumnData columnsListaProducto[] =
    { new FarmaColumnData("Codigo", 50, JLabel.CENTER),
      new FarmaColumnData("Descripcion", 190, JLabel.LEFT),
      new FarmaColumnData("Presentacion", 90, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 120, JLabel.LEFT),
      new FarmaColumnData("Frac. Venta", 80, JLabel.LEFT),
      new FarmaColumnData("Frac. Sug", 80, JLabel.LEFT),
      new FarmaColumnData("Frac. Min", 0, JLabel.LEFT),
      new FarmaColumnData("Stock", 50, JLabel.CENTER),
      new FarmaColumnData("", 0, JLabel.CENTER)//indicador fraccionable
    };
    
    public static final Object[] defaultValuesListaProducto  =
    { " "," "," "," "," ", " ", " ", " ", " "};
    
    /** Contantes de tabla producto_Seleccionado*/
    
     public static final int INDEX_CODIGO_SELECC = 0;
     public static final int INDEX_DESCRI_SELECC = 1;
     public static final int INDEX_PRESEN_SELECC = 2;
     public static final int INDEX_LABORA_SELECC = 3;
     public static final int INDEX_TIP_FRAC_SELE = 5;
     
    public static final FarmaColumnData columnsProducto_Seleccion[] =
    { new FarmaColumnData("Codigo", 50, JLabel.CENTER),
      new FarmaColumnData("Descripcion", 140, JLabel.LEFT),
      new FarmaColumnData("Presentacion", 90, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 125, JLabel.LEFT),
      new FarmaColumnData("Frac.Actual",100, JLabel.LEFT),
      new FarmaColumnData("Frac.Cambio",100, JLabel.LEFT),
      new FarmaColumnData("Tipo Frac.", 135, JLabel.LEFT)
    };
    
    public static final Object[] defaultValuesProducto_Seleccion  =
    { " ", " ", " "," ", " ", " ", " "};
        
}
