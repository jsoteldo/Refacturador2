package mifarma.ptoventa.tomainventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
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

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgIngresoCantidadToma extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgIngresoCantidadToma.class);
    public boolean firstTime = false;
    int longitudTexto = 0;
    Frame myParentFrame;
    private JPanelWhite jContentPane = new JPanelWhite();

    private BorderLayout borderLayout2 = new BorderLayout();

    private JPanelWhite jPanelWhite1 = new JPanelWhite();

    private JLabel jLabel1 = new JLabel();

    private JLabel jLabel2 = new JLabel();

    private JLabel lblCodigo = new JLabel();

    private JLabel lblDescripcion = new JLabel();

    private JLabel jLabel5 = new JLabel();

    private JLabel lblUnidadPresentacion = new JLabel();

    private JLabel jLabel7 = new JLabel();

    private JLabel lblLaboratorio = new JLabel();

    private JButtonLabel jButtonLabel1 = new JButtonLabel();
    private JButtonLabel jButtonLabelAnaquel = new JButtonLabel();

    private JTextFieldSanSerif txtEntero = new JTextFieldSanSerif();

    private JTextFieldSanSerif txtAnaquel = new JTextFieldSanSerif();

    private JLabelFunction jLabelFunction1 = new JLabelFunction();

    private JLabelFunction jLabelFunction2 = new JLabelFunction();
    private JLabel lblTValorFraccion = new JLabel();
    private JLabel lblValorFraccion = new JLabel();
    private JTextFieldSanSerif txtFraccion = new JTextFieldSanSerif();
    private JButtonLabel lblFraccion = new JButtonLabel();
    private JLabel lblUnidadVenta = new JLabel();
    private JLabel jLabel6 = new JLabel();

    private JTextArea lblMensaje = new JTextArea();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgIngresoCantidadToma() {
        this(null, "", false);
    }

    public DlgIngresoCantidadToma(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(417, 342));
        this.getContentPane().setLayout(borderLayout2);
        this.setTitle("Ingreso de Cantidad");

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanelWhite1.setBounds(new Rectangle(20, 15, 380, 255));
        jPanelWhite1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanelWhite1.setLayout(null);

        jLabel1.setText("Codigo :");
        jLabel1.setBounds(new Rectangle(25, 10, 50, 15));
        jLabel1.setFont(new Font("SansSerif", 1, 11));
        jLabel1.setForeground(new Color(43, 141, 39));

        lblCodigo.setText("914005");
        lblCodigo.setBounds(new Rectangle(135, 10, 60, 15));
        lblCodigo.setFont(new Font("SansSerif", 1, 11));
        lblCodigo.setForeground(new Color(255, 130, 14));

        jLabel2.setText("Descripcion :");
        jLabel2.setBounds(new Rectangle(25, 35, 85, 15));
        jLabel2.setFont(new Font("SansSerif", 1, 11));
        jLabel2.setForeground(new Color(43, 141, 39));

        lblDescripcion.setText("ACTIVEL SINGLES");
        lblDescripcion.setBounds(new Rectangle(135, 35, 205, 15));
        lblDescripcion.setFont(new Font("SansSerif", 1, 11));
        lblDescripcion.setForeground(new Color(255, 130, 14));

        jLabel5.setText("Unid de Presentacion :");
        jLabel5.setBounds(new Rectangle(25, 60, 125, 15));
        jLabel5.setFont(new Font("SansSerif", 1, 11));
        jLabel5.setForeground(new Color(43, 141, 39));

        lblUnidadPresentacion.setText("CJA/20");
        lblUnidadPresentacion.setBounds(new Rectangle(170, 60, 170, 15));
        lblUnidadPresentacion.setFont(new Font("SansSerif", 1, 11));
        lblUnidadPresentacion.setForeground(new Color(255, 130, 14));

        jLabel6.setText("Unid de Venta :");
        jLabel6.setBounds(new Rectangle(25, 85, 125, 15));
        jLabel6.setFont(new Font("SansSerif", 1, 11));
        jLabel6.setForeground(new Color(43, 141, 39));

        lblUnidadVenta.setText("CJA/20");
        lblUnidadVenta.setBounds(new Rectangle(120, 85, 170, 15));
        lblUnidadVenta.setFont(new Font("SansSerif", 1, 11));
        lblUnidadVenta.setForeground(new Color(255, 130, 14));

        lblValorFraccion.setText("Valor Fraccion :");
        lblValorFraccion.setBounds(new Rectangle(25, 110, 85, 15));
        lblValorFraccion.setFont(new Font("SansSerif", 1, 11));
        lblValorFraccion.setForeground(new Color(43, 141, 39));

        lblTValorFraccion.setText("CJA/20");
        lblTValorFraccion.setBounds(new Rectangle(135, 110, 170, 15));
        lblTValorFraccion.setFont(new Font("SansSerif", 1, 11));
        lblTValorFraccion.setForeground(new Color(255, 130, 14));

        jLabel7.setText("Laboratorio :");
        jLabel7.setBounds(new Rectangle(25, 135, 80, 15));
        jLabel7.setFont(new Font("SansSerif", 1, 11));
        jLabel7.setForeground(new Color(43, 141, 39));

        lblLaboratorio.setText("3M PERU S.A.");
        lblLaboratorio.setBounds(new Rectangle(135, 135, 240, 15));
        lblLaboratorio.setFont(new Font("SansSerif", 1, 11));
        lblLaboratorio.setForeground(new Color(255, 130, 14));

        lblMensaje.setText("[POLITICA DE CAMBIO]");
        lblMensaje.setBounds(new Rectangle(10, 155, 360, 30));
        lblMensaje.setFont(new Font("SansSerif", 1, 12));
        lblMensaje.setForeground(Color.red);
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblMensaje.setLineWrap(true);

        jButtonLabel1.setText("Entero : ");
        jButtonLabel1.setBounds(new Rectangle(20, 215, 55, 20));
        jButtonLabel1.setForeground(new Color(255, 130, 14));
        jButtonLabel1.setMnemonic('e');
        jButtonLabel1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonLabel1_actionPerformed(e);
            }
        });

        jButtonLabelAnaquel.setText("Anaquel : ");
        jButtonLabelAnaquel.setBounds(new Rectangle(20, 180, 55, 20));
        jButtonLabelAnaquel.setForeground(new Color(255, 130, 14));
        jButtonLabelAnaquel.setMnemonic('e');
        jButtonLabelAnaquel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonLabel1_actionPerformed(e);
            }
        });
        txtEntero.setBounds(new Rectangle(80, 215, 60, 20));
        txtEntero.setDocument(new FarmaLengthText(5));
        txtEntero.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtEntero_keyPressed(e);
            }


            public void keyTyped(KeyEvent e) {
                txtCantidad_keyTyped(e);
            }

        });


        txtEntero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtEntero_actionPerformed(e);
            }
        });
        txtAnaquel.setBounds(new Rectangle(80, 180, 60, 20));
        txtAnaquel.setFocusTraversalPolicyProvider(true);
        txtAnaquel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtAnaquel_keyPressed(e);
            }
        });
        txtAnaquel.setDocument(new FarmaLengthText(5));
        txtAnaquel.setEnabled(false);

        jLabelFunction1.setBounds(new Rectangle(170, 280, 105, 20));
        jLabelFunction1.setText("[ Enter ] Aceptar");
        jLabelFunction1.setBackground(SystemColor.window);
        jLabelFunction1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelFunction1.setFont(new Font("SansSerif", 1, 11));
        jLabelFunction1.setForeground(new Color(32, 105, 29));
        jLabelFunction2.setBounds(new Rectangle(285, 280, 115, 20));
        jLabelFunction2.setText("[ ESCAPE ] Cerrar");


        jLabelFunction2.setBackground(SystemColor.window);
        jLabelFunction2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelFunction2.setFont(new Font("SansSerif", 1, 11));
        jLabelFunction2.setForeground(new Color(32, 105, 29));
        txtFraccion.setBounds(new Rectangle(275, 215, 60, 20));
        txtFraccion.setDocument(new FarmaLengthText(5));
        txtFraccion.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFraccion_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtCantidad_keyTyped(e);
            }
        });
        lblFraccion.setText("Fraccion :");
        lblFraccion.setBounds(new Rectangle(205, 215, 65, 20));
        lblFraccion.setForeground(new Color(255, 130, 14));
        lblFraccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonLabel1_actionPerformed(e);
            }
        });


        jPanelWhite1.add(jLabel6, null);
        jPanelWhite1.add(lblUnidadVenta, null);
        jPanelWhite1.add(lblFraccion, null);
        jPanelWhite1.add(txtFraccion, null);
        jPanelWhite1.add(lblValorFraccion, null);
        jPanelWhite1.add(lblTValorFraccion, null);
        jPanelWhite1.add(txtEntero, null);
        jPanelWhite1.add(txtAnaquel, null);
        jPanelWhite1.add(jButtonLabel1, null);
        jPanelWhite1.add(jButtonLabelAnaquel, null);
        jPanelWhite1.add(lblLaboratorio, null);
        jPanelWhite1.add(jLabel7, null);
        jPanelWhite1.add(lblUnidadPresentacion, null);
        jPanelWhite1.add(jLabel5, null);
        jPanelWhite1.add(lblDescripcion, null);
        jPanelWhite1.add(lblCodigo, null);
        jPanelWhite1.add(jLabel2, null);
        jPanelWhite1.add(jLabel1, null);
        jPanelWhite1.add(lblMensaje, null);
        jContentPane.add(jLabelFunction2, null);
        jContentPane.add(jLabelFunction1, null);
        jContentPane.add(jPanelWhite1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        mostrarDatos();
        txtAnaquel.setText("D");
        if (VariablesTomaInv.vFraccion.equalsIgnoreCase("1")) {
            txtFraccion.setEditable(false);
            txtAnaquel.setEditable(false);
            txtAnaquel.setText("D");
            txtFraccion.setText("0");

        }
        FarmaUtility.moveFocus(txtEntero);
    }

    private void txtCantidad_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtEntero, e);
    }

    private void txtFraccion_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            longitudTexto = txtFraccion.getText().length();
            if (longitudTexto > 2) {
                DlgAvisoCargaTomaInv dlgAviso = new DlgAvisoCargaTomaInv(myParentFrame, "Mensaje del sistema", true);
                dlgAviso.setAviso("<html><body>Esta seguro de ingresar: " + txtFraccion.getText() +
                                  " Consulta! </body></html>");
                dlgAviso.setVisible(true);
                if (DlgAvisoCargaTomaInv.aceptarTransaccion) {
                    chkKeyPressed(e);
                } else {

                    txtFraccion.selectAll();
                    FarmaUtility.moveFocus(txtFraccion);
                    return;
                }
            } else {
                chkKeyPressed(e);
            }
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtEntero_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            longitudTexto = txtEntero.getText().length();
            int cantidad = Integer.parseInt(txtEntero.getText());
            if (longitudTexto > 2 && datosValidados()) {
                DlgAvisoCargaTomaInv dlgAviso = new DlgAvisoCargaTomaInv(myParentFrame, "Mensaje del sistema", true);
                dlgAviso.setAviso("<html><body>Esta seguro de ingresar: " + txtEntero.getText() +
                                  " Consulta! </body></html>");
                dlgAviso.setVisible(true);

                if (DlgAvisoCargaTomaInv.aceptarTransaccion) {
                    if (!txtFraccion.isEditable()) {
                        chkKeyPressed(e);
                    } else {
                        FarmaUtility.moveFocus(txtFraccion);
                    }
                } else {
                    txtEntero.selectAll();
                    txtEntero.requestFocus();
                }
            } else if (longitudTexto < 3) {
                if (!txtFraccion.isEditable()) {
                    chkKeyPressed(e);
                } else {
                    FarmaUtility.moveFocus(txtFraccion);
                }
            } else if (cantidad < 0) {
                FarmaUtility.showMessage(this, "Por favor, ingrese una Cantidad valida !!!", txtFraccion);
            }
        } else
            chkKeyPressed(e);
    }


    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            e.consume();
            this.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (datosValidados()) {
                try {
                    ArrayList<String> lista = new ArrayList<String>();
                    DBTomaInv.ListaAnaquelesTomaInventario(lista);
                    log.debug("entero : " + lista);
                    if (lista.toString().contains(txtAnaquel.getText())) {
                        log.debug("Contiene la D");
                        String registroExiste = "S";
                        ingresarCantidad(registroExiste);
                    }

                    else if (!lista.toString().contains(txtAnaquel.getText())) {
                        String registroExiste = "N";
                        ingresarCantidad(registroExiste);
                    }

                    FarmaUtility.aceptarTransaccion();
                    cerrarVentana(true);
                } catch (SQLException sql) {
                    FarmaUtility.liberarTransaccion();
                    log.error("", sql);
                    FarmaUtility.showMessage(this, "Ocurrió un error al registrar la cantidad : \n" +
                            sql.getMessage(), txtEntero);
                    cerrarVentana(false);
                }
            }

        }
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void mostrarDatos() {
        lblCodigo.setText(VariablesTomaInv.vCodProd);
        lblDescripcion.setText(VariablesTomaInv.vDesProd);
        lblUnidadPresentacion.setText(VariablesTomaInv.vUnidPresentacion);
        lblUnidadVenta.setText(VariablesTomaInv.vUnidVta);
        lblLaboratorio.setText(VariablesTomaInv.vNomLab);
        txtEntero.setText(VariablesTomaInv.vCantEntera.trim());
        txtFraccion.setText(VariablesTomaInv.vCantFraccion.trim());
        lblTValorFraccion.setText(VariablesTomaInv.vFraccion.trim());
        // txtAnaquel.setText(VariablesTomaInv.Vanaquel.trim());
        // KMONCADA 24.02.2016 MENSAJE DE POLITICA DE CAMBIO
        lblMensaje.setText(DBInventario.obtenerMensajePoliticaCambio(VariablesTomaInv.vCodProd));
    }

    private void ingresarCantidad(String anaquelverifica) throws SQLException {
        int entero = Integer.parseInt(txtEntero.getText().trim());
        int fraccion = Integer.parseInt(txtFraccion.getText().trim());
        log.debug("entero : " + entero);
        log.debug("fraccion : " + fraccion);
        int cantidad = entero * Integer.parseInt(VariablesTomaInv.vFraccion) + fraccion;
        String cantidadCompleta = "" + cantidad;
        String anaquel = "" + txtAnaquel.getText().trim();


        log.debug("cantidad : " + cantidadCompleta);
        log.debug("Anaquel : " + anaquel);
        DBTomaInv.ingresaCantidadProdInv(cantidadCompleta, anaquel, anaquelverifica,VariablesTomaInv.vFraccion.trim());
        FarmaUtility.aceptarTransaccion();
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        if (pAceptar) {
            this.dispose();
        }
    }

    private boolean datosValidados() {

        boolean rpta = true;
        if (txtEntero.getText().trim().length() == 0) {
            rpta = false;
            FarmaUtility.showMessage(this, "Ingrese la cantidad entera", txtEntero);
        } else if (txtFraccion.getText().trim().length() == 0 && txtFraccion.isEditable()) {
            rpta = false;
            FarmaUtility.moveFocus(txtFraccion);
            FarmaUtility.showMessage(this, "Ingrese la cantidad fraccion", txtFraccion);
        } else if (txtFraccion.getText().trim().equalsIgnoreCase(VariablesTomaInv.vFraccion)) {
            rpta = false;
            FarmaUtility.moveFocus(txtFraccion);
            FarmaUtility.showMessage(this, "La cantidad de Fraccion no puede ser igual al Valor de Fraccion",
                                     txtFraccion);
        } else if (Integer.parseInt(txtFraccion.getText().trim()) > Integer.parseInt(VariablesTomaInv.vFraccion)) {
            rpta = false;
            FarmaUtility.moveFocus(txtFraccion);
            FarmaUtility.showMessage(this, "La cantidad de Fraccion ingresada no puede ser mayor al valor de Fraccion",
                                     txtFraccion);
        }
        return rpta;
    }

    private void jButtonLabel1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtEntero);
    }

    private void txtAnaquel_keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            FarmaUtility.moveFocus(txtEntero);
        }


    }

    private void txtEntero_actionPerformed(ActionEvent e) {
    }


}
