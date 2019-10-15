package mifarma.ptoventa.centromedico;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;

import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
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

import java.sql.SQLException;

import java.text.DecimalFormat;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.text.JTextComponent;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;
import mifarma.ptoventa.centromedico.domain.historiaclinica.CmeAtencionMedicaTri;
import mifarma.ptoventa.centromedico.reference.ConstantsCentroMedico;
import mifarma.ptoventa.centromedico.reference.UtilityAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgADMRegistroTriaje extends JDialog {
    
    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */    
    
    private static final Logger log = LoggerFactory.getLogger(DlgADMRegistroTriaje.class);
    private static final long serialVersionUID = -2626325502788986022L;
    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelTitle pnlTitle = new JPanelTitle();
    
    private JButtonLabel lblPArterial = new JButtonLabel();
    private JTextFieldSanSerif txtPArterial = new JTextFieldSanSerif();
    private JButtonLabel lblSlash = new JButtonLabel();
    private JTextFieldSanSerif txtMMMercurio = new JTextFieldSanSerif();
    private JButtonLabel lblMMMercurio= new JButtonLabel();
    private JButtonLabel lblFRespiratoria = new JButtonLabel();
    private JTextFieldSanSerif txtFRespiratoria = new JTextFieldSanSerif();
    private JButtonLabel lblFRespiratoriaX = new JButtonLabel();
    private JButtonLabel lblFCardiaca = new JButtonLabel();
    private JTextFieldSanSerif txtFCardiaca = new JTextFieldSanSerif();
    private JButtonLabel lblFCardiacaX = new JButtonLabel();
    private JButtonLabel lblTemperatura = new JButtonLabel();
    private JTextFieldSanSerif txtTemperatura = new JTextFieldSanSerif();
    private JButtonLabel lblTemperaturaC = new JButtonLabel();
    private JButtonLabel lblPeso = new JButtonLabel();
    private JTextFieldSanSerif txtPeso = new JTextFieldSanSerif();
    private JButtonLabel lblPesoKg = new JButtonLabel();
    private JButtonLabel lblTalla = new JButtonLabel();
    private JTextFieldSanSerif txtTalla = new JTextFieldSanSerif();
    private JButtonLabel lblTallaCms = new JButtonLabel();
    
    
    private JLabelFunction btnF11 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();
    private Frame MyParentFrame;
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();
    
    private CmeAtencionMedicaTri atenMedicaTri;
    private String nroSolicitud;
    private JTextArea jTextArea1 = new JTextArea();

    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */
    public DlgADMRegistroTriaje() {
        super();
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgADMRegistroTriaje(Frame parent, String title, boolean modal,String nroSolicitud) {
        super(parent, title, modal);
        try {
            this.nroSolicitud=nroSolicitud;
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
        this.setSize(new Dimension(477, 399));
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
        pnlTitle.setBounds(new Rectangle(20, 25, 420, 280));
        pnlTitle.setBackground(Color.white);
        //pnlTitle.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        pnlTitle.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlTitle.setFocusable(false);
        
        
        /*** fila 1 ***/
        lblPArterial.setText("P. Arterial:");
        //lblPArterial.setMnemonic('P');
        lblPArterial.setBounds(new Rectangle(20, 30, 120, 20));
        lblPArterial.setBackground(Color.white);
        lblPArterial.setForeground(new Color(255, 90, 33));
        lblPArterial.setFocusable(false);
        lblPArterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblPArterial_actionPerformed(e);
            }
        });

        txtPArterial.setName("Presión Arterial 1");
        txtPArterial.setBounds(new Rectangle(165, 30, 60, 20));
        txtPArterial.setLengthText(3);
        txtPArterial.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtPArterial_keyPressed(e);
                }
            public void keyTyped(KeyEvent e) {
                //txtNumDoc_keyTyped(e);
                    txtPArterial_keyTyped(e);
                }
        });
        
        
        lblSlash.setText("/");
        //lblSlash.setMnemonic('P');
        lblSlash.setBounds(new Rectangle(235, 30, 15, 20));
        lblSlash.setBackground(Color.white);
        lblSlash.setForeground(new Color(255, 90, 33));
        lblSlash.setFocusable(false);
        lblSlash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblSlash_actionPerformed(e);
            }
        });

        txtMMMercurio.setName("Presión Arterial 2");
        txtMMMercurio.setBounds(new Rectangle(250, 30, 60, 20));
        txtMMMercurio.setLengthText(3);
        txtMMMercurio.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtMMMercurio_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                txtMMMercurio_keyTyped(e);
            }
        });
        
        lblMMMercurio.setText("MMHG.");
        //lblMMMercurio.setMnemonic('P');
        lblMMMercurio.setBounds(new Rectangle(320, 30, 55, 20));
        lblMMMercurio.setBackground(Color.white);
        lblMMMercurio.setForeground(new Color(255, 90, 33));
        lblMMMercurio.setFocusable(false);
        lblMMMercurio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblMMMercurio_actionPerformed(e);
            }
        });
        

        /*** fila 2 ***/
        lblFRespiratoria.setText("F. Respiratoria:");
        //lblFRespiratoria.setMnemonic('P');
        lblFRespiratoria.setBounds(new Rectangle(20, 70, 120, 20));
        lblFRespiratoria.setBackground(Color.white);
        lblFRespiratoria.setForeground(new Color(255, 90, 33));
        lblFRespiratoria.setFocusable(false);
        lblFRespiratoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFRespiratoria_actionPerformed(e);
            }
        });
        
        txtFRespiratoria.setName("Frecuencia Respiratoria");
        txtFRespiratoria.setBounds(new Rectangle(165, 70, 60, 20));
        txtFRespiratoria.setLengthText(3);
        txtFRespiratoria.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFRespiratoria_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                txtFRespiratoria_keyTyped(e);
            }
        });
        
        lblFRespiratoriaX.setText("X'.");
        lblFRespiratoriaX.setMnemonic('P');
        lblFRespiratoriaX.setBounds(new Rectangle(235, 70, 30, 20));
        lblFRespiratoriaX.setBackground(Color.white);
        lblFRespiratoriaX.setForeground(new Color(255, 90, 33));
        lblFRespiratoriaX.setFocusable(false);
        lblFRespiratoriaX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFRespiratoriaX_actionPerformed(e);
            }
        });
        
        /*** fila 3 ***/
        lblFCardiaca.setText("F. Cardiaca:");
        //lblFCardica.setMnemonic('M');
        lblFCardiaca.setBounds(new Rectangle(20, 110, 120, 20));
        lblFCardiaca.setBackground(Color.white);
        lblFCardiaca.setForeground(new Color(255, 90, 33));
        lblFCardiaca.setFocusable(false);
        lblFCardiaca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFCardiaca_actionPerformed(e);
            }
        });
        
        txtFCardiaca.setName("Frecuencia Cardiaca");
        txtFCardiaca.setBounds(new Rectangle(165, 110, 60, 20));
        txtFCardiaca.setLengthText(3);
        txtFCardiaca.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFCardiaca_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                txtFCardiaca_keyTyped(e);
            }
        });
        
        lblFCardiacaX.setText("X'.");
        //lblFCardiacaX.setMnemonic('P');
        lblFCardiacaX.setBounds(new Rectangle(235, 110, 30, 20));
        lblFCardiacaX.setBackground(Color.white);
        lblFCardiacaX.setForeground(new Color(255, 90, 33));
        lblFCardiacaX.setFocusable(false);
        lblFCardiacaX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFCardiacaX_actionPerformed(e);
            }
        });
        
        /*** fila 4 ***/
        lblTemperatura.setText("Temperatura:");
        //lblTemperatura.setMnemonic('M');
        lblTemperatura.setBounds(new Rectangle(20, 150, 120, 20));
        lblTemperatura.setBackground(Color.white);
        lblTemperatura.setForeground(new Color(255, 90, 33));
        lblTemperatura.setFocusable(false);
        lblTemperatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTemperatura_actionPerformed(e);
            }
        });
        
        txtTemperatura.setName("Temperatura");
        txtTemperatura.setBounds(new Rectangle(165, 150, 60, 20));
        txtTemperatura.setLengthText(10);
        txtTemperatura.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtTemperatura_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                txtTemperatura_keyTyped(e);
            }
        });
        
        lblTemperaturaC.setText("°C.");
        //lblTemperaturaC.setMnemonic('P');
        lblTemperaturaC.setBounds(new Rectangle(235, 150, 30, 20));
        lblTemperaturaC.setBackground(Color.white);
        lblTemperaturaC.setForeground(new Color(255, 90, 33));
        lblTemperaturaC.setFocusable(false);
        lblTemperaturaC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTemperaturaC_actionPerformed(e);
            }
        });
        
        /*** fila 5 ***/
        lblPeso.setText("Peso:");
        //lblPeso.setMnemonic('M');
        lblPeso.setBounds(new Rectangle(20, 190, 120, 20));
        lblPeso.setBackground(Color.white);
        lblPeso.setForeground(new Color(255, 90, 33));
        lblPeso.setFocusable(false);
        lblPeso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblPeso_actionPerformed(e);
            }
        });
        
        txtPeso.setName("Peso");
        txtPeso.setBounds(new Rectangle(165, 190, 60, 20));
        txtPeso.setLengthText(6);
        txtPeso.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtPeso_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                txtPeso_keyTyped(e);
            }
        });
        
        lblPesoKg.setText("Kg.");
        //lblPesoKg.setMnemonic('P');
        lblPesoKg.setBounds(new Rectangle(235, 190, 30, 20));
        lblPesoKg.setBackground(Color.white);
        lblPesoKg.setForeground(new Color(255, 90, 33));
        lblPesoKg.setFocusable(false);
        lblPesoKg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblPesoKg_actionPerformed(e);
            }
        });
        
        /*** fila 6 ***/
        lblTalla.setText("Talla:");
        //lblTalla.setMnemonic('M');
        lblTalla.setBounds(new Rectangle(20, 230, 120, 20));
        lblTalla.setBackground(Color.white);
        lblTalla.setForeground(new Color(255, 90, 33));
        lblTalla.setFocusable(false);
        lblTalla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTalla_actionPerformed(e);
            }
        });
        
        txtTalla.setName("Talla");
        txtTalla.setBounds(new Rectangle(165, 230, 60, 20));
        txtTalla.setLengthText(3);
        txtTalla.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtTalla_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                txtTalla_keyTyped(e);
            }
        });
        
        lblTallaCms.setText("cms.");
        //lblTallaCms.setMnemonic('P');
        lblTallaCms.setBounds(new Rectangle(235, 230, 30, 20));
        lblTallaCms.setBackground(Color.white);
        lblTallaCms.setForeground(new Color(255, 90, 33));
        lblTallaCms.setFocusable(false);
        lblTallaCms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTallaCms_actionPerformed(e);
            }
        });
        
        btnF11.setBounds(new Rectangle(20, 330, 117, 20));
        btnF11.setText("[F11] Aceptar");
        btnF11.setFocusable(false);
        btnEsc.setBounds(new Rectangle(325, 330, 117, 20));
        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);
        jPanelHeader1.setBounds(new Rectangle(125, 235, 1, 1));
        jLabelWhite1.setText("jLabelWhite1");
        pnlTitle.add(lblPArterial, null);
        pnlTitle.add(txtPArterial, null);
        pnlTitle.add(lblSlash, null);
        pnlTitle.add(txtMMMercurio, null);
        pnlTitle.add(lblMMMercurio, null);
        pnlTitle.add(lblFRespiratoria, null);
        pnlTitle.add(txtFRespiratoria, null);
        pnlTitle.add(lblFRespiratoriaX, null);
        pnlTitle.add(lblFCardiaca, null);
        pnlTitle.add(txtFCardiaca, null);
        pnlTitle.add(lblFCardiacaX, null);
        pnlTitle.add(lblTemperatura, null);
        pnlTitle.add(txtTemperatura, null);
        pnlTitle.add(lblTemperaturaC, null);
        pnlTitle.add(lblPeso, null);
        pnlTitle.add(txtPeso, null);
        pnlTitle.add(lblPesoKg, null);
        pnlTitle.add(lblTalla, null);
        pnlTitle.add(txtTalla, null);
        pnlTitle.add(lblTallaCms, null);
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
          //cargaCombo();
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
        FarmaUtility.moveFocus(txtPArterial);
    }

    private void this_windowClosing(WindowEvent e){
      FarmaUtility.showMessage(this,"Debe presionar la tecla ESC para cerrar la ventana.",txtPArterial);
    }
    
    
    /* ************************************************************************ */
    /*                      METODOS AUXILIARES DE EVENTOS                       */
    /* ************************************************************************ */

    private void cargaCombo() {
        /*ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbTipDoc, "cmbTipoDocumento", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_DOCUMENTO(?)",
                                   parametros, true, true);*/
    }

    private void chkKeyPressed(KeyEvent e){
        //log.info("Entro al evento keypress : "+e);
      if (UtilityPtoVenta.verificaVK_F11(e)){
          //log.info("Se presiono Actualizar");
          if(validaDatos()){
              
          atenMedicaTri=new CmeAtencionMedicaTri();
          atenMedicaTri.setNumAtenMed(nroSolicitud);
          atenMedicaTri.setCantPA1(txtPArterial.getText().trim());
          atenMedicaTri.setCantPA2(txtMMMercurio.getText().trim());
          atenMedicaTri.setCantFR(txtFRespiratoria.getText().trim());
          atenMedicaTri.setCantFC(txtFCardiaca.getText().trim());
          atenMedicaTri.setCantTemp(txtTemperatura.getText().trim());
          atenMedicaTri.setCantPeso(txtPeso.getText().trim());
          atenMedicaTri.setCantTalla(txtTalla.getText().trim());
         
          try{
               /*FarmaUtility.showMessage(this,"Grabo OK",
                                        txtPArterial);*/
              String codRpta = UtilityAdmisionMedica.insertAtencionMedicaTri(atenMedicaTri);
              FarmaUtility.aceptarTransaccion();
              FarmaUtility.showMessage(this,"Se registró el triaje correctamente.",txtPArterial);
              cerrarVentana(true);
           }catch (SQLException sql) {
                      FarmaUtility.liberarTransaccion();
                      if (sql.getErrorCode() > 20000) {
                      FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                               txtPArterial);
                      } 
                      else {
                      FarmaUtility.showMessage(this, "Error al registrar el triaje.\n" + sql.getMessage(), txtPArterial);
                      log.error("", sql);
                      }
                          
            }catch(Exception er){
                      FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+er.getMessage(), txtPArterial);  
                      log.error("", er);
            }
          }
                                
      }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
        cerrarVentana(false);
      }
  }
    
    private boolean validaDatos(){
        /*if(UtilityAdmisionMedica.valorCampoEsNulo(txtPArterial,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtMMMercurio,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtFRespiratoria,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtFCardiaca,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtTemperatura,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtPeso,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtTalla,this)) return false;*/
        
        try{
            if( !(txtPeso.getText().trim().equals("")) ){
                Double num=Double.parseDouble(txtPeso.getText().trim());
                num  = Math.rint(num*100)/100;
                txtPeso.setText(""+num);
            }
        }catch(NumberFormatException e){
            FarmaUtility.showMessage(this, "Campo Peso debe ser tener el formato (999.99)", txtPeso);
            return false;
        }
        
        if(!(JConfirmDialog.rptaConfirmDialog(this, "¿Desea grabar el triaje del paciente?")))
            return false;
        
        
        return Boolean.TRUE;
    }
    

    private void txtPArterial_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtMMMercurio);
        }
        chkKeyPressed(e);
    }
    private void txtMMMercurio_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtFRespiratoria);
        }
        chkKeyPressed(e);
    }
    private void txtFRespiratoria_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtFCardiaca);
        }
        chkKeyPressed(e);
    }
    private void txtFCardiaca_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtTemperatura);
        }
        chkKeyPressed(e);
    }
    private void txtTemperatura_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtPeso);
        }
        chkKeyPressed(e);
    }
    private void txtPeso_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtTalla);
        }
        chkKeyPressed(e);
    }
    private void txtTalla_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtPArterial);
        }
        chkKeyPressed(e);
    }
    
    
    private void lblPArterial_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtPArterial);
    }
    private void lblSlash_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMMMercurio);
    }
    private void lblMMMercurio_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMMMercurio);
    }
    private void lblFRespiratoria_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtFRespiratoria);
    }
    private void lblFRespiratoriaX_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFRespiratoria);
    }
    private void lblFCardiaca_actionPerformed(ActionEvent e) {
         FarmaUtility.moveFocus(txtFCardiaca);
    }
    private void lblFCardiacaX_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFCardiaca);
    }
    private void lblTemperatura_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtTemperatura);
    }
    private void lblTemperaturaC_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtTemperatura);
    }
    private void lblPeso_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtPeso);
    }
    private void lblPesoKg_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtPeso);
    }    
    private void lblTalla_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtTalla);
    }    
    private void lblTallaCms_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtTalla);
    }


    private void cerrarVentana(boolean pAceptar){
      FarmaVariables.vAceptar = pAceptar;
      this.setVisible(false);
      this.dispose();
    }

    /* ************************************************************************ */
    /*                      METODOS DE LOGICA DE NEGOCIO                        */
    /* ************************************************************************ */


    private void txtPArterial_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtPArterial, e);
    }
    private void txtMMMercurio_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtMMMercurio, e);
    }
    private void txtFRespiratoria_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtFRespiratoria, e);
    }
    private void txtFCardiaca_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtFCardiaca, e);
    }
    private void txtTemperatura_keyTyped(KeyEvent e) {
        //FarmaUtility.admitirDigitos(txtTemperatura, e);
        if(!(e.getKeyChar()=='.'||
                 (e.getKeyChar()=='0'||e.getKeyChar()=='1'||e.getKeyChar()=='2'|| 
                  e.getKeyChar()=='3'||e.getKeyChar()=='4'||e.getKeyChar()=='5'||
                  e.getKeyChar()=='6'||e.getKeyChar()=='7'||e.getKeyChar()=='8'||
                  e.getKeyChar()=='9'
             ) 
            ))
            e.consume();
    }
    private void txtPeso_keyTyped(KeyEvent e) {
        if(!(e.getKeyChar()=='.'||
                 (e.getKeyChar()=='0'||e.getKeyChar()=='1'||e.getKeyChar()=='2'|| 
                  e.getKeyChar()=='3'||e.getKeyChar()=='4'||e.getKeyChar()=='5'||
                  e.getKeyChar()=='6'||e.getKeyChar()=='7'||e.getKeyChar()=='8'||
                  e.getKeyChar()=='9'
             ) 
            ))
            e.consume();
    }
    private void txtTalla_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtTalla, e);
    }
    
}
