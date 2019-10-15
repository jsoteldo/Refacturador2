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
import java.awt.Font;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import javax.swing.SwingConstants;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.reference.ConstantsCentroMedico;
import mifarma.ptoventa.centromedico.reference.UtilityAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgADMTrazabilidad extends JDialog {

    @SuppressWarnings("compatibility:-112453352585881827")
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DlgADMTrazabilidad.class);
    private Frame myParentFrame;
    private BorderLayout borderLayout = new BorderLayout();
    private JPanelWhite pnlContenedor = new JPanelWhite();
    private JPanelTitle pnlTituloTablaPacientes = new JPanelTitle();
    private JScrollPane scpPacientes = new JScrollPane();
    private JTable tblPacientes = new JTable();
    private FarmaTableModel modelTblPacientes;
    private JLabelWhite lblListaPacientes = new JLabelWhite();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction lblF3 = new JLabelFunction();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private JTextFieldSanSerif txtFecFin = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtFecIni = new JTextFieldSanSerif();
    private JButton btnBuscar = new JButton();
    private JButtonLabel lblFecIni = new JButtonLabel();
    private JButtonLabel lblFecFin = new JButtonLabel();
    
    

    public DlgADMTrazabilidad() {
        this(null, "", false);
    }

    public DlgADMTrazabilidad(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        //this.setSize(new Dimension(935, 360));
        this.setSize(new Dimension(865, 360));
        this.getContentPane().setLayout(borderLayout);
        this.setTitle("Trazabilidad de Solicitudes de Atención Médica");
        
        pnlContenedor.setBounds(new Rectangle(0, 0, 675, 405));


        // TABLA DE LISTA
        lblListaPacientes.setText("Lista de Pacientes");
        lblListaPacientes.setBounds(new Rectangle(5, 0, 125, 20));
        lblListaPacientes.setHorizontalAlignment(SwingConstants.LEFT);
        lblListaPacientes.setHorizontalTextPosition(SwingConstants.LEFT);
        pnlTituloTablaPacientes.setBounds(new Rectangle(10, 60, 835, 20));
        tblPacientes.setFont(new Font("SansSerif", 0, 11));
        scpPacientes.setBounds(new Rectangle(10, 80, 835, 200));
        scpPacientes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scpPacientes.getViewport().add(tblPacientes, null);
        pnlTituloTablaPacientes.add(lblListaPacientes, null);
        pnlContenedor.add(pnlTituloTablaPacientes, null);
        pnlContenedor.add(scpPacientes, null);
        jPanelHeader1.setBounds(new Rectangle(10, 5, 835, 45));
        txtFecFin.setBounds(new Rectangle(445, 15, 110, 20));
        txtFecFin.setLengthText(10);
        txtFecFin.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                txtFecFin_keyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                txtFecFin_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFecFin_keyReleased(e);
            }
        });
        txtFecIni.setBounds(new Rectangle(295, 15, 110, 20));
        txtFecIni.setLengthText(10);
        txtFecIni.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                txtFecIni_keyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                txtFecIni_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFecIni_keyReleased(e);
            }
        });
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(610, 15, 85, 20));
        btnBuscar.setFont(new Font("Dialog", 1, 11));
        btnBuscar.setMnemonic('B');
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        lblFecIni.setText("Rango de Fechas");
        lblFecIni.setMnemonic('R');
        lblFecIni.setBounds(new Rectangle(190, 15, 100, 20));
        lblFecIni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFecIni_actionPerformed(e);
            }
        });
        lblFecFin.setText("Hasta");
        lblFecFin.setMnemonic('H');
        lblFecFin.setVisible(false);
        lblFecFin.setBounds(new Rectangle(170, 15, 34, 20));
        lblFecFin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFecFin_actionPerformed(e);
            }
        });
        jPanelHeader1.add(lblFecFin, null);
        jPanelHeader1.add(lblFecIni, null);
        jPanelHeader1.add(btnBuscar, null);
        jPanelHeader1.add(txtFecIni, null);
        jPanelHeader1.add(txtFecFin, null);
        pnlContenedor.add(jPanelHeader1, null);
        
        // TECLAS DE FUNCION
        //pnlContenedor.add(lblF3, null);
        //pnlContenedor.add(lblF2, null);
        //pnlContenedor.add(lblF1, null);
        
        //lblEsc.setBounds(new Rectangle(80, 280, 117, 19));
        pnlContenedor.add(lblF5, null);
        pnlContenedor.add(lblF11, null);
        pnlContenedor.add(lblEsc, null);
        lblEsc.setBounds(new Rectangle(695, 300, 117, 19));
        lblEsc.setName("ESC");
        lblEsc.setText("[ ESC ] Salir");
        
        lblF1.setBounds(new Rectangle(210, 280, 117, 19));
        lblF1.setName("F1");
        lblF1.setText("[ F1 ] Anular");

        lblF2.setBounds(new Rectangle(340, 280, 135, 19));
        lblF2.setName("F2");
        lblF2.setText("[ F2 ]  Eliminar Triaje");

        lblF3.setBounds(new Rectangle(485, 280, 135, 19));
        lblF3.setName("F3");
        lblF3.setText("[ F3 ]  Registrar Triaje");

        lblF5.setBounds(new Rectangle(415, 300, 135, 20));
        lblF5.setName("F5");
        lblF5.setText("[ F5 ] Imprimir Receta");

        lblF11.setBounds(new Rectangle(565, 300, 117, 19));
        lblF11.setName("F11");
        lblF11.setText("[ F11 ] VISUALIZAR");

        this.getContentPane().add(pnlContenedor, BorderLayout.CENTER);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        
        lblF11.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                lblF11_mouseClicked(e);
            }
        });
        
        lblF5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                lblF5_mouseClicked(e);
            }
        });
        
        tblPacientes.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblPacientes_keyPressed(e);
            }
        });
        tblPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblPacientes_mouseClicked(e);
            }
        });
    }
    
    private void txtFecIni_keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (!(Character.isDigit(keyChar) ||  keyChar == e.VK_SLASH))
            e.consume();

    }

    private void txtFecIni_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            FarmaUtility.moveFocus(txtFecFin);
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            cerrarVentana(false);
        chkKeyPressed(e);
    }

    private void txtFecIni_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFecIni, e);
    }

    private void txtFecFin_keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (!(Character.isDigit(keyChar) ||  keyChar == e.VK_SLASH))
            e.consume();
    }

    private void txtFecFin_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnBuscar.doClick();
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            cerrarVentana(false);
        chkKeyPressed(e);
    }

    private void txtFecFin_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFecFin, e);
    }


    private void lblFecIni_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFecIni);
    }

    private void lblFecFin_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFecFin);
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        if (validarFechas()) {
            cargaListaTrazabilidad(txtFecIni.getText().trim(), txtFecFin.getText().trim());
        }
    }
    
    private boolean validarFechas() {
        //quitando los espacios en blanco si es que las ubiera
        txtFecIni.setText(txtFecIni.getText().trim());
        txtFecFin.setText(txtFecFin.getText().trim());

        boolean retorno = true;
        if (!FarmaUtility.validarRangoFechas(this, txtFecIni, txtFecFin, false, true, true, true))
            retorno = false;
        //log.info("retorno : "+retorno);
        return retorno;


    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtFecIni);
    }
    
    private void this_windowClosing(WindowEvent e) {
        //FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    
    private void initialize(){
        crearTablePaciente();
        cargarDatosPantalla();
    }
    
    private void cargarDatosPantalla(){  
        
    }
    
    
    private void crearTablePaciente(){

        /*modelTblPacientes = new FarmaTableModel(ConstantsCentroMedico.columnasListaEspera, UtilityPtoVenta.obtenerDefaultValuesTabla(ConstantsCentroMedico.columnasListaEspera.length),0);
        FarmaUtility.initSimpleList(tblPacientes, modelTblPacientes, ConstantsCentroMedico.columnasListaEspera);
        ArrayList<String> lstPrueba = new ArrayList<String>();
        lstPrueba.add("00:00:00");
        lstPrueba.add("0000000001");
        lstPrueba.add("MERCURY QUEEN FREDDIE");
        lstPrueba.add("38");
        lstPrueba.add("CONTINUADOR");
        lstPrueba.add("CARDIOLOGIA");
        lstPrueba.add("CHRISTIAAN BARNARD");
        lstPrueba.add("ESPERA TRIAJE");
        modelTblPacientes.data.add(lstPrueba);
        lstPrueba = new ArrayList<String>();
        lstPrueba.add("00:01:00");
        lstPrueba.add("0000000002");
        lstPrueba.add("JACKSON THRILLER MICHAEL");
        lstPrueba.add("50");
        lstPrueba.add("CONTINUADOR");
        lstPrueba.add("NEUROLOGIA");
        lstPrueba.add("SIGMUND FREUD");
        lstPrueba.add("ESPERA CONSULTORIO");
        modelTblPacientes.data.add(lstPrueba);
        modelTblPacientes.fireTableDataChanged();*/
        FarmaColumnData[] columnasListaEspera = { new FarmaColumnData("Fecha", 100, JLabel.CENTER),       //0
                                                  new FarmaColumnData("Hora", 90, JLabel.CENTER),       //1
                                                  new FarmaColumnData("Nro HC", 100, JLabel.CENTER),     //2
                                                  new FarmaColumnData("Paciente", 330, JLabel.LEFT),    //3
                                                  new FarmaColumnData("Edad", 50, JLabel.CENTER),       //4
                                                  new FarmaColumnData("Estado", 100, JLabel.LEFT),      //5
                                                  new FarmaColumnData("Especialidad", 0, JLabel.LEFT),  //6
                                                  new FarmaColumnData("Médico", 200, JLabel.LEFT),        //7
 
                                                  new FarmaColumnData("CodigoPaciente", 0, JLabel.LEFT),//8 
                                                  new FarmaColumnData("COD_ESTADO", 0, JLabel.CENTER),  //9
                                                  new FarmaColumnData("HCFISICA", 0, JLabel.CENTER),    //10
                                                  new FarmaColumnData("NROHCFISICA", 0, JLabel.CENTER), //11
                                                  new FarmaColumnData("NRO_CONSULTA", 0, JLabel.CENTER), //12
                                                  new FarmaColumnData("IND ACTIVO", 0, JLabel.CENTER), //13
                                                  
                                                  new FarmaColumnData("COD_GRUPO_CIA", 0, JLabel.CENTER), //14
                                                  new FarmaColumnData("COD_CIA", 0, JLabel.CENTER), //15
                                                  new FarmaColumnData("COD_LOCAL", 0, JLabel.CENTER), //16
                                                  new FarmaColumnData("NUM_ATENCION", 0, JLabel.CENTER), //17
                                                  new FarmaColumnData("COD_PACIENTE", 0, JLabel.CENTER) //18
                                                  
                                                  };
        
        modelTblPacientes = new FarmaTableModel(columnasListaEspera, UtilityPtoVenta.obtenerDefaultValuesTabla(columnasListaEspera.length),0);
        FarmaUtility.initSimpleList(tblPacientes, modelTblPacientes, columnasListaEspera);
        
        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecFin = formato.format(calendario.getTime());
        //calendario.add(Calendar.DAY_OF_YEAR, -30); //restando 30 dias
        String fecIni = formato.format(calendario.getTime());

        txtFecIni.setText(fecIni);
        txtFecFin.setText(fecFin);

        cargaListaTrazabilidad(fecIni, fecFin);
    }
    
    private int getNumFilaSeleccionadaTabla(){
        int cantFila = tblPacientes.getRowCount();
        if(cantFila == 0){
            FarmaUtility.showMessage(this, "No hay resultados en la busqueda.", tblPacientes);
            return -1;
        }
        int seleccion = tblPacientes.getSelectedRow();
        if(seleccion == -1){
            FarmaUtility.showMessage(this, "Debe seleccionar un registro.", tblPacientes);
        }
        return seleccion;
    }
    
    private void tblPacientes_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    
    private void chkKeyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblPacientes, null, 0);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        /*
        } else if (UtilityPtoVenta.verificaVK_F1(e)) {
            funcionF1();
        } else if (e.getKeyCode() == KeyEvent.VK_F2) {    
            funcionF2();
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {    
            funcionF3();
        */
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            funcionF5();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //funcionF11();
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            funcionF11();
        }
    }
    
    private void cargaListaTrazabilidad(String fecIni, String fecFin) {
        try {
            /*DBInventario.cargaListaPedidosEspeciales(tableModel, fecIni, fecFin);
            FarmaUtility.ordenar(tblListaPedidos, tableModel, 0, FarmaConstants.ORDEN_DESCENDENTE);*/
            UtilityAdmisionMedica.obtenerTrazabilidad(modelTblPacientes, txtFecIni.getText(), txtFecFin.getText());
            //FarmaUtility.ordenar(tblPacientes, modelTblPacientes, 0, FarmaConstants.ORDEN_DESCENDENTE);
            FarmaUtility.moveFocus(txtFecIni);
        } catch (Exception/*SQLException*/ sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al cargar la lista de pedidos : \n " + sql.getMessage(),
                                     btnBuscar);
        }
    }
    
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    private void tblPacientes_mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            funcionF11();
        }
    }
    
    private void lblF11_mouseClicked(MouseEvent e) {
        funcionF11();
    }
    
    private void lblF5_mouseClicked(MouseEvent e) {
        funcionF5();
    }
    
    private void eventosConMouse(MouseEvent event){
        
    }
    
    
    private void funcionF5(){
        int selecRow = getNumFilaSeleccionadaTabla();
        if(selecRow != -1){
            String codEstado = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 9);
            if("A".equalsIgnoreCase(codEstado)){
                if(JConfirmDialog.rptaConfirmDialog(this,"¿Esta seguro de reimprimir la receta?")){
                    FarmaVariables.vAceptar = false;
                    DlgLoginMedico dlgLogin = new DlgLoginMedico(myParentFrame, "", true);
                    dlgLogin.setMostrarMensaje(false);
                    dlgLogin.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        String codMedico = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 19);
                        if(codMedico.equalsIgnoreCase(dlgLogin.getCodMedico())){
                            String nroAtencion = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 17);
                            (new UtilityAtencionMedica()).imprimirRecetaMedica(this, nroAtencion);
                        }else{
                            FarmaUtility.showMessage(this,"No se puede reimprimir, debido a que usted no fue quien realizó la consulta medica", txtFecIni);
                        }
                    }
                }
            }else{
                FarmaUtility.showMessage(this, "Consulta médica aun no ha sido atendida.", txtFecIni);
            }
        }/*else{
            FarmaUtility.showMessage(this, "Debe seleccionar un registro.", txtFecIni);
        }*/
    }
    
    private void funcionF11(){
        int selecRow = getNumFilaSeleccionadaTabla();
        if(selecRow != -1){
            String codEstado = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 9);
            if("A".equalsIgnoreCase(codEstado)){
                String codGrupoCia = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 14);
                String codCia = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 15);
                String codLocal = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 16);
                String nroAtencion = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 17);
                String codPaciente = FarmaUtility.getValueFieldArrayList(modelTblPacientes.data, selecRow, 18);
                boolean isImpresion = false;
                (new UtilityAtencionMedica()).verAtencionMedica(myParentFrame, this, codGrupoCia, codCia, codLocal, nroAtencion, codPaciente, isImpresion);
            }else{
                FarmaUtility.showMessage(this, "Solo se muestra de las consultas médicas que ya fueron atendidas.", txtFecIni);
            }
        }/*else{
            FarmaUtility.showMessage(this, "Debe seleccionar un registro.", txtFecIni);
        }*/
    }
}
