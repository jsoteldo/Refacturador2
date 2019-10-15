package mifarma.ptoventa.centromedico;


import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableRowSorter;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2015 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DlgFiltroProductosNuevo.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * KMONCADA             09.01.2015   Creación<br>
 * <br>
 * @author Kenny Moncada<br>
 * @version 1.0<br>
 *
 */

public class DlgListadoProductoCM extends JDialog {
    
    private static final Logger log = LoggerFactory.getLogger(DlgListadoProductoCM.class);
    private static final long serialVersionUID = 1L;
    private Frame myParentFrame;
    private FarmaTableModel tableModelListaFiltro;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JPanel pnlRelacionFiltros = new JPanel();
    private JLabel lblRelacionFiltros = new JLabel();
    private JPanel pnlIngresarProductos = new JPanel();
    private JTextFieldSanSerif txtProducto = new JTextFieldSanSerif();
    private JButton btnProducto = new JButton();
    private JTable tblFiltro = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction();

    private JLabelFunction lblF11 = new JLabelFunction();

    private String tipoProducto;
    private transient ArrayList lstLista;
    
   
    private transient TableRowSorter<FarmaTableModel> sorter;
    
    //private ArrayList lstNombresFiltro = new ArrayList();
    //private ArrayList lstCodigoFiltro = new ArrayList();
    //private ArrayList lstOrdenFiltro = new ArrayList();
    //private ArrayList lstIndFiltroTipo = new ArrayList();
    
    private boolean agregarCheck;
    private transient ArrayList lstResultado;
  
    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgListadoProductoCM() {
        this(null, "", false, null);
    }

    public DlgListadoProductoCM(Frame parent, String title, boolean modal, ArrayList lstLista) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.lstLista = (ArrayList)lstLista.clone();
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
        this.setSize(new Dimension(643, 346));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Filtro de Productos");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(623, 335));
        jContentPane.setForeground(Color.white);
        lblEnter.setText("<html><center>[ ENTER ]<br>Seleccionar");
        lblEnter.setBounds(new Rectangle(340, 280, 120, 30));


        lblF11.setText("<html><center>[ F11 ]<br>Aceptar");
        lblF11.setBounds(new Rectangle(215, 280, 120, 30));


        jScrollPane1.setBounds(new Rectangle(15, 85, 570, 185));
        jScrollPane1.setBackground(new Color(255, 130, 14));
        pnlRelacionFiltros.setBounds(new Rectangle(15, 65, 570, 20));
        pnlRelacionFiltros.setBackground(new Color(255, 130, 14));
        pnlRelacionFiltros.setLayout(null);
        lblRelacionFiltros.setText("Relacion de Filtros");
        lblRelacionFiltros.setBounds(new Rectangle(5, 0, 145, 20));
        lblRelacionFiltros.setFont(new Font("SansSerif", 1, 11));
        lblRelacionFiltros.setForeground(Color.white);
        pnlIngresarProductos.setBounds(new Rectangle(20, 10, 565, 50));
        pnlIngresarProductos.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 14), 1));
        pnlIngresarProductos.setBackground(Color.white);
        pnlIngresarProductos.setLayout(null);
        pnlIngresarProductos.setForeground(Color.orange);
        txtProducto.setBounds(new Rectangle(95, 15, 330, 20));
        //txtProducto.setForeground(new Color(32, 105, 29));
        txtProducto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtProducto_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtProducto_keyReleased(e);
            }
        });
        btnProducto.setText("Filtro :");
        btnProducto.setBounds(new Rectangle(10, 15, 60, 25));
        btnProducto.setMnemonic('f');
        btnProducto.setFont(new Font("SansSerif", 1, 11));
        btnProducto.setDefaultCapable(false);
        btnProducto.setRequestFocusEnabled(false);
        btnProducto.setBackground(new Color(50, 162, 65));
        btnProducto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProducto.setFocusPainted(false);
        btnProducto.setHorizontalAlignment(SwingConstants.LEFT);
        btnProducto.setContentAreaFilled(false);
        btnProducto.setBorderPainted(false);
        btnProducto.setForeground(new Color(255, 140, 14));
        btnProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnProducto_actionPerformed(e);
            }
        });
        tblFiltro.setFont(new Font("SansSerif", 0, 12));
        tblFiltro.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblFiltro_keyPressed(e);
            }
        });
        lblEsc.setText("<html><center>[ ESC ]<br>Cerrar");
        lblEsc.setBounds(new Rectangle(465, 280, 120, 30));
        jScrollPane1.getViewport();
        pnlIngresarProductos.add(txtProducto, null);
        pnlIngresarProductos.add(btnProducto, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblEnter, null);
        jContentPane.add(lblF11, null);
        jScrollPane1.getViewport().add(tblFiltro, null);
        jContentPane.add(jScrollPane1, null);
        pnlRelacionFiltros.add(lblRelacionFiltros, null);
        jContentPane.add(pnlRelacionFiltros, null);
        jContentPane.add(pnlIngresarProductos, null);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        try{
            initTable();
            cargarTabla();
            FarmaVariables.vAceptar = false;
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(this, "Error en Filtro: "+ex.getMessage(), null);
        }
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTable() {
        FarmaColumnData columnas[] = {//new FarmaColumnData("SEL", 0, JLabel.CENTER),             
                                      new FarmaColumnData("Codigo", 60, JLabel.CENTER),         //0
                                      new FarmaColumnData("Descripcion", 200, JLabel.LEFT),     //1
                                      new FarmaColumnData("Unidad", 95, JLabel.LEFT),           //2
                                      new FarmaColumnData("Laboratorio", 150, JLabel.LEFT),     //3
                                      new FarmaColumnData("Stock", 45, JLabel.RIGHT),           //4
                                      new FarmaColumnData("VAL_FRAC", 0, JLabel.LEFT),          //5
                                      new FarmaColumnData("PRINCIPIO ACTIVO", 0, JLabel.LEFT)   //6
                                      };
        
        
        tableModelListaFiltro = new FarmaTableModel(columnas, UtilityPtoVenta.obtenerDefaultValuesTabla(columnas.length), 0);
        FarmaUtility.initSimpleList(tblFiltro, tableModelListaFiltro, columnas);
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void tblFiltro_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtProducto);
    }

    private void txtProducto_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblFiltro.getSelectedRow() >= 0) {
                /*boolean check = ((Boolean)tblFiltro.getValueAt(tblFiltro.getSelectedRow(), 0));
                tblFiltro.setValueAt(!check, tblFiltro.getSelectedRow(), 0);
                tblFiltro.repaint();*/
                if(!agregarCheck){
                    cerrarVentana(true);
                }
            }
        }
        chkKeyPressed(e);
    }

    private void txtProducto_keyReleased(KeyEvent e) {
        //FarmaGridUtils.buscarDescripcion(e, tblFiltro, null, 1);
        FarmaGridUtils.aceptarTeclaPresionada(e, tblFiltro, null, 2);
        txtProducto.setText(txtProducto.getText().toUpperCase());
        String expr = txtProducto.getText();//.toUpperCase();
        if (expr.length() == 0) {
            sorter.setRowFilter(null);
        } else {
           sorter.setRowFilter(RowFilter.regexFilter(""+expr, 1, 2));
        }
        sorter.setSortKeys(null); 
    }

    private void btnProducto_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProducto);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            cerrarVentana(false);
        }
        if(UtilityPtoVenta.verificaVK_F11(e)){
            cerrarVentana(true); 
        }
    }
    
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        if(pAceptar)
            guardaValoresFiltro();
        this.setVisible(false);
        this.dispose();
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void cargarTabla() {
        if(lstLista.isEmpty()){
            FarmaUtility.showMessage(this, "No se pudo cargar el listado de productos.\n"+
                                           "Reintente, en caso persista llame a mesa de ayuda", null);
            cerrarVentana(false);
        }else{
            try {
                tableModelListaFiltro.clearTable();
                tblFiltro.setRowSorter(null);
                for(int i=0; i<lstLista.size();i++){
                    ArrayList fila = (ArrayList)lstLista.get(i);
                    //fila.add(0, new Boolean(false));
                    tableModelListaFiltro.data.add(fila);
                }
                sorter = new TableRowSorter<FarmaTableModel>(tableModelListaFiltro);
                tblFiltro.setRowSorter(sorter);
                tableModelListaFiltro.fireTableDataChanged();
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    private void guardaValoresFiltro() {
        lstResultado = new ArrayList();
        ArrayList fila = new ArrayList();
        for(int k=0;k<tblFiltro.getColumnCount();k++)
            fila.add(((String)tblFiltro.getValueAt(tblFiltro.getSelectedRow(),k)));
        lstResultado.add(fila);
        /*
        for(int i=0; i<tblFiltro.getRowCount();i++){
            boolean check = ((Boolean)tblFiltro.getValueAt(i, 0));
            if(check){
                ArrayList fila = new ArrayList();
                for(int k=1;k<tblFiltro.getColumnCount();k++)
                    fila.add(((String)tblFiltro.getValueAt(i,k)));
                lstResultado.add(fila);
            }
        }*/
    }

    public void setLstResultado(ArrayList lstResultado) {
        this.lstResultado = lstResultado;
    }

    public ArrayList getLstResultado() {
        return lstResultado;
    }
}
