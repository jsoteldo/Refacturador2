package mifarma.ptoventa.fraccion;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.fraccion.modelo.VariableProducto;
import mifarma.ptoventa.fraccion.reference.ConstantsDatos;
import mifarma.ptoventa.fraccion.reference.UtilityFraccion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgSolicitudFraccion extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgSolicitudFraccion.class);
    private String fechaIni;
    private String fechaFin;
    private String codEstado;

    private Frame myParentFrame;
    private FarmaTableModel tblModelProductos;
    private FarmaTableModel tblModelSolicitud;
    private JTable tblProductos = new JTable();
    private JTable tblSolicitud = new JTable();

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlFechas = new JPanel();
    private JPanel pnlListaSolicitud = new JPanel();
    private JPanel pnlListaProductos = new JPanel();
    private JScrollPane spnSolicitud = new JScrollPane();
    private JScrollPane scpProducto = new JScrollPane();
    private JLabel lblTituloFechas = new JLabel();
    private JLabel lblDe = new JLabel();
    private JLabel lblA = new JLabel();
    private JTextFieldSanSerif txtFechaInicio = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtFechaFin = new JTextFieldSanSerif();
    private JLabel lblEstado = new JLabel();
    private JComboBox cmbEstado = new JComboBox();
    private JLabel lblListaSolicitud = new JLabel();
    private JLabel lblListaProducto = new JLabel();
    private JLabelFunction lblNuevo_F1 = new JLabelFunction();
    private JLabelFunction lblAnular_Del = new JLabelFunction();
    private JLabelFunction lblSalir_Esc = new JLabelFunction();
    private JLabel lblSolicitudNro = new JLabel();
    private JLabel lblSolicitudCant = new JLabel();
    private JButton btnBuscar = new JButton();


    public DlgSolicitudFraccion() {
        super();
    }

    public DlgSolicitudFraccion(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(801, 477));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Lista de solicitud de fraccionamiento");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.white);
        this.setBackground(Color.white);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });

        ctpContenido.setBackground(Color.white);
        ctpContenido.setLayout(null);
        ctpContenido.setSize(new Dimension(623, 439));
        ctpContenido.setForeground(Color.white);

        pnlFechas.setBounds(new Rectangle(15, 5, 770, 75));
        pnlFechas.setBackground(new Color(43, 141, 39));
        pnlFechas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlFechas.setLayout(null);

        scpProducto.setBounds(new Rectangle(15, 285, 770, 110));
        scpProducto.setBackground(new Color(255, 130, 14));

        lblTituloFechas.setText("Rango de fechas");
        lblTituloFechas.setBounds(new Rectangle(5, 5, 130, 15));
        lblTituloFechas.setBackground(SystemColor.window);
        lblTituloFechas.setForeground(SystemColor.window);
        lblTituloFechas.setFont(new Font("SansSerif", 1, 14));
        lblDe.setText("De:");
        lblDe.setBounds(new Rectangle(20, 35, 34, 15));
        lblDe.setForeground(SystemColor.window);
        lblDe.setFont(new Font("SansSerif", 1, 12));
        lblA.setText("A:");
        lblA.setBounds(new Rectangle(180, 35, 34, 15));
        lblA.setForeground(SystemColor.window);
        lblA.setFont(new Font("SansSerif", 1, 12));
        lblEstado.setText("Estado: ");
        lblEstado.setBounds(new Rectangle(380, 35, 55, 15));
        lblEstado.setFont(new Font("SansSerif", 1, 12));
        lblEstado.setForeground(SystemColor.window);
        cmbEstado.setBounds(new Rectangle(435, 33, 135, 20));
        cmbEstado.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                evento_keyPressed(e);
            }
        });
        lblListaSolicitud.setText("Lista de solicitudes");
        lblListaSolicitud.setBounds(new Rectangle(5, 2, 145, 15));
        lblListaSolicitud.setFont(new Font("SansSerif", 1, 12));
        lblListaSolicitud.setForeground(SystemColor.window);
        lblListaProducto.setText("Lista de productos");
        lblListaProducto.setBounds(new Rectangle(5, 2, 160, 15));
        lblListaProducto.setFont(new Font("SansSerif", 1, 12));
        lblListaProducto.setForeground(SystemColor.window);
        lblNuevo_F1.setText("[F1] Nuevo");
        lblNuevo_F1.setBounds(new Rectangle(20, 405, 130, 25));
        lblAnular_Del.setText("[F2] Anular");
        lblAnular_Del.setBounds(new Rectangle(160, 405, 130, 25));
        lblSalir_Esc.setText("[ESC] Cerrar");
        lblSalir_Esc.setBounds(new Rectangle(620, 405, 130, 25));
        lblSolicitudNro.setBounds(new Rectangle(370, 2, 395, 15));
        lblSolicitudNro.setText("");
        lblSolicitudNro.setFont(new Font("SansSerif", 1, 12));
        lblSolicitudNro.setForeground(SystemColor.window);
        lblSolicitudNro.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolicitudNro.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblSolicitudCant.setBounds(new Rectangle(385, 2, 380, 15));
        lblSolicitudCant.setText("");
        lblSolicitudCant.setFont(new Font("SansSerif", 1, 12));
        lblSolicitudCant.setForeground(SystemColor.window);
        lblSolicitudCant.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolicitudCant.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(590, 33, 120, 20));
        btnBuscar.setMnemonic('B');
        btnBuscar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnBuscar_ActionPerformed(e);
                }
            });
        txtFechaInicio.setBounds(new Rectangle(45, 33, 125, 20));
        txtFechaInicio.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaIni_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaIni_keyReleased(e);
            }
        });
        txtFechaInicio.setDocument(new FarmaLengthText(10));
        txtFechaFin.setBounds(new Rectangle(205, 33, 125, 20));
        txtFechaFin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaFin_keyPressed(e);

            }

            public void keyReleased(KeyEvent e) {
                txtFechaFin_keyReleased(e);

            }
        });
        txtFechaFin.setDocument(new FarmaLengthText(10));
        pnlListaSolicitud.setBounds(new Rectangle(15, 80, 770, 20));
        pnlListaSolicitud.setBackground(new Color(255, 130, 14));
        pnlListaSolicitud.setLayout(null);

        spnSolicitud.setBounds(new Rectangle(15, 100, 770, 165));
        spnSolicitud.setBackground(new Color(255, 130, 14));

        pnlListaProductos.setBounds(new Rectangle(15, 265, 770, 20));
        pnlListaProductos.setBackground(new Color(255, 130, 14));
        pnlListaProductos.setLayout(null);

        tblProductos.setFont(new Font("SansSerif", 0, 12));
        tblProductos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblProductos_mouseClicked(e);
            }   
        });
        tblProductos.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblProductos_keyPressed(e);
                }
            });
        tblSolicitud.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblSolicitud_mouseClicked(e);
            }
        });
        tblSolicitud.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblSolicitud_KeyPressed(e);
                }
            });
        scpProducto.getViewport();
        spnSolicitud.getViewport();

        pnlFechas.add(btnBuscar, null);
        pnlFechas.add(txtFechaFin, null);
        pnlFechas.add(txtFechaInicio, null);
        pnlFechas.add(cmbEstado, null);
        pnlFechas.add(lblEstado, null);
        pnlFechas.add(lblA, null);
        pnlFechas.add(lblDe, null);
        pnlFechas.add(lblTituloFechas, null);
        ctpContenido.add(lblSalir_Esc, null);
        ctpContenido.add(lblAnular_Del, null);
        ctpContenido.add(lblNuevo_F1, null);
        ctpContenido.add(pnlFechas, null);
        scpProducto.getViewport().add(tblProductos, null);
        ctpContenido.add(scpProducto, null);
        pnlListaSolicitud.add(lblSolicitudCant, null);
        pnlListaSolicitud.add(lblListaSolicitud, null);
        ctpContenido.add(pnlListaSolicitud, null);
        spnSolicitud.getViewport().add(tblSolicitud, null);
        ctpContenido.add(spnSolicitud, null);
        pnlListaProductos.add(lblSolicitudNro, null);
        pnlListaProductos.add(lblListaProducto, null);
        ctpContenido.add(pnlListaProductos, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);

    }

    private void initialize() {
        initListaSolicitud();
        initListaProducto();
        cargar_cmbEstado();
    }

    void initListaSolicitud() {
        tblModelSolicitud =
                new FarmaTableModel(ConstantsDatos.columnsListaSolicitud, ConstantsDatos.defaultValuesListaSolicitud,
                                    0);
        FarmaUtility.initSimpleList(tblSolicitud, tblModelSolicitud, ConstantsDatos.columnsListaSolicitud);
    }

    void initListaProducto() {
        tblModelProductos =
                new FarmaTableModel(ConstantsDatos.columnsProducto_Solicitud, ConstantsDatos.defaultValuesProducto_Solicitud,
                                    0);
        FarmaUtility.initSimpleList(tblProductos, tblModelProductos, ConstantsDatos.columnsProducto_Solicitud);
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtFechaInicio);
        try {
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            String fechaAtrasada =
                FarmaSearch.getFechaHoraAtrasadaBD(FarmaConstants.FORMATO_FECHA, 7); //Change by Cesar Huanes
            txtFechaInicio.setText(fechaAtrasada);
            txtFechaFin.setText(fechaActual); //Change by Cesar Huanes
            muestraDatosIniciales();
            //recuperaDatos_Consulta();
        } catch (SQLException sql) {
            muestraMensaje("No existe solicitudes de fraccionamiento en la fecha indicada!!!!!.",
                            sql.getMessage());
        }
    }

    private void this_windowClosing(WindowEvent e) {
        muestraMensaje("Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void evento_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            cerrarVentana();
            break;
        case KeyEvent.VK_B:
            btnBuscar.doClick();
            break;
        case KeyEvent.VK_F1:
            nuevaSolicitud();
            muestraDatosIniciales();
            break;
        case KeyEvent.VK_ENTER:
            e.consume();
            btnBuscar.doClick();
            break;
        case KeyEvent.VK_F5:
            e.consume();
            if (validarCampos()) {
                fechaIni = txtFechaInicio.getText().trim();
                fechaFin = txtFechaFin.getText().trim();
                codEstado = "%";
                recuperaDatos_Consulta();
            }
            
            break;
        //case KeyEvent.VK_DELETE:
        case KeyEvent.VK_F2:
            anularSolicitud_Fraccion();
            break;
        /*
        case KeyEvent.VK_F11:
            enviarDatos_APPS();
            break;/**/
        case KeyEvent.VK_F12:
            //ejecutaViajero();
            //procesarSolicitud_Fraccion();
            
            if(FarmaVariables.vIpPc.equalsIgnoreCase("10.18.0.139")){
                DlgCupon dCupon = new DlgCupon(myParentFrame, "", true);
                dCupon.setVisible(true);}
            /**/
            break;
        }
    }

    private void cerrarVentana() {
        VariableProducto.listaMemoriaProd=new ArrayList();
        this.setVisible(false);
        this.dispose();
    }

    private synchronized void nuevaSolicitud() {
        cargarProductos_Memoria();
        VariableProducto.listaProductos = new ArrayList();
        VariableProducto.listaSeleccion = new ArrayList();
        DlgNuevaFraccion dlgNuevo = new DlgNuevaFraccion(myParentFrame, "", true);
        dlgNuevo.setVisible(true);
    }
    
    public synchronized void cargarProductos_Memoria() {
        if(VariableProducto.listaMemoriaProd.isEmpty()){
            DlgProcesoEspere dlgProcesar = new DlgProcesoEspere(myParentFrame, "", true);
            dlgProcesar.mostrar();                      
        }
    }
    
    private void cargar_cmbEstado() {
        ArrayList<ArrayList> selection = new ArrayList();
        UtilityFraccion.recuperaOptionTipo(selection, "1");
        FarmaLoadCVL.loadCVLFromArrayList(cmbEstado, "CMB_ESTADO_SOLIC", selection, true);
    }

    private void txtFechaIni_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtFechaFin);
        } else
            chkKeyPressed(e);
    }

    private void txtFechaIni_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaInicio, e);
    }

    private void txtFechaFin_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbEstado);
            //btnBuscar_actionPerformed();//btnBuscar.doClick();
        } else
            chkKeyPressed(e);
    }

    private void txtFechaFin_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaFin, e);
    }

    private void buscar_actionPerformed() { 
        if (validarCampos()) {
            fechaIni = txtFechaInicio.getText().trim();
            fechaFin = txtFechaFin.getText().trim();
            codEstado = FarmaLoadCVL.getCVLCode("CMB_ESTADO_SOLIC", cmbEstado.getSelectedIndex());
            recuperaDatos_Consulta();
        }
    }

    private boolean validarCampos() {
        boolean retorno = true;
        if (txtFechaInicio.getText().trim().equals("")) {
            retorno = false;
             muestraMensaje("Ingrese Fecha de Inicio.", txtFechaInicio);
        } else if (txtFechaFin.getText().trim().equals("")) {
            retorno = false;
             muestraMensaje("Ingrese Fecha de Fin. ", txtFechaFin);
        } else if (!FarmaUtility.validaFecha(txtFechaInicio.getText(), "dd/MM/yyyy")) {
            retorno = false;
             muestraMensaje("Formato Incorrecto de Fecha.", txtFechaInicio);
        } else if (!FarmaUtility.validaFecha(txtFechaFin.getText(), "dd/MM/yyyy")) {
            retorno = false;
             muestraMensaje("Formato Incorrecto de Fecha.", txtFechaFin);
        } else if (!FarmaUtility.validarRangoFechas(this, txtFechaInicio, txtFechaFin, false, true, true, true))
            retorno = false;
        return retorno;
    }

    private void recuperaDatos_Consulta() {
        tblModelSolicitud.clearTable();
        tblModelProductos.clearTable();
        int existenDatos = UtilityFraccion.existenDatos(fechaIni, fechaFin, codEstado);
        if (existenDatos > 0) {
            UtilityFraccion.cargaTabla_SolicitudFrac(tblModelSolicitud, fechaIni, fechaFin, codEstado);
            //UtilityFraccion.cargaLista_SolicitudFrac(VariableSolicitud.listaSolicitud,fechaIni,fechaFin,codEstado);
            //this.repaint();
            FarmaUtility.moveFocusJTable(tblSolicitud);
            lblSolicitudCant.setText(tblModelSolicitud.getRowCount()+" Solicitudes encontradas");
        } else {
            muestraMensaje("No existe información de solicitudes de fraccionamiento en la fecha indicada.",
                            null);
            lblSolicitudCant.setText("");
        }
        
        lblSolicitudNro.setText("");
    }

    private void tblSolicitud_mouseClicked(MouseEvent e) {
        cargarListaProductos_Solicitud(0);
        //FarmaUtility.moveFocusJTable(tblProductos);
        /* if (e.isMetaDown() ){//boton derecho del mouese
             vEjecutaAccionTeclaListado = false;
         }*/
    }
    
    private void tblProductos_mouseClicked(MouseEvent e) {
        FarmaUtility.moveFocusJTable(tblSolicitud);
    }
    
    private void anularSolicitud_Fraccion() {
        String msj="Confirma que desea anular la solicitud ";
        boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
        if(ok){
            boolean regDia=true;
            int i = tblSolicitud.getSelectedRow();
            String nroSolic = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, 0);
            String codLocalCrea = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, 6);
            String fecSolicita=FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, 1);
            int diaSol=Integer.parseInt(fecSolicita.substring(0,fecSolicita.indexOf("/")));
            int mesSol=Integer.parseInt(fecSolicita.substring(fecSolicita.indexOf("/")+1,fecSolicita.lastIndexOf("/")));
            int anioSol=Integer.parseInt(fecSolicita.substring(fecSolicita.lastIndexOf("/")+1));
            int dia=Calendar.getInstance().getTime().getDate();
            int mes=Calendar.getInstance().getTime().getMonth()+1;
            int anio=Calendar.getInstance().getTime().getYear()+1900;
            if(!(anio==anioSol && mes==mesSol && dia==diaSol)){
                regDia=false;                
            }
            if(regDia){
                if(codLocalCrea.equalsIgnoreCase(FarmaVariables.vCodLocal)){
                    int procesar=UtilityFraccion.recuperaSolicitud_Frac(nroSolic,codLocalCrea);
                    switch(procesar){
                    case 0:
                        muestraMensaje("Se produjo un error al acceder a la base de datos", null);
                        break;
                    case 1:
                        int nro=UtilityFraccion.actualiza_AnulacionSolicitud(nroSolic);
                        if ( nro==1) {
                            muestraMensaje("La solicitud y sus registros asociados se anulados correctamente", tblSolicitud);
                            recuperaDatos_Consulta();
                            FarmaUtility.moveFocusJTable(tblSolicitud);
                        } else if ( nro==2) {
                            muestraMensaje("No se puede eliminar la solicitud\nExisten productos procesados", tblSolicitud);
                        }else{
                            muestraMensaje("Se produjo un error y no se anulo la solicitud ni los registros asociados a el", tblSolicitud);
                        }
                        
                        txtFechaInicio.setText(fechaIni);
                        lblSolicitudNro.setText("");
                        break;
                    case 2:muestraMensaje("La solicitud ya fue procesada", null);
                        break;
                    case 3:muestraMensaje("La solicitud ya fue anulada", null);
                        break;
                    case 4:muestraMensaje("La solicitud se encuentra observada por el sistema\nConsulte con el administrador", null);
                        break;
                    case 5:muestraMensaje("La solicitud ya fue enviado a central\nComuniquese con el administrador para ser atendido", null);
                        break;
                    case 6:muestraMensaje("Se produjo un error y no se encontro el registro con los datos enviados", null);
                        break;
                    }       
                }else{
                    muestraMensaje("La solicitud no puede ser procesada por el local ya que fue generada por administracion", null);
                }
            }else{
                muestraMensaje("Este registro no puede ser anulado\nConsulte a ADM para su atencion y/o anulacion", null);
            }
        }
    }

    private void muestraDatosIniciales() {
        fechaIni = txtFechaInicio.getText();
        fechaFin = txtFechaFin.getText();
        codEstado = FarmaLoadCVL.getCVLCode("CMB_ESTADO_SOLIC", cmbEstado.getSelectedIndex());
        tblModelSolicitud.clearTable();
        tblModelProductos.clearTable();
        int existenDatos = UtilityFraccion.existenDatos(fechaIni, fechaFin, codEstado);
        if (existenDatos > 0) {
            UtilityFraccion.cargaTabla_SolicitudFrac(tblModelSolicitud, fechaIni, fechaFin, codEstado);
            FarmaUtility.moveFocusJTable(tblSolicitud);
        }
        lblSolicitudCant.setText(tblModelSolicitud.getRowCount()+" Solicitudes encontradas");
        lblSolicitudNro.setText("");
    }

    private void tblSolicitud_KeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
            e.consume();
            cargarListaProductos_Solicitud(0);
            //FarmaUtility.moveFocusJTable(tblProductos);
            break;
        case KeyEvent.VK_UP:
            cargarListaProductos_Solicitud(-1);
            break;
        case KeyEvent.VK_DOWN:
            cargarListaProductos_Solicitud(1);
            break;
        case KeyEvent.VK_TAB:
            FarmaUtility.moveFocus(txtFechaInicio);
            break;
        default:
            chkKeyPressed(e);
        }
    }

    private void cargarListaProductos_Solicitud(int position) {
        int i = tblSolicitud.getSelectedRow();
        i=i+position;
        if(i<0){i=0;}
        if(i==tblSolicitud.getRowCount()){i--;}
        String nroSolic = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, 0);
        String codLocalCrea = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, 6);
        tblModelProductos.clearTable();
        UtilityFraccion.cargaTabla_ProductosFrac(tblModelProductos, nroSolic,codLocalCrea);
        //UtilityFraccion.cargaLista_ProductosFrac(nroSolic);
        lblSolicitudNro.setText("Solicitud Nro. "+Integer.parseInt(nroSolic)+": Cant. de productos. "+tblModelProductos.getRowCount());
    }

    private void tblProductos_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_TAB){
            FarmaUtility.moveFocusJTable(tblSolicitud);
        }
    }

    private void procesarSolicitud_Fraccion() {
        //muestraDatosIniciales();
        String msjConfirma="Se procesaran todas las solicitudes";
        boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msjConfirma, "Si");
        if(ok){
            String msj=UtilityFraccion.atenderSolicitudes_Pendientes();
            if(msj==null){
                muestraMensaje("Se aprobaron todas las solicitudes correctamente", null);
            }else{
                muestraMensaje(msj, null);
            }
            tblModelProductos.clearTable();
            tblModelSolicitud.clearTable();
        }
    }
    
    private void muestraMensaje(String msj, Object cTxt) {
        FarmaUtility.showMessage(this, msj, cTxt);
    }

    private void enviarDatos_APPS() {
        String msjConfirma="Enviar datos a matriz";
        boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msjConfirma, "Si");
        if(ok){
            UtilityFraccion.enviarDatos_APPS();
        }
    }

    private void ejecutaViajero() {
        UtilityFraccion.ejecutaViajero();
    }

    private void btnBuscar_ActionPerformed(ActionEvent e) {
        buscar_actionPerformed();
    }
}
