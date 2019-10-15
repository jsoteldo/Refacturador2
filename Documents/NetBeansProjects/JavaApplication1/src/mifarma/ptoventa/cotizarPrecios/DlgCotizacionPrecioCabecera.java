package mifarma.ptoventa.cotizarPrecios;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JLabelWhite;
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

import java.io.File;

import java.io.PrintStream;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.inventario.DlgListaCompetencias;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCotizacionPrecioCabecera extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgCotizacionPrecioCabecera.class);

    Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JPanel pnlWhite = new JPanel();

    private JTextFieldSanSerif txtNroGuia = new JTextFieldSanSerif();
    private JComboBox cmbTipo = new JComboBox();
    private JTextFieldSanSerif txtFechaCotizacion = new JTextFieldSanSerif();
    private JButton btnFechaCotizacion = new JButton();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private JLabelOrange lblNombre = new JLabelOrange();
    private JButton btnRuc = new JButton();
    private JTextFieldSanSerif txtNombre = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtRuc = new JTextFieldSanSerif();
    private JLabel lblNroDoc_T = new JLabel();
    private JTextFieldSanSerif txtSerieDoc = new JTextFieldSanSerif();
    private JLabelOrange jLabelOrange1 = new JLabelOrange();
    private JPanelWhite jPanelImagen = new JPanelWhite();
    private JButton btnDocumento = new JButton();
    private JButton btnTipoProceso = new JButton();
    private JButton btnImagen = new JButton();
    private JTextFieldSanSerif txtImagen = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtTipoProceso = new JTextFieldSanSerif();
    //1-compra,2-cotiza, 3-sin solicitud
    private String tipoProceso = "";
    private String nombreTienda = "";
    private String nroRUC = "";
    private String fechaCreacion = "";
    private String tipoComprobante = "";
    private String nroComprobante = "";
    private String nroSolicitud = "";
    private JLabel lblEnter= new JLabel();
    String descripcionProceso="";
    boolean llamoDetalle=false;
    String fechaSolicitud;

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgCotizacionPrecioCabecera() {
        this(null, "", false);
    }

    public DlgCotizacionPrecioCabecera(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(411, 282));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Cotización por Competencia - Cabecera");
        this.setDefaultCloseOperation(0);
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
        jContentPane.setSize(new Dimension(405, 229));
        pnlWhite.setBounds(new Rectangle(10, 10, 385, 90));
        pnlWhite.setBackground(Color.white);
        pnlWhite.setLayout(null);
        pnlWhite.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        
        txtNroGuia.setBounds(new Rectangle(285, 35, 60, 20));
        txtNroGuia.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNroGuia_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtNroGuia_keyTyped(e);
            }
        });
        
        cmbTipo.setBounds(new Rectangle(125, 35, 105, 20));
        cmbTipo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipo_keyPressed(e);
            }
        });
        
        txtFechaCotizacion.setBounds(new Rectangle(125, 10, 105, 20));
        txtFechaCotizacion.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaCotizacion_keyPressed(e);
            }
            public void keyReleased(KeyEvent e) {
                txtFechaCotizacion_keyReleased(e);
            }
        });
        
        btnFechaCotizacion.setText("Fecha :");
        btnFechaCotizacion.setBounds(new Rectangle(10, 10, 100, 20));
        btnFechaCotizacion.setBackground(new Color(255, 130, 14));
        btnFechaCotizacion.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnFechaCotizacion.setBorderPainted(false);
        btnFechaCotizacion.setContentAreaFilled(false);
        btnFechaCotizacion.setDefaultCapable(false);
        btnFechaCotizacion.setFocusPainted(false);
        btnFechaCotizacion.setFont(new Font("SansSerif", 1, 11));
        btnFechaCotizacion.setForeground(new Color(255, 130, 14));
        btnFechaCotizacion.setHorizontalAlignment(SwingConstants.LEFT);
        btnFechaCotizacion.setMnemonic('f');
        btnFechaCotizacion.setRequestFocusEnabled(false);
        btnFechaCotizacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnFechaCotizacion_actionPerformed(e);
            }
        });
        
        lblF1.setText("[ F1 ] Adjuntar Documento");
        lblF1.setBounds(new Rectangle(40, 230, 160, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(210, 230, 90, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(305, 230, 90, 20));
        jPanelWhite1.setBounds(new Rectangle(10, 105, 385, 70));
        jPanelWhite1.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        lblNombre.setText("Nombre Tienda :");
        lblNombre.setBounds(new Rectangle(10, 60, 100, 20));
        btnRuc.setText("RUC :");
        btnRuc.setBounds(new Rectangle(10, 35, 100, 20));
        btnRuc.setBackground(new Color(255, 130, 14));
        btnRuc.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRuc.setBorderPainted(false);
        btnRuc.setContentAreaFilled(false);
        btnRuc.setDefaultCapable(false);
        btnRuc.setFocusPainted(false);
        btnRuc.setFont(new Font("SansSerif", 1, 11));
        btnRuc.setForeground(new Color(255, 130, 14));
        btnRuc.setHorizontalAlignment(SwingConstants.LEFT);
        btnRuc.setRequestFocusEnabled(false);
        btnRuc.setMnemonic('R');
        
        txtRuc.setBounds(new Rectangle(125, 35, 145, 20));
        txtRuc.setEnabled(false);
        txtRuc.setDisabledTextColor(Color.black);
        txtRuc.setLengthText(11);
        txtRuc.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtRuc_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtRuc_keyTyped(e);
            }
        });
        
        lblEnter.setText("[Enter] Buscar");
        lblEnter.setBounds(new Rectangle(275, 35, 100, 20));
        lblEnter.setFont(new Font("SansSerif", 1, 11));
        lblEnter.setForeground(new Color(255, 130, 14));
        lblEnter.setVisible(false);
        
        
        txtNombre.setBounds(new Rectangle(125, 60, 215, 20));
        txtNombre.setEnabled(false);
        txtNombre.setDisabledTextColor(Color.black);
        txtNombre.setLengthText(20);
        txtNombre.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNombre_keyPressed(e);
            }

        });
        
        btnTipoProceso.setText("Tipo Proceso :");
        btnTipoProceso.setBounds(new Rectangle(10, 10, 105, 20));
        btnTipoProceso.setBackground(new Color(255, 130, 14));
        btnTipoProceso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnTipoProceso.setBorderPainted(false);
        btnTipoProceso.setContentAreaFilled(false);
        btnTipoProceso.setDefaultCapable(false);
        btnTipoProceso.setFocusPainted(false);
        btnTipoProceso.setFont(new Font("SansSerif", 1, 11));
        btnTipoProceso.setForeground(new Color(255, 130, 14));
        btnTipoProceso.setHorizontalAlignment(SwingConstants.LEFT);
        btnTipoProceso.setMnemonic('t');
        btnTipoProceso.setRequestFocusEnabled(false);
        btnTipoProceso.setActionCommand("Tipo Proceso:");
        
        txtTipoProceso.setBounds(new Rectangle(125, 10, 145, 20));
        txtTipoProceso.setEnabled(false);
        txtTipoProceso.setDisabledTextColor(Color.black);
        
        txtImagen.setBounds(new Rectangle(210, 10, 150, 20));
        txtImagen.setLengthText(15);
        txtImagen.setEditable(false);
        txtImagen.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
              txtImagen_keyPressed(e);
            }
        });

        btnDocumento.setText("Sustento :");
        btnDocumento.setBounds(new Rectangle(10, 10, 100, 15));
        btnDocumento.setBackground(new Color(255, 130, 14));
        btnDocumento.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDocumento.setBorderPainted(false);
        btnDocumento.setContentAreaFilled(false);
        btnDocumento.setDefaultCapable(false);
        btnDocumento.setFocusPainted(false);
        btnDocumento.setFont(new Font("SansSerif", 1, 11));
        btnDocumento.setForeground(new Color(255, 130, 14));
        btnDocumento.setHorizontalAlignment(SwingConstants.LEFT);
        btnDocumento.setMnemonic('s');
        btnDocumento.setRequestFocusEnabled(false);
        btnDocumento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDocumento_actionPerformed(e);
            }
        });
        
        this.btnImagen.setText("Adjuntar");
        this.btnImagen.setBounds(new Rectangle(125, 10, 85, 20));
            btnImagen.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        btnImagen_actionPerformed(e);
                    }

            });
            btnImagen.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        btnImagen_keyPressed(e);
                    }

            });
        jPanelImagen.setBounds(new Rectangle(10, 180, 385, 40));
        jPanelImagen.setBackground(Color.white);
        jPanelImagen.setLayout(null);
        jPanelImagen.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        jPanelImagen.setVisible(true);

        pnlWhite.add(txtTipoProceso, null);
        pnlWhite.add(btnTipoProceso, null);
        pnlWhite.add(txtNombre, null);
        pnlWhite.add(lblNombre, null);
        pnlWhite.add(txtRuc, null);
        pnlWhite.add(btnRuc, null);

        pnlWhite.add(lblEnter, null);
        jPanelWhite1.add(txtFechaCotizacion, null);
        jPanelWhite1.add(btnFechaCotizacion, null);
        jPanelWhite1.add(txtNroGuia, null);
        jPanelWhite1.add(jLabelOrange1, null);
        jPanelWhite1.add(txtSerieDoc, null);
        jPanelWhite1.add(cmbTipo, null);
        jPanelWhite1.add(lblNroDoc_T, null);
        jContentPane.add(jPanelWhite1, null);
        jContentPane.add(lblF1, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(pnlWhite, null);
        jContentPane.add(jPanelImagen, null);
        txtFechaCotizacion.setLengthText(10);
        txtSerieDoc.setLengthText(4);
        txtNroGuia.setLengthText(8);
       

        lblNroDoc_T.setText("Nro. Documento :");
        lblNroDoc_T.setBounds(new Rectangle(10, 35, 145, 20));
        lblNroDoc_T.setFont(new Font("SansSerif", 1, 11));
        lblNroDoc_T.setForeground(new Color(255, 130, 14));
        txtSerieDoc.setBounds(new Rectangle(235, 35, 35, 20));
        txtSerieDoc.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtSerieDoc_keyPressed(e);
            }
        });
        
        jLabelOrange1.setText("-");
        jLabelOrange1.setBounds(new Rectangle(270, 35, 15, 20));
        jLabelOrange1.setFont(new Font("SansSerif", 1, 25));
        jLabelOrange1.setHorizontalAlignment(SwingConstants.CENTER);

        jPanelImagen.add(txtImagen, null);
        jPanelImagen.add(btnDocumento, null);
        jPanelImagen.add(btnImagen, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */
    private void initialize() {
        initCmbTipo();
        initDatos();

        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */
    private void initCmbTipo() {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbTipo, ConstantsInventario.NOM_HASHTABLE_CMBTIPO,
                                   "PTOVENTA_INV.INV_GET_TIPO_DOCUMENTO(?)", parametros, false, true);
        parametros = null;
    }

    private void initDatos() {
        //txtFechaCotizacion.setText(VariablesInventario.vFecGuia);
        txtFechaCotizacion.setText(VarCotizacionPrecio.vFecha_Cotizacion);
        if(!VarCotizacionPrecio.vTipo_Doc.isEmpty()){
            cmbTipo.setSelectedIndex(Integer.parseInt(VarCotizacionPrecio.vTipo_Doc));
        }else{
            cmbTipo.setSelectedIndex(-1);
        }
        txtSerieDoc.setText(VarCotizacionPrecio.vSerieDoc);
        txtNroGuia.setText(VarCotizacionPrecio.vNroGuia);
        txtImagen.setText(VarCotizacionPrecio.vRuta_Archivo);
        
        //AOVIEDO EVALUAR SI QUITAR
        /*txtNombre.setText(VariablesInventario.vNombreTienda);
        txtRuc.setText(VariablesInventario.vRucTienda);*/
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtFechaCotizacion);
        
        if(this.getTipoProceso().equals("1")){
                descripcionProceso="Comprar";
            }
        else if(this.getTipoProceso().equals("2")){
                descripcionProceso="Cotizar";
            }
        else if(this.getTipoProceso().equals("3")){
                descripcionProceso="Cotizar Sin Solicitud";
                FarmaUtility.moveFocus(txtRuc);
                lblEnter.setVisible(true);
                txtNombre.setEnabled(true);
                txtRuc.setEnabled(true);
            }
        txtTipoProceso.setText(descripcionProceso);
        txtNombre.setText(this.getNombreTienda());
        txtRuc.setText(this.getNroRUC());
    }
    
    private void btnDocumento_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(btnImagen);
    }

    private void btnFechaCotizacion_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFechaCotizacion);
    }

    private void txtFechaCotizacion_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            FarmaUtility.moveFocus(cmbTipo);
        else
            chkKeyPressed(e);
    }

    private void txtFechaCotizacion_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaCotizacion, e);
    }

    private void cmbTipo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            FarmaUtility.moveFocus(txtSerieDoc);
        else
            chkKeyPressed(e);
    }

    private void txtSerieDoc_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtNroGuia);
            txtSerieDoc.setText(FarmaUtility.completeWithSymbol(txtSerieDoc.getText(), 3, "0", "I"));
        } else
            chkKeyPressed(e);
    }

    private void txtNroGuia_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(btnImagen);
            txtNroGuia.setText(FarmaUtility.completeWithSymbol(txtNroGuia.getText(), 8, "0", "I"));
        } else
            chkKeyPressed(e);
    }

    private void txtNroGuia_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtNroGuia, e);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */
    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F1(e)) {
            cargarArchivo();
        }
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (validarCampos()) {
                if (validaCotizacion()) {
                    txtSerieDoc.setText(FarmaUtility.completeWithSymbol(txtSerieDoc.getText(), 3, "0", "I"));
                    txtNroGuia.setText(FarmaUtility.completeWithSymbol(txtNroGuia.getText(), 7, "0", "I"));
                   
                    cargarDatos();
                   
                   if(!llamoDetalle){
                    DlgCotizacionPrecioDetalle dlgCotizacionPrecioDetalle = new DlgCotizacionPrecioDetalle(myParentFrame, "", true, getTipoProceso());
                    dlgCotizacionPrecioDetalle.setFechaCreacion(getFechaCreacion());
                    dlgCotizacionPrecioDetalle.setTipoProceso(getTipoProceso());
                    dlgCotizacionPrecioDetalle.setTipoComprobante(getTipoComprobante());
                    dlgCotizacionPrecioDetalle.setNroComprobante(getNroComprobante());
                    dlgCotizacionPrecioDetalle.setNroSolicitud(getNroSolicitud());
                     dlgCotizacionPrecioDetalle.setNombreTienda(nombreTienda);
                    dlgCotizacionPrecioDetalle.setNroRUC(nroRUC);
                    dlgCotizacionPrecioDetalle.setVisible(true);
                    }
                    FarmaVariables.vAceptar=true;
                   cerrarVentana(true);
                   
                } else {
                    FarmaUtility.showMessage(this, "Ya existe una Cotización con el mismo número de documento ingresado.", txtSerieDoc);
                    txtSerieDoc.selectAll();
                    txtNroGuia.selectAll();
                    FarmaUtility.moveFocus(txtSerieDoc);
                }
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                cerrarVentana(false);
            }
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
    private boolean validarCampos() {
        boolean retorno = true;

        if(this.getTipoProceso().equals("1")){
        
            if (txtFechaCotizacion.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe ingresar la Fecha del Documento.", txtFechaCotizacion);
                retorno = false;
            } else if (!FarmaUtility.validaFecha(txtFechaCotizacion.getText(), "dd/MM/yyyy")) {
                FarmaUtility.showMessage(this, "Ingrese la fecha correctamente.", txtFechaCotizacion);
               FarmaUtility.obtiene_fecha(txtFechaCotizacion.getText(), "dd/MM/yyyy");
                retorno = false;
            }/*else if((FarmaUtility.validaFecha(txtFechaCotizacion.getText(), "dd/MM/yyyy")) && FarmaUtility.validaFecha(getFechaSolicitud(), "dd/MM/yyyy")){
                Date fechaCotiza=FarmaUtility.obtiene_fecha(txtFechaCotizacion.getText(),"00:00:00");
                Date fechaSolicitud=FarmaUtility.obtiene_fecha(getFechaSolicitud(),"00:00:00") ;
                if( fechaCotiza.before(fechaSolicitud)){
                FarmaUtility.showMessage(this, "La fecha del documento no debe ser menor a la solicitada", txtFechaCotizacion);
                retorno = false;
                    }
            }*/ else if (cmbTipo.getSelectedIndex() == 0 || cmbTipo.getSelectedIndex() == -1) {
                FarmaUtility.showMessage(this, "Debe indicar el Tipo de Documento.", cmbTipo);
                retorno = false;
            } else if (txtSerieDoc.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe ingresar la Serie del Documento.", txtSerieDoc);
                retorno = false;
            } else if (txtNroGuia.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe ingresar el Número del Documento.", txtNroGuia);
                retorno = false;
            } else if (VariablesInventario.vFileImagen == null){
                FarmaUtility.showMessage(this, "Debe adjuntar el sustento", btnImagen);
                retorno = false;
            } else {

            if (!validaFechaIngresoCC())
                retorno = false;
                
            if((FarmaUtility.validaFecha(txtFechaCotizacion.getText(), "dd/MM/yyyy")) && FarmaUtility.validaFecha(getFechaSolicitud(), "dd/MM/yyyy")){
                   Date fechaCotiza=FarmaUtility.obtiene_fecha(txtFechaCotizacion.getText(),"00:00:00");
                   Date fechaSolicitud=FarmaUtility.obtiene_fecha(getFechaSolicitud(),"00:00:00") ;
                   if( fechaCotiza.before(fechaSolicitud)){
                   FarmaUtility.showMessage(this, "La fecha del documento no debe ser menor a la solicitada", txtFechaCotizacion);
                   retorno = false;
                       }
                }
            }
        } else if(this.getTipoProceso().equals("2")){
            if (txtFechaCotizacion.getText().trim().length() > 0) {
                if (!FarmaUtility.validaFecha(txtFechaCotizacion.getText(), "dd/MM/yyyy")) {
                    FarmaUtility.showMessage(this, "Ingrese la fecha correctamente.", txtFechaCotizacion);
                    retorno = false;
                }
            }
            
            if (txtSerieDoc.getText().trim().length() > 0 && txtNroGuia.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe ingresar el Número del Documento.", txtNroGuia);
                retorno = false;
            } else if (txtSerieDoc.getText().trim().length() == 0 && txtNroGuia.getText().trim().length() > 0) {
                FarmaUtility.showMessage(this, "Debe ingresar la Serie del Documento.", txtSerieDoc);
                retorno = false;
            }  else if (VariablesInventario.vFileImagen != null && txtSerieDoc.getText().trim().length() == 0  ) {
                FarmaUtility.showMessage(this, "Ingrese el número de comprobante", txtSerieDoc);
                retorno = false;
            }else if (VariablesInventario.vFileImagen != null && (cmbTipo.getSelectedIndex() == 0 || cmbTipo.getSelectedIndex() == -1) ) {
                FarmaUtility.showMessage(this, "Ingrese el tipo de documento", cmbTipo);
                retorno = false;
            }
            
            if (!validaFechaIngresoCC())
                retorno = false;
        }else if(this.getTipoProceso().equals("3")){
             
            if (txtRuc.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe ingresar el RUC", txtFechaCotizacion);
                FarmaUtility.moveFocus(txtRuc);
                retorno = false;
            } else if (txtNombre.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Ingrese el nombre del competidor", txtFechaCotizacion);
                FarmaUtility.moveFocus(txtNombre);
                retorno = false;
            }  else if (!FarmaUtility.validaFecha(txtFechaCotizacion.getText(), "dd/MM/yyyy") && txtFechaCotizacion.getText().trim().length() > 0) {
                FarmaUtility.showMessage(this, "Ingrese la fecha correctamente.", txtFechaCotizacion);
                retorno = false;
           }  else if (VariablesInventario.vFileImagen != null && txtSerieDoc.getText().trim().length() == 0  ) {
                FarmaUtility.showMessage(this, "Ingrese el número de comprobante", txtSerieDoc);
                retorno = false;
            }else if (VariablesInventario.vFileImagen != null && (cmbTipo.getSelectedIndex() == 0 || cmbTipo.getSelectedIndex() == -1) ) {
                FarmaUtility.showMessage(this, "Ingrese el tipo de documento", cmbTipo);
                retorno = false;
            }
        }

        return retorno;
    }

    private void cargarDatos() {
        this.fechaCreacion = txtFechaCotizacion.getText().trim();
        this.nroComprobante = txtSerieDoc.getText().trim() + txtNroGuia.getText().trim();
        VarCotizacionPrecio.vFecha_Cotizacion = txtFechaCotizacion.getText().trim();
        VarCotizacionPrecio.vNum_Doc = txtSerieDoc.getText().trim() + txtNroGuia.getText().trim();
        VarCotizacionPrecio.vSerieDoc = txtSerieDoc.getText().trim();
        VarCotizacionPrecio.vNroGuia = txtNroGuia.getText().trim();
        //this.tipoProceso = txtTipoProceso.getText().equals("1") ? "Con Documento" : "Sin Documento";
        if(cmbTipo.getSelectedIndex() > 0){
            this.tipoComprobante = FarmaLoadCVL.getCVLCode(ConstantsInventario.NOM_HASHTABLE_CMBTIPO, cmbTipo.getSelectedIndex());
            VarCotizacionPrecio.vTipo_Doc = FarmaLoadCVL.getCVLCode(ConstantsInventario.NOM_HASHTABLE_CMBTIPO, cmbTipo.getSelectedIndex());
            VarCotizacionPrecio.vDesc_Doc = FarmaLoadCVL.getCVLDescription(ConstantsInventario.NOM_HASHTABLE_CMBTIPO, VarCotizacionPrecio.vTipo_Doc);
        }
        
      /*  if(this.tipoProceso.equals("1")){
            this.nroComprobante = txtSerieDoc.getText().trim() + txtNroGuia.getText().trim();
            VarCotizacionPrecio.vNum_Doc = txtSerieDoc.getText().trim() + txtNroGuia.getText().trim();
            VarCotizacionPrecio.vSerieDoc = txtSerieDoc.getText().trim();
            VarCotizacionPrecio.vNroGuia = txtNroGuia.getText().trim();
        } */if(this.tipoProceso.equals("3")){
            this.nombreTienda=txtNombre.getText();
            this.nroRUC = txtRuc.getText();
            VarCotizacionPrecio.vCompetencia=nombreTienda;
            VarCotizacionPrecio.vRUC_Comp=nroRUC;
            VarCotizacionPrecio.vCod_Solic="0000000000";
            
        }/*else  {
            if(!txtSerieDoc.getText().trim().isEmpty() && !txtNroGuia.getText().trim().isEmpty()){
                this.nroComprobante = txtSerieDoc.getText().trim() + txtNroGuia.getText().trim();
                VarCotizacionPrecio.vNum_Doc = txtSerieDoc.getText().trim() + txtNroGuia.getText().trim();
                VarCotizacionPrecio.vSerieDoc = txtSerieDoc.getText().trim();
                VarCotizacionPrecio.vNroGuia = txtNroGuia.getText().trim();
            }
        }*/
        VarCotizacionPrecio.vTipo_Origen = "04";
        VarCotizacionPrecio.vCod_Origen = FarmaVariables.vCodLocal;
        VarCotizacionPrecio.vTipo_Proceso = "02";
    }

    private boolean validaFechaIngresoCC() {
        String diaVenta = txtFechaCotizacion.getText().trim();
        
        if(!diaVenta.isEmpty()){
            if (!FarmaUtility.isFechaValida(this, diaVenta, "Ingrese una Fecha correcta para la generación de la Cotización")) {
                FarmaUtility.moveFocus(txtFechaCotizacion);
                return false;
            }   
        }
        
        return true;
    }

    /**
     * Valida si se repite la cotizacion
     * @author dubilluz
     * @since  12.11.2007
     */
    private boolean validaCotizacion() {
        String num_doc = txtSerieDoc.getText().trim() + txtNroGuia.getText().trim();
        String tipo_doc = "";
        if(cmbTipo.getSelectedIndex() > 0){
            tipo_doc = FarmaLoadCVL.getCVLCode(ConstantsInventario.NOM_HASHTABLE_CMBTIPO, cmbTipo.getSelectedIndex());
        }
        String ruc_empresa = txtRuc.getText().trim();

        try {
            String v_retorno = DBInventario.validaNumeroDocCompetencia(num_doc, tipo_doc, ruc_empresa);
            log.debug("¿El Número de Documento existe? : " + v_retorno);
            
            if (v_retorno.equalsIgnoreCase("S")) {
                cargaDatosCotizacion(num_doc, tipo_doc, ruc_empresa);
                return false;
            } else
                return true;
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al validar el número de documento de la cotización.", txtFechaCotizacion);
            return true;
        }
    }

    /**
     * Se carga los datos de la cotizacion que ya existe para mostrarla y saber si desea
     * anular y generarla
     * @author dubilluz
     * @since  26.11.2007
     */
    public void cargaDatosCotizacion(String num_doc, String tipo_doc, String ruc_empresa) {
        VariablesInventario.vArrayDatosCotizacion = new ArrayList();
        
        try {
            DBInventario.getDatosCotizacion(VariablesInventario.vArrayDatosCotizacion, num_doc, tipo_doc, ruc_empresa);
            log.debug("Se cargaron los datos de la cotización : " + VariablesInventario.vArrayDatosCotizacion);
        } catch (Exception e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al obtener los datos de la cotización.", txtNroGuia);
        }
    }
    
    private void txtImagen_keyPressed(KeyEvent keyEvent) {
    
    }
    
    private void btnImagen_actionPerformed(ActionEvent actionEvent) {
        if (JConfirmDialog.rptaConfirmDialog(this, "Está seguro de adjuntar sustento?")) {
            cargarArchivo();
        }
    }

    private void btnImagen_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if (JConfirmDialog.rptaConfirmDialog(this, "Está seguro de adjuntar sustento?")) {
            cargarArchivo();
            }
        } else
            chkKeyPressed(e);
    }

    private void cargarArchivo() {
        File lfFile = new File("C:\\");
        JFileChooser filechooser = new JFileChooser(lfFile);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes JPG, JPEG, GIF, BMP y PNG", "jpeg", "jpg","gif","bmp","png");
        filechooser.setFileFilter(filter);
        //filechooser.addChoosableFileFilter(filter);
        filechooser.setDialogTitle("Seleccione imagen");
        //desactiva todos los archivos
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (filechooser.showSaveDialog(this.myParentFrame) != JFileChooser.APPROVE_OPTION)
            return;
        File fileChoosen = filechooser.getSelectedFile();
        
        //String cade = String.valueOf(fileChoosen.length());
        //CALCULAR : http://es.calcuworld.com/informatica/calculadora-de-bytes/
        //tamaño en bytes
      long limiteByte= 0;

        try {
            limiteByte =Long.parseLong(BDCotizacionPrecio.getLimiteCargaDocumento()); //  Integer.parseInt(BDCotizacionPrecio.getLimiteCargaDocumento());
            if(fileChoosen.length()>0){
            if(fileChoosen.length() <= limiteByte){//500KB
                VariablesInventario.vFileImagen = fileChoosen;
                String ruta = fileChoosen.getAbsolutePath();
                FarmaUtility.showMessage(this, "Se adjuntó imagen sustento!!", null);
                txtImagen.setText(ruta);
                VarCotizacionPrecio.vRuta_Archivo = ruta;
            }else{
                FarmaUtility.showMessage(this,"No se cargó la imagen, excede el tamaño máximo.",btnImagen);
            }
            }else{
                FarmaUtility.showMessage(this,"Seleccionar una imagen valida",btnImagen);
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
       
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public String getTipoProceso() {
        return tipoProceso;
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
    
    private void buscaRucEmpresa() {
        String ruc_empresa = txtRuc.getText().trim();
        log.debug("buscando empresa por ruc " + ruc_empresa);
        ArrayList arrayEmpresa_busqueda = new ArrayList();
        if (ruc_empresa.length() > 0) {
            try {
                DBInventario.buscaEmpresa(arrayEmpresa_busqueda, ruc_empresa);
                log.debug("Resultado ArrayEmpresa_busqueda: " + arrayEmpresa_busqueda);
            } catch (SQLException sql) {
                log.error("", sql);
                FarmaUtility.showMessage(this, "Error al buscar la empresa por Ruc.", txtRuc);
            }
            if (arrayEmpresa_busqueda.size() != 0) {
                VariablesInventario.vDescripcionCompetencia =
                        ((String)((ArrayList)arrayEmpresa_busqueda.get(0)).get(0)).trim();
                VariablesInventario.vRucCompetencia =
                        ((String)((ArrayList)arrayEmpresa_busqueda.get(0)).get(1)).trim();
                txtNombre.setText(VariablesInventario.vDescripcionCompetencia);
                txtRuc.setText(VariablesInventario.vRucCompetencia);
            } else {
                FarmaUtility.showMessage(this, "La empresa con el RUC " + ruc_empresa + " no se encuentra registrada.",
                                         null);
                listaCompetencias();
            }
        } else
            listaCompetencias();
    }
    
    private void listaCompetencias() {
        log.debug("Variables de la Competencia: ");
        log.debug("Desc : " + VariablesInventario.vDescripcionCompetencia + " ");
        log.debug("RUC  : " + VariablesInventario.vRucCompetencia);
        DlgListaCompetencias dlgListaComp = new DlgListaCompetencias(myParentFrame, "", true);
        dlgListaComp.setVisible(true);
        
        if (FarmaVariables.vAceptar) {
            
            FarmaVariables.vAceptar = false;
            
            txtNombre.setText(VariablesInventario.vDescripcionCompetencia.trim());
            txtRuc.setText(VariablesInventario.vRucCompetencia.trim());
        }

    }
    
    private void txtRuc_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtNombre.getText().trim().length() > 0) {
                if (txtRuc.getText().trim().length() > 0)
                    FarmaUtility.moveFocus(txtFechaCotizacion);
                else
                    buscaRucEmpresa();
            } else
                buscaRucEmpresa();
        } else
            chkKeyPressed(e);
    }
    private void txtRuc_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtRuc, e);
    }

    private void txtNombre_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtNombre.getText().trim().length() > 0) {
                if (txtRuc.getText().trim().length() > 0)
                    FarmaUtility.moveFocus(txtFechaCotizacion);
                else
                    buscaRucEmpresa();
            } else
                buscaRucEmpresa();
        } else
            chkKeyPressed(e);
    }


    public void setLlamoDetalle(boolean llamoDetalle) {
        this.llamoDetalle = llamoDetalle;
    }

    public boolean isLlamoDetalle() {
        return llamoDetalle;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }
}

