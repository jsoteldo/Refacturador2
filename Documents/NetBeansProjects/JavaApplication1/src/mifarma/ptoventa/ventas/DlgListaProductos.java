package mifarma.ptoventa.ventas;


import com.gs.mifarma.componentes.ColumnGroup;
import com.gs.mifarma.componentes.GroupableTableHeader;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import farmaciasperuanas.reference.VariablesRefacturadorElectronico;

import farmapuntos.bean.BeanTarjeta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.URL;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.iscbolsas.reference.ConstantsISCBolsas; // JHAMRC 10072019
import mifarma.ptoventa.iscbolsas.reference.UtilityISCBolsas; // JHAMRC 10072019
import mifarma.ptoventa.iscbolsas.reference.VariablesISCBolsas; // JHAMRC 10072019

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.campAcumulada.DlgListaCampAcumulada;
import mifarma.ptoventa.campAcumulada.reference.VariablesCampAcumulada;
import mifarma.ptoventa.campana.reference.VariablesCampana;
import mifarma.ptoventa.centromedico.reference.DBReceta;
import mifarma.ptoventa.centromedico.reference.FacadeVentaAtencionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.centromedico.reference.proceso.proceso.DlgPrpcesaRecetaPedido;
import mifarma.ptoventa.convenioBTLMF.DlgListaConveniosBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;

//import mifarma.ptoventa.convenioSITEDS.DlgMain;

//import mifarma.ptoventa.convenioSITEDS.DlgDatosAdicional;

//import mifarma.ptoventa.convenioSITEDS.reference.UtilityConvenioSITEDS;
import mifarma.ptoventa.delivery.DlgListaClientes;
import mifarma.ptoventa.delivery.reference.VariablesDelivery;
import mifarma.ptoventa.fidelizacion.reference.AuxiliarFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.DBFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.UtilityFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.inventario.reference.UtilityInventario;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.main.FrmEconoFar;
import mifarma.ptoventa.puntos.DlgMensajeBienvenida;
import mifarma.ptoventa.puntos.modelo.BeanTarjetaMonedero;
import mifarma.ptoventa.puntos.reference.ConstantsPuntos;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.recaudacion.DlgRecaudacionTeleton;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recetario.DlgListaGuiasRM;
import mifarma.ptoventa.recetario.DlgResumenRecetarioMagistral;
import mifarma.ptoventa.recetario.reference.VariablesRecetario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import static mifarma.ptoventa.reference.UtilityPtoVenta.limpiaCadenaAlfanumerica;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.reportes.reference.VariablesReporte;
import mifarma.ptoventa.vendedor.DAOVendedor.DBVendedor;
import mifarma.ptoventa.vendedor.DlgMetasVendedor;
import mifarma.ptoventa.ventas.reference.BeanDetalleVenta;
import mifarma.ptoventa.ventas.reference.BeanInfoPrecioMonedero;
import mifarma.ptoventa.ventas.reference.BeanInfoPrecioNormal;
import mifarma.ptoventa.ventas.reference.BeanInfoPrecioProd;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBCalculoPrecio;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityCalculoPrecio;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2005 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : DlgListaProductos.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * LMESIA      28.12.2005   Creaci�n
 * PAULO       28.04.2006   Modificacion <br>
 * DUBILLUZ    14.06.2007   Modificacion <br>
 * ERIOS       01.06.2008   Modificacion <br>
 * DVELIZ      22.08.2008   Modificacion <br>
 * JCALLO      03.03.2009   Modificacion <br>
 * ASOSA       02.02.2010   Modificacion <br>
 * AOVIEDO     07.04.2017   Modificaci�n <br>
 * <br>
 * @author Luis Mesia Rivera<br>
 * @version 1.0<br>
 *
 */

public class DlgListaProductos extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgListaProductos.class);

    private Frame myParentFrame;

    private JTable myJTable;

    private FarmaTableModel tableModelListaPrecioProductos;
    private FarmaTableModel tblModelListaSustitutos;

    private String descUnidPres = "";
    private String stkProd = "";
    private String valPrecPres = "";
    private String valFracProd = "";
    private String indProdCong = "";
    //private String valPrecLista = "";
    private String valPrecVta = "";
    private String descUnidVta = "";
    private String indProdHabilVta = "";
    private String porcDscto_1 = "";
    //private String indProdProm = "";
    // kmoncada 02.07.2014 obtiene el estado del producto.
    private String estadoProd = "";

    private String tipoProducto = ""; //ASOSA - 02/10/2014 - PANHD


    /**
     * Indicador de Tipo de Producto
     * @author dubilluz
     * @since  22.10.2007
     */
    private String tipoProd = "";
    private String bonoProd = "";

    private int totalItems = 0;
    private double totalVenta = 0;

    private String tempCodBarra = "";

    /**
     * Indicadores de stock en adicional en fraccion del local.
     * @author Edgar Rios Navarro
     * @since 03.06.2008
     */
    private String stkFracLoc = "";
    private String descUnidFracLoc = "";

    /**
     * Columnas de la grilla
     * @author Edgar Rios Navarro
     * @since 09.04.2008
     */
    private final int COL_COD = 1;
    private final int COL_DESC_PROD = 2;
    private final int COL_STOCK = 5;
    private final int COL_ZAN = 7;
    private final int COL_ORD_LISTA = 18;
    private final int COL_IND_ENCARTE = 19;
    private final int COL_ORIG_PROD = 20;

    //JCORTEZ 23.07.08
    private final int COL_RES_CUPON = 25;
    private final int COL_RES_ORIG_PROD = 19;
    private final int COL_RES_VAL_FRAC = 10;
    private final int DIG_PROD = 6;

    private final int COL_RES_IND_TRAT = 22;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JLabel lblItems = new JLabel();
    private JLabel lblItems_T = new JLabel();
    private JLabel lblPrecio = new JLabel();
    private JLabel lblPrecio_T = new JLabel();
    private JLabel lblUnidad = new JLabel();
    private JLabel lblUnidad_T = new JLabel();
    private JLabelFunction lblF6 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JPanel jPanel2 = new JPanel();
    private JSeparator jSeparator2 = new JSeparator();
    private JLabel lblDescLab_Alter = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JPanel jPanel1 = new JPanel();
    private JLabel lblDescLab_Prod = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JPanel pnlIngresarProductos = new JPanel();
    private JButton btnBuscar = new JButton();
    private JTextField txtProducto = new JTextField();
    private JButton btnProducto = new JButton();
    private JTable tblProductos = new JTable();
    private JTable tblListaSustitutos = new JTable();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JLabel lblTotalVenta = new JLabel();
    private JLabel lblTotalVenta_T = new JLabel();
    private JPanel jPanel4 = new JPanel();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JLabelWhite lblCliente = new JLabelWhite();
    private JLabelWhite lblCliente_T = new JLabelWhite();
    private JLabelWhite lblClienteConv = new JLabelWhite();
    private JLabelWhite lblClienteConv_T = new JLabelWhite();

    private JButtonLabel btnProdAlternativos = new JButtonLabel();

    private JButtonLabel btnRelacionProductos = new JButtonLabel();
    private JLabelFunction lblF7 = new JLabelFunction();
    private JLabelFunction lblF9 = new JLabelFunction();
    private JLabelFunction lblF10 = new JLabelFunction();
    private JLabelFunction lblF8 = new JLabelFunction();
    private JLabelWhite lblMedico = new JLabelWhite();
    private JLabelWhite lblMedicoT = new JLabelWhite();

    private JLabelWhite lblConvenio = new JLabelWhite();
    private JLabelWhite lblConvenioT = new JLabelWhite();


    private JLabel lblProdIgv = new JLabel();
    private JLabelFunction lblF12 = new JLabelFunction();
    private JLabelFunction lblF13 = new JLabelFunction();
    private JPanel jPanel5 = new JPanel();
    private JLabel lblProdRefrig = new JLabel();
    private JLabel lblProdCong = new JLabel();
    private JLabelWhite lblIndTipoProd = new JLabelWhite();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabel lblProdProm = new JLabel();
    private JLabelFunction lblF4 = new JLabelFunction();
    private JLabel lblProdEncarte = new JLabel();
    private JLabelWhite lblStockAdic_T = new JLabelWhite();
    private JLabelWhite lblStockAdic = new JLabelWhite();
    private JLabelWhite lblUnidFracLoc = new JLabelWhite();
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private Object operaCampa�asFid;
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private JPanelHeader jPanelHeader0 = new JPanelHeader();

    private JLabel lblMensajeCampa�a = new JLabel();
    // private JLabelFunction lblF12 = new JLabelFunction();


    private boolean vEjecutaAccionTeclaListado = false;
    //JMIRANDA 16/09/2009
    private JButtonLabel lblMensajeCodBarra = new JButtonLabel();

    private boolean pasoTarjeta = false;

    private int contarCombinacion = 0; //kmoncada contador para validar combinacion de teclas ALT+S o ALT+R o ALT+P

    private JLabel lblDNI_SIN_COMISION = new JLabel();

    String nombCliente = " ";
    String nombConvenio = " ";
    private JLabelFunction lblDeliveryProv = new JLabelFunction();
    private DlgResumenPedido dlgResumenPedido;
    
    String vIndActGarantizados;

    // KMONCADA 2015.02.10 PROGRAMA DE PUNTOS
    private boolean isLectoraLazer, isCodigoBarra, isEnter;
    private long tiempoTeclaInicial ,tiempoTeclaFinal,OldtmpT2;
    private ArrayList lstTiempoTecla = new ArrayList();
    
    private boolean pIngresoComprobanteManual = false;
    private JLabelWhite lblPuntosRentables = new JLabelWhite();
    
    
    // DUBILLUZ 31.08.2016
        String pRecetaCodCia    = "";
        String pRecetaCodLocal  = "";
        String pRecetaNumReceta = "";
    private JLabel lblReceta = new JLabel();
    // DUBILLUZ 31.08.2016
    // KMONCADA 06.09.2016 [CENTRO MEDICO]
    private boolean listadoReceta = false;
    private String codProdRecetaSelect = "";
    private FarmaTableModel tblMdlListaReceta;


    private JLabel lblInfoFraccion = new JLabel();
    private JLabel lblInfoSugerido = new JLabel();
    private JLabel lblInfoAhorro = new JLabel();
    private JTextPane jepMonedero = new JTextPane();
    private JEditorPane jepMayor = new JEditorPane();
    private JEditorPane jepNormal = new JEditorPane();
    private JButton btnMetas = new JButton();
    private JPanel pnlMsjProducto = new JPanel(); //RARGUMEDO
    private JLabel lblMsjArgumento_Prod = new JLabel(); //RARGUMEDO
    // dubilluz 30.07.2019
    public boolean cerrarVentana = false;
    // dubilluz 30.07.2019
    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgListaProductos() {
        this(null, "", false);
    }

    public DlgListaProductos(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }
    
    public DlgListaProductos(Frame parent, String title, boolean modal,boolean cerrarVentana) {
        super(parent, title, modal);
        myParentFrame = parent;
        // dubilluz 30.07.2019
        this.cerrarVentana =cerrarVentana; 
        // dubilluz 30.07.2019
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }
    
    public DlgListaProductos(Frame parent, String title, boolean modal, boolean listadoReceta, FarmaTableModel tblMdlListaReceta) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.listadoReceta = listadoReceta;
        this.tblMdlListaReceta = tblMdlListaReceta;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    // **************************************************************************
    // M�todo "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        //this.setSize(new Dimension(737, 567));
        this.setSize(new Dimension(747, 605));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Lista de Productos y Precios");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.white);
        this.setBackground(Color.white);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jepMonedero.setContentType("text/html");
        jepMayor.setContentType("text/html");
        jepNormal.setContentType("text/html");
        pnlMsjProducto.add(lblMsjArgumento_Prod, null); //RARGUMEDO 2019-07-25
        jContentPane.add(pnlMsjProducto, null); //RARGUMEDO 2019-07-25
        jContentPane.add(jepNormal, null);
        jContentPane.add(jepMayor, null);
        jContentPane.add(jepMonedero, null);
        jContentPane.add(lblInfoAhorro, null);
        jContentPane.add(lblInfoSugerido, null);
        jContentPane.add(lblInfoFraccion, null);


        lblInfoFraccion.setVisible(false);
        lblInfoSugerido.setVisible(false);
        lblInfoAhorro.setVisible(false);

        lblInfoFraccion.setBounds(new Rectangle(15, 335, 220, 35));
        lblInfoFraccion.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfoFraccion.setHorizontalTextPosition(SwingConstants.CENTER);
        lblInfoFraccion.setFont(new Font("SansSerif", 1, 13));
        lblInfoFraccion.setForeground(new Color(0, 132, 0));
        lblInfoSugerido.setBounds(new Rectangle(250, 335, 235, 35));
        lblInfoSugerido.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfoSugerido.setHorizontalTextPosition(SwingConstants.CENTER);
        lblInfoSugerido.setFont(new Font("SansSerif", 1, 13));
        lblInfoSugerido.setForeground(new Color(0, 132, 0));
        lblInfoAhorro.setBounds(new Rectangle(480, 335, 235, 35));
        lblInfoAhorro.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfoAhorro.setForeground(Color.red);
        lblInfoAhorro.setFont(new Font("SansSerif", 1, 13));


        jepNormal.setBounds(new Rectangle(10, 285, 240, 45)); //RARGUMEDO 2019-07-25
        jepMonedero.setBounds(new Rectangle(260, 285, 225, 45)); //RARGUMEDO 2019-07-25
        jepMayor.setBounds(new Rectangle(495, 285, 225, 45)); //RARGUMEDO 2019-07-25
        
        //jepMonedero.setText("<style type=\"text/css\"> body {     font-family: 'trebuchet MS', 'Lucida sans', Arial;     color: #444;     margin: 0px auto; } table {     border-spacing: 0;     font-size: 9; } table td{     width: 90px; } .bordered td, .bordered th {     padding: 0px;     text-align: center;     }.bordered th {     background-color: #ffefd6; }</style><div> <table border=1 class=\"bordered\"> <tr class=\"style4\"><td colspan=\"2\"><center><b>Monedero</b></center></td></tr> <tr> <td>TABLETA</td> <td>BLIST/10 TAB</td></tr> <tr><td>S/ 1.50</td>     <td>S/ 14.50</td></tr>  </table>");
        jepMonedero.setFont(new Font("SansSerif", 0, 10));
        jepMonedero.setBorder(BorderFactory.createTitledBorder(""));
        
        
        jepMayor.setBorder(BorderFactory.createTitledBorder(""));
        
        jepNormal.setBorder(BorderFactory.createTitledBorder(""));
        btnMetas.setText("Mis Ventas");
        btnMetas.setBounds(new Rectangle(580, 5, 110, 20));
        btnMetas.setBackground(SystemColor.control);
        btnMetas.setMnemonic('M');
        btnMetas.setDefaultCapable(false);
        btnMetas.setFocusPainted(false);
        btnMetas.setRequestFocusEnabled(false);
        btnMetas.setFont(new Font("SansSerif", 1, 12));

        btnMetas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnMetas_ActionPerformed(e);
                }
            });
		//INI RARGUMEDO 2019-07-25
        pnlMsjProducto.setBounds(new Rectangle(15, 460, 700, 25));
        pnlMsjProducto.setBackground(new Color(211, 237, 212));
        pnlMsjProducto.setLayout(null);
        lblMsjArgumento_Prod.setText("Argumento ayuda de explicacion de producto a vender para el tecnico verdedro de locales Inkafarma y Mifarma - Farmacias Peruanas");
        lblMsjArgumento_Prod.setBounds(new Rectangle(5, 0, 690, 20));
        lblMsjArgumento_Prod.setFont(new Font("Tahoma", 1, 12));
        lblMsjArgumento_Prod.setForeground(Color.red);
        //FIN RARGUMEDO 2019-07-25
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(623, 439));
        jContentPane.setForeground(Color.white);
        jPanel3.setBounds(new Rectangle(15, 60, 470, 45));
        jPanel3.setBackground(new Color(43, 141, 39));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel3.setLayout(null);
        lblItems.setText("0");
        lblItems.setBounds(new Rectangle(145, 5, 80, 15));
        lblItems.setFont(new Font("SansSerif", 1, 14));
        lblItems.setForeground(Color.white);
        lblItems.setHorizontalAlignment(SwingConstants.RIGHT);
        lblItems_T.setText("Items :");
        lblItems_T.setBounds(new Rectangle(15, 5, 65, 15));
        lblItems_T.setFont(new Font("SansSerif", 1, 14));
        lblItems_T.setForeground(Color.white);
        lblPrecio.setBounds(new Rectangle(115, 20, 120, 20));
        lblPrecio.setFont(new Font("SansSerif", 1, 15));
        lblPrecio.setForeground(Color.white);
        lblPrecio_T.setText("<html><center>Precio regular: S/</center></html>");
        lblPrecio_T.setBounds(new Rectangle(10, 20, 115, 20));
        lblPrecio_T.setFont(new Font("SansSerif", 1, 11));
        lblPrecio_T.setForeground(Color.white);
        lblUnidad.setBounds(new Rectangle(70, 5, 195, 15));
        lblUnidad.setFont(new Font("SansSerif", 1, 11));
        lblUnidad.setForeground(Color.white);
        lblUnidad_T.setText("Unidad :");
        lblUnidad_T.setBounds(new Rectangle(15, 5, 60, 15));
        lblUnidad_T.setFont(new Font("SansSerif", 1, 11));
        lblUnidad_T.setForeground(Color.white);
        lblF6.setText("[ F6 ] Buscar Por");
        lblF6.setBounds(new Rectangle(155, 515, 130, 20));
        lblF2.setText("[ F2 ] Ver Alternativos");
        lblF2.setBounds(new Rectangle(290, 490, 130, 20));
        lblF1.setText("[ F1 ] Info Prod");
        lblF1.setBounds(new Rectangle(155, 490, 130, 20));
        jScrollPane2.setBounds(new Rectangle(15, 350, 700, 110)); //RARGUMEDO 2019-07-25
        jScrollPane2.setBackground(new Color(255, 130, 14));
        jPanel2.setBounds(new Rectangle(15, 330, 700, 20)); //RARGUMEDO 2019-07-25
        jPanel2.setBackground(new Color(255, 130, 14));
        jPanel2.setLayout(null);
        jSeparator2.setBounds(new Rectangle(200, 0, 15, 20));
        jSeparator2.setBackground(Color.black);
        jSeparator2.setOrientation(SwingConstants.VERTICAL);
        lblDescLab_Alter.setBounds(new Rectangle(225, 0, 375, 20));
        lblDescLab_Alter.setFont(new Font("SansSerif", 1, 11));
        lblDescLab_Alter.setForeground(Color.white);
        jScrollPane1.setBounds(new Rectangle(15, 125, 700, 140)); //RARGUMEDO 2019-07-25
        jScrollPane1.setBackground(new Color(255, 130, 14));
        jPanel1.setBounds(new Rectangle(15, 105, 700, 20));
        jPanel1.setBackground(new Color(255, 130, 14));
        jPanel1.setLayout(null);
        lblDescLab_Prod.setBounds(new Rectangle(160, 0, 345, 20));
        lblDescLab_Prod.setFont(new Font("SansSerif", 1, 11));
        lblDescLab_Prod.setForeground(Color.white);
        jSeparator1.setBounds(new Rectangle(150, 0, 15, 20));
        jSeparator1.setBackground(Color.black);
        jSeparator1.setOrientation(SwingConstants.VERTICAL);
        pnlIngresarProductos.setBounds(new Rectangle(15, 30, 700, 30));
        pnlIngresarProductos.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlIngresarProductos.setBackground(new Color(43, 141, 39));
        pnlIngresarProductos.setLayout(null);
        pnlIngresarProductos.setForeground(Color.orange);
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(495, 5, 75, 20));
        btnBuscar.setBackground(SystemColor.control);
        btnBuscar.setMnemonic('b');
        btnBuscar.setDefaultCapable(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.setFont(new Font("SansSerif", 1, 12));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        txtProducto.setBounds(new Rectangle(75, 5, 415, 20));
        txtProducto.setFont(new Font("SansSerif", 1, 11));
        txtProducto.setForeground(new Color(32, 105, 29));
        txtProducto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtProducto_keyPressed(e);

                }

            public void keyReleased(KeyEvent e) {
                    txtProducto_keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {
                txtProducto_keyTyped(e);
            }
        });
        txtProducto.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                    txtProducto_focusLost(e);
                }
        });
        btnProducto.setText("Producto");
        btnProducto.setBounds(new Rectangle(15, 5, 60, 20));
        btnProducto.setMnemonic('p');
        btnProducto.setFont(new Font("SansSerif", 1, 11));
        btnProducto.setDefaultCapable(false);
        btnProducto.setRequestFocusEnabled(false);
        btnProducto.setBackground(new Color(50, 162, 65));
        btnProducto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProducto.setFocusPainted(false);
        btnProducto.setHorizontalAlignment(SwingConstants.LEFT);
        btnProducto.setContentAreaFilled(false);
        btnProducto.setBorderPainted(false);
        btnProducto.setForeground(Color.white);
        btnProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnProducto_actionPerformed(e);
            }
        });
        tblProductos.setFont(new Font("SansSerif", 0, 12));
        tblProductos.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    tblProductos_mouseClicked(e);
                }
            });
        tblListaSustitutos.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    tblListaSustitutos_mouseClicked(e);
                }
            });
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(155, 540, 130, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(565, 540, 150, 20));
        lblEnter.setText("[ ENTER ] Seleccionar");
        lblEnter.setBounds(new Rectangle(15, 490, 135, 20));
        lblTotalVenta.setText("0.00");
        lblTotalVenta.setBounds(new Rectangle(140, 25, 85, 15));
        lblTotalVenta.setFont(new Font("SansSerif", 1, 15));
        lblTotalVenta.setForeground(Color.white);
        lblTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalVenta_T.setText("Total venta : " + ConstantesUtil.simboloSoles);
        lblTotalVenta_T.setBounds(new Rectangle(15, 25, 120, 15));
        lblTotalVenta_T.setFont(new Font("SansSerif", 1, 15));
        lblTotalVenta_T.setForeground(Color.white);
        jPanel4.setBounds(new Rectangle(485, 60, 230, 45));
        jPanel4.setBackground(new Color(43, 141, 39));
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel4.setLayout(null);
        pnlTitle1.setBounds(new Rectangle(15, 0, 700, 30));
        lblCliente.setBounds(new Rectangle(70, 5, 335, 20));
        lblCliente.setText(" ");
        lblCliente.setFont(new Font("SansSerif", 1, 14));
        lblCliente_T.setText("Cliente:");
        lblCliente_T.setBounds(new Rectangle(5, 5, 60, 20));
        lblCliente_T.setFont(new Font("SansSerif", 1, 14));
        btnProdAlternativos.setText("Productos Sustitutos");
        btnProdAlternativos.setBounds(new Rectangle(10, 0, 150, 20));
        btnProdAlternativos.setMnemonic('s');
        btnProdAlternativos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnProdAlternativos_actionPerformed(e);
                }
        });
        btnRelacionProductos.setText("Relacion de Productos");
        btnRelacionProductos.setBounds(new Rectangle(10, 0, 140, 20));
        btnRelacionProductos.setMnemonic('r');
        btnRelacionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionProductos_actionPerformed(e);
            }
        });
        lblF7.setBounds(new Rectangle(290, 515, 130, 20));
        lblF7.setText("[ F7 ] Filtrar Desc.");
        lblF9.setBounds(new Rectangle(565, 515, 150, 20));
        //lblF9.setText("[ F9 ] Asociar Campa�a");//lblF9.setText("[ F9 ] Vta. Delivery");//JCALLO 19.12.2008 SE REEMPLAZO PARA OPCION DE CAMP ACUMULADAS
        lblF9.setText("[ F9 ] Camp. Acumulada");
        lblF10.setBounds(new Rectangle(15, 540, 135, 20));
        lblF10.setText("[ F10 ] Venta Perdida");
        lblF8.setBounds(new Rectangle(425, 515, 135, 20));
        lblF8.setText("[ F8 ] Dcto por Receta");
        lblMedico.setBounds(new Rectangle(475, 5, 220, 20));
        lblMedico.setText(" ");
        lblMedicoT.setText("Medico:");
        lblMedicoT.setBounds(new Rectangle(420, 5, 45, 20));
        lblProdIgv.setBounds(new Rectangle(125, 0, 95, 20));
        lblProdIgv.setFont(new Font("SansSerif", 1, 11));
        lblProdIgv.setText("SIN IGV");
        lblProdIgv.setBackground(new Color(44, 146, 24));
        lblProdIgv.setOpaque(true);
        lblProdIgv.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdIgv.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblProdIgv.setForeground(Color.white);
        lblF12.setBounds(new Rectangle(350, 445, 150, 20));
        lblF12.setText("[ F12 ] Buscar TrjxDNI");
        lblF12.setVisible(true);
        lblF13.setBounds(new Rectangle(425, 490, 135, 20));
        lblF13.setText("[ F3 ] Vta. Convenio");
        jPanel5.setBounds(new Rectangle(15, 265, 700, 20)); //RARGUMEDO 2019-07-25
        jPanel5.setBackground(new Color(255, 130, 14));
        jPanel5.setLayout(null);
        lblProdRefrig.setBounds(new Rectangle(480, 0, 95, 20));
        lblProdRefrig.setVisible(true);
        lblProdRefrig.setFont(new Font("SansSerif", 1, 11));
        lblProdRefrig.setText("REFRIG");
        lblProdRefrig.setBackground(new Color(44, 146, 24));
        lblProdRefrig.setOpaque(true);
        lblProdRefrig.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdRefrig.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblProdRefrig.setForeground(Color.white);
        lblProdCong.setBounds(new Rectangle(15, 0, 95, 20));
        lblProdCong.setVisible(true);
        lblProdCong.setFont(new Font("SansSerif", 1, 11));
        lblProdCong.setText("CONGELADO");
        lblProdCong.setBackground(new Color(44, 146, 24));
        lblProdCong.setOpaque(true);
        lblProdCong.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdCong.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblProdCong.setForeground(Color.white);
        lblIndTipoProd.setBounds(new Rectangle(545, 0, 140, 20));
        lblIndTipoProd.setFont(new Font("SansSerif", 1, 12));
        lblF5.setBounds(new Rectangle(15, 515, 135, 20));
        lblF5.setText("[ F5 ] Pack");
        KeyAdapter keyAdapterlblF5 = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblF5_keyPressed(e);
            }
        };
        lblF5.addKeyListener(keyAdapterlblF5);
        lblProdProm.setBounds(new Rectangle(240, 0, 220, 20));
        lblProdProm.setFont(new Font("SansSerif", 1, 11));
        lblProdProm.setText("PRODUCTO EN PACK");
        lblProdProm.setBackground(new Color(44, 146, 24));
        lblProdProm.setOpaque(true);
        lblProdProm.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdProm.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblProdProm.setForeground(Color.white);
        lblF4.setBounds(new Rectangle(565, 490, 150, 20));
        if (VariablesPtoVenta.vIndVerStockLocales.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            lblF4.setText("[ F4 ] Stock Locales");
        } else if (VariablesPtoVenta.vIndVerReceMagis.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            lblF4.setText("[ F4 ] Recetario Magistral");
        }
        lblProdEncarte.setBounds(new Rectangle(590, 0, 95, 20));
        lblProdEncarte.setVisible(true);
        lblProdEncarte.setFont(new Font("SansSerif", 1, 11));
        lblProdEncarte.setText("ENCARTE");
        lblProdEncarte.setBackground(new Color(44, 146, 24));
        lblProdEncarte.setOpaque(true);
        lblProdEncarte.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdEncarte.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblProdEncarte.setForeground(Color.white);
        lblStockAdic_T.setText("Stock adic.:");
        lblStockAdic_T.setBounds(new Rectangle(5, 0, 65, 20));
        lblStockAdic_T.setForeground(new Color(43, 141, 39));
        lblStockAdic.setBounds(new Rectangle(75, 0, 40, 20));
        lblStockAdic.setHorizontalAlignment(SwingConstants.RIGHT);
        lblStockAdic.setForeground(new Color(43, 141, 39));
        lblUnidFracLoc.setBounds(new Rectangle(120, 0, 90, 20));
        lblUnidFracLoc.setForeground(new Color(43, 141, 39));
        jPanelWhite1.setBounds(new Rectangle(255, 25, 215, 20));
        jPanelWhite1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanelHeader1.setBounds(new Rectangle(0, 0, 410, 30));
        lblMensajeCampa�a.setBounds(new Rectangle(385, 0, 315, 30));
        //lblMensajeCampa�a.setText("   Promoci�n: \" Acumula tu Compra \"");
        lblMensajeCampa�a.setText("   ");
        lblMensajeCampa�a.setBackground(Color.white);
        lblMensajeCampa�a.setForeground(Color.red);
        lblMensajeCampa�a.setFont(new Font("Dialog", 1, 14));
        // lblProdRefrig.setBackground(new Color(44, 146, 24));
        lblMensajeCampa�a.setOpaque(true);
        lblMensajeCampa�a.setVisible(false);

        //JMIRANDA 16/09/2009
        lblMensajeCodBarra.setText("");
        lblMensajeCodBarra.setBounds(new Rectangle(285, 0, 160, 20));
        lblMensajeCodBarra.setFont(new Font("SansSerif", 1, 12));
        lblDNI_SIN_COMISION.setText("DNI Inv�lido. No aplica Prog. Atenci�n al Cliente");
        lblDNI_SIN_COMISION.setBounds(new Rectangle(380, 0, 320, 30));
        lblDNI_SIN_COMISION.setForeground(new Color(231, 0, 0));
        lblDNI_SIN_COMISION.setFont(new Font("Dialog", 3, 14));
        lblDNI_SIN_COMISION.setBackground(Color.white);
        lblDNI_SIN_COMISION.setOpaque(true);
        lblDNI_SIN_COMISION.setVisible(false);
        /*
         *         lblMensajeCampa�a.setBackground(Color.white);
        lblMensajeCampa�a.setForeground(Color.red);
         * */
        lblDeliveryProv.setBounds(new Rectangle(425, 540, 135, 20));
        lblDeliveryProv.setText("[Alt+D] Pedido Delivery");
        lblPuntosRentables.setText("Puntos: 1.000");
        lblPuntosRentables.setBounds(new Rectangle(545, 0, 140, 20));
        lblPuntosRentables.setBackground(new Color(148, 0, 0));
        lblPuntosRentables.setOpaque(true);
        lblPuntosRentables.setHorizontalAlignment(SwingConstants.CENTER);
        lblReceta.setVisible(false);
        lblReceta.setBounds(new Rectangle(675, 5, 40, 40));
        lblReceta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF12.setBounds(new Rectangle(290, 540, 130, 20));
        pnlTitle1.add(lblDNI_SIN_COMISION, null);
        //lblMedico.add(lblMensajeCampa�a, null);
        pnlTitle1.add(lblMensajeCampa�a, null);
        pnlTitle1.add(lblMedicoT, null);
        pnlTitle1.add(lblMedico, null);
        pnlTitle1.add(jPanelHeader1, null);
        jPanelHeader1.add(lblCliente_T, null);
        jPanelHeader1.add(lblCliente, null);
        jPanelWhite1.add(lblStockAdic_T, null);
        //JMIRANDA 16/09/2009
        jPanelWhite1.add(lblStockAdic, null);
        jPanelWhite1.add(lblUnidFracLoc, null);
        jPanel3.add(lblMensajeCodBarra, null);
        jPanel3.add(jPanelWhite1, null);
        jPanel3.add(lblPrecio, null);
        jPanel3.add(lblPrecio_T, null);
        jPanel3.add(lblUnidad, null);
        jPanel3.add(lblUnidad_T, null);
        jScrollPane2.getViewport();
        jPanel2.add(btnProdAlternativos, null);
        jPanel2.add(jSeparator2, null);
        jPanel2.add(lblDescLab_Alter, null);
        jScrollPane1.getViewport();
        jPanel1.add(lblPuntosRentables, null);
        jPanel1.add(lblIndTipoProd, null);
        jPanel1.add(btnRelacionProductos, null);
        jPanel1.add(jSeparator1, null);
        jPanel1.add(lblDescLab_Prod, null);
        pnlIngresarProductos.add(btnMetas, null);
        pnlIngresarProductos.add(btnBuscar, null);
        pnlIngresarProductos.add(txtProducto, null);
        pnlIngresarProductos.add(btnProducto, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        jPanel4.add(lblTotalVenta_T, null);
        jPanel4.add(lblItems_T, null);
        jPanel4.add(lblItems, null);
        jPanel4.add(lblTotalVenta, null);
        jPanel5.add(lblProdEncarte, null);
        jPanel5.add(lblProdProm, null);
        jPanel5.add(lblProdCong, null);
        jPanel5.add(lblProdRefrig, null);
        jPanel5.add(lblProdIgv, null);
        jContentPane.add(lblReceta, null);
        jContentPane.add(lblDeliveryProv, null);
        jContentPane.add(lblF12, null);
        jContentPane.add(lblF4, null);
        jContentPane.add(jPanel5, null);
        jContentPane.add(lblF13, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(lblF12, null);
        jContentPane.add(lblF10, null);
        jContentPane.add(lblF9, null);
        jContentPane.add(lblF7, null);
        jContentPane.add(pnlTitle1, null);
        jContentPane.add(jPanel4, null);
        jContentPane.add(lblEnter, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(jPanel3, null);
        jContentPane.add(lblF6, null);
        jContentPane.add(lblF2, null);
        jContentPane.add(lblF1, null);
        jScrollPane2.getViewport().add(tblListaSustitutos, null);
        jContentPane.add(jScrollPane2, null);
        jContentPane.add(jPanel2, null);
        jScrollPane1.getViewport().add(tblProductos, null);
        jContentPane.add(jScrollPane1, null);
        jContentPane.add(jPanel1, null);
        jContentPane.add(pnlIngresarProductos, null);
        //jContentPane.add(lblF5, null);
        //this.getContentPane().add(jContentPane, null);
    }

    // **************************************************************************
    // M�todo "initialize()"
    // **************************************************************************

    private void initialize() {
        initTableListaPreciosProductos();
        initTableProductosSustitutos();
        setJTable(tblProductos);
        iniciaProceso(true);
        VariablesPtoVenta.vInd_Filtro = FarmaConstants.INDICADOR_N;
        //JQUISPE 03.05.2010
        //Inicializo los valores de las posiciones
        VariablesVentas.vPosNew = 0;
        VariablesVentas.vPosOld = 0;

        // Inicio Adicion Delivery 28/04/2006 Paulo
        if (!FarmaVariables.vAceptar) {
            String nombreClienteDeliv =
                VariablesDelivery.vNombreCliente + " " + VariablesDelivery.vApellidoPaterno + " " +
                VariablesDelivery.vApellidoMaterno;
            //Modificado por DVELIZ 30.09.08
            //jcallo 02.10.2008
            if (VariablesFidelizacion.vNumTarjeta.trim().length() <= 0) {
                lblCliente.setText(nombreClienteDeliv);
            } else {
                String msjPto = "";
                if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && 
                   VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!=null){
                    msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
                }
                lblCliente.setText(msjPto + VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente +
                                   " " + VariablesFidelizacion.vApeMatCliente);
            }
            //fin jcallo 02.10.2008
            lblMedico.setText(VariablesVentas.vNombreListaMed);
            FarmaVariables.vAceptar = true;
        }
        // Fin Adicion Delivery 28/04/2006 Paulo
        FarmaVariables.vAceptar = false;

        //Dveliz 26.08.08
        VariablesCampana.vListaCupones = new ArrayList();

        /** JCALLO 01.10.2008**/
        /*if ( VariablesVentas.HabilitarF9.equalsIgnoreCase(
            ConstantsVentas.ACTIVO) ) {
        lblF9.setVisible(true);
    }else if( VariablesVentas.HabilitarF9.equalsIgnoreCase(
            ConstantsVentas.INACTIVO) ){
        lblF9.setVisible(false);
    }*/
        //lblF9.setVisible(true);
        lblF9.setVisible(false);//No se utiliza la funcionalidad del F9

        if (VariablesPtoVenta.vIndVerStockLocales.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) { //JCHAVEZ 08102009.sn
            lblF4.setVisible(true);
        } else if (VariablesPtoVenta.vIndVerReceMagis.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            lblF4.setVisible(true);
        } else {
            lblF4.setVisible(false);
        }

        try {
            lblDeliveryProv.setVisible(DBVentas.getIndVerPedidoDelivery());
        } catch (SQLException ex) {
            lblDeliveryProv.setVisible(false);
            log.error("", ex);
        }
        //JCHAVEZ 08102009.en

        VariablesRecetario.strCodigoRecetario = "";
        vIndActGarantizados = DlgProcesar.cargaIndActGarantizados();
        //INI JMONZALVE 24042019
        try {
            if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                if(!DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                    lblF12.setVisible(false);
                }
            }
        } catch (SQLException e) {
        }
        //FIN JMONZALVE 24042019
    }

    // **************************************************************************
    // M�todos de inicializaci�n
    // **************************************************************************

    private void initTableListaPreciosProductos() {
        
        if(isListadoReceta()){
            (ConstantsVentas.columnsListaProdPrecios[21]).m_width=0;
            (ConstantsVentas.columnsListaProdPrecios[22]).m_width=0;
            (ConstantsVentas.columnsListaProdPrecios[23]).m_width=0;
        }
        else{
            (ConstantsVentas.columnsListaProdPrecios[21]).m_width=65;
            (ConstantsVentas.columnsListaProdPrecios[22]).m_width=65;
            (ConstantsVentas.columnsListaProdPrecios[23]).m_width=65;
        }
            
        
        tableModelListaPrecioProductos =
                new FarmaTableModel(ConstantsVentas.columnsListaProdPrecios, ConstantsVentas.defaultValuesListaProdPrecios,
                                    0);
        clonarListadoProductos();
        FarmaUtility.initSelectList(tblProductos, tableModelListaPrecioProductos,
                                    ConstantsVentas.columnsListaProdPrecios);
       
        tblProductos.setName(ConstantsVentas.NAME_TABLA_PRODUCTOS);        
        //GroupColumn        
        TableColumnModel cm = tblProductos.getColumnModel();
        ColumnGroup g_other = new ColumnGroup("Precio Venta "+ConstantesUtil.simboloSoles);
        g_other.add(cm.getColumn(21));
        g_other.add(cm.getColumn(22));
        g_other.add(cm.getColumn(23));
        GroupableTableHeader header = new GroupableTableHeader(cm);
        header.addColumnGroup(g_other);
        
        tblProductos.setTableHeader(header);  
        
        if (tableModelListaPrecioProductos.getRowCount() > 0)
            FarmaUtility.ordenar(tblProductos, tableModelListaPrecioProductos, COL_ORD_LISTA,
                                 FarmaConstants.ORDEN_ASCENDENTE);
    }

    private void initTableProductosSustitutos() {
        tblModelListaSustitutos =
                new FarmaTableModel(ConstantsVentas.columnsListaProdPrecios, ConstantsVentas.defaultValuesListaProdPrecios,
                                    0);
        FarmaUtility.initSelectList(tblListaSustitutos, tblModelListaSustitutos,
                                    ConstantsVentas.columnsListaProdPrecios);
        
        tblListaSustitutos.setName(ConstantsVentas.NAME_TABLA_SUSTITUTOS);      
        
        //GroupColumn        
        TableColumnModel cm = tblListaSustitutos.getColumnModel();
        ColumnGroup g_other = new ColumnGroup("Precio Venta "+ConstantesUtil.simboloSoles);
        g_other.add(cm.getColumn(21));
        g_other.add(cm.getColumn(22));
        g_other.add(cm.getColumn(23));
        GroupableTableHeader header = new GroupableTableHeader(cm);
        header.addColumnGroup(g_other);
        
        tblListaSustitutos.setTableHeader(header);  
        
        muestraProductosSustitutos();
    }

    public void iniciaProceso(boolean pInicializar) {

        for (int i = 0; i < tblProductos.getRowCount(); i++)
            tblProductos.setValueAt(new Boolean(false), i, 0);

        for (int i = 0; i < VariablesVentas.tableModelListaGlobalProductos.getRowCount(); i++)
            VariablesVentas.tableModelListaGlobalProductos.setValueAt(new Boolean(false), i, 0);

        if (pInicializar) {
            VariablesVentas.vArrayList_PedidoVenta = new ArrayList();
            UtilityCalculoPrecio.loadDetalleVenta(VariablesVentas.vArrayList_PedidoVenta);
        }

        log.debug("VariablesVentas.vArrayList_PedidoVenta : " + VariablesVentas.vArrayList_PedidoVenta.size());
        ArrayList myArray = new ArrayList();
        for (int i = 0; i < VariablesVentas.vArrayList_PedidoVenta.size(); i++)
            myArray.add((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(i));

        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++)
            myArray.add((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i));


        FarmaUtility.ponerCheckJTable(tblProductos, COL_COD, myArray, 0);
        //if ( !pInicializar )
        actualizaListaProductosAlternativos();
        muestraInfoProd();
        
        UtilityVentas.muestraNombreLab(myJTable,4, lblDescLab_Prod);
        UtilityVentas.muestraProductoInafectoIgv(myJTable,11, lblProdIgv);
        UtilityVentas.muestraProductoPromocion(myJTable,17, lblProdProm,1);
        UtilityVentas.muestraProductoRefrigerado(myJTable,15, lblProdRefrig);
        UtilityVentas.muestraIndTipoProd(myJTable,16, lblIndTipoProd);
       
        UtilityVentas.muestraProductoCongelado(myJTable,indProdCong,lblProdCong);
        UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
        UtilityVentas.muestraPuntosRentables(myJTable,COL_COD,lblPuntosRentables);
        colocaTotalesPedido();


    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        
        //dubilluz 31.08.2016
        if(VariablesVentas.vNumPedido_Receta.trim().length()>0)
            cargaIconoReceta();
        //dubilluz 31.08.2016
        
        VariablesVentas.vValorMultiplicacion = "1"; //ASOSA - 12/08/2014

        // DUBILLUZ 04.02.2013
        FarmaConnection.closeConnection();
        DlgProcesar.setVersion();

        if (VariablesFidelizacion.vSIN_COMISION_X_DNI)
            lblDNI_SIN_COMISION.setVisible(true);
        else
            lblDNI_SIN_COMISION.setVisible(false);

        FarmaUtility.centrarVentana(this);
        vEjecutaAccionTeclaListado = false;
        VariablesVentas.vVentanaListadoProductos = true;

        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
            //ImageIcon icon = new ImageIcon(this.getClass().getResource("logo_mf_btl.JPG"));
            this.setTitle("Lista de Productos y Precios " + VariablesConvenioBTLMF.vNomConvenio);
            log.debug("Nombre Cliente::" + VariablesConvenioBTLMF.vNomCliente);
            lblCliente.setText("" + VariablesConvenioBTLMF.vNomCliente);
            //lblMedicoT.setIcon(icon);
            //lblMedico.setText(" "+VariablesConvenioBTLMF.vNomConvenio );
            
            //INI JMONZALVE 24042019
            try {
                if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio) &&
                    DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                    String msjPto = "";
                    if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && 
                       VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!=null){
                        msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
                    }
                    lblCliente.setText(msjPto + VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente +
                                       " " + VariablesFidelizacion.vApeMatCliente);
                }
            } catch (SQLException ex) {
                log.error("Error al verificar activacion de convenio Cobro por Responsabilidad: " + ex.getMessage());
            }
            //FIN JMONZALVE 24042019
        } else {
            evaluaTitulo();
        }
        //evaluaSeleccionMedico();
        lblProdIgv.setVisible(false);
        FarmaUtility.moveFocus(txtProducto);

        if (VariablesVentas.vArrayList_PedidoVenta.size() == 0)
            VariablesVentas.vIndPedConProdVirtual = false;
        
        
        /**
         * NO TOCAR , REVISAR CON EL ENCARGADO ES DELICADO DEBIDO AL LLAMADO DEL RESUMEN DE PEDIDO
         */




        if (VariablesVentas.vKeyPress != null) {
            if (VariablesVentas.vCodBarra.trim().length() > 0) {
                txtProducto.setText(VariablesVentas.vCodBarra.trim());
                txtProducto_keyPressed(VariablesVentas.vKeyPress);
            } else if (VariablesVentas.vCodProdBusq.trim().length() > 0) {
                txtProducto.setText(VariablesVentas.vCodProdBusq.trim());
                txtProducto_keyPressed(VariablesVentas.vKeyPress);
            }
            else if(VariablesVentas.vTmpNumReceta.trim().length() > 0){
                            txtProducto.setText(VariablesVentas.vTmpNumReceta.trim());
                            txtProducto_keyPressed(VariablesVentas.vKeyPress);
            }
            else {
                txtProducto.setText(VariablesVentas.vKeyPress.getKeyChar() + "");
                txtProducto_keyReleased(VariablesVentas.vKeyPress);
            }
        }

        //JCORTEZ 17.04.08
        if (!VariablesVentas.vCodFiltro.equalsIgnoreCase("")) {
            cargaListaFiltro();
        }
        AuxiliarFidelizacion.setMensajeDNIFidelizado(lblMensajeCampa�a, "L", txtProducto, this);
        
            //ERIOS 18.04.2016 Muestra puntos rentables
            lblPuntosRentables.setVisible(false);
            if(!VariablesReporte.indVerTipoComision.equals("A")
                && VariablesReporte.indVerRentables.equals("A")){
                lblPuntosRentables.setVisible(true);
                lblIndTipoProd.setVisible(false);
            }
        if(isListadoReceta()){
            lblF6.setVisible(true);
            lblEnter.setVisible(true);
            lblF7.setVisible(true);
            lblF1.setVisible(true);
            
            lblF2.setVisible(false);
            lblF11.setVisible(false);
            lblEsc.setVisible(false);
            lblF9.setVisible(false);
            lblF10.setVisible(false);
            lblF8.setVisible(false);
            lblF12.setVisible(false);
            lblF13.setVisible(false);
            lblF5.setVisible(false);
            lblF4.setVisible(false);
            lblDeliveryProv.setVisible(false);
        }
        if(isListadoReceta()){
            deshabilitarLabelReceta();
        }
        String valorHabilitado = "";
        try {
            valorHabilitado = DBVendedor.verificaEstadoReporteMetas();
        } catch (SQLException f) {
            System.out.println("Error: "+f.getMessage());
        }
        if(valorHabilitado.equals("A")){
            if(VariablesVentas.verMetasVendedor){
                DlgMetasVendedor dlgMetas = new DlgMetasVendedor(myParentFrame, "", true);
                dlgMetas.setVisible(true);
            }
        }
        if(UtilityInventario.is_Migra_Sap()){
            lblF10.setVisible(false);
        }
        
        // dubilluz 30.07.2019
        if(cerrarVentana){
            cancelaOperacion();
            cerrarVentana(true);
        }
        /*** INICIO ARAVELLO 04/10/2019 ***/
        
        if(VariablesRefacturadorElectronico.vComprobanteActual != null && 
           VariablesRefacturadorElectronico.vComprobanteActual.isMotivoConvenio()){
            KeyEvent vKeyEventPressedF3 = new KeyEvent(
                                            txtProducto,
                                            KeyEvent.KEY_PRESSED,
                                            System.currentTimeMillis(),
                                            0,
                                            KeyEvent.VK_F3,
                                            '\uffff');
            KeyEvent vKeyEventReleasedF3 = new KeyEvent(
                                            txtProducto,
                                            KeyEvent.KEY_RELEASED,
                                            System.currentTimeMillis(),
                                            0,
                                            KeyEvent.VK_F3,
                                            '\uffff');

            txtProducto_keyPressed(vKeyEventPressedF3);
            txtProducto_keyReleased(vKeyEventReleasedF3);
            
            KeyEvent vKeyEventPressedESC = new KeyEvent(
                                            txtProducto,
                                            KeyEvent.KEY_PRESSED,
                                            System.currentTimeMillis(),
                                            0,
                                            KeyEvent.VK_ESCAPE,
                                            '\u001b');
            KeyEvent vKeyEventTypedESC = new KeyEvent(
                                            txtProducto,
                                            KeyEvent.KEY_TYPED,
                                            System.currentTimeMillis(),
                                            0,
                                            0,
                                            '\u001b');
            KeyEvent vKeyEventReleasedESC = new KeyEvent(
                                            txtProducto,
                                            KeyEvent.KEY_RELEASED,
                                            System.currentTimeMillis(),
                                            0,
                                            KeyEvent.VK_ESCAPE,
                                            '\u001b');
            txtProducto_keyPressed(vKeyEventPressedESC);
            txtProducto_keyTyped(vKeyEventTypedESC);
            txtProducto_keyReleased(vKeyEventReleasedESC);
        }        
        /*** FIN    ARAVELLO 04/10/2019 ***/
        // dubilluz 30.07.2019        
    }

    // ************************************************************************************************************************************************
    // Marco Fajardo: Cambio realizado el 21/04/09 - Evitar le ejecuci�n de 2 teclas a la vez al momento de comprometer stock
    // ************************************************************************************************************************************************
    /**
     * NUEVO METODO KEYPRESSED PARA PROGRAMA DE PUNTOS999
     * @param e
     */
    private void txtProducto_keyPressed(KeyEvent e) {
        if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE ||
              e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT ||
              e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_HOME)) {
            e.consume();

        }
        if(isListadoReceta()){
		isCodigoBarra = false;
            log.info("FLUJO NORMAL");
            txtProducto_keyPressed2(e);
        }else{
            if(isNumero(e.getKeyChar()) || e.getKeyCode() == KeyEvent.VK_ENTER ){
                String palabraClave = txtProducto.getText().trim();
                String indActivoTeleton = UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_IND_TELETON).trim();
                String indActivoCruzRoja = UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_IND_CRUZ_ROJA).trim();
                String palabraTeleton = UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_IND_PALABRA_CLAVE_TELETON).trim();
                String palabraCruzRoja = UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_IND_PALABRA_CLAVE_CRUZ_ROJA).trim();
                
                if ((palabraClave.equalsIgnoreCase(palabraTeleton) && indActivoTeleton.equals("S")) ||
                    (palabraClave.equalsIgnoreCase(palabraCruzRoja) && indActivoCruzRoja.equals("S"))) {
                    String codMarca = UtilityVentas.obtenerCodMarcaLocal();
                    if(codMarca.equals("")) {
                        txtProducto.setText("");
                        FarmaUtility.showMessage(this, 
                                                 "Error al obtener marca, comun�quese con mesa de ayuda", 
                                                 txtProducto);
                    } else {
                        if (codMarca.equalsIgnoreCase(ConstantsVentas.COD_MARCA_MF) /*|| //ASOSA - 07/09/2017 - TELEROJA.CAMBIO DE ULTIMO MINUTO, SOLO LETREROS MIFARMA VAN A RECAUDAR
                            codMarca.equalsIgnoreCase(ConstantsVentas.COD_MARCA_FASA) ||
                            codMarca.equalsIgnoreCase(ConstantsVentas.COD_MARCA_BTL) ||
                            codMarca.equalsIgnoreCase(ConstantsVentas.COD_MARCA_ARCANGEL)*/) { //AOVIEDO 07/04/17
                            
                            String tipoRecau = "";
                            double minRecau = 0.0;
                            double maxRecau = 0.0;                            
                            
                            if (palabraClave.equalsIgnoreCase(palabraTeleton) && indActivoTeleton.equals("S")) {
                                tipoRecau = ConstantsRecaudacion.TIPO_REC_TELETON;
                                minRecau = FarmaUtility.getDecimalNumber(UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_MIN_REC_TELETON));
                                minRecau = FarmaUtility.getDecimalNumber(UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_MAX_REC_TELETON));
                            } else if (palabraClave.equalsIgnoreCase(palabraCruzRoja) && indActivoCruzRoja.equals("S")) {
                                tipoRecau = ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA;
                                minRecau = FarmaUtility.getDecimalNumber(UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_MIN_REC_CRUZ_ROJA));
                                minRecau = FarmaUtility.getDecimalNumber(UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_MAX_REC_CRUZ_ROJA));
                            }
                            
                            DlgRecaudacionTeleton dlgRecaudacionTeleton = new DlgRecaudacionTeleton(myParentFrame, "", true);
                            dlgRecaudacionTeleton.setTipoRecau(tipoRecau);
                            dlgRecaudacionTeleton.setMinRecau(minRecau);
                            dlgRecaudacionTeleton.setMaxRecau(maxRecau);
                            dlgRecaudacionTeleton.setVisible(true);
                            txtProducto.setText("");
                            FarmaUtility.moveFocus(txtProducto);
                        }                    
                    }                
                }            
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
                    // JVARA LLEVA AL FLUJO DEL RAC SI EL PEDIDO NO ESTA EN EL LOCAL
                    tiempoTeclaFinal = System.currentTimeMillis();
                    //ERIOS 03.07.2013 Limpia la caja de texto
                    limpiaCadenaAlfanumerica(txtProducto);
                    txtProducto.setText(txtProducto.getText().toUpperCase());
                    if(isNumeroReceta(txtProducto.getText())){
                        carga_pedido_receta(txtProducto.getText());
                    }
                    else
                    // KMONCADA 01.07.2015 PUNTOS APLICA PARA CONVENIOS
                    if (!UtilityPuntos.isActivoFuncionalidad()) {
                        isLectoraLazer = false;
                        tiempoTeclaInicial = 0;
                        log.info("FUNCIONALIDAD DE PUNTOS NO ACTIVA");
                        isCodigoBarra = false;
                        txtProducto_keyPressed2(e);
                    }else{
                        int maxTiempoLectora = UtilityPuntos.obtieneTiempoMaximoLectora();
                        isLectoraLazer = false;
                        
                        log.info("FUNCIONALIDAD DE PUNTOS ACTIVA");
                        
                        log.info("Tiem 2 " + (tiempoTeclaInicial));
                        log.info("Tiem 1 " + (tiempoTeclaFinal));
                        log.info("Tiempo de ingreso y ENTER " + (tiempoTeclaFinal - tiempoTeclaInicial));
                        boolean validaFinal = true;
                        for(int k=0;k<txtProducto.getText().toCharArray().length;k++){
                            validaFinal = validaFinal && isNumero(txtProducto.getText().toCharArray()[k]);
                        }
                        if ((tiempoTeclaFinal - tiempoTeclaInicial) <= maxTiempoLectora && txtProducto.getText().length() > 0 && validaFinal) {
                            isLectoraLazer = true;
                            tiempoTeclaInicial = 0;
                            log.info("ES CODIGO DE BARRA");
                            isCodigoBarra = true;
                            isEnter = true;
                            
                            
                            BeanTarjeta tarjetaPuntosOld = null;
                            if(VariablesPuntos.frmPuntos != null && UtilityPuntos.isTarjetaValida(txtProducto.getText().trim())){
                                //if(VariablesFidelizacion.clienteFidelizado != null){
                                if(VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                                    //if(JConfirmDialog.rptaConfirmDialog(this, "Programa Monedero:\nYa registro un cliente, �desea cambiarlo?")){
                                    if(JConfirmDialog.rptaConfirmDialog(this, UtilityFidelizacion.obtenerMensajeReemplazoClientePedido())){
                                        tarjetaPuntosOld = VariablesPuntos.frmPuntos.getBeanTarjeta();
                                        //tarjetaPuntosOld = VariablesFidelizacion.clienteFidelizado.getTarjetaMonedero();
                                        VariablesFidelizacion.vNumTarjeta = "";
                                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                        VariablesFidelizacion.limpiaVariables();
                                    }else{
                                        return;
                                    }
                                }else{//[Desarrollo5] 20.10.2015 se quitan los valores de fidelizacion para que sean cargados nuevamente
                                    /*VariablesFidelizacion.vNumTarjeta = "";
                                    VariablesFidelizacion.limpiaVariables();
                                    VariablesPuntos.frmPuntos.eliminarTarjetaBean();*/
                                }
                                // KMONCADA 10.02.2015 INIT DE CONSULTA DE TARJETA DESLIZADA
                                int rsptaConsulta = UtilityPuntos.consultarTarjetaOrbis(myParentFrame, this, txtProducto, true, false);
                                
                                BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
                                if(tarjetaCliente!=null){
                                    //valida cliente como fidelizado
                                    boolean isClienteAfiliado = false;
                                    if(rsptaConsulta == ConstantsPuntos.TARJETA_NUEVA ||
                                       rsptaConsulta == ConstantsPuntos.TARJETA_OFFLINE){
                                        isClienteAfiliado = true;
                                    }
                                    accion_f12("N",isClienteAfiliado);
                                /**
                                 * LTAVARA 2016.11.21
                                 * Validar los productos del pedido, si participan en el programa x+1 :
                                 * Cliente monedero: muestra solicitud de afiliacion al programa por producto. Si es tranferencia entre tarjetas solicita ingresar las cantidades de los productos x+1
                                 * Cliente no monedero : muestra mensaje en que programa participa el producto
                                 * */
                                dlgResumenPedido.validarProductosXmas1(tarjetaPuntosOld);
                              /*  if(tarjetaPuntosOld != null){
                                    UtilityPuntos.transferirInscripcionBonificados(myParentFrame, this, tarjetaPuntosOld, pIngresoComprobanteManual);
                                        //KMONCADA 13.08.2015 QUITARA LOS PACKS COMO FIDELIZADOS EN CASO NO EXISTA CLIENTE FIDELIZADO
                                        if(UtilityPuntos.quitarPacksFidelizados()){
                                            cerrarVentana(true);
                                        }
                                }*/
                                    txtProducto.setText("");
                                    return ;
                                }else{
                                    evaluaTitulo();
                                    txtProducto_keyPressed2(e);
                                    
                                }
                            }else{
                                if(VariablesPuntos.frmPuntos == null){
                                    FarmaUtility.showMessage(this, "Programa de Lealtad:\nError al inicializar variables.", txtProducto);
                                }
                                if(!UtilityPuntos.isTarjetaValida(txtProducto.getText().trim())){
                                    log.info("FORMATO DE TARJETA INVALIDO "+txtProducto.getText().trim());
                                    txtProducto_keyPressed2(e);
                                }
                            }    
                            /*}else{
                                log.info("FUNCIONALIDAD DE PUNTOS NO ACTIVA");
                                txtProducto_keyPressed2(e);
                            }*/
                            
                        } else {
                            isLectoraLazer = false;
                            tiempoTeclaInicial = 0;
                            log.info("FLUJO NORMAL");
                            isCodigoBarra = false;
                            if(UtilityPuntos.isActivoFuncionalidad()){
                                try{
                                    //String aux = DBPuntos.evaluaTarjeta(txtProducto.getText().trim());
                                    //if("S".equalsIgnoreCase(aux)){
                                    if(UtilityPuntos.isTarjetaOtroPrograma(txtProducto.getText().trim(), true)){
                                        FarmaUtility.showMessage(this, "Debe deslizar la tarjeta para acceder a los descuentos.", txtProducto);
                                        txtProducto.setText("");
                                    }else{
                                        txtProducto_keyPressed2(e);
                                    }
                                }catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            }else{
                                txtProducto_keyPressed2(e);
                            }
                        }
                        //FarmaUtility.moveFocus(txtTarjeta);
                    }
                }
            }else{
                isCodigoBarra = false;
                log.info("FLUJO NORMAL");
                txtProducto_keyPressed2(e);
              }
            }
    
    }
   
    /**
     * NUEVO METODO KEYPRESSED PARA PROGRAMA DE PUNTOS
     * @param e
     */
    private void txtProducto_keyPressed_new(KeyEvent e) {
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
                if (!UtilityPuntos.isActivoFuncionalidad()) {
                    isLectoraLazer = false;
                    tiempoTeclaInicial = 0;
                    log.info("FUNCIONALIDAD DE PUNTOS NO ACTIVA");
                    isCodigoBarra = false;
                    txtProducto_keyPressed2(e);
                }else{
                    int maxTiempoLectora = UtilityPuntos.obtieneTiempoMaximoLectora();
                    isLectoraLazer = false;
                    
                    log.info("FUNCIONALIDAD DE PUNTOS ACTIVA");
                    
                    log.info("Tiem 2 " + (tiempoTeclaInicial));
                    log.info("Tiem 1 " + (tiempoTeclaFinal));
                    log.info("Tiempo de ingreso y ENTER " + (tiempoTeclaFinal - tiempoTeclaInicial));
                    //ERIOS 03.07.2013 Limpia la caja de texto
                    limpiaCadenaAlfanumerica(txtProducto);
                    boolean validaFinal = true;
                    for(int k=0;k<txtProducto.getText().toCharArray().length;k++){
                        validaFinal = validaFinal && isNumero(txtProducto.getText().toCharArray()[k]);
                    }
                    if ((tiempoTeclaFinal - tiempoTeclaInicial) <= maxTiempoLectora && txtProducto.getText().length() > 0 && validaFinal) {
                        isLectoraLazer = true;
                        tiempoTeclaInicial = 0;
                        log.info("ES CODIGO DE BARRA");
                        isCodigoBarra = true;
                        isEnter = true;
                        
                        //if(UtilityPuntos.isActivoFuncionalidad()){
                        
                        BeanTarjetaMonedero tarjetaPuntosOld = null;
                        if(VariablesPuntos.frmPuntos != null && UtilityPuntos.isTarjetaValida(txtProducto.getText().trim())){
                            if(VariablesFidelizacion.clienteFidelizado != null){
                                if(JConfirmDialog.rptaConfirmDialog(this, UtilityFidelizacion.obtenerMensajeReemplazoClientePedido())){
                                    tarjetaPuntosOld = VariablesFidelizacion.clienteFidelizado.getTarjetaMonedero();
                                    VariablesFidelizacion.limpiaVariables();
                                }else{
                                    return;
                                }
                            }
                            // KMONCADA 10.02.2015 INIT DE CONSULTA DE TARJETA DESLIZADA
                            int rsptaConsulta = UtilityPuntos.consultarTarjetaOrbis(myParentFrame, this, txtProducto, true, false);
                            
                            BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
                            if(tarjetaCliente!=null){
                                //valida cliente como fidelizado
                                boolean isClienteAfiliado = false;
                                if(rsptaConsulta == ConstantsPuntos.TARJETA_NUEVA ||
                                   rsptaConsulta == ConstantsPuntos.TARJETA_OFFLINE){
                                    isClienteAfiliado = true;
                                }
                                accion_f12("N",isClienteAfiliado);
                                if(tarjetaPuntosOld != null){
                                    UtilityPuntos.transferirInscripcionBonificados(myParentFrame, this, tarjetaPuntosOld, pIngresoComprobanteManual);
                                    //KMONCADA 13.08.2015 QUITARA LOS PACKS COMO FIDELIZADOS EN CASO NO EXISTA CLIENTE FIDELIZADO
                                    if(UtilityPuntos.quitarPacksFidelizados()){
                                        cerrarVentana(true);
                                    }
                                }
                                txtProducto.setText("");
                                return ;
                            }else{
                                evaluaTitulo();
                                txtProducto_keyPressed2(e);
                                
                            }
                        }else{
                            //Inicio [Desarrollo5] 20.10.2015
                            /*VariablesFidelizacion.vNumTarjeta = "";
                            VariablesFidelizacion.limpiaVariables();
                            VariablesPuntos.frmPuntos.eliminarTarjetaBean();*/
                            //Fin [Desarrollo5] 20.10.2015
                            if(VariablesPuntos.frmPuntos == null){
                                FarmaUtility.showMessage(this, "Programa de Lealtad:\nError al inicializar variables.", txtProducto);
                            }
                            if(!UtilityPuntos.isTarjetaValida(txtProducto.getText().trim())){
                                log.info("FORMATO DE TARJETA INVALIDO "+txtProducto.getText().trim());
                                txtProducto_keyPressed2(e);
                            }
                        }    
                        /*}else{
                            log.info("FUNCIONALIDAD DE PUNTOS NO ACTIVA");
                            txtProducto_keyPressed2(e);
                        }*/
                        
                    } else {
                        isLectoraLazer = false;
                        tiempoTeclaInicial = 0;
                        log.info("FLUJO NORMAL");
                        isCodigoBarra = false;
                        if(UtilityPuntos.isActivoFuncionalidad()){
                            try{
                                //String aux = DBPuntos.evaluaTarjeta(txtProducto.getText().trim());
                                //if("S".equalsIgnoreCase(aux)){
                                if(UtilityPuntos.isTarjetaOtroPrograma(txtProducto.getText().trim(), true)){
                                    FarmaUtility.showMessage(this, "Debe deslizar la tarjeta para acceder a los descuentos.", txtProducto);
                                    txtProducto.setText("");
                                }else{
                                    txtProducto_keyPressed2(e);
                                }
                            }catch(Exception ex){
    
                            }
                        }else{
                            txtProducto_keyPressed2(e);
                        }
                    }
                    //FarmaUtility.moveFocus(txtTarjeta);
                }
            }
        }else{
            isCodigoBarra = false;
            log.info("FLUJO NORMAL");
            txtProducto_keyPressed2(e);
        }
    }    
    public void cargaTarjFidelizacion(String cadena){
        boolean pPermite = false;
        BeanTarjeta tarjetaPuntosOld = null;
        if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
            if (JConfirmDialog.rptaConfirmDialog(this,
                                                 "Programa club de descuentos:\nYa registro una tarjeta, �desea cambiarlo?")) {
                
                if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                    tarjetaPuntosOld = VariablesPuntos.frmPuntos.getBeanTarjeta();
                    VariablesFidelizacion.vNumTarjeta = "";
                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                }
                VariablesFidelizacion.limpiaVariables();
                pPermite = true;
            } else
                pPermite = false;
        } else
            pPermite = true;


        if (pPermite) {
            //validarClienteTarjeta(cadena, true);
            validarClienteTarjeta(cadena, false);
            //VariablesFidelizacion.vNumTarjeta = cadena.trim();
            if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0)
                UtilityFidelizacion.operaCampa�asFidelizacion(cadena, VariablesConvenioBTLMF.vCodConvenio);

            //DAUBILLUZ -- Filtra los DNI anulados
            //25.05.2009
            VariablesFidelizacion.vDNI_Anulado = UtilityFidelizacion.isDniValido(VariablesFidelizacion.vDniCliente);
            VariablesFidelizacion.vAhorroDNI_x_Periodo =
                    UtilityFidelizacion.getAhorroDNIxPeriodoActual(VariablesFidelizacion.vDniCliente,
                                                                   VariablesFidelizacion.vNumTarjeta);
            VariablesFidelizacion.vMaximoAhorroDNIxPeriodo =
                    UtilityFidelizacion.getMaximoAhorroDnixPeriodo(VariablesFidelizacion.vDniCliente,
                                                                   VariablesFidelizacion.vNumTarjeta);

            AuxiliarFidelizacion.setMensajeDNIFidelizado(lblMensajeCampa�a, "L", txtProducto, this);
            if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
                if(tarjetaCliente!=null){
                    //valida cliente como fidelizado
                    if(tarjetaPuntosOld != null){
                        UtilityPuntos.transferirInscripcionBonificados(myParentFrame, this, tarjetaPuntosOld, pIngresoComprobanteManual);
                    }
                }
            }
        }
    }
    
    private void txtProducto_keyPressed2(KeyEvent e) {
        //ERIOS 15.01.2014 Correccion para lectura de escaner NCR
        //log.debug("Tecla: "+e.getKeyCode()+" ("+e.getKeyChar()+")");
        if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE ||
              e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT ||
              e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_HOME)) {
            e.consume();
        }

        try {
            FarmaGridUtils.aceptarTeclaPresionada(e, myJTable, txtProducto, 2);
            //log.debug("Caracter: "+String.valueOf(e.getKeyChar())+"   ASCII: "+String.valueOf(e.getKeyCode()));

            if (!vEjecutaAccionTeclaListado) {
                vEjecutaAccionTeclaListado = true;
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    VariablesVentas.vCostoICBPER = "";
                    VariablesVentas.vTotalICBPER = "";
                    deshabilitaProducto();
                    String vCadenaOriginal = txtProducto.getText().trim();
                    log.debug("!!!!!!!!!!!!!Cadena Original:" + vCadenaOriginal);
                    //ERIOS 03.07.2013 Limpia la caja de texto
                    limpiaCadenaAlfanumerica(txtProducto);

                    //Agregado por FRAMIREZ 24/11/2011
                    //Si es la tarjeta de un cliente, va al modulo de convenio.
                    if (!isListadoReceta() && 
                        (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                        UtilityConvenioBTLMF.esTarjetaConvenio(txtProducto.getText()))){

                        if (VariablesConvenioBTLMF.vCodConvenio != null &&
                            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1) {
                            if (JConfirmDialog.rptaConfirmDialog(this,
                                                                 "�Esta seguro de cancelar el pedido por Convenio?")) {
                                VariablesConvenioBTLMF.limpiaVariablesBTLMF();
                                if (VariablesVentas.vArrayList_PedidoVenta.size() > 0) {
                                    FarmaUtility.showMessage(this,
                                                             "Existen Productos Seleccionados. Para realizar un Pedido Mostrador\n" +
                                            "no deben haber productos seleccionados. Verifique!!!", txtProducto);
                                } else {
                                    lblCliente.setText("");
                                    lblMedico.setText("");
                                    this.setTitle("Lista de Productos y Precios /  IP : " + FarmaVariables.vIpPc);
                                    evaluaTitulo();
                                    //jquispe 25.07.2011 se agrego la funcionalidad de listar las campa�as sin fidelizar
                                    UtilityFidelizacion.operaCampa�asFidelizacion(" ", VariablesConvenioBTLMF.vCodConvenio);
                                }
                            }
                        } else {
                            if (VariablesVentas.vArrayList_PedidoVenta.size() > 0) {
                                FarmaUtility.showMessage(this,
                                                         "Existen Productos Seleccionados. Para iniciar un pedido convenio\n" +
                                        "no deben haber productos seleccionados. Verifique!!!", txtProducto);
                            } else {
                                DlgListaConveniosBTLMF convenio = new DlgListaConveniosBTLMF(myParentFrame);
//                                convenio.irIngresoDatosConvenio2(txtProducto);

                                if (VariablesConvenioBTLMF.vAceptar) {
                                    VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF = true;

                                    //ImageIcon icon = new ImageIcon(this.getClass().getResource("logo_mf_btl.JPG"));
                                    UtilityConvenioBTLMF.cargarVariablesConvenio(VariablesConvenioBTLMF.vCodConvenio,
                                                                                 this, myParentFrame);

                                    log.debug("VariablesConvenioBTLMF.vCodConvenio:" +
                                              VariablesConvenioBTLMF.vCodConvenio);
                                    log.debug("VariablesConvenioBTLMF.vNomConvenio:" +
                                              VariablesConvenioBTLMF.vNomConvenio);

                                    evaluaTitulo();
                                    txtProducto.setText("");
                                    FarmaGridUtils.showCell(tblProductos, 0, 0);
                                    FarmaUtility.setearActualRegistro(tblProductos, txtProducto, 2);
                                    FarmaUtility.moveFocus(txtProducto);
                                } else {
                                    evaluaTitulo();
                                    VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF = false;
                                }
                            }
                        }
                    } else {
                        log.info("Paso Tarjeta : " + pasoTarjeta);
                        if (pasoTarjeta) {
                            txtProducto.setText("");
                            pasoTarjeta = false;
                            return;
                        }

                        //dubilluz 21.07.2011
                        setFormatoTarjetaCredito(txtProducto.getText().trim());
                        log.info("pasoTarjeta:" + pasoTarjeta);

                        try {
                            //mfajardo 29/04/09 validar ingreso de productos virtuales
                            if (!VariablesVentas.vProductoVirtual) {
                                e.consume();
                                if (myJTable.getSelectedRow() >= 0) {
                                    String productoBuscar = vCadenaOriginal;
                                    //txtProducto.getText().trim().toUpperCase();
                                    String cadena = txtProducto.getText().trim();

                                    //AGREGADO POR DVELIZ 26.09.08
                                    //Cambiar en el futuro esto por una consulta a base de datos.
                                    String formato = "";
                                    if (cadena.trim().length() > 6)
                                        formato = cadena.substring(0, 5);
                                    /* if (formato.equals("99999")) {
                                        if (VariablesVentas.vEsPedidoConvenio ||
                                            (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {

                                            FarmaUtility.showMessage(this, "No puede agregar una tarjeta a un " + "pedido por convenio.", txtProducto);
                                            return;
                                        }
                                        if (UtilityFidelizacion.EsTarjetaFidelizacion(cadena)) {
                                            //dubilluz 22.04.2015
                                            cargaTarjFidelizacion(cadena);                                        
                                            //vEjecutaAccionTeclaListado = false;
                                            return;
                                        } else {
                                            FarmaUtility.showMessage(this, "La tarjeta no es valida", null);
                                            txtProducto.setText("");
                                            FarmaUtility.moveFocus(txtProducto);
                                            //vEjecutaAccionTeclaListado = false;
                                            return;
                                        }
                                    } */
                                    if(!isListadoReceta()){
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
                                                        FarmaUtility.showMessage(this,DBPuntos.getMensajeSinTarjeta(),txtProducto);
                                                        txtProducto.setText("");
                                                        return;
                                                    }
                                                    //txtProducto.setText("");
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
            
                                                        FarmaUtility.showMessage(this, "No puede agregar una tarjeta a un " + "pedido por convenio.", txtProducto);
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
    
    
                                        if (UtilityVentas.esCupon(cadena, this, txtProducto)) {
                                            //agregarCupon(cadena);//metodo reemplazado por lo nuevo
                                            //ERIOS 2.3.2 Valida convenio BTLMF
                                            /*if (VariablesVentas.vEsPedidoConvenio ||
                                                (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                                                 VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF)) {
                                                FarmaUtility.showMessage(this,
                                                                         "No puede agregar cupones a un pedido por convenio.",
                                                                         null);
                                                return;
                                            }*/
                                            validarAgregarCupon(cadena);
    
                                            //vEjecutaAccionTeclaListado = false;
    
                                            log.info("es CUpon :");
                                            return;
                                        }
                                        //21.08.2008 Daniel Veliz
                                        VariablesCampana.vFlag = false;
    
                                        //Dubilluz saber si ingreso una tarjeta y esta en una campa�a automatica
                                        //para q aparezca la pantalla de fidelizacion
                                        //inicio dubilluz 15.07.2011
                                        
                                        if (pIsTarjetaEnCampana) {
                                            if (VariablesVentas.vEsPedidoConvenio ||
                                                (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {
                                                FarmaUtility.showMessage(this, "No puede agregar una tarjeta en campa�a a un pedido por convenio.", txtProducto);
                                                txtProducto.setText("");
                                                return;
                                            }
                                            // VALIDAR
                                            if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                                                log.info("se limpia la tarjeta puntos");
                                                VariablesFidelizacion.limpiaVariables();
                                                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                            }
                                            log.info("es una tarjeta de pago q esta en una campana automatica:");
                                            validaIngresoTarjetaPagoCampanaAutomatica(cadena.trim());
                                            return;
                                        }
                                    //fin dubilluz 15.07.2011
                                    }
                                    if (productoBuscar.length() == 0) {
                                        //vEjecutaAccionTeclaListado = false;
                                        return;
                                    }

                                    String codigo = "";
                                    // revisando codigo de barra

                                    char primerkeyChar = cadena.charAt(0);
                                    char segundokeyChar;

                                    if (cadena.length() > 1)
                                        segundokeyChar = cadena.charAt(1);
                                    else
                                        segundokeyChar = primerkeyChar;

                                    char ultimokeyChar = cadena.charAt(cadena.length() - 1);
                                    log.info("productoBuscar:" + cadena);
                                    if (cadena.length() > 6 &&
                                        (!Character.isLetter(primerkeyChar) && (!Character.isLetter(segundokeyChar) &&
                                                                                (!Character.isLetter(ultimokeyChar))))) {
                                        VariablesVentas.vCodigoBarra = cadena;

                                        log.info("consulta cod barra antes");
                                        productoBuscar = DBVentas.obtieneCodigoProductoBarra();

                                        log.info("consulta cod barra despues");
                                    }


                                    log.info("productoBuscar new:" + productoBuscar);
                                    //JCORTEZ 23.07.2008
                                    ///if (productoBuscar.equalsIgnoreCase("000000")&&UtilityVentas.esCupon(productoBuscar,this,txtProducto)) {
                                    if (productoBuscar.equalsIgnoreCase("000000")) {
                                        
                                            FarmaUtility.showMessage(this,
                                                                 "No existe producto relacionado con el Codigo de Barra. Verifique!!!",
                                                                 txtProducto);
                                            //KMONCADA 05.08.2015 SE LIMPIA EL TEXTO INGRESADO
                                            txtProducto.setText("");
                                            
                                        //vEjecutaAccionTeclaListado = false;
                                        return;
                                        
                                    }

                                    for (int k = 0; k < myJTable.getRowCount(); k++) {
                                        codigo = ((String)myJTable.getValueAt(k, COL_COD)).trim();
                                        if (codigo.equalsIgnoreCase(productoBuscar)) {
                                            FarmaGridUtils.showCell(myJTable, k, 0);
                                            //vEjecutaAccionTeclaListado = false;
                                            break;
                                        }
                                    }

                                    int vFila = myJTable.getSelectedRow();
                                    String actualCodigo = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
                                    String actualProducto =
                                        ((String)(myJTable.getValueAt(myJTable.getSelectedRow(), 2))).trim().toUpperCase();
                                    // Asumimos que codigo de producto ni codigo de barra empiezan con letra
                                    //if (Character.isLetter(primerkeyChar))
                                    /*{
                                    txtProducto.setText(actualProducto);
                                    productoBuscar = actualProducto;
                                }
                                txtProducto.setText(txtProducto.getText().trim());*/


                                    // Comparando codigo y descripcion de la fila actual con el txtProducto
                                    if ((actualCodigo.equalsIgnoreCase(productoBuscar) ||
                                         actualProducto.substring(0, productoBuscar.length()).equalsIgnoreCase(productoBuscar))) {
                                        //aqui

                                        btnBuscar.doClick();
                                        txtProducto.setText(actualProducto.trim());
                                        txtProducto.selectAll();
                                    } else if (productoBuscar.trim().length() <= DIG_PROD &&
                                               UtilityVentas.esCupon(productoBuscar, this, txtProducto)) {
                                        log.debug("productoBuscar.trim().length() " + productoBuscar.trim().length());

                                        FarmaUtility.showMessage(this,
                                                                 "Producto No Encontrado seg�n Criterio de B�squeda !!!",
                                                                 txtProducto);
                                        //KMONCADA 05.08.2015 SE LIMPIA EL TEXTO INGRESADO
                                        txtProducto.setText("");
                                        //vEjecutaAccionTeclaListado = false;
                                        return;
                                    }

                                }
                            } // producto virtual
                            else {
                                FarmaUtility.showMessage(this, "Ya se selecciono un producto virtual", txtProducto);
                            } // producto virtual
                        } catch (SQLException sql) {
                            //log.error("",sql);
                            //vEjecutaAccionTeclaListado = false;
                            log.error(null, sql);
                            FarmaUtility.showMessage(this, "Error al buscar el Producto.\n" +
                                    sql, txtProducto);
                        }
                    }
                    lstTiempoTecla = new ArrayList();
                    isEnter = true;
                } else {
                    
                    vEjecutaAccionTeclaListado = false;
                    chkKeyPressed(e);
                    // KMONCADA 06.09.2016 [CENTRO MEDICO]
                    if(!isListadoReceta()){
                        //kmoncada
                        // PERMITE RECONOCER LA COMBINACION DE TECLAS ALT + S o ALT + R o ALT + P
                        if (e.getKeyCode() == 18 && contarCombinacion == 0) {
                            contarCombinacion++;
                        } else {
                            if (contarCombinacion == 1) {
                                switch (e.getKeyCode()) {
                                case 83:
                                    if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS)) {
                                        btnProdAlternativos.doClick();
                                    }
                                    break;
                                case 82:
                                case 80:
                                    myJTable = tblProductos;
                                    txtProducto.setText("");
                                    FarmaUtility.setearActualRegistro(tblProductos, txtProducto, 2);
                                    muestraInfoProd();
                                    UtilityVentas.muestraProductoInafectoIgv(myJTable,11, lblProdIgv);                                
                                    UtilityVentas.muestraProductoPromocion(myJTable,17, lblProdProm,1);
                                    UtilityVentas.muestraProductoRefrigerado(myJTable,15, lblProdRefrig);
                                    UtilityVentas.muestraIndTipoProd(myJTable,16, lblIndTipoProd);                                
                                    UtilityVentas.muestraProductoCongelado(myJTable,indProdCong,lblProdCong);
                                    UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
                                    UtilityVentas.muestraPuntosRentables(myJTable,COL_COD,lblPuntosRentables);
                                    break;
                                    //DUBILLUZ 30.06.2014
                                case KeyEvent.VK_D:
                                    lblPedDelivery();
                                    break;
                                case KeyEvent.VK_M:
                                    btnMetas.doClick();
                                    break;
                                case KeyEvent.VK_SPACE:
                                    abrePedidosDelivery();
                                    break;
    
    
                                }
                            }
                            contarCombinacion = 0;
                        }
                    }
                    
                }
            }
        } catch (Exception exc) {
            log.error("", exc);
            log.debug("catch" + vEjecutaAccionTeclaListado);
        } finally {
            vEjecutaAccionTeclaListado = false;
        }

        log.info("Fin Enter:" + pasoTarjeta);
    }

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
    /**
     * @Author    Daniel Fernando Veliz La Rosa
     * @Since     15.08.08
     */
    /* private void ingresarDatosCampana(){
      try{
          log.debug(VariablesCampana.vCodCampana);
          DlgListDatosCampana dlgListDatosCampana
                    = new DlgListDatosCampana(myParentFrame, "Datos Campa�a", true);
          //FarmaUtility.centrarVentana(dlgListDatosCampana);
          dlgListDatosCampana.setVisible(true);
          if(FarmaVariables.vAceptar)
          {
            FarmaVariables.vAceptar = false ;

          }
      }catch(Exception e){
          log.error("",e);;
      }
  }*/
    //JQUISPE  03.05.2010 Cambio para modificar la busqueda de productos en la lista.
    private void txtProducto_keyReleased(KeyEvent e) {
        if(!isCodigoBarra){
        if (tblProductos.getRowCount() >= 0 && tableModelListaPrecioProductos.getRowCount() > 0 &&
            e.getKeyChar() != '+') {
            if (FarmaGridUtils.buscarDescripcion(e, myJTable, txtProducto, 2) ||
                (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_PAGE_UP) ||
                (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) ||
                e.getKeyCode() == KeyEvent.VK_ENTER) {
                VariablesVentas.vCostoICBPER = "";
                VariablesVentas.vTotalICBPER = "";
                VariablesVentas.vPosNew = myJTable.getSelectedRow();
                        //tblProductos.getSelectedRow();
                if (VariablesVentas.vPosOld == 0 && VariablesVentas.vPosNew == 0) {
                    UpdateReleaseProd(e);
                    VariablesVentas.vPosOld = VariablesVentas.vPosNew;
                } else {
                    if (VariablesVentas.vPosOld != VariablesVentas.vPosNew) {
                        UpdateReleaseProd(e);
                        VariablesVentas.vPosOld = VariablesVentas.vPosNew;
                    }
                }
                /*muestraNombreLab(4, lblDescLab_Prod);
      muestraProductoInafectoIgv(11, lblProdIgv);
      muestraProductoRefrigerado(15,lblProdRefrig);
      muestraProductoPromocion(17,lblProdProm);
      muestraIndTipoProd(16,lblIndTipoProd);
      // JCORTEZ 08.04.2008
      muestraProductoEncarte(COL_IND_ENCARTE,lblProdEncarte);

      muestraInfoProd();
      muestraProductoCongelado(lblProdCong);
      if ( !(e.getKeyCode()==KeyEvent.VK_ESCAPE) ) {
        if ( myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS) ) {

          actualizaListaProductosAlternativos();
        }
      }
      colocaTotalesPedido();

      */
            }
        }


        ///---

        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {

        } else {
            if (myJTable.getSelectedRow() >= 0) {
                if (isExisteProdCampana(myJTable.getValueAt(myJTable.getSelectedRow(), COL_COD).toString().trim())) {
                    lblMensajeCampa�a.setVisible(true);
                } else {
                    // dubilluz 26.05.2009
                    AuxiliarFidelizacion.setMensajeDNIFidelizado(lblMensajeCampa�a, "L", txtProducto, this);
                    //lblMensajeCampa�a.setVisible(false);
                }
            }
        }
        contarCombinacion = 0; // kmoncada : contador de combinacion de teclas
        ///---

        //JCORTEZ 23.07.2008
        /*String cadena = txtProducto.getText().trim();
    if (e.getKeyCode() == KeyEvent.VK_ENTER)
    {
      if(UtilityVentas.esCupon(cadena,this,txtProducto))
      {
        agregarCupon(cadena);
      }
    }*/
        }
    }

    private void txtProducto_keyTyped(KeyEvent e) {
        //log.debug("e.getKeyCode() presionado:"+e.getKeyCode());
        //log.debug("e.getKeyChar() presionado:"+e.getKeyChar());
        //if (e.getKeyCode() == KeyEvent.VK_PLUS)
        if (e.getKeyChar() == '+') {
            e.consume();
            if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS)) {
                btnProdAlternativos.doClick();
            } else //if(myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS_ALTERNATIVOS)) {
            {
                //btnRelacionProductos.doClick();
                //setJTable(tblProductos);
                myJTable = tblProductos;
                txtProducto.setText("");
                /*if(pJTable.getRowCount() > 0){
            FarmaGridUtils.showCell(pJTable, 0, 0);
            FarmaUtility.setearActualRegistro(pJTable, txtProducto, 2);
            muestraInfoProd();
        }*/
                FarmaUtility.setearActualRegistro(tblProductos, txtProducto, 2);
                muestraInfoProd();
                //FarmaUtility.moveFocus(txtProducto);

                UtilityVentas.muestraProductoInafectoIgv(myJTable,11, lblProdIgv);                
                UtilityVentas.muestraProductoPromocion(myJTable,17, lblProdProm,1);
                UtilityVentas.muestraProductoRefrigerado(myJTable,15, lblProdRefrig);
                UtilityVentas.muestraIndTipoProd(myJTable,16, lblIndTipoProd);                
                UtilityVentas.muestraProductoCongelado(myJTable,indProdCong,lblProdCong);
                UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
                UtilityVentas.muestraPuntosRentables(myJTable,COL_COD,lblPuntosRentables);
            }
        } else if (e.getKeyChar() == '-' && !isListadoReceta()) {
            e.consume();
            String lblStock = lblStockAdic.getText().trim();

            if (!lblStock.equals("")) {
                int vFila = myJTable.getSelectedRow();
                Boolean valor = (Boolean)myJTable.getValueAt(vFila, 0);

                if (valor.booleanValue()) {
                    FarmaUtility.showMessage(this,
                                             "Para modificar la venta por tratamiento, debe deseleccionarlo primero.",
                                             txtProducto);
                } else {
                    int auxStk = FarmaUtility.trunc(FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_STOCK));
                    int auxStkFrac = FarmaUtility.trunc(lblStock);

                    if ((auxStk + auxStkFrac) > 0 ||
                        (UtilityVentas.validaStockDisponible(stkProd) && !VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S))) {
                        //if (validaVentaConMenos()) {
                        /*** INICIO ARAVELLO 01/10/2019 ***/
                        //mostrarTratamiento();
                        /*** FIN    ARAVELLO 01/10/2019 ***/
                        aceptaOperacion();
                        //}
                    }
                }
            }
        }
        //LLEIVA - 21-Nov-2013 No se permite el espacio al inicio de la busqueda
        else if (e.getKeyChar() == ' ') {
            if (txtProducto.getText().length() == 0)
                e.consume();
            txtProducto.setText(txtProducto.getText().trim());
        }
        contarCombinacion = 0; // kmoncada : contador de combinacion de teclas
    }

    private void btnProdAlternativos_actionPerformed(ActionEvent e) {
        setJTable(tblListaSustitutos);
        UtilityVentas.muestraProductoInafectoIgv(myJTable,11, lblProdIgv);
        UtilityVentas.muestraProductoPromocion(myJTable,17, lblProdProm,1);
        UtilityVentas.muestraProductoRefrigerado(myJTable,15, lblProdRefrig);
        UtilityVentas.muestraIndTipoProd(myJTable,16, lblIndTipoProd);
        UtilityVentas.muestraProductoCongelado(myJTable,indProdCong,lblProdCong);
        UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
        UtilityVentas.muestraPuntosRentables(myJTable,COL_COD,lblPuntosRentables);
    }

    private void btnRelacionProductos_actionPerformed(ActionEvent e) {
        setJTable(tblProductos);
        UtilityVentas.muestraProductoInafectoIgv(myJTable,11, lblProdIgv);
        UtilityVentas.muestraProductoPromocion(myJTable,17, lblProdProm,1);
        UtilityVentas.muestraProductoRefrigerado(myJTable,15, lblProdRefrig);
        UtilityVentas.muestraIndTipoProd(myJTable,16, lblIndTipoProd);
        UtilityVentas.muestraProductoCongelado(myJTable,indProdCong,lblProdCong);
        UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
        UtilityVentas.muestraPuntosRentables(myJTable,COL_COD,lblPuntosRentables);
    }

    private void btnProducto_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProducto);
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        verificaCheckJTable();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    // **************************************************************************
    // Marco Fajardo: Cambio realizado el 21/04/09 - evitar le ejecucion de 2 teclas a la vez
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        try {
            if (!vEjecutaAccionTeclaListado) {
                vEjecutaAccionTeclaListado = true;
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    VariablesVentas.vCostoICBPER = "";
                    VariablesVentas.vTotalICBPER = "";
                    e.consume();
                } else if (UtilityPtoVenta.verificaVK_F1(e)) {
                    vEjecutaAccionTeclaListado = false;
                    UtilityVentas.muestraDetalleProducto(myParentFrame,myJTable,COL_COD,2,4,isListadoReceta());
                } else if (UtilityPtoVenta.verificaVK_F2(e) && !isListadoReceta()) {
                    //INI ASOSA - 19/01/2014 - RIMAC
                    VariablesVentas.vCantMesRimac = 0;
                    VariablesVentas.vDniRimac = "";
                    //FIN ASOSA - 19/01/2014 - RIMAC
                    /*if(esProductoFarma())
          {
            FarmaUtility.showMessage(this, "Operaci�n no valida para productos del Tipo Farma", txtProducto);
            return;
          }
          muestraProductosComplementarios();*/
                    muestraProductosAlternativos();
                } else if (e.getKeyCode() == KeyEvent.VK_F6) {
                    cargaListaFiltro();
                } else if (e.getKeyCode() == KeyEvent.VK_F7) {
                    filtroGoogle();
                } else if (e.getKeyCode() == KeyEvent.VK_F8 && !isListadoReceta()) {
                    //ingresaReceta();
                    //Dubilluz - 06.12.2011
                    ingresaMedicoFidelizado();
                    
                    
                } else if (e.getKeyCode() == KeyEvent.VK_F9 && !isListadoReceta()) {//add jcallo 15/12/2008 campanias acumuladas.
                   if(lblF9.isVisible()){
                    //veririficar que el producto seleccionado tiene el flag de campanias acumuladas.
                    vEjecutaAccionTeclaListado = true;
                    //validar que no sea un pedido por convenio
                    if (VariablesVentas.vEsPedidoConvenio) {
                        FarmaUtility.showMessage(this,
                                                 "No puede asociar clientes a campa�as de ventas acumuladas en un " +
                                                 "pedido por convenio.", txtProducto);
                        //     return;
                    } else { //toda la logica para asociar un cliente hacia campa�as nuevas
                        //DUBILLUZ - 29.04.2010
                        if (VariablesFidelizacion.vDniCliente.trim().length() > 0) {
                            int rowSelec = myJTable.getSelectedRow();
                            if (rowSelec >= 0)
                            //&& myJTable.getModel().getValueAt(rowSelec,10).toString().equals("S")
                            { //validar si el producto seleccionado tiene alguna campa�a asociada
                                String auxCodProd = myJTable.getValueAt(rowSelec, COL_COD).toString().trim();
                                asociarCampAcumulada(auxCodProd);
                            }
                        } else {
                            FarmaUtility.showMessage(this, "No puede ver las campa�as:\n" +
                                    "Porque primero debe de fidelizar al cliente con la funci�n F12.", txtProducto);
                        }

                    }

                    //JCALLO 19.12.2008 comentado sobre la opcion de ver pedidos delivery..y usarlo para el tema inscribir cliente a campa�as acumuladas
                    /** JCALLO INHABILITAR F9 02.10.2008* **/
                    /*log.debug("HABILITAR F9 : "+VariablesVentas.HabilitarF9);

            if ( VariablesVentas.HabilitarF9.equalsIgnoreCase(
                    ConstantsVentas.ACTIVO) ) {
              if(UtilityVentas.evaluaPedidoDelivery(this, txtProducto, VariablesVentas.vArrayList_PedidoVenta)){
                evaluaTitulo();
                // Inicio Adicion Delivery 28/04/2006 Paulo
                if(VariablesVentas.vEsPedidoDelivery) generarPedidoDelivery();
                // Fin Adicion Delivery 28/04/2006 Paulo
              }
            }*/
                  }
                } else if (UtilityPtoVenta.verificaVK_F10(e) && !isListadoReceta()) {
                    if(!UtilityInventario.is_Migra_Sap() && lblF10.isVisible()){
                        e.consume();
                        faltacero();
                    }else{
                        FarmaUtility.showMessage(this, "El local no tiene activo la funcionalidad.", txtProducto);
                    }
                } else if (UtilityPtoVenta.verificaVK_F11(e) && !isListadoReceta()) {
                    VariablesVentas.vIndDireccionarResumenPed = true;
                    VariablesVentas.vIndF11 = true;
                    aceptaOperacion();
                } else if (UtilityPtoVenta.verificaVK_F12(e) && !isListadoReceta()) {
                    //INI AOVIEDO 12/07/2017
                    //SE CAMBIA A FUNCIONALIDAD DE FIDELIZACI�N PARA LOS TICO
                    //CUANDO IND_PUNTOS = 'N'
                    
                    if((!DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)) || 
                    (DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio) && 
                       DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp())){//JMONZALVE 24042019
                        //ERIOS 18.02.2016 Verifica funcionalidad de puntos
	                    if(!UtilityPuntos.isActivoFuncionalidad()){ //AOVIEDO IND_PUNTOS = 'N'
	                        /*FarmaUtility.showMessage(this, "Esta opci�n no est� habilitada.", txtProducto);
	                        return;*/
	                    
	                        if(!VariablesFidelizacion.vNumTarjeta.isEmpty()){
	                            if(JConfirmDialog.rptaConfirmDialog(this, "Ya registr� un cliente, �Desea cambiarlo?")){
	                                VariablesFidelizacion.vNumTarjeta = "";
	                                VariablesFidelizacion.limpiaVariables();
	                            }else{
	                                return;
	                            }
	                        }
	                        
	                        accion_f12("N",true);
	                        
	                        //KMONCADA 13.08.2015 QUITARA LOS PACKS COMO FIDELIZADOS EN CASO NO EXISTA CLIENTE FIDELIZADO
	                        
	                        //AOVIEDO TICO DUDA PARA QUITAR
	                        /*if(UtilityPuntos.quitarPacksFidelizados()){
	                            cerrarVentana(true);
	                            VariablesVentas.vIndDireccionarResumenPed = true;
	                            FarmaVariables.vAceptar = false;
	                        }*/
	                        
	                        UpdateReleaseProd(e);
	                    }else{ //AOVIEDO IND_PUNTOS = 'S'
	                        // KMONCADA 2015.02.13 VALIDACION DE REINGRESO O CAMBIO DE CLIENTE
	                        BeanTarjeta tarjetaPuntosOld = null;
	                        String codProd;
	                        String descProd;
	                        UtilityVentas utilityVentas;
	                        
	                        //if(!pIngresoComprobanteManual){
	                        if(true){
	                            if(UtilityPuntos.isActivoFuncionalidad()){
	                                if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
	                                    if(JConfirmDialog.rptaConfirmDialog(this, "Programa Puntos:\nYa registro un cliente, �desea cambiarlo?")){
	                                        tarjetaPuntosOld = VariablesPuntos.frmPuntos.getBeanTarjeta();
	                                        VariablesFidelizacion.vNumTarjeta = "";
	                                        VariablesFidelizacion.limpiaVariables();
	                                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
	                                        lblCliente.setText("");//JMONZALVE 24042019
	                                    }else{
	                                        return;
	                                    }
	                                }else{//[Desarrollo5] 19.10.2015
	                                    /*VariablesFidelizacion.vNumTarjeta = "";
	                                    VariablesFidelizacion.limpiaVariables();
	                                    VariablesPuntos.frmPuntos.eliminarTarjetaBean();*/	
	                                }
	                            }
	                        }else{
	                            log.info("PROGRAMA PUNTOS [F12] --> SE ENCUENTRA EN VENTA MANUAL NO APLICA");
	                        }
	                        accion_f12("N",true);
	                        if(tarjetaPuntosOld!=null){
	                            UtilityPuntos.transferirInscripcionBonificados(myParentFrame, this, tarjetaPuntosOld, pIngresoComprobanteManual);
	                            //KMONCADA 13.08.2015 QUITARA LOS PACKS COMO FIDELIZADOS EN CASO NO EXISTA CLIENTE FIDELIZADO
	                            if(UtilityPuntos.quitarPacksFidelizados()){
	                                    //2016.09.16 CAMBIO
	                                                cerrarVentana(true);
	                                                /**
	                                                 * LTAVARA 2016.09.16
	                                                 * Validar los productos del pedido, si participan en el programa x+1 :
	                                                 * Cliente monedero: muestra solicitud de afiliacion al programa por producto. Si es tranferencia entre tarjetas solicita ingresar las cantidades de los productos x+1
	                                                 * Cliente no monedero : muestra mensaje en que programa participa el producto
	                                                 * */
	                                               dlgResumenPedido.validarProductosXmas1(tarjetaPuntosOld);
	                                                VariablesVentas.vIndDireccionarResumenPed = true;
	                                                FarmaVariables.vAceptar=false;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	                            }
	
	
	                        }
	                        
	                        UpdateReleaseProd(e);
	                    }
	                    //FIN AOVIEDO 12/07/2017
	            	}//JMONZALVE 24042019
                } else if (e.getKeyCode() == KeyEvent.VK_F3 && !isListadoReceta()) {
                    if(UtilityVentas.isPedidoReceta()){
                        FarmaUtility.showMessage(this, "No puede ingresar convenio para una atenci�n de receta m�dica.", txtProducto);
                    }
                    else{
                        //INI DMOSQUEIRA 20190710 - Validar que hay una bolsa seleccionada
                        boolean hayBolsaSeleccionada = false;
                        String codBolsaMed = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, 0, 0);
                        String codBolsaGran = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, 1, 0);
                        for (int i=0; i<VariablesVentas.vArrayList_PedidoVenta.size(); i++){
                            String codProd = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta, i, 0);
                            
                            if(codProd.equals(codBolsaMed) || codProd.equals(codBolsaGran)){
                                hayBolsaSeleccionada = true;
                                break;
                            }                                
                        }
                        
                        if(hayBolsaSeleccionada){
                            FarmaUtility.showMessage(this,
                                                     "No puede realizar ventas por convenio, ya que seleccion� un producto BOLSA MF",
                                                     txtProducto);
                        } else {
                            if(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana!=null && VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim().length()>1){
                                FarmaUtility.showMessage(this, "Ha agreado una tarjeta en campa�a, no puede agregar un convenio.", txtProducto);
                                txtProducto.setText("");
                                return;
                            }
                            //ERIOS 18.02.2016 Verifica funcionalidad de convenios.
                            if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && !pIngresoComprobanteManual) {
                                try{
                                    cargarConvenioBTL();                       
                                }catch(Throwable ex){
                                    log.error("",ex);
                                }

                            } /*else if (UtilityConvenioSITEDS.esActivoConvenioSITEDS(this, null)) {
        
                                DlgMain principal = new DlgMain(myParentFrame, "Convenios Variable", true);
                                FarmaUtility.centrarVentana(principal);
                               // principal.setLocationRelativeTo(null);
                                principal.setVisible(true);
        
                            }else{
                                FarmaUtility.showMessage(this, "Esta opci�n no est� habilitada.", txtProducto);
                            }*/                            
                        }
                        //FIN DMOSQUEIRA 20190710
                    }
                    
                    log.info("PEDIDO FIDELIZADO?????");
                    log.info("NUMERO DE TARJETA DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vNumTarjeta);
                    log.info("NUMERO DE DNI DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vDniCliente);
                    if(VariablesPuntos.frmPuntos==null)
                        log.info("VariablesPuntos.frmPuntos es null");
                    else    
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
                        log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
                    }
                    
                } else if (e.getKeyCode() == KeyEvent.VK_F4 && !isListadoReceta()) {
                    if (VariablesPtoVenta.vIndVerStockLocales.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                        vEjecutaAccionTeclaListado = false;

                        //JCORTEZ 24.07.09 Se valida ingreso del Quimico (Admin Loca)
                        if (cargaValidaLogin()) {
                            cargaStockLocales();
                        }
                        /*if (!ValidaRolUsu("011"))
                        {
                             FarmaUtility.showMessage(this,
                                                        "Usted no cuenta con el rol adecuado.",
                                                        txtProducto);
                        }
                        else
                            cargaStockLocales();*/
                        else {
                            FarmaUtility.showMessage(this, "El local no puede usar esta opci�n.\nGracias.", null);
                        }
                    } else if (VariablesPtoVenta.vIndVerReceMagis.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                        //LLEIVA 24-Junio-2013 - El producto virtual se debe vender solo, sin productos adicionales
                        if (VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                            VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                            VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                            VariablesVentas.vArrayList_Promociones.size() > 0 ||
                            VariablesVentas.vArrayList_Promociones_temporal.size() > 0) {
                            FarmaUtility.showMessage(this,
                                                     "La venta de un Producto Virtual debe ser �nica por pedido. Verifique!!!",
                                                     txtProducto);
                        } else {
                            DlgListaGuiasRM dlgListaGuiasRM = new DlgListaGuiasRM(myParentFrame, "", true);
                            dlgListaGuiasRM.setVisible(true);

                            if (FarmaVariables.vAceptar) {
                                VariablesVentas.vCant_Ingresada = VariablesRecetario.strCant_Recetario;
                                VariablesVentas.vVal_Prec_Lista_Tmp = VariablesRecetario.strPrecioTotal;
                                VariablesVentas.vVal_Bruto_Ped = VariablesRecetario.strPrecioTotal;
                                VariablesVentas.vVal_Prec_Vta = VariablesRecetario.strPrecioTotal;
                                VariablesVentas.vVal_Prec_Lista = VariablesRecetario.strPrecioTotal;

                                seleccionaProducto();

                                VariablesVentas.venta_producto_virtual = true;
                                VariablesVentas.vIndDireccionarResumenPed = true;
                                FarmaVariables.vAceptar = false;
                            } else {
                                VariablesVentas.vIndDireccionarResumenPed = false;
                                VariablesVentas.vProductoVirtual = false;
                            }
                            aceptaOperacion();
                        }
                    }
                } /*else if (e.getKeyCode() == KeyEvent.VK_F5 && !isListadoReceta()) {
                    if (VariablesVentas.vEsPedidoConvenio ||
                        (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() >
                         1)) {

                        FarmaUtility.showMessage(this,
                                                 "No puede agregar estas promociones a un " + "pedido por convenio.",
                                                 txtProducto);
                        return;
                    } else {

                        //vEjecutaAccionTeclaListado = false;
                        int vFila = myJTable.getSelectedRow();
                        Boolean valor = (Boolean)(myJTable.getValueAt(vFila, 0));
                        String indProm = (String)(myJTable.getValueAt(vFila, 17));

                        if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS))
                            VariablesVentas.vCodProdFiltro =
                                    FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
                        else
                            VariablesVentas.vCodProdFiltro = "";

                        if (indProm.equalsIgnoreCase("S")) { //if(!valor.booleanValue())
                            muestraPromocionProd(VariablesVentas.vCodProdFiltro);
                            //else
                            //  FarmaUtility.showMessage(this,"El Producto est� en una Promoci�n ya seleccionada",txtProducto);
                        } else
                            muestraPromocionProd(VariablesVentas.vCodProdFiltro);
                    }
                }*/ /*else if(e.getKeyChar() == '*') {//add jcallo 15/12/2008 campanias acumuladas.
            //veririficar que el producto seleccionado tiene el flag de campanias acumuladas.

            //validar que no sea un pedido por convenio
            if(VariablesVentas.vEsPedidoConvenio)
            {
                  FarmaUtility.showMessage(this,
                                           "No puede asociar clientes a campa�as de ventas acumuladas en un " +
                                           "pedido por convenio.", txtProducto);
             //     return;
            }else {//toda la logica para asociar un cliente hacia campa�as nuevas

                int rowSelec = myJTable.getSelectedRow();
                if(rowSelec >= 0
                   //&& myJTable.getModel().getValueAt(rowSelec,10).toString().equals("S")
                    ){//validar si el producto seleccionado tiene alguna campa�a asociada
                    String auxCodProd = myJTable.getValueAt(rowSelec, COL_COD).toString().trim();
                    asociarCampAcumulada(auxCodProd);

                }
            }

        }*/ else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    //vEjecutaAccionTeclaListado = false;
                    cancelaOperacion();
                    // Inicio Adicion Delivery 28/04/2006 Paulo
                    //limpiaVariables();
                    // Fin Adicion Delivery 28/04/2006 Paulo
                } else if (e.getKeyCode() == KeyEvent.VK_INSERT) { //Inicio ASOSA 02.02.2010 | 03.02.2010
                    VariablesVentas.vIndPrecioCabeCliente = "S";
                    DlgListaProdDIGEMID dlgDIGEMIT = new DlgListaProdDIGEMID(myParentFrame, "", true);
                    dlgDIGEMIT.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        cancelaOperacion();
                        cerrarVentana(true);
                    }
                } //Fin ASOSA 02.02.2010 | 03.02.2010
                //vEjecutaAccionTeclaListado = false;
                else if(e.getKeyCode() == KeyEvent.VK_ALT) {
                    
                }

            }

        } //try
        catch (Exception exc) {
            log.debug("catch" + vEjecutaAccionTeclaListado);
        } finally {
            vEjecutaAccionTeclaListado = false;
            log.debug(" finally: " + vEjecutaAccionTeclaListado);

        }

    }

    private void cerrarVentana(boolean pAceptar) {
        log.info("PEDIDO FIDELIZADO?????");
        log.info("NUMERO DE TARJETA DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vNumTarjeta);
        log.info("NUMERO DE DNI DE VARIABLE ESTATICA --> " + VariablesFidelizacion.vDniCliente);
        if(VariablesPuntos.frmPuntos==null)
            log.info("VariablesPuntos.frmPuntos es null");
        else    
        if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
            log.info("VariablesPuntos.frmPuntos.getBeanTarjeta es null");
        }
        
        FarmaVariables.vAceptar = pAceptar;
        VariablesVentas.vVentanaListadoProductos = false;
        VariablesVentas.vIndDireccionarResumenPed = pAceptar;
        VariablesVentas.vKeyPress = null;
        
        log.debug("ANTES DE CERRAR VENTANA ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
        log.debug(""+VariablesVentas.vArrayList_PedidoVenta);
        
        this.setVisible(false);
        this.dispose();
    }

    // **************************************************************************
    // Metodos de l�gica de negocio
    // **************************************************************************

    private void muestraInfoProd() {
        if (myJTable.getRowCount() <= 0)
            return;
        log.info("VariablesVentas.vCodigoBarra 05: " + VariablesVentas.vCodigoBarra);
        ArrayList myArray = new ArrayList();

        UtilityVentas.obtieneInfoProdEnArrayList(this,myJTable,myArray,txtProducto,COL_COD,false);
        log.debug("Tama�o en muestra info" + myArray.size());
        log.info("VariablesVentas.vCodigoBarra 06: " + VariablesVentas.vCodigoBarra);
        if (myArray.size() == 1) {
            log.info("VariablesVentas.vCodigoBarra 07: " + VariablesVentas.vCodigoBarra);
            stkProd = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
            descUnidPres = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
            valFracProd = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
            valPrecPres = ((String)((ArrayList)myArray.get(0)).get(3)).trim();
            indProdCong = ((String)((ArrayList)myArray.get(0)).get(4)).trim();
            valPrecVta = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
            descUnidVta = ((String)((ArrayList)myArray.get(0)).get(6)).trim();
            indProdHabilVta = ((String)((ArrayList)myArray.get(0)).get(7)).trim();
            porcDscto_1 = ((String)((ArrayList)myArray.get(0)).get(8)).trim();
            //log.info("DLGLISTAPRODUCTOS : porcDscto_1 - " + porcDscto_1);
            tipoProd = ((String)((ArrayList)myArray.get(0)).get(9)).trim();
            UtilityVentas.muestraIndTipoProd(myJTable, lblIndTipoProd, tipoProd);
            bonoProd = ((String)((ArrayList)myArray.get(0)).get(10)).trim();
            stkFracLoc = FarmaUtility.getValueFieldArrayList(myArray, 0, 11);
            descUnidFracLoc = FarmaUtility.getValueFieldArrayList(myArray, 0, 12);
            estadoProd = ((String)((ArrayList)myArray.get(0)).get(13)).trim();
            tipoProducto = ((String)((ArrayList)myArray.get(0)).get(14)).trim(); //ASOSA - 02/10/2014 - PANHD
            VariablesVentas.tipoProducto = tipoProducto; //ASOSA - 09/10/2014 - PANHD
            log.info("VariablesVentas.vCodigoBarra 08: " + VariablesVentas.vCodigoBarra);
            myJTable.setValueAt(((String)((ArrayList)myArray.get(0)).get(15)).trim(), myJTable.getSelectedRow(), 19);
            myJTable.setValueAt(((String)((ArrayList)myArray.get(0)).get(16)).trim(), myJTable.getSelectedRow(), 17);

        } else {
            stkProd = "";
            descUnidPres = "";
            valFracProd = "";
            valPrecPres = "";
            indProdCong = "";
            valPrecVta = "";
            descUnidVta = "";
            indProdHabilVta = "";
            porcDscto_1 = "";
            tipoProd = "";
            bonoProd = "";
            stkFracLoc = "";
            descUnidFracLoc = "";
            estadoProd = "";
            FarmaUtility.showMessage(this, "Error al obtener Informacion del Producto", txtProducto);
        }
        lblUnidad.setText(descUnidPres);
        lblPrecio.setText(valPrecPres);
        lblStockAdic.setText(stkFracLoc);
        lblUnidFracLoc.setText(descUnidFracLoc);
        
        myJTable.setValueAt(stkProd, myJTable.getSelectedRow(), 5);
        myJTable.setValueAt(valPrecVta, myJTable.getSelectedRow(), 6);
        myJTable.setValueAt(descUnidVta, myJTable.getSelectedRow(), 3);
        myJTable.setValueAt(bonoProd, myJTable.getSelectedRow(), 7);
        //dubilluz 20190731
        //VariablesVentas.vVal_Prec_Vta = valPrecPres; // JHAMRC 10072019
        VariablesVentas.vVal_Frac = valFracProd;
        VariablesVentas.vInd_Prod_Habil_Vta = indProdHabilVta;
        VariablesVentas.vPorc_Dcto_1 = porcDscto_1;
        log.info("DLGLISTAPRODUCTOS : VariablesVentas.vPorc_Dcto_1 - " + porcDscto_1);
        
        
        if(!DBVentas.isProdBolsa(FarmaUtility.getValueFieldJTable(myJTable, myJTable.getSelectedRow(), COL_COD)))
            loadInfoPrecioProducto(FarmaUtility.getValueFieldJTable(myJTable, myJTable.getSelectedRow(), COL_COD),myJTable.getSelectedRow());
        
        myJTable.repaint();
    }

    private void setJTable(JTable pJTable) {
        myJTable = pJTable;
        txtProducto.setText("");
        if (pJTable.getRowCount() > 0) {
            FarmaGridUtils.showCell(pJTable, 0, 0);
            FarmaUtility.setearActualRegistro(pJTable, txtProducto, 2);
            muestraInfoProd();
        }
        FarmaUtility.moveFocus(txtProducto);
    }

    private void muestraIngresoCantidad() {
        
        boolean flagContinua = true; 
        
        //ERIOS 09.01.2015 Mostrar pantalla garantizados.
        /*** INICIO ARAVELLO 01/10/2019 ***/
        if(flagContinua && 
           mostrarPantallaGarantizado()){
            return;
        }
        /*** FIN    ARAVELLO 01/10/2019 ***/
        int vFila = myJTable.getSelectedRow();
        
        flagContinua = UtilityVentas.validaVentaConMenos(this, myParentFrame, myJTable, txtProducto, pIngresoComprobanteManual);
        
        if (flagContinua) {

            //int vFila = myJTable.getSelectedRow();
            VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
            VariablesVentas.vDesc_Prod = ((String)(myJTable.getValueAt(vFila, 2))).trim();
            VariablesVentas.vNom_Lab = ((String)(myJTable.getValueAt(vFila, 4))).trim();
            VariablesVentas.vPorc_Igv_Prod = ((String)(myJTable.getValueAt(vFila, 11))).trim();
            VariablesVentas.vCant_Ingresada_Temp = "0";
            VariablesVentas.vNumeroARecargar = "";
            VariablesVentas.vVal_Prec_Lista_Tmp = "";
            VariablesVentas.vIndProdVirtual = FarmaConstants.INDICADOR_N;
            VariablesVentas.vTipoProductoVirtual = "";
            VariablesVentas.vVal_Prec_Pub = ((String)(myJTable.getValueAt(vFila, 6))).trim();
            VariablesVentas.vIndOrigenProdVta = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_ORIG_PROD);
            VariablesVentas.vPorc_Dcto_2 = "0";
            VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
            VariablesVentas.vCantxDia = "";
            VariablesVentas.vCantxDias = "";


            //Busca precio de convenio BTLMF
            UtilityConvenioBTLMF.obtienePrecioConvenio(this,txtProducto);
            
            //INI ASOSA - 23/10/2014
            if (isProdInPack() && VariablesPtoVenta.vIndTico.equals("S")) //ASOSA - 24/10/2014
            {
            // 2017.07.03 El flujo de packs s�lo es ingresando la cantidad del producto
             /*  if (JConfirmDialog.rptaConfirmDialogDefaultNo(this,
                                                              "El producto seleccionado puede comprarse en pack, \n�Desea comprarlo en pack?")) {
                    lblF5_keyPressed(null);
                } else {*/
                    agregaProdTemporalmente();
                    //FIN ASOSA - 23/10/2014
                    log.info("A PUNTO DE ABRIR LA PANTALLA DE CANTIDAD");
                    DlgIngresoCantidad dlgIngresoCantidad = new DlgIngresoCantidad(myParentFrame, "", true);
                    VariablesVentas.vIngresaCant_ResumenPed = false;
                    VariablesVentas.tipoLlamada = "0"; //ASOSA - 15/10/2014
                    dlgIngresoCantidad.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        //INI ASOSA - 27/03/2017 - PACKVARIEDAD
                        if (dlgIngresoCantidad.getIndTipoVenta().equals(ConstantsVentas.IND_PROD_NORMAL)) {
                            deseleccionaProducto();
                            if(dlgIngresoCantidad.isVAccionAcumula()){
                                                  // selecciona lo que acepta y lo del regalo por si acepta
                                                     if(dlgIngresoCantidad.getVCtdNormal()>0){
                                                        UtilityVentas.agregaProductoAcumula(this, tblProductos, null,false,
                                                                                            dlgIngresoCantidad.getVCtdNormal(),
                                                                                            dlgIngresoCantidad.getVCodCampAcumula(),
                                                                                            dlgIngresoCantidad.getVCodEQCampAcumula()
                                                                                            );
                                                    }
                                                    if(dlgIngresoCantidad.getVCtdBono()>0){
                                                        UtilityVentas.agregaProductoAcumula(this, tblProductos, null,true,
                                                                                            dlgIngresoCantidad.getVCtdBono(),
                                                                                            dlgIngresoCantidad.getVCodCampAcumula(),
                                                                                            dlgIngresoCantidad.getVCodEQCampAcumula());
                                                    }
                                                    //FarmaUtility.showMessage(this, "El Producto NOOOO", tblProductos);
                             }
                            else
                            seleccionaProducto();
                            FarmaVariables.vAceptar = false;
                        } else {
                            deseleccionaProducto();
                        }
                        //FIN ASOSA - 27/03/2017 - PACKVARIEDAD
                        VariablesVentas.vIndDireccionarResumenPed = true;                        
                    } else {
                        VariablesVentas.vIndDireccionarResumenPed = false;
                    }
          ///      }
            } else {
                log.info("A PUNTO DE ABRIR LA PANTALLA DE CANTIDAD");
                // agrega el producto y lo selecciona esto servira para poder validar todo.
                // dubilluz 2017.02.15
                agregaProdTemporalmente();
                DlgIngresoCantidad dlgIngresoCantidad = new DlgIngresoCantidad(myParentFrame, "", true);
                VariablesVentas.vIngresaCant_ResumenPed = false;
                VariablesVentas.tipoLlamada = "0"; //ASOSA - 15/10/2014
                dlgIngresoCantidad.setVisible(true);
                
                if (FarmaVariables.vAceptar) {
                    //INI ASOSA - 27/03/2017 - PACKVARIEDAD
                    if (dlgIngresoCantidad.getIndTipoVenta().equals(ConstantsVentas.IND_PROD_NORMAL)) {
                        // dubilluz 2017.02.15
                        deseleccionaProducto();
                        if(dlgIngresoCantidad.isVAccionAcumula()){
                                              // selecciona lo que acepta y lo del regalo por si acepta
                                                 if(dlgIngresoCantidad.getVCtdNormal()>0){
                                                    UtilityVentas.agregaProductoAcumula(this, tblProductos, null,false,
                                                                                        dlgIngresoCantidad.getVCtdNormal(),
                                                                                        dlgIngresoCantidad.getVCodCampAcumula(),
                                                                                        dlgIngresoCantidad.getVCodEQCampAcumula()
                                                                                        );
                                                }
                                                if(dlgIngresoCantidad.getVCtdBono()>0){
                                                    UtilityVentas.agregaProductoAcumula(this, tblProductos, null,true,
                                                                                        dlgIngresoCantidad.getVCtdBono(),
                                                                                        dlgIngresoCantidad.getVCodCampAcumula(),
                                                                                        dlgIngresoCantidad.getVCodEQCampAcumula());
                                                }
                                                //FarmaUtility.showMessage(this, "El Producto NOOOO", tblProductos);
                         }
                        else
                        seleccionaProducto();
                        FarmaVariables.vAceptar = false;
                    } else {
                        deseleccionaProducto();
                    }
                    //FIN ASOSA - 27/03/2017 - PACKVARIEDAD
                    VariablesVentas.vIndDireccionarResumenPed = true;
                } else {
                    // dubilluz 2017.02.15
                    deseleccionaProducto();
                    VariablesVentas.vIndDireccionarResumenPed = false;
                }
            }

            VariablesVentas.vValorMultiplicacion = "1"; //ASOSA - 12/08/2014
        }

    }

    private void colocaTotalesPedido() {
        calculaTotalVentaPedido();
        totalItems =
                VariablesVentas.vArrayList_PedidoVenta.size() + VariablesVentas.vArrayList_Prod_Promociones_temporal.size() +
                VariablesVentas.vArrayList_Prod_Promociones.size();
        lblItems.setText("" + totalItems);
        lblTotalVenta.setText(FarmaUtility.formatNumber(totalVenta, 2));
    }

    /**
     * Calcula el monto total de venta
     * @author dubilluz
     * @since  18.06.2007
     */
    private void calculaTotalVentaPedido() {
        totalVenta = 0;
        for (int i = 0; i < VariablesVentas.vArrayList_PedidoVenta.size(); i++)
            totalVenta +=
                    FarmaUtility.getDecimalNumber(((String)((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(i)).get(7)).trim());

        double totalProd_Prom = 0.00;

        ArrayList aux = new ArrayList();
        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones_temporal.size(); i++) {
            aux = (ArrayList)VariablesVentas.vArrayList_Prod_Promociones_temporal.get(i);
            //log.debug(FarmaUtility.getDecimalNumber(""+aux.get(6))+"");
            totalProd_Prom += FarmaUtility.getDecimalNumber("" + aux.get(7));
        }

        ArrayList aux2 = new ArrayList();
        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
            aux2 = (ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i);
            //log.debug(FarmaUtility.getDecimalNumber(""+aux.get(6))+"");
            totalProd_Prom += FarmaUtility.getDecimalNumber("" + aux2.get(7));
        }

        totalVenta += totalProd_Prom;
    }

    private void verificaCheckJTable() {
        log.info("VariablesVentas.vCodigoBarra 01: " + VariablesVentas.vCodigoBarra);
        int vFila = myJTable.getSelectedRow();
        String codigo = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
        log.info("VariablesVentas.vCodigoBarra 02: " + VariablesVentas.vCodigoBarra);
        
        String codBolsaMed = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, 0, 0);//DMOSQUEIRA 20190710
        String codBolsaGran = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, 1, 0);//DMOSQUEIRA 20190710
        
        if (isListadoReceta()) {
            codProdRecetaSelect = codigo;
            cerrarVentana(true);
        //INI DMOSQUEIRA 20190710: Se valida que, cuando haya un convenio seleccionado, no se pueda agregar los productos BOLSA MF    
        } else if ((VariablesConvenioBTLMF.vCodConvenio != null &&
                    VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1) &&
                   (codigo.equals(codBolsaMed) || codigo.equals(codBolsaGran))) {
            
            FarmaUtility.showMessage(this,
                                     "No debe registrar un producto BOLSA MF en una venta por convenio",
                                     txtProducto);
        //FIN DMOSQUEIRA 20190710
        } else {
            if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS))
                actualizaListaProductosAlternativos();
            log.info("VariablesVentas.vCodigoBarra 03: " + VariablesVentas.vCodigoBarra);
            Boolean valor = (Boolean)(myJTable.getValueAt(vFila, 0));
            if (valor.booleanValue()) {
                if (!UtilityVentas.buscar(VariablesVentas.vArrayList_Prod_Promociones, codigo) &&
                    !UtilityVentas.buscar(VariablesVentas.vArrayList_Prod_Promociones_temporal, codigo)) {
                    deseleccionaProducto();
                } else {
                    /* ASOSA - 30/03/2017 - PACKVARIEDAD - PACKVARIEDADREVERTIR
                    FarmaUtility.showMessage(this, "El Producto se encuentra en una Promoci�n", txtProducto);
                    return;
                    */
                    //INI ASOSA - 30/03/2017 - PACKVARIEDAD - se esta agregando este padazaso de codigo a ver si cumple con lo que desean de mezclar packs con productos normales de ellos. //PACKVARIEDADREVERTIR
                    log.info("VariablesVentas.vCodigoBarra 04: " + VariablesVentas.vCodigoBarra);
                    muestraInfoProd();
                    VariablesVentas.vIndProdVirtual = FarmaUtility.getValueFieldJTable(myJTable, vFila, 13);
                    
                    // KMONCADA 02.07.2014 VALIDA ESTADO DEL PRODUCTO
                    if (!"A".equalsIgnoreCase(estadoProd)) {
                        FarmaUtility.showMessage(this, "El Producto se encuentra inactivo en local.", txtProducto);
                        return;
                    }
                    
                    if (!UtilityVentas.validaStockDisponible(stkProd) &&
                        !VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                        //INI ASOSA - 02/10/2014 - PANHD
                        if (!isProductoFinal()) {
                            return;
                        }
                        //FIN ASOSA - 02/10/2014
                    }
                    if (!UtilityVentas.validaProductoTomaInventario(indProdCong)) {
                        FarmaUtility.showMessage(this, "El Producto se encuentra Congelado por Toma de Inventario",
                                                 txtProducto);
                        return;
                    }
                    if (!UtilityVentas.validaProductoHabilVenta()) {
                        FarmaUtility.showMessage(this, "El Producto NO se encuentra h�bil para la venta. Verifique!!!",
                                                 txtProducto);
                        return;
                    }
                    
                    VariablesVentas.vIndProdVirtual = FarmaUtility.getValueFieldJTable(myJTable, vFila, 13);
                    
                    String tipoProdVirtual = FarmaUtility.getValueFieldJTable(myJTable, myJTable.getSelectedRow(), 14);
                    /*            if (VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S) ||
                        VariablesVentas.vIndPedConProdVirtual) {
                    */
                    
                    //INI ASOSA - 13/01/2015 - RIMAC
                    if(UtilityInventario.isExistProdRimac(VariablesVentas.vCod_Prod)){   
                        if ((VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                             VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                             VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                             VariablesVentas.vArrayList_Promociones.size() > 0 ||
                             VariablesVentas.vArrayList_Promociones_temporal.size() > 0)) {
                            FarmaUtility.showMessage(this,
                                                     "La venta de un Producto RIMAC debe ser �nica por pedido. Verifique!!!",
                                                     txtProducto);
                            return;
                        }
                        if (VariablesVentas.vEsPedidoConvenio ||    //ASOSA - 22/01/2015 - RIMAC
                            (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() >
                             1)) {
                    
                            FarmaUtility.showMessage(this, "No puede realizar ventas del producto RIMAC en un pedido por convenio.",
                                                     txtProducto);
                            return;
                        }
                    }
                    //FIN ASOSA - 13/01/2015 - RIMAC
                    
                    //kmoncada: cambio para balanza kieto
                    if (((VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S) ||
                         VariablesVentas.vIndPedConProdVirtual) && !"B".equalsIgnoreCase(tipoProdVirtual)) &&
                            !UtilityInventario.isExistProdRimac(VariablesVentas.vCod_Prod)   //ASOSA - 15/01/2015 - RIMAC
                        ) {
                        //ERIOS 2.4.4 No se permite venta de recarga virtuales por convenios
                        if (VariablesVentas.vEsPedidoConvenio ||
                            (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() >
                             1)) {
                    
                            FarmaUtility.showMessage(this, "No puede realizar recargas virtuales en " + "pedido por convenio.",
                                                     txtProducto);
                            return;
                        }
                        //ERIOS 2.4.4 No se permite venta de recarga virtuales por delivery
                        if (VariablesVentas.vEsPedidoDelivery) {
                    
                            FarmaUtility.showMessage(this, "No puede realizar recargas virtuales en " + "pedido por delivery.",
                                                     txtProducto);
                            return;
                        }
                        //Modificado para que no pueda comprar Nada si hay Promociones
                        if (VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                            VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                            VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                            VariablesVentas.vArrayList_Promociones.size() > 0 ||
                            VariablesVentas.vArrayList_Promociones_temporal.size() > 0) {
                            FarmaUtility.showMessage(this,
                                                     "La venta de un Producto Virtual debe ser �nica por pedido. Verifique!!!",
                                                     txtProducto);
                            return;
                        } else {
                            VariablesVentas.vIndProdControlStock = false;
                            VariablesVentas.vIndPedConProdVirtual = true;
                            evaluaTipoProdVirtual();
                            VariablesVentas.vIndPedConProdVirtual = false;
                        }
                    } else {
                        //kmoncada balanza keito
                        if ((VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                             VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                             VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                             VariablesVentas.vArrayList_Promociones.size() > 0 ||
                             VariablesVentas.vArrayList_Promociones_temporal.size() > 0) &&
                            "B".equalsIgnoreCase(tipoProdVirtual)) {
                            FarmaUtility.showMessage(this,
                                                     "La venta de un Producto Virtual debe ser �nica por pedido. Verifique!!!",
                                                     txtProducto);
                            return;
                        }
                        
                        //INI ASOSA - 06/01/2015 - RIMAC
                        if(UtilityInventario.isExistProdRimac(VariablesVentas.vCod_Prod)){   
                            
                            int vFila_rimac = myJTable.getSelectedRow();
                            VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, vFila_rimac, COL_COD);
                            VariablesVentas.vDesc_Prod = ((String)(myJTable.getValueAt(vFila_rimac, 2))).trim();
                            VariablesVentas.vNom_Lab = ((String)(myJTable.getValueAt(vFila_rimac, 4))).trim();
                            VariablesVentas.vPorc_Igv_Prod = ((String)(myJTable.getValueAt(vFila_rimac, 11))).trim();
                            VariablesVentas.vCant_Ingresada_Temp = "0";
                            VariablesVentas.vNumeroARecargar = "";
                            VariablesVentas.vVal_Prec_Lista_Tmp = "";
                            VariablesVentas.vIndProdVirtual = FarmaConstants.INDICADOR_N;
                            VariablesVentas.vTipoProductoVirtual = "";
                            VariablesVentas.vVal_Prec_Pub = ((String)(myJTable.getValueAt(vFila_rimac, 6))).trim();
                            VariablesVentas.vIndOrigenProdVta = FarmaUtility.getValueFieldJTable(myJTable, vFila_rimac, COL_ORIG_PROD);
                            VariablesVentas.vPorc_Dcto_2 = "0";
                            VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
                            VariablesVentas.vCantxDia = "";
                            VariablesVentas.vCantxDias = "";
                            
                            DlgIngresoProductoRimac dlgIngresoProductoRimac = new DlgIngresoProductoRimac(myParentFrame, "", true);
                            dlgIngresoProductoRimac.setVisible(true);
                            if (!FarmaVariables.vAceptar) {
                                VariablesVentas.vCantMesRimac = 0;
                                VariablesVentas.vDniRimac = "";
                                return;
                            }
                        } else {
                            VariablesVentas.vCantMesRimac = 0;
                            VariablesVentas.vDniRimac = "";
                        }
                        //FIN ASOSA - 06/01/2015 - RIMAC
                    
                        //kmoncada mostrar pantalla en caso de producto Balanza Keito (Prod Virtual)
                        log.info("Resultado de balanza " + ("B".equalsIgnoreCase(tipoProdVirtual)));
                        //VariablesVentas.vIndProdControlStock = true;
                        VariablesVentas.vIndProdControlStock = !("B".equalsIgnoreCase(tipoProdVirtual)); //
                        //
                        VariablesVentas.vIndPedConProdVirtual = false;
                    
                        muestraIngresoCantidad();
                    
                        //kmoncada si ha seleccionado producto evalua si se trata de balanza keito y lo marca como prod virtual
                        if (VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                            VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                            VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                            VariablesVentas.vArrayList_Promociones.size() > 0 ||
                            VariablesVentas.vArrayList_Promociones_temporal.size() > 0) {
                            if (!VariablesVentas.vProductoVirtual && ("B".equalsIgnoreCase(tipoProdVirtual))) {
                                VariablesVentas.vProductoVirtual = true;
                            }
                        }
                    }
                    //FIN ASOSA - 30/03/2017 - PACKVARIEDAD
                }
            } else {
                log.info("VariablesVentas.vCodigoBarra 04: " + VariablesVentas.vCodigoBarra);
                muestraInfoProd();
                VariablesVentas.vIndProdVirtual = FarmaUtility.getValueFieldJTable(myJTable, vFila, 13);
    
                // KMONCADA 02.07.2014 VALIDA ESTADO DEL PRODUCTO
                if (!"A".equalsIgnoreCase(estadoProd)) {
                    FarmaUtility.showMessage(this, "El Producto se encuentra inactivo en local.", txtProducto);
                    return;
                }
                
                if (!UtilityVentas.existsStockComp(this, VariablesVentas.vCod_Prod, 1, 1)) {
                    return;
                }
    
                if (!UtilityVentas.validaStockDisponible(stkProd) &&
                    !VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                    //INI ASOSA - 02/10/2014 - PANHD
                    if (!isProductoFinal()) {
                        return;
                    }
                    //FIN ASOSA - 02/10/2014
                }
                if (!UtilityVentas.validaProductoTomaInventario(indProdCong)) {
                    FarmaUtility.showMessage(this, "El Producto se encuentra Congelado por Toma de Inventario",
                                             txtProducto);
                    return;
                }
                if (!UtilityVentas.validaProductoHabilVenta()) {
                    FarmaUtility.showMessage(this, "El Producto NO se encuentra h�bil para la venta. Verifique!!!",
                                             txtProducto);
                    return;
                }
    
                VariablesVentas.vIndProdVirtual = FarmaUtility.getValueFieldJTable(myJTable, vFila, 13);
    
                String tipoProdVirtual = FarmaUtility.getValueFieldJTable(myJTable, myJTable.getSelectedRow(), 14);
                /*            if (VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S) ||
                    VariablesVentas.vIndPedConProdVirtual) {
    */
                
                //INI ASOSA - 13/01/2015 - RIMAC
                if(UtilityInventario.isExistProdRimac(VariablesVentas.vCod_Prod)){   
                    if ((VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                         VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                         VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                         VariablesVentas.vArrayList_Promociones.size() > 0 ||
                         VariablesVentas.vArrayList_Promociones_temporal.size() > 0)) {
                        FarmaUtility.showMessage(this,
                                                 "La venta de un Producto RIMAC debe ser �nica por pedido. Verifique!!!",
                                                 txtProducto);
                        return;
                    }
                    if (VariablesVentas.vEsPedidoConvenio ||    //ASOSA - 22/01/2015 - RIMAC
                        (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() >
                         1)) {
    
                        FarmaUtility.showMessage(this, "No puede realizar ventas del producto RIMAC en un pedido por convenio.",
                                                 txtProducto);
                        return;
                    }
                }
                //FIN ASOSA - 13/01/2015 - RIMAC
                
                //kmoncada: cambio para balanza kieto
                if (((VariablesVentas.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S) ||
                     VariablesVentas.vIndPedConProdVirtual) && !"B".equalsIgnoreCase(tipoProdVirtual)) &&
                        !UtilityInventario.isExistProdRimac(VariablesVentas.vCod_Prod)   //ASOSA - 15/01/2015 - RIMAC
                    ) {
                    //ERIOS 2.4.4 No se permite venta de recarga virtuales por convenios
                    if (VariablesVentas.vEsPedidoConvenio ||
                        (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() >
                         1)) {
    
                        FarmaUtility.showMessage(this, "No puede realizar recargas virtuales en " + "pedido por convenio.",
                                                 txtProducto);
                        return;
                    }
                    //ERIOS 2.4.4 No se permite venta de recarga virtuales por delivery
                    if (VariablesVentas.vEsPedidoDelivery) {
    
                        FarmaUtility.showMessage(this, "No puede realizar recargas virtuales en " + "pedido por delivery.",
                                                 txtProducto);
                        return;
                    }
                    //Modificado para que no pueda comprar Nada si hay Promociones
                    if (VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                        VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                        VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                        VariablesVentas.vArrayList_Promociones.size() > 0 ||
                        VariablesVentas.vArrayList_Promociones_temporal.size() > 0) {
                        FarmaUtility.showMessage(this,
                                                 "La venta de un Producto Virtual debe ser �nica por pedido. Verifique!!!",
                                                 txtProducto);
                        return;
                    } else {
                        VariablesVentas.vIndProdControlStock = false;
                        VariablesVentas.vIndPedConProdVirtual = true;
                        evaluaTipoProdVirtual();
                        VariablesVentas.vIndPedConProdVirtual = false;
                    }
                } else {
                    //kmoncada balanza keito
                    if ((VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                         VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                         VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                         VariablesVentas.vArrayList_Promociones.size() > 0 ||
                         VariablesVentas.vArrayList_Promociones_temporal.size() > 0) &&
                        "B".equalsIgnoreCase(tipoProdVirtual)) {
                        FarmaUtility.showMessage(this,
                                                 "La venta de un Producto Virtual debe ser �nica por pedido. Verifique!!!",
                                                 txtProducto);
                        return;
                    }
                    
                    //INI ASOSA - 06/01/2015 - RIMAC
                    if(UtilityInventario.isExistProdRimac(VariablesVentas.vCod_Prod)){   
                        
                        int vFila_rimac = myJTable.getSelectedRow();
                        VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, vFila_rimac, COL_COD);
                        VariablesVentas.vDesc_Prod = ((String)(myJTable.getValueAt(vFila_rimac, 2))).trim();
                        VariablesVentas.vNom_Lab = ((String)(myJTable.getValueAt(vFila_rimac, 4))).trim();
                        VariablesVentas.vPorc_Igv_Prod = ((String)(myJTable.getValueAt(vFila_rimac, 11))).trim();
                        VariablesVentas.vCant_Ingresada_Temp = "0";
                        VariablesVentas.vNumeroARecargar = "";
                        VariablesVentas.vVal_Prec_Lista_Tmp = "";
                        VariablesVentas.vIndProdVirtual = FarmaConstants.INDICADOR_N;
                        VariablesVentas.vTipoProductoVirtual = "";
                        VariablesVentas.vVal_Prec_Pub = ((String)(myJTable.getValueAt(vFila_rimac, 6))).trim();
                        VariablesVentas.vIndOrigenProdVta = FarmaUtility.getValueFieldJTable(myJTable, vFila_rimac, COL_ORIG_PROD);
                        VariablesVentas.vPorc_Dcto_2 = "0";
                        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
                        VariablesVentas.vCantxDia = "";
                        VariablesVentas.vCantxDias = "";
                        
                        DlgIngresoProductoRimac dlgIngresoProductoRimac = new DlgIngresoProductoRimac(myParentFrame, "", true);
                        dlgIngresoProductoRimac.setVisible(true);
                        if (!FarmaVariables.vAceptar) {
                            VariablesVentas.vCantMesRimac = 0;
                            VariablesVentas.vDniRimac = "";
                            return;
                        }
                    } else {
                        VariablesVentas.vCantMesRimac = 0;
                        VariablesVentas.vDniRimac = "";
                    }
                    //FIN ASOSA - 06/01/2015 - RIMAC
    
                    //kmoncada mostrar pantalla en caso de producto Balanza Keito (Prod Virtual)
                    log.info("Resultado de balanza " + ("B".equalsIgnoreCase(tipoProdVirtual)));
                    //VariablesVentas.vIndProdControlStock = true;
                    VariablesVentas.vIndProdControlStock = !("B".equalsIgnoreCase(tipoProdVirtual)); //
                    //
                    VariablesVentas.vIndPedConProdVirtual = false;
    
                    muestraIngresoCantidad();
    
                    //kmoncada si ha seleccionado producto evalua si se trata de balanza keito y lo marca como prod virtual
                    if (VariablesVentas.vArrayList_PedidoVenta.size() > 0 ||
                        VariablesVentas.vArrayList_Prod_Promociones.size() > 0 ||
                        VariablesVentas.vArrayList_Prod_Promociones_temporal.size() > 0 ||
                        VariablesVentas.vArrayList_Promociones.size() > 0 ||
                        VariablesVentas.vArrayList_Promociones_temporal.size() > 0) {
                        if (!VariablesVentas.vProductoVirtual && ("B".equalsIgnoreCase(tipoProdVirtual))) {
                            VariablesVentas.vProductoVirtual = true;
                        }
                    }
                }
            }
    
            //txtProducto.selectAll();
            UtilityVentas.muestraNombreLab(myJTable,4, lblDescLab_Prod);
            UtilityVentas.muestraProductoInafectoIgv(myJTable,11, lblProdIgv);        
            UtilityVentas.muestraProductoPromocion(myJTable,17, lblProdProm,1);
            UtilityVentas.muestraProductoRefrigerado(myJTable,15, lblProdRefrig);
            UtilityVentas.muestraIndTipoProd(myJTable,16, lblIndTipoProd);
            UtilityVentas.muestraProductoCongelado(myJTable,indProdCong,lblProdCong);
            UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
            UtilityVentas.muestraPuntosRentables(myJTable,COL_COD,lblPuntosRentables);
    
            aceptaOperacion();
        }

    }

    private void seleccionaProducto() {
        int vFila = myJTable.getSelectedRow();
        Boolean valorTmp = (Boolean)(myJTable.getValueAt(vFila, 0));
        UtilityVentas.seleccionaProducto(this, myJTable, txtProducto);
        pintaCheckOtroJTable(myJTable, valorTmp);
        colocaTotalesPedido();
    }

    private void deseleccionaProducto() {
        
        int vFila = myJTable.getSelectedRow();
        Boolean valorTmp = (Boolean)(myJTable.getValueAt(vFila, 0));        
        
        UtilityVentas.deseleccionaProducto(this, myJTable, COL_COD);

        pintaCheckOtroJTable(myJTable, valorTmp);
        //indicadorItems = FarmaConstants.INDICADOR_N;
        colocaTotalesPedido();
    }


    /**
     * Metodo para determinar si el producto es un producto final y tiene stock.
     * @author ASOSA
     * @since 02/10/2014
     * @return
     */
    private boolean isProductoFinal() {
        boolean flag = false;
        if (tipoProducto.trim().equals(ConstantsVentas.TIPO_PROD_FINAL)) {
            String[] list = UtilityVentas.obtenerInfoRecetaProdFinal(VariablesVentas.vCod_Prod);
            String codRpta = list[0];
            String descRpta = list[1];
            if (codRpta.equals("S")) {
                flag = true;
            } else {
                FarmaUtility.showMessage(this, descRpta, null);
            }
        }
        return flag;
    }

    private void cancelaOperacion() {
        String codProd = "";
        String codProdTmp = "";
        String indControlStk = "";
        boolean existe = false;

        String secRespaldo = ""; //ASOSA, 01.07.2010

        for (int i = 0; i < VariablesVentas.vArrayList_PedidoVenta.size(); i++) {
            codProd = (String)(((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(i)).get(0));
            VariablesVentas.vVal_Frac =
                    FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta, i, 10);
            String cantidad = (String)(((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(i)).get(4));
            indControlStk = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_PedidoVenta, i, 16);

            secRespaldo =
                    (String)(((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(i)).get(26)); //ASOSA, 01.07.2010

            existe = UtilityCalculoPrecio.isExisteCodProd(codProd);

            if (!existe) {
                int aux2CantIng = FarmaUtility.trunc(FarmaUtility.getDecimalNumber(cantidad));
                VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                    /*!UtilityVentas.actualizaStkComprometidoProd(VariablesVentas.vCod_Prod, //ANTES; ASOSA, 01.07.2010
                                                        aux2CantIng,
                                                        ConstantsVentas.INDICADOR_A,
                                                        ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                                                        aux2CantIng,
                                                        true,
                                                        this,
                                                        txtProducto,))*/
                    !UtilityVentas.operaStkCompProdResp(VariablesVentas.vCod_Prod,
                        //ASOSA, 01.07.2010
                        aux2CantIng, ConstantsVentas.INDICADOR_A, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                        aux2CantIng, true, this, txtProducto, secRespaldo))
                    return;
            }

            existe = false;
        }

        for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
            BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
            codProd = bean.getVCodProd();
            VariablesVentas.vVal_Frac = bean.getVValFracVta();
            String cantidad =  bean.getVCtdVta();
            String cantidadTmp = "0";
            indControlStk = bean.getVIndControlaStock();
            secRespaldo =  (String)(((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(i)).get(26)); //ASOSA, 01.07.2010
            log.debug("", existe);

            for (int j = 0; j < VariablesVentas.vArrayList_PedidoVenta.size(); j++) {
                codProdTmp = (String)(((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(j)).get(0));

                if (codProd.equalsIgnoreCase(codProdTmp)) {
                    existe = true;
                    cantidadTmp = (String)(((ArrayList)VariablesVentas.vArrayList_PedidoVenta.get(j)).get(4));
                    break;
                }
            }
            log.debug("", existe);

            if (!existe) {
                int aux2CantIng = FarmaUtility.trunc(FarmaUtility.getDecimalNumber(cantidad));
                VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                    /*!UtilityVentas.actualizaStkComprometidoProd(VariablesVentas.vCod_Prod, //ANTES; ASOSA, 01.07.2010
                                                        aux2CantIng,
                                                        ConstantsVentas.INDICADOR_A,
                                                        ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                                                        aux2CantIng,
                                                        true,
                                                        this,
                                                        txtProducto,))*/
                    !UtilityVentas.operaStkCompProdResp(VariablesVentas.vCod_Prod,
                        //ASOSA, 01.07.2010
                        aux2CantIng, ConstantsVentas.INDICADOR_A, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                        aux2CantIng, true, this, txtProducto, secRespaldo))
                    return;
            } else {
                int aux3CantIng = Integer.parseInt(cantidad);
                int aux4CantIngTmp = Integer.parseInt(cantidadTmp);

                if (aux3CantIng < aux4CantIngTmp) {
                    int aux5CantIng = aux4CantIngTmp - aux3CantIng;
                    VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                    if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                        /*!UtilityVentas.actualizaStkComprometidoProd(VariablesVentas.vCod_Prod, //ANTES; ASOSA, 01.07.2010
                                                          aux2CantIng,
                                                          ConstantsVentas.INDICADOR_A,
                                                          ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                                                          aux2CantIng,
                                                          true,
                                                          this,
                                                          txtProducto,))*/
                        !UtilityVentas.operaStkCompProdResp(VariablesVentas.vCod_Prod,
                            //ASOSA, 01.07.2010
                            aux5CantIng, ConstantsVentas.INDICADOR_A, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                            aux3CantIng, true, this, txtProducto, secRespaldo))
                        return;
                } else if (aux3CantIng > aux4CantIngTmp) {
                    int aux5CantIng = aux3CantIng - aux4CantIngTmp;
                    VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                    if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                        /*!UtilityVentas.actualizaStkComprometidoProd(VariablesVentas.vCod_Prod, //ANTES; ASOSA, 01.07.2010
                                                          aux2CantIng,
                                                          ConstantsVentas.INDICADOR_A,
                                                          ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                                                          aux2CantIng,
                                                          true,
                                                          this,
                                                          txtProducto,))*/
                        !UtilityVentas.operaStkCompProdResp(VariablesVentas.vCod_Prod,
                            //ASOSA, 01.07.2010
                            aux5CantIng, ConstantsVentas.INDICADOR_A, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                            aux3CantIng, true, this, txtProducto, secRespaldo))
                        return;
                }
            }
            existe = false;
        }

        cancela_promociones();
        cerrarVentana(false);
    }

    private void aceptaOperacion() {
        log.info("ENTRO A ACEPTAR OPERACION");
        log.debug("<<TCT 3>> Genera Resumen de Pedido ITEMS= " + VariablesVentas.vArrayList_PedidoVenta.size());
        log.debug(""+VariablesVentas.vArrayList_PedidoVenta);
        vEjecutaAccionTeclaListado = false;
        
        saveResumenProductosVendidos();

        /*
             * Solo se cerrara si el indicador lo permite
             * dubilluz 11.04.2008
             */
        if (VariablesVentas.vIndDireccionarResumenPed)
            cerrarVentana(true);
        log.debug("<<TCT 3>> Despues de Carga Resumen Hay Prom= " + VariablesVentas.vArrayList_Promociones.size());

    }


    private void cargaListaFiltro() {
        //ERIOS 29.08.2013 Al filtrar, se muestra "[ F7 ] Quitar Filtro"
        vEjecutaAccionTeclaListado = false;
        String tipoOrdenamiento = null;
        if (VariablesVentas.vCodFiltro.equalsIgnoreCase("")) {
            /* DlgFiltroProductos dlgFiltroProductos = new DlgFiltroProductos(myParentFrame, "", true);
            dlgFiltroProductos.setVisible(true); */
            DlgFiltroProductosNuevo dlgFiltroProductos = new DlgFiltroProductosNuevo(myParentFrame, "", true);
            dlgFiltroProductos.setVisible(true);
            tipoOrdenamiento = dlgFiltroProductos.getTipoOrden();
            
        } else {
            UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
            VariablesPtoVenta.vInd_Filtro = FarmaConstants.INDICADOR_S;
            log.debug("VariablesPtoVenta.vTipoFiltro  : " + VariablesVentas.vCodFiltro);
            VariablesPtoVenta.vTipoFiltro = VariablesVentas.vCodFiltro;
            VariablesPtoVenta.vCodFiltro = "";
            //polimorfismo
            /*VariablesVentas.vCodFiltro_temp=VariablesVentas.vCodFiltro;
      if(!VariablesVentas.vCodFiltro_temp.equalsIgnoreCase("")){
        filtrarTablaProductos(VariablesVentas.vCodFiltro_temp,VariablesPtoVenta.vTipoFiltro,VariablesPtoVenta.vCodFiltro);
      }*/
            FarmaVariables.vAceptar = true;
        }

        if (FarmaVariables.vAceptar) {
            tblModelListaSustitutos.clearTable();
            txtProducto.setText("");
            filtrarTablaProductos(VariablesPtoVenta.vTipoFiltro, VariablesPtoVenta.vCodFiltro, tipoOrdenamiento);
            setJTable(tblProductos);
            //iniciaProceso(false);
            FarmaUtility.ponerCheckJTable(tblProductos, COL_COD, VariablesVentas.vArrayList_PedidoVenta, 0);
            FarmaUtility.ponerCheckJTable(tblProductos, COL_COD, VariablesVentas.vArrayList_Prod_Promociones_temporal,
                                          0);
            FarmaUtility.ponerCheckJTable(tblProductos, COL_COD, VariablesVentas.vArrayList_Prod_Promociones, 0);

            FarmaVariables.vAceptar = false;

            //lblF7.setText("[ F7 ] Quitar Filtro");
            lblF7.setText("[ F7 ] Mostrar Todo");
        }

        VariablesPtoVenta.vTipoFiltro = "";
        VariablesPtoVenta.vCodFiltro = "";
        VariablesVentas.vCodFiltro = "";
    }


    private void filtrarTablaProductos(String pTipoFiltro, String pCodFiltro, String pTipoOrdenamiento) {
        try {
            tableModelListaPrecioProductos.clearTable();
            tableModelListaPrecioProductos.fireTableDataChanged();
            tblModelListaSustitutos.clearTable();
            clonarListadoProductos();
            //DBVentas.cargaListaProductosVenta_Filtro(tableModelListaPrecioProductos, pTipoFiltro, pCodFiltro);
            DBVentas.cargaListaProductosVenta_FiltroNuevo(tableModelListaPrecioProductos, pTipoFiltro, pCodFiltro, pTipoOrdenamiento);
            /*if (tableModelListaPrecioProductos.getRowCount() > 0)
                FarmaUtility.ordenar(tblProductos, tableModelListaPrecioProductos, COL_ORD_LISTA,
                                     FarmaConstants.ORDEN_ASCENDENTE);*/
            //else
            //FarmaUtility.showMessage(this, "Resultado vacio", null);
        } catch (SQLException sqlException) {
            log.error(null, sqlException);
            FarmaUtility.showMessage(this, "Error al obtener Lista de Productos Filtrado!!!", txtProducto);
        }
    }

    private void muestraProductosAlternativos() {
        vEjecutaAccionTeclaListado = false;
        int vFila = tblProductos.getSelectedRow();

        VariablesVentas.vCodProdOrigen_Alter = FarmaUtility.getValueFieldJTable(tblProductos, vFila, COL_COD);
        //VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(tblProductos,vFila,1);
        VariablesVentas.vDesc_Prod = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 2);
        VariablesVentas.vNom_Lab = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 4);
        VariablesVentas.vUnid_Vta = lblUnidad.getText();

        DlgProductosAlternativos dlgProductosAlternativos = new DlgProductosAlternativos(myParentFrame, "", true);
        dlgProductosAlternativos.setVisible(true);
        if (FarmaVariables.vAceptar) {
            VariablesVentas.vIndDireccionarResumenPed = true;
            aceptaOperacion();
            /*log.debug("Se refresca el listado temporal");
      FarmaVariables.vAceptar = false;

      FarmaUtility.ponerCheckJTable(tblProductos,COL_COD,VariablesVentas.vArrayList_PedidoVenta,0);
      FarmaUtility.ponerCheckJTable(tblProductos,COL_COD,VariablesVentas.vArrayList_Prod_Promociones_temporal,0);
      FarmaUtility.ponerCheckJTable(tblProductos,COL_COD,VariablesVentas.vArrayList_Prod_Promociones,0);

      colocaTotalesPedido();

      setJTable(tblProductos);
      FarmaUtility.setearPrimerRegistro(tblProductos,txtProducto,2);
      actualizaListaProductosAlternativos();
      muestraNombreLab(4, lblDescLab_Prod);
      muestraProductoInafectoIgv(11, lblProdIgv);
      muestraProductoPromocion(17,lblProdProm);
      muestraProductoRefrigerado(15,lblProdRefrig);
      muestraIndTipoProd(16,lblIndTipoProd);
      muestraInfoProd();
      muestraProductoCongelado(lblProdCong);*/

        } else {
            VariablesVentas.vIndDireccionarResumenPed = false;
        }

    }

    private void actualizaListaProductosAlternativos() {
        tblModelListaSustitutos.clearTable();
        tblModelListaSustitutos.fireTableDataChanged();
        //ERIOS 09.04.2008 Se muestra los sustitutos para todos los productos.
        //if(esProductoFarma()){
        //muestraProductosAlternativos();
        muestraProductosSustitutos();
        FarmaUtility.ponerCheckJTable(tblListaSustitutos, COL_COD, VariablesVentas.vArrayList_PedidoVenta, 0);
        FarmaUtility.ponerCheckJTable(tblListaSustitutos, COL_COD,
                                      VariablesVentas.vArrayList_Prod_Promociones_temporal, 0);
        FarmaUtility.ponerCheckJTable(tblListaSustitutos, COL_COD, VariablesVentas.vArrayList_Prod_Promociones, 0);
        //}
    }

    private void pintaCheckOtroJTable(JTable pActualJTable, Boolean pValor) {
        //log.debug(pValor.booleanValue());
        if (pActualJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS)) {
            FarmaUtility.setearCheckInRow(tblListaSustitutos, pValor, true, true, VariablesVentas.vCod_Prod, COL_COD);
            tblListaSustitutos.repaint();
        } else if (pActualJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_SUSTITUTOS)) {
            FarmaUtility.setearCheckInRow(tblProductos, pValor, true, true, VariablesVentas.vCod_Prod, COL_COD);
            tblProductos.repaint();
        }
    }

    private void muestraProductosComplementarios() {
        if (myJTable.getRowCount() == 0)
            return;

        int vFila = myJTable.getSelectedRow();
        VariablesVentas.vCodProdOrigen_Comple = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
        VariablesVentas.vDescProdOrigen_Comple = ((String)(myJTable.getValueAt(vFila, 2))).trim();

        DlgProductosComplementarios dlgProductosComplementarios =
            new DlgProductosComplementarios(myParentFrame, "", true);
        dlgProductosComplementarios.setVisible(true);
        if (FarmaVariables.vAceptar) {
            log.debug("VariablesVentas.vCodProdComplementario : " + VariablesVentas.vCodProdComplementario);
            btnRelacionProductos.doClick();
            buscaCodigoEnJtable(VariablesVentas.vCodProdComplementario);
            FarmaVariables.vAceptar = false;
            VariablesVentas.vIndDireccionarResumenPed = true;
        } else
            VariablesVentas.vIndDireccionarResumenPed = false;
    }

    private void buscaCodigoEnJtable(String pCodBusqueda) {
        String codTmp = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            codTmp = FarmaUtility.getValueFieldJTable(myJTable, i, COL_COD);
            if (codTmp.equalsIgnoreCase(pCodBusqueda)) {
                FarmaGridUtils.showCell(tblProductos, i, 0);
                FarmaUtility.setearRegistro(tblProductos, i, txtProducto, 2);
                FarmaUtility.moveFocus(txtProducto);
                return;
            }
        }
    }

    private void evaluaTitulo() {
        if (VariablesVentas.vEsPedidoDelivery) {
            this.setTitle("Lista de Productos y Precios - Pedido Delivery" + " /  IP : " + FarmaVariables.vIpPc);
        } else if (VariablesVentas.vEsPedidoInstitucional) {
            lblCliente.setText("");
            this.setTitle("Lista de Productos y Precios - Pedido Institucional" + " /  IP : " + FarmaVariables.vIpPc);
        } else if (VariablesVentas.vEsPedidoConvenio) {
            lblCliente.setText("");
            lblCliente.setText(VariablesConvenio.vTextoCliente);
            this.setTitle("Lista de Productos y Precios - Pedido por Convenio: " + VariablesConvenio.vNomConvenio +
                          " /  IP : " + FarmaVariables.vIpPc);
        } else if (VariablesConvenioBTLMF.vCodConvenio != null &&
                   VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1) {
            lblCliente.setText("");
            lblCliente.setText(VariablesConvenioBTLMF.vNomCliente);
            this.setTitle("Lista de Productos y Precios - Pedido por Convenio: " +
                          VariablesConvenioBTLMF.vNomConvenio + " /  IP : " + FarmaVariables.vIpPc);
            
            //INI JMONZALVE 24042019
            try {
                if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                    if(DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                        String msjPto = "";
                        if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && 
                           VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!=null){
                            msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
                        }
                        lblCliente.setText(msjPto + VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente +
                                           " " + VariablesFidelizacion.vApeMatCliente);
                    }else{
                        VariablesFidelizacion.vNumTarjeta = "";
                        VariablesFidelizacion.limpiaVariables();
                    }
                }
            } catch (SQLException ex) {
                log.error("Error al verificar activacion de convenio Cobro por Responsabilidad: " + ex.getMessage());
            }
            //FIN JMONZALVE 24042019
            
        } else {
            VariablesConvenio.vCodConvenio = "";
            VariablesConvenio.vArrayList_DatosConvenio.clear();
            // Solo si el no se ingreso tarjeta
            if (VariablesFidelizacion.vNumTarjeta.trim().length() == 0)
                lblCliente.setText("");
            if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0){
                String msjPto = "";
                if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && 
                VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!=null){
                    msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
                }
                lblCliente.setText(msjPto+VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente +
                                   " " + VariablesFidelizacion.vApeMatCliente);
            }
            
            this.setTitle("Lista de Productos y Precios" + " /  IP : " + FarmaVariables.vIpPc + " / " + VariablesPtoVenta.tituloBaseFrame);
        }
    }

    private void evaluaSeleccionMedico() {
        if (VariablesVentas.vSeleccionaMedico) {
            lblF8.setText("[F8] Des. Medico");
        } else {
            lblF8.setText("[F8] Sel. Medico");
        }
    }

    private boolean esProductoFarma() {
        int vFila = myJTable.getSelectedRow();
        String indicador = FarmaUtility.getValueFieldJTable(myJTable, vFila, 12);

        if (indicador.equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }
    // Inicio Adicion Delivery 28/04/2006 Paulo
    //JCORTEZ  07.08.09 Aun falta completar funcionalidad de mantenimiento de clientes.

    private void generarPedidoDelivery() {
        DlgListaClientes dlgListaClientes = new DlgListaClientes(myParentFrame, "", true);
        dlgListaClientes.setVisible(true);
        log.debug("Indicador Pedido Delivery-->" + VariablesVentas.vEsPedidoDelivery);
        if (FarmaVariables.vAceptar) {
            String nombreCliente =
                VariablesDelivery.vNombreCliente + " " + VariablesDelivery.vApellidoPaterno + " " + VariablesDelivery.vApellidoMaterno;
            lblCliente.setText(nombreCliente);
            log.debug("************DATOS CLIENTE DELIVERY*******");
            VariablesDelivery.vNombreCliente = nombreCliente; //Se setea el valor mostrado en pantalla
            log.debug("CodCliente: " + VariablesDelivery.vCodCli);
            log.debug("Nombre: " + VariablesDelivery.vNombreCliente);
            log.debug("Nunero Telf: " + VariablesDelivery.vNumeroTelefonoCabecera);
            log.debug("Direccion: " + VariablesDelivery.vDireccion);
            log.debug("Nro Documento: " + VariablesDelivery.vNumeroDocumento);
            FarmaVariables.vAceptar = false;
            VariablesVentas.vEsPedidoDelivery = true;
            FarmaUtility.moveFocus(txtProducto);
            log.debug("Indicador Pedido Delivery-->" + VariablesVentas.vEsPedidoDelivery);
        } else {
            if (FarmaVariables.vImprTestigo.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                evaluaTitulo();
                VariablesVentas.vEsPedidoDelivery = false;
            }
        }
    }

    //inicio adicion Paulo 15/06/2006

    private void faltacero() {
        vEjecutaAccionTeclaListado = false;
        if (!UtilityVentas.validaStockDisponible(stkProd)) {
            int vFila = myJTable.getSelectedRow();
            VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);

            if (!isExistProdListaFaltaCero(VariablesVentas.vListaProdFaltaCero, VariablesVentas.vCod_Prod)) {
                try {
                    DBVentas.ingresaStockCero();
                    FarmaUtility.aceptarTransaccion();
                    VariablesVentas.vListaProdFaltaCero.add(VariablesVentas.vCod_Prod);
                    FarmaUtility.showMessage(this, "Se ha Registrado el Producto para reposicion", txtProducto);
                } catch (SQLException sqlException) {
                    FarmaUtility.liberarTransaccion();
                    if (sqlException.getErrorCode() == 00001) {
                        FarmaUtility.showMessage(this, "Ya registr� el producto con Falta Cero.!", txtProducto);
                    } else
                        FarmaUtility.showMessage(this, "Error al insertar en la tabla falta stock.\n" +
                                sqlException, txtProducto);
                    log.error(null, sqlException);
                }
            } else {
                FarmaUtility.showMessage(this, "Ya registr� el producto con Falta Cero!", txtProducto);
            }
        }
    }
    //fin adicion Paulo 15/06/2006
    //inicio adicion paulo 23/06/2006

    private void muestraBuscaMedico() {
        DlgBuscaMedico dlgBuscaMedico = new DlgBuscaMedico(myParentFrame, "", true);
        dlgBuscaMedico.setVisible(true);
    }

    /**
     * Se selecciona el medico y graba receta.
     * @author Edgar Rios Navarro
     * @since 06.12.2006
     */
    private void ingresaReceta() {

        vEjecutaAccionTeclaListado = false;
        if (!VariablesVentas.vSeleccionaMedico) {
            if (validaSeleccionProductos()) {
                muestraBuscaMedico();
                if (FarmaVariables.vAceptar) {
                    lblMedico.setText(VariablesVentas.vNombreListaMed);
                    evaluaSeleccionMedico();
                    cargarReceta();
                } else {
                    limpiaMedico();
                }
            }
        } else {
            if (JConfirmDialog.rptaConfirmDialog(this,
                                                 "�Est� seguro de quitar la referencia a la receta ingresada?")) {
                limpiaMedico();
                evaluaSeleccionMedico();
            }
        }
    }

    /**
     * Se verifica que no haya productos seleccionados.
     * @return <b>true</b> si no hay productos seleccionados.
     * @author Edgar Rios Navarro
     * @since 06.12.2006
     */
    private boolean validaSeleccionProductos() {
        boolean retorno = true;
        if (VariablesVentas.vArrayList_PedidoVenta.size() > 0) {
            FarmaUtility.showMessage(this, "Existen Productos Seleccionados. Para ingresar una Receta Medica\n" +
                    "no deben haber productos seleccionados. Verifique!!!", txtProducto);
            retorno = false;
        } else if (UtilityCalculoPrecio.getSizeDetalleVenta() > 0) {
            FarmaUtility.showMessage(this,
                                     "Existen Productos Seleccionados en el Resumen de Pedido. Para ingresar una Receta Medica\n" +
                    "no deben haber productos seleccionados. Verifique!!!", txtProducto);
            retorno = false;
        }
        return retorno;
    }

    /**
     * Se revierte la seleccion del medico y la receta generada.
     * @author Edgar Rios Navarro
     * @since 06.12.2006
     */
    private void limpiaMedico() {
        lblMedico.setText("");
        VariablesVentas.vArrayList_Medicos.clear();
        VariablesVentas.vCodListaMed = "";
        VariablesVentas.vMatriListaMed = "";
        VariablesVentas.vNombreListaMed = "";
        VariablesCentroMedico.vArrayList_PedidoReceta = new ArrayList();
        VariablesCentroMedico.vArrayList_ResumenReceta = new ArrayList();
        VariablesCentroMedico.vNum_Ped_Rec = "";
        VariablesVentas.vSeleccionaMedico = false;
        VariablesCentroMedico.vVerReceta = false;
    }

    /**
     * Se muestra los productos de la receta, en el resumen de pedido.
     * @author Edgar Rios Navarro
     * @since 06.12.2006
     */
    private void cargarReceta() {
        if (VariablesCentroMedico.vArrayList_ResumenReceta.size() <= 0) {
            log.warn("No hay productos");
        } else {
            ArrayList arrayRow;
            for (int i = 0; i < VariablesCentroMedico.vArrayList_ResumenReceta.size(); i++) {
                try {
                    arrayRow = (ArrayList)VariablesCentroMedico.vArrayList_ResumenReceta.get(i);
                    //log.debug(arrayRow);
                    VariablesVentas.vCod_Prod = arrayRow.get(0).toString().trim();
                    VariablesVentas.vDesc_Prod = arrayRow.get(1).toString().trim();
                    VariablesVentas.vNom_Lab = arrayRow.get(9).toString().trim();
                    VariablesVentas.vPorc_Igv_Prod = arrayRow.get(11).toString().trim();
                    VariablesVentas.vCant_Ingresada = arrayRow.get(4).toString().trim();
                    VariablesCentroMedico.vVal_Frac = arrayRow.get(10).toString().trim();
                    VariablesVentas.vIndOrigenProdVta = ConstantsVentas.IND_ORIGEN_REC;
                    VariablesVentas.vPorc_Dcto_2 = "0";
                    log.info("******JCALLO****** CAMPO INDICADOR TRATAMIENTO :" + arrayRow.get(13).toString().trim());
                    //VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
                    VariablesVentas.vIndTratamiento = arrayRow.get(13).toString().trim();
                    if (VariablesVentas.vIndTratamiento.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                        VariablesVentas.vTotalPrecVtaTra = Double.parseDouble(arrayRow.get(7).toString().trim());
                        VariablesVentas.vCantxDia = "1";
                        VariablesVentas.vCantxDias = "" + VariablesVentas.vCant_Ingresada;
                    } else {
                        VariablesVentas.vCantxDia = "";
                        VariablesVentas.vCantxDias = "";
                    }

                    muestraInfoProd2();

                    if (!UtilityVentas.validaStockDisponible(stkProd))
                        continue;
                    if (!UtilityVentas.validaProductoTomaInventario(indProdCong))
                        continue;
                    if (!UtilityVentas.validaProductoHabilVenta())
                        continue;

                    validaStockActual2();
                    seleccionaProducto2();
                } catch (SQLException e) {
                    FarmaUtility.liberarTransaccion();
                    //log.error("",e);
                    log.error(null, e);
                }
            }
            VariablesVentas.vIndDireccionarResumenPed = true;
            aceptaOperacion();
        }
    }

    /**
     * Se obtiene informacion detallada del producto seleccionado en la receta.
     * @throws SQLException
     * @author Edgar Rios Navarro
     * @since 12.12.2006
     */
    private void muestraInfoProd2() throws SQLException {
        ArrayList myArray = new ArrayList();
        DBVentas.obtieneInfoProducto(myArray, VariablesVentas.vCod_Prod);
        if (myArray.size() == 1) {
            stkProd = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
            valFracProd = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
            indProdCong = ((String)((ArrayList)myArray.get(0)).get(4)).trim();
            indProdHabilVta = ((String)((ArrayList)myArray.get(0)).get(7)).trim();
        } else {
            stkProd = "";
            valFracProd = "";
            indProdCong = "";
            indProdHabilVta = "";
            log.warn("Error al obtener Informacion del Producto");
            throw new SQLException("Error al obtener Informacion del Producto");
        }
        VariablesVentas.vVal_Frac = valFracProd;
        VariablesVentas.vInd_Prod_Habil_Vta = indProdHabilVta;

        myArray = new ArrayList();
        DBVentas.obtieneInfoDetalleProducto(myArray, VariablesVentas.vCod_Prod);
        if (myArray.size() == 1) {
            VariablesVentas.vUnid_Vta = ((String)((ArrayList)myArray.get(0)).get(4)).trim();
            VariablesVentas.vVal_Bono = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
            VariablesVentas.vVal_Prec_Lista = ((String)((ArrayList)myArray.get(0)).get(7)).trim();
        } else {
            VariablesVentas.vUnid_Vta = "";
            VariablesVentas.vVal_Bono = "";
            VariablesVentas.vVal_Prec_Lista = "";
            log.warn("Error al obtener Detalle del Producto");
            throw new SQLException("Error al obtener Detalle del Producto");
        }
    }

    /**
     * Se verifica que la cantidad seleccionada en la receta
     * no se mayor que el stock del producto.
     * @throws SQLException
     * @author Edgar Rios Navarro
     * @since 12.12.2006
     */
    private void validaStockActual2() throws SQLException {
        obtieneStockProducto2();
        int cantReceta = Integer.parseInt(VariablesVentas.vCant_Ingresada);
        int fracReceta = Integer.parseInt(VariablesCentroMedico.vVal_Frac);
        int fracProd = Integer.parseInt(VariablesVentas.vVal_Frac);
        int cantIngreso = (cantReceta * fracProd) / fracReceta;
        if ((Integer.parseInt(VariablesVentas.vStk_Prod) + 0) < (cantIngreso))
            VariablesVentas.vCant_Ingresada = VariablesVentas.vStk_Prod;
        else
            VariablesVentas.vCant_Ingresada = String.valueOf(cantIngreso);
    }

    /**
     * Se obtiene el stock actual del producto.
     * @throws SQLException
     * @author Edgar Rios Navarro
     * @since 12.12.2006
     */
    private void obtieneStockProducto2() throws SQLException {

        ArrayList myArray = new ArrayList();
        DBVentas.obtieneStockProducto_ForUpdate(myArray, VariablesVentas.vCod_Prod, VariablesVentas.vVal_Frac);
        FarmaUtility.liberarTransaccion();
        //quitar bloqueo de stock fisico
        //dubilluz 13.10.2011
        if (myArray.size() == 1) {
            VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
            VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
            VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
        } else {
            log.warn("Error al obtener Stock del Producto");
            throw new SQLException("Error al obtener Stock del Producto");
        }
    }

    /**
     * Se agrega el producto, en el pedido de venta.
     * @author Edgar Rios Navarro
     * @since 12.12.2006
     */
    private void seleccionaProducto2() {
        VariablesVentas.vTotalPrecVtaProd =
                (FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada) * FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta));
        VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
        if ( /*!UtilityVentas.actualizaStkComprometidoProd(VariablesVentas.vCod_Prod,
                                      Integer.parseInt(VariablesVentas.vCant_Ingresada),
                                      ConstantsVentas.INDICADOR_A,
                                      ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR,
                                      Integer.parseInt(VariablesVentas.vCant_Ingresada),
                                                    true,
                                                    this,
                                                    txtProducto))*/
            !UtilityVentas.operaStkCompProdResp(VariablesVentas.vCod_Prod,
                //ASOSA, 01.07.2010
                Integer.parseInt(VariablesVentas.vCant_Ingresada), ConstantsVentas.INDICADOR_A,
                ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR, Integer.parseInt(VariablesVentas.vCant_Ingresada),
                true, this, txtProducto, ""))
            return;

        VariablesVentas.vNumeroARecargar = ""; //NUMERO TELEFONICO SI ES RECARGA AUTOMATICA
        VariablesVentas.vIndProdVirtual = FarmaConstants.INDICADOR_N; //INDICADOR DE PRODUCTO VIRTUAL
        VariablesVentas.vTipoProductoVirtual = ""; //TIPO DE PRODUCTO VIRTUAL
        VariablesVentas.vIndProdControlStock =
                true; //? FarmaConstants.INDICADOR_S : FarmaConstants.INDICADOR_N);//INDICADOR PROD CONTROLA STOCK
        VariablesVentas.vVal_Prec_Lista_Tmp = ""; //PRECIO DE LISTA ORIGINAL SI ES QUE SE MODIFICO
        VariablesVentas.vVal_Prec_Pub = VariablesVentas.vVal_Prec_Vta;

        Boolean valor = new Boolean(true);
        UtilityVentas.operaProductoSeleccionadoEnArrayList_02(valor, VariablesVentas.secRespStk); //ASOSA, 06.07.2010
        colocaTotalesPedido();
        //FarmaUtility.aceptarTransaccion();
    }

    /**
     * Evalua si el producto es del Tipo Virtual y si se puede seleccionar.
     * @author Luis Mesia Rivera
     * @since 05.01.2007
     */
    private void evaluaTipoProdVirtual() {
        //ERIOS 20.05.2013 Tratamiento de Producto Virtual - Magistral

        int row = myJTable.getSelectedRow();
        String tipoProdVirtual = FarmaUtility.getValueFieldJTable(myJTable, row, 14);

        if (tipoProdVirtual.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_RECARGA)) {
            muestraIngresoTelefonoMonto();
        } else if (tipoProdVirtual.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_TARJETA)) {
            int cantidad_ingresada = muestraIngresoCantidad_Tarjeta_Virtual();
            if (cantidad_ingresada != 0) {
                seleccionaProductoTarjetaVirtual(cantidad_ingresada + " ");
                String valorTarj =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta), 2);
                FarmaUtility.showMessage(this,
                                         "Se ha seleccionado una tarjeta virtual " + VariablesVentas.vDesc_Prod + " de "+ConstantesUtil.simboloSoles+" " +
                                         valorTarj, txtProducto);
            }
        } else if (tipoProdVirtual.equalsIgnoreCase(ConstantsVentas.TIPO_PROD_VIRTUAL_MAGISTRAL)) {
            muestraIngresoRecetarioMagistral();
        }
    }

    /**o no aca nada es
     * Muestra pantalla ingreso de numero telefonico y monto de recarga
     * @author Luis Mesia Rivera
     * @since 05.01.2007
     */
    private void muestraIngresoTelefonoMonto() {
        if (myJTable.getRowCount() == 0)
            return;

        int row = myJTable.getSelectedRow();
        VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, COL_COD);
        VariablesVentas.vDesc_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, 2);
        VariablesVentas.vNom_Lab = FarmaUtility.getValueFieldJTable(myJTable, row, 4);
        VariablesVentas.vUnid_Vta = FarmaUtility.getValueFieldJTable(myJTable, row, 3);
        VariablesVentas.vVal_Prec_Lista = FarmaUtility.getValueFieldJTable(myJTable, row, 10);
        //VariablesVentas.vPorc_Dcto_1 = FarmaUtility.getValueFieldJTable(myJTable, row, 2);
        VariablesVentas.vVal_Bono = FarmaUtility.getValueFieldJTable(myJTable, row, 7);
        VariablesVentas.vPorc_Igv_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, 11);
        VariablesVentas.vTipoProductoVirtual = FarmaUtility.getValueFieldJTable(myJTable, row, 14);
        VariablesVentas.vMontoARecargar_Temp = "0";
        VariablesVentas.vNumeroARecargar = "";
        VariablesVentas.vIndOrigenProdVta = FarmaUtility.getValueFieldJTable(myJTable, row, COL_ORIG_PROD);
        VariablesVentas.vPorc_Dcto_2 = "0";
        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
        VariablesVentas.vCantxDia = "";
        VariablesVentas.vCantxDias = "";

        //mfajardo 29/04/09 validar ingreso de productos virtuales
        VariablesVentas.vProductoVirtual = true;

        DlgIngresoRecargaVirtual dlgIngresoRecargaVirtual = new DlgIngresoRecargaVirtual(myParentFrame, "", true);
        dlgIngresoRecargaVirtual.setVisible(true);
        if (FarmaVariables.vAceptar) {
            VariablesVentas.vVal_Prec_Lista_Tmp = VariablesVentas.vVal_Prec_Lista;
            //VariablesVentas.vVal_Prec_Vta = FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vMontoARecargar));
            //Ahora se grabara S/1.00
            //31.10.2007 dubilluz modificacion
            VariablesVentas.vVal_Prec_Vta =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(ConstantsVentas.PrecioVtaRecargaTarjeta));
            VariablesVentas.vVal_Prec_Lista =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Lista) *
                                              FarmaUtility.getDecimalNumber(VariablesVentas.vMontoARecargar));
            seleccionaProducto();
            log.debug("VariablesVentas.secRespStkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " +
                      VariablesVentas.secRespStk);
            //Actualizando el Indicador de la Venta de Tarjeta Virtual Recarga
            VariablesVentas.venta_producto_virtual = true;
            VariablesVentas.vIndDireccionarResumenPed = true;
            FarmaVariables.vAceptar = false;
        } else {
            VariablesVentas.vIndDireccionarResumenPed = false;
            VariablesVentas.vProductoVirtual = false; //ASOSA 01.02.2010
        }

    }

    /**
     * Selecciona el producto y le asigna la cantidad por defecto 1
     * @author Luis Mesia Rivera
     * @since 05.01.2007
     */
    private void seleccionaProductoTarjetaVirtual(String cantidad) {
        if (myJTable.getRowCount() == 0)
            return;

        int row = myJTable.getSelectedRow();
        VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, COL_COD);
        VariablesVentas.vDesc_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, 2);
        VariablesVentas.vNom_Lab = FarmaUtility.getValueFieldJTable(myJTable, row, 4);
        VariablesVentas.vUnid_Vta = FarmaUtility.getValueFieldJTable(myJTable, row, 3);
        VariablesVentas.vVal_Prec_Lista = FarmaUtility.getValueFieldJTable(myJTable, row, 10);
        //VariablesVentas.vPorc_Dcto_1 = FarmaUtility.getValueFieldJTable(myJTable, row, 2);
        VariablesVentas.vVal_Bono = FarmaUtility.getValueFieldJTable(myJTable, row, 7);
        VariablesVentas.vPorc_Igv_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, 11);
        VariablesVentas.vVal_Prec_Vta = FarmaUtility.getValueFieldJTable(myJTable, row, 6);
        VariablesVentas.vTipoProductoVirtual = FarmaUtility.getValueFieldJTable(myJTable, row, 14);
        VariablesVentas.vCant_Ingresada = cantidad.trim(); //"1";
        VariablesVentas.vNumeroARecargar = "";
        VariablesVentas.vVal_Prec_Lista_Tmp = "";
        VariablesVentas.vIndOrigenProdVta = FarmaUtility.getValueFieldJTable(myJTable, row, COL_ORIG_PROD);
        VariablesVentas.vPorc_Dcto_2 = "0";
        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
        VariablesVentas.vCantxDia = "";
        VariablesVentas.vCantxDias = "";

        seleccionaProducto();
        //Actualizando el Indicador de la Venta de Tarjeta Virtual
        VariablesVentas.venta_producto_virtual = true;
    }

    /**
     * Muestra el dialogo de convenios.
     * @author Edgar Rios Navarro
     * @since 24.05.2007
     */


    //ini Agregado FRC
    private void cargarConvenioBTL() throws Throwable{

        log.debug("-Exe F3 Convenio Nuevo MF - BTL -");
        if(UtilityPuntos.isBloqueaVtaConvenio()){
            if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
                FarmaUtility.showMessage(this,
                                         "No puede elegir un convenio!!!.\n Porque se ha asociado una tarjeta de fidelizacion.",
                                         txtProducto);
                return;
            }
        }

        if (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1) {
            if (JConfirmDialog.rptaConfirmDialog(this, "�Esta seguro de cancelar el pedido por Convenio?")) {
                //INI JMONZALVE 24042019
                try {
                    if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                        lblF12.setVisible(true);//Se habilita campo ya que se esta anulando la venta por convenio
                    }
                } catch (SQLException e) {
                }
                //FIN JMONZALVE 24042019
                VariablesConvenioBTLMF.limpiaVariablesBTLMF();
                if (VariablesVentas.vArrayList_PedidoVenta.size()   > 0 ||
                    // DUBILLUZ 30.04.2015
                    UtilityCalculoPrecio.getSizeDetalleVenta() > 0  ||
                    VariablesVentas.vArrayList_Prod_Promociones.size() > 0  
                ) {
                    FarmaUtility.showMessage(this,
                                             "Existen Productos Seleccionados. Para realizar un Pedido Mostrador\n" +
                            "no deben haber productos seleccionados. Verifique!!!", txtProducto);

                } else {
                    lblCliente.setText("");
                    lblMedico.setText("");
                    this.setTitle("Lista de Productos y Precios" + " /  IP : " + FarmaVariables.vIpPc);
                    evaluaTitulo();
                    //jquispe 25.07.2011 se agrego la funcionalidad de listar las campa�as sin fidelizar
                    UtilityFidelizacion.operaCampa�asFidelizacion(" ", VariablesConvenioBTLMF.vCodConvenio);
                }
            }
        } else {

            if (VariablesVentas.vArrayList_PedidoVenta.size() > 0||
                    // DUBILLUZ 30.04.2015
                    UtilityCalculoPrecio.getSizeDetalleVenta() > 0  ||
                    VariablesVentas.vArrayList_Prod_Promociones.size() > 0) {
                FarmaUtility.showMessage(this, "Existen Productos Seleccionados. Para iniciar un pedido convenio\n" +
                        "no deben haber productos seleccionados. Verifique!!!", txtProducto);

            } else {
                log.debug("-Ejecutando F3 VTA CONVENIO BTLMFARMA-");
                /*** INICIO ARAVELLO 11/10/2019 ***/
                
                
                DlgListaConveniosBTLMF.iniciarVariablesGlobales();
//                VariablesConvenioBTLMF.vValorSelCopago = 80;
//                VariablesConvenio.vPorcCoPago = String.valueOf(VariablesConvenioBTLMF.vValorSelCopago);
                DlgListaConveniosBTLMF.guardaRegistroConvenio(this, myParentFrame);
                VariablesConvenioBTLMF.vCodCliente = VariablesRefacturadorElectronico.vComprobanteActual.getCodCliente();
                VariablesConvenioBTLMF.vNomCliente = VariablesRefacturadorElectronico.vComprobanteActual.getNomCliente();
                FarmaVariables.vAceptar = true;
                VariablesConvenioBTLMF.vAceptar = true;
                //            cargaPantallaCopago();
                /*** FIN    ARAVELLO 11/10/2019 ***/
//                DlgListaConveniosBTLMF loginDlg = new DlgListaConveniosBTLMF(myParentFrame, "", true);
//                loginDlg.setVisible(true);
//                log.debug("Cerramos Convenios" + VariablesConvenioBTLMF.vAceptar + "-" +
//                          VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF);

                if (VariablesConvenioBTLMF.vAceptar) {


                    VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF = true;
                    //11-DIC-13  TCT Begin
                    log.debug("<<TCT 121>>VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF=>" +
                              VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF);
                    //FarmaUtility.showMessage(this, "Ingreso a Convenio", null);
                    //11-DIC-13  TCT End

                    //ImageIcon icon = new ImageIcon(this.getClass().getResource("logo_mf_btl.JPG"));
                    UtilityConvenioBTLMF.cargarVariablesConvenio(VariablesConvenioBTLMF.vCodConvenio, this,
                                                                 myParentFrame);

                    log.debug("VariablesConvenioBTLMF.vCodConvenio:" + VariablesConvenioBTLMF.vCodConvenio);
                    log.debug("VariablesConvenioBTLMF.vNomConvenio:" + VariablesConvenioBTLMF.vNomConvenio);

                    /*
                            this.setTitle("Lista de Productos y Precios      "+nombConvenio);
                            lblMedicoT.setText(" ");
                            //lblMedicoT.setIcon(icon);
                            lblMedico.setText(" "+nombConvenio);
                            lblCliente.setText(VariablesConvenioBTLMF.vNomCliente);
                            */

                } else {
                    VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF = false;
                }

                evaluaTitulo();
                //SE SELECCIONO CONVENIO
                if (VariablesConvenioBTLMF.vCodConvenio != null &&
                    VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1) {
                    //si se selecciono convenios debe de quitar campa�as de fid. automaticas
                    //KMONCADA 20.10.2016 [CAMPA�AS CON CONVENIOS]
                    /*
                    VariablesFidelizacion.vListCampa�asFidelizacion = new ArrayList();
                    VariablesVentas.vArrayList_Cupones = new ArrayList();
                    */
                    UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);
                    //INI JMONZALVE 24042019
                    try {
                        if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                            if(!DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                                lblF12.setVisible(false);
                            }
                        }
                    } catch (SQLException e) {
                    }
                    //FIN JMONZALVE 24042019
                }
            }
        }
    }


    /**
     * Muestra el Dialogo de Promociones por Producto
     * @author Dubilluz
     * @since  15.06.2007
     */
    private void muestraPromocionProd(String codigo) {
        //codigo es el cod del producto q se utilizara
        //para cargar las promociones q se encuentran en la promocion
        VariablesVentas.vCodProdFiltro = codigo;
        //String indPromocion=(String)myJTable.getValueAt(myJTable.getSelectedRow(),17);
        //  if(indPromocion.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) return;
        // else{
        //valida si esta en ArrayListadeProd promocion
        //falta aun
        //KMONCADA 10.08.2015 VALIDARA SI EXISTE CLIENTE FIDELIZADO O NO
        boolean isClienteFidelizado = false;
        if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta()!= null){
            isClienteFidelizado = true;
        }else{
            if( DBVentas.getAplicaFidelizadosNoMonedero() && (VariablesFidelizacion.vNumTarjeta != null && VariablesFidelizacion.vNumTarjeta.trim().length() > 0))
            isClienteFidelizado = true;
        }
        DlgListaPromocion dlgListPromocion = new DlgListaPromocion(myParentFrame, "", true, isClienteFidelizado);
        VariablesVentas.vIngresaCant_ResumenPed = false;
        dlgListPromocion.setVisible(true);

        log.debug("Detalle Promocion" + VariablesVentas.vArrayList_Prod_Promociones);
        if (FarmaVariables.vAceptar) {
            seleccionaProductoPromocion();
            VariablesVentas.vIndDireccionarResumenPed = true;
            FarmaVariables.vAceptar = false;
            aceptaOperacion();

            //JCHAVEZ 29102009 inicio
            try {
                VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
            } catch (SQLException ex) {
                log.error("", ex);
            }
            if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
                    String codProd =
                        FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones, i,
                                                            0).toString();
                    double precioUnit =
                        FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones,
                                                                                          i, 3));
                    double precioVentaUnit =
                        FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones,
                                                                                          i, 6));
                    double precioVtaTotal =
                        FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones,
                                                                                          i, 7));
                    double cantidad =
                        FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones,
                                                                                          i, 4));
                    log.debug("precioVtaTotal: " + precioVtaTotal);
                    log.debug("precioVentaUnit: " + precioVentaUnit);
                    try {
                        double precioVtaTotalRedondeado = DBVentas.getPrecioRedondeado(precioVtaTotal);
                        double precioVentaUnitRedondeado = precioVtaTotalRedondeado / cantidad;
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
                        String cprecioVentaUnitRedondeado =
                            FarmaUtility.formatNumber(pAux5); /*precioVentaUnitRedondeado,3*/
                        String cprecioUnitRedondeado = FarmaUtility.formatNumber(precioUnitRedondeado, 3);
                        ((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i)).set(3, cprecioUnitRedondeado);
                        ((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i)).set(6,
                                                                                            cprecioVentaUnitRedondeado);
                        ((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i)).set(7,
                                                                                            cprecioVtaTotalRedondeado);
                        log.debug("precioVtaTotalRedondeado: " + "" + cprecioVtaTotalRedondeado);
                        log.debug("precioVentaUnitRedondeado: " + cprecioVentaUnitRedondeado);
                        log.debug("precioUnitRedondeado: " + cprecioUnitRedondeado);

                    } catch (SQLException ex) {
                        log.error("", ex);
                    }

                    log.debug("codProd: " + codProd);
                    log.debug("precioUnit: " + precioUnit);
                    log.debug("precioVentaUnit: " + precioVentaUnit);
                    log.debug("precioVtaTotal: " + precioVtaTotal);
                    log.debug("cantidad: " + cantidad);
                }
            }
            //JCHAVEZ 29102009 fin
        } else
            VariablesVentas.vIndDireccionarResumenPed = false;
        //  }
    }

    /**
     * Seleccionara los Productos  de la promocion
     * aceptada
     * @author dubilluz
     * @since  15.06.2007
     */
    private void seleccionaProductoPromocion() {
        int vFila = myJTable.getSelectedRow();
        Boolean valorTmp = (Boolean)(myJTable.getValueAt(vFila, 0));
        log.debug("<<TCT 4>>vArrayList_Promociones" + VariablesVentas.vArrayList_Promociones);
        log.debug("" + VariablesVentas.vArrayList_Prod_Promociones.size());
        log.debug("" + VariablesVentas.vArrayList_Prod_Promociones);
        FarmaUtility.ponerCheckJTable(myJTable, 1, VariablesVentas.vArrayList_Prod_Promociones_temporal, 0);
        String cod_prod_tem = "";
        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones_temporal.size(); i++) {
            cod_prod_tem =
                    ((String)((ArrayList)(VariablesVentas.vArrayList_Prod_Promociones_temporal.get(i))).get(0)).trim();
            if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS)) {
                FarmaUtility.setearCheckInRow(tblListaSustitutos, valorTmp, true, true, cod_prod_tem, 1);
                tblListaSustitutos.repaint();
            } else if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_SUSTITUTOS)) {
                FarmaUtility.setearCheckInRow(tblProductos, valorTmp, true, true, cod_prod_tem, 1);
                tblProductos.repaint();
            }
        }
        colocaTotalesPedido();
        //FarmaUtility.ponerCheckJTable(myJTable,1,VariablesVentas.vArrayList_Prod_Promociones,0);

    }


    /**
     * Establece un valor boolean para un objeto en una lista de selecci�n
     * @param pJTable
     * 				Tabla a operar
     * @param pCampoEnJTable
     * 				Campo en la tabla
     * @param pArrayList
     * 				Lista de campos
     * @param pCampoEnArrayList
     * 				Indice del campo en la lista
     */
    public static void quitarCheckJTable(JTable pJTable, int pCampoEnJTable, ArrayList pArrayList,
                                         int pCampoEnArrayList) {
        String myCodigo = "";
        String myCodigoTmp = "";
        for (int i = 0; i < pJTable.getRowCount(); i++) {
            myCodigo = ((String)pJTable.getValueAt(i, pCampoEnJTable)).trim();
            for (int j = 0; j < pArrayList.size(); j++) {
                myCodigoTmp = ((String)(((ArrayList)pArrayList.get(j)).get(pCampoEnArrayList))).trim();
                if (myCodigo.equalsIgnoreCase(myCodigoTmp))
                    pJTable.setValueAt(new Boolean(false), i, 0);
            }
        }
        pJTable.repaint();
    }

    /**
     * Cancela las Promociones Pedidos
     * @author dubilluz
     * @since  04.07.2007
     */
    private void cancela_promociones() {
        String codProm = "";
        String codProd = "";
        String cantidad = "";
        String indControlStk = "";
        ArrayList aux = new ArrayList();
        ArrayList array_promocion = new ArrayList();
        ArrayList prod_Prom = new ArrayList();

        Boolean valor = new Boolean(true);
        ArrayList agrupado = new ArrayList();
        ArrayList atemp = new ArrayList();
        //log.debug("");
        log.debug("!!!!>Promociona antes de Elimin " + VariablesVentas.vArrayList_Promociones_temporal.size());
        log.debug("!!!!Promociona eli " + VariablesVentas.vArrayList_Promociones_temporal);

        for (int j = 0; j < VariablesVentas.vArrayList_Promociones_temporal.size(); j++) {
            array_promocion = (ArrayList)(VariablesVentas.vArrayList_Promociones_temporal.get(j));
            codProm = ((String)(array_promocion.get(0))).trim();
            log.debug(">>>>Promociona eli " + codProm);
            codProd = "";
            cantidad = "";
            indControlStk = "";
            aux = new ArrayList();

            prod_Prom = new ArrayList();
            log.debug(">>>>**Detalles " + VariablesVentas.vArrayList_Prod_Promociones_temporal);
            prod_Prom = detalle_Prom(VariablesVentas.vArrayList_Prod_Promociones_temporal, codProm);
            log.debug(">>>>**detalle de la prmo  " + j + "  :" + prod_Prom);
            valor = new Boolean(true);
            agrupado = new ArrayList();
            atemp = new ArrayList();

            for (int i = 0; i < prod_Prom.size(); i++) {
                atemp = (ArrayList)(prod_Prom.get(i));
                FarmaUtility.operaListaProd(agrupado, (ArrayList)(atemp.clone()), valor, 0);
            }

            agrupado = agrupar(agrupado);
            log.debug(">>>>**Agrupado " + agrupado);
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
                log.debug(indControlStk);
                secRespaldo = ((String)(aux.get(24))).trim(); //ASOSA, 08.07.2010
                VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                    /*!UtilityVentas.actualizaStkComprometidoProd(codProd, //Antes, ASOSA, 01.07.2010
                                                     Integer.parseInt(cantidad),
                                                     ConstantsVentas.INDICADOR_D,
                                                     ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR,
                                                     Integer.parseInt(cantidad),
                                                     false,
                                                     this,
                                                     txtProducto))*/
                    !UtilityVentas.operaStkCompProdResp(codProd,
                        //ASOSA, 01.07.2010
                        Integer.parseInt(cantidad), ConstantsVentas.INDICADOR_D,
                        ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR, Integer.parseInt(cantidad), false, this,
                        txtProducto, secRespaldo))

                    return;
                //}
            }
            FarmaUtility.aceptarTransaccion();
            /*        removeItemArray(VariablesVentas.vArrayList_Promociones_temporal,codProm,0);
        removeItemArray(VariablesVentas.vArrayList_Prod_Promociones_temporal,codProm,18);*/
        }

        VariablesVentas.vArrayList_Promociones_temporal = new ArrayList();
        VariablesVentas.vArrayList_Prod_Promociones_temporal = new ArrayList();
        log.debug("Resultados despues de Eliminar");
        log.debug("Tama�o de Resumen   :" + UtilityCalculoPrecio.getSizeDetalleVenta() + "");
        log.debug("Tama�o de Promocion :" + VariablesVentas.vArrayList_Promociones_temporal.size() + "");
        log.debug("Tama�o de Prod_Promocion :" + VariablesVentas.vArrayList_Prod_Promociones_temporal.size() + "");

    }

    /* ************************************************************************** */
    /*                        Metodos Auxiliares                                  */
    /* ************************************************************************** */

    /**
     * Metodo que elimina items del array ,que sean iguales al paramtro
     * q se le envia , comaprando por campo
     * @author dubilluz
     * @since  20.06.2007
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
     * @author dubilluz
     * @since  03.07.2007
     */
    private ArrayList detalle_Prom(ArrayList array, String codProm) {
        ArrayList nuevo = new ArrayList();
        ArrayList aux = new ArrayList();
        String cod_p = "";
        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones_temporal.size(); i++) {
            aux = (ArrayList)(VariablesVentas.vArrayList_Prod_Promociones_temporal.get(i));
            cod_p = (String)(aux.get(18));
            log.debug("cod de detal" + cod_p + ">>>>" + codProm);

            if ((cod_p). /*.trim()*/equalsIgnoreCase(codProm)) {
                log.debug("si son iguales ");
                nuevo.add((ArrayList)(aux.clone()));
            }
        }
        return nuevo;
    }

    /**
     * Agrupa productos que esten en ambos paquetes
     * retorna el nuevoa arreglo
     * @author dubilluz
     * @since 27.06.2007
     */
    private ArrayList agrupar(ArrayList array) {
        ArrayList nuevo = new ArrayList();
        ArrayList aux1 = new ArrayList();
        ArrayList aux2 = new ArrayList();
        int cantidad1 = 0;
        int cantidad2 = 0;
        int suma = 0;
        for (int i = 0; i < array.size(); i++) {
            aux1 = (ArrayList)(array.get(i));
            if (aux1.size() < 23) { //(((String)(aux1.get(19))).trim()).equalsIgnoreCase("Revisado")){
                for (int j = i + 1; j < array.size(); j++) {
                    aux2 = (ArrayList)(array.get(j));
                    if (aux2.size() < 23) {
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

    private void cargaStockLocales() {
        if (myJTable.getRowCount() == 0)
            return;

        int vFila = myJTable.getSelectedRow();
        VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
        VariablesVentas.vDesc_Prod = ((String)(myJTable.getValueAt(vFila, 2))).trim();
        VariablesVentas.vNom_Lab = ((String)(myJTable.getValueAt(vFila, 4))).trim();
        VariablesVentas.vUnid_Vta = lblUnidad.getText();

        DlgStockLocales dlgStockLocales = new DlgStockLocales(myParentFrame, "", true);
        dlgStockLocales.setVisible(true);
    }

    /**
     * Muestra para un Producto de Tarjeta Virtual
     * @author dubilluz
     * @since  29.08.2007
     */
    private int muestraIngresoCantidad_Tarjeta_Virtual() {
        if (myJTable.getRowCount() == 0)
            return 0;

        ArrayList aux = new ArrayList();
        int vFila = myJTable.getSelectedRow();
        aux.add(FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD)); // codigo
        aux.add(FarmaUtility.getValueFieldJTable(myJTable, vFila, 2)); //descripcion
        aux.add(FarmaUtility.getValueFieldJTable(myJTable, vFila, 4)); //laboratorio
        aux.add(FarmaUtility.getValueFieldJTable(myJTable, vFila, 3)); //unidad
        aux.add(FarmaUtility.getValueFieldJTable(myJTable, vFila, 6)); //precio
        log.debug("DIEGO UBILLUZ >>  " + aux);

        VariablesVentas.vArrayList_Prod_Tarjeta_Virtual.clear();
        VariablesVentas.vArrayList_Prod_Tarjeta_Virtual = (ArrayList)aux.clone();
        //(myJTable.getSelectedRow())
        aux.clear();
        log.debug("DIEGO UBILLUZ >>  " + aux);
        DlgIngresoCantidadTarjetaVirtual dlgIngresoCantidad =
            new DlgIngresoCantidadTarjetaVirtual(myParentFrame, "", true);
        dlgIngresoCantidad.setVisible(true);

        if (FarmaVariables.vAceptar) {
            //seleccionaProducto();
            FarmaVariables.vAceptar = false;
            VariablesVentas.vIndDireccionarResumenPed = true;
            return VariablesVentas.cantidad_tarjeta_virtual;
        } else {
            VariablesVentas.vIndDireccionarResumenPed = false;
            return 0;
        }
    }

    /**
     * Se muestra los productos sustitutos
     * @author Edgar Rios Navarro
     * @since 09.04.2008
     */
    private void muestraProductosSustitutos() {
        try {
            int vFila = tblProductos.getSelectedRow();
            if (vFila >= 0) {
                String codigoProducto = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
                if(codigoProducto != null && codigoProducto.length() > 0){
                    muestra_MsjProducto(codigoProducto); //RARGUMEDO 2019-07-25
                }
                DBVentas.cargaListaProductosSustitutos(tblModelListaSustitutos, codigoProducto);
                tblListaSustitutos.repaint();
            }
        } catch (SQLException sqlException) {
            log.error(null, sqlException);
            FarmaUtility.showMessage(this, "Error al Listar Productos Sustitutos.\n" +
                    sqlException, txtProducto);
        }
    }

    private void quitarFiltro() {
        vEjecutaAccionTeclaListado = false;
        if (VariablesPtoVenta.vInd_Filtro.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            //lblDescFiltro.setText("");
            VariablesPtoVenta.vInd_Filtro = FarmaConstants.INDICADOR_N;
            tblModelListaSustitutos.clearTable();
            tableModelListaPrecioProductos.clearTable();
            tableModelListaPrecioProductos.fireTableDataChanged();
            clonarListadoProductos();
            setJTable(tblProductos);
            iniciaProceso(false);
            FarmaUtility.moveFocus(txtProducto);

            lblF7.setText("[ F7 ] Filtrar Desc.");
        }
    }

    /**
   * Se filtran los productos dependiendo del filtro del resumen
   * @author JCORTEZ
   * @since 17.04.08
   */
    /* private void filtrarTablaProductosResumen2(String pTipoFiltro,
                                           String pCodFiltro) {
    try {
      tableModelListaPrecioProductos.clearTable();
      tableModelListaPrecioProductos.fireTableDataChanged();
      DBVentas.cargaListaProductosVenta_Filtro2(tableModelListaPrecioProductos,
                                               pTipoFiltro,
                                               pCodFiltro);
      if(tableModelListaPrecioProductos.getRowCount()>0)
      FarmaUtility.ordenar(tblProductos, tableModelListaPrecioProductos,COL_ORD_LISTA, FarmaConstants.ORDEN_ASCENDENTE);
      //else
      //FarmaUtility.showMessage(this, "Resultado vacio", null);
    } catch (SQLException sqlException) {
        log.error("",sqlException);
      FarmaUtility.showMessage(this, "Error al obtener Lista de Productos Filtrado!!!", txtProducto);
    }
  }*/

    /**
     * Busqueda de cadena en el listado
     * @param pLista
     * @param pCadena
     * @return
     */
    private boolean isExistProdListaFaltaCero(ArrayList pLista, String pCadena) {
        if (pLista.size() > 0) {
            for (int i = 0; i < pLista.size(); i++) {
                if (pLista.get(i).toString().trim().equalsIgnoreCase(pCadena.trim())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Para clonar el listado de productos original.
     * @author Edgar Rios Navarro
     * @since 29.05.2008
     */
    private void clonarListadoProductos() {
        ArrayList arrayClone = new ArrayList();
        if(isListadoReceta()){
            for (int i = 0; i < tblMdlListaReceta.data.size(); i++) {
                ArrayList aux = (ArrayList)((ArrayList)tblMdlListaReceta.data.get(i)).clone();
                aux.add(0, "");
                arrayClone.add(aux);
            }
        }else{
            for (int i = 0; i < VariablesVentas.tableModelListaGlobalProductos.data.size(); i++) {
    
                ArrayList aux = (ArrayList)((ArrayList)VariablesVentas.tableModelListaGlobalProductos.data.get(i)).clone();
                arrayClone.add(aux);
            }
        }
        tableModelListaPrecioProductos.data = arrayClone;
    }

    /**
     * Se muestra el tratemiento del producto
     * @author JCORTEZ
     * @since 29.05.2008
     */
    private void mostrarTratamiento() {
        
        boolean flagContinua = true; 
        
        //ERIOS 09.01.2015 Mostrar pantalla garantizados.
        if(flagContinua && mostrarPantallaGarantizado()){
            return;
        }
        
        flagContinua = UtilityVentas.validaVentaConMenos(this, myParentFrame, myJTable, txtProducto, pIngresoComprobanteManual);
        
        if(flagContinua){
            
            
            DlgTratamiento vDlg = UtilityVentas.mostrarTratamiento(this,myParentFrame,myJTable,COL_COD,COL_ORIG_PROD);
            
            if (FarmaVariables.vAceptar) {
            
                if(vDlg.isVAccionAcumula()){
                                      // selecciona lo que acepta y lo del regalo por si acepta
                                         if(vDlg.getVCtdNormal()>0){
                                            UtilityVentas.agregaProductoAcumula(this, tblProductos, null,false,
                                                                                vDlg.getVCtdNormal(),                                                                               
                                                                                vDlg.getVCodCampAcumula(),
                                                                                vDlg.getVCodEQCampAcumula()
                                                                                );
                                         
                                        }                    
                                        if(vDlg.getVCtdBono()>0){
                                            UtilityVentas.agregaProductoAcumula(this, tblProductos, null,true,
                                                                                vDlg.getVCtdBono(),
                                                                                vDlg.getVCodCampAcumula(),
                                                                                vDlg.getVCodEQCampAcumula());
                                        }
                                        //FarmaUtility.showMessage(this, "El Producto NOOOO", tblProductos);
                }
                else
                    seleccionaProducto();
                VariablesVentas.vIndDireccionarResumenPed = true;
                FarmaVariables.vAceptar = false;
            } else {
                VariablesVentas.vIndDireccionarResumenPed = false;
            }
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
        
        VariablesFidelizacion.vCodCuponIngresadoCampanasR = nroCupon;
        VariablesFidelizacion.vCodFormaPagoCampanasR = "";

        try {
            mapCupon = DBVentas.getDatosCupon(codCampCupon, nroCupon,VariablesFidelizacion.vDniCliente);
            //TCT 21.07.2014 Reemplaza Codigo Campana Cupon por Valor Real
            codCampCupon = (String)mapCupon.get("COD_CAMP_CUPON");
            //TCT
            mapCupon.put("COD_CUPON", nroCupon);
        } catch (SQLException e) {
            log.debug("ocurrio un error al obtener datos del cupon:" + nroCupon + " error:" + e);
            FarmaUtility.showMessage(this, "Ocurrio un error al obtener datos del cupon.\n" +
                    e.getMessage() +
                    "\n Int�ntelo Nuevamente\n si persiste el error comuniquese con operador de sistemas.",
                    txtProducto);
            return;
        }
        log.debug("datosCupon:" + mapCupon);
        //Se verifica si el cupon ya fue agregado tambien verifica si la campa�a tambien ya esta agregado
        if (UtilityVentas.existeCuponCampana(nroCupon, this, txtProducto))
            return;

        String indMultiuso = mapCupon.get("IND_MULTIUSO").toString().trim();
        boolean obligarIngresarFP = isFormaPagoUso_x_Cupon(codCampCupon);
        boolean yaIngresoFormaPago = false;
        //validacion de cupon en base de datos vigente y todo lo demas

        //jquispe
        //String vIndFidCupon = "N";//obtiene el ind fid -- codCampCupon
        log.debug("CAMP CUPON:: codCampCupon " + codCampCupon);
        String vIndFidCupon = UtilityFidelizacion.getIndfidelizadoUso(codCampCupon);

        if (vIndFidCupon.trim().equalsIgnoreCase("S")) {
            //INI AOVIEDO 28/06/2017
            String tipoCampanaCupon = mapCupon.get("TIPO_CAMPANA").toString();
            String tipoCupon = mapCupon.get("TIP_CUPON").toString();
            if(tipoCampanaCupon.equals("R")){
                VariablesFidelizacion.vNumCuponCampanasR = 1;
                VariablesFidelizacion.vTipCuponCampanasR = tipoCupon;
            }
            //FIN AOVIEDO 28/06/2017

            if (VariablesFidelizacion.vDniCliente.trim().length() == 0) {
                accion_f12(codCampCupon,true);
                yaIngresoFormaPago = true;
                try {
                    mapCupon = DBVentas.getDatosCupon(codCampCupon, nroCupon,VariablesFidelizacion.vDniCliente);
                    mapCupon.put("COD_CUPON", nroCupon);
                } catch (SQLException e) {
                    log.debug("ocurrio un error al obtener datos del cupon:" + nroCupon + " error:" + e);
                    FarmaUtility.showMessage(this, "Ocurrio un error al obtener datos del cupon.\n" +
                            e.getMessage() +
                            "\n Int�ntelo Nuevamente\n si persiste el error comuniquese con operador de sistemas.",
                            txtProducto);
                    return;
                }
            }

            if (VariablesFidelizacion.vDniCliente.trim().length() > 0) {
                //Consultara si es necesario ingresar forma de pago x cupon
                // si es necesario solicitar� el mismo.
                if (obligarIngresarFP) {
                    if (!yaIngresoFormaPago){
                        accion_f12(codCampCupon,true);
                    
                        try {
                            mapCupon = DBVentas.getDatosCupon(codCampCupon, nroCupon,VariablesFidelizacion.vDniCliente);
                            mapCupon.put("COD_CUPON", nroCupon);
                        } catch (SQLException e) {
                            log.debug("ocurrio un error al obtener datos del cupon:" + nroCupon + " error:" + e);
                            FarmaUtility.showMessage(this, "Ocurrio un error al obtener datos del cupon.\n" +
                                    e.getMessage() +
                                    "\n Int�ntelo Nuevamente\n si persiste el error comuniquese con operador de sistemas.",
                                    txtProducto);
                            return;
                        }
                    }
                }

                if (!UtilityVentas.validarCuponEnBD(nroCupon, this, txtProducto, indMultiuso,
                                                    VariablesFidelizacion.vDniCliente, VariablesConvenioBTLMF.vCodConvenio)) {
                    return;
                } else {
                    //agregando cupon al listado
                    String tipCampanaCupon=mapCupon.get("TIPO_CAMPANA").toString();
                    //dubilluz 2019.07.31
                    //***** AOVIEDO 18/06/19 *******/
                    /*if(mapCupon.get("NUM_DOC_IDEN_CUPON").toString().trim().length() != 0){
                        if(!VariablesFidelizacion.vDniCliente.equalsIgnoreCase(mapCupon.get("NUM_DOC_IDEN_CUPON").toString())){
                            FarmaUtility.showMessage(this, "Cup�n no pertenece al cliente fidelizado.\nPor favor fidelizar nuevamente.", null);
                            return;
                        }
                    }*/
                    //***** AOVIEDO 18/06/19 *******/

                    if(tipCampanaCupon.trim().equalsIgnoreCase("F")){
                        VariablesVentas.vArrayList_CuponesFijos.add(mapCupon);
                    }else{
                        //INI AOVIEDO 27/06/2017
                        actualizarCuponesCampanasR(tipCampanaCupon, mapCupon);
                        //FIN AOVIEDO 27/06/2017
                        
                        //INI AOVIEDO 03/07/2017
                        if(!(tipCampanaCupon.trim().equalsIgnoreCase("R") && VariablesFidelizacion.vCodFormaPagoCampanasR.isEmpty())){
                            VariablesVentas.vArrayList_Cupones.add(mapCupon);
                        }
                        //FIN AOVIEDO 03/07/2017
//[3] Agrega el map de cupones de tipo C al arreglo de cupones
                    }
                    //INICIO RARGUMEDO
                    boolean aceptaCupon=true;
                    String msj="";
                    if(tipCampanaCupon.trim().equalsIgnoreCase("H")){//Validacion para aceptar el cupon de cumplea�os
                        String dniCupon=nroCupon.substring(nroCupon.length()-8);
                        String vDniCliente=VariablesFidelizacion.vDniCliente;
                        if(dniCupon.equalsIgnoreCase(vDniCliente)){
                            String fecNac=VariablesFidelizacion.vFecNacimiento;
                            int mesActual=Calendar.getInstance().getTime().getMonth()+1;
                            String mesNac=fecNac.substring(fecNac.indexOf("/")+1, fecNac.lastIndexOf("/"));
                            System.out.println("==> mesActual: "+mesActual+" mesNac: "+mesNac);
                            if(mesActual!=Integer.parseInt(mesNac)){
                                aceptaCupon=false;
                                msj="El cupon cumplea�os ha vencido!!\n" +
                                    "Su uso es permitido solo en el mes del cumplea�os del titular";
                            }
                        }else{
                            aceptaCupon=false;
                            msj="El cupon no correponde al cliente!!\n" +
                                "El uso del cupon cumplea�os es personal e instrasferible\n" +
                                "presentando el DNI del titular";
                        }
                    }
                    //FIN RARGUMEDO 18072017
                    if(aceptaCupon){
                        //24.06.2011
                        //Para Reinicializar todas las formas de PAGO.
                        if(!(tipCampanaCupon.trim().equalsIgnoreCase("R") && VariablesFidelizacion.vCodFormaPagoCampanasR.isEmpty())){
                            UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);
                            VariablesVentas.vMensCuponIngre = "Se ha agregado el cup�n " + nroCupon + " de la Campa�a \n" +
                                    mapCupon.get("DESC_CUPON").toString() + ".";
                            FarmaUtility.showMessage(this, VariablesVentas.vMensCuponIngre, txtProducto);
                        }
                    }else{
                        FarmaUtility.showMessage(this, msj,null);
                    }
                }
            }
            txtProducto.setText("");
        } else {
            if (obligarIngresarFP) {
                if (!yaIngresoFormaPago)
                    accion_f12(codCampCupon,true);
            }

            if (!UtilityVentas.validarCuponEnBD(nroCupon, this, txtProducto, indMultiuso,
                                                VariablesFidelizacion.vDniCliente, VariablesConvenioBTLMF.vCodConvenio)) {
                return;
            } else {
                //agregando cupon al listado
                String tipCampanaCupon=mapCupon.get("TIPO_CAMPANA").toString();
                if(tipCampanaCupon.trim().equalsIgnoreCase("F")){
                    VariablesVentas.vArrayList_CuponesFijos.add(mapCupon);
                }else{
                    VariablesVentas.vArrayList_Cupones.add(mapCupon);
//[4] Agrega el map de cupones de tipo C al arreglo de cupones
                }
                //24.06.2011
                //Para Reinicializar todas las formas de PAGO.
                UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);
                VariablesVentas.vMensCuponIngre = "Se ha agregado el cup�n " + nroCupon + " de la Campa�a \n" +
                        mapCupon.get("DESC_CUPON").toString() + ".";
                FarmaUtility.showMessage(this, VariablesVentas.vMensCuponIngre, txtProducto);
            }
            txtProducto.setText("");
        }
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
     * Se agrega el cupon ingresado.
     * @param cadena
     * @author Edgar Rios Navarro
     * @since 02.07.2008
     * @deprecated
     */
    private void agregarCupon(String cadena) {

        if (VariablesVentas.vEsPedidoConvenio) {
            FarmaUtility.showMessage(this, "No puede agregar cupones a un pedido por convenio.", txtProducto);
            return;
        }


        //JCORTEZ 15.08.08 obtiene indicador de multiuso de la campa�a
        String ind_multiuso = "", cod_camp = "";
        ArrayList aux = new ArrayList();
        try {
            DBVentas.obtieneIndMultiuso(aux, cadena);
            if (aux.size() > 0) {
                ind_multiuso = (String)((ArrayList)aux.get(0)).get(1);
                cod_camp = (String)((ArrayList)aux.get(0)).get(0);
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrio un error al obtener indicador.\n" +
                    sql.getMessage(), txtProducto);
        }

        //Verifica que no exista en el arreglo
        if (UtilityVentas.validaCupones(cadena, this, txtProducto))
            return;

        //Valida que la campana no haya sido agregado al pedido
        if (UtilityVentas.validaCampanaCupon(cadena, this, txtProducto, ind_multiuso, cod_camp))
            return;

        //Valida estructura del cupon en BBDD
        if (!UtilityVentas.validaDatoCupon(cadena, this, txtProducto, ind_multiuso))
            return;


        //txtDescProdOculto.setText("");
        txtProducto.setText("");

        // validaPedidoCupon();


        //dveliz 25.08.08

        VariablesCampana.vCodCupon = cadena;
        //ingresarDatosCampana();
        if (VariablesCampana.vListaCupones.size() > 0)
            VariablesCampana.vFlag = true;

        /*if(VariablesCampana.vNumIngreso==1)
     if(!UtilityVentas.validaDatoCupon(cadena,this,txtProducto,ind_multiuso)) return;  */
        //Fin dveliz
    }

    private void validarClienteTarjeta(String cadena, boolean isTarjetaAntigua) {
        if(!UtilityPuntos.isActivoFuncionalidad()){
            if (VariablesVentas.vEsPedidoConvenio ||
                (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {
//                    if (VariablesVentas.vEsPedidoConvenio) {
                FarmaUtility.showMessage(this, "No puede agregar una tarjeta a un " + "pedido por convenio.", txtProducto);
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
                    if(isTarjetaAntigua /*&& !pIngresoComprobanteManual*/ && isValidaOrbis){
                        txtProducto.setText(cadena);
                        UtilityPuntos.consultarTarjetaOrbis(myParentFrame, this, txtProducto, false, true);
                    }
                }
            }catch(Exception ex){
                log.error("",ex);
                FarmaUtility.showMessage(this, "ERROR EN PROGRAMA DE PUNTOS Y LEALTAD", null);
            }
            ArrayList array = (ArrayList)VariablesFidelizacion.vDataCliente.get(0);
            //JCALLO 02.10.2008
            //VariablesFidelizacion.vDniCliente = String.valueOf(array.get(0));
            //seteando los datos del cliente en las variables con los datos del array
            UtilityFidelizacion.setVariablesDatos(array);
            if (VariablesFidelizacion.vIndExisteCliente) {
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                }else{
                    FarmaUtility.showMessage(this, "Bienvenido \n" +
                        VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente + " " +
                        VariablesFidelizacion.vApeMatCliente + "\n" +
                        "DNI: " + VariablesFidelizacion.vDniCliente, null);
                }
            } else if (VariablesFidelizacion.vIndAgregoDNI.trim().equalsIgnoreCase("0")){
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                }else{
                    FarmaUtility.showMessage(this, "Se agrego el DNI :" + VariablesFidelizacion.vDniCliente, null);
                }
            }else if (VariablesFidelizacion.vIndAgregoDNI.trim().equalsIgnoreCase("2"))
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
            else if (VariablesFidelizacion.vIndAgregoDNI.trim().equalsIgnoreCase("1")){
                if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                    DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                }else{
                    FarmaUtility.showMessage(this,
                                         "Se actualizaron los datos del DNI :" + VariablesFidelizacion.vDniCliente,
                                         null);
                }
            }
            //jcallo 02.10.2008
            String msjPto = "";
            if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && 
                VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados() != null
            ){
                msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
            }
            lblCliente.setText(msjPto + VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente + " " +
                               VariablesFidelizacion.vApeMatCliente);
            //fin jcallo 02.10.2008
            VariablesFidelizacion.vIndAgregoDNI = "";

            //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz
            log.debug("**************************************");
            log.debug("jjccaalloo:VariablesFidelizacion.vDniCliente" + VariablesFidelizacion.vDniCliente);
            VariablesVentas.vArrayList_CampLimitUsadosMatriz = new ArrayList();
            log.debug("******VariablesVentas.vArrayList_CampLimitUsadosMatriz" +
                      VariablesVentas.vArrayList_CampLimitUsadosMatriz);

            //Ya no validara con Matriz
            //Campa�a Limitante 14.04.2009 DUBILLUZ
            /*VariablesFidelizacion.vIndConexion = FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ,          																	FarmaConstants.INDICADOR_N);
          log.debug("************************");
          if(VariablesFidelizacion.vIndConexion.equals(FarmaConstants.INDICADOR_S)){//VER SI HAY LINEA CON MATRIZ
          	log.debug("jjccaalloo:VariablesFidelizacion.vDniCliente"+VariablesFidelizacion.vDniCliente);
          	VariablesVentas.vArrayList_CampLimitUsadosMatriz = CampLimitadasUsadosDeMatrizXCliente(VariablesFidelizacion.vDniCliente);
          	log.debug("******VariablesVentas.vArrayList_CampLimitUsadosMatriz"+VariablesVentas.vArrayList_CampLimitUsadosMatriz);
          }
          */
            //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz
            //dubilluz 19.07.2011 - inicio
            if (VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim().length() > 0) {
                UtilityFidelizacion.grabaTarjetaUnica(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim(),
                                                      VariablesFidelizacion.vDniCliente);
            }
            //dubilluz 19.07.2011 - fin
            
        } else {
            //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz
            log.debug("**************************************");
            //VariablesFidelizacion.vIndConexion = FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ,FarmaConstants.INDICADOR_N);
            VariablesFidelizacion.vIndConexion = FarmaConstants.INDICADOR_N;

            log.debug("************************");
            // if(VariablesFidelizacion.vIndConexion.equals(FarmaConstants.INDICADOR_S)){//VER SI HAY LINEA CON MATRIZ  JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local
            log.debug("jjccaalloo:VariablesFidelizacion.vDniCliente" + VariablesFidelizacion.vDniCliente);
            VariablesVentas.vArrayList_CampLimitUsadosMatriz =
                    UtilityFidelizacion.CampLimitadasUsadosDeMatrizXCliente(VariablesFidelizacion.vDniCliente);
            log.debug("******VariablesVentas.vArrayList_CampLimitUsadosMatriz" +
                      VariablesVentas.vArrayList_CampLimitUsadosMatriz);
            //  }// JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local
            //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz

            //jcallo 02.10.2008
            String msjPto = "";
            if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!=null){
                msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
            }
            lblCliente.setText(msjPto + VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente + " " +
                               VariablesFidelizacion.vApeMatCliente);
            //fin jcallo 02.10.2008
        }


        txtProducto.setText("");
    }
    /*
    private void mostrarBuscarTarjetaPorDNI() {

        DlgFidelizacionBuscarTarjetas dlgBuscar =
            new DlgFidelizacionBuscarTarjetas(myParentFrame, "", true);
        dlgBuscar.setVisible(true);
        log.debug("vv DIEGO:" + FarmaVariables.vAceptar);
        log.debug("dat_1:" + VariablesFidelizacion.vDataCliente);
        log.debug(" VariablesFidelizacion.vNomCliente_1:" +
                           VariablesFidelizacion.vNomCliente);
        log.debug(" VariablesFidelizacion.vDniCliente_1:" +
                           VariablesFidelizacion.vDniCliente);
        if (FarmaVariables.vAceptar) {
            log.debug("en aceptar");
            log.debug("dat:" + VariablesFidelizacion.vDataCliente);
            ArrayList array =
                (ArrayList)VariablesFidelizacion.vDataCliente.get(0);
            log.debug("des 1");
            //JCALLO 02.10.2008
            //VariablesFidelizacion.vDniCliente = String.valueOf(array.get(0));
            //seteando los datos del cliente en las variables con los datos del array
            UtilityFidelizacion.setVariablesDatos(array);
            log.debug("des 2");

            log.debug(" VariablesFidelizacion.vNomCliente:" +
                               VariablesFidelizacion.vNomCliente);
            log.debug(" VariablesFidelizacion.vDniCliente:" +
                               VariablesFidelizacion.vDniCliente);
            FarmaUtility.showMessage(this, "Bienvenido \n" +
                    VariablesFidelizacion.vNomCliente + " " +
                    VariablesFidelizacion.vApePatCliente + " " +
                    VariablesFidelizacion.vApeMatCliente + "\n" +
                    "DNI: " + VariablesFidelizacion.vDniCliente, null);
            //dubilluz 19.07.2011 - inicio
            if (VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim().length() >
                0) {
                UtilityFidelizacion.grabaTarjetaUnica(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim(),
                                                      VariablesFidelizacion.vDniCliente);
            }
            //dubilluz 19.07.2011 - fin


            //jcallo 02.10.2008
            lblCliente.setText(VariablesFidelizacion.vNomCliente + " " +
                               VariablesFidelizacion.vApePatCliente + " " +
                               VariablesFidelizacion.vApeMatCliente);
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
                //VariablesFidelizacion.vIndConexion = FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_N);
                VariablesFidelizacion.vIndConexion =
                        FarmaConstants.INDICADOR_N;
                log.debug("**************************************");
                //if(VariablesFidelizacion.vIndConexion.equals(FarmaConstants.INDICADOR_S)){//VER SI HAY LINEA CON MATRIZ   //VER SI HAY LINEA CON MATRIZ  JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local
                log.debug("jjccaalloo:VariablesFidelizacion.vDniCliente" +
                          VariablesFidelizacion.vDniCliente);
                VariablesVentas.vArrayList_CampLimitUsadosMatriz =
                        CampLimitadasUsadosDeMatrizXCliente(VariablesFidelizacion.vDniCliente);

                log.debug("******VariablesVentas.vArrayList_CampLimitUsadosMatriz" +
                          VariablesVentas.vArrayList_CampLimitUsadosMatriz);
                // } // JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local
                //cargando las campa�as automaticas limitadas en cantidad de usos desde matriz
            } else {
                log.info("Cliente esta invalidado para descuento...");
            }
        }
    }
*/

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

        //cargando las campanas de fidelizacion
        if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
            log.debug("INVOCANDO CARGAR CAMPA�AS DEL CLIENTES ..:" + VariablesFidelizacion.vNumTarjeta);
            UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta.trim(), VariablesConvenioBTLMF.vCodConvenio);
            log.debug("FIN INVOCANDO CARGAR CAMPA�AS DEL CLIENTES ..");

            /**mostranto el nombre del cliente **/
            String msjPto = "";
            if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null &&
            VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!=null){
                msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
            }
            lblCliente.setText(msjPto + VariablesFidelizacion.vNomCliente); /*+" "
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

    private boolean isExisteProdCampana(String pCodProd) {
        //lblMensajeCampa�a.setVisible(true);
        String pRespta = "N";
        try {
            lblMensajeCampa�a.setText("");
            pRespta = DBVentas.existeProdEnCampa�aAcumulada(pCodProd, VariablesFidelizacion.vDniCliente);
            if (pRespta.trim().equalsIgnoreCase("E"))
                lblMensajeCampa�a.setText("    Cliente inscrito en Campa�a Acumulada.");

            if (pRespta.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
                lblMensajeCampa�a.setText("   Promoci�n: \" Acumula tu Compra y Gana \"");
        } catch (SQLException e) {
            log.error(null, e);
            pRespta = "N";
        }

        if (pRespta.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N))
            return false;

        return true;
    }

    /**
     * obtener todas las campa�as de fidelizacion automaticas usados en el pedido
     *
     * */
    /*private ArrayList CampLimitadasUsadosDeMatrizXCliente(String dniCliente) {
        ArrayList listaCampLimitUsadosMatriz = new ArrayList();
        try {
            //listaCampLimitUsadosMatriz = DBCaja.getListaCampUsadosMatrizXCliente(dniCliente);
            listaCampLimitUsadosMatriz =
                    DBCaja.getListaCampUsadosLocalXCliente(dniCliente); // DBCaja.getListaCampUsadosMatrizXCliente(dniCliente);// JCHAVEZ 27092009. se coment� pues no es necesario que valide ya que se consultar� al local
            if (listaCampLimitUsadosMatriz.size() > 0) {
                listaCampLimitUsadosMatriz =
                        (ArrayList)listaCampLimitUsadosMatriz.get(0);
            }
            log.debug("listaCampLimitUsadosMatriz listaCampLimitUsadosMatriz ===> " +
                      listaCampLimitUsadosMatriz);
        } catch (Exception e) {
            log.error("",e);
        }
        return listaCampLimitUsadosMatriz;
    }
    */
    /*
    private void setMensajeDNIFidelizado() {
        if (VariablesFidelizacion.vDniCliente.trim().length() > 7 &&
            VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
            if (!VariablesFidelizacion.vDNI_Anulado) {
                lblMensajeCampa�a.setText("  DNI no afecto a Descuento.");
                lblMensajeCampa�a.setVisible(true);
            } else {
                lblMensajeCampa�a.setText("");
                lblMensajeCampa�a.setVisible(false);
            }

        }
    }
*/

    /**
     * Se valida el rol del usuario
     * @author JCORTEZ
     * @since 24.07.2009
     * */
    private boolean ValidaRolUsu(String vRol) {

        boolean valor = true;
        String result = "";

        log.debug("FarmaVariables.vNuSecUsu : " + FarmaVariables.vNuSecUsu);
        try {
            result = DBVentas.verificaRolUsuario(FarmaVariables.vNuSecUsu, vRol);
            log.debug("result : " + result);
            if (result.equalsIgnoreCase("N"))
                valor = false;
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Ha ocurrido un error al validar el rol de usuario .\n" +
                    e.getMessage(), txtProducto);
        }
        return valor;
    }


    private boolean cargaValidaLogin() {
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
            FarmaUtility.showMessage(this, "Ocurrio un error al validar rol de usuariario \n : " + e.getMessage(),
                                     null);
        }
        return FarmaVariables.vAceptar;
    }


    private void lblPedDelivery() {

        //JCALLO 19.12.2008 comentado sobre la opcion de ver pedidos delivery..y usarlo para el tema inscribir cliente a campa�as acumuladas
        //JCORTEZ 07.08.09 Se habilita nuevamente la opcion de pedido automatico.
        /** JCALLO INHABILITAR F9 02.10.2008* **/
        log.debug("HABILITAR F9 : " + VariablesVentas.HabilitarF9);

        if (VariablesVentas.HabilitarF9.equalsIgnoreCase(ConstantsVentas.ACTIVO)) {
            if (UtilityVentas.evaluaPedidoDelivery(this, txtProducto, UtilityCalculoPrecio.getSizeDetalleVenta())) {
                //KMONCADA 18.05.2015 SE SOLICITA ANULAR LA TRANSACCION DE TARJETA DE ORBIS
                BeanTarjeta tarjetaPuntosOld = null;
                if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                    VariablesFidelizacion.limpiaVariables();
                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                }
                
                evaluaTitulo();
                FarmaUtility.moveFocus(txtProducto);
                // Inicio Adicion Delivery 28/04/2006 Paulo
                if (VariablesVentas.vEsPedidoDelivery) {
                    generarPedidoDelivery();
                }
                // Fin Adicion Delivery 28/04/2006 Paulo
            }
        } else
            FarmaUtility.showMessage(this, "Opcion no disponible en local.", txtProducto);
    }

    //jquispe 03.05.2010
    //Cambia el proceso de actualizacion en mostrar dagtos del producto.

    private void UpdateReleaseProd(KeyEvent e) {
        muestraInfoProd();
        UtilityVentas.muestraMsjArgumentoProd(myJTable,1, lblMsjArgumento_Prod); //RARGUMEDO 2019-07-25
        UtilityVentas.muestraNombreLab(myJTable,4, lblDescLab_Prod);
        UtilityVentas.muestraProductoInafectoIgv(myJTable,11, lblProdIgv);
        UtilityVentas.muestraProductoPromocion(myJTable,17, lblProdProm,1);
        UtilityVentas.muestraProductoRefrigerado(myJTable,15, lblProdRefrig);
        UtilityVentas.muestraIndTipoProd(myJTable,16, lblIndTipoProd);
        UtilityVentas.muestraProductoCongelado(myJTable,indProdCong,lblProdCong);
        UtilityVentas.muestraProductoEncarte(myJTable,COL_IND_ENCARTE, lblProdEncarte);
        UtilityVentas.muestraPuntosRentables(myJTable,COL_COD,lblPuntosRentables);
        if (!(e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS)) {

                actualizaListaProductosAlternativos();
            }
        }
        colocaTotalesPedido();
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

            try {//[Desarrollo5] 20.10.2015 se carga el numero de tarjeta CMR o financiero Encriptado
                vTarjetaEncriptada = DBFidelizacion.getEncriptaTarjetaCampana(FarmaVariables.vCodLocal,
                                                                          FarmaVariables.vCodGrupoCia,
                                                                          nroTarjetaFormaPago);
            
                VariablesFidelizacion.tmp_NumTarjeta_unica_Campana = vTarjetaEncriptada;
                funcionF12_con_Tarjeta("N",true, true);
                /*String pExisteAsociado = UtilityFidelizacion.existeDniAsociado(nroTarjetaFormaPago);
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
                        VariablesFidelizacion.vMaximoAhorroDNIxPeriodo =
                                UtilityFidelizacion.getMaximoAhorroDnixPeriodo(VariablesFidelizacion.vDniCliente,
                                                                               VariablesFidelizacion.vNumTarjeta);
    
                        log.info("Variable de DNI_ANULADO: " + VariablesFidelizacion.vDNI_Anulado);
                        log.info("Variable de vAhorroDNI_x_Periodo: " + VariablesFidelizacion.vAhorroDNI_x_Periodo);
                        log.info("Variable de vMaximoAhorroDNIxPeriodo: " +
                                 VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
    
                        AuxiliarFidelizacion.setMensajeDNIFidelizado(lblMensajeCampa�a, "L", txtProducto, this);
                    }
                } else {
                    if (VariablesFidelizacion.vDniCliente.trim().length() == 0) {
                        funcionF12("N",true, true);
                        yaIngresoFormaPago = true;
                    }
    
                }*/
            
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
                    txtProducto.setText("");
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }
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

        if (pCadenaNueva.length() > 0) {


            if (!pCadenaNueva.trim().equalsIgnoreCase("N")) {
                log.debug("Es tarjeta");
                txtProducto.setText(pCadenaNueva.trim());
                pasoTarjeta = true;
            } else {
                log.debug("NO ES tarjeta");
                pasoTarjeta = false;
            }


        }


    }

    //Dubilluz - 06.12.2011

    public void ingresaMedicoFidelizado() {

        AuxiliarFidelizacion.ingresoMedico(this.myParentFrame, lblMedico, lblMensajeCampa�a, lblCliente, this, "L",
                                           lblDNI_SIN_COMISION, txtProducto);
        /*
        String pPermiteIngresoMedido =
            UtilityFidelizacion.getPermiteIngresoMedido();

        if (pPermiteIngresoMedido.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            if (VariablesVentas.vEsPedidoConvenio) {
                FarmaUtility.showMessage(this,
                                         "No puede ingresar el M�dido porque tiene" +
                                         "seleccionado convenio.",
                                         txtProducto);
                return;
            }
            DlgBusquedaMedicoCamp dlgLista = new DlgBusquedaMedicoCamp(myParentFrame,"",true,lblMedico,
                                                                       lblMensajeCampa�a,lblCliente,this,"L",lblDNI_SIN_COMISION);
            dlgLista.setVisible(true);

            //if(FarmaVariables.vAceptar){
              //  pExiste = "S";
           // }
            //else{
              //  pExiste = "NO_SELECCIONO";
           // }

        }
        else
            FarmaUtility.showMessage(this,"Por el momento no existen promociones por Receta.",txtProducto);
        log.debug("****** ====VARIABLES DE MEDICO ==========================******");
        log.debug("VariablesFidelizacion.V_NUM_CMP:"+VariablesFidelizacion.V_NUM_CMP);
        log.debug("VariablesFidelizacion.V_NOMBRE:"+VariablesFidelizacion.V_NOMBRE);
        log.debug("VariablesFidelizacion.V_DESC_TIP_COLEGIO:"+VariablesFidelizacion.V_DESC_TIP_COLEGIO);
        log.debug("VariablesFidelizacion.V_TIPO_COLEGIO:"+VariablesFidelizacion.V_TIPO_COLEGIO);
        log.debug("VariablesFidelizacion.V_COD_MEDICO:"+VariablesFidelizacion.V_COD_MEDICO);
        log.debug("****** ====VARIABLES DE MEDICO ==========================******");
        */
    }

    /**
     * Muestra ventana de registro de Recetario Magistral
     *
     * @author ERIOS
     * @since 20.05.2013
     */
    private void muestraIngresoRecetarioMagistral() {
        if (VariablesPtoVenta.vIndVerReceMagis.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            if (myJTable.getRowCount() == 0)
                return;

            int row = myJTable.getSelectedRow();
            VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, COL_COD);
            VariablesVentas.vDesc_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, 2);
            VariablesVentas.vNom_Lab = FarmaUtility.getValueFieldJTable(myJTable, row, 4);
            VariablesVentas.vUnid_Vta = FarmaUtility.getValueFieldJTable(myJTable, row, 3);

            VariablesVentas.vVal_Prec_Lista = FarmaUtility.getValueFieldJTable(myJTable, row, 10);
            //VariablesVentas.vPorc_Dcto_1 = FarmaUtility.getValueFieldJTable(myJTable, row, 2);
            VariablesVentas.vVal_Bono = FarmaUtility.getValueFieldJTable(myJTable, row, 7);
            VariablesVentas.vPorc_Igv_Prod = FarmaUtility.getValueFieldJTable(myJTable, row, 11);

            VariablesVentas.vTipoProductoVirtual = FarmaUtility.getValueFieldJTable(myJTable, row, 14);

            VariablesVentas.vMontoARecargar_Temp = "0";
            VariablesVentas.vNumeroARecargar = "";

            VariablesVentas.vIndOrigenProdVta = FarmaUtility.getValueFieldJTable(myJTable, row, COL_ORIG_PROD);
            VariablesVentas.vPorc_Dcto_2 = "0";
            VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
            VariablesVentas.vCantxDia = "";
            VariablesVentas.vCantxDias = "";

            VariablesVentas.vProductoVirtual = true;
            VariablesRecetario.strCodigoRecetario = null;

            DlgResumenRecetarioMagistral dlgResumenRecetarioMagistral =
                new DlgResumenRecetarioMagistral(myParentFrame, "", true);
            dlgResumenRecetarioMagistral.setVisible(true);

            VariablesRecetario.vMapDatosPacienteMedico = null;
            VariablesRecetario.vArrayEsteril = null;

            if (FarmaVariables.vAceptar) {
                VariablesVentas.vCant_Ingresada = VariablesRecetario.strCant_Recetario;
                //VariablesRecetario.strCodigoRecetario = strCodigoRecetario;
                VariablesVentas.vVal_Prec_Lista_Tmp = VariablesRecetario.strPrecioTotal;
                VariablesVentas.vVal_Prec_Vta = VariablesRecetario.strPrecioTotal;
                //FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesRecetario.strPrecioTotal));
                VariablesVentas.vVal_Prec_Lista = VariablesRecetario.strPrecioTotal;
                //FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(VariablesRecetario.strPrecioTotal));
                seleccionaProducto();

                VariablesVentas.venta_producto_virtual = true;
                VariablesVentas.vIndDireccionarResumenPed = true;
                FarmaVariables.vAceptar = false;
            } else {
                VariablesVentas.vIndDireccionarResumenPed = false;
                VariablesVentas.vProductoVirtual = false;
            }
        } else {
            FarmaUtility.showMessage(this, "El registro de Recetario Magistral no est� habilitado.", null);
        }
    }

    /**
     * Se filtra/quita filtro de busqueda
     * @author ERIOS
     * @since 29.08.2013
     */
    private void filtroGoogle() {
        if (VariablesPtoVenta.vInd_Filtro.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            quitarFiltro();
        } else {
            filtrarBusquedaGoogle();
        }
    }

    /**
     * Se filtra el listado de productos segun la descripcion que se ingrese
     * @author ERIOS
     * @since 29.08.2013
     */
    private void filtrarBusquedaGoogle() {
        String condicion = txtProducto.getText().toUpperCase();
        if (!condicion.equals("") && condicion.length() > 0) {
            //inicializa el listado
            clonarListadoProductos();
            //filtrar java
            ArrayList target = tableModelListaPrecioProductos.data;
            ArrayList filteredCollection = new ArrayList();

            Iterator iterator = target.iterator();
            while (iterator.hasNext()) {
                ArrayList fila = (ArrayList)iterator.next();
                String descProd = fila.get(COL_DESC_PROD).toString();
                //if(descProd.startsWith(condicion) || descProd.endsWith(condicion)){
                if (descProd.contains(condicion)) {
                    filteredCollection.add(fila);
                }
            }

            //limpia las tablas auxiliares
            tableModelListaPrecioProductos.data = filteredCollection;
            VariablesPtoVenta.vInd_Filtro = FarmaConstants.INDICADOR_S;
            tableModelListaPrecioProductos.fireTableDataChanged();
            setJTable(tblProductos);
            //iniciaProceso(false);
            tblModelListaSustitutos.clearTable();

            FarmaUtility.ponerCheckJTable(tblProductos, COL_COD, VariablesVentas.vArrayList_PedidoVenta, 0);
            FarmaUtility.ponerCheckJTable(tblProductos, COL_COD, VariablesVentas.vArrayList_Prod_Promociones_temporal,
                                          0);
            FarmaUtility.ponerCheckJTable(tblProductos, COL_COD, VariablesVentas.vArrayList_Prod_Promociones, 0);

            lblF7.setText("[ F7 ] Quitar Filtro");
        }
    }

    /**
     *
     * @author ERIOS
     * @since 26.11.2013
     */
    private void deshabilitaProducto() {
        txtProducto.setEnabled(false);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                }
                txtProducto.setEnabled(true);
                pasoTarjeta = false;
                FarmaUtility.moveFocus(txtProducto);
            }
        }.start();
    }

    private void txtProducto_focusLost(FocusEvent e) {
        //dubilluz 31.08.2016
        //FarmaUtility.moveFocus(txtProducto);
    }

    private void abrePedidosDelivery() {

        dlgResumenPedido.abrePedidosDelivery(true);
        cerrarVentana(true);
    }

    void setResumenPedido(DlgResumenPedido dlgResumenPedido) {
        this.dlgResumenPedido = dlgResumenPedido;
    }

    /**
     * Determinar si un producto es parte de algun pack
     * @author ASOSA
     * @since 23/10/2014
     * @return
     */
    public boolean isProdInPack() {
        boolean flag = false;
        int vFila = myJTable.getSelectedRow();
        String codigoProd = FarmaUtility.getValueFieldArrayList(tableModelListaPrecioProductos.data, vFila, COL_COD);
        flag = UtilityVentas.obtenerIndProdInPack(codigoProd);
        return flag;
    }

    /**
     * Metodo para forzar la llamada a el F5 de packs
     * @author ASOSA
     * @since 23/10/2014
     * @param e
     */
    private void lblF5_keyPressed(KeyEvent e) {
        if (VariablesVentas.vEsPedidoConvenio ||
            (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {

            FarmaUtility.showMessage(this, "No puede agregar estas promociones a un " + "pedido por convenio.",
                                     txtProducto);
            return;
        } else {

            //vEjecutaAccionTeclaListado = false;
            int vFila = myJTable.getSelectedRow();
            Boolean valor = (Boolean)(myJTable.getValueAt(vFila, 0));
            String indProm = (String)(myJTable.getValueAt(vFila, 17));

            if (myJTable.getName().equalsIgnoreCase(ConstantsVentas.NAME_TABLA_PRODUCTOS))
                VariablesVentas.vCodProdFiltro = FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
            else
                VariablesVentas.vCodProdFiltro = "";

            if (indProm.equalsIgnoreCase("S")) { //if(!valor.booleanValue())
                muestraPromocionProd(VariablesVentas.vCodProdFiltro);
                //else
                //  FarmaUtility.showMessage(this,"El Producto est� en una Promoci�n ya seleccionada",txtProducto);
            } else
                muestraPromocionProd(VariablesVentas.vCodProdFiltro);
        }
    }

    private boolean mostrarPantallaGarantizado() {        
        boolean bRetorno = false;
        JTable tblProductos = myJTable;
        int vFila = tblProductos.getSelectedRow();
        
        //INI ASOSA - 19/01/2015 - RIMAC
        String codProd = FarmaUtility.getValueFieldArrayList(tableModelListaPrecioProductos.data, vFila, 1).toString();    
        boolean isRimac = UtilityInventario.isExistProdRimac(codProd);
        String virtual = FarmaUtility.getValueFieldArrayList(tableModelListaPrecioProductos.data, vFila, 13);
        if (virtual.equals("S") || isRimac) {
            return false;
        }
        //FIN ASOSA - 19/01/2015 - RIMAC
        
        //0. Validar indicador
        if(vIndActGarantizados.equals(FarmaConstants.INDICADOR_S)){
            //1. Producto seleccionado es no garantizado
            //String producto = FarmaUtility.getValueFieldJTable(tblProductos, vFila, COL_COD);
            String zan = FarmaUtility.getValueFieldJTable(tblProductos, vFila, COL_ZAN);
            ArrayList selec = ((FarmaTableModel)myJTable.getModel()).getRow(vFila);
            if(zan.equals("")){
                
                //2. Existe producto garantizado
                Iterator<ArrayList> lista = tblModelListaSustitutos.data.iterator();
                String zanOrig = "";
                ArrayList pSustitutos = new ArrayList();
                pSustitutos.add(selec);
                while(lista.hasNext()){                
                    ArrayList fila = lista.next();
                    String sust = fila.get(COL_ZAN).toString().trim();
                    //Boolean valor = (Boolean)(myJTable.getValueAt(vFila, 0));
                    Boolean valor = (Boolean)fila.get(0);
                    if(!sust.equals("") && !valor){
                        bRetorno = true;
                        if(zanOrig.equals("")){
                            zanOrig = sust;                    
                        }
                        if(zanOrig.equals(sust)){
                            pSustitutos.add(fila);
                        }
                    }    
                }
                
                //3. Muestra pantalla garantizado
                if(bRetorno){                    
                    if(UtilityVentas.verificaCobertura(this)){
                        DlgProductosGarantizados dlgProductosGarantizados = new DlgProductosGarantizados(myParentFrame,"",true,pIngresoComprobanteManual);
                        dlgProductosGarantizados.setSustitos(pSustitutos);
                        dlgProductosGarantizados.setVisible(true);
                        if (FarmaVariables.vAceptar) {
                            VariablesVentas.vIndDireccionarResumenPed = true;
                            aceptaOperacion();
                        }else{
                            VariablesVentas.vIndDireccionarResumenPed = false;
                        }
                    }
                }
            }
        }
        
        return bRetorno;
    }

    public void setPIngresoComprobanteManual(boolean pIngresoComprobanteManual) {
        this.pIngresoComprobanteManual = pIngresoComprobanteManual;
    }
    
    // DUBILLUZ 31.08.2016
    private void carga_pedido_receta(String pCadena){
        
        if(pCadena.trim().length()>0){
            if(pCadena.trim().substring(0,1).equalsIgnoreCase("R")&&pCadena.trim().length()==14){
                String pPrefijo =  pCadena.trim().substring(0,1);
                String pCodLocal =  pCadena.trim().substring(1,4);
                String pNumPedidoReceta =  pCadena.trim().substring(4);
                boolean pPermite = false;
                if(UtilityVentas.isPedidoReceta()){
                    if(JConfirmDialog.rptaConfirmDialog(this, 
                                                        "Ya registr� una receta a la toma de pedido, �desea reemplazarla?"))
                    {
                        pPermite = true;
                    }
                    else{
                        pPermite = false;
                    }
                }
                else
                    pPermite = true;
                
                // JVARA BUSQUEDA LA RECETA EN RAC
                // si no existe en el local intenta ir a RAC para traer el pedido a la base de datos del local
                if(!UtilityAtencionMedica.vExisteRecetaLocalBD(pCodLocal,pNumPedidoReceta)){
                    //ERIOS 24.08.2016 Envia datos de Atencion Medica
                    FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
                    facadeVtaAtenMed.recuperarReceta(pCodLocal,pNumPedidoReceta);
                }
                
                
                if(pPermite&&UtilityAtencionMedica.vExisteRecetaLocalBD(pCodLocal,pNumPedidoReceta)){
                 
                    // actualiza historico
                    UtilityAtencionMedica.getHistoricoRecetaPedido(pCodLocal,pNumPedidoReceta);
                    
                DlgDetalleReceta dlgDetalleRegistroVentas = new DlgDetalleReceta(myParentFrame, "", true,
                                                                                 FarmaVariables.vCodGrupoCia,
                                                                                 pCodLocal,
                                                                                 pNumPedidoReceta);
                dlgDetalleRegistroVentas.setVisible(true);
                if (FarmaVariables.vAceptar) {
                      FarmaVariables.vAceptar = false;
                      pRecetaCodCia    = FarmaVariables.vCodGrupoCia;
                      pRecetaCodLocal  = pCodLocal;
                      pRecetaNumReceta = pNumPedidoReceta;
                        
                        /*FarmaUtility.showMessage(this, "Se va Procesar la Receta "+pCadena+"\n"+
                                                        "pPrefijo   "+pPrefijo+"\n"+
                                                        "pCodLocal  "+pCodLocal+"\n"+
                                                        "pNumPedido "+pNumPedido+"\n"+
                                                        "Por favor de esperar que termine.", txtProducto);*/
                        cargaIconoReceta();
                        DlgPrpcesaRecetaPedido dlgEspera = new DlgPrpcesaRecetaPedido(myParentFrame,"",true,this,
                                                                FarmaVariables.vCodGrupoCia,pCodLocal,pNumPedidoReceta);
                        dlgEspera.setVisible(true);
                        VariablesVentas.vCodCia_Receta = pRecetaCodCia;
                        VariablesVentas.vCodLocal_Receta= pRecetaCodLocal;                        
                        VariablesVentas.vNumPedido_Receta= pRecetaNumReceta;
                        VariablesVentas.vDetalleReceta = new ArrayList();
                        cargaDetalleReceta(VariablesVentas.vDetalleReceta,pRecetaCodCia,pRecetaCodLocal,pRecetaNumReceta);

                            
                        // carga campa�as que son recetas
                        UtilityFidelizacion.operaCampa�asFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);
                }
                else{
                    txtProducto.setText(FarmaUtility.getValueFieldArrayList(tableModelListaPrecioProductos.data,tblProductos.getSelectedRow(),2));
                    FarmaUtility.moveFocus(txtProducto);
                }
                }
                else{
                    
                    if(pPermite){
                        FarmaUtility.showMessage(this,"La receta no existe, vuelva a intentarlo.",txtProducto);    
                    }   
                    
                    txtProducto.setText(FarmaUtility.getValueFieldArrayList(tableModelListaPrecioProductos.data,tblProductos.getSelectedRow(),2));
                    FarmaUtility.moveFocus(txtProducto);
                }
            }
        }
    }
    
    public void cargaDetalleReceta(ArrayList vLista, String pCodGrupoCia_in,String pCodLocal_in,String pNumReceta_in) {
            try {
                log.debug(VariablesReporte.vCorrelativo);
                
                String pCodCia   = pCodGrupoCia_in;
                String pCodLocal = pCodLocal_in;
                String pNumPed   = pNumReceta_in;

            DBReceta.getDetalleReceta(vLista, pCodCia,pCodLocal,pNumPed);
            } catch (SQLException sql) {
                log.error("", sql);
                FarmaUtility.showMessage(this, "Error al listar el Detalle de las Ventas : \n" +
                        sql.getMessage(), null);
            }        
    }
    
    public boolean isNumeroReceta(String pCadena){
        if(pCadena.trim().length()>0){
            if(pCadena.trim().substring(0,1).equalsIgnoreCase("R")&&pCadena.trim().length()==14
               && isNumerico(pCadena.trim().substring(4))){
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
    
    private void ingresoRecetaNuevo() {
        DlgListaRecetas dlgReceta = new DlgListaRecetas(myParentFrame, "", true);
        dlgReceta.setVisible(true);
        if (FarmaVariables.vAceptar&&dlgReceta.isVOperaReceta()) {
            FarmaVariables.vAceptar = false;
            String pCodCia    = dlgReceta.getVCodCia();
            String pCodLocal  = dlgReceta.getVCodLocal();
            String pNumReceta = dlgReceta.getVNumReceta();
            
            pRecetaCodCia    = pCodCia;
            pRecetaCodLocal  = pCodLocal;
            pRecetaNumReceta = pNumReceta;
            
            FarmaUtility.showMessage(this, "Se va Procesar la Receta \n"+
                                            "N� "+pNumReceta+"\n"+
                                            "Por favor de esperar que termine.", txtProducto);
            
            DlgPrpcesaRecetaPedido dlgEspera = new DlgPrpcesaRecetaPedido(myParentFrame,"",true,this,pCodCia,pCodLocal,pNumReceta);
            dlgEspera.setVisible(true);
            VariablesVentas.vCodCia_Receta = pRecetaCodCia;
            VariablesVentas.vCodLocal_Receta= pRecetaCodLocal;
            VariablesVentas.vNumPedido_Receta= pRecetaNumReceta;
            
        }
    }


    public void procesaReceta(){
        
        txtProducto.setText("Se procesa los productos 1 x 1");
        
        ArrayList vLista = new ArrayList();

        try {


            DBReceta.getDetRecetaOpera(vLista, pRecetaCodCia, pRecetaCodLocal, pRecetaNumReceta);
            if(vLista.size()>0){
                
                quitaProductosIngresadosParaReceta();
                
                
                for(int i=0;i<vLista.size();i++){
                    if(FarmaUtility.getValueFieldArrayList(vLista, i, 2).trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)){
                        int vFila = -1;
                        for(int j=0;j< tableModelListaPrecioProductos.data.size();j++){ 
                            if(FarmaUtility.getValueFieldArrayList(vLista, i, 0).trim().
                                    equalsIgnoreCase(FarmaUtility.getValueFieldArrayList( tableModelListaPrecioProductos.data, j, 1))){
                                    vFila=j;
                                    break;
                                    }
                            }

                        VariablesVentas.vCod_Prod = 
                                //FarmaUtility.getValueFieldArrayList(vLista, i, 0).trim();
                                FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
                        
                        ArrayList myArray = new ArrayList();
                        DBVentas.obtieneInfoProductoVta_RECETA(myArray, VariablesVentas.vCod_Prod);
                        VariablesVentas.vVal_Frac = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
                        VariablesVentas.vCod_Prod = 
                                FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
                        VariablesVentas.vDesc_Prod = 
                                ((String)(myJTable.getValueAt(vFila, 2))).trim();
                        VariablesVentas.vNom_Lab = 
                                ((String)(myJTable.getValueAt(vFila, 4))).trim();
                        VariablesVentas.vPorc_Igv_Prod = 
                                ((String)(myJTable.getValueAt(vFila, 11))).trim();
                        VariablesVentas.vCant_Ingresada_Temp = "0";
                        VariablesVentas.vNumeroARecargar = "";
                        VariablesVentas.vVal_Prec_Lista_Tmp = "";
                        VariablesVentas.vIndProdVirtual = FarmaConstants.INDICADOR_N;
                        VariablesVentas.vTipoProductoVirtual = "";
                        //sasas
                        VariablesVentas.vVal_Prec_Pub = 
                                ((String)(myJTable.getValueAt(vFila, 6))).trim();
                        VariablesVentas.vIndOrigenProdVta = 
                                FarmaUtility.getValueFieldJTable(myJTable, vFila, 
                                                                 COL_ORIG_PROD);
                        VariablesVentas.vPorc_Dcto_2 = "0";
                        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
                        VariablesVentas.vCantxDia = "";
                        VariablesVentas.vCantxDias = "";


                        VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldArrayList(vLista, i, 0);
                           DlgIngresoCantidad dlgIngCantidad = new DlgIngresoCantidad(myParentFrame, "", true);
                        VariablesVentas.vIngresaCant_ResumenPed = false;
                           dlgIngCantidad.setCantInic(Integer.parseInt(FarmaUtility.getValueFieldArrayList(vLista, i, 1)));
                           //dlgIngCantidad.setSize(10, 10);
                           dlgIngCantidad.abreOpera();
                           ////////////selecciona product /////////
                           Boolean valorTmp = (Boolean)(myJTable.getValueAt(vFila, 0));
                        
                        //   DUBILLUZ 2016.10.27
                        VariablesVentas.vUnid_Vta =     ((String)((ArrayList)myArray.get(0)).get(6)).trim();
                        VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
                        
                        //   DUBILLUZ 2016.10.27
                           double auxCantIng = 
                               FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada);
                           double auxPrecVta = 
                               FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta);
                           
                        VariablesVentas.vTotalPrecVtaProd = (auxCantIng * auxPrecVta);
                        VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                        
                           setCheckValueRow(myJTable, false,vFila);
                           Boolean valor = (Boolean)(myJTable.getValueAt(vFila, 0));
                           
                            if (!UtilityVentas.buscar(VariablesVentas.vArrayList_Prod_Promociones, FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD)) &&
                                !UtilityVentas.buscar(VariablesVentas.vArrayList_Prod_Promociones_temporal, FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD))) { //PACKVARIEDADREVERTIR
                                valor = (Boolean)(tblProductos.getValueAt(vFila, 0));
                            } else {
                                valor = true;
                            }
                        UtilityVentas.operaProductoSeleccionadoEnArrayList_02(valor, VariablesVentas.secRespStk); //ASOSA, 01.07.2010
                           pintaCheckOtroJTable(myJTable, valorTmp);
                           colocaTotalesPedido();
                           //FarmaUtility.showMessage(this,"VariablesVentas.vCod_Prod " + VariablesVentas.vCod_Prod,txtProducto);

                        VariablesVentas.vIndDireccionarResumenPed = false;
                           FarmaVariables.vAceptar = false;   
                    }
             }
                VariablesVentas.vIndDireccionarResumenPed = true;
                VariablesVentas.vIndF11 = true;
             aceptaOperacion();
                
         }
        } catch (Exception sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        }
        VariablesVentas.vIndDireccionarResumenPed = true;
        VariablesVentas.vIndF11 = true;
        aceptaOperacion();
    }    
    // DUBILLUZ 31.08.2016

    private void jButton1_actionPerformed(ActionEvent e) {
        ingresoRecetaNuevo();
    }
    
    private void cargaIconoReceta() {
        //LTERRAZOS 01.03.2013 Se llama a la tabla PBL_CIA para mostrar la ruta
        String strRutaJpg = "IconoReceta.jpg";
        //ImageIcon imageIcono =new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/" + strRutaJpg));
        try {
            lblReceta.setIcon(ajustarImagen(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/" +
                                                                          strRutaJpg)));
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
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

    private void quitaProductosIngresadosParaReceta() {
       VariablesVentas.vArrayList_PedidoVenta = new ArrayList();
       UtilityCalculoPrecio.clearDetalleVenta();
       VariablesVentas.vArrayList_Promociones = new ArrayList();
       VariablesVentas.vArrayList_Prod_Promociones = new ArrayList();
       iniciaProceso(true);
    }

    public boolean isListadoReceta() {
        return listadoReceta;
    }

    public void setListadoReceta(boolean listadoReceta) {
        this.listadoReceta = listadoReceta;
    }
    
    public String getCodProdRecetaSelect(){
        return this.codProdRecetaSelect;
    }
    
    private void deshabilitarLabelReceta(){
        lblPrecio.setVisible(false);
        lblPrecio_T.setVisible(false);
        lblItems.setVisible(false);
        lblItems_T.setVisible(false);
        lblTotalVenta.setVisible(false);
        lblTotalVenta_T.setVisible(false);
    }
    
    public void loadInfoPrecioProducto(String pCodProd,int fila){
        lblInfoFraccion.setText("");
        lblInfoSugerido.setText("");
        lblInfoAhorro.setText("");
        
        jepMonedero.setText("");
        jepMayor.setText("");
        jepNormal.setText("");
        
        if(!isListadoReceta()){
            try { 
                //String pCodProd = "";
                BeanInfoPrecioProd beanProd = DBCalculoPrecio.getInfoPrecioProd(pCodProd);
                if(beanProd!=null){
                    /*myJTable.setValueAt("**********", fila, 6);
                    lblInfoFraccion.setText(getCastCadenaInfoPrecio(beanProd.getVDATOS_FRACCION()));
                    lblInfoSugerido.setText(getCastCadenaInfoPrecio(beanProd.getVDATOS_SUGERIDO()));
                    lblInfoAhorro.setText(getCastCadenaInfoPrecio(beanProd.getVAHORRO_REDONDEO()));                */
                    myJTable.setValueAt("**********", fila, 21);
                    myJTable.setValueAt("**********", fila, 22);
                    myJTable.setValueAt("**********", fila, 23);
                    jepMonedero.setText(getTextMensaje("Precio Monedero",beanProd.getVMonedero_DATOS_FRACCION().split("@"),beanProd.getVMonedero_DATOS_SUGERIDO().split("@")));
                    jepMayor.setText(getTextMensaje("Precio Mayor de 50 A�os",beanProd.getVMayor_DATOS_FRACCION().split("@"),beanProd.getVMayor_DATOS_SUGERIDO().split("@")));
                    jepNormal.setText(getTextMensaje("Precio Normal",beanProd.getVDATOS_FRACCION().split("@"),beanProd.getVDATOS_SUGERIDO().split("@")));
                    
                    if(VariablesFidelizacion.vDniCliente.trim().length()>0){
                        if(VariablesFidelizacion.vDniCliente.trim().length()>0){
                            ArrayList vListPrec = new ArrayList();
                            BeanInfoPrecioMonedero bean = DBCalculoPrecio.getDatoPrecioFidelizado(pCodProd,VariablesFidelizacion.vDniCliente);
                            FarmaUtility.aceptarTransaccion();
                            if(bean!=null){
                            jepMonedero.setText(getTextMensajeNuevo("Precio Monedero",
                                                               beanProd.getVMonedero_DATOS_FRACCION().split("@"),
                                                               beanProd.getVMonedero_DATOS_SUGERIDO().split("@"),
                                                                bean.getVMonedero_Precio_FRACCION().replace(",", ""),
                                                                bean.getVMonedero_Precio_SUGERIDO().replace(",", "")   
                                                               ));
                            jepMayor.setText(getTextMensajeNuevo("Precio Mayor de 50 A�os",
                                                                 beanProd.getVMayor_DATOS_FRACCION().split("@"),
                                                                 beanProd.getVMayor_DATOS_SUGERIDO().split("@"),
                                                                 bean.getVMayor_Precio_FRACCION().replace(",", ""),
                                                                 bean.getVMayor_Precio_SUGERIDO().replace(",", "")) 
                                                                 );
                            
                            jepNormal.setText(getTextMensajeNuevo("Precio Normal",
                                                                     beanProd.getVDATOS_FRACCION().split("@"),
                                                                     beanProd.getVDATOS_SUGERIDO().split("@"),
                                                                     bean.getVNoFid_Precio_FRACCION().replace(",", ""),
                                                                     bean.getVNoFid_Precio_SUGERIDO().replace(",", "")) 
                                                                     );
                                
                            }
                        }
                    }
                }
                else{
                    if(VariablesFidelizacion.vDniCliente.trim().length()>0){
                        ArrayList vListPrec = new ArrayList();
                        BeanInfoPrecioMonedero bean = DBCalculoPrecio.getDatoPrecioFidelizado(pCodProd,VariablesFidelizacion.vDniCliente);
                        
                        if(bean!=null){
                            if(Double.parseDouble(bean.getVNoFid_Precio_FRACCION().replace(",", ""))!=99999)
                                 myJTable.setValueAt(bean.getVNoFid_Precio_FRACCION(), fila, 21);
                            if(Double.parseDouble(bean.getVMonedero_Precio_FRACCION().replace(",", ""))!=99999)
                                myJTable.setValueAt(bean.getVMonedero_Precio_FRACCION(), fila, 22);
                            if(Double.parseDouble(bean.getVMayor_Precio_FRACCION().replace(",", ""))!=99999)
                                 myJTable.setValueAt(bean.getVMayor_Precio_FRACCION(), fila, 23);
                            
                        }
                    }
                    else{
                        try {
                            BeanInfoPrecioNormal beanProdNormal = DBCalculoPrecio.getInfoPrecioNormal(pCodProd);
                            myJTable.setValueAt(beanProdNormal.getVPRECIO_NORMAL(), fila, 21);
                            myJTable.setValueAt(beanProdNormal.getVPRECIO_MONEDERO(), fila, 22);
                            myJTable.setValueAt(beanProdNormal.getVPRECIO_MAYOR(), fila, 23);
                            myJTable.repaint();
                        } catch (Exception sqle) {
                            // TODO: Add catch code
                            log.info(sqle.getMessage());
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
            }            
        }
    }
    
    public String getCastCadenaInfoPrecio(String pCadena){
        String[] cadena = pCadena.split("@");
        if(cadena.length==2){
            return "<html>"+
                    "<center>"+
                    cadena[0]+"<br>"+
                    cadena[1]+""+
                   "</center>"+
                    "</html>";            
        }
        else
            return "";

    }

    private void tblProductos_mouseClicked(MouseEvent e) {
        int i= tblProductos.getSelectedRow();
          txtProducto.setText(FarmaUtility.getValueFieldArrayList(tableModelListaPrecioProductos.data,i, 2));
          FarmaUtility.moveFocus(txtProducto);
          muestraProductosSustitutos();    
              
              if (e.isMetaDown() ){
                  // click derecho
                  vEjecutaAccionTeclaListado = false;
                  UtilityVentas.muestraDetalleProducto(myParentFrame,myJTable,COL_COD,2,4,isListadoReceta());
              }
              else{
                  if (e.getClickCount() == 2 && !e.isConsumed()) {
                       e.consume();
                       int vFoco = tblProductos.getSelectedRow();
                       //txtFiltroCategoria.setText(""+vFoco);
                      if (tblProductos.getSelectedRow() >= 0)
                      {  txtProducto_keyPressed2(new KeyEvent( txtProducto, KeyEvent.KEY_PRESSED, 
                                          0,                          // When timeStamp
                                          0,                          // Modifier
                                          KeyEvent.VK_ENTER,      // Key Code
                                          KeyEvent.CHAR_UNDEFINED ));
                          
                       }
                       //System.out.println("- "+vFoco);
                  }
              }
    }

    private void tblListaSustitutos_mouseClicked(MouseEvent e) {
        int i= tblListaSustitutos.getSelectedRow();
              //txtProducto.setText(FarmaUtility.getValueFieldArrayList(tblModelListaSustitutos.data,i, 2));
              if (e.isMetaDown() ){
                  // click derecho
                  vEjecutaAccionTeclaListado = false;
                  UtilityVentas.muestraDetalleProducto(myParentFrame,myJTable,COL_COD,2,4,isListadoReceta());
              }
              else{
                  if (e.getClickCount() == 2 && !e.isConsumed()) {
                       e.consume();
                       int vFoco = tblListaSustitutos.getSelectedRow();
                       //txtFiltroCategoria.setText(""+vFoco);
                      if (tblListaSustitutos.getSelectedRow() >= 0)
                      {  txtProducto_keyPressed2(new KeyEvent( txtProducto, KeyEvent.KEY_PRESSED, 
                                          0,                          // When timeStamp
                                          0,                          // Modifier
                                          KeyEvent.VK_ENTER,      // Key Code
                                          KeyEvent.CHAR_UNDEFINED ));
                          
                       }
                       //System.out.println("- "+vFoco);
                  }
              }
    }

    private void JO_actionPerformed(ActionEvent e) {
       Component cell = tblProductos.getCellRenderer(0,21).getTableCellRendererComponent(tblProductos,
                                                                                         "", 
                                                                                         true, 
                                                                                         true, 
                                                                                         0, 
                                                                                         21);
        cell.setBackground(new Color(148,0,0));
        cell.setForeground(Color.WHITE);
        tblProductos.repaint();
    }
    
    public String getTextMensaje(String pCabecera,
                                 String[] listFracc,
                                 String[] listSugerido
                                ){
        
        String UnidadFracc = listFracc[0];
        String UnidadSugerido= listSugerido[0];
        String pPrecFracc = listFracc[1];
        String pPrecSugerido= listSugerido[1];
        return "<style type=\"text/css\">\n" + 
        "body {\n" + 
        "    font-family: 'trebuchet MS', 'Lucida sans', Arial;\n" + 
        "    color: #444;\n" + 
        "    margin: 0px auto;\n" + 
        "}\n" + 
        "table {\n" + 
        "    border-spacing: 0;\n" + 
        "    font-size: 11;\n" + 
        "}\n" + 
        "table td{\n" + 
        "    width: 90px;\n" + 
        "}\n" + 
        ".bordered td, .bordered th {\n" + 
        "    padding: 0px;\n" + 
        "    text-align: center;    \n" + 
        "}.bordered th {\n" + 
        "    background-color: #ffefd6;\n" + 
        "}</style><div>\n" + 
        "<table border=0 class=\"bordered\">\n" + 
        "<tr class=\"style4\"><td colspan=\"2\"><center><b>"+pCabecera+"</b></center></td></tr>\n" + 
        "<tr>\n" + 
        "<td>"+UnidadFracc+"</td>\n" + 
        "<td>"+UnidadSugerido+"</td></tr>\n" + 
        "<tr><td>"+pPrecFracc+"</td>\n" + 
        "    <td>"+pPrecSugerido+"</td></tr> \n" + 
        "</table>";
        
    }

    public String getTextMensajeNuevo(String pCabecera,
                                 String[] listFracc,
                                 String[] listSugerido,
                                      String pPrecioFraccion,
                                      String pPrecioSugerido
                                ){
        
        String UnidadFracc = listFracc[0];
        String UnidadSugerido= listSugerido[0];
        
        String pPrecFracc = "";
        String pPrecSugerido= "";
        
        
        if(Double.parseDouble(listFracc[1].replace(""+ConstantesUtil.simboloSoles,"").trim())<Double.parseDouble(pPrecioFraccion))
             pPrecFracc = listFracc[1];
        else
            pPrecFracc = pPrecioFraccion;
        
        if(Double.parseDouble(listSugerido[1].replace(""+ConstantesUtil.simboloSoles,"").trim())<Double.parseDouble(pPrecioSugerido))
            pPrecSugerido= listSugerido[1];
        else
            pPrecSugerido= pPrecioSugerido;
            
        return "<style type=\"text/css\">\n" + 
        "body {\n" + 
        "    font-family: 'trebuchet MS', 'Lucida sans', Arial;\n" + 
        "    color: #444;\n" + 
        "    margin: 0px auto;\n" + 
        "}\n" + 
        "table {\n" + 
        "    border-spacing: 0;\n" + 
        "    font-size: 11;\n" + 
        "}\n" + 
        "table td{\n" + 
        "    width: 90px;\n" + 
        "}\n" + 
        ".bordered td, .bordered th {\n" + 
        "    padding: 0px;\n" + 
        "    text-align: center;    \n" + 
        "}.bordered th {\n" + 
        "    background-color: #ffefd6;\n" + 
        "}</style><div>\n" + 
        "<table border=0 class=\"bordered\">\n" + 
        "<tr class=\"style4\"><td colspan=\"2\"><center><b>"+pCabecera+"</b></center></td></tr>\n" + 
        "<tr>\n" + 
        "<td>"+UnidadFracc+"</td>\n" + 
        "<td>"+UnidadSugerido+"</td></tr>\n" + 
        "<tr><td>"+pPrecFracc+"</td>\n" + 
        "    <td>"+pPrecSugerido+"</td></tr> \n" + 
        "</table>";
        
    }    
    private void agregaProdTemporalmente() {
        VariablesVentas.vCant_Ingresada = "1";
        seleccionaProducto();
        saveResumenProductosVendidos();
    }

    private void saveResumenProductosVendidos() {
       UtilityCalculoPrecio.clearDetalleVenta();
       UtilityCalculoPrecio.uploadDetalleVenta(VariablesVentas.vArrayList_PedidoVenta);
        
        for (int i = 0; i < VariablesVentas.vArrayList_Promociones_temporal.size(); i++)
            VariablesVentas.vArrayList_Promociones.add((ArrayList)((ArrayList)VariablesVentas.vArrayList_Promociones_temporal.get(i)).clone());


        VariablesVentas.vArrayList_Promociones_temporal = new ArrayList();

        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones_temporal.size(); i++)
            VariablesVentas.vArrayList_Prod_Promociones.add((ArrayList)((ArrayList)VariablesVentas.vArrayList_Prod_Promociones_temporal.get(i)).clone());

        VariablesVentas.vArrayList_Prod_Promociones_temporal = new ArrayList();
    }

    public static void setCheckValueRow(JTable pTable, boolean pOnlyCurrentRow,int vFila) {
        // Asignaci�n de valores antes de proceder o no con la inicializaci�n
        // de la celda JCheckBox de cada uno de los registros del JTable.
        int selectedRow = vFila;
        Boolean valor = (Boolean)pTable.getValueAt(selectedRow, 0);
        if (!valor.booleanValue()) {
            if (pOnlyCurrentRow) {
                // Si no es multiselecci�n se eliminan todos los check.
                for (int i = 0; i < pTable.getRowCount(); i++)
                    pTable.setValueAt(new Boolean(false), i, 0);
            }
        }
        pTable.setValueAt(new Boolean(!valor.booleanValue()), selectedRow, 0);
        // Es necesario pintar nuevamente este objeto para poder mostrar los
        // cambios efectuados.
        pTable.repaint();
    }

    private void accion_f12(String pCodCampanaCupon, boolean vTeclaF12) {
        UtilityPuntos.fideliza_cliente(pCodCampanaCupon,vTeclaF12, false,myParentFrame,this);
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
            //ImageIcon icon = new ImageIcon(this.getClass().getResource("logo_mf_btl.JPG"));
            this.setTitle("Lista de Productos y Precios " + VariablesConvenioBTLMF.vNomConvenio);
            log.debug("Nombre Cliente::" + VariablesConvenioBTLMF.vNomCliente);
            lblCliente.setText("" + VariablesConvenioBTLMF.vNomCliente);
            //lblMedicoT.setIcon(icon);
            //lblMedico.setText(" "+VariablesConvenioBTLMF.vNomConvenio );
            
            //INI JMONZALVE 24042019
            try {
                if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio) &&
                    DBConv_Responsabilidad.verificaAcumulacionPuntoCobroPorResp()){
                    String msjPto = "";
                    if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && 
                       VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!=null){
                        msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
                    }
                    lblCliente.setText(msjPto + VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente +
                                       " " + VariablesFidelizacion.vApeMatCliente);
                }
            } catch (SQLException e) {
                log.error("Error al verificar activacion de convenio Cobro por Responsabilidad: " + e.getMessage());
            }
            //FIN JMONZALVE 24042019
        } else {
            evaluaTitulo();
        }
        AuxiliarFidelizacion.setMensajeDNIFidelizado(lblMensajeCampa�a, "L", txtProducto, this);
    } 
    
    private void funcionF12_con_Tarjeta(String pCodCampanaCupon, boolean vTeclaF12, boolean vPaseTarjeta) {
        
        UtilityPuntos.fideliza_cliente(pCodCampanaCupon,vTeclaF12, vPaseTarjeta,myParentFrame,this);
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
            //ImageIcon icon = new ImageIcon(this.getClass().getResource("logo_mf_btl.JPG"));
            this.setTitle("Lista de Productos y Precios " + VariablesConvenioBTLMF.vNomConvenio);
            log.debug("Nombre Cliente::" + VariablesConvenioBTLMF.vNomCliente);
            lblCliente.setText("" + VariablesConvenioBTLMF.vNomCliente);
            //lblMedicoT.setIcon(icon);
            //lblMedico.setText(" "+VariablesConvenioBTLMF.vNomConvenio );

        } else {
            evaluaTitulo();
        }        AuxiliarFidelizacion.setMensajeDNIFidelizado(lblMensajeCampa�a, "L", txtProducto, this);
        
    } 
    
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

    private void btnMetas_ActionPerformed(ActionEvent e) {
        String valorHabilitado = "";
        try {
            valorHabilitado = DBVendedor.verificaEstadoReporteMetas();
        } catch (SQLException f) {
            System.out.println("Error: "+f.getMessage());
        }
        if(valorHabilitado.equals("A")){
            DlgMetasVendedor dlgMetas = new DlgMetasVendedor(myParentFrame, "", true);
            dlgMetas.setVisible(true);
        }
    }
	//INI RARGUMEDO 2019-07-25
    private void muestra_MsjProducto(String codigoProducto) {
        String msj = UtilityPtoVenta.getArgumeto_Producto(codigoProducto);
        lblMsjArgumento_Prod.setText(msj);
    }
	//FIN RARGUMEDO 2019-07-25
	
    
    //  INICIO: JHAMRC 10072019
    public void procesaBolsa() {

        //txtProducto.setText("Se procesa los productos 1 x 1");
        VariablesVentas.vCostoICBPER = "";
        VariablesVentas.vTotalICBPER = "";
        ArrayList vLista = new ArrayList();

        try {
            
            //INI DMOSQUEIRA 20190710: REMOVER PRODUCTOS BOLSA
            /*for(int j = 0; j < VariablesISCBolsas.vArrayList_ProductosISC.size(); j++){
                UtilityISCBolsas.removeItemBolsa(VariablesVentas.vArrayList_PedidoVenta,
                                                 FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC,
                                                                                     j,
                                                                                     ConstantsISCBolsas.COL_CO_PRODUCTO));
            }*/
            //FIN DMOSQUEIRA 20190710
            
            ArrayList bolsas = new ArrayList();
                
            for (int i=0; i < VariablesISCBolsas.vArrayList_ProductosISC_Temp.size(); i++){
                bolsas = new ArrayList();
                bolsas.add(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 0));
                bolsas.add(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 4));
                bolsas.add("S");
                bolsas.add("1");
                bolsas.add(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 1));
                bolsas.add(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 6));
                vLista.add(bolsas);
            }

            if (vLista.size() > 0) {
                
                    for(int i=0;i<vLista.size();i++){
                    if(FarmaUtility.getValueFieldArrayList(vLista, i, 2).trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)){
                        int vFila = -1;
                        for(int j=0;j< tableModelListaPrecioProductos.data.size();j++){ 
                            if(FarmaUtility.getValueFieldArrayList(vLista, i, 0).trim().
                                    equalsIgnoreCase(FarmaUtility.getValueFieldArrayList( tableModelListaPrecioProductos.data, j, 1))){
                                    vFila=j;
                                    break;
                                    }
                            }

                        VariablesVentas.vCod_Prod = 
                                //FarmaUtility.getValueFieldArrayList(vLista, i, 0).trim();
                                FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
                        
                        ArrayList myArray = new ArrayList();
                        DBVentas.obtieneInfoProductoVta_RECETA(myArray, VariablesVentas.vCod_Prod);
                        VariablesVentas.vVal_Frac = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
                        VariablesVentas.vCod_Prod = 
                                FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD);
                        VariablesVentas.vDesc_Prod = 
                                ((String)(myJTable.getValueAt(vFila, 2))).trim();
                        VariablesVentas.vNom_Lab = 
                                ((String)(myJTable.getValueAt(vFila, 4))).trim();
                        VariablesVentas.vPorc_Igv_Prod = 
                                ((String)(myJTable.getValueAt(vFila, 11))).trim();
                        VariablesVentas.vCant_Ingresada_Temp = "0";
                        VariablesVentas.vNumeroARecargar = "";
                        VariablesVentas.vVal_Prec_Lista_Tmp = "";
                        VariablesVentas.vIndProdVirtual = FarmaConstants.INDICADOR_N;
                        VariablesVentas.vTipoProductoVirtual = "";
                        //sasas
                        VariablesVentas.vVal_Prec_Pub = 
                                ((String)(myJTable.getValueAt(vFila, 6))).trim();
                        VariablesVentas.vIndOrigenProdVta = 
                                FarmaUtility.getValueFieldJTable(myJTable, vFila, 
                                                                 COL_ORIG_PROD);
                        VariablesVentas.vPorc_Dcto_2 = "0";
                        VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;
                        VariablesVentas.vCantxDia = "";
                        VariablesVentas.vCantxDias = "";


                        VariablesVentas.vCod_Prod = FarmaUtility.getValueFieldArrayList(vLista, i, 0);
                           DlgIngresoCantidad dlgIngCantidad = new DlgIngresoCantidad(myParentFrame, "", true);
                        VariablesVentas.vIngresaCant_ResumenPed = false;
                           dlgIngCantidad.setCantInic(Integer.parseInt(FarmaUtility.getValueFieldArrayList(vLista, i, 1)));
                           //dlgIngCantidad.setSize(10, 10);
                           dlgIngCantidad.abreOpera();
                           ////////////selecciona product /////////
                           Boolean valorTmp = (Boolean)(myJTable.getValueAt(vFila, 0));
                        
                        //   DUBILLUZ 2016.10.27
                        VariablesVentas.vUnid_Vta =     ((String)((ArrayList)myArray.get(0)).get(6)).trim();
                        VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
                        
                        //   DUBILLUZ 2016.10.27
                           double auxCantIng = 
                               FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada);
                           double auxPrecVta = 
                               FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta);
                           
                        VariablesVentas.vTotalPrecVtaProd = (auxCantIng * auxPrecVta);
                        VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                        
                           setCheckValueRow(myJTable, false,vFila);
                           Boolean valor = (Boolean)(myJTable.getValueAt(vFila, 0));
                           
                            if (!UtilityVentas.buscar(VariablesVentas.vArrayList_Prod_Promociones, FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD)) &&
                                !UtilityVentas.buscar(VariablesVentas.vArrayList_Prod_Promociones_temporal, FarmaUtility.getValueFieldJTable(myJTable, vFila, COL_COD))) { //PACKVARIEDADREVERTIR
                                valor = (Boolean)(tblProductos.getValueAt(vFila, 0));
                            } else {
                                valor = true;
                            }
                            
                        VariablesVentas.vCostoICBPER = FarmaUtility.getValueFieldArrayList(vLista, i, 4);
                        VariablesVentas.vTotalICBPER = FarmaUtility.getValueFieldArrayList(vLista, i, 5);
                            
                        UtilityVentas.operaProductoSeleccionadoEnArrayList_02(valor, VariablesVentas.secRespStk); //ASOSA, 01.07.2010
                        VariablesVentas.vCostoICBPER = "";
                        VariablesVentas.vTotalICBPER = "";
                           pintaCheckOtroJTable(myJTable, valorTmp);
                           colocaTotalesPedido();
                           //FarmaUtility.showMessage(this,"VariablesVentas.vCod_Prod " + VariablesVentas.vCod_Prod,txtProducto);

                        //VariablesVentas.vIndDireccionarResumenPed = false;
                        //   FarmaVariables.vAceptar = false;   
                    }
                }
                VariablesVentas.vIndDireccionarResumenPed = true;
                VariablesVentas.vIndF11 = true;
                aceptaOperacion();
                
            }
        } catch (Exception sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        }
        
        aceptaOperacion();
    }
	// FIN: JHAMRC 10072019

}
