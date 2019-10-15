package mifarma.ptoventa.administracion.usuarios;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

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

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.administracion.usuarios.reference.ConstantsUsuarios;
import mifarma.ptoventa.administracion.usuarios.reference.DBUsuarios;
import mifarma.ptoventa.administracion.usuarios.reference.VariablesUsuarios;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgBuscaTrabajador extends JDialog {
    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */
    private static final Logger log = LoggerFactory.getLogger(DlgBuscaTrabajador.class);

    private Frame myParentFrame;
    FarmaTableModel tableModel;

    private final int COL_COD_TRAB = 0;
    private final int COL_COD_TRAB_RRHH = 1;
    private final int COL_APELL_PAT = 2;
    private final int COL_DNI = 5;

    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JButtonLabel btnDniApe = new JButtonLabel();
    private JTextFieldSanSerif txtDniApe = new JTextFieldSanSerif();
    private JScrollPane scrListaTrabajadores = new JScrollPane();
    private JTable tblListaTrabajadores = new JTable();
    private JPanelTitle pnlHeaderUsuarios = new JPanelTitle();
    private JButtonLabel btnRelacion = new JButtonLabel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JLabelFunction lblF12 = new JLabelFunction();
    private JCheckBox chkSoloActivos = new JCheckBox();
    private JButtonLabel btnCantRegistros = new JButtonLabel();

    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */

    public DlgBuscaTrabajador() {
        this(null, "", false);
    }

    public DlgBuscaTrabajador(Frame parent, String title, boolean modal) {
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
    /*                              METODO jbInit                               */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(555, 349));
        this.getContentPane().setLayout(borderLayout1);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });

        jPanelTitle1.setBounds(new Rectangle(10, 10, 525, 40));
        btnDniApe.setText("DNI / Apellido:");
        btnDniApe.setBounds(new Rectangle(5, 10, 80, 20));
        btnDniApe.setMnemonic('D');
        btnDniApe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDniApe_actionPerformed(e);
            }
        });
        txtDniApe.setBounds(new Rectangle(90, 10, 195, 20));
        txtDniApe.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtDniApe_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtDniApe_keyReleased(e);
            }
        });
        scrListaTrabajadores.getViewport().add(tblListaTrabajadores, null);
        scrListaTrabajadores.setBounds(new Rectangle(10, 90, 525, 185));
        pnlHeaderUsuarios.setBounds(new Rectangle(10, 65, 525, 25));
        btnRelacion.setText("Relaci\u00f3n de Trabajadores :");
        btnRelacion.setBounds(new Rectangle(5, 5, 255, 15));
        lblEsc.setBounds(new Rectangle(255, 280, 95, 20));
        lblEsc.setText("[ ESC ] Salir");
        lblF11.setBounds(new Rectangle(70, 280, 95, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF12.setText("[F12] Ver todos");
        lblF12.setBounds(new Rectangle(290, 10, 105, 20));
        lblF12.setHorizontalAlignment(SwingConstants.CENTER);
        chkSoloActivos.setText("[F1]Solo Activos");
        chkSoloActivos.setBounds(new Rectangle(400, 10, 115, 20));
        chkSoloActivos.setBackground(new Color(255, 130, 14));
        chkSoloActivos.setFont(new Font("SansSerif", 1, 11));
        chkSoloActivos.setForeground(SystemColor.window);
        btnCantRegistros.setBounds(new Rectangle(265, 5, 250, 15));
        btnCantRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
        jPanelTitle1.add(chkSoloActivos, null);
        jPanelTitle1.add(lblF12, null);
        jPanelTitle1.add(btnDniApe, null);
        jPanelTitle1.add(txtDniApe, null);
        pnlHeaderUsuarios.add(btnCantRegistros, null);
        pnlHeaderUsuarios.add(btnRelacion, null);
        jPanelWhite1.add(lblF11, null);
        jPanelWhite1.add(lblEsc, null);
        jPanelWhite1.add(pnlHeaderUsuarios, null);
        jPanelWhite1.add(scrListaTrabajadores, null);
        jPanelWhite1.add(jPanelTitle1, null);
        this.getContentPane().add(jPanelWhite1, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
                new FarmaTableModel(ConstantsUsuarios.columnsListaTrabajadores, ConstantsUsuarios.defaultValuesListaTrabajadores,
                                    0);
        FarmaUtility.initSimpleList(tblListaTrabajadores, tableModel, ConstantsUsuarios.columnsListaTrabajadores);
        cargaListaTrabajadores();
    }

    private void cargaListaTrabajadores() {
        try {
            String flagOtraCia="N";  // JVARA 16-08-2017
            String flagTrabajadoresActivo="S";  // JVARA 16-08-2017
            DBUsuarios.obtieneListaTrabajadores(tableModel,flagOtraCia,flagTrabajadoresActivo); // JVARA 16-08-2017
            eliminarInactivos();
            if (tblListaTrabajadores.getRowCount() > 0) {
                FarmaUtility.ordenar(tblListaTrabajadores, tableModel, COL_APELL_PAT, FarmaConstants.ORDEN_ASCENDENTE);
                FarmaUtility.setearPrimerRegistro(tblListaTrabajadores, txtDniApe, COL_APELL_PAT);
            }
            //log.debug("Lista de trabajadores cargada!");
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al obtener los usuarios. \n " + e.getMessage(), txtDniApe);
        }
    }
    
    private void eliminarInactivos() {
        if(chkSoloActivos.isSelected()){
            for(int i=0;i<tableModel.getRowCount();i++){
                String estado=tableModel.getRow(i).get(6).toString().trim();
                System.out.println("===> "+estado);
                if(estado.equalsIgnoreCase("I")){
                    tableModel.deleteRow(i);
                    i--;
                }
            }
        }
    }
    private void cargaTodosTrabajadores() {
        try {
            DBUsuarios.obtieneTodosTrabajadores(tableModel);
            eliminarInactivos();
            if (tblListaTrabajadores.getRowCount() > 0) {
                FarmaUtility.ordenar(tblListaTrabajadores, tableModel, COL_APELL_PAT, FarmaConstants.ORDEN_ASCENDENTE);
                FarmaUtility.setearPrimerRegistro(tblListaTrabajadores, txtDniApe, COL_APELL_PAT);
            }
            //log.debug("Lista de trabajadores cargada!");
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al obtener los usuarios. \n " + e.getMessage(), txtDniApe);
        }
    }
    
    /* ************************************************************************ */
    /*                           METODOS DE EVENTOS                             */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtDniApe);
        //mostrarDatos();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void btnDniApe_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtDniApe);
    }

    private void txtDniApe_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void txtDniApe_keyReleased(KeyEvent e) {
        if (txtDniApe.getText().length() == 1) {
            if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                //log.debug("BUSCA DNI");
                VariablesUsuarios.vColBuscarTrab = COL_DNI;
            } else {
                //log.debug("BUSCA APELLIDO");
                VariablesUsuarios.vColBuscarTrab = COL_APELL_PAT;
            }
        }
        FarmaGridUtils.buscarDescripcion(e, tblListaTrabajadores, txtDniApe, VariablesUsuarios.vColBuscarTrab);
    }

    /* ************************************************************************ */
    /*                      METODOS AUXILIARES DE EVENTOS                       */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblListaTrabajadores, txtDniApe, COL_APELL_PAT);

        if (UtilityPtoVenta.verificaVK_F11(e)) {
            //LTAVARA 2017.08.21

            // KMONCADA 19.09.2014 NO PERMITE SELECCION DE EMPLEADOS INACTIVOS
            try {
                if( DBUsuarios.validarInactivarUsuarioMaeTrab(tblListaTrabajadores.getValueAt(tblListaTrabajadores.getSelectedRow(), 5).toString().trim()).equals("N")){
                    FarmaUtility.showMessage(this,
                                             "El Usuario está Inactivo por el área de RRHH.",
                                             txtDniApe);
                    return;
                }
            } catch (SQLException f) {
            }
            String estadoEmpleado =
                FarmaUtility.getValueFieldArrayList(tableModel.data, tblListaTrabajadores.getSelectedRow(), 6);
            if ("I".equalsIgnoreCase(estadoEmpleado)) {
                FarmaUtility.showMessage(this, "El usuario se encuentra inactivo, no se puede seleccionar.", null);
                return;
            }
            cargaDatosTrabajador();
            txtDniApe.setText("");
            cerrarVentana(true);
            //this.setVisible(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            VariablesUsuarios.vCodTrab = "";
            cerrarVentana(false);
        }else if (e.getKeyCode() == KeyEvent.VK_F12) {
            if(!lblF12.getText().trim().equalsIgnoreCase("[F12] Ver de CIA")){
                cargaTodosTrabajadores();
                lblF12.setText("[F12] Ver de CIA");
                btnRelacion.setText("Relación de todos los colaboradores :");
            }else{
                cargaListaTrabajadores();
                lblF12.setText("[F12] Ver Todos");
                btnRelacion.setText("Relación de Trabajadores :");
            }
            btnCantRegistros.setText(tableModel.getRowCount()+" Registros recuperados");
        }else if (e.getKeyCode() == KeyEvent.VK_F1) {
            chkSoloActivos.setSelected(!chkSoloActivos.isSelected());
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

    /**
     * Se carga los datos del trabajador seleccioando.
     */
    private void cargaDatosTrabajador() {
        int vFilaSelect = tblListaTrabajadores.getSelectedRow();

        VariablesUsuarios.vCodTrab = FarmaUtility.getValueFieldJTable(tblListaTrabajadores, vFilaSelect, COL_COD_TRAB);
        VariablesUsuarios.vCodTrab_RRHH =
                FarmaUtility.getValueFieldJTable(tblListaTrabajadores, vFilaSelect, COL_COD_TRAB_RRHH);
        /*VariablesUsuarios.vApePat = (String) tblListaTrabajadores.getValueAt(vFilaSelect,1);
    VariablesUsuarios.vApeMat = (String) tblListaTrabajadores.getValueAt(vFilaSelect,2);
    VariablesUsuarios.vNombres = (String) tblListaTrabajadores.getValueAt(vFilaSelect,3);
    VariablesUsuarios.vDNI = (String) tblListaTrabajadores.getValueAt(vFilaSelect,4);
    VariablesUsuarios.vDireccion = (String) tblListaTrabajadores.getValueAt(vFilaSelect,6);
    VariablesUsuarios.vTelefono = (String) tblListaTrabajadores.getValueAt(vFilaSelect,7);
    VariablesUsuarios.vFecNac = (String) tblListaTrabajadores.getValueAt(vFilaSelect,8);

    log.debug("Datos: "+ VariablesUsuarios.vCodTrab);
    log.debug("Datos: "+ VariablesUsuarios.vApePat);
    log.debug("Datos: "+ VariablesUsuarios.vApeMat);
    log.debug("Datos: "+ VariablesUsuarios.vNombres);
    log.debug("Datos: "+ VariablesUsuarios.vDNI);
    log.debug("Datos: "+ VariablesUsuarios.vDireccion);
    log.debug("Datos: "+ VariablesUsuarios.vTelefono);
    log.debug("Datos: "+ VariablesUsuarios.vFecNac);*/

    }

}
