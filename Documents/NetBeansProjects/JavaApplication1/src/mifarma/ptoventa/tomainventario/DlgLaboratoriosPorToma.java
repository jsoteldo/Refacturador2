package mifarma.ptoventa.tomainventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.tomainventario.reference.ConstantsTomaInv;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.FarmaPrintService;
import mifarma.ptoventa.tomainventario.reference.FarmaVariablesdos;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgLaboratoriosPorToma extends JDialog {
    FarmaTableModel tableModelLaboratorioToma;
    FarmaTableModel tableModelDiferenciasConsolidado;
    private static final Logger log = LoggerFactory.getLogger(DlgLaboratoriosPorToma.class);
    ArrayList lista = new ArrayList();
    ArrayList listaImprimir = new ArrayList();
    public String cantPdas;
    private boolean xLaboratorio = false;
    public static int cantPdaUp = 0;
    private double totalInicial, totalFinal;
    private int saltosLaboratorio = 0;
    private int saltosLaboratorioSig = 0;
    private int acumuladoImpresion = 0;
    private int h = 0;
    


    
    
    

    FarmaTableModel tableModelDiferenciasProductos;

    Frame parentFrame;
    private boolean esMensual = false;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanel jContentPane = new JPanel();

    private JPanelHeader jPanelHeader1 = new JPanelHeader();

    private JPanelTitle jPanelTitle1 = new JPanelTitle();

    private JButtonLabel btnLaboratorio = new JButtonLabel();

    private JTextFieldSanSerif txtLaboratorio = new JTextFieldSanSerif();

    private JScrollPane jScrollPane1 = new JScrollPane();

    private JTable tblRelacionLaboratoriosXToma = new JTable();

    private JButtonLabel btnRelacionLaboratoriosToma = new JButtonLabel();

    private JLabelFunction lblEnter = new JLabelFunction();

    private JLabelFunction lblF12 = new JLabelFunction();

    private JLabelFunction lblEscape = new JLabelFunction();

    private boolean isHistorico = false;


    /**
     * Variable de Filtro Estado
     * @author  dubilluz
     * @since  08.01.2008
     */
    private String filtroEstado = "";
    private JLabelFunction lblF9 = new JLabelFunction();
    private JLabelFunction lblF11EliminaFiltro = new JLabelFunction();
    private JLabelFunction jLabelFunction1 = new JLabelFunction();
    private JLabelFunction lblF4 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private BorderLayout borderLayout2 = new BorderLayout();
    private JLabelFunction lblF3 = new JLabelFunction();
    private JLabelFunction lblF7 = new JLabelFunction();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabel lblF8 = new JLabel();
    private JLabel lblF6 = new JLabel();
    private JTable tblRelacionDiferenciasProductos = new JTable();
    private JPanel pnlPieLaboratoriosProceso = new JPanel();
    private JLabel lblTotalLaboratoriosT = new JLabel();
    private JLabel lblTotalLaboratorios = new JLabel();
    private JLabel lblCantPdaT = new JLabel();
    public JLabel lblCantPda = new JLabel();
    public final static String PATH_PDANET_API = "C:/PdaNetApi/PdaNetApi.exe";
    private JLabelOrange lblF6_1t = new JLabelOrange();
    private JLabelFunction lblF10 = new JLabelFunction();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgLaboratoriosPorToma() {
        this(null, "", false);
    }

    public DlgLaboratoriosPorToma(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            initialize();
            cargaListaDiferenciasProductos();
            obtieneCantPdaCargadoTomaInv();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public DlgLaboratoriosPorToma(Frame parent, String title, boolean modal, boolean isHistorico) {
        this(parent, title, modal);
        this.isHistorico = isHistorico;

    }

    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(615, 532));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Laboratorios por Toma de Inventario");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jPanelHeader1.setBounds(new Rectangle(10, 10, 580, 30));
        jPanelTitle1.setBounds(new Rectangle(10, 45, 580, 20));
        btnLaboratorio.setText("Laboratorios :");
        btnLaboratorio.setBounds(new Rectangle(15, 5, 85, 20));
        btnLaboratorio.setMnemonic('l');
        btnLaboratorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLaboratorio_actionPerformed(e);
            }
        });
        txtLaboratorio.setBounds(new Rectangle(105, 5, 450, 20));
        txtLaboratorio.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtLaboratorio_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtLaboratorio_keyReleased(e);
            }
        });
        jScrollPane1.setBounds(new Rectangle(10, 85, 585, 225));


        tblRelacionLaboratoriosXToma.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                tblRelacionLaboratoriosXToma_mouseReleased(e);
            }
        });
        btnRelacionLaboratoriosToma.setText("Relacion de Laboratorios por Toma");
        btnRelacionLaboratoriosToma.setBounds(new Rectangle(10, 0, 215, 20));
        btnRelacionLaboratoriosToma.setMnemonic('r');
        btnRelacionLaboratoriosToma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionLaboratoriosToma_actionPerformed(e);
            }
        });
        lblEnter.setBounds(new Rectangle(10, 345, 170, 20));
        lblEnter.setText("[ ENTER ] Ver Detalle");
        lblEnter.setOpaque(false);
        lblEnter.setHorizontalAlignment(SwingConstants.LEFT);
        lblEnter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEnter.setForeground(new Color(32, 105, 29));
        lblEnter.setFont(new Font("SansSerif", 1, 11));
        lblF12.setBounds(new Rectangle(370, 335, 110, 20));
        lblF12.setText("[ F12 ] Imprimir");
        lblF12.setVisible(false);
        lblF12.setFont(new Font("SansSerif", 1, 11));
        lblEscape.setBounds(new Rectangle(465, 470, 120, 20));
        lblEscape.setText("[ ESCAPE ] Cerrar");
        lblEscape.setOpaque(false);
        lblEscape.setHorizontalAlignment(SwingConstants.LEFT);
        lblEscape.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEscape.setFont(new Font("SansSerif", 1, 11));
        lblEscape.setForeground(new Color(32, 105, 29));
        lblF9.setBounds(new Rectangle(395, 375, 185, 20));
        lblF9.setText("[ F9 ] Guardar Diferencias");
        lblF9.setOpaque(false);
        lblF9.setHorizontalAlignment(SwingConstants.LEFT);
        lblF9.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF9.setFont(new Font("SansSerif", 1, 11));
        lblF9.setForeground(new Color(32, 105, 29));
        lblF11EliminaFiltro.setBounds(new Rectangle(245, 335, 115, 20));
        lblF11EliminaFiltro.setText("[ F11 ] Quitar Filtro");
        lblF11EliminaFiltro.setVisible(false);
        jLabelFunction1.setBounds(new Rectangle(110, 505, 170, 20));
        jLabelFunction1.setText("[ F12 ] Ingreso C\u00f3digo Barra");

        jLabelFunction1.setOpaque(false);
        jLabelFunction1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabelFunction1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelFunction1.setFont(new Font("SansSerif", 1, 11));
        jLabelFunction1.setForeground(new Color(43, 141, 39));
        lblF4.setBounds(new Rectangle(10, 405, 170, 20));
        lblF4.setText("[ F4 ] Completar Ceros");
        lblF4.setOpaque(false);
        lblF4.setHorizontalAlignment(SwingConstants.LEFT);
        lblF4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF4.setForeground(new Color(32, 105, 29));
        lblF4.setFont(new Font("SansSerif", 1, 11));
        lblF2.setBounds(new Rectangle(10, 375, 170, 20));
        lblF2.setText("[ F2 ] Cargar Inventario PDA");

        lblF2.setOpaque(false);
        lblF2.setHorizontalAlignment(SwingConstants.LEFT);
        lblF2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF2.setFont(new Font("SansSerif", 1, 11));
        lblF2.setForeground(new Color(32, 105, 29));
        lblF3.setBounds(new Rectangle(395, 405, 180, 20));
        lblF3.setText("[ F3] Resumen en Costo");
        lblF3.setOpaque(false);
        lblF3.setHorizontalAlignment(SwingConstants.LEFT);
        lblF3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF3.setFont(new Font("SansSerif", 1, 11));
        lblF3.setForeground(new Color(32, 105, 29));
        lblF7.setBounds(new Rectangle(205, 405, 165, 20));
        lblF7.setText("[ F7 ] Resumen Inventario");
        lblF7.setOpaque(false);
        lblF7.setHorizontalAlignment(SwingConstants.LEFT);
        lblF7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF7.setFont(new Font("SansSerif", 1, 11));
        lblF7.setForeground(new Color(32, 105, 29));
        lblF5.setBounds(new Rectangle(10, 435, 180, 20));
        lblF5.setText("[ F5 ] Guardar Primer Conteo");
        lblF5.setOpaque(false);
        lblF5.setHorizontalAlignment(SwingConstants.LEFT);
        lblF5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF5.setForeground(new Color(32, 105, 29));
        lblF5.setFont(new Font("SansSerif", 1, 11));
        lblF11.setBounds(new Rectangle(395, 345, 195, 20));
        lblF11.setText("[ F11 ] Guardar Segundo Conteo");
        lblF11.setOpaque(false);
        lblF11.setHorizontalAlignment(SwingConstants.LEFT);
        lblF11.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF11.setFont(new Font("SansSerif", 1, 11));
        lblF11.setForeground(new Color(32, 105, 29));
        lblF8.setText("[ F8 ] Reporte Diferencias");
        lblF8.setBounds(new Rectangle(205, 375, 165, 20));
        lblF8.setBackground(new Color(212, 208, 200));
        lblF8.setHorizontalAlignment(SwingConstants.LEFT);
        lblF8.setFont(new Font("SansSerif", 1, 11));
        lblF8.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        lblF8.setForeground(new Color(32, 105, 29));
        lblF6.setText("[ F6 ] Reporte Diferencias");
        lblF6.setBounds(new Rectangle(205, 435, 175, 20));
        lblF6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF6.setHorizontalAlignment(SwingConstants.LEFT);
        lblF6.setBackground(new Color(212, 208, 200));
        lblF6.setFont(new Font("SansSerif", 1, 11));

        lblF6.setForeground(new Color(32, 105, 29));
        tblRelacionDiferenciasProductos.setBounds(new Rectangle(15, 320, 5, 0));
        pnlPieLaboratoriosProceso.setBounds(new Rectangle(10, 310, 585, 25));
        pnlPieLaboratoriosProceso.setBackground(new Color(255, 130, 14));
        pnlPieLaboratoriosProceso.setLayout(null);
        lblTotalLaboratoriosT.setBounds(new Rectangle(10, 0, 135, 25));
        lblTotalLaboratoriosT.setText("Total de Laboratorios:");
        lblTotalLaboratoriosT.setFont(new Font("SansSerif", 1, 11));
        lblTotalLaboratoriosT.setForeground(SystemColor.window);
        lblTotalLaboratorios.setBounds(new Rectangle(140, 0, 55, 25));
        lblTotalLaboratorios.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTotalLaboratorios.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalLaboratorios.setFont(new Font("SansSerif", 1, 11));
        lblTotalLaboratorios.setForeground(SystemColor.window);
        lblCantPdaT.setText("N\u00b0 PDA's cargados:");
        lblCantPdaT.setBounds(new Rectangle(405, 0, 115, 25));
        lblCantPdaT.setFont(new Font("SansSerif", 1, 11));
        lblCantPdaT.setForeground(SystemColor.window);
        lblCantPdaT.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCantPda.setText("0");
        lblCantPda.setFont(new Font("SansSerif", 1, 11));
        lblCantPda.setBounds(new Rectangle(520, 0, 40, 25));
        lblCantPda.setForeground(SystemColor.window);
        lblCantPda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblF6_1t.setText("Valorizadas");
        lblF6_1t.setBounds(new Rectangle(235, 455, 130, 15));
        lblF6_1t.setForeground(new Color(32, 105, 29));
        lblF6_1t.setFont(new Font("SansSerif", 1, 11));
        lblF10.setBounds(new Rectangle(205, 346, 117, 19));
        lblF10.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF10.setOpaque(false);
        lblF10.setForeground(new Color(32, 105, 29));
        lblF10.setText("[ F10 ] Ver Todos");
        lblF10.setFont(new Font("SansSerif", 1, 11));
        lblF10.setHorizontalTextPosition(SwingConstants.CENTER);
        lblF10.setHorizontalAlignment(SwingConstants.LEFT);
        jPanelHeader1.add(txtLaboratorio, null);
        jPanelHeader1.add(btnLaboratorio, null);
        jContentPane.add(lblF10, null);
        jContentPane.add(lblF6_1t, null);
        jContentPane.add(tblRelacionDiferenciasProductos, null);
        jContentPane.add(lblF6, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(lblF5, null);
        jContentPane.add(lblF7, null);
        jContentPane.add(lblF3, null);
        jContentPane.add(lblF2, null);
        jContentPane.add(lblF4, null);
        jContentPane.add(jLabelFunction1, null);
        jContentPane.add(lblF9, null);
        jContentPane.add(lblEscape, null);
        jContentPane.add(lblF12, null);
        jContentPane.add(lblEnter, null);
        jScrollPane1.getViewport().add(tblRelacionLaboratoriosXToma, null);
        jContentPane.add(jScrollPane1, null);
        jPanelTitle1.add(btnRelacionLaboratoriosToma, null);
        jContentPane.add(jPanelTitle1, null);
        jContentPane.add(jPanelHeader1, null);
        jContentPane.add(lblF11EliminaFiltro, null);
        pnlPieLaboratoriosProceso.add(lblCantPda, null);
        pnlPieLaboratoriosProceso.add(lblCantPdaT, null);
        pnlPieLaboratoriosProceso.add(lblTotalLaboratorios, null);
        pnlPieLaboratoriosProceso.add(lblTotalLaboratoriosT, null);
        jContentPane.add(pnlPieLaboratoriosProceso, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariablesdos.vAceptar = false;
        initTableLaboratoriosToma();
        if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
            lblF3.setVisible(false);
        } else {
            lblF3.setVisible(true);
        }
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_INVENTARIADOR)) {
            lblF2.setEnabled(false);
            lblF4.setEnabled(false);
            lblF5.setEnabled(false);
            lblF8.setEnabled(false);
            lblF7.setEnabled(false);
            lblF6.setEnabled(false);
            lblF6_1t.setEnabled(false);
            lblF11.setEnabled(false);
            lblF9.setEnabled(false);
            lblF3.setEnabled(false);
        }
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************


    private void cargaListaDiferenciasProductos() {
        try {
            // DBTomaInv.getListaProdsDiferenciasTotales(tableModelDiferenciasProductos);
            DBTomaInv.getListaProdsDiferenciasTotales(lista, "");
            DBTomaInv.listaDiferenciasConsolidadoExport(tableModelDiferenciasConsolidado, VariablesTomaInv.vSecToma);

            // tableModelDiferenciasProductos.data = lista;


        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener la lista de diferencias :\n" +
                    sql.getMessage(), null);
        }
    }

    private void initTableLaboratoriosToma() {
        tableModelLaboratorioToma =
                new FarmaTableModel(ConstantsTomaInv.columnsLaboratoriosToma, ConstantsTomaInv.defaultValuesLaboratoriosToma,
                                    0);
        tableModelDiferenciasConsolidado =
                new FarmaTableModel(ConstantsTomaInv.columnsListaDiferenciasConsolidado, ConstantsTomaInv.defaultValuesListaDiferenciasConsolidado,
                                    0);
        FarmaUtility.initSimpleList(tblRelacionLaboratoriosXToma, tableModelLaboratorioToma,
                                    ConstantsTomaInv.columnsLaboratoriosToma);
        FarmaUtility.initSimpleList(tblRelacionDiferenciasProductos, tableModelDiferenciasConsolidado,
                                    ConstantsTomaInv.columnsListaDiferenciasConsolidado);
        cargaLaboratoriosToma();
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    public static double getDecimalNumber(String pDecimal, int pDecimales) {
        double decimalNumber = 0.00;
        try {
            DecimalFormat formatDecimal = new DecimalFormat("###,##0." + caracterIzquierda("", pDecimales, "0"));
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();

            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            formatDecimal.setDecimalFormatSymbols(symbols);
            decimalNumber = formatDecimal.parse(pDecimal).doubleValue();
        } catch (ParseException errParse) {
            //            System.out.print(pDecimal + " -> " + errParse.getMessage());
            //Inicio ID:010
            log.error("No se puede parsear el valor " + pDecimal + " con " + pDecimales + "decimales");
            //Fin ID:010
            //Inicio ID:011
            //errParse.printStackTrace();
            //Fin ID:011
        }
        return decimalNumber;
    }

    public static String caracterIzquierda(int parmint, int parmLen, String parmCaracter) {
        return caracterIzquierda(String.valueOf(parmint), parmLen, parmCaracter);
    }


    public static String caracterIzquierda(String parmString, int parmLen, String parmCaracter) {

        String tempString = parmString;

        if (tempString.length() > parmLen)
            tempString = tempString.substring(tempString.length() - parmLen, tempString.length());
        else {
            while (tempString.length() < parmLen)
                tempString = parmCaracter + tempString;
        }

        return tempString;

    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtLaboratorio);
        FarmaUtility.setearPrimerRegistro(tblRelacionLaboratoriosXToma, txtLaboratorio, 1);
    }

    private void txtLaboratorio_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblRelacionLaboratoriosXToma, txtLaboratorio, 1);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblRelacionLaboratoriosXToma.getSelectedRow() >= 0) {
                if (!(FarmaUtility.findTextInJTable(tblRelacionLaboratoriosXToma, txtLaboratorio.getText().trim(), 0,
                                                    1))) {
                    FarmaUtility.showMessage(this, "Laboratorio No Encontrado según Criterio de Búsqueda !!!",
                                             txtLaboratorio);
                    return;
                }
            }
            if (tieneRegistroSeleccionado(tblRelacionLaboratoriosXToma)) {
                VariablesTomaInv.vLaboratorioXToma = true;
                VariablesTomaInv.vDiferenciasXToma = true;
                cargarRegistroSeleccionado();
                DlgProductosTotales dlgProductosPorToma =
                    new DlgProductosTotales(parentFrame, "Lista de Laboratorio por Toma Inventario", true,
                                            this.isHistorico);
                //DlgProductosPorToma dlgProductosPorToma = new DlgProductosPorToma(parentFrame, "", true);
                dlgProductosPorToma.lblLaboratorio.setText(VariablesTomaInv.vNomLab.trim());
                dlgProductosPorToma.setVisible(true);
                VariablesTomaInv.vLaboratorioXToma = false;
                ///listara lab para actualizar los estados
                //08.01.2008 dubilluz modificacion
                //cargaLabFiltrados();
            }
        }
        chkKeyPressed(e);

        if (UtilityPtoVenta.verificaVK_F12(e) && jLabelFunction1.isEnabled()) {
            ingresoCodigoBarra();
        }
    }

    private void btnRelacionLaboratoriosToma_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblRelacionLaboratoriosXToma);
    }

    private void btnLaboratorio_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtLaboratorio);
    }

    private void txtLaboratorio_keyReleased(KeyEvent e) {
        chkKeyReleased(e);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************


    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_F6 && lblF6.isEnabled()) {            
            dlgpaginado();
        } else if (e.getKeyCode() == KeyEvent.VK_F9 && lblF9.isEnabled()) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                //SLEYVA-INICIO-2019
                try {
                    DBTomaInv.listaDiferenciasExport(tableModelDiferenciasConsolidado,
                                                                VariablesTomaInv.vSecToma);

                    if (tableModelDiferenciasConsolidado.getRowCount() > 0) {

                        String pattern = "yyyyMMdd";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                        String date = "";

                        date =
simpleDateFormat.format(FarmaUtility.getStringToDate(FarmaSearch.getFechaHoraBD(1), "dd/MM/yyyy"));

                        String nombreSugerido =
                            FarmaVariables.vCodLocal + "_" + FarmaVariables.vDescLocal + "_Diferencia_" + date;
                        String indGuardar = "N";

                        String userNamePc = System.getProperty("user.name");
                        File lfFile = new File("C:\\Users\\" + userNamePc + "\\Desktop\\");

                        JFileChooser filechooser = new JFileChooser(lfFile);
                        filechooser.setDialogTitle("Seleccione el directorio destino");
                        filechooser.setSelectedFile(new File(nombreSugerido));
                        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        filechooser.setFileFilter(new FileFilter() {
                            public boolean accept(final File f) {
                                return f.isDirectory() || f.getAbsolutePath().endsWith(".xls");
                            }

                            public String getDescription() {
                                return "Excel files (*.xls)";
                            }
                        });

                        if (filechooser.showSaveDialog(this.parentFrame) != JFileChooser.APPROVE_OPTION)
                            return;

                        File fileChoosen = filechooser.getSelectedFile();
                        String ruta = fileChoosen.getAbsolutePath() + ".xls";

                        DlgProcesarTomaInventario dlgProcesarTomaInventario =
                            new DlgProcesarTomaInventario(parentFrame, "", true);
                        dlgProcesarTomaInventario.setRuta(ruta);
                        dlgProcesarTomaInventario.setTblRelacionDiferenciasProductos(tblRelacionDiferenciasProductos);
                        dlgProcesarTomaInventario.mostrar();
                        indGuardar = dlgProcesarTomaInventario.getIndGuardar();
                        if (indGuardar.equals(FarmaConstants.INDICADOR_S)) {
                            FarmaUtility.showMessage(this, "Se guardo con exito el archivo", txtLaboratorio);
                        }

                    } else {
                        FarmaUtility.showMessage(this, "No hay diferencias para exportar.", null);
                    }
                } catch (SQLException f) {
                    f.printStackTrace();
                }

                //SLEYVA-FIN-2019
            } else
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción", null);
        }


        /**
     * Añadido : Filtro por : Usuario y Estado
     * @author : dubilluz
     * @since  : 08.01.2008
     */
        else if (UtilityPtoVenta.verificaVK_F10(e)) {
            DlgProductosTotales proTotal =
                new DlgProductosTotales(parentFrame, "Lista de Laboratorio por Toma Inventario", true,
                                        this.isHistorico);
            VariablesTomaInv.vDiferenciasXToma = false;
            proTotal.lblLaboratorio.setText("TODOS");
            proTotal.setVisible(true);

        } else if (UtilityPtoVenta.verificaVK_F11(e) && lblF11.isEnabled()) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {

                guardaConteo("2do");
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F5 && lblF5.isEnabled()) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {

                guardaConteo("1er");
            }

        } else if (e.getKeyCode() == KeyEvent.VK_F7 && lblF7.isEnabled()) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                if (tieneRegistroSeleccionado(tblRelacionLaboratoriosXToma)) {
                    cargarRegistroSeleccionado();
                    DlgConsolidadoToma dlgConsolidadoToma =
                        new DlgConsolidadoToma(parentFrame, "Resumen Inventario", true, "V", isHistorico);
                    dlgConsolidadoToma.setVisible(true);
                }
            } else
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción",
                                         tblRelacionLaboratoriosXToma);
        } else if (e.getKeyCode() == KeyEvent.VK_F8 && lblF8.isEnabled()) {
            imprimirDiferenciasSinValores();
            // verDiferencias();
        }

        else if (e.getKeyCode() == KeyEvent.VK_F2 && lblF2.isEnabled() && !isHistorico) {


            log.info("Presione el F2");
            Cargando carga = new Cargando(parentFrame, "", true);

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                String strSI = "Si";
                String strNO = "No";
                Object[] options = { strSI, strNO };
                int rptaDialogo =
                    JOptionPane.showOptionDialog(this, "Desea realizar la carga de Datos del PDA", "Mensaje del Sistema",
                                                 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                                 options, strNO);
                if (rptaDialogo == JOptionPane.YES_OPTION) {

                    Cargando.estado = "L";
                    carga.mostrar();

                    switch(carga.getMessage()){
                    case "PED":
                        JOptionPane.showMessageDialog(this, "El PDA no ha exportado los datos", "Mensaje del Sistema",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        break;
                    
                    case "SSD":
                        JOptionPane.showMessageDialog(this, "Información procesada correctamente.", "Mensaje del Sistema",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        break;
                    
                    case "NSD":
                        JOptionPane.showMessageDialog(this, "No se encuentran datos para exportar", "Mensaje del Sistema",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        break;
                        
                    default:
                        break;
                    }
                    
                    obtieneCantPdaCargadoTomaInv2();

                }

            }
        }

        else if (e.getKeyCode() == KeyEvent.VK_F3 && lblF3.isVisible()) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                if (tieneRegistroSeleccionado(tblRelacionLaboratoriosXToma)) {
                    cargarRegistroSeleccionado();
                    DlgConsolidadoToma dlgConsolidadoToma =
                        new DlgConsolidadoToma(parentFrame, "Resumen en Costo", true, "P", isHistorico);
                    dlgConsolidadoToma.setVisible(true);
                }
            } else
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción",
                                         tblRelacionLaboratoriosXToma);
        } else if (e.getKeyCode() == KeyEvent.VK_F4 && lblF4.isEnabled() && !isHistorico) {
            completarConCeros();
        }

    }

    public void renombrarFileInventario(String dire) {
        File fileInventario = new File(dire);
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {

            if (!new File(fileInventario.getParent() + "old_txt").exists()) {
                new File(fileInventario.getParent() + "old_txt").mkdirs();
            }
            String pathMove = "C:\\old_txt\\productoInventario_" + hourFormat.format(date) + ".txt";
            Path origenPath = FileSystems.getDefault().getPath(dire);
            Path destinoPath = FileSystems.getDefault().getPath(pathMove);
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void obtieneCantPdaCargadoTomaInv2() {
        try {
            VariablesTomaInv.cantPdaUp = DBTomaInv.getCantPdaCargadoTomaInv();
            if (VariablesTomaInv.cantPdaUp <= 0) {
                cantPdas = "0";
            } else {
                cantPdas = String.valueOf(VariablesTomaInv.cantPdaUp);
            }
            this.lblCantPda.setText(cantPdas);
        } catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
        }
    }

    public boolean copyFileInDevice(String oldFileName, String newFileName) {
        boolean estado = false;
        String[] commands = { PATH_PDANET_API, "copiar", oldFileName, newFileName };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                    if (stdError.readLine() != null) {
                        System.err.println("El archivo " + oldFileName + " no existe");
                        estado = false;
                    } else if (stdInput.readLine() != null) {
                        System.out.println("El archivo " + oldFileName + " fue encontrado");
                        estado = true;
                    }
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return estado;
    }

    void imprimirDiferenciasSinValores() { // F8

        if (JOptionPane.showConfirmDialog(this, "Desea imprimir el reporte de diferencias según Toma de Inventario?",
                                          "Confirmar Impresión", JOptionPane.YES_NO_OPTION) !=
            JOptionPane.YES_OPTION) {
            return;
        }
        
        FarmaVariablesdos.reporteDiferencias = true;

        DlgFiltroEstadoItems dlgOrdenar = new DlgFiltroEstadoItems(parentFrame, "Ordenar", true);
        dlgOrdenar.pnlFiltro1.setVisible(false);
        dlgOrdenar.txtMayoresDiferencias.setText("");
        dlgOrdenar.setTitleBorder("Seleccione el orden");
        dlgOrdenar.cmbCampo.removeAllItems();
        dlgOrdenar.cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporLaboratorio);
        dlgOrdenar.cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporProducto); 
        
        if (dlgOrdenar.optPagina.isSelected())
        {        
        FarmaVariablesdos.pagInicio = dlgOrdenar.txtPagIni.getText().trim();
        FarmaVariablesdos.pagFin = dlgOrdenar.txtPagFin.getText().trim();
        
       
          
        
        }
        else {
                FarmaVariablesdos.pagInicio = "";
                FarmaVariablesdos.pagFin ="";
            }
        FarmaVariablesdos.cantRegistros =  dlgOrdenar.txtMayoresDiferencias.getText().trim();  
        
        dlgOrdenar.optFiltrado.setEnabled(false);
     
        dlgOrdenar.setVisible(true);
        
       
       
        
        FarmaVariablesdos.reporteDiferencias = false;
  
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA))
        {
            FarmaVariablesdos.vRol= "A";
        } else {
            FarmaVariablesdos.vRol= "Q";
        }
        if (FarmaVariables.vAceptar) {
            if (dlgOrdenar.cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporLaboratorio)) {
                FarmaVariablesdos.estadoCmb = "LAB";
            } else if (dlgOrdenar.cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporProducto)) {
                FarmaVariablesdos.estadoCmb = "PRD";
            } else if (dlgOrdenar.cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporCantidad)) {
                FarmaVariablesdos.estadoCmb = "CNT";
            } else if (dlgOrdenar.cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio)) {
                FarmaVariablesdos.estadoCmb = "R";
            }
        
            listaImprimir.clear();
        
            if (dlgOrdenar.rbnPreCosto.isSelected() && dlgOrdenar.rbnPreVenta.isVisible() && dlgOrdenar.pnlFiltro1.isVisible() ){
                DlgConsolidadoToma dlgConsolidadoToma =
                    new DlgConsolidadoToma(parentFrame, "Resumen Inventario", true, "P");
                totalInicial = dlgConsolidadoToma.getTotalInicial();
                calculaDiferencia();  
            }else if (dlgOrdenar.rbnPreVenta.isSelected() && dlgOrdenar.rbnPreVenta.isVisible() && dlgOrdenar.pnlFiltro1.isVisible()){
                DlgConsolidadoToma dlgConsolidadoToma =
                    new DlgConsolidadoToma(parentFrame, "Resumen Inventario", true, "V");
                totalInicial = dlgConsolidadoToma.getTotalInicial();
                calculaDiferencia();
            } 

            try {
                //DBTomaInv.getListaProdsDiferenciasTotales(listaImprimir, FarmaVariablesdos.estadoCmb);
                
                DBTomaInv.getListaImprimirTomaInv(listaImprimir, FarmaVariablesdos.estadoCmb);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            String filtro = dlgOrdenar.getFiltro();
            int cantRegistros = dlgOrdenar.getCantRegistros();
           // int numPagIni = dlgOrdenar.getCantPagIni();
            int numPagIni = Integer.parseInt(dlgOrdenar.txtPagIni.getText().trim());		
            int numPagFin = dlgOrdenar.getCantPagFin();
            FarmaPrintService.numeroPagina = dlgOrdenar.txtPagIni.getText().trim();
            
            
            boolean varRadioPagina = dlgOrdenar.getRadioPagina();

            //FarmaPrintService vPrint = new FarmaPrintService(66, "//10.18.6.241//reportes", true);
            FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);

            if (!varRadioPagina) {
                vPrint.setPageNumber(1);
            } else {
                vPrint.setPageNumber(numPagIni);
            }
            if (!vPrint.startPrintService()) {
                FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", null);
                return;
            }
            try {
                String labNew = "", labOld = "";


                String textoImpresion = "";
                String ordenadoPor = "";


                switch (FarmaVariablesdos.estadoCmb) {
                case "LAB":
                    ordenadoPor = " (Ordenado por Laboratorio)";
                    break;
                case "PRD":
                    ordenadoPor = " (Ordenado por Producto)";
                    break;
                case "UNI":
                    ordenadoPor = " (Ordenado por Unidad)";
                    break;
                case "CNT":
                    ordenadoPor = " (Ordenado por Cantidad)";
                    break;
                case "R":
                    ordenadoPor = " (Ordenado por Precio)";
                    break;
                
                    case "CTO":
                        ordenadoPor = " (Ordenado por Precio)";
                        break;
                
                    case "VTA":
                        ordenadoPor = " (Ordenado por Precio)";
                        break;
                default:
                    ordenadoPor = " (Sin Orden Especificado)";
                }

                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                    textoImpresion = "REPORTE DE DIFERENCIAS DE PRODUCTOS " + ordenadoPor;
                } else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                    textoImpresion = "REPORTE DE DIFERENCIAS DE PRODUCTOS " + ordenadoPor;
                }
                
              /*   if (dlgOrdenar.optPagina.isSelected())
                {        
                
                    vPrint.numeroPagina = dlgOrdenar.txtPagIni.getText().trim();
                
                }    */    
              
                String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
                vPrint.setStartHeader();
                vPrint.activateCondensed();
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(27) + textoImpresion, true);
                vPrint.printLine("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
                vPrint.printLine("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
                vPrint.printLine("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);
             
                vPrint.printLine("Fecha Actual    : " + fechaActual, true);
                vPrint.printLine("========================================================================================================================================",
                                 true);
                vPrint.printBold("Código  Nombre                                           Cod. Lab          Laboratorio     U.Venta     C.Entero/C.Fracción    Anaquel",
                                 true);
                vPrint.printLine("========================================================================================================================================",
                                 true);
                vPrint.setEndHeader();

                int pagIni = numPagIni;
                int pagFin = numPagFin;
                int linePerPage = 52;

                int lineFromIni;
                int lineToEnd;

                lineFromIni = calcularLineIniPrint(pagIni, linePerPage);
                lineToEnd = calcularLineEndPrint(pagFin, linePerPage);


                ArrayList newarray = new ArrayList();

                ArrayList blanco = new ArrayList();

                blanco.add(0, " ");
                blanco.add(1, " ");
                blanco.add(2, " ");
                blanco.add(3, " ");
                blanco.add(4, " ");
                blanco.add(5, " ");
                blanco.add(6, " ");
                blanco.add(7, " ");
                blanco.add(8, " ");
                blanco.add(9, " ");
                blanco.add(10, " ");
                blanco.add(11, " ");
                blanco.add(1, " ");
                blanco.add(13, " ");
                blanco.add(14, " ");
                blanco.add(15, " ");


                for (int counter = 0; counter < listaImprimir.size(); counter++) {
                    ArrayList lab = (ArrayList)listaImprimir.get(counter);
                    labOld = lab.get(0).toString().trim();

                    if ((!labOld.equalsIgnoreCase(labNew) && counter > 0)) {
                       // newarray.add(blanco);
                        newarray.add(lab);
                    } else {
                        newarray.add(lab);
                    }

                    labNew = (lab.get(0).toString()).trim();
                }


                int j = 0;
                int k = 1;
                int c = 0;
                int paginado = 0;
                for (int i = 0; i < newarray.size(); i++) {
                    j++;
                    ArrayList lab = (ArrayList)newarray.get(i);
                    String precioGen = "";
                    
                
                    
                    if (!FarmaUtility.getValueFieldArrayList(newarray, i, 16)
                        .equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(newarray, i+1, 16))) {
                       
                        vPrint.numeroPagina =  FarmaUtility.getValueFieldArrayList(newarray,i+1,21);
                    }
                    log.info(FarmaUtility.getValueFieldArrayList(newarray,i,21));
                    
                    if (dlgOrdenar.getTipoPrecioImp().equalsIgnoreCase("venta")) {
                        precioGen = (String)lab.get(9);
                    } else if (dlgOrdenar.getTipoPrecioImp().equalsIgnoreCase("costo")) {
                        precioGen = (String)lab.get(12);
                    }
                    String linea = "_______/_______";
                    
                    //if (j >= lineFromIni && j <= lineToEnd && varRadioPagina) {
                    if (k >= pagIni && k <= pagFin && varRadioPagina) {
                        c++;
                        
                    
                      
                        //log.info(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + " " + FarmaUtility.getValueFieldArrayList(newarray, i, 16));
                        vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),
                            // COD PRODUCTO 
                            8) +
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) +
                            " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),
                            // DESC PRODUCTO
                            50) + 
                            // codigo de laboratorio
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),
                            // codigo de laboratorio
                            9)  + "       "+ 
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),
                            // NOMBRE LABORATORIO
                            13)  + "        "+
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 5),
                            // U.VENTA
                            10) + 
                        FarmaPRNUtility.alinearDerecha(linea, 15) + 
                            // Anaquel
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),
                            16),
                        true);
                            if (!FarmaUtility.getValueFieldArrayList(newarray, i, 16)
                            .equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(newarray, i+1, 16))) {
                            //linea = "";
                           // newarray.remove(i);
                            paginado++;
                            vPrint.printLine("================================================================================",
                                            true);
                           
                            vPrint.printLine("Total Items: " + c, true);
                            c=0;
                            vPrint.pageBreakArchivo();
                            
                        } 
                    }else if (!varRadioPagina){
                        c++;
                        h++;
                       
                        
                        log.info(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + " " + FarmaUtility.getValueFieldArrayList(newarray, i, 16));
                        vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),
                            // COD PRODUCTO
                            8) +
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) +
                            " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),
                            // DESC PRODUCTO
                            50) + 
                            // codigo de laboratorio
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),
                            // codigo de laboratorio
                            9) + "        "+
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),
                            // NOMBRE LABORATORIO
                            13) + "      "+
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 5),
                            // U.VENTA
                            9) + 
                        FarmaPRNUtility.alinearDerecha(linea, 15) + 
                            // Anaquel
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),
                            16),
                        true);
                        
                        if (!FarmaUtility.getValueFieldArrayList(newarray, i, 16)
                            .equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(newarray, i+1, 16))) {
                            vPrint.printLine("================================================================================",
                                            true);
                            saltosLaboratorioSig = j - saltosLaboratorio - 1;
                            saltosLaboratorio = (j - 1);
                            vPrint.printLine("Total Items: " + (h), true);
                            acumuladoImpresion = acumuladoImpresion+saltosLaboratorioSig;
                            h=0;
                            vPrint.pageBreakArchivo();
                        }
                    }

                    if ((!FarmaUtility.getValueFieldArrayList(newarray, i, 0)
                            .equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(newarray, i+1, 0))) && varRadioPagina) {
                         //vPrint.printLine("================================================================================",true);
                         saltosLaboratorioSig = j - saltosLaboratorio - 1;
                         saltosLaboratorio = (j - 1);
                         //vPrint.printLine("Total Items: " + saltosLaboratorioSig, true); 
                    }
                    
                    if(i+1<=newarray.size() && varRadioPagina){
                        if ((!FarmaUtility.getValueFieldArrayList(newarray, i, 0)
                                .equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(newarray, i+1, 0)))) {                         
                            k++;
                        }
                    }
                }
                vPrint.activateCondensed();
                vPrint.printLine("========================================================================================================================================",
                                 true);
              
                saltosLaboratorioSig = j - saltosLaboratorio;
                saltosLaboratorio = (j - 1);
                if (paginado!=0) {
                                         vPrint.printLine("Total Items: " + (paginado), true);
                                     }
                
                else {
                vPrint.printLine("Total Items: " + (c-acumuladoImpresion), true);
                }
                c=0;
                FarmaVariablesdos.estadoCmb="";
                vPrint.deactivateCondensed();
                vPrint.endPrintService();
              
            } catch (Exception sqlerr) {
                log.error("", sqlerr);
                FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                        sqlerr.getMessage(), null);
            }
        } else {
            FarmaUtility.showMessage(this, "Impresion cancelada", null);
        }


    }

    private void imprimirDiferencias() { // F11

        if (JOptionPane.showConfirmDialog(this, "Desea imprimir el reporte de diferencias?", "Confirmar Impresión",
                                          JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }


        DlgFiltroEstadoItems dlgOrdenar = new DlgFiltroEstadoItems(parentFrame, "Ordenar", true);
        dlgOrdenar.setTitleBorder("Seleccione el orden");
        dlgOrdenar.optFiltrado.setEnabled(false);
        dlgOrdenar.setVisible(true);


        log.info("imprimirDiferencias");
        /*   if (!JConfirmDialog.rptaConfirmDialog(this, "Seguro que desea imprimir?"))
            return; */
        //FarmaPrintService vPrint = new FarmaPrintService(45,
        log.info("start Service");
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", null);
            return;
        }
        //log.info("antes del TRY");
        try {
            //log.info("antes de fecha actual");
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            //log.info("despues de fecha actual");
            //vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printLine(FarmaPRNUtility.llenarBlancos(27) + " REPORTE DE DIFERENCIAS DE PRODUCTOS", true);
            vPrint.printLine("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printLine("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
            vPrint.printLine("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);
           
            vPrint.printLine("Fecha Actual    : " + fechaActual, true);

            //log.info("antes del cambio");
            ///ESTO SE DEBE IMPRIMIR POR BLOQUE DE LABORATORIO
            ArrayList pListaLaboratorios = new ArrayList();
            DBTomaInv.getLabTomaInv(pListaLaboratorios);

            // variables del DLG FILTRO

            String filtro = dlgOrdenar.getFiltro();
            int cantRegistros = dlgOrdenar.getCantRegistros();
            int numPagIni = dlgOrdenar.getCantPagIni();
            int numPagFin = dlgOrdenar.getCantPagFin();
            boolean varRadioPagina = dlgOrdenar.getRadioPagina();

            // variables del DLG FILTRO


            if (!varRadioPagina) {
                vPrint.setPageNumber(1);
            } else {
                vPrint.setPageNumber(numPagIni);
            }
            if (!vPrint.startPrintService()) {
                FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", null);
                return;
            }

            //log.info("Lista de Laboratorios:" + pListaLaboratorios);
            String pCodigoLabImprimir = "", pCodLabEliminar = "";
            ArrayList pListaProductoLaboratorio = new ArrayList();
            int cantidadImpresos = 0;

            String labNew = "", labOld = "";


            for (int i = 0; i < pListaLaboratorios.size(); i++) {
                pCodigoLabImprimir = FarmaUtility.getValueFieldArrayList(pListaLaboratorios, i, 0).trim();
              

                DBTomaInv.getProductoLabTomaInv(pListaProductoLaboratorio, pCodigoLabImprimir);

              
                vPrint.printLine("Laboratorio     : " +
                                 FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio, i, 0).trim() + " - " +
                                 FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio, i, 1).trim(), true);
                vPrint.printLine("=================================================================================================",
                                 true);
                vPrint.printBold("Código  Descripcion                                 Unidad       C.Entera/C.Fraccion     ",
                                 true);
                vPrint.printLine("=================================================================================================",
                                 true);
                vPrint.deactivateCondensed();

                for (int a = 0; a < pListaProductoLaboratorio.size(); a++) {
                  

                    vPrint.deactivateCondensed();

                    cantidadImpresos++;
                    vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,
                                                                                                               a, 1),
                                                                           8) +
                                          FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,
                                                                                                               a, 2),
                                                                           44) +
                                          FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,
                                                                                                               a, 3),
                                                                           10) + "   " +
                                          FarmaPRNUtility.alinearDerecha("_________/________", 19), true);
                }
                vPrint.activateCondensed();
                vPrint.printLine("=================================================================================================",
                                 true);
            }

            vPrint.printLine("Total de Items: " + listaImprimir.size(), true);
            FarmaVariablesdos.estadoCmb="";
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
         
            FarmaUtility.showMessage(this, "Por favor de recoger la Impresión", null);
        } catch (Exception sqlerr) {
            log.error("", sqlerr);
            FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                    sqlerr.getMessage(), null);
        }
    }

    private void guardaConteo(String nuConteo) {
        ArrayList lista = new ArrayList();
        String nombreSugerido = "";
        String userNamePc = System.getProperty("user.name");
        try {

            DBTomaInv.getListaPrimerConteo(lista, true);

            String pattern = "yyyymmdd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date =
                simpleDateFormat.format(FarmaUtility.getStringToDate(FarmaSearch.getFechaHoraBD(1), "dd/mm/yyyy"));
           

            nombreSugerido =
                    FarmaVariables.vCodLocal + "_" + FarmaVariables.vDescLocal + "_" + nuConteo + "Conteo_" + date +
                    ".txt";
            if (lista.size() > 0) {
                File lfFile = new File("C:\\Users\\" + userNamePc + "\\Desktop\\");
                JFileChooser filechooser = new JFileChooser(lfFile);
                filechooser.setSelectedFile(new File(nombreSugerido));
                if (filechooser.showSaveDialog(parentFrame) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                File fileChoosen = filechooser.getSelectedFile();

                FileWriter outFile = new FileWriter(fileChoosen);
                String linea = "";
                linea = " ";
                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                    linea = "Codigo\tNombre\tUnidad\tLaboratorio\tFracción\tCant Entera\tCant Fracción\tVa Costo";
                } else {
                    linea = "Codigo\tNombre\tUnidad\tLaboratorio\tFracción\tCant Entera\tCant Fracción\tVa Precio";
                }
                
                outFile.write(linea + "\n");
                linea = " ";

                for (int i = 0; i < lista.size(); i++) {
                    ArrayList producto = (ArrayList)lista.get(i);
                    StringBuffer row = new StringBuffer();
                    row.append((producto.get(0))); // codigo
                    row.append("\t");
                    row.append(producto.get(1)); // nombre
                    row.append("\t");
                    row.append(producto.get(2)); // unidad
                    row.append("\t");
                    row.append(producto.get(3)); // laboratorio
                    row.append("\t");
                    row.append(producto.get(12)); // cant por la que fracciona
                    row.append("\t");

                    row.append(producto.get(4)); // cant entero
                    row.append("\t");
                    row.append(producto.get(5)); // cant fraccion
                    row.append("\t");
                    
                    if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                        row.append(producto.get(13)); // precio costo (producto)
                        row.append("\t");
                    } else {
                        row.append(producto.get(11)); // precio venta (producto)
                        row.append("\t");
                    }
                  
                    outFile.write(row.toString() + "\n");
                }

                outFile.close();
            }
        } catch (SQLException e) {
            log.error("Error al guardar archivo: ",e);
            JOptionPane.showMessageDialog(this, "Error al guardar archivo - " + e.getMessage(), "Mensaje del Sistema",
                                          JOptionPane.WARNING_MESSAGE);
          
        } catch (IOException ioerr) {
            log.error("Error al guardar archivo: ",ioerr);
            JOptionPane.showMessageDialog(this, "Error al guardar archivo - " + ioerr.getMessage(),
                                          "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
          
        }
    }

    void obtieneCantPdaCargadoTomaInv() {
        try {
            VariablesTomaInv.cantPdaUp = DBTomaInv.getCantPdaCargadoTomaInv();
            if (VariablesTomaInv.cantPdaUp <= 0) {
                cantPdas = "0";
            } else {
                cantPdas = String.valueOf(VariablesTomaInv.cantPdaUp);
            }
            lblCantPda.setText(cantPdas);
        } catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
        }
    }


    private void muestraContenido(String archivo) throws FileNotFoundException, IOException, SQLException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        int Diferencia = 0;

        try {
            while ((cadena = b.readLine()) != null) {
                String[] arrOfStr = cadena.split("@");
                List<String> itemList = Arrays.asList(arrOfStr);
                if (itemList.get(2).equalsIgnoreCase("S")) {
                    Diferencia =
                            (Integer.parseInt(itemList.get(4)) * Integer.parseInt(itemList.get(3))) + Integer.parseInt(itemList.get(5));
                } else if (itemList.get(2).equalsIgnoreCase("N")) {
                    Diferencia = Integer.parseInt(itemList.get(4));
                }
                DBTomaInv.cargaTemporalTomaInv(FarmaVariables.vCodLocal, VariablesTomaInv.vSecToma,
                                               String.valueOf(itemList.get(0)), Integer.parseInt(itemList.get(3)),
                                               Diferencia, FarmaVariables.vIdUsu, String.valueOf(itemList.get(6)),
                                               "P");
            }
            DBTomaInv.cargaTomaInv(FarmaVariables.vCodLocal, VariablesTomaInv.vSecToma, FarmaVariables.vIdUsu);
            cantPdaUp++;
            b.close();
            renombrarFileInventario("C:/" + VariablesTomaInv.PRODUCTOS_INVENTARIO);
            DBTomaInv.updateLgtcTomaInventarioCantPda(cantPdaUp);
            obtieneCantPdaCargadoTomaInv();
            FarmaUtility.aceptarTransaccion();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException t) {
            t.printStackTrace();
        } catch (SQLException g) {
            g.printStackTrace();
        }
    }


    public void traerArchivoDesdePDA(String pathApi, String action, String localPath, String deviceFile) {
        //                    Ruta del API    Acción   Ruta  Nombre del archivo
        String[] commands = { pathApi, action, localPath, deviceFile };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
            System.out.println("Archivo " + deviceFile + " copiado a la PC");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }


    public void eliminarArchivoDesdePDA(String pathApi, String action, String deviceFile) {
        String[] commands = { pathApi, action, deviceFile };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
            System.out.println("Archivo " + deviceFile + " eliminado del PDA");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public boolean buscarArchivoDesdePDA(String fileName) {
        boolean estado = false;
        String[] commands = { VariablesTomaInv.PATH_PDANET_API, "buscar", fileName };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                    if (stdError.readLine() != null) {
                        System.err.println("El archivo " + fileName + " no existe");
                        estado = false;
                    } else if (stdInput.readLine() != null) {
                        System.out.println("El archivo " + fileName + " fue encontrado");
                        estado = true;
                    }
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return estado;
    }

    private void chkKeyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblRelacionLaboratoriosXToma, txtLaboratorio, 1);
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void cargaLaboratoriosToma() {
        try {
            DBTomaInv.getListaLabsXToma(tableModelLaboratorioToma);
            if (tblRelacionLaboratoriosXToma.getRowCount() > 0)
                lblTotalLaboratorios.setText(FarmaUtility.formatNumber(tblRelacionLaboratoriosXToma.getRowCount(),
                                                                       "#,##0"));
            FarmaUtility.ordenar(tblRelacionLaboratoriosXToma, tableModelLaboratorioToma, 1,
                                 FarmaConstants.ORDEN_ASCENDENTE);

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al obtener los laboratorios de la toma :\n" +
                    sql.getMessage(), txtLaboratorio);
        }
    }

    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;
        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    private void cargarRegistroSeleccionado() {
        VariablesTomaInv.vCodLab =
                tblRelacionLaboratoriosXToma.getValueAt(tblRelacionLaboratoriosXToma.getSelectedRow(),
                                                        0).toString().trim();
        VariablesTomaInv.vNomLab =
                tblRelacionLaboratoriosXToma.getValueAt(tblRelacionLaboratoriosXToma.getSelectedRow(),
                                                        1).toString().trim();
    }

    private void imprimir() throws SQLException {
        if (!JConfirmDialog.rptaConfirmDialog(this, "Seguro que desea imprimir?"))
            return;
        log.debug(FarmaVariablesdos.vImprReporte);
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariablesdos.vImprReporte, true);
        //FarmaPrintService vPrint = new FarmaPrintService(10, "//10.18.6.241//reportes", true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", txtLaboratorio);
            return;
        }

        try {
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printBold(FarmaPRNUtility.llenarBlancos(27) + " REPORTE LABORATORIOS POR TOMA DE INVENTARIO ",
                             true);
            vPrint.printBold(" ", true);
            vPrint.printBold("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printBold("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
            vPrint.printBold("Toma #: " + VariablesTomaInv.vSecToma, true);
            vPrint.printBold("Tipo         : " + VariablesTomaInv.vDescTipoToma, true);
            vPrint.printBold("Fecha Inicio : " + VariablesTomaInv.vFecIniToma, true);
            vPrint.printBold("Fecha Fin    : " + VariablesTomaInv.vFecFinToma, true);
            vPrint.printBold("Estado       : " + VariablesTomaInv.vDescEstadoToma, true);
            vPrint.printBold("Fecha Actual : " + fechaActual, true);
            vPrint.printLine("======================================================================================================",
                             true);
            vPrint.printBold("   Código        Laboratorio                                                           Estado      Cant. Cont   ",
                             true);
            vPrint.printLine("======================================================================================================",
                             true);
            vPrint.deactivateCondensed();
            vPrint.setEndHeader();

            for (int i = 0; i < tblRelacionLaboratoriosXToma.getRowCount(); i++) {

                vPrint.printCondensed("   " +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionLaboratoriosXToma.getValueAt(i,
                                                                                                                       0),
                                                                       14) +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionLaboratoriosXToma.getValueAt(i,
                                                                                                                       1),
                                                                       70) +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionLaboratoriosXToma.getValueAt(i,
                                                                                                                       2),
                                                                       22) +
                                      FarmaPRNUtility.alinearIzquierda("  /  ", 22), true);
            }
            vPrint.activateCondensed();
            vPrint.printLine("==============================================================================================",
                             true);
            vPrint.printLine("Total de Items: " + listaImprimir.size(), true);
            FarmaVariablesdos.estadoCmb="";
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
       
        } catch (Exception sqlerr) {
            log.error("", sqlerr);
            FarmaUtility.showMessage(this, "Ocurrió un error al imprimir :\n" +
                    sqlerr.getMessage(), txtLaboratorio);
        }

    }

    /**
     * Muestra  el cuadro de diálogo
     * donde seleccionara el tipo de Filtro
     * @author : dubilluz
     * @since  : 08.01.2008
     */
    private void filtrar() {
        if (tblRelacionLaboratoriosXToma.getRowCount() > 0) {
            DlgFiltroLabToma dlgflLab = new DlgFiltroLabToma(this.parentFrame, "", true);
            dlgflLab.setVisible(true);
            if (FarmaVariables.vAceptar) {
                filtroEstado = VariablesTomaInv.vCodFiltroEstado;
                cargaLabFiltrados();
                FarmaVariables.vAceptar = false;
            } else
                cargaLabFiltrados();
            FarmaUtility.moveFocus(txtLaboratorio);
        }
        log.debug("Colocando el Foco en Tabla");
        FarmaUtility.moveFocus(txtLaboratorio);
    }

    /**
     * Cargara la tabla con laboratorios de estado
     * @author : dubilluz
     * @since  : 08.01.2008
     */

    private void cargaLabFiltrados() {
        try {
            DBTomaInv.cargaLabxTomaFiltro(tableModelLaboratorioToma, filtroEstado);
            if (tblRelacionLaboratoriosXToma.getRowCount() > 0)
                FarmaUtility.ordenar(tblRelacionLaboratoriosXToma, tableModelLaboratorioToma, 1,
                                     FarmaConstants.ORDEN_ASCENDENTE);

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al listar los laboratorios : \n" +
                    sql.getMessage(), txtLaboratorio);
        }
    }

    /**
     * Quita el filtro
     * @author : dubilluz
     * @since  : 08.01.2008
     */
    private void quitarFiltro() {
        VariablesTomaInv.vCodFiltroEstado = "X";
        filtroEstado = "";
        cargaLaboratoriosToma();
        FarmaUtility.moveFocus(txtLaboratorio);
    }

    /**
     * Ingreso de codigos de Barra para los productos
     * @author dubilluz
     * @since  21.12.2009
     */
    private void ingresoCodigoBarra() {
        DlgIngresoCodigoBarra dlgIngresoCodigoBarra = new DlgIngresoCodigoBarra(this.parentFrame, "", true);
        dlgIngresoCodigoBarra.setVisible(true);
        if (FarmaVariables.vAceptar) {
            //SI SE INGRESO
            filtroEstado = VariablesTomaInv.vCodFiltroEstado;

            FarmaVariables.vAceptar = false;
        }
        cargaLabFiltrados();
    }


    private void completarConCeros() {
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
            FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
            if (tableModelLaboratorioToma.data.size() > 0) {
                if (JConfirmDialog.rptaConfirmDialog(this,
                                                     "¿Está seguro de completar los registros no ingresados con ceros?")) {
                    try {
                        DBTomaInv.rellenaTomaConCeros();
                        FarmaUtility.aceptarTransaccion();
                        cargaLaboratoriosToma();
                        FarmaUtility.showMessage(this, "La operación se realizó correctamente", txtLaboratorio);
                    } catch (SQLException sql) {
                        FarmaUtility.liberarTransaccion();
                        log.error("", sql);
                        FarmaUtility.showMessage(this, "Ocurrió un error al completar con ceros: \n" +
                                sql.getMessage(), txtLaboratorio);
                    }
                }
            }
        } else {
            FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción",
                                     txtLaboratorio);
        }

    }

    void dlgpaginado() {

        if (JOptionPane.showConfirmDialog(this, "Desea imprimir el reporte de diferencias según Toma de Inventario?",
                                          "Confirmar Impresión", JOptionPane.YES_NO_OPTION) !=
            JOptionPane.YES_OPTION) {
            return;
        }
        
        FarmaVariablesdos.reporteDiferenciasValorizadas = true;

        DlgFiltroEstadoItems dlgOrdenar = new DlgFiltroEstadoItems(parentFrame, "Ordenar", true);
        dlgOrdenar.setTitleBorder("Seleccione el orden");

        FarmaVariablesdos.reporteDiferenciasValorizadas = false;
      
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL))
        {
            //dlgOrdenar.optFiltrado.setEnabled(false);
            FarmaVariablesdos.vRol= "Q";
        }



       


        dlgOrdenar.setVisible(true);

        if (FarmaVariables.vAceptar) {
        
           
        
            if (dlgOrdenar.optFiltrado.isSelected())
                
            {
                
                    FarmaVariablesdos.cantRegistros =  dlgOrdenar.txtMayoresDiferencias.getText().trim();
                }
            
            else {
                    FarmaVariablesdos.cantRegistros = "";
                
                }
            
            // VARIABLE PRECIO COSTO
            
            if (dlgOrdenar.rbnPreCosto.isSelected() && dlgOrdenar.cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio))
                
            {
                
                   // FarmaVariablesdos.precCosto =  "CTO";
            
                   FarmaVariablesdos.estadoCmb = "CTO";
                }
            
            
            
            // VARIABLE PRECIO VENTA
            
           else if (dlgOrdenar.rbnPreVenta.isSelected() && dlgOrdenar.cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio))
                
            {
                
                    //FarmaVariablesdos.precVenta =  "VTA";
                    FarmaVariablesdos.estadoCmb = "VTA";
                }
         
            listaImprimir.clear();
        
            if (dlgOrdenar.rbnPreCosto.isSelected()){
                DlgConsolidadoToma dlgConsolidadoToma =
                    new DlgConsolidadoToma(parentFrame, "Resumen Inventario", true, "P");
                totalInicial = dlgConsolidadoToma.getTotalInicial();
                calculaDiferencia();  
            }else if (dlgOrdenar.rbnPreVenta.isSelected()){
                DlgConsolidadoToma dlgConsolidadoToma =
                    new DlgConsolidadoToma(parentFrame, "Resumen Inventario", true, "V");
                totalInicial = dlgConsolidadoToma.getTotalInicial();
                calculaDiferencia();
            } 
            try {
                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)){   
                    if(dlgOrdenar.getTipoPrecioImp().equalsIgnoreCase("venta")){
                        FarmaVariablesdos.vRol= "Q";
                    }else{
                        FarmaVariablesdos.vRol= "A";    
                    }
                }
                
                //DBTomaInv.getListaProdsDiferenciasTotales(listaImprimir, FarmaVariablesdos.estadoCmb);
                DBTomaInv.getListaImprimirTomaInv(listaImprimir, FarmaVariablesdos.estadoCmb);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            String filtro = dlgOrdenar.getFiltro();
            int cantRegistros = dlgOrdenar.getCantRegistros();
            int numPagIni = dlgOrdenar.getCantPagIni();
            int numPagFin = dlgOrdenar.getCantPagFin();
            boolean varRadioPagina = dlgOrdenar.getRadioPagina();
            
            //FarmaPrintService vPrint = new FarmaPrintService(66, "//10.18.6.241//reportes", true);
            FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);

            if (!varRadioPagina) {
                vPrint.setPageNumber(1);
            } else {
                vPrint.setPageNumber(numPagIni);
            }
            if (!vPrint.startPrintService()) {
                FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", null);
                return;
            }
            try {
                String labNew = "", labOld = "";

                String textoImpresion = "";
                String ordenadoPor = "";


                switch (FarmaVariablesdos.estadoCmb) {
                case "LAB":
                    ordenadoPor = " (Ordenado por Laboratorio)";
                    break;
                case "PRD":
                    ordenadoPor = " (Ordenado por Producto)";
                    break;
                case "UNI":
                    ordenadoPor = " (Ordenado por Unidad)";
                    break;
                case "CNT":
                    ordenadoPor = " (Ordenado por Cantidad)";
                    break;
                case "R":
                    ordenadoPor = " (Ordenado por Precio)";
                    break;
                
                    case "CTO":
                        ordenadoPor = " (Ordenado por Precio)";
                        break;
                
                    case "VTA":
                        ordenadoPor = " (Ordenado por Precio)";
                        break;
                
                   
                default:
                    ordenadoPor = " (Sin Orden Especificado)";
                }


                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)  && dlgOrdenar.varTipoPrecio.toString().equalsIgnoreCase("venta"))

                {
                    textoImpresion = "REPORTE DE DIFERENCIAS DE PRODUCTOS VALORIZADOS A P. VENTA" + ordenadoPor;

                }
                
              else  if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)  && dlgOrdenar.varTipoPrecio.toString().equalsIgnoreCase("costo"))

                {
                    textoImpresion = "REPORTE DE DIFERENCIAS DE PRODUCTOS VALORIZADOS A P. COSTO" + ordenadoPor;

                }

                else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL))

                {

                    textoImpresion = "REPORTE DE DIFERENCIAS DE PRODUCTOS VALORIZADOS A P. VENTA" + ordenadoPor;
                }


                String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
                vPrint.setStartHeader();
                vPrint.activateCondensed();
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(27) + textoImpresion, true);
                vPrint.printLine("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
                vPrint.printLine("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
                vPrint.printLine("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);
                vPrint.printLine("Fecha Actual    : " + fechaActual, true);
                vPrint.printLine("========================================================================================================================================",
                                 true);
                vPrint.printBold("Código  Nombre                                  Cod.Lab    Laboratorio      U.Venta Cantidad        P.Uni  Venta Tot   Can.Cont  Anaquel",
                                 true);
                vPrint.printLine("========================================================================================================================================",
                                 true);
                vPrint.setEndHeader();

                int pagIni = numPagIni;
                int pagFin = numPagFin;
                int linePerPage = 52;

                int lineFromIni;
                int lineToEnd;

                lineFromIni = calcularLineIniPrint(pagIni, linePerPage);
                lineToEnd = calcularLineEndPrint(pagFin, linePerPage);


                ArrayList newarray = new ArrayList();
                // String[] blanco = {" "," "," "," "," "," "," "," "," "," "," "," "," "};

                ArrayList blanco = new ArrayList();
                blanco.add(0, " ");
                blanco.add(1, " ");
                blanco.add(2, " ");
                blanco.add(3, " ");
                blanco.add(4, " ");
                blanco.add(5, " ");
                blanco.add(6, " ");
                blanco.add(7, " ");
                blanco.add(8, " ");
                blanco.add(9, " ");
                blanco.add(10, " ");
                blanco.add(11, " ");
                blanco.add(1,  " ");
                blanco.add(13, " ");
                blanco.add(14, " ");
                blanco.add(15, " ");

                String varEspace = "";
                int c = 0;
                int paginado = 0;

                for (int counter = 0; counter < listaImprimir.size(); counter++) {
                    ArrayList lab = (ArrayList)listaImprimir.get(counter);
                    labOld = lab.get(0).toString().trim();

                    if ((!labOld.equalsIgnoreCase(labNew) && counter > 0)) {
                        newarray.add(blanco);
                        newarray.add(lab);
                    } else {
                        newarray.add(lab);
                    }

                    labNew = (lab.get(0).toString()).trim();
                }

                int j = 0;
                for (int i = 0; i < newarray.size(); i++) {
                    j++;

                    ArrayList lab = (ArrayList)newarray.get(i);

                    if(FarmaUtility.getValueFieldArrayList(newarray,i,1).equalsIgnoreCase(null)){
                        varEspace = "";
                    }else{
                        varEspace = " / ";
                    }

                    String precioGen = "";
                    if (dlgOrdenar.getTipoPrecioImp().equalsIgnoreCase("venta")) {
                        precioGen = (String)lab.get(9);
                    } else if (dlgOrdenar.getTipoPrecioImp().equalsIgnoreCase("costo")) {
                        precioGen = (String)lab.get(12);
                    }

                    if (j >= lineFromIni && j <= lineToEnd && varRadioPagina &&
                        FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)  && 
                        dlgOrdenar.varTipoPrecio.toString().equalsIgnoreCase("costo")) {
                        c++;
                        
                        
                        vPrint.printCondensed(
                        // COD PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),8) +
                        // DESC PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + 
                                                   " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),41) +
                        // CODIGO DE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),8) +
                        // NOMBRE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),17) +
                        // U.VENTA
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 5),6) +
                        // CANT ENTERO / CANT FRACC
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 8),7) +
                            varEspace +
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 9),5) +
                        // PRECIO UNITARIO AUDITOR
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 13),10) +
                        // PRECIO AUDITOR
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 14),12) +
                        FarmaPRNUtility.alinearDerecha(varEspace, 7) +
                        // NUM ANAQUEL
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),11),
                        true);
                        
                      /*   if (!FarmaUtility.getValueFieldArrayList(newarray, i, 16)
                        .equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(newarray, i+1, 16))) {
                        //linea = "";
                        // newarray.remove(i);
                        newarray.remove(i+1);
                        paginado++;
                        vPrint.printLine("================================================================================",
                                        true);
                        
                        vPrint.printLine("Total Items: " + c, true);
                        c=0;
                        vPrint.pageBreakArchivo();
                        
                        } */
                    } 
                    else if (!varRadioPagina && FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) && 
                               dlgOrdenar.varTipoPrecio.toString().equalsIgnoreCase("costo")) {
                        vPrint.printCondensed(
                        // COD PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),8) +
                        // DESC PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + 
                                                   " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),41) +
                        // CODIGO DE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),8) +
                        // NOMBRE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),17) +
                        // U.VENTA
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 5),6) +
                        // CANT ENTERO / CANT FRACC
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 8),7) +
                            varEspace +
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 9),5) +
                        // PRECIO UNITARIO QUIMICO
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 13),10) +
                        // PRECIO QUIMICO
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 14),12) +
                        FarmaPRNUtility.alinearDerecha(varEspace, 7) +
                        // NUM ANAQUEL
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),11),
                        true);                        
                    }
                    else if (!varRadioPagina && FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) && 
                             dlgOrdenar.varTipoPrecio.toString().equalsIgnoreCase("venta")) {
                        vPrint.printCondensed(
                        // COD PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),8) +
                        // DESC PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + 
                                                   " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),41) +
                        // CODIGO DE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),8) +
                        // NOMBRE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),17) +
                        // U.VENTA
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 5),6) +
                        // CANT ENTERO / CANT FRACC
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 8),7) +
                            varEspace +
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 9),5) +
                        // PRECIO UNITARIO QUIMICO
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 11),10) +
                        // PRECIO QUIMICO
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 12),12) +
                        FarmaPRNUtility.alinearDerecha(varEspace, 7) +
                        // NUM ANAQUEL
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),11),
                        true);    
                    }
                    else if (j >= pagIni && j <= pagFin && varRadioPagina &&
                         FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)  && dlgOrdenar.varTipoPrecio.toString().equalsIgnoreCase("venta")) {
                         c++;
                        
                         vPrint.printCondensed(
                         // COD PRODUCTO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),8) +
                         // DESC PRODUCTO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + 
                                                    " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),41) +
                         // CODIGO DE LABORATORIO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),8) +
                         // NOMBRE LABORATORIO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),17) +
                         // U.VENTA
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 5),6) +
                         // CANT ENTERO / CANT FRACC
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 8),7) +
                             varEspace +
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 9),5) +
                         // PRECIO UNITARIO AUDITOR
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 11),10) +
                         // PRECIO AUDITOR
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 12),12) +
                         FarmaPRNUtility.alinearDerecha(varEspace, 7) +
                         // NUM ANAQUEL
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),11),
                         true);
                         
                      /*    if (!FarmaUtility.getValueFieldArrayList(newarray, i, 16)
                         .equalsIgnoreCase(FarmaUtility.getValueFieldArrayList(newarray, i+1, 16))) {
                         //linea = "";
                          newarray.remove(i+1);
                         paginado++;
                         vPrint.printLine("================================================================================",
                                         true);
                         
                         vPrint.printLine("Total Items: " + c, true);
                         c=0;
                         vPrint.pageBreakArchivo();
                         
                         } */
                     }
                     else if (j >= lineFromIni && j <= lineToEnd && varRadioPagina &&
                              FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {   
                         vPrint.printCondensed(
                         // COD PRODUCTO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),8) +
                         // DESC PRODUCTO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + 
                                                    " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),41) +
                         // CODIGO DE LABORATORIO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),8) +
                         // NOMBRE LABORATORIO
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),17) +
                         // U.VENTA
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 5),6) +
                         // CANT ENTERO / CANT FRACC
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 8),7) +
                             varEspace +
                         FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 9),5) +
                         // PRECIO UNITARIO AUDITOR
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 11),10) +
                         // PRECIO AUDITOR
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 12),12) +
                         FarmaPRNUtility.alinearDerecha(varEspace, 7) +
                         // NUM ANAQUEL
                         FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),11),
                         true);
                     }
                     else if (!varRadioPagina &&
                        FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) { //ENTRA AQUI
                        vPrint.printCondensed(
                        // COD PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,i,1),8) +
                        // DESC PRODUCTO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2) + 
                                                   " " + FarmaUtility.getValueFieldArrayList(newarray, i, 3),41) +
                        // CODIGO DE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 16),8) +
                        // NOMBRE LABORATORIO
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),17) +
                        // U.VENTA
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 5),6) +
                        // CANT ENTERO / CANT FRACC
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 8),7) +
                            varEspace +
                        FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 9),5) +
                        // PRECIO UNITARIO QUIMICO
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 11),10) +
                        // PRECIO QUIMICO
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 12),12) +
                        FarmaPRNUtility.alinearDerecha(varEspace, 7) +
                        // NUM ANAQUEL
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 15),11),
                        true);    
                    }
                }
                vPrint.activateCondensed();
                vPrint.printLine("========================================================================================================================================",
                                 true);
                vPrint.printLine("Total Items: " + listaImprimir.size() + "                Sobrante -->  S/." +
                                 VariablesTomaInv.vSobrante + "   Faltante --> S/." + VariablesTomaInv.vFaltante, 
                                 true);
                vPrint.printLine("Stock Valorizado Inicial: " + FarmaUtility.formatNumber(totalInicial, 2) +
                                 "        Stock Valorizado Final: " + FarmaUtility.formatNumber(totalFinal, 2), true);
                
              
                FarmaVariablesdos.estadoCmb="";
               
                vPrint.deactivateCondensed();
                vPrint.endPrintService();
              
            } catch (Exception sqlerr) {
                log.error("", sqlerr);
                FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                        sqlerr.getMessage(), null);
            }

        } else {
            FarmaUtility.showMessage(this, "Impresion cancelada", null);
        }


    }

    public int calcularLineIniPrint(int pIni, int linePerPage) {
        int linePrees;
        linePrees = (pIni - 1) * linePerPage + 1;
        return linePrees;
    }


    public int calcularLineEndPrint(int pFin, int linePerPage) {
        int linePrees;
        linePrees = pFin * linePerPage;
        return linePrees;
    }
    
    private void calculaDiferencia() {
        double diferencia = 0;
        double sobrante = 0;
        double faltante = 0;
        sobrante = FarmaUtility.getDecimalNumber(VariablesTomaInv.vSobrante);
        faltante = FarmaUtility.getDecimalNumber(VariablesTomaInv.vFaltante);
        diferencia = sobrante + faltante;
        //cantDifeGlobalTotal = diferencia;
        totalFinal = totalInicial + diferencia;
       

    }


    public boolean isXLaboratorio() {
        return xLaboratorio;
    }

    public void setXLaboratorio(boolean xLaboratorio) {
        this.xLaboratorio = xLaboratorio;
    }


    private void tblRelacionLaboratoriosXToma_mouseReleased(MouseEvent e) {

        txtLaboratorio.requestFocus();

    }
}


