package mifarma.ptoventa.cotizarPrecios;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
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

import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.DBVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCotizacionIngreseCantidad extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgCotizacionIngreseCantidad.class);

    Frame myParentFrame;
    private boolean productoFraccionado = false;

    private String descUnidPres = "";

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JPanelHeader pnlHeader1 = new JPanelHeader();
    private JLabelWhite lblDescripcion_T = new JLabelWhite();
    private JLabelWhite lblLaboratorio_T = new JLabelWhite();
    private JLabelWhite lblLaboratorio = new JLabelWhite();
    private JLabelWhite lblDescripcion = new JLabelWhite();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JButtonLabel btnCantidadComprada = new JButtonLabel();
    private JTextFieldSanSerif txtCantComprada = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtPrecioUnitario = new JTextFieldSanSerif();
    private JButtonLabel lblPrecioUnitario_T = new JButtonLabel();
    private JTextFieldSanSerif txtFechaVencimiento = new JTextFieldSanSerif();
    private JButtonLabel lblFechaVenc_T = new JButtonLabel();
    private JTextFieldSanSerif txtLote = new JTextFieldSanSerif();
    private JButtonLabel lblLote = new JButtonLabel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelOrange lblUnidPresent_T = new JLabelOrange();
    private JLabelOrange lblUnidPresent = new JLabelOrange();
    private JTextFieldSanSerif txtPrecioTotal = new JTextFieldSanSerif();
    private JLabelOrange lblPrecioTotal_T = new JLabelOrange();
    private String indicadorLoteFecha = "";
    private String tipoProceso = "";
    private JLabelWhite lblPrecio_T = new JLabelWhite();
    private JLabelWhite lblPrecio = new JLabelWhite();
    private String unidadVta;
    private String precioVta;
    private String divisibleFraccion;
    
    private JButtonLabel lblMotivo = new JButtonLabel();
    private JComboBox cmbTipoValida = new JComboBox();
    String valorMotivo="";
    boolean flagMotivo=false;
    private int cantidadMinima;
    private int cantidadSolicitada;

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgCotizacionIngreseCantidad() {
        this(null, "", false);
    }

    public DlgCotizacionIngreseCantidad(Frame parent, String title, boolean modal) {
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

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(342, 320));
        this.getContentPane().setLayout(null);
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ingrese Cantidad Solicitada");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlHeader1.setBounds(new Rectangle(10, 10, 310, 125));
        pnlHeader1.setBackground(Color.white);
        pnlHeader1.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        lblDescripcion_T.setText("Descripción:");
        lblDescripcion_T.setBounds(new Rectangle(10, 5, 75, 15));
        lblDescripcion_T.setForeground(new Color(255, 130, 14));
        lblLaboratorio_T.setText("Laboratorio:");
        lblLaboratorio_T.setBounds(new Rectangle(10, 55, 70, 15));
        lblLaboratorio_T.setForeground(new Color(255, 130, 14));
        lblLaboratorio.setBounds(new Rectangle(95, 55, 280, 15));
        lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
        lblLaboratorio.setForeground(new Color(255, 130, 14));
        lblPrecio_T.setText("Precio:");
        lblPrecio_T.setBounds(new Rectangle(10, 75, 70, 15));
        lblPrecio_T.setForeground(new Color(255, 130, 14));
        lblPrecio.setBounds(new Rectangle(95, 75, 280, 15));
        lblPrecio.setFont(new Font("SansSerif", 0, 11));
        lblPrecio.setForeground(new Color(255, 130, 14));
        lblPrecio.setText("-:");
        lblPrecio.setVisible(false);
        lblDescripcion.setBounds(new Rectangle(95, 5, 280, 15));
        lblDescripcion.setFont(new Font("SansSerif", 0, 11));
        lblDescripcion.setForeground(new Color(255, 130, 14));

        lblMotivo.setText("Motivo:");
        lblMotivo.setBounds(new Rectangle(10, 100, 70, 15));
        lblMotivo.setForeground(new Color(255, 130, 14));
        
        lblMotivo.setMnemonic('m');
        lblMotivo.addKeyListener(new KeyAdapter() {
           public void keyPressed(KeyEvent e) {
                lblMotivo_keyPressed(e);
            }


            });
        lblMotivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblMotivo_actionPerformed(e);
            }
        });
        cmbTipoValida.setBounds(new Rectangle(95, 95, 155, 20));
        cmbTipoValida.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipoValida_keyPressed(e);
            }
        });
        cmbTipoValida.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cmbTipoValida_actionPerformed(e);  
                }
        });
        
      
        lblLote.setText("Lote");
        lblLote.setBounds(new Rectangle(15, 10, 70, 15));
        
        lblLote.setForeground(new Color(255, 130, 14));
        lblLote.setMnemonic('l');
        lblLote.addKeyListener(new KeyAdapter() {
           public void keyPressed(KeyEvent e) {
                lblLote_keyPressed(e);
            }


            });
        lblLote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblLote_actionPerformed(e);
            }
        });
        
        txtLote.setBounds(new Rectangle(15, 30, 70, 20));
        txtLote.setLengthText(15);
        txtLote.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtLote_keyPressed(e);
            }
        });
        
        pnlTitle1.setBounds(new Rectangle(10, 145, 310, 110));
        pnlTitle1.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlTitle1.setBackground(Color.white);
        btnCantidadComprada.setText("Cant. Comprada");
        btnCantidadComprada.setBounds(new Rectangle(15, 60, 90, 15));
        btnCantidadComprada.setMnemonic('C');
        btnCantidadComprada.setForeground(new Color(255, 130, 14));
        btnCantidadComprada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCantidadComprada_actionPerformed(e);
            }
        });
        txtCantComprada.setBounds(new Rectangle(15, 80, 60, 20));
        txtCantComprada.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtCantComprada_keyPressed(e);
            }
            public void keyTyped(KeyEvent e) {
                txtCantComprada_keyTyped(e);
            }
            public void keyReleased(KeyEvent e){
                txtCantComprada_keyReleased(e);
                } 
        });
        txtPrecioUnitario.setBounds(new Rectangle(125, 80, 70, 20));
        txtPrecioUnitario.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtPrecioUnitario_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtPrecioUnitario_keyTyped(e);
            }
            public void keyReleased(KeyEvent e){
                txtPrecioUnitario_keyReleased(e);
                } 
        });
        lblPrecioUnitario_T.setText("Precio Unitario");
        lblPrecioUnitario_T.setBounds(new Rectangle(125, 60, 85, 15));
        lblPrecioUnitario_T.setForeground(new Color(255, 130, 14));
        lblPrecioUnitario_T.setMnemonic('P');
        lblPrecioUnitario_T.addKeyListener(new KeyAdapter() {
           public void keyPressed(KeyEvent e) {
                lblPrecioUnitario_T_keyPressed(e);
            }
            });
        lblPrecioUnitario_T.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblPrecioUnitario_T_actionPerformed(e);
            }
        });
        txtFechaVencimiento.setBounds(new Rectangle(125, 30, 75, 20));
        txtFechaVencimiento.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaVencimiento_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaVencimiento_keyReleased(e);
            }
        });
        lblFechaVenc_T.setText("Fecha Venc.");
        lblFechaVenc_T.setBounds(new Rectangle(125, 10, 70, 15));
        lblFechaVenc_T.setForeground(new Color(255, 130, 14));
        lblFechaVenc_T.setMnemonic('f');
        lblFechaVenc_T.addKeyListener(new KeyAdapter() {
           public void keyPressed(KeyEvent e) {
                lblFechaVenc_T_keyPressed(e);
            }
            });
        lblFechaVenc_T.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFechaVenc_T_actionPerformed(e);
            }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(230, 265, 85, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(115, 265, 105, 20));

        lblUnidPresent_T.setText("Presentación:");
        lblUnidPresent_T.setBounds(new Rectangle(10, 30, 79, 15));
        lblUnidPresent.setBounds(new Rectangle(95, 30, 155, 15));
        lblUnidPresent.setFont(new Font("SansSerif", 0, 11));
        txtPrecioTotal.setBounds(new Rectangle(225, 80, 70, 20));
        txtPrecioTotal.setEditable(false);
        lblPrecioTotal_T.setText("Precio Total");
        lblPrecioTotal_T.setBounds(new Rectangle(225, 60, 70, 15));
        /*** INICIO CCASTILLO 31/08/2017 ***/
        pnlHeader1.add(lblMotivo, null);
        /*** FIN CCASTILLO 31/08/2017 ***/
        pnlHeader1.add(cmbTipoValida, null);
        pnlHeader1.add(lblUnidPresent, null);
        pnlHeader1.add(lblUnidPresent_T, null);
        pnlHeader1.add(lblDescripcion, null);
        pnlHeader1.add(lblLaboratorio, null);
        pnlHeader1.add(lblLaboratorio_T, null);
        pnlHeader1.add(lblDescripcion_T, null);
        pnlHeader1.add(lblPrecio, null);
        pnlHeader1.add(lblPrecio_T, null);
        pnlTitle1.add(lblPrecioTotal_T, null);
        pnlTitle1.add(txtPrecioTotal, null);
        pnlTitle1.add(lblLote, null);
        pnlTitle1.add(txtLote, null);
        pnlTitle1.add(lblFechaVenc_T, null);
        pnlTitle1.add(txtFechaVencimiento, null);
        pnlTitle1.add(lblPrecioUnitario_T, null);
        pnlTitle1.add(txtPrecioUnitario, null);
        pnlTitle1.add(txtCantComprada, null);
        pnlTitle1.add(btnCantidadComprada, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(pnlTitle1, null);
        jContentPane.add(pnlHeader1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        //
        txtCantComprada.setLengthText(6);
        txtFechaVencimiento.setLengthText(10);
        txtFechaVencimiento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                }
            });
        txtPrecioUnitario.setLengthText(9);
        txtPrecioUnitario.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   // txtPrecioUnitario_actionPerformed(e);
                }
            }); //<100000.000
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        initCabecera();
        initCmbTipo();
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    /*** INICIO CCASTILLO 31/08/2017 ***/
     public void initCmbTipo() {
         ArrayList parametros = new ArrayList();
         FarmaLoadCVL.loadCVLFromSP(cmbTipoValida, "cmbTipoValida",
                                    "PKG_COTIZACION_PRECIO.F_LISTA_TIP_MOT_NO_COT", parametros, true);
         FarmaLoadCVL.setSelectedValueInComboBox(cmbTipoValida,  "cmbTipoValida", VarCotizacionPrecio.vIC_MotivoNoCot);

     }
     private void cmbTipoValida_keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_ENTER){
             if(valorMotivo.trim().equalsIgnoreCase("1")){
                     if(txtLote.isVisible())
                         FarmaUtility.moveFocus(txtLote);
                     else
                      FarmaUtility.moveFocus(txtCantComprada);
            }else
             FarmaUtility.moveFocus(cmbTipoValida);
           
         }else
            chkKeyPressed(e);
         
     }
     private void cmbTipoValida_actionPerformed(ActionEvent e) {
        capturaValidaciones();
     }
    
    public void capturaValidaciones(){

        valorMotivo=FarmaLoadCVL.getCVLCode("cmbTipoValida", cmbTipoValida.getSelectedIndex());

        if(valorMotivo.trim().equalsIgnoreCase("1") || valorMotivo.trim().equalsIgnoreCase("0")){
            flagMotivo=true; // debe ingresar los demás datos
        }else{
            
            
            flagMotivo=false;  // no debe ingresar los demás datos
        }
        
        EnableDisableComponent(flagMotivo);
    }
    
    public void EnableDisableComponent(boolean flag){
        txtLote.setEditable(flag);
        txtLote.setEnabled(flag);
        txtFechaVencimiento.setEditable(flag);
        txtFechaVencimiento.setEnabled(flag);
        txtCantComprada.setEditable(flag);
        txtCantComprada.setEnabled(flag);
        txtPrecioUnitario.setEditable(flag);
        txtPrecioUnitario.setEnabled(flag);
        txtPrecioTotal.setEnabled(flag);
    }
    /*** FIN CCASTILLO 31/08/2017 ***/

    private void initCabecera() {
        lblDescripcion.setText(VariablesInventario.vNomProd);
     //   muestraInfoProd();
        lblLaboratorio.setText(VariablesInventario.vNomLab);
        txtLote.setText(VarCotizacionPrecio.vIC_Lote);
        //txtFechaVencimiento.setText(VariablesInventario.vFechaVec);
        txtFechaVencimiento.setText(VarCotizacionPrecio.vIC_Fec_Vigencia);
        txtCantComprada.setText(String.valueOf(VarCotizacionPrecio.vIC_Cant_Comprada));

      //  FarmaLoadCVL.setSelectedValueInComboBox(cmbTipoValida, "cmbTipoValida", (VarCotizacionPrecio.vIC_MotivoNoCot.length()==0)?"0":VarCotizacionPrecio.vIC_MotivoNoCot);
        if (VarCotizacionPrecio.vIC_Precio_Unitario.trim().equals("")) {
            txtPrecioUnitario.setText("0.00");
            txtCantComprada.setText("0");
        } else {
            txtPrecioUnitario.setText(VarCotizacionPrecio.vIC_Precio_Unitario);
        }


        calculaPrecioTotal();
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void btnCantidadComprada_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtCantComprada);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(cmbTipoValida);

        lblPrecio.setText(getPrecioVta());
        lblUnidPresent.setText(getUnidadVta());
     //   lblEntero.setText(getUnidadVta());

        if (!getTipoProceso().equals("1")) { //sin documento //LTAVARA 2017.08.04
            txtLote.setVisible(false);
            lblLote.setVisible(false);
            lblFechaVenc_T.setVisible(false);
            txtFechaVencimiento.setVisible(false);
            if(getDivisibleFraccion().equals(FarmaConstants.INDICADOR_N)){
                lblPrecio.setVisible(false);
                lblPrecio_T.setVisible(false);
            }else{
                    lblPrecio.setVisible(true);
                }
            
            if(!valorMotivo.trim().equalsIgnoreCase("1")){
                     FarmaUtility.moveFocus(cmbTipoValida);
            }else
            FarmaUtility.moveFocus(txtCantComprada);
        }
        if(getTipoProceso().equals("3")){//cotizacion si solicitud
            cmbTipoValida.setEditable(false);
            cmbTipoValida.setEnabled(false);
         /*  txtCantComprada.setText("");
           txtPrecioUnitario.setText("");
            txtPrecioTotal.setText("");*/
            }
        
       
    }

    private void txtCantComprada_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            calculaPrecioTotal();
            FarmaUtility.moveFocus(txtPrecioUnitario);
        } else
            chkKeyPressed(e);
    }

    private void txtCantComprada_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCantComprada, e);
    }

    private void txtFraccion_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtLote);
            calculaPrecioTotal();
        } else
            chkKeyPressed(e);
    }

    private void txtLote_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtFechaVencimiento);
        } else
            chkKeyPressed(e);
    }

    private void txtFechaVencimiento_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            FarmaUtility.moveFocus(txtCantComprada);
        else
            chkKeyPressed(e);
    }

    private void txtFechaVencimiento_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaVencimiento, e);
    }

    private void txtPrecioUnitario_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            calculaPrecioTotal();
        } else
            chkKeyPressed(e);
    }

    private void txtPrecioUnitario_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtPrecioUnitario, e);
    }
    private void txtPrecioUnitario_keyReleased(KeyEvent e) {
        calculaPrecioTotal();
    }
    
    private void txtCantComprada_keyReleased(KeyEvent e) {
        calculaPrecioTotal();
    }
    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            aceptaCantidadIngresada();
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

    private boolean validarCampos() {
        boolean retorno = true;

        if (getTipoProceso().equals("1")) {
            if (txtLote.getText().trim().length() == 0// && this.indicadorLoteFecha.equals(FarmaConstants.INDICADOR_S)
            ) {
                FarmaUtility.showMessage(this, "Debe Ingresar el Lote." ,
                                         txtLote);
                retorno = false;
            } else if (txtFechaVencimiento.getText().trim().length() == 0 &&
                       this.indicadorLoteFecha.equals(FarmaConstants.INDICADOR_S)) {
                FarmaUtility.showMessage(this, "Debe Ingresar la Fecha de Vencimiento.", txtFechaVencimiento);
                retorno = false;
            } else if (!FarmaUtility.validaFecha(txtFechaVencimiento.getText().trim(), "dd/MM/yyyy")) {
                FarmaUtility.showMessage(this, "Corrija la Fecha de Vencimiento.", txtFechaVencimiento);
                retorno = false;
            } else if (txtCantComprada.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe Ingresar la Cantidad.", txtCantComprada);
                retorno = false;
            }else if (txtCantComprada.getText().trim().length() > 0 &&
                      Integer.parseInt(txtCantComprada.getText().trim())<=Integer.parseInt(VarCotizacionPrecio.vIC_Cant_Pendiente)){ 

               /** Se valida la cantidad minima:
                * Si la cantidad solicitada no es divisible devuelve 0
                * Se valida si es divisible con el local
                * 
                * */ 
                if(getCantidadMinima()!=0 && getCantidadMinima()<getCantidadSolicitada()){
                    if(Integer.parseInt(txtCantComprada.getText().trim())<getCantidadMinima()){
                            FarmaUtility.showMessage(this, "La cantidad comprada No debe ser menor a "+getCantidadMinima(),
                                                     txtCantComprada);
                            retorno = false;
                    }else if(getCantidadMinima()==getCantidadSolicitada() 
                             && Integer.parseInt(txtCantComprada.getText().trim())<getCantidadMinima()){
                            FarmaUtility.showMessage(this, "La cantidad comprada debe ser minimo "+getCantidadMinima(),
                                                     txtCantComprada);
                            retorno = false;
                    }
                }
            } else if(Integer.parseInt(txtCantComprada.getText().trim()) >
                       Integer.parseInt(VarCotizacionPrecio.vIC_Cant_Pendiente)) {
                FarmaUtility.showMessage(this, "La Cantidad Comprada excede a la Cantidad Pendiente de compra.",
                                         txtCantComprada);
                retorno = false;
                
            } else if (txtPrecioUnitario.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe Ingresar el Precio Unitario.", txtPrecioUnitario);
                retorno = false;
            } else if (!FarmaUtility.validateDecimal(this, txtPrecioUnitario, "Corrija el Precio Unitario.", false))
                retorno = false;
            else if (Double.parseDouble(txtPrecioUnitario.getText().trim()) >= 100000.000) {
                FarmaUtility.showMessage(this, "El Precio Unitario, no debe ser mayor de 99,999.999 .",
                                         txtPrecioUnitario);
                retorno = false;
            } 
            
            else {
                try {
                    String fecha = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
                    Date sysdate = FarmaUtility.getStringToDate(fecha, "dd/MM/yyyy"), fechaVec =
                        FarmaUtility.getStringToDate(txtFechaVencimiento.getText().trim(), "dd/MM/yyyy");
                    if (!sysdate.before(fechaVec)) {
                        FarmaUtility.showMessage(this,
                                                 "La Fecha de Vencimiento debe ser posterior a la fecha de hoy: " +
                                                 fecha + " .", txtFechaVencimiento);
                        retorno = false;
                    }
                } catch (SQLException sql) {
                    log.error("", sql);
                    FarmaUtility.showMessage(this, "Error al obtener la fecha del sistema : \n" +
                            sql.getMessage(), txtCantComprada);
                }
            }
        } else { //sin documento
            /*if(!txtFechaVencimiento.getText().trim().isEmpty()){
                if (!FarmaUtility.validaFecha(txtFechaVencimiento.getText().trim(), "dd/MM/yyyy")) {
                    FarmaUtility.showMessage(this, "Corrija la Fecha de Vencimiento.", txtFechaVencimiento);
                    retorno = false;
                }
            }*/

            if (txtCantComprada.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe Ingresar la Cantidad.", txtCantComprada);
                retorno = false;
            } else if ( getTipoProceso().equals("2")) {//cuando es cotizacion cotizar
                if((Integer.parseInt(txtCantComprada.getText().trim()) >
                    Integer.parseInt(VarCotizacionPrecio.vIC_Cant_Pendiente))){
                    
                        FarmaUtility.showMessage(this, "La Cantidad Comprada excede a la Cantidad Pendiente de compra.",
                                                 txtCantComprada);
                        retorno = false;
               }
              
            } else if (txtPrecioUnitario.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe Ingresar el Precio Unitario.", txtPrecioUnitario);
                retorno = false;
            } else if (!FarmaUtility.validateDecimal(this, txtPrecioUnitario, "Corrija el Precio Unitario.", false))
                retorno = false;
            else if (Double.parseDouble(txtPrecioUnitario.getText().trim()) >= 100000.000) {
                FarmaUtility.showMessage(this, "El Precio Unitario, no debe ser mayor de 99,999.999 .",
                                         txtPrecioUnitario);
                retorno = false;
            }
            /* else {
                try {
                    String fecha = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);

                    if(!txtFechaVencimiento.getText().trim().isEmpty()){
                        Date sysdate = FarmaUtility.getStringToDate(fecha, "dd/MM/yyyy"), fechaVec =
                            FarmaUtility.getStringToDate(txtFechaVencimiento.getText().trim(), "dd/MM/yyyy");
                        if (!sysdate.before(fechaVec)) {
                            FarmaUtility.showMessage(this,
                                                     "La Fecha de Vencimiento debe ser posterior a la fecha de hoy: " + fecha +
                                                     " .", txtFechaVencimiento);
                            retorno = false;
                        }
                    }
                } catch (SQLException sql) {
                    log.error("", sql);
                    FarmaUtility.showMessage(this, "Error al obtener la fecha del sistema : \n" +
                            sql.getMessage(), txtCantComprada);
                }
            }*/
        }

        if (retorno)
            if (Double.parseDouble(txtPrecioUnitario.getText()) < 0.01) {
                FarmaUtility.showMessage(this, "Debe ingresar un Precio Mayor de 0.01", txtPrecioUnitario);
                retorno = false;
            }

        return retorno;
    }

    private void cargarDatos() {
        //VariablesInventario.vCant = "" + VariablesInventario.vCantIngreso;
        VarCotizacionPrecio.vIC_Cant_Comprada= Integer.parseInt(txtCantComprada.getText().trim());
        VarCotizacionPrecio.vIC_Lote = txtLote.getText().trim();
        VarCotizacionPrecio.vIC_Fec_Vigencia = txtFechaVencimiento.getText().trim();
        VarCotizacionPrecio.vIC_Precio_Unitario = txtPrecioUnitario.getText().trim();
        /*** INICIO CCASTILLO 31/08/2017 ***/
        VarCotizacionPrecio.vIC_MotivoNoCot=valorMotivo.trim();
        /*** FIN CCASTILLO 31/08/2017 ***/

        //int cant = Integer.parseInt(VariablesInventario.vCant);
        int cant = VarCotizacionPrecio.vIC_Cant_Comprada;
        double precio = Double.parseDouble(VarCotizacionPrecio.vIC_Precio_Unitario);
        VarCotizacionPrecio.vIC_Total = (cant * precio) + "";
        
        txtCantComprada.setText("0");
        txtPrecioUnitario.setText("0");
        txtPrecioTotal.setText("0");
    }

    private void aceptaCantidadIngresada() {
        try {
            this.indicadorLoteFecha = BDCotizacionPrecio.getIndicadorLoteFechaCotizacion();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(flagMotivo){
            if (validarCampos()) {
                if (!validaCantIngreso()) {
                    return;
                }
                //si el producto su fraccion no es divisible con el local y es sin documento, no debe validar el rango de precios valido
              /*  if (!(getDivisibleFraccion().equals(FarmaConstants.INDICADOR_N) &&
                    getTipoProceso().equals("2")) ){ //Miguel Sanchez Solicito cuando es cotizacion sin documento debe registrar
                    if (!validaPrecioCompetencia(VarCotizacionPrecio.vIC_Cod_Prod)) {
                        return;
                    }
                }*/
                //NO VALIDA EL RANGO DE PRECIO SI EL PRODUCTO NO ES DIVISIBLE Y ES UNA COTIZACION
                if (!(getDivisibleFraccion().equals(FarmaConstants.INDICADOR_N)&& getTipoProceso().equals("2"))){
                    if (!validaPrecioCompetencia(VarCotizacionPrecio.vIC_Cod_Prod)) {
                        return;
                    }
                }
                cargarDatos();
        
                cerrarVentana(true);
            }
         /*** INICIO CCASTILLO 31/08/2017 ***/   
         }else{
             txtLote.setText("");
             txtFechaVencimiento.setText("");
             txtCantComprada.setText("0");
             txtPrecioUnitario.setText("0");
             txtPrecioTotal.setText("0");
             cargarDatos();
             cerrarVentana(true);    
         }
        /*** FIN CCASTILLO 31/08/2017 ***/
    }

    private boolean validaCantIngreso() {
        boolean valor = true;

        calculaCantidadIngreso();

        if (VarCotizacionPrecio.vIC_Cant_Comprada > 999999) {
            valor = false;
            FarmaUtility.showMessage(this, "La cantidad a ingresar excede el valor máximo de ingreso.",
                                     txtCantComprada);
        } else if (VarCotizacionPrecio.vIC_Cant_Comprada == 0) {
            valor = false;
            FarmaUtility.showMessage(this, "Debe ingresar una cantidad válida.", txtCantComprada);
        }

        return valor;
    }

    private void muestraInfoProd() {
        ArrayList myArray = new ArrayList();
        obtieneInfoProdEnArrayList(myArray);

        if (myArray.size() == 1) {
            descUnidPres = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
        } else {
            descUnidPres = "";
            FarmaUtility.showMessage(this, "Error al obtener Información del Producto", txtCantComprada);
        }

        lblUnidPresent.setText(descUnidPres);
        //lblEntero.setText(descUnidPres);
    }

    private void obtieneInfoProdEnArrayList(ArrayList pArrayList) {
        String codProd = VarCotizacionPrecio.vIC_Cod_Prod;

        try {
            DBVentas.obtieneInfoProducto(pArrayList, codProd);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener información del producto en Arreglo. \n" +
                    sql.getMessage(), txtCantComprada);
        }
    }

    private boolean validaPrecioCompetencia(String pCodProd) {
        boolean bRetorno = true;
        double precioTotal = calculaPrecioTotal();

        String vRetorno;

        try {
            vRetorno =
                    BDCotizacionPrecio.validaPrecioCompetencia(pCodProd, precioTotal / VarCotizacionPrecio.vIC_Cant_Comprada);
        } catch (SQLException e) {
            log.error("", e);
            vRetorno = e.getMessage();
        }

        if (!vRetorno.equals("S")) {
            bRetorno = false;
            FarmaUtility.showMessage(this, vRetorno, txtPrecioUnitario);
        }

        return bRetorno;
    }

    /**
     * Muestra precio total
     * @author ERIOS
     * @since 14.07.2015
     */
    private double calculaPrecioTotal() {
        calculaCantidadIngreso();

        double precio = FarmaUtility.getDecimalNumber(txtPrecioUnitario.getText().trim());
        double precioTotal = VarCotizacionPrecio.vIC_Cant_Comprada * precio;
        txtPrecioTotal.setText(FarmaUtility.formatNumber(precioTotal));

        return precioTotal;
    }

    private void calculaCantidadIngreso() {
        String cantidad = "";
        cantidad = txtCantComprada.getText().trim();

        if (cantidad.equals(""))
            cantidad = "0";

        //VariablesInventario.vCantIngreso = Integer.parseInt(cantidad);
        VarCotizacionPrecio.vIC_Cant_Comprada = Integer.parseInt(cantidad);
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setUnidadVta(String unidadVta) {
        this.unidadVta = unidadVta;
    }

    public String getUnidadVta() {
        return unidadVta;
    }

    public void setPrecioVta(String precioVta) {
        this.precioVta = precioVta;
    }

    public String getPrecioVta() {
        return precioVta;
    }

    public void setDivisibleFraccion(String divisibleFraccion) {
        this.divisibleFraccion = divisibleFraccion;
    }

    public String getDivisibleFraccion() {
        return divisibleFraccion;
    }
    private void lblMotivo_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipoValida);
    }
    private void lblMotivo_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    
    private void lblPrecioUnitario_T_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtPrecioUnitario);
    }
    private void lblPrecioUnitario_T_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    private void lblLote_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtLote);
    }
    private void lblLote_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    private void lblFechaVenc_T_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFechaVencimiento);
    }
    private void lblFechaVenc_T_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }


}
