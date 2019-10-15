package mifarma.ptoventa.fidelizacion;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.DlgNuevoCobro;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * JVARA 27-09-2017  clase creada DlgConfirmacionImpresion
 */
public class DlgConfirmacionImpresion extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgConfirmacionImpresion.class);   
   
    Frame myParentFrame;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel pnlContent = new JPanel();    
    private JTextArea lblVoucherTeleton = new JTextArea();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    
    private JLabel lblVerificacion = new JLabel();
    private JLabel lblLocal = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel lblDonacion = new JLabel();
    private JLabel lblDNI = new JLabel();
    private JLabel lblDNIValor = new JLabel();
    private JLabel lblMonto = new JLabel();
    private JLabel lblMontoValor = new JLabel(); 
    private JLabel lblPregunta = new JLabel(); 
    
    public DlgConfirmacionImpresion() {
        this(null, "", false);
    }

    public DlgConfirmacionImpresion(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(374, 363));
        this.getContentPane().setLayout(borderLayout1);        
        this.setFont(new Font("SansSerif", 0, 11));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Verificación de Donación");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }

            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });

        pnlContent.setLayout(null);
        pnlContent.setBackground(Color.white);
        pnlContent.setSize(new Dimension(594, 405)); 
        
        jButton1.setText("SI");
        jButton1.setMnemonic('A');
        jButton1.setFont(new Font("Dialog", 1, 12));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });    
        jButton1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                jButton1_keyPressed(e);
            }
        });       
       
        jButton1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                jButton_focusGained(e);
            }
    
            public void focusLost(FocusEvent e) {
                jButton_focusLost(e);
            }
        });

       // lblVerificacion.setText("VERIFICACIÓN DE DONACIÓN");
        lblVerificacion.setBounds(new Rectangle(75, 20, 225, 20));
        lblVerificacion.setForeground(Color.black);
        lblVerificacion.setFont(new Font("SansSerif", Font.BOLD, 15));  

        //lblLocal.setText("LOCAL: 506-ORMEÑO-TEST");
        lblLocal.setBounds(new Rectangle(100, 50, 175, 20));
        lblLocal.setForeground(Color.black);
        lblLocal.setFont(new Font("SansSerif", 0, 12));
        
      //  lblFecha.setText("FECHA: 27/09/2017 17:55:10");
        lblFecha.setBounds(new Rectangle(100, 75, 180, 20));
        lblFecha.setForeground(Color.black);
        lblFecha.setFont(new Font("SansSerif", 0, 12));

       // lblDonacion.setText("DONACIÓN TELETÓN 2017");
        lblDonacion.setBounds(new Rectangle(50, 105, 180, 20));
        lblDonacion.setForeground(Color.black);
        lblDonacion.setFont(new Font("SansSerif", 0, 12));

      //  lblDNI.setText("DNI/CE:");
        lblDNI.setBounds(new Rectangle(50, 125, 160, 20));
        lblDNI.setForeground(Color.black);
        lblDNI.setFont(new Font("SansSerif", 0, 12));

      //  lblDNIValor.setText("09662580");
        lblDNIValor.setBounds(new Rectangle(125, 145, 205, 35));
        lblDNIValor.setForeground(Color.black);
        lblDNIValor.setFont(new Font("SansSerif", Font.BOLD, 28));      

      //  lblMonto.setText("MONTO");
        lblMonto.setBounds(new Rectangle(50, 185, 60, 20));
        lblMonto.setForeground(Color.black);
        lblMonto.setFont(new Font("SansSerif", 0, 12));

      //  lblMontoValor.setText("S/12.00");
        lblMontoValor.setBounds(new Rectangle(125, 205, 195, 25));
        lblMontoValor.setForeground(Color.black);
        lblMontoValor.setFont(new Font("SansSerif", Font.BOLD, 28));        
        
        lblPregunta.setText("¿Está de acuerdo con el DNI y monto ingresado?");
        lblPregunta.setBounds(new Rectangle(10, 250, 360, 30));
        lblPregunta.setForeground(Color.black);
        lblPregunta.setFont(new Font("SansSerif",Font.BOLD, 15));     
       
        jButton1.setBounds(new Rectangle(70, 295, 105, 20));   

        jButton2.setText("NO");
        jButton2.setMnemonic('C');
        jButton2.setFont(new Font("Dialog", 1, 12));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });        
        
        jButton2.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                jButton2_keyPressed(e);
            }
        });
        
        jButton2.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                jButton_focusGained(e);
            }

            public void focusLost(FocusEvent e) {
                jButton_focusLost(e);
            }
        });

        jButton2.setBounds(new Rectangle(195, 295, 115, 20));

        pnlContent.add(jButton1, null);
        pnlContent.add(jButton2, null);
        pnlContent.add(lblVerificacion, null);
        pnlContent.add(lblLocal, null);
        pnlContent.add(lblFecha, null);
        pnlContent.add(lblDonacion, null);
        pnlContent.add(lblDNI , null);
        pnlContent.add(lblDNIValor, null);
        pnlContent.add(lblMonto, null);

        pnlContent.add(lblPregunta, null);
        pnlContent.add(lblMontoValor, null);      
        this.getContentPane().add(pnlContent, BorderLayout.CENTER);
    }
    

    private void initialize() {
        
     FarmaVariables.vAceptar = false;    
    
    }
    
    private void aplicarCobro(){
    DlgNuevoCobro dlgNuevoCobro = new DlgNuevoCobro(myParentFrame, "", true);
    dlgNuevoCobro.setIndPedirLogueo(false);
    dlgNuevoCobro.setIndTipoCobro(ConstantsCaja.COBRO_RECAUDACION);
    dlgNuevoCobro.setCodRecau(VariablesCaja.vTmpCodigo);
    
    dlgNuevoCobro.setStrMonedaPagar(VariablesCaja.vArrayCabecera.get(7).toString());
    dlgNuevoCobro.setArrayCabeRcd(VariablesCaja.vArrayCabecera);

    if (ConstantsRecaudacion.TIPO_REC_CITI.equals(VariablesCaja.vTipoRec) ||
        ConstantsRecaudacion.TIPO_REC_CMR.equals(VariablesCaja.vTipoRec) ||
        ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(VariablesCaja.vTipoRec)) {
       
        dlgNuevoCobro.setNumTarjeta(VariablesCaja.vArrayCabecera.get(3).toString());
       
    }
    FarmaVariables.vAceptar = true; 
    dlgNuevoCobro.setVisible(true); 
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    private void jButton_focusGained(FocusEvent e) {
        ((JButton)e.getSource()).setBackground(new Color(255, 130, 40));
        ((JButton)e.getSource()).setForeground(Color.WHITE);
    }
    
    private void jButton_focusLost(FocusEvent e) {
        ((JButton)e.getSource()).setBackground(new Color(237, 237, 237));
        ((JButton)e.getSource()).setForeground(Color.BLACK);
    }
    
    private void jButton1_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.setVisible(false);
            this.dispose();
            aplicarCobro();
            
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButton2.requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.setVisible(false);
            this.dispose();
        }
    }
    private void jButton2_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.setVisible(false);
        this.dispose();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
         jButton1.requestFocus();
    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        this.setVisible(false);
       this.dispose();
     }  
    }
    
    private void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.dispose();
    }
  

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(jButton2);
       
    }
    
    private void jButton1_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.dispose();
       aplicarCobro(); 
    }
    
  
    private void cerrarVentana(boolean pAceptar) {
            FarmaVariables.vAceptar = pAceptar;
            this.setVisible(false);
            this.dispose();
    }

    public JTextArea getLblVoucherTeleton() {
        return lblVoucherTeleton;
    }

    public void setLblVoucherTeleton(JTextArea lblVoucherTeleton) {
        this.lblVoucherTeleton = lblVoucherTeleton;
    }


    public JLabel getLblVerificacion() {
        return lblVerificacion;
    }

    public void setLblVerificacion(JLabel lblVerificacion) {
        this.lblVerificacion = lblVerificacion;
    }

    public JLabel getLblLocal() {
        return lblLocal;
    }

    public void setLblLocal(JLabel lblLocal) {
        this.lblLocal = lblLocal;
    }

    public JLabel getLblFecha() {
        return lblFecha;
    }

    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }

    public JLabel getLblDonacion() {
        return lblDonacion;
    }

    public void setLblDonacion(JLabel lblDonacion) {
        this.lblDonacion = lblDonacion;
    }

    public JLabel getLblDNI() {
        return lblDNI;
    }

    public void setLblDNI(JLabel lblDNI) {
        this.lblDNI = lblDNI;
    }

    public JLabel getLblDNIValor() {
        return lblDNIValor;
    }

    public void setLblDNIValor(JLabel lblDNIValor) {
        this.lblDNIValor = lblDNIValor;
    }

    public JLabel getLblMonto() {
        return lblMonto;
    }

    public void setLblMonto(JLabel lblMonto) {
        this.lblMonto = lblMonto;
    }

    public JLabel getLblMontoValor() {
        return lblMontoValor;
    }

    public void setLblMontoValor(JLabel lblMontoValor) {
        this.lblMontoValor = lblMontoValor;
    }
}
