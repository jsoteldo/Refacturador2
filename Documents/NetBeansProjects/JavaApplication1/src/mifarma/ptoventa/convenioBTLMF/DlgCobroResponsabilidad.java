package mifarma.ptoventa.convenioBTLMF;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelTitle;

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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (c) 2008 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DlgCobroResponsabilidad.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * JMONZALVE       24.04.2019   Creacion <br>
 * <br>
 * @author  Jhony Monzalve V.<br>
 * @version 1.0<br>
 *
 */

public class DlgCobroResponsabilidad extends JDialog {
    /* ************************************************************************** */
    /*                           DECLARACION PROPIEDADES                          */
    /* ************************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(DlgCobroResponsabilidad.class);

    private Frame myParentFrame;

    private static FarmaTableModel tableModelListaEmpleado_cobro;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JPanel jPanel1 = new JPanel();
    private JLabel lblDescLab_Prod = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JPanel pnlIngresarProductos = new JPanel();
    private JButton btnBuscar = new JButton();
    private JTextField txtCodTrabajador = new JTextField();
    private JButtonLabel btnProducto = new JButtonLabel();
    private JTable tblCobro_Responsabilidad = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF4 = new JLabelFunction();

    private JButtonLabel btnRelacion = new JButtonLabel();
    private JPanel jPanel5 = new JPanel();
    private JLabel lblTotal = new JLabel();
    private JLabelWhite lblIndTipoProd = new JLabelWhite();
    private JLabel lblMonto = new JLabel();
    private JLabelFunction lblF10 = new JLabelFunction();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();

    private JTextField txtDescTrabajador = new JTextField();
    private JSplitPane jSplitPane1 = new JSplitPane();
    private JButtonLabel btnImporteTotal = new JButtonLabel();
    private JTextField txtImporteTotal = new JTextField();
    private JButtonLabel btnImporteAsumir = new JButtonLabel();
    private JTextField txtImporteAsumir = new JTextField();
    private JComboBox cmbMotivoRegularizacion = new JComboBox();
    private JButtonLabel lblMotivoRegularizacion = new JButtonLabel();
    private String codigoTrabajador = "";
    private String datosTrabajador = "";
    private String estadoTrabajador = "";
    private JSeparator jSeparator2 = new JSeparator();
    private String mensajes = "";

    /* ************************************************************************** */
    /*                              CONSTRUCTORES                                 */
    /* ************************************************************************** */

    public DlgCobroResponsabilidad() {
        this(null, "", false, "0.00");
    }

    public DlgCobroResponsabilidad(Frame parent, String title, boolean modal, String montoTotal) {
        super(parent, title, modal);
        myParentFrame = parent;
        txtImporteTotal.setText(montoTotal);
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    /* ************************************************************************** */
    /*                              METODO jbInit                                 */
    /* ************************************************************************** */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(737, 493));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ventas al Personal");
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
        jContentPane.setSize(new Dimension(623, 439));
        jContentPane.setForeground(Color.white);
        lblF1.setText("[ F1 ] Adicionar");
        lblF1.setBounds(new Rectangle(540, 145, 150, 20));
        jScrollPane1.setBounds(new Rectangle(10, 215, 705, 180));
        jScrollPane1.setBackground(new Color(255, 130, 14));
        jPanel1.setBounds(new Rectangle(10, 195, 705, 20));
        jPanel1.setBackground(new Color(255, 130, 14));
        jPanel1.setLayout(null);
        lblDescLab_Prod.setBounds(new Rectangle(160, 0, 345, 20));
        lblDescLab_Prod.setFont(new Font("SansSerif", 1, 11));
        lblDescLab_Prod.setForeground(Color.white);
        jSeparator1.setBounds(new Rectangle(150, 0, 15, 20));
        jSeparator1.setBackground(Color.black);
        jSeparator1.setOrientation(SwingConstants.VERTICAL);
        pnlIngresarProductos.setBounds(new Rectangle(10, 5, 705, 180));
        pnlIngresarProductos.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlIngresarProductos.setBackground(new Color(43, 141, 39));
        pnlIngresarProductos.setLayout(null);
        pnlIngresarProductos.setForeground(Color.orange);
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(585, 80, 105, 20));
        btnBuscar.setMnemonic('b');
        btnBuscar.setDefaultCapable(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.setFont(new Font("SansSerif", 1, 12));
        txtCodTrabajador.setBounds(new Rectangle(135, 80, 115, 20));
        txtCodTrabajador.setFont(new Font("SansSerif", 1, 15));
        txtCodTrabajador.setForeground(new Color(32, 105, 29));
        txtCodTrabajador.setHorizontalAlignment(JTextField.RIGHT);
        txtCodTrabajador.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtProducto_keyPressed(e);

                }
            
        });
        btnProducto.setText("Codigo Trabajador :");
        btnProducto.setBounds(new Rectangle(10, 80, 125, 20));
        btnProducto.setMnemonic('C');
        btnProducto.setFont(new Font("SansSerif", 1, 12));
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
        tblCobro_Responsabilidad.setFont(new Font("SansSerif", 0, 12));
        tblCobro_Responsabilidad.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblCobro_Responsabilidad_keyPressed(e);
            }
        });
        btnProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnProducto_actionPerformed(e);
                }
        });
        lblEsc.setText("[ ESC ] Salir");
        lblEsc.setBounds(new Rectangle(600, 425, 115, 20));
        lblF4.setText("[ F4 ] Eliminar");
        lblF4.setBounds(new Rectangle(10, 425, 145, 20));
        btnRelacion.setText("Relaci\u00f3n");
        btnRelacion.setBounds(new Rectangle(10, 0, 140, 20));
        btnRelacion.setMnemonic('R');
        btnRelacion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRelacion_actionPerformed(e);
                }
            });
        jPanel5.setBounds(new Rectangle(10, 395, 705, 20));
        jPanel5.setBackground(new Color(255, 130, 14));
        jPanel5.setLayout(null);
        lblTotal.setBounds(new Rectangle(495, 0, 100, 20));
        lblTotal.setVisible(true);
        lblTotal.setFont(new Font("SansSerif", 1, 12));
        lblTotal.setText("TOTAL : S/.  ");
        lblTotal.setBackground(new Color(44, 146, 24));
        lblTotal.setOpaque(true);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        lblTotal.setForeground(Color.white);
        lblIndTipoProd.setBounds(new Rectangle(545, 0, 140, 20));
        lblIndTipoProd.setFont(new Font("SansSerif", 1, 12));
        lblMonto.setBounds(new Rectangle(590, 0, 90, 20));
        lblMonto.setVisible(true);
        lblMonto.setFont(new Font("SansSerif", 1, 12));
        lblMonto.setText("0.00");
        lblMonto.setBackground(new Color(44, 146, 24));
        lblMonto.setOpaque(true);
        lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMonto.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        lblMonto.setForeground(Color.white);
        lblF10.setBounds(new Rectangle(450, 425, 135, 20));
        lblF10.setText("[ F10 ] Aceptar");
        txtDescTrabajador.setBounds(new Rectangle(255, 80, 315, 20));
        txtDescTrabajador.setEditable(false);
        btnImporteTotal.setText("Importe Total a Pagar :");
        btnImporteTotal.setBounds(new Rectangle(10, 25, 135, 20));
        btnImporteTotal.setFont(new Font("SansSerif", 1, 12));
        txtImporteTotal.setBounds(new Rectangle(145, 25, 140, 20));
        txtImporteTotal.setHorizontalAlignment(JTextField.RIGHT);
        txtImporteTotal.setFont(new Font("Tahoma", 1, 12));
        btnImporteAsumir.setText("Importe a Asumir :");
        btnImporteAsumir.setBounds(new Rectangle(10, 120, 125, 20));
        btnImporteAsumir.setMnemonic('I');
        btnImporteAsumir.setFont(new Font("SansSerif", 1, 12));
        btnImporteAsumir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnImporteAsumir_actionPerformed(e);
                }
            });
        txtImporteAsumir.setBounds(new Rectangle(135, 120, 115, 20));
        txtImporteAsumir.setText("0.00");
        txtImporteAsumir.setHorizontalAlignment(JTextField.RIGHT);
        txtImporteAsumir.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtImporteAsumir_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtImporteAsumir_keyTyped(e);
                }

                public void keyReleased(KeyEvent e) {
                    txtImporteAsumir_keyReleased(e);
                }
            });
        cmbMotivoRegularizacion.setBounds(new Rectangle(510, 25, 180, 20));
        cmbMotivoRegularizacion.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbMotivoRegularizacion_keyPressed(e);
                }
            });
        lblMotivoRegularizacion.setText("Motivo Regularizaci\u00f3n :");
        lblMotivoRegularizacion.setBounds(new Rectangle(370, 25, 135, 20));
        lblMotivoRegularizacion.setMnemonic('M');
        lblMotivoRegularizacion.setFont(new Font("SansSerif", 1, 12));
        lblMotivoRegularizacion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblMotivoRegularizacion_actionPerformed(e);
                }
            });
        jSeparator2.setBounds(new Rectangle(10, 60, 680, 2));
        jSeparator2.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 2));
        jSeparator2.setForeground(SystemColor.window);
        jScrollPane1.getViewport();
        jPanel1.add(lblIndTipoProd, null);
        jPanel1.add(btnRelacion, null);
        jPanel1.add(lblDescLab_Prod, null);
        jPanel1.add(jSeparator1, null);
        pnlIngresarProductos.add(jSeparator2, null);
        pnlIngresarProductos.add(lblMotivoRegularizacion, null);
        pnlIngresarProductos.add(cmbMotivoRegularizacion, null);
        pnlIngresarProductos.add(txtImporteAsumir, null);
        pnlIngresarProductos.add(btnImporteAsumir, null);
        pnlIngresarProductos.add(txtImporteTotal, null);
        pnlIngresarProductos.add(btnImporteTotal, null);
        pnlIngresarProductos.add(txtDescTrabajador, null);
        pnlIngresarProductos.add(btnBuscar, null);
        pnlIngresarProductos.add(txtCodTrabajador, null);
        pnlIngresarProductos.add(btnProducto, null);
        pnlIngresarProductos.add(lblF1, null);
        jPanel5.add(lblTotal, null);
        jPanel5.add(lblMonto, null);
        jContentPane.add(lblF10, null);
        jContentPane.add(jPanel5, null);
        jContentPane.add(lblF4, null);
        jContentPane.add(lblEsc, null);
        jScrollPane1.getViewport().add(tblCobro_Responsabilidad, null);
        jContentPane.add(jScrollPane1, null);
        jContentPane.add(jPanel1, null);
        jContentPane.add(pnlIngresarProductos, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************** */
    /*                            METODO initialize                               */
    /* ************************************************************************** */

    private void initialize() {
        initTableListaEmpleado_Cobro();
        initComboMotivos();
        FarmaVariables.vAceptar = false;
        txtImporteTotal.setEditable(false);
    }

    /* ************************************************************************** */
    /*                          METODOS INICIALIZACION                            */
    /* ************************************************************************** */

    private void initTableListaEmpleado_Cobro() {
        tableModelListaEmpleado_cobro =
                new FarmaTableModel(ConstantsConv_Responsabilidad.columnsListaEmpleado_Cobro, ConstantsConv_Responsabilidad.defaultValuesListaEmpleado_Cobro, 0);
        FarmaUtility.initSimpleList(tblCobro_Responsabilidad, tableModelListaEmpleado_cobro,ConstantsConv_Responsabilidad.columnsListaEmpleado_Cobro);
        
    }
    
    private void initComboMotivos() {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbMotivoRegularizacion, ConstantsConv_Responsabilidad.NOM_HASTABLE_CMBMOTIVOS_RESPONSABILIDAD,
                                   "PTOVENTA_CONV_COBRO_RESP.GET_LIST_MOT_REG(?)", parametros, true, true);
    }

    /* ************************************************************************** */
    /*                            METODOS DE EVENTOS                              */
    /* ************************************************************************** */

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(cmbMotivoRegularizacion);
        ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad = new ArrayList();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);

    }
    
    private boolean validaDatosIngresados(){
        if(codigoTrabajador.length() <= 0){
            mensajes = "Código de Trabajador Obligatorio !!!";
            return false;
        }
        if(txtImporteAsumir.getText().trim().length() <= 0){
            mensajes = "Importe a Asumir Obligatorio !!!";
            return false;
        }
        if(txtImporteAsumir.getText().trim().length() > 0 && Double.parseDouble(txtImporteAsumir.getText().trim()) <= 0){
            mensajes = "Importe a Asumir debe de ser mayor a 0 !!!";
            return false;
        }
        return true;
    }
    
    private boolean buscarEmpleadoJTable(){
        if(tblCobro_Responsabilidad.getRowCount() > 0){
            for (int k = 0; k < tblCobro_Responsabilidad.getRowCount(); k++) {
                String vProduct = ((String)tblCobro_Responsabilidad.getValueAt(k, 0));
                try {
                    String codObtenidoEmp = DBConv_Responsabilidad.obtenerCodigoTrabajador(txtCodTrabajador.getText().trim());
                    if (codObtenidoEmp.equals(vProduct)) {
                        return false;
                    }
                } catch (SQLException e) {
                    log.error("Error al obtener codigo de Trabajador !!!");
                }
            }
        }
        return true;
    }
    
    private boolean calcularMontoAsumido(){
        if(tblCobro_Responsabilidad.getRowCount() > 0){
            double sumaTotalSupuesta = Double.parseDouble(lblMonto.getText().trim().replace(",", "")) + Double.parseDouble(txtImporteAsumir.getText().trim());
            double montoTotal = Double.parseDouble(txtImporteTotal.getText().trim().replace(",", ""));
            if(sumaTotalSupuesta > montoTotal){
                mensajes = "En monto total asumido supera el monto total a pagar !!!";
                return false;
            }
        }else{
            double sumaTotalSupuesta = Double.parseDouble(txtImporteAsumir.getText().trim());
            double montoTotal = Double.parseDouble(txtImporteTotal.getText().trim().replace(",", ""));
            if(sumaTotalSupuesta > montoTotal){
                mensajes = "En monto que se quiere asumir supera el monto total a pagar !!!";
                return false;
            }
        }
        return true;
    }
    
    private void calcularMontoTotalAsumido(){
        if(tblCobro_Responsabilidad.getRowCount() > 0){
            double precio = 0;
            for (int k = 0; k < tblCobro_Responsabilidad.getRowCount(); k++) {
                String monto = ((String)tblCobro_Responsabilidad.getValueAt(k, 4));
                monto = monto.replace(",", "");
                precio =  precio + Double.parseDouble(monto);
            }
            lblMonto.setText(FarmaUtility.formatNumber(precio));
        }else{
            lblMonto.setText("0.00");
        }
    }

    private void txtProducto_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
            e.consume();
            try {
                if(txtCodTrabajador.getText().trim().length() > 0){
                    if(buscarEmpleadoJTable()){
                        String regEmpleado = DBConv_Responsabilidad.obtenerEmpleado(txtCodTrabajador.getText().trim());
                        if(regEmpleado != null && regEmpleado.length() > 0){
                            String[] datosEmpleado = regEmpleado.split("@");
                            String codTrabajador = datosEmpleado[0];
                            String datTrabajador = datosEmpleado[1];
                            String estTrabajador = datosEmpleado[2];
                            if(estTrabajador.equals("Activo")){
                                txtDescTrabajador.setText(datTrabajador);
                                codigoTrabajador = codTrabajador;
                                datosTrabajador = datTrabajador;
                                estadoTrabajador = estTrabajador;
                                FarmaUtility.moveFocus(txtImporteAsumir);
                            }else{
                                if(JConfirmDialog.rptaConfirmDialog(this,"El trabajador ingresado no está activado. ¿Desea continuar con la operación?")){
                                    txtDescTrabajador.setText(datTrabajador);
                                    codigoTrabajador = codTrabajador;
                                    datosTrabajador = datTrabajador;
                                    estadoTrabajador = estTrabajador;
                                    txtImporteAsumir.setText("0.00");
                                    FarmaUtility.moveFocus(txtImporteAsumir);
                                }else{
                                    limpiarCamposEmpleado();
                                }
                            }
                        }else{
                            FarmaUtility.showMessage(this,"Código de Trabajador NO REGISTRADO. Verifique !!!", txtCodTrabajador);
                            limpiarCamposEmpleado();
                        }
                    }else{
                        FarmaUtility.showMessage(this, "El Trabajador ya ha sido agregado anteriormente !!!", txtCodTrabajador);
                        limpiarCamposEmpleado();
                    }
                }
            } catch (SQLException sql) {
                log.error("", sql);
                FarmaUtility.showMessage(this, "Error al buscar empleado.\n" + sql, txtCodTrabajador);
            }
        } else {
            checkEventDlg(e);
        }

    }
    
    private void añadirRegistroJTable(){
        if(validaDatosIngresados()){
            if(calcularMontoAsumido()){
                ArrayList array = new ArrayList();
                array.add(codigoTrabajador);//1
                array.add(datosTrabajador);//2
                array.add(FarmaLoadCVL.getCVLCode(ConstantsConv_Responsabilidad.NOM_HASTABLE_CMBMOTIVOS_RESPONSABILIDAD,cmbMotivoRegularizacion.getSelectedIndex()));//3
                String codMotivo = FarmaLoadCVL.getCVLCode(ConstantsConv_Responsabilidad.NOM_HASTABLE_CMBMOTIVOS_RESPONSABILIDAD,cmbMotivoRegularizacion.getSelectedIndex());
                array.add(FarmaLoadCVL.getCVLDescription(ConstantsConv_Responsabilidad.NOM_HASTABLE_CMBMOTIVOS_RESPONSABILIDAD,codMotivo));
                double monto = Double.parseDouble(txtImporteAsumir.getText().trim());
                array.add(FarmaUtility.formatNumber(monto));//5
                array.add(estadoTrabajador);//6
                tableModelListaEmpleado_cobro.insertRow(array);
                tableModelListaEmpleado_cobro.fireTableDataChanged();
                FarmaUtility.moveFocus(txtCodTrabajador);
            }
        }else{
            FarmaUtility.moveFocus(txtCodTrabajador);
        }
    }
    
    private void limpiarCamposEmpleado(){
        codigoTrabajador = "";
        datosTrabajador = "";
        estadoTrabajador = "";
        txtImporteAsumir.setText("0.00");
        txtCodTrabajador.setText("");
        txtDescTrabajador.setText("");
    }

    private void btnProducto_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtCodTrabajador);
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        if(!pAceptar){
            limpiarCamposEmpleado();
        }
        this.setVisible(false);
        this.dispose();
    }

    private void cmbMotivoRegularizacion_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
            FarmaUtility.moveFocus(txtCodTrabajador);
        } else {
            checkEventDlg(e);
        }
    }

    private void txtImporteAsumir_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
            FarmaUtility.moveFocus(txtCodTrabajador);
        } else {
            checkEventDlg(e);
        }
    }
    
    private void obtenerMotivoSeleccionado(){
        for(int i = 0; i < tableModelListaEmpleado_cobro.getRowCount(); i++){
            String codMotivo = FarmaLoadCVL.getCVLCode(ConstantsConv_Responsabilidad.NOM_HASTABLE_CMBMOTIVOS_RESPONSABILIDAD,cmbMotivoRegularizacion.getSelectedIndex());
            tableModelListaEmpleado_cobro.setValueAt(codMotivo, i, 2);
        }
    }
    
    private void checkEventDlg(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            añadirRegistroJTable();
            calcularMontoTotalAsumido();
            if(mensajes.length() > 0){
                if(!mensajes.startsWith("Importe")){
                    limpiarCamposEmpleado();
                    FarmaUtility.moveFocus(txtImporteAsumir);
                }
                FarmaUtility.showMessage(this, mensajes, txtCodTrabajador);
            }else{
                limpiarCamposEmpleado();
            }
            mensajes = "";
        } else if(e.getKeyCode() == KeyEvent.VK_F10){
            if(tblCobro_Responsabilidad.getRowCount() > 0){
                double montoAsumido = Double.parseDouble(lblMonto.getText().trim().replace(",", ""));
                double montoTotal = Double.parseDouble(txtImporteTotal.getText().trim().replace(",", ""));
                if(montoAsumido < montoTotal){
                    FarmaUtility.showMessage(this, "Aún no se completa el monto total a pagar !!!", txtCodTrabajador);
                }else if(montoAsumido > montoTotal){
                    FarmaUtility.showMessage(this, "El monto asumido no puede ser mayor al monto total a pagar !!!", txtCodTrabajador);
                }else{
                    obtenerMotivoSeleccionado();
                    ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad = tableModelListaEmpleado_cobro.data;
                    cerrarVentana(true);
                }
            }else{
                FarmaUtility.showMessage(this, "No se han agregado montos asumidos por los Trabajadores !!!", txtCodTrabajador);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void btnRelacion_actionPerformed(ActionEvent e) {
        if(tblCobro_Responsabilidad.getRowCount() > 0){
            FarmaUtility.moveFocus(tblCobro_Responsabilidad);
            FarmaGridUtils.moveRowSelection(tblCobro_Responsabilidad, 0);
        }
    }

    private void btnImporteAsumir_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtImporteAsumir);
    }
    
    private void tblCobro_Responsabilidad_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F4){
            if(tableModelListaEmpleado_cobro.getRowCount() > 0){
                if(tblCobro_Responsabilidad.getSelectedRowCount() > 0){
                    if(JConfirmDialog.rptaConfirmDialog(this,"¿Seguro que desea eliminar el registro seleccionado?")){
                        tableModelListaEmpleado_cobro.deleteRow(tblCobro_Responsabilidad.getSelectedRow());
                        tblCobro_Responsabilidad.repaint();
                        if(tblCobro_Responsabilidad.getSelectedRowCount() > 0){
                            FarmaGridUtils.moveRowSelection(tblCobro_Responsabilidad, 0);
                        }else{
                            FarmaUtility.moveFocus(txtCodTrabajador);
                        }
                    }
                }else{
                    FarmaUtility.showMessage(this,"Debe de seleccionar un registro para eliminar !!!", tblCobro_Responsabilidad);
                }
            }else{
                FarmaUtility.showMessage(this, "No existen registros para ser eliminados !!!", txtCodTrabajador);
            }
            calcularMontoTotalAsumido();
        } else if(e.getKeyCode() == KeyEvent.VK_F10){
            if(tblCobro_Responsabilidad.getRowCount() > 0){
                double montoAsumido = Double.parseDouble(lblMonto.getText().trim().replace(",", ""));
                double montoTotal = Double.parseDouble(txtImporteTotal.getText().trim().replace(",", ""));
                if(montoAsumido < montoTotal){
                    FarmaUtility.showMessage(this, "Aún no se completa el monto total a pagar !!!", txtCodTrabajador);
                }else if(montoAsumido > montoTotal){
                    FarmaUtility.showMessage(this, "El monto asumido no puede ser mayor al monto total a pagar !!!", txtCodTrabajador);
                }else{
                    obtenerMotivoSeleccionado();
                    ConstantsConv_Responsabilidad.listaEmpleados_CobroResponsabilidad = tableModelListaEmpleado_cobro.data;
                    cerrarVentana(true);
                }
            }else{
                FarmaUtility.showMessage(this, "No se han agregado montos asumidos por los Trabajadores !!!", txtCodTrabajador);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void lblMotivoRegularizacion_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbMotivoRegularizacion);
    }

    private void txtImporteAsumir_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtImporteAsumir, e);
    }

    private void txtImporteAsumir_keyReleased(KeyEvent e) {
        FarmaUtility.admitirSoloDecimales(e, txtImporteAsumir, 3, this);
    }
}
