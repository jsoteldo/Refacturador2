package mifarma.ptoventa.tomainventario;


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
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;


import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaPRNUtility;

import mifarma.ptoventa.tomainventario.reference.FarmaPrintService;

import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.tomainventario.reference.ConstantsTomaInv;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.FarmaVariablesdos;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgListaDiferenciasTomaToTal extends JDialog {

    Frame parentFrame;
    FarmaTableModel tableModelDiferenciasProductos;
    private static final Logger log = LoggerFactory.getLogger(DlgListaDiferenciasTomaToTal.class);

    Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanelWhite jContentPane = new JPanelWhite();

    private JPanelHeader jPanelHeader1 = new JPanelHeader();

    private JPanelTitle jPanelTitle1 = new JPanelTitle();

    private JScrollPane jScrollPane1 = new JScrollPane();

    private JTable tblRelacionDiferenciasProductos = new JTable();

    private JButtonLabel btnRelacionProductos = new JButtonLabel();

    private JButtonLabel btnProductos = new JButtonLabel();

    private JTextFieldSanSerif txtProductos = new JTextFieldSanSerif();

    private JLabelFunction lblF11 = new JLabelFunction();

    private JLabelFunction lblEscape = new JLabelFunction();
    private JLabelFunction lblF12 = new JLabelFunction();
    private JLabelWhite lblLaboratorio = new JLabelWhite();
    ArrayList lista = new ArrayList();
    ArrayList listaImprimir = new ArrayList();
    private boolean isHistorico = false;

    /** Almacena todos los objetos y datos asociados a un ComboBox */
    private static Hashtable tableList = new Hashtable();
    private JPanel pnlFooter = new JPanel();
    private JLabel lblCantidadProd = new JLabel();
    private JLabel lblCantidad = new JLabel();
    private JLabel lblGuardarT = new JLabel();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgListaDiferenciasTomaToTal() {
        this(null, "", false, false);
    }

    public DlgListaDiferenciasTomaToTal(Frame parent, String title, boolean modal, boolean isHistorico) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            this.isHistorico = isHistorico;
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(1023, 427));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Lista de Diferencias de Productos");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanelHeader1.setBounds(new Rectangle(5, 10, 1005, 30));
        jPanelHeader1.setLayout(null);
        jPanelTitle1.setBounds(new Rectangle(5, 45, 1005, 25));
        jPanelTitle1.setLayout(null);
        jScrollPane1.setBounds(new Rectangle(5, 70, 1005, 260));
        tblRelacionDiferenciasProductos.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    tblRelacionDiferenciasProductos_mouseReleased(e);
                }
            });
        btnRelacionProductos.setText("Relacion de Diferencias de Productos");
        btnRelacionProductos.setBounds(new Rectangle(10, 0, 215, 25));
        btnRelacionProductos.setMnemonic('r');
        btnRelacionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionProductos_actionPerformed(e);
            }
        });
        btnProductos.setText("Productos :");
        btnProductos.setMnemonic('p');
        btnProductos.setBounds(new Rectangle(30, 5, 65, 20));
        btnProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnProductos_actionPerformed(e);
            }
        });
        txtProductos.setBounds(new Rectangle(115, 5, 305, 20));
        txtProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtProductos_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtProductos_keyReleased(e);
            }
        });
        lblF11.setBounds(new Rectangle(550, 360, 215, 20));
        lblF11.setText("[ F12 ] Imprimir Dif. Valorizadas");
        lblF11.setBackground(SystemColor.window);
        lblF11.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF11.setFont(new Font("SansSerif", 1, 11));
        lblF11.setForeground(new Color(32, 105, 29));
        lblF11.setVisible(false);
        lblEscape.setBounds(new Rectangle(705, 360, 110, 20));
        lblEscape.setText("[ ESCAPE ] Cerrar");
        lblEscape.setBackground(SystemColor.window);
        lblEscape.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEscape.setFont(new Font("SansSerif", 1, 11));
        lblEscape.setForeground(new Color(32, 105, 29));
        lblF12.setBounds(new Rectangle(305, 360, 225, 20));
        lblF12.setText("[ F11 ] Imprimir Diferencias en Blanco");
        lblF12.setBackground(SystemColor.window);
        lblF12.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF12.setFont(new Font("SansSerif", 1, 11));
        lblF12.setForeground(new Color(32, 105, 29));
        lblF12.setVisible(false);
        lblLaboratorio.setBounds(new Rectangle(480, 0, 525, 25));
        pnlFooter.setBounds(new Rectangle(5, 330, 1005, 25));
        pnlFooter.setBackground(new Color(255, 130, 14));
        pnlFooter.setLayout(null);
        pnlFooter.add(lblCantidad, null);
        pnlFooter.add(lblCantidadProd, null);
        lblCantidadProd.setText("Cantidad Productos:");
        lblCantidadProd.setFont(new Font("Arial Black", 1, 11));
        lblCantidadProd.setForeground(SystemColor.window);
        lblCantidadProd.setBounds(new Rectangle(5, 0, 165, 20));
        lblCantidad.setBounds(new Rectangle(165, 5, 80, 15));
        lblCantidad.setForeground(SystemColor.window);
        lblGuardarT.setText("[ F9 ] Guardar");
        lblGuardarT.setBounds(new Rectangle(610, 360, 80, 20));
        lblGuardarT.setForeground(new Color(32, 105, 29));
        lblGuardarT.setFont(new Font("SansSerif", 1, 11));
        jScrollPane1.getViewport().add(tblRelacionDiferenciasProductos, null);

        jContentPane.add(lblGuardarT, null);
        jContentPane.add(pnlFooter, null);
        jContentPane.add(lblF12, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(jScrollPane1, null);
        jContentPane.add(jPanelTitle1, null);
        jPanelTitle1.add(lblLaboratorio, null);
        jPanelTitle1.add(btnRelacionProductos, null);
        jPanelHeader1.add(txtProductos, null);
        jPanelHeader1.add(btnProductos, null);
        jContentPane.add(jPanelHeader1, null);
        jContentPane.add(lblEscape, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableListaDiferenciasProductos();
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTableListaDiferenciasProductos() {


        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) { // CABECERA TABLA PARA EL ROL AUDITOR 012


            tableModelDiferenciasProductos =
                    new FarmaTableModel(ConstantsTomaInv.columnsListaDiferenciasTotales, ConstantsTomaInv.defaultValuesListaDiferenciasTotales,
                                        0);

            FarmaUtility.initSimpleList(tblRelacionDiferenciasProductos, tableModelDiferenciasProductos,
                                        ConstantsTomaInv.columnsListaDiferenciasTotales);

            cargaListaDiferenciasProductos(this.isHistorico);

            if (tableModelDiferenciasProductos.data.size() > 0) {
                FarmaGridUtils.showCell(tblRelacionDiferenciasProductos, 0, 0);
                lblLaboratorio.setText(FarmaUtility.getValueFieldArrayList(tableModelDiferenciasProductos.data,
                                                                           tblRelacionDiferenciasProductos.getSelectedRow(),
                                                                           0));
            }

        }

        else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) { // CABECERA TABLA PARA EL ROL QUIMICO Ó JEFE LOCAL 011

            tableModelDiferenciasProductos =
                    new FarmaTableModel(ConstantsTomaInv.columnsListaDiferenciasTotalesQuimico,
                                        ConstantsTomaInv.defaultValuesListaDiferenciasTotales, 0);

            FarmaUtility.initSimpleList(tblRelacionDiferenciasProductos, tableModelDiferenciasProductos,
                                        ConstantsTomaInv.columnsListaDiferenciasTotalesQuimico);

            cargaListaDiferenciasProductos(this.isHistorico);

            if (tableModelDiferenciasProductos.data.size() > 0) {
                FarmaGridUtils.showCell(tblRelacionDiferenciasProductos, 0, 0);
                lblLaboratorio.setText(FarmaUtility.getValueFieldArrayList(tableModelDiferenciasProductos.data,
                                                                           tblRelacionDiferenciasProductos.getSelectedRow(),
                                                                           0));
            }
        }

    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtProductos);
        FarmaUtility.setearPrimerRegistro(tblRelacionDiferenciasProductos, txtProductos, 2);
    }
    
    private void txtProductos_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblRelacionDiferenciasProductos, txtProductos, 2);
    }

    private void txtProductos_keyPressed(KeyEvent e) {
        if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE ||
              e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT ||
              e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_HOME)) {
            e.consume();
        }
        FarmaGridUtils.aceptarTeclaPresionada(e, tblRelacionDiferenciasProductos, txtProductos, 2);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblRelacionDiferenciasProductos.getSelectedRow() >= 0) {
                if (!(FarmaUtility.findTextInJTable(tblRelacionDiferenciasProductos, txtProductos.getText().trim(), 1,
                                                    2))) {
                    FarmaUtility.showMessage(this, "Producto No Encontrado según Criterio de Búsqueda !!!",
                                             txtProductos);
                    return;
                }
            }
            if (tieneRegistroSeleccionado(tblRelacionDiferenciasProductos) && !isHistorico) {
                cargarRegistroSeleccionado();
                DlgIngresoCantidadToma dlgIngresoCantidadToma = new DlgIngresoCantidadToma(this.parentFrame, "", true);
                dlgIngresoCantidadToma.setVisible(true);
                if (FarmaVariables.vAceptar) {
                    cargaListaDiferenciasProductos(false);
                    tableModelDiferenciasProductos.fireTableDataChanged();
                    FarmaVariables.vAceptar = false;
                    FarmaGridUtils.showCell(tblRelacionDiferenciasProductos, VariablesTomaInv.vRegActual, 0);
                    txtProductos.setText((String)tableModelDiferenciasProductos.getValueAt(tblRelacionDiferenciasProductos.getSelectedRow(), 2));  
                }
                txtProductos.selectAll();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        } else if (UtilityPtoVenta.verificaVK_F12(e) && lblF12.isVisible()) {
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, txtProductos);
            else
                dlgpaginado();

            // imprimir();
        } else if (UtilityPtoVenta.verificaVK_F11(e) && lblF11.isVisible()) {
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, txtProductos);
            else
                imprimirDiferencias();
        } else if (e.getKeyCode() == KeyEvent.VK_F9) {
            guardarArchivo();
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
        VariablesTomaInv.vRegActual = tblRelacionDiferenciasProductos.getSelectedRow();
        if (isHistorico) {
            VariablesTomaInv.vNomLab = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(0);
            VariablesTomaInv.vCodProd = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(1);
            VariablesTomaInv.vDesProd = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(2);
            VariablesTomaInv.vUnidPresentacion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(3);
            VariablesTomaInv.vUnidVta = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(15);
            VariablesTomaInv.vCantEntera = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(9);
            VariablesTomaInv.vCantEntera = VariablesTomaInv.vCantEntera.replace(",", "");
            VariablesTomaInv.vCantFraccion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(6);
            VariablesTomaInv.vCantFraccion = VariablesTomaInv.vCantFraccion.replace(",", "");
            VariablesTomaInv.vFraccion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(4);
            VariablesTomaInv.Vanaquel = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(14);
            VariablesTomaInv.Vorigen = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(17);
            VariablesTomaInv.vCodLab = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(16);
        } else {
            /*VariablesTomaInv.vNomLab = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(0);
            VariablesTomaInv.vCodProd = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(1);
            VariablesTomaInv.vDesProd = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(2);
            VariablesTomaInv.vUnidPresentacion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(3);
            VariablesTomaInv.vUnidVta = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(17);
            VariablesTomaInv.vCantEntera = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(18);
            VariablesTomaInv.vCantEntera = VariablesTomaInv.vCantEntera.replace(",", "");
            VariablesTomaInv.vCantFraccion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(19);
            VariablesTomaInv.vCantFraccion = VariablesTomaInv.vCantFraccion.replace(",", "");
            VariablesTomaInv.vFraccion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(5);
            VariablesTomaInv.Vanaquel = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(14);
            VariablesTomaInv.Vorigen = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(16);
            VariablesTomaInv.vCodLab = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(15);*/
            VariablesTomaInv.vNomLab = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(0);
            VariablesTomaInv.vCodProd = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(1);
            VariablesTomaInv.vDesProd = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(2);
            VariablesTomaInv.vUnidPresentacion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(3);
            VariablesTomaInv.vUnidVta = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(18);
            VariablesTomaInv.vCantEntera = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(19);
            VariablesTomaInv.vCantEntera = VariablesTomaInv.vCantEntera.replace(",", "");
            VariablesTomaInv.vCantFraccion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(20);
            VariablesTomaInv.vCantFraccion = VariablesTomaInv.vCantFraccion.replace(",", "");
            VariablesTomaInv.vFraccion = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(5);
            VariablesTomaInv.Vanaquel = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(15);
            VariablesTomaInv.Vorigen = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(17);
            VariablesTomaInv.vCodLab = (String)((ArrayList)lista.get(VariablesTomaInv.vRegActual)).get(16);
        }


        //  log.debug("este es el valor del origen "+VariablesTomaInv.Vorigen);
    }


    public void guardarArchivo() {

        JFileChooser m_chooser = new JFileChooser();
        if (m_chooser.showSaveDialog(parentFrame) != JFileChooser.APPROVE_OPTION)
            return;

        File fChoosen =
            new File(m_chooser.getSelectedFile().getParent(), m_chooser.getSelectedFile().getName() + ".xls");

        try {
            FileWriter out = new FileWriter(fChoosen);
            out.write("REPORTE DE DIFERENCIAS" + "\n\n");

            /* Cabecera Reporte */
            String strCabecera = "";
            for (int i = 0; i < tblRelacionDiferenciasProductos.getColumnCount(); i++) {
                String columnName = "";
                columnName = tblRelacionDiferenciasProductos.getColumnName(i);
                if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                    if (columnName.equalsIgnoreCase("Total Prod") || columnName.equalsIgnoreCase("P. Costo") ||
                        columnName.equalsIgnoreCase("TOTAL_PREC_AUDITOR")) {
                        strCabecera += "";
                    } else {
                        strCabecera += tblRelacionDiferenciasProductos.getColumnName(i) + "\t";
                    }
                } else {
                    if (columnName.equalsIgnoreCase("Total Prod") || columnName.equalsIgnoreCase("Anaq")) {
                        strCabecera += "";
                    } else {
                        strCabecera += tblRelacionDiferenciasProductos.getColumnName(i) + "\t";
                    }
                }
            }
            strCabecera += "\n";
            out.write(strCabecera);
            /*Cuerpo Reporte*/

            String strCuerpo = "";
            for (int i = 0; i < tblRelacionDiferenciasProductos.getRowCount(); i++) {
                String columnName = "";
                for (int j = 0; j < tblRelacionDiferenciasProductos.getColumnCount(); j++) {
                    columnName = tblRelacionDiferenciasProductos.getColumnName(j);
                    if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                        if (columnName.equalsIgnoreCase("Total Prod") || columnName.equalsIgnoreCase("P. Costo") ||
                            columnName.equalsIgnoreCase("TOTAL_PREC_AUDITOR")) {
                            strCuerpo += "";
                        } else {
                            strCuerpo += tblRelacionDiferenciasProductos.getValueAt(i, j).toString().trim() + "\t";
                        }
                    } else {
                        if (columnName.equalsIgnoreCase("Total Prod") || columnName.equalsIgnoreCase("Anaq")) {
                            strCuerpo += "";
                        } else {
                            strCuerpo += tblRelacionDiferenciasProductos.getValueAt(i, j).toString().trim() + "\t";
                        }
                    }
                }
                strCuerpo += "\n";
                out.write(strCuerpo);
                strCuerpo = "";
            }
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void btnProductos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProductos);
    }

   


    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************
    //SLEYVA-2019

    private void cargaListaDiferenciasProductos(boolean isHistorico) {
        try {
            lista.clear();
            tableModelDiferenciasProductos.clearTable();
            // DBTomaInv.getListaProdsDiferenciasTotales(tableModelDiferenciasProductos);
            if (isHistorico) {
                DBTomaInv.getListaProdsDiferenciasXEstado(lista, "", VariablesTomaInv.vCodEstadoToma);
            } else {
                DBTomaInv.getListaProdsDiferenciasTotales(lista, "");
            }

            tableModelDiferenciasProductos.data = lista;

            /* if (tblRelacionDiferenciasProductos.getRowCount() > 0) {
                FarmaUtility.ordenar(tblRelacionDiferenciasProductos, tableModelDiferenciasProductos, 2,
                                     FarmaConstants.ORDEN_ASCENDENTE);
            } */

            lblCantidad.setText("" + lista.size());
            log.debug("se cargo la lista de de tomas dif");
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener la lista de diferencias :\n" +
                    sql.getMessage(), txtProductos);
        }
    }


    void dlgpaginado() { // F12

        if (JOptionPane.showConfirmDialog(this, "Desea imprimir el reporte de diferencias según Toma de Inventario?",
                                          "Confirmar Impresión", JOptionPane.YES_NO_OPTION) !=
            JOptionPane.YES_OPTION) {
            return;
        }


        DlgFiltroEstadoItems dlgOrdenar = new DlgFiltroEstadoItems(parentFrame, "Ordenar", true);
        dlgOrdenar.setTitleBorder("Seleccione el orden");
        dlgOrdenar.optFiltrado.setEnabled(false);
        dlgOrdenar.setVisible(true);


        try {
            DBTomaInv.getListaProdsDiferenciasTotales(listaImprimir, FarmaVariablesdos.estadoCmb);

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
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", txtProductos);
            return;
        }
        try {
            String labNew = "", labOld = "";


            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printLine(FarmaPRNUtility.llenarBlancos(27) + " REPORTE DE DIFERENCIAS DE PRODUCTOS", true);
            vPrint.printLine("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printLine("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
            vPrint.printLine("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);
            vPrint.printLine("Fecha Actual    : " + fechaActual, true);
            vPrint.printLine("========================================================================================================================================",
                             true);
            vPrint.printBold("Código  Nombre                                       Laboratorio      U.Venta     Cant.      P.Uni      Pre. Prom   Cant. Cont. Anaquel ",
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
            blanco.add(1, " ");
            blanco.add(13, " ");
            blanco.add(14, " ");


            for (int counter = 0; counter < lista.size(); counter++) {
                ArrayList lab = (ArrayList)lista.get(counter);
                labOld = lab.get(0).toString().trim();

                if ((!labOld.equalsIgnoreCase(labNew) && counter > 0)) {
                    log.info(lab.get(0).toString());
                    newarray.add(blanco);


                    log.info("Array vacio " + newarray);


                    newarray.add(lab);


                    log.info("Array anterior " + newarray);


                } else {
                    newarray.add(lab);

                    log.info("Array sin diferencia " + newarray);
                }

                labNew = (lab.get(0).toString()).trim();
            }

            log.info("Array dentro de array" + newarray);

            int j = 0;
            for (int i = 0; i < newarray.size(); i++) {
                j++;

                ArrayList lab = (ArrayList)newarray.get(i);


                String precioGen = "";
                if (dlgOrdenar.getTipoPrecioImp().equalsIgnoreCase("venta")) {
                    precioGen = (String)lab.get(9);
                } else if (dlgOrdenar.getTipoPrecioImp().equalsIgnoreCase("costo")) {
                    precioGen = (String)lab.get(12);
                }

                if (j >= lineFromIni && j <= lineToEnd && varRadioPagina &&
                    FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                    vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,
                                                                                                               i, 1),
                                // COD PRODUCTO
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2),
                                // DESC PRODUCTO
                                45) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),
                                // NOMBRE LABORATORIO
                                15) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 4),
                                /// U.VENTA
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.

                                getValueFieldArrayList(newarray, i, 5), // CANT ENTERO
                                7) + // ESPACIO LINEA
                            " / " +
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 6),
                                // CANT FRACC
                                7) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.


                                getValueFieldArrayList(newarray, i, 10),
                                // PRECIO UNITARIO AUDITOR
                                13) + // ESPACIO LINEA ESTABA EN 7

                            //  FarmaPRNUtility.alinearIzquierda(precioGen, 13) + // TOTAL PRDUCTOS * PRECIO (AUDITOR)
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 13),
                                // PRECIO AUDITOR
                                13) + // ESPACIO LINEA / ESTABA EN 7
                            FarmaPRNUtility.alinearDerecha("  /  ", 4) + // "/"
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 14),
                                // NUM ANAQUEL
                                5), // ESPACIO LINEA
                            true);
                }

                else if (j >= lineFromIni && j <= lineToEnd && varRadioPagina &&
                         FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                    vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,
                                                                                                               i, 1),
                                // COD PRODUCTO
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2),
                                // DESC PRODUCTO
                                45) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),
                                // NOMBRE LABORATORIO
                                15) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 4),
                                /// U.VENTA
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.

                                getValueFieldArrayList(newarray, i, 5), // CANT ENTERO
                                7) + // ESPACIO LINEA
                            " / " +
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 6),
                                // CANT FRACC
                                7) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.


                                getValueFieldArrayList(newarray, i, 11),
                                // PRECIO UNITARIO QUIMICO
                                13) + // ESPACIO LINEA ESTABA EN 7

                            //  FarmaPRNUtility.alinearIzquierda(precioGen, 13) + // TOTAL PRDUCTOS * PRECIO (AUDITOR)
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 12),
                                // PRECIO QUIMICO
                                13) + // ESPACIO LINEA / ESTABA EN 7
                            FarmaPRNUtility.alinearDerecha("  /  ", 4) + // "/"
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 14),
                                // NUM ANAQUEL
                                5), // ESPACIO LINEA
                            true);
                } else if (!varRadioPagina && FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                    vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,
                                                                                                               i, 1),
                                // COD PRODUCTO
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2),
                                // DESC PRODUCTO
                                45) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),
                                // NOMBRE LABORATORIO
                                15) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 4),
                                /// U.VENTA
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.

                                getValueFieldArrayList(newarray, i, 5), // CANT ENTERO
                                7) + // ESPACIO LINEA
                            " / " +
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 6),
                                // CANT FRACC
                                7) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.


                                getValueFieldArrayList(newarray, i, 10),
                                // PRECIO UNITARIO AUDITOR
                                13) + // ESPACIO LINEA ESTABA EN 7

                            //  FarmaPRNUtility.alinearIzquierda(precioGen, 13) + // TOTAL PRDUCTOS * PRECIO (AUDITOR)
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.


                                getValueFieldArrayList(newarray, i, 13), // PRECIO AUDITOR
                                13) + // ESPACIO LINEA / ESTABA EN 7
                            FarmaPRNUtility.alinearDerecha("  /  ",


                                4) + // "/"
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 14),
                                // NUM ANAQUEL
                                5), // ESPACIO LINEA
                            true);

                }

                else if (!varRadioPagina && FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                    vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray,
                                                                                                               i, 1),
                                // COD PRODUCTO
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 2),
                                // DESC PRODUCTO
                                45) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 0),
                                // NOMBRE LABORATORIO
                                15) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(newarray, i, 4),
                                /// U.VENTA
                                8) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.

                                getValueFieldArrayList(newarray, i, 5), // CANT ENTERO
                                7) + // ESPACIO LINEA
                            " / " +
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 6),
                                // CANT FRACC
                                7) + // ESPACIO LINEA
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.


                                getValueFieldArrayList(newarray, i, 11),
                                // PRECIO UNITARIO QUIMICO
                                13) + // ESPACIO LINEA ESTABA EN 7

                            //  FarmaPRNUtility.alinearIzquierda(precioGen, 13) + // TOTAL PRDUCTOS * PRECIO (AUDITOR)
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.


                                getValueFieldArrayList(newarray, i, 12), // PRECIO QUIMICO
                                13) + // ESPACIO LINEA / ESTABA EN 7
                            FarmaPRNUtility.alinearDerecha("  /  ",


                                4) + // "/"
                            FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(newarray, i, 14),
                                // NUM ANAQUEL
                                5), // ESPACIO LINEA
                            true);

                }


            }
            vPrint.activateCondensed();
            vPrint.printLine("=====================================================================================================================",
                             true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
        } catch (Exception sqlerr) {
            log.error("", sqlerr);
            FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                    sqlerr.getMessage(), txtProductos);
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


    private void imprimir() {
        if (!JConfirmDialog.rptaConfirmDialog(this, "¿Seguro que desea imprimir?"))
            return;


        //FarmaPrintService vPrint = new FarmaPrintService(66, "//10.18.6.241//reportes", true);
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", txtProductos);
            return;
        }
        try {
            String labNew = "", labOld = "";


            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.activateCondensed();
            vPrint.printLine(FarmaPRNUtility.llenarBlancos(27) + " REPORTE DE DIFERENCIAS DE PRODUCTOS", true);
            vPrint.printLine("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printLine("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
            vPrint.printLine("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);
            vPrint.printLine("Fecha Actual    : " + fechaActual, true);
            vPrint.printLine("===================================================================================================================================",
                             true);
            vPrint.printBold("Código  Nombre                                    Laboratorio       Fraccion   Cantidad             Total      Pre. Prom   Cant. Cont.  ",
                             true);
            vPrint.printLine("===================================================================================================================================",
                             true);
            for (int i = 0; i < lista.size(); i++) {
                ArrayList lab = (ArrayList)lista.get(i);
                labOld = lab.get(0).toString();

                if ((!labOld.equalsIgnoreCase(labNew) && i > 0)) {
                    vPrint.printLine("", true);
                }


                vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(lista, i,
                                                                                                           1), 8) +
                                      FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(lista, i,
                                                                                                           2), 40) +
                                      FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(lista, i,
                                                                                                           0), 20) +
                                      FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(lista, i, 4),
                                                                     5) +
                                      FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(lista, i, 5),
                                                                     10) + " / " +
                                      FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(lista, i,
                                                                                                           6), 10) +
                                      FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(lista, i, 11),
                                                                     10) +
                                      FarmaPRNUtility.alinearDerecha(FarmaUtility.getValueFieldArrayList(lista, i, 9),
                                                                     15) + FarmaPRNUtility.alinearDerecha("  /  ", 4),
                                      true);
                labNew = (lab.get(0).toString()).trim();
            }
            vPrint.activateCondensed();
            vPrint.printLine("=====================================================================================================================",
                             true);

        } catch (Exception sqlerr) {
            log.error("", sqlerr);
            FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                    sqlerr.getMessage(), txtProductos);
        }
    }


    /*
    private void imprimir() {
        if (!JConfirmDialog.rptaConfirmDialog(this, "¿Seguro que desea imprimir?"))
            return;

          FarmaPrintService vPrint = new FarmaPrintService(66,"//10.18.6.241//reportes", true);
       // FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", txtProductos);
            return;
        }
        try {
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.activateCondensed();
            vPrint.printLine(FarmaPRNUtility.llenarBlancos(27) + " REPORTE DE DIFERENCIAS DE PRODUCTOS", true);
            vPrint.printLine("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printLine("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
            vPrint.printLine("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);

            //vPrint.printLine("Tipo            : " +
            //                 VariablesTomaInv.vDescTipoToma.trim(), true);
            //vPrint.printLine("Estado          : " +
            //                 VariablesTomaInv.vDescEstadoToma.trim(), true);


            vPrint.printLine("Fecha Actual    : " + fechaActual, true);


            ArrayList pListaLaboratorios = new ArrayList();
            String pCodigoLab = "", pCodigoLabAux = "";

            DBTomaInv.getLabTomaInv(pListaLaboratorios);

            log.info("Lista de Laboratorios:" + pListaLaboratorios);

            String pCodigoLabImprimir = "", pCodLabEliminar = "";
            ArrayList pListaProductoLaboratorio = new ArrayList();

            for (int i = 0; i < pListaLaboratorios.size(); i++) {

                pCodigoLabImprimir = FarmaUtility.getValueFieldArrayList(pListaLaboratorios, i, 0);
                log.info("pCodigoLabImprimir:" + pCodigoLabImprimir);

                vPrint.printLine("Laboratorio     : " +
                                 FarmaUtility.getValueFieldArrayList(pListaLaboratorios, i, 0).trim() + " - " +
                                 FarmaUtility.getValueFieldArrayList(pListaLaboratorios, i, 1).trim(), true);
                vPrint.printLine("===============================================================================================================",
                                 true);
                vPrint.printBold("Código  Nombre                                    Laboratorio       Fraccio Cantidad Total  Pre. Prom   Cant. Cont.  ",
                                 true);
                vPrint.printLine("==============================================================================================================",
                                 true);
                pListaProductoLaboratorio = new ArrayList();
                DBTomaInv.getProductoLabTomaInv(pListaProductoLaboratorio, pCodigoLabImprimir);
                for (int a = 0; a < pListaProductoLaboratorio.size(); a++) {

                    vPrint.deactivateCondensed();

                    vPrint.printCondensed(
FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 1), 8) +
FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 2), 40) +
FarmaPRNUtility.alinearIzquierda(FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 0), 20) +
FarmaPRNUtility.alinearDerecha(  FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 4), 5) +
FarmaPRNUtility.alinearDerecha(  FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 5),10) + " / " +
FarmaPRNUtility.alinearDerecha(  FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 6),10) +
FarmaPRNUtility.alinearDerecha(  FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 11),10) +
FarmaPRNUtility.alinearDerecha(  FarmaUtility.getValueFieldArrayList(pListaProductoLaboratorio,a, 9),15)
,true);

                }
                vPrint.activateCondensed();
                vPrint.printLine("================================================================================================================",
                                 true);

            }


            vPrint.printBold("Registros Impresos por todos los Laboratorios: " +
                             FarmaUtility.formatNumber(tblRelacionDiferenciasProductos.getRowCount(), ",##0") +
                             FarmaPRNUtility.llenarBlancos(11), true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
            FarmaUtility.showMessage(this, "Por favor de recoger la Impresión", txtProductos);

        } catch (Exception sqlerr) {
            log.error("", sqlerr);
            FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                    sqlerr.getMessage(), txtProductos);
        }
    }
                */

    private void imprimirDiferencias() {
        log.info("imprimirDiferencias");
        if (!JConfirmDialog.rptaConfirmDialog(this, "Seguro que desea imprimir?"))
            return;
        //FarmaPrintService vPrint = new FarmaPrintService(45,
        log.info("start Service");
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", txtProductos);
            return;
        }
        log.info("antes del TRY");
        try {
            log.info("antes de fecha actual");
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            log.info("despues de fecha actual");
            //vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printLine(FarmaPRNUtility.llenarBlancos(27) + " REPORTE DE DIFERENCIAS DE PRODUCTOS", true);
            vPrint.printLine("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printLine("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
            vPrint.printLine("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);
            /*
            vPrint.printLine("Tipo            : " +
                             VariablesTomaInv.vDescTipoToma.trim(), true);
            vPrint.printLine("Estado          : " +
                             VariablesTomaInv.vDescEstadoToma.trim(), true);
            */
            /*vPrint.printLine("Laboratorio     : " + VariablesTomaInv.vCodLab +
                             " - " + VariablesTomaInv.vNomLab.trim(), true);
            */
            vPrint.printLine("Fecha Actual    : " + fechaActual, true);

            log.info("antes del cambio");
            ///ESTO SE DEBE IMPRIMIR POR BLOQUE DE LABORATORIO
            ArrayList pListaLaboratorios = new ArrayList();
            DBTomaInv.getLabTomaInv(pListaLaboratorios);

            log.info("Lista de Laboratorios:" + pListaLaboratorios);
            String pCodigoLabImprimir = "", pCodLabEliminar = "";
            ArrayList pListaProductoLaboratorio = new ArrayList();
            int cantidadImpresos = 0;

            for (int i = 0; i < pListaLaboratorios.size(); i++) {
                pCodigoLabImprimir = FarmaUtility.getValueFieldArrayList(pListaLaboratorios, i, 0).trim();
                log.info("pCodigoLabImprimir:" + pCodigoLabImprimir);

                DBTomaInv.getProductoLabTomaInv(pListaProductoLaboratorio, pCodigoLabImprimir);

                log.info("pListaProductoLaboratorio:" + pListaProductoLaboratorio.size());
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
                    //log.info("Linea 1");
                    //log.info("Linea 2");

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

            vPrint.printBold("Registros Impresos: " +
                             FarmaUtility.formatNumber( //tblRelacionDiferenciasProductos.getRowCount(),
                        cantidadImpresos, ",##0") + FarmaPRNUtility.llenarBlancos(11), true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
            FarmaUtility.showMessage(this, "Por favor de recoger la Impresión", txtProductos);
        } catch (Exception sqlerr) {
            log.error("", sqlerr);
            FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                    sqlerr.getMessage(), txtProductos);
        }
    }


    private void btnRelacionProductos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblRelacionDiferenciasProductos);
    }

    private void tblRelacionDiferenciasProductos_mouseReleased(MouseEvent e) {
        
        txtProductos.requestFocus();
        
    }
}
