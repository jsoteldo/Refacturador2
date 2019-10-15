package mifarma.ptoventa.test_desa;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.fraccion.modelo.VariableProducto;
import mifarma.ptoventa.test_desa.CodeQR.DlgCodeQR;
import mifarma.ptoventa.test_desa.backup.DlgMenu01;
import mifarma.ptoventa.test_desa.casos.DlgImp_CodBarra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgTestMenu extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgTestMenu.class);
    private Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlFechas = new JPanel();
    private JPanel pnlListaSolicitud = new JPanel();
    private JLabel lblSolicitudCant = new JLabel();
    private JButton lblTitulo = new JButton();
    private JButton btnCodeQR = new JButton();
    private JButton btnCodBarra = new JButton();
    private JButton btnSalir = new JButton();
    private JButton btnJTable = new JButton();
    private JButton btnReloj = new JButton();
    private JButton btnNewMenu = new JButton();
    private JButton btnEncripta = new JButton();
    private JButton btnOperaciones = new JButton();

    public DlgTestMenu() {
        super();
    }

    public DlgTestMenu(Frame parent, String title, boolean modal) {
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
        ctpContenido.setBackground(Color.white);
        ctpContenido.setLayout(null);
        ctpContenido.setSize(new Dimension(623, 439));
        ctpContenido.setForeground(Color.white);

        pnlFechas.setBounds(new Rectangle(15, 5, 770, 40));
        pnlFechas.setBackground(new Color(43, 141, 39));
        pnlFechas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlFechas.setLayout(null);


        lblSolicitudCant.setBounds(new Rectangle(385, 2, 380, 15));
        lblSolicitudCant.setText("");
        lblSolicitudCant.setFont(new Font("SansSerif", 1, 12));
        lblSolicitudCant.setForeground(SystemColor.window);
        lblSolicitudCant.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolicitudCant.setHorizontalTextPosition(SwingConstants.RIGHT);
                
        lblTitulo.setText("INTERFACE DE PRUEBA DE CODIGOS");
        lblTitulo.setBounds(new Rectangle(5, 10, 760, 15));
        lblTitulo.setBackground(SystemColor.window);
        lblTitulo.setForeground(SystemColor.window);
        lblTitulo.setFont(new Font("SansSerif", 1, 14));
        
        lblTitulo.setMnemonic('I');
        lblTitulo.setBackground(new Color(255, 130, 14));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblTitulo.setBorderPainted(false);
        lblTitulo.setContentAreaFilled(false);
        lblTitulo.setDefaultCapable(false);
        lblTitulo.setFocusPainted(false);
        lblTitulo.setForeground(Color.white);
        lblTitulo.setFont(new Font("SansSerif", 1, 11));
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitulo.setRequestFocusEnabled(false);
        
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    lblTitulo_KeyPressed(e);
                }
            });
        btnCodeQR.setText("[F3] CodeQR");
        btnCodeQR.setBounds(new Rectangle(50, 195, 165, 20));
        btnCodeQR.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CodeQR_ActionPerformed(e);
                }
            });
        btnCodBarra.setText("[F2] Imprime Cod Barra");
        btnCodBarra.setBounds(new Rectangle(50, 165, 165, 20));
        btnCodBarra.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CodBarra_ActionPerformed(e);
                }
            });
        btnSalir.setText("[ESC] Salir");
        btnSalir.setBounds(new Rectangle(50, 130, 165, 20));
        btnSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Salir_ActionPerformed(e);
                }
            });
        btnJTable.setText("[F4] JTable");
        btnJTable.setBounds(new Rectangle(50, 225, 165, 20));
        btnJTable.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnJTable_ActionPerformed(e);
                }
            });
        btnReloj.setText("[F5] Reloj");
        btnReloj.setBounds(new Rectangle(50, 255, 165, 20));
        btnReloj.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnReloj_ActionPerformed(e);
                }
            });
        btnNewMenu.setText("[Nuevos Menus]");
        btnNewMenu.setBounds(new Rectangle(55, 315, 165, 20));
        /*
        jMenu1.setText("Primero");
        jMenu2.setText("Segundo");
        jMenu3.setText("Salir");
        */
        btnNewMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnNewMenu_ActionPerformed(e);
                }
            });
        btnEncripta.setText("[F6] Encrita Texto");
        btnEncripta.setBounds(new Rectangle(55, 285, 165, 20));
        btnEncripta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnEncripta_ActionPerformed(e);
                }
            });
        btnOperaciones.setText("Operacion Divide");
        btnOperaciones.setBounds(new Rectangle(245, 130, 120, 20));
        btnOperaciones.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnOperaciones_ActionPerformed(e);
                }
            });
        pnlListaSolicitud.setBounds(new Rectangle(15, 40, 770, 20));
        pnlListaSolicitud.setBackground(new Color(255, 130, 14));
        pnlListaSolicitud.setLayout(null);


        pnlFechas.add(lblTitulo, null);
        ctpContenido.add(btnOperaciones, null);
        ctpContenido.add(btnEncripta, null);
        ctpContenido.add(btnNewMenu, null);
        ctpContenido.add(btnReloj, null);
        ctpContenido.add(btnJTable, null);
        ctpContenido.add(btnSalir, null);
        ctpContenido.add(btnCodBarra, null);
        ctpContenido.add(btnCodeQR, null);
        ctpContenido.add(pnlFechas, null);
        pnlListaSolicitud.add(lblSolicitudCant, null);
        ctpContenido.add(pnlListaSolicitud, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);
        /*
        farmaMenu.add(jMenu1);
        farmaMenu.add(jMenu2);
        farmaMenu.add(jMenu3);
        */

    }

    private void initialize() {
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(lblTitulo);
    }

    private void this_windowClosing(WindowEvent e) {
        muestraMensaje("Debe presionar la tecla ESC para cerrar la ventana.", null);
        FarmaUtility.moveFocus(lblTitulo);
    }

    private void evento_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            cerrarVentana();
            break;
        case KeyEvent.VK_F6:
            abrirEncriptar();
            break;
        case KeyEvent.VK_F1:
            abrirParseoTrama();            
            break;
        case KeyEvent.VK_F2:
            abrirImprimeCupon();
            break;
        case KeyEvent.VK_ENTER:
            e.consume();
            break;
        case KeyEvent.VK_F3:
            abrirImprimeQR();
            break;
        case KeyEvent.VK_F4:
            btnJTable.doClick();
            break;
        case KeyEvent.VK_F5:
            btnReloj.doClick();
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

    private void lblTitulo_KeyPressed(KeyEvent e) {
        evento_keyPressed(e);
    }

    private void CodeQR_ActionPerformed(ActionEvent e) {
        abrirImprimeQR();
    }

    private void CodBarra_ActionPerformed(ActionEvent e) {
        abrirImprimeCupon();
    }

    private void Salir_ActionPerformed(ActionEvent e) {
        cerrarVentana();
    }

    private void abrirParseoTrama() {
        DlgMenu01 dlgMenu01 = new DlgMenu01(myParentFrame, "", true);
        dlgMenu01.setVisible(true);
    }

    private void abrirImprimeCupon() {
        DlgImp_CodBarra dCupon = new DlgImp_CodBarra(myParentFrame, "", true);
        dCupon.setVisible(true);
    }

    private void abrirImprimeQR() {
        DlgCodeQR dlqQR =new DlgCodeQR(myParentFrame, "", true);
        dlqQR.setVisible(true);
    }

    private void btnJTable_ActionPerformed(ActionEvent e) {
        DlgJTable_Eventos dlg= new DlgJTable_Eventos(myParentFrame, "", true);
        dlg.setVisible(true);
    }

    private void btnReloj_ActionPerformed(ActionEvent e) {
        DlgReloj reloj = new DlgReloj(myParentFrame, "", true);
        reloj.setVisible(true);
    }

    private void btnNewMenu_ActionPerformed(ActionEvent e) {
        MenuFarmaVenta menu = new MenuFarmaVenta();
        menu.setVisible(true);
    }

    private void abrirEncriptar() {
        DlgEncripta encripta = new DlgEncripta(myParentFrame, "", true);
        encripta.setVisible(true);
    }

    private void btnEncripta_ActionPerformed(ActionEvent e) {
        abrirEncriptar();
    }

    private void btnOperaciones_ActionPerformed(ActionEvent e) {
        DlgOperaMat oper = new DlgOperaMat(myParentFrame, "", true);
        oper.setVisible(true);
    }
}
