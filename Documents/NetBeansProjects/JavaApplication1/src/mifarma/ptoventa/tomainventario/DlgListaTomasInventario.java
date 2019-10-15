package mifarma.ptoventa.tomainventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.tomainventario.reference.ConstantsTomaInv;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgListaTomasInventario extends JDialog {

    Frame parentFrame;

    private static final Logger log = LoggerFactory.getLogger(DlgListaTomasInventario.class);

    FarmaTableModel tableModelTomasInventario;

    Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanel jContentPane = new JPanel();

    private JPanelTitle jPanelTitle1 = new JPanelTitle();

    private JScrollPane jScrollPane1 = new JScrollPane();

    private JTable tblRelacionTomas = new JTable();

    private JButtonLabel btnRelacionTomas = new JButtonLabel();

    private JLabelFunction lblF1 = new JLabelFunction();

    private JLabelFunction lblF5 = new JLabelFunction();

    private JLabelFunction lblF12 = new JLabelFunction();

    private JLabelFunction lblEscape = new JLabelFunction();
    private JLabelFunction lblF3 = new JLabelFunction();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgListaTomasInventario() {
        this(null, "", false);
    }

    public DlgListaTomasInventario(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    // **************************************************************************
    // Método "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(749, 405));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Lista de Tomas de Inventario");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jPanelTitle1.setBounds(new Rectangle(15, 10, 700, 30));
        jScrollPane1.setBounds(new Rectangle(15, 40, 700, 245));
        tblRelacionTomas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblRelacionTomas_keyPressed(e);
            }
        });
        btnRelacionTomas.setText("Relacion de Tomas de Inventario");
        btnRelacionTomas.setBounds(new Rectangle(10, 5, 195, 20));
        btnRelacionTomas.setMnemonic('r');
        btnRelacionTomas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionTomas_actionPerformed(e);
            }
        });
        lblF1.setBounds(new Rectangle(15, 295, 185, 20));
        lblF1.setText("[ ENTER ] Seleccionar Inventario");
        lblF1.setForeground(new Color(32, 105, 29));
        lblF1.setFont(new Font("SansSerif", 1, 11));
        lblF1.setOpaque(false);
        lblF1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF1.setBackground(SystemColor.window);
        lblF5.setBounds(new Rectangle(220, 295, 150, 20));
        lblF5.setText("[ F5 ] Cargar Inventario");
        lblF5.setForeground(new Color(32, 105, 29));
        lblF5.setFont(new Font("SansSerif", 1, 11));
        lblF5.setOpaque(false);
        lblF5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF5.setBackground(SystemColor.window);
        lblF12.setBounds(new Rectangle(395, 295, 155, 20));
        lblF12.setText("[ F12 ] Anular Inventario");
        lblF12.setForeground(new Color(32, 105, 29));
        lblF12.setFont(new Font("SansSerif", 1, 11));
        lblF12.setOpaque(false);
        lblF12.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF12.setBackground(SystemColor.window);
        lblEscape.setBounds(new Rectangle(590, 295, 115, 20));
        lblEscape.setText("[ ESCAPE ] Cerrar");
        lblEscape.setForeground(new Color(32, 105, 29));
        lblEscape.setFont(new Font("SansSerif", 1, 11));
        lblEscape.setOpaque(false);
        lblEscape.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEscape.setBackground(SystemColor.window);
        lblF3.setBounds(new Rectangle(15, 320, 130, 20));
        lblF3.setText("[ F3 ] Ver Diferencias");
        lblF3.setVisible(false);
        lblF3.setForeground(new Color(43, 141, 39));
        lblF3.setFont(new Font("SansSerif", 1, 11));
        jContentPane.add(lblF3, null);
        jContentPane.add(lblEscape, null);
        jContentPane.add(lblF12, null);
        jContentPane.add(lblF5, null);
        jContentPane.add(lblF1, null);
        jScrollPane1.getViewport().add(tblRelacionTomas, null);
        jContentPane.add(jScrollPane1, null);
        jPanelTitle1.add(btnRelacionTomas, null);
        jContentPane.add(jPanelTitle1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableListaTomasInventario();
    }

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTableListaTomasInventario() {
        tableModelTomasInventario =
                new FarmaTableModel(ConstantsTomaInv.columnsTomasInventario, ConstantsTomaInv.defaultValuesTomasInventario,
                                    0);
        FarmaUtility.initSimpleList(tblRelacionTomas, tableModelTomasInventario,
                                    ConstantsTomaInv.columnsTomasInventario);
        cargaTomasInventario();
        if(tblRelacionTomas.getRowCount() == 0) {
            lblF5.setEnabled(false);
            lblF5.setVisible(false);
            lblF12.setEnabled(false);
            lblF12.setVisible(false);
        } else {
            if (!(FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                     FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_OPERADOR_SISTEMAS))) {
                
                    if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)){
                        lblF5.setEnabled(true);
                        lblF5.setVisible(true);
                        lblF12.setEnabled(false);
                        lblF12.setVisible(false);
                    } else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_INVENTARIADOR)) {
                        lblF5.setEnabled(false);
                        lblF5.setVisible(false);
                        lblF12.setEnabled(false);
                        lblF12.setVisible(false);
                    } else {
                        lblF5.setEnabled(false);
                        lblF5.setVisible(false);
                        lblF12.setEnabled(false);
                        lblF12.setVisible(false);
                    }
            } else {
                lblF5.setEnabled(true);
                lblF5.setVisible(true);
                lblF12.setEnabled(true);
                lblF12.setVisible(true);
            }
        }
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(tblRelacionTomas);
    }

    private void tblRelacionTomas_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void btnRelacionTomas_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblRelacionTomas);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tieneRegistroSeleccionado(tblRelacionTomas)) {
                cargarRegistroSeleccionado();
                VariablesTomaInv.vTipOp = ConstantsTomaInv.TIPO_OPERACION_TOMA_INV;
                DlgLaboratoriosPorToma dlgLaboratoriosPorToma = new DlgLaboratoriosPorToma(myParentFrame, "", true);
                dlgLaboratoriosPorToma.setVisible(true);
            }
        } else if (UtilityPtoVenta.verificaVK_F2(e)) {
            /*if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                if (tieneRegistroSeleccionado(tblRelacionTomas)) {
                    cargarRegistroSeleccionado();
                    DlgConsolidadoToma dlgConsolidadoToma = new DlgConsolidadoToma(myParentFrame, " ", true);
                    dlgConsolidadoToma.setVisible(true);
                }
            } else
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción",
                                         tblRelacionTomas);*/


        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                if (tieneRegistroSeleccionado(tblRelacionTomas)) {
                    cargarRegistroSeleccionado();
                    DlgListaDiferenciasConsolidado dlgListaDiferenciasConsolidado =
                        new DlgListaDiferenciasConsolidado(myParentFrame, " ", true);
                    dlgListaDiferenciasConsolidado.setVisible(true);
                }
            } else
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción",
                                         tblRelacionTomas);

        } else if (e.getKeyCode() == KeyEvent.VK_F5 && lblF5.isEnabled()) {
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, tblRelacionTomas);
            else {
                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) ||
                    FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL) ||
                    FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_OPERADOR_SISTEMAS)) {
                    log.debug("esta en el if");
                    if (tieneRegistroSeleccionado(this.tblRelacionTomas)) {
                        cargarRegistroSeleccionado();
                        DlgAvisoCargaTomaInv dlgAviso =
                            new DlgAvisoCargaTomaInv(myParentFrame, "Mensaje del sistema", true);
                        dlgAviso.setAviso("<html><body>¿ Desea cargar <br> la Toma de Inventario ? </body></html>");
                        dlgAviso.setVisible(true);

                        if (esTomaValida())
                          
                            if (DlgAvisoCargaTomaInv.aceptarTransaccion) {
                                if (cargaLogin("C")) {
                                    try {
                                        if (obtieneIndicadorProceso_ForUpdate(VariablesTomaInv.vSecToma,
                                                                              VariablesTomaInv.vIndProceso)) {
                                            DBTomaInv.limpiarTemporalTomaInv(FarmaVariables.vCodLocal,
                                                                             VariablesTomaInv.vSecToma);
                                            cargarToma();

                                            FarmaUtility.aceptarTransaccion();
                                            FarmaUtility.showMessage(this, "La toma se cargó correctamente",
                                                                     tblRelacionTomas);
                                            try {
                                                verificaDescongelamiento();
                                                FarmaUtility.aceptarTransaccion();
                                            } catch (Exception ef) {
                                                log.info("Error de verificacion de descongelamiento");
                                            }
                                            
                                            ///SLEYVA 29/01/2019-INICIO
                                            DBTomaInv.desactivaRolInventariador();
                                            DBTomaInv.cantTomaInventarios();
                                            ///SLEYVA 29/01/2019-FIN
                                            cargaTomasInventario();

                                            DlgResumenCierre dlgConsolidadoToma =
                                                new DlgResumenCierre(parentFrame, "Resumen Inventario", true, "V");
                                            dlgConsolidadoToma.setFecFinToma(FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA_HORA));
                                            dlgConsolidadoToma.setVisible(true);
                                        } else {
                                            DBTomaInv.desactivaRolInventariador();
                                            FarmaUtility.liberarTransaccion();
                                            FarmaUtility.showMessage(this,
                                                                     "No es posible realizar la operacion. La Toma ya se encuentra cargada. Verifique!!!",
                                                                     tblRelacionTomas);
                                            cargaTomasInventario();
                                            return;
                                        }
                                    } catch (SQLException sql) {
                                        FarmaUtility.liberarTransaccion();
                                        try {
                                            DBTomaInv.desactivaRolInventariador();
                                            FarmaUtility.aceptarTransaccion();
                                        } catch (SQLException f) {
                                            FarmaUtility.showMessage(this,
                                                                     "No se pudo devolver los roles:\n" + f.getMessage(),
                                                                     tblRelacionTomas);
                                        }
                                        log.error("", sql);
                                        FarmaUtility.showMessage(this, "Ocurrió un error al cargar la toma :\n" +
                                                sql.getMessage(), tblRelacionTomas);
                                    }
                                }
                            }
                    }
                } else {

                    FarmaUtility.showMessage(this,
                                             "Solo los usuarios con el Rol Auditor o Jefe Local \n pueden Eliminar una Toma de Inventario",
                                             tblRelacionTomas);
                }
                
                
                
                
                

                
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F12 && lblF12.isEnabled()) {
            
            if(tblRelacionTomas.getRowCount()<=0){
                FarmaUtility.showMessage(this,
                                         "No hay Toma de Inventario creada!",
                                         tblRelacionTomas);                
                return;
            }
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, tblRelacionTomas);
            else {
                
                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) || FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_OPERADOR_SISTEMAS)) {
                    log.debug("esta en el if");
                    if (tieneRegistroSeleccionado(this.tblRelacionTomas)) {
                        DlgAvisoCargaTomaInv dlgAviso =
                            new DlgAvisoCargaTomaInv(myParentFrame, "Mensaje del sistema", true);
                        dlgAviso.setAviso("<html><body>¿ Esta seguro de ANULAR <br> la Toma de Inventario ? </body></html>");
                        dlgAviso.setVisible(true);

                        if (DlgAvisoCargaTomaInv.aceptarTransaccion) {
                            if (cargaLogin("A")) {
                                cargarRegistroSeleccionado();
                                try {
                                    if (obtieneIndicadorProceso_ForUpdate(VariablesTomaInv.vSecToma,
                                                                          VariablesTomaInv.vIndProceso)) {
                                        DBTomaInv.limpiarTemporalTomaInv(FarmaVariables.vCodLocal,
                                                                         VariablesTomaInv.vSecToma);
                                        anularToma();
                                        FarmaUtility.aceptarTransaccion();
                                        ///SLEYVA 29/01/2019 INICIO
                                        /*int cantRow = tblRelacionTomas.getRowCount();
                                        switch (cantRow) {
                                        case 0:
                                            DBTomaInv.reasignarRolesUsuario();
                                            DBTomaInv.deleteRolesUsuario();
                                            break;
                                        default:
                                            break;
                                        }*/
                                        DBTomaInv.desactivaRolInventariador();
                                        DBTomaInv.cantTomaInventarios();
                                        cargaTomasInventario();
                                        FarmaUtility.showMessage(this, "La toma se anuló correctamente",
                                                                 tblRelacionTomas);
                                        ///SLEYVA 29/01/2019 FIN
                                    } else {
                                        FarmaUtility.liberarTransaccion();
                                        DBTomaInv.desactivaRolInventariador();
                                        FarmaUtility.aceptarTransaccion();
                                        FarmaUtility.showMessage(this,
                                                                 "No es posible realizar la operacion. La Toma ya se encuentra anulada. Verifique!!!",
                                                                 tblRelacionTomas);
                                        cargaTomasInventario();
                                        return;
                                    }
                                } catch (SQLException sql) {
                                    FarmaUtility.liberarTransaccion();
                                    try {
                                        DBTomaInv.desactivaRolInventariador();
                                        FarmaUtility.aceptarTransaccion();
                                    } catch (SQLException f) {
                                        FarmaUtility.showMessage(this,
                                                                 "No se pudo devolver los roles:\n" + f.getMessage(),
                                                                 tblRelacionTomas);
                                    }
                                    log.error("", sql);
                                    FarmaUtility.showMessage(this, "Ocurrió un error al anular la toma :\n" +
                                            sql.getMessage(), tblRelacionTomas);
                                }
                            }
                        }
                    }
                } else
                    FarmaUtility.showMessage(this,
                                             "Debes iniciar Sesión con el Rol Auditor \n para Eliminar una Toma de Inventario",
                                             tblRelacionTomas);
                
                

                
            }
        }
    }

    // **************************************************************************
    // Metodos de lógica de negocio
    // **************************************************************************

    private void cargaTomasInventario() {
        try {
            DBTomaInv.getListaTomasInv(tableModelTomasInventario);
            if (tblRelacionTomas.getRowCount() > 0)
                FarmaUtility.ordenar(tblRelacionTomas, tableModelTomasInventario, 2, FarmaConstants.ORDEN_ASCENDENTE);

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al obtener las tomas de inventario : \n" +
                    sql.getMessage(), tblRelacionTomas);
        }
    }

    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;
        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    private void cargarRegistroSeleccionado() {
        VariablesTomaInv.vSecToma =
                tblRelacionTomas.getValueAt(tblRelacionTomas.getSelectedRow(), 0).toString().trim();
        // DFLORES: Agregados para inventario 2019
        VariablesTomaInv.vFecIniToma = 
                tblRelacionTomas.getValueAt(tblRelacionTomas.getSelectedRow(), 2).toString().trim();
        VariablesTomaInv.vDescTipoToma = 
                tblRelacionTomas.getValueAt(tblRelacionTomas.getSelectedRow(), 1).toString().trim();        
        // Fin
        VariablesTomaInv.vIndProceso =
                tblRelacionTomas.getValueAt(tblRelacionTomas.getSelectedRow(), 4).toString().trim();
    
    }

    private void cargarToma() throws SQLException {
        DBTomaInv.cargarToma();
    }


    private void verificaDescongelamiento() throws SQLException {
        DBTomaInv.verificarDescongelamiento();
    }

    private void anularToma() throws SQLException {
        DBTomaInv.anularToma();
    }


    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private boolean esTomaValida() {
        boolean rpta = true;
        String indTomaInc = "";
        ArrayList listaLabs;
        String sListaLabs = "";
        try {
            indTomaInc = DBTomaInv.obtenerIndTomaIncompleta().trim();
        } catch (SQLException sql) {
            rpta = false;
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al determinar el estado de la toma : \n" +
                    sql.getMessage(), tblRelacionTomas);
        }
        if (indTomaInc.equals("1")) {
            // La toma esta incompleta
            listaLabs = new ArrayList();
            rpta = false;
            try {
                listaLabs = DBTomaInv.getListaLabsTomaIncompleta();
                for (int i = 0; i < listaLabs.size(); i++) {
                    sListaLabs = sListaLabs + listaLabs.get(i).toString() + "\n";
                }
                FarmaUtility.showMessage(this,
                                         "No se puede efectuar la operacion ya que los siguientes laboratorios no han sido completados correctamente:\n " +
                                         sListaLabs, tblRelacionTomas);
            } catch (SQLException sql) {
                FarmaUtility.showMessage(this, "Ocurrió un error en la transacción:\n" +
                        sql.getMessage(), tblRelacionTomas);
                log.error("", sql);
            }
        }
        return rpta;
    }

    public static boolean obtieneIndicadorProceso_ForUpdate(String pSecTomaInv, String pIndProceso) {
        ArrayList myArray = new ArrayList();
        String indProceso = new String();
        try {
            DBTomaInv.obtieneIndTomaInvForUpdate(myArray, pSecTomaInv, pIndProceso);
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            return false;
        } finally {
            if (myArray.size() > 0) {
                indProceso = ((String)((ArrayList)myArray.get(0)).get(1)).trim();
                log.debug("indProceso : " + indProceso);
                VariablesTomaInv.vIndProceso = indProceso;
                return true;
            } else
                return false;
        }
    }

    private boolean cargaLogin(String estado) {
        String numsec = FarmaVariables.vNuSecUsu;
        String idusu = FarmaVariables.vIdUsu;
        String nomusu = FarmaVariables.vNomUsu;
        String apepatusu = FarmaVariables.vPatUsu;
        String apematusu = FarmaVariables.vMatUsu;

        try {
            DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
            switch(estado){
            case "C":
                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                    dlgLogin.setRolUsuario(FarmaConstants.ROL_AUDITORIA);
                } else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                    dlgLogin.setRolUsuario(FarmaConstants.ROL_ADMLOCAL);
                }
              break;  
            case "A":
                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                    dlgLogin.setRolUsuario(FarmaConstants.ROL_AUDITORIA);
                }
                break;
            default:
                break;
            }
            dlgLogin.setVisible(true);
            FarmaVariables.vNuSecUsu = numsec;
            /**
       * @author : dubilluz
       * @since  : 19.07.2007
       */
            VariablesTomaInv.vIdUsu_Toma = FarmaVariables.vIdUsu;
            FarmaVariables.vIdUsu = idusu;
            FarmaVariables.vNomUsu = nomusu;
            FarmaVariables.vPatUsu = apepatusu;
            FarmaVariables.vMatUsu = apematusu;
        } catch (Exception e) {
            FarmaVariables.vNuSecUsu = numsec;
            FarmaVariables.vIdUsu = idusu;
            FarmaVariables.vNomUsu = nomusu;
            FarmaVariables.vPatUsu = apepatusu;
            FarmaVariables.vMatUsu = apematusu;
            FarmaVariables.vAceptar = false;
            log.error("", e);
            FarmaUtility.showMessage(this, "Ocurrio un error al validar rol de usuariario \n : " + e.getMessage(),
                                     null);
        }
        return FarmaVariables.vAceptar;
    }

}
