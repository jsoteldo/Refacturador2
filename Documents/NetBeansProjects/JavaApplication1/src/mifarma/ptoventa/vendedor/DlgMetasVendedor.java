package mifarma.ptoventa.vendedor;


import com.gs.mifarma.componentes.JPanelWhite;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.vendedor.UtilVendedor.UtilityVendedor;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgMetasVendedor  extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgMetasVendedor.class);
    private Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JButton lblTitulo = new JButton();
    
    private JPanelWhite pnlInformacion = new JPanelWhite();
    private JScrollPane scrPane = new JScrollPane();
    private JEditorPane txtInfo = new JEditorPane();
    private JButton btnSalir = new JButton();
    private JPanel pnlDatosGral = new JPanel();
    private JLabel lblCajero = new JLabel();
    private JLabel txtCajero = new JLabel();
    private JLabel lblTurno = new JLabel();
    private JLabel txtTurno = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel txtFecha = new JLabel();

    public DlgMetasVendedor() {
        super();
    }
    
    public DlgMetasVendedor(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(447, 492));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Avances de Ventas Vendedor");
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


        lblTitulo.setText(" AVANCE DE METAS DE VENTA POR VENDEDOR");
        lblTitulo.setBounds(new Rectangle(10, 10, 395, 15));
        lblTitulo.setBackground(SystemColor.window);
        lblTitulo.setForeground(SystemColor.window);
        lblTitulo.setFont(new Font("SansSerif", 1, 14));
        
        lblTitulo.setMnemonic('A');
        lblTitulo.setBackground(new Color(255, 130, 14));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblTitulo.setBorderPainted(false);
        lblTitulo.setContentAreaFilled(false);
        lblTitulo.setDefaultCapable(false);
        lblTitulo.setFocusPainted(false);
        lblTitulo.setForeground(Color.white);
        lblTitulo.setFont(new Font("SansSerif", 1, 15));
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitulo.setRequestFocusEnabled(false);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        //lblSubTitle


        btnSalir.setText("[ENTER] Salir");
        btnSalir.setBounds(new Rectangle(315, 430, 110, 20));
        btnSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnSalir_ActionPerformed(e);
                }
            });
        btnSalir.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnSalir_KeyPressed(e);
                }
            });
        pnlDatosGral.setBounds(new Rectangle(15, 10, 410, 85));
        pnlDatosGral.setBackground(new Color(43, 141, 39));
        pnlDatosGral.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlDatosGral.setLayout(null);
        lblCajero.setText("Cajero :");
        lblCajero.setFont(new Font("SansSerif", 1, 12));
        lblCajero.setForeground(SystemColor.window);
        lblCajero.setBounds(new Rectangle(25, 40, 55, 20));
        txtCajero.setText("Cajero");
        txtCajero.setBounds(new Rectangle(85, 40, 310, 20));
        txtCajero.setFont(new Font("SansSerif", 1, 12));
        txtCajero.setForeground(SystemColor.window);
        lblTurno.setText("Turno :");
        lblTurno.setFont(new Font("SansSerif", 1, 12));
        lblTurno.setForeground(SystemColor.window);
        lblTurno.setBounds(new Rectangle(25, 65, 55, 20));
        txtTurno.setText("Turno");
        txtTurno.setBounds(new Rectangle(85, 65, 125, 20));
        txtTurno.setFont(new Font("SansSerif", 1, 12));
        txtTurno.setForeground(SystemColor.window);
        lblFecha.setText("Fecha :");
        lblFecha.setFont(new Font("SansSerif", 1, 12));
        lblFecha.setForeground(SystemColor.window);
        lblFecha.setBounds(new Rectangle(210, 65, 65, 20));
        txtFecha.setText("Fecha");
        txtFecha.setBounds(new Rectangle(275, 65, 130, 20));
        txtFecha.setFont(new Font("SansSerif", 1, 12));
        txtFecha.setForeground(SystemColor.window);
        pnlInformacion.setBounds(new Rectangle(15, 95, 410, 325));

        pnlDatosGral.add(txtFecha, null);
        pnlDatosGral.add(lblFecha, null);
        pnlDatosGral.add(txtTurno, null);
        pnlDatosGral.add(lblTurno, null);
        pnlDatosGral.add(txtCajero, null);
        pnlDatosGral.add(lblCajero, null);
        pnlDatosGral.add(lblTitulo, null);
        ctpContenido.add(pnlDatosGral, null);
        ctpContenido.add(btnSalir, null);
        scrPane.getViewport().add(txtInfo, null);
        pnlInformacion.add(scrPane, null);
        ctpContenido.add(pnlInformacion, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);

        txtInfo.setEditable(false);
        txtInfo.setContentType("text/html");
        txtInfo.setAutoscrolls(true);

        txtInfo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtInfo_KeyPressed(e);
                }
            });        
        scrPane.setBounds(new Rectangle(0, 0, 410, 325));
        scrPane.setBackground(new Color(255, 130, 14));
        scrPane.getViewport();


    }

    private void initialize() {
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(btnSalir);
        String valores=UtilityVendedor.obtieneDatosGral();
        String[] datosGral = valores.split("Ã");
        if(valores != null && !valores.equals("")){
            txtCajero.setText(datosGral[0]);
            txtTurno.setText(datosGral[1]);
            txtFecha.setText(datosGral[2]);
        }
        if(datosGral[3].equals("1")){
            pnlInformacion.setBounds(new Rectangle(15, 95, 410, 225));
            scrPane.setBounds(new Rectangle(0, 0, 410, 225));
            btnSalir.setBounds(new Rectangle(315, 330, 110, 20));
            this.setSize(new Dimension(447, 395));
        }else if(datosGral[3].equals("2")){
            pnlInformacion.setBounds(new Rectangle(15, 95, 410, 110));
            scrPane.setBounds(new Rectangle(0, 0, 410, 110));
            btnSalir.setBounds(new Rectangle(315, 210, 110, 20));
            this.setSize(new Dimension(447, 279));
        }
        String datos=recuperaMsjAcceso();
        if(datos!=null && !datos.equalsIgnoreCase("")){
            txtInfo.setText(datos);
        }else
            muestraMensaje("Error al recuperar la informacion del vendedor", null);
    }

    private void this_windowClosing(WindowEvent e) {
        muestraMensaje("Debe presionar la tecla ESC para cerrar la ventana.", null);
        FarmaUtility.moveFocus(btnSalir);
    }

    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            cerrarVentana();
            break;
        case KeyEvent.VK_ENTER:
            cerrarVentana();
            break;
        }
    }

    private void cerrarVentana() {
        VariablesVentas.verMetasVendedor=false;
        this.setVisible(false);
        this.dispose();
    }
    
    private void muestraMensaje(String msj, Object cTxt) {
        FarmaUtility.showMessage(this, msj, cTxt);
    }
    
    public static String recuperaMsjAcceso() {
        String txtDatos=UtilityVendedor.recuperaReporteMetas().trim();
        return txtDatos;
    }

    private void txtInfo_KeyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void btnSalir_ActionPerformed(ActionEvent e) {
        cerrarVentana();
    }

    private void btnSalir_KeyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
}
