package mifarma.ptoventa.tomainventario;

import com.gs.mifarma.componentes.JLabelFunction;

import com.gs.mifarma.componentes.JLabelWhite;

import com.gs.mifarma.componentes.JPanelTitle;

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

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaPrintService;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.tomainventario.reference.ConstantsTomaInv;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;

import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import mifarma.ptoventa.tomainventario.valueobjects.ProductoLaboratorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgProductosTotales extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgProductosTotales.class);

    FarmaTableModel tableModelProductoLaboratorio;
    Frame parentFrame;
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable tblProductoLab = new JTable();
    private JPanel jContentPane = new JPanel();
    private JPanel pnlBusqueda = new JPanel();
    private JTextField txtFindText = new JTextField();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JPanel pnlProductos = new JPanel();
    private JLabelWhite lblRProductos = new JLabelWhite();
    private JPanel pnlTProductos = new JPanel();
    private JLabelWhite lblTProductos = new JLabelWhite();
    private JLabelWhite lblTotalProductos = new JLabelWhite();
    private boolean isHistorico = false;
    String origen = "T";
    private JLabelFunction lblF12 = new JLabelFunction();
    ArrayList lista = new ArrayList();
    private JLabelWhite lblEstTabla = new JLabelWhite();
    private JLabelFunction lblF3 = new JLabelFunction();
    private int rowGeneral;
    private JLabel lblEnter = new JLabel();
    public JLabel lblLaboratorio = new JLabel();

    public DlgProductosTotales() {
        this(null, "", false);
    }

    public DlgProductosTotales(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            initialize();
            cantidadProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public DlgProductosTotales(Frame parent, String title, boolean modal, boolean isHistorico) {
        this(parent, title, modal);
        this.isHistorico = isHistorico;
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(767, 583));
        this.getContentPane().setLayout(null);
        this.setTitle("Lista de Laboratorio por Toma Inventario");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jScrollPane1.setBounds(new Rectangle(15, 110, 735, 360));
        tblProductoLab.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                    tblProductoLab_mouseReleased(e);
                }
        });
        jContentPane.setBounds(new Rectangle(0, 0, 765, 555));
        jContentPane.setLayout(null);
        jContentPane.setBackground(SystemColor.window);
        pnlBusqueda.setBounds(new Rectangle(15, 10, 735, 60));
        pnlBusqueda.setLayout(null);
        pnlBusqueda.setBorder(BorderFactory.createTitledBorder("Ingrese critero de búsqueda"));
        pnlBusqueda.setOpaque(false);
        txtFindText.setBounds(new Rectangle(25, 20, 480, 25));
        txtFindText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFindText_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFindText_keyReleased(e);
            }
        });
        lblF5.setBounds(new Rectangle(425, 515, 135, 20));
        lblF5.setText("[ F5 ] Ver Diferencia");
        lblF5.setFont(new Font("SansSerif", 1, 11));
        lblF5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF5.setOpaque(false);
        lblF5.setForeground(new Color(32, 105, 29));
        lblF5.setBackground(SystemColor.window);
        pnlProductos.setBounds(new Rectangle(15, 80, 735, 30));
        pnlProductos.setBackground(new Color(255, 130, 14));
        pnlProductos.setLayout(null);
        lblRProductos.setText("Relaci\u00f3n de Productos");
        lblRProductos.setBounds(new Rectangle(20, 5, 170, 20));
        lblRProductos.setFont(new Font("SansSerif", 1, 12));
        pnlTProductos.setBounds(new Rectangle(15, 470, 735, 30));
        pnlTProductos.setBackground(new Color(255, 130, 14));
        pnlTProductos.setLayout(null);
        lblTProductos.setText("Total de Productos:");
        lblTProductos.setBounds(new Rectangle(15, 5, 120, 20));
        lblTProductos.setFont(new Font("SansSerif", 1, 12));
        lblTotalProductos.setBounds(new Rectangle(135, 5, 55, 20));
        lblF12.setBounds(new Rectangle(590, 515, 160, 20));
        lblF12.setText("[ F12 ] Imprimir Cantidades");
        lblF12.setFont(new Font("SansSerif", 1, 11));
        lblF12.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF12.setOpaque(false);
        lblF12.setForeground(new Color(32, 105, 29));
        lblF12.setBackground(SystemColor.window);
        lblEstTabla.setText("jLabelWhite1");
        lblEstTabla.setBounds(new Rectangle(205, 5, 105, 20));
        lblF3.setBounds(new Rectangle(280, 515, 115, 20));
        lblF3.setText("[ F3 ] Ver detalle");
        lblF3.setFont(new Font("SansSerif", 1, 11));
        lblF3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF3.setOpaque(false);
        lblF3.setBackground(SystemColor.window);
        lblF3.setForeground(new Color(32, 105, 29));
        lblEnter.setText("[ ENTER ] Seleccionar");
        lblEnter.setBounds(new Rectangle(15, 515, 130, 20));
        lblEnter.setFont(new Font("SansSerif", 1, 11));
        lblEnter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEnter.setForeground(new Color(32, 105, 29));
        lblLaboratorio.setText("Nombre del Laboratorio");
        lblLaboratorio.setBounds(new Rectangle(405, 0, 330, 30));
        lblLaboratorio.setFont(new Font("SansSerif", 1, 11));
        lblLaboratorio.setForeground(SystemColor.window);
        lblLaboratorio.setHorizontalAlignment(SwingConstants.CENTER);
        pnlBusqueda.add(txtFindText, null);
        pnlProductos.add(lblLaboratorio, null);
        pnlProductos.add(lblEstTabla, null);
        pnlProductos.add(lblRProductos, null);
        pnlTProductos.add(lblTProductos, null);
        pnlTProductos.add(lblTotalProductos, null);
        jContentPane.add(lblEnter, null);
        jContentPane.add(lblF3, null);
        jContentPane.add(lblF12, null);
        jContentPane.add(pnlTProductos, null);
        jContentPane.add(pnlProductos, null);
        jContentPane.add(lblF5, null);
        jContentPane.add(pnlBusqueda, null);
        jScrollPane1.getViewport().add(tblProductoLab, null);
        jContentPane.add(jScrollPane1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    private void initialize() {
        initTableLaboratoriosToma();
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_INVENTARIADOR)) {
            lblF5.setEnabled(false);
            lblF12.setEnabled(false);
            lblF3.setEnabled(false);
        }
    }

    private void initTableLaboratoriosToma() {
        tableModelProductoLaboratorio =
                new FarmaTableModel(ConstantsTomaInv.columnsListaProductos, ConstantsTomaInv.defaultValuesListaProductos,
                                    0);
        FarmaUtility.initSimpleList(tblProductoLab, tableModelProductoLaboratorio,
                                    ConstantsTomaInv.columnsListaProductos);
        cargaListaProductos();
    }

    private void cargaListaProductos() {
        try {
            lista.clear();
            tableModelProductoLaboratorio.clearTable();
            DBTomaInv.getListaProdsXToma(lista);
            tableModelProductoLaboratorio.data = lista;
            if (tblProductoLab.getRowCount() > 0) {
                FarmaUtility.ordenar(tblProductoLab, tableModelProductoLaboratorio, 1,
                                     FarmaConstants.ORDEN_ASCENDENTE);
                lblEstTabla.setForeground(Color.green);
                lblEstTabla.setText("Lista cargada");
                log.debug("se cargo la lista de de prods");
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al obtener la lista de productos : \n" +
                    sql.getMessage(), tblProductoLab);
        }
    }

    private void cantidadProductos() {
        int cantidad = tblProductoLab.getRowCount();
        if (cantidad <= 0) {
            lblTotalProductos.setText("0");
        } else if (cantidad > 0) {
            lblTotalProductos.setText(String.valueOf(cantidad));
        }

    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.setearPrimerRegistro(tblProductoLab, txtFindText, 1);
        FarmaUtility.moveFocus(txtFindText);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void txtFindText_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblProductoLab, txtFindText, 1);
    }

    private void txtFindText_keyPressed(KeyEvent e) {
        if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE ||
              e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT ||
              e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_HOME)) {
            e.consume();
        }
        FarmaGridUtils.aceptarTeclaPresionada(e, tblProductoLab, txtFindText, 1);
        String coProducto = "";
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();


            if (txtFindText.getText().length() > 6 && isNumeric(txtFindText.getText())) {
                try {
                    coProducto = DBTomaInv.getCodigoProductoBarra(txtFindText.getText());
                    if (!coProducto.equalsIgnoreCase("")) {
                        if (!(FarmaUtility.findTextInJTable(tblProductoLab, new JTextField(coProducto).getText().trim(), 
                                                            0, 1))) {
                            FarmaUtility.showMessage(this, "Producto No Encontrado según Criterio de Búsqueda !!!",
                                                     txtFindText);
                            return;
                        }                    
                    }else{
                        FarmaUtility.showMessage(this, "CodBarra No Encontrado según Criterio de Búsqueda !!!",
                                                 txtFindText);
                        return;
                    }
                } catch (SQLException e1) {
                    FarmaUtility.showMessage(this, "Producto No Encontrado según Criterio de Búsqueda !!!",
                                             txtFindText);
                    e1.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                    return;
                }
            }
            else if (tblProductoLab.getSelectedRow() >= 0) {
                if (!(FarmaUtility.findTextInJTable(tblProductoLab, txtFindText.getText().trim(), 0, 1))) {
                    FarmaUtility.showMessage(this, "Laboratorio No Encontrado según Criterio de Búsqueda !!!",
                                             txtFindText);
                    return;
                }
            }
            if (tieneRegistroSeleccionado(tblProductoLab) && !isHistorico) {
                cargarRegistroSeleccionado();
                DlgIngresoCantidadToma dlgIngresoCantidadToma = new DlgIngresoCantidadToma(this.parentFrame, "", true);
                dlgIngresoCantidadToma.setVisible(true);
                if (FarmaVariables.vAceptar) {
                    lblEstTabla.setForeground(Color.RED);
                    lblEstTabla.setText("Cargando");
                    cargaListaProductos();
                    FarmaVariables.vAceptar = false;
                    FarmaGridUtils.showCell(tblProductoLab, VariablesTomaInv.vRegActual, 0);
                }
                txtFindText.selectAll();
            }

        } else if (e.getKeyCode() == KeyEvent.VK_F5 && lblF5.isEnabled()) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                DlgListaDiferenciasTomaToTal dlgListaDiferenciasProductos =
                    new DlgListaDiferenciasTomaToTal(parentFrame, "", true, this.isHistorico);
                dlgListaDiferenciasProductos.setVisible(true);
                cargaListaProductos();
            } else {
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción",
                                         txtFindText);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            VariablesTomaInv.vLaboratorioXToma = false;
            this.setVisible(false);
            this.dispose();
        } else if (e.getKeyCode() == KeyEvent.VK_F12 && lblF12.isEnabled()) {
            imprimeListaDiferencia();
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                e.consume();
                verDetalle();
            }
        }

    }

    private boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            BigDecimal d = new BigDecimal(cadena.trim());
            resultado = true;
        } catch (Exception e) {
            resultado = false;
        }
        return resultado;
    }

    void verDetalle() {
        rowGeneral = tblProductoLab.getSelectedRow();
        String coProducto = (String)tblProductoLab.getValueAt(rowGeneral, 0);
        ArrayList lista = new ArrayList();
        try {
            DBTomaInv.getListaDetallePdaDigitacion(lista, coProducto);

            String CaEntero = (String)((ArrayList)lista.get(0)).get(0);
            String CaEnteroPda = (String)((ArrayList)lista.get(0)).get(1);
            String CaFraccion = (String)((ArrayList)lista.get(0)).get(2);
            String CaFraccionPda = (String)((ArrayList)lista.get(0)).get(3);

            DlgDetalle detalle = new DlgDetalle(parentFrame, "Detalle", true);
            detalle.getLblDigitacion().setText(CaEntero);
            detalle.getLblDigitacionFrac().setText(CaFraccion);
            detalle.getLblPda().setText(CaEnteroPda);
            detalle.getLblPdaFrac().setText(CaFraccionPda);
            detalle.setVisible(true);
            FarmaGridUtils.showCell(tblProductoLab, rowGeneral, 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private void cargarRegistroSeleccionado() {
        VariablesTomaInv.vCodProd = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 0).toString().trim();
        VariablesTomaInv.vDesProd = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 1).toString().trim();
        VariablesTomaInv.vUnidPresentacion =
                tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 2).toString().trim();
        VariablesTomaInv.vCantEntera = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 4).toString().trim();
        VariablesTomaInv.vCantEntera = VariablesTomaInv.vCantEntera.replace(",", "");
        VariablesTomaInv.vCantFraccion =
                tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 5).toString().trim();
        VariablesTomaInv.vCantFraccion = VariablesTomaInv.vCantFraccion.replace(",", "");
        VariablesTomaInv.vFraccion = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 6).toString().trim();
        VariablesTomaInv.vRegActual = tblProductoLab.getSelectedRow();
        VariablesTomaInv.Vanaquel = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 9).toString().trim();
        VariablesTomaInv.Vorigen = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 10).toString().trim();
        VariablesTomaInv.vCodLab = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 11).toString().trim();
        // dflores
        VariablesTomaInv.vNomLab = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 3).toString().trim();
        VariablesTomaInv.vUnidVta = tblProductoLab.getValueAt(tblProductoLab.getSelectedRow(), 7).toString().trim();
        //  log.debug("este es el valor del origen "+VariablesTomaInv.Vorigen);
    }


    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;
        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    void imprimeListaDiferencia() {
        DlgFiltroEstado dlgOrdenar = new DlgFiltroEstado(parentFrame, "Filtrar", true);

        String[] IND_DESCRIP_CAMPOS = { "Todos", "PDA", "Digitación", "Ambos" };
        String[] IND_CAMPOS = { "T", "P", "D", "A" };

        VariablesTomaInv.vNombreInHashtable = "FILTRO_ORIGEN";

        FarmaLoadCVL.loadCVLfromArrays(dlgOrdenar.cmbCampo, VariablesTomaInv.vNombreInHashtable, IND_CAMPOS,
                                       IND_DESCRIP_CAMPOS, true);

        dlgOrdenar.setVisible(true);

        if (!VariablesTomaInv.vAceptaOrdenar) {
            return;
        }

        origen = FarmaLoadCVL.getCVLCode(VariablesTomaInv.vNombreInHashtable, dlgOrdenar.cmbCampo.getSelectedIndex());
        //   obtiene lista de diferencias en blanco
        //obtieneListaTomaInventario();


        if (JOptionPane.showConfirmDialog(this, "Desea imprimir el Reporte de Toma de Inventarioi por Laboratorio?",
                                          "Confirmar Impresión", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
            return;

        FarmaPrintService vPrint = new FarmaPrintService(65, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            JOptionPane.showMessageDialog(this, "No se pudo determinar la existencia de la Impresora. Verifique !!!",
                                          "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.activateCondensed();
            vPrint.setStartHeader();
            vPrint.printBold(FarmaPRNUtility.alinearIzquierda("Local: " + FarmaVariables.vDescCortaLocal, 20) +
                             FarmaPRNUtility.llenarBlancos(87) + "Fecha: " + fechaActual, true);
            vPrint.printBold(FarmaPRNUtility.alinearIzquierda("Dpto. de Informatica", 40), true);
            vPrint.printBold(FarmaPRNUtility.llenarBlancos(43) + "Reporte Para Toma de Inventarios x Laboratorio",
                             true);
            vPrint.printBold(FarmaPRNUtility.alinearIzquierda("Laboratorio: " + "TODOS", 65), true);
            vPrint.printBold("--------------------------------------------------------------------------------------------------------------------------------------",
                             true);
            vPrint.printBold("Codigo Item                                         Unid.de Venta   Punto Vta.  Almacen    | Total Inv. Observaciones          Anaquel",
                             true);
            vPrint.printBold("                                                                    Ent./Frac.  Ent./Frac. | Ent./Frac.                        ",
                             true);
            vPrint.printBold("------ -------------------------------------------- --------------  ----------  ---------- | ---------- ------------------------------",
                             true);
            vPrint.deactivateCondensed();
            vPrint.setEndHeader();
            int row = 0;
            for (int i = 0; i < tableModelProductoLaboratorio.getRowCount(); i++) {
                String strIngresoCantidadesF =
                    ((String)tableModelProductoLaboratorio.getValueAt(i, 3)).trim() + " / " +
                    ((String)tableModelProductoLaboratorio.getValueAt(i, 4)).trim();
                vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda(((String)tableModelProductoLaboratorio.getValueAt(i,
                                                                                                                         0)).trim(),
                                                                       6) + " " +
                                      FarmaPRNUtility.alinearIzquierda(((String)tableModelProductoLaboratorio.getValueAt(i,
                                                                                                                         1)).trim(),
                                                                       44) + " " +
                                      FarmaPRNUtility.alinearIzquierda(((String)tableModelProductoLaboratorio.getValueAt(i,
                                                                                                                         6)).trim(),
                                                                       15) + "  " + "___ / ___" + "  " + "___ / ___" +
                                      "  | " + FarmaPRNUtility.alinearIzquierda(strIngresoCantidadesF, 9) +
                                      FarmaPRNUtility.alinearIzquierda("  " + "______________", 16) +
                                      FarmaPRNUtility.alinearDerecha(((String)tableModelProductoLaboratorio.getValueAt(i,
                                                                                                                       8)).trim(),
                                                                     16), true);

                row++;
            }
            vPrint.activateCondensed();
            vPrint.printBold("-------------------------------------------------------------------------------------------------------------------------------",
                             true);
            vPrint.printBold("Total de Registros: " + FarmaUtility.formatNumber(row, "#,##0"), true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
        } catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error del sistema – " + sqlerr.getErrorCode() + "\n" +
                    sqlerr.getMessage(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void tblProductoLab_mouseReleased(MouseEvent e) {

        txtFindText.requestFocus();

    }
}
