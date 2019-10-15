package mifarma.ptoventa.tomainventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
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

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaPrintService;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.tomainventario.reference.ConstantsTomaInv;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgProductosPorToma extends JDialog {
    FarmaTableModel tableModelProductosToma;
    private static final Logger log = LoggerFactory.getLogger(DlgProductosPorToma.class);

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

    private JLabelFunction lblEnter = new JLabelFunction();

    private JLabelFunction lblF5 = new JLabelFunction();


    private JLabelFunction lblEscape = new JLabelFunction();

    private JLabelWhite lblLaboratorio_T = new JLabelWhite();

    private JLabelWhite lblLaboratorio = new JLabelWhite();

    private JLabelFunction lblRellenar = new JLabelFunction();

    // JVARA 02-10-2017
    private JPanelTitle pnlTitle2 = new JPanelTitle();
    private JPanelHeader jPanelHeader2 = new JPanelHeader();
    private JLabelOrange jLabelOrange1 = new JLabelOrange();
    private JLabel lblTotalProductosT = new JLabel();
    private JLabel lblTotalProductos = new JLabel();
    private JLabelFunction lblF3 = new JLabelFunction();
    private int rowGeneral;

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgProductosPorToma() {
        this(null, "", false);
    }

    public DlgProductosPorToma(Frame parent, String title, boolean modal) {
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
    //jPanelHeader1.setBackground(Color.white);  // JVARA 02-10-2017

    private void jbInit() throws Exception {
        this.setSize(new Dimension(811, 494));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Productos por Laboratorio por Toma de Inventario");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanelHeader1.setBounds(new Rectangle(15, 10, 755, 50));

        jPanelHeader1.setLayout(null);
        jPanelTitle1.setBounds(new Rectangle(15, 65, 755, 25));
        jPanelTitle1.setLayout(null);
        jScrollPane1.setBounds(new Rectangle(15, 90, 755, 260));
        btnRelacionProductos.setText("Relacion de Productos");
        btnRelacionProductos.setBounds(new Rectangle(10, 0, 140, 25));
        btnRelacionProductos.setMnemonic('r');
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
        lblEnter.setBounds(new Rectangle(15, 425, 135, 20));
        lblEnter.setText("[ ENTER ] Seleccionar");
        lblEnter.setBackground(SystemColor.window);
        lblEnter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEnter.setFont(new Font("SansSerif", 1, 11));
        lblEnter.setForeground(new Color(32, 105, 29));
        lblF5.setBounds(new Rectangle(335, 425, 135, 20));
        lblF5.setText("[ F5 ] Ver Diferencias");
        lblF5.setBackground(SystemColor.window);
        lblF5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF5.setFont(new Font("SansSerif", 1, 11));
        lblF5.setForeground(new Color(32, 105, 29));
        lblEscape.setBounds(new Rectangle(655, 425, 110, 20));
        lblEscape.setText("[ ESCAPE ] Cerrar");
        lblEscape.setBackground(SystemColor.window);
        lblEscape.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEscape.setFont(new Font("SansSerif", 1, 11));
        lblEscape.setForeground(new Color(32, 105, 29));
        lblLaboratorio_T.setText("Laboratorio :");
        lblLaboratorio_T.setBounds(new Rectangle(30, 5, 80, 15));
        lblLaboratorio.setText("Laboratorios Unidos S. A.");
        lblLaboratorio.setBounds(new Rectangle(120, 5, 410, 15));
        lblRellenar.setBounds(new Rectangle(485, 425, 155, 20));
        lblRellenar.setText("[ F2 ] Completar con ceros");

        // JVARA 02-10-2017  INICIO
        lblRellenar.setBackground(SystemColor.window);
        lblRellenar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblRellenar.setFont(new Font("SansSerif", 1, 11));
        lblRellenar.setForeground(new Color(32, 105, 29));
        pnlTitle2.setBounds(new Rectangle(15, 350, 755, 25));
        pnlTitle2.setLayout(null);
        jPanelHeader2.setBounds(new Rectangle(15, 375, 755, 35));
        jPanelHeader2.setBackground(Color.white);
        jLabelOrange1.setText("LOS PRODUCTOS INACTIVOS CON STOCK SE MUESTRAN EN COLOR ROJO");
        jLabelOrange1.setBounds(new Rectangle(5, 5, 710, 20));
        jLabelOrange1.setForeground(Color.red);
        jLabelOrange1.setFont(new Font("DialogInput", 1, 15));

        // JVARA 02-10-2017  FIN

        lblTotalProductosT.setText("Total de Productos:");
        lblTotalProductosT.setBounds(new Rectangle(10, 0, 115, 25));
        lblTotalProductosT.setFont(new Font("SansSerif", 1, 11));
        lblTotalProductosT.setForeground(SystemColor.window);
        lblTotalProductos.setText("0");
        lblTotalProductos.setBounds(new Rectangle(130, 0, 55, 25));
        lblTotalProductos.setFont(new Font("SansSerif", 1, 11));
        lblTotalProductos.setForeground(SystemColor.window);
        lblTotalProductos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalProductos.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblF3.setBounds(new Rectangle(180, 425, 115, 20));
        lblF3.setText("[ F3 ] Detalle");
        lblF3.setFont(new Font("SansSerif", 1, 11));
        lblF3.setForeground(new Color(32, 105, 29));
        lblF3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF3.setBackground(SystemColor.window);
        jContentPane.add(lblF3, null);
        jContentPane.add(lblRellenar, null);
        jContentPane.add(lblEscape, null);
        jContentPane.add(lblF5, null);
        jContentPane.add(lblEnter, null);
        jScrollPane1.getViewport().add(tblRelacionProductos, null);
        jContentPane.add(jScrollPane1, null);
        jPanelTitle1.add(btnRelacionProductos, null);
        jContentPane.add(jPanelTitle1, null);


        jPanelHeader2.add(jLabelOrange1, null);
        pnlTitle2.add(lblTotalProductos, null);
        pnlTitle2.add(lblTotalProductosT, null);
        jContentPane.add(pnlTitle2, null); // JVARA 02-10-2017
        jPanelHeader1.add(lblLaboratorio, null);
        jPanelHeader1.add(lblLaboratorio_T, null);
        jPanelHeader1.add(txtProductos, null);
        jPanelHeader1.add(btnProductos, null);
        jContentPane.add(jPanelHeader1, null);


        jPanelHeader2.add(jLabelOrange1, null);
        jContentPane.add(jPanelHeader2, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableListaProductosXLaboratorio();
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_INVENTARIADOR)) {
            lblF5.setEnabled(false);
            lblRellenar.setEnabled(false);
        }
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTableListaProductosXLaboratorio() {
        tableModelProductosToma =
                new FarmaTableModel(ConstantsTomaInv.columnsListaProductosXLaboratorio, ConstantsTomaInv.defaultValuesListaProductosXLaboratorio,
                                    0);
        FarmaUtility.initSimpleList(tblRelacionProductos, tableModelProductosToma,
                                    ConstantsTomaInv.columnsListaProductosXLaboratorio);
        cargaListaProductosXLaboratorio();
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtProductos);
        FarmaUtility.setearPrimerRegistro(tblRelacionProductos, txtProductos, 1);
        mostrarDatos();
    }

    private void btnProductos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProductos);
    }

    private void txtProductos_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblRelacionProductos, txtProductos, 1);
        if (lblEnter.isVisible()) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (FarmaVariables.vEconoFar_Matriz)
                    FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, txtProductos);
                else {
                    e.consume();
                    if (tblRelacionProductos.getSelectedRow() >= 0) {
                        if (!(FarmaUtility.findTextInJTable(tblRelacionProductos, txtProductos.getText().trim(), 0,
                                                            1))) {
                            FarmaUtility.showMessage(this, "Producto No Encontrado según Criterio de Búsqueda !!!",
                                                     txtProductos);
                            return;
                        }

                        else if (!VariablesTomaInv.vTipOp.equals(ConstantsTomaInv.TIPO_OPERACION_CONSULTA_HIST))
                            if (tieneRegistroSeleccionado(tblRelacionProductos)) {
                                cargarRegistroSeleccionado();
                                DlgIngresoCantidadToma dlgIngresoCantidadToma =
                                    new DlgIngresoCantidadToma(this.myParentFrame, "", true);
                                dlgIngresoCantidadToma.setVisible(true);
                                if (FarmaVariables.vAceptar) {
                                    cargaListaProductosXLaboratorio();
                                    FarmaVariables.vAceptar = false;
                                    FarmaGridUtils.showCell(tblRelacionProductos, VariablesTomaInv.vRegActual, 0);
                                }
                                txtProductos.selectAll();
                            }
                    }
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
        } else if (e.getKeyCode() == KeyEvent.VK_F5 && lblF5.isEnabled()) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                DlgListaDiferenciasProductos dlgListaDiferenciasProductos =
                    new DlgListaDiferenciasProductos(myParentFrame, "", true);
                dlgListaDiferenciasProductos.setVisible(true);
            } else {
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción",
                                         txtProductos);
            }
        } else if (UtilityPtoVenta.verificaVK_F2(e) && lblRellenar.isEnabled()) {
            if (lblRellenar.isVisible()) {
                if (FarmaVariables.vEconoFar_Matriz)
                    FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, txtProductos);
                else {
                    if (!VariablesTomaInv.vTipOp.equals(ConstantsTomaInv.TIPO_OPERACION_CONSULTA_HIST))
                        if (tieneRegistros(this.tblRelacionProductos)) {
                            if (JConfirmDialog.rptaConfirmDialog(this,
                                                                 "¿Esta seguro de completar los registros no ingresados con ceros?")) {
                                try {
                                    rellenarConCeros();
                                    FarmaUtility.aceptarTransaccion();
                                    cargaListaProductosXLaboratorio();
                                    FarmaUtility.showMessage(this, "La operación se realizó correctamente",
                                                             txtProductos);
                                } catch (SQLException sql) {
                                    FarmaUtility.liberarTransaccion();
                                    log.error("", sql);
                                    FarmaUtility.showMessage(this, "Ocurrió un error al completar con ceros: \n" +
                                            sql.getMessage(), txtProductos);
                                }
                            }
                        }
                }
            }
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            imprimir();
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                e.consume();
                verDetalle();
            }
        }
    }

    void verDetalle() {
        rowGeneral = tblRelacionProductos.getSelectedRow();
        String coProducto = (String)tblRelacionProductos.getValueAt(rowGeneral, 0);
        ArrayList lista = new ArrayList();
        try {
            DBTomaInv.getListaDetallePdaDigitacion(lista, coProducto);
            
            String CaEntero = (String)((ArrayList)lista.get(0)).get(0);
            String CaEnteroPda = (String)((ArrayList)lista.get(0)).get(1);
            String CaFraccion = (String)((ArrayList)lista.get(0)).get(2);
            String CaFraccionPda = (String)((ArrayList)lista.get(0)).get(3);

            DlgDetalle detalle = new DlgDetalle(myParentFrame, "Detalle", true);
            detalle.getLblDigitacion().setText(CaEntero);
            detalle.getLblDigitacionFrac().setText(CaFraccion);
            detalle.getLblPda().setText(CaEnteroPda);
            detalle.getLblPdaFrac().setText(CaFraccionPda);
            detalle.setVisible(true);
            FarmaGridUtils.showCell(tblRelacionProductos, rowGeneral, 0);
        } catch (SQLException e) {
            log.error("Se produjo un error al mostrar detalle",e);
        }


    }


    private void chkKeyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblRelacionProductos, txtProductos, 1);
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void cargaListaProductosXLaboratorio() {
        try {
            DBTomaInv.getListaProdsXLabXToma(tableModelProductosToma);
            if (tblRelacionProductos.getRowCount() > 0)
                FarmaUtility.ordenar(tblRelacionProductos, tableModelProductosToma, 1,
                                     FarmaConstants.ORDEN_ASCENDENTE);

            //JVARA 29-09-2017 INICIO
            ArrayList rowsWithOtherColor = new ArrayList();
            for (int i = 0; i < tableModelProductosToma.data.size(); i++) {
                if (((ArrayList)tableModelProductosToma.data.get(i)).get(7).toString().trim().equalsIgnoreCase("I")) {
                    rowsWithOtherColor.add(String.valueOf(i));
                }
            }
            FarmaUtility.initSimpleListCleanColumns(tblRelacionProductos, tableModelProductosToma,
                                                    ConstantsTomaInv.columnsListaProductosXLaboratorio,
                                                    rowsWithOtherColor, Color.white, Color.red, false);

            //JVARA 29-09-2017 FIN
            //DFLORES: a solicitud de Sara debe mostrar los productos con stock 0 11/04/2019
            //if (tblRelacionProductos.getRowCount() > 0) {
            lblTotalProductos.setText(String.valueOf(tblRelacionProductos.getRowCount()));
            this.setVisible(true);
            /*} else {
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, "No existen productos para este laboratorio",
                                              "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);


            }*/

            log.debug("se cargo la lista de de prods");
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al obtener la lista de productos : \n" +
                    sql.getMessage(), txtProductos);
        }
    }

    private void mostrarDatos() {
        lblLaboratorio.setText(VariablesTomaInv.vNomLab.trim());
        if (VariablesTomaInv.vTipOp.equals(ConstantsTomaInv.TIPO_OPERACION_CONSULTA_HIST)) {
            lblEnter.setVisible(false);
            lblRellenar.setVisible(false);
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
        VariablesTomaInv.vCodProd = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 0).toString().trim();
        VariablesTomaInv.vDesProd = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 1).toString().trim();
        VariablesTomaInv.vUnidPresentacion = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 2).toString().trim();
        VariablesTomaInv.vUnidVta = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 6).toString().trim();
        VariablesTomaInv.vCantEntera = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 3).toString().trim();
        VariablesTomaInv.vCantEntera = VariablesTomaInv.vCantEntera.replace(",", "");
        VariablesTomaInv.vCantFraccion = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 4).toString().trim();
        VariablesTomaInv.vCantFraccion = VariablesTomaInv.vCantFraccion.replace(",", "");
        VariablesTomaInv.vFraccion = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 5).toString().trim();
        VariablesTomaInv.vRegActual = tblRelacionProductos.getSelectedRow();
        VariablesTomaInv.Vanaquel = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 8).toString().trim();
        VariablesTomaInv.Vorigen = tblRelacionProductos.getValueAt(tblRelacionProductos.getSelectedRow(), 9).toString().trim();

        //  log.debug("este es el valor del origen "+VariablesTomaInv.Vorigen);


    }

    private boolean tieneRegistros(JTable tbl) {
        if (tbl.getRowCount() > 0) {
            return true;
        } else
            return false;
    }

    private void rellenarConCeros() throws SQLException {
        DBTomaInv.rellenaCantNoIngresadas();
    }

    private void imprimir() {
        if (!JConfirmDialog.rptaConfirmDialog(this, "Seguro que desea imprimir?"))
            return;
        //FarmaPrintService vPrint = new FarmaPrintService(45,
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresión", txtProductos);
            return;
        }
        try {
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printBold(FarmaPRNUtility.llenarBlancos(27) + " REPORTE PRODUCTOS POR TOMA DE INVENTARIO ", true);
            vPrint.printBold("Nombre Compañia : " + FarmaVariables.vNomCia.trim(), true);
            vPrint.printBold("Toma #          : " + VariablesTomaInv.vSecToma.trim(), true);
            vPrint.printBold("Tipo            : " + VariablesTomaInv.vDescTipoToma.trim(), true);
            vPrint.printBold("Fecha Inicio    : " + VariablesTomaInv.vFecIniToma.trim(), true);
            vPrint.printBold("Fecha Fin       : " + VariablesTomaInv.vFecFinToma.trim(), true);
            vPrint.printBold("Estado          : " + VariablesTomaInv.vDescEstadoToma.trim(), true);
            vPrint.printBold("Laboratorio     : " + VariablesTomaInv.vNomLab.trim(), true);
            vPrint.printBold("Fecha Actual    : " + fechaActual, true);


            vPrint.printLine("================================================================================================",
                             true);
            vPrint.printBold("Codigo        Descripcion                      Unidad              Stock         Inventario ",
                             true);
            vPrint.printLine("================================================================================================",
                             true);
            vPrint.deactivateCondensed();
            vPrint.setEndHeader();
            for (int i = 0; i < tblRelacionProductos.getRowCount(); i++) {

                vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 0),
                                                                       14) +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 1),
                                                                       33) +
                                      FarmaPRNUtility.alinearIzquierda((String)tblRelacionProductos.getValueAt(i, 2),
                                                                       16) +
                                      FarmaPRNUtility.alinearDerecha((String)tblRelacionProductos.getValueAt(i, 3),
                                                                     9) + "         ________", true);
            }
            vPrint.activateCondensed();
            vPrint.printLine("================================================================================================",
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

}
