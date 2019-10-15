package mifarma.ptoventa.cotizarPrecios;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;

import com.gs.mifarma.componentes.JLabelWhite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableComparator;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.referencia.ConstantesCotiza;
import mifarma.ptoventa.cotizarPrecios.referencia.FacadeCotizacionPrecio;
import mifarma.ptoventa.fraccion.reference.UtilityFraccion;

import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCambiarProd_Cotizar  extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgCambiarProd_Cotizar.class);
    private Frame myParentFrame;    
    private FarmaTableModel tblModelProductos;
    private JTable tblProductos = new JTable();

    private JPanel pnlWhite = new JPanel();
    private JLabelWhite lblProducto_T    = new JLabelWhite(); 
    private JLabelWhite lblProducto      = new JLabelWhite(); 
    private JLabelWhite lblLaboratorio_T = new JLabelWhite(); 
    private JLabelWhite lblLaboratorio   = new JLabelWhite(); 
    private JLabelWhite lblUnidad_T      = new JLabelWhite(); 
    private JLabelWhite lblUnidad        = new JLabelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlTitulo = new JPanel();
    private JPanel pnBusquedaProducto = new JPanel();
    private JScrollPane spnProducto = new JScrollPane();
    private JLabel lblBuscarProducto = new JLabel();
    private JLabelFunction lblSalir_Esc = new JLabelFunction();
    private JButton lblBusqueda = new JButton();
    private JTextField txtProducto = new JTextField();
    private JLabelFunction lblGrabar_F11 = new JLabelFunction();
    private JLabelOrange lblLeyenda = new JLabelOrange();
    private JLabel lblCantProd = new JLabel();
    private boolean procesoRealizado=false;
    private JScrollPane spnScrollProd = new JScrollPane();
    private JScrollBar jsbScrollProducto = new JScrollBar();
    int INDEX_COD_PROD=1;//0
    int INDEX_DESC_PROD=2;
    int INDEX_UNIDAD=3; //
    int INDEX_LABORATORIO=4;
    int INDEX_PRECIO=6;


    public DlgCambiarProd_Cotizar() {
        this(null, "", false);
    }
    
    public DlgCambiarProd_Cotizar(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(702, 435));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Cambiar Producto ");
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

        pnlWhite.setBounds(new Rectangle(15, 10, 675, 70));
        pnlWhite.setBackground(Color.white);
        pnlWhite.setLayout(null);
        pnlWhite.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        
        lblProducto_T.setText("Producto:");
        lblProducto_T.setBounds(new Rectangle(15, 10, 70, 15));
        lblProducto_T.setForeground(new Color(255, 130, 14));
        lblProducto.setBounds(new Rectangle(85, 10, 280, 15));
        lblProducto.setFont(new Font("SansSerif", 0, 11));
        lblProducto.setForeground(new Color(255, 130, 14));

        lblLaboratorio_T.setText("Laboratorio:");
        lblLaboratorio_T.setBounds(new Rectangle(15, 35, 70, 15));
        lblLaboratorio_T.setForeground(new Color(255, 130, 14));
        lblLaboratorio.setBounds(new Rectangle(90, 35, 280, 15));
        lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
        lblLaboratorio.setForeground(new Color(255, 130, 14));
        
        lblUnidad_T.setText("Unidad:");
        lblUnidad_T.setBounds(new Rectangle(365, 10, 70, 15));
        lblUnidad_T.setForeground(new Color(255, 130, 14));
        lblUnidad.setBounds(new Rectangle(410, 10, 245, 15));
        lblUnidad.setFont(new Font("SansSerif", 0, 11));
        lblUnidad.setForeground(new Color(255, 130, 14));

        pnlWhite.add(lblProducto_T    , null);
        pnlWhite.add(lblProducto      , null);
        pnlWhite.add(lblLaboratorio_T , null);
        pnlWhite.add(lblLaboratorio   , null);
        pnlWhite.add(lblUnidad_T      , null);

        pnlWhite.add(lblUnidad        , null);
        ctpContenido.setBackground(Color.white);
        ctpContenido.setLayout(null);
        ctpContenido.setSize(new Dimension(623, 439));
        ctpContenido.setForeground(Color.white);

        pnlTitulo.setBounds(new Rectangle(15, 90, 675, 45));
        pnlTitulo.setBackground(new Color(43, 141, 39));
        pnlTitulo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlTitulo.setLayout(null);


        lblBuscarProducto.setText("Lista de productos");
        lblBuscarProducto.setBounds(new Rectangle(5, 5, 210, 15));
        lblBuscarProducto.setFont(new Font("SansSerif", 1, 12));
        lblBuscarProducto.setForeground(SystemColor.window);


        lblSalir_Esc.setText("[ESC] Cerrar");
        lblSalir_Esc.setBounds(new Rectangle(550, 370, 130, 25));

        lblBusqueda.setText("Buscar :");
        lblBusqueda.setBounds(new Rectangle(10, 15, 70, 20));
        lblBusqueda.setFont(new Font("SansSerif", 1, 12));
        lblBusqueda.setForeground(Color.white);
        lblBusqueda.setBackground(new Color(43, 141, 39));
        lblBusqueda.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblBusqueda.setBorderPainted(false);
        lblBusqueda.setContentAreaFilled(false);
        lblBusqueda.setDefaultCapable(false);
        lblBusqueda.setFocusPainted(false);
        lblBusqueda.setMnemonic('B');
        lblBusqueda.setRequestFocusEnabled(false);
        lblBusqueda.setHorizontalAlignment(SwingConstants.LEFT);
        lblBusqueda.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblBusqueda_ActionPerformed(e);
                }
            });

        lblBusqueda.addContainerListener(new ContainerAdapter() {
                public void componentRemoved(ContainerEvent e) {
                    lblBusquedad_ComponenRemoved(e);
                }
            });
        txtProducto.setBounds(new Rectangle(80, 15, 460, 20));
        txtProducto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtProducto_keyPressed(e);

            }

            public void keyReleased(KeyEvent e) {
                txtProducto_keyReleased(e);
            }
        });
        lblGrabar_F11.setText("[F11] Seleccionar producto");
        lblGrabar_F11.setBounds(new Rectangle(340, 370, 190, 25));
        
        lblLeyenda.setText("Productos fraccionables se muestran de color amarillo");
        lblLeyenda.setBounds(new Rectangle(295, 270, 485, 25));
        lblLeyenda.setForeground(Color.red);
        lblLeyenda.setFont(new Font("DialogInput", 1, 15));
        lblLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLeyenda.setVisible(false);
        
        lblCantProd.setBounds(new Rectangle(300, 0, 255, 15));
        lblCantProd.setFont(new Font("SansSerif", 1, 12));
        lblCantProd.setForeground(SystemColor.window);

        lblCantProd.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCantProd.setHorizontalTextPosition(SwingConstants.RIGHT);


        spnScrollProd.setBounds(new Rectangle(670, 165, 20, 185));
        pnBusquedaProducto.setBounds(new Rectangle(15, 140, 675, 25));
        pnBusquedaProducto.setBackground(new Color(255, 130, 14));
        pnBusquedaProducto.setLayout(null);

        spnProducto.setBounds(new Rectangle(15, 165, 655, 185));
        spnProducto.setBackground(new Color(255, 130, 14));
        spnProducto.setAutoscrolls(false);
        spnProducto.setVerticalScrollBar(jsbScrollProducto);
        tblProductos.setFont(new Font("SansSerif", 0, 12));
        tblProductos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblProductos_mouseClicked(e);
            }
        });
        
        spnProducto.getViewport();

        spnScrollProd.getViewport().add(jsbScrollProducto, null);

        ctpContenido.add(pnlWhite, null);
        ctpContenido.add(spnScrollProd, null);
        ctpContenido.add(lblLeyenda, null);
        ctpContenido.add(lblGrabar_F11, null);
        ctpContenido.add(lblSalir_Esc, null);
        ctpContenido.add(pnlTitulo, null);
        pnlTitulo.add(txtProducto, null);
        pnlTitulo.add(lblBusqueda, null);
        ctpContenido.add(pnBusquedaProducto, null);

        pnBusquedaProducto.add(lblCantProd, null);
        pnBusquedaProducto.add(lblBuscarProducto, null);
        spnProducto.getViewport().add(tblProductos, null);
        ctpContenido.add(spnProducto, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);
    }
    
    private void initialize() {
        initListaProductos();
    }

    void initListaProductos() {

        tblModelProductos =
                new FarmaTableModel(ConstantsVentas.columnsListaProductos, ConstantsVentas.defaultValuesListaProductos,
                                    0);
        tblModelProductos.data = VariablesVentas.tableModelListaGlobalProductos.data;
        FarmaUtility.initSelectList(tblProductos, tblModelProductos,
                                    ConstantsVentas.columnsListaProductos);
        Collections.sort(tblModelProductos.data,
                         new FarmaTableComparator(2, true));
        tblProductos.getSelectionModel().setSelectionInterval (0, 0);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        if(tblModelProductos.getRowCount()==0){
            muestraMsj("El producto seleccionado no tiene productos.");
            procesoRealizado=true;
            cerrarVentana();
        }
        FarmaUtility.moveFocus(txtProducto);
        lblProducto.setText(VarCotizacionPrecio.vProducto);
        lblLaboratorio.setText(VarCotizacionPrecio.vLaboratorio);
        lblUnidad.setText(VarCotizacionPrecio.vUnidad);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void cerrarVentana() {
       /* DlgCotizaVerDetalle dlgCotiza=new DlgCotizaVerDetalle(myParentFrame,"",true,VarCotizacionPrecio.vCod_Solic);
        if(procesoRealizado){
            this.setVisible(false);
            this.dispose();
        }else{
            String msj = "No ha seleccionado ningún producto.\n ¿Desea salir?";
            boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
            if (ok) {
                this.setVisible(false);
                this.dispose();
            }        
        }
        dlgCotiza.repaint();*/
        
       if(procesoRealizado){
           this.setVisible(false);
           this.dispose();
       }else{
           String msj = "No ha seleccionado ningún producto.\n ¿Desea salir?";
           boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
           if (ok) {
               this.setVisible(false);
               this.dispose();
           }    
       }

    }
    private void txtProducto_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblProductos, txtProducto, INDEX_DESC_PROD);
        /*** INICIO CCASTILLO 28/08/2017 ***/
        /*if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //e.consume();
            boolean buscaXcodigo=false;           
            String txtAux=txtProducto.getText().trim();
            if(txtAux.length()<=20){
                String codProd=UtilityFraccion.buscaCodProd_CodBarra(txtAux);
                if(codProd!=null){
                    txtAux=codProd;
                    buscaXcodigo=true;
                }else{
                    buscaXcodigo=verificaDigitos(txtAux);
                }
            }            
            if(buscaXcodigo){
                buscaCodigoEnJtable(txtAux);
                int vFila = tblProductos.getSelectedRow();
                String codAux = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 0);
                if(!txtAux.equalsIgnoreCase("") && !txtAux.equalsIgnoreCase(codAux)){
                    FarmaUtility.showMessage(this, "Codigo no encontrado", txtProducto);
                    FarmaUtility.moveFocus(txtProducto);
                    txtProducto.setText("");
                }
            }
            verificaIngreso();
        }*/
        /*** FIN CCASTILLO 28/08/2017 ***/
        chkKeyPressed(e);
    }
    
    private void buscaCodigoEnJtable(String pCodBusqueda) {
        String codTmp = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            codTmp = FarmaUtility.getValueFieldJTable(tblProductos, i, 0);
            if (codTmp.equalsIgnoreCase(pCodBusqueda)) {
                FarmaGridUtils.showCell(tblProductos, i, 0);
                FarmaUtility.setearRegistro(tblProductos, i, txtProducto, 1);
                FarmaUtility.moveFocus(txtProducto);
                return;
            }
        }
    }
    
    private boolean verificaDigitos(String txtAux) {
       boolean noCod=true;
        for(int i=0;i<txtAux.length();i++){
            String cChar =txtAux.substring(i, i+1);
            try{
                if(Integer.parseInt(cChar)>=0){
                    log.info("NUMERO");
                }
            }catch(Exception e){
                log.info("***CARACTER**");
                noCod=false;
                break;
            }
        }
        return noCod;
    }
    
    private void txtProducto_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblProductos, txtProducto, INDEX_DESC_PROD);
    }

    private void tblProductos_mouseClicked(MouseEvent e) {
        int i = tblProductos.getSelectedRow();
        txtProducto.setText(FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i, INDEX_DESC_PROD));
        FarmaUtility.moveFocus(txtProducto);
    }

    private void grabaSolicitud_Fraccionamiento() {
        String msj="Confirma que desea grabar los datos como nueva solicitud ";
        boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
        if(ok){
            ArrayList<ArrayList> okSave = UtilityFraccion.grabaNuevaSolicitud_Fracionamiento();
            if (okSave.size() == 0) {
                try{
                    String msjAtencion=UtilityFraccion.atenderSolicitudes_Pendientes();
                    if(msjAtencion==null){
                        FarmaUtility.showMessage(this, "Se grabaron correctamento todos los datos", null);
                    }else{
                        FarmaUtility.showMessage(this, msjAtencion, null);
                    }
                }catch(Exception e ){
                    FarmaUtility.showMessage(this, "Se produjo un error al enviar la solicitud a matriz!", null);
                    e.printStackTrace();
                }
                cerrarVentana();
            } else {
                if (okSave.size() == 1) {
                    String detError = okSave.get(0).get(0).toString();
                    String codError = okSave.get(0).get(1).toString();
                    FarmaUtility.showMessage(this,
                                             "Se produjo errores en el proseso de grabar los datos\nIntentelo despues." +
                                             "\n(" + detError + "-" + codError+")", null);
                } else {
                    String cad="Errores:\n";
                    for(int i=0;i<okSave.size();i++){
                        for(int j=0;j<okSave.get(i).size();j++){
                            System.out.println("==> "+okSave.get(i).get(j));
                            cad+=okSave.get(i).get(j)+" ";
                        }
                        cad+="\n";
                    }
                    FarmaUtility.showMessage(this,
                                             "Se produjo multiples errores en el proseso de grabar los datos\n" +cad+
                                             "\nIntentelo despues.",
                                             txtProducto);
                }
                cerrarVentana();
            }
        }
    }

    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
        case KeyEvent.VK_ESCAPE: cerrarVentana();break;
        case KeyEvent.VK_F11:
            seleccionarProducto();
            break;
        }
    }

    private void cargaRelacionProductos() {
        try {
            BDCotizacionPrecio.cargaListaProductosSustitutos(tblModelProductos, VarCotizacionPrecio.vCod_Prod);
        } catch (SQLException sqlException) {
            log.error(null, sqlException);
            FarmaUtility.showMessage(this, "Error al Listar Productos Sustitutos.\n" +
                    sqlException, txtProducto);
        }
    }

    private void seleccionarProducto() {
        String txt=txtProducto.getText().trim();
        if(!txt.equalsIgnoreCase("")){
            int i = tblProductos.getSelectedRow();
            String codProducto=FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i,INDEX_COD_PROD/*ConstantesCotiza.INDEX_COD_PROD*/);
            String descProd=FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i,INDEX_DESC_PROD/*ConstantesCotiza.INDEX_PRODUCTO*/);
            String unidad=FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i,INDEX_UNIDAD/*ConstantesCotiza.INDEX_UNIDAD*/);
            String laboratorio=FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i,INDEX_LABORATORIO/*ConstantesCotiza.INDEX_LABORATORIO*/);
            String precio=FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i,INDEX_PRECIO);
            if(txt.trim().equalsIgnoreCase(descProd)){
                String msj = "Se envía la solicitud de cambio del producto:\n"+VarCotizacionPrecio.vCod_Prod+"-"
                             +VarCotizacionPrecio.vProducto+"\nen "+VarCotizacionPrecio.vUnidad+"\ndel laboratorio: "
                             +VarCotizacionPrecio.vLaboratorio+"\n\nPor: "+codProducto+" "
                             +descProd+"\nen "+unidad+"\ndel laboratorio: "+laboratorio+"\n\n ¿Está seguro de enviar la solicitud?";
              
                  String    pDatosProductoSolicitud=
                //unidadOriginal@cantidadOriginal@cantidadPorCambiar@precio@cantidadPorCambiar@precio
                      VarCotizacionPrecio.vUnidad+"@"+VarCotizacionPrecio.vCant_Solic+"@"+VarCotizacionPrecio.vValPrecVta+"@"+unidad+"@"+1+"@"+precio;
                             
                boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
                if (ok) {
                    procesoRealizado=true;
                    //String rpta=UtilCotizacion.cambiarProducto_Cotizacion(codProducto);
                    FacadeCotizacionPrecio f = new FacadeCotizacionPrecio();
                    String rpta=f.enviarSolicitudDeCambioProducto(FarmaVariables.vCodGrupoCia,
                                                                FarmaVariables.vCodLocal,
                                                                VarCotizacionPrecio.vCod_Solic,
                                                                VarCotizacionPrecio.vCod_Prod,
                                                                codProducto,
                                                                pDatosProductoSolicitud);
                    if(rpta!=null && !rpta.equalsIgnoreCase("") && !rpta.equalsIgnoreCase("Error")){
                        muestraMsj("La solicitud de cambio de producto para cotizacion de competencia se ha enviado con exito.");
                    }else{
                        muestraMsj("Se produjo un error al enviar la solicitud de cambio de producto." +
                            "\nIntentelo mas tarde");
                    }
                    cerrarVentana();
                }
            }else{
                muestraMsj("El texto ingresado no corresponde al producto selecionado. Verifique");
                FarmaUtility.moveFocus(txtProducto);
            }
        }else{
            muestraMsj("Ingrese la descripcion y o codigo de producto a reemplazar");
        }
    }

    private void muestraMsj(String msj) {
        FarmaUtility.showMessage(this, msj, null);
    }

    private void verificaIngreso() {
        int i = tblProductos.getSelectedRow();
        String codProducto=FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i, 0);
        String descProd=FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i, 1);
        String txt=txtProducto.getText().trim();
        if(!txt.equalsIgnoreCase("")&&!txt.equalsIgnoreCase(descProd)){
            muestraMsj("El producto registrado no existe en la lista");
            FarmaUtility.moveFocus(txtProducto);
            txtProducto.setText("");           
        }
    }

    private void lblBusqueda_ActionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtProducto);
    }

    private void lblBusquedad_ComponenRemoved(ContainerEvent e) {
        FarmaUtility.moveFocus(txtProducto);
    }
}
