package mifarma.ptoventa.convenioBTLMF;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.filechooser.FileNameExtensionFilter;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaPrintService;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConv_Responsabilidad;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reportes.DlgDetalleResumenVentas;
import mifarma.ptoventa.reportes.DlgFiltroDetalleVentas;
import mifarma.ptoventa.reportes.reference.ConstantsReporte;
import mifarma.ptoventa.reportes.reference.DBReportes;
import mifarma.ptoventa.reportes.reference.VariablesReporte;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.ss.util.RegionUtil;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DlgReporteProductosFaltantes.java
 * @author Jhony Monzalve V.
 * @version 1.0
 * @fechaCreacion 24042019
 * 
 */
public class DlgReporteProductosFaltantes extends JDialog {

    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(DlgReporteProductosFaltantes.class);

    private FarmaTableModel tableModelListadoVentas;
    private FarmaTableModel tableModelDetalleVentas;
    private Frame myParentFrame;
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private JPanelHeader pnlCriterioBusqueda = new JPanelHeader();
    private JButton btnBuscar = new JButton();
    private JTextFieldSanSerif txtFechaFin = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtFechaIni = new JTextFieldSanSerif();
    private JButtonLabel btnPeriodo = new JButtonLabel();
    private JPanelTitle pnlTitulo = new JPanelTitle();
    private JButtonLabel btnListadoVentas = new JButtonLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTable tblListadoVentas = new JTable();
    private JTable tblDetalleVentas = new JTable();
    private JPanelTitle pnlResultados = new JPanelTitle();
    private JButtonLabel btnDetalleVenta = new JButtonLabel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF12 = new JLabelFunction();
    private JLabelFunction lblF9 = new JLabelFunction();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgReporteProductosFaltantes() {
        this(null, "", false);
    }

    public DlgReporteProductosFaltantes(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(739, 598));
        this.getContentPane().setLayout(gridLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Reporte Detalle de Ventas");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlCriterioBusqueda.setBounds(new Rectangle(10, 10, 715, 30));
        pnlCriterioBusqueda.setFocusable(false);
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(465, 5, 95, 20));
        btnBuscar.setMnemonic('b');
        btnBuscar.setFont(new Font("SansSerif", 1, 11));
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        txtFechaFin.setBounds(new Rectangle(335, 5, 101, 19));
        txtFechaFin.setLengthText(10);
        txtFechaFin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaFin_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaFin_keyReleased(e);
            }
        });
        txtFechaIni.setBounds(new Rectangle(210, 5, 101, 19));
        txtFechaIni.setLengthText(10);
        txtFechaIni.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaIni_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaIni_keyReleased(e);
            }
        });
        btnPeriodo.setText("Periodo :");
        btnPeriodo.setBounds(new Rectangle(140, 5, 60, 20));
        btnPeriodo.setMnemonic('p');
        btnPeriodo.setFocusable(false);
        btnPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPeriodo_actionPerformed(e);
            }
        });
        pnlTitulo.setBounds(new Rectangle(10, 50, 715, 20));
        pnlTitulo.setFocusable(false);
        btnListadoVentas.setText("Relacion de Ventas");
        btnListadoVentas.setBounds(new Rectangle(10, 0, 200, 20));
        btnListadoVentas.setMnemonic('R');
        btnListadoVentas.setFocusable(false);
        btnListadoVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnListadoVentas_actionPerformed(e);
                }
        });
        jScrollPane1.setBounds(new Rectangle(10, 70, 715, 215));
        jScrollPane1.setFocusable(false);
        jScrollPane2.setBounds(new Rectangle(10, 305, 715, 215));
        jScrollPane2.setFocusable(false);
        
        tblListadoVentas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                //tblListadoVentas_keyPressed(e);
                    tblListadoVentas_keyPressed(e);
                }
            public void keyReleased(KeyEvent e) {
                tblListadoVentas_keyReleased(e);
            }
        });
        tblListadoVentas.setBounds(new Rectangle(-5, 0, 0, 0));
        tblDetalleVentas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    tblDetalleVentas_keyPressed(e);
                }
        });
        tblDetalleVentas.setBounds(new Rectangle(-5, 0, 0, 0));
        
        pnlResultados.setBounds(new Rectangle(10, 285, 715, 20));
        pnlResultados.setFocusable(false);
        btnDetalleVenta.setText("Detalle de Productos :");
        btnDetalleVenta.setBounds(new Rectangle(15, 0, 225, 20));
        btnDetalleVenta.setFont(new Font("SansSerif", 1, 11));
        btnDetalleVenta.setForeground(Color.white);
        btnDetalleVenta.setFocusable(false);
        btnDetalleVenta.setMnemonic('D');
        btnDetalleVenta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnDetalleVenta_actionPerformed(e);
                }
            });
        lblEsc.setBounds(new Rectangle(615, 530, 110, 25));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setFocusable(false);
        lblF12.setBounds(new Rectangle(470, 530, 120, 25));
        lblF12.setText("[ F12 ] Exportar");
        lblF12.setFocusable(false);
        lblF9.setBounds(new Rectangle(330, 530, 110, 25));
        lblF9.setText("[ F9 ] Ordenar");
        lblF9.setFocusable(false);
        lblF1.setBounds(new Rectangle(5, 530, 120, 25));
        lblF1.setText("[ F1 ] Ver Detalle");
        lblF1.setFocusable(false);
        lblF2.setBounds(new Rectangle(165, 530, 125, 25));
        lblF2.setText("[ F2 ] Ver Resumen");
        lblF2.setFocusable(false);
        jScrollPane1.getViewport();
        jScrollPane2.getViewport();
        pnlResultados.add(btnDetalleVenta, null);
        pnlCriterioBusqueda.add(btnBuscar, null);
        pnlCriterioBusqueda.add(txtFechaFin, null);
        pnlCriterioBusqueda.add(txtFechaIni, null);
        pnlCriterioBusqueda.add(btnPeriodo, null);
        pnlTitulo.add(btnListadoVentas, null);
        jPanelWhite1.add(lblF1, null);
        jPanelWhite1.add(lblF9, null);
        jPanelWhite1.add(lblF12, null);
        jPanelWhite1.add(lblEsc, null);
        jPanelWhite1.add(pnlResultados, null);
        jPanelWhite1.add(tblListadoVentas, null);
        jPanelWhite1.add(tblDetalleVentas, null);
        jScrollPane1.getViewport().add(tblListadoVentas, null);
        jScrollPane2.getViewport().add(tblDetalleVentas, null);
        jPanelWhite1.add(jScrollPane1, null);
        jPanelWhite1.add(jScrollPane2, null);
        jPanelWhite1.add(pnlTitulo, null);
        jPanelWhite1.add(pnlCriterioBusqueda, null);
        jPanelWhite1.add(lblF2, null);
        this.getContentPane().add(jPanelWhite1, null);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        initTableListadoVentas();
        initTableDetalleVentas();
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTableListadoVentas() {
        tableModelListadoVentas =
                new FarmaTableModel(ConstantsConv_Responsabilidad.columnsListaVentas, ConstantsConv_Responsabilidad.defaultValuesListadoVentas, 0);
                FarmaUtility.initSimpleList(tblListadoVentas, tableModelListadoVentas, ConstantsConv_Responsabilidad.columnsListaVentas);
    }
    
    private void initTableDetalleVentas() {
        tableModelDetalleVentas =
                new FarmaTableModel(ConstantsConv_Responsabilidad.columnsDetalleVentas, ConstantsConv_Responsabilidad.defaultValuesDetalleVentas, 0);
                FarmaUtility.initSimpleList(tblDetalleVentas, tableModelDetalleVentas, ConstantsConv_Responsabilidad.columnsDetalleVentas);
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void txtFechaFin_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaFin, e);
    }


    private void tblListadoVentas_keyReleased(KeyEvent e) {
        obtenerDetalleVentas();
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtFechaIni);
        lblF9.setVisible(false);
        lblF2.setVisible(false);
    }

    private void txtFechaIni_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            FarmaUtility.moveFocus(txtFechaFin);
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            cerrarVentana(false);
        chkKeyPressed(e);
    }

    private void txtFechaFin_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btnBuscar.doClick();
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            cerrarVentana(false);

        chkKeyPressed(e);
    }

    private void tblDetalleVentas_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void txtFechaIni_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaIni, e);
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        busqueda();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void btnPeriodo_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFechaIni);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(true);
        } else if (UtilityPtoVenta.verificaVK_F1(e)) {
            if (tblDetalleVentas.getRowCount() <= 0) {
                FarmaUtility.showMessage(this, "No existen registros de ventas !!!", txtFechaIni);
                return;
            } else
                resumenEmpleados();
        } else if (e.getKeyCode() == KeyEvent.VK_F8) {
            if (tblDetalleVentas.getRowCount() <= 0)
                FarmaUtility.showMessage(this, "No existe informacion para guardar el archivo", txtFechaIni);
            else
                ;
        } else if (UtilityPtoVenta.verificaVK_F12(e)) {
            if (tblDetalleVentas.getRowCount() <= 0)
                FarmaUtility.showMessage(this, "Debe realizar una busqueda antes de imprmir", txtFechaIni);
            else{
                if(tblListadoVentas.getSelectedRowCount() > 0){
                    int vFila = tblListadoVentas.getSelectedRow();
                    String descEmpresa = FarmaUtility.getValueFieldJTable(tblListadoVentas, vFila, 0);
                    String descLocal = FarmaUtility.getValueFieldJTable(tblListadoVentas, vFila, 2);
                    String fechaEmision = FarmaUtility.getValueFieldJTable(tblListadoVentas, vFila, 4);
                    //exportarProductos(descLocal);
                    boolean flag = UtilityConv_Responsabilidad.exportarProductos(tableModelDetalleVentas, descEmpresa, descLocal, fechaEmision);
                    if(flag){
                        FarmaUtility.showMessage(this, "El archivo fue exportado exitosamente !!!", tblListadoVentas);
                    }
                }else{
                    FarmaUtility.showMessage(this, "No se ha seleccionado ninguna venta !!!", tblListadoVentas);
                }
            }
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */


    private void resumenEmpleados() {
        if(tblListadoVentas.getSelectedRowCount() > 0){
            int vFila = tblListadoVentas.getSelectedRow();
            String numPedVenta = FarmaUtility.getValueFieldJTable(tblListadoVentas, vFila, 3);
            DlgReporteDetalleProductosFaltantes dlgReporteDetalleProductosFaltantes = new DlgReporteDetalleProductosFaltantes(myParentFrame, "", true, numPedVenta);
            dlgReporteDetalleProductosFaltantes.setVisible(true);
        }else{
            FarmaUtility.showMessage(this, "No se ha seleccionado ninguna venta !!!", tblListadoVentas);
        }
    }
    
    private void obtenerDetalleVentas(){
        if(tblListadoVentas.getRowCount() > 0){
            if(tblListadoVentas.getSelectedRowCount() > 0){
                int vFila = tblListadoVentas.getSelectedRow();
                String numPedVenta = FarmaUtility.getValueFieldJTable(tblListadoVentas, vFila, 3);
                cargaDetalleVentas(numPedVenta);
            }
        }
    }
    
    private void busqueda() {
        if (validarCampos()) {
            txtFechaIni.setText(txtFechaIni.getText().trim().toUpperCase());
            txtFechaFin.setText(txtFechaFin.getText().trim().toUpperCase());
            String FechaInicio = txtFechaIni.getText().trim();
            String FechaFin = txtFechaFin.getText().trim();
            if (FechaInicio.length() > 0 || FechaFin.length() > 0) {
                char primerkeyCharFI = FechaInicio.charAt(0);
                char ultimokeyCharFI = FechaInicio.charAt(FechaInicio.length() - 1);
                char primerkeyCharFF = FechaFin.charAt(0);
                char ultimokeyCharFF = FechaFin.charAt(FechaFin.length() - 1);

                if (!Character.isLetter(primerkeyCharFI) && !Character.isLetter(ultimokeyCharFI) &&
                    !Character.isLetter(primerkeyCharFF) && !Character.isLetter(ultimokeyCharFF)) {
                    buscaListadoVentas(FechaInicio, FechaFin);
                } else
                    FarmaUtility.showMessage(this, "Ingrese un formato valido de fechas", txtFechaIni);
            } else
                FarmaUtility.showMessage(this, "Ingrese datos para la busqueda", txtFechaIni);
        }
    }

    private boolean validarCampos() {
        boolean retorno = true;
        if (!FarmaUtility.validarRangoFechas(this, txtFechaIni, txtFechaFin, false, true, true, true))
            retorno = false;
        return retorno;
    }

    private void buscaListadoVentas(String pFechaInicio, String pFechaFin) {
        ConstantsConv_Responsabilidad.vFechaInicio = pFechaInicio;
        ConstantsConv_Responsabilidad.vFechaFin = pFechaFin;
        cargaListadoVentas();
    }

    private void cargaListadoVentas() {
        try {
            tableModelDetalleVentas.clearTable();
            tableModelDetalleVentas.fireTableDataChanged();
            tableModelListadoVentas.clearTable();
            tableModelListadoVentas.fireTableDataChanged();
            DBConv_Responsabilidad.obtenerListadoVentas(tableModelListadoVentas, ConstantsConv_Responsabilidad.vFechaInicio, ConstantsConv_Responsabilidad.vFechaFin);
            tblListadoVentas.repaint();
            if (tblListadoVentas.getRowCount() == 0) {
                FarmaUtility.showMessage(this, "No se encontraron registros en el rango de fechas ingresado !!!", txtFechaIni);
            } else {
                //FarmaUtility.ordenar(tblListadoVentas, tableModelListadoVentas, 1, FarmaConstants.ORDEN_DESCENDENTE);
                FarmaUtility.moveFocusJTable(tblListadoVentas);
                FarmaGridUtils.showCell(tblListadoVentas, 0, 0);
                String numPedVenta = FarmaUtility.getValueFieldJTable(tblListadoVentas, 0, 3);
                cargaDetalleVentas(numPedVenta);
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al listar el detalle de Ventas : \n" +
                    sql.getMessage(), txtFechaIni);
            cerrarVentana(false);
        }
    }
    
    private void cargaDetalleVentas(String numPedVenta) {
        try {
            tableModelDetalleVentas.clearTable();
            tableModelDetalleVentas.fireTableDataChanged();
            DBConv_Responsabilidad.obtenerDetalleVentas(tableModelDetalleVentas, numPedVenta);
            tblDetalleVentas.repaint();
            if (tblListadoVentas.getRowCount() == 0) {
                FarmaUtility.showMessage(this, "No se encontraron productos para la venta seleccionada !!!", txtFechaIni);
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al listar el detalle de Ventas : \n" +
                    sql.getMessage(), txtFechaIni);
            cerrarVentana(false);
        }
    }

    private void btnDetalleVenta_actionPerformed(ActionEvent e) {
        if(tblDetalleVentas.getRowCount() > 0){
            FarmaUtility.moveFocus(tblDetalleVentas);
            FarmaGridUtils.showCell(tblDetalleVentas, 0, 0);
        }
    }

    private void btnListadoVentas_actionPerformed(ActionEvent e) {
        if(tblListadoVentas.getRowCount() > 0){
            FarmaUtility.moveFocus(tblListadoVentas);
            FarmaGridUtils.showCell(tblListadoVentas, 0, 0);
        }
    }

    private void tblListadoVentas_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
}
