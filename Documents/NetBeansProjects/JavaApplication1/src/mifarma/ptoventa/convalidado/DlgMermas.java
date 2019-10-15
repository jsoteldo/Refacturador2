package mifarma.ptoventa.convalidado;


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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convalidado.reference.DBConvalidado;
import mifarma.ptoventa.convalidado.reference.VariablesConvalidado;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgMermas extends JDialog {
    // **************************************************************************
    // Constructores
    // **************************************************************************
    private static final Logger log = LoggerFactory.getLogger(DlgMermas.class);

    Frame myParentFrame;

    FarmaTableModel tableModel;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanelWhite jContentPane = new JPanelWhite();

    private JPanelHeader pnlTitulos = new JPanelHeader();


    private JLabelWhite lblProducto_T = new JLabelWhite();

    private JLabelWhite lblUnidadActual_T = new JLabelWhite();

    private JLabelWhite lblLaboratorio_T = new JLabelWhite();

    private JLabelWhite lblProducto = new JLabelWhite();

    private JLabelWhite lblUnidad = new JLabelWhite();

    private JLabelWhite lblLaboratorio = new JLabelWhite();


    private JLabelFunction lblEsc = new JLabelFunction();

    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelWhite lblGlosa_T = new JLabelWhite();
    private JLabelWhite lblStock_T = new JLabelWhite();
    private JLabelWhite lblStock = new JLabelWhite();
    private JLabelWhite lblUnidadVenta = new JLabelWhite();

    private JPanelWhite pnlMsj = new JPanelWhite();
    private JScrollPane scrMsj = new JScrollPane();

    private String tipo_rep;
    private JPanelTitle pnlGlosa = new JPanelTitle();
    private JPanelTitle pnlDetalle2 = new JPanelTitle();
    private JLabelWhite lblIngresoCant = new JLabelWhite();
    private JTextFieldSanSerif txtCantAjuste = new JTextFieldSanSerif();
    private JEditorPane txtMensaje = new JEditorPane();
    private int posX=15;
    private int posY=180;
    private int ancho=425;
    private int largo=50;
    private ArrayList<ArrayList> listaMsjMotivo=new ArrayList<ArrayList>();
    private String txt="<div style=\"font-size: 12px\">\n"
               +"<b style=\"align-content: center;color: red\">MSJ</b><br>\n" +
               "</div>";
    private String mensaje="LA CANTIDAD A INGRESAR DEBE DE SER IGUAL AL STOCK FÍSICO DEL LOCAL";
    private JLabel lblStockActualizado = new JLabel();
    private JTextFieldSanSerif txtGlosa = new JTextFieldSanSerif();
    private JLabel lblGlosa_R = new JLabel();


    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    public DlgMermas() {
        this(null, "", false);
    }

    public DlgMermas(Frame parent, String title, boolean modal) {
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
    
    private int indPedidoEsp=0; // la ventana es llamada desde los pedidos especiales insumos (1: SI , 0: NO)
    public DlgMermas(Frame parent, String title, boolean modal,int indPedidoEsp) {
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

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(468, 463));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Mermar Producto");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlTitulos.setBounds(new Rectangle(15, 15, 425, 115));
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
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(350, 375, 90, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(245, 375, 90, 20));
        lblGlosa_T.setText("Stock despues de la Merma :");
        lblGlosa_T.setBounds(new Rectangle(10, 55, 175, 20));

        lblStock_T.setText("Stock:");
        lblStock_T.setBounds(new Rectangle(20, 90, 90, 15));
        lblStock.setBounds(new Rectangle(120, 90, 40, 15));
        lblStock.setFont(new Font("SansSerif", 0, 11));
        lblUnidadVenta.setBounds(new Rectangle(185, 90, 140, 15));
        lblUnidadVenta.setFont(new Font("SansSerif", 0, 11));

        pnlMsj.setBounds(new Rectangle(10, 295, 430, 70));
        scrMsj.setBounds(new Rectangle(5, 0, 425, 70));
        scrMsj.setFont(new Font("SansSerif", 0, 11));
        scrMsj.setBackground(SystemColor.windowText);
        scrMsj.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrMsj.getViewport();
        pnlGlosa.setBounds(new Rectangle(15, 195, 425, 90));

        pnlGlosa.add(lblGlosa_R, null);
        pnlGlosa.add(txtGlosa, null);
        pnlGlosa.add(lblStockActualizado, null);
        pnlGlosa.add(lblGlosa_T, null);
        lblIngresoCant.setText("Cantidad a mermar :");
        lblIngresoCant.setBounds(new Rectangle(10, 15, 120, 20));
        txtCantAjuste.setBounds(new Rectangle(130, 15, 170, 20));

        txtCantAjuste.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCantAjuste_KeyPressed(e);
                }


                public void keyReleased(KeyEvent e) {
                    txtCantAjuste_keyReleased(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtCantAjuste_keyTyped(e);
                }
            });
        txtMensaje.setContentType("text/html");
        txtMensaje.setAutoscrolls(false);
        txtMensaje.setEditable(false);

        txtMensaje.setSize(new Dimension(423, 60));
        txtMensaje.setBounds(new Rectangle(20, 305, 405, 55));
        lblStockActualizado.setBounds(new Rectangle(185, 55, 150, 20));
        txtGlosa.setBounds(new Rectangle(130, 10, 270, 20));
        txtGlosa.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtGlosa_keyPressed(e);
                }
            });
        lblGlosa_R.setText("Glosa :");
        lblGlosa_R.setBounds(new Rectangle(10, 10, 95, 20));
        lblGlosa_R.setFont(new Font("SansSerif", 1, 11));
        lblGlosa_R.setForeground(SystemColor.window);
        pnlDetalle2.setBounds(new Rectangle(15, 140, 425, 55));


        pnlDetalle2.add(lblIngresoCant, null);
        pnlDetalle2.add(txtCantAjuste, null);
        scrMsj.getViewport().add(txtMensaje, null);
        pnlMsj.add(scrMsj, null);
        jContentPane.add(pnlMsj, null);
        jContentPane.add(pnlDetalle2, null);
        jContentPane.add(pnlGlosa, null);
        jContentPane.add(lblF11, null);
        jContentPane.add(lblEsc, null);
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
        jContentPane.add(txtMensaje, null);
        jContentPane.add(txtMensaje, null);
        jContentPane.add(txtMensaje, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initialize() {
        
    };

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        this.setSize(new Dimension(468, 385));
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtCantAjuste);
        mostrarDatos();
        pnlMsj.setVisible(false);
        lblEsc.setBounds(new Rectangle(350, 305, 90, 20));
        lblF11.setBounds(new Rectangle(245, 305, 90, 20));
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            if (datosValidados())
                if (JConfirmDialog.rptaConfirmDialog(this, "¿Está seguro que desea guardar la merma?")) {
                    try {
                        boolean bFlag = insertarAjusteKardex();
                        if(bFlag){
                            FarmaUtility.aceptarTransaccion();
                            FarmaUtility.showMessage(this, "Merma registrada correctamente", txtCantAjuste);
                            tipo_rep = "";
                            cerrarVentana(true);
                        }else{
                            FarmaUtility.showMessage(this, "La cantidad a mermar debe de ser menor al Stock actual", txtCantAjuste);
                        }
                    } catch (SQLException sql) {
                        FarmaUtility.liberarTransaccion();
                        FarmaUtility.showMessage(this, "Ocurrió un error al guardar la merma: \n" +
                                sql.getMessage(), txtCantAjuste);
                        log.error("", sql);
                        cerrarVentana(true);
                    }
                }

        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.cerrarVentana(false);
        } 
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void mostrarDatos() {
        lblProducto.setText(VariablesConvalidado.vDescProd);
        lblLaboratorio.setText(VariablesConvalidado.vNomLab);
        lblUnidad.setText(VariablesConvalidado.vDescUnidPresent);
        lblUnidadVenta.setText(VariablesConvalidado.vDescUnidFrac);
        lblStock.setText("" + VariablesConvalidado.vStock);
        lblStockActualizado.setText("" + VariablesConvalidado.vStock);
    }

    private boolean datosValidados() {
        boolean rpta = true;
        
        if (txtCantAjuste.getText().trim().length() == 0) {
            FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtCantAjuste);
            return false;
        }
        if (!FarmaUtility.isInteger(txtCantAjuste.getText().trim())) {
            FarmaUtility.showMessage(this, "Ingrese una cantidad válida", txtCantAjuste);
            return false;
        }
        if (txtGlosa.getText().trim().length() == 0) {
            FarmaUtility.showMessage(this, "Ingrese Glosa", txtGlosa);
            return false;
        }
        return rpta;
    }

    private boolean insertarAjusteKardex() throws SQLException {
        boolean bFlag = validarMerma();
        String msjInfo = txt;
        if(bFlag){
            int stockActualizado = 0;
            int iStockActual = Integer.parseInt(lblStock.getText());
            int iCantidadMerma = Integer.parseInt(txtCantAjuste.getText());
            stockActualizado = iStockActual - iCantidadMerma;
            
            String cantidadCompleta = "" + stockActualizado;
            log.debug("Nuevo stock : " + cantidadCompleta);

            DBConvalidado.ingresaAjusteKardex("526", cantidadCompleta, txtGlosa.getText().trim().toUpperCase());
            return true;
        }else{
            if(!pnlMsj.isVisible()){
                this.setSize(new Dimension(476, 440));
                lblEsc.setBounds(new Rectangle(350, 375, 90, 20));
                lblF11.setBounds(new Rectangle(245, 375, 90, 20));
                pnlMsj.setVisible(true);
                txtMensaje.setVisible(true);
                this.revalidate();
                this.repaint();
            }
            String mensaje = "La cantidad a mermar debe de ser menor al Stock actual";
            txtMensaje.setText(msjInfo.replaceAll("MSJ", mensaje.toUpperCase()));
            return false;
        }
    }

    private void txtGlosa_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtCantAjuste);
            txtGlosa.setText(txtGlosa.getText().toUpperCase());
        } else {
            chkKeyPressed(e);
        }
    }

    public void setTipo_rep(String tipo_rep) {
        this.tipo_rep = tipo_rep;
    }
    
    private void txtCantAjuste_KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtGlosa);
        } else {
            chkKeyPressed(e);
        }
    }

    private boolean validarMerma(){
        int iStockActual = Integer.parseInt(lblStock.getText());
        int iCantidadMerma = Integer.parseInt(txtCantAjuste.getText());
        if(iStockActual - iCantidadMerma < 0){
            lblStockActualizado.setText("");
            return false;
        }else{
            lblStockActualizado.setText((iStockActual - iCantidadMerma) + "");
            return true;
        }
    }

    private void txtCantAjuste_keyReleased(KeyEvent e) {
        String msjInfo = txt;
        if(!txtCantAjuste.getText().equals("")){
            boolean bFlag = validarMerma();
            if(!bFlag){
                if(!pnlMsj.isVisible()){
                    this.setSize(new Dimension(476, 440));
                    lblEsc.setBounds(new Rectangle(350, 375, 90, 20));
                    lblF11.setBounds(new Rectangle(245, 375, 90, 20));
                    pnlMsj.setVisible(true);
                    txtMensaje.setVisible(true);
                    this.revalidate();
                    this.repaint();
                }
                String mensaje = "La cantidad a mermar debe de ser menor al Stock actual"; 
                txtMensaje.setText(msjInfo.replaceAll("MSJ", mensaje.toUpperCase()));
            }else{
                if(pnlMsj.isVisible()){
                    pnlMsj.setVisible(false);
                    txtMensaje.setVisible(false);
                    this.setSize(new Dimension(468, 385));
                    lblEsc.setBounds(new Rectangle(350, 305, 90, 20));
                    lblF11.setBounds(new Rectangle(245, 305, 90, 20));
                    this.revalidate();
                    this.repaint();
                }
            }
        }else{
            if(pnlMsj.isVisible()){
                pnlMsj.setVisible(false);
                txtMensaje.setVisible(false);
                this.setSize(new Dimension(468, 385));
                lblEsc.setBounds(new Rectangle(350, 305, 90, 20));
                lblF11.setBounds(new Rectangle(245, 305, 90, 20));
                this.revalidate();
                this.repaint();
            }
            lblStockActualizado.setText(lblStock.getText());
        }
    }

    private void txtCantAjuste_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCantAjuste, e);
    }
}
