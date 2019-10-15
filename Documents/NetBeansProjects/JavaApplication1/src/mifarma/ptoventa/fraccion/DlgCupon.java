package mifarma.ptoventa.fraccion;


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

import mifarma.imptermica.UtilPrinterCampanaCupon;

import mifarma.ptoventa.caja.UtilNuevoCobro.UtilityNuevoCobro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCupon extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgDetalleFraccion.class);
    private JPanelWhite jPanel1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private static ArrayList parametros = new ArrayList();
    private JButton btnImprimir = new JButton();
    private JTextField txtCodigo = new JTextField();
    private JButton btnAgregar = new JButton();
    private JEditorPane txtLista = new JEditorPane();
    private JScrollPane scrPane = new JScrollPane();
    private JButton btnArreglo = new JButton();

    public DlgCupon(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgCupon() {
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
        this.setTitle("CUPON");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        this.getContentPane().setLayout(gridLayout1);
        btnImprimir.setText("Imprimir");
        btnImprimir.setBounds(new Rectangle(110, 75, 75, 21));
        btnImprimir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnImprimir_ActionPerformed(e);
                }
            });
        btnImprimir.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnImprimir_KeyPressed(e);
                }
            });
        txtCodigo.setBounds(new Rectangle(10, 20, 175, 20));
        txtCodigo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCodigo_KeyPressed(e);
                }
            });
        btnAgregar.setText("Agregar");
        btnAgregar.setBounds(new Rectangle(10, 75, 75, 21));
        btnAgregar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAgregar_ActionPerformed(e);
                }
            });
        txtLista.setEditable(false);
        txtLista.setContentType("text/html");
        txtLista.setAutoscrolls(true);

        scrPane.setBounds(new Rectangle(195, 20, 165, 100));
        scrPane.setBackground(new Color(255, 130, 14));
        btnArreglo.setText("Enviar Arreglo");
        btnArreglo.setBounds(new Rectangle(35, 110, 110, 20));
        btnArreglo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnArreglo_ActionPerformed(e);
                }
            });
        scrPane.getViewport();

        scrPane.getViewport().add(txtLista, null);
        jPanel1.add(btnArreglo, null);
        jPanel1.add(scrPane, null);
        jPanel1.add(btnAgregar, null);
        jPanel1.add(txtCodigo, null);
        jPanel1.add(btnImprimir, null);
        this.getContentPane().add(jPanel1, null);
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
    }
    
    private void this_windowClosing(WindowEvent e) {
        cerrarVentana();
    }
    
    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    private void btnImprimir_ActionPerformed(ActionEvent e) {
        //UtilPrinterCampanaCupon.pruebaImpresion();
        //UtilPrinterCampanaCupon.imprimeCodBarra();
        if(!listaCodigos.isEmpty()){
            String cadLista=listaCodigos.get(0).toString();
            for(int i=1;i<listaCodigos.size();i++){
                cadLista=cadLista+"@"+listaCodigos.get(i).toString();
            }
            UtilPrinterCampanaCupon.imprimeCodBarra(cadLista);
        }
        txtCodigo.setText("");
        FarmaUtility.moveFocus(txtCodigo);
    }

    private void btnImprimir_KeyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_F11){
            e.consume();
            btnImprimir.doClick();
        }
        keyPressed(e);
    }
    
    private void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            e.consume();
            listaCodigos = new ArrayList<String>();
            cerrarVentana();
        }else if(e.getKeyCode()==KeyEvent.VK_F11){
            btnImprimir.doClick();
        }
    }
    private ArrayList<String> listaCodigos = new ArrayList<String>();
    private void btnAgregar_ActionPerformed(ActionEvent e) {
        agregarLista();
        txtCodigo.setText("");
        FarmaUtility.moveFocus(txtCodigo);
    }

    private void txtCodigo_KeyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            btnAgregar.doClick();
        }
        keyPressed(e);
    }

    private void agregarLista() {
        String txt=txtCodigo.getText().trim();
        if(!txt.equalsIgnoreCase("")){
            listaCodigos.add(txt);
            txtCodigo.setText("");
        }
        FarmaUtility.moveFocus(txtCodigo);
        if(!listaCodigos.isEmpty()){
            String cadLista=listaCodigos.get(0).toString();
            for(int i=1;i<listaCodigos.size();i++){
                cadLista=cadLista+"<br>"+listaCodigos.get(i).toString();
            }
            txtLista.setText(cadLista);
        }
    }

    private void btnArreglo_ActionPerformed(ActionEvent e) {
        ArrayList<ArrayList> arreglo=new ArrayList();
        ArrayList arreglo1=new ArrayList();
        arreglo1.add("PRUEBA");
        arreglo1.add("DE");
        arreglo1.add("ENVIO");
        arreglo1.add("DE");
        arreglo1.add("ARREGLOS");
        arreglo1.add("A");
        arreglo1.add("BASE");
        arreglo1.add("DE");
        arreglo1.add("DATOS");
        arreglo.add(arreglo1);
        
        ArrayList arreglo2=new ArrayList();
        arreglo2.add("RICHARD");
        arreglo2.add("NICOLA");
        arreglo2.add("ARGUMEDO");
        arreglo2.add("LOJA");
        arreglo2.add("PRUEBA");
        arreglo2.add("AGREGLO");
        arreglo2.add("BI");
        arreglo2.add("-");
        arreglo2.add("DIMENCIONAL");
        arreglo.add(arreglo2);
        
        String msj=UtilityNuevoCobro.enviarArreglo(arreglo);
        FarmaUtility.showMessage(this, msj, null);
    }
}
