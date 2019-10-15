package mifarma.ptoventa.convalidado;


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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convalidado.reference.DBConvalidado;
import mifarma.ptoventa.convalidado.reference.VariablesConvalidado;
import mifarma.ptoventa.inventario.DlgFiltroDescripcionMovKardex;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;

import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgReporteMerma extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgReporteMerma.class);

    Frame myParentFrame;

    FarmaTableModel tableModel;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanelWhite jPanelWhite1 = new JPanelWhite();

    private JPanelHeader pnlHeader1 = new JPanelHeader();

    private JLabelWhite lblProducto_T = new JLabelWhite();

    private JLabelWhite lblProducto = new JLabelWhite();

    private JLabelWhite lblUnidad_T = new JLabelWhite();

    private JLabelWhite lblStock_T = new JLabelWhite();

    private JLabelWhite lblUnidad = new JLabelWhite();

    private JLabelWhite lblStockEntero = new JLabelWhite();

    private JTextFieldSanSerif txtFechaInicio = new JTextFieldSanSerif();

    private JTextFieldSanSerif txtFechaFin = new JTextFieldSanSerif();

    private JButton btnBuscar = new JButton();

    private JButtonLabel btnRangoFechas = new JButtonLabel();

    private JPanelTitle pnllTitle1 = new JPanelTitle();

    private JButtonLabel btnRelacionMovimiento = new JButtonLabel();

    private JScrollPane scrListaProductos = new JScrollPane();

    private JTable tblListaMovs = new JTable();


    private JLabelFunction lblEsc = new JLabelFunction();

    private JLabelWhite lblLaboratorio_T = new JLabelWhite();

    private JLabelWhite lblLaboratorio = new JLabelWhite();
    private JLabelWhite lblUnidadFraccion = new JLabelWhite();
    private JLabelFunction lblF8 = new JLabelFunction();
    private JPanelTitle pnllTitle2 = new JPanelTitle();
    private JButtonLabel btnRelacionMovimiento1 = new JButtonLabel();
    private JPanelTitle pnllTitle3 = new JPanelTitle();
    private JLabelWhite lblCantMovimientoT = new JLabelWhite();
    private JLabelWhite lblCantMovieminto = new JLabelWhite();
    private JLabel lblExcluido = new JLabel();
    private JLabelFunction lblTodos = new JLabelFunction();
    private JLabelFunction lblReversa = new JLabelFunction();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgReporteMerma() {
        this(null, "", false);
    }

    public DlgReporteMerma(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            FarmaUtility.centrarVentana(this);
        } catch (Exception e) {
            log.error("", e);
        }

    }

    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(780, 395));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Detalle de Mermas");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlHeader1.setBounds(new Rectangle(10, 10, 760, 80));
        lblProducto_T.setText("Producto:");
        lblProducto_T.setBounds(new Rectangle(10, 10, 65, 20));
        lblProducto.setText("TREUPEL-NF NIÑOS ");
        lblProducto.setBounds(new Rectangle(70, 10, 205, 20));
        lblProducto.setFont(new Font("SansSerif", 0, 11));
        lblUnidad_T.setText("Unidad:");
        lblUnidad_T.setBounds(new Rectangle(285, 10, 45, 20));
        lblStock_T.setText("Stock:");
        lblStock_T.setBounds(new Rectangle(470, 35, 35, 20));
        lblUnidad.setText("CJA/5 SUPOS ");
        lblUnidad.setBounds(new Rectangle(330, 10, 100, 20));
        lblUnidad.setFont(new Font("SansSerif", 0, 11));
        lblStockEntero.setText("10");
        lblStockEntero.setBounds(new Rectangle(510, 35, 55, 20));
        lblStockEntero.setFont(new Font("SansSerif", 0, 11));
        txtFechaInicio.setBounds(new Rectangle(125, 45, 85, 20));
        txtFechaInicio.setDocument(new FarmaLengthText(10));
        txtFechaInicio.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaInicio_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaInicio_keyReleased(e);
            }
            
            public void keyTyped(KeyEvent e) {
                txtFechaInicio_keyTyped(e);
                }
        });
        txtFechaFin.setBounds(new Rectangle(225, 45, 85, 20));
        txtFechaFin.setDocument(new FarmaLengthText(10));
        txtFechaFin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaFin_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaFin_keyReleased(e);
            }
            
            public void keyTyped(KeyEvent e) {
                txtFechaFin_keyTyped(e);
                }
        });
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(330, 45, 90, 20));
        btnBuscar.setMnemonic('B');
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    btnBuscar_actionPerformed(e);
                }
        });
        btnRangoFechas.setText("Rango de Fechas:");
        btnRangoFechas.setBounds(new Rectangle(10, 45, 110, 20));
        btnRangoFechas.setMnemonic('F');
        btnRangoFechas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRangoFechas_actionPerformed(e);
            }
        });
        pnllTitle1.setBounds(new Rectangle(10, 105, 760, 25));
        btnRelacionMovimiento.setText("Relación de Movimientos del Producto");
        btnRelacionMovimiento.setBounds(new Rectangle(15, 5, 215, 15));
        btnRelacionMovimiento.setMnemonic('R');
        btnRelacionMovimiento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionMovimiento_actionPerformed(e);
            }
        });
        scrListaProductos.setBounds(new Rectangle(10, 130, 760, 175));
        tblListaMovs.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblListaProductos_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                tblListaMovs_keyReleased(e);
            }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(635, 340, 130, 20));
        lblLaboratorio_T.setText("Laboratorio:");
        lblLaboratorio_T.setBounds(new Rectangle(440, 10, 70, 20));
        lblLaboratorio.setText("PERUANO GERMANO (EDMUNDO STAHL)");
        lblLaboratorio.setBounds(new Rectangle(510, 10, 215, 20));
        lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
        lblUnidadFraccion.setBounds(new Rectangle(575, 35, 135, 20));
        lblUnidadFraccion.setFont(new Font("SansSerif", 0, 11));
        lblF8.setText("[ F8 ] Exportar a Excel");
        lblF8.setBounds(new Rectangle(250, 340, 135, 20));
        lblF8.setVisible(false);
        pnllTitle2.setBounds(new Rectangle(10, 105, 760, 25));
        btnRelacionMovimiento1.setText("Relaci\u00f3n de Mermas del Producto");
        btnRelacionMovimiento1.setBounds(new Rectangle(15, 5, 215, 15));
        btnRelacionMovimiento1.setMnemonic('R');
        btnRelacionMovimiento1.setActionCommand("Relaci\u00f3n de Mermas del Producto");
        btnRelacionMovimiento1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionMovimiento_actionPerformed(e);
            }
        });
        pnllTitle3.setBounds(new Rectangle(10, 305, 760, 25));
        lblCantMovimientoT.setText("Cantidad de Mermas :");
        lblCantMovimientoT.setBounds(new Rectangle(10, 5, 145, 15));
        lblCantMovieminto.setBounds(new Rectangle(160, 5, 110, 15));
        lblExcluido.setBounds(new Rectangle(550, 0, 135, 25));
        lblExcluido.setFont(new Font("SansSerif", 1, 12));
        lblExcluido.setText("Excluido de Reposición");
        lblExcluido.setBackground(new Color(44, 146, 24));
        lblExcluido.setOpaque(true);
        lblExcluido.setHorizontalAlignment(SwingConstants.CENTER);
        lblExcluido.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblExcluido.setForeground(Color.white);
        lblExcluido.setVisible(false);
        lblTodos.setText("[ F2 ] Ver Todos");
        lblTodos.setBounds(new Rectangle(465, 340, 145, 20));
        lblReversa.setText("[ F1 ] Reversa Merma");
        lblReversa.setBounds(new Rectangle(285, 340, 150, 20));
        pnllTitle2.add(lblExcluido, null);
        pnllTitle2.add(btnRelacionMovimiento1, null);
        pnlHeader1.add(lblUnidadFraccion, null);
        pnlHeader1.add(lblLaboratorio, null);
        pnlHeader1.add(lblLaboratorio_T, null);
        pnlHeader1.add(btnRangoFechas, null);
        pnlHeader1.add(btnBuscar, null);
        pnlHeader1.add(txtFechaFin, null);
        pnlHeader1.add(txtFechaInicio, null);
        pnlHeader1.add(lblStockEntero, null);
        pnlHeader1.add(lblUnidad, null);
        pnlHeader1.add(lblStock_T, null);
        pnlHeader1.add(lblProducto, null);
        pnlHeader1.add(lblProducto_T, null);
        pnlHeader1.add(lblUnidad_T, null);
        pnllTitle3.add(lblCantMovieminto, null);
        pnllTitle3.add(lblCantMovimientoT, null);
        jPanelWhite1.add(lblTodos, null);
        jPanelWhite1.add(lblReversa, null);
        jPanelWhite1.add(pnllTitle3, null);
        jPanelWhite1.add(pnllTitle2, null);
        jPanelWhite1.add(lblF8, null);
        jPanelWhite1.add(lblEsc, null);
        scrListaProductos.getViewport().add(tblListaMovs, null);
        jPanelWhite1.add(scrListaProductos, null);
        pnllTitle1.add(btnRelacionMovimiento, null);
        jPanelWhite1.add(pnllTitle1, null);
        jPanelWhite1.add(pnlHeader1, null);
        this.getContentPane().add(jPanelWhite1, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        initTable();
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) && FarmaVariables.vEconoFar_Matriz){
            lblF8.setVisible(true);
        }
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTable() {
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsListaMovsKardex, ConstantsInventario.defaultListaMovsKardex,
                                    0);
        FarmaUtility.initSimpleList(tblListaMovs, tableModel, ConstantsInventario.columnsListaMovsKardex);
        cargarFechas();

    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void btnRangoFechas_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFechaInicio);
    }

    private void tblListaProductos_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void txtFechaFin_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBuscar.doClick();

        } else {
            chkKeyPressed(e);
        }
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtFechaInicio);
        mostrarDatos();
    }

    private void txtFechaInicio_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtFechaFin);
        } else {
            chkKeyPressed(e);
        }

    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        if (datosValidados()) {
            cargarFechas();
            cargaListaMovimientos("526");
        }
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void txtFechaInicio_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaInicio, e);
        FarmaGridUtils.buscarDescripcion(e, tblListaMovs, txtFechaInicio, 0);
    }

    private void txtFechaFin_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaFin, e);
    }
    
    private void txtFechaInicio_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtFechaInicio, e);
    }
    
    private void txtFechaFin_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtFechaFin, e);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    boolean bandF2 = true;
    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.cerrarVentana(false);
        } else if(e.getKeyCode() == KeyEvent.VK_F2){
            if(bandF2){
                cargaListaMovimientos("");
                bandF2 = false;
                lblTodos.setText("[ F2 ] Ver Mermas");
            }else{
                cargaListaMovimientos("526");
                bandF2 = true;
                lblTodos.setText("[ F2 ] Ver Todos");
            }
        } else if(e.getKeyCode()==KeyEvent.VK_F1){
            if(lblReversa.isEnabled() && lblReversa.isVisible()){
                if(tblListaMovs.getSelectedRow() != -1){
                    if(tblListaMovs.getValueAt(tblListaMovs.getSelectedRow(), 15).toString().trim().equals("0") && 
                        tblListaMovs.getValueAt(tblListaMovs.getSelectedRow(), 14).toString().trim().equals("526")){
                        cargaLogin();
                    }else{
                        FarmaUtility.showMessage(this, "El registro seleccionado ya ha sido revertido.", null);
                    }
                }
            }
        } else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_UP){
            if(tblListaMovs.getSelectedRow() != -1){
                int registroSeleccionado = -1;
                int selectedRow = tblListaMovs.getSelectedRow();
                int rowCount = tblListaMovs.getRowCount();
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    registroSeleccionado = selectedRow == rowCount - 1 ? selectedRow : selectedRow + 1; 
                }else{
                    registroSeleccionado = selectedRow == 0 ? selectedRow : selectedRow - 1; 
                }
                if(tblListaMovs.getValueAt(registroSeleccionado, 14).toString().trim().equals("526")){
                    lblReversa.setEnabled(true);
                    lblReversa.setVisible(true);
                }else{
                    lblReversa.setEnabled(false);
                    lblReversa.setVisible(false);
                }
            }
        }
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************
    
    private void cargaLogin() {
        DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
        dlgLogin.setRolUsuario(FarmaConstants.ROL_ADMIN_MERMA);
        dlgLogin.setVisible(true);

        if (FarmaVariables.vAceptar) {
            FarmaVariables.dlgLogin = dlgLogin;
            FarmaVariables.vAceptar = false;
            aplicaReversa();
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void mostrarDatos() {
        lblProducto.setText(VariablesConvalidado.vCodProd + " " + VariablesConvalidado.vDescProd);
        lblLaboratorio.setText(VariablesConvalidado.vNomLab);
        lblUnidad.setText(VariablesConvalidado.vDescUnidPresent);
        lblStockEntero.setText("" + VariablesConvalidado.vStock);
        lblUnidadFraccion.setText(VariablesConvalidado.vDescUnidFrac);
    }

    private boolean datosValidados() {
        if (!FarmaUtility.validaFecha(txtFechaInicio.getText(), "dd/MM/yyyy") ||
            txtFechaInicio.getText().length() != 10) {
            FarmaUtility.showMessage(this, "Ingrese la Fecha Inicial correctamente", txtFechaInicio);
            return false;
        }
        if (!FarmaUtility.validaFecha(txtFechaFin.getText(), "dd/MM/yyyy") || txtFechaFin.getText().length() != 10) {
            FarmaUtility.showMessage(this, "Ingrese la Fecha Final correctamente", txtFechaFin);
            return false;
        }
        Date fecIni = FarmaUtility.getStringToDate(txtFechaInicio.getText().trim(), "dd/MM/yyyy");
        Date fecFin = FarmaUtility.getStringToDate(txtFechaFin.getText().trim(), "dd/MM/yyyy");

        if (fecIni.after(fecFin)) {
            FarmaUtility.showMessage(this, "La Fecha Inicial no puede ser posterior a la Fecha Final", txtFechaInicio);
            return false;
        }
        return true;
    }

    private void cargaListaMovimientos(String filtro) {
        try {
            if(filtro.equals("")){
                tableModel.clearTable();
            }
            DBConvalidado.getListaMovsKardex(tableModel, filtro);
            sumaCantidades();
            if (tblListaMovs.getRowCount() > 0) {
                FarmaUtility.ordenar(tblListaMovs, tableModel, 10, FarmaConstants.ORDEN_DESCENDENTE);
                if(!tblListaMovs.getValueAt(tblListaMovs.getSelectedRow(), 14).toString().trim().equals("526")){
                    lblReversa.setEnabled(false);
                    lblReversa.setVisible(false);
                }else{
                    lblReversa.setEnabled(true);
                    lblReversa.setVisible(true);
                }
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al cargar la lista de movimientos : \n" +
                    sql.getMessage(), txtFechaInicio);
        }
        if (tblListaMovs.getRowCount() == 0) {
            FarmaUtility.showMessage(this, "La búsqueda no arrojó resultados.", txtFechaInicio);
            return;
        }
        FarmaUtility.moveFocusJTable(tblListaMovs);

    }

    private void cargarFechas() {
        VariablesConvalidado.vFecIniMovKardex = txtFechaInicio.getText().trim();
        VariablesConvalidado.vFecFinMovKardex = txtFechaFin.getText().trim();
    }

    private void btnRelacionMovimiento_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblListaMovs);
    }
    
    private void sumaCantidades() {
        //lblCantMovieminto.setText(FarmaUtility.formatNumber(FarmaUtility.sumColumTable(tblListaMovs, 11)));
        lblCantMovieminto.setText(tblListaMovs.getRowCount()+"");
    }

    private void tblListaMovs_keyReleased(KeyEvent e) {
        muestraIndicadorExcluido();
    }

    private void muestraIndicadorExcluido() {
        if (tblListaMovs.getRowCount() > 0) {
            String indExcluido = FarmaUtility.getValueFieldJTable(tblListaMovs, tblListaMovs.getSelectedRow(), 13);
            if (indExcluido.equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                lblExcluido.setVisible(true);
            } else
                lblExcluido.setVisible(false);
        }
    } 

    private void aplicaReversa() {
        if(JConfirmDialog.rptaConfirmDialogDefaultNo(this,"¿Está seguro que desea realizar la reversa de la merma?")){
            String cantMermar = tblListaMovs.getValueAt(tblListaMovs.getSelectedRow(), 5).toString().trim();
            try {
                DBConvalidado.ingresaReversaMerma("527", cantMermar, "", tblListaMovs.getValueAt(tblListaMovs.getSelectedRow(), 10).toString().trim());
                FarmaUtility.aceptarTransaccion();
            } catch (SQLException e) {
                FarmaUtility.liberarTransaccion();
                System.out.println("Error: "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
}