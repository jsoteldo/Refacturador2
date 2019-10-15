package mifarma.ptoventa.centromedico;

import com.gs.mifarma.componentes.ExpressionValidate;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import com.gs.mifarma.componentes.JTextFieldValidate;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;

import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.SwingConstants;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.DlgIngresoCompManual;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.centromedico.reference.ConstantsCentroMedico;
import mifarma.ptoventa.centromedico.reference.DBAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.FacadeVentaAtencionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.delivery.DlgMantClienteDireccionNew;
import mifarma.ptoventa.reference.ConstantsPtoVenta;

import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgADMBuscarComprobantePago extends JDialog{

    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */    

    private static final Logger log = LoggerFactory.getLogger(DlgADMBuscarComprobantePago.class);
    private static final long serialVersionUID = -2626325502788986022L;
    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelTitle pnlTitle = new JPanelTitle();
    private JButtonLabel lblTipoComprobante = new JButtonLabel();
    private JButtonLabel lblNroComprobante = new JButtonLabel();
    private JComboBox cmbTipoComp = new JComboBox();
    private JLabelFunction btnF11 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();
    private JTextFieldValidate txtNroComprobante = new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,true);
    private Frame MyParentFrame;
    
    private VtaCompAtencionMedica vtaCAM;
    private JTextFieldValidate txtSerie = new JTextFieldValidate(ExpressionValidate.ALFANUMERICO,true);
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JPasswordField txtLecturaOculta = new JPasswordField();
    private JLabelFunction jLabel3 = new JLabelFunction();


    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */
    public DlgADMBuscarComprobantePago() {
        super();
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgADMBuscarComprobantePago(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(458, 289));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ingrese Comprobante de Pago");
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
        pnlTitle.setBounds(new Rectangle(20, 60, 405, 130));
        pnlTitle.setBackground(Color.white);
        pnlTitle.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        pnlTitle.setFocusable(false);
        lblTipoComprobante.setText("Tipo Comprobante:");
        lblTipoComprobante.setMnemonic('T');
        lblTipoComprobante.setBounds(new Rectangle(20, 30, 120, 20));
        lblTipoComprobante.setBackground(Color.white);
        lblTipoComprobante.setForeground(new Color(255, 90, 33));
        lblTipoComprobante.setFocusable(false);
        lblTipoComprobante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTipoComprobante_actionPerformed(e);
            }
        });
        
        lblNroComprobante.setText("Nro. Comprobante:");
        lblNroComprobante.setMnemonic('N');
        lblNroComprobante.setBounds(new Rectangle(20, 75, 115, 20));
        lblNroComprobante.setForeground(new Color(255, 90, 33));
        lblNroComprobante.setFocusable(false);
        lblNroComprobante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNroComprobante_actionPerformed(e);
            }
        });
        
        cmbTipoComp.setBounds(new Rectangle(165, 30, 215, 20));
        cmbTipoComp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //cmbTipoComp_actionPerformed(e);
                
            }
        });
        cmbTipoComp.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    cmbTipoComp_keyPressed(e);
                }
        });
        
        txtNroComprobante.setBounds(new Rectangle(240, 75, 140, 20));
        txtNroComprobante.setLengthText(8);//20
        txtNroComprobante.setText("");//borrar
        txtNroComprobante.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNroComprobante_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                    txtNroComprobante_keyTyped(e);
                }
        });


        txtSerie.setBounds(new Rectangle(165, 75, 50, 20));
        txtSerie.setLengthText(4);//20
        txtSerie.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtSerie_keyPressed(e);
                }
                public void keyTyped(KeyEvent e) {
                    txtSerie_keyTyped(e);
                }
            });
        jLabel1.setText("-");
        jLabel1.setBounds(new Rectangle(220, 75, 15, 15));
        jLabel1.setFont(new Font("SansSerif", 1, 16));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel1.setForeground(new Color(214, 107, 0));
        jLabel2.setText("<html><center>Ingresar<br>C\u00f3digo Barra<br>Comprobante</center></html>");
        jLabel2.setBounds(new Rectangle(40, 10, 80, 40));
        jLabel2.setFont(new Font("SansSerif", 1, 11));
        txtLecturaOculta.setBounds(new Rectangle(135, 20, 255, 25));
        txtLecturaOculta.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtLecturaOculta_keyPressed(e);
                }
            });
        jLabel3.setText("<html><center>[F1] Ingresar <br>C\u00f3digo Barra</center></html>");
        jLabel3.setBounds(new Rectangle(20, 205, 125, 35));
        btnF11.setBounds(new Rectangle(180, 205, 115, 35));
        btnF11.setText("[F11] Aceptar");
        btnF11.setFocusable(false);
        btnEsc.setBounds(new Rectangle(310, 205, 115, 35));
        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);
        pnlTitle.add(jLabel1, null);
        pnlTitle.add(txtSerie, null);
        pnlTitle.add(lblTipoComprobante, null);
        pnlTitle.add(cmbTipoComp, null);
        pnlTitle.add(lblNroComprobante, null);
        pnlTitle.add(txtNroComprobante, null);
        jContentPane.add(jLabel3, null);
        jContentPane.add(txtLecturaOculta, null);
        jContentPane.add(jLabel2, null);
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
        FarmaUtility.moveFocus(txtLecturaOculta);
        cmbTipoComp.setEnabled(false);
        txtSerie.setEnabled(false);
        txtNroComprobante.setEnabled(false);
    }

    private void this_windowClosing(WindowEvent e){
      FarmaUtility.showMessage(this,"Debe presionar la tecla ESC para cerrar la ventana.",cmbTipoComp);
    }
    

    /* ************************************************************************ */
    /*                      METODOS AUXILIARES DE EVENTOS                       */
    /* ************************************************************************ */


    private void cargaCombo() {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbTipoComp, "cmbComprobante", "PTOVENTA_ADMIN_IMP.IMP_LISTA_TIPOS_COMP_MANUAL(?)",
                                   parametros, true, true);
        //FarmaLoadCVL.setSelectedValueInComboBox(cmbTipoComp, "cmbComprobante", "02"/*paciente.getTipDocumento()*/);
        
    }
    
    private void chkKeyPressed(KeyEvent e){
        
      if (UtilityPtoVenta.verificaVK_F11(e)){
          
          evento_F11();

      }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
        cerrarVentana(false);
      }
      else
        if (e.getKeyCode() == KeyEvent.VK_F1) {
           txtSerie.setEnabled(false);
           txtNroComprobante.setEnabled(false);
           cmbTipoComp.setEnabled(false);
           txtLecturaOculta.setEnabled(true);
           txtLecturaOculta.setText("");
           txtSerie.setText("");
           txtNroComprobante.setText("");
           FarmaUtility.moveFocus(txtLecturaOculta);
        }
    }
    
    public ArrayList validaComprobanteRAC(VtaCompAtencionMedica vtaCAM) throws SQLException{
        

        return UtilityAdmisionMedica.validaComprobantePago(vtaCAM);
    }
    
    
    private void txtNroComprobante_keyTyped(KeyEvent e) {
        //FarmaUtility.admitirDigitos(txtNroComprobante, e);
        
    }
    
    private void txtSerie_keyTyped(KeyEvent e) {
        //FarmaUtility.admitirLetrasNumeros(txtSerie, e);

    }
    
    private void txtNroComprobante_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            //txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(),8 , "0", "I").toUpperCase());
            if(txtSerie.getText().length()==4){
                txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 8, "0",
                                                                          "I").toUpperCase());
            }
            else
            if(txtSerie.getText().length()==3){
                txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 7, "0",
                                                                          "I").toUpperCase());
            }            
            FarmaUtility.moveFocus(cmbTipoComp);
        }
        chkKeyPressed(e);
    }
    
    private void cmbTipoComp_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtSerie);
        }
        chkKeyPressed(e);
    }
    
    
    private void lblTipoComprobante_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipoComp);
    }

    private void lblNroComprobante_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtNroComprobante);
    }


    private void cerrarVentana(boolean pAceptar){
      FarmaVariables.vAceptar = pAceptar;
      this.setVisible(false);
      this.dispose();
    }

    /* ************************************************************************ */
    /*                      METODOS DE LOGICA DE NEGOCIO                        */
    /* ************************************************************************ */
    
    public boolean validaDatos(){
        boolean flag=true;
        vtaCAM.setNum_comp_pago(getNumComprobante());
        if(vtaCAM.getTip_comp_pago().equals("")){
            FarmaUtility.showMessage(this,"Ingrese tipo de documento.",cmbTipoComp);
            return false;
        }
        if(vtaCAM.getNum_comp_pago().equals("")){
            FarmaUtility.showMessage(this,"Ingrese número de documento.",txtNroComprobante);
            return false;
        }
        return flag;
    }

    private void txtSerie_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            //txtSerie.setText(FarmaUtility.completeWithSymbol(txtSerie.getText().trim(),4 , "0", "I").toUpperCase());

            if(txtSerie.getText().length()>0)
                if(txtSerie.getText().length()==4){
                    txtNroComprobante.setLengthText(8);
                    FarmaUtility.moveFocus(txtNroComprobante);    
                }
            else
                if(txtSerie.getText().length()==3){
                    txtNroComprobante.setLengthText(7);
                    FarmaUtility.moveFocus(txtNroComprobante);    
                }
                    else
                        txtNroComprobante.setLengthText(0);
            
            //FarmaUtility.moveFocus(txtNroComprobante);
        }
        chkKeyPressed(e);
    }
    
    public String getNumComprobante(){
        if(txtSerie.getText().length()==4){
            txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 8, "0",
                                                                      "I").toUpperCase());
        }
        else
        if(txtSerie.getText().length()==3){
            txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 7, "0",
                                                                      "I").toUpperCase());
        }
        
        
        return 
        //FarmaUtility.completeWithSymbol(txtSerie.getText().trim(),4 , "0", "I").toUpperCase() +
        //FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(),8 , "0", "I").toUpperCase();
        (txtSerie.getText().trim()+txtNroComprobante.getText().trim()).toUpperCase();
    }

    private void evento_F11() {         
        //txtSerie.setText(FarmaUtility.completeWithSymbol(txtSerie.getText().trim(), 4, "0", "I").toUpperCase());
        /*txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 8, "0",
                                                                  "I").toUpperCase());*/
        if(txtSerie.getText().length()==4){
            txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 8, "0",
                                                                      "I").toUpperCase());
        }
        else
        if(txtSerie.getText().length()==3){
            txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(), 7, "0",
                                                                      "I").toUpperCase());
        }        

        String vTipComp = FarmaLoadCVL.getCVLCode("cmbComprobante", cmbTipoComp.getSelectedIndex());
        vtaCAM = new VtaCompAtencionMedica();
        vtaCAM.setTip_comp_pago(vTipComp);
        vtaCAM.setNum_comp_pago(getNumComprobante());

        int reintentos = 2;
        if (validaDatos()) {
            do {
                try {
                    ArrayList lista = UtilityAdmisionMedica.validaComprobantePago(vtaCAM);
                    //ArrayList lista=validaComprobanteRAC(vtaCAM);
                    VariablesCentroMedico.vNumPedVtaComprobante = ((String)((ArrayList)lista.get(0)).get(1)).trim();
                    VariablesCentroMedico.vCodLocalVtaComprobante = ((String)((ArrayList)lista.get(0)).get(2)).trim();
                    VariablesCentroMedico.vTipComprobante =
                            vtaCAM.getTip_comp_pago(); // setear el valor del comprobante
                    VariablesCentroMedico.vNumComprobante =
                            vtaCAM.getNum_comp_pago(); // setear el valor del comprobante

                    DlgADMListaPacientes dlglistPac =
                        new DlgADMListaPacientes(MyParentFrame, "", true, vtaCAM, new VtaPedidoAtencionMedica(), ConstantsCentroMedico.TIPO_BUSQUEDA_COMPROBANTE);
                    dlglistPac.setLocationRelativeTo(MyParentFrame);
                    dlglistPac.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        cerrarVentana(true);
                    }
                    break;
                } catch (SQLException sql) {
                    reintentos--;
                    
                    if (sql.getErrorCode() == 20001) {
                            if (reintentos >= 1) {
                                log.info("Insertar desde el RAC  =>  TipComp : " + vtaCAM.getTip_comp_pago() + "  ------  NumComp : " + vtaCAM.getNum_comp_pago());
                                
                                boolean flag = UtilityAtencionMedica.getComprobanteConsultaMedica(vtaCAM.getTip_comp_pago(), vtaCAM.getNum_comp_pago());
                                log.info("Exito al insertar ? " + flag);
                                if (flag) {
                                    String pDatos = UtilityAdmisionMedica.getDatosComprobante(vtaCAM);
                                    if(!pDatos.trim().equalsIgnoreCase("N")){
                                        String[] pR = pDatos.split("@");
                                        if(pR.length==2)
                                        UtilityAtencionMedica.recuperaEstadosComp(pR[0].trim(), pR[1].trim());    
                                    }
                                }
                                continue;
                            }else{
                                FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                         txtNroComprobante);
                            }
                    
                    }else if (sql.getErrorCode() > 20001) {
                        FarmaUtility.showMessage(this,
                                                 sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                 txtNroComprobante);
                    } else {
                        FarmaUtility.showMessage(this, "Error al buscar comprobante.\n" +
                                sql.getMessage(), txtSerie);
                        log.error("", sql);
                    }
                    break;
                } catch (Exception er) {
                    FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n " + er.getMessage(), txtSerie);
                    log.error("", er);
                    break;
                }
            } while (reintentos >= 1);
        }
    }

    private void txtLecturaOculta_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            //txtNroComprobante.setText(FarmaUtility.completeWithSymbol(txtNroComprobante.getText().trim(),8 , "0", "I").toUpperCase());
            //FarmaUtility.moveFocus(cmbTipoComp);
            String pCadena = txtLecturaOculta.getText().trim();
            if(pCadena.trim().length()==17){
                String pCodLocal = pCadena.substring(0,3);
                String pTipo = pCadena.substring(3,5);
                String pSerie = pCadena.substring(5,9);
                String pNum = pCadena.substring(9);
                
                cmbTipoComp.setEnabled(true);
                txtSerie.setEnabled(true);
                txtNroComprobante.setEnabled(true);
                
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipoComp, "cmbComprobante", pTipo);                         
                txtSerie.setText(pSerie);
                txtNroComprobante.setText(pNum);
                /*FarmaUtility.showMessage(this, ""+pCodLocal+"\n" +
                    ""+pTipo+"\n"+
                ""+pSerie+"\n"+
                ""+pNum+""
                    , txtSerie);*/
                evento_F11();
            }
            else{
                txtLecturaOculta.setEnabled(false);
                txtSerie.setEnabled(true);
                txtNroComprobante.setEnabled(true);
                cmbTipoComp.setEnabled(true);
                txtLecturaOculta.setText("");
                txtSerie.setText("");
                txtNroComprobante.setText("");                
                FarmaUtility.moveFocus(cmbTipoComp);
            } 
        }
        chkKeyPressed(e);
    }
}
