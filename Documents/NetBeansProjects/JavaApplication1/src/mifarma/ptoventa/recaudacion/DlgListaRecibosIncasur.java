package mifarma.ptoventa.recaudacion;


import com.gs.mifarma.componentes.JLabelFunction;
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.UtilityRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (c) 2016 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicación : DlgListaRecibosIncasur.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * ASOSA      18.05.2016   Creación     INCASUR<br>
 * <br>
 * @author Alfredo Sosa Dordán<br>
 * @version 1.0<br>
 *
 */
public class DlgListaRecibosIncasur extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(DlgListaRecibosIncasur.class);

    Frame myParentFrame;
    FarmaTableModel tableModel;
    ArrayList arrayProductos = new ArrayList();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JScrollPane scrRecibos = new JScrollPane();
    private JPanel pnlHeader = new JPanel();
    private JButton btnListaRecibos = new JButton();
    private JTable tblRecibos = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction();

    UtilityRecaudacion utilityRecaudacion = new UtilityRecaudacion();
    transient FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();
    UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();

    private String pCodCliente;
    private String pCodServ;

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgListaRecibosIncasur() {
        this(null, "", false);
    }

    public DlgListaRecibosIncasur(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(332, 247));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Recibos pendientes");
        this.setDefaultCloseOperation(0);        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }

            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jContentPane.setLayout(null);
        jContentPane.setBackground(Color.white);
        jContentPane.setSize(new Dimension(300, 300));
        jContentPane.setForeground(Color.white);
        lblF5.setText("[ F11 ] Seleccionar");
        lblF5.setBounds(new Rectangle(85, 180, 130, 20));
        scrRecibos.setBounds(new Rectangle(15, 40, 295, 125));
        scrRecibos.setBackground(new Color(255, 130, 14));
        pnlHeader.setBounds(new Rectangle(15, 15, 295, 25));
        pnlHeader.setBackground(new Color(255, 130, 14));
        pnlHeader.setLayout(null);
        pnlHeader.setForeground(new Color(255, 130, 14));
        btnListaRecibos.setText("Recibos pendiente");
        btnListaRecibos.setBounds(new Rectangle(10, 0, 145, 25));
        btnListaRecibos.setBackground(new Color(255, 130, 14));
        btnListaRecibos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnListaRecibos.setBorderPainted(false);
        btnListaRecibos.setContentAreaFilled(false);
        btnListaRecibos.setDefaultCapable(false);
        btnListaRecibos.setFocusPainted(false);
        btnListaRecibos.setFont(new Font("SansSerif", 1, 11));
        btnListaRecibos.setMnemonic('l');
        btnListaRecibos.setForeground(Color.white);
        btnListaRecibos.setHorizontalAlignment(SwingConstants.LEFT);
        btnListaRecibos.setRequestFocusEnabled(false);
        btnListaRecibos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnListaRecibos_actionPerformed(e);
            }
        });
        /*btnRecaudacion.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        btnCorrelativo_actionPerformed(e);
                    }
                });*/
        tblRecibos.setFocusable(true);
        tblRecibos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblCabeceraPedido_keyPressed(e);
            }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(225, 180, 85, 20));
        scrRecibos.getViewport();
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblF5, null);
        scrRecibos.getViewport().add(tblRecibos, null);
        jContentPane.add(scrRecibos, null);
        pnlHeader.add(btnListaRecibos, null);
        jContentPane.add(pnlHeader, null);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        utilityRecaudacion.initMensajesVentana(this, null, null, "00");
        initTable();
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTable() {
        tableModel =
                new FarmaTableModel(ConstantsRecaudacion.columnsListaRecibosPendientes, ConstantsRecaudacion.defaultListaRecibosPendientes,
                                    0);
        FarmaUtility.initSimpleList(tblRecibos, tableModel,
                                    ConstantsRecaudacion.columnsListaRecibosPendientes);
       
    }

    


    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void this_windowOpened(WindowEvent e) {
        listarRecibosPendientesFasaProd();
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(tblRecibos);
        validarCantidadRecibos();
    }


    private void tblCabeceraPedido_keyPressed(KeyEvent e) {
        chkkeyPressed(e);
    }

    private void btnListaRecibos_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblRecibos);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */


    private void chkkeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            if (validarSeleccionRecibo()) {
                int fila = tblRecibos.getSelectedRow();
                VariablesRecaudacion.vNroRecibo = FarmaUtility.getValueFieldArrayList(tableModel.data, 
                                                                                      fila, 
                                                                                      0);
                cerrarVentana(true);
            }            
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }
    
    private void validarCantidadRecibos() {
        if (tableModel.getRowCount() == 0) {
            FarmaUtility.showMessage(this, 
                                     "No existen recibos para el cliente", 
                                     tblRecibos);
            cerrarVentana(false);
        }
    }
    
    private boolean validarSeleccionRecibo() {
        boolean flag = true;
        if (tableModel.getRowCount() == 0) {
            FarmaUtility.showMessage(this, 
                                     "No existen recibos para el cliente", 
                                     tblRecibos);
            flag = false;
        } else if (tblRecibos.getSelectedRow() == -1) {
            FarmaUtility.showMessage(this, 
                                     "Seleccione un recibo", 
                                     tblRecibos);
            flag = false;
        }
        return flag;
    }

    private void cerrarVentana(boolean flag) {
        FarmaVariables.vAceptar = flag;
        this.setVisible(false);        
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void listarRecibosPendientesFasaProd() {
        ArrayList<ArrayList<String>> tmpLista = new ArrayList<>();
        tmpLista =
                facadeRecaudacion.listarRecibosPendientesFasaProd(getPCodCliente(), 
                                                                  getPCodServ());
        llenarTabla(tableModel, tmpLista);
    }
    
    public void llenarTabla(FarmaTableModel tableModel, 
                            ArrayList<ArrayList<String>> tmpArrayLista) {
        if (tmpArrayLista.size() > 0) {
            for (int i = 0; i < tmpArrayLista.size(); i++) {
                tableModel.insertRow(tmpArrayLista.get(i));
            }
        }
    }

    public String getPCodCliente() {
        return pCodCliente;
    }

    public void setPCodCliente(String pCodCliente) {
        this.pCodCliente = pCodCliente;
    }

    public String getPCodServ() {
        return pCodServ;
    }

    public void setPCodServ(String pCodServ) {
        this.pCodServ = pCodServ;
    }
}
