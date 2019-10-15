package mifarma.ptoventa.recaudacion.reference;


import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;


/**
 * Copyright (c) 2013 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : ConstantsCajaElectronica.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * ERIOS      25.03.2013   Creaci�n<br>
 * AOVIEDO    07.04.2017   Modificaci�n <br>
 * <br>
 * @author <br>
 * @version 1.0<br>
 *
 */

public class ConstantsRecaudacion {

    public static final String NOM_HASTABLE_CMB_TIPO_PAGO = "NOM_HASTABLE_CMB_TIPO_PAGO";

    public static final String ESTADO_PENDIENTE = "P";
    public static final String ESTADO_ANULADO = "N";
    public static final String ESTADO_COBRADO = "C";

    public static final String LISTAR_TODO = "-";
    public static final String MONTO_VACIO = "";

    public static final String TIP_IMPRE = "IMP";
    public static final String TIP_REIMPRE = "REIMP";

    public static final String DESC_TARJ_CITI = "CITIBANK";
    public static final String DESC_TARJ_CMR = "CMR";

    /************** ESTADOS TIMER PARA EL SIX **************/
    public static final String ESTADO_INICIO_TAREA = "I";
    public static final String ESTADO_RESPUESTA_DISPONIBLE = "D";
    public static final String ESTADO_FIN_TAREA = "T";

    /************** PROCESO CON EL SIX **************/
    public static final String CANT_INTENTOS_CONSULTA_SIX = "15";
    public static final String ESTADO_SIX_PENDIENTE = "1";
    public static final String ESTADO_SIX_PROCESO = "2";
    public static final String ESTADO_SIX_TERMINADO = "3";
    public static String vCOD_ESTADO_TRSSC_RECAU = "";

    /************** CODIGO DE MENSAJES CON EL SIX **************/
    public static final String MSJ_SIX_PETICION_TRSSC_200 = "200";
    public static final String MSJ_SIX_ANULACION_TRSSC_400 = "400";
    public static final String MSJ_SIX_ANULACION_TRSSC_420 = "420";
    
    /************** CODIGO DE MOTIVOS EXTORNOS **************/
    public static final String COD_EXT_ANULACION_PAGO = "17";
    public static final String COD_EXT_TIME_OUT = "91";
    public static final String COD_EXT_ERROR_HARDWARE  = "22";
    public static final String COD_EXT_OTROS_MOTIVOS  = "S5";
    
    /************** TIPOS DE TRANSACCIONES **************/
    public static String TRNS_CNSULTA_SRV = "35"; // consulta SIX
    public static String TRNS_PAG_PRE_AUTORI_SRV = "94"; // pago de servicio SIX
    public static String TRNS_RECARGA = "97"; // Recarga
    public static String TRNS_PAG_TARJ = "17"; // tarjeta CMR
    public static String TRNS_ANU_PAG_TARJ = "47"; // anular TIPO_RECAUD_CMR �FINANCIERO?
    public static String TRNS_ANU_PAG_SRV = "96"; // anular Pago Serv CLARO
    public static String TRNS_CMPRA_VNTA = "00"; // venta CMR
    public static String TRNS_ANU_CMR = "02"; // anular TIPO_RECAUD_VENTA_CMR
    //public static String TRNS_ANU_RIPLEY         = "47";// anular pago Ripley

    /************** TIPOS DE RECAUDACIONES **************/
    public static final String TIPO_REC_CMR = "01";
    public static final String TIPO_REC_CITI = "02";
    public static final String TIPO_REC_CLARO = "03";
    public static final String TIPO_REC_PRES_CITI = "04";
    public static final String TIPO_REC_MOVISTAR = "05";
    public static final String TIPO_REC_VENTA_CMR = "06";
    public static final String TIPO_REC_RIPLEY = "07";
    public static final String TIPO_REC_TELETON = "21"; //INI ASOSA - 08/08/2016 - TELETON
    public static final String TIPO_REC_CRUZ_ROJA = "23";   //ASOSA - 04/09/2017 - TELEROJ
    public static final String TIPO_REC_INCASUR = "20";//INI ASOSA - 17/05/2016 - INCASUR
    public static final String TIPO_REC_FINANCIERO = "22"; //BANCO PICHINCHA, despues de 20/08/2018
    ////A
    //INI ASOSA - 05/08/2015 - RAIZ
    public static final String TIPO_REC_RAIZ = "19";
    public static final int ID_IND_DNI_VISIBLE_RAIZ = 539;
    public static final int ID_MONT_MAX_RECAU_RAIZ = 540;
    //FIN ASOSA - 11/08/2015 - RAIZ
    
    //INI ASOSA - 08/08/2016 - TELETON
    public static final int ID_MIN_REC_TELETON = 666;
    public static final int ID_MAX_REC_TELETON = 667; 
    public static final int ID_IND_TELETON = 669;
    public static final Long COD_TRSSC_SIN_RAC = 1234L;
    public static final String PALABRA_CLAVE_TELETON = "TELETON";
    public static final int ID_IND_PALABRA_CLAVE_TELETON = 757; //AOVIEDO 07/04/17
    //FIN ASOSA - 08/08/2016 - TELETON
    
    //INI ASOSA - 08/08/2016 - TELEROJA
    public static final int ID_MIN_REC_CRUZ_ROJA = 824;
    public static final int ID_MAX_REC_CRUZ_ROJA = 825;
    public static final int ID_IND_CRUZ_ROJA = 821;
    public static final int ID_IND_PALABRA_CLAVE_CRUZ_ROJA = 820;
    //FIN ASOSA - 08/08/2016 - TELEROJA
    
    //FIN ASOSA - 17/05/2016 - INCASUR
    public static final String RCD_CMR = "CMR";
    public static final String RCD_CITI = "CITIBANK TARJETAS";
    public static final String RCD_CLARO = "CLARO";
    public static final String RCD_PRES_CITI = "CITIBANK PRESTAMOS";
    public static final String RCD_MOVISTAR = "MOVISTAR";


    /************** MENSAJES DE TRANSSACIONES DE PAGO CON EL SIX **************/
    public static final String RCD_PAGO_SIX_MSJ_COBRO_FALLIDO =
        "No se pudo realizar el pago, intente nuevamente."; //"Se continua con el proceso de cobro.\n Verifique el estado de la operaci�n.";
    public static final String RCD_PAGO_SIX_MSJ_COBRO_CMR_RECARGA =
        "No se pudo realizar el pago de la recarga, intente nuevamente.";
    public static final String RCD_PAGO_SIX_MSJ_COBRO_CMR_VENTA = "No se pudo realizar el pago con la tarjeta.";
    public static final String RCD_PAGO_SIX_MSJ_COBRO_EXITO = "La Recaudacion se realizo satisfactoriamente.";
    public static final String RCD_MSJ_CLARO_SERV_INACTIVO = "El servicio para transacciones Claro no est� activo.";
    public static final String RCD_MSJ_RECIBOS_CLARO_NO_HAY_PENDIENTES =
        "El n�mero de telefono no existe o no hay recibos pendientes.";
    public static final String RCD_MSJ_RECARGA_MOVISTAR_SERV_INACTIVO =
        "El servicio para transacciones Movistar no est� activo.";
    public static final String RCD_MSJ_CMR_SERV_INACTIVO = "El servicio para transacciones CMR no esta activo.";
    public static final String RCD_MSJ_RIPLEY_SERV_INACTIVO = "El servicio para transacciones Ripley no esta activo.";
    public static final String RCD_MSJ_NO_RESPUESTA = "No se pudo obtener respuesta del proveedor.";
    public static final String RCD_MSJ_CMR_NRO_DOC_ERRADO = "El n�mero de documento es incorrecto.";

    /************** MENSAJES DE LOG DE TRANSSACIONES DE PAGO CON EL SIX **************/
    public static final String RCD_PAGO_SIX_RIPLEY = "PAGO TARJETA RIPLEY ";
    public static final String RCD_PAGO_SIX_CMR = "PAGO TARJETA CMR ";
    public static final String RCD_ANU_PAGO_SIX_CMR = "ANULACION DE PAGO TARJETA CMR ";
    public static final String RCD_ANU_PAGO_SIX_FINANCIERO = "ANULACION DE PAGO TARJETA FINANCIERO ";
    public static final String RCD_ANU_PAGO_SIX_RIPLEY = "ANULACION DE PAGO TARJETA RIPLEY ";
    public static final String RCD_PAGO_SIX_VENTA_CMR = "PAGO TARJETA CMR ";
    public static final String RCD_PAGO_SIX_CLARO = "PAGO SERVICIO CLARO ";
    public static final String RCD_ANU_PAGO_SIX_CLARO = "ANULACION DE PAGO SERVICIO CLARO ";
    public static final String RCD_CONSULTA_PAGO_SIX_CLARO = "CONSULTA SERVICIO CLARO ";
    public static final String RCD_PAGO_SIX_RECARGA_VIRTUAL_CLARO = "RECARGA VIRTUAL CLARO ";
    public static final String RCD_PAGO_SIX_RECARGA_VIRTUAL_MOVISTAR = "RECARGA VIRTUAL MOVISTAR ";
    public static final String RCD_PAGO_SIX_NO_HAY_RESP = "No se encontro respuesta del proveedor.";
    public static final String RCD_PAGO_SIX_SI_HAY_RESP = "Se encontro respuesta del proveedor.";
    public static final String RCD_SIX_RESP_OK = "Se realizo con exito - codigo de respuesta ";
    public static final String RCD_SIX_RESP_ERROR = "No se realizo con exito - codigo de respuesta ";
    public static final String RCD_PAGO_SIX_CONCAT = " -- ";

    /************** ESTADOS DE TRANSACCION CON EL SIX **************/
    public static final String RCD_PAGO_SIX_EST_TRSSC_CORRECTA = "OK";
    public static final String RCD_PAGO_SIX_EST_TRSSC_FALLIDA = "FA";

    /************** MODO DE TRANSSACIONES CON EL SIX **************/
    public static final String RCD_MODO_CONSULTA_SIX = "C";
    public static final String RCD_MODO_PAGO_SIX = "P";
    public static final String RCD_MODO_RECARGA_SIX = "R";

    /************** RESPUESTA DE TRANSSACIONES CON EL SIX **************/
    public static final int RCD_PAGO_RESPUESTA = 0;
    public static final int RCD_PAGO_MSJ = 1;
    public static final int RCD_PAGO_RESPONSE_CODE = 2;
    public static final int RCD_PAGO_MONTO_PAGAR = 3;
    public static final int RCD_PAGO_COD_AUDITORIA = 4;
    public static final int RCD_PAGO_COD_AUTORIZ = 5;
    public static final int RCD_PAGO_FECHA_VENC_CUOTA = 6;
    public static final int RCD_PAGO_NUM_RECIBO = 8;
    public static final int RCD_PAGO_FECHA_ORIG = 11;


    /************** TIPOS DE TRSSC DE RECAUDACION PARA CONCILIACION  **************/
    public static final String RCD_MODO_PAGO = "8";
    public static final String RCD_MODO_VENTA = "1";
    public static final String RCD_MODO_ANULACION = "9";

    /************** CODIGOS DE ALIANZA DE RECAUDACION PARA CONCILIACION  **************/
    //Codigo de Alianza Ripley 12, 16 Citibank, Ban Finan 17 , Cmr 7 , Claro 9
    public static final String RCD_COD_ALIANZA_RIPLEY = "12";
    public static final String RCD_COD_ALIANZA_CITIBANK = "16";
    public static final String RCD_COD_ALIANZA_BANFINAN = "17";
    public static final String RCD_COD_ALIANZA_CMR = "7";
    public static final String RCD_COD_ALIANZA_CLARO = "9";

    /************** CODIGOS DE SERVICIO DE RECAUDACION PARA CONCILIACION  **************/
    //solo en Recaudacion Pago 02 EstaCta Citibank, 04 Ripley, 07 CMR, 14 Financiero, 15 Claro, 18 Prest Terc. Citibank
    public static final String RCD_COD_SERV_PAGO_CITIBANK = "02";
    public static final String RCD_COD_SERV_PREST_CITIBANK = "18";
    public static final String RCD_COD_SERV_PAGO_CLARO = "15";
    public static final String RCD_COD_SERV_PAGO_CMR = "07";
    public static final String RCD_COD_SERV_PAGO_RIPLEY = "04";

    /************** TIPOS DE MONEDA DE RECAUDACION PARA CONCILIACION  **************/
    public static final String RCD_COD_MONEDA_SOLES = "001";
    public static final String RCD_COD_MONEDA_DOLARES = "002";
    public static final String RCD_COD_MONEDA_VENTA_CMR = "7";


    /************** CODIGOS DE RESPUESTA **************/
    public static String COD_SOLICITUD_EXITOSA = "00";
    public static String COD_ERR_TRSS_EXTORNADA_DC = "37";
    public static String COD_ERR_TRSS_EXTORNADA_TC = "06";
    public static String COD_NO_RECIB_PEND_CLARO = "21";
    public static String COD_SERV_INACTIVO = "96";
    public static String COD_NO_RESPUESTA = "";
    public static String COD_NRO_DOC_ERRADO = "50";

    /************** IMPRESION VENTA CMR **************/
    public static String COD_COMPROBANTE_ORIGINAL = "O";
    public static String COD_COMPROBANTE_COPIA = "C";

    public static String FECHA_RCD = "";
    public static String HORA_RCD = "";

    /************** CODIGOS DE CONCILIACION DE RECARGAS **************/
    public static String COD_CONCENTRADOR_CLARO = "052";
    public static String COD_CONCENTRADOR_MOVISTAR = "055";

    public static String NOM_HASTABLE_CMB_CUOTAS = "CMB_CUOTAS";

    public ConstantsRecaudacion() {
    }

    public static final FarmaColumnData[] columnsListaRecaudacion =
    { new FarmaColumnData("Fec.Crea.", 80, JLabel.LEFT), new FarmaColumnData("Hora.Crea", 80, JLabel.LEFT),
      new FarmaColumnData("Nombre", 120, JLabel.LEFT), new FarmaColumnData("Codigo", 65, JLabel.LEFT),
      new FarmaColumnData("Monto Total", 80, JLabel.LEFT) };

    public static final Object[] defaultListaRecaudacion = { " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsCabeceraPedidoRecaudacion =
    { new FarmaColumnData("Operacion", 70, JLabel.CENTER),                          //00
      new FarmaColumnData("Fecha", 75, JLabel.CENTER),                              //01
      new FarmaColumnData("Entidad", 130, JLabel.LEFT),                             //02
      new FarmaColumnData("Nro Tarjeta", 135, JLabel.CENTER),                       //03
      new FarmaColumnData("Numero/Boleta", 90, JLabel.CENTER),                      //04
      new FarmaColumnData("Cliente", 0, JLabel.LEFT),//NO MOSTRAR                 //05
      new FarmaColumnData("M.", 17, JLabel.CENTER), //6                             //06
        new FarmaColumnData("Total", 74, JLabel.RIGHT), //7                         //07
        new FarmaColumnData("Cajero", 0, JLabel.LEFT), // NO MOSTRAR              //08
        new FarmaColumnData("Cod_Estado", 0, JLabel.LEFT), // NO MOSTRAR          //09
        new FarmaColumnData("Six", 0, JLabel.RIGHT), // NO MOSTRAR                //10
        new FarmaColumnData("Cod_Est.Trans.", 0, JLabel.LEFT), // NO MOSTRAR      //11
        new FarmaColumnData("Estado", 75, JLabel.CENTER),                           //12
      new FarmaColumnData("Est.Trans.", 85, JLabel.CENTER),                         //13
        new FarmaColumnData("Id_Anular.", 0, JLabel.LEFT), // NO MOSTRAR          //14
        new FarmaColumnData("Tip_Recau.", 0, JLabel.LEFT), // NO MOSTRAR          //15
        new FarmaColumnData("Cod_Moneda.", 0, JLabel.LEFT), // NO MOSTRAR         //16
        new FarmaColumnData("Fecha Origen", 0, JLabel.LEFT), // NO MOSTRAR         //17
        new FarmaColumnData("Nro Tarjeta", 0, JLabel.LEFT) // NO MOSTRAR         //18
        } ;

    public static final Object[] defaultCabeceraPedidoRecaudacion =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
    
    //INI ASOSA - 20/05/2016 - INCASUR
    public static final FarmaColumnData[] columnsListaRecibosPendientes =
    { new FarmaColumnData("Nro. Recibo", 80, JLabel.CENTER), 
      new FarmaColumnData("Fecha Venc.", 80, JLabel.CENTER),
        new FarmaColumnData("Monto", 120, JLabel.RIGHT)
        } ;

    public static final Object[] defaultListaRecibosPendientes =
    { " ", " ", " "};
    
    public static final String COD_CLIENTE_INCASUR = "1";
    public static final String NRO_RECIBO_INCASUR = "2";
    
    //FIN ASOSA - 20/05/2016 - INCASUR


    //Lista opcion de busqueda de recaudacion prestamos Citibank , GFS 17.06.2013
    public static final String HASHTABLE_OPCION_COD_BUSQUEDA = "OPCIONCODBUS";
    public static final String[] OP_COD = { "2", "1" };
    public static final String[] OP_DESC = { "NRO. RECIBO", "COD. CLIENTE" };

    //Tipo de transaccion recaudacion prestamos Citibank , GFS 17.06.2013
    public static final String TIPO_TRANSACCION_PAGO = "P";
    public static final String TIPO_TRANSACCION_EXTORNO = "X";

    /************** DATOS DE COMBO DE MONEDA PRESTAMOS CITIBANK **************/
    public static final String FORMA_PAGO_PRESCITI_EFECTIVO_SOLES = "001";
    public static final String FORMA_PAGO_PRESCITI_EFECTIVO_DOLARES = "002";

    public static final String HASHTABLE_OPCION_MONEDA = "OPCION_MONEDA";

    public static final String[] MONEDA_COD =
    { FORMA_PAGO_PRESCITI_EFECTIVO_SOLES, FORMA_PAGO_PRESCITI_EFECTIVO_DOLARES };
    public static final String[] MONEDA_DESC = { "Soles", "Dolares" };

    public static final String SIMBOLO_SOLES = ConstantesUtil.simboloSoles+" ";
    public static final String SIMBOLO_DOLARES = "$ ";

    public static final String EST_CTA_SOLES = "S";
    public static final String EST_CTA_DOLARES = "D";

    /*********** LISTADO CONSOLIDADO RECAUDACIONES CIERRE CAJA TURNO ***********/
    public static final FarmaColumnData[] columnsCabeceraConsolidadoRecaudacion =
    { new FarmaColumnData("Descripcion", 150, JLabel.LEFT), new FarmaColumnData("Moneda Pago", 85, JLabel.LEFT),
      new FarmaColumnData("Cant.", 40, JLabel.RIGHT), new FarmaColumnData("Total("+ConstantesUtil.simboloSoles+" )", 70, JLabel.RIGHT) };

    public static final Object[] defaultCabeceraConsolidadoRecaudacion = { " ", " ", " ", " " };

    /******************** COMPANIA ************************************/
    public static final String CODGRUPOCIA_MIFARMA = "001";
    public static final String CODGRUPOCIA_FASA = "002";
    public static final String CODGRUPOCIA_BTL = "003";
    public static final String CODGRUPOCIA_BTL_AMAZONIA = "004";    //ASOSA - 20/07/2015 - IGVAZNIA

    /******************** INDICADOR PROCESO DE RECAUDACION ************************************/
    public static final String RCD_IND_PROCESO_PAGO = "1";
    public static final String RCD_IND_PROCESO_ANULACION = "2";
    public static final String RCD_IND_PROCESO_CONSU_CLARO = "3";

    /************ TIPO COBRO *******************************/
    public static final String TIPO_COBRO_VENTA_CMR = "VCMR";
    public static final String TIPO_COBRO_RECAUDACION = "RCD";

    /************ FORMAS DE PAGO *******************************/
    public static final String FORMA_PAGO_CMR = "00085";
    public static final String FORMA_PAGO_CITIBANK = "00082";
    public static final String FORMA_PAGO_RIPLEY = "00086";

    /**************ID DE RECARGAS**************/
    public static String ID_RECARGAS = "5960";
    
    //INI AOVIEDO 31/07/2017
    public static final FarmaColumnData columnsListaTarjetas[] =
    { new FarmaColumnData("Nro. Tarjeta", 140, JLabel.CENTER),  //00
      new FarmaColumnData("Moneda", 42, JLabel.LEFT),           //01
        new FarmaColumnData("P. M�nimo", 70, JLabel.RIGHT),  //02
        new FarmaColumnData("P. Mes", 70, JLabel.RIGHT),     //03
        new FarmaColumnData("P. Total", 70, JLabel.RIGHT),   //04
      new FarmaColumnData("Moneda", 42, JLabel.LEFT),           //05
        new FarmaColumnData("P. Min", 70, JLabel.RIGHT),      //06
        new FarmaColumnData("P. Mes", 70, JLabel.RIGHT),      //07
        new FarmaColumnData("P. Total", 70, JLabel.RIGHT)     //08
    };
    
    public static final Object[] defaultValuesListaTarjetas =
    { " ", " ", " ", " ", " ", " ", " ", " ", " " };
    
    //INI AOVIEDO 31/07/2017
    public static final FarmaColumnData columnsListaBFP_Tarjeta[] =
    { new FarmaColumnData("Nro. Tarjeta", 140, JLabel.CENTER),  //00
      new FarmaColumnData("Moneda", 0, JLabel.LEFT),           //01
        new FarmaColumnData("P. M�nimo", 0, JLabel.RIGHT),  //02
        new FarmaColumnData("P. Mes", 0, JLabel.RIGHT),     //03
        new FarmaColumnData("P. Total", 0, JLabel.RIGHT),   //04
      new FarmaColumnData("Moneda", 0, JLabel.LEFT),           //05
        new FarmaColumnData("Pago Min", 95, JLabel.RIGHT),      //06
        new FarmaColumnData("Pago Mes", 95, JLabel.RIGHT),      //07
        new FarmaColumnData("Pago Total", 95, JLabel.RIGHT),     //08
        new FarmaColumnData("Tipo Cambio", 0, JLabel.RIGHT),     //09
      new FarmaColumnData("Otro Monto", 95, JLabel.RIGHT)     //10
    };
    
    public static final Object[] defaultValuesListaBFP_Tarjeta =
    { " "," "," "," "," "," "," "," "," "," "," "};
    // 00, 01, 02, 03, 04, 05, 06, 07, 08, 09, 10
    public static final String FORMA_PAGO_FINANCIERO = "00095";
    public static final String RCD_IND_PROCESO_CONSU_FINANCIERO = "4";
    public static final String RCD_CONSULTA_PAGO_SIX_FINANCIERO = "CONSULTA SERVICIO FINANCIERO ";
    public static final String RCD_PAGO_SIX_FINANCIERO = "PAGO TARJETA FINANCIERO ";
    public static String TRNS_PAGO_SRV_FINANCIERO = "17"; // consulta SIX Financiero
    public static final String RCD_CONSULTA_SIX_MSJ_CONSULTA_FALLIDO = "No se pudo realizar la consulta, intente nuevamente.";
    //FIN AOVIEDO 31/07/2017
    
    public static final FarmaColumnData columnsPrueba[] =
    { new FarmaColumnData("Primero", 100, JLabel.CENTER),  //00
      new FarmaColumnData("Segundo", 100, JLabel.LEFT),           //01
      new FarmaColumnData("Tercero", 100, JLabel.RIGHT),  //02
      new FarmaColumnData("Cuarto", 100, JLabel.RIGHT)     //03
    };
    
    public static final Object[] defaultDatePrueba =
    { " "," "," "," "};
    // 00, 01, 02, 03
    
    public static final FarmaColumnData columnsListaBFP_Prestamo[] =
    { new FarmaColumnData("Tipo Prestamo", 140, JLabel.CENTER),  //00
      new FarmaColumnData("Desc Tipo Prest.", 140, JLabel.CENTER),  //01
      new FarmaColumnData("Nro Pretamo", 0, JLabel.LEFT),           //02
        new FarmaColumnData("Fecha Pago", 0, JLabel.RIGHT),  //03
        new FarmaColumnData("Descripcion", 0, JLabel.RIGHT),     //04
        new FarmaColumnData("Moneda", 0, JLabel.RIGHT),   //05
      new FarmaColumnData("Importe Pago", 0, JLabel.LEFT),           //06
        new FarmaColumnData("Moneda Orig", 95, JLabel.RIGHT),      //07
        new FarmaColumnData("Importe Orir", 95, JLabel.RIGHT),      //08
        new FarmaColumnData("Tipo Cambio", 95, JLabel.RIGHT),     //09
        new FarmaColumnData("Otro Monto", 95, JLabel.RIGHT)     //10
    };
    
    public static final Object[] defaultValuesListaBFP_Prestamo =
    { " "," "," "," "," "," "," "," "," "," "," "};
    //00, 01, 02, 03, 04, 05, 06, 07, 08, 09, 10
    
    public static final FarmaColumnData columnsListaBFP_Deposito[] =
    { new FarmaColumnData("Nro. Cuenta", 140, JLabel.CENTER),  //00
      new FarmaColumnData("Descripcion", 100, JLabel.LEFT),           //01
        new FarmaColumnData("Moneda", 100, JLabel.RIGHT),  //02
        new FarmaColumnData("Saldo", 100, JLabel.RIGHT),     //03
        new FarmaColumnData("Moneda Orgi", 100, JLabel.RIGHT),   //04
        new FarmaColumnData("Saldo Orgi", 100, JLabel.RIGHT),   //05
        new FarmaColumnData("Tipo Cambio", 100, JLabel.RIGHT),   //06
        new FarmaColumnData("Otro monto", 100, JLabel.RIGHT)   //07
    };
    
    public static final Object[] defaultValuesListaBFP_Deposito =
    { " "," "," "," "," "," "," "," "};
    // 00, 01, 02, 03, 04, 05, 06, 07
}
