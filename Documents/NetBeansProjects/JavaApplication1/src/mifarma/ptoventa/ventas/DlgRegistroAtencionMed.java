package mifarma.ptoventa.ventas;


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

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.campAcumulada.reference.DBCampAcumulada;
import mifarma.ptoventa.cliente.DlgRegistroCliente;
import mifarma.ptoventa.fidelizacion.reference.ConstantsFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.UtilityFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.DBVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2016 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicación : DlgRegistroAtencionMed.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * ERIOS      23.08.2016   Creación<br>
 * <br>
 * @author Edgar Rios Navarro<br>
 * @version 1.0<br>
 *
 */
public class DlgRegistroAtencionMed extends JDialog {

    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(DlgRegistroAtencionMed.class);

    private Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JButton btnDni = new JButton();
    private JTextFieldSanSerif txtDni = new JTextFieldSanSerif();
    private JTextField txtPaciente = new JTextField();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();

    private String strDni = "";
    private String strPaciente = "";
    private String codTipoDocumento = "";
    private Map<String, String> parametros;

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgRegistroAtencionMed() {
        this(null, "", false);
    }

    public DlgRegistroAtencionMed(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(498, 183));
        this.getContentPane().setLayout(borderLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.white);
        this.setTitle("Registro de Atencion Medica");
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
        jPanel1.setFocusable(false);
        jPanel2.setBounds(new Rectangle(15, 10, 465, 105));
        jPanel2.setBackground(new Color(43, 141, 39));
        jPanel2.setLayout(null);

        jPanel2.setForeground(new Color(0, 132, 0));
        jPanel2.setFocusable(false);
        btnDni.setText("* Doc. Identidad :");
        btnDni.setBackground(Color.white);
        btnDni.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDni.setBorderPainted(false);
        btnDni.setContentAreaFilled(false);
        btnDni.setDefaultCapable(false);
        btnDni.setFocusPainted(false);
        btnDni.setFont(new Font("SansSerif", 1, 11));
        btnDni.setForeground(Color.white);
        btnDni.setHorizontalAlignment(SwingConstants.LEFT);
        btnDni.setMnemonic('D');
        btnDni.setRequestFocusEnabled(false);
        btnDni.setBounds(new Rectangle(10, 15, 95, 20));

        btnDni.setFocusable(false);
        btnDni.setActionCommand("* Doc. Ident. :");
        btnDni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDni_actionPerformed(e);
            }
        });


        txtDni.setBounds(new Rectangle(10, 40, 100, 20));
        
        txtDni.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtDni_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                //txtDni_keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {
                txtDni_keyTyped(e);
            }
        });
        txtPaciente.setBounds(new Rectangle(115, 40, 295, 20));
        txtPaciente.setEditable(false);
        txtPaciente.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtPaciente_keyPressed(e);
            }
        });
        
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(385, 125, 95, 25));
        lblEsc.setFocusable(false);
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(280, 125, 95, 25));

        lblF11.setFocusable(false);
        jPanel2.add(txtPaciente, null);
        jPanel2.add(txtDni, null);
        jPanel2.add(btnDni, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(lblEsc, null);
        jPanel1.add(lblF11, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void txtPaciente_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //FarmaUtility.moveFocus(txtTelefono);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtDni_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            FarmaVariables.vAceptar = false;
            mostrarBusquedaPaciente();

        } else {
            chkKeyPressed(e);
        }
        validarBotonF11();
    }

    private void btnDni_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtDni);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtDni);
        //ERIOS 2.4.2 Automatico para fidelizados
        if (!VariablesFidelizacion.vDniCliente.equals("")) {
            codTipoDocumento = validarDocIdentidad(VariablesFidelizacion.vDniCliente.trim());
            if(codTipoDocumento != null && codTipoDocumento.trim().length() >= 0){
            //KMONCADA 05.06.2015 VALIDA FORMATO DE DOCUMENTO DE IDENTIDAD
                txtDni.setText(VariablesFidelizacion.vDniCliente);
                mostrarBusquedaPaciente();
            }
        }
        validarBotonF11();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (!txtPaciente.getText().trim().equals("")){                
                if (txtDni.getText().equalsIgnoreCase(this.strDni)){
                    if(insertaDatosVenta()){
                        cerrarVentana(true);
                    }                    
                } else{
                    FarmaUtility.showMessage(this, "ATENCIÓN: El dato de DNI ha sido modificado", null);
                }
            } else{
                FarmaUtility.showMessage(this, "ERROR: Alguno de los campos se encuentra vacío", null);
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

    private void mostrarBusquedaPaciente() {
        boolean tipoDocumentoValidado = false;
        ArrayList<Object> resultado = new ArrayList<Object>();

        String vdni = txtDni.getText().trim();
        // KMONCADA 01.10.2015 VALIDARA EL TIPO DE DOCUMENTO 
        if(codTipoDocumento==null || (codTipoDocumento!=null && codTipoDocumento.trim().length()==0)){
            codTipoDocumento = validarDocIdentidad(vdni);
        }
        
        if(codTipoDocumento!=null && codTipoDocumento.trim().length()>0){
            tipoDocumentoValidado = true;
        }
        
        try {
            if(!vdni.equals("")){
                DBCampAcumulada.getDatosExisteDNI(resultado, vdni);
            }
            parametros = new HashMap<String, String>();
            if (resultado.size() > 0) {
                ArrayList array = (ArrayList)resultado.get(0);
                this.strDni = array.get(0).toString();
                this.strPaciente =
                        array.get(1).toString() + " " + array.get(2).toString() + " " + array.get(3).toString();
                
                txtPaciente.setText(this.strPaciente.toUpperCase().trim());
                
                parametros.put("dni", array.get(0).toString());
                parametros.put("nombre", array.get(1).toString());
                parametros.put("apePaterno", array.get(2).toString());
                parametros.put("apeMaterno", array.get(3).toString());
                // KMONCADA 01.01.2015 REGISTRARA EL TIPO DE DOCUMENTO DEL CLIENTE A REGISTRAR
                parametros.put("tipoDoc", codTipoDocumento);
            } else {
                FarmaUtility.showMessage(this, "No existe información del paciente.", null);
                DlgRegistroCliente dlgRegistroCliente = new DlgRegistroCliente(myParentFrame, "", true);
                dlgRegistroCliente.setDNI(vdni);
                dlgRegistroCliente.setCodTipoDocumento(codTipoDocumento==null?"":codTipoDocumento);
                dlgRegistroCliente.setPIndAtencionMedica(true);
                dlgRegistroCliente.setVisible(true);

                if (FarmaVariables.vAceptar) {
                    parametros = dlgRegistroCliente.recuperarPaciente();
                    this.strDni = vdni;
                    this.strPaciente =
                            parametros.get("nombre") + " " + parametros.get("apePaterno") + " " + parametros.get("apeMaterno");
                    
                    txtPaciente.setText(this.strPaciente.toUpperCase().trim());
                }
            }
            
        } catch (Exception ex) {
            log.error("", ex);
        }
        
    }

    private boolean insertaDatosVenta() {
        boolean vRetorno = false;
        try {
            DBVentas.insertaDatosVentaAtencionMed(this.parametros);
            vRetorno = true;
        } catch (SQLException sql) {
            log.error("", sql);
        }
        return vRetorno;
    }

    private void txtDni_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtDni, e);
    }

    private void validarBotonF11() {
        if (!txtPaciente.getText().equalsIgnoreCase("")){
            lblF11.setEnabled(true);
        } else{
            lblF11.setEnabled(false);
        }
    }
    
    private String validarDocIdentidad(String nroDocumentoIdentidad){
        return UtilityFidelizacion.determinarTipoDocIndentidad(nroDocumentoIdentidad, ConstantsFidelizacion.COD_TIPO_DOC_DNI, ConstantsFidelizacion.COD_TIPO_DOC_CARNET_EXTRANJERIA);
    }
}
