package mifarma.ptoventa.inventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.inventario.dto.NotaEsCabDTO;
import mifarma.ptoventa.inventario.dto.NotaEsCabDetDTO;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.FacadeInventario;
import mifarma.ptoventa.inventario.reference.UtilityInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgMercaderiaDirectaProducto_02 extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(DlgMercaderiaDirectaProducto_02.class);

    Frame myParentFrame;
    FarmaTableModel tableModel;

    String strCodigoOrdCompDetalle = "1";
    ArrayList arrayProductos = new ArrayList();
    ArrayList arrayCab = new ArrayList();
    ArrayList arrayclone = new ArrayList();
    ArrayList arrayProdOrdComp = new ArrayList();
    private Pattern pattern;
    private Matcher matcher;
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
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JPanelTitle jPanelTitle2 = new JPanelTitle();
    private JLabelWhite lblTotal_l = new JLabelWhite();
    private JLabelWhite lblTotal = new JLabelWhite();
    private JFormattedTextField txtRedondeo = new JFormattedTextField();
    private JLabelFunction lblF4 = new JLabelFunction();
    private FacadeInventario facadeInventario = new FacadeInventario();
    private static final String REDONDEO_PATTERN = "^[+-]?[0-9]+(?:\\.[0-9]+)?$";
    private JButtonLabel jButtonLabel1 = new JButtonLabel();
    private JLabelFunction lblF5 = new JLabelFunction();

    private final int COL_VAL_FRAC = 8;

    private NotaEsCabDTO notaEsCabDTO = null;

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgMercaderiaDirectaProducto_02() {
        this(null, "", false);
    }

    public DlgMercaderiaDirectaProducto_02(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(720, 460));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Producto Ord. Comp. - Mercaderia Directa");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
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
        lblEnter.setText("[ ENTER ] Seleccionar");
        lblEnter.setBounds(new Rectangle(10, 370, 140, 20));
        lblF5.setText("[F5] Deseleccionar");
        lblF5.setBounds(new Rectangle(165, 370, 120, 20));
        scrListaProductos.setBounds(new Rectangle(10, 90, 695, 240));
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
        txtProducto.addKeyListener(new KeyAdapter() {
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
        btnProducto.addContainerListener(new ContainerAdapter() {
            public void componentRemoved(ContainerEvent e) {
                btnProducto_componentRemoved(e);
            }
        });
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(480, 370, 105, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(605, 370, 85, 20));
        jPanelTitle1.setBounds(new Rectangle(10, 345, 660, 20));
        jPanelTitle2.setBounds(new Rectangle(10, 330, 695, 20));
        jPanelTitle2.setSize(new Dimension(695, 20));
        lblTotal_l.setText("TOTAL "+ConstantesUtil.simboloSoles);
        lblTotal_l.setBounds(new Rectangle(545, 0, 70, 20));
        lblTotal.setText("0.00");
        lblTotal.setBounds(new Rectangle(625, 0, 55, 20));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtRedondeo.setBounds(new Rectangle(475, 0, 50, 20));
        txtRedondeo.setText("0.00");
        txtRedondeo.setHorizontalAlignment(SwingConstants.RIGHT);
        txtRedondeo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                pattern = Pattern.compile(REDONDEO_PATTERN, Pattern.CANON_EQ);
                txtRedondeo_keyPressed(e);
            }

        });
        lblF4.setBounds(new Rectangle(295, 370, 170, 20));
        lblF4.setText("[ F4 ] Modificar Cabecera");
        jButtonLabel1.setText("Redondeo:");
        jButtonLabel1.setBounds(new Rectangle(405, 0, 65, 20));
        jButtonLabel1.setMnemonic('r');
        jButtonLabel1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonLabel1_actionPerformed(e);
            }
        });
        scrListaProductos.getViewport();
        pnlHeader.add(btnListaProducto, null);
        pnlTitle.add(btnBuscar, null);
        pnlTitle.add(txtProducto, null);
        pnlTitle.add(btnProducto, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        //  jPanelTitle2.add(jButtonLabel1, null);Cesar Huanes se quito el redondeo y el total
        // jPanelTitle2.add(txtRedondeo, null);
        // jPanelTitle2.add(lblTotal, null);
        //jPanelTitle2.add(lblTotal_l, null);
        jContentPane.add(lblF5, null);
        jContentPane.add(lblF4, null);
        jContentPane.add(jPanelTitle2, null);
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
        notaEsCabDTO = new NotaEsCabDTO();
        initTable();
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTable() {
        tblListaProductos.getTableHeader().setReorderingAllowed(false);
        tblListaProductos.getTableHeader().setResizingAllowed(false);
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsListaItemOrdenSobr, ConstantsInventario.defaultListaItemOrdenSobr,
                                    0);
        FarmaUtility.initSelectList(tblListaProductos, tableModel, ConstantsInventario.columnsListaItemOrdenSobr);
        //mostrarDetalleOC();
    }


    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtProducto);
        FarmaUtility.showMessage(this, "Al finalizar la devoluci�n ya no podra volver a devolver nada de la OC", null);
        mostrarCabecera();
    }

    private void mostrarCabecera() {
        DlgDevolucionNueva_02 dlgDevolucionNueva = new DlgDevolucionNueva_02(myParentFrame, "", true);
        dlgDevolucionNueva.setNotaEsCabDTO(notaEsCabDTO);
        dlgDevolucionNueva.setVisible(true);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            mostrarDetalleOC();
        } else {
            cerrarVentana(false);
        }
    }

    private void btnProducto_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProducto);
    }

    private void txtRedondeo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validateText(txtRedondeo.getText().trim())) {
                addRedondeo();
                FarmaUtility.moveFocus(txtProducto);
            } else {
                FarmaUtility.showMessage(this, "Debe ingresar solo n�meros decimales.", null);
                FarmaUtility.moveFocus(txtRedondeo);
            }

        }
    }


    private void txtProducto_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblListaProductos, txtProducto, 2);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblListaProductos.getSelectedRow() >= 0) {
                if (!(FarmaUtility.findTextInJTable(tblListaProductos, txtProducto.getText().trim(), 1, 2))) {
                    FarmaUtility.showMessage(this, "Producto No Encontrado seg�n Criterio de B�squeda !!!",
                                             txtProducto);
                    return;
                }
            }
        }
        chkKeyPressed(e);
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

    private boolean validateText(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    private void jButtonLabel1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtRedondeo);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            seleccionarProducto();
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (listaConCantIngresada()) {
                if (JConfirmDialog.rptaConfirmDialogDefaultNo(this,
                                                              "�Est� seguro de continuar con la devoluci�n?\nLa Orden de Compra se cerrara y no podra recepcionar ning�n otro producto")) {
                    procesarDevolucion();
                }
            } else {
                if (JConfirmDialog.rptaConfirmDialogDefaultNo(this,
                                                              "No se ha registrado ninguna cantidad para devolver.\n�Est� seguro de continuar con la devoluci�n?\n" +
                        "\nLa Orden de Compra se cerrara y no podra recepcionar ning�n otro producto")) {
                    procesarDevolucion();

                }
            }

        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            if (JConfirmDialog.rptaConfirmDialog(this, "�Est� seguro de salir? Se perder� los datos.")) {
                arrayclone.clear();
                cerrarVentana(false);
            }

        } else if (e.getKeyCode() == KeyEvent.VK_F4) {
            mostrarCabecera();
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            deseleccionarProducto();
        }
    }

    private void procesarDevolucion() {
        FarmaVariables.vAceptar = false;
        DlgListaImpresorasInv dlgListaImpresorasInv = new DlgListaImpresorasInv(this.myParentFrame, "", true);
        dlgListaImpresorasInv.setVisible(true);

        if (!FarmaVariables.vAceptar) {
            return;
        }
        //CHUANES 14.03.2014
        //Verificamos la ruta y el acceso ala impresora correspondiente a imprimir
        if (!UtilityCaja.verificaImpresora(this, null, ConstantsPtoVenta.TIP_COMP_GUIA)) {
            return;
        }

        if (grabar()) {
            VariablesInventario.vArrayProductos = new ArrayList();

            /*
            cargaProductosSeleccionados();
            cabeceraRecepcion();
            guardaDB();
            */

            cerrarVentana(true);
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

        if (!verificaCantIngresada()) { //validamos la cantidad
            return;
        }
        cargaCabeceraIngreseCantidad();
        // kmoncada 15.07.2014 obtiene cantidad adicional permitida por OC y valor de innerpack del producto
        int rowSelec = tblListaProductos.getSelectedRow();
        int cantAdicPermitidaOCProd =
            FarmaUtility.trunc(FarmaUtility.getValueFieldArrayList(tableModel.data, rowSelec, 9));
        int valInnerPackProd = FarmaUtility.trunc(FarmaUtility.getValueFieldArrayList(tableModel.data, rowSelec, 14));

        DlgDevolucionIngresoCantidad_02 dlgDevolucion = new DlgDevolucionIngresoCantidad_02(myParentFrame, "", true);
        dlgDevolucion.setValInnerPackProd(valInnerPackProd);
        dlgDevolucion.setVisible(true);
        if (FarmaVariables.vAceptar) {
            // si esta marcado borramos el registro anterior adicionado para que no se repita en el reporte.
            if (seleccion == true) {
                borrarProducto();
            }
            agregarProducto();
            calculoTotal();
            cargaNewProduct();
            cargarIGV();
            if (seleccion == false) { // si esta deselecionado selecciona caso contrario lo mantiene seleccionado
                FarmaUtility.setCheckValue(tblListaProductos, false);
            }
            FarmaVariables.vAceptar = false;
            tblListaProductos.setRowSelectionInterval(VariablesInventario.vPos, VariablesInventario.vPos);

        }
    }

    private void cargaCabeceraIngreseCantidad() {
        int row = tblListaProductos.getSelectedRow();

        VariablesInventario.vCodProducto = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 1);
        VariablesInventario.vDescProd = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 2);
        VariablesInventario.vUnidMedida = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 6);
        VariablesInventario.vCantPedida = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 3);
        VariablesInventario.vRecepTotal = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 4);
        VariablesInventario.vPrecUnit = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 5);
        VariablesInventario.vValFrac = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 8);

        VariablesInventario.vCantEntregada = "";
        VariablesInventario.vPrecioIGV = "";
        VariablesInventario.vRedondeo = "";
        VariablesInventario.vPos = row;

        VariablesInventario.vLaborat = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 9);
        VariablesInventario.vStockDispo = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 10);

        VariablesInventario.vFechVentprod = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 11);
        VariablesInventario.vMunLote = FarmaUtility.getValueFieldArrayList(tableModel.data, row, 12);

        VariablesInventario.vFlagCantMov = true;


        //VariablesInventario.vCodigo=tblListaProductos.getValueAt(filaSelec,0).toString();
        //VariablesInventario.vDescrip=tblListaProductos.getValueAt(filaSelec,1).toString();
        //VariablesInventario.vDescUniPrese=tblListaProductos.getValueAt(filaSelec,2).toString();
        //VariablesInventario.vLaborat=tblListaProductos.getValueAt(filaSelec,3).toString();
        //VariablesInventario.vStockDispo=tblListaProductos.getValueAt(filaSelec,8).toString();

        //VariablesInventario.vCantMovi=Integer.parseInt(tblListaProductos.getValueAt(filaSelec,4).toString());
        //VariablesInventario.vPrecVtaVig=tblListaProductos.getValueAt(filaSelec,5).toString();
        //VariablesInventario.vFechVentprod= tblListaProductos.getValueAt(filaSelec,6).toString();
        //VariablesInventario.vMunLote=tblListaProductos.getValueAt(filaSelec,7).toString();

        //VariablesInventario.vValFrac=tblListaProductos.getValueAt(filaSelec,9).toString();


    }

    private void agregarProducto() {
        ArrayList array = new ArrayList();
        //   arrayProductos = new ArrayList();
        double total =
            (((FarmaUtility.getDecimalNumber(VariablesInventario.vCantEntregada))) * FarmaUtility.getDecimalNumber(VariablesInventario.vPrecioUnit));
        VariablesInventario.vImportRecep = FarmaUtility.formatNumber(total, 2);
        array.add(VariablesInventario.vCodProducto.trim());
        array.add(VariablesInventario.vCantPedida.trim());
        array.add(VariablesInventario.vCantEntregada.trim());
        array.add(VariablesInventario.vPrecioUnit.trim());
        array.add(VariablesInventario.vPrecioIGV.trim());
        array.add(VariablesInventario.vImportRecep.trim());
        array.add(strCodigoOrdCompDetalle.toString().trim());
        array.add(VariablesInventario.vValFrac);
        arrayProductos.add(array);
    }

    private void borrarProducto() {
        double subTotal = Double.parseDouble(VariablesInventario.vImportRecep);
        String cod = tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 1).toString();
        for (int i = 0; i < arrayProductos.size(); i++) {
            if (((ArrayList)arrayProductos.get(i)).contains(cod)) {
                //restarSubtotal
                subTotal = subTotal - (Double.parseDouble(((ArrayList)arrayProductos.get(i)).get(5).toString()));
                lblTotal.setText(FarmaUtility.formatNumber(subTotal, 2));
                VariablesInventario.vImportRecep = lblTotal.getText();

                int newValu =
                    Integer.parseInt(tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 4).toString()) -
                    Integer.parseInt(((ArrayList)arrayProductos.get(i)).get(2).toString());

                tblListaProductos.setValueAt(String.valueOf(newValu), tblListaProductos.getSelectedRow(), 4);
                //Actualiza IGV DataBase
                tblListaProductos.setValueAt(((ArrayList)arrayclone.get(i)).get(6).toString(),
                                             tblListaProductos.getSelectedRow(), 6);
                //Elimina Registro
                arrayProductos.remove(i);
                break;
            }
        }
    }
    //columnsListaGuiaIngresoProductos

    private void cargaProductosSeleccionados() {
        if (VariablesInventario.vArrayIngresoMercaderiaDirecta.size() > 0) {
            arrayProductos = VariablesInventario.vArrayIngresoMercaderiaDirecta;
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

    private void calculoTotal() {
        double total = 0.0;
        for (int i = 0; i < arrayProductos.size(); i++) {
            total =
                    total + (((FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayProductos, i, 2)))) *
                             FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(arrayProductos, i, 3)));
        }
        lblTotal.setText(FarmaUtility.formatNumber(total, 2));
        VariablesInventario.vImportRecep = lblTotal.getText();
    }

    private void addRedondeo() {
        double vRedon = Double.parseDouble(txtRedondeo.getText().trim());
        double vTotal = Double.parseDouble(lblTotal.getText().trim());
        lblTotal.setText(FarmaUtility.formatNumber(vTotal + vRedon, 2));
        VariablesInventario.vRedondeo = txtRedondeo.getText();
        VariablesInventario.vImportRecep = lblTotal.getText();
    }

    private void cargaNewProduct() {

        //Refrescamos el array para obtener el primer valor de la cant recibida
        //arrayProdOrdComp = facadeInventario.listarProdOrdenCompra(VariablesInventario.vNumOrdenCompra);
        String vall = ((ArrayList)arrayProdOrdComp.get(tblListaProductos.getSelectedRow())).get(4).toString();
        int valorAnterior = (vall.trim().equals("")) ? 0 : (int)Integer.parseInt(vall);
        int cantPed = valorAnterior + (int)Integer.parseInt(VariablesInventario.vCantEntregada);
        tableModel.setValueAt(String.valueOf(cantPed), tblListaProductos.getSelectedRow(), 4);
        tableModel.setValueAt(VariablesInventario.vImportRecep, tblListaProductos.getSelectedRow(), 16);
        tableModel.setValueAt(VariablesInventario.vFechVentprod, tblListaProductos.getSelectedRow(), 11);
        tableModel.setValueAt(VariablesInventario.vMunLote, tblListaProductos.getSelectedRow(), 12);

        tblListaProductos.setSize(5, 120);
        //tblListaProductos.show();


    }

    private void cargarIGV() {
        String igv = VariablesInventario.vPrecioIGV;
        VariablesInventario.vIGVDB = tableModel.getValueAt(tblListaProductos.getSelectedRow(), 6).toString();
        tableModel.setValueAt(String.valueOf(igv), tblListaProductos.getSelectedRow(), 6);
        tblListaProductos.setSize(5, 120);
        //tblListaProductos.show();
    }

    private void cabeceraRecepcion() {
        arrayCab.add(FarmaVariables.vCodGrupoCia.trim());
        arrayCab.add(FarmaVariables.vCodCia.trim());
        arrayCab.add(FarmaVariables.vCodLocal.trim());
        arrayCab.add(FarmaVariables.vIdUsu.trim());
        arrayCab.add(VariablesInventario.vNumOrdenCompra.trim());
        arrayCab.add(VariablesInventario.vFechIngreso.trim());
        int tipoDoc = Integer.valueOf(VariablesInventario.vIdeDocumento.trim()) + 1; //CHUANES  27.01.2014
        arrayCab.add('0' + String.valueOf(tipoDoc)); //correcion del tipo de documento
        arrayCab.add(VariablesInventario.vSerieDocument.trim());
        arrayCab.add(VariablesInventario.vNumeroDocument.trim());
        arrayCab.add(VariablesInventario.vCantItem.trim());
        arrayCab.add(VariablesInventario.vCodProveedor.trim());
        arrayCab.add(VariablesInventario.vImporteTotal.trim());
        arrayCab.add(VariablesInventario.vImportRecep.trim());
        arrayCab.add(VariablesInventario.vRedondeo.trim());
    }

    private void guardaDB() {

        boolean vRecepcion;
        try {
            vRecepcion = facadeInventario.guardarRecepcionOC(arrayCab, arrayProductos);
            //CHUANES 27.02.2014
            // No tiene sentido la condicion porque  siempre ingresa el mismo numero de documento.
            /*  if(!vRecepcion){

                FarmaUtility.showMessage(this, "El Documento: " + VariablesInventario.vDescripDocum +
                                               " Serie: " + VariablesInventario.vSerieDocument +
                                               " Numero: " + VariablesInventario.vNumeroDocument +
                                               " ya fue ingresado.", null);
            }else{*/
            FarmaUtility.showMessage(this,
                                     "Se agregaron sus producto ala Ord. de Compra " + VariablesInventario.vNumOrdenCompra.trim(),
                                     null);
            cerrarVentana(true);
            //   }

        } catch (Exception e) {
            log.error("", e);
            FarmaUtility.showMessage(this, e.getMessage(), txtProducto);
        }
        arrayclone.clear();

        boolean vCierre =
            facadeInventario.cierreOC(FarmaVariables.vCodGrupoCia.trim(), FarmaVariables.vCodLocal.trim(),
                                      VariablesInventario.vNumOrdenCompra.trim());
        if (vCierre) {
            FarmaUtility.showMessage(this,
                                     "La orden de compra " + VariablesInventario.vNumeroDocument + " Se cerro correctamente.",
                                     null);
            cerrarVentana(true);
        } else {
            ;
        }

    }

    void setFacade(FacadeInventario facadeInventario) {
        this.facadeInventario = facadeInventario;
    }

    private void mostrarDetalleOC() {
        try {
            DBInventario.listarProdOrdenCompraSobrante(VariablesInventario.vNumOrdenCompra, tableModel);

        } catch (Exception sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al cargar el detalle sobrante de la oc: \n" +
                    sql.getMessage(), txtProducto);
        }

        //arrayProdOrdComp = facadeInventario.listarProdOrdenCompraSobrante(VariablesInventario.vNumOrdenCompra);
        arrayProdOrdComp = tableModel.data;
        arrayclone = (ArrayList)arrayProdOrdComp.clone();

        if (tableModel.data.size() > 0) {
            for (int i = 0; i < tblListaProductos.getRowCount(); i++) {
                tblListaProductos.setValueAt(new Boolean(false), i, 0);
            }
            cargaProductosSeleccionados();
            FarmaGridUtils.showCell(tblListaProductos, 0, 0);
            log.info("SIZE: " + tblListaProductos.getRowCount());
        } else {
            FarmaUtility.showMessage(this, "Esta orden de compra no tiene productos para devolver por sobrantes",
                                     null);
            cerrarVentana(false);
        }

    }


    private void deseleccionarProducto() {
        boolean seleccion =
            ((Boolean)tblListaProductos.getValueAt(tblListaProductos.getSelectedRow(), 0)).booleanValue();
        if (seleccion) {
            if (JConfirmDialog.rptaConfirmDialog(this, "�Desea deseleccionar? Se perder� los datos ingresados.")) {
                borrarProducto();
                FarmaUtility.setCheckValue(tblListaProductos, false);
            }
        }
    }
    //Cesar Huanes-03.03.2014
    //Se verifica a nivel de base de datos la cant. Ped con la cant Rec.

    private boolean verificaCantIngresada() {
        int row = tblListaProductos.getSelectedRow();
        //arrayProdOrdComp = facadeInventario.listarProdOrdenCompra(VariablesInventario.vNumOrdenCompra);
        //String cantRec = ((ArrayList)arrayProdOrdComp.get(row)).get(3).toString();
        //String cantPed = ((ArrayList)arrayProdOrdComp.get(row)).get(4).toString();
        // KMONCADA
        String estadoProdCompleto = ((ArrayList)arrayProdOrdComp.get(row)).get(15).toString();
        //if (cantRec.equals(cantPed)) {
        if ("T".equalsIgnoreCase(estadoProdCompleto)) { // T --> TRABAJADA A --> PENDIENTE
            FarmaUtility.showMessage(this, "Ya se ingres� la cantidad solicitada del producto", null);
            return false;
        } else
            return true;
    }

    private boolean listaConCantIngresada() {
        boolean flag = false;
        int cant = 0;
        for (int i = 0; i < tblListaProductos.getRowCount(); i++) {
            if (!tblListaProductos.getValueAt(i, 4).toString().trim().equals("")) {
                cant = cant + 1;
            }
        }
        if (cant > 0) {
            flag = true;
        }
        return flag;
    }

    private boolean grabar() {
        boolean retorno = false;
        String numera;
        int count = 0;


        notaEsCabDTO.setValorTotalNotaEsCab(FarmaUtility.sumColumTable(tblListaProductos, 10));
        ArrayList<NotaEsCabDetDTO> lstNotaEsCabDetDTO = new ArrayList<NotaEsCabDetDTO>();
        for (int i = 0; i < tblListaProductos.getRowCount(); i++) {
            if (!tblListaProductos.getValueAt(i, 4).toString().trim().equals("")) {
                NotaEsCabDetDTO notaEsCabDetDTO = new NotaEsCabDetDTO();
                notaEsCabDetDTO.setCodProd(tblListaProductos.getValueAt(i, 1).toString());


                String precio = tblListaProductos.getValueAt(i, 5).toString().trim();
                notaEsCabDetDTO.setPrecVta(precio);
                notaEsCabDetDTO.setValPrecioTotal(tblListaProductos.getValueAt(i, 16).toString());
                notaEsCabDetDTO.setCantMov(new Integer(tblListaProductos.getValueAt(i, 4).toString()));
                notaEsCabDetDTO.setFecVtoProd(FarmaUtility.getValueFieldArrayList(tableModel.data, i,
                                                                                  11).toString().trim());

                log.info("fecha venc producto: " +
                         FarmaUtility.getValueFieldArrayList(tableModel.data, i, 11).toString().trim());

                notaEsCabDetDTO.setNumLoteProd(FarmaUtility.getValueFieldArrayList(tableModel.data, i,
                                                                                   10).toString().trim()); //ASOSA - 26/09/2014
                notaEsCabDetDTO.setValFraccion(new Integer(tblListaProductos.getValueAt(i, 8).toString()));

                notaEsCabDetDTO.setCantExceso(new Integer(tblListaProductos.getValueAt(i, 3).toString()));
                int cantidadUsadaExceso =
                    (int)Integer.parseInt(tblListaProductos.getValueAt(i, 3).toString()) - (int)Integer.parseInt(tblListaProductos.getValueAt(i,
                                                                                                                                              4).toString());
                notaEsCabDetDTO.setCantUsadoExceso(cantidadUsadaExceso);

                notaEsCabDetDTO.setCodigoOc(tblListaProductos.getValueAt(i, 17).toString());
                notaEsCabDetDTO.setSecDetalle(tblListaProductos.getValueAt(i, 18).toString());

                //notaEsCabDetDTO.setSecRespalStock(new Integer(tblListaProductos.getValueAt(i,12).toString()));
                lstNotaEsCabDetDTO.add(notaEsCabDetDTO);
                count = count + 1;
            }
        }
        notaEsCabDTO.setCantItem(count);
        try {
            for (int k = 0; k < tblListaProductos.getRowCount(); k++) {
                if (!tblListaProductos.getValueAt(k, 4).toString().trim().equals("")) {
                    String mensaje =
                        DBInventario.getStockProducto(tblListaProductos.getValueAt(k, 1).toString(), tblListaProductos.getValueAt(k,
                                                                                                                                  4).toString());
                    if (mensaje.trim().equals("FALSE")) {
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.showMessage(this, "El producto  no cuenta con Stock Diponible \n" +
                                tblListaProductos.getValueAt(k, 0).toString() + " - \t" +
                                tblListaProductos.getValueAt(k, 1).toString(), null);
                        retorno = false;
                        return retorno;
                    }
                }
            }

            numera = facadeInventario.grabarDevolucionOC(notaEsCabDTO, lstNotaEsCabDetDTO);
            if (!numera.equals("")) {
                retorno = true;
                FarmaUtility.showMessage(this, "Devoluci�n generada!", tblListaProductos);

                //ERIOS 17.12.2013 Se cargan las variables de impresion
                UtilityInventario.cargaCabecera(this, btnListaProducto, numera);
                VariablesInventario.vNumNotaEs = numera;
                //UtilityInventario.procesoImpresionGuias(this ,tblListaProductos , VariablesInventario.vTipoFormatoImpresion);
                //notaEsCabDTO.getNumNotaEs()
                if (UtilityInventario.reimprimir(this, tblListaProductos, btnListaProducto,
                                                 VariablesInventario.vNumNotaEs)) {
                    facadeInventario.confirmarDevolucionMercaderia(numera);
                }

            }
        } catch (Exception e) {
            log.error("", e);
            FarmaUtility.showMessage(this, e.getMessage(), tblListaProductos);
        }

        return retorno;
    }
}
