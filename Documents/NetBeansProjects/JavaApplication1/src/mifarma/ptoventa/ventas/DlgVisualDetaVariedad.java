package mifarma.ptoventa.ventas;


import com.gs.mifarma.componentes.JButtonLabel;
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

import mifarma.ptoventa.recepcionCiega.reference.DBRecepCiega;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgVisualDetaVariedad extends JDialog {

    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */
    private static final Logger log = LoggerFactory.getLogger(DlgVisualDetaVariedad.class);

    Frame myParentFrame;
    private FarmaTableModel tableModelListaPaquete1;
    private ArrayList myArray = new ArrayList();
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
    public JTextField txtPrecioTotal = new JTextField();
    private JTable tblpaquete1 = new JTable();
    private JTextArea txtDescPromocion = new JTextArea();
    private JScrollPane jScrollPane3 = new JScrollPane();
    JPanel pnlStock2 = new JPanel();
    XYLayout xYLayout4 = new XYLayout();
    private JButtonLabel btnDescripcion = new JButtonLabel();

    private String codProm = "";
    

    private ArrayList listaLimites = new ArrayList();
    private JLabelOrange lblDescPromocion = new JLabelOrange();
    private JLabelOrange lblMsgCompletado = new JLabelOrange();
    
    ArrayList lista = new ArrayList();
    ArrayList fila = new ArrayList();

    /* ************************************************************************ */
    /*                          CONSTRUCTORES                                   */
    /* ************************************************************************ */

    public DlgVisualDetaVariedad() {
        this(null, "", false, "");
    }

    public DlgVisualDetaVariedad(Frame parent, String title, boolean modal, String codProm) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.codProm = codProm;
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
        this.setSize(new Dimension(716, 282));
        this.setTitle("Detalle de Variedad");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }

        });
        pnlStock.setBounds(new Rectangle(5, 10, 685, 20));
        pnlStock.setFont(new Font("SansSerif", 0, 11));
        pnlStock.setBackground(new Color(255, 130, 14));
        pnlStock.setLayout(xYLayout2);
        pnlStock.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock.setForeground(Color.white);
        pnlStock.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    pnlStock_keyPressed(e);
                }
            });
        pnlStock1.setBounds(new Rectangle(5, 265, 625, 20));
        pnlStock1.setFont(new Font("SansSerif", 0, 11));
        pnlStock1.setBackground(new Color(255, 130, 14));
        pnlStock1.setLayout(xYLayout3);
        pnlStock1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock1.setForeground(Color.white);
        jScrollPane1.setBounds(new Rectangle(5, 30, 685, 145));
        jScrollPane1.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    jScrollPane1_keyPressed(e);
                }
            });
        jScrollPane2.setBounds(new Rectangle(5, 285, 625, 105));
        btnpaquete1.setText("Productos Seleccionados");
        btnpaquete1.setMnemonic('s');
        btnpaquete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnpaquete1_actionPerformed(e);
                }
        });
        btnpaquete1.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnpaquete1_keyPressed(e);
                }
            });
        btnpaquete2.setText("Lista 2");
        btnpaquete2.setMnemonic('2');
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(610, 220, 85, 20));
        lblEsc.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    lblEsc_keyPressed(e);
                }
            });
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
        tblpaquete1.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblpaquete1_keyPressed(e);
                }
            });
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
        lblDescPromocion.setText("jLabelOrange1");
        lblDescPromocion.setBounds(new Rectangle(10, 15, 640, 15));
        lblMsgCompletado.setText("jLabelOrange1");
        lblMsgCompletado.setBounds(new Rectangle(5, 185, 605, 20));
        lblMsgCompletado.setForeground(Color.red);
        lblMsgCompletado.setFont(new Font("SansSerif", 1, 14));
        lblMsgCompletado.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    lblMsgCompletado_keyPressed(e);
                }
            });
        pnlStock2.add(btnDescripcion, new XYConstraints(5, 0, 70, 20));
        jPanelWhite1.add(lblMsgCompletado, null);
        jPanelWhite1.add(txtPrecioTotal, null);
        jPanelWhite1.add(lblEsc, null);
        jPanelWhite1.add(jScrollPane1, null);
        pnlStock.add(btnpaquete1, new XYConstraints(4, -1, 155, 20));
        jPanelWhite1.add(pnlStock, null);
        //jPanelWhite1.add(jScrollPane2, null);
        pnlStock1.add(btnpaquete2, new XYConstraints(5, 0, 65, 20));
        //jPanelWhite1.add(pnlStock1, null);
        jScrollPane1.getViewport().add(jTable1, null);
        jScrollPane1.getViewport().add(tblpaquete1, null);
        this.getContentPane().add(jPanelWhite1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        log.debug("ini initialize visualizar: " + VariablesVentas.vArrayList_PedidoVenta);
        initTableListaPaquete1();
        log.debug("fin initialize visualizar: " + VariablesVentas.vArrayList_PedidoVenta);
        listarProductosSeleccionados();
    }
    
    private void listarProductosSeleccionados() {
        lblMsgCompletado.setText("Para modificar un pack variedad debe eliminarlo y volver a seleccionar");
        log.info("promociones" + VariablesVentas.vArrayList_Prod_Promociones);
        log.info("prods prom" + VariablesVentas.vArrayList_Promociones);
        String codPromComodin;
        for (int c = 0; c < VariablesVentas.vArrayList_Prod_Promociones.size(); c++) {
            codPromComodin = ((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(c)).get(18).toString();
            if (codPromComodin.equals(codProm)) {
                fila = new ArrayList();
                fila.add(((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(c)).get(0));
                fila.add(((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(c)).get(1));
                fila.add(((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(c)).get(2));
                fila.add(((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(c)).get(9));
                fila.add(((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(c)).get(7));
                fila.add(((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(c)).get(4));
                fila.add(" ");
                fila.add(" ");
                fila.add(" ");
                fila.add(" ");
                fila.add(" ");
                lista.add(fila);
            }
        }
        tableModelListaPaquete1.data = lista;
        tblpaquete1.repaint();
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTableListaPaquete1() {
        tableModelListaPaquete1 =
                new FarmaTableModel(ConstantsVentas.columnsDetallePromocionVisual, ConstantsVentas.defaultValuesDetallePromocionsVisual,
                                    COL_COD);
        FarmaUtility.initSimpleList(tblpaquete1, tableModelListaPaquete1, ConstantsVentas.columnsDetallePromocionVisual);
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void this_windowOpened(WindowEvent e) {  
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(tblpaquete1);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

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

    private void btnpaquete1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblpaquete1);
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void jScrollPane1_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void pnlStock_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void btnpaquete1_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void lblMsgCompletado_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void lblEsc_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void tblpaquete1_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
}
