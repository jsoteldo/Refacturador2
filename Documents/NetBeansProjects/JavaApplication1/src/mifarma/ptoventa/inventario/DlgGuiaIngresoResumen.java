package mifarma.ptoventa.inventario;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;

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

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaPrintService;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.referencia.FacadeCotizacionPrecio;
import mifarma.ptoventa.inventario.precioCompetencia.reference.UtilityPrecioCompetencia;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgGuiaIngresoResumen extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgGuiaIngresoResumen.class);

    FarmaTableModel tableModelResumenPedido;
    Frame myParentFrame;
    String vEstadoNota;
    public boolean verListaProductos = true;
    public boolean esGuiaRecepcion = false;
    private boolean cabecera = false;    
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabelFunction lblF3 = new JLabelFunction();
    private JScrollPane scrResumenPedido = new JScrollPane();
    private JPanel pnlHeader = new JPanel();
    private JButton btnResumenPedido = new JButton();
    private JTable tblResumenPedido = new JTable();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelHeader pnlHeader1 = new JPanelHeader();
    private JLabel lblFechaGuia = new JLabel();
    private JLabel lblTipoComp_T = new JLabel();
    private JLabel lblFechaGuia_T = new JLabel();
    private JLabel lblTipoComp = new JLabel();
    private JLabel lblNroComp_T = new JLabel();
    private JLabel lblNrocomp = new JLabel();
    private JLabel lblTipoGuia = new JLabel();
    private JLabel lblTipoGuia_T = new JLabel();
    private JLabel lblLocal = new JLabel();
    private JLabel lblLocal_T = new JLabel();
    private JLabelFunction lblF9 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabel lblEstado = new JLabel();
    private JLabelFunction lblF12 = new JLabelFunction();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JLabelWhite lblTotal_T = new JLabelWhite();
    private JLabelWhite lblTotal = new JLabelWhite();
    private JLabelFunction lblF8 = new JLabelFunction();
    private JLabel lblImagen = new JLabel();
    private JLabelWhite lblTipProceso_T = new JLabelWhite();
    private JLabelWhite lblTipProceso = new JLabelWhite();
    
    String tipoCotiza;
    

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgGuiaIngresoResumen() {
        this(null, "", false);
    }

    public DlgGuiaIngresoResumen(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            limpiarDatosCabecera();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    public DlgGuiaIngresoResumen(Frame parent, String title, boolean modal, boolean ver) {
        super(parent, title, modal);
        myParentFrame = parent;
        verListaProductos = ver;
        if (VariablesInventario.vTipoNota.equals(ConstantsPtoVenta.TIP_NOTA_RECEPCION)) {
            esGuiaRecepcion = true;
        }
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
        this.setSize(new Dimension(705, 473));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Guia de Ingreso - Resumen Pedido");
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
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(678, 395));
        lblF5.setText("[ F5 ] Borrar Producto");
        lblF5.setBounds(new Rectangle(165, 375, 140, 20));
        lblF3.setText("[ F3 ] Insertar Producto");
        lblF3.setBounds(new Rectangle(15, 375, 140, 20));
        scrResumenPedido.setBounds(new Rectangle(10, 110, 660, 235));
        scrResumenPedido.setBackground(new Color(255, 130, 14));
        pnlHeader.setBounds(new Rectangle(10, 85, 660, 25));
        pnlHeader.setBackground(new Color(255, 130, 14));
        pnlHeader.setLayout(null);
        btnResumenPedido.setText("Resumen de Pedido");
        btnResumenPedido.setBounds(new Rectangle(10, 0, 140, 25));
        btnResumenPedido.setBackground(new Color(255, 130, 14));
        btnResumenPedido.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnResumenPedido.setBorderPainted(false);
        btnResumenPedido.setContentAreaFilled(false);
        btnResumenPedido.setDefaultCapable(false);
        btnResumenPedido.setFocusPainted(false);
        btnResumenPedido.setFont(new Font("SansSerif", 1, 12));
        btnResumenPedido.setForeground(Color.white);
        btnResumenPedido.setHorizontalAlignment(SwingConstants.LEFT);
        btnResumenPedido.setMnemonic('r');
        btnResumenPedido.setRequestFocusEnabled(false);
        btnResumenPedido.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnResumenPedido_keyPressed(e);
            }
        });
        btnResumenPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnResumenPedido_actionPerformed(e);
            }
        });
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(470, 375, 100, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(575, 405, 85, 20));
        pnlHeader1.setBounds(new Rectangle(10, 10, 660, 75));
        lblFechaGuia.setText("-");
        lblFechaGuia.setBounds(new Rectangle(135, 5, 170, 20));
        lblFechaGuia.setFont(new Font("SansSerif", 1, 11));
        lblFechaGuia.setForeground(Color.white);
        lblTipoComp_T.setText("Tipo Comp:");
        lblTipoComp_T.setBounds(new Rectangle(350, 5, 75, 20));
        lblTipoComp_T.setFont(new Font("SansSerif", 1, 11));
        lblTipoComp_T.setForeground(Color.white);
        lblFechaGuia_T.setText("Fecha Creacion Guia:");
        lblFechaGuia_T.setBounds(new Rectangle(10, 5, 120, 20));
        lblFechaGuia_T.setFont(new Font("SansSerif", 1, 11));
        lblFechaGuia_T.setForeground(Color.white);
        lblTipoComp.setText("-");
        lblTipoComp.setBounds(new Rectangle(430, 5, 220, 20));
        lblTipoComp.setFont(new Font("SansSerif", 1, 11));
        lblTipoComp.setForeground(Color.white);
        lblNroComp_T.setText("Nro. Comp:");
        lblNroComp_T.setBounds(new Rectangle(350, 55, 75, 20));
        lblNroComp_T.setFont(new Font("SansSerif", 1, 11));
        lblNroComp_T.setForeground(Color.white);
        lblNrocomp.setText("-");
        lblNrocomp.setBounds(new Rectangle(430, 55, 215, 20));
        lblNrocomp.setFont(new Font("SansSerif", 1, 11));
        lblNrocomp.setForeground(Color.white);
        lblTipoGuia.setText("-");
        lblTipoGuia.setBounds(new Rectangle(135, 30, 210, 20));
        lblTipoGuia.setFont(new Font("SansSerif", 1, 11));
        lblTipoGuia.setForeground(Color.white);
        lblTipoGuia_T.setText("Tipo Guia Entrada:");
        lblTipoGuia_T.setBounds(new Rectangle(10, 30, 110, 20));
        lblTipoGuia_T.setFont(new Font("SansSerif", 1, 11));
        lblTipoGuia_T.setForeground(Color.white);
        lblLocal.setText("-");
        lblLocal.setBounds(new Rectangle(430, 30, 220, 20));
        lblLocal.setFont(new Font("SansSerif", 1, 11));
        lblLocal.setForeground(Color.white);
        lblLocal_T.setText("Local:");
        lblLocal_T.setBounds(new Rectangle(350, 30, 50, 20));
        lblLocal_T.setFont(new Font("SansSerif", 1, 11));
        lblLocal_T.setForeground(Color.white);
        lblF9.setText("[ F9 ] Datos generales");
        lblF9.setBounds(new Rectangle(315, 375, 145, 20));
        lblF2.setText("[ F2 ] Anular");
        lblF2.setBounds(new Rectangle(15, 405, 85, 20));
        lblEstado.setBounds(new Rectangle(535, 40, 95, 20));
        lblEstado.setFont(new Font("SansSerif", 1, 11));
        lblEstado.setForeground(Color.white);
        lblF12.setText("[F12] Imprimir");
        lblF12.setBounds(new Rectangle(120, 405, 110, 20));
        jPanelTitle1.setBounds(new Rectangle(10, 345, 660, 20));
        lblTotal_T.setText("TOTAL: "+ConstantesUtil.simboloSoles);
        lblTotal_T.setBounds(new Rectangle(480, 0, 70, 20));
        lblTotal.setBounds(new Rectangle(555, 0, 85, 20));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setText("0.00");
        lblF8.setBounds(new Rectangle(430, 405, 135, 20));
        lblF8.setText("[ F8 ] Adjuntar Imagen");
        lblImagen.setBounds(new Rectangle(535, 0, 120, 20));
        lblImagen.setFont(new Font("SansSerif", 1, 11));
        lblImagen.setForeground(SystemColor.window);
        lblTipProceso_T.setText("Tipo Proceso:");
        lblTipProceso_T.setBounds(new Rectangle(10, 55, 105, 15));
        lblTipProceso.setText("-");
        lblTipProceso.setBounds(new Rectangle(135, 55, 200, 15));
        scrResumenPedido.getViewport();
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        pnlHeader1.add(lblTipProceso, null);
        pnlHeader1.add(lblTipProceso_T, null);
        pnlHeader1.add(lblEstado, null);
        pnlHeader1.add(lblLocal_T, null);
        pnlHeader1.add(lblLocal, null);
        pnlHeader1.add(lblTipoGuia_T, null);
        pnlHeader1.add(lblTipoGuia, null);
        pnlHeader1.add(lblNrocomp, null);
        pnlHeader1.add(lblNroComp_T, null);
        pnlHeader1.add(lblTipoComp, null);
        pnlHeader1.add(lblFechaGuia_T, null);

        pnlHeader1.add(lblTipoComp_T, null);
        pnlHeader1.add(lblFechaGuia, null);
        jPanelTitle1.add(lblTotal, null);
        jPanelTitle1.add(lblTotal_T, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(jPanelTitle1, null);
        jContentPane.add(pnlHeader1, null);
        jContentPane.add(lblEsc, null);
        scrResumenPedido.getViewport().add(tblResumenPedido, null);
        jContentPane.add(scrResumenPedido, null);
        pnlHeader.add(lblImagen, null);
        pnlHeader.add(btnResumenPedido, null);
        jContentPane.add(pnlHeader, null);
        jContentPane.add(lblF12, null);
        if (verListaProductos) {
            jContentPane.add(lblF11, null);
            jContentPane.add(lblF5, null);
            jContentPane.add(lblF3, null);
            jContentPane.add(lblF9, null);
        } else if (!esGuiaRecepcion)
            lblF12.setText("[F12] ReImprimir");
            jContentPane.add(lblF2, null);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        initTableResumenPedido();

        if (!verListaProductos){
                cargarDetalle();
                //JSANTIVANEZ 21.10.2010
                if (lblTipoGuia.getText().equalsIgnoreCase("LOCAL")) {
                    lblF2.setVisible(false);

                }
                }
            
        FarmaVariables.vAceptar = false;
        
        //Celso. L. 14012014
        lblF8.setVisible(false);
        //fin.    
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTableResumenPedido() {
        tableModelResumenPedido =
                new FarmaTableModel(ConstantsInventario.columnsListaResumenPedido, ConstantsInventario.defaultListaResumenPedido,
                                    0);
        FarmaUtility.initSimpleList(tblResumenPedido, tableModelResumenPedido,
                                    ConstantsInventario.columnsListaResumenPedido);
    }


    private void cargaListaResumenPedido() {
        tableModelResumenPedido.clearTable();

        if (VariablesInventario.vArrayGuiaIngresoProductos.size() > 0) {
            ArrayList prods = VariablesInventario.vArrayGuiaIngresoProductos;
            for (int i = 0; i < prods.size(); i++) {
                tableModelResumenPedido.insertRow(((ArrayList)prods.get(i)));
            }
            prods = null;
        }
            
        calcularTotal();
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void btnResumenPedido_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(btnResumenPedido);
    }

    private void btnResumenPedido_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(btnResumenPedido);
        //mostrarListadoProductos();
        if (verListaProductos)
            mostrarCabecera();

        try {
            // LAIS REVISAR
            if (!getTipoCotiza().equals("N")) { //proceso de cotizacion
                if (getTipoCotiza().equals(VarCotizacionPrecio.TIP_COTIZA_COMPRAR)) { //si es cotizacion compra
                    lblF2.setVisible(true);
                } else
                    lblF2.setVisible(false);
            }
        } catch (Exception e1) {
            // TODO: Add catch code
           // e1.printStackTrace();
        }

    }

    private void tblResumenPedido_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblResumenPedido, null, 0);
        if (UtilityPtoVenta.verificaVK_F12(e)) {
            if(verListaProductos){
                imprimir();
            }else{
                reimprimir();
            }
            
        } else if (UtilityPtoVenta.verificaVK_F2(e) && !esGuiaRecepcion) {
            funcion_F2();
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
            if (cabecera)
                mostrarListadoProductos();
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            if (verListaProductos) {
                if (tblResumenPedido.getSelectedRow() > -1) {
                    if (JConfirmDialog.rptaConfirmDialog(this,
                                                                                    "Esta seguro de borrar el producto de la guia?"))
                        borrarProducto();
                } else
                    FarmaUtility.showMessage(this, "Debe seleccionar un producto", btnResumenPedido);
            }
        }else if(e.getKeyCode() == KeyEvent.VK_F8){//Celso L. 22/12/2014
            File imagenAdjunta = UtilityPrecioCompetencia.cargarArchivo(this.myParentFrame);
            if(imagenAdjunta.length() <= 512000){//500KB){
                if(imagenAdjunta!=null){
                    VariablesInventario.vFileImagen = imagenAdjunta;
                    lblImagen.setText("Con Imagen Adjunta");
                    //***
                    lblImagen.setForeground(Color.YELLOW);

                    if (!verListaProductos) {//adjunta imagen a una guia por competencia ya existente.
                        if (JConfirmDialog.rptaConfirmDialog(this, "Est� seguro de adjuntar imagen?")) {
                            UtilityPrecioCompetencia.grabarImagenSustentoPrecioCompetencia(VariablesInventario.vNumNota);
                            FarmaUtility.showMessage(this, "Se adjunt� imagen sustento!!", null);
                            lblImagen.setText("Imagen Adjuntada!!");
                            lblImagen.setForeground(Color.YELLOW);
                        }
                    }//****
                }
            }else{
                FarmaUtility.showMessage(this,"No se carg� la imagen, tama�o m�ximo es de 500KB.",null);
            }    
        } else if (e.getKeyCode() == KeyEvent.VK_F9) {
            if (verListaProductos) {
                if (!cabecera)
                    mostrarCabecera();
            }
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (validaDatos() && verListaProductos)
                if (JConfirmDialog.rptaConfirmDialog(this,
                                                                                "Est� seguro de generar la guia de ingreso?")) {
                    if (grabar()) {
                        //Procedera a anular la cotizacion si se dio la opcion
                        //26.11.2007 dubilluz
                        if (VariablesInventario.vIndAnularNota.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                            log.debug("Anulando la cotizacion Nro: " + VariablesInventario.vNumNota_Anular);                            
                            if (anular(VariablesInventario.vNumNota_Anular,FarmaConstants.INDICADOR_N)) {
                                FarmaUtility.aceptarTransaccion();
                                FarmaUtility.showMessage(this, "Guia generada satisfactoriamente!", tblResumenPedido);
                                cerrarVentana(true);
                            }
                        } else {
                            FarmaUtility.aceptarTransaccion();
                            FarmaUtility.showMessage(this, "Guia generada satisfactoriamente!", tblResumenPedido);
                            cerrarVentana(true);
                        }
                    }
                }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (verListaProductos) {
                if (tblResumenPedido.getSelectedRow() > -1) {
                    cargarCabecera();
                    DlgGuiaIngresoCantidad dlgGuiaIngresoCantidad =
                        new DlgGuiaIngresoCantidad(myParentFrame, "", true);
                    dlgGuiaIngresoCantidad.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        actualizarProducto();
                        FarmaVariables.vAceptar = false;
                    }
                } else
                    FarmaUtility.showMessage(this, "Debe seleccionar un producto", btnResumenPedido);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        /**
     * Limpiamos las variables que contienen la informacion de la
     * competencia seleccionada
     * @author dubilluz
     * @since  12.11.2007
     */
        VariablesInventario.vDescripcionCompetencia = "";
        VariablesInventario.vRucCompetencia = "";
        FarmaVariables.vAceptar = pAceptar;
        VariablesInventario.vIndAnularNota = "";
        VariablesInventario.vNumNota = "";
        this.setVisible(false);
        this.dispose();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", btnResumenPedido);
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void mostrarListadoProductos() {
        if (verListaProductos) {
            DlgGuiaIngresoProductos dlgGuiaIngresoProductos = new DlgGuiaIngresoProductos(myParentFrame, "", true);
            dlgGuiaIngresoProductos.setVisible(true);

            if (FarmaVariables.vAceptar) {
                cargaListaResumenPedido();
                FarmaVariables.vAceptar = false;
            }
        }
    }

    private void borrarProducto() {
        String cod = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 0).toString();

        for (int i = 0; i < VariablesInventario.vArrayGuiaIngresoProductos.size(); i++) {
            if (((ArrayList)VariablesInventario.vArrayGuiaIngresoProductos.get(i)).contains(cod)) {
                VariablesInventario.vArrayGuiaIngresoProductos.remove(i);
                break;
            }
        }
        cargaListaResumenPedido();
        tblResumenPedido.repaint();
    }

    private void cargarCabecera() {
        VariablesInventario.vCodProd = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 0).toString();
        VariablesInventario.vNomProd = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 1).toString();
        VariablesInventario.vUnidMed = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 2).toString();
        VariablesInventario.vNomLab = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 3).toString();

        VariablesInventario.vCant = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 4).toString();
        VariablesInventario.vLote = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 7).toString();
        VariablesInventario.vFechaVec = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 8).toString();
        VariablesInventario.vPrecUnit = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 5).toString();

        VariablesInventario.vValFrac_Guia =
                tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 9).toString();
    }

    private void actualizarProducto() {
        ((ArrayList)(VariablesInventario.vArrayGuiaIngresoProductos.get(tblResumenPedido.getSelectedRow()))).set(4, VariablesInventario.vCant);
        ((ArrayList)(VariablesInventario.vArrayGuiaIngresoProductos.get(tblResumenPedido.getSelectedRow()))).set(7, VariablesInventario.vLote);
        ((ArrayList)(VariablesInventario.vArrayGuiaIngresoProductos.get(tblResumenPedido.getSelectedRow()))).set(8, VariablesInventario.vFechaVec);
        ((ArrayList)(VariablesInventario.vArrayGuiaIngresoProductos.get(tblResumenPedido.getSelectedRow()))).set(5, VariablesInventario.vPrecUnit);
        ((ArrayList)(VariablesInventario.vArrayGuiaIngresoProductos.get(tblResumenPedido.getSelectedRow()))).set(6, VariablesInventario.vTotal);

        cargaListaResumenPedido();
    }

    private void mostrarDatos() {
        lblFechaGuia.setText(VariablesInventario.vFecGuia);
        lblTipoComp.setText(VariablesInventario.vDescDoc);
        lblNrocomp.setText(VariablesInventario.vNumDoc);
        lblTipoGuia.setText(VariablesInventario.vNomOrigen);
        lblLocal.setText(VariablesInventario.vDescOrigen);
        lblTipProceso.setText(VariablesInventario.vDescTipProceso);
    }

    private boolean validaDatos() {
        boolean retorno = true;
        if (!FarmaUtility.validaFecha(lblFechaGuia.getText().trim(), "dd/MM/yyyy")) {
            FarmaUtility.showMessage(this, "Debe ingresar los datos de Cabecera.", btnResumenPedido);
            retorno = false;
        } else if (tblResumenPedido.getRowCount() == 0) {
            FarmaUtility.showMessage(this, "No ha ingresado productos a esta guia.", btnResumenPedido);
            retorno = false;
        }

        //clarico  19.12.2014 - alerta si productos necesitan adjuntar imagen.
        VariablesInventario.vMotivoNoImagen="";
        if(VariablesInventario.vTipoOrigen.equals(ConstantsPtoVenta.LISTA_MAESTRO_COMPETENCIA) && Integer.parseInt(VariablesInventario.vCodTipProceso)==ConstantsPtoVenta.TIPO_PROC_COT_PROD){  
            String ProductosAdjuntarImagen = UtilityPrecioCompetencia.existeProductosPorCotizar(tblResumenPedido);
            if (ProductosAdjuntarImagen != null &&  
                ProductosAdjuntarImagen.length() > 0 &&
                lblImagen.getText().length() == 0  ){
                if (JConfirmDialog.rptaConfirmDialog(this, "Productos "+ProductosAdjuntarImagen+" necesitan sustento. Desea Adjuntar imagen del Comprobante?")) {
                  //si es S�, abre ventana para adjuntar imagen
                  File imagenAdjunta = UtilityPrecioCompetencia.cargarArchivo(this.myParentFrame);
                  if(imagenAdjunta.length() <= 512000){//500KB){
                      if(imagenAdjunta!=null){
                            VariablesInventario.vFileImagen = imagenAdjunta;
                          lblImagen.setText("Con Imagen Adjunta");
                      }
                  }else{
                        FarmaUtility.showMessage(this,"No se carg� la imagen, tama�o m�ximo es de 500KB.",null);
                  }    
                }else{
                  //sino, deja grabar
                  String input = "";
                }
            }
        }

        return retorno;
    }

    private boolean grabar() {
        boolean retorno;
        try {
            /*
            VariablesInventario.vCodTipProceso
            VariablesInventario.vDescTipProceso
            VariablesInventario.vFecGuia
            VariablesInventario.vTipoDoc
            VariablesInventario.vDescDoc
            VariablesInventario.vNumDoc
            
            VariablesInventario.vTipoOrigen
            VariablesInventario.vNomOrigen
            ------------
            SI(VariablesInventario.vTipoOrigen.equals(ConstantsPtoVenta.LISTA_MAESTRO_COMPETENCIA))//=04
            VariablesInventario.vCodOrigen = FarmaVariables.vCodLocal;
            VariablesInventario.vDescOrigen = txtNombre.getText().trim();
            VariablesInventario.vTipoMotivoKardex = ConstantsPtoVenta.MOT_KARDEX_COTIZACION_COMPETENCIA;
            SINO
            VariablesInventario.vCodOrigen = txtCodigo.getText();
            VariablesInventario.vDescOrigen = txtDescripcion.getText();
            ------------
            
            VariablesInventario.vNombreTienda
            VariablesInventario.vCiudadTienda
            VariablesInventario.vRucTienda
            */
            String numera =
                DBInventario.agregarCabeceraGuiaIngreso(VariablesInventario.vFecGuia,       //VariablesInventario.vFecGuia
                    VariablesInventario.vTipoDoc,       //VariablesInventario.vTipoDoc
                    VariablesInventario.vNumDoc,        //VariablesInventario.vNumDoc
                    VariablesInventario.vTipoOrigen,    //VariablesInventario.vTipoOrigen
                    VariablesInventario.vCodOrigen,     //VariablesInventario.vCodOrigen
                                                        tblResumenPedido.getRowCount() + "",
                                                        FarmaUtility.sumColumTable(tblResumenPedido, 6) + "", VariablesInventario.vNombreTienda,  //VariablesInventario.vNombreTienda
                    VariablesInventario.vCiudadTienda,  //ariablesInventario.vCiudadTienda
                    VariablesInventario.vRucTienda,    //VariablesInventario.vRucTienda
                    VariablesInventario.vCodTipProceso);//VariablesInventario.vCodTipProceso
            //log.debug(numera);
            for (int i = 0; i < tblResumenPedido.getRowCount(); i++) {
                DBInventario.agregarDetalleGuiaIngreso(numera, VariablesInventario.vTipoOrigen,
                                                       tblResumenPedido.getValueAt(i, 0).toString(),
                                                       tblResumenPedido.getValueAt(i, 5).toString(),
                                                       tblResumenPedido.getValueAt(i, 6).toString(),
                                                       tblResumenPedido.getValueAt(i, 4).toString(), VariablesInventario.vFecGuia,
                                                       tblResumenPedido.getValueAt(i, 8).toString(),
                                                       tblResumenPedido.getValueAt(i, 7).toString(),
                                                       tblResumenPedido.getValueAt(i, 9).toString());
            }
            //FarmaUtility.aceptarTransaccion();
            //FarmaUtility.liberarTransaccion();
            
            //clarico  19.12.2014 - ejecuci�n por hilo para insercion de compra competencia a tablas de Gestion de Precios.
            VariablesInventario.vNumNotaCompetencia = numera;
            VariablesInventario.vTipoDocCotizado = VariablesInventario.vTipoDoc;
            VariablesInventario.vNumDocCotizado = VariablesInventario.vNumDoc;
            VariablesInventario.vFecGuiaCotizado = VariablesInventario.vFecGuia;
            if(VariablesInventario.vTipoOrigen.equals(ConstantsPtoVenta.LISTA_MAESTRO_COMPETENCIA) && Integer.parseInt(VariablesInventario.vCodTipProceso)== ConstantsPtoVenta.TIPO_PROC_COT_PROD ){  
              new Thread(){
                  public void run(){  
                    UtilityPrecioCompetencia.grabarCompraPrecioCompetenciaDAO(tblResumenPedido, VariablesInventario.vNumNotaCompetencia);
                  }
              }.start();
            
              //  UtilityPrecioCompetencia.grabarCompraPrecioCompetenciaDAO(tblResumenPedido,VariablesInventario.vNumNotaCompetencia);

            }
            
            limpiarDatosCabecera();
            retorno = true;
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ha ocurrido un error al grabar los datos del pedido : \n" +
                    sql.getMessage(), btnResumenPedido);
            retorno = false;
        } catch (Exception ex) {
            FarmaUtility.liberarTransaccion();
            log.error("", ex);
            FarmaUtility.showMessage(this, "Error en la aplicacion al grabar los datos del pedido : \n" +
                    ex.getMessage(), btnResumenPedido);
            retorno = false;
        }
        return retorno;
    }

    private void cargarDetalle() {
        try {
            ArrayList array = new ArrayList();
            array = DBInventario.cargaCabeceraGuiaIngreso(VariablesInventario.vNumNota, VariablesInventario.vNumDoc);
            array = (ArrayList)array.get(0);
            lblFechaGuia.setText(array.get(0).toString());
            lblTipoComp.setText(array.get(1).toString());
            lblNrocomp.setText(array.get(2).toString());
            lblTipoGuia.setText(array.get(3).toString());
            lblLocal.setText(array.get(4).toString());
            vEstadoNota = array.get(5).toString();
            lblTipProceso.setText(array.get(6).toString());
            if (vEstadoNota.equals(FarmaConstants.INDICADOR_N))
                lblEstado.setText("ANULADO");
            //log.debug("ESTADO:"+vEstadoNota);
            DBInventario.cargaDetalleGuiaIngreso(VariablesInventario.vNumNota, VariablesInventario.vNumDoc, VariablesInventario.vTipoNota);
            cargaListaResumenPedido();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al cargar los datos de la gu�a : \n" +
                    sql.getMessage(), null);
        }
      
    }

    /**
     * Modificado para enviale un parametro de numero de cotizacion
     * @author dubilluz
     * @since  26.11.2007
     */
    private boolean anular(String pNumNota,String indicador) {
        boolean retorno;
        try {
            DBInventario.anularGuiaIngreso(pNumNota,indicador);
            //FarmaUtility.aceptarTransaccion();
            
            //clarico  19.12.2014 - ejecuci�n por hilo para anulacion de compra competencia a tablas de Gestion de Precios.
            VariablesInventario.vNumNotaCompetencia = pNumNota;  
          /*  if(lblTipoGuia.getText().equalsIgnoreCase("COMPETENCIA")){  
                //no se envia la anulacion al ADM, lo solicito Miguel Sanchez
                /*
              new Thread(){
                  public void run(){  
                    UtilityPrecioCompetencia.anularCompraPrecioCompetenciaDAO(VariablesInventario.vNumNotaCompetencia);
                  }
              }.start();
            }*/
            
            retorno = true;
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            //log.debug(e.getMessage().substring(11,e.getMessage().indexOf("ORA-06512")-1));
            if (sql.getErrorCode() == 20001)
                FarmaUtility.showMessage(this, "No existe Stock Disponible para anular esta Guia.", btnResumenPedido);
            else if (sql.getErrorCode() == 20002)
                FarmaUtility.showMessage(this, "La Fracci�n Actual no permite anular esta Guia.\n" +
                        sql, btnResumenPedido);
            //INICIO RPASCACIO 01-06-2017    
            else if (sql.getErrorCode() == 20003)
                FarmaUtility.showMessage(this, "Cantidades diferentes en movimientos de productos.\n"+ sql, btnResumenPedido);
            //FIN RPASCACIO
            else {
                log.error("", sql);
                FarmaUtility.showMessage(this, "Ha ocurrido un error al anular.\n" +
                        sql.getMessage(), btnResumenPedido);
            }
            retorno = false;
        } catch (Exception ex) {
            FarmaUtility.liberarTransaccion();
            log.error("", ex);
            FarmaUtility.showMessage(this, "Error en la aplicacion al grabar el pedido : \n" +
                    ex, btnResumenPedido);
            retorno = false;
        }
        return retorno;
    }

    private void limpiarDatosCabecera() {
        VariablesInventario.vFecGuia = "";
        VariablesInventario.vTipoDoc = "";
        VariablesInventario.vDescDoc = "";
        VariablesInventario.vNumDoc = "";
        VariablesInventario.vTipoOrigen = "";
        VariablesInventario.vNomOrigen = "";
        VariablesInventario.vCodOrigen = "";
        VariablesInventario.vDescOrigen = "";
    }

    private void imprimir() {

        if (tblResumenPedido.getRowCount() == 0)
            return;
        if (!JConfirmDialog.rptaConfirmDialog(this, "Seguro que desea imprimir?"))
            return;
        //FarmaPrintService vPrint = new FarmaPrintService(45, FarmaVariables.vImprReporte, true);
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresi�n", btnResumenPedido);
            return;
        }
        try {
            log.debug("IMPRIMIENDO");
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printBold(FarmaPRNUtility.llenarBlancos(27) + " REPORTE GUIA DE INGRESO - RESUMEN DE PEDIDO ",
                             true);
            vPrint.printBold(" ", true);
            vPrint.printBold("Fecha Guia       : " + lblFechaGuia.getText().trim(), true);
            vPrint.printBold("Tipo de Guia     : " + lblTipoGuia.getText().trim(), true);
            vPrint.printBold("Tipo Comprobante : " + lblTipoComp.getText().trim(), true);
            vPrint.printBold("Nro. Comprobante : " + lblNrocomp.getText().trim(), true);
            vPrint.printBold("Local            : " + lblLocal.getText().trim(), true);
            vPrint.printBold(" ", true);
            vPrint.printLine("===================================================================================================",
                             true);
            vPrint.printBold("Codigo Descripcion              Unidad       Laboratorio            Cantidad   Pre.U.    Total",
                             true);
            vPrint.printLine("===================================================================================================",
                             true);
            vPrint.deactivateCondensed();
            vPrint.setEndHeader();

            for (int i = 0; i < tblResumenPedido.getRowCount(); i++) {

                vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 0), 6) +
                                      " " +
                                      FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 1), 24) +
                                      " " +
                                      FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 2), 12) +
                                      " " +
                                      FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 3), 22) +
                                      " " +
                                      FarmaPRNUtility.alinearDerecha((String)tblResumenPedido.getValueAt(i, 4), 8) +
                                      " " +
                                      FarmaPRNUtility.alinearDerecha((String)tblResumenPedido.getValueAt(i, 5), 8) +
                                      " " +
                                      FarmaPRNUtility.alinearDerecha((String)tblResumenPedido.getValueAt(i, 6), 8) +
                                      " ", true);
            }
            vPrint.activateCondensed();
            vPrint.printLine("===========================================================================================================================",
                             true);
            vPrint.printBold("Registros Impresos: " +
                             FarmaUtility.formatNumber(tblResumenPedido.getRowCount(), ",##0") +
                             FarmaPRNUtility.llenarBlancos(11), true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al imprimir : \n" +
                    sql.getMessage(), btnResumenPedido);
        }

    }
    
    private void reimprimir() {
        FarmaPrintService vPrint = new FarmaPrintService(66, FarmaVariables.vImprReporte, true);
        if (!vPrint.startPrintService()) {
            FarmaUtility.showMessage(this, "No se pudo inicializar el proceso de impresi�n para la copia N�2", btnResumenPedido);
            return;
        }
        try {
            log.debug("REALIZANDO REIMPRESION");
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printBold(FarmaPRNUtility.llenarBlancos(27) + " REPORTE GUIA DE INGRESO - RESUMEN DE PEDIDO [COPIA IMPRESA] ",
                             true);
            vPrint.printBold(" ", true);
            vPrint.printBold("Fecha Guia       : " + lblFechaGuia.getText().trim(), true);
            vPrint.printBold("Tipo de Guia     : " + lblTipoGuia.getText().trim(), true);
            vPrint.printBold("Tipo Comprobante : " + lblTipoComp.getText().trim(), true);
            vPrint.printBold("Nro. Comprobante : " + lblNrocomp.getText().trim(), true);
            vPrint.printBold("Local            : " + lblLocal.getText().trim(), true);
            vPrint.printBold(" ", true);
            vPrint.printLine("===================================================================================================",
                             true);
            vPrint.printBold("Codigo Descripcion              Unidad       Laboratorio            Cantidad   Pre.U.    Total",
                             true);
            vPrint.printLine("===================================================================================================",
                             true);
            vPrint.deactivateCondensed();
            vPrint.setEndHeader();

            for (int i = 0; i < tblResumenPedido.getRowCount(); i++) {

                vPrint.printCondensed(FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 0), 6) +
                                      " " +
                                      FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 1), 24) +
                                      " " +
                                      FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 2), 12) +
                                      " " +
                                      FarmaPRNUtility.alinearIzquierda((String)tblResumenPedido.getValueAt(i, 3), 22) +
                                      " " +
                                      FarmaPRNUtility.alinearDerecha((String)tblResumenPedido.getValueAt(i, 4), 8) +
                                      " " +
                                      FarmaPRNUtility.alinearDerecha((String)tblResumenPedido.getValueAt(i, 5), 8) +
                                      " " +
                                      FarmaPRNUtility.alinearDerecha((String)tblResumenPedido.getValueAt(i, 6), 8) +
                                      " ", true);
            }
            vPrint.activateCondensed();
            vPrint.printLine("===========================================================================================================================",
                             true);
            vPrint.printBold("Registros Impresos: " +
                             FarmaUtility.formatNumber(tblResumenPedido.getRowCount(), ",##0") +
                             FarmaPRNUtility.llenarBlancos(11), true); 
            vPrint.printBold(" ", true);
            vPrint.printBold("COPIA IMPRESA", true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al imprimir 2da copia : \n" +
                    sql.getMessage(), btnResumenPedido);
        }
    }

    private void mostrarCabecera() {
        DlgGuiaIngresoCabecera dlgGuiaIngresoCabecera = new DlgGuiaIngresoCabecera(myParentFrame, "", true);
        dlgGuiaIngresoCabecera.setVisible(true);
        if (FarmaVariables.vAceptar) {
            //LTAVARA 2016.11.30 identifica si cargo la imagen
            if(VariablesInventario.vFileImagen!=null){
                lblImagen.setText("Con Imagen Adjunta");
                lblImagen.setForeground(Color.YELLOW);
                
                }

            mostrarDatos();
            cabecera = true;
            mostrarListadoProductos();
            FarmaVariables.vAceptar = false;
        }
    }

    private boolean verificaComprobante() {
        boolean retorno;
        if (!vEstadoNota.equals(FarmaConstants.INDICADOR_N)) {
            try {
                String estadoProcesado =
                    DBInventario.getEstadoProcesoSap(VariablesInventario.vNumNota, ConstantsPtoVenta.TIP_NOTA_INGRESO);
                String estadoProcCierreDia =
                    DBInventario.getEstadoProcesoCierreDia(VariablesInventario.vNumNota, ConstantsPtoVenta.TIP_NOTA_INGRESO);
                if (estadoProcesado.equals(FarmaConstants.INDICADOR_S)) {
                    if (VariablesInventario.vTipoNota.equals(ConstantsInventario.TIP_NOTA_GUIA) && VariablesInventario.vTipoNotaOrigen.equals(ConstantsInventario.TIP_NOTA_ORIGEN_GUIA)) {
                        if (cargaLoginOper())
                            retorno = true;
                        else {
                            retorno = false;
                            FarmaUtility.showMessage(this,
                                                     "Esta Gu�a ha sido procesado por la interface SAP.\nSolo un Operador de Sistemas puede anularla.",
                                                     btnResumenPedido);
                        }
                    } else {
                        retorno = false;
                        FarmaUtility.showMessage(this,
                                                 "Esta Gu�a ha sido procesado por la interface SAP. No puede ser anulada.",
                                                 btnResumenPedido);
                    }
                } else if (estadoProcCierreDia.equals(FarmaConstants.INDICADOR_S)) {
                    retorno = false;
                    FarmaUtility.showMessage(this,
                                             "Esta Gu�a ha sido registrado en un Cierre de D�a. No puede ser anulada.",
                                             btnResumenPedido);
                } else {
                    //2017.07.21  validar si tiene solicitud
                    String codSolicitud=DBInventario.getSolicitudPorCotizacion(VariablesInventario.vNumNota);
                    if(!codSolicitud.equals("N") && getTipoCotiza().equals(VarCotizacionPrecio.TIP_COTIZA_COMPRAR)){
                    FacadeCotizacionPrecio facadeCotizacionPrecio= new FacadeCotizacionPrecio();
                        if( facadeCotizacionPrecio.validarAutorizacionAnular(FarmaVariables.vCodGrupoCia,
                                                                             FarmaVariables.vCodLocal, VariablesInventario.vNumNota,codSolicitud)){
                            retorno = true;
                        }else{
                            retorno = false;
                            FarmaUtility.showMessage(this,
                                                     "Necesita autorizaci�n para proceder con la anulaci�n.",
                                                     btnResumenPedido);
                        }
                       
                    
                    }else  if(!codSolicitud.equals("N") && !getTipoCotiza().equals(VarCotizacionPrecio.TIP_COTIZA_COMPRAR)){
                        retorno = false;
                        FarmaUtility.showMessage(this,
                                                 "No procede la anulacion porque no es una Cotizaci�n por Compra.",
                                                 btnResumenPedido);
                    } else{
                    retorno = true;
                    }
                }
            } catch (SQLException e) {
                retorno = false;
                log.error("", e);
                FarmaUtility.showMessage(this, "Ha ocurrido un error al consultar estado.\n" +
                        e, btnResumenPedido);
            }
        } else {
            retorno = false;
            FarmaUtility.showMessage(this, "Esta Guia Ingreso ya esta anulada.", btnResumenPedido);
        }
        return retorno;
    }

    private void calcularTotal() {
        double total = 0.00;
        int cantidad = tblResumenPedido.getRowCount();

        if (cantidad > 0) {
            for (int i = 0; i < cantidad; i++) {
                total += FarmaUtility.getDecimalNumber(tblResumenPedido.getValueAt(i, 6).toString().trim());
            }
        }

        lblTotal.setText(FarmaUtility.formatNumber(total, 2));
    }

    private boolean cargaLoginOper() {
        String numsec = FarmaVariables.vNuSecUsu;
        String idusu = FarmaVariables.vIdUsu;
        String nomusu = FarmaVariables.vNomUsu;
        String apepatusu = FarmaVariables.vPatUsu;
        String apematusu = FarmaVariables.vMatUsu;

        try {
            DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
            dlgLogin.setRolUsuario(FarmaConstants.ROL_OPERADOR_SISTEMAS);
            dlgLogin.setVisible(true);
            FarmaVariables.vNuSecUsu = numsec;
            FarmaVariables.vIdUsu = idusu;
            FarmaVariables.vNomUsu = nomusu;
            FarmaVariables.vPatUsu = apepatusu;
            FarmaVariables.vMatUsu = apematusu;
        } catch (Exception e) {
            FarmaVariables.vNuSecUsu = numsec;
            FarmaVariables.vIdUsu = idusu;
            FarmaVariables.vNomUsu = nomusu;
            FarmaVariables.vPatUsu = apepatusu;
            FarmaVariables.vMatUsu = apematusu;
            FarmaVariables.vAceptar = false;
            log.error("", e);
            FarmaUtility.showMessage(this, "Ocurrio un error al validar rol de usuariario \n : " + e.getMessage(),
                                     null);
        }
        return FarmaVariables.vAceptar;
    }

    private void funcion_F2() {
        if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_SUPERVISOR_VENTAS)) {
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, btnResumenPedido);
            else if (!verListaProductos) {
                //JSANTIVANEZ 21.10.2010
                if (!lblTipoGuia.getText().equalsIgnoreCase("LOCAL")) {

                    if (verificaComprobante()) {
                        if (JConfirmDialog.rptaConfirmDialog(this,
                                                                                        "�Est� seguro de anular la Guia Ingreso?")) {
                            if (anular(VariablesInventario.vNumNota,FarmaConstants.INDICADOR_S)) {
                                
                                String codSolicitud;
                                try {
                                    codSolicitud = DBInventario.getSolicitudPorCotizacion(VariablesInventario.vNumNota);
                                    if(!codSolicitud.equals("N") ){
                                        FacadeCotizacionPrecio facadeCotizacionPrecio= new FacadeCotizacionPrecio();
                                            facadeCotizacionPrecio.anularCotizacion(FarmaVariables.vCodGrupoCia,
                                                                             FarmaVariables.vCodLocal, VariablesInventario.vNumNota,codSolicitud);
                                        }
                                } catch (SQLException e) {
                                    log.error("", e);
                                }
                            
                                
                                FarmaUtility.aceptarTransaccion();
                                FarmaUtility.showMessage(this, "Gu�a Ingreso fue anulada.", btnResumenPedido);
                                cerrarVentana(true);
                            }
                        }
                        
                    }
                }

            }
        } else {
            FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opci�n", null);
        }
    }


    public void setTipoCotiza(String tipoCotiza) {
        this.tipoCotiza = tipoCotiza;
    }

    public String getTipoCotiza() {
        return tipoCotiza;
    }
}
