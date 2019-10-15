package mifarma.ptoventa.ce;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import com.gs.mifarma.componentes.FarmaSegundos;
import mifarma.ptoventa.administracion.cajas.reference.ConstantsCajas;
import mifarma.ptoventa.ce.reference.DBCajaElectronica;
import mifarma.ptoventa.ce.reference.FacadeCajaElectronica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2014 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicaci�n : DlgListaCajasAperturadas.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * ERIOS      06.02.2014   Creaci�n<br>
 * <br>
 * @author Edgar Rios Navarro<br>
 * @since 2.2.8<br>
 *
 */
public class DlgValidaAperturaCaja extends JDialog {

    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */

    private static final Logger log = LoggerFactory.getLogger(DlgValidaAperturaCaja.class);

    Frame myParentFrame;
    FarmaTableModel tableModel;
    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelTitle pnlHeaderListaCajas = new JPanelTitle();
    private JScrollPane scrListaCajas = new JScrollPane();
    private JTable tblListaCajas = new JTable();
    private JLabelFunction lblSalir = new JLabelFunction();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JButtonLabel jButtonLabel1 = new JButtonLabel();
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private JLabelWhite lblMensaje = new JLabelWhite();

    private FacadeCajaElectronica facadeCajaElectronica = new FacadeCajaElectronica();
    private String vFechaCierreDia = "";
    private String vMensaje;
    
    private ArrayList vLista = new ArrayList();
    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */

    public DlgValidaAperturaCaja() {
        this(null, "", false,null);
    }

    public DlgValidaAperturaCaja(Frame parent, String title, boolean modal,ArrayList vListaInput) {
        super(parent, title, modal);
        myParentFrame = parent;
        vLista = vListaInput;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    /* ************************************************************************ */
    /*                              METODO jbInit                               */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(467, 305));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Cajas Aperturadas");
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
        pnlHeaderListaCajas.setBounds(new Rectangle(10, 110, 445, 25));
        scrListaCajas.setBounds(new Rectangle(10, 135, 445, 110));
        scrListaCajas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                scrListaCajas_keyPressed(e);
            }
        });
        tblListaCajas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    tblListaCajas_keyPressed(e);
                }
        });
        lblSalir.setBounds(new Rectangle(325, 255, 95, 20));
        lblSalir.setText("[ESC] Cerrar");
        lblEnter.setBounds(new Rectangle(10, 255, 120, 20));
        lblEnter.setText("[Enter] Seleccionar");
        
        jButtonLabel1.setText("Lista de Cajas");
        jButtonLabel1.setBounds(new Rectangle(5, 5, 110, 15));
        jPanelHeader1.setBounds(new Rectangle(10, 10, 445, 100));
        lblMensaje.setText("[Mensaje]");
        lblMensaje.setBounds(new Rectangle(10, 10, 400, 80));
        jPanelHeader1.add(lblMensaje, null);
        jContentPane.add(jPanelHeader1, null);
        jContentPane.add(lblSalir, null);
        jContentPane.add(lblEnter, null);
        scrListaCajas.getViewport().add(tblListaCajas, null);
        jContentPane.add(scrListaCajas, null);
        pnlHeaderListaCajas.add(jButtonLabel1, null);
        jContentPane.add(pnlHeaderListaCajas, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                           METODO initialize                              */
    /* ************************************************************************ */

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTable();
    }

    /* ************************************************************************ */
    /*                         METODOS INICIALIZACION                           */
    /* ************************************************************************ */

    private void initTable() {
        tableModel =
                new FarmaTableModel(ConstantsCajas.columnsListaValidaAperturaCaja, ConstantsCajas.defaultValuesValidaAperturaCaja,
                                    0);
        FarmaUtility.initSimpleList(tblListaCajas, tableModel, ConstantsCajas.columnsListaValidaAperturaCaja);
    }

    /* ************************************************************************ */
    /*                           METODOS DE EVENTOS                             */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        cargaListaCajas(vLista);
        muestraMensaje();
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(tblListaCajas);
    }

    private void this_windowClosing(WindowEvent e) 
    {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void scrListaCajas_keyPressed(KeyEvent e)
    {
        chkKeyPressed(e);
    }

    private void tblListaCajas_keyPressed(KeyEvent e){
        chkKeyPressed(e);
    }

    /* ************************************************************************ */
    /*                      METODOS AUXILIARES DE EVENTOS                       */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
        else{
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                
                if(tblListaCajas.getSelectedRow()>=0){
            String FechaDia= FarmaUtility.getValueFieldArrayList(tableModel.data, tblListaCajas.getSelectedRow(), 0).trim();
            String pSecUsuCaja = FarmaVariables.vNuSecUsu;
            String pTurno = FarmaUtility.getValueFieldArrayList(tableModel.data, tblListaCajas.getSelectedRow(), 1).trim();
                
              DlgCierreCajaTurno dl = new  DlgCierreCajaTurno(myParentFrame,"",true,FechaDia,pSecUsuCaja,pTurno);
                                                    dl.setVisible(true);
                                                                           
                       cargaListaCajas(getListaCajaSinVBCajero(FarmaVariables.vNuSecUsu));                                  
                       tblListaCajas.repaint();
                   }
            }
            
        }

    }
    
    private ArrayList getListaCajaSinVBCajero(String vSecUsuLogin) {
        ArrayList vLista = new ArrayList();
        try {
            return DBCajaElectronica.getCajaSinVBCajero(vSecUsuLogin);
        } catch (Exception sqle) {
            // TODO: Add catch code
            log.error("",sqle);
            log.error("",sqle);
            return vLista;
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                      METODOS DE LOGICA DE NEGOCIO                        */
    /* ************************************************************************ */

    private void cargaListaCajas(ArrayList listaCajas) {
        tableModel.clearTable();
        tableModel.data = listaCajas;
    }

    void setFechaCierreDia(String pFechaCierreDia) {
        this.vFechaCierreDia = pFechaCierreDia;
    }

    void setMensaje(String pMensaje) {
        this.vMensaje = pMensaje;
    }

    private void muestraMensaje() {
        lblMensaje.setText(//"<html>" + "No puede aperturar la caja, por tener turnos pendientes de dar visto bueno." + 
                           "<html><table border=\"0\" style=\"width:100%\"><tr><td></td><td></td><td></td></tr><tr><td></td><td><b><left>" +
                           "<FONT FACE=\"arial\" SIZE=5 COLOR=\"white\">" +
                            "No puede aperturar su caja, por tener turnos pendientes de dar visto bueno.</font></left></b>" +
                           "</td><td></td></tr><tr><td></td>    <td>" +
                           "<left><FONT FACE=\"arial\" SIZE=3 COLOR=\"white\"></font></left></td><td></td></tr>" +
                           "<tr><td></td><td></td><td></td></tr></table></html>");
    }
}
