package mifarma.ptoventa.ventas;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;

import farmapuntos.bean.BeanTarjeta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
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

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.fidelizacion.reference.UtilityFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.ventas.reference.BeanDetalleVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityCalculoPrecio;
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
 * Nombre de la Aplicaci�n : DlgIngresoCantidad.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * LMESIA      28.12.2005   Creaci�n<br>
 * <br>
 * @author Luis Mesia Rivera<br>
 * @version 1.0<br>
 *
 */

public class DlgIngresoCantidad_OLD extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgIngresoCantidad_OLD.class);

    private int cantInic = 0;
    // Bjct, 27-DIC-12, si precio es cero no Vender
    private boolean vbPrecProdOk = true;
    // Bjct, 27-DIC-12


    /** Objeto Frame de la Aplicaci�n */
    Frame myParentFrame;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    JPanel pnlStock = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JLabel lblUnidades = new JLabel();
    JLabel lblStock = new JLabel();
    JLabel lblFechaHora = new JLabel();
    JLabel lblStockTexto = new JLabel();
    JPanel pnlDetalleProducto = new JPanel();
    XYLayout xYLayout1 = new XYLayout();
    JTextField txtPrecioVenta = new JTextField();
    JLabel lblUnidadT = new JLabel();
    JLabel lblDescripcionT = new JLabel();
    JLabel lblCodigoT = new JLabel();
    JLabel lblLaboratorio = new JLabel();
    JLabel lblDcto = new JLabel();
    JLabel lblLaboratorioT = new JLabel();
    JLabel lblDscto = new JLabel();
    public JTextField txtCantidad = new JTextField();
    JLabel lblUnidad = new JLabel();
    JLabel lblDescripcion = new JLabel();
    JLabel lblCodigo = new JLabel();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JButtonLabel btnCantidad = new JButtonLabel();
    private JButtonLabel btnPrecioVta = new JButtonLabel();
    private JLabelOrange lblPrecVtaConv = new JLabelOrange();
    private JLabelOrange T_lblPrecVtaConv = new JLabelOrange();
    private JLabelOrange lblProdCupon = new JLabelOrange();
    private JLabelOrange lblPrecioProdCamp = new JLabelOrange();

    private double pPrecioFidelizacion = 0.0;
    private JLabel jLabel1 = new JLabel();
    private JLabel lblMensajeCampa�a = new JLabel();
    private JTextField txtPrecioVentaRedondeado = new JTextField();

    private String tipoProducto;
    private JEditorPane jepPrecio = new JEditorPane();
	private boolean actuaRobot;//LTAVARA 08/11/2016 **
        
    
    //INI ASOSA - 24/03/2017 - PACKVARIEDAD
    private String indTipoVenta = ConstantsVentas.IND_PROD_NORMAL;
    private String indTipoPack = "";
    private JScrollPane scrListaPack = new JScrollPane();
    private JButtonLabel btlListaPack = new JButtonLabel();
    private JTable tblListaPack = new JTable();
    private FarmaTableModel tableModelListaPromociones;
    private final int COL_COD = 0;
    //FIN ASOSA - 24/03/2017 - PACKVARIEDAD
    // **************************************************************************
    // Constructores
    // **************************************************************************

    /**
     *Constructor
     */
    public DlgIngresoCantidad_OLD() {
        this(null, "", false);
    }

    /**
     *Constructor
     *@param parent Objeto Frame de la Aplicaci�n.
     *@param title T�tulo de la Ventana.
     *@param modal Tipo de Ventana.
     */
    public DlgIngresoCantidad_OLD(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
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

    /**
     *Implementa la Ventana con todos sus Objetos
     */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(661, 553));
        this.getContentPane().setLayout(borderLayout1);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Ingreso de Cantidad");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(360, 331));
        jContentPane.setBackground(Color.white);
        pnlStock.setBounds(new Rectangle(15, 20, 625, 55));
        pnlStock.setFont(new Font("SansSerif", 0, 11));
        pnlStock.setBackground(new Color(255, 130, 14));
        pnlStock.setLayout(xYLayout2);
        pnlStock.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock.setForeground(Color.white);
        lblUnidades.setText("unidades");
        lblUnidades.setFont(new Font("SansSerif", 1, 14));
        lblUnidades.setForeground(Color.white);
        lblStock.setText("10");
        lblStock.setFont(new Font("SansSerif", 1, 15));
        lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
        lblStock.setForeground(Color.white);
        lblFechaHora.setText("12/01/2006 09:20:34");
        lblFechaHora.setFont(new Font("SansSerif", 0, 12));
        lblFechaHora.setForeground(Color.white);
        lblStockTexto.setText("Stock del Producto al");
        lblStockTexto.setFont(new Font("SansSerif", 0, 12));
        lblStockTexto.setForeground(Color.white);
        pnlDetalleProducto.setBounds(new Rectangle(15, 80, 625, 405));
        pnlDetalleProducto.setLayout(xYLayout1);
        pnlDetalleProducto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlDetalleProducto.setFont(new Font("SansSerif", 0, 11));
        pnlDetalleProducto.setBackground(Color.white);
        txtPrecioVenta.setHorizontalAlignment(JTextField.RIGHT);
        txtPrecioVenta.setFont(new Font("SansSerif", 1, 11));
        txtPrecioVenta.setEnabled(false);
        txtPrecioVenta.setText("13.20");
        txtPrecioVenta.setVisible(false);
        txtPrecioVenta.setText("0");
        txtPrecioVenta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtPrecioVenta_keyPressed(e);
            }
        });
        lblUnidadT.setText("Unidad");
        lblUnidadT.setFont(new Font("SansSerif", 1, 11));
        lblDescripcionT.setText("Descripcion");
        lblDescripcionT.setFont(new Font("SansSerif", 1, 11));
        lblCodigoT.setText("Codigo");
        lblCodigoT.setFont(new Font("SansSerif", 1, 11));
        lblLaboratorio.setText("COLLIERE S.A.");
        lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
        lblDcto.setText("10.00");
        lblDcto.setHorizontalAlignment(SwingConstants.LEFT);
        lblDcto.setFont(new Font("SansSerif", 0, 11));
        lblLaboratorioT.setText("Laboratorio :");
        lblLaboratorioT.setFont(new Font("SansSerif", 1, 11));
        lblDscto.setText("% Dcto. :");
        lblDscto.setFont(new Font("SansSerif", 1, 11));
        txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
        txtCantidad.setDocument(new FarmaLengthText(6));
        txtCantidad.setText("0");
        txtCantidad.setFont(new Font("SansSerif", 1, 11));
        txtCantidad.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtCantidad_keyPressed(e);
                }

            public void keyTyped(KeyEvent e) {
                txtCantidad_keyTyped(e);
            }

                public void keyReleased(KeyEvent e) {
                    txtCantidad_keyReleased(e);
                }
            });
        txtCantidad.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                txtCantidad_focusLost(e);
            }
        });
        lblUnidad.setText(" ");
        lblUnidad.setFont(new Font("SansSerif", 0, 11));
        lblDescripcion.setText(" ");
        lblDescripcion.setFont(new Font("SansSerif", 0, 11));
        lblCodigo.setText(" ");
        lblCodigo.setFont(new Font("SansSerif", 0, 11));
        lblF11.setText("[ ENTER ] Aceptar");
        lblF11.setBounds(new Rectangle(140, 495, 135, 20));
        lblF11.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    lblF11_keyPressed(e);
                }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(285, 495, 85, 20));
        btnCantidad.setText("Cantidad :");
        btnCantidad.setForeground(Color.black);
        btnCantidad.setMnemonic('c');
        btnCantidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCantidad_actionPerformed(e);
            }
        });
        btnPrecioVta.setText("Precio Venta : "+ConstantesUtil.simboloSoles);
        btnPrecioVta.setForeground(Color.black);
        btnPrecioVta.setMnemonic('p');
        btnPrecioVta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPrecioVta_actionPerformed(e);
            }
        });
        lblPrecVtaConv.setForeground(Color.black);
        T_lblPrecVtaConv.setText("P. Vta. Conv.:");
        T_lblPrecVtaConv.setForeground(Color.black);
        lblPrecioProdCamp.setForeground(Color.red);
        lblPrecioProdCamp.setFont(new Font("SansSerif", 1, 15));
        jLabel1.setText("jLabel1");
        lblMensajeCampa�a.setVisible(false);
        lblMensajeCampa�a.setForeground(Color.red);
        lblMensajeCampa�a.setFont(new Font("Dialog", 1, 13));
        lblMensajeCampa�a.setText("     Producto se encuentra en campa�a  \" Acumula tu Compra y Gana !\"");
        txtPrecioVentaRedondeado.setHorizontalAlignment(JTextField.RIGHT);
        txtPrecioVentaRedondeado.setFont(new Font("SansSerif", 1, 11));
        txtPrecioVentaRedondeado.setEnabled(false);
        txtPrecioVentaRedondeado.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtPrecioVenta_keyPressed(e);
            }
        });
        txtPrecioVentaRedondeado.setVisible(true);
        jepPrecio.setBounds(new Rectangle(25, 375, 490, 70));
        jepPrecio.setContentType("text/html");
        jepPrecio.setBorder(BorderFactory.createTitledBorder("Precio Venta referencial "));
        jepPrecio.setText("<style type=\"text/css\"> body {     font-family: 'trebuchet MS', 'Lucida sans', Arial;     color: #444;     margin: 0px auto; } table {     border-spacing: 0;     font-size: 20; } table td{     width: 200px; } .bordered td, .bordered th {     padding: 0px; }.bordered th {     background-color: #ffefd6; }</style><div> <table border=0 class=\"bordered\"> <tr><td></td><td align=\"left\">Precio Venta</td><td align=\"left\">S/ 10</td><td></td></tr> <tr><td></td><td align=\"left\">Total</td><td align=\"left\">S/ 10</td><td></td></tr> <tr><td></td><td align=\"left\">Ahorro</td><td align=\"left\">S/ 10</td><td></td></tr> <tr><td></td><td align=\"left\">Descuento</td><td align=\"left\">10%</td><td></td></tr> </table>");
        pnlStock.add(lblUnidades, new XYConstraints(230, 10, 75, 20));
        pnlStock.add(lblStock, new XYConstraints(140, 5, 80, 30));
        pnlStock.add(lblFechaHora, new XYConstraints(5, 20, 130, 20));
        pnlStock.add(lblStockTexto, new XYConstraints(5, 0, 125, 20));
        pnlDetalleProducto.add(btlListaPack, new XYConstraints(19, 229, 80, 15));
        scrListaPack.getViewport().add(tblListaPack, null);
        pnlDetalleProducto.add(scrListaPack, new XYConstraints(14, 254, 590, 105));
        pnlDetalleProducto.add(lblPrecioProdCamp, new XYConstraints(179, 124, 305, 30));
        pnlDetalleProducto.add(lblProdCupon, new XYConstraints(199, 189, 285, 15));
        pnlDetalleProducto.add(T_lblPrecVtaConv, new XYConstraints(9, 184, 80, 15));
        pnlDetalleProducto.add(lblPrecVtaConv, new XYConstraints(99, 184, 80, 15));


        //Agregado Por FRAMIREZ 11.01.2012 oculta el precio de una venta normal
        pnlDetalleProducto.add(btnCantidad, new XYConstraints(184, 369, 60, 20));
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
            btnPrecioVta.setVisible(false);
        } else {
            btnPrecioVta.setVisible(true);
            pnlDetalleProducto.add(btnPrecioVta, new XYConstraints(9, 159, 100, 20));
        }

        pnlDetalleProducto.add(lblUnidadT, new XYConstraints(354, 55, 50, 20));
        pnlDetalleProducto.add(lblDescripcionT, new XYConstraints(10, 55, 95, 20));
        pnlDetalleProducto.add(lblCodigoT, new XYConstraints(10, 10, 55, 20));
        pnlDetalleProducto.add(lblLaboratorio, new XYConstraints(89, 99, 280, 20));
        pnlDetalleProducto.add(lblDcto, new XYConstraints(59, 129, 40, 20));
        pnlDetalleProducto.add(lblLaboratorioT, new XYConstraints(10, 100, 80, 20));
        pnlDetalleProducto.add(lblDscto, new XYConstraints(9, 129, 50, 20));
        pnlDetalleProducto.add(txtCantidad, new XYConstraints(249, 369, 50, 20));
        pnlDetalleProducto.add(lblUnidad, new XYConstraints(354, 74, 135, 20));
        pnlDetalleProducto.add(lblDescripcion, new XYConstraints(10, 75, 270, 20));
        pnlDetalleProducto.add(lblCodigo, new XYConstraints(10, 30, 55, 20));
        pnlDetalleProducto.add(lblMensajeCampa�a, new XYConstraints(-1, 224, 500, 25));


        log.debug("�VariablesConvenioBTLMF.vCodConvenio?" + VariablesConvenioBTLMF.vCodConvenio);


        //Agregado Por FRAMIREZ 11.01.2012 oculta el precio de una venta normal
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {

            //if(VariablesVentas.vEstadoProdConvenio.equals("I"))

            txtPrecioVentaRedondeado.setVisible(false);
            txtPrecioVenta.setVisible(false);
            T_lblPrecVtaConv.setVisible(true);

        } else {
            T_lblPrecVtaConv.setVisible(false);
            pnlDetalleProducto.add(txtPrecioVentaRedondeado, new XYConstraints(109, 159, 60, 20));
            pnlDetalleProducto.add(txtPrecioVenta, new XYConstraints(109, 134, 60, 20));


        }
        jepPrecio.setText("");

        jepPrecio.setForeground(new Color(231, 0, 0));

        scrListaPack.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    scrListaPack_keyPressed(e);
                }
            });
        btlListaPack.setText("Lista Packs:");
        btlListaPack.setForeground(new Color(255, 130, 14));
        btlListaPack.setMnemonic('l');
        btlListaPack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btlListaPack_actionPerformed(e);
                }
            });
        tblListaPack.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblListaPack_keyPressed(e);
                }
            });
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        //jContentPane.add(jepPrecio, null); ASOSA - 27/03/2017 - PACKVARIEDAD
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(pnlStock, null);
        jContentPane.add(pnlDetalleProducto, null);
        //this.getContentPane().add(jContentPane, null);
    }

    // **************************************************************************
    // M�todo "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableListaPromociones();
        listarPacks();
    }
    
    private void initTableListaPromociones() {
        tableModelListaPromociones =
                new FarmaTableModel(ConstantsVentas.columnsListaPromociones, ConstantsVentas.defaultValuesListaPromociones,
                                    COL_COD);
        FarmaUtility.initSimpleList(tblListaPack, tableModelListaPromociones,
                                    ConstantsVentas.columnsListaPromociones);
    }
    
    private void listarPacks() {
        UtilityVentas.listaPromocionesPorProductoV2(tableModelListaPromociones,
                                                    VariablesVentas.vCod_Prod,
                                                    VariablesFidelizacion.vDniCliente.trim());
    }

    // **************************************************************************
    // M�todos de inicializaci�n
    // **************************************************************************

    private void obtieneInfoProdEnArrayList(ArrayList pArrayList) {
        try {
            //ERIOS 06.06.2008 Soluci�n temporal para evitar la venta sugerida por convenio
            if (VariablesVentas.vEsPedidoConvenio) {
                DBVentas.obtieneInfoDetalleProducto(pArrayList, VariablesVentas.vCod_Prod);
            } else {
                DBVentas.obtieneInfoDetalleProductoVta(pArrayList, VariablesVentas.vCod_Prod);
            }

        } catch (SQLException sql) {
            log.error(null, sql.fillInStackTrace());
            FarmaUtility.showMessage(this, "Error al obtener informacion del producto. \n" +
                    sql.getMessage(), txtCantidad);
        }
    }

    private void muestraInfoDetalleProd() {
        ArrayList myArray = new ArrayList();
        obtieneInfoProdEnArrayList(myArray);
        if (myArray.size() == 1) {
            VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
            VariablesVentas.vStk_Prod_Fecha_Actual = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
            //DUBILLUZ 24.07.2014
            if (( /* !VariablesVentas.vEsPedidoDelivery &&  */!VariablesVentas.vEsPedidoInstitucional) ||
                !VariablesVentas.vIngresaCant_ResumenPed) {

                //JCORTEZ 11/04/08 no se actualiza el precio y descuento si es producto  oferta
                //if(!VariablesVentas.vIndOrigenProdVta.equals(ConstantsVentas.IND_ORIGEN_OFER)||!VariablesVentas.vEsProdOferta)

                // Segun gerencia se debe seguir la misma logica para todos los productos.
                if (VariablesVentas.vVentanaListadoProductos) {
                    log.debug("SETEANDO DESCUENTO");
                    /*
                    VariablesVentas.vVal_Prec_Vta =
                            ((String)((ArrayList)myArray.get(0)).get(3)).trim();
                    */

                    double precio =
                        FarmaUtility.getDecimalNumber(((String)((ArrayList)myArray.get(0)).get(3)).trim()) *
                        FarmaUtility.getDecimalNumber(VariablesVentas.vValorMultiplicacion); //ASOSA - 12/08/2014
                    VariablesVentas.vVal_Prec_Vta = "" + precio; //ASOSA - 12/08/2014


                    VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(6)).trim();

                } else {
                    if (UtilityVentas.isAplicoPrecioCampanaCupon(lblCodigo.getText().trim(),
                                                                 FarmaConstants.INDICADOR_S)) {
                        if (!VariablesVentas.vVentanaOferta) {
                            log.debug("SETEANDO DESCUENTO");
                            /*
                            VariablesVentas.vVal_Prec_Vta =
                                    ((String)((ArrayList)myArray.get(0)).get(3)).trim();
                            */

                            double precio =
                                FarmaUtility.getDecimalNumber(((String)((ArrayList)myArray.get(0)).get(3)).trim()) *
                                FarmaUtility.getDecimalNumber(VariablesVentas.vValorMultiplicacion); //ASOSA - 12/08/2014
                            VariablesVentas.vVal_Prec_Vta = "" + precio; //ASOSA - 12/08/2014


                            VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(6)).trim();
                        }
                    }
                }


                log.info("DlgIngresoCantidad: VariablesVentas.vPorc_Dcto_1 - " + VariablesVentas.vPorc_Dcto_1);
                log.debug("VariablesVentas.vPorc_Dcto_2 : " + VariablesVentas.vPorc_Dcto_2);
            }
            VariablesVentas.vUnid_Vta = ((String)((ArrayList)myArray.get(0)).get(4)).trim();
            VariablesVentas.vVal_Bono = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
            VariablesVentas.vVal_Prec_Lista = ((String)((ArrayList)myArray.get(0)).get(7)).trim();

            setearValoresAdicionales();

        } else {
            VariablesVentas.vStk_Prod = "0";
            VariablesVentas.vDesc_Acc_Terap = "";
            VariablesVentas.vStk_Prod_Fecha_Actual = "";
            VariablesVentas.vVal_Prec_Vta = "";
            VariablesVentas.vUnid_Vta = "";
            VariablesVentas.vPorc_Dcto_1 = "";
            VariablesVentas.vVal_Prec_Lista = "";
            VariablesVentas.vNom_Lab = "";
            VariablesVentas.vDesc_Prod = "";
            VariablesVentas.vCod_Prod = "";
            FarmaUtility.showMessage(this, "Error al obtener Informacion del Producto", null);
            cerrarVentana(false);
        }


        lblFechaHora.setText(VariablesVentas.vStk_Prod_Fecha_Actual);

        if (!VariablesVentas.existenValoresAdicionales) { //ASOSA - 12/08/2014
            lblStock.setText("" +
                             (Integer.parseInt(VariablesVentas.vStk_Prod) / Integer.parseInt(VariablesVentas.vValorMultiplicacion) +
                              cantInic));
        } else {
            lblStock.setText("" + (Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic));
        }

        //lblStock.setText(VariablesVentas.vStk_Prod);
        lblCodigo.setText(VariablesVentas.vCod_Prod);
        lblDescripcion.setText(VariablesVentas.vDesc_Prod);
        lblLaboratorio.setText(VariablesVentas.vNom_Lab);
        lblUnidad.setText(VariablesVentas.vUnid_Vta);
        txtPrecioVenta.setText(VariablesVentas.vVal_Prec_Vta); //JCHAVEZ 29102009 se cambio txtPrecioVenta por txtPrecioVentaOculto
        lblDcto.setText(VariablesVentas.vPorc_Dcto_1);
        txtCantidad.setText("" + cantInic);
        //JCHAVEZ 29102009 inicio
        try {
            //double precVtaRedondeadoNum = DBVentas.getPrecioRedondeado(Double.parseDouble(VariablesVentas.vVal_Prec_Vta));  antes

            // Bjct, 27-DIC-12, setar flag de precio correcto
            String vsPrecMinVta = DBVentas.getPrecioMinimoVta();
            if (vsPrecMinVta.equalsIgnoreCase("N")) {
                FarmaUtility.showMessage(this, "Error, No se puede Leer el Valor M�nimo de Venta...", txtCantidad);
                return;
            }
            double vdPrecOrigonal = FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta);
            double vdPrecMinVta = Double.valueOf(vsPrecMinVta).doubleValue();

            if (vdPrecOrigonal < vdPrecMinVta) {
                vbPrecProdOk = false;
            }
            // Ejct, 27-DIC-12

            double precVtaRedondeadoNum =
                DBVentas.getPrecioRedondeado(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta)); //ASOSA, 18.06.2010

            String precVtaRedondeadoStr =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + precVtaRedondeadoNum), 3);
            if (!VariablesConvenio.vVal_Prec_Vta_Conv.trim().equalsIgnoreCase("")) {
                log.debug("VariablesConvenio.vVal_Prec_Vta_Conv: " + VariablesConvenio.vVal_Prec_Vta_Conv);
                double precVtaConvRedondeadoNum =
                    DBVentas.getPrecioRedondeado(FarmaUtility.getDecimalNumber(VariablesConvenio.vVal_Prec_Vta_Conv));
                String precVtaConvRedondeadoStr =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + precVtaConvRedondeadoNum), 3);
                lblPrecVtaConv.setText(precVtaConvRedondeadoStr); //JCHAVEZ 29102009 se cambio VariablesConvenio.vVal_Prec_Vta_Conv por precVtaConvRedondeadoStr

            }
            this.txtPrecioVentaRedondeado.setText(precVtaRedondeadoStr);
        } catch (SQLException sql) {
            log.error("", sql);
        }
        //JCHAVEZ 29102009 fin

        if (!VariablesVentas.vEsPedidoConvenio ||
            VariablesVentas.vIndOrigenProdVta.equals(ConstantsVentas.IND_ORIGEN_OFER)) {
            T_lblPrecVtaConv.setVisible(false);
            lblPrecVtaConv.setVisible(false);
        }


    }

    /**
     * Setea los valores adicionales en las variables si es que existen.
     * La primera vez entrara el producto porque la variable de codigo de barras tiene datos, las demas veces ya no
     * @author ASOSA
     * @since 06/08/2014
     */
    public void setearValoresAdicionales() {

        String[] list = UtilityVentas.obtenerArrayValoresBd(ConstantsVentas.TIPO_VAL_ADIC_COD_BARRA);
        String new_frac = "";
        String new_desc = "";
        String new_precio = "";
        String stockLocal = "";
        String fracProdLocal = "";
        if (!list[0].equals("THERE ISNT")) {
            new_frac = list[0];
            new_desc = list[1];
            new_precio = list[2];
            stockLocal = list[3];
            fracProdLocal = list[4];

            VariablesVentas.vStk_Prod = "" + (Integer.parseInt(stockLocal) / Integer.parseInt(new_frac));

            //Double valMult = 1.00 / Integer.parseInt(new_frac);
            VariablesVentas.vValorMultiplicacion = new_frac.trim();
            //VariablesVentas.vVal_Frac = "" + valMult;
            VariablesVentas.vDesc_Prod = VariablesVentas.vDesc_Prod + " " + new_desc;
            VariablesVentas.vVal_Prec_Vta = new_precio.trim();

            VariablesVentas.existenValoresAdicionales = true;
        } else {
            //VariablesVentas.vValorMultiplicacion = "1";
            VariablesVentas.existenValoresAdicionales = false;
        }
        VariablesVentas.vCodigoBarra = "";
        log.info("VariablesVentas.vCodigoBarra 09: " + VariablesVentas.vCodigoBarra);
        log.info("VariablesVentas.vVal_Frac" + VariablesVentas.vVal_Frac);
        log.info("VariablesVentas.vUnid_Vta" + VariablesVentas.vUnid_Vta);
        log.info("VariablesVentas.vVal_Prec_Vta" + VariablesVentas.vVal_Prec_Vta);
        log.info("VariablesVentas.vStk_Prod" + VariablesVentas.vStk_Prod);

    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void txtPrecioVenta_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtCantidad);
        }

    }

    private void txtCantidad_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblListaPack, new JTextField(), 2);
        chkKeyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblListaPack.getSelectedRow() >= 0) {
                VariablesVentas.vCodProm = FarmaUtility.getValueFieldArrayList(tableModelListaPromociones.data, tblListaPack.getSelectedRow(), 0);
                if (VariablesVentas.vCodProm.equals("000000")) {
                    indTipoVenta = ConstantsVentas.IND_PROD_NORMAL;                             
                    // Bjct, 27-DIC-12, si producto tiene precio inferior al minimo then error
                    if (!vbPrecProdOk) {
                            FarmaUtility.showMessage(this,
                                                                             "No se puede Vender un Producto con Precio inferior al M�nimo Vigente...",
                                                                             txtCantidad);
                            return;
                    }
                    // Ejct, 27-DIC-12, si producto tiene precio inferior al minimo then error
                    aceptaCantidadIngresada();
                } else {
                    indTipoVenta = ConstantsVentas.IND_PROD_PACK;
                    indTipoPack = FarmaUtility.getValueFieldArrayList(tableModelListaPromociones.data, tblListaPack.getSelectedRow(), 10);
                    DlgDetalleVariedad dlgDetalleVariedad = new DlgDetalleVariedad(myParentFrame, "", true, indTipoPack);
                    dlgDetalleVariedad.setCodProd(VariablesVentas.vCod_Prod);
                    dlgDetalleVariedad.txtCantidad.setText(txtCantidad.getText());
                    //dlgDetalleVariedad.setIndTipoPack(indTipoPack);
                    VariablesVentas.vCod_Prom = FarmaUtility.getValueFieldArrayList(tableModelListaPromociones.data, tblListaPack.getSelectedRow(), 0);
                    VariablesVentas.vDesc_Prom = FarmaUtility.getValueFieldArrayList(tableModelListaPromociones.data, tblListaPack.getSelectedRow(), 1);
                    
                    if (indTipoPack.equals(ConstantsVentas.IND_PACK_SIMPLE) || 
                                           indTipoPack.equals(ConstantsVentas.IND_MULTIPACK)) {
                        //PACKS SIMPLES Y MULTIPACKS, DEFRENTE SE SETEA LA CANTIDAD Y QUE HAGA LO SUYO
                        dlgDetalleVariedad.setVisible(false);
                        dlgDetalleVariedad.txtCantidad_keyPressed(new KeyEvent(txtCantidad, KeyEvent.KEY_PRESSED, 
                                                                  0,                          // When timeStamp
                                                                  0,                          // Modifier
                                                                  KeyEvent.VK_F11,      // Key Code
                                                                  KeyEvent.CHAR_UNDEFINED ));
                        
                    } else if (indTipoPack.equals(ConstantsVentas.IND_PACK_VARIEDAD)) {
                        //PACK VARIEDAD, EL USUARIO TENDRA QUE ARMAR EL PACK DE ACUERDO CON EL CLIENTE
                        if (validaCantidadIngreso()) {
                            dlgDetalleVariedad.setVisible(true);
                        } else {
                            FarmaUtility.showMessage(this, "Ingrese una cantidad correcta.", txtCantidad);
                        }
                        
                    }
                    
                    
                    if (FarmaVariables.vAceptar) {
                        cerrarVentana(true);
                    }
                }
            } else {
                FarmaUtility.showMessage(this, 
                                         "Seleccione una fila", 
                                         tblListaPack);
            }                        
        }

    }
    
    
    private void this_windowOpened(WindowEvent e) {
        VariablesVentas.vCant_Ingresada_ItemQuote="";
        //FarmaUtility.moveFocus(txtCantidad);
        log.debug("VariablesVentas.vVal_Prec_Vta;" + VariablesVentas.vVal_Prec_Vta);        
        this.setLocation(this.getX(), this.getY() - 75);
        VariablesVentas.vCant_Ingresada_Temp = "0";
        cantInic = FarmaUtility.trunc(FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada_Temp));

        //segun sea el flg nuevo convenios BTL cargara el detalle
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {

            muestraInfoDetalleProd_Btl();

        } else {
            muestraInfoDetalleProd();
        }
        evaluaTipoPedido();
        //JCORTEZ 17.04.08
        lblDscto.setVisible(false);
        lblDcto.setVisible(false);

        /*if(!VariablesVentas.vEsPedidoDelivery && !VariablesVentas.vEsPedidoInstitucional)
        FarmaUtility.moveFocus(txtCantidad);
    else
        FarmaUtility.moveFocus(txtPrecioVenta);        */
        muestraMaxProdCupon();
        calculoNuevoPrecio();


        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {

        } else {
            ///---
            if (isExisteProdCampana(VariablesVentas.vCod_Prod)) {
                lblMensajeCampa�a.setVisible(true);
            } else
                lblMensajeCampa�a.setVisible(false);
            ///---
        }
        


        //if (VariablesPtoVenta.vIndTico.equals("S") && SE COMENTO X MIENTRAS PORQUE QUIEREN YAYAYAYA QUE SEA TICO Y SE COMPORTE COMO FARMA - ASOSA 23/10/2014
        //  !VariablesVentas.tipoLlamada.equals("1")) { //ASOSA - 09/10/2014
        if (false) {
            txtCantidad.setText("1");
            lblF11_keyPressed(null);
            VariablesVentas.tipoLlamada = "0";
        }

        //INI ASOSA - 06/01/2014 - RIMAC
        if (VariablesVentas.vCantMesRimac > 0 &&
            !VariablesVentas.vDniRimac.equals("")){
            txtCantidad.setText("" + VariablesVentas.vCantMesRimac);
            if (!vbPrecProdOk) {
                FarmaUtility.showMessage(this,
                                         "No se puede Vender un Producto con Precio inferior al M�nimo Vigente...",
                                         txtCantidad);
                return;
            }            
            aceptaCantidadIngresada();
        }
        //FIN ASOSA - 06/01/2014 - RIMAC
        FarmaUtility.moveFocus(tblListaPack);
        FarmaGridUtils.showCell(tblListaPack, 0, 0);
        log.debug("fin window opened ing cantidad: " + VariablesVentas.vArrayList_PedidoVenta);
        FarmaUtility.centrarVentana(this);
        this.repaint();
    }

    private void btnCantidad_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtCantidad);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void btnPrecioVta_actionPerformed(ActionEvent e) {
        if (VariablesVentas.vEsPedidoDelivery || VariablesVentas.vEsPedidoInstitucional)
            FarmaUtility.moveFocus(txtPrecioVenta);
    }

    private void txtCantidad_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCantidad, e);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    // **************************************************************************
    // Metodos de l�gica de negocio
    // **************************************************************************

    private boolean validaCantidadIngreso() {
        boolean valor = false;
        String cantIngreso = txtCantidad.getText().trim();
        if (FarmaUtility.isInteger(cantIngreso) && Integer.parseInt(cantIngreso) > 0)
            valor = true;
        return valor;
    }

    private boolean validaPrecioIngreso() {
        boolean valor = false;
        String precioIngreso = txtPrecioVenta.getText().trim();
        if (FarmaUtility.isDouble(precioIngreso) && FarmaUtility.getDecimalNumber(precioIngreso) > 0)
            valor = true;
        return valor;
    }

    private boolean validaStockActual() {
        boolean valor = false;
        //if (!VariablesVentas.existenValoresAdicionales) {   //ASOSA - 08/08/2014
        obtieneStockProducto();
        //}
        VariablesVentas.vStk_Prod =
                "" + Integer.parseInt(VariablesVentas.vStk_Prod) / Integer.parseInt(VariablesVentas.vValorMultiplicacion);

        String cantIngreso = txtCantidad.getText().trim();

        if ((Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic) >= Integer.parseInt(cantIngreso))
            valor = true;
        //kmoncada valida si es producto virtual (balanza keito)
        if (!valor && !VariablesVentas.vIndProdControlStock) {
            valor = true;
        }

        return valor;
    }

    private void aceptaCantidadIngresada() {
        ArrayList listaProductos;
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        VariablesVentas.vIndAplicaPrecNuevoCampanaCupon = FarmaConstants.INDICADOR_N;
        
        boolean pAactivoVtaNegativa = false;


        if (!validaCantidadIngreso()) {
            FarmaUtility.showMessage(this, "Ingrese una cantidad correcta.", txtCantidad);
            return;
        }
        VariablesVentas.vCant_Ingresada_ItemQuote=txtCantidad.getText(); //LTAVARA 2016.11.14 
        
        BeanTarjeta tarjetaBean=null;
            if( VariablesPuntos.frmPuntos!=null){
                    tarjetaBean=VariablesPuntos.frmPuntos.getBeanTarjeta();
                }
           
        // LTAVARA 2016.07.26 - VALIDAR SI EL PRODUCTO PARTICIPA EN X+1
        if (validaCantidadIngreso() && UtilityPuntos.isActivoFuncionalidad() && tarjetaBean!= null) {
            if(!isActuaRobot()){
            int cantidadIngresada=Integer.parseInt(txtCantidad.getText());
            listaProductos = (ArrayList<String>)tarjetaBean.getListaAuxiliarInscritos();
            //Validar si el producto participa en x+1 de los programas inscritos del cliente monedero
            if (facadeLealtad.verificaInscripcionProducto(listaProductos, lblCodigo.getText())!= "") {
                    boolean a;
                     try {
                       String cantProdFracLocal = DBPuntos.getCantProdFracLocal(cantidadIngresada, lblCodigo.getText(),VariablesVentas.vVal_Frac, "ENVIAR");
                         if(esDecimal( cantProdFracLocal) && !cantProdFracLocal.equals("0")){
                         //OBETENER LA CANTIDAD A LLEVAR VALIDADO POR CON EL WS ITEMQUOTE
                        //cantidadIngresada= UtilityPuntos.cantidadBonificarCotiza(myParentFrame, this, Integer.parseInt(cantProdFracLocal), lblCodigo.getText(),VariablesVentas.vVal_Frac,lblUnidad.getText(),true);
                         }
                     } catch (Exception e) {
                         log.info(e.getMessage());
                     }
                     txtCantidad.setText(cantidadIngresada+"");
                    // return;
                }
            }
        }
 
        
        if (!validaStockActual()) {
            FarmaUtility.liberarTransaccion();

            //INI ASOSA - 03/10/2014 - PANHD
            tipoProducto = VariablesVentas.tipoProducto; //ASOSA - 09/10/2014
            if (tipoProducto.trim().equals(ConstantsVentas.TIPO_PROD_FINAL)) {
                int cantidad = (int)Integer.parseInt(txtCantidad.getText().trim());
                if (!UtilityVentas.existsStockComp(this,VariablesVentas.vCod_Prod, cantidad,txtCantidad)) {
                    FarmaUtility.showMessage(this, "No hay stock suficiente para vender el producto", txtCantidad);
                    lblStock.setText("" + (Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic));
                    return;
                } else {
                    pAactivoVtaNegativa = true;
                }
            } else {
                //FIN ASOSA - 03/10/2014 - PANHD
                if (!UtilityVentas.permiteVtaNegativa(myParentFrame, this, VariablesVentas.vCod_Prod,
                                                      txtCantidad.getText().trim(), VariablesVentas.vVal_Frac)) {
                    FarmaUtility.showMessage(this, "La cantidad ingresada no puede ser mayor al Stock del Producto.",
                                             txtCantidad);
                    lblStock.setText("" + (Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic));
                    return;
                } else {
                    pAactivoVtaNegativa = true;
                }
            }

        }


        VariablesVentas.vCant_Ingresada = txtCantidad.getText().trim();
        if ((VariablesVentas.vEsPedidoDelivery || VariablesVentas.vEsPedidoInstitucional) && !validaPrecioIngreso()) {
            FarmaUtility.showMessage(this, "Ingrese un precio correcto.", txtPrecioVenta);
            return;
        }
        //VariablesVentas.vVal_Prec_Vta = txtPrecioVenta.getText().trim();

        VariablesVentas.vVal_Prec_Vta = getAnalizaPrecio(txtPrecioVenta.getText().trim(), pPrecioFidelizacion);


        if (VariablesVentas.vEsPedidoDelivery || VariablesVentas.vEsPedidoInstitucional)
            calculoNuevoDescuento();

        /**Nueva Modificaci�n
     * @Autor: Luis Reque Orellana
     * @Fecha: 16-03-2007
     */
        /**---INCIO----*/
        String indControlPrecio = "";
        //VariablesConvenio.vCodConvenio = "0000000001";
        if (!UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null)) {

            if (!VariablesConvenio.vCodConvenio.equalsIgnoreCase("") &&
                !VariablesVentas.vIndOrigenProdVta.equals(ConstantsVentas.IND_ORIGEN_OFER)) //Se ha seleccionado un convenio
            {
                /*try{
        indControlPrecio = DBConvenio.obtieneIndPrecioControl(VariablesVentas.vCod_Prod);
      }catch(SQLException sql)
      {
        log.error("",sql);
        FarmaUtility.showMessage(this,"Error al obtener indicador de control de precio.",txtCantidad);
      }*/
                /* 23.01.2007 ERIOS La validacion de realiza por las listas de exclusion */
                //if(indControlPrecio.equals(FarmaConstants.INDICADOR_N))
                if (true) {
                    try {
                        VariablesVentas.vVal_Prec_Vta = DBConvenio.obtieneNvoPrecioConvenio(VariablesConvenio.vCodConvenio,
                                                                    VariablesVentas.vCod_Prod,
                                                                    txtPrecioVenta.getText());

                        VariablesVentas.vIndAplicaPrecNuevoCampanaCupon = FarmaConstants.INDICADOR_N;

                        log.debug("vVal_Prec_Vta: " + VariablesVentas.vVal_Prec_Vta);
                    } catch (SQLException sql) {
                        log.error("", sql);
                        FarmaUtility.showMessage(this, "Error al obtener el nuevo precio seg�n el convenio.",
                                                 txtCantidad);
                    }
                    txtPrecioVenta.setText(VariablesVentas.vVal_Prec_Vta);
                }
            }
        } else {
            if (VariablesConvenioBTLMF.vCodConvenio != null &&
                VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
                /*try{
                        indControlPrecio = DBConvenio.obtieneIndPrecioControl(VariablesVentas.vCod_Prod);
                      }catch(SQLException sql)
                      {
                        log.error("",sql);
                        FarmaUtility.showMessage(this,"Error al obtener indicador de control de precio.",txtCantidad);
                      }*/
                /* 23.01.2007 ERIOS La validacion de realiza por las listas de exclusion */
                //if(indControlPrecio.equals(FarmaConstants.INDICADOR_N))
                if (true) {
                    /*try {*/
                    /*VariablesVentas.vVal_Prec_Vta = DBConvenio.obtieneNvoPrecioConvenio(VariablesConvenioBTLMF.vCodConvenio,
                                                                              VariablesVentas.vCod_Prod,
                                                                              txtPrecioVenta.getText());*/
                    /*VariablesVentas.vVal_Prec_Vta=UtilityVentas.Conv_Buscar_Precio();*/


                    log.debug(">>>>>>VariablesConvenioBTLMF.vNew_Prec_Conv: " + VariablesConvenioBTLMF.vNew_Prec_Conv);
                    log.debug(">>>>>>><vVal_Prec_Vta: " + VariablesVentas.vVal_Prec_Vta);

                    if (VariablesConvenioBTLMF.vNew_Prec_Conv.trim().length() > 0) {
                        //KMONCADA 2016.10.07 [CAMPA�AS CON CONVENIOS]
                        if(UtilityConvenioBTLMF.isConvenioCompetencia(VariablesConvenioBTLMF.vCodConvenio.trim()))
                            VariablesVentas.vVal_Prec_Vta = VariablesConvenioBTLMF.vNew_Prec_Conv;
                                /*DBConvenioBTLMF.obtieneNvoPrecioConvenio_btlmf(VariablesConvenioBTLMF.vCodConvenio,
	                                                                               VariablesVentas.vCod_Prod,
	                                                                               VariablesVentas.vVal_Prec_Pub);*/
                                /*UtilityVentas.Conv_Buscar_Precio();*/
                    }


                    VariablesVentas.vIndAplicaPrecNuevoCampanaCupon = FarmaConstants.INDICADOR_N;

                    log.debug("vVal_Prec_Vta: " + VariablesVentas.vVal_Prec_Vta);
                    /*} catch (SQLException sql) {
                        log.error("",sql);
                        FarmaUtility.showMessage(this,
                                                 "Error al obtener el nuevo precio seg�n el convenio.",
                                                 txtCantidad);
                    }*/

                    if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                        VariablesConvenioBTLMF.vCodConvenio != null &&
                        VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
                        txtPrecioVenta.setEnabled(true);
                        txtPrecioVenta.setVisible(false);
                        btnPrecioVta.setText(" ");
                    } else {
                        txtPrecioVenta.setText(VariablesVentas.vVal_Prec_Vta);
                    }
                }
            }
        }
        /**---FIN----*/
        log.debug("VariablesVentas.vVal_Prec_Vta 01:" + VariablesVentas.vVal_Prec_Vta);
        log.debug("VariablesVentas.vIndAplicaPrecNuevoCampanaCupon: " +
                  VariablesVentas.vIndAplicaPrecNuevoCampanaCupon);
        if (VariablesVentas.vIndAplicaPrecNuevoCampanaCupon.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            VariablesVentas.vListaProdAplicoPrecioDescuento.add(lblCodigo.getText().trim());
        }


        // >>>>>>>>>>>>>>>>>>>>>>>>

        cerrarVentana(true);
    }

    private void obtieneStockProducto_ForUpdate(ArrayList pArrayList) {
        try {
            DBVentas.obtieneStockProducto_ForUpdate(pArrayList, VariablesVentas.vCod_Prod, VariablesVentas.vVal_Frac);
            FarmaUtility.liberarTransaccion();
            //quitar bloqueo de stock fisico
            //dubilluz 13.10.2011
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            //quitar bloqueo de stock fisico
            //dubilluz 13.10.2011
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener stock del producto. \n" +
                    sql.getMessage(), txtCantidad);
        }
    }

    private void obtieneStockProducto() {
        ArrayList myArray = new ArrayList();
        obtieneStockProducto_ForUpdate(myArray);
        if (myArray.size() == 1) {
            VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
            VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
            VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
            log.info("DlgIngresoCantidad : VariablesVentas.vPorc_Dcto_1 (2) - " + VariablesVentas.vPorc_Dcto_1);
        } else {
            FarmaUtility.showMessage(this, "Error al obtener Stock del Producto", null);
            cerrarVentana(false);
        }
    }

    private void evaluaTipoPedido() {
        if (!VariablesVentas.vEsPedidoDelivery && !VariablesVentas.vEsPedidoInstitucional) {
            /*if(FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL) && FarmaVariables.vIndHabilitado.equalsIgnoreCase(FarmaConstants.INDICADOR_S))
      {
      }*/
            txtPrecioVenta.setEnabled(false);
            FarmaUtility.moveFocus(txtCantidad);
        } else if (VariablesVentas.vEsPedidoDelivery || VariablesVentas.vEsPedidoInstitucional) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL) &&
                FarmaVariables.vIndHabilitado.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                txtPrecioVenta.setEnabled(true);
                FarmaUtility.moveFocus(txtPrecioVenta);
            } else {
                txtPrecioVenta.setEnabled(false);
                FarmaUtility.moveFocus(txtCantidad);
            }
        }
    }

    private void calculoNuevoDescuento() {
        double precioLista = FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Lista);
        double precioVenta = FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta);
        double porcDcto = (1 - (precioVenta / precioLista));
        VariablesVentas.vPorc_Dcto_1 = FarmaUtility.formatNumber(porcDcto, 2);
    }

    private void muestraMaxProdCupon() {
        String vCodCamp;
        String vIndPordCamp;
        String vIndTipoCupon;
        double vCantProdMax;
        //ArrayList cupon = new ArrayList();
        Map mapaCupon = new HashMap();

        lblProdCupon.setVisible(false);

        try {
            for (int j = 0; j < VariablesVentas.vArrayList_Cupones.size(); j++) {
                mapaCupon = (Map)VariablesVentas.vArrayList_Cupones.get(j);
                vCodCamp = mapaCupon.get("COD_CAMP_CUPON").toString(); //cupon.get(1).toString();
                vIndTipoCupon = mapaCupon.get("TIP_CUPON").toString(); //cupon.get(2).toString();
                vCantProdMax =
                        FarmaUtility.getDecimalNumber(mapaCupon.get("UNID_MAX_PROD").toString()); //FarmaUtility.getDecimalNumber(cupon.get(6).toString());
                vIndPordCamp = DBVentas.verificaProdCamp(vCodCamp, VariablesVentas.vCod_Prod);
                if (vIndPordCamp.equals(FarmaConstants.INDICADOR_S)) {
                    if (vIndTipoCupon.equalsIgnoreCase("P")) {
                        lblProdCupon.setText("M�ximo " + vCantProdMax + " unidades para aplicar el descuento.");
                        lblProdCupon.setVisible(true);
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            log.error(null, e);
        }
    }

    private boolean isCampanaFidelizacion(String pCodCupon) {
        int i = pCodCupon.trim().indexOf("F");
        if (i == -1)
            return false;
        return true;
    }

    /**
     * corregir este metodo ya que en su momento DUBILLUZ
     * hizo la logicade mostrar el primero encontrado
     ***/
    public void calculoNuevoPrecio() {
        String vCodCamp;
        String vNvoPrecio;
        String vIndTipoCupon;
        double vNvoPrecioRedondeado; //JCHAVEZ 29102009
        //ArrayList cupon = new ArrayList();
        Map mapaCupon = new HashMap();
        //boolean vIndFidelizacion =  false;
        String vIndFidelizacion = "N";
        pPrecioFidelizacion = 0.0;
        lblPrecioProdCamp.setVisible(false);

        String vPrecioVenta = "";

        try {
            /*for (int j = 0; j < VariablesVentas.vArrayList_Cupones.size(); j++) {
                mapaCupon = (Map)VariablesVentas.vArrayList_Cupones.get(j);
                vCodCamp = mapaCupon.get("COD_CAMP_CUPON").toString(); //cupon.get(1).toString();
                vIndTipoCupon = mapaCupon.get("TIP_CUPON").toString(); //cupon.get(2).toString();
                vIndFidelizacion = mapaCupon.get("IND_FID").toString(); 
                if(VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF)
                    vPrecioVenta = lblPrecVtaConv.getText().trim();
                else
                    vPrecioVenta = txtPrecioVenta.getText().trim();

                if (vIndFidelizacion.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {

                    // DUBILLUZ 01.07.2014
                    vNvoPrecio = DBVentas.getNuevoPrecio(VariablesVentas.vCod_Prod, vCodCamp, vPrecioVenta,
                    //dubilluz 15.08.2015 -- 
                    VariablesFidelizacion.vDniCliente
                                                         );

                    // vNvoPrecioRedondeado= DBVentas.getPrecioRedondeado(Double.parseDouble(vNvoPrecio.trim())); //JCHAVEZ 29102009
                    if (!vNvoPrecio.equals(FarmaConstants.INDICADOR_N)) {
                        vNvoPrecioRedondeado =
                                DBVentas.getPrecioRedondeado(Double.parseDouble(vNvoPrecio.trim())); //JCHAVEZ 29102009
                        if (vIndTipoCupon.equalsIgnoreCase("P")) {
                            pPrecioFidelizacion = Double.parseDouble(vNvoPrecio.trim());
                            log.debug("JCHAVEZ pPrecioFidelizacion: sin redondeo " + pPrecioFidelizacion);
                            lblPrecioProdCamp.setText("Prec. Fidelizado. Referencial : "+ConstantesUtil.simboloSoles+" " +
                                                      vNvoPrecioRedondeado); //JCHAVEZ 29102009 se cambio vNvoPrecio por vNvoPrecioRedondeado
                            lblPrecioProdCamp.setVisible(true);
                        }
                        break;
                    }
                }


            }*/

            //05-DIC-13, TCT , Obtener precio Fijo de Producto y Comparar  con Fidelizado  -- Begin
            // Lectura de Datos de Campa�a con el Mejor Precio de Promocion (el mas bajo)
            if (!VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
                calculoPrecioReferencial();
            }
            //05-DIC-13, TCT , Obtener precio Fijo de Producto y Comparar  con Fidelizado  -- End


        } catch (Exception e) {
            log.error(null, e);
        }
    }
    /*boolean vIndFidelizacion;
  // [FA0001, A0001, P, 10, 10, 0, 1, 100, N, 0000000100000001P 99990.000],
  vIndFidelizacion = isCampanaFidelizacion(cupon.get(0));
   * */

    private String getAnalizaPrecio(String pPrecioVenta, double pNvoPrecioFid) {
        String pResultado = "";

        //jcallo se quito las comas del precio de los productos
        pPrecioVenta = pPrecioVenta.replaceAll(",", "");

        double pPrecio = Double.parseDouble(pPrecioVenta.trim());
        log.debug("pPrecio:" + pPrecio);
        log.debug("pNvoPrecioFid:" + pNvoPrecioFid);
        log.debug("VariablesVentas.vIndAplicaPrecNuevoCampanaCupon:" +
                  VariablesVentas.vIndAplicaPrecNuevoCampanaCupon);
        if (pNvoPrecioFid > 0) {
            if (pPrecio >= pNvoPrecioFid) {
                /*Se comento este metodo porque no funcionaba para el caso
                 * de productos fraccionados
                 * asi que por lo visto no existe diferencia salvo
                */
                /*
                try
                {
                  pResultado = DBVentas.getPrecioNormal(VariablesVentas.vCod_Prod);

                }catch(SQLException e)
                {
                  log.error(null,e);
                } */
                VariablesVentas.vIndAplicaPrecNuevoCampanaCupon = FarmaConstants.INDICADOR_S;
                pResultado = "" + pPrecio;
            } else {
                pResultado = "" + pPrecio;

            }
        } else
            pResultado = "" + pPrecioVenta;
        pResultado = pResultado.trim();
        log.debug("pResultado:" + pResultado);

        return pResultado;
    }


    private boolean isExisteProdCampana(String pCodProd) {
        //lblMensajeCampa�a.setVisible(true);
        String pRespta = "N";
        try {
            lblMensajeCampa�a.setText("");
            pRespta = DBVentas.existeProdEnCampa�aAcumulada(pCodProd, VariablesFidelizacion.vDniCliente);
            if (pRespta.trim().equalsIgnoreCase("E"))
                lblMensajeCampa�a.setText("    Cliente ya est� inscrito en campa�a Acumula tu Compra y Gana");

            if (pRespta.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
                lblMensajeCampa�a.setText("  Producto se encuentra en la campa�a \" Acumula tu Compra y Gana\"");
        } catch (SQLException e) {
            log.error(null, e);
            pRespta = "N";
        }

        if (pRespta.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N))
            return false;

        return true;
    }


    private void muestraInfoDetalleProd_Btl() {

        log.debug("metodo:muestraInfoDetalleProd_Btl");
        ArrayList myArray = new ArrayList();
        obtieneInfoProdEnArrayList(myArray);
        if (myArray.size() == 1) {
            VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
            VariablesVentas.vStk_Prod_Fecha_Actual = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
            if ((!VariablesVentas.vEsPedidoDelivery && !VariablesVentas.vEsPedidoInstitucional) ||
                !VariablesVentas.vIngresaCant_ResumenPed) {

                //JCORTEZ 11/04/08 no se actualiza el precio y descuento si es producto  oferta
                //if(!VariablesVentas.vIndOrigenProdVta.equals(ConstantsVentas.IND_ORIGEN_OFER)||!VariablesVentas.vEsProdOferta)

                // Segun gerencia se debe seguir la misma logica para todos los productos.
                if (VariablesVentas.vVentanaListadoProductos) {
                    log.debug("SETEANDO DESCUENTO");
                    VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(3)).trim();
                    VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(6)).trim();

                } else {
                    if (UtilityVentas.isAplicoPrecioCampanaCupon(lblCodigo.getText().trim(),
                                                                 FarmaConstants.INDICADOR_S)) {
                        if (!VariablesVentas.vVentanaOferta) {
                            log.debug("SETEANDO DESCUENTO");
                            VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(3)).trim();
                            VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(6)).trim();
                        }
                    }
                }


                log.debug("VariablesVentas.vVal_Prec_Vta_XXXXXXX: " + VariablesVentas.vVal_Prec_Vta);
                log.debug("VariablesVentas.vVentanaListadoProductos: " + VariablesVentas.vVentanaListadoProductos);

                log.info("DlgIngresoCantidad: VariablesVentas.vPorc_Dcto_1 - " + VariablesVentas.vPorc_Dcto_1);
                log.debug("VariablesVentas.vPorc_Dcto_2 : " + VariablesVentas.vPorc_Dcto_2);
            }
            VariablesVentas.vUnid_Vta = ((String)((ArrayList)myArray.get(0)).get(4)).trim();
            VariablesVentas.vVal_Bono = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
            VariablesVentas.vVal_Prec_Lista = ((String)((ArrayList)myArray.get(0)).get(7)).trim();
        } else {
            VariablesVentas.vStk_Prod = "0";
            VariablesVentas.vDesc_Acc_Terap = "";
            VariablesVentas.vStk_Prod_Fecha_Actual = "";
            VariablesVentas.vVal_Prec_Vta = "";
            VariablesVentas.vUnid_Vta = "";
            VariablesVentas.vPorc_Dcto_1 = "";
            VariablesVentas.vVal_Prec_Lista = "";
            VariablesVentas.vNom_Lab = "";
            VariablesVentas.vDesc_Prod = "";
            VariablesVentas.vCod_Prod = "";
            FarmaUtility.showMessage(this, "Error al obtener Informacion del Producto", null);
            cerrarVentana(false);
        }


        lblFechaHora.setText(VariablesVentas.vStk_Prod_Fecha_Actual);
        lblStock.setText("" + (Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic));
        //lblStock.setText(VariablesVentas.vStk_Prod);
        lblCodigo.setText(VariablesVentas.vCod_Prod);
        lblDescripcion.setText(VariablesVentas.vDesc_Prod);
        lblLaboratorio.setText(VariablesVentas.vNom_Lab);
        lblUnidad.setText(VariablesVentas.vUnid_Vta);

        String PrecioNew = VariablesConvenioBTLMF.vNew_Prec_Conv.toString();
        log.debug("Precio PrecioNew:" + PrecioNew);


        log.debug("VARIABLE EN MEMORIA CONVENIO:" + VariablesConvenio.vVal_Prec_Vta_Conv);

        log.debug("Precio Normal:" + VariablesVentas.vVal_Prec_Vta);
        log.debug("Precio Conven:" + VariablesConvenioBTLMF.vNew_Prec_Conv);

        //        if(UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0)
        //        {
        //           if (VariablesVentas.vEstadoProdConvenio.equals("P"))
        //           {
        //         	  VariablesConvenioBTLMF.vNew_Prec_Conv = VariablesVentas.vVal_Prec_Vta;
        //           }
        //           //Valida el Monto Precio convenio
        //           if(UtilityConvenioBTLMF.esMontoPrecioCero(VariablesConvenioBTLMF.vNew_Prec_Conv,this))
        //           {
        //        	   cerrarVentana(false);
        //           }
        //        }
        //rsachun
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
            if ((VariablesVentas.vEstadoProdConvenio.equals("P")) ||
                (VariablesVentas.vEstadoProdConvenio.equals("I"))) {
                log.debug("Estado:" + VariablesVentas.vEstadoProdConvenio);
            } else {
                VariablesConvenioBTLMF.vNew_Prec_Conv = VariablesVentas.vVal_Prec_Vta;
            }
            //Valida el Monto Precio convenio
            if (UtilityConvenioBTLMF.esMontoPrecioCero(VariablesConvenioBTLMF.vNew_Prec_Conv, this)) {
                cerrarVentana(false);
            }
        }

        txtPrecioVenta.setText(VariablesVentas.vVal_Prec_Vta); //JCHAVEZ 29102009 se cambio txtPrecioVenta por txtPrecioVentaOculto


        //lblDcto.setText(VariablesVentas.vPorc_Dcto_1);
        txtCantidad.setText("" + cantInic);
        //JCHAVEZ 29102009 inicio
        try {
            //double precVtaRedondeadoNum = DBVentas.getPrecioRedondeado(Double.parseDouble(VariablesVentas.vVal_Prec_Vta));  antes
            double precVtaRedondeadoNum =
                DBVentas.getPrecioRedondeado(FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta)); //ASOSA, 18.06.2010

            String precVtaRedondeadoStr =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + precVtaRedondeadoNum), 3);

            VariablesConvenio.vVal_Prec_Vta_Conv = VariablesConvenioBTLMF.vNew_Prec_Conv;
            /*UtilityConvenioBTLMF.Conv_Buscar_Precio()*/;
            /*DBConvenioBTLMF.obtieneNvoPrecioConvenio_btlmf(VariablesConvenioBTLMF.vCodConvenio,
                                                                                           VariablesVentas.vCod_Prod,
                                                                                           VariablesVentas.vVal_Prec_Pub);*/

            if (VariablesConvenio.vVal_Prec_Vta_Conv != null &&
                !VariablesConvenio.vVal_Prec_Vta_Conv.trim().equalsIgnoreCase("")) {
                log.debug("VariablesConvenio.vVal_Prec_Vta_Conv: " + VariablesConvenio.vVal_Prec_Vta_Conv);
                double precVtaConvRedondeadoNum = 0;
                String precVtaConvRedondeadoStr = "";
                //KMONCADA 21.06.2016 VERIFICA SI EL PRODUCTO SE TOMARA COMO GRATUITO
                if (!UtilityConvenioBTLMF.isVtaProdConvenioGratuito(VariablesVentas.vNum_Ped_Vta, VariablesConvenioBTLMF.vCodConvenio, VariablesVentas.vCod_Prod)){
                    precVtaConvRedondeadoNum = DBVentas.getPrecioRedondeado(FarmaUtility.getDecimalNumber(VariablesConvenio.vVal_Prec_Vta_Conv));
                }else{
                    precVtaConvRedondeadoNum = FarmaUtility.getDecimalNumber(VariablesConvenio.vVal_Prec_Vta_Conv);
                }
                precVtaConvRedondeadoStr = FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + precVtaConvRedondeadoNum), 3);
                lblPrecVtaConv.setText(precVtaConvRedondeadoStr); //JCHAVEZ 29102009 se cambio VariablesConvenio.vVal_Prec_Vta_Conv por precVtaConvRedondeadoStr

            }
            log.debug("precVtaRedondeadoStr:" + precVtaRedondeadoStr);

            this.txtPrecioVentaRedondeado.setText(precVtaRedondeadoStr);


        } catch (SQLException sql) {
            log.error("", sql);
        }
        //JCHAVEZ 29102009 fin
    }

    private void txtCantidad_focusLost(FocusEvent e) {
        FarmaUtility.moveFocus(txtCantidad);
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    private void lblF11_keyPressed(KeyEvent e) {
        if (!vbPrecProdOk) {
            FarmaUtility.showMessage(this, "No se puede Vender un Producto con Precio inferior al M�nimo Vigente...",
                                     txtCantidad);
            return;
        }
        // Ejct, 27-DIC-12, si producto tiene precio inferior al minimo then error
        aceptaCantidadIngresada();
    }
    
    public void ejecutarVKEnter(){
        
    }
    
    
    // DUBILLUZ 31.08.2016
    public void abreOpera() {
        muestraInfoDetalleProd();
        evaluaTipoPedido();
        lblDscto.setVisible(false);
        lblDcto.setVisible(false);
        muestraMaxProdCupon();
        calculoNuevoPrecio();
        lblMensajeCampa�a.setVisible(false);
        aceptaCantidadIngresada();
    }
    
    public void setCantInic(int cantInic) {
        this.cantInic = cantInic;
    }

    public int getCantInic() {
        return cantInic;
    }
    // DUBILLUZ 31.08.2016
    
    public boolean calculoDctosPedidoXCupones() {
        //KMONCADA 12.10.2016 [CONVENIOS CON CAMPA�AS]
        ArrayList<Integer> lstProdAplicaPrecioConvenio = new ArrayList<Integer>();
        // DUBILLUZ 19.08.2015
        //FarmaConnection.closeConnection();
        //El ahorro acumulado del pedido se coloca en 0
        //para reiniciar todo el calculo.


        VariablesFidelizacion.vAhorroDNI_Pedido = 0;

        log.debug("JCALLO: nuevo metodo de calculo de descuento");
        long timeIni = System.currentTimeMillis();

        List listaCodProds = new ArrayList(); //listaTemporal para tener el listado de codigo de productos
        Map mapaAux;
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
        if (listaCodProds.size() > 0 && VariablesVentas.vArrayList_Cupones.size() > 0) {
            prodsCampanias =
                    UtilityVentas.prodsCampaniasAplicables(listaCodProds, 
                                                           VariablesVentas.vArrayList_Cupones);
        }
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
        if ( VariablesFidelizacion.vDniCliente.trim().length() > 0 && (acumuladoDesctoPedido  >= VariablesFidelizacion.vMaximoAhorroDNIxPeriodo)) {
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
            flg_AcumulaAhorro = true;
            else
            flg_AcumulaAhorro = false;
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
            log.debug("JCALLO:indiceCamp:" + indiceCamp);
            //hasta aqui tenemos el indice donde se encuentra la campana cupon a aplicar
            
            
            //el calculo de los descuentoS solo se aplica para cupones tipo  PORCENTAJE
            if (((Map)VariablesVentas.vArrayList_Cupones.get(indiceCamp)).get("TIP_CUPON").toString().equals(ConstantsVentas.TIPO_PORCENTAJE)) {
            
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
            log.info("El cliente ya cumplio con el tope de unidades \n" +
                "con descuento de este producto:"+codProdAux);
            }
            
            log.debug("unidMaximaAplicable:" + unidMaximaAplicable + ",cantAplicable:" + cantAplicable +
                  ", cantPed:" + cantPed);
            
            //ASOSA 
            ///////////////////////////////////////////////////////////////////////////////////////////////                    
            //INI ASOSA - 15/09/2016 - PROMOCIONES PRECIO FIJO PARCHE
            precioVtaConDcto = UtilityVentas.obtenerPrecProdCampCupon(codCampAux, 
                                                                      codProdAux);
            if (precioVtaConDcto == 0.0) {//FIN ASOSA - 15/09/2016 - PROMOCIONES PRECIO FIJO PARCHE
                precioVtaConDcto = (precioVtaOrig * ((100.0 - valorCuponNuevo) / 100.0));
            }             
            //ASOSA
            
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
            
            if (ahorro > mayorAhorro && cantUnidPed >= unidMinUso)
            {
            mayorAhorro = ahorro;
            campMayorAhorro = codCampAux;
            precioVtaMayorAhorro = neoPrecioVtaXProd;
            totalVtaMayorAhorro = neoTotalXProd;
            dctoVtaMayorAhorro = neoDctoPorcentaje;
            cantPedMayorAhorro = cantAplicable;
            
            valorCuponMayorAhorro = valorCuponNuevo;
            flg_AcumulaMasAhorro = flg_AcumulaAhorro;
            }
            
            } //fin calculo de ahorro para cupones campana tipo PROCENTAJE
            
            
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
            log.info("Descuento Parcial " + mayorAhorro);
            log.info("Descuento acumulado " + acumuladoDesctoPedido);
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
            
            acuAhorro += mayorAhorro;
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
                //log.info("--->"+numero, txtDescProdOculto);
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
                    mapCampPrec = DBVentas.getDatosCampPrec(codProd,"",VariablesFidelizacion.vDniCliente.trim());


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

                        double porcIgv =
                            FarmaUtility.getDecimalNumber(bean.getVValIgv());

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
                        precioVtaTotalRedondeado = DBVentas.getPrecioRedondeado(precioVtaTotal);
                        precioUnitRedondeado = DBVentas.getPrecioRedondeado(precioUnit);
                    }else{
                        precioVtaTotalRedondeado = precioVtaTotal;
                        precioUnitRedondeado = precioUnit;
                    }
                    //dubilluz 24.11.2016
                    
                    precioVtaTotalRedondeado = precioVtaTotal;
                    precioUnitRedondeado = precioUnit;
                    
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
                        FarmaUtility.formatNumber(pAux5,3); /*precioVentaUnitRedondeado,3*/
                    String cprecioUnitRedondeado = FarmaUtility.formatNumber(precioUnitRedondeado, 3);

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
                    /*}else{
                        log.info("PRODUCTO NO APLICARA LA LOGICA DE TAB DE REDONDEO POR TENER CAMPA�A LO REALIZARA EL ALGORITMO DE RECALCULO --> \nPRODUCTO:" +codProd+"\nCAMPA�A :"+codPromAplica);
                    }*/
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
                    vCodCampCupon = mapAux.get("COD_CAMP_CUPON").toString().trim();
                    if (pCodigo.trim().equalsIgnoreCase(codProd)) {
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
    
    private void operaProductoEnArrayList(int pFila,String pCantidadNueva) {

        //INI ASOSA - 12/08/2014
        VariablesVentas.vCant_Ingresada = "" + Integer.parseInt(pCantidadNueva) * Integer.parseInt(VariablesVentas.vValorMultiplicacion);
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
        UtilityCalculoPrecio.reloadVenta();
        
        //JCHAVEZ 29102009 fin
        log.info("Registro modificado: ");
    }

    private void txtCantidad_keyReleased(KeyEvent e) {
        /*jepPrecio.setText("");
        if(txtCantidad.getText().trim().length()>0){
            int pPos = -1;
            pPos = getPosLista(lblCodigo.getText());
            operaProductoEnArrayList(pPos,txtCantidad.getText());
            calculoDctosPedidoXCupones();
            jepPrecio.setText(getPrecioFinal(pPos));
        }
        else{
            int pPos = -1;
            pPos = getPosLista(lblCodigo.getText());
            operaProductoEnArrayList(pPos,"1");
            calculoDctosPedidoXCupones();
            jepPrecio.setText(getPrecioFinal(pPos));
        }*/
    }
    public static void main (String[] args) {
          System.out.println("12.002".replace(".", "@").split("@").length);
       }
    private String getPrecioFinal(int pos) {
        String pPrecVta  = UtilityCalculoPrecio.getBeanPosDetalleVenta(pos).getVPrecioVta();
        String pSubTotal = UtilityCalculoPrecio.getBeanPosDetalleVenta(pos).getVSubTotal();
        String pAhorro   = UtilityCalculoPrecio.getBeanPosDetalleVenta(pos).getPahorroPedido();
        String pDcto     = UtilityCalculoPrecio.getBeanPosDetalleVenta(pos).getVPctDcto();
        pPrecVta = pPrecVta.replace(".", "@");
        if(pPrecVta.indexOf("@")!=-1){
            String pCadena_uno = (pPrecVta.split("@"))[0].trim();
            String pCadena_dos = (pPrecVta.split("@"))[1].trim();
            if(pCadena_dos.length()==3){
                pCadena_dos = pCadena_dos.substring(0, 2);
            }
            pPrecVta = pCadena_uno +"."+ pCadena_dos;
        }
        
        if(pAhorro.trim().length()==0)
            pAhorro= "0";
         
        lblPrecioProdCamp.setText("Prec. Referencial : "+ConstantesUtil.simboloSoles+" " + pPrecVta);
        lblPrecioProdCamp.setVisible(true);
            
        return "<style type=\"text/css\">\n" + 
        "body {\n" + 
        "    font-family: 'trebuchet MS', 'Lucida sans', Arial;\n" + 
        "    color: #444;\n" + 
        "    margin: 0px auto;\n" + 
        "}\n" + 
        "table {\n" + 
        "    border-spacing: 0;\n" + 
        "    font-size: 25;\n" + 
        "}\n" + 
        "table td{\n" + 
        "    width: 200px;\n" + 
        "}\n" + 
        ".bordered td, .bordered th {\n" + 
        "    padding: 0px;\n" + 
        "}.bordered th {\n" + 
        "    background-color: #ffefd6;\n" + 
        "}</style><div>\n" + 
        "<table border=0 class=\"bordered\">\n" + 
        //"<tr><td></td><td align=\"left\">Precio Venta</td><td align=\"left\">"+ConstantesUtil.simboloSoles+pPrecVta+"</td><td></td></tr>\n" + 
        "<tr><td></td><td align=\"left\">Precio Referencial </td><td align=\"left\">"+ConstantesUtil.simboloSoles+pPrecVta+"</td><td></td></tr>\n" + 
        //"<tr><td></td><td align=\"left\">Ahorro</td><td align=\"left\">"+ConstantesUtil.simboloSoles+pAhorro+"</td><td></td></tr>\n" + 
        //"<tr><td></td><td align=\"left\">Descuento</td><td align=\"left\">"+pDcto+"%</td><td></td></tr>\n" + 
        "</table>";
    }

    private int getPosLista(String pCodProd) {
        for(int i=0;i<UtilityCalculoPrecio.getSizeDetalleVenta();i++){
            BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
            if(bean.getVCodProd().trim().equalsIgnoreCase(pCodProd)){
                return i;
            }
        }
        return -1;
    }

    private void calculoPrecioReferencial() {
        lblPrecioProdCamp.setVisible(false);
        if(isMuestraCalculoReferencial()){
            int pPos = -1;
            pPos = getPosLista(lblCodigo.getText());
            operaProductoEnArrayList(pPos,"1");
            calculoDctosPedidoXCupones();
            jepPrecio.setText(getPrecioFinal(pPos));    
        }
    }
    
    private void calculoERRADO_Referencial(){
        Map mapCampPrec = new HashMap();
        try {
            mapCampPrec = DBVentas.getDatosCampPrec(VariablesVentas.vCod_Prod,"",VariablesFidelizacion.vDniCliente.trim());


        } catch (SQLException e) {

            FarmaUtility.showMessage(this, "Ocurrio un error al obtener datos de la Campa�a por Precio.\n" +
                    e.getMessage() +
                    "\n Int�ntelo Nuevamente\n si persiste el error comuniquese con operador de sistemas.",
                    null);

        }
        log.debug("######## TCT 20: mapCampPrec:" + mapCampPrec);

        // Verificar si el Precio de Campa�a x Precio es < al Precio Fidelizado => Calcular Nuevamente
        if (mapCampPrec.size() > 0) {
            double vd_Val_Prec_Prom;
            double vd_Prec_Fideliz = 99999.0;
            int vi_Val_Frac_Vta = Integer.parseInt(VariablesVentas.vVal_Frac);
            //int vi_Val_Frac_Sug = Integer.parseInt(VariablesVentas.vVal_Frac_Sug);

            String vs_Val_Prec_Prom = (String)mapCampPrec.get("VAL_PREC_PROM_ENT");
            String vs_Cod_Camp_Prec = (String)mapCampPrec.get("COD_CAMP_CUPON");
            log.debug("###### TCT 21 : vs_Val_Prec_Prom: " + vs_Val_Prec_Prom);
            /*
        if (VariablesVentas.vVal_Frac_Sug.length()>0) {
        vi_Val_Frac_Vta=Integer.parseInt(VariablesVentas.vVal_Frac_Sug);
        }
        */
            vd_Val_Prec_Prom = FarmaUtility.getDecimalNumber(vs_Val_Prec_Prom) / vi_Val_Frac_Vta;
            if (lblPrecioProdCamp.getText().length() > 0) {
                vd_Prec_Fideliz =
                        pPrecioFidelizacion; //Double.parseDouble(lblPrecioProdCamp.getText().trim());
            }

            log.debug("###### TCT 25 : vd_Val_Prec_Prom: " + vd_Val_Prec_Prom);

            if (vd_Val_Prec_Prom < vd_Prec_Fideliz) {
                lblPrecioProdCamp.setText("Prec. Fijo Campa�a : "+ConstantesUtil.simboloSoles+" " + vd_Val_Prec_Prom);
                lblPrecioProdCamp.setVisible(true);
            }
        }
    }

    private boolean isMuestraCalculoReferencial() {
        return DBVentas.isCalculoReferencial();
    }

    //Devuelve true si la cadena que llega es un numero decimal, false en caso contrario
     public boolean esDecimal(String cad)
     {
     try
     {
       Double.parseDouble(cad);
       return true;
     }
     catch(NumberFormatException nfe)
     {
       return false;
     }
     }

    public void setActuaRobot(boolean actuaRobot) {
        this.actuaRobot = actuaRobot;
    }

    public boolean isActuaRobot() {
        return actuaRobot;
    }

    public String getIndTipoVenta() {
        return indTipoVenta;
    }

    public void setIndTipoVenta(String indTipoVenta) {
        this.indTipoVenta = indTipoVenta;
    }

    private void btlListaPack_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(scrListaPack);
    }

    private void tblListaPack_keyPressed(KeyEvent e) {
        
    }

    private void scrListaPack_keyPressed(KeyEvent e) {
        
    }
}
