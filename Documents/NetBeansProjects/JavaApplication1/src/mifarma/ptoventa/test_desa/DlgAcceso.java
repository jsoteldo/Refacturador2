package mifarma.ptoventa.test_desa;


import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.test_desa.CodeQR.DlgCodeQR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgAcceso extends JDialog {
    private Frame myParentFrame;
    private static final Logger log = LoggerFactory.getLogger(DlgCodeQR.class);
    private JPanelWhite jPanel1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private static ArrayList parametros = new ArrayList();
    private JButton btnSalir = new JButton();
    private JTextField txtCod_Acceso = new JTextField();
    private JButton btnIngreso = new JButton();
    private JEditorPane txtInfo = new JEditorPane();
    private JScrollPane scrPane = new JScrollPane();

    public DlgAcceso(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgAcceso() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void jbInit() throws Exception {
        this.setSize(new Dimension(370, 224));
        this.getContentPane().setLayout(gridLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Ambiente de prueba");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        this.getContentPane().setLayout(gridLayout1);
        btnSalir.setText("Salir");
        btnSalir.setBounds(new Rectangle(215, 160, 75, 21));

        btnSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnSalir_ActionPerformed(e);
                }
            });
        txtCod_Acceso.setBounds(new Rectangle(95, 115, 175, 20));
        txtCod_Acceso.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCodigo_KeyPressed(e);
                }
            });
        btnIngreso.setText("Ingresar");
        btnIngreso.setBounds(new Rectangle(50, 155, 75, 21));

        btnIngreso.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnIngreso_ActionPerformed(e);
                }
            });
        txtInfo.setEditable(false);
        txtInfo.setContentType("text/html");
        txtInfo.setAutoscrolls(true);

        scrPane.setBounds(new Rectangle(10, 15, 340, 85));
        scrPane.setBackground(new Color(255, 130, 14));
        scrPane.getViewport();

        scrPane.getViewport().add(txtInfo, null);
        jPanel1.add(scrPane, null);
        jPanel1.add(btnIngreso, null);
        jPanel1.add(txtCod_Acceso, null);
        jPanel1.add(btnSalir, null);
        this.getContentPane().add(jPanel1, null);
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtCod_Acceso);
        txtInfo.setText(recuperaMsjAcceso(12));
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
        }else if(e.getKeyCode()==KeyEvent.VK_ENTER){
            String dato=txtCod_Acceso.getText().trim();
            if(dato.length()>0){
                ingresarAmbiente(dato.trim());
            }else{
                muestraMsj("No tiene permiso de acceso");
                cerrarVentana();
            }
        }
    }
        
    private void txtCodigo_KeyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            btnIngreso.doClick();
        }
        keyPressed(e);
    }

    private void muestraMsj(String msj) {
        FarmaUtility.showMessage(this, msj, null);
    }

    private void btnIngreso_ActionPerformed(ActionEvent e) {
        
    }

    private void btnSalir_ActionPerformed(ActionEvent e) {
        cerrarVentana();
    }
    
    public static String recuperaMsjAcceso(int tamLetra) {
        String txt="<div style=\"font-size: "+tamLetra+"px\">\n"+
                   //"<b style=\"align-content: center;color: red\">AMBIENTE RESTRINGIDO</b><br>\n" + 
                   "Ingrese el codigo para ingresar a este modulo"+
                   "</div>";
        return txt;
    }
    String codAcceso="NICO";
    private void ingresarAmbiente(String codIngreso) {
        if(codIngreso.toUpperCase().equalsIgnoreCase(codAcceso)){
            DlgTestMenu mnuTest = new DlgTestMenu(this.myParentFrame, "", true);
            mnuTest.setVisible(true);
            cerrarVentana();
        }else{
            muestraMsj("No tiene permitido el acceso");
            cerrarVentana();
        }
    }
}

