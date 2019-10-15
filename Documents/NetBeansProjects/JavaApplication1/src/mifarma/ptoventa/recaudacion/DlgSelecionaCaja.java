package mifarma.ptoventa.recaudacion;

import com.gs.mifarma.componentes.JLabelFunction;

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

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.DlgDetalleAnularPedido;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.pinpad.DlgAnularTransPinpad;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;

import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgSelecionaCaja  extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgDetalleAnularPedido.class);
    
    FarmaTableModel tableModelUsuariosCaja;
    Frame myParentFrame;
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JPanel jPanel1 = new JPanel();
    private JButton btnListaUsuarioCaja = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private static JTable tblUsuariosCaja = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    
    FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();
    
    public DlgSelecionaCaja() {
        this(null, "", false);
    }
    
    public DlgSelecionaCaja(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    public DlgSelecionaCaja(Frame parent, String title, boolean modal, boolean esNotaCredito) {
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
        this.setSize(new Dimension(446, 241));
        this.setResizable(false);
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Seleciona caja y/o cajero");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setBackground(Color.white);
        jContentPane.setSize(new Dimension(435, 208));
        jContentPane.setLayout(null);
        jPanel1.setBounds(new Rectangle(20, 20, 390, 30));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setBackground(new Color(255, 130, 14));
        jPanel1.setLayout(null);
        btnListaUsuarioCaja.setText("Lista de Usuarios y Cajas Disponibles");
        btnListaUsuarioCaja.setBounds(new Rectangle(10, 5, 220, 20));
        btnListaUsuarioCaja.setMnemonic('l');
        btnListaUsuarioCaja.setBackground(new Color(255, 130, 14));
        btnListaUsuarioCaja.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnListaUsuarioCaja.setBorderPainted(false);
        btnListaUsuarioCaja.setContentAreaFilled(false);
        btnListaUsuarioCaja.setDefaultCapable(false);
        btnListaUsuarioCaja.setFocusPainted(false);
        btnListaUsuarioCaja.setHorizontalAlignment(SwingConstants.LEFT);
        btnListaUsuarioCaja.setRequestFocusEnabled(false);
        btnListaUsuarioCaja.setForeground(SystemColor.window);
        btnListaUsuarioCaja.setFont(new Font("SansSerif", 1, 11));
        btnListaUsuarioCaja.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnListaUsuarioCaja_keyPressed(e);
            }
        });
        btnListaUsuarioCaja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnListaUsuarioCaja_actionPerformed(e);
            }
        });
        jScrollPane1.setBounds(new Rectangle(20, 50, 390, 125));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(320, 185, 85, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(210, 185, 100, 20));
        jPanel1.add(btnListaUsuarioCaja, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        jScrollPane1.getViewport();
        jScrollPane1.getViewport();
        jContentPane.add(lblF11, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(jPanel1, null);
        jScrollPane1.getViewport().add(tblUsuariosCaja, null);
        jContentPane.add(jScrollPane1, null);
    }

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableUsuariosCaja();
    }
    
    private void initTableUsuariosCaja() {
        tableModelUsuariosCaja =
                new FarmaTableModel(ConstantsCaja.columnsUsuariosCaja, ConstantsCaja.defaultUsuariosCaja, 0);
        FarmaUtility.initSimpleList(tblUsuariosCaja, tableModelUsuariosCaja, ConstantsCaja.columnsUsuariosCaja);
        cargaUsuariosCajaDisponibles();
    }

    private void cargaUsuariosCajaDisponibles() {
        try {
            DBCaja.getListaCajaUsuario(tableModelUsuariosCaja);
            log.debug("tableModelUsuariosCaja:" + tableModelUsuariosCaja);
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Ocurrio un error al verificar caja disponible - \n" +
                    e.getMessage(), tblUsuariosCaja);
        }
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void btnListaUsuarioCaja_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(btnListaUsuarioCaja);
    }

    private void btnListaUsuarioCaja_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(btnListaUsuarioCaja);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    
    private void chkKeyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblUsuariosCaja, null, 0);
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            int i = tblUsuariosCaja.getSelectedRow();
            String codCajero = FarmaUtility.getValueFieldArrayList(tableModelUsuariosCaja.data, i, 0);
            VariablesRecaudacion.vIdCajero_cobro = codCajero;
            cerrarVentana(true);
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                cerrarVentana(false);
            }
        }
    }
    
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
}
