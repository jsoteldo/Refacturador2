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

import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.imptermica.UtilPrinterCampanaCupon;

import mifarma.ptoventa.caja.UtilNuevoCobro.UtilityNuevoCobro;
import mifarma.ptoventa.test_desa.casos.DlgImp_CodBarra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DlgEncripta extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgImp_CodBarra.class);
    private JPanelWhite jPanel1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private static ArrayList parametros = new ArrayList();
    private JButton btnDesEncripta = new JButton();
    private JTextField txtTexto = new JTextField();
    private JButton btnEncripta = new JButton();
    private JTextField txtEncriptado = new JTextField();
    private JTextField txtDesEncripta = new JTextField();
    private JButton btnProcesa = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();

    public DlgEncripta(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgEncripta() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(385, 175));
        this.getContentPane().setLayout(gridLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Encripta textos");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        this.getContentPane().setLayout(gridLayout1);
        btnDesEncripta.setText("Desencripta");
        btnDesEncripta.setBounds(new Rectangle(130, 110, 120, 20));
        btnDesEncripta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDesencripta_ActionPerformed(e);
            }
        });
        txtTexto.setBounds(new Rectangle(125, 20, 145, 20));
        txtTexto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtTexto_KeyPressed(e);
            }
        });
        btnEncripta.setText("Encripta");
        btnEncripta.setBounds(new Rectangle(15, 110, 100, 20));

        btnEncripta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEncripta_ActionPerformed(e);
            }
        });
        txtEncriptado.setBounds(new Rectangle(30, 70, 140, 20));
        txtEncriptado.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtEncripta_KeyPressed(e);
            }
        });
        txtDesEncripta.setBounds(new Rectangle(215, 70, 140, 20));
        txtDesEncripta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtDesEncripta_KeyPressed(e);
            }
        });
        btnProcesa.setText("Procesar");
        btnProcesa.setBounds(new Rectangle(270, 110, 90, 20));
        btnProcesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtProcesa_ActionPerformed(e);
            }
        });
        jLabel1.setText("Texto Encriptado");
        jLabel1.setBounds(new Rectangle(30, 55, 130, 15));
        jLabel2.setText("Texto Desencriptado");
        jLabel2.setBounds(new Rectangle(215, 55, 130, 15));
        jLabel3.setText("Texto a Encriptar");
        jLabel3.setBounds(new Rectangle(125, 5, 145, 15));
        jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(jLabel3, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(jLabel1, null);
        jPanel1.add(btnProcesa, null);
        jPanel1.add(txtDesEncripta, null);
        jPanel1.add(txtEncriptado, null);
        jPanel1.add(btnEncripta, null);
        jPanel1.add(txtTexto, null);
        jPanel1.add(btnDesEncripta, null);
        this.getContentPane().add(jPanel1, null);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtTexto);
    }

    private void this_windowClosing(WindowEvent e) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    private void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            e.consume();
            cerrarVentana();
        }
    }

    private void muestraMsj(String msj) {
        FarmaUtility.showMessage(this, msj, txtTexto);
    }

    private void txtTexto_KeyPressed(KeyEvent e) {
        keyPressed(e);
    }

    private void txtEncripta_KeyPressed(KeyEvent e) {
        keyPressed(e);
    }

    private void txtDesEncripta_KeyPressed(KeyEvent e) {
        keyPressed(e);
    }

    private void btnEncripta_ActionPerformed(ActionEvent e) {
        String txt=txtTexto.getText();
        String txtEncripta=encripta(txt);
        txtEncriptado.setText(txtEncripta);
        FarmaUtility.moveFocus(txtTexto);
    }

    private void btnDesencripta_ActionPerformed(ActionEvent e) {
        String txt=txtTexto.getText();
        String txtDEncripta=desencripta(txt);
        txtDesEncripta.setText(txtDEncripta);
        FarmaUtility.moveFocus(txtTexto);
    }

    private void txtProcesa_ActionPerformed(ActionEvent e) {
        String txt=txtTexto.getText();
        String text1=encripta(txt);
        txtEncriptado.setText(text1);
        String text2=desencripta(txt);
        txtDesEncripta.setText(text2);
        FarmaUtility.moveFocus(txtTexto);
    }

    private static String encripta(String texto) {
        String textoEncriptado = "";
        BASE64Encoder encoder = new BASE64Encoder();
        textoEncriptado = encoder.encode(texto.getBytes());
        return textoEncriptado;
    }

    public static String desencripta(String textoEncriptado) {
        String texto = "";
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            texto = new String(decoder.decodeBuffer(textoEncriptado));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
    }
}
