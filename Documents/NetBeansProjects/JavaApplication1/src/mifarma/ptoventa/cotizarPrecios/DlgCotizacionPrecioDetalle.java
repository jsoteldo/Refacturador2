package mifarma.ptoventa.cotizarPrecios;


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

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.cnx.FarmaVentaCnxUtility;
import mifarma.ptoventa.controlAsistencia.reference.UtilityControlAsistencia;
import mifarma.ptoventa.controlAsistencia.util.UtilFtp;
import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.referencia.ConstantesCotiza;
import mifarma.ptoventa.cotizarPrecios.referencia.FacadeCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.referencia.UtilCotizacion;
import mifarma.ptoventa.inventario.DlgGuiaIngresoProductos;
import mifarma.ptoventa.inventario.precioCompetencia.reference.UtilityPrecioCompetencia;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.BeanConexion;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.UtilityVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCotizacionPrecioDetalle extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgCotizacionPrecioDetalle.class);

    FarmaTableModel tableModelResumenPedido;
    Frame myParentFrame;
    String vEstadoNota;
    public boolean verListaProductos = true;
    public boolean esGuiaRecepcion = false;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
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
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JLabel lblEstado = new JLabel();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JLabelWhite lblTotal_T = new JLabelWhite();
    private JLabelWhite lblTotal = new JLabelWhite();
    private JLabel lblImagen = new JLabel();
    private JLabelWhite lblTipProceso_T = new JLabelWhite();
    private JLabelWhite lblTipProceso = new JLabelWhite();
    private JPanelHeader pnlHeader2 = new JPanelHeader();
    private JLabel lblAvisoHeader = new JLabel();
  //  private int tipoProceso = 0;
    private String fechaCreacion = "";
    private String tipoProceso = "";
    private String tipoComprobante = "";
    private String nroComprobante = "";
    private String nroSolicitud = "";
    private String nombreTienda = "";
    private String nroRUC = "";
    private String dirFtpRemoto, usrFtp, pwdFtp, servidorFtp;
    private int puertoFtp;
    private static BeanConexion conexionFtp;
    
    private JLabel lblAvisoHeader2 = new JLabel();
    private JLabel lblAvisoPie = new JLabel();
    private JPanel pnlMensajePie = new JPanel();
    
    private JLabelFunction lblF3 = new JLabelFunction();

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */
    public DlgCotizacionPrecioDetalle() {
        this(null, "", false, "0");
    }

    public DlgCotizacionPrecioDetalle(Frame parent, String title, boolean modal, String tipoProceso) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.tipoProceso = tipoProceso;
        
        try {
            jbInit();
            initialize();
            //limpiarDatosCabecera();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(831, 455));
        this.getContentPane().setLayout(borderLayout1);
        String descripcionProceso="";
        if(this.getTipoProceso().equals("1")){
                descripcionProceso="Comprar";
            }
        else if(this.getTipoProceso().equals("2")){
                descripcionProceso="Cotizar";
            }
        else if(this.getTipoProceso().equals("3")){
        descripcionProceso="Sin Solicitud";
        }
        this.setTitle("Cotización Por Competenacia - Detalle - "+descripcionProceso);
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
        scrResumenPedido.setBounds(new Rectangle(10, 110, 805, 235));
        scrResumenPedido.setBackground(new Color(255, 130, 14));
        pnlHeader.setBounds(new Rectangle(10, 85, 805, 25));
        pnlHeader.setBackground(new Color(255, 130, 14));
        pnlHeader.setLayout(null);
        btnResumenPedido.setText("Resumen de Cotización");
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
        lblF11.setBounds(new Rectangle(625, 400, 100, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(730, 400, 85, 20));
        pnlHeader1.setBounds(new Rectangle(10, 30, 805, 55));
        pnlHeader2.setBounds(new Rectangle(10, 5, 805, 25));
        pnlHeader2.setBackground(new Color(231, 0, 0));
        lblFechaGuia.setText("-");
        lblFechaGuia.setBounds(new Rectangle(110, 30, 170, 20));
        lblFechaGuia.setFont(new Font("SansSerif", 1, 11));
        lblFechaGuia.setForeground(Color.white);
        lblTipoComp_T.setText("Tipo Comp:");
        lblTipoComp_T.setBounds(new Rectangle(350, 5, 75, 20));
        lblTipoComp_T.setFont(new Font("SansSerif", 1, 11));
        lblTipoComp_T.setForeground(Color.white);
        lblFechaGuia_T.setText("Fecha Cotización:");
        lblFechaGuia_T.setBounds(new Rectangle(10, 30, 95, 20));
        lblFechaGuia_T.setFont(new Font("SansSerif", 1, 11));
        lblFechaGuia_T.setForeground(Color.white);
        
        lblAvisoHeader.setText("-");
        lblAvisoHeader.setBounds(new Rectangle(5, 4, 560, 20));
        lblAvisoHeader.setFont(new Font("SansSerif", 1, 11));
        lblAvisoHeader.setForeground(Color.white);
        
        lblAvisoHeader2.setText("-");
        lblAvisoHeader2.setBounds(new Rectangle(600, 4, 560, 20));
        lblAvisoHeader2.setFont(new Font("SansSerif", 1, 15));
        lblAvisoHeader2.setForeground(Color.white);
        
        tblResumenPedido.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblResumenPedido_keyPressed(e);
                }
            });
        
        
        lblTipoComp.setText("-");
        lblTipoComp.setBounds(new Rectangle(430, 5, 220, 20));
        lblTipoComp.setFont(new Font("SansSerif", 1, 11));
        lblTipoComp.setForeground(Color.white);
        lblNroComp_T.setText("Nro. Comp:");
        lblNroComp_T.setBounds(new Rectangle(350, 30, 75, 20));
        lblNroComp_T.setFont(new Font("SansSerif", 1, 11));
        lblNroComp_T.setForeground(Color.white);
        lblNrocomp.setText("-");
        lblNrocomp.setBounds(new Rectangle(430, 30, 215, 20));
        lblNrocomp.setFont(new Font("SansSerif", 1, 11));
        lblNrocomp.setForeground(Color.white);
        lblF2.setText("[ F2 ] Ingresar Documento");
        lblF2.setBounds(new Rectangle(300, 400, 150, 20));
        lblF1.setText("[ F1 ] Ver Info Producto");
        lblF1.setBounds(new Rectangle(155, 400, 135, 20));
        lblEnter.setText("[ ENTER ] Ingresar Cantidad");
        lblEnter.setBounds(new Rectangle(460, 400, 160, 20));
        lblEstado.setBounds(new Rectangle(535, 40, 95, 20));
        lblEstado.setFont(new Font("SansSerif", 1, 11));
        lblEstado.setForeground(Color.white);
        jPanelTitle1.setBounds(new Rectangle(10, 345, 805, 20));
        lblAvisoPie.setText("¡ATENCI\u00d3N! Los productos de color rojo no cumplen con la unidad de venta permitida en su local");
        lblAvisoPie.setBounds(new Rectangle(5, 0, 565, 25));
        lblAvisoPie.setFont(new Font("SansSerif", 3, 12));
        lblAvisoPie.setForeground(SystemColor.window);
        lblAvisoPie.setVerticalTextPosition(SwingConstants.TOP);
        lblAvisoPie.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlMensajePie.setBounds(new Rectangle(10, 365, 805, 25));
        pnlMensajePie.setBackground(Color.red);
        pnlMensajePie.setLayout(null);
        lblTotal_T.setText("TOTAL: "+ConstantesUtil.simboloSoles);
        lblTotal_T.setBounds(new Rectangle(635, 0, 70, 20));
        lblTotal.setBounds(new Rectangle(710, 0, 85, 20));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setText("0.00");
        lblImagen.setBounds(new Rectangle(675, 0, 120, 20));
        lblImagen.setFont(new Font("SansSerif", 1, 11));
        lblImagen.setForeground(SystemColor.window);
        lblTipProceso_T.setText("Tipo Proceso:");
        lblTipProceso_T.setBounds(new Rectangle(10, 10, 100, 15));
        lblTipProceso.setText("-");
        lblTipProceso.setBounds(new Rectangle(110, 10, 185, 15));
        
        lblF3.setText("[ F3 ] Insertar Producto");
        lblF3.setBounds(new Rectangle(10, 400, 135, 20));
        lblF3.setVisible(false);
        scrResumenPedido.getViewport();
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        pnlHeader1.add(lblTipProceso, null);
        pnlHeader1.add(lblTipProceso_T, null);
        pnlHeader1.add(lblEstado, null);
        pnlHeader1.add(lblNrocomp, null);

        pnlHeader1.add(lblNroComp_T, null);
        pnlHeader1.add(lblTipoComp, null);
        pnlHeader1.add(lblFechaGuia_T, null);
        pnlHeader1.add(lblTipoComp_T, null);

        pnlHeader1.add(lblFechaGuia, null);
        jPanelTitle1.add(lblTotal, null);
        jPanelTitle1.add(lblTotal_T, null);
        pnlMensajePie.add(lblAvisoPie, null);
        jContentPane.add(pnlMensajePie, null);
        jContentPane.add(jPanelTitle1, null);
        jContentPane.add(pnlHeader1, null);
        pnlHeader2.add(lblAvisoHeader, null);
        pnlHeader2.add(lblAvisoHeader2, null);
        jContentPane.add(pnlHeader2, null);
        jContentPane.add(lblEsc, null);
        scrResumenPedido.getViewport().add(tblResumenPedido, null);
        jContentPane.add(scrResumenPedido, null);
        pnlHeader.add(lblImagen, null);
        pnlHeader.add(btnResumenPedido, null);
        jContentPane.add(pnlHeader, null);
        jContentPane.add(lblF1, null);
        jContentPane.add(lblF2, null);
        jContentPane.add(lblEnter, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(lblF3, null);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */
    private void initialize() {
        initCampos();
        initTableResumenPedido();
        VariablesInventario.vTipoMotivoKardex = ConstantsPtoVenta.MOT_KARDEX_COTIZACION_COMPETENCIA;
        FarmaVariables.vAceptar = false;
    }
    
    private void initCampos() {
        String cadena = (this.tipoProceso.equals("1")) ? " " : " NO ";
        lblAvisoHeader.setText("¡ATENCIÓN!, ESTOS PRODUCTOS" + cadena + "MOVERÁN KÁRDEX");
        lblAvisoHeader2.setText("¡" + cadena + "MOVERÁN KÁRDEX!");
        
        if(this.getTipoProceso().equals("3")){
        lblF3.setVisible(true);
            }
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */
    private void initTableResumenPedido() {
        tableModelResumenPedido =
                new FarmaTableModel(ConstantesCotiza.columnsListaResumenCotizacion, ConstantesCotiza.defaultListaResumenCotizacion,
                                    0);
        FarmaUtility.initSimpleList(tblResumenPedido, tableModelResumenPedido, ConstantesCotiza.columnsListaResumenCotizacion);
        
        tblResumenPedido.getSelectionModel().setSelectionInterval (0, 0);
        
    }

    private void cargaListaResumenPedido() {
        tableModelResumenPedido.clearTable();

        if (VarCotizacionPrecio.vIC_ArrayProductosCotizacion.size() > 0) {
            ArrayList prods = VarCotizacionPrecio.vIC_ArrayProductosCotizacion;
            for (int i = 0; i < prods.size(); i++) {
                tableModelResumenPedido.insertRow(((ArrayList)prods.get(i)));
            }
            prods = null;
        }
        
        calcularTotal();
        
        tblResumenPedido.getSelectionModel().setSelectionInterval (0, 0);
        
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
        String descripcionProceso="";
        if(this.getTipoProceso().equals("1")){
                descripcionProceso="Comprar";
            }
        else if(this.getTipoProceso().equals("2")){
                descripcionProceso="Cotizar";
            }
        else if(this.getTipoProceso().equals("3")){
        descripcionProceso="Sin Solicitud";
        }
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(btnResumenPedido);
        //mostrarListadoProductos();
        
        lblFechaGuia.setText(this.fechaCreacion);

        lblTipProceso.setText(descripcionProceso);   
        lblTipoComp.setText(VarCotizacionPrecio.vDesc_Doc);
        lblNrocomp.setText(this.nroComprobante);
        
        UtilCotizacion.cargaTabla_ProductosCotizacion(tableModelResumenPedido, this.nroSolicitud);
        VarCotizacionPrecio.vIC_ArrayProductosCotizacion = tableModelResumenPedido.data;
        pintarProds_NoValidos();
    }

    private void tblResumenPedido_keyPressed(KeyEvent e) {
        /*System.err.println(" 0 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 0).toString());//num doc
        System.err.println(" 1 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 1).toString());//tip doc
        System.err.println(" 2 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 2).toString());//Fec doc
        System.err.println(" 3 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 3).toString());//num doc
        System.err.println(" 4 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 4).toString());//tip doc
        System.err.println(" 5 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 5).toString());//Fec doc
        System.err.println(" 6 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 6).toString());//num doc
        System.err.println(" 7 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 7).toString());//tip doc
        System.err.println(" 8 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 8).toString());//Fec doc
        System.err.println(" 9 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 9).toString());//num doc
        System.err.println(" 10 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 10).toString());//tip doc
        System.err.println(" 11 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 11).toString());//Fec doc
        System.err.println(" 12 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 12).toString());//num doc
        System.err.println(" 13 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 13).toString());//tip doc
        System.err.println(" 14 "+tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 14).toString());//Fec doc*/
        chkKeyPressed(e);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */
    private void chkKeyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblResumenPedido, null, 0);
        
        if (UtilityPtoVenta.verificaVK_F1(e) && !esGuiaRecepcion) {
            UtilityVentas.muestraDetalleProducto(myParentFrame, tblResumenPedido, 0, 1, 3, true);
        } else if (e.getKeyCode() == KeyEvent.VK_F2) {
            mostrarCabecera();
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
           agregarProducto();
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (validaDatos()){
                if(this.tipoProceso.equals("2") || //COTIZACION 
                   this.tipoProceso.equals("3")//SIN SOLICITUD
                ) {
                    if(JConfirmDialog.rptaConfirmDialog(this, "Los productos no moverán Kárdex. ¿Está seguro de generar la Cotización?")){
                            if (grabar()) {
                                FarmaUtility.aceptarTransaccion();

                                FarmaUtility.showMessage(this, "Cotización generada satisfactoriamente!", tblResumenPedido);
                                cerrarVentana(true);
                            }
                        
                    }
                }else{
                    if (JConfirmDialog.rptaConfirmDialog(this, "¿Está seguro de generar la Cotización?")) {
                        if (grabar()) {
                            FarmaUtility.aceptarTransaccion();

                            FarmaUtility.showMessage(this, "Cotización generada satisfactoriamente!", tblResumenPedido);
                            cerrarVentana(true);
                        }
                    }
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblResumenPedido.getSelectedRow() > -1) {
                int cantidad = Integer.parseInt(tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 4).toString());
                String indValido = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 5).toString();
                String precioVta= tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 12).toString();//unid_vta
                String unidadVta= tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), /*2*/3).toString();
                String indTieneCotizacion=tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 15).toString();
                int cantidadMinima=Integer.parseInt(tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 16).toString());
                int cantidadSolicitada=Integer.parseInt(tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 17).toString());
                if(getTipoProceso().equals("3")){ //SIN SOLICITUD
                        cargarCabecera();
                        
                        DlgCotizacionIngreseCantidad dlgCotizacionIngreseCantidad = new DlgCotizacionIngreseCantidad(myParentFrame, "", true);
                        dlgCotizacionIngreseCantidad.setTipoProceso(getTipoProceso());
                        dlgCotizacionIngreseCantidad.setPrecioVta(precioVta);
                        dlgCotizacionIngreseCantidad.setUnidadVta(unidadVta);
                        dlgCotizacionIngreseCantidad.setDivisibleFraccion(indValido);
                        dlgCotizacionIngreseCantidad.setVisible(true);                     
                        
                        if (FarmaVariables.vAceptar) {
                            actualizarProducto();
                            FarmaVariables.vAceptar = false;
                        }
                    }  else if(indValido.equals(FarmaConstants.INDICADOR_S)|| (indValido.equals(FarmaConstants.INDICADOR_N) && 
                                                                   getTipoProceso().equals("2")) ){
                        if(cantidad > 0){
                        if(indTieneCotizacion.equals("N")){//si no fue cotizado te permite ingresar cotizacion
                            cargarCabecera();
                            
                            DlgCotizacionIngreseCantidad dlgCotizacionIngreseCantidad = new DlgCotizacionIngreseCantidad(myParentFrame, "", true);
                            dlgCotizacionIngreseCantidad.setTipoProceso(getTipoProceso());
                            dlgCotizacionIngreseCantidad.setPrecioVta(precioVta);
                            dlgCotizacionIngreseCantidad.setUnidadVta(unidadVta);
                            dlgCotizacionIngreseCantidad.setDivisibleFraccion(indValido);
                            dlgCotizacionIngreseCantidad.setCantidadMinima(cantidadMinima);
                            dlgCotizacionIngreseCantidad.setCantidadSolicitada(cantidadSolicitada);

                            dlgCotizacionIngreseCantidad.setVisible(true);                     
                            
                            if (FarmaVariables.vAceptar) {
                                actualizarProducto();
                                FarmaVariables.vAceptar = false;
                            }
                           ///////
                         //   cerrarVentana(true);
                        }else{
                                FarmaUtility.showMessage(this, "El producto ya tiene cotización", btnResumenPedido);
                            }
                    }else{
                        FarmaUtility.showMessage(this, "Producto no tiene Cantidad Pendiente de compra", btnResumenPedido);
                    }   
                }else{
                    FarmaUtility.showMessage(this, "Producto no cumple con la Unidad de venta permitida en su Local", btnResumenPedido);
                }
            } else
                FarmaUtility.showMessage(this, "Debe seleccionar un producto", btnResumenPedido);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
     
                if(JConfirmDialog.rptaConfirmDialog(this, " ¿Está seguro de salir de la ventana ?")){
                        cerrarVentana(false);
                }else{
                    }

          //  cerrarVentana(false);
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

    private void  cargarCabecera() {
        VariablesInventario.vCodProd = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 0).toString();        

        VariablesInventario.vNomProd = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 1).toString();
        VariablesInventario.vUnidMed = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), /*2*/3).toString();
        VariablesInventario.vNomLab = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), /*3*/2).toString();
        VariablesInventario.vCant = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 4).toString();
        //VariablesInventario.vPrecUnit = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 5).toString();
        
        VarCotizacionPrecio.vIC_Cod_Prod = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 0).toString();
        VarCotizacionPrecio.vIC_Lote = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 9).toString();
        VarCotizacionPrecio.vIC_Fec_Vigencia = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 10).toString();
        VarCotizacionPrecio.vIC_Cant_Pendiente = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 4).toString();
        VarCotizacionPrecio.vIC_Cant_Comprada = Integer.parseInt(tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 6).toString());
        VarCotizacionPrecio.vIC_Precio_Unitario = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 7).toString();
        VarCotizacionPrecio.vIC_MotivoNoCot = tblResumenPedido.getValueAt(tblResumenPedido.getSelectedRow(), 14).toString();
    }


    private void actualizarProducto() {
        
        ((ArrayList)(VarCotizacionPrecio.vIC_ArrayProductosCotizacion.get(tblResumenPedido.getSelectedRow()))).set(6, String.valueOf(VarCotizacionPrecio.vIC_Cant_Comprada));
        ((ArrayList)(VarCotizacionPrecio.vIC_ArrayProductosCotizacion.get(tblResumenPedido.getSelectedRow()))).set(9, VarCotizacionPrecio.vIC_Lote);
        ((ArrayList)(VarCotizacionPrecio.vIC_ArrayProductosCotizacion.get(tblResumenPedido.getSelectedRow()))).set(10, VarCotizacionPrecio.vIC_Fec_Vigencia);
        ((ArrayList)(VarCotizacionPrecio.vIC_ArrayProductosCotizacion.get(tblResumenPedido.getSelectedRow()))).set(7, VarCotizacionPrecio.vIC_Precio_Unitario);
        ((ArrayList)(VarCotizacionPrecio.vIC_ArrayProductosCotizacion.get(tblResumenPedido.getSelectedRow()))).set(8, VarCotizacionPrecio.vIC_Total);
        ((ArrayList)(VarCotizacionPrecio.vIC_ArrayProductosCotizacion.get(tblResumenPedido.getSelectedRow()))).set(14, VarCotizacionPrecio.vIC_MotivoNoCot);

        cargaListaResumenPedido();
    }

    private void mostrarDatos() {
        lblFechaGuia.setText(VarCotizacionPrecio.vFecha_Cotizacion);
        lblTipoComp.setText(VarCotizacionPrecio.vDesc_Doc);
        lblNrocomp.setText(VarCotizacionPrecio.vNum_Doc);        
        String descripcionProceso="";
        if(this.getTipoProceso().equals("1")){
                descripcionProceso="Comprar";
            }
        else if(this.getTipoProceso().equals("2")){
                descripcionProceso="Cotizar";
            }
        else if(this.getTipoProceso().equals("3")){
        descripcionProceso="Sin Solicitud";
        }
        lblTipProceso.setText(descripcionProceso);   
    }

    private boolean validaDatos() {
        boolean retorno = true;
        
        /*if (!FarmaUtility.validaFecha(lblFechaGuia.getText().trim(), "dd/MM/yyyy")) {
            FarmaUtility.showMessage(this, "Debe ingresar los datos de Cabecera.", btnResumenPedido);
            retorno = false;
        } else if (tblResumenPedido.getRowCount() == 0) {
            FarmaUtility.showMessage(this, "No hay productos para esta Cotización.", btnResumenPedido);
            retorno = false;
        }*/
        
        int cantIngresada = 0;
        int cantMotNoCot=0;
        for (int i = 0; i < tblResumenPedido.getRowCount(); i++) {
            cantIngresada = cantIngresada + Integer.parseInt(tblResumenPedido.getValueAt(i, 6).toString());
            cantMotNoCot = cantMotNoCot + Integer.parseInt(tblResumenPedido.getValueAt(i, 14).toString());
        }
        
        if(cantIngresada <= 0 && cantMotNoCot<=0 ) {
            FarmaUtility.showMessage(this,"No hay Cantidad Ingresada alguna.", null);
            retorno = false;
        }
/*
        VariablesInventario.vMotivoNoImagen = "";
        
        if(Integer.parseInt(VarCotizacionPrecio.vTipo_Proceso) == ConstantsPtoVenta.TIPO_PROC_COT_PROD) {
            String ProductosAdjuntarImagen = UtilityPrecioCompetencia.existeProductosPorCotizar(tblResumenPedido);
            
            if (ProductosAdjuntarImagen != null && ProductosAdjuntarImagen.length() > 0 && lblImagen.getText().length() == 0){
                if (JConfirmDialog.rptaConfirmDialog(this, "Productos "+ProductosAdjuntarImagen+" necesitan sustento. ¿Desea Adjuntar imagen del Comprobante?")) {
                    //si es Sí, abre ventana para adjuntar imagen
                    File imagenAdjunta = UtilityPrecioCompetencia.cargarArchivo(this.myParentFrame);
                    if(imagenAdjunta.length() <= 512000){//500KB){
                        if(imagenAdjunta!=null){
                            VariablesInventario.vFileImagen = imagenAdjunta;
                            lblImagen.setText("Con Imagen Adjunta");
                        }
                    }else{
                        FarmaUtility.showMessage(this,"No se cargó la imagen, tamaño máximo es de 500KB.",null);
                    }    
                }else{
                    //sino, deja grabar
                    String input = "";
                }
            }
        }*/

        return retorno;
    }

    private boolean grabar() {
        boolean retorno;
        try {
            String fecha = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            int canProducto=0;
            for (int i = 0; i < tblResumenPedido.getRowCount(); i++) {
                //posicion 6 es la cantidad ingresada
                if(!tblResumenPedido.getValueAt(i, 6).toString().equals("0")){
                    ++canProducto;
                }
            }
      if(canProducto>0) {//tiene productos con cantidad

            String ruta = VarCotizacionPrecio.vRuta_Archivo;
            String nomFile = "";
            if(!ruta.equals("")){
                    nomFile = FarmaVariables.vCodLocal +"-"+ VarCotizacionPrecio.vCod_Solic +"-"+ VarCotizacionPrecio.vNum_Nota_Competencia;
                }
            String numera =
                BDCotizacionPrecio.agregarCabeceraGuiaIngreso((VarCotizacionPrecio.vFecha_Cotizacion.isEmpty()) ? fecha : VarCotizacionPrecio.vFecha_Cotizacion,
                                                        VarCotizacionPrecio.vTipo_Doc,       //VariablesInventario.vTipoDoc
                                                        VarCotizacionPrecio.vNum_Doc,        //VariablesInventario.vNumDoc
                                                        VarCotizacionPrecio.vTipo_Origen,    //VariablesInventario.vTipoOrigen
                                                        VarCotizacionPrecio.vCod_Origen,     //VariablesInventario.vCodOrigen
                                                        canProducto+"",
                                                        FarmaUtility.sumColumTable(tblResumenPedido, 8) + "",
                                                        VarCotizacionPrecio.vCompetencia,  //VariablesInventario.vNombreTienda
                                                        "",  //VariablesInventario.vCiudadTienda
                                                        VarCotizacionPrecio.vRUC_Comp,    //VariablesInventario.vRucTienda
                                                        VarCotizacionPrecio.vTipo_Proceso,
                                                        VarCotizacionPrecio.vCod_Solic,
                                                        nomFile/*VarCotizacionPrecio.vRuta_Archivo*//*"MOTIVO"*/,
                                                        getTipoProceso());
                                                    
            VarCotizacionPrecio.vNum_Nota_Competencia=numera;
          if(!ruta.equals("")){
                  //actualizar el nombre del documento
                  int p = ruta.lastIndexOf('.');
                  String extension="";
                  if (p > 0) {
                     extension  = ruta.substring(p+1,ruta.length());
                  }
                  nomFile = FarmaVariables.vCodLocal +"-"+ VarCotizacionPrecio.vCod_Solic +"-"+ VarCotizacionPrecio.vNum_Nota_Competencia+"."+extension;             
              }
 
            for (int i = 0; i < tblResumenPedido.getRowCount(); i++) {
                if(!tblResumenPedido.getValueAt(i, 6).toString().equals("0")){
                    BDCotizacionPrecio.agregarDetalleGuiaIngreso(numera, VarCotizacionPrecio.vTipo_Origen,
                                                           tblResumenPedido.getValueAt(i, 0).toString(),
                                                           tblResumenPedido.getValueAt(i, 7).toString(),
                                                           tblResumenPedido.getValueAt(i, 8).toString(),
                                                           tblResumenPedido.getValueAt(i, 6).toString(),
                                                           (VarCotizacionPrecio.vFecha_Cotizacion.isEmpty()) ? fecha : VarCotizacionPrecio.vFecha_Cotizacion,
                                                           (tblResumenPedido.getValueAt(i, 10).toString().isEmpty()) ? fecha : tblResumenPedido.getValueAt(i, 10).toString(),
                                                           tblResumenPedido.getValueAt(i, 9).toString(),
                                                           tblResumenPedido.getValueAt(i, 11).toString(),//ValFrac
                                                           tblResumenPedido.getValueAt(i, 13).toString(),
                                                            getTipoProceso()     
                                                           );
                }
                
                if (!getTipoProceso().equals("3")){//no aplica para cotizacion sin solicitud
                  if(!tblResumenPedido.getValueAt(i, 6).toString().equals("0") || 
                     //motivo 1 o 0 es sin motivo
                    ( !tblResumenPedido.getValueAt(i, 14).toString().equals("0") && !tblResumenPedido.getValueAt(i, 14).toString().equals("1"))
                     ){
                    BDCotizacionPrecio.actualizaCantIngreSolicitud(VarCotizacionPrecio.vCod_Solic, 
                                                                   tblResumenPedido.getValueAt(i, 0).toString(), 
                                                                   tblResumenPedido.getValueAt(i, 6).toString(),
                                                                    nomFile/*VarCotizacionPrecio.vRuta_Archivo*//*"MOTIVO4"*/,
                                                                    tblResumenPedido.getValueAt(i, 14).toString()// cod_motivo
                                                                   );
                  }
                }
            }


            VariablesInventario.vTipoDocCotizado = VarCotizacionPrecio.vTipo_Doc;
            VariablesInventario.vNumDocCotizado = VarCotizacionPrecio.vNum_Doc;
            VariablesInventario.vFecGuiaCotizado = VarCotizacionPrecio.vFecha_Cotizacion;
            cargaArchivoFtp();
            
          //envia el pedido al RAC-
          FacadeCotizacionPrecio facadeCotizacionPrecio = new FacadeCotizacionPrecio();
          facadeCotizacionPrecio.insertaCotizacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, 
                                                   VarCotizacionPrecio.vCod_Solic, numera);
          
          //actualiza el estado de la solicitud local
          // •        P-> pendiente
          // •        A->Atendido
          if (!getTipoProceso().equals("3")){
            BDCotizacionPrecio.actualizaEstadoSolicitud(VarCotizacionPrecio.vCod_Solic);
          }
          
 
          if(!ruta.equals("")){
                 BDCotizacionPrecio.actualizaNombreSustento(numera,nomFile);
             
              }
      }else{ //actualiza el motivo del producto
          for (int i = 0; i < tblResumenPedido.getRowCount(); i++) {
              //posion14 es el motivo
              if( !tblResumenPedido.getValueAt(i, 14).toString().equals("0") &&
                  !tblResumenPedido.getValueAt(i, 14).toString().equals("1") ){
                BDCotizacionPrecio.actualizaCantIngreSolicitud(VarCotizacionPrecio.vCod_Solic, 
                                                               tblResumenPedido.getValueAt(i, 0).toString(), 
                                                               tblResumenPedido.getValueAt(i, 6).toString(),
                                                                "",
                                                                tblResumenPedido.getValueAt(i, 14).toString() // cod_motivo
                                                               );
              }
            }  
        String indCotizado=  BDCotizacionPrecio.actualizaEstadoSolicitud(VarCotizacionPrecio.vCod_Solic);
          //si la cotizacions se realizo el cambia a A--> cotizacion atendida
          if(indCotizado.equals("A")){
              //INSERTA EN EL LOCAL
              BDCotizacionPrecio.insertarSolicitudCotizaNota(VarCotizacionPrecio.vCod_Solic, "0000000000");
                  
              //envia la cotizacion al RAC sin numero de nota porque todos los productos tiene observación
              FacadeCotizacionPrecio facadeCotizacionPrecio = new FacadeCotizacionPrecio();
              facadeCotizacionPrecio.insertaCotizacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, 
                                                       VarCotizacionPrecio.vCod_Solic, "0000000000");
              }
          
      }

            limpiarDatosCabecera();
            retorno = true;
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ha ocurrido un error al grabar los datos del pedido : \n" + sql.getMessage(), btnResumenPedido);
            retorno = false;
        } catch (Exception ex) {
            FarmaUtility.liberarTransaccion();
            log.error("", ex);
            FarmaUtility.showMessage(this, "Error en la aplicación al grabar los datos del pedido : \n" + ex.getMessage(), btnResumenPedido);
            retorno = false;
        }
        
        return retorno;
    }

    private void limpiarDatosCabecera() {
        VariablesInventario.vFecGuia = "";
        VarCotizacionPrecio.vTipo_Doc = "";
        VariablesInventario.vDescDoc = "";
        VarCotizacionPrecio.vNum_Doc = "";
        VarCotizacionPrecio.vTipo_Origen = "";
        VariablesInventario.vNomOrigen = "";
        VariablesInventario.vCodOrigen = "";
        VariablesInventario.vDescOrigen = "";
        
        VarCotizacionPrecio.vFecha_Cotizacion = "";
        VarCotizacionPrecio.vTipo_Doc = "";
        VarCotizacionPrecio.vSerieDoc = "";
        VarCotizacionPrecio.vNroGuia = "";
        VarCotizacionPrecio.vRuta_Archivo = "";
    }

    private void mostrarCabecera() {
     
        DlgCotizacionPrecioCabecera dlgCotizacionPrecioCabecera = new DlgCotizacionPrecioCabecera(myParentFrame, "", true);
         dlgCotizacionPrecioCabecera.setTipoProceso(String.valueOf(tipoProceso));
        dlgCotizacionPrecioCabecera.setNombreTienda(nombreTienda);
        dlgCotizacionPrecioCabecera.setNroRUC(nroRUC);
        dlgCotizacionPrecioCabecera.setNroSolicitud(nroSolicitud);
        dlgCotizacionPrecioCabecera.setLlamoDetalle(true);
        dlgCotizacionPrecioCabecera.setVisible(true);
        //VarCotizacionPrecio.vCompetencia=dlgCotizacionPrecioCabecera.getNombreTienda();
       // VarCotizacionPrecio.vRUC_Comp=dlgCotizacionPrecioCabecera.getNroRUC();
        nombreTienda=dlgCotizacionPrecioCabecera.getNombreTienda();
        nroRUC=dlgCotizacionPrecioCabecera.getNroRUC();
        
        mostrarDatos();
        
        /*if (FarmaVariables.vAceptar) {
            if(VariablesInventario.vFileImagen!=null){
                lblImagen.setText("Con Imagen Adjunta");
                lblImagen.setForeground(Color.YELLOW);
            }

            mostrarDatos();
            FarmaVariables.vAceptar = false;
        }*/
        //lineas nuevas
        /*lblFechaGuia.setText(dlgCotizacionPrecioCabecera.getFechaCreacion());  
        lblTipoComp.setText(VarCotizacionPrecio.vDesc_Doc);
        lblNrocomp.setText(dlgCotizacionPrecioCabecera.getNroComprobante());*/
     
       // FarmaVariables.vAceptar = true;
       
    }

    private void calcularTotal() {
        double total = 0.00;
        int cantidad = tblResumenPedido.getRowCount();

        if (cantidad > 0) {
            for (int i = 0; i < cantidad; i++) {
                total += FarmaUtility.getDecimalNumber(tblResumenPedido.getValueAt(i, 8).toString().trim());
            }
        }

        lblTotal.setText(FarmaUtility.formatNumber(total, 2));
    }
    
    private void pintarProds_NoValidos() {
        ArrayList rowsWithOtherColor = new ArrayList();
        for (int i = 0; i < tableModelResumenPedido.data.size(); i++) {
            if (((ArrayList)tableModelResumenPedido.data.get(i)).get(ConstantesCotiza.INDEX_IND_VALIDO).toString().trim().equalsIgnoreCase("N")) { 
                rowsWithOtherColor.add(String.valueOf(i));
            }
        }
        
        FarmaUtility.initSimpleListCleanColumns(tblResumenPedido, tableModelResumenPedido,
                                                ConstantesCotiza.columnsListaResumenCotizacion, rowsWithOtherColor,
                                                Color.WHITE, Color.RED, false);
        
        tblResumenPedido.getTableHeader().setReorderingAllowed(false);
        tblResumenPedido.getTableHeader().setResizingAllowed(false);
    }
    
    public void cargaArchivoFtp(){
        if (!VarCotizacionPrecio.vRuta_Archivo.trim().equalsIgnoreCase("")) { //SI SELECCIONO ARCHIVO
            String rutaOrigen = "";
            String[] rutasArray = VarCotizacionPrecio.vRuta_Archivo.split(";;;");
            
            String nomFile = FarmaVariables.vCodLocal + VarCotizacionPrecio.vCod_Solic + VarCotizacionPrecio.vNum_Nota_Competencia;
            String INPUT_FOLDER = "C:\\inetpub\\ftproot\\IMG_COTIZACION_PRECIO\\" + nomFile;
            String ZIPPED_FOLDER = "C:\\inetpub\\ftproot\\IMG_COTIZACION_PRECIO\\" + nomFile + ".zip";
            File directorio = new File(INPUT_FOLDER);
            File diretory_zip = new File(ZIPPED_FOLDER);
            
            rutaOrigen = VarCotizacionPrecio.vRuta_Archivo;

            cargarParametrosFtp();
            
            String nombreCarpeta = "";
            try{
                nombreCarpeta = BDCotizacionPrecio.getNombreCarpetaCotizacion();
            }catch(Exception e){
                e.printStackTrace();
            }
            
            boolean envioFtp = UtilFtp.conectarFtpCotizacion(rutaOrigen, this.getDirFtpRemoto(), VarCotizacionPrecio.vCod_Solic, 
                                                   this.getUsrFtp(), this.getPwdFtp(), this.getPuertoFtp(), 
                                                   this.getServidorFtp(), nombreCarpeta);
            log.debug("Envío FTP ? =" + envioFtp);
        }
    }
    
    public void cargarParametrosFtp(){
        try {
            FarmaVentaCnxUtility facade = new FarmaVentaCnxUtility();
            conexionFtp = facade.setBeanConexion(UtilityControlAsistencia.getParametrosFTP());
            this.setServidorFtp(conexionFtp.getIPBD());
            this.setUsrFtp(conexionFtp.getUsuarioBD());
            this.setPwdFtp(conexionFtp.getClaveBD());
            this.setPuertoFtp(Integer.valueOf(conexionFtp.getPORT()));
            this.setDirFtpRemoto(conexionFtp.getServiceName());//SE USARA COMO DIRECTORIO REMOTO                       
        } catch (Exception f) {
            log.error("Error al setear parámetros de conexión", f);
        }
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroSolicitud(String nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    public String getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNroRUC(String nroRUC) {
        this.nroRUC = nroRUC;
    }

    public String getNroRUC() {
        return nroRUC;
    }

    public void setDirFtpRemoto(String dirFtpRemoto) {
        this.dirFtpRemoto = dirFtpRemoto;
    }

    public String getDirFtpRemoto() {
        return dirFtpRemoto;
    }

    public void setUsrFtp(String usrFtp) {
        this.usrFtp = usrFtp;
    }

    public String getUsrFtp() {
        return usrFtp;
    }

    public void setPwdFtp(String pwdFtp) {
        this.pwdFtp = pwdFtp;
    }

    public String getPwdFtp() {
        return pwdFtp;
    }

    public void setServidorFtp(String servidorFtp) {
        this.servidorFtp = servidorFtp;
    }

    public String getServidorFtp() {
        return servidorFtp;
    }

    public void setPuertoFtp(int puertoFtp) {
        this.puertoFtp = puertoFtp;
    }

    public int getPuertoFtp() {
        return puertoFtp;
    }
    private void agregarProducto() {
        
            DlgGuiaIngresoProductos dlgGuiaIngresoProductos = new DlgGuiaIngresoProductos(myParentFrame, "", true);
             dlgGuiaIngresoProductos.setTipoProcesoCotizacion(getTipoProceso());
            dlgGuiaIngresoProductos.setVisible(true);

            if (FarmaVariables.vAceptar) {
                cargaListaResumenPedido();
                FarmaVariables.vAceptar = false;
            }
        
    }
    
}
