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
public class DlgMercaderiaDirectaListaRecepCab extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgMercaderiaDirectaListaRecepCab.class);

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
    private JButtonLabel btnRelacionOrdenCompra = new JButtonLabel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelHeader pnlCriterioBusqueda = new JPanelHeader(); //change by Cesar Huanes
    private JButtonLabel lblNumOC = new JButtonLabel();
    private JLabelFunction lblF8 = new JLabelFunction();
    private JLabelFunction lblF1 = new JLabelFunction();
    private FacadeInventario facadeOrdenCompra = new FacadeInventario();
    private JLabelFunction lblF12 = new JLabelFunction();

    private String pCodOC = "";
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    /**
     * Constructor
     **/

    public DlgMercaderiaDirectaListaRecepCab() {
        this(null, "", false,"");
    }

    /**
     * Constructor
     * @param parent Objeto Frame de la Aplicación.
     * @param title Título de la Ventana.
     * @param modal Tipo de Ventana.
     */

    public DlgMercaderiaDirectaListaRecepCab(Frame parent, String title, boolean modal,String pCodOC) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.pCodOC = pCodOC;
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
        this.setSize(new Dimension(766, 447));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Lista de recepciones registradas de una OC ");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlTitle1.setBounds(new Rectangle(10, 60, 730, 25));
        scrListaTransferencias.setBounds(new Rectangle(10, 85, 730, 260));
        tblListaDetRECEP_OC.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblListaDetRECEP_OC_keyPressed(e);
                }
            });
        btnRelacionOrdenCompra.setText("Relaci\u00f3n de recepciones registradas:");
        btnRelacionOrdenCompra.setBounds(new Rectangle(5, 5, 265, 15));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(650, 355, 90, 40));
        pnlCriterioBusqueda.setBounds(new Rectangle(10, 10, 730, 45));
        /*Change by Cesar Huanes*/
        lblNumOC.setText("N\u00ba OC :");
        lblNumOC.setBounds(new Rectangle(20, 10, 255, 20));
        lblNumOC.setMnemonic('f');
        lblNumOC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRandoFec_actionPerformed(e);
            }
        });
        lblF8.setText("[ F8 ] Exportar a Excel");
        lblF8.setVisible(false);
        lblF8.setBounds(new Rectangle(400, 400, 170, 20));
        lblF1.setBounds(new Rectangle(15, 355, 150, 40));
        lblF1.setText("<html><center>[ F1 ] Ver Detalle recepci\u00f3n.</center></html>");
        lblF12.setText("<html><center>[ F12 ] Imprimir Recepci\u00f3n</center></html>");
        lblF12.setBounds(new Rectangle(495, 355, 135, 40)); //Change By Cesar Huanes
        pnlCriterioBusqueda.add(lblNumOC, null);
        jContentPane.add(lblF12, null);
        jContentPane.add(lblF1, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(pnlCriterioBusqueda, null);
        jContentPane.add(lblEsc, null);
        scrListaTransferencias.getViewport().add(tblListaDetRECEP_OC, null);
        jContentPane.add(scrListaTransferencias, null);
        pnlTitle1.add(btnRelacionOrdenCompra, null);
        jContentPane.add(pnlTitle1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initializeTable() {
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsListaRecepCab_OC, ConstantsInventario.defaultListaRecepCab_OC,
                                    0);
        FarmaUtility.initSimpleList(tblListaDetRECEP_OC, tableModel, ConstantsInventario.columnsListaRecepCab_OC);
        cargaListaRecep_Cab();
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void cargaListaRecep_Cab() {
        try {
            lblNumOC.setText("Nº OC : "+pCodOC);
            DBInventario.getListaRecepOC_Cab(tableModel, pCodOC);

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
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", btnRelacionOrdenCompra);
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
        
            int pos = tblListaDetRECEP_OC.getSelectedRow();
            if(pos>=0){
                String pSecOC = FarmaUtility.getValueFieldArrayList(tableModel.data,pos, 1);
                DlgMercaderiaDirectaListaRecepDet dlgRecep =  new DlgMercaderiaDirectaListaRecepDet(myParentFrame, "", true,pCodOC,pSecOC);
                dlgRecep.setVisible(true);
                if (FarmaVariables.vAceptar) {            
                    FarmaVariables.vAceptar = false;
                }    
            }
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
               FarmaUtility.showMessage(this, "Se imprimió exitosamente", tblListaDetRECEP_OC);
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
