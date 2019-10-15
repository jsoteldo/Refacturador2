package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;//LMEZA
import com.gs.mifarma.componentes.JLabelFunction;

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

import java.util.ArrayList;//LMEZA

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.UtilityRecargaVirtual;
import mifarma.ptoventa.caja.reference.VariablesCaja;
//LMEZA
import mifarma.ptoventa.cliente.reference.ConstantsCliente;
import mifarma.ptoventa.cliente.reference.DBCliente;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;

import mifarma.ptoventa.main.DlgProcesar;

import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;
//LMEZA
import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgPedidosPendientes extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgPedidosPendientes.class);

    Frame myParentFrame;
    FarmaTableModel tableModelDetallePedido;
    FarmaTableModel tableModelListaPendientes;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    ActionMap actionMap1 = new ActionMap();
    JLabelFunction lblF11 = new JLabelFunction();
    JLabelFunction lblEnter = new JLabelFunction();
    JScrollPane scrPendientes = new JScrollPane();
    JPanel pnlRelacion = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JScrollPane scrDetalle = new JScrollPane();
    JPanel pnlItems = new JPanel();
    XYLayout xYLayout3 = new XYLayout();
    JButton btnDetalle = new JButton();
    JLabel jLabel3 = new JLabel();
    JLabelFunction lblEsc = new JLabelFunction();
    JTable tblDetalle = new JTable();
    JTable tblListaPendientes = new JTable();
    private JButtonLabel btnPedidosPendeintes = new JButtonLabel();
    private double vValorSelCopago = -1;//LMEZA
    private String descProductoRimac = "";//LMEZA

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgPedidosPendientes() {
        this(null, "", false);
    }

    public DlgPedidosPendientes(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(668, 395));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Pedidos Pendientes");
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(657, 361));
        jContentPane.setBackground(Color.white);
        jContentPane.setForeground(Color.white);
        lblF11.setText("[ F5] Anular");
        lblF11.setBounds(new Rectangle(250, 335, 100, 20));
        lblEnter.setText("[ ENTER ]  Seleccionar Pedido");
        lblEnter.setBounds(new Rectangle(65, 335, 180, 20));
        scrPendientes.setFont(new Font("SansSerif", 0, 11));
        scrPendientes.setBounds(new Rectangle(10, 40, 635, 100));
        scrPendientes.setBackground(new Color(255, 130, 14));
        pnlRelacion.setBackground(new Color(255, 130, 14));
        pnlRelacion.setLayout(xYLayout2);
        pnlRelacion.setFont(new Font("SansSerif", 0, 11));
        pnlRelacion.setBounds(new Rectangle(10, 15, 635, 25));
        scrDetalle.setFont(new Font("SansSerif", 0, 11));
        scrDetalle.setBounds(new Rectangle(10, 170, 635, 140));
        scrDetalle.setBackground(new Color(255, 130, 14));
        pnlItems.setBackground(new Color(255, 130, 14));
        pnlItems.setFont(new Font("SansSerif", 0, 11));
        pnlItems.setLayout(xYLayout3);
        pnlItems.setBounds(new Rectangle(10, 145, 635, 25));
        btnDetalle.setText("Detalle del Pedido :");
        btnDetalle.setFont(new Font("SansSerif", 1, 11));
        btnDetalle.setHorizontalAlignment(SwingConstants.LEFT);
        btnDetalle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDetalle.setBackground(new Color(43, 141, 39));
        btnDetalle.setForeground(Color.white);
        btnDetalle.setRequestFocusEnabled(false);
        btnDetalle.setMnemonic('d');
        btnDetalle.setBorderPainted(false);
        btnDetalle.setContentAreaFilled(false);
        btnDetalle.setDefaultCapable(false);
        btnDetalle.setFocusPainted(false);
        btnDetalle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDetalle_actionPerformed(e);
            }
        });
        jLabel3.setText("Opciones :");
        jLabel3.setFont(new Font("SansSerif", 1, 11));
        jLabel3.setBounds(new Rectangle(10, 315, 70, 15));
        lblEsc.setText("[ Esc ]  Cerrar");
        lblEsc.setBounds(new Rectangle(545, 335, 95, 20));
        tblDetalle.setFont(new Font("SansSerif", 0, 11));
        tblDetalle.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblDetalle_keyPressed(e);
            }
        });
        tblListaPendientes.setFont(new Font("SansSerif", 0, 11));
        tblListaPendientes.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblListaPendientes_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                tblListaPendientes_keyReleased(e);
            }
        });
        btnPedidosPendeintes.setText("Pedidos Pendientes de Cobranza :");
        btnPedidosPendeintes.setMnemonic('p');
        btnPedidosPendeintes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPedidosPendeintes_actionPerformed(e);
            }
        });
        scrPendientes.getViewport();
        scrDetalle.getViewport();
        pnlItems.add(btnDetalle, new XYConstraints(10, 5, 125, 15));
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        jContentPane.add(lblF11, null);
        jContentPane.add(lblEnter, null);
        scrPendientes.getViewport().add(tblListaPendientes, null);
        jContentPane.add(scrPendientes, null);
        pnlRelacion.add(btnPedidosPendeintes, new XYConstraints(10, 5, 245, 15));
        jContentPane.add(pnlRelacion, null);
        scrDetalle.getViewport().add(tblDetalle, null);
        jContentPane.add(scrDetalle, null);
        jContentPane.add(pnlItems, null);
        jContentPane.add(jLabel3, null);
        jContentPane.add(lblEsc, null);
        this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        mifarma.common.FarmaVariables.vAceptar = false;
        initTableListaPendientes();
        //cargarRegSeleccionado();
        if (tblListaPendientes.getRowCount() > 0) {
            String pNumPedVta = tblListaPendientes.getValueAt(0, 2).toString().trim();
            initTableDetallePedido(pNumPedVta);
        }
    };

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTableListaPendientes() {
        tableModelListaPendientes =
                new FarmaTableModel(ConstantsCaja.columnsListaPendientes, ConstantsCaja.defaultListaPendientes, 0);
        FarmaUtility.initSimpleList(tblListaPendientes, tableModelListaPendientes,
                                    ConstantsCaja.columnsListaPendientes);
        cargaListaPendientes();
    };

    private void initTableDetallePedido(String pNumPedVta) {
        if (tblListaPendientes.getRowCount() > 0) {
            tableModelDetallePedido =
                    new FarmaTableModel(ConstantsCaja.columnsDetallePedido, ConstantsCaja.defaultDetallePedido, 0);
            FarmaUtility.initSimpleList(tblDetalle, tableModelDetallePedido, ConstantsCaja.columnsDetallePedido);
            cargaDetallePedido(pNumPedVta);
        }
    };

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void tblListaPendientes_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void tblDetalle_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(tblListaPendientes);
        /* if(FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL))
                {
                    lblEnter.setVisible(false);
                    lblF11.setVisible(false);
                }*/
    }

    private void btnPedidosPendeintes_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblListaPendientes);
    }

    private void btnDetalle_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblDetalle);
    }
    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            /*if(!FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL))
                            {
                                seleccionaPedido();
                            }*/
            seleccionaPedido();
            
            realizarCobro(); 
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            /*if(!FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_MULTIFUNCIONAL))
                            {
                                anularPedidoPendiente();
                            }*/
            anularPedidoPendiente();

        }
    }
    
    private void realizarCobro() {
        if (tblListaPendientes.getRowCount() > 0 && tblListaPendientes.getSelectedRow() > -1) {
            if (JConfirmDialog.rptaConfirmDialog(this, "¿Está seguro de realizar este cobro pendiente?")) {
                if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(this, null) &&
                    VariablesConvenioBTLMF.vCodConvenio != null &&
                    VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0) {
                } else {
                    if (VariablesCaja.vNumPedVtaOrigenCOBRO.trim().length() == 0)
                        VariablesCaja.vNumPedVtaOrigenCOBRO = VariablesCaja.vNumPedVta;

                    if (buscaPedidoDiario()) {
                        mostrarNuevoCobroPendiente();
                    }
                    VariablesCaja.vNumPedPendiente = "";
                    VariablesCaja.vFecPedACobrar = "";

                }
            }
        }
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void cargaListaPendientes() {
        try {
            DBCaja.getListaPedidosPendientes(tableModelListaPendientes);
            if (tblListaPendientes.getRowCount() > 0)
                FarmaUtility.ordenar(tblListaPendientes, tableModelListaPendientes, 13,
                                     FarmaConstants.ORDEN_DESCENDENTE);
            log.debug("se cargo la lista de cabeceras");
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al listar pedidos pendientes. \n " + e.getMessage(), tblDetalle);
        }
    }

    private void cargaDetallePedido(String pNumPedVta) {
        log.debug("cargaDetallePedido");
        tableModelDetallePedido.clearTable();
        tblDetalle.repaint();
        tblDetalle.removeAll();
        try {
            DBCaja.getListaDetallePedido(tableModelDetallePedido, pNumPedVta);
            if (tblDetalle.getRowCount() > 0)
                FarmaUtility.ordenar(tblDetalle, tableModelDetallePedido, 0, FarmaConstants.ORDEN_ASCENDENTE);
            log.debug("se cargo la lista de detalle");
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al cargra lista detalle pedido. \n " + e.getMessage(), tblDetalle);
        }
    }

    private void tblListaPendientes_keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
            if (tieneRegistroSeleccionado(tblListaPendientes)) {
                //cargarRegSeleccionado();
                if (tieneRegistroSeleccionado(tblListaPendientes)) {
                    String pNumPedVta =
                        tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 2).toString().trim();
                    cargaDetallePedido(pNumPedVta);
                }
            }

    }

    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;

        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    private void cargarRegSeleccionado() {
        if (tieneRegistroSeleccionado(tblListaPendientes))
            VariablesCaja.vNumPedVta =
                    tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 2).toString().trim();

    }

    private void cambiaEstadoPedido(String pNumPed, String pEst) throws SQLException {
        DBCaja.cambiarEstadoPed(pNumPed, pEst);
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void seleccionaPedido() {
        if (tblListaPendientes.getRowCount() <= 0)
            return;
        VariablesCaja.vNumPedVta =
                tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 2).toString().trim();
        VariablesCaja.vNumPedPendiente =
                tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 1).toString().trim();
        VariablesCaja.vFecPedACobrar =
                tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 12).toString().trim();

        VariablesCaja.vIndConvenio =
                tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 14).toString().trim();
        VariablesCaja.vCodConvenio =
                tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 15).toString().trim();
        VariablesCaja.vCodCliLocal =
                tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 16).toString().trim();

        //cerrarVentana(true);//LMEZA
    }
    
//LMEZA    
    private boolean buscaPedidoDiario() {
        ArrayList myArray = new ArrayList();
        String numPedDiario = (String)tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 1);
        String fecPedDiario = (String)tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 3);
        if (fecPedDiario.length() > 0) {
            fecPedDiario = fecPedDiario.substring(0, 10);
        }
        try {
            DBCaja.obtieneInfoCobrarPedido(myArray, numPedDiario, fecPedDiario);
            if (validaInfoPedido(myArray)) {
                return true;
            }
        } catch (SQLException sql) { //log.error("",sql);
            log.error(null, sql);
            FarmaUtility.showMessage(this, "Error al obtener Informacion del Pedido.\n" +
                    sql.getMessage(), tblListaPendientes);
        }
        return false;
    }

    private boolean validaInfoPedido(ArrayList pArrayList) {
        if (pArrayList.size() < 1) {
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
            FarmaUtility.showMessage(this, "No se encontro pedidos.\n" +
                    "Ponganse en contacto con el area de Sistemas.", tblListaPendientes);

            return false;
        } else if (pArrayList.size() > 1) {
            FarmaUtility.showMessage(this, "Se encontro mas de un pedido.\n" +
                    "Ponganse en contacto con el area de Sistemas.", tblListaPendientes);
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
            return false;
        } else {
            limpiaVariablesFormaPago();
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_S;
            muestraInfoPedido(pArrayList);
            return true;
        }
    }

    private void muestraInfoPedido(ArrayList pArrayList) {
        VariablesCaja.vNumPedVta = ((String)((ArrayList)pArrayList.get(0)).get(0)).trim();
        log.info("Pedido cargado: " + VariablesCaja.vNumPedVta);
        if (!UtilityCaja.verificaEstadoPedido(this, VariablesCaja.vNumPedVta, ConstantsCaja.ESTADO_PENDIENTE,
                                              tblListaPendientes)) {
            VariablesCaja.vIndPedidoSeleccionado = FarmaConstants.INDICADOR_N;
            return;
        }
        FarmaUtility.liberarTransaccion();

        VariablesCaja.vValTotalPagar = ((String)((ArrayList)pArrayList.get(0)).get(1)).trim();
        String valDolares = ((String)((ArrayList)pArrayList.get(0)).get(2)).trim();
        valDolares =
                FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(valDolares) + FarmaUtility.getRedondeo(FarmaUtility.getDecimalNumber(valDolares)));
        VariablesCaja.vValTipoCambioPedido = ((String)((ArrayList)pArrayList.get(0)).get(3)).trim();
        VariablesVentas.vTip_Comp_Ped = ((String)((ArrayList)pArrayList.get(0)).get(4)).trim();
        VariablesVentas.vNom_Cli_Ped = ((String)((ArrayList)pArrayList.get(0)).get(6)).trim();
        VariablesVentas.vRuc_Cli_Ped = ((String)((ArrayList)pArrayList.get(0)).get(7)).trim();
        VariablesVentas.vDir_Cli_Ped = ((String)((ArrayList)pArrayList.get(0)).get(8)).trim();
        VariablesVentas.vTipoPedido = ((String)((ArrayList)pArrayList.get(0)).get(9)).trim();
        VariablesVentas.vNumOrdeCompra = ((String)((ArrayList)pArrayList.get(0)).get(18)).trim();
        VariablesVentas.vPuntoLlegada = ((String)((ArrayList)pArrayList.get(0)).get(17)).trim();
        VariablesVentas.vMensajeBotiquinBTLVtaInstitucional = ((String)((ArrayList)pArrayList.get(0)).get(19)).trim();
        VariablesCaja.vIndPedidoConvenio = ((String)((ArrayList)pArrayList.get(0)).get(14)).trim();

        if ((VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA) ||
             VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET_FACT)) &&
            !"S".equalsIgnoreCase(VariablesCaja.vIndPedidoConvenio)) {
            boolean isValido = true;
            if (ConstantsCliente.RESULTADO_RUC_INVALIDO.equalsIgnoreCase(DBCliente.verificaRucValido2(VariablesVentas.vRuc_Cli_Ped))) {
                FarmaUtility.showMessage(this, "NRO RUC DE CLIENTE INVALIDO, VERIFIQUE!!!\n" +
                        "RUC: " + VariablesVentas.vRuc_Cli_Ped, tblListaPendientes);
                isValido = false;
            } else {
                if (VariablesVentas.vNom_Cli_Ped.trim().length() < 4) {
                    FarmaUtility.showMessage(this, "RAZON SOCIAL DEL CLIENTE NO CUMPLE CON ESTANDAR\n" +
                            "RAZON SOCIAL : " + VariablesVentas.vNom_Cli_Ped + "\n" +
                            "DEBE CONTENER 04(CUATRO) CARACTERES MINIMO, VERIFIQUE!!!", tblListaPendientes);
                    isValido = false;
                }
            }

            if (!isValido) {
                VariablesVentas.vRuc_Cli_Ped = "";
                VariablesVentas.vNom_Cli_Ped = "";
                //limpiarDatos();
                //limpiaVariablesVirtuales();
                cerrarVentana(false);
            }
        }
        VariablesVentas.vCant_Items_Ped = ((String)((ArrayList)pArrayList.get(0)).get(13)).trim();
        //ERIOS v2.4.5 Se obtiene el copago variable
        vValorSelCopago = FarmaUtility.getDecimalNumber(((String)((ArrayList)pArrayList.get(0)).get(20)).trim());
        VariablesConvenio.vPorcCoPago = FarmaUtility.formatNumber(vValorSelCopago);
        //ERIOS 05.03.2015 Muestra mensaje de ahorro
        String strAhorro[] = ((String)((ArrayList)pArrayList.get(0)).get(21)).trim().split("@");
        evaluaPedidoProdRimac(VariablesCaja.vNumPedVta);
        if (VariablesCaja.vIndDistrGratuita.equalsIgnoreCase(FarmaConstants.INDICADOR_S) ||
            FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar.trim()) <= 0) {
            VariablesCaja.vIndTotalPedidoCubierto = true;
        } else {
            VariablesCaja.vIndTotalPedidoCubierto = false;
        }
    }

    private void evaluaPedidoProdRimac(String pNumPedido) {
        boolean flag = false;
        String tipoProd = "";
        flag = UtilityCaja.get_ind_ped_con_rimac(pNumPedido);
        if (flag) {
            //INI ASOSA - 03/07/2014

            UtilityCaja utilityCaja = new UtilityCaja();
            log.info("VariablesVentas.vVal_Neto_Ped: " + VariablesVentas.vVal_Neto_Ped);
            log.info("VariablesVentas.vVal_Neto_Ped: " + VariablesVentas.vVal_Bruto_Ped);


            utilityCaja.imprVouVerifRimac(this, null, descProductoRimac, VariablesVentas.vDniRimac,
                                          String.valueOf(VariablesVentas.vCantMesRimac).trim(),
                                          VariablesVentas.vVal_Neto_Ped, VariablesPtoVenta.vImpresoraActual,
                                          VariablesPtoVenta.vTipoImpTermicaxIp);
        }

    }

    private void limpiaVariablesFormaPago() {
        VariablesCaja.vCodFormaPago = "";
        VariablesCaja.vDescFormaPago = "";
        VariablesCaja.vDescMonedaPago = "";
        VariablesCaja.vValMontoPagado = "";
        VariablesCaja.vValTotalPagado = "";
        VariablesCaja.vNumPedVtaNCR = "";
        log.debug("************************LimpiaVariablesFormaPago***********************");
    }

    private void mostrarNuevoCobroPendiente() {
        FarmaConnection.closeConnection();
        DlgProcesar.setVersion();

        DlgNuevoCobro dlgFormaPago = new DlgNuevoCobro(myParentFrame, "", true);

        dlgFormaPago.setIndPedirLogueo(false);
        dlgFormaPago.setIndPantallaCerrarAnularPed(true);
        dlgFormaPago.setIndPantallaCerrarCobrarPed(true);

        String descProd = FarmaUtility.getValueFieldJTable(tblListaPendientes, 0, 1);
        log.info("producto nuevo cobro: " + descProd);
        dlgFormaPago.setDescProductoRecVirtual(descProd);
        dlgFormaPago.setDescProductoRimac(descProd);
        dlgFormaPago.isPedidoPendientePinpad = true;
        dlgFormaPago.setVisible(true);

        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            //cerrarVentana(true);
            cargaListaPendientes();
            tableModelDetallePedido.clearTable();
            tblDetalle.repaint();
            if (tblListaPendientes.getRowCount() != 0) {
                String numeroPedido =
                    tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 2).toString().trim();
                cargaDetallePedido(numeroPedido);

            } else {
                FarmaUtility.showMessage(this, "No tiene más pendientes por cobrar", null);
                cerrarVentana(true);
            }

        }
    }
//LMEZA
    
    private void anularPedidoPendiente() {
        if (tblListaPendientes.getRowCount() > 0 && tblListaPendientes.getSelectedRow() > -1) {
            if (com.gs.mifarma.componentes.JConfirmDialog.rptaConfirmDialog(this,
                                                                            "¿Está seguro que desea efectuar la operación?")) {
                try {
                    cargarRegSeleccionado();
                    if (permiteAnularPedidoRecarga()) {
                        DBCaja.anularPedidoPendiente(VariablesCaja.vNumPedVta);
                        ///-- inicio de validacion de Campaña
                        // DUBILLUZ 19.12.2008
                        String pIndLineaMatriz =
                            FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_N);
                        boolean pRspCampanaAcumulad =
                            UtilityCaja.realizaAccionCampanaAcumulada(pIndLineaMatriz, VariablesCaja.vNumPedVta, this,
                                                                      ConstantsCaja.ACCION_ANULA_PENDIENTE, lblEsc,
                                                                      FarmaConstants.INDICADOR_N);

                        if (!pRspCampanaAcumulad) {
                            FarmaUtility.liberarTransaccion();
                            FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                  FarmaConstants.INDICADOR_S);
                        }

                        FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                              FarmaConstants.INDICADOR_S);
                        FarmaUtility.aceptarTransaccion();
                        //FarmaUtility.liberarTransaccion();
                        VariablesCaja.vNumPedVta = VariablesCaja.vNumPedVtaOrigenCOBRO;
                        FarmaUtility.showMessage(this, "La operación se realizó correctamente", lblEsc);
                    }
                    //cerrarVentana(true);
                } catch (SQLException ex) {
                    VariablesCaja.vNumPedVta = VariablesCaja.vNumPedVtaOrigenCOBRO;
                    FarmaUtility.liberarTransaccion();
                    if (ex.getErrorCode() == 20002)
                        FarmaUtility.showMessage(this, "El pedido ya fue anulado!!!", null);
                    else if (ex.getErrorCode() == 20003)
                        FarmaUtility.showMessage(this, "El pedido ya fue cobrado!!!", null);
                    else if (ex.getErrorCode() == 20222)
                        FarmaUtility.showMessage(this,
                                                 "NO SE PUEDE ANULAR UN PEDIDO DE RECARGA.!!!" + VariablesCaja.vNumPedVta +
                                                 "-" + VariablesCaja.vNumPedVtaOrigenCOBRO, null);
                    else
                        FarmaUtility.showMessage(this, "Ocurrió un error al anular pedido pendiente : \n" +
                                ex.getMessage(), lblEsc);
                    //FarmaUtility.showMessage(this,"Ocurrió un error al anular pedido pendiente : \n"+ ex.getMessage(), lblEsc);
                    log.error("", ex);
                    //cerrarVentana(false);
                } finally {
                    /*
                    VariablesCaja.vNumPedVta = VariablesCaja.vNumPedVtaOrigenCOBRO;
                    cargaListaPendientes();
                    tableModelDetallePedido.clearTable();
                    tblDetalle.repaint();
                    */
                    //INI LMEZA
                    cargaListaPendientes();
                    tableModelDetallePedido.clearTable();
                    tblDetalle.repaint();
                    if (tblListaPendientes.getRowCount() != 0) {
                        String numeroPedido =
                            tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 2).toString().trim();
                        cargaDetallePedido(numeroPedido);

                    } else {
                        FarmaUtility.showMessage(this, "No tiene más pendientes por cobrar", null);
                        cerrarVentana(true);
                    }
                    //FIN LMEZA
                }
            }
        }
    }

    public boolean permiteAnularPedidoRecarga() {
        boolean pResultado = false;
        //CARGANDO PARAMETROS
        String pNumPedVta = tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 2).toString().trim();
        String pFecha =
            tblListaPendientes.getValueAt(tblListaPendientes.getSelectedRow(), 3).toString().trim().substring(0, 10);
        String pRpta = "";
        log.info("pNumPedVta :" + pNumPedVta);
        log.info("pFecha :" + pFecha);
        if (isPedidoRecarga(pNumPedVta)) {

            try {
                pRpta = (new UtilityRecargaVirtual()).getMensajeRPTRecargaPedido(pNumPedVta, pFecha);
            } catch (Exception ex) {
                log.debug("", ex);
                pRpta = ex.getMessage();
            }

            log.info("pRpta :" + pRpta);

            if (pRpta.trim().length() > 2) {
                pResultado = false;
                FarmaUtility.showMessage(this, "Error al Revisar la Recarga : \n" +
                        pRpta.trim(), null);
            } else {
                if (pRpta.trim().equalsIgnoreCase("S"))
                    pResultado = true; // PERMITE XQ LA RECARGA NO EXISTE
                else {
                    if (pRpta.trim().equalsIgnoreCase("N")) {
                        pResultado = false;
                        FarmaUtility.showMessage(this,
                                                 "No se pudo Obtener la Resputa de la Recarag NO SE PUEDE ANULAR",
                                                 null);
                    } else {
                        if (pRpta.trim().equalsIgnoreCase("00")) {
                            pResultado = false;
                            FarmaUtility.showMessage(this,
                                                     "NO SE PUEDE ANULAR PORQUE LA RECARGA PASO EXITOSAMENTE",
                                                     null);
                        } else
                            pResultado = true; // PERMITE XQ LA RECARGA DIO ERROR
                    }
                }
            }

        } else
            return true;

        return pResultado;
    }


    private int cantidadProductosVirtualesPedido(String pNumPedido) {
        int cant = 0;
        try {
            cant = DBCaja.obtieneCantProdVirtualesPedido(pNumPedido);
        } catch (Exception ex) {
            log.error("", ex);
            cant = 0;
            FarmaUtility.showMessage(this, "Error al obtener cantidad de productos virtuales.\n" +
                    ex.getMessage(), tblListaPendientes);
        }
        return cant;
    }

    private boolean isPedidoRecarga(String pNumPedido) {
        int cantProdVirtualesPed = 0;
        cantProdVirtualesPed = cantidadProductosVirtualesPedido(pNumPedido);
        if (cantProdVirtualesPed <= 0) {
            return false;
        } else {
            return true;
        }
    }

}
