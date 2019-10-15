package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.worker.JDialogProgress;

import farmaciasperuanas.reference.DBRefacturadorElectronico;
import farmaciasperuanas.reference.VariablesRefacturadorElectronico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import mifarma.common.FarmaConnectionRemoto;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.UtilityCPE;

import mifarma.ptoventa.caja.UtilNuevoCobro.UtilityNuevoCobro;
import mifarma.ptoventa.caja.daoNuevoCobro.BDNuevoCobro;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.caja.reference.VariablesVirtual;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.FacadeConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.delivery.reference.HiloActualizarProforma;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgProcesar_CobroGenerico extends JDialogProgress {
    private static final Logger log = LoggerFactory.getLogger(DlgProcesar_CobroGenerico.class);

    private Frame myParentFrame;
    //private boolean vValorProceso;
    private JTable tblFormasPago;
    private JLabel lblVuelto;
    private JTable tblDetallePago;
    private JTextField txtNroPedido;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private String indCommitBefore = "";

    private boolean indUpdateEnConvenio = false;

    private boolean indFinalizaCobro = false;
    private boolean indAnularPedido = false;
    private boolean bProcesando = false;
    private boolean indCobroBD = false;
    private boolean isCovenio = false;
    private String tipoCobro = "";

    public DlgProcesar_CobroGenerico() {
        this(null, "", false);
    }

    public DlgProcesar_CobroGenerico(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public DlgProcesar_CobroGenerico(Frame parent, String title, boolean modal, JTable pTableModel, JLabel pVuelto,
                                     JTable pDetallePago, JTextField pNroPedido, boolean esConvenio) {
        super(parent, title, modal);
        myParentFrame = parent;
        tblFormasPago = pTableModel;
        lblVuelto = pVuelto;
        tblDetallePago = pDetallePago;
        txtNroPedido = pNroPedido;
        isCovenio = esConvenio;
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(238, 125));
        this.getContentPane().setLayout(null);
        this.setTitle("Procesando Información . . .");
        this.getContentPane().setLayout(borderLayout1);
        this.setDefaultCloseOperation(0);

        jContentPane.setBounds(new Rectangle(0, 0, 240, 90));

        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        this.setResizable(false);
        FarmaUtility.centrarVentana(this);
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        /*** INICIO ARAVELLO 01/10/2019 ***/
        VariablesVentas.verMetasVendedor=false;  
        /*** FIN    ARAVELLO 01/10/2019 ***/
        this.setVisible(false);
        this.dispose();
    }

    @Override
    public void ejecutaProceso() {
        boolean vRetorno = false;
       
        bProcesando = true;
        log.warn("1.1.2--Ingreso a ProcesarNuevoCobro----");
        if (indCobroBD) {
//            vRetorno = finalizaCobroBD();
        } else if (indFinalizaCobro) {
            /*** INICIO ARAVELLO 09/10/2019 ***/
            try{
                if(VariablesRefacturadorElectronico.vComprobanteActual != null){
                    DBRefacturadorElectronico.actualizarComprobantePago(
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getCodGrupoCia(),
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getCodLocal(),
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getNumPedidoVentaNew()
                                                                        );
                    DBRefacturadorElectronico.actualizarFormaPago(
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getCodGrupoCia(),
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getCodLocal(),
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getNumPedidoVentaNew()
                                                                        );
                } 
            }catch(Throwable ex){
                log.error("",ex);
            }

            /*** FIN    ARAVELLO 09/10/2019 ***/
            vRetorno = finalizaCobro();
        } else if (indAnularPedido) {
//            vRetorno = anulaPedido();
        } else {
            vRetorno = realizarCobro();
        }
        
        log.debug("Retorno de Procesar Nuevo Cobro(Convenio - Gral):" + vRetorno);
        log.debug("Termino de procesar !!!!");
        cerrarVentana(vRetorno);
    }
/***********************************************************************************************************************/
    private boolean realizarCobro() {
        boolean datosValidos = UtilityNuevoCobro.validarDatos_Cobro(this, VariablesCaja.vNumPedVta, tblFormasPago);
        if (!datosValidos)
            return false;

        if (!UtilityCaja.validaFechaMovimientoCaja(this, tblFormasPago)) {
            FarmaUtility.liberarTransaccion();
            return false;
        }

        boolean vRetorno = false;
        //INICIO PROCESO DE COBRO
        if (isCovenio) {
            /*** INICIO ARAVELLO 11/10/2019 ***/
            try{
                if(VariablesRefacturadorElectronico.vComprobanteActual != null){
                    DBRefacturadorElectronico.actualizarInfoConvenio(
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getCodGrupoCia(),
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getCodLocal(),
                                                                        VariablesRefacturadorElectronico.vComprobanteActual.getNumPedidoVentaNew()
                                                                        );
                }
            }catch(Throwable ex){
                log.error("",ex);
            }

            /*** FIN    ARAVELLO 11/10/2019 ***/
            tipoCobro = "C";
            vRetorno = cobroConvenio();
        } else {
            tipoCobro = "G";
            vRetorno = cobroGeneral();
        }
        return vRetorno;
    }
/***********************************************************************************************************************/
    private boolean finalizaCobro() {
        log.warn("***** PROCESO FINALIZAR COBRO *****");
        boolean vRetorno = false;
        if (isCovenio) {
            tipoCobro = "C";
            vRetorno = finalizaCobroConvenio();
        } else {
            tipoCobro = "G";
            vRetorno = finalizaCobroGeneral();
        }
        return vRetorno;
    }
/***********************************************************************************************************************/
    private boolean cobroConvenio() {
        boolean esComprobanteCredito = false;
        boolean procesoExito=false;
        /*--------SOLO SI ES CONVENIO------------*/
        if (ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL.equalsIgnoreCase(VariablesVentas.vTipoPedido)) 
            VariablesCaja.vValMontoCredito = VariablesCaja.vValTotalPagar;
       
        if (FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoCredito) > 0) 
            esComprobanteCredito = true;
        
        /*--------FINS SI ES CONVENIO------------*/
        String resultado = "";
        resultado = procesarCobro_Fase1(""); //Si commit;
        try {
            String resMatriz = "";
            log.warn("RPTA: PROCESO LOCAL: "+resultado);
            if (resultado.trim().equalsIgnoreCase("EXITO")) {
                VariablesCaja.vIndPedidoCobrado = true;
                DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "F");
                /** Fase 1.1- PROCESAR PEDIDO EN RAC (SEGUN SE HA EL CASO) **/
                if (VariablesConvenioBTLMF.vFlgValidaLincreBenef.equals("1") || esComprobanteCredito) {
                    log.warn("Es convenio: grabara en rac");
                    FacadeConvenioBTLMF facadeConvenioBTLMF = new FacadeConvenioBTLMF();
                    //resMatriz = facadeConvenioBTLMF.grabarTemporalesRAC(VariablesCaja.vNumPedVta);
                    //resMatriz = facadeConvenioBTLMF.grabarDatosPedidos_RAC(VariablesCaja.vNumPedVta,FarmaConstants.INDICADOR_N);
                    resMatriz = facadeConvenioBTLMF.grabarPedidVta_RAC(VariablesCaja.vNumPedVta,FarmaConstants.INDICADOR_N);
                    log.warn("==> Proceso en rac : "+resMatriz);
                    if ("S".equalsIgnoreCase(resMatriz)) {
                        DBCaja.actualizaFechaProcesoRac(VariablesCaja.vNumPedVta);
                        log.warn("Actualiza fecha de conexion a rac en local "+resMatriz+ "== S ?");
                        FarmaUtility.aceptarTransaccion();
                        procesoExito=true;
                    } else {
                        resMatriz=resMatriz.replaceAll("&", "\n");
                        throw new Exception("Error al realizar al procesar pedido en RAC\n" +
                                            "NroPedido: " + VariablesCaja.vNumPedVta + "\n" +
                                            "Error: " + resMatriz);
                    }
                    /*
                    if ("S".equalsIgnoreCase(resMatriz)) {
                        log.warn("grabara en matriz");
                        resMatriz =
                                facadeConvenioBTLMF.grabarCobroPedidoRac(VariablesCaja.vNumPedVta, FarmaVariables.vCodLocal,
                                                                         FarmaVariables.vCodGrupoCia,
                                                                         FarmaConstants.INDICADOR_N);
                        log.warn("Grabo y actualizo todo el pedido en rac\n"+resMatriz);
                        
                        if ("S".equalsIgnoreCase(resMatriz)) {
                            DBCaja.actualizaFechaProcesoRac(VariablesCaja.vNumPedVta);
                            
                            //resMatriz =
                            //        facadeConvenioBTLMF.actualizaFechaProcesoRac(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                            //                                                     VariablesCaja.vNumPedVta);
                            
                            log.warn("Actualiza fecha de conexion a rac en local "+resMatriz+ "== S ?");
                            FarmaUtility.aceptarTransaccion();
                        } else {
                            throw new Exception("Error al realizar al procesar pedido en RAC\n" +
                                    "NroPedido: " + VariablesCaja.vNumPedVta + "\n" +
                                    "Error: " + resMatriz);
                        }
                        
                    } else {
                        throw new Exception("No se grabaron los temporales en el RAC.\n" +
                                "Reintente, si problema persiste comuniquese con Mesa de Ayuda!!!.\n" +
                                "Error: " + resMatriz);
                    }
                    */
                } else {
                    log.warn("commit en  local");
                    FarmaUtility.aceptarTransaccion();
                    procesoExito=true;
                }
            } else {
                FarmaUtility.showMessage(this, "El proceso no devolvio exito\n"
                                               +"Error: "+resultado, null);
                FarmaUtility.liberarTransaccion();
            }
        } catch (SQLException sql) {
            log.warn("Primer catch del proceso de cobro gral --> SQLException");
            FarmaUtility.liberarTransaccion();
            sql.printStackTrace();
            if (VariablesConvenioBTLMF.vFlgValidaLincreBenef.equals("1")) {
                FarmaConnectionRemoto.closeConnection();
            }
            FarmaUtility.liberarTransaccion();
            String pMensaje = sql.getMessage();
            int nIsSecCajaNull = pMensaje.indexOf("CHECK_SEC_MOV_CAJA");

            if (nIsSecCajaNull > 0) {
                FarmaUtility.showMessage(this, "No se pudo cobrar el pedido.\nInténtelo nuevamente", tblFormasPago);
            } else {
                VariablesCaja.vIndPedidoCobrado = false;
                // KMONCADA 18.09.2014 CONTROL DE ERRORES EN EL RAC.
                switch (sql.getErrorCode()) {
                case 20850: // LINEA DE CREDITO
                    FarmaUtility.showMessage(this,
                                             "El cliente no cuenta con crédito suficiente para realizar esta venta. Comuníquese con Créditos y Cobranzas",
                                             null);
                    break;
                case 20851: // DOCUMENTOS DUPLICADOS
                    FarmaUtility.showMessage(this, "Documento ya se encuentra registrado en RAC: \n" +
                            sql.getMessage().substring(sql.getMessage().indexOf("- CIA:"),
                                                       sql.getMessage().indexOf("ORA-00001:")) +
                            "\nComuniquese con Sistemas.", null);
                    break;
                case 20852: // CLIENTE SIN CODIGO SAP
                    FarmaUtility.showMessage(this, "Cliente no cuenta con codigo SAP asignado en RAC.\n" +
                            sql.getMessage().substring(sql.getMessage().indexOf("RUC/DNI"),
                                                       sql.getMessage().indexOf("ORA-06512: en \"MEDCO.FACTURAC_GENERA_ASIENTO\"")).replace("RAZ.SOC",
                                                                                                                                            "\nRAZ.SOC"),
                            null);
                    break;
                case 20853: // CLIENTE NO CUENTA CON LINEA DE CREDITO EN CONVENIO MIXTO
                    FarmaUtility.showMessage(this, "Cliente no cuenta con Línea de Crédito\n" +
                            "Agregue una Forma de Pago en Efectivo y/o Tarjeta.\n" +
                            "o Comuníquese con Créditos y Cobranzas.\n", null);
                    break;
                default:
                    UtilityPtoVenta.mensajeErrorBd(this, "Error en BD al cobrar el Pedido.\n" +
                            sql.getMessage(), tblFormasPago);
                }
            }
        } catch (Exception ex) { //error inesperado
            log.warn("Segundo catch del proceso de cobro gral --> Exception "+indCommitBefore);
            String mensajeError = "";
            ex.printStackTrace();
            if (indCommitBefore.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                if (VariablesCaja.vIndPedidoConProdVirtual) {
                    //evalua indicador de impresion por error
                    String vIndImpre = "N";
                    try {
                        vIndImpre = DBCaja.obtieneIndImpresionRecarga(VariablesVirtual.vCodigoRespuesta);
                    } catch (Exception e) {
                        vIndImpre = "N";
                    }
                    if (!vIndImpre.equals("N")) {
                        if (!UtilityCaja.validaFechaMovimientoCaja(this, tblFormasPago)) {
                            FarmaUtility.liberarTransaccion();
                            return false;
                        }
                        //**
                        //JMIRANDA 24.06.2010
                        //VALIDAR SI HIZO UPDATE DAR COMMIT
                        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                            FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0) {
                            String vIndLineaMat =
                                FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);

                            if (((indUpdateEnConvenio) && VariablesCaja.usoConvenioCredito.equalsIgnoreCase("S")) &&
                                vIndLineaMat.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                                FarmaUtility.aceptarTransaccion();
                                if (VariablesCaja.vIndCommitRemota) {
                                    FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                          FarmaConstants.INDICADOR_N);
                                }
                            } else {
                                //DUBILLUZ 25.06.2010
                                VariablesCaja.vIndPedidoCobrado = false;
                                FarmaUtility.liberarTransaccion();
                                FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                                FarmaUtility.showMessage(this,
                                                         "Error al actualizar el uso del Convenio.\nComuníquese con el Operador de Sistemas.",
                                                         null);
                                return false;
                            }
                        } else {
                            FarmaUtility.aceptarTransaccion();
                            if (VariablesCaja.vIndCommitRemota) {
                                FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                            }
                        }
                        UtilityCaja.obtieneInfoCajero(VariablesCaja.vSecMovCaja);
                        UtilityCaja.obtieneInfoVendedor();
                        try {
                            DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "F");
                            FarmaUtility.aceptarTransaccion();
                        } catch (SQLException sql) {
                            log.error("", sql);
                            FarmaUtility.liberarTransaccion();
                        }
                        FarmaUtility.showMessage(this, "Error en Aplicacion al cobrar el Pedido.\n" +
                                ex.getMessage(), tblFormasPago);
                        FarmaUtility.showMessage(this, "Comprobantes Impresos con Exito", tblFormasPago);
                    } else {
                        FarmaUtility.liberarTransaccion();
                        if (VariablesCaja.vIndCommitRemota) {
                            log.debug("jcallo: liberando transaccione remota");
                            FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                  FarmaConstants.INDICADOR_N);
                        }
                        FarmaUtility.showMessage(this, "Error en Aplicacion al cobrar el Pedido.\n" +
                                ex.getMessage(), tblFormasPago);
                    }
                }
            } else {
                FarmaUtility.liberarTransaccion();
                if (ex instanceof RuntimeException) {
                    String[] lstError = ex.getMessage().trim().split(";");
                    for (int i = 0; i < lstError.length; i++) {
                        if (lstError[i].trim().startsWith("ORA-"))
                            mensajeError = mensajeError + "\n" +
                                    lstError[i].trim();
                    }
                } else {
                    mensajeError = ex.getMessage();
                }
                FarmaUtility.showMessage(this, "Error en Aplicacion al cobrar el Pedido Convenio.\n" +
                        mensajeError, tblFormasPago);                
            }
            //no se pudo cobrar el pedido
            VariablesCaja.vIndPedidoCobrado = false;
        } finally {
            //Se cierra la conexion si hubo linea esto cuando se consulto a matriz
            FarmaConnectionRemoto.closeConnection(); //dentro metodo solo cierra si estuvo abierta alguna conexion
        }
        return procesoExito;
    }

/***********************************************************************************************************************/
/***********************************************************************************************************************/
    private boolean cobroGeneral() {
        //se agrego para tener un indicador si se mando realizar la recarga virtual
        VariablesCaja.vIndEnvioRecargar = false;
        boolean procesoExito=false;
        //INICIO PROCESO DE COBRO GENERAL
        try {
//-*********************************************************************************************************************
            //validacion de campañas limitadas en cantidad de uso
            //obteniendo el dni del cliente si se trata de una venta cliente fidelizado
            String dniClienteFidelizado = obtenerDniClienteFidelizado(VariablesCaja.vNumPedVta);
            if (dniClienteFidelizado != null && dniClienteFidelizado.trim().length() > 0) {
                //quiere decir que es pedido de venta fidelizado
                ArrayList listaCampLimitTerminados = new ArrayList();
                ArrayList listaCampAutomaticasPedido = new ArrayList();

                //obtiene todas las campañas automaticas usados en el pedido
                listaCampAutomaticasPedido = UtilityNuevoCobro.getCampAutomaticasPedido(VariablesCaja.vNumPedVta);
                listaCampLimitTerminados = UtilityNuevoCobro.CampLimitadasUsadosDeLocalXCliente(dniClienteFidelizado);

                boolean flag = false;

                if (listaCampLimitTerminados != null) {
                    for (int i = 0; i < listaCampLimitTerminados.size(); i++) {
                        String cod_camp = listaCampLimitTerminados.get(i).toString();
                        if (listaCampAutomaticasPedido != null && listaCampAutomaticasPedido.contains(cod_camp)) {
                            flag = true;
                            break;
                        }
                    }
                }

                //quiere decir que encontro al menos una campaña que ya no deberia de aplicar, anular el pedido
                if (flag) {
                    FarmaUtility.liberarTransaccion();
                    FarmaUtility.showMessage(this,
                                             "Error al cobrar pedido !\nEl descuento de la campaña ya fue usado por el cliente !",
                                             tblFormasPago);
                    return false;
                }
            }
            
            //ERIOS 26.03.2015 Recalcular puntos acumulados
            log.warn("**--VALIDA LINEAS POR DOCUMENTO--**");
            int cantidadPorComprobante = 0;
            String lineasPorComprobante = "";
            String pIsCompManual = DBCaja.getIndIngresoCompManual(VariablesCaja.vNumPedVta);
            //DUBILLUZ 05.11.2014
            if (UtilityCPE.isActivoFuncionalidad() && !UtilityCPE.isEstaContigenciaEPOS() &&
                "N".equalsIgnoreCase(pIsCompManual)) {
                lineasPorComprobante = ConstantsVentas.LINEAS_ELECTRONICO_SIN_CONV;
            } else {
                //numero de lineas por BOLETA
                if (VariablesVentas.vTip_Comp_Ped != null &&
                    VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_BOLETA)) {
                    lineasPorComprobante = ConstantsVentas.LINEAS_BOLETA_SIN_CONVENIO;
                }
                //numero de linea por FACTURA
                else if (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA)) {
                    lineasPorComprobante = ConstantsVentas.LINEAS_FACTURA_SIN_CONVENIO;
                }
                //numero de linea por TICKET
                else if (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET)) {
                    lineasPorComprobante = ConstantsVentas.LINEAS_TICKET;
                }
                //LLEIVA 30-Ene-2014 Numero de linea por TICKET FACTURA
                else if (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT)) {
                    lineasPorComprobante = ConstantsVentas.LINEAS_TICKET;
                }
            }
            String temp = DBCaja.getLineasDetalleDocumento(lineasPorComprobante);
            if (temp == null || "".equals(temp))
                temp = "0";
            cantidadPorComprobante = Integer.parseInt(temp);
            VariablesCaja.vNumSecImpresionComprobantes = DBCaja.agrupaImpresionDetallePedido(cantidadPorComprobante);
            log.debug("**--TERMINA DE OBTENER LINEAS POR DOCUMENTO--**");
            
//-*********************************************************************************************************************
            String resultado = "";
            resultado = procesarCobro_Fase1(dniClienteFidelizado);
//----------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
            /** 1.- COBRA PEDIDO EN EL LOCAL **/
            //cobrar pedido DEVOLVERA EXITO. si cobro correctamente
            //Se agrega DNI para guardar en cupone generados
            if (resultado.trim().equalsIgnoreCase("EXITO")) {
                /*
                System.out.append( VariablesPuntos.frmPuntos.getBeanTarjeta().getIdTransaccion());
                DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "F");
                System.out.append( VariablesPuntos.frmPuntos.getBeanTarjeta().getIdTransaccion());
                */// KMONCADA 08.11.2016
                // Valida formas de pago campana
                String vResValidaCampConFormaPAgo = DBCaja.verificaPagoUsoCampana(VariablesCaja.vNumPedVta);
                log.warn("verificaPagoUsoCampana ERROR:   " + vResValidaCampConFormaPAgo);
                if (vResValidaCampConFormaPAgo.trim().equalsIgnoreCase("N")) { //modificado por jvara 27.06.2017
                    log.warn("verificaPagoUsoCampana N");
                    FarmaUtility.liberarTransaccion();
                    //VariablesCaja.vIndPedidoCobrado = false;
                    FarmaUtility.showMessage(this, "No se puede cobrar el pedido con las formas de pago ingresada\n" +
                            "Porque existen descuentos aplicados a la venta que no cumplen con las condiciones de pago.",
                            tblFormasPago);
                    return false;
                }
                // Valida montos de pedido
                log.warn("verificaPedidoFormasPago",VariablesCaja.vNumPedVta);
                String v_Resultado = DBCaja.verificaPedidoFormasPago(VariablesCaja.vNumPedVta);
                log.warn("v_Resultado:   " + v_Resultado);
                if (v_Resultado.trim().equalsIgnoreCase("ERROR")) {
                    log.warn("FORM_PAGO_PEDIDO ERROR");
                    FarmaUtility.liberarTransaccion();
                    //VariablesCaja.vIndPedidoCobrado = false;
                    FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                            "Los totales de formas de pago y cabecera no coinciden. \n" +
                            "Comuníquese con el Operador de Sistemas inmediatamente." + ". \n" +
                            "NO CIERRE LA VENTANA.", tblFormasPago);
                    return false;
                }
                
                log.warn("EXITO - SE GRABO EN TABLA COBRAR PEDIDO");
                //PROCESO DE VERIFICACION DE EMISION DE CUPON CUMPLEAÑOS
                FarmaUtility.aceptarTransaccion();
                boolean mesCumpleanio = false;
                if (!VariablesFidelizacion.vFecNacimiento.trim().equalsIgnoreCase("")) {
                    mesCumpleanio=UtilityNuevoCobro.consultaMesCumpleanio();
                }
                //wasInsertedProdRimac();

                //el liberar transaccion esta dentro del metodo
                if (!UtilityCaja.validaAgrupacionComprobante(this, tblFormasPago)) {
                    VariablesCaja.vIndPedidoCobrado = false;
                    return false;
                }

                //KMONCADA 23.11.2016 [FACTURACION ELECTRONICA 2.0]
                //VERIFICA QUE TODOS SE HAYAN ASIGNADO COMPROBANTES DE PAGO
                ArrayList lstComprobantes = UtilityCaja.obtieneComprobantesPago(VariablesCaja.vNumPedVta);
                for (int k = 0; k < lstComprobantes.size(); k++) {
                    String secCompPago = ((String)((ArrayList)lstComprobantes.get(k)).get(1)).trim();
                    String pNroCP =
                        DBCaja.getNumeroComprobante(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, VariablesCaja.vNumPedVta,
                                                    secCompPago);
                    if (pNroCP == null) {
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                                "No se asignaron correctamente los comprobantes al pedido. \n" +
                                "Reintente, en caso persista comuníquese con Mesa de Ayuda." + ". \n", tblFormasPago);
                        return false;
                    }
                }

                VariablesCaja.vIndPedidoCobrado = true;
                //si es pedido convenio y va usar credito de convenio
                if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                    FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0) {

                    String vIndLinea =
                        FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);
                    boolean indExisteConv = false;
                    boolean indMontoValido = false;
                    log.debug("indica linea on line (conexion con matriz ):  " + vIndLinea);
                    //NUEVO METODO DE CONVENIO
                    if (vIndLinea.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                        String valor =
                            DBConvenio.validaCreditoCli(VariablesConvenio.vCodConvenio, VariablesConvenio.vCodCliente,
                                                        "" + VariablesConvenio.vValCredDis,
                                                        FarmaConstants.INDICADOR_S);

                        double vValCredDisponible = FarmaUtility.getDecimalNumber(valor);

                        //Paso 1 valida que exista el convenio
                        indExisteConv =
                                UtilityConvenio.getIndClienteConvActivo(this, tblFormasPago, VariablesConvenio.vCodConvenio,
                                                                        VariablesConvenio.vNumDocIdent,
                                                                        VariablesConvenio.vCodCliente);

                        if (indExisteConv) {
                            //Paso 2 validar el monto disponible
                            indMontoValido =
                                    UtilityConvenio.getIndValidaMontoConvenio(this, tblFormasPago, VariablesConvenio.vCodConvenio,
                                                                              VariablesConvenio.vNumDocIdent,
                                                                              FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago),
                                                                              VariablesConvenio.vCodCliente);
                            if (indMontoValido) {
                                //El convenio está activo y el monto a usar es correcto
                                //actualiza consumo del cliente en matriz
                                DBConvenio.actualizaConsumoClienteEnMatriz_v2(VariablesConvenio.vCodConvenio,
                                                                              VariablesConvenio.vCodCliente,
                                                                              "" + VariablesConvenio.vValCredDis,
                                                                              FarmaConstants.INDICADOR_N,
                                                                              VariablesCaja.vNumPedVta,
                                                                              FarmaVariables.vIdUsu,
                                                                              VariablesConvenio.vNumDocIdent);
                                //En el Metodo ActualizaConsumoClienteEnMatriz se inserta los Datos del consumo
                                //del convenio en la tabla CON_REG_VENTA
                                VariablesCaja.vIndCommitRemota = true;
                                //indica que debera hacer commit remotamente si to_do el proceso es exitoso
                                //actualizar credito disponible del cliente en local*/
                                DBConvenio.actualizarCreditoDisp(VariablesConvenio.vCodConvenio,
                                                                 VariablesConvenio.vCodCliente,
                                                                 VariablesCaja.vNumPedVta, vValCredDisponible);
                                indUpdateEnConvenio = true;
                            }
                        }
                    } else { //si no hay linea con matriz
                        log.warn("No HAY LINEA CON MATRIZ");
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.showMessage(this,
                                                 "Error: En este momento no hay linea con matriz.\nSi el problema persiste comunicarse con el operador de sistema !",
                                                 tblFormasPago);
                        return false;
                    }
                }
                //obtener flag de IND PARA SABER SI IMPRIMIRA ANTES DE LA RECARGA VIRTUAL
                indCommitBefore = getIndCommitAntesRecargar();
                if (dniClienteFidelizado.length() > 0) { //quiere decir que es pedido de venta fidelizado
                    boolean pRspCampanaAcumulad =
                        UtilityCaja.realizaAccionCampanaAcumulada(FarmaConstants.INDICADOR_N, //NO HAY LINEA
                            VariablesCaja.vNumPedVta, this, ConstantsCaja.ACCION_COBRO, txtNroPedido,
                            FarmaConstants.INDICADOR_N);
                    if (!pRspCampanaAcumulad) {
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                              FarmaConstants.INDICADOR_S);
                        VariablesCaja.vIndPedidoCobrado = false;
                        FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                                "Presenta un producto regalo de campaña que no se puede validar con Matriz. \n" +
                                "Inténtelo nuevamente de lo contrario anule el pedido y genérelo nuevamente." + "\n" +
                                "Gracias.", tblFormasPago);
                        return false;
                    }
                }
                //flag de IND PARA SABER SI IMPRIMIRA ANTES DE LA RECARGA VIRTUAL
                if (indCommitBefore.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                    //ERIOS 10.10.2013 Parte de finalizar cobro
                    //evalua indicador de impresion por error
                    String vIndImpre = DBCaja.obtieneIndImpresionRecarga(VariablesVirtual.vCodigoRespuesta);
                    if (!vIndImpre.equals("N")) {
                        //KMONCADA 10.05.2016
                        UtilityVentas.actualizarCuponesUsados(this, VariablesCaja.vNumPedVta,
                                                              VariablesCaja.listCuponesUsadosPedido, tblFormasPago);
                        if (!UtilityCaja.validaFechaMovimientoCaja(this, tblFormasPago)) {
                            FarmaUtility.liberarTransaccion();
                            return false;
                        }
                        //VALIDAR SI HIZO UPDATE DAR COMMIT
                        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                            FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0) {
                            String vIndLineaMat =
                                FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);

                            if (((indUpdateEnConvenio) && VariablesCaja.usoConvenioCredito.equalsIgnoreCase("S")) &&
                                vIndLineaMat.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                                FarmaUtility.aceptarTransaccion();
                                if (VariablesCaja.vIndCommitRemota) {
                                    FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                          FarmaConstants.INDICADOR_N);
                                }
                            } else {
                                //DUBILLUZ 25.06.2010
                                VariablesCaja.vIndPedidoCobrado = false;
                                FarmaUtility.liberarTransaccion();
                                FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                                return false;
                            }
                        } else {
                            FarmaUtility.aceptarTransaccion();
                            if (VariablesCaja.vIndCommitRemota) {
                                FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                            }
                        }

                        /* DESPUES DEL MENSAJE DE COBRADO CON EXITO***/
                        //HASTA AQUI EL PEDIDO SE ENCUENTRA EN ESTADO S
                        UtilityCaja.obtieneInfoCajero(VariablesCaja.vSecMovCaja);
                        //obtiene informacion del vendedor
                        UtilityCaja.obtieneInfoVendedor();
                        //proceso de impresion de comprobante del pedido

                        VariablesCaja.vIndLineaMatriz = VariablesCaja.vIndLinea;

                        try {
                            DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "F");
                            FarmaUtility.aceptarTransaccion();
                        } catch (SQLException sql) {
                            log.error("", sql);
                            FarmaUtility.liberarTransaccion();
                        }
                    } else { //si el indicador de impresion es N
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.showMessage(this, "Error en Aplicacion al cobrar el Pedido.", tblFormasPago);
                    }
                } else { //quiere decir que el indicador de IMPRIMIR ANTES DE RECARGAR ES DIFERENTE DE S
                    if (indCommitBefore.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                        //KMONCADA 10.05.2016 USO DE CUPONES
                        //UtilityVentas.actualizarCuponesUsados(this, VariablesCaja.vNumPedVta,
                        //                                      VariablesCaja.listCuponesUsadosPedido, tblFormasPago);

                        //if (!UtilityCaja.validaFechaMovimientoCaja(this, tblFormasPago)) {
                        //    FarmaUtility.liberarTransaccion();
                        //    return false;
                        //}

                        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                            FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0) {
                            log.debug("Indicador del que es un pedido sin convenio");
                            String vIndLineaMat =
                                FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);

                            if (((indUpdateEnConvenio) && VariablesCaja.usoConvenioCredito.equalsIgnoreCase("S")) &&
                                vIndLineaMat.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                                FarmaUtility.aceptarTransaccion();
                                if (VariablesCaja.vIndCommitRemota) {
                                    FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                          FarmaConstants.INDICADOR_N);
                                }
                            } else {

                                VariablesCaja.vIndPedidoCobrado = false;
                                FarmaUtility.liberarTransaccion();
                                FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                                FarmaUtility.showMessage(this,
                                                         "Error al actualizar el uso del Convenio.\nComuníquese con el Operador de Sistemas.",
                                                         null);
                                return false;
                            }
                        } else {
                            log.debug("Indicador del que es un pedido sin convenio");
                            FarmaUtility.aceptarTransaccion();
                            //INICIO RPASCACIO 29-05-2017
                            try {
                                DBVentas.verificaPedido();
                            } catch (SQLException e) {
                                VariablesCaja.vIndPedidoCobrado = false; //No se pudo cobrar el pedido
                                FarmaUtility.showMessage(this,
                                                         "Error, Productos afectos e inafectos en un mismo comprobante",
                                                         null);
                                return false;
                            }
                            //FIN RPASCACIO
                            if (VariablesCaja.vIndCommitRemota) {
                                FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                            }
                        }
                        /** KMONCADA 17.07.2015 SE MODIFICA EL ORDEN DEL ENVIO DE PEDIDO CON PUNTOS YA QUE DEBE HACERLO LUEGO DE REALIZAR EL COBRO **/
                        
                        /*
                        COBRO DE ORBIS
                        //2.6 ERIOS 13.02.2015 Registrar venta lealtad
                        FacadeLealtad facadeLealtad = new FacadeLealtad();
                        if (!facadeLealtad.registrarVenta(this, VariablesCaja.vNumPedVta)) {
                        }
                        */

                        UtilityCaja.obtieneInfoCajero(VariablesCaja.vSecMovCaja);
                        UtilityCaja.obtieneInfoVendedor();
                        /*
                        try {
                            DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "F");
                            FarmaUtility.aceptarTransaccion();
                        } catch (SQLException sql) {
                            log.error("HORROR -", sql);
                            FarmaUtility.liberarTransaccion();
                        }*/
                    }
                }
                log.warn("FIN DE QUE SE HAYA COBRADO EXITOSAMENTE,..... AUN SIN IMPRIMIR - Comprobante");
                //FIN DE QUE SE HAYA COBRADO EXITOSAMENTE
                //PROCESO DE VERIFICACION DE EMISION DE CUPON CUMPLEAÑOS
                try {
                    DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "F");
                    FarmaUtility.aceptarTransaccion();
                    procesoExito=true;
                } catch (SQLException sql) {
                    log.error("HORROR -", sql);
                    FarmaUtility.liberarTransaccion();
                }
            } else if (resultado.trim().equalsIgnoreCase("ERROR")) {
                log.warn("ERROR - AL GRABAR EN TABLA COBRAR PEDIDO");
                FarmaUtility.liberarTransaccion();
                VariablesCaja.vIndPedidoCobrado = false;
                FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                        "Se produjo un error al intentar grabar el pedido en local. \n" +
                        "Comuníquese con el Operador de Sistemas." + ".", tblFormasPago);
                
                /*
                FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                        "Los totales de formas de pago y cabecera no coinciden. \n" +
                        "Comuníquese con el Operador de Sistemas inmediatamente." + ". \n" +
                        "NO CIERRE LA VENTANA.", tblFormasPago);
                */
            }else {
                log.warn("ERROR - devuelve el TRY ");
                FarmaUtility.liberarTransaccion();
                VariablesCaja.vIndPedidoCobrado = false;
                FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                        resultado+" \n" +
                        "Comuníquese con el Operador de Sistemas." + ".", tblFormasPago);
                
                /*
                FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                        "Los totales de formas de pago y cabecera no coinciden. \n" +
                        "Comuníquese con el Operador de Sistemas inmediatamente." + ". \n" +
                        "NO CIERRE LA VENTANA.", tblFormasPago);
                */
            }
        } catch (SQLException sql) { //error de base de datos al cobrar
            log.warn("HORROR - AL GRABAR EN TABLA COBRAR PEDIDO");
            FarmaUtility.liberarTransaccion();
            log.error(null, sql);
            String pMensaje = sql.getMessage();

            int nIsSecCajaNull = pMensaje.indexOf("CHECK_SEC_MOV_CAJA");

            if (nIsSecCajaNull > 0) {
                FarmaUtility.showMessage(this, "No se pudo cobrar el pedido.\nInténtelo nuevamente", tblFormasPago);
            } else {

                if (VariablesCaja.vIndEnvioRecargar) {
                    log.error("ERROR de BASE DE DATOS AL MOMENTO DE COBRAR UN RECARGAR VIRTUAL...PERO IGUAL SE MANDO A RECARGAR!");
                    try {
                        FarmaUtility.enviaCorreoPorBD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                                                      VariablesPtoVenta.vDestEmailErrorCobro,
                                                      "Error Recarga Virtual, error de base datos",
                                                      "Error de Recarga Virtual",
                                                      "Error al realizar recarga virtual al numero : " +
                                                      VariablesCaja.vNumeroCelular,
                                                      "IP PC: " + FarmaVariables.vIpPc + "<br>" + "");
                    } catch (Exception e) {
                        log.error("ERROR AL TRATAR de enviar correo de alerta de recarga virtual");
                    }
                }

                log.error("", sql);
                if (sql.getErrorCode() > 20000) {
                    VariablesCaja.vIndPedidoCobrado = false;
                    FarmaUtility.showMessage(this,
                                             sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                             null);
                } else {
                    VariablesCaja.vIndPedidoCobrado = false;
                    FarmaUtility.showMessage(this, "Error en BD al cobrar el Pedido.\n" +
                            sql.getMessage(), tblFormasPago);
                }
            }
            if(VariablesVentas.vResultadoORBIS){
                FacadeLealtad facade = new FacadeLealtad();
                try {
                    facade.anularVenta(this, VariablesCaja.vNumPedVta);
                } catch (Exception e) {
                    FarmaUtility.showMessage(this, "Error al anular pedido en ORBIS.", null);
                }
            }
        } catch (Exception ex) { //error inesperado
            log.warn("ERROR INESPERADO - AL GRABAR EN TABLA COBRAR PEDIDO");
            log.error("", ex); //error inesperado
            if (indCommitBefore.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                if (VariablesCaja.vIndPedidoConProdVirtual) {
                    //evalua indicador de impresion por error
                    String vIndImpre = "N";
                    try {
                        vIndImpre = DBCaja.obtieneIndImpresionRecarga(VariablesVirtual.vCodigoRespuesta);
                    } catch (Exception e) {
                        log.error("", e);
                        vIndImpre = "N";
                    }

                    if (!vIndImpre.equals("N")) {
                        if (!UtilityCaja.validaFechaMovimientoCaja(this, tblFormasPago)) {
                            FarmaUtility.liberarTransaccion();
                            return false;
                        }
                        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                            FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0) {
                            String vIndLineaMat =
                                FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);

                            if (((indUpdateEnConvenio) && VariablesCaja.usoConvenioCredito.equalsIgnoreCase("S")) &&
                                vIndLineaMat.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                                FarmaUtility.aceptarTransaccion();
                                if (VariablesCaja.vIndCommitRemota) {
                                    FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                          FarmaConstants.INDICADOR_N);
                                }
                            } else {
                                VariablesCaja.vIndPedidoCobrado = false;

                                FarmaUtility.liberarTransaccion();
                                FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                                FarmaUtility.showMessage(this,
                                                         "Error al actualizar el uso del Convenio.\nComuníquese con el Operador de Sistemas.",
                                                         null);
                                return false;
                            }
                        } else {
                            FarmaUtility.aceptarTransaccion();
                            if (VariablesCaja.vIndCommitRemota) {
                                FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_N);
                            }
                        }
                        UtilityCaja.obtieneInfoCajero(VariablesCaja.vSecMovCaja);
                        UtilityCaja.obtieneInfoVendedor();
                        try {
                            DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "F");
                            FarmaUtility.aceptarTransaccion();
                        } catch (SQLException sql) {
                            log.error("", sql);
                            FarmaUtility.liberarTransaccion();
                        }
                        //ERIOS 10.10.2013 Parte de finalizar cobro
                    } else {
                        FarmaUtility.liberarTransaccion();
                        if (VariablesCaja.vIndCommitRemota) {
                            FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                  FarmaConstants.INDICADOR_N);
                        }
                        FarmaUtility.showMessage(this, "Error en Aplicacion al cobrar el Pedido.\n" +
                                ex.getMessage(), tblFormasPago);
                    }
                    //no se pudo cobrar el pedido
                    VariablesCaja.vIndPedidoCobrado = false;
                }
            } else {
                if(VariablesVentas.vResultadoORBIS){
                    FacadeLealtad facade = new FacadeLealtad();
                    try {
                        facade.anularVenta(this, VariablesCaja.vNumPedVta);
                    } catch (Exception e) {
                        FarmaUtility.showMessage(this, "Error al anular pedido en ORBIS.", null);
                    }
                }
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(this, "Error en Aplicacion al cobrar el Pedido.\n" +
                        ex.getMessage(), tblFormasPago);
            }
            //no se pudo cobrar el pedido
            VariablesCaja.vIndPedidoCobrado = false;
        } finally {
            //Se cierra la conexion si hubo linea esto cuando se consulto a matriz
            FarmaConnectionRemoto.closeConnection(); //dentro metodo solo cierra si estuvo abierta alguna conexion
        }
        return procesoExito;
    } //Fin de cobro general

/***********************************************************************************************************************/
/***********************************************************************************************************************/
    private void formasPagoImpresion() {
        //verificar que haya al menos una forma de pago declarado
        if (tblDetallePago.getRowCount() <= 0) {
            VariablesCaja.vFormasPagoImpresion = "";
            return;
        }
        //obtiene la descripcion de las formas de pago para la impresion
        for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
            if (i == 0) {
                VariablesCaja.vFormasPagoImpresion = ((String)tblDetallePago.getValueAt(i, 1)).trim();
            } else {
                VariablesCaja.vFormasPagoImpresion += ", " + ((String)tblDetallePago.getValueAt(i, 1)).trim();
            }
        }

        String codFormaPagoDolares = getCodFormaPagoDolares();
        String codFormaPago = "";
        if (codFormaPagoDolares.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
            log.debug("La Forma de Pago Dolares esta Inactiva");
        } else {
            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                codFormaPago = ((String)tblDetallePago.getValueAt(i, 0)).trim();
                if (codFormaPagoDolares.equalsIgnoreCase(codFormaPago))
                    VariablesCaja.vFormasPagoImpresion =
                            VariablesCaja.vFormasPagoImpresion + "  Tipo Cambio:  " + VariablesCaja.vValTipoCambioPedido;
            }
        }
    }

/***********************************************************************************************************************/
    public String getCodFormaPagoDolares() {
        String codFP = "";
        try {
            codFP = BDNuevoCobro.getCodFPDolares();
        } catch (SQLException ex) {
            log.error("", ex);
            FarmaUtility.showMessage(this, "Error al Obtener el codidgo de Forma de Pago Dolares.\n" +
                    ex.getMessage(), tblFormasPago);
        }
        log.debug("jcallo: codforma de pago dolares " + codFP);
        return codFP;
    }

    /***********************************************************************************************************************/
    private void actualizaClientePedido(String pNumPedVta, String pCodCliLocal) throws SQLException {
        log.debug("Actualiza Datos del Cliente (PROCESO GENERAL)");

        DBCaja.actualizaClientePedido(pNumPedVta, pCodCliLocal, VariablesVentas.vNom_Cli_Ped,
                                      VariablesVentas.vDir_Cli_Ped, VariablesVentas.vRuc_Cli_Ped);
    }

    /***********************************************************************************************************************/
    private void colocaVueltoDetallePago(String pVuelto) {
        if (tblDetallePago.getRowCount() <= 0) {
            return;
        }
        boolean existeSoles = false;
        boolean existeDolares = false;
        int filaSoles = 0;
        int filaDolares = 0;
        for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
            String temp = (String)tblDetallePago.getValueAt(i, 0);
            if (temp != null)
                temp = temp.trim();
            else
                temp = "";

            if (ConstantsCaja.FORMA_PAGO_EFECTIVO_SOLES.equalsIgnoreCase(temp)) {
                existeSoles = true;
                filaSoles = i;
                break;
            } else if (ConstantsCaja.FORMA_PAGO_EFECTIVO_DOLARES.equalsIgnoreCase(temp)) {
                existeDolares = true;
                filaDolares = i;
            }
        }
        if (existeSoles) {
            tblDetallePago.setValueAt(pVuelto, filaSoles, 7);
        } else if (existeDolares && !existeSoles) {
            tblDetallePago.setValueAt(pVuelto, filaDolares, 7);
        }
        tblDetallePago.repaint();
        log.warn("detalle del pago cobrado", tblDetallePago);
    }
    /***********************************************************************************************************************/

    /**
     * metodo encargado de obtener el dni del cliente fidelizado que realizo la compra
     * */
    private String obtenerDniClienteFidelizado(String nroPedido) {
        String dniClienteFid = "";
        try {
            dniClienteFid = DBCaja.obtieneDniClienteFidVenta(nroPedido).trim();
        } catch (Exception e) {
            dniClienteFid = "";
            log.error("error al obtener DNI cliente del pedido " + e);
        }
        return dniClienteFid;
    }

    /***********************************************************************************************************************/
    private String procesarCobro_Fase1(String dniClienteFidelizado) {
        String resultado;
        try {
            //verificar si es pedido por convenio
            String pIndPedConvenio = BDNuevoCobro.getIndPedConvenio(VariablesCaja.vNumPedVta);
            VariablesVentas.vEsPedidoConvenio = (pIndPedConvenio.equals("S")) ? true : false;
            //obtiene la descrip de la formas de pago para la impresion
            formasPagoImpresion();
            //actualiza datos del cliente como nombre direccion ruc, etc
            ArrayList<String> datosClientePedido_up = new ArrayList();
            if (!VariablesVentas.vCod_Cli_Local.equalsIgnoreCase("")) {
                datosClientePedido_up = UtilityNuevoCobro.recuperaDatosClientePedido();
            }
            //Se consulta para obtener los cupones usados en el pedido
            //USO DE CUPONES
            VariablesCaja.listCuponesUsadosPedido =
                    (new UtilityVentas()).getCuponesUsadosPedidos(this, VariablesCaja.vNumPedVta, tblFormasPago);
            if (VariablesCaja.listCuponesUsadosPedido == null) {
                VariablesCaja.listCuponesUsadosPedido = new ArrayList();
                return "ERROR";
            }
            //La formas de pago, se graban antes de validar.
            //obtiene el monto del vuelto
            String vueltoPedido = lblVuelto.getText().trim();
            colocaVueltoDetallePago(vueltoPedido);
            log.warn("-------------Finaliza coloca vuelto-------------");
            //detalle de formas de pago
            VariablesCaja.vDescripcionDetalleFormasPago = "";
            VariablesCaja.vDescripcionDetalleFormasPago = ConstantsCaja.COLUMNAS_DETALLE_FORMA_PAGO;
            ArrayList<ArrayList> datosFormaPagoPedido_in = new ArrayList<ArrayList>();
            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                ArrayList parametros = new ArrayList();
                parametros =
                        UtilityNuevoCobro.recuperaFormaPagoPedido(((String)tblDetallePago.getValueAt(i, 0)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 4)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 6)).trim(),
                                                                  VariablesCaja.vValTipoCambioPedido,
                                                                  ((String)tblDetallePago.getValueAt(i, 7)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 5)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 8)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 9)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 10)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 2)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 12)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 13)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 14)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 15)).trim());
                datosFormaPagoPedido_in.add(parametros);
                VariablesCaja.vDescripcionDetalleFormasPago =
                        VariablesCaja.vDescripcionDetalleFormasPago + FarmaUtility.getValueFieldJTable(tblDetallePago,
                                                                                                       i, 0) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 1) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 3) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 4) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 5) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 7) + "<BR>";
            }
            /**/
            ArrayList<String> datosCobroPedido_in = null;
            if (isCovenio) {
                datosCobroPedido_in = UtilityNuevoCobro.datosCobroPedido_Concenio();
            } else {
                //String resultado = DBCaja.cobraPedido(VariablesVentas.vTip_Comp_Ped, VariablesCaja.vPermiteCampaña.trim(), dniClienteFidelizado.trim());
                datosCobroPedido_in =
                        UtilityNuevoCobro.datosCobroPedido_gral(VariablesVentas.vTip_Comp_Ped, VariablesCaja.vPermiteCampaña.trim(),
                                                                dniClienteFidelizado.trim());
            }
            ArrayList<String> listaCuponesUsados = null;
            if(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL.equalsIgnoreCase(VariablesVentas.vTipoPedido)){
                listaCuponesUsados = new ArrayList<String> ();
            }else{
                listaCuponesUsados =
                    UtilityNuevoCobro.recuperaCuponesUsados(VariablesCaja.vNumPedVta, VariablesCaja.listCuponesUsadosPedido);
            }

            log.warn("Cobra pedido local: --> UtilityNuevoCobro.cobraPedido_Local");
            resultado =
                    UtilityNuevoCobro.cobraPedido_Local(datosClientePedido_up, datosFormaPagoPedido_in, datosCobroPedido_in,
                                                        listaCuponesUsados, VariablesCaja.vNumPedVta, tipoCobro);
            log.warn("Cobra pedido local: resultado ==> "+resultado);
            ////////////////////////////////// GRABA VENTA EN ORBIS
            FacadeLealtad facadeLealtad = new FacadeLealtad();
            if (facadeLealtad.registrarVenta(this, VariablesCaja.vNumPedVta)) {
                VariablesVentas.vResultadoORBIS = true;
            }
            ////////////////////////////////
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            resultado = "** " + ex.getMessage();
        }
        return resultado;
    }
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/
    /***********************************************************************************************************************/

    /*
    private String procesarCobro_Fase1_Convenio(String dato) {
        String resultado;
        try{

            String pIndPedConvenio = BDNuevoCobro.getIndPedConvenio(VariablesCaja.vNumPedVta);
            VariablesVentas.vEsPedidoConvenio = (pIndPedConvenio.equals("S")) ? true : false;
            formasPagoImpresion();
            ArrayList<String> datosClientePedido_up = new ArrayList();
            if (!VariablesVentas.vCod_Cli_Local.equalsIgnoreCase("")) {
                datosClientePedido_up = UtilityNuevoCobro.recuperaDatosClientePedido();
            }
            VariablesCaja.listCuponesUsadosPedido =
                    (new UtilityVentas()).getCuponesUsadosPedidos(this, VariablesCaja.vNumPedVta, tblFormasPago);
            if (VariablesCaja.listCuponesUsadosPedido == null) {
                VariablesCaja.listCuponesUsadosPedido = new ArrayList();
                return "ERROR";
            }
            String vueltoPedido = lblVuelto.getText().trim();
            colocaVueltoDetallePago(vueltoPedido);
            VariablesCaja.vDescripcionDetalleFormasPago = "";
            VariablesCaja.vDescripcionDetalleFormasPago = ConstantsCaja.COLUMNAS_DETALLE_FORMA_PAGO;
            ArrayList<ArrayList> datosFormaPagoPedido_in = new ArrayList<ArrayList>();
            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                ArrayList parametros = new ArrayList();
                parametros =
                        UtilityNuevoCobro.recuperaFormaPagoPedido(((String)tblDetallePago.getValueAt(i, 0)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 4)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 6)).trim(),
                                                                  VariablesCaja.vValTipoCambioPedido,
                                                                  ((String)tblDetallePago.getValueAt(i, 7)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 5)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 8)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 9)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 10)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 2)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 12)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 13)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 14)).trim(),
                                                                  ((String)tblDetallePago.getValueAt(i, 15)).trim());
                datosFormaPagoPedido_in.add(parametros);
                VariablesCaja.vDescripcionDetalleFormasPago =
                        VariablesCaja.vDescripcionDetalleFormasPago + FarmaUtility.getValueFieldJTable(tblDetallePago,
                                                                                                       i, 0) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 1) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 3) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 4) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 5) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 7) + "<BR>";
            }
            ArrayList<String> datosCobroPedido_in = UtilityNuevoCobro.datosCobroPedido_Concenio();

            ArrayList<String> listaCuponesUsados =
                UtilityNuevoCobro.recuperaCuponesUsados(VariablesCaja.vNumPedVta, VariablesCaja.listCuponesUsadosPedido);

            //resultado =  DBConvenioBTLMF.cobraPedido(datosClientePedido_up,datosFormaPagoPedido_in,
            resultado =
                    UtilityNuevoCobro.cobraPedido_Local(datosClientePedido_up, datosFormaPagoPedido_in, datosCobroPedido_in,
                                                        listaCuponesUsados, VariablesCaja.vNumPedVta, tipoCobro);

            } catch (Exception ex) {
                ex.printStackTrace();
                log.error(ex.getMessage());
                resultado = "ERROR";
            }
            return resultado;
    }
    */

/***********************************************************************************************************************/
    public void setIndFinalizaCobro(boolean b) {
        this.indFinalizaCobro = b;
    }

    private boolean finalizaCobroGeneral() {
        log.warn("***** PROCESO FINALIZAR COBRO *****");
        boolean isGraboPedido = false;
        //2. Actualiza forma de pago
        try {
            /* 
            //2.1 Valida formas de pago campana
            String vResValidaCampConFormaPAgo = DBCaja.verificaPagoUsoCampana(VariablesCaja.vNumPedVta);
            log.warn("verificaPagoUsoCampana ERROR:   " + vResValidaCampConFormaPAgo);
            if (vResValidaCampConFormaPAgo.trim().equalsIgnoreCase("ERROR")) {
                log.warn("verificaPagoUsoCampana ERROR");
                FarmaUtility.liberarTransaccion();
                //VariablesCaja.vIndPedidoCobrado = false;
                FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                        "NO SE PUEDE COBRAR.Porque el descuento usado no cumple con la forma de Pago.", tblFormasPago);
                return false;
            }
            //FarmaUtility.aceptarTransaccion();
            //2.2 Valida montos de pedido
            String v_Resultado = DBCaja.verificaPedidoFormasPago(VariablesCaja.vNumPedVta);
            log.warn("FORM_PAGO_PEDIDO ERROR:   " + v_Resultado);
            if (v_Resultado.trim().equalsIgnoreCase("ERROR")) {
                log.warn("FORM_PAGO_PEDIDO ERROR");
                FarmaUtility.liberarTransaccion();
                //VariablesCaja.vIndPedidoCobrado = false;
                FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                        "Los totales de formas de pago y cabecera no coinciden. \n" +
                        "Comuníquese con el Operador de Sistemas inmediatamente." + ". \n" +
                        "NO CIERRE LA VENTANA.", tblFormasPago);
                return false;
            }

            FarmaUtility.aceptarTransaccion(); */
            isGraboPedido = true;
            /*
            * @author LTAVARA
            * Validar si la variable COMPROBANTE ELECTRONICO esta activo
            * @since 17/09/2014
            */
            String pIsCompManual = DBCaja.getIndIngresoCompManual(VariablesCaja.vNumPedVta);
            if (pIsCompManual.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                if ((VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                     FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0) == false) {
                    /*
                    if (UtilityEposTrx.validacionEmiteElectronico()) {

                        //obtiene los comprobantes del pedido
                        if (UtilityCaja.obtieneCompPago()) {
                            for (int j = 0; j < VariablesVentas.vArray_ListaComprobante.size(); j++) {
                                //obtener  secComprobante por documento
                                VariablesCaja.vSecComprobante =
                                        ((String)((ArrayList)VariablesVentas.vArray_ListaComprobante.get(j)).get(1)).trim();

                                // obtener el numero del comprobante electrónico
                                VariablesCaja.vNumCompImprimir =
                                        UtilityEposTrx.getNumCompPagoE(VariablesVentas.vTip_Comp_Ped,
                                                                       VariablesCaja.vNumPedVta,
                                                                       VariablesCaja.vSecComprobante, "", false);

                                // KMONCADA 05.11.2014 VALIDACION DE HOMOLOGACION
                                if (VariablesCaja.vNumCompImprimir != "" || VariablesCaja.vNumCompImprimir != "0") {
                                    if (ConstantsPtoVenta.TIP_COMP_TICKET.equalsIgnoreCase(VariablesVentas.vTip_Comp_Ped)) {
                                        VariablesVentas.vTip_Comp_Ped = ConstantsPtoVenta.TIP_COMP_BOLETA;
                                    } else {
                                        if (ConstantsPtoVenta.TIP_COMP_TICKET_FACT.equalsIgnoreCase(VariablesVentas.vTip_Comp_Ped)) {
                                            VariablesVentas.vTip_Comp_Ped = ConstantsPtoVenta.TIP_COMP_FACTURA;
                                        }
                                    }
                                    String tipoCompPago =
                                        UtilityEposTrx.modificarTipCompPago(VariablesVentas.vTip_Comp_Ped,
                                                                            VariablesCaja.vSecComprobante);
                                    FarmaUtility.aceptarTransaccion();
                                } else {
                                    FarmaUtility.liberarTransaccion();
                                    FarmaUtility.showMessage(this,
                                                             "Error al cobrar pedido !\nEl numero de comprobante electronico no se encontro !",
                                                             tblFormasPago);
                                    return false;
                                }
                            }

                        }
                    }
                    */
                } //fin validacion electronica

            }
            //2.5 Evalua proceso de recarga virtual
            //ERIOS 19.11.2013 Se comentar por si algun dia cambian el procedimiento
            /*if(!vProcesoRecarga){
                anulaPedido();
                return false;
            }*/

            
        }
        catch (Exception ex) {
            //No continuar con la transaccion
            FarmaUtility.liberarTransaccion();
            log.error("", ex);
            FarmaUtility.showMessage(this, ex.getMessage(), tblFormasPago);
            if (isGraboPedido) {
                /*FarmaUtility.showMessage(this,
                                         "Error de comunicación con EPOS.\nPedido ya fue cobrado.\nSe encuentra pendiente de Impresión, verifique!!! ",
                                         tblFormasPago);*/
                return true;
            } else {
                //No continuar con la transaccion
                //FarmaUtility.liberarTransaccion();

                //FarmaUtility.showMessage(this, ex.toString(), tblFormasPago);
                return false;
            }
        }

        //2.5 Evalua proceso de recarga virtual
        //ERIOS 19.11.2013 Se comentar por si algun dia cambian el procedimiento
        /*if(!vProcesoRecarga){
            anulaPedido();
            return false;
        }*/
        // KMONCADA 28.11.2016 [FACTURACION ELECTRONICA 2.0]
        // VERIFICA QUE PEDIDO ESTA EN CONTINGENCIA PARA MOSTRARA MENSAJE DE ALERTA
        try{
            boolean isPedidoContingencia = DBCaja.isPedidoEnContingencia(FarmaVariables.vCodGrupoCia,  
                                                                         FarmaVariables.vCodLocal, 
                                                                         VariablesCaja.vNumPedVta);
            if(isPedidoContingencia){
                UtilityConvenioBTLMF.muestraMensajeImpresion(this, "", 0, "", VariablesCaja.vNumPedVta, true, false);
            }
        }catch(Exception ex){
            log.error("", ex);
        }
        
        //3. Imprimir Comprobante
        UtilityCaja.procesoImpresionComprobante(this, txtNroPedido, false);

        if (VariablesCaja.vIndPedidoConProdVirtual) {
            FarmaUtility.showMessage(this, "Comprobantes Impresos con Exito", tblFormasPago);
        }
        
        //4. verifica si existen pedidos pendientes de anulacion despues de N minutos
        UtilityCaja.verificaPedidosPendientes(this);

        //KMONCADA 21.11.2014
        //5. VERIFICA PEDIDOS ACTUALIZACION PEDIDOS DELIVERY INVOCA A UN HILO
        HiloActualizarProforma hilo =
            new HiloActualizarProforma(VariablesCaja.vNumPedVta, FarmaVariables.vCodLocal, FarmaVariables.vCodGrupoCia);
        hilo.start();
        return true;
    }

    private boolean finalizaCobroConvenio() {
        //1. Procesa pedido virtual
        log.debug("-------------------> FINALIZA COBRO CONVENIO <-------------------");
        //2. Actualiza forma de pago
        try {
            /*LTAVARA 22.09.2014
            //obtiene el monto del vuelto
            String vueltoPedido = lblVuelto.getText().trim();
            colocaVueltoDetallePago(vueltoPedido);

            //detalle de formas de pago
            VariablesCaja.vDescripcionDetalleFormasPago = "";
            VariablesCaja.vDescripcionDetalleFormasPago = ConstantsCaja.COLUMNAS_DETALLE_FORMA_PAGO;
            for (int i = 0; i < tblDetallePago.getRowCount(); i++)
            {
                    //grabar forma de pago del pedido

                //descripcion de la forma de pago en el detalle

                    DBCaja.grabaFormaPagoPedido(((String) tblDetallePago.getValueAt(i,0)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,4)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,6)).trim(),
                                           VariablesCaja.vValTipoCambioPedido,
                                           ((String) tblDetallePago.getValueAt(i,7)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,5)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,8)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,9)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,10)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,2)).trim(),

                                           ((String) tblDetallePago.getValueAt(i,12)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,13)).trim(),
                                           ((String) tblDetallePago.getValueAt(i,14)).trim());

                VariablesCaja.vDescripcionDetalleFormasPago = VariablesCaja.vDescripcionDetalleFormasPago +
                                                                                                          FarmaUtility.getValueFieldJTable(tblDetallePago, i, 0) + " , " +
                                                                                                          FarmaUtility.getValueFieldJTable(tblDetallePago, i, 1) + " , " +
                                                                                                          FarmaUtility.getValueFieldJTable(tblDetallePago, i, 3) + " , " +
                                                                                                          FarmaUtility.getValueFieldJTable(tblDetallePago, i, 4) + " , " +
                                                                                                          FarmaUtility.getValueFieldJTable(tblDetallePago, i, 5) + " , " +
                                                                                                          FarmaUtility.getValueFieldJTable(tblDetallePago, i, 7) + "<BR>";
            }*/
        
            //2.1 Valida montos de pedido
            String v_Resultado = DBCaja.verificaPedidoFormasPago(VariablesCaja.vNumPedVta);
            //v_Resultado = "ERROR";
            //DUBILLUZ REVISAR ERROR COBRO CONVENIO 2017.07.21
            if (v_Resultado.trim().equalsIgnoreCase("ERROR")) {
                FarmaUtility.liberarTransaccion();
                //VariablesCaja.vIndPedidoCobrado = false;
                FarmaUtility.showMessage(this, "El pedido no puede ser cobrado. \n" +
                        "Los totales de formas de pago y cabecera no coinciden. \n" +
                        "Comuníquese con el Operador de Sistemas inmediatamente." + ". \n" +
                        "NO CIERRE LA VENTANA.", tblFormasPago);
                return false;
            }

            FarmaUtility.aceptarTransaccion();
        } catch (SQLException e) {
            //No continuar con la transaccion
            log.error("", e);
            FarmaUtility.showMessage(this, "Error. No se pudo registrar una de las formas de pagos", tblFormasPago);
            return false;
        }
        
        // KMONCADA 28.11.2016 [FACTURACION ELECTRONICA 2.0]
        // VERIFICA QUE PEDIDO ESTA EN CONTINGENCIA PARA MOSTRARA MENSAJE DE ALERTA
        try{
            boolean isPedidoContingencia = DBCaja.isPedidoEnContingencia(FarmaVariables.vCodGrupoCia,  
                                                                         FarmaVariables.vCodLocal, 
                                                                         VariablesCaja.vNumPedVta);
            if(isPedidoContingencia){
                UtilityConvenioBTLMF.muestraMensajeImpresion(this, "", 0, "", VariablesCaja.vNumPedVta, true, false);
            }
        }catch(Exception ex){
            log.error("", ex);
        }
        
        //3. Imprimir Comprobante
        UtilityConvenioBTLMF.procesoImpresionComprobante(this, null, false);

        //4. verifica si existen pedidos pendientes de anulacion despues de N minutos
        UtilityCaja.verificaPedidosPendientes(this);

        //KMONCADA 21.11.2014
        //5. VERIFICA PEDIDOS ACTUALIZACION PEDIDOS DELIVERY INVOCA A UN HILO
        HiloActualizarProforma hilo =
            new HiloActualizarProforma(VariablesCaja.vNumPedVta, FarmaVariables.vCodLocal, FarmaVariables.vCodGrupoCia);
        hilo.start();
        
        return true;
    }

    private String getIndCommitAntesRecargar() {
        String ind;
        try {
            ind = DBCaja.obtieneIndCommitAntesdeRecargar().trim();
            log.debug("ind Impr Antes de Recargar" + ind);
        } catch (SQLException sql) {
            ind = "N";
            log.error("", sql);
        }

        return ind;
    }
}

