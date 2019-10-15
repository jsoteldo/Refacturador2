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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaPrintService;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.tomainventario.reference.ConstantsTomaInv;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgListaItemsLaboratorio extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgListaItemsLaboratorio.class);
    //private String IND_HABILITAR = "S"; //RPASCACIO 18/05/2017
    FarmaTableModel tableModel;

    Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanelWhite jContentPane = new JPanelWhite();

    private JPanelHeader jPanelHeader1 = new JPanelHeader();

    private JPanelTitle jPanelTitle1 = new JPanelTitle();

    private JScrollPane jScrollPane1 = new JScrollPane();

    private JTable tblRelacionProductos = new JTable();

    private JButtonLabel btnRelacionProductos = new JButtonLabel();

    private JButtonLabel btnProductos = new JButtonLabel();

    private JTextFieldSanSerif txtProductos = new JTextFieldSanSerif();

    private JLabelFunction lblF6 = new JLabelFunction();

    private JLabelFunction lblF7 = new JLabelFunction();

    private JLabelFunction lblEscape = new JLabelFunction();

    private JLabelWhite lblLaboratorio_T = new JLabelWhite();

    private JLabelWhite lblLaboratorio = new JLabelWhite();

    private JPanelTitle pnlFooter = new JPanelTitle();

    private JLabelWhite lblCantReg_T = new JLabelWhite();

    private JLabelWhite lblCantReg = new JLabelWhite();
    private JLabelFunction lblF3 = new JLabelFunction();
    private JTextArea txtAF9 = new JTextArea();
    private JLabelFunction lblF8 = new JLabelFunction();
    private JLabelFunction lblF9 = new JLabelFunction();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgListaItemsLaboratorio() {
        this(null, "", false);
    }

    public DlgListaItemsLaboratorio(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
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
        this.setSize(new Dimension(763, 504));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Lista de Items por Laboratorio");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanelHeader1.setBounds(new Rectangle(15, 10, 730, 50));
        jPanelHeader1.setLayout(null);
        jPanelTitle1.setBounds(new Rectangle(15, 65, 730, 25));
        jPanelTitle1.setLayout(null);
        jScrollPane1.setBounds(new Rectangle(15, 90, 730, 280));
        btnRelacionProductos.setText("Relacion de Productos");
        btnRelacionProductos.setBounds(new Rectangle(10, 0, 140, 25));
        btnRelacionProductos.setMnemonic('r');
        btnRelacionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionProductos_actionPerformed(e);
            }
        });
        btnProductos.setText("Productos :");
        btnProductos.setMnemonic('p');
        btnProductos.setBounds(new Rectangle(30, 25, 65, 20));
        btnProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnProductos_actionPerformed(e);
            }
        });
        txtProductos.setBounds(new Rectangle(115, 25, 305, 20));
        txtProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtProductos_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtProductos_keyReleased(e);
            }
        });
        lblF6.setBounds(new Rectangle(15, 400, 145, 45));
        lblF6.setText("[ F6 ] Imprimir sin stock");
        lblF6.setFont(new Font("SansSerif", 1, 11));
        lblF6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF6.setBackground(SystemColor.window);
        lblF6.setForeground(new Color(32, 105, 29));
        lblF7.setBounds(new Rectangle(170, 400, 155, 45));
        lblF7.setText("<html><center>[ F7 ] Imprimir con stock</center></html>");        
        
        lblF7.setFont(new Font("SansSerif", 1, 11));
        lblF7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF7.setBackground(SystemColor.window);
        lblF7.setForeground(new Color(32, 105, 29));
        lblEscape.setBounds(new Rectangle(655, 400, 90, 50));
        lblEscape.setText("[ Esc ] Salir");
        lblEscape.setFont(new Font("SansSerif", 1, 11));
        lblEscape.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEscape.setBackground(SystemColor.window);
        lblEscape.setForeground(new Color(32, 105, 29));
        lblLaboratorio_T.setText("Laboratorio :");
        lblLaboratorio_T.setBounds(new Rectangle(30, 5, 80, 15));
        lblLaboratorio.setText("Laboratorios Unidos S. A.");
        lblLaboratorio.setBounds(new Rectangle(120, 5, 410, 15));
        pnlFooter.setBounds(new Rectangle(15, 370, 730, 25));
        pnlFooter.setLayout(null);
        lblCantReg_T.setText("Cantidad de registros:");
        lblCantReg_T.setBounds(new Rectangle(10, 5, 125, 15));
        lblCantReg.setText("0");
        lblCantReg.setBounds(new Rectangle(140, 5, 60, 15));


        //RPASCACIO 18-05-2017
        //RPASCACIO 18-05-2017
        //RPASCACIO 18-05-2017
        //RPASCACIO 18-05-2017
        //RPASCACIO 18-05-2017
        lblF8.setText("<html><center>[F8] Filtrar Productos con<br>Movimiento de Inventario<br>(\u00daltimos 12 meses)</center></html>");
        lblF8.setBounds(new Rectangle(340, 400, 170, 65));
        lblF8.setFont(new Font("SansSerif", 1, 11));
        lblF8.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF8.setBackground(SystemColor.window);
        lblF8.setForeground(new Color(32, 105, 29));
        lblF9.setText("<html><center>[F9] Ver Todos<br>los Productos</center></html>");
        lblF9.setBounds(new Rectangle(520, 400, 125, 50));
        lblF9.setFont(new Font("SansSerif", 1, 11));
        lblF9.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF9.setBackground(SystemColor.window);
        lblF9.setForeground(new Color(32, 105, 29));
        pnlFooter.add(lblCantReg, null);
        pnlFooter.add(lblCantReg_T, null);
        jContentPane.add(lblF9, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(pnlFooter, null);
        jContentPane.add(lblEscape, null);
        jContentPane.add(lblF7, null);
        jContentPane.add(lblF6, null);
        jScrollPane1.getViewport().add(tblRelacionProductos, null);
        jContentPane.add(jScrollPane1, null);
        jPanelTitle1.add(btnRelacionProductos, null);
        jContentPane.add(jPanelTitle1, null);
        jPanelHeader1.add(lblLaboratorio, null);
        jPanelHeader1.add(lblLaboratorio_T, null);
        jPanelHeader1.add(txtProductos, null);
        jPanelHeader1.add(btnProductos, null);
        jContentPane.add(jPanelHeader1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableListaProductosXLaboratorio();
        mostrarCant();
        
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTableListaProductosXLaboratorio() {
        tableModel =
                new FarmaTableModel(ConstantsTomaInv.columnsListaProductosIxL, ConstantsTomaInv.defaultValuesListaProductosIxL,
                                    0);
        FarmaUtility.initSimpleList(tblRelacionProductos, tableModel, ConstantsTomaInv.columnsListaProductosIxL);
        cargaListaProductos();
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtProductos);
        mostrarDatos();
        FarmaUtility.setearPrimerRegistro(tblRelacionProductos, txtProductos, 1);
        if(permiteImprimirConStock().trim().equalsIgnoreCase("S"))
           lblF7.setVisible(true);
        else
           lblF7.setVisible(false);
    }

    private void btnRelacionProductos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblRelacionProductos);
    }

    private void btnProductos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProductos);
    }

    private void txtProductos_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblRelacionProductos, txtProductos, 1);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblRelacionProductos.getSelectedRow() >= 0) {
                if (!(FarmaUtility.findTextInJTable(tblRelacionProductos, txtProductos.getText().trim(), 0, 1))) {
                    FarmaUtility.showMessage(this, "Producto No Encontrado según Criterio de Búsqueda !!!",
                                             txtProductos);
                    return;
                }
            }
        }
        chkKeyPressed(e);
    }

    private void txtProductos_keyReleased(KeyEvent e) {
        chkKeyReleased(e);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_F6) {
            if(lblF6.isVisible())
                imprimir(false);
            else
                FarmaUtility.showMessage(this, "No tiene activado esta función", txtProductos);
        } else if (e.getKeyCode() == KeyEvent.VK_F7) {
            //INICIO RPASCACIO 18-05-2017 
            if(lblF7.isVisible())
                imprimir(true);
            else
                FarmaUtility.showMessage(this, "No tiene activado esta función", txtProductos);
            //FIN RPASCACIO 
           
        } else if (e.getKeyCode() == KeyEvent.VK_F8) {
           // cargaListaProductosMovimiento();
           if(lblF8.isVisible())
            cargaListaProductosMovimiento();
            else
                FarmaUtility.showMessage(this, "No tiene activado esta función", txtProductos);
        } else if (e.getKeyCode() == KeyEvent.VK_F9) {
            if(lblF9.isVisible()){
                cargaListaProductos();
                FarmaUtility.showMessage(this, "Se listan todos los productos del laboratorio.", txtProductos);
            }
            else
                FarmaUtility.showMessage(this, "No tiene activado esta función", txtProductos);
        }
    }

    private void chkKeyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblRelacionProductos, txtProductos, 1);
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void cargaListaProductos() {
        try {
            DBTomaInv.getListaItemsxLab(tableModel);
            if (tblRelacionProductos.getRowCount() > 0)
                FarmaUtility.ordenar(tblRelacionProductos, tableModel, 1, FarmaConstants.ORDEN_ASCENDENTE);
            mostrarCant();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al cargar la lista de productos : \n" +
                    sql.getMessage(), txtProductos);
        }
    }

    private void cargaListaProductosMovimiento() {
        try {
            DBTomaInv.getListaItemsxLabMovimiento(tableModel);
            if (tblRelacionProductos.getRowCount() > 0){
                FarmaUtility.ordenar(tblRelacionProductos, tableModel, 1, FarmaConstants.ORDEN_ASCENDENTE);
            }
            mostrarCant();
            FarmaUtility.showMessage(this, "Se filtraron los productos del laboratorio asociado a la fila seleccionada.\n" +
                                            "Que tenian stock y que se hicieron inventario en los ultimos 12 meses", txtProductos);
            
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al cargar la lista de productos : \n" +
                    sql.getMessage(), txtProductos);
        }
    }

    private void mostrarDatos() {
        lblLaboratorio.setText(VariablesTomaInv.vNomLab.trim());
    }

    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;
        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    private boolean tieneRegistros(JTable tbl) {
        if (tbl.getRowCount() > 0) {
            return true;
        } else
            return false;
    }

    private void mostrarCant() {
        lblCantReg.setText("" + tblRelacionProductos.getRowCount());
    }

    private void imprimir(boolean incStock) {
        if (!JConfirmDialog.rptaConfirmDialog(this, "Seguro que desea imprimir?"))
            return;

        //--FarmaPrintService vPrint = new FarmaPrintService(65,
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", txtProductos);
            return;
        }
        try {
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            String campoAlt = "________";
            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printBold(FarmaPRNUtility.llenarBlancos(27) + " REPORTE ITEMS POR LABORATORIO", true);
            vPrint.printBold("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printBold("Nombre Local : " + FarmaVariables.vDescLocal.trim(), true);
            vPrint.printBold("Laboratorio: " + VariablesTomaInv.vCodLab + " - " + VariablesTomaInv.vNomLab.trim(),
                             true);
            vPrint.printBold("Fecha: " + fechaActual, true);

            vPrint.printLine("=============================================================================================================",
                             true);
            vPrint.printBold("Codigo  Descripcion                                Unid Presentacion  Unidad Venta       Entero    Fraccion  ",
                             true);
            vPrint.printLine("=============================================================================================================",
                             true);
            vPrint.deactivateCondensed();
            vPrint.setEndHeader();
            for (int i = 0; i < tblRelacionProductos.getRowCount(); i++) {

                if (incStock) {
                    campoAlt =
                            FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 5), 10) + FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i,
                                                                                                                                                                                           6),
                                                                                                                                                   10);
                } else {
                    campoAlt =
                            FarmaPRNUtility.alinearIzquierda("_______", 10) + FarmaPRNUtility.alinearIzquierda("_______",
                                                                                                               10);
                }

                vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 0),
                                                                       8) +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 1),
                                                                       44) +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 2),
                                                                       19) +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 3),
                                                                       18) + " " + campoAlt, true);
            }
            vPrint.activateCondensed();
            vPrint.printLine("=============================================================================================================",
                             true);
            vPrint.printBold("Registros Impresos: " +
                             FarmaUtility.formatNumber(tblRelacionProductos.getRowCount(), ",##0") +
                             FarmaPRNUtility.llenarBlancos(11), true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
        } catch (Exception sqlerr) {
            log.error("", sqlerr);
            FarmaUtility.showMessage(this, "Ocurrió un error al imprimir : \n" +
                    sqlerr.getMessage(), txtProductos);
        }
    }

    private String permiteImprimirConStock() {
        String pIndPermite = "N";
        try {
            pIndPermite = DBTomaInv.getIndPermiteImpConStock();
        } catch (SQLException sql) {
            log.error("", sql);
        }
        return pIndPermite;
    }
}
