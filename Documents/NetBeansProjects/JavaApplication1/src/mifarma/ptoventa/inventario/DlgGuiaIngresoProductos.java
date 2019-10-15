package mifarma.ptoventa.inventario;


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
import java.awt.event.ContainerEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cotizarPrecios.DlgCotizacionIngreseCantidad;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgGuiaIngresoProductos extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgGuiaIngresoProductos.class);

    Frame myParentFrame;
    FarmaTableModel tableModel;
    ArrayList arrayProductos = new ArrayList();

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JScrollPane scrListaProductos = new JScrollPane();
    private JPanel pnlHeader = new JPanel();
    private JButton btnListaProducto = new JButton();
    private JPanel pnlTitle = new JPanel();
    private JButton btnBuscar = new JButton();
    private JTextFieldSanSerif txtProducto = new JTextFieldSanSerif();
    private JButton btnProducto = new JButton();
    private JTable tblListaProductos = new JTable();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    
    private String tipoProcesoCotizacion;

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgGuiaIngresoProductos() {
        this(null, "", false);
    }

    public DlgGuiaIngresoProductos(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(720, 441));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Guia de Ingreso - Lista de Productos");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setLayout(null);
        jContentPane.setBackground(Color.white);
        jContentPane.setSize(new Dimension(594, 405));
        lblEnter.setText("[ ENTER ] Seleccionar/Deseleccionar Producto");
        lblEnter.setBounds(new Rectangle(15, 375, 270, 20));
        scrListaProductos.setBounds(new Rectangle(10, 90, 695, 270));
        scrListaProductos.setBackground(new Color(255, 130, 14));
        pnlHeader.setBounds(new Rectangle(10, 65, 695, 25));
        pnlHeader.setBackground(new Color(255, 130, 14));
        pnlHeader.setLayout(null);
        btnListaProducto.setText("Lista de Productos");
        btnListaProducto.setBounds(new Rectangle(10, 0, 145, 25));
        btnListaProducto.setBackground(new Color(255, 130, 14));
        btnListaProducto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnListaProducto.setBorderPainted(false);
        btnListaProducto.setContentAreaFilled(false);
        btnListaProducto.setDefaultCapable(false);
        btnListaProducto.setFocusPainted(false);
        btnListaProducto.setFont(new Font("SansSerif", 1, 11));
        btnListaProducto.setForeground(Color.white);
        btnListaProducto.setHorizontalAlignment(SwingConstants.LEFT);
        btnListaProducto.setRequestFocusEnabled(false);
        pnlTitle.setBounds(new Rectangle(10, 20, 695, 40));
        pnlTitle.setBackground(new Color(43, 141, 39));
        pnlTitle.setLayout(null);
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(425, 10, 115, 25));
        btnBuscar.setFont(new Font("SansSerif", 1, 12));
        btnBuscar.setDefaultCapable(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setMnemonic('b');
        btnBuscar.setRequestFocusEnabled(false);
        txtProducto.setBounds(new Rectangle(110, 10, 295, 20));
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtProducto_keyPressed(e);
                }

            public void keyReleased(KeyEvent e) {
                txtProducto_keyReleased(e);
            }
        });
        btnProducto.setText("Producto :");
        btnProducto.setBounds(new Rectangle(25, 10, 70, 20));
        btnProducto.setFont(new Font("SansSerif", 1, 12));
        btnProducto.setForeground(Color.white);
        btnProducto.setBackground(new Color(43, 141, 39));
        btnProducto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProducto.setBorderPainted(false);
        btnProducto.setContentAreaFilled(false);
        btnProducto.setDefaultCapable(false);
        btnProducto.setFocusPainted(false);
        btnProducto.setMnemonic('p');
        btnProducto.setRequestFocusEnabled(false);
        btnProducto.setHorizontalAlignment(SwingConstants.LEFT);
        btnProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnProducto_actionPerformed(e);
            }
        });
        btnProducto.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(ContainerEvent e) {
                btnProducto_componentRemoved(e);
            }
        });
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(445, 375, 105, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(555, 375, 85, 20));
        scrListaProductos.getViewport();
        pnlHeader.add(btnListaProducto, null);
        pnlTitle.add(btnBuscar, null);
        pnlTitle.add(txtProducto, null);
        pnlTitle.add(btnProducto, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(lblEnter, null);
        scrListaProductos.getViewport().add(tblListaProductos, null);
        jContentPane.add(scrListaProductos, null);
        jContentPane.add(pnlHeader, null);
        jContentPane.add(pnlTitle, null);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        tipoProcesoCotizacion="0";
        initTable();
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTable() {
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsListaGuiaIngresoProductos, ConstantsInventario.defaultListaGuiaIngresoProductos,
                                    0);
        tableModel.data = VariablesVentas.tableModelListaGlobalProductos.data;
        FarmaUtility.initSelectList(tblListaProductos, tableModel,
                                    ConstantsInventario.columnsListaGuiaIngresoProductos);
        for (int i = 0; i < VariablesVentas.tableModelListaGlobalProductos.getRowCount(); i++)
            VariablesVentas.tableModelListaGlobalProductos.setValueAt(new Boolean(false), i, 0);
        cargaProductosSeleccionados();
        /////////////////////////////
        if (tableModel.getRowCount() > 0)
            FarmaUtility.ordenar(tblListaProductos, tableModel, 2,
                                 FarmaConstants.ORDEN_ASCENDENTE);
        ///////////////////////////////
        FarmaGridUtils.showCell(tblListaProductos, 0, 0);
    }


    private void cargaLista() {
        try {
            DBInventario.cargaListaProductosGuiaIngreso(tableModel);
            FarmaUtility.ordenar(tblListaProductos, tableModel, 2, FarmaConstants.ORDEN_ASCENDENTE);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al cargar la lista de productos : \n" +
                    sql.getMessage(), txtProducto);
        }

    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtProducto);
    }

    private void btnProducto_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProducto);
    }

    private void txtProducto_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblListaProductos, txtProducto, 2);

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblListaProductos.getSelectedRow() >= 0) {
                if (!(FarmaUtility.findTextInJTable(tblListaProductos, txtProducto.getText().trim(), 1, 2))) {
                    //FarmaUtility.showMessage(this, "Producto No Encontrado seg�n Criterio de B�squeda !!!",
                    //                         txtProducto);
                    //return;
                    if (!"000000".equalsIgnoreCase(buscaCodBarra())) { //si se encuentra el cod de barras, ubicarlo en la tabla y mostrar el detalle
                        String temp = buscaCodBarra();
                        FarmaUtility.findTextInJTable(tblListaProductos, temp, 1, 2);
                        //seleccionarProducto();
                    } else {
                        FarmaUtility.showMessage(this, "Producto No Encontrado seg�n Criterio de B�squeda !!!",txtProducto);
                        return;
                    }
                }
            }
        }

        chkKeyPressed(e);
    }
    
    private String buscaCodBarra() {
        UtilityPtoVenta.limpiaCadenaAlfanumerica(txtProducto);
        String productoBuscar = "";
        try {
            productoBuscar = DBVentas.obtieneCodigoProductoBarra(txtProducto.getText());
            return productoBuscar;
        } catch (SQLException q) {
            log.error("", q);
            return "000000";
        }
    }


    private void txtProducto_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblListaProductos, txtProducto, 2);
    }

    private void btnProducto_componentRemoved(ContainerEvent e) {
        FarmaUtility.moveFocus(txtProducto);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            seleccionarProducto();
        } else if (mifarma.ptoventa.reference.UtilityPtoVenta.verificaVK_F11(e)) {
            if(getTipoProcesoCotizacion().equals("3")){
              VarCotizacionPrecio.vIC_ArrayProductosCotizacion = arrayProductos;
            }else{
                VariablesInventario.vArrayGuiaIngresoProductos = arrayProductos;
            }
            cerrarVentana(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
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

    private void seleccionarProducto() {
        boolean seleccion =
            ((Boolean)tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 0)).booleanValue();
        if (!seleccion) {
            cargaCabeceraIngreseCantidad();
            if(tipoProcesoCotizacion.equals("3")){//si es cotizacion sin solicitud
                VarCotizacionPrecio.vIC_Cod_Prod= tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 1).toString();
                //limpiando variables globales
                VarCotizacionPrecio.vIC_Cant_Comprada= 0;
                VarCotizacionPrecio.vIC_Precio_Unitario ="";
                VarCotizacionPrecio.vIC_Total ="";
                DlgCotizacionIngreseCantidad dlgCotizacionIngreseCantidad = new DlgCotizacionIngreseCantidad(myParentFrame, "", true);
                dlgCotizacionIngreseCantidad.setTipoProceso(getTipoProcesoCotizacion());
                dlgCotizacionIngreseCantidad.setPrecioVta("0.00");
                dlgCotizacionIngreseCantidad.setUnidadVta(VariablesInventario.vUnidMed);
                dlgCotizacionIngreseCantidad.setDivisibleFraccion("S");
                dlgCotizacionIngreseCantidad.setVisible(true); 
            }else{
                    DlgGuiaIngresoCantidad dlgGuiaIngresoCantidad = new DlgGuiaIngresoCantidad(myParentFrame, "", true);
                    dlgGuiaIngresoCantidad.setVisible(true);
                }
            if (FarmaVariables.vAceptar) {
                agregarProducto();
                FarmaUtility.setCheckValue(tblListaProductos, false);
                FarmaVariables.vAceptar = false;
                tblListaProductos.setRowSelectionInterval(VariablesInventario.vPos, VariablesInventario.vPos);
            }
        } else {
            borrarProducto();
            FarmaUtility.setCheckValue(tblListaProductos, false);
        }

    }

    private void cargaCabeceraIngreseCantidad() {
        VariablesInventario.vCodProd = tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 1).toString();
        VariablesInventario.vNomProd = tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 2).toString();
        VariablesInventario.vUnidMed = tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 3).toString();
        VariablesInventario.vNomLab = tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 4).toString();
        VariablesInventario.vStkFisico =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 5).toString();
        VariablesInventario.vValFrac_Guia =
                tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 9).toString();
        VariablesInventario.vCant = "";
        VariablesInventario.vLote = "";
        VariablesInventario.vFechaVec = "";
        VariablesInventario.vPrecUnit = "";

        VariablesInventario.vPos = tblListaProductos.getSelectedRow();
    }

    private void agregarProducto() {
        ArrayList array = new ArrayList();
        array.add(VariablesInventario.vCodProd);
        array.add(VariablesInventario.vNomProd);
        if(getTipoProcesoCotizacion().equals("3")){
        array.add(VariablesInventario.vNomLab);
        array.add(VariablesInventario.vUnidMed);
        array.add("0");
        array.add("S");
        array.add(VarCotizacionPrecio.vIC_Cant_Comprada+"");
        array.add(VarCotizacionPrecio.vIC_Precio_Unitario);
        array.add(VarCotizacionPrecio.vIC_Total );
        array.add("");//Lote
        array.add("");//Fecha Venc.
        array.add( tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 9).toString());//Val. Frac.
        array.add( tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 6).toString());//Val. Prec Vta.
        array.add("0000000000");//Cod Solicitud
        array.add("0");//Cod Motivo
        array.add("N");//Indicador Tiene Cotizacion
        array.add("0");//16-CANTIDAD MINIMA DE INGRESO A COTIZAR
        array.add("0");//17- CANTIDAD SOLICITADA DIVISIBLE EN EL LOCAL
        
        VarCotizacionPrecio.vIC_Cant_Comprada=0;
        VarCotizacionPrecio.vIC_Precio_Unitario="";
        VarCotizacionPrecio.vIC_Total ="";
        }else{
        array.add(VariablesInventario.vUnidMed);
        array.add(VariablesInventario.vNomLab);
        array.add(VariablesInventario.vCant);
        array.add(VariablesInventario.vPrecUnit);
        array.add(VariablesInventario.vTotal);
        array.add(VariablesInventario.vLote);
        array.add(VariablesInventario.vFechaVec);
        array.add(VariablesInventario.vValFrac_Guia);
            }
        
        arrayProductos.add(array);
    }

    private void borrarProducto() {
        String cod = tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 1).toString();

        for (int i = 0; i < arrayProductos.size(); i++) {
            if (((ArrayList)arrayProductos.get(i)).contains(cod)) {
                arrayProductos.remove(i);
                break;
            }
        }
    }

    private void cargaProductosSeleccionados() {
        if (VariablesInventario.vArrayGuiaIngresoProductos.size() > 0) {
            arrayProductos = VariablesInventario.vArrayGuiaIngresoProductos;
            String cod;
            for (int i = 0; i < arrayProductos.size(); i++) {
                cod = ((ArrayList)arrayProductos.get(i)).get(0).toString();
                for (int j = 0; j < tblListaProductos.getRowCount(); j++) {
                    if (((ArrayList)tableModel.getRow(j)).contains(cod)) {
                        tableModel.setValueAt(new Boolean(true), j, 0);
                        break;
                    }
                }
            }
        }
    }

    public void setTipoProcesoCotizacion(String tipoProcesoCotizacion) {
        this.tipoProcesoCotizacion = tipoProcesoCotizacion;
    }

    public String getTipoProcesoCotizacion() {
        return tipoProcesoCotizacion;
    }
}
