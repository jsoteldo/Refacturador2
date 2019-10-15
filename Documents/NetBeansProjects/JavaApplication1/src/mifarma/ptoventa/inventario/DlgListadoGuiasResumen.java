package mifarma.ptoventa.inventario;


import com.gs.mifarma.componentes.DocumentFilterTextArea;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;

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

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.SwingConstants;


import javax.swing.text.PlainDocument;


import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;


import mifarma.ptoventa.inventario.reference.FacadeInventario;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgListadoGuiasResumen extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgListadoGuiasResumen.class);

    FarmaTableModel tableModelResumenPedido;
    Frame myParentFrame;
    ArrayList<String> cabecera;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();

    private JPanel pnlHeader = new JPanel();
    private JButton btnResumenPedido = new JButton();

    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelHeader pnlHeader1 = new JPanelHeader();
    private JLabel lblNumNota_T = new JLabel();
    private JLabel lblNumNota = new JLabel();
    private JLabel lblFecCrea_T = new JLabel();
    private JLabel lblFecCrea = new JLabel();
    private JLabel lblNumGuia = new JLabel();
    private JLabel lblNumGuia_T = new JLabel();
    private JLabel lblLocal = new JLabel();
    private JLabel lblLocal_T = new JLabel();
    private JLabel lblEstado = new JLabel();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JLabel lblImagen = new JLabel();
    private JTextArea txaResumen = new JTextArea();
    transient FacadeInventario facadeInventario = new FacadeInventario();
    private JPanel pnlBody = new JPanel();
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgListadoGuiasResumen() {
        this(null, "", false, new ArrayList());
    }

    public DlgListadoGuiasResumen(Frame parent, String title, boolean modal,ArrayList<String> datos) {
        super(parent, title, modal);
        myParentFrame = parent;
        cabecera=datos;
        try {
            jbInit();
            initialize();
            limpiarDatosCabecera();
        } catch (Exception e) {
            log.error("", e);
        }

    }


    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(704, 399));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Guia No Mueve Stock - Detalle");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setLayout(null);
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(678, 395));
        pnlHeader.setBounds(new Rectangle(10, 70, 660, 25));
        pnlHeader.setBackground(new Color(255, 130, 14));
        pnlHeader.setLayout(null);
        btnResumenPedido.setText("Detalle de Guia");
        btnResumenPedido.setBounds(new Rectangle(10, 0, 140, 25));
        btnResumenPedido.setBackground(new Color(255, 130, 14));
        btnResumenPedido.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnResumenPedido.setBorderPainted(false);
        btnResumenPedido.setContentAreaFilled(false);
        btnResumenPedido.setDefaultCapable(false);
        btnResumenPedido.setFocusPainted(false);
        btnResumenPedido.setFont(new Font("SansSerif", 1, 12));
        btnResumenPedido.setForeground(Color.white);
        btnResumenPedido.setHorizontalAlignment(SwingConstants.LEFT);
        btnResumenPedido.setMnemonic('r');
        btnResumenPedido.setRequestFocusEnabled(false);
        btnResumenPedido.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnResumenPedido_keyPressed(e);
            }
        });
        btnResumenPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnResumenPedido_actionPerformed(e);
            }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(585, 340, 85, 20));
        pnlHeader1.setBounds(new Rectangle(10, 10, 660, 60));
        lblNumNota_T.setText("N° Nota :");
        lblNumNota_T.setBounds(new Rectangle(10, 30, 120, 20));
        lblNumNota_T.setFont(new Font("SansSerif", 1, 11));
        lblNumNota_T.setForeground(Color.white);
        lblNumNota.setText("-");
        lblNumNota.setBounds(new Rectangle(135, 30, 210, 20));
        lblNumNota.setFont(new Font("SansSerif", 1, 11));
        lblNumNota.setForeground(Color.white);
        lblFecCrea_T.setText("Fecha Creacion Guia:");
        lblFecCrea_T.setBounds(new Rectangle(295, 5, 130, 20));
        lblFecCrea_T.setFont(new Font("SansSerif", 1, 11));
        lblFecCrea_T.setForeground(Color.white);
        lblFecCrea.setText("-");
        lblFecCrea.setBounds(new Rectangle(430, 5, 220, 20));
        lblFecCrea.setFont(new Font("SansSerif", 1, 11));
        lblFecCrea.setForeground(Color.white);
        lblNumGuia.setText("-");
        lblNumGuia.setBounds(new Rectangle(135, 5, 170, 20));
        lblNumGuia.setFont(new Font("SansSerif", 1, 11));
        lblNumGuia.setForeground(Color.white);
        lblNumGuia_T.setText("N° Guia:");
        lblNumGuia_T.setBounds(new Rectangle(10, 5, 110, 20));
        lblNumGuia_T.setFont(new Font("SansSerif", 1, 11));
        lblNumGuia_T.setForeground(Color.white);
        lblLocal.setText("-");
        lblLocal.setBounds(new Rectangle(430, 30, 220, 20));
        lblLocal.setFont(new Font("SansSerif", 1, 11));
        lblLocal.setForeground(Color.white);
        lblLocal_T.setText("Local:");
        lblLocal_T.setBounds(new Rectangle(295, 30, 50, 20));
        lblLocal_T.setFont(new Font("SansSerif", 1, 11));
        lblLocal_T.setForeground(Color.white);
        lblEstado.setBounds(new Rectangle(535, 40, 95, 20));
        lblEstado.setFont(new Font("SansSerif", 1, 11));
        lblEstado.setForeground(Color.white);
        jPanelTitle1.setBounds(new Rectangle(10, 310, 660, 20));
        lblImagen.setBounds(new Rectangle(535, 0, 120, 20));
        lblImagen.setFont(new Font("SansSerif", 1, 11));
        lblImagen.setForeground(SystemColor.window);
        txaResumen.setBounds(new Rectangle(0, 0, 655, 215));
        txaResumen.setFont(new Font("SansSerif", 1, 11));
        txaResumen.setEditable(false);
        txaResumen.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txaResumen_keyPressed(e);
            }
        });
        pnlBody.setLayout(null);



        pnlBody.setBounds(new Rectangle(15, 95, 655, 215));
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        pnlHeader1.add(lblEstado, null);
        pnlHeader1.add(lblLocal_T, null);
        pnlHeader1.add(lblLocal, null);
        pnlHeader1.add(lblNumGuia_T, null);
        pnlHeader1.add(lblNumGuia, null);
        pnlHeader1.add(lblFecCrea, null);

        pnlHeader1.add(lblNumNota_T, null);
        pnlHeader1.add(lblFecCrea_T, null);
        pnlHeader1.add(lblNumNota, null);
        pnlBody.add(txaResumen);
        jContentPane.add(pnlBody, null);
        jContentPane.add(jPanelTitle1, null);
        jContentPane.add(pnlHeader1, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(pnlHeader, null);
        pnlHeader.add(lblImagen, null);
        pnlHeader.add(btnResumenPedido, null);

    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        mostrarDatos();
    }


    private void btnResumenPedido_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(btnResumenPedido);
    }

    private void btnResumenPedido_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    
    private void txaResumen_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(btnResumenPedido);
    }


    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
            cerrarVentana(false);
    }

    private void cerrarVentana(boolean pAceptar) {
        this.setVisible(false);
        this.dispose();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", btnResumenPedido);
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */


    public void mostrarDatos() {
        lblNumNota.setText(cabecera.get(0).toString());
        lblFecCrea.setText(cabecera.get(2).toString());
        lblNumGuia.setText(cabecera.get(1).toString());
        lblLocal.setText(FarmaVariables.vCodLocal);
        recuperaDetalleGuia();
    }
    
    public void recuperaDetalleGuia(){
      ArrayList listaGuias = new ArrayList();
      ArrayList listaFila = new ArrayList();
      String texto;
            try {
                listaGuias =facadeInventario.detGuiaNoMueveStock(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,lblNumNota.getText());
                if (listaGuias != null && listaGuias.size()>0) {
                    listaFila = (ArrayList)listaGuias.get(0);
                    texto = listaFila.get(0).toString();
                    txaResumen.setText(texto); 
                }

            } catch (Exception sql) {
                log.error("", sql);
                FarmaUtility.showMessage(this, "Ocurrió un error al recuperar el mensaje de la Guias: \n" +
                        sql.getMessage(), btnResumenPedido);
            }
    }



    public void limpiarDatosCabecera() {

    }


}
