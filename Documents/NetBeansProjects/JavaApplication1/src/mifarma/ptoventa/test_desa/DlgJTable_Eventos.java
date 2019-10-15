
package mifarma.ptoventa.test_desa;


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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.UtilityLectorTarjeta;
import mifarma.ptoventa.recaudacion.DlgRecaudacionFinanciero;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.UtilityRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgJTable_Eventos extends JDialog {
    static final Logger log = LoggerFactory.getLogger(DlgRecaudacionFinanciero.class);

    Frame myParentFrame;

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private FarmaTableModel tblModelTarjetas;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JButton btnDocIdentidad = new JButton();
    private JButton btnMontoPagar = new JButton();
    private JTextFieldSanSerif txtDocIdentidad = new JTextFieldSanSerif();
    private JPanel jPanel3 = new JPanel();
    private JLabel lblUsuario = new JLabel();
    private JLabel txtUsuario = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel txtFecha = new JLabel();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();


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
    private JTextField txtMontoPagar = new JTextField();

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgJTable_Eventos() {
        this(null, "", false);
    }
    
    public DlgJTable_Eventos(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(560, 396));
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
        jPanel3.setBounds(new Rectangle(10, 5, 530, 70));
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
        lblF11.setBounds(new Rectangle(340, 330, 90, 20));
        lblF2.setText("[ F2 ] Buscar");
        lblF2.setBounds(new Rectangle(25, 390, 90, 20));
        lblF2.setVisible(false);
        
        lblTitulo.setText("RECAUDACION BANCO FINANCIERO DEL PERU");
        lblTitulo.setBounds(new Rectangle(0, 5, 525, 20));
        //lblTitulo.setSize(new Dimension(30, 20));
        lblTitulo.setFont(new Font("Dialog", 1, 14));
        lblTitulo.setForeground(Color.white);

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloTCambio.setText("Tipo de cambio referencial:");
        lblTituloTCambio.setBounds(new Rectangle(280, 15, 165, 20));
        lblTituloTCambio.setBackground(SystemColor.window);
        lblTituloTCambio.setForeground(new Color(255, 130, 14));
        lblTituloTCambio.setFont(new Font("SansSerif", 1, 11));

        lblTipoCambio.setBounds(new Rectangle(450, 15, 34, 20));
        lblTipoCambio.setText("");
        lblTipoCambio.setForeground(SystemColor.windowText);
        lblTipoCambio.setFont(new Font("SansSerif", 3, 11));

        txtMontoPagar.setBounds(new Rectangle(110, 330, 100, 20));
        txtMontoPagar.setFont(new Font("Tahoma", 1, 11));
        txtMontoPagar.setDisabledTextColor(new Color(231, 0, 0));
        txtMontoPagar.setForeground(Color.red);
        txtMontoPagar.setEditable(false);
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(340, 220, 90, 20));
        lblEsc.setBounds(new Rectangle(450, 330, 90, 20));
        lbl01.setText("Nombre:");
        lbl01.setBounds(new Rectangle(15, 50, 80, 20));
        lbl01.setBackground(SystemColor.window);
        lbl01.setForeground(new Color(255, 130, 14));
        lbl01.setFont(new Font("SansSerif", 1, 11));
        srcTarjetas.setBounds(new Rectangle(10, 180, 530, 145));
        pnlListaTarjetas.setBounds(new Rectangle(10, 155, 530, 25));
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

        /*
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
        */
        jPanel2.setBounds(new Rectangle(10, 75, 530, 80));
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
        tblTarjetas.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (!tblTarjetas.isEditing() &&
                    tblTarjetas.editCellAt(tblTarjetas.getSelectedRow(), tblTarjetas.getSelectedColumn())) {
                    tblTarjetas.getEditorComponent().requestFocusInWindow(); // obligamos que la celda reciba el foco
                }
            }
        });
        srcTarjetas.getViewport().add(tblTarjetas, null);
        pnlListaTarjetas.add(btnListaTarjeta, null);
        jPanel1.add(txtMontoPagar, null);
        jPanel1.add(pnlListaTarjetas, null);
        jPanel1.add(srcTarjetas, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jPanel3, null);
        jPanel1.add(lblF11, null);
        jPanel1.add(lblF2, null);
        jPanel1.add(lblEsc, null);
        jPanel1.add(btnMontoPagar, null);
        jPanel2.add(lblTipoCambio, null);
        jPanel2.add(lblTituloTCambio, null);
        jPanel2.add(lblNombre, null);
        jPanel2.add(lbl01, null);
        jPanel2.add(btnDocIdentidad, null);
        jPanel2.add(txtDocIdentidad, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */
    private void initialize() {
        initTable();
        //modifcaTabla();
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
        tblModelTarjetas = new FarmaTableModel(ConstantsRecaudacion.columnsPrueba, 
                                               ConstantsRecaudacion.defaultDatePrueba, 0);
        FarmaUtility.initSimpleList(tblTarjetas, tblModelTarjetas, ConstantsRecaudacion.columnsPrueba);
        tblTarjetas.setName("tblTarjetas");
        llenarTabla(tblModelTarjetas, dataPrueba());
    }
    
    private void modifcaTabla() {
        tblTarjetas.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (!tblTarjetas.isEditing() &&
                    tblTarjetas.editCellAt(tblTarjetas.getSelectedRow(), tblTarjetas.getSelectedColumn())) {
                    tblTarjetas.getEditorComponent().requestFocusInWindow(); // obligamos que la celda reciba el foco
                }
            }
        });

        srcTarjetas.setViewportView(tblTarjetas);

        tblTarjetas.setVisible(true);
        srcTarjetas.setVisible(true);
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
        if (e.getKeyCode() == KeyEvent.VK_F11) {
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
    /*
    private void buscarSolicitudesPago() {
        if(validarBusqueda()) {
            ArrayList<ArrayList<String>> tmpLista = new ArrayList<>();
            //tmpLista = facadeRecaudacion.solicitaPagos_BancoFinanciero("");
            llenarTabla(tblModelTarjetas, tmpLista);
        }
        
        FarmaUtility.showMessage(this, "Implementar búsqueda de tarjetas", null);
    }*/
    
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
                tableModel.insertRow(tmpArrayLista.get(i));
            }
        }
        
        if(tblModelTarjetas.getRowCount() > 0){
            FarmaUtility.moveFocusJTable(tblTarjetas);
            int i = tblTarjetas.getSelectedRow();
            String monto= FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, nroCelda);
            txtMontoPagar.setText(ConstantsRecaudacion.SIMBOLO_SOLES+" "+monto);
        }else{
            FarmaUtility.moveFocus(txtDocIdentidad);
        }
    }
    
    private void registarPago() {
        String strEstadoCuenta = "";
        String strMonedaPago = "";
        String strNumDNI = txtDocIdentidad.getText().trim();
        String montoPagar=txtMontoPagar.getText().substring(2, 3);
        if(montoPagar.equalsIgnoreCase(".")){
            montoPagar=txtMontoPagar.getText().substring(3).trim();
        }else{
            montoPagar=txtMontoPagar.getText().substring(2).trim();
        }
        double dCuantoPaga = FarmaUtility.getDecimalNumber(montoPagar);
        double dMontoPagarSoles = 0.00;
        double TotalVenta = 0.00;
        
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
                /*3*/arrayCabecera.add(nroTarjeta); /// 3 
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
                
                String tmpCodigo = "OK";//facadeRecaudacion.grabaCabeRecau(arrayCabecera, strNumDNI);

                if (!tmpCodigo.equals("")) {
                    //ABRE PANTALLA DE COBRO
                    if (facadeRecaudacion.cobrarRecaudacion(myParentFrame, tmpCodigo, arrayCabecera, ConstantsRecaudacion.TIPO_REC_FINANCIERO)) {
                        /*DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                        dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_PAGO);
                        dlgProcesarPagoTerceros.procesarPagoFinanciero("TER1", strNumDNI, "T", nroTarjeta,
                                                                       dCuantoPaga, strMonedaPago, dMontoPagoTotal);
                        dlgProcesarPagoTerceros.mostrar();*/
                        
                        //cerrarVentana(true);
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
        String montoPagar=txtMontoPagar.getText().substring(2, 3);
        if(montoPagar.equalsIgnoreCase(".")){
            montoPagar=txtMontoPagar.getText().substring(3).trim();
        }else{
            montoPagar=txtMontoPagar.getText().substring(2).trim();
        }
        dCuantoPaga = FarmaUtility.getDecimalNumber(montoPagar);
        marcaTarjeta = utilityRecaudacion.obtieneMarcaTarjetaCreditoFinanciero(nroTarjeta);
        if(tipoCambioOrigen==0){
            dMontoTotalDeuda=FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, 8));
            tipoCambioOrigen=dMontoTotalPagar/dMontoTotalDeuda;
            tipCambio_Ref="\n(Tipo de cambio referencial de MiFarma)";
        }
        
        if(i >= 0){
            if (montoPagar.isEmpty()) {
                FarmaUtility.showMessage(this, "Ingrese Monto a Pagar válido", null);
                resultado = false;
            }else{
                if(realizarValidacion){
                    if(dCuantoPaga==0){
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser mayor cero "+montoPagar, null);
                        resultado = false;
                    }
                    
                    if(obtieneTipoMoneda(tipoMonedaOrigen).equals(ConstantsCaja.EFECTIVO_SOLES)){
                        if(dCuantoPaga > lim_max_rcd_Soles) {
                            FarmaUtility.showMessage(this, "Monto a Pagar excede al máximo permitido para este canal: "
                                                           +ConstantsRecaudacion.SIMBOLO_SOLES+" "+lim_max_rcd_Soles+" soles", null);
                            resultado = false;
                        }
                    }else{
                        lim_max_rcd_Cambio = lim_max_rcd_Dolares * tipoCambioOrigen;
                        if(dCuantoPaga > lim_max_rcd_Cambio) {
                            FarmaUtility.showMessage(this, "Monto a Pagar excede al máximo permitido para este canal:\n"
                                                           +ConstantsRecaudacion.SIMBOLO_SOLES+" "+lim_max_rcd_Cambio+" soles " +
                                                           "o su equivalente en moneda extranjera a "+
                                                           ConstantsRecaudacion.SIMBOLO_DOLARES+" "+lim_max_rcd_Dolares,null);
                            resultado = false;
                        }
                    }
                }
            }
        }else{
            FarmaUtility.showMessage(this, "Debe realizar una búsqueda para proceder con el pago", txtDocIdentidad);
            resultado = false;
        }
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
                /*
                DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_CONSU_FINANCIERO);
                dlgProcesarPagoTerceros.procesarConsultaFinanciero("0"+FarmaVariables.vCodLocal, txtDocIdentidad.getText().trim(), "T");
                dlgProcesarPagoTerceros.mostrar();
                */
                isConsultaOk = true;//dlgProcesarPagoTerceros.isVProcesoConsulta();
                
                if(isConsultaOk){
                    tblModelTarjetas.clearTable();
                    //tmpLista = dlgProcesarPagoTerceros.getTmpLista();
                    tmpLista = dataPrueba();
                    
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
    
    int nroCelda=2; //--> se mueve solo entre 2,3,4,(5 otro monto)
    private void tblTarjetas_KeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
            e.consume();
            FarmaUtility.moveFocus(txtDocIdentidad);
            break;
        case KeyEvent.VK_UP:
            seleccionaFila(-1);
            break;
        case KeyEvent.VK_DOWN:
            seleccionaFila(1);
            break;
        case KeyEvent.VK_RIGHT:
            switch (nroCelda){
            case 1: nroCelda++;break;
            case 2: nroCelda++;break;
            case 3: nroCelda++;break;                
            case 4: nroCelda=10;break;                
            default: nroCelda=2;break;
            }
            seleccionaCelda(nroCelda);
            break;
        case KeyEvent.VK_LEFT:
            switch (nroCelda){
            case 2: nroCelda=10;break;
            case 3: nroCelda--;break;
            case 4: nroCelda--;break;
            default: nroCelda=4;break;
            }
            seleccionaCelda(nroCelda);
            break;
        default:
            chkKeyPressed(e);
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

    private ArrayList<ArrayList<String>> dataPrueba() {
        ArrayList<ArrayList<String>> tmpLista = new ArrayList();
        ArrayList lista1 = new ArrayList();
        ArrayList lista2 = new ArrayList();
        ArrayList lista3 = new ArrayList();
        
        lista1.add("514940******8121");
        lista1.add("814.62");
        lista1.add("1000.00");
        lista1.add("OTRO");
        
        lista2.add("514940******8121");
        lista2.add("79.46");
        lista2.add("1495.29");
        lista2.add("OTRO");
        
        lista3.add("432343******0118");
        lista3.add("197.15");
        lista3.add("1822.73");
        lista3.add("OTRO");
        
        tmpLista.add(lista1);
        tmpLista.add(lista2);
        tmpLista.add(lista3);
        return tmpLista;
    }

    private void seleccionaFila(int position) {
        int i = tblTarjetas.getSelectedRow();
        i=i+position;
        if(i<0){i=0;}
        if(i==tblTarjetas.getRowCount()){i--;}
        String monto= FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, nroCelda);
        txtMontoPagar.setText(ConstantsRecaudacion.SIMBOLO_SOLES+" "+monto);
    }

    private void seleccionaCelda(int nroCelda) {
        int i = tblTarjetas.getSelectedRow();
        String monto= FarmaUtility.getValueFieldArrayList(tblModelTarjetas.data, i, nroCelda);
        txtMontoPagar.setText(ConstantsRecaudacion.SIMBOLO_SOLES+" "+monto);
    }
    }
