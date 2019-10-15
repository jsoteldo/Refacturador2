package mifarma.ptoventa.inventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.FacadeInventario;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgMercaderiaDirectaResumenProdIngresado  extends JDialog{
    
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgMercaderiaDirectaResumenProdIngresado.class);

    Frame myParentFrame;
    FarmaTableModel tableModel;

    String strCodigoOrdCompDetalle = "1";
    ArrayList arrayListaProductos = new ArrayList();
    ArrayList arrayProducto = new ArrayList();
    ArrayList arrayclone = new ArrayList();
    ArrayList arrayProdOrdComp = new ArrayList();
    ArrayList fila = new ArrayList();
    private Pattern pattern;
    private Matcher matcher;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JScrollPane scrListaProductos = new JScrollPane();
    private JPanel pnlHeader = new JPanel();
    private JButton btnListaProducto = new JButton();
    private JButton btnBuscar = new JButton();
    private JTextFieldSanSerif txtProducto = new JTextFieldSanSerif();
    private JButton btnProducto = new JButton();
    private JTable tblListaProductos = new JTable();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JPanelTitle jPanelTitle2 = new JPanelTitle();
    private JLabelWhite lblTotal_l = new JLabelWhite();
    private JLabelWhite lblTotal = new JLabelWhite();
    private JFormattedTextField txtRedondeo = new JFormattedTextField();
    private FacadeInventario facadeInventario = new FacadeInventario();
    private static final String REDONDEO_PATTERN = "^[+-]?[0-9]+(?:\\.[0-9]+)?$";
    private JButtonLabel jButtonLabel1 = new JButtonLabel();

    private final int COL_VAL_FRAC = 8;
    
    private boolean aceptaResumen=false;
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgMercaderiaDirectaResumenProdIngresado() {
        this(null, "", false);
    }

    public DlgMercaderiaDirectaResumenProdIngresado(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() {
        this.setSize(new Dimension(574, 313));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Resumen productos - Mercaderia Directa");
        this.setDefaultCloseOperation(0);
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
        jContentPane.setSize(new Dimension(394, 405));
        scrListaProductos.setBounds(new Rectangle(10, 30, 550, 200));
        scrListaProductos.setBackground(new Color(255, 130, 14));
        pnlHeader.setBounds(new Rectangle(10, 5, 550, 25));
        pnlHeader.setBackground(new Color(255, 130, 14));
        pnlHeader.setLayout(null);
        btnListaProducto.setText("Lista de Productos Ingresados");
        btnListaProducto.setMnemonic('i');
        btnListaProducto.setBounds(new Rectangle(10, 0, 210, 25));
        btnListaProducto.setBackground(new Color(255, 130, 14));
        btnListaProducto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnListaProducto.setBorderPainted(false);
        btnListaProducto.setContentAreaFilled(false);
        btnListaProducto.setDefaultCapable(false);
        btnListaProducto.setFocusPainted(false);
        btnListaProducto.setFont(new Font("SansSerif", 1, 11));
        btnListaProducto.setForeground(Color.white);
        btnListaProducto.setHorizontalAlignment(SwingConstants.LEFT);
        btnListaProducto.setRequestFocusEnabled(false);
        btnListaProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnListaProducto_actionPerformed(e);
                }

            });
        btnListaProducto.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnListaProducto_keyPressed(e);
                }
            });
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(335, 260, 105, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(465, 260, 85, 20));
        jPanelTitle1.setBounds(new Rectangle(10, 345, 660, 20));
        jPanelTitle2.setBounds(new Rectangle(10, 230, 550, 20));
        jPanelTitle2.setSize(new Dimension(695, 20));
        lblTotal_l.setText("TOTAL "+ConstantesUtil.simboloSoles);
        lblTotal_l.setBounds(new Rectangle(410, 0, 70, 20));
        lblTotal.setText("0.00");
        lblTotal.setBounds(new Rectangle(480, 0, 55, 20));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtRedondeo.setBounds(new Rectangle(475, 0, 50, 20));
        txtRedondeo.setText("0.00");
        txtRedondeo.setHorizontalAlignment(SwingConstants.RIGHT);
        txtRedondeo.addKeyListener(new KeyAdapter() {
           /* public void keyPressed(KeyEvent e) {
                pattern = Pattern.compile(REDONDEO_PATTERN, Pattern.CANON_EQ);
                txtRedondeo_keyPressed(e);
            }*/

        });
        jButtonLabel1.setText("Redondeo:");
        jButtonLabel1.setBounds(new Rectangle(405, 0, 65, 20));
        jButtonLabel1.setMnemonic('r');
        /*jButtonLabel1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonLabel1_actionPerformed(e);
            }
        });*/
        jPanelTitle2.add(lblTotal, null);
        jPanelTitle2.add(lblTotal_l, null);
        scrListaProductos.getViewport();
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        jContentPane.add(jPanelTitle2, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblF11, null);
        scrListaProductos.getViewport().add(tblListaProductos, null);
        jContentPane.add(scrListaProductos, null);
        pnlHeader.add(btnListaProducto, null);
        jContentPane.add(pnlHeader, null);
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
        tblListaProductos.getTableHeader().setReorderingAllowed(false);
        tblListaProductos.getTableHeader().setResizingAllowed(false);
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsResumenProdIngreado, ConstantsInventario.defaultResumenProdIngreado,
                                    0);
        FarmaUtility.initSimpleList(tblListaProductos, tableModel, ConstantsInventario.columnsResumenProdIngreado);


    }
    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(btnListaProducto);
        cargaProductosSeleccionados();
        calculoTotal();
    }

    public void setProductoSeleccionados(ArrayList arrayList) {
        arrayListaProductos.addAll(arrayList) ;
    }
    
    private void cargaProductosSeleccionados() {
       if (arrayListaProductos.size() > 0) {
 
        for (int c = 0; c < arrayListaProductos.size(); c++) {
                fila = new ArrayList();
                fila.add(((ArrayList)arrayListaProductos.get(c)).get(0));
                fila.add(((ArrayList)arrayListaProductos.get(c)).get(8));
                fila.add(((ArrayList)arrayListaProductos.get(c)).get(2));
                fila.add(((ArrayList)arrayListaProductos.get(c)).get(3));
                fila.add(((ArrayList)arrayListaProductos.get(c)).get(5));
                arrayProducto.add(fila);
            
        }
        tableModel.data = arrayProducto;
        tblListaProductos.repaint();
        }
    }
    private void chkKeyPressed(KeyEvent e) {
      if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (arrayListaProductos.size() > 0) {
                if (JConfirmDialog.rptaConfirmDialog(this, "El IMPORTE TOTAL de la recepción es :\n \t"+lblTotal.getText()+" \n¿Desea continuar?")) {
                    aceptaResumen=true;
                    cerrarVentana(false);
                }else
                cerrarVentana(false);
            } else {
                cerrarVentana(false);
            }

        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                cerrarVentana(false);
        } 
    }
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void calculoTotal() {
        double total = 0.0;
        for (int i = 0; i < arrayListaProductos.size(); i++) {
            total =
                    total + (((FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayListaProductos, i, 2)))) *
                             FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayListaProductos, i, 3)));
        }
        lblTotal.setText(FarmaUtility.formatNumber(total, 2));
    }
    private void btnListaProducto_actionPerformed(ActionEvent e) {
      
    }
    
    private void btnListaProducto_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    boolean getAceptaResumen() {
        return aceptaResumen;
    }
}
