package mifarma.ptoventa.caja;

import com.gs.mifarma.componentes.JLabelFunction;

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

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EtchedBorder;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgNumeroPedido extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgNumeroPedido.class);
    
    private JPanel pnlPedido = new JPanel();
    private JLabel lblFecha_T = new JLabel();
    private JLabel lblPedido_T = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel lblPedido = new JLabel();
    private JLabelFunction lblEnter = new JLabelFunction();
    private XYLayout xYLayout1 = new XYLayout();
    private JLabel lblTitulo = new JLabel();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgNumeroPedido() {
        this(null, "", false);
    }

    public DlgNumeroPedido(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(375, 233));
        this.getContentPane().setLayout( null );
        this.setBackground(new Color(247, 247, 247));
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("Pedido PinPad Generado");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                this_keyPressed(e);
                }
            
            public void windowClosing(WindowEvent e){
                this_windowClosing(e);
                }
            });
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened(WindowEvent e) {
                    this_windowOpened(e);
                }
            });
        pnlPedido.setBounds(new Rectangle(0, 0, 370, 210));
        pnlPedido.setFont(new Font("SansSerif", 0, 14));
        pnlPedido.setBackground(Color.white);
        pnlPedido.setLayout(xYLayout1);
        pnlPedido.setSize(new Dimension(372, 210));
        lblFecha_T.setText("Fecha - Hora :");
        lblFecha_T.setFont(new Font("SansSerif", 1, 14));
        lblFecha_T.setForeground(new Color(255, 130, 14));
        lblPedido_T.setText("Pedido N° :");
        lblPedido_T.setFont(new Font("SansSerif", 1, 14));
        lblPedido_T.setForeground(new Color(255, 130, 14));
        lblFecha.setText("99/99/9999 99:99:99");
        lblFecha.setFont(new Font("SansSerif", 1, 14));
        lblPedido.setText("9999");
        lblPedido.setFont(new Font("SansSerif", 1, 16));
        lblPedido.setForeground(new Color(32, 105, 29));
        lblEnter.setBounds(new Rectangle(100, 155, 170, 30));
        lblEnter.setText("[ ENTER ] Aceptar");
        lblEnter.setFont(new Font("Arial Black", 0, 12));
        lblTitulo.setText("¡Pedido PinPad Generado con \u00e9xito!");
        lblTitulo.setFont(new Font("SansSerif", 1, 16));
        lblTitulo.setForeground(new Color(32, 105, 29));
        pnlPedido.add(lblTitulo, new XYConstraints(45, 30, 275, 15));
        pnlPedido.add(lblFecha_T, new XYConstraints(29, 64, 130, 20));
        pnlPedido.add(lblPedido_T, new XYConstraints(49, 99, 130, 25));
        pnlPedido.add(lblFecha, new XYConstraints(169, 64, 185, 20));
        pnlPedido.add(lblPedido, new XYConstraints(189, 99, 130, 25));
        this.getContentPane().add(lblEnter, null);
        this.getContentPane().add(pnlPedido, null);
    }
    
    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_keyPressed(KeyEvent e){
        chkKeyPressed(e);
        }
    
    private void this_windowOpened(WindowEvent e){
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(this);
        
        muestraInfoPedidoGenerado();
        
    }
    
    private void this_windowClosing(WindowEvent e){
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana", null);
        }
    
    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            cerrarVentana(true);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    
    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void muestraInfoPedidoGenerado(){
        
        try{
            lblPedido.setText(VariablesVentas.vNum_Ped_Diario);
            lblFecha.setText(String.valueOf(FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA_HORA)));
            
        } catch(SQLException sqlException){
                   sqlException.printStackTrace();
                   FarmaUtility.showMessage(this, "Error al obtener Fecha - Hora del Sistema !!! - " + sqlException.getErrorCode(), null);
               }
}
    
    
}


