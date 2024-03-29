package mifarma.ptoventa.recepcionCiega.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;


public class ConstantsRecepCiega {
    public ConstantsRecepCiega() {
    }

    public static final FarmaColumnData columnsListaRecepcion[] =
    { new FarmaColumnData("N� Ingreso", 90, JLabel.CENTER), new FarmaColumnData("Fecha Ingreso", 130, JLabel.CENTER),
      new FarmaColumnData("Usuario Creacion", 110, JLabel.CENTER),
      new FarmaColumnData("Cant Guias", 80, JLabel.CENTER), new FarmaColumnData("Estado", 120, JLabel.LEFT),
         };

    public static final Object[] defaultValuesListaRecepcion = { " ", " ", " ", " ", " " };

    public static final FarmaColumnData columnsListaGuias[] = { new FarmaColumnData("Sel", 25, JLabel.CENTER), //0
        new FarmaColumnData("Guia", 95, JLabel.LEFT), //1
        new FarmaColumnData("Num. Entrega", 95, JLabel.LEFT), //2
        new FarmaColumnData("Fecha", 130, JLabel.LEFT), //3
        new FarmaColumnData("Almacen", 130, JLabel.LEFT), //4                                                                
        } ;
    public static final Object[] defaultValuesListaGuias = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };


    public static final FarmaColumnData columnsListaGuias2[] =
    { new FarmaColumnData("Guia", 95, JLabel.LEFT), new FarmaColumnData("Num. Entrega", 95, JLabel.LEFT),
      new FarmaColumnData("Fecha", 130, JLabel.LEFT),
        };

    public static final Object[] defaultValuesListaGuias2 = { " ", " ", " ", " ", " ", " ", " ", " ", " " };
    //DlgDetalleGuia
    public static final FarmaColumnData columnsListaDetalleGuia[] =
    { new FarmaColumnData("Codigo", 50, JLabel.LEFT), new FarmaColumnData("Descripcion", 240, JLabel.LEFT),
      new FarmaColumnData("Unidad", 120, JLabel.LEFT), new FarmaColumnData("Laboratorio", 165, JLabel.LEFT),
      new FarmaColumnData("Cant.", 40, JLabel.RIGHT),
        } ;

    public static final Object[] defaultValuesListaDetalleGuia = { " ", " ", " ", " ", " " };

    //DlgOrdenar
    public static final String[] vDescColum = { "GUIA", "NUM_ENTREGA", "FECHA" };
    public static final String[] vCodColum = { "1", "2", "3" };
    public static final String NOM_HASTABLE_CMBCOLUMN = "CMB_COLUMN";

    public static final String[] vDescOrden = { FarmaConstants.ORDEN_ASCENDENTE, FarmaConstants.ORDEN_DESCENDENTE };
    public static final String[] vCodOrden = { "ASC", "DESC" };
    public static final String NOM_HASTABLE_CMBORDEN = "CMB_ORDERN";

    public static final FarmaColumnData columnsListaGuiasEntre[] =
    { new FarmaColumnData("Guia", 90, JLabel.LEFT), new FarmaColumnData("Num. Entrega", 90, JLabel.LEFT),
      new FarmaColumnData("Fecha", 100, JLabel.LEFT), new FarmaColumnData("NumNota", 90, JLabel.LEFT), //NumNotaEst
        new FarmaColumnData("Pag.", 40, JLabel.CENTER), new FarmaColumnData("Items", 40, JLabel.CENTER),
        new FarmaColumnData("Prods", 40, JLabel.CENTER), new FarmaColumnData("Estado", 70, JLabel.CENTER) }; //Estado

    public static final Object[] defaultValuesListaGuiasEntre = { " ", " ", " ", " ", " ", " ", " ", " " };

    /**
     * @since 2.3.3 ERIOS Nuevo campos
     */
    public static final FarmaColumnData[] columnsListaProductosSegundoConteo =
    { new FarmaColumnData("C�digo", 50, JLabel.CENTER), new FarmaColumnData("Descripci�n Producto", 230, JLabel.LEFT),
      new FarmaColumnData("Unidad", 90, JLabel.LEFT), new FarmaColumnData("Laboratorio", 145, JLabel.LEFT),
      new FarmaColumnData("En G/R", 45, JLabel.CENTER), new FarmaColumnData("Rcp.Ciega", 63, JLabel.CENTER),
      new FarmaColumnData("Reconteo", 63, JLabel.CENTER), new FarmaColumnData("Resultado", 80, JLabel.LEFT),
      new FarmaColumnData("CodProd", 0, JLabel.LEFT), new FarmaColumnData("SecConteo", 0, JLabel.LEFT) };

    public static final Object[] defaultcolumnsListaProductosSegundoConteo =
    {"100359","EUCERIN BA�O & SHAMPOO","FCO 250 ML","BEIERSDORF","0","1"," ","+1 Sobrante","100359","4"};

    /**
     * @since 06.08.2015 Bultos por producto
     */
    public static final FarmaColumnData[] columnsListaProductosSegundoConteoBultos =
    { new FarmaColumnData("Bulto", 150, JLabel.LEFT), 
      new FarmaColumnData("Bulto", 150, JLabel.LEFT),
      new FarmaColumnData("Bulto", 150, JLabel.LEFT),
      new FarmaColumnData("Bulto", 150, JLabel.LEFT),
      new FarmaColumnData("Bulto", 150, JLabel.LEFT)};

    public static final Object[] defaultcolumnsListaProductosSegundoConteoBultos =
    { "B0001 (1000 CJA)", "B00002 (1000 TBO)", "B00003 (1000 FCO)", "B00004 (1000 KIT)", "B00005 (1000 DISP)", "0000000006 (1000)","0000000007 (1000)", "0000000008 (1000)", "0000000009 (1000)" };
    
    public static final FarmaColumnData[] columnsListaGuiasPendientes =
    { new FarmaColumnData("Num. Gu�a", 90, JLabel.LEFT), new FarmaColumnData("Num. Entrega", 100, JLabel.LEFT),
      new FarmaColumnData("Fecha", 100, JLabel.LEFT) };

    public static final Object[] defaultcolumnsListaGuiasPendientes = { " ", " ", " " };

    public static final FarmaColumnData[] columnsListaProductosFaltantes = {
        //  new FarmaColumnData("Cod. Barra",70,JLabel.LEFT),
        new FarmaColumnData("C�digo", 50, JLabel.LEFT), new FarmaColumnData("Desc. Producto", 200, JLabel.LEFT),
        new FarmaColumnData("Unidad", 90, JLabel.LEFT), new FarmaColumnData("Laboratorio", 180, JLabel.LEFT),
        new FarmaColumnData("Contada", 62, JLabel.LEFT), new FarmaColumnData("Entregada", 67, JLabel.LEFT),
        //  new FarmaColumnData("Diferencia",0,JLabel.LEFT),
        new FarmaColumnData("Nro. Entrega", 100, JLabel.LEFT) };

    public static final Object[] defaultcolumnsListaProductosFaltantes =
    { " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsListaProductosSobrantes = {
        //  new FarmaColumnData("Cod. Barra",70,JLabel.LEFT),
        new FarmaColumnData("C�digo", 50, JLabel.LEFT), new FarmaColumnData("Desc. Producto", 200, JLabel.LEFT),
        new FarmaColumnData("Unidad", 90, JLabel.LEFT), new FarmaColumnData("Laboratorio", 178, JLabel.LEFT),
        new FarmaColumnData("Contada", 62, JLabel.LEFT), //82
        new FarmaColumnData("Entregada", 67, JLabel.LEFT),
        //   new FarmaColumnData("Diferencia",0,JLabel.LEFT),
        new FarmaColumnData("Nro. Entrega", 100, JLabel.LEFT) };

    public static final Object[] defaultcolumnsListaProductosSobrantes =
    { " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsListaProductosTransferencia =
    { new FarmaColumnData("Desc. Producto", 200, JLabel.LEFT), new FarmaColumnData("Unidad", 90, JLabel.LEFT),
      new FarmaColumnData("Cantidad", 82, JLabel.LEFT), new FarmaColumnData("Lote", 100, JLabel.LEFT),
      new FarmaColumnData("Fecha Vcto.", 100, JLabel.LEFT) };

    public static final Object[] defaultcolumnsListaProductosTransferencia =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", "" };

    public static final FarmaColumnData[] columnsListaProductos =
    { new FarmaColumnData("Sel", 31, JLabel.CENTER), new FarmaColumnData("Codigo", 45, JLabel.LEFT),
      new FarmaColumnData("Descripcion", 220, JLabel.LEFT), new FarmaColumnData("Unidad", 105, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 180, JLabel.LEFT) };


    public static final Object[] defaultcolumnsListaProductos =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData columnsListaImpresoras[] =
    { new FarmaColumnData("Sec.", 60, JLabel.LEFT), new FarmaColumnData("Descripcion", 200, JLabel.LEFT),
      new FarmaColumnData("Num. Comp.", 80, JLabel.LEFT), };

    public static final Object[] defaultValuesListaImpresoras = { " ", " ", " " };

    public static final String NOM_HASHTABLE_CMBTIPO_TRANSF = "cmbTipoTransf";
    public static final String NOM_HASHTABLE_CMBTIPO_TRANSF_INTERNO = "cmbTipoTransfInterno";
    public static final String NOM_HASHTABLE_CMBMOTIVO_TRANSF = "cmbMotivoTransf";


    //JMIRANDA

    public static final FarmaColumnData columnsListaProductosConteo[] =
    { new FarmaColumnData("Descripci�n", 170, JLabel.LEFT), new FarmaColumnData("Unidad", 80, JLabel.LEFT),
      new FarmaColumnData("Cantidad", 60, JLabel.RIGHT), new FarmaColumnData("Lote 1", 80, JLabel.LEFT),
      new FarmaColumnData("Venc. 1", 70, JLabel.LEFT), new FarmaColumnData("Lote 2", 80, JLabel.LEFT),
      new FarmaColumnData("Venc. 2", 70, JLabel.LEFT), new FarmaColumnData("secAux", 0, JLabel.RIGHT), //7
        new FarmaColumnData("", 0, JLabel.RIGHT), //codBarra  8
        new FarmaColumnData("", 0, JLabel.RIGHT) //NroBloque  9
        } ;
    public static final Object[] defaultValuesListaProductosConteo =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static final String AuxConteoNumera = "075";

    public static final String EstadoEmitido = "E";
    public static final String EstadoConteo = "C";
    public static final String EstadoEspera = "P";
    public static final String EstadoRevisado = "R";
    public static final String EstadoVerificacion = "V"; //VERIFICACION DE CONTEO
    public static final String EstadoAfectadoTotal = "T";
    public static final String EstadoAfectadoParcial = "L"; // AUN NO SE UTILIZA ESTE ESTADO

    //JMIRANDA 17.03.2010 COLUMNAS LISTA TRANSPORTISTA
    // AAMPUERO 15.04.2014
    public static final FarmaColumnData columnsListaTransp[] =
    { new FarmaColumnData("N� Ingreso", 85, JLabel.CENTER), //0
        new FarmaColumnData("Fecha Ingreso", 125, JLabel.CENTER), //1
        new FarmaColumnData("Usuario", 100, JLabel.CENTER), //2
        new FarmaColumnData("Transportista", 160, JLabel.LEFT), //3
        new FarmaColumnData("Placa", 65, JLabel.LEFT), //4
        new FarmaColumnData("Estado", 83, JLabel.LEFT), //5
        new FarmaColumnData("Cant.Gu�as", 68, JLabel.CENTER), //6
        new FarmaColumnData("# Band.Dev", 79, JLabel.CENTER), //7
        new FarmaColumnData("cod_est", 0, JLabel.RIGHT), //cod_est 8
        new FarmaColumnData("ordena", 0, JLabel.RIGHT), //ordena 9
        new FarmaColumnData("hojaresumen", 0, JLabel.RIGHT) //ordena 10
        } ;
    public static final Object[] defaultValuesListaTrans = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," "};

    public static boolean error_du = false;

    public static final FarmaColumnData[] columnsListaActaRecepcion =
    { new FarmaColumnData("C�digo", 60, JLabel.CENTER), new FarmaColumnData("Descripcion", 250, JLabel.LEFT),
      new FarmaColumnData("Presentaci�n.", 100, JLabel.LEFT), new FarmaColumnData("Cantidad", 60, JLabel.CENTER),
      new FarmaColumnData("Nro Lote", 80, JLabel.CENTER), new FarmaColumnData("Fecha Vcto", 80, JLabel.CENTER),
      new FarmaColumnData("Embalaje", 80, JLabel.CENTER), new FarmaColumnData("Envase Mediato", 80, JLabel.CENTER),
      new FarmaColumnData("Envase Inmediato", 80, JLabel.CENTER), new FarmaColumnData("R�tulo", 80, JLabel.CENTER),
      new FarmaColumnData("Contenido", 80, JLabel.CENTER), new FarmaColumnData("R.S.", 80, JLabel.CENTER) };

    public static final Object[] defaultListaActaRecepcion =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData columnsListaRptBandeja[] =
    { 
      new FarmaColumnData("N� Bandeja", 100, JLabel.CENTER), 
      new FarmaColumnData("Fecha Recepci�n", 123, JLabel.CENTER),
      new FarmaColumnData("Estado", 210, JLabel.CENTER),
      new FarmaColumnData("", 0, JLabel.CENTER), 
      new FarmaColumnData("", 0, JLabel.LEFT),
    new FarmaColumnData("", 0, JLabel.LEFT)
        }; 

    public static final Object[] defaultValuesListaRptBandeja= { " ", " ", " ", " ", " " ," "};
    
    public static final FarmaColumnData columnsListaRptBandejas[] =
    { 
      new FarmaColumnData("N� Bandeja", 100, JLabel.CENTER), 
      new FarmaColumnData("Fecha Recepci�n", 123, JLabel.CENTER)
        }; 

    public static final Object[] defaultValuesListaRptBandejas= { " ", " ", " ", " "};
    
    //INI ASOSA - 04/06/2015 - RCIEGAM
    public static final int COD_ACTIVAR_NEW_RECEP_NEW_01 = 508;
    public static final int COD_ALLOW_BULTO_NOTEXISTS = 503;
    public static final int COD_RUTA_PLAY_AUDIO = 509;
    public static final int COD_RUTA_SAVE_AUDIO = 510;
    
    public static final String ARCHIVO_ATENDIDO = "atendido.wav"; //atendido
    public static final String ARCHIVO_ERROR = "error.wav"; //error
    
    public static final FarmaColumnData columnsListaProductosConteo02[] =
    { new FarmaColumnData("Descripci�n", 150, JLabel.LEFT), 
      new FarmaColumnData("Unidad", 70, JLabel.LEFT),
      new FarmaColumnData("Cant.", 40, JLabel.RIGHT), 
      new FarmaColumnData("Lote 1", 70, JLabel.LEFT),
      new FarmaColumnData("Venc. 1", 70, JLabel.LEFT), 
      new FarmaColumnData("Lote 2", 70, JLabel.LEFT),
      new FarmaColumnData("Venc. 2", 70, JLabel.LEFT), 
      new FarmaColumnData("secAux", 0, JLabel.RIGHT), //7
        new FarmaColumnData("", 0, JLabel.RIGHT), //codBarra  8
        new FarmaColumnData("", 0, JLabel.RIGHT), //NroBloque  9        
        new FarmaColumnData("Corr.Bulto", 80, JLabel.LEFT) //NroBloque  9        
        } ;
    public static final Object[] defaultValuesListaProductosConteo02 =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
    
    //FIN ASOSA - 10/06/2015 - RCIEGAM
    
    
    
    public static final FarmaColumnData columnsListaMotivoNoDevolucion[] = { 
        new FarmaColumnData("Motivo", 350, JLabel.LEFT) ,
    new FarmaColumnData("CodMotivo", 0, JLabel.LEFT) 
    
    };

    public static final Object[] defaultValuesListaMotivoNoDevolucion = { " "," " };
    
    public static final FarmaColumnData columnsListaIngresoBandeja[] = { 
        new FarmaColumnData("Bandeja", 250, JLabel.LEFT) ,
        new FarmaColumnData("Fecha Recepci�n", 0, JLabel.LEFT) ,
        new FarmaColumnData("cia", 0, JLabel.LEFT) ,
        new FarmaColumnData("local", 0, JLabel.LEFT) ,
        new FarmaColumnData("recep", 0, JLabel.LEFT) ,
        new FarmaColumnData("hoja", 0, JLabel.LEFT) ,
        new FarmaColumnData("indlazer", 0, JLabel.LEFT) 
    };

    public static final Object[] defaultValuesListaIngresoBandeja = { " "," "," "," "," "," " ," "};   
    
    
    public static final FarmaColumnData columnsListaDifBandeja[] = { 
        new FarmaColumnData("Bandeja", 100, JLabel.CENTER) ,
        new FarmaColumnData(" ", 90, JLabel.CENTER) ,
        new FarmaColumnData(" ", 90, JLabel.CENTER) ,
        new FarmaColumnData(" ", 90, JLabel.CENTER) ,
        new FarmaColumnData(" ", 90, JLabel.CENTER) 
    
    };

    public static final Object[] defaultValuesListaDifBandeja = { " "," "," "," "," " };  

    
    public static final FarmaColumnData columnsListaPorDevolver[] = { 
        new FarmaColumnData("Bandeja", 110, JLabel.CENTER) ,
        new FarmaColumnData("Fecha Recepci�n", 130, JLabel.CENTER) ,
        new FarmaColumnData(" ", 0, JLabel.CENTER) ,
        new FarmaColumnData(" ", 0, JLabel.CENTER) ,
        new FarmaColumnData(" ", 0, JLabel.CENTER) ,
        new FarmaColumnData(" ", 0, JLabel.CENTER) 
    };

    public static final Object[] defaultValuesListaPorDevolver = { " "," "," "," "," "," " };  

    //ASOSA - 21/07/2014
    public static final FarmaColumnData columnsListaBandejas[] = { 
    new FarmaColumnData("Bandejas", 110, JLabel.CENTER) ,
    new FarmaColumnData("", 110, JLabel.CENTER) ,
    new FarmaColumnData("", 110, JLabel.CENTER)
    };

    public static final Object[] defaultValuesListaBandejas = { " "," ",""};
    
    public static final FarmaColumnData columnsListaHoja[] =
    { new FarmaColumnData("Bandejas", 150, JLabel.LEFT), new FarmaColumnData("Bandejas", 150, JLabel.LEFT) };

    public static final Object[] defaultValuesListaHoja = { " ", " " };

    public static final String NAME_TABLA_BANDEJAS = "tblBandejas";
    
    public static final FarmaColumnData columnsListaBandeja[] = { 
    new FarmaColumnData("Bandejas", 150, JLabel.LEFT)
    };

    public static final Object[] defaultValuesListaBandeja = { " "};
    
}
