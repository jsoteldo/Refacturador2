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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.test_desa.UtilTest.DlgInterfaz;
import mifarma.ptoventa.test_desa.reloj.Hora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgReloj extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgReloj.class);
    private Frame myParentFrame;
    private JPanelWhite jPanel1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private static ArrayList parametros = new ArrayList();
    private JButton btnCerrar = new JButton();
    private JLabel lblHora = new Hora();
    private JButton jButton1 = new JButton();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JButton btnInterfaz = new JButton();

    public DlgReloj(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgReloj() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(385, 164));
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
        btnCerrar.setBounds(new Rectangle(15, 110, 95, 20));
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

        jButton1.setText("Fecha Formato");
        jButton1.setBounds(new Rectangle(125, 110, 130, 20));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fecha_ActionPerformed(e);
                }
            });
        jTextField1.setBounds(new Rectangle(15, 40, 160, 20));
        jTextField2.setBounds(new Rectangle(200, 40, 135, 20));
        btnInterfaz.setText("Interfaz");
        btnInterfaz.setBounds(new Rectangle(270, 110, 75, 21));
        btnInterfaz.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnInterfaz_actionPerformed(e);
                }
            });
        jPanel1.add(btnInterfaz, null);
        jPanel1.add(jTextField2, null);
        jPanel1.add(jTextField1, null);
        jPanel1.add(jButton1, null);
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

    private void fecha_ActionPerformed(ActionEvent e) {
        Date myDate = new Date();
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(myDate);
        muestraMsj(" ===> "+fecha);
        
    }

    private void btnInterfaz_actionPerformed(ActionEvent e) {
        DlgInterfaz dlg = new DlgInterfaz(myParentFrame,"",true);
        dlg.setVisible(true);
    }
}
