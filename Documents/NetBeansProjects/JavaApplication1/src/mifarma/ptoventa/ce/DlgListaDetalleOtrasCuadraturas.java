package mifarma.ptoventa.ce;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.border.EtchedBorder;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.ce.reference.ConstantsCajaElectronica;
import mifarma.ptoventa.ce.reference.DBCajaElectronica;
import mifarma.ptoventa.ce.reference.VariablesCajaElectronica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2006 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DlgListaEliminacionCuadratura.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * PAULO      03.10.2006   Creación<br>
 * <br>
 * @author Paulo Cesar Ameghino Rojas<br>
 * @version 1.0<br>
 *
 */
public class DlgListaDetalleOtrasCuadraturas extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgListaDetalleOtrasCuadraturas.class);

    Frame myParentFrame;
    FarmaTableModel tableModel;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JScrollPane scrLista = new JScrollPane();
    private JTable tblListaDetalle = new JTable();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JButtonLabel btnLista = new JButtonLabel();
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();
    private JLabelWhite lblFechaDiaVenta = new JLabelWhite();
    private JLabelWhite lblCuadratura = new JLabelWhite();
    private JLabelWhite lblDescripcionCuadratura = new JLabelWhite();
    private JButton btnEnter = new JButton();

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgListaDetalleOtrasCuadraturas() {
        try {
            jbInit();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    public DlgListaDetalleOtrasCuadraturas(Frame parent, String title, boolean modal) {
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
        this.getContentPane().setLayout(borderLayout1);
        this.setSize(new Dimension(812, 303));
        this.setDefaultCloseOperation(0);
        this.setTitle("Detalle de Cuadraturas");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        lblEsc.setBounds(new Rectangle(700, 255, 95, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        scrLista.setBounds(new Rectangle(10, 105, 785, 145));
        tblListaDetalle.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblLista_keyPressed(e);
            }
        });
        pnlTitle1.setBounds(new Rectangle(10, 85, 785, 20));
        btnLista.setText("Lista");
        btnLista.setBounds(new Rectangle(5, 0, 105, 20));
        btnLista.setMnemonic('L');
        btnLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLista_actionPerformed(e);
            }
        });
        jPanelHeader1.setBounds(new Rectangle(10, 10, 785, 60));
        jLabelWhite1.setText("Dia de Venta :");
        jLabelWhite1.setBounds(new Rectangle(10, 5, 85, 15));
        lblFechaDiaVenta.setBounds(new Rectangle(100, 5, 80, 15));
        lblCuadratura.setText("Cuadratura :");
        lblCuadratura.setBounds(new Rectangle(10, 30, 75, 15));
        lblDescripcionCuadratura.setBounds(new Rectangle(100, 30, 305, 15));

        btnEnter.setBounds(new Rectangle(560, 255, 120, 20));
        btnEnter.setText("[ ENTER ] Mostrar");
        btnEnter.setBackground(SystemColor.scrollbar);
        btnEnter.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnEnter.setFont(new Font("Arial Black", 0, 10));
        btnEnter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnEnter_actionPerformed(e);
                }
            });        
        jPanelHeader1.add(lblDescripcionCuadratura, null);
        jPanelHeader1.add(lblCuadratura, null);
        jPanelHeader1.add(lblFechaDiaVenta, null);
        jPanelHeader1.add(jLabelWhite1, null);
        jContentPane.add(jPanelHeader1, null);
        pnlTitle1.add(btnLista, null);
        jContentPane.add(pnlTitle1, null);
        scrLista.getViewport().add(tblListaDetalle, null);
        jContentPane.add(scrLista, null);
        jContentPane.add(btnEnter, null);
        jContentPane.add(lblEsc, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        FarmaVariables.vAceptar = false;
        // this.setTitle(VariablesCajaElectronica.vFechaCierreDia + "   " + VariablesCajaElectronica.vCodCuadratura + " - "+ VariablesCajaElectronica.vDescCuadratura);
        lblFechaDiaVenta.setText(VariablesCajaElectronica.vFechaCierreDia);
        lblDescripcionCuadratura.setText(VariablesCajaElectronica.vCodCuadratura + " - " +
                                         VariablesCajaElectronica.vDescCuadratura);
        if (VariablesCajaElectronica.vCodCuadratura.equalsIgnoreCase(ConstantsCajaElectronica.CUADRATURA_DINERO_FALSO)) {
            initTableDineroFalso();
        } else if (VariablesCajaElectronica.vCodCuadratura.equalsIgnoreCase(ConstantsCajaElectronica.CUADRATURA_ROBO)) {
            initTableRobo();
        } else if (VariablesCajaElectronica.vCodCuadratura.equalsIgnoreCase(ConstantsCajaElectronica.CUADRATURA_DEFICIT_CAJERO)) {
            initTableDeficitCajero();
        /*** INICIO CCASTILLO 22/08/2017 ***/
        } else if (VariablesCajaElectronica.vCodCuadratura.equalsIgnoreCase(ConstantsCajaElectronica.CUADRATURA_COTIZA_COMPETENCIA_CAJERO)) {
            initTableCotizacionCompetencia();    
        /*** FIN CCASTILLO 22/08/2017 ***/
        } else
            initTableOtrasCuadraturas();
        
        if(validaRolContador()){
            btnEnter.setVisible(true);
        }else{
            btnEnter.setVisible(false);
            }
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTableDineroFalso() {
        tableModel =
                new FarmaTableModel(ConstantsCajaElectronica.columsListaDetalleDineroFalso, ConstantsCajaElectronica.defaultListaDetalleDineroFalso,
                                    0);
        FarmaUtility.initSimpleList(tblListaDetalle, tableModel,
                                    ConstantsCajaElectronica.columsListaDetalleDineroFalso);
    }

    private void initTableRobo() {
        tableModel =
                new FarmaTableModel(ConstantsCajaElectronica.columsListaDetalleRobo, ConstantsCajaElectronica.defaultListaDetalleRobo,
                                    0);
        FarmaUtility.initSimpleList(tblListaDetalle, tableModel, ConstantsCajaElectronica.columsListaDetalleRobo);
    }

    private void initTableDeficitCajero() {
        tableModel =
                new FarmaTableModel(ConstantsCajaElectronica.columsListaDetalleDeficitCajero, ConstantsCajaElectronica.defaultListaDetalleDeficitCajero,
                                    0);
        FarmaUtility.initSimpleList(tblListaDetalle, tableModel,
                                    ConstantsCajaElectronica.columsListaDetalleDeficitCajero);
    }
    
    /*** INICIO CCASTILLO 22/08/2017 ***/
    private void initTableCotizacionCompetencia() {
        tableModel =
                new FarmaTableModel(ConstantsCajaElectronica.columsListaDetalleCotizacionCompetencia, ConstantsCajaElectronica.defaultListaDetalleCotizacionCompetencia,
                                    0);
        FarmaUtility.initSimpleList(tblListaDetalle, tableModel,
                                    ConstantsCajaElectronica.columsListaDetalleCotizacionCompetencia);
    }
    /*** INICIO CCASTILLO 22/08/2017 ***/
    
    private void initTableOtrasCuadraturas() {
        tableModel =
                new FarmaTableModel(ConstantsCajaElectronica.columsListaDetalleOtrasCuadraturas, ConstantsCajaElectronica.defaultListaDetalleOtrasCuadraturas,
                                    0);
        FarmaUtility.initSimpleList(tblListaDetalle, tableModel,
                                    ConstantsCajaElectronica.columsListaDetalleOtrasCuadraturas);
    }


    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        cargaListaDetalleCuadraturas();
        FarmaUtility.moveFocusJTable(tblListaDetalle);
        FarmaUtility.centrarVentana(this);

    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void btnLista_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocusJTable(tblListaDetalle);
    }

    private void tblLista_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        if(btnEnter.isVisible()){
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            realizarVBCotnCompetencia();
        }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }
    
    /*** INICIO CCASTILLO 21/08/2017 ***/
    public void realizarVBCotnCompetencia(){
        if(validaRolContador()){
            ArrayList<String> datos = new ArrayList<String>();
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 0).toString());//sec_usu
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 1).toString());//id_usu
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 15).toString());//local
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 8).toString());//total
            
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 14).toString());//cod_grupo_cia
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 16).toString());//sec_mov_caja
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 17).toString());//sec_cuadratura_caja
            
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 10).toString());//ind_val_man
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 11).toString());//glosa_man
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 12).toString());//ind_val_web
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 13).toString());//glosa_web
            
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 4).toString());//tipoDocumento
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 5).toString());//serie
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 6).toString());//documento
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 18).toString());//numero de nota
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 19).toString());//fecha cotizacion
            datos.add(tblListaDetalle.getValueAt(tblListaDetalle.getSelectedRow(), 20).toString());//nombre sustento
            DlgValCotizacionCompetencia dlg = new DlgValCotizacionCompetencia(myParentFrame, "", true,datos);
            dlg.setVisible(true);
            if(FarmaVariables.vAceptar){
                cargaListaDetalleCuadraturas();
            }
       }
    }
    
    private boolean validaRolContador() {
        String ind = "";
        try {
            ind = DBCajaElectronica.validaRolContador();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener el Rol del Usuario.\n" +
                    sql, tblListaDetalle);
            ind = "";
        }
        if (ind.equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }

    /*** FIN CCASTILLO 21/08/2017 ***/

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void cargaListaDetalleCuadraturas() {
        try {
            DBCajaElectronica.listaDetalleCuadraturasCierreDia(tableModel, VariablesCajaElectronica.vFechaCierreDia,
                                                               VariablesCajaElectronica.vCodCuadratura);
            if (tblListaDetalle.getRowCount() > 0)
                FarmaUtility.ordenar(tblListaDetalle, tableModel, 0, FarmaConstants.ORDEN_ASCENDENTE);
            FarmaUtility.moveFocusJTable(tblListaDetalle);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al cargar los detalle de las cuadraturas en Cierre Dia\n" +
                    sql, null);
        }
    }

    private void btnEnter_actionPerformed(ActionEvent e) {
    }
}
