package mifarma.ptoventa.ventas;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.recepcionCiega.reference.DBRecepCiega;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgDetalleProductosVariedadXY extends JDialog {
    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */
    private static final Logger log = LoggerFactory.getLogger(DlgDetalleProductosVariedadXY.class);

    Frame myParentFrame;
    private FarmaTableModel tableModelListaPaquete2;
    //private ArrayList myArray = new ArrayList();
    private final int COL_COD = 0;
    //private final int COL_DESC=1;
    //private final int COL_DESL=2;
    //private final int COL_CODP1=3;
    //private final int COL_CODP2=4;

    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    JPanel pnlStock = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JPanel pnlStock1 = new JPanel();
    XYLayout xYLayout3 = new XYLayout();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTable jTable1 = new JTable();
    //private JTable jTable2 = new JTable();
    private JButtonLabel btnpaquete1 = new JButtonLabel();
    private JButtonLabel btnpaquete2 = new JButtonLabel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    public JTextField txtPrecioTotal = new JTextField();
    JLabel lblUnidadT5 = new JLabel();
    private JTable tblpaqueteRegalo1 = new JTable();
    private JTable tblpaquete2 = new JTable();
    private JButtonLabel btnCantidad = new JButtonLabel();
    private JTextArea txtDescPromocion = new JTextArea();
    private JScrollPane jScrollPane3 = new JScrollPane();
    JPanel pnlStock2 = new JPanel();
    XYLayout xYLayout4 = new XYLayout();
    private JButtonLabel btnDescripcion = new JButtonLabel();

    private String codProd = "";
    private String indTipoPack = "";
    private String descPromocion = "";
    
    private JButtonLabel btlLista1 = new JButtonLabel();
    private JButtonLabel btlLista2 = new JButtonLabel();
    private JTextFieldSanSerif txtProd1 = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtProd2 = new JTextFieldSanSerif();

    private JLabelFunction jLabelFunction2 = new JLabelFunction();
    private ArrayList listaLimites = new ArrayList();
    private JLabelOrange lblDescPromocion = new JLabelOrange();
    private JTextArea jTextArea1 = new JTextArea();
    private String mensajeSinStock = "";
    private JLabelOrange lblPrecioTotalRedondeado = new JLabelOrange();
    public JLabelOrange lblCantidad = new JLabelOrange();
    private JLabelOrange lblMsgCompletado = new JLabelOrange();

    /* ************************************************************************ */
    /*                          CONSTRUCTORES                                   */
    /* ************************************************************************ */

    public DlgDetalleProductosVariedadXY() {
        this(null, "", false, "");
    }

    public DlgDetalleProductosVariedadXY(Frame parent, String title, boolean modal, String indTipoPack) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.indTipoPack = indTipoPack;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(716, 339));
        this.setTitle("Detalle de Variedad - Regalo");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }

        });
        pnlStock.setBounds(new Rectangle(5, 55, 685, 20));
        pnlStock.setFont(new Font("SansSerif", 0, 11));
        pnlStock.setBackground(new Color(255, 130, 14));
        pnlStock.setLayout(xYLayout2);
        pnlStock.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock.setForeground(Color.white);
        pnlStock1.setBounds(new Rectangle(5, 265, 625, 20));
        pnlStock1.setFont(new Font("SansSerif", 0, 11));
        pnlStock1.setBackground(new Color(255, 130, 14));
        pnlStock1.setLayout(xYLayout3);
        pnlStock1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock1.setForeground(Color.white);
        jScrollPane1.setBounds(new Rectangle(5, 75, 685, 145));
        jScrollPane2.setBounds(new Rectangle(5, 285, 625, 105));
        btnpaquete1.setText("Lista 1");
        btnpaquete1.setMnemonic('1');
        btnpaquete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnpaquete1_actionPerformed(e);
                }
        });
        btnpaquete2.setText("Lista 2");
        btnpaquete2.setMnemonic('2');
        btnpaquete2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnpaquete2_actionPerformed(e);
            }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(600, 235, 85, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(485, 235, 110, 20));
        txtPrecioTotal.setHorizontalAlignment(JTextField.RIGHT);
        txtPrecioTotal.setDocument(new FarmaLengthText(6));
        txtPrecioTotal.setFont(new Font("SansSerif", 1, 11));
        txtPrecioTotal.setVisible(false);
        txtPrecioTotal.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                //txtCantidad_keyPressed(e);
            }
        });
        txtPrecioTotal.setBounds(new Rectangle(35, 215, 60, 20));
        txtPrecioTotal.setEnabled(false);
        lblUnidadT5.setText("Precio Total: "+ConstantesUtil.simboloSoles);
        lblUnidadT5.setFont(new Font("SansSerif", 1, 12));
        lblUnidadT5.setBounds(new Rectangle(0, 460, 95, 20));
        lblUnidadT5.setVisible(false);
        btnCantidad.setText("Cantidad Variedad Regalo:");
        btnCantidad.setBounds(new Rectangle(5, 235, 150, 20));
        btnCantidad.setForeground(new Color(2, 2, 2));
        btnCantidad.setFont(new Font("SansSerif", 1, 12));
        txtDescPromocion.setEnabled(false);
        txtDescPromocion.setFont(new Font("Arial", 0, 21));
        jScrollPane3.setBounds(new Rectangle(10, 30, 720, 155));
        pnlStock2.setBounds(new Rectangle(10, 5, 720, 25));
        pnlStock2.setFont(new Font("SansSerif", 0, 11));
        pnlStock2.setBackground(new Color(255, 130, 14));
        pnlStock2.setLayout(xYLayout4);
        pnlStock2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock2.setForeground(Color.white);
        btnDescripcion.setText("Descripción");
        btnDescripcion.setMnemonic('1');


        btlLista1.setText("Prod. Lista 2: ");
        btlLista1.setBounds(new Rectangle(10, 25, 75, 15));
        btlLista1.setForeground(new Color(255, 130, 14));
        btlLista1.setMnemonic('l');
        btlLista1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btlLista1_actionPerformed(e);
                }
            });
        btlLista2.setText("Prod. Lista 2:");
        btlLista2.setBounds(new Rectangle(10, 240, 75, 15));
        btlLista2.setForeground(new Color(255, 130, 14));
        btlLista2.setMnemonic('a');
        btlLista2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btlLista2_actionPerformed(e);
                }
            });
        txtProd1.setBounds(new Rectangle(90, 20, 245, 20));
        txtProd1.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtProd1_keyPressed(e);
                }

                public void keyReleased(KeyEvent e) {
                    txtProd1_keyReleased(e);
                }
            });
        txtProd1.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    txtProd1_focusLost(e);
                }
            });
        txtProd2.setBounds(new Rectangle(95, 235, 240, 20));
        txtProd2.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtProd2_keyPressed(e);
                }

                public void keyReleased(KeyEvent e) {
                    txtProd2_keyReleased(e);
                }
            });
        jLabelFunction2.setBounds(new Rectangle(345, 20, 140, 20));
        jLabelFunction2.setText("[ ENTER ] Ingresar");
        lblDescPromocion.setText("jLabelOrange1");
        lblDescPromocion.setBounds(new Rectangle(10, 15, 640, 15));
        jTextArea1.setBounds(new Rectangle(0, 310, 685, 140));
        jTextArea1.setBackground(new Color(237, 237, 237));
        jTextArea1.setFont(new Font("Arial", 1, 22));
        jTextArea1.setForeground(new Color(214, 107, 0));
        jTextArea1.setVisible(false);
        lblPrecioTotalRedondeado.setText("precio");
        lblPrecioTotalRedondeado.setBounds(new Rectangle(95, 460, 80, 20));
        lblPrecioTotalRedondeado.setForeground(SystemColor.windowText);
        lblPrecioTotalRedondeado.setFont(new Font("SansSerif", 1, 12));
        lblPrecioTotalRedondeado.setVisible(false);
        lblCantidad.setText("cantidad");
        lblCantidad.setBounds(new Rectangle(160, 235, 105, 20));
        lblCantidad.setForeground(SystemColor.windowText);
        lblCantidad.setFont(new Font("SansSerif", 1, 12));
        lblMsgCompletado.setText("jLabelOrange1");
        lblMsgCompletado.setBounds(new Rectangle(50, 275, 540, 20));
        lblMsgCompletado.setForeground(Color.red);
        lblMsgCompletado.setFont(new Font("SansSerif", 1, 16));
        pnlStock2.add(btnDescripcion, new XYConstraints(5, 0, 70, 20));
        //jPanelWhite1.add(lblDescPromocion, null);
        //jPanelWhite1.add(txtProd2, null);
        //jPanelWhite1.add(pnlStock2, null); ASOSA - 03/04/2017 - PACKVARIEDAD
        //jScrollPane3.getViewport().add(txtDescPromocion, null); ASOSA - 03/04/2017 - PACKVARIEDAD
        //jPanelWhite1.add(jScrollPane3, null); ASOSA - 03/04/2017 - PACKVARIEDAD
        //jPanelWhite1.add(btlLista2, null);
        jPanelWhite1.add(lblMsgCompletado, null);
        jPanelWhite1.add(lblPrecioTotalRedondeado, null);
        jPanelWhite1.add(jTextArea1, null);
        jPanelWhite1.add(jLabelFunction2, null);
        jPanelWhite1.add(txtProd1, null);
        jPanelWhite1.add(btlLista1, null);
        jPanelWhite1.add(btnCantidad, null);
        jPanelWhite1.add(lblUnidadT5, null);
        jPanelWhite1.add(txtPrecioTotal, null);
        jPanelWhite1.add(lblF11, null);
        jPanelWhite1.add(lblEsc, null);
        jPanelWhite1.add(jScrollPane1, null);
        jScrollPane2.getViewport().add(tblpaquete2, null);
        //jPanelWhite1.add(jScrollPane2, null);
        pnlStock.add(btnpaquete1, new XYConstraints(5, 0, 60, 20));
        jPanelWhite1.add(pnlStock, null);
        jPanelWhite1.add(lblCantidad, null);
        pnlStock1.add(btnpaquete2, new XYConstraints(5, 0, 65, 20));
        //jPanelWhite1.add(pnlStock1, null);
        jScrollPane1.getViewport().add(jTable1, null);
        jScrollPane1.getViewport().add(tblpaqueteRegalo1, null);
        this.getContentPane().add(jPanelWhite1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        VariablesVentas.vArrayList_Prod_XY_Regalos.clear();
        log.debug("ini initialize detalle: " + VariablesVentas.vArrayList_PedidoVenta);
        FarmaVariables.vAceptar = false;
        initTableListaPaquete2();
        obtieneCantidad();
        log.debug("fin initialize detalle: " + VariablesVentas.vArrayList_PedidoVenta);
        listaPaquetes();        
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTableListaPaquete2() {
        tableModelListaPaquete2 =
                new FarmaTableModel(ConstantsVentas.columnsDetalleVariedadRegaloXY, ConstantsVentas.defaultValuesDetalleVariedadRegaloXY,
                                    COL_COD);
        FarmaUtility.initSimpleList(tblpaqueteRegalo1, tableModelListaPaquete2, ConstantsVentas.columnsDetalleVariedadRegaloXY);
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void this_windowOpened(WindowEvent e) {  
        lblDescPromocion.setText(descPromocion);        
        mostrarDetallePromocion();
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtProd1);
        cargarConf(true);      
        borrarProdSinStockLista1();        
        //setearProductoOriginal();
        isRegaloCompleto();
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private boolean isValidoStockProductosSeleccionados() {
        boolean flag = true;
        String tipoProducto = "";
        int contador = 0;
        mensajeSinStock = "Falta stock de lo siguiente:\n";
        ArrayList list;
        String codProd;
        String indStock;
        String descProd; 
        for (int r = 0; r < tableModelListaPaquete2.data.size(); r++) {
            list = (ArrayList)tableModelListaPaquete2.data.get(r);
            codProd = list.get(0).toString();
            
            //INI AOVIEDO 04/09/2017
            try {
                tipoProducto = DBVentas.obtieneIndTipoConsumoProd(codProd);
            } catch (SQLException e) {
                e.printStackTrace();
                tipoProducto = "";
            }
            
            if (tipoProducto.trim().equals(ConstantsVentas.TIPO_PROD_FINAL)) {
                if(!list.get(5).toString().trim().isEmpty()){
                    int cantidad = Integer.parseInt(list.get(5).toString().trim());
                    if (!UtilityVentas.existsStockComp(this, codProd, cantidad, cantidad)) {
                        indStock = "N";
                    } else {
                        indStock = "S";
                    }   
                }else{
                    indStock = "S";
                }
            }else{
                indStock = UtilityVentas.determinarIndTieneStockProd(codProd);
            }
            //FIN AOVIEDO 04/09/2017
            
            descProd = list.get(1).toString();
            if (indStock.trim().equals("N")) {
                contador = 1;
                mensajeSinStock = mensajeSinStock + codProd + "-" + descProd + "\n";
                borrarProdSinStockLista1();
            }
        }
        if (contador == 1) {
            flag = false;
            listaPaquetes(); 
            listarCantidadesPromocion();
            borrarProdSinStockLista1();            
            setearProductoOriginal();
            isRegaloCompleto();
            tblpaqueteRegalo1.repaint();            
            FarmaUtility.showMessage(this, mensajeSinStock, txtProd1);
        }                
        return flag;
    }

    /**
     * Cuando inicia la pantalla estoy haciendo que elimine los registros sin stock porque no los quieren ver.
     * Y ademas se esta concatenando el mensaje de los productos sin stock, que solo mostrare en el F11 al revalidar los stocks luego.
     */
    private void borrarProdSinStockLista1() {
        ArrayList list;
        String codProd;
        String stock;
        String descProd; 
        /*for (int r = 0; r < tableModelListaPaquete2.data.size(); r++) {
            list = (ArrayList)tableModelListaPaquete2.data.get(r);
            codProd = list.get(0).toString();
            stock = list.get(6).toString();
            descProd = list.get(1).toString();
            if (stock.trim().equals("0.00") || stock.trim().equals("") || stock.trim().equals("0") || stock.trim().equals(" ")) {
                removeItemArray(tableModelListaPaquete2.data, codProd, 0);
                borrarProdSinStockLista1();
            }
        }  */      
    }

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
            
            //AOVIEDO 15/03/2018
            if(indTipoPack.equals(ConstantsVentas.IND_PACK_MULTIPACK_VARIEDAD)){
                VariablesVentas.vArrayList_Prod_XY.clear();   
            }
            //AOVIEDO 15/03/2018
            
            VariablesVentas.vArrayList_Prod_XY_Regalos.clear();
        } else  if (UtilityPtoVenta.verificaVK_F11(e)) {
            txtCantidad_keyPressed(e);
        } 
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
        /**
     * Limpia los valores
     * @author : dubilluz
     * @since  : 25.06.2007
     */
        VariablesVentas.accionModificar = false;
        limpiaValores();
    }

    private void btnpaquete1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProd1);
    }

    private void btnpaquete2_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProd2);
    }

    

    public void txtCantidad_keyPressed(KeyEvent e) {
        //if (e.getKeyCode() == KeyEvent.VK_F11) {
        if (UtilityPtoVenta.verificaVK_F11(e)) { 
        
            if (validaCompletoPaquete_DU()) {
                if(JConfirmDialog.rptaConfirmDialogDefaultNo(this, 
                                                         "¿Está seguro del regalo elegido?")){
                    //Consultara si Existe Producto Virtual
                    if (!VariablesVentas.venta_producto_virtual) {
                        FarmaVariables.vAceptar = true;
                        if (!VariablesVentas.accionModificar) {                    
                            if (verificaProducto()) {
                                if(isValidoStockProductosSeleccionados()) {
                                    aceptarComprometidos();
                                }                        
                            } else {
                                FarmaVariables.vAceptar = false;
                                FarmaUtility.showMessage(this, "La Promocion tiene productos ya seleccionados", txtProd1);
                            }
                        } else {
                            aceptarComprometidos();
                        }
                    } else {
                        cerrarVentana(true);
                        FarmaUtility.showMessage(this,
                                                 "La venta de un Producto Virtual debe ser única por pedido. Verifique!!!",
                                                 null);
                    }
                }
            }
            else {
                    FarmaUtility.showMessage(this, "Para continuar debe completar el pack variedad", txtProd1);
                }         
        
        } else {
            chkKeyPressed(e);
        }
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    /**
     * Se lista el detalle del paquete 2 de la promocion - regalo
     */
    private void listaPaquetes() {
        try {
            VariablesVentas.vArrayList_Prod_XY_Regalos.clear();
            DBVentas.obtieneListadoPaquetes02Regalo(VariablesVentas.vArrayList_Prod_XY_Regalos, VariablesVentas.vCodProm.trim());
            DBVentas.listaPaquete1_02_Regalo(tableModelListaPaquete2, VariablesVentas.vCodProm);
            int cantmax = Integer.parseInt(FarmaUtility.getValueFieldArrayList(tableModelListaPaquete2.data, 0, 10));
            VariablesVentas.vcantMaxVta = cantmax;
            log.debug("*****");
            
            calcularNumerosPack();
            
            //ordenarListas();
        } catch (SQLException sql) {
            log.error("", sql.getMessage());
            FarmaUtility.showMessage(this, "Ocurrio un error al listar los paquetes para regalo.\n" +
                    sql.getMessage(), tblpaqueteRegalo1);
        }
    }
    
    private void listarCantidadesPromocion() {
        UtilityVentas.listarCantidadesPromocion02(listaLimites, VariablesVentas.vCodProm);
    }
       
    private void calcularNumerosPack() {
        try {
            double total1 = 0.0;
            double total2 = 0.0;
            
            if (indTipoPack.equals(ConstantsVentas.IND_PACK_VARIEDAD) ||
                indTipoPack.equals(ConstantsVentas.IND_PACK_MULTIPACK_VARIEDAD)) {
            
                ArrayList listComodin;
                String cantComodin;
                String cantComodinAplicar;
                String precioComodin;
                String porcComodin;
                String cantFraccionada;
                String cantDivFraccion;
                
                //INI AOVIEDO 26/04/17
                int cantMaxPaq1 = 0;
                String porcPaquete2 = "";
                
                if(listaLimites.size() > 0 && listaLimites != null && !listaLimites.isEmpty()){
                    cantMaxPaq1 = (int)Integer.parseInt(((ArrayList)listaLimites.get(0)).get(0).toString());
                    //porcPaquete2 = ((ArrayList)listaLimites.get(0)).get(3).toString();
                }
                
                int cantPacks = 0;
                try{
                    cantPacks = (int)Integer.parseInt(lblCantidad.getText().trim());
                } catch (Exception e){
                    cantPacks = 0;
                }
                int cont = 0;
                //FIN AOVIEDO 26/04/17
                
                /*
                int cantMaxPaq1 = (int)Integer.parseInt(((ArrayList)listaLimites.get(0)).get(0).toString());
                String porcPaquete2 = ((ArrayList)listaLimites.get(0)).get(3).toString();
                int cantPacks = (int)Integer.parseInt(lblCantidad.getText().trim());
                int cont = 0;
                */
                
                for (int c = 0; c < tableModelListaPaquete2.data.size(); c++) {
                    listComodin = (ArrayList)tableModelListaPaquete2.data.get(c);
                    cantComodin = listComodin.get(5).toString();
                    cantFraccionada = listComodin.get(12).toString();
                    if (!cantComodin.equals(" ")) {                        
                        porcComodin = listComodin.get(8).toString();
                        precioComodin = listComodin.get(4).toString();
                        porcPaquete2 = listComodin.get(11).toString().trim();
                        
                        cantDivFraccion = String.valueOf((Integer.parseInt(cantComodin)/Integer.parseInt(cantFraccionada)));
                        
                        /*for (int i = 0; i < (int)Integer.parseInt(cantComodin); i++) {
                            cont = cont + 1;
                            if (cont > (cantMaxPaq1 * cantPacks)) {
                                porcComodin = porcPaquete2;
                            }
                            //total1 = total1 + ((double)Double.parseDouble(precioComodin) * (100.0 - (double)Double.parseDouble(porcComodin))/100) * (double)Double.parseDouble(cantComodin);
                            total1 = total1 + ((double)Double.parseDouble(precioComodin) * (100.0 - (double)Double.parseDouble(porcComodin))/100);
                        }*/
                        
                        for (int i = 0; i < Integer.parseInt(cantDivFraccion); i++) {
                            cont = cont + 1;
                            if (cont > (cantMaxPaq1 * cantPacks)) {
                                porcComodin = porcPaquete2.trim().isEmpty() ? "0" : porcPaquete2.trim(); //AOVIEDO 16/08/2017
                            }
                            
                            cantComodinAplicar = String.valueOf(Integer.parseInt(cantComodin)/Integer.parseInt(cantDivFraccion));
                            for (int j = 0; j < Integer.parseInt(cantComodinAplicar); j++) {
                                //total1 = total1 + ((double)Double.parseDouble(precioComodin) * (100.0 - (double)Double.parseDouble(porcComodin))/100) * (double)Double.parseDouble(cantComodin);
                                total1 = total1 + (Double.parseDouble(precioComodin) * (100 - Double.parseDouble(porcComodin))/100);
                            }
                        }
                    }
                }                
                
            } else {
                total1 = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldJTable(tblpaqueteRegalo1, 0, 9));
                total2 = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldJTable(tblpaquete2, 0, 9));
            }
            
            //JCHAVEZ 29102009 inicio
            double total1Aux = total1;
            double total2Aux = total2;
            //JCHAVEZ 29102009 fin
            total2 += total1;

            //JCHAVEZ 29102009 inicio
            double totalRedondeadoNum = DBVentas.getPrecioRedondeado(total2);
            String totalRedondeadoStr =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + totalRedondeadoNum), 3);
            lblPrecioTotalRedondeado.setText(totalRedondeadoStr);
            log.debug("totalRedondeado:" + totalRedondeadoStr);
            //JCHAVEZ 29102009 fin

            // int totalDou = Integer.parseInt(totalStr.trim());

            //JCHAVEZ 29102009 inicio
            //KMONCADA 24/08.2015 PARA LOS PACKS NO APLICARA LA VARIABLE REDONDEO
            VariablesVentas.vIndAplicaRedondeo = "N";
            if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
            }

            if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                double total1AuxRedondeado = DBVentas.getPrecioRedondeado(total1Aux);
                double total2AuxRedondeado = DBVentas.getPrecioRedondeado(total2Aux);
                total2AuxRedondeado += total1AuxRedondeado;
                VariablesVentas.vVentaTotal = FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + total2AuxRedondeado), 3);
                txtPrecioTotal.setText(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + total2), 3));
            } else if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("N")) {
                /* 25.01.2008 ERIOS Se ajusta la precision. */
                VariablesVentas.vVentaTotal = FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + total2), 3);
                txtPrecioTotal.setText(VariablesVentas.vVentaTotal);
            }
            //JCHAVEZ 29102009 fin
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
    
    private void ordenarListas() {
        if (tblpaqueteRegalo1.getRowCount() > 0 && tblpaquete2.getRowCount() > 0) {
            FarmaUtility.ordenar(tblpaqueteRegalo1, tableModelListaPaquete2, COL_COD, FarmaConstants.ORDEN_ASCENDENTE);
        }
    }
    
    //Se deben separa porque lamentablemente ya no esta como antes y ahora se enrreda más
    //el pack, EL PACK OJO, nunca ha admitido productos iguales, ahora SI.
    private void separarProductosArray() {
        ArrayList list = new ArrayList();
        ArrayList listTemp;
        int stock = 0;
        String codProd = "";
        ArrayList listCloned = (ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.clone();
        for (int c = 0; c < listCloned.size(); c++) {
            listTemp = new ArrayList();
            stock = (int)Integer.parseInt(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, c, 9).trim());
            codProd = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, c, 0);
            if (stock > 1) {
                listTemp.add((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(c));
                ((ArrayList)listTemp.get(0)).set(9, "1");
                removeItemArray(VariablesVentas.vArrayList_Prod_XY_Regalos, codProd, 0);
                for (int i = 0; i < stock; i++) {
                    VariablesVentas.vArrayList_Prod_XY_Regalos.add(((ArrayList)listTemp.get(0)).clone());
                }
            }
        }
    }
    
    private void ponerPorcetajesDsctoMyArray() {        
        int cantMaxPaq2 = (int)Integer.parseInt(((ArrayList)listaLimites.get(0)).get(1).toString());
        //SE LE APLICA EL PORCENTAJE DEL PAQUETE 2
        //double porcPaquete2 = Double.parseDouble(((ArrayList)listaLimites.get(0)).get(3).toString());
        int cantPacks = (int)Integer.parseInt(lblCantidad.getText().trim());
        
        ArrayList list = new ArrayList();
        double precio = 0.0;
        double precioDescontado = 0.0;
        double porcentajeDescontar = 0.0;
        double porcPaquete2 = 0.0;
        log.debug("ANTES PONER PORC: " + VariablesVentas.vArrayList_Prod_XY_Regalos);
        
        //for (int c = 0; c < (cantMaxPaq2 * cantPacks); c++) {
        for (int c = 0; c < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); c++) {
            //int i = c + cantPacks;
            int i = c;
            
            list = (ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i);
            precio = (double)Double.parseDouble(list.get(5).toString());
            porcPaquete2 = Double.parseDouble(list.get(24).toString()); //AOVIEDO 18/08/2017
            //INI AOVIEDO 26/04/17
            porcentajeDescontar = (100 - porcPaquete2)/100;
            //precioDescontado = precio * ((100 - porcPaquete2)/100);
            precioDescontado = Double.parseDouble(FarmaUtility.formatNumber(precio * porcentajeDescontar, 2));
            //FIN AOVIEDO 26/04/17
            
            //list.set(5,  " " + precioDescontado);//precio descontado CAMBIAR CAMBIAR CAMBIAR AOVIEDO
            list.set(18, " " + porcPaquete2);//porcentaje
            list.set(20, " " + (precioDescontado - precio));//precio descontado - precio
            /*
            ((ArrayList)myArray.get(c)).set(5,  " " + precioDescontado);//precio descontado
            ((ArrayList)myArray.get(c)).set(18, " " + porcPaquete2);//porcentaje
            ((ArrayList)myArray.get(c)).set(20, " " + (precioDescontado - precio));//precio descontado - precio
    */
            
        }
        log.debug("DESPUES PONER PORC: " + VariablesVentas.vArrayList_Prod_XY_Regalos);
    }
    
    /**
     * Se acepta la cantidad ingresada y se llena los arreglos con todos los datos de cada producto
     */
    private void aceptarComprometidos() {
        if (indTipoPack.equals(ConstantsVentas.IND_PACK_VARIEDAD) ||
            indTipoPack.equals(ConstantsVentas.IND_PACK_MULTIPACK_VARIEDAD)) {
            limpiarProductosVariedad();
        }   
            //separarProductosArray();
            ponerPorcetajesDsctoMyArray();
            
        if (isRegaloCompleto()) {
            if (!validaCantidadIngreso()) {
                FarmaUtility.showMessage(this, "Ingrese una cantidad correcta.", txtProd1);
                FarmaVariables.vAceptar = false;
                return;
            }

            if (Integer.parseInt(lblCantidad.getText().trim()) > VariablesVentas.vcantMaxVta) {
                FarmaUtility.showMessage(this, "Se excede la cantidad maxima de ventas.", txtProd1);
                FarmaVariables.vAceptar = false;
                return;
            }
            /*if (!validaStockActual()) {
                FarmaUtility.showMessage(this, "No hay disponibilidad de Stock", txtProd1);
                FarmaVariables.vAceptar = false;
                return;
            }*/


            log.debug("Acepta ");
            if (tblpaqueteRegalo1.getRowCount() > 1) {
                log.debug("", tblpaqueteRegalo1.getValueAt(tblpaqueteRegalo1.getSelectedRow(), 9));
            }

            log.info("TODOS LOS PROM TEMP:" + VariablesVentas.vArrayList_Promociones_temporal);
            log.info("TODOS LOS PRODUCTOS PROM TEMP:" + VariablesVentas.vArrayList_Prod_Promociones_temporal);
            log.info("TODOS LOS PROM :" + VariablesVentas.vArrayList_Promociones);
            log.info("TODOS LOS PRODUCTOS PROM :" + VariablesVentas.vArrayList_Prod_Promociones);
            log.debug("Acepta Comprometidos");
            log.debug("VariablesVentas.vArrayList_Prod_XY_Regalos xxx " + VariablesVentas.vArrayList_Prod_XY_Regalos);
            //INI AOVIEDO 27/04/17
            //VariablesVentas.vCant_Ingresada = lblCantidad.getText();
            //FIN AOVIEDO 27/04/17
            
            //AceptarProductosComprometidos();
            cerrarVentana(true);
        
        } else {
            FarmaUtility.showMessage(this, "Para continuar debe completar el pack variedad", txtProd1);
        }   
            
    }
    
    private void limpiarProductosVariedad() {
        ArrayList list = new ArrayList();
        String codProd;
        for (int c = 0; c < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); c++) {
            list = (ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(c);
            codProd = list.get(0).toString();
            
            ArrayList listPq = new ArrayList();
            String codProdPq;
            String cantPq;
            
            for (int i = 0; i < tableModelListaPaquete2.data.size(); i++) {
                
                listPq = (ArrayList)tableModelListaPaquete2.data.get(i);
                codProdPq = listPq.get(0).toString();
                cantPq = listPq.get(5).toString();
                
                if (codProd.equals(codProdPq)) {
                    if (cantPq.equals(" ")) {
                        removeItemArray(VariablesVentas.vArrayList_Prod_XY_Regalos, codProd, 0);
                        limpiarProductosVariedad();
                    } else {
                        ((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(c)).set(9, cantPq);
                    }
                    break;
                }
            }
        }
        
        
        
        log.info("ARRAY PROCESADO: " + VariablesVentas.vArrayList_Prod_XY_Regalos);
        
    }

    /**
     * Se valida el valor de la cantidad ingresada
     */
    private boolean validaCantidadIngreso() {
        boolean valor = false;
        String cantIngreso = lblCantidad.getText().trim();
        if (FarmaUtility.isInteger(cantIngreso) && Integer.parseInt(cantIngreso) > 0)
            valor = true;
        return valor;
    }

    /**
     * Se valida el stockActual
     */
    private boolean validaStockActual() {
        boolean valor = false;
        //String tipoProducto = "";
        //obtieneStockProducto();
        int cantIngreso = Integer.parseInt(lblCantidad.getText().trim());
        ArrayList a2 = new ArrayList();
        ArrayList b = new ArrayList();
        for (int i = 0; i < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); i++) {
            a2 = (ArrayList)(VariablesVentas.vArrayList_Prod_XY_Regalos.get(i));
            b.add((ArrayList)(a2.clone()));
        }
        ArrayList auxNuevo = VariablesVentas.vArrayList_Prod_XY_Regalos;
        //(ArrayList)agruparProdPaquete(b).clone();
        log.debug("Se agrupa para Validar Stock");
        log.debug("Prod Actuales para Validar : " + auxNuevo);

        for (int i = 0; i < auxNuevo.size(); i++) {
            //  String codProd = ((String)((ArrayList)auxNuevo.get(i)).get(0)).trim();
            String unidad = ((String)((ArrayList)auxNuevo.get(i)).get(9)).trim();
            log.debug(unidad);
            String disponible = ((String)((ArrayList)auxNuevo.get(i)).get(4)).trim();
            log.debug(disponible);
            
            // MODIFICAR PARA VALIDAR STOCK
            // DUBILLUZ 2017.05.02
            if ((Integer.parseInt(unidad)  /** cantIngreso*/ ) <= Integer.parseInt(disponible))
                valor = true;
            else
                return false;
            
            /*
            //INI AOVIEDO 04/09/2017
            try {
                tipoProducto = DBVentas.obtieneIndTipoConsumoProd(codProd);
            } catch (SQLException e) {
                e.printStackTrace();
                tipoProducto = "";
            }
            
            if (tipoProducto.trim().equals(ConstantsVentas.TIPO_PROD_FINAL)) {
                int cantidad = Integer.parseInt(unidad.trim());
                if (!UtilityVentas.existsStockComp(this, codProd, cantidad, cantidad)) {
                    FarmaUtility.showMessage(this, "No hay stock suficiente para vender el producto", cantidad);
                    //lblStock.setText("" + (Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic));
                    valor = false;
                } else {
                    valor = true;
                }
            }else{
                // MODIFICAR PARA VALIDAR STOCK
                // DUBILLUZ 2017.05.02
                if ((Integer.parseInt(unidad) cantIngreso ) <= Integer.parseInt(disponible))
                    valor = true;
                else
                    return false;
            }
            //FIN AOVIEDO 04/09/2017
            */
        }

        return valor;
    }

    private void AceptarProductosComprometidos() {
        ArrayList arrayProdProm = new ArrayList();
        String indFidelizado = "N";
        if (VariablesVentas.accionModificar) {
            VariablesVentas.vCod_Prom = VariablesVentas.vCodProm;
            //log.debug("**COD  *Prom  "+ VariablesVentas.vCodProm);
            borraStock();
            //actualizaStockComprometido(VariablesVentas.vArrayList_Prod_Promociones,ConstantsVentas.INDICADOR_D);
            removeItemArray(VariablesVentas.vArrayList_Promociones, VariablesVentas.vCod_Prom, 0);
            //log.debug("******Prom :"+VariablesVentas.vArrayList_Promociones.size());
            //log.debug("****Prom"+VariablesVentas.vArrayList_Promociones);
            removeItemArray(VariablesVentas.vArrayList_Prod_Promociones, VariablesVentas.vCod_Prom, 18);
            //log.debug("******PrdoProm :"+VariablesVentas.vArrayList_Prod_Promociones.size());
            //log.debug("****PrdoProm"+VariablesVentas.vArrayList_Prod_Promociones);
        }
        //agrego al arreglo principal vArrayList_Promociones
        //ArrayList myArray = new ArrayList();
        log.debug("**********************");
        log.debug("Llenado de Arreglo");
        try {
            // DBVentas.obtieneListadoPaquetes(myArray, VariablesVentas.vCodProm.trim());
            log.debug("VariablesVentas.vArrayList_Prod_XY_Regalos.size() " + VariablesVentas.vArrayList_Prod_XY_Regalos.size());
            log.debug("VariablesVentas.vArrayList_Prod_XY_Regalos  () " + VariablesVentas.vArrayList_Prod_XY_Regalos);
            double igvTotal = 0;
            for (int i = 0; i < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); i++) {
                ArrayList myArray2 = new ArrayList();
                //
                VariablesVentas.vCod_Prod = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(0)).trim();
                VariablesVentas.vDesc_Prod = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(1)).trim();
                VariablesVentas.vUnid_Vta = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(2)).trim();
                VariablesVentas.vNom_Lab = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(3)).trim();
                VariablesVentas.vStk_Prod = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(4)).trim();
                VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(5)).trim();

                //JCHAVEZ 29102009 inicio
                try {
                    //KMONCADA 24/08.2015 PARA LOS PACKS NO APLICARA LA VARIABLE REDONDEO
                    VariablesVentas.vIndAplicaRedondeo = "N";
                    if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("")) {
                        VariablesVentas.vIndAplicaRedondeo = DBVentas.getIndicadorAplicaRedondedo();
                    }

                    if (VariablesVentas.vIndAplicaRedondeo.equalsIgnoreCase("S")) {
                        double total1AuxRedondeado =
                            DBVentas.getPrecioRedondeado(Double.parseDouble(VariablesVentas.vVal_Prec_Vta));
                        VariablesVentas.vVal_Prec_Vta =
                                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + total1AuxRedondeado), 3);

                    }

                } catch (Exception ex) {
                    log.error("", ex);
                }
                //JCHAVEZ 29102009 fin

                VariablesVentas.vTotalPrecVtaProd =
                        FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta) * /*FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada) **/
                        Integer.parseInt((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(9));

                VariablesVentas.vVal_Bono = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(16)).trim();
                VariablesVentas.vVal_Frac = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(7)).trim();
                //dubilluz 2017.05.02
                VariablesVentas.vCant_Ingresada = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_XY_Regalos, i, 9);
                
                //INI AOVIEDO 27/04/17
                //VariablesVentas.vCant_Ingresada = lblCantidad.getText();
                //FIN AOVIEDO 27/04/17
                log.debug(" Promociones VariablesVentas.vCant_Ingresada : " + VariablesVentas.vCant_Ingresada);
                //VariablesVentas.vCant_Ingresada =(Integer.parseInt(VariablesVentas.vCant_Ingresada) * Integer.parseInt((String)((ArrayList)myArray.get(i)).get(9))) +
                        //"";

                log.debug(" Promociones cantidad unidad: " + (String)(((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(9)));
                log.debug(" Promociones VariablesVentas.vCant_Ingresada : " + VariablesVentas.vCant_Ingresada);


                VariablesVentas.vVal_Prec_Lista = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(10)).trim();
                VariablesVentas.vPorc_Igv_Prod = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(11)).trim();
                String valIgv =
                    FarmaUtility.formatNumber((FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta) -
                                               (FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta) /
                                                (1 + (FarmaUtility.getDecimalNumber(VariablesVentas.vPorc_Igv_Prod) /
                                                      100)))) *
                                              FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada));
                VariablesVentas.vVal_Igv_Prod = valIgv;
                log.debug("Igv de cada uno: " + valIgv);
                igvTotal += FarmaUtility.getDecimalNumber(valIgv.trim());

                VariablesVentas.vNumeroARecargar = "";
                VariablesVentas.vIndProdVirtual = FarmaConstants.INDICADOR_N;
                VariablesVentas.vTipoProductoVirtual = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(14)).trim();
                VariablesVentas.vIndProdControlStock =
                        true; //? FarmaConstants.INDICADOR_S : FarmaConstants.INDICADOR_N);//INDICADOR PROD CONTROLA STOCK
                VariablesVentas.vVal_Prec_Lista_Tmp = ""; //PRECIO DE LISTA ORIGINAL SI ES QUE SE MODIFICO
                //VariablesVentas.vVal_Prec_Pub=((String) ((ArrayList) myArray.get(i)).get(6)).trim();
                /**
         * VAriables descuentos y Precio_Publico
         * @author : dubilluz
         * @since  : 03.07.2007
         */
                VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(17)).trim();
                VariablesVentas.vPorc_Dcto_2 = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(18)).trim();
                VariablesVentas.vVal_Prec_Pub = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(19)).trim();


                VariablesVentas.vAhorroPack = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(20)).trim(); //JCHAVEZ 20102009
                VariablesVentas.vInd_Origen_Prod_Prom =
                        ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(21)).trim(); //JCHAVEZ 20102009
                double totaltemp =
                    Double.parseDouble(VariablesVentas.vCant_Ingresada.trim()) * Double.parseDouble(VariablesVentas.vVal_Prec_Vta.trim());
                // Begin 16-AGO-13, TCT, Add Precio Fijo
                VariablesVentas.vPrecFijo = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(22)).trim();
                // End 16-AGO-13, TCT, Add Precio Fijo


                /*ERIOS 10.04.2008 Datos que se agregan al arreglo de prod_promociones,
                           que se guarda en memoria para grabar el detalle.*/
                myArray2.add(VariablesVentas.vCod_Prod);
                myArray2.add(VariablesVentas.vDesc_Prod);
                myArray2.add(VariablesVentas.vUnid_Vta);
                myArray2.add(VariablesVentas.vVal_Prec_Lista);
                myArray2.add(VariablesVentas.vCant_Ingresada); //ingresa en Double
                myArray2.add("" + FarmaUtility.getDecimalNumber(VariablesVentas.vPorc_Dcto_1)); //proc_Dcto_1;
                myArray2.add(VariablesVentas.vVal_Prec_Vta);
                myArray2.add(FarmaUtility.formatNumber(totaltemp, 2) + "");
                myArray2.add(VariablesVentas.vVal_Bono); //val_Bono
                myArray2.add(VariablesVentas.vNom_Lab); //9
                myArray2.add(VariablesVentas.vVal_Frac);
                myArray2.add(VariablesVentas.vPorc_Igv_Prod);
                myArray2.add(VariablesVentas.vVal_Igv_Prod); //generado
                myArray2.add(VariablesVentas.vNumeroARecargar); //vacio
                myArray2.add(VariablesVentas.vIndProdVirtual);
                //myArray.add(VariablesVentas.vTipoProductoVirtual);//TIPO DE PRODUCTO VIRTUAL
                //myArray2.add("S");//Indicador de Stock
                myArray2.add(VariablesVentas.vVal_Prec_Lista_Tmp); //vacio
                //myArray2.add(""+FarmaUtility.getDecimalNumber( VariablesVentas.vVal_Prec_Pub));//prec_publico;
                //myArray.add(VariablesVentas.vIndOrigenProdVta); //19
                //myArray2.add("S");//20 indica q esta en una promocion
                myArray2.add("S"); //Indicador de Stock
                myArray2.add(" ");
                myArray2.add(VariablesVentas.vCod_Prom.trim()); //codigo de la promocion donde esta el producto
                myArray2.add("S"); //indica q esta en una promocion
                myArray2.add("" + FarmaUtility.getDecimalNumber(VariablesVentas.vPorc_Dcto_2)); //proc_Dcto_2;
                myArray2.add("" +
                             FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Pub)); //prec_publico; // numero 23
                //myArray2.add("N"); //ind. tratamiento
                //myArray2.add(" ");//23 cantxDia tratamiento
                //myArray2.add(" ");//24 cantxDias tratamiento

                myArray2.add("" +
                             FarmaUtility.getDecimalNumber(VariablesVentas.vAhorroPack)); //JCHAVEZ 20102009 columna 22
                myArray2.add(VariablesVentas.vInd_Origen_Prod_Prom); //JCHAVEZ 20102009 columna 23

                //agregado dubilluz 07.07.2010
                String codProd = ((String)(myArray2.get(0))).trim();
                String cantidadAux = ((String)(myArray2.get(4))).trim();
                String indControlStk = ((String)(myArray2.get(16))).trim();
                VariablesVentas.vVal_Frac = ((String)(myArray2.get(10))).trim();
                VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
                if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                    !UtilityVentas.operaStkCompProdResp(codProd, //ASOSA, 07.07.2010
                        Integer.parseInt(cantidadAux), ConstantsVentas.INDICADOR_A,
                        ConstantsPtoVenta.TIP_OPERACION_RESPALDO_SUMAR, Integer.parseInt(cantidadAux), false, this,
                        lblCantidad, "")) {
                    int numero = 1 / 0;
                    break;
                }

                myArray2.add(VariablesVentas.secRespStk); // numero 24
                // Begin 16-AGO-13, TCT, Add Precio Fijo
                myArray2.add(VariablesVentas.vPrecFijo);
                // End 16-AGO-13, TCT, Add Precio Fijo

                //agregado dubilluz 07.07.2010

                Boolean valor = new Boolean(true);
                arrayProdProm.add(myArray2);
                // KMONCADA 10.08.2015 INDICADOR DE PROMOCION PARA CLIENTES FIDELIZADOS O NO
                indFidelizado = ((String)((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).get(23)).trim();
            }
            ///agrega areglor de promociones que mete en resumen pedido
            //agregar dubilluz 07.07.2010
            Boolean valor2 = new Boolean(true);
            //llenado de arreglo de promociones
            ArrayList myArrayP = new ArrayList();
            double total = FarmaUtility.getDecimalNumber(VariablesVentas.vVentaTotal);
            double cantidad = FarmaUtility.getDecimalNumber(lblCantidad.getText().trim());
            String pagototal = FarmaUtility.formatNumber(cantidad * total, 2);

            /*ERIOS 10.04.2008 Datos que se agregan al arreglo de promociones,
                           que solo se muestran en el resumen de pedido.*/
            myArrayP.add(VariablesVentas.vCod_Prom);
            myArrayP.add(VariablesVentas.vDesc_Prom);
            myArrayP.add(VariablesVentas.vUnid_Prom); //vacio
            myArrayP.add(FarmaUtility.formatNumber(total/cantidad, 3)); //AOVIEDO /cantidad
            myArrayP.add(lblCantidad.getText().trim());
            myArrayP.add(VariablesVentas.vDes_Prom); //vacio
            myArrayP.add(FarmaUtility.formatNumber(total/cantidad, //AOVIEDO /cantidad
                                                   3)); //el precio venta seria el mismo que precio , ya que no ahi descuento
            myArrayP.add("" + total); //AOVIEDO cambio pagototal por total
            myArrayP.add("0.00");
            myArrayP.add(" "); //9
            myArrayP.add(" ");
            myArrayP.add(" ");
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
            myArrayP.add(indFidelizado);//25 INDICA SI PACK APLICA PARA CLIENTE FIDELIZADO O NO
            // VariablesVentas.vVentaTotal
            //Boolean valor2 = new Boolean(true);
            FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Promociones_temporal, myArrayP, valor2, 0);

            if (VariablesVentas.vArrayList_Promociones.size() > 0) {
                log.debug("PROMOCIONES ACTUAL---->" + VariablesVentas.vCod_Prom + "  N°PROMOCIONES " +
                          VariablesVentas.vArrayList_Promociones.size());
            }

            // fin
            ArrayList aux = new ArrayList();

            log.debug("<<TCT 100>> Inicio de Carga Promociones a Temporal..");
            for (int a = 0; a < arrayProdProm.size(); a++) {
                aux = (ArrayList)(arrayProdProm.get(a));
                /*
                log.debug("VariablesVentas.vArrayList_Prod_Promociones" +
                                   VariablesVentas.vArrayList_Prod_Promociones);
                 */
                FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Prod_Promociones_temporal,
                                            (ArrayList)(aux.clone()), valor2, 0);
            }
            log.debug("<<TCT 100>> Promociones Cargadas a Temporal=>VariablesVentas.vArrayList_Prod_Promociones_temporal:" +
                      VariablesVentas.vArrayList_Prod_Promociones_temporal);
            VariablesVentas.vArrayList_Prod_XY_Regalos = new ArrayList();
            arrayProdProm = new ArrayList();
            if (VariablesVentas.accionModificar)
                aceptaPromocion();

            FarmaUtility.aceptarTransaccion();
            log.info("lista prod paquete:" + VariablesVentas.vArrayList_Prod_Promociones_temporal);
        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            FarmaUtility.showMessage(this, "Error al comprometer los productos del PACK", txtProd1);
            log.error("", e);
        }
    }

    /**
     * Coloca cantidad sise modificara la cantidad de Promocion
     * @author : dubilluz
     * @since  : 25.06.2007
     */
    private void obtieneCantidad() {
        if (VariablesVentas.accionModificar) {
            lblCantidad.setText(VariablesVentas.vCantidad);
        }
    }

    private void limpiaValores() {
        //VariablesVentas.vCodProm = "";
        //VariablesVentas.vCodProdFiltro="";
        VariablesVentas.vCantidad = "";
        VariablesVentas.accionModificar = false;
        /**
    * SETEO DE VARIABLES
    * @author : dubilluz
    * @since  : 03.07.2007s
    */
        VariablesVentas.vPorc_Dcto_1 = "";
        VariablesVentas.vPorc_Dcto_2 = "";
        VariablesVentas.vVal_Prec_Pub = "";
        //VariablesVentas.vCodProm = "";
        //VariablesVentas.vDesc_Prom = "";

    }

    /**
     * Elimina elementos del Array
     * @author : dubilluz
     * @since  : 25.06.2007
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
     * Actualiza el Stock
     * @author dubilluz
     * @since  27.06.2007
     */
    private void borraStock() {
        ArrayList prod_Prom = new ArrayList();
        prod_Prom = detalle_Prom(VariablesVentas.vArrayList_Prod_Promociones, VariablesVentas.vCodProm);
        //prod_Prom = agrupar(prod_Prom); ya no porque ahora se comprometera por producto independientemente y ya no agrupado como antes
        log.debug("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD secRespaldo: " + prod_Prom);
        ArrayList aux = new ArrayList();
        String codProm = "";
        String codProd = "";
        String cantidad = ""; //((String)(tblProductos.getValueAt(filaActual,4))).trim();
        String indControlStk = ""; // ((String)(tblProductos.getValueAt(filaActual,16))).trim();
        String secRespaldo = ""; //ASOSA, 07.07.2010
        for (int j = 0; j < prod_Prom.size(); j++) {
            aux = (ArrayList)(prod_Prom.get(j));
            codProd = ((String)(aux.get(0))).trim();
            VariablesVentas.vVal_Frac = ((String)(aux.get(10))).trim();
            cantidad = ((String)(aux.get(4))).trim();
            indControlStk = ((String)(aux.get(16))).trim();
            secRespaldo = ((String)(aux.get(24))).trim(); //ASOSA, 07.07.2010
            VariablesVentas.secRespStk = ""; //ASOSA, 26.08.2010
            if (indControlStk.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
                /*!UtilityVentas.actualizaStkComprometidoProd(codProd, //Antes, ASOSA, 07.07.2010
                                                       Integer.parseInt(cantidad),
                                                       ConstantsVentas.INDICADOR_D,
                                                       ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR,
                                                       Integer.parseInt(cantidad),
                                                       true,
                                                       this,
                                                       txtCantidad))*/
                !UtilityVentas.operaStkCompProdResp(codProd, //ASOSA, 01.07.2010
                    0, ConstantsVentas.INDICADOR_D, ConstantsPtoVenta.TIP_OPERACION_RESPALDO_BORRAR, 0, true, this,
                    lblCantidad, secRespaldo))
                return;
        }
        FarmaUtility.aceptarTransaccion();
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
     * Verifica el producto si esta comprado
     * @author : dubilluz
     * @since  : 26.06.2007
     */
    private boolean verificaProducto() {
        ArrayList aux = new ArrayList();
        ArrayList aux2 = new ArrayList();
        for (int i = 0; i < VariablesVentas.vArrayList_PedidoVenta.size(); i++) {            
            log.debug("COD PROD: " + codProd);
            aux = (ArrayList)(VariablesVentas.vArrayList_PedidoVenta.get(i));
            if (!aux.get(0).toString().equals(codProd)) {
                log.debug("Entro");
                for (int j = 0; j < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); j++) {
                    aux2 = (ArrayList)(VariablesVentas.vArrayList_Prod_XY_Regalos.get(j));
                    if ((((String)(aux.get(0))).trim()).equalsIgnoreCase((((String)(aux2.get(0))).trim()))) {
                        //return false;
                        log.info("NO SE VALIDA QUE UN PRODUCTO DE LA PROMO ESTE PREVIAMENTE EN EL RESUMEN");
                    }
                    log.debug("En check" + (String)(aux.get(0)));
                    log.debug("En detalle" + (String)(aux2.get(0)));
                }
            }            
        }

        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
            aux = (ArrayList)(VariablesVentas.vArrayList_Prod_Promociones.get(i));

            for (int j = 0; j < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); j++) {
                aux2 = (ArrayList)(VariablesVentas.vArrayList_Prod_XY_Regalos.get(j));
            
                if(aux2.size() == 26){
                    if ((((String)(aux.get(0))).trim()).equalsIgnoreCase((((String)(aux2.get(0))).trim()))) {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones_temporal.size(); i++) {
            aux = (ArrayList)(VariablesVentas.vArrayList_Prod_Promociones_temporal.get(i));

            for (int j = 0; j < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); j++) {
                aux2 = (ArrayList)(VariablesVentas.vArrayList_Prod_XY_Regalos.get(j));
                if ((((String)(aux.get(0))).trim()).equalsIgnoreCase((((String)(aux2.get(0))).trim()))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Agrupa productos que esten en ambos paquetes
     * retorna el nuevoa arreglo
     * @author : dubilluz
     * @author : 27.06.2007
     */
    private ArrayList agrupar(ArrayList array) {
        ArrayList nuevo = new ArrayList();
        ArrayList aux1 = new ArrayList();
        ArrayList aux2 = new ArrayList();
        int cantidad1 = 0;
        int cantidad2 = 0;
        int suma = 0;
        log.debug("****************************JCORTEZ **********AGRUPACION" + array);

        for (int i = 0; i < array.size(); i++) {
            aux1 = (ArrayList)(array.get(i));
            //if(aux1.size()>0){//(((String)(aux1.get(19))).trim()).equalsIgnoreCase("Revisado")){//JCHAVEZ DECIA <23 Y CAMBIE a >0
            // KMONCADA 10.08.2015 SE CAMBIA DE 24 A 25 POR EL CAMPO AGREGADO DE INDICADOR FIDELIZADO
            if (aux1.size() < 26) { // JCORTEZ DICE POR NUEVO CAMPOS DE JCHAVEZ (23+2=25)
                for (int j = i + 1; j < array.size(); j++) {
                    aux2 = (ArrayList)(array.get(j));
                    //if(aux2.size()>0){               //JCHAVEZ DECIA <23 Y CAMBIE a >0
                    // KMONCADA 10.08.2015 SE CAMBIA DE 24 A 25 POR EL CAMPO AGREGADO DE INDICADOR FIDELIZADO
                    if (aux2.size() < 26) { //JCORTEZ DICE POR NUEVO CAMPOS DE JCHAVEZ (23+2=25)
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
     * Agrupa productos del Paquete
     * retorna el nuevoa arreglo
     * @author : dubilluz
     * @author : 27.06.2007
     */
    private ArrayList agruparProdPaquete(ArrayList array) {
        log.debug("para unir +" + array);
        ArrayList nuevo = new ArrayList();
        ArrayList aux1 = new ArrayList();
        ArrayList aux2 = new ArrayList();
        int cantidad1 = 0;
        int cantidad2 = 0;
        int suma = 0;
        for (int i = 0; i < array.size(); i++) {
            aux1 = (ArrayList)(array.get(i));
            log.debug("Tamaño " + i + " " + aux1.size());
            if (aux1.size() < 28) { 
                //(((String)(aux1.get(19))).trim()).equalsIgnoreCase("Revisado")){//JCHAVEZ 20102009 DECIA <21
                for (int j = i + 1; j < array.size(); j++) {
                    aux2 = (ArrayList)(array.get(j));
                    if (aux2.size() < 28) { //JCHAVEZ 20102009 DECIA <21
                        if ((((String)(aux1.get(0))).trim()).equalsIgnoreCase((((String)(aux2.get(0))).trim()))) {
                            cantidad1 = Integer.parseInt(((String)(aux1.get(9))).trim());
                            cantidad2 = Integer.parseInt(((String)(aux2.get(9))).trim());
                            suma = cantidad1 + cantidad2;
                            aux1.set(9, suma + "");
                            ((ArrayList)(array.get(j))).add("Revisado");
                        }
                    }
                }
                nuevo.add(aux1);
            }
        }
        return nuevo;
    }


    private void cargarConf(boolean valor) {
        txtDescPromocion.setLineWrap(true);
        txtDescPromocion.setWrapStyleWord(true);
        txtDescPromocion.setText(VariablesVentas.vDescProm);
        pnlStock.setVisible(valor);
        pnlStock1.setVisible(valor);
        jScrollPane1.setVisible(valor);
        jScrollPane2.setVisible(valor);
        if (indTipoPack.equals(ConstantsVentas.IND_PACK_VARIEDAD) ||
            indTipoPack.equals(ConstantsVentas.IND_PACK_MULTIPACK_VARIEDAD)) {
            listarCantidadesPromocion();
        }
    }
    
    private void setearProductoOriginal() {
        ArrayList list;
        String codProd;
        for (int c = 0; c < tableModelListaPaquete2.data.size(); c++) {
            list = (ArrayList)tableModelListaPaquete2.data.get(c);
            codProd = list.get(0).toString();
            if (codProd.equals(VariablesVentas.vCod_Prod)) {
                ((ArrayList)tableModelListaPaquete2.data.get(c)).set(5, "1");
                break;
            }
        }
        tblpaqueteRegalo1.repaint();
        calcularNumerosPack();        
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getIndTipoPack() {
        return indTipoPack;
    }

    public void setIndTipoPack(String indTipoPack) {
        this.indTipoPack = indTipoPack;
    }

    private void btlLista1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProd1);
    }

    private void btlLista2_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProd2);
    }

    private void buscarProducto(String codigo) {
        String cod;
        boolean flag = false;
        for (int c = 0; c < tableModelListaPaquete2.data.size(); c++) {
            cod = ((ArrayList)tableModelListaPaquete2.data.get(c)).get(0).toString();
            if (cod.equals(codigo)) {
                FarmaGridUtils.showCell(tblpaqueteRegalo1, c, 5);                
                flag = true;
                break;
            }            
        }
        if (flag) {
            llamarSetearCantidad("1", 
                                 tblpaqueteRegalo1.getSelectedRow());
        } else {
            FarmaUtility.showMessage(this, "Codigo inexistente", txtProd1);
        }
        txtProd1.setText("");
    }

    private void txtProd1_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblpaqueteRegalo1, txtProd1, 1);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {            
            String codigo = txtProd1.getText().trim();
            if (FarmaUtility.isInteger(codigo) ||FarmaUtility.isLong(codigo)) {
                if (codigo.length() != 6) {
                    try {
                        codigo = DBRecepCiega.obtieneCodigoProductoBarra(codigo);
                    } catch (SQLException ex) {
                        log.error(ex.getMessage());
                    }                    
                }
                buscarProducto(codigo);
            } else {
                if (isRowSelected()) {
                    llamarSetearCantidad("1", 
                                         tblpaqueteRegalo1.getSelectedRow());
                }            
            }            
        } else {
            chkKeyPressed(e);
        }        
    }
    
    private boolean isRowSelected() {
        boolean flag = true;
        int ind = tblpaqueteRegalo1.getSelectedRow();
        if (ind < 0) {
            flag = false;
            FarmaUtility.showMessage(this, "Seleccione un registro", txtProd1);
        }
        return flag;
    }
    
    private void llamarSetearCantidad(String opcion,
                                      int indice) {
        if (isThereStock(opcion)) {
            String codProductoY = FarmaUtility.getValueFieldArrayList(tableModelListaPaquete2.data, indice, 0);
            String descProd = FarmaUtility.getValueFieldArrayList(tableModelListaPaquete2.data, indice, 1) + " " +
                                FarmaUtility.getValueFieldArrayList(tableModelListaPaquete2.data, indice, 2);
            String cantFraccionada = FarmaUtility.getValueFieldArrayList(tableModelListaPaquete2.data, indice, 12);
            DlgCantDetaVariedad02 dlgCantDetaVariedad02 = new DlgCantDetaVariedad02(myParentFrame, "", true);
            dlgCantDetaVariedad02.setTipoPantallaXoY("REGALO");
            dlgCantDetaVariedad02.setOpcion(opcion);
            dlgCantDetaVariedad02.setCantPermitidaPaquete(getCantPermitidaPaquete(opcion));
            dlgCantDetaVariedad02.setDescProd(descProd);
            dlgCantDetaVariedad02.setCantFraccionada(Integer.parseInt(cantFraccionada));
            dlgCantDetaVariedad02.setVisible(true);
            if (FarmaVariables.vAceptar) {
                if (opcion.equals("1")) {
                    ((ArrayList)tableModelListaPaquete2.data.get(indice)).set(5, VariablesVentas.cantidadDetalleVariedad);
                    FarmaGridUtils.showCell(tblpaqueteRegalo1, indice, 5);
                } 
            } else {
                VariablesVentas.cantidadDetalleVariedad = " ";
            }
            
            ArrayList list;
            String codProd;
            for(int i = 0; i < VariablesVentas.vArrayList_Prod_XY_Regalos.size(); i++) {
                list = (ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i);
                codProd = list.get(0).toString();
                if (codProd.equals(codProductoY)) {
                    ((ArrayList)VariablesVentas.vArrayList_Prod_XY_Regalos.get(i)).add(25, VariablesVentas.cantidadDetalleVariedad);
                    break;
                }
            }
            
            calcularNumerosPack();
            isRegaloCompleto();
        }        
    }
    
    private boolean isThereStock(String opcion) {
        boolean flag = true;
        String stock = "0";
        if (opcion.equals("1")) {
            stock = FarmaUtility.getValueFieldArrayList(tableModelListaPaquete2.data, tblpaqueteRegalo1.getSelectedRow(), 6).toString();
        }
        if (stock.trim().equals("0.00")) {
            //flag = false;
            //FarmaUtility.showMessage(this, "No hay stock", txtProd1);
            flag = true;
        }
        
        return flag;
    }
    
    private int getCantIngresadaPaquete(String opcion) {
        int valor = 0;
        if (opcion.equals("1")) {
            for (int c = 0; c < tableModelListaPaquete2.data.size(); c++) {
                ArrayList fila = (ArrayList)tableModelListaPaquete2.data.get(c);
                if (!fila.get(0).toString().equals(FarmaUtility.getValueFieldArrayList(tableModelListaPaquete2.data, tblpaqueteRegalo1.getSelectedRow(), 0))) {
                    valor = valor + (int)Integer.parseInt((fila.get(5).toString().trim().equals("") ? "0" : fila.get(5).toString()));
                }                
            }
        
        }
        return valor;
    } 
    
    private int getCantIngresadaPaqueteTotal(String opcion) {
        int valor = 0;
        if (opcion.equals("1")) {
            for (int c = 0; c < tableModelListaPaquete2.data.size(); c++) {
                ArrayList fila = (ArrayList)tableModelListaPaquete2.data.get(c);
                valor = valor + (int)Integer.parseInt((fila.get(5).toString().trim().equals("") ? "0" : fila.get(5).toString()));
            }
        
        }
        return valor;
    }
    
    private boolean isRegaloCompleto () {
        boolean flag = false;
        int cantSolicitada = getCantMaxPaquete("1");// + getCantMaxPaquete("2");
        int cantAtendida = getCantIngresadaPaqueteTotal("1");// + getCantIngresadaPaqueteTotal("2");
        if (cantAtendida == cantSolicitada) {
            flag = true;
            lblMsgCompletado.setText("EL REGALO YA ESTÁ COMPLETO");
        } else {
            lblMsgCompletado.setText("");
        }
        return flag;
    }
    
    private int getCantMaxPaquete(String opcion) {
        int valor = (int)Integer.parseInt(lblCantidad.getText());
        int cantMaxPaquete1 = (int)Integer.parseInt(((ArrayList)listaLimites.get(0)).get(0).toString());
        int cantMaxPaquete2 = (int)Integer.parseInt(((ArrayList)listaLimites.get(0)).get(1).toString());
        if (opcion.equals("1")) {
            //valor = valor * (cantMaxPaquete1 + cantMaxPaquete2);
            valor = valor * cantMaxPaquete2;
        //} else {
        //    valor = valor * cantMaxPaquete2;
        }
        return valor;
    }
    
    
    private int getCantPermitidaPaquete(String opcion) {
        int valor = 0;
        valor = getCantMaxPaquete(opcion) - getCantIngresadaPaquete(opcion);
        return valor;
    }
    
    private void txtProd1_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblpaqueteRegalo1, txtProd1, 1);
    }

    private void txtProd2_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblpaquete2, txtProd2, 1);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            llamarSetearCantidad("2", 
                                 tblpaquete2.getSelectedRow());
        } else {
            chkKeyPressed(e);
        } 
    }

    private void txtProd2_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblpaquete2, txtProd2, 1);
    }

    public String getDescPromocion() {
        return descPromocion;
    }

    public void setDescPromocion(String descPromocion) {
        this.descPromocion = descPromocion;
    }

    private void mostrarDetallePromocion() {
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setText(descPromocion);
    }

    private boolean validaCompletoPaquete_DU() {
        if (lblMsgCompletado.getText().trim().length()>0) {
            return true;
        } else {
            return false;
        }
    }

    private void txtProd1_focusLost(FocusEvent e) {
        FarmaUtility.moveFocus(txtProd1);
    }
}
