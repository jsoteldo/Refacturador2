package mifarma.ptoventa.ventas;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgCantDetaVariedad extends JDialog {

    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */
    private static final Logger log = LoggerFactory.getLogger(DlgCantDetaVariedad.class);

    Frame myParentFrame;
    private FarmaTableModel tableModelListaPaquete1;
    private FarmaTableModel tableModelListaPaquete2;
    private ArrayList myArray = new ArrayList();
    private final int COL_COD = 0;
    //private final int COL_DESC=1;
    //private final int COL_DESL=2;
    //private final int COL_CODP1=3;
    //private final int COL_CODP2=4;

    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    //private JTable jTable2 = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JScrollPane jScrollPane3 = new JScrollPane();
    JPanel pnlStock2 = new JPanel();
    XYLayout xYLayout4 = new XYLayout();
    private JButtonLabel btnDescripcion = new JButtonLabel();

    private JButtonLabel btlCantidad = new JButtonLabel();
    private JTextFieldSanSerif txtCantidad = new JTextFieldSanSerif();

    private int cantPermitidaPaquete;
    private String opcion = "";

    /* ************************************************************************ */
    /*                          CONSTRUCTORES                                   */
    /* ************************************************************************ */

    public DlgCantDetaVariedad() {
        this(null, "", false);
    }

    public DlgCantDetaVariedad(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }


    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(219, 121));
        this.setTitle("Cantidad Producto");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }

        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(120, 65, 85, 20));
        lblF11.setText("[ ENTER ] Aceptar");
        lblF11.setBounds(new Rectangle(5, 65, 110, 20));
        
        jScrollPane3.setBounds(new Rectangle(10, 30, 720, 155));
        pnlStock2.setBounds(new Rectangle(10, 5, 720, 25));
        pnlStock2.setFont(new Font("SansSerif", 0, 11));
        pnlStock2.setBackground(new Color(255, 130, 14));
        pnlStock2.setLayout(xYLayout4);
        pnlStock2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock2.setForeground(Color.white);
        btnDescripcion.setText("Descripción");
        btnDescripcion.setMnemonic('1');


        btlCantidad.setText("Cantidad: ");
        btlCantidad.setBounds(new Rectangle(10, 30, 75, 15));
        btlCantidad.setForeground(new Color(255, 130, 14));
        btlCantidad.setMnemonic('c');
        btlCantidad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btlLista1_actionPerformed(e);
                }
            });
        txtCantidad.setBounds(new Rectangle(90, 25, 80, 20));
        txtCantidad.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCantidad_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtCantidad_keyTyped(e);
                }
            });
        pnlStock2.add(btnDescripcion, new XYConstraints(5, 0, 70, 20));
        jPanelWhite1.add(txtCantidad, null);
        jPanelWhite1.add(btlCantidad, null);
        jPanelWhite1.add(lblF11, null);
        jPanelWhite1.add(lblEsc, null);
        this.getContentPane().add(jPanelWhite1, BorderLayout.CENTER);
        txtCantidad.setLengthText(1);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        txtCantidad.setText("1");
        FarmaUtility.moveFocus(txtCantidad);  
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    public void txtCantidad_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validarValorCantidadIngreso()) {
                if (validarCantidadIngresada()) {
                    VariablesVentas.cantidadDetalleVariedad = txtCantidad.getText().trim();
                    cerrarVentana(true);
                }             
            } else {
                FarmaUtility.showMessage(this, "Valor no valido", txtCantidad);
            }            
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtCantidad_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCantidad, e);
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    /**
     * Se valida el valor de la cantidad ingresada
     */
    private boolean validarValorCantidadIngreso() {
        boolean valor = false;
        String cantIngreso = txtCantidad.getText().trim();
        if (FarmaUtility.isInteger(cantIngreso) && Integer.parseInt(cantIngreso) >= 0){
            valor = true;
        } 
        return valor;
    }
    
    private boolean validarCantidadIngresada() {
        boolean flag = true;
        int cant = (int)Integer.parseInt(txtCantidad.getText().trim());
        if (cant > cantPermitidaPaquete) {
            flag = false;
            if (cantPermitidaPaquete == 0) {
                FarmaUtility.showMessage(this, "La promoción no admite más selecciones", txtCantidad);
            } else {
                FarmaUtility.showMessage(this, "Ingresar un máximo de " + cantPermitidaPaquete + " unidades", txtCantidad);
            }
        } 
        return flag;
    }

    private void btlLista1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtCantidad);
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getCantPermitidaPaquete() {
        return cantPermitidaPaquete;
    }

    public void setCantPermitidaPaquete(int cantPermitidaPaquete) {
        this.cantPermitidaPaquete = cantPermitidaPaquete;
    }
}
