package mifarma.ptoventa.convenioBTLMF;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgIngresoDocumento extends JDialog {
    double vTiempoMaquina = 400; // MILISEGUNDOS
    private static final Logger log = LoggerFactory.getLogger(DlgIngresoDocumento.class);
    long tmpT1,tmpT2,OldtmpT2;
    boolean isLectoraLazer = false;
    boolean isEnter = false;
    
    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelTitle pnlTitle = new JPanelTitle();
    private JButtonLabel lblFecha = new JButtonLabel();
    private JLabelFunction btnF11 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();
    private Frame MyParentFrame;
    private String eRCM = "";
    private String vRpta = "";
    private boolean estrcm = false;
    private boolean estvta = false;
    //private JTextField txtFecha = new JTextField();
    private JTextFieldSanSerif txtTarjeta = new JTextFieldSanSerif();
    private JLabel lblTarjOculta = new JLabel();
    private JLabel lblTarjetaInput = new JLabel();
    private String titulo=null;
    private String vDocumento = "";
    private JLabelFunction lblMsj = new JLabelFunction();    

    public DlgIngresoDocumento() {
        this(null, "", false);
    }

    public DlgIngresoDocumento(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        titulo=title.trim();
        try {
            MyParentFrame = parent;
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(414, 155));
        this.getContentPane().setLayout(borderLayout1);
        if(titulo!=null && !titulo.equalsIgnoreCase("")){
            this.setTitle(titulo);
        }else{
            this.setTitle("Ingreso de Documento");
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }

            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jContentPane.setFocusable(false);
        pnlTitle.setBounds(new Rectangle(25, 45, 365, 45));
        pnlTitle.setBackground(Color.white);
        pnlTitle.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlTitle.setFocusable(false);
        //txtSerie.setLengthText(3);
        //LTAVARA 03.09.2014 se incrementa en 4 para la serie del documento electronico
        //txtNroComprobante.setLengthText(7);
        //LTAVARA 03.09.2014 se incrementa en 8 para el correlativo del documento electronico

        lblFecha.setText("Documento:");
        lblFecha.setMnemonic('F');
        lblFecha.setBounds(new Rectangle(5, 10, 100, 20));
        lblFecha.setForeground(new Color(255, 90, 33));
        lblFecha.setFocusable(false);
        lblFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblFecha_actionPerformed(e);
            }
        });

        btnF11.setBounds(new Rectangle(40, 95, 117, 20));
        btnEsc.setBounds(new Rectangle(260, 95, 117, 19));
        btnF11.setText("[Enter] Aceptar");
        btnF11.setFocusable(false);


        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);
        txtTarjeta.setBounds(new Rectangle(365, 25, 20, 10));
        txtTarjeta.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                txtTarjeta_keyReleased(e);
            }
            public void keyPressed(KeyEvent e) {
                    txtTarjeta_keyPressed(e);
                }
            public void keyTyped(KeyEvent e) {
                    txtTarjeta_keyTyped(e);
                }
            });
        txtTarjeta.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    txtTarjeta_focusLost(e);
                }
            });
        lblTarjOculta.setBounds(new Rectangle(75, 10, 280, 20));
        lblTarjOculta.setFont(new Font("Century", 1, 11));
        lblTarjOculta.setBorder(BorderFactory.createLineBorder(new Color(0, 99, 0), 2));
        lblTarjOculta.setBackground(SystemColor.window);
        lblTarjetaInput.setBounds(new Rectangle(85, 0, 295, 20));
        pnlTitle.add(lblTarjOculta, null);
        pnlTitle.add(lblTarjetaInput, null);
        pnlTitle.add(lblFecha, null);
        pnlTitle.add(txtTarjeta, null);
        jContentPane.add(lblMsj, null);
        jContentPane.add(btnEsc, null);
        jContentPane.add(btnF11, null);
        jContentPane.add(pnlTitle, null);
        txtTarjeta.setVisible(true);
        lblTarjetaInput.setVisible(false);
        lblMsj.setText("<HTML><CENTER>USE EL LECTOR DE CODIGO DE BARRAS<BR>PARA INGRESAR EL DOCUMENTO</HTML></CENTER>");
        lblMsj.setBounds(new Rectangle(25, 0, 365, 40));
        lblMsj.setBackground(SystemColor.window);
        lblMsj.setFont(new Font("SansSerif", 1, 14));
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    private void initialize() {
        
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", txtTarjeta);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtTarjeta);
        lblTarjetaInput.setVisible(false);
        lblTarjOculta.setVisible(true);
        // KMONCADA 16.07.2015 OBTIENE TIEMPO DE LECTORA DE BARRAS.
        vTiempoMaquina = UtilityPuntos.obtieneTiempoMaximoLectora();
    }
    private void chkKeyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(txtTarjeta.getText().trim().length()>0){
                /*FarmaUtility.showMessage(this, "lblTarjetaInput.getText() "+lblTarjetaInput.getText()+"\n"+
                                               "txtTarjeta.getText() "+txtTarjeta.getText()+"\n"
                                         , txtTarjeta);*/
                vDocumento = txtTarjeta.getText();
                cerrarVentana(true);
            }
            //else
            //    FarmaUtility.showMessage(this, "Debe de escanear el documento.", txtTarjeta);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    private void lblFecha_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtTarjeta);
    }

    private void txtTarjeta_keyReleased(KeyEvent e) {
        if (isTeclaPermitida(e)) {
            if (isLectoraLazer && isEnter && (OldtmpT2 != tmpT2)) {
                txtTarjeta.selectAll();
                OldtmpT2 = tmpT2;
                muestraTarjeta(txtTarjeta.getText());
            }
        } else if ((e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
            txtTarjeta.setText("");
            lblTarjOculta.setText("");
            lblTarjetaInput.setText("");
            e.consume();
            FarmaUtility.showMessage(this, "Por favor Ingrese el Documento", txtTarjeta);
        } else
            e.consume();
    }
    
        private void txtTarjeta_keyPressed(KeyEvent e) {
        if (isTeclaPermitida(e)) {
            if (isLectoraLazer) {
                txtTarjeta.setText("");
                isLectoraLazer = false;
            }
            isEnter = false;
            isLectoraLazer = false;
            if (txtTarjeta.getText().length() == 0)
                tmpT1 = System.currentTimeMillis();
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                isLectoraLazer = false;
                tmpT2 = System.currentTimeMillis();
                log.info("Tiem 2 " + (tmpT2));
                log.info("Tiem 1 " + (tmpT1));
                log.info("Tiempo de ingreso y ENTER " + (tmpT2 - tmpT1));
                if ((tmpT2 - tmpT1) <= vTiempoMaquina && txtTarjeta.getText().length() > 0) {
                    isLectoraLazer = true;
                    isEnter = true;
                    tmpT1 = 0;
                } else {
                    isLectoraLazer = false;
                    txtTarjeta.setText("");
                    tmpT1 = 0;
                    FarmaUtility.showMessage(this, 
                            "Por favor escane el documento con el lector de código de barras.\n" +
                            "No se permite el uso del teclado en esta función.", txtTarjeta);
                }
                //FarmaUtility.moveFocus(txtTarjeta);
            } else
                isEnter = false;
            chkKeyPressed(e);
        } else if ((e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
            txtTarjeta.setText("");
            lblTarjOculta.setText("");
            lblTarjetaInput.setText("");
            e.consume();
            FarmaUtility.showMessage(this, "Por favor Ingrese el documento", txtTarjeta);
        } else
            e.consume();
    }

    private boolean isNumero(char ca) {
        int numero  = 0;
        try {
            numero = Integer.parseInt(ca + "");
            return true;
        } catch (NumberFormatException nfe) {
            //log.error("",nfe);
            return false;
        }
        
        
    }

    private void txtTarjeta_keyTyped(KeyEvent e) {
        if(!(isTeclaPermitida(e))) {
            if((e.getKeyCode() == KeyEvent.VK_DELETE||e.getKeyCode() == KeyEvent.VK_BACK_SPACE)){
                    txtTarjeta.setText("");
                    lblTarjOculta.setText("");
                    lblTarjetaInput.setText("");
                    e.consume();
                    FarmaUtility.showMessage(this,"Por favor Ingrese el documento",txtTarjeta);
            }
            else
            e.consume();
        }
       
    }

    private boolean isTeclaPermitida(KeyEvent e) {
        return (isNumero(e.getKeyChar())||(e.getKeyCode() == KeyEvent.VK_ENTER)||
                (e.getKeyCode() == KeyEvent.VK_ESCAPE) || UtilityPtoVenta.verificaVK_F11(e)
            );
    }
    
    public void muestraTarjeta(String pTarjeta){
        lblTarjOculta.setText("");
        lblTarjetaInput.setText("");
        txtTarjeta.setText("");
        lblTarjOculta.setText(enmascararDNI(pTarjeta));
    }
    
    public static String enmascararDNI(String numTarj) {
        String res = "";
        int tam = numTarj.length();
        if (tam > 3) {
            String prim = numTarj.substring(0, 1);
            String ult = numTarj.substring(tam - 1, tam);
            String centro = FarmaUtility.caracterIzquierda("", 5, "*");
            res = prim + centro + ult;
        }else{
            return numTarj;
        }
        return res;
    }

    private void txtTarjeta_focusLost(FocusEvent e) {
        FarmaUtility.moveFocus(txtTarjeta);
    }
    
    public Frame getMyParentFrame(){
        return this.MyParentFrame;
    }


    public void setVDocumento(String vDocumento) {
        this.vDocumento = vDocumento;
    }

    public String getVDocumento() {
        return vDocumento;
    }
}
