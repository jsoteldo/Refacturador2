package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.electronico.impresion.dao.ConstantesDocElectronico;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.convenioBTLMF.reference.FacadeConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.pinpad.reference.FacadePinpad;
import mifarma.ptoventa.puntos.reference.UtilityTransactionPuntos;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.test_desa.casos.DlgImp_CodBarra;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgNotaCreditoNueva extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    Frame myParentFrame;
    FarmaTableModel tableModel;
    private static final Logger log = LoggerFactory.getLogger(DlgNotaCreditoNueva.class);
    private final static int COL_COD_PROD = 0;
    private final static int COL_DESC_PROD = 1;
    private final static int COL_UND_MED = 2;
    private final static int COL_NOM_LAB = 3;
    private final static int COL_CANT_DISP = 4;
    private final static int COL_PREC_VTA = 5;
    private final static int COL_CANT_NC = 6;
    private final static int COL_FRAC_VTA = 7;
    private final static int COL_PREC_LTA = 8;
    private final static int COL_VAL_IGV = 9;
    private final static int COL_SEC_DET = 10;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JScrollPane scrListaProductos = new JScrollPane();
    private JTable tblListaProductos = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JButtonLabel btnRelacionProductos = new JButtonLabel();
    private JLabelFunction lblF11 = new JLabelFunction();

    private FacadeConvenioBTLMF facadeConvenioBTLMF = new FacadeConvenioBTLMF();
    private FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();

    private String[] pListaPedidos; //kmoncada 26.06.2014 almacena las notas de credito generadas en la anulacion
    //LTAVARA 2016.08.17 NC PARCIAL
    private boolean NCParcial =false; 
    private String  listProdNCParcial="N";
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgNotaCreditoNueva() {
        this(null, "", false);
    }

    public DlgNotaCreditoNueva(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            FarmaUtility.centrarVentana(this);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(705, 346));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Nueva Nota Credito");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlTitle1.setBounds(new Rectangle(10, 15, 680, 25));
        scrListaProductos.setEnabled(false);
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(590, 290, 85, 20));
        btnRelacionProductos.setText("Relacion de Productos de Nota Credito");
        btnRelacionProductos.setBounds(new Rectangle(5, 5, 235, 15));
        btnRelacionProductos.setMnemonic('R');
        btnRelacionProductos.setActionCommand("Relacion de Productos de Nota Credito");
        btnRelacionProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnRelacionProductos_keyPressed(e);
            }
        });
        btnRelacionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionProductos_actionPerformed(e);
            }
        });
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(445, 290, 135, 20));
        jContentPane.add(lblF11, null);
        jContentPane.add(lblEsc, null);
        pnlTitle1.add(btnRelacionProductos, null);
        jContentPane.add(pnlTitle1, null);
        this.getContentPane().add(jContentPane, BorderLayout.NORTH);
        scrListaProductos.getViewport().add(tblListaProductos, null);
        this.getContentPane().add(scrListaProductos, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        initTable();
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTable() {
        tableModel =
                new FarmaTableModel(ConstantsCaja.columnsListaProductosNotaCreditoNueva, ConstantsCaja.defaultValuesListaProductosNotaCreditoNueva,
                                    0);
        FarmaUtility.initSimpleList(tblListaProductos, tableModel,
                                    ConstantsCaja.columnsListaProductosNotaCreditoNueva);
        cargaListaProductos();
    }

    public void cargaListaProductos() {
        try {
            DBCaja.getListaDetalleNotaCredito(tableModel, VariablesCaja.vNumPedVta_Anul, VariablesCaja.vTipComp_Anul,
                                              VariablesCaja.vNumComp_Anul);
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al listar detalle nota credito :\n" +
                    e.getMessage(), null);
        }
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void btnRelacionProductos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(btnRelacionProductos);
    }

    private void btnRelacionProductos_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void this_windowOpened(WindowEvent e) {
        //LLEIVA 03-Ene-2014 Si el tipo de cambio es cero, no permitir continuar
        if (FarmaVariables.vTipCambio == 0) {
            FarmaUtility.showMessage(this,
                                     "ATENCIÓN: No se pudo obtener el tipo de cambio actual\nNo se puede continuar con la acción",
                                     null);
            cerrarVentana(false);
        } else {
            FarmaUtility.centrarVentana(this);
            FarmaUtility.moveFocus(btnRelacionProductos);
            tblListaProductos.setEnabled(false);
            presionarF11();/*
            String ind1 = "";
            String ind2 = "" ;
            try{
                ind1 = DBCaja.recuperaIndicador_AnulaOrviis();
                ind2 = DBCaja.GET_IND_AUTO_ANUL_ORVIIS();
                FarmaUtility.showMessage(this,"IND1: "+ind1+"\nIND2: "+ind2,null);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            if(ind1.equalsIgnoreCase("S")){
                anulacionManual();
            }else{
                presionarF11();
            }
            /* */
        }
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */
    // KMONCADA 18.11.2016 [FACTURACION ELECTRONICA]
    // TODOS LOS ERRORES SE CONTROLARAN EN ESTE METODO.
    private void presionarF11() {
        boolean isAnuloLocal = false;
        boolean isTerminoCorrecto = false;
        boolean previaAnulacion=true;
        UtilityTransactionPuntos trnxPuntos=null;  
        String errorConstraintValorFrac1="";  //JVARA 18-08-2017 
        String errorConstraintValorFrac2="";  //JVARA 18-08-2017 
        try{
            trnxPuntos = new UtilityTransactionPuntos(VariablesCaja.vNumPedVta_Anul);            
            //UNA NC PARCIAL NO SE ENVIA A ORBIS
            if(!isNCParcial()){
                /*
                * previoAnulaPuntos
                * anulaOrbis
                 * */
                 previaAnulacion =trnxPuntos.previoAnulaPuntos();
                 log.info("1. previaAnulacion: "+previaAnulacion);
                 log.info("1. codAnul_Orviis: "+VariablesCaja.codAnul_Orviis);
                 log.info("1. ptoMsj_Orviis: "+VariablesCaja.ptoMsj_Orviis);
            }
            
            if (previaAnulacion) {
                //LTAVARA 2016.08.19 SELECCIONAR LOS PRODUCTOS PARA GENERAR LA NC PARCIAL
             
                if (isNCParcial()){
                      DBCaja.seleccionarProdNCParcial(VariablesCaja.vNumPedVta_Anul,listProdNCParcial);
                      FarmaUtility.aceptarTransaccion(); //PRUEBA 2016.18.08
                }
                
                if (validaDatos()) {
                    Map vtaPedido = (Map)UtilityConvenioBTLMF.obtieneConvenioXpedido(VariablesCaja.vNumPedVta_Anul, this);
                    String indConvenioBTLMF = (String)vtaPedido.get("IND_CONV_BTL_MF");
                    boolean esConvenioBTLMF = UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null);
                    boolean esCompCredito = false;
    
                    /** 1.- REALIZA LA ANULACION EN EL LOCAL **/
                    if (esConvenioBTLMF && indConvenioBTLMF != null && indConvenioBTLMF.equals("S")){
                        esCompCredito = UtilityConvenioBTLMF.esCompCredito(this);
                        if (esCompCredito) {
                            //ANULACION PARCIAL, no procede con las ventas convenios a credito 
                            if(isNCParcial()){
                                    throw new Exception(
                                            "Error en el proceso de ANULACION PARCIAL,\n" +
                                            "no procede en las ventas convenio con credito.\n" +
                                            "Por favor llame a mesa de ayuda");
                            }
                        }
                        //JVARA 18-08-2017    INICIO
                       
                        try {
                        
                        isAnuloLocal = grabarBTLMF(esCompCredito);
                            
                        } catch (SQLException sql) {
                            
                            log.debug("seguimiento codigo de error de sql " + sql);
                            
                            if(sql!=null && (sql.getErrorCode()==2290||sql.getErrorCode()==20850 || sql.getErrorCode()==20058)) {
                                 errorConstraintValorFrac1="S"; 
                            } 
                            throw new Exception("error "+sql.getMessage());
                        }
                        
                        
                    } else {
                        
                        try {
                        isAnuloLocal = grabar();
                            
                        } catch (SQLException sql) {
                            log.debug("seguimiento codigo de error de sql " + sql);                            
                                if(sql!=null && (sql.getErrorCode()==2290||sql.getErrorCode()==20850 || sql.getErrorCode()==20058)) {
                                 errorConstraintValorFrac2="S";                                 
                            }
                                
                            throw new Exception("error "+sql.getMessage());
                            
                        }
                        
                        //JVARA 18-08-2017    FIN   
                    }
    
                    
                    /*log.error("", sqle);
                    FarmaUtility.aceptarTransaccion();
                    FarmaUtility.showMessage(this, "Se presento un error en la anulacion en RAC.\n"+
                                                   "Presente [Enter] para continuar con el proceso de anulación.\n"+
                                                   sqle.getMessage(), tblListaProductos);*/
                    
                    ////////////////////////////////////////////
                    ////////////////////////////////////////////
                    if(isAnuloLocal){
                            if(UtilityCaja.getPermiteSaveNotaCredito(FarmaVariables.vCodGrupoCia,
                                                 FarmaVariables.vCodLocal,
                                                 VariablesCaja.vNumPedVta_Anul,
                                                 ConstantesDocElectronico.lstPedidos)){
                            isAnuloLocal = true;
                            }
                            else{
                            FarmaUtility.liberarTransaccion();
                                isAnuloLocal = false;
                            throw new Exception("No puede generar una nota de crédito de un pedido mas de una vez.");
                            
                            }
                    }
                    ////////////////////////////////////////////
                    ////////////////////////////////////////////
                    
                    if (isAnuloLocal) {
                        /*
                        try {
                            for (int i = 0; i < pListaPedidos.length; i++) {
                                String pNumPedNC = pListaPedidos[i].trim();
                                UtilityCaja.procesarNrosComprobantesPago(this, pNumPedNC, "04");
                            }
                        } catch (Exception ex) {
                            throw new Exception("Comunicación con Servidor EPOS:\n" +
                                                "Error al procesar los comprobantes de pago electronicos.\n" +
                                                ex.getMessage());
                        }
                        */
                        /** 2.- PROCESAR ANULACION EN EL RAC **/
                        if (esCompCredito) {
                            try {
                                //VariablesCaja.vNumPedVta = VariablesCaja.vNumPedVta_Anul;
                                //KMONCADA 26.06.2014 GRABA CONEXION DIRECTA PERO DE LOS NUM_PEDIDOS DE NOTA DE CREDITO GENERADOS
                                String rptaMatriz = "";
                                String pLstPedidosNC = "";
                                for (int k = 0; k < pListaPedidos.length; k++) { //kmoncada
                                    String numPedVtaNC = pListaPedidos[k].toString();
                                    //rptaMatriz = facadeConvenioBTLMF.grabarTemporalesRAC(numPedVtaNC, FarmaConstants.INDICADOR_S);
                                    rptaMatriz = facadeConvenioBTLMF.grabarPedidVta_RAC(numPedVtaNC,FarmaConstants.INDICADOR_S);
                                    if(k==0)
                                        pLstPedidosNC = numPedVtaNC;
                                    else
                                        pLstPedidosNC = pLstPedidosNC+"@"+numPedVtaNC;
                                    
                                    if (!"S".equals(rptaMatriz))
                                        throw new Exception("Error al grabar temporales en el RAC.\n" +
                                                            rptaMatriz);
                                }
                                
                                rptaMatriz = facadeConvenioBTLMF.grabarAnulacionPedidoRAC(FarmaVariables.vCodGrupoCia, 
                                                                                          FarmaVariables.vCodLocal,
                                                                                          VariablesCaja.vNumPedVta_Anul,
                                                                                          pLstPedidosNC);
                                if ("S".equalsIgnoreCase(rptaMatriz)) {
                                    FarmaUtility.aceptarTransaccion();
                                    for (int k = 0; k < pListaPedidos.length; k++) { 
                                        String numPedVtaNC = pListaPedidos[k].toString();
                                        facadeConvenioBTLMF.actualizaFechaProcesoRac(FarmaVariables.vCodGrupoCia,
                                                                                     FarmaVariables.vCodLocal,
                                                                                     numPedVtaNC);
                                    }
                                } else {
                                    throw new Exception("Error al anular pedido en el RAC\n" + rptaMatriz);
                                }
                            } catch (Exception sqle) {
                                if(UtilityConvenioBTLMF.isObligatorioAnularRaC()){
                                    throw new Exception(sqle.getMessage());
                                }else{
                                    log.error("", sqle);
                                    FarmaUtility.aceptarTransaccion();
                                    FarmaUtility.showMessage(this, "Se presento un error en la anulacion en RAC.\n"+
                                                                   "Presente [Enter] para continuar con el proceso de anulación.\n"+
                                                                   sqle.getMessage(), tblListaProductos);
                                }
                                
                            }
                        } else {
                            
                            if(UtilityCaja.getPermiteSaveNotaCredito(FarmaVariables.vCodGrupoCia,
                                                 FarmaVariables.vCodLocal,
                                                 VariablesCaja.vNumPedVta_Anul,
                                                 ConstantesDocElectronico.lstPedidos)){
                                isAnuloLocal = true;
                                FarmaUtility.aceptarTransaccion();
                            }
                            else{
                            FarmaUtility.liberarTransaccion();
                            //FarmaUtility.showMessage(this, "No puede generar una nota de crédito de un pedido mas de una vez.",tblListaProductos);
                                isAnuloLocal = false;
                                throw new Exception("No puede generar una nota de crédito de un pedido mas de una vez.");
                            }
                            
                            
                        }
                    //}
                    log.info("Genero Nota credito satisfactoriamente? " + isAnuloLocal);
                    //if (isAnuloLocal) {
                        isTerminoCorrecto  = true;
                        // SI YA GENERO LA NOTA DE CREDITO YA SE DEBE DE ANULAR EN ORBIS
                        // LAIS INDICO QUE SEGUN LO DEFINIDO Y CONVERSADO
                        // DEBE DE HACERSE EL REGISTRO DE ANULACION A ORBIS SI ES QUE SE GRABO Y COMMIT EN EL LOCAL.
                        // dubilluz 16.02.2015
                        try {
                            if (trnxPuntos.isVAnulaEnOrbis() && !trnxPuntos.isVIndDescartaPedidoOrbis()) {
                                //NO TIENE QUE SER DESCARTADA
                                trnxPuntos.anulaOrbis();
                                if (trnxPuntos.isVIndDescartaPedidoOrbis()) {
                                    trnxPuntos.descartaAnulacionOrbis();
                                } else if (trnxPuntos.isVIndDescartaNCnoPedidoOrbis()) {
                                    //solo descarta origen
                                    trnxPuntos.descartaNCnoOrigen();
                                } else
                                    trnxPuntos.saveTrxAnulOrbis();
                                
                                //FarmaUtility.aceptarTransaccion();
                                if(UtilityCaja.getPermiteSaveNotaCredito(FarmaVariables.vCodGrupoCia,
                                                     FarmaVariables.vCodLocal,
                                                     VariablesCaja.vNumPedVta_Anul,
                                                     ConstantesDocElectronico.lstPedidos)){
                                    isAnuloLocal = true;
                                    FarmaUtility.aceptarTransaccion();
                                }
                                else{
                                FarmaUtility.liberarTransaccion();
                                //FarmaUtility.showMessage(this, "No puede generar una nota de crédito de un pedido mas de una vez.",tblListaProductos);
                                    isAnuloLocal = false;
                                    throw new Exception("No puede generar una nota de crédito de un pedido mas de una vez.");
                                }
                               
                                trnxPuntos.imprimeVariables();
                                trnxPuntos.reset();
                                /*** INICIO ARAVELLO 23/09/2019 ***/
                                /*FarmaUtility.showMessage(this, "¡Nota Credito generada satisfactoriamente!",
                                                         btnRelacionProductos);*/
                                /*** FIN    ARAVELLO 23/09/2019 ***/
                            } else {
                                if (trnxPuntos.isVIndDescartaPedidoOrbis()) {
                                    trnxPuntos.descartaAnulacionOrbis();
                                    
                                    
                                    //FarmaUtility.aceptarTransaccion();
                                    if(UtilityCaja.getPermiteSaveNotaCredito(FarmaVariables.vCodGrupoCia,
                                                         FarmaVariables.vCodLocal,
                                                         VariablesCaja.vNumPedVta_Anul,
                                                         ConstantesDocElectronico.lstPedidos)){
                                        isAnuloLocal = true;
                                        FarmaUtility.aceptarTransaccion();
                                    }
                                    else{
                                    FarmaUtility.liberarTransaccion();
                                    //FarmaUtility.showMessage(this, "No puede generar una nota de crédito de un pedido mas de una vez.",tblListaProductos);
                                        isAnuloLocal = false;
                                        throw new Exception("No puede generar una nota de crédito de un pedido mas de una vez.");
                                    }                                    
                                    
                                    trnxPuntos.imprimeVariables();
                                    trnxPuntos.reset();
                                }
                                FarmaUtility.showMessage(this, "¡Nota Credito generada satisfactoriamente!",
                                                         btnRelacionProductos);
                            }
                        } catch (Exception e) {
                            FarmaUtility.liberarTransaccion();
                            log.error("", e);
                            FarmaUtility.showMessage(this, "Se generó con la éxito la nota de crédito" + "\n" +
                                    "No se pudo procesar la anulación de Puntos:" + "\n" +
                                    e.getMessage() + "", btnRelacionProductos);
                        }
    
                        // dubilluz 16.02.2015
                        // SI FUESE NECESARIO
                        //JCHAVEZ 10.07.2009.sn
                        try {
                            if (esConvenioBTLMF && indConvenioBTLMF != null && indConvenioBTLMF.equals("S")) {
                                if (UtilityConvenioBTLMF.imprimeTicketBTLMF(this, VariablesCaja.vNumPedVta_Anul,
                                                                            VariablesCaja.vNumCaja,
                                                                            VariablesCaja.vNumTurnoCaja, pListaPedidos)) {
                                    //LAIS SOLICITA ESTA CAMBIO , 08.05.2015
                                    //FarmaUtility.showMessage(this, "La nota de crédito se ha reimpreso con éxito .", null);
                                    /*** INICIO ARAVELLO 23/09/2019 ***/ //Comentado
                                    //FarmaUtility.showMessage(this, "La nota de crédito se ha impreso con éxito .", null);
                                    /*** FIN    ARAVELLO 23/09/2019 ***/
                                }
                            } else {
                                if (UtilityCaja.getImpresionTicketAnulado(this, VariablesCaja.vNumPedVta_Anul, "",
                                                                          VariablesCaja.vNumCaja,
                                                                          VariablesCaja.vNumTurnoCaja, pListaPedidos)) {
                                    //LAIS SOLICITA ESTA CAMBIO , 08.05.2015
                                    //FarmaUtility.showMessage(this, "La nota de crédito se ha reimpreso con éxito .", null);
                                    /*** INICIO ARAVELLO 23/09/2019 ***///Comentario
                                    //FarmaUtility.showMessage(this, "La nota de crédito se ha impreso con éxito .", null);
                                    /*** FIN    ARAVELLO 23/09/2019 ***/
                                }
                            }
                            
                            if(UtilityCaja.getPermiteSaveNotaCredito(FarmaVariables.vCodGrupoCia,
                                                 FarmaVariables.vCodLocal,
                                                 VariablesCaja.vNumPedVta_Anul,
                                                 ConstantesDocElectronico.lstPedidos)){
                                isAnuloLocal = true;
                                FarmaUtility.aceptarTransaccion();
                            }
                            else{
                            FarmaUtility.liberarTransaccion();
                            //FarmaUtility.showMessage(this, "No puede generar una nota de crédito de un pedido mas de una vez.",tblListaProductos);
                                isAnuloLocal = false;
                                throw new Exception("No puede generar una nota de crédito de un pedido mas de una vez.");
                            }
                            //FarmaUtility.aceptarTransaccion();
                            
                        } catch (Exception e) {
                            log.error("", e);
                            FarmaUtility.liberarTransaccion();
                            FarmaUtility.enviaCorreoPorBD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                                    //"dubilluz",
                                    VariablesPtoVenta.vDestEmailErrorAnulacion, "Error de Impresión La nota de crédito",
                                    "Error de Impresión Nota de Credito Anulado",
                                    "Error al imprimir ticket Anulado :<br>" + "Correlativo : " +
                                    VariablesCaja.vNumPedVta_Anul + "<br>" + "Error: " + e,
                                    //"joliva;operador;daubilluz@gmail.com");
                                    "");
                            FarmaUtility.showMessage(this, "Error al imprimir las NC .\n" +
                                    e.getMessage(), null);
                        }
                        //JCHAVEZ 10.07.2009.en
                        isTerminoCorrecto  = true;
                    } else {
                        // KMONCADA 09.10.2014 en caso de presentarse algun tipo de error
                        FarmaUtility.liberarTransaccion();
                        isTerminoCorrecto = false;
                    }
                }else{
                    isTerminoCorrecto = false;
                }
            } else {
                FarmaUtility.showMessage(this,
                                         "No puede anular el pedido \n porque presento problemas en la validación previa de puntos.",
                                         tblListaProductos);
                isTerminoCorrecto = false;
            }
            
        }catch (Exception ex) {
            log.error("", ex);
            FarmaUtility.liberarTransaccion();
            //JVARA 18-08-2017  Error en el proceso de Anulacion - Notas de Credito inicio
            log.info("2. previaAnulacion: "+previaAnulacion);
            log.info("2. codAnul_Orviis: "+VariablesCaja.codAnul_Orviis);
            log.info("2. ptoMsj_Orviis: "+VariablesCaja.ptoMsj_Orviis);
            
            if (errorConstraintValorFrac1 != null && errorConstraintValorFrac1.trim().equals("S") ||
                errorConstraintValorFrac2 != null && errorConstraintValorFrac2.trim().equals("S")) {

                FarmaUtility.showMessage(this, "Error,producto(s) ahora el local lo(s) vende(n) entero(s) \n",
                                         tblListaProductos);
            } else { //JVARA 18-08-2017  Error en el proceso de Anulacion - Notas de Credito fin
                if(VariablesCaja.codAnul_Orviis.trim().equalsIgnoreCase("11") //RARGUMEDO 12.04.18 Anulacion manual de orviis para error 11 y 15
                   || VariablesCaja.codAnul_Orviis.trim().equalsIgnoreCase("15")){
                    String indAnulManual_orviis="N";
                    //indAnulManual_orviis="S";/*
                    try {
                        indAnulManual_orviis = DBCaja.recuperaIndicador_AnulaOrviis();
                    } catch (SQLException e) {
                        log.debug("Error: IND_ANUL_ORVIIS\n"+e.getErrorCode());
                    }
                    /* */
                    if(indAnulManual_orviis.equalsIgnoreCase("S")){
                        anulacionManual();
                        /*
                        String msj="ERROR AL ANULAR LA VENTA:\n" +ex.getMessage()+"\n"+ 
                        "Desea proceder con la anulacion manual";
                        boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
                        if(ok){
                            anulacionManual();
                        }
                        /* */
                    }else{
                        FarmaUtility.showMessage(this, "Error en el proceso de Anulacion Orviis - Notas de Credito:\n" +
                                                 ex.getMessage()+"\n["+VariablesCaja.codAnul_Orviis+" - "+VariablesCaja.ptoMsj_Orviis+"]",
                                                 tblListaProductos);
                    }
                }else{
                    FarmaUtility.showMessage(this, "Error en el proceso de Anulacion - Notas de Credito:\n" +
                            ex.getMessage(), tblListaProductos);
                }
            }
            cerrarVentana(VariablesCaja.vCerrarVentanaTotalAnularPedido);
        }
        
        /** EN CASO SE HAYA GENERADO PEDIDOS PERO CAYO EL PROCESO DE ANUALACION, SE ANULARAN TODOS LOS PEDIDOS GENERADOS **/
        if(isAnuloLocal && !isTerminoCorrecto){
            for(int i = 0;i<pListaPedidos.length;i++){
                try{
                    UtilityCaja.actualizaEstadoPedido(pListaPedidos[i], ConstantsCaja.ESTADO_ANULADO);
                    FarmaUtility.aceptarTransaccion();
                }catch(Exception ex){
                    FarmaUtility.liberarTransaccion();
                    log.error("", ex);
                }
            }
        }
        cerrarVentana(isTerminoCorrecto);
    }
    
    private void presionarEnter() {
        if (tblListaProductos.getSelectedRow() > -1) {
            cargarCabecera();
            DlgNotaCreditoIngresoCantidad DlgNotaCreditoIngresoCantidad =
                new DlgNotaCreditoIngresoCantidad(myParentFrame, "", true);
            DlgNotaCreditoIngresoCantidad.setVisible(true);
            if (FarmaVariables.vAceptar) {
                actualizarProducto();
                FarmaVariables.vAceptar = false;
            }
        } else
            FarmaUtility.showMessage(this, "Debe seleccionar un producto", btnRelacionProductos);
    }

    private void chkKeyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblListaProductos, null, 0);

        if (mifarma.ptoventa.reference.UtilityPtoVenta.verificaVK_F11(e))
            try {
                presionarF11();/*
                anulacionManual();
                /* */
            } catch (Exception ev) {
                log.error("",ev);
                FarmaUtility.showMessage(this, "Error al intentar anular.\n"+ev.getMessage(),null);
                cerrarVentana(false);
            }
        //  else if(e.getKeyCode() == KeyEvent.VK_ENTER && (VariablesCaja.vTipComp.equals(ConstantsVentas.TIPO_COMP_FACTURA)))
        //     presionarEnter();
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            cerrarVentana(false);

    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void cargarCabecera() {
        int vFila = tblListaProductos.getSelectedRow();
        VariablesCaja.vCodProd_Nota = FarmaUtility.getValueFieldJTable(tblListaProductos, vFila, COL_COD_PROD);
        VariablesCaja.vNomProd_Nota = FarmaUtility.getValueFieldJTable(tblListaProductos, vFila, COL_DESC_PROD);
        VariablesCaja.vUnidMed_Nota = FarmaUtility.getValueFieldJTable(tblListaProductos, vFila, COL_UND_MED);
        VariablesCaja.vNomLab_Nota = FarmaUtility.getValueFieldJTable(tblListaProductos, vFila, COL_NOM_LAB);
        VariablesCaja.vCant_Nota = FarmaUtility.getValueFieldJTable(tblListaProductos, vFila, COL_CANT_DISP);
        VariablesCaja.vValFrac_Nota = FarmaUtility.getValueFieldJTable(tblListaProductos, vFila, COL_FRAC_VTA);
        VariablesCaja.vCantIng_Nota = FarmaUtility.getValueFieldJTable(tblListaProductos, vFila, COL_CANT_NC);
    }

    private void actualizarProducto() {
        int vFila = tblListaProductos.getSelectedRow();
        tblListaProductos.setValueAt(VariablesCaja.vCantIng_Nota, vFila, COL_CANT_NC);
        tblListaProductos.repaint();
    }

    private boolean validaDatos() {
        boolean retorno = true;
        if (tblListaProductos.getRowCount() == 0) {
            FarmaUtility.showMessage(this, "No ha ingresado productos a esta Nota Credito.", btnRelacionProductos);
            retorno = false;
        } else if (FarmaUtility.sumColumTable(tblListaProductos, COL_CANT_NC) == 0 &&
                   (VariablesCaja.vTipComp.equals(ConstantsVentas.TIPO_COMP_FACTURA))) {
            FarmaUtility.showMessage(this, "No ha ingresado productos a esta Nota Credito.", btnRelacionProductos);
            retorno = false;
        }
        return retorno;
    }
    
    private boolean grabarBTLMF(boolean conexionRAC) throws Exception {
        // KMONCADA 18.11.2016 LOS ERRORES SE CONTROLORAN DESDE EL METODO QUE INVOCA
        boolean retorno = true;
        String pDatosDel = FarmaConstants.INDICADOR_N;
        
        //try {
        pDatosDel = DBCaja.getDatosPedDelivery(VariablesCaja.vNumPedVta_Anul);
        //Esta decision fue tomada por gerencia
        //dubilluz 02.12.2008
        //DBCaja.activarCuponesUsados(VariablesCaja.vNumPedVta_Anul);
        DlgDetalleAnularPedido dlgDetalleAnularPedido = new DlgDetalleAnularPedido(myParentFrame, "", true, true);
        dlgDetalleAnularPedido.setNCParcial(isNCParcial());//LTAVARA 2016.08.19 NC PARCIAL 
        dlgDetalleAnularPedido.setVisible(true);
        if (FarmaVariables.vAceptar) {
            //INI ASOSA - 11/12/2014 - RECAR
            boolean isAnulOk = dlgDetalleAnularPedido.isAnul();
            if (!isAnulOk) {
                return false;
            }
            //FIN ASOSA - 11/12/2014 - RECAR
            //ERIOS 29.05.2015 Al fallar la anulacion de CMR, detiene la NCR.
            if (!dlgDetalleAnularPedido.anularTransaccionCMR()) {
                return false;
            }

            if (dlgDetalleAnularPedido.anularPedidofidelizado(FarmaConstants.INDICADOR_S)) { // LTAVARA 10.09.2014
                log.debug("Valor de vMotivoAnulacion dentro del metodo grabar: " + VariablesCaja.vMotivoAnulacion);

                (new FacadeRecaudacion()).obtenerTipoCambio();
                String tipoCambio = FarmaVariables.vTipCambio + "";
                
                //KMONCADA 10.05.2016 CUPONES EN CONVENIO
                DBCaja.activarCuponesUsados(VariablesCaja.vNumPedVta_Anul);

                // NUEVO METODO DE GENERACION DE NOTA DE CREDITO
                // REVISAR LA GENERACION COMPLETA DE LOS PEDIDOS CORRECTAMENTE
                // DUBILLUZ 27.09.2013
                /********************* INICIO DE CAMBIO ********************************/
                //dubilluz 27.09.2013 retorna el conjunto de pedidos generados por la NOTA DE CREDITO
                String nroPedidoNC = "";
                if(conexionRAC){
                    nroPedidoNC = facadeConvenioBTLMF.agregarCabeceraNotaCredito(VariablesCaja.vNumPedVta_Anul, tipoCambio, VariablesCaja.vMotivoAnulacion);
                }else{
                    nroPedidoNC = DBCaja.agregarCabeceraNotaCredito(VariablesCaja.vNumPedVta_Anul, tipoCambio, VariablesCaja.vMotivoAnulacion);
                }
                
                if(nroPedidoNC == null)
                    nroPedidoNC = "";
                if(nroPedidoNC.trim().length()==0)
                    throw new Exception("Anulación Convenio en Local:\nNo se han generado pedidos. Pedido: "+VariablesCaja.vNumPedVta_Anul);

                pListaPedidos = nroPedidoNC.split("@");
                //CHUANES
                //SE OBTIENE LA LISTA QUE ALAMACENA LOS NUMEROS DE PEDIDOS
                ConstantesDocElectronico.lstPedidos = pListaPedidos;
                log.info(">> cantidad de pedidos generados de NC>>" + pListaPedidos.length);
                String numera = "";
                for (int i = 0; i < pListaPedidos.length; i++) {
                    numera = pListaPedidos[i].toString();
                    if(i==0){
                        DBCaja.anularCuponesNC(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, VariablesCaja.vNumPedVta_Anul, numera, FarmaVariables.vIdUsu);
                    }
                    // los parametros de monto,cantidad,total,items NO NECESITA porque se va sacar la copia del ORIGINAL de La NC
                    //ERIOS 2.4.3 Indica el secuencial de NC
                    
                    /////JVARA ACA SE CAE SOBRE EL TEMA DEL CONSTRAINT CK_CANT_LOCAL
                    String nroNC = DBCaja.agregarDetalleNotaCredito(VariablesCaja.vNumPedVta_Anul, 
                                                                 numera, 
                                                                 "0", 
                                                                 "0", 
                                                                 "0.0", 
                                                                 (i+"")); 
                    
                    nroNC = nroNC.trim();
                    if ("N".equalsIgnoreCase(nroNC)) {
                        throw new Exception("Detalle Anulación Convenio en Local: \n ERROR AL GENERAR LA NOTA DE CREDITO DEL PEDIDO "+VariablesCaja.vNumPedVta_Anul);
                    }
                }
                if (VariablesCaja.vIndPedidoConProdVirtual && !VariablesCaja.vIndAnulacionConReclamoNavsat) {
                    UtilityCaja.actualizaInfoPedidoVirtualAnulado(VariablesCaja.vNumPedVta_Anul);                        
                }
                retorno = true;
                /********************* FIN   DE CAMBIO ********************************/
            } //fin if (dlgDetalleAnularPedido.anularPedidofidelizado(FarmaConstants.INDICADOR_S))
        } else {
            retorno = false;
        }
        return retorno;
    }

    public boolean grabar() throws Exception{
        // KMONCADA 18.11.2016 LOS ERRORES SE CONTROLORAN DESDE EL METODO QUE INVOCA
        boolean retorno = true;
        
        String pDatosDel = FarmaConstants.INDICADOR_N;
        //try {
        pDatosDel = DBCaja.getDatosPedDelivery(VariablesCaja.vNumPedVta_Anul);
        
        //KMONCADA 13.04.2015 SE COMENTA YA QUE SE REALIZA UN ROLLBACK EN OTRA VENTANA
        //Esta decision fue tomada por gerencia
        //dubilluz 02.12.2008
        //DBCaja.activarCuponesUsados(VariablesCaja.vNumPedVta_Anul);

        DlgDetalleAnularPedido dlgDetalleAnularPedido;
        dlgDetalleAnularPedido = new DlgDetalleAnularPedido(myParentFrame, "", true, true);
        dlgDetalleAnularPedido.setNCParcial(isNCParcial());//LTAVARA 2016.08.19 NC PARCIAL 
        dlgDetalleAnularPedido.setVisible(true);


        if (FarmaVariables.vAceptar) {
            //INI ASOSA - 11/12/2014 - RECAR
            boolean isAnulOk = FarmaVariables.vAceptar;//dlgDetalleAnularPedido.isAnul();

            if (!isAnulOk) {
                return false;
            }
            //FIN ASOSA - 11/12/2014 - RECAR
            //ERIOS 29.05.2015 Al fallar la anulacion de CMR, detiene la NCR.
            if (!dlgDetalleAnularPedido.anularTransaccionCMR()) {
                return false;
            }
            
            ArrayList vListPedidoNotaCredito = new ArrayList();

            if (dlgDetalleAnularPedido.anularPedidofidelizado(FarmaConstants.INDICADOR_S)) { // LTAVARA 10.09.2014
                log.debug("Valor de vMotivoAnulacion dentro del metodo grabar: " + VariablesCaja.vMotivoAnulacion);

                (new FacadeRecaudacion()).obtenerTipoCambio();
                String tipoCambio = FarmaVariables.vTipCambio + "";
                
                //KMONCADA 13.04.2015 SE REALIZA EL CAMBIO DE POSICION YA QUE ANTES NO SE EJECUTABA POR UN ROLLBACK INVOLUNTARIO
                //Esta decision fue tomada por gerencia
                //dubilluz 02.12.2008
                DBCaja.activarCuponesUsados(VariablesCaja.vNumPedVta_Anul);

                // NUEVO METODO DE GENERACION DE NOTA DE CREDITO
                // REVISAR LA GENERACION COMPLETA DE LOS PEDIDOS CORRECTAMENTE
                // DUBILLUZ 27.09.2013
                /********************* INICIO DE CAMBIO ********************************/
                //dubilluz 27.09.2013 retorna el conjunto de pedidos generados por la NOTA DE CREDITO
                pListaPedidos = DBCaja.agregarCabeceraNotaCredito(VariablesCaja.vNumPedVta_Anul, tipoCambio, VariablesCaja.vMotivoAnulacion).split("@");
                //CHUANES
                //SE OBTIENE LA LISTA QUE ALAMACENA LOS NUMEROS DE PEDIDOS
                ConstantesDocElectronico.lstPedidos = pListaPedidos;
                log.info(">> cantidad de pedidos generados de NC>>" + pListaPedidos.length);
                log.debug(">> >>" + pListaPedidos.toString());
                String numera = "";
                boolean nroCorrecto = true;
                for (int i = 0; i < pListaPedidos.length; i++) {
                    numera = pListaPedidos[i].toString();
                    log.debug("numera>>" + numera);
                    // los parametros de monto,cantidad,total,items NO NECESITA porque se va sacar todo la copia del ORIGINAL de La NC
                    //dubilluz 27.09.2013                    
                    /////JVARA ACA SE CAE SOBRE EL TEMA DEL CONSTRAINT CK_CANT_LOCAL                    
                    String nroNC = DBCaja.agregarDetalleNotaCredito(VariablesCaja.vNumPedVta_Anul, numera, "0", "0", "0.0",i + "");
                    nroNC = nroNC.trim();
                    log.info("-------------------> NC: "+nroNC);
                    vListPedidoNotaCredito.add(numera);
                    if ("N".equalsIgnoreCase(nroNC)) {
                        throw new Exception("Detalle Anulación en Local:\n ERROR AL GENERAR LA NOTA DE CREDITO DEL PEDIDO "+VariablesCaja.vNumPedVta_Anul);
                    }
                }
                
                //ERIOS 15.05.2015 Verifica anulacion pinpad
                FacadePinpad facadePinpad = new FacadePinpad();
                if(!facadePinpad.verificaAnulacionPinpad(this,VariablesCaja.vNumPedVta_Anul)){
                    return false;
                }


                //GFONSECA 08/11/2013 Conciliacion anulacion venta CMR
                /* if(dlgProcesarVentaCMR != null)
                {   if(dlgProcesarVentaCMR.isBRptTrsscAnul())
                    {
                        String numPedNeg = facadeRecaudacion.getNumPedidoNegativo(VariablesCaja.vNumPedVta_Anul);
                        dlgProcesarVentaCMR.setStrNumPedNegativo(numPedNeg);
                        dlgProcesarVentaCMR.procesarConciliacionAnul();
                    }*/
                if (VariablesCaja.vIndPedidoConProdVirtual && !VariablesCaja.vIndAnulacionConReclamoNavsat) {
                    try {
                        UtilityCaja.actualizaInfoPedidoVirtualAnulado(VariablesCaja.vNumPedVta_Anul);
                    } catch (SQLException sql) {
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.showMessage(this,
                                                 "Error al actualizar informacion del pedido virtual anulado.\n" +
                                sql.getMessage(), this);
                    }
                }

                //FarmaUtility.aceptarTransaccion();
                retorno = true;
                /********************* FIN   DE CAMBIO ********************************/
                /****************************************
                 * 
                 * */
                UtilityAtencionMedica.grabaHistoricoRecetaRac(VariablesCaja.vNumPedVta_Anul);
                
				boolean isVtaAtendMedica = UtilityVentas.getVentaAtencionMedica(VariablesCaja.vNumPedVta_Anul);
                    
                    if(isVtaAtendMedica){
                            //Dubilluz 2016.09.07
                            
                            UtilityAtencionMedica.grabaHistoricoEstadoCompRac(VariablesCaja.vNumPedVta_Anul);
                    }

                
                //--Se anula los cupones en Matriz
                //  04.09.2008 Dubilluz   // JVARA ANULAR CUPONES POR MATRIZ
                UtilityCaja.anulaCuponesPedido(VariablesCaja.vNumPedVta_Anul, this, btnRelacionProductos);

                //Activa los cupones en matriz // JVARA ACTIVA CUPONES EN MATRIZ
                //03.12.2008 dubilluz
                UtilityCaja.activaCuponesMatriz(VariablesCaja.vNumPedVta_Anul, this, btnRelacionProductos);

                UtilityCaja.alertaPedidoDelivery(pDatosDel.trim());
                retorno = true;

            } //LTAVARA 10.09.2014 FIN


        } else {
            retorno = false;
        }
        return retorno;
    }

    public void setNCParcial(boolean NCParcial) {
        this.NCParcial = NCParcial;
    }

    public boolean isNCParcial() {
        return NCParcial;
    }


    public void setListProdNCParcial(String listProdNCParcial) {
        this.listProdNCParcial = listProdNCParcial;
    }

    public String getListProdNCParcial() {
        return listProdNCParcial;
    }

    private void anulacionManual() {
        String indAnul_Automatico="S";
        //indAnul_Automatico="N";/*
        try {
            indAnul_Automatico = DBCaja.GET_IND_AUTO_ANUL_ORVIIS();
        } catch (SQLException f) {
            log.debug("Error: IND_ANUL_ORVIIS\n"+f.getErrorCode());
        }
        /* */
        if(indAnul_Automatico.equalsIgnoreCase("S"))
            ejecutaDlgProcesar();
        else{
            DlgAnulacionOrviis dAnula = new DlgAnulacionOrviis(myParentFrame, "", true);
            dAnula.setVProductos(listProdNCParcial);
            dAnula.setCodException(VariablesCaja.codAnul_Orviis.trim());
            if(indAnul_Automatico.equalsIgnoreCase("A"))
                dAnula.setAutoProceso(true);
            else
                dAnula.setAutoProceso(false);
            dAnula.setVisible(true);
        }
        cerrarVentana(FarmaVariables.vAceptar);
    }

    private void ejecutaDlgProcesar() {
        DlgProcesar_AnulOrbis dlgAnul = new DlgProcesar_AnulOrbis(myParentFrame, "", true);
        dlgAnul.setVProductos(listProdNCParcial);
        dlgAnul.setCodException(VariablesCaja.codAnul_Orviis.trim());
        dlgAnul.setVisible(true);
    }
}
