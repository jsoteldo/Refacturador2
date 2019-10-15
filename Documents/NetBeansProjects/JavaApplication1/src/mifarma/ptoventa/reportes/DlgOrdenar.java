package mifarma.ptoventa.reportes;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reportes.reference.ConstantsReporte;
import mifarma.ptoventa.reportes.reference.VariablesReporte;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgOrdenar extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgOrdenar.class);

    private Frame myParentFrame;
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JButtonLabel jButtonLabel1 = new JButtonLabel();
    private JButtonLabel jButtonLabel2 = new JButtonLabel();
    private JComboBox cmbCampo = new JComboBox();
    private JComboBox cmbOrden = new JComboBox();
    private JLabelFunction jLabelFunction1 = new JLabelFunction();
    private JLabelFunction jLabelFunction2 = new JLabelFunction();
    private JLabelFunction jLabelFunction3 = new JLabelFunction();

    public DlgOrdenar() {
        this(null, "", false);
    }

    public DlgOrdenar(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void initialize() {
        FarmaVariables.vAceptar = false;
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(421, 191));
        this.getContentPane().setLayout(gridLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Ordenar");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jPanelTitle1.setBounds(new Rectangle(15, 15, 380, 105));
        jPanelTitle1.setBackground(Color.white);
        jPanelTitle1.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        jButtonLabel1.setText("Columna");
        jButtonLabel1.setBounds(new Rectangle(15, 15, 135, 20));
        jButtonLabel1.setBackground(new Color(255, 130, 14));
        jButtonLabel1.setForeground(new Color(255, 130, 14));
        jButtonLabel1.setMnemonic('c');
        jButtonLabel1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonLabel1_actionPerformed(e);
            }
        });
        jButtonLabel2.setText("Orden");
        jButtonLabel2.setBounds(new Rectangle(15, 60, 65, 20));
        jButtonLabel2.setBackground(new Color(255, 130, 14));
        jButtonLabel2.setForeground(new Color(255, 130, 14));
        jButtonLabel2.setMnemonic('o');
        jButtonLabel2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButtonLabel2_actionPerformed(e);
            }
        });
        cmbCampo.setBounds(new Rectangle(110, 15, 170, 20));
        cmbCampo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                cmbCampo_keyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
                cmbCampo_keyPressed(e);
            }
        });
        cmbOrden.setBounds(new Rectangle(110, 55, 125, 20));
        cmbOrden.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                cmbOrden_keyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
                cmbOrden_keyPressed(e);
            }

        });
        cmbOrden.addMouseListener(new MouseAdapter() {

        });

        jLabelFunction1.setBounds(new Rectangle(90, 130, 95, 20));
        jLabelFunction1.setText("[ ENTER ] Elegir");
        jLabelFunction1.setBackground(SystemColor.window);
        jLabelFunction1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelFunction1.setFont(new Font("SansSerif", 1, 11));
        jLabelFunction1.setForeground(new Color(32, 105, 29));
        jLabelFunction2.setBounds(new Rectangle(300, 130, 95, 20));
        jLabelFunction2.setText("[ ESC ] Escape");
        jLabelFunction2.setBackground(SystemColor.window);
        jLabelFunction2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelFunction2.setFont(new Font("SansSerif", 1, 11));
        jLabelFunction2.setForeground(new Color(32, 105, 29));
        jLabelFunction3.setBounds(new Rectangle(195, 130, 95, 20));
        jLabelFunction3.setText("[ F11 ] Aceptar");
        jLabelFunction3.setBackground(SystemColor.window);
        jLabelFunction3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelFunction3.setFont(new Font("SansSerif", 1, 11));
        jLabelFunction3.setForeground(new Color(32, 105, 29));
        jPanelTitle1.add(cmbOrden, null);
        jPanelTitle1.add(jButtonLabel2, null);
        jPanelTitle1.add(jButtonLabel1, null);
        jPanelTitle1.add(cmbCampo, null);
        jPanelWhite1.add(jLabelFunction3, null);
        jPanelWhite1.add(jLabelFunction2, null);
        jPanelWhite1.add(jLabelFunction1, null);
        jPanelWhite1.add(jPanelTitle1, null);
        this.getContentPane().add(jPanelWhite1, null);
    }

    private void cargaCombos() {
        FarmaLoadCVL.loadCVLfromArrays(cmbOrden, VariablesReporte.vNombreInHashtableVal, ConstantsReporte.ORDENVAL,
                                       ConstantsReporte.ORDEN, true);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        cargaCombos();
        FarmaUtility.moveFocus(cmbCampo);
    }

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            VariablesReporte.vCampo =
                    Integer.parseInt(FarmaLoadCVL.getCVLCode(VariablesReporte.vNombreInHashtable, cmbCampo.getSelectedIndex()));
            VariablesReporte.vOrden =
                    FarmaLoadCVL.getCVLCode(VariablesReporte.vNombreInHashtableVal, cmbOrden.getSelectedIndex());
            cerrarVentana(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void jButtonLabel1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbCampo);
    }

    private void cmbCampo_keyReleased(KeyEvent e) {

    }

    private void jButtonLabel2_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbOrden);
    }

    private void cmbOrden_keyReleased(KeyEvent e) {

    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void cmbCampo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbOrden);
        } else {
            chkKeyPressed(e);
        }
    }

    private void cmbOrden_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbCampo);
        } else {
            chkKeyPressed(e);
        }
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    public JComboBox getCmbCampo() {
        return this.cmbCampo;
    }


}
