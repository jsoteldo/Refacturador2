package mifarma.ptoventa.ventas;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import farmapuntos.bean.BeanTarjeta;

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

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityProgramaAcumula;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2008 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DlgTratamiento.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * JCORTEZ      29.05.2008   Creación<br>
 * <br>
 * @author Jorge Cortez Alvarez<br>
 * @version 1.0<br>
 *
 */

public class DlgTratamiento extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgTratamiento.class);

    private int cantInic = 0;
    private Frame myParentFrame;
    private boolean vIndTieneSug;

    private String lblPreUnit;
    private String lblDescProdSug;
    private String lblUnidPresProdSug;
    private String lblCantSug;
    private String lblTotalSug;
    private String lblTotalSugRedondeado; //JCHAVEZ 29102009
    private String lblPreUnitSug;
    private String lblStockFracSug;

    //private static final Object[] defaultValuesListaFiltro = {" ", " "};
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JPanel pnlRelacionFiltros = new JPanel();
    private JLabel lbl1 = new JLabel();
    private JPanel pnlIngresarProductos = new JPanel();
    private JButton btn1 = new JButton();
    private JButton btnProducto = new JButton();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JButton btn2 = new JButton();
    private JTextFieldSanSerif txtCantxDia = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtCant = new JTextFieldSanSerif();
    private JButton btnProducto1 = new JButton();
    private JPanel pnlIngresarProductos1 = new JPanel();
    private JPanelHeader pnllHeader1 = new JPanelHeader();
    JLabel lblCantTrata = new JLabel();
    private JButton btnProducto5 = new JButton();
    JLabel lblCantVenta = new JLabel();
    private JLabelFunction lblEnter1 = new JLabelFunction();
    JLabel lblTotalVenta = new JLabel();
    JLabel lblUnidades = new JLabel();
    JLabel lblStock = new JLabel();
    JLabel lblFechaHora = new JLabel();
    JLabel lblStockTexto = new JLabel();
    JLabel lblUnidad = new JLabel();
    JLabel lblUnidadT = new JLabel();
    JLabel lblLaboratorio = new JLabel();
    JLabel lblLaboratorioT = new JLabel();
    JLabel lblDescripcion = new JLabel();
    JLabel lblDescripcionT = new JLabel();
    JLabel lblCodigo = new JLabel();
    JLabel lblCodigoT = new JLabel();
    JLabel lblStockEntSug = new JLabel();
    private JButton lblS1 = new JButton();
    JLabel lblMensaje = new JLabel(); 
    private JLabel lblMsjAcumula = new JLabel();//RPASCACIO 19-06-2017
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();
    private JLabel lblTotalVentaRedondeado = new JLabel();
    
    //INICIO RPASCACIO 19-06-2017
    private int vCtdNormal = 0;
    private int vCtdBono = 0;
    private boolean vAccionAcumula = false;
    private String vCodCampAcumula = "";
    private String vCodEQCampAcumula = "";
    private ArrayList vListaAcumula =  new ArrayList();
    int COL_DESC_LARGA = 9;
    //FIN RPASCACIO 19-06-2017
    
    private String pBKPrecioRef = "";

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgTratamiento() {
        this(null, "", false);
    }

    public DlgTratamiento(Frame parent, String title, boolean modal) {
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
    // Método "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(563, 474));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Tratamiento Sugerido");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(623, 398));
        jContentPane.setForeground(Color.white);
        lblEnter.setText("[F11] Tratamiento");
        lblEnter.setBounds(new Rectangle(5, 410, 115, 20));
        pnlRelacionFiltros.setBounds(new Rectangle(5, 255, 545, 25));
        pnlRelacionFiltros.setBackground(new Color(255, 130, 14));
        pnlRelacionFiltros.setLayout(null);
        lbl1.setText("Producto Sugerido :");
        lbl1.setBounds(new Rectangle(5, 0, 115, 25));
        lbl1.setFont(new Font("SansSerif", 1, 11));
        lbl1.setForeground(Color.white);
        pnlIngresarProductos.setBounds(new Rectangle(5, 160, 545, 90));
        pnlIngresarProductos.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 14), 1));
        pnlIngresarProductos.setBackground(Color.white);
        pnlIngresarProductos.setLayout(null);
        pnlIngresarProductos.setForeground(Color.orange);
        btn1.setText("Cant. por Dia :");
        btn1.setBounds(new Rectangle(10, 10, 75, 20));
        btn1.setMnemonic('c');
        btn1.setRequestFocusEnabled(false);
        btn1.setFocusPainted(false);
        btn1.setDefaultCapable(false);
        btn1.setBorderPainted(false);
        btn1.setBackground(new Color(43, 141, 39));
        btn1.setHorizontalAlignment(SwingConstants.LEFT);
        btn1.setFont(new Font("SansSerif", 1, 11));
        btn1.setForeground(new Color(255, 140, 14));
        btn1.setHorizontalTextPosition(SwingConstants.LEADING);
        btn1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btn1.setContentAreaFilled(false);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn1_actionPerformed(e);
            }
        });
        btnProducto.setText("Total "+ConstantesUtil.simboloSoles+" :");
        btnProducto.setBounds(new Rectangle(210, 60, 80, 20));
        btnProducto.setMnemonic('f');
        btnProducto.setFont(new Font("SansSerif", 1, 15));
        btnProducto.setDefaultCapable(false);
        btnProducto.setRequestFocusEnabled(false);
        btnProducto.setBackground(new Color(50, 162, 65));
        btnProducto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProducto.setFocusPainted(false);
        btnProducto.setHorizontalAlignment(SwingConstants.LEFT);
        btnProducto.setContentAreaFilled(false);
        btnProducto.setBorderPainted(false);
        btnProducto.setForeground(new Color(255, 140, 14));

        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(465, 410, 85, 20));
        btn2.setText("Cant. Dias :");
        btn2.setBounds(new Rectangle(10, 35, 75, 20));
        btn2.setRequestFocusEnabled(false);
        btn2.setFocusPainted(false);
        btn2.setDefaultCapable(false);
        btn2.setBorderPainted(false);
        btn2.setBackground(new Color(43, 141, 39));
        btn2.setHorizontalAlignment(SwingConstants.LEFT);
        btn2.setFont(new Font("SansSerif", 1, 11));
        btn2.setForeground(new Color(255, 140, 14));
        btn2.setHorizontalTextPosition(SwingConstants.LEADING);
        btn2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btn2.setContentAreaFilled(false);
        txtCantxDia.setBounds(new Rectangle(120, 10, 55, 20));
        txtCantxDia.setFont(new Font("SansSerif", 1, 11));
        txtCantxDia.setForeground(new Color(32, 105, 29));
        txtCantxDia.setLengthText(5);
        txtCantxDia.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtCantxDia_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtCantxDia_keyTyped(e);
            }
        });
        txtCant.setBounds(new Rectangle(120, 35, 55, 20));
        txtCant.setFont(new Font("SansSerif", 1, 11));
        txtCant.setForeground(new Color(32, 105, 29));
        txtCant.setLengthText(5);
        txtCant.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtCant_keyPressed(e);
                }

            public void keyTyped(KeyEvent e) {
                txtCant_keyTyped(e);
            }
        });
        btnProducto1.setText("Tratamiento :");
        btnProducto1.setBounds(new Rectangle(195, 35, 90, 20));
        btnProducto1.setMnemonic('f');
        btnProducto1.setFont(new Font("SansSerif", 1, 11));
        btnProducto1.setDefaultCapable(false);
        btnProducto1.setRequestFocusEnabled(false);
        btnProducto1.setBackground(new Color(50, 162, 65));
        btnProducto1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProducto1.setFocusPainted(false);
        btnProducto1.setHorizontalAlignment(SwingConstants.LEFT);
        btnProducto1.setContentAreaFilled(false);
        btnProducto1.setBorderPainted(false);
        btnProducto1.setForeground(new Color(255, 140, 14));
        pnlIngresarProductos1.setBounds(new Rectangle(5, 280, 545, 80));
        pnlIngresarProductos1.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 14), 1));
        pnlIngresarProductos1.setBackground(Color.white);
        pnlIngresarProductos1.setLayout(null);
        pnlIngresarProductos1.setForeground(Color.orange);
        pnllHeader1.setBounds(new Rectangle(5, 5, 545, 120));
        pnllHeader1.setBackground(Color.white);
        lblCantTrata.setFont(new Font("SansSerif", 1, 11));
        lblCantTrata.setBounds(new Rectangle(295, 35, 55, 20));
        btnProducto5.setText("Cant. Total Venta :");
        btnProducto5.setBounds(new Rectangle(10, 60, 105, 20));
        btnProducto5.setMnemonic('f');
        btnProducto5.setFont(new Font("SansSerif", 1, 11));
        btnProducto5.setDefaultCapable(false);
        btnProducto5.setRequestFocusEnabled(false);
        btnProducto5.setBackground(new Color(50, 162, 65));
        btnProducto5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProducto5.setFocusPainted(false);
        btnProducto5.setHorizontalAlignment(SwingConstants.LEFT);
        btnProducto5.setContentAreaFilled(false);
        btnProducto5.setBorderPainted(false);
        btnProducto5.setForeground(new Color(255, 140, 14));
        lblCantVenta.setFont(new Font("SansSerif", 1, 11));
        lblCantVenta.setBounds(new Rectangle(120, 60, 55, 20));
        lblEnter1.setText("[F12] Sugerido");
        lblEnter1.setBounds(new Rectangle(130, 410, 115, 20));
        lblTotalVenta.setFont(new Font("SansSerif", 1, 15));
        lblTotalVenta.setBounds(new Rectangle(420, 60, 100, 20));
        lblTotalVenta.setAlignmentY((float)0.8);
        lblTotalVenta.setVisible(false);
        lblUnidades.setText("unidades");
        lblUnidades.setFont(new Font("SansSerif", 1, 14));
        lblUnidades.setBounds(new Rectangle(235, 15, 75, 20));
        lblUnidades.setForeground(new Color(0, 114, 0));
        lblStock.setText("10");
        lblStock.setFont(new Font("SansSerif", 1, 15));
        lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
        lblStock.setBounds(new Rectangle(145, 10, 80, 30));
        lblStock.setForeground(new Color(0, 114, 0));
        lblFechaHora.setText("12/01/2006 09:20:34");
        lblFechaHora.setFont(new Font("SansSerif", 0, 12));
        lblFechaHora.setBounds(new Rectangle(10, 25, 130, 20));
        lblFechaHora.setForeground(new Color(0, 114, 0));
        lblStockTexto.setText("Stock del Producto al");
        lblStockTexto.setFont(new Font("SansSerif", 0, 12));
        lblStockTexto.setBounds(new Rectangle(10, 5, 125, 20));
        lblStockTexto.setForeground(new Color(0, 114, 0));
        lblUnidad.setText(" ");
        lblUnidad.setFont(new Font("SansSerif", 0, 11));
        lblUnidad.setBounds(new Rectangle(370, 75, 135, 20));
        lblUnidad.setForeground(new Color(0, 114, 0));
        lblUnidadT.setText("Unidad :");
        lblUnidadT.setFont(new Font("SansSerif", 1, 11));
        lblUnidadT.setBounds(new Rectangle(370, 50, 50, 20));
        lblUnidadT.setForeground(new Color(0, 114, 0));
        lblLaboratorio.setText("COLLIERE S.A.");
        lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
        lblLaboratorio.setBounds(new Rectangle(95, 95, 280, 20));
        lblLaboratorio.setForeground(new Color(0, 114, 0));
        lblLaboratorioT.setText("Laboratorio :");
        lblLaboratorioT.setFont(new Font("SansSerif", 1, 11));
        lblLaboratorioT.setBounds(new Rectangle(10, 95, 80, 20));
        lblLaboratorioT.setForeground(new Color(0, 117, 0));
        lblDescripcion.setText(" ");
        lblDescripcion.setFont(new Font("SansSerif", 0, 11));
        lblDescripcion.setBounds(new Rectangle(80, 75, 280, 20));
        lblDescripcion.setForeground(new Color(0, 117, 0));
        lblDescripcionT.setText("Descripcion :");
        lblDescripcionT.setFont(new Font("SansSerif", 1, 11));
        lblDescripcionT.setBounds(new Rectangle(80, 50, 95, 20));
        lblDescripcionT.setForeground(new Color(0, 116, 0));
        lblCodigo.setFont(new Font("SansSerif", 0, 11));
        lblCodigo.setBounds(new Rectangle(10, 75, 60, 20));
        lblCodigo.setForeground(new Color(0, 117, 0));
        lblCodigoT.setText("Codigo :");
        lblCodigoT.setFont(new Font("SansSerif", 1, 11));
        lblCodigoT.setBounds(new Rectangle(10, 50, 55, 20));
        lblCodigoT.setForeground(new Color(0, 116, 0));
        lblStockEntSug.setText("-");
        lblStockEntSug.setFont(new Font("SansSerif", 1, 11));
        lblStockEntSug.setBounds(new Rectangle(100, 10, 80, 20));
        lblS1.setText("Stock Entero :");
        lblS1.setBounds(new Rectangle(10, 10, 85, 20));
        lblS1.setMnemonic('f');
        lblS1.setFont(new Font("SansSerif", 1, 11));
        lblS1.setDefaultCapable(false);
        lblS1.setRequestFocusEnabled(false);
        lblS1.setBackground(new Color(50, 162, 65));
        lblS1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblS1.setFocusPainted(false);
        lblS1.setHorizontalAlignment(SwingConstants.LEFT);
        lblS1.setContentAreaFilled(false);
        lblS1.setBorderPainted(false);
        lblS1.setForeground(new Color(255, 140, 14));
        lblMensaje.setText("No existe producto sugerido");
        lblMensaje.setFont(new Font("Arial", 1, 15));
        lblMensaje.setBounds(new Rectangle(10, 40, 520, 20));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        //INCIO RPASCACIO 19-06-2017
        lblMsjAcumula.setText("Acumula 4 y 1 gratis.");
        lblMsjAcumula.setFont(new Font("Tahoma", 1, 14));
        lblMsjAcumula.setForeground(Color.red);
        lblMsjAcumula.setBounds(new Rectangle(5, 370, 540, 20));
       //FIN RPASCACIO 19-06-2017
        jPanelTitle1.setBounds(new Rectangle(5, 135, 545, 25));
        jLabelWhite1.setText("VENTA POR TRATAMIENTO");
        jLabelWhite1.setBounds(new Rectangle(10, 0, 365, 25));
        jLabelWhite1.setFont(new Font("SansSerif", 1, 13));

        lblTotalVentaRedondeado.setFont(new Font("SansSerif", 1, 15));
        lblTotalVentaRedondeado.setBounds(new Rectangle(290, 60, 100, 20));
        lblTotalVentaRedondeado.setAlignmentY((float)0.8);

        pnlIngresarProductos.add(lblTotalVentaRedondeado, null);
        pnlIngresarProductos.add(lblTotalVenta, null);
        pnlIngresarProductos.add(lblCantVenta, null);
        pnlIngresarProductos.add(btnProducto5, null);
        pnlIngresarProductos.add(btnProducto1, null);
        pnlIngresarProductos.add(txtCant, null);
        pnlIngresarProductos.add(txtCantxDia, null);
        pnlIngresarProductos.add(btn2, null);
        pnlIngresarProductos.add(btn1, null);
        pnlIngresarProductos.add(btnProducto, null);
        pnlIngresarProductos.add(lblCantTrata, null);
        pnlIngresarProductos1.add(lblMensaje, null);
        pnlIngresarProductos1.add(lblS1, null);
        pnlIngresarProductos1.add(lblStockEntSug, null);
        pnllHeader1.add(lblCodigoT, null);
        pnllHeader1.add(lblCodigo, null);
        pnllHeader1.add(lblDescripcionT, null);
        pnllHeader1.add(lblDescripcion, null);
        pnllHeader1.add(lblLaboratorioT, null);
        pnllHeader1.add(lblLaboratorio, null);
        pnllHeader1.add(lblUnidadT, null);
        pnllHeader1.add(lblUnidad, null);
        pnllHeader1.add(lblStockTexto, null);
        pnllHeader1.add(lblFechaHora, null);
        pnllHeader1.add(lblStock, null);
        pnllHeader1.add(lblUnidades, null);
        jPanelTitle1.add(jLabelWhite1, null);
        jContentPane.add(jPanelTitle1, null);
        jContentPane.add(lblEnter1, null);
        jContentPane.add(pnllHeader1, null);
        jContentPane.add(pnlIngresarProductos1, null); 
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblEnter, null);
        jContentPane.add(lblMsjAcumula, null);//RPASCACIO 19-06-2017
        pnlRelacionFiltros.add(lbl1, null);
        jContentPane.add(pnlRelacionFiltros, null);
        jContentPane.add(pnlIngresarProductos, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        //this.getContentPane().add(jContentPane, null);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        //initTable();
        FarmaVariables.vAceptar = false;
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTable() {
        // tableModelListaFiltro = new FarmaTableModel(ConstantsPtoVenta.columnsListaFiltro,ConstantsPtoVenta.defaultValuesListaFiltro,0);
        //FarmaUtility.initSimpleList(tblFiltro,tableModelListaFiltro,ConstantsPtoVenta.columnsListaFiltro);
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        VariablesVentas.vCant_Ingresada_Temp = "0";
        cantInic = FarmaUtility.trunc(FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada_Temp));
        muestraInfoDetalleProd();
        mostrarSugerido(false);
        if (VariablesVentas.vIndModificacion.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            iniciaModificacion();
        }

        FarmaUtility.moveFocus(txtCantxDia);
        
        operaListaProdAcumula();//RPASCACIO 20.06.2017
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            aceptaCantidadIngresada("VK_F11");
            //DVELIZ
            //cerrarVentana(true);
            
            
        } else if (UtilityPtoVenta.verificaVK_F12(e)) {
            if (vIndTieneSug) {
                aceptaCantidadIngresada("VK_F12");
                //DVELIZ
                //cerrarVentana(true);
            }
        }
    }

    private void txtCantxDia_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCantxDia, e);
    }

    private void txtCant_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCant, e);
    }

    private void txtCantxDia_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCantxDia.getText().trim().length() > 0)
                FarmaUtility.moveFocus(txtCant);
        } else
            chkKeyPressed(e);
    }

    private void txtCant_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCant.getText().trim().length() < 1 || Integer.parseInt(txtCant.getText().trim()) < 1) {
                FarmaUtility.showMessage(this, "Debe ingresar una cantidad de dias ", txtCant);
            } else {
                if (txtCantxDia.getText().trim().length() < 1 || Integer.parseInt(txtCantxDia.getText().trim()) < 1) {
                    FarmaUtility.showMessage(this, "Debe ingresar una cantidad por dia ", txtCantxDia);
                } else {
                    procesaTratamiento();
                }
            }
        } else
            chkKeyPressed(e);
    }


    private void cerrarVentana(boolean pAceptar) {
        
        if(vCtdBono>0){
           vCtdBono = Integer.parseInt(UtilityProgramaAcumula.getCtdRegaloConversion(
                                         getVCodCampAcumula(),
                                         lblCodigo.getText(),
                                         FarmaUtility.formatNumber(vCtdBono).replace(",", ""),
                                         VariablesVentas.vVal_Frac).trim());
               
        }
        
        
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************


    private void muestraInfoDetalleProd() {
        ArrayList myArray = new ArrayList();
        obtieneInfoProdEnArrayList(myArray);
        if (myArray.size() == 1) {
            VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(0)).trim(); //

            VariablesVentas.vStk_Prod_Fecha_Actual = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
            VariablesVentas.vUnid_Vta = ((String)((ArrayList)myArray.get(0)).get(4)).trim();
            VariablesVentas.vVal_Bono = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
            VariablesVentas.vVal_Prec_Lista = ((String)((ArrayList)myArray.get(0)).get(7)).trim();
            VariablesVentas.vPrecVtaCastLocal = ((String)((ArrayList)myArray.get(0)).get(8)).trim();

            VariablesVentas.vVal_Frac = FarmaUtility.getValueFieldArrayList(myArray, 0, 9);

            /*if((!VariablesVentas.vEsPedidoDelivery && !VariablesVentas.vEsPedidoInstitucional) || !VariablesVentas.vIngresaCant_ResumenPed)
        {
         //JCORTEZ 11/04/08 no se actualiza el precio y descuento si es producto  oferta
         if(!VariablesVentas.vIndOrigenProdVta.equals("5")){
            log.debug("SETEANDO DESCUENTO");
            VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(3)).trim();
            VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(6)).trim();
          }
          log.debug("VariablesVentas.vPorc_Dcto_1 : "+VariablesVentas.vPorc_Dcto_1);
          log.debug("VariablesVentas.vPorc_Dcto_2 : "+VariablesVentas.vPorc_Dcto_2);
        }*/
        } else {
            VariablesVentas.vStk_Prod = "";
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
        //lblPreUnit.setText(VariablesVentas.vVal_Prec_Vta);
        lblFechaHora.setText(VariablesVentas.vStk_Prod_Fecha_Actual);
        lblStock.setText("" + (Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic));
        //lblStock.setText(VariablesVentas.vStk_Prod);
        lblCodigo.setText(VariablesVentas.vCod_Prod);
        lblDescripcion.setText(VariablesVentas.vDesc_Prod);
        lblLaboratorio.setText(VariablesVentas.vNom_Lab);
        lblUnidad.setText(VariablesVentas.vUnid_Vta);
    }

    private void aceptaCantidadIngresada(String Fx) {
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        ArrayList myArray;
        if (!validaCantidadPorDias()) {
            FarmaUtility.showMessage(this, "Ingrese una cantidad correcta.", txtCantxDia);
            return;
        }
        if (!validaCantidadDias()) {
            FarmaUtility.showMessage(this, "Ingrese una cantidad correcta.", txtCant);
            return;
        }

        if (Fx.equalsIgnoreCase("VK_F11")) { //tratamiento
            VariablesVentas.vCant_Ingresada = lblCantVenta.getText().trim();
            VariablesVentas.vVal_Prec_Vta = lblPreUnit.trim(); //Al agregar el detalle, se recalcula
            double auxPrecLista = FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Lista);
            double auxPrecVta = FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta);
            VariablesVentas.vPorc_Dcto_1 =
                    FarmaUtility.formatNumber(((auxPrecLista - auxPrecVta) / auxPrecLista) * 100);

            VariablesVentas.vVal_Prec_Pub = VariablesVentas.vVal_Prec_Vta;

            VariablesVentas.vTotalPrecVtaTra = FarmaUtility.getDecimalNumber(lblTotalVenta.getText());

            // -- Añadido DUBILLUZ
            // COMENTADO POR JCALLO 23.10.2008
            /*VariablesVentas.vCantxDia  = txtCantxDia.getText().trim(); // 1
      VariablesVentas.vCantxDias  = txtCant.getText().trim();// 2*/
            // --FIN
            // LTAVARA 2016.11.15 CANTIDAD INGRESADA
            VariablesVentas.vCant_Ingresada_ItemQuote= VariablesVentas.vCant_Ingresada;
            // LTAVARA 2016.09.14 - VALIDAR LA CANTIDAD A COMPRAR CON EL ITEMQUOTE
            if ( UtilityPuntos.isActivoFuncionalidad() && VariablesPuntos.frmPuntos.getBeanTarjeta()!= null) {
                //cantidad en los programas que participa el producto
                int cantidadPrograma =facadeLealtad.verificaAcumulaX1(lblCodigo.getText());
                int cantidadIngresada=Integer.parseInt(VariablesVentas.vCant_Ingresada);
                ArrayList listaProductos=new ArrayList();
                ArrayList vDato = new ArrayList();
                String pCadena = "";               
                //PARTICIPA EN UN PROGRAMA
              if (cantidadPrograma > 0) {
                    boolean a;
                    try {
                     //INICIO RPASCACIO 19.06.2017   
                     listaProductos = new ArrayList();  
                     DBPuntos.getCampAcumulaPorProducto(listaProductos,lblCodigo.getText());
                     String prodEquivalente =  FarmaUtility.getValueFieldArrayList(listaProductos,0,1);   
                 if (listaProductos.size()==1) {                      
                    if(prodEquivalente.length()>6){
                          log.info("Antes de getConversionFraccion >> "+"acepta INGRESO TRATAMIENTO");
                        String cantidad=String.valueOf(cantidadIngresada).trim();                                    
                        cantidad=DBPuntos.getConversionFraccion(lblCodigo.getText(),
                                                               prodEquivalente,
                                                               VariablesVentas.vVal_Frac,
                                                               String.valueOf(cantidad));
                         cantidadIngresada=Integer.parseInt(cantidad.trim()); 
                       
                      }
                    //FIN RPASCACIO 19.06.2017 
                           cantidadIngresada= UtilityPuntos.cantidadBonificarCotiza(myParentFrame,
                                                                           this,
                                                                           cantidadIngresada,                                                                          
                                                                           lblCodigo.getText(),
                                                                           VariablesVentas.vVal_Frac,
                                                                           lblUnidad.getText(),
                                                                           true,
                                                                           vDato,
                                                                           prodEquivalente);   
                                
                          //   }
                           }  
                         } catch (Exception e) {
                             log.info(e.getMessage());
                         }
                   
                    vCtdBono = Integer.parseInt(vDato.get(1).toString());                        
                        
                        if(vCtdBono>0){
                            if(vCtdBono==1)
                                pCadena = "regalo";
                            else
                                pCadena = "regalos";
                            
                            if(UtilityProgramaAcumula.vAutomaticoIngresoCantidad){
                                vCtdBono = Integer.parseInt(vDato.get(1).toString());                        
                                vCtdNormal = Integer.parseInt(vDato.get(0).toString());  
                                vAccionAcumula = true;
                                vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                  //listaProductos.get(0).toString().substring(8);
                                vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                  //prodEquivalente;//;listaProductos.get(0).toString();
                            }
                            else{
                                //INICIO RPASCACIO 06-06-2017
                                String isExist="";
                            try {
                                vCtdNormal = Integer.parseInt(vDato.get(0).toString());  
                                isExist = DBVentas.validaStockSuficiente(lblCodigo.getText(),VariablesVentas.vVal_Frac,
                                                                                   vCtdBono,vCtdNormal);
                                
                            } catch (SQLException e) {                                
                                log.error("", e);
                            }
                            if(isExist.equals("S")){
                                //FIN RPASCACIO 06-06-2017
                                      
                                        if(JConfirmDialog.rptaConfirmDialog(this, 
                                                                            "Felicitaciones!!\n" +
                                                                            "Puedes llevarte "+vCtdBono +" "+pCadena+"\n"+
                                                                           "¿desea acceder al regalo?")){
                                           vCtdBono = Integer.parseInt(vDato.get(1).toString());                        
                                           vCtdNormal = Integer.parseInt(vDato.get(0).toString());  
                                           vAccionAcumula = true;
                                           vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                             //listaProductos.get(0).toString().substring(8);
                                           vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                             //prodEquivalente;//;listaProductos.get(0).toString();
                                        }
                                        else{
                                            vCtdBono=0;
                                            vCtdNormal = Integer.parseInt(lblCantVenta.getText().trim());
                                            vAccionAcumula = true;
                                            vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                              //listaProductos.get(0).toString().substring(8);
                                            vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                              //prodEquivalente;//;listaProductos.get(0).toString();//PERO SI ACUMULA
                                        }
                                    }
                                    //INICIO RPASCACIO 06-06-2017
                                   else{                                             
                                           FarmaUtility.showMessage(this, "No hay stock suficiente para acceder a la bonificación",
                                                                    cantidadIngresada);
                                            
                                            vCtdBono=0;
                                            vCtdNormal = cantidadIngresada;
                                            vAccionAcumula = true;
                                            vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                              //listaProductos.get(0).toString().substring(8);
                                            vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                              //prodEquivalente;//;listaProductos.get(0).toString();//PERO SI ACUMULA
                                       }
                                    //FIN RPASCACIO 06-06-2017
                            }
                        }
                        else{
                            vCtdBono=0;
                            vCtdNormal = cantidadIngresada;
                            vAccionAcumula = true;
                            vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                             //listaProductos.get(0).toString().substring(8);
                            vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                            //prodEquivalente;//;listaProductos.get(0).toString();
                        }
                  
                }

            }
            //FIN LTAVARA 2016.09.12  VALIDAR CON ORBIS LA CANTIDAD A DEL SUGERIDO  

            log.info("Escogio tratamiento");
        } else if (Fx.equalsIgnoreCase("VK_F12")) { //sugerido
            VariablesVentas.vIndTratamiento = FarmaConstants.INDICADOR_N;

            VariablesVentas.vUnid_Vta = VariablesVentas.vDesc_Unid_Vta_Sug;
            VariablesVentas.vVal_Prec_Lista = VariablesVentas.vPrecListaSug;
            VariablesVentas.vCant_Ingresada = lblCantSug.trim();
            VariablesVentas.vVal_Prec_Vta = lblPreUnitSug.trim();
            double auxPrecLista = FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Lista.trim());
            double auxPrecVta = FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta);
            VariablesVentas.vPorc_Dcto_1 =
                    FarmaUtility.formatNumber(((auxPrecLista - auxPrecVta) / auxPrecLista) * 100);

            VariablesVentas.vVal_Frac = VariablesVentas.vVal_Frac_Sug;
            VariablesVentas.vVal_Prec_Pub = VariablesVentas.vVal_Prec_Vta;

            VariablesVentas.vTotalPrecVtaTra = FarmaUtility.getDecimalNumber(lblTotalSug.trim());
            
            
            // -- Añadido DUBILLUZ
            // COMENTADO POR JCALLO 23.10.2008
            /*VariablesVentas.vCantxDia  = txtCantxDia.getText().trim(); // 1
            VariablesVentas.vCantxDias  = txtCant.getText().trim();// 2*/
            // --FIN
            // LTAVARA 2016.11.15 CANTIDAD INGRESADA
            VariablesVentas.vCant_Ingresada_ItemQuote= VariablesVentas.vCant_Ingresada;
            // LTAVARA 2016.09.14 - VALIDAR LA CANTIDAD A COMPRAR CON EL ITEMQUOTE
            if ( UtilityPuntos.isActivoFuncionalidad() && VariablesPuntos.frmPuntos.getBeanTarjeta()!= null) {
                //cantidad en los programas que participa el producto
                int cantidadPrograma =facadeLealtad.verificaAcumulaX1(lblCodigo.getText());
                int cantidadIngresada=Integer.parseInt(VariablesVentas.vCant_Ingresada);
                ArrayList listaProductos=new ArrayList();
                ArrayList vDato = new ArrayList();
                String prodEquivalente = ""; 
               
                String pCadena = "";               
                //PARTICIPA EN UN PROGRAMA
              if (cantidadPrograma > 0) {
                    boolean a;
                    try {
                     //INICIO RPASCACIO 19.06.2017
                     listaProductos = new ArrayList();
                     DBPuntos.getCampAcumulaPorProducto(listaProductos,lblCodigo.getText());   
                     prodEquivalente = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);   
                 if (listaProductos.size()==1) {                      
                    if(prodEquivalente.length()>6){
                          log.info("Antes de getConversionFraccion >> "+"acepta INGRESO TRATAMIENTO");
                        String cantidad=String.valueOf(cantidadIngresada).trim();
                        cantidad=DBPuntos.getConversionFraccion(lblCodigo.getText(),
                                                               prodEquivalente,
                                                               VariablesVentas.vVal_Frac,
                                                               String.valueOf(cantidad));
                         cantidadIngresada=Integer.parseInt(cantidad.trim());
                       
                      }
                    //FIN RPASCACIO 19.06.2017 
                           cantidadIngresada= UtilityPuntos.cantidadBonificarCotiza(myParentFrame,
                                                                           this,
                                                                           cantidadIngresada,                                                                          
                                                                           lblCodigo.getText(),
                                                                           VariablesVentas.vVal_Frac,
                                                                           lblUnidad.getText(),
                                                                           true,
                                                                           vDato,
                                                                           prodEquivalente);   
                                
                          //   }
                           }  
                         } catch (Exception e) {
                             log.info(e.getMessage());
                         }
                   
                    vCtdBono = Integer.parseInt(vDato.get(1).toString());                        
                        
                        if(vCtdBono>0){
                            /*if(vCtdBono==1)
                                pCadena = "regalo";
                            else
                                pCadena = "regalos";*/
                            
                            if(UtilityProgramaAcumula.vAutomaticoIngresoCantidad){
                                vCtdBono = Integer.parseInt(vDato.get(1).toString());                        
                                vCtdNormal = Integer.parseInt(vDato.get(0).toString());  
                                vAccionAcumula = true;
                                vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                  //listaProductos.get(0).toString().substring(8);
                                vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                  //prodEquivalente;//;listaProductos.get(0).toString();
                            }
                            else{
                            //INICIO RPASCACIO 06-06-2017
                                String isExist="";
                            try {
                                vCtdNormal = Integer.parseInt(vDato.get(0).toString());
                                isExist = DBVentas.validaStockSuficiente(lblCodigo.getText(),VariablesVentas.vVal_Frac,
                                                                                   vCtdBono,vCtdNormal);
                            } catch (SQLException e) {                                
                                log.error("", e);
                            }
                            if(isExist.equals("S")){
                            //FIN RPASCACIO 06-06-2017
                            
                            //INICIO RPASCACIO 06-07-2017
                               String pCtdNueva =""; 
                               String pConversion="";
                                try {
                                 pCtdNueva=DBPuntos.getConversionRegaloOrbis(prodEquivalente.substring(8),
                                                                              lblCodigo.getText(),
                                                                              FarmaUtility.formatNumber(vCtdBono).trim().replace(",",""),
                                                                              VariablesVentas.vVal_Frac);
                                    
                                 pConversion=DBPuntos.getConversionRegaloOrbis(prodEquivalente.substring(8),
                                                                     lblCodigo.getText(),
                                                                     FarmaUtility.formatNumber(cantidadIngresada).trim().replace(",",""),
                                                                     VariablesVentas.vVal_Frac);   
                                } catch (SQLException e) {
                                    log.error("", e);
                                }
                                
                                if(pCtdNueva.equals("1"))
                                       pCadena = "regalo";
                                else
                                       pCadena = "regalos";
                            //FIN RPASCACIO 06-07-2017
                                if(JConfirmDialog.rptaConfirmDialog(this, 
                                                                            "Felicitaciones!!\n" +
                                                                            "Puedes llevarte "+pCtdNueva +" "+pCadena+"\n"+
                                                                           "¿desea acceder al regalo?")){
                                           vCtdBono = Integer.parseInt(vDato.get(1).toString());                        
                                           vCtdNormal =Integer.parseInt(pConversion);
                                           vAccionAcumula = true;
                                           vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                             //listaProductos.get(0).toString().substring(8);
                                           vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                             //prodEquivalente;//;listaProductos.get(0).toString();
                                        }
                                        else{
                                            vCtdBono=0;
                                            vCtdNormal =Integer.parseInt(VariablesVentas.vCant_Ingresada);//cantidadIngresada;
                                            vAccionAcumula = true;
                                            vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                              //listaProductos.get(0).toString().substring(8);
                                            vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                              //prodEquivalente;//;listaProductos.get(0).toString();//PERO SI ACUMULA
                                        }
                                    }
                                    //INICIO RPASCACIO 06-06-2017
                                   else{                                             
                                           FarmaUtility.showMessage(this, "No hay stock suficiente para acceder a la bonificación",
                                                                    cantidadIngresada);
                                            
                                            vCtdBono=0;
                                            vCtdNormal =Integer.parseInt(VariablesVentas.vCant_Ingresada);
                                            vAccionAcumula = true;
                                            vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                                              //listaProductos.get(0).toString().substring(8);
                                            vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                                              //prodEquivalente;//;listaProductos.get(0).toString();//PERO SI ACUMULA
                                       }
                                    //FIN RPASCACIO 06-06-2017
                            }
                        }
                        else{
                            vCtdBono=0;
                            vCtdNormal = Integer.parseInt(VariablesVentas.vCant_Ingresada);
                            vAccionAcumula = true;
                            vCodCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,0);
                                             //listaProductos.get(0).toString().substring(8);
                            vCodEQCampAcumula = FarmaUtility.getValueFieldArrayList(listaProductos,0,1);
                                            //prodEquivalente;//;listaProductos.get(0).toString();
                        }
                  
                }

            }
            //FIN LTAVARA 2016.09.12  VALIDAR CON ORBIS LA CANTIDAD A DEL SUGERIDO  

            log.info("Escogio tratamiento");
          
          
            
            

            log.info("Acepto sugerido");
        }
        cerrarVentana(true);


        //VariablesVentas.vTotalPrecVtaProd = (FarmaUtility.getDecimalNumber(VariablesVentas.vCant_Ingresada) *
        //FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta));

    }

    private boolean validaCantidadPorDias() {
        boolean valor = false;
        String cantIngreso = txtCantxDia.getText().trim();
        if (FarmaUtility.isInteger(cantIngreso) && Integer.parseInt(cantIngreso) > 0)
            valor = true;
        return valor;
    }

    private boolean validaCantidadDias() {
        boolean valor = false;
        String cantIngreso = txtCant.getText().trim();
        if (FarmaUtility.isInteger(cantIngreso) && Integer.parseInt(cantIngreso) > 0)
            valor = true;
        return valor;
    }

    private boolean validaStockActual() {
        boolean valor = false;
        //obtieneStockProducto();
        String cantIngreso = lblCantTrata.getText().trim();
        if ((Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic) >= Integer.parseInt(cantIngreso))
            valor = true;
        return valor;
    }

    private void obtieneStockProducto() {
        ArrayList myArray = new ArrayList();
        obtieneStockProducto_ForUpdate(myArray);
        if (myArray.size() == 1) {
            VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
            //VariablesVentas.vVal_Prec_Vta = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
            VariablesVentas.vPorc_Dcto_1 = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
        } else {
            FarmaUtility.showMessage(this, "Error al obtener Stock del Producto", null);
            cerrarVentana(false);
        }
    }

    private void obtieneStockProducto_ForUpdate(ArrayList pArrayList) {
        try {
            DBVentas.obtieneStockProducto_ForUpdate(pArrayList, VariablesVentas.vCod_Prod, VariablesVentas.vVal_Frac);
            FarmaUtility.liberarTransaccion();
            //quitar bloqueo de stock fisico
            //dubilluz 13.10.2011
        } catch (SQLException sql) {
            //log.error("",sql);
            FarmaUtility.liberarTransaccion();
            //quitar bloqueo de stock fisico
            //dubilluz 13.10.2011
            log.error(null, sql);
            FarmaUtility.showMessage(this, "Error al obtener stock del producto. \n" +
                    sql.getMessage(), txtCant);
        }
    }

    private void obtieneInfoProdEnArrayList(ArrayList pArrayList) {
        try {
            DBVentas.obtieneInfoDetalleProducto(pArrayList, VariablesVentas.vCod_Prod);
        } catch (SQLException sql) {
            //log.error("",sql);
            log.error(null, sql);
            FarmaUtility.showMessage(this, "Error al obtener informacion del producto. \n" +
                    sql.getMessage(), txtCant);
        }
    }

    private void procesaTratamiento() {
        procesaTratamiento(false);
    }

    private void procesaTratamiento(boolean pInicioModificacion) {
        if (txtCantxDia.getText().length() > 0) {
            int auxCantxDia = Integer.parseInt(txtCantxDia.getText().trim());
            int auxCantDias = Integer.parseInt(txtCant.getText().trim());

            double cantTra = (auxCantxDia * auxCantDias);

            if (cantTra > 999999) {
                FarmaUtility.showMessage(this, "El tratamiento no es válido. ¡Verifique las cantidades!", txtCant);
                return;
            }

            lblCantTrata.setText("" + (int)cantTra);

            //JCALLO 23.10.2008
            VariablesVentas.vCantxDia = txtCantxDia.getText().trim();
            VariablesVentas.vCantxDias = txtCant.getText().trim();

            log.debug(VariablesVentas.vVal_Dsct_cast + " * " + VariablesVentas.vVal_Prec_Pub);
            // double PrecUnit=FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Dsct_cast)*FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Pub);
            double PrecUnit =
                FarmaUtility.getDecimalNumber(VariablesVentas.vPrecVtaCastLocal); //aplicando porcentaje castigo
            lblPreUnit = "" + PrecUnit;

            int stockFrac = Integer.parseInt(lblStock.getText().trim());
            int cantVta;
            boolean pActivoVtaNegativa = false;
            if ((VariablesVentas.vEsPedidoDelivery && UtilityVentas.getIndVtaNegativa())) {
                if (pInicioModificacion) {
                    pActivoVtaNegativa = true;
                } else if (UtilityVentas.permiteVtaNegativa(myParentFrame, this, VariablesVentas.vCod_Prod,
                                                            "" + (int)cantTra, VariablesVentas.vVal_Frac)) {
                    pActivoVtaNegativa = true;
                }
            }

            if (cantTra >= stockFrac && !pActivoVtaNegativa) {
                if (!validaStockActual()) {
                    FarmaUtility.liberarTransaccion();
                    FarmaUtility.showMessage(this, "La cantidad de tratamiento es mayor al stock.", txtCantxDia);
                    //lblStock.setText("" + (Integer.parseInt(VariablesVentas.vStk_Prod) + cantInic));
                    //return;
                }
                cantVta = stockFrac;
            } else {
                cantVta = (int)cantTra;
            }
            lblCantVenta.setText("" + cantVta);


            calculaProdSugerido(); //
            FarmaUtility.moveFocus(txtCantxDia);
        }
    }

    private void calculaProdSugerido() {

        Integer cantidadVtaLocal=0;
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        lblDescProdSug = VariablesVentas.vDesc_Prod;
        lblUnidPresProdSug = VariablesVentas.vUnid_Vta;

        VariablesVentas.vCant_Vta = lblCantVenta.getText().trim();


        ArrayList myArray = new ArrayList();
        obtieneInfoProdEnArrayListSug(myArray);
        //obtiene el total de la venta x tratamiento porque la vCant_Vta se va a modificar
        VariablesVentas.vTotal_Vta = FarmaUtility.getValueFieldArrayList(myArray, 0, 11);
        //INICIO LTAVARA 2016.09.12  VALIDAR CON ORBIS LA CANTIDAD A DEL SUGERIDO 
        VariablesVentas.vVal_Frac_Sug = (String)((ArrayList)myArray.get(0)).get(8);
        lblCantSug = (String)((ArrayList)myArray.get(0)).get(0);
        
        // LTAVARA 2016.07.26 - VALIDAR SI EL PRODUCTO PARTICIPA EN X+1
        if ( UtilityPuntos.isActivoFuncionalidad() && VariablesPuntos.frmPuntos.getBeanTarjeta()!= null) {
            //cantidad en los programas que participa el producto
            int cantidadPrograma = facadeLealtad.verificaAcumulaX1(lblCodigo.getText());
            int cantidadIngresada=Integer.parseInt(lblCantSug);
            //PARTICIPA EN UN PROGRAMA
            if (cantidadPrograma > 0) {
                    boolean a;
                     try {
                       String cantProdFracLocal = DBPuntos.getCantProdFracLocal(cantidadIngresada, lblCodigo.getText(),VariablesVentas.vVal_Frac_Sug, "ENVIAR");
                         /*if(UtilityPuntos.esDecimal( cantProdFracLocal) && !cantProdFracLocal.equals("0")){
                             ArrayList vDato = new ArrayList();
                        cantidadIngresada= UtilityPuntos.cantidadBonificarCotiza(myParentFrame, 
                                                                                 this, 
                                                                                 Integer.parseInt(cantProdFracLocal), 
                                                                                 lblCodigo.getText(),
                                                                                 VariablesVentas.vVal_Frac_Sug,
                                                                                 lblUnidad.getText(),
                                                                                 false,
                                                                                 vDato);
                         }*/
                         if( cantidadIngresada!= Integer.parseInt(lblCantSug)){
                          
                                
                                cantidadVtaLocal=(cantidadIngresada * Integer.parseInt(VariablesVentas.vVal_Frac))/Integer.parseInt(VariablesVentas.vVal_Frac_Sug);
                                 VariablesVentas.vCant_Vta =cantidadVtaLocal.toString();
                                 myArray=new ArrayList();
                                 obtieneInfoProdEnArrayListSug(myArray);
                                 log.info("SON DISTINTOS:::::"+cantidadIngresada+"::::::"+lblCantSug);
                             }
                     } catch (Exception e) {
                         //continua con el anterior
                         VariablesVentas.vVal_Frac_Sug = (String)((ArrayList)myArray.get(0)).get(8);
                         lblCantSug = (String)((ArrayList)myArray.get(0)).get(0);
    
                     }
            }

        }
        //FIN LTAVARA 2016.09.12  VALIDAR CON ORBIS LA CANTIDAD A DEL SUGERIDO  
        //if(myArray.size() == 1) {
        /*VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(4)).trim();
    VariablesVentas.vVal_Prec_Vta_Sug = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
    VariablesVentas.vVal_Frac_Sug = ((String)((ArrayList)myArray.get(0)).get(7)).trim();
    VariablesVentas.vDesc_Unid_Vta_Sug=((String)((ArrayList)myArray.get(0)).get(9)).trim();
    VariablesVentas.vPrec_Unit_Sug=((String)((ArrayList)myArray.get(0)).get(9)).trim();*/

        log.debug("", myArray);

        //lblStockEntSug.setText("");
        lblCantSug = (String)((ArrayList)myArray.get(0)).get(0);
        lblTotalSug = (String)((ArrayList)myArray.get(0)).get(1);
        //JCHAVEZ 29102009 inicio
        try {
            double precSugeridoRedondeadoNum = DBVentas.getPrecioRedondeado(Double.parseDouble(lblTotalSug));
            /*
           * Modificacion
           * Autor: ASOSA
           * Fecha: 28.12.2009
           *<<---------------- */
            /*String*/lblTotalSugRedondeado =
                    FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + precSugeridoRedondeadoNum), 3);
            //------------------>>

        } catch (SQLException ex) {
            log.error("", ex);
        }

        //JCHAVEZ 29102009 fin
        lblUnidPresProdSug = (String)((ArrayList)myArray.get(0)).get(2);
        VariablesVentas.vDesc_Unid_Vta_Sug = ((String)((ArrayList)myArray.get(0)).get(2)).trim();

        lblStockEntSug.setText((String)((ArrayList)myArray.get(0)).get(3));

        lblDescProdSug = (String)((ArrayList)myArray.get(0)).get(5);
        lblPreUnitSug = (String)((ArrayList)myArray.get(0)).get(6);
        VariablesVentas.vPrec_Unit_Sug = (String)((ArrayList)myArray.get(0)).get(6);

        lblStockFracSug = (String)((ArrayList)myArray.get(0)).get(7);
        
        VariablesVentas.vPrecListaSug = (String)((ArrayList)myArray.get(0)).get(9);
        VariablesVentas.vValFracLocal = (String)((ArrayList)myArray.get(0)).get(10);
        //VariablesVentas.vTotal_Vta = FarmaUtility.getValueFieldArrayList(myArray, 0, 11);

        VariablesVentas.vVal_Frac_Sug = (String)((ArrayList)myArray.get(0)).get(8);
        lblCantSug = (String)((ArrayList)myArray.get(0)).get(0);

        //JCHAVEZ 29102009 inicio
        try {
            double precTotalVtaRedondeadoNum =
                DBVentas.getPrecioRedondeado(Double.parseDouble(VariablesVentas.vTotal_Vta));
            log.debug("precTotalVtaRedondeadoNum: " + precTotalVtaRedondeadoNum);
            String precTotalVtaRedondeadoStr =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber("" + precTotalVtaRedondeadoNum), 3);
            lblTotalVentaRedondeado.setText(precTotalVtaRedondeadoStr);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        //JCHAVEZ 29102009 fin

        lblTotalVenta.setText(VariablesVentas.vTotal_Vta);
        String auxIndSug = FarmaUtility.getValueFieldArrayList(myArray, 0, 12);

        if (auxIndSug.equalsIgnoreCase(FarmaConstants.INDICADOR_S) &&
            VariablesVentas.vIndModificacion.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
            mostrarSugerido(true);
        } else if (VariablesVentas.vEsPedidoDelivery && UtilityVentas.getIndVtaNegativa() &&
                   VariablesVentas.vIndModificacion.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
            mostrarSugerido(true);
        } else {
            mostrarSugerido(false);
        }
        //lblStockEntSug.setText(""+Int((Integer.parseInt(VariablesVentas.vStk_Prod)));
        //lblStockEntSug.setText(""+(new Integer(VariablesVentas.vStk_Prod)));

        // lblStockFracSug.setText(""+Integer.parseInt(VariablesVentas.vStk_Prod)-Int(VariablesVentas.vStk_Prod));
        /* int s1=Integer.parseInt(VariablesVentas.vStk_Prod);

     double auxStk = FarmaUtility.getDecimalNumber(VariablesVentas.vStk_Prod.trim());
     int s2= (int) auxStk;

      lblStockFracSug.setText(""+(s1-s2));

     double  cantsug=0;
     double  precSug=0;
     double  prevta=FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Prec_Vta_Sug.trim());
     int  fracc=Integer.parseInt(VariablesVentas.vVal_Frac_Sug.trim());
     int  cant  =Integer.parseInt(lblCantVenta.getText().trim());

    if(s2-(cant/fracc)>=1){
     lblCantSug.setText(""+lblCantVenta.getText().trim());
     lblTotalSug.setText(""+(cant*prevta));
    }else{
    cantsug=(Integer.parseInt(lblCantVenta.getText().trim())/Integer.parseInt(VariablesVentas.vVal_Frac_Sug)+1);
    precSug=cantsug*FarmaUtility.getDecimalNumber(VariablesVentas.vVal_Frac_Sug);
     lblCantSug.setText(""+cantsug);
     lblTotalSug.setText(""+precSug);
    }*/


        //mensaje
        /* if(FarmaUtility.getDecimalNumber(lblTotalVenta.getText().trim())<FarmaUtility.getDecimalNumber(lblTotalSug.getText().trim()))
    mostrarSugerido(false);
    else
    mostrarSugerido(true);
*/
    }

    private void obtieneInfoProdEnArrayListSug(ArrayList pArrayList) {
        try {
            //ERIOS 2.4.4 Precio convenio
            if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF &&
                VariablesVentas.vCod_Prod.trim().length() == 6) {
                DBConvenioBTLMF.obtieneInfoProductoSug(pArrayList, VariablesVentas.vCod_Prod,
                                                       VariablesVentas.vCant_Vta);
            } else {
                DBVentas.obtieneInfoProductoSug(pArrayList, VariablesVentas.vCod_Prod, VariablesVentas.vCant_Vta);
            }
        } catch (SQLException sql) {
            //log.error("",sql);
            log.error(null, sql);
            FarmaUtility.showMessage(this, "Error al obtener informacion del producto sugerido. \n" +
                    sql.getMessage(), txtCant);
        }
    }

    private void mostrarSugerido(boolean valor) {


        lblS1.setVisible(valor);
        lblStockEntSug.setVisible(valor);
        lblEnter1.setVisible(valor);
        lblMensaje.setText("Llévate " + lblCantSug + " " + lblUnidPresProdSug + " por sólo "+ConstantesUtil.simboloSoles +
                           lblTotalSugRedondeado); //JCHAVEZ 29102009 se cambio lblTotalSug por lblTotalSugRedondeado
        lblMensaje.setVisible(valor);
        vIndTieneSug = valor;
    }

    private void btn1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtCantxDia);
    }

    private void iniciaModificacion() {
        txtCantxDia.setText(VariablesVentas.vCantxDia);
        txtCant.setText(VariablesVentas.vCantxDias);
        lblCantVenta.setText(VariablesVentas.vCant_Vta);
        //VariablesVentas.vStk_Prod = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
        muestraInfoDetalleProd();
        procesaTratamiento(true);

    }
   //INICIO RPASCACIO 20.06.2017 
    public void operaListaProdAcumula(){
        muestra_prom_acumula();
        
        if(!lblMsjAcumula.isVisible()){
            vListaAcumula = new ArrayList();
            try {
                DBVentas.getListCampaAcumula_x_Prod(vListaAcumula, VariablesVentas.vCod_Prod);
            } catch (Exception sqle) {
                // TODO: Add catch code
                sqle.printStackTrace();
            }
            
            /*for(int i=0;i<vListaAcumula.size();i++) {
                tableModelListaPromociones.insertRow((ArrayList)vListaAcumula.get(i));
            } */
            if(UtilityPuntos.isActivoFuncionalidad()){//RPASCACIO 14.07.2017   
                if(vListaAcumula.size()>0){
                    // existe una muestra definicion.
                    String vCampana = FarmaUtility.getValueFieldArrayList(vListaAcumula, 0, COL_DESC_LARGA);
                    lblMsjAcumula.setText("" +vCampana);
                    lblMsjAcumula.setVisible(true);
                }
            } //RPASCACIO 14.07.2017
        }
    }
    
    public void muestra_prom_acumula(){
        lblMsjAcumula.setText("");
        lblMsjAcumula.setVisible(false);
        
        ArrayList listaProductos;
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        VariablesVentas.vIndAplicaPrecNuevoCampanaCupon = FarmaConstants.INDICADOR_N;
        BeanTarjeta tarjetaBean=null;
        if( VariablesPuntos.frmPuntos!=null){
            tarjetaBean=VariablesPuntos.frmPuntos.getBeanTarjeta();
        }
           
        if (UtilityPuntos.isActivoFuncionalidad() && tarjetaBean!= null) {
            listaProductos = (ArrayList<String>)tarjetaBean.getListaAuxiliarInscritos();
            //Validar si el producto participa en x+1 de los programas inscritos del cliente monedero           
           if (facadeLealtad.verificaInscripcionProducto(listaProductos,lblCodigo.getText())!= "") {
                    
                    //DEBE VALIDARSE PARA EL PROGRAMA QUE EL CODIGO EXISTA
                    for(int v =0;v<listaProductos.size();v++){
                        String pCodAcumulaEq_AUX = listaProductos.get(v).toString().trim();
                        if(pCodAcumulaEq_AUX.indexOf(lblCodigo.getText())!=-1){
                            // una sola promocion por producto
                            String pCodAcumulaEquivalente = listaProductos.get(v).toString().trim();
                            // si esta inscrito objtendra la cantidad acumulada hasta el momento.
                            String listaProductoAcumCliente="N"; 
                            BeanTarjeta tarjetaCliente =   VariablesPuntos.frmPuntos.getGanarBonificaciones(tarjetaBean.getNumeroTarjeta(), 
                                                                                                            "001");
                            if(tarjetaCliente!=null && !tarjetaCliente.getListaPendienteAcumular().isEmpty()){
                                   List lstProducto = tarjetaCliente.getListaPendienteAcumular();
                                   for(int i=0;i<lstProducto.size();i++){
                                       String[] prod = lstProducto.get(i).toString().split(",");
                                       String codProdEQ=prod[0];
                                       String cantPendAcum=prod[1];
                                       if(pCodAcumulaEquivalente.trim().equalsIgnoreCase(codProdEQ.trim())){
                                           lblMsjAcumula.setText("Te faltan "+cantPendAcum+" unidades para ganar uno gratis.");
                                           lblMsjAcumula.setVisible(true);
                                           break;
                                       }
                                   }
                               }
                            }
                    }
                    
                    if(!lblMsjAcumula.isVisible()){
                        lblMsjAcumula.setText("No tienes acumulado unidades para este producto.");
                        lblMsjAcumula.setVisible(true);
                    }
                }
        }
    }
//FIN RPASCACIO 20.06.2017.
    
    
    public void setVCtdNormal(int vCtdNormal) {
        this.vCtdNormal = vCtdNormal;
    }

    public int getVCtdNormal() {
        return vCtdNormal;
    }

    public void setVCtdBono(int vCtdBono) {
        this.vCtdBono = vCtdBono;
    }

    public int getVCtdBono() {
        return vCtdBono;
    }


    public void setVAccionAcumula(boolean vAccionAcumula) {
        this.vAccionAcumula = vAccionAcumula;
    }

    public boolean isVAccionAcumula() {
        return vAccionAcumula;
    }


    public void setVCodCampAcumula(String vCodCampAcumula) {
        this.vCodCampAcumula = vCodCampAcumula;
    }

    public String getVCodCampAcumula() {
        return vCodCampAcumula;
    }


    public void setVCodEQCampAcumula(String vCodEQCampAcumula) {
        this.vCodEQCampAcumula = vCodEQCampAcumula;
    }

    public String getVCodEQCampAcumula() {
        return vCodEQCampAcumula;
    }
    
}
