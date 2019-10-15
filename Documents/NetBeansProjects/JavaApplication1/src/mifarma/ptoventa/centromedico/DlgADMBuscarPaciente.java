package mifarma.ptoventa.centromedico;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;

import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComboBox;
import javax.swing.JDialog;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgADMBuscarPaciente extends JDialog {
    
    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */    
    
    private static final Logger log = LoggerFactory.getLogger(DlgADMBuscarPaciente.class);
    private static final long serialVersionUID = -2626325502788986022L;
    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelTitle pnlTitle = new JPanelTitle();
    private JButtonLabel lblTipDoc = new JButtonLabel();
    private JComboBox cmbTipDoc = new JComboBox();
    private JTextFieldSanSerif txtNumDoc = new JTextFieldSanSerif();
    private JButtonLabel lblNombre = new JButtonLabel();
    private JTextFieldSanSerif txtNombre = new JTextFieldSanSerif();
    private JButtonLabel lblApePaterno = new JButtonLabel();
    private JTextFieldSanSerif txtApePaterno = new JTextFieldSanSerif();
    private JButtonLabel lblApeMaterno = new JButtonLabel();
    private JTextFieldSanSerif txtApeMaterno = new JTextFieldSanSerif(); 
    private JLabelFunction btnF11 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();
    private Frame MyParentFrame;
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();

    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */
    public DlgADMBuscarPaciente() {
        super();
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgADMBuscarPaciente(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            MyParentFrame = parent;
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    
    /* ************************************************************************ */
    /*                              METODO jbInit                               */
    /* ************************************************************************ */


    private void jbInit() throws Exception {
        this.setSize(new Dimension(477, 303));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ingrese Datos del Paciente");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter(){
              public void windowOpened(WindowEvent e){
                this_windowOpened(e);
              }

              public void windowClosing(WindowEvent e){
                this_windowClosing(e);
              }
            });
        jContentPane.setFocusable(false);
        pnlTitle.setBounds(new Rectangle(20, 25, 420, 195));
        pnlTitle.setBackground(Color.white);
        //pnlTitle.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        pnlTitle.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlTitle.setFocusable(false);
        
        lblTipDoc.setText("Tipo Documento:");
        lblTipDoc.setMnemonic('D');
        lblTipDoc.setBounds(new Rectangle(20, 30, 120, 20));
        lblTipDoc.setBackground(Color.white);
        lblTipDoc.setForeground(new Color(255, 90, 33));
        lblTipDoc.setFocusable(false);
        lblTipDoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTipDoc_actionPerformed(e);
            }
        });
        
        cmbTipDoc.setBounds(new Rectangle(165, 30, 65, 20));
        cmbTipDoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //cmbTipDoc_actionPerformed(e);
                
            }
        });
        cmbTipDoc.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipDoc_keyPressed(e);
                
            }
        });
        
        txtNumDoc.setBounds(new Rectangle(240, 30, 155, 20));
        txtNumDoc.setLengthText(10);
        txtNumDoc.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNumDoc_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                //txtNumDoc_keyTyped(e);
            }
        });
        
        lblApePaterno.setText("Apellido Paterno:");
        lblApePaterno.setMnemonic('P');
        lblApePaterno.setBounds(new Rectangle(20, 70, 120, 20));
        lblApePaterno.setBackground(Color.white);
        lblApePaterno.setForeground(new Color(255, 90, 33));
        lblApePaterno.setFocusable(false);
        lblApePaterno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblApePaterno_actionPerformed(e);
            }
        });
        
        txtApePaterno.setBounds(new Rectangle(165, 70, 230, 20));
        txtApePaterno.setLengthText(30);
        txtApePaterno.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtApePaterno_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                //txtApePaterno_keyTyped(e);
            }
        });
        
        lblApeMaterno.setText("Apellido Materno:");
        lblApeMaterno.setMnemonic('M');
        lblApeMaterno.setBounds(new Rectangle(20, 110, 120, 20));
        lblApeMaterno.setBackground(Color.white);
        lblApeMaterno.setForeground(new Color(255, 90, 33));
        lblApeMaterno.setFocusable(false);
        lblApeMaterno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblApeMaterno_actionPerformed(e);
            }
        });
        
        txtApeMaterno.setBounds(new Rectangle(165, 110, 230, 20));
        txtApeMaterno.setLengthText(30);
        txtApeMaterno.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtApeMaterno_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                //txtApeMaterno_keyTyped(e);
            }
        });
        
        lblNombre.setText("Nombres:");
        lblNombre.setMnemonic('M');
        lblNombre.setBounds(new Rectangle(20, 150, 120, 20));
        lblNombre.setBackground(Color.white);
        lblNombre.setForeground(new Color(255, 90, 33));
        lblNombre.setFocusable(false);
        lblNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNombre_actionPerformed(e);
            }
        });
        
        
        txtNombre.setBounds(new Rectangle(165, 150, 230, 20));
        txtNombre.setLengthText(30);
        txtNombre.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNombre_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                //txtNombre_keyTyped(e);
            }
        });
        
        btnF11.setBounds(new Rectangle(20, 240, 117, 20));
        btnF11.setText("[F11] Aceptar");
        btnF11.setFocusable(false);
        btnEsc.setBounds(new Rectangle(325, 240, 117, 20));
        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);
        jPanelHeader1.setBounds(new Rectangle(125, 235, 1, 1));
        jLabelWhite1.setText("jLabelWhite1");
        pnlTitle.add(lblTipDoc, null);
        pnlTitle.add(cmbTipDoc, null);
        pnlTitle.add(txtNumDoc, null);
        pnlTitle.add(lblApePaterno, null);
        pnlTitle.add(txtApePaterno, null);
        pnlTitle.add(lblApeMaterno, null);
        pnlTitle.add(txtApeMaterno, null);
        pnlTitle.add(lblNombre, null);
        pnlTitle.add(txtNombre, null);
        jContentPane.add(jPanelHeader1, null);
        jContentPane.add(pnlTitle, null);
        jContentPane.add(btnF11, null);
        jContentPane.add(btnEsc, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }
    
    /* ************************************************************************ */
    /*                           METODO initialize                              */
    /* ************************************************************************ */

    private void initialize(){
          //initTable();
          cargaCombo();
      FarmaVariables.vAceptar = false;
    }
    
    /* ************************************************************************ */
    /*                         METODOS INICIALIZACION                           */
    /* ************************************************************************ */

    /*private void initTable() {
          tableModel =
                  new FarmaTableModel(ConstantsPtoVenta.columnsListaFiltro, ConstantsPtoVenta.defaultValuesListaFiltro,
                                      0);
          FarmaUtility.initSimpleList(tblFiltro, tableModel, ConstantsPtoVenta.columnsListaFiltro);
      }*/
    
    /* ************************************************************************ */
    /*                           METODOS DE EVENTOS                             */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e){
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(cmbTipDoc);
    }

    private void this_windowClosing(WindowEvent e){
      FarmaUtility.showMessage(this,"Debe presionar la tecla ESC para cerrar la ventana.",cmbTipDoc);
    }
    
    
    /* ************************************************************************ */
    /*                      METODOS AUXILIARES DE EVENTOS                       */
    /* ************************************************************************ */

    private void cargaCombo() {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbTipDoc, "cmbTipoDocumento", "CC_CME_ATENCION_MEDICA.CME_LISTA_TIPOS_DOCUMENTOS(?)",
                                   parametros, true, true);
    }

    private void chkKeyPressed(KeyEvent e){
      if (UtilityPtoVenta.verificaVK_F11(e)){
                  //log.info("Se presiono F11"); 
                  DlgADMListaPacientes dlglistPac = new DlgADMListaPacientes(MyParentFrame,"",true,
                                                                             new VtaCompAtencionMedica(),new VtaPedidoAtencionMedica(),"C");
                  dlglistPac.setLocationRelativeTo(MyParentFrame);
                  dlglistPac.setVisible(true);
                  
                  
      }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
        cerrarVentana(false);
      }
    }
    

    
    private void cmbTipDoc_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    private void txtNumDoc_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    private void txtApePaterno_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    private void txtApeMaterno_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    private void txtNombre_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    
    
    private void lblTipDoc_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipDoc);
    }

    private void lblApePaterno_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtApePaterno);
    }
    
    private void lblApeMaterno_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtApeMaterno);
    }

    private void lblNombre_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtNombre);
    }
    
    


    private void cerrarVentana(boolean pAceptar){
      FarmaVariables.vAceptar = pAceptar;
      this.setVisible(false);
      this.dispose();
    }

    /* ************************************************************************ */
    /*                      METODOS DE LOGICA DE NEGOCIO                        */
    /* ************************************************************************ */
    
    
    
    
}
