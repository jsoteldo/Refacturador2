package mifarma.ptoventa.test_desa.UtilTest;

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

import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.test_desa.DlgReloj;
import mifarma.ptoventa.test_desa.reloj.Hora;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgInterfaz  extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgReloj.class);
    private JPanelWhite jPanel1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private static ArrayList parametros = new ArrayList();
    private JButton btnCerrar = new JButton();
    private JLabel lblHora = new Hora();
    private JLabel lblImagen = new JLabel();


    public DlgInterfaz(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgInterfaz() {
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
        btnCerrar.setBounds(new Rectangle(270, 100, 95, 30));
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
        lblHora.setBounds(new Rectangle(10, 100, 260, 30));

        lblImagen.setText("");
        lblImagen.setBounds(new Rectangle(10, 5, 355, 90));
        jPanel1.add(lblImagen, null);
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
        
        String path = "/cargando.gif";  
        URL url = this.getClass().getResource(path);  
        ImageIcon icon = new ImageIcon(url);  
          
        lblImagen = new JLabel("");  
        lblImagen.setIcon(icon);  
        
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
}
