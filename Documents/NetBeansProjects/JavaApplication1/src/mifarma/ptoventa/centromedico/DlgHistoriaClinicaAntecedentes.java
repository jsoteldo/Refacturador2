package mifarma.ptoventa.centromedico;


import com.gs.mifarma.componentes.DocumentFilterTextArea;
import com.gs.mifarma.componentes.FarmaHora;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JComboWithCheck;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JNumericField;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;
import com.gs.mifarma.componentes.OptionComboBox;

import com.gs.mifarma.componentes.UpperCaseDocument;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

import javax.swing.text.PlainDocument;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaTableComparator;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.DlgAtencionMedica.KeyActionComunes;
import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentes;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesFisiologicos;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesGenerales;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesGinecologicos;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesPatologico;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import mifarma.ptoventa.ventas.reference.ConstantsVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgHistoriaClinicaAntecedentes extends JDialog {
    
    private static final Logger log = LoggerFactory.getLogger(DlgHistoriaClinicaAntecedentes.class);
    private Frame myParentFrame;
    private JPanelWhite pnlContenedor = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JTabbedPane tbpPaneles = new JTabbedPane();
    
    /**
     * PANEL 1
     */
    private JPanel pnl1 = new JPanel();
    //FISIOLOGICAS
    private JPanel pnlFisiologicos = new JPanel();
    private JButtonLabel btnPrenatales = new JButtonLabel();
    private JComboWithCheck cmbPrenatales = new JComboWithCheck();
    private JButtonLabel btnPartos = new JButtonLabel();
    private JComboWithCheck cmbPartos = new JComboWithCheck();
    private JButtonLabel btnInmunizaciones = new JButtonLabel();
    private JComboWithCheck cmbInmunizaciones = new JComboWithCheck();
    
    //GENERALES - OCUPACIONALES
    private JPanel pnlGenerales = new JPanel();
    private JButtonLabel btnMedicamentos = new JButtonLabel();
    private JScrollPane scrPanTxtMedicamentos = new JScrollPane();
    private JTextArea txtMedicamentos = new JTextArea();
    private JButtonLabel btnHabitosNocivos = new JButtonLabel();
    private JComboWithCheck cmbHabitosNocivos = new JComboWithCheck();
    private JScrollPane scrPanTxtRAM = new JScrollPane();
    private JButtonLabel btnRAM = new JButtonLabel();
    private JTextArea txtRAM = new JTextArea();
    private JButtonLabel btnOcupacionales = new JButtonLabel();
    private JTextFieldSanSerif txtOcupacionales = new JTextFieldSanSerif();

    /**
     * PANEL 2
     */
    //GINECO-OBSTETRICOS
    private JPanel pnlGinecoObstetricos = new JPanel();
    
    private JButtonLabel btnEdadMenarquia = new JButtonLabel();
    private JNumericField txtEdadMenarquia = new JNumericField(2, JNumericField.NUMERIC);
    private JCheckBox chkReglaRegular = new JCheckBox();
    private JButtonLabel btnCatamenial = new JButtonLabel();
    private JNumericField txtCatamenial1 = new JNumericField(2, JNumericField.NUMERIC);
    private JLabel lblCatamenial2 = new JLabel();
    private JNumericField txtCatamenial2 = new JNumericField(2, JNumericField.NUMERIC);
    private JButtonLabel btnEdadRS = new JButtonLabel();
    private JNumericField txtEdadRS = new JNumericField(2, JNumericField.NUMERIC);
    private JButtonLabel btnFechaFUR = new JButtonLabel();
    private JTextFieldSanSerif txtFechaFUR = new JTextFieldSanSerif();
    private JButtonLabel btnFechaFPP = new JButtonLabel();
    private JTextFieldSanSerif txtFechaFPP = new JTextFieldSanSerif();
    private JCheckBox chkDisminorrea = new JCheckBox();
    private JButtonLabel btnNroGestaciones = new JButtonLabel();
    private JNumericField txtNroGestaciones = new JNumericField(2, JNumericField.NUMERIC);
    private JButtonLabel btnFechaFUP = new JButtonLabel();
    private JTextFieldSanSerif txtFechaFUP = new JTextFieldSanSerif();
    private JButtonLabel btnPariedad = new JButtonLabel();
    private JLabel lblPariedad1 = new JLabel();
    private JLabel lblPariedad2 = new JLabel();
    private JLabel lblPariedad3 = new JLabel();
    private JNumericField txtPariedad1 = new JNumericField(2, JNumericField.NUMERIC);
    private JNumericField txtPariedad2 = new JNumericField(2, JNumericField.NUMERIC);
    private JNumericField txtPariedad3 = new JNumericField(2, JNumericField.NUMERIC);
    private JNumericField txtPariedad4 = new JNumericField(2, JNumericField.NUMERIC);
    private JButtonLabel btnNroCesareas = new JButtonLabel();
    private JNumericField txtNroCesareas = new JNumericField(2, JNumericField.NUMERIC);
    private JScrollPane scrPanTxtPAP = new JScrollPane();
    private JButtonLabel btnPAP = new JButtonLabel();
    private JTextArea txtPAP = new JTextArea();
    private JScrollPane scrPanTxtMamografia = new JScrollPane();
    private JButtonLabel btnMamografia = new JButtonLabel();
    private JTextArea txtMamografia = new JTextArea();
    private JButtonLabel btnMac = new JButtonLabel();
    private JTextFieldSanSerif txtMac = new JTextFieldSanSerif();
    private JButtonLabel btnOtros = new JButtonLabel();
    private JTextFieldSanSerif txtOtros = new JTextFieldSanSerif();
    
    /**
     * PANEL3
     */
    //PATOLOGICOS
    private JPanel pnlPatologicos = new JPanel();
    
    private JPanelTitle pnlTitlePatologicos = new JPanelTitle();
    private JButtonLabel btnTitlePatologicos = new JButtonLabel();
    private JScrollPane scrTblPatologicos = new JScrollPane();
    private JTable tblPatologicos = new JTable();
    private FarmaTableModel mdlTblPatologicos;

    private JPanelTitle pnlTitlePatologicosFamiliares = new JPanelTitle();
    private JButtonLabel btnTitlePatologicosFamiliares = new JButtonLabel();
    private JScrollPane scrTblPatologicosFamiliares = new JScrollPane();
    private JTable tblPatologicosFamiliares = new JTable();
    private FarmaTableModel mdlTblPatologicosFamiliares;
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction lblF3 = new JLabelFunction();
    private JLabelFunction lblF4 = new JLabelFunction();
    
    /**
     * TECLAS DE FUNCION
     */
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF5 = new JLabelFunction();
    
    private transient BeanHCAntecedentes antecedentePaciente;
    private transient BeanPaciente beanPaciente;
    private boolean sexoFemeninoPaciente = false;
    private boolean modoVisual = false;
    
    private ArrayList<ArrayList<String>> lstDiagnostico;
    private int edadPaciente = -1;
    
    private UtilityAtencionMedica utility = new UtilityAtencionMedica();
    private JLabelFunction lblF6 = new JLabelFunction();
    private boolean modoVisualHistorico;

    public DlgHistoriaClinicaAntecedentes() {
        this(null, "", false, null);
    }

    public DlgHistoriaClinicaAntecedentes(Frame myParentFrame, String title, boolean modal, BeanPaciente beanPaciente) {
        super(myParentFrame, title, modal);
        this.myParentFrame = myParentFrame;
        this.beanPaciente = beanPaciente;
        this.antecedentePaciente = beanPaciente.getHistoriaClinia().getBeanAntecedente();
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(713, 517));
        this.getContentPane().setLayout(borderLayout1);
        this.setResizable(false);
        
        /************ PANEL FISIOLOGICOS***********/
        /******************************************/
        btnPrenatales.setText("Prenatales");
        btnPrenatales.setBounds(new Rectangle(10, 25, 90, 20));
        btnPrenatales.setForeground(new Color(255, 130, 14));
        btnPrenatales.setToolTipText("Se indica si el paciente tiene antecedentes prenatales");
        btnPrenatales.setMnemonic('n');
        btnPrenatales.setHorizontalAlignment(SwingConstants.RIGHT);

        cmbPrenatales.setBounds(new Rectangle(105, 25, 250, 20));
        cmbPrenatales.setToolTipText("Se indica si el paciente tiene antecedentes prenatales");
        cmbPrenatales.setFont(new Font("SansSerif", 0, 11));
        cmbPrenatales.setBounds(new Rectangle(105, 25, 545, 20));
       
        btnPartos.setText("Parto");
        btnPartos.setBounds(new Rectangle(10, 55, 90, 20));
        btnPartos.setToolTipText("Se indica si el paciente tiene antecedentes de parto");
        btnPartos.setForeground(new Color(255, 130, 14));
        btnPartos.setMnemonic('a');
        btnPartos.setHorizontalAlignment(SwingConstants.RIGHT);

        cmbPartos.setBounds(new Rectangle(105, 55, 250, 20));
        cmbPartos.setToolTipText("Se indica si el paciente tiene antecedentes de parto");
        cmbPartos.setFont(new Font("SansSerif", 0, 11));
        cmbPartos.setSeleccionMultiple(false);
        
        btnInmunizaciones.setText("Inmunizaciones");
        btnInmunizaciones.setBounds(new Rectangle(10, 85, 90, 20));
        btnInmunizaciones.setToolTipText("Se indica si el paciente tiene antecedentes de inmunizaciones");
        btnInmunizaciones.setForeground(new Color(255, 130, 14));
        btnInmunizaciones.setMnemonic('I');

        cmbInmunizaciones.setBounds(new Rectangle(105, 85, 545, 20));
        cmbInmunizaciones.setToolTipText("Se indica si el paciente tiene antecedentes de inmunizaciones");
        cmbInmunizaciones.setFont(new Font("SansSerif", 0, 11));
        
        pnlFisiologicos.setBackground(SystemColor.window);
        pnlFisiologicos.setLayout(null);
        pnlFisiologicos.setBounds(new Rectangle(10, 10, 675, 120));
        pnlFisiologicos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(43,141,39)),
                                                                   "FISIOLOGICOS",
                                                                   TitledBorder.LEFT,
                                                                   TitledBorder.DEFAULT_POSITION,
                                                                   new Font("SansSerif",Font.PLAIN,12),
                                                                   new Color(43,141,39)));
        pnlFisiologicos.add(btnPrenatales, null);
        pnlFisiologicos.add(cmbPrenatales, null);
        pnlFisiologicos.add(btnPartos, null);
        pnlFisiologicos.add(cmbPartos, null);
        pnlFisiologicos.add(btnInmunizaciones, null);
        pnlFisiologicos.add(cmbInmunizaciones, null);


        /************** PANEL GENERALES************/
        /******************************************/
        btnMedicamentos.setText("Medicamentos");
        btnMedicamentos.setBounds(new Rectangle(10, 25, 85, 20));
        btnMedicamentos.setToolTipText("Se ingresa el medicamento que usa con frecuencia");
        btnMedicamentos.setFont(new Font("SansSerif", 1, 12));
        btnMedicamentos.setForeground(new Color(255, 130, 14));
        btnMedicamentos.setMnemonic('M');
        
        txtMedicamentos.setFont(new Font("SansSerif", 0, 11));
        txtMedicamentos.setWrapStyleWord(true);
        txtMedicamentos.setLineWrap(true);
        txtMedicamentos.setTabSize(2);
        ((PlainDocument) txtMedicamentos.getDocument()).setDocumentFilter(new DocumentFilterTextArea(250));
        
        scrPanTxtMedicamentos.setBounds(new Rectangle(105, 25, 545, 90));
        scrPanTxtMedicamentos.getViewport().add(txtMedicamentos, null);

        btnHabitosNocivos.setText("Habitos Nocivos");
        btnHabitosNocivos.setBounds(new Rectangle(10, 125, 90, 20));
        btnHabitosNocivos.setToolTipText("Se indica si el paciente tiene hábitos nocivos");
        btnHabitosNocivos.setForeground(new Color(255, 130, 14));
        btnHabitosNocivos.setMnemonic('H');

        cmbHabitosNocivos.setBounds(new Rectangle(105, 125, 545, 20));
        cmbHabitosNocivos.setToolTipText("Se indica si el paciente tiene hábitos nocivos");

        btnRAM.setText("RAM");
        btnRAM.setBounds(new Rectangle(15, 155, 80, 20));
        btnRAM.setToolTipText("Se indica si se tiene reacci\u00f3n al\u00e9rgica alg\u00fan medicamento, en caso afirmativo se ingresa una descripci\u00f3n");
        btnRAM.setFont(new Font("SansSerif", 1, 12));
        btnRAM.setForeground(new Color(255, 130, 14));
        btnRAM.setMnemonic('R');

        txtRAM.setFont(new Font("SansSerif", 0, 11));
        txtRAM.setToolTipText("Se indica si se tiene reacci\u00f3n al\u00e9rgica alg\u00fan medicamento, en caso afirmativo se ingresa una descripci\u00f3n");
        txtRAM.setWrapStyleWord(true);
        txtRAM.setLineWrap(true);
        txtRAM.setTabSize(2);
        ((PlainDocument) txtRAM.getDocument()).setDocumentFilter(new DocumentFilterTextArea(250));
        
        scrPanTxtRAM.setBounds(new Rectangle(105, 155, 545, 90));
        scrPanTxtRAM.getViewport().add(txtRAM, null);

        btnOcupacionales.setText("Ocupacionales");
        btnOcupacionales.setBounds(new Rectangle(15, 255, 85, 20));
        btnOcupacionales.setForeground(new Color(255, 130, 14));
        btnOcupacionales.setMnemonic('O');
        btnOcupacionales.setToolTipText("Se registra en caso se tenga alg\u00fan antecedente de enfermedad con la labor que se desempe\u00f1a en el centro laboral");

        txtOcupacionales.setBounds(new Rectangle(105, 255, 545, 20));
        txtOcupacionales.setFont(new Font("SansSerif", 0, 11));
        txtOcupacionales.setToolTipText("Se registra en caso se tenga alg\u00fan antecedente de enfermedad con la labor que se desempe\u00f1a en el centro laboral");

        pnlGenerales.setBackground(SystemColor.window);
        pnlGenerales.setBounds(new Rectangle(10, 135, 675, 290));
        pnlGenerales.setLayout(null);
        pnlGenerales.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(43, 141, 39)),
                                                                "GENERALES / OCUPACIONALES", TitledBorder.LEFT,
                                                                TitledBorder.DEFAULT_POSITION,
                                                                new Font("SansSerif", Font.PLAIN, 12),
                                                                new Color(43, 141, 39)));
        pnlGenerales.add(scrPanTxtRAM, null);
        pnlGenerales.add(scrPanTxtMedicamentos, null);
        pnlGenerales.add(btnMedicamentos, null);
        pnlGenerales.add(btnHabitosNocivos, null);
        pnlGenerales.add(cmbHabitosNocivos, null);
        pnlGenerales.add(btnRAM, null);
        pnlGenerales.add(txtOcupacionales, null);
        pnlGenerales.add(btnOcupacionales, null);
        
        pnl1.setLayout(null);
        pnl1.setBackground(SystemColor.window);
        pnl1.add(pnlFisiologicos, null);
        pnl1.add(pnlGenerales, null);
        

        /*********** PANEL GINECO-OBSTETRICOS*********/
        /*********************************************/
        btnEdadMenarquia.setText("Menarquía");
        btnEdadMenarquia.setBounds(new Rectangle(25, 20, 60, 20));
        btnEdadMenarquia.setToolTipText("Se indica si tuvo o no menarquia la paciente y la edad cronol\u00f3gica");
        btnEdadMenarquia.setFont(new Font("SansSerif", 1, 11));
        btnEdadMenarquia.setForeground(new Color(255, 130, 14));
        btnEdadMenarquia.setMnemonic('e');
        
        chkReglaRegular.setText("¿Regla Regular?");
        chkReglaRegular.setBounds(new Rectangle(195, 20, 130, 20));
        chkReglaRegular.setForeground(new Color(255, 130, 14));
        chkReglaRegular.setFont(new Font("SansSerif", 1, 11));
        chkReglaRegular.setBackground(SystemColor.window);
        
        txtEdadMenarquia.setBounds(new Rectangle(90, 20, 55, 20));
        txtEdadMenarquia.setFont(new Font("SansSerif", 0, 11));
        txtEdadMenarquia.setHorizontalAlignment(JTextField.CENTER);
        txtEdadMenarquia.setAllowNegative(false);
        txtEdadMenarquia.setToolTipText("Se indica si tuvo o no menarquia la paciente y la edad cronol\u00f3gica");

        btnCatamenial.setText("R.Catamenial (+-3/28)");
        btnCatamenial.setBounds(new Rectangle(355, 20, 120, 20));
        btnCatamenial.setToolTipText("Se registra la duraci\u00f3n de la menstruaci\u00f3n (1-5 d\u00edas) duraci\u00f3n del ciclo menstrual (26-34 d\u00edas)");
        btnCatamenial.setFont(new Font("SansSerif", 1, 11));
        btnCatamenial.setForeground(new Color(255, 130, 14));
        btnCatamenial.setMnemonic('C');

        txtCatamenial1.setBounds(new Rectangle(520, 20, 30, 20));
        txtCatamenial1.setFont(new Font("SansSerif", 0, 11));
        txtCatamenial1.setHorizontalAlignment(JTextField.CENTER);
        txtCatamenial1.setAllowNegative(false);
        txtCatamenial1.setToolTipText("Se registra la duraci\u00f3n de la menstruaci\u00f3n (1-5 d\u00edas) duraci\u00f3n del ciclo menstrual (26-34 d\u00edas)");
        
        lblCatamenial2.setText("/");
        lblCatamenial2.setBounds(new Rectangle(505, 20, 15, 20));
        lblCatamenial2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCatamenial2.setHorizontalAlignment(SwingConstants.CENTER);
        lblCatamenial2.setFont(new Font("SansSerif", 0, 11));
        
        txtCatamenial2.setBounds(new Rectangle(475, 20, 30, 20));
        txtCatamenial2.setHorizontalAlignment(JTextField.CENTER);
        txtCatamenial2.setFont(new Font("SansSerif", 0, 11));
        txtCatamenial2.setAllowNegative(false);
        txtCatamenial2.setToolTipText("Se registra la duraci\u00f3n de la menstruaci\u00f3n (1-5 d\u00edas) duraci\u00f3n del ciclo menstrual (26-34 d\u00edas)");
        
        btnEdadRS.setText("RS");
        btnEdadRS.setBounds(new Rectangle(595, 20, 20, 20));
        btnEdadRS.setToolTipText("Se ingresa la edad de inicio de actividad sexual");
        btnEdadRS.setFont(new Font("SansSerif", 1, 11));
        btnEdadRS.setForeground(new Color(255, 130, 14));
        btnEdadRS.setMnemonic('S');
        
        txtEdadRS.setBounds(new Rectangle(620, 20, 30, 20));
        txtEdadRS.setHorizontalAlignment(JTextField.CENTER);
        txtEdadRS.setToolTipText("Se ingresa la edad de inicio de actividad sexual.");
        txtEdadRS.setFont(new Font("SansSerif", 0, 11));
        txtEdadRS.setAllowNegative(false);

        btnFechaFUR.setText("FUR");
        btnFechaFUR.setBounds(new Rectangle(20, 55, 30, 20));
        btnFechaFUR.setToolTipText("Se registra la \u00faltima fecha de menstruaci\u00f3n");
        btnFechaFUR.setFont(new Font("SansSerif", 1, 11));
        btnFechaFUR.setForeground(new Color(255, 130, 14));
        btnFechaFUR.setMnemonic('U');
        
        txtFechaFUR.setBounds(new Rectangle(50, 55, 95, 20));
        txtFechaFUR.setFont(new Font("SansSerif", 0, 11));
        txtFechaFUR.setHorizontalAlignment(JTextField.CENTER);
        txtFechaFUR.setToolTipText("Se registra la \u00faltima fecha de menstruaci\u00f3n");
        txtFechaFUR.setLengthText(10);


        btnFechaFPP.setText("FPP");
        btnFechaFPP.setBounds(new Rectangle(190, 55, 25, 20));
        btnFechaFPP.setToolTipText("Se registra la fecha programada de parto");
        btnFechaFPP.setFont(new Font("SansSerif", 1, 11));
        btnFechaFPP.setForeground(new Color(255, 130, 14));
        btnFechaFPP.setMnemonic('F');
        
        txtFechaFPP.setBounds(new Rectangle(220, 55, 95, 20));
        txtFechaFPP.setFont(new Font("SansSerif", 0, 11));
        txtFechaFPP.setHorizontalAlignment(JTextField.CENTER);
        txtFechaFPP.setToolTipText("Se registra la fecha programada de parto");
        txtFechaFPP.setLengthText(10);

        chkDisminorrea.setText("Disminorrea");
        chkDisminorrea.setBounds(new Rectangle(375, 55, 100, 20));
        chkDisminorrea.setToolTipText("Se indica si el paciente sufre de disminorrea");
        chkDisminorrea.setBackground(SystemColor.window);
        chkDisminorrea.setFont(new Font("SansSerif", 1, 11));
        chkDisminorrea.setForeground(new Color(255, 130, 14));
        chkDisminorrea.setMnemonic('D');

        btnNroGestaciones.setText("# Gestaciones");
        btnNroGestaciones.setBounds(new Rectangle(530, 55, 85, 20));
        btnNroGestaciones.setFont(new Font("SansSerif", 1, 11));
        btnNroGestaciones.setToolTipText("Se indica el n\u00famero de gestaciones del paciente siguiendo esta regla \"Gestaciones=Partos+Ces\u00e1reas\"");
        btnNroGestaciones.setForeground(new Color(255, 130, 14));
        btnNroGestaciones.setMnemonic('G');
        
        txtNroGestaciones.setBounds(new Rectangle(615, 55, 35, 20));
        txtNroGestaciones.setToolTipText("Se indica el n\u00famero de gestaciones del paciente siguiendo esta regla \"Gestaciones=Partos+Ces\u00e1reas\"");
        txtNroGestaciones.setHorizontalAlignment(JTextField.CENTER);
        txtNroGestaciones.setFont(new Font("SansSerif", 0, 11));
        txtNroGestaciones.setAllowNegative(false);
        
        btnFechaFUP.setText("FUP");
        btnFechaFUP.setBounds(new Rectangle(15, 90, 25, 20));
        btnFechaFUP.setFont(new Font("SansSerif", 1, 11));
        btnFechaFUP.setToolTipText("Se ingresa la fecha de \u00faltimo parto");
        btnFechaFUP.setForeground(new Color(255, 130, 14));
        btnFechaFUP.setMnemonic('P');
        
        txtFechaFUP.setBounds(new Rectangle(50, 90, 95, 20));
        txtFechaFUP.setToolTipText("Se ingresa la fecha de \u00faltimo parto");
        txtFechaFUP.setHorizontalAlignment(JTextField.CENTER);
        txtFechaFUP.setFont(new Font("SansSerif", 0, 11));
        txtFechaFUP.setLengthText(10);
        
        
        btnPariedad.setText("Pariedad");
        btnPariedad.setBounds(new Rectangle(190, 90, 55, 20));
        btnPariedad.setToolTipText("Se ingresa la cantidad de partos");
        btnPariedad.setFont(new Font("SansSerif", 1, 11));
        btnPariedad.setForeground(new Color(255, 130, 14));
        btnPariedad.setMnemonic('t');
        
        txtPariedad1.setBounds(new Rectangle(245, 90, 25, 20));
        txtPariedad1.setFont(new Font("SansSerif", 0, 11));
        txtPariedad1.setHorizontalAlignment(JTextField.CENTER);
        txtPariedad1.setToolTipText("Se ingresa la cantidad de partos");
        txtPariedad1.setAllowNegative(false);
        
        lblPariedad1.setText("-");
        lblPariedad1.setBounds(new Rectangle(270, 90, 15, 20));
        lblPariedad1.setFont(new Font("SansSerif", 1, 12));
        lblPariedad1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPariedad1.setHorizontalAlignment(SwingConstants.CENTER);
       
        txtPariedad2.setBounds(new Rectangle(285, 90, 25, 20));
        txtPariedad2.setFont(new Font("SansSerif", 0, 11));
        txtPariedad2.setHorizontalAlignment(JTextField.CENTER);
        txtPariedad2.setToolTipText("Se ingresa la cantidad de partos");
        txtPariedad2.setAllowNegative(false);

        lblPariedad2.setText("-");
        lblPariedad2.setBounds(new Rectangle(310, 90, 15, 20));
        lblPariedad2.setFont(new Font("SansSerif", 1, 12));
        lblPariedad2.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPariedad2.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtPariedad3.setBounds(new Rectangle(325, 90, 25, 20));
        txtPariedad3.setFont(new Font("SansSerif", 0, 11));
        txtPariedad3.setHorizontalAlignment(JTextField.CENTER);
        txtPariedad3.setToolTipText("Se ingresa la cantidad de partos");
        txtPariedad3.setAllowNegative(false);
        
        lblPariedad3.setText("-");
        lblPariedad3.setBounds(new Rectangle(350, 90, 15, 20));
        lblPariedad3.setFont(new Font("SansSerif", 1, 12));
        lblPariedad3.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPariedad3.setHorizontalAlignment(SwingConstants.CENTER);        
        
        txtPariedad4.setBounds(new Rectangle(365, 90, 25, 20));
        txtPariedad4.setFont(new Font("SansSerif", 0, 11));
        txtPariedad4.setHorizontalAlignment(JTextField.CENTER);
        txtPariedad4.setToolTipText("Se ingresa la cantidad de partos");
        txtPariedad4.setAllowNegative(false);
        
        btnNroCesareas.setText("# Cesáreas");
        btnNroCesareas.setBounds(new Rectangle(450, 90, 65, 20));
        btnNroCesareas.setToolTipText("Se ingresa la cantidad de ces\u00e1reas");
        btnNroCesareas.setFont(new Font("SansSerif", 1, 11));
        btnNroCesareas.setForeground(new Color(255, 130, 14));
        
        txtNroCesareas.setBounds(new Rectangle(520, 90, 35, 20));
        txtNroCesareas.setFont(new Font("SansSerif", 0, 11));
        txtNroCesareas.setHorizontalAlignment(JTextField.CENTER);
        txtNroCesareas.setToolTipText("Se ingresa la cantidad de ces\u00e1reas");
        txtNroCesareas.setAllowNegative(false);

        btnPAP.setText("PAP");
        btnPAP.setBounds(new Rectangle(20, 120, 30, 20));
        btnPAP.setToolTipText("Se ingresa la \u00faltima fecha de Papanicolaou");
        btnPAP.setFont(new Font("SansSerif", 1, 11));
        btnPAP.setForeground(new Color(255, 130, 14));
        
        txtPAP.setToolTipText("Se ingresa la \u00faltima fecha de Papanicolaou");
        txtPAP.setFont(new Font("SansSerif", 0, 11));
        txtPAP.setWrapStyleWord(true);
        txtPAP.setLineWrap(true);
        txtPAP.setTabSize(2);
        ((PlainDocument) txtPAP.getDocument()).setDocumentFilter(new DocumentFilterTextArea(250));
        

        scrPanTxtPAP.setBounds(new Rectangle(50, 125, 615, 90));
        
        btnMamografia.setText("Mamografía");
        btnMamografia.setBounds(new Rectangle(15, 225, 80, 20));
        btnMamografia.setToolTipText("Se ingresa la \u00faltima fecha de Mamograf\u00eda");
        btnMamografia.setFont(new Font("SansSerif", 1, 11));
        btnMamografia.setForeground(new Color(255, 130, 14));
        btnMamografia.setMnemonic('.');
        
        txtMamografia.setToolTipText("Se ingresa la \u00faltima fecha de Mamograf\u00eda");
        txtMamografia.setFont(new Font("SansSerif", 0, 11));
        txtMamografia.setWrapStyleWord(true);
        txtMamografia.setLineWrap(true);
        txtMamografia.setTabSize(2);
        ((PlainDocument) txtMamografia.getDocument()).setDocumentFilter(new DocumentFilterTextArea(250));
        
        scrPanTxtMamografia.setBounds(new Rectangle(50, 250, 615, 90));

        btnMac.setText("MAC");
        btnMac.setBounds(new Rectangle(20, 355, 30, 20));
        btnMac.setToolTipText("Se registra los m\u00e9todos anticonceptivos");
        btnMac.setFont(new Font("SansSerif", 1, 11));
        btnMac.setForeground(new Color(255, 130, 14));
        
        txtMac.setBounds(new Rectangle(50, 355, 615, 20));
        txtMac.setToolTipText("Se registra los m\u00e9todos anticonceptivos");
        txtMac.setFont(new Font("SansSerif", 0, 11));
        txtMac.setLengthText(250);

        btnOtros.setText("Otros");
        btnOtros.setBounds(new Rectangle(15, 390, 35, 20));
        btnOtros.setToolTipText("ingresa la descripci\u00f3n en caso hubiera otro antecedente ginecol\u00f3gico no considerado");
        btnOtros.setFont(new Font("SansSerif", 1, 11));
        btnOtros.setForeground(new Color(255, 130, 14));
        
        txtOtros.setBounds(new Rectangle(50, 390, 615, 20));
        txtOtros.setToolTipText("Ingresa la descripci\u00f3n en caso hubiera otro antecedente ginecol\u00f3gico no considerado");
        txtOtros.setFont(new Font("SansSerif", 0, 11));
        txtOtros.setLengthText(250);
    
        pnlGinecoObstetricos.setBackground(new Color(255, 255, 255));
        pnlGinecoObstetricos.setLayout(null);
        /*pnlGinecoObstetricos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(43,
                                                                                                                 141,
                                                                                                                 39)),
                                                                        "GINECO - OBSTETRICOS", TitledBorder.LEFT,
                                                                        TitledBorder.DEFAULT_POSITION,
                                                                        new Font("SansSerif", Font.PLAIN, 12),
                                                                        new Color(43, 141, 39)));
        pnlGinecoObstetricos.setBounds(new Rectangle(10, 10, 675, 385));*/
        pnlGinecoObstetricos.add(lblPariedad1, null);
        pnlGinecoObstetricos.add(lblPariedad2, null);
        pnlGinecoObstetricos.add(lblPariedad3, null);
        scrPanTxtMamografia.getViewport().add(txtMamografia, null);
        pnlGinecoObstetricos.add(scrPanTxtMamografia, null);
        scrPanTxtPAP.getViewport().add(txtPAP, null);
        pnlGinecoObstetricos.add(scrPanTxtPAP, null);
        pnlGinecoObstetricos.add(chkReglaRegular, null);
        pnlGinecoObstetricos.add(btnEdadMenarquia, null);
        pnlGinecoObstetricos.add(txtEdadMenarquia, null);
        pnlGinecoObstetricos.add(btnCatamenial, null);
        pnlGinecoObstetricos.add(lblCatamenial2, null);
        pnlGinecoObstetricos.add(txtCatamenial1, null);
        pnlGinecoObstetricos.add(txtCatamenial2, null);
        pnlGinecoObstetricos.add(btnFechaFUR, null);
        pnlGinecoObstetricos.add(txtFechaFUR, null);
        pnlGinecoObstetricos.add(btnFechaFPP, null);
        pnlGinecoObstetricos.add(txtFechaFPP, null);
        pnlGinecoObstetricos.add(btnEdadRS, null);
        pnlGinecoObstetricos.add(txtEdadRS, null);
        pnlGinecoObstetricos.add(chkDisminorrea, null);
        pnlGinecoObstetricos.add(btnNroGestaciones, null);
        pnlGinecoObstetricos.add(txtNroGestaciones, null);
        pnlGinecoObstetricos.add(btnPariedad, null);
        pnlGinecoObstetricos.add(txtPariedad1, null);
        pnlGinecoObstetricos.add(txtPariedad2, null);
        pnlGinecoObstetricos.add(txtPariedad3, null);
        pnlGinecoObstetricos.add(txtPariedad4, null);


        /*************** PANEL PATOLOGICOS ************/
        /*********************************************/
        pnlGinecoObstetricos.add(btnFechaFUP, null);
        pnlGinecoObstetricos.add(txtFechaFUP, null);
        pnlGinecoObstetricos.add(btnNroCesareas, null);
        pnlGinecoObstetricos.add(txtNroCesareas, null);
        pnlGinecoObstetricos.add(btnPAP, null);
        pnlGinecoObstetricos.add(btnMamografia, null);
        pnlGinecoObstetricos.add(btnMac, null);
        pnlGinecoObstetricos.add(txtMac, null);
        pnlGinecoObstetricos.add(btnOtros, null);
        pnlGinecoObstetricos.add(txtOtros, null);
        btnTitlePatologicos.setText("1.Antecedentes Patol\u00f3gicos");
        btnTitlePatologicos.setBounds(new Rectangle(5, 0, 165, 15));
        btnTitlePatologicos.setMnemonic('1');
        
        pnlTitlePatologicos.setBounds(new Rectangle(20, 10, 645, 15));
        pnlTitlePatologicos.add(btnTitlePatologicos, null);
        
        scrTblPatologicos.setBounds(new Rectangle(20, 25, 645, 150));
        scrTblPatologicos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrTblPatologicos.getViewport().add(tblPatologicos, null);
        
        btnTitlePatologicosFamiliares.setText("2.Patol\u00f3gicos Familiares");
        btnTitlePatologicosFamiliares.setBounds(new Rectangle(5, 0, 150, 15));
        btnTitlePatologicosFamiliares.setMnemonic('2');
        
        pnlTitlePatologicosFamiliares.setBounds(new Rectangle(15, 225, 650, 15));

        scrTblPatologicosFamiliares.setBounds(new Rectangle(15, 240, 650, 150));
        scrTblPatologicosFamiliares.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lblF1.setBounds(new Rectangle(490, 180, 85, 20));
        lblF1.setText("[ F1 ] Agregar");
        
        lblF2.setBounds(new Rectangle(580, 180, 85, 20));
        lblF2.setText("[ F2 ] Quitar");
        
        lblF3.setBounds(new Rectangle(490, 395, 85, 20));
        lblF3.setText("[ F3 ] Agregar");
        
        lblF4.setBounds(new Rectangle(580, 395, 85, 20));
        lblF4.setText("[ F4 ] Quitar");
        
        pnlPatologicos.setBackground(SystemColor.window);
        pnlPatologicos.setLayout(null);
        /*pnlPatologicos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(43, 141,
                                                                                                           39)),
                                                                  "PATOLOGICOS", TitledBorder.LEFT,
                                                                  TitledBorder.DEFAULT_POSITION,
                                                                  new Font("SansSerif", Font.PLAIN, 12),
                                                                  new Color(43, 141, 39)));
        pnlPatologicos.setBounds(new Rectangle(5, 10, 680, 425));*/

        pnlPatologicos.add(lblF4, null);
        pnlPatologicos.add(lblF3, null);
        pnlPatologicos.add(lblF2, null);
        pnlPatologicos.add(lblF1, null);
        pnlPatologicos.add(pnlTitlePatologicos, null);
        pnlPatologicos.add(scrTblPatologicos, null);


        /**********BOTONES DE FUNCION ***********/
        /***************************************/
        pnlTitlePatologicosFamiliares.add(btnTitlePatologicosFamiliares, null);
        pnlPatologicos.add(pnlTitlePatologicosFamiliares, null);
        scrTblPatologicosFamiliares.getViewport().add(tblPatologicosFamiliares, null);
        pnlPatologicos.add(scrTblPatologicosFamiliares, null);
        lblF11.setBounds(new Rectangle(590, 465, 110, 20));
        lblF11.setText("[ F11 ] Guardar");
        
        lblEsc.setBounds(new Rectangle(470, 465, 110, 20));
        lblEsc.setText("[ Esc ] Salir");
        
        lblF5.setBounds(new Rectangle(205, 465, 117, 19));
        lblF5.setText("[ F5 ] Historial");

        lblF6.setBounds(new Rectangle(330, 465, 130, 20));
        lblF6.setText("[ F6 ] Cambiar Panel");
        tbpPaneles.setBounds(new Rectangle(5, 5, 695, 455));
        tbpPaneles.addTab("Generales", pnl1);
        tbpPaneles.addTab("Ginecológicos", pnlGinecoObstetricos);
        tbpPaneles.addTab("Patológicos", pnlPatologicos);

        pnlContenedor.add(lblF6, null);
        pnlContenedor.add(lblF5, null);
        pnlContenedor.add(lblF11, null);
        pnlContenedor.add(lblEsc, null);
        pnlContenedor.add(tbpPaneles, null);
        
        this.getContentPane().add(pnlContenedor, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
    }
    
    private void initialize(){
        cargarTablas();
        cargarCombos();
        asignarNombresComponentes();
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        setearTitulo();
        ocultarComponentes();
        //habilitarCamposSegunGenero(isSexoFemenino());
        configurarEventos();
        mostrarDatosAntecedentes();
        if(isModoVisual()){
            cargarModoVisual(false);
        }else{
            lblF5.setVisible(false);
            HiloCargaListaDiagnostico hilo = new HiloCargaListaDiagnostico();
            hilo.start();
        }
        FarmaUtility.moveFocus(cmbPrenatales);
    }
    
    private void setearTitulo(){
        edadPaciente = calcularEdad();
        if(edadPaciente==-1){
            this.setTitle("Antecendentes - Nro.HC: "+beanPaciente.getHistoriaClinia().getNroHistoriaActual()+
                          " - Edad: NR"+
                          " - "+beanPaciente.getApellidoPaterno()+" "+beanPaciente.getApellidoMaterno()+" "+
                          beanPaciente.getNombre());
        }else{
            this.setTitle("Antecendentes - Nro.HC: "+beanPaciente.getHistoriaClinia().getNroHistoriaActual()+
                          " - Edad: "+edadPaciente+
                          " - "+beanPaciente.getApellidoPaterno()+" "+beanPaciente.getApellidoMaterno()+" "+
                          beanPaciente.getNombre());

        }
    }
    
    private void this_windowClosing(WindowEvent e) {
        //FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    
    private void ocultarComponentes(){
        if(!isSexoFemenino() || edadPaciente<=9){
            tbpPaneles.remove(1);
        }
    }
    
    private void cargarModoVisual(boolean activo){
        if(!activo)
            lblF11.setText("[ F11 ] Editar");
        else
            lblF11.setText("[ F11 ] Guardar");
        lblF1.setEnabled(activo);
        lblF3.setEnabled(activo);
        lblF2.setEnabled(activo);
        lblF4.setEnabled(activo);
        if(isModoVisualHistorico()){
            lblF5.setVisible(false);
            lblF11.setVisible(false);
        }
        /*deshabilitar(pnlFisiologicos, activo);
        deshabilitar(pnlGenerales, activo);
        deshabilitar(pnlGinecoObstetricos, activo);*/
        deshabilitar(tbpPaneles,activo);
        /*if(isSexoFemenino()){
            deshabilitar(pnlGinecoObstetricos, activo);
        }*/
        
    }
    
    private void deshabilitar(Component panel, boolean activo){
        if(panel instanceof JComboWithCheck)
            ((JComboWithCheck)panel).setSeleccionable(activo);
        if(panel instanceof JLabelFunction)
            ((JLabelFunction)panel).setVisible(activo);
        else if(panel instanceof JTextComponent)
            ((JTextComponent)panel).setEditable(activo);
        else if(panel instanceof JCheckBox)
            ((JCheckBox)panel).setEnabled(activo);
        else if(panel instanceof Container){
            Component[] lst = ((Container)panel).getComponents();
            for(Component component : lst)
                deshabilitar(component, activo);
        }
        /*
        if(panel instanceof JTextField)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JTable)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JComboBox)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JCheckBox)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JTextArea)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof Container){
            Component[] lst = ((Container)panel).getComponents();
            for(Component component : lst)
                ponerListenerComunes(component);
        }
        */
    }
        /*
    private void deshabilitar(JPanel panel, boolean activo){
        Component[] lst = panel.getComponents();
        for(Component component : lst){
            if(component instanceof JComboWithCheck)
                ((JComboWithCheck)component).setSeleccionable(activo);
            else if(component instanceof JTextComponent)
                ((JTextComponent)component).setEditable(activo);
        }
    }
    */
    private void cargarTablas(){
        //235
        FarmaColumnData[] colTblDiagnostico = { new FarmaColumnData("CIE", 70, JLabel.LEFT),            //0
                                                new FarmaColumnData("Descripción", 555, JLabel.LEFT),     //1
                                                new FarmaColumnData("cod_diagnostico", 0, JLabel.CENTER)  //4
                                              };
        
        mdlTblPatologicos = new FarmaTableModel(colTblDiagnostico, UtilityPtoVenta.obtenerDefaultValuesTabla(colTblDiagnostico.length),0);
        FarmaUtility.initSimpleList(tblPatologicos, mdlTblPatologicos, colTblDiagnostico);
        
        //315
        FarmaColumnData[] colTblReceta = { new FarmaColumnData("CIE", 70, JLabel.LEFT),             //0
                                           new FarmaColumnData("Descripción", 470, JLabel.LEFT),    //1
                                           new FarmaColumnData("Familiar", 90, JLabel.LEFT),        //2
                                           new FarmaColumnData("cod_diagnostico", 0, JLabel.CENTER) //3
                                         };
        mdlTblPatologicosFamiliares = new FarmaTableModel(colTblReceta, UtilityPtoVenta.obtenerDefaultValuesTabla(colTblReceta.length),0);
        FarmaUtility.initSimpleList(tblPatologicosFamiliares, mdlTblPatologicosFamiliares, colTblReceta);
    }
    
    private int contEnter = 0;
    
    private void configurarEventos(){
        
        tbpPaneles.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarTabPanel();
            }
        });

        tbpPaneles.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER)
                    mostrarTabPanel();
                
            }
        });
        
        btnPrenatales.addActionListener(new ActionTransferFocus(cmbPrenatales));
        btnPartos.addActionListener(new ActionTransferFocus(cmbPartos));
        btnInmunizaciones.addActionListener(new ActionTransferFocus(cmbInmunizaciones));        
        btnMedicamentos.addActionListener(new ActionTransferFocus(txtMedicamentos));
        btnHabitosNocivos.addActionListener(new ActionTransferFocus(cmbHabitosNocivos));
        btnRAM.addActionListener(new ActionTransferFocus(txtRAM));
        btnOcupacionales.addActionListener(new ActionTransferFocus(txtOcupacionales));
        
        cmbPrenatales.setNextObjTransferFocus(cmbPartos);
        cmbPartos.setNextObjTransferFocus(cmbInmunizaciones);
        cmbInmunizaciones.setNextObjTransferFocus(txtMedicamentos);
        txtMedicamentos.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contEnter++;
                    if(contEnter==2){
                        contEnter = 0;
                        e.consume();
                        FarmaUtility.moveFocus(cmbHabitosNocivos);
                    }
                }else{
                    contEnter = 0;
                }
            }
        });
        
        txtMedicamentos.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contEnter = 0;
            }
        });
        
        cmbHabitosNocivos.setNextObjTransferFocus(txtRAM);
        txtRAM.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contEnter++;
                    if(contEnter==2){
                        contEnter = 0;
                        e.consume();
                        FarmaUtility.moveFocus(txtOcupacionales);
                    }
                }else{
                    contEnter = 0;
                }
            }
        });
        
        txtRAM.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contEnter = 0;
            }
        });
        
        txtOcupacionales.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    tbpPaneles.setSelectedIndex(1);
                    if(isSexoFemenino()){
                        txtEdadMenarquia.requestFocus();
                    }else{
                        tblPatologicos.requestFocus();
                    }
                }
            }
        });
        
        btnEdadMenarquia.addActionListener(new ActionTransferFocus(txtEdadMenarquia));
        txtEdadMenarquia.addActionListener(new ActionTransferFocus(chkReglaRegular));
        chkReglaRegular.addActionListener(new ActionTransferFocus(txtCatamenial2));
        btnCatamenial.addActionListener(new ActionTransferFocus(txtCatamenial2));
        txtCatamenial2.addActionListener(new ActionTransferFocus(txtCatamenial1));
        txtCatamenial1.addActionListener(new ActionTransferFocus(txtEdadRS));
        btnEdadRS.addActionListener(new ActionTransferFocus(txtEdadRS));
        txtEdadRS.addActionListener(new ActionTransferFocus(txtFechaFUR));
        btnFechaFUR.addActionListener(new ActionTransferFocus(txtFechaFUR));
        txtFechaFUR.addActionListener(new ActionTransferFocus(txtFechaFPP));
        btnFechaFPP.addActionListener(new ActionTransferFocus(txtFechaFPP));
        txtFechaFPP.addActionListener(new ActionTransferFocus(chkDisminorrea));
        chkDisminorrea.addActionListener(new ActionTransferFocus(txtNroGestaciones));
        btnNroGestaciones.addActionListener(new ActionTransferFocus(txtNroGestaciones));
        txtNroGestaciones.addActionListener(new ActionTransferFocus(txtFechaFUP));
        btnFechaFUP.addActionListener(new ActionTransferFocus(txtFechaFUP));
        txtFechaFUP.addActionListener(new ActionTransferFocus(txtPariedad1));
        btnPariedad.addActionListener(new ActionTransferFocus(txtPariedad1));
        txtPariedad1.addActionListener(new ActionTransferFocus(txtPariedad2));
        txtPariedad2.addActionListener(new ActionTransferFocus(txtPariedad3));
        txtPariedad3.addActionListener(new ActionTransferFocus(txtPariedad4));
        txtPariedad4.addActionListener(new ActionTransferFocus(txtNroCesareas));
        btnNroCesareas.addActionListener(new ActionTransferFocus(txtNroCesareas));
        txtNroCesareas.addActionListener(new ActionTransferFocus(txtPAP));
        btnPAP.addActionListener(new ActionTransferFocus(txtPAP));
        txtPAP.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contEnter++;
                    if(contEnter==2){
                        contEnter = 0;
                        e.consume();
                        FarmaUtility.moveFocus(txtMamografia);
                    }
                }else{
                    contEnter = 0;
                }
            }
        });
        txtPAP.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contEnter = 0;
            }
        });
        
        btnMamografia.addActionListener(new ActionTransferFocus(txtMamografia));
        txtMamografia.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    contEnter++;
                    if(contEnter==2){
                        contEnter = 0;
                        e.consume();
                        FarmaUtility.moveFocus(txtMac);
                    }
                }else{
                    contEnter = 0;
                }
            }
        });
        txtMamografia.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                contEnter = 0;
            }
            public void focusLost(FocusEvent e){
                contEnter = 0;
            }
        });
        
        btnMac.addActionListener(new ActionTransferFocus(txtMac));
        txtMac.addActionListener(new ActionTransferFocus(txtOtros));
        
        btnOtros.addActionListener(new ActionTransferFocus(txtOtros));
        txtOtros.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    tbpPaneles.setSelectedIndex(2);
                    tblPatologicos.requestFocus();
                }
            }
        });
        
        txtFechaFPP.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                if(!Character.isDigit(e.getKeyChar())){
                    e.consume();
                }
            }
            public void keyReleased(KeyEvent e) {
                formatoFecha(e);
            }
        });
        
        txtFechaFUP.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                if(!Character.isDigit(e.getKeyChar())){
                    e.consume();
                }
            }
            public void keyReleased(KeyEvent e) {
                formatoFecha(e);
            }
        });
        
        txtFechaFUR.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                if(!Character.isDigit(e.getKeyChar())){
                    e.consume();
                }
            }
            
            public void keyReleased(KeyEvent e) {
                formatoFecha(e);
                
            }
        });
        
        chkReglaRegular.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    chkReglaRegular.setSelected(!chkReglaRegular.isSelected());
                    txtCatamenial2.requestFocus();
                }
            }
        });
        
        
        chkDisminorrea.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    chkDisminorrea.setSelected(!chkDisminorrea.isSelected());
                    txtNroGestaciones.requestFocus();
                }
            }
        });
        
        btnTitlePatologicos.addActionListener(new ActionTransferFocus(tblPatologicos));
        btnTitlePatologicosFamiliares.addActionListener(new ActionTransferFocus(tblPatologicosFamiliares));
        
        lblF1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                mostrarDiagnostico();
            }
        });
        
        lblF2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                quitarAntecedentePatologico();
            }
        });
        
        lblF3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                mostrarDiagnosticoFamiliares();
            }
        });
        
        lblF4.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                quitarAntecedentePatologicoFamiliares();
            }
        });
        
        lblEsc.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                cerrarVentana(false);
            }
        });
        
        lblF11.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                grabarAntecedente();
            }
        });
        
        lblF5.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                mostrarHistorialAntecedente();
            }
        });
        
        ponerListenerComunes(pnlContenedor);
        
    }
    
    private void ponerListenerComunes(Component panel){
        if(panel instanceof JButtonLabel)
            panel.addKeyListener(new KeyActionComunes(panel));
        if(panel instanceof JTextField)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JTable)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JComboBox)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JCheckBox)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof JTextArea)
            panel.addKeyListener(new KeyActionComunes(panel));
        else if(panel instanceof Container){
            Component[] lst = ((Container)panel).getComponents();
            for(Component component : lst)
                ponerListenerComunes(component);
        }
    }
    
    private void formatoFecha(KeyEvent e){
        FarmaUtility.dateComplete((JTextField)e.getSource(), e);
    }
    
    private void pasarFocus(KeyEvent e, Object objOrigen, Object objDestino){
        if(e==null || (e!=null && e.getKeyCode() == KeyEvent.VK_ENTER)){
            ((Component)objDestino).requestFocus();
        }
    }
   
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    
    /* private void habilitarCamposSegunGenero(boolean isFemenino){
        Component[] lst = pnlGinecoObstetricos.getComponents();
        for(Component component : lst){
            if(component instanceof JTextField)
                component.setEnabled(isFemenino);
            else if(component instanceof JCheckBox)
                component.setEnabled(isFemenino);
        }
        if(!isFemenino)
            pnlGinecoObstetricos.setBackground(new Color(231, 231, 231));
    } */
    
    private void mostrarDatosAntecedentes(){
        ArrayList<BeanHCAntecedentesFisiologicos> lstFisiologicos = antecedentePaciente.getFisiologico();
        for(int i=0;i<lstFisiologicos.size();i++){
            BeanHCAntecedentesFisiologicos fisiologico = lstFisiologicos.get(i);
            if(fisiologico.getCodGrupo()==31){
                cmbPrenatales.selectItemIdentificador(fisiologico.getCodAnteFisiologico(), fisiologico.getDescAnteFisiologico());
                /*for(int k=0;k<lstPrenatales.size();k++)
                    //if( Integer.valueOf(((BigDecimal)lstPrenatales.get(k).getCodigo()).intValue()) == fisiologico.getCodAnteFisiologico()){
                    if( Integer.valueOf(((String)lstPrenatales.get(k).getCodigo())) == fisiologico.getCodAnteFisiologico()){
                        lstPrenatales.get(k).setSeleccionado(true);
                        if(lstPrenatales.get(k).isOpcionOtro())
                            lstPrenatales.get(k).setLabel(fisiologico.getDescAnteFisiologico());
                    }*/
                
            }else if(fisiologico.getCodGrupo()==32){
                cmbPartos.selectItemIdentificador(fisiologico.getCodAnteFisiologico(), fisiologico.getDescAnteFisiologico());
                /*for(int k=0;k<lstParto.size();k++)
                    //if( Integer.valueOf(((BigDecimal)lstParto.get(k).getCodigo()).intValue()) == fisiologico.getCodAnteFisiologico()){
                    if( Integer.valueOf(((String)lstParto.get(k).getCodigo())) == fisiologico.getCodAnteFisiologico()){
                        lstParto.get(k).setSeleccionado(true);
                        if(lstParto.get(k).isOpcionOtro())
                            lstParto.get(k).setLabel(fisiologico.getDescAnteFisiologico());
                    }*/
            }else if(fisiologico.getCodGrupo()==33){
                cmbInmunizaciones.selectItemIdentificador(fisiologico.getCodAnteFisiologico(), fisiologico.getDescAnteFisiologico());
                /*for(int k=0;k<lstInmunizaciones.size();k++)
                    //if( Integer.valueOf(((BigDecimal)lstInmunizaciones.get(k).getCodigo()).intValue()) == fisiologico.getCodAnteFisiologico()){
                    if( Integer.valueOf(((String)lstInmunizaciones.get(k).getCodigo())) == fisiologico.getCodAnteFisiologico()){
                        lstInmunizaciones.get(k).setSeleccionado(true);
                        if(lstInmunizaciones.get(k).isOpcionOtro())
                            lstInmunizaciones.get(k).setLabel(fisiologico.getDescAnteFisiologico());
                    }*/
            }else if(fisiologico.getCodGrupo()==34){
                cmbHabitosNocivos.selectItemIdentificador(fisiologico.getCodAnteFisiologico(), fisiologico.getDescAnteFisiologico());
                /*for(int k=0;k<lstHabitosNocivos.size();k++)
                    //if( Integer.valueOf(((BigDecimal)lstHabitosNocivos.get(k).getCodigo()).intValue()) == fisiologico.getCodAnteFisiologico()){
                    if( Integer.valueOf(((String)lstHabitosNocivos.get(k).getCodigo())) == fisiologico.getCodAnteFisiologico()){
                        lstHabitosNocivos.get(k).setSeleccionado(true);
                        if(lstHabitosNocivos.get(k).isOpcionOtro())
                            lstHabitosNocivos.get(k).setLabel(fisiologico.getDescAnteFisiologico());
                    }*/
            }
        }
        
        BeanHCAntecedentesGenerales generales = antecedentePaciente.getGenerales();
        if(generales!=null){
            txtMedicamentos.setText(getValorTextoNulo(generales.getMedicamentos()));
            txtRAM.setText(getValorTextoNulo(generales.getRam()));
            txtOcupacionales.setText(getValorTextoNulo(generales.getOcupacionales()));
        }
        
        BeanHCAntecedentesGinecologicos ginecologico = antecedentePaciente.getGinecologico();
        if(ginecologico != null){
            
            //txtEdadMenarquia.setInt(ginecologico.getEdadMenarquia());
            mostrarCampoInt(txtEdadMenarquia, ginecologico.getEdadMenarquia());
            chkReglaRegular.setSelected("S".equalsIgnoreCase(ginecologico.getIndReglaRegular()));
            //txtCatamenial1.setInt(ginecologico.getRcCicloMenstrual());
            mostrarCampoInt(txtCatamenial1, ginecologico.getRcCicloMenstrual());
            //txtCatamenial2.setInt(ginecologico.getRcMenstruacion());
            mostrarCampoInt(txtCatamenial2, ginecologico.getRcMenstruacion());
            //txtEdadRS.setInt(ginecologico.getRs());
            mostrarCampoInt(txtEdadRS, ginecologico.getRs());
            txtFechaFUR.setText(getValorTextoNulo(ginecologico.getFur()));
            txtFechaFPP.setText(getValorTextoNulo(ginecologico.getFpp()));
            chkDisminorrea.setSelected("S".equalsIgnoreCase(ginecologico.getDisminorrea()));
            //txtNroGestaciones.setInt(ginecologico.getNroGestaciones());
            mostrarCampoInt(txtNroGestaciones, ginecologico.getNroGestaciones());
            txtFechaFUP.setText(getValorTextoNulo(ginecologico.getFup()));
            mostrarParidad(getValorTextoNulo(ginecologico.getParidad()));
            //txtNroCesareas.setInt(ginecologico.getNroCesareas());
            mostrarCampoInt(txtNroCesareas, ginecologico.getNroCesareas());
            txtPAP.setText(getValorTextoNulo(ginecologico.getPap()));
            txtMamografia.setText(getValorTextoNulo(ginecologico.getMamografia()));
            txtMac.setText(getValorTextoNulo(ginecologico.getMac()));
            txtOtros.setText(getValorTextoNulo(ginecologico.getOtros()));
        }
        
        ArrayList<BeanHCAntecedentesPatologico> lstPatologico = antecedentePaciente.getPatologicos();
        for(int i=0;i<lstPatologico.size();i++){
            BeanHCAntecedentesPatologico patologico = lstPatologico.get(i);
            if(patologico.getTipoPatologico()!=null && patologico.getTipoPatologico().trim().length()>0){
                ArrayList<String> fila = new ArrayList<String>();
                fila.add(patologico.getCodCIE10());
                fila.add(patologico.getNombreDiagnostico());
                if("PA".equalsIgnoreCase(patologico.getTipoPatologico())){
                    fila.add(patologico.getCodDiagnostico());
                    mdlTblPatologicos.data.add(fila);
                }else if("FA".equalsIgnoreCase(patologico.getTipoPatologico())){
                    fila.add(patologico.getDescripcionPariente());
                    fila.add(patologico.getCodDiagnostico());
                    mdlTblPatologicosFamiliares.data.add(fila);
                }
            }
        }
        mdlTblPatologicos.fireTableDataChanged();
        mdlTblPatologicosFamiliares.fireTableDataChanged();
    }
    
    private void mostrarCampoInt(JNumericField jText, int valor){
        if(valor != Integer.MIN_VALUE){
            jText.setInt(valor);
        }
    }
    private void cargarCombos(){
        cargarComboGenerico(cmbPrenatales, 31);
        cargarComboGenerico(cmbPartos, 32);
        cargarComboGenerico(cmbInmunizaciones, 33);
        cargarComboGenerico(cmbHabitosNocivos, 34);
    }
    
    private void cargarComboGenerico(JComboWithCheck combo, int idCombo){
        ArrayList<OptionComboBox> lstPrenatales = utility.obtenerListaComboCheckBox(idCombo);
        Map<Object, Boolean> optionsCombo = new LinkedHashMap<Object, Boolean>();
        for(int i=0;i<lstPrenatales.size();i++){
            OptionComboBox option = lstPrenatales.get(i);
            optionsCombo.put(option, false);
        }
        combo.addItems(optionsCombo);
    }
    
    private String getValorTextoNulo(JTextComponent jText){
        if(jText.getText() == null)
            jText.setText("");
        return jText.getText().trim();
    }
    
    private String getValorTextoNulo(String text){
        if(text == null)
            text = "";
        return text;
    }
    
    private int getValorIntNulo(JNumericField jText){
        try{
            return jText.getInt();
        }catch(Exception ex){
            return Integer.MIN_VALUE;
        }
    }
    
    private void asignarNombresComponentes(){
        tblPatologicos.setName("tblPatologicos");
        txtMedicamentos.setName("MEDICAMENTOS");
        tblPatologicosFamiliares.setName("tblPatologicosFamiliares");
        txtRAM.setName("RAM");
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
                    FarmaUtility.showMessage(this, "Consulta Médica: campo "+component.getName()+" vació. verifique!!!", component);
            }
        }else if(component instanceof JTable){
            if(((JTable)component).getRowCount() == 0){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(this, "Consulta Médica: no ha agregado registro a la lista de "+component.getName()+". verifique!!!", component);
            }
        }else if(component instanceof JComboBox){
            if(((JComboBox)component).getSelectedIndex() < 1){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(this, "Consulta Médica: No ha selecciodo un item en el campo "+component.getName()+". verifique!!!", component);
            }
        }
        return vacio;
    }
    
    private void mostrarDiagnostico(){
        if(!isModoVisual() && (tbpPaneles.getSelectedComponent().equals(pnlPatologicos)) ){
            if(lstDiagnostico == null || (lstDiagnostico!=null && lstDiagnostico.size()==0)){
                FarmaUtility.showMessage(this, "Antecedentes:\n"+
                                               "Lista de Diagnosticos se esta cargando, reintente.", cmbPrenatales);
                return;
            }
            ArrayList lstSelecPrevios = new ArrayList();
            for(int i=0; i<mdlTblPatologicos.data.size();i++){
                lstSelecPrevios.add(mdlTblPatologicos.data.get(i));
            }
            
            DlgListadoCM dlgListado = new DlgListadoCM(myParentFrame, "Antecedentes Patológicos", true, true, lstDiagnostico);
            dlgListado.setLstSelecPrevios(lstSelecPrevios);
            dlgListado.setVisible(true);
            if(FarmaVariables.vAceptar){
                FarmaVariables.vAceptar = false;
                ArrayList lstResultado = dlgListado.getLstResultado();
                if(!lstResultado.isEmpty()){
                    mdlTblPatologicos.clearTable();
                    for(int i=0;i<lstResultado.size();i++)
                        mdlTblPatologicos.data.add((ArrayList)lstResultado.get(i));
                    mdlTblPatologicos.fireTableDataChanged();
                }
            }
            FarmaUtility.moveFocusJTable(tblPatologicos);
        }
    }
    
    private void mostrarDiagnosticoFamiliares(){
        if(!isModoVisual() && (tbpPaneles.getSelectedComponent().equals(pnlPatologicos))){
            if(lstDiagnostico == null || (lstDiagnostico!=null && lstDiagnostico.size()==0)){
                FarmaUtility.showMessage(this, "Antecedentes:\n"+
                                               "Lista de Diagnosticos se esta cargando, reintente.", cmbPrenatales);
                return;
            }
            DlgListadoCM dlgListado = new DlgListadoCM(myParentFrame, "Antecedentes Patológicos Familiares", true, false, lstDiagnostico);
            dlgListado.setVisible(true);
            if(FarmaVariables.vAceptar){
                FarmaVariables.vAceptar = false;
                ArrayList lstResultado = dlgListado.getLstResultado();
                if(!lstResultado.isEmpty()){
                    String datoFamilia = JOptionPane.showInputDialog(this, "Ingrese parentesco:", "");
                    if(datoFamilia!=null){
                        ArrayList fila = (ArrayList)lstResultado.get(0);
                        fila.add(2, datoFamilia.toUpperCase());
                        mdlTblPatologicosFamiliares.data.add(fila);
                        mdlTblPatologicosFamiliares.fireTableDataChanged();
                    }
                }
            }
            FarmaUtility.moveFocusJTable(tblPatologicosFamiliares);
        }
    }
    
    private boolean validarFecha(String fecha){
        return validarFecha(fecha, false);
    }
    
    private boolean validarFecha(String fecha, boolean permiteMayorActual){
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
                if(!permiteMayorActual){
                    Calendar dateActual = Calendar.getInstance();
                    if(date.getTime().compareTo(dateActual.getTime()) == 1 )
                        valido = false;
                }
            }
        }
        return valido;
    }
    
    private void grabarAntecedente(){
        if(isModoVisual()){
            if (JConfirmDialog.rptaConfirmDialog(this, "¿Esta seguro de editar los antecedentes?")) {
                cerrarVentana(true);
            }
            return;
        }
        
        if (!JConfirmDialog.rptaConfirmDialog(this, "¿Esta seguro de grabar los antecedentes?")) 
            return;
        
        boolean guardo = guardarPanelFisiologicos();
        guardo = guardo && guardarPanelGenerales();
        guardo = guardo && guardarPanelGinecologicos();
        guardo = guardo && guardarPanelPatologicos();
        
        if(guardo){
            guardo = utility.grabarAntecedentePaciente(this, antecedentePaciente);
            if(guardo){
                beanPaciente.getHistoriaClinia().setBeanAntecedente(antecedentePaciente);
                beanPaciente.setCodLocalAntecedente(antecedentePaciente.getCodLocal());
                beanPaciente.setNroHCAntecedente(antecedentePaciente.getSecuencialHC());
                cerrarVentana(true);
            }
        }
    }
    
    private boolean guardarPanelFisiologicos(){
        ArrayList<BeanHCAntecedentesFisiologicos> lstPrenatales = obtenerSeleccionadosComboBox(cmbPrenatales, 31);
        ArrayList<BeanHCAntecedentesFisiologicos> lstPartos = obtenerSeleccionadosComboBox(cmbPartos, 32);
        ArrayList<BeanHCAntecedentesFisiologicos> lstInmunologicos = obtenerSeleccionadosComboBox(cmbInmunizaciones, 33);
        
        
        //VALIDARA SOLO EN CASO SE HA MENOR DE 5 AÑOS
        if(edadPaciente >= 0 && edadPaciente<=5){
            if(lstPrenatales.size()==0){
                FarmaUtility.showMessage(this, "Antecedentes: \nDebe seleccionar al menos una opción de Prenatales.", cmbPrenatales);
                return false;
            }
            if(lstPartos.size()==0){
                FarmaUtility.showMessage(this, "Antecedentes: \nDebe seleccionar al menos una opción de Partos.", cmbPartos);
                return false;
            }
            if(lstInmunologicos.size()==0){
                FarmaUtility.showMessage(this, "Antecedentes: \nDebe seleccionar al menos una opción de Inmunologicos.", cmbInmunizaciones);
                return false;
            }
        }
        
        lstPrenatales.addAll(lstPartos);
        lstPrenatales.addAll(lstInmunologicos);
        antecedentePaciente.setFisiologico(lstPrenatales);
        return true;
    }
    
    private boolean guardarPanelGenerales(){

        ArrayList<BeanHCAntecedentesFisiologicos> lstHabitos = obtenerSeleccionadosComboBox(cmbHabitosNocivos, 34);
        if(valorCampoEsNulo(txtMedicamentos)) return false;
        if(valorCampoEsNulo(txtRAM)) return false;
        
        if(edadPaciente>5){
            if(lstHabitos.size()==0){
                FarmaUtility.showMessage(this, "Antecedentes: \nDebe seleccionar al menos una opción de Habitos Nocivos.", cmbHabitosNocivos);
                return false;
            }
        }
        antecedentePaciente.getFisiologico().addAll(lstHabitos);

        BeanHCAntecedentesGenerales generales = new BeanHCAntecedentesGenerales();
        generales.setMedicamentos(getValorTextoNulo(txtMedicamentos));
        generales.setRam(getValorTextoNulo(txtRAM));
        generales.setOcupacionales(getValorTextoNulo(txtOcupacionales));
        antecedentePaciente.setGenerales(generales);
        return true;
    }
    
    private boolean guardarPanelGinecologicos(){
        BeanHCAntecedentesGinecologicos ginecologico = null;
        if(isSexoFemenino() && edadPaciente>9){
            int valor = 0;
            //int valor = txtEdadMenarquia.getInt();
            /*int valor = getValorIntNulo(txtEdadMenarquia);
            if(valor != Integer.MIN_VALUE){
                if(valor<0){
                    FarmaUtility.showMessage(this, "Antecedentes: \nDato de Edad Menarquia erroneo.", txtEdadMenarquia);
                    return false;
                }
            }*/
            //valor = txtCatamenial1.getInt();
            valor = getValorIntNulo(txtCatamenial1);
            if(valor != Integer.MIN_VALUE && (valor<26 || valor>34)){
                FarmaUtility.showMessage(this, "Antecedentes: \nValor de Ciclo Menstrual debe ser entre [26-34] días.", txtCatamenial1);
                return false;
            }
            
            valor = getValorIntNulo(txtCatamenial2);
            if(valor != Integer.MIN_VALUE && (valor<1 || valor>5)){
                FarmaUtility.showMessage(this, "Antecedentes: \nValor de Duración de Menstruación debe ser entre [1-5] días.", txtCatamenial2);
                return false;
            }
            
            valor =  getValorIntNulo(txtEdadRS);
            if(valor != Integer.MIN_VALUE && valor<10){
                FarmaUtility.showMessage(this, "Antecedentes: \nValor de Edad de Inicio de Actividad Sexual erronea .", txtEdadRS);
                return false;
            }
            
            String fecha = getValorTextoNulo(txtFechaFUR);
            if(fecha.trim().length()>0)
                if(!validarFecha(fecha)){
                    FarmaUtility.showMessage(this, "Antecedentes: \nFecha de Ultima Regla, erronea (no puede ser mayor a la fecha actual).", txtFechaFUR);
                    return false;
                }
            
            fecha = getValorTextoNulo(txtFechaFPP);
            if(fecha.trim().length()>0)
                if(!validarFecha(fecha, true)){
                    FarmaUtility.showMessage(this, "Antecedentes: \nFecha Programada de Parto erronea .", txtFechaFPP);
                    return false;
                }

            /*
            valor = txtNroGestaciones.getInt();
            if(valor<0){
                FarmaUtility.showMessage(this, "Antecedentes: \nValor de Nro de Gestaciones erronea .", txtNroGestaciones);
                return false;
            }*/
            
            fecha = getValorTextoNulo(txtFechaFUP);
            if(fecha.trim().length()>0)
                if(!validarFecha(fecha)){
                    FarmaUtility.showMessage(this, "Antecedentes: \nFecha Ultima de Parto erronea (no puede ser mayor a la actual).", txtFechaFUP);
                    return false;
                }
            
            /*if(getValorIntNulo(txtPariedad1) == Integer.MIN_VALUE || 
               getValorIntNulo(txtPariedad2) == Integer.MIN_VALUE || 
               getValorIntNulo(txtPariedad3) == Integer.MIN_VALUE || 
               getValorIntNulo(txtPariedad4) == Integer.MIN_VALUE ){
                   FarmaUtility.showMessage(this, "Antecedentes: \nValor de Paridad errónea .", txtPariedad1);
               }
            */
            ginecologico = new BeanHCAntecedentesGinecologicos();
            ginecologico.setEdadMenarquia(getValorIntNulo(txtEdadMenarquia));
            if(chkReglaRegular.isSelected())
                ginecologico.setIndReglaRegular("S");
            else
                ginecologico.setIndReglaRegular("N");
            ginecologico.setRcCicloMenstrual(getValorIntNulo(txtCatamenial1));
            ginecologico.setRcMenstruacion(getValorIntNulo(txtCatamenial2));
            ginecologico.setRs(getValorIntNulo(txtEdadRS));
            ginecologico.setFur(getValorTextoNulo(txtFechaFUR));
            ginecologico.setFpp(getValorTextoNulo(txtFechaFPP));
            if(chkDisminorrea.isSelected())
                ginecologico.setDisminorrea("S");
            else
                ginecologico.setDisminorrea("N");
            ginecologico.setNroGestaciones(getValorIntNulo(txtNroGestaciones));
            ginecologico.setFup(getValorTextoNulo(txtFechaFUP));
            ginecologico.setParidad(obtenerParidad());
            ginecologico.setNroCesareas(getValorIntNulo(txtNroCesareas));
            ginecologico.setPap(getValorTextoNulo(txtPAP));
            ginecologico.setMamografia(getValorTextoNulo(txtMamografia));
            ginecologico.setMac(getValorTextoNulo(txtMac));
            ginecologico.setOtros(getValorTextoNulo(txtOtros));
            
            if(ginecologico.getEdadMenarquia() == Integer.MIN_VALUE && ginecologico.getIndReglaRegular().equalsIgnoreCase("N") && 
               ginecologico.getRcCicloMenstrual() == Integer.MIN_VALUE && ginecologico.getRcMenstruacion()==Integer.MIN_VALUE &&
               ginecologico.getRs() == Integer.MIN_VALUE && ginecologico.getFur().trim().length()==0 && 
               ginecologico.getFpp().trim().length()==0 && "N".equalsIgnoreCase(ginecologico.getDisminorrea()) && 
               ginecologico.getNroGestaciones() == Integer.MIN_VALUE && ginecologico.getFup().trim().length()==0 &&
               ginecologico.getParidad().trim().length()==0 && ginecologico.getNroCesareas() == Integer.MIN_VALUE && 
               ginecologico.getPap().trim().length() == 0 && ginecologico.getMamografia().trim().length() == 0 &&
               ginecologico.getMac().trim().length() == 0 && ginecologico.getOtros().trim().length() == 0){
                   ginecologico = null;
               }
        }
        antecedentePaciente.setGinecologico(ginecologico);
        return true;
    }
    private String obtenerParidad(){
        int paridad1 = getValorIntNulo(txtPariedad1);
        int paridad2 = getValorIntNulo(txtPariedad2);
        int paridad3 = getValorIntNulo(txtPariedad3);
        int paridad4 = getValorIntNulo(txtPariedad4);
        if(paridad1 != Integer.MIN_VALUE && 
            paridad2 != Integer.MIN_VALUE &&
            paridad3 != Integer.MIN_VALUE &&
            paridad4 != Integer.MIN_VALUE) {
            return paridad1+"-"+paridad2+"-"+paridad3+"-"+paridad4;
        }else
            return "";
    }
    
    private void mostrarParidad(String texto){
        if(texto!=null && texto.trim().length()==11){
            String par1 = texto.substring(0,texto.indexOf("-"));
            texto = texto.substring(texto.indexOf("-")+1);
            String par2 = texto.substring(0,texto.indexOf("-"));
            texto = texto.substring(texto.indexOf("-")+1);
            String par3 = texto.substring(0,texto.indexOf("-"));
            texto = texto.substring(texto.indexOf("-")+1);
            String par4 = texto;
            try{
                txtPariedad1.setInt(Integer.parseInt(par1));
            }catch(Exception ex){
                txtPariedad1.setInt(0);
            }
            try{
                txtPariedad2.setInt(Integer.parseInt(par2));
            }catch(Exception ex){
                txtPariedad2.setInt(0);
            }
            try{
                txtPariedad3.setInt(Integer.parseInt(par3));
            }catch(Exception ex){
                txtPariedad3.setInt(0);
            }
            try{
                txtPariedad4.setInt(Integer.parseInt(par4));
            }catch(Exception ex){
                txtPariedad4.setInt(0);
            }
        }
    }
    
    private boolean guardarPanelPatologicos(){
        ArrayList<BeanHCAntecedentesPatologico> lstPatologicos = null;
        
        for(int i=0;i<tblPatologicos.getRowCount();i++){
            if(lstPatologicos == null)
                lstPatologicos = new ArrayList<BeanHCAntecedentesPatologico>();
            String codDiagnostico = FarmaUtility.getValueFieldArrayList(mdlTblPatologicos.data, i, 2);
            BeanHCAntecedentesPatologico patologico = new BeanHCAntecedentesPatologico();
            /*patologico.setCodGrupoCia(antecedentePaciente.getCodGrupoCia());
            patologico.setCodPaciente(antecedentePaciente.getCodPaciente());
            patologico.setSecuencialHC(antecedentePaciente.getSecuencialHC());*/
            patologico.setCodDiagnostico(codDiagnostico);
            patologico.setTipoPatologico("PA");
            lstPatologicos.add(patologico);
        }
        
        for(int i=0;i<tblPatologicosFamiliares.getRowCount();i++){
            if(lstPatologicos == null)
                lstPatologicos = new ArrayList<BeanHCAntecedentesPatologico>();
            String codDiagnostico = FarmaUtility.getValueFieldArrayList(mdlTblPatologicosFamiliares.data, i, 3);
            String nombrePariente = FarmaUtility.getValueFieldArrayList(mdlTblPatologicosFamiliares.data, i, 2);
            BeanHCAntecedentesPatologico patologico = new BeanHCAntecedentesPatologico();
            /*patologico.setCodGrupoCia(antecedentePaciente.getCodGrupoCia());
            patologico.setCodPaciente(antecedentePaciente.getCodPaciente());
            patologico.setSecuencialHC(antecedentePaciente.getSecuencialHC());*/
            patologico.setCodDiagnostico(codDiagnostico);
            patologico.setDescripcionPariente(nombrePariente);
            patologico.setTipoPatologico("FA");
            lstPatologicos.add(patologico);
        }
        antecedentePaciente.setPatologicos(lstPatologicos);
        return true;
    }
    
    private ArrayList<BeanHCAntecedentesFisiologicos> obtenerSeleccionadosComboBox(JComboWithCheck combo, int grupo){
        ArrayList<BeanHCAntecedentesFisiologicos> lista = new ArrayList<BeanHCAntecedentesFisiologicos>();
        ArrayList<OptionComboBox> lstOption = combo.getElementosSeleccionados();
        for(int k=0;k<lstOption.size();k++){
            OptionComboBox option = lstOption.get(k);
            BeanHCAntecedentesFisiologicos fisiologico = new BeanHCAntecedentesFisiologicos();
            /*fisiologico.setCodGrupoCia(antecedentePaciente.getCodGrupoCia());
            fisiologico.setCodLocal(antecedentePaciente.getCodLocal());
            fisiologico.setCodPaciente(antecedentePaciente.getCodPaciente());
            fisiologico.setSecuencialHC(antecedentePaciente.getSecuencialHC());*/
            fisiologico.setCodGrupo(grupo);
            if(grupo==34)
                fisiologico.setTipoFisiologico("GE");
            else
                fisiologico.setTipoFisiologico("FI");
            //fisiologico.setCodAnteFisiologico(Integer.valueOf(((BigDecimal)option.getCodigo()).intValue()));
            fisiologico.setCodAnteFisiologico(FarmaUtility.trunc((String)option.getCodigo()));
            if(option.isOpcionOtro())
                fisiologico.setDescAnteFisiologico(option.getLabel().substring(1, option.getLabel().trim().length()-1));
            lista.add(fisiologico);
        }

        return lista;
    }
    
    private void quitarAntecedentePatologico(){
        if(!isModoVisual() && (tbpPaneles.getSelectedComponent().equals(pnlPatologicos))){
            int filaSelect = tblPatologicos.getSelectedRow();
            if(filaSelect==-1){
                FarmaUtility.showMessage(this, "Debe Seleccionar una fila de la tabla de Antecedentes Patologicos.", tblPatologicos);
            }else{
                mdlTblPatologicos.data.remove(filaSelect);
                mdlTblPatologicos.fireTableDataChanged();
            }
        }
    }
    
    private void quitarAntecedentePatologicoFamiliares(){
        if(!isModoVisual() && (tbpPaneles.getSelectedComponent().equals(pnlPatologicos))){
            int filaSelect = tblPatologicosFamiliares.getSelectedRow();
            if(filaSelect==-1){
                FarmaUtility.showMessage(this, "Debe Seleccionar una fila de la tabla de Antecedentes Patologicos Familiares.", tblPatologicosFamiliares);
            }else{
                mdlTblPatologicosFamiliares.data.remove(filaSelect);
                mdlTblPatologicosFamiliares.fireTableDataChanged();
            }
        }
    }

    public boolean isSexoFemenino() {
        return "F".equalsIgnoreCase(beanPaciente.getSexo());
    }

    public boolean isModoVisual() {
        return modoVisual;
    }

    public void setModoVisual(boolean mostrarDatos) {
        this.modoVisual = mostrarDatos;
    }

    private void mostrarHistorialAntecedente(){
        if(isModoVisual()){
            DlgListadoAntecedentes dlg = new DlgListadoAntecedentes(myParentFrame, "Historial de Antecedentes", true);
            dlg.setBeanPaciente(beanPaciente);
            dlg.setCodPaciente(antecedentePaciente.getCodPaciente());
            dlg.setVisible(true);
        }
    }
    
    private int calcularEdad() {
        Date bornDate = obtenerFecha(beanPaciente.getFecNacimiento());
        if(bornDate==null)
            return -1;
        Calendar cal = Calendar.getInstance(); // current date
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH);
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(bornDate); // now born date
        int years = currYear - cal.get(Calendar.YEAR);
        int bornMonth = cal.get(Calendar.MONTH);
        if (bornMonth == currMonth) { // same month
            return cal.get(Calendar.DAY_OF_MONTH) <= currDay ? years : years - 1;
        } else {
            return bornMonth < currMonth ? years : years - 1;
        }
    }
    
    private Date obtenerFecha(String fecha){
        if(fecha==null || (fecha!=null && fecha.trim().length()!=10)){
            return null;
        }
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
    
    private void mostrarTabPanel() {
        switch(tbpPaneles.getSelectedIndex()){
            case 0: FarmaUtility.moveFocus(cmbPrenatales);
                break;
            case 1: 
                if((tbpPaneles.getSelectedComponent().equals(pnlPatologicos)))
                    FarmaUtility.moveFocus(btnTitlePatologicos);
                else
                    FarmaUtility.moveFocus(txtEdadMenarquia);
                break;
            case 2: FarmaUtility.moveFocus(btnTitlePatologicos);
                break;
        }
    }

    public boolean isModoVisualHistorico() {
        return modoVisualHistorico;
    }

    public void setModoVisualHistorico(boolean modoVisualHistorico) {
        this.modoVisualHistorico = modoVisualHistorico;
    }
    
    private void funcionSalir(){
        if(!isModoVisual()){
            if(JConfirmDialog.rptaConfirmDialog(this, "Si cierra la ventana se borrará los datos registrados\n ¿Desea Salir?"))
                cerrarVentana(false);
        }else
            cerrarVentana(false);
    }

    class HiloCargaListaDiagnostico extends Thread{
        @Override
        public void run() {
            //lstProductosReceta = utility.obtenerListadoProductos();
            if(lstDiagnostico == null){
                lstDiagnostico = new ArrayList<ArrayList<String>>();
                lstDiagnostico = utility.obtenerListaDiagnostico();
            }
        }
    }
    
    class ActionTransferFocus implements ActionListener{
        private Component objNext;
        
        public ActionTransferFocus(Component objNext){
            this.objNext = objNext;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            objNext.requestFocus();
        }
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
            }else if(UtilityPtoVenta.verificaVK_F1(e)){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(!((JComboWithCheck)e.getSource()).isDesplegadoMenu())
                        mostrarDiagnostico();
                }else
                    mostrarDiagnostico();
            }else if(e.getKeyCode() == KeyEvent.VK_F2){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(!((JComboWithCheck)e.getSource()).isDesplegadoMenu())
                        quitarAntecedentePatologico();
                }else
                    quitarAntecedentePatologico();
            }else if(e.getKeyCode() == KeyEvent.VK_F3){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(!((JComboWithCheck)e.getSource()).isDesplegadoMenu())
                        mostrarDiagnosticoFamiliares();
                }else
                    mostrarDiagnosticoFamiliares();
            }else if(e.getKeyCode() == KeyEvent.VK_F4){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(!((JComboWithCheck)e.getSource()).isDesplegadoMenu())
                        quitarAntecedentePatologicoFamiliares();
                }else
                    quitarAntecedentePatologicoFamiliares();
            }else if(e.getKeyCode() == KeyEvent.VK_F5 && lblF5.isVisible()){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(!((JComboWithCheck)e.getSource()).isDesplegadoMenu())
                        mostrarHistorialAntecedente();
                }else
                    mostrarHistorialAntecedente();
            }else if(e.getKeyCode() == KeyEvent.VK_F6){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(!((JComboWithCheck)e.getSource()).isDesplegadoMenu())
                        tbpPaneles.requestFocus();
                }else
                    tbpPaneles.requestFocus();
                
            }else if(UtilityPtoVenta.verificaVK_F11(e) && lblF11.isVisible()){
                if(e.getSource() instanceof JComboWithCheck ){
                    if(!((JComboWithCheck)e.getSource()).isDesplegadoMenu())
                        grabarAntecedente();
                }else
                    grabarAntecedente();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}