package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JLabelFunction;

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

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.recaudacion.DlgProcesarPagoTerceros;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCobroFinanciero extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgCobroFinanciero.class);
    public Frame myParentFrame;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JPanel pnlDetalles = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel1 = new JPanel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabel lblTotalPagar = new JLabel();
    private JLabel lblSolesTotalPagar = new JLabel();
    private JLabel lblDolarSimbolo = new JLabel();
    private JLabel lblDolarTotalPagar = new JLabel();
    private JTextField txtMontoPagado = new JTextField();
    private JTextField txtMontoPagadoDolares = new JTextField();
    private JTextField txtMontoVuelto = new JTextField();
    private JButton btnMonto = new JButton();
    private JButton btnMontoDolares = new JButton();
    private JButton btnMontoVuelto = new JButton();

    FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();
    private String strCodRecau = "";
    private int intTipoCobro = 3;
    private ArrayList<Object> tmpArrayCabRcd = new ArrayList<Object>();
    private FarmaTableModel tableModelDetallePago;
    public JTable tblDetallePago = new JTable();
    /*
    private String strMonedaPagar = ConstantsRecaudacion.EST_CTA_SOLES;

    private int intTipoCobro = 1;
    private boolean indPedirLogueo = true;

    private String tipoRcd = "";
    */

    public DlgCobroFinanciero() {
        this(null, "", false);
    }

    public DlgCobroFinanciero(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public DlgCobroFinanciero(Frame parent, String title, boolean modal, boolean isRecaudacionBFP) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            initVariable();
            initJTable();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(458, 273));
        this.getContentPane().setLayout(borderLayout1);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("Recaudacion "+VariablesRecaudacion.RAZON_SOCIAL_BFP);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        jContentPane.setSize(new Dimension(554, 504));

        pnlDetalles.setBounds(new Rectangle(5, 5, 435, 235));
        pnlDetalles.setFont(new Font("SansSerif", 0, 11));
        //pnlDetalles.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlDetalles.setBackground(Color.white);
        pnlDetalles.setLayout(null);
        //pnlDetalles.setLayout(xYLayout2);

        jPanel3.setBackground(new Color(255, 130, 14));
        jPanel3.setBorder(BorderFactory.createTitledBorder(""));
        jPanel3.setBounds(new Rectangle(0, 0, 435, 40));
        jPanel3.setLayout(null);

        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setBackground(Color.white);
        jPanel1.setBounds(new Rectangle(0, 50, 435, 120));
        jPanel1.setLayout(null);

        lblEsc.setText("<HTML><CENTER>[ESC]<BR>Cerrar</CENTER></HTML>");
        lblEsc.setBounds(new Rectangle(285, 185, 135, 35));
        lblF11.setText("<HTML><CENTER>[F11]<BR>Aceptar</HTML></CENTER>");
        lblF11.setBounds(new Rectangle(0, 185, 135, 35));

        lblTotalPagar.setText("TOTAL A PAGAR : " + ConstantesUtil.simboloSoles);
        lblTotalPagar.setFont(new Font("SansSerif", 1, 13));
        lblTotalPagar.setForeground(Color.white);
        lblTotalPagar.setBounds(new Rectangle(10, 10, 230, 20));

        lblTotalPagar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolesTotalPagar.setText("0.00");
        lblSolesTotalPagar.setFont(new Font("SansSerif", 1, 13));
        lblSolesTotalPagar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolesTotalPagar.setForeground(Color.white);
        lblSolesTotalPagar.setBounds(new Rectangle(240, 10, 65, 20));

        if (VariablesRecaudacion.vTipoCambioBFP > 0) {
            lblDolarSimbolo.setText("US$");
            lblDolarSimbolo.setFont(new Font("SansSerif", 1, 13));
            lblDolarSimbolo.setForeground(Color.white);
            lblDolarSimbolo.setBounds(new Rectangle(310, 10, 40, 20));
            lblDolarSimbolo.setHorizontalAlignment(SwingConstants.RIGHT);
            lblDolarTotalPagar.setText("0.00");
            lblDolarTotalPagar.setFont(new Font("SansSerif", 1, 13));
            lblDolarTotalPagar.setHorizontalAlignment(SwingConstants.RIGHT);
            lblDolarTotalPagar.setForeground(Color.white);
            lblDolarTotalPagar.setBounds(new Rectangle(350, 10, 65, 20));
        }

        txtMontoPagado.setBounds(new Rectangle(295, 10, 115, 25));
        txtMontoPagado.setText("0.00");
        txtMontoPagado.setFont(new Font("Tahoma", 1, 13));
        txtMontoPagado.setHorizontalAlignment(JTextField.RIGHT);
        txtMontoPagado.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtMontoPagado_focusGained(e);
            }
        });
        txtMontoPagado.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtMontoPagado_KeyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtMontoPagado_KeyTyped(e);
            }
        });
//        if (VariablesRecaudacion.vTipoCambioBFP > 0) {
            txtMontoPagadoDolares.setBounds(new Rectangle(295, 45, 115, 25));
            txtMontoPagadoDolares.setText("0.00");
            txtMontoPagadoDolares.setFont(new Font("Tahoma", 1, 13));
            txtMontoPagadoDolares.setHorizontalAlignment(JTextField.RIGHT);
            txtMontoPagadoDolares.setEditable(false);
//        }
        txtMontoVuelto.setBounds(new Rectangle(295, 80, 115, 25));
        txtMontoVuelto.setText("0.00");
        txtMontoVuelto.setHorizontalAlignment(JTextField.RIGHT);
        txtMontoVuelto.setFont(new Font("Tahoma", 1, 13));
        txtMontoVuelto.setEditable(false);

        btnMonto.setText("El Cliente pago con " + ConstantsRecaudacion.SIMBOLO_SOLES);
        btnMonto.setBounds(new Rectangle(30, 15, 255, 15));
        btnMonto.setBorderPainted(false);
        btnMonto.setContentAreaFilled(false);
        btnMonto.setDefaultCapable(false);
        btnMonto.setFocusPainted(false);
        btnMonto.setHorizontalAlignment(SwingConstants.RIGHT);
        btnMonto.setMnemonic('s');
        btnMonto.setRequestFocusEnabled(false);
        btnMonto.setFont(new Font("SansSerif", 1, 14));
        btnMonto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnMonto.setActionCommand("Soles : ");
        btnMonto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //                btnMonto_actionPerformed(e);
                btnMonto_ActionPerformed(e);
            }
        });
        btnMonto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnMonto_KeyPressed(e);
            }
        });
//        if (VariablesRecaudacion.vTipoCambioBFP > 0) {
            btnMontoDolares.setBounds(new Rectangle(55, 50, 230, 15));
            btnMontoDolares.setText(ConstantsRecaudacion.SIMBOLO_DOLARES);
            btnMontoDolares.setBorderPainted(false);
            btnMontoDolares.setContentAreaFilled(false);
            btnMontoDolares.setDefaultCapable(false);
            btnMontoDolares.setFocusPainted(false);
            btnMontoDolares.setHorizontalAlignment(SwingConstants.RIGHT);
            btnMontoDolares.setMnemonic('d');
            btnMontoDolares.setRequestFocusEnabled(false);
            btnMontoDolares.setFont(new Font("SansSerif", 1, 14));
            btnMontoDolares.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            btnMontoDolares.setActionCommand("Soles : ");
            btnMontoDolares.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //                btnMontoDolares_actionPerformed(e);
                }
            });
//        }

        btnMontoVuelto.setBounds(new Rectangle(60, 85, 225, 15));
        btnMontoVuelto.setText("Vuelto " + ConstantsRecaudacion.SIMBOLO_SOLES);
        btnMontoVuelto.setBorderPainted(false);
        btnMontoVuelto.setContentAreaFilled(false);
        btnMontoVuelto.setDefaultCapable(false);
        btnMontoVuelto.setFocusPainted(false);
        btnMontoVuelto.setHorizontalAlignment(SwingConstants.RIGHT);
        btnMontoVuelto.setMnemonic('V');
        btnMontoVuelto.setRequestFocusEnabled(false);
        btnMontoVuelto.setFont(new Font("SansSerif", 1, 14));
        btnMontoVuelto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnMontoVuelto.setActionCommand("Soles : ");
        btnMontoVuelto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //                btnMontoTarjeta_actionPerformed(e);
            }
        });
        
        if (VariablesRecaudacion.vTipoCambioBFP > 0) {
            jPanel3.add(lblDolarTotalPagar, null);
            jPanel3.add(lblDolarSimbolo, null);
            jPanel1.add(btnMontoDolares, null);
            jPanel1.add(txtMontoPagadoDolares, null);
        }
        
        jPanel3.add(lblSolesTotalPagar, null);
        jPanel3.add(lblTotalPagar, null);
        pnlDetalles.add(lblEsc, null);
        pnlDetalles.add(lblF11, null);
        jPanel1.add(btnMontoVuelto, null);
        jPanel1.add(txtMontoVuelto, null);
        jPanel1.add(btnMonto, null);
        jPanel1.add(txtMontoPagado, null);
        pnlDetalles.add(jPanel1, null);
        pnlDetalles.add(jPanel3, null);
        jContentPane.add(pnlDetalles, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    private void initialize() {
        FarmaVariables.vAceptar = false;
        DlgProcesar.getIndGestorTx(true);
    }
    
    private void initJTable() {
        tableModelDetallePago =
                new FarmaTableModel(ConstantsCaja.columsListaDetallePago, ConstantsCaja.defaultListaDetallePago, 0);
        //FarmaUtility.initSimpleList(tblDetallePago, tableModelDetallePago, ConstantsCaja.columsListaDetallePago);
    }
    
    private double TotalVenta = 0.0;
    private void initVariable() {
        tmpArrayCabRcd = VariablesRecaudacion.arrayCabecera;
        TotalVenta = Double.parseDouble(VariablesRecaudacion.vMontoPago);
        lblSolesTotalPagar.setText(FarmaUtility.formatNumber(TotalVenta));
        if (VariablesRecaudacion.vTipoCambioBFP > 0) {
            double vtaTotal = TotalVenta / VariablesRecaudacion.vTipoCambioBFP;
            lblDolarTotalPagar.setText(FarmaUtility.formatNumber(vtaTotal));
        } else {
            double vtaTotal = TotalVenta / FarmaVariables.vTipCambio;
            lblDolarTotalPagar.setText(FarmaUtility.formatNumber(vtaTotal));
        }
    }

    public void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        strCodRecau = VariablesRecaudacion.vCodCabRecau;
        FarmaUtility.moveFocus(txtMontoPagado);
        initVariablesRecaudacion();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            boolean ok=validarMontoPago();
            if(ok)
                realizarRecaudacionFinanciero();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana();
        }
    }

    private void txtMontoPagado_focusGained(FocusEvent e) {
        String monto = txtMontoPagado.getText().trim();
        if (!monto.equalsIgnoreCase("")) {
            double dCuantoPaga = FarmaUtility.getDecimalNumber(monto);
            if (dCuantoPaga == 0) {
                txtMontoPagado.setText("");
            }
        } else {
            txtMontoPagado.setText("");
        }
    }

    private void txtMontoPagado_KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            boolean ok=validarMontoPago();
            if(ok)
                FarmaUtility.moveFocus(btnMonto);            
        } else {
            chkKeyPressed(e);
        }
    }

    private boolean validarMontoPago() {
        boolean retorno=false;
        String monto = txtMontoPagado.getText().trim();
        if (!monto.equalsIgnoreCase("")) {
            double TotalPago = FarmaUtility.getDecimalNumber(txtMontoPagado.getText().trim());
            if ((TotalPago - TotalVenta) >= 0.00) {
                txtMontoVuelto.setText(FarmaUtility.formatNumber(TotalPago - TotalVenta));
                if (VariablesRecaudacion.vTipoCambioBFP > 0) {
                    double pagoDolar = TotalPago / VariablesRecaudacion.vTipoCambioBFP;
                    txtMontoPagadoDolares.setText(FarmaUtility.formatNumber(pagoDolar));
                }
                txtMontoPagado.setText(FarmaUtility.formatNumber(TotalPago));
                retorno = true;
            } else {
                txtMontoVuelto.setText(FarmaUtility.formatNumber(0.00));
                FarmaUtility.showMessage(this, "El monto de pago es inferior a la cuota\nIngrese un nuevo monto",
                                         txtMontoPagado);
            }
        } else {
            txtMontoVuelto.setText(FarmaUtility.formatNumber(0.00));
            FarmaUtility.showMessage(this, "Ingrese un monto para realizar el pago", txtMontoPagado);
        }
        return retorno;
    }

    private void txtMontoPagado_KeyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMontoPagado, e);
    }

    private void initVariablesRecaudacion() {
        if (!UtilityCaja.existeCajaUsuarioImpresora(this, null) ||
            !UtilityCaja.validaFechaMovimientoCaja(this, txtMontoPagado)) {
            FarmaUtility.showMessage(this, "La Recaudacion sera Anulado. Vuelva a generar uno nuevo.", null);
            //facadeRecaudacion.anularRCDPend(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, strCodRecau, null);
            cerrarVentana();
            return;
        }

        buscaPedidoDiario();
        VariablesCaja.vNumPedPendiente = "";
        FarmaVariables.vAceptar = false;
        FarmaUtility.moveFocus(txtMontoPagado);
    }

    private void buscaPedidoDiario() {
        UtilityCaja utilityCaja = new UtilityCaja();
        ArrayList myArray = new ArrayList();
        myArray =
                facadeRecaudacion.obtenerRcdPend(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                 strCodRecau);
        validaInfoPedido(myArray);
        /*
        utilityCaja.imprVouVerifRecaudacion(this, txtMontoPagado, 
                                            VariablesRecaudacion.vMontoPago, //monto_cuota
                                            tmpArrayCabRcd.get(34).toString(), // DNI del cliente
                                            ConstantsRecaudacion.TIPO_REC_FINANCIERO,
                                            "S",//tmpArrayCabRcd.get(26).toString(), // tipoMoneda
                                            tmpArrayCabRcd.get(26).toString()); //Nro de tarjeta
        */
    }

    private void validaInfoPedido(ArrayList pArrayList) {
        if (pArrayList.size() < 1) {
            FarmaUtility.showMessage(this, "El Pedido No existe o No se encuentra pendiente de pago", txtMontoPagado);
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
            limpiarDatos();
            limpiarPagos();
            return;
        } else if (pArrayList.size() > 1) {
            FarmaUtility.showMessage(this, "Se encontro mas de un pedido.\n" +
                    "Ponganse en contacto con el area de Sistemas.", txtMontoPagado);
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
            limpiarDatos();
            limpiarPagos();
            return;
        } else {
            if (intTipoCobro == ConstantsCaja.COBRO_RECAUDACION) {
                limpiaVariablesFormaPago();
                muestraInfoPedidoRecau(pArrayList);
            }
        }
    }

    private void limpiarDatos() {
        txtMontoPagado.setText("");
        VariablesCaja.vIndPedidoSeleccionado = "N";
        VariablesCaja.vIndTotalPedidoCubierto = false;
        VariablesCaja.vIndPedidoCobrado = false;
        VariablesCaja.vIndPedidoConProdVirtual = false;
        VariablesCaja.vIndPedidoConvenio = "";
        VariablesCaja.vValMontoPagadoTarj = "";

        VariablesConvenio.vCodCliente = "";
        VariablesConvenio.vCodConvenio = "";
        VariablesConvenio.vValCredDis = 0.00;

        VariablesCaja.cobro_Pedido_Conv_Credito = "N";
        VariablesCaja.valorCredito_de_PedActual = 0.0;

        VariablesCaja.monto_forma_credito_ingresado = "0.00";
        VariablesCaja.uso_Credito_Pedido_N_Delivery = "N";
        VariablesCaja.usoConvenioCredito = "";
        VariablesConvenio.vIndSoloCredito = "";

        VariablesCaja.vValEfectivo = "";
        VariablesCaja.vVuelto = "";
    }

    private void limpiarPagos() {
        if (VariablesCaja.vIndTarjetaSeleccionada && VariablesCaja.vIndDatosTarjeta) {
            FarmaUtility.showMessage(this, "Se ha ingresado informacion de pago con tarjeta.", txtMontoPagado);
            return;
        }

        VariablesCaja.vIndTotalPedidoCubierto = false;
        VariablesCaja.vIndPedidoCobrado = false;
        txtMontoPagado.setText("0.00");
        txtMontoPagadoDolares.setText("0.00");
    }

    private void limpiaVariablesFormaPago() {
        VariablesCaja.vCodFormaPago = "";
        VariablesCaja.vDescFormaPago = "";
        VariablesCaja.vDescMonedaPago = "";
        VariablesCaja.vValMontoPagado = "";
        VariablesCaja.vValMontoCredito = "";
        VariablesCaja.vValTotalPagado = "";
        VariablesCaja.vNumPedVtaNCR = "";
    }

    private void muestraInfoPedidoRecau(ArrayList pArrayList) {
        String tMaxAnulRecaudacion = "";
        String strTipoRecau = "";
        VariablesConvenio.vCodCliente = "";

        //VariablesRecaudacion.vTipCambioCompra
        //INI AOVIEDO 04/09/2017
        //VariablesCaja.vValTipoCambioPedido = VariablesRecaudacion.vTipoCambioVenta + "";
        VariablesCaja.vValTipoCambioPedido = ((String)((ArrayList)pArrayList.get(0)).get(3)).trim();
        //INI AOVIEDO 04/09/2017
        
        VariablesVentas.vTip_Comp_Ped = ((String)((ArrayList)pArrayList.get(0)).get(4)).trim();
        
        String vValTotalPagar = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
        String vValTotalSolesPagar = ((String)((ArrayList)pArrayList.get(0)).get(2)).trim();

        if (ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(tmpArrayCabRcd.get(4).toString())) {
            VariablesCaja.vValTotalPagar = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
            strTipoRecau = "Banco Financiero";
            UtilityCaja utilityCaja = new UtilityCaja();
            String monto = tmpArrayCabRcd.get(10).toString();
            String dniCe = tmpArrayCabRcd.get(34).toString();
            String tipoMoneda = tmpArrayCabRcd.get(7).toString();
            
            utilityCaja.imprVouVerifRecaudacion(this, 
                                                txtMontoPagado, 
                                                monto, 
                                                dniCe,
                                                ConstantsRecaudacion.TIPO_REC_FINANCIERO,
                                                tipoMoneda,
                                                tmpArrayCabRcd.get(26).toString());
            
        }
        VariablesCaja.vIndTotalPedidoCubierto = false;

        //RLLANTOY.11.07.2013. Muestra mensaje tiempo máximo de anulación x tipo de recaudación.
        tMaxAnulRecaudacion = facadeRecaudacion.tiempoMaxAnulacionRecaudacion("RCD");
        
        String msgRecaudacion = "<html> Recaudación: " + strTipoRecau + " <br> Sólo podrá anularse dentro de " +
                           tMaxAnulRecaudacion + " minutos. </html>";
        ArrayList<String> tmpCodFormRCD = new ArrayList<>();
        tmpCodFormRCD = facadeRecaudacion.obtenerCodFormsPagoRCD();        
        
    }

    private void btnMonto_ActionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMontoPagado);
    }

    private void btnMonto_KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnMonto.doClick();
//        } else if ((e.getKeyCode() == KeyEvent.VK_F12)) {
      } else {
            chkKeyPressed(e);
        }
    }

    private void realizarRecaudacionFinanciero() {
        initJTable();
        ArrayList<Object> arrayCobro=new ArrayList();
        arrayCobro.add("00001"); //00
        arrayCobro.add("EFECTIVO SOLES"); //01
        arrayCobro.add("0"); //02
        arrayCobro.add(""); //03
        double TotalPago = FarmaUtility.getDecimalNumber(txtMontoPagado.getText().trim());
        arrayCobro.add(String.valueOf(TotalPago)); //04
        arrayCobro.add(String.valueOf(TotalPago)); //05
        arrayCobro.add("01"); //06
        arrayCobro.add("0.00"); //07
        arrayCobro.add(""); //08
        arrayCobro.add(""); //09
        arrayCobro.add(""); //10
        arrayCobro.add(""); //11
        arrayCobro.add(""); //12
        arrayCobro.add(""); //13
        arrayCobro.add(""); //14
        arrayCobro.add(""); //15
        tableModelDetallePago.insertRow(arrayCobro);
        
        DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
        dlgProcesarPagoTerceros.procesarPagoTerceros(tmpArrayCabRcd, strCodRecau, tableModelDetallePago, txtMontoVuelto,
                                                     txtMontoPagado, txtMontoPagadoDolares);
        dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_PAGO);
        dlgProcesarPagoTerceros.mostrar();
        
        cerrarVentana();
    }
}
