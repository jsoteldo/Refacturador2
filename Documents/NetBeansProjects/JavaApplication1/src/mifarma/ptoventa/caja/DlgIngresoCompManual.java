package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgIngresoCompManual extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgIngresoCompManual.class);
    private static final long serialVersionUID = -2626325502788986022L;
    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelTitle pnlTitle = new JPanelTitle();
    private JButtonLabel lblTipoComprobante = new JButtonLabel();
    private JButtonLabel lblNroComprobante = new JButtonLabel();
    private JTextFieldSanSerif txtSerie = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtNroComprobante = new JTextFieldSanSerif();
    private JComboBox cmbTipoComp = new JComboBox();
    private JComboBox cmbSerie = new JComboBox();
    private JLabelFunction btnF11 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();
    private Frame MyParentFrame;
    private String eRCM = "";
    private String vRpta = "";
    private boolean estrcm = false;
    private boolean estvta = false;
    //private JTextField txtFecha = new JTextField();
    private String serie="";
    ArrayList paramSerie=null;
    public DlgIngresoCompManual() {
        this(null, "", false);
    }

    public DlgIngresoCompManual(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            MyParentFrame = parent;
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(419, 158));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ingrese Comprobante Manual");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }

            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jContentPane.setFocusable(false);
        pnlTitle.setBounds(new Rectangle(5, 10, 400, 95));
        pnlTitle.setBackground(Color.white);
        pnlTitle.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlTitle.setFocusable(false);
        lblTipoComprobante.setText("Tipo Comprobante:");
        lblTipoComprobante.setMnemonic('T');
        lblTipoComprobante.setBounds(new Rectangle(20, 20, 110, 15));
        lblTipoComprobante.setBackground(Color.white);
        lblTipoComprobante.setForeground(new Color(255, 90, 33));
        lblTipoComprobante.setFocusable(false);
        lblTipoComprobante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTipoComprobante_actionPerformed(e);
            }
        });
        txtNroComprobante.setBounds(new Rectangle(215, 55, 130, 20));
        lblNroComprobante.setText("Nro. Comprobante:");
        lblNroComprobante.setMnemonic('N');
        lblNroComprobante.setBounds(new Rectangle(20, 55, 105, 15));
        lblNroComprobante.setForeground(new Color(255, 90, 33));
        lblNroComprobante.setFocusable(false);
        lblNroComprobante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNroComprobante_actionPerformed(e);
            }
        });
        txtSerie.setBounds(new Rectangle(160, 55, 60, 20));
        txtSerie.setLengthText(3);
        //txtSerie.setLengthText(4); //LTAVARA 03.09.2014 se incrementa en 4 para la serie del documento electronico
        txtSerie.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtSerie_keyPressed(e);
            }


            public void keyTyped(KeyEvent e) {
                txtSerie_keyTyped(e);
            }
        });
        txtSerie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtSerie_actionPerformed(e);
            }
        });
        cmbSerie.setBounds(new Rectangle(160, 55, 60, 20));
       
        cmbSerie.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipoSerie_keyPressed(e);
            }
        });
        
        
        txtNroComprobante.setBounds(new Rectangle(235, 55, 145, 20));
        txtNroComprobante.setLengthText(7);
        //txtNroComprobante.setLengthText(8); //LTAVARA 03.09.2014 se incrementa en 8 para el correlativo del documento electronico
        txtNroComprobante.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNroComprobante_keyPressed(e);
            }


            public void keyTyped(KeyEvent e) {
                txtNroComprobante_keyTyped(e);
            }
        });


        cmbTipoComp.setBounds(new Rectangle(160, 20, 220, 20));
        cmbTipoComp.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipoComp_keyPressed(e);
            }
        });
        cmbTipoComp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmbTipoComp_actionPerformed(e);
            }
        });
        btnF11.setBounds(new Rectangle(5, 110, 117, 20));
        btnEsc.setBounds(new Rectangle(290, 110, 117, 19));
        btnF11.setText("[F11] Aceptar");
        btnF11.setFocusable(false);
        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);
        pnlTitle.add(cmbTipoComp, null);
        pnlTitle.add(txtNroComprobante, null);
       // pnlTitle.add(txtSerie, null);
        pnlTitle.add(cmbSerie, null);
        pnlTitle.add(lblNroComprobante, null);
        pnlTitle.add(lblTipoComprobante, null);
        jContentPane.add(btnF11, null);
        jContentPane.add(btnEsc, null);
        jContentPane.add(pnlTitle, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    private void initialize() {
        cargaCombo();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", cmbTipoComp);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(cmbTipoComp);
    }

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            VariablesPtoVenta.vIndVerReceMagis = "N"; //ASOSA - 09/07/2014
            this.setSerie(FarmaLoadCVL.getCVLCode("cmbSerie", cmbSerie.getSelectedIndex())); /// jvara 15.06.2017
            if (validarDatos()) {
                String vTipoComp = FarmaLoadCVL.getCVLCode("cmbComprobante", cmbTipoComp.getSelectedIndex());

                if (vTipoComp.trim().equalsIgnoreCase("01") || vTipoComp.trim().equalsIgnoreCase("02")) {
                    if (validaIngreso(vTipoComp,
                                       this.getSerie().trim(),
                                     // FarmaUtility.completeWithSymbol(txtSerie.getText().trim(), 3, "0", "I"),
                                      FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 7, "0",
                                                                      "I"))) {
                        VariablesCaja.pManualSerieComprobante =
                        this.getSerie().trim();
                               // FarmaUtility.completeWithSymbol(txtSerie.getText().trim(), 3, "0", "I");
                        VariablesCaja.pManualNumCompPago =
                                FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 7, "0", "I");
                        VariablesCaja.pManualTipCompPago = vTipoComp;
                        cerrarVentana(true);
                    } else
                        FarmaUtility.showMessage(this,
                                                 "Verifique la Serie y Comprobante Ingresado.\n." + "La Serie NO ES VALIDA �\n" +
                                "El Numero de Comprobante ya existe en el sistema.", txtSerie);
                } else
                    FarmaUtility.showMessage(this, "Solo se pueden Ingresar Factura � Boleta.", cmbTipoComp);
            } else
                FarmaUtility.showMessage(this, "Falta Ingresar Informaci�n.", cmbTipoComp);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private boolean validarDatos() {
        boolean flag = true;
        if (txtNroComprobante.getText().trim().length() == 0)
            return flag = false;

        if (cmbTipoComp.getSelectedObjects().length == 0)
            return flag = false;
        if (cmbSerie.getSelectedObjects().length == 0)
            return flag = false;
        
        // dubilluz 13.01.2015
        return validoNumeros();
        /*if(validoNumeros())
            return flag = false;*/

        //return flag;
    }
    
    public boolean validoNumeros(){
        //int vSerie = Integer.parseInt(txtSerie.getText().trim());
        int vSerie = Integer.parseInt(this.getSerie().trim());

        int vNum   = Integer.parseInt(txtNroComprobante.getText().trim());
        
        if(vSerie>0&&vNum>0)
            return true;
        else
            return false;
    }

    private void txtSerie_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            /* LTAVARA 12.09.2014
            * txtSerie.setText(FarmaUtility.caracterIzquierda(txtSerie.getText(),
                                                                //  3,
                                                                    4,//LTAVARA 03.09.2014 se incrementa en 4 para la serie del documento electronico
                                                                "0"));*/
            FarmaUtility.moveFocus(txtNroComprobante);
        }
        chkKeyPressed(e);
    }

    private void txtNroComprobante_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbTipoComp);
        }
        chkKeyPressed(e);
    }


    private void lblTipoComprobante_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipoComp);
    }

    private void lblNroComprobante_actionPerformed(ActionEvent e) {
      //  FarmaUtility.moveFocus(txtSerie);
      FarmaUtility.moveFocus(cmbSerie);
    }


    private void cargaCombo() {
        //FarmaLoadCVL.loadCVLfromArrays(cmbTipoComp,
        //                               ConstantsVentas.HASHTABLE_TIPOS_COMPROBANTES,
        //                               ConstantsVentas.TIPOS_COMPROBANTES_CODIGO,
        //                               ConstantsVentas.TIPOS_COMPROBANTES_DESCRIPCION,true);

        //LLEIVA 03-Feb-2014 Se realiza la carga desde la BD
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        // DUBILLUZ 13.01.2015
        FarmaLoadCVL.loadCVLFromSP(cmbTipoComp, "cmbComprobante", "PTOVENTA_ADMIN_IMP.IMP_LISTA_TIPOS_COMP_MANUAL(?)",
                                   parametros, true, true);
    }

    //CHUANES 31.03.2015--CARGA SERIE POR TIPO DE COMPROBANTE
    private void cargaComboSerie(String tipComprobante){
        //KMONCADA 17.07.2015 SE REALIZA EL CAMBIO  PARA QUE SE ACTUALICE LA LISTA MOSTRAR SEGUN LO SELECCIONADO
        ArrayList paramSerie = new ArrayList();
        paramSerie.add(FarmaVariables.vCodGrupoCia);
        paramSerie.add(FarmaVariables.vCodLocal);
        paramSerie.add(tipComprobante.trim());
        FarmaLoadCVL.loadCVLFromSP(cmbSerie, "cmbSerie", "PTOVENTA_ADMIN_IMP.IMP_LISTA_SERIE_COMP(?,?,?)",
                                   paramSerie, true, true);   
    }
    

    private void cmbTipoComp_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSerie.removeAllItems();
            String tipComprobante="";
            tipComprobante=FarmaLoadCVL.getCVLCode("cmbComprobante", cmbTipoComp.getSelectedIndex()).trim();
            cargaComboSerie(tipComprobante);
            FarmaUtility.moveFocus(cmbSerie);
            
        }
        chkKeyPressed(e);
    }
    private void cmbTipoSerie_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.setSerie(FarmaLoadCVL.getCVLCode("cmbSerie", cmbSerie.getSelectedIndex()));
                                  
            FarmaUtility.moveFocus(txtNroComprobante);  
        }
        chkKeyPressed(e);
    }


    private void txtSerie_keyTyped(KeyEvent e) {
        //FarmaUtility.admitirDigitos(txtSerie,e);
        FarmaUtility.admitirLetrasNumeros(txtSerie, e); //LTAVARA 03.09.2014 - serie alfanumerico para DE
    }

    private void txtNroComprobante_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtNroComprobante, e);
    }

    public boolean isEstrcm() {
        return estrcm;
    }

    public void setEstrcm(boolean estrcm) {
        this.estrcm = estrcm;
    }

    private void txtSerie_actionPerformed(ActionEvent e) {
    }

    private void cmbTipoComp_actionPerformed(ActionEvent e) {
    }

    public boolean validaIngreso(String pTipComp, String pSerie, String pNum) {
        boolean pValor = false;
        String pResultado = "";
        try {
            pResultado = DBCaja.getValidaCompManual(pTipComp, pSerie, pNum);
        } catch (Exception sqle) {
            log.error("", sqle);
        }
        if (pResultado.trim().equalsIgnoreCase("S"))
            pValor = true;

        return pValor;
    }


    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
}
