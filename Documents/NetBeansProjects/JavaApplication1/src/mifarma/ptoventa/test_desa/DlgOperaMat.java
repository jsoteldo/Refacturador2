package mifarma.ptoventa.test_desa;


import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.test_desa.reloj.Hora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgOperaMat extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgReloj.class);
    private JPanelWhite jPanel1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private static ArrayList parametros = new ArrayList();
    private JButton btnCerrar = new JButton();
    private JLabel lblHora = new Hora();
    private JButton btnDivide = new JButton();
    private JTextField txtOperador1 = new JTextField();
    private JTextField txtOperador2 = new JTextField();
    private JTextField txtRpta = new JTextField();
    private JTextField txtCociente = new JTextField();
    private JTextField txtRest0 = new JTextField();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JTextField txtDecimal = new JTextField();
    private JTextField txtRedondeo1 = new JTextField();
    private JButton btnRedondeo = new JButton();
    private JTextField txtFactor = new JTextField();
    private JTextField txtRedondear2 = new JTextField();

    public DlgOperaMat(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgOperaMat() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void jbInit() throws Exception {
        this.setSize(new Dimension(385, 280));
        this.getContentPane().setLayout(gridLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("RELOJ");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        this.getContentPane().setLayout(gridLayout1);

        btnCerrar.setText("[ESC] Cerrar");
        btnCerrar.setBounds(new Rectangle(275, 230, 95, 20));
        btnCerrar.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnCerrar_KeyPressed(e);
                }
            });
        btnCerrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnCerrar_ActionPerformed(e);
                }
            });
        lblHora.setText("Hora");
        lblHora.setBounds(new Rectangle(5, 0, 100, 30));

        btnDivide.setText("Divide");
        btnDivide.setBounds(new Rectangle(130, 45, 75, 21));
        btnDivide.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnDivide_ActionPerformed(e);
                }
            });
        txtOperador1.setBounds(new Rectangle(20, 30, 90, 20));
        txtOperador1.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    txtOpera1_KeyTyped(e);
                }
            });
        txtOperador2.setBounds(new Rectangle(20, 70, 90, 20));
        txtOperador2.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    txtOpera2_KeyTyped(e);
                }
            });
        txtRpta.setBounds(new Rectangle(235, 10, 95, 20));
        txtCociente.setBounds(new Rectangle(235, 45, 95, 20));
        txtRest0.setBounds(new Rectangle(235, 80, 95, 20));
        jLabel1.setText("Respuesta");
        jLabel1.setBounds(new Rectangle(260, 30, 60, 15));
        jLabel2.setText("Cociente");
        jLabel2.setBounds(new Rectangle(260, 65, 60, 15));
        jLabel3.setText("Resto");
        jLabel3.setBounds(new Rectangle(265, 100, 34, 14));
        jLabel4.setText("divisor");
        jLabel4.setBounds(new Rectangle(45, 50, 40, 15));
        jLabel5.setText("dividendo");
        jLabel5.setBounds(new Rectangle(40, 90, 60, 15));
        txtDecimal.setBounds(new Rectangle(20, 125, 90, 20));
        txtDecimal.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    txtDecimal_KeyTyped(e);
                }
            });
        txtRedondeo1.setBounds(new Rectangle(235, 120, 95, 20));
        btnRedondeo.setText("Redondear");
        btnRedondeo.setBounds(new Rectangle(125, 140, 90, 20));
        btnRedondeo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRedondear_ActionPerformed(e);
                }
            });
        txtFactor.setBounds(new Rectangle(20, 155, 90, 20));
        txtFactor.setText("1");
        txtFactor.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    txtFactor_KeyTyped(e);
                }
            });
        txtRedondear2.setBounds(new Rectangle(235, 155, 95, 20));
        jPanel1.add(txtRedondear2, null);
        jPanel1.add(txtFactor, null);
        jPanel1.add(btnRedondeo, null);
        jPanel1.add(txtRedondeo1, null);
        jPanel1.add(txtDecimal, null);
        jPanel1.add(jLabel5, null);
        jPanel1.add(jLabel4, null);
        jPanel1.add(jLabel3, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(jLabel1, null);
        jPanel1.add(txtRest0, null);
        jPanel1.add(txtCociente, null);
        jPanel1.add(txtRpta, null);
        jPanel1.add(txtOperador2, null);
        jPanel1.add(txtOperador1, null);
        jPanel1.add(btnDivide, null);
        jPanel1.add(btnCerrar, null);
        jPanel1.add(lblHora, null);
        this.getContentPane().add(jPanel1, null);
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(btnCerrar);
        lblHora = new Hora();
        //lblHora.setBounds(280, 5, 150, 30);
        lblHora.setFont(new Font("Trebuchet MS",Font.BOLD,20));        
    }
    
    private void this_windowClosing(WindowEvent e) {
        cerrarVentana();
    }
    
    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    private void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            e.consume();            
            cerrarVentana();
        }
    }
    
    private void muestraMsj(String msj) {
        FarmaUtility.showMessage(this, msj, null);
    }   

    private void btnCerrar_KeyPressed(KeyEvent e) {
        keyPressed(e);
    }

    private void btnCerrar_ActionPerformed(ActionEvent e) {
        cerrarVentana();
    }

    private void txtOpera1_KeyTyped(KeyEvent e) {
        FarmaUtility.admitirSoloDigitos(e, txtOperador1, 0, this);
    }

    private void txtOpera2_KeyTyped(KeyEvent e) {
        FarmaUtility.admitirSoloDigitos(e, txtOperador2, 0, this);
    }

    private void btnDivide_ActionPerformed(ActionEvent e) {
        int num1=Integer.parseInt(txtOperador1.getText().trim().toString());
        int num2=Integer.parseInt(txtOperador2.getText().trim().toString());
        
        double rpta=Double.parseDouble(txtOperador1.getText().trim().toString())/Double.parseDouble(txtOperador2.getText().trim().toString());
        int cociente=num1/num2;
        int resto=num1%num2;
        
        txtRpta.setText(rpta+"");
        txtCociente.setText(cociente+"");
        txtRest0.setText(resto+"");
    }

    private void btnRedondear_ActionPerformed(ActionEvent e) {
        double nro=Double.parseDouble(txtDecimal.getText().trim());
        txtRedondear2.setText(FarmaUtility.getRedondeo(nro)+"");
        int factor=Integer.parseInt(txtFactor.getText().trim());
        nro = nro*factor;
        nro=Math.round(nro);
        nro=nro/factor;
        txtRedondeo1.setText(nro+"");
    }

    private void txtDecimal_KeyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtDecimal, e);
    }

    private void txtFactor_KeyTyped(KeyEvent e) {
        FarmaUtility.admitirSoloDigitos(e, txtFactor, 0, this);
        
    }
}
