package mifarma.ptoventa.inventario;


/* GUI JAVA*/


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
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

import java.lang.Exception;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.electronico.UtilityImpCompElectronico;

import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.FacadeInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/* SQL y Utilitarios JAVA */


/**
 * Copyright (c) 2013 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicación : DlgMercaderiaDirectaBuscar.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * LRUIZ      16.05.2013   Creación<br>
 * KMONCADA   23.07.2013   Modificación<br>
 * <br>
 * @author Luis Ruiz Peralta<br>
 * @version 1.0<br>
 *
 */
public class DlgMercaderiaDirectaListaRecepDet extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgMercaderiaDirectaListaRecepDet.class);

    private Frame myParentFrame;
    private FarmaTableModel tableModel;
    private String fechaIni;
    private String fechaFin;
    private String ind;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JScrollPane scrListaTransferencias = new JScrollPane();
    private JTable tblListaDetRECEP_OC = new JTable();
    private JLabelFunction lblEsc = new JLabelFunction(); //change by Cesar Huanes
    private JLabelFunction lblF8 = new JLabelFunction();
    private FacadeInventario facadeOrdenCompra = new FacadeInventario();

    private String pCodOC = "";
    private String pSecRecepCab = "";
        /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    /**
     * Constructor
     **/

    public DlgMercaderiaDirectaListaRecepDet() {
        this(null, "", false,"","");
    }

    /**
     * Constructor
     * @param parent Objeto Frame de la Aplicación.
     * @param title Título de la Ventana.
     * @param modal Tipo de Ventana.
     */

    public DlgMercaderiaDirectaListaRecepDet(Frame parent, String title, boolean modal,String pCodOC,String pSecRecepCab) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.pCodOC = pCodOC;
        this.pSecRecepCab = pSecRecepCab;
        try {
            jbInit();
            initializeTable();
            FarmaUtility.centrarVentana(this);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    /**
     * Implementa la Ventana con todos sus Objetos
     */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(759, 389));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Detalle de recepcion : ");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlTitle1.setBounds(new Rectangle(10, 5, 730, 25));
        scrListaTransferencias.setBounds(new Rectangle(10, 30, 730, 260));
        tblListaDetRECEP_OC.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblListaDetRECEP_OC_keyPressed(e);
                }
            });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(650, 300, 90, 40));
        /*Change by Cesar Huanes*/
        lblF8.setText("[ F8 ] Exportar a Excel");
        lblF8.setVisible(false);
        lblF8.setBounds(new Rectangle(400, 400, 170, 20)); //Change By Cesar Huanes
        jContentPane.add(lblF8, null);
        jContentPane.add(lblEsc, null);
        scrListaTransferencias.getViewport().add(tblListaDetRECEP_OC, null);
        jContentPane.add(scrListaTransferencias, null);
        jContentPane.add(pnlTitle1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initializeTable() {
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsListaRecepDet_OC, ConstantsInventario.defaultListaRecepDet_OC,
                                    0);
        FarmaUtility.initSimpleList(tblListaDetRECEP_OC, tableModel, ConstantsInventario.columnsListaRecepDet_OC);
        cargaListaRecep_Det();
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void cargaListaRecep_Det() {
        try {
           // lblNumOC.setText("Nº OC : "+pCodOC);
            DBInventario.getListaRecepOC_Det(tableModel, pCodOC,pSecRecepCab);

            tblListaDetRECEP_OC.getTableHeader().setReorderingAllowed(false);
            tblListaDetRECEP_OC.getTableHeader().setResizingAllowed(false);

            if (tblListaDetRECEP_OC.getRowCount() == 0)
                FarmaUtility.showMessage(this, "No existe información de la Orden de Compra que se haya recepcionado.",
                                         null);
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        }
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.moveFocus(tblListaDetRECEP_OC);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", tblListaDetRECEP_OC);
    }

    private void btnRandoFec_actionPerformed(ActionEvent e) {
        
    }

    private void btnBuscarDesc_actionPerformed(ActionEvent e) {
        
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F1(e)) {
            verDetalleOC_Det();
        } else if (UtilityPtoVenta.verificaVK_F12(e)) {
            imprimirRecepcion();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }


    private void verDetalleOC_Det() {
        
    }

    public void imprimirRecepcion() {
        try {
            int pos = tblListaDetRECEP_OC.getSelectedRow();
            if (pos >= 0) {
                String pOC = FarmaUtility.getValueFieldArrayList(tableModel.data, pos, 0);
                String pSerie = FarmaUtility.getValueFieldArrayList(tableModel.data, pos, 5);
                String pNumDoc = FarmaUtility.getValueFieldArrayList(tableModel.data, pos, 6);
                String pSecCabOC = FarmaUtility.getValueFieldArrayList(tableModel.data, pos, 1);

                List vListaDatos2 = DBInventario.getDatoConstanciaRecepcionMD(pOC, pSerie, pNumDoc, pSecCabOC);
                for (int j = 1; j < 3; j++) {
                    (new UtilityImpCompElectronico()).impresionTermica(vListaDatos2, null);
                }

            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
       
    }

    private void tblListaDetRECEP_OC_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
}
