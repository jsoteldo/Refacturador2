package mifarma.ptoventa.cotizarPrecios;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableCellRenderer;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.referencia.ConstantesCotiza;
import mifarma.ptoventa.cotizarPrecios.referencia.UtilCotizacion;
import mifarma.ptoventa.ventas.DlgDetalleProducto;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCotizaVerDetalle extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgCotizaVerDetalle.class);
    private Frame myParentFrame;
    private FarmaTableModel tblModelProductos;
    private JTable tblProductos = new JTable();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlFechas = new JPanel();
    private JPanel pnlListaProductos = new JPanel();
    private JScrollPane scpProducto = new JScrollPane();
    private JLabel lblTituloFechas = new JLabel();
    private JLabel lblListaProducto = new JLabel();
    private JLabelFunction lblNuevo_F1 = new JLabelFunction();
    private JLabelFunction lblAnular_Del = new JLabelFunction();
    private JLabelFunction lblSalir_Esc = new JLabelFunction();
    private JLabel lblSolicitudNro = new JLabel();
    private String tituloVentana = "";
    private JLabelFunction lblCambiaProd = new JLabelFunction();
    private JPanel pnlMensaje = new JPanel();
    private JLabel lblMensaje = new JLabel();
    private String codigoSolicitud = "";
    private JScrollPane scpScrollBar = new JScrollPane();
    private JScrollBar jsbScrollProd = new JScrollBar();
    private int conDocumento = 2;

    public DlgCotizaVerDetalle() {
        super();
    }

    public DlgCotizaVerDetalle(Frame parent, String title, boolean modal, String codSolicitud) {
        super(parent, title, modal);
        myParentFrame = parent;
        tituloVentana = title;
        codigoSolicitud = codSolicitud;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void imprimir(String msj) {
        System.out.println(msj);
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(632, 468));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ver detalle de la solicitud de precios");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.white);
        this.setBackground(Color.white);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });

        ctpContenido.setBackground(Color.white);
        ctpContenido.setLayout(null);
        ctpContenido.setSize(new Dimension(623, 439));
        ctpContenido.setForeground(Color.white);

        pnlFechas.setBounds(new Rectangle(15, 5, 595, 45));
        pnlFechas.setBackground(new Color(43, 141, 39));
        pnlFechas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlFechas.setLayout(null);

        scpProducto.setBounds(new Rectangle(15, 70, 575, 280));
        scpProducto.setBackground(new Color(255, 130, 14));

        scpProducto.setAutoscrolls(false);
        scpProducto.setVerticalScrollBar(jsbScrollProd);
        lblTituloFechas.setText("Competencia : " + VarCotizacionPrecio.vRUC_Comp + " - " +
                                VarCotizacionPrecio.vCompetencia + " vigente hasta " +
                                VarCotizacionPrecio.vFec_Vigencia);
        lblTituloFechas.setBounds(new Rectangle(5, 5, 580, 35));
        lblTituloFechas.setBackground(SystemColor.window);
        lblTituloFechas.setForeground(SystemColor.window);
        lblTituloFechas.setFont(new Font("SansSerif", 1, 14));
        /*
        lblEstado.setText("Estado: ");
        lblEstado.setBounds(new Rectangle(380, 35, 55, 15));
        lblEstado.setFont(new Font("SansSerif", 1, 12));
        lblEstado.setForeground(SystemColor.window);
        cmbEstado.setBounds(new Rectangle(435, 33, 135, 20));
        */
        lblListaProducto.setText("Lista de productos");
        lblListaProducto.setBounds(new Rectangle(5, 2, 145, 15));
        lblListaProducto.setFont(new Font("SansSerif", 1, 12));
        lblListaProducto.setForeground(SystemColor.window);
        lblNuevo_F1.setText("[F1] Ver Info. Producto");
        lblNuevo_F1.setBounds(new Rectangle(15, 395, 135, 25));
        lblAnular_Del.setText("[Enter] Ingresa cotizacion");
        lblAnular_Del.setBounds(new Rectangle(340, 395, 170, 25));
        lblSalir_Esc.setText("[ESC] Cerrar");
        lblSalir_Esc.setBounds(new Rectangle(520, 395, 90, 25));
        lblSolicitudNro.setBounds(new Rectangle(170, 2, 410, 15));
        lblSolicitudNro.setText("");
        lblSolicitudNro.setFont(new Font("SansSerif", 1, 12));
        lblSolicitudNro.setForeground(SystemColor.window);
        lblSolicitudNro.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolicitudNro.setHorizontalTextPosition(SwingConstants.RIGHT);


        lblCambiaProd.setText("[F12] Cambiar Producto");
        lblCambiaProd.setBounds(new Rectangle(165, 395, 155, 25));

        pnlMensaje.setBounds(new Rectangle(15, 350, 595, 30));
        pnlMensaje.setBackground(Color.red);
        pnlMensaje.setLayout(null);

        lblMensaje.setText("¡ATENCI\u00d3N! Los productos de color rojo no cumplen con la unidad de venta permitida en su local");
        lblMensaje.setBounds(new Rectangle(10, 2, 565, 25));
        lblMensaje.setFont(new Font("SansSerif", 3, 12));
        lblMensaje.setForeground(SystemColor.window);

        lblMensaje.setVerticalTextPosition(SwingConstants.TOP);
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scpScrollBar.setBounds(new Rectangle(590, 70, 20, 280));
        pnlListaProductos.setBounds(new Rectangle(15, 50, 595, 20));
        pnlListaProductos.setBackground(new Color(255, 130, 14));
        pnlListaProductos.setLayout(null);

        tblProductos.setFont(new Font("SansSerif", 0, 12));
        tblProductos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblProductos_KeyPressed(e);
            }
        });
        scpProducto.getViewport();

        /*
        pnlFechas.add(cmbEstado, null);
        pnlFechas.add(lblEstado, null);
    */
        pnlMensaje.add(lblMensaje, null);
        scpScrollBar.getViewport().add(jsbScrollProd, null);
        ctpContenido.add(scpScrollBar, null);
        ctpContenido.add(pnlMensaje, null);
        ctpContenido.add(lblCambiaProd, null);
        ctpContenido.add(lblSalir_Esc, null);
        ctpContenido.add(lblAnular_Del, null);
        pnlFechas.add(lblTituloFechas, null);
        ctpContenido.add(pnlFechas, null);
        scpProducto.getViewport().add(tblProductos, null);
        ctpContenido.add(scpProducto, null);
        ctpContenido.add(pnlListaProductos, null);
        ctpContenido.add(lblNuevo_F1, null);
        pnlListaProductos.add(lblSolicitudNro, null);
        pnlListaProductos.add(lblListaProducto, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);
    }

    private void initialize() {
        initListaProducto();
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        cargarDatos_Cotizacion();
    }

    private void cargarDatos_Cotizacion() {
        UtilCotizacion.cargaTabla_Productos(tblModelProductos, codigoSolicitud);
        pintarProds_NoValidos();
        lblSolicitudNro.setText("Solicitud " + codigoSolicitud + ": Cant. de productos a cotizar: " +
                                tblModelProductos.getRowCount());
        FarmaUtility.moveFocusJTable(tblProductos);
    }

    private void this_windowClosing(WindowEvent e) {
        muestraMensaje("Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void muestraMensaje(String msj, Object cTxt) {
        FarmaUtility.showMessage(this, msj, cTxt);
    }

    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            cerrarVentana();
            break;
        }
    }

    private void initListaProducto() {
        tblModelProductos =
                new FarmaTableModel(ConstantesCotiza.columnsProducto_Solicitud, ConstantesCotiza.defaultValuesProducto_Solicitud,
                                    0);
        FarmaUtility.initSimpleList(tblProductos, tblModelProductos, ConstantesCotiza.columnsProducto_Solicitud);
    }

    private void tblProductos_KeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
            e.consume();

            //INI AOVIEDO 21/07/2017
            int x = tblProductos.getSelectedRow();
            //String nombreTienda = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COMPETENCIA);
            //VarCotizacionPrecio.vCompetencia = FarmaUtility.getValueFieldArrayList(tblModelProductos.data, x, ConstantesCotiza.INDEX_COMPETENCIA);
            //String nroRUC = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_RUC_COMPETENCIA);
            //VarCotizacionPrecio.vRUC_Comp = FarmaUtility.getValueFieldArrayList(tblModelProductos.data, x, ConstantesCotiza.INDEX_RUC_COMPETENCIA);
            //String nroSolicitud = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COD_SOLIC);
            //VarCotizacionPrecio.vCod_Solic = FarmaUtility.getValueFieldArrayList(tblModelProductos.data, x, ConstantesCotiza.INDEX_COD_SOLIC);
            VarCotizacionPrecio.vTipo_Proceso = "02";
            VarCotizacionPrecio.vTipo_Origen = "04";
            VarCotizacionPrecio.vCod_Origen = FarmaVariables.vCodLocal;

            VarCotizacionPrecio.vFecha_Cotizacion = "";
            VarCotizacionPrecio.vTipo_Doc = "";
            VarCotizacionPrecio.vSerieDoc = "";
            VarCotizacionPrecio.vNroGuia = "";
            VarCotizacionPrecio.vRuta_Archivo = "";

            try {
                VarCotizacionPrecio.vFecha_Cotizacion = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            } catch (SQLException sql) {
                log.error("", sql);
            }

            if (this.conDocumento == 2) {
                if (JConfirmDialog.rptaConfirmDialog(this, "¿Tiene el documento físico?")) {
                    abrirPantallaCabecera(this.conDocumento, VarCotizacionPrecio.vCompetencia,
                                          VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic);
                } else {
                    VarCotizacionPrecio.vRuta_Archivo = "";
                    abrirPantallaDetalle(this.conDocumento, VarCotizacionPrecio.vCompetencia,
                                         VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic);
                }
            } else {
                abrirPantallaCabecera(this.conDocumento, VarCotizacionPrecio.vCompetencia,
                                      VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic);
            }

            //FIN AOVIEDO 21/07/2017

            break;
        case KeyEvent.VK_F1:
            int i = tblProductos.getSelectedRow();
            VariablesVentas.vCod_Prod =
                    FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i, ConstantesCotiza.INDEX_COD_PROD);
            DlgDetalleProducto dlgDetalleProducto = new DlgDetalleProducto(myParentFrame, "", true, false);
            dlgDetalleProducto.setVisible(true);
            break;
        case KeyEvent.VK_F12:
            seleccionaregistro();
            permitirCambiarProducto();
            cargarDatos_Cotizacion();
            //String ok=UtilCotizacion.verificaAutorizacion_deCambio(VarCotizacionPrecio.vCod_Solic,VarCotizacionPrecio.vCod_Prod);
            /*
            FacadeCotizacionPrecio f = new FacadeCotizacionPrecio();
            String ok=f.verificaAutorizacionDeCambio(FarmaVariables.vCodGrupoCia,FarmaVariables.vCodLocal,
                                                     VarCotizacionPrecio.vCod_Solic,VarCotizacionPrecio.vCod_Prod);
            muestraMensaje(ok,null);

            switch(ok){
            case "01":
                muestraMensaje("La solicitud de autorizacion de cambio ha sido enviada,\nespere la aprobacion por adm",null);
                break;
            case "02":
                muestraMensaje("Aun no se tiene respuesta a la solicitud de cambion envida a adm",null);
                break;
            case "03":
                muestraMensaje("La solicitud de cambio de producto a cotizar fue rechazadas.",null);
                break;
            case "04":
                permitirCambiarProducto();
                cargarDatos_Cotizacion();
                break;
            }*/
            break;
        default:
            chkKeyPressed(e);
        }
    }

    private void abrirPantallaCabecera(int tipoProceso, String nombreTienda, String nroRUC, String nroSolicitud) {
        DlgCotizacionPrecioCabecera dlgCotizacionPrecioCabecera =
            new DlgCotizacionPrecioCabecera(myParentFrame, "", true);
        dlgCotizacionPrecioCabecera.setTipoProceso(String.valueOf(tipoProceso));
        dlgCotizacionPrecioCabecera.setNombreTienda(nombreTienda);
        dlgCotizacionPrecioCabecera.setNroRUC(nroRUC);
        dlgCotizacionPrecioCabecera.setNroSolicitud(nroSolicitud);
        dlgCotizacionPrecioCabecera.setVisible(true);

        if (FarmaVariables.vAceptar) {
            DlgCotizacionPrecioDetalle dlgCotizacionPrecioDetalle =
                new DlgCotizacionPrecioDetalle(myParentFrame, "", true, this.conDocumento + "");
            dlgCotizacionPrecioDetalle.setFechaCreacion(dlgCotizacionPrecioCabecera.getFechaCreacion());
            dlgCotizacionPrecioDetalle.setTipoProceso(dlgCotizacionPrecioCabecera.getTipoProceso());
            dlgCotizacionPrecioDetalle.setTipoComprobante(dlgCotizacionPrecioCabecera.getTipoComprobante());
            dlgCotizacionPrecioDetalle.setNroComprobante(dlgCotizacionPrecioCabecera.getNroComprobante());
            dlgCotizacionPrecioDetalle.setNroSolicitud(dlgCotizacionPrecioCabecera.getNroSolicitud());
            dlgCotizacionPrecioDetalle.setNombreTienda(nombreTienda);
            dlgCotizacionPrecioDetalle.setNroRUC(nroRUC);
            dlgCotizacionPrecioDetalle.setVisible(true);
        }
    }

    private void abrirPantallaDetalle(int tipoProceso, String nombreTienda, String nroRUC, String nroSolicitud) {
        DlgCotizacionPrecioDetalle dlgCotizacionPrecioDetalle =
            new DlgCotizacionPrecioDetalle(myParentFrame, "", true, this.conDocumento + "");
        dlgCotizacionPrecioDetalle.setFechaCreacion("");
        dlgCotizacionPrecioDetalle.setTipoProceso(String.valueOf(tipoProceso));
        dlgCotizacionPrecioDetalle.setTipoComprobante("");
        dlgCotizacionPrecioDetalle.setNroComprobante("");
        dlgCotizacionPrecioDetalle.setNroSolicitud(nroSolicitud);
        dlgCotizacionPrecioDetalle.setNombreTienda(nombreTienda);
        dlgCotizacionPrecioDetalle.setNroRUC(nroRUC);
        dlgCotizacionPrecioDetalle.setVisible(true);
    }

    private void pintarProds_NoValidos() {
        ArrayList rowsWithOtherColor = new ArrayList();
        for (int i = 0; i < tblModelProductos.data.size(); i++) {
            if (((ArrayList)tblModelProductos.data.get(i)).get(ConstantesCotiza.INDEX_IND_VALIDO).toString().trim().equalsIgnoreCase("N")) {
                rowsWithOtherColor.add(String.valueOf(i));
            }
        }

        FarmaUtility.initSimpleListCleanColumns(tblProductos, tblModelProductos,
                                                ConstantesCotiza.columnsProducto_Solicitud, rowsWithOtherColor,
                                                Color.WHITE, Color.RED, false);

        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.getTableHeader().setResizingAllowed(false);
    }

    private void permitirCambiarProducto() {
        DlgCambiarProd_Cotizar dlgCambiar = new DlgCambiarProd_Cotizar(myParentFrame, "", true);
        dlgCambiar.setVisible(true);
    }

    private void seleccionaregistro() {
        int j = tblProductos.getSelectedRow();
        VarCotizacionPrecio.vCod_Prod =
                FarmaUtility.getValueFieldArrayList(tblModelProductos.data, j, ConstantesCotiza.INDEX_COD_PROD);
        VarCotizacionPrecio.vProducto =
                FarmaUtility.getValueFieldArrayList(tblModelProductos.data, j, ConstantesCotiza.INDEX_PRODUCTO);
        VarCotizacionPrecio.vUnidad =
                FarmaUtility.getValueFieldArrayList(tblModelProductos.data, j, ConstantesCotiza.INDEX_UNIDAD);
        VarCotizacionPrecio.vLaboratorio =
                FarmaUtility.getValueFieldArrayList(tblModelProductos.data, j, ConstantesCotiza.INDEX_LABORATORIO);
        VarCotizacionPrecio.vCant_Solic =
                FarmaUtility.getValueFieldArrayList(tblModelProductos.data, j, ConstantesCotiza.INDEX_CANTIDAD);
        VarCotizacionPrecio.vValido =
                FarmaUtility.getValueFieldArrayList(tblModelProductos.data, j, ConstantesCotiza.INDEX_IND_VALIDO);
    }

    public void setConDocumento(int conDocumento) {
        this.conDocumento = conDocumento;
    }

    public int getConDocumento() {
        return conDocumento;
    }
}
