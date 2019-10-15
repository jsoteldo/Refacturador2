 package mifarma.ptoventa.inventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgAjusteKardex extends JDialog {
    // **************************************************************************
    // Constructores
    // **************************************************************************
    private static final Logger log = LoggerFactory.getLogger(DlgAjusteKardex.class);

    Frame myParentFrame;

    FarmaTableModel tableModel;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanelWhite jContentPane = new JPanelWhite();

    private JPanelHeader pnlTitulos = new JPanelHeader();

    private JPanelTitle pnlMotivo = new JPanelTitle();

    private JLabelWhite lblProducto_T = new JLabelWhite();

    private JLabelWhite lblUnidadActual_T = new JLabelWhite();

    private JLabelWhite lblLaboratorio_T = new JLabelWhite();

    private JLabelWhite lblProducto = new JLabelWhite();

    private JLabelWhite lblUnidad = new JLabelWhite();

    private JLabelWhite lblLaboratorio = new JLabelWhite();

    private JButtonLabel btnMotivoAjuste = new JButtonLabel();

    private JLabelWhite lblStockModif_T = new JLabelWhite();

    private JTextFieldSanSerif txtStockModifEntero = new JTextFieldSanSerif();

    private JComboBox cmbMotivoAjuste = new JComboBox();

    private JLabelFunction lblEsc = new JLabelFunction();

    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelWhite lblGlosa_T = new JLabelWhite();
    private JTextFieldSanSerif txtGlosa = new JTextFieldSanSerif();
    private JLabelWhite lblStock_T = new JLabelWhite();
    private JLabelWhite lblStock = new JLabelWhite();
    private JLabelWhite lblUnidadVenta = new JLabelWhite();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();
    private JLabelWhite jLabelWhite2 = new JLabelWhite();
    private JTextFieldSanSerif txtStockFraccion = new JTextFieldSanSerif();
    
    private JPanelWhite pnlMsj = new JPanelWhite();
    private JScrollPane scrMsj = new JScrollPane();

    private String tipo_rep;
    private JPanelTitle pnlGlosa = new JPanelTitle();
    private JPanelTitle pnlDetalle1 = new JPanelTitle();
    private JPanelTitle pnlDetalle2 = new JPanelTitle();
    private JLabelWhite lblIngresoCant = new JLabelWhite();
    private JTextFieldSanSerif txtCantAjuste = new JTextFieldSanSerif();
    private JEditorPane txtMensaje = new JEditorPane();
    private int posX=15;
    private int posY=180;
    private int posX_Aux=15;
    private int posY_Aux=425;
    private int ancho=425;
    private int largo=50;
    private ArrayList<ArrayList> listaMsjMotivo=new ArrayList<ArrayList>();
    private String signoAjuste=null;
    private String txt="<div style=\"font-size: 12px\">\n"
               +"<b style=\"align-content: center;color: red\">MSJ</b><br>\n" +
               "</div>";
    private String mensaje="LA CANTIDAD A INGRESAR DEBE DE SER IGUAL AL STOCK FÍSICO DEL LOCAL";


    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    public DlgAjusteKardex() {
        this(null, "", false);
    }

    public DlgAjusteKardex(Frame parent, String title, boolean modal) {
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
    
    /*** INICIO CCASTILLO 26/12/2016 ***/
    private int indPedidoEsp=0; // la ventana es llamada desde los pedidos especiales insumos (1: SI , 0: NO)
    public DlgAjusteKardex(Frame parent, String title, boolean modal,int indPedidoEsp) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.indPedidoEsp=indPedidoEsp;
        try {
            jbInit();
            initialize();
            FarmaUtility.centrarVentana(this);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /*** FIN CCASTILLO 26/12/2016 ***/

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(476, 511));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ajuste de Producto");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlTitulos.setBounds(new Rectangle(15, 15, 425, 115));
        pnlMotivo.setBounds(new Rectangle(15, 135, 425, 45));
        lblProducto_T.setText("Producto:");
        lblProducto_T.setBounds(new Rectangle(20, 15, 90, 15));
        lblUnidadActual_T.setText("Unidad Actual:");
        lblUnidadActual_T.setBounds(new Rectangle(20, 40, 90, 15));
        lblLaboratorio_T.setText("Laboratorio:");
        lblLaboratorio_T.setBounds(new Rectangle(20, 65, 90, 15));
        lblProducto.setBounds(new Rectangle(120, 15, 300, 15));
        lblProducto.setFont(new Font("SansSerif", 0, 11));
        lblUnidad.setBounds(new Rectangle(120, 40, 295, 15));
        lblUnidad.setFont(new Font("SansSerif", 0, 11));
        lblLaboratorio.setBounds(new Rectangle(120, 65, 300, 15));
        lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
        btnMotivoAjuste.setText("Motivo de Ajuste:");
        btnMotivoAjuste.setBounds(new Rectangle(10, 10, 105, 20));
        btnMotivoAjuste.setMnemonic('M');
        btnMotivoAjuste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnMotivoAjuste_actionPerformed(e);
            }
        });
        lblStockModif_T.setText("Stock Mod :");
        lblStockModif_T.setBounds(new Rectangle(10, 15, 75, 20));
        txtStockModifEntero.setBounds(new Rectangle(215, 15, 45, 20));
        txtStockModifEntero.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtStockModif_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtStockModif_keyTyped(e);
            }
        });
        cmbMotivoAjuste.setBounds(new Rectangle(130, 10, 275, 20));
        cmbMotivoAjuste.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbMotivoAjuste_keyPressed(e);
            }
        });
        cmbMotivoAjuste.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    cmbMotivoAjuste_ItemStateChanged(e);
                }
            });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(350, 375, 90, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(245, 375, 90, 20));
        lblGlosa_T.setText("Glosa :");
        lblGlosa_T.setBounds(new Rectangle(5, 10, 90, 20));
        txtGlosa.setBounds(new Rectangle(60, 10, 355, 20));
        txtGlosa.setDocument(new FarmaLengthText(120));
        txtGlosa.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtGlosa_keyPressed(e);
            }
        });

        lblStock_T.setText("Stock:");
        lblStock_T.setBounds(new Rectangle(20, 90, 90, 15));
        lblStock.setBounds(new Rectangle(120, 90, 40, 15));
        lblStock.setFont(new Font("SansSerif", 0, 11));
        lblUnidadVenta.setBounds(new Rectangle(185, 90, 140, 15));
        lblUnidadVenta.setFont(new Font("SansSerif", 0, 11));
        jLabelWhite1.setText("Entero :");
        jLabelWhite1.setBounds(new Rectangle(155, 15, 55, 20));
        jLabelWhite2.setText("Fraccion");
        jLabelWhite2.setBounds(new Rectangle(280, 15, 55, 20));
        txtStockFraccion.setBounds(new Rectangle(340, 15, 45, 20));
        txtStockFraccion.setText("0");
        txtStockFraccion.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtStockFraccion_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtStockFraccion_keyTyped(e);
            }
        });
        pnlMotivo.add(cmbMotivoAjuste, null);
        pnlMotivo.add(btnMotivoAjuste, null);
        
        pnlMsj.setBounds(new Rectangle(15, 275, 430, 95));
        scrMsj.setBounds(new Rectangle(1, 1, 425, 90));
        scrMsj.setFont(new Font("SansSerif", 0, 11));
        scrMsj.setBackground(SystemColor.windowText);
        scrMsj.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrMsj.getViewport();
        /*
        pnlMsj.setBounds(new Rectangle(15, 275, 425, 90));
        pnlMsj.setBackground(Color.white);
        pnlMsj.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        */
        pnlGlosa.setBounds(new Rectangle(15, 230, 425, 45));
        pnlGlosa.add(txtGlosa, null);
        pnlGlosa.add(lblGlosa_T, null);
        
        lblIngresoCant.setText("Ingrese cantidad a ajustar");
        lblIngresoCant.setBounds(new Rectangle(10, 15, 155, 20));
        txtCantAjuste.setBounds(new Rectangle(165, 15, 70, 20));

        //txtMensaje.setEditable(false);
        txtCantAjuste.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCantAjuste_KeyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtCantAjuste_KeyTyped(e);
                }
            });
        txtMensaje.setContentType("text/html");
        txtMensaje.setAutoscrolls(true);
        txtMensaje.setEditable(false);
        /*
        //txtMensaje.setNextFocusableComponent(tblCabecera);
        txtMensaje.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtDetalle_KeyPressed(e);
                }
            });
        txtMensaje.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    txtDetalle_focusGained(e);
                }
            });
        */
        //pnlDetalle2.setBounds(new Rectangle(X, Y, ANCHO, LARGO));
        pnlDetalle1.setBounds(new Rectangle(15, 180, 425, 50));
        pnlDetalle1.add(txtStockModifEntero, null);
        pnlDetalle1.add(txtStockFraccion, null);
        pnlDetalle1.add(jLabelWhite2, null);
        pnlDetalle1.add(jLabelWhite1, null);
        pnlDetalle1.add(lblStockModif_T, null);
        
        pnlDetalle2.setBounds(new Rectangle(15, 425, 425, 50));
        pnlDetalle2.add(lblIngresoCant, null);
        pnlDetalle2.add(txtCantAjuste, null);
//        pnlDetalle2.setVisible(false);

        scrMsj.getViewport().add(txtMensaje, null);
        pnlMsj.add(scrMsj, null);
        jContentPane.add(pnlMsj, null);
        jContentPane.add(pnlDetalle1, null);
        jContentPane.add(pnlDetalle2, null);
        jContentPane.add(pnlGlosa, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(pnlMotivo, null);
        pnlTitulos.add(lblUnidadVenta, null);
        pnlTitulos.add(lblStock, null);
        pnlTitulos.add(lblStock_T, null);
        pnlTitulos.add(lblLaboratorio, null);
        pnlTitulos.add(lblUnidad, null);
        pnlTitulos.add(lblProducto, null);
        pnlTitulos.add(lblLaboratorio_T, null);
        pnlTitulos.add(lblUnidadActual_T, null);
        pnlTitulos.add(lblProducto_T, null);
        jContentPane.add(pnlTitulos, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initialize() {
        initCmbMotivoAjuste();
    };

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void btnMotivoAjuste_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbMotivoAjuste);
    }

    private void btnCancelar_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    private void txtFracModif_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void txtStockModif_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtStockFraccion.isEnabled())
                FarmaUtility.moveFocus(txtStockFraccion);
            else
                FarmaUtility.moveFocus(txtGlosa);
        } else {
            chkKeyPressed(e);
        }
    }
    
    private void this_windowOpened(WindowEvent e) {
        String msjInfo=txt;
        this.setSize(new Dimension(476, 440));
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(cmbMotivoAjuste);
        if (VariablesInventario.vCFraccion.equalsIgnoreCase("1")) {
            txtStockFraccion.setEnabled(false);
            txtStockFraccion.setText("0");
            txtMensaje.setText(msjInfo.replaceAll("MSJ", mensaje));
        }
        mostrarDatos();
    }
    
    private void cmbMotivoAjuste_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String codMotivo=FarmaLoadCVL.getCVLCode("cmbMotivoAjuste",cmbMotivoAjuste.getSelectedIndex());
            visualizarMensajeMotivoAjuste(codMotivo);
            if(codMotivo.equalsIgnoreCase("526")){
                FarmaUtility.moveFocus(txtCantAjuste);
            }else{
                FarmaUtility.moveFocus(txtStockModifEntero);
            }
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtStockModif_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtStockModifEntero, e);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            validaIngresoStock();
            /*  if( FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)){// 08.08.2014 rherrera
                        FarmaUtility.showMessage(this,"ERROR: LA CANTIDAD A INGRESAR DEBE SER MENOR AL STOCK DEL SISTEMA",txtStockModifEntero);
                }else { */

            if (datosValidados())
                if (JConfirmDialog.rptaConfirmDialog(this, "¿Está seguro que desea grabar el ajuste?")) {
                    try {
                        insertarAjusteKardex();
                        FarmaUtility.aceptarTransaccion();
                        String codMotivo=FarmaLoadCVL.getCVLCode("cmbMotivoAjuste",cmbMotivoAjuste.getSelectedIndex());
                        int entero =0;
                        int fraccion =0;
                        if(codMotivo.equalsIgnoreCase("526")){
                            if(signoAjuste.equalsIgnoreCase("-")){
                                entero = VariablesInventario.vStock-Integer.parseInt(txtCantAjuste.getText().trim());
                            }
                            if(signoAjuste.equalsIgnoreCase("+")){
                                entero = VariablesInventario.vStock+Integer.parseInt(txtCantAjuste.getText().trim());
                            }
                        }else{
                            entero = Integer.parseInt(txtStockModifEntero.getText().trim());
                            fraccion = Integer.parseInt(txtStockFraccion.getText().trim());
                        }
                        
                        VariablesInventario.vStk_ModEntero = ""+entero;
                        VariablesInventario.vStk_ModFrac = ""+fraccion;
                        FarmaUtility.showMessage(this, "El ajuste se guardó correctamente", cmbMotivoAjuste);
                        tipo_rep = "";
                    } catch (SQLException sql) {
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.showMessage(this, "Ocurrió un error al guardar el ajuste: \n" +
                                sql.getMessage(), cmbMotivoAjuste);
                        log.error("", sql);
                    }
                    cerrarVentana(true);
                }

        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.cerrarVentana(false);
        } 
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void initCmbMotivoAjuste() {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add("FV");
        FarmaLoadCVL.loadCVLFromSP(cmbMotivoAjuste, "cmbMotivoAjuste", "PTOVENTA_INV.INV_LISTA_MOT_AJUST_KRDX(?,?)",
                                   parametros, false);
        
        //msjMotivo;
        try {
            log.info("PTOVENTA_INV.INV_RECUP_MSJ_SIG_AJUS_KRDX(?,?)"+parametros);
            FarmaDBUtility.executeSQLStoredProcedureArrayList(listaMsjMotivo,
                                                 "PTOVENTA_INV.INV_RECUP_MSJ_SIG_AJUS_KRDX(?,?)",
                                                 parametros);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* public void initCmbMotivoAjuste() { //06.08.2014 cambio a publico

	                ArrayList parametros = new ArrayList();
	                parametros.add(FarmaVariables.vCodGrupoCia);
	                parametros.add(tipo_rep); //rherrera 06.08.2014
	                //FarmaLoadCVL.loadCVLFromSP(cmbMotivoAjuste, "cmbMotivoAjuste","PTOVENTA_INV.INV_LISTA_MOT_AJUST_KRDX(?,?)", parametros, false);
	                log.debug("invocando a PTOVENTA_INV.INV_LISTA_MOT_AJUST_KRDX(?,?):"+parametros);
                        if (tipo_rep.equals(ConstantsCaja.TIPO_REPOSICION_INSUMOS))
	                    FarmaLoadCVL.loadCVLFromSP(cmbMotivoAjuste, "cmbMotivoAjuste","PTOVENTA_INV.INV_LISTA_MOT_AJUST_KRDX(?,?)", parametros, false);
	                       else
	                    FarmaLoadCVL.loadCVLFromSP(cmbMotivoAjuste, "cmbMotivoAjuste2","PTOVENTA_INV.INV_LISTA_MOT_AJUST_KRDX(?,?)", parametros, false);
	        } */

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void mostrarDatos() {
        lblProducto.setText(VariablesInventario.vDescProd);
        lblLaboratorio.setText(VariablesInventario.vNomLab);
        lblUnidad.setText(VariablesInventario.vDescUnidPresent);
        lblUnidadVenta.setText(VariablesInventario.vDescUnidFrac);
        lblStock.setText("" + VariablesInventario.vStock);
    }

    private boolean datosValidados() {
        boolean rpta = true;
        if (cmbMotivoAjuste.getSelectedIndex() == 0) {
            FarmaUtility.showMessage(this, "Seleccione una opcion válida", cmbMotivoAjuste);
            return false;
        }
        
        String codMotivo=FarmaLoadCVL.getCVLCode("cmbMotivoAjuste",cmbMotivoAjuste.getSelectedIndex());
        if(codMotivo.equalsIgnoreCase("526")){
            if (txtCantAjuste.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtCantAjuste);
                return false;
            }
            if (!FarmaUtility.isInteger(txtCantAjuste.getText().trim())) {
                FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtCantAjuste);
                return false;
            }
            if(signoAjuste.equalsIgnoreCase("-")){
                int diferencia = VariablesInventario.vStock-Integer.parseInt(txtCantAjuste.getText().trim());
                if(diferencia<0){
                    FarmaUtility.showMessage(this, "El nuevo stock no puede estar en negativo", txtCantAjuste);
                    return false;
                }
            }
        }else{
            if (txtStockModifEntero.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtStockModifEntero);
                return false;
            }
            if (!FarmaUtility.isInteger(txtStockModifEntero.getText().trim())) {
                FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtStockModifEntero);
                return false;
            }
            if (txtStockFraccion.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtStockFraccion);
                return false;
            }
            if (!FarmaUtility.isInteger(txtStockFraccion.getText().trim())) {
                FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtStockFraccion);
                return false;
            }
            if (FarmaUtility.getDecimalNumber(txtStockModifEntero.getText().trim()) *
                Integer.parseInt(VariablesInventario.vFrac) +
                FarmaUtility.getDecimalNumber(txtStockFraccion.getText().trim()) ==
                FarmaUtility.getDecimalNumber(lblStock.getText().trim())) {
                /*** INICIO CCASTILLO 26/12/2016 ***/
                /*FarmaUtility.showMessage(this, "La cantidad ingresada debe ser distinto del Stock Actual.",
                                         txtStockModifEntero);
                return false;*/
                if(indPedidoEsp!=ConstantsInventario.TIP_PED_ESP_INS){
                    FarmaUtility.showMessage(this, "La cantidad ingresada debe ser distinto del Stock Actual.",
                                             txtStockModifEntero);
                    return false;
                }
                /*** FIN CCASTILLO 26/12/2016 ***/
            }
        }
        
        return rpta;
    }

    private void insertarAjusteKardex() throws SQLException {
        log.debug("Integer.parseInt(VariablesInventario.vFrac) : " + Integer.parseInt(VariablesInventario.vFrac));
        txtGlosa.setText(txtGlosa.getText().toUpperCase());
        int entero = 0;//Integer.parseInt(txtStockModifEntero.getText().trim());
        int fraccion = 0;// Integer.parseInt(txtStockFraccion.getText().trim());
        String codMotivo=FarmaLoadCVL.getCVLCode("cmbMotivoAjuste",cmbMotivoAjuste.getSelectedIndex());
        if(codMotivo.equalsIgnoreCase("526")){
            if(signoAjuste.equalsIgnoreCase("-")){
                entero = VariablesInventario.vStock-Integer.parseInt(txtCantAjuste.getText().trim());
            }
            if(signoAjuste.equalsIgnoreCase("+")){
                entero = VariablesInventario.vStock+Integer.parseInt(txtCantAjuste.getText().trim());
            }
        }else{
            entero = Integer.parseInt(txtStockModifEntero.getText().trim());
            fraccion = Integer.parseInt(txtStockFraccion.getText().trim());
        }
        log.debug("entero : " + entero);
        log.debug("fraccion : " + fraccion);
        int cantidad = entero * Integer.parseInt(VariablesInventario.vFrac) + fraccion;
        String cantidadCompleta = "" + cantidad;
        log.debug("cantidad : " + cantidadCompleta);

        DBInventario.ingresaAjusteKardex(FarmaLoadCVL.getCVLCode("cmbMotivoAjuste",
                                                                 cmbMotivoAjuste.getSelectedIndex()), cantidadCompleta,
                                         txtGlosa.getText().trim().toUpperCase());

        /* 	    if (tipo_rep.equals(ConstantsCaja.TIPO_REPOSICION_INSUMOS))
	        DBInventario.ingresaAjusteKardex(FarmaLoadCVL.getCVLCode(
	                        "cmbMotivoAjuste", cmbMotivoAjuste.getSelectedIndex()),
	        cantidadCompleta,
	        txtGlosa.getText().trim().toUpperCase());
	           else
	               //rherrera 09.09.2014 -- cambio para insumos
                DBInventario.ingresaAjusteKardex(FarmaLoadCVL.getCVLCode(
                                "cmbMotivoAjuste2", cmbMotivoAjuste.getSelectedIndex()),
                cantidadCompleta,
                txtGlosa.getText().trim().toUpperCase()); */
    }

    private void txtGlosa_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbMotivoAjuste);
            txtGlosa.setText(txtGlosa.getText().toUpperCase());
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtStockFraccion_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtGlosa);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtStockFraccion_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtStockFraccion, e);
    }

    public void validaIngresoStock() {

        boolean val = false;
        int entero = 0;
        int stock = 0;
        int fraccion = 0;
        String codMotivo=FarmaLoadCVL.getCVLCode("cmbMotivoAjuste",cmbMotivoAjuste.getSelectedIndex());
        if(codMotivo.equalsIgnoreCase("526")){
            try {
                entero = Integer.parseInt(txtCantAjuste.getText().trim());
            } catch (Exception ex) {
                entero = 0;
            }
        }else{
            try {
                entero = Integer.parseInt(txtStockModifEntero.getText().trim());
            } catch (Exception ex) {
                entero = 0;
            }
            try {
                fraccion = Integer.parseInt(txtStockFraccion.getText().trim());
            } catch (Exception ex) {
                fraccion = 0;
            }
        }
        try {
            stock = Integer.parseInt(lblStock.getText().trim());
        } catch (Exception ex) {
            stock = 0;
        }

        int cantidad = entero * Integer.parseInt(VariablesInventario.vFrac) + fraccion;

        /*  if (cantidad < stock ) return !val;
              else return val; */

    }

    /**
     * Para indicar el tipo de reposicion
     * @author rherrera  06.08.2014
     * @param tipo_rep
     */
    public void setTipo_rep(String tipo_rep) {
        this.tipo_rep = tipo_rep;
    }

    private void imprime(String msj) {
        System.out.println(msj);
    }
    boolean newStado=true;
    boolean muestraPanel2=true;
    private void cmbMotivoAjuste_ItemStateChanged(ItemEvent e) {
        if(newStado){
            String codMotivo=FarmaLoadCVL.getCVLCode("cmbMotivoAjuste",cmbMotivoAjuste.getSelectedIndex());
            if(codMotivo.equalsIgnoreCase("526")){
                pnlDetalle2.setVisible(true);
                pnlDetalle1.setBounds(new Rectangle(posX_Aux,posY_Aux, ancho, largo));
                pnlDetalle2.setBounds(new Rectangle(posX,posY, ancho, largo));
                pnlDetalle1.setVisible(false);
                //FarmaUtility.moveFocus(txtCantAjuste);
            }else{
                pnlDetalle1.setVisible(true);
                pnlDetalle2.setBounds(new Rectangle(posX_Aux,posY_Aux, ancho, largo));
                pnlDetalle1.setBounds(new Rectangle(posX,posY, ancho, largo));
                pnlDetalle2.setVisible(false);
                //FarmaUtility.moveFocus(txtStockModifEntero);
            }
            visualizarMensajeMotivoAjuste(codMotivo);
            newStado=false;
        }else{
            newStado=true;
        }
    }

    private void txtCantAjuste_KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtGlosa);
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtCantAjuste_KeyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtStockModifEntero, e);
    }

    private void visualizarMensajeMotivoAjuste(String codMotivo) {
        String msjInfo=txt;
        String msjAux="";
        for(int i=0;i<listaMsjMotivo.size();i++){
            String codAux=listaMsjMotivo.get(i).get(0).toString();
            imprime("=> "+codAux+" - "+listaMsjMotivo.get(i).get(1).toString()+" - "+listaMsjMotivo.get(i).get(2).toString());
            if(codAux.equalsIgnoreCase(codMotivo)){
                msjAux=listaMsjMotivo.get(i).get(1).toString();
                signoAjuste=listaMsjMotivo.get(i).get(2).toString();
                break;
            }
        }
        if(msjAux.equalsIgnoreCase("NULL")){
            txtMensaje.setText(msjInfo.replaceAll("MSJ", mensaje));
        }else{
            txtMensaje.setText(msjInfo.replaceAll("MSJ", msjAux));
        }
    }
}
