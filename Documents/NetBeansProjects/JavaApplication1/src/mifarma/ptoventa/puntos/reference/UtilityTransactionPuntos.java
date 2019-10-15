package mifarma.ptoventa.puntos.reference;

import farmapuntos.bean.BeanOperacion;
import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityTransactionPuntos {

    private final Logger log = LoggerFactory.getLogger(UtilityTransactionPuntos.class);

    public String vNumPedVta = "";
    public String vTarjTransaccOrbis = "";
    public String vDniPedido = "";
    public String vTarjetaPedido = "";

    boolean vIndAsocProdMasUNO = false;
    // SE VERIFICA SI PROCESO EN ORBIS - EL PEDIDO ORIGINAL QUE SE ANULA
    boolean vIndProcOrbisCobro = false;
    boolean vIndPuntosAcum = false;
    boolean vIndRedimio = false;
    boolean vAnulaOnline = false;
    boolean vAnulaEnOrbis = false;
    boolean vIndBonificadoPedido = false;
    
    boolean vIndOperoConOrbis = false;
    
    String vIdTrxAnula_Orbis = "";
    String vNroAutoriza_Anula_Orbis = "";
    
    boolean vIndDescartaPedidoOrbis = false;
    boolean vIndDescartaNCnoPedidoOrbis = false;
    
    String RPT_ORBIS_TRJ_INVALIDA = "41";

    public UtilityTransactionPuntos(String pNumPedido) {
        setVNumPedVta(pNumPedido);
    }

    public void reset() {
        vNumPedVta = "";
        vTarjTransaccOrbis = "";
        vDniPedido = "";
        vTarjetaPedido = "";
        vIndAsocProdMasUNO = false;
        vIndProcOrbisCobro = false;
        vIndPuntosAcum = false;
        vIndRedimio = false;
        vAnulaOnline = false;
        vAnulaEnOrbis = false;
        vIndBonificadoPedido = false;
    }

    public void imprimeVariables() {
        log.info("**** IMPRIME VARIABLES UtilityTransactionPuntos *****");
        log.info("vNumPedVta " + vNumPedVta + "");
        log.info("vTarjTransaccOrbis " + vTarjTransaccOrbis + "");
        log.info("vDniPedido " + vDniPedido + "");
        log.info("vTarjetaPedido " + vTarjetaPedido + "");
        log.info("vIndAsocProdMasUNO " + vIndAsocProdMasUNO + "");
        log.info("vIndProcOrbisCobro " + vIndProcOrbisCobro + "");
        log.info("vIndPuntosAcum " + vIndPuntosAcum + "");
        log.info("vIndRedimio " + vIndRedimio + "");
        log.info("vAnulaOnline " + vAnulaOnline + "");
        log.info("vAnulaOnline " + vAnulaEnOrbis + "");
        log.info("vIndBonificadoPedido " + vIndBonificadoPedido + "");
        log.info("vIndOperoConOrbis " + vIndOperoConOrbis + "");
        log.info("**** IMPRIME VARIABLES UtilityTransactionPuntos *****");
    }

    /**
     * Validaciones Previas para ANULAR en ORBIS
     * @return
     * @throws Exception
     */
    public boolean previoAnulaPuntos() throws Exception {
        String vNumPedVta = getVNumPedVta();
        boolean bResultado = false;
        String indAnular = "";
        if (DBPuntos.getIndPedActuaPuntos(vNumPedVta)){//SI ME DA TRUE ES PORQUE EL PEDIDO ESTA "P" O "E", SIGNIFICA QUE TIENE INTERACCION CON ORBIS
            log.info("Pedido " + vNumPedVta + " Si interactua con ORBIS PUNTOS ");
            /*
            T.NUM_PEDIDO,
            C.NUM_TARJ_PUNTOS ,
            T.DNI_CLI,
            T.COD_TARJETA
             */
            
            //INI AOVIEDO 13/09/2017
            //INICIO RPASCACIO 16-05-2017
            if(!VariablesCaja.vDniCli.isEmpty()){
                UtilityCaja.verificaTarjetaPedido(VariablesCaja.vNumPedVta_Anul, VariablesCaja.vDniCli);
            }
            //FIN RPASCACIO 16-05-2017
            //FIN AOVIEDO 13/09/2017
            
            List pListTarjPedido = DBPuntos.getPedAsocTarjeta(vNumPedVta);
            if (pListTarjPedido.size() == 0) { //VALIDA QUE EL PEDIDO NO TIENE ASOCIADA UNA TARJETA MONEDERO
                bResultado = false;
                log.info("Pedido " + vNumPedVta + " No tiene ASOCIADO UNA TARJETA DE OPERACION CON ORBIS - ERROR ");
                throw new Exception(
                        //"Error al validar pedido en puntos\n" +
                        "El pedido no tiene asociado una tarjeta de operación con puntos.\n" +
                        "Por favor llame a mesa de ayuda");
            } else {
                if (pListTarjPedido.size() == 1) {//SE VALIDA QUE EL PEDIDO TIENE ASOCIADA UNA TARJETA MONEDERO
                    log.info("Pedido " + vNumPedVta + " SI tiene ASOCIADO UNA TARJETA ");
                    Map mapFila = new HashMap();
                    mapFila = (HashMap)pListTarjPedido.get(0);
                    // Este campo DNI_CLI puede ser el DNI o Carnet Extranjeria
                    String pTarjetaEnvOrbis = mapFila.get("NUM_TARJ_PUNTOS").toString();
                    String pDocCli = mapFila.get("DNI_CLI").toString();
                    String pTarjetaPedido = mapFila.get("COD_TARJETA").toString();

                    setVTarjTransaccOrbis(pTarjetaEnvOrbis.trim());
                    setVDniPedido(pDocCli.trim());
                    setVTarjetaPedido(pTarjetaPedido.trim());

                    // SE VERIFICA SI PARTICIPA ALGUN PRODUCTO X + 1
                    setVIndAsocProdMasUNO(DBPuntos.getIndPedidoAsocProdMasUno(vNumPedVta));
                    // SE VERIFICA SI PROCESO EN ORBIS - EL PEDIDO ORIGINAL QUE SE ANULA
                    setVIndProcOrbisCobro(DBPuntos.getIndCobroProcesoOrbis(vNumPedVta));//SI ES QUE LE PEDIDO YA SE ENVIO A ORBIS "E"
                    setVIndPuntosAcum(DBPuntos.getIndPuntosAcum(vNumPedVta));// NOS INDICA SI ESTA ACUMULANDO PUNTOS MONEDERO
                    setVIndRedimio(DBPuntos.getIndPedidoRedimio(vNumPedVta));//NOS INDICA SI SE CANJEARON PUNTOS MONEDERO
                    setVIndBonificadoPedido(DBPuntos.isTieneBonificado(vNumPedVta));//NOS INDICA SI EL PEDIDO BONIFICO ALGUN PRODUCTO X+1
                    // SI HAY PRODUCCION ASOCIADO A X+1 ó REMIDIO
                    // SE VALIDARA SI SE DEBE HACER ONLINE O OFFLINE

                    List vDataOpera = DBPuntos.getDataTrnxAnula(getVNumPedVta());
                    Map mapTrnx = new HashMap();
                    mapTrnx = (HashMap)vDataOpera.get(0);
                    String vIdTrx = mapTrnx.get("ID_TRANSACCION").toString();
                    String vNumAut = mapTrnx.get("NUMERO_AUTORIZACION").toString();
                    double vCtPIni = Double.parseDouble(mapTrnx.get("CTD_PUNTO_INI").toString().trim());
                    double vCtPAcum = Double.parseDouble(mapTrnx.get("CTD_PUNTO_ACUM").toString().trim());
                    double vCtPRedi = Double.parseDouble(mapTrnx.get("CTD_PUNTO_REDI").toString().trim());
                    double vCtPTot = Double.parseDouble(mapTrnx.get("CTD_PUNTO_TOT").toString().trim());
                    String vFecPed = mapTrnx.get("FEC_PED_VTA").toString();
                    //LISTA DE LOS PRODUCTOS DEL DETALLE, FORMATO ORBIS
                    ArrayList vListaDet = vListDetPedAnula(getVNumPedVta());
                    log.info("¿se necesita anular el pedido en Orbis ?");

                    ///////////////////////////////////////////////////////////////////////////////////////////////
                    //  VALIDACION DE PEDIDO EN ORBIS
                    //if (isVIndRedimio() || isVIndBonificadoPedido()) {
                    // TODOS LOS PEDIDOS QUE TIENEN TEMAS CON ORBIS VAN VALIDAR PEDIDO
                    
                        log.info("El Pedido " + vNumPedVta + " Tiene Bonificado ó Redimio puntos isVIndRedimio " +
                                 isVIndRedimio() + " isVIndBonificadoPedido " + isVIndBonificadoPedido());
                    BeanTarjeta trnxValidaAnula = null;
                    //////////////////////////////////////////////////////////////////////////////////////////////
                        if (isVIndProcOrbisCobro()) {//VALIDA SI EL PEDIDO ORIGEN SE ENVIO A ORBIS
                            log.info("El Pedido " + vNumPedVta +
                                     " Si se proceso en Orbis por lo tanto VALIDA ANULA EN ORBIS ");
                            
                            if(VariablesPuntos.frmPuntos==null){
                                throw new Exception("No se puede validar la anulación .\n" +
                                        "Porque el servicio de puntos no ha sido iniciado:" +
                                        "\n" +
                                        "Por favor llame a mesa de ayuda");
                            }
                            
                             trnxValidaAnula =
                                VariablesPuntos.frmPuntos.validarAnulacion(
                                                                           getVTarjTransaccOrbis(), 
                                                                           vIdTrx, 
                                                                           vNumAut,
                                                                           vListaDet, 
                                                                           UtilityPuntos.getDNI_USU());//NOS VALIDA SI PODEMOS REALIZAR LAS ANULACIONES QUE YA HAYAN SIDO ENVIADAS A ORBIS
                            
                            if (trnxValidaAnula == null) {
                                //FarmaUtility.showMessage(pDialog, "No se pudo Recuperar puntos por:\n" +
                                //        "El estado de la Respuesta No es Válido.", objeto);
                                throw new Exception("Se presentó un error en servicio de puntos,al validar la anulación.\n" +
                                        "La respuesta de la operación es vacía ó nula." +"\n" +
                                        "Por favor llame a mesa de ayuda");                        
                            } else if (WSClientConstans.EXITO.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                log.info("El Pedido " + vNumPedVta +
                                         " Si se proceso en Orbis por lo tanto VALIDA ANULA EN ORBIS ");
                                setVAnulaEnOrbis(true);//[Desarrollo5 - Alejandro Nuñez]
                            } else {
                                if (WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                    //indLinea = "N";
                                    log.info("El Pedido " + vNumPedVta +
                                             " NO hay conexion con ORBIS se obvia este paso debido a conexion");
                                } else if (WSClientConstans.DEVOLUCION_YA_APLICADA.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                    //indLinea = "N";
                                    log.info("El Pedido " + vNumPedVta +
                                             " ya se ANULO en orbis, por lo tanto todo OK continua con el resto.");
                                } 
                                else if (WSClientConstans.TARJETA_NO_AFILIADA.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                    //SOLO DESCARTA LA NC NO EL PEDIDO ORIGINAL --LATAVARA 22.05.2015
                                    setVIndDescartaNCnoPedidoOrbis(true);
                                    //La tarjeta es INVALIDA 
                                    log.info("La tarjeta esta Invalida del pedido " + vNumPedVta +
                                             " , por lo tanto todo OK continua con el resto. pero se Descarta la NC ");
                                }else if (WSClientConstans.ERR_ANUL_VTA_ORIG_CANJEA_XMAS1.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())){
                                indAnular=DBCaja.isNCAcumula();
                                   
                                     // COMENTADO TODO PARA REHACER
                                     // DUBILLUZ 02.05.2016
                                     
                                      log.info("El Pedido " + vNumPedVta + " ERROR LA VALIDACION NO PERMITE PROCEDER POR ERROR" + trnxValidaAnula.getMensaje());
                                    
                                       //ANULACION PARCIAL SÓLO PARA EL PEDIDO CON PAGO EN EFECTIVO
                                      String indPagoSoloEfectivo =DBCaja.getIndFormaPagoEfectivoPedido(getVNumPedVta());
                                      
                                      if(indPagoSoloEfectivo.equals("S")){

                                          if(indAnular.equals("S")){
                                              setVAnulaEnOrbis(true);
                                              bResultado = true;
                                              //FarmaUtility.showMessage(new JDialog(),"Sólo podras realizar una ANULACION PARCIAL \n de los productos que no participan en Programa X+1.\n" +
                                              FarmaUtility.showMessage(new JDialog(),"Se anulará el pedido de manera local.\n" +
                                                           "El servicio de puntos indica que :\n" +
                                              UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())
                                                                              ,null);
                                          }
                                          else{
                                              VariablesCaja.vCerrarVentanaTotalAnularPedido=true;
                                              
                                              //throw new Exception("Sólo podras realizar una ANULACION PARCIAL \n de los productos que no participan en Programa X+1.\n" +
                                              throw new Exception("Se anulará el pedido de manera local.\n" +                                                                  
                                                              "El servicio de puntos indica que :\n" +
                                              UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())
                                                                                 );
                                             
                                          }  
                                      }else{ //NO PERMITE REALIZAR NC PARCIAL
                                          throw new Exception("No se puede anular el PEDIDO TOTAL. \n"  +
                                                          "El servicio de puntos indica que :\n" +
                                          UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())
                                          );
                                               }                                         
                                 
                                 
                                }else {
                                    log.info("El Pedido " + vNumPedVta + " ERROR LA VALIDACION NO PERMITE PROCEDER: "+trnxValidaAnula.getEstadoOperacion());
                                    log.info("No se puede anular el pedido.\n" +
                                            "Porque el servicio de puntos indica que :" + 
                                            UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())+
                                            "\n" +
                                            "Por favor llame a mesa de ayuda");
                                  
                                    setVAnulaEnOrbis(false);                                 
                                    VariablesCaja.ptoMsj_Orviis="Pto 01";
                                    throw new Exception("No se puede anular el pedido.\n" +
                                            "Porque el servicio de puntos indica que :" +
                                            trnxValidaAnula.getEstadoOperacion() + "-" + 
                                            UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())                                                        
                                                        +
                                            "\n" +
                                            "Por favor llame a mesa de ayuda");                                    
                                }
                                //setVAnulaEnOrbis(false); 
                                // rehacer para anulacion ORBIS con X+1
                                // dubilluz 02.05.2017
                                //setVAnulaEnOrbis(true);
                            }
                            log.info("El Pedido " + vNumPedVta + " Se termino de Validar ");
                        } else { //EL PEDIDO NO SE HA ENVIADO A ORBIS "P"
                            setVAnulaEnOrbis(false);
                            log.info("El Pedido " + vNumPedVta +
                                     " NO SE PROCESO EN ORBIS por lo TANTO NO VALIDA EN ORBIS porque NO SE ENVIO");
                            log.info("NO ES MANDATORIO");
                        }
                    
                    if(!isVIndDescartaPedidoOrbis()){//AQUI SE VALIDA QUE NO SE VA A DESCARTAR LA NOTA DE CREDITO
                        // SI LA TARJETA NO ESTA INACTIVA Y POR LO TANTO NO SE DESCARTA LA ANULACION
                        // DEBE DE ANULAR NORMAL CON TODAS LAS VALIDACIONES
                        ///////////////////////////////////////////////////////////////////////////////////////////////
                        ///////////////////////////////////////////////////////////////////////////////////////////////
                        if (isVIndRedimio() ||
                            isVIndBonificadoPedido()||
                            //LTAVARA 2017.29.03 AGREGO SI ES X+1
                           isVIndAsocProdMasUNO()
                        ) {//SE VALIDA QUE EL PEDIDO HA CANJEADO PUNTOS O QUE ESTE ASOCIADO A X+1
                            //setVAnulaEnOrbis(true);
                            log.info("Pedido " + vNumPedVta + " Se tiene participacion en X + 1 -- Se DEBE ANULAR ");
                            //SI PROCESO EL PEDIDO ORIGINAL EN ORBIS
                            if (isVIndProcOrbisCobro()) {//VALIDA QUE LE PEDIDO SE HAYA ENVIADO A ORBIS "E"
                                if (WSClientConstans.EXITO.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion()) ||
                                    WSClientConstans.DEVOLUCION_YA_APLICADA.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {//VALIDA QUE LA CONSULTA DE ANULACION A ORBIS HAYA CONTESTADO SATISFACTORIAMENTE
                                    log.info("El Pedido " + vNumPedVta +
                                             " Se Cobro de modo ONLINE es decir se proceso en ORBIS");
                                    log.info("El Pedido " + vNumPedVta +
                                             " Esto confirma que el pedido se hizo la validacion , para que valide de modo online");
                                    log.info("El Pedido " + vNumPedVta + " Por lo tanto se debe anular de modo ONLINE");
                                    setVAnulaOnline(true);
                                    bResultado = true;//[Desarrollo5 - Alejandro Nuñez] 20.11.2015
                                } else {//VALIDA QUE LA RESPUESTA DE ORBIS NO ES SATISFACTORIA PARA DEJAR ANULAR
                                    log.info("El Pedido " + vNumPedVta +
                                             " el pedido NO HIZO la validacion y en este es OBLIGATORIO");
                                    if (WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion()))
                                        throw new Exception("No se Puede Anular el Pedido.\n" +
                                                "Porque no hubo conexión al intentar validar la anulación con sistema de puntos." +
                                                "\n" +
                                                "Por favor llame a mesa de ayuda");
                                    else
                                        if(indAnular.equals("S")){
                                            setVAnulaEnOrbis(true);
                                            bResultado = true;
                                        }
                                        else {
                                            VariablesCaja.ptoMsj_Orviis="Pto 02";
                                            throw new Exception("No se puede anular el pedido.\n" +
                                                    "Porque el servicio de puntos indica que :" +
                                                    trnxValidaAnula.getEstadoOperacion() + "-" + 
                                            UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())
                                                                
                                                                +
                                                    "\n" +
                                                    "Por favor llame a mesa de ayuda");                                            
                                        }
                                }
                            } else {//EL PEDIDO ESTA PENDIENTE Y COMO ES BONIFICACION O CANJE NO SE DEBE DEJAR ANULAR
                               
                                if(isVIndAsocProdMasUNO() && 
                                   !isVIndBonificadoPedido()&&
                                   !isVIndRedimio()){//2017.03.29 SI SOLO ACUMULA X+1 Y NO FUE ENVIADA A ORBIS, SE PUEDE ANULAR
                                setVIndDescartaPedidoOrbis(true);
                                setVAnulaEnOrbis(false);
                                bResultado = true;
                                }else{//2017.03.29  BONIFICACION O CANJE NO SE DEBE DEJAR ANULAR
                                        //El pedido ORIGINAL no se envio a ORBIS
                                        //No se envia a ORBIS
                                        setVAnulaOnline(false);
                                        setVAnulaEnOrbis(false);//[Desarrollo5 - Alejandro Nuñez] 20.11.2015
                                }
                         
                            }
                        } else {
                            //VER SI ACUMULO PUNTOS
                            // OFFILNE
                            if (isVIndPuntosAcum() //|| isVIndAsocProdMasUNO())
                                //LTAVARA 2017.03.29 ACUMULA PUNTOS Y PEDIDO COBRADO
                                && isVIndProcOrbisCobro()) {//VALIDA QUE EL PEDIDO ESTE ACUMULANDO PUNTOS Y QUE HAYA SIDO ENVIADO A ORBIS
                                //setVAnulaEnOrbis(true);
                                if (WSClientConstans.EXITO.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion()) ||
                                    WSClientConstans.DEVOLUCION_YA_APLICADA.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                    log.info("El Pedido " + vNumPedVta +
                                             " Se Cobro de modo ONLINE es decir se proceso en ORBIS");
                                    log.info("El Pedido " + vNumPedVta +
                                             " Esto confirma que el pedido se hizo la validacion , para que valide de modo online");
                                    log.info("El Pedido " + vNumPedVta + " Por lo tanto se debe anular de modo ONLINE");
                                    setVAnulaOnline(true);
                                    bResultado = true;
                                } else {//valida que orbis respondio que no se debe anular
                                    log.info("El Pedido " + vNumPedVta +
                                             " el pedido NO HIZO la validacion y en este es OBLIGATORIO");
                                    if (WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())){
                                        log.info("El Pedido " + vNumPedVta +
                                                 " SOLO ACUMULA PUNTOS y NO HAY CONEXION ORBIS en Valida, entonces VALIDA DE MODO OFFLINE");
                                        setVAnulaOnline(false);
                                        //LTAVARA 2017.03.28
                                        bResultado = true;
                                        
                                    } else {
                                        VariablesCaja.ptoMsj_Orviis="Pto 03";
                                        throw new Exception("No se Puede Anular el Pedido.\n" +
                                                "Porque el servicio de puntos indica que :" +
                                                trnxValidaAnula.getEstadoOperacion() + "-" + 
                                    UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())                                                        
                                                            +
                                                "\n" +
                                                "Por favor llame a mesa de ayuda");                                    
                                }
                                }
                                log.info("Pedido " + vNumPedVta + " Solo acumula puntos se anula de modo offline");
                            } else {//VALIDA QUE EL PEDIDO NO PARTICIPE EN MONEDERO PUNTOS O QUE NO HAYA SIDO ENVIADO A ORBIS O AMBOS
                                //DUBILLUZ 04.05.2015
                                setVIndDescartaPedidoOrbis(true);
                                //DUBILLUZ 04.05.2015
                                setVAnulaEnOrbis(false);
                                log.info("Pedido " + vNumPedVta +
                                         " NO PARTICIPO EN ORBIS termina proceso NO ANULA EN NADA");
                        bResultado = true;
                   }
                        }
                        //bResultado = true;
                   } else{//VALIDA QUE SI VA A DESCARTAR LA NOTA DE CREDITO
                        // LA TARJETA ESTA INACTIVA Y POR LO TANTO SE DESCARTA LA ANULACION
                        // DEBE DE ANULAR NORMAL SIN TODAS LAS VALIDACIONES y NO ANULA EN ORBIS
                        setVAnulaEnOrbis(false);
                        setVAnulaOnline(false);
                        bResultado = true;
                    }
                } else {//VALIDA QUE NO TIENE ASOCIADA UNA TARJETA MONEDERO
                    bResultado = false;
                    log.info("El Pedido " + vNumPedVta +
                             " Tiene mas de UNA TARJETA asociada ERROR EN LA GENERACION DE PEDIDO");
                    log.info("NO SE PUEDE ANULAR " + vNumPedVta);
                    throw new Exception("Error al validar Pedido en Puntos\n" +
                            "El pedido tiene asociado mas de una tarjeta de operación con puntos.\n" +
                            "Por favor llame a mesa de ayuda");
                }
            }
        } else {
            log.info("Pedido " + vNumPedVta + " NO interactua con ORBIS PUNTOS ");
            bResultado = true;
            log.info("Pedido " + vNumPedVta + " Se puede anular y NO anula en ORBIS");
        }
        
        
        if (isVAnulaOnline()) 
            log.info("SI DEBE ANULAR ONLINE en orbis");
        else    
            log.info("NO DEBE ANULAR ONLINE en orbis");
        
        imprimeVariables();
        
        return bResultado;
    }

    public boolean anulaOrbis() throws Exception {
        boolean pResultado = false;
        if (getVNumPedVta().trim().length() > 0) {
            if (isVAnulaEnOrbis()) {
                List vDataOpera = DBPuntos.getDataTrnxAnula(getVNumPedVta());
                Map mapFila = new HashMap();
                mapFila = (HashMap)vDataOpera.get(0);
                String vIdTrx = mapFila.get("ID_TRANSACCION").toString();
                String vNumAut = mapFila.get("NUMERO_AUTORIZACION").toString();
                double vCtPIni = Double.parseDouble(mapFila.get("CTD_PUNTO_INI").toString().trim());
                double vCtPAcum = Double.parseDouble(mapFila.get("CTD_PUNTO_ACUM").toString().trim());
                double vCtPRedi = Double.parseDouble(mapFila.get("CTD_PUNTO_REDI").toString().trim());
                double vCtPTot = Double.parseDouble(mapFila.get("CTD_PUNTO_TOT").toString().trim());
                double vNetoPed = Double.parseDouble(mapFila.get("NETO_PED_VTA").toString().trim());
                String vFecPed = mapFila.get("FEC_PED_VTA").toString();
                ArrayList vListaDet = vListDetPedAnula(getVNumPedVta());
                log.info("¿se necesita anular el pedido en Orbis?");

                // SE VERIFICA SI PARTICIPA ALGUN PRODUCTO X + 1
                setVIndAsocProdMasUNO(DBPuntos.getIndPedidoAsocProdMasUno(vNumPedVta));
                // SE VERIFICA SI PROCESO EN ORBIS - EL PEDIDO ORIGINAL QUE SE ANULA
                setVIndProcOrbisCobro(DBPuntos.getIndCobroProcesoOrbis(vNumPedVta));
                setVIndPuntosAcum(DBPuntos.getIndPuntosAcum(vNumPedVta));
                setVIndRedimio(DBPuntos.getIndPedidoRedimio(vNumPedVta));
                setVIndBonificadoPedido(DBPuntos.isTieneBonificado(vNumPedVta));
                // SI HAY PRODUCCION ASOCIADO A X+1 ó REMIDIO
                

                if (isVAnulaOnline()) {
                    log.info("SI ANULA ONLINE en orbis");
                    // // // // // // // // // //
                    // ANULA VENTA EN ORBIS // //
                    // // // // // // // // // //
                    if(VariablesPuntos.frmPuntos==null)
                        throw new Exception("No se puede anular el pedido.\n" +
                                "Porque el servicio de puntos no ha sido iniciado:" +
                                "\n" +
                                "Por favor llame a mesa de ayuda");                    
                    
                    log.info("--------------------------------------------");
                    log.info("getVTarjTransaccOrbis: "+getVTarjTransaccOrbis());
                    log.info("vListaDet: "+vListaDet);
                    log.info("getVNumPedVta: "+getVNumPedVta());
                    log.info("vCtPRedi: "+vCtPRedi);
                    log.info("vFecPed: "+vFecPed);
                    log.info("vNetoPed: "+vNetoPed);
                    log.info("getDNI_USU: "+UtilityPuntos.getDNI_USU());
                    log.info("--------------------------------------------");
                    
                    BeanTarjeta trnxValidaAnula =
                        /*
                        VariablesPuntos.frmPuntos.anularPedido(getVTarjTransaccOrbis(), vIdTrx, vNumAut, vListaDet,
                                                               getVNumPedVta(), vCtPRedi, vFecPed, vNetoPed,
                                                               UtilityPuntos.getDNI_USU());*/
                        VariablesPuntos.frmPuntos.anularPedido(getVTarjTransaccOrbis(),  vListaDet,
                                                               getVNumPedVta(), vCtPRedi, vFecPed, vNetoPed,
                                                               UtilityPuntos.getDNI_USU());                    

                    if (trnxValidaAnula == null) {
                        //FarmaUtility.showMessage(pDialog, "No se pudo Recuperar puntos por:\n" +
                        //        "El estado de la Respuesta No es Válido.", objeto);
                        throw new Exception("Se presentó un error en servicio de puntos,al intentar anular.\n" +
                                "La respuesta de la operación es vacía ó nula." +"\n" +
                                "Por favor llame a mesa de ayuda");                        
                    } else {
                        //
                        BeanOperacion operacion = VariablesPuntos.frmPuntos.getBitacora();
                        if(operacion!=null){
                                log.info("[TRAMA MONEDERO] [ANULACION PEDIDO ONLINE] INPUT-->"+operacion.getVInput_in());
                                log.info("[TRAMA MONEDERO] [ANULACION PEDIDO ONLINE] OUTPUT-->"+operacion.getVOutput());
                                UtilityPuntos.insertarLogOperacion(operacion);
                            }
                        
                        log.info("Anular Pedido ORBIS");
                        if (WSClientConstans.EXITO.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                            log.info("Anular Pedido ORBIS");
                            pResultado = true;
                            // CAMPOS SOLICITADOS POR LAIS PARA GUARDAR
                            setVIdTrxAnula_Orbis(trnxValidaAnula.getIdTransaccion());
                            setVNroAutoriza_Anula_Orbis(trnxValidaAnula.getNumeroAutororizacion());
                            setVIndOperoConOrbisAnulacion(true);
                        } else {
                            if (WSClientConstans.DEVOLUCION_YA_APLICADA.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                pResultado = true;
                                // CAMPOS SOLICITADOS POR LAIS PARA GUARDAR
                                setVIdTrxAnula_Orbis(trnxValidaAnula.getIdTransaccion());
                                setVNroAutoriza_Anula_Orbis(trnxValidaAnula.getNumeroAutororizacion());                                
                                setVIndOperoConOrbisAnulacion(true);
                            } 
                            else 
                            if (WSClientConstans.TARJETA_NO_AFILIADA.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                pResultado = true;
                                // LA tarjeta es Invalida se 
                                //setVIndDescartaPedidoOrbis(true);
                                //SOLO DESCARTA LA NC NO EL PEDIDO ORIGINAL --LATAVARA 22.05.2015
                                setVIndDescartaNCnoPedidoOrbis(true);
                            } 
                            else {
                                if (WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                                    if(isVIndProcOrbisCobro()){
                                        log.info("Pedido ANULACION PEDIDO ORBIS "+getVNumPedVta()+ " NO HAY CONEXION" );
                                        if(isVIndAsocProdMasUNO()||isVIndRedimio()){
                                        //NO SE CULMINA LA ANULACION POR ESO QUEDA EN PENDIENTE DE ENVIO AL WS
                                            pResultado = true;
                                            setVIndOperoConOrbisAnulacion(false);
                                            log.info("Pedido ANULACION PEDIDO ORBIS "+getVNumPedVta()+ " Prod +1 o redime .. anulara online NO PERMITE ,.. error" );
                                            log.info("Se presentó un error en servicio de puntos,al momento de anular el pedido.\n" +
                                                                                                            "No hay conexión y no pudo ser procesada."+
                                                                                                "\n" +
                                                                                                "Por favor llame a Mesa de Ayuda");
                                            /*throw new Exception("Se presentó un error en servicio de puntos,al momento de anular el pedido.\n" +
                                                                "No hay conexión y no pudo ser procesada."+
                                                    "\n" +
                                                    "Por favor llame a Mesa de Ayuda");*/
                                           
                                           
                                        }
                                        else{
                                            if(isVIndPuntosAcum()){
                                                log.info("Pedido ANULACION PEDIDO ORBIS "+getVNumPedVta()+ " Solo acumula puntos .. anulara offline" );
                                                pResultado = true;
                                                setVIndOperoConOrbisAnulacion(false);
                                            }
                                            else{
                                                pResultado = true;
                                                setVIndOperoConOrbisAnulacion(false);
                                            }
                                        }
                                    }
                                    else{
                                        pResultado = true;
                                        setVIndOperoConOrbisAnulacion(false);
                                    }
                                    //Genera la NC en el local y queda pendiente en ORBIS enviarlos
                                } else {
                                    pResultado = false;
                                    setVIndOperoConOrbisAnulacion(false);
                                    throw new Exception("Se presentó un error en servicio de puntos.\n" +
                                            "Esta es la razón porque no se pudo anular :" +
                                        //  trnxValidaAnula.getEstadoTarjeta() + "-" + 
                                    UtilityPuntos.obtenerMensajeErrorLealtad(trnxValidaAnula.getEstadoOperacion(),trnxValidaAnula.getMensaje())                                                        
                                                        +
                                            "\n" +
                                            "Por favor llame a mesa de ayuda");
                                }
                            }
                        }
                    }                    
                } else {
                    pResultado = true;
                    log.info("NO ANULA ONLINE en orbis");
                }
            } else {
                log.info("NO SE NECESITA ANULAR en orbis");
                pResultado = true;
            }
        } else {
            throw new Exception("Error al procesar la anulación\n" +
                    "No se sabe que pedido desea anular.\n" +
                    "Por favor llame a mesa de ayuda");
        }
        return pResultado;
    }
    
    public void saveTrxAnulOrbis()throws Exception {
        DBPuntos.pGrabaProcesoAnulaOrbis(getVNumPedVta(),isVIndOperoConOrbis(),
                                         getVIdTrxAnula_Orbis(),getVNroAutoriza_Anula_Orbis());
    }
    

    public void setVNumPedVta(String vNumPedVta) {
        this.vNumPedVta = vNumPedVta;
    }

    public String getVNumPedVta() {
        return vNumPedVta;
    }

    public void setVTarjTransaccOrbis(String vTarjTransaccOrbis) {
        this.vTarjTransaccOrbis = vTarjTransaccOrbis;
    }

    public String getVTarjTransaccOrbis() {
        return vTarjTransaccOrbis;
    }

    public void setVDniPedido(String vDniPedido) {
        this.vDniPedido = vDniPedido;
    }

    public String getVDniPedido() {
        return vDniPedido;
    }

    public void setVTarjetaPedido(String vTarjetaPedido) {
        this.vTarjetaPedido = vTarjetaPedido;
    }

    public String getVTarjetaPedido() {
        return vTarjetaPedido;
    }

    public void setVIndAsocProdMasUNO(boolean vIndAsocProdMasUNO) {
        this.vIndAsocProdMasUNO = vIndAsocProdMasUNO;
    }

    public boolean isVIndAsocProdMasUNO() {
        return vIndAsocProdMasUNO;
    }

    public void setVIndProcOrbisCobro(boolean vIndProcOrbisCobro) {
        this.vIndProcOrbisCobro = vIndProcOrbisCobro;
    }

    public boolean isVIndProcOrbisCobro() {
        return vIndProcOrbisCobro;
    }

    public void setVIndPuntosAcum(boolean vIndPuntosAcum) {
        this.vIndPuntosAcum = vIndPuntosAcum;
    }

    public boolean isVIndPuntosAcum() {
        return vIndPuntosAcum;
    }

    public void setVIndRedimio(boolean vIndRedimio) {
        this.vIndRedimio = vIndRedimio;
    }

    public boolean isVIndRedimio() {
        return vIndRedimio;
    }

    public void setVAnulaOnline(boolean vAnulaOnline) {
        this.vAnulaOnline = vAnulaOnline;
    }

    public boolean isVAnulaOnline() {
        return vAnulaOnline;
    }

    public void setVAnulaEnOrbis(boolean vAnulaEnOrbis) {
        this.vAnulaEnOrbis = vAnulaEnOrbis;
    }

    public boolean isVAnulaEnOrbis() {
        return vAnulaEnOrbis;
    }

    public void setVIndBonificadoPedido(boolean vIndBonificadoPedido) {
        this.vIndBonificadoPedido = vIndBonificadoPedido;
    }

    public boolean isVIndBonificadoPedido() {
        return vIndBonificadoPedido;
    }

    public ArrayList vListDetPedAnula(String pNumPed) throws Exception {
        return DBPuntos.getListProdPuntosAnula(pNumPed);
    }

    public void setVIndOperoConOrbisAnulacion(boolean vIndOperoConOrbis) {
        this.vIndOperoConOrbis = vIndOperoConOrbis;
    }

    public boolean isVIndOperoConOrbis() {
        return vIndOperoConOrbis;
    }

    public void setVIdTrxAnula_Orbis(String vIdTrxAnula_Orbis) {
        this.vIdTrxAnula_Orbis = vIdTrxAnula_Orbis;
    }

    public String getVIdTrxAnula_Orbis() {
        return vIdTrxAnula_Orbis;
    }

    public void setVNroAutoriza_Anula_Orbis(String vNroAutoriza_Anula_Orbis) {
        this.vNroAutoriza_Anula_Orbis = vNroAutoriza_Anula_Orbis;
    }

    public String getVNroAutoriza_Anula_Orbis() {
        return vNroAutoriza_Anula_Orbis;
    }

    public void setVIndDescartaPedidoOrbis(boolean vIndDescartaPedidoOrbis) {
        this.vIndDescartaPedidoOrbis = vIndDescartaPedidoOrbis;
    }

    public boolean isVIndDescartaPedidoOrbis() {
        return vIndDescartaPedidoOrbis;
    }

    public void descartaAnulacionOrbis() throws Exception {
        DBPuntos.pDescartaAnulaOrbis(getVNumPedVta(),isVIndDescartaPedidoOrbis());
    }
    
    public void descartaNCnoOrigen() throws Exception {
        DBPuntos.pDescartaNCnoOrigen(getVNumPedVta());
    }

    public void setVIndDescartaNCnoPedidoOrbis(boolean vIndDescartaNCnoPedidoOrbis) {
        this.vIndDescartaNCnoPedidoOrbis = vIndDescartaNCnoPedidoOrbis;
    }

    public boolean isVIndDescartaNCnoPedidoOrbis() {
        return vIndDescartaNCnoPedidoOrbis;
    }

    public void setRPT_ORBIS_TRJ_INVALIDA(String RPT_ORBIS_TRJ_INVALIDA) {
        this.RPT_ORBIS_TRJ_INVALIDA = RPT_ORBIS_TRJ_INVALIDA;
    }

    public String getRPT_ORBIS_TRJ_INVALIDA() {
        return RPT_ORBIS_TRJ_INVALIDA;
    }
    
 
}
