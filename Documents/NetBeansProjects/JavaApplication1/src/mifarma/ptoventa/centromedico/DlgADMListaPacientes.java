package mifarma.ptoventa.centromedico;


import com.gs.mifarma.componentes.ExpressionValidate;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import com.gs.mifarma.componentes.JTextFieldValidate;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedDiagnostico;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentes;
import mifarma.ptoventa.centromedico.reference.ConstantsCentroMedico;

import mifarma.ptoventa.centromedico.reference.FacadeAtencioMedica;
import mifarma.ptoventa.centromedico.reference.Tratamiento;
import mifarma.ptoventa.centromedico.reference.UtilityAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.inventario.reference.DBInventario;

import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgADMListaPacientes extends JDialog {
    
    private static final Logger log = LoggerFactory.getLogger(DlgADMBuscarPaciente.class);
    private static final long serialVersionUID = -2626325502788986022L;
    private JPanelWhite jContentPane = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private JPanelHeader pnlDatos = new JPanelHeader();
    private JPanelTitle pnlTituloLista = new JPanelTitle();
    private JButtonLabel lblTitulo = new JButtonLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable tblDatos = new JTable();
    FarmaTableModel tableModelListaDatos;
    private JLabelFunction btnF1 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();
    private JLabelFunction btnF2 = new JLabelFunction();
    private JLabelFunction btnF11 = new JLabelFunction();
    private Frame MyParentFrame;
    private VtaCompAtencionMedica vtaCAM;
    private VtaPedidoAtencionMedica vtaPAM;    
    private VtaPedidoAtencionMedica vtaPAMBusqueda;
    private String pTipoLista;    
    private JButtonLabel lblApePaterno = new JButtonLabel();
    
    private JButtonLabel lblTipNumDoc = new JButtonLabel();
    private JComboBox cmbTipDoc = new JComboBox();

    private JButton btnBuscar = new JButton();
    BeanPaciente paciente;
    private JLabel lblApeMaterno = new JLabel();
    private JLabel lblNombre = new JLabel();

    private JTextFieldValidate txtApePaterno = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,true);
    private JTextFieldValidate txtApeMaterno = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,false);
    private JTextFieldValidate txtNombre = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,true);      
    private JTextFieldValidate txtNumDoc =new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,false);


    private boolean flagDNIPac=true;
    private JLabelFunction btnF3 = new JLabelFunction();
    private JLabelFunction btnF4 = new JLabelFunction();
    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */
    public DlgADMListaPacientes() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgADMListaPacientes(Frame parent, String title, boolean modal,String tipo) {
        super(parent, title, modal);
        try {
            MyParentFrame = parent;
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    public DlgADMListaPacientes(Frame parent, String title, boolean modal,
                                VtaCompAtencionMedica vtaCAM,VtaPedidoAtencionMedica vtaPAM,String tipo) {
        super(parent, title, modal);
        try {
            this.vtaCAM=vtaCAM;
            this.vtaPAM=vtaPAM;;
            this.pTipoLista=tipo;
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
        this.setSize(new Dimension(661, 369));
        this.getContentPane().setLayout(gridLayout1);
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
        pnlDatos.setBounds(new Rectangle(10, 5, 635, 95));
        pnlDatos.setFocusable(false);
    
        
        tblDatos.getTableHeader().setReorderingAllowed(false);
        tblDatos.getTableHeader().setResizingAllowed(false);
        
        lblApePaterno.setText("Apellido Paterno (*)");
        lblApePaterno.setBounds(new Rectangle(40, 40, 120, 15));
        lblApePaterno.setBackground(Color.white);
        lblApePaterno.setFocusable(false);
        //lblNombre.setForeground(new Color(255, 90, 33));
        lblApePaterno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblApePaterno_actionPerformed(e);
            }
        });
        
        txtApePaterno.setBounds(new Rectangle(40, 60, 155, 20));
        txtApePaterno.setLengthText(20);
        txtApePaterno.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtApePaterno_keyPressed(e);
                }
            public void keyTyped(KeyEvent e) {
                   txtApePaterno_keyTyped(e);
            }
        });
        
        lblApeMaterno.setText("Apellido Materno");
        lblApeMaterno.setBounds(new Rectangle(215, 40, 125, 15));
        lblApeMaterno.setForeground(SystemColor.window);
        lblApeMaterno.setFont(new Font("SansSerif", 1, 11));
        
        txtApeMaterno.setBounds(new Rectangle(215, 60, 180, 20));
        txtApeMaterno.setLengthText(20);
        txtApeMaterno.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtApeMaterno_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtApeMaterno_keyTyped(e);
                }
            });

        lblNombre.setText("Nombres (*)");
        lblNombre.setBounds(new Rectangle(410, 40, 95, 15));
        lblNombre.setForeground(SystemColor.window);
        lblNombre.setFont(new Font("SansSerif", 1, 11));
        
        txtNombre.setBounds(new Rectangle(410, 60, 170, 20));
        txtNombre.setLengthText(20);
        txtNombre.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtNombre_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtNombre_keyTyped(e);
                }
            });
        
        
        lblTipNumDoc.setText("Tipo y Número Doc.");
        lblTipNumDoc.setBounds(new Rectangle(40, 10, 115, 15));
        lblTipNumDoc.setBackground(Color.white);
        lblTipNumDoc.setFocusable(false);
        lblTipNumDoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTipNumDoc_actionPerformed(e);
            }
        });
        
        cmbTipDoc.setBounds(new Rectangle(175, 10, 130, 20));
        cmbTipDoc.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipDoc_keyPressed(e);

                }
            });
        cmbTipDoc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cmbTipDoc_actionPerformed(/*e*/);

                }
            });
        
        
        txtNumDoc.setBounds(new Rectangle(330, 10, 130, 20));
        txtNumDoc.setLengthText(10);
        txtNumDoc.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtNumDoc_keyPressed(e);
                }
            public void keyTyped(KeyEvent e) {
                    txtNumDoc_keyTyped(e);
                }
        });
        
        btnF3.setText("<html><center>[F3] Imprimir <br> HC </center></html>");
        btnF3.setBounds(new Rectangle(220, 290, 90, 40));
        btnF4.setText("<html><center>[F4] Ver Histórico Atenciones</center></html>");
        btnF4.setBounds(new Rectangle(325, 290, 105, 40));
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(475, 10, 105, 25));
        btnBuscar.setFont(new Font("Dialog", 1, 11));
        btnBuscar.setMnemonic('B');
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });



        pnlTituloLista.setBounds(new Rectangle(10, 100, 635, 30));
        lblTitulo.setText("Relación de Pacientes");
        lblTitulo.setBounds(new Rectangle(10, 10, 225, 15));
        lblTitulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblTitulo_actionPerformed(e);
            }
        });
        jScrollPane1.setBounds(new Rectangle(10, 130, 635, 155));
        tblDatos.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblDatos_keyPressed(e);
                }
            });
        btnF1.setBounds(new Rectangle(10, 290, 85, 40));
        btnF1.setText("<html><center>[F1] Nuevo <br>Paciente</center></html>");
        btnF1.setFocusable(false);
        btnF2.setBounds(new Rectangle(110, 290, 95, 40));
        btnF2.setText("<html><center>[F2] Modificar<br>Paciente</center></html>");
        btnF2.setFocusable(false);
        btnF11.setBounds(new Rectangle(445, 290, 110, 40));
        btnF11.setText("<html><center>[F11] Selecionar<br>Paciente</center></html>");
        btnF11.setFocusable(false);
        btnEsc.setBounds(new Rectangle(565, 290, 80, 40));
        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);


        pnlDatos.add(lblNombre, null);
        pnlDatos.add(lblApeMaterno, null);
        pnlDatos.add(btnBuscar, null);
        pnlDatos.add(txtApePaterno, null);
        pnlDatos.add(txtApeMaterno, null);
        pnlDatos.add(txtNombre, null);
        pnlDatos.add(lblApePaterno, null);
        pnlDatos.add(cmbTipDoc, null);
        pnlDatos.add(lblTipNumDoc, null);
        pnlDatos.add(txtNumDoc, null);
        jContentPane.add(btnF4, null);
        jContentPane.add(btnF3, null);
        jContentPane.add(btnF2, null);
        jContentPane.add(btnF11, null);
        jContentPane.add(btnEsc, null);
        jContentPane.add(btnF1, null);
        jScrollPane1.getViewport().add(tblDatos, null);
        jContentPane.add(jScrollPane1, null);
        pnlTituloLista.add(lblTitulo, null);
        jContentPane.add(pnlTituloLista, null);
        jContentPane.add(pnlDatos, null);
        this.getContentPane().add(jContentPane, null);
    }
    
    /* ************************************************************************ */
    /*                           METODO initialize                              */
    /* ************************************************************************ */

    private void initialize(){
            cargaCombo();
            initTable();
      FarmaVariables.vAceptar = false;
    }
      
    /* ************************************************************************ */
    /*                         METODOS INICIALIZACION                           */
    /* ************************************************************************ */

    private void initTable() {
        tableModelListaDatos =
                new FarmaTableModel(ConstantsCentroMedico.columnsListaPacientes, ConstantsCentroMedico.columnsListaPacientes,
                                    0);
        FarmaUtility.initSimpleList(tblDatos, tableModelListaDatos, ConstantsCentroMedico.columnsListaPacientes);
        //cargaListaPacientes();
    }
    
    private void cargaListaPacientes() {
        try{
            UtilityAdmisionMedica.cargarListaCompPacientes(tableModelListaDatos,pTipoLista,vtaCAM,vtaPAM,false);
            FarmaUtility.ordenar(tblDatos, tableModelListaDatos, 7, FarmaConstants.ORDEN_ASCENDENTE);
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(this,""+ex,txtApePaterno);
        }
    }
    
    private void cargaCombo() {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbTipDoc, "cmbTipoDocumento", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_DOCUMENTO(?)",
                                   parametros, true, true);
    }
    
    /* ************************************************************************ */
    /*                           METODOS DE EVENTOS                             */
    /* ************************************************************************ */
    
    private void btnBuscar_actionPerformed(ActionEvent e) {
        
        vtaPAMBusqueda=new VtaPedidoAtencionMedica();
        String vTipdoc = FarmaLoadCVL.getCVLCode("cmbTipoDocumento", cmbTipDoc.getSelectedIndex());
        
        vtaPAMBusqueda.setApe_pat(txtApePaterno.getText().trim());
        vtaPAMBusqueda.setApe_mat(txtApeMaterno.getText().trim());
        vtaPAMBusqueda.setNombre (txtNombre.getText().trim());
        vtaPAMBusqueda.setApe_pat(txtApePaterno.getText().trim());
        vtaPAMBusqueda.setTip_documento(vTipdoc.trim());
        vtaPAMBusqueda.setNum_documento(txtNumDoc.getText().trim());
        
        if (validaDatos()) {
            try{
                UtilityAdmisionMedica.cargarListaCompPacientes(tableModelListaDatos, ConstantsCentroMedico.TIPO_BUSQUEDA_PACIENTE,vtaCAM,vtaPAMBusqueda,true);
                if(tableModelListaDatos.data.size()==0){
                    UtilityAdmisionMedica.cargarListaCompPacientes(tableModelListaDatos, ConstantsCentroMedico.TIPO_BUSQUEDA_PACIENTE,vtaCAM,vtaPAMBusqueda,false);
                    
                if(tableModelListaDatos.data.size()==0)
                FarmaUtility.showMessage(this,"No se encontraron resultados en la busqueda",txtApePaterno);  
                    else
                    FarmaUtility.ordenar(tblDatos, tableModelListaDatos, 7, FarmaConstants.ORDEN_ASCENDENTE);    
                }
                else
                FarmaUtility.ordenar(tblDatos, tableModelListaDatos, 7, FarmaConstants.ORDEN_ASCENDENTE);    
            }catch(Exception ex){
                log.error("", ex);
                FarmaUtility.showMessage(this,""+ex,txtApePaterno);
            }
        }
        
        FarmaUtility.moveFocus(txtApePaterno);
    }
    
    public boolean validaDatos(){
        boolean flag=true;
        int cantfiltros=0;
        
        if(!(vtaPAMBusqueda.getApe_pat().equals(""))){
            cantfiltros++;
        }
        if(!(vtaPAMBusqueda.getNombre().equals(""))){
            cantfiltros++;
        }
        if(!(vtaPAMBusqueda.getNum_documento().equals(""))){
            cantfiltros=+2;
        }
        if(cantfiltros<2){
            FarmaUtility.showMessage(this,"Debe ingresar los datos obligatorios",txtApePaterno);
            flag=false;
        }
        
        return flag;
    }

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




    private void chkKeyPressed(KeyEvent e){
      FarmaGridUtils.aceptarTeclaPresionada(e, tblDatos, null, 0);
        
      if (UtilityPtoVenta.verificaVK_F1(e)){
          if(!(pTipoLista.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_ACTUALIZAR))){  //operacion no valida para las actualizaciones    
              try{
                  //ArrayList lista=UtilityAdmisionMedica.obtieneBenficiario();
                  paciente=new BeanPaciente();  
                  paciente.setCodPaciente("0");//El paciente no existe 
                  paciente.setNumHistCli("");//El paciente no existe     
                  paciente.setTipDocumento(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_DNI/*((String)((ArrayList)lista.get(0)).get(0)).trim()*/);
                  paciente.setNumDocumento(""/*((String)((ArrayList)lista.get(0)).get(1)).trim()*/);
                  paciente.setApellidoPaterno(""/*((String)((ArrayList)lista.get(0)).get(2)).trim()*/);
                  paciente.setApellidoMaterno(""/*((String)((ArrayList)lista.get(0)).get(3)).trim()*/);
                  paciente.setNombre(""/*((String)((ArrayList)lista.get(0)).get(4)).trim()*/);
              /*}catch(SQLException ex){
                  FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+ ex.getMessage(), txtApePaterno);
                  paciente.setTipDocumento(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_DNI);*/
              }catch(Exception er){
                  FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+er.getMessage(), txtApePaterno);
                  paciente.setTipDocumento(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_DNI);
              }

              VariablesCentroMedico.vCodPaciente=paciente.getCodPaciente();
              DlgADMDatosPaciente dlgDtsPac = new DlgADMDatosPaciente(MyParentFrame,"",true,/*"N"*/ConstantsCentroMedico.TIPO_PACIENTE_NUEVO,paciente, TipoAtencionCM.ADMISION);
              dlgDtsPac.setLocationRelativeTo(MyParentFrame);
              dlgDtsPac.setVisible(true);
              if(FarmaVariables.vAceptar){
                  cerrarVentana(true);
              }
              if( (!(txtApePaterno.getText().equals(""))) || (!(txtNombre.getText().equals(""))) || (!(txtNumDoc.getText().equals("")))  ){    
                  try{
                        UtilityAdmisionMedica.cargarListaCompPacientes(tableModelListaDatos, ConstantsCentroMedico.TIPO_BUSQUEDA_PACIENTE,vtaCAM,vtaPAMBusqueda,true);
                      if(tableModelListaDatos.data.size()!=0)
                      FarmaUtility.ordenar(tblDatos, tableModelListaDatos, 7, FarmaConstants.ORDEN_ASCENDENTE);    
                  }catch(Exception ex){
                      log.error("", ex);
                      FarmaUtility.showMessage(this,""+ex,txtApePaterno);
                  }
              }else{
                  tableModelListaDatos.clearTable();
                  tableModelListaDatos.fireTableDataChanged();
              }
          }else{
              FarmaUtility.showMessage(this, "Opción no válida para actualizar", txtApePaterno);
          }
              
          
      }else if(UtilityPtoVenta.verificaVK_F2(e)){   
            cargarDatosPaciente(e);
      }else if(UtilityPtoVenta.verificaVK_F3(e)){   
            boolean band = imprimirHC();
            /*if(band){
                FarmaUtility.showMessage(this, "La Historia Clínica se ha impreso exitosamente.", null);
            }*/
      }else if(UtilityPtoVenta.verificaVK_F4(e)){
          int selecRow = getNumFilaSeleccionadaTabla();
          if(selecRow != -1){
              String codPaciente = FarmaUtility.getValueFieldArrayList(tableModelListaDatos.data, selecRow, 0).trim();
              DlgListadoAtencionesMedicas dlg = new DlgListadoAtencionesMedicas(MyParentFrame, "", false);
              dlg.setCodPaciente(codPaciente);
              dlg.setBandImpresiones(true);
              dlg.setVisible(true);
          }
      }else if(UtilityPtoVenta.verificaVK_F11(e)){
          if(!(pTipoLista.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_ACTUALIZAR)))
          cargarDatosPaciente(e);
          else
          FarmaUtility.showMessage(this, "Opción no válida para actualizar", txtApePaterno);    
                        
      }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
        cerrarVentana(false);
      }   
    }
    
    private boolean imprimirHC(){
        UtilityAtencionMedica utilAtencionMedica = new UtilityAtencionMedica();
        int selecRow = getNumFilaSeleccionadaTabla();
        if(selecRow != -1){
            String cod_Paciente = FarmaUtility.getValueFieldArrayList(tableModelListaDatos.data, selecRow, 0).trim();
            
            FacadeAtencioMedica facade = new FacadeAtencioMedica();
            
            String numAtencion = facade.obtenerNroAtencionMedica(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, cod_Paciente);
            boolean resultado = utilAtencionMedica.procesarImpresion(cod_Paciente, numAtencion);
            if(!resultado){
                FarmaUtility.showMessage(this, "Error al generar documento pdf de la Historia Clinica.\n"+
                                                   "Reintente, en caso persista el problema comuniquese con Mesa de Ayuda.", null);
            }else{
                return true;
            }
        }
        return false;
    }
    
    private void lblApePaterno_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtApePaterno);
    }
    
    private void lblTipNumDoc_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipDoc);
    }
    
    private void cmbTipDoc_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(txtNumDoc.isEnabled())
            FarmaUtility.moveFocus(txtNumDoc);
            else
            FarmaUtility.moveFocus(txtApePaterno);   
        }
        if(UtilityPtoVenta.verificaVK_F1(e) || UtilityPtoVenta.verificaVK_F2(e) ||
           UtilityPtoVenta.verificaVK_F3(e) || UtilityPtoVenta.verificaVK_F4(e) ||
           UtilityPtoVenta.verificaVK_F11(e) ||(e.getKeyCode() == KeyEvent.VK_ESCAPE))
        chkKeyPressed(e);
    }
    
    private void txtNumDoc_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if(txtNumDoc.getText().trim().length()>=8){
                btnBuscar.doClick();
                FarmaUtility.moveFocus(cmbTipDoc);
            }
            else
            FarmaUtility.moveFocus(txtApePaterno);
        }
        chkKeyPressed(e);
    }
    
    private void txtApePaterno_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtApeMaterno);
        }
        chkKeyPressed(e);
    }
    private void txtApeMaterno_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtNombre);
        }
        chkKeyPressed(e);
    }
    private void txtNombre_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            btnBuscar.doClick();
            FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }

    private void tblDatos_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    
    private void lblTitulo_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtApePaterno);
    }
    
    private void txtApePaterno_keyTyped(KeyEvent e) {
        FarmaUtility.admitirLetras(txtApePaterno, e);
    }
    private void txtApeMaterno_keyTyped(KeyEvent e) {
        FarmaUtility.admitirLetras(txtApeMaterno, e);
    }
    
    private void txtNombre_keyTyped(KeyEvent e) {
        FarmaUtility.admitirLetras(txtNombre, e);
    } 
    private void txtNumDoc_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtNumDoc, e);
    }
    
    private void cmbTipDoc_actionPerformed(/*ActionEvent e*/){
        String vTipDocPac = FarmaLoadCVL.getCVLCode("cmbTipoDocumento", cmbTipDoc.getSelectedIndex());
        if(vTipDocPac.equals(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NR) ||
           vTipDocPac.equals(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NA)){
            txtNumDoc.setText("");
            flagDNIPac=false;
        }else{
            flagDNIPac=true;
        }
        txtNumDoc.setEnabled(flagDNIPac);
        txtNumDoc.setEditable(flagDNIPac);
    }
    
    
    
    private void cerrarVentana(boolean pAceptar){
      FarmaVariables.vAceptar = pAceptar;
      this.setVisible(false);
      this.dispose();
    }

    /* ************************************************************************ */
    /*                      METODOS DE LOGICA DE NEGOCIO                        */
    /* ************************************************************************ */
    private int getNumFilaSeleccionadaTabla(){
        int cantFila = tblDatos.getRowCount();
        if(cantFila == 0){
            FarmaUtility.showMessage(this, "No hay pacientes encontrados.", tblDatos);
            FarmaUtility.moveFocus(cmbTipDoc);
            return -1;
        }
        int seleccion = tblDatos.getSelectedRow();
        if(seleccion == -1){
            FarmaUtility.showMessage(this, "No ha seleccionado un Paciente.", tblDatos);
            FarmaUtility.moveFocus(cmbTipDoc);
        }
        return seleccion;
    }


    private void cargarDatosPaciente(KeyEvent e) {
        
        //log.info("Se presiono F2"); 
        int selecRow = getNumFilaSeleccionadaTabla();
        
        // carga datos rac to local
        if(selecRow>=0){
            
            log.info("cod paciente a obtener "+FarmaUtility.getValueFieldArrayList(tableModelListaDatos.data, selecRow, 0).trim());
            
            if(FarmaUtility.getValueFieldArrayList(tableModelListaDatos.data, selecRow, 9).trim().length()==0){
                UtilityAdmisionMedica.getPacienteRacToLocal(FarmaUtility.getValueFieldArrayList(tableModelListaDatos.data, selecRow, 0).trim());
            }   
            
            paciente=new BeanPaciente();  
         
            FacadeAtencioMedica facade = new FacadeAtencioMedica();
            paciente = facade.obtenerDatosPaciente(FarmaVariables.vCodGrupoCia, 
                                                   FarmaUtility.getValueFieldArrayList(tableModelListaDatos.data, selecRow, 0).trim());
            
            if(paciente==null) {
                FarmaUtility.showMessage(this,"No se pudo obtener los datos del paciente seleccionado.", txtNumDoc);
            }
            else
            {
                VariablesCentroMedico.vCodPaciente = paciente.getCodPaciente();
                DlgADMDatosPaciente dlgDtsPac;

                if (pTipoLista.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_ACTUALIZAR)) {
                    dlgDtsPac =
                            new DlgADMDatosPaciente(MyParentFrame, "", true, ConstantsCentroMedico.TIPO_PACIENTE_ACTUALIZAR,
                                                    paciente, TipoAtencionCM.ADMISION);
                } else if (UtilityPtoVenta.verificaVK_F11(e)) {
                    dlgDtsPac =
                            new DlgADMDatosPaciente(MyParentFrame, "", true, ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR,
                                                    paciente, TipoAtencionCM.ADMISION);
                } else {
                    dlgDtsPac =
                            new DlgADMDatosPaciente(MyParentFrame, "", true, ConstantsCentroMedico.TIPO_PACIENTE_CONTINUADOR,
                                                    paciente, TipoAtencionCM.ADMISION);
                }
                dlgDtsPac.setLocationRelativeTo(MyParentFrame);
                dlgDtsPac.setVisible(true);
                if (FarmaVariables.vAceptar) {
                    cerrarVentana(true);
                }
                //btnBuscar.doClick();
                try{
                    UtilityAdmisionMedica.cargarListaCompPacientes(tableModelListaDatos, ConstantsCentroMedico.TIPO_BUSQUEDA_PACIENTE,vtaCAM,vtaPAMBusqueda,true);
                    if(tableModelListaDatos.data.size()!=0)
                    FarmaUtility.ordenar(tblDatos, tableModelListaDatos, 7, FarmaConstants.ORDEN_ASCENDENTE);    
                }catch(Exception ex){
                    log.error("", ex);
                    FarmaUtility.showMessage(this,""+ex,txtApePaterno);
                }
            }
        }
    }    
    
    /*

    paciente.setCodPaciente(tblDatos.getValueAt(selecRow, 0).toString().trim());
    paciente.setTipDocumento(tblDatos.getValueAt(selecRow, 1).toString().trim());
    paciente.setNumDocumento(tblDatos.getValueAt(selecRow, 3).toString().trim());
    paciente.setApellidoPaterno(tblDatos.getValueAt(selecRow, 4).toString().trim());
    paciente.setApellidoMaterno(tblDatos.getValueAt(selecRow, 5).toString().trim());
    paciente.setNombre(tblDatos.getValueAt(selecRow, 6).toString().trim());
    paciente.setSexo(tblDatos.getValueAt(selecRow, 9).toString().trim());
    paciente.setEstCivil(tblDatos.getValueAt(selecRow, 10).toString().trim());
    paciente.setFecNacimiento(tblDatos.getValueAt(selecRow, 11).toString().trim());
    paciente.setDireccion(tblDatos.getValueAt(selecRow, 15).toString().trim());
    paciente.setGradoInstruccion(tblDatos.getValueAt(selecRow, 16).toString().trim());
    paciente.setOcupacion(tblDatos.getValueAt(selecRow, 17).toString().trim());
    paciente.setCentroEduLab(tblDatos.getValueAt(selecRow, 18).toString().trim());
    paciente.setEmail(tblDatos.getValueAt(selecRow, 19).toString().trim());
    paciente.setTelFijo(tblDatos.getValueAt(selecRow, 20).toString().trim());
    paciente.setTelCelular(tblDatos.getValueAt(selecRow, 21).toString().trim());
    paciente.setTipAcom(tblDatos.getValueAt(selecRow, 22).toString().trim());
    paciente.setNomAcom(tblDatos.getValueAt(selecRow, 23).toString().trim());
    paciente.setTipDocAcom(tblDatos.getValueAt(selecRow, 24).toString().trim());
    paciente.setNumDocAcom(tblDatos.getValueAt(selecRow, 25).toString().trim());
    paciente.setNumHCFisica(tblDatos.getValueAt(selecRow, 26).toString().trim());
    paciente.setFecHCFisica(tblDatos.getValueAt(selecRow, 27).toString().trim());
    paciente.setFactorRH(tblDatos.getValueAt(selecRow, 28).toString().trim());
    paciente.setGrupoSan(tblDatos.getValueAt(selecRow, 29).toString().trim());
    paciente.setNumHistCli(tblDatos.getValueAt(selecRow, 30).toString().trim());

    paciente.setDepUbigeo(tblDatos.getValueAt(selecRow, 31).toString().trim());
    paciente.setPvrUbigeo(tblDatos.getValueAt(selecRow, 32).toString().trim());
    paciente.setDisUbigeo(tblDatos.getValueAt(selecRow, 33).toString().trim());
    paciente.setDepLugNac(tblDatos.getValueAt(selecRow, 34).toString().trim());
    paciente.setPvrLugNac(tblDatos.getValueAt(selecRow, 35).toString().trim());
    paciente.setDisLugNac(tblDatos.getValueAt(selecRow, 36).toString().trim());
    paciente.setDepLugPro(tblDatos.getValueAt(selecRow, 37).toString().trim());
    paciente.setPvrLugPro(tblDatos.getValueAt(selecRow, 38).toString().trim());
    paciente.setDisLugPro(tblDatos.getValueAt(selecRow, 39).toString().trim());*/


}
