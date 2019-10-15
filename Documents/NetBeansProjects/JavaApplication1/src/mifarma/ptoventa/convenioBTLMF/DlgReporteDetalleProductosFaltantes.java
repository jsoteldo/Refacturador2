package mifarma.ptoventa.convenioBTLMF;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.filechooser.FileNameExtensionFilter;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConv_Responsabilidad;
import mifarma.ptoventa.reportes.reference.ConstantsReporte;
import mifarma.ptoventa.reportes.reference.DBReportes;
import mifarma.ptoventa.reportes.reference.VariablesReporte;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.util.CellRangeAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * DlgReporteProductosFaltantes.java
 * @author Jhony Monzalve V.
 * @version 1.0
 * @fechaCreacion 24042019
 * 
 */
public class DlgReporteDetalleProductosFaltantes extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgReporteDetalleProductosFaltantes.class);

    private Frame myParentFrame;
    private FarmaTableModel tableModelDetalleEmpleados;
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private JPanelTitle pnlTitulo = new JPanelTitle();
    private JButtonLabel btnListado = new JButtonLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable tblDetalleEmpleados = new JTable();
    private JLabelFunction lblF12 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelTitle pnlResultados = new JPanelTitle();
    private String numPedVta = "";

    public DlgReporteDetalleProductosFaltantes() {
        this(null, "", false, "");
    }

    public DlgReporteDetalleProductosFaltantes(Frame parent, String title, boolean modal, String numPedVta) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.numPedVta = numPedVta;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(667, 396));
        this.getContentPane().setLayout(gridLayout1);
        this.setTitle("Resumen Detalle Ventas");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlTitulo.setBounds(new Rectangle(10, 10, 640, 20));
        btnListado.setText("Relaci\u00f3n de Personal Regularizado");
        btnListado.setBounds(new Rectangle(10, 0, 200, 20));
        btnListado.setMnemonic('R');
        btnListado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnListado_actionPerformed(e);
                }
        });
        jScrollPane1.setBounds(new Rectangle(10, 35, 640, 270));
        tblDetalleEmpleados.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblDetalleEmpleados_keyPressed(e);
            }
        });
        lblF12.setBounds(new Rectangle(405, 340, 117, 19));
        lblF12.setText("[ F12 ] Exportar");
        lblEsc.setBounds(new Rectangle(530, 340, 117, 19));
        lblEsc.setText("[ ESC ] Cerrar");
        pnlResultados.setBounds(new Rectangle(10, 305, 640, 20));
        pnlTitulo.add(btnListado, null);
        jScrollPane1.getViewport().add(tblDetalleEmpleados, null);
        jPanelWhite1.add(pnlResultados, null);
        jPanelWhite1.add(lblEsc, null);
        jPanelWhite1.add(lblF12, null);
        jPanelWhite1.add(jScrollPane1, null);
        jPanelWhite1.add(pnlTitulo, null);
        this.getContentPane().add(jPanelWhite1, null);
    }


    private void initialize() {
        initTableListaDetalleProductosVendidos();
    };

    private void initTableListaDetalleProductosVendidos() {
        tableModelDetalleEmpleados =
                new FarmaTableModel(ConstantsConv_Responsabilidad.columnsDetalleEmpleados, ConstantsConv_Responsabilidad.defaultValuesDetalleEmpleados,0);
                FarmaUtility.initSimpleList(tblDetalleEmpleados, tableModelDetalleEmpleados,ConstantsConv_Responsabilidad.columnsDetalleEmpleados);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        cargaResumenEmpleados();
        FarmaUtility.moveFocus(tblDetalleEmpleados);
    }

    private void cargaResumenEmpleados() {
        try {
            DBConv_Responsabilidad.obtenerDetalleEmpleadosPorVenta(tableModelDetalleEmpleados, numPedVta);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al listar el resumen de productos vendidos : \n" + sql.getMessage(), null);
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void tblDetalleEmpleados_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        } else if (e.getKeyCode() == KeyEvent.VK_F12) {
                if (tblDetalleEmpleados.getRowCount() <= 0)
                    FarmaUtility.showMessage(this, "Debe realizar una busqueda antes de imprimir", tblDetalleEmpleados);
                else{
                    boolean flag = UtilityConv_Responsabilidad.exportarDetalleProductos(tableModelDetalleEmpleados);
                    if(flag){
                        FarmaUtility.showMessage(this, "El archivo fue exportado exitosamente !!!", tblDetalleEmpleados);
                    }
                }
        }
    }
    
    private void btnListado_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblDetalleEmpleados);
    }
}
