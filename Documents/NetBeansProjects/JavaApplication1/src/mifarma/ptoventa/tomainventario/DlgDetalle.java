package mifarma.ptoventa.tomainventario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import java.awt.Label;

import java.awt.Rectangle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

public class DlgDetalle extends JDialog {

    private JPanel pnlFondo = new JPanel();
    private JLabel lblMensaje = new JLabel();
    JLabel lblEsc = new JLabel();
    private JLabel lblMonto = new JLabel();
    private Frame parentFrame;
    JLabel lblFactura = new JLabel();
    private Label lblDigitacion = new Label();
    private Label lblPda = new Label();
    private Label lblPdaFrac = new Label();
    private Label lblDigitacionFrac = new Label();
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();

    public DlgDetalle() {
        this(null, "", false);
    }

    public DlgDetalle(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setTitle("Detalle");
        this.setSize(new Dimension(287, 161));
        this.getContentPane().setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                this_keyPressed(e);
            }
        });
        pnlFondo.setBounds(new Rectangle(9, 9, 252, 87));
        pnlFondo.setBorder(BorderFactory.createTitledBorder(""));
        pnlFondo.setLayout(null);
        lblMensaje.setText("Digitación :");
        lblMensaje.setBounds(new Rectangle(12, 27, 63, 21));
        lblEsc.setText("[ Esc ]  Salir");
        lblEsc.setBounds(new Rectangle(186, 99, 69, 18));
        lblEsc.setFont(new Font("SansSerif", 1, 11));
        lblEsc.setForeground(new Color(32, 105, 29));
        lblMonto.setText("PDA :");
        lblMonto.setBounds(new Rectangle(12, 54, 63, 21));
        lblMonto.setToolTipText("null");
        lblFactura.setText("[ F6 ] Factura");
        lblFactura.setBounds(new Rectangle(120, 90, 81, 15));
        lblFactura.setFont(new Font("SansSerif", 1, 11));
        lblFactura.setForeground(new Color(32, 105, 29));
        lblFactura.setVisible(false);
        lblDigitacion.setText("150");
        lblDigitacion.setBounds(new Rectangle(99, 27, 45, 21));
        lblDigitacion.setAlignment(2);
        lblPda.setText("25000");
        lblPda.setBounds(new Rectangle(99, 54, 45, 21));
        lblPda.setAlignment(2);
        lblPdaFrac.setText("25000");
        lblPdaFrac.setBounds(new Rectangle(177, 54, 45, 21));
        lblPdaFrac.setAlignment(2);
        lblDigitacionFrac.setText("150");
        lblDigitacionFrac.setBounds(new Rectangle(177, 27, 45, 21));
        lblDigitacionFrac.setAlignment(2);
        label1.setText("Entero");
        label1.setBounds(new Rectangle(111, 6, 42, 21));
        label2.setText("Entero");
        label2.setBounds(new Rectangle(111, 6, 42, 21));
        label3.setText("Fracción");
        label3.setBounds(new Rectangle(183, 6, 49, 21));

        pnlFondo.add(label3, null);
        pnlFondo.add(label2, null);
        pnlFondo.add(label1, null);
        pnlFondo.add(lblDigitacionFrac, null);
        pnlFondo.add(lblPdaFrac, null);
        pnlFondo.add(lblPda, null);
        pnlFondo.add(lblDigitacion, null);
        pnlFondo.add(lblMonto, null);
        pnlFondo.add(lblMensaje, null);
        this.getContentPane().add(lblFactura, null);
        this.getContentPane().add(lblEsc, null);
        this.getContentPane().add(pnlFondo, null);
    }

    public Label getLblDigitacion() {
        return lblDigitacion;
    }

    public void setLblDigitacion(Label lblDigitacion) {
        this.lblDigitacion = lblDigitacion;
    }

    public Label getLblPda() {
        return lblPda;
    }

    public void setLblPda(Label lblPda) {
        this.lblPda = lblPda;
    }


    public Label getLblPdaFrac() {
        return lblPdaFrac;
    }

    public void setLblPdaFrac(Label lblPdaFrac) {
        this.lblPdaFrac = lblPdaFrac;
    }

    public Label getLblDigitacionFrac() {
        return lblDigitacionFrac;
    }

    public void setLblDigitacionFrac(Label lblDigitacionFrac) {
        this.lblDigitacionFrac = lblDigitacionFrac;
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
    }

    private void this_keyPressed(KeyEvent e) {
        e.consume();
        closeWindow(false);
    }

    private void closeWindow(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
}

