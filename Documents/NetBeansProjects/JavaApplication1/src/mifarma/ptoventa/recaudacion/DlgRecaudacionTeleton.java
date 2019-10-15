package mifarma.ptoventa.recaudacion;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

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
  * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
  * Nombre de la Aplicaci�n : DlgRecaudacionTeleton.java<br>
  * <br>
  * Hist�rico de Creaci�n/Modificaci�n<br>
  * ASOSA      08.08.2016   Creaci�n<br>
  * AOVIEDO    07.04.2017   Modificaci�n <br>
  * <br>
  * @author <br>
  * @version 1.0<br>
  *
  */
public class DlgRecaudacionTeleton extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgRecaudacionTeleton.class);

    Frame myFrameParent;

    private JComboBox cmbMoneda = new JComboBox();
    private JTextFieldSanSerif txtMonto1 = new JTextFieldSanSerif();

    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jPanel3 = new JPanel();
    private JLabel lblUsuario = new JLabel();
    private JLabel txtUsuario = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel txtFecha = new JLabel();
    private JButtonLabel lblMoneda = new JButtonLabel();
    private JButtonLabel lblMonto1 = new JButtonLabel();

    private FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();

    UtilityRecaudacion utilityRecaudacion = new UtilityRecaudacion();
    UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    private JButtonLabel lblMonto2 = new JButtonLabel();
    private JTextFieldSanSerif txtMonto2 = new JTextFieldSanSerif();
    private JButtonLabel lblDniCe = new JButtonLabel();
    private JTextFieldSanSerif txtDniCe = new JTextFieldSanSerif();
    
    private String tipoRecau;
    private double minRecau;
    private double maxRecau;

    public DlgRecaudacionTeleton() {
        this(null, "", false);
    }

    public DlgRecaudacionTeleton(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myFrameParent = parent;
        try {
            jbInit();
            initialize();
            utilityRecaudacion.initMensajesVentana(this, txtFecha, txtUsuario, null);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(454, 370));
        this.getContentPane().setLayout(borderLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //this.setTitle("Recaudaci\u00f3n Telet\u00f3n"); // AOVIEDO 07/04/17
        this.setTitle("Recaudaci\u00f3n Cruz Roja"); // AOVIEDO 07/04/17
        this.setDefaultCloseOperation(0);

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
        jPanel1.setSize(new Dimension(452, 290));


        txtMonto1.setBounds(new Rectangle(170, 80, 80, 20));
        txtMonto1.setLengthText(9);
        txtMonto1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtMonto1_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtMonto1_keyTyped(e);
            }
        });
        cmbMoneda.setBounds(new Rectangle(170, 45, 80, 20));
        cmbMoneda.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbEstCta_keyPressed(e);
            }
        });
        cmbMoneda.addItem("Soles");
        cmbMoneda.addItem("Dolares");
        jPanel3.setBounds(new Rectangle(20, 20, 410, 55));
        jPanel3.setBackground(new Color(43, 141, 39));
        jPanel3.setForeground(new Color(0, 132, 66));
        jPanel3.setLayout(null);
        lblUsuario.setText("Usuario :");
        lblUsuario.setSize(new Dimension(30, 20));
        lblUsuario.setFont(new Font("Dialog", 1, 11));
        lblUsuario.setBounds(new Rectangle(15, 20, 55, 20));
        lblUsuario.setForeground(Color.white);
        txtUsuario.setBounds(new Rectangle(70, 20, 100, 20));
        txtUsuario.setFont(new Font("Dialog", 1, 11));
        txtUsuario.setForeground(Color.white);
        lblFecha.setText("Fecha :");
        lblFecha.setBounds(new Rectangle(190, 20, 50, 20));
        lblFecha.setForeground(Color.white);
        lblFecha.setFont(new Font("Dialog", 1, 11));
        lblFecha.setSize(new Dimension(75, 20));
        txtFecha.setForeground(Color.white);
        txtFecha.setFont(new Font("Dialog", 1, 11));
        txtFecha.setBounds(new Rectangle(240, 20, 155, 20));
        lblMoneda.setText("Moneda:");
        lblMoneda.setBounds(new Rectangle(10, 45, 105, 20));
        lblMoneda.setForeground(new Color(255, 130, 14));
        lblMoneda.setMnemonic('e');
        lblMoneda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblMoneda_actionPerformed(e);
            }
        });
        lblMonto1.setText("Monto a Pagar:");
        lblMonto1.setBounds(new Rectangle(10, 80, 100, 20));
        lblMonto1.setForeground(new Color(255, 130, 14));
        lblMonto1.setMnemonic('m');
        lblMonto1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    lblMonto1_actionPerformed(e);
                }
        });
        lblMonto2.setText("Ingrese de nuevo el monto:");
        lblMonto2.setBounds(new Rectangle(10, 115, 160, 15));
        lblMonto2.setForeground(new Color(255, 130, 14));
        lblMonto2.setMnemonic('i');
        lblMonto2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblMonto2_actionPerformed(e);
                }
            });
        txtMonto2.setBounds(new Rectangle(170, 110, 80, 20));
        txtMonto2.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtMonto2_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtMonto2_keyTyped(e);
                }
            });
        lblDniCe.setText("DNI/CE:");
        lblDniCe.setBounds(new Rectangle(10, 20, 75, 15));
        lblDniCe.setForeground(new Color(255, 130, 14));
        lblDniCe.setMnemonic('d');
        lblDniCe.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblDniCe_actionPerformed(e);
                }
            });
        txtDniCe.setBounds(new Rectangle(170, 15, 85, 20));
        txtDniCe.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtDniCe_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtDniCe_keyTyped(e);
                }
            });
        txtDniCe.setLengthText(9);
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(240, 300, 90, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(340, 220, 90, 20));
        lblEsc.setBounds(new Rectangle(340, 300, 90, 20));
        jPanel2.setBounds(new Rectangle(20, 80, 410, 205));
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
        jPanel2.add(txtDniCe, null);
        jPanel2.add(lblDniCe, null);
        jPanel2.add(txtMonto2, null);
        jPanel2.add(lblMonto2, null);
        jPanel2.add(lblMonto1, null);
        jPanel2.add(cmbMoneda, null);
        jPanel2.add(txtMonto1, null);
        jPanel2.add(lblMoneda, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        inicializaTxtTarjeta();
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void inicializaTxtTarjeta() {
        FarmaUtility.moveFocus(txtDniCe);
    }

    private void this_windowOpened(WindowEvent e) {
        setearTipoDonacion();
        if (VariablesRecaudacion.vTipoCambioVenta == 0) {
            FarmaUtility.showMessage(this,
                                     "ATENCI�N: No se pudo obtener el tipo de cambio actual\nNo se puede continuar con la acci�n",
                                     null);
            cerrarVentana(false);
        } else {
            FarmaUtility.centrarVentana(this);
            FarmaUtility.moveFocus(txtDniCe);
        }
    }
    
    private void setearTipoDonacion() {
        if (tipoRecau.equals(ConstantsRecaudacion.TIPO_REC_TELETON)) {
            setTitle("Recaudaci\u00f3n Telet\u00f3n");
        } else if (tipoRecau.equals(ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA)) {
            setTitle("Recaudaci\u00f3n Cruz Roja");
        }
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void cmbEstCta_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtMonto1);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtMonto1_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtMonto2);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtMonto1_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMonto1, e);
    }

    private void lblMoneda_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbMoneda);
    }

    private void lblMonto1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMonto1);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    
    private boolean isMontosIguales(String valor1,
                                   String valor2){
        boolean flag = true;
        if (!valor1.equals(valor2)) {
            txtMonto1.setText("");
            txtMonto2.setText("");
            FarmaUtility.showMessage(this, 
                                     "Los montos ingresados no coinciden,\nvuelva a ingresar", 
                                     txtMonto1);
            flag = false;
        }
        return flag;
    }
    
    private boolean isDentroRangoDonacion(String monto) {
        boolean flag = true;
        double valor = 0.0;
        valor = FarmaUtility.getDecimalNumber(monto);
        try {
            if (valor < maxRecau || valor > minRecau) {
                flag = false;
                txtMonto1.setText("");
                txtMonto2.setText("");
                FarmaUtility.showMessage(this, 
                                         "Solo se puede donar montos entre " + minRecau + " y " + maxRecau, 
                                         txtMonto1);
            }
        } catch(Exception e) {
            flag = false;
            log.error(e.getMessage());
        } finally {
            return flag;            
        }
    }

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (isCamposCompletos()) {
                if(isMontosIguales(txtMonto1.getText(), 
                                    txtMonto2.getText())){
                    if (isDentroRangoDonacion(txtMonto1.getText().trim())) {
                        cobrarRecaudacion();
                    }                
                }            
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }
    
    private boolean isCamposCompletos() {
        boolean flag = true;
        if (txtDniCe.getText().trim().equals("")){
            FarmaUtility.showMessage(this, 
                                     "Ingresar DNI/CE",
                                     txtDniCe);
            flag = false;
        } else if(txtDniCe.getText().trim().length() < 8) {
            FarmaUtility.showMessage(this, 
                                     "Verificar DNI/CE, faltan d�gitos",
                                     txtDniCe);
            flag = false;
        }else if (txtMonto1.getText().trim().equals("")) {
            FarmaUtility.showMessage(this, 
                                     "Ingresar Monto",
                                     txtMonto1);
            flag = false;
        } else if (txtMonto2.getText().trim().equals("")) {
            FarmaUtility.showMessage(this, 
                                     "Reingresar Monto",
                                     txtMonto2);
            flag = false;
        }
        return flag;
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void cobrarRecaudacion() {
        
        String strEstCta = "";
        String strMonedaPago = "";
        String dniCe = txtDniCe.getText().trim();
        double dMonto = FarmaUtility.getDecimalNumber(txtMonto1.getText());
        double dMontoPagarSol = 0.00;
        double dMontoPagarDol = 0.00;
        double dTotalVenta = 0.00;

        if (cmbMoneda.getSelectedIndex() == 0) {
            strEstCta = ConstantsRecaudacion.EST_CTA_SOLES;
            strMonedaPago = FarmaConstants.MONEDA_SOLES;
            dMontoPagarSol = dMonto;
            dTotalVenta = dMontoPagarSol;
        } else if (cmbMoneda.getSelectedIndex() == 1) {
            strEstCta = ConstantsRecaudacion.EST_CTA_DOLARES;
            strMonedaPago = FarmaConstants.MONEDA_DOLARES;
            dMontoPagarDol = dMonto;
            dTotalVenta = dMontoPagarDol * VariablesRecaudacion.vTipoCambioVenta;
        }

        ArrayList<Object> arrayCabecera = new ArrayList<Object>();
        /*0*/arrayCabecera.add(FarmaVariables.vCodGrupoCia);
        /*1*/arrayCabecera.add(FarmaVariables.vCodCia);
        /*2*/arrayCabecera.add(FarmaVariables.vCodLocal);
        /*3*/arrayCabecera.add(dniCe); //PARA LA TELETON NO HAY TARJETA, SE PONE EL DNI
        /*4*/arrayCabecera.add(tipoRecau);
        /*5*/arrayCabecera.add("");
        /*6*/arrayCabecera.add(ConstantsRecaudacion.ESTADO_PENDIENTE);
        /*7*/arrayCabecera.add(strEstCta);
        /*8*/arrayCabecera.add("");
        /*9*/arrayCabecera.add(strMonedaPago);
        /*10*/arrayCabecera.add(String.valueOf(dMonto)); //Monto total(moneda original)
        /*11*/arrayCabecera.add(String.valueOf(dTotalVenta)); //Monto soles
        /*12*/arrayCabecera.add(String.valueOf(dTotalVenta)); //Monto minimo
        /*13*/arrayCabecera.add(VariablesRecaudacion.vTipoCambioVenta); //Tipo cambio
        /*14*/arrayCabecera.add(""); //Fecha Venc Tarj
        /*15*/arrayCabecera.add("");
        /*16*/arrayCabecera.add("");
        /*17*/arrayCabecera.add(ConstantsRecaudacion.FECHA_RCD);
        /*18*/arrayCabecera.add(FarmaVariables.vIdUsu);
        /*19*/arrayCabecera.add("");
        /*20*/arrayCabecera.add("");
        /*21*/arrayCabecera.add(""); //codigo de autorizacion
        /*22*/arrayCabecera.add(""); //mov de caja
        /*23*/arrayCabecera.add(""); //numero de pedido solo valido para VENTA CMR
        /*24*/arrayCabecera.add(""); //tipo producto servicio
        /*25*/arrayCabecera.add(""); //numero de recibo de pago

        String tmpCodigo = facadeRecaudacion.grabaCabeRecau(arrayCabecera,
                                                            dniCe);
        if (!tmpCodigo.equals("")) {
            if (facadeRecaudacion.cobrarRecaudacion(myFrameParent, tmpCodigo, arrayCabecera,
                                                    tipoRecau)) {
                cerrarVentana(true);
            }
        } else {
            FarmaUtility.showMessage(this, "Error al procesar el cobro.", null);
        }
    }

    private void lblMonto2_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(lblMonto2);
    }

    private void lblDniCe_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtDniCe);
    }

    private void txtDniCe_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbMoneda);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtMonto2_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void txtDniCe_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtDniCe, e);
    }

    private void txtMonto2_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMonto2, e);
    }

    public String getTipoRecau() {
        return tipoRecau;
    }

    public void setTipoRecau(String tipoRecau) {
        this.tipoRecau = tipoRecau;
    }

    public double getMinRecau() {
        return minRecau;
    }

    public void setMinRecau(double minRecau) {
        this.minRecau = minRecau;
    }

    public double getMaxRecau() {
        return maxRecau;
    }

    public void setMaxRecau(double maxRecau) {
        this.maxRecau = maxRecau;
    }
}


