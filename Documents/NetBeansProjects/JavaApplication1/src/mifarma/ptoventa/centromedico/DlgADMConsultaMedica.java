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
import java.awt.Dimension;
import java.awt.Frame;

import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JTextField;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.administracion.DlgListaMaestros;
import mifarma.ptoventa.centromedico.DlgHistoriaClinicaAntecedentes.HiloCargaListaDiagnostico;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;
import mifarma.ptoventa.centromedico.reference.ConstantsCentroMedico;
import mifarma.ptoventa.centromedico.reference.DBAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import mifarma.ptoventa.reference.VariablesPtoVenta;

import mifarma.ptoventa.ventas.DlgListaMedicos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgADMConsultaMedica extends JDialog {
    
    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */    
    
    private static final Logger log = LoggerFactory.getLogger(DlgADMConsultaMedica.class);
    private static final long serialVersionUID = -2626325502788986022L;
    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelTitle pnlTitle = new JPanelTitle();
    
    private JButtonLabel lblMedico = new JButtonLabel();
    private JTextFieldSanSerif txtCodMedico = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtDescMedico = new JTextFieldSanSerif(); //txtDescMedico
    
    private JButtonLabel lblConsulta = new JButtonLabel();
    private JTextFieldSanSerif txtCodConsulta = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtDescConsulta = new JTextFieldSanSerif();
    
    private JButtonLabel lblFecha = new JButtonLabel();
    private JTextFieldSanSerif txtFecha = new JTextFieldSanSerif(); 
    
    private JLabelFunction btnF11 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();
    private Frame MyParentFrame;
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();
    
    private BeanAtencionMedica ateMedica;
    private JTextFieldSanSerif txtDescEspecialidad = new JTextFieldSanSerif();
    private JButtonLabel lblEspecialidad = new JButtonLabel();
    private JButtonLabel lblTipoAtencion = new JButtonLabel();
    private JComboBox cmbTipAtencion = new JComboBox();
    
    private ArrayList<ArrayList<String>> lstConsulta;


    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */
    public DlgADMConsultaMedica() {
        super();
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgADMConsultaMedica(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(578, 301));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Datos de Consulta Médica");
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
        pnlTitle.setBounds(new Rectangle(20, 25, 515, 190));
        pnlTitle.setBackground(Color.white);
        //pnlTitle.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        pnlTitle.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlTitle.setFocusable(false);
        
        //lblMedico.setText("Médico:                *");
        lblMedico.setText("Médico :");
        lblMedico.setMnemonic('M');
        lblMedico.setBounds(new Rectangle(20, 30, 120, 20));
        lblMedico.setBackground(Color.white);
        lblMedico.setForeground(new Color(255, 90, 33));
        lblMedico.setFocusable(true);
        
        lblMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblMedico_actionPerformed(e);
            }
        });
        txtCodMedico.setBounds(new Rectangle(165, 30, 45, 20));
        txtCodMedico.setLengthText(10);
        txtCodMedico.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtCodMedico_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                    txtCodMedico_keyTyped(e);
                }
            
        });
        
        txtDescMedico.setBounds(new Rectangle(220, 30, 250, 20));
        txtDescMedico.setLengthText(30);
        txtDescMedico.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtDescMedico_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                //txtNombre_keyTyped(e);
            }
        });
        txtDescMedico.setEditable(false);
        //txtDescMedico.setEnabled(false);
        
        
        txtDescEspecialidad.setBounds(new Rectangle(165, 70, 305, 20));
        //txtDescEspecialidad.setEnabled(false);
        txtDescEspecialidad.setEditable(false);
        
        
        lblEspecialidad.setText("Especialidad :");
        lblEspecialidad.setMnemonic('E');
        lblEspecialidad.setBounds(new Rectangle(20, 75, 125, 15));
        lblEspecialidad.setBackground(Color.white);
        lblEspecialidad.setForeground(new Color(255, 90, 33));
        lblEspecialidad.setFocusable(false);
        lblEspecialidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblEspecialidad_actionPerformed(e);
            }
        });
        
        
        //lblConsulta.setText("Consulta:       *");
        lblConsulta.setText("Consulta :       ");
        lblConsulta.setMnemonic('C');
        lblConsulta.setBounds(new Rectangle(20, 110, 120, 20));
        lblConsulta.setBackground(Color.white);
        lblConsulta.setForeground(new Color(255, 90, 33));
        lblConsulta.setFocusable(false);
        lblConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblEspecialidad_actionPerformed(e);
            }
        });
        
        txtCodConsulta.setBounds(new Rectangle(165, 110, 45, 20));
        txtCodConsulta.setLengthText(3);
        txtCodConsulta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtCodConsulta_keyPressed(e);
                }
            public void keyTyped(KeyEvent e) {
                txtCodConsulta_keyTyped(e);
            }
        });
        
        txtDescConsulta.setBounds(new Rectangle(220, 110, 250, 20));
        txtDescConsulta.setLengthText(30);
        txtDescConsulta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtDescConsulta_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                //txtDescEspecialidad_keyTyped(e);
            }
        });
        txtDescConsulta.setEditable(false);
        //txtDescConsulta.setEnabled(false);
        
        
        lblTipoAtencion.setText("Tipo Atención  :");
        lblTipoAtencion.setBounds(new Rectangle(20, 155, 100, 15));
        lblTipoAtencion.setMnemonic('T');
        lblTipoAtencion.setBackground(Color.white);
        lblTipoAtencion.setForeground(new Color(255, 90, 33));
        lblTipoAtencion.setFocusable(false);
        lblTipoAtencion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTipoAtencion_actionPerformed(e);
            }
        });
        
        
        
        
        cmbTipAtencion.setBounds(new Rectangle(165, 150, 170, 20));
        cmbTipAtencion.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    cmbTipAtencion_keyPressed(e);
                }
        });
        

        //lblFecha.setText("Fecha:                   *");
        lblFecha.setText("Fecha :                   ");
        //lblFecha.setMnemonic('M');
        lblFecha.setBounds(new Rectangle(20, 150, 120, 20));
        lblFecha.setBackground(Color.white);
        lblFecha.setForeground(new Color(255, 90, 33));
        lblFecha.setFocusable(false);
        lblFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFecha_actionPerformed(e);
            }
        });
        
        txtFecha.setBounds(new Rectangle(165, 150, 130, 20));
        txtFecha.setLengthText(10);
        //txtFecha.setEditable(false);
        txtFecha.setEditable(false);
        txtFecha.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFecha_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                //txtFecha_keyTyped(e);
            }
            public void keyReleased(KeyEvent e) {
                  txtFecha_keyReleased(e);
            }
        });



        btnF11.setBounds(new Rectangle(20, 235, 117, 20));
        btnF11.setText("[F11] Aceptar");
        btnF11.setFocusable(false);
        btnEsc.setBounds(new Rectangle(420, 235, 117, 20));
        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);
        jPanelHeader1.setBounds(new Rectangle(125, 235, 1, 1));
        jLabelWhite1.setText("jLabelWhite1");


        


        /*pnlTitle.add(cmbTipAtencion, null);
        pnlTitle.add(lblTipoAtencion, null);*/
        pnlTitle.add(lblEspecialidad, null);
        pnlTitle.add(txtDescEspecialidad, null);
        pnlTitle.add(lblMedico, null);
        pnlTitle.add(txtCodMedico, null);
        pnlTitle.add(txtDescMedico, null);
        pnlTitle.add(lblConsulta, null);
        pnlTitle.add(txtCodConsulta, null);
        pnlTitle.add(txtDescConsulta, null);
        pnlTitle.add(lblFecha, null);
        pnlTitle.add(txtFecha, null);
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
        FarmaUtility.moveFocus(txtCodMedico);
    }

    private void this_windowClosing(WindowEvent e){
      FarmaUtility.showMessage(this,"Debe presionar la tecla ESC para cerrar la ventana.",txtCodMedico);
    }
    
    
    /* ************************************************************************ */
    /*                      METODOS AUXILIARES DE EVENTOS                       */
    /* ************************************************************************ */

    private void cargaCombo() {
        /*ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbTipAtencion, "cmbTipAtencion", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_SOL_MEDICA(?)",
                                   parametros, true, true);*/
        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecIni = formato.format(calendario.getTime());
        txtFecha.setText(fecIni);
    }

    private void chkKeyPressed(KeyEvent e){
      if (UtilityPtoVenta.verificaVK_F11(e)){
                  //log.info("Se presiono F11");                 
                  funcion_f11();
      }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
        cerrarVentana(false);
      }
    }
    
    private void funcion_f11(){
        if(validarDatos()){
            //String vTipAten = FarmaLoadCVL.getCVLCode("cmbTipAtencion", cmbTipAtencion.getSelectedIndex());
            ateMedica=new BeanAtencionMedica();
            ateMedica.setCodMedico(VariablesCentroMedico.vCodMedico/*txtCodMedico.getText().trim()*/);
            ateMedica.setCodEspecialidad(Integer.parseInt(txtCodConsulta.getText().trim()));
            ateMedica.setCodPaciente(VariablesCentroMedico.vCodPaciente);
            ateMedica.setEstado(ConstantsCentroMedico.ATE_MEDICA_PEND_TRIAJE);
            ateMedica.setCodTipAtencion("");
            
            try{
            String codRpta = UtilityAdmisionMedica.insertAtencionMedica(ateMedica);
            FarmaUtility.aceptarTransaccion();

                UtilityAtencionMedica.registraEstadoCompAtencionMedica(VariablesCentroMedico.vCodLocalVtaComprobante, VariablesCentroMedico.vNumPedVtaComprobante);
                
            FarmaUtility.showMessage(this,"Se registró la solicitud médica correctamente.",txtCodMedico);
            cerrarVentana(true);
            }catch (SQLException sql) {
                    FarmaUtility.liberarTransaccion();
                    if (sql.getErrorCode() > 20000) {
                    FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                             txtCodMedico);
                    } 
                    else {
                    FarmaUtility.showMessage(this, "Error al registrar la solicitud médica.\n" + sql.getMessage(), txtCodMedico);
                    log.error("", sql);
                    }
                        
            }catch(Exception er){
                    FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+er.getMessage(), txtCodMedico);  
                    log.error("", er);
            }
        }
    }
    
    private boolean validarDatos(){
        boolean flag=true;
        
        if(txtCodMedico.getText().trim().equals("")){
            FarmaUtility.showMessage(this,"El código del médico es obligatorio",txtCodMedico);
            flag=false;
        }else if(txtDescMedico.getText().trim().equals("")){
            FarmaUtility.showMessage(this,"Código de médico no encontrado",txtCodMedico);
            flag=false;    
        }else if(txtCodConsulta.getText().trim().equals("")){
            FarmaUtility.showMessage(this,"El código de la consulta es obligatorio",txtCodConsulta);
            flag=false;
        }else if(txtDescConsulta.getText().trim().equals("")){
            FarmaUtility.showMessage(this,"Código de consulta no encontrado",txtCodConsulta);
            flag=false;   
        }else if(!(JConfirmDialog.rptaConfirmDialog(this, "¿Desea grabar la solicitud de atención?"))){ 
            flag=false;
        }
        return flag;
    }
    
    private void txtCodMedico_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            
            /*if(!(txtCodMedico.getText().trim().equals("")))
            txtCodMedico.setText(FarmaUtility.completeWithSymbol(txtCodMedico.getText().trim(),10 , "0", "I").toUpperCase());*/
            
            //VariablesPtoVenta.vTipoMaestro = "CME01";
            validaCodigo(txtCodMedico, txtDescMedico,txtDescEspecialidad, VariablesPtoVenta.vTipoMaestro, ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_MEDICO);
            /*if (!txtCodMedico.getText().trim().equals("")) {
                FarmaUtility.moveFocus(txtCodMedico);
            }*/
        } else {
            chkKeyPressed(e);
        }    
    }
    
    private void txtCodConsulta_keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            VariablesPtoVenta.vTipoMaestro = "CME02";
            validaCodigo(txtCodConsulta, txtDescConsulta,new JTextField(), VariablesPtoVenta.vTipoMaestro, ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_ATENCION);
            /*if (!txtCodConsulta.getText().trim().equals("")) {
                FarmaUtility.moveFocus(txtCodConsulta);
            }*/
        } else {
            chkKeyPressed(e);
        }    
    }
    
    
    private void validaCodigo(JTextField pJTextField_Cod, JTextField pJTextField_DescI,JTextField pJTextField_DescII, String pTipoMaestro,String tipobusqueda) {
        if (pJTextField_Cod.getText().trim().length() > 0) {
            VariablesPtoVenta.vCodMaestro = pJTextField_Cod.getText().trim();
            ArrayList myArray = new ArrayList();
            boolean flagFind=true;
            
            if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_MEDICO)){
                if(VariablesCentroMedico.vCodMedico.trim().length()!=0){
                    if(JConfirmDialog.rptaConfirmDialog(this, "¿Desea buscar un nuevo médico?")){
                        buscaCodigoListaMaestro(myArray,tipobusqueda);
                    }else{
                        FarmaUtility.moveFocus(txtCodConsulta);
                        flagFind=false;
                    }
                }
                else
                buscaCodigoListaMaestro(myArray,tipobusqueda);

            }
            else
            buscaCodigoListaMaestro(myArray,tipobusqueda);

            if (myArray.size() == 0 && flagFind) {
                FarmaUtility.showMessage(this, "Código No Encontrado", pJTextField_Cod);
                FarmaVariables.vAceptar = false;
                //pJTextField_Cod.setText("");
                pJTextField_DescI.setText("");
                pJTextField_DescII.setText("");
                FarmaUtility.moveFocus(pJTextField_Cod);
                if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_MEDICO))
                    VariablesCentroMedico.vCodMedico="";
                
            } else if (myArray.size() == 1 && flagFind) {
                if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_ATENCION)){ // busqueda de atención
                        String codigo = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
                        String descripcion = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
                        pJTextField_Cod.setText(codigo);
                        pJTextField_DescI.setText(descripcion);
                        VariablesPtoVenta.vCodMaestro = codigo;
                        FarmaUtility.moveFocus(/*cmbTipAtencion*/txtCodMedico);
                }else if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_MEDICO)){ 
                        String codigo = ((String)((ArrayList)myArray.get(0)).get(0)).trim();
                        String codigoCMP = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
                        String descripcion = ((String)((ArrayList)myArray.get(0)).get(2)).trim();
                        String descripcionII = ((String)((ArrayList)myArray.get(0)).get(5)).trim();
                        pJTextField_Cod.setText(codigoCMP);
                        pJTextField_DescI.setText(descripcion);
                        
                        if(descripcionII.trim().equalsIgnoreCase("MEDICINA GENERAL")){
                            txtCodConsulta.setEnabled(false);
                            txtCodConsulta.setText("237");
                            txtDescConsulta.setText("MEDICINA GENERAL");
                        }
                        else{
                            txtCodConsulta.setEnabled(true);
                            txtCodConsulta.setText("");
                            txtDescConsulta.setText("");
                            FarmaUtility.moveFocus(txtCodConsulta);    
                        }
                        //VariablesPtoVenta.vCodMaestro = codigoCMP;
                        pJTextField_DescII.setText(descripcionII);
                        
                }
                FarmaVariables.vAceptar = true;
            } else if(flagFind)/*if (myArray.size() >= 2)*/ { //>2
                if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_ATENCION)){
                    FarmaUtility.showMessage(this, "Se encontro más de un registro.Verificar!!!", pJTextField_Cod);
                    FarmaUtility.moveFocus(pJTextField_Cod);
                    FarmaVariables.vAceptar = false;
                }else if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_MEDICO)){ 
                    DlgADMListaMedicos dlgListaMedicos = new DlgADMListaMedicos(MyParentFrame, "", true,pJTextField_Cod.getText().trim());
                    dlgListaMedicos.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        pJTextField_Cod.setText(VariablesCentroMedico.vCodCMPMedico);
                        pJTextField_DescI.setText(VariablesCentroMedico.vDescMedico);
                        pJTextField_DescII.setText(VariablesCentroMedico.vDescEspecialidad);
                        
                        if(VariablesCentroMedico.vDescEspecialidad.trim().equalsIgnoreCase("MEDICINA GENERAL")){
                            txtCodConsulta.setEnabled(false);
                            txtCodConsulta.setText("237");
                            txtDescConsulta.setText("MEDICINA GENERAL");
                        }
                        else{
                            txtCodConsulta.setEnabled(true);
                            txtCodConsulta.setText("");
                            txtDescConsulta.setText("");
                            FarmaUtility.moveFocus(txtCodConsulta);    
                        }
                    }/*else{
                        pJTextField_Cod.setText("");
                        pJTextField_DescI.setText("");
                        pJTextField_DescII.setText("");
                    }*/
                }
            }
        } else {
            cargaListaMaestros(pTipoMaestro, pJTextField_Cod, pJTextField_DescI,pJTextField_DescII,tipobusqueda);
        }
    }
    
    private void buscaCodigoListaMaestro(ArrayList pArrayList,String tipobusqueda) {
        try {
            if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_ATENCION)){
                //DBPtoVenta.buscaCodigoListaMaestro(pArrayList, VariablesPtoVenta.vTipoMaestro,VariablesPtoVenta.vCodMaestro);
                DBAdmisionMedica.buscarporCodigoConsulta(pArrayList,txtCodConsulta.getText().trim());
            }else if(tipobusqueda.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_MEDICO)){
                //log.info("Cargar lista por codigo maestro");
                //DBAdmisionMedica.buscarporCodigoMedico(pArrayList,txtCodMedico.getText().trim(),"","","");
                DBAdmisionMedica.buscarporCodigoMedico(pArrayList,"","","",txtCodMedico.getText().trim());
            }

            
        } catch (Exception sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al buscar código en maestros :\n" +
                    sql.getMessage(), txtCodMedico);
        }
    }
    
    private void cargaListaMaestros(String pTipoMaestro, JTextField pJTextField_Cod, JTextField pJTextField_Desc,
                                    JTextField pJTextField_DescII,String tipoMaestro) {
        if(tipoMaestro.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_ATENCION)){
            lstConsulta=(new UtilityAdmisionMedica()).obtenerListaConsultas();
            DlgListadoCM dlgListado = new DlgListadoCM(MyParentFrame, "Lista de Consultas", true, false, lstConsulta);
            dlgListado.setVisible(true);
            if(FarmaVariables.vAceptar){
                FarmaVariables.vAceptar = false;
                ArrayList lstResultado = dlgListado.getLstResultado();
                if(!lstResultado.isEmpty()){
                    pJTextField_Cod.setText(((String)((ArrayList)lstResultado.get(0)).get(0)).trim());
                    pJTextField_Desc.setText(((String)((ArrayList)lstResultado.get(0)).get(1)).trim());
                    FarmaUtility.moveFocus(/*cmbTipAtencion*/txtCodMedico);
                }else{
                    pJTextField_Cod.setText("");
                    pJTextField_Desc.setText("");
                }
            }else{
                    pJTextField_Cod.setText("");
                    pJTextField_Desc.setText("");
            }
            
            
        }else if(tipoMaestro.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_MAE_MEDICO)){
            DlgADMListaMedicos dlgListaMedicos = new DlgADMListaMedicos(MyParentFrame, "", true,"");
            dlgListaMedicos.setVisible(true);
            if (FarmaVariables.vAceptar) {
                pJTextField_Cod.setText(VariablesCentroMedico.vCodCMPMedico);
                pJTextField_Desc.setText(VariablesCentroMedico.vDescMedico);
                pJTextField_DescII.setText(VariablesCentroMedico.vDescEspecialidad);
                if(VariablesCentroMedico.vDescEspecialidad.trim().equalsIgnoreCase("MEDICINA GENERAL")){
                    txtCodConsulta.setEnabled(false);
                    txtCodConsulta.setText("237");
                    txtDescConsulta.setText("MEDICINA GENERAL");
                }
                else{
                    txtCodConsulta.setEnabled(true);
                    txtCodConsulta.setText("");
                    txtDescConsulta.setText("");
                    FarmaUtility.moveFocus(txtCodConsulta);    
                }                
                FarmaUtility.moveFocus(txtCodConsulta);
            }/*else{
                pJTextField_Cod.setText("");
                pJTextField_Desc.setText("");
                pJTextField_DescII.setText("");
            }*/
        }
    }
    
    private void txtDescMedico_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void txtDescConsulta_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    private void txtFecha_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    
    private void txtFecha_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFecha, e);
    }
     
    private void cmbTipAtencion_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtCodMedico);
        }
        chkKeyPressed(e);
    }
    
    private void lblMedico_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtCodMedico);
    } 
    
    private void txtCodMedico_keyTyped(KeyEvent e) {
        if(!(e.getKeyCode() == KeyEvent.VK_ENTER||
            e.getKeyCode() == KeyEvent.VK_DELETE))
            e.consume();
        //FarmaUtility.admitirDigitos(txtCodMedico, e);
    }
    private void txtCodConsulta_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCodConsulta, e);
    }
    
    private void lblEspecialidad_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtCodConsulta);
    }
    private void lblTipoAtencion_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipAtencion);
    }
    
    private void lblFecha_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFecha);
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
