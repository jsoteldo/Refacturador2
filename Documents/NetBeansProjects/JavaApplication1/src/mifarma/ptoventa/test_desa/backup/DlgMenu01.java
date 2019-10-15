package mifarma.ptoventa.test_desa.backup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.fraccion.DlgCupon;
import mifarma.ptoventa.fraccion.modelo.VariableProducto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgMenu01 extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgMenu01.class);
    private Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlFechas = new JPanel();
    private JPanel pnlListaSolicitud = new JPanel();
    private JLabel lblSolicitudCant = new JLabel();
    private JButton btnPrueba1 = new JButton();
    private JTextField txtMonto = new JTextField();
    private JButton btnCeros = new JButton();

    public DlgMenu01() {
        super();
    }

    public DlgMenu01(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(801, 477));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Menu de pruebas");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.white);
        this.setBackground(Color.white);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });

        this.addInputMethodListener(new InputMethodListener() {
            public void inputMethodTextChanged(InputMethodEvent e) {
            }

            public void caretPositionChanged(InputMethodEvent e) {
                btnCeros_ActionPerformed(e);
            }
        });
        ctpContenido.setBackground(Color.white);
        ctpContenido.setLayout(null);
        ctpContenido.setSize(new Dimension(623, 439));
        ctpContenido.setForeground(Color.white);

        pnlFechas.setBounds(new Rectangle(15, 5, 770, 75));
        pnlFechas.setBackground(new Color(43, 141, 39));
        pnlFechas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlFechas.setLayout(null);


        lblSolicitudCant.setBounds(new Rectangle(385, 2, 380, 15));
        lblSolicitudCant.setText("");
        lblSolicitudCant.setFont(new Font("SansSerif", 1, 12));
        lblSolicitudCant.setForeground(SystemColor.window);
        lblSolicitudCant.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolicitudCant.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnPrueba1.setText("decimal");
        btnPrueba1.setBounds(new Rectangle(280, 160, 75, 21));
        btnPrueba1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPrueba1_ActionPerformed(e);
            }
        });
        txtMonto.setBounds(new Rectangle(160, 130, 200, 20));
        txtMonto.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtMonto_KeyPressed(e);
                }
            });
        btnCeros.setText("ceros");
        btnCeros.setBounds(new Rectangle(170, 160, 75, 21));
        pnlListaSolicitud.setBounds(new Rectangle(15, 80, 770, 20));
        pnlListaSolicitud.setBackground(new Color(255, 130, 14));
        pnlListaSolicitud.setLayout(null);


        ctpContenido.add(btnCeros, null);
        ctpContenido.add(txtMonto, null);
        ctpContenido.add(btnPrueba1, null);
        ctpContenido.add(pnlFechas, null);
        pnlListaSolicitud.add(lblSolicitudCant, null);
        ctpContenido.add(pnlListaSolicitud, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);

    }

    private void initialize() {
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
    }

    private void this_windowClosing(WindowEvent e) {
        muestraMensaje("Debe presionar la tecla ESC para cerrar la ventana.", null);
        FarmaUtility.moveFocus(txtMonto);
    }

    private void evento_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            cerrarVentana();
            break;
        case KeyEvent.VK_B:
            break;
        case KeyEvent.VK_F1:
            break;
        case KeyEvent.VK_ENTER:
            e.consume();
            break;
        case KeyEvent.VK_F5:
            e.consume();
            break;
        case KeyEvent.VK_F2:
            break;
        case KeyEvent.VK_F12:
            DlgCupon dCupon = new DlgCupon(myParentFrame, "", true);
            dCupon.setVisible(true);
            break;
        }
    }

    private void cerrarVentana() {
        VariableProducto.listaMemoriaProd = new ArrayList();
        this.setVisible(false);
        this.dispose();
    }


    private void muestraMensaje(String msj, Object cTxt) {
        FarmaUtility.showMessage(this, msj, cTxt);
    }

    private void btnPrueba1_ActionPerformed(ActionEvent e) {
        String nroMonto = txtMonto.getText();
        //String cadena=String.format("%010f",Double.parseDouble(nroMonto));
        /*
            DecimalFormatSymbols simboloDecimal= new DecimalFormatSymbols();
            simboloDecimal.setDecimalSeparator('.');
            DecimalFormat myFormat=new DecimalFormat("#.000",simboloDecimal);
            String cadena=myFormat.format(Double.parseDouble(nroMonto));
            */
        String redondeoPago = ".0";

        DecimalFormatSymbols simboloDecimal = new DecimalFormatSymbols();
        simboloDecimal.setDecimalSeparator('.');

        DecimalFormat formatoMonto = new DecimalFormat("000000000.00", simboloDecimal);
        String cadenaMonto = formatoMonto.format(Double.parseDouble(nroMonto));


        DecimalFormat formatoRedondeo = new DecimalFormat("#.000", simboloDecimal);
        String cadenaRedon = formatoRedondeo.format(Double.parseDouble(redondeoPago));

        String cadenaPago =
            "S/." + cadenaMonto + String.format("%012d", 0) + String.format("%012d", 0) + cadenaRedon + String.format("%04d",
                                                                                                                      0) +
            String.format("%04d", 0);

        FarmaUtility.showMessage(this, cadenaPago, null);
    }

    private void btnCeros_ActionPerformed(InputMethodEvent e) {
        String nroMonto = txtMonto.getText();
        //String cadena=String.format("%010f",Double.parseDouble(nroMonto));
        DecimalFormatSymbols simboloDecimal = new DecimalFormatSymbols();
        simboloDecimal.setDecimalSeparator('.');
        DecimalFormat myFormat = new DecimalFormat("000000000.00", simboloDecimal);
        String cadena = myFormat.format(Double.parseDouble(nroMonto));

        FarmaUtility.showMessage(this, cadena, null);
    }

    private void txtMonto_KeyPressed(KeyEvent e) {
        evento_keyPressed(e);
    }
}
