package mifarma.ptoventa.recaudacion;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.inventario.reference.UtilityInventario;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.UtilityRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2016 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicaci�n : DlgRecaudacionPrestamosIncasur.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * ASOSA      16.05.2016   Creaci�n     INCASUR<br>
 * <br>
 * @author Alfredo Sosa Dord�n<br>
 * @version 1.0<br>
 *
 */
public class DlgRecaudacionPrestamosIncasur extends JDialog {

    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(DlgRecaudacionPrestamosIncasur.class);

    Frame myParentFrame;

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JButton btnRecibo = new JButton();
    private JTextFieldSanSerif txtRecibo = new JTextFieldSanSerif();
    private JPanelHeader jPanel3 = new JPanelHeader();
    private JLabel lblUsuario = new JLabel();
    private JLabel txtUsuario = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel txtFecha = new JLabel();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();

    ArrayList<ArrayList<String>> tmpArrayPres = new ArrayList<>();
    private JComboBox cmbTipo = new JComboBox();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JLabelWhite lblMensaje = new JLabelWhite();
    private JComboBox cmbMoneda = new JComboBox();

    UtilityRecaudacion utilityRecaudacion = new UtilityRecaudacion();
    UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();

    private JTextFieldSanSerif jTextFieldSanSerif1 = new JTextFieldSanSerif();
    private boolean bDeuda;
    private JButton btnBuscar = new JButton();
    private String tipoMonemdaRecau = "";

    String tmpCodigoAutoriza = "";
    String tmpMontoPagar = "";
    String strTotalVenta = "";
    String strEstCta = "";
    private JButtonLabel lblPago = new JButtonLabel();
    private JTextFieldSanSerif txtMonto = new JTextFieldSanSerif();
    private JButtonLabel lblDni = new JButtonLabel();
    private JTextFieldSanSerif txtdni = new JTextFieldSanSerif();
    private boolean indVisibleDni = true;


    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgRecaudacionPrestamosIncasur() {
        this(null, "", false);
    }

    public DlgRecaudacionPrestamosIncasur(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            FarmaVariables.vAceptar = false;
            utilityRecaudacion.initMensajesVentana(this, txtFecha, txtUsuario,
                                                   ConstantsRecaudacion.TIPO_REC_INCASUR);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(454, 334));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Recaudaci\u00f3n Prestamos Incasur");
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
        btnRecibo.setText("Seleccione :");
        btnRecibo.setBackground(Color.white);
        btnRecibo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRecibo.setBorderPainted(false);
        btnRecibo.setContentAreaFilled(false);
        btnRecibo.setDefaultCapable(false);
        btnRecibo.setFocusPainted(false);
        btnRecibo.setFont(new Font("SansSerif", 1, 11));
        btnRecibo.setForeground(new Color(255, 130, 14));
        btnRecibo.setHorizontalAlignment(SwingConstants.LEFT);
        btnRecibo.setMnemonic('s');
        btnRecibo.setRequestFocusEnabled(false);
        btnRecibo.setBounds(new Rectangle(10, 15, 70, 20));
        btnRecibo.setActionCommand("");
        btnRecibo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRecibo_actionPerformed(e);
            }
        });
        txtRecibo.setBounds(new Rectangle(190, 15, 130, 20));
        txtRecibo.setDocument(new FarmaLengthText(16));
        txtRecibo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtRecibo_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtRecibo_keyTyped(e);
            }
        });
        jPanel3.setBounds(new Rectangle(20, 20, 410, 55));
        lblUsuario.setText("Usuario :");
        lblUsuario.setSize(new Dimension(30, 20));
        lblUsuario.setFont(new Font("Dialog", 1, 11));
        lblUsuario.setBounds(new Rectangle(15, 15, 55, 20));
        lblUsuario.setForeground(Color.white);
        txtUsuario.setBounds(new Rectangle(70, 15, 100, 20));
        txtUsuario.setFont(new Font("Dialog", 1, 11));
        txtUsuario.setForeground(Color.white);
        lblFecha.setText("Fecha :");
        lblFecha.setBounds(new Rectangle(190, 15, 45, 20));
        lblFecha.setForeground(Color.white);
        lblFecha.setFont(new Font("Dialog", 1, 11));
        lblFecha.setSize(new Dimension(75, 20));
        txtFecha.setForeground(Color.white);
        txtFecha.setFont(new Font("Dialog", 1, 11));
        txtFecha.setBounds(new Rectangle(240, 15, 150, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(240, 280, 90, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(340, 280, 90, 20));
        cmbTipo.setBounds(new Rectangle(80, 15, 105, 20));
        cmbTipo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipo_keyPressed(e);
            }
        });
        cmbMoneda.setBounds(new Rectangle(80, 50, 80, 20));
        cmbMoneda.setPreferredSize(new Dimension(25, 20));
        cmbMoneda.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbMoneda_keyPressed(e);
            }
        });
        jPanelTitle1.setBounds(new Rectangle(0, 105, 410, 45));
        lblMensaje.setText("[MENSAJE]");
        lblMensaje.setBounds(new Rectangle(15, 10, 375, 30));
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(245, 50, 75, 21));
        btnBuscar.setMnemonic('B');
        btnBuscar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnBuscar_keyPressed(e);
            }
        });
        lblPago.setText("Ingrese monto a pagar:");
        lblPago.setBounds(new Rectangle(10, 165, 155, 15));
        lblPago.setForeground(new Color(255, 115, 66));
        lblPago.setMnemonic('i');
        lblPago.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblPago_actionPerformed(e);
                }
            });
        txtMonto.setBounds(new Rectangle(190, 160, 100, 20));
        txtMonto.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtMonto_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtMonto_keyTyped(e);
                }
            });
        lblDni.setText("Ingrese DNI:");
        lblDni.setBounds(new Rectangle(10, 85, 75, 15));
        lblDni.setForeground(new Color(255, 115, 66));
        lblDni.setMnemonic('d');
        lblDni.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblDni_actionPerformed(e);
                }
            });
        txtdni.setBounds(new Rectangle(80, 80, 110, 20));
        jPanel2.setBounds(new Rectangle(20, 80, 410, 195));
        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        jPanel2.setBackground(Color.white);
        jPanel2.setLayout(null);
        jPanel2.setForeground(Color.white);

        jPanel3.add(txtFecha, null);
        jPanel3.add(lblFecha, null);
        jPanel3.add(txtUsuario, null);
        jPanel3.add(lblUsuario, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jPanel3, null);
        jPanel1.add(lblF11, null);
        jPanel1.add(lblEsc, null);
        jPanel2.add(txtdni, null);
        jPanel2.add(lblDni, null);
        jPanel2.add(txtMonto, null);
        jPanel2.add(lblPago, null);
        jPanel2.add(btnBuscar, null);
        jPanel2.add(cmbMoneda, null);
        jPanelTitle1.add(lblMensaje, null);
        jPanel2.add(jPanelTitle1, null);
        jPanel2.add(cmbTipo, null);
        jPanel2.add(btnRecibo, null);
        txtRecibo.add(jTextFieldSanSerif1, null);
        txtRecibo.setLengthText(15);
        txtdni.setLengthText(20);
        txtdni.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtdni_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtdni_keyTyped(e);
                }
            });
        jPanel2.add(txtRecibo, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        cargaCombos();
    }
    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void cargaCombos() {
        FarmaLoadCVL.loadCVLfromArrays(cmbTipo, ConstantsRecaudacion.HASHTABLE_OPCION_COD_BUSQUEDA,
                                       ConstantsRecaudacion.OP_COD, ConstantsRecaudacion.OP_DESC, true);

        FarmaLoadCVL.loadCVLfromArrays(cmbMoneda, ConstantsRecaudacion.HASHTABLE_OPCION_MONEDA,
                                       ConstantsRecaudacion.MONEDA_COD, ConstantsRecaudacion.MONEDA_DESC, true);
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        if (VariablesRecaudacion.vTipoCambioVenta == 0) {
            FarmaUtility.showMessage(this,
                                     "ATENCI�N: No se pudo obtener el tipo de cambio actual\nNo se puede continuar con la acci�n",
                                     null);
            cerrarVentana(false);
        } else {
            FarmaUtility.centrarVentana(this);
            FarmaUtility.moveFocus(txtRecibo);
            lblMensaje.setVisible(false);
            FarmaUtility.moveFocus(cmbTipo);
            setVisibleDni();
        }
    }
    
    private void setVisibleDni() {
        String ind = "N";//UtilityInventario.obtenerParametroString(ConstantsRecaudacion.ID_IND_DNI_VISIBLE_RAIZ);
        if (ind.equals("N")) {
            lblDni.setVisible(false);
            txtdni.setVisible(false);
            indVisibleDni = false;
        } else {
            indVisibleDni = true;
        }
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void txtRecibo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbMoneda);
        } else {
            chkKeyPressed(e);
        }
    }

    private void cmbMoneda_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(btnBuscar);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtRecibo_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtRecibo, e);
    }

    private void btnRecibo_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipo);
    }

    private void cmbTipo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtRecibo);
        } else {
            chkKeyPressed(e);
        }
    }

    private void btnBuscar_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (utilityPtoVenta.validarCampTxtField(this, txtRecibo)) {
                bDeuda = buscarDeuda();
                if (indVisibleDni) {
                    FarmaUtility.moveFocus(txtdni);
                } else {
                    FarmaUtility.moveFocus(txtMonto);
                }
            } else {
                lblMensaje.setText("");
            }
        } else {
            chkKeyPressed(e);
        }
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (utilityPtoVenta.validarCampTxtField(this, txtRecibo)) {
                if (bDeuda) {
                    funcF11();
                }
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

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void funcF11() {
        //como ahora se ingresa el monto tengo que hacer de nuevo el calculo.
        tmpMontoPagar = txtMonto.getText();
        if (ConstantsRecaudacion.EST_CTA_SOLES.equals(strEstCta)) {
            strTotalVenta = tmpMontoPagar;
        } else {
            strTotalVenta = String.valueOf(new Double(tmpMontoPagar) * VariablesRecaudacion.vTipoCambioVenta);
        }
        if (true) {//isMontoValido()) {
            String dniCli = txtdni.getText();
            String tmpCod = txtRecibo.getText().trim();
            ArrayList<Object> arrayCabecera = new ArrayList<Object>();
            /*0*/arrayCabecera.add(FarmaVariables.vCodGrupoCia);
            /*1*/arrayCabecera.add(FarmaVariables.vCodCia);
            /*2*/arrayCabecera.add(FarmaVariables.vCodLocal);
            /*3*/arrayCabecera.add(""); //nro de tarjeta
            /*4*/arrayCabecera.add(ConstantsRecaudacion.TIPO_REC_INCASUR); //tipo recaudacion
            /*5*/arrayCabecera.add(""); //tipo pago
            /*6*/arrayCabecera.add(ConstantsRecaudacion.ESTADO_PENDIENTE);
            /*7*/arrayCabecera.add(strEstCta);
            /*8*/arrayCabecera.add(VariablesRecaudacion.vNroRecibo); //codigo de recibo o de cliente //tmpCod
            /*9*/arrayCabecera.add(tipoMonemdaRecau); // tipo moneda FarmaConstants.MONEDA_SOLES
            /*10*/arrayCabecera.add(UtilityRecaudacion.formatNumber(tmpMontoPagar)); //Monto total(moneda original)
            /*11*/arrayCabecera.add(UtilityRecaudacion.formatNumber(strTotalVenta.trim())); //arrayCabecera.add(tmpMontoPagar);//Monto soles
            /*12*/arrayCabecera.add(UtilityRecaudacion.formatNumber(strTotalVenta.trim())); //arrayCabecera.add(tmpMontoPagar);//Monto minimo
            /*13*/arrayCabecera.add(VariablesRecaudacion.vTipoCambioVenta); //Tipo cambio
            /*14*/arrayCabecera.add(""); //Fecha Venc Tarj
            /*15*/arrayCabecera.add(""); //Nombre Cliente
            /*16*/arrayCabecera.add("");
            /*17*/arrayCabecera.add(ConstantsRecaudacion.FECHA_RCD);
            /*18*/arrayCabecera.add(FarmaVariables.vIdUsu);
            /*19*/arrayCabecera.add("");
            /*20*/arrayCabecera.add("");
            /*21*/arrayCabecera.add(tmpCodigoAutoriza); //codigo de autorizacion
            /*22*/arrayCabecera.add(""); //mov de caja
            /*23*/arrayCabecera.add(""); //numero de pedido solo valido para VENTA CMR
            /*24*/arrayCabecera.add(""); //tipo producto servicio
            /*25*/arrayCabecera.add(""); //numero de recibo de pago
                  
            /*26*/arrayCabecera.add(dniCli); //dni cliente

            String tmpCodigo = facadeRecaudacion.grabaCabeRecau(arrayCabecera,dniCli);
            if (!tmpCodigo.equals("")) {
                if (facadeRecaudacion.cobrarRecaudacion(myParentFrame, tmpCodigo, arrayCabecera,
                                                        ConstantsRecaudacion.TIPO_REC_INCASUR)) {
                    cerrarVentana(true);
                }
            } else {
                FarmaUtility.showMessage(this, "Error al procesar el cobro.", txtRecibo);
            }
        }     
    }
    
    private boolean isMontoValido() {
        boolean flag = true;
        double montoMax = Double.parseDouble(UtilityInventario.obtenerParametroInteger(ConstantsRecaudacion.ID_MONT_MAX_RECAU_RAIZ));
        String simbolo = ""+ConstantesUtil.simboloSoles;
        if (ConstantsRecaudacion.EST_CTA_DOLARES.equals(strEstCta)) {
            montoMax = FarmaUtility.getDecimalNumberRedondeado(montoMax / VariablesRecaudacion.vTipoCambioVenta);
            simbolo = "$";
        }
        
        if (montoMax < Double.parseDouble(tmpMontoPagar)) {
            flag = false;
            FarmaUtility.showMessage(this, "El monto m�ximo a pagar es " + simbolo + " " + montoMax, txtRecibo);
            FarmaUtility.moveFocus(txtMonto);
        }
        return flag;
    }

    private boolean buscarDeuda() {
        boolean flag = true;
        String simboloMoneda = "";
        String tipoCod =
            FarmaLoadCVL.getCVLCode(ConstantsRecaudacion.HASHTABLE_OPCION_COD_BUSQUEDA, cmbTipo.getSelectedIndex());
        String tipoMoneda =
            FarmaLoadCVL.getCVLCode(ConstantsRecaudacion.HASHTABLE_OPCION_MONEDA, cmbMoneda.getSelectedIndex());
        String codBus = txtRecibo.getText();
        
        FarmaVariables.vAceptar = true;
        
        if (tipoCod.trim().equals(ConstantsRecaudacion.COD_CLIENTE_INCASUR)) {   //1: codigo cliente | 2: nro. recibo
        
            DlgListaRecibosIncasur dlgListaRecibosIncasur = new DlgListaRecibosIncasur(myParentFrame, "", true);
            dlgListaRecibosIncasur.setPCodCliente(codBus.trim());
            dlgListaRecibosIncasur.setPCodServ(ConstantsRecaudacion.TIPO_REC_INCASUR);
            dlgListaRecibosIncasur.setVisible(true);
            codBus = VariablesRecaudacion.vNroRecibo;
            
        }
        
        if (FarmaVariables.vAceptar) {
            String tramaConsulta =
                facadeRecaudacion.getTramaPresCiti(ConstantsRecaudacion.NRO_RECIBO_INCASUR, 
                                                   codBus.trim(), 
                                                   tipoMoneda, 
                                                   ConstantsRecaudacion.TIPO_TRANSACCION_PAGO,
                                                   facadeRecaudacion.getCodLocalMigra(), 
                                                   ConstantsRecaudacion.TIPO_REC_INCASUR);

            String tramaRpt = facadeRecaudacion.setTramaPresCiti(tramaConsulta);
            tmpArrayPres = facadeRecaudacion.obtenerDatosPagarCitiPres(tramaRpt);
            lblMensaje.setVisible(true);

            if (((ArrayList)tmpArrayPres.get(0)).size() == 1) { //En el caso de que el codigo no exista
                lblMensaje.setText(((ArrayList)tmpArrayPres.get(0)).get(0).toString());            
                flag = false;
            } else {
                tmpMontoPagar = ((ArrayList)tmpArrayPres.get(0)).get(4).toString();
                tmpMontoPagar = UtilityRecaudacion.eliminaComasNumber(tmpMontoPagar);
                tmpCodigoAutoriza = ((ArrayList)tmpArrayPres.get(0)).get(0).toString();
                double dTotalSoles = 0;

                if (ConstantsRecaudacion.FORMA_PAGO_PRESCITI_EFECTIVO_SOLES.equals(tipoMoneda)) {
                    simboloMoneda = ConstantsRecaudacion.SIMBOLO_SOLES;
                    tipoMonemdaRecau = FarmaConstants.MONEDA_SOLES;
                    strEstCta = ConstantsRecaudacion.EST_CTA_SOLES;
                    strTotalVenta = tmpMontoPagar;
                } else {
                    simboloMoneda = ConstantsRecaudacion.SIMBOLO_DOLARES;
                    tipoMonemdaRecau = FarmaConstants.MONEDA_DOLARES;
                    strEstCta = ConstantsRecaudacion.EST_CTA_DOLARES;
                    dTotalSoles = new Double(tmpMontoPagar) * VariablesRecaudacion.vTipoCambioVenta;
                    strTotalVenta = String.valueOf(dTotalSoles);
                }
                lblMensaje.setText("Tiene una deuda de : " + simboloMoneda +
                                   FarmaUtility.formatNumber(new Double(tmpMontoPagar)) +
                                   " - Tipo de Cambio: " + 
                                   FarmaUtility.formatNumber(VariablesRecaudacion.vTipoCambioVenta));  
                lblPago.setText("Ingrese monto a pagar " + simboloMoneda + ":");
            }
        } else{
            flag = false;
            lblMensaje.setText("");
            txtRecibo.setText("");
            FarmaUtility.moveFocus(cmbTipo);
        }
        
        return flag;
    }

    private void lblPago_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMonto);
    }

    private void txtdni_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtMonto);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtMonto_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void txtdni_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtdni, e);
    }

    private void txtMonto_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMonto, e);
    }

    private void lblDni_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtdni);
    }

    public boolean isIndVisibleDni() {
        return indVisibleDni;
    }

    public void setIndVisibleDni(boolean indVisibleDni) {
        this.indVisibleDni = indVisibleDni;
    }
    
}
