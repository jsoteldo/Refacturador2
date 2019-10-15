package mifarma.ptoventa.recaudacion;


import com.gs.mifarma.componentes.ColumnGroup;
import com.gs.mifarma.componentes.GroupableTableHeader;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JPanelTitle;
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

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;

import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.UtilityLectorTarjeta;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.UtilityRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2017 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : EconoFar.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * ASOSA      24.01.2017   Creación<br>
 * <br> RECAUFINANCIERO
 * @author Alfredo Sosa Dordán<br>
 * @version 1.0<br>
 *
 */

public class DlgRecauTarjetaFinanciero extends JDialog {

    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    static final Logger log = LoggerFactory.getLogger(DlgRecauTarjetaFinanciero.class);

    Frame myParentFrame;

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private FarmaTableModel tblModelTarjetas;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JButton btnDocIdentidad = new JButton();
    private JButton btnMontoPagar = new JButton();
    //private JTextFieldTarjeta txtDocIdentidad = new JTextFieldTarjeta();
    private JTextFieldSanSerif txtDocIdentidad = new JTextFieldSanSerif();
    //private JComboBox<String> cmbMoneda = new JComboBox<String>();
    private JPanel jPanel3 = new JPanel();
    private JLabel lblUsuario = new JLabel();
    private JLabel txtUsuario = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel txtFecha = new JLabel();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JTextFieldSanSerif txtMontoPagar = new JTextFieldSanSerif();
    //ArrayList tmpArrayPres = new ArrayList();
    //ArrayList listInforTarje = new ArrayList();
    private JLabel lbl01 = new JLabel();
    private FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();
    UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    UtilityRecaudacion utilityRecaudacion = new UtilityRecaudacion();
    UtilityLectorTarjeta utilityLectorTarjeta = new UtilityLectorTarjeta();
    private JScrollPane srcTarjetas = new JScrollPane();
    private JTable tblTarjetas = new JTable();
    private JPanelTitle pnlListaTarjetas = new JPanelTitle();
    private JButtonLabel btnListaTarjeta = new JButtonLabel();
    private JLabelOrange lblNombre = new JLabelOrange();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabel lblTitulo = new JLabel();
    private double lim_max_rcd_Soles = 0;
    private double lim_max_rcd_Dolares = 0;
    private boolean realizarValidacion=false;
    private JLabel lblTituloTCambio = new JLabel();
    private JLabelOrange lblTipoCambio = new JLabelOrange();
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgRecauTarjetaFinanciero() {
        this(null, "", false);
    }

    public DlgRecauTarjetaFinanciero(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            utilityRecaudacion.initMensajesVentana(this, txtFecha, txtUsuario, ConstantsRecaudacion.TIPO_REC_RIPLEY);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(663, 396));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Recaudación Banco Financiero");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jPanel1.setLayout(null);
        jPanel1.setForeground(Color.white);
        jPanel1.setBackground(Color.white);
        btnDocIdentidad.setText("DNI: ");
        btnDocIdentidad.setBackground(Color.white);
        btnDocIdentidad.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDocIdentidad.setBorderPainted(false);
        btnDocIdentidad.setContentAreaFilled(false);
        btnDocIdentidad.setDefaultCapable(false);
        btnDocIdentidad.setFocusPainted(false);
        btnDocIdentidad.setFont(new Font("SansSerif", 1, 11));
        btnDocIdentidad.setForeground(new Color(255, 130, 14));
        btnDocIdentidad.setHorizontalAlignment(SwingConstants.LEFT);
        btnDocIdentidad.setMnemonic('D');
        btnDocIdentidad.setRequestFocusEnabled(false);
        btnDocIdentidad.setBounds(new Rectangle(15, 15, 50, 20));
        btnDocIdentidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDocIdentidad_actionPerformed(e);
            }
        });
        btnMontoPagar.setText("Monto a Pagar :");
        btnMontoPagar.setBackground(Color.white);
        btnMontoPagar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnMontoPagar.setBorderPainted(false);
        btnMontoPagar.setContentAreaFilled(false);
        btnMontoPagar.setDefaultCapable(false);
        btnMontoPagar.setFocusPainted(false);
        btnMontoPagar.setFont(new Font("SansSerif", 1, 11));
        btnMontoPagar.setForeground(new Color(255, 130, 14));
        btnMontoPagar.setHorizontalAlignment(SwingConstants.LEFT);
        btnMontoPagar.setMnemonic('P');
        btnMontoPagar.setRequestFocusEnabled(false);
        btnMontoPagar.setBounds(new Rectangle(15, 330, 90, 20));
        btnMontoPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnMontoPagar_actionPerformed(e);
            }
        });
        
        txtDocIdentidad.setBounds(new Rectangle(115, 15, 80, 20));
        txtDocIdentidad.setDocument(new FarmaLengthText(10));
        txtDocIdentidad.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtDocIdentidad_keyPressed(e);
                }
            public void keyTyped(KeyEvent e){
                txtDocIdentidad_keyTyped(e);
            }
        });
        /*cmbMoneda.setBounds(new Rectangle(205, 85, 80, 20));
        cmbMoneda.setPreferredSize(new Dimension(25, 20));
        cmbMoneda.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipoPago_keyPressed(e);
            }
        });
        cmbMoneda.addItem("Soles");*/
        //cmbMoneda.addItem("Dolares");
        jPanel3.setBounds(new Rectangle(10, 5, 635, 70));
        jPanel3.setBackground(new Color(43, 141, 39));
        jPanel3.setForeground(new Color(0, 132, 66));
        jPanel3.setLayout(null);
        lblUsuario.setText("Usuario :");
        lblUsuario.setSize(new Dimension(30, 20));
        lblUsuario.setFont(new Font("Dialog", 1, 11));
        lblUsuario.setBounds(new Rectangle(10, 40, 55, 20));
        lblUsuario.setForeground(Color.white);
        txtUsuario.setBounds(new Rectangle(70, 40, 110, 20));
        txtUsuario.setFont(new Font("Dialog", 1, 11));
        txtUsuario.setForeground(Color.white);
        lblFecha.setText("Fecha :");
        lblFecha.setBounds(new Rectangle(185, 40, 75, 20));
        lblFecha.setForeground(Color.white);
        lblFecha.setFont(new Font("Dialog", 1, 11));
        lblFecha.setSize(new Dimension(75, 20));
        txtFecha.setForeground(Color.white);
        txtFecha.setFont(new Font("Dialog", 1, 11));
        txtFecha.setBounds(new Rectangle(230, 40, 155, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(440, 330, 90, 20));
        lblF2.setText("[ F2 ] Buscar");
        lblF2.setBounds(new Rectangle(25, 390, 90, 20));
        lblF2.setVisible(false);
        
        lblTitulo.setText("RECAUDACION BANCO FINANCIERO DEL PERU");
        lblTitulo.setBounds(new Rectangle(0, 5, 635, 20));
        //lblTitulo.setSize(new Dimension(30, 20));
        lblTitulo.setFont(new Font("Dialog", 1, 14));
        lblTitulo.setForeground(Color.white);

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloTCambio.setText("Tipo de cambio referencial:");
        lblTituloTCambio.setBounds(new Rectangle(380, 15, 165, 20));
        lblTituloTCambio.setBackground(SystemColor.window);
        lblTituloTCambio.setForeground(new Color(255, 130, 14));
        lblTituloTCambio.setFont(new Font("SansSerif", 1, 11));

        lblTipoCambio.setBounds(new Rectangle(550, 15, 34, 20));
        lblTipoCambio.setText("");
        lblTipoCambio.setForeground(SystemColor.windowText);
        lblTipoCambio.setFont(new Font("SansSerif", 3, 11));

        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(340, 220, 90, 20));
        lblEsc.setBounds(new Rectangle(550, 330, 90, 20));
        lbl01.setText("Nombre:");
        lbl01.setBounds(new Rectangle(15, 50, 80, 20));
        lbl01.setBackground(SystemColor.window);
        lbl01.setForeground(new Color(255, 130, 14));
        lbl01.setFont(new Font("SansSerif", 1, 11));
        srcTarjetas.setBounds(new Rectangle(10, 180, 635, 145));
        pnlListaTarjetas.setBounds(new Rectangle(10, 155, 635, 25));
        btnListaTarjeta.setText("Lista de Tarjetas");
        btnListaTarjeta.setBounds(new Rectangle(10, 5, 115, 15));
        btnListaTarjeta.setMnemonic('l');
        btnListaTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnListaTarjeta_actionPerformed(e);
            }
        });
        lblNombre.setText("");
        lblNombre.setBounds(new Rectangle(115, 50, 270, 20));
        lblNombre.setForeground(SystemColor.windowText);
        lblNombre.setFont(new Font("SansSerif", 3, 11));
        txtMontoPagar.setBounds(new Rectangle(115, 330, 80, 20));
        txtMontoPagar.setLengthText(9);
        txtMontoPagar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtMontoPagar_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtMontoPagar_keyTyped(e);
            }
        });
        jPanel2.setBounds(new Rectangle(10, 75, 635, 80));
        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        jPanel2.setBackground(Color.white);
        jPanel2.setLayout(null);
        jPanel2.setForeground(Color.white);
        jPanel3.add(lblTitulo, null);
        jPanel3.add(txtFecha, null);
        jPanel3.add(lblFecha, null);
        jPanel3.add(txtUsuario, null);
        jPanel3.add(lblUsuario, null);
        tblTarjetas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblTarjetas_KeyPressed(e);
            }
        });
        srcTarjetas.getViewport().add(tblTarjetas, null);
        pnlListaTarjetas.add(btnListaTarjeta, null);
        jPanel1.add(pnlListaTarjetas, null);
        jPanel1.add(srcTarjetas, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jPanel3, null);
        jPanel1.add(lblF11, null);
        jPanel1.add(lblF2, null);
        jPanel1.add(lblEsc, null);
        jPanel1.add(txtMontoPagar, null);
        jPanel1.add(btnMontoPagar, null);
        jPanel2.add(lblTipoCambio, null);
        jPanel2.add(lblTituloTCambio, null);
        jPanel2.add(lblNombre, null);
        jPanel2.add(lbl01, null);
        //jPanel2.add(cmbMoneda, null);
        jPanel2.add(btnDocIdentidad, null);
        jPanel2.add(txtDocIdentidad, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */
    private void initialize() {
        initTable();
        
        try{
            ArrayList<ArrayList> param = UtilityRecaudacion.getCantIntentosTiempoEsperaBancoFinanciero();
            VariablesRecaudacion.vNroIntentos = Integer.parseInt(param.get(0).get(0).toString());
            VariablesRecaudacion.vCantSegEspere = Integer.parseInt(param.get(0).get(1).toString());
        }catch(Exception e){
            VariablesRecaudacion.vNroIntentos = 3;
            VariablesRecaudacion.vCantSegEspere = 3;
            log.error("Error parametros: " + e);
        }
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */
    private void initTable(){
        (ConstantsRecaudacion.columnsListaTarjetas[1]).m_width=42;
        (ConstantsRecaudacion.columnsListaTarjetas[2]).m_width=65;
        (ConstantsRecaudacion.columnsListaTarjetas[3]).m_width=65;
        (ConstantsRecaudacion.columnsListaTarjetas[4]).m_width=65;
        (ConstantsRecaudacion.columnsListaTarjetas[5]).m_width=42;
        (ConstantsRecaudacion.columnsListaTarjetas[6]).m_width=65;
        (ConstantsRecaudacion.columnsListaTarjetas[7]).m_width=65;
        (ConstantsRecaudacion.columnsListaTarjetas[8]).m_width=65;
        
        tblModelTarjetas = new FarmaTableModel(ConstantsRecaudacion.columnsListaTarjetas, 
                                               ConstantsRecaudacion.defaultValuesListaTarjetas, 0);
        FarmaUtility.initSimpleList(tblTarjetas, tblModelTarjetas, ConstantsRecaudacion.columnsListaTarjetas);
        
        tblTarjetas.setName("tblTarjetas");
        
        TableColumnModel cm = tblTarjetas.getColumnModel();
        ColumnGroup g_other1 = new ColumnGroup("Moneda Pago");
        ColumnGroup g_other2 = new ColumnGroup("Moneda Origen");
        g_other1.add(cm.getColumn(1));
        g_other1.add(cm.getColumn(2));
        g_other1.add(cm.getColumn(3));
        g_other1.add(cm.getColumn(4));
        g_other2.add(cm.getColumn(5));
        g_other2.add(cm.getColumn(6));
        g_other2.add(cm.getColumn(7));
        g_other2.add(cm.getColumn(8));
        GroupableTableHeader header1 = new GroupableTableHeader(cm);
        header1.addColumnGroup(g_other1);
        header1.addColumnGroup(g_other2);
        tblTarjetas.setTableHeader(header1); 
    }
    
    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */
    private void this_windowOpened(WindowEvent e) {
        lblTituloTCambio.setText("");
        lblTipoCambio.setText("");
        if (VariablesRecaudacion.vTipoCambioVenta == 0) {
            FarmaUtility.showMessage(this,
                                     "ATENCIÓN: No se pudo obtener el tipo de cambio actual\nNo se puede continuar con la Recaudación",
                                     null);
            cerrarVentana(false);
        } else {
            //txtDocIdentidad.setText("45031775");
            FarmaUtility.centrarVentana(this);
            FarmaUtility.moveFocus(txtDocIdentidad);
            lim_max_rcd_Soles = utilityRecaudacion.recupera_limiteRecacudacionSoles();
            lim_max_rcd_Dolares = utilityRecaudacion.recupera_limiteRecacudacionDolares();
            realizarValidacion=utilityRecaudacion.recupera_indicadorRealiaValidacion();
        }
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void btnMontoPagar_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMontoPagar);
    }

    private void txtMontoPagar_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //cmbMoneda.requestFocus();
        } else {
            chkKeyPressed(e);
        }
    }

    private void cmbTipoPago_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtDocIdentidad.getText().trim().equals("")) {
                txtDocIdentidad.requestFocus();
            } else {
                txtMontoPagar.requestFocus();
            }
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtMontoPagar_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMontoPagar, e);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */
    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F2) {
            //buscarSolicitudesPago();
        } else if (e.getKeyCode() == KeyEvent.VK_F11) {
            registarPago();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        VariablesRecaudacion.vNombre_Cliente_BFP="";
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */
    private void buscarSolicitudesPago() {
        if(validarBusqueda()) {
            ArrayList<ArrayList<String>> tmpLista = new ArrayList<>();
            //tmpLista = facadeRecaudacion.solicitaPagos_BancoFinanciero("");
            llenarTabla(tblModelTarjetas, tmpLista);
        }
        
        FarmaUtility.showMessage(this, "Implementar búsqueda de tarjetas", null);
    }
    
    private boolean validarBusqueda() {
        boolean resultado = true;
        
        if(txtDocIdentidad.getText().trim().isEmpty()){
            FarmaUtility.showMessage(this, "Ingrese DNI para realizar una búsqueda", null);
            resultado = false;
        }else if(txtDocIdentidad.getText().trim().length() < 8) {
            FarmaUtility.showMessage(this, "Ingrese DNI válido", null);
            resultado = false;
        }
        
        return resultado;
    }
    
    public void llenarTabla(FarmaTableModel tableModel, ArrayList<ArrayList<String>> tmpArrayLista) {
        if (tmpArrayLista.size() > 0) {
            for (int i = 0; i < tmpArrayLista.size(); i++) {
                String nroTarjeta = "", nroTarjetaReal = "";
                ArrayList<String> tmpArray;
                nroTarjeta = FarmaUtility.getValueFieldArrayList(tmpArrayLista, i, 0);
                nroTarjetaReal = nroTarjeta;
                /*
                if (nroTarjeta != null && !nroTarjeta.trim().equals("")) {
                    nroTarjeta = nroTarjeta.substring(0, 8) + "****" + nroTarjeta.substring(12, 16);
                    tmpArray = tmpArrayLista.get(i);
                    tmpArray.set(0, nroTarjeta); //guardamos el numero de tarjeta enmascarada
                    tmpArray.add(nroTarjetaReal); //guardamos el numero de tarjeta real en la columna real
                    tmpArrayLista.set(i, tmpArray);
                }*/
                
                tableModel.insertRow(tmpArrayLista.get(i));
            }
        }
        
        if(tblModelTarjetas.getRowCount() > 0){
            FarmaUtility.moveFocusJTable(tblTarjetas);
        }else{
            FarmaUtility.moveFocus(txtDocIdentidad);
        }
    }
    
    private void registarPago() {
        String strEstadoCuenta = "";
        String strMonedaPago = "";
        String strMonedaOrigen = "";
        String strNumDNI = txtDocIdentidad.getText().trim();
        double dCuantoPaga = FarmaUtility.getDecimalNumber(txtMontoPagar.getText());
        double dMontoPagarSoles = 0.00;
        double dMontoPagardolares = 0.00;
        double TotalVenta = 0.00;
        boolean tipmoneda = true;
        
        int i = tblTarjetas.getSelectedRow();
        String nroTarjeta = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 0);
        String siglaMoneda_Pago = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 1);
        String sMontoMinimoPagar = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 2);
        String sMontoMesPagar = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 3);
        String sMontoTotalPagar = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 4);
        String siglaMoneda_Origen = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 5);
        String sRedMinimoPagar = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 6);
        String sRedMesPagar = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 7);
        String sRedTotalPagar = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 8);
        double dTipoCambioOrigen = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 9));
        
        String nroTarjetaSinEncriptar = nroTarjeta;//FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 8);
        
        if (validarCamposPago()){
            //if (JConfirmDialog.rptaConfirmDialog(this, "¿Recibió la conformidad del cliente?")) {
                //strMonedaPago = obtieneTipoMoneda(siglaMoneda); //de grilla
                strMonedaPago = obtieneTipoMoneda(siglaMoneda_Pago); //de grilla
                VariablesRecaudacion.vTipoMonedaOrigen=obtieneTipoMoneda(siglaMoneda_Origen); //de grilla
                if(VariablesRecaudacion.vTipoMonedaOrigen.contentEquals(ConstantsCaja.EFECTIVO_DOLARES)){
                    VariablesRecaudacion.vTipoCambioBFP=dTipoCambioOrigen;
                }else{
                    VariablesRecaudacion.vTipoCambioBFP=0.00;
                }
                dMontoPagarSoles = dCuantoPaga;
                TotalVenta = dMontoPagarSoles;
                strEstadoCuenta = ConstantsRecaudacion.EST_CTA_SOLES;
                
                /*if (strMonedaPago.equals("01")) {
                    dMontoPagarSoles = dCuantoPaga;
                    TotalVenta = dMontoPagarSoles;
                    strEstadoCuenta = ConstantsRecaudacion.EST_CTA_SOLES;
                } else if (strMonedaPago.equals("02")) {
                    dMontoPagardolares = dCuantoPaga;
                    TotalVenta = dMontoPagardolares * VariablesRecaudacion.vTipoCambioVenta;
                    strEstadoCuenta = ConstantsRecaudacion.EST_CTA_DOLARES;
                    tipmoneda = false;
                }*/
                
                ArrayList<Object> arrayCabecera = new ArrayList<Object>();
                /*0*/arrayCabecera.add(FarmaVariables.vCodGrupoCia);
                /*1*/arrayCabecera.add(FarmaVariables.vCodCia);
                /*2*/arrayCabecera.add(FarmaVariables.vCodLocal);
                /*3*/arrayCabecera.add(nroTarjetaSinEncriptar); /// 3 
                /*4*/arrayCabecera.add(ConstantsRecaudacion.TIPO_REC_FINANCIERO);
                /*5*/arrayCabecera.add(""); // TIPO PAGO //FarmaLoadCVL.getCVLCode(ConstantsRecaudacion.NOM_HASTABLE_CMB_TIPO_PAGO, cmbTipoPago.getSelectedIndex())
                /*6*/arrayCabecera.add(ConstantsRecaudacion.ESTADO_PENDIENTE); // ESTADO
                /*7*/arrayCabecera.add(strEstadoCuenta); //ESTADO DE CUENTA
                /*8*/arrayCabecera.add(""); //CODIGO DEL CLIENTE
                /*9*/arrayCabecera.add(strMonedaPago); //TIPO DE MONEDA //
                /*10*/arrayCabecera.add(String.valueOf(dCuantoPaga)); //Monto total(moneda original)
                /*11*/arrayCabecera.add(String.valueOf(TotalVenta)); //Monto soles
                /*12*/arrayCabecera.add(String.valueOf(TotalVenta)); //Monto minimo
                /*13*/arrayCabecera.add(dTipoCambioOrigen/*VariablesRecaudacion.vTipoCambioVenta*/); //Tipo cambio
                /*14*/arrayCabecera.add(""); //Fecha DE VENCIMIENTO DE RECAUDACION
                /*15*/arrayCabecera.add(""); //NOMBRE DEL CLIENTE
                /*16*/arrayCabecera.add(""); //Fecha Venc Tarj (CMR)
                /*17*/arrayCabecera.add(ConstantsRecaudacion.FECHA_RCD); //FECHA DE CREACION
                /*18*/arrayCabecera.add(FarmaVariables.vIdUsu); // USUARIO CREADOR
                /*19*/arrayCabecera.add(""); //FECHA DE MODIFICACION
                /*20*/arrayCabecera.add(""); //USUARIO MODIFICADOR
                /*21*/arrayCabecera.add(""); //codigo de autorizacion
                /*22*/arrayCabecera.add(""); //mov de caja
                /*23*/arrayCabecera.add(""); //numero de pedido solo valido para VENTA CMR
                /*24*/arrayCabecera.add(""); //tipo producto servicio
                /*25*/arrayCabecera.add(""); //numero de recibo de pago
                /*26*/arrayCabecera.add(nroTarjeta); //numero de tarjeta
                /*27*/arrayCabecera.add(siglaMoneda_Pago); //sigla de moneda
                /*28*/arrayCabecera.add(sMontoMinimoPagar); //monto minimo a pagar
                /*29*/arrayCabecera.add(sMontoMesPagar); //monto mes a pagar
                /*30*/arrayCabecera.add(sMontoTotalPagar); //monto total a pagar
                
                /*31*/arrayCabecera.add(sRedMinimoPagar); //redondeo minimo a pagar
                /*32*/arrayCabecera.add(sRedMesPagar); //redondeo mes a pagar
                /*33*/arrayCabecera.add(sRedTotalPagar); //redondeo total a pagar
                
                /*34*/arrayCabecera.add(strNumDNI); //numero de DNI // 34
                
                String tmpCodigo = facadeRecaudacion.grabaCabeRecau(arrayCabecera, strNumDNI);

                if (!tmpCodigo.equals("")) {
                    //ABRE PANTALLA DE COBRO
                    if (facadeRecaudacion.cobrarRecaudacion(myParentFrame, tmpCodigo, arrayCabecera, ConstantsRecaudacion.TIPO_REC_FINANCIERO)) {
                        /*DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                        dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_PAGO);
                        dlgProcesarPagoTerceros.procesarPagoFinanciero("TER1", strNumDNI, "T", nroTarjeta,
                                                                       dCuantoPaga, strMonedaPago, dMontoPagoTotal);
                        dlgProcesarPagoTerceros.mostrar();*/
                        
                        cerrarVentana(true);
                    }
                } else {
                    FarmaUtility.showMessage(this, "Error al procesar el cobro.", txtDocIdentidad);
                }
            //}
        }
    }
    
    private boolean validarCamposPago(){
        boolean resultado = true;
        String nroTarjeta = "";
        String marcaTarjeta = "";
        String tipoMonedaOrigen = "";
        String tipCambio_Ref="";
        double dMontoMinimoPagar = 0;
        double dMontoMesPagar = 0;
        double dMontoTotalPagar = 0;
        double dMontoTotalDeuda = 0;
        double dCuantoPaga = 0;
        double tipoCambioOrigen = 0;
        double lim_max_rcd_Cambio = 0;
        
        int i = tblTarjetas.getSelectedRow();
        nroTarjeta = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 0);
        dMontoMinimoPagar = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 2));
        dMontoMesPagar = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 3));
        dMontoTotalPagar = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 4));
        tipoMonedaOrigen = FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 5);
        tipoCambioOrigen = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 9));
        dCuantoPaga = FarmaUtility.getDecimalNumber(txtMontoPagar.getText());
        marcaTarjeta = utilityRecaudacion.obtieneMarcaTarjetaCreditoFinanciero(nroTarjeta);
        if(tipoCambioOrigen==0){
            dMontoTotalDeuda=FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 8));
            tipoCambioOrigen=dMontoTotalPagar/dMontoTotalDeuda;
            tipCambio_Ref="\n(Tipo de cambio referencial de MiFarma)";
        }
        if(i >= 0){
            if (txtMontoPagar.getText().trim().isEmpty()) {
                FarmaUtility.showMessage(this, "Ingrese Monto a Pagar válido", txtMontoPagar);
                resultado = false;
            }else{
                if(realizarValidacion){
                    if(dCuantoPaga==0){
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser mayor cero", txtMontoPagar);
                        resultado = false;
                    }
                    
                    if(obtieneTipoMoneda(tipoMonedaOrigen).equals(ConstantsCaja.EFECTIVO_SOLES)){
                        if(dCuantoPaga > lim_max_rcd_Soles) {
                            FarmaUtility.showMessage(this, "Monto a Pagar excede al máximo permitido para este canal: "
                                                           +ConstantsRecaudacion.SIMBOLO_SOLES+" "+lim_max_rcd_Soles+" soles", txtMontoPagar);
                            resultado = false;
                        }
                    }else{
                        lim_max_rcd_Cambio = lim_max_rcd_Dolares * tipoCambioOrigen;
                        if(dCuantoPaga > lim_max_rcd_Cambio) {
                            FarmaUtility.showMessage(this, "Monto a Pagar excede al máximo permitido para este canal:\n"
                                                           +ConstantsRecaudacion.SIMBOLO_SOLES+" "+lim_max_rcd_Cambio+" soles " +
                                                           "o su equivalente en moneda extranjera a "+
                                                           ConstantsRecaudacion.SIMBOLO_DOLARES+" "+lim_max_rcd_Dolares, txtMontoPagar+
                                                                                                                         tipCambio_Ref);
                            resultado = false;
                        }
                    }
                }
            }
        }else{
            FarmaUtility.showMessage(this, "Debe realizar una búsqueda para proceder con el pago", txtDocIdentidad);
            resultado = false;
        }
        /*
        if(i >= 0){
            if (txtMontoPagar.getText().trim().isEmpty()) {
                FarmaUtility.showMessage(this, "Ingrese Monto a Pagar válido", txtMontoPagar);
                resultado = false;
            }else{
                if(marcaTarjeta.equals("DINERS")){
                    if(dCuantoPaga==0){
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser mayor cero", txtMontoPagar);
                        resultado = false;
                    }else{
                        if(dCuantoPaga>dMontoMesPagar){
                            FarmaUtility.showMessage(this, "Monto a Pagar debe ser menor o igual al Pago Mes", txtMontoPagar);
                            resultado = false;
                        }
                    }
                }else{
                    //if(dCuantoPaga < dMontoMinimoPagar) {
                    if(dCuantoPaga==0){
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser mayor cer0", txtMontoPagar);
                        resultado = false;
                    }else if(dCuantoPaga > dMontoTotalPagar) {
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser menor o igual al Pago Total", txtMontoPagar);
                        resultado = false;
                    }
                }
                
                if(dCuantoPaga > 3500) {
                    FarmaUtility.showMessage(this, "Monto a Pagar excede al máximo permitido (S/ 3500.00 soles)", txtMontoPagar);
                    resultado = false;
                }
            }
        }else{
            FarmaUtility.showMessage(this, "Debe realizar una búsqueda para proceder con el pago", txtDocIdentidad);
            resultado = false;
        }
        */
        return resultado;
    }
    
    private void txtDocIdentidad_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            VariablesRecaudacion.vNombre_Cliente_BFP="";
            lblNombre.setText("");
            txtMontoPagar.setText("");
            if(validarBusqueda()) {
                boolean isConsultaOk = true;
                ArrayList<ArrayList<String>> tmpLista = new ArrayList<>();
                
                DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_CONSU_FINANCIERO);
                dlgProcesarPagoTerceros.procesarConsultaFinanciero("0"+FarmaVariables.vCodLocal, txtDocIdentidad.getText().trim(), "T");
                dlgProcesarPagoTerceros.mostrar();
                //rptSix = dlgProcesarPagoTerceros.getRptSix(); NO SABEMOS COMO SERA ACA
                
                isConsultaOk = dlgProcesarPagoTerceros.isVProcesoConsulta();
                
                if(isConsultaOk){
                    tblModelTarjetas.clearTable();
                    tmpLista = dlgProcesarPagoTerceros.getTmpLista();
                    if(tmpLista.size() == 1){
                        String col1 = FarmaUtility.getValueFieldArrayList(tmpLista, 0, 0);
                        if(col1.length() == 3){
                            FarmaUtility.showMessage(this, "No se encontraron resultados para el DNI ingresado", null);
                        }else{
                            lblNombre.setText(VariablesRecaudacion.vNombre_Cliente_BFP);
                            llenarTabla(tblModelTarjetas, tmpLista);
                        }
                    }else if(tmpLista.size() > 1){
                        lblNombre.setText(VariablesRecaudacion.vNombre_Cliente_BFP);
                        llenarTabla(tblModelTarjetas, tmpLista);
                    }
                    setFrame_tipoCambio();
                }else{
                    tblModelTarjetas.clearTable();
                    //FarmaUtility.showMessage(this, "No se encontraron resultados para el DNI ingresado", txtDocIdentidad);
                    if(VariablesRecaudacion.vMensajeError!=null && !VariablesRecaudacion.vMensajeError.trim().equalsIgnoreCase("")){
                        //FarmaUtility.showMessage(this, VariablesRecaudacion.vMensajeError, txtDocIdentidad);
                        FarmaUtility.showMessage(this, "[Mensaje Operador]" + " :\n" +
                                VariablesRecaudacion.vMensajeError, txtDocIdentidad);
                        VariablesRecaudacion.vMensajeError="";
                    }
                }
            }
        } else{
            chkKeyPressed(e);
        }
    }
    
    private void txtDocIdentidad_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtDocIdentidad, e);
    }

    private void btnDocIdentidad_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtDocIdentidad);
    }
    
    private void btnListaTarjeta_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocusJTable(tblTarjetas);
        txtMontoPagar.setText("");
    }
    
    private String obtieneTipoMoneda(String siglaMoneda){
        String resultado = "";
        
        if(siglaMoneda.equals(ConstantsRecaudacion.SIMBOLO_SOLES) || siglaMoneda.equals("S/.")){
            resultado = ConstantsCaja.EFECTIVO_SOLES;
        }else if(siglaMoneda.equals("US$")){
            resultado = ConstantsCaja.EFECTIVO_DOLARES;
        }
        
        return resultado;
    }
    
    private void tblTarjetas_KeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
            e.consume();
            txtMontoPagar.setText("");
            FarmaUtility.moveFocus(txtMontoPagar);
            break;
        }
    }

    private void setFrame_tipoCambio() {
        for(int i=0;i<tblModelTarjetas.getRowCount();i++){
            double tipoCambioOrigen = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 9));
            if(tipoCambioOrigen!=0){
                lblTituloTCambio.setText("Tipo de Cambio Referencial");
                lblTipoCambio.setText(ConstantsRecaudacion.SIMBOLO_DOLARES+" "+tipoCambioOrigen);
                break;
            }
        }
    }
}
