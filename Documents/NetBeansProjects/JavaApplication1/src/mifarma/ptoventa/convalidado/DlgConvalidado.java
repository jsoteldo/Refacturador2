package mifarma.ptoventa.convalidado;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convalidado.reference.DBConvalidado;
import mifarma.ptoventa.convalidado.reference.VariablesConvalidado;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.ventas.reference.DBVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgConvalidado extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgConvalidado.class);

    Frame myParentFrame;
    FarmaTableModel tableModel;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JPanelHeader pnlHeader1 = new JPanelHeader();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JButtonLabel btnBuscar = new JButtonLabel();
    private JTextFieldSanSerif txtBuscar = new JTextFieldSanSerif();
    private JLabelWhite lblLocal_T = new JLabelWhite();
    private JLabelWhite lblLocal = new JLabelWhite();
    private JScrollPane scrListaProductos = new JScrollPane();
    private JTable tblListaProductos = new JTable();
    private JPanelTitle pnlTitle2 = new JPanelTitle();

    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction jLabelFunction5 = new JLabelFunction();
    private JLabelFunction jLabelFunction6 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction jLabelFunction8 = new JLabelFunction();

    private JButtonLabel btnRelacionProductos = new JButtonLabel();


    private JLabelWhite lblTotalRegistros_T = new JLabelWhite();

    private JLabelWhite lblTotalRegistros = new JLabelWhite();

    private String tipo_reposicion;
    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgConvalidado() {
        this(null, "", false);
    }

    public DlgConvalidado(Frame parent, String title, boolean modal) {
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
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(800, 437));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Convalidado");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlHeader1.setBounds(new Rectangle(10, 10, 775, 35));
        pnlTitle1.setBounds(new Rectangle(10, 50, 775, 30));
        btnBuscar.setText("Buscar por:");
        btnBuscar.setBounds(new Rectangle(5, 5, 70, 20));
        btnBuscar.setMnemonic('B');
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        txtBuscar.setBounds(new Rectangle(75, 5, 250, 20));
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtBuscar_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtBuscar_keyReleased(e);
            }
        });
        lblLocal_T.setText("Local:");
        lblLocal_T.setBounds(new Rectangle(395, 0, 40, 30));
        lblLocal.setText("XXXXX");
        lblLocal.setBounds(new Rectangle(440, 5, 225, 20));
        scrListaProductos.setBounds(new Rectangle(10, 80, 775, 245));
        tblListaProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblListaProductos_keyPressed(e);
            }
        });
        pnlTitle2.setBounds(new Rectangle(10, 325, 775, 25));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(635, 360, 150, 20));
        lblF1.setText("[ F2 ] Detalle Merma");
        lblF1.setBounds(new Rectangle(10, 360, 150, 20));
        jLabelFunction5.setText("jLabelFunction1");
        jLabelFunction5.setBounds(new Rectangle(20, 325, 100, 20));
        jLabelFunction6.setText("jLabelFunction1");
        jLabelFunction6.setBounds(new Rectangle(110, 375, 100, 20));
        lblF2.setText("[ F3 ] Registrar Merma");
        lblF2.setBounds(new Rectangle(175, 360, 150, 20));
        jLabelFunction8.setText("jLabelFunction1");
        jLabelFunction8.setBounds(new Rectangle(110, 375, 100, 20));
        btnRelacionProductos.setText("Relación de Productos");
        btnRelacionProductos.setBounds(new Rectangle(5, 10, 140, 15));
        btnRelacionProductos.setMnemonic('R');
        btnRelacionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionProductos_actionPerformed(e);
            }
        });
        lblTotalRegistros_T.setText("Total de Registros:");
        lblTotalRegistros_T.setBounds(new Rectangle(15, 5, 115, 20));
        lblTotalRegistros_T.setVisible(false);
        lblTotalRegistros.setText("100");
        lblTotalRegistros.setBounds(new Rectangle(150, 5, 70, 20));
        lblTotalRegistros.setVisible(false);
        scrListaProductos.getViewport().add(tblListaProductos, null);
        lblF2.add(jLabelFunction8, null);
        jContentPane.add(lblF2, null);
        lblF1.add(jLabelFunction6, null);
        jContentPane.add(lblF1, null);
        lblEsc.add(jLabelFunction5, null);
        jContentPane.add(lblEsc, null);
        pnlTitle2.add(lblTotalRegistros, null);
        pnlTitle2.add(lblTotalRegistros_T, null);
        jContentPane.add(pnlTitle2, null);
        jContentPane.add(scrListaProductos, null);
        jContentPane.add(pnlTitle1, null);
        pnlTitle1.add(btnRelacionProductos, null);
        jContentPane.add(pnlHeader1, null);
        pnlHeader1.add(lblLocal, null);
        pnlHeader1.add(lblLocal_T, null);
        pnlHeader1.add(txtBuscar, null);
        pnlHeader1.add(btnBuscar, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void initialize() {
        initTable();
    };

    private void initTable() {
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsListaProductosAK, ConstantsInventario.defaultValuesListaProductosAK,
                                    0);
        FarmaUtility.initSimpleList(tblListaProductos, tableModel, ConstantsInventario.columnsListaProductosAK);
        cargaListaProductos();
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void tblListaProductos_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void btnRelacionProductos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblListaProductos);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtBuscar);
        lblLocal.setText(FarmaVariables.vDescCortaLocal);
        log.debug("ROL USUARIO: " + FarmaVariables.vNuSecUsu);
        if (validarAsistAudit()) {
            lblF2.setVisible(false);
        }
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtBuscar);
    }

    private void txtBuscar_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblListaProductos, txtBuscar, 1);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblListaProductos.getSelectedRow() >= 0) {
                String pCodBusque = txtBuscar.getText().trim();
                if (FarmaUtility.findTextInJTable(tblListaProductos, pCodBusque, 0, 1)) {
                    //realiza busqueda por codigo
                } else if (!"000000".equalsIgnoreCase(codBarra())) {
                    String temp = codBarra();
                    FarmaUtility.findTextInJTable(tblListaProductos, temp, 0,
                                                  1); //realiza busqueda por codigo de barra
                    txtBuscar.setText("");
                } else {
                    FarmaUtility.showMessage(this, "Producto No Encontrado según Criterio de Búsqueda !!!", txtBuscar);
                }
            }
        }
        chkKeyPressed(e);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void txtBuscar_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblListaProductos, txtBuscar, 1);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************
    private boolean validacionPermisosTICO() {
        boolean isQuimico=FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL);
        boolean isLocalTico=UtilityPtoVenta.EsLocalTICO();
        if(isQuimico && isLocalTico){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean verificarProductoInctivo() {
        String inactivo=tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 9).toString().trim();
        if(inactivo.equalsIgnoreCase("I")){
            return true;
        }else{
            return false;
        }
    }
    
    private void chkKeyPressed(KeyEvent e) {
        if(tblListaProductos.getSelectedRow() != -1){
            boolean prodInactivo=verificarProductoInctivo();
            if(prodInactivo && e.getKeyCode() != KeyEvent.VK_ESCAPE){
                if(e.getKeyCode() == KeyEvent.VK_F3){
                    FarmaUtility.showMessage(this, "No se puede mermar un producto inactivo", txtBuscar);
                    return;
                }else if(e.getKeyCode() != KeyEvent.VK_F2){
                    return;
                }
            }
        }
        if (UtilityPtoVenta.verificaVK_F2(e)) {
            if (tieneRegistroSeleccionado(tblListaProductos)) {
                cargarRegistroSeleccionado();
                DlgReporteMerma dlgReporteMerma = new DlgReporteMerma(myParentFrame, "", true);
                dlgReporteMerma.setVisible(true);
                cargaListaProductos();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
            boolean realizaAjuste=validacionPermisosTICO();
            if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_SUPERVISOR_VENTAS)) {
                if (FarmaVariables.vEconoFar_Matriz)
                    FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, txtBuscar);
                else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                         FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_OPERADOR_SISTEMAS)||realizaAjuste) {
                    if (tieneRegistroSeleccionado(tblListaProductos)) {
                        cargarRegistroSeleccionado();
                        if (VariablesConvalidado.vIndProdVirtual.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                            FarmaUtility.showMessage(this,
                                                     "No se puede realizar un ajuste al tipo de producto virtual",
                                                     txtBuscar);
                        } else {
                            if (!validarAsistAudit()) {
                                DlgMermas dlgMermas = new DlgMermas(myParentFrame, "", true);
                                dlgMermas.setVisible(true);
                                if (FarmaVariables.vAceptar) {
                                    tableModel.clearTable();
                                    cargaListaProductos();
                                    tblListaProductos.repaint();
                                }
                            }
                        }
                    }
                } else
                    FarmaUtility.showMessage(this,
                                             "No tiene los permisos requeridos para acceder a esta opcion",
                                             txtBuscar);

            } else {
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción", null);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F6) {
            if (VariablesPtoVenta.vInd_Filtro.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                VariablesPtoVenta.vInd_Filtro = FarmaConstants.INDICADOR_N;
                tableModel.clearTable();
                cargaListaProductos();
                FarmaUtility.moveFocus(txtBuscar);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        }
    }
    
    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************
    public void cargaListaProductos() {
        try {
            DBConvalidado.getListaProdsAK(tableModel);
            if (tblListaProductos.getRowCount() > 0)
                FarmaUtility.ordenar(tblListaProductos, tableModel, 1, FarmaConstants.ORDEN_ASCENDENTE);
            
            //Para pintar de color rojo los productos inactivos
            ArrayList rowsWithOtherColor = new ArrayList();
            for (int i = 0; i < tableModel.data.size(); i++) {
                if (((ArrayList)tableModel.data.get(i)).get(9).toString().trim().equalsIgnoreCase("I")) { //cantguias 8 es 0
                    rowsWithOtherColor.add(String.valueOf(i));
                }
            }
            FarmaUtility.initSimpleListCleanColumns(tblListaProductos, tableModel,
                                                    ConstantsInventario.columnsListaProductosAK, rowsWithOtherColor,
                                                    Color.white, Color.red, false);

            tblListaProductos.getTableHeader().setReorderingAllowed(false);
            tblListaProductos.getTableHeader().setResizingAllowed(false);
            
            log.debug("se cargo la lista de prods");
        } catch (SQLException sql) {
            FarmaUtility.showMessage(this, "Ocurrió un error al cargar la lista de productos : \n" +
                    sql.getMessage(), txtBuscar);
        }
        lblTotalRegistros.setText("" + tblListaProductos.getRowCount());
    }

    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;
        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    private void cargarRegistroSeleccionado() {
        VariablesConvalidado.vCodProd =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 0).toString().trim();
        VariablesConvalidado.vDescProd =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 1).toString().trim();
        VariablesConvalidado.vDescUnidPresent =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 2).toString().trim();
        VariablesConvalidado.vNomLab =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 3).toString().trim();
        VariablesConvalidado.vDescUnidFrac =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 4).toString().trim();
        VariablesConvalidado.vCant =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 5).toString().trim();
        VariablesConvalidado.vCantFrac =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 6).toString().trim();
        VariablesConvalidado.vFrac =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 7).toString().trim();
        VariablesConvalidado.vIndProdVirtual =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 8).toString().trim();
        //VariablesConvalidado.vStock = Integer.parseInt(VariablesConvalidado.vCant);
        VariablesConvalidado.vStock = (Integer.parseInt(VariablesConvalidado.vCant) * Integer.parseInt(VariablesConvalidado.vFrac)) +
                                       Integer.parseInt(VariablesConvalidado.vCantFrac);
    }
    
    private boolean validarAsistAudit() {
        boolean flag = false;
        String ind = "";
        try {
            ind =
    DBConvalidado.validarAsistenteAuditoria(FarmaVariables.vCodCia, FarmaVariables.vCodLocal, FarmaVariables.vNuSecUsu);
            if (ind.equalsIgnoreCase("S"))
                flag = true;
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "ERROR en validarAsistAudit \n : " + sql.getMessage(), null);
        }
        return flag;
    }

    private String codBarra() {
        String cadena = txtBuscar.getText().trim();
        cadena = UtilityPtoVenta.getCadenaAlfanumerica(cadena);
        cadena = UtilityPtoVenta.getCodBarraSinCarControl(cadena);
        String codBarra = "";
        try {
            codBarra = DBVentas.obtieneCodigoProductoBarra(cadena);
            return codBarra;

        } catch (SQLException e) {
            log.error("", e);
            return "000000";
        }

    }

    public void setTipo_reposicion(String tipo_reposicion) {
        this.tipo_reposicion = tipo_reposicion;
    }

}
