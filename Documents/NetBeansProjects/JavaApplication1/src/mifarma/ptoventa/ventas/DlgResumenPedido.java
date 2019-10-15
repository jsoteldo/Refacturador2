package mifarma.ptoventa.ventas;


import com.gs.mifarma.FarmaMenu.Util.UtilMenu;
import com.gs.mifarma.TestKeyEvents;
import com.gs.mifarma.TestKeyEvents.Key;
import com.gs.mifarma.TestKeyEvents.KeySequence;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor; // JHAMRC 10072019
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.net.URL;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConnectionRemoto;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.iscbolsas.DlgIngCantPed_Bolsas; // JHAMRC 10072019
import mifarma.ptoventa.iscbolsas.reference.VariablesISCBolsas; // JHAMRC 10072019
import mifarma.ptoventa.iscbolsas.reference.ConstantsISCBolsas;
import mifarma.ptoventa.iscbolsas.reference.UtilityISCBolsas;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.administracion.usuarios.reference.DBUsuarios;
import mifarma.ptoventa.caja.DlgFormaPago;
import mifarma.ptoventa.caja.DlgIngresoCompManual;
import mifarma.ptoventa.caja.DlgNuevoCobro;
import mifarma.ptoventa.caja.DlgSeleccionTipoComprobante;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.campAcumulada.DlgListaCampAcumulada;
import mifarma.ptoventa.campAcumulada.reference.VariablesCampAcumulada;
import mifarma.ptoventa.campana.reference.DBCampana;
import mifarma.ptoventa.campana.reference.VariablesCampana;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.cliente.reference.ConstantsCliente;
import mifarma.ptoventa.cliente.reference.DBCliente;
import mifarma.ptoventa.convenioBTLMF.DlgCobroResponsabilidad;
import mifarma.ptoventa.convenioBTLMF.DlgListaPantallaBTLMF;
import mifarma.ptoventa.convenioBTLMF.DlgMensajeRetencion;
import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.FacadeConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.delivery.DlgListaClientes;
import mifarma.ptoventa.delivery.DlgUltimosPedidos;
import mifarma.ptoventa.delivery.reference.VariablesDelivery;
import mifarma.ptoventa.fidelizacion.reference.AuxiliarFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.DBFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.UtilityFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.inventario.reference.UtilityInventario;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.main.FrmEconoFar;
import mifarma.ptoventa.main.FrmEconoMenuFar;
import mifarma.ptoventa.programaXmas1.facade.ProgramaXmas1Facade;
import mifarma.ptoventa.puntos.DlgMensajeBienvenida;
import mifarma.ptoventa.puntos.reference.ConstantsPuntos;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityProgramaAcumula;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recetario.reference.DBRecetario;
import mifarma.ptoventa.recetario.reference.VariablesRecetario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import static mifarma.ptoventa.reference.UtilityPtoVenta.limpiaCadenaAlfanumerica;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.reportes.reference.FacadeReporte;
import mifarma.ptoventa.reportes.reference.VariablesReporte;
import mifarma.ptoventa.ventas.reference.BeanDetalleVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityCalculoPrecio;
import mifarma.ptoventa.ventas.reference.UtilityPacksPromo;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2005 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : DlgResumenPedido.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * LMESIA      29.12.2005   Creaci�n<br>
 * PAULO       28.04.2006   Modificacion<br>
 * ASOSA        17.02.2010 Modificacion <br>
 * ERIOS       03.07.2013   Modificacion<br>
 * ASOSA       03.07.2014   Modificacion <br>
 * LTAVARA     28.08.2014  Modificacion <br>
 * LTAVARA     15.08.2016  Modificacion <br>
 * <br>
 * @author Luis Mesia Rivera<br>
 * @version 1.0<br>
 */

public class DlgResumenPedido extends JDialog {

    private double diferencia = 0;
    private String valor = "";


    private static final Logger log = LoggerFactory.getLogger(DlgResumenPedido.class);

    private String fechaPedido = "";

    //flag que controla la toma del pedido
    private boolean pedidoGenerado = false;

    /** Almacena el Objeto Frame de la Aplicaci�n - Ventana Principal */
    private Frame myParentFrame;

    /** Almacena el Objeto TableModel de los Productos del Pedido */
    private FarmaTableModel tableModelResumenPedido;

    /**
     * Columnas de la grilla
     * @author Edgar Rios Navarro
     * @since 10.04.2008
     */
    /* Resumen Pedido */
    //JCORTEZ 17.04.08
    private final int COL_RES_DSCTO = 5;
    private final int COL_RES_VAL_FRAC = 10;
    private final int COL_RES_ORIG_PROD = 19;
    private final int COL_RES_IND_PACK = 20;
    private final int COL_RES_DSCTO_2 = 21;
    private final int COL_RES_IND_TRAT = 22;
    private final int COL_RES_CANT_XDIA = 23;
    private final int COL_RES_CANT_DIAS = 24;
    private final int COL_RES_CUPON = 25;
    private final int COL_CANT_ITEM_QUOTE = 29;

    // DUBILLUZ 09.07.2008
    private final int COL_COD_CAMPANA = 0;
    private final int COL_TIPO_CAMPANA = 1;
    private final int COL_IND_MENSAJE_CAMPANA = 2;

    // COLUMNAS DE RESULATADO PARA PROCESAR CAMPA�AS CUPON
    private final int P_COL_COD_PROD = 0;
    private final int P_COL_COD_CAMPANA = 1;
    private final int P_COL_PRIORIDAD = 2;
    private final int P_COL_VALOR_CUPON = 3;
    private final int P_COL_TIPO_CUPON = 4;

    //COLUMNA COD_PRODUCTO_EQUIVALENTE
    private final int COL_COD_PROD_EQ = 26;

    /**
     * Flag para determinar el estado de generacion del pedido.
     * @author Edgar Rios Navarro
     * @since 14.04.2008
     */
    private boolean pedidoEnProceso = false;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JPanel pnlTotalesD = new JPanel();
    private XYLayout xYLayout6 = new XYLayout();
    private JLabel lblTotalD = new JLabel();
    private JLabel lblTotalS = new JLabel();
    private JLabel lblTotalDT = new JLabel();
    private JLabel lblTotalST = new JLabel();
    private JLabel lblRedondeoT = new JLabel();
    private JLabel lblRedondeo = new JLabel();
    private JLabel lblCreditoT = new JLabel();
    private JLabel lblCredito = new JLabel();
    private JLabel lblPorPagarT = new JLabel();
    private JLabel lblPorPagar = new JLabel();
    private JPanel pnlTotalesT = new JPanel();
    private XYLayout xYLayout5 = new XYLayout();
    private JLabel lblDsctoPorc = new JLabel();
    private JLabel lblTotalesT = new JLabel();
    private JLabel lblBrutoT = new JLabel();
    private JLabel lblBruto = new JLabel();
    private JLabel lblDsctoT = new JLabel();
    private JLabel lblDscto = new JLabel();
    private JLabel lblIGVT = new JLabel();
    private JLabel lblIGV = new JLabel();
    private JScrollPane scrProductos = new JScrollPane();
    private JPanel pnlProductos = new JPanel();
    private XYLayout xYLayout2 = new XYLayout();
    private JButton btnRelacionProductos = new JButton();
    private JLabel lblItemsT = new JLabel();
    private JLabel lblItems = new JLabel();
    private JPanel pnlAtencion = new JPanel();
    private XYLayout xYLayout4 = new XYLayout();
    private JLabel lblUltimoPedido = new JLabel();
    private JLabel lblUltimoPedidoT = new JLabel();
    private JLabel lblVendedor = new JLabel();
    private JLabel lblNombreVendedor = new JLabel();
    private JLabel lblTipoCambio = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel lblTipoCambioT = new JLabel();
    private JLabel lblFechaT = new JLabel();
    private JLabelFunction lblF3 = new JLabelFunction();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction lblF8 = new JLabelFunction(); //LTAVARA 28.08.2014
    private JTable tblProductos = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JLabelWhite lblCliente_T = new JLabelWhite();

    private JLabelWhite lblLCredito_T = new JLabelWhite();

    private JLabelWhite lblBeneficiario_T = new JLabelWhite();


    private JLabelWhite lblCliente = new JLabelWhite();

    private JLabelFunction lblF9 = new JLabelFunction();
    private JLabelFunction lblF12 = new JLabelFunction();

    private int CON_COLUM_COD_PROD_REGALO = 2;
    private int CON_COLUM_MONT_MIN_ENCARTE = 3;
    private int CON_COLUM_CANT_MAX_PROD_REGALO = 4;

    private int CON_COLUM_COD_CUPON = 1;
    private int CON_COLUM_DESC_CUPON = 2;
    private int CON_COLUM_MONT_CUPON = 3;
    private int CON_COLUM_CANT_CUPON = 4;

    //jquispe 22.04.2010 cambio para leer cod de barra
    private final int DIG_PROD = 6;
    private final int COL_COD = 1;

    private JLabel lblMensajeCupon = new JLabel();
    private JPanel pnlAtencion1 = new JPanel();
    private XYLayout xYLayout7 = new XYLayout();
    private JLabel lblUltimoPedido1 = new JLabel();
    private JLabel lblUltimoPedidoT1 = new JLabel();
    private JLabel lblVendedor1 = new JLabel();
    private JLabel lblNombreVendedor1 = new JLabel();
    private JLabel lblTipoCambio1 = new JLabel();
    private JLabel lblFecha1 = new JLabel();
    private JLabel lblTipoCambioT1 = new JLabel();
    private JLabel lblFechaT1 = new JLabel();
    private JTextFieldSanSerif txtDescProdOculto = new JTextFieldSanSerif();

    private boolean varNumero = false;
    private JLabelWhite lblProdOculto_T = new JLabelWhite();
    private JLabelFunction lblF6 = new JLabelFunction();
    private JTextArea txtMensajesPedido = new JTextArea();
    private JScrollPane jScrollPane1 = new JScrollPane();

    private JLabel jLabel1 = new JLabel();
    private JLabel lblCuponIngr = new JLabel();
    private JPanel pnlTotalesT1 = new JPanel();
    private XYLayout xYLayout8 = new XYLayout();
    private JPanelHeader jPanelHeader1 = new JPanelHeader();

    private boolean vEjecutaAccionTeclaResumen = false;
    private JLabel lblDNI_Anul = new JLabel();
    private JLabel lblTopeAhoro = new JLabel();
    private JLabelFunction lblF7 = new JLabelFunction();

    Border border = LineBorder.createGrayLineBorder();
    private JLabel lblFormaPago = new JLabel();
    private boolean pasoTarjeta = false;
    private JLabel lblDNI_SIN_COMISION = new JLabel();
    private JLabel lblMedico = new JLabel();
    //private FacadeRecetario facadeRecetario = new FacadeRecetario();
    private FrmEconoFar frmEconoFar;
    private FrmEconoMenuFar frmEconoMenuFar;
    private boolean vIndPedidosDelivery;
    String vInd_Menu="";
    //kmoncada 16.07.2014 variable para la combinacion de teclas.
    private int contarCombinacion = 0;

    //Dubilluz 13.10.2014
    private boolean pIngresoComprobanteManual = false;
    
    //KMONCADA 2015.03.19
    private FarmaTableModel tableModelResumenPedidoAux;
    private ArrayList lstAuxResumenPedido = new ArrayList();
    //private boolean actuaRobot = false;
    //private boolean actuaRobotDetalle = false;
    //private String numPedVtaRobot = "";
    //private String cadenaTex = "";
    private JLabel lblReceta = new JLabel();
    
    private boolean vAccionAcumula = false;

	private JLabel lblICBPERT = new JLabel(); // JHAMRC 10072019
    private JLabel lblICBPER = new JLabel(); // JHAMRC 10072019

    // **************************************************************************
    // Constructores
    // **************************************************************************

    /**
     *Constructor
     */
    public DlgResumenPedido() {
        this(null, "", false, false);
    }

    /**
     *Constructor
     *@param parent Objeto Frame de la Aplicaci�n.
     *@param title T�tulo de la Ventana.
     *@param modal Tipo de Ventana.
     */
    public DlgResumenPedido(Frame parent, String title, boolean modal, boolean vRegulaManual) {
        super(parent, title, modal);
        myParentFrame = parent;
        pIngresoComprobanteManual = vRegulaManual;
        try {
            jbInit();
            initialize();
            //      lblItems.setText("0");
            lblBruto.setText("0.00");
            lblDscto.setText("0.00");
            lblIGV.setText("0.00");
            lblTotalS.setText("0.00");
            lblTotalD.setText("0.00");
            lblCuponIngr.setText("");
            lblMensajeCupon.setText("");
            lblCreditoT.setText("");
            lblCredito.setText("");
            lblPorPagarT.setText("");
            lblPorPagar.setText("");
            setISCTotal(); // JHAMRC 10072019
        } catch (Exception e) {
            log.error("", e);
        }
    }

    // **************************************************************************
    // M�todo "jbInit()"
    // **************************************************************************

    /**
     *Implementa la Ventana con todos sus Objetos
     */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(752, 583));
        this.getContentPane().setLayout(borderLayout1);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("Resumen de Pedido" + " /  IP : " + FarmaVariables.vIpPc);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds(new Rectangle(10, 10, 752, 583));
        this.setBackground(Color.white);
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
        jContentPane.setSize(new Dimension(742, 423));
        pnlTotalesD.setFont(new Font("SansSerif", 0, 12));
        pnlTotalesD.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlTotalesD.setLayout(xYLayout6);
        pnlTotalesD.setBounds(new Rectangle(10, 340, 715, 35));
        pnlTotalesD.setBackground(new Color(43, 141, 39));
        lblTotalD.setText("9,990.00");
        lblTotalD.setFont(new Font("SansSerif", 1, 14));
        lblTotalD.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalD.setForeground(Color.white);
        lblTotalS.setText("99,990.00");
        lblTotalS.setFont(new Font("SansSerif", 1, 14));
        lblTotalS.setForeground(Color.white);
        lblTotalS.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalDT.setText("US$");
        lblTotalDT.setFont(new Font("SansSerif", 1, 14));
        lblTotalDT.setForeground(Color.white);
        lblTotalST.setText("TOTAL : "+ConstantesUtil.simboloSoles);
        lblTotalST.setFont(new Font("SansSerif", 1, 14));
        lblTotalST.setForeground(Color.white);
        lblRedondeoT.setText("Red. "+ConstantesUtil.simboloSoles);
        lblRedondeoT.setFont(new Font("SansSerif", 1, 14));
        lblRedondeoT.setForeground(Color.white);
        lblRedondeo.setText("-0.00");
        lblRedondeo.setFont(new Font("SansSerif", 1, 14));
        lblRedondeo.setForeground(Color.white);
        lblCreditoT.setText("Cr�dito(100%): "+ConstantesUtil.simboloSoles);

        //lblCreditoT.setVisible(false);
        lblCreditoT.setFont(new Font("SansSerif", 1, 14));
        lblCreditoT.setForeground(new Color(43, 141, 39));
        lblCreditoT.setBounds(new Rectangle(225, 315, 160, 20));
        lblCredito.setText("100.00");
        //lblCredito.setVisible(false);
        lblCredito.setFont(new Font("SansSerif", 1, 14));
        lblCredito.setForeground(new Color(43, 141, 39));
        lblCredito.setBounds(new Rectangle(390, 315, 75, 20));
        lblPorPagarT.setText("CoPago: "+ConstantesUtil.simboloSoles);
        //lblPorPagarT.setVisible(false);
        lblPorPagarT.setFont(new Font("SansSerif", 1, 14));
        lblPorPagarT.setForeground(Color.white);
        lblPorPagar.setText("99.99");
        //lblPorPagar.setVisible(false);
        lblPorPagar.setFont(new Font("SansSerif", 1, 14));
        lblPorPagar.setForeground(Color.white);
        pnlTotalesT.setFont(new Font("SansSerif", 0, 12));
        pnlTotalesT.setBackground(new Color(255, 130, 14));
        pnlTotalesT.setLayout(xYLayout5);
        pnlTotalesT.setBounds(new Rectangle(495, 315, 230, 25));
        lblDsctoPorc.setText("(00.00%)");
        lblDsctoPorc.setFont(new Font("SansSerif", 1, 12));
        lblDsctoPorc.setForeground(Color.white);
        lblTotalesT.setText("Totales :");
        lblTotalesT.setFont(new Font("SansSerif", 1, 12));
        lblTotalesT.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotalesT.setForeground(Color.white);
        lblBrutoT.setText("Bruto :");
        lblBrutoT.setFont(new Font("SansSerif", 1, 12));
        lblBrutoT.setForeground(Color.white);
        lblBruto.setText("99,990.00");
        lblBruto.setFont(new Font("SansSerif", 1, 12));
        lblBruto.setForeground(Color.white);
        lblBruto.setHorizontalAlignment(SwingConstants.LEFT);
        lblDsctoT.setText("Ud. ha ahorrado "+ConstantesUtil.simboloSoles);
        lblDsctoT.setFont(new Font("SansSerif", 1, 12));
        lblDsctoT.setForeground(new Color(255, 114, 48));
        lblDscto.setText("999999.00  ");
        lblDscto.setFont(new Font("SansSerif", 1, 12));
        lblDscto.setForeground(new Color(255, 124, 37));
        lblDscto.setHorizontalAlignment(SwingConstants.LEFT);
        //lblDscto.setMaximumSize(new Dimension(100, 100));
        //lblDscto.setSize(new Dimension(500, 25));
        lblIGVT.setText("I.G.V. :");
        lblIGVT.setFont(new Font("SansSerif", 1, 12));
        lblIGVT.setForeground(Color.white);
        lblIGV.setText("9,990.00");
        lblIGV.setFont(new Font("SansSerif", 1, 12));
        lblIGV.setForeground(Color.white);
        lblIGV.setHorizontalAlignment(SwingConstants.LEFT);
        scrProductos.setFont(new Font("SansSerif", 0, 12));
        scrProductos.setBounds(new Rectangle(10, 105, 715, 210));
        scrProductos.setBackground(new Color(255, 130, 14));
        pnlProductos.setFont(new Font("SansSerif", 0, 12));
        pnlProductos.setLayout(xYLayout2);
        pnlProductos.setBackground(new Color(255, 130, 14));
        pnlProductos.setBounds(new Rectangle(10, 80, 715, 25));
        btnRelacionProductos.setText("Relacion de Productos :");
        btnRelacionProductos.setFont(new Font("SansSerif", 1, 11));
        btnRelacionProductos.setForeground(Color.white);
        btnRelacionProductos.setBackground(new Color(255, 130, 14));
        btnRelacionProductos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRelacionProductos.setHorizontalAlignment(SwingConstants.LEFT);
        btnRelacionProductos.setRequestFocusEnabled(false);
        btnRelacionProductos.setMnemonic('r');
        btnRelacionProductos.setBorderPainted(false);
        btnRelacionProductos.setContentAreaFilled(false);
        btnRelacionProductos.setDefaultCapable(false);
        btnRelacionProductos.setFocusPainted(false);
        btnRelacionProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnRelacionProductos_keyPressed(e);
            }
        });
        lblItemsT.setText("items");
        lblItemsT.setFont(new Font("SansSerif", 1, 11));
        lblItemsT.setForeground(Color.white);
        lblItems.setText("0");
        lblItems.setFont(new Font("SansSerif", 1, 11));
        lblItems.setForeground(Color.white);
        lblItems.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlAtencion.setFont(new Font("SansSerif", 0, 11));
        pnlAtencion.setLayout(xYLayout4);
        pnlAtencion.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlAtencion.setBackground(new Color(43, 141, 39));
        pnlAtencion.setBounds(new Rectangle(10, 45, 715, 30));
        lblUltimoPedido.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedido.setForeground(Color.white);
        lblUltimoPedidoT.setText("Ult. Pedido :");
        lblUltimoPedidoT.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedidoT.setForeground(Color.white);
        lblVendedor.setText("Vendedor :");
        lblVendedor.setFont(new Font("SansSerif", 0, 11));
        lblVendedor.setForeground(Color.white);
        lblNombreVendedor.setFont(new Font("SansSerif", 1, 11));
        lblNombreVendedor.setForeground(Color.white);
        lblTipoCambio.setFont(new Font("SansSerif", 1, 11));
        lblTipoCambio.setForeground(Color.white);
        lblFecha.setFont(new Font("SansSerif", 1, 11));
        lblFecha.setForeground(Color.white);
        lblTipoCambioT.setText("Tipo Cambio :");
        lblTipoCambioT.setFont(new Font("SansSerif", 0, 11));
        lblTipoCambioT.setForeground(Color.white);
        lblFechaT.setText("Fecha :");
        lblFechaT.setFont(new Font("SansSerif", 0, 11));
        lblFechaT.setForeground(Color.white);
        lblF3.setText("<HTML><CENTER>[F3] M�s<BR>Productos</CENTER></HTML>");
        lblF3.setBounds(new Rectangle(167, 400, 70, 35));
        lblEnter.setText("[ ENTER ]  Cambiar Cantidad");
        lblEnter.setBounds(new Rectangle(445, 445, 180, 20));
        lblF5.setText("<HTML><CENTER>[F5] Borrar<BR>Producto</CENTER></HTML>");
        lblF5.setBounds(new Rectangle(246, 400, 70, 35));
        lblF1.setText("<HTML><CENTER>[F1]<BR>Boleta</CENTER></HTML>");
        lblF1.setBounds(new Rectangle(10, 400, 70, 35));
        lblF2.setText("<HTML><CENTER>[F4]<BR>Factura</CENTER></HTML>");
        lblF2.setBounds(new Rectangle(89, 400, 70, 35));
        lblF8.setText("<HTML><CENTER>[F8] Dcto<BR>Receta</CENTER></HTML>");
        lblF8.setBounds(new Rectangle(481, 400, 70, 35));
        tblProductos.setFont(new Font("SansSerif", 0, 12));
        tblProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblProductos_keyPressed(e);
            }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(630, 445, 90, 20));
        pnlTitle1.setBounds(new Rectangle(10, 10, 715, 30));
        lblCliente_T.setText("Cliente:");
        lblCliente_T.setBounds(new Rectangle(15, 5, 55, 20));
        lblCliente_T.setFont(new Font("SansSerif", 1, 14));
        lblCliente.setBounds(new Rectangle(75, 5, 315, 20));
        lblCliente.setFont(new Font("SansSerif", 1, 14));
        lblF9.setBounds(new Rectangle(560, 400, 70, 35));
        lblF9.setText("<HTML><CENTER>[F9] Camp. Acumulada</CENTER></HTML>");
        lblF12.setBounds(new Rectangle(640, 400, 80, 35));
        lblF12.setText("<HTML><CENTER>[F12] Buscar X DNI</CENTER></HTML>");
        lblMensajeCupon.setText("[MENSAJE CUPON]");
        lblMensajeCupon.setFont(new Font("SansSerif", 1, 11));
        lblMensajeCupon.setBounds(new Rectangle(115, 380, 605, 15));
        lblMensajeCupon.setBackground(new Color(232, 236, 230));
        lblMensajeCupon.setForeground(new Color(230, 23, 39));
        pnlAtencion1.setFont(new Font("SansSerif", 0, 11));
        pnlAtencion1.setLayout(xYLayout7);
        pnlAtencion1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlAtencion1.setBackground(new Color(43, 141, 39));
        lblUltimoPedido1.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedido1.setForeground(Color.white);
        lblUltimoPedidoT1.setText("Ult. Pedido :");
        lblUltimoPedidoT1.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedidoT1.setForeground(Color.white);
        lblVendedor1.setText("Vendedor :");
        lblVendedor1.setFont(new Font("SansSerif", 0, 11));
        lblVendedor1.setForeground(Color.white);
        lblNombreVendedor1.setFont(new Font("SansSerif", 1, 11));
        lblNombreVendedor1.setForeground(Color.white);
        lblTipoCambio1.setFont(new Font("SansSerif", 1, 11));
        lblTipoCambio1.setForeground(Color.white);
        lblFecha1.setFont(new Font("SansSerif", 1, 11));
        lblFecha1.setForeground(Color.white);
        lblTipoCambioT1.setText("Tipo Cambio :");
        lblTipoCambioT1.setFont(new Font("SansSerif", 0, 11));
        lblTipoCambioT1.setForeground(Color.white);
        lblFechaT1.setText("Fecha :");
        lblFechaT1.setFont(new Font("SansSerif", 0, 11));
        lblFechaT1.setForeground(Color.white);
        txtDescProdOculto.setBounds(new Rectangle(65, 5, 250, 20));
        txtDescProdOculto.setFont(new Font("SansSerif", 1, 11));
        txtDescProdOculto.setForeground(new Color(32, 105, 29));
        txtDescProdOculto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtDescProdOculto_keyPressed(e);
                }

            public void keyReleased(KeyEvent e) {
                txtDescProdOculto_keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {
                txtDescProdOculto_keyTyped(e);
            }
        });
        txtDescProdOculto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtDescProdOculto_actionPerformed(e);
            }
        });
        txtDescProdOculto.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                txtDescProdOculto_focusLost(e);
            }
        });
        lblProdOculto_T.setText("Producto:");
        lblProdOculto_T.setBounds(new Rectangle(5, 5, 60, 20));
        lblF6.setText("<HTML><CENTER>[F6]<BR>Promociones</CENTER></HTML>");
        lblF6.setBounds(new Rectangle(324, 400, 70, 35));
        txtMensajesPedido.setCaretColor(new Color(7, 133, 7));
        txtMensajesPedido.setEditable(false);
        jScrollPane1.setBounds(new Rectangle(10, 475, 720, 55));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jLabel1.setText("Opciones");
        jLabel1.setBounds(new Rectangle(15, 375, 70, 20));
        jLabel1.setFont(new Font("SansSerif", 1, 11));
        lblCuponIngr.setText("[MENSAJE CUPON]");
        lblCuponIngr.setFont(new Font("SansSerif", 1, 12));
        lblCuponIngr.setBounds(new Rectangle(10, 445, 430, 25));
        lblCuponIngr.setToolTipText("null");
        pnlTotalesT1.setFont(new Font("SansSerif", 0, 12));
        pnlTotalesT1.setBackground(Color.white);
        pnlTotalesT1.setLayout(xYLayout8);
        pnlTotalesT1.setBounds(new Rectangle(10, 315, 185, 25));


        jPanelHeader1.setBounds(new Rectangle(320, 0, 395, 30));
        lblDNI_Anul.setBackground(Color.white);
        lblDNI_Anul.setFont(new Font("Dialog", 1, 14));
        lblDNI_Anul.setForeground(Color.red);
        lblDNI_Anul.setVisible(false);
        lblTopeAhoro.setForeground(Color.red);
        lblTopeAhoro.setFont(new Font("Dialog", 1, 12));
        lblF7.setBounds(new Rectangle(403, 400, 70, 35));
        lblF7.setText("<HTML><CENTER>[F7]<BR>Info Prod</CENTER></HTML>");
        //lblFormaPago.setOpaque(true);
        //lblFormaPago.setText("kokokokokoko okokoko okokok ooko okokoko okoko");
        lblFormaPago.setFont(new Font("SansSerif", 1, 12));
        lblFormaPago.setForeground(Color.red);
        lblFormaPago.setVisible(false);

        lblDNI_SIN_COMISION.setText("DNI Inv�lido. No aplica Prog. Atenci�n al Cliente");
        lblDNI_SIN_COMISION.setForeground(new Color(231, 0, 0));
        lblDNI_SIN_COMISION.setFont(new Font("Dialog", 3, 14));
        lblDNI_SIN_COMISION.setBackground(Color.white);
        lblDNI_SIN_COMISION.setOpaque(true);
        lblDNI_SIN_COMISION.setVisible(false);

        lblMedico.setForeground(Color.white);
        lblMedico.setFont(new Font("Dialog", 3, 11));
        lblMedico.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        lblMedico.setVisible(false);
        lblReceta.setBounds(new Rectangle(695, 5, 40, 40));
		
        // INICIO: JHAMRC 10072019
        lblICBPERT.setText("ICBPER :");
        lblICBPERT.setFont(new Font("SansSerif", 1, 12));
        lblICBPERT.setForeground(SystemColor.window);
        lblICBPER.setText("9,990.00");
        lblICBPER.setFont(new Font("SansSerif", 1, 12));
        lblICBPER.setForeground(SystemColor.window);
        // FIN: JHAMRC 10072019
        pnlAtencion1.add(lblUltimoPedido1, new XYConstraints(655, 10, 40, 15));
        pnlAtencion1.add(lblUltimoPedidoT1, new XYConstraints(585, 10, 70, 15));
        pnlAtencion1.add(lblVendedor1, new XYConstraints(245, 10, 70, 15));
        pnlAtencion1.add(lblNombreVendedor1, new XYConstraints(315, 10, 245, 15));
        pnlAtencion1.add(lblTipoCambio1, new XYConstraints(205, 10, 40, 15));
        pnlAtencion1.add(lblFecha1, new XYConstraints(60, 10, 70, 15));
        pnlAtencion1.add(lblTipoCambioT1, new XYConstraints(130, 10, 80, 15));
        pnlAtencion1.add(lblFechaT1, new XYConstraints(10, 10, 50, 15));
        pnlTotalesD.add(lblTotalD, new XYConstraints(615, 9, 80, 20));
        //pnlTotalesT.add(lblDsctoPorc, new XYConstraints(90, 5, 15, 15));
        //pnlTotalesT.add(lblDsctoPorc, new XYConstraints(90, 5, 15, 15));

        pnlTotalesD.add(lblTotalD, new XYConstraints(619, 9, 80, 20));
        pnlTotalesD.add(lblTotalS, new XYConstraints(464, 9, 95, 20));
        pnlTotalesD.add(lblTotalDT, new XYConstraints(579, 9, 35, 20));
        pnlTotalesD.add(lblTotalST, new XYConstraints(379, 9, 80, 20));
        pnlTotalesD.add(lblRedondeoT, new XYConstraints(4, 9, 70, 20));
        pnlTotalesD.add(lblRedondeo, new XYConstraints(79, 9, 65, 20));

        pnlTotalesD.add(lblPorPagarT, new XYConstraints(149, 9, 100, 20));
        pnlTotalesD.add(lblPorPagar, new XYConstraints(259, 9, 100, 20));
        pnlTotalesT.add(lblICBPER, new XYConstraints(165, 5, 50, 15)); // JHAMRC 10072019
        pnlTotalesT.add(lblICBPERT, new XYConstraints(110, 5, 50, 15)); // JHAMRC 10072019
        pnlTotalesT.add(lblDsctoPorc, new XYConstraints(545, 5, 15, 15));
        pnlTotalesT.add(lblTotalesT, new XYConstraints(495, 5, 15, 15));
        pnlTotalesT.add(lblBrutoT, new XYConstraints(510, 5, 15, 15));
        pnlTotalesT.add(lblBruto, new XYConstraints(525, 5, 15, 15));
        pnlTotalesT.add(lblIGVT, new XYConstraints(10, 5, 40, 15)); // JHAMRC 10072019
        pnlTotalesT.add(lblIGV, new XYConstraints(50, 5, 55, 15)); // JHAMRC 10072019
        scrProductos.getViewport();
        pnlProductos.add(lblMedico, new XYConstraints(230, 0, 485, 25));
        pnlProductos.add(pnlAtencion1, new XYConstraints(10, 45, 715, 35));
        pnlProductos.add(btnRelacionProductos, new XYConstraints(10, 5, 145, 15));
        pnlProductos.add(lblItemsT, new XYConstraints(185, 5, 40, 15));
        pnlProductos.add(lblItems, new XYConstraints(150, 5, 30, 15));
        pnlAtencion.add(lblDNI_SIN_COMISION, new XYConstraints(384, -1, 330, 30));
        pnlAtencion.add(lblFormaPago, new XYConstraints(454, -1, 260, 30));
        pnlAtencion.add(lblUltimoPedido, new XYConstraints(655, 5, 40, 15));
        pnlAtencion.add(lblUltimoPedidoT, new XYConstraints(584, 4, 70, 15));
        pnlAtencion.add(lblVendedor, new XYConstraints(244, 4, 60, 15));
        pnlAtencion.add(lblNombreVendedor, new XYConstraints(304, 4, 235, 15));
        pnlAtencion.add(lblTipoCambio, new XYConstraints(205, 5, 40, 15));
        pnlAtencion.add(lblFecha, new XYConstraints(60, 5, 70, 15));
        pnlAtencion.add(lblTipoCambioT, new XYConstraints(130, 5, 80, 15));
        pnlAtencion.add(lblFechaT, new XYConstraints(10, 5, 50, 15));
        jPanelHeader1.add(lblCliente_T, null);
        jPanelHeader1.add(lblCliente, null);
        pnlTitle1.add(jPanelHeader1, null);
        pnlTitle1.add(lblProdOculto_T, null);

        pnlTitle1.add(txtDescProdOculto, null);
        pnlTotalesT1.add(lblDNI_Anul, new XYConstraints(0, 0, 290, 25));
        pnlTotalesT1.add(lblDscto, new XYConstraints(120, 0, 85, 25));
        pnlTotalesT1.add(lblDsctoT, new XYConstraints(5, 5, 115, 15));
        pnlTotalesT1.add(lblTopeAhoro, new XYConstraints(195, 0, 290, 25));
        jContentPane.add(lblReceta, null);
        jContentPane.add(lblF7, null);
        jContentPane.add(pnlTotalesT1, null);
        jContentPane.add(jLabel1, null);
        jScrollPane1.getViewport().add(txtMensajesPedido, null);
        jContentPane.add(jScrollPane1, null);
        jContentPane.add(lblF6, null);
        jContentPane.add(lblMensajeCupon, null); //LTAVARA 28.08.2014
        jContentPane.add(lblF12, null);
        jContentPane.add(lblF9, null);
        jContentPane.add(pnlTitle1, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(pnlTotalesD, null);
        jContentPane.add(pnlTotalesT, null);
        scrProductos.getViewport().add(tblProductos, null);
        jContentPane.add(scrProductos, null);
        jContentPane.add(pnlProductos, null);
        jContentPane.add(pnlAtencion, null);
        jContentPane.add(lblF3, null);
        jContentPane.add(lblEnter, null);
        jContentPane.add(lblF5, null);
        jContentPane.add(lblF1, null);
        jContentPane.add(lblF2, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(lblCuponIngr, null);
        jContentPane.add(lblCreditoT, null);
        jContentPane.add(lblCredito, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        //this.getContentPane().add(jContentPane, null);
        FarmaUtility.centrarVentana(this);
    }
    // **************************************************************************
    // M�todo "jbInitBTLMF()"
    // **************************************************************************

    /**
     *Implementa la Ventana con todos sus Objetos
     */
    private void jbInitBTLMF() {

        log.debug("jbInitBTLMF");
        lblF2.setVisible(false);
        lblF6.setVisible(false);
        lblF12.setVisible(false);
        lblF8.setVisible(false);
        lblF9.setVisible(false);
        
        //this.setSize(new Dimension(950, 583));
        this.getContentPane().setLayout(borderLayout1);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("Resumen de Pedido" + " /  IP : " + FarmaVariables.vIpPc);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //this.setBounds(new Rectangle(10, 10, 765, 583));
        this.setBackground(Color.white);
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
        jContentPane.setSize(new Dimension(742, 423));
        pnlTotalesD.setFont(new Font("SansSerif", 0, 12));
        pnlTotalesD.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlTotalesD.setLayout(xYLayout6);
        //pnlTotalesD.setBounds(new Rectangle(10, 390, 740, 35));
        pnlTotalesD.setBackground(new Color(43, 141, 39));
        lblTotalD.setText("9,990.00");
        lblTotalD.setFont(new Font("SansSerif", 1, 14));
        lblTotalD.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalD.setForeground(Color.white);
        lblTotalS.setText("99,990.00");
        lblTotalS.setFont(new Font("SansSerif", 1, 14));
        lblTotalS.setForeground(Color.white);
        lblTotalS.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalDT.setText("US$");
        lblTotalDT.setFont(new Font("SansSerif", 1, 14));
        lblTotalDT.setForeground(Color.white);
        lblTotalST.setText("TOTAL : "+ConstantesUtil.simboloSoles);
        lblTotalST.setFont(new Font("SansSerif", 1, 14));
        lblTotalST.setForeground(Color.white);
        lblRedondeoT.setText("Red. "+ConstantesUtil.simboloSoles);
        lblRedondeoT.setFont(new Font("SansSerif", 1, 14));
        lblRedondeoT.setForeground(Color.white);
        lblRedondeo.setText("-0.00");
        lblRedondeo.setFont(new Font("SansSerif", 1, 14));
        lblRedondeo.setForeground(Color.white);
        lblCreditoT.setText("");

        lblCreditoT.setVisible(false);
        lblCreditoT.setFont(new Font("SansSerif", 1, 14));
        lblCreditoT.setForeground(new Color(43, 141, 39));
        //lblCreditoT.setBounds(new Rectangle(195, 360, 160, 20));
        lblCredito.setText("");
        lblCredito.setVisible(false);
        lblCredito.setFont(new Font("SansSerif", 1, 14));
        lblCredito.setForeground(new Color(43, 141, 39));
        //lblCredito.setBounds(new Rectangle(360, 360, 75, 20));
        lblPorPagarT.setText("");
        lblPorPagarT.setVisible(false);
        lblPorPagarT.setFont(new Font("SansSerif", 1, 14));
        lblPorPagarT.setForeground(Color.white);
        lblPorPagar.setText("");
        lblPorPagar.setVisible(false);
        lblPorPagar.setFont(new Font("SansSerif", 1, 14));
        lblPorPagar.setForeground(Color.white);
        lblPorPagar.setBounds(new Rectangle(525, 110, 715, 210));
        pnlTotalesT.setFont(new Font("SansSerif", 0, 12));
        pnlTotalesT.setBackground(new Color(255, 130, 14));
        pnlTotalesT.setLayout(xYLayout5);
        //pnlTotalesT.setBounds(new Rectangle(520, 360, 230, 25));
        lblDsctoPorc.setText("(00.00%)");
        lblDsctoPorc.setFont(new Font("SansSerif", 1, 12));
        lblDsctoPorc.setForeground(Color.white);
        lblTotalesT.setText("Totales :");
        lblTotalesT.setFont(new Font("SansSerif", 1, 12));
        lblTotalesT.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotalesT.setForeground(Color.white);
        lblBrutoT.setText("Bruto :");
        lblBrutoT.setFont(new Font("SansSerif", 1, 12));
        lblBrutoT.setForeground(Color.white);
        lblBruto.setText("99,990.00");
        lblBruto.setFont(new Font("SansSerif", 1, 12));
        lblBruto.setForeground(Color.white);
        lblBruto.setHorizontalAlignment(SwingConstants.LEFT);
        lblDsctoT.setText("Ud. ha ahorrado "+ConstantesUtil.simboloSoles);
        lblDsctoT.setFont(new Font("SansSerif", 1, 12));
        lblDsctoT.setForeground(new Color(255, 114, 48));
        lblDsctoT.setBounds(new Rectangle(10, 370, 115, 15));
        lblDscto.setText("999999.00  ");
        lblDscto.setFont(new Font("SansSerif", 1, 12));
        lblDscto.setForeground(new Color(255, 124, 37));
        lblDscto.setHorizontalAlignment(SwingConstants.LEFT);
        //lblDscto.setMaximumSize(new Dimension(100, 100));
        //lblDscto.setSize(new Dimension(500, 25));
        lblDscto.setBounds(new Rectangle(125, 365, 85, 25));
        lblIGVT.setText("I.G.V. :");
        lblIGVT.setFont(new Font("SansSerif", 1, 12));
        lblIGVT.setForeground(Color.white);
        lblIGV.setText("9,990.00");
        lblIGV.setFont(new Font("SansSerif", 1, 12));
        lblIGV.setForeground(Color.white);
        lblIGV.setHorizontalAlignment(SwingConstants.LEFT);
        scrProductos.setFont(new Font("SansSerif", 0, 12));
        scrProductos.setBounds(new Rectangle(10, 145, 715, 170));
        scrProductos.setBackground(new Color(255, 130, 14));
        pnlProductos.setFont(new Font("SansSerif", 0, 12));
        pnlProductos.setLayout(xYLayout2);
        pnlProductos.setBackground(new Color(255, 130, 14));
        pnlProductos.setBounds(new Rectangle(10, 115, 715, 25));
        btnRelacionProductos.setText("Relacion de Productos :");
        btnRelacionProductos.setFont(new Font("SansSerif", 1, 11));
        btnRelacionProductos.setForeground(Color.white);
        btnRelacionProductos.setBackground(new Color(255, 130, 14));
        btnRelacionProductos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRelacionProductos.setHorizontalAlignment(SwingConstants.LEFT);
        btnRelacionProductos.setRequestFocusEnabled(false);
        btnRelacionProductos.setMnemonic('r');
        btnRelacionProductos.setBorderPainted(false);
        btnRelacionProductos.setContentAreaFilled(false);
        btnRelacionProductos.setDefaultCapable(false);
        btnRelacionProductos.setFocusPainted(false);
        btnRelacionProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnRelacionProductos_keyPressed(e);
            }
        });
        lblItemsT.setText("items");
        lblItemsT.setFont(new Font("SansSerif", 1, 11));
        lblItemsT.setForeground(Color.white);
        lblItems.setText("0");
        lblItems.setFont(new Font("SansSerif", 1, 11));
        lblItems.setForeground(Color.white);
        lblItems.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlAtencion.setFont(new Font("SansSerif", 0, 11));
        pnlAtencion.setLayout(xYLayout4);
        pnlAtencion.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlAtencion.setBackground(new Color(43, 141, 39));
        pnlAtencion.setBounds(new Rectangle(10, 80, 715, 30));
        lblUltimoPedido.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedido.setForeground(Color.white);
        lblUltimoPedidoT.setText("Ult. Pedido :");
        lblUltimoPedidoT.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedidoT.setForeground(Color.white);
        lblVendedor.setText("Vendedor :");
        lblVendedor.setFont(new Font("SansSerif", 0, 11));
        lblVendedor.setForeground(Color.white);
        lblNombreVendedor.setFont(new Font("SansSerif", 1, 11));
        lblNombreVendedor.setForeground(Color.white);
        lblTipoCambio.setFont(new Font("SansSerif", 1, 11));
        lblTipoCambio.setForeground(Color.white);
        lblFecha.setFont(new Font("SansSerif", 1, 11));
        lblFecha.setForeground(Color.white);
        lblTipoCambioT.setText("Tipo Cambio :");
        lblTipoCambioT.setFont(new Font("SansSerif", 0, 11));
        lblTipoCambioT.setForeground(Color.white);
        lblFechaT.setText("Fecha :");
        lblFechaT.setFont(new Font("SansSerif", 0, 11));
        lblFechaT.setForeground(Color.white);
        //lblF3.setText("[ F3 ]  Agregar Prod");
        //lblF3.setBounds(new Rectangle(165, 440, 125, 20));
        //lblEnter.setText("[ ENTER ]  Cambiar Cantidad");
        //lblEnter.setBounds(new Rectangle(460, 440, 180, 20));
        //lblF5.setText("[ F5 ]  Borrar Producto");
        //lblF5.setBounds(new Rectangle(310, 440, 135, 20));
        if(!pIngresoComprobanteManual){
            lblF1.setText("<HTML><CENTER>[F1] Cobrar<BR>Pedido</CENTER></HTML>");
        }
        //lblF1.setBounds(new Rectangle(10, 440, 140, 20));
        //lblF8.setText("[ F8 ] Dcto por Receta");
        //lblF8.setBounds(new Rectangle(240, 465, 140, 20));
    
        //INI JMONZALVE 24042019
        try {
            if (DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)) {
                lblF1.setText("<HTML><CENTER>[F1] Dscto<BR>Colaborador</CENTER></HTML>");
                lblF1.setBounds(new Rectangle(10, 400, 80, 35));
                if(!DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                    lblF12.setVisible(false);
                }else{
                    lblF12.setVisible(true);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        //FIN JMONZALVE 24042019
        tblProductos.removeAll();

        tblProductos.setFont(new Font("SansSerif", 0, 12));
        tblProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblProductos_keyPressed(e);
            }
        });
        //lblEsc.setText("[ ESC ] Cerrar");
        //lblEsc.setBounds(new Rectangle(660, 440, 90, 20));
        pnlTitle1.setBounds(new Rectangle(10, 5, 715, 70));

        lblCliente_T.setText("Cliente:");
        lblCliente_T.setBounds(new Rectangle(10, 5, 55, 20));
        lblCliente_T.setFont(new Font("SansSerif", 1, 14));


        //        if(VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0)
        //        {
        lblLCredito_T.setText("");
        lblLCredito_T.setBounds(new Rectangle(10, 25, 380, 20));
        lblLCredito_T.setFont(new Font("SansSerif", 1, 14));

        lblBeneficiario_T.setText("");
        lblBeneficiario_T.setBounds(new Rectangle(10, 45, 380, 20));
        lblBeneficiario_T.setFont(new Font("SansSerif", 1, 14));
        //}

        lblCliente.setBounds(new Rectangle(75, 5, 315, 20));
        lblCliente.setFont(new Font("SansSerif", 1, 14));
        //lblF9.setBounds(new Rectangle(400, 465, 150, 20));
        lblMensajeCupon.setFont(new Font("SansSerif", 1, 11));
        //lblMensajeCupon.setBounds(new Rectangle(115, 380, 605, 15));
        lblMensajeCupon.setBackground(new Color(232, 236, 230));
        lblMensajeCupon.setForeground(new Color(230, 23, 39));
        pnlAtencion1.setFont(new Font("SansSerif", 0, 11));
        pnlAtencion1.setLayout(xYLayout7);
        pnlAtencion1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlAtencion1.setBackground(new Color(43, 141, 39));
        lblUltimoPedido1.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedido1.setForeground(Color.white);
        lblUltimoPedidoT1.setText("Ult. Pedido :");
        lblUltimoPedidoT1.setFont(new Font("SansSerif", 0, 11));
        lblUltimoPedidoT1.setForeground(Color.white);
        lblVendedor1.setText("Vendedor :");
        lblVendedor1.setFont(new Font("SansSerif", 0, 11));
        lblVendedor1.setForeground(Color.white);
        lblNombreVendedor1.setFont(new Font("SansSerif", 1, 11));
        lblNombreVendedor1.setForeground(Color.white);
        lblTipoCambio1.setFont(new Font("SansSerif", 1, 11));
        lblTipoCambio1.setForeground(Color.white);
        lblFecha1.setFont(new Font("SansSerif", 1, 11));
        lblFecha1.setForeground(Color.white);
        lblTipoCambioT1.setText("Tipo Cambio :");
        lblTipoCambioT1.setFont(new Font("SansSerif", 0, 11));
        lblTipoCambioT1.setForeground(Color.white);
        lblFechaT1.setText("Fecha :");
        lblFechaT1.setFont(new Font("SansSerif", 0, 11));
        lblFechaT1.setForeground(Color.white);

        txtDescProdOculto.setBounds(new Rectangle(65, 25, 250, 20));
        txtDescProdOculto.setFont(new Font("SansSerif", 1, 11));
        txtDescProdOculto.setForeground(new Color(32, 105, 29));
        //        txtDescProdOculto.addKeyListener(new KeyAdapter() {
        //                    public void keyPressed(KeyEvent e) {
        //                        txtDescProdOculto_keyPressed(e);
        //                    }
        //
        //                    public void keyReleased(KeyEvent e) {
        //                        txtDescProdOculto_keyReleased(e);
        //                    }
        //
        //                    public void keyTyped(KeyEvent e) {
        //                        txtDescProdOculto_keyTyped(e);
        //                    }
        //                });
        lblProdOculto_T.setText("Producto:");
        lblProdOculto_T.setBounds(new Rectangle(5, 25, 60, 20));
        txtMensajesPedido.setCaretColor(new Color(7, 133, 7));
        txtMensajesPedido.setEditable(false);
        txtMensajesPedido.setBounds(new Rectangle(5, 515, 745, 40));
        //jScrollPane1.setBounds(new Rectangle(5, 490, 745, 65));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jLabel1.setText("Opciones");
        //jLabel1.setBounds(new Rectangle(15, 420, 70, 20));
        jLabel1.setFont(new Font("SansSerif", 1, 11));
        lblCuponIngr.setText("-");
        lblCuponIngr.setFont(new Font("SansSerif", 1, 12));
        //lblCuponIngr.setBounds(new Rectangle(165, 475, 560, 25));
        lblCuponIngr.setToolTipText("null");
        pnlTotalesT1.setFont(new Font("SansSerif", 0, 12));
        pnlTotalesT1.setBackground(Color.white);
        pnlTotalesT1.setLayout(xYLayout8);


        //pnlTotalesT1.setBounds(new Rectangle(35, 155, 715, 210));
        jPanelHeader1.setBounds(new Rectangle(320, 0, 395, 70));
        lblDNI_Anul.setBackground(Color.white);
        lblDNI_Anul.setFont(new Font("Dialog", 1, 14));
        lblDNI_Anul.setForeground(Color.red);
        lblDNI_Anul.setVisible(false);
        lblTopeAhoro.setForeground(Color.red);
        lblTopeAhoro.setFont(new Font("Dialog", 1, 12));
        //lblF7.setBounds(new Rectangle(120, 465, 95, 20));
        //lblF7.setText("[ F7 ]  Info Prod");
        //lblFormaPago.setOpaque(true);
        lblFormaPago.setText("kokokokokoko okokoko okokok ooko okokoko okoko");
        lblFormaPago.setFont(new Font("SansSerif", 1, 12));
        lblFormaPago.setForeground(Color.red);
        lblFormaPago.setVisible(false);
        pnlAtencion1.add(lblUltimoPedido1, new XYConstraints(655, 10, 40, 15));
        pnlAtencion1.add(lblUltimoPedidoT1, new XYConstraints(585, 10, 70, 15));
        pnlAtencion1.add(lblVendedor1, new XYConstraints(245, 10, 70, 15));
        pnlAtencion1.add(lblNombreVendedor1, new XYConstraints(315, 10, 245, 15));
        pnlAtencion1.add(lblTipoCambio1, new XYConstraints(205, 10, 40, 15));
        //pnlTotalesT.add(lblDsctoPorc, new XYConstraints(90, 5, 15, 15));
        //pnlTotalesT.add(lblDsctoPorc, new XYConstraints(90, 5, 15, 15));

        pnlAtencion1.add(lblFecha1, new XYConstraints(60, 10, 70, 15));
        pnlAtencion1.add(lblTipoCambioT1, new XYConstraints(130, 10, 80, 15));
        pnlAtencion1.add(lblFechaT1, new XYConstraints(10, 10, 50, 15));
        pnlTotalesD.add(lblTotalD, new XYConstraints(615, 9, 80, 20));
        pnlTotalesD.add(lblTotalD, new XYConstraints(619, 9, 80, 20));
        pnlTotalesD.add(lblTotalS, new XYConstraints(464, 9, 95, 20));
        pnlTotalesD.add(lblTotalDT, new XYConstraints(579, 9, 35, 20));
        pnlTotalesD.add(lblTotalST, new XYConstraints(379, 9, 80, 20));
        pnlTotalesD.add(lblRedondeoT, new XYConstraints(4, 9, 70, 20));

        pnlTotalesD.add(lblRedondeo, new XYConstraints(79, 9, 65, 20));
        pnlTotalesD.add(lblPorPagarT, new XYConstraints(149, 9, 100, 20));
        pnlTotalesD.add(lblPorPagar, new XYConstraints(259, 9, 100, 20));
        pnlTotalesT.add(lblDsctoPorc, new XYConstraints(545, 5, 15, 15));
        pnlTotalesT.add(lblTotalesT, new XYConstraints(495, 5, 15, 15));
        pnlTotalesT.add(lblBrutoT, new XYConstraints(510, 5, 15, 15));
        pnlTotalesT.add(lblBruto, new XYConstraints(525, 5, 15, 15));
        pnlTotalesT.add(lblIGVT, new XYConstraints(65, 5, 45, 15));
        pnlTotalesT.add(lblIGV, new XYConstraints(120, 5, 95, 15));
        scrProductos.getViewport();
        pnlProductos.add(pnlAtencion1, new XYConstraints(10, 45, 715, 35));
        pnlProductos.add(btnRelacionProductos, new XYConstraints(10, 5, 145, 15));
        pnlProductos.add(lblItemsT, new XYConstraints(185, 5, 40, 15));
        pnlProductos.add(lblItems, new XYConstraints(150, 5, 30, 15));
        pnlAtencion.add(lblFormaPago, new XYConstraints(454, -1, 260, 30));
        pnlAtencion.add(lblUltimoPedido, new XYConstraints(655, 5, 40, 15));
        pnlAtencion.add(lblUltimoPedidoT, new XYConstraints(585, 5, 70, 15));
        pnlAtencion.add(lblVendedor, new XYConstraints(244, 4, 60, 15));
        pnlAtencion.add(lblNombreVendedor, new XYConstraints(304, 4, 235, 15));
        pnlAtencion.add(lblTipoCambio, new XYConstraints(205, 5, 40, 15));

        pnlAtencion.add(lblFecha, new XYConstraints(60, 5, 70, 15));
        pnlAtencion.add(lblTipoCambioT, new XYConstraints(130, 5, 80, 15));
        pnlAtencion.add(lblFechaT, new XYConstraints(10, 5, 50, 15));

        jPanelHeader1.add(lblCliente_T, null);
        jPanelHeader1.add(lblLCredito_T, null);
        jPanelHeader1.add(lblCliente, null);
        jPanelHeader1.add(lblBeneficiario_T, null);
        pnlTitle1.add(jPanelHeader1, null);
        pnlTitle1.add(lblProdOculto_T, null);
        pnlTitle1.add(txtDescProdOculto, BorderLayout.CENTER);
        pnlTotalesT1.add(lblDNI_Anul, new XYConstraints(0, 0, 290, 25));
        pnlTotalesT1.add(lblTopeAhoro, new XYConstraints(195, 0, 290, 25));
        jContentPane.add(lblF7, null);
        jContentPane.add(jLabel1, null);
        jContentPane.add(jScrollPane1, null);
        jContentPane.add(lblMensajeCupon, null);
        jContentPane.add(lblF9, null);
        jContentPane.add(pnlTitle1, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(pnlTotalesD, null);
        jContentPane.add(pnlTotalesT, null);
        scrProductos.getViewport().remove(tblProductos);
        scrProductos.getViewport().add(tblProductos, null);
        jContentPane.add(scrProductos, null);
        jContentPane.add(pnlProductos, null);
        jContentPane.add(pnlAtencion, null);
        jContentPane.add(lblF3, null);
        jContentPane.add(lblEnter, null);
        jContentPane.add(lblF5, null);
        jContentPane.add(lblF1, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(lblCuponIngr, null);
        jContentPane.add(lblCreditoT, null);
        jContentPane.add(lblCredito, null);
        jContentPane.add(lblDsctoT, null);
        jContentPane.add(lblDscto, null);
        jContentPane.add(txtMensajesPedido, null);
        jContentPane.add(pnlTotalesT1, null);

        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        this.setLocationRelativeTo(myParentFrame);
        this.setVisible(true);

        //this.getContentPane().add(jContentPane, null);
    }


    // **************************************************************************
    // M�todo "initialize()"
    // **************************************************************************

    private void initialize() {
        //jcallo 02.10.2008
        lblDscto.setVisible(false);
        lblDsctoT.setVisible(false);
        //fin jcallo 02.10.2008
        initTableResumenPedido();
        limpiaValoresPedido();
        //dubilluz - 28.03.2012 inicio
        VariablesConvenioBTLMF.limpiaVariablesBTLMF();
        //dubilluz - 28.03.2012 fin
        // Inicio Adicion Delivery 28/04/2006 Paulo
        limpiaVariables();
        // Fin Adicion Delivery 28/04/2006 Paulo
        FarmaVariables.vAceptar = false;

        //jquispe 25.07.2011 se agrego la funcionalidad de listar las campa�as sin fidelizar
        UtilityFidelizacion.operaCampa�asFidelizacion(" ", VariablesConvenioBTLMF.vCodConvenio);

        //ERIOS 18.04.2016 Determina el tipo de comision
        VariablesReporte.indVerTipoComision = (new FacadeReporte()).getVerTipoComision();
        //ERIOS 16.09.2016 Se recupera indicador de ver rentables
        VariablesReporte.indVerRentables = (new FacadeReporte()).getVerRentables();
    }

    // **************************************************************************
    // M�todos de inicializaci�n
    // **************************************************************************

    private void initTableResumenPedido() {
        tableModelResumenPedido = new FarmaTableModel(ConstantsVentas.columnsListaResumenPedido, ConstantsVentas.defaultValuesListaResumenPedido, 0);
        FarmaUtility.initSimpleList(tblProductos, tableModelResumenPedido, ConstantsVentas.columnsListaResumenPedido);
        
        tableModelResumenPedidoAux = new FarmaTableModel(ConstantsVentas.columnsListaResumenPedido, ConstantsVentas.defaultValuesListaResumenPedido, 0);
        
       
        
    }

    private void cargaLogin() {
        // DUBILLUZ 04.02.2013
        FarmaConnection.closeConnection();
        DlgProcesar.setVersion();

        VariablesVentas.vListaProdFaltaCero = new ArrayList();
        VariablesVentas.vListaProdFaltaCero.clear();

        //limpiando variables de fidelizacion
        UtilityFidelizacion.setVariables();

        //JCORTEZ 04.08.09 Se limpiar cupones.
        VariablesVentas.vArrayListCuponesCliente.clear();
        VariablesVentas.dniListCupon = "";

        /*DlgLogin dlgLogin =
            new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
        dlgLogin.setRolUsuario(FarmaConstants.ROL_VENDEDOR);
        dlgLogin.setVisible(true);*/

        FarmaVariables.vAceptar = true;

        if (FarmaVariables.vAceptar) {
            log.info("******* JCORTEZ *********");
            if (UtilityCaja.existeIpImpresora(this, null)) {
                if (FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL) &&
                    !UtilityCaja.existeCajaUsuarioImpresora(this, null)) {
                    //linea agrega p�ra corregir el error al validar los roles de los usuarios
                    //FarmaVariables.dlgLogin = dlgLogin;
                    VariablesCaja.vVerificaCajero = false;
                    log.debug("");
                    cerrarVentana(false);
                } else {
                    //FarmaVariables.dlgLogin = dlgLogin;

                    log.info("******* 2 *********");
                    log.info("Usuario: " + FarmaVariables.vIdUsu);
                    muestraMensajeUsuario();
                    FarmaVariables.vAceptar = false;

                    //agregarProducto();
                    agregarProducto(null); //ASOSA - 10/10/2014 - PANHD
                }
            } else {
                //no se genera venta sin impresora asignada (Boleta/ Ticket)
                //FarmaVariables.dlgLogin = dlgLogin;
                VariablesCaja.vVerificaCajero = false;
                cerrarVentana(false);
            }
        } else
            cerrarVentana(false);
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        obtieneInfoPedido();
        try{
            vInd_Menu=UtilMenu.Recupera_IndicadorMenu();
        }catch(Exception f){
            f.printStackTrace();
            log.error(" ==> Error al recuperar indicador de listar menu:\n"+f.getMessage());
        }
        
        //LLEIVA 03-Ene-2014 Si el tipo de cambio es cero, no permitir continuar
        if (FarmaVariables.vTipCambio == 0) {
            FarmaUtility.showMessage(this,
                                     "ATENCI�N: No se pudo obtener el tipo de cambio actual\nNo se puede continuar con la acci�n",
                                     null);
            cerrarVentana(false);
        } else {
            // DUBILLUZ 13.10.2014
            if (pIngresoComprobanteManual) {
                DlgIngresoCompManual dlgIngManual = new DlgIngresoCompManual(myParentFrame, "", true);
                dlgIngManual.setVisible(true);
                if (!FarmaVariables.vAceptar) {
                    cerrarVentana(true);
                    return;
                } else {
                    log.info("Ingreso Comprobante Manual");
                    cambiaFunciones();
                }
            }
            // DUBILLUZ 13.10.2014

            //JCHAVEZ 08102009.sn
            /*try {
                lblF7.setVisible(DBVentas.getIndVerCupones());


            } catch (SQLException ex) {
                lblF7.setVisible(false);
                log.error("", ex);
            }*/
            //JCHAVEZ 08102009.en

            // Inicio Adicion Delivery 28/04/2006 Paulo
            //if(FarmaVariables.vAceptar)
            //{
            // String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
            // lblCliente.setText(nombreCliente);
            // FarmaVariables.vAceptar = false ;
            // }
            // Fin Adicion Delivery 28/04/2006 Paulo
            txtDescProdOculto.grabFocus();

            lblFecha.setText(fechaPedido);
            lblTipoCambio.setText(FarmaUtility.formatNumber(FarmaVariables.vTipCambio));
            VariablesCaja.vVerificaCajero = true;
            cargaLogin();

            //verificaRolUsuario();
            //agregarProducto();
            lblNombreVendedor.setText(FarmaVariables.vNomUsu.trim() + " " + FarmaVariables.vPatUsu.trim() + " " +
                                      FarmaVariables.vMatUsu.trim());
            // Inicio Adicion Delivery 28/04/2006 Paulo
            //String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
            //lblCliente.setText(nombreCliente);
            // Fin Adicion Delivery 28/04/2006 Paulo
            colocaUltimoPedidoDiarioVendedor();
            //FarmaUtility.moveFocus(tblProductos);
            FarmaUtility.moveFocus(txtDescProdOculto);


            //JCORTEZ 17.04.08
            lblTotalesT.setVisible(false);
            lblBrutoT.setVisible(false);
            lblBruto.setVisible(false);
            //lblDsctoT.setVisible(false);
            //lblDscto.setVisible(false);
            lblDsctoPorc.setVisible(false);

            //JCORTEZ 23.07.2008
            lblCuponIngr.setText(VariablesVentas.vMensCuponIngre);
            if(vInd_Menu.equalsIgnoreCase("S")){
                FrmEconoMenuFar.obtieneIndRegistroVentaRestringida();
            }else{
                FrmEconoFar.obtieneIndRegistroVentaRestringida();
            }
        }
    }

    private void tblProductos_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void btnRelacionProductos_keyPressed(KeyEvent e) {
        //FarmaUtility.moveFocus(tblProductos);
        FarmaUtility.moveFocus(txtDescProdOculto);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    // ************************************************************************************************************************************************
    // Marco Fajardo: Cambio realizado el 21/04/09 - Evitar le ejecuci�n de 2 teclas a la vez al momento de comprometer stock
    // ************************************************************************************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        try {
            if (!vEjecutaAccionTeclaResumen) {
                //log.debug("e.getKeyCode() presionado:"+e.getKeyCode());
                //log.debug("e.getKeyChar() presionado:"+e.getKeyChar());
                vEjecutaAccionTeclaResumen = true;
                log.debug(" try: " + vEjecutaAccionTeclaResumen);
                if (Character.isLetter(e.getKeyChar())) {
                    //LLEIVA 12/Julio/2013 - se a�ade validaciones para producto virtual
                    if (!VariablesVentas.vProductoVirtual) {
                        //log.debug("Presiono una letra");
                        //vEjecutaAccionTeclaResumen = false;
                        if (VariablesVentas.vKeyPress == null) {
                            //VariablesVentas.vLetraBusqueda = e.getKeyChar() + "";;
                            //log.debug("VariablesVentas.vLetraBusqueda  " + VariablesVentas.vLetraBusqueda);
                            VariablesVentas.vKeyPress = e;
                            //agregarProducto(null);
                            
                            if (!existsProdRimac()) { //ASOSA - 19/01/2015 - RIMAC   //ASOSA - 12/01/2015 - RIMAC
                            agregarProducto(null); //ASOSA - 10/10/2014 - PANHD
                            } else {
                                FarmaUtility.showMessage(this, "Ya se selecciono un producto rimac", txtDescProdOculto);
                            }
                        }
                    } else {
                        FarmaUtility.showMessage(this, "Ya se selecciono un producto virtual", txtDescProdOculto);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    VariablesVentas.vCostoICBPER = "";
                    VariablesVentas.vTotalICBPER = "";
                    if(vPermiteAccion()){
                        e.consume();
                        //vEjecutaAccionTeclaResumen = false;
                        evaluaIngresoCantidad();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_F8 && !pIngresoComprobanteManual) {
                    //Se ha comentado este metodo solo de manera temporal
                    //debe de colocarte una funcion para habilitar esto en tab gral
                    //dubilluz 16.09.2009
                    /*if (lblF7.isVisible()) {
                        //JCORTEZ 04.08.09
                        log.info("VariablesVentas.dniListCupon  " +
                                 VariablesVentas.dniListCupon);
                        if (VariablesVentas.dniListCupon.trim().length() < 1)
                            FarmaUtility.showMessage(this,
                                                     "No es pedido fidelizado.",
                                                     txtDescProdOculto);
                        else
                            cargarCupones();
                    }*/
                    //SE QUITO LO ANTERIOR para que pueda ingresar el MEDICO
                    //dubilluz 07.12.2011

                    //Agregado por FRAMIREZ 04.05.2012 descativa para el convenio BTLMF
                    if (!UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null)) {
                        ingresaMedicoFidelizado();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_F7) {
                    //vEjecutaAccionTeclaResumen = false;
                    if(vPermiteAccion())
                        muestraDetalleProducto();
                } else if (e.getKeyCode() == KeyEvent.VK_F5) {
                    
                        //vEjecutaAccionTeclaResumen = false;
                        eliminaItemResumenPedido();
                        FarmaUtility.moveFocus(txtDescProdOculto);
                        //mfajardo 29/04/09 validar ingreso de productos virtuales
                        //INI ASOSA - 04/06/2015
                        if (tblProductos.getRowCount() == 0) {
                            VariablesVentas.vProductoVirtual = false;
                        }
                        //FIN ASOSA - 04/06/2015
                        VariablesRecetario.strCodigoRecetario = "";

                        //INI ASOSA - 13/01/2015 - RIMAC
                        if (!VariablesVentas.vDniRimac.equals("")) {
                            VariablesVentas.vDniRimac = "";
                            VariablesVentas.vCantMesRimac = 0;
                        }
                        //FIN ASOSA - 13/01/2015 - RIMAC
                   
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    //vEjecutaAccionTeclaResumen = false;

                    if (!VariablesVentas.vProductoVirtual) {
                        //agregarProducto();
                        if (!existsProdRimac()) { //ASOSA - 19/01/2015 - RIMAC   //ASOSA - 12/01/2015 - RIMAC
                            agregarProducto(null); //ASOSA - 10/10/2014 - PANHD
                        } else {
                            FarmaUtility.showMessage(this, "Ya se selecciono un producto rimac", txtDescProdOculto);
                        }
                    } else {
                        //log.debug("error de producto virtual marco");
                        FarmaUtility.showMessage(this, "Ya se selecciono un producto virtual", txtDescProdOculto);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_F4 && !pIngresoComprobanteManual) {
                    //vEjecutaAccionTeclaResumen = false;
                    //validaConvenio(e, VariablesConvenio.vPorcCoPago);
                    //JMIRANDA 23.06.2010
                    //NUEVO VALIDA CONVENIO
                    /*if(cargaLogin_verifica())
                    {*/
                    lblNombreVendedor.setText(FarmaVariables.vNomUsu.trim() + " " + FarmaVariables.vPatUsu.trim() +
                                              " " + FarmaVariables.vMatUsu.trim());
                    // Inicio Adicion Delivery 28/04/2006 Paulo
                    //String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
                    //lblCliente.setText(nombreCliente);
                    // Fin Adicion Delivery 28/04/2006 Paulo
                    //FarmaUtility.moveFocus(tblProductos);
                    colocaUltimoPedidoDiarioVendedor();
                    FarmaUtility.moveFocus(txtDescProdOculto);
                    validaConvenio_v2(e, VariablesConvenio.vPorcCoPago);
                    FarmaUtility.moveFocus(txtDescProdOculto);
                    /*if(actuaRobot){
                        procesarToto(VariablesVentas.vNum_Ped_Vta, "F4");
                    }*/
                    //}
                } else if (UtilityPtoVenta.verificaVK_F1(e) && !pIngresoComprobanteManual) {
                    //vEjecutaAccionTeclaResumen = false;
                    /* if(cargaLogin_verifica())
                    {*/
                    //mfajardo 29/04/09 validar ingreso de productos virtuales
                    //VariablesVentas.vProductoVirtual = false;

                    //validaConvenio(e, VariablesConvenio.vPorcCoPago);
                    validaConvenio_v3(e, VariablesConvenio.vPorcCoPago);
                    
                    FarmaUtility.moveFocus(txtDescProdOculto); //}
                    // 2016.09.23 se desativo el robot
                    /*if(actuaRobot){
                        procesarToto(VariablesVentas.vNum_Ped_Vta, "F1");
                        
                    }*/
                }
                //JCORTEZ 17.04.08
                else if (e.getKeyCode() == KeyEvent.VK_F6 && !pIngresoComprobanteManual) {
                    //vEjecutaAccionTeclaResumen = false;
                    if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                        VariablesConvenioBTLMF.vCodConvenio != null &&
                        VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
                        //No se hace nada.
                    } else {
                        mostrarFiltro();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_F9 && !pIngresoComprobanteManual) {
                    if(lblF9.isVisible()){
                        //Agregado por FRAMIREZ 04.05.2012 descativa para el convenio BTLMF
                        if (!UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null)) {
                            //add jcallo 15/12/2008 campanias acumuladas.
                            //veririficar que el producto seleccionado tiene el flag de campanias acumuladas.
                            //validar que no sea un pedido por convenio
                            //log.debug("tecleo f9");
                            //vEjecutaAccionTeclaResumen = false;
                            if (VariablesVentas.vEsPedidoConvenio) {
                                FarmaUtility.showMessage(this,
                                                         "No puede asociar clientes a campa�as de ventas acumuladas en un " +
                                                         "pedido por convenio.", txtDescProdOculto);
                            } else { //toda la logica para asociar un cliente hacia campa�as nuevas
                                //DUBILLUZ - 29.04.2010
                                if (VariablesFidelizacion.vDniCliente.trim().length() > 0) {
                                    int rowSelec = tblProductos.getSelectedRow();
                                    String auxCodProd = "";
                                    if (rowSelec >=
                                        0) { //validar si el producto seleccionado tiene alguna campa�a asociada
                                        auxCodProd = tblProductos.getValueAt(rowSelec, 0).toString().trim();
                                    }
                                    asociarCampAcumulada(auxCodProd);
                                    //se agrego el metodo opera resumen pedido para aplicar las campanas de fidelizacion
                                    //operaResumenPedido(); REEMPLAZADO POR EL DE ABAJO
                                    neoOperaResumenPedido();
                                    //nuevo metodo jcallo 10.03.2009
                                    //FarmaUtility.setearPrimerRegistro(tblProductos,null,0);
                                } else {
                                    FarmaUtility.showMessage(this, "No puede ver las campa�as:\n" +
                                            "Porque primero debe de fidelizar al cliente con la funci�n F12.",
                                            txtDescProdOculto);
                                }
                            }

                            //JCALLO 19.12.2008 comentado sobre la opcion de ver pedidos delivery..y usarlo para el tema inscribir cliente a campa�as acumuladas
                            /** JCALLO INHABILITAR F9 02.10.2008* **/
                            /*log.debug("HABILITAR F9 : " + VariablesVentas.HabilitarF9);
                             if (VariablesVentas.HabilitarF9.equalsIgnoreCase(ConstantsVentas.ACTIVO)) {
                                 if (UtilityVentas.evaluaPedidoDelivery(this, tblProductos,
                                                                        VariablesVentas.vArrayList_ResumenPedido)) {
                                     evaluaTitulo();
                                 // Inicio Adicion Delivery 28/04/2006 Paulo
                                 if (VariablesVentas.vEsPedidoDelivery)
                                     generarPedidoDelivery();
                                 // Fin Adicion Delivery 28/04/2006 Paulo
                             }
                             FarmaUtility.moveFocus(txtDescProdOculto);
                            }*/
                        }
                    }
                } else if (UtilityPtoVenta.verificaVK_F10(e) && !pIngresoComprobanteManual) {
                    //vEjecutaAccionTeclaResumen = false;
                    //Agregado por FRAMIREZ 04.05.2012 descativa para el convenio BTLMF
                    if (!UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null)) {
                        verReceta();
                    }
                    FarmaUtility.moveFocus(txtDescProdOculto);
                } else if (UtilityPtoVenta.verificaVK_F12(e)) {
                    if((!DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)) || 
	                    (DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio) && 
	                       DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp())){//JMONZALVE 24042019
                        //ERIOS 18.02.2016 Verifica funcionalidad de puntos
	                    //AOVIEDO 13/07/2017 - SE CAMBIA LA VALIDACI�N PARA LOCALES TICO
	                    if(!UtilityPuntos.isActivoFuncionalidad()){ //IND_PUNTOS = 'N'
	                        /*FarmaUtility.showMessage(this, "Esta opci�n no est� habilitada.", txtDescProdOculto);
	                        return;*/
	                    
	                        // KMONCADA 2015.02.13 VALIDACION DE REINGRESO O CAMBIO DE CLIENTE
	                        
	                        if(!VariablesFidelizacion.vNumTarjeta.isEmpty()){
	                            if(JConfirmDialog.rptaConfirmDialog(this, "Ya registr� un cliente, �Desea cambiarlo?")){
	                                VariablesFidelizacion.vNumTarjeta = "";
	                                VariablesFidelizacion.limpiaVariables();
	                            }else{
	                                return;
	                            }
	                        }                        
	                        
	                        funcionF12("N",true);
	                    }else{ //IND_PUNTOS = 'S'
	                        // KMONCADA 2015.02.13 VALIDACION DE REINGRESO O CAMBIO DE CLIENTE
	                        BeanTarjeta tarjetaPuntosOld = null;
	                        String codProd;
	                        String descProd;
	                        UtilityVentas utilityVentas;
	                        //if(!pIngresoComprobanteManual){
	                        if(true){
	                            if(UtilityPuntos.isActivoFuncionalidad() && VariablesPuntos.frmPuntos != null){
	                                    
	                                if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
	                                    if(JConfirmDialog.rptaConfirmDialog(this, "Programa Puntos:\nYa registro un cliente, �desea cambiarlo?")){
	                                        tarjetaPuntosOld = VariablesPuntos.frmPuntos.getBeanTarjeta();
	                                        VariablesFidelizacion.vNumTarjeta = "";
	                                        VariablesFidelizacion.limpiaVariables();
	                                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
	
	
	
	
	                                        
	                                    }else{
	                                        return;
	
	
	
	
	
	
	
	
	
	
	
	
	                                    }
	                                }else{//[Desarrollo5] 19.10.2015 se quitan los valores de fidelizacion para que sean cargados nuevamente
	                                    /*VariablesFidelizacion.vNumTarjeta = "";
	                                    VariablesFidelizacion.limpiaVariables();
	                                    VariablesPuntos.frmPuntos.eliminarTarjetaBean();*/
	                                }
	
	
	                            }
	                        }else{
	                            log.info("PROGRAMA PUNTOS [F12] --> SE ENCUENTRA EN VENTA MANUAL NO APLICA");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	                        }
	                        funcionF12("N",true);
	                        /**
	                         * LTAVARA 2016.09.16
	                         * Validar los productos del pedido, si participan en el programa x+1 :
	                         * Cliente monedero: muestra solicitud de afiliacion al programa por producto. Si es tranferencia entre tarjetas solicita ingresar las cantidades de los productos x+1
	                         * Cliente no monedero : muestra mensaje en que programa participa el producto
	                         * */
	                         validarProductosXmas1(tarjetaPuntosOld);
	                        //vEjecutaAccionTeclaResumen = false;
	                        /*if(UtilityVentas.evaluaPedidoInstitucional(this, tblProductos, VariablesVentas.vArrayList_ResumenPedido)){
	                                evaluaTitulo();
	                           }
	                           FarmaUtility.moveFocus(txtDescProdOculto);*/
	                        // no podran ver vta institucional segun GERENCIA
	                        /*
	                            if (VariablesVentas.vEsPedidoConvenio) {
	                                FarmaUtility.showMessage(this,
	                                                         "No puede agregar una tarjeta a un " +
	                                                         "pedido por convenio.",
	                                                         txtDescProdOculto);
	                                return;
	                            }
	
	                            if (VariablesFidelizacion.vNumTarjeta.trim().length() >
	                                0) {
	                                FarmaUtility.showMessage(this,
	                                                         "No puede ingresar mas de una tarjeta.",
	                                                         txtDescProdOculto);
	                                txtDescProdOculto.setText("");
	                            } else
	                                mostrarBuscarTarjetaPorDNI();
	                            */
	                    }
	            	}//JMONZALVE 24042019
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    //vEjecutaAccionTeclaResumen = false;
                    if (JConfirmDialog.rptaConfirmDialog(this, "Est� seguro que Desea salir del pedido?")) {
                        //Agregado por FRAMIREZ 27.03.2012
                        VariablesConvenioBTLMF.limpiaVariablesBTLMF();

                        cancelaOperacion_02();
                        VariablesVentas.vCodProdBusq = "";
                        VariablesVentas.vCodBarra = "";

                        //mfajardo 29/04/09 validar ingreso de productos virtuales
                        VariablesVentas.vProductoVirtual = false;
                        //jquispe 13.01.2011
                        FarmaVariables.vAceptar = false;
                        VariablesCaja.vVerificaCajero = false;
                        
                        if(VariablesPuntos.frmPuntos!=null){//validacion cuando es null 01/04/2015
                             VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                        }
                       
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_INSERT) { //Inicio ASOSA 03.02.2010
                    VariablesVentas.vIndPrecioCabeCliente = "S";
                    DlgListaProdDIGEMID objDIGEMID = new DlgListaProdDIGEMID(myParentFrame, "", true);
                    objDIGEMID.setVisible(true);
                    cancelaOperacion_02();

                    //mfajardo 29/04/09 validar ingreso de productos virtuales
                    VariablesVentas.vProductoVirtual = false;
                    cerrarVentana(true);
                } else if (UtilityPtoVenta.verificaVK_F11(e) && pIngresoComprobanteManual) {
                    //COBRA PEDIDO MANUAL
                    //dubilluz 13.10.2014
                    cobraPedidoManual();
                }
                //Fin ASOSA 03.02.2010
                //vEjecutaAccionTeclaResumen = false;
                //pruebas de validacion
                //int i=1/0;
                //if(true)
                // return;
            }
        }
        //try
        catch (Exception exc) {
            log.error("", exc);
            log.debug("catch" + vEjecutaAccionTeclaResumen);
        } finally {
            vEjecutaAccionTeclaResumen = false;
            //log.debug(" finally: " + vEjecutaAccionTeclaResumen);
        }
    }

    /** Valida que usuario logueado sea del Rol Quimico
     * LTAVARA
     * 28.08.2014
     * */

    private boolean cargaLoginAdmLocal() {
        String numsec = FarmaVariables.vNuSecUsu;
        String idusu = FarmaVariables.vIdUsu;
        String nomusu = FarmaVariables.vNomUsu;
        String apepatusu = FarmaVariables.vPatUsu;
        String apematusu = FarmaVariables.vMatUsu;

        try {
            DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);

            dlgLogin.setRolUsuario(FarmaConstants.ROL_ADMLOCAL);

            dlgLogin.setVisible(true);
            FarmaVariables.vNuSecUsu = numsec;
            FarmaVariables.vIdUsu = idusu;
            FarmaVariables.vNomUsu = nomusu;
            FarmaVariables.vPatUsu = apepatusu;
            FarmaVariables.vMatUsu = apematusu;
        } catch (Exception e) {
            FarmaVariables.vNuSecUsu = numsec;
            FarmaVariables.vIdUsu = idusu;
            FarmaVariables.vNomUsu = nomusu;
            FarmaVariables.vPatUsu = apepatusu;
            FarmaVariables.vMatUsu = apematusu;
            FarmaVariables.vAceptar = false;
            log.error("", e);
            FarmaUtility.showMessage(this, "Ocurri� un error al validar rol de usuariario \n : " + e.getMessage(),
                                     null);
        }
        return FarmaVariables.vAceptar;
    }

    /**
     * Se verifica si se hizo un cierre de DIGEMID para cerrarlo o no
     * @author ASOSA
     * @since 03.02.2010
     */
    private void verificarDIGEMID() {
        if (VariablesVentas.vIndPrecioCabeCliente.equalsIgnoreCase("S")) { //Inicio ASOSA 03.02.2010
            VariablesVentas.vIndPrecioCabeCliente = "N";
            cancelaOperacion_02();

            //mfajardo 29/04/09 validar ingreso de productos virtuales
            VariablesVentas.vProductoVirtual = false;
            cerrarVentana(true);
        } //Fin ASOSA 03.02.2010
    }

    private void cerrarVentana(boolean pAceptar) {
        VariablesVentas.vListaProdFaltaCero = new ArrayList();
        VariablesVentas.vListaProdFaltaCero.clear();
        FarmaVariables.vAceptar = pAceptar;

        this.setVisible(false);
        this.dispose();
    }

    // **************************************************************************
    // Metodos de l�gica de negocio
    // **************************************************************************

    private void agregarProducto(String codigo) {
        log.debug("Entro aca :::: 23.04.2010");
        //FarmaUtility.moveFocus(tblProductos);

        if (VariablesVentas.vCodFiltro.equalsIgnoreCase(ConstantsVentas.IND_OFER)) {
            String vkF = "F6";
            agregarComplementarios(vkF);
        } else { //if (!VariablesVentas.vProductoVirtual)

            //INI ASOSA - 10/10/2014 - PANHD
            boolean flag = false;
            /* SE COMENTO X MIENTRAS PORQUE QUIEREN YAYAYAYA QUE SEA TICO Y SE COMPORTE COMO FARMA - ASOSA 23/10/2014
            if (VariablesPtoVenta.vIndTico.equals("S")) {
                if(aumentarCantidad(codigo)){
                    flag = true;
                }
            }
           */
            //FIN ASOSA - 10/10/2014 - PANHD

            //if(!flag)  //ASOSA - 10/10/2014 - PANHD
            if (true) {
                if (!flag) {
                    DlgListaProductos dlgListaProductos = new DlgListaProductos(myParentFrame, "", true);
                    dlgListaProductos.setPIngresoComprobanteManual(pIngresoComprobanteManual);
                    dlgListaProductos.setResumenPedido(this);
                    dlgListaProductos.setVisible(true);
                    
                    log.info("PEDIDO FIDELIZADO?????");
                    log.info("NUMERO DE TARJETA DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vNumTarjeta);
                    log.info("NUMERO DE DNI DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vDniCliente);
                    if(VariablesPuntos.frmPuntos==null)
                        log.info("VariablesPuntos.frmPuntos es null");
                    else    
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
                        log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
                    }
                    
                }
                verificarDIGEMID(); //ASOSA 03.02.2010
                
                log.debug("ANTES DE neoOperaResumenPedido ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
                log.debug(""+VariablesVentas.vArrayList_PedidoVenta);
                
                //operaResumenPedido(); REEMPLAZADO POR EL DE ABAJO
                neoOperaResumenPedido(); //nuevo metodo jcallo 10.03.2009

                if (VariablesConvenio.vCodConvenio.equalsIgnoreCase("")) {
                    lblCuponIngr.setText(VariablesVentas.vMensCuponIngre);
                } else {
                    VariablesVentas.vMensCuponIngre = "";
                    lblCuponIngr.setText(VariablesVentas.vMensCuponIngre);
                }

                FarmaVariables.vAceptar = false;

                if (VariablesVentas.vIndDireccionarResumenPed) {
                    if (!VariablesVentas.vIndF11) {
                        /*if(com.gs.mifarma.componentes.JConfirmDialog.rptaConfirmDialog(this,"�Desea agregar m�s productos al pedido?"))
                {
                agregarProducto();
                }*/
                    }
                }

                if (vIndPedidosDelivery) {
                    vIndPedidosDelivery = false;
                    abrePedidosDelivery();
                }
            } else {
                //INI ASOSA - 10/10/2014 - PANHD

                //if (VariablesPtoVenta.vIndTico.equals("N")) {SE COMENTO X MIENTRAS PORQUE QUIEREN YAYAYAYA QUE SEA TICO Y SE COMPORTE COMO FARMA - ASOSA 23/10/2014
                if (false) {
                    FarmaUtility.showMessage(this, "Ya se selecciono un producto virtual", txtDescProdOculto);
                }
                //FIN ASOSA - 10/10/2014 - PANHD
            }
        }

        txtDescProdOculto.setText("");
        VariablesVentas.vCodFiltro = "";
        VariablesVentas.vIndF11 = false;
    }

    /**
     * Metodo que determina si un producto ya existe en la lista, si existe le aumenta 1.
     * @author ASOSA
     * @since 10/10/2014
     * @param codigo
     * @return
     */
    public boolean aumentarCantidad(String codigo) {
        log.info("VariablesVentas.vCodigoBarra AUMENTARCANTIDAD: " + VariablesVentas.vCodigoBarra);
        //log.info("LONGITUD DE ARRAY: " + VariablesVentas.vArrayList_ResumenPedido.size() + " MULTIPLO " +
                 //FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_ResumenPedido, 0, 27).toString());
        boolean flag = false;
        int pFila = -1;
        String textoCod = "";
        Integer cantAnt = 0;
        log.info("HITO 01 - aumentarCantidad " + codigo);
        if (codigo != null && !codigo.equals("") && codigo.length() == 6) {
            log.info("HITO 02 - aumentarCantidad");
            for (int c = 0; c < UtilityCalculoPrecio.getSizeDetalleVenta(); c++) {
                log.info("HITO 03 - aumentarCantidad");
                textoCod = UtilityCalculoPrecio.getBeanPosDetalleVenta(c).getVCodProd();
                log.info("HITO 04 - aumentarCantidad");
                if (textoCod.trim().equals(codigo)) {
                    flag = true;
                    pFila = c;
                    String valFracFila = "1";
                    if (!determinarIgualdadMultiplo(pFila)) {
                        if (JConfirmDialog.rptaConfirmDialogDefaultNo(this,
                                                                      "Esta escaneando producto ya existente en diferente presentaci�n, \nSi contin�a se elminara el anterior, \n�desea continuar?")) {
                            UtilityCalculoPrecio.deleteBeannPosDetalleVenta(pFila);
                            flag = false; //para que luego de borrar el anterior busque el producto y lo muestre en su nueva forma de presentacion.
                        }
                    } else {
                        
                        BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(pFila);
                        
                        if(bean.getVValorMultiplicacion().trim().length()==0){
                            valFracFila = "1";
                        }
                        else{
                            valFracFila =bean.getVValorMultiplicacion().trim();
                        }
                        
                        cantAnt = Integer.parseInt(bean.getVCtdVta());
                        cantAnt = cantAnt + Integer.parseInt(valFracFila); //puede ser 1 o puede ser otro numero debido a un SIX pack x ejemplo
                        
                        if (existeStockOtraVez(textoCod, cantAnt,
                                               pFila)) { 
                            //VALIDA EL STOCK ACA Y NO EN "DLG INGRESO CANTIDAD" PORQUE EN ESTE CASO DEFRENTE AUMENTA CANTIDAD SIN PASAR A LA SIGUIENTE VENTANA.
                            UtilityCalculoPrecio.getListaDetalleVenta().get(pFila).setVCtdVta(cantAnt.toString());
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * Determina si existe stock de un producto normal o final
     * @author ASOSA
     * @since 15/10/2014
     * @param codigo
     * @param cant
     * @param fila
     * @return
     */
    private boolean existeStockOtraVez(String codigo, Integer cant, int fila) {
        String valFrac = UtilityCalculoPrecio.getBeanPosDetalleVenta(fila).getVValFracVta();
        String tipoProducto =UtilityCalculoPrecio.getBeanPosDetalleVenta(fila).getVTipoProducto();
        boolean flag = true;

        if (tipoProducto.trim().equals(ConstantsVentas.TIPO_PROD_FINAL)) {
            if (!UtilityVentas.existsStockComp(this, codigo, cant, txtDescProdOculto)) {
                flag = false;
            }
        } else {
            if (!existeStockProductoNormal(codigo, cant.toString(), valFrac)) {
                FarmaUtility.showMessage(this, "No hay suficiente stock para vender el producto.", txtDescProdOculto);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Determina si existe stock de un producto normal
     * @author ASOSA
     * @since 15/10/2014
     * @param pCodProd
     * @param pCantidad
     * @param pValFrac
     * @return
     */
    private boolean existeStockProductoNormal(String pCodProd, String pCantidad, String pValFrac) {
        ArrayList lista = new ArrayList();
        int valFracLocal = 0;
        int stockLocal = 0;
        boolean flag = false;
        try {
            DBVentas.obtieneInfoProductoVta(lista, pCodProd);
        } catch (Exception nfe) {
            log.error("", nfe);
        }
        stockLocal = Integer.parseInt(((String)((ArrayList)lista.get(0)).get(0)).trim());
        valFracLocal = Integer.parseInt(((String)((ArrayList)lista.get(0)).get(2)).trim());
        int cantidadPedido = (Integer.parseInt(pCantidad) * valFracLocal) / Integer.parseInt(pValFrac);

        if (cantidadPedido <= stockLocal) {
            flag = true;
        }
        return flag;
    }

    /**
     * Determina la igualdad del valor multiplicador entre el doble registro de un producto con unidades de agrupacion diferente(SIX PACK, FOUR PACK, etc).
     * @author ASOSA
     * @since 15/10/2014
     * @param fila
     * @return
     */
    private boolean determinarIgualdadMultiplo(int fila) {
        boolean flag = false;
        BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(fila);
        if (bean.getVTipoProducto().trim().length()>0) {
            if (VariablesVentas.vCodigoBarra == null || VariablesVentas.vCodigoBarra.trim().equals("")) {
                flag = true;
            } else {
                String[] list = UtilityVentas.obtenerArrayValoresBd(ConstantsVentas.TIPO_VAL_ADIC_COD_BARRA);
                String new_frac = "1";
                if (!list[0].equals("THERE ISNT")) {
                    new_frac = list[0];
                }
                String valFracFila = bean.getVValorMultiplicacion();
                if (new_frac.trim().equals(valFracFila.trim())) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }

    private void muestraDetalleProducto() {
        if (tblProductos.getRowCount() == 0)
            return;
        VariablesVentas.vCod_Prod = ((String)(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0))).trim();
        VariablesVentas.vDesc_Prod = ((String)(tblProductos.getValueAt(tblProductos.getSelectedRow(), 1))).trim();
        VariablesVentas.vNom_Lab = ((String)(tblProductos.getValueAt(tblProductos.getSelectedRow(), 9))).trim();
        DlgDetalleProducto dlgDetalleProducto = new DlgDetalleProducto(myParentFrame, "", true);
        dlgDetalleProducto.setVisible(true);
    }

    private void inicializaArrayList() {
        VariablesVentas.vArrayList_PedidoVenta = new ArrayList();
        UtilityCalculoPrecio.clearDetalleVenta();
        /**
     * Reinicia Array
     * @author : dubilluz
     * @since  : 19.06.2007
     */
        VariablesVentas.vArrayList_Promociones = new ArrayList();
        VariablesVentas.vArrayList_Prod_Promociones = new ArrayList();

        VariablesISCBolsas.vArrayList_ProductosISC = new ArrayList(); // JHAMRC 10072019
        VariablesISCBolsas.vArrayList_ProductosISC_Temp = new ArrayList();// JHAMRC 10072019
    }

    /***
     * calcular montos totales del resumen pedido
     * **/
    private void calculaTotalesPedido() {
        
        //dubilluz 31.08.2016
        if(VariablesVentas.vNumPedido_Receta.trim().length()>0)
            cargaIconoReceta();
        //dubilluz 31.08.2016

        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {

            jbInitBTLMF();
        }

        log.debug("calculando montos totales");
        //lero lero reemplazando el anterior por el nuevo
        //validaPedidoCupon();
        //el nuevo metodo de calculo de dcto por campana cupones
        calculoDctosPedidoXCupones();

        if (lblDscto.getText().trim().equalsIgnoreCase("0.00")) {
            lblDsctoT.setVisible(false);
            lblDscto.setVisible(false);
        }

        if (UtilityCalculoPrecio.getSizeDetalleVenta()<= 0 &&
            VariablesVentas.vArrayList_Promociones.size() <= 0) {
            log.debug("LISTA CERO SUPUESTAMENTE NO HAY PRODUCTOS");
            //FarmaUtility.showMessage(this,"NO HAY PRODUCTOS EN EL LISTADO RESUMEN\ncomuniquese con operador de sistemas!",tblProductos);
            lblBruto.setText("0.00");
            lblDscto.setText("0.00");
            lblDsctoPorc.setText("(0.00%)");
            lblIGV.setText("0.00");
            lblTotalS.setText("0.00");
            lblTotalD.setText("0.00");
            lblRedondeo.setText("0.00");
            lblItems.setText("0");
            evaluaProductoRegalo();
            evaluaCantidadCupon();

            lblCredito.setVisible(false);
            lblCreditoT.setVisible(false);
            lblPorPagarT.setVisible(false);
            lblPorPagar.setVisible(false);

            return;
        }

        double totalBruto = 0.00;
        double totalBrutoRedondeado = 0.00;
        double totalAhorro = 0.00;
        double totalDscto = 0.00;
        double totalIGV = 0.00;
        double totalNeto = 0.00;
        double totalNetoRedondeado = 0.00;
        double redondeo = 0.00;
        int cantidad = 0;
        // valores de Productos EXCLUYENTES ACUMULA AHORRO DIARIO
        int cantidad_excluye = 0;
        double totalBruto_excluye = 0.00;
        double totalIGV_excluye = 0.00;
        double totalNeto_excluye = 0.00;
        double totalBrutoRedondeado_excluye = 0.00;
        double totalNetoRedondeado_excluye = 0.00;
        double redondeo_excluye = 0.00;
        double totalAhorro_excluye = 0.00;
        double totalDscto_excluye = 0.00;
        
        // dubilluz 2019.07.31
        double totalImpICBPER = 0.0;
        double vGetSubTotalBolsaMediana = 0.0;
        String pCodProdBolsaMediana = "538507" ; //PENDIENTE PARAMETRIZAR
        boolean ventaBolsMediana = false;
        try {
            vGetSubTotalBolsaMediana = DBVentas.getSubTotalBolsa("M");
        } catch (Exception sqle) {
            // TODO: Add catch code
            vGetSubTotalBolsaMediana = 0.0;
            sqle.printStackTrace();
        }
        
        //log.debug("LISTADO DE PRODUCTOS RESUMEN " + VariablesVentas.vArrayList_ResumenPedido);
        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
            log.debug("--------------------------------------------------------------------");
            log.debug("VariablesVentas.vArrayList_ResumenPedido.get(i):" );
            BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
            
            if(!bean.getVCodProd().equalsIgnoreCase(pCodProdBolsaMediana)){
            
            cantidad = Integer.parseInt(bean.getVCtdVta());
            totalBruto +=FarmaUtility.getDecimalNumber(//bean.getPPREC_VTA_UNIT_NVO() // dubilluz 2016.12.29
                                                        bean.getVPrecioBase()
                                                       ) *cantidad;
            totalIGV += FarmaUtility.getDecimalNumber(bean.getVValIgv());
            totalNeto += FarmaUtility.getDecimalNumber(bean.getVSubTotal());
            
            // INI JHAMRC 10072019 Se adiciona el total ICBPER al total neto
            if(!bean.getVIcbperTotal().equals(""))
                totalNeto += FarmaUtility.getDecimalNumber(bean.getVIcbperTotal());
            // FIN JHAMRC 10072019

            ///////////////////////////////////////////////////////////////////////////////////////
            for (int j = 0; j < VariablesVentas.vListProdExcluyeAcumAhorro.size(); j++) {
                String pCod = VariablesVentas.vListProdExcluyeAcumAhorro.get(j).toString();
                if (UtilityCalculoPrecio.getBeanPosDetalleVenta(i).getVCodProd().trim().equalsIgnoreCase(pCod)) {
                    cantidad_excluye = cantidad;
                    totalBruto_excluye += totalBruto;
                    totalIGV_excluye += totalIGV;
                    totalNeto_excluye += totalNeto;
                    log.debug("cantidad_excluye:" + cantidad_excluye);
                    log.debug("totalBruto_excluye:" + totalBruto_excluye);
                    log.debug("totalIGV_excluye:" + totalIGV_excluye);
                    log.debug("totalNeto_excluye:" + totalNeto_excluye);
                }
            }

            log.debug("cantidad:" + cantidad);
            log.debug("totalBrutoAcumulado:" + totalBruto);
            log.debug("totalIGVAcumulado:" + totalIGV);
            log.debug("totalNetoAcumulado:" + totalNeto);
            
            }
            else{
                ventaBolsMediana = true;
                totalImpICBPER+= FarmaUtility.getDecimalNumber(bean.getVIcbperTotal());
            }
            
        }
        log.debug("LISTADO DE PRODUCTOS PROMOCION: " + VariablesVentas.vArrayList_Promociones);
        for (int i = 0; i < VariablesVentas.vArrayList_Promociones.size(); i++) {
            log.debug("--------------------------------------------------------------------");
            log.debug("VariablesVentas.vArrayList_Promociones.get(i):" +
                      VariablesVentas.vArrayList_Promociones.get(i));
            cantidad = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).get(4));
            totalBruto +=
                    FarmaUtility.getDecimalNumber((String)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).get(3)) *
                    cantidad;
            totalNeto +=
                    FarmaUtility.getDecimalNumber((String)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).get(7));
            totalIGV +=
                    FarmaUtility.getDecimalNumber((String)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).get(12));
            log.debug("cantidad:" + cantidad);
            log.debug("totalBrutoAcumulado:" + totalBruto);
            log.debug("totalIGVAcumulado:" + totalIGV);
            log.debug("totalNetoAcumulado:" + totalNeto);
        }
        //hasta aqui se tiene la suma de los subtotales
        //totalBruto = FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(totalBruto,2));//suma del precio tales como aparecen en la lista sin dctos
        //totalNeto = FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(totalNeto,2));//suma de subtotales aplicando dctos
        //totalIGV = FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(totalIGV,2));//total igv, basado en los subtotales con dctos

        //redondeo total bruto a 2 cifras
        totalBrutoRedondeado = UtilityVentas.Redondear(totalBruto, 2);

        /// excluyente
        //redondeo total bruto a 2 cifras
        totalBrutoRedondeado_excluye = UtilityVentas.Redondear(totalBruto_excluye, 2);
        //total bruto a 2 cifras decimales a favor del cliente multiplo de .05
        totalBrutoRedondeado_excluye = UtilityVentas.ajustarMonto(totalBrutoRedondeado_excluye, 2);

        //redondeo total neto a 2 cifras
        totalNetoRedondeado =
                UtilityVentas.Redondear(totalNeto, 2); //redondeo a 2 cifras pero no a ajustado a .05 o 0.00

        //total neto a 2 cifras decimales a favor del cliente multiplo de .05
        //redondeo total neto a 2 cifras
        totalNetoRedondeado_excluye =
                UtilityVentas.Redondear(totalNeto_excluye, 2); //redondeo a 2 cifras pero no a ajustado a .05 o 0.00
        //total neto a 2 cifras decimales a favor del cliente multiplo de .05
        
        int tipo = UtilityVentas.getTipoRedondeo();
        
        //totalNetoRedondeado = UtilityVentas.ajustarMonto(totalNetoRedondeado, 3);
        double totalNetoRedNUEVO = UtilityVentas.ajustarMonto(totalNetoRedondeado, 3);
        //ERIOS 2.4.7 Para pedido convenio, no ajusta el total
        if ( //UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
            VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
            ;
        } else {
            //total bruto a 2 cifras decimales a favor del cliente multiplo de .05
            totalBrutoRedondeado = UtilityVentas.ajustarMonto(totalBrutoRedondeado, 2);  
            
            if(tipo == 2){
                totalNetoRedondeado = UtilityVentas.Truncar(totalNetoRedondeado, 1);
            }else{
                totalNetoRedondeado = UtilityVentas.ajustarMonto(totalNetoRedondeado, 2);
            }
        }
        
        if(ventaBolsMediana)
            redondeo = totalNetoRedondeado - totalNeto - 0.04;
        else
            redondeo = totalNetoRedondeado - totalNeto;
        
        totalNetoRedondeado = totalNetoRedondeado + totalImpICBPER;
            
        /////////////////////////////////////////////////////////////////////////////////////
        //double totalNetoRedNUEVO_excluye = UtilityVentas.ajustarMonto(totalNetoRedondeado_excluye, 3);
        totalNetoRedondeado_excluye = UtilityVentas.ajustarMonto(totalNetoRedondeado_excluye, 2);

        redondeo_excluye = totalNetoRedondeado_excluye - totalNeto_excluye;

        /////////////////////////////////////////////////////////////////////////////////////
        //mfajardo 18.05.09 : si es convenio no debe mostrar ahorro
        if (!VariablesVentas.vEsPedidoConvenio) {
            //totalAhorro = (totalBrutoRedondeado - totalNetoRedondeado);
            totalAhorro = (totalBrutoRedondeado - totalNetoRedNUEVO);
            //Se comento en Convenios: totalAhorro_excluye = (totalBrutoRedondeado_excluye - totalNetoRedNUEVO_excluye);
        }

        totalDscto = UtilityVentas.Redondear((totalAhorro * 100.00) / totalBruto, 2);
        totalDscto_excluye = UtilityVentas.Redondear((totalAhorro_excluye * 100.00) / totalBruto_excluye, 2);

        //Se verifica el ahorro que se obtiene este ahorro no debe de exceder al Maximo
        //DUBILLUZ 28.05.2009
        boolean pLlegoTopeDscuento = false;
        lblTopeAhoro.setText("");
        
        boolean isEvaluaFidelizacion = false;
        
        if(UtilityPuntos.isActivoFuncionalidad()){
            if(VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.length() > 0){
                isEvaluaFidelizacion = true;
            }
        }

        if (VariablesFidelizacion.vDniCliente.trim().length() > 0 && !isEvaluaFidelizacion) {
            if (VariablesFidelizacion.vAhorroDNI_Pedido > 0)
                totalAhorro = VariablesFidelizacion.vAhorroDNI_Pedido - totalAhorro_excluye;

            if (totalAhorro > 0) {
                if (totalAhorro + VariablesFidelizacion.vAhorroDNI_x_Periodo - totalAhorro_excluye >=
                    VariablesFidelizacion.vMaximoAhorroDNIxPeriodo) {
                    /*
                    SE COMENTO ESTA PARTE PARA QUE EL MENSAJE QUE SE MUESTRE SEA SIEMPRE EL AHORRO EXITENTE
                    YA QUE DEBIDO A LA CAMPA�A CMR A BELACTA 1 DEBE DE EXCEDER LOS 50SOLES DIARIO PERO NO DEBE DE
                    USARSE PARA ACUMULAR EL AHORRO. PERO SE PIDIO MOSTRAR TO_DO EL AHORRO.
                    totalAhorro =
                            VariablesFidelizacion.vMaximoAhorroDNIxPeriodo -
                            VariablesFidelizacion.vAhorroDNI_x_Periodo;
                    */
                    pLlegoTopeDscuento = true;
                }
                //ya no se muestra el total bruto
                //por si algun dia quiera volver mostrar
                lblBruto.setText(FarmaUtility.formatNumber(totalBrutoRedondeado));
                //jcallo 02.10.2008 se modifico por el tema del texto de ahorro
                try{
                    lblDsctoT.setText(DBPuntos.getTextoAhorro());
                }catch(Exception ex){
                    log.error(" ",ex);
                }
                if (pLlegoTopeDscuento) {
                    lblDscto.setText(FarmaUtility.formatNumber(totalAhorro));
                    //lblDscto.setText(FarmaUtility.formatNumber(totalAhorro)+"-"+FarmaUtility.formatNumber(VariablesFidelizacion.vAhorroDNI_Pedido));
                    lblTopeAhoro.setText(" (Lleg� al tope de descuento "+ConstantesUtil.simboloSoles+" " +
                                         FarmaUtility.formatNumber(VariablesFidelizacion.vMaximoAhorroDNIxPeriodo) +
                                         " )");
                } else {
                    lblDscto.setText(FarmaUtility.formatNumber(totalAhorro));
                    //lblDscto.setText(FarmaUtility.formatNumber(totalAhorro)+"-"+FarmaUtility.formatNumber(VariablesFidelizacion.vAhorroDNI_Pedido));
                    lblTopeAhoro.setText("");
                }
            }
        } else {
            try{
                lblDsctoT.setText(DBPuntos.getTextoAhorro());
            }catch(Exception ex){
                log.error(" ",ex);
            }
            //ya no se muestra el total bruto
            //por si algun dia quiera volver mostrar
            lblBruto.setText(FarmaUtility.formatNumber(totalBrutoRedondeado));
            //jcallo 02.10.2008 se modifico por el tema del texto de ahorro
            lblDscto.setText(FarmaUtility.formatNumber(totalAhorro));
        }


        if (totalAhorro > 0.0) {
            lblDsctoT.setVisible(true);
            lblDscto.setVisible(true);
        } else {
            lblDsctoT.setVisible(false);
            lblDscto.setVisible(false);
        }
        //fin jcallo 02.10.2008
        lblDsctoPorc.setText(FarmaUtility.formatNumber(totalDscto));
        if (FarmaUtility.getDecimalNumber(lblDsctoPorc.getText().trim()) > 0) {
            lblDsctoPorc.setVisible(true);
        }

        lblIGV.setText(FarmaUtility.formatNumber(totalIGV));

        lblPorPagarT.setVisible(false);
        lblPorPagar.setVisible(false);
        lblCredito.setVisible(false);
        lblCreditoT.setVisible(false);

        VariablesConvenioBTLMF.vImpSubTotal = totalNetoRedondeado;

        //Agregado por FRAMIREZ 26.03.2012 Calcula El monto de credito de tipo convenio COPAGO.
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {

            double montoCredito;

            if (VariablesConvenioBTLMF.vValorSelCopago == -1) {
                montoCredito =
                        UtilityConvenioBTLMF.obtieneMontoCredito(this, null, new Double(totalNetoRedondeado), "",
                                                                 VariablesConvenioBTLMF.vCodConvenio,
                                                                 VariablesConvenioBTLMF.vValorSelCopago);
            } else {

                //VariablesConvenio.vPorcCoPago=String.valueOf(100-VariablesConvenioBTLMF.vValorSelCopago);
                montoCredito = ((100 - VariablesConvenioBTLMF.vValorSelCopago) / 100) * totalNetoRedondeado;
            }
            double porcentajeCredito = (montoCredito / totalNetoRedondeado) * 100;

            //log.debug("porcentajeCredito:::"+porcentajeCredito);

            if (porcentajeCredito > 0) {
                double montoaPagar = (totalNetoRedondeado - montoCredito);

                lblPorPagarT.setVisible(true);
                lblPorPagar.setVisible(true);
                lblCredito.setVisible(true);
                lblCreditoT.setVisible(true);

                lblCredito.setText(FarmaUtility.formatNumber(montoCredito));
                //ERIOS 2.4.6 Redondeo por calculo de porcentaje
                String porcCoPago = FarmaUtility.formatNumber((montoCredito / totalNetoRedondeado) * 100, 2);

                if (montoaPagar == 0) {
                    lblCreditoT.setText("Cr�dito(" + porcCoPago + "%): "+ConstantesUtil.simboloSoles);
                    lblPorPagarT.setText("");
                    lblPorPagar.setText("");
                } else {
                    lblCreditoT.setText("Cr�dito(" + porcCoPago + "%): "+ConstantesUtil.simboloSoles);
                    lblPorPagarT.setText("CoPago: "+ConstantesUtil.simboloSoles);
                    lblPorPagar.setText(FarmaUtility.formatNumber(montoaPagar));
                }
            } else {
                lblPorPagarT.setVisible(false);
                lblPorPagar.setVisible(false);
                lblCredito.setVisible(false);
                lblCreditoT.setVisible(false);
            }
        } else {
            lblCreditoT.setText(" ");
            lblCredito.setText(" ");
            lblPorPagar.setText(" ");
            lblPorPagarT.setText(" ");
        }

        lblTotalS.setText(FarmaUtility.formatNumber(totalNetoRedondeado));
        lblTotalD.setText(FarmaUtility.formatNumber(totalNetoRedondeado /
                                                    FarmaVariables.vTipCambio)); //obtener el tipo de cambio del dia
        lblRedondeo.setText(FarmaUtility.formatNumber(redondeo));
        lblItems.setText(String.valueOf(UtilityCalculoPrecio.getSizeDetalleVenta() +
                                        VariablesVentas.vArrayList_Prod_Promociones.size()));

        evaluaProductoRegalo();
        evaluaCantidadCupon();
        
        setISCTotal();

        /*
    //Se evalua si ya esta en el limite de ahorro diario
    //DUBILLUZ 28.05.2009
    if(VariablesFidelizacion.vAhorroDNI_Pedido + VariablesFidelizacion.vAhorroDNI_x_Periodo>=VariablesFidelizacion.vMaximoAhorroDNIxPeriodo)
    {
        FarmaUtility.showMessage(this,"El tope de descuento diario por persona es de "+ConstantesUtil.simboloSoles+VariablesFidelizacion.vMaximoAhorroDNIxPeriodo+". \n"+
                                      "El cliente ya llego a su tope diario"
                                      , tblProductos);
    }
    */

    }

    private void muestraIngresoCantidad() {
        if (tblProductos.getRowCount() == 0)
            return;
        int vFila = tblProductos.getSelectedRow();
        String pCodProdOrigen = ((String)(tblProductos.getValueAt(vFila, 0))).trim();
        VariablesVentas.vCod_Prod = ((String)(tblProductos.getValueAt(vFila, 0))).trim();
        VariablesVentas.vDesc_Prod = ((String)(tblProductos.getValueAt(vFila, 1))).trim();
        VariablesVentas.vVal_Prec_Lista = ((String)(tblProductos.getValueAt(vFila, 3))).trim();
        VariablesVentas.vNom_Lab = ((String)(tblProductos.getValueAt(vFila, 9))).trim();
        VariablesVentas.vCant_Ingresada_Temp = ((String)(tblProductos.getValueAt(vFila, 4))).trim();
        
        if(UtilityProgramaAcumula.vAutomaticoIngresoCantidad)
            UtilityProgramaAcumula.vCantidad = VariablesVentas.vCant_Ingresada_Temp;
        else
            UtilityProgramaAcumula.vCantidad = "";
        
        VariablesVentas.vVal_Frac = ((String)(tblProductos.getValueAt(vFila, 10))).trim();
        VariablesVentas.vPorc_Igv_Prod = ((String)(tblProductos.getValueAt(vFila, 11))).trim();
        VariablesVentas.vPorc_Dcto_1 = ((String)(tblProductos.getValueAt(vFila, 5))).trim();
        log.info("((String)(tblProductos.getValueAt(vFila,5))).trim() : " +
                 ((String)(tblProductos.getValueAt(vFila, 5))).trim());
        /*
        VariablesVentas.vVal_Prec_Vta =
                ((String)(tblProductos.getValueAt(vFila, 6))).trim();
        */
        //INI ASOSA - 03/09/2014 - CORRECCION

        double precio = 0.0;
        String valMulti = "1";
        VariablesVentas.vValorMultiplicacion = "1";

        if (tableModelResumenPedido.getRow(vFila).size() >= 28) {
            valMulti = FarmaUtility.getValueFieldArrayList(tableModelResumenPedido.data, vFila, 27);
        }
        //INI ASOSA - 09/10/2014
        String tipoProducto = "";
        if (tableModelResumenPedido.getRow(vFila).size() >= 29) {
            tipoProducto = FarmaUtility.getValueFieldArrayList(tableModelResumenPedido.data, vFila, 28);
        }
        //FIn ASOSA - 09/10/2014

        precio =
                FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tableModelResumenPedido.data, vFila, 6)) *
                FarmaUtility.getDecimalNumber(valMulti); //ASOSA - 12/08/2014

        VariablesVentas.vValorMultiplicacion = valMulti; //ASOSA - 12/08/2014 , old
        VariablesVentas.tipoProducto = tipoProducto; //ASOSA - 09/10/2014 - PANHD

        //INI ASOSA - 03/09/2014 - CORRECCION

        VariablesVentas.vVal_Prec_Vta = "" + precio; //ASOSA - 12/08/2014

        //log.debug("VariablesVentas.vPorc_Igv_Prod : " + VariablesVentas.vPorc_Igv_Prod);
        VariablesVentas.vIndOrigenProdVta = FarmaUtility.getValueFieldJTable(tblProductos, vFila, COL_RES_ORIG_PROD);
        VariablesVentas.vPorc_Dcto_2 = "0";
        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
        VariablesVentas.vCantxDia = "";
        VariablesVentas.vCantxDias = "";

        log.debug("-------------------------------------------------------------");
        log.debug("-------------------------------------------------------------");
        log.debug("-------------Metodo: muestraIngresoCantidad------------------");
        log.debug("-------------------------------------------------------------");
        log.debug("-------------------------------------------------------------");
        log.debug("-------------------------------------------------------------");


        log.debug("VariablesVentas.vVal_Prec_Vta:" + VariablesVentas.vVal_Prec_Vta);


        //if (VariablesVentas.vEsPedidoConvenio) {
        ///////////////////////////////////////////////////////////////////////////////////////

        VariablesConvenio.vVal_Prec_Vta_Local = ((String)(tblProductos.getValueAt(vFila, 6))).trim();
        VariablesConvenio.vVal_Prec_Vta_Conv = VariablesVentas.vVal_Prec_Vta;
        VariablesConvenioBTLMF.vNew_Prec_Conv = VariablesVentas.vVal_Prec_Lista;
        System.out.println("VariablesConvenioBTLMF.vNew_Prec_Conv: "+VariablesConvenioBTLMF.vNew_Prec_Conv);
        ////////////////////////////////////////////////////////////////////////////////////////
        log.debug("VariablesConvenio.vVal_Prec_Vta_Local:" + VariablesConvenio.vVal_Prec_Vta_Local);
        log.debug("VariablesConvenio.vVal_Prec_Vta_Conv:" + VariablesConvenio.vVal_Prec_Vta_Conv);

        //}
        
        //INI ASOSA - 06/01/2015 - RIMAC
        if(UtilityInventario.isExistProdRimac(VariablesVentas.vCod_Prod)){   
            DlgIngresoProductoRimac dlgIngresoProductoRimac = new DlgIngresoProductoRimac(myParentFrame, "", true);
            dlgIngresoProductoRimac.setVisible(true);
            /*
            if (!FarmaVariables.vAceptar) {
                VariablesVentas.vCantMesRimac = 0;
                VariablesVentas.vDniRimac = "";
            }
            */
        } 
        //FIN ASOSA - 06/01/2015 - RIMAC
        log.debug("ANTES DE DlgIngresoCantidad ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
        log.debug(""+VariablesVentas.vArrayList_PedidoVenta);
                    
            DlgIngresoCantidad dlgIngresoCantidad = new DlgIngresoCantidad(myParentFrame, "", true);
            VariablesVentas.vIngresaCant_ResumenPed = true;
            dlgIngresoCantidad.setTipoProducto(tipoProducto); //ASOSA - 09/10/2014
            VariablesVentas.tipoLlamada = "1"; //ASOSA - 09/10/2014
            //dlgIngresoCantidad.setActuaRobot(actuaRobot);//LTAVARA 10/11/2016
            dlgIngresoCantidad.setVisible(true);
            if (FarmaVariables.vAceptar) {
                //INI ASOSA - 27/03/2017 - PACKVARIEDAD
                if (dlgIngresoCantidad.getIndTipoVenta().equals(ConstantsVentas.IND_PROD_NORMAL)) {
                    if(dlgIngresoCantidad.isVAccionAcumula()){
                        log.debug("ANTES DE BORRAR ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
                        log.debug(""+VariablesVentas.vArrayList_PedidoVenta);
                        borraProducto(vFila);
                        log.debug("DESPUES DE BORRAR ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
                        log.debug(""+VariablesVentas.vArrayList_PedidoVenta);
                        UtilityCalculoPrecio.clearDetalleVenta();
                        UtilityCalculoPrecio.uploadDetalleVenta(VariablesVentas.vArrayList_PedidoVenta);
                        log.debug("DESPUES DE uploadDetalleVenta ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
                        log.debug(""+VariablesVentas.vArrayList_PedidoVenta);
                        //pCodProdOrigen
                        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                           BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
                            log.debug("bean "+bean.getVCodProd()+ " "+bean.isPIndCampAcumula() + " "+
                                      bean.getPCodProdRegalo());
                            if(bean.getPCodProdRegalo().trim().equalsIgnoreCase(pCodProdOrigen)){
                                UtilityCalculoPrecio.deleteBeannPosDetalleVenta(i);
                                int filaActual = -1;
                                for(int j=0;j<VariablesVentas.vArrayList_PedidoVenta.size();j++){
                                    if(bean.getVCodProd().equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta,
                                                                                                               j,
                                                                                                               0)))
                                        filaActual = j;
                                }
                                VariablesVentas.vArrayList_PedidoVenta.remove(filaActual);
                            }
                                
                        }
                      // selecciona lo que acepta y lo del regalo por si acepta
                         if(dlgIngresoCantidad.getVCtdNormal()>0){
                            UtilityVentas.agregaProductoAcumula(this, tblProductos, null,false,
                                                                dlgIngresoCantidad.getVCtdNormal(),
                                                                dlgIngresoCantidad.getVCodCampAcumula(),
                                                                dlgIngresoCantidad.getVCodEQCampAcumula());
                        }
                        if(dlgIngresoCantidad.getVCtdBono()>0){
                            UtilityVentas.agregaProductoAcumula(this, tblProductos, null,true,
                                                                dlgIngresoCantidad.getVCtdBono(),
                                                                dlgIngresoCantidad.getVCodCampAcumula(),
                                                                dlgIngresoCantidad.getVCodEQCampAcumula());
                        }
                        UtilityCalculoPrecio.clearDetalleVenta();
                        UtilityCalculoPrecio.uploadDetalleVenta(VariablesVentas.vArrayList_PedidoVenta);                        
                        neoOperaResumenPedido();
                        //FarmaUtility.showMessage(this, "El Producto NOOOO", tblProductos);
                    }
                    else{
                        //UtilityVentas.seleccionaProducto(this, tblProductos, null);
                        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                           BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
                            log.debug("bean "+bean.getVCodProd()+ " "+bean.isPIndCampAcumula() + " "+
                                      bean.getPCodProdRegalo());
                            if(bean.getPCodProdRegalo().trim().equalsIgnoreCase(pCodProdOrigen)){
                                UtilityCalculoPrecio.deleteBeannPosDetalleVenta(i);
                                int filaActual = -1;
                                for(int j=0;j<VariablesVentas.vArrayList_PedidoVenta.size();j++){
                                    if(bean.getVCodProd().equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta,
                                                                                                               j,
                                                                                                               0)))
                                        filaActual = j;
                                }
                                VariablesVentas.vArrayList_PedidoVenta.remove(filaActual);
                            }
                                
                        }
                        seleccionaProducto(vFila);
                    }
                    
                    
                    FarmaVariables.vAceptar = false;
                }else{
                    neoOperaResumenPedido();
                }
                //FIN ASOSA - 27/03/2017 - PACKVARIEDAD
                VariablesVentas.vIndDireccionarResumenPed = true;
            } else
                VariablesVentas.vIndDireccionarResumenPed = false;
            
        
        VariablesVentas.vValorMultiplicacion = "1"; //ASOSA - 12/08/2014
    }

    /**
     * Muestra el Detalle de Promocion para su modificacion
     * @author : dubilluz
     * @since  : 25.06.2007
     */
    private void muestraDetallePromocion(int row) {
        VariablesVentas.vCodProm = FarmaUtility.getValueFieldJTable(tblProductos, row, 0);
        VariablesVentas.vDesc_Prom = FarmaUtility.getValueFieldJTable(tblProductos, row, 1);
        VariablesVentas.vCantidad = FarmaUtility.getValueFieldJTable(tblProductos, row, 4);
        VariablesVentas.accionModificar = true;
        log.debug("****Codigo de Prom _antes del detalle : " + VariablesVentas.vCodProm);
        DlgDetallePromocion dlgdetalleprom = new DlgDetallePromocion(myParentFrame, "", true);
        dlgdetalleprom.setVisible(true);
        //Se debe colocar false tanto la accion y to_do
        //if(FarmaVariables.vAceptar){
        FarmaVariables.vAceptar = false;
        VariablesVentas.accionModificar = false;
        VariablesVentas.vCodProm = "";
        VariablesVentas.vDesc_Prom = "";
        VariablesVentas.vCantidad = "";
        //}
        log.debug("Accion de Moficar" + VariablesVentas.accionModificar);
        log.debug("Cantidad de Promocion" + VariablesVentas.vCantidad);

        //operaResumenPedido(); REEMPLAZADO POR EL DE ABAJO
        neoOperaResumenPedido(); //nuevo metodo jcallo 10.03.2009

    }
    
    private boolean isPackVariedad() {
        boolean flag = true;
        String codProm = FarmaUtility.getValueFieldJTable(tblProductos, tblProductos.getSelectedRow(), 0);
        flag = UtilityVentas.determinarSiEsPackVariedad(codProm);
        return flag;
    }

    private void seleccionaProducto(int pFila) {
        //    VariablesVentas.vTotalPrecVtaProd = (Double.parseDouble(VariablesVentas.vCant_Ingresada) * Double.parseDouble(VariablesVentas.vVal_Prec_Vta));
        VariablesVentas.vTotalPrecVtaProd = (FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada) * FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta));
        String indicadorControlStock = FarmaUtility.getValueFieldJTable(tblProductos, pFila, 16);
        String secRespaldo = ""; //ASOSA, 02.07.2010
        secRespaldo =
                (String)((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(pFila)).get(26); //ASOSA, 02.07.2010
        int cantIngresada = FarmaUtility.trunc(FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada));
        int cantIngresada_old =
            FarmaUtility.trunc(FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada_Temp));
        VariablesVentas.vVal_Frac = FarmaUtility.getValueFieldJTable(tblProductos, pFila, COL_RES_VAL_FRAC);
        log.info("ANTES_RES_VariablesVentas.secRespStk:_" + VariablesVentas.secRespStk);
        if (indicadorControlStock.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            if (cantIngresada_old > cantIngresada) {
                VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                if ( /*!UtilityVentas.actualizaStkComprometidoProd(VariablesVentas.vCod_Prod,   //antes - ASOSA, 02.07.2010
                                                     (cantIngresada_old-cantIngresada),
                                                     ConstantsVentas.INDICADOR_D,
                                                     ConstantsPtoVenta.TIP_OPERACION_RESPALDO_ACTUALIZAR,
                                                     cantIngresada,
                                                     true,
                                                     this,
                                                     tblProductos))*/
                    !UtilityVentas.operaStkCompProdResp(VariablesVentas.vCod_Prod,
                        //ASOSA, 02.07.2010
                        cantIngresada, ConstantsVentas.INDICADOR_D,
                        ConstantsPtoVenta.TIP_OPERACION_RESPALDO_ACTUALIZAR, 0, true, this, tblProductos, secRespaldo))
                    return;
            } else if (cantIngresada_old < cantIngresada) {
                if (FarmaUtility.trunc(FarmaUtility.getDecimalNumber(VariablesVentas.vStk_Prod)) == 0 &&
                    !(VariablesVentas.vEsPedidoDelivery && UtilityVentas.getIndVtaNegativa())) {
                    if (!VariablesVentas.tipoProducto.equals(ConstantsVentas.TIPO_PROD_FINAL)) { //ASOSA - 09/10/2014
                        FarmaUtility.showMessage(this, "No existe Stock disponible. Verifique!!!", tblProductos);
                    }
                } else {
                    VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                    if ( /*!UtilityVentas.actualizaStkComprometidoProd(VariablesVentas.vCod_Prod,  //antes - ASOSA, 02.07.2010
                                                       (cantIngresada-cantIngresada_old),
                                                       ConstantsVentas.INDICADOR_A,
                                                       ConstantsPtoVenta.TIP_OPERACION_RESPALDO_ACTUALIZAR,
                                                       cantIngresada,
                                                       true,
                                                       this,
                                                       tblProductos))*/
                        !UtilityVentas.operaStkCompProdResp(VariablesVentas.vCod_Prod,
                            //ASOSA, 02.07.2010
                            cantIngresada, ConstantsVentas.INDICADOR_A,
                            ConstantsPtoVenta.TIP_OPERACION_RESPALDO_ACTUALIZAR, 0, true, this, tblProductos,
                            secRespaldo))
                        return;
                }
            }
        }
        //liberando registros
        FarmaUtility.liberarTransaccion();
        log.info("Desp_RES_VariablesVentas.secRespStk:_" + VariablesVentas.secRespStk);
        
        UtilityVentas.calculaPrecioVenta();

        log.debug("VariablesVentas.vArrayList_ResumenPedido 0 ");
        //ESTO PONE DATOS EN EL JTABLE, cosa que estaria de mas
        operaProductoEnJTable(pFila);
        log.debug("VariablesVentas.vArrayList_ResumenPedido 1 " );
        //ESTO DE AQUI CAMBIO LOS DATOS EN EL ARRAYLIST DE RESUMEN PEDIDO
        operaProductoEnArrayList(pFila);
        log.debug("VariablesVentas.vArrayList_ResumenPedido 2 " );
        //aqui calculato totales pedido SE COMENTO YA QUE NO REFLEJABA LOS CAMBIOS EN EL JTABLE
        //calculaTotalesPedido();se reemplazo con lo siguiente que tiene el reflejar los cambios en el jtable
        neoOperaResumenPedido();
        //seleccionar el producto que se selecciono
        FarmaGridUtils.showCell(tblProductos, pFila, 0);

        log.debug("VariablesVentas.vArrayList_ResumenPedido 3 " );
    }

    private void operaProductoEnJTable(int pFila) {
        //tblProductos.setValueAt(VariablesVentas.vVal_Prec_Lista, pFila, 3);//precio de lista
        tblProductos.setValueAt(VariablesVentas.vCant_Ingresada, pFila, 4); //cantidad ingresada
        //JCORTEZ 17.04.08
        //tblProductos.setValueAt(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vPorc_Dcto_1),2), pFila, 5);//PORC DCTO 1

        //tblProductos.setValueAt(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta),3), pFila, 6);//PRECIO DE VENTA
        //log.debug(" FarmaUtility.formatNumber(VariablesVentas.vTotalPrecVtaProd,2) " + FarmaUtility.formatNumber(VariablesVentas.vTotalPrecVtaProd,2));
        //tblProductos.setValueAt(FarmaUtility.formatNumber(VariablesVentas.vTotalPrecVtaProd,2), pFila, 7);//Total Precio Vta
        //String valIgv = FarmaUtility.formatNumber((FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta) - (FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta) / ( 1 + (FarmaUtility.getDecimalNumber(VariablesVentas.vPorc_Igv_Prod) / 100)))) * FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada));
        //log.debug(valIgv);
        //VariablesVentas.vVal_Igv_Prod = valIgv;
        //tblProductos.setValueAt(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Igv_Prod),2), pFila, 12);//Total Igv Producto
        tblProductos.setValueAt(VariablesVentas.vNumeroARecargar, pFila, 13); //Numero de Recarga

        tblProductos.setValueAt(VariablesVentas.vCantxDia, pFila, COL_RES_CANT_XDIA);
        tblProductos.setValueAt(VariablesVentas.vCantxDias, pFila, COL_RES_CANT_DIAS);

        tblProductos.repaint();
    }

    private void operaProductoEnArrayList(int pFila) {

        //INI ASOSA - 12/08/2014
        VariablesVentas.vCant_Ingresada = "" + Integer.parseInt(VariablesVentas.vCant_Ingresada) * Integer.parseInt(VariablesVentas.vValorMultiplicacion);
        VariablesVentas.vVal_Prec_Vta ="" + FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta) / Integer.parseInt(VariablesVentas.vValorMultiplicacion);
        //FIN ASOSA - 12/08/2014
        BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(pFila);
        bean.setVCtdVta(VariablesVentas.vCant_Ingresada);
        bean.setVPctDcto(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vPorc_Dcto_1),2));
        bean.setVPrecioBase(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta),3));
        bean.setVSubTotal(FarmaUtility.formatNumber(VariablesVentas.vTotalPrecVtaProd,2));
        bean.setVValIgv(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Igv_Prod),2));
        bean.setVNumTelefonoRecarga(VariablesVentas.vNumeroARecargar);
        bean.setVCtdxDia(VariablesVentas.vCantxDia);
        bean.setVCtdDias(VariablesVentas.vCantxDias);
        
        
        // actualiza impuesto bolsa
        // dubilluz 20190802
        if(bean.getVIcbperVal().trim().length()>0){
            double vValorImpuestoUnitario = 0.0;
            double vTotalImpuestoBolsa = 0.0;
            vValorImpuestoUnitario = Double.parseDouble(bean.getVIcbperVal().trim());    
            vTotalImpuestoBolsa = vValorImpuestoUnitario * Integer.parseInt(VariablesVentas.vCant_Ingresada);
            
            bean.setVIcbperTotal(FarmaUtility.formatNumber(vTotalImpuestoBolsa).trim().replace(",", ""));
        }
        // actualiza impuesto bolsa
        // dubilluz 20190802
        
        //JCHAVEZ 29102009 inicio
        try {
            if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
            }
        } catch (SQLException ex) {
            log.error("", ex);
        }
        if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
            bean.setVPrecioVta(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta),
                                                                                                           3));
            bean.setVSubTotal(FarmaUtility.formatNumber(VariablesVentas.vTotalPrecVtaProd,3));
        }
        
        //   //
        bean.setVCant_Ingresada_ItemQuote(VariablesVentas.vCant_Ingresada_ItemQuote);
       // FarmaUtility.showMessage(new JDialog(), "FarmaUtility.getValueFieldArrayList(vLista,i,31) - "+ VariablesVentas.vCant_Ingresada_ItemQuote,null);
        UtilityCalculoPrecio.reloadVenta();
        
        //JCHAVEZ 29102009 fin
        log.info("Registro modificado: ");
    }

    /**
     * elimina elemento seleccionado
     * @author : dubilluz
     * @since  : 19.06.2007
     */
    private void eliminaItemResumenPedido() {
        txtDescProdOculto.setText("");
        int filaActual = tblProductos.getSelectedRow();
        if (filaActual >= 0) {
            String indicadorPromocion = FarmaUtility.getValueFieldJTable(tblProductos, filaActual, COL_RES_IND_PACK);
            log.debug("INDICADOR PROMOCION: " + indicadorPromocion);

            if (indicadorPromocion.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                eliminaPromocion(filaActual);
                log.debug("ELIMINAR PROMOCION");
            } else {
                eliminaProducto(filaActual);
                log.debug("ELIMINAR PRODUCTO");
            }
            /****************************************************************************************************/
            /*if(indicadorPromocion.equalsIgnoreCase("N")){
                    eliminaProducto(filaActual);
                    log.debug("ELIMINAR PRODUCTO");
                }else{
                    eliminaPromocion(filaActual);
                    log.debug("ELIMINAR PROMOCION");
                }*/
            /***************************************************************************************************/
        } else {
            FarmaUtility.showMessage(this, "Debe seleccionar un Producto", tblProductos);
        }
    }

    /**
     * Elimina  producto seleccionado
     * @author :dubilluz
     * @since  :19.06.2007
     */
    void eliminaProducto(int filaActual) {

        if (JConfirmDialog.rptaConfirmDialog(this, "Seguro de eliminar el Producto del Pedido?")) {
            borraProducto(filaActual);
        }
    }

    /**
     * Elimina la promocion y su detalle del Pedido
     * @author : dubilluz
     * @since  : 19.06.2007
     */
    void eliminaPromocion(int filaActual) {
        if (JConfirmDialog.rptaConfirmDialog(this, "Seguro de eliminar la Promocion del Pedido?")) {
            String codProm = ((String)(tblProductos.getValueAt(filaActual, 0))).trim();
            String codProd = "";
            String cantidad = ""; //((String)(tblProductos.getValueAt(filaActual,4))).trim();
            String indControlStk = ""; // ((String)(tblProductos.getValueAt(filaActual,16))).trim();
            ArrayList aux = new ArrayList();

            ArrayList prod_Prom = new ArrayList();
            log.debug("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " +
                      VariablesVentas.vArrayList_Prod_Promociones);
            log.debug("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB: " + VariablesVentas.vCodProm);
            prod_Prom = detalle_Prom(VariablesVentas.vArrayList_Prod_Promociones, codProm);
            log.debug("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC: " + prod_Prom);
            Boolean valor = new Boolean(true);
            ArrayList agrupado = new ArrayList();
            ArrayList atemp = new ArrayList();
            for (int i = 0; i < prod_Prom.size(); i++) {
                log.debug("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
                atemp = (ArrayList)(prod_Prom.get(i));
                log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5555 ATEMP: " + atemp);
                FarmaUtility.operaListaProd(agrupado, (ArrayList)(atemp.clone()), valor, 0);
            }
            log.debug("AAA: -> " + agrupado);
            //agrupado=agrupar(agrupado);
            log.debug(">>>>**Agrupado " + agrupado.size());
            String secRespaldo = ""; //ASOSA, 08.07.2010
            for (int i = 0; i < agrupado.size(); i++) //VariablesVentas.vArrayList_Prod_Promociones.size(); i++)
            {
                log.debug("Entro al for");
                aux = (ArrayList)(agrupado.get(i)); //VariablesVentas.vArrayList_Prod_Promociones.get(i));
                //if((((String)(aux.get(18))).trim()).equalsIgnoreCase(codProm)){
                log.debug("Entro");
                codProd = ((String)(aux.get(0))).trim();
                VariablesVentas.vVal_Frac = ((String)(aux.get(10))).trim();
                cantidad = ((String)(aux.get(4))).trim();
                indControlStk = ((String)(aux.get(16))).trim();
                secRespaldo = ((String)(aux.get(24))).trim(); //ASOSA, 08.07.2010
                log.debug(indControlStk);
                VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                    /*!UtilityVentas.actualizaStkComprometidoProd(codProd,
                                                       Integer.parseInt(cantidad),
                                                       ConstantsVentas.INDICADOR_D,
                                                       ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR,
                                                       Integer.parseInt(cantidad),
                                                       false,
                                                       this,
                                                       tblProductos))*/
                    !UtilityVentas.operaStkCompProdResp(codProd,
                        //ASOSA, 08.07.2010
                        0, ConstantsVentas.INDICADOR_D, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR, 0, false,
                        this, tblProductos, secRespaldo))
                    return;
                //}
            }
            FarmaUtility.aceptarTransaccion();
            removeItemArray(VariablesVentas.vArrayList_Promociones, codProm, 0);
            removeItemArray(VariablesVentas.vArrayList_Prod_Promociones, codProm, 18);

            log.debug("Resultados despues de Eliminar");
            log.debug("Tama�o de Resumen   :" + UtilityCalculoPrecio.getSizeDetalleVenta()+ "");
            log.debug("Tama�o de Promocion :" + VariablesVentas.vArrayList_Promociones.size() + "");
            log.debug("Tama�o de Prod_Promocion :" + VariablesVentas.vArrayList_Prod_Promociones.size() + "");

            tableModelResumenPedido.deleteRow(filaActual);
            tblProductos.repaint();
            calculaTotalesPedido();
            if (tableModelResumenPedido.getRowCount() > 0) {
                if (filaActual > 0)
                    filaActual--;
                FarmaGridUtils.showCell(tblProductos, filaActual, 0);
            }
        }
    }

    private void obtieneInfoPedido() {

        try {
            fechaPedido = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            (new FacadeRecaudacion()).obtenerTipoCambio();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener Tipo de Cambio del Dia . \n " + sql.getMessage(), null);
        }
    }

    /**
     * VALIDA NRO DE RUC INGRESADO.
     * @author KMONCADA
     * @since 01.04.2015
     * @param nroRuc
     * @return
     */
    private String verificaRucValido(String nroRuc) {
        String resultado = "";
        try {
            resultado = DBCliente.verificaRucValido(nroRuc);
            return resultado;
        } catch (SQLException sql) {
            log.error("", sql);
            return ConstantsCliente.RESULTADO_RUC_INVALIDO;
        }
    }

    /*
     *  Se graba el pedido de venta, segun sea el comprobante
     */
    private synchronized void grabarPedidoVenta(String pTipComp) {
        
        log.info("PEDIDO FIDELIZADO?????");
        log.info("NUMERO DE TARJETA DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vNumTarjeta);
        log.info("NUMERO DE DNI DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vDniCliente);
        if(VariablesPuntos.frmPuntos==null)
            log.info("VariablesPuntos.frmPuntos es null");
        else    
        if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
            log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
        }
        
        
        if (pedidoGenerado) {
            log.debug("El pedido ya fue generado");
            return;
        }

        if (UtilityCalculoPrecio.getSizeDetalleVenta() <= 0 &&
            VariablesVentas.vArrayList_Prod_Promociones.size() <= 0) {
            FarmaUtility.showMessage(this, "No hay Productos Seleccionados. Verifique!!!", tblProductos);
            return;
        }

        //LLEIVA 24-Junio-2013 - Se a�ade la validaci�n para que no cobre los pedidos con precio cero
        if (FarmaUtility.getDecimalNumber(lblTotalS.getText()) <= 0) {
            FarmaUtility.showMessage(this, "El precio del pedido es "+ConstantesUtil.simboloSoles+" 0.00. Verifique!!!", tblProductos);
            return;
        }
        boolean aceptaCupones;
        boolean esConvenioBTLMF = false;

        aceptaCupones = validaCampsMontoNetoPedido(lblTotalS.getText().trim());

        //Agregado por FRAMRIEZ 16.12.2011
        log.debug("------------------------------------");
        log.debug("----Asignando el Importe total------");
        //log.debug("------------------------------------" + totalS);
        VariablesConvenioBTLMF.vImpSubTotal = FarmaUtility.getDecimalNumber(lblTotalS.getText());

        int cantCuponesNoUsado = 0;
        Map mapaCupon = new HashMap();
        boolean flagCampAutomatico;
        VariablesVentas.vList_CuponesNoUsados = new ArrayList();
        VariablesVentas.vList_CuponesUsados = new ArrayList();
        ArrayList vAux_ListaCupones=new ArrayList();
        
        vAux_ListaCupones.addAll(VariablesVentas.vArrayList_Cupones);
        vAux_ListaCupones.addAll(VariablesVentas.vArrayList_CuponesFijos);
        
        //Si existen cupones
        if (vAux_ListaCupones.size() > 0) {
            for (int i = 0; i < vAux_ListaCupones.size(); i++) {
                mapaCupon = new HashMap();
                mapaCupon = (Map)vAux_ListaCupones.get(i);
                //ver si es un cupon de campania automatica.
                flagCampAutomatico =
                        (mapaCupon.get("COD_CAMP_CUPON").toString().indexOf("A") != -1 || mapaCupon.get("COD_CAMP_CUPON").toString().indexOf("L") !=
                         -1) ? true : false;

                if (!flagCampAutomatico) {
                    //ver si se uso o no en resumen pedido
                    boolean flagUso = false;
                    for (int k = 0; k < UtilityCalculoPrecio.getSizeDetalleVenta(); k++) {
                        String campUso = UtilityCalculoPrecio.getBeanPosDetalleVenta(k).getVCodCupon();
                        if (mapaCupon.get("COD_CAMP_CUPON").toString().equals(campUso)) {
                            flagUso = true;
                            break;
                        }
                    }
                    if (!flagUso) {
                        VariablesVentas.vList_CuponesNoUsados.add(mapaCupon);
                        cantCuponesNoUsado++;
                    } else {
                        VariablesVentas.vList_CuponesUsados.add(mapaCupon);
                    }
                }
            }
            
            if (cantCuponesNoUsado > 0) {
                DlgCupones dlgCupones = new DlgCupones(myParentFrame, "Cupones No Usados", true);
                dlgCupones.setVisible(true);

                if (FarmaVariables.vAceptar) {
                    FarmaVariables.vAceptar = false;
                    aceptaCupones = true;
                } else {
                    aceptaCupones = false;
                }
            }
        }

        if (aceptaCupones) {
            // Valida si el monto del pedido es menor de la suma de los cupones que
            // ingreso, Retorna TRUE si se generara el pedido.
            //if(validaUsoCampanaMonto(lblTotalS.getText().trim()))
            //{

            boolean isAuxiliar = false;
            if (pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_GUIA)) {
                isAuxiliar = true;
                pTipComp = ConstantsVentas.TIPO_COMP_BOLETA;
            }
            try {

                //Se obtiene tipo de comprobante de la relacion maquina - impresora
                if (pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET) ||
                    pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_BOLETA) ||
                    pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA) ||
                    pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT)) {
                    try {
                        VariablesVentas.vTip_Comp_Ped = DBCaja.getObtieneTipoCompPorIP(FarmaVariables.vIpPc, pTipComp);
                    } catch (SQLException sqle) {
                        log.error("", sqle);
                        VariablesVentas.vTip_Comp_Ped = "N";
                    }

                    if (VariablesVentas.vTip_Comp_Ped.trim().equalsIgnoreCase("N")) {
                        if (pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET) ||
                            pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_BOLETA))
                            FarmaUtility.showMessage(this,
                                                     "La IP no cuenta con una impresora asignada de ticket o boleta. Verifique!!!",
                                                     tblProductos);
                        else
                            FarmaUtility.showMessage(this,
                                                     "La IP no cuenta con una impresora asignada de Factura o Ticket Factura. Verifique!!!",
                                                     tblProductos);

                        return;
                    }
                }
                //else if ((pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA)) ||
                //         (pTipComp.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT)))
                //{
                //    VariablesVentas.vTip_Comp_Ped = pTipComp.trim();
                //}

                /*double vValorMaximoCompra = Double.parseDouble(DBVentas.getMontoMinimoDatosCliente());
                double dTotalNeto = FarmaUtility.getDecimalNumber(lblTotalS.getText().trim());
                VariablesVentas.vIndObligaDatosCliente = false;

                if (dTotalNeto >= vValorMaximoCompra) {
                    VariablesVentas.vIndObligaDatosCliente = true;
                }*/
                // KMONCADA 15.04.2016
                if(!DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){//JMONZALVE 24042019
                    VariablesVentas.vIndObligaDatosCliente = UtilityVentas.isObligaDatosCliente(lblTotalS.getText().trim(), false);
                }else{//JMONZALVE 24042019
                    VariablesVentas.vIndObligaDatosCliente = false;//JMONZALVE 24042019
                }//JMONZALVE 24042019

                if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                    VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
                    esConvenioBTLMF = true;
                    if (isAuxiliar)
                        VariablesVentas.vIndObligaDatosCliente = false;
                }

                if (FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL) || (

                    FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_TRADICIONAL))
                    /*&&
                         (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA) ||
                          VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT)*/
                    )
                // ||

                //VariablesVentas.vIndObligaDatosCliente
                {
                    if (VariablesVentas.vIndObligaDatosCliente ||
                        (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA) ||
                         VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT))) {
                        // KMONCADA 01.04.2015 VALIDA DATOS INGRESADOS ANTERIORMENTE
                        if(ConstantsCliente.RESULTADO_RUC_INVALIDO.equalsIgnoreCase(verificaRucValido(VariablesVentas.vRuc_Cli_Ped)) &&
                            (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA) ||
                             VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT)
                            )
                        ){
                            log.info("RUC INVALIDO");
                            VariablesVentas.vRuc_Cli_Ped = "";
                            VariablesVentas.vNom_Cli_Ped = "";
                        }
                        // Pedir datos si pase del monto indicado
                        if (VariablesVentas.vRuc_Cli_Ped.trim().length() == 0 ||
                            VariablesVentas.vNom_Cli_Ped.trim().length() == 0) {
                            muestraSeleccionComprobante(esConvenioBTLMF);
                        } else {
                            VariablesVentas.vRuc_Cli_Ped = VariablesVentas.vRuc_Cli_Ped.trim();
                            VariablesVentas.vNom_Cli_Ped = VariablesVentas.vNom_Cli_Ped.trim();
                            FarmaVariables.vAceptar = true;
                        }

                        if (VariablesVentas.vRuc_Cli_Ped.trim().length() == 0 &&
                            VariablesVentas.vNom_Cli_Ped.trim().length() == 0) {
                            FarmaUtility.liberarTransaccion();
                            FarmaUtility.showMessage(this,
                                                     "Por favor ingrese Nombre y Dni del cliente para esta venta.\n" +
                                    "Gracias.", tblProductos);
                            return;
                        }

                    } else
                        FarmaVariables.vAceptar = true;

                    //Se valida si el RUC es valido para cobrar/generar el pedido si tiene descuentos en un fidelizado
                    //LLEIVA 28-Ene-2014 Se a�adio la opcion de ticket factura
                    if ((VariablesVentas.vTip_Comp_Ped.trim().equalsIgnoreCase(ConstantsPtoVenta.TIP_COMP_FACTURA) ||
                         VariablesVentas.vTip_Comp_Ped.trim().equalsIgnoreCase(ConstantsPtoVenta.TIP_COMP_TICKET_FACT)) &&
                        VariablesFidelizacion.vAhorroDNI_Pedido > 0) {
                        if (!UtilityFidelizacion.isRUCValido(VariablesVentas.vRuc_Cli_Ped.trim())) {
                            FarmaUtility.showMessage(this, "Los descuentos son para venta con\n" +
                                    "boleta de venta y para consumo\n" +
                                    "personal. El RUC ingresado queda\n" +
                                    "fuera de la promoci�n de descuento.", tblProductos);
                            return;
                        }
                    }

                    if (!FarmaVariables.vAceptar) {
                        FarmaUtility.liberarTransaccion();
                        return;
                    } else {
                        if (pedidoGenerado) {
                            FarmaUtility.liberarTransaccion();
                            return;
                        }

                        if (!FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL)) {
                            if (!JConfirmDialog.rptaConfirmDialog(this, "Esta seguro de realizar el pedido?")) {
                                FarmaUtility.liberarTransaccion();
                                return;
                            }
                        }
                    }
                }
                if (!cargaLogin_verifica()) {
                    FarmaVariables.vAceptar = false;
                    FarmaUtility.liberarTransaccion();
                    return;
                }

                VariablesVentas.vNum_Ped_Vta = FarmaSearch.getNuSecNumeracion(FarmaConstants.COD_NUMERA_PEDIDO, 10);
                VariablesVentas.vNum_Ped_Diario = obtieneNumeroPedidoDiario();

                if (VariablesVentas.vNum_Ped_Vta.trim().length() == 0 ||
                    VariablesVentas.vNum_Ped_Diario.trim().length() == 0)
                    throw new SQLException("Error al obtener Numero de Pedido", "Error", 9001);
                //coloca valores de variables de cabecera de pedido
                guardaVariablesPedidoCabecera();
                DBVentas.grabarCabeceraPedido(pIngresoComprobanteManual, VariablesCaja.pManualTipCompPago,
                                              VariablesCaja.pManualSerieComprobante, VariablesCaja.pManualNumCompPago);

                int cont = 0;

                for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                    cont++;
                    grabarDetalle_02(pTipComp, UtilityCalculoPrecio.getBeanPosDetalleVenta(i), cont, ConstantsVentas.IND_PROD_SIMPLE);
                }

                ArrayList array = new ArrayList();
                
                for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
                    cont++;
                    array = ((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i));
                    grabarDetalle_02(pTipComp, array, cont, ConstantsVentas.IND_PROD_PROM);
                }

                for (int i = 0; i < VariablesVentas.vArrayList_Promociones.size(); i++) {
                    array = ((ArrayList)VariablesVentas.vArrayList_Promociones.get(i));
                    int cantidad =
                        Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).get(4));
                    String cod_prom = (String)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).get(0);

                    try {
                        DBVentas.grabaPromXPedidoNoAutomaticos(cod_prom, cantidad);
                    } catch (SQLException e) {
                        log.error("", e);
                    }
                }
                
                //INI JMONZALVE 24042019
                //Grabar Cobro por Responsabilidad 
                for(int i = 0; i < ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad.size(); i++){
                    String codTrabajador = (String)((ArrayList)ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad.get(i)).get(0);
                    String datTrabajador = (String)((ArrayList)ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad.get(i)).get(1);
                    String codMotivo = (String)((ArrayList)ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad.get(i)).get(2);
                    String motRegularizacion = (String)((ArrayList)ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad.get(i)).get(3);
                    String importeTrabajador = (String)((ArrayList)ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad.get(i)).get(4);
                    DBConv_Responsabilidad.guardarListCobroResponsabilidad(codTrabajador, datTrabajador, codMotivo, motRegularizacion, importeTrabajador);
                }
                //FIN JMONZALVE 24042019

                //Se procedera a ver si se puede o no acceder a un producto de regalo por el encarte.
                cont++;
                VariablesVentas.vIndVolverListaProductos = false;
                if (!procesoProductoRegalo(VariablesVentas.vNum_Ped_Vta, cont)) {
                    FarmaUtility.liberarTransaccion();
                    VariablesVentas.vIndVolverListaProductos = true;
                    return;
                }

                FarmaSearch.setNuSecNumeracionNoCommit(FarmaConstants.COD_NUMERA_PEDIDO);
                FarmaSearch.setNuSecNumeracionNoCommit(FarmaConstants.COD_NUMERA_PEDIDO_DIARIO);

                //***********************************CONVENIO BTLMF*****************************************/
                //***********************************INICIO*************************************************/

                /**NUEVO
                     * @Fecha: 14-12-2011
                     * @Autor: FRAMIREZ
                     **/
                if (esConvenioBTLMF) {
                    if (VariablesConvenioBTLMF.vDatosConvenio != null) {
                        log.debug("grabando los datos del convenio BTLMF y el Pedido");

                        for (int j = 0; j < VariablesConvenioBTLMF.vDatosConvenio.size(); j++) {
                            Map convenio = (Map)VariablesConvenioBTLMF.vDatosConvenio.get(j);

                            String pCodCampo = (String)convenio.get(ConstantsConvenioBTLMF.COL_CODIGO_CAMPO);
                            String pDesCampo = (String)convenio.get(ConstantsConvenioBTLMF.COL_VALOR_IN);
                            String pNombCampo = (String)convenio.get(ConstantsConvenioBTLMF.COL_NOMBRE_CAMPO);
                            String pCodValorCampo = (String)convenio.get(ConstantsConvenioBTLMF.COL_COD_VALOR_IN);

                            grabarPedidoConvenioBTLMF(pCodCampo, pDesCampo, pNombCampo, pCodValorCampo);
                        }
                    }
                } else {
                    //***********************************CONVENIO*****************************************/
                    //***********************************INICIO*******************************************/

                    if (!VariablesConvenio.vCodConvenio.equalsIgnoreCase("")) {
                        String vCodCli = "" + VariablesConvenio.vArrayList_DatosConvenio.get(1);
                        String vApePat = "" + VariablesConvenio.vArrayList_DatosConvenio.get(3);
                        String vApeMat = "" + VariablesConvenio.vArrayList_DatosConvenio.get(4);
                        String vNumDoc = "" + VariablesConvenio.vArrayList_DatosConvenio.get(5);
                        String vTelefono = "" + VariablesConvenio.vArrayList_DatosConvenio.get(6);
                        String vCodInterno = "" + VariablesConvenio.vArrayList_DatosConvenio.get(7);
                        String vTrabajador = "" + VariablesConvenio.vArrayList_DatosConvenio.get(8);
                        String vCodTrabEmpresa = "" + VariablesConvenio.vArrayList_DatosConvenio.get(9);
                        String vCodCliDep = VariablesConvenio.vCodClienteDependiente;
                        String vCodTrabEmpDep = VariablesConvenio.vCodTrabDependiente;

                        grabarPedidoConvenio(vCodCli, vNumDoc, vCodTrabEmpresa, vApePat, vApeMat, "", "", vTelefono,
                                             "", "", vCodInterno, vTrabajador, vCodCliDep, vCodTrabEmpDep);

                        double totalS = FarmaUtility.getDecimalNumber(lblTotalS.getText());

                        if (FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0) {
                            VariablesConvenio.vCredito = DBConvenio.obtieneCredito(VariablesConvenio.vCodConvenio, vCodCli,
                                                              FarmaConstants.INDICADOR_S);
                            VariablesConvenio.vCreditoUtil = DBConvenio.obtieneCreditoUtil(VariablesConvenio.vCodConvenio, vCodCli,
                                                                  FarmaConstants.INDICADOR_S);
                        }
                        if (FarmaUtility.getDecimalNumber(VariablesConvenio.vValCoPago) != 0 &&
                            !VariablesConvenio.vCredito.equalsIgnoreCase(VariablesConvenio.vCreditoUtil)) {
                            String vFormaPago = DBConvenio.obtieneFormaPagoXConvenio(VariablesConvenio.vCodConvenio);
                            grabarFormaPagoPedido(vFormaPago, VariablesVentas.vNum_Ped_Vta, VariablesConvenio.vValCoPago, FarmaVariables.vCodMoneda,
                                                  FarmaUtility.formatNumber(FarmaVariables.vTipCambio), "0", VariablesConvenio.vValCoPago, "", "", "", "0");
                        }
                    }
                }
                //*************************************FIN********************************************/
                //***********************************CONVENIO*****************************************/
                //Se graban los cupones validos.
                String cadena, vCodCamp, vIndUso, vIndFid;
                boolean vCampAuto;
                Map mapaCuponAux = new HashMap();
                for (int i = 0; i < VariablesVentas.vList_CuponesUsados.size(); i++) {
                    mapaCuponAux = (Map)VariablesVentas.vList_CuponesUsados.get(i);
                    cadena = mapaCuponAux.get("COD_CUPON").toString();
                    vCodCamp = mapaCuponAux.get("COD_CAMP_CUPON").toString();
                    vIndFid = mapaCuponAux.get("IND_FID").toString();
                    vIndUso = FarmaConstants.INDICADOR_S;
                    vCampAuto = (vCodCamp.indexOf("A") != -1) ? true : false;

                    if (!vCampAuto) { //si no es campania automatica graba cupon
                        DBVentas.grabaPedidoCupon(VariablesVentas.vNum_Ped_Vta, cadena, vCodCamp, vIndUso);
                    }
                }
                
                
                for (int i = 0; i < VariablesVentas.vList_CuponesNoUsados.size(); i++) {
                    mapaCuponAux = (Map)VariablesVentas.vList_CuponesNoUsados.get(i);
                    cadena = mapaCuponAux.get("COD_CUPON").toString();
                    vCodCamp = mapaCuponAux.get("COD_CAMP_CUPON").toString();
                    vIndFid = mapaCuponAux.get("IND_FID").toString();
                    vIndUso = FarmaConstants.INDICADOR_N;
                    vCampAuto = (vCodCamp.indexOf("A") != -1) ? true : false;

                    if (!vCampAuto) { //si no es campania automatica graba cupon
                        DBVentas.grabaPedidoCupon(VariablesVentas.vNum_Ped_Vta, cadena, vCodCamp, vIndUso);
                    }
                }

                //Proceso de campa�as Acumuladas
                //Solo se generara el historico y canje si no hay producto recarga
                if (!VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S) ||
                    !VariablesVentas.vIndPedConProdVirtual) {

                    //Proceso de campa�as Automaticas.
                    if (!esConvenioBTLMF) {
                        //KMONCADA 13.08.2015 SE VALIDARA ANTES DE APLICAR LA CAMPA�A AUTOMATICA SI SE PERMITE SEGUN TAB GRAL
                        if(DBVentas.getAplicaCampa�asAutomaticas()){
                            procesoPack(VariablesVentas.vNum_Ped_Vta);
                        }

                        //Campa�as acumuladas
                        procesoCampa�asAcumuladas(VariablesVentas.vNum_Ped_Vta, VariablesFidelizacion.vDniCliente);

                        procesoCampa�as(VariablesVentas.vNum_Ped_Vta);
/*
**************************************************************************
-- RARGUMEDO 22/07/2017 -- Autorizado por DUBILLUZ.
--  Este Procedimiento deja de utilizarce porque el cupon de descuento monto, 
-- deja de ser una forma de pago, se aplica el descuento en el pedido.
                        DBVentas.procesaPedidoCupon(VariablesVentas.vNum_Ped_Vta);
*/
                    }
                }
                
                //INI ASOSA - 15/01/2015 - RIMAC
                boolean indRimac = false;
                if (UtilityCaja.get_ind_ped_con_rimac(VariablesVentas.vNum_Ped_Vta)) {
                    indRimac = UtilityCaja.insProdRegaloRimac(VariablesVentas.vNum_Ped_Vta);
                    if (!indRimac) {
                        FarmaUtility.showMessage(this, "Ya se selecciono un producto rimac", txtDescProdOculto);
                        FarmaUtility.liberarTransaccion();
                        return;
                    }
                }
                //FIN ASOSA - 15/01/2015 - RIMAC
                //KMONCADA 20.08.2015 PEDIDO FIDELIZADO
                log.info("PEDIDO FIDELIZADO?????");
                log.info("NUMERO DE TARJETA DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vNumTarjeta);
                log.info("NUMERO DE DNI DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vDniCliente);
                if(VariablesPuntos.frmPuntos==null)
                    log.info("VariablesPuntos.frmPuntos es null");
                else    
                if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
                    log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
                }
                    
                
                
                
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                    BeanTarjeta tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
                    log.info("TARJETA BEAN --> " + tarjetaPuntos);
                    String auxDocumento = tarjetaPuntos.getDni();
                    String auxNroTarjeta = tarjetaPuntos.getNumeroTarjeta();
                    /*if(VariablesFidelizacion.vDniCliente == null || (VariablesFidelizacion.vDniCliente!=null && VariablesFidelizacion.vDniCliente.trim().length()==0)){
                        VariablesFidelizacion.vDniCliente = auxDocumento;
                    }*/
                    VariablesFidelizacion.vDniCliente = auxDocumento;
                    // SI FALTA NRO TARJETA
                    if(VariablesFidelizacion.vNumTarjeta==null || (VariablesFidelizacion.vNumTarjeta!=null && VariablesFidelizacion.vNumTarjeta.trim().length()==0)){
                        if(auxNroTarjeta!=null && auxNroTarjeta.trim().length()>0){
                            if(!auxNroTarjeta.equalsIgnoreCase(auxDocumento)){
                                VariablesFidelizacion.vNumTarjeta = auxNroTarjeta;
                            }else{
                                VariablesFidelizacion.vNumTarjeta = DBPuntos.getObtieneTarjetaNoMonedero(VariablesFidelizacion.vDniCliente, VariablesPuntos.frmPuntos.getBeanTarjeta().getDeslizaTarjeta());
                            }
                        }else{
                            VariablesFidelizacion.vNumTarjeta = DBPuntos.getObtieneTarjetaNoMonedero(VariablesFidelizacion.vDniCliente, VariablesPuntos.frmPuntos.getBeanTarjeta().getDeslizaTarjeta());
                        }
                    }
                }else{
                    log.info("EL BEAN DE TARJETA DE PUNTOS ESTA NUL");
                }
                
                boolean isFidelizado = false;
                try{
                    if ((VariablesFidelizacion.vNumTarjeta!=null && VariablesFidelizacion.vNumTarjeta.trim().length() > 0) && 
                        (VariablesFidelizacion.vDniCliente!=null && VariablesFidelizacion.vDniCliente.trim().length()>0)) {
                        isFidelizado = true;
                    }
                }catch(Exception ex){
                    isFidelizado = false;
                }
                //grabando numero de tarjeta del pedido de cliente fidelizado
                //if (VariablesFidelizacion.vNumTarjeta.length() > 0) {
                log.info("PEDIDO ES FIDELIZADO????? --> "+isFidelizado);
                log.info("NUMERO DE TARJETA DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vNumTarjeta);
                log.info("NUMERO DE DNI DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vDniCliente);
                if(isFidelizado){
                    DBFidelizacion.insertarTarjetaPedido();
                }

                //actualizando los descto aplicados por cada productos en el detalle del pedido
                Map mapaDctoProd = new HashMap();
                for (int mm = 0; mm < VariablesVentas.vListDctoAplicados.size(); mm++) {
                    mapaDctoProd = (Map)VariablesVentas.vListDctoAplicados.get(mm);

                    DBVentas.guardaDctosDetPedVta(mapaDctoProd.get("COD_PROD").toString(),
                                                  mapaDctoProd.get("COD_CAMP_CUPON").toString(),
                                                  mapaDctoProd.get("VALOR_CUPON").toString(),
                                                  mapaDctoProd.get("AHORRO").toString(),
                                                  mapaDctoProd.get("DCTO_AHORRO").toString(),
                                                  mapaDctoProd.get("SEC_PED_VTA_DET").toString()); //JMIRANDA 30.10.09 ENVIA SEC_DET_
                }

                if (VariablesVentas.vSeleccionaMedico) {
                    DBVentas.agrega_medico_vta();
                }
                
                if(VariablesPuntos.frmPuntos==null)
                    log.info("VariablesPuntos.frmPuntos es null");
                else    
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null)
                     log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
                
                log.info("ANTES UtilityProgramaAcumula.pSaveRegaloRechazoPedido");
                
                // dubilluz 2017.04.26
                UtilityProgramaAcumula.pSaveRegaloRechazoPedido(VariablesVentas.vNum_Ped_Vta);
                // dubilluz 2017.04.26
                
                log.info("DESPUES UtilityProgramaAcumula.pSaveRegaloRechazoPedido");
                if(VariablesPuntos.frmPuntos==null)
                    log.info("VariablesPuntos.frmPuntos es null");
                else    
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null)
                     log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
                
                DBVentas.validarValorVentaNeto(VariablesVentas.vNum_Ped_Vta);

                //LLEIVA - 22/Mayo/2013 - Se actualiza, si existe el recetario magistral adjunto al presente pedido
                if (VariablesVentas.vNum_Ped_Vta != "" && VariablesRecetario.strCodigoRecetario != null &&
                    !VariablesRecetario.strCodigoRecetario.equals("")) {
                    DBRecetario.asignarPedidoRM(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                FarmaVariables.vCodLocal, VariablesRecetario.strCodigoRecetario,
                                                VariablesVentas.vNum_Ped_Vta);
                    //facadeRecetario.asignarPedidoRM(VariablesRecetario.strCodigoRecetario,
                    //                                VariablesVentas.vNum_Ped_Vta);
                }
                //FIN LLEIVA

                FarmaUtility.aceptarTransaccion();

                //ERIOS 03.09.2013 Determina registro de productos restringidos
                if (VariablesPtoVenta.vIndRegistroVentaRestringida) {
                    boolean isVtaPsicotropico = UtilityVentas.getVentaRestringida(VariablesVentas.vNum_Ped_Vta);
                    boolean isRegistroFarmaCMP = UtilityVentas.getRegistroProdFarmaCMP(VariablesVentas.vNum_Ped_Vta);
                    if (isVtaPsicotropico || isRegistroFarmaCMP) {
                        if (!UtilityVentas.registroDatosRestringidos(myParentFrame, isVtaPsicotropico)) {
                            pedidoGenerado = false;
                            throw new Exception("No se registraron los datos de venta restringida");
                        }
                    }
                }
                
                //ERIOS 23.08.2016 Registro de Atencion Medica
                boolean isVtaAtendMedica = UtilityVentas.getVentaAtencionMedica(VariablesVentas.vNum_Ped_Vta);
                if(isVtaAtendMedica){
                    if (!UtilityVentas.registroDatosAtencionMedica(myParentFrame)) {
                        pedidoGenerado = false;
                        throw new Exception("No se registraron los datos de Atencion Medica");
                    }
                }
                
                //TOTALIZA LOS AHORROS
                try{
                    DBPuntos.totalizaAhorros(VariablesVentas.vNum_Ped_Vta);
                }catch(Exception ex){
                    log.error("",ex);
                }

                FarmaUtility.aceptarTransaccion();
                
                // PROGRAMA DE PUNTOS 
                //DUBILLUZ 2017.04.24
                UtilityProgramaAcumula.operacion_calculo_puntos(VariablesVentas.vNum_Ped_Vta);
                
                //KMONCADA 18.04.2016 CALCULO DE LA PERCEPCION
                if(UtilityPtoVenta.isLocalAplicaPercepcion()){
                    UtilityVentas.calcularMontoPercepcion(VariablesVentas.vNum_Ped_Vta);
                }
                
                FarmaUtility.aceptarTransaccion();
                
                //INI AOVIEDO 13/09/2017
                //INICIO RPASCACIO 16-05-2017
                if(!VariablesFidelizacion.vDniCliente.isEmpty()){
                    UtilityCaja.verificaTarjetaPedido(VariablesVentas.vNum_Ped_Vta, VariablesFidelizacion.vDniCliente);
                }
                //FIN RPASCACIO 16-05-2017
                //FIN AOVIEDO 13/09/2017
                
                pedidoGenerado = true;
                
                //ERIOS 2.2.8 Carga variables
                if(vInd_Menu.equalsIgnoreCase("S")){
                    UtilityPtoVenta.obtieneInfoLocal();
                }else{
                    frmEconoFar.obtieneInfoLocal();
                }
                

                if (FarmaVariables.vTipCaja.equalsIgnoreCase("") || FarmaVariables.vTipCaja.equalsIgnoreCase(null)) {
                    FarmaVariables.vTipLocal = "M";
                }

                if (FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL)) {
                    VariablesCaja.vNumPedPendiente = VariablesVentas.vNum_Ped_Diario;

                    if (DBPtoVenta.getIndicadorNuevoCobro()) {
                        mostrarNuevoCobro();
                    } else {
                        muestraCobroPedido();
                    }
                    
                    if(!pedidoGenerado){

                            log.info("regresando");
                            log.info(""+tableModelResumenPedidoAux.data);
                            log.info("goggoogogogoog");
                            log.info(""+tableModelResumenPedido.data);
                            
                            /*tableModelResumenPedido.data = (ArrayList)tableModelResumenPedidoAux.data.clone();
                            VariablesVentas.vArrayList_ResumenPedido = (ArrayList)lstAuxResumenPedido.clone();
                            */
                            if(tableModelResumenPedidoAux.getRowCount()>0){
                                tableModelResumenPedido.clearTable();
                                for(int i=0;i<tableModelResumenPedidoAux.getRowCount();i++){
                                    tableModelResumenPedido.insertRow((ArrayList)((ArrayList)tableModelResumenPedidoAux.data.get(i)).clone());
                                }
                                
                                UtilityCalculoPrecio.clearDetalleVenta();
                                UtilityCalculoPrecio.uploadDetalleVenta(lstAuxResumenPedido);
                                tblProductos.repaint();
                                neoOperaResumenPedido();
                            }
                        
                    }

                    if (VariablesFidelizacion.vRecalculaAhorroPedido) {
                        VariablesFidelizacion.vMaximoAhorroDNIxPeriodo =
                                UtilityFidelizacion.getMaximoAhorroDnixPeriodo(VariablesFidelizacion.vDniCliente,
                                                                               VariablesFidelizacion.vNumTarjeta);
                        VariablesFidelizacion.vAhorroDNI_x_Periodo =
                                UtilityFidelizacion.getAhorroDNIxPeriodoActual(VariablesFidelizacion.vDniCliente,
                                                                               VariablesFidelizacion.vNumTarjeta);
                        neoOperaResumenPedido();
                    }
                } else {
                    if (FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_TRADICIONAL))
                        muestraPedidoRapido();
                }

                VariablesVentas.vArrayListCuponesCliente.clear();
                VariablesVentas.dniListCupon = "";

            } catch (SQLException sql) {
                FarmaUtility.liberarTransaccion();
                //log.error("",sql);
                log.error(null, sql);
                if (sql.getErrorCode() == 20066) {
                    FarmaUtility.showMessage(this,
                                             "Error en Base de Datos al Generar Pedido.\n Inconsistencia en los montos calculados",
                                             tblProductos);
                } else {
                    FarmaUtility.showMessage(this, "Error en Base de Datos al grabar el pedido.\n" +
                            sql, tblProductos);
                }
            } catch (Exception exc) {
                FarmaUtility.liberarTransaccion();
                log.error(null, exc);
                FarmaUtility.showMessage(this, "Error en la aplicacion al grabar el pedido.\n" +
                        exc.getMessage(), tblProductos);
            }
        }
        //}
    }

    private void validaConvenio_v3(KeyEvent e, String vCoPagoConvenio) {
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
            VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
            boolean result = true;
            if (VariablesConvenioBTLMF.vFlgValidaLincreBenef != null &&
                VariablesConvenioBTLMF.vFlgValidaLincreBenef.equals("1")) {
                result = existeSaldoCredDispBenif(this);
            }
            if (result)
                validaConvenio_v2(e, VariablesConvenio.vPorcCoPago);
        } else {
            validaConvenio_v2(e, VariablesConvenio.vPorcCoPago);
        }
    }

    private void borraProducto(int filaActual) {
        String codProd = ((String)(tblProductos.getValueAt(filaActual, 0))).trim();
        VariablesVentas.vVal_Frac = FarmaUtility.getValueFieldJTable(tblProductos, filaActual, 10);
        String cantidad = ((String)(tblProductos.getValueAt(filaActual, 4))).trim();
        String indControlStk = ((String)(tblProductos.getValueAt(filaActual, 16))).trim();
        String secRespaldo = ""; //ASOSA, 02.07.2010
        secRespaldo =
                (String)((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(filaActual)).get(26); //ASOSA, 02.07.2010
        VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
        log.info("filaActual:_" + filaActual);
        log.info("VariablesVentas.vArrayList_PedidoVenta:_" + VariablesVentas.vArrayList_PedidoVenta);
        log.info("ANTES_BORRA_VariablesVentas.secRespStk:_" + VariablesVentas.secRespStk);
        log.info("secRespaldo:_" + secRespaldo);
        if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
            /*!UtilityVentas.actualizaStkComprometidoProd(codProd,
                                                   Integer.parseInt(cantidad),
                                                   ConstantsVentas.INDICADOR_D,
                                                   ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR,
                                                   Integer.parseInt(cantidad),
                                                  true,
                                                  this,
                                                  tblProductos))*/
            !UtilityVentas.operaStkCompProdResp(codProd,
                //ASOSA, 02.07.2010
                0, ConstantsVentas.INDICADOR_D, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR, 0, true, this,
                tblProductos, secRespaldo))
            return;
        log.info("DESPUES_BORRA_VariablesVentas.secRespStk:_" + VariablesVentas.secRespStk);
        log.info("secRespaldo:_" + secRespaldo);
        
        String pCodEQCampAcumula = UtilityCalculoPrecio.getBeanPosDetalleVenta(filaActual).getPCodEQCampAcumula();
        String pCodProdAcumula = "N";
        if(pCodEQCampAcumula.trim().length()>0){
            pCodProdAcumula = "A"+pCodEQCampAcumula.substring(8)    ;
        }
        
        VariablesVentas.vArrayList_PedidoVenta.remove(filaActual);
        UtilityCalculoPrecio.deleteBeannPosDetalleVenta(filaActual);
        tableModelResumenPedido.deleteRow(filaActual);
        
        if(!pCodProdAcumula.equalsIgnoreCase("N")){
            for(int i=0;i<VariablesVentas.vArrayList_PedidoVenta.size();i++){
                if(pCodProdAcumula.equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta,
                                                                                        i,0))){
                    VariablesVentas.vArrayList_PedidoVenta.remove(i);
                    break;
                }
            }
            for(int i=0;i<tableModelResumenPedido.data.size();i++){
                if(pCodProdAcumula.equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(tableModelResumenPedido.data,
                                                                                        i,0))){
                    tableModelResumenPedido.deleteRow(i);
                    break;
                }
            }
            for(int i=0;i<UtilityCalculoPrecio.getSizeDetalleVenta();i++){
                if(pCodProdAcumula.equalsIgnoreCase(UtilityCalculoPrecio.getBeanPosDetalleVenta(i).getVCodProd())){
                    UtilityCalculoPrecio.deleteBeannPosDetalleVenta(i);
                    break;
                }
            }
            
        }
        
        
        
        tblProductos.repaint();
        log.info("000-VariablesVentas.vArrayList_PedidoVenta:_" + VariablesVentas.vArrayList_PedidoVenta);
        

        for (int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC_Temp.size(); i++) {
            String pcodProd = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 0);
            if(codProd.equalsIgnoreCase(pcodProd)){
                VariablesISCBolsas.vArrayList_ProductosISC_Temp.remove(i);
                break;
            }
        }
        
        //calculaTotalesPedido();comentado para reemplazar por por el neoOperaResumenPedido
        neoOperaResumenPedido();
        setISCTotal();
        log.info("001-VariablesVentas.vArrayList_PedidoVenta:_" + VariablesVentas.vArrayList_PedidoVenta);


        if (tableModelResumenPedido.getRowCount() > 0) {
            if (filaActual > 0)
                filaActual--;
            FarmaGridUtils.showCell(tblProductos, filaActual, 0);
        }

        //Setea e INdicador de Venta de Producto Virtual
        VariablesVentas.venta_producto_virtual = false;
        
        //Inicio[Desarrollo5 - Alejandro Nu�ez] 23.11.2015
        /*if (VariablesPuntos.frmPuntos.getBeanTarjeta() != null) {
            ProgramaXmas1Facade programaXmas1Facade = new ProgramaXmas1Facade();
            programaXmas1Facade.quitarInscripcionPendiente(codProd,VariablesPuntos.frmPuntos.getBeanTarjeta().getDni());
        }*/
        //Fin[Desarrollo5 - Alejandro Nu�ez] 23.11.2015        
    }

    private void muestraMsj(String msj) {
        FarmaUtility.showMessage(this, msj, null);
    }

    private void imprime(String cadena) {
        System.out.println("====> "+cadena);
    }

    public void setFrameMenuFar(FrmEconoMenuFar frame) {
        this.frmEconoMenuFar = frame;
    }

    private void calculoDescuentoFinal(String codProdAux, String codCampAux, double cantUnidPed, int cantAplicable, 
                                       int fraccionP�d, Map mapaCupon) {
        //Obtiene el maximo de unidades a la compra del producto
        //DUBILLUZ 28.05.2009
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////
        // VALIDAR SI PRODUCTO ESTA EN LISTA NEGRA Y CANTIDAD DIARIA O SEMANAL
        // ESTE YA TE DEVUELVE LA CANTIDAD MAXIMA DIARIA O SEMANAL
        // SI NO ES ASI USA EL MAXIMO DECTO MAXIMO AHORA SERA DE LA CAMPA�A SERA DIARIA
        double unidMaximaAplicable = 0.0;
        
        double vUnidDctoHistorico = 0.0;
        
        boolean vExisteProductoListaLocked = false;
        vExisteProductoListaLocked = UtilityFidelizacion.getExisteProdListaLocked(codProdAux);
        boolean vMaxLockedDia = UtilityFidelizacion.getExisteProdListaLockedDia(codProdAux);
        boolean vMaxLockedSemana = UtilityFidelizacion.getExisteProdListaLockedSemana(codProdAux);
        
        if(vExisteProductoListaLocked){
        //esta bloqeado buscara las unidades si es dia o semanal
        if(vMaxLockedDia){
        unidMaximaAplicable = UtilityFidelizacion.getUnidMaxDctoDia(codProdAux);
        vUnidDctoHistorico = UtilityFidelizacion.getHistUnidDctoDia(codProdAux,VariablesFidelizacion.vDniCliente);
        }
        else{
        if(vMaxLockedSemana){    
           unidMaximaAplicable = UtilityFidelizacion.getUnidMaxDctoSemana(codProdAux);
           vUnidDctoHistorico = UtilityFidelizacion.getHistUnidDctoSemana(codProdAux,VariablesFidelizacion.vDniCliente);
        }
        }
        }
        else{
        // no esta en 1 tope, se ira al 2do tope dnde el maximo sera x dia m
        // entnces obtiene  lo ya usado de historico diario 
        unidMaximaAplicable = UtilityFidelizacion.getMaxUnidDctoProdCampana(codCampAux, codProdAux,VariablesFidelizacion.vDniCliente);
        if (unidMaximaAplicable == -1) {
            unidMaximaAplicable = Double.parseDouble(mapaCupon.get("UNID_MAX_PROD").toString());
        }
        vUnidDctoHistorico =  UtilityFidelizacion.getHistUnidDctoDia(codProdAux,VariablesFidelizacion.vDniCliente);// maximo descuento de este producto DIARIO
        }
        ///// aqui hace la restriccion de cantidad
        log.info("unidMaximaAplicable vUnidDctoHistorico "+unidMaximaAplicable+" / "+vUnidDctoHistorico);
        double vCantidadPermitida =  unidMaximaAplicable - (vUnidDctoHistorico);
        if(vCantidadPermitida<0)vCantidadPermitida = 0;
        log.info("vCantidadPermitida "+vCantidadPermitida);
        
        if(vCantidadPermitida>0){
            log.info("cantUnidPed "+cantUnidPed);
            log.info("NO SE >> "+cantUnidPed);
            
            if(cantUnidPed>vCantidadPermitida)
                cantUnidPed=vCantidadPermitida;
            
            log.info("cantUnidPed "+cantUnidPed);
            
            if(vCantidadPermitida<0)
                vCantidadPermitida = 0;
            
            // se ve si llego o no al toque
            if (cantUnidPed  > vCantidadPermitida){
                log.info("aaa");
                cantAplicable = Math.round((float)UtilityVentas.Truncar(vCantidadPermitida * fraccionP�d, 0));
            } else {
                log.info("bbb");
                // este ya excedio el tope sea de maestro o sea de la campa�a
                cantAplicable = Math.round((float)(cantUnidPed * fraccionP�d));
            }           
            
            
        }
        else{
        cantAplicable = 0;
        // dubilluz 17.08.2015
        FarmaUtility.showMessage(this,"El cliente ya cumplio con el tope de unidades \n" +
            "con descuento de este producto:"+codProdAux, txtDescProdOculto);
        }       
        
    }

    private double redondearNro(double nro) {
        int factor=10;
        nro = nro*factor;
        nro=Math.round(nro);
        nro=nro/factor;
        return nro;
    }

    public class KeyDispatcher implements Runnable {
        @Override
        public void run() {
            log.info("antes del hilo");
            log.info("auxiliar --> "+tableModelResumenPedidoAux.data);
            log.info("goggoogogogoog");
            log.info("original --> "+tableModelResumenPedido.data);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            //dispatchKeyEventsViaEventQueue();
            dispatchKeyEventsViaRobot();
            
            log.info("termino el hilo");
            log.info("auxiliar --> "+tableModelResumenPedidoAux.data);
            log.info("goggoogogogoog");
            log.info("original --> "+tableModelResumenPedido.data);
        }

        protected void dispatchKeyEventsViaEventQueue() {
            if (EventQueue.isDispatchThread()) {
                String text = "This is a key sequence dispatched via the event queue\n";
                KeySequence keySequence = TestKeyEvents.getKeySequence(text);
                List<KeyEvent> events = new ArrayList<KeyEvent>();
                List<Integer> modifers = new ArrayList<Integer>();
                for (Key key : keySequence) {
                    events.clear();
                    log.debug(key.toString());
                    switch (key.getStrokeType()) {
                        case Press:
                            switch (key.getKeyCode()) {
                                case KeyEvent.VK_SHIFT:
                                case KeyEvent.VK_ALT:
                                case KeyEvent.VK_CONTROL:
                                case KeyEvent.VK_META:
                                    if (!modifers.contains(key.getKeyCode())) {
                                        modifers.add(key.getKeyCode());
                                    }
                                    break;
                                default:
                                    events.add(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), TestKeyEvents.getModifiers(modifers), key.getKeyCode(), key.getKeyChar()));
                                    break;
                            }
                            break;
                        case Release:
                            switch (key.getKeyCode()) {
                                case KeyEvent.VK_SHIFT:
                                case KeyEvent.VK_ALT:
                                case KeyEvent.VK_CONTROL:
                                case KeyEvent.VK_META:
                                    if (!modifers.contains(key.getKeyCode())) {
                                        modifers.remove(key.getKeyCode());
                                    }
                                    break;
                                default:
                                    events.add(new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), TestKeyEvents.getModifiers(modifers), key.getKeyCode(), key.getKeyChar()));
                                    break;
                            }
                            break;
                        case Type:
                            events.add(new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), TestKeyEvents.getModifiers(modifers), key.getKeyCode(), key.getKeyChar()));
                            events.add(new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), TestKeyEvents.getModifiers(modifers), key.getKeyCode(), key.getKeyChar()));
                            events.add(new KeyEvent(new JPanel(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), TestKeyEvents.getModifiers(modifers), KeyEvent.VK_UNDEFINED, key.getKeyChar()));
                            break;
                    }

                    for (KeyEvent evt : events) {
                        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(evt);
                    }
                }
            } else {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            dispatchKeyEventsViaEventQueue();
                        }
                    });
                } catch (Exception exp) {
                    log.error("",exp);
                }
            }
        }

        protected void dispatchKeyEventsViaRobot() {
        }
    }
    
    
    private String obtieneNumeroPedidoDiario() throws SQLException {

        String feModNumeracion = DBVentas.obtieneFecModNumeraPed();
        String feHoyDia = "";
        String numPedDiario = "";
        if (!(feModNumeracion.trim().length() > 0))
            throw new SQLException("Ultima Fecha Modificacion de Numeraci�n Diaria del Pedido NO ES VALIDA !!!",
                                   "Error", 0001);
        else {
            feHoyDia = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            feHoyDia = feHoyDia.trim().substring(0, 2);
            feModNumeracion = feModNumeracion.trim().substring(0, 2);
            if (Integer.parseInt(feHoyDia) != Integer.parseInt(feModNumeracion)) {
                FarmaSearch.inicializaNumeracionNoCommit(FarmaConstants.COD_NUMERA_PEDIDO_DIARIO);
                numPedDiario = "0001";
            } else {
                // Obtiene el Numero de Atencion del D�a y hace SELECT FOR UPDATE.
                numPedDiario = FarmaSearch.getNuSecNumeracion(FarmaConstants.COD_NUMERA_PEDIDO_DIARIO, 4);
            }
        }
        return numPedDiario;
    }

    private void guardaVariablesPedidoCabecera() {
        VariablesVentas.vIndDistrGratuita = FarmaConstants.INDICADOR_N;
        VariablesVentas.vVal_Bruto_Ped = lblBruto.getText().trim();
        VariablesVentas.vVal_Neto_Ped = lblTotalS.getText().trim();
        VariablesVentas.vVal_Redondeo_Ped = lblRedondeo.getText().trim();
        VariablesVentas.vCostoICBPER_Ped = lblICBPER.getText().trim(); // JHAMRC 10072019
        VariablesVentas.vVal_Igv_Ped = lblIGV.getText().trim();
        VariablesVentas.vVal_Dcto_Ped = lblDscto.getText().trim();
        if (VariablesVentas.vEsPedidoDelivery)
            VariablesVentas.vTip_Ped_Vta = ConstantsVentas.TIPO_PEDIDO_DELIVERY; //verificar q tipo de pedido es...
        else if (VariablesVentas.vEsPedidoInstitucional)
            VariablesVentas.vTip_Ped_Vta =
                    ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL; //verificar q tipo de pedido es...
        else
            VariablesVentas.vTip_Ped_Vta = ConstantsVentas.TIPO_PEDIDO_MESON; //verificar q tipo de pedido es...
        VariablesVentas.vCant_Items_Ped = lblItems.getText().trim();
        VariablesVentas.vEst_Ped_Vta_Cab = ConstantsVentas.ESTADO_PEDIDO_PENDIENTE;

    }

    private void muestraPedidoRapido() {
        DlgNumeroPedidoGenerado dlgNumeroPedidoGenerado = new DlgNumeroPedidoGenerado(myParentFrame, "", true);
        dlgNumeroPedidoGenerado.setVisible(true);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cerrarVentana(true);
        }
    }

    private void limpiaValoresPedido() {

        VariablesVentas.vCodCia_Receta = "";
        VariablesVentas.vCodLocal_Receta= "";
        VariablesVentas.vNumPedido_Receta= "";

        VariablesVentas.vValorMultiplicacion = "1"; //ASOSA - 12/08/2014

        VariablesVentas.vQFApruebaVTANEGATIVA = "";
        VariablesVentas.vCodSolicitudVtaNegativa = "";
        VariablesVentas.vQFApruebaVTANEGATIVA = "";

        VariablesVentas.vCod_Prod = "";
        VariablesVentas.vDesc_Prod = "";
        VariablesVentas.vNom_Lab = "";
        VariablesVentas.vUnid_Vta = "";
        VariablesVentas.vVal_Prec_Vta = "";
        VariablesVentas.vPorc_Dcto_1 = "";
        VariablesVentas.vVal_Bono = "";
        VariablesVentas.vDesc_Acc_Terap = "";
        VariablesVentas.vStk_Prod = "";
        VariablesVentas.vStk_Prod_Fecha_Actual = "";
        VariablesVentas.vVal_Frac = "";
        VariablesVentas.vVal_Prec_Lista = "";
        VariablesVentas.vVal_Igv_Prod = "";
        VariablesVentas.vPorc_Igv_Prod = "";
        VariablesVentas.vEst_Ped_Vta_Cab = "";

        VariablesVentas.vCant_Ingresada = "";
        VariablesVentas.vCant_Ingresada_Temp = "";
        VariablesVentas.vTotalPrecVtaProd = 0;

        VariablesVentas.vArrayList_PedidoVenta = new ArrayList();
        UtilityCalculoPrecio.clearDetalleVenta();

        VariablesVentas.vArrayList_Promociones = new ArrayList();
        VariablesVentas.vArrayList_Prod_Promociones = new ArrayList();

        VariablesVentas.vNum_Ped_Vta = "";
        VariablesVentas.vVal_Bruto_Ped = "";
        VariablesVentas.vVal_Neto_Ped = "";
        VariablesVentas.vVal_Redondeo_Ped = "";
        VariablesVentas.vVal_Igv_Ped = "";
        VariablesVentas.vVal_Dcto_Ped = "";

        VariablesVentas.vNom_Cli_Ped = "";
        VariablesVentas.vDir_Cli_Ped = "";
        VariablesVentas.vRuc_Cli_Ped = "";
        VariablesVentas.vTip_Comp_Ped = "";
        VariablesVentas.vCant_Items_Ped = "";
        VariablesVentas.vNum_Ped_Diario = "";
        VariablesVentas.vTip_Ped_Vta = "";
        VariablesVentas.vCod_Cli_Local = "";
        VariablesVentas.vSec_Ped_Vta_Det = "";
        VariablesVentas.vPorc_Dcto_Total = "";
        VariablesVentas.vEst_Ped_Vta_Det = "";
        VariablesVentas.vVal_Total_Bono = "";
        VariablesVentas.vSec_Usu_Local = "";

        VariablesVentas.vEsPedidoDelivery = false;
        VariablesVentas.vEsPedidoConvenio = false;
        VariablesVentas.vEsPedidoInstitucional = false;
        VariablesVentas.vIngresaCant_ResumenPed = false;

        VariablesVentas.vIndPedConProdVirtual = false;
        VariablesVentas.vIndProdControlStock = true;
        VariablesVentas.vNumeroARecargar = "";
        VariablesVentas.vMontoARecargar = "";
        VariablesVentas.vMontoARecargar_Temp = "";
        VariablesVentas.vIndProdVirtual = "";
        VariablesVentas.vTipoProductoVirtual = "";
        VariablesVentas.vVal_Prec_Lista_Tmp = "";

        VariablesConvenio.vCodConvenio = "";
        VariablesConvenio.vCodCliente = "";
        VariablesConvenio.vPorcCoPago = "";
        VariablesConvenio.vPorcDctoConv = "";
        VariablesConvenio.vNomConvenio = "";
        VariablesConvenio.vValCoPago = "";
        VariablesConvenio.vCreditoUtil = "";
        //VariablesConvenio.vTextoCliente = "" ;

        VariablesVentas.vCodProd_Regalo = "";
        VariablesVentas.vCantidad_Regalo = 0;
        VariablesVentas.vMontoMinConsumoEncarte = 0.00;
        VariablesVentas.vDescProxProd_Regalo = "";
        VariablesVentas.vMontoProxMinConsumoEncarte = 0.00;
        VariablesVentas.vIndVolverListaProductos = false;

        VariablesVentas.vCodCampCupon = "";
        VariablesVentas.vDescCupon = "";
        VariablesVentas.vMontoPorCupon = 0.00;

        VariablesVentas.vIndOrigenProdVta = "";
        VariablesVentas.vPorc_Dcto_2 = "";
        VariablesVentas.vIndDireccionarResumenPed = false;
        VariablesVentas.vIndF11 = false;
        VariablesVentas.vKeyPress = null;

        VariablesVentas.vArrayList_Cupones = new ArrayList();
        VariablesVentas.vArrayList_CuponesFijos = new ArrayList();
        //SE LIMPIAN LAS VARABLES DE FIDELIZACION
        //29.09.2008 DUBILLUZ
        VariablesFidelizacion.vNumTarjeta = "";
        VariablesFidelizacion.vNomCliente = "";
        VariablesFidelizacion.vApeMatCliente = "";
        VariablesFidelizacion.vApePatCliente = "";
        VariablesFidelizacion.vDataCliente = new ArrayList();
        VariablesFidelizacion.vDireccion = "";
        VariablesFidelizacion.vDniCliente = "";
        VariablesFidelizacion.vEmail = "";
        VariablesFidelizacion.vFecNacimiento = "";
        VariablesFidelizacion.vIndAgregoDNI = "N";
        VariablesFidelizacion.vIndExisteCliente = false;
        VariablesFidelizacion.vNomClienteImpr = "";
        VariablesFidelizacion.vSexo = "";
        VariablesFidelizacion.vSexoExists = false;
        VariablesFidelizacion.vTelefono = "";
        VariablesFidelizacion.vSIN_COMISION_X_DNI = false;
        VariablesFidelizacion.vListCampa�asFidelizacion = new ArrayList();

        VariablesVentas.vMensCuponIngre = "";

        //Limpia Variables de Fidelizacion de FORMA DE PAGO
        //dubilluz 09.06.2011
        VariablesFidelizacion.vIndUsoEfectivo = "NULL";
        VariablesFidelizacion.vIndUsoTarjeta = "NULL";
        VariablesFidelizacion.vCodFPagoTarjeta = "NULL";
        VariablesFidelizacion.tmpCodCampanaCupon = "N";
        lblFormaPago.setText("");
        lblFormaPago.setVisible(false);

        /**
     * jcallo inhabilitar delivery cuando el parametro sea NO
     * ***/
        try {
            ArrayList lprms = new ArrayList();
            DBVentas.getParametrosLocal(lprms); //[0]:impresora,[1]:max minutos,[2]:ind_activo
            ArrayList prms = (ArrayList)lprms.get(0);

            // JCORTEZ 06.08.09
            if (prms.get(2).toString().equals("NO")) {
                VariablesVentas.HabilitarF9 = ConstantsVentas.INACTIVO;
                //lblF9.setVisible(false);
            } else {
                VariablesVentas.HabilitarF9 = ConstantsVentas.ACTIVO;
                //lblF9.setVisible(true);
            }

            //JCORTEZ 07.08.09 Se limpia variables tipo pedido Delivery
            VariablesDelivery.vCodCli = "";
            VariablesDelivery.vNombreCliente = "";
            VariablesDelivery.vNumeroTelefonoCabecera = "";
            VariablesDelivery.vDireccion = "";
            VariablesDelivery.vNumeroDocumento = "";
            VariablesDelivery.vObsCli = "";

            lblF9.setVisible(false);

        } catch (Exception e) {
            log.debug("ERROR : " + e);
        }

        // KMONCADA 2015.02.13 PROGRAMA DE PUNTOS
        if(UtilityPuntos.isActivoFuncionalidad()){
            if(VariablesPuntos.frmPuntos != null)
                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
        }
        
        VariablesFidelizacion.vCodFormaPagoCampanasR = ""; //AOVIEDO 04/07/2017
        
        VariablesVentas.vCostoICBPER = "";
        VariablesVentas.vTotalICBPER = "";
    }

    private void colocaUltimoPedidoDiarioVendedor() {
        String pedDiario = "";
        try {
            pedDiario = DBVentas.obtieneUltimoPedidoDiario();
            if (pedDiario.equalsIgnoreCase("0000"))
                pedDiario = "----";
            lblUltimoPedido.setText(pedDiario);
        } catch (SQLException sql) {
            log.error("", sql);
            lblUltimoPedido.setText("----");
            FarmaUtility.showMessage(this, "Error al obtener ultimo pedido diario. \n" +
                    sql.getMessage(), tblProductos);
        }
    }

    private void muestraSeleccionComprobante(boolean esConvenioBTLMF) {
        DlgSeleccionTipoComprobante dlgSeleccionTipoComprobante =
            new DlgSeleccionTipoComprobante(myParentFrame, "", true);
        dlgSeleccionTipoComprobante.setIsActivaF2(!esConvenioBTLMF);
        dlgSeleccionTipoComprobante.setNroDocumentoCliente(VariablesVentas.vRuc_Cli_Ped);
        dlgSeleccionTipoComprobante.setNombreCliente(VariablesVentas.vNom_Cli_Ped);
        dlgSeleccionTipoComprobante.setDireccionCliente(VariablesVentas.vDir_Cli_Ped);
        dlgSeleccionTipoComprobante.setVisible(true);
    }

    private void muestraCobroPedido() {
        // DUBILLUZ 04.02.2013
        FarmaConnection.closeConnection();
        DlgProcesar.setVersion();

        DlgFormaPago dlgFormaPago = new DlgFormaPago(myParentFrame, "", true);
        dlgFormaPago.setIndPedirLogueo(false);
        dlgFormaPago.setIndPantallaCerrarAnularPed(true);
        dlgFormaPago.setIndPantallaCerrarCobrarPed(true);

        //INI ASOSA - 03.07.2014
        String descProd = FarmaUtility.getValueFieldArrayList(tableModelResumenPedido.data, 0, 1);
        log.info("producto cobro pedido: " + descProd);
        dlgFormaPago.setDescProductoRecVirtual(descProd);
        dlgFormaPago.setDescProductoRimac(descProd);    //ASOSA - 12/01/2015 - RIMAC
        //FIN ASOSA - 03.07.2014

        dlgFormaPago.setVisible(true);
        log.info("XXXXX_FarmaVariables.vAceptar:" + FarmaVariables.vAceptar);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cerrarVentana(true);
        } else
            pedidoGenerado = false;
    }

    private void evaluaTitulo() {
        //String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
        //lblCliente.setText(nombreCliente);

        if (VariablesVentas.vEsPedidoDelivery) {
            this.setTitle("Resumen de Pedido - Pedido Delivery" + " /  IP : " + FarmaVariables.vIpPc);
            String nombreCliente =
                VariablesDelivery.vNombreCliente + " " + VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
            lblCliente.setText(nombreCliente);
        } else if (VariablesVentas.vEsPedidoInstitucional) {
            lblCliente.setText("");
            this.setTitle("Resumen de Pedido - Pedido Institucional" + " /  IP : " + FarmaVariables.vIpPc);
        } else if (VariablesVentas.vEsPedidoConvenio) {
            lblCliente.setText(VariablesConvenio.vTextoCliente);
            this.setTitle("Resumen de Pedido - Pedido por Convenio: " + VariablesConvenio.vNomConvenio + " /  IP : " +
                          FarmaVariables.vIpPc);
            log.debug("------------------------" + this.getTitle());
            log.debug("VariablesConvenio.vTextoCliente : *****" + VariablesConvenio.vTextoCliente);

            lblLCredito_T.setText(VariablesConvenioBTLMF.vDatoLCredSaldConsumo);
            lblBeneficiario_T.setText(getMensajeComprobanteConvenio(VariablesConvenioBTLMF.vCodConvenio));

        } else {
            
            
            if(vInd_Menu.equalsIgnoreCase("S")){
                this.setTitle("Resumen de Pedido" + " /  IP : " + FarmaVariables.vIpPc + " / " + FrmEconoMenuFar.tituloBaseFrame);
            }else{
                this.setTitle("Resumen de Pedido" + " /  IP : " + FarmaVariables.vIpPc + " / " + VariablesPtoVenta.tituloBaseFrame);
            }
            
            log.debug("VariablesConvenio.vTextoCliente vacio");
            if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
                //jcallo 02.10.2008
                lblCliente.setText(VariablesFidelizacion.vNomCliente + " "
                             + VariablesFidelizacion.vApePatCliente + " "
                             + VariablesFidelizacion.vApeMatCliente);
                if(VariablesVentas.vArrayList_Cupones.size() == 0){
                    lblCuponIngr.setText("");
                }
                //fin jcallo 02.10.2008
            } else {
                lblCliente.setText("");
                lblCuponIngr.setText("");
            }
        }
    }

    // Inicio Adicion Delivery 28/04/2006 Paulo

    private void generarPedidoDelivery() {
        DlgListaClientes dlgListaClientes = new DlgListaClientes(myParentFrame, "", true);
        dlgListaClientes.setVisible(true);
        if (FarmaVariables.vAceptar) {
            String nombreCliente =
                VariablesDelivery.vNombreCliente + " " + VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
            lblCliente.setText(nombreCliente);
            log.debug("Antes de Evaluar titulo");
            FarmaVariables.vAceptar = false;
            VariablesVentas.vEsPedidoDelivery = true;
        } else {
            if (FarmaVariables.vImprTestigo.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                log.debug("Evaluando titulo");
                evaluaTitulo();
                VariablesVentas.vEsPedidoDelivery = false;
            }
        }
    }

    private void limpiaVariables() {

        VariablesDelivery.vNumeroTelefono = "";
        VariablesDelivery.vNombreInHashtable = "";
        VariablesDelivery.vNombreInHashtableVal = "";
        VariablesDelivery.vCampo = "";
        VariablesDelivery.vCantidadLista = "";
        VariablesDelivery.vDni_Apellido_Busqueda = "";
        VariablesDelivery.vTipoBusqueda = "";
        VariablesDelivery.vNumeroTelefonoCabecera = "";
        VariablesDelivery.vDireccion = "";
        VariablesDelivery.vDistrito = "";
        VariablesDelivery.vCodigoDireccion = "";
        VariablesDelivery.vInfoClienteBusqueda = new ArrayList();
        VariablesDelivery.vInfoClienteBusquedaApellidos = new ArrayList();
        VariablesDelivery.vCodCli = "";
        VariablesDelivery.vObsCli = "";
        VariablesDelivery.vCodDirTable = "";
        VariablesDelivery.vCodDirTmpTable = "";
        VariablesDelivery.vNombreCliente = "";
        VariablesDelivery.vApellidoPaterno = "";
        VariablesDelivery.vApellidoMaterno = "";
        VariablesDelivery.vTipoDocumento = "";
        VariablesDelivery.vTipoCliente = "";
        VariablesDelivery.vNumeroDocumento = "";
        VariablesDelivery.vTipoCalle = "";
        VariablesDelivery.vNombreCalle = "";
        VariablesDelivery.vNumeroCalle = "";
        VariablesDelivery.vNombreUrbanizacion = "";
        VariablesDelivery.vNombreDistrito = "";
        VariablesDelivery.vReferencia = "";
        VariablesDelivery.vNombreInterior = "";
        VariablesDelivery.vModificacionDireccion = "";
        VariablesDelivery.vTipoAccion = "";
        VariablesDelivery.vTipoCampo = "";
        VariablesDelivery.vCodTelefono = "";
        VariablesVentas.vArrayList_Medicos = new ArrayList();
        VariablesVentas.vCodMed = "";
        VariablesVentas.vMatriculaApe = "";
        VariablesVentas.vCodListaMed = "";
        VariablesVentas.vMatriListaMed = "";
        VariablesVentas.vNombreListaMed = "";
        VariablesVentas.vSeleccionaMedico = false;
        VariablesCentroMedico.vNum_Ped_Rec = "";
        VariablesCentroMedico.vVerReceta = false;
        VariablesVentas.vEsPedidoConvenio = false;
        VariablesVentas.venta_producto_virtual = false;
        //limpia Variables de Cliente Dependiente
        VariablesConvenio.vCodClienteDependiente = "";
        VariablesConvenio.vCodTrabDependiente = "";
        // VariablesConvenio
        VariablesConvenio.vTextoCliente = "";
        VariablesConvenio.vCodTrab = "";
        VariablesConvenio.vNomCliente = "";
        //VariablesConvenio.vVal_Prec_Vta_Conv = "";
        //Limipia Variables De DNI para control de maximo ahorro
        //DUBILLUZ 28.05.2009
        VariablesFidelizacion.vDNI_Anulado = false;
        VariablesFidelizacion.vAhorroDNI_x_Periodo = 0;
        VariablesFidelizacion.vMaximoAhorroDNIxPeriodo = 0;
        VariablesFidelizacion.vAhorroDNI_Pedido = 0;
        VariablesFidelizacion.vIndComprarSinDcto = false;
        VariablesFidelizacion.vRecalculaAhorroPedido = false;
        //jquispe 01.08.2011 limpia variables de fidelizacion
        //VariablesFidelizacion.limpiaVariables();

        VariablesFidelizacion.vNumTarjeta = "";
        VariablesFidelizacion.vNomCliente = "";
        VariablesFidelizacion.vApeMatCliente = "";
        VariablesFidelizacion.vApePatCliente = "";
        VariablesFidelizacion.vDataCliente = new ArrayList();
        VariablesFidelizacion.vDireccion = "";
        VariablesFidelizacion.vDniCliente = "";
        VariablesFidelizacion.vEmail = "";
        VariablesFidelizacion.vFecNacimiento = "";
        VariablesFidelizacion.vIndAgregoDNI = "N";
        VariablesFidelizacion.vIndExisteCliente = false;
        VariablesFidelizacion.vNomClienteImpr = "";
        VariablesFidelizacion.vSexo = "";
        VariablesFidelizacion.vSexoExists = false;
        VariablesFidelizacion.vTelefono = "";

        VariablesFidelizacion.vListCampa�asFidelizacion = new ArrayList();

        VariablesVentas.vMensCuponIngre = "";

        //Limpia Variables de Fidelizacion de FORMA DE PAGO
        //dubilluz 09.06.2011
        VariablesFidelizacion.vIndUsoEfectivo = "NULL";
        VariablesFidelizacion.vIndUsoTarjeta = "NULL";
        VariablesFidelizacion.vCodFPagoTarjeta = "NULL";
        VariablesFidelizacion.tmpCodCampanaCupon = "N";
        VariablesFidelizacion.vNumTarjetaCreditoDebito_Campana = "";
        VariablesFidelizacion.tmp_NumTarjeta_unica_Campana = "";

        ///////////////////////////////////////////////
        VariablesFidelizacion.V_NUM_CMP = "";
        VariablesFidelizacion.V_NOMBRE = "";
        VariablesFidelizacion.V_DESC_TIP_COLEGIO = "";
        VariablesFidelizacion.V_TIPO_COLEGIO = "";
        VariablesFidelizacion.V_COD_MEDICO = "";
        VariablesFidelizacion.vColegioMedico = "";
        VariablesFidelizacion.vSIN_COMISION_X_DNI = false;

        ///////////////////////////////////////////////
        //dubilluz 2017.02.15
        VariablesFidelizacion.vIsPrimeraCompraFidelizadoNuevo = false;
        
        tableModelResumenPedidoAux.clearTable();
        tableModelResumenPedidoAux.fireTableDataChanged();
        
        
        VariablesVentas.vCod_Prod_Regalo = "";
        VariablesVentas.vCodEquiValenteAcumula = "";
        UtilityProgramaAcumula.pAccion = "N";
        UtilityProgramaAcumula.vListaAceptaRegaloPedido = new ArrayList();
        UtilityProgramaAcumula.vListaRechazoRegaloPedido = new ArrayList();
        UtilityProgramaAcumula.vAutomaticoIngresoCantidad = false;
        //actuaRobot = false;
        //numPedVtaRobot = "";
        //cadenaTex = "";
        
        VariablesFidelizacion.vCodFormaPagoCampanasR = ""; //AOVIEDO 04/07/2017
        
        //INI AOVIEDO 29/08/2017
        VariablesVentas.vArrayList_Prom_Aux_XX = new ArrayList();
        VariablesVentas.vArrayList_Prod_Prom_Aux_XX = new ArrayList();
        VariablesVentas.vArrayList_Prod_XY = new ArrayList();
        VariablesVentas.vArrayList_Prod_XY_Regalos = new ArrayList();
        //INI AOVIEDO 29/08/2017
        
        // JHAMRC 10072019
        VariablesVentas.vCostoICBPER = "0.00";
        VariablesVentas.vTotalICBPER = "0.00";
        VariablesVentas.vCostoICBPER_Ped = "0.00";
        VariablesISCBolsas.vArrayList_ProductosISC = new ArrayList();
        VariablesISCBolsas.vArrayList_ProductosISC_Temp = new ArrayList();
        // JHAMRC 10072019
    }
    // Fin Adicion Delivery 28/04/2006 Paulo

    //Inicio Adicion Lista Medicos 26/06/2006 Paulo

    private void agregaMedicoVta() {
        try {
            DBVentas.agrega_medico_vta();
            FarmaUtility.aceptarTransaccion();
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrio un error al grabar medico \n " + sql.getMessage(), tblProductos);
        }
    }
    //Fin Adicion Lista Medicos 26/06/2006 Paulo

    /**
     * Se muestra el resumen de la receta generada.
     * @author Edgar Rios Navarro
     * @since 14.12.2006
     */
    private void verReceta() {
        if (VariablesVentas.vSeleccionaMedico) {
            VariablesCentroMedico.vVerReceta = true;
            DlgResumenReceta dlgResumenReceta = new DlgResumenReceta(myParentFrame, "", true);
            dlgResumenReceta.setVisible(true);
        }
    }

    /**
     * Muestra pantalla ingreso de numero telefonico y monto de recarga
     * @author Luis Mesia Rivera
     * @since 08.01.2007
     */
    private void muestraIngresoTelefonoMonto() {
        if (tblProductos.getRowCount() == 0)
            return;
        int filaActual = tblProductos.getSelectedRow();
        VariablesVentas.vCod_Prod = ((String)(tblProductos.getValueAt(filaActual, 0))).trim();
        VariablesVentas.vDesc_Prod = ((String)(tblProductos.getValueAt(filaActual, 1))).trim();
        VariablesVentas.vNom_Lab = ((String)(tblProductos.getValueAt(filaActual, 9))).trim();
        VariablesVentas.vVal_Frac = ((String)(tblProductos.getValueAt(filaActual, 10))).trim();
        VariablesVentas.vPorc_Igv_Prod = ((String)(tblProductos.getValueAt(filaActual, 11))).trim();
        VariablesVentas.vPorc_Dcto_1 = ((String)(tblProductos.getValueAt(filaActual, 5))).trim();
        VariablesVentas.vVal_Prec_Vta = ((String)(tblProductos.getValueAt(filaActual, 6))).trim();

        //Obtenemos la cantidad que recargo
        //31.10.2007  dubilluz  modificacion
        //VariablesVentas.vMontoARecargar_Temp = ((String)(tblProductos.getValueAt(filaActual,6))).trim();
        VariablesVentas.vMontoARecargar_Temp = ((String)(tblProductos.getValueAt(filaActual, 4))).trim();

        VariablesVentas.vNumeroARecargar = ((String)(tblProductos.getValueAt(filaActual, 13))).trim();
        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
        VariablesVentas.vCantxDia = "";
        VariablesVentas.vCantxDias = "";

        DlgIngresoRecargaVirtual dlgIngresoRecargaVirtual = new DlgIngresoRecargaVirtual(myParentFrame, "", true);
        VariablesVentas.vIngresaCant_ResumenPed = true;
        dlgIngresoRecargaVirtual.setVisible(true);
        if (FarmaVariables.vAceptar) {
            VariablesVentas.vVal_Prec_Lista_Tmp = ((String)(tblProductos.getValueAt(filaActual, 17))).trim();
            //Ahora se grabara S/1.00
            //31.10.2007 dubilluz modificacion
            //VariablesVentas.vVal_Prec_Vta = FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vMontoARecargar));
            VariablesVentas.vVal_Prec_Vta =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(ConstantsVentas.PrecioVtaRecargaTarjeta));
            VariablesVentas.vVal_Prec_Lista =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Lista_Tmp) *
                                              FarmaUtility.getDecimalNumber(VariablesVentas.vMontoARecargar));
            
            
            
            seleccionaProducto(filaActual);
            
            

            log.debug("VariablesVentas.vNumeroARecargar : " + VariablesVentas.vNumeroARecargar);
            VariablesVentas.vIndDireccionarResumenPed = true;
            FarmaVariables.vAceptar = false;
        } else
            VariablesVentas.vIndDireccionarResumenPed = false;
    }

    /**
     * Evalua cual debera ser el ingreso de cantidad dependiendo del tipo de producto.
     * @author Luis Mesia Rivera
     * @since 08.01.2007
     */
    private void evaluaIngresoCantidad() {
        //ERIOS 20.05.2013 Tratamiento de Producto Virtual - Magistral

        if (tblProductos.getRowCount() == 0)
            return;

        int row = tblProductos.getSelectedRow();
        String indicadorProdVirtual = FarmaUtility.getValueFieldJTable(tblProductos, row, 14);
        String indicadorPromocion = FarmaUtility.getValueFieldJTable(tblProductos, row, COL_RES_IND_PACK);
        String indTratamiento = FarmaUtility.getValueFieldJTable(tblProductos, row, COL_RES_IND_TRAT);

        //INI DMOSQUEIRA 20190710 - Mostrar la ventana de cobro de bolsas, si selecciono una bolsa        
        String codProducto = FarmaUtility.getValueFieldJTable(tblProductos, row, 0);
        /*boolean esBolsa = UtilityISCBolsas.verificarProductoBolsa(codProducto);
        
        if(esBolsa){
            mostrarISCBolsas(codProducto);
        } else {*/
            if (indicadorPromocion.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                if (row > -1) {
                    if (isPackVariedad()) {
                        String codProm = FarmaUtility.getValueFieldJTable(tblProductos, row, 0);
                        DlgVisualDetaVariedad dlgVisualDetaVariedad = new DlgVisualDetaVariedad(myParentFrame, "", true, codProm);
                        dlgVisualDetaVariedad.setVisible(true);
                    } else {
                        muestraDetallePromocion(row);
                        aceptaPromocion();
                    }                
                }
            } else {
                if (indicadorProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                    if (indTratamiento.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                        muestraIngresoCantidad();
                    } else {
                        //FarmaUtility.showMessage(this,"No puede modificar este registro, eliminelo y vuelva a ingresarlo.",null);
                        muestraTratamiento();
                    }
                } else {
                    String tipoProdVirtual = FarmaUtility.getValueFieldJTable(tblProductos, row, 15);
                    if (tipoProdVirtual.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_RECARGA)) {
                        muestraIngresoTelefonoMonto();
                    } else if (tipoProdVirtual.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_TARJETA)) {
                        muestraIngresoCantidad_Tarjeta_Virtual();
                        //FarmaUtility.showMessage(this, "No es posible cambiar la cantidad de este producto. Verifique!!!", tblProductos);
                        //return;
                    } else if (tipoProdVirtual.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_MAGISTRAL)) {
                        FarmaUtility.showMessage(this,
                                                 "El Recetario Magistral ya fue ingresado. No puede modificar esta informaci�n.",
                                                 tblProductos);
                    }
                }
            }
       // }
        //FIN DMOSQUEIRA 20190710
        
        txtDescProdOculto.setText("");
    }

    /**NUEVO
     * @Autor:  Luis Reque
     * @Fecha:  16-03-2007
     * */
    private boolean validaCreditoCliente(String pCodCli) {
        if (!pCodCli.equalsIgnoreCase("")) //Existe un codigo de trabajador
        {
            double diferencia;
            try {
                double valTotal = FarmaUtility.getDecimalNumber(lblTotalS.getText());
                log.debug("TotalS: " + lblTotalS.getText());
                String valor = DBConvenio.validaCreditoCli(VariablesConvenio.vCodConvenio, pCodCli, FarmaUtility.formatNumber(valTotal),
                                                FarmaConstants.INDICADOR_S);
                log.debug("Diferencia: " + valor);
                diferencia = FarmaUtility.getDecimalNumber(valor);
                if (diferencia >= 0) {
                    log.debug("OK");
                    return true;
                } else {
                    FarmaUtility.showMessage(this,
                                             "No se puede generar el pedido. \nEl cliente excede en "+ConstantesUtil.simboloSoles+" " + FarmaUtility.formatNumber((diferencia *
                                                                                                                                       -1)) +
                                             " el limite de su cr�dito.", null);
                    //FarmaUtility.moveFocusJTable(tblProductos);
                    FarmaUtility.moveFocus(txtDescProdOculto);
                    return false;
                }
            } catch (SQLException sql) {
                log.error("", sql);
                FarmaUtility.showMessage(this, "Error al grabar informaci�n.", null);
                //FarmaUtility.moveFocusJTable(tblProductos);
                FarmaUtility.moveFocus(txtDescProdOculto);
                return false;
            }
        } else { //El convenio no tiene BD
            log.debug("Arreglo: " + VariablesConvenio.vArrayList_DatosConvenio);
            return true;
        }
    }

    private void grabarPedidoConvenio(String pCodCli, String pNumDocIden, String pCodTrabEmp, String pApePat,
                                      String pApeMat, String pFecNac, String pCodSol, String pNumTel, String pDirecCli,
                                      String pNomDist, String pCodInterno, String pNomTrabajador, String pCodCliDep,
                                      String pCodTrabEmpDep) {
        try {
            DBConvenio.grabarPedidoConvenio(VariablesVentas.vNum_Ped_Vta, VariablesConvenio.vCodConvenio, pCodCli,
                                            pNumDocIden, pCodTrabEmp, pApePat, pApeMat, pFecNac, pCodSol, VariablesConvenio.vPorcDctoConv, VariablesConvenio.vPorcCoPago, pNumTel,
                                            pDirecCli, pNomDist, VariablesConvenio.vValCoPago, pCodInterno,
                                            pNomTrabajador, pCodCliDep, pCodTrabEmpDep);
            log.debug("Grabar Pedido Convenio");
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            FarmaUtility.showMessage(this,
                                     "Ocurri� un error al grabar el la informacion del pedido y el convenio: \n" +
                    sql, null);
            //FarmaUtility.moveFocusJTable(tblProductos);
            FarmaUtility.moveFocus(txtDescProdOculto);
        }
    }

    private void grabarPedidoConvenioBTLMF(String pCodCampo, String pDesCampo, String pNombCampo,
                                           String pCodValorCampo) {
        try {

            DBConvenioBTLMF.grabarPedidoVenta(pCodCampo, pDesCampo, pNombCampo, pCodValorCampo);

            log.debug("Grabar Pedido Convenio BTL MF");
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            FarmaUtility.showMessage(this,
                                     "Ocurri� un error al grabar el la informacion del pedido y el convenio BTL MF: \n" +
                    sql, null);
        }
    }

    private void grabarFormaPagoPedido(String pCodFormaPago, String pNumPedVta, String pImPago, String pTipMoneda,
                                       String pTipoCambio, String pVuelto, String pImTotalPago, String pNumTarj,
                                       String pFecVencTarj, String pNomCliTarj, String pCantCupon) {
        try {
            //DBCaja.grabaFormaPagoPedido(pCodFormaPago,pImPago,pTipMoneda,pTipoCambio,pVuelto,pImTotalPago,pNumTarj,pFecVencTarj,pNomCliTarj,pCantCupon);
            DBConvenio.grabaFormaPagoPedido(pCodFormaPago, pNumPedVta, pImPago, pTipMoneda, pTipoCambio, pVuelto,
                                            pImTotalPago, pNumTarj, pFecVencTarj, pNomCliTarj, pCantCupon);
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al grabar la forma de pago del pedido." + sql.getMessage(), null);
            //FarmaUtility.moveFocusJTable(tblProductos);
            FarmaUtility.moveFocus(txtDescProdOculto);
        }
    }

    /**
     * Determina el credito disponible.
     * @param pCodCli
     * @return <b>double</b> credito disponible
     * @author Edgar Rios Navarro
     * @since 23.05.2007
     */
    private double validaCreditoCliente(String pCodCli, String pCodPago, KeyEvent e) throws SQLException {
        double diferencia = 0;
        double valTotal = FarmaUtility.getDecimalNumber(pCodPago);
        String vkF = "";
        log.debug("Monto Copago: " + pCodPago);

        String vIndLinea = FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_N);


        if (vIndLinea.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            String valor = DBConvenio.validaCreditoCli(VariablesConvenio.vCodConvenio, pCodCli, FarmaUtility.formatNumber(valTotal),
                                            FarmaConstants.INDICADOR_S);
            log.debug("Diferencia: " + valor);
            diferencia = FarmaUtility.getDecimalNumber(valor);

            if (diferencia < 0) {
                if (VariablesConvenio.vIndSoloCredito.equals(FarmaConstants.INDICADOR_N)) {
                    valTotal = valTotal + diferencia;
                    VariablesConvenio.vValCoPago = FarmaUtility.formatNumber(valTotal);
                    if (JConfirmDialog.rptaConfirmDialog(this,
                                                         "El cliente excede en "+ConstantesUtil.simboloSoles+" " + FarmaUtility.formatNumber((diferencia *
                                                                                                                  -1)) +
                                                         " el limite de su cr�dito. \n Est� seguro de continuar?")) {
                        if (e.getKeyCode() == KeyEvent.VK_F4 && !pIngresoComprobanteManual) {

                            //VariablesVentas.venta_producto_virtual = false;
                            //grabarPedidoVenta(ConstantsVentas.TIPO_COMP_FACTURA);
                            /*if(cargaLogin_verifica())
                            {*/
                            lblNombreVendedor.setText(FarmaVariables.vNomUsu.trim() + " " +
                                                      FarmaVariables.vPatUsu.trim() + " " +
                                                      FarmaVariables.vMatUsu.trim());
                            // Inicio Adicion Delivery 28/04/2006 Paulo
                            //String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
                            //lblCliente.setText(nombreCliente);
                            // Fin Adicion Delivery 28/04/2006 Paulo
                            colocaUltimoPedidoDiarioVendedor();
                            //FarmaUtility.moveFocus(tblProductos);
                            FarmaUtility.moveFocus(txtDescProdOculto);

                            vkF = "F4";
                            agregarComplementarios(vkF);
                            //}
                        } else if (UtilityPtoVenta.verificaVK_F1(e) && !pIngresoComprobanteManual) {
                            //VariablesVentas.venta_producto_virtual = false;
                            //grabarPedidoVenta(ConstantsVentas.TIPO_COMP_BOLETA);
                            /*if(cargaLogin_verifica())

                            {*/
                            lblNombreVendedor.setText(FarmaVariables.vNomUsu.trim() + " " +
                                                      FarmaVariables.vPatUsu.trim() + " " +
                                                      FarmaVariables.vMatUsu.trim());
                            // Inicio Adicion Delivery 28/04/2006 Paulo
                            //String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
                            //lblCliente.setText(nombreCliente);
                            // Fin Adicion Delivery 28/04/2006 Paulo
                            colocaUltimoPedidoDiarioVendedor();
                            //FarmaUtility.moveFocus(tblProductos);
                            FarmaUtility.moveFocus(txtDescProdOculto);

                            vkF = "F1";
                            agregarComplementarios(vkF);
                            ///}
                        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
                            //COBRA PEDIDO MANUAL
                            //dubilluz 13.10.2014
                            cobraPedidoManual();
                        }
                    }
                } else if (VariablesConvenio.vIndSoloCredito.equals(FarmaConstants.INDICADOR_S)) {
                    FarmaUtility.showMessage(this,
                                             "El cliente excede en "+ConstantesUtil.simboloSoles+" " + FarmaUtility.formatNumber((diferencia *
                                                                                                      -1)) +
                                             " el limite de su cr�dito. \n �No puede realizar la venta!",
                                             tblProductos);
                } else {
                    FarmaUtility.showMessage(this, "No se pudo determinar el indicador del convenio.", tblProductos);
                }
            } else {
                //valTotal = valTotal + diferencia;
                VariablesConvenio.vValCoPago = FarmaUtility.formatNumber(valTotal);
                if (e.getKeyCode() == KeyEvent.VK_F4 && !pIngresoComprobanteManual) {
                    //grabarPedidoVenta(ConstantsVentas.TIPO_COMP_FACTURA);

                    /*if(cargaLogin_verifica())
                    {*/
                    lblNombreVendedor.setText(FarmaVariables.vNomUsu.trim() + " " + FarmaVariables.vPatUsu.trim() +
                                              " " + FarmaVariables.vMatUsu.trim());
                    // Inicio Adicion Delivery 28/04/2006 Paulo
                    //String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
                    //lblCliente.setText(nombreCliente);
                    // Fin Adicion Delivery 28/04/2006 Paulo
                    colocaUltimoPedidoDiarioVendedor();
                    //FarmaUtility.moveFocus(tblProductos);
                    FarmaUtility.moveFocus(txtDescProdOculto);

                    vkF = "F4";
                    agregarComplementarios(vkF);
                    /*}*/
                } else if (UtilityPtoVenta.verificaVK_F1(e) || (e.getKeyChar() == '+' && !pIngresoComprobanteManual)) {
                    // grabarPedidoVenta(ConstantsVentas.TIPO_COMP_BOLETA);
                    /*if(cargaLogin_verifica())
                    {*/
                    lblNombreVendedor.setText(FarmaVariables.vNomUsu.trim() + " " + FarmaVariables.vPatUsu.trim() +
                                              " " + FarmaVariables.vMatUsu.trim());
                    // Inicio Adicion Delivery 28/04/2006 Paulo
                    //String nombreCliente = VariablesDelivery.vNombreCliente + " " +VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
                    //lblCliente.setText(nombreCliente);
                    // Fin Adicion Delivery 28/04/2006 Paulo
                    colocaUltimoPedidoDiarioVendedor();
                    //FarmaUtility.moveFocus(tblProductos);
                    FarmaUtility.moveFocus(txtDescProdOculto);

                    vkF = "F1";
                    agregarComplementarios(vkF);
                    /*}*/
                }
            }
            FarmaUtility.moveFocus(txtDescProdOculto);
        } else
            FarmaUtility.showMessage(this,
                                     "No hay linea con matriz.\n Int�ntelo nuevamente si el problema persiste comun�quese con el Operador de Sistemas.",
                                     txtDescProdOculto);

        return valTotal;
    }


    /**
     * @Author Daniel Fernando Veliz La Rosa
     * @Since  15.08.08
     */
    private void agregarClienteCampana(String pCodCampana, String pCodCupon, String pCodCli, String pDniCliente,
                                       String pNomcliente, String pApePatCliente, String pApeMatCliente,
                                       String pTelefono, String pNumCMP, String pMedico, String pSexo,
                                       String pFecNacimiento) {
        try {
            DBCampana.agregarClienteCampana(pCodCampana, pCodCupon, pCodCli, pDniCliente, pNomcliente, pApePatCliente,
                                            pApeMatCliente, pTelefono, pNumCMP, pMedico, pSexo, pFecNacimiento);
        } catch (SQLException e) {
            log.error("", e);
        }
    }

    /* ************************************************************************** */
    /*                        Metodos Auxiliares                                  */
    /* ************************************************************************** */

    /**
     * Metodo que elimina items del array ,que sean iguales al paramtro
     * q se le envia , comaprando por campo
     * @author :dubilluz
     * @since  :20.06.2007
     */
    private void removeItemArray(ArrayList array, String codProm, int pos) {
        String cod = "";
        codProm = codProm.trim();
        for (int i = 0; i < array.size(); i++) {
            cod = ((String)((ArrayList)array.get(i)).get(pos)).trim();
            log.debug(cod + "<<<<<" + codProm);
            if (cod.equalsIgnoreCase(codProm)) {
                array.remove(i);
                i = -1;
            }
        }
    }

    /**
     * Retorna el detalle de una promocion
     * @author : dubilluz
     * @since  : 03.07.2007
     */
    private ArrayList detalle_Prom(ArrayList array, String codProm) {
        ArrayList nuevo = new ArrayList();
        ArrayList aux = new ArrayList();
        String cod_p = "";
        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
            aux = (ArrayList)(VariablesVentas.vArrayList_Prod_Promociones.get(i));
            cod_p = (String)(aux.get(18));
            if (cod_p.equalsIgnoreCase(codProm)) {
                nuevo.add((ArrayList)(aux.clone()));
            }
        }
        return nuevo;
    }

    /**
     * Agrupa productos que esten en ambos paquetes
     * retorna el nuevoa arreglo
     * @author : dubilluz
     * @since : 27.06.2007
     * @modificacion: ASOSA, 18.12.2009
     */
    private ArrayList agrupar(ArrayList array) {
        int cantCampos = ((ArrayList)array.get(0)).size();
        ArrayList nuevo = new ArrayList();
        ArrayList aux1 = new ArrayList();
        ArrayList aux2 = new ArrayList();
        int cantidad1 = 0;
        int cantidad2 = 0;
        int suma = 0;
        for (int i = 0; i < array.size(); i++) {
            aux1 = (ArrayList)(array.get(i));
            log.debug("AUXXXXXXXXXXXXXXXXXXXXXXXX 1: " + aux1);
            log.debug("SIZE: SIZE: " + aux1.size());
            if (aux1.size() <= cantCampos) { //cantidad de campos ASOSA, 18.12.2009
                for (int j = i + 1; j < array.size(); j++) {
                    aux2 = (ArrayList)(array.get(j));
                    if (aux2.size() <= cantCampos) { //cantidad de campos ASOSA, 18.12.2009
                        if ((((String)(aux1.get(0))).trim()).equalsIgnoreCase((((String)(aux2.get(0))).trim()))) {
                            cantidad1 = Integer.parseInt(((String)(aux1.get(4))).trim());
                            cantidad2 = Integer.parseInt(((String)(aux2.get(4))).trim());
                            suma = cantidad1 + cantidad2;
                            aux1.set(4, suma + "");
                            ((ArrayList)(array.get(j))).add("Revisado");
                        }
                    }
                }
                nuevo.add(aux1);
            }
        }
        log.debug("Agrupado :" + nuevo.size());
        log.debug("Aggrup Elment :" + nuevo);
        return nuevo;
    }


    /**
     * Acepta Modificacion de promocion
     * @author : dubilluz
     * @since  : 04.07.2007
     */
    private void aceptaPromocion() {

        for (int i = 0; i < VariablesVentas.vArrayList_Promociones_temporal.size(); i++)
            VariablesVentas.vArrayList_Promociones.add((ArrayList)((ArrayList)VariablesVentas.vArrayList_Promociones_temporal.get(i)).clone());

        VariablesVentas.vArrayList_Promociones_temporal = new ArrayList();

        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones_temporal.size(); i++)
            VariablesVentas.vArrayList_Prod_Promociones.add((ArrayList)((ArrayList)VariablesVentas.vArrayList_Prod_Promociones_temporal.get(i)).clone());

        VariablesVentas.vArrayList_Prod_Promociones_temporal = new ArrayList();
        //log.debug(VariablesVentas.vArrayList_ResumenPedido.size());
    }

    /**
     * Muestra el dialogo de Ingreso de Cantidad para Producto Tarjeta Virtual
     * @author : dubilluz
     * @since  : 29.08.2007
     */
    private void muestraIngresoCantidad_Tarjeta_Virtual() {
        if (tblProductos.getRowCount() == 0)
            return;
        log.debug("DIEGO UBILLUZ >>  " + VariablesVentas.vArrayList_Prod_Tarjeta_Virtual);
        VariablesVentas.cantidad_tarjeta_virtual =
                Integer.parseInt(FarmaUtility.getValueFieldJTable(tblProductos, tblProductos.getSelectedRow(), 4));
        //------

        //-------
        DlgIngresoCantidadTarjetaVirtual dlgIngresoCantidad =
            new DlgIngresoCantidadTarjetaVirtual(myParentFrame, "", true);
        dlgIngresoCantidad.setVisible(true);

        if (FarmaVariables.vAceptar) {
            int fila = tblProductos.getSelectedRow();
            int cantidad_new = VariablesVentas.cantidad_tarjeta_virtual;
            int cantidad_old = Integer.parseInt(FarmaUtility.getValueFieldJTable(tblProductos, fila, 4));
            log.debug("Cantidad Antigua :  " + cantidad_old);
            log.debug("Cantidad Nueva   :  " + cantidad_new);
            if (cantidad_new != 0)
                seleccionaProductoTarjetaVirtual(cantidad_new + "");
            VariablesVentas.vIndDireccionarResumenPed = true;
            FarmaVariables.vAceptar = false;
        } else
            VariablesVentas.vIndDireccionarResumenPed = false;
    }

    /**
     * Seleccionando el Producto de tajeta Virtual
     * @author : dubilluz
     * @since  : 29.08.2007
     */
    private void seleccionaProductoTarjetaVirtual(String cantidad) { //VariablesVentas.vTotalPrecVtaProd  75.0
        if (tblProductos.getRowCount() == 0)
            return;
        log.debug("VariablesVentas.vTotalPrecVtaProd : " + VariablesVentas.vTotalPrecVtaProd);

        int row = tblProductos.getSelectedRow();
        VariablesVentas.vVal_Prec_Vta = FarmaUtility.getValueFieldJTable(tblProductos, row, 6);
        /**
     * Modificado para la Cantidad que se compra
     */
        VariablesVentas.vCant_Ingresada = cantidad.trim();
        VariablesVentas.vTotalPrecVtaProd =
                (FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada) * FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta));
        operaProductoEnJTable(row);
        operaProductoEnArrayList(row);
        calculaTotalesPedido();

    }

    /**
     * Proceso para revisar eventos externos para los pedidos
     * como productos de regalo ,cumpones y/o complementarios
     * @author dubilluz
     * @since  08.04.2007
     */
    private boolean procesoProductoRegalo(String pNumped, int pSecDet) throws Exception {
        //--Se verifica si puede o no acceder al regalo
        ArrayList arrayLista = new ArrayList();
        ArrayList vAEncartesAplicables = new ArrayList();
        DBVentas.obtieneEncartesAplicables(vAEncartesAplicables);
        log.debug("...Encartes aplicables : " + vAEncartesAplicables);
        String cod_encarte = "";
        ArrayList vMensajesRegalo = new ArrayList();
        for (int e = 0; e < vAEncartesAplicables.size(); e++) {
            cod_encarte = FarmaUtility.getValueFieldArrayList(vAEncartesAplicables, e, 0);
            log.debug("...Procesando Encarte : " + cod_encarte);

            DBVentas.analizaProdEncarte(arrayLista, cod_encarte);
            log.debug("RESULTADO " + arrayLista);
            if (arrayLista.size() > 1) {
                String[] listEncarte =
                    arrayLista.get(0).toString().substring(1, arrayLista.get(0).toString().length() - 1).split("&");
                log.debug("**************** " + listEncarte);
                log.debug("********CON_COLUM_COD_PROD_REGALO* " + CON_COLUM_COD_PROD_REGALO);
                VariablesVentas.vCodProd_Regalo = listEncarte[CON_COLUM_COD_PROD_REGALO];
                VariablesVentas.vCantidad_Regalo =
                        (int)FarmaUtility.getDecimalNumber(listEncarte[CON_COLUM_CANT_MAX_PROD_REGALO]);
                VariablesVentas.vMontoMinConsumoEncarte =
                        FarmaUtility.getDecimalNumber(listEncarte[CON_COLUM_MONT_MIN_ENCARTE]);
                VariablesVentas.vVal_Frac =
                        "1"; //ERIOS 04.06.2008 Por definici�n de Regalo, es en unidad de presentaci�n.

                log.debug("VariablesVentas.vCodProd_Regalo  " + VariablesVentas.vCodProd_Regalo);
                log.debug("VariablesVentas.vCantidad_Regalo  " + VariablesVentas.vCantidad_Regalo);
                log.debug("VariablesVentas.vMontoMinConsumoEncarte  " + VariablesVentas.vMontoMinConsumoEncarte);

                arrayLista.remove(0);
                ArrayList arrayProdEncarte = (ArrayList)arrayLista.clone();
                if (arrayProdEncarte.size() > 0) {
                    log.debug("arrayProdEncarte " + arrayProdEncarte);
                    log.debug("listEncarte " + listEncarte);
                    double monto_actual_prod_encarte = calculoMontoProdEncarte(arrayProdEncarte, 2);

                    //Actualizo regalo si es codigo  cero '0'
                    /*if (VariablesVentas.vCodProd_Regalo.equalsIgnoreCase("0")) {
                        VariablesVentas.vCodProd_Regalo = DBVentas.getEligeRegaloMonto(cod_encarte.trim(),VariablesVentas.vMontoMinConsumoEncarte);
                    }*/

                    //Vuelve a verificar el prod. regalo si este da regalo de acuerdo al monto.
                    ArrayList arrayRegMontos = new ArrayList();
                    DBVentas.getDatosRegaloxMonto(arrayRegMontos, cod_encarte.trim(), monto_actual_prod_encarte);

                    if (arrayRegMontos.size() > 1) {
                        arrayRegMontos.remove(0);
                        ArrayList arrayDatosRegalo = (ArrayList)arrayRegMontos.clone();

                        VariablesVentas.vCodProd_Regalo = FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0, 0);

                        VariablesVentas.vMontoMinConsumoEncarte =
                                FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0,
                                                                                                  1).trim());
                        VariablesVentas.vMontoProxMinConsumoEncarte =
                                FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0,
                                                                                                  2).trim());
                        VariablesVentas.vCodProdProxRegalo =
                                FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0, 3).trim();
                        VariablesVentas.vDescProxProd_Regalo =
                                FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0, 4).trim();

                    } else {
                        VariablesVentas.vMontoProxMinConsumoEncarte = 0;
                        VariablesVentas.vDescProxProd_Regalo = "";
                        VariablesVentas.vCodProdProxRegalo = "";
                    }

                    String desc_prod = DBVentas.obtieneDescProducto(VariablesVentas.vCodProd_Regalo);


                    double stock_regalo = 0, stock_prox_regalo = 0;

                    if (VariablesVentas.vCodProd_Regalo.trim().length() > 0)
                        stock_regalo =
                                FarmaUtility.getDecimalNumber(DBVentas.getStockProdRegalo(VariablesVentas.vCodProd_Regalo).trim());

                    if (VariablesVentas.vCodProdProxRegalo.trim().length() > 0)
                        stock_prox_regalo =
                                FarmaUtility.getDecimalNumber(DBVentas.getStockProdRegalo(VariablesVentas.vCodProdProxRegalo).trim());

                    if (stock_regalo > 0) {
                        if (monto_actual_prod_encarte >= VariablesVentas.vMontoMinConsumoEncarte) {
                            log.debug("...Procesa a a�adir producto de regalo");
                            a�adirProductoRegalo(VariablesVentas.vCodProd_Regalo, VariablesVentas.vCantidad_Regalo,
                                                 pNumped, pSecDet, 0, desc_prod,
                                                 cod_encarte);
                            pSecDet++;
                        }
                    }
                    if (stock_regalo == 0) {
                        if (monto_actual_prod_encarte >= VariablesVentas.vMontoMinConsumoEncarte) {
                            ArrayList array = new ArrayList();

                            DBVentas.getEncarteAplica(array, monto_actual_prod_encarte, cod_encarte.trim());

                            if (array.size() > 0) {
                                double stk_prod =
                                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(array, 0,
                                                                                                      4).trim());
                                if (stk_prod > 0) {
                                    a�adirProductoRegalo(FarmaUtility.getValueFieldArrayList(array, 0, 0).trim(),
                                                         (int)(FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(array,
                                                                                                                                 0,
                                                                                                                                 2).trim())),
                                                         pNumped, pSecDet, 0,
                                                         FarmaUtility.getValueFieldArrayList(array, 0, 1).trim(),
                                                 cod_encarte);
                                    pSecDet++;

                                }
                            }
                        }
                    } else {

                        if (stock_regalo == 0 && stock_prox_regalo > 0) {
                            if (monto_actual_prod_encarte < VariablesVentas.vMontoProxMinConsumoEncarte)

                                a�adirProductoRegalo(VariablesVentas.vCodProdProxRegalo,
                                                     VariablesVentas.vCantidad_Regalo, pNumped, pSecDet, 0,
                                                     VariablesVentas.vDescProxProd_Regalo,
                                                 cod_encarte);
                            pSecDet++;
                        }
                    }

                    if (stock_regalo > 0) {
                        if (monto_actual_prod_encarte < VariablesVentas.vMontoMinConsumoEncarte) {

                            String mensaje =
                                "Para llevarse de regalo " + VariablesVentas.vCantidad_Regalo + " " + desc_prod + " " +
                                " le faltan "+ConstantesUtil.simboloSoles +
                                FarmaUtility.formatNumber(VariablesVentas.vMontoMinConsumoEncarte - monto_actual_prod_encarte) +
                                ".\n" +
                                "�Desea a�adir m�s productos para llevar el regalo?";
                            log.debug(mensaje);
                        } else {

                            if (stock_prox_regalo > 0) {
                                if (monto_actual_prod_encarte < VariablesVentas.vMontoProxMinConsumoEncarte) {

                                    String mensaje =
                                        "Para llevarse de regalo " + VariablesVentas.vCantidad_Regalo + " " +
                                        VariablesVentas.vDescProxProd_Regalo + " " + " le faltan "+ConstantesUtil.simboloSoles +
                                        FarmaUtility.formatNumber(VariablesVentas.vMontoProxMinConsumoEncarte -
                                                                  monto_actual_prod_encarte) + ".\n" +
                                        "�Desea a�adir m�s productos para llevar el regalo?";
                                    log.debug(mensaje);


                                }
                            }
                        }
                    }


                }

            }

            arrayLista = new ArrayList();
        }
        return true;

    }

    private boolean buscaElementArray(ArrayList pArray, String pCodbusq, int pTipo) {

        if (pTipo == 1) // --busqueda para Capana Cupon
            for (int i = 0; i < pArray.size(); i++) {
                if (pArray.get(i).toString().trim().equalsIgnoreCase(pCodbusq.trim()))
                    return true;
            }
        else if (pTipo == 2) // --busqueda para Multimarca y Encarte
            for (int i = 0; i < pArray.size(); i++) {
                if (pArray.get(i).toString().trim().substring(1,
                                                              pArray.get(i).toString().length() - 1).equalsIgnoreCase(pCodbusq.trim()))
                    return true;
            }
        return false;
    }

    private double calculoMontoProdEncarte(ArrayList pArray, int pTipo) {
        double totalNeto = 0.00;
        double totalParcial = 0.00;
        int cantidad = 0;
        String cod_prod = "";
        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
            //log.debug(VariablesVentas.vArrayList_ResumenPedido.get(i));
            cod_prod = UtilityCalculoPrecio.getBeanPosDetalleVenta(i).getVCodProd();
            totalParcial =
                    FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(i).getVSubTotal());

            //log.debug(cod_prod + " " +totalParcial);

            if (buscaElementArray(pArray, cod_prod, pTipo))
                totalNeto = totalNeto + totalParcial;
        }
        //log.debug("*******");
        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
            //log.debug(VariablesVentas.vArrayList_Prod_Promociones.get(i));
            cod_prod = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones, i, 0);
            totalParcial =
                    FarmaUtility.getDecimalNumber((String)((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i)).get(7));

            //log.debug(cod_prod + " " +totalParcial);
            if (buscaElementArray(pArray, cod_prod, pTipo))
                totalNeto = totalNeto + totalParcial;
        }
        //log.debug("*******");
        //log.debug("Monto total de productos de encarte " + totalNeto);

        return totalNeto;
    }

    /**
     *
     */
    private void a�adirProductoRegalo(String pCodProd, int pCantidad, String pNumped, int pSecDet, int pValPrec,
                                      String pDescProd,String vCod_encarte) {
        VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
        if ( /*UtilityVentas.actualizaStkComprometidoProd(pCodProd,pCantidad,
                                    ConstantsVentas.INDICADOR_A,
                                    ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                                    pCantidad,
                                                  true,
                                                  this,
                                                  tblProductos))*/
            UtilityVentas.operaStkCompProdResp(pCodProd, //ASOSA, 08.07.2010
                pCantidad, ConstantsVentas.INDICADOR_A, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR, pCantidad,
                true, this, tblProductos, "")) {
            ArrayList arrayDatosProd = new ArrayList();
            try {
                //DBVentas.obtieneInfoProducto(arrayDatosProd,pCodProd.trim());
                //DBVentas.grabaProductoRegalo(pNumped,pCodProd,pSecDet,pCantidad,pValPrec); //Antes
                DBVentas.grabaProductoRegalo_02(pNumped, pCodProd, pSecDet, pCantidad, pValPrec,
                                                VariablesVentas.secRespStk,vCod_encarte); //ASOSA, 09.07.2010
                //FarmaUtility.showMessage(this, "wawawa", null);
                /*DBPtoVenta.ejecutaRespaldoStock(pCodProd, //Antes, ASOSA, 09.07.2010
                                        pNumped,
                                        ConstantsPtoVenta.TIP_OPERACION_RESPALDO_ACTUALIZAR_PEDIDO,
                                        0,
                                        0,
                                        ConstantsPtoVenta.MODULO_VENTAS);*/
                /*
                 *                 DBVentas.actualizarRespaldoNumPedido(pCodProd,
                                                     ConstantsPtoVenta.MODULO_VENTAS,
                                                     pNumped,
                                                     VariablesVentas.secRespStk); //ASOSA, 09.07.2010
                 * */
                //FarmaUtility.showMessage(this, "wowowo", null);
                String mensaje = "Usted se llevara de regalo " + pCantidad + " ";
                mensaje += pDescProd + ".";
                log.debug(mensaje);
                //FarmaUtility.showMessage(this,mensaje,null);
            } catch (SQLException e) {
                log.error("", e);
            }
            log.debug(" arrayDatosProd " + arrayDatosProd);
        }
    }

    /**
     * Procesa las campa�as sean multimarca y/o cupones
     * @param pNumPed
     * @author dubilluz
     * @since  10.07.2008
     */
    private void procesoCampa�as(String pNumPed) throws Exception {

        // -- Primero se procesan las multimarcas
        procesoMultimarca(pNumPed.trim());
        // -- Luego se validad y procesan las campa�as cupon
        procesoCampanaCupon(pNumPed.trim());
    }


    //Modificado por DVELIZ  04.10.08

    private void procesoCampanaCupon(String pNumPed) throws Exception {
        DBVentas.procesaCampanaCupon(pNumPed, ConstantsVentas.TIPO_CAMPANA_CUPON, VariablesFidelizacion.vDniCliente);
    }

    /**
     * Procensa las multimarcas aplicables al pedido actual
     * @param pNumPed
     * @throws Exception
     * @author dubilluz
     * @since  10.07.2008
     */
    private void procesoMultimarca(String pNumPed) throws Exception {
        //--Se verifica si puede o no acceder a las campa�as
        ArrayList vACampCuponplicables = new ArrayList();
        DBVentas.obtieneCampCuponAplicables(vACampCuponplicables, ConstantsVentas.TIPO_MULTIMARCA, "N");
        log.debug("...Camp Cupones aplicables : " + vACampCuponplicables);
        String cod_camp_cupon = "";
        ArrayList vMensajesCampCupon = new ArrayList();
        for (int e = 0; e < vACampCuponplicables.size(); e++) {
            cod_camp_cupon = FarmaUtility.getValueFieldArrayList(vACampCuponplicables, e, 0);

            ArrayList arrayLista = new ArrayList();
            DBVentas.analizaProdCampCupon(arrayLista, pNumPed, cod_camp_cupon);
            log.debug("RESULTADO " + arrayLista);
            if (arrayLista.size() > 1) {
                String[] listDatosCupon =
                    arrayLista.get(0).toString().substring(1, arrayLista.get(0).toString().length() - 1).split("&");

                VariablesVentas.vCodCampCupon = listDatosCupon[CON_COLUM_COD_CUPON];
                VariablesVentas.vDescCupon = listDatosCupon[CON_COLUM_DESC_CUPON];
                VariablesVentas.vMontoPorCupon = FarmaUtility.getDecimalNumber(listDatosCupon[CON_COLUM_MONT_CUPON]);

                VariablesVentas.vCantidadCupones = FarmaUtility.getDecimalNumber(listDatosCupon[CON_COLUM_CANT_CUPON]);

                log.debug("VariablesVentas.vCodCampCupon  " + VariablesVentas.vCodCampCupon);
                log.debug("VariablesVentas.vDescCupon  " + VariablesVentas.vDescCupon);
                log.debug("VariablesVentas.vMontoPorCupon  " + VariablesVentas.vMontoPorCupon);

                arrayLista.remove(0);
                ArrayList arrayProdCupon = (ArrayList)arrayLista.clone();
                if (arrayProdCupon.size() > 0) {

                    log.debug("arrayProdCupon " + arrayProdCupon);
                    log.debug("listDatosCupon " + listDatosCupon);
                    double monto_actual_prod_cupon = calculoMontoProdEncarte(arrayProdCupon, 2);

                    if (monto_actual_prod_cupon >= VariablesVentas.vMontoPorCupon) {
                        log.debug("...calculando numero de cupones para llevarse");
                        log.debug("...monto_actual_prod_cupon " + monto_actual_prod_cupon);
                        log.debug("...VariablesVentas.vMontoPorCupon " + VariablesVentas.vMontoPorCupon);
                        int cantCupones =
                            (int)((monto_actual_prod_cupon / VariablesVentas.vMontoPorCupon) * VariablesVentas.vCantidadCupones);
                        log.debug("Numero Cupones " + cantCupones);
                        String mensaje = "Usted a ganado " + cantCupones;
                        if (cantCupones == 1)
                            mensaje += " cupon (";
                        else
                            mensaje += " cupones (";

                        mensaje += VariablesVentas.vDescCupon + ")";

                        //FarmaUtility.showMessage(this,mensaje,null);
                        //Se grabara la cantidad de cupones entregados al cupon.
                        DBVentas.grabaCuponPedido(pNumPed, VariablesVentas.vCodCampCupon, cantCupones,
                                                  ConstantsVentas.TIPO_MULTIMARCA, "");
                    }
                }
            }

        }
    }

    private void aceptaOperacion() {
        UtilityCalculoPrecio.clearDetalleVenta();
        UtilityCalculoPrecio.uploadDetalleVenta(VariablesVentas.vArrayList_PedidoVenta);

        
        for (int i = 0; i < VariablesVentas.vArrayList_Promociones_temporal.size(); i++)
            VariablesVentas.vArrayList_Promociones.add((ArrayList)((ArrayList)VariablesVentas.vArrayList_Promociones_temporal.get(i)).clone());

        VariablesVentas.vArrayList_Promociones_temporal = new ArrayList();

        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones_temporal.size(); i++)
            VariablesVentas.vArrayList_Prod_Promociones.add((ArrayList)((ArrayList)VariablesVentas.vArrayList_Prod_Promociones_temporal.get(i)).clone());

        VariablesVentas.vArrayList_Prod_Promociones_temporal = new ArrayList();
    }

    /**
     * se lista productos complementarios
     * @author JCORTEZ
     * @since  10.05.2008
     */
    private void agregarComplementarios(String vkF) {
        log.debug("Seleccion de origen...");
        boolean mostrarComplementarios = true;
        VariablesVentas.vEsProdOferta = false;
        String ind_ori = "";
        for (int i = 0; i <= tblProductos.getRowCount(); i++) {
            ind_ori = FarmaUtility.getValueFieldJTable(tblProductos, i, 19);
            if (ind_ori.equalsIgnoreCase(ConstantsVentas.IND_ORIGEN_COMP) ||
                ind_ori.equalsIgnoreCase(ConstantsVentas.IND_ORIGEN_OFER)) {
                mostrarComplementarios = false;
                break;
            }
        }

        //se fuerza el listado de oferta por el filtro
        if (VariablesVentas.vCodFiltro.equalsIgnoreCase(ConstantsVentas.IND_OFER)) {
            VariablesVentas.vEsProdOferta = true;
            mostrarComplementarios = true;
        }

        int vFila = tblProductos.getSelectedRow();
        VariablesVentas.vCodProdOrigen_Comple = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 1);

        // dubilluz 14.10.2014
        if (pIngresoComprobanteManual) {
            VariablesConvenioBTLMF.vIndVtaComplentaria = "N";
            mostrarComplementarios = false;
        }
        // dubilluz 14.10.2014
        
        //Agregado por FRAMIREZ 11.02.2012 Flag Para Mostrar productos complementarios para un convenio
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
            VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
            if (VariablesConvenioBTLMF.vIndVtaComplentaria.equals("S")) {
                DlgComplementarios1 dlgcomplementarios = new DlgComplementarios1(myParentFrame, "", true, pIngresoComprobanteManual);
                dlgcomplementarios.setVisible(true);
            } else {
                FarmaVariables.vAceptar = false;
            }
        } else {
            if (mostrarComplementarios) {
                DlgComplementarios1 dlgcomplementarios = new DlgComplementarios1(myParentFrame, "", true, pIngresoComprobanteManual);
                dlgcomplementarios.setVisible(true);
            } else {
                FarmaVariables.vAceptar = false;
            }
        }
        
        if (FarmaVariables.vAceptar) {
            VariablesVentas.vCodFiltro = ""; //JCORTEZ 25/04/08
            VariablesVentas.vEsProdOferta = false; //JCORTEZ 25/04/08
            aceptaOperacion(); //agrega producto al pedido
            //operaResumenPedido(); REEMPLAZADO POR EL DE ABAJO
            neoOperaResumenPedido(); //nuevo metodo jcallo 10.03.2009
        } else {
            
            // ahora va calcular t odo lo de puntos y acumula unidades
            // dubilluz 2017.04.25
                if(!vAccionAcumula){
                    UtilityProgramaAcumula.vListaAceptaRegaloPedido = new ArrayList();
                    UtilityProgramaAcumula.vListaRechazoRegaloPedido = new ArrayList();
                    
                    
                    log.info("ANTES UtilityProgramaAcumula.operacion_acumula_bonifica ");
                    if(VariablesPuntos.frmPuntos==null)
                            log.info("VariablesPuntos.frmPuntos es null");
                    else    
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
                            log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
                            }
                    
                    UtilityProgramaAcumula.operacion_acumula_bonifica(this,
                                                                      myParentFrame,
                                                                      pIngresoComprobanteManual);
                    
                    log.info("DESPUES UtilityProgramaAcumula.operacion_acumula_bonifica ");
                    if(VariablesPuntos.frmPuntos==null)
                            log.info("VariablesPuntos.frmPuntos es null");
                    else    
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
                            log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
                            }
                    
                    if(UtilityProgramaAcumula.pAccion.equalsIgnoreCase("C")) {
                        vAccionAcumula = false;
                        FarmaVariables.vAceptar = false;
                        log.info(" cuantos bonifica "+UtilityProgramaAcumula.vListaAceptaRegaloPedido.size());
                        if(UtilityProgramaAcumula.vListaAceptaRegaloPedido.size()>0){
                            log.info(""+UtilityProgramaAcumula.vListaAceptaRegaloPedido);
                        }
                        log.info(" cuantos rechaza  "+UtilityProgramaAcumula.vListaRechazoRegaloPedido.size());
                        if(UtilityProgramaAcumula.vListaRechazoRegaloPedido.size()>0){
                            log.info(""+UtilityProgramaAcumula.vListaRechazoRegaloPedido);
                        }
                    }
                    else {
                        if(UtilityProgramaAcumula.pAccion.equalsIgnoreCase("P")) {
                            vAccionAcumula = false;
                            FarmaVariables.vAceptar = false;
                            vAccionAcumula = false;
                        }
                    }
                    
                }
            // dubilluz 2017.04.25
            if(!FarmaVariables.vAceptar){
                //Agregado Por FRAMIREZ
                if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                    VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
                    if (vkF.equalsIgnoreCase("F1")) {
                        String tipoDocBeneficiario = "N";
                        try {
                            tipoDocBeneficiario =
                                    DBConvenioBTLMF.getComprobanteBeneficiario(VariablesConvenioBTLMF.vCodConvenio);
                        } catch (Exception ex) {
                            tipoDocBeneficiario = "N";
                        }
                        tipoDocBeneficiario = tipoDocBeneficiario.trim();
                        if ("BOL".equalsIgnoreCase(tipoDocBeneficiario) || "M".equalsIgnoreCase(tipoDocBeneficiario)) {
                            if (VariablesConvenioBTLMF.vDatosConvenio != null) {
                                for (int j = 0; j < VariablesConvenioBTLMF.vDatosConvenio.size(); j++) {
                                    Map convenio = (Map)VariablesConvenioBTLMF.vDatosConvenio.get(j);
                                    String pCodCampo =
                                        (String)convenio.get(ConstantsConvenioBTLMF.COL_CODIGO_CAMPO); //COD_TIPO_CAMPO
                                    String pDesCampo = (String)convenio.get(ConstantsConvenioBTLMF.COL_VALOR_IN); // NOMBRE
                                    String pCodValorCampo =
                                        (String)convenio.get(ConstantsConvenioBTLMF.COL_COD_VALOR_IN); // DNI
                                    if ("BOL".equalsIgnoreCase(tipoDocBeneficiario)) {
                                        if (ConstantsConvenioBTLMF.COD_DATO_CONV_BENIFICIARIO.equalsIgnoreCase(pCodCampo)) {
                                            if (pDesCampo != null) {
                                                VariablesVentas.vNom_Cli_Ped = pDesCampo.trim();
                                            }
                                            if (pCodValorCampo != null) {
                                                VariablesVentas.vRuc_Cli_Ped = pCodValorCampo.trim();
                                            }
                                        }
                                    } else {
                                        if (ConstantsConvenioBTLMF.COD_DATO_NOMBRE_BENEFICIARIO.equalsIgnoreCase(pCodCampo)) {
                                            if (pDesCampo != null) {
                                                VariablesVentas.vNom_Cli_Ped = pDesCampo.trim();
                                            }
                                            if (pCodValorCampo != null) {
                                                if (!"0".equalsIgnoreCase(pCodValorCampo.trim()))
                                                    VariablesVentas.vRuc_Cli_Ped = pCodValorCampo.trim();
                                            }
                                        }

                                    }
                                }
                            }
                            //INI JMONZALVE 24042019
                            //Se a�ade funcionalidad de verificacion si es Convenio COBRO POR RESPONSABILIDAD
                            ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad = new ArrayList();
                            boolean verificaConvenioCobroResp = false;
                            try {
                                verificaConvenioCobroResp = verificaConvenioPorResponsabilidad();
                            } catch (Exception ex) {
                                log.debug("Error al verificar convenio por Responsabilidades: " + ex.getMessage());
                            }
                            if(verificaConvenioCobroResp){
                                if(FarmaVariables.vAceptar){
                                    FarmaVariables.vAceptar = false;
                                    try {
                                        if(!DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                                            VariablesConvenioBTLMF.vRuc = "88888888";
                                            VariablesConvenioBTLMF.vInstitucion = "Clientes Varios";
                                            VariablesConvenioBTLMF.vDireccion = "";
                                            
                                            VariablesVentas.vRuc_Cli_Ped = VariablesConvenioBTLMF.vRuc;
                                            VariablesVentas.vNom_Cli_Ped = VariablesConvenioBTLMF.vInstitucion;
                                            VariablesVentas.vDir_Cli_Ped = VariablesConvenioBTLMF.vDireccion;
                                        }else{
                                            if(VariablesFidelizacion.vNomCliente.length() <= 0){
                                                VariablesConvenioBTLMF.vRuc = "88888888";
                                                VariablesConvenioBTLMF.vInstitucion = "Clientes Varios";
                                                VariablesConvenioBTLMF.vDireccion = "";
                                                
                                                VariablesVentas.vRuc_Cli_Ped = VariablesConvenioBTLMF.vRuc;
                                                VariablesVentas.vNom_Cli_Ped = VariablesConvenioBTLMF.vInstitucion;
                                                VariablesVentas.vDir_Cli_Ped = VariablesConvenioBTLMF.vDireccion;
                                            }
                                        }
                                    } catch (SQLException ew) {
                                        log.debug("Error al verificar Acumulacion de puntos para convenio por Responsabilidades: " + ew.getMessage());
                                    }
                                    try {
                                        DBVentas.actualizarCabeceraPedido();
                                    } catch (SQLException sqlEx) {
                                        log.debug("Ocurrio error al Actualizar Cabecera Pedido: " + sqlEx);
                                    }
                                    grabarPedidoVenta(ConstantsVentas.TIPO_COMP_BOLETA);
                                }else{
                                    //Es convenio Cobro por Responsabilidad pero no hay acceso de logeo o seleccion de trabajadores
                                }
                            }else{
                                grabarPedidoVenta(ConstantsVentas.TIPO_COMP_BOLETA);//No es convenio Cobro por Responsabilidad y continua el flujo normal
                            }
                            //FIN JMONZALVE 24042019
                        } else {
                            if ("FAC".equalsIgnoreCase(tipoDocBeneficiario)) {
                                //INI JMONZALVE 24042019
                                //Se a�ade funcionalidad de verificacion si es Convenio COBRO POR RESPONSABILIDAD
                                boolean verificaConvenioCobroResp = false;
                                try {
                                    verificaConvenioCobroResp = verificaConvenioPorResponsabilidad();
                                } catch (Exception ex) {
                                    log.debug("Error al verificar convenio por Responsabilidades");
                                }
                                if(verificaConvenioCobroResp){
                                    if(FarmaVariables.vAceptar){
                                        FarmaVariables.vAceptar = false;
                                        try {
                                            if(!DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                                                VariablesConvenioBTLMF.vRuc = "88888888";
                                                VariablesConvenioBTLMF.vInstitucion = "Clientes Varios";
                                                VariablesConvenioBTLMF.vDireccion = "";
                                                
                                                VariablesVentas.vRuc_Cli_Ped = VariablesConvenioBTLMF.vRuc;
                                                VariablesVentas.vNom_Cli_Ped = VariablesConvenioBTLMF.vInstitucion;
                                                VariablesVentas.vDir_Cli_Ped = VariablesConvenioBTLMF.vDireccion;
                                            }else{
                                                if(VariablesFidelizacion.vNomCliente.length() <= 0){
                                                    VariablesConvenioBTLMF.vRuc = "88888888";
                                                    VariablesConvenioBTLMF.vInstitucion = "Clientes Varios";
                                                    VariablesConvenioBTLMF.vDireccion = "";
                                                    
                                                    VariablesVentas.vRuc_Cli_Ped = VariablesConvenioBTLMF.vRuc;
                                                    VariablesVentas.vNom_Cli_Ped = VariablesConvenioBTLMF.vInstitucion;
                                                    VariablesVentas.vDir_Cli_Ped = VariablesConvenioBTLMF.vDireccion;
                                                }
                                            }
                                        } catch (SQLException ew) {
                                            log.debug("Error al verificar Acumulacion de puntos para convenio por Responsabilidades: " + ew.getMessage());
                                        }
                                        try {
                                            DBVentas.actualizarCabeceraPedido();
                                        } catch (SQLException sqlEx) {
                                            log.debug("Ocurrio error al Actualizar Cabecera Pedido: " + sqlEx);
                                        }
                                        grabarPedidoVenta(ConstantsVentas.TIPO_COMP_FACTURA);
                                    }else{
                                        //Es convenio Cobro por Responsabilidad pero no hay acceso de logeo o seleccion de trabajadores
                                    }
                                }else{
                                    //No es convenio Cobro por Responsabilidad y continua el flujo normal
                                    VariablesVentas.vRuc_Cli_Ped = VariablesConvenioBTLMF.vRuc;
                                    VariablesVentas.vNom_Cli_Ped = VariablesConvenioBTLMF.vInstitucion;
                                    VariablesVentas.vDir_Cli_Ped = VariablesConvenioBTLMF.vDireccion;
                                    grabarPedidoVenta(ConstantsVentas.TIPO_COMP_FACTURA);
                                }
                                //FIN JMONZALVE 24042019
                            } else {
                                grabarPedidoVenta(ConstantsVentas.TIPO_COMP_GUIA);
                            }
                        }
                    }
                } else {
                    if (vkF.equalsIgnoreCase("F1")) {
                        grabarPedidoVenta(ConstantsVentas.TIPO_COMP_BOLETA);
                        //VariablesVentas.venta_producto_virtual = false;
                    } else if (vkF.equalsIgnoreCase("F4")) {
                        grabarPedidoVenta(ConstantsVentas.TIPO_COMP_FACTURA);
                        //VariablesVentas.venta_producto_virtual = false;
                    }
                }                
            }
        }
    }
    
    //INI JMONZALVE 24042019
    private boolean verificaConvenioPorResponsabilidad() throws SQLException{
        String codConvenio = VariablesConvenioBTLMF.vCodConvenio;
        if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(codConvenio)){
            DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
            dlgLogin.setRolUsuario(FarmaConstants.ROL_ADMLOCAL);
            dlgLogin.setVisible(true);
            if(FarmaVariables.vAceptar){
                DlgCobroResponsabilidad dlgCobroResponsabilidad = new DlgCobroResponsabilidad(myParentFrame, "", true, lblTotalS.getText());
                dlgCobroResponsabilidad.setVisible(true);
            }
            return true;
        }
        return false;
    }
    //FIN JMONZALVE 24042019

    /**
     *
     */
    private void evaluaProductoRegalo() {
        //lblPedidoRegalo1.setText("");
        //lblPedidoRegalo2.setText("");
        //lblPedidoRegalo3.setText("");
        txtMensajesPedido.setText("");

        try {
            //--Se verifica si puede o no acceder al regalo
            log.debug("...Evaluando pedido si llevar� Regalo");
            ArrayList vAEncartesAplicables = new ArrayList();
            DBVentas.obtieneEncartesAplicables(vAEncartesAplicables);
            log.debug("...Encartes aplicables : " + vAEncartesAplicables);
            String cod_encarte = "";
            ArrayList vMensajesRegalo = new ArrayList();
            for (int e = 0; e < vAEncartesAplicables.size(); e++) {
                cod_encarte = FarmaUtility.getValueFieldArrayList(vAEncartesAplicables, e, 0);
                log.debug("...Procesando Encarte : " + cod_encarte);

                ArrayList arrayLista = new ArrayList();
                DBVentas.analizaProdEncarte(arrayLista, cod_encarte.trim());
                if (arrayLista.size() > 1) {
                    String[] listEncarte =
                        arrayLista.get(0).toString().substring(1, arrayLista.get(0).toString().length() -
                                                               1).split("&");

                    VariablesVentas.vCodProd_Regalo = listEncarte[CON_COLUM_COD_PROD_REGALO];
                    VariablesVentas.vCantidad_Regalo =
                            (int)FarmaUtility.getDecimalNumber(listEncarte[CON_COLUM_CANT_MAX_PROD_REGALO]);
                    VariablesVentas.vMontoMinConsumoEncarte =
                            FarmaUtility.getDecimalNumber(listEncarte[CON_COLUM_MONT_MIN_ENCARTE]);

                    log.debug("VariablesVentas.vCodProd_Regalo  " + VariablesVentas.vCodProd_Regalo);
                    log.debug("VariablesVentas.vCantidad_Regalo  " + VariablesVentas.vCantidad_Regalo);
                    log.debug("VariablesVentas.vMontoMinConsumoEncarte  " + VariablesVentas.vMontoMinConsumoEncarte);

                    arrayLista.remove(0);
                    ArrayList arrayProdEncarte = (ArrayList)arrayLista.clone();
                    if (arrayProdEncarte.size() > 0) {
                        double monto_actual_prod_encarte = calculoMontoProdEncarte(arrayProdEncarte, 2);

                        //Vuelve a verificar el prod. regalo si este da regalo de acuerdo al monto.
                        ArrayList arrayRegMontos = new ArrayList();
                        DBVentas.getDatosRegaloxMonto(arrayRegMontos, cod_encarte.trim(), monto_actual_prod_encarte);

                        //busco que este encarte tenga regalos por montos.
                        if (arrayRegMontos.size() > 1) {
                            arrayRegMontos.remove(0);
                            ArrayList arrayDatosRegalo = (ArrayList)arrayRegMontos.clone();

                            VariablesVentas.vCodProd_Regalo =
                                    FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0, 0);

                            VariablesVentas.vMontoMinConsumoEncarte =
                                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayDatosRegalo,
                                                                                                      0, 1).trim());
                            VariablesVentas.vMontoProxMinConsumoEncarte =
                                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayDatosRegalo,
                                                                                                      0, 2).trim());
                            VariablesVentas.vCodProdProxRegalo =
                                    FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0, 3).trim();
                            VariablesVentas.vDescProxProd_Regalo =
                                    FarmaUtility.getValueFieldArrayList(arrayDatosRegalo, 0, 4).trim();

                        } else {
                            VariablesVentas.vMontoProxMinConsumoEncarte = 0;
                            VariablesVentas.vDescProxProd_Regalo = "";
                            VariablesVentas.vCodProdProxRegalo = "";
                        }
                        /*

                        */
                        //jquispe 05.12.2011 cambiar si la cantidad del prod. regalo es variable segun el monto
                        /*VariablesVentas.vCantidad_Regalo =
                                            FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayRegMontos,0,2).trim());
                        */
                        String desc_prod = DBVentas.obtieneDescProducto(VariablesVentas.vCodProd_Regalo);
                        String mensaje = "";

                        double stock_regalo = 0, stock_prox_regalo = 0;

                        if (VariablesVentas.vCodProd_Regalo.trim().length() > 0)
                            stock_regalo =
                                    FarmaUtility.getDecimalNumber(DBVentas.getStockProdRegalo(VariablesVentas.vCodProd_Regalo).trim());

                        if (VariablesVentas.vCodProdProxRegalo.trim().length() > 0)
                            stock_prox_regalo =
                                    FarmaUtility.getDecimalNumber(DBVentas.getStockProdRegalo(VariablesVentas.vCodProdProxRegalo).trim());

                        /*if(VariablesVentas.vMontoProxMinConsumoEncarte>0)
                        {*/
                        if (stock_regalo > 0) {

                            if (monto_actual_prod_encarte >= VariablesVentas.vMontoMinConsumoEncarte) {
                                mensaje =
                                        "Usted se llevar� de regalo " + VariablesVentas.vCantidad_Regalo + " " + desc_prod;
                                log.debug(mensaje);
                            }
                        }

                        if (stock_regalo == 0) {
                            if (monto_actual_prod_encarte >= VariablesVentas.vMontoMinConsumoEncarte) {
                                ArrayList array = new ArrayList();

                                DBVentas.getEncarteAplica(array, monto_actual_prod_encarte, cod_encarte.trim());

                                if (array.size() > 0) {
                                    double stk_prod =
                                        FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(array, 0,
                                                                                                          4).trim());
                                    if (stk_prod > 0) {
                                        mensaje =
                                                "Usted se llevar� de regalo " + (int)(FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(array,
                                                                                                                                                        0,
                                                                                                                                                        2).trim())) +
                                                " " + FarmaUtility.getValueFieldArrayList(array, 0, 1).trim();
                                        log.debug(mensaje);
                                    }
                                }
                            } else {
                                /*if(monto_actual_prod_encarte<VariablesVentas.vMontoMinConsumoEncarte){
                                        mensaje = mensaje+" / "+
                                                "FALTAN "+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(VariablesVentas.vMontoMinConsumoEncarte -
                                                                                         monto_actual_prod_encarte) +
                                                "   Para llevarse " +
                                                VariablesVentas.vCantidad_Regalo + " " +
                                         desc_prod;

                                        log.debug(mensaje);
                                    }else{

                                    */
                                if (stock_prox_regalo > 0) {
                                    if (monto_actual_prod_encarte < VariablesVentas.vMontoProxMinConsumoEncarte) {
                                        mensaje =
                                                mensaje + " / " + "FALTAN "+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(VariablesVentas.vMontoProxMinConsumoEncarte -
                                                                                                           monto_actual_prod_encarte) +
                                                "   Para llevarse " + VariablesVentas.vCantidad_Regalo + " " +
                                                VariablesVentas.vDescProxProd_Regalo;
                                        log.debug(mensaje);
                                    }
                                }
                                /*}*/
                            }
                        }


                        if (stock_regalo > 0) {
                            if (monto_actual_prod_encarte < VariablesVentas.vMontoMinConsumoEncarte) {
                                mensaje =
                                        mensaje + " / " + "FALTAN "+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(VariablesVentas.vMontoMinConsumoEncarte -
                                                                                                   monto_actual_prod_encarte) +
                                        "   Para llevarse " + VariablesVentas.vCantidad_Regalo + " " + desc_prod;

                                log.debug(mensaje);
                            } else {


                                if (stock_prox_regalo > 0) {
                                    if (monto_actual_prod_encarte < VariablesVentas.vMontoProxMinConsumoEncarte) {
                                        mensaje =
                                                mensaje + " / " + "FALTAN "+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(VariablesVentas.vMontoProxMinConsumoEncarte -
                                                                                                           monto_actual_prod_encarte) +
                                                "   Para llevarse " + VariablesVentas.vCantidad_Regalo + " " +
                                                VariablesVentas.vDescProxProd_Regalo;
                                        log.debug(mensaje);
                                    }
                                }
                            }
                        }
                        /*
                        }else{

                        if (monto_actual_prod_encarte >=
                            VariablesVentas.vMontoMinConsumoEncarte) {
                            mensaje =
                                    "Usted se llevar� de regalo " + VariablesVentas.vCantidad_Regalo +
                                    " " + desc_prod;
                            log.debug(mensaje);
                            //lblPedidoRegalo2.setText(mensaje.trim());
                        } else {
                            mensaje =
                                    "FALTAN "+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(VariablesVentas.vMontoMinConsumoEncarte -
                                                                             monto_actual_prod_encarte) +
                                    "   Para llevarse " +
                                    VariablesVentas.vCantidad_Regalo + " " +
                                    desc_prod + " ";
                            log.debug(mensaje);
                            //lblPedidoRegalo2.setText(mensaje.trim());
                        }
                        }*/
                        ArrayList varray = new ArrayList();
                        varray.add(mensaje);
                        vMensajesRegalo.add((ArrayList)varray.clone());
                    }
                }
                arrayLista = new ArrayList();
            }
            log.debug("Msn " + vMensajesRegalo);
            String msn = "";
            for (int e = 0; e < vMensajesRegalo.size(); e++) {
                msn = msn + " / " + FarmaUtility.getValueFieldArrayList(vMensajesRegalo, e, 0);
            }


            if (msn.trim().length() > 1) {
                if (msn.length() > 300)
                    //jquispe 16.12.2011 se cambio para que el mensaje se muestre completo
                    msn = msn.substring(2, 800);
                else
                    msn = msn.substring(2);

                txtMensajesPedido.setLineWrap(true);
                txtMensajesPedido.setWrapStyleWord(true);
                txtMensajesPedido.setFont(new Font("SansSerif", Font.BOLD, 13));
                txtMensajesPedido.setForeground(new Color(7, 133, 7));
                txtMensajesPedido.setText(msn.trim());
                // FarmaUtility.moveFocus(txtMensajesPedido);
                FarmaUtility.moveFocus(txtDescProdOculto);
            }

        } catch (SQLException sql) {
            log.error("", sql);
        }


    }


    private void evaluaCantidadCupon() {
        lblMensajeCupon.setText("");
        try {
            //--Se verifica si puede o no acceder al regalo
            ArrayList vACampCuponplicables = new ArrayList();
            DBVentas.obtieneCampCuponAplicables(vACampCuponplicables, ConstantsVentas.TIPO_MULTIMARCA, "N");
            log.debug("...Camp Cupones aplicables : " + vACampCuponplicables);
            String cod_camp_cupon = "";
            String tipo_campana = "";
            String ind_mensaje = "";
            ArrayList vMensajesCampCupon = new ArrayList();
            String msg = "";
            for (int e = 0; e < vACampCuponplicables.size(); e++) {
                cod_camp_cupon = FarmaUtility.getValueFieldArrayList(vACampCuponplicables, e, COL_COD_CAMPANA);
                tipo_campana = FarmaUtility.getValueFieldArrayList(vACampCuponplicables, e, COL_TIPO_CAMPANA);
                ind_mensaje = FarmaUtility.getValueFieldArrayList(vACampCuponplicables, e, COL_IND_MENSAJE_CAMPANA);


                if (ind_mensaje.equalsIgnoreCase(FarmaConstants.INDICADOR_S))
                    if (tipo_campana.equalsIgnoreCase("M"))
                        msg = evaluaMultimarca(cod_camp_cupon);
                //else if(tipo_campana.equalsIgnoreCase("C"))
                //  msg = evaluaCampanaCupon(cod_camp_cupon);

                if (!msg.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                    ArrayList varray = new ArrayList();
                    varray.add(msg);
                    vMensajesCampCupon.add((ArrayList)varray.clone());
                }
            }
            // Evaluando el mensaje
            log.debug("Msn " + vMensajesCampCupon);
            String msn = "";
            for (int e = 0; e < vMensajesCampCupon.size(); e++) {
                msn = msn + " / " + FarmaUtility.getValueFieldArrayList(vMensajesCampCupon, e, 0);
            }
            if (msn.length() > 3)
                lblMensajeCupon.setText(msn.trim().substring(2));


        } catch (SQLException sql) {
            log.error("", sql);
        }
    }

    private String evaluaCampanaCupon(String cod_camp_cupon) {

        return "N";
    }

    private String evaluaMultimarca(String cod_camp_cupon) throws SQLException {

        String mensaje = FarmaConstants.INDICADOR_N;
        ArrayList arrayLista = new ArrayList();
        DBVentas.analizaProdCampCupon(arrayLista, "N", cod_camp_cupon);
        log.debug("RESULTADO " + arrayLista);
        if (arrayLista.size() > 1) {
            String[] listDatosCupon =
                arrayLista.get(0).toString().substring(1, arrayLista.get(0).toString().length() - 1).split("&");

            VariablesVentas.vCodCampCupon = listDatosCupon[CON_COLUM_COD_CUPON];
            VariablesVentas.vDescCupon = listDatosCupon[CON_COLUM_DESC_CUPON];
            VariablesVentas.vMontoPorCupon = FarmaUtility.getDecimalNumber(listDatosCupon[CON_COLUM_MONT_CUPON]);

            VariablesVentas.vCantidadCupones = FarmaUtility.getDecimalNumber(listDatosCupon[CON_COLUM_CANT_CUPON]);


            log.debug("VariablesVentas.vCodCampCupon  " + VariablesVentas.vCodCampCupon);
            log.debug("VariablesVentas.vDescCupon  " + VariablesVentas.vDescCupon);
            log.debug("VariablesVentas.vMontoPorCupon  " + VariablesVentas.vMontoPorCupon);

            arrayLista.remove(0);
            ArrayList arrayProdCupon = (ArrayList)arrayLista.clone();
            if (arrayProdCupon.size() > 0) {
                log.debug("arrayProdCupon " + arrayProdCupon);
                log.debug("listDatosCupon " + listDatosCupon);
                double monto_actual_prod_cupon = calculoMontoProdEncarte(arrayProdCupon, 2);

                log.debug("...monto_actual_prod_cupon " + monto_actual_prod_cupon);
                log.debug("...VariablesVentas.vMontoPorCupon " + VariablesVentas.vMontoPorCupon);
                if (monto_actual_prod_cupon >= VariablesVentas.vMontoPorCupon) {
                    log.debug("...calculando numero de cupones para llevarse");
                    int cantCupones =
                        ((int)(monto_actual_prod_cupon / VariablesVentas.vMontoPorCupon)) * ((int)VariablesVentas.vCantidadCupones);
                    log.debug("Numero Cupones " + cantCupones);
                    mensaje = "Gan� " + cantCupones;
                    if (cantCupones == 1)
                        mensaje += " CUPON  (";
                    else
                        mensaje += " CUPONES  (";

                    mensaje += VariablesVentas.vDescCupon + ")";

                } else {
                    double dif = VariablesVentas.vMontoPorCupon - monto_actual_prod_cupon;
                    if (dif > 0) {
                        /* jquispe 30.11.2011 cambio para dar ams de 1 cupon x camp. multimarca
                          mensaje =
                                "Faltan "+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(dif) +
                                " para 1 CUPON (" +
                                VariablesVentas.vDescCupon + ")";*/

                        mensaje =
                                "Faltan "+ConstantesUtil.simboloSoles+" " + FarmaUtility.formatNumber(dif) + " para " + (int)(VariablesVentas.vCantidadCupones) +
                                " CUPON (" + VariablesVentas.vDescCupon + ")";


                    }
                }
            }
        }

        arrayLista = new ArrayList();
        return mensaje;
    }
    
    // KMONCADA 2015.02.25 PROGRAMA DE PUNTOS
    private boolean isLectoraLazer, isCodigoBarra, isEnter;
    private long tiempoTeclaInicial ,tiempoTeclaFinal,OldtmpT2;
    private ArrayList lstTiempoTecla = new ArrayList();
    
    
    private boolean isNumero(char ca) {
        int numero  = 0;
        try {
            numero = Integer.parseInt(ca + "");
            return true;
        } catch (NumberFormatException nfe) {
            //log.error("",nfe);
            return false;
        }
    }
    
    private void txtDescProdOculto_keyPressed(KeyEvent e) {
        if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE ||
              e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT ||
              e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_HOME)) {
            e.consume();

        }
        
        if(isNumero(e.getKeyChar()) || e.getKeyCode() == KeyEvent.VK_ENTER ){
            VariablesVentas.vCostoICBPER = "";
            VariablesVentas.vTotalICBPER = "";
            if (isLectoraLazer) {
                isLectoraLazer = false;
            }
            isEnter = false;
            isLectoraLazer = false;
            if (tiempoTeclaInicial == 0){
                tiempoTeclaInicial = System.currentTimeMillis();
            }
            isCodigoBarra = true;
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                VariablesVentas.vCostoICBPER = "";
                VariablesVentas.vTotalICBPER = "";
                tiempoTeclaFinal = System.currentTimeMillis();
                // KMONCADA 01.07.2015 PUNTOS APLICA PARA CONVENIOS
                if ( /*(VariablesVentas.vEsPedidoConvenio ||
                    (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1))||*/
                    !UtilityPuntos.isActivoFuncionalidad() /*|| 
                     (pIngresoComprobanteManual)*/
                ) {
                    isLectoraLazer = false;
                    tiempoTeclaInicial = 0;
                    log.info("FLUJO NORMAL");
                    isCodigoBarra = false;
                    txtDescProdOculto_keyPressed2(e);
                }else{
                
                    int maxTiempoLectora = UtilityPuntos.obtieneTiempoMaximoLectora();
                    isLectoraLazer = false;
                    
                    log.info("Tiem 2 " + (tiempoTeclaInicial));
                    log.info("Tiem 1 " + (tiempoTeclaFinal));
                    log.info("Tiempo de ingreso y ENTER " + (tiempoTeclaFinal - tiempoTeclaInicial));
                    //ERIOS 03.07.2013 Limpia la caja de texto
                    limpiaCadenaAlfanumerica(txtDescProdOculto);
                    boolean validaFinal = true;
                    for(int k=0;k<txtDescProdOculto.getText().toCharArray().length;k++){
                        validaFinal = validaFinal && isNumero(txtDescProdOculto.getText().toCharArray()[k]);
                    }
                    if ((tiempoTeclaFinal - tiempoTeclaInicial) <= maxTiempoLectora && txtDescProdOculto.getText().length() > 0 && validaFinal) {
                        isLectoraLazer = true;
                        tiempoTeclaInicial = 0;
                        log.info("ES CODIGO DE BARRA");
                        isCodigoBarra = true;
                        isEnter = true;
                        
                        if(UtilityPuntos.isActivoFuncionalidad()){
                            BeanTarjeta tarjetaPuntosOld = null;
                            log.info("FUNCIONALIDAD DE PUNTOS ACTIVA");
                            if(VariablesPuntos.frmPuntos != null && UtilityPuntos.isTarjetaValida(txtDescProdOculto.getText().trim())){
                                if(VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                                    if(JConfirmDialog.rptaConfirmDialog(this, "Programa Puntos:\nYa registro un cliente, �desea cambiarlo?")){
                                        tarjetaPuntosOld = VariablesPuntos.frmPuntos.getBeanTarjeta();
                                        VariablesFidelizacion.vNumTarjeta = "";
                                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                        VariablesFidelizacion.limpiaVariables();
                                    }else{
                                        return;
                                    }
                                }else{//[Desarrollo5] 20.10.2015
                                    /*VariablesFidelizacion.vNumTarjeta = "";
                                    VariablesFidelizacion.limpiaVariables();
                                    VariablesPuntos.frmPuntos.eliminarTarjetaBean();*/
                                }
                                    
                                // KMONCADA 10.02.2015 INIT DE CONSULTA DE TARJETA DESLIZADA
                                //boolean isAfiliado = UtilityPuntos.consultarTarjetaOrbis(myParentFrame, this, txtDescProdOculto, isLectoraLazer, false);
                                int rsptaConsulta = UtilityPuntos.consultarTarjetaOrbis(myParentFrame, this, txtDescProdOculto, true, false);
                                BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
                                if(tarjetaCliente!=null){
                                    //valida cliente como fidelizado
                                    boolean isClienteAfiliado = false;
                                    if(rsptaConsulta == ConstantsPuntos.TARJETA_NUEVA ||
                                       rsptaConsulta == ConstantsPuntos.TARJETA_OFFLINE){
                                        isClienteAfiliado = true;
                                    }
                                    funcionF12("N",isClienteAfiliado);
                                    /**
                                     * LTAVARA 2016.11.21
                                     * Validar los productos del pedido, si participan en el programa x+1 :
                                     * Cliente monedero: muestra solicitud de afiliacion al programa por producto. Si es tranferencia entre tarjetas solicita ingresar las cantidades de los productos x+1
                                     * Cliente no monedero : muestra mensaje en que programa participa el producto
                                     * */
                                    validarProductosXmas1(tarjetaPuntosOld);
                                    /*
                                    if(tarjetaPuntosOld != null){
                                        UtilityPuntos.transferirInscripcionBonificados(myParentFrame, this, tarjetaPuntosOld, pIngresoComprobanteManual);
                                        //KMONCADA 13.08.2015 QUITARA LOS PACKS COMO FIDELIZADOS EN CASO NO EXISTA CLIENTE FIDELIZADO
                                        if(UtilityPuntos.quitarPacksFidelizados()){
                                            neoOperaResumenPedido();
                                        }
                                    }*/
                                    txtDescProdOculto.setText("");
                                    return ;
                                }else{
                                    txtDescProdOculto_keyPressed2(e);
                                }
                            }else{
                                //Inicio [Desarrollo5] 20.10.2015
                                /*VariablesFidelizacion.vNumTarjeta = "";
                                VariablesFidelizacion.limpiaVariables();
                                VariablesPuntos.frmPuntos.eliminarTarjetaBean();*/
                                //Fin [Desarrollo5] 20.10.2015
                                if(VariablesPuntos.frmPuntos == null){
                                    FarmaUtility.showMessage(this, "Programa de Lealtad:\nError al inicializar variables.", txtDescProdOculto);
                                }
                                if(!UtilityPuntos.isTarjetaValida(txtDescProdOculto.getText().trim())){
                                    log.info("FORMATO DE TARJETA INVALIDO "+txtDescProdOculto.getText().trim());
                                    txtDescProdOculto_keyPressed2(e);
                                }
                            }    
                        }else{
                            log.info("FUNCIONALIDAD DE PUNTOS NO ACTIVA");
                            txtDescProdOculto_keyPressed2(e);
                        }
                        
                    } else {
                        isLectoraLazer = false;
                        tiempoTeclaInicial = 0;
                        log.info("FLUJO NORMAL");
                        isCodigoBarra = false;
                        //////////////////////////////
                        //dubilluz 31.08.2016
                        String pCadena = txtDescProdOculto.getText().trim();
                        
                            if(pCadena.length()==14&&isNumeroReceta(pCadena))
                            {
                                txtDescProdOculto.setText(pCadena);
                                VariablesVentas.vTmpNumReceta = pCadena;
                                VariablesVentas.vKeyPress = e;
                                agregarProducto(null);
                                VariablesVentas.vTmpNumReceta = "";
                                VariablesVentas.vKeyPress = null;
                                
                            }
                            else
                            {
                        //////////////////////////////
                        
                        
                        
                        if(UtilityPuntos.isActivoFuncionalidad()){
                            try{
                                //String aux = DBPuntos.evaluaTarjeta(txtDescProdOculto.getText().trim());
                                //if("S".equalsIgnoreCase(aux)){
                                if(UtilityPuntos.isTarjetaOtroPrograma(txtDescProdOculto.getText().trim(), true)){
                                    FarmaUtility.showMessage(this, "Debe deslizar la tarjeta para acceder a los descuentos.", txtDescProdOculto);
                                    txtDescProdOculto.setText("");
                                }else{
                                    txtDescProdOculto_keyPressed2(e);
                                }
                            }catch(Exception ex){
                                
                            }
                        }else{
                            txtDescProdOculto_keyPressed2(e);
                        }
                                
                       
                        }
                                
                    }
                    //FarmaUtility.moveFocus(txtTarjeta);
                }
            }
        }else{
            isCodigoBarra = false;
            
            char letra = e.getKeyChar();
            String pCadena = txtDescProdOculto.getText().trim();

            /*isCodigoBarra = false;
            log.info("FLUJO NORMAL");
            txtDescProdOculto_keyPressed2(e);*/
            //ASADSADSD
            VariablesVentas.vCostoICBPER = "";
            VariablesVentas.vTotalICBPER = "";
            if(isNumeroReceta(pCadena)&&e.getKeyCode() != KeyEvent.VK_ENTER && pCadena.length()>0||(pCadena.trim().length()==0&&(letra+"").equalsIgnoreCase("R"))){
                pCadena = txtDescProdOculto.getText().trim()+letra;
                if(pCadena.substring(0, 1).trim().equalsIgnoreCase("R")){
                    if(pCadena.length()>1){
                        if(isNumerico(letra+"")&&pCadena.length()<=14)
                        {
                            if(pCadena.length()==14)
                            {
                                if(isNumeroReceta(pCadena)){
                                    txtDescProdOculto.setText(pCadena);
                                    // log.debug("cod_barra "+cod_barra);
                                    VariablesVentas.vTmpNumReceta = pCadena;
                                    VariablesVentas.vKeyPress = e;
                                    agregarProducto(null);
                                    VariablesVentas.vTmpNumReceta = "";
                                    VariablesVentas.vKeyPress = null;
                                }
                                else{
                                    log.info("FLUJO NORMAL AV");
                                    txtDescProdOculto_keyPressed2(e);                            
                                }
                            }
                        }
                        else{
                            if(pCadena.length()==14 && e.getKeyCode() != KeyEvent.VK_ENTER)
                            {
                                VariablesVentas.vCostoICBPER = "";
                                VariablesVentas.vTotalICBPER = "";
                                if(isNumeroReceta(pCadena)){
                                    txtDescProdOculto.setText(pCadena);
                                    // log.debug("cod_barra "+cod_barra);
                                    VariablesVentas.vTmpNumReceta = pCadena;
                                    VariablesVentas.vKeyPress = e;
                                    agregarProducto(null);
                                    VariablesVentas.vTmpNumReceta = "";
                                    VariablesVentas.vKeyPress = null; 
                                }
                                else{
                                    log.info("FLUJO NORMAL BD");
                                    txtDescProdOculto_keyPressed2(e);                            
                                }
                            }
                            else
                            {
                             log.info("FLUJO NORMAL CD");
                             txtDescProdOculto_keyPressed2(e);                            
                            }
                        }
                    }
                }
                else{
                    log.info("FLUJO NORMAL EF");
                    txtDescProdOculto_keyPressed2(e);
                }
            }
            else{
                if(pCadena.length()==14 && e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    VariablesVentas.vCostoICBPER = "";
                    VariablesVentas.vTotalICBPER = "";
                    if(isNumeroReceta(pCadena)){
                        txtDescProdOculto.setText(pCadena);
                        // log.debug("cod_barra "+cod_barra);
                        VariablesVentas.vTmpNumReceta = pCadena;
                        VariablesVentas.vKeyPress = e;
                        agregarProducto(null);
                        VariablesVentas.vTmpNumReceta = "";
                        VariablesVentas.vKeyPress = null; 
                    }
                    else{
                        log.info("FLUJO NORMAL HG");
                        txtDescProdOculto_keyPressed2(e);                            
                    }
                }
                else
                {
                    if(e.getKeyCode() != KeyEvent.VK_ENTER){
                        VariablesVentas.vCostoICBPER = "";
                        VariablesVentas.vTotalICBPER = "";
                 log.info("FLUJO NORMAL TR");
                 txtDescProdOculto_keyPressed2(e);                            
                    }
                }
            }
        }
    }

    public void cargaTarjFidelizacion(String cadena){
        boolean pPermite = false;
        BeanTarjeta tarjetaPuntosOld = null;
        if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
            /*FarmaUtility.showMessage(this, "No puede ingresar m�s de una tarjeta.",
                                     txtDescProdOculto);*/
            if(JConfirmDialog.rptaConfirmDialog(this, "Programa club de descuentos:\nYa registro una tarjeta, �desea cambiarlo?")){
                if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                    tarjetaPuntosOld = VariablesPuntos.frmPuntos.getBeanTarjeta();
                    VariablesFidelizacion.vNumTarjeta = "";
                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                }
                VariablesFidelizacion.limpiaVariables();
                pPermite=true;
            }
            else
                pPermite=false;
        }
        else
            pPermite=true;
        
        
        if(pPermite){
            //validarClienteTarjeta(cadena, true);
            validarClienteTarjeta(cadena, false);
            if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
                UtilityFidelizacion.operaCampa�asFidelizacion(cadena, VariablesConvenioBTLMF.vCodConvenio);
                VariablesFidelizacion.vDNI_Anulado =
                        UtilityFidelizacion.isDniValido(VariablesFidelizacion.vDniCliente);
                VariablesFidelizacion.vAhorroDNI_x_Periodo =
                        UtilityFidelizacion.getAhorroDNIxPeriodoActual(VariablesFidelizacion.vDniCliente,
                                                                       VariablesFidelizacion.vNumTarjeta);
                VariablesFidelizacion.vMaximoAhorroDNIxPeriodo =
                        UtilityFidelizacion.getMaximoAhorroDnixPeriodo(VariablesFidelizacion.vDniCliente,
                                                                       VariablesFidelizacion.vNumTarjeta);
    
                AuxiliarFidelizacion.setMensajeDNIFidelizado(lblDNI_Anul, "R", txtDescProdOculto,
                                                             this);
                if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                    BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
                    if(tarjetaCliente!=null){
                        //valida cliente como fidelizado
                        if(tarjetaPuntosOld != null){
                            UtilityPuntos.transferirInscripcionBonificados(myParentFrame, this, tarjetaPuntosOld, pIngresoComprobanteManual);
                        }
                    }
                }
                neoOperaResumenPedido();
                
            }       
        }
        txtDescProdOculto.setText("");
    }
    
    private void txtDescProdOculto_keyPressed2(KeyEvent e) {
        //if (e.getKeyCode() == KeyEvent.VK_ALT) {e.consume();return;}

        //ERIOS 15.01.2014 Correccion para lectura de escaner NCR
        //log.debug("Tecla: " + e.getKeyCode() + " (" + e.getKeyChar() + ")");
        if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE ||
              e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT ||
              e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_HOME)) {
            e.consume();
        }

        FarmaGridUtils.aceptarTeclaPresionada(e, tblProductos, txtDescProdOculto, 1);
        String vCadIngresada = txtDescProdOculto.getText();

        //kmoncada 16.07.2014 RECONOCE CONVINACION DE TECLAS ALT +
        if (e.getKeyCode() == KeyEvent.VK_ALT && contarCombinacion == 0) {
            contarCombinacion++;
        } else {
            if (contarCombinacion == 1) {
                switch (e.getKeyCode()) {

                    //SOLICITARON QUE NO EXISTA Y QUE SEA DE ACUERDO A LA CONEXION o NO.
                    /*case KeyEvent.VK_C: // ALT + C // LTAVARA 29.08.2014 activa Contingencia
                        if(cargaLoginAdmLocal()){

                            if (com.gs.mifarma.componentes.JConfirmDialog.rptaConfirmDialog(this,"Est� seguro que Desea el Activar Proceso Contingencia?"))
                            {
                            EposVariables.vFlagComprobanteE= false;
                            }

                        }
                    break;*/
                case KeyEvent.VK_SPACE: // ALT + BARRA ESPACIADORA
                    abrePedidosDelivery();
                    break;


                }
                contarCombinacion = 0;
                return;
            }
            contarCombinacion = 0;
        }

        if (!UtilityVentas.isNumerico(vCadIngresada) && vCadIngresada.indexOf("%") != 0) {
            tblProductos_keyPressed(e);
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                VariablesVentas.vCostoICBPER = "";
                VariablesVentas.vTotalICBPER = "";
                //ERIOS 03.07.2013 Limpia la caja de texto
                limpiaCadenaAlfanumerica(txtDescProdOculto);
                if (pasoTarjeta) {
                    txtDescProdOculto.setText("");
                    pasoTarjeta = false;
                    return;
                }

                setFormatoTarjetaCredito(txtDescProdOculto.getText().trim());
                String codProd = txtDescProdOculto.getText().trim();
                if (UtilityVentas.isNumerico(codProd)) {

                    String cadena = codProd.trim();
                    String formato = "";
                    if (cadena.trim().length() > 6)
                        formato = cadena.substring(0, 5);
                    /*if (formato.equals("99999")) {
                        if (VariablesVentas.vEsPedidoConvenio ||
                            (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {

                            FarmaUtility.showMessage(this, "No puede agregar una tarjeta a un " + "pedido por convenio.", txtDescProdOculto);
                            return;
                        }
                        if (UtilityFidelizacion.EsTarjetaFidelizacion(cadena)) {
                            //dubilluz 22.04.2015
                            cargaTarjFidelizacion(cadena);
                            return;
                        } else {
                            FarmaUtility.showMessage(this, "La tarjeta no es valida", null);
                            txtDescProdOculto.setText("");
                            FarmaUtility.moveFocus(txtDescProdOculto);
                            return;
                        }
                    }*/
                    // KMONCADA 
                    boolean pIsTarjetaEnCampana = UtilityFidelizacion.isTarjetaPagoInCampAutomatica(cadena.trim());
                    if(!pIsTarjetaEnCampana){
                        boolean evaluaTarjetaConvenio = true;
                        if(UtilityPuntos.isActivoFuncionalidad()){
                            String aux = "N";
                            try{
                                //aux = DBPuntos.evaluaTarjeta(cadena);
                                aux = DBPuntos.isTarjetaValida(cadena);
                            }catch(Exception ex){
                                aux = "N";
                            }
                            if("S".equalsIgnoreCase(aux)){
                                evaluaTarjetaConvenio = false;
                            }else{
                                if(UtilityFidelizacion.validaTarjetaLocal(cadena)){
                                    FarmaUtility.showMessage(this,DBPuntos.getMensajeSinTarjeta(),txtDescProdOculto);
                                    txtDescProdOculto.setText("");
                                    return;
                                }
                                //txtDescProdOculto.setText("");
                                evaluaTarjetaConvenio = false;
                                //return;
                            }
                        }else{
                            evaluaTarjetaConvenio = true;
                        }
                        
                        if (UtilityFidelizacion.EsTarjetaFidelizacion(cadena) && evaluaTarjetaConvenio) {
                            if(!UtilityPuntos.isActivoFuncionalidad()){
                                if (VariablesVentas.vEsPedidoConvenio ||
                                    (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {
        
                                    FarmaUtility.showMessage(this, "No puede agregar una tarjeta a un " + "pedido por convenio.", txtDescProdOculto);
                                    return;
                                }
                            }
                            //dubilluz 22.04.2015
                            cargaTarjFidelizacion(cadena);                                        
                            //vEjecutaAccionTeclaListado = false;
                            return;
                        } else {
                            log.info("La tarjeta no es valida --->"+cadena);
                            //FarmaUtility.showMessage(this, "La tarjeta no es valida", null);
                            //txtProducto.setText("");
                            //FarmaUtility.moveFocus(txtProducto);
                            //vEjecutaAccionTeclaListado = false;
                            //return;
                        }
                    }

                    /*if (cadena.trim().length() > 6) {
                        formato = cadena.substring(0, 5);
                    }
                    if (formato.equals("99999")) {
                        return;
                    }*/

                    if (UtilityVentas.esCupon(cadena, this, txtDescProdOculto)) {
                        //ERIOS 2.3.2 Valida convenio BTLMF
                        /*if (VariablesVentas.vEsPedidoConvenio ||
                            (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                             VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF)) {
                            FarmaUtility.showMessage(this, "No puede agregar cupones a un pedido por convenio.",
                                                     txtDescProdOculto);
                            return;
                        }*/
                        validarAgregarCupon(cadena);
                        return;
                    }

                    
                    if (pIsTarjetaEnCampana) {
                        if (VariablesVentas.vEsPedidoConvenio ||
                            (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {
                            FarmaUtility.showMessage(this, "No puede agregar una tarjeta en campa�a a un pedido por convenio.", txtDescProdOculto);
                            txtDescProdOculto.setText("");
                            return;
                        }
                        if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                            log.info("se limpia la tarjeta puntos");
                            VariablesFidelizacion.limpiaVariables();
                            VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                        }
                        validaIngresoTarjetaPagoCampanaAutomatica(cadena.trim());
                        return;
                    }

                    if (VariablesFidelizacion.vDniCliente != "") {
                        VariablesCampana.vFlag = false;

                        if (codProd.length() == 0) {
                            return;
                        }

                        if (codProd.length() < 6) {
                            FarmaUtility.showMessage(this, "Producto No Encontrado seg�n Criterio de B�squeda !!!",
                                                     txtDescProdOculto);
                            VariablesVentas.vCodProdBusq = "";
                            VariablesVentas.vKeyPress = null;
                            //KMONCADA 05.08.2015 SE LIMPIA EL TEXTO INGRESADO
                            txtDescProdOculto.setText("");
                            return;
                        }

                        buscaProducto(codProd, e);
                    } else {

                        buscaProducto(codProd, e);

                        if (codProd.length() == 0) {
                            return;
                        }

                        if (codProd.length() < 6) {
                            FarmaUtility.showMessage(this, "Producto No Encontrado seg�n Criterio de B�squeda !!!",
                                                     txtDescProdOculto);
                            VariablesVentas.vCodProdBusq = "";
                            VariablesVentas.vKeyPress = null;
                            //KMONCADA 05.08.2015 SE LIMPIA EL TEXTO INGRESADO
                            txtDescProdOculto.setText("");
                            return;
                        }

                    }
                }

            }
        }

        if (vCadIngresada.length() > 6 && vCadIngresada != null) {
            if (Character.isLetter(vCadIngresada.charAt(0)) && (!Character.isLetter(vCadIngresada.charAt(1)))) {
                vCadIngresada = UtilityPtoVenta.getCodBarraSinCarControl(vCadIngresada);
                String vTemp = UtilityPtoVenta.getCadenaAlfanumerica(vCadIngresada);
                log.debug(vTemp);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    VariablesVentas.vCostoICBPER = "";
                    VariablesVentas.vTotalICBPER = "";
                    e.consume();
                    try {
                        log.debug(vTemp);
                        buscaProducto(vTemp, e);

                    } catch (Exception ex) {
                        log.error(" ", ex);
                    }
                }


            }


        }
    }

    /**
     * BUSCANDO EN EL ARREGLO DE CUPONES o campanias que no haya agregado anteriormente
     * @author JMINAYA
     * @since  01.09.2008
     */
    public boolean busca_producto_cupon(String vcodigo) {
        //empieza la busqueda del producto en el arrayList
        //String codigo;
        Map mapaCupon = new HashMap();
        for (int i = 0; i < VariablesVentas.vArrayList_Cupones.size(); i++) {
            //codigo = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Cupones,i,0);
            mapaCupon = (Map)VariablesVentas.vArrayList_Cupones.get(i);
            if (mapaCupon.get("COD_CUPON").toString().trim().equals(vcodigo.trim()))
                return true;
        }
        return false;
    }

    private void txtDescProdOculto_keyReleased(KeyEvent e) {
        if (1 == 1)
            return;
        // log.debug("en el oculto " + e.getKeyChar());
        String cadena = txtDescProdOculto.getText().trim();
        //log.debug("cadena " +cadena );
        //varNumero = isNumerico(cadena.trim());
        //log.debug("es numero "+isNumero);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            /*String formato = "";
        if(cadena.trim().length()>6)
           formato = cadena.substring(0, 5);
        if (formato.equals("99999"))
            return;


      if(UtilityVentas.esCupon(cadena,this,txtDescProdOculto))
      {
        agregarCupon(cadena);
      }
      else*/
            limpiaCadenaAlfanumerica(txtDescProdOculto);
            if (UtilityVentas.isNumerico(cadena) && VariablesFidelizacion.vNumTarjeta.trim().length() == 0 &&
                //!busca_producto_cupon(cadena.trim())
                !UtilityVentas.esCupon(cadena, this, txtDescProdOculto)) {
                //log.debug("presiono enter");
                e.consume();
                String productoBuscar = txtDescProdOculto.getText().trim().toUpperCase();

                txtDescProdOculto.setText(productoBuscar);
                if (productoBuscar.length() == 0)
                    return;

                String codigo = "";
                // revisando codigo de barra
                char primerkeyChar = productoBuscar.charAt(0);
                char segundokeyChar;

                if (productoBuscar.length() > 1)
                    segundokeyChar = productoBuscar.charAt(1);
                else
                    segundokeyChar = primerkeyChar;

                char ultimokeyChar = productoBuscar.charAt(productoBuscar.length() - 1);

                if (productoBuscar.length() > 6 &&
                    (!Character.isLetter(primerkeyChar) && (!Character.isLetter(segundokeyChar) &&
                                                            (!Character.isLetter(ultimokeyChar))))) {
                    String cod_barra = productoBuscar + "";
                    txtDescProdOculto.setText(cod_barra);
                    // log.debug("cod_barra "+cod_barra);
                    VariablesVentas.vCodBarra = cod_barra;
                    VariablesVentas.vKeyPress = e;

                    //agregarProducto();
                    agregarProducto(null); //ASOSA - 10/10/2014 - PANHD

                    VariablesVentas.vCodBarra = "";
                    VariablesVentas.vKeyPress = null;
                }

                FarmaUtility.moveFocus(txtDescProdOculto);
                txtDescProdOculto.setText("");
            }

        }
    }

    private void txtDescProdOculto_keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '+' && !pIngresoComprobanteManual) {
            e.consume();
            //validaConvenio(e, VariablesConvenio.vPorcCoPago);
            //JMIRANDA 23.06.2010
            validaConvenio_v3(e, VariablesConvenio.vPorcCoPago);
            FarmaUtility.moveFocus(txtDescProdOculto);
        }
    }

    /**
     * Se muestra filtro para filtrar los productos
     * @author JCORTEZ
     * @since  17.04.2008
     */
    private void mostrarFiltro() {

        DlgFiltro dlgFiltro = new DlgFiltro(myParentFrame, "", true);
        dlgFiltro.setVisible(true);
        if (FarmaVariables.vAceptar) {
            //agregarProducto();
            agregarProducto(null); //ASOSA - 10/10/2014 - PANHD
            //FarmaVariables.vAceptar = false;
        }

        FarmaUtility.moveFocus(txtDescProdOculto);

    }

    private void obtieneInfoProdEnArrayList(ArrayList pArrayList, String codProd) {
        try {
            DBVentas.obtieneInfoProducto(pArrayList, codProd);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener informacion del producto en Arreglo. \n" +
                    sql.getMessage(), txtDescProdOculto);
        }
    }

    private void muestraTratamiento() {
        if (tblProductos.getRowCount() == 0)
            return;


        int vFila = tblProductos.getSelectedRow();

        VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 0);
        VariablesVentas.vDesc_Prod = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 1);
        VariablesVentas.vNom_Lab = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 9);

        VariablesVentas.vCant_Ingresada_Temp = ((String)(tblProductos.getValueAt(vFila, 4))).trim();
        VariablesVentas.vVal_Frac = ((String)(tblProductos.getValueAt(vFila, 10))).trim();

        VariablesVentas.vCant_Vta = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 4);
        VariablesVentas.vIndModificacion = FarmaConstants.INDICADOR_S;

        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_S;
        VariablesVentas.vCantxDia = FarmaUtility.getValueFieldJTable(tblProductos, vFila, COL_RES_CANT_XDIA);
        VariablesVentas.vCantxDias = FarmaUtility.getValueFieldJTable(tblProductos, vFila, COL_RES_CANT_DIAS);


        DlgTratamiento dlgtratemiento = new DlgTratamiento(myParentFrame, "", true);
        VariablesVentas.vIngresaCant_ResumenPed = false;
        dlgtratemiento.setVisible(true);

        if (FarmaVariables.vAceptar) {
            seleccionaProducto(vFila);
            FarmaVariables.vAceptar = false;
            VariablesVentas.vIndDireccionarResumenPed = true;
        } else
            VariablesVentas.vIndDireccionarResumenPed = false;


        VariablesVentas.vCantxDia = "";
        VariablesVentas.vCantxDias = "";
        VariablesVentas.vCant_Vta = "";
        VariablesVentas.vIndTratamiento = "";

    }

    private void procesoPack(String pNumPed) {
        try {
            //DBVentas.procesoPackRegalo(pNumPed.trim()); Antes
            DBVentas.procesoPackRegalo_02(pNumPed.trim()); //ASOSA, 06.07.2010
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener informacion del producto en Arreglo. \n" +
                    sql.getMessage(), txtDescProdOculto);
        }
    }


    /**
     * metodo de encargado de validar y agregar los cupones
     * @param nroCupon
     * @author Javier Callo Quispe
     * @since 04.03.2009
     */
    private void validarAgregarCupon(String nroCupon) {
        Map mapCupon;
        // KMONCADA 22.02.2016 NUEVO FORMATO DE CUPON
        String codCampCupon = UtilityVentas.obtenerCampaniaDeCupon(nroCupon);
        //String codCampCupon = nroCupon.substring(0, 5);
        try {
            mapCupon = DBVentas.getDatosCupon(codCampCupon, nroCupon,VariablesFidelizacion.vDniCliente);
            mapCupon.put("COD_CUPON", nroCupon);
        } catch (SQLException e) {
            log.debug("ocurrio un error al obtener datos del cupon:" + nroCupon + " error:" + e);
            FarmaUtility.showMessage(this, "Ocurrio un error al obtener datos del cupon.\n" +
                    e.getMessage() +
                    "\n Int�ntelo Nuevamente\n si persiste el error comuniquese con operador de sistemas.",
                    txtDescProdOculto);
            return;
        }
        log.debug("datosCupon:" + mapCupon);
        //Se verifica si el cupon ya fue agregado tambien verifica si ya existe la campa�a
        if (UtilityVentas.existeCuponCampana(nroCupon, this, txtDescProdOculto))
            return;

        String indMultiuso = mapCupon.get("IND_MULTIUSO").toString().trim();
        boolean obligarIngresarFP = isFormaPagoUso_x_Cupon(codCampCupon); //saber si la campa�a pide forma de pago
        boolean yaIngresoFormaPago = false;


        //jquispe
        //String vIndFidCupon = "N";//obtiene el ind fid -- codCampCupon
        String vIndFidCupon = UtilityFidelizacion.getIndfidelizadoUso(codCampCupon);
        //if(vIndFidCupon.trim().equalsIgnoreCase("S")){
        //inicio dubilluz 09.06.2011
        if (vIndFidCupon.trim().equalsIgnoreCase("S")) {
            if (VariablesFidelizacion.vDniCliente.trim().length() == 0) {
                funcionF12(codCampCupon,true);
                yaIngresoFormaPago = true;
            }

            //fin daubilluz 09.06.2011
            if (VariablesFidelizacion.vDniCliente.trim().length() >
                0) /*&&vIndFidCupon.trim().equalsIgnoreCase("S")*/ {

                //Consultara si es necesario ingresar forma de pago x cupon
                // si es necesario solicitar� el mismo.
                if (obligarIngresarFP) {
                    if (!yaIngresoFormaPago)
                        funcionF12(codCampCupon,true);

                }
                //validacion de cupon en base de datos vigente y to_do lo demas
                if (!UtilityVentas.validarCuponEnBD(nroCupon, this, txtDescProdOculto, indMultiuso,
                                                    VariablesFidelizacion.vDniCliente, VariablesConvenioBTLMF.vCodConvenio)) {
                    return;
                } else {
                    evaluaFormaPagoFidelizado();
                    //agregando cupon al listado
                    
                    //INI AOVIEDO 27/06/2017
                    String tipCampanaCupon = mapCupon.get("TIPO_CAMPANA").toString();
                    actualizarCuponesCampanasR(tipCampanaCupon, mapCupon);
                    //FIN AOVIEDO 27/06/2017
                    
                    //***** AOVIEDO 18/06/19 *******/
                   /* if(mapCupon.get("NUM_DOC_IDEN_CUPON").toString().trim().length() != 0){
                        if(!VariablesFidelizacion.vDniCliente.equalsIgnoreCase(mapCupon.get("NUM_DOC_IDEN_CUPON").toString())){
                            FarmaUtility.showMessage(this, "Cup�n no pertenece al cliente fidelizado.\nPor favor fidelizar nuevamente.", null);
                            return;
                        }
                    }*/
                    //***** AOVIEDO 18/06/19 *******/
                    
                    VariablesVentas.vArrayList_Cupones.add(mapCupon);
//[5]
                    //24.06.2011
                    //Para Reinicializar todas las formas de PAGO.
                    UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);
                    VariablesVentas.vMensCuponIngre = "Se ha agregado el cup�n " + nroCupon + " de la Campa�a\n" +
                            mapCupon.get("DESC_CUPON").toString() + ".";

                    //se comento lo que sigue ya que no se tiene claro pa que se usa
                    /*VariablesCampana.vCodCupon = nroCupon;
                               if(VariablesCampana.vListaCupones.size()>0)
                                       VariablesCampana.vFlag = true;
                               VariablesCampana.vDescCamp = mapCupon.get("DESC_CUPON").toString();*/
                    FarmaUtility.showMessage(this, VariablesVentas.vMensCuponIngre, txtDescProdOculto);
                }
                txtDescProdOculto.setText("");
                lblCuponIngr.setText(VariablesVentas.vMensCuponIngre);
            }

        } else {
            //PIDE SI necesita ES FORMA DE PAGO
            //Falta inigresar forma de pago en OTRA PANTALLA
            if (obligarIngresarFP) {
                if (!yaIngresoFormaPago)
                    funcionF12(codCampCupon,true);

            }

            if (!UtilityVentas.validarCuponEnBD(nroCupon, this, txtDescProdOculto, indMultiuso, "", VariablesConvenioBTLMF.vCodConvenio)) {
                return;
            } else {
                evaluaFormaPagoFidelizado(); //SETETA el label de TARJETA UNICA
                //agregando cupon al listado
                VariablesVentas.vArrayList_Cupones.add(mapCupon);
//[6]
                //24.06.2011
                //Para Reinicializar todas las formas de PAGO.
                /*
                    UtilityFidelizacion.operaCampa�asFidelizacion("");
                */
                VariablesVentas.vMensCuponIngre = "Se ha agregado el cup�n " + nroCupon + " de la Campa�a\n" +
                        mapCupon.get("DESC_CUPON").toString() + ".";

                FarmaUtility.showMessage(this, VariablesVentas.vMensCuponIngre, txtDescProdOculto);
            }
            txtDescProdOculto.setText("");
            lblCuponIngr.setText(VariablesVentas.vMensCuponIngre);
        }
        //calcular totales pedido despues de haber agregado un nuevo cupon
        //calculaTotalesPedido();
        neoOperaResumenPedido();
    }

    public boolean tieneDatoFormaPagoFidelizado() {
        if (VariablesFidelizacion.vIndUsoEfectivo.trim().equalsIgnoreCase("S") ||
            (VariablesFidelizacion.vIndUsoTarjeta.trim().equalsIgnoreCase("S") &&
             VariablesFidelizacion.vCodFPagoTarjeta.trim().length() > 0))
            return true;
        else
            return false;
    }

    /**
     * Se muestra el mensaje personalizado al usuario.
     */
    private void muestraMensajeUsuario() {
        ArrayList vAux = new ArrayList();
        String mensaje;
        try {
            DBUsuarios.getMensajeUsuario(vAux, FarmaVariables.vNuSecUsu);
            if (vAux.size() > 0) {
                log.debug("Se muestra mensaje al usuario");
                mensaje = FarmaUtility.getValueFieldArrayList(vAux, 0, 0);
                DlgMensajeUsuario dlgMensajeUsuario = new DlgMensajeUsuario(myParentFrame, "", true);
                dlgMensajeUsuario.setMensaje(mensaje);
                dlgMensajeUsuario.setVisible(true);
                DBUsuarios.actCantVeces(FarmaVariables.vNuSecUsu);
            }
        } catch (SQLException e) {
            log.error(null, e);
        }

    }

    /**
     * Valida monto de cupones del pedido
     * se reempn el nuevo metodo validaCampsMontoNetoPedido
     * @author dubilluz
     * @since  23.07.2008, modif 11.03.2009
     * @param  pNetoPedido
     * @deprecated
     * @return boolean
     */
    private boolean validaUsoCampanaMonto(String pNetoPedido) {
        String vCodCupon;
        String vCodCamp;
        String vIndTipoCupon;
        double vValorCupon;

        double vValorAcumuladoCupones = 0.0;
        double vNetoPedido = FarmaUtility.getDecimalNumber(pNetoPedido.trim());

        String vIndValido;

        ArrayList cupon = new ArrayList();
        for (int j = 0; j < VariablesVentas.vArrayList_Cupones.size(); j++) {
            cupon = (ArrayList)VariablesVentas.vArrayList_Cupones.get(j);
            vCodCupon = cupon.get(0).toString();
            vCodCamp = cupon.get(1).toString();
            vIndTipoCupon = cupon.get(2).toString();
            if (vIndTipoCupon.equalsIgnoreCase(ConstantsVentas.TIPO_MONTO)) {
                vValorCupon = FarmaUtility.getDecimalNumber(cupon.get(3).toString());
                vIndValido = cupon.get(8).toString();
                if (vIndValido.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {

                    vValorAcumuladoCupones += vValorCupon;

                }
            }

        }

        // VALIDANDO EL MONTO DEL PEDIDO CON LA SUMA DE CUPONES
        boolean indNoUsaCupones = false;
        if (vValorAcumuladoCupones > 0) {
            if (vNetoPedido < vValorAcumuladoCupones) {
                if (JConfirmDialog.rptaConfirmDialog(this,
                                                                                "El monto del pedido es menor que" +
                                                                                " la suma de los cupones.\n" +
                        "�Desea generar el pedido y " + "perder la diferencia?")) {
                    for (int j = 0; j < VariablesVentas.vArrayList_Cupones.size(); j++) {
                        cupon = (ArrayList)VariablesVentas.vArrayList_Cupones.get(j);
                        vCodCupon = cupon.get(0).toString();
                        vCodCamp = cupon.get(1).toString();
                        vIndTipoCupon = cupon.get(2).toString();
                        if (vIndTipoCupon.equalsIgnoreCase(ConstantsVentas.TIPO_MONTO)) {
                            vValorCupon = FarmaUtility.getDecimalNumber(cupon.get(3).toString());
                            vIndValido = cupon.get(8).toString();
                            if (vIndValido.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {

                                if (indNoUsaCupones)
                                    cupon.set(8, FarmaConstants.INDICADOR_N);
                                else {
                                    vNetoPedido -= vValorCupon;
                                    if (vNetoPedido <= 0)
                                        indNoUsaCupones = true;
                                    log.debug("vNetoPedido " + vNetoPedido);
                                }

                            }
                        }

                    }
                    log.debug("VariablesVentas.vArrayList_Cupones " + VariablesVentas.vArrayList_Cupones);
                    return true;
                } else
                    return false;
            } else
                return true;
        } else
            return true;


    }

    private void validarClienteTarjeta(String cadena, boolean isTarjetaAntigua) {
        if(!UtilityPuntos.isActivoFuncionalidad()){
            if (VariablesVentas.vEsPedidoConvenio ||
                (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {
            //if (VariablesVentas.vEsPedidoConvenio) {
                FarmaUtility.showMessage(this, "No puede agregar una tarjeta a un " + "pedido por convenio.",
                                         txtDescProdOculto);
                txtDescProdOculto.setText("");
                return;
            }
        }
        // KMONCADA 2015.02.27 REALIZA LA CONSULTA DE LOS DATOS DE RENIEC.
        try{
            String nroDni = DBFidelizacion.getDniClienteFidelizado(cadena);
            if(nroDni != null){
                ArrayList array = new ArrayList();
                DBFidelizacion.buscarTarjetasXDNI(array, nroDni, null);
            }
        }catch(Exception ex){
            log.error("",ex);
        }
        UtilityFidelizacion.validaLecturaTarjeta(cadena, myParentFrame);
        if (VariablesFidelizacion.vDataCliente.size() > 0) {
            ArrayList array = (ArrayList)VariablesFidelizacion.vDataCliente.get(0);
            UtilityFidelizacion.setVariablesDatos(array);
            // KMONCADA 12.02.2015 FUNCIONALIDAD DE PROGRAMA DE PUNTOS PARA TARJETAS ANTERIORES
            boolean isValidaOrbis = true;
            try{
                if(UtilityPuntos.isActivoFuncionalidad()){
                    if(isTarjetaAntigua){
                        // KMONCADA 22.05.2015 VALIDA SI EL NUMERO DE TARJETA SE ENCUENTRA EN EL RANGO DE TARJETA
                        // PERO NO PERTENECE AL RANGO DE TARJETAS DE PUNTOS
                        //String aux = DBPuntos.evaluaTarjeta(cadena);
                        //if("S".equalsIgnoreCase(aux)){
                        if(UtilityPuntos.isTarjetaOtroPrograma(cadena, false)){
                            isValidaOrbis = false;
                        }
                    }
                    if(isTarjetaAntigua && /*!pIngresoComprobanteManual &&*/ isValidaOrbis){
                        txtDescProdOculto.setText(cadena);
                        UtilityPuntos.consultarTarjetaOrbis(myParentFrame, this, txtDescProdOculto, false, true);
                    }
                }
            }catch(Exception ex){
                log.error("",ex);
                FarmaUtility.showMessage(this, "ERROR EN PROGRAMA DE PUNTOS Y LEALTAD", null);
            }
            //fin jcallo 02.10.2008
            if (VariablesFidelizacion.vIndExisteCliente) {
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                }else{
                    FarmaUtility.showMessage(this, "Bienvenido \n" +
                        VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente + " " +
                        VariablesFidelizacion.vApeMatCliente + "\n" +
                        "DNI: " + VariablesFidelizacion.vDniCliente, null);
                }
            } else if (VariablesFidelizacion.vIndAgregoDNI.trim().equalsIgnoreCase("0")) {
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                }else{
                    FarmaUtility.showMessage(this, "Se agrego el DNI :" + VariablesFidelizacion.vDniCliente, null);
                }
            } else if (VariablesFidelizacion.vIndAgregoDNI.trim().equalsIgnoreCase("2")) {
                /*FarmaUtility.showMessage(this,
                                         "Cliente encontrado con DNI " + VariablesFidelizacion.vDniCliente,
                                         null);*/
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                }else{
                    FarmaUtility.showMessage(this, "Bienvenido \n" +
                        VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente + " " +
                        VariablesFidelizacion.vApeMatCliente + "\n" +
                        "DNI: " + VariablesFidelizacion.vDniCliente, null);
                }
            } else if (VariablesFidelizacion.vIndAgregoDNI.trim().equalsIgnoreCase("1")) {
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                }else{
                    FarmaUtility.showMessage(this,
                                         "Se actualizaron los datos del DNI :" + VariablesFidelizacion.vDniCliente,
                                         null);
                }
            }

            lblCliente.setText(VariablesFidelizacion.vNomCliente); /*+" "
                               +VariablesFidelizacion.vApePatCliente+" "
                               +VariablesFidelizacion.vApeMatCliente);*/


            VariablesVentas.vArrayList_CampLimitUsadosMatriz = new ArrayList();
            //Ya no validara en Matriz
            //14.04.2009 DUBILLUZ
            //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz
            /*log.debug("**************************************");
            VariablesFidelizacion.vIndConexion = FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ,
            																	FarmaConstants.INDICADOR_N);
            log.debug("************************");
            if(VariablesFidelizacion.vIndConexion.equals(FarmaConstants.INDICADOR_S)){//VER SI HAY LINEA CON MATRIZ
            	log.debug("jjccaalloo:VariablesFidelizacion.vDniCliente"+VariablesFidelizacion.vDniCliente);
            	VariablesVentas.vArrayList_CampLimitUsadosMatriz = CampLimitadasUsadosDeMatrizXCliente(VariablesFidelizacion.vDniCliente);
            	log.debug("******VariablesVentas.vArrayList_CampLimitUsadosMatriz"+VariablesVentas.vArrayList_CampLimitUsadosMatriz);
            }
            */
            //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz

            //operaResumenPedido(); REEMPLAZADO POR EL DE ABAJO
            neoOperaResumenPedido(); //nuevo metodo jcallo 10.03.2009


            VariablesFidelizacion.vIndAgregoDNI = "";
            //dubilluz 19.07.2011 - inicio
            if (VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim().length() > 0) {
                UtilityFidelizacion.grabaTarjetaUnica(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim(),
                                                      VariablesFidelizacion.vDniCliente);
            }
            //dubilluz 19.07.2011 - fin
        } else {
            //jcallo 02.10.2008
            lblCliente.setText(VariablesFidelizacion.vNomCliente); /*+" "
                               +VariablesFidelizacion.vApePatCliente+" "
                               +VariablesFidelizacion.vApeMatCliente);*/
            //fin jcallo 02.10.2008
        }
        txtDescProdOculto.setText("");
    }
    /*
    private void mostrarBuscarTarjetaPorDNI() {

        DlgFidelizacionBuscarTarjetas dlgBuscar =
            new DlgFidelizacionBuscarTarjetas(myParentFrame, "", true);
        dlgBuscar.setVisible(true);
        log.debug("vv:" + FarmaVariables.vAceptar);
        if (FarmaVariables.vAceptar) {
            log.debug("despues de haber encontrado el cliente");
            log.debug("CLIENTE......:" + VariablesFidelizacion.vDataCliente);
            ArrayList array =
                (ArrayList)VariablesFidelizacion.vDataCliente.get(0);
            UtilityFidelizacion.setVariablesDatos(array);
            FarmaUtility.showMessage(this, "Bienvenido \n" +
                    VariablesFidelizacion.vNomCliente + " " +
                    VariablesFidelizacion.vApePatCliente + " " +
                    VariablesFidelizacion.vApeMatCliente + "\n" +
                    "DNI: " + VariablesFidelizacion.vDniCliente, null);
            //dubilluz 19.07.2011 - inicio
            if(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim().length()>0){
               UtilityFidelizacion.grabaTarjetaUnica(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim(),VariablesFidelizacion.vDniCliente);
            }
            //dubilluz 19.07.2011 - fin
            //jcallo 02.10.2008
            lblCliente.setText(VariablesFidelizacion.vNomCliente);
            //fin jcallo 02.10.2008
            //DAUBILLUZ -- Filtra los DNI anulados
            //25.05.2009
            VariablesFidelizacion.vDNI_Anulado =
                    UtilityFidelizacion.isDniValido(VariablesFidelizacion.vDniCliente);
            VariablesFidelizacion.vAhorroDNI_x_Periodo =
                    UtilityFidelizacion.getAhorroDNIxPeriodoActual(VariablesFidelizacion.vDniCliente,VariablesFidelizacion.vNumTarjeta);
            VariablesFidelizacion.vMaximoAhorroDNIxPeriodo =
                    UtilityFidelizacion.getMaximoAhorroDnixPeriodo(VariablesFidelizacion.vDniCliente,VariablesFidelizacion.vNumTarjeta);


            log.info("Variable de DNI_ANULADO: " +
                     VariablesFidelizacion.vDNI_Anulado);
            log.info("Variable de vAhorroDNI_x_Periodo: " +
                     VariablesFidelizacion.vAhorroDNI_x_Periodo);
            log.info("Variable de vMaximoAhorroDNIxPeriodo: " +
                     VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
            setMensajeDNIFidelizado();
            if (VariablesFidelizacion.vDNI_Anulado) {
                if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0)
                    UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta);
                //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz
                log.debug("**************************************");
                //VariablesFidelizacion.vIndConexion = FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ,FarmaConstants.INDICADOR_N);
                VariablesFidelizacion.vIndConexion =
                        FarmaConstants.INDICADOR_N;

                log.debug("************************");
                //      if(VariablesFidelizacion.vIndConexion.equals(FarmaConstants.INDICADOR_S)){//VER SI HAY LINEA CON MATRIZ   // JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local
                log.debug("jjccaalloo:VariablesFidelizacion.vDniCliente" +
                          VariablesFidelizacion.vDniCliente);
                VariablesVentas.vArrayList_CampLimitUsadosMatriz =
                        CampLimitadasUsadosDeMatrizXCliente(VariablesFidelizacion.vDniCliente);
                log.debug("******VariablesVentas.vArrayList_CampLimitUsadosMatriz" +
                          VariablesVentas.vArrayList_CampLimitUsadosMatriz);
                //     } // JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local


                //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz
            } else {
                log.info("Cliente esta invalidado para descuento...");
            }

            //operaResumenPedido(); REEMPLAZADO POR EL DE ABAJO
            neoOperaResumenPedido(); //nuevo metodo jcallo 10.03.2009


        }
        FarmaUtility.moveFocus(txtDescProdOculto);
    }*/

    /**
     * el procedimiento en  BASE DE DATOS esta haciendo commit cuando no debe
     * @author  author JCALLO
     * @since   09.10.08
     * @param array
     */
    private void actualizarAhorroProdVentaDet(Map mProdDcto) {
        try {
            DBVentas.guardaDctosDetPedVta(mProdDcto.get("COD_PROD").toString(),
                                          mProdDcto.get("COD_CAMP_CUPON").toString(),
                                          mProdDcto.get("VALOR_CUPON").toString(), mProdDcto.get("AHORRO").toString(),
                                          mProdDcto.get("DCTO_AHORRO").toString(),
                                          mProdDcto.get("SEC_PED_VTA_DET").toString());
            //FarmaUtility.aceptarTransaccion();
        } catch (SQLException e) {
            log.error("", e);
        }
    }

    /* *************************************************************************** */
    //Inicio de Campa�as Acumuladas DUBILLUZ 18.12.2008

    /**
     * @author Dubilluz
     * @since  17.12.2008
     * @param  pNumPed,pDniCli
     */
    private void procesoCampa�asAcumuladas(String pNumPed, String pDniCli) {
        if (pDniCli.trim().length() == 0) {
            log.debug("No es fidelizado...");
            return;
        }
        log.info("inicio operaAcumulacionCampa�as");
        //--1.Se procesa si acumula unidades
        operaAcumulacionCampa�as(pNumPed, pDniCli);
        log.info("FIN operaAcumulacionCampa�as");
        // Se inserta los pedidos de campa�as acumuladas en el local a Matriz
        //--2.Se valida si hay linea
        String pIndLinea = FarmaConstants.INDICADOR_N;
        /*
                             * FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ,
                                                           FarmaConstants.INDICADOR_N);
                             */
        //--3.No hay linea to_do el proceso concluye y solo acumula
        log.info("pIndLinea:" + pIndLinea);

        if (pIndLinea.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
            FarmaConnectionRemoto.closeConnection();
            log.info("PASO 1");
        }
        /*
        else{
        log.info("Evalua Campa�a Acumulada");
          // Se inserta los pedidos de campa�as acumuladas en el local a Matriz
          enviaUnidadesAcumuladasLocalMatriz(pNumPed,pDniCli,pIndLinea);

          // Se opera el intento de canje y se acumula en el local
          operaRegaloCampa�aAcumulada(pNumPed,pDniCli);

        }
        */
        log.info("INICIO operaRegaloCampa�aAcumulada");
        // Se opera el intento de canje y se acumula en el local
        operaRegaloCampa�aAcumulada(pNumPed, pDniCli);
        log.info("FIN operaRegaloCampa�aAcumulada");
        //Actualiza los datos en la cabecera si acumulo o gano algun canje para poder
        //indentificar si el pedido es de fidelizado y campa�a acumulada
        log.debug("Actualiza datos");
        actualizaDatoPedidoCabecera(pNumPed, pDniCli);

    }

    private void actualizaDatoPedidoCabecera(String pNumPed, String pDniCli) {
        try {
            DBVentas.actualizaPedidoXCampanaAcumulada(pNumPed, pDniCli);
        } catch (SQLException e) {
            log.error("", e);
        }
    }

    /**
     *
     * @param pNumPed
     * @param pDniCli
     */
    private void operaAcumulacionCampa�as(String pNumPed, String pDniCli) {
        try {
            DBVentas.operaAcumulaUnidadesCampa�a(pNumPed, pDniCli);
        } catch (SQLException e) {
            log.error("", e);
        }
    }


    /**
     * Envia unidades acumulaciones local matriz
     * @author Dubilluz
     * @param  pNumPedido
     * @param  pDniCli
     * @param  pIndLinea
     */
    private void enviaUnidadesAcumuladasLocalMatriz(String pNumPedido, String pDniCli, String pIndLinea) {
        ArrayList pListaAcumulados = new ArrayList();
        int COL_DNI = 0, COL_CIA = 1, COL_COD_CAMP = 2, COL_LOCAL = 3, COL_NUM_PED = 4, COL_FECH_PED =
            5, COL_SEC_PED_VTA = 6, COL_COD_PROD = 7, COL_CANT_PED = 8, COL_VAL_FRAC_PED = 9, COL_ESTADO =
            10, COL_VAL_FRAC_MIN = 11, COL_USU_CREA = 12, COL_CANT_RESTANTE = 13;

        boolean indInsertMatriz = false;

        String pDni, pCodCia, pCodCamp, pCodLocal, pNumPed, pFechPed, pSecDet, pCodProd, pCantPed, pValFrac, pEstado, pValFracMin, pUsuCrea, pCantRestante;

        try {
            DBVentas.getListaUnidadesAcumuladas(pListaAcumulados, pNumPedido, pDniCli);

            if (pListaAcumulados.size() > 0) {
                /*
                if(pIndLinea.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)){
                        for(int i=0;i<pListaAcumulados.size();i++){

                            pDni  = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_DNI).trim();
                            pCodCia = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_CIA).trim();
                            pCodCamp = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_COD_CAMP).trim();
                            pCodLocal = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_LOCAL).trim();
                            pNumPed = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_NUM_PED).trim();
                            pFechPed = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_FECH_PED).trim();
                            pSecDet = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_SEC_PED_VTA).trim();
                            pCodProd = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_COD_PROD).trim();
                            pCantPed = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_CANT_PED).trim();
                            pValFrac = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_VAL_FRAC_PED).trim();
                            pEstado = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_ESTADO).trim();
                            pValFracMin = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_VAL_FRAC_MIN).trim();
                            pUsuCrea = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_USU_CREA).trim();
                            pCantRestante = FarmaUtility.getValueFieldArrayList(pListaAcumulados,i,COL_CANT_RESTANTE).trim();
                           DBVentas.insertaAcumuladosEnMatriz(
                                                              pDni, pCodCia, pCodCamp,
                                                              pCodLocal, pNumPed, pFechPed,
                                                              pSecDet, pCodProd, pCantPed,
                                                              pValFrac, pEstado, pValFracMin,
                                                              pUsuCrea,pCantRestante
                                                             );
                            indInsertMatriz = true;
                            log.debug("..envia a matriz..");


                        }

              a matriz
                                  // Si envio DBVentas.actualizaProcesoMatrizHistorico(pNumPedido,pDniCli,FarmaConstants.INDICADOR_S);
                    }
                    else{
                        log.debug("Acumula unidades y no envia a Matriz");
                        // No envia a Matriz
                        DBVentas.actualizaProcesoMatrizHistorico(pNumPedido,pDniCli,FarmaConstants.INDICADOR_N);
                    }
                 */
                log.debug("Acumula unidades y no envia a Matriz");
                DBVentas.actualizaProcesoMatrizHistorico(pNumPedido, pDniCli, FarmaConstants.INDICADOR_N);
            } else
                log.debug("No acumulo ninguna unidad...");
        } catch (SQLException e) {
            log.error("", e);
        } finally {
            if (indInsertMatriz) // -- indica de linea
            {
                if (pIndLinea.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {

                    FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_S);
                }
            }
        }

    }

    private void operaRegaloCampa�aAcumulada(String pNumPed, String pDniCli) {
        ArrayList listaPedOrigen = new ArrayList();
        int COL_COD_CAMP = 0, COL_LOCAL_ORIGEN = 1, COL_NUM_PED_ORIGEN = 2, COL_SEC_PED_ORIGEN =
            3, COL_COD_PED_ORIGEN = 4, COL_CANT_USO = 5, COL_VAL_FRAC_MIN = 6;

        String pCodCamp, pCodLocalOrigen, pNumPedOrigen, pSecDetOrigen, pCodProdOrigen, pCantUsoOrigen, pValFracMinOrigen;


        try {
            DBVentas.operaIntentoRegaloCampa�aAcumulada(listaPedOrigen, pNumPed, pDniCli);

            ArrayList listaCAEfectivas = new ArrayList();
            boolean vIndAgregaElmento = true;

            //1. Opera el listado auxiliares y se obtienen las campa�as que regalaran
            for (int j = 0; j < listaPedOrigen.size(); j++) {
                vIndAgregaElmento = true;
                pCodCamp = FarmaUtility.getValueFieldArrayList(listaPedOrigen, j, COL_COD_CAMP).trim();
                for (int k = 0; k < listaCAEfectivas.size(); k++) {
                    if (pCodCamp.trim().equalsIgnoreCase(listaCAEfectivas.get(k).toString().trim())) {
                        vIndAgregaElmento = false;
                        break;
                    }
                }
                if (vIndAgregaElmento)
                    listaCAEfectivas.add(pCodCamp);
            }
            log.info("Lista CAEfectivas " + listaCAEfectivas);
            //2. Agrega Productos regalo de cada campa�as
            for (int i = 0; i < listaCAEfectivas.size(); i++) {
                //obtiene la campa�a para a�adir los regalos
                pCodCamp = listaCAEfectivas.get(i).toString().trim();
                //DBVentas.a�adeRegaloCampa�a(pNumPed,pDniCli,pCodCamp); antes
                DBVentas.a�adeRegaloCampa�a_02(pNumPed, pDniCli, pCodCamp); //ASOSA, 07.07.2010
            }


            //se obtiene las campa�as de regalo efectiva realizadas
            ArrayList listaCanjesRealizados = new ArrayList();
            DBCaja.getPedidosCanj(pDniCli, pNumPed, listaCanjesRealizados);
            ArrayList listaCanjesPosibles = (ArrayList)listaCAEfectivas.clone();
            String codCampana = "";
            for (int i = 0; i < listaCanjesRealizados.size(); i++) {

                codCampana = FarmaUtility.getValueFieldArrayList(listaCanjesRealizados, i, 0);

                for (int j = 0; j < listaCanjesPosibles.size(); j++) {
                    //obtiene la campa�a para a�adir los regalos
                    pCodCamp = listaCanjesPosibles.get(j).toString().trim();
                    if (pCodCamp.trim().equalsIgnoreCase(codCampana.trim())) {
                        listaCanjesPosibles.remove(j);
                        break;
                    }
                }

            }
            log.debug("Camapa�as no realizadas:" + listaCanjesPosibles);
            ////

            //3. Agrega los pedido de productos origen
            /*
            a�adePedidosOrigenCanje(
                                                            String pDniCli,
                                                            String pCodCamp,
                                                            String pNumPedidoVenta,
                                                            String pCodLocalOrigen,
                                                            String pNumPedOrigen,
                                                            String pSecPedOrigen,
                                                            String pCodProdOrigen,
                                                            String pCantUsoOrigen,
                                                            String pValFracMinOrigen
             * */
            boolean vEfectivo = true;
            for (int i = 0; i < listaPedOrigen.size(); i++) {
                //obtiene la campa�a para a�adir los regalos
                pCodCamp = FarmaUtility.getValueFieldArrayList(listaPedOrigen, i, COL_COD_CAMP).trim();
                pCodLocalOrigen = FarmaUtility.getValueFieldArrayList(listaPedOrigen, i, COL_LOCAL_ORIGEN).trim();
                pNumPedOrigen = FarmaUtility.getValueFieldArrayList(listaPedOrigen, i, COL_NUM_PED_ORIGEN).trim();
                pSecDetOrigen = FarmaUtility.getValueFieldArrayList(listaPedOrigen, i, COL_SEC_PED_ORIGEN).trim();
                pCodProdOrigen = FarmaUtility.getValueFieldArrayList(listaPedOrigen, i, COL_COD_PED_ORIGEN).trim();
                pCantUsoOrigen = FarmaUtility.getValueFieldArrayList(listaPedOrigen, i, COL_CANT_USO).trim();
                pValFracMinOrigen = FarmaUtility.getValueFieldArrayList(listaPedOrigen, i, COL_VAL_FRAC_MIN).trim();

                for (int j = 0; j < listaCanjesPosibles.size(); j++) {
                    codCampana = listaCanjesPosibles.get(j).toString().trim();
                    if (pCodCamp.trim().equalsIgnoreCase(codCampana.trim())) {
                        vEfectivo = false;
                        break;
                    }
                }

                if (vEfectivo) {
                    DBVentas.a�adePedidosOrigenCanje(pDniCli, pCodCamp, pNumPed, pCodLocalOrigen, pNumPedOrigen,
                                                     pSecDetOrigen, pCodProdOrigen, pCantUsoOrigen, pValFracMinOrigen);
                }

            }

            //Se revierte los canjes posibles de la camapa�a que se realizo en matriz
            //pero que no se hizo por falta de stock
            for (int j = 0; j < listaCanjesPosibles.size(); j++) {
                codCampana = listaCanjesPosibles.get(j).toString().trim();
                //Se revierten los canjes que eran posibles en matriz
                //pero que no se realizaron en el local
                //posiblemente por erro de fraccion, stock.
                DBVentas.revertirCanjeMatriz(pDniCli, codCampana, pNumPed);
            }

        } catch (SQLException e) {
            log.error("", e);
        }

    }

    /**
     * metodo encargado de registrar y/o asociar cliente a las campanias de acumulacion
     * @param
     * @author Javier Callo Quispe
     * @since 15.12.2008
     */
    private void asociarCampAcumulada(String codProd) {
        VariablesCampAcumulada.vCodProdFiltro = codProd;
        log.info("VariablesCampAcumulada.vCodProdFiltro:" + VariablesCampAcumulada.vCodProdFiltro);

        FarmaVariables.vAceptar = false;

        //lanzar dialogo las campa�as por asociar
        DlgListaCampAcumulada dlgListaCampAcumulada = new DlgListaCampAcumulada(myParentFrame, "", true);
        dlgListaCampAcumulada.setVisible(true);
        //cargas las campa�as de fidelizacion
        if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
            log.debug("INVOCANDO CARGAR CAMPA�AS DEL CLIENTES ..:" + VariablesFidelizacion.vNumTarjeta);
            UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta.trim(), VariablesConvenioBTLMF.vCodConvenio);
            log.debug("FIN INVOCANDO CARGAR CAMPA�AS DEL CLIENTES ..");

            /**mostranto el nombre del cliente **/
            lblCliente.setText(VariablesFidelizacion.vNomCliente); /*+" "
                                   +VariablesFidelizacion.vApePatCliente+" "
                                   +VariablesFidelizacion.vApeMatCliente);*/
            //VariablesFidelizacion.vApeMatCliente = Variables

            log.debug("imprmiendo todas las variables de fidelizacion");
            log.debug("VariablesFidelizacion.vApeMatCliente:" + VariablesFidelizacion.vApeMatCliente);
            log.debug("VariablesFidelizacion.vApePatCliente:" + VariablesFidelizacion.vApePatCliente);
            log.debug("VariablesFidelizacion.vCodCli:" + VariablesFidelizacion.vCodCli);
            log.debug("VariablesFidelizacion.vCodGrupoCia:" + VariablesFidelizacion.vCodGrupoCia);
            log.debug("VariablesFidelizacion.vDataCliente:" + VariablesFidelizacion.vDataCliente);
            log.debug("VariablesFidelizacion.vDireccion:" + VariablesFidelizacion.vDireccion);
            log.debug("VariablesFidelizacion.vDniCliente:" + VariablesFidelizacion.vDniCliente);
            log.debug("VariablesFidelizacion.vDocValidos:" + VariablesFidelizacion.vDocValidos);
            log.debug("VariablesFidelizacion.vEmail:" + VariablesFidelizacion.vEmail);
            log.debug("VariablesFidelizacion.vFecNacimiento:" + VariablesFidelizacion.vFecNacimiento);
            log.debug("VariablesFidelizacion.vIndAgregoDNI:" + VariablesFidelizacion.vIndAgregoDNI);
            log.debug("VariablesFidelizacion.vIndConexion:" + VariablesFidelizacion.vIndConexion);
            log.debug("VariablesFidelizacion.vIndEstado:" + VariablesFidelizacion.vIndEstado);
            log.debug("VariablesFidelizacion.vIndExisteCliente:" + VariablesFidelizacion.vIndExisteCliente);
            log.debug("VariablesFidelizacion.vListCampa�asFidelizacion:" +
                      VariablesFidelizacion.vListCampa�asFidelizacion);
            log.debug("VariablesFidelizacion.vNomCliente:" + VariablesFidelizacion.vNomCliente);
            log.debug("VariablesFidelizacion.vNomClienteImpr:" + VariablesFidelizacion.vNomClienteImpr);
            log.debug("VariablesFidelizacion.vNumTarjeta:" + VariablesFidelizacion.vNumTarjeta);
            log.debug("VariablesFidelizacion.vSexo:" + VariablesFidelizacion.vSexo);
            log.debug("VariablesFidelizacion.vSexoExists:" + VariablesFidelizacion.vSexoExists);
            log.debug("VariablesFidelizacion.vTelefono:" + VariablesFidelizacion.vTelefono);
            log.debug("fin de imprmir todas las variables de fidelizacion");

        }


    }

    /**
     * obtener todas las campa�as de fidelizacion automaticas usados en el pedido
     *
     * */
    private ArrayList CampLimitadasUsadosDeMatrizXCliente(String dniCliente) {
        ArrayList listaCampLimitUsadosMatriz = new ArrayList();
        try {
            //listaCampLimitUsadosMatriz = DBCaja.getListaCampUsadosMatrizXCliente(dniCliente);
            listaCampLimitUsadosMatriz =
                    DBCaja.getListaCampUsadosLocalXCliente(dniCliente); //DBCaja.getListaCampUsadosMatrizXCliente(dniCliente); // JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local
            if (listaCampLimitUsadosMatriz.size() > 0) {
                listaCampLimitUsadosMatriz = (ArrayList)listaCampLimitUsadosMatriz.get(0);
            }
            log.debug("listaCampLimitUsadosMatriz listaCampLimitUsadosMatriz ===> " + listaCampLimitUsadosMatriz);
        } catch (Exception e) {
            log.error("",e);
        }
        return listaCampLimitUsadosMatriz;
    }

    /**
     * metodo nuevo de calculo y/o aplicacion de descuentos de acuerdo
     * a las campa�as o cupones usados en el pedido.
     * @author Javier Callo Quispe
     * @since   05/03/2009
     * **/
     public boolean calculoDctosPedidoXCupones() {
         //KMONCADA 12.10.2016 [CONVENIOS CON CAMPA�AS]
         ArrayList<Integer> lstProdAplicaPrecioConvenio = new ArrayList<Integer>();
         // DUBILLUZ 19.08.2015
         //FarmaConnection.closeConnection();
         //El ahorro acumulado del pedido se coloca en 0
         //para reiniciar todo el calculo.
         double ahorroXCupon=0;

         VariablesFidelizacion.vAhorroDNI_Pedido = 0;

         log.debug("JCALLO: nuevo metodo de calculo de descuento");
         long timeIni = System.currentTimeMillis();

         List listaCodProds = new ArrayList(); //listaTemporal para tener el listado de codigo de productos
         Map mapaAux;
         Map mapaValidarAcumula; //AOVIEDO 17/05/2017
         String codProdAux = "";
         String codCampAux = "";
         //dubilluz 21.06.2011
         double totalProducto = 0.0;
         ///////////////////////////////////////////////////////////////////
         VariablesVentas.vListProdExcluyeAcumAhorro = new ArrayList();
         VariablesVentas.vListProdExcluyeAcumAhorro.clear();
         ///////////////////////////////////////////////////////////////////
         //Limpiar las marcas de prod cupon
         for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {     
                BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
             /**agregar al arreglo de cod_productos*/
             mapaAux = new HashMap();
             codProdAux = bean.getVCodProd();
             totalProducto = FarmaUtility.getDecimalNumber(
                                                           //bean.getPPREC_VTA_UNIT_NVO() // dubilluz 2016.12.29
                                                           bean.getVPrecioBase()
                                                           ) *FarmaUtility.getDecimalNumber(bean.getVCtdVta());
             // fin dubilluz 21.06.2011
             mapaAux.put("COD_PROD", codProdAux);
             mapaAux.put("TOTAL_PROD", totalProducto + "");
             listaCodProds.add(mapaAux);
             /**fin de agregar al arreglo de cod_productos**/
             double auxPrecio = FarmaUtility.getDecimalNumber(bean.getVValPrecioLista_2());
             double auxCantidad = FarmaUtility.getDecimalNumber(bean.getVCtdVta());
             String auxPrecAnt;
             try {
                 if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                     VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
                 }
             } catch (Exception ex) {
                 log.error("", ex);
             }
             if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                 auxPrecAnt = FarmaUtility.formatNumber(auxPrecio * auxCantidad, 3);
             } else {
                 auxPrecAnt = FarmaUtility.formatNumber(auxPrecio * auxCantidad);
             }
             double porcIgv = FarmaUtility.getDecimalNumber(bean.getVPctIgv());
             double precioTotal = auxPrecio * auxCantidad;
             double totalIgv = precioTotal - (precioTotal / (1 + porcIgv / 100));
             String vTotalIgv = FarmaUtility.formatNumber(totalIgv);
             String cPrecioFinal = FarmaUtility.formatNumber(auxPrecio, 3);
             String cPrecioVta = FarmaUtility.formatNumber(auxPrecio, 3);

             bean.setVValIgv(vTotalIgv);
             bean.setVCodCupon("");
             bean.setVPctDcto("");
             //bean.setVPrecioBase(cPrecioVta);
             bean.setVPrecioVta(cPrecioFinal);
             bean.setVSubTotal(auxPrecAnt);

         }


         /**********     
          * recorriendo todos los productos y calculando el descuento que es aplicable a por cada campa�a.
          * * ************/

         log.debug("listaCodProds:" + listaCodProds);


         VariablesVentas.vListDctoAplicados = new ArrayList(); //el detalle de los dscto Aplicados

         List prodsCampanias = new ArrayList();
String cad1="----listaCodProds----\n";
for(int i=0;i<listaCodProds.size();i++){
    cad1=cad1+"["+i+"]"+listaCodProds.get(i).toString()+"\n";
    }
imprime(cad1);
cad1="------VariablesVentas.vArrayList_Cupones-------\n";
for(int i=0;i<VariablesVentas.vArrayList_Cupones.size();i++){
        cad1=cad1+"["+i+"]"+VariablesVentas.vArrayList_Cupones.get(i).toString()+"\n";
    }
imprime(cad1);
         if (listaCodProds.size() > 0 && VariablesVentas.vArrayList_Cupones.size() > 0) {
             prodsCampanias =
                     UtilityVentas.prodsCampaniasAplicables(listaCodProds, 
                                                            VariablesVentas.vArrayList_Cupones);
         }
cad1="-----------prodsCampanias----------------\n";
for(int i=0;i<prodsCampanias.size();i++){
    cad1=cad1+"["+i+"]"+prodsCampanias.get(i).toString()+"\n";
    }
imprime(cad1);
//muestraMsj("PAUSE");
         log.debug("prodsCampanias:" + prodsCampanias);
         //INICIALIZANDO TODAS LAS CAMPANIAS APLICABLES A LOS PRODUCTOS
         //para conservar el acumulado de ahorros totales
         List listaCampAhorro = new ArrayList(); //lista de campa�as descuento por producto
         Map mapaTemp;
         String codCampTemp = "";

         List prodsCampaniasNUEVA = new ArrayList();
         for (int u = 0; u < prodsCampanias.size(); u++) {
             codCampTemp = ((Map)prodsCampanias.get(u)).get("COD_CAMP_CUPON").toString();
             // dubilluz 01.06.2012
             if (UtilityFidelizacion.getPermiteCampanaTarj(codCampTemp, VariablesFidelizacion.vDniCliente,
                                                           VariablesFidelizacion.vNumTarjeta)) {
                 boolean existe = false;
                 for (int p = 0; p < listaCampAhorro.size(); p++) {
                     if (((Map)listaCampAhorro.get(p)).get("COD_CAMP_CUPON").toString().equalsIgnoreCase(codCampTemp)) {
                         existe = true;
                         break;
                     }
                 }

                 if (!existe) {
                     mapaTemp = new HashMap();

                     mapaTemp.put("COD_CAMP_CUPON", codCampTemp);
                     mapaTemp.put("AHORRO_ACUM", "0.0");
                     mapaTemp.put("IND_CON_RECETA", ((Map)prodsCampanias.get(u)).get("IND_CON_RECETA").toString());
                     //mapaTemp.put("UNID_ACUM", "0.0");
                     listaCampAhorro.add(mapaTemp);
                 }
                 prodsCampaniasNUEVA.add((Map)prodsCampanias.get(u));
             } else {
                 log.debug("la campana ingresada NO ES VALIDA para la tarjeta " + codCampTemp);
             }

         }

         log.info("prodsCampanias>>>" + prodsCampanias.size());
         prodsCampanias = prodsCampaniasNUEVA;
         log.info("prodsCampanias>>>" + prodsCampanias.size());
         //// dubilluz 05.06.2012
         log.debug("listaCampAhorro:" + listaCampAhorro);
         /*calculando los descuentos por cada productos para todas las cammpanias**/
         //variables auxiliares usados para calcular
         mapaAux = new HashMap();
         mapaValidarAcumula = new HashMap();
         Map mapaAux2 = new HashMap();
         String codProdAux2 = "";
         String codCampAux2 = "";
         //


         double mayorAhorro = 0.0;
         String campMayorAhorro = "";
         double precioVtaMayorAhorro = 0.0;
         double totalVtaMayorAhorro = 0.0;
         double dctoVtaMayorAhorro = 0.0;
         double valorCuponMayorAhorro = 0.0;
         int cantPedMayorAhorro = 0;
         Map dctosAplicado = new HashMap();

         double ahorro = 0.0;
         double ahorroTotal = 0.0;

         double cantUnidAcumulado = 0.0; //contador de unidades aplicados a una camapania        
         double ahorroAcumulado = 0.0; //contador monto ahorro acumulado a una campania

         int cantAplicable = 0, cantAplicableAux = 0;


         int cantPed = 0; // cantidad del producto dentro de resumen pedido
         int fraccionP�d = 0; // cantidad del producto dentro de resumen pedido
         double cantUnidPed = 0.0; // cantidad en unidades del pedido            
         double precioVtaOrig = 0.0; //
         double totalXProd = 0.0;
         double precioVtaConDcto = 0.0; //

         double neoTotalXProd = 0.0; //nuevo total por producto
         double neoPrecioVtaXProd = 0.0; //nuevo precio venta por producto
         double neoDctoPorcentaje = 0.0;

         Map mapaCupon;

         double acumuladoDesctoPedido = 0;
         acumuladoDesctoPedido += VariablesFidelizacion.vAhorroDNI_x_Periodo;
         log.info("Descuento maximo utilizado: " + acumuladoDesctoPedido);

         // dubilluz 01.06.2010
         double valorCuponNuevo = 0.0;

         boolean flg_AcumulaAhorro = false;//indExcluyeProd_AHORRO_ACUM
         boolean flg_AcumulaMasAhorro = false;//indExcluyeProdMayorAhorro


         int cantPed_Receta = 0; // cantidad del producto dentro de resumen pedido
         int fraccionPed_Receta = 0; // cantidad del producto dentro de resumen pedido
         
         log.debug("analizando productos con CUPONES TIPO porcentaje");
         log.info("ProdCamp.:" + prodsCampanias);
         
         /*********************************************************************
          ****************1. CALCULO DE CAMPA�AS POR PRODUCTOS*****************
          *********************************************************************/
         
         //KMONCADA 15.05.2015 PARA CASO DE MONTO SUPERADO NO REALIZAR OPERACION DE MEJOR DSCTO DE CAMPA�AS
         // INI AOVIEDO 17/05/2017
         for (int i = 0; i < prodsCampanias.size(); i++) {
             mapaValidarAcumula = (Map)prodsCampanias.get(i);
             String vCodCamp = (String)mapaValidarAcumula.get("COD_CAMP_CUPON");
             String vCodProd = (String)mapaValidarAcumula.get("COD_PROD");
             String indAhorro=((String)mapaValidarAcumula.get("FLG_ACUMULA")).trim();//IND_EXCLUYE_ACUM_AHORRO
             //FarmaUtility.showMessage(this, "Indicador: "+indAhorro, null);
             log.info("Indicador de Ahorro FLG_ACUMULA: "+indAhorro+" vCodCamp: "+vCodCamp+" vCodProd: "+vCodProd);
                          
             if (((String)mapaValidarAcumula.get("FLG_ACUMULA")).trim().equalsIgnoreCase("N")){ //AOVIEDO 17/05/2017 //IND_EXCLUYE_ACUM_AHORRO
                VariablesFidelizacion.vExcluyeAcumula = true;
             }else{
                 VariablesFidelizacion.vExcluyeAcumula = false;
             }
         }
         // FIN AOVIEDO 17/05/2017
         
         if ( VariablesFidelizacion.vDniCliente.trim().length() > 0 
              && (acumuladoDesctoPedido  >= VariablesFidelizacion.vMaximoAhorroDNIxPeriodo) 
              && !VariablesFidelizacion.vExcluyeAcumula) { // AOVIEDO 17/05/2017
             log.info("NO VA HA REALIZAR OPERACION DE BUSCAR MEJOR DSCTO X CAMPA�AS");
             log.info("MONTO DE AHORRO DE DNI X PERIODO --> "+VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
             log.info("MONTO ACUMULADO --> "+acumuladoDesctoPedido);
         }else{
             //@@@@@@ 08.2016
             
             boolean pIND_CON_RECETA = false;
             boolean pPermiteCalculo = false;
             for (int i = 0; i < prodsCampanias.size(); i++) {
                     
             valorCuponNuevo = 0.0;
             mapaAux = (Map)prodsCampanias.get(i);
             codProdAux = (String)mapaAux.get("COD_PROD");
             codCampAux = (String)mapaAux.get("COD_CAMP_CUPON");
                     
             pIND_CON_RECETA = false;
             pPermiteCalculo = false;
             
             if(((String)mapaAux.get("IND_CON_RECETA")).trim().equalsIgnoreCase("S"))
                      pIND_CON_RECETA = true;
              
              
             if(pIND_CON_RECETA){
                     if(UtilityFidelizacion.isExisteProductoReceta(codProdAux)){
                             pPermiteCalculo = true;         
                     }
                     else{
                             pPermiteCalculo = false;                        
                             log.debug("NO PERMITE PRODUCTO FUERA DE RECETA :" + 
                                           codProdAux + ",cod_camp_cupon:" + codCampAux);
                     }
             }
             else    
                     pPermiteCalculo = true;
             
             if(pPermiteCalculo){
                     
             ////////////////////////////
                 if (((String)mapaAux.get("FLG_ACUMULA")).trim().equalsIgnoreCase("N"))//IND_EXCLUYE_ACUM_AHORRO
                 {flg_AcumulaAhorro = true;}
                 else
                 {flg_AcumulaAhorro = false;}
                 ////////////////////////////
                 log.debug("analizando el prod:" + codProdAux + ",cod_camp_cupon:" + codCampAux);
                 
                 //BUSCANDO EL INDICE DEL PRODUCTO EN ARREGLO AL CUAL APLICAR EL DSCTO;
                 int indiceProducto = -1;
                 for (int m = 0; m < UtilityCalculoPrecio.getSizeDetalleVenta(); m++) {
                     if ( UtilityCalculoPrecio.getBeanPosDetalleVenta(m).getVCodProd() .equalsIgnoreCase(codProdAux)) { //si codigo del producto a buscar coincide con el que se aplicar dcto
                 indiceProducto = m;
                 break;
                 }
                 }
                 log.debug("JCALLO:indiceProducto:" + indiceProducto);
                 //hasta aqui se tiene el indice donde se encuentra el producto al cual se le aplicar el dcto
                 
                 //BUSCANDO EL INDICE DE LA CAMPANA CUPON del listado de campanas cupones
                 int indiceCamp = -1;
                 Map mapTemp2 = new HashMap();
                 for (int m = 0; m < VariablesVentas.vArrayList_Cupones.size(); m++) {
                 mapTemp2 = (Map)VariablesVentas.vArrayList_Cupones.get(m);
                 if (((String)mapTemp2.get("COD_CAMP_CUPON")).equals(codCampAux)) { //ve si existe un valor en mapa si tiene cod_camp_cupon
                 indiceCamp = m;
                 break;
                 }
                 }
                 log.debug("JCALLO:indiceCamp:" + indiceCamp+" - "+((Map) VariablesVentas.vArrayList_Cupones.get(indiceCamp)).get("TIP_CUPON").toString());
                 //hasta aqui tenemos el indice donde se encuentra la campana cupon a aplicar
                 
                 
                 //el calculo de los descuentoS solo se aplica para cupones tipo  PORCENTAJE
                 if (((Map)VariablesVentas.vArrayList_Cupones.get(indiceCamp)).get("TIP_CUPON").toString().equals(ConstantsVentas.TIPO_PORCENTAJE)) {
//muestraMsj("DESCUENTO POR PORCENTAJE");
                 int indiceProdCamp = -1;
                 Map mapTemp3 = new HashMap();
                 log.debug("listaCampAhorro:" + listaCampAhorro);
                 for (int m = 0; m < listaCampAhorro.size(); m++) {
                 mapTemp3 = (Map)listaCampAhorro.get(m);
                 log.debug("mapTemp3:" + mapTemp3);
                 if (((String)mapTemp3.get("COD_CAMP_CUPON")).equals(codCampAux)) { //ve si existe un valor en mapa si tiene cod_camp_cupon
                     indiceProdCamp = m;
                     break;
                 }
                 }
                 log.debug("JCALLO:indiceProdCamp:" + indiceProdCamp);
                 //hasta aqui se tiene el indice de de los datos de  montoAhorro acumulado por campana a aplicar
                 //obteniendo datos principales del producto del resumen pedido
                 if(((String)((Map)listaCampAhorro.get(indiceProdCamp)).get("IND_CON_RECETA")).trim().equalsIgnoreCase("S"))
                     pIND_CON_RECETA = true;
                 else
                     pIND_CON_RECETA = false;
                     
    
                     cantPed_Receta = -1;
                     fraccionPed_Receta = -1;
                     
                     
                             //dubilluz 31.08.2016
                             if(pIND_CON_RECETA)
                             {
                                   for(int k=0;k<VariablesVentas.vDetalleReceta.size();k++){
                                         if(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, k, 0).trim().equalsIgnoreCase(
                                                 UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCodProd()
                                                )){
                                           
                                           cantPed_Receta     = Integer.parseInt(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, k, 1).trim());
                                           fraccionPed_Receta = Integer.parseInt(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, k, 3).trim());  
                                           break;
                                         }
                                   }
                                 //FarmaUtility.showMessage(this,"cantPed "+cantPed+" >>>  fraccionP�d "+fraccionP�d,null);
                             }
                             //dubilluz 31.08.2016
                             
                             cantPed     = Integer.parseInt(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCtdVta());
                             fraccionP�d = Integer.parseInt(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVValFracVta());
                             precioVtaOrig = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getPPREC_VTA_UNIT_NVO());
                                             // dubilluz 2016.12.29   
                                             // FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVPrecioBase()); //   
                                             // FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_ResumenPedido,indiceProducto,3));//precio venta
                             cantUnidPed = (cantPed * 1.00) / fraccionP�d; //cantidad en unidades
                             totalXProd  = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVSubTotal());
                 
                 //obtiendo el mapa del cupon a aplicar
                 mapaCupon = (Map)VariablesVentas.vArrayList_Cupones.get(indiceCamp); //mapa del cupon
                 Map mapaCampProd = (Map)listaCampAhorro.get(indiceProdCamp); //mapa camp ahorro
                 
                 
                 /**APLICANDO EL DSCTO AL PRODUCTO CON ESTA CAMPANIA**/
                 //obtieniendo el acumulado de unidades y acumulado de ahorros que se tiene
                 ahorroAcumulado = Double.parseDouble(mapaCampProd.get("AHORRO_ACUM").toString());
                 //cantUnidAcumulado = Double.parseDouble(mapaCampProd.get("UNID_ACUM").toString());
                 
                 
                 //CALCULANDO EL DCTO AL PRODUCTO CON LA CAMPANA
                 double unidMinUso = Double.parseDouble(mapaCupon.get("UNID_MIN_USO").toString());
                 double montMinUso = Double.parseDouble(mapaCupon.get("MONT_MIN_USO").toString());
                 //unidades aplicables de la campania
                 //double unidMaximaAplicable = Double.parseDouble(mapaCupon.get("UNID_MAX_PROD").toString());
                 
                 //Obtiene el porcentaje descuento personalizado de producto
                 //DUBILLUZ 01.06.2010
                 //valorCuponNuevo = Double.parseDouble(mapaCupon.get("VALOR_CUPON").toString());
                 // Descomentado porque ya se acuerdan que SI PIDIERON
                 // SE PARAMETRIZARA con
                 // Esto se comentara porque NO RECUERDAN CUANDO PIDIERON ESTO.
                 valorCuponNuevo =
                     UtilityFidelizacion.getDescuentoPersonalizadoProdCampana(codCampAux,
                                                                              codProdAux,
                                                                              //dubilluz 15.08.2015 -- 
                                                                              VariablesFidelizacion.vDniCliente
                                                                              );
//----------------------------------------------------------------------------------------------------------------------                                  
                 if (valorCuponNuevo == -1) {
                 valorCuponNuevo =
                         Double.parseDouble(mapaCupon.get("VALOR_CUPON").toString());
                 }
                 double ahorroAplicable =
                 Double.parseDouble(mapaCupon.get("MONTO_MAX_DESCT").toString()) - ahorroAcumulado;
                 
                 //Obtiene el maximo de unidades a la compra del producto
                 //DUBILLUZ 28.05.2009
                 ///////////////////////////////////////////////////////////////////////////////////////////////
                 ///////////////////////////////////////////////////////////////////////////////////////////////
                 // VALIDAR SI PRODUCTO ESTA EN LISTA NEGRA Y CANTIDAD DIARIA O SEMANAL
                 // ESTE YA TE DEVUELVE LA CANTIDAD MAXIMA DIARIA O SEMANAL
                 // SI NO ES ASI USA EL MAXIMO DECTO MAXIMO AHORA SERA DE LA CAMPA�A SERA DIARIA
                 double unidMaximaAplicable = 0.0;
                 
                 double vUnidDctoHistorico = 0.0;
                 
                 boolean vExisteProductoListaLocked = false;
                 vExisteProductoListaLocked = UtilityFidelizacion.getExisteProdListaLocked(codProdAux);
                 boolean vMaxLockedDia = UtilityFidelizacion.getExisteProdListaLockedDia(codProdAux);
                 boolean vMaxLockedSemana = UtilityFidelizacion.getExisteProdListaLockedSemana(codProdAux);
                 
                 if(vExisteProductoListaLocked){
                 //esta bloqeado buscara las unidades si es dia o semanal
                 if(vMaxLockedDia){
                 unidMaximaAplicable = UtilityFidelizacion.getUnidMaxDctoDia(codProdAux);
                 vUnidDctoHistorico = UtilityFidelizacion.getHistUnidDctoDia(codProdAux,VariablesFidelizacion.vDniCliente);
                 }
                 else{
                 if(vMaxLockedSemana){    
                    unidMaximaAplicable = UtilityFidelizacion.getUnidMaxDctoSemana(codProdAux);
                    vUnidDctoHistorico = UtilityFidelizacion.getHistUnidDctoSemana(codProdAux,VariablesFidelizacion.vDniCliente);
                 }
                 }
                 }
                 else{
                 // no esta en 1 tope, se ira al 2do tope dnde el maximo sera x dia m
                 // entnces obtiene  lo ya usado de historico diario 
                 unidMaximaAplicable = UtilityFidelizacion.getMaxUnidDctoProdCampana(codCampAux, codProdAux,VariablesFidelizacion.vDniCliente);
                 if (unidMaximaAplicable == -1) {
                     unidMaximaAplicable = Double.parseDouble(mapaCupon.get("UNID_MAX_PROD").toString());
                 }
                 vUnidDctoHistorico =  UtilityFidelizacion.getHistUnidDctoDia(codProdAux,VariablesFidelizacion.vDniCliente);// maximo descuento de este producto DIARIO
                 }
                     /**********RARGUMEDO: Variables para calculo y evaluacion de descecuento de campa�a de prod fracionado ***********/
                     int cantPed_Aux = 0; 
                     double totalXProd_Aux = 0.0d;
                     
                     int cantEntero=0;
                     int cantFracc=0;
                     double precioEntero=0.0d;
                     double precioFracc=0.0d;
                     double descTotal=0.0d;
                     double ahorro1=0.0d;
                     double ahorro2=0.0d;
                     
                     double precio_Real=0.0d;
                     double precio_1erDec=0.0d;
                     boolean descuentoAplicado=false;
                     
                     boolean aplicaDescuentoCamp =
                         UtilityFidelizacion.getIndCampDescuentoFraccion(codCampAux,
                                                                         codProdAux,
                                                                         fraccionP�d,
                                                                         valorCuponNuevo);
                     /**********FIN........................................................................................ ***********/
                     
                 ///// aqui hace la restriccion de cantidad
                 log.info("unidMaximaAplicable vUnidDctoHistorico "+unidMaximaAplicable+" / "+vUnidDctoHistorico);
                 double vCantidadPermitida =  unidMaximaAplicable - (vUnidDctoHistorico);
                 if(vCantidadPermitida<0)vCantidadPermitida = 0;
                 log.info("vCantidadPermitida "+vCantidadPermitida);
                 log.info("Variable Auxiliar 1: "+cantPed_Aux);
                 if(vCantidadPermitida>0){
                     log.info("cantUnidPed "+cantUnidPed);
                     log.info("NO SE >> "+cantUnidPed);
                     
                     if(cantUnidPed>vCantidadPermitida)
                         cantUnidPed=vCantidadPermitida;
                     
                     log.info("cantUnidPed "+cantUnidPed);
                     
                     if(vCantidadPermitida<0)
                         vCantidadPermitida = 0;
                     
                     // se ve si llego o no al toque
                     if (cantUnidPed  > vCantidadPermitida){
                         log.info("aaa");
                         cantAplicable = Math.round((float)UtilityVentas.Truncar(vCantidadPermitida * fraccionP�d, 0));
                     } else {
                         log.info("bbb");
                         // este ya excedio el tope sea de maestro o sea de la campa�a
                         cantAplicable = Math.round((float)(cantUnidPed * fraccionP�d));
                     }
                     
                     /**********RARGUMEDO: para calcular el descuento de cupon en prod fraciconado *****************/
                     if(aplicaDescuentoCamp && cantUnidPed>0){
                        cantEntero=cantPed/fraccionP�d;
                        cantFracc=cantPed%fraccionP�d;
                        
                        double precioBase  = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVPrecioBase());
                        double precioVta   = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVPrecioVta());
                        if(cantUnidPed>=cantEntero)
                             precioEntero=cantEntero*valorCuponNuevo;
                        else
                             precioEntero=cantUnidPed*valorCuponNuevo+((cantEntero-cantUnidPed)*fraccionP�d)*precioVta;
                         
                         precioFracc=cantFracc*precioBase;
                         
                         precio_Real = ((cantEntero*fraccionP�d)+cantFracc)*precioBase;
                         precio_1erDec = (cantEntero*fraccionP�d+cantFracc)*precioVta;
                         totalXProd_Aux = precioEntero + precioFracc;
                         
                         ahorro1 = precio_Real - precio_1erDec;
                         ahorro2 = precio_1erDec - totalXProd_Aux;
                         descTotal = ahorro2 + ahorro1;
                         
                         descuentoAplicado=true;
                         System.out.println("Precio Promo: "+valorCuponNuevo);
                         System.out.println("Precio Prods Promo: ("+cantEntero+"x"+valorCuponNuevo+") = "+precioEntero);
                         System.out.println("Precio Prods Sin Prom: ("+cantFracc+"x"+precioBase+") = "+precioFracc);
                         System.out.println("-----------------------------------------------");
                         System.out.println("Precio real: (("+cantEntero+"x"+fraccionP�d+")+"+cantFracc+")x"+precioBase+" = "+precio_Real);
                         System.out.println("1er descuento: ("+cantEntero+"x"+fraccionP�d+" + "+cantFracc+")x"+precioVta+" = "+precio_1erDec);
                         System.out.println("Desc Total: "+precioEntero+"+ "+precioFracc+" = "+totalXProd_Aux);
                         System.out.println("-----------------------------------------------");
                         System.out.println("Ahorro 1: "+ahorro1);
                         System.out.println("Ahorro 2: "+ahorro2);
                         System.out.println("Ahorro Total: "+descTotal);
                         System.out.println("-----------------------------------------------");
                             
                         }else{
                             System.out.println("Precio Promo: --> PRODUCTO ENTERO");
                         }
                     /**********FIN................................................................*****************/
                     
                     if(pIND_CON_RECETA){
                         log.info(" revisa la cantidad aplicable porq es receta ");
                         log.info(" ====================== ");
                         log.info(" cantAplicable "+cantAplicable);
                         log.info(" fraccionP�d "+fraccionP�d);
                         log.info(" ====================== ");
                         log.info(" cantPed_Receta "+cantPed_Receta);
                         log.info(" fraccionPed_Receta "+fraccionPed_Receta);
                         log.info(" ====================== ");
                         if(cantAplicable>(cantPed_Receta*1.00*fraccionP�d/fraccionPed_Receta)){
                             //FarmaUtility.showMessage(this,"no se puede mas que la receta el descuento : cantAplicable "+cantAplicable+" - cantPed_Receta "+cantPed_Receta
                             //                         + " fraccionPed_Receta "+fraccionPed_Receta
                             //                         , txtDescProdOculto);
                             cantAplicable = Math.round((float)(cantPed_Receta*1.00*fraccionP�d/fraccionPed_Receta));
                             //FarmaUtility.showMessage(this,"no se puede mas que la receta el descuento : cantAplicable "+cantAplicable, txtDescProdOculto);
                         }
                     }
                     
                 }
                 else{
                 cantAplicable = 0;
                 // dubilluz 17.08.2015
                 FarmaUtility.showMessage(this,"El cliente ya cumplio con el tope de unidades \n" +
                     "con descuento de este producto:"+codProdAux, txtDescProdOculto);
                 }
                 
                 log.debug("unidMaximaAplicable:" + unidMaximaAplicable + ",cantAplicable:" + cantAplicable +
                       ", cantPed:" + cantPed);
                 /* */
                 //ASOSA 
                 ///////////////////////////////////////////////////////////////////////////////////////////////                    
                 ///////////////////////////////////////////////////////////////////////////////////////////////
                 
                 //precioVtaConDcto = ( precioVtaOrig * (( 100.0-Double.parseDouble( mapaCupon.get("VALOR_CUPON").toString() ) )/100.0) );
                 //Cambiado para usar la variable
                 
                 //INI ASOSA - 15/09/2016 - PROMOCIONES PRECIO FIJO PARCHE
                 precioVtaConDcto = UtilityVentas.obtenerPrecProdCampCupon(codCampAux, 
                                                                           codProdAux);
                 if (precioVtaConDcto == 0.0) {//FIN ASOSA - 15/09/2016 - PROMOCIONES PRECIO FIJO PARCHE
                     precioVtaConDcto = (precioVtaOrig * ((100.0 - valorCuponNuevo) / 100.0));
                 }             
                 //ASOSA
                 log.info("Variable Auxiliar 2: "+cantPed_Aux);
                 log.debug("precioVtaOriginal:" + precioVtaOrig);
                 log.debug("VALOR_CUPON:" + mapaCupon.get("VALOR_CUPON").toString());
                 log.debug("valorCuponNuevo:" + valorCuponNuevo);
                 log.debug("precioVtaConDcto:" + precioVtaConDcto);
                 //SI LA CAMPANIA PERMITE VENDER POR DEBAJO DEL COSTO PROMEDIO
                 String sPrecioFinal = "";
                 //dubilluz 16.08.2015 se valida las campa�as HASTA
                 //
                 if (mapaCupon.get("IND_VAL_COSTO_PROM").toString().equals(FarmaConstants.INDICADOR_N)) {
                 //VERIFICANDO SI ESTA EN N:NO PERMITIR VENDER POR DEBAJO DEL COSTO PROMEDIO
                 try {
                     sPrecioFinal =
                             DBVentas.getPrecioFinalCampania(codProdAux, codCampAux, precioVtaConDcto, precioVtaOrig,
                                                             fraccionP�d,
                                         //dubilluz 15.08.2015 -- 
                                         VariablesFidelizacion.vDniCliente
                                                             ).trim();
                 
                     if (Double.parseDouble(sPrecioFinal) > precioVtaConDcto) {
                         precioVtaConDcto = Double.parseDouble(sPrecioFinal);
                     }
                 } catch (Exception e) {
                     log.debug("Exception e:" + e);
                 }
                     
                 }
                 //fin de verificacion de venta por debajo del costo promedio.
                 if(aplicaDescuentoCamp && descuentoAplicado){
                     /**********RARGUMEDO: para aplicar el descuento de cupon en prod fraciconado *****************/
                     neoTotalXProd = redondearNro(totalXProd_Aux);
                     ahorro = redondearNro(descTotal);
                     /**********FIN..............................................................................**/
                 }else{
                     neoTotalXProd = (precioVtaConDcto * cantAplicable) + (precioVtaOrig * (cantPed - cantAplicable));
                     ahorro = (precioVtaOrig - precioVtaConDcto) * cantAplicable;
                     
                     log.info("Variable Auxiliar 5: "+cantPed_Aux);
                     neoPrecioVtaXProd = UtilityVentas.Truncar(neoTotalXProd / cantPed, 3);
                     //por si deseara saber la diferencia
                     double diferencia = neoTotalXProd - UtilityVentas.Truncar(neoPrecioVtaXProd * cantPed, 2);
                     neoTotalXProd = UtilityVentas.Truncar(neoPrecioVtaXProd * cantPed, 2);
                     
                 }
                 
                 log.debug("ahorro:" + ahorro + ",ahorroAplicable:" + ahorroAplicable);
                 
                 if (ahorro > ahorroAplicable) { //se volvera a calcular si el ahorro es superior al aplicable
                 //int cantUnidAplicables = Math.round( (float)( ( ( totalXProd - ahorroAplicable ) / precioVtaConDcto ) * fraccionP�d ));
                 
                 precioVtaConDcto = precioVtaOrig - (ahorroAplicable) / cantAplicable;
                 int cantAplicableAux2;
                 cantAplicableAux2 = cantAplicable;
                 
                 cantAplicable =
                         Math.round((float)UtilityVentas.Truncar((ahorroAplicable / (precioVtaOrig - precioVtaConDcto)),
                                                                 0));
                 
                 if (cantAplicableAux2 > cantAplicable) {
                     cantAplicable = cantAplicableAux2;
                     log.info("cantidad cambiada");
                 }
                 
                 neoTotalXProd = (precioVtaConDcto * cantAplicable) + (precioVtaOrig * (cantPed - cantAplicable));
                 log.debug("precioVtaConDcto:" + precioVtaConDcto);
                 log.debug("precioVtaOrig:" + precioVtaOrig);
                 log.debug("cantAplicable:" + cantAplicable);
                 ahorro = (precioVtaOrig - precioVtaConDcto) * cantAplicable;
                 log.debug("ahorro:" + ahorro);
                 log.info("Variable Auxiliar 3: "+cantPed_Aux);
                 }
                 log.info("Variable Auxiliar 4: "+cantPed_Aux);
                 log.debug("ahorro 2:" + ahorro);
                 /*
                             //--INICIO de verificar maximo descuento por dia del DNI
                             // DUBILLUZ 27.05.2009
                 
                 
                             if( (acumuladoDesctoPedido + ahorro) > VariablesFidelizacion.vMaximoAhorroDNIxPeriodo ){
                 
                                 //ahorro = (precioVtaOrig - precioVtaConDcto)*cantAplicable;
                                 log.info("precioVtaOrigl "+precioVtaOrig);
                                 log.info("VariablesFidelizacion.vMaximoAhorroDNIxPeriodo "+VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
                                 log.info("acumuladoDesctoPedido "+acumuladoDesctoPedido);
                                 log.info("cantAplicable "+cantAplicable);
                                 precioVtaConDcto = precioVtaOrig - (VariablesFidelizacion.vMaximoAhorroDNIxPeriodo-acumuladoDesctoPedido)/cantAplicable;
                                 log.info("**precioVtaConDcto "+precioVtaConDcto);
                 
                                 log.info("**ahorro "+ahorro);
                                 log.info("**ahorroAplicable "+ahorroAplicable);
                                 log.info("**precioVtaOrig "+precioVtaOrig);
                                 log.info("**precioVtaConDcto "+precioVtaConDcto);
                                 cantAplicableAux=cantAplicable;
                                 cantAplicable = Math.round( (float) UtilityVentas.Truncar( ( (VariablesFidelizacion.vMaximoAhorroDNIxPeriodo-acumuladoDesctoPedido)/(precioVtaOrig-precioVtaConDcto) ) , 0)  );
                                 if(cantAplicableAux>cantAplicable)
                                     cantAplicable = cantAplicableAux;
                 
                 
                                 log.info("**cantAplicable "+cantAplicable);
                                 log.info("-----");
                                 log.info("**precioVtaConDcto "+precioVtaConDcto);
                                 log.info("**precioVtaOrig "+precioVtaOrig);
                                 log.info("**cantPed "+cantPed);
                                 neoTotalXProd = (precioVtaConDcto * cantAplicable) + (precioVtaOrig * (cantPed - cantAplicable) );
                                 log.info("**neoTotalXProd "+neoTotalXProd);
                                 ahorro = (precioVtaOrig - precioVtaConDcto)*cantAplicable;
                                 log.info("-----");
                                 log.info("**precioVtaOrig "+precioVtaOrig);
                                 log.info("**precioVtaConDcto "+precioVtaConDcto);
                                 log.info("**cantAplicable "+cantAplicable);
                             }
                             acumuladoDesctoPedido += ahorro;
                             log.info("Descuento Parcial "+ahorro);
                             log.info("Descuento acumulado "+acumuladoDesctoPedido);
                             // Fin de Validacion de Maximo -- DUBILLUZ
                 
                              * */
                 
                 /** hasta aqui se tiene cantidad al cual se va aplicar el dcto
                              *  ahorro en monto
                              *  nuevo total por producto
                              * **/
//----------------------------------------------------------------------------------------------------------------------                 
                 neoDctoPorcentaje =
                     UtilityVentas.Truncar(((precioVtaOrig - neoPrecioVtaXProd) / precioVtaOrig) * 100.0, 2);
                 
                 
                 //                neoPrecioVtaXProd =
                 //                        UtilityVentas.Redondear(neoTotalXProd / cantPed, 3);
                 //                //por si deseara saber la diferencia
                 //                double diferencia =
                 //                    neoTotalXProd - UtilityVentas.Redondear(neoPrecioVtaXProd *
                 //                                                          cantPed, 2);
                 //                neoTotalXProd =
                 //                        UtilityVentas.Redondear(neoPrecioVtaXProd * cantPed, 2);
                 //                neoDctoPorcentaje =
                 //                        UtilityVentas.Redondear(((precioVtaOrig - neoPrecioVtaXProd) /
                 //                                               precioVtaOrig) * 100.0, 2);
                 //ver si el ahorro calculado es mayor al que se tenia anteriormente
                 if (ahorro > mayorAhorro && cantUnidPed >= unidMinUso)
                 // el mont minimo de uso debe ser de todos los productos USO de la campana
                 // dubilluz 21.06.2011
                 //&&totalXProd >= montMinUso)
                 {
                 mayorAhorro = ahorro;
                 campMayorAhorro = codCampAux;
                 precioVtaMayorAhorro = neoPrecioVtaXProd;
                 totalVtaMayorAhorro = neoTotalXProd;
                 dctoVtaMayorAhorro = neoDctoPorcentaje;
                 cantPedMayorAhorro = cantAplicable;
                 
                 //valorCuponMayorAhorro = Double.parseDouble( mapaCupon.get("VALOR_CUPON").toString() );
                 //dubilluz 01.06.2010
                 valorCuponMayorAhorro = valorCuponNuevo;
                 flg_AcumulaMasAhorro = flg_AcumulaAhorro;
                 }
                 //fin de ver el maximo ahorro
                 
                 } //fin calculo de ahorro para cupones campana tipo PROCENTAJE
                 else if (((Map)VariablesVentas.vArrayList_Cupones.get(indiceCamp)).get("TIP_CUPON").toString().equals(ConstantsVentas.TIPO_MONTO)) {
//muestraMsj("CALCULA DESCUENTO MONTO");
                    int indiceProdCamp = -1;
                    Map mapTemp3 = new HashMap();
                    log.debug("listaCampAhorro:" + listaCampAhorro);
                    for (int m = 0; m < listaCampAhorro.size(); m++) {
                    mapTemp3 = (Map)listaCampAhorro.get(m);
                    log.debug("mapTemp3:" + mapTemp3);
                    if (((String)mapTemp3.get("COD_CAMP_CUPON")).equals(codCampAux)) { //ve si existe un valor en mapa si tiene cod_camp_cupon
                        indiceProdCamp = m;
                        break;
                    }
                    }
                    log.debug("JCALLO:indiceProdCamp:" + indiceProdCamp);
                    //hasta aqui se tiene el indice de de los datos de  montoAhorro acumulado por campana a aplicar
                    //obteniendo datos principales del producto del resumen pedido
                    if(((String)((Map)listaCampAhorro.get(indiceProdCamp)).get("IND_CON_RECETA")).trim().equalsIgnoreCase("S"))
                        pIND_CON_RECETA = true;
                    else
                        pIND_CON_RECETA = false;
                        
                    
                        cantPed_Receta = -1;
                        fraccionPed_Receta = -1;
                        
                        
                                //dubilluz 31.08.2016
                                if(pIND_CON_RECETA)
                                {
                                      for(int k=0;k<VariablesVentas.vDetalleReceta.size();k++){
                                            if(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, k, 0).trim().equalsIgnoreCase(
                                                    UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCodProd()
                                                   )){
                                              
                                              cantPed_Receta     = Integer.parseInt(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, k, 1).trim());
                                              fraccionPed_Receta = Integer.parseInt(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, k, 3).trim());  
                                              break;
                                            }
                                      }
                                    //FarmaUtility.showMessage(this,"cantPed "+cantPed+" >>>  fraccionP�d "+fraccionP�d,null);
                                }
                                //dubilluz 31.08.2016
                                
                                cantPed     = Integer.parseInt(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCtdVta());
                                fraccionP�d = Integer.parseInt(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVValFracVta());
                                precioVtaOrig = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getPPREC_VTA_UNIT_NVO());
                                                // dubilluz 2016.12.29   
                                                // FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVPrecioBase()); //   
                                                // FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_ResumenPedido,indiceProducto,3));//precio venta
                                cantUnidPed = (cantPed * 1.00) / fraccionP�d; //cantidad en unidades
                                totalXProd  = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVSubTotal());
                    
                    //obtiendo el mapa del cupon a aplicar
                    mapaCupon = (Map)VariablesVentas.vArrayList_Cupones.get(indiceCamp); //mapa del cupon
                    Map mapaCampProd = (Map)listaCampAhorro.get(indiceProdCamp); //mapa camp ahorro
                    
                    
                    /**APLICANDO EL DSCTO AL PRODUCTO CON ESCA CAMPANIA**/
                    //obtieniendo el acumulado de unidades y acumulado de ahorros que se tiene
                    ahorroAcumulado = Double.parseDouble(mapaCampProd.get("AHORRO_ACUM").toString());
                    //cantUnidAcumulado = Double.parseDouble(mapaCampProd.get("UNID_ACUM").toString());
                    
                    
                    //CALCULANDO EL DCTO AL PRODUCTO CON LA CAMPANA
                    double unidMinUso = Double.parseDouble(mapaCupon.get("UNID_MIN_USO").toString());
                    double montMinUso = Double.parseDouble(mapaCupon.get("MONT_MIN_USO").toString());
                    //unidades aplicables de la campania
                    //double unidMaximaAplicable = Double.parseDouble(mapaCupon.get("UNID_MAX_PROD").toString());
                    
                    //Obtiene el porcentaje descuento personalizado de producto
                    //DUBILLUZ 01.06.2010
                    //valorCuponNuevo = Double.parseDouble(mapaCupon.get("VALOR_CUPON").toString());
                    // Descomentado porque ya se acuerdan que SI PIDIERON
                    // SE PARAMETRIZARA con
                    // Esto se comentara porque NO RECUERDAN CUANDO PIDIERON ESTO.
                    valorCuponNuevo =
                        UtilityFidelizacion.getDescuentoPersonalizadoProdCampana(codCampAux,
                                                                                 codProdAux,
                                                                                 //dubilluz 15.08.2015 -- 
                                                                                 VariablesFidelizacion.vDniCliente
                                                                                 );
                    if (valorCuponNuevo == -1) {
                    valorCuponNuevo =
                            Double.parseDouble(mapaCupon.get("VALOR_CUPON").toString());
                    }
                    
                    
                    double ahorroAplicable =
                    Double.parseDouble(mapaCupon.get("MONTO_MAX_DESCT").toString()) - ahorroAcumulado;
                    
                    
                    //Obtiene el maximo de unidades a la compra del producto
                    //DUBILLUZ 28.05.2009
                    ///////////////////////////////////////////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////////////////////////////////////////////
                    // VALIDAR SI PRODUCTO ESTA EN LISTA NEGRA Y CANTIDAD DIARIA O SEMANAL
                    // ESTE YA TE DEVUELVE LA CANTIDAD MAXIMA DIARIA O SEMANAL
                    // SI NO ES ASI USA EL MAXIMO DECTO MAXIMO AHORA SERA DE LA CAMPA�A SERA DIARIA
                    double unidMaximaAplicable = 0.0;
                    
                    double vUnidDctoHistorico = 0.0;
                    
                    boolean vExisteProductoListaLocked = false;
                    vExisteProductoListaLocked = UtilityFidelizacion.getExisteProdListaLocked(codProdAux);
                    boolean vMaxLockedDia = UtilityFidelizacion.getExisteProdListaLockedDia(codProdAux);
                    boolean vMaxLockedSemana = UtilityFidelizacion.getExisteProdListaLockedSemana(codProdAux);
                    
                    if(vExisteProductoListaLocked){
                    //esta bloqeado buscara las unidades si es dia o semanal
                    if(vMaxLockedDia){
                    unidMaximaAplicable = UtilityFidelizacion.getUnidMaxDctoDia(codProdAux);
                    vUnidDctoHistorico = UtilityFidelizacion.getHistUnidDctoDia(codProdAux,VariablesFidelizacion.vDniCliente);
                    }
                    else{
                    if(vMaxLockedSemana){    
                       unidMaximaAplicable = UtilityFidelizacion.getUnidMaxDctoSemana(codProdAux);
                       vUnidDctoHistorico = UtilityFidelizacion.getHistUnidDctoSemana(codProdAux,VariablesFidelizacion.vDniCliente);
                    }
                    }
                    }
                    else{
                    // no esta en 1 tope, se ira al 2do tope dnde el maximo sera x dia m
                    // entnces obtiene  lo ya usado de historico diario 
                    unidMaximaAplicable = UtilityFidelizacion.getMaxUnidDctoProdCampana(codCampAux, codProdAux,VariablesFidelizacion.vDniCliente);
                    if (unidMaximaAplicable == -1) {
                        unidMaximaAplicable = Double.parseDouble(mapaCupon.get("UNID_MAX_PROD").toString());
                    }
                    vUnidDctoHistorico =  UtilityFidelizacion.getHistUnidDctoDia(codProdAux,VariablesFidelizacion.vDniCliente);// maximo descuento de este producto DIARIO
                    }
                    
                    log.info("unidMaximaAplicable vUnidDctoHistorico "+unidMaximaAplicable+" / "+vUnidDctoHistorico);
                    double vCantidadPermitida =  unidMaximaAplicable - (vUnidDctoHistorico);
                    if(vCantidadPermitida<0)vCantidadPermitida = 0;
                    log.info("vCantidadPermitida "+vCantidadPermitida);
                    
                    if(vCantidadPermitida>0){
                        log.info("cantUnidPed "+cantUnidPed);
                        log.info("NO SE >> "+cantUnidPed);
                        
                        if(cantUnidPed>vCantidadPermitida)
                            cantUnidPed=vCantidadPermitida;
                        
                        log.info("cantUnidPed "+cantUnidPed);
                        
                        if(vCantidadPermitida<0)
                            vCantidadPermitida = 0;
                        
                        // se ve si llego o no al toque
                        if (cantUnidPed  > vCantidadPermitida){
                            log.info("aaa");
                            cantAplicable = Math.round((float)UtilityVentas.Truncar(vCantidadPermitida * fraccionP�d, 0));
                        } else {
                            log.info("bbb");
                            // este ya excedio el tope sea de maestro o sea de la campa�a
                            cantAplicable = Math.round((float)(cantUnidPed * fraccionP�d));
                        }
                        
                        if(pIND_CON_RECETA){
                            log.info(" revisa la cantidad aplicable porq es receta ");
                            log.info(" ====================== ");
                            log.info(" cantAplicable "+cantAplicable);
                            log.info(" fraccionP�d "+fraccionP�d);
                            log.info(" ====================== ");
                            log.info(" cantPed_Receta "+cantPed_Receta);
                            log.info(" fraccionPed_Receta "+fraccionPed_Receta);
                            log.info(" ====================== ");
                            if(cantAplicable>(cantPed_Receta*1.00*fraccionP�d/fraccionPed_Receta)){
                                //FarmaUtility.showMessage(this,"no se puede mas que la receta el descuento : cantAplicable "+cantAplicable+" - cantPed_Receta "+cantPed_Receta
                                //                         + " fraccionPed_Receta "+fraccionPed_Receta
                                //                         , txtDescProdOculto);
                                cantAplicable = Math.round((float)(cantPed_Receta*1.00*fraccionP�d/fraccionPed_Receta));
                                //FarmaUtility.showMessage(this,"no se puede mas que la receta el descuento : cantAplicable "+cantAplicable, txtDescProdOculto);
                            }
                        }
                        
                    }
                    else{
                    cantAplicable = 0;
                    // dubilluz 17.08.2015
                    FarmaUtility.showMessage(this,"El cliente ya cumplio con el tope de unidades \n" +
                        "con descuento de este producto:"+codProdAux, txtDescProdOculto);
                    }
                    
                    log.debug("unidMaximaAplicable:" + unidMaximaAplicable + ",cantAplicable:" + cantAplicable +
                          ", cantPed:" + cantPed);
//RARGUMEDO
///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
                 /*
                 muestraMsj("cantPed: "+cantPed
                            +"\ncantUnidPed: "+cantUnidPed
                            +"\nvCantidadPermitida: "+vCantidadPermitida
                            +"\ncantAplicable: "+cantAplicable
                            +"\nunidMaximaAplicable: "+unidMaximaAplicable
                            +"\nahorroXCupon: "+ahorroXCupon
                            +"\nvalorCuponNuevo: "+valorCuponNuevo);
                 */
//RARGUMEDO - 22/06/2017 - calcula el descuento por cupon monto.
                        precioVtaConDcto = UtilityVentas.obtenerPrecProdCampCupon(codCampAux, codProdAux);
                        if (precioVtaConDcto == 0.0) { //FIN ASOSA - 15/09/2016 - PROMOCIONES PRECIO FIJO PARCHE
                            if (ahorroXCupon > 0 && ahorroXCupon < valorCuponNuevo) {
                                valorCuponNuevo = valorCuponNuevo - ahorroXCupon;
                            } else if (ahorroXCupon >= valorCuponNuevo) {
                                valorCuponNuevo = 0;
                            }
                            precioVtaConDcto = (precioVtaOrig - (valorCuponNuevo / cantAplicable));
                            if (precioVtaConDcto < 0) {
                                precioVtaConDcto = 0;
                            }
                        }
//RARGUMEDO
/////////////////////////////////////////////////////////////////////////////////////////////////
                        
/////////////////////////////////////////////////////////////////////////////////////////////////
                    log.debug("precioVtaOriginal:" + precioVtaOrig);
                    log.debug("VALOR_CUPON:" + mapaCupon.get("VALOR_CUPON").toString());
                    log.debug("valorCuponNuevo:" + valorCuponNuevo);
                    log.debug("precioVtaConDcto:" + precioVtaConDcto);
                    //SI LA CAMPANIA PERMITE VENDER POR DEBAJO DEL COSTO PROMEDIO
                    String sPrecioFinal = "";
                    //dubilluz 16.08.2015 se valida las campa�as HASTA
                    //
                    if (mapaCupon.get("IND_VAL_COSTO_PROM").toString().equals(FarmaConstants.INDICADOR_N)) {
                    //VERIFICANDO SI ESTA EN N:NO PERMITIR VENDER POR DEBAJO DEL COSTO PROMEDIO
                    try {
                        sPrecioFinal =
                                DBVentas.getPrecioFinalCampania(codProdAux, codCampAux, precioVtaConDcto, precioVtaOrig,
                                                                fraccionP�d,
                                            //dubilluz 15.08.2015 -- 
                                            VariablesFidelizacion.vDniCliente
                                                                ).trim();
                    
                        if (Double.parseDouble(sPrecioFinal) > precioVtaConDcto) {
                            precioVtaConDcto = Double.parseDouble(sPrecioFinal);
                        }
                    } catch (Exception e) {
                        log.debug("Exception e:" + e);
                    }
                        
                    }
                    //fin de verificacion de venta por debajo del costo promedio.
                    
                    //calculando el nuevo total
                    neoTotalXProd = (precioVtaConDcto * cantAplicable) + (precioVtaOrig * (cantPed - cantAplicable));
                    ahorro = (precioVtaOrig - precioVtaConDcto) * cantAplicable;
                    log.debug("ahorro:" + ahorro + ",ahorroAplicable:" + ahorroAplicable);
                    if (ahorro > ahorroAplicable) { //se volvera a calcular si el ahorro es superior al aplicable
                    //int cantUnidAplicables = Math.round( (float)( ( ( totalXProd - ahorroAplicable ) / precioVtaConDcto ) * fraccionP�d ));
                    
                    precioVtaConDcto = precioVtaOrig - (ahorroAplicable) / cantAplicable;
                    int cantAplicableAux2;
                    cantAplicableAux2 = cantAplicable;
                    
                    cantAplicable =
                            Math.round((float)UtilityVentas.Truncar((ahorroAplicable / (precioVtaOrig - precioVtaConDcto)),
                                                                    0));
                    
                    if (cantAplicableAux2 > cantAplicable) {
                        cantAplicable = cantAplicableAux2;
                        log.info("cantidad cambiada");
                    }
                    
                    neoTotalXProd = (precioVtaConDcto * cantAplicable) + (precioVtaOrig * (cantPed - cantAplicable));
                    log.debug("precioVtaConDcto:" + precioVtaConDcto);
                    log.debug("precioVtaOrig:" + precioVtaOrig);
                    log.debug("cantAplicable:" + cantAplicable);
                    ahorro = (precioVtaOrig - precioVtaConDcto) * cantAplicable;
                    log.debug("ahorro:" + ahorro);
                    }
                    log.debug("ahorro 2:" + ahorro);
                    /*
                                //--INICIO de verificar maximo descuento por dia del DNI
                                // DUBILLUZ 27.05.2009
                    
                    
                                if( (acumuladoDesctoPedido + ahorro) > VariablesFidelizacion.vMaximoAhorroDNIxPeriodo ){
                    
                                    //ahorro = (precioVtaOrig - precioVtaConDcto)*cantAplicable;
                                    log.info("precioVtaOrigl "+precioVtaOrig);
                                    log.info("VariablesFidelizacion.vMaximoAhorroDNIxPeriodo "+VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
                                    log.info("acumuladoDesctoPedido "+acumuladoDesctoPedido);
                                    log.info("cantAplicable "+cantAplicable);
                                    precioVtaConDcto = precioVtaOrig - (VariablesFidelizacion.vMaximoAhorroDNIxPeriodo-acumuladoDesctoPedido)/cantAplicable;
                                    log.info("**precioVtaConDcto "+precioVtaConDcto);
                    
                                    log.info("**ahorro "+ahorro);
                                    log.info("**ahorroAplicable "+ahorroAplicable);
                                    log.info("**precioVtaOrig "+precioVtaOrig);
                                    log.info("**precioVtaConDcto "+precioVtaConDcto);
                                    cantAplicableAux=cantAplicable;
                                    cantAplicable = Math.round( (float) UtilityVentas.Truncar( ( (VariablesFidelizacion.vMaximoAhorroDNIxPeriodo-acumuladoDesctoPedido)/(precioVtaOrig-precioVtaConDcto) ) , 0)  );
                                    if(cantAplicableAux>cantAplicable)
                                        cantAplicable = cantAplicableAux;
                    
                    
                                    log.info("**cantAplicable "+cantAplicable);
                                    log.info("-----");
                                    log.info("**precioVtaConDcto "+precioVtaConDcto);
                                    log.info("**precioVtaOrig "+precioVtaOrig);
                                    log.info("**cantPed "+cantPed);
                                    neoTotalXProd = (precioVtaConDcto * cantAplicable) + (precioVtaOrig * (cantPed - cantAplicable) );
                                    log.info("**neoTotalXProd "+neoTotalXProd);
                                    ahorro = (precioVtaOrig - precioVtaConDcto)*cantAplicable;
                                    log.info("-----");
                                    log.info("**precioVtaOrig "+precioVtaOrig);
                                    log.info("**precioVtaConDcto "+precioVtaConDcto);
                                    log.info("**cantAplicable "+cantAplicable);
                                }
                                acumuladoDesctoPedido += ahorro;
                                log.info("Descuento Parcial "+ahorro);
                                log.info("Descuento acumulado "+acumuladoDesctoPedido);
                                // Fin de Validacion de Maximo -- DUBILLUZ
                    
                                 * */
                    
                    /** hasta aqui se tiene cantidad al cual se va aplicar el dcto
                                 *  ahorro en monto
                                 *  nuevo total por producto
                                 * **/
                    
                    neoPrecioVtaXProd = UtilityVentas.Truncar(neoTotalXProd / cantPed, 3);
                    //por si deseara saber la diferencia
                    double diferencia = neoTotalXProd - UtilityVentas.Truncar(neoPrecioVtaXProd * cantPed, 2);
                    neoTotalXProd = UtilityVentas.Truncar(neoPrecioVtaXProd * cantPed, 2);
                    neoDctoPorcentaje =
                        UtilityVentas.Truncar(((precioVtaOrig - neoPrecioVtaXProd) / precioVtaOrig) * 100.0, 2);
                    
                    
                    //                neoPrecioVtaXProd =
                    //                        UtilityVentas.Redondear(neoTotalXProd / cantPed, 3);
                    //                //por si deseara saber la diferencia
                    //                double diferencia =
                    //                    neoTotalXProd - UtilityVentas.Redondear(neoPrecioVtaXProd *
                    //                                                          cantPed, 2);
                    //                neoTotalXProd =
                    //                        UtilityVentas.Redondear(neoPrecioVtaXProd * cantPed, 2);
                    //                neoDctoPorcentaje =
                    //                        UtilityVentas.Redondear(((precioVtaOrig - neoPrecioVtaXProd) /
                    //                                               precioVtaOrig) * 100.0, 2);
                    //ver si el ahorro calculado es mayor al que se tenia anteriormente
                    if (ahorro > mayorAhorro && cantUnidPed >= unidMinUso)
                    // el mont minimo de uso debe ser de todos los productos USO de la campana
                    // dubilluz 21.06.2011
                    //&&totalXProd >= montMinUso)
                    {
                    mayorAhorro = ahorro;
                    campMayorAhorro = codCampAux;
                    precioVtaMayorAhorro = neoPrecioVtaXProd;
                    totalVtaMayorAhorro = neoTotalXProd;
                    dctoVtaMayorAhorro = neoDctoPorcentaje;
                    cantPedMayorAhorro = cantAplicable;
                    
                    //valorCuponMayorAhorro = Double.parseDouble( mapaCupon.get("VALOR_CUPON").toString() );
                    //dubilluz 01.06.2010
                    valorCuponMayorAhorro = valorCuponNuevo;
                    flg_AcumulaMasAhorro = flg_AcumulaAhorro;
                    }
                    //fin de ver el maximo ahorro
                    
                    }//fin calculo de ahorro para cupones campana tipo MONTO
                 
                 /**aqui verificar si el producto y al campana a analizar es el ultimo**/
                 boolean flagAplicarMayorDcto = false;
                 if ((i + 1) == prodsCampanias.size()) { //si es el ultimo calcular aplicar el mayor dscto al producto
                 flagAplicarMayorDcto = true;
                 
                 } else { //quiere decir que no es el ultimo prod y campana a analizar
                 /**verificar si el cod_prod siguiente es diferente al que se tiene*/
                 mapaAux2 = (Map)prodsCampanias.get(i + 1);
                 if (!mapaAux2.get("COD_PROD").toString().equals(codProdAux)) { //si el producto siguiente es diferente tonces aplicar mayor dcto
                 flagAplicarMayorDcto = true;
                 }
                 }
                 
                 log.debug("flagAplicarMayorDcto:" + flagAplicarMayorDcto + ",mayorAhorro:" + mayorAhorro);
                 /**verificar si se aplica el mayor dscto**/
                 if (flagAplicarMayorDcto && mayorAhorro > 0.0) { //se agrego este flag para no repetir codigo
                 //--INICIO de verificar maximo descuento por dia del DNI
                 // DUBILLUZ 27.05.2009
                 if (((Map)VariablesVentas.vArrayList_Cupones.get(indiceCamp)).get("IND_FID").toString().trim().equals(FarmaConstants.INDICADOR_S) |
                 VariablesFidelizacion.vDniCliente.trim().length() > 0) {
                 //if (!indExcluyeProd_AHORRO_ACUM) {
                 if (!flg_AcumulaMasAhorro) {
                     if ((acumuladoDesctoPedido + mayorAhorro) > VariablesFidelizacion.vMaximoAhorroDNIxPeriodo) {
                         log.info("**mayorAhorro old " + mayorAhorro);
                 
                         //ahorro = (precioVtaOrig - precioVtaConDcto)*cantAplicable;
                         log.info("precioVtaOrigl " + precioVtaOrig);
                         log.info("VariablesFidelizacion.vMaximoAhorroDNIxPeriodo " +
                                  VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
                         log.info("acumuladoDesctoPedido " + acumuladoDesctoPedido);
                         log.info("cantAplicable " + cantAplicable);
                         mayorAhorro = VariablesFidelizacion.vMaximoAhorroDNIxPeriodo - acumuladoDesctoPedido;
                 
                         //ERIOS 2.4.5 Evalua cantidad aplicable
                         if (cantAplicable == 0)
                             cantAplicable = 1;
                 
                         precioVtaConDcto =
                                 precioVtaOrig - (VariablesFidelizacion.vMaximoAhorroDNIxPeriodo - acumuladoDesctoPedido) /
                                 cantAplicable;
                 
                         log.info("**precioVtaConDcto " + precioVtaConDcto);
                 
                         log.info("**ahorro " + ahorro);
                         log.info("**precioVtaOrig " + precioVtaOrig);
                         log.info("**precioVtaConDcto " + precioVtaConDcto);
                         cantAplicableAux = cantAplicable;
                         cantAplicable =
                                 Math.round((float)UtilityVentas.Truncar(((VariablesFidelizacion.vMaximoAhorroDNIxPeriodo -
                                                                           acumuladoDesctoPedido) /
                                                                          (precioVtaOrig - precioVtaConDcto)), 0));
                         if (cantAplicableAux > cantAplicable) {
                             cantAplicable = cantAplicableAux;
                             log.info("cantidad cambiada");
                         }
                 
                         log.info("**cantAplicable " + cantAplicable);
                         log.info("-----");
                         log.info("**precioVtaConDcto " + precioVtaConDcto);
                         log.info("**precioVtaOrig " + precioVtaOrig);
                         log.info("**cantPed " + cantPed);
                         neoTotalXProd =
                                 (precioVtaConDcto * cantAplicable) + (precioVtaOrig * (cantPed - cantAplicable));
                         log.info("**neoTotalXProd " + neoTotalXProd);
                         ahorro = (precioVtaOrig - precioVtaConDcto) * cantAplicable;
                         log.info("-----");
                         log.info("**precioVtaOrig " + precioVtaOrig);
                         log.info("**precioVtaConDcto " + precioVtaConDcto);
                         log.info("**cantAplicable " + cantAplicable);
                 
                         neoPrecioVtaXProd = UtilityVentas.Truncar(neoTotalXProd / cantPed, 3);
                         //por si deseara saber la diferencia
                         double diferencia = neoTotalXProd - UtilityVentas.Truncar(neoPrecioVtaXProd * cantPed, 2);
                         neoTotalXProd = UtilityVentas.Truncar(neoPrecioVtaXProd * cantPed, 2);
                 
                         neoDctoPorcentaje =
                                 UtilityVentas.Truncar(((precioVtaOrig - neoPrecioVtaXProd) / precioVtaOrig) *
                                                       100.0, 2);
                 
                 
                         //mayorAhorro          = ahorro;
                         campMayorAhorro = codCampAux;
                         precioVtaMayorAhorro = neoPrecioVtaXProd;
                         totalVtaMayorAhorro = neoTotalXProd;
                         dctoVtaMayorAhorro = neoDctoPorcentaje;
                         cantPedMayorAhorro = cantAplicable;
                 
                 
                         log.info("**mayorAhorro new " + mayorAhorro);
                 
                 
                     }
                     //KMONCADA 26.08.2015 ACUMULA SOLO PARA EL CASO DE CAMPA�AS QUE ACUMULEN AL DNI
                     acumuladoDesctoPedido += mayorAhorro;
                 }
                 // KMONCADA 26.08.2015 
                 //acumuladoDesctoPedido += mayorAhorro;
                 /*
                 if(!indExcluyeProd_AHORRO_ACUM)
                     VariablesFidelizacion.vAhorroDNI_Pedido += mayorAhorro;
                 */
                 log.info("Descuento Parcial " + mayorAhorro);
                 log.info("Descuento acumulado " + acumuladoDesctoPedido);
                 // Fin de Validacion de Maximo -- DUBILLUZ
                 }
                 
                 
                 log.debug("aplicando el dcto al producto : " +
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCodProd());
                     // KMONCADA 07.10.2016 [CONVENIOS CON CAMPA�AS]
                         if (VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
                         if(!UtilityConvenioBTLMF.isConvenioCompetencia(VariablesConvenioBTLMF.vCodConvenio.trim())){
                             boolean aplicaPrecioConvenio = false;
                             double precConvenio = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVValPrecioConvenio());
                             if(dctoVtaMayorAhorro>0){
                                 if(precConvenio<precioVtaMayorAhorro){
                                     // aplica precio convenio
                                     aplicaPrecioConvenio = true;
                                     dctoVtaMayorAhorro = 0;
                                 }
                             }else{
                                 // aplica precio convenio
                                 aplicaPrecioConvenio = true;
                             }
                             
                             if(aplicaPrecioConvenio){
                                 UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVPrecioBase(FarmaUtility.formatNumber(precConvenio, 3));
                                 double cantidad = FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCtdVta());
                                 String auxPrecAnt = "";
                                 if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                                     auxPrecAnt = FarmaUtility.formatNumber(precConvenio * cantidad, 3);
                                 } else {
                                     auxPrecAnt = FarmaUtility.formatNumber(precConvenio * cantidad);
                                 }
                                 precioVtaMayorAhorro = precConvenio;
                                 totalVtaMayorAhorro = FarmaUtility.getDecimalNumber(auxPrecAnt);
                             }
                             lstProdAplicaPrecioConvenio.add(indiceProducto);
                         }
                     }             
                 
                 
                 
                 //duda si hacer esto esta bien la parecer al hacer set solo estaria referenciando direcion de memoria a la
                 //variable se�alada ahi podria haber problemas, es solo una suposicion. JCALLO
                 if(dctoVtaMayorAhorro>0)
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVCodCupon(campMayorAhorro);
                 else
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVCodCupon("");
    
    
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVPctDcto(""+dctoVtaMayorAhorro);
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVPrecioVta(FarmaUtility.formatNumber(precioVtaMayorAhorro,3));
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVSubTotal(FarmaUtility.formatNumber(totalVtaMayorAhorro,2));
                     
                 // dubilluz 07.11.2012
                 //if (indExcluyeProd_AHORRO_ACUM) {
                 if(flg_AcumulaMasAhorro){
                 VariablesVentas.vListProdExcluyeAcumAhorro.add( UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCodProd());
                 }
                 
                 //
                 //guardado el detalle de los dcto aplicados por producto
                 Map mapaDctoProd = new HashMap();
                 mapaDctoProd.put("COD_PROD",UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCodProd());
                 mapaDctoProd.put("COD_CAMP_CUPON", campMayorAhorro);
                 mapaDctoProd.put("VALOR_CUPON", "" + valorCuponMayorAhorro);
                 mapaDctoProd.put("AHORRO", "" + mayorAhorro);
                 mapaDctoProd.put("DCTO_AHORRO", "" + dctoVtaMayorAhorro);
                 //JMIRANDA 30.10.09 A�ADE SEC DETALLE PEDIDO
                 mapaDctoProd.put("SEC_PED_VTA_DET", "" + (indiceProducto + 1));
                 //===============================
                 mapaDctoProd.put("PT_MULTIPLICA", (String)mapaAux.get("PT_MULTIPLICA"));
                 
                 log.debug("JM 30.10.09, SEC_PED_VTA_DET " + (indiceProducto + 1));
                 if(dctoVtaMayorAhorro>0)
                 VariablesVentas.vListDctoAplicados.add(mapaDctoProd);
                 //calculando el nuevo igv por producto con el dcto
                 //obteniendo el procentaje de igv a aplicar
                 double valorIgv =
                 FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVPctIgv());
                 //
                 double totalIgvProd = totalVtaMayorAhorro - totalVtaMayorAhorro / ((100.0 + valorIgv) / 100.0);
                 log.debug("TOTALX_PRODUCTO:" + totalVtaMayorAhorro + ",valorIgv:" + valorIgv + ",totalIgvProd:" +
                       totalIgvProd);
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVValIgv(FarmaUtility.formatNumber(totalIgvProd,
                                                                                                                     3));
                 
                 
                 /**actualizando la cantidad unidad como monto ahorro acumulado por campania aplicada*/
                 //buscado el mapa en el listado de campanas, la campania que mayor dcto le da al producto
                 int indiceProdCampApli = -1;
                 Map mapProdCampApli = new HashMap();
                 for (int m = 0; m < listaCampAhorro.size(); m++) {
                 mapProdCampApli = (Map)listaCampAhorro.get(m);
                 log.debug("ver si :" + (String)mapProdCampApli.get("COD_CAMP_CUPON") + ":=:" + campMayorAhorro);
                 if (((String)mapProdCampApli.get("COD_CAMP_CUPON")).equals(campMayorAhorro)) { //ve si existe un valor en mapa si tiene cod_camp_cupon
                     indiceProdCampApli = m;
                     break;
                 }
                 }
                 //obtiendo la campania al cual se ve aumentar las unididas como el ahorro acumulado por campania
                 mapProdCampApli = new HashMap();
                 mapProdCampApli = (Map)listaCampAhorro.get(indiceProdCampApli);
                 
                 double acuAhorro = Double.parseDouble(mapProdCampApli.get("AHORRO_ACUM").toString());
                 //double acuUnidad = Double.parseDouble(mapProdCampApli.get("UNID_ACUM").toString());
                 
                 acuAhorro += mayorAhorro;
                 //acuUnidad   += cantPedMayorAhorro/fraccionP�d;//a la cant del pedido diviendo entre el valorFracion del producto para obtener la UNIDADES
                 
                 ////todo esto se podria solo reemplazar por un simple put
                 //eliminando el mapa del listado de campanaDctos
                 listaCampAhorro.remove(indiceProdCampApli);
                 mapProdCampApli.remove("AHORRO_ACUM");
                 //mapProdCampApli.remove("UNID_ACUM");
                 log.info("acuAhorro:" + acuAhorro);
                 mapProdCampApli.put("AHORRO_ACUM", "" + acuAhorro);
                 //mapProdCampApli.put("UNID_ACUM", ""+acuUnidad);
                 //agregando el campDcto
                 listaCampAhorro.add(indiceProdCampApli, mapProdCampApli);
                 
                 //inicializando las variable de mayor ahorro por producto
                 mayorAhorro = 0.0;
                 campMayorAhorro = "";
                 precioVtaMayorAhorro = 0.0;
                 totalVtaMayorAhorro = 0.0;
                 dctoVtaMayorAhorro = 0.0;
                 cantPedMayorAhorro = 0;
                 //dubilluz -01.06.2010
                 valorCuponNuevo = 0.0;
                 }else{ //fin de recorrer todas las productos campanias aplicables
                 if(flagAplicarMayorDcto && mayorAhorro == 0.0){
                 //KMONCADA ACA TRIPLICA O DUPLICA O LO QUE QUIERA SIN DSCTO
                 String aux = (String)mapaAux.get("PT_MULTIPLICA");
                 double numero = Double.parseDouble(aux);
                 if(numero!= -1){
                     log.info("DEBERIA DE MULTIPLICAR PTOS");
                     log.info("--->"+numero, txtDescProdOculto);
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).setVCodCupon(campMayorAhorro);
                     //guardado el detalle de los dcto aplicados por producto
                     Map mapaDctoProd = new HashMap();
                     mapaDctoProd.put("COD_PROD", UtilityCalculoPrecio.getBeanPosDetalleVenta(indiceProducto).getVCodProd());
                     mapaDctoProd.put("COD_CAMP_CUPON", (String)mapaAux.get("COD_CAMP_CUPON"));
                     mapaDctoProd.put("VALOR_CUPON", "0");
                     mapaDctoProd.put("AHORRO", "0");
                     mapaDctoProd.put("DCTO_AHORRO", "0");
                     mapaDctoProd.put("SEC_PED_VTA_DET", "" + (indiceProducto + 1));
                     //===============================
                     mapaDctoProd.put("PT_MULTIPLICA", (String)mapaAux.get("PT_MULTIPLICA"));
                     VariablesVentas.vListDctoAplicados.add(mapaDctoProd);
                     
                 }
                 }
                 }
                }
             } //fin de recorrer todas las productos campanias aplicables            
         }

         //ANALIZANDO TODOS LOS PRODUCTOS CON CAMPANIAS TIPO DE MONTO
         //no se completo la logica, ya que no se tenia bien definido el manejo de este tipo de cupones
         //hasta la fecha 11.03.2009.

         /* DESCOMENTAR sE va implementar la logica de cupones tipo MONTO
           log.debug("analizando productos con CUPONES TIPO monto");
         for( int i = 0; i < prodsCampanias.size(); i++){
                 mapaAux    = (Map)prodsCampanias.get(i);
                 codProdAux = (String)mapaAux.get("COD_PROD");
                 codCampAux = (String)mapaAux.get("COD_CAMP_CUPON");
                 log.debug("JCALLO:analizando el prod:"+codProdAux+",cod_camp_cupon:"+codCampAux);
                 
                 //BUSCANDO EL INDICE DEL PRODUCTO EN EL ARREGLO DE RESUMEN PEDIDO AL CUAL APLICAR EL CUPON;
                 int indiceProducto = -1;
                 ArrayList listaDatosProd = new ArrayList();
                 for(int m=0;m<VariablesVentas.vArrayList_ResumenPedido.size();m++){
                         listaDatosProd = (ArrayList)VariablesVentas.vArrayList_ResumenPedido.get(m);
                         if ( ((String)listaDatosProd.get(0)).equalsIgnoreCase(codProdAux) ){//si codigo del producto a buscar coincide con el que se aplicar dcto
                                 indiceProducto = m;
                                 break;
                         }
                 }
                 log.debug("JCALLO:indiceProducto:"+indiceProducto);
                 //hasta aqui se tiene el indice donde se encuentra el producto al cual se le aplicar el dcto
                 
                 //BUSCANDO EL INDICE DE LA CAMPANA CUPON del listado de campanas cupones
                 int indiceCamp = -1;
                 Map mapTemp2 = new HashMap();
                 for(int m=0; m < VariablesVentas.vArrayList_Cupones.size() ; m++){
                         mapTemp2 = (Map)VariablesVentas.vArrayList_Cupones.get(m);
                         if ( ((String)mapTemp2.get("COD_CAMP_CUPON")).equals(codCampAux) ){//ve si existe un valor en mapa si tiene cod_camp_cupon
                                 indiceCamp = m;
                                 break;
                         }
                 }
                 log.debug("JCALLO:indiceCamp:"+indiceCamp);
                 //hasta aqui tenemos el indice donde se encuentra la campana cupon a aplicar
                 
                 //verificando los cupones de tipo MONTO
                 if( ((Map)VariablesVentas.vArrayList_Cupones.get(indiceCamp)).get("TIP_CUPON").toString()
                                 .equals(ConstantsVentas.TIPO_MONTO) ) {
                         //AQUI DEBE IR COMO APLICAR LOS CUPONES TIPO DE MONTO POR PRODUCTO.
                 }
         }
         */

         // KMONCADA 12.10.2016 [CONVENIOS CON CAMPA�AS]
         // PARA EL CASO DE QUE UN PEDIDO CONVENIO PERO NO CONVENIO COMPETENCIA.
         // SE ASIGNARA PRECIO DE CONVENIO PORQUE NO APLICO NINGUN DESCUENTO.
         if (VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF && !UtilityConvenioBTLMF.isConvenioCompetencia(VariablesConvenioBTLMF.vCodConvenio.trim())) {
             for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                 boolean aplicoPrecio = false;
                 for(int k=0; k< lstProdAplicaPrecioConvenio.size(); k++){
                     if(i==k){
                         aplicoPrecio = true;
                         break;
                     }
                 }
                 if(!aplicoPrecio){
                     // OBTIENE PRECIO DE CONVENIO, PARA EL RECALCULO DE LOS DATOS.
                     double auxPrecio = FarmaUtility.getDecimalNumber( UtilityCalculoPrecio.getBeanPosDetalleVenta(i).getVValPrecioConvenio());
                     double auxCantidad =  FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(i).getVCtdVta());

                     String auxPrecAnt;
                     try {
                         if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                             VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
                         }
                     } catch (Exception ex) {
                         log.error("", ex);
                     }
                     if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                         auxPrecAnt = FarmaUtility.formatNumber(auxPrecio * auxCantidad, 3);
                     } else {
                         auxPrecAnt = FarmaUtility.formatNumber(auxPrecio * auxCantidad);
                     }
                     //JCHAVEZ 29102009 fin
                     double porcIgv =
                         FarmaUtility.getDecimalNumber(UtilityCalculoPrecio.getBeanPosDetalleVenta(i).getVPctIgv());
                     double precioTotal = auxPrecio * auxCantidad;
                     double totalIgv = precioTotal - (precioTotal / (1 + porcIgv / 100));
                     String vTotalIgv = FarmaUtility.formatNumber(totalIgv);
                     String cPrecioFinal = FarmaUtility.formatNumber(auxPrecio, 3);
                     String cPrecioVta = FarmaUtility.formatNumber(auxPrecio, 3);


                     UtilityCalculoPrecio.getBeanPosDetalleVenta(i).setVValIgv(vTotalIgv);
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(i).setVCodCupon("");
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(i).setVPctDcto("");
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(i).setVPrecioBase(cPrecioVta);
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(i).setVPrecioVta(cPrecioFinal);
                     UtilityCalculoPrecio.getBeanPosDetalleVenta(i).setVSubTotal(auxPrecAnt);
                      
                 }
             }
         }
         
         log.info("Ahorror Actual Total del Pedido " + VariablesFidelizacion.vAhorroDNI_Pedido);
         
         //KMONCADA 28.08.2015 SE MODIFICA EL ORDEN DEL PROCESO DE CALCULO DE DESCTOS
         /*********************************************************************
          **************2. CALCULO DE CAMPA�AS POR PRECIOS FIJOS***************
          *********************************************************************/
         
         //04-DIC-13 TCT Begin Aplicacion de Precio  Fijo x Campa�a
         //FarmaUtility.showMessage(this, "Antes de Aplicar Ultimo Descuento",null);
         log.debug("### TCT 001 VariablesVentas.vEsPedidoConvenio: " + VariablesVentas.vEsPedidoConvenio);
         log.debug("### TCT 001 VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF: " +
                   VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF);
         // 10.- Begin if no convenios
         if (!VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
             for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                 BeanDetalleVenta bean  = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
                 String codProd    = bean.getVCodProd();
                 double precioUnit =  //FarmaUtility.getDecimalNumber(bean.getPPREC_VTA_UNIT_NVO()); // dubilluz 2016.12.29
                                      FarmaUtility.getDecimalNumber(bean.getVPrecioBase());
                 double precioVentaUnit = FarmaUtility.getDecimalNumber(bean.getVPrecioVta());
                 double precioVtaTotal =FarmaUtility.getDecimalNumber(bean.getVSubTotal());
                 double cantidad = FarmaUtility.getDecimalNumber(bean.getVCtdVta());
                 int fraccionPed = Integer.parseInt(bean.getVValFracVta());

                 // Lectura de Datos de Campa�a con el Mejor Precio de Promocion (el mas bajo)
                 Map mapCampPrec = new HashMap();
                 try {
                     String cadena_Cupon="";
                     if(VariablesVentas.vArrayList_CuponesFijos.size()>0){
                         //Map mapCuponFijo = new HashMap();
                         //mapCuponFijo=(Map)VariablesVentas.vArrayList_CuponesFijos.get(0).toString();
                         cadena_Cupon=((Map)VariablesVentas.vArrayList_CuponesFijos.get(0)).get("COD_CUPON").toString();
                         for(int r=1;r<VariablesVentas.vArrayList_CuponesFijos.size();r++){
                             cadena_Cupon=cadena_Cupon+"@"+((Map)VariablesVentas.vArrayList_CuponesFijos.get(i)).get("COD_CUPON").toString();
                         }
                     }

                     mapCampPrec = DBVentas.getDatosCampPrec(codProd,cadena_Cupon,VariablesFidelizacion.vDniCliente.trim());
                     /*** INICIO ARAVELLO 04/07/2019 ***/
                     UtilityVentas.aplicaMejorDsctoClubIntercorp(mapCampPrec,codProd,cantidad,fraccionPed, precioVtaTotal,cadena_Cupon,VariablesFidelizacion.vDniCliente.trim());
                     /*** FIN    ARAVELLO 04/07/2019 ***/
//muestraMsj(cadena_Cupon+" mapCampPrec.size() "+mapCampPrec.size() );
                 } catch (SQLException e) {

                     FarmaUtility.showMessage(this, "Ocurrio un error al obtener datos de la Campa�a por Precio.\n" +
                             e.getMessage() +
                             "\n Int�ntelo Nuevamente\n si persiste el error comuniquese con operador de sistemas.",
                             null);

                 }
                 log.debug("######## TCT 10: mapCampPrec:" + mapCampPrec);

                 // Verificar si el Precio de Campa�a x Precio es < al Precio ya Calculado => Calcular Nuevamente
                 if (mapCampPrec.size() > 0) {
                     double vd_Val_Prec_Prom, vd_Val_Prec_Prom_frac;
                     String vs_Val_Prec_Prom = (String)mapCampPrec.get("VAL_PREC_PROM_ENT");
                     String vs_Cod_Camp_Prec = (String)mapCampPrec.get("COD_CAMP_CUPON");
                     log.debug("###### TCT 11 : vs_Val_Prec_Prom: " + vs_Val_Prec_Prom);

                     //ERIOS 03.01.2013 Logica de tratamiento
                     vd_Val_Prec_Prom = FarmaUtility.getDecimalNumber(vs_Val_Prec_Prom);
                     int enteros = (new Double(cantidad)).intValue() / fraccionPed;
                     int fracciones = (new Double(cantidad)).intValue() % fraccionPed;
                     vd_Val_Prec_Prom_frac = UtilityVentas.Redondear(vd_Val_Prec_Prom / fraccionPed, 2);
                     /*
                     cantAplicable=Integer.parseInt(mapCampPrec.get("UNID_MAX_PROD").toString());
                     
                     calculoDescuentoFinal(codProd, vs_Cod_Camp_Prec, cantidad, cantAplicable, 
                                           fraccionPed, mapCampPrec);
                     */
                     double totalVentaCamp = enteros * vd_Val_Prec_Prom + fracciones * vd_Val_Prec_Prom_frac;

                     //&&&&&&&&&&&&&&&& CAMBIAR DATOS DE LINEA DE PRODUCTO X CAMPA�A DE PRECIO &&&&&&&&&&&&&&&&&&&&&&&&
                     if (precioVtaTotal > totalVentaCamp) {
                         double valAhorro = precioVtaTotal-totalVentaCamp;
                         double precioAnt = 0.0;
                         // KMONCADA 20.05.2015 SE CALCULA 
                         if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                             precioAnt = UtilityVentas.Redondear(precioUnit * cantidad, 3);
                         } else {
                             precioAnt = precioUnit * cantidad;
                         }
                         dctoVtaMayorAhorro = UtilityVentas.Redondear((1 - totalVentaCamp / precioAnt) * 100, 2);
                         //dctoVtaMayorAhorro = UtilityVentas.Redondear((1 - totalVentaCamp / precioVtaTotal) * 100, 2);
                         precioVtaTotal = totalVentaCamp;

                         //ahorroAcumulado =  Double.parseDouble(mapaCampProd.get("AHORRO_ACUM").toString());
                         bean.setVCodCupon(vs_Cod_Camp_Prec);
                         bean.setVPctDcto(""+dctoVtaMayorAhorro);

                         double porcIgv = FarmaUtility.getDecimalNumber(bean.getVPctIgv());

                         double totalIgv = precioVtaTotal - (precioVtaTotal / (1 + porcIgv / 100));
                         String vTotalIgv = FarmaUtility.formatNumber(totalIgv);

                         bean.setVValIgv(vTotalIgv);
                         
                         ////////////////////dubilluz 14.04.2015 //////////////////////////////////////////
                         Map mapAux = new HashMap();
                         String pCodigo  = "";
                         // 1- quitando el map de ahorro producto x camapana
                         for(int j=0;j<VariablesVentas.vListDctoAplicados.size();j++){
                             mapAux = (Map)VariablesVentas.vListDctoAplicados.get(j);
                             pCodigo = mapAux.get("COD_PROD").toString().trim();
                             if(pCodigo.trim().equalsIgnoreCase(codProd)){
                                 VariablesVentas.vListDctoAplicados.remove(j);
                                 break;
                             }
                         }
                         // 2- agrega el ahorro de prod campana
                         mapAux = new HashMap();
                         mapAux.put("COD_PROD",codProd);
                         mapAux.put("COD_CAMP_CUPON", vs_Cod_Camp_Prec);   
                         mapAux.put("VALOR_CUPON", "" + dctoVtaMayorAhorro);  
                         mapAux.put("AHORRO", "" + "" +valAhorro);   
                         mapAux.put("DCTO_AHORRO", "" +dctoVtaMayorAhorro); 
                         //JMIRANDA 30.10.09 A�ADE SEC DETALLE PEDIDO
                         mapAux.put("SEC_PED_VTA_DET", "" + (i + 1));
                         VariablesVentas.vListDctoAplicados.add(mapAux);
                         ////////////////////dubilluz 14.04.2015 //////////////////////////////////////////                        

                     }

                 }
                 
                 //KMONCADA 18.08.2015 PARA EL CASO DE CAMPA�AS NO SE APLICARA O SE UTILIZARA EL TAB GRAL DE REDONDEO
                 String codPromAplica = bean.getVCodCupon();
                 
                 if(codPromAplica==null){
                     codPromAplica = "";
                 }

                 log.debug("precioVtaTotal: " + precioVtaTotal);
                 log.debug("precioVentaUnit: " + precioVentaUnit);
                 try {
                     //KMONCADA 18.08.2015 PARA EL CASO DE CAMPA�AS NO SE APLICARA O SE UTILIZARA EL TAB GRAL DE REDONDEO
                     double precioVtaTotalRedondeado = 0;
                     double precioUnitRedondeado = 0;
                     //dubilluz 24.11.2016
                     if(codPromAplica.trim().length()== 0){
                         // si es bolsa no redondea
                         if(DBVentas.isProdBolsa(bean.getVCodProd())){
                             precioVtaTotalRedondeado = precioVtaTotal;
                             precioUnitRedondeado = precioUnit;                             
                         }
                         else{
                             precioVtaTotalRedondeado = DBVentas.getPrecioRedondeado(precioVtaTotal);
                             precioUnitRedondeado = DBVentas.getPrecioRedondeado(precioUnit);
                             
                         }
                         
                     }else{
                         precioVtaTotalRedondeado = precioVtaTotal;
                         precioUnitRedondeado = precioUnit;
                     }
                     //dubilluz 24.11.2016
                     
                     //precioVtaTotalRedondeado = precioVtaTotal;
                     //precioUnitRedondeado = precioUnit;
                     
                     double precioVentaUnitRedondeado = UtilityVentas.getDivideDouble(precioVtaTotalRedondeado,cantidad);
                                                        //precioVtaTotalRedondeado / cantidad;
                     
                     log.debug("precioVtaTotalRedondeado: " + precioVtaTotalRedondeado);
                     log.debug("precioVentaUnitRedondeado: " + precioVentaUnitRedondeado);

                     double pAux2;
                     double pAux5;
                     pAux2 = UtilityVentas.Redondear(precioVentaUnitRedondeado, 3);
                     log.debug("pAux2: " + pAux2);
                     
                     if (pAux2 < precioVentaUnitRedondeado) {
                         pAux5 = (pAux2 * Math.pow(10, 2) + 1) / 100;
                         log.debug("pAux5: " + pAux5);
                     } else {
                         pAux5 = pAux2;
                     }


                     String cprecioVtaTotalRedondeado = FarmaUtility.formatNumber(precioVtaTotalRedondeado, 3);
                     String cprecioVentaUnitRedondeado =
                         FarmaUtility.formatNumber(pAux5,3); //precioVentaUnitRedondeado,3
                     String cprecioUnitRedondeado = FarmaUtility.formatNumber(precioUnitRedondeado, 3);

                  
                 //TCT Seteos Originales
                 //((ArrayList)VariablesVentas.vArrayList_ResumenPedido.get(i)).set(3,
                 //                                                                 cprecioUnitRedondeado);
                 //((ArrayList)VariablesVentas.vArrayList_ResumenPedido.get(i)).set(6,
                 //                                                                 cprecioVentaUnitRedondeado);
                 //((ArrayList)VariablesVentas.vArrayList_ResumenPedido.get(i)).set(7,
                 //                                                                 cprecioVtaTotalRedondeado);

                     // Get Datos de Precio de Promocion si Existe


                     log.debug("#### 01.- TCT VariablesVentas.vArrayList_ResumenPedido: ");

                     //ERIOS 06.08.2015 Se comenta el redondeo del precio unitario 
                     //((ArrayList)VariablesVentas.vArrayList_ResumenPedido.get(i)).set(3, cprecioUnitRedondeado);
                     bean.setVPrecioVta(cprecioVentaUnitRedondeado);
                     bean.setVSubTotal(cprecioVtaTotalRedondeado);

                     log.debug("#### 02.- TCT VariablesVentas.vListDctoAplicados: " +
                               VariablesVentas.vListDctoAplicados);
                     log.debug("#### 03.- TCT VariablesVentas.vArrayList_ResumenPedido: " );
                     log.debug("#### 04.- TCT  listaCampAhorro: " + listaCampAhorro);

                     log.debug("precioVtaTotalRedondeado: " + "" + cprecioVtaTotalRedondeado);
                     log.debug("precioVentaUnitRedondeado: " + cprecioVentaUnitRedondeado);
                     log.debug("precioUnitRedondeado: " + cprecioUnitRedondeado);
                     //}else{
                     //    log.info("PRODUCTO NO APLICARA LA LOGICA DE TAB DE REDONDEO POR TENER CAMPA�A LO REALIZARA EL ALGORITMO DE RECALCULO --> \nPRODUCTO:" +codProd+"\nCAMPA�A :"+codPromAplica);
                     //}
                 } catch (Exception ex) {
                     log.error("", ex);
                 }

                 log.debug("codProd: " + codProd);
                 log.debug("precioUnit: " + precioUnit);
                 log.debug("precioVentaUnit: " + precioVentaUnit);
                 log.debug("precioVtaTotal: " + precioVtaTotal);
                 log.debug("cantidad: " + cantidad);
             }
             
         } // if no convenio convenio
         //04-DIC-13 End
         /*  */
         /*********************************************************************
          ************3. RECALCULO DE DSCTO A LAS CAMPA�AS APLICADAS***********
          *********************************************************************/
         
         //if (!VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
             // KMONCADA 15.05.2015 SE RECALCULA LOS DSCTO EN BASE AL REDONDEO DEL SUBTOTAL X PRODUCTO
             try{
                recalculoDscto();
             }catch(Exception ex){
                 log.info("ERROR EN EL REDONDEO DE DSCTO");
             }
         //}
         
         /*********************************************************************
          **************4. REDONDEO A LOS PRODUCTOS DEL PEDIDO*****************
          **************** NO INCLUYE PRODUCTOS EN CAMPA�A*********************
          *********************************************************************/
         
         //JCHAVEZ 29102009 inicio

         try {
             if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                 VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
                 //KMONCADA 18.08.2015 PARA EL CASO DE CAMPA�AS NO SE APLICARA O SE UTILIZARA EL TAB GRAL DE REDONDEO
                 //VariablesVentas.vIndAplicaRedondeo = "N";
             }
         } catch (Exception ex) {
             log.error("", ex);
         }
         if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
             //JCHAVEZ 29102009 inicio Redondeando montos
             for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                 BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
                 
                 //KMONCADA 18.08.2015 PARA EL CASO DE CAMPA�AS NO SE APLICARA O SE UTILIZARA EL TAB GRAL DE REDONDEO
                 String codPromAplica = bean.getVCodCupon();
                 String codProd = bean.getVCodProd();
                 if(codPromAplica==null){
                     codPromAplica = "";
                 }
                 if(codPromAplica.trim().length()> 0){
                     log.info("PRODUCTO NO APLICARA LA LOGICA DE TAB DE REDONDEO POR TENER CAMPA�A LO REALIZARA EL ALGORITMO DE RECALCULO --> \nPRODUCTO:" +codProd+"\nCAMPA�A :"+codPromAplica);
                 }else{
                     double precioUnit = //FarmaUtility.getDecimalNumber(bean.getPPREC_VTA_UNIT_NVO()); // dubilluz 2016.12.29
                                          FarmaUtility.getDecimalNumber(bean.getVPrecioBase());
                     double precioVentaUnit = FarmaUtility.getDecimalNumber(bean.getVPrecioVta());
                     double precioVtaTotal = FarmaUtility.getDecimalNumber(bean.getVSubTotal());
                     double cantidad = FarmaUtility.getDecimalNumber(bean.getVCtdVta());
                     double precioVtaTotalRedondeado = 0;
                     double pAux5 = 0;
                     double precioUnitRedondeado = 0;
                     String cprecioVtaTotalRedondeado = "";
                     String cprecioVentaUnitRedondeado = "";
                     //KMONCADA 21.06.2016 VERIFICA SI EL PRODUCTO SE TOMARA COMO GRATUITO
                     if (UtilityConvenioBTLMF.isVtaProdConvenioGratuito("", VariablesConvenioBTLMF.vCodConvenio, codProd)){
                         //int totalRegistros = VariablesVentas.vArrayList_ResumenPedido.size();
                         // CUANDO EXISTA MAS DE UN PRODUCTO EN EL PEDIDO SE CARGARA 0.01
                         precioVtaTotalRedondeado = 0.04;
                         pAux5 =    UtilityVentas.getDivideDouble(precioVtaTotalRedondeado, cantidad);
                                    //precioVtaTotalRedondeado/cantidad;
                     }else{
                         log.debug("precioVtaTotal: " + precioVtaTotal);
                         log.debug("precioVentaUnit: " + precioVentaUnit);
                         try {
                             precioVtaTotalRedondeado = precioVtaTotal;
                                 //dubilluz 24.11.201 OJO NO TOCAR   
                                 //DBVentas.getPrecioRedondeado(precioVtaTotal);
                             double precioVentaUnitRedondeado = UtilityVentas.getDivideDouble(precioVtaTotalRedondeado, cantidad);
                                 //precioVtaTotalRedondeado / cantidad;
                             precioUnitRedondeado = precioUnit;
                                 //dubilluz 24.11.201 OJO NO TOCAR   
                                 //DBVentas.getPrecioRedondeado(precioUnit);
                             log.debug("precioVtaTotalRedondeado: " + precioVtaTotalRedondeado);
                             log.debug("precioVentaUnitRedondeado: " + precioVentaUnitRedondeado);
                             double pAux2;
                             pAux2 = UtilityVentas.Redondear(precioVentaUnitRedondeado, 3);
                             log.debug("pAux2: " + pAux2);
                             if (pAux2 < precioVentaUnitRedondeado) {
                                 pAux5 = (pAux2 * Math.pow(10, 2) + 1) / 100;
                                 log.debug("pAux5: " + pAux5);
                             } else {
                                 pAux5 = pAux2;
                             }
                         } catch (Exception ex) {
                             log.error("", ex);
                         }
                     }
     
                     cprecioVtaTotalRedondeado = FarmaUtility.formatNumber(UtilityVentas.ajustaMontoGo(precioVtaTotalRedondeado),2);
                     
                         //FarmaUtility.formatNumber(precioVtaTotalRedondeado, 2);
                     cprecioVentaUnitRedondeado = FarmaUtility.formatNumber(pAux5,3); /*precioVentaUnitRedondeado,3*/
                     String cprecioUnitRedondeado = FarmaUtility.formatNumber(precioUnitRedondeado, 3);
                     //ERIOS 06.08.2015 Se comenta el redondeo del precio unitario 
                     //((ArrayList)VariablesVentas.vArrayList_ResumenPedido.get(i)).set(3, cprecioUnitRedondeado);
                     bean.setVPrecioVta(cprecioVentaUnitRedondeado);
                     bean.setVSubTotal(cprecioVtaTotalRedondeado);
                     bean.calculoPctDcto();
                     log.debug("precioVtaTotalRedondeado: " + "" + cprecioVtaTotalRedondeado);
                     log.debug("precioVentaUnitRedondeado: " + cprecioVentaUnitRedondeado);
                     log.debug("precioUnitRedondeado: " + cprecioUnitRedondeado);

                     
     
                     log.debug("codProd: " + codProd);
                     log.debug("precioUnit: " + precioUnit);
                     log.debug("precioVentaUnit: " + precioVentaUnit);
                     log.debug("precioVtaTotal: " + precioVtaTotal);
                     log.debug("cantidad: " + cantidad);
                 }
             }
             //JCHAVEZ 29102009 fin
         }

            
         for(int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
             BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i); 
             bean.calculoPctDcto();
         }
         
         return true; //por ahora dejando asi nomas jeje

     } //fin del metodo de calculo y aplicacion de descuentos por CAMPANAS CUPONES
     
    /**
     * Metodo que realiza el recalculo para redondear a un decimal el valor subtotal de cada producto, redondeando su %dscto y precio unitario
     * @author KMONCADA
     * @since 15.05.2015
     * @throws Exception
     */
    private void recalculoDscto()throws Exception{
        log.info("======================================================================");
        log.info("=======================RECALCULO DE DSCTOS============================");
        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
            BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
            String codProd = bean.getVCodProd();
            double pctDscto  = FarmaUtility.getDecimalNumber(bean.getVPctDcto());
            String vMontoSubTotal = bean.getVSubTotal();
            
            double precioVentaUnitario =//FarmaUtility.getDecimalNumber(bean.getPPREC_VTA_UNIT_NVO());//dubilluz 2016.12.29
                                        FarmaUtility.getDecimalNumber(bean.getVPrecioBase());
            String codCampAplica = bean.getVCodCupon();
            // SACAR QUE CAMPANA
            String vCodCamp_in = "";
            Map mAux = new HashMap();
            String pCodProd  = "";
            
            for(int j=0;j<VariablesVentas.vListDctoAplicados.size();j++){
                mAux = (Map)VariablesVentas.vListDctoAplicados.get(j);
                pCodProd = mAux.get("COD_PROD").toString().trim();
                if(pCodProd.trim().equalsIgnoreCase(codProd)){
                    vCodCamp_in = mAux.get("COD_CAMP_CUPON").toString().trim();
                    break;
                }
            }
            boolean aplicaRedondeo = true;
            if(vCodCamp_in.trim().length()>0){
                aplicaRedondeo = UtilityFidelizacion.getIndRedondeaPrecioCampana(vCodCamp_in);//vCodCamp_in
            }
            
            log.info("PRODUCTO --> " + codProd);
            log.info("DSCTO INICIAL : " + pctDscto);
            log.info("VALOR SUBTOTAL : " + vMontoSubTotal);
            log.info("PRECIO DE VENTA UNITARIO : " + precioVentaUnitario);
            log.info("CAMPA�A : " + vCodCamp_in+" APLICA REDONDEO : "+aplicaRedondeo);
            
            // KMONCADA 20.05.2015 SOLO REALIZARA EL CALCULO PARA LOS P.U MAYORES A 1
            if(pctDscto != 0  && !isRedondeado(vMontoSubTotal) && aplicaRedondeo /*&& precioVentaUnitario > 1*/){
                // obtiene valores iniciales
                double precioSubTotalNetoInicial = FarmaUtility.getDecimalNumber(bean.getVSubTotal());
                double cantProducto = FarmaUtility.getDecimalNumber(bean.getVCtdVta());
                double pctIgv = FarmaUtility.getDecimalNumber(bean.getVPctIgv());
                
                // VALOR DE VENTA SIN DSCTOS
                double precioSubTotalBruto = precioVentaUnitario * cantProducto;
                try {
                    if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                        //VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
                        //KMONCADA 18.08.2015 PARA EL CASO DE CAMPA�AS NO SE APLICARA O SE UTILIZARA EL TAB GRAL DE REDONDEO
                        VariablesVentas.vIndAplicaRedondeo = "N";
                    }
                } catch (Exception ex) {
                    log.error("", ex);
                }

                if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                    precioSubTotalBruto = UtilityVentas.Redondear(precioSubTotalBruto, 3);
                } else {
                    precioSubTotalBruto = UtilityVentas.Redondear(precioSubTotalBruto, 2);
                }
                // SE APLICA REDONDEO AL PRECIO FINAL DEL PRODUCTO, EL PRECIO REDONDEO SIEMPRE HACIA ARRIBA
                double precioSubTotalNetoFinal = UtilityVentas.Redondear(precioSubTotalNetoInicial+0.05, 1);
                // CALCULA EL NUEVO PORCENTAJE DE DSCTO
                double pctDsctoNew = UtilityVentas.Redondear((1 - precioSubTotalNetoFinal / precioSubTotalBruto) * 100, 2);
                // CALCULA NUEVO PRECIO UNITARIO DEL PRODUCTO
                double precioVentaUnitRedondeado = precioSubTotalNetoFinal / cantProducto;
                
                
                log.info("CANTIDAD DE PRODUCTO --> " + cantProducto);
                log.info("PRECIO DE VENTA TOTAL SIN DSCTO : " + precioSubTotalBruto);
                log.info("PRECIO DE VENTA UNIT INICIAL C/DSCTO : " + bean.getVPrecioVta());
                log.info("PRECIO DE VENTA TOTAL INICIAL C/DSCTO : " + precioSubTotalNetoInicial);
                log.info("PCT IGV : " + pctIgv);
                log.info("VALOR IGV INICIAL : " + bean.getVValIgv());
                
                double pAux2;
                double pAux5;
                pAux2 = UtilityVentas.Redondear(precioVentaUnitRedondeado, 2);
                log.debug("pAux2: " + pAux2);
                if (pAux2 < precioVentaUnitRedondeado) {
                    pAux5 = (pAux2 * Math.pow(10, 2) + 1) / 100;
                    log.debug("pAux5: " + pAux5);
                } else {
                    pAux5 = pAux2;
                }
                
                double montoTotalIgv = precioSubTotalNetoFinal - (precioSubTotalNetoFinal / (1 + pctIgv / 100));
                double valAhorro = precioSubTotalBruto - precioSubTotalNetoFinal;
                
                if(precioVentaUnitario<=pAux5){
                    log.info("SE CANCELA EL DSCTO OTORGADO, PRECIO VTA :"+precioVentaUnitario+" PRECIO REDONDEADO: "+pAux5);
                    pctDsctoNew = 0;
                    pAux5 = precioVentaUnitario;
                    precioSubTotalNetoFinal = pAux5 * cantProducto;
                    montoTotalIgv = precioSubTotalNetoFinal - (precioSubTotalNetoFinal / (1 + pctIgv / 100));
                    valAhorro = 0;
                    codCampAplica = "";
                }
                
                String cPrecioVtaTotalRedondeado = FarmaUtility.formatNumber(precioSubTotalNetoFinal, 3);
                String cPrecioVentaUnitRedondeado = FarmaUtility.formatNumber(pAux5); 
                String cTotalIgv = FarmaUtility.formatNumber(montoTotalIgv, 3);
                String cPctNuevo = FarmaUtility.formatNumber(pctDsctoNew, 3);
                
                log.info("DSCTO FINAL : " + cPctNuevo);
                log.info("PRECIO DE VENTA UNIT FINAL C/DSCTO : " + cPrecioVentaUnitRedondeado);
                log.info("PRECIO DE VENTA TOTAL FINAL C/DSCTO : " + cPrecioVtaTotalRedondeado);
                log.info("PCT IGV : " + pctIgv);
                log.info("VALOR IGV FINAL : " + cTotalIgv);
                log.info("VALOR DE AHORRO FINAL : " + valAhorro);
                
                //if (aplicaRedondeo) {
                //columna de porcentaje descuento
                bean.setVPctDcto(cPctNuevo);
                bean.setVPrecioVta(cPrecioVentaUnitRedondeado);
                bean.setVSubTotal(cPrecioVtaTotalRedondeado);
                bean.setVValIgv(cTotalIgv);
                bean.setVCodCupon(codCampAplica);

                Map mapAux = new HashMap();
                String pCodigo = "";
                String vCodCampCupon = "";
                // 1- quitando el map de ahorro producto x camapana
                for (int j = 0; j < VariablesVentas.vListDctoAplicados.size(); j++) {
                    mapAux = (Map)VariablesVentas.vListDctoAplicados.get(j);
                    pCodigo = mapAux.get("COD_PROD").toString().trim();
                    if (pCodigo.trim().equalsIgnoreCase(codProd)) {
                        vCodCampCupon = mapAux.get("COD_CAMP_CUPON").toString().trim();
                        VariablesVentas.vListDctoAplicados.remove(j);
                        break;
                    }
                }

                // 2- agrega el ahorro de prod campana aun array previo
                mapAux = new HashMap();
                mapAux.put("COD_PROD", codProd);
                mapAux.put("COD_CAMP_CUPON", vCodCampCupon);
                mapAux.put("VALOR_CUPON", "" + pctDsctoNew);
                mapAux.put("AHORRO", "" + "" + valAhorro);
                mapAux.put("DCTO_AHORRO", "" + pctDsctoNew);
                //JMIRANDA 30.10.09 A�ADE SEC DETALLE PEDIDO
                mapAux.put("SEC_PED_VTA_DET", "" + (i + 1));
                // KMONCADA 19.08.2015 los ahorros igual a 0 no se registraran
                if(valAhorro>0){
                    VariablesVentas.vListDctoAplicados.add(mapAux);
                }
                /*}else {
                    log.info("no aplica redondeo ");    
                }*/

            }else {
                log.info("no aplica redondeo ");    
            }
            log.info("***************************************************************************");
        }
    }
    
    /**
     * Metodo que valida si monto cuenta ya con redondeo.
     * @author KMONCADA
     * @since 15.05.2015
     * @param pMonto
     * @return
     */
    private boolean isRedondeado(String pMonto){
        BigDecimal bMontoUnDecimal = BigDecimal.valueOf(FarmaUtility.getDecimalNumber(pMonto));
        bMontoUnDecimal = bMontoUnDecimal.setScale(1, RoundingMode.FLOOR);
        
        BigDecimal bMontoDosDecimal = BigDecimal.valueOf(FarmaUtility.getDecimalNumber(pMonto));
        bMontoDosDecimal = bMontoDosDecimal.setScale(2, RoundingMode.FLOOR);
        
        BigDecimal resto = bMontoDosDecimal.subtract(bMontoUnDecimal);
        log.info("MONTO A VALIDAR REDONDEO --> "+ pMonto);
        log.info("MONTO A UN DECIMAL --> " + bMontoUnDecimal);
        log.info("MONTO A DOS DECIMAL --> " + bMontoDosDecimal);
        log.info("RESTO DE AMBOS --> " + resto);
        if(resto.compareTo(BigDecimal.ZERO) == 0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * nuevo metodo encargado de resumen pedido
     * @author jcallo
     * **/
    private void neoOperaResumenPedido() {

        if (VariablesFidelizacion.vSIN_COMISION_X_DNI)
            lblDNI_SIN_COMISION.setVisible(true);
        else
            lblDNI_SIN_COMISION.setVisible(false);

        if (VariablesFidelizacion.V_NUM_CMP.trim().length() > 0) {
            lblMedico.setText("Receta de : " + VariablesFidelizacion.V_NUM_CMP + "-" + VariablesFidelizacion.V_NOMBRE +
                              " / " + VariablesFidelizacion.V_DESC_TIP_COLEGIO);
            lblMedico.setVisible(true);
        } else {
            lblMedico.setText("");
            lblMedico.setVisible(false);
        }
        //16-AGO-13 TCT Imprime Promociones Habilitadas
        log.debug("<<TCT 2>>Lista Promociones Habilitadas");
        log.debug("VariablesVentas.VariablesVentas.vArrayList_Promociones=>" + VariablesVentas.vArrayList_Promociones);

        //jcallo quitar las campa�as que ya han terminado de ser usados por el cliente
        log.debug("quitando las campa�as limitadas en numeros de usos del cliente");

        log.debug("VariablesVentas.vArrayList_CampLimitUsadosMatriz:" +
                  VariablesVentas.vArrayList_CampLimitUsadosMatriz);
        for (int i = 0; i < VariablesVentas.vArrayList_CampLimitUsadosMatriz.size(); i++) {
            String cod_camp_limit =
                VariablesVentas.vArrayList_CampLimitUsadosMatriz.get(i).toString().trim(); //por culpa de diego
            for (int j = 0; j < VariablesVentas.vArrayList_Cupones.size(); j++) {
                String cod_camp_cupon =
                    ((Map)VariablesVentas.vArrayList_Cupones.get(j)).get("COD_CAMP_CUPON").toString();
                if (cod_camp_limit.equals(cod_camp_cupon)) {
                    log.debug("quitando cupon que ya no deberia de aplicar");
                    VariablesVentas.vArrayList_Cupones.remove(j);
                    break;
                }
            }
        }

        log.debug("ANTES DE calculaTotalesPedido ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
        log.debug("."+VariablesVentas.vArrayList_PedidoVenta);
        
        log.debug("Antes   UtilityPacksPromo.proceso_automatico_packs ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
        
        //INI AOVIEDO 07/06/2017
        if(FarmaVariables.vAceptar){
            calcularProductosXmasY();
        }
        //FIN AOVIEDO 07/06/2017
        
        try {
            eliminarPromocionesPorFidelizacion(); // AOVIEDO 21/06/2017
            
            UtilityPacksPromo.proceso_automatico_packs();
            UtilityPacksPromo.proceso_automatico_packs_XY();
            FarmaUtility.aceptarTransaccion();
        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            e.printStackTrace();
        }
        log.debug("Despues UtilityPacksPromo.proceso_automatico_packs ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
        
        calculaTotalesPedido(); //dentro de esto esta el aplicar los dctos por campanias cupon

        log.debug("DESPUES DE calculaTotalesPedido ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
        log.debug(""+VariablesVentas.vArrayList_PedidoVenta);

        log.debug("VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF: " +
                  VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF);

        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
            VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
            lblCliente.setText(VariablesConvenioBTLMF.vNomCliente);
            VariablesVentas.vCod_Cli_Local = "";
            VariablesVentas.vNom_Cli_Ped = VariablesConvenioBTLMF.vNomCliente;
            VariablesVentas.vDir_Cli_Ped = "";
            VariablesVentas.vRuc_Cli_Ped = "";
            this.setTitle("Pedido por Convenio: OK " + VariablesConvenioBTLMF.vNomConvenio +
                          " /  IP : " + FarmaVariables.vIpPc);
            log.debug("------------------------" + this.getTitle());
            log.debug("VariablesConvenio.vTextoCliente : *****" + VariablesConvenioBTLMF.vNomCliente);

            lblLCredito_T.setText(VariablesConvenioBTLMF.vDatoLCredSaldConsumo);
            lblBeneficiario_T.setText(getMensajeComprobanteConvenio(VariablesConvenioBTLMF.vCodConvenio));
            //INI JMONZALVE 20042019
            try {
                if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio) &&
                    DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                    if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
                        lblCliente.setText(VariablesFidelizacion.vNomCliente + " "
                                     + VariablesFidelizacion.vApePatCliente + " "
                                     + VariablesFidelizacion.vApeMatCliente);
                    } else {
                        lblCliente.setText("");
                    }
                }
            } catch (SQLException e) {
                log.error("Error al verificar activacion de convenio Cobro por Responsabilidad: " + e.getMessage());
            }
            //FIN JMONZALVE 24042019
        } else {
            evaluaTitulo(); //titulo y datos dependiendo del tipo de pedido que se este haciendo
        }

        //refrescando los nuevos datos en tabla de resumen pedido
        tableModelResumenPedido.clearTable();
        tableModelResumenPedido.fireTableDataChanged();
        tblProductos.repaint();

        // cargamos los productos desde el ArrayList de Productos
        String prodVirtual = FarmaConstants.INDICADOR_N;
        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
            tableModelResumenPedido.insertRow((ArrayList)(UtilityCalculoPrecio.getArrayPosDetalleVenta(i)).clone());
            tableModelResumenPedido.fireTableDataChanged();
        }
        
        imprime("-------------------------------------------------------------------------");
        Map mAux = new HashMap();
        for(int j=0;j<VariablesVentas.vListDctoAplicados.size();j++){
            mAux = (Map)VariablesVentas.vListDctoAplicados.get(j);
            imprime("["+j+"]COD_PROD"+" : "+mAux.get("COD_PROD").toString().trim()
                     +" COD_CAMP_CUPON"+" : "+mAux.get("COD_CAMP_CUPON").toString().trim());
        }
        imprime("-------------------------------------------------------------------------");
        
        //JCHAVEZ 29102009 inicio Redondeando montos
        log.debug("VariablesVentas.vArrayList_Promociones: " + VariablesVentas.vArrayList_Promociones);
        log.debug("VariablesVentas.vArrayList_Prod_Promociones: " + VariablesVentas.vArrayList_Prod_Promociones);
        try {
            if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
            }
        } catch (SQLException ex) {
            log.error("", ex);
        }
        if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
            for (int i = 0; i < VariablesVentas.vArrayList_Promociones.size(); i++) {
                double precioUnit =
                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Promociones,
                                                                                      i, 3));
                double precioVentaUnit =
                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Promociones,
                                                                                      i, 6));
                double precioVtaTotal =
                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Promociones,
                                                                                      i, 7));
                double cantidad =
                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Promociones,
                                                                                      i, 4));
                log.debug("precioVtaTotal: " + precioVtaTotal);
                log.debug("precioVentaUnit: " + precioVentaUnit);
                try {
                    double precioVtaTotalRedondeado = DBVentas.getPrecioRedondeado(precioVtaTotal);
                    double precioVentaUnitRedondeado = //precioVtaTotalRedondeado / cantidad;
                                                       UtilityVentas.getDivideDouble(precioVtaTotalRedondeado, cantidad);
                    
                    double precioUnitRedondeado = DBVentas.getPrecioRedondeado(precioUnit);
                    log.debug("precioVtaTotalRedondeado: " + precioVtaTotalRedondeado);
                    log.debug("precioVentaUnitRedondeado: " + precioVentaUnitRedondeado);
                    double pAux2;
                    double pAux5;
                    pAux2 = UtilityVentas.Redondear(precioVentaUnitRedondeado, 2);
                    log.debug("pAux2: " + pAux2);
                    if (pAux2 < precioVentaUnitRedondeado) {
                        pAux5 = (pAux2 * Math.pow(10, 2) + 1) / 100;
                        log.debug("pAux5: " + pAux5);
                    } else {
                        pAux5 = pAux2;
                    }


                    String cprecioVtaTotalRedondeado = FarmaUtility.formatNumber(precioVtaTotalRedondeado, 3);
                    String cprecioVentaUnitRedondeado = FarmaUtility.formatNumber(pAux5);
                    String cprecioUnitRedondeado = FarmaUtility.formatNumber(precioUnitRedondeado, 3);
                    ((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).set(3, cprecioUnitRedondeado);
                    ((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).set(6, cprecioVentaUnitRedondeado);
                    ((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).set(7, cprecioVtaTotalRedondeado);
                    log.debug("precioVtaTotalRedondeado: " + "" + cprecioVtaTotalRedondeado);
                    log.debug("precioVentaUnitRedondeado: " + cprecioVentaUnitRedondeado);
                    log.debug("precioUnitRedondeado: " + cprecioUnitRedondeado);

                } catch (SQLException ex) {
                    log.error("", ex);
                }


                log.debug("precioUnit: " + precioUnit);
                log.debug("precioVentaUnit: " + precioVentaUnit);
                log.debug("precioVtaTotal: " + precioVtaTotal);
                log.debug("cantidad: " + cantidad);
            }
        }
        //JCHAVEZ 29102009 fin

        for (int i = 0; i < VariablesVentas.vArrayList_Promociones.size(); i++) {
            tableModelResumenPedido.insertRow((ArrayList)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).clone());
            tableModelResumenPedido.fireTableDataChanged();
        }
        tblProductos.repaint();
        FarmaUtility.setearPrimerRegistro(tblProductos, null, 0);
        //Seteando el valor de Si da o no Descuento al cliente.
        //daubilluz 26.05.2009
        AuxiliarFidelizacion.setMensajeDNIFidelizado(lblDNI_Anul, "R", txtDescProdOculto, this);

        evaluaFormaPagoFidelizado();

        pintaFilasProdExcluyeAhorroAcum();

    }

    private void pintaFilasProdExcluyeAhorroAcum() {
        ArrayList aux = new ArrayList();
        for (int i = 0; i < tableModelResumenPedido.data.size(); i++) {
            for (int j = 0; j < VariablesVentas.vListProdExcluyeAcumAhorro.size(); j++) {
                String pCod = VariablesVentas.vListProdExcluyeAcumAhorro.get(j).toString();
                if ((getValueColumna(tableModelResumenPedido.data, i, 0)).trim().equalsIgnoreCase(pCod)) {
                    aux.add(String.valueOf(i));
                }
            }
        }

        FarmaUtility.initSimpleListCleanColumns(tblProductos, tableModelResumenPedido,
                                                ConstantsVentas.columnsListaResumenPedido, aux, Color.white, Color.red,
                                                false);
    }

    public String getValueColumna(ArrayList tbl, int Fila, int Columna) {
        String pValor = FarmaUtility.getValueFieldArrayList(tbl, Fila, Columna);
        return pValor;
    }

    /**
     * FALTA TERMINAR IMPLEMENTAR PARA EL CASO DE CUPONES TIPO MONTO
     * Valida monto de cupones del pedido
     * verifica el uso de cupones de tipo MONTO, el valor de los ingresados no se mayor al monto del pedido
     * @author JCALLO
     * @since  11.03.2009
     * @param  pNetoPedido
     * @return boolean
     */
    private boolean validaCampsMontoNetoPedido(String pNetoPedido) {
        boolean flag = false;
        //AQUI IMPLEMENTAR EL CODIGO DE VALIDACION DE LA SUMA DE CUPONES TIPO MONTO
        //APLICADOS COMPARADOS CON EL TOTAL NETO DEL PEDIDO
        //POR AHORA POR DEFECTO SE PUSO QUE DEVUELVA SIEMPRE TRUE;
        return true;
    }

    /**
     * Se carga cupones emitidos por el cliente fidelizado.
     * @author JCORTEZ
     * @since  05.08.09
     * */
    private void cargarCupones() {
        DlgListaCupones dlglista = new DlgListaCupones(myParentFrame, "", true);
        dlglista.setVisible(true);
        if (FarmaVariables.vAceptar) {
            log.info("***********JCORTEZ--- procesando cupones clientes*********");
            operaCupones();
        }
    }


    /**
     * Se procesa los cupones cargados
     * @author JCORTEZ
     * @since 05.08.09
     * */
    private void operaCupones() {

        String cadena = "";
        //asumiendo que solo se cargara un cupon por campa�a
        for (int i = 0; i < VariablesVentas.vArrayListCuponesCliente.size(); i++) {
            cadena = ((String)((ArrayList)VariablesVentas.vArrayListCuponesCliente.get(i)).get(1)).trim();
            if (UtilityVentas.esCupon(cadena, this, txtDescProdOculto)) {
                //ERIOS 2.3.2 Valida convenio BTLMF
                if (VariablesVentas.vEsPedidoConvenio ||
                    (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF)) {
                    FarmaUtility.showMessage(this, "No puede agregar cupones a un pedido por convenio.",
                                             txtDescProdOculto);
                    return;
                }
                validarAgregarCupon(cadena);
            }
        }

    }

    /**
     * Nuestra la nueva ventana de Cobro
     * @author Edgar Rios Navarro
     * @since 01.04.2013
     */
    private void mostrarNuevoCobro() {
        // DUBILLUZ 04.02.2013
        FarmaConnection.closeConnection();
        DlgProcesar.setVersion();

        DlgNuevoCobro dlgFormaPago = new DlgNuevoCobro(myParentFrame, "", true);

        dlgFormaPago.setIndPedirLogueo(false);
        dlgFormaPago.setIndPantallaCerrarAnularPed(true);
        dlgFormaPago.setIndPantallaCerrarCobrarPed(true);

        //INI ASOSA - 03.07.2014
        String descProd = FarmaUtility.getValueFieldJTable(tblProductos, 0, 1);
        log.info("producto nuevo cobro: " + descProd);
        dlgFormaPago.setDescProductoRecVirtual(descProd);
        dlgFormaPago.setDescProductoRimac(descProd);    //ASOSA - 12/01/2015 - RIMAC
        //FIN ASOSA - 03.07.2014

        dlgFormaPago.setVisible(true);

        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cerrarVentana(true);
        } else
            pedidoGenerado = false;
    }

    //==================================================================================================================
    //==================================================================================================================
    //==================================================================================================================


    //JMIRANDA 23.06.2010
    //NUEVO PROCESO PARA VALIDAR CONVENIO

    private void validaConvenio_v2(KeyEvent e, String vCoPagoConvenio) {
        String vkF = "";
        boolean indExisteConv = false;
        boolean indMontoValido = false;
        
		// INICIO : JHAMRC 10072019
        if(validaMostrarISCBolsas()){
            mostrarISCBolsas("");
            
            if(!FarmaVariables.vAceptar)
                return;
        }
		// FIN: JHAMRC 10072019
		
        if (pedidoEnProceso) {
            return;
        }
        pedidoEnProceso = true;
        if (false) //Ha elegido un convenio y un cliente
        {
            //-----------INI PEDIDO CONVENIO
            log.debug("VariablesConvenio.vArrayList_DatosConvenio : " + VariablesConvenio.vArrayList_DatosConvenio);
            String vCodCli = "" + VariablesConvenio.vArrayList_DatosConvenio.get(1);
            String vDniCli = "" + VariablesConvenio.vArrayList_DatosConvenio.get(5);
            String vCodConvenio = "" + VariablesConvenio.vArrayList_DatosConvenio.get(0);

            log.debug("vCodConvenio " + VariablesConvenio.vArrayList_DatosConvenio.get(0));
            log.debug("vDniCLi " + VariablesConvenio.vArrayList_DatosConvenio.get(5));
            if (!vCodCli.equalsIgnoreCase("")) {
                //--INI TIENE CODCLI
                String mensaje = "";
                //1� Obtiene valor de copago
                try {
                    double totalS = FarmaUtility.getDecimalNumber(lblTotalS.getText());

                    if (FarmaUtility.getDecimalNumber(vCoPagoConvenio) != 0) {
                        //verificar la conexi�n con MATRIZ
                        String vIndLinea =
                            FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_N);

                        if (vIndLinea.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                            log.debug("Existe conexion a Matriz");
                            //Paso 1 valida que exista el convenio
                            indExisteConv = UtilityConvenio.getIndClienteConvActivo(this, txtDescProdOculto, vCodConvenio,
                                                                            vDniCli, vCodCli);
                            if (indExisteConv) {
                                //Paso 2 validar el monto disponible
                                indMontoValido = UtilityConvenio.getIndValidaMontoConvenio(this, txtDescProdOculto, vCodConvenio,
                                                                                  vDniCli, totalS, vCodCli);
                                if (indMontoValido) {
                                    if (colocaVariablesDU(VariablesConvenio.vCodCliente, lblTotalS.getText())) {
                                        //El convenio est� activo y el monto a usar es correcto
                                        continuarCobroPedido(e);
                                    } else {
                                        FarmaUtility.showMessage(this,
                                                                 "Ocurri� un problema al obtener variables convenio.",
                                                                 txtDescProdOculto);
                                        return;
                                    }
                                }
                            }
                        } else {
                            FarmaUtility.showMessage(this,
                                                     "No hay linea con matriz.\n Int�ntelo nuevamente si el problema persiste comun�quese con el Operador de Sistemas.",
                                                     txtDescProdOculto);
                        }
                    } else {
                        continuarCobroPedido(e);
                    }
                } catch (SQLException sql) {
                    log.error("", sql);
                    if (sql.getErrorCode() > 20000) {
                        FarmaUtility.showMessage(this,
                                                 sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                 txtDescProdOculto);
                    } else {
                        FarmaUtility.showMessage(this, "Ocurri� un error al validar el convenio.\n" +
                                sql, txtDescProdOculto);
                    }
                } catch (Exception ex) {
                    log.error("", ex);
                    FarmaUtility.showMessage(this, mensaje + ex.getMessage(), tblProductos);
                } finally {
                    //cerrar conexi�n
                    FarmaConnectionRemoto.closeConnection();
                }
                //--FIN TIENE CODCLI
            } else {
                continuarCobroPedido(e);
            }
            //-----------FIN PEDIDO CONVENIO
        } else {
            continuarCobroPedido(e);
        }

        pedidoEnProceso = false;
        if (VariablesVentas.vIndVolverListaProductos) {
            //agregarProducto();
            agregarProducto(null); //ASOSA - 10/10/2014 - PANHD
        }
    }

    //JMIRANDA 23.06.2010

    public void continuarCobroPedido(KeyEvent e) {
        String vkF = "";
        if (e.getKeyCode() == KeyEvent.VK_F4 && !pIngresoComprobanteManual) {
            vkF = "F4";
            agregarComplementarios(vkF);
        } else if ((UtilityPtoVenta.verificaVK_F1(e)) || e.getKeyChar() == '+' && !pIngresoComprobanteManual) {
            vkF = "F1";
            agregarComplementarios(vkF);
        }
    }

    public boolean colocaVariablesDU(String vCodCli, String totalS) {
        boolean pResultado = false;
        String pCodPago = "";
        double diferencia = 0.0;
        double valTotal = 0.0;
        double totalS_double = 0.0;
        FarmaUtility.getDecimalNumber(totalS.trim());
        try {
            totalS_double = FarmaUtility.getDecimalNumber(totalS);

            pCodPago = DBConvenio.obtieneCoPagoConvenio(VariablesConvenio.vCodConvenio, vCodCli, FarmaUtility.formatNumber(totalS_double));

            diferencia = 0.0;
            valTotal = FarmaUtility.getDecimalNumber(pCodPago);
            log.debug("Monto Copago: " + pCodPago);
            String valor = DBConvenio.validaCreditoCli(VariablesConvenio.vCodConvenio, vCodCli, FarmaUtility.formatNumber(valTotal),
                                            FarmaConstants.INDICADOR_S);
            log.debug("Diferencia: " + valor);
            diferencia = FarmaUtility.getDecimalNumber(valor);
            log.debug("VariablesConvenio.vIndSoloCredito: " + VariablesConvenio.vIndSoloCredito);
            if (diferencia < 0) {
                if (VariablesConvenio.vIndSoloCredito.equals(FarmaConstants.INDICADOR_N)) {
                    valTotal = valTotal + diferencia;
                }
            }

            VariablesConvenio.vValCoPago = FarmaUtility.formatNumber(valTotal);
            log.info("0000000000000000000000:");
            log.debug("VariablesConvenio.vValCoPago: " + VariablesConvenio.vValCoPago);
            log.info("0000000000000000000000:");
            if (VariablesConvenio.vValCoPago.trim().length() > 0) {
                pResultado = true;
            }
        } catch (SQLException e) {
            log.error("", e);
        }
        return pResultado;
    }

    /**
     * Graba el detalle de Producto por promocion
     * @param pTiComprobante
     * @param array
     * @param pFila
     * @param tipo indica si es Producto simple o de una promocion
     * @throws Exception
     * @author ASOSA
     * @since  05.07.2010
     */
    private void grabarDetalle_02(String pTiComprobante, BeanDetalleVenta bean, int pFila, int tipo) throws Exception {

        VariablesVentas.vSec_Ped_Vta_Det = String.valueOf(pFila);
        VariablesVentas.vCod_Prod = bean.getVCodProd();
        VariablesVentas.vCant_Ingresada = String.valueOf(FarmaUtility.trunc(FarmaUtility.getDecimalNumber(bean.getVCtdVta())));
        VariablesVentas.vTotalPrecVtaProd = FarmaUtility.getDecimalNumber(bean.getVSubTotal());
        VariablesVentas.vVal_Prec_Vta =FarmaUtility.formatNumber(VariablesVentas.vTotalPrecVtaProd / Integer.parseInt(VariablesVentas.vCant_Ingresada),3);
        VariablesVentas.vPorc_Dcto_1 = String.valueOf(FarmaUtility.getDecimalNumber(bean.getVPctDcto()));
        log.info("***-VariablesVentas.vPorc_Dcto_1 " + VariablesVentas.vCod_Prod + " - " +
                 VariablesVentas.vPorc_Dcto_1);
        if (tipo == ConstantsVentas.IND_PROD_PROM)
            VariablesVentas.vPorc_Dcto_2 =
                    String.valueOf(FarmaUtility.getDecimalNumber(bean.getVIndProdPromocion()));
        else
            VariablesVentas.vPorc_Dcto_2 =
                    String.valueOf(FarmaUtility.getDecimalNumber(bean.getVPctDcto_2()));

        log.debug("***-VariablesVentas.desc_2 " + VariablesVentas.vPorc_Dcto_2);
        VariablesVentas.vPorc_Dcto_Total =
                VariablesVentas.vPorc_Dcto_1; //cuando es pedido normal, el descuento total siempre es el descuento 1
        VariablesVentas.vVal_Total_Bono =
                String.valueOf(FarmaUtility.getDecimalNumber(bean.getVValBono()));
        VariablesVentas.vVal_Frac = bean.getVValFracVta();
        VariablesVentas.vEst_Ped_Vta_Det = ConstantsVentas.ESTADO_PEDIDO_DETALLE_ACTIVO;
        VariablesVentas.vSec_Usu_Local = FarmaVariables.vNuSecUsu;
        //VariablesVentas.vVal_Prec_Lista = String.valueOf(FarmaUtility.getDecimalNumber(((String)(array.get(3))).trim()));
        VariablesVentas.vVal_Prec_Lista =
                String.valueOf(FarmaUtility.getDecimalNumber(bean.getVPrecioVta()));
        VariablesVentas.vVal_Igv_Prod = bean.getVPctIgv();
        VariablesVentas.vUnid_Vta = bean.getVUnidVta();
        VariablesVentas.vNumeroARecargar = bean.getVNumTelefonoRecarga();
        String secrespaldo = ""; //ASOSA, 05.07.2010

        //INI ASOSA - 03/09/2014 - CORRECCION
        VariablesVentas.vValorMultiplicacion = "1";
        VariablesVentas.vValorMultiplicacion = bean.getVValorMultiplicacion(); //ASOSA - 11/08/2014
        //FIN ASOSA - 03/09/2014 - CORRECCION

        //ConstantsVentas.IND_PROD_SIMPLE
        // numero 24
        int posSecRespaldo = 0;
        if (tipo == ConstantsVentas.IND_PROD_SIMPLE){
            secrespaldo = bean.getVSecRespaldoStk(); //ASOSA, 05.07.2010
        }
        else if (tipo == ConstantsVentas.IND_PROD_PROM){
            secrespaldo = bean.getVCtdDias(); //ASOSA, 05.07.2010
        }
        //log.debug("***-VariablesVentas.vVal_Prec_Pub "+VariablesVentas.vVal_Prec_Pub);
        if (tipo == ConstantsVentas.IND_PROD_SIMPLE)
            VariablesVentas.vVal_Prec_Pub = bean.getVValPrecPublico();
        if (tipo == ConstantsVentas.IND_PROD_PROM)
            VariablesVentas.vVal_Prec_Pub = bean.getVPctDcto();
        /*
       Para grabar la promocion  en el detalle dubilluz 28.02.2008
       */

        if (tipo == ConstantsVentas.IND_PROD_PROM)
            VariablesVentas.vCodPromoDet = bean.getVValPrecPublico();
        else
            VariablesVentas.vCodPromoDet = "";
        log.debug("Promo al detalle : " + VariablesVentas.vCodPromoDet);
        if (tipo == ConstantsVentas.IND_PROD_SIMPLE) {
            VariablesVentas.vIndOrigenProdVta = bean.getVIndOrigenProd();
            VariablesVentas.vCantxDia =bean.getVCtdxDia();
            VariablesVentas.vCantxDias = bean.getVCtdDias();
        } else {

            VariablesVentas.vIndOrigenProdVta =bean.getVCtdxDia(); //JCHAVEZ 20102009 se asignaba cadena nula ""
            VariablesVentas.vCantxDia = "";
            VariablesVentas.vCantxDias = "";
        }

        //JCHAVEZ 20102009
        if (tipo == ConstantsVentas.IND_PROD_PROM) {
            VariablesVentas.vAhorroPack = bean.getVIndTratamiento();
        }else{
            VariablesVentas.vAhorroPack = "";
        }
        log.debug("VariablesVentas.vCod_Prod:" + VariablesVentas.vCod_Prod);
        log.debug("VariablesVentas.tipo:" + tipo);

        // INI DMOSQUEIRA 20190710
        VariablesVentas.vCostoICBPER = bean.getVIcbperVal().equals("") ? "0.00": bean.getVIcbperVal();
        VariablesVentas.vTotalICBPER = bean.getVIcbperTotal().equals("") ? "0.00" : bean.getVIcbperTotal();
        // FIN DMOSQUEIRA 20190710

        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
            //JCHAVEZ 20102009
            if (tipo == ConstantsVentas.IND_PROD_SIMPLE) {

                //DBVentas.grabarDetallePedido_02(secrespaldo);
                UtilityCalculoPrecio.saveDetalleVenta(secrespaldo,bean);
                VariablesVentas.vCodPromoDet = "";
            }
        } else {
            //JCHAVEZ 20102009
            //DBVentas.grabarDetallePedido_02(secrespaldo);
            UtilityCalculoPrecio.saveDetalleVenta(secrespaldo,bean);
            VariablesVentas.vCodPromoDet = "";
        }

        //dveliz 15.08.08
        //DUBILLUZ 22.08.2008
        /* if(VariablesCampana.vFlag){
              for(int i =0; i<VariablesCampana.vListaCupones.size();i++){
                  ArrayList myList = (ArrayList)VariablesCampana.vListaCupones.get(i);
                  agregarClienteCampana(myList.get(0).toString(),
                                            myList.get(1).toString(),
                                            myList.get(2).toString(),
                                            myList.get(3).toString(),
                                            myList.get(4).toString(),
                                            myList.get(5).toString(),
                                            myList.get(6).toString(),
                                            myList.get(7).toString(),
                                            myList.get(8).toString(),
                                            myList.get(9).toString(),
                                            myList.get(10).toString(),
                                            myList.get(11).toString());
              }*/

    }
    private void grabarDetalle_02(String pTiComprobante, ArrayList array, int pFila, int tipo) throws Exception {

        VariablesVentas.vSec_Ped_Vta_Det = String.valueOf(pFila);
        VariablesVentas.vCod_Prod = ((String)(array.get(0))).trim();
        VariablesVentas.vCant_Ingresada =
                String.valueOf(FarmaUtility.trunc(FarmaUtility.getDecimalNumber(((String)(array.get(4))).trim())));
        //VariablesVentas.vVal_Prec_Vta = ((String)(array.get(6))).trim();
        
        //INI AOVIEDO 24/04/17 
        VariablesVentas.vTotalPrecVtaProd = FarmaUtility.getDecimalNumber(((String)(array.get(7))).trim());
        VariablesVentas.vVal_Prec_Vta =
                        FarmaUtility.formatNumber(VariablesVentas.vTotalPrecVtaProd / Integer.parseInt(VariablesVentas.vCant_Ingresada),
                                                  3);
        //FIN AOVIEDO 24/04/17
        
        VariablesVentas.vPorc_Dcto_1 = String.valueOf(FarmaUtility.getDecimalNumber(((String)(array.get(5))).trim()));
        log.info("***-VariablesVentas.vPorc_Dcto_1 " + VariablesVentas.vCod_Prod + " - " +
                 VariablesVentas.vPorc_Dcto_1);
        if (tipo == ConstantsVentas.IND_PROD_PROM)
            VariablesVentas.vPorc_Dcto_2 =
                    String.valueOf(FarmaUtility.getDecimalNumber(((String)(array.get(20))).trim()));
        else
            VariablesVentas.vPorc_Dcto_2 =
                    String.valueOf(FarmaUtility.getDecimalNumber(((String)(array.get(COL_RES_DSCTO_2))).trim()));

        log.debug("***-VariablesVentas.desc_2 " + VariablesVentas.vPorc_Dcto_2);
        VariablesVentas.vPorc_Dcto_Total =
                VariablesVentas.vPorc_Dcto_1; //cuando es pedido normal, el descuento total siempre es el descuento 1
        VariablesVentas.vVal_Total_Bono =
                String.valueOf(FarmaUtility.getDecimalNumber(((String)(array.get(8))).trim()));
        VariablesVentas.vVal_Frac = ((String)(array.get(10))).trim();
        VariablesVentas.vEst_Ped_Vta_Det = ConstantsVentas.ESTADO_PEDIDO_DETALLE_ACTIVO;
        VariablesVentas.vSec_Usu_Local = FarmaVariables.vNuSecUsu;
        //VariablesVentas.vVal_Prec_Lista = String.valueOf(FarmaUtility.getDecimalNumber(((String)(array.get(3))).trim()));
        VariablesVentas.vVal_Prec_Lista =
                String.valueOf(FarmaUtility.getDecimalNumber(((String)(array.get(6))).trim()));
        VariablesVentas.vVal_Igv_Prod = ((String)(array.get(11))).trim();
        VariablesVentas.vUnid_Vta = ((String)(array.get(2))).trim();
        VariablesVentas.vNumeroARecargar = ((String)(array.get(13))).trim();
        String secrespaldo = ""; //ASOSA, 05.07.2010

        //INI ASOSA - 03/09/2014 - CORRECCION
        VariablesVentas.vValorMultiplicacion = "1";
        log.info("TAMA�O DETALLE: " + array.size());
        if (array.size() >= 28) {
            VariablesVentas.vValorMultiplicacion = ((String)(array.get(27))).trim(); //ASOSA - 11/08/2014
        }
        //FIN ASOSA - 03/09/2014 - CORRECCION

        //ConstantsVentas.IND_PROD_SIMPLE
        // numero 24
        int posSecRespaldo = 0;
        if (tipo == ConstantsVentas.IND_PROD_SIMPLE)
            posSecRespaldo = 26;
        else if (tipo == ConstantsVentas.IND_PROD_PROM)
            posSecRespaldo = 24;
        /*FarmaUtility.showMessage(this, "posSecRespaldo: " + posSecRespaldo,
                                 null);
        FarmaUtility.showMessage(this,
                                 "posSecRespaldo: " + ((String)(array.get(posSecRespaldo))).trim(),
                                 null);*/
        log.debug("WAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa: " +
                  posSecRespaldo);
        secrespaldo = ((String)(array.get(posSecRespaldo))).trim(); //ASOSA, 05.07.2010

        //log.debug("***-VariablesVentas.vVal_Prec_Pub "+VariablesVentas.vVal_Prec_Pub);
        if (tipo == ConstantsVentas.IND_PROD_SIMPLE)
            VariablesVentas.vVal_Prec_Pub = ((String)(array.get(18))).trim();
        if (tipo == ConstantsVentas.IND_PROD_PROM)
            VariablesVentas.vVal_Prec_Pub = ((String)(array.get(21))).trim();
        /*
       Para grabar la promocion  en el detalle dubilluz 28.02.2008
       */

        if (tipo == ConstantsVentas.IND_PROD_PROM)
            VariablesVentas.vCodPromoDet = ((String)(array.get(18))).trim();
        else
            VariablesVentas.vCodPromoDet = "";
        log.debug("Promo eeeee " + array);
        log.debug("Promo al detalle : " + VariablesVentas.vCodPromoDet);

        if (tipo == ConstantsVentas.IND_PROD_SIMPLE) {
            VariablesVentas.vIndOrigenProdVta = array.get(COL_RES_ORIG_PROD).toString().trim();
            VariablesVentas.vCantxDia = array.get(COL_RES_CANT_XDIA).toString().trim();
            VariablesVentas.vCantxDias = array.get(COL_RES_CANT_DIAS).toString().trim();
        } else {

            VariablesVentas.vIndOrigenProdVta =
                    array.get(23).toString().trim(); //JCHAVEZ 20102009 se asignaba cadena nula ""
            VariablesVentas.vCantxDia = "";
            VariablesVentas.vCantxDias = "";
        }

        //JCHAVEZ 20102009
        if (tipo == ConstantsVentas.IND_PROD_PROM) {
            VariablesVentas.vAhorroPack = ((String)(array.get(22))).trim();
        }else{
            VariablesVentas.vAhorroPack = "";
        }
        log.debug("VariablesVentas.vCod_Prod:" + VariablesVentas.vCod_Prod);
        log.debug("VariablesVentas.tipo:" + tipo);
        log.debug("VariablesVentas.array:" + array);

       ///VariablesVentas.vCant_Ingresada_ItemQuote=array.get(COL_CANT_ITEM_QUOTE).toString().trim();//LTAVARA 2016.11.14

        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
            //JCHAVEZ 20102009
            if (tipo == ConstantsVentas.IND_PROD_SIMPLE) {

                DBVentas.grabarDetallePedido_02(secrespaldo,pIngresoComprobanteManual);
                VariablesVentas.vCodPromoDet = "";
            }
        } else {
            //JCHAVEZ 20102009
            DBVentas.grabarDetallePedido_02(secrespaldo,pIngresoComprobanteManual);
            VariablesVentas.vCodPromoDet = "";
        }

        //dveliz 15.08.08
        //DUBILLUZ 22.08.2008
        /* if(VariablesCampana.vFlag){
              for(int i =0; i<VariablesCampana.vListaCupones.size();i++){
                  ArrayList myList = (ArrayList)VariablesCampana.vListaCupones.get(i);
                  agregarClienteCampana(myList.get(0).toString(),
                                            myList.get(1).toString(),
                                            myList.get(2).toString(),
                                            myList.get(3).toString(),
                                            myList.get(4).toString(),
                                            myList.get(5).toString(),
                                            myList.get(6).toString(),
                                            myList.get(7).toString(),
                                            myList.get(8).toString(),
                                            myList.get(9).toString(),
                                            myList.get(10).toString(),
                                            myList.get(11).toString());
              }*/

    }

    /************************************************************ INI - ASOSA, 09.07.2010 ***************************************************************/
    private void cancelaOperacion_02() {
        String codProd = "";
        String cantidad = "";
        String indControlStk = "";
        String secRespaldo = ""; //ASOSA, 02.07.2010
        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
            
            BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
            codProd = bean.getVCodProd();
            VariablesVentas.vVal_Frac =bean.getVValFracVta();
            cantidad = bean.getVCtdVta();
            indControlStk = bean.getVIndControlaStock();
            secRespaldo =bean.getVSecRespaldoStk(); //ASOSA, 02.07.2010
            VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
            if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                /*!UtilityVentas.actualizaStkComprometidoProd(codProd, //ANTES-ASOSA, 02.07.2010
                                                       Integer.parseInt(cantidad),
                                                       ConstantsVentas.INDICADOR_D,
                                                       ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR,
                                                       Integer.parseInt(cantidad),
                                                       true,
                                                       this,
                                                       tblProductos))*/
                !UtilityVentas.operaStkCompProdResp(codProd,
                    //ASOSA, 02.07.2010
                    0, ConstantsVentas.INDICADOR_D, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR, 0, true, this,
                    tblProductos, secRespaldo))
                return;
        }
        /**
       * Actualiza comprometido a Arra Promociones
       * @author : dubilluz
       * @since  : 25.06.2007
       */
        ArrayList aux = new ArrayList();
        ArrayList agrupado = new ArrayList();
        String codProm = "";
        codProd = "";
        cantidad = ""; //((String)(tblProductos.getValueAt(filaActual,4))).trim();
        indControlStk = ""; // ((String)(tblProductos.getValueAt(filaActual,16))).trim();
        for (int i = 0; i < VariablesVentas.vArrayList_Promociones.size(); i++) {
            agrupado = new ArrayList();
            aux = (ArrayList)(VariablesVentas.vArrayList_Promociones.get(i));
            codProm = ((String)(aux.get(0))).trim();
            agrupado = detalle_Prom(VariablesVentas.vArrayList_Prod_Promociones, codProm);
            log.debug("AAAAAAAAAAPRODSDSDSDSDSDS: " + agrupado);
            //agrupado=agrupar(agrupado);
            for (int j = 0; j < agrupado.size(); j++) //VariablesVentas.vArrayList_Prod_Promociones.size(); j++)
            {
                aux = (ArrayList)(agrupado.get(j)); //VariablesVentas.vArrayList_Prod_Promociones.get(j));
                //if((((String)(aux.get(18))).trim()).equalsIgnoreCase(codProm)){
                codProd = ((String)(aux.get(0))).trim();
                cantidad = ((String)(aux.get(4))).trim();
                VariablesVentas.vVal_Frac = ((String)(aux.get(10))).trim();
                indControlStk = ((String)(aux.get(16))).trim();
                secRespaldo = ((String)(aux.get(24))).trim(); //ASOSA, 08.07.2010
                VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                    /*!UtilityVentas.actualizaStkComprometidoProd(codProd,Integer.parseInt(cantidad),ConstantsVentas.INDICADOR_D, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR, Integer.parseInt(cantidad), //Antes, ASOSA, 08.07.2010
                                                         false,
                                                         this,
                                                         tblProductos))*/
                    !UtilityVentas.operaStkCompProdResp(codProd,
                        //ASOSA, 08.07.2010
                        0, ConstantsVentas.INDICADOR_D, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR, 0, false,
                        this, tblProductos, secRespaldo))
                    return;
                //}
            }

        }
        FarmaUtility.aceptarTransaccion();
        inicializaArrayList();
        //jcallo: el parametro estaba en false--> se cambio a true
        cerrarVentana(true);
    }


    private boolean cargaLogin_verifica() {
        VariablesVentas.vListaProdFaltaCero = new ArrayList();
        VariablesVentas.vListaProdFaltaCero.clear();
        boolean band = false;
        //limpiando variables de fidelizacion
        //UtilityFidelizacion.setVariables();

        //JCORTEZ 04.08.09 Se limpiar cupones.
        VariablesVentas.vArrayListCuponesCliente.clear();
        VariablesVentas.dniListCupon = "";

        //dubilluz
        //22.10.2014
        if (!UtilityPtoVenta.getIndLoginCajUnicaVez()) {
            // KMONCADA 2015.03.19
            //if(!actuaRobot){
                DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                dlgLogin.setRolUsuario(FarmaConstants.ROL_VENDEDOR);
                dlgLogin.setVisible(true);
            /*}else{
                FarmaVariables.vAceptar = true;
            }*/
        } else
            FarmaVariables.vAceptar = true;

        if (FarmaVariables.vAceptar) {

            //Agregado por FRAMIREZ 09/11/2011
            //Muestra mensaje de retencion de un convenio.
            log.debug("<<<<<<<<Ingresando al mensaje de Retencion>>>>>>>>>");
            log.debug("vCodConvenio :" + VariablesConvenioBTLMF.vCodConvenio);

            if (VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
                mostrarMensajeRetencion();
            }

            log.info("******* JCORTEZ *********");
            if (UtilityCaja.existeIpImpresora(this, null)) {
                if (FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL) &&
                    !UtilityCaja.existeCajaUsuarioImpresora(this, null)) {
                    //linea agrega p�ra corregir el error al validar los roles de los usuarios
                    //FarmaVariables.dlgLogin = dlgLogin;
                    log.debug("");
                    VariablesCaja.vVerificaCajero = false;
                    //FarmaUtility.showMessage(this,"No tiene Asociada Caja esa Impresora",txtDescProdOculto);
                    //cerrarVentana(false);
                } else {
                    //FarmaVariables.dlgLogin = dlgLogin;
                    log.info("******* 2 *********");
                    log.info("Usuario: " + FarmaVariables.vIdUsu);
                    muestraMensajeUsuario();
                    FarmaVariables.vAceptar = false;
                    VariablesCaja.vVerificaCajero = true;
                    band = true;
                    //agregarProducto();
                }
            } else {
                //no se genera venta sin impresora asignada (Boleta/ Ticket)
                //FarmaVariables.dlgLogin = dlgLogin;
                VariablesCaja.vVerificaCajero = false;
                //FarmaUtility.showMessage(this,"No tiene Asociada ninguna Impresora",txtDescProdOculto);
                //cerrarVentana(false);
            }

        } //else
        //cerrarVentana(false);
        return band;
    }

    //Agregado Por FRAMIREZ.

    public void mostrarMensajeRetencion() {
        ArrayList htmlDerecho = new ArrayList();


        UtilityConvenioBTLMF.listaMensaje(htmlDerecho, VariablesConvenioBTLMF.vCodConvenio,
                                          ConstantsConvenioBTLMF.FLG_DOC_RETENCION, this, null);

        log.debug("Tama�o:" + htmlDerecho);
        if (htmlDerecho.size() != 0) {
            DlgMensajeRetencion dlg = new DlgMensajeRetencion(myParentFrame, "", false);
            dlg.setVisible(true);
        }

    }
    
    public void funcionF12(String pCodCampanaCupon,boolean vTeclaF12) {
        funcionF12(pCodCampanaCupon,vTeclaF12, false);
    }
    
    public void funcionF12(String pCodCampanaCupon,boolean vTeclaF12, boolean isPasoTarjeta) {
        //Desarrollo5 - Alejandro Nu�ez 11.11.2015
        ProgramaXmas1Facade programaXmas1Facade = new ProgramaXmas1Facade();
        /*
        VariablesFidelizacion.tmpCodCampanaCupon = pCodCampanaCupon;
        if (VariablesVentas.vEsPedidoConvenio) {
            FarmaUtility.showMessage(this,
                                     "No puede agregar una tarjeta a un " +
                                     "pedido por convenio.",
                                     txtDescProdOculto);
            return;
        }
        mostrarBuscarTarjetaPorDNI();
        */

        AuxiliarFidelizacion.funcionF12(pCodCampanaCupon, txtDescProdOculto, this.myParentFrame, lblDNI_Anul,
                                        lblCliente, this, "R", lblDNI_SIN_COMISION,vTeclaF12, isPasoTarjeta, false);
                                        //pIngresoComprobanteManual);

        neoOperaResumenPedido();
        FarmaUtility.moveFocus(txtDescProdOculto);
        VariablesFidelizacion.tmpCodCampanaCupon = "N";
        //Inicio - dubilluz 15.06.2011
        evaluaFormaPagoFidelizado();
        //Fin - dubilluz 15.06.2011
        
        //Desarrollo5 - Alejandro Nu�ez 11.11.2015
        if(UtilityPuntos.isActivoFuncionalidad()){ //AOVIEDO 13/07/2017 SE AGREGA LA VALIDACION PARA LOCALES TICO
            if(VariablesFidelizacion.vDniCliente.trim().length()>0)
                programaXmas1Facade.reemplazarListaProgramasPorProductos();
        }
    }

    public void evaluaFormaPagoFidelizado() {

        if (VariablesFidelizacion.vDniCliente.trim().length() >= 1) {
            //Inicio - dubilluz 15.06.2011
            lblFormaPago.setVisible(false);
            lblFormaPago.setOpaque(false);
            if (VariablesFidelizacion.vIndUsoEfectivo.trim().equalsIgnoreCase("S") ||
                VariablesFidelizacion.vIndUsoTarjeta.trim().equalsIgnoreCase("S"))
                if (!VariablesFidelizacion.vNamePagoTarjeta.trim().equalsIgnoreCase("N")) {
                    lblFormaPago.setVisible(true);
                    lblFormaPago.setBackground(Color.white);
                    lblFormaPago.setOpaque(true);
                    if (VariablesFidelizacion.vCodFPagoTarjeta.trim().equalsIgnoreCase("T0000")) {
                        lblFormaPago.setText(" Pago con Todas las Tarjetas");
                    } else {
                        lblFormaPago.setText(" Pago con " + VariablesFidelizacion.vNamePagoTarjeta);
                    }
                    lblFormaPago.setText("  " + lblFormaPago.getText().trim().toUpperCase());
                }
            //Fin - dubilluz 15.06.2011
        }else{
            lblFormaPago.setText("  ");
        }
    }

    public boolean isFormaPagoUso_x_Cupon(String codCampCupon) {
        String valor = "N";
        try {
            valor = DBFidelizacion.isValidaFormaPagoUso_x_Campana(codCampCupon).trim();
        } catch (SQLException e) {
            log.error("", e);
        }

        if (valor.trim().equalsIgnoreCase("S"))
            return true;

        return false;
    }

    private void validaIngresoTarjetaPagoCampanaAutomatica(String nroTarjetaFormaPago) {
        if (isNumerico(nroTarjetaFormaPago)) {
            Map mapCupon;
            boolean obligarIngresarFP = false;
            boolean yaIngresoFormaPago = false;
            String vTarjetaEncriptada;

            try {//[Desarrollo5] 19.10.2015 se carga el numero de tarjeta CMR o financiero Encriptado
                vTarjetaEncriptada = DBFidelizacion.getEncriptaTarjetaCampana(FarmaVariables.vCodLocal,
                                                                              FarmaVariables.vCodGrupoCia,
                                                                              nroTarjetaFormaPago);

                VariablesFidelizacion.tmp_NumTarjeta_unica_Campana = vTarjetaEncriptada;
                funcionF12("N",true, true);
                //verifica si la tarjeta ya esta asociada a un DNI
                /* String pExisteAsociado = UtilityFidelizacion.existeDniAsociado(nroTarjetaFormaPago);
    
                if (pExisteAsociado.trim().equalsIgnoreCase("S")) {
                    //VALIDA EL CLIENTE POR TARJETA 12.01.2011
                    String cadena = nroTarjetaFormaPago;
                    validarClienteTarjeta(cadena, false);
                    //VariablesFidelizacion.vNumTarjeta = cadena.trim();
                    if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
                        log.debug("RRRR");
                        UtilityFidelizacion.operaCampa�asFidelizacion(cadena);
                        //DAUBILLUZ -- Filtra los DNI anulados
                        //25.05.2009
                        VariablesFidelizacion.vDNI_Anulado =
                                UtilityFidelizacion.isDniValido(VariablesFidelizacion.vDniCliente);
                        VariablesFidelizacion.vAhorroDNI_x_Periodo =
                                UtilityFidelizacion.getAhorroDNIxPeriodoActual(VariablesFidelizacion.vDniCliente,
                                                                               VariablesFidelizacion.vNumTarjeta);
                        // envio sl numero de tarjeta
                        // 01.06.2012 dubilluz
                        VariablesFidelizacion.vMaximoAhorroDNIxPeriodo =
                                UtilityFidelizacion.getMaximoAhorroDnixPeriodo(VariablesFidelizacion.vDniCliente,
                                                                               VariablesFidelizacion.vNumTarjeta);
                        // 01.06.2012 dubilluz
                        log.info("Variable de DNI_ANULADO: " + VariablesFidelizacion.vDNI_Anulado);
                        log.info("Variable de vAhorroDNI_x_Periodo: " + VariablesFidelizacion.vAhorroDNI_x_Periodo);
                        log.info("Variable de vMaximoAhorroDNIxPeriodo: " +
                                 VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
    
                        AuxiliarFidelizacion.setMensajeDNIFidelizado(lblDNI_Anul, "R", txtDescProdOculto, this);
                    }
                } else {
                    if (VariablesFidelizacion.vDniCliente.trim().length() == 0) {
                        funcionF12("N",true, true);
                        yaIngresoFormaPago = true;
                    }
    
    
                } */
            
                if (VariablesFidelizacion.vNumTarjeta.trim().length() == 0){
                    VariablesFidelizacion.tmp_NumTarjeta_unica_Campana = "";
                    return;
                }
                //cargar las campa�as de tipo automatica
                String cadenaTarjeta = UtilityFidelizacion.getDatosTarjetaFormaPago(vTarjetaEncriptada.trim());
                String[] datos = cadenaTarjeta.split("@");
                if (datos.length == 2) {
                    VariablesFidelizacion.vIndUsoEfectivo = "N";
                    VariablesFidelizacion.vIndUsoTarjeta = "S";
                    VariablesFidelizacion.vCodFPagoTarjeta = datos[0].toString().trim();
                    VariablesFidelizacion.vNamePagoTarjeta = datos[1].toString().trim();
        
                    //if(VariablesFidelizacion.vDNI_Anulado)
                    //{
                    if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0){
                        UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);
                    }
                    VariablesFidelizacion.vNumTarjetaCreditoDebito_Campana = vTarjetaEncriptada.trim();
                    //}
                    evaluaFormaPagoFidelizado();
                    txtDescProdOculto.setText("");
                }
            }catch (Exception e) {
                log.error("", e);
            }
        }
        neoOperaResumenPedido();
    }


    public boolean isNumerico(String pCadena) {
        int numero = 0;
        boolean pRes = false;
        try {
            for (int i = 0; i < pCadena.length(); i++) {
                numero = Integer.parseInt(pCadena.charAt(i) + "");
                pRes = true;
            }
        } catch (NumberFormatException e) {
            pRes = false;
        }
        return pRes;
    }

    public void setFormatoTarjetaCredito(String pCadena) {
        String pCadenaNueva = UtilityFidelizacion.pIsTarjetaVisaRetornaNumero(pCadena).trim();
        if (!pCadenaNueva.trim().equalsIgnoreCase("N")) {
            log.debug("Es tarjeta");
            txtDescProdOculto.setText(pCadenaNueva.trim());
            pasoTarjeta = true;
        } else {
            log.debug("NO ES tarjeta");
            pasoTarjeta = false;
        }

    }

    //Dubilluz - 06.12.2011

    public void ingresaMedicoFidelizado() {
        AuxiliarFidelizacion.ingresoMedico(this.myParentFrame, lblMedico, lblDNI_Anul, lblCliente, this, "R",
                                           lblDNI_SIN_COMISION, txtDescProdOculto);
        neoOperaResumenPedido();
        /*
        String pPermiteIngresoMedido =
            UtilityFidelizacion.getPermiteIngresoMedido();

        if (pPermiteIngresoMedido.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            if (VariablesVentas.vEsPedidoConvenio) {
                FarmaUtility.showMessage(this,
                                         "No puede ingresar el M�dido porque tiene" +
                                         "seleccionado convenio.",
                                         txtDescProdOculto);
                return;
            }

            String pIngresoMedido =
                FarmaUtility.ShowInput(this, "Ingrese el Colegio M�dico:");
            log.debug("pIngresoMedido:" + pIngresoMedido);
            if (pIngresoMedido.trim().length() > 0){
                log.debug("valida si existe el medico");
                String pExisteMedico =
                    UtilityFidelizacion.getExisteMedido(this.myParentFrame,pIngresoMedido.trim());
                log.debug("pExisteMedico:" + pExisteMedico);

                if (pExisteMedico.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                    if (VariablesFidelizacion.vNumTarjeta.trim().length() >
                        0) {
                        log.debug(">>> ya existe DNI ingresado");
                    } else {
                        log.debug(">>> NO EXISTE DNI ingresado");
                        funcionF12("N");
                    }

                    log.debug(">>>VariablesFidelizacion.vNumTarjeta.trim().length()+"+VariablesFidelizacion.vNumTarjeta.trim().length());
                    if (VariablesFidelizacion.vNumTarjeta.trim().length() >
                        0) {
                        log.debug(">>> BUSCA campa�as para agregar las q tiene asociado ese tipo de colegio");
                        UtilityFidelizacion.agregaCampanaMedicoAuto(VariablesFidelizacion.vNumTarjeta,
                                                                    VariablesFidelizacion.V_TIPO_COLEGIO.trim(),
                                                                    VariablesFidelizacion.V_OLD_TIPO_COLEGIO);
                        //VariablesFidelizacion.vColegioMedico = pIngresoMedido.trim();
                        ///////////////////////////////////////////////
                        VariablesFidelizacion.vColegioMedico = VariablesFidelizacion.V_NUM_CMP;
                        ///////////////////////////////////////////////
                        log.debug(">>> agrego campna..");
                    }
                    else
                    {
                        ///////////////////////////////////////////////
                        UtilityFidelizacion.limpiaVariablesMedico();
                        ///////////////////////////////////////////////
                    }
                }
                else{
                    if(VariablesFidelizacion.vColegioMedico.trim().length()>0&&VariablesFidelizacion.vNumTarjeta.trim().length()>0)
                      UtilityFidelizacion.quitarCampanaMedico(VariablesFidelizacion.vNumTarjeta,VariablesFidelizacion.V_TIPO_COLEGIO);
                   FarmaUtility.showMessage(this,"No existe el M�dico Seleccionado. Verifique!!",txtDescProdOculto);

                    UtilityFidelizacion.limpiaVariablesMedico();
                }
            }
        }*/

    }

    /**
     * @since 2.4.8 ERIOS Se consulta el limite de credito en linea.
     * @param dialog
     * @return
     */
    public boolean existeSaldoCredDispBenif(JDialog dialog) {
        boolean ret = true;
        try {
            FacadeConvenioBTLMF facadeConvenioBTLMF = new FacadeConvenioBTLMF();
            ArrayList<String> beneficiario = facadeConvenioBTLMF.obtieneBeneficRemoto(VariablesConvenioBTLMF.vCodCliente);
            //KMONCADA 07.11.2016 VALIDA QUE HAYA ENCONTRADO BENEFICIARIO EN RAC.
            if(beneficiario == null){
                FarmaUtility.showMessage(dialog, "No se obtuvo datos del beneficiario en Matriz. \n"+ 
                                                 "Codigo Cliente: "+VariablesConvenioBTLMF.vCodCliente+"\n"+
                                                 "Codigo Convenio: "+VariablesConvenioBTLMF.vCodConvenio+"\n"+
                                                 "Reintente, si persiste comuniquese con Mesa de Ayuda", null);
                return false;
            }
            
            VariablesConvenioBTLMF.vLineaCredito = beneficiario.get(DlgListaPantallaBTLMF.COLUMN_LINEA_CREDITO).trim();
            String vEstado = beneficiario.get(3).trim();
            if (vEstado.equals("N")) {
                FarmaUtility.showMessage(dialog,
                                         "El Beneficiario ha sido Bloqueado en Matriz. Comun�quese con el �rea de Convenios.",
                                         "");
                ret = false;
            } else {
                facadeConvenioBTLMF.consultarSaldCreditoBenif(dialog);
                if (VariablesConvenioBTLMF.vImpSubTotal >
                    FarmaUtility.getDecimalNumber(VariablesConvenioBTLMF.vMontoSaldo)) {

                    FarmaUtility.showMessage(dialog,
                                             "El importe " + VariablesConvenioBTLMF.vImpSubTotal + " supera el saldo de credito del Benificiario!!",
                                             "");
                    ret = false;
                }
            }
        } catch (Exception e) {
            log.error("", e);
            FarmaUtility.showMessage(dialog, "Ocurri� un error al verificar L�mite de Cr�dito en l�nea.\n" +
                    e.getMessage(), null);
            ret = false;
        }

        return ret;
    }

    public String getMensajeComprobanteConvenio(String pCodConvenio) {
        String pCadena = "";
        try {
            pCadena =
                    DBConvenioBTLMF.getMsgComprobante(pCodConvenio, VariablesConvenioBTLMF.vImpSubTotal, VariablesConvenioBTLMF.vValorSelCopago);
        } catch (SQLException e) {
            pCadena = "N";
            log.error("", e);
        }
        return pCadena;
    }

    private void txtDescProdOculto_actionPerformed(ActionEvent e) {
    }

    /**
     * Busca el producto ingresado
     * @author ERIOS
     * @since 03.07.2013
     * @param codProd
     * @param e
     */
    private void buscaProducto(String codProd, KeyEvent e) {
        char primerkeyChar = codProd.charAt(0);
        char segundokeyChar;

        if (codProd.length() > 1)
            segundokeyChar = codProd.charAt(1);
        else
            segundokeyChar = primerkeyChar;

        char ultimokeyChar = codProd.charAt(codProd.length() - 1);
        if (codProd.length() > 6 &&
            (!Character.isLetter(primerkeyChar) && (!Character.isLetter(segundokeyChar) && (!Character.isLetter(ultimokeyChar))))) {
            VariablesVentas.vCodigoBarra = codProd;

            try {
                FarmaUtility.moveFocus(txtDescProdOculto);
                codProd = DBVentas.obtieneCodigoProductoBarra();
            } catch (SQLException er) {
                log.error("", er);
            }
        }

        if (codProd.length() == 6) {
            VariablesVentas.vCodProdBusq = codProd;
            ArrayList myArray = new ArrayList();
            obtieneInfoProdEnArrayList(myArray, codProd);

            if (myArray.size() == 1) {
                VariablesVentas.vKeyPress = e;
                // kmoncada evalua si hay producto virtual
                if (!VariablesVentas.vProductoVirtual) {
                    //agregarProducto(null);
                    
                    if (!existsProdRimac()) { //ASOSA - 19/01/2015 - RIMAC   //ASOSA - 12/01/2015 - RIMAC
                        agregarProducto(null); //ASOSA - 10/10/2014 - PANHD
                    } else {
                        FarmaUtility.showMessage(this, "Ya se selecciono un producto rimac", txtDescProdOculto);
                    }

                } else {
                    FarmaUtility.showMessage(this, "Ya se selecciono un producto virtual.", txtDescProdOculto);
                }

                VariablesVentas.vCodProdBusq = "";
                VariablesVentas.vKeyPress = null;
            } else {
                FarmaUtility.showMessage(this, "Producto No Encontrado seg�n Criterio de B�squeda !!!",
                                         txtDescProdOculto);
                VariablesVentas.vCodProdBusq = "";
                VariablesVentas.vKeyPress = null;
                //KMONCADA 05.08.2015 SE LIMPIA EL TEXTO INGRESADO
                txtDescProdOculto.setText("");
            }
        }
    }

    public void setFrameEconoFar(FrmEconoFar frmEconoFar) {
        this.frmEconoFar = frmEconoFar;
    }
    
    public void setFrameEconoMenuFar(FrmEconoMenuFar frmEconoMenuFar) {
        this.frmEconoMenuFar = frmEconoMenuFar;
    }
    
    private void txtDescProdOculto_focusLost(FocusEvent e) {
        FarmaUtility.moveFocus(txtDescProdOculto);
    }

    public void abrePedidosDelivery(boolean vIndicador) {
        this.vIndPedidosDelivery = vIndicador;
    }

    private void abrePedidosDelivery() {
        DlgUltimosPedidos dlgUltimosPedidos = new DlgUltimosPedidos(myParentFrame, "", true);
        dlgUltimosPedidos.setTipoVenta(ConstantsVentas.TIPO_PEDIDO_DELIVERY);
        dlgUltimosPedidos.setVisible(true);

        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cerrarVentana(true);
        }
    }

    private void cambiaFunciones() {
        lblF1.setVisible(false);
        lblF2.setVisible(false);
        lblF6.setVisible(false);
        lblF8.setVisible(false);
        lblF9.setVisible(false);
        
        lblF1.setText("[F11] Cobrar");
        lblF1.setVisible(true);
    }

    private void cobraPedidoManual() {
        /*if(vTipoComp.trim().equalsIgnoreCase("01")||vTipoComp.trim().equalsIgnoreCase("02")){
            VariablesCaja.pManualSerieComprobante = txtSerie.getText().trim();
            VariablesCaja.pManualNumCompPago = txtNroComprobante.getText().trim(); ;
            VariablesCaja.pManualTipCompPago = vTipoComp;
            cerrarVentana(true);
        }*/
        String vkF = "F1";
        if (VariablesCaja.pManualTipCompPago.trim().equalsIgnoreCase("01")) {
            vkF = "F1";
            FarmaUtility.showMessage(this, "Se va Ingresar la :\n" +
                    "BOLETA : " + VariablesCaja.pManualSerieComprobante + VariablesCaja.pManualNumCompPago,
                    txtDescProdOculto);
        } else if (VariablesCaja.pManualTipCompPago.trim().equalsIgnoreCase("02")) {
            vkF = "F4";
            FarmaUtility.showMessage(this, "Se va Ingresar la :\n" +
                    "FACTURA : " + VariablesCaja.pManualSerieComprobante + VariablesCaja.pManualNumCompPago,
                    txtDescProdOculto);
        }
        agregarComplementarios(vkF);
    }
    
    /**
     * Si existe algun producto rimac en el resumen de pedido
     * @author ASOSA
     * @since 19/01/2015
     * @kind RIMAC
     * @return
     */
    public boolean existsProdRimac(){
        boolean flag = false;
        int cant = 0;
        for (int c = 0; c < tableModelResumenPedido.data.size(); c++) {
            String codProd = FarmaUtility.getValueFieldArrayList(tableModelResumenPedido.data, c, 0);            
            boolean flag02 = UtilityInventario.isExistProdRimac(codProd);
            if (flag02 == true) {
                cant = cant + 1;
            }            
        }
        if (cant > 0) {
            flag = true;
        }
        return flag;
    }
    
    // dubilluz 31.08.2016
    private void cargaIconoReceta() {
        //LTERRAZOS 01.03.2013 Se llama a la tabla PBL_CIA para mostrar la ruta
        String strRutaJpg = "IconoReceta.jpg";
        //ImageIcon imageIcono =new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/" + strRutaJpg));
        if(vInd_Menu.equalsIgnoreCase("S")){
            lblReceta.setIcon(ajustarImagen(FrmEconoMenuFar.class.getResource("/mifarma/ptoventa/imagenes/" + strRutaJpg)));
        }else{
            lblReceta.setIcon(ajustarImagen(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/" + strRutaJpg)));
        }
        lblReceta.setVisible(true);
    }
    
    private ImageIcon ajustarImagen(URL ico)
    {   
        int x=0,y=0;
        x = 40 ;
        y = 40;
        ImageIcon tmpIconAux = new ImageIcon(ico);
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT));
        return tmpIcon;
    }
    
    public boolean isNumeroReceta(String pCadena){
        
        boolean vValor = true;
        for(int i=0;i<pCadena.length();i++){
            char val = pCadena.charAt(i);
            if(!(Character.isDigit(val)||Character.isLetter(val))){
                vValor = false;
                break;
            }
        }
        
        if(pCadena.trim().length()>0&&vValor){
            if(pCadena.trim().substring(0,1).equalsIgnoreCase("R")&&pCadena.trim().length()==14){
                     String pPrefijo =  pCadena.trim().substring(0,1);
                     String pCodLocal =  pCadena.trim().substring(1,4);
                     String pNumPedido =  pCadena.trim().substring(4);
                if(pPrefijo.length()==1&&pCodLocal.length()==3&&pNumPedido.length()==10){
                    return true;
                }
         }
        }     
        return false;
    }
    
    // dubilluz 31.08.2016
/**
     * LTAVARA 2016.09.16
     * Validar los productos del pedido, si participan en el programa x+1 :
     * Cliente monedero: muestra solicitud de afiliacion al programa por producto. 
     * Si es tranferencia entre tarjetas solicita ingresar las cantidades de los productos x+1
     * Cliente no monedero : muestra mensaje en que programa participa el producto
     * */
    public void validarProductosXmas1(BeanTarjeta tarjetaPuntosOld){
            UtilityProgramaAcumula.vAutomaticoIngresoCantidad = false;
            String    codProd;
            String    descProd;
            
            if(tarjetaPuntosOld!=null){
           //actualizar el nombre del nuevo cliente //agrego 2016.09.19
            evaluaTitulo();
            
            UtilityPuntos.transferirInscripcionBonificados(myParentFrame, this, tarjetaPuntosOld, pIngresoComprobanteManual);
            
            //INICIO LTAVARA 2016.09.15 SOLICITAR INGRESAR LA CANTIDAD SI EL PRODUCTO PARTICIPA EN X+1
            FacadeLealtad facadeLealtad = new FacadeLealtad();
            ArrayList<String> listaProductos=null;

                for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                //seleccionando los productos de la lista
                BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i);                     
                tblProductos.getSelectionModel().setSelectionInterval(i,i);
                codProd = bean.getVCodProd();
                descProd = codProd+" - "+bean.getVDescProd();
            
                    BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
                    //LISTA DE INSCRITOS DEL NUEVO CLIENTE
                    listaProductos=(ArrayList<String>)tarjetaCliente.getListaAuxiliarInscritos();
                
                    if (tarjetaCliente!=null && WSClientConstans.EXITO.equals(tarjetaCliente.getEstadoOperacion()) &&
                       (WSClientConstans.EstadoTarjeta.ACTIVA.equals(tarjetaCliente.getEstadoTarjeta()) ||
                        WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR.equals(tarjetaCliente.getEstadoTarjeta()))) {
                        //
                       if(facadeLealtad.verificaInscripcionProducto(listaProductos, codProd)!= "") {
                            log.info("SOLICITAR LA CANTIDAD PORQUE PARTICIPA EN EL PROGRAMA X+1"+codProd);
                            evaluaIngresoCantidad();
                        }
                    }
                }
            //FIN LTAVARA 2016.09.15 SOLICITAR INGRESAR LA CANTIDAD SI EL PRODUCTO PARTICIPA EN X+1
                //KMONCADA 13.08.2015 QUITARA LOS PACKS COMO FIDELIZADOS EN CASO NO EXISTA CLIENTE FIDELIZADO
                if(UtilityPuntos.quitarPacksFidelizados()){
                    neoOperaResumenPedido();
                }
            }else{
            codProd = "";
            descProd = "";
            
            for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i); 
                tblProductos.getSelectionModel().setSelectionInterval(i,i);
                codProd = bean.getVCodProd();
                descProd = codProd+" - "+bean.getVDescProd();
                log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] CODIGO DE PRODUCTO "+codProd);
                UtilityVentas.verificaInscripcionX1(this,myParentFrame,codProd, descProd,pIngresoComprobanteManual);
                if(UtilityVentas.indIngresarCantProdXmas1UV.equals(FarmaConstants.INDICADOR_S)){
                    UtilityProgramaAcumula.vAutomaticoIngresoCantidad = true;
                    evaluaIngresoCantidad();
                }
            }
                
            UtilityProgramaAcumula.vAutomaticoIngresoCantidad = false;
            
            }
        }

        public boolean vPermiteAccion(){
            int vfila = tblProductos.getSelectedRow();
            if(vfila>=0){
                boolean vValor = UtilityCalculoPrecio.isPermiteAccionResumen(FarmaUtility.getValueFieldArrayList(tableModelResumenPedido.data, vfila , 0));
                if(vValor)
                    return vValor;
                else {
                    FarmaUtility.showMessage(this, "Para la fila seleccionada no se permite esta acci�n.", txtDescProdOculto);
                    return false;
                }
            }
            else
                return false;
        }
        
    //INI AOVIEDO 07/06/2017
    public void calcularProductosXmasY(){
        ArrayList myArrayCab = new ArrayList();
        ArrayList vListaCabPack = new ArrayList();
        ArrayList myArrayDet = new ArrayList();
        ArrayList vListaDetPack = new ArrayList();
        ArrayList myArrayDetFinal = new ArrayList();
        double totalG = 0.0;
        String igv = "";
        String precioG = "";
        int cantidadPack = 0;
        
        for(int g=0;g<VariablesVentas.vArrayList_Prod_XY.size();g++){
            log.info("VariablesVentas.vArrayList_Prod_XY "+VariablesVentas.vArrayList_Prod_XY);
            //cantidadPack = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY.get(g)).get(9));
            cantidadPack = Integer.parseInt(VariablesVentas.sCantidadPackXY);
            int cantidadG = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY.get(g)).get(25));
            precioG = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, g, 5);
            totalG = totalG + (UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precioG), 2) * cantidadG);
            igv = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, g, 11);
        }
        
        for(int g=0;g<VariablesVentas.vArrayList_Prod_XY_Regalos.size();g++){
            int cantidadG = (String.valueOf(((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(g)).get(25)).isEmpty()) ? 0 : Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(g)).get(25));
            //int cantidadG = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(g)).get(25));
            precioG = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, g, 5);
            totalG = totalG + (UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precioG), 2) * cantidadG);
            igv = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, g, 11);
        }
        
        //CARGA CABECERA PRODUCTOS
        for(int p=0;p<VariablesVentas.vArrayList_Prod_XY.size();p++){
            log.debug("CARGA CABECERA PRODUCTOS");
            log.debug("Array VariablesVentas.vArrayList_Prod_XY " + p + " :" + VariablesVentas.vArrayList_Prod_XY.get(p));
            myArrayCab = new ArrayList();
            
            if(vListaCabPack.size() <= 0 || vListaCabPack == null){
                myArrayCab.add(VariablesVentas.vCodProm);
                myArrayCab.add(VariablesVentas.vDescProm);
                int cantidadProd = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY.get(p)).get(25));
                String precio = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, p, 5);
                double total = UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precio), 2) * cantidadProd;
                myArrayCab.add(String.valueOf(cantidadPack));
                myArrayCab.add(FarmaUtility.formatNumber(totalG/cantidadPack, 3));
                myArrayCab.add(FarmaUtility.formatNumber(totalG, 3));
                FarmaUtility.operaListaProd(vListaCabPack, myArrayCab, true, 0);
            }
            
            for(int p1=0;p1<vListaCabPack.size();p1++){
                String codProm = FarmaUtility.getValueFieldArrayList(vListaCabPack, p1, 0);
                if(!codProm.equals(VariablesVentas.vCodProm)){
                    myArrayCab.add(VariablesVentas.vCodProm);
                    myArrayCab.add(VariablesVentas.vDescProm);
                    int cantidad = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY.get(p)).get(25));
                    String precio = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, p, 5);
                    double total = UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precio), 2) * cantidad;
                    myArrayCab.add(String.valueOf(cantidad));
                    myArrayCab.add(precio);
                    myArrayCab.add(FarmaUtility.formatNumber(total, 3));
                    FarmaUtility.operaListaProd(vListaCabPack, myArrayCab, true, 0);
                }
            }
        }
        
        
        
        
        
        //CARGA CABECERA PRODUCTOS REGALO
        for(int r=0;r<VariablesVentas.vArrayList_Prod_XY_Regalos.size();r++){
            log.debug("CARGA CABECERA PRODUCTOS REGALO");
            log.debug("Array VariablesVentas.vArrayList_Prod_XY_Regalos " + r + " :" + VariablesVentas.vArrayList_Prod_XY_Regalos.get(r));
            
            String cantidadIngresada = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 25);
            
            if(cantidadIngresada.length() != 0 || !cantidadIngresada.isEmpty()){
                myArrayDet = new ArrayList();
                
                int cantidad = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(r)).get(25));
                String precio = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 19);
                double procDscto = Double.parseDouble((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(r)).get(18));
                double precioNuevo = (100-procDscto)*(UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precio), 2)/100);
                double total = precioNuevo * cantidad;
                double porcIgv = Double.parseDouble((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(r)).get(11));
                double valor = total*porcIgv/(100+porcIgv);
                double ahorro = (UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precio), 2)) * (procDscto/100);
                
                myArrayDet.add(VariablesVentas.vCodProm);
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 0));
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 1));
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 2));
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 4));
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 25));
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 19));
                myArrayDet.add(FarmaUtility.formatNumber(precioNuevo, 3));
                myArrayDet.add(FarmaUtility.formatNumber(ahorro, 3));
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 18));
                myArrayDet.add(FarmaUtility.formatNumber(total, 3));
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 11));
                myArrayDet.add(FarmaUtility.formatNumber(valor, 2));
                // fraccion de regalo 
                // dubilluz 2017.08.09
                myArrayDet.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, r, 7));
                // fraccion de regalo 
                // dubilluz 2017.08.09
                FarmaUtility.operaListaProd(vListaDetPack, myArrayDet, true, 0);   
            }
        }
        log.debug("VariablesVentas.vArrayList_Prod_XY_Regalos: " + VariablesVentas.vArrayList_Prod_XY_Regalos);
        
        //CARGA DETALLE PRODUCTOS REGALO
        for(int prfinal=0;prfinal<VariablesVentas.vArrayList_Prod_XY.size();prfinal++){
            log.debug("CARGA DETALLE PRODUCTOS REGALO");
            log.debug("Array VariablesVentas.vArrayList_Prod_XY " + prfinal + " :" + VariablesVentas.vArrayList_Prod_XY.get(prfinal));
            
            String cantidadIngresada = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 25);
            log.debug("cantidadIngresada: " + cantidadIngresada);
            
            if(cantidadIngresada.length() != 0 || !cantidadIngresada.isEmpty()){
                myArrayDetFinal = new ArrayList();
                
                int cantidad = Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY.get(prfinal)).get(25));
                String precio = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 19);
                double procDscto = Double.parseDouble((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY.get(prfinal)).get(18));
                double precioNuevo = (100-procDscto)*(UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precio), 2)/100);
                double total = precioNuevo * cantidad;
                double porcIgv = Double.parseDouble((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY.get(prfinal)).get(11));
                double valor = total*porcIgv/(100+porcIgv);
                double ahorro = UtilityVentas.Redondear(FarmaUtility.getDecimalNumber(precio), 2) * (procDscto/100);
                
                myArrayDetFinal.add(VariablesVentas.vCodProm);
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 0));
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 1));
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 2));
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 4));
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 25));
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 19));
                myArrayDetFinal.add(FarmaUtility.formatNumber(precioNuevo, 3));
                myArrayDetFinal.add(FarmaUtility.formatNumber(ahorro, 3));
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 18));
                myArrayDetFinal.add(FarmaUtility.formatNumber(total, 3));
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 11));
                myArrayDetFinal.add(FarmaUtility.formatNumber(valor, 2));
                // fraccion de regalo 
                // dubilluz 2017.08.09
                myArrayDetFinal.add(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY, prfinal, 7));
                // fraccion de regalo 
                // dubilluz 2017.08.09
                FarmaUtility.operaListaProd(vListaDetPack, myArrayDetFinal, true, 0);
            }
        }
        
        VariablesVentas.vCodProm = "000000";
        
        for(int i=0;i<vListaCabPack.size();i++){
            //llenado de arreglo de promociones
            ArrayList myArrayP = new ArrayList();
            double total = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 3));
            double cantidad = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 2));
            String pagototal = FarmaUtility.formatNumber(cantidad * total, 3);
            double igvTotal = FarmaUtility.getDecimalNumber(pagototal) - (FarmaUtility.getDecimalNumber(pagototal)/(1+FarmaUtility.getDecimalNumber(igv)/100));
            myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 0));
            myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 1));
            myArrayP.add(" "); //vacio
            myArrayP.add(FarmaUtility.formatNumber(total, 3));
            myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 2));
            myArrayP.add(VariablesVentas.vDes_Prom); //vacio
            myArrayP.add(FarmaUtility.formatNumber(total,
                                                   3)); //el precio venta seria el mismo que precio , ya que no ahi descuento
            myArrayP.add("" + pagototal);
            myArrayP.add("0.00");
            myArrayP.add(" "); //9
            myArrayP.add(" ");
            myArrayP.add(" ");
            //myArrayP.add("99");//igvTotal + "");
            myArrayP.add(igvTotal + "");
            myArrayP.add(" "); //NUMERO TELEFONICO SI ES RECARGA AUTOMATICA
            myArrayP.add(" "); //INDICADOR DE PRODUCTO VIRTUAL
            myArrayP.add(" "); //TIPO DE PRODUCTO VIRTUAL
            myArrayP.add(" "); //INDICADOR PROD CONTROLA STOCK
            myArrayP.add(" "); //VENTA
            myArrayP.add(" "); //18 myArray.add(VariablesVentas.vVal_Prec_Pub);
            myArrayP.add(" "); //19 myArray.add(VariablesVentas.vIndOrigenProdVta);
            myArrayP.add("S"); //20 es promocion
            myArrayP.add("0"); //21 dscto 2
            myArrayP.add("N"); //22 ind. tratamiento
            myArrayP.add(" "); //23 cantxDia tratamiento
            myArrayP.add(" "); //24 cantxDias tratamiento
            // KMONCADA 10.08.2015
            myArrayP.add("N");//25 INDICA SI PACK APLICA PARA CLIENTE FIDELIZADO O NO
            FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Promociones, myArrayP, true, 0);                
        }
        log.debug("Array VariablesVentas.vArrayList_Promociones :" + VariablesVentas.vArrayList_Promociones);
        
        for(int i=0;i<vListaDetPack.size();i++){
            ArrayList myArray2 = new ArrayList();
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 1));// cod prod
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 2));//des prod
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 3));//unid vta
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7));//val prec lista
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 5)); //ingresa cantidad
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 9)); //proc_Dcto_1;
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7));//precio venta unitario
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 10) + "");//sub total
                myArray2.add(" "); //val_Bono
                myArray2.add(" "); //9
                //myArray2.add("1");//VALOR FRACCION
                // fraccion de regalo 
                // dubilluz 2017.08.09
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 13).replace(",", "").trim() + "");
                // fraccion de regalo 
                // dubilluz 2017.08.09
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 11));//% igv
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 12)); //generado // valor igv
                myArray2.add(" "); //vacio
                myArray2.add("N");
                myArray2.add(" "); //vacio
                myArray2.add("S"); //Indicador de Stock
                myArray2.add(" ");
                myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 0)); //codigo de la promocion donde esta el producto
                myArray2.add("S"); //indica q esta en una promocion
                myArray2.add("0"); //proc_Dcto_2;
                myArray2.add("" +FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7)); //prec_publico; // numero 23
                myArray2.add("" +FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 8)); //JCHAVEZ 20102009 columna 22
                myArray2.add(VariablesVentas.vInd_Origen_Prod_Prom); //JCHAVEZ 20102009 columna 23
                myArray2.add(VariablesVentas.secRespStk); 
                myArray2.add(VariablesVentas.vPrecFijo);                
                FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Prod_Promociones,(ArrayList)(myArray2.clone()), true, 0);
        }
        log.debug("Array VariablesVentas.vArrayList_Prod_Promociones :" + VariablesVentas.vArrayList_Prod_Promociones);
        
        VariablesVentas.vArrayList_Prod_XY.clear();
        VariablesVentas.vArrayList_Prod_XY_Regalos.clear();
        VariablesVentas.sCantidadPackXY = "";
    }
    //FIN AOVIEDO 07/06/2017
    
    //INI AOVIEDO 21/06/2017 VERIFICA Y ELIMINA LAS PROMOCIONES DE ACUERDO A LA FIDELIZACI�N
    public void eliminarPromocionesPorFidelizacion(){
        try{
            if(!(VariablesFidelizacion.vDniCliente.trim().length() > 0)) {
                for (int i = 0; i < VariablesVentas.vArrayList_Promociones.size(); i++) {
                    String codigoPromocion = (String)((ArrayList)VariablesVentas.vArrayList_Promociones.get(i)).get(0);
                    String indFidUso = UtilityPacksPromo.verificaFidelizacionPromocion(codigoPromocion);
                    
                    if(indFidUso.equals("S")){
                        VariablesVentas.vArrayList_Promociones.remove(i);
                        
                        eliminarProdPromocionesPorFidelizacion(codigoPromocion);
                    }
                }
            }
        }catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            e.printStackTrace();
        }
    }
    
    public void eliminarProdPromocionesPorFidelizacion(String codigoPromocion){
        if(VariablesVentas.vArrayList_Prod_Promociones.size() > 0){
            for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
                String codigoPromocionProd = (String)((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i)).get(18);
                
                if(codigoPromocion.equals(codigoPromocionProd)){
                    VariablesVentas.vArrayList_Prod_Promociones.remove(i);
                    eliminarProdPromocionesPorFidelizacion(codigoPromocion);
                }
            }
        }
    }
    //FIN AOVIEDO 21/06/2017 
    
    //INI AOVIEDO 27/06/2017
    public static void actualizarCuponesCampanasR(String tipCampanaCupon, Map mapCupon){
        try{
            if(tipCampanaCupon.trim().equalsIgnoreCase("R")){
                if(mapCupon != null){
                    for(int i = 0; i < VariablesVentas.vArrayList_Cupones.size(); i++){
                        Map mapaCupones = (Map)VariablesVentas.vArrayList_Cupones.get(i);
                        String tipoCampana = (String)mapaCupones.get("TIPO_CAMPANA");
                        if(tipoCampana.equals("R")){
                            VariablesVentas.vArrayList_Cupones.remove(i);
                            actualizarCuponesCampanasR(tipCampanaCupon, mapCupon);
                        }
                    }
                }
            }
        }catch(Exception ex){
            log.error("", ex);
        }
    }
    //FIN AOVIEDO 27/06/2017
    
    
    // INICIO: JHAMRC 10072019
    public boolean validaMostrarISCBolsas(){
        boolean muestraISCBolsa = true;
        String codProdBolsaMed = "";
        String codProdBolsaGran = "";
        
        /*
         * INI DMOSQUEIRA 20190710: Se valida que existe un convenio registrado
         * Si existe convenio, no se ofrece bolsa al cliente
         *  
         * */        
        if (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1) {
            muestraISCBolsa = false;
        } else {
            for(int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC_Temp.size(); i++){
                if(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 3).trim().equals(VariablesISCBolsas.vTipoBolsaMediano)){
                    codProdBolsaMed = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 0);
                }else{
                    codProdBolsaGran = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 0);
                }  
            }
            for(int i=0; i<VariablesVentas.vArrayList_PedidoVenta.size(); i++){
                ArrayList pedidoTemp = (ArrayList) VariablesVentas.vArrayList_PedidoVenta.get(i);
                if (pedidoTemp.get(0).equals(codProdBolsaMed) || pedidoTemp.get(0).equals(codProdBolsaGran)) {
                    muestraISCBolsa = false;
                    i = VariablesVentas.vArrayList_PedidoVenta.size();
                }
            }
        }
        //FIN DMOSQUEIRA 20190710
        return muestraISCBolsa;
    }
    
    private void mostrarISCBolsas(String codigoProducto){
        // dubilluz 30.07.2019
        DlgListaProductos dlgListaProductos = new DlgListaProductos(myParentFrame, "", true,true);
        dlgListaProductos.setPIngresoComprobanteManual(pIngresoComprobanteManual);
        dlgListaProductos.setResumenPedido(this);
        dlgListaProductos.setVisible(true);
        // dubilluz 30.07.2019
        
        DlgIngCantPed_Bolsas dlgIngCantPedBolsas = new DlgIngCantPed_Bolsas(myParentFrame, "Ingreso Cantidad de Bolsas", true);
        
        String codBolsaMed = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, 0, ConstantsISCBolsas.COL_CO_PRODUCTO);
        String codBolsaGran = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, 1, ConstantsISCBolsas.COL_CO_PRODUCTO);
        
        String cantBolsaMed = "0";
        String cantBolsaGran = "0";
        
        for(int i=0; i < VariablesVentas.vArrayList_PedidoVenta.size(); i++){
            String codProdBolsa = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta, i, 0);
            String cantPedBolsa = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta, i, 4);
            if (codProdBolsa.equals(codBolsaMed)) {
                cantBolsaMed = cantPedBolsa;
            } else if (codProdBolsa.equals(codBolsaGran)) {
                cantBolsaGran = cantPedBolsa;
            }
        }
        dlgIngCantPedBolsas.setTxtPrecioGran(cantBolsaGran);
        dlgIngCantPedBolsas.setTxtPrecioMed(cantBolsaMed);
        
        dlgIngCantPedBolsas.setIsGrande(false);
        if(codigoProducto.equals(codBolsaGran)){
            dlgIngCantPedBolsas.setIsGrande(true);
        }
        
        dlgIngCantPedBolsas.setVisible(true);        
        
        if(FarmaVariables.vAceptar){
            //actualizaProductos();
            UtilityCalculoPrecio.clearDetalleVenta();
            UtilityCalculoPrecio.uploadDetalleVenta(VariablesVentas.vArrayList_PedidoVenta);
            neoOperaResumenPedido();
            setISCTotal();
        }else{
            return;
        }

    }

    public void setISCTotal(){
        double tempTotal = 0.00;
        /*
        for(int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC_Temp.size(); i++){
            try{  
                String total = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 6);
                tempTotal += FarmaUtility.getDecimalNumber(total);
            }catch (Exception ex){
                tempTotal = 0.00;
                log.info(ex.toString());
            }
        }*/
        for(int i=0;i< UtilityCalculoPrecio.getListaDetalleVenta().size();i++){
            BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
            try{  
                String total = bean.getVIcbperTotal();
                tempTotal += FarmaUtility.getDecimalNumber(total);
            }catch (Exception ex){
                tempTotal = 0.00;
                log.info(ex.toString());
            }            
        }
        
        
        
        lblICBPER.setText(FarmaUtility.formatNumber(tempTotal));
    }
    // FIN: JHAMRC 10072019
    
}
