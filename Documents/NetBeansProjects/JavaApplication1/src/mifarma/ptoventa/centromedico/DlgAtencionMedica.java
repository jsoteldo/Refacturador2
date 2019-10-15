package mifarma.ptoventa.centromedico;

import com.gs.mifarma.componentes.DocumentFilterTextArea;
import com.gs.mifarma.componentes.FarmaHora;
import com.gs.mifarma.componentes.JButtonFunction;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JComboWithCheck;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JNumericField;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import com.gs.mifarma.componentes.OptionComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.print.PrinterJob;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.Collections;
import java.util.Date;

import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.print.DocPrintJob;
import javax.print.PrintService;

import javax.print.PrintServiceLookup;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import javax.swing.border.TitledBorder;

import javax.swing.text.JTextComponent;

import javax.swing.text.PlainDocument;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConnection;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableComparator;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedDiagnostico;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedEnfermedadActual;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedExamenFisico;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedExamenesAuxiliares;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedOtros;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTrataReceta;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTrataRecetaDetalle;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTratamiento;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTriaje;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentes;
import mifarma.ptoventa.centromedico.reference.FacadeAtencioMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import mifarma.ptoventa.ventas.DlgListaProductos;

import mifarma.ptoventa.ventas.reference.ConstantsVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgAtencionMedica extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgListaEspera.class);
    private Frame myParentFrame;
    private BorderLayout borderLayout = new BorderLayout();
    
    private JPanelWhite pnlContenedor = new JPanelWhite();
    
    private JTabbedPane tabpContenedor = new JTabbedPane();
    
    private JPanel pnlEnfermedadActual = new JPanel();
    private JButtonLabel lblMotivoConsulta = new JButtonLabel();
    private JTextFieldSanSerif txtMotivoConsulta = new JTextFieldSanSerif();
    private JButtonLabel lblTiempoEnfermedad = new JButtonLabel();
    private JTextFieldSanSerif txtTiempoEnfermedad = new JTextFieldSanSerif();
    private JButtonLabel lblTipoInformante = new JButtonLabel();
    //private JComboBox cmbTipoInformante = new JComboBox();
    private JComboWithCheck cmbTipoInformante = new JComboWithCheck();
    private JButtonLabel lblFormaInicio = new JButtonLabel();
    private JTextFieldSanSerif txtFormaInicio = new JTextFieldSanSerif();
    private JButtonLabel lblSignos = new JButtonLabel();
    //private JTextFieldSanSerif txtSignos = new JTextFieldSanSerif();
    private JTextArea txtSignos = new JTextArea();
    private JButtonLabel lblRelatoCronologico = new JButtonLabel();
    private JScrollPane scrRelatoCronologico = new JScrollPane();
    private JTextArea txtRelatoCronologico = new JTextArea();
    private JButtonLabel lblSintomas = new JButtonLabel();
    private JTextFieldSanSerif txtSintomas = new JTextFieldSanSerif();
    private JPanel pnlFuncionesBiologicas = new JPanel();
    private JButtonLabel lblApetito = new JButtonLabel();
    //private JComboBox cmbApetito = new JComboBox();
    private JComboWithCheck cmbApetito = new JComboWithCheck();
    private JButtonLabel lblSed = new JButtonLabel();
    //private JComboBox cmbSed = new JComboBox();
    private JComboWithCheck cmbSed = new JComboWithCheck();
    private JButtonLabel lblSuenio = new JButtonLabel();
    //private JComboBox cmbSuenio = new JComboBox();
    private JComboWithCheck cmbSuenio = new JComboWithCheck();
    private JButtonLabel lblOrina = new JButtonLabel();
    //private JComboBox cmbOrina = new JComboBox();
    private JComboWithCheck cmbOrina = new JComboWithCheck();
    private JButtonLabel lblDeposiciones = new JButtonLabel();
    //private JComboBox cmbDeposiciones = new JComboBox();
    private JComboWithCheck cmbDeposiciones = new JComboWithCheck();
    
    private JPanel pnlExamenFisico = new JPanel();
    private JPanel pnlFuncionesVitales = new JPanel();
    private JButtonLabel lblPA = new JButtonLabel();
    private JNumericField txtPA1 = new JNumericField(3, JNumericField.NUMERIC);
    private JLabel lblPA1 = new JLabel();
    private JNumericField txtPA2 = new JNumericField(3, JNumericField.NUMERIC);
    private JLabel lblPA2 = new JLabel();
    private JButtonLabel lblFR = new JButtonLabel();
    private JNumericField txtFR = new JNumericField(3, JNumericField.NUMERIC);
    private JLabel lblFR1 = new JLabel();
    private JButtonLabel lblFC = new JButtonLabel();
    private JNumericField txtFC = new JNumericField(3, JNumericField.NUMERIC);
    private JLabel lblFC1 = new JLabel();
    private JButtonLabel lblT = new JButtonLabel();
    private JNumericField txtT = new JNumericField(3, JNumericField.DECIMAL);
    private JLabel lblT1 = new JLabel();
    private JButtonLabel lblPeso = new JButtonLabel();
    private JNumericField txtPeso = new JNumericField(3, JNumericField.DECIMAL);
    private JLabel lblPeso1 = new JLabel();
    private JButtonLabel lblTalla = new JButtonLabel();
    private JNumericField txtTalla = new JNumericField(3, JNumericField.DECIMAL);
    private JLabel lblTalla1 = new JLabel();
    private JPanel pnlExamenFisicoInter = new JPanel();
    private JButtonLabel lblEstadoGeneral = new JButtonLabel();
    //private JComboBox cmbEstadoGeneral = new JComboBox();
    private JComboWithCheck cmbEstadoGeneral = new JComboWithCheck();
    private JButtonLabel lblEstadoConciencia = new JButtonLabel();
    private JTextFieldSanSerif txtEstadoConciencia = new JTextFieldSanSerif();
    private JButtonLabel lblExamenFisicoDirigido = new JButtonLabel();
    private JScrollPane scrFisicoDirigido = new JScrollPane();
    private JTextArea txtFisicoDirigido = new JTextArea();
    
    private JPanel pnlDiagnostico = new JPanel();
    private JButtonLabel lblDiagnostico = new JButtonLabel();
    private JTextFieldSanSerif txtCodDiagnostico = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtDescDiagnostico = new JTextFieldSanSerif();
    //private JComboBox cmbTipoDiagnostico = new JComboBox();
    private JComboWithCheck cmbTipoDiagnostico = new JComboWithCheck();
    private JPanelTitle pnlTitleListaDiagnostico = new JPanelTitle();
    private JButtonLabel lblTituloListaDiagnostico = new JButtonLabel();
    private JScrollPane scrListaDiagnostico = new JScrollPane();
    private JTable tblListaDiagnostico = new JTable();
    private FarmaTableModel mdlTblDiagnostico;
    
    private JPanel pnlTratamiento = new JPanel();
    private JButtonLabel lblTratamientoProducto = new JButtonLabel();
    private JTextFieldSanSerif txtProductoTratamiento = new JTextFieldSanSerif();
    private JButtonLabel lblFrecuenciaTratamiento = new JButtonLabel();
    private JNumericField txtFrecuenciaTratamiento = new JNumericField(2, JNumericField.NUMERIC);
    private JButtonLabel lblDuracionTratamiento = new JButtonLabel();
    private JNumericField txtDuracionTratamiento = new JNumericField(2, JNumericField.NUMERIC);
    private JButtonLabel lblViaAdministracion = new JButtonLabel();
    //private JComboBox cmbViaAdministracion = new JComboBox();
    private JComboWithCheck cmbViaAdministracion = new JComboWithCheck();
    private JPanelTitle pnlTitleListaReceta = new JPanelTitle();
    private JButtonLabel lblTitleListaReceta = new JButtonLabel();
    private JScrollPane scrListaReceta = new JScrollPane();
    private JTable tblListaReceta = new JTable();
    private FarmaTableModel mdlTblReceta;
    private JButtonLabel lblValidezReceta = new JButtonLabel();
    private JTextFieldSanSerif txtValidezReceta = new JTextFieldSanSerif();
    private JButtonLabel lblIndicacionesGeneralesTratamiento = new JButtonLabel();
    private JScrollPane scrIndicacionesGeneralesTratamiento = new JScrollPane();
    private JTextArea txtIndicacionesGeneralesTratamiento = new JTextArea();
    
    private JPanel pnlExamenesAuxiliares = new JPanel();
    private JButtonLabel lblExaAuxLaborotarios = new JButtonLabel();
    private JScrollPane scrExaAuxLaborotarios = new JScrollPane();
    private JTextArea txtExaAuxLaborotarios = new JTextArea();
    private JButtonLabel lblExaAuxImagenologicos = new JButtonLabel();
    private JScrollPane scrExaAuxImagenologicos = new JScrollPane();
    private JTextArea txtExaAuxImagenologicos = new JTextArea();


    private JPanel pnlProcedimiento = new JPanel();
    private JButtonLabel lblProcedimiento = new JButtonLabel();
    private JScrollPane scrProcedimiento = new JScrollPane();
    private JTextArea txtProcedimiento = new JTextArea();
    private JButtonLabel lblInterconsulta = new JButtonLabel();
    private JScrollPane scrInterconsulta = new JScrollPane();
    private JTextArea txtInterconsulta = new JTextArea();
    private JButtonLabel lblTransferencia = new JButtonLabel();
    private JScrollPane scrTransferencia = new JScrollPane();
    private JTextArea txtTransferencia = new JTextArea();
    private JButtonLabel lblDescansoMedico = new JButtonLabel();
    private JTextFieldSanSerif txtDescansoMedicoInicio = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtDescansoMedicoFin = new JTextFieldSanSerif();
    private JButtonLabel lblProximaCita = new JButtonLabel();
    private JTextFieldSanSerif txtProximaCita = new JTextFieldSanSerif();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction lblF6 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JButtonLabel lblCantidad = new JButtonLabel();
    private JLabelOrange lblSugerido = new JLabelOrange();
    private JNumericField txtCantidadTratamiento = new JNumericField(5, JNumericField.NUMERIC);
    private JLabel lblUnidadRecetada = new JLabel();
    private JButtonLabel lblDosis = new JButtonLabel();
    private JTextFieldSanSerif txtDosis = new JTextFieldSanSerif();
    private JLabelOrange lblTipoDiagnostico = new JLabelOrange();
    private JLabelFunction lblF4 = new JLabelFunction();
    
    private transient BeanAtencionMedica beanAtencionMedica;
    private transient BeanPaciente beanPaciente;
    private String codDiagnosticoAux  = "";
    private String codProdReceta = "";
    private int valMaxFracProdReceta = 0;
    private int valFracProdReceta = 0;
    private ArrayList<ArrayList<String>> lstProductosReceta = new ArrayList<ArrayList<String>>();
    
    private boolean imprimioReceta = false;
    private boolean graboReceta = false;
    
    private boolean modoVisual = false;
    private JNumericField txtCantDiasValidezReceta = new JNumericField(2, JNumericField.NUMERIC);
    private JLabelOrange lblFechaValidezReceta = new JLabelOrange();
    
    private transient UtilityAtencionMedica utility = new UtilityAtencionMedica();
    private JButtonFunction btnAgregarDiagnostico = new JButtonFunction();
    private JButtonFunction btnQuitarDiagnostico = new JButtonFunction();
    private JButtonFunction btnAgregarReceta = new JButtonFunction();
    private JButtonFunction btnQuitarReceta = new JButtonFunction();
    
    private FarmaTableModel tblMdlListaReceta;
    private JLabel lblMensajeDiagnostico = new JLabel();
    private ArrayList<ArrayList<String>> lstDiagnostico;
    private int contadorEnter = 0;
    
    private JScrollPane scrPnlTxtSignos = new JScrollPane();
    private boolean atencionNueva;
    private JPanel pnlBotonesFuncion = new JPanel();
    private boolean bandImpresion = false;
    private String atencion = "";

    public DlgAtencionMedica() {
        this(null, "", false);
    }
        
    public DlgAtencionMedica(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.myParentFrame = parent;
        try {
            jbInit();
            inicialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(760, 505));
        this.getContentPane().setLayout(borderLayout);
        this.setTitle("Consulta Médica");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        /************************************************************************/
        /************************ ENFERDAD ACTUAL *******************************/
        /************************************************************************/
        
        lblMotivoConsulta.setText("Motivo Consulta");
        lblMotivoConsulta.setBounds(new Rectangle(15, 10, 95, 20));
        lblMotivoConsulta.setForeground(new Color(255, 130, 14));
        lblMotivoConsulta.setToolTipText("se ingresa la causa del problema en la versi\u00f3n que proporciona el paciente");
        
        txtMotivoConsulta.setBounds(new Rectangle(115, 10, 560, 20));
        txtMotivoConsulta.setToolTipText("se ingresa la causa del problema en la versi\u00f3n que proporciona el paciente");
        txtMotivoConsulta.setLengthText(500);
        //txtMotivoConsulta.addActionListener(new ActionTransferFocus(txtFormaInicio));
        lblSignos.setText("Signos y Sintomas");
        lblSignos.setBounds(new Rectangle(15, 40, 110, 20));
        lblSignos.setForeground(new Color(255, 130, 14));
        lblSignos.setToolTipText("se ingresa signos y s\u00edntomas t\u00edpicos de la enfermedad");
        
        txtSignos.setToolTipText("se ingresa signos y s\u00edntomas t\u00edpicos de la enfermedad");
        txtSignos.setFont(new Font("SansSerif", 0, 11));
        txtSignos.setLineWrap(true);
        txtSignos.setWrapStyleWord(true);
        txtSignos.setTabSize(2);
        ((PlainDocument) txtSignos.getDocument()).setDocumentFilter(new DocumentFilterTextArea(500));
        //txtSignos.setBounds(new Rectangle(130, 70, 545, 20));
        //txtSignos.addActionListener(new ActionTransferFocus(txtFormaInicio));
        //.setBounds(new Rectangle(115, 40, 560, 20));
        //130, 70
        scrPnlTxtSignos.setBounds(new Rectangle(130, 40, 545, 50));

        scrPnlTxtSignos.getViewport().add(txtSignos, null);
        
        lblFormaInicio.setText("Forma de Inicio");
        lblFormaInicio.setBounds(new Rectangle(15, 100, 95, 20));
        lblFormaInicio.setForeground(new Color(255, 130, 14));
        
        txtFormaInicio.setBounds(new Rectangle(115, 100, 560, 20));
        txtFormaInicio.setLengthText(500);
        
        lblTiempoEnfermedad.setText("Tiempo Enfermedad:");
        lblTiempoEnfermedad.setBounds(new Rectangle(15, 130, 120, 20));
        lblTiempoEnfermedad.setForeground(new Color(255, 130, 14));
        lblTiempoEnfermedad.setToolTipText("Se ingresa el tiempo de evoluci\u00f3n de la enfermedad (d\u00edas)");
        
        txtTiempoEnfermedad.setBounds(new Rectangle(135, 130, 185, 20));
        txtTiempoEnfermedad.setToolTipText("Se ingresa el tiempo de evoluci\u00f3n de la enfermedad");
        //txtTiempoEnfermedad.set(false);
        
        lblTipoInformante.setText("Tipo Informante");
        lblTipoInformante.setBounds(new Rectangle(335, 130, 90, 20));
        lblTipoInformante.setForeground(new Color(255, 130, 14));
        lblTipoInformante.setToolTipText("Directa: s\u00f3lo el paciente explica sus dolencias y s\u00edntomas, Indirecta: si el acompa\u00f1ante indica las dolencias y s\u00edntomas, Mixta: si el acompa\u00f1ante y el paciente indican las dolencias y s\u00edntomas");
        
        cmbTipoInformante.setBounds(new Rectangle(435, 130, 230, 20));
        cmbTipoInformante.setToolTipText("Directa: s\u00f3lo el paciente explica sus dolencias y s\u00edntomas, Indirecta: si el acompa\u00f1ante indica las dolencias y s\u00edntomas, Mixta: si el acompa\u00f1ante y el paciente indican las dolencias y s\u00edntomas");
        cmbTipoInformante.setSeleccionMultiple(false);
        
        lblRelatoCronologico.setText("Relato Cronol\u00f3gico");
        lblRelatoCronologico.setBounds(new Rectangle(15, 155, 115, 20));
        lblRelatoCronologico.setForeground(new Color(255, 130, 14));
        lblRelatoCronologico.setToolTipText("se registra como se ha presentado y evolucionado la enfermedad en el paciente");
        
        txtRelatoCronologico.setFont(new Font("SansSerif", 0, 11));
        txtRelatoCronologico.setLineWrap(true);
        txtRelatoCronologico.setTabSize(2);
        txtRelatoCronologico.setWrapStyleWord(true);
        txtRelatoCronologico.setToolTipText("se registra como se ha presentado y evolucionado la enfermedad en el paciente");
        ((PlainDocument) txtRelatoCronologico.getDocument()).setDocumentFilter(new DocumentFilterTextArea(500));
        
        scrRelatoCronologico.setBounds(new Rectangle(15, 175, 660, 90));
        scrRelatoCronologico.getViewport().add(txtRelatoCronologico, null);
        
        lblApetito.setText("Apetito");
        lblApetito.setBounds(new Rectangle(10, 20, 45, 20));
        lblApetito.setForeground(new Color(255, 130, 14));
        lblApetito.setMnemonic('A');
        
        cmbApetito.setBounds(new Rectangle(55, 20, 135, 20));
        cmbApetito.setFont(new Font("SansSerif", 0, 11));
        cmbApetito.setSeleccionMultiple(false);
        
        lblSed.setText("Sed");
        lblSed.setBounds(new Rectangle(10, 55, 40, 20));
        lblSed.setForeground(new Color(255, 130, 14));
        
        cmbSed.setBounds(new Rectangle(55, 55, 135, 20));
        cmbSed.setFont(new Font("SansSerif", 0, 11));
        cmbSed.setSeleccionMultiple(false);

        lblSuenio.setText("Sue\u00f1o");
        lblSuenio.setBounds(new Rectangle(225, 20, 40, 20));
        lblSuenio.setForeground(new Color(255, 130, 14));
        
        cmbSuenio.setBounds(new Rectangle(265, 20, 135, 20));
        cmbSuenio.setFont(new Font("SansSerif", 0, 11));
        cmbSuenio.setSeleccionMultiple(false);
        
        lblOrina.setText("Orina");
        lblOrina.setBounds(new Rectangle(225, 55, 40, 20));
        lblOrina.setForeground(new Color(255, 130, 14));
        
        cmbOrina.setBounds(new Rectangle(265, 55, 135, 20));
        cmbOrina.setFont(new Font("SansSerif", 0, 11));
        cmbOrina.setSeleccionMultiple(false);
        
        lblDeposiciones.setText("Deposiciones");
        lblDeposiciones.setBounds(new Rectangle(435, 20, 80, 20));
        lblDeposiciones.setForeground(new Color(255, 130, 14));
        
        cmbDeposiciones.setBounds(new Rectangle(520, 20, 135, 20));
        cmbDeposiciones.setFont(new Font("SansSerif", 0, 11));
        cmbDeposiciones.setSeleccionMultiple(false);
        //cmbDeposiciones.setNextObjTransferFocus(txtPA1);
        
        pnlFuncionesBiologicas.setBounds(new Rectangle(15, 270, 665, 90));
        pnlFuncionesBiologicas.setLayout(null);
        pnlFuncionesBiologicas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(43,141,39)),
                                                           "Funciones Biológicas",
                                                           TitledBorder.LEFT,
                                                           TitledBorder.DEFAULT_POSITION,
                                                           new Font("SansSerif",Font.PLAIN,12),
                                                           new Color(43,141,39)));

        pnlFuncionesBiologicas.setBackground(SystemColor.window);
        pnlFuncionesBiologicas.add(lblApetito, null);
        pnlFuncionesBiologicas.add(cmbApetito, null);
        pnlFuncionesBiologicas.add(lblSed, null);
        pnlFuncionesBiologicas.add(cmbSed, null);
        pnlFuncionesBiologicas.add(lblSuenio, null);
        pnlFuncionesBiologicas.add(cmbSuenio, null);
        pnlFuncionesBiologicas.add(lblOrina, null);
        pnlFuncionesBiologicas.add(cmbOrina, null);
        pnlFuncionesBiologicas.add(lblDeposiciones, null);

        pnlFuncionesBiologicas.add(cmbDeposiciones, null);
        pnlEnfermedadActual.setLayout(null);

        pnlEnfermedadActual.setBackground(SystemColor.window);
        pnlEnfermedadActual.add(lblMotivoConsulta, null);
        pnlEnfermedadActual.add(txtMotivoConsulta, null);
        pnlEnfermedadActual.add(lblFormaInicio, null);
        pnlEnfermedadActual.add(txtFormaInicio, null);
        pnlEnfermedadActual.add(lblSignos, null);
        pnlEnfermedadActual.add(scrPnlTxtSignos, null);
        //pnlEnfermedadActual.add(txtSignos, null);
        //pnlEnfermedadActual.add(lblSintomas, null);
        //pnlEnfermedadActual.add(txtSintomas, null);
        pnlEnfermedadActual.add(lblTiempoEnfermedad, null);
        pnlEnfermedadActual.add(txtTiempoEnfermedad, null);
        pnlEnfermedadActual.add(lblTipoInformante, null);
        pnlEnfermedadActual.add(cmbTipoInformante, null);
        pnlEnfermedadActual.add(lblRelatoCronologico, null);
        pnlEnfermedadActual.add(scrRelatoCronologico, null);


        /**********************************************************************/
        /************************ EXAMEN FISICO *******************************/
        /**********************************************************************/

        pnlEnfermedadActual.add(pnlFuncionesBiologicas, null);
        lblPA.setText("P.A.");
        lblPA.setBounds(new Rectangle(10, 30, 25, 20));
        lblPA.setForeground(new Color(255, 130, 14));
        
        txtPA1.setBounds(new Rectangle(35, 30, 35, 20));
        
        txtPA2.setBounds(new Rectangle(80, 30, 35, 20));
        
        lblPA1.setText("/");
        lblPA1.setBounds(new Rectangle(70, 30, 10, 20));
        lblPA1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPA1.setHorizontalTextPosition(SwingConstants.CENTER);
        
        lblPA2.setText("MMHG");
        lblPA2.setBounds(new Rectangle(115, 30, 35, 20));
        lblPA2.setFont(new Font("SansSerif", 0, 11));
        lblPA2.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblFR.setText("F.R.");
        lblFR.setBounds(new Rectangle(170, 30, 25, 20));
        lblFR.setForeground(new Color(255, 130, 14));
       
        txtFR.setBounds(new Rectangle(195, 30, 35, 20));
        
        lblFR1.setText("X'");
        lblFR1.setBounds(new Rectangle(230, 30, 15, 20));
        lblFR1.setFont(new Font("SansSerif", 0, 11));
        lblFR1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFR1.setHorizontalTextPosition(SwingConstants.CENTER);
        
        lblFC.setText("F.C.");
        lblFC.setBounds(new Rectangle(265, 30, 20, 20));
        lblFC.setForeground(new Color(255, 130, 14));
        
        txtFC.setBounds(new Rectangle(285, 30, 35, 20));
        
        lblFC1.setText("X'");
        lblFC1.setBounds(new Rectangle(320, 30, 15, 20));
        lblFC1.setFont(new Font("SansSerif", 0, 11));
        lblFC1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFC1.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblT.setText("Temp.");
        lblT.setBounds(new Rectangle(350, 30, 35, 20));
        lblT.setForeground(new Color(255, 130, 14));
        
        txtT.setBounds(new Rectangle(385, 30, 40, 20));
        
        lblT1.setText("\u00b0C");
        lblT1.setBounds(new Rectangle(425, 30, 15, 20));
        lblT1.setFont(new Font("SansSerif", 0, 11));
        lblT1.setHorizontalAlignment(SwingConstants.CENTER);
        lblT1.setHorizontalTextPosition(SwingConstants.CENTER);
        
        lblPeso.setText("Peso");
        lblPeso.setBounds(new Rectangle(455, 30, 30, 20));
        lblPeso.setForeground(new Color(255, 130, 14));
        
        txtPeso.setBounds(new Rectangle(485, 30, 45, 20));
        
        lblPeso1.setText("Kg");
        lblPeso1.setBounds(new Rectangle(530, 30, 20, 20));
        lblPeso1.setFont(new Font("SansSerif", 0, 11));
        lblPeso1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPeso1.setHorizontalTextPosition(SwingConstants.CENTER);
        
        lblTalla.setText("Talla");
        lblTalla.setBounds(new Rectangle(570, 30, 30, 20));
        lblTalla.setForeground(new Color(255, 130, 14));
        
        txtTalla.setBounds(new Rectangle(600, 30, 45, 20));
        
        lblTalla1.setText("cm");
        lblTalla1.setBounds(new Rectangle(645, 30, 20, 20));
        lblTalla1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTalla1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTalla1.setFont(new Font("SansSerif", 0, 11));
        
        pnlFuncionesVitales.setBounds(new Rectangle(15, 20, 685, 75));
        pnlFuncionesVitales.setLayout(null);
        pnlFuncionesVitales.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(43,141,39)),
                                                           "Funciones Vitales",
                                                           TitledBorder.LEFT,
                                                           TitledBorder.DEFAULT_POSITION,
                                                           new Font("SansSerif",Font.PLAIN,12),
                                                           new Color(43,141,39)));

        pnlFuncionesVitales.setBackground(SystemColor.window);
        pnlFuncionesVitales.add(lblPA, null);
        pnlFuncionesVitales.add(txtPA1, null);
        pnlFuncionesVitales.add(lblPA1, null);
        pnlFuncionesVitales.add(txtPA2, null);
        pnlFuncionesVitales.add(lblPA2, null);
        pnlFuncionesVitales.add(lblFR, null);
        pnlFuncionesVitales.add(txtFR, null);
        pnlFuncionesVitales.add(lblFR1, null);
        pnlFuncionesVitales.add(lblFC, null);
        pnlFuncionesVitales.add(txtFC, null);
        pnlFuncionesVitales.add(lblFC1, null);
        pnlFuncionesVitales.add(lblT, null);
        pnlFuncionesVitales.add(txtT, null);
        pnlFuncionesVitales.add(lblT1, null);
        pnlFuncionesVitales.add(lblPeso, null);


        pnlFuncionesVitales.add(txtPeso, null);
        pnlFuncionesVitales.add(lblPeso1, null);
        pnlFuncionesVitales.add(lblTalla, null);
        pnlFuncionesVitales.add(txtTalla, null);
        pnlFuncionesVitales.add(lblTalla1, null);

        lblEstadoGeneral.setText("Estado General");
        lblEstadoGeneral.setBounds(new Rectangle(15, 25, 85, 20));
        lblEstadoGeneral.setForeground(new Color(255, 130, 14));
        
        cmbEstadoGeneral.setBounds(new Rectangle(100, 25, 135, 20));
        cmbEstadoGeneral.setFont(new Font("SansSerif", 0, 11));
        cmbEstadoGeneral.setSeleccionMultiple(false);

        lblEstadoConciencia.setText("Estado Conciencia");
        lblEstadoConciencia.setBounds(new Rectangle(285, 25, 105, 20));
        lblEstadoConciencia.setForeground(new Color(255, 130, 14));
        
        txtEstadoConciencia.setBounds(new Rectangle(390, 25, 215, 20));
        txtEstadoConciencia.setLengthText(500);
        
        lblExamenFisicoDirigido.setText("Examen F\u00edsico Dirigido");
        lblExamenFisicoDirigido.setBounds(new Rectangle(15, 55, 135, 15));
        lblExamenFisicoDirigido.setForeground(new Color(255, 130, 14));
        
        txtFisicoDirigido.setWrapStyleWord(true);
        txtFisicoDirigido.setTabSize(2);
        txtFisicoDirigido.setLineWrap(true);
        txtFisicoDirigido.setFont(new Font("SansSerif", 0, 11));
        ((PlainDocument) txtFisicoDirigido.getDocument()).setDocumentFilter(new DocumentFilterTextArea(2000));
        
        scrFisicoDirigido.setBounds(new Rectangle(15, 75, 600, 155));
        scrFisicoDirigido.getViewport().add(txtFisicoDirigido, null);
        
        pnlExamenFisicoInter.setBounds(new Rectangle(40, 110, 630, 250));
        pnlExamenFisicoInter.setLayout(null);
        pnlExamenFisicoInter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(43,141,39)),
                                                           "Examen Físico",
                                                           TitledBorder.LEFT,
                                                           TitledBorder.DEFAULT_POSITION,
                                                           new Font("SansSerif",Font.PLAIN,12),
                                                           new Color(43,141,39)));

        pnlExamenFisicoInter.setBackground(SystemColor.window);
        pnlExamenFisicoInter.add(lblEstadoGeneral, null);
        pnlExamenFisicoInter.add(cmbEstadoGeneral, null);
        pnlExamenFisicoInter.add(lblEstadoConciencia, null);
        pnlExamenFisicoInter.add(txtEstadoConciencia, null);
        pnlExamenFisicoInter.add(scrFisicoDirigido, null);
        pnlExamenFisicoInter.add(lblExamenFisicoDirigido, null);
        
        pnlExamenFisico.setLayout(null);

        pnlExamenFisico.setBackground(SystemColor.window);
        pnlExamenFisico.add(pnlFuncionesVitales, null);
        pnlExamenFisico.add(pnlExamenFisicoInter, null);
        
        
        /********************************************************************/
        /************************ DIAGNOSTICO *******************************/
        /********************************************************************/
        

        lblDiagnostico.setText("Diagn\u00f3stico");
        lblDiagnostico.setBounds(new Rectangle(25, 10, 70, 20));
        lblDiagnostico.setForeground(new Color(255, 130, 14));
        
        txtCodDiagnostico.setBounds(new Rectangle(25, 30, 75, 20));
        
        txtDescDiagnostico.setBounds(new Rectangle(110, 30, 385, 20));
        txtDescDiagnostico.setEditable(false);
        
        lblTipoDiagnostico.setText("Tipo");
        lblTipoDiagnostico.setBounds(new Rectangle(505, 10, 80, 20));
        
        cmbTipoDiagnostico.setBounds(new Rectangle(505, 30, 180, 20));
        cmbTipoDiagnostico.setFont(new Font("SansSerif", 0, 11));
        cmbTipoDiagnostico.setSeleccionMultiple(false);
        
        lblTituloListaDiagnostico.setText("Lista de Diagnostico");
        lblTituloListaDiagnostico.setBounds(new Rectangle(5, 0, 310, 25));
        lblTituloListaDiagnostico.setMnemonic('D');
        
        pnlTitleListaDiagnostico.setBounds(new Rectangle(15, 75, 675, 25));

        scrListaDiagnostico.setBounds(new Rectangle(15, 100, 675, 260));
        scrListaDiagnostico.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlDiagnostico.setLayout(null);
        pnlDiagnostico.setBackground(SystemColor.window);
        pnlDiagnostico.add(lblMensajeDiagnostico, null);
        pnlDiagnostico.add(txtCodDiagnostico, null);
        pnlDiagnostico.add(lblDiagnostico, null);
        pnlDiagnostico.add(txtDescDiagnostico, null);
        pnlDiagnostico.add(lblTipoDiagnostico, null);
        pnlDiagnostico.add(cmbTipoDiagnostico, null);
        pnlDiagnostico.add(pnlTitleListaDiagnostico, null);
        pnlDiagnostico.add(scrListaDiagnostico, null);

        /********************************************************************/
        /********************* TRATAMIENTO **********************************/
        /********************************************************************/


        pnlTitleListaDiagnostico.add(lblTituloListaDiagnostico, null);
        pnlTitleListaDiagnostico.add(btnAgregarDiagnostico, null);
        pnlTitleListaDiagnostico.add(btnQuitarDiagnostico, null);
        scrListaDiagnostico.getViewport().add(tblListaDiagnostico, null);
        
        lblTratamientoProducto.setText("Producto");
        lblTratamientoProducto.setBounds(new Rectangle(15, 5, 65, 20));
        lblTratamientoProducto.setForeground(new Color(255, 130, 14));
        
        txtProductoTratamiento.setBounds(new Rectangle(15, 35, 520, 25));

        lblSugerido.setBounds(new Rectangle(545, 0, 170, 35));

        lblSugerido.setForeground(new Color(231, 0, 0));
        lblSugerido.setFont(new Font("SansSerif", 1, 12));
        lblSugerido.setBorder(BorderFactory.createTitledBorder("Se vende Sugerido en :"));
        lblSugerido.setHorizontalAlignment(SwingConstants.CENTER);
        lblUnidadRecetada.setBounds(new Rectangle(545, 40, 170, 35));
        //lblUnidadRecetada.setEnabled(false);

        lblUnidadRecetada.setFont(new Font("SansSerif", 1, 12));
        lblUnidadRecetada.setForeground(new Color(0, 99, 0));
        lblUnidadRecetada.setBorder(BorderFactory.createTitledBorder("Unidad a Recetar :"));
        lblUnidadRecetada.setHorizontalAlignment(SwingConstants.CENTER);
        lblFrecuenciaTratamiento.setText("Frecuencia (x d\u00eda)");
        lblFrecuenciaTratamiento.setBounds(new Rectangle(15, 70, 100, 20));
        lblFrecuenciaTratamiento.setForeground(new Color(255, 130, 14));
        
        txtFrecuenciaTratamiento.setBounds(new Rectangle(15, 90, 95, 20));
        txtFrecuenciaTratamiento.setEnabled(false);

        txtFrecuenciaTratamiento.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtFrecuenciaTratamiento_keyPressed(e);
                }
            });
        lblDuracionTratamiento.setText("Duraci\u00f3n (d\u00edas)");
        lblDuracionTratamiento.setBounds(new Rectangle(125, 70, 85, 20));
        lblDuracionTratamiento.setForeground(new Color(255, 130, 14));
        
        txtDuracionTratamiento.setBounds(new Rectangle(125, 90, 80, 20));
        txtDuracionTratamiento.setEnabled(false);

        txtDuracionTratamiento.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtDuracionTratamiento_keyPressed(e);
                }
            });
        lblViaAdministracion.setText("Via Administraci\u00f3n");
        lblViaAdministracion.setForeground(new Color(255, 130, 14));
        lblViaAdministracion.setBounds(new Rectangle(220, 70, 105, 20));
        
        cmbViaAdministracion.setBounds(new Rectangle(220, 90, 175, 20));
        cmbViaAdministracion.setFont(new Font("SansSerif", 0, 11));
        cmbViaAdministracion.setEnabled(false);
        cmbViaAdministracion.setSeleccionMultiple(false);

        cmbViaAdministracion.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbViaAdministracion_keyPressed(e);
                }
            });
        lblDosis.setText("Dosis");
        lblDosis.setBounds(new Rectangle(410, 70, 40, 20));
        lblDosis.setForeground(new Color(255, 130, 14));
        
        txtDosis.setBounds(new Rectangle(410, 90, 225, 20));
        txtDosis.setEnabled(false);

        txtDosis.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtDosis_keyPressed(e);
                }
            });
        lblCantidad.setText("Cantidad");
        lblCantidad.setBounds(new Rectangle(645, 70, 55, 20));
        lblCantidad.setForeground(new Color(255, 130, 14));
        
        txtCantidadTratamiento.setBounds(new Rectangle(645, 90, 60, 20));
        txtCantidadTratamiento.setEnabled(false);
        
        lblTitleListaReceta.setText("Resumen Receta");
        lblTitleListaReceta.setBounds(new Rectangle(5, 0, 280, 25));
        lblTitleListaReceta.setMnemonic('R');
        
        pnlTitleListaReceta.setBounds(new Rectangle(15, 120, 690, 25));

        scrListaReceta.setBounds(new Rectangle(15, 140, 690, 145));
        scrListaReceta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lblValidezReceta.setText("Validez de Receta (días): ");
        lblValidezReceta.setBounds(new Rectangle(15, 290, 140, 20));
        lblValidezReceta.setForeground(new Color(255, 130, 14));
        //lblValidezReceta.addActionListener(new ActionTransferFocus(txtValidezReceta));
        
        txtValidezReceta.setBounds(new Rectangle(245, 290, 105, 20));
        //txtValidezReceta.addActionListener(new ActionTransferFocus(txtIndicacionesGeneralesTratamiento));
        
        lblIndicacionesGeneralesTratamiento.setText("Recomendaciones Generales");
        lblIndicacionesGeneralesTratamiento.setBounds(new Rectangle(15, 310, 260, 20));
        lblIndicacionesGeneralesTratamiento.setForeground(new Color(255, 130, 14));
        
        txtIndicacionesGeneralesTratamiento.setWrapStyleWord(true);
        txtIndicacionesGeneralesTratamiento.setTabSize(2);
        txtIndicacionesGeneralesTratamiento.setFont(new Font("SansSerif", 0, 11));
        txtIndicacionesGeneralesTratamiento.setLineWrap(true);
        
        scrIndicacionesGeneralesTratamiento.setBounds(new Rectangle(15, 335, 685, 55));

        pnlTratamiento.setLayout(null);

        pnlTratamiento.setBackground(SystemColor.window);
        pnlTratamiento.add(lblFechaValidezReceta, null);
        pnlTratamiento.add(txtCantDiasValidezReceta, null);
        pnlTratamiento.add(lblTratamientoProducto, null);
        pnlTratamiento.add(txtProductoTratamiento, null);
        pnlTratamiento.add(lblSugerido, null);
        pnlTratamiento.add(lblUnidadRecetada, null);
        pnlTratamiento.add(lblFrecuenciaTratamiento, null);
        pnlTratamiento.add(txtFrecuenciaTratamiento, null);
        pnlTratamiento.add(lblDuracionTratamiento, null);
        pnlTratamiento.add(txtDuracionTratamiento, null);
        pnlTratamiento.add(lblViaAdministracion, null);
        pnlTratamiento.add(cmbViaAdministracion, null);
        pnlTratamiento.add(lblDosis, null);
        pnlTratamiento.add(txtDosis, null);
        pnlTratamiento.add(lblCantidad, null);
        pnlTratamiento.add(txtCantidadTratamiento, null);
        pnlTratamiento.add(pnlTitleListaReceta, null);
        scrIndicacionesGeneralesTratamiento.getViewport().add(txtIndicacionesGeneralesTratamiento, null);
        pnlTratamiento.add(scrIndicacionesGeneralesTratamiento, null);
        pnlTratamiento.add(lblValidezReceta, null);
        pnlTitleListaReceta.add(lblTitleListaReceta, null);
        pnlTitleListaReceta.add(btnAgregarReceta, null);


        /*************************************************************************************/
        /***************************** EXAMENES AUXILIARES ***********************************/
        /*************************************************************************************/

        pnlTitleListaReceta.add(btnQuitarReceta, null);
        pnlTratamiento.add(txtValidezReceta, null);
        pnlTratamiento.add(scrListaReceta, null);
        scrListaReceta.getViewport().add(tblListaReceta, null);
        pnlTratamiento.add(lblIndicacionesGeneralesTratamiento, null);
        lblExaAuxLaborotarios.setText("Laboratorios");
        lblExaAuxLaborotarios.setBounds(new Rectangle(20, 15, 85, 15));
        lblExaAuxLaborotarios.setForeground(new Color(255, 130, 14));
        
        txtExaAuxLaborotarios.setWrapStyleWord(true);
        txtExaAuxLaborotarios.setTabSize(2);
        txtExaAuxLaborotarios.setLineWrap(true);
        txtExaAuxLaborotarios.setFont(new Font("SansSerif", 0, 11));
        ((PlainDocument) txtExaAuxLaborotarios.getDocument()).setDocumentFilter(new DocumentFilterTextArea(500));
        
        scrExaAuxLaborotarios.setBounds(new Rectangle(20, 35, 670, 125));
        scrExaAuxLaborotarios.getViewport().add(txtExaAuxLaborotarios, null);
        
        lblExaAuxImagenologicos.setText("Imagenologicos");
        lblExaAuxImagenologicos.setBounds(new Rectangle(20, 170, 105, 15));
        lblExaAuxImagenologicos.setForeground(new Color(255, 130, 14));
        
        txtExaAuxImagenologicos.setFont(new Font("SansSerif", 0, 11));
        txtExaAuxImagenologicos.setLineWrap(true);
        txtExaAuxImagenologicos.setTabSize(2);
        txtExaAuxImagenologicos.setWrapStyleWord(true);
        ((PlainDocument) txtExaAuxImagenologicos.getDocument()).setDocumentFilter(new DocumentFilterTextArea(250));
        
        scrExaAuxImagenologicos.setBounds(new Rectangle(20, 190, 670, 120));
        scrExaAuxImagenologicos.getViewport().add(txtExaAuxImagenologicos, null);
        
        pnlExamenesAuxiliares.setLayout(null);

        pnlExamenesAuxiliares.setBackground(SystemColor.window);
        pnlExamenesAuxiliares.add(lblExaAuxLaborotarios, null);
        pnlExamenesAuxiliares.add(scrExaAuxLaborotarios, null);
        pnlExamenesAuxiliares.add(lblExaAuxImagenologicos, null);
        pnlExamenesAuxiliares.add(scrExaAuxImagenologicos, null);
        
        
        /*********************************************************************/
        /**********************PROCEDIMIENTO***********************************/
        /*********************************************************************/
        
        
        lblProcedimiento.setText("Procedimiento");
        lblProcedimiento.setBounds(new Rectangle(10, 5, 85, 15));
        lblProcedimiento.setForeground(new Color(255, 130, 14));
        
        txtProcedimiento.setWrapStyleWord(true);
        txtProcedimiento.setTabSize(2);
        txtProcedimiento.setFont(new Font("SansSerif", 0, 11));
        txtProcedimiento.setLineWrap(true);
        ((PlainDocument) txtProcedimiento.getDocument()).setDocumentFilter(new DocumentFilterTextArea(500));
        
        scrProcedimiento.getViewport().add(txtProcedimiento, null);
        scrProcedimiento.setBounds(new Rectangle(10, 20, 695, 70));
        
        lblInterconsulta.setText("Interconsulta");
        lblInterconsulta.setBounds(new Rectangle(10, 95, 85, 15));
        lblInterconsulta.setForeground(new Color(255, 130, 14));
        
        txtInterconsulta.setWrapStyleWord(true);
        txtInterconsulta.setTabSize(2);
        txtInterconsulta.setLineWrap(true);
        txtInterconsulta.setFont(new Font("SansSerif", 0, 11));
        ((PlainDocument) txtInterconsulta.getDocument()).setDocumentFilter(new DocumentFilterTextArea(500));
        
        scrInterconsulta.setBounds(new Rectangle(10, 115, 695, 95));
        scrInterconsulta.getViewport().add(txtInterconsulta, null);
        
        lblTransferencia.setText("Transferencia");
        lblTransferencia.setBounds(new Rectangle(10, 215, 85, 15));
        lblTransferencia.setForeground(new Color(255, 130, 14));
        
        txtTransferencia.setWrapStyleWord(true);
        txtTransferencia.setTabSize(2);
        txtTransferencia.setLineWrap(true);
        txtTransferencia.setFont(new Font("SansSerif", 0, 11));
        ((PlainDocument) txtTransferencia.getDocument()).setDocumentFilter(new DocumentFilterTextArea(500));
        
        scrTransferencia.setBounds(new Rectangle(10, 230, 695, 95));

        lblDescansoMedico.setText("Descanso M\u00e9dico");
        lblDescansoMedico.setBounds(new Rectangle(40, 340, 105, 20));
        lblDescansoMedico.setForeground(new Color(255, 130, 14));
        
        txtDescansoMedicoInicio.setBounds(new Rectangle(160, 340, 100, 20));
        
        txtDescansoMedicoFin.setBounds(new Rectangle(275, 340, 100, 20));
        
        lblProximaCita.setText("Fecha Proxima Cita");
        lblProximaCita.setBounds(new Rectangle(430, 340, 110, 20));
        lblProximaCita.setForeground(new Color(255, 130, 14));
        
        txtProximaCita.setBounds(new Rectangle(545, 340, 120, 20));
        
        pnlProcedimiento.setLayout(null);

        pnlProcedimiento.setBackground(SystemColor.window);
        pnlProcedimiento.add(lblProcedimiento, null);
        pnlProcedimiento.add(scrProcedimiento, null);
        pnlProcedimiento.add(lblInterconsulta, null);
        pnlProcedimiento.add(scrInterconsulta, null);
        pnlProcedimiento.add(lblTransferencia, null);


        /************************************************************************/
        /*********************************************************************/

        scrTransferencia.getViewport().add(txtTransferencia, null);
        pnlProcedimiento.add(scrTransferencia, null);
        pnlProcedimiento.add(lblDescansoMedico, null);
        pnlProcedimiento.add(txtDescansoMedicoInicio, null);
        pnlProcedimiento.add(txtDescansoMedicoFin, null);
        pnlProcedimiento.add(lblProximaCita, null);
        pnlProcedimiento.add(txtProximaCita, null);
        
        txtCantDiasValidezReceta.setBounds(new Rectangle(155, 290, 35, 20));
        lblFechaValidezReceta.setText("Fecha:");
        lblFechaValidezReceta.setBounds(new Rectangle(205, 290, 40, 20));
        btnAgregarDiagnostico.setText("[+]");
        btnAgregarDiagnostico.setBounds(new Rectangle(555, 3, 55, 20));
        btnAgregarDiagnostico.setFont(new Font("SansSerif", 1, 11));
        btnQuitarDiagnostico.setText("[ - ]");
        btnQuitarDiagnostico.setBounds(new Rectangle(615, 3, 55, 20));
        btnQuitarDiagnostico.setFont(new Font("SansSerif", 1, 11));
        btnAgregarReceta.setText("[ + ]");
        btnAgregarReceta.setBounds(new Rectangle(560, 3, 55, 20));
        btnAgregarReceta.setFont(new Font("SansSerif", 1, 11));
        btnAgregarReceta.setActionCommand("[ + ]");
        btnQuitarReceta.setText("[ - ]");
        btnQuitarReceta.setBounds(new Rectangle(630, 3, 55, 20));
        btnQuitarReceta.setFont(new Font("SansSerif", 1, 11));
        lblMensajeDiagnostico.setText("Presione [Enter] para agregar diagnostico");
        lblMensajeDiagnostico.setBounds(new Rectangle(25, 50, 470, 15));

        lblEsc.setText("[ ESC ] Salir");
        lblEsc.setPreferredSize(new Dimension(130, 20));

        lblF4.setText("[ F4 ] Cambiar Panel");
        lblF4.setPreferredSize(new Dimension(130, 20));

        lblF2.setText("[ F2 ] Antecedentes");
        lblF6.setText("[ F6 ] Imprimir HC");
        lblF2.setPreferredSize(new Dimension(130, 20));
        lblF6.setPreferredSize(new Dimension(130, 20));

        lblF5.setText("[ F5 ] Historia Clinica");
        lblF5.setPreferredSize(new Dimension(130, 20));

        lblF11.setText("[ F11 ] Grabar");
        lblF11.setPreferredSize(new Dimension(130, 20));
        
        tabpContenedor.addTab("Enfermedad Actual", pnlEnfermedadActual);
        tabpContenedor.addTab("Examen Físico", pnlExamenFisico);
        tabpContenedor.addTab("Diagnóstico", pnlDiagnostico);
        tabpContenedor.addTab("Tratamiento", pnlTratamiento);
        tabpContenedor.addTab("Examenes Auxiliares", pnlExamenesAuxiliares);
        tabpContenedor.addTab("Procedimiento, Interconsultas y Otros", pnlProcedimiento);
        tabpContenedor.setBounds(new Rectangle(15, 10, 725, 420));
        tabpContenedor.setFont(new Font("SansSerif", 0, 11));


        tabpContenedor.setBackground(SystemColor.window);
        pnlBotonesFuncion.setBounds(new Rectangle(15, 440, 725, 30));
        pnlBotonesFuncion.setBackground(SystemColor.window);

        /*pnlContenedor.add(lblF4, null);
        pnlContenedor.add(lblEsc, null);
        pnlContenedor.add(lblF2, null);
        pnlContenedor.add(lblF5, null);
        pnlContenedor.add(lblF11, null);*/
        pnlBotonesFuncion.add(lblF11, null);
        pnlBotonesFuncion.add(lblF5, null);
        pnlBotonesFuncion.add(lblF2, null);
        pnlBotonesFuncion.add(lblF6, null);
        lblF6.setVisible(false);
        pnlBotonesFuncion.add(lblF4, null);
        pnlBotonesFuncion.add(lblEsc, null);
        pnlContenedor.add(pnlBotonesFuncion, null);
        pnlContenedor.add(tabpContenedor, null);
        pnlContenedor.setBounds(new Rectangle(0, 0, 675, 405));
        
        this.getContentPane().add(pnlContenedor, null);
        
        
    }
    
    private void inicialize(){
        
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtMotivoConsulta);
        agregarNombresACampos();
        cargarCombos();
        crearTablas();
        configurarEventosAComponentes();
        mostrarDatosConsulta();
        cargarModoVista();
       
        if(!isModoVisual()){
            HiloCargaProdReceta hilo = new HiloCargaProdReceta();
            hilo.start();
        }
    }
    
    private void configurarCampoTextArea(JTextArea textArea, Component transferFocus){
        if(transferFocus!=null)
            textArea.setNextFocusableComponent(transferFocus);
        textArea.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contadorEnter++;
                    if(contadorEnter==2){
                        contadorEnter = 0;
                        e.consume();
                        if(((JTextArea)e.getSource()).getNextFocusableComponent() != null)
                            FarmaUtility.moveFocus(((JTextArea)e.getSource()).getNextFocusableComponent());
                    }
                }else{
                    contadorEnter = 0;
                }
            }
        });
        
        textArea.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contadorEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contadorEnter = 0;
            }
        });
    }
    
    private void configurarEventosAComponentes(){
        
        tabpContenedor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarTabPanel();
            }
        });

        tabpContenedor.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER)
                    mostrarTabPanel();
                
            }
        });
        
        lblMotivoConsulta.addActionListener(new ActionTransferFocus(txtMotivoConsulta));
        txtMotivoConsulta.addActionListener(new ActionTransferFocus(txtSignos));
        lblSignos.addActionListener(new ActionTransferFocus(txtSignos));
        configurarCampoTextArea(txtSignos, txtFormaInicio);
        
        lblFormaInicio.addActionListener(new ActionTransferFocus(txtFormaInicio));
        txtFormaInicio.addActionListener(new ActionTransferFocus(txtTiempoEnfermedad));
        
        lblTiempoEnfermedad.addActionListener(new ActionTransferFocus(txtTiempoEnfermedad));
        txtTiempoEnfermedad.addActionListener(new ActionTransferFocus(cmbTipoInformante));        
        
        lblTipoInformante.addActionListener(new ActionTransferFocus(cmbTipoInformante));
        cmbTipoInformante.setNextObjTransferFocus(txtRelatoCronologico);
        
        lblRelatoCronologico.addActionListener(new ActionTransferFocus(txtRelatoCronologico));
        configurarCampoTextArea(txtRelatoCronologico, cmbApetito);
        
        lblApetito.addActionListener(new ActionTransferFocus(cmbApetito));
        cmbApetito.setNextObjTransferFocus(cmbSed);
        
        lblSed.addActionListener(new ActionTransferFocus(cmbSed));
        cmbSed.setNextObjTransferFocus(cmbSuenio);
        
        lblSuenio.addActionListener(new ActionTransferFocus(cmbSuenio));
        cmbSuenio.setNextObjTransferFocus(cmbOrina);
        
        lblOrina.addActionListener(new ActionTransferFocus(cmbOrina));
        cmbOrina.setNextObjTransferFocus(cmbDeposiciones);
        
        lblDeposiciones.addActionListener(new ActionTransferFocus(cmbDeposiciones));       
        
        lblPA.addActionListener(new ActionTransferFocus(txtPA1));
        txtPA1.addActionListener(new ActionTransferFocus(txtPA2));
        txtPA2.addActionListener(new ActionTransferFocus(txtFR));
        
        lblFR.addActionListener(new ActionTransferFocus(txtFR));
        txtFR.addActionListener(new ActionTransferFocus(txtFC));
        
        lblFC.addActionListener(new ActionTransferFocus(txtFC));
        txtFC.addActionListener(new ActionTransferFocus(txtT));
        
        lblT.addActionListener(new ActionTransferFocus(txtT));
        txtT.addActionListener(new ActionTransferFocus(txtPeso
                                                       ));
        lblPeso.addActionListener(new ActionTransferFocus(txtPeso));
        txtPeso.addActionListener(new ActionTransferFocus(txtTalla));
        
        lblTalla.addActionListener(new ActionTransferFocus(txtTalla));
        txtTalla.addActionListener(new ActionTransferFocus(cmbEstadoGeneral));
        
        lblEstadoGeneral.addActionListener(new ActionTransferFocus(cmbEstadoGeneral));
        cmbEstadoGeneral.setNextObjTransferFocus(txtEstadoConciencia);
        
        lblEstadoConciencia.addActionListener(new ActionTransferFocus(txtEstadoConciencia));
        txtEstadoConciencia.addActionListener(new ActionTransferFocus(txtFisicoDirigido));
        
        lblExamenFisicoDirigido.addActionListener(new ActionTransferFocus(txtFisicoDirigido));
        txtFisicoDirigido.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contadorEnter++;
                    if(contadorEnter==2){
                        contadorEnter = 0;
                        e.consume();
                        tabpContenedor.setSelectedIndex(2); 
                    }
                }else{
                    contadorEnter = 0;
                }
            }
        });
        txtFisicoDirigido.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contadorEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contadorEnter = 0;
            }
        });
        
        lblDiagnostico.addActionListener(new ActionTransferFocus(txtCodDiagnostico));
        lblDiagnostico.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                FarmaUtility.moveFocus(txtCodDiagnostico);
            }
        });
        
        txtCodDiagnostico.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode()!=KeyEvent.VK_ENTER  || 
                   e.getKeyCode() != KeyEvent.VK_DOWN || 
                   e.getKeyCode() != KeyEvent.VK_UP)
                    e.consume();
            }
            
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !isModoVisual()){
                    e.consume();
                    mostrarDiagnostico(e);
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
                         e.getKeyCode() == KeyEvent.VK_UP){
                    //ubicarDiagnostico((e.getKeyCode() == KeyEvent.VK_DOWN));
                    FarmaGridUtils.aceptarTeclaPresionada(e, tblListaDiagnostico, null, 0);
                }
            }
        });
        txtCodDiagnostico.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                if(txtCodDiagnostico.isEditable() && txtCodDiagnostico.isEnabled() && !isModoVisual()){
                    lblMensajeDiagnostico.setVisible(true);
                }else
                    lblMensajeDiagnostico.setVisible(false);
            }
            
            public void focusLost(FocusEvent e){
                lblMensajeDiagnostico.setVisible(false);
            }
        });
        //cmbTipoDiagnostico.setNextObjTransferFocus(txtCodDiagnostico);
        cmbTipoDiagnostico.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ADD){
                    e.consume();
                    btnAgregarDiagnostico.doClick();
                }
            }
        });
        lblTituloListaDiagnostico.addActionListener(new ActionTransferFocus(tblListaDiagnostico));
        
        btnAgregarDiagnostico.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(btnAgregarDiagnostico.isVisible())
                    agregarDiagnostico();
            }
        });
        
        btnQuitarDiagnostico.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(btnQuitarDiagnostico.isVisible())
                    quitarDiagnostico();
            }
        });

        tblListaDiagnostico.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){ 
                if(e.getKeyCode() == KeyEvent.VK_SUBTRACT)
                    btnQuitarDiagnostico.doClick();
                if(e.getKeyCode() == KeyEvent.VK_ADD)
                    FarmaUtility.moveFocus(txtCodDiagnostico);
            }
        });


        lblTratamientoProducto.addActionListener(new ActionTransferFocus(txtProductoTratamiento));
        txtProductoTratamiento.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode()!=KeyEvent.VK_ENTER)
                    e.consume();
            }
            
            public void keyPressed(KeyEvent e){
                mostrarProducto(e);
            }
        });
        
        lblFrecuenciaTratamiento.addActionListener(new ActionTransferFocus(txtFrecuenciaTratamiento));
        
        txtFrecuenciaTratamiento.addActionListener(new ActionTransferFocus(txtDuracionTratamiento));
        
        lblDuracionTratamiento.addActionListener(new ActionTransferFocus(txtDuracionTratamiento));
        
        txtDuracionTratamiento.addActionListener(new ActionTransferFocus(cmbViaAdministracion));
        txtDuracionTratamiento.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                calcularCantidadVtaDosis();
            }
            
            public void focusLost(FocusEvent e){
                calcularCantidadVtaDosis();
            }
        });
        
        lblViaAdministracion.addActionListener(new ActionTransferFocus(cmbViaAdministracion));
        cmbViaAdministracion.setNextObjTransferFocus(txtDosis);
        
        lblDosis.addActionListener(new ActionTransferFocus(txtDosis));
        txtDosis.addActionListener(new ActionTransferFocus(txtCantidadTratamiento));
        
        lblCantidad.addActionListener(new ActionTransferFocus(txtCantidadTratamiento));
        txtCantidadTratamiento.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ADD)
                    btnAgregarReceta.doClick();
            }
        });
        
        lblTitleListaReceta.addActionListener(new ActionTransferFocus(tblListaReceta));
        
        tblListaReceta.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_SUBTRACT)
                    btnQuitarReceta.doClick();
                if(e.getKeyCode() == KeyEvent.VK_ADD)
                    FarmaUtility.moveFocus(txtProductoTratamiento);
            }
        });
        
        btnAgregarReceta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(btnAgregarReceta.isVisible())
                    agregarProductoAReceta();
            }
        });
        
        btnQuitarReceta.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(btnQuitarReceta.isVisible()) 
                    quitarProductoAReceta();
            }
        });
        
        lblValidezReceta.addActionListener(new ActionTransferFocus(txtCantDiasValidezReceta));
        txtCantDiasValidezReceta.addActionListener(new ActionTransferFocus(txtIndicacionesGeneralesTratamiento));
        txtCantDiasValidezReceta.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                calcularFechaValidezReceta();
            }
        });
        
        lblIndicacionesGeneralesTratamiento.addActionListener(new ActionTransferFocus(txtIndicacionesGeneralesTratamiento));
        txtIndicacionesGeneralesTratamiento.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contadorEnter++;
                    if(contadorEnter==2){
                        contadorEnter = 0;
                        e.consume();
                        tabpContenedor.setSelectedIndex(4); 
                    }
                }else{
                    contadorEnter = 0;
                }
            }
        });
        txtIndicacionesGeneralesTratamiento.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contadorEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contadorEnter = 0;
            }
        });
        
        
        
        lblExaAuxLaborotarios.addActionListener(new ActionTransferFocus(txtExaAuxLaborotarios));
        lblExaAuxLaborotarios.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                FarmaUtility.moveFocus(txtExaAuxLaborotarios);
            }
        });
        
        configurarCampoTextArea(txtExaAuxLaborotarios, txtExaAuxImagenologicos);
        
        lblExaAuxImagenologicos.addActionListener(new ActionTransferFocus(txtExaAuxImagenologicos));        
        
        txtExaAuxImagenologicos.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contadorEnter++;
                    if(contadorEnter==2){
                        contadorEnter = 0;
                        e.consume();
                        tabpContenedor.setSelectedIndex(5); 
                    }
                }else{
                    contadorEnter = 0;
                }
            }
        });
        txtExaAuxImagenologicos.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contadorEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contadorEnter = 0;
            }
        });
        
        lblProcedimiento.addActionListener(new ActionTransferFocus(txtProcedimiento));
        lblProcedimiento.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                FarmaUtility.moveFocus(txtProcedimiento);
            }
        });
        
        configurarCampoTextArea(txtProcedimiento, txtInterconsulta);
        
        lblInterconsulta.addActionListener(new ActionTransferFocus(txtInterconsulta));
        lblInterconsulta.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                FarmaUtility.moveFocus(txtInterconsulta);
            }
        });
        
        configurarCampoTextArea(txtInterconsulta, txtTransferencia);
        
        lblTransferencia.addActionListener(new ActionTransferFocus(txtTransferencia));
        lblTransferencia.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                FarmaUtility.moveFocus(txtTransferencia);
            }
        });
        
        configurarCampoTextArea(txtTransferencia, txtDescansoMedicoInicio);
        
        lblDescansoMedico.addActionListener(new ActionTransferFocus(txtDescansoMedicoInicio));
        lblDescansoMedico.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                FarmaUtility.moveFocus(txtDescansoMedicoInicio);
            }
        });
        
        txtDescansoMedicoInicio.addActionListener(new ActionTransferFocus(txtDescansoMedicoFin));
        
        txtDescansoMedicoFin.addActionListener(new ActionTransferFocus(txtProximaCita));
        
        lblProximaCita.addActionListener(new ActionTransferFocus(txtProximaCita));
        
        aplicarFormatoFecha(txtValidezReceta);
        aplicarFormatoFecha(txtDescansoMedicoInicio);
        aplicarFormatoFecha(txtDescansoMedicoFin);
        aplicarFormatoFecha(txtProximaCita);
        
        configurarEventosComunes(pnlContenedor);
    }
    
    
    private void aplicarFormatoFecha(JTextFieldSanSerif textoField){
        textoField.setLengthText(10);
        textoField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                FarmaUtility.dateComplete((JTextField)e.getSource(), e);
            }
        });
        
    }
    
    private void configurarEventosComunes(Component panel){
        if(panel instanceof JTextField)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JTable)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JComboBox)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JTextArea)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof Container){
            Component[] lst = ((Container)panel).getComponents();
            for(Component component : lst)
                configurarEventosComunes(component);
        }
    }
    
    private void cargarModoVista(){
        if(isModoVisual()){
            lblF11.setVisible(false);
            lblF5.setVisible(false);
            setImprimioReceta(true);
            setGraboReceta(true);
            deshabilitar(pnlContenedor, false);
        }else{
            txtCantDiasValidezReceta.setInt(utility.obtenerCantidadDiasVigencia(this));
            calcularFechaValidezReceta();
        }
        if(isBandImpresion()){
            lblF11.setVisible(false);
            lblF2.setVisible(false);
            lblF4.setVisible(false);
            lblF5.setVisible(false);
            lblF6.setVisible(true);
        }
    }
    
    private void calcularFechaValidezReceta(){
        if(txtCantDiasValidezReceta.getText().trim().length()>0){
            int cantidadDias = txtCantDiasValidezReceta.getInt();
            Calendar calActual = Calendar.getInstance();
            calActual.add(Calendar.DAY_OF_MONTH, cantidadDias);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            txtValidezReceta.setText(format.format(calActual.getTime()));
        }else{
            txtValidezReceta.setText("");
        }
    }
    
    private void deshabilitar(Component panel, boolean activo){
        if(panel instanceof JComboWithCheck)
            ((JComboWithCheck)panel).setSeleccionable(activo);
        else if(panel instanceof JComboBox)
            ((JComboBox)panel).setEditable(activo);
        else if(panel instanceof JTextComponent){
            ((JTextComponent)panel).setEditable(activo);
            ((JTextComponent)panel).setBackground(new Color(237,237,237));
        }
        else if(panel instanceof JButtonFunction)
            ((JButtonFunction)panel).setVisible(activo);
        else if(panel instanceof Container){
            Component[] lst = ((Container)panel).getComponents();
            for(Component component : lst)
                deshabilitar(component, activo);
        }
    }
    
    private void this_windowClosing(WindowEvent e) {
        //FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    
    private void cerrarVentana(boolean pAceptar) {
        boolean continua = true;
        if(!pAceptar){
            if(isGraboReceta() && !isImprimioReceta()){
                String mensaje = "Consulta Médica:\n"+
                                 "No se completo la impresión de la receta médica\n"+
                                 "¿Desea Cerrar?";
                if(JConfirmDialog.rptaConfirmDialog(this,mensaje))
                    continua = true;
            }
        }
        if(continua){
            FarmaVariables.vAceptar = pAceptar;
            beanAtencionMedica = null;
            this.setVisible(false);
            this.dispose();
        }
    }

    private void mostrarTabPanel() {
        switch(tabpContenedor.getSelectedIndex()){
            case 0: FarmaUtility.moveFocus(txtMotivoConsulta);
                break;
            case 1: FarmaUtility.moveFocus(txtPA1);
                break;
            case 2: FarmaUtility.moveFocus(txtCodDiagnostico);
                break;
            case 3: FarmaUtility.moveFocus(txtProductoTratamiento);
                break;
            case 4: FarmaUtility.moveFocus(txtExaAuxLaborotarios);
                break;
            case 5: FarmaUtility.moveFocus(txtProcedimiento);
                break;
        }
    }
    
    private void cargaCombo(int codCombo, JComboWithCheck combo){
        ArrayList<OptionComboBox> lista = (new UtilityAtencionMedica()).obtenerListaComboCheckBox(codCombo);
        Map<Object, Boolean> optionsCombo = new LinkedHashMap<Object, Boolean>();
        for(int i=0;i<lista.size();i++){
            OptionComboBox option = lista.get(i);
            optionsCombo.put(option, option.isSeleccionado());
        }
        combo.addItems(optionsCombo);
    }
    
    private void cargarCombos(){
        cargaCombo(40, cmbTipoInformante);
        cargaCombo(39, cmbApetito);
        cargaCombo(39, cmbSed);
        cargaCombo(39, cmbSuenio);
        cargaCombo(39, cmbOrina);
        cargaCombo(39, cmbDeposiciones);
        cargaCombo(38, cmbEstadoGeneral);
        cargaCombo(37, cmbTipoDiagnostico);
        cargaCombo(36, cmbViaAdministracion);

        if(isAtencionNueva()){
            cmbApetito.selectItem(1);
            cmbSed.selectItem(1);
            cmbSuenio.selectItem(1);
            cmbOrina.selectItem(1);
            cmbDeposiciones.selectItem(1);
        }
    }
    
    private void crearTablas(){
        FarmaColumnData[] colTblDiagnostico = { new FarmaColumnData("Codigo", 80, JLabel.LEFT),            //0
                                                  new FarmaColumnData("Descripción", 440, JLabel.LEFT),     //1
                                                  new FarmaColumnData("Tipo", 135, JLabel.LEFT),            //2
                                                  new FarmaColumnData("ID_TIPO", 0, JLabel.CENTER),         //3
                                                  new FarmaColumnData("cod_diagnostico", 0, JLabel.CENTER)  //4
                                                  };
        
        mdlTblDiagnostico = new FarmaTableModel(colTblDiagnostico, UtilityPtoVenta.obtenerDefaultValuesTabla(colTblDiagnostico.length),0);
        FarmaUtility.initSimpleList(tblListaDiagnostico, mdlTblDiagnostico, colTblDiagnostico);
        //690 675
        FarmaColumnData[] colTblReceta = { new FarmaColumnData("Cod.", 50, JLabel.CENTER),              //0
                                           new FarmaColumnData("Producto", 250, JLabel.LEFT),           //1
                                           new FarmaColumnData("Tratamiento", 150, JLabel.CENTER),      //2
                                           new FarmaColumnData("Presentación", 0, JLabel.CENTER),       //3
                                           new FarmaColumnData("Frec/día", 0, JLabel.LEFT),             //4
                                           new FarmaColumnData("Tiempo", 0, JLabel.CENTER),             //5
                                           new FarmaColumnData("Via Adm.", 120, JLabel.CENTER),         //6
                                           new FarmaColumnData("Dosis", 100, JLabel.CENTER),            //7
                                           new FarmaColumnData("Cantidad", 0, JLabel.CENTER),           //8
                                           new FarmaColumnData("ValFrac", 0, JLabel.CENTER),            //9
                                           new FarmaColumnData("ID_VIA_ADMINISTRACION", 0, JLabel.CENTER) //10
                                        };
        mdlTblReceta = new FarmaTableModel(colTblReceta, UtilityPtoVenta.obtenerDefaultValuesTabla(colTblReceta.length),0);
        FarmaUtility.initSimpleList(tblListaReceta, mdlTblReceta, colTblReceta);
    }
    
    private void ubicarDiagnostico(boolean isAbajo){
        int selec = tblListaDiagnostico.getSelectedRow();
        int total = tblListaDiagnostico.getRowCount();
        if(total>0){
            if(selec==-1){
                if(isAbajo)
                    tblListaDiagnostico.setRowSelectionInterval(0, 0);
                else
                    tblListaDiagnostico.setRowSelectionInterval(total-1, total-1);
            }else{
                if(isAbajo){
                    selec = selec+1;
                    if(selec>=total){
                        selec = 0;
                    }
                }else{
                    selec = selec-1;
                    if(selec<0){
                        selec = total-1;
                    }
                }
                tblListaDiagnostico.setRowSelectionInterval(selec, selec);
            }
        }
    }
    
    private void mostrarDiagnostico(KeyEvent e){
        //ArrayList lst = utility.obtenerListaDiagnostico();
        if(lstDiagnostico == null || (lstDiagnostico!=null && lstDiagnostico.size()==0)){
            FarmaUtility.showMessage(this, "Consulta Médica:\n"+
                                           "Lista de Diagnósticos se esta cargando, reintente.", txtCodDiagnostico);
            return;
        }
        DlgListadoCM dlgListado = new DlgListadoCM(myParentFrame, "Lista", true, false, lstDiagnostico);
        dlgListado.setVisible(true);
        if(FarmaVariables.vAceptar){
            FarmaVariables.vAceptar = false;
            ArrayList lstResultado = dlgListado.getLstResultado();
            if(!lstResultado.isEmpty()){
                ArrayList fila = (ArrayList)lstResultado.get(0);
                txtCodDiagnostico.setText((String)fila.get(0));
                txtDescDiagnostico.setText((String)fila.get(1));
                codDiagnosticoAux = (String)fila.get(2);
                FarmaUtility.moveFocus(cmbTipoDiagnostico);
                txtCodDiagnostico.setEditable(false);
                cmbTipoDiagnostico.selectItem(2);
            }
        }
        
    }
    
    private void agregarDiagnostico(){
        if(cmbTipoDiagnostico.getElementosSeleccionados().size()==0){
            FarmaUtility.showMessage(this, "Diagnóstico: No ha seleccionado Tipo de Diagnóstico", cmbTipoDiagnostico);
            return;
        }
        if(codDiagnosticoAux == null || (codDiagnosticoAux!=null && codDiagnosticoAux.trim().length()==0) ){
            FarmaUtility.showMessage(this, "Diagnóstico: no ha seleccionado un Diagnóstico, valido", cmbTipoDiagnostico);
            return;
        }

        String codigo = (String)cmbTipoDiagnostico.getCodigoElementAt();//FarmaLoadCVL.getCVLCode(cmbTipoDiagnostico.getName(), cmbTipoDiagnostico.getSelectedIndex());
        String descripcion = cmbTipoDiagnostico.getLabelElementAt();//FarmaLoadCVL.getCVLDescription(cmbTipoDiagnostico.getName(), codigo);
        
        ArrayList<String> fila = new ArrayList<String>();
        fila.add(txtCodDiagnostico.getText());
        fila.add(txtDescDiagnostico.getText());
        fila.add(descripcion);
        fila.add(codigo);
        fila.add(codDiagnosticoAux);
        mdlTblDiagnostico.data.add(fila);
        mdlTblDiagnostico.fireTableDataChanged();
        tblListaDiagnostico.repaint();
        codDiagnosticoAux="";
        txtCodDiagnostico.setText("");
        txtDescDiagnostico.setText("");
        txtCodDiagnostico.setEditable(true);
        FarmaUtility.moveFocus(txtCodDiagnostico);
        cmbTipoDiagnostico.selectItem(0);
        
    }
    
    private void quitarDiagnostico(){
        int seleccion = tblListaDiagnostico.getSelectedRow();
        if(seleccion >= 0){
            mdlTblDiagnostico.data.remove(seleccion);
            mdlTblDiagnostico.fireTableDataChanged();
            FarmaUtility.moveFocus(txtCodDiagnostico);
        }else{
            FarmaUtility.showMessage(this, "No ha seleccionado un registro en el Diagnostico", tblListaDiagnostico);
        }
    }
    
    private void mostrarProducto(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(tblMdlListaReceta == null || (tblMdlListaReceta!=null && tblMdlListaReceta.data.size()==0)){
                FarmaUtility.showMessage(this, "Listado de productos para la receta, no se ha cargado aún!!!\n"+
                                               "Reintente por favor, en caso persista comuniquese con Mesa de Ayuda", txtProductoTratamiento);
                return;
            }else{
                DlgListaProductos dlgListaProductos = new DlgListaProductos(myParentFrame, "", true, true, tblMdlListaReceta);
                //dlgListaProductos.setListadoReceta(true);
                dlgListaProductos.setVisible(true);
                /*DlgListadoProductoCM dlgListaProductos = new DlgListadoProductoCM(myParentFrame, "Lista de Productos", true, lstProductosReceta);
                dlgListaProductos.setVisible(true);*/
                if(FarmaVariables.vAceptar){
                    FarmaVariables.vAceptar = false;
                    //String codProd = "";
                    //ArrayList lstDato = (ArrayList)dlgListaProductos.getLstResultado().get(0);
                    String codProd = dlgListaProductos.getCodProdRecetaSelect();
                    ArrayList lstDato = utility.obtenerProductoReceta(codProd);
                    
                    txtProductoTratamiento.setText((String)lstDato.get(1)+" ("+(String)lstDato.get(6)+")");
                    lblSugerido.setText((String)lstDato.get(7));
                    lblUnidadRecetada.setText((String)lstDato.get(2));
                    
                    valMaxFracProdReceta = FarmaUtility.trunc((String)lstDato.get(5));
                    valFracProdReceta = FarmaUtility.trunc((String)lstDato.get(3));
                    codProdReceta = (String)lstDato.get(0);
                    
                    //txtProductoTratamiento.setEditable(false);
                    txtFrecuenciaTratamiento.setEnabled(true);
                    txtDuracionTratamiento.setEnabled(true);
                    cmbViaAdministracion.setEnabled(true);
                    txtCantidadTratamiento.setEnabled(true);
                    txtDosis.setEnabled(true);
                    FarmaUtility.moveFocus(txtFrecuenciaTratamiento);
                    
                }
            }
        }
    }
    
    private void calcularCantidadVtaDosis(){
        String valFrecuencia = txtFrecuenciaTratamiento.getText().trim();
        String valDuracion = txtDuracionTratamiento.getText().trim();
        int frecuenciaDosis, duracionDosis;
        if((valFrecuencia!=null && valFrecuencia.length()>0)&&
            (valDuracion!=null && valDuracion.length()>0)
        ){
            frecuenciaDosis = Integer.parseInt(valFrecuencia);
            duracionDosis = Integer.parseInt(valDuracion);
            int cantidadDosis = frecuenciaDosis * duracionDosis;
            
            BigDecimal bCantidadDosis = new BigDecimal(cantidadDosis);
            BigDecimal bValFrac = new BigDecimal(valFracProdReceta);
            BigDecimal bValMaxFrac = new BigDecimal(valMaxFracProdReceta);
            
            BigDecimal bCantidadFracDeseada = bValFrac.multiply(bCantidadDosis);
            bCantidadFracDeseada = bCantidadFracDeseada.divide(bValMaxFrac, 2, RoundingMode.CEILING);
            //double cantidadFrac = (valFracProdReceta * cantidadDosis)/valMaxFracProdReceta;
            double cantidadFrac = bCantidadFracDeseada.doubleValue();
            if(valMaxFracProdReceta == 1)
                txtCantidadTratamiento.setInt(1);
            else
                txtCantidadTratamiento.setInt((new Double(Math.ceil(cantidadFrac))).intValue());
            
            /*
            if(valMaxFracProdReceta==1)
                txtCantidadTratamiento.setInt(1);
            else
                txtCantidadTratamiento.setInt(cantidadDosis);
            */
        }
    }
    
    private void agregarProductoAReceta(){
        if(cmbViaAdministracion.getElementosSeleccionados().size()==0){
            FarmaUtility.showMessage(this, "Receta: no ha registrado la via de administración del medicamento", cmbViaAdministracion);
            return;
        }
        if(codProdReceta.trim().length()==0){
            FarmaUtility.showMessage(this, "Receta: no ha seleccionado un producto", txtProductoTratamiento);
            return;
        }
        if(txtFrecuenciaTratamiento.getText()==null||(txtFrecuenciaTratamiento.getText()!=null && txtFrecuenciaTratamiento.getText().trim().length()==0)){
            FarmaUtility.showMessage(this, "Receta: no ha registrado la Frecuencia del tratamiento", txtFrecuenciaTratamiento);
            return;
        }
        if(txtDuracionTratamiento.getText()==null||(txtDuracionTratamiento.getText()!=null && txtDuracionTratamiento.getText().trim().length()==0)){
            FarmaUtility.showMessage(this, "Receta: no ha registrado la Duracion del tratamiento", txtDuracionTratamiento);
            return;
        }
        
        if(txtDosis.getText()==null||(txtDosis.getText()!=null && txtDosis.getText().trim().length()==0)){
            FarmaUtility.showMessage(this, "Receta: no ha registrado la Dosis de la toma.", txtDosis);
            return;
        }
        
        if(txtCantidadTratamiento.getText()==null || (txtCantidadTratamiento.getText()!=null && txtCantidadTratamiento.getText().trim().length()==0) || 
            (txtCantidadTratamiento.getText()!=null && txtCantidadTratamiento.getText().trim().length()>0 && FarmaUtility.getDecimalNumber(txtCantidadTratamiento.getText().trim())==0)
        ){
            FarmaUtility.showMessage(this, "Receta: no se ha podido determinar la cantidad del medicamento.", txtCantidadTratamiento);
            return;
        }
        
        
        String codViaAdministracion = (String)cmbViaAdministracion.getCodigoElementAt();
        String descripcion = cmbViaAdministracion.getLabelElementAt();
        String texto = txtFrecuenciaTratamiento.getText()+" veces al día X "+txtDuracionTratamiento.getText()+" días.";
        ArrayList<String> fila = new ArrayList<String>();
        fila.add(codProdReceta);
        fila.add(txtProductoTratamiento.getText());
        fila.add(texto);
        fila.add(lblUnidadRecetada.getText());
        fila.add(txtFrecuenciaTratamiento.getText());
        fila.add(txtDuracionTratamiento.getText());
        fila.add(descripcion);
        fila.add(txtDosis.getText());
        fila.add(txtCantidadTratamiento.getText());
        //fila.add(""+valMaxFracProdReceta);
        fila.add(""+valFracProdReceta);
        fila.add(codViaAdministracion);
        
        log.info("filafila ");
        log.info(fila+"");
               
        mdlTblReceta.data.add(fila);
        mdlTblReceta.fireTableDataChanged();
        
        txtProductoTratamiento.setText("");
        lblUnidadRecetada.setText("");
        lblSugerido.setText("");
        
        txtFrecuenciaTratamiento.setText("");
        txtDuracionTratamiento.setText("");
        cmbViaAdministracion.selectItem(0);
        txtDosis.setText("");
        txtCantidadTratamiento.setText("");
        
        FarmaUtility.moveFocus(txtProductoTratamiento);
        
        txtFrecuenciaTratamiento.setEnabled(false);
        txtDuracionTratamiento.setEnabled(false);
        cmbViaAdministracion.setEnabled(false);
        txtDosis.setEnabled(false);
        txtCantidadTratamiento.setEnabled(false);
        
    }
    
    private void quitarProductoAReceta(){
        int seleccion = tblListaReceta.getSelectedRow();
        if(seleccion >= 0){
            mdlTblReceta.data.remove(seleccion);
            mdlTblReceta.fireTableDataChanged();
            FarmaUtility.moveFocus(txtProductoTratamiento);
        }else{
            FarmaUtility.showMessage(this, "No ha seleccionado un registro de la receta", tblListaReceta);
        }
    }

    public BeanAtencionMedica getBeanAtencionMedica() {
        return beanAtencionMedica;
    }

    public void setBeanAtencionMedica(BeanAtencionMedica atencionMedica) {
        this.beanAtencionMedica = atencionMedica;
    }
    
    private void agregarNombresACampos(){
        txtMotivoConsulta.setName("Motivo de Consulta");
        txtFormaInicio.setName("Forma de Inicio de Enfermedad");
        txtSintomas.setName("Sintomas de Enfermedad");
        txtSignos.setName("Signos de Enfermedad");
        txtTiempoEnfermedad.setName("Tiempo de Enfermedad");
        txtRelatoCronologico.setName("Relato Cronológico");
        cmbTipoInformante.setName("Tipo de Informante");
        cmbApetito.setName("Func.Biológica Apetito");
        cmbSed.setName("Func.Biológica Sed");
        cmbSuenio.setName("Func.Biológica Sueño");
        cmbOrina.setName("Func.Biológica Orina");
        cmbDeposiciones.setName("Func.Biológica Deposiciones");
        
        txtPA1.setName("Presión Arterial");
        txtPA2.setName("Presión Arterial");
        txtFR.setName("Frecuencia Respiratoria");
        txtFC.setName("Frecuencia Cardiaca");
        txtT.setName("Temperatura");
        txtPeso.setName("Peso");
        txtTalla.setName("Talla");
        
        cmbEstadoGeneral.setName("ESTADO GENERAL");
        txtEstadoConciencia.setName("ESTADO DE CONCIENCIA");
        txtFisicoDirigido.setName("EXAMEN FISICO DIRIGIDO");
        
        tblListaDiagnostico.setName("DIAGNOSTICO");
        cmbTipoDiagnostico.setName("TIPO DE DIAGNOSTICO");
        
        cmbViaAdministracion.setName("VIA ADMINISTRACION");
        tblListaReceta.setName("RECETA");
        txtValidezReceta.setName("VALIDEZ DE RECETA");
        txtIndicacionesGeneralesTratamiento.setName("INDICACIONES GENERALES DEL TRATAMIENTO");
    }
    
    private int getValorIntNulo(JNumericField jText){
        try{
            return jText.getInt();
        }catch(Exception ex){
            return Integer.MIN_VALUE;
        }
    }
    
    private double getValorDoubleNulo(JNumericField jText){
        try{
            return jText.getDouble();
        }catch(Exception ex){
            return -99999.99;
        }
    }
    
    private boolean guardarPanelEnfermedadActual(boolean grabaTemporal){
        tabpContenedor.setSelectedIndex(0);
        if(!grabaTemporal){
            if(valorCampoEsNulo(txtMotivoConsulta)) return false;
            if(valorCampoEsNulo(txtFormaInicio)) return false;
            //if(valorCampoEsNulo(txtSintomas)) return false;
            if(valorCampoEsNulo(txtSignos)) return false;
            if(valorCampoEsNulo(txtTiempoEnfermedad)) return false;
            if(valorCampoEsNulo(cmbTipoInformante)) return false;
            if(valorCampoEsNulo(txtRelatoCronologico)) return false;
            if(valorCampoEsNulo(cmbApetito)) return false;
            if(valorCampoEsNulo(cmbSed)) return false;
            if(valorCampoEsNulo(cmbSuenio)) return false;
            if(valorCampoEsNulo(cmbOrina)) return false;
            if(valorCampoEsNulo(cmbDeposiciones)) return false;
           
            /*if(getValorIntNulo(txtTiempoEnfermedad) == Integer.MIN_VALUE){
                FarmaUtility.showMessage(this, "Consulta Médica: \nValor del campo Tiempo de Enfermedad debe ser mayor o igual a 0 (cero). verifique!!!", txtTiempoEnfermedad);
            }*/
        }
        
        String codGrupoCia = beanAtencionMedica.getCodGrupoCia();
        String codCia = beanAtencionMedica.getCodCia();
        String codLocal = beanAtencionMedica.getCodLocal();
        String nroAtencion = beanAtencionMedica.getNroAtencionMedica();
        
        BeanAtMedEnfermedadActual enfActual = new BeanAtMedEnfermedadActual();
        enfActual.setCodGrupoCia(codGrupoCia);
        enfActual.setCodCia(codCia);
        enfActual.setCodLocal(codLocal);
        enfActual.setNroAtencionMedica(nroAtencion);
        enfActual.setMotivoConsulta(getValorTextoNulo(txtMotivoConsulta));
        enfActual.setFormaInicio(getValorTextoNulo(txtFormaInicio));
        enfActual.setSignos(getValorTextoNulo(txtSignos));
        enfActual.setSintomas(getValorTextoNulo(txtSintomas));
        enfActual.setTiempoEnfermedad(getValorTextoNulo(txtTiempoEnfermedad));
        enfActual.setTipoInformante(getValorTextoNulo(cmbTipoInformante.getCodigoElementAt()));
        enfActual.setRelatoCronologico(getValorTextoNulo(txtRelatoCronologico));
        enfActual.setTipoApetito(getValorTextoNulo(cmbApetito.getCodigoElementAt()));
        enfActual.setTipoSed(getValorTextoNulo(cmbSed.getCodigoElementAt()));
        enfActual.setTipoSuenio(getValorTextoNulo(cmbSuenio.getCodigoElementAt()));
        enfActual.setTipoOrina(getValorTextoNulo(cmbOrina.getCodigoElementAt()));
        enfActual.setTipoDeposicion(getValorTextoNulo(cmbDeposiciones.getCodigoElementAt()));
        beanAtencionMedica.setEnfermedadActual(enfActual);
        return true;
    }
    
    private boolean guardarPanelTriaje(boolean grabaTemporal){
        tabpContenedor.setSelectedIndex(1);
        /*if(!grabaTemporal){
            if(valorCampoEsNulo(txtPA1)) return false;
            if(valorCampoEsNulo(txtPA2)) return false;
            if(valorCampoEsNulo(txtFR)) return false;
            if(valorCampoEsNulo(txtFC)) return false;
            if(valorCampoEsNulo(txtT)) return false;
            if(valorCampoEsNulo(txtPeso)) return false;
            if(valorCampoEsNulo(txtTalla)) return false;
        }*/
        String codGrupoCia = beanAtencionMedica.getCodGrupoCia();
        String codCia = beanAtencionMedica.getCodCia();
        String codLocal = beanAtencionMedica.getCodLocal();
        String nroAtencion = beanAtencionMedica.getNroAtencionMedica();
        BeanAtMedTriaje triaje = null;
        if(getValorIntNulo(txtPA1) == Integer.MIN_VALUE && getValorIntNulo(txtPA2) == Integer.MIN_VALUE && 
           getValorIntNulo(txtFR) == Integer.MIN_VALUE && getValorIntNulo(txtFC) == Integer.MIN_VALUE && 
           getValorDoubleNulo(txtT) == -99999.99 && getValorDoubleNulo(txtPeso) == -99999.99 &&  
           getValorDoubleNulo(txtTalla) == -99999.99){

            triaje = new BeanAtMedTriaje();
            triaje.setCodGrupoCia(codGrupoCia);
            triaje.setCodCia(codCia);
            triaje.setCodLocal(codLocal);
            triaje.setNroAtencionMedica(nroAtencion);
            triaje.setFuncionVitalPA1(getValorIntNulo(txtPA1));
            triaje.setFuncionVitalPA2(getValorIntNulo(txtPA2));
            triaje.setFuncionVitalFR(getValorIntNulo(txtFR));
            triaje.setFuncionVitalFC(getValorIntNulo(txtFC));
            triaje.setFuncionVitalT(getValorDoubleNulo(txtT));
            triaje.setFuncionVitalPeso(getValorDoubleNulo(txtPeso));
            triaje.setFuncionvitalTalla(getValorDoubleNulo(txtTalla));
        }
        beanAtencionMedica.setTriaje(triaje);
        return true;
    }
    
    private boolean guardarPanelExamenFisico(boolean grabaTemporal){
        tabpContenedor.setSelectedIndex(1);
        if(!grabaTemporal){
            if(valorCampoEsNulo(cmbEstadoGeneral)) return false;
            if(valorCampoEsNulo(txtEstadoConciencia)) return false;
            if(valorCampoEsNulo(txtFisicoDirigido)) return false;
        }
        String codGrupoCia = beanAtencionMedica.getCodGrupoCia();
        String codCia = beanAtencionMedica.getCodCia();
        String codLocal = beanAtencionMedica.getCodLocal();
        String nroAtencion = beanAtencionMedica.getNroAtencionMedica();
        
        BeanAtMedExamenFisico exaFisico = new BeanAtMedExamenFisico();
        exaFisico.setCodGrupoCia(codGrupoCia);
        exaFisico.setCodCia(codCia);
        exaFisico.setCodLocal(codLocal);
        exaFisico.setNroAtencionMedica(nroAtencion);
        exaFisico.setEstadoGeneral(getValorTextoNulo(cmbEstadoGeneral.getCodigoElementAt()));
        exaFisico.setEstadoConciencia(getValorTextoNulo(txtEstadoConciencia));
        exaFisico.setExamenFisicoDirigido(getValorTextoNulo(txtFisicoDirigido));
        
        beanAtencionMedica.setExamenFisico(exaFisico);
        return true;
    }
    
    private boolean guardarPanelDiagnostico(boolean grabaTemporal){
        tabpContenedor.setSelectedIndex(2);
        if(!grabaTemporal)
            if(valorCampoEsNulo(tblListaDiagnostico)) return false;
        
        String codGrupoCia = beanAtencionMedica.getCodGrupoCia();
        String codCia = beanAtencionMedica.getCodCia();
        String codLocal = beanAtencionMedica.getCodLocal();
        String nroAtencion = beanAtencionMedica.getNroAtencionMedica();
        
        ArrayList<BeanAtMedDiagnostico> lstDiagnosticos = new ArrayList<BeanAtMedDiagnostico>();
        for(int i=0; i <tblListaDiagnostico.getRowCount(); i++){
            ArrayList fila = (ArrayList)mdlTblDiagnostico.data.get(i);
            BeanAtMedDiagnostico diagnostico = new BeanAtMedDiagnostico();
            diagnostico.setCodGrupoCia(codGrupoCia);
            diagnostico.setCodCia(codCia);
            diagnostico.setCodLocal(codLocal);
            diagnostico.setNroAtencionMedica(nroAtencion);
            diagnostico.setSecuencial(i+1);
            diagnostico.setCodDiagnostico((String)fila.get(4));
            diagnostico.setTipoDiagnostico((String)fila.get(3));
            lstDiagnosticos.add(diagnostico);
        }
        beanAtencionMedica.setDiagnostico(lstDiagnosticos);
        return true;
    }
    
    private boolean guardarPanelTratamiento(boolean grabaTemporal){
        tabpContenedor.setSelectedIndex(3);
        boolean rspta = false;
        BeanAtMedTratamiento tratamiento = null;
        FarmaUtility.moveFocus(txtProductoTratamiento);
        if(!grabaTemporal){
            if(tblListaReceta.getRowCount()==0)
                if(!JConfirmDialog.rptaConfirmDialog(this,"No ha registrado tratamiento\n" +"¿Está seguro de Guardar?"))
                    return false;
                else {
                    rspta = true;
                    setImprimioReceta(true);
                }
            else
                rspta = true;
        }else{
            rspta = !(tblListaReceta.getRowCount()==0);
        }
        
        if(rspta){
            //lstTratamiento = new BeanAtMedTratamiento();
            tabpContenedor.setSelectedIndex(3);
            
            //if(valorCampoEsNulo(tblListaReceta)) return false;
            
            if(valorCampoEsNulo(txtValidezReceta)) return false;
            //if(campoVacio(txtIndicacionesGeneralesTratamiento)) return;
            
            String fechaValidez = txtValidezReceta.getText();
            if(!validarFecha(fechaValidez)){
                FarmaUtility.showMessage(this, "Fecha de Validez invalida", txtValidezReceta);
                return false;
            }
            String indicaciones = txtIndicacionesGeneralesTratamiento.getText();
            
            String codGrupoCia = beanAtencionMedica.getCodGrupoCia();
            String codCia = beanAtencionMedica.getCodCia();
            String codLocal = beanAtencionMedica.getCodLocal();
            String nroAtencion = beanAtencionMedica.getNroAtencionMedica();
            //BeanAtMedTratamiento 
            tratamiento = new BeanAtMedTratamiento();
            tratamiento.setCodGrupoCia(codGrupoCia);
            tratamiento.setCodCia(codCia);
            tratamiento.setCodLocal(codLocal);
            tratamiento.setNroAtencionMedica(nroAtencion);
            tratamiento.setValidezReceta(fechaValidez);
            tratamiento.setIndicacionesGenerales(indicaciones);
            
            BeanAtMedTrataReceta receta = new BeanAtMedTrataReceta();
            receta.setCodGrupoCia(codGrupoCia);
            receta.setCodLocal(codLocal);
            receta.setNroPedidoReceta("");
            receta.setCantidadItems(tblListaReceta.getRowCount());
            ArrayList<BeanAtMedTrataRecetaDetalle> lstDetalle = new ArrayList<BeanAtMedTrataRecetaDetalle>();
            for(int i=0; i< tblListaReceta.getRowCount(); i++){
                ArrayList fila = (ArrayList)mdlTblReceta.data.get(i);
                BeanAtMedTrataRecetaDetalle detalle = new BeanAtMedTrataRecetaDetalle();
                detalle.setCodGrupoCia(codGrupoCia);
                detalle.setCodLocal(codLocal);
                detalle.setNroPedidoReceta("");
                detalle.setSecuencialDetalle(i+1);
                detalle.setCodProducto((String)fila.get(0));
                detalle.setCantidadToma(FarmaUtility.trunc((String)fila.get(8)));
                detalle.setValFraccionamiento(FarmaUtility.trunc((String)fila.get(9)));
                detalle.setUnidadVenta((String)fila.get(3));
                detalle.setFrecuenciaToma(FarmaUtility.trunc((String)fila.get(4)));
                detalle.setDuracionToma(FarmaUtility.trunc((String)fila.get(5)));
                detalle.setCodViaAdministracion(FarmaUtility.trunc((String)fila.get(10)));
                detalle.setDosis((String)fila.get(7));
                lstDetalle.add(detalle);
            }
            receta.setDetalles(lstDetalle);
            tratamiento.setReceta(receta);
        }
        beanAtencionMedica.setTratamiento(tratamiento);
        return true;
    }
    
    private boolean guardarPanelExaAuxiliares(boolean grabaTemporal){
        tabpContenedor.setSelectedIndex(4);
        BeanAtMedExamenesAuxiliares exaAuxi = null;
        if( !valorCampoEsNulo(txtExaAuxLaborotarios, false) || !valorCampoEsNulo(txtExaAuxLaborotarios, false) ){
            String codGrupoCia = beanAtencionMedica.getCodGrupoCia();
            String codCia = beanAtencionMedica.getCodCia();
            String codLocal = beanAtencionMedica.getCodLocal();
            String nroAtencion = beanAtencionMedica.getNroAtencionMedica();
            
            exaAuxi = new BeanAtMedExamenesAuxiliares();
            exaAuxi.setCodGrupoCia(codGrupoCia);
            exaAuxi.setCodCia(codCia);
            exaAuxi.setCodLocal(codLocal);
            exaAuxi.setNroAtencionMedica(nroAtencion);
            exaAuxi.setLaboratorio(getValorTextoNulo(txtExaAuxLaborotarios));
            exaAuxi.setImagenes(getValorTextoNulo(txtExaAuxImagenologicos));
        }
        beanAtencionMedica.setExamenesAuxiliares(exaAuxi);
        return true;
    }
    
    private boolean guardarPanelOtros(boolean grabaTemporal){
        tabpContenedor.setSelectedIndex(5);
        BeanAtMedOtros otros = null;
        if( !valorCampoEsNulo(txtProcedimiento, false) || 
            !valorCampoEsNulo(txtInterconsulta, false) || 
            !valorCampoEsNulo(txtTransferencia, false) ||
            !valorCampoEsNulo(txtDescansoMedicoInicio, false) ||
            !valorCampoEsNulo(txtDescansoMedicoFin, false) ||
            !valorCampoEsNulo(txtProximaCita, false)
        ){
            String codGrupoCia = beanAtencionMedica.getCodGrupoCia();
            String codCia = beanAtencionMedica.getCodCia();
            String codLocal = beanAtencionMedica.getCodLocal();
            String nroAtencion = beanAtencionMedica.getNroAtencionMedica();
            
            otros = new BeanAtMedOtros();
            otros.setCodGrupoCia(codGrupoCia);
            otros.setCodCia(codCia);
            otros.setCodLocal(codLocal);
            otros.setNroAtencionMedica(nroAtencion);
            otros.setProcedimiento(getValorTextoNulo(txtProcedimiento));
            otros.setInterconsulta(getValorTextoNulo(txtInterconsulta));
            otros.setTransferencia(getValorTextoNulo(txtTransferencia));
            String iniDescanso = getValorTextoNulo(txtDescansoMedicoInicio);
            String finDescanso = getValorTextoNulo(txtDescansoMedicoFin);
            boolean conFechaIniDescanso = true;
            boolean conFechaFinDescanso = true;
            if(iniDescanso == null || (iniDescanso!=null && iniDescanso.trim().length()==0))
                conFechaIniDescanso = false;
            if(finDescanso == null || (finDescanso!=null && finDescanso.trim().length()==0))
                conFechaFinDescanso = false;
            
            if(!(conFechaFinDescanso || conFechaIniDescanso)){
                iniDescanso = "";
                finDescanso = "";
            }else{
                if( !(conFechaFinDescanso && conFechaIniDescanso) ){
                    FarmaUtility.showMessage(this, "Consulta Médica: favor de validar las fechas de descanso medico, campos vacios", txtDescansoMedicoInicio);
                    return false;
                }else{
                    if(!validarFecha(iniDescanso)){
                        FarmaUtility.showMessage(this, "Consulta Médica: Formato de Fecha de Inicio de descanso invalido", txtDescansoMedicoInicio);
                        return false;
                    }
                    if(!validarFecha(finDescanso)){
                        FarmaUtility.showMessage(this, "Consulta Médica: Formato de Fecha de Fin de descanso invalido", txtDescansoMedicoFin);
                        return false;
                    }
                    Date fechaIniDescanso = obtenerFecha(iniDescanso);
                    Date fechaFinDescanso = obtenerFecha(finDescanso);
                    if(fechaIniDescanso.compareTo(fechaFinDescanso) == 1){
                        FarmaUtility.showMessage(this, "Consulta Médica: Fecha de Descanso Medico fin no puede ser menor ala fecha de inicio ", txtDescansoMedicoFin);
                        return false;
                    }
                }
            }
            otros.setDescansoMedicoInicio(iniDescanso);
            otros.setDescansoMedicoFin(finDescanso);
            
            String fechProxCita = getValorTextoNulo(txtProximaCita);
            boolean conFechaProxCita = true;
            if(fechProxCita == null || (fechProxCita!=null && fechProxCita.trim().length()==0))
                conFechaProxCita = false;
            if(conFechaProxCita){
                if(!validarFecha(fechProxCita)){
                    FarmaUtility.showMessage(this, "Consulta Médica: Formato de Fecha de Proxima Cita invalido!!!", txtProximaCita);
                    return false;
                }else{
                    Date fechaProximaCita = obtenerFecha(fechProxCita);
                    Date fechaActual = Calendar.getInstance().getTime();
                    if(fechaProximaCita.compareTo(fechaActual)<1){
                        FarmaUtility.showMessage(this, "Consulta Médica: Fecha de Proxima Cita tiene que ser mayor a la fecha actual!!!", txtProximaCita);
                        return false;
                    }
                }
            }else{
                fechProxCita = "";
            }
            otros.setProximaCita(fechProxCita);
        }
        beanAtencionMedica.setOtros(otros);
        return true;
    }
    
    private void grabarConsulta(){
        if(isModoVisual()){
            return;
        }
        //int rspta = JConfirmDialog.showOptionDialog(this, "¿Qué acción desea realizar?","Grabar Temporalmente","Finalizar Atencion");
        int rspta = JConfirmDialog.showOptionDialog(this, "¿Finalizo la atención de la Consulta Médica?","Sí, Genera Receta","No, Graba Temporalmente");
        boolean grabaTemporal = false;
        switch(rspta){
            case JOptionPane.YES_OPTION :
                grabaTemporal = false;
                break;
            case JOptionPane.NO_OPTION :
                grabaTemporal = true;
                break;
            case JOptionPane.CLOSED_OPTION: 
                return;
        }
        
        if(!isGraboReceta()){
            boolean guardoPanel = true;
            if(guardoPanel) guardoPanel = guardoPanel && guardarPanelEnfermedadActual(grabaTemporal);
            if(guardoPanel) guardoPanel = guardoPanel && guardarPanelTriaje(grabaTemporal);
            if(guardoPanel) guardoPanel = guardoPanel && guardarPanelExamenFisico(grabaTemporal);
            if(guardoPanel) guardoPanel = guardoPanel && guardarPanelDiagnostico(grabaTemporal);
            if(guardoPanel) guardoPanel = guardoPanel && guardarPanelTratamiento(grabaTemporal);
            if(guardoPanel) guardoPanel = guardoPanel && guardarPanelExaAuxiliares(grabaTemporal);
            if(guardoPanel) guardoPanel = guardoPanel && guardarPanelOtros(grabaTemporal);
            if(guardoPanel){
                setGraboReceta(utility.grabarConsulta(this, beanAtencionMedica, grabaTemporal));
                if(isGraboReceta())
                    FarmaUtility.showMessage(this, "Consulta Grabada con éxito", null);
            }
        }
        
        if(isGraboReceta() && !grabaTemporal){
            if(!isImprimioReceta()){
                setImprimioReceta(imprimirReceta());
            }
            tabpContenedor.setSelectedIndex(0);
            FarmaUtility.moveFocus(txtMotivoConsulta);
            
            if(beanAtencionMedica.getTratamiento().getReceta().getDetalles().size()>0){
                if(isImprimioReceta()){
                    FarmaUtility.showMessage(this, "Receta impresa con exito.", null);
                    cerrarVentana(true);
                }    
            }
            else
                cerrarVentana(true);
                
        }else{
            if(grabaTemporal) cerrarVentana(true);
        }
        
        
    }
    
    private boolean imprimirReceta(){
        if(beanAtencionMedica.getTratamiento().getReceta().getDetalles().size()>0)
            return utility.imprimirRecetaMedica(this, beanAtencionMedica.getNroAtencionMedica());
        return true;
    }
    
    private String getValorTextoNulo(JTextComponent jText){
        if(jText.getText() == null)
            jText.setText("");
        return jText.getText().trim();
    }
    
    private String getValorTextoNulo(Object text){
        if(text == null)
            text = "";
        return (String)text;
    }
    
    private Date obtenerFecha(String fecha){
        String aux = fecha;
        int numDia, numMes, numAnio;
        numDia = numMes = numAnio =0;
        String dia = aux.substring(0,2);
        String mes = aux.substring(3,5);
        String anio = aux.substring(6);
        try{
            numDia = Integer.parseInt(dia);
            numMes = Integer.parseInt(mes);
            numAnio = Integer.parseInt(anio);
        }catch(Exception e){
            return null;
        }
        
        Calendar date = Calendar.getInstance();
        date.set(numAnio, (numMes-1), numDia);
        return date.getTime();
    }
    
    private boolean validarFecha(String fecha){
        boolean valido = true;
        if(fecha.trim().length()!=10)
            valido = false;
        else{
            String aux = fecha;
            int numDia, numMes, numAnio;
            numDia = numMes = numAnio =0;
            String dia = aux.substring(0,2);
            String mes = aux.substring(3,5);
            String anio = aux.substring(6);
            try{
                numDia = Integer.parseInt(dia);
                numMes = Integer.parseInt(mes);
                numAnio = Integer.parseInt(anio);
            }catch(Exception e){
                valido = false;
            }
            if(valido){
                Calendar date = Calendar.getInstance();
                date.set(numAnio, (numMes-1), numDia);
                Calendar dateActual = Calendar.getInstance();
                if(date.getTime().compareTo(dateActual.getTime()) == -1 )
                    valido = false;
            }
        }
        return valido;
    }
    
    private boolean valorCampoEsNulo(Component component){
        return valorCampoEsNulo(component, true);
    }
    
    private boolean valorCampoEsNulo(Component component, boolean mostrarMensaje){
        boolean vacio = false;
        if(component instanceof JTextComponent){
            String texto = ((JTextComponent)component).getText();
            if(texto == null || (texto != null && texto.trim().length() == 0)){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(this, "Consulta Médica:\nCampo "+component.getName()+" vació.\nVerifique!!!", component);
            }
        }else if(component instanceof JTable){
            if(((JTable)component).getRowCount() == 0){
                vacio = true;
                if(mostrarMensaje&&component.equals(tblListaDiagnostico)){
                    FarmaUtility.showMessage(this, "Consulta Médica:\nNo ha agregado registro a la lista de "+component.getName()+".\nVerifique!!!", component);
                    if(component.equals(tblListaDiagnostico)){
                        FarmaUtility.moveFocus(txtCodDiagnostico);
                    }
                    //no dejar a nadie agregar
                    /*else if(component.equals(tblListaReceta)){
                        FarmaUtility.moveFocus(txtProductoTratamiento);
                    }*/
                    //no dejar a nadie agregar
                }
            }
        }else if(component instanceof JComboWithCheck){
            if(((JComboWithCheck)component).getElementosSeleccionados().size() == 0){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(this, "Consulta Médica:\nNo ha selecciodo un item en el campo "+component.getName()+".\nverifique!!!", component);
            }
        }else if(component instanceof JComboBox){
            if(((JComboBox)component).getSelectedIndex() < 1){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(this, "Consulta Médica:\nNo ha selecciodo un item en el campo "+component.getName()+".\nverifique!!!", component);
            }
        
        }
        
        return vacio;
    }
    
    private void mostrarDatosConsulta(){
        
        BeanAtMedTriaje triaje = beanAtencionMedica.getTriaje();
        if(triaje!=null){
            mostrarCampoInt(txtPA1, triaje.getFuncionVitalPA1());
            mostrarCampoInt(txtPA2, triaje.getFuncionVitalPA2());
            mostrarCampoInt(txtFR, triaje.getFuncionVitalFR());
            mostrarCampoInt(txtFC, triaje.getFuncionVitalFC());
            mostrarCampoDouble(txtT, triaje.getFuncionVitalT());
            mostrarCampoDouble(txtPeso, triaje.getFuncionVitalPeso());
            mostrarCampoDouble(txtTalla, triaje.getFuncionvitalTalla());
        }
        
        if(!isAtencionNueva()){
            BeanAtMedEnfermedadActual enfermedadActual = beanAtencionMedica.getEnfermedadActual();
            if(enfermedadActual!=null){
                txtMotivoConsulta.setText(getValorTextoNulo(enfermedadActual.getMotivoConsulta()));
                txtFormaInicio.setText(getValorTextoNulo(enfermedadActual.getFormaInicio()));
                txtSignos.setText(getValorTextoNulo(enfermedadActual.getSignos()));
                txtSintomas.setText(getValorTextoNulo(enfermedadActual.getSintomas()));
                txtTiempoEnfermedad.setText(enfermedadActual.getTiempoEnfermedad());
                cmbTipoInformante.selectItemIdentificador(getValorTextoNulo(enfermedadActual.getTipoInformante()));
                txtRelatoCronologico.setText(getValorTextoNulo(enfermedadActual.getRelatoCronologico()));
                cmbApetito.selectItemIdentificador(getValorTextoNulo(enfermedadActual.getTipoApetito()));
                cmbSed.selectItemIdentificador(getValorTextoNulo(enfermedadActual.getTipoSed()));
                cmbSuenio.selectItemIdentificador(getValorTextoNulo(enfermedadActual.getTipoSuenio()));
                cmbOrina.selectItemIdentificador(getValorTextoNulo(enfermedadActual.getTipoOrina()));
                cmbDeposiciones.selectItemIdentificador(getValorTextoNulo(enfermedadActual.getTipoDeposicion()));
            }
        
            BeanAtMedExamenFisico exaFisico = beanAtencionMedica.getExamenFisico();
            if(exaFisico!=null){
                //FarmaLoadCVL.setSelectedValueInComboBox(cmbEstadoGeneral, cmbEstadoGeneral.getName(), getValorTextoNulo(exaFisico.getEstadoGeneral()));
                cmbEstadoGeneral.selectItemIdentificador(getValorTextoNulo(exaFisico.getEstadoGeneral()));
                txtEstadoConciencia.setText(getValorTextoNulo(exaFisico.getEstadoConciencia()));
                txtFisicoDirigido.setText(getValorTextoNulo(exaFisico.getExamenFisicoDirigido()));
            }
            
            ArrayList<BeanAtMedDiagnostico> lstDiagnosticos = beanAtencionMedica.getDiagnostico();
            if(lstDiagnosticos!=null){
                for(int i=0; i<lstDiagnosticos.size();i++){
                    BeanAtMedDiagnostico diagnostico = lstDiagnosticos.get(i);
                    if(diagnostico.getSecuencial()>0){
                        ArrayList<String> fila = new ArrayList<String>();
                        fila.add(diagnostico.getCodCIE());
                        fila.add(diagnostico.getDescripcionDiagnostico());
                        fila.add(diagnostico.getDescTipoDiagnostico());
                        fila.add(diagnostico.getTipoDiagnostico());
                        fila.add(diagnostico.getCodDiagnostico());
                        mdlTblDiagnostico.data.add(fila);
                    }
                }
                mdlTblDiagnostico.fireTableDataChanged();
            }
            
            BeanAtMedTratamiento tratamiento = beanAtencionMedica.getTratamiento();
            if(tratamiento != null){
                BeanAtMedTrataReceta receta = tratamiento.getReceta();
                if(receta.getCantidadItems() > 0){
                    ArrayList<BeanAtMedTrataRecetaDetalle> lstDetalleReceta = receta.getDetalles();
                    for(int i=0; i<lstDetalleReceta.size(); i++){
                        BeanAtMedTrataRecetaDetalle detalle = lstDetalleReceta.get(i);
                        if(detalle.getSecuencialDetalle()>0){
                            codProdReceta = getValorTextoNulo(detalle.getCodProducto());
                            //valMaxFracProdReceta = detalle.getValFraccionamiento();
                            valFracProdReceta = detalle.getValFraccionamiento();
                            txtProductoTratamiento.setText(getValorTextoNulo(detalle.getDescProducto()));
                            lblSugerido.setText(getValorTextoNulo(detalle.getUnidadVenta()));
                            lblUnidadRecetada.setText(getValorTextoNulo(detalle.getUnidadVentaFraccion()));
                            txtFrecuenciaTratamiento.setInt(detalle.getFrecuenciaToma());
                            txtDuracionTratamiento.setInt(detalle.getDuracionToma());
                            //FarmaLoadCVL.setSelectedValueInComboBox(cmbViaAdministracion, cmbViaAdministracion.getName(), (detalle.getCodViaAdministracion()+""));
                            cmbViaAdministracion.selectItemIdentificador(detalle.getCodViaAdministracion());
                            txtDosis.setText(getValorTextoNulo(detalle.getDosis()));
                            txtCantidadTratamiento.setInt(detalle.getCantidadToma());
                            agregarProductoAReceta();
                        }
                    }
                    txtValidezReceta.setText(getValorTextoNulo(tratamiento.getValidezReceta()));
                    txtIndicacionesGeneralesTratamiento.setText(getValorTextoNulo(tratamiento.getIndicacionesGenerales()));
                }
            }
            
            BeanAtMedExamenesAuxiliares exaAuxiliares = beanAtencionMedica.getExamenesAuxiliares();
            if(exaAuxiliares!=null){
                txtExaAuxLaborotarios.setText(getValorTextoNulo(exaAuxiliares.getLaboratorio()));
                txtExaAuxImagenologicos.setText(getValorTextoNulo(exaAuxiliares.getImagenes()));
            }
            
            BeanAtMedOtros otros = beanAtencionMedica.getOtros();
            if(otros!=null){
                txtProcedimiento.setText(getValorTextoNulo(otros.getProcedimiento()));
                txtInterconsulta.setText(getValorTextoNulo(otros.getInterconsulta()));
                txtTransferencia.setText(getValorTextoNulo(otros.getTransferencia()));
                txtDescansoMedicoInicio.setText(getValorTextoNulo(otros.getDescansoMedicoInicio()));
                txtDescansoMedicoFin.setText(getValorTextoNulo(otros.getDescansoMedicoFin()));
                txtProximaCita.setText(getValorTextoNulo(otros.getProximaCita()));
            }
        }
    }

    public boolean isGraboReceta() {
        return graboReceta;
    }

    public void setGraboReceta(boolean graboReceta) {
        this.graboReceta = graboReceta;
    }

    public boolean isImprimioReceta() {
        return imprimioReceta;
    }

    public void setImprimioReceta(boolean imprimioReceta) {
        this.imprimioReceta = imprimioReceta;
    }

    public BeanPaciente getBeanPaciente() {
        return beanPaciente;
    }

    public void setBeanPaciente(BeanPaciente beanPaciente) {
        this.beanPaciente = beanPaciente;
    }

    public boolean isModoVisual() {
        return modoVisual;
    }

    public void setModoVisual(boolean modoVisual) {
        this.modoVisual = modoVisual;
    }

    public boolean isAtencionNueva() {
        return atencionNueva;
    }

    public void setAtencionNueva(boolean atencionNueva) {
        this.atencionNueva = atencionNueva;
    }
    
    private void mostrarCampoInt(JNumericField jText, int valor){
        if(valor != Integer.MIN_VALUE){
            jText.setInt(valor);
        }
    }
    
    private void mostrarCampoDouble(JNumericField jText, double valor){
        if(valor != -99999.99){
            jText.setDouble(valor);
        }
    }
    
    private void funcionSalir(){
        if(!isModoVisual()){
            if(JConfirmDialog.rptaConfirmDialog(this, "Si cierra la ventana se borrará los datos registrados\n ¿Desea guardar los datos?"))
                grabarConsulta();
            else
                cerrarVentana(false);
        }else
            cerrarVentana(false);
    }

    private void txtDosis_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ADD)
            btnAgregarReceta.doClick();
        
    }

    private void cmbViaAdministracion_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ADD)
            btnAgregarReceta.doClick();
        
    }

    private void txtDuracionTratamiento_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ADD)
            btnAgregarReceta.doClick();
        
    }

    private void txtFrecuenciaTratamiento_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ADD)
            btnAgregarReceta.doClick();
        
    }

    public void setBandImpresion(boolean bandImpresion) {
        this.bandImpresion = bandImpresion;
    }

    public boolean isBandImpresion() {
        return bandImpresion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public String getAtencion() {
        return atencion;
    }


    class HiloCargaProdReceta extends Thread{
        @Override
        public void run() {
            //lstProductosReceta = utility.obtenerListadoProductos();
            if(lstDiagnostico == null){
                lstDiagnostico = new ArrayList<ArrayList<String>>();
                lstDiagnostico = utility.obtenerListaDiagnostico();
            }
            if(tblMdlListaReceta == null){
                tblMdlListaReceta = new FarmaTableModel(ConstantsVentas.columnsListaProductos, ConstantsVentas.defaultValuesListaProductos, 0);
                ArrayList lista = utility.obtenerListadoProductos();
                tblMdlListaReceta.data = lista;
                Collections.sort(tblMdlListaReceta.data, new FarmaTableComparator(2, true));
            }
        }
    }
    
    private JDialog getJDialog(){
        return this;
    }

    class KeyActionComunes implements KeyListener{
        private Component obj;
        
        public KeyActionComunes(Component obj){
            this.obj = obj;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(((JComboWithCheck)e.getSource()).isPermiteEventoEsc())
                        funcionSalir();
                }else{
                    funcionSalir();
                }
            }else if(UtilityPtoVenta.verificaVK_F2(e)){
                // mostrar antecedente del paciente
                //boolean edita = utility.verAntecedentePaciente(myParentFrame, getJDialog(), beanPaciente.getCodLocalAntecedente(), beanAtencionMedica.getCodPaciente(), beanPaciente.getNroHCAntecedente());
                if(beanPaciente.getHistoriaClinia().getBeanAntecedente().getSecuencialHC()==0){
                    BeanHCAntecedentes antecedente = new BeanHCAntecedentes();
                    antecedente.setCodGrupoCia(beanPaciente.getCodGrupoCia());
                    antecedente.setCodLocal(beanPaciente.getCodLocalAntecedente());
                    antecedente.setCodPaciente(beanPaciente.getCodPaciente());
                    antecedente.setSecuencialHC(beanPaciente.getNroHCAntecedente());
                    antecedente.setCodMedico(beanAtencionMedica.getCodMedico());
                    beanPaciente.getHistoriaClinia().setBeanAntecedente(antecedente);
                }
                utility.verAntecedentePaciente(myParentFrame, getJDialog(), beanPaciente);
            }else if(e.getKeyCode() == KeyEvent.VK_F5 && lblF5.isVisible()){
                DlgListadoAtencionesMedicas dlg = new DlgListadoAtencionesMedicas(myParentFrame, "", true);
                dlg.setCodPaciente(beanAtencionMedica.getCodPaciente());
                dlg.setVisible(true);
            }else if(e.getKeyCode() == KeyEvent.VK_F4){
                tabpContenedor.requestFocus();
            }else if(e.getKeyCode() == KeyEvent.VK_F6 && lblF6.isVisible()){
                UtilityAtencionMedica utilAtencionMedica = new UtilityAtencionMedica();
                boolean band = utilAtencionMedica.procesarImpresion(beanAtencionMedica.getCodPaciente(), getAtencion());
                if(!band){
                    FarmaUtility.showMessage((JFrame)myParentFrame, "Error al generar documento pdf de la Historia Clinica.\n"+
                                                       "Reintente, en caso persista el problema comuniquese con Mesa de Ayuda.", null);
                }else{
                    cerrarVentana(true);
                }
            }else if(UtilityPtoVenta.verificaVK_F11(e) && lblF5.isVisible() ){
                grabarConsulta();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
    
    class ActionTransferFocus implements ActionListener{
        private Component objNext;
        
        public ActionTransferFocus(Component objNext){
            this.objNext = objNext;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isModoVisual())
                FarmaUtility.moveFocus(objNext);
            else
                objNext.requestFocus();
            if(objNext instanceof JTable){
                if(((JTable)objNext).getRowCount()>0)
                    ((JTable)objNext).setRowSelectionInterval(0, 0);
            }else if(objNext instanceof JTextArea && isModoVisual()){
                ((JTextArea)objNext).selectAll();
            }
        }
    }
}
