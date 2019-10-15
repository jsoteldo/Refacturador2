package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import farmapuntos.orbis.WSClientConstans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConnectionRemoto;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.UtilityCPE;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.caja.DlgDatosTipoTarjeta.JPanelImagenTransparente;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.DBCobroBD;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.caja.reference.VariablesVirtual;
import mifarma.ptoventa.ce.reference.ConstantsCajaElectronica;
import mifarma.ptoventa.ce.reference.DBCajaElectronica;
import mifarma.ptoventa.ce.reference.FacadeCajaElectronica;
import mifarma.ptoventa.ce.reference.VariablesCajaElectronica;
import mifarma.ptoventa.cliente.reference.ConstantsCliente;
import mifarma.ptoventa.cliente.reference.DBCliente;
import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.delivery.reference.DBDelivery;
import mifarma.ptoventa.delivery.reference.UtilityDelivery;
import mifarma.ptoventa.delivery.reference.VariablesDelivery;
import mifarma.ptoventa.fidelizacion.reference.ConstantsFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.DBFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.pinpad.DlgInterfacePinpad;
import mifarma.ptoventa.pinpad.DlgInterfacePinpad2;
import mifarma.ptoventa.pinpad.reference.UtilityPinpad;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.FacadePuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.recaudacion.DlgProcesarPagoTerceros;
import mifarma.ptoventa.recaudacion.DlgProcesarVentaCMR;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2013 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicaci�n : DlgNuevoCobro.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * ERIOS      01.03.2013   Creaci�n<br>
 * <br>
 * @author Edgar Rios Navarro<br>
 * @version 1.0<br>
 *
 */

public class DlgNuevoCobro extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgNuevoCobro.class);
    public boolean vActivaF9 = false;
    /** Almacena el Objeto Frame de la Aplicaci�n - Ventana Principal */

    public Frame myParentFrame;

    private FarmaTableModel tableModelDetallePago;
    private FarmaTableModel tableModelFormasPago;

    private boolean indPedirLogueo = true;
    private boolean indCerrarPantallaAnularPed = false;
    private boolean indCerrarPantallaCobrarPed = false;

    private String descProductoRecVirtual = ""; //ASOSA - 03.07.2014
    private String  descProductoRimac = ""; //ASOSA - 12/01/2015 - RIMAC

    private int intTipoCobro = 1;
    private String strCodRecau = "";
    private String tipoRcd = "";
    private boolean indBorra = false;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JLabel lblRazSoc = new JLabel();
    private JLabel lblRazSoc_T = new JLabel();
    private JPanel jPanel1 = new JPanel();

    private JLabel lblTipoTrj = new JLabel();

    public JTextField txtMontoPagado = new JTextField();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JPanel pnlTotales = new JPanel();
    private XYLayout xYLayout5 = new XYLayout();
    private JLabel lblSaldoT = new JLabel();
    private JLabel lblSaldo = new JLabel();
    public JLabel lblVueltoT = new JLabel();
    public JLabel lblVuelto = new JLabel();
    private JLabel lblTipoComprobante = new JLabel();
    private JPanel pnlTotal = new JPanel();
    private XYLayout xYLayout2 = new XYLayout();
    private JLabel lblSolesTotalVenta = new JLabel();
    private JLabel lblTotalVenta = new JLabel();
    private JButton btnMonto = new JButton();
    private JLabel lblMensaje = new JLabel();
    private JLabel lblMsjNumRecarga = new JLabel();


    public JTable tblDetallePago = new JTable();
    public JTable tblFormasPago = new JTable();

    public JTextField txtMontoPagadoDolares = new JTextField();
    private JButton btnMontoDolares = new JButton();
    private JLabel lblSolesTotalPagar = new JLabel();
    private JLabel lblTotalPagar = new JLabel();
    private JLabelOrange lblValCopago = new JLabelOrange();
    private JLabelOrange lblCopago = new JLabelOrange();
    private JLabelOrange lblPorcCopago = new JLabelOrange();
    private JLabelOrange lblCopagoSoles = new JLabelOrange();
    private JPanelWhite pnlCopago = new JPanelWhite();
    private JButton btnMontoTarjeta = new JButton();
    public JTextField txtMontoTarjeta = new JTextField();

    private String strMonedaPagar = ConstantsRecaudacion.EST_CTA_SOLES;
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private JLabel lblTipoCambioT1 = new JLabel();
    private JLabel lblMontoEnSolesT1 = new JLabel();
    private JLabel lblMontoEnSoles1 = new JLabel();
    private JLabel lblTipoCambio1 = new JLabel();
    private JPanelWhite jPanelWhite4 = new JPanelWhite();
    private JLabel lblTipoCambioT2 = new JLabel();
    private JLabel lblMontoEnSolesT2 = new JLabel();
    private JLabel lblMontoEnSoles2 = new JLabel();
    private JLabel lblTipoCambio2 = new JLabel();

    ArrayList<String> arrayCodFormaPago = new ArrayList<>();
    FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();
    ArrayList<Object> tmpArrayCabRcd;
    UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    private JLabel lblTotalConversion = new JLabel();
    private JLabel lblTotConversionT = new JLabel();
    private boolean presionoF11 = false;
    private boolean presionoTecla = false;
    public boolean indCobroBD = false;
    public boolean pEjecutaOldCobro = false;
    private DlgFormaPago dlgFormPago;
    private double vValorSelCopago = -1;
    private JLabelFunction lblF9 = new JLabelFunction();
    FacadeLealtad facadeLealtad = new FacadeLealtad();
    private JPanelWhite pnlPuntos = new JPanelWhite();
    private JLabelOrange lblPuntosAcumulados_T = new JLabelOrange();
    private JLabelOrange lblPuntosRedimidos_T = new JLabelOrange();
    private JPanelWhite pnlPuntosAcumulados = new JPanelWhite();
    private JLabelOrange lblPuntosAcumulados = new JLabelOrange();
    private JPanelWhite pnlPuntosRedimidos = new JPanelWhite();
    private JLabelOrange lblPuntosRedimidos = new JLabelOrange();
    private JLabelOrange lblMontoRedencion = new JLabelOrange();
    private JPanelWhite pnlMontoRedencion = new JPanelWhite();
    private JLabelOrange lblMontoRedencion_T1 = new JLabelOrange();
    private JLabelOrange lblMontoRedencion_T2 = new JLabelOrange();
    private JPanelTitle pblTotalPagar = new JPanelTitle();
    private JLabelFunction lblF10 = new JLabelFunction();
    private JLabelFunction lblF7 = new JLabelFunction();
    private JLabelOrange lblAhorro_T = new JLabelOrange();
    private JLabelOrange lblAhorro = new JLabelOrange();
    private JPanelWhite pnlPuntos2 = new JPanelWhite();
    private JPanelHeader pnlSaldoPuntos = new JPanelHeader();
    private JPanelHeader pnlPuntosRedimir = new JPanelHeader();
    private JLabelOrange lblSaldoPuntos_T = new JLabelOrange();
    private JLabelOrange lblSaldoPuntos = new JLabelOrange();
    private JLabelOrange lblPuntosRedimir_T = new JLabelOrange();
    private JLabelOrange lblPuntosRedimir = new JLabelOrange();
    private JLabel lblTotalPercepcion = new JLabel();
    private JLabel lblMontoPercepcion = new JLabel();
    
    private JLabel lblTotalPedido = new JLabel();
    private JLabel lblMontoPedido = new JLabel();
    private JLabel lblTipoCliente = new JLabel();
    private boolean isRecaudacionBFP = false;
    public boolean isPedidoPendientePinpad = false;
    private JPanel pnlDetalles = new JPanel();
    // **************************************************************************
    // Constructores
    // **************************************************************************

    /**
     *Constructor
     */
    public DlgNuevoCobro() {
        this(null, "", false);
    }

    /**
     *Constructor
     *@param parent Objeto Frame de la Aplicaci�n.
     *@param title T�tulo de la Ventana.
     *@param modal Tipo de Ventana.
     */
    public DlgNuevoCobro(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        //dlgFormPago = dlgFp;
        try {
            jbInit();
            initialize();
            initTempVariablesFormaPago(true); /*ZB 20190719*/
        } catch (Exception e) {
            log.error("", e);
        }
    }
   
    //Prueba de contructor 25/07/2018 
    public DlgNuevoCobro(Frame parent, String title, boolean modal,boolean isRecaudacionBFP, boolean isPedidoPendientePinpad) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.isRecaudacionBFP=isRecaudacionBFP;
        this.isPedidoPendientePinpad = isPedidoPendientePinpad;
        
        try {
            jbInit_BFP();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    // **************************************************************************
    // M�todo "jbInit()"
    // **************************************************************************

    private void jbInit_BFP() throws Exception {
        
    }
    /**
     *Implementa la Ventana con todos sus Objetos
     */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(458, 549));
        this.getContentPane().setLayout(borderLayout1);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("Cobrar Pedido");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setLayout(null);
        jContentPane.setBackground(Color.white);
        jContentPane.setSize(new Dimension(554, 504));
        jPanel3.setBackground(new Color(255, 130, 14));
        jPanel3.setBorder(BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(null);
        
        pnlTotal.setBounds(new Rectangle(10, 5, 420, 215));
        pnlTotal.setFont(new Font("SansSerif", 0, 11));
        pnlTotal.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlTotal.setBackground(Color.white);
        pnlTotal.setLayout(xYLayout2);
        
        pnlDetalles.setBounds(new Rectangle(5, 225, 435, 285));
        pnlDetalles.setFont(new Font("SansSerif", 0, 11));
        //pnlDetalles.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlDetalles.setBackground(Color.white);
        pnlDetalles.setLayout(xYLayout2);
        
        lblRazSoc.setFont(new Font("SansSerif", 1, 12));
        lblRazSoc.setForeground(new Color(255, 130, 14));
        lblRazSoc_T.setText("Razon Social :");
        lblRazSoc_T.setFont(new Font("SansSerif", 0, 12));
        lblRazSoc_T.setForeground(new Color(255, 130, 14));
        
        lblTipoTrj.setText("");
        lblTipoTrj.setFont(new Font("SansSerif", 0, 12));
        lblTipoTrj.setForeground(new Color(255, 130, 14));

        lblTipoTrj.setBounds(new Rectangle(210, 80, 200, 20));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setBackground(Color.white);
        jPanel1.setLayout(null);

        txtMontoPagado.setText("0.00");
        txtMontoPagado.setHorizontalAlignment(JTextField.RIGHT);
        txtMontoPagado.setBounds(new Rectangle(85, 10, 90, 20));
        txtMontoPagado.setEnabled(true);
        txtMontoPagado.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtMontoPagado_mouseClicked(e);
            }
        });
        txtMontoPagado.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtMontoPagado_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtMontoPagado_keyTyped(e);
            }
        });
        lblEsc.setText("<HTML><CENTER>[ESC]<BR>Cerrar</CENTER></HTML>");
        lblF11.setText("<HTML><CENTER>[F11]<BR>Aceptar</HTML></CENTER>");
        pnlTotales.setFont(new Font("SansSerif", 0, 11));
        pnlTotales.setBackground(new Color(43, 141, 39));
        pnlTotales.setLayout(xYLayout5);
        lblSaldoT.setBounds(45, 10, 90, 20);
        lblSaldoT.setText("SALDO :  "+ConstantesUtil.simboloSoles);
        lblSaldoT.setFont(new Font("SansSerif", 1, 13));
        lblSaldoT.setForeground(Color.white);
        lblSaldo.setBounds(130, 10, 105, 20);
        lblSaldo.setText("0.00");
        lblSaldo.setFont(new Font("SansSerif", 1, 13));
        lblSaldo.setForeground(Color.white);
        lblSaldo.setHorizontalAlignment(SwingConstants.LEFT);
        lblSaldo.setBounds(new Rectangle(130, 10, 80, 20));
        lblVueltoT.setText("Vuelto :  "+ConstantesUtil.simboloSoles);
        lblVueltoT.setFont(new Font("SansSerif", 1, 13));
        lblVueltoT.setForeground(Color.white);
        lblVuelto.setText("0.00");
        lblVuelto.setFont(new Font("SansSerif", 1, 13));
        lblVuelto.setForeground(Color.white);
        lblTipoComprobante.setForeground(new Color(255, 130, 14));
        lblTipoComprobante.setFont(new Font("SansSerif", 1, 12));
        lblTipoComprobante.setText("[COMPROBANTE]");
        
        lblSolesTotalVenta.setText("0.00");
        lblSolesTotalVenta.setFont(new Font("SansSerif", 1, 13));
        lblSolesTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolesTotalVenta.setForeground(new Color(255, 130, 14));
        lblTotalVenta.setText("Total Venta : "+ConstantesUtil.simboloSoles);
        lblTotalVenta.setFont(new Font("SansSerif", 1, 13));
        lblTotalVenta.setForeground(new Color(255, 130, 14));
        lblTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
        btnMonto.setText("Soles : ");
        btnMonto.setBounds(new Rectangle(15, 10, 65, 20));
        btnMonto.setBorderPainted(false);
        btnMonto.setContentAreaFilled(false);
        btnMonto.setDefaultCapable(false);
        btnMonto.setFocusPainted(false);
        btnMonto.setHorizontalAlignment(SwingConstants.LEFT);
        btnMonto.setMnemonic('s');
        btnMonto.setRequestFocusEnabled(false);
        btnMonto.setFont(new Font("SansSerif", 0, 11));
        btnMonto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnMonto.setActionCommand("Soles : ");
        btnMonto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnMonto_actionPerformed(e);
            }
        });

        lblMensaje.setForeground(Color.red);
        lblMensaje.setFont(new Font("SansSerif", 1, 13));
        lblMensaje.setText("[Mensaje]");
        lblMsjNumRecarga.setForeground(new Color(43, 141, 39));
        lblMsjNumRecarga.setFont(new Font("SansSerif", 1, 17));

        lblMsjNumRecarga.setText("[997368791]");


        txtMontoPagadoDolares.setText("0.00");
        txtMontoPagadoDolares.setHorizontalAlignment(JTextField.RIGHT);
        txtMontoPagadoDolares.setBounds(new Rectangle(85, 45, 90, 20));
        txtMontoPagadoDolares.setEnabled(true);
        txtMontoPagadoDolares.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtMontoPagado_mouseClicked(e);
            }
        });
        txtMontoPagadoDolares.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtMontoPagadoDolares_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtMontoPagadoDolares_keyTyped(e);
            }
        });
        btnMontoDolares.setText("Dolares : ");
        btnMontoDolares.setBounds(new Rectangle(15, 45, 65, 20));
        btnMontoDolares.setBorderPainted(false);
        btnMontoDolares.setContentAreaFilled(false);
        btnMontoDolares.setDefaultCapable(false);
        btnMontoDolares.setFocusPainted(false);
        btnMontoDolares.setHorizontalAlignment(SwingConstants.LEFT);
        btnMontoDolares.setMnemonic('d');
        btnMontoDolares.setRequestFocusEnabled(false);
        btnMontoDolares.setFont(new Font("SansSerif", 0, 11));
        btnMontoDolares.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnMontoDolares.setActionCommand("Soles : ");
        btnMontoDolares.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnMontoDolares_actionPerformed(e);
            }
        });
        lblSolesTotalPagar.setText("0.00");
        lblSolesTotalPagar.setFont(new Font("SansSerif", 1, 13));
        lblSolesTotalPagar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolesTotalPagar.setForeground(Color.white);
        lblSolesTotalPagar.setBounds(new Rectangle(240, 10, 65, 20));
        lblTotalPagar.setText("TOTAL A PAGAR : "+ConstantesUtil.simboloSoles);
        lblTotalPagar.setFont(new Font("SansSerif", 1, 13));
        lblTotalPagar.setForeground(Color.white);
        lblTotalPagar.setBounds(new Rectangle(100, 10, 140, 20));
        lblValCopago.setText("0.00");
        lblValCopago.setHorizontalAlignment(SwingConstants.RIGHT);
        lblValCopago.setFont(new Font("SansSerif", 1, 12));
        lblValCopago.setBounds(new Rectangle(185, 0, 50, 20));
        lblCopago.setText("CoPago");
        lblCopago.setFont(new Font("SansSerif", 1, 12));
        lblCopago.setBounds(new Rectangle(10, 0, 80, 20));
        lblPorcCopago.setText("100.00");
        lblPorcCopago.setFont(new Font("SansSerif", 1, 12));
        lblPorcCopago.setBounds(new Rectangle(90, 0, 40, 20));
        lblPorcCopago.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCopagoSoles.setText("% : "+ConstantesUtil.simboloSoles);
        lblCopagoSoles.setFont(new Font("SansSerif", 1, 12));
        lblCopagoSoles.setBounds(new Rectangle(135, 0, 35, 20));
        btnMontoTarjeta.setText("Tarjeta : ");
        btnMontoTarjeta.setBounds(new Rectangle(15, 80, 65, 20));
        btnMontoTarjeta.setBorderPainted(false);
        btnMontoTarjeta.setContentAreaFilled(false);
        btnMontoTarjeta.setDefaultCapable(false);
        btnMontoTarjeta.setFocusPainted(false);
        btnMontoTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
        btnMontoTarjeta.setMnemonic('t');
        btnMontoTarjeta.setRequestFocusEnabled(false);
        btnMontoTarjeta.setFont(new Font("SansSerif", 0, 11));
        btnMontoTarjeta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnMontoTarjeta.setActionCommand("Soles : ");
        btnMontoTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnMontoTarjeta_actionPerformed(e);
            }
        });
        txtMontoTarjeta.setText("0.00");
        txtMontoTarjeta.setHorizontalAlignment(JTextField.RIGHT);
        txtMontoTarjeta.setBounds(new Rectangle(85, 80, 90, 20));
        txtMontoTarjeta.setEnabled(true);
        txtMontoTarjeta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtMontoPagado_mouseClicked(e);
            }
        });
        txtMontoTarjeta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtMontoTarjeta_keyPressed(e, false);
            }

            public void keyTyped(KeyEvent e) {
                txtMontoTarjeta_keyTyped(e);
            }
        });
        jPanelWhite1.setBounds(new Rectangle(190, 10, 195, 35));
        lblTipoCambioT1.setText("Tipo Cambio : ");
        lblTipoCambioT1.setBounds(new Rectangle(0, 0, 85, 15));
        lblTipoCambioT1.setForeground(new Color(43, 141, 39));
        lblTipoCambioT1.setFont(new Font("SansSerif", 1, 12));
        lblMontoEnSolesT1.setText("Monto en dolares : ");
        lblMontoEnSolesT1.setBounds(new Rectangle(0, 15, 105, 15));
        lblMontoEnSolesT1.setForeground(Color.black);
        lblMontoEnSolesT1.setFont(new Font("SansSerif", 0, 12));
        lblMontoEnSoles1.setBounds(new Rectangle(110, 15, 75, 15));
        lblMontoEnSoles1.setFont(new Font("SansSerif", 0, 12));
        lblMontoEnSoles1.setForeground(Color.black);
        lblMontoEnSoles1.setText("0.00");
        lblTipoCambio1.setFont(new Font("SansSerif", 1, 12));
        lblTipoCambio1.setForeground(new Color(43, 141, 39));
        lblTipoCambio1.setBounds(new Rectangle(100, 0, 75, 15));
        jPanelWhite4.setBounds(new Rectangle(190, 45, 195, 30));
        lblTipoCambioT2.setText("Tipo Cambio : ");
        lblTipoCambioT2.setBounds(new Rectangle(0, 0, 85, 15));
        lblTipoCambioT2.setForeground(new Color(43, 141, 39));
        lblTipoCambioT2.setFont(new Font("SansSerif", 1, 12));
        lblMontoEnSolesT2.setText("Monto en soles : ");
        lblMontoEnSolesT2.setBounds(new Rectangle(0, 15, 105, 15));
        lblMontoEnSolesT2.setForeground(Color.black);
        lblMontoEnSolesT2.setFont(new Font("SansSerif", 0, 12));
        lblMontoEnSoles2.setBounds(new Rectangle(110, 15, 75, 15));
        lblMontoEnSoles2.setFont(new Font("SansSerif", 0, 12));
        lblMontoEnSoles2.setForeground(Color.black);
        lblMontoEnSoles2.setText("0.00");
        lblTipoCambio2.setFont(new Font("SansSerif", 1, 12));
        lblTipoCambio2.setForeground(new Color(43, 141, 39));
        lblTipoCambio2.setBounds(new Rectangle(100, 0, 75, 15));
        lblTotalConversion.setText("0.00");
        lblTotalConversion.setFont(new Font("SansSerif", 1, 13));
        lblTotalConversion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalConversion.setForeground(Color.white);
        lblTotalConversion.setBounds(new Rectangle(350, 10, 65, 20));
        lblTotConversionT.setText("US$");
        lblTotConversionT.setFont(new Font("SansSerif", 1, 13));
        lblTotConversionT.setForeground(Color.white);
        lblTotConversionT.setBounds(new Rectangle(310, 10, 40, 20));
        lblF9.setText("<HTML><CENTER>[ F9 ] Canje<BR>de Puntos</HTML></CENTER>");
        pnlPuntos.setBounds(new Rectangle(190, 85, 225, 30));
        lblPuntosAcumulados_T.setText("Puntos Acumulados:");
        lblPuntosAcumulados_T.setBounds(new Rectangle(0, 0, 150, 15));
        lblPuntosAcumulados_T.setForeground(new Color(43, 141, 39));
        lblPuntosRedimidos_T.setText("Puntos Redimidos:");
        lblPuntosRedimidos_T.setBounds(new Rectangle(0, 0, 105, 15));
        lblPuntosRedimidos_T.setForeground(Color.red);
        pnlPuntosAcumulados.setBounds(new Rectangle(0, 0, 205, 15));
        lblPuntosAcumulados.setText("0,000.00");
        lblPuntosAcumulados.setBounds(new Rectangle(150, 0, 45, 15));
        lblPuntosAcumulados.setForeground(new Color(43, 141, 39));
        pnlPuntosRedimidos.setBounds(new Rectangle(0, 15, 225, 15));
        lblPuntosRedimidos.setText("0,000.00");
        lblPuntosRedimidos.setBounds(new Rectangle(105, 0, 45, 15));
        lblPuntosRedimidos.setForeground(Color.red);
        lblMontoRedencion.setText("0,000.00");
        lblMontoRedencion.setBounds(new Rectangle(20, 0, 45, 15));
        lblMontoRedencion.setForeground(Color.red);
        pnlMontoRedencion.setBounds(new Rectangle(150, 0, 75, 15));
        lblMontoRedencion_T1.setText("("+ConstantesUtil.simboloSoles);
        lblMontoRedencion_T1.setBounds(new Rectangle(0, 0, 20, 15));
        lblMontoRedencion_T1.setForeground(Color.red);
        lblMontoRedencion_T2.setText(")");
        lblMontoRedencion_T2.setBounds(new Rectangle(65, 0, 10, 15));
        lblMontoRedencion_T2.setForeground(Color.red);
        pblTotalPagar.setBackground(Color.red);
        lblF10.setText("<HTML><CENTER>[ F10 ]<BR>Nota Cr\u00e9dito</HTML></CENTER>");
        lblF7.setText("<HTML><CENTER>[ F7 ]<BR>Reservar Pedido</HTML></CENTER>");
        lblAhorro_T.setText("Ud. ha ahorrado : "+ConstantesUtil.simboloSoles);
        lblAhorro_T.setFont(new Font("SansSerif", 1, 13));
        lblAhorro_T.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAhorro.setText("0.00");
        lblAhorro.setFont(new Font("SansSerif", 1, 13));
        lblAhorro.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlSaldoPuntos.setBounds(new Rectangle(0, 0, 210, 20));
        pnlSaldoPuntos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlPuntosRedimir.setBounds(new Rectangle(210, 0, 210, 20));
        pnlPuntosRedimir.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblSaldoPuntos_T.setText("Puntos saldo acumulado:");
        lblSaldoPuntos_T.setBounds(new Rectangle(5, 0, 145, 20));
        lblSaldoPuntos_T.setForeground(SystemColor.window);
        lblSaldoPuntos.setText("0,000.00");
        lblSaldoPuntos.setBounds(new Rectangle(150, 0, 55, 20));
        lblSaldoPuntos.setForeground(SystemColor.window);
        lblPuntosRedimir_T.setText("Maximo de puntos a redimir:");
        lblPuntosRedimir_T.setBounds(new Rectangle(0, 0, 120, 20));
        lblPuntosRedimir_T.setForeground(SystemColor.window);
        lblPuntosRedimir.setText("0,000.00");
        lblPuntosRedimir.setBounds(new Rectangle(120, 0, 85, 20));
        lblPuntosRedimir.setForeground(SystemColor.window);
        lblPuntosRedimir.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblPuntosRedimir.setHorizontalAlignment(SwingConstants.RIGHT);
        jPanel3.add(lblTotConversionT, null);
        jPanel3.add(lblTotalConversion, null);
        jPanel3.add(lblTotalPagar, null);
        jPanel3.add(lblSolesTotalPagar, null);
        pnlCopago.add(lblCopagoSoles, null);
        pnlCopago.add(lblValCopago, null);
        pnlCopago.add(lblPorcCopago, null);
        pnlCopago.add(lblCopago, null);
        jPanelWhite1.add(lblTipoCambio1, null);
        jPanelWhite1.add(lblMontoEnSoles1, null);
        jPanelWhite1.add(lblMontoEnSolesT1, null);
        jPanelWhite1.add(lblTipoCambioT1, null);
        jPanelWhite4.add(lblTipoCambio2, null);
        jPanelWhite4.add(lblMontoEnSoles2, null);
        jPanelWhite4.add(lblMontoEnSolesT2, null);
        jPanelWhite4.add(lblTipoCambioT2, null);
        pnlPuntosAcumulados.add(lblPuntosAcumulados_T, null);
        pnlPuntosAcumulados.add(lblPuntosAcumulados, null);
        pnlMontoRedencion.add(lblMontoRedencion_T2, null);
        pnlMontoRedencion.add(lblMontoRedencion_T1, null);
        pnlMontoRedencion.add(lblMontoRedencion, null);
        pnlPuntosRedimidos.add(pnlMontoRedencion, null);
        pnlPuntosRedimidos.add(lblPuntosRedimidos, null);
        pnlPuntosRedimidos.add(lblPuntosRedimidos_T, null);
        pnlPuntos.add(pnlPuntosRedimidos, null);
        pnlPuntos.add(pnlPuntosAcumulados, null);
        jPanel1.add(pnlPuntos, null);
        jPanel1.add(jPanelWhite4, null);
        jPanel1.add(jPanelWhite1, null);
        jPanel1.add(txtMontoTarjeta, null);
        jPanel1.add(btnMontoTarjeta, null);
        jPanel1.add(btnMontoDolares, null);

        // KMONCADA 18.04.2016
        jPanel1.add(txtMontoPagadoDolares, null);
        jPanel1.add(btnMonto, null);
        jPanel1.add(txtMontoPagado, null);
        jPanel1.add(lblTipoTrj, null);
        lblTotalPercepcion.setText("Total Percepcion : "+ConstantesUtil.simboloSoles);
        lblTotalPercepcion.setFont(new Font("SansSerif", 1, 13));
        lblTotalPercepcion.setForeground(new Color(255, 130, 14));
        lblTotalPercepcion.setHorizontalAlignment(SwingConstants.RIGHT);
        
        lblMontoPercepcion.setText("0.00");
        lblMontoPercepcion.setFont(new Font("SansSerif", 1, 13));
        lblMontoPercepcion.setForeground(new Color(255, 130, 14));
        lblMontoPercepcion.setHorizontalAlignment(SwingConstants.RIGHT);
        
        lblTotalPedido.setText("Total Pedido : "+ConstantesUtil.simboloSoles);
        lblTotalPedido.setFont(new Font("SansSerif", 1, 13));
        lblTotalPedido.setForeground(new Color(255, 130, 14));
        lblTotalPedido.setHorizontalAlignment(SwingConstants.RIGHT);
        
        lblMontoPedido.setText("0.00");
        lblMontoPedido.setFont(new Font("SansSerif", 1, 13));
        lblMontoPedido.setForeground(new Color(255, 130, 14));
        lblMontoPedido.setHorizontalAlignment(SwingConstants.RIGHT);
        
        lblTipoCliente.setFont(new Font("SansSerif", 1, 12));
        lblTipoCliente.setForeground(new Color(255, 130, 14));


        pblTotalPagar.add(lblSaldoT, null);
        pblTotalPagar.add(lblSaldo, null);
        pnlTotales.add(pblTotalPagar, new XYConstraints(0, 0, 210, 40));
        pnlTotales.add(lblVueltoT, new XYConstraints(235, 10, 80, 20));
        pnlTotales.add(lblVuelto, new XYConstraints(320, 10, 70, 20));
        pnlTotal.add(lblAhorro_T, new XYConstraints(189, 22, 145, 15));
        pnlTotal.add(lblAhorro, new XYConstraints(334, 22, 70, 15));
        pnlTotal.add(lblTotalPedido, new XYConstraints(189, 39, 145, 15));
        pnlTotal.add(lblMontoPedido, new XYConstraints(334, 39, 70, 15));
        pnlTotal.add(lblTotalPercepcion, new XYConstraints(189, 56, 145, 15));
        pnlTotal.add(lblMontoPercepcion, new XYConstraints(334, 56, 70, 15));
        pnlTotal.add(lblTotalVenta, new XYConstraints(224, 73, 110, 15));
        pnlTotal.add(lblSolesTotalVenta, new XYConstraints(334, 73, 70, 15));
        pnlTotal.add(lblRazSoc, new XYConstraints(94, 90, 315, 15));
        pnlTotal.add(lblRazSoc_T, new XYConstraints(4, 90, 85, 15));
        pnlTotal.add(lblTipoCliente, new XYConstraints(4, 109, 405, 15));
        
        pnlTotal.add(lblTipoComprobante, new XYConstraints(4, 4, 400, 20));
        pnlTotal.add(lblMensaje, new XYConstraints(4, 129, 410, 40));
        pnlTotal.add(lblMsjNumRecarga, new XYConstraints(9, 179, 125, 20));
        pnlTotal.add(pnlCopago, new XYConstraints(149, 179, 245, 25));
        
        
        
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);


        pnlPuntosRedimir.add(lblPuntosRedimir, null);
        pnlPuntosRedimir.add(lblPuntosRedimir_T, null);
        pnlSaldoPuntos.add(lblSaldoPuntos, null);
        pnlSaldoPuntos.add(lblSaldoPuntos_T, null);
        pnlPuntos2.add(pnlPuntosRedimir, null);
        pnlPuntos2.add(pnlSaldoPuntos, null);
        pnlDetalles.add(jPanel3, new XYConstraints(5, 0, 420, 35));
        pnlDetalles.add(pnlPuntos2, new XYConstraints(5, 40, 420, 20));
        pnlDetalles.add(jPanel1, new XYConstraints(5, 65, 420, 125));
        pnlDetalles.add(pnlTotales, new XYConstraints(5, 195, 420, 40));
        pnlDetalles.add(lblEsc, new XYConstraints(365, 250, 60, 35));
        pnlDetalles.add(lblF7, new XYConstraints(265, 250, 95, 35));
        pnlDetalles.add(lblF11, new XYConstraints(190, 250, 70, 35));
        pnlDetalles.add(lblF10, new XYConstraints(100, 250, 85, 35));
        pnlDetalles.add(lblF9, new XYConstraints(5, 250, 90, 35));
        jContentPane.add(pnlDetalles, null);
        jContentPane.add(pnlTotal, null);
    }

    // **************************************************************************
    // M�todo "initialize()"
    // **************************************************************************

    private void initialize() {
        initTableDetallePago();

        FarmaVariables.vAceptar = false;

        obtenerTipoCambio();
        
        //ERIOS 16.09.2015 Verifica indicador de GTX
        DlgProcesar.getIndGestorTx(true);
    }

    // **************************************************************************
    // M�todos de inicializaci�n
    // **************************************************************************

    private void initTableDetallePago() {
        tableModelDetallePago =
                new FarmaTableModel(ConstantsCaja.columsListaDetallePago, ConstantsCaja.defaultListaDetallePago, 0);
        FarmaUtility.initSimpleList(tblDetallePago, tableModelDetallePago, ConstantsCaja.columsListaDetallePago);

        //Nueva tabla
        tableModelFormasPago =
                new FarmaTableModel(ConstantsCaja.columsListaFormasPago, ConstantsCaja.defaultListaFormasPago, 0);
        FarmaUtility.initSimpleList(tblFormasPago, tableModelFormasPago, ConstantsCaja.columsListaFormasPago);
    }


    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************


    public void this_windowOpened(WindowEvent e) {
        //LLEIVA 03-Ene-2014 Si el tipo de cambio es cero, no permitir continuar
        System.out.println("OPEN DLGNUEVOCOBRO");
        if (FarmaVariables.vTipCambio == 0 || VariablesRecaudacion.vTipoCambioVenta == 0 ||
            VariablesRecaudacion.vTipoCambioCompra == 0) {
            FarmaUtility.showMessage(this,
                                     "ATENCI�N: No se pudo obtener el tipo de cambio actual\nNo se puede continuar con la acci�n",
                                     null);
            cerrarVentana(false, null);
        } else {
            //ERIOS 21.11.2014 Verificar el indicador de pinpad
            DlgProcesar.cargaIndPinpad();

            inicializaVariablesVentana();
            if (intTipoCobro == ConstantsCaja.COBRO_PEDIDO) {
                initVariablesCobro(pEjecutaOldCobro);
            } else if (intTipoCobro == ConstantsCaja.COBRO_CAJA_ELECTRONICA) {
                initVariablesCajaElectronica();
            } else if (intTipoCobro == ConstantsCaja.COBRO_RECAUDACION) {
                initVariablesRecaudacion();
            }
        }
        
        if(VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)){
            txtMontoPagado.setEditable(false);
            txtMontoPagadoDolares.setEditable(false);
            txtMontoTarjeta.setText(lblSolesTotalVenta.getText().trim());
            FarmaUtility.moveFocus(txtMontoTarjeta);
        }

        //INI JMONZALVE 24042019
        try {
            if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                txtMontoPagado.setEditable(false);
                txtMontoPagadoDolares.setEditable(false);
                txtMontoTarjeta.setText(calcularRestoParaTarjeta());
                lblF10.setVisible(false);
                FarmaUtility.moveFocus(txtMontoTarjeta);
                btnMontoTarjeta.setText("Tarj. Conv:");
            }
        } catch (SQLException f) {
        }
        //FIN JMONZALVE 24042019
    }

    private void btnMonto_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMontoPagado);
    }

    public void txtMontoPagado_keyPressed(KeyEvent e) {
        if (pEjecutaOldCobro || e.getKeyCode() == KeyEvent.VK_ENTER) {
            obtieneDatosFormaPagoPedidoSoles();
            adicionaDetallePago(txtMontoPagado);
            if (txtMontoPagadoDolares.isEditable()) {
                FarmaUtility.moveFocus(txtMontoPagadoDolares);
            } else if (!txtMontoPagadoDolares.isEditable() && txtMontoTarjeta.isEnabled()) {
                if (VariablesCaja.vIndDatosTarjeta) {
                    FarmaUtility.moveFocus(txtMontoPagado);
                } else {
                    txtMontoTarjeta.setText(calcularRestoParaTarjeta());
                    FarmaUtility.moveFocus(txtMontoTarjeta);
                }
            } else if (!txtMontoPagadoDolares.isEditable() && !txtMontoTarjeta.isEnabled()) {
                FarmaUtility.moveFocus(txtMontoPagado);
            }
        } else
            chkkeyPressed(e);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }


    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private synchronized void chkkeyPressed(KeyEvent e) {
        //ERIOS 02.07.2015 Sincronizacion de teclas
        if (presionoTecla) {
            log.warn("Metodo sincronizado: "+KeyEvent.getKeyText(e.getKeyCode()));
            return;
        } else {
            presionoTecla = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_F9) {
            if(vActivaF9){
                //ERIOS 19.02.2015 Redencion de puntos            
                funcionF9();
                //KMONCADA 21.10.2015 RETORNA EL FOCO AL COMPONENTE QUE LO INVOCO
                JTextField campoTextoAux = (JTextField)e.getSource();
                FarmaUtility.moveFocus(campoTextoAux);
            }else{
                //KMONCADA 05.08.2015 SE MOSTRARA MENSAJE PARA CASOS DE TARJETAS BLOQUEDAS PARA REDIMIR
                boolean isTarjetaBloqueada = false;
               if(VariablesPuntos.frmPuntos != null){ 
                    if ( VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    FacadePuntos facadePtos = new FacadePuntos();
                    // KMONCADA 11.08.2015 VALIDARA SI CLIENTE SE ENCUENTRA REGISTRADO COMO BLOQUEADO PARA REDIMIR
                    if(WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR.equalsIgnoreCase(VariablesPuntos.frmPuntos.getBeanTarjeta().getEstadoTarjeta()) ||
                       facadePtos.verificaClienteBloqueadoRedime(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni())){
                        isTarjetaBloqueada = true;
                    }
                }}
                if(isTarjetaBloqueada){
                    FacadePuntos facadePtos = new FacadePuntos();
                    FarmaUtility.showMessage(this, facadePtos.getMsjTarjBloqueaRedimir(), txtMontoPagado);
                }else{
                    FarmaUtility.showMessage(this, "El cliente no puede canjear su puntos", txtMontoPagado);
                }
            }
        }else if (UtilityPtoVenta.verificaVK_F10(e) && lblF10.isVisible()) {
            //INI JMONZALVE 24042019
            try {
                if(!DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                    funcionF10();
                }
            } catch (SQLException f) {
            }
            //FIN JMONZALVE 24042019
        }else if (UtilityPtoVenta.verificaVK_F7(e) && lblF7.isVisible()) {
            funcionF7();
        }else if (UtilityPtoVenta.verificaVK_F11(e)) {
            funcionF11();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            initTempVariablesFormaPago(false); /*ZB 20190719*/
            if (intTipoCobro == ConstantsCaja.COBRO_PEDIDO) {
                if (VariablesVirtual.vCodigoRespuesta.trim().equalsIgnoreCase(ConstantsCaja.COD_RESPUESTA_OK_TAR_VIRTUAL)) {
                    FarmaUtility.showMessage(this, "La recarga se proces� exitosamente. No puede salir del cobro.\n" +
                            "Si persiste el error llame a Mesa de Ayuda.", null);
                } else {
                    eventoEscape();
                }
            } else if (intTipoCobro == ConstantsCaja.COBRO_CAJA_ELECTRONICA) {
                cerrarVentana(false, null);
            } else if (intTipoCobro == ConstantsCaja.COBRO_RECAUDACION) {
                cerrarVentanaRecau();
            }
        }
        presionoTecla = false;
    }

    private void cerrarVentana(boolean pAceptar, DlgFormaPago pDlg) {
        FarmaVariables.vAceptar = pAceptar;
        VariablesCaja.vNumPedVta = "";
        if (pAceptar) {
            log.info("cerrar el Nuevo Cobro ...");
            if (pDlg != null) {
                log.info("cerrar DLgFormaPago ...");
                pDlg.cerrarVentana(pAceptar);
            }
        }
        limpiaVariableCliente();
        this.setVisible(false);
        this.dispose();
    }
    
    /**
     * 
     * @author KMONCADA
     * @since 2015.03.18 
     */
    private void limpiaVariableCliente() {
        VariablesVentas.vCod_Cli_Local = "";
        VariablesVentas.vRuc_Cli_Ped = "";
        VariablesVentas.vNom_Cli_Ped = "";
        VariablesVentas.vDir_Cli_Ped = "";
    }

    // **************************************************************************
    // Metodos de l�gica de negocio
    // **************************************************************************

    private void limpiarDatos() {
        lblTipoComprobante.setText("");
        lblRazSoc.setText("");

        lblSolesTotalVenta.setText("");

        txtMontoPagado.setText("");
        lblSaldo.setText("0.00");
        lblVuelto.setText("0.00");
        VariablesCaja.vIndPedidoSeleccionado = "N";
        VariablesCaja.vIndTotalPedidoCubierto = false;
        VariablesCaja.vIndPedidoCobrado = false;
        lblMensaje.setVisible(false);
        lblMsjNumRecarga.setVisible(false);
        VariablesCaja.vIndPedidoConProdVirtual = false;
        VariablesCaja.vIndPedidoConvenio = "";
        VariablesCaja.vValMontoPagadoTarj = "";

        VariablesConvenio.vCodCliente = "";
        VariablesConvenio.vCodConvenio = "";
        VariablesConvenio.vValCredDis = 0.00;

        VariablesCaja.cobro_Pedido_Conv_Credito = "N";
        VariablesCaja.valorCredito_de_PedActual = 0.0;

        VariablesCaja.monto_forma_credito_ingresado = "0.00";
        VariablesCaja.uso_Credito_Pedido_N_Delivery = "N";
        VariablesCaja.usoConvenioCredito = "";
        VariablesConvenio.vIndSoloCredito = "";


        VariablesCaja.vValEfectivo = "";
        VariablesCaja.vVuelto = "";
    }

    private void limpiarPagos() {

        if (VariablesCaja.vIndTarjetaSeleccionada && VariablesCaja.vIndDatosTarjeta) {
            FarmaUtility.showMessage(this, "Se ha ingresado informacion de pago con tarjeta.", txtMontoPagado);
            return;
        }
        
        tableModelDetallePago.clearTable();
        lblSaldo.setText(lblSolesTotalVenta.getText().trim());
        lblVuelto.setText("0.00");
        VariablesCaja.vIndTotalPedidoCubierto = false;
        VariablesCaja.vIndPedidoCobrado = false;
        txtMontoPagado.setText("0.00");
        txtMontoPagadoDolares.setText("0.00");
        txtMontoTarjeta.setText("0.00");
        
        if (!indBorra) {
            cargaDetalleFormaPago(VariablesCaja.vNumPedVta);

            ArrayList array = new ArrayList();
            String codsel = "", codobt = "";
            obtieneDetalleFormaPagoPedido(array, VariablesCaja.vNumPedVta);

            if (array.size() > 0 && !indBorra) {

                for (int j = 0; j < array.size(); j++) {
                    codsel = (((String)((ArrayList)array.get(j)).get(0)).trim());
                    for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
                        codobt = ((String)tableModelDetallePago.getValueAt(i, 0)).trim();

                        if (!codobt.equalsIgnoreCase(codsel)) {
                            tableModelDetallePago.deleteRow(i);

                        }
                    }
                }
            } else {
                tableModelDetallePago.clearTable();
            }
            verificaMontoPagadoPedido();
            indBorra = false;
        }

    }

    public void adicionaDetallePago(JTextField txtMonto) {

        //Verifica si es igual a 0, negativo o no sea numero
        if (txtMonto != null && !validaMontoIngresado(txtMonto))
            return;

        if (VariablesCaja.vIndTarjetaSeleccionada && !VariablesCaja.vIndDatosTarjeta) {

            FarmaUtility.showMessage(this, "La forma de pago requiere datos de la tarjeta. Verifique!!!", txtMonto);
            return;
        }
        //All Revisar
        /*if(VariablesCaja.vIndTarjetaSeleccionada && FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoPagado) > VariablesCaja.vMontoMaxPagoTarjeta)
    {
      FarmaUtility.showMessage(this,"El monto ingresado no puede ser mayor al saldo del Pedido. Verifique!!!", txtMonto);
      return;
    }

    if(VariablesCaja.vIndTarjetaSeleccionada && FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoPagado) > VariablesCaja.vMontoMaxPagoTarjeta)
    {
      FarmaUtility.showMessage(this,"El monto ingresado no puede ser mayor al saldo del Pedido. Verifique!!!", txtMonto);
      return;
    }*/
        //Aqui buscara la forma de pago para ser eliminada
        if (!validaCodigoFormaPago()) {
            eliminarFormaPagoTabla(VariablesCaja.vCodFormaPago);
        }
        operaListaDetallePago();
        verificaMontoPagadoPedido();
    }


    private void obtieneDatosFormaPagoPedidoSoles() {
        VariablesCaja.vValEfectivo = txtMontoPagado.getText().trim();

        VariablesCaja.vCodFormaPago = ConstantsCaja.FORMA_PAGO_EFECTIVO_SOLES;
        VariablesCaja.vDescFormaPago = ConstantsCaja.DESC_FORMA_PAGO_SOLES;
        VariablesCaja.vCantidadCupon = "0";

        VariablesCaja.vCodMonedaPago = ConstantsCaja.EFECTIVO_SOLES;
        VariablesCaja.vDescMonedaPago =
                FarmaLoadCVL.getCVLDescription(FarmaConstants.HASHTABLE_MONEDA, VariablesCaja.vCodMonedaPago);
        VariablesCaja.vValMontoPagado =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(txtMontoPagado.getText().trim()));
        if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_DOLARES)) {
            VariablesCaja.vValTotalPagado =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoPagado) /
                                              VariablesRecaudacion.vTipoCambioCompra);
        } else {
            VariablesCaja.vValTotalPagado = VariablesCaja.vValMontoPagado;
        }
        VariablesCaja.vNumTarjCred = "";
        VariablesCaja.vFecVencTarjCred = "";
        VariablesCaja.vNomCliTarjCred = "";
        VariablesCaja.vDNITarj = "";
        VariablesCaja.vNumPedVtaNCR = "";

        lblMontoEnSoles1.setText(VariablesCaja.vValTotalPagado);
    }

    private void operaListaDetallePago() {
        ArrayList myArray = new ArrayList();
        myArray.add(VariablesCaja.vCodFormaPago);
        myArray.add(VariablesCaja.vDescFormaPago);
        myArray.add(VariablesCaja.vCantidadCupon);
        myArray.add(VariablesCaja.vDescMonedaPago);
        myArray.add(VariablesCaja.vValMontoPagado);
        myArray.add(VariablesCaja.vValTotalPagado);
        myArray.add(VariablesCaja.vCodMonedaPago);
        myArray.add("0.00"); //VUELTO
        myArray.add(VariablesCaja.vNumTarjCred);

        myArray.add(VariablesCaja.vFecVencTarjCred);

        myArray.add(VariablesCaja.vNomCliTarjCred);
        myArray.add("");

        myArray.add(VariablesCaja.vDNITarj);
        myArray.add(VariablesCaja.vCodVoucher);
        myArray.add(VariablesCaja.vCodLote);
        myArray.add(VariablesCaja.vNumPedVtaNCR);
                                    
        //ERIOS 2.4.6 Log de datos tarjeta
        log.debug("Agrega F.Pago:" + myArray.toString());
        tableModelDetallePago.data.add(myArray);
        tableModelDetallePago.fireTableDataChanged();

    }

    private boolean validaMontoIngresado(JTextField txtMonto) {
        String monto = txtMonto.getText().trim();
        if (monto.equalsIgnoreCase("") || monto.length() <= 0) {
            FarmaUtility.showMessage(this, "Ingrese monto a pagar.", txtMonto);
            txtMonto.setText("0.00");
            return false;
        }
        //Se cambia la condicion de <= a < Luigy Terrazos
        if (FarmaUtility.getDecimalNumber(monto) < 0) {
            FarmaUtility.showMessage(this, "Ingrese monto a pagar mayor a 0.", txtMonto);
            txtMonto.setText("0.00");
            return false;
        }
        if (FarmaUtility.getDecimalNumber(monto) == 0) {
            //Cuando es 0 busca en la tabla si ya esta registrada para eliminarla y recalcular
            eliminarFormaPagoTabla(VariablesCaja.vCodFormaPago);
            verificaMontoPagadoPedido();
            txtMonto.setText("0.00");
            return false;
        }
        return true;
    }

    private void verificaMontoPagadoPedido() {

        double montoTotal = 0.0;
        double montoFormaPago = 0.0;
        // KMONCADA 23.10.2014 CAMBIO POR PRECISION DE SUMA
        BigDecimal bMontoTotal = new BigDecimal("0.0");
        BigDecimal bMontoFormaPago = new BigDecimal("0.0");

        for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
            // KMONCADA 13.11.2014 MEJORA LA PRECISION DE LA SUMA CON CLASE BIGDECIMAL 0.1
            bMontoFormaPago =
                    BigDecimal.valueOf(FarmaUtility.getDecimalNumber(((String)tableModelDetallePago.getValueAt(i,
                                                                                                               5)).trim()));
            bMontoTotal = bMontoTotal.add(bMontoFormaPago);
            //montoFormaPago = FarmaUtility.getDecimalNumber(((String)tableModelDetallePago.getValueAt(i,5)).trim());
            //montoTotal =  montoTotal + montoFormaPago;
        }

        montoTotal = bMontoTotal.doubleValue();
        if (FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar) > montoTotal) {
            VariablesCaja.vIndTotalPedidoCubierto = false;
            VariablesCaja.vSaldoPedido =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar) -
                                              montoTotal);
            VariablesCaja.vValVueltoPedido = "0.00";
        } else {
            VariablesCaja.vIndTotalPedidoCubierto = true;
            VariablesCaja.vSaldoPedido = "0.00";
            if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_SOLES)) {
                VariablesCaja.vValVueltoPedido =
                        FarmaUtility.formatNumber(montoTotal - FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar));
            } else {
                //Logica para Recaudacion
                double dSumMontoTotal = 0.00;
                double montoTotalS = 0;
                double montoFormaPagoS = 0;
                double montoTotalD = 0;
                double montoFormaPagoD = 0;

                for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
                    String codMoneda = tableModelDetallePago.getValueAt(i, 6).toString();

                    if (ConstantsCaja.EFECTIVO_DOLARES.equals(codMoneda)) {
                        montoFormaPagoD =
                                FarmaUtility.getDecimalNumber(((String)tableModelDetallePago.getValueAt(i, 5)).trim());
                    } else {
                        montoFormaPagoS =
                                FarmaUtility.getDecimalNumber(((String)tableModelDetallePago.getValueAt(i, 5)).trim());
                    }
                }
                if (montoFormaPagoD > 0) {
                    montoTotalD = montoFormaPagoD - FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                    if (montoTotalD > 0) {
                        montoTotalD = montoTotalD * FarmaUtility.getDecimalNumber(VariablesCaja.vValTipoCambioPedido);
                        dSumMontoTotal = dSumMontoTotal + montoTotalD;
                    }
                }
                if (montoFormaPagoS > 0) {
                    montoTotalS =
                            (montoFormaPagoS + montoFormaPagoD) - FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                    if (montoTotalS > 0) {
                        montoTotalS = montoTotalS * VariablesRecaudacion.vTipoCambioCompra;
                        dSumMontoTotal = dSumMontoTotal + montoTotalS;
                    }
                }

                VariablesCaja.vValVueltoPedido = FarmaUtility.formatNumber(dSumMontoTotal);
            }
        }
        //ERIOS 2.4.6 Log de datos tarjeta
        log.debug("VariablesCaja.vIndTotalPedidoCubierto:" + VariablesCaja.vIndTotalPedidoCubierto);
        log.debug("montoTotal:" + montoTotal);
        lblSaldo.setText(VariablesCaja.vSaldoPedido);
        lblVuelto.setText(VariablesCaja.vValVueltoPedido);
    }

    private boolean validaCodigoFormaPago() {
        if (tableModelDetallePago.getRowCount() <= 0)
            return true;

        for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
            String codTmp = ((String)tableModelDetallePago.getValueAt(i, 0)).trim();
            if (VariablesCaja.vCodFormaPago.equalsIgnoreCase(codTmp))
                return false;
        }
        return true;
    }

    private void limpiaVariablesFormaPago() {
        VariablesCaja.vCodFormaPago = "";
        VariablesCaja.vDescFormaPago = "";
        VariablesCaja.vDescMonedaPago = "";
        VariablesCaja.vValMontoPagado = "";
        VariablesCaja.vValMontoCredito = "";
        VariablesCaja.vValTotalPagado = "";
        VariablesCaja.vNumPedVtaNCR = "";
    }
    
    /*INI ZB 20190719 AGREGAR O REASIGNAR*/
    private void initTempVariablesFormaPago(boolean initVariable){
        if(initVariable){
            VariablesCaja.vCodFormaPagoTemp =   VariablesCaja.vCodFormaPago;
            VariablesCaja.vDescFormaPagoTemp =  VariablesCaja.vDescFormaPago;
            VariablesCaja.vDescMonedaPagoTemp = VariablesCaja.vDescMonedaPago;
            VariablesCaja.vValMontoPagadoTemp = VariablesCaja.vValMontoPagado;
            VariablesCaja.vValMontoCreditoTemp =VariablesCaja.vValMontoCredito;
            VariablesCaja.vValTotalPagadoTemp = VariablesCaja.vValTotalPagado;
            VariablesCaja.vNumPedVtaNCRTemp =   VariablesCaja.vNumPedVtaNCR;
        }else{
            VariablesCaja.vCodFormaPago = VariablesCaja.vCodFormaPagoTemp;   
            VariablesCaja.vDescFormaPago = VariablesCaja.vDescFormaPagoTemp;  
            VariablesCaja.vDescMonedaPago = VariablesCaja.vDescMonedaPagoTemp; 
            VariablesCaja.vValMontoPagado = VariablesCaja.vValMontoPagadoTemp; 
            VariablesCaja.vValMontoCredito = VariablesCaja.vValMontoCreditoTemp;
            VariablesCaja.vValTotalPagado = VariablesCaja.vValTotalPagadoTemp; 
            VariablesCaja.vNumPedVtaNCR = VariablesCaja.vNumPedVtaNCRTemp;   
        }
    }
    /*INI ZB 20190719 AGREGAR O REASIGNAR*/

    private void anularAcumuladoCanje() {
        try {

            String pIndLineaMatriz =
                FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_N);

            boolean pRspCampanaAcumulad =
                UtilityCaja.realizaAccionCampanaAcumulada(pIndLineaMatriz, VariablesVentas.vNum_Ped_Vta, this,
                                                          ConstantsCaja.ACCION_ANULA_PENDIENTE, txtMontoPagado,
                                                          FarmaConstants.INDICADOR_S) //Aqui si liberara stock al regalo
            ;


            if (!pRspCampanaAcumulad) {

                FarmaUtility.liberarTransaccion();
                FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);
            }

            FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);
            FarmaUtility.aceptarTransaccion();

            cerrarVentana(false, null);
        } catch (Exception sql) {
            FarmaUtility.liberarTransaccion();
            FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);

        } finally {
            FarmaConnectionRemoto.closeConnection();
        }
    }


    public void setIndPedirLogueo(boolean pValor) {
        this.indPedirLogueo = pValor;
    }

    public void setIndPantallaCerrarAnularPed(boolean pValor) {
        this.indCerrarPantallaAnularPed = pValor;
    }

    public void setIndPantallaCerrarCobrarPed(boolean pValor) {
        this.indCerrarPantallaCobrarPed = pValor;
    }


    private void colocaFormaPagoDeliveryAutomatico(String pNumPedido) {
        try {
            DBDelivery.cargaFormaPagoPedidoDelAutomatico(tableModelDetallePago.data, pNumPedido);
            tableModelDetallePago.fireTableDataChanged();
        } catch (SQLException ex) {

            log.error(null, ex);
            FarmaUtility.showMessage(this, "Error al obtener forma de pago delivery automatico.\n" +
                    ex.getMessage(), txtMontoPagado);
        }
    }

    private void colocaFormaPagoPedidoConvenio(String pNumPedido) {
        try {
            DBCaja.cargaFormaPagoPedidoConvenio(tableModelDetallePago.data, pNumPedido);
        } catch (SQLException ex) {
            log.error(null, ex);
            FarmaUtility.showMessage(this, "Error al obtener forma de pago delivery automatico.\n" +
                    ex.getMessage(), txtMontoPagado);
        }
    }

    private int cantidadProductosVirtualesPedido(String pNumPedido) {
        int cant = 0;
        try {
            cant = DBCaja.obtieneCantProdVirtualesPedido(pNumPedido);
        } catch (SQLException ex) {

            log.error(null, ex);
            cant = 0;
            FarmaUtility.showMessage(this, "Error al obtener cantidad de productos virtuales.\n" +
                    ex.getMessage(), txtMontoPagado);
        }
        return cant;
    }

    private void evaluaPedidoProdVirtual(String pNumPedido) {
        int cantProdVirtualesPed = 0;
        String tipoProd = "";
        cantProdVirtualesPed = cantidadProductosVirtualesPedido(pNumPedido);
        if (cantProdVirtualesPed <= 0) {
            lblMensaje.setText("");
            lblMsjNumRecarga.setText("");
            lblMensaje.setVisible(false);
            lblMsjNumRecarga.setVisible(false);
            VariablesCaja.vIndPedidoConProdVirtual = false;
            //ERIOS 30.06.2015 Mensaje de Puntos
            muestraMensajePuntos();
        } else {

            tipoProd = obtieneTipoProductoVirtual(pNumPedido);
            if (tipoProd.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_TARJETA)) {
                lblMensaje.setText("<html>El pedido contiene una Tarjeta Virtual. <br>" +
                                   "Si lo cobra, No podr� ser anulado.</html>");
                lblMsjNumRecarga.setText("");
            } else if (tipoProd.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_RECARGA) &&
                !UtilityCaja.get_ind_ped_con_rimac(pNumPedido)   //ASOSA - 15/01/2015 - RIMAC
                ) {
                //ERIOS 15.11.2013 Se elimina el mensaje de minutos para anular [time_max(pNumPedido)]
                lblMensaje.setText("<html>Recarga Virtual.<br>" + " Telefono: </html>");
                lblMsjNumRecarga.setText("" + num_telefono(pNumPedido));

                //INI ASOSA - 03/07/2014
                if (!pEjecutaOldCobro) { //ASOSA - 08/07/2014
                    String numTelefono = num_telefono(pNumPedido);
                    lblMsjNumRecarga.setText("" + numTelefono);
                    String monto = lblSolesTotalVenta.getText();
                    UtilityCaja utilityCaja = new UtilityCaja();
                    utilityCaja.imprVouVerifRecarga(this, tblFormasPago, descProductoRecVirtual, numTelefono, monto,
                                                    VariablesPtoVenta.vImpresoraActual,
                                                    VariablesPtoVenta.vTipoImpTermicaxIp);
                }
                //FIN ASOSA - 03/07/2014

            } else {
                lblMensaje.setText("");
                lblMsjNumRecarga.setText("");
            }
            lblMensaje.setVisible(true);
            lblMsjNumRecarga.setVisible(true);

            VariablesCaja.vIndPedidoConProdVirtual = true;
        }

    }

    private void limpiaVariablesVirtuales() {
        VariablesVirtual.vCodigoComercio = "";
        VariablesVirtual.vTipoTarjeta = "";
        VariablesVirtual.vMonto = "";
        VariablesVirtual.vNumTerminal = "";
        VariablesVirtual.vNumSerie = "";
        VariablesVirtual.vNumTrace = "";
        VariablesVirtual.vIPHost = "";
        VariablesVirtual.vPuertoHost = "";
        VariablesVirtual.vNumeroCelular = "";
        VariablesVirtual.vCodigoProv = "";
        VariablesVirtual.vCodigoAprobacion = "";
        VariablesVirtual.vNumeroTarjeta = "";
        VariablesVirtual.vNumeroPin = "";
        VariablesVirtual.vCodigoRespuesta = "";
        VariablesVirtual.vDescripcionRespuesta = "";
        VariablesVirtual.vArrayList_InfoProdVirtual.clear();

        VariablesVirtual.vNumTraceOriginal = "";
        VariablesVirtual.vCodAprobacionOriginal = "";
        VariablesVirtual.vFechaTX = "";
        VariablesVirtual.vHoraTX = "";
    }

    private String obtieneTipoProductoVirtual(String pNumPedido) {
        String tipoProd = "";
        try {
            tipoProd = DBCaja.obtieneTipoProductoVirtualPedido(pNumPedido);
        } catch (SQLException ex) {

            log.error(null, ex);
            tipoProd = "";
            FarmaUtility.showMessage(this, "Error al obtener cantidad de productos virtuales.\n" +
                    ex.getMessage(), txtMontoPagado);
        }
        return tipoProd;
    }

    private void txtMontoPagado_mouseClicked(MouseEvent e) {
        FarmaUtility.showMessage(this, "No puedes usar el mouse en caja. Realice un uso adecuado del sistema",
                                 txtMontoPagado);
        eventoEscape();
        indBorra = true;
        limpiarPagos();
        limpiarDatos();
        limpiaVariablesFormaPago();
    }

    private String isConvenioCredito(String codConvenio) {
        String indCredito = "";
        try {
            indCredito = DBCaja.verifica_Credito_Convenio(codConvenio.trim());
        } catch (SQLException sql) {

            log.error(null, sql);
            FarmaUtility.showMessage(this, "Error en Obtener si da Credito el Convenio.", txtMontoPagado);
        }

        return indCredito;
    }


    public void initVariables_Auxiliares() {
        VariablesFidelizacion.vRecalculaAhorroPedido = false;

        VariablesCaja.cobro_Pedido_Conv_Credito = "N";
        VariablesCaja.uso_Credito_Pedido_N_Delivery = "N";

        VariablesCaja.arrayPedidoDelivery = new ArrayList();
        VariablesCaja.usoConvenioCredito = "";
        VariablesCaja.valorCredito_de_PedActual = 0.0;
        VariablesCaja.monto_forma_credito_ingresado = "0.00";
    }

    public String num_telefono(String numPed) {
        String num_telefono = "";
        try {
            num_telefono = DBCaja.getNumeroRecarga(numPed);

        } catch (SQLException e) {

            log.error(null, e);
            FarmaUtility.showMessage(this, "Ocurrio un error al obtener el numero de telefono de recarga.\n" +
                    e.getMessage(), null);
        }
        return num_telefono;
    }

    private void cargaDetalleFormaPago(String NumPed) {
        ArrayList array = new ArrayList();
        ArrayList myArray = new ArrayList();
        obtieneDetalleFormaPagoPedido(array, NumPed);

        if (array.size() > 0) {

            for (int j = 0; j < array.size(); j++) {
                myArray.add(((String)((ArrayList)array.get(j)).get(0)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(1)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(2)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(3)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(4)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(5)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(6)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(7)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(8)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(9)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(10)).trim());
                myArray.add(((String)((ArrayList)array.get(j)).get(11)).trim());

                myArray.add("");
                myArray.add("");
                myArray.add("");

                tableModelDetallePago.data.add(myArray);
                tableModelDetallePago.fireTableDataChanged();
            }
        }
        verificaMontoPagadoPedido();
    }

    private void obtieneDetalleFormaPagoPedido(ArrayList array, String vtaNumPed) {

        array.clear();
        try {
            DBCaja.getDetalleFormaPagoCampa�a(array, vtaNumPed);
        } catch (SQLException e) {

            log.error(null, e);
            FarmaUtility.showMessage(this, "Ocurrio un error al obtener detalle forma pago del pedido.\n" +
                    e.getMessage(), null);
        }
    }

    public void pedidoCobrado(DlgFormaPago pDialogo) {
        //jvara para lo de los sobres
        if (VariablesCaja.vIndPedidoCobrado) {
            log.info("pedido cobrado !");
            if (FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL) &&
                indCerrarPantallaCobrarPed) {

                if (validaIngresoSobre()) {

                    if (JConfirmDialog.rptaConfirmDialog(this,
                                                         "Ha excedido el importe m�ximo de dinero en su caja. \n" +
                            "Desea hacer entrega de un nuevo sobre?\n")) {
                        mostrarIngresoSobres();
                    }
                }
                cerrarVentana(true, pDialogo);
            }
            indBorra = true;
            limpiarDatos();
            limpiarPagos();
            limpiaVariablesVirtuales();
            FarmaUtility.moveFocus(txtMontoPagado);
        }
    }

    public void eventoEscape() {
        log.warn("Ingreso al metodo eventoEscape()");
        if (!VariablesCaja.vNumPedVta.equalsIgnoreCase("")) {
            VariablesCaja.vPermiteCampa�a = UtilityCaja.verificaPedidoCamp(this, VariablesCaja.vNumPedVta);
            if (VariablesCaja.vPermiteCampa�a.trim().equalsIgnoreCase("S")) {
                UtilityCaja.actualizaPedidoCupon(this, "", VariablesCaja.vNumPedVta, "N", "S");
            }
        }

        obligarAnularTransaccionPinpad();

        indBorra = false;
        if (FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL)) {
            if (indCerrarPantallaAnularPed &&
                VariablesCaja.vIndPedidoSeleccionado.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                //Se anulara el pedido
                if (VariablesCaja.vIndDeliveryAutomatico.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                    if (JConfirmDialog.rptaConfirmDialog(this, "La venta ser� anulada. �Desea continuar?")) {
                        try { //ERIOS 11.10.2013 Nuevo modelo cobro
                            /*if (VariablesCaja.vIndPedidoCobrado) {
                                UtilityCaja.anularPedidoNuevoCobro(myParentFrame, this, lblVuelto, tblDetallePago,
                                                                   txtMontoPagado);
                            } else {*/
                                DBCaja.anularPedidoPendienteSinRespaldo_02(VariablesCaja.vNumPedVta);
                            //}

                            FarmaUtility.aceptarTransaccion();
                            log.info("Pedido anulado.");
                            FarmaUtility.showMessage(this, "Pedido Anulado Correctamente", null);
                            cerrarVentana(true, null);
                        } catch (SQLException sql) {
                            FarmaUtility.liberarTransaccion();
                            //log.error("",sql);
                            log.error(null, sql);
                            if (sql.getErrorCode() == 20002)
                                FarmaUtility.showMessage(this, "El pedido ya fue anulado!!!", null);
                            else if (sql.getErrorCode() == 20003)
                                FarmaUtility.showMessage(this, "El pedido ya fue cobrado!!!", null);
                            else
                                FarmaUtility.showMessage(this, "Error al Anular el Pedido.\n" +
                                        sql.getMessage(), null);
                            cerrarVentana(true, null);
                        }
                    } else {
                        return;
                    }
                } else {
                    try {
                        if(!isPedidoPendientePinpad){
                        UtilityCaja.liberaProdRegalo(VariablesCaja.vNumPedVta, ConstantsCaja.ACCION_ANULA_PENDIENTE,
                                                     FarmaConstants.INDICADOR_S);
                        /*
                        //ERIOS 11.10.2013 Nuevo modelo cobro
                        if (VariablesCaja.vIndPedidoCobrado) {
                            if (JConfirmDialog.rptaConfirmDialogDefaultNo(this,
                                                                          "La venta ser� anulada. �Desea continuar?")) {
                                UtilityCaja.anularPedidoNuevoCobro(myParentFrame, this, lblVuelto, tblDetallePago,
                                                                   txtMontoPagado);
                            } else
                                return;
                        } else {
                        */
                        DBCaja.anularPedidoPendienteSinRespaldo_02(VariablesCaja.vNumPedVta);
                        //}

                        String pIndLineaMatriz = FarmaConstants.INDICADOR_N;

                        boolean pRspCampanaAcumulad =
                            UtilityCaja.realizaAccionCampanaAcumulada(pIndLineaMatriz, VariablesCaja.vNumPedVta, this,
                                                                      ConstantsCaja.ACCION_ANULA_PENDIENTE,
                                                                      txtMontoPagado,
                                                                      FarmaConstants.INDICADOR_S) //Aqui si liberara stock al regalo
                        ;

                        if (!pRspCampanaAcumulad) {
                            FarmaUtility.liberarTransaccion();
                            FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                  FarmaConstants.INDICADOR_S);
                        }
                        if (pIndLineaMatriz.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                            FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                  FarmaConstants.INDICADOR_S);
                        }

                        FarmaUtility.aceptarTransaccion();
                        log.info("Pedido anulado sin quitar respaldo.");
                        cerrarVentana(false, null);
                        } else {
                                cerrarVentana(false, null);
                            }
                    } catch (SQLException sql) {
                        FarmaUtility.liberarTransaccion();

                        log.error(null, sql);
                        if (sql.getErrorCode() == 20002)
                            FarmaUtility.showMessage(this, "El pedido ya fue anulado!!!", null);
                        else if (sql.getErrorCode() == 20003)
                            FarmaUtility.showMessage(this, "El pedido ya fue cobrado!!!", null);
                        else
                            FarmaUtility.showMessage(this, "Error al Anular el Pedido.\n" +
                                    sql.getMessage(), null);
                        cerrarVentana(true, null);
                    }
                }
            } else
                cerrarVentana(false, null);
        } else
            cerrarVentana(false, null);
    }

    private boolean validaIngresoSobre() {
        boolean valor = false;
        String ind = "";
        try {

            ind = DBCaja.permiteIngreSobre(VariablesCaja.vSecMovCaja);

            if (ind.trim().equalsIgnoreCase("S")) {
                valor = true;
            }

        } catch (SQLException sql) {
            valor = false;
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrio un error validar ingreso de sobre.\n" +
                    sql.getMessage(), null);
        }
        return valor;
    }

    //Metodos

    private void validaInfoPedido(ArrayList pArrayList) {
        if (pArrayList.size() < 1) {
            FarmaUtility.showMessage(this, "El Pedido No existe o No se encuentra pendiente de pago", txtMontoPagado);
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
            limpiarDatos();
            limpiarPagos();
            return;
        } else if (pArrayList.size() > 1) {
            FarmaUtility.showMessage(this, "Se encontro mas de un pedido.\n" +
                    "Ponganse en contacto con el area de Sistemas.", txtMontoPagado);
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
            limpiarDatos();
            limpiarPagos();
            return;
        } else {

            if (intTipoCobro == ConstantsCaja.COBRO_PEDIDO) {
                limpiarPagos();
                limpiaVariablesFormaPago();
                VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_S;
                muestraInfoPedido(pArrayList);
                if (tblDetallePago.getRowCount() < 1) {
                    cargaDetalleFormaPago(VariablesCaja.vNumPedVta);
                }
                verificaMontoPagadoPedido();
            } else if (intTipoCobro == ConstantsCaja.COBRO_CAJA_ELECTRONICA) {
                limpiaVariablesFormaPago();

                muestraInfoPedido(pArrayList);
            } else if (intTipoCobro == ConstantsCaja.COBRO_RECAUDACION) {
                limpiaVariablesFormaPago();

                muestraInfoPedidoRecau(pArrayList);
            }
        }
    }

    private void muestraInfoPedido(ArrayList pArrayList) {
        // KMONCADA 18.04.2016 OBTIENE PERCEPCION
        String valPercepcion = ((String)((ArrayList)pArrayList.get(0)).get(22)).trim();
        double montoPercepcion = FarmaUtility.getDecimalNumber(valPercepcion);
        String valRedondeoPercepcion = ((String)((ArrayList)pArrayList.get(0)).get(23)).trim();
        double montoRedondeoPercepcion = FarmaUtility.getDecimalNumber(valRedondeoPercepcion);
        String valMensajePercepcion = ((String)((ArrayList)pArrayList.get(0)).get(24)).trim();
        String valTotalPedido = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
        double montoTotalPedido = FarmaUtility.getDecimalNumber(valTotalPedido);
        double montoTotalAPagar = montoTotalPedido + montoPercepcion;
        String valTotalAPagar = FarmaUtility.formatNumber(montoTotalAPagar);
        
        if(UtilityPtoVenta.isLocalAplicaPercepcion()){
            lblMontoPercepcion.setText(FarmaUtility.formatNumber(montoPercepcion));
            lblMontoPedido.setText(FarmaUtility.formatNumber(montoTotalPedido));
            lblTipoCliente.setText(valMensajePercepcion);
            lblTotalPercepcion.setVisible(true);
            lblMontoPercepcion.setVisible(true);
            lblTipoCliente.setVisible(true);
            // EN CASO EL REDONDEO SE HA IGUAL AL MONTO DE LA PERCEPCION NO SE MOSTRARA EN EL COBRO DEL PEDIDO
            if(montoPercepcion==montoRedondeoPercepcion){
                montoTotalAPagar = montoTotalPedido;
                valTotalAPagar = FarmaUtility.formatNumber(montoTotalAPagar);
            }
            /*pnlTotal.add(lblAhorro_T, new XYConstraints(189, 22, 145, 20));
            pnlTotal.add(lblAhorro, new XYConstraints(334, 22, 70, 20));
            pnlTotal.add(lblTotalPedido, new XYConstraints(189, 42, 145, 20));
            pnlTotal.add(lblMontoPedido, new XYConstraints(334, 42, 70, 20));
            pnlTotal.add(lblTotalPercepcion, new XYConstraints(189, 62, 145, 20));
            pnlTotal.add(lblMontoPercepcion, new XYConstraints(334, 62, 70, 20));
            pnlTotal.add(lblTotalVenta, new XYConstraints(224, 82, 110, 20));
            pnlTotal.add(lblSolesTotalVenta, new XYConstraints(334, 82, 70, 20));*/
            pnlTotal.add(lblAhorro_T, new XYConstraints(189, 22, 145, 15));
            pnlTotal.add(lblAhorro, new XYConstraints(334, 22, 70, 15));
            pnlTotal.add(lblTotalPedido, new XYConstraints(189, 39, 145, 15));
            pnlTotal.add(lblMontoPedido, new XYConstraints(334, 39, 70, 15));
            pnlTotal.add(lblTotalPercepcion, new XYConstraints(189, 56, 145, 15));
            pnlTotal.add(lblMontoPercepcion, new XYConstraints(334, 56, 70, 15));
            pnlTotal.add(lblTotalVenta, new XYConstraints(224, 73, 110, 15));
            pnlTotal.add(lblSolesTotalVenta, new XYConstraints(334, 73, 70, 15));
            pnlTotal.add(lblRazSoc, new XYConstraints(94, 90, 315, 15));
            pnlTotal.add(lblRazSoc_T, new XYConstraints(4, 90, 85, 15));
            pnlTotal.add(lblTipoCliente, new XYConstraints(4, 109, 405, 15));
        }else{
            lblMontoPercepcion.setText("0.00");
            lblMontoPedido.setText("0.00");
            lblTotalPercepcion.setVisible(false);
            lblMontoPercepcion.setVisible(false);
            lblTotalPedido.setVisible(false);
            lblMontoPedido.setVisible(false);
            lblTipoCliente.setVisible(false);
            
            pnlTotal.add(lblAhorro_T, new XYConstraints(189, 22, 145, 20));
            pnlTotal.add(lblAhorro, new XYConstraints(334, 22, 70, 20));
            /*pnlTotal.add(lblTotalPedido, new XYConstraints(189, 42, 145, 20));
            pnlTotal.add(lblMontoPedido, new XYConstraints(334, 42, 70, 20));
            pnlTotal.add(lblTotalPercepcion, new XYConstraints(189, 62, 145, 20));
            pnlTotal.add(lblMontoPercepcion, new XYConstraints(334, 62, 70, 20));*/
            pnlTotal.add(lblTotalVenta, new XYConstraints(189, 42, 145, 20));
            pnlTotal.add(lblSolesTotalVenta, new XYConstraints(334, 42, 70, 20));
            pnlTotal.add(lblRazSoc, new XYConstraints(94, 109, 315, 15));
            pnlTotal.add(lblRazSoc_T, new XYConstraints(4, 109, 85, 15));
            
        }
        VariablesCaja.vValTotalPagar = valTotalAPagar;
        lblSolesTotalVenta.setText(VariablesCaja.vValTotalPagar);
        
        VariablesCaja.vNumPedVta = ((String)((ArrayList)pArrayList.get(0)).get(0)).trim();
        if (intTipoCobro == ConstantsCaja.COBRO_PEDIDO) {
            if (!UtilityCaja.verificaEstadoPedido(this, VariablesCaja.vNumPedVta, ConstantsCaja.ESTADO_PENDIENTE,
                                                  txtMontoPagado)) {
                VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
                return;
            }
        } else if (intTipoCobro == ConstantsCaja.COBRO_CAJA_ELECTRONICA) {
            if (!UtilityCaja.verificaEstadoPedido(this, VariablesCaja.vNumPedVta, ConstantsCaja.ESTADO_COBRADO,
                                                  txtMontoPagado)) {
                return;
            }
        }
        FarmaUtility.liberarTransaccion();

        

        VariablesCaja.vValTipoCambioPedido = ((String)((ArrayList)pArrayList.get(0)).get(3)).trim();
        lblTipoCambio2.setText(VariablesCaja.vValTipoCambioPedido);
        lblTipoCambio1.setText(VariablesRecaudacion.vTipoCambioCompra + "");

        //ERIOS 22.01.2014 Calcula totales a pagar
        calculaTotalPagar(VariablesCaja.vValTotalPagar);

        VariablesVentas.vTip_Comp_Ped = ((String)((ArrayList)pArrayList.get(0)).get(4)).trim();
        lblTipoComprobante.setText(((String)((ArrayList)pArrayList.get(0)).get(5)).trim());
        VariablesVentas.vNom_Cli_Ped = ((String)((ArrayList)pArrayList.get(0)).get(6)).trim();
        VariablesVentas.vRuc_Cli_Ped = ((String)((ArrayList)pArrayList.get(0)).get(7)).trim();
        VariablesVentas.vDir_Cli_Ped = ((String)((ArrayList)pArrayList.get(0)).get(8)).trim();
        VariablesVentas.vTipoPedido = ((String)((ArrayList)pArrayList.get(0)).get(9)).trim();
        VariablesCaja.vIndPedidoConvenio = ((String)((ArrayList)pArrayList.get(0)).get(14)).trim();
        
        // KMONCADA 08.04.2015 REALIZA LA VALIDACION DE NRO RUC DEL CLIENTE
        if( (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA) ||
            VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT))
            && !"S".equalsIgnoreCase(VariablesCaja.vIndPedidoConvenio)
        ){
            boolean isValido = true;
            if (ConstantsCliente.RESULTADO_RUC_INVALIDO.equalsIgnoreCase(DBCliente.verificaRucValido2(VariablesVentas.vRuc_Cli_Ped))){
                FarmaUtility.showMessage(this, "NRO RUC DE CLIENTE ("+VariablesVentas.vRuc_Cli_Ped+") INVALIDO, VERIFIQUE!!!",
                                         txtMontoPagado);
                isValido = false;   
            }else{
                if (VariablesVentas.vNom_Cli_Ped.trim().length() < 4){
                    FarmaUtility.showMessage(this, "NOMBRE DE CLIENTE ("+VariablesVentas.vNom_Cli_Ped+") NO CUMPLE CON ESTANDAR\n" +
                                             "DEBE CONTENER 04(CUATRO) CARACTERES MINIMO, VERIFIQUE!!!",
                                             txtMontoPagado);
                    isValido = false;
                }
            }
            
            if(!isValido){
                VariablesVentas.vRuc_Cli_Ped = "";
                VariablesVentas.vNom_Cli_Ped = "";
                limpiarDatos();
                limpiarPagos();
                limpiaVariablesVirtuales();
                cerrarVentana(false, null);
            }
        }
        
        if (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA))
            lblRazSoc_T.setText("Razon Social :");
        else
            lblRazSoc_T.setText("Cliente :");

        lblRazSoc.setText(VariablesVentas.vNom_Cli_Ped);
        lblSaldo.setText(VariablesCaja.vValTotalPagar);
        VariablesCaja.vIndDistrGratuita = ((String)((ArrayList)pArrayList.get(0)).get(11)).trim();
        VariablesCaja.vIndDeliveryAutomatico = ((String)((ArrayList)pArrayList.get(0)).get(12)).trim();
        VariablesVentas.vCant_Items_Ped = ((String)((ArrayList)pArrayList.get(0)).get(13)).trim();
        //indicador de Convenio

        VariablesConvenio.vCodConvenio = ((String)((ArrayList)pArrayList.get(0)).get(15)).trim();
        VariablesConvenio.vCodCliente = ((String)((ArrayList)pArrayList.get(0)).get(16)).trim();
        //ERIOS v2.4.5 Se obtiene el copago variable
        vValorSelCopago = FarmaUtility.getDecimalNumber(((String)((ArrayList)pArrayList.get(0)).get(20)).trim());
        VariablesConvenio.vPorcCoPago = FarmaUtility.formatNumber(vValorSelCopago);
        //ERIOS 05.03.2015 Muestra mensaje de ahorro
        String strAhorro[] = ((String)((ArrayList)pArrayList.get(0)).get(21)).trim().split("@");
        lblAhorro_T.setText(strAhorro[0]); 
        lblAhorro.setText(strAhorro[1]); 
        
        evaluaPedidoProdVirtual(VariablesCaja.vNumPedVta);
        evaluaPedidoProdRimac(VariablesCaja.vNumPedVta);    //ASOSA - 12/01/2015 - RIMAC

        if (VariablesCaja.vIndDistrGratuita.equalsIgnoreCase(FarmaConstants.INDICADOR_S) ||
            FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar.trim()) <= 0) {
            VariablesCaja.vIndTotalPedidoCubierto = true;
        } else {
            VariablesCaja.vIndTotalPedidoCubierto = false;
        }
        if (VariablesCaja.vIndDeliveryAutomatico.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            log.info("Es Pedido Delivery");
            colocaFormaPagoDeliveryAutomatico(VariablesCaja.vNumPedVta);
            verificaMontoPagadoPedido();
        }
        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            //Agregado Por FRAMIREZ 16.02.2012
            if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
                //VariablesConvenioBTLMF.vCodConvenio = ((String)((ArrayList)pArrayList.get(0)).get(15)).trim();
                if (((ArrayList)pArrayList.get(0)).get(16) != null) {
                    VariablesConvenioBTLMF.vCodCliente = ((String)((ArrayList)pArrayList.get(0)).get(16)).trim();
                }
                //COLOCAR COMPROBANTES A IMPRIMIR
                if (VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
                    lblTipoComprobante.setText(getMensajeComprobanteConvenio(VariablesConvenioBTLMF.vCodConvenio.trim()));
                }
                adicionaDetallePagoCredito();
            } else if (!VariablesCaja.vIndDeliveryAutomatico.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                log.info("Es Pedido Convenio");
                colocaFormaPagoPedidoConvenio(VariablesCaja.vNumPedVta);
                carga_Credito_Convenio();
                verificaMontoPagadoPedido();
            }
            //KMONCADA 23.06.2016 [CONV FAMISALUD]
            adicionaFormaPagoVtaGratuita();
        }
        cargarFormaPago();
    }

    private void btnMontoDolares_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMontoPagadoDolares);
    }
    
    /**
     * evaluaPedidoProdRimac
     * @author ASOSA
     * @since 12/01/2015
     * @param pNumPedido
     */
    private void evaluaPedidoProdRimac(String pNumPedido) {
        boolean flag = false;
        flag = UtilityCaja.get_ind_ped_con_rimac(pNumPedido);
        if (flag) {
            //INI ASOSA - 03/07/2014
            log.info("VariablesVentas.vVal_Neto_Ped: " + VariablesVentas.vVal_Neto_Ped);
            log.info("VariablesVentas.vVal_Neto_Ped: " + VariablesVentas.vVal_Bruto_Ped);
            UtilityCaja utilityCaja = new UtilityCaja();
            if (!pEjecutaOldCobro) {
                utilityCaja.imprVouVerifRimac(this, 
                                                tblFormasPago, 
                                                descProductoRimac, 
                                                VariablesVentas.vDniRimac, 
                                                String.valueOf(VariablesVentas.vCantMesRimac).trim(),
                                                VariablesVentas.vVal_Neto_Ped,
                                                VariablesPtoVenta.vImpresoraActual,
                                                VariablesPtoVenta.vTipoImpTermicaxIp);
            }            
        }
    }

    public void txtMontoPagadoDolares_keyPressed(KeyEvent e) {
        if (pEjecutaOldCobro || e.getKeyCode() == KeyEvent.VK_ENTER) {
            obtieneDatosFormaPagoPedidoDolares();
            adicionaDetallePago(txtMontoPagadoDolares);
            if (txtMontoTarjeta.isEnabled()) {
                if (VariablesCaja.vIndDatosTarjeta) {
                    FarmaUtility.moveFocus(txtMontoPagado);
                } else {
                    txtMontoTarjeta.setText(calcularRestoParaTarjeta());
                    FarmaUtility.moveFocus(txtMontoTarjeta);
                }
            } else if (!txtMontoTarjeta.isEnabled() && txtMontoPagado.isEditable()) {
                FarmaUtility.moveFocus(txtMontoPagado);
            } else if (!txtMontoTarjeta.isEnabled() && !txtMontoPagado.isEditable()) {
                FarmaUtility.moveFocus(txtMontoPagadoDolares);
            }
        } else
            chkkeyPressed(e);
    }

    private void obtieneDatosFormaPagoPedidoDolares() {
        VariablesCaja.vValEfectivo = txtMontoPagadoDolares.getText().trim();

        VariablesCaja.vCodFormaPago = ConstantsCaja.FORMA_PAGO_EFECTIVO_DOLARES;
        VariablesCaja.vDescFormaPago = ConstantsCaja.DESC_FORMA_PAGO_DOLARES;
        VariablesCaja.vCantidadCupon = "0";

        VariablesCaja.vCodMonedaPago = ConstantsCaja.EFECTIVO_DOLARES;
        VariablesCaja.vDescMonedaPago =
                FarmaLoadCVL.getCVLDescription(FarmaConstants.HASHTABLE_MONEDA, VariablesCaja.vCodMonedaPago);

        VariablesCaja.vValMontoPagado =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(txtMontoPagadoDolares.getText().trim()));

        if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_SOLES)) {
            VariablesCaja.vValTotalPagado =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoPagado) *
                                              FarmaUtility.getDecimalNumber(VariablesCaja.vValTipoCambioPedido));
        } else {
            VariablesCaja.vValTotalPagado = VariablesCaja.vValMontoPagado;
        }
        VariablesCaja.vNumPedVtaNCR = "";
        lblMontoEnSoles2.setText(VariablesCaja.vValTotalPagado);
    }

    private void carga_Credito_Convenio() {

        //indicador si es credito convenio
        String indCredConvenio = isConvenioCredito(VariablesConvenio.vCodConvenio);

        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase("S")) {

            if (indCredConvenio.equalsIgnoreCase("S")) {

                //verificar si hay linea con matriz
                VariablesCaja.vIndLinea =
                        FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S).trim();
                String credito_actual = "0";
                if (VariablesCaja.vIndLinea.equals(FarmaConstants.INDICADOR_S)) {
                    //Se obtiene el porcentaje de la forma de pago
                    VariablesConvenio.vPorcCoPago = obtenerPorcentajeConPago().trim();
                    //si hay linea con matriz
                    //Verirficamos si Tiene Saldo el Cliente..este es el que invocar a un DBLINK CORREGIR a matriz
                    credito_actual =
                            existsCreditoDisponible(VariablesConvenio.vCodCliente, VariablesConvenio.vCodConvenio);
                    double cred_actual = FarmaUtility.getDecimalNumber(credito_actual.trim());
                    //es el campo de la Tabla Temporal
                    double saldo_grabado_credito =
                        FarmaUtility.getDecimalNumber(tblDetallePago.getValueAt(0, 5).toString().trim());

                    if (cred_actual > 0) {

                        VariablesCaja.valorCredito_de_PedActual = saldo_grabado_credito;

                        //Muestra los dato del convenio en pantalla
                        pnlCopago.setVisible(true);
                        lblPorcCopago.setText(VariablesConvenio.vPorcCoPago);
                        lblValCopago.setText(FarmaUtility.formatNumber(VariablesCaja.valorCredito_de_PedActual));

                        String vValTotalPagarPantalla =
                            FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar) -
                                                      VariablesCaja.valorCredito_de_PedActual);
                        calculaTotalPagar(vValTotalPagarPantalla);

                        if (!validaMontoCredito_Convenio())
                            return;

                        FarmaUtility.moveFocus(txtMontoPagado);

                    } else {
                        FarmaUtility.showMessage(this,
                                                 "El cliente no tiene saldo disponible para la forma de pago por convenio.",
                                                 txtMontoPagado);

                    }
                } else {
                    FarmaUtility.showMessage(this,
                                             "Error: En este momento no hay linea con matriz.\nNo podra usar la forma de pago por convenio\n" +
                            "Si el problema persiste comunicarse con el operador de sistema", txtMontoPagado);
                }

            } else {
                FarmaUtility.moveFocus(txtMontoPagado);
            }
        }
    }

    private String existsCreditoDisponible(String codCliente, String codConvenio) {
        String resultado = "";
        try {
            resultado = DBCaja.getSaldoCredClienteMatriz(codCliente, codConvenio, FarmaConstants.INDICADOR_S);
            FarmaUtility.aceptarTransaccion();
            log.debug("credito del cliente : " + resultado);
        } catch (SQLException ex) {
            FarmaUtility.liberarTransaccion();
            log.error("",ex);
            FarmaUtility.showMessage(this, "Error al Obtener credito disponible del Cliente Actual.\n" +
                    ex.getMessage(), txtMontoPagado);
        }
        return resultado;
    }

    private boolean validaMontoCredito_Convenio() {
        String rpta = "S";
        String indCredConvenio = "";
        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase("S")) {
            indCredConvenio = isConvenioCredito(VariablesConvenio.vCodConvenio);

            if (indCredConvenio.equalsIgnoreCase("S")) {

                VariablesCaja.monto_forma_credito_ingresado = "" + VariablesCaja.valorCredito_de_PedActual;
                VariablesCaja.uso_Credito_Pedido_N_Delivery = "S";

            }
        }
        if (rpta.equalsIgnoreCase("N"))
            return false;
        else
            return true;
    }

    private void mostrarIngresoSobres() {
        try{
            DlgIngresoSobre dlgsobre = new DlgIngresoSobre(myParentFrame, "", true);
            dlgsobre.setVisible(true);
    
            if (FarmaVariables.vAceptar) {
                cerrarVentana(true, null);
            }
        }catch(Exception ex){
            log.error(" ",ex);
            FarmaUtility.showMessage(this, "Error Inesperado en el registro de sobres.\nSi problema persiste comun�quese con " +
                "Mesa de Ayuda.", null);
        }
    }
    
    public void txtMontoTarjeta_keyPressed(KeyEvent e, boolean Ejecuta) {
        boolean modoOld = DBCaja.ind_Cobro_Antiguo();
        boolean ind_getDatos_Tarj = true;
        String vConciliacionOnline = DlgProcesar.cargaIndConciliaconOnline();
        
        boolean cobroTarjOh = false;
        if(VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)){
            double valEfectivo = Double.parseDouble(txtMontoPagado.getText().trim());
            double valDolares = Double.parseDouble(txtMontoPagadoDolares.getText().trim());
            double valTarjeta = Double.parseDouble(txtMontoTarjeta.getText().trim());
            
            if(valEfectivo==0 && valDolares == 0 && valTarjeta > 0){
                cobroTarjOh=true;
            }else{
                FarmaUtility.showMessage(this, "El pedido no permite frarcionar el monto en formas de pago diferentes." +
                                               "\nRealice el pago integro con la Tarjeta Oh!",txtMontoPagado);
                return;
            }
        }
            
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER || Ejecuta) {
            cobraConTarjeta(modoOld,cobroTarjOh,ind_getDatos_Tarj,true,false);
        } else
            chkkeyPressed(e);
    }
            
    private void cobraConTarjeta(boolean modoOld,boolean cobroTarjOh,boolean ind_getDatos_Tarj,
                                 boolean pres_Enter,boolean pres_F11){
            //ERIOS 2.2.8 Valida monto a pagar
        presionoEnter=pres_Enter;
            actualizaFormasPago();
            verificaMontoPagadoPedido();

            double tmpMontoTarjeta = 0.0;
            double tmpMontoTotal = 0.0;
            double tmpMontoSaldo = 0.0;
            tmpMontoTarjeta = FarmaUtility.getDecimalNumber(txtMontoTarjeta.getText().trim());
            tmpMontoTotal = FarmaUtility.getDecimalNumber(lblSolesTotalPagar.getText().trim());
            tmpMontoSaldo = FarmaUtility.getDecimalNumber(lblSaldo.getText().trim());
            if (tmpMontoTarjeta > 0) {
                if (tmpMontoTarjeta != tmpMontoSaldo) {
                FarmaUtility.showMessage(this, "El monto de pago con tarjeta debe ser igual monto del saldo a pagar.",
                                             txtMontoTarjeta);
                if(VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)){
                    txtMontoTarjeta.setText(lblSolesTotalVenta.getText().trim());
                    FarmaUtility.moveFocus(txtMontoTarjeta);
                }
                } else {
                    obtieneDatosFormaPagoPedidoTarjeta();
                    String codVoucher = "";
                    if(modoOld){
                    //-------------------------------------------------------------------------------------------------
                        if (VariablesPtoVenta.vIndPinpad.equals("S") &&
                            !VariablesVentas.vEsPedidoDelivery) { //Si es con Pinpad                        
                                DlgDatosTarjetaPinpad objTC = new DlgDatosTarjetaPinpad(myParentFrame, "", true);
                                objTC.setArrayFormPago(arrayCodFormaPago);
                                objTC.setTableModelFormasPago(tableModelFormasPago);
                                objTC.setDlgNuevoCobro(this);
                                objTC.setVisible(true);                            
                        } else { //Si es con POS
                            //KMONCADA 04.09.2015 NUEVA PANTALLA PARA SOLICITAR DATOS DE TARJETA
                            boolean isNuevaVentana = DBCaja.nuevaVentanaDatosTarjetaPOS();
                            //KMONCADA 16.05.2016 OBTIENE LOS LOGOS DE LAS TARJETAS COMO FORMA DE PAGO ASIGNADAS AL LOCAL
                            List lstTarjetaLogo = DBCaja.obtenerTarjetasLogoLocal(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
                            if(cobroTarjOh)
                                lstTarjetaLogo = DBCaja.getTipoLogoTarjeta(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,VariablesFidelizacion.vCodFormaPagoCampanasR);
                            isNuevaVentana = !lstTarjetaLogo.isEmpty(); //SI NO HUBO LISTADO ASIGNADO FUNCIONARA VENTANA ANTIGUA DE TARJETAS
                            if(isNuevaVentana){
                                DlgDatosTipoTarjeta dlgTipoTarjeta = new DlgDatosTipoTarjeta(myParentFrame, "", true);
                                dlgTipoTarjeta.setLstTarjetaLogo(lstTarjetaLogo);
                                
                                //INI AOVIEDO 10/07/2017
                                String indTarjeta = "";
                                if(!VariablesFidelizacion.vCodFormaPagoCampanasR.isEmpty()){
                                    try{
                                        indTarjeta = DBCaja.isIndicadorTarjeta(VariablesFidelizacion.vCodFormaPagoCampanasR);
                                    }catch(Exception ex){
                                        ex.printStackTrace();
                                    }
                                }
                                
                                if(!VariablesFidelizacion.vCodFormaPagoCampanasR.isEmpty() && indTarjeta.equals("S")){
                                    Map mapaFilaTarjeta = new HashMap();
                                    //Inserta la tarjeta seleccionada
                                    try {
                                        mapaFilaTarjeta = DBFidelizacion.getImgTarjetaPago(VariablesFidelizacion.vCodFormaPagoCampanasR);
                                    } catch (SQLException f) {
                                        log.info("------------------------");
                                        log.error(f.getLocalizedMessage());
                                        log.info("------------------------");
                                        f.printStackTrace();
                                        //if(VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)){
                                        if(cobroTarjOh){
                                            mapaFilaTarjeta.put("NOM_TARJETA", "Financiera Oh!");
                                            mapaFilaTarjeta.put("IMG_LOGO", "cmr.png");
                                            mapaFilaTarjeta.put("IMG_AYUDA", "cmr.png");
                                            mapaFilaTarjeta.put("COD_FORMA_PAGO", "00096");
                                        }else{
                                            mapaFilaTarjeta.put("NOM_TARJETA", "BANCO RIPLEY");
                                            mapaFilaTarjeta.put("IMG_LOGO", "banco_ripley.png");
                                            mapaFilaTarjeta.put("IMG_AYUDA", "banco_ripley.png");
                                            mapaFilaTarjeta.put("COD_FORMA_PAGO", "00015");
                                        }
                                    }
                                    lstTarjetaLogo.add(mapaFilaTarjeta);
                                    //TODAS TARJETAS
                                    /*mapaFilaTarjeta = new HashMap();
                                    mapaFilaTarjeta.put("NOM_TARJETA", "OTRAS TARJETAS");
                                    mapaFilaTarjeta.put("IMG_LOGO", "otras_tarjetas.png");
                                    mapaFilaTarjeta.put("IMG_AYUDA", "otras_tarjetas.png");
                                    mapaFilaTarjeta.put("COD_FORMA_PAGO", "T0000");
                                    lstTarjetaLogo.add(mapaFilaTarjeta);*/
                                    dlgTipoTarjeta.setLstTarjetaLogo(lstTarjetaLogo);
                                    dlgTipoTarjeta.setVisible(true);
                                
                                    if (FarmaVariables.vAceptar) {
                                        FarmaVariables.vAceptar = false;
                                        JPanelImagenTransparente pnlTarjetaR = (JPanelImagenTransparente)dlgTipoTarjeta.getPanelSeleccionado();
                                        DlgDatosTarjetaNew objTC = new DlgDatosTarjetaNew(myParentFrame, "", true, pnlTarjetaR);
                                        objTC.setVisible(true);
                                    }
                                }else{
                                    dlgTipoTarjeta.setVisible(true);
                                    
                                    if (FarmaVariables.vAceptar) {
                                        JPanelImagenTransparente pnlTarjeta = (JPanelImagenTransparente)dlgTipoTarjeta.getPanelSeleccionado();
                                        if(pnlTarjeta!=null){
                                            FarmaVariables.vAceptar = false;
                                            DlgDatosTarjetaNew objTC = new DlgDatosTarjetaNew(myParentFrame, "", true, pnlTarjeta);
                                            objTC.setVisible(true);
                                        }else{
                                            FarmaUtility.showMessage(this, "ERROR AL CARGAR LA TARJETA SELECCIONADA, REINTENTE\n" +
                                                                            "SI PERSISTE COMUNICARSE CON MESA DE AYUDA", txtMontoTarjeta);
                                        }
                                    }
                                }
                                //FIN AOVIEDO 10/07/2017
                            }else{
                                DlgDatosTarjeta objTC = new DlgDatosTarjeta(myParentFrame, "", true);
                                objTC.setArrayFormPago(arrayCodFormaPago);
                                if (VariablesVentas.vEsPedidoDelivery) {
                                    objTC.setIndValidaDatos(false);
                                }
                                objTC.setVisible(true);
                            }
                        }
                    //-------------------------------------------------------------------------------------------------
                    }else{
                        DlgDatosTipoTarjeta dlgTipoTarjeta = new DlgDatosTipoTarjeta(myParentFrame, "", true);
                        List lstTarjetaLogo = DBCaja.obtenerTarjetasLogoLocal(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
                    if(cobroTarjOh){
                        JOptionPane.showMessageDialog(this, "El cobro es exclusivo con Tarjeta Oh!", "Descuentos Tarjeta Oh!",JOptionPane.WARNING_MESSAGE);
                            lstTarjetaLogo = DBCaja.getTipoLogoTarjeta(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,VariablesFidelizacion.vCodFormaPagoCampanasR);
                    }
                        //---------------------------------------------------------------------------------------------
                        //INI AOVIEDO 10/07/2017
                        String indTarjeta = "";
                        if(!VariablesFidelizacion.vCodFormaPagoCampanasR.isEmpty()){
                            try{
                                indTarjeta = DBCaja.isIndicadorTarjeta(VariablesFidelizacion.vCodFormaPagoCampanasR);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        
                        if(!VariablesFidelizacion.vCodFormaPagoCampanasR.isEmpty() && indTarjeta.equals("S")){
                            Map mapaFilaTarjeta = new HashMap();
                            //Inserta la tarjeta seleccionada
                            try {
                                mapaFilaTarjeta = DBFidelizacion.getImgTarjetaPago(VariablesFidelizacion.vCodFormaPagoCampanasR);
                            } catch (SQLException f) {
                                //if(VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)){
                                if(cobroTarjOh){
                                    mapaFilaTarjeta.put("NOM_TARJETA", "Financiera Oh!");
                                    mapaFilaTarjeta.put("IMG_LOGO", "cmr.png");
                                    mapaFilaTarjeta.put("IMG_AYUDA", "cmr.png");
                                    mapaFilaTarjeta.put("COD_FORMA_PAGO", "00096");
                                }else{
                                    mapaFilaTarjeta.put("NOM_TARJETA", "BANCO RIPLEY");
                                    mapaFilaTarjeta.put("IMG_LOGO", "banco_ripley.png");
                                    mapaFilaTarjeta.put("IMG_AYUDA", "banco_ripley.png");
                                    mapaFilaTarjeta.put("COD_FORMA_PAGO", "00015");
                                }
                            }
                            lstTarjetaLogo.add(mapaFilaTarjeta); 
                        }
                        //FIN AOVIEDO 10/07/2017
                        //---------------------------------------------------------------------------------------------
                        dlgTipoTarjeta.setLstTarjetaLogo(lstTarjetaLogo);
                        //INI JMONZALVE 24042019
                        Map mapTarjeta = null;
                        try {
                            if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                                dlgTipoTarjeta.setVisible(false);
                                dlgTipoTarjeta.cerrarVentana(true);
                                mapTarjeta = new HashMap();
                                mapTarjeta.put("COD_FORMA_PAGO", "00097");
                                mapTarjeta.put("NOM_TARJETA", "TARJ. COBRO RESPONSABILIDAD");
                            }else{
                                dlgTipoTarjeta.setVisible(true);
                                mapTarjeta = (Map)dlgTipoTarjeta.getMapTajSeleccionado();
                            }
                        } catch (SQLException e) {
                            dlgTipoTarjeta.setVisible(true);
                            mapTarjeta = (Map)dlgTipoTarjeta.getMapTajSeleccionado();
                        }
                        //FIN JMONZALVE 24042019
                        strCodFormaPago = mapTarjeta.get("COD_FORMA_PAGO").toString();
                        tipoTarjeta = mapTarjeta.get("NOM_TARJETA").toString();
                        
                        if(FarmaVariables.vAceptar){
                            boolean cobroPinpad = DBPtoVenta.getIndPinpadxTarjeta(strCodFormaPago,tipoTarjeta);
                            if(cobroTarjOh){
                                try {
                                    mapTarjeta = DBFidelizacion.getImgTarjetaPago(VariablesFidelizacion.vCodFormaPagoCampanasR);
                                    strCodFormaPago = mapTarjeta.get("COD_FORMA_PAGO").toString();
                                    tipoTarjeta = mapTarjeta.get("NOM_TARJETA").toString() + " - "+tipoTarjeta;
                                } catch (SQLException f) {
                                    log.info("---------------------------------------");
                                    log.error(f.getErrorCode()+" - "+f.getMessage());
                                    log.info("---------------------------------------");
                                }
                            }   
                            if(cobroPinpad && !VariablesVentas.vEsPedidoDelivery) {
                                //DlgPINPAD
                                if(ind_getDatos_Tarj){
                                    DlgDatosTarjetaPinpad objTC = new DlgDatosTarjetaPinpad(myParentFrame, "", true);
                                    objTC.setArrayFormPago(arrayCodFormaPago);
                                    objTC.setTableModelFormasPago(tableModelFormasPago);
                                    objTC.setDlgNuevoCobro(this);
                                    objTC.setVisible(true);
                                }else{
                                    //FarmaUtility.showMessage(this, "CARGA PANTALLA PINPAD 02", null);
                                    //RARGUMEDO_PINPAD
                                    conexionPinpad();
                                    DlgInterfacePinpad2 dlgInterfacePinpad2 = new DlgInterfacePinpad2(myParentFrame, "", true);
                                    dlgInterfacePinpad2.inicializarDatos(tipoTarjeta,strCodFormaPago);                                
                                    dlgInterfacePinpad2.setVisible(true);
                                    codVoucher = dlgInterfacePinpad2.getNumAutorizacion();
                                    log.debug("Codigo Autorizacion Pinpad:" + codVoucher);
                                    if (FarmaVariables.vAceptar) {
                                        log.debug("Termino cobreo pinpad: " + codVoucher);
                                        conciliacionPinpad(dlgInterfacePinpad2);
                                        VariablesCaja.vValMontoPagadoTarj = VariablesCaja.vValMontoPagado;
                                    }
                                    dlgInterfacePinpad2.dispose();
                                    dlgInterfacePinpad2 = null;
                                }
                            }else{
                                if (FarmaVariables.vAceptar) {
                                    try {
                                        if(!DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){//JMONZALVE 24042019
                                            JPanelImagenTransparente pnlTarjeta = (JPanelImagenTransparente)dlgTipoTarjeta.getPanelSeleccionado();
                                            if(cobroTarjOh)
                                                pnlTarjeta = (JPanelImagenTransparente)dlgTipoTarjeta.getPanelTarjetaOh();
                                            
                                            if(pnlTarjeta!=null){
                                                FarmaVariables.vAceptar = false;
                                                DlgDatosTarjetaNew objTC = new DlgDatosTarjetaNew(myParentFrame, "", true, pnlTarjeta);
                                                objTC.setVisible(true);
                                            }else{
                                                FarmaUtility.showMessage(this, "ERROR AL CARGAR LA TARJETA SELECCIONADA, REINTENTE\n" +
                                                                                "SI PERSISTE COMUNICARSE CON MESA DE AYUDA", txtMontoTarjeta);
                                            }
                                        }else{//JMONZALVE 24042019
                                            //INI JMONZALVE 24042019
                                            VariablesCaja.vCodFormaPago = ConstantsConv_Responsabilidad.COD_FPAGO_COBRO_RESP;
                                            VariablesCaja.vDescFormaPago = ConstantsConv_Responsabilidad.DESC_FPAGO_COBRO_RESP;
                                            VariablesCaja.vNumTarjCred = "";
                                            VariablesCaja.vFecVencTarjCred = "";
                                            VariablesCaja.vNomCliTarjCred = "";
                                            VariablesCaja.vDNITarj = "";
                                            VariablesCaja.vCodVoucher = "";
                                            VariablesCaja.vCodLote = "";
                                            FarmaVariables.vAceptar = true;
                                        }
                                        //FIN JMONZALVE 24042019
                                    } catch (SQLException e) {
                                        
                                    }
                                    
                                }
                            }
                        }//si es escape 
                    }

                    if (FarmaVariables.vAceptar) {
                        VariablesCaja.vIndDatosTarjeta = true;
                        VariablesCaja.vIndTarjetaSeleccionada = true;
                        adicionaDetallePago(txtMontoTarjeta);
                        lblTipoTrj.setText(VariablesCaja.vDescFormaPago);
                        if ((FarmaUtility.getDecimalNumber(txtMontoTarjeta.getText().trim())) ==
                            (FarmaUtility.getDecimalNumber(lblSolesTotalPagar.getText().trim()))) {
                            txtMontoPagado.setEditable(false);
                            txtMontoPagadoDolares.setEditable(false);
                        }
                        txtMontoTarjeta.setEditable(false);
                        //limpiarVariableTarjeta();
                    if(!pres_F11){
                        funcionF11();
                    }
                    } else {
                    if(VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)){
                        limpiarVariableTarjeta();
                        //txtMontoTarjeta.setText("0.00");
                        FarmaUtility.moveFocus(txtMontoTarjeta);
                    }else{
                        limpiarVariableTarjeta();
                        txtMontoTarjeta.setText("0.00");
                    }
                }

                    if (txtMontoPagado.isEditable()) {
                        FarmaUtility.moveFocus(txtMontoPagado);
                    } else if (txtMontoPagadoDolares.isEditable()) {
                        FarmaUtility.moveFocus(txtMontoPagadoDolares);
                    }

                }
            }
    }
    public void obtieneDatosFormaPagoPedidoTarjeta() {
        VariablesCaja.vValEfectivo = txtMontoTarjeta.getText().trim();

        //VariablesCaja.vCodFormaPago = ConstantsCaja.FORMA_PAGO_EFECTIVO_SOLES;
        //VariablesCaja.vDescFormaPago = ConstantsCaja.FORMA_PAGO
        VariablesCaja.vCantidadCupon = "0";

        VariablesCaja.vCodMonedaPago = ConstantsCaja.EFECTIVO_SOLES;
        VariablesCaja.vDescMonedaPago =
                FarmaLoadCVL.getCVLDescription(FarmaConstants.HASHTABLE_MONEDA, VariablesCaja.vCodMonedaPago);
        VariablesCaja.vValMontoPagado =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(txtMontoTarjeta.getText().trim()));
        VariablesCaja.vValTotalPagado = VariablesCaja.vValMontoPagado;
        VariablesCaja.vNumPedVtaNCR = "";
    }

    private void btnMontoTarjeta_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMontoTarjeta);
    }

    /**
     * Se muestra en el cuadro de texto de tarjeta, el resto a pagar por el pedido
     * @author Luigy Terrazos
     * @since 19.03.2013
     */
    private String calcularRestoParaTarjeta() {
        double dblSaldo = 0.0;
        double tmpDblMonto1 = 0.0;
        double tmpDblMonto2 = 0.0;
        double tmpDblMonto3 = 0.0;
        double tmpDblMonto4 = 0.0;
        double tmpDblMonto5 = 0.0;
        double tmpDblMonto6 = 0.0; //KMONCADA 18.05.2015 monto de notacredito como formapago
        tmpDblMonto1 = FarmaUtility.getDecimalNumber(lblSolesTotalPagar.getText());
        tmpDblMonto2 = FarmaUtility.getDecimalNumber(txtMontoPagado.getText());
        tmpDblMonto3 = FarmaUtility.getDecimalNumber(txtMontoPagadoDolares.getText());
        tmpDblMonto4 = FarmaUtility.getDecimalNumber(VariablesCaja.vValTipoCambioPedido);
        tmpDblMonto5 = FarmaUtility.getDecimalNumber(lblMontoRedencion.getText());
        
        //KMONCADA 18.05.2015 OBTIENE MONTO DE NOTA DE CREDITO COMO FORMA DE PAGO
        for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
            String codTmp = ((String)tableModelDetallePago.getValueAt(i, 0)).trim();
            if (ConstantsCaja.FORMA_PAGO_EFECTIVO_NCR.equalsIgnoreCase(codTmp)){
                tmpDblMonto6 = FarmaUtility.getDecimalNumber(((String)tableModelDetallePago.getValueAt(i, 5)).trim());
                i = tableModelDetallePago.getRowCount() + 1;
            }
        }

        dblSaldo = tmpDblMonto1 - tmpDblMonto2 - (tmpDblMonto3 * tmpDblMonto4) - tmpDblMonto5 - tmpDblMonto6;
        if (dblSaldo <= 0) {
            dblSaldo = 0.0;
        }
        return FarmaUtility.formatNumber(dblSaldo);
    }

    /**
     * Actualiza las formas de pago al momento de pulsar f11
     * @author Luigy Terrazos
     * @since 19.03.2013
     */
    private void actualizaFormasPago() {
        obtieneDatosFormaPagoPedidoSoles();
        adicionaDetallePago(txtMontoPagado);

        obtieneDatosFormaPagoPedidoDolares();
        adicionaDetallePago(txtMontoPagadoDolares);
        // kmoncada 24.09.2014
        convenioMixto();
    }

    public boolean convenioMixto() {
        boolean resultado = false;
        try {
            if (!VariablesCaja.vIndTotalPedidoCubierto && tblDetallePago.getRowCount() == 1) {
                if (DBCaja.isConvenioMixto()) {
                    boolean rspta =
                        JConfirmDialog.rptaConfirmDialog(this, "�Desea realizar el pago del beneficiario con credito?");
                    if (rspta) {
                        double montoFpConvMixto = obtieneDatosFormaPagoPedidoConvMixto();
                        JTextField txtAux = new JTextField();
                        txtAux.setText(FarmaUtility.formatNumber(montoFpConvMixto));
                        adicionaDetallePago(txtAux);
                        resultado = true;
                    }
                }
            }
        } catch (Exception ex) {
            log.info("ERRROR CONSULTADO FLAG DE CONV MIXTO: " + ex.toString());
            resultado = false;
            verificaMontoPagadoPedido();
        }
        return resultado;
    }

    /**
     * Elimina la forma de pago de la tabla
     * @author Luigy Terrazos
     * @since 19.03.2013
     * @param codFormPago Codigo de forma de pago
     */
    private void eliminarFormaPagoTabla(String codFormPago) {
        String codobt = "";
        if (tableModelDetallePago.getRowCount() > 0) {
            for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
                codobt = ((String)tableModelDetallePago.getValueAt(i, 0)).trim();
                if (codobt.equals(codFormPago)) {
                    //ERIOS 2.4.6 Log datos tarjeta
                    log.debug("Elimina F.Pago:" + tableModelDetallePago.data.get(i));
                    tableModelDetallePago.deleteRow(i);
                }
            }
        }
    }

    /**
     * Limpia las variable de tarjeta
     * @author Luigy Terrazos
     * @since 19.03.2013
     */
    private void limpiarVariableTarjeta() {
        //ERIOS 27.01.2014 Limpia indicadores
        VariablesCaja.vIndTarjetaSeleccionada = false;
        VariablesCaja.vIndDatosTarjeta = false;
        VariablesCaja.vNumTarjCred = "";
        VariablesCaja.vDNITarj = "";
        VariablesCaja.vCodVoucher = "";
        VariablesCaja.vCodLote = "";
        VariablesCaja.vFecVencTarjCred = "";
        VariablesCaja.vNomCliTarjCred = "";
    }

    private void inicializaVariablesVentana() {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtMontoPagado);
        lblMensaje.setVisible(false);
        lblMsjNumRecarga.setVisible(false);
        pnlCopago.setVisible(false);

        VariablesCaja.vIndPedidoConProdVirtual = false;
        //Reinicia Variables
        initVariables_Auxiliares();

        limpiarVariableTarjeta();

        //ERIOS 22.11.2013 Muestra moneda a pagar
        if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_DOLARES)) {
            lblSaldoT.setText("SALDO :  $");
            lblTotalVenta.setText("Total : $");
            lblTotalPagar.setText("TOTAL A PAGAR : $");
            lblTotConversionT.setText(ConstantesUtil.simboloSoles);

            jPanelWhite1.setVisible(true);
            jPanelWhite4.setVisible(false);
        } else {
            jPanelWhite1.setVisible(false);
            jPanelWhite4.setVisible(true);
        }
        //INI ASOSA - 19/08/2016 - TELETON. SE DESECHO LA LOGICA PARA MOSTRAR O NO EL TIPO DE CAMBIO DE COMPRA O VENTA, XSIAKSO NO BORRO LO ANTERIOR POR SI SE ARREPIENTEN LUEGO.
        jPanelWhite1.setVisible(false);
        jPanelWhite4.setVisible(true);
        //INI ASOSA - 19/08/2016 - TELETON
        
        //ERIOS 19.02.2015 Redencion puntos
        if(this.isRecaudacionBFP){
            pnlPuntos2.setVisible(true);
            lblSaldoPuntos.setVisible(false);
            lblSaldoPuntos_T.setVisible(false);
            lblPuntosRedimir.setVisible(false);
            lblPuntosRedimir_T.setVisible(false);
        }else{
            pnlPuntos2.setVisible(false);    
        }
        pnlPuntos.setVisible(false);
        //ERIOS 05.03.2015 Mensaje de ahorro
        lblAhorro_T.setVisible(false);
        lblAhorro.setVisible(false);
        //ERIOS 25.06.2015 Desactiva funcionalidad
        lblF9.setVisible(false);
        lblF10.setVisible(false);
        lblF7.setVisible(false);
        
        vActivaF9 = false;        
    }

    private void initVariablesCobro(boolean pEjecOldCobro) {
        if (indPedirLogueo && !pEjecOldCobro) {
            DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
            dlgLogin.setRolUsuario(FarmaConstants.ROL_CAJERO);
            dlgLogin.setVisible(true);
            if (FarmaVariables.vAceptar) {
                FarmaVariables.dlgLogin = dlgLogin;
                if (!UtilityCaja.existeCajaUsuarioImpresora(this, null))
                    cerrarVentana(false, null);
                FarmaVariables.vAceptar = false;

            } else
                cerrarVentana(false, null);
        } else {
            if (!UtilityCaja.existeCajaUsuarioImpresora(this, null) ||
                !UtilityCaja.validaFechaMovimientoCaja(this, txtMontoPagado)) {
                FarmaUtility.showMessage(this, "El Pedido sera Anulado. Vuelva a generar uno nuevo.", null);
                try {
                    DBCaja.anularPedidoPendiente(VariablesVentas.vNum_Ped_Vta);

                    anularAcumuladoCanje();
                    VariablesCaja.vCierreDiaAnul = false;

                    FarmaUtility.aceptarTransaccion();
                    FarmaUtility.showMessage(this, "Pedido Anulado Correctamente", null);
                    cerrarVentana(true, null);
                    return;
                } catch (SQLException sql) {
                    FarmaUtility.liberarTransaccion();
                    log.error("", sql);
                    FarmaUtility.showMessage(this, "Error al Anular el Pedido.\n" +
                            sql.getMessage(), null);
                    cerrarVentana(true, null);
                    return;
                }
            }
            //Problemas al generar la venta, cuando la variable tiene contenido.   Autor Luigy Terrazos
            VariablesCaja.vFecPedACobrar = "";

            buscaPedidoDiario();

            if (VariablesFidelizacion.vSIN_COMISION_X_DNI) {
                lblMensaje.setText("DNI Inv�lido. No aplica Prog. Atenci�n al Cliente");
                lblMensaje.setVisible(true);
            }
            
            //ERIOS 19.02.2015 Redencion puntos
            boolean bValor =
                facadeLealtad.muestraRendencion(this,VariablesCaja.vNumPedVta, pnlPuntos, pnlPuntosAcumulados, lblPuntosAcumulados_T, lblPuntosAcumulados,
                                                pnlPuntosRedimidos, lblPuntosRedimidos_T, pnlMontoRedencion, pnlPuntos2, lblPuntosRedimir_T,
                                                lblPuntosRedimir, lblSaldoPuntos_T, lblSaldoPuntos, lblF9);
            //dubilluz 22.04.2015            
            vActivaF9 = bValor; 
            if (UtilityPuntos.isActivoFuncionalidad()) {
                lblF9.setVisible(true);
                if(!bValor){
                    lblF9.setForeground(Color.GRAY);
                }      
            }
            //ERIOS 25.06.2015 Activa funcionalidad
            lblF10.setVisible(true);
            
            //LMEZA 04.06.2018 ACTIVA FUNCIONALIDAD
            if(isPedidoPendientePinpad){
                lblF7.setVisible(false);
            } else {
                //solo venta mezon y monedero;XXX
                if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
                    VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
                      lblF7.setVisible(false);    
                    }
                else
                    lblF7.setVisible(true);
                }
            //ERIOS 05.03.2015 Mensaje de ahorro
            lblAhorro_T.setVisible(true);
            lblAhorro.setVisible(true);
            
            VariablesCaja.vNumPedPendiente = "";
            FarmaVariables.vAceptar = false;
            FarmaUtility.moveFocus(txtMontoPagado);

        }
    }

    private void initVariablesCajaElectronica() {        
        buscaPedidoDiario();
        VariablesCaja.vNumPedPendiente = "";
        FarmaVariables.vAceptar = false;
        FarmaUtility.moveFocus(txtMontoPagado);
    }

    private void buscaPedidoDiario() {
        ArrayList myArray = new ArrayList();
        try {
            if (intTipoCobro == ConstantsCaja.COBRO_PEDIDO) {
                VariablesCaja.vFecPedACobrar =
                        ""; //Problemas al generar la venta, cuando la variable tiene contenido.   Autor Luigy Terrazos
                DBCaja.obtieneInfoCobrarPedido(myArray, VariablesCaja.vNumPedPendiente, VariablesCaja.vFecPedACobrar);
                validaInfoPedido(myArray);
            } else if (intTipoCobro == ConstantsCaja.COBRO_CAJA_ELECTRONICA) {
                DBCajaElectronica.obtienePedidoaCambiar(myArray, VariablesCajaElectronica.vNumPedVta,
                                                        VariablesCaja.vFecPedACobrar);
                validaInfoPedido(myArray);
            } else if (intTipoCobro == ConstantsCaja.COBRO_RECAUDACION) {
                myArray =
                        facadeRecaudacion.obtenerRcdPend(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                         strCodRecau);
                validaInfoPedido(myArray);
            }
        } catch (SQLException sql) {
            log.error(null, sql);
            FarmaUtility.showMessage(this, "Error al obtener Informacion del Pedido.\n" +
                    sql.getMessage(), txtMontoPagado);
        }
    }

    /**
     * Obtiene el porcentaje de la forma de pago
     * @author Luigy Terrazos
     * @since 25.03.2013
     */
    public String obtenerPorcentajeConPago() {
        String resultado = "";
        try {
            resultado = DBConvenio.obtenerPorcentajeConPago(VariablesConvenio.vCodConvenio);
        } catch (SQLException ex) {
            log.error(null, ex);
        }
        return resultado;
    }

    private void cambiarFormaPago() {
        boolean blnIndicador = false;
        try {
            FacadeCajaElectronica facade = new FacadeCajaElectronica();
            blnIndicador =
                    facade.grabarCambioFormaPago(VariablesCaja.vNumPedVta, tableModelDetallePago.data, lblVuelto.getText().trim());
            if (blnIndicador) {
                cerrarVentana(blnIndicador, null);
            } else {
                FarmaUtility.showMessage(this, "Error realizar la modificaci�n del pedido.\n", txtMontoPagado);
            }
        } catch (Exception e) {
            FarmaUtility.showMessage(this, "Error realizar la modificaci�n del pedido.\n" +
                    e.getMessage(), txtMontoPagado);
        }
    }

    private boolean validarFormaPago() {
        if (!validaMontoFormaPago())
            return false;

        actualizaFormasPago();
        verificaMontoPagadoPedido();


        /* Inicio agregado de GFS por motivo de Recaudacion Prestamos Citibank y Rec. Claro*/
        //Para el caso de recaudacion no se necesita las validaciones de abajo
        //ERIOS 2.2.8 Se quita funcionalidad para Prestamos Citibank
        if ( //ConstantsRecaudacion.TIPO_REC_PRES_CITI.equalsIgnoreCase(tipoRcd) ||
            ConstantsRecaudacion.TIPO_REC_CLARO.equalsIgnoreCase(tipoRcd)) {
            return true;
        }

        if (!VariablesCaja.vIndTotalPedidoCubierto) {
            //ERIOS 2.4.6 Log de datos tarjeta
            log.debug("VariablesCaja.vValTotalPagar:" + VariablesCaja.vValTotalPagar);
            log.debug("VariablesCaja.vSaldoPedido:" + VariablesCaja.vSaldoPedido);
            log.debug("VariablesCaja.vValVueltoPedido:" + VariablesCaja.vValVueltoPedido);
            FarmaUtility.showMessage(this, "El Pedido tiene saldo pendiente de cobro.Verifique!!!", txtMontoPagado);
            return false;
        }
        if (VariablesCaja.vIndDatosTarjeta == false && (Double.parseDouble(txtMontoTarjeta.getText()) != 0)) {
            FarmaUtility.showMessage(this, "Ingrese informacion de la tarjeta.", txtMontoTarjeta);
            return false;
        }
        return true;
    }

    private boolean validaMontoFormaPago() {

        double dblTotalPagar = 0.0;
        double dblMontoSoles = 0.0;
        double dblMontoDolares = 0.0;
        double dblTipoCambio = 0.0;
        double dblMontoTarjeta = 0.0;
        dblTotalPagar = FarmaUtility.getDecimalNumber(lblSolesTotalPagar.getText());

        dblMontoTarjeta = FarmaUtility.getDecimalNumber(txtMontoTarjeta.getText());
        dblTipoCambio = FarmaUtility.getDecimalNumber(VariablesCaja.vValTipoCambioPedido);

        if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_SOLES)) {
            dblMontoSoles = FarmaUtility.getDecimalNumber(txtMontoPagado.getText());
            dblMontoDolares = FarmaUtility.getDecimalNumber(txtMontoPagadoDolares.getText());
        } else {
            dblMontoSoles =
                    FarmaUtility.getDecimalNumber(txtMontoPagado.getText()) * VariablesRecaudacion.vTipoCambioCompra;
            dblMontoDolares = FarmaUtility.getDecimalNumber(txtMontoPagadoDolares.getText());
        }

        if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_SOLES)) {
            //Valida tarjeta,soles,dolares
            if (dblMontoTarjeta == dblTotalPagar) {
                if (dblMontoSoles > 0) {
                    FarmaUtility.showMessage(this,
                                             "Al monto a cobrar ya fue cubierto. No puede ingresar esta forma de pago.",
                                             txtMontoPagado);
                    return false;
                } else if (dblMontoDolares > 0) {
                    FarmaUtility.showMessage(this,
                                             "Al monto a cobrar ya fue cubierto. No puede ingresar esta forma de pago.",
                                             txtMontoPagadoDolares);
                    return false;
                }
            } else if ((dblMontoTarjeta + dblMontoSoles) >= dblTotalPagar) {
                if (dblMontoDolares > 0) {
                    FarmaUtility.showMessage(this,
                                             "Al monto a cobrar ya fue cubierto. No puede ingresar esta forma de pago.",
                                             txtMontoPagadoDolares);
                    return false;
                }
            }
        } else {
            //Para recaudacion en dolares
            if (dblMontoTarjeta == dblTotalPagar) {
                if (dblMontoSoles > 0) {
                    FarmaUtility.showMessage(this,
                                             "Al monto a cobrar ya fue cubierto. No puede ingresar esta forma de pago.",
                                             txtMontoPagado);
                    return false;
                } else if (dblMontoDolares > 0) {
                    FarmaUtility.showMessage(this,
                                             "Al monto a cobrar ya fue cubierto. No puede ingresar esta forma de pago.",
                                             txtMontoPagadoDolares);
                    return false;
                }
            } else if ((dblMontoTarjeta + dblMontoDolares) >= dblTotalPagar) {
                if (dblMontoSoles > 0) {
                    FarmaUtility.showMessage(this,
                                             "Al monto a cobrar ya fue cubierto. No puede ingresar esta forma de pago.",
                                             txtMontoPagado);
                    return false;
                }
            }
        }
        return true;
    }

    private void txtMontoPagado_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMontoPagado, e);
    }

    private void txtMontoPagadoDolares_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMontoPagadoDolares, e);
    }

    private void txtMontoTarjeta_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMontoTarjeta, e);
    }

    public void setIndTipoCobro(int intTipoCobro) {
        this.intTipoCobro = intTipoCobro;
    }

    public void setCodRecau(String strCodRecau) {
        this.strCodRecau = strCodRecau;
    }

    public void setArrayCabeRcd(ArrayList<Object> arrayCabRcd) {
        tmpArrayCabRcd = arrayCabRcd;
        tipoRcd = arrayCabRcd.get(4).toString();
    }

    public void setMontoPagado(String ptotventa) {
        txtMontoPagado.setText(ptotventa);
        //   lblSaldo.setText(String.valueOf(Double.parseDouble(lblSolesTotalPagar.getText())-Double.parseDouble(txtMontoPagado.getText())));
    }

    public void setMontoPagadoDolares(String ptotventadolares) {
        txtMontoPagadoDolares.setText(ptotventadolares);
        // lblSolesTotalVenta.setText(String.valueOf(ptotventadolares*FarmaVariables.vTipCambio));
        // lblSolesTotalPagar.setText(String.valueOf(ptotventadolares*FarmaVariables.vTipCambio));
    }


    public void setNumTarjeta(String pnumtarjeta) {
        lblRazSoc.setText(pnumtarjeta);
    }

    public void cargaFormasPagoSinConvenio(String numPed, String indConvenio, String codConvenio, String codCliente) {
        if (VariablesVentas.vNum_Ped_Vta.trim().length() > 0) {
            numPed = VariablesVentas.vNum_Ped_Vta.trim();
        } else if (VariablesCaja.vNumPedVta.trim().length() > 0) {
            numPed = VariablesCaja.vNumPedVta.trim();
        }
        try {
            DBCaja.obtieneFormasPagoSinConvenio(tableModelFormasPago, indConvenio, codConvenio, codCliente, numPed);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener las Formas de Pago Sin Convenio.\n" +
                    sql.getMessage(), null);
        }
    }

    public void cargaFormasPagoConvenio(String numPed, String indConvenio, String codConvenio, String codCliente,
                                        String valorCredito) {
        try {
            DBCaja.obtieneFormasPagoConvenio(tableModelFormasPago, indConvenio, codConvenio, codCliente, numPed,
                                             valorCredito);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener las Formas de Pago Convenio.\n" +
                    sql.getMessage(), null);
        }
    }

    /**
     * Se agrega logica de Convenios BTLMF
     * @author ERIOS
     * @since 11.06.2013
     * @param codConvenio
     * @return
     */
    private boolean cargaFormasPagoConvenio(String codConvenio) {
        try {
            DBConvenioBTLMF.obtieneFormasPagoConvenio(tableModelFormasPago, codConvenio);
            return true;
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener las Formas de Pago Convenio BTLMF.\n" +
                    sql.getMessage(), null);
            return false;
        }
    }

    public void habilitarCajas(ArrayList<String> codFormaPago, ArrayList<String> indTarje) {
        boolean contFormSol = codFormaPago.contains(ConstantsCaja.FORMA_PAGO_EFECTIVO_SOLES);
        boolean contFormDol = codFormaPago.contains(ConstantsCaja.FORMA_PAGO_EFECTIVO_DOLARES);
        boolean contFormTrj = indTarje.contains(FarmaConstants.INDICADOR_S);
        txtMontoPagado.setEditable(contFormSol);
        txtMontoPagadoDolares.setEditable(contFormDol);
        txtMontoTarjeta.setEnabled(contFormTrj);
    }

    private void cargarFormaPago() {
        //ERIOS 11.06.2013 Se agrega logica de Convenios BTLMF
        String numPed = VariablesCaja.vNumPedVta;
        String indConvenio = VariablesCaja.vIndPedidoConvenio;
        String codConvenio = VariablesConvenio.vCodConvenio;
        String codCliente = VariablesConvenio.vCodCliente;
        String creditoSaldo = "";
        String esCredito = "";
        String valorCredito = "";
        ArrayList<String> indTarje = new ArrayList<>();
        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
            !UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null)) {
            try {
                esCredito = DBConvenio.obtenerPorcentajeCopago(codConvenio);
            } catch (SQLException e) {
                log.error("", e);
                FarmaUtility.showMessage(this, "Error al obtener el porcentaje de la forma de pago." + e.getMessage(),
                                         null);
            }
            if (esCredito.equalsIgnoreCase("S")) {
                try {
                    creditoSaldo = DBConvenio.obtieneConvenioCredito(codConvenio, codCliente, FarmaConstants.INDICADOR_S);
                } catch (SQLException e) {
                    log.error("", e);
                    FarmaUtility.showMessage(this, "Error al obtener el convenio." + e.getMessage(), null);
                }
                if (creditoSaldo.equalsIgnoreCase("S")) {
                    valorCredito = "S";
                } else {
                    valorCredito = "N";
                }
            }
            cargaFormasPagoConvenio(numPed, indConvenio, codConvenio, codCliente, valorCredito);
        } else if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
            cargaFormasPagoSinConvenio(numPed, indConvenio, codConvenio, codCliente);
        } else if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            //Agregado Por FRAMIREZ CargaForma de Pago por convenio
            if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
                cargaFormasPagoConvenio(VariablesConvenioBTLMF.vCodConvenio);
            }
        }
        for (int i = 0; i < tableModelFormasPago.getRowCount(); i++) {
            arrayCodFormaPago.add(((String)tableModelFormasPago.getValueAt(i, 0)).trim());
            indTarje.add(((String)tableModelFormasPago.getValueAt(i, 3)).trim());
        }
        habilitarCajas(arrayCodFormaPago, indTarje);
    }

    private void initVariablesRecaudacion() {
        if (!UtilityCaja.existeCajaUsuarioImpresora(this, null) ||
            !UtilityCaja.validaFechaMovimientoCaja(this, txtMontoPagado)) {
            FarmaUtility.showMessage(this, "La Recaudacion sera Anulado. Vuelva a generar uno nuevo.", null);
            //facadeRecaudacion.anularRCDPend(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, strCodRecau, null);
            cerrarVentanaRecau();
            return;
        }
        
        buscaPedidoDiario();
        VariablesCaja.vNumPedPendiente = "";
        FarmaVariables.vAceptar = false;
        FarmaUtility.moveFocus(txtMontoPagado);
    }

    private void muestraInfoPedidoRecau(ArrayList pArrayList) {
        String tMaxAnulRecaudacion = "";
        String strTipoRecau = "";
        lblRazSoc_T.setText("");
        lblRazSoc.setText("");
        VariablesConvenio.vCodCliente = "";

        //VariablesRecaudacion.vTipCambioCompra
        //INI AOVIEDO 04/09/2017
        //VariablesCaja.vValTipoCambioPedido = VariablesRecaudacion.vTipoCambioVenta + "";
        VariablesCaja.vValTipoCambioPedido = ((String)((ArrayList)pArrayList.get(0)).get(3)).trim();
        //INI AOVIEDO 04/09/2017
        lblTipoCambio2.setText(VariablesCaja.vValTipoCambioPedido);
        lblTipoCambio1.setText(VariablesRecaudacion.vTipoCambioCompra + "");

        VariablesVentas.vTip_Comp_Ped = ((String)((ArrayList)pArrayList.get(0)).get(4)).trim();
        lblTipoComprobante.setText(((String)((ArrayList)pArrayList.get(0)).get(5)).trim());

        String vValTotalPagar = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
        String vValTotalSolesPagar = ((String)((ArrayList)pArrayList.get(0)).get(2)).trim();

        if (ConstantsRecaudacion.TIPO_REC_CMR.equals(tmpArrayCabRcd.get(4).toString()) ||
            ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(tmpArrayCabRcd.get(4).toString())) {

            //lblRazSoc.setText(tmpArrayCabRcd.get(3).toString());
            lblRazSoc_T.setText("Cliente :");

            if (ConstantsRecaudacion.TIPO_REC_CMR.equals(tmpArrayCabRcd.get(4).toString())) {
                strTipoRecau = "CMR";
            } else if (ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(tmpArrayCabRcd.get(4).toString())) {
                strTipoRecau = "Ripley";
            }

            VariablesCaja.vValTotalPagar = vValTotalPagar;

            VariablesVentas.vTipoPedido = ((String)((ArrayList)pArrayList.get(0)).get(9)).trim();
            //ERIOS 21.01.2014 Se quita la funcionalidad de cargar el monto de pago
            /*if(ConstantsCaja.EFECTIVO_SOLES.equals(tmpArrayCabRcd.get(9).toString())){
                txtMontoPagado.setText(tmpArrayCabRcd.get(10).toString());
                obtieneDatosFormaPagoPedidoSoles();
                adicionaDetallePago(txtMontoPagado);

            }else{
                txtMontoPagadoDolares.setText(tmpArrayCabRcd.get(10).toString());
                obtieneDatosFormaPagoPedidoDolares();
                adicionaDetallePago(txtMontoPagadoDolares);

            }*/
            VariablesCaja.vIndPedidoConvenio = ((String)((ArrayList)pArrayList.get(0)).get(14)).trim();
            VariablesConvenio.vCodConvenio = ((String)((ArrayList)pArrayList.get(0)).get(15)).trim();

        } else if (ConstantsRecaudacion.TIPO_REC_PRES_CITI.equals(tmpArrayCabRcd.get(4).toString()) ||
                   ConstantsRecaudacion.TIPO_REC_CITI.equals(tmpArrayCabRcd.get(4).toString()) ||
                ConstantsRecaudacion.TIPO_REC_RAIZ.equals(tmpArrayCabRcd.get(4).toString()) || //ASOSA - 06/08/2015 - RAIZ
				ConstantsRecaudacion.TIPO_REC_INCASUR.equals(tmpArrayCabRcd.get(4).toString()) ||//ASOSA - 17/05/2016 - INCASUR
                ConstantsRecaudacion.TIPO_REC_TELETON.equals(tmpArrayCabRcd.get(4).toString()) ||//ASOSA - 10/08/2016 - TELETON
                ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(tmpArrayCabRcd.get(4).toString()) //ASOSA - 05/09/2017 - TELEROJA
                    ) {

            //VariablesCaja.vValTotalPagar = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
            //TipoRecaudacion="Prestamos Citibank";

            if (ConstantsRecaudacion.TIPO_REC_PRES_CITI.equals(tmpArrayCabRcd.get(4).toString())) {
                strTipoRecau = "Prestamos Citibank";
            } else if (ConstantsRecaudacion.TIPO_REC_CITI.equals(tmpArrayCabRcd.get(4).toString())) {
                strTipoRecau = "Tarjeta Citibank";
            } else if (ConstantsRecaudacion.TIPO_REC_RAIZ.equals(tmpArrayCabRcd.get(4).toString())){ //ASOSA - 06/08/2015 - RAIZ 
                strTipoRecau = "Prestamos Raiz";
            } else if (ConstantsRecaudacion.TIPO_REC_TELETON.equals(tmpArrayCabRcd.get(4).toString())){ //ASOSA - 10/08/2016 - TELETON
                //strTipoRecau = "Donacion Teleton";
                strTipoRecau = "Donacion Teleton";
                UtilityCaja utilityCaja = new UtilityCaja();
                String monto = tmpArrayCabRcd.get(10).toString();
                String dniCe = tmpArrayCabRcd.get(3).toString();
                String tipoMoneda = tmpArrayCabRcd.get(7).toString();
              
               //JVARA 26-09-2017  inicio
               String flag ="";
               try {
                   
               flag = DBCaja.getFlagImpresionTeleton();
                   } catch (SQLException e) {
                   flag="S";
               }
               
                //JVARA 26-09-2017  fin
               
               if(flag!=null && flag.trim().equals("S")){  //JVARA 26-09-2017
                
                utilityCaja.imprVouVerifRecaudacion(this, 
                                                    tblFormasPago, 
                                                    monto, 
                                                    dniCe,
                                                    ConstantsRecaudacion.TIPO_REC_TELETON,
                                                    tipoMoneda,
                                                    "");
                }
                
            }else if (ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(tmpArrayCabRcd.get(4).toString())){ //ASOSA - 10/08/2016 - TELETON
                                //strTipoRecau = "Donacion Teleton";
                                strTipoRecau = "Donacion Cruz Roja";
                                UtilityCaja utilityCaja = new UtilityCaja();
                                String monto = tmpArrayCabRcd.get(10).toString();
                                String dniCe = tmpArrayCabRcd.get(3).toString();
                                String tipoMoneda = tmpArrayCabRcd.get(7).toString();
                                utilityCaja.imprVouVerifRecaudacion(this, 
                                                                    tblFormasPago, 
                                                                    monto, 
                                                                    dniCe,
                                                                    ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA,
                                                                    tipoMoneda,
                                                                    "");
            } else if (ConstantsRecaudacion.TIPO_REC_INCASUR.equals(tmpArrayCabRcd.get(4).toString())){ //ASOSA - 17/05/2016 - INCASUR
                strTipoRecau = "Prestamos Incasur";
            }

            //}else if(ConstantsRecaudacion.TIPO_REC_CITI.equals(tmpArrayCabRcd.get(4).toString())){

            //lblRazSoc.setText(tmpArrayCabRcd.get(3).toString());
            lblRazSoc_T.setText("Cliente :");

            VariablesCaja.vValTotalPagar = vValTotalPagar;

            VariablesVentas.vTipoPedido = ((String)((ArrayList)pArrayList.get(0)).get(9)).trim();
            //ERIOS 21.01.2014 Se quita la funcionalidad de cargar el monto de pago
            /*if(ConstantsCaja.EFECTIVO_SOLES.equals(tmpArrayCabRcd.get(9).toString())){
                txtMontoPagado.setText(tmpArrayCabRcd.get(10).toString());
                obtieneDatosFormaPagoPedidoSoles();
                adicionaDetallePago(txtMontoPagado);

            }else{
                txtMontoPagadoDolares.setText(tmpArrayCabRcd.get(10).toString());
                obtieneDatosFormaPagoPedidoDolares();
                adicionaDetallePago(txtMontoPagadoDolares);

            }*/
            VariablesCaja.vIndPedidoConvenio = ((String)((ArrayList)pArrayList.get(0)).get(14)).trim();
            VariablesConvenio.vCodConvenio = ((String)((ArrayList)pArrayList.get(0)).get(15)).trim();

        } else if (ConstantsRecaudacion.TIPO_REC_CLARO.equals(tmpArrayCabRcd.get(4).toString())) {

            VariablesCaja.vValTotalPagar = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
            strTipoRecau = "Claro";
        } else if (ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(tmpArrayCabRcd.get(4).toString())) {
            VariablesCaja.vValTotalPagar = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
            strTipoRecau = "Banco Financiero";
            UtilityCaja utilityCaja = new UtilityCaja();
            String monto = tmpArrayCabRcd.get(10).toString();
            String dniCe = tmpArrayCabRcd.get(34).toString();
            String tipoMoneda = tmpArrayCabRcd.get(7).toString();
            
            utilityCaja.imprVouVerifRecaudacion(this, 
                                                tblFormasPago, 
                                                monto, 
                                                dniCe,
                                                ConstantsRecaudacion.TIPO_REC_FINANCIERO,
                                                tipoMoneda,
                                                tmpArrayCabRcd.get(26).toString());
            
        }

        lblSolesTotalVenta.setText(VariablesCaja.vValTotalPagar);
        lblSaldo.setText(VariablesCaja.vValTotalPagar);
        calculaTotalPagar(VariablesCaja.vValTotalPagar);

        VariablesCaja.vIndTotalPedidoCubierto = false;

        //RLLANTOY.11.07.2013. Muestra mensaje tiempo m�ximo de anulaci�n x tipo de recaudaci�n.
        tMaxAnulRecaudacion = facadeRecaudacion.tiempoMaxAnulacionRecaudacion("RCD");
        
        String msgRecaudacion = "<html> Recaudaci�n: " + strTipoRecau + " <br> S�lo podr� anularse dentro de " +
                           tMaxAnulRecaudacion + " minutos. </html>";
        if (ConstantsRecaudacion.TIPO_REC_TELETON.equals(tmpArrayCabRcd.get(4).toString())){
            msgRecaudacion = "<html> Recaudaci�n: " + strTipoRecau + " <br> Esta donaci�n no podra anularse. </html>";
        }
        lblMensaje.setVisible(true);
        lblMensaje.setText(msgRecaudacion);

        ArrayList<String> tmpCodFormRCD = new ArrayList<>();
        tmpCodFormRCD = facadeRecaudacion.obtenerCodFormsPagoRCD();
        habilitarCajas(tmpCodFormRCD, tmpCodFormRCD);
    }


    public void grabarRecaudacion() {
        DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
        dlgProcesarPagoTerceros.procesarPagoTerceros(tmpArrayCabRcd, strCodRecau, tableModelDetallePago, lblVuelto,
                                                     txtMontoPagado, txtMontoPagadoDolares);
        dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_PAGO);
        dlgProcesarPagoTerceros.mostrar();

        if (FarmaVariables.vAceptar) {
            cerrarVentana(true, null);
        }
    }

    public void obtenerTipoCambio() {
        try {
            facadeRecaudacion.obtenerTipoCambio();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener Tipo de Cambio del Dia . \n " + sql.getMessage(), null);
        }
    }

    public void cerrarVentanaRecau() {
        //facadeRecaudacion.anularRCDPend(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, strCodRecau);
        cerrarVentana(false, null);
    }

    /**
     * Se agrego logica de Convenios BTLMF
     * @author ERIOS
     * @since 11.06.2013
     * @param pCodConvenio
     * @return
     */
    public String getMensajeComprobanteConvenio(String pCodConvenio) {
        String pCadena = "";
        try {
            double montoPedido = FarmaUtility.getDecimalNumber(lblSolesTotalVenta.getText().trim());

            pCadena =
                    DBConvenioBTLMF.getMsgComprobante(pCodConvenio, montoPedido, VariablesConvenioBTLMF.vValorSelCopago);
        } catch (SQLException e) {
            pCadena = "N";
            log.error("", e);
        }
        return pCadena;
    }

    /**
     * Se agrego logica de Convenios BTLMF
     * @author ERIOS
     * @since 11.06.2013
     */
    private void adicionaDetallePagoCredito() {
        double montoCredito =
            UtilityConvenioBTLMF.obtieneMontoCredito(this, null, new Double(FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar)),
                                                     VariablesCaja.vNumPedVta, "", vValorSelCopago);

        if (montoCredito > 0) {
            /*
             *  obtieneDatosFormaPagoPedidoCredito();
             *  operaListaDetallePago();
             *  verificaMontoPagadoPedido();
             */
            obtieneDatosFormaPagoPedidoCredito();

            //kmoncada 23.06.2014 en el caso de ventas institucionales, no agrega otra forma de pago al pedido.
            if (!(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL.equalsIgnoreCase(VariablesVentas.vTipoPedido) ||
                  ConstantsVentas.TIPO_PEDIDO_DELIVERY.equalsIgnoreCase(VariablesVentas.vTipoPedido)) ||
                dlgFormPago == null) {
                operaListaDetallePago();
            }
            //KMONCADA 14.11.2014 REVISA FP CONVENIO
            if (!(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL.equalsIgnoreCase(VariablesVentas.vTipoPedido))) {
                boolean isContieneFpConvenio = false;
                for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
                    if (ConstantsConvenioBTLMF.COD_FORMA_PAGO_CREDITO.equalsIgnoreCase((String)tableModelDetallePago.getValueAt(i,
                                                                                                                                0))) {
                        isContieneFpConvenio = true;
                        i = tableModelDetallePago.getRowCount() + 1;
                    }
                }
                if (!isContieneFpConvenio) {
                    operaListaDetallePago();
                }
            }
            verificaMontoPagadoPedido();
        }
    }

    /**
     * Se agrego logica de Convenios BTLMF
     * @author ERIOS
     * @since 11.06.2013
     */
    private void obtieneDatosFormaPagoPedidoCredito() {
        VariablesCaja.vCodFormaPago = ConstantsConvenioBTLMF.COD_FORMA_PAGO_CREDITO;
        VariablesCaja.vDescFormaPago =
                UtilityConvenioBTLMF.obtieneFormaPago(this, null, ConstantsConvenioBTLMF.COD_FORMA_PAGO_CREDITO);
        VariablesCaja.vCantidadCupon = "0";

        VariablesCaja.vCodMonedaPago = ConstantsCaja.EFECTIVO_SOLES;
        VariablesCaja.vDescMonedaPago =
                FarmaLoadCVL.getCVLDescription(FarmaConstants.HASHTABLE_MONEDA, VariablesCaja.vCodMonedaPago);

        double montoPagar =
            UtilityConvenioBTLMF.obtieneMontoCredito(this, null, new Double(FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar)),
                                                     VariablesCaja.vNumPedVta, "", vValorSelCopago);
        VariablesCaja.vValMontoCredito = new Double(montoPagar).toString();

        VariablesCaja.vValMontoPagado = FarmaUtility.formatNumber(montoPagar);

        //DJ - RH
        VariablesCaja.vValMontoCredito_2 = montoPagar;

        lblValCopago.setText(VariablesCaja.vValMontoPagado);

        String porcCopago =
            FarmaUtility.formatNumber((FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoPagado) / FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar)) *
                                      100);
        String porcCopagoTemp = porcCopago.replace('.', ' ');

        pnlCopago.setVisible(true);
        if (porcCopagoTemp.trim().equals("100")) {
            lblCopago.setText("Cr�ditos");
            lblPorcCopago.setText(porcCopago);
        } else {
            lblCopago.setText("Monto Empr.");
            lblPorcCopago.setText(porcCopago);
        }
        String vValTotalPagarPantalla =
            FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar) - montoPagar);
        calculaTotalPagar(vValTotalPagarPantalla);

        VariablesCaja.vValTotalPagado = VariablesCaja.vValMontoPagado;
        VariablesCaja.vNumPedVtaNCR = "";
    }

    /**
     * Cobro de pedido
     * @author ERIOS
     * @since 10.09.2013
     */
    boolean presionoEnter = false;
    private synchronized void funcionF11() {
        log.debug("Ingreso al metodo funcionF11()");
        //ERIOS 2.2.9 Sincronizacion de cobro
        if (presionoF11) {
            FarmaUtility.showMessage(this, "El pedido se encuentra en proceso de cobro.", null);
            return;
        } else {
            if(!presionoEnter){
                if (VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)) {
                    boolean modoOld = DBCaja.ind_Cobro_Antiguo();
                    boolean ind_getDatos_Tarj = true;
                    String vConciliacionOnline = DlgProcesar.cargaIndConciliaconOnline();
                
                    double valTarjeta = Double.parseDouble(txtMontoTarjeta.getText().trim());
                    double valPago = Double.parseDouble(lblSolesTotalVenta.getText().trim());
                    if (valTarjeta == valPago) {
                        cobraConTarjeta(modoOld, true, ind_getDatos_Tarj,false,true);
                    } else {
                        FarmaUtility.showMessage(this,
                                                 "El pedido no permite frarcionar el monto en formas de pago diferentes." +
                                                 "\nRealice el pago integro con la Tarjeta Oh!", txtMontoPagado);
                        presionoF11 = false;
                        return;
                    }
                }
            }
            presionoF11 = true;
        }
        if (validarFormaPago()) {
            if (intTipoCobro == ConstantsCaja.COBRO_CAJA_ELECTRONICA) {
                cambiarFormaPago();
            } else if (intTipoCobro == ConstantsCaja.COBRO_RECAUDACION) {
                grabarRecaudacion();
            } else {
                /*
                  * @author ltavara
                  *Validar conexion con el EPOS
                  * @since 29.09.2014
                  * */
                // KMONCADA [FACTURACION ELECTRONICA 2.0]
                //if (UtilityEposTrx.vPermiteCobro(this)) {
                    //LTAVARA

                    //CHUANES 04/09/2014 //verificamos el acceso ala impresora termica cuando es comprobante electronico.
                    //if (UtilityEposTrx.validacionEmiteElectronico()) {
                    // KMONCADA 14.10.2016 [FACTURACION ELECTRONICA 2.0]
                    if (UtilityCPE.isActivoFuncionalidad()) {
                        if (!UtilityCaja.accesoImprTermica(this)) {
                            return;
                        }
                    } else {
                        //CHUANES 14.03.2014 //Verificamos la ruta y el acceso ala impresora correspondiente a imprimir
                        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                            VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
                            //1.Recupera comprobantes por convenio
                            String[] comp = UtilityConvenioBTLMF.getComprobantesConvenio(this);
                            //2.Itera por cada documento
                            for (String pTipoComp : comp) {
                                if (!UtilityCaja.verificaImpresora(this, null, pTipoComp))
                                    return;
                            }
                        } else if (!UtilityCaja.verificaImpresora(this, null, VariablesVentas.vTip_Comp_Ped)) {
                            return;
                        }
                    }


                    if (intTipoCobro == ConstantsCaja.COBRO_PEDIDO) {
                        //JCHAVEZ 09.07.2009.sn graba el tiempo dei nicio de cobro
                        try {
                            DBCaja.grabaInicioFinProcesoCobroPedido(VariablesCaja.vNumPedVta, "I");
                            log.debug("Grabo el tiempo de inicio de cobro");
                        } catch (SQLException sql) {
                            //FALTA CORREGIR ESTO
                            FarmaUtility.liberarTransaccion(); //ASOSA 22.02.2010
                            log.error("", sql);
                            log.debug("Error al grabar el tiempo de inicio de cobro");
                        }

                        if (VariablesVentas.vEsPedidoDelivery &&
                            !(VariablesCaja.vIndDeliveryAutomatico.trim().equalsIgnoreCase("S"))) {
                            // GRABA EL PEDIDO EN TEMPORAL
                            // DUBILLUZ 30.06.2014
                            grabaPedidoProformaDelivery();
                            pedidoCobrado(null);
                        } else {
                            UtilityCaja.grabarNuevoCobro(this, dlgFormPago, myParentFrame);
                            verificarErrorPedidoConvenioMixto();
                            verificaMontoPagadoPedido();
                            //verificarErrorPedidoConvenioMixto();
                            //verificaMontoPagadoPedido();
                        }

                    }
            }
        }
        presionoF11 = false;
    }

    private void verificarErrorPedidoConvenioMixto() {
        try {
            int pos = -1;
            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                String codigoFP = FarmaUtility.getValueFieldArrayList(tableModelDetallePago.data, i, 0);
                if ("00050".equalsIgnoreCase(codigoFP)) {
                    pos = i;
                    i = tblDetallePago.getRowCount() + 1;
                }
            }

            if (pos != -1) {
                String estadoPed = UtilityCaja.obtieneEstadoPedido(null, VariablesCaja.vNumPedVta);
                if (ConstantsCaja.ESTADO_PENDIENTE.equalsIgnoreCase(estadoPed) &&
                    !(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL.equalsIgnoreCase(VariablesVentas.vTipoPedido))) {
                    tableModelDetallePago.deleteRow(pos);
                    tblDetallePago.repaint();
                }
            }
        } catch (Exception ex) {
            log.error("",ex);
        }
    }

    /**
     * Procesa nuevo cobro
     * @author ERIOS
     * @since 10.10.2013
     */
    public void procesarNuevoCobro() {
        log.debug(" 1.1 ********    NUEVO COBRO    ********");
        log.debug("===> procesarNuevoCobro - FarmaVariables.vAceptar: "+FarmaVariables.vAceptar);
        log.debug("*Cobro de Pedido Normal");

        log.info("PEDIDO FIDELIZADO?????");
        log.info("NUMERO DE TARJETA DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vNumTarjeta);
        log.info("NUMERO DE DNI DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vDniCliente);
        if(VariablesPuntos.frmPuntos==null)
            log.info("VariablesPuntos.frmPuntos es null");
        else    
        if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
            log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
        }        
        
        //JCORTEZ 02.07.2008 la generacion de cupones no aplica convenios
        VariablesCaja.vPermiteCampa�a = UtilityCaja.verificaPedidoCamp(this, VariablesCaja.vNumPedVta);
        if (VariablesCaja.vPermiteCampa�a.trim().equalsIgnoreCase("S") && tblDetallePago.getRowCount() > 0) {
            UtilityCaja.validarFormasPagoCupones(this, VariablesCaja.vNumPedVta, tblDetallePago, lblSaldo, lblVuelto);
        }

        UtilityCaja.procesarPedidoNuevoCobro(myParentFrame, this, lblVuelto, tblDetallePago, txtMontoPagado);
        log.info("---------------------procesarNuevoCobro------------------------------");
        log.debug("===> FarmaVariables.vAceptar: "+FarmaVariables.vAceptar);
        
        if (!FarmaVariables.vAceptar) {
            if (VariablesCaja.vCierreDiaAnul) {
                anularAcumuladoCanje();
                VariablesCaja.vCierreDiaAnul = false;
            }

            obligarAnularTransaccionPinpad();

            try {
                DBCaja.anularPedidoPendiente(VariablesVentas.vNum_Ped_Vta);
                FarmaUtility.aceptarTransaccion();
                //FarmaUtility.showMessage(this, "Pedido Anulado Correctamente", null);
                cerrarVentana(true, dlgFormPago);
            } catch (SQLException e) {
                FarmaUtility.liberarTransaccion();
                log.error("", e);
            }
        }
        
        if (VariablesCaja.usoConvenioCredito.equalsIgnoreCase("S")) {
            FarmaConnection.closeConnection();
            FarmaConnection.anularConnection();
        }

    }


    /**
     * Procesa nuevo cobro
     * @author RHERRERA
     * @since 10.04.2014
     */
    public void procesarCobroBD() {
        log.debug(" 1.1 ********    NUEVO COBRO    ********");
        log.debug("===> procesarCobroBD - FarmaVariables.vAceptar: "+FarmaVariables.vAceptar);
        log.debug("*Cobro de Pedido Normal");
        //JCORTEZ 02.07.2008 la generacion de cupones no aplica convenios
        VariablesCaja.vPermiteCampa�a = UtilityCaja.verificaPedidoCamp(this, VariablesCaja.vNumPedVta);
        if (VariablesCaja.vPermiteCampa�a.trim().equalsIgnoreCase("S") && tblDetallePago.getRowCount() > 0) {
            UtilityCaja.validarFormasPagoCupones(this, VariablesCaja.vNumPedVta, tblDetallePago, lblSaldo, lblVuelto);
        }

        // RHERRERA : SE DECLARAN LAS VARIABLES DE CONEXION
        UtilityCaja.finalizarPedidoCobroBD(myParentFrame, this, lblVuelto, tblDetallePago, txtMontoPagado);
        
        log.info("---------------------  procesarCobroBD ------------------------------");
        log.debug("===> FarmaVariables.vAceptar: "+FarmaVariables.vAceptar);
        
        if (!FarmaVariables.vAceptar) {
            if (VariablesCaja.vCierreDiaAnul) {
                anularAcumuladoCanje();
                VariablesCaja.vCierreDiaAnul = false;
            }

            obligarAnularTransaccionPinpad();
            
            try {
                DBCaja.anularPedidoPendiente(VariablesVentas.vNum_Ped_Vta);
                FarmaUtility.aceptarTransaccion();
                //FarmaUtility.showMessage(this, "Pedido Anulado Correctamente", null);
                cerrarVentana(false, null);
            } catch (SQLException e) {
                FarmaUtility.liberarTransaccion();
                log.error("", e);
            }
        }
        

        if (VariablesCaja.usoConvenioCredito.equalsIgnoreCase("S")) {
            FarmaConnection.closeConnection();
            FarmaConnection.anularConnection();
        }

    }

    public void setStrMonedaPagar(String strMonedaPagar) {
        this.strMonedaPagar = strMonedaPagar;
    }

    public String getStrMonedaPagar() {
        return strMonedaPagar;
    }

    /**
     * Muestra el monto a pagar y el valor al tipo de cambio
     * @author ERIOS
     * @since 22.01.2014
     * @param pMonto
     */
    private void calculaTotalPagar(String pMonto) {

        lblSolesTotalPagar.setText(pMonto);

        String strMontoConv = "0.0";
        if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_SOLES)) {
            strMontoConv =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(pMonto) / FarmaUtility.getDecimalNumber(VariablesCaja.vValTipoCambioPedido));
        } else {
            strMontoConv =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(pMonto) * VariablesRecaudacion.vTipoCambioCompra);
        }
        lblTotalConversion.setText(strMontoConv);
    }
    
    private void obligarAnularTransaccionPinpad() { //LLEIVA 09/Ene/2014 Si el pedido posee transacci�n de Pinpad, intentar la anulaci�n hasta se realice correctamente
        if (VariablesCaja.vIndDatosTarjeta) {
            try { //LLEIVA 09-Ene-2014 Se agrupo to_do el procedimiento de obligar a anular la transaccion de pinpad
                UtilityPinpad.obligarAnularTransaccionPinpad(myParentFrame,
                                                             "Es obligatoria la anulaci�n de la transacci�n debido a que no se pudo procesar el pedido");
            } catch (Exception e) {
                log.error("", e);
            }
        }
        //FIN LLEIVA
    }

    public void imprimeFormasPago() {
        log.info("****  imprimeFormasPago ****");
        for (int i = 0; i < tableModelDetallePago.data.size(); i++) {
            ArrayList lista = (ArrayList)tableModelDetallePago.data.get(i);
            log.info("****  FILA  **** " + i + " *****");
            for (int j = 0; j < lista.size(); j++)
                log.info("j=" + j + "-" + lista.get(j));
        }
    }

    public boolean getIndCobraBD() {
        boolean pvalor = false;
        String pCadena = "";
        try {
            pCadena = DBCobroBD.getIndCobroBD();
        } catch (Exception sqle) {
            pCadena = "N";
            log.error("",sqle);
        }

        if (pCadena.trim().equalsIgnoreCase("S"))
            pvalor = true;

        return pvalor;
    }

    public void grabaPedidoProformaDelivery() {
        try {
            //obtiene el monto del vuelto
            String vueltoPedido = lblVuelto.getText().trim();
            log.debug("vuelto del cobro: " + vueltoPedido);
            colocaVueltoDetallePago(vueltoPedido);

            //detalle de formas de pago
            VariablesCaja.vDescripcionDetalleFormasPago = "";
            VariablesCaja.vDescripcionDetalleFormasPago = ConstantsCaja.COLUMNAS_DETALLE_FORMA_PAGO;

            //ERIOS 2.4.4 Muestra resumen pedido delivery
            String vObservPedido = "";
            String fechaSistema = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            String horaSistema = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_HORA);
            ArrayList<ArrayList<String>> formasPagoDelivery = new ArrayList<>();
            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                ArrayList<String> reg = new ArrayList<>();
                reg.add(((String)tblDetallePago.getValueAt(i, 0)).trim());
                reg.add(((String)tblDetallePago.getValueAt(i, 1)).trim());
                reg.add(((String)tblDetallePago.getValueAt(i, 4)).trim());
                formasPagoDelivery.add(reg);
            }
            VariablesCaja.vVuelto = lblVuelto.getText().trim();
            vObservPedido =
                    UtilityDelivery.resumenPedidoVentas(myParentFrame, VariablesCaja.vNumPedVta, VariablesVentas.vNom_Cli_Ped,
                                                        VariablesVentas.vRuc_Cli_Ped, fechaSistema, horaSistema, "",
                                                        "", formasPagoDelivery, lblTipoComprobante.getText(), "",
                                                        false, "");
            if (FarmaVariables.vAceptar) {
                FarmaVariables.vAceptar = false;
            } else {
                return;
            }

            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                //grabar forma de pago del pedido
                //descripcion de la forma de pago en el detalle
                DBCaja.grabaFormaPagoPedido(((String)tblDetallePago.getValueAt(i, 0)).trim(),
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

                VariablesCaja.vDescripcionDetalleFormasPago =
                        VariablesCaja.vDescripcionDetalleFormasPago + FarmaUtility.getValueFieldJTable(tblDetallePago,
                                                                                                       i, 0) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 1) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 3) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 4) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 5) + " , " +
                        FarmaUtility.getValueFieldJTable(tblDetallePago, i, 7) + "<BR>";
            }

            //2.1 Valida formas de pago campana
            String vResValidaCampConFormaPAgo = DBCaja.verificaPagoUsoCampana(VariablesCaja.vNumPedVta);
            log.warn("verificaPagoUsoCampana ERROR:   " + vResValidaCampConFormaPAgo);
            if (vResValidaCampConFormaPAgo.trim().equalsIgnoreCase("ERROR")) {
                log.warn("verificaPagoUsoCampana ERROR");
                FarmaUtility.liberarTransaccion();
                //VariablesCaja.vIndPedidoCobrado = false;
                FarmaUtility.showMessage(this, "La Proforma no se puede generar. \n" +
                        "NO SE PUEDE GRABAR.Porque el descuento usado no cumple con la forma de Pago.", tblFormasPago);
            }

            //2.2 Valida montos de pedido
            String v_Resultado = DBCaja.verificaPedidoFormasPago(VariablesCaja.vNumPedVta);
            log.warn("FORM_PAGO_PEDIDO ERROR:   " + v_Resultado);
            if (v_Resultado.trim().equalsIgnoreCase("ERROR")) {
                log.warn("FORM_PAGO_PEDIDO ERROR");
                FarmaUtility.liberarTransaccion();
                //VariablesCaja.vIndPedidoCobrado = false;
                FarmaUtility.showMessage(this, "La Proforma no se puede generar.\n" +
                        "Los totales de formas de pago y cabecera no coinciden. \n" +
                        "Comun�quese con el Operador de Sistemas inmediatamente." + ". \n" +
                        "NO CIERRE LA VENTANA.", tblFormasPago);
            }

            DBCaja.grabaProformaDelivery(VariablesCaja.vNumPedVta);
            DBCaja.updMotorizadoDLV(VariablesCaja.vNumPedVta, "", "", vObservPedido, VariablesDelivery.vNombreCliente,
                                    VariablesDelivery.vDireccion, VariablesDelivery.vNumeroTelefonoCabecera,
                                    VariablesDelivery.vObsCli);
            FarmaUtility.aceptarTransaccion();
            VariablesCaja.vIndPedidoCobrado = true;
            FarmaUtility.showMessage(this, "Proforma Creada con �xito.", tblFormasPago);
        } catch (Exception e) { //No continuar con la transaccion
            FarmaUtility.liberarTransaccion();
            //VariablesCaja.vIndPedidoCobrado = false;
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al Grabar Proforma\n" +
                    e.getMessage(), txtMontoPagado);
        }

    }

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

    public String getDescProductoRecVirtual() {
        return descProductoRecVirtual;
    }

    public void setDescProductoRecVirtual(String descProductoRecVirtual) {
        this.descProductoRecVirtual = descProductoRecVirtual;
    }

    public void setDlgFormPago(DlgFormaPago dlgFormPago) {
        this.dlgFormPago = dlgFormPago;
    }

    public DlgFormaPago getDlgFormPago() {
        return dlgFormPago;
    }

    private double obtieneDatosFormaPagoPedidoConvMixto() {
        double montoTotal = 0;
        for (int i = 0; i < tableModelDetallePago.getRowCount(); i++) {
            montoTotal =
                    montoTotal + FarmaUtility.getDecimalNumber(((String)tableModelDetallePago.getValueAt(i, 5)).trim());
        }

        double montoFp = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar) - montoTotal;
        String sMontoFp = FarmaUtility.formatNumber(montoFp);

        VariablesCaja.vValEfectivo = sMontoFp;
        VariablesCaja.vCodFormaPago = "00050"; //forma de pago vta al credito
        VariablesCaja.vDescFormaPago = "VTA AL CREDITO";
        VariablesCaja.vCantidadCupon = "0";

        VariablesCaja.vCodMonedaPago = ConstantsCaja.EFECTIVO_SOLES;
        VariablesCaja.vDescMonedaPago =
                FarmaLoadCVL.getCVLDescription(FarmaConstants.HASHTABLE_MONEDA, VariablesCaja.vCodMonedaPago);
        VariablesCaja.vValMontoPagado = sMontoFp;
        if (strMonedaPagar.equals(ConstantsRecaudacion.EST_CTA_DOLARES)) {
            VariablesCaja.vValTotalPagado =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoPagado) /
                                              VariablesRecaudacion.vTipoCambioCompra);
        } else {
            VariablesCaja.vValTotalPagado = VariablesCaja.vValMontoPagado;
        }
        VariablesCaja.vNumTarjCred = "";
        VariablesCaja.vFecVencTarjCred = "";
        VariablesCaja.vNomCliTarjCred = "";
        VariablesCaja.vDNITarj = "";
        VariablesCaja.vNumPedVtaNCR = "";
        lblMontoEnSoles1.setText(VariablesCaja.vValTotalPagado);
        return montoFp;
    }

    public String getDescProductoRimac() {
        return descProductoRimac;
    }

    public void setDescProductoRimac(String descProductoRimac) {
        this.descProductoRimac = descProductoRimac;
    }

    private void funcionF9() {
        limpiarPagos();
        facadeLealtad.ingresarPuntosRedencion(myParentFrame,this,VariablesCaja.vNumPedVta,lblPuntosRedimidos, lblMontoRedencion, pnlPuntosRedimidos);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;                   
            adicionaDetallePago(null);
            //funcionF11();
        }
    }
    
    private void funcionF10() {
        log.debug("Ingreso al metodo funcionF10()");
        //limpiarPagos();
        facadeLealtad.ingresarNotaCredito(myParentFrame,this,VariablesCaja.vNumPedVta,lblSaldo.getText());
        if (FarmaVariables.vAceptar) {            
            FarmaVariables.vAceptar = false;                   
            if (!validaCodigoFormaPago()) {
                limpiaVariablesFormaPago();
                FarmaUtility.showMessage(this, "No puede ingresar m�s de una NCR por pedido.", tblFormasPago);
                return;
            }
            adicionaDetallePago(null);
            FarmaUtility.showMessage(this, "Se agreg� la NCR al pedido. Monto de uso total "+ConstantesUtil.simboloSoles+VariablesCaja.vValTotalPagado,null);
            funcionF11();
        }   
    }
    
    private void funcionF7(){
        log.debug("Ingreso al metodo funcionF7()");
        
        if (VariablesCaja.vIndPedidoConvenio.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null)){
            lblF7.setVisible(false);
            }
        } else {
                if(lblF7.isEnabled()){
                        DlgNumeroPedido dlgNumeroPedido = new DlgNumeroPedido(myParentFrame, "", true);
                        dlgNumeroPedido.setVisible(true);
                        }
        }
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cerrarVentana(true, null);
        }
    }

    /**
     * Muestra mensaje de puntos
     * @author ERIOS
     * @since 30.06.2015
     */
    private void muestraMensajePuntos() {        
        if(VariablesPuntos.frmPuntos!=null){
            if( VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
            lblMensaje.setText(DBPuntos.getMensajePuntosPantallaCobro());
            lblMensaje.setVisible(true);
            }
        }
    }
    
    /**
     * AGREGA FORMA DE PAGO PARA VENTA DE PRODUCTO GRATUITO EN CASO DE CONVENIOS.
     * @authos KMONCADA
     * @since 23.06.2016
     */
    private void adicionaFormaPagoVtaGratuita(){
        if (UtilityConvenioBTLMF.isVtaProdConvenioGratuito(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vCodConvenio, "")) {
            try{
                DBConvenioBTLMF.cargaFormaPagoVtaConvenioGratuito(tableModelDetallePago.data, FarmaVariables.vCodGrupoCia, 
                                                                  FarmaVariables.vCodLocal, VariablesCaja.vNumPedVta);
                tableModelDetallePago.fireTableDataChanged();
            }catch(Exception ex){
                
            }
            verificaMontoPagadoPedido();
        }
        
    }
    
    public void habilitarReservaPedido(boolean isHabilitado){
            lblF7.setEnabled(isHabilitado);}
    
    private String vConciliacionOnline;
    private String strCodFormaPago;
    private String tipoTarjeta;
    
    private void conciliacionPinpad(DlgInterfacePinpad2 dlgInterfacePinpad) {
        String vSalida = "";

        //ERIOS 2.2.8 Conciliacion offline
        if (vConciliacionOnline.equals(FarmaConstants.INDICADOR_N)) {
            log.debug("Conciliacilion offline");
            return;
        }

        try {
            String PLOCAL = facadeRecaudacion.getCodLocalMigra();
            String PID_VENDEDOR = facadeRecaudacion.obtenerDniUsuario(FarmaVariables.vNuSecUsu).trim();
            String PFECHA_VENTA = "";
            try {
                PFECHA_VENTA = FarmaSearch.getFechaHoraBD(1);
            } catch (Exception e) {
                log.error("", e);
            }

            double PMONTO_VENTA = FarmaUtility.getDecimalNumber(VariablesCaja.vValMontoPagado);

            String tempNumCuotas =
                (dlgInterfacePinpad.getCantCuotas() == null) ? "0" : dlgInterfacePinpad.getCantCuotas();
            double PNUM_CUOTAS = FarmaUtility.getDecimalNumber(tempNumCuotas); //solo en venta

            String PCOD_AUTORIZACION = dlgInterfacePinpad.getNumAutorizacion();
            String PTRACK2 = ""; //solo en venta
            String PCOD_AUTORIZACION_PRE = ""; //solo en venta cuando es anulacion
            double PFPA_VALORXCUOTA = 0; //solo en venta
            int PCAJA = Integer.parseInt(VariablesCaja.vNumCaja);
            String PTIPO_TRANSACCION =
                ConstantsRecaudacion.RCD_MODO_VENTA; //solo en venta 1 Venta y 3 venta Anulacion 8 Pago y 9 Pago Anulacion
            String PCODISERV =
                ""; //solo en Recaudacion Pago 02 EstaCta Citibank, 04 Ripley, 07 CMR, 14 Financiero, 15 Claro, 18 Prest Terc. Citibank
            
            String PFPA_NUM_OBJ_PAGO = "";//nroTarjetaBruto.trim(); //Nro de Celular(nro recibo) o Nro de Tarjeta o Nro de Cuenta
            
            String PNOMBCLIE = "";
            long PVOUCHER = Long.parseLong(VariablesVentas.vNum_Ped_Vta); //Nro de Comprobante
            long PNRO_COMP_ANU = 0; //Anulacion-Nro de Comprobante origen
            String PFECH_COMP_ANU = ""; //Anulacion-Fecha Origen del Comprobante
            String PCODIAUTOORIG = ""; //Anulacion-Codigo autorizacion Origen
            double PFPA_TIPOCAMBIO = FarmaVariables.vTipCambio;
            long PFPA_NROTRACE = 0; //PARA TODOS LOS CASOS SERA CERO
            
            //PCOD_ALIANZA      IN NUMBER,      -> Para Recaudacion: Ripley 12,  Citibank 16, Banco Finan 17 , Cmr 7 , Claro 9,
            //                                         Para Venta: CMR(7), Claro(9), Visa(10), Ripley (12),Mastercard(13),American (14), Dinners(15),Citibank(16),Banco Finan(17)
             

            int PCOD_ALIANZA = facadeRecaudacion.getCodigoAlianzaConciliacion(strCodFormaPago);
            
            int PCOD_MONEDA_TRX = 0;//Integer.parseInt(cmbDebitoCredito.getSelectedItem().toString().equals("Credito") ? "5" : "6");

            ////Recaudacion 1 Soles , 2 Dolares, en venta tipo 7(CMR),9 Ripley, 5 Tcredit, 6 Tdebito ,
            String PMON_ESTPAGO = ConstantsRecaudacion.RCD_COD_MONEDA_SOLES;


            //LLEIVA 04-Oct-2013 Guarda un log del resultado de conciliacion
            String descProceso = "";
            if (ConstantsPtoVenta.FORPAG_VISA_PINPAD.equals(strCodFormaPago))
                descProceso = ConstantsCajaElectronica.GUARD_CONC_VENTA_PINPAD_VISA;
            else if (ConstantsPtoVenta.FORPAG_MC_PINPAD.equals(strCodFormaPago))
                descProceso = ConstantsCajaElectronica.GUARD_CONC_VENTA_PINPAD_MASTERCARD;
            else if (ConstantsPtoVenta.FORPAG_DINERS.equals(strCodFormaPago))
                descProceso = ConstantsCajaElectronica.GUARD_CONC_VENTA_PINPAD_DINERS;
            else if (ConstantsPtoVenta.FORPAG_AMEX.equals(strCodFormaPago))
                descProceso = ConstantsCajaElectronica.GUARD_CONC_VENTA_PINPAD_AMEX;

            try {
                vSalida =
                        facadeRecaudacion.setDatosRecauConciliacion(PLOCAL, PID_VENDEDOR, PFECHA_VENTA, PMONTO_VENTA, PNUM_CUOTAS,
                                                                    PCOD_AUTORIZACION, PTRACK2, PCOD_AUTORIZACION_PRE,
                                                                    PFPA_VALORXCUOTA, PCAJA, PTIPO_TRANSACCION,
                                                                    PCODISERV, PFPA_NUM_OBJ_PAGO, PNOMBCLIE, PVOUCHER,
                                                                    PNRO_COMP_ANU, PFECH_COMP_ANU, PCODIAUTOORIG,
                                                                    PFPA_TIPOCAMBIO, PFPA_NROTRACE, PCOD_ALIANZA,
                                                                    PCOD_MONEDA_TRX, PMON_ESTPAGO, descProceso);
            } catch (Exception e) {
                log.error("", e);
            }

            log.debug("Resultado conciliacion: " + vSalida);

        } catch (Exception e) {
            vSalida = "NOK";
            log.error("", e);
        }
    }

    private void conexionPinpad() {
        String codVoucher = "";
        if (!UtilityCaja.existeStockPedido(this, VariablesCaja.vNumPedVta))
            return;

        //INICIO DE VALIDACIONES
        if (!UtilityCaja.validacionesCobroPedido(false, this, this.tableModelFormasPago))
            return;
        
        if (ConstantsPtoVenta.FORPAG_VISA_PINPAD.equalsIgnoreCase(strCodFormaPago) ||
            ConstantsPtoVenta.FORPAG_MC_PINPAD.equalsIgnoreCase(strCodFormaPago) ||
            ConstantsPtoVenta.FORPAG_DINERS.equalsIgnoreCase(strCodFormaPago) ||
            ConstantsPtoVenta.FORPAG_AMEX.equalsIgnoreCase(strCodFormaPago) ||
            (ConstantsPtoVenta.FORPAG_CMR.equalsIgnoreCase(strCodFormaPago) &&
             !VariablesPtoVenta.vIndFarmaSix.equals("S"))) {
            
                DlgInterfacePinpad2 dlgInterfacePinpad = new DlgInterfacePinpad2(myParentFrame, "", true);
                dlgInterfacePinpad.inicializarDatos(tipoTarjeta,strCodFormaPago);
                //inicializarDatos(String numTarjeta, String tipoTarjeta, String strCodFormaPago)
                dlgInterfacePinpad.setVisible(true);
                codVoucher = dlgInterfacePinpad.getNumAutorizacion();
                log.debug("Codigo Autorizacion Pinpad:" + codVoucher);
                if (FarmaVariables.vAceptar) {
                    log.debug("Termino cobreo pinpad: " + codVoucher);
                    conciliacionPinpad(dlgInterfacePinpad);
                    VariablesCaja.vValMontoPagadoTarj = VariablesCaja.vValMontoPagado;
                }
                dlgInterfacePinpad.dispose();
                dlgInterfacePinpad = null;
                
            }
    }
}
