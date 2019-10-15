package mifarma.ptoventa.centromedico;

import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import javax.swing.SwingConstants;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.reference.ConstantsCentroMedico;
import mifarma.ptoventa.centromedico.reference.DBAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgListaEspera extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgListaEspera.class);
    private Frame myParentFrame;
    private BorderLayout borderLayout = new BorderLayout();
    private JPanelWhite pnlContenedor = new JPanelWhite();
    private JPanelTitle pnlTituloTablaPacientes = new JPanelTitle();
    private JScrollPane scpPacientes = new JScrollPane();
    private JTable tblPacientes = new JTable();
    private FarmaTableModel modelTblPacientes;
    private JLabelWhite lblListaPacientes = new JLabelWhite();
    private TipoAtencionCM tipoLista;
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabel lblFecha = new JLabel();
    private JLabel lblEtiquetaFecha = new JLabel();
    private JLabelOrange lblHCFisica = new JLabelOrange();
    private JLabelOrange lblEspecialidadPaciente = new JLabelOrange();
    private JLabelOrange lblMedicoPaciente = new JLabelOrange();
    private JLabel txtTieneHCFisica = new JLabel();
    private JLabel txtEspecialidadPaciente = new JLabel();
    private JLabel txtMedicoPaciente = new JLabel();
    private JLabelOrange lblNroHCFisica = new JLabelOrange();
    private JLabel txtNroHCFisica = new JLabel();
    private String pCodMedico = "";
    
    String fecActual="";


    public DlgListaEspera() {
        this(null, "", false, null);
    }

    public DlgListaEspera(Frame parent, String title, boolean modal, TipoAtencionCM tipoLista) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.tipoLista = tipoLista;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        //this.setSize(new Dimension(935, 360));
        this.setSize(new Dimension(799, 380));
        this.getContentPane().setLayout(borderLayout);
        this.setTitle("Lista de Espera");
        
        pnlContenedor.setBounds(new Rectangle(0, 0, 675, 405));
        
        // ENCABEZADO

        lblHCFisica.setText("HC.Fisica: ");
        lblHCFisica.setBounds(new Rectangle(15, 60, 85, 20));
        lblHCFisica.setFont(new Font("SansSerif", 1, 12));
        lblHCFisica.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEspecialidadPaciente.setText("Especialidad: ");
        lblEspecialidadPaciente.setBounds(new Rectangle(15, 10, 85, 20));
        lblEspecialidadPaciente.setFont(new Font("SansSerif", 1, 12));
        lblEspecialidadPaciente.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblEspecialidadPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMedicoPaciente.setText("Medico: ");
        lblMedicoPaciente.setBounds(new Rectangle(15, 35, 85, 20));
        lblMedicoPaciente.setFont(new Font("SansSerif", 1, 12));
        lblMedicoPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTieneHCFisica.setBounds(new Rectangle(100, 60, 45, 20));
        txtTieneHCFisica.setFont(new Font("SansSerif", 0, 12));
        txtEspecialidadPaciente.setBounds(new Rectangle(100, 10, 290, 20));
        txtEspecialidadPaciente.setFont(new Font("SansSerif", 0, 12));
        txtMedicoPaciente.setBounds(new Rectangle(100, 35, 455, 20));
        txtMedicoPaciente.setFont(new Font("SansSerif", 0, 12));
        lblNroHCFisica.setText("Nro HC.Fisica:");
        lblNroHCFisica.setBounds(new Rectangle(155, 60, 85, 20));
        lblNroHCFisica.setFont(new Font("SansSerif", 1, 12));
        lblNroHCFisica.setHorizontalAlignment(SwingConstants.RIGHT);
        txtNroHCFisica.setBounds(new Rectangle(245, 60, 145, 20));
        txtNroHCFisica.setFont(new Font("SansSerif", 0, 12));
        lblEtiquetaFecha.setText("Fecha");
        lblEtiquetaFecha.setBounds(new Rectangle(415, 10, 40, 20));
        lblEtiquetaFecha.setFont(new Font("SansSerif", 1, 12));
        lblEtiquetaFecha.setForeground(new Color(255, 130, 14));
        lblFecha.setBounds(new Rectangle(460, 10, 70, 20));
        lblFecha.setFont(new Font("SansSerif", 0, 12));


        // TABLA DE LISTA
        pnlContenedor.add(txtNroHCFisica, null);
        pnlContenedor.add(lblNroHCFisica, null);
        pnlContenedor.add(txtMedicoPaciente, null);
        pnlContenedor.add(txtEspecialidadPaciente, null);
        pnlContenedor.add(txtTieneHCFisica, null);
        pnlContenedor.add(lblMedicoPaciente, null);
        pnlContenedor.add(lblEspecialidadPaciente, null);
        pnlContenedor.add(lblHCFisica, null);
        pnlContenedor.add(lblEtiquetaFecha, null);
        pnlContenedor.add(lblFecha, null);
        pnlTituloTablaPacientes.add(lblListaPacientes, null);
        pnlContenedor.add(pnlTituloTablaPacientes, null);
        scpPacientes.getViewport().add(tblPacientes, null);
        pnlContenedor.add(scpPacientes, null);
        pnlContenedor.add(lblEsc, null);
        pnlContenedor.add(lblF11, null);
        pnlContenedor.add(lblF5, null);
        pnlContenedor.add(lblF1, null);
        lblListaPacientes.setText("Lista de Pacientes");
        lblListaPacientes.setBounds(new Rectangle(5, 0, 125, 20));
        lblListaPacientes.setHorizontalAlignment(SwingConstants.LEFT);
        lblListaPacientes.setHorizontalTextPosition(SwingConstants.LEFT);
        pnlTituloTablaPacientes.setBounds(new Rectangle(10, 90, 775, 20));
        tblPacientes.setFont(new Font("SansSerif", 0, 11));
        scpPacientes.setBounds(new Rectangle(10, 110, 775, 200));
        scpPacientes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // TECLAS DE FUNCION

        lblEsc.setBounds(new Rectangle(115, 325, 117, 19));
        lblEsc.setName("ESC");
        lblEsc.setText("[ ESC ] Salir");

        lblF1.setBounds(new Rectangle(245, 325, 117, 19));
        lblF1.setName("F1");
        lblF1.setText("[ F1 ] Anular");


        lblF5.setBounds(new Rectangle(370, 325, 117, 19));
        lblF5.setName("F5");
        lblF5.setText("[ F5 ] Actualizar");

        lblF11.setBounds(new Rectangle(500, 325, 117, 19));
        lblF11.setName("F11");
        lblF11.setText("[ F11 ] Atender");

        this.getContentPane().add(pnlContenedor, BorderLayout.CENTER);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        
        lblF5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                funcionF5();
            }
        });
        
        lblF11.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                funcionF11();
            }
        });
        
        tblPacientes.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                mostrarTitulo();
            }
            
            public void keyPressed(KeyEvent e) {
                chkKeyPressed(e);
            }
        });
        tblPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblPacientes_mouseClicked(e);
            }
        });
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        cargarDatosPantalla();
        if(tblPacientes.getRowCount()>0){
            FarmaUtility.moveFocusJTable(tblPacientes);
            mostrarTitulo();
        }
    }
    
    private void this_windowClosing(WindowEvent e) {
        //FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
        
    }
    
    private void initialize(){
        crearTablaPaciente();
        try {
            fecActual=FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            //lblFecha.setText(fecActual);
            fecActual = " - Fecha : "+fecActual;
        } catch (Exception e) {
            fecActual = "";
        }
        this.lblEtiquetaFecha.setVisible(false);
        this.lblFecha.setVisible(false);
    }
    
    private void crearTablaPaciente(){
        int anchoFecha = 0;
        if(TipoAtencionCM.CONSULTA.equals(tipoLista)){
            anchoFecha = 80;
        }
        FarmaColumnData[] columnasListaEspera = { new FarmaColumnData("Fecha", anchoFecha, JLabel.CENTER),  //0
                                                  new FarmaColumnData("Hora", /*60*/ (80-(anchoFecha/4))  , JLabel.CENTER),       //0
                                                  new FarmaColumnData("Nro HC", /*120*/ (120-(anchoFecha/4)), JLabel.CENTER),     //1
                                                  new FarmaColumnData("Paciente", /*395*/ (375-(anchoFecha/2)), JLabel.LEFT),    //2
                                                  new FarmaColumnData("Edad", 40, JLabel.CENTER),       //3
                                                  new FarmaColumnData("Estado", 140, JLabel.LEFT),      //4
                                                  new FarmaColumnData("Especialidad", 0, JLabel.LEFT),  //5
                                                  new FarmaColumnData("Médico", 0, JLabel.LEFT),        //6
                                                  //KMONCADA
                                                  new FarmaColumnData("CodigoPaciente", 0, JLabel.LEFT),//7 
                                                  new FarmaColumnData("COD_ESTADO", 0, JLabel.CENTER),  //8
                                                  new FarmaColumnData("HCFISICA", 0, JLabel.CENTER),    //9
                                                  new FarmaColumnData("NROHCFISICA", 0, JLabel.CENTER), //10
                                                  new FarmaColumnData("NRO_CONSULTA", 0, JLabel.CENTER) //11
                                                  };
        
        modelTblPacientes = new FarmaTableModel(columnasListaEspera, UtilityPtoVenta.obtenerDefaultValuesTabla(columnasListaEspera.length),0);
        FarmaUtility.initSimpleList(tblPacientes, modelTblPacientes, columnasListaEspera);
                
        /*modelTblPacientes = new FarmaTableModel(ConstantsCentroMedico.columnasListaEspera, UtilityPtoVenta.obtenerDefaultValuesTabla(ConstantsCentroMedico.columnasListaEspera.length),0);
        FarmaUtility.initSimpleList(tblPacientes, modelTblPacientes, ConstantsCentroMedico.columnasListaEspera);*/
        
    }
    
    private void cargarDatosPantalla(){
        
        if(TipoAtencionCM.ADMISION.equals(tipoLista)){
            this.setTitle("Lista de Espera Admisión "+fecActual);
        }else if(TipoAtencionCM.TRIAJE.equals(tipoLista)){
            this.setTitle("Lista de Espera Admisión "+fecActual);
        }else if(TipoAtencionCM.CONSULTA.equals(tipoLista)){
            this.setTitle("Lista de Espera Consulta "+fecActual);
        }
        cargarTablaLista();
    }
    
    private void cargarTablaLista(){
        String tipoEstado = "";
        if(TipoAtencionCM.ADMISION.equals(tipoLista)){
            tipoEstado = ConstantsCentroMedico.LISTA_ESPERA_ADMISION;
        }else if(TipoAtencionCM.TRIAJE.equals(tipoLista)){
            tipoEstado = ConstantsCentroMedico.LISTA_ESPERA_ADMISION;
        }else if(TipoAtencionCM.CONSULTA.equals(tipoLista)){
            tipoEstado = ConstantsCentroMedico.LISTA_ESPERA_CONSULTA;
            lblF1.setVisible(false);
        }
        UtilityAtencionMedica.cargarListaEsperaPacientes(modelTblPacientes, tipoEstado, getPCodMedico());
    }
    
    
    
    private int getNumFilaSeleccionadaTabla(){
        int cantFila = tblPacientes.getRowCount();
        if(cantFila == 0){
            FarmaUtility.showMessage(this, "No hay pacientes en espera.", tblPacientes);
            return -1;
        }
        int seleccion = tblPacientes.getSelectedRow();
        if(seleccion == -1){
            FarmaUtility.showMessage(this, "No ha seleccionado un Paciente.", tblPacientes);
        }
        return seleccion;
    }
    
    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        } else if (UtilityPtoVenta.verificaVK_F1(e) && lblF1.isVisible()) {
            funcionF1();
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            funcionF5();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            funcionF11();
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            funcionF11();
        }
    }
    
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    private void tblPacientes_mouseClicked(MouseEvent e) {
        mostrarTitulo();
        if(e.getClickCount()==2){
            funcionF11();
        }
    }
    
    private void mostrarTitulo(){
        int filaSeleccionada = tblPacientes.getSelectedRow();
        String especialidad = "";
        String medico = "";
        String hcFisica = "";
        String nroHCFisica = "";
        if(filaSeleccionada!=-1){
            especialidad = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, filaSeleccionada, 6);
            medico = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, filaSeleccionada, 7);
            hcFisica = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, filaSeleccionada, 10);
            nroHCFisica = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, filaSeleccionada, 11);
        }
        txtEspecialidadPaciente.setText(especialidad);
        txtMedicoPaciente.setText(medico);
        txtTieneHCFisica.setText(hcFisica);
        txtNroHCFisica.setText(nroHCFisica);
        //lblFecha.setText(fecAct);
        
    }
    
    private void funcionF1(){
        int selecRow = getNumFilaSeleccionadaTabla();
        if(selecRow != -1){
            if(JConfirmDialog.rptaConfirmDialog(this, 
                                                "¿Desea anular la solicitud de consulta?")){            
                /*modelTblPacientes.deleteRow(selecRow);
                modelTblPacientes.fireTableDataChanged();
                FarmaUtility.showMessage(this, "Se eliminó correctamente.", tblPacientes);*/
                try{
                    String nroSolicitud = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 12);
                    log.info("nroSolicitud  : "+nroSolicitud);
                    String codRpta = UtilityAdmisionMedica.anularAtencionMedica(nroSolicitud);
                    FarmaUtility.aceptarTransaccion();    
                    
                        
                    try {
                        String pCadena = DBAdmisionMedica.getDatosCompVentaMedica(nroSolicitud);
                        if (!pCadena.equalsIgnoreCase("N")) {
                            String[] vDato = pCadena.split("@");
                            UtilityAtencionMedica.registraEstadoCompAtencionMedica(vDato[0].trim(), vDato[1].trim());
                        }
                    } catch (Exception sqle) {
                        // TODO: Add catch code
                        log.info(sqle.getMessage());
                    }
                        
                    
                    
                    cargarTablaLista();
                }catch (SQLException sql) {
                    FarmaUtility.liberarTransaccion();
                      if (sql.getErrorCode() > 20000) {
                      FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                               null);
                      } 
                      else {
                      FarmaUtility.showMessage(this, "Error al anular la solicitud de atención.\n" + sql.getMessage(), null);
                      log.error("", sql);
                      }
                          
                }catch(Exception er){
                          FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+er.getMessage(), null);  
                          log.error("", er);
                }
                   
            }
            
        }
    }
    
    private void funcionF5(){
        int seleccionado = tblPacientes.getSelectedRow();
        cargarTablaLista();
        if(seleccionado != -1)
            tblPacientes.setRowSelectionInterval(seleccionado, seleccionado);
    }
    
    private void funcionF11(){
        int selecRow = getNumFilaSeleccionadaTabla();
        if(selecRow != -1){
            String codEstado = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 9);
            if(ConstantsCentroMedico.ATE_MEDICA_PEND_TRIAJE.equalsIgnoreCase(codEstado)){
                //FarmaUtility.showMessage(this, "PROGRAMAR PARA ATENCION DE TRIAJE", tblPacientes);
                String nroSolicitud = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 12);
                DlgADMRegistroTriaje dlgRegTri = new DlgADMRegistroTriaje(myParentFrame,"",true,nroSolicitud);
                dlgRegTri.setLocationRelativeTo(myParentFrame);
                dlgRegTri.setVisible(true);                
            }else if(ConstantsCentroMedico.ATE_MEDICA_PEND_ATENCION.equalsIgnoreCase(codEstado)){
                if(JConfirmDialog.rptaConfirmDialog(this, 
                                                    "¿Desea enviar para consulta?")){
                    String nroSolicitud = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 12);
                    UtilityAtencionMedica.actualizarEstadoSolicitudAtencion(this, nroSolicitud, ConstantsCentroMedico.ATE_MEDICA_EN_CONSULTA);                                        
                }
            }else if(ConstantsCentroMedico.ATE_MEDICA_EN_CONSULTA.equalsIgnoreCase(codEstado) || ConstantsCentroMedico.ATE_MEDICA_GUARDADA.equalsIgnoreCase(codEstado)){
                realizarAtencionPaciente();
            }
            cargarTablaLista();
        }else{
            FarmaUtility.showMessage(this, "Centro Medico:\nNo ha seleccionado un paciente.", tblPacientes);
        }
    }

    private void realizarAtencionPaciente(){
        int selecRow = getNumFilaSeleccionadaTabla();
        String codEstado = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 9);
        boolean isAtencionNueva = ConstantsCentroMedico.ATE_MEDICA_EN_CONSULTA.equalsIgnoreCase(codEstado);
        String codPaciente = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 8);//cod paciente
        String nroAtencion = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 12);//nro atencion
        boolean isImpresion = false;
        (new UtilityAtencionMedica()).realizarAtencionPaciente(myParentFrame, this, codPaciente, nroAtencion, getPCodMedico(), isAtencionNueva, isImpresion);
    }

    public String getPCodMedico() {
        return pCodMedico;
    }

    public void setPCodMedico(String pCodMedico) {
        this.pCodMedico = pCodMedico;
    }
}
