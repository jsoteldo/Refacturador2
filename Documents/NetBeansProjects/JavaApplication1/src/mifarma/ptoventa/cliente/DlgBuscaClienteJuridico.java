package mifarma.ptoventa.cliente;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cliente.reference.ConstantsCliente;
import mifarma.ptoventa.cliente.reference.DBCliente;
import mifarma.ptoventa.cliente.reference.UtilityCliente;
import mifarma.ptoventa.cliente.reference.VariablesCliente;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2006 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DlgBuscaClienteJuridico.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * LMESIA      23.02.2006   Creación<br>
 * PAULO       03.03.2006   Modificacion
 * <br>
 * @author Luis Mesia Rivera<br>
 * @version 1.0<br>
 *
 */

public class DlgBuscaClienteJuridico extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgBuscaClienteJuridico.class);

    private Frame myParentFrame;
    FarmaTableModel tableModel;
    public FarmaTableModel tableModelListaClienteJuridico;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JPanelHeader pnlCliente = new JPanelHeader();
    
    private JPanelTitle pnlRelacionCliente = new JPanelTitle();
    private JScrollPane scrClienteJuridico = new JScrollPane();
    public  JTable tblClienteJuridico = new JTable();
    private JLabelFunction lblF3 = new JLabelFunction();
    private JLabelFunction lblF4 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JButtonLabel btnRelacion = new JButtonLabel();
    private JButtonLabel btnClienteJuridico = new JButtonLabel();
    private JTextFieldSanSerif txtClienteJuridico = new JTextFieldSanSerif();
    private JButton btnBuscar = new JButton();
    
    private JLabelFunction lblF6 = new JLabelFunction();
    private JLabelFunction lblF8 = new JLabelFunction();
    private JRadioButton rbtJuridico = new JRadioButton();
    private JRadioButton rbtNatural = new JRadioButton();
    private ButtonGroup grupoRadio = new ButtonGroup();
    private JPanel jPanel3 = new JPanel();
    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgBuscaClienteJuridico() {
        this(null, "", false);
    }

    public DlgBuscaClienteJuridico(Frame parent, String title, boolean modal) {
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
        this.setSize(new Dimension(735, 418));
        this.getContentPane().setLayout(null);
        this.getContentPane().setLayout(borderLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Relacion de Clientes");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setLayout(null);
        pnlCliente.setBounds(new Rectangle(5, 5, 470, 80));
        pnlRelacionCliente.setBounds(new Rectangle(5, 85, 715, 25));
        scrClienteJuridico.setBounds(new Rectangle(5, 110, 715, 235));
        tblClienteJuridico.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblClienteJuridico_keyPressed(e);
            }
        });
        
        
        //jPanel3.setBackground(new Color(255, 130, 14));
        //jPanel3.setBorder(BorderFactory.createTitledBorder(""));
        jPanel3.setBounds(new Rectangle(475, 5, 245, 80));        
        jPanel3.setLayout(null);


        jPanel3.setBackground(SystemColor.window);
        lblF3.setBounds(new Rectangle(5, 355, 115, 20));
        lblF3.setText("[ F3 ] Crear");
        lblF4.setBounds(new Rectangle(135, 355, 125, 20));
        lblF4.setText("[ F4 ] Modificar");
        lblEsc.setBounds(new Rectangle(630, 355, 85, 20));
        lblEsc.setText("[ Esc ] Cerrar");
        btnRelacion.setText("Relacion de Clientes ");
        btnRelacion.setBounds(new Rectangle(10, 5, 180, 15));
        btnRelacion.setMnemonic('r');
        btnRelacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacion_actionPerformed(e);
            }
        });
        btnClienteJuridico.setText("Cliente :");
        btnClienteJuridico.setBounds(new Rectangle(10, 0, 105, 25));
        btnClienteJuridico.setMnemonic('c');
        btnClienteJuridico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnClienteJuridico_actionPerformed(e);
            }
        });
        txtClienteJuridico.setBounds(new Rectangle(10, 30, 315, 20));
        txtClienteJuridico.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtClienteJuridico_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtClienteJuridico_keyReleased(e);
            }
        });
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(340, 25, 110, 25));
        btnBuscar.setBackground(SystemColor.control);
        btnBuscar.setMnemonic('b');
        btnBuscar.setDefaultCapable(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.setFont(new Font("SansSerif", 1, 12));
        
        lblF6.setBounds(new Rectangle(35, 50, 175, 20));
        lblF6.setText("[ F8 ] Cambia Tipo Cliente");
        lblF8.setBounds(new Rectangle(505, 355, 110, 20));
        lblF8.setText("[ F11 ] Aceptar");
        grupoRadio.add(rbtJuridico);
        grupoRadio.add(rbtNatural);
        rbtJuridico.setText("Juridico");
        rbtJuridico.setFont(new Font("SansSerif", 1, 14));
        rbtJuridico.setFocusPainted(false);
        rbtJuridico.setRequestFocusEnabled(false);
        rbtJuridico.setFocusable(false);
        rbtJuridico.setSelected(true);
        rbtJuridico.setBounds(new Rectangle(5, 5, 115, 25));
        rbtJuridico.setLayout(null);

        rbtJuridico.setBackground(SystemColor.window);
        rbtNatural.setText("Natural");
        rbtNatural.setFont(new Font("SansSerif", 1, 14));
        rbtNatural.setFocusPainted(false);
        rbtNatural.setRequestFocusEnabled(false);
        rbtNatural.setFocusable(false);
        rbtNatural.setSelected(false);
        rbtNatural.setBounds(new Rectangle(130, 5, 110, 25));
        rbtNatural.setLayout(null);

        rbtNatural.setBackground(SystemColor.window);
        jContentPane.add(lblF8, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(lblF4, null);
        jContentPane.add(lblF3, null);
        scrClienteJuridico.getViewport().add(tblClienteJuridico, null);
        jContentPane.add(scrClienteJuridico, null);
        pnlRelacionCliente.add(btnRelacion, null);
        jContentPane.add(pnlRelacionCliente, null);
        jContentPane.add(pnlCliente, null);
        jContentPane.add(jPanel3, null);
        jPanel3.add(rbtNatural, null);
        jPanel3.add(rbtJuridico, null);
        jPanel3.add(lblF6, null);
        pnlCliente.add(txtClienteJuridico, null);
        pnlCliente.add(btnClienteJuridico, null);
        pnlCliente.add(btnBuscar, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        initTableListaClienteJuridico();
        //seleccionTipoCliente();
        FarmaVariables.vAceptar = false;
    };

    // **************************************************************************
    // Métodos de inicialización
    // **************************************************************************

    private void initTableListaClienteJuridico() {
        tableModelListaClienteJuridico =
                new FarmaTableModel(ConstantsCliente.columnsListaClientesJuridicos, ConstantsCliente.defaultValuesListaClientesJuridicos,
                                    0);
        FarmaUtility.initSimpleList(tblClienteJuridico, tableModelListaClienteJuridico,
                                    ConstantsCliente.columnsListaClientesJuridicos);
    }
    
    private void seleccionTipoCliente() {
        if (VariablesCliente.vTipoBusqueda.equalsIgnoreCase(ConstantsCliente.TIPO_JURIDICO)) {
            rbtNatural.setSelected(false);
            rbtJuridico.setSelected(true);
        } else {
            rbtNatural.setSelected(true);
            rbtJuridico.setSelected(false);
        }
    }
    
    private void cambiaTipoCliente() {        
        if (rbtJuridico.isSelected()) {
            rbtNatural.setSelected(true);
            rbtJuridico.setSelected(false);
            VariablesCliente.vTipoBusqueda = ConstantsCliente.TIPO_JURIDICO;
        } else if (rbtNatural.isSelected()) {
            rbtNatural.setSelected(false);
            rbtJuridico.setSelected(true);
            VariablesCliente.vTipoBusqueda = ConstantsCliente.TIPO_NATURAL;
        }
    }
   

    public void cargaClienteJuridico() {
        try {
            log.debug(VariablesCliente.vRuc_RazSoc_Busqueda);
            log.debug(VariablesCliente.vTipoBusqueda);
            (new UtilityCliente()).consultarClienteJuridico(this, tableModelListaClienteJuridico, VariablesCliente.vRuc_RazSoc_Busqueda, VariablesCliente.vTipoBusqueda);
            FarmaUtility.ordenar(tblClienteJuridico, tableModelListaClienteJuridico, 2,
                                     FarmaConstants.ORDEN_ASCENDENTE);
            if (tblClienteJuridico.getRowCount() > 0)
                FarmaUtility.setearPrimerRegistro(tblClienteJuridico, txtClienteJuridico, 2);
            else
                FarmaUtility.showMessage(this, "No se encontro ningun Cliente para esta Busqueda", txtClienteJuridico);
            
        } catch (Exception e) {
            log.error("", e);
            FarmaUtility.showMessage(this, "Error al listar Clientes Juridicos", null);
            cerrarVentana(false);
        }
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.moveFocus(txtClienteJuridico);
        FarmaUtility.centrarVentana(this);
        if (VariablesCliente.vIndicadorCargaCliente.equals(FarmaConstants.INDICADOR_S)) {
            cargaClienteJuridico();
            rbtJuridico.setSelected(true);
            rbtNatural.setSelected(false);
        }
    }

    private void txtClienteJuridico_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblClienteJuridico, txtClienteJuridico, 2);

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            txtClienteJuridico.setText(txtClienteJuridico.getText().trim().toUpperCase());
            String textoBusqueda = txtClienteJuridico.getText().trim();

            if (textoBusqueda.length() >= 3) {
                boolean isNumero = true;
                int k=0;
                while(isNumero && k<textoBusqueda.length()){
                    isNumero = isNumero && Character.isDigit(textoBusqueda.charAt(k));
                    k++;
                }                
                if(isNumero){
                    muestraMsj("NUMERO: "+textoBusqueda.length());
                    if(ConstantsCliente.RESULTADO_RUC_VALIDO.equalsIgnoreCase(DBCliente.verificaRucValido2(textoBusqueda))){
                        log.info("[SELECCION DE TIPO COMPROBANTE]: RUC VALIDO");
                        VariablesCliente.vRuc = textoBusqueda;
                        buscaClienteJuridico(ConstantsCliente.TIPO_BUSQUEDA_RUC, textoBusqueda);
                    }else{
                        if(textoBusqueda.length()==8){
                            log.info("[SELECCION DE TIPO COMPROBANTE]: RUC VALIDO");
                            VariablesCliente.vRuc = textoBusqueda;
                            buscaClienteJuridico(ConstantsCliente.TIPO_BUSQUEDA_RUC, textoBusqueda);
                        }else{
                            log.info("[SELECCION DE TIPO COMPROBANTE]: SE BUSCARA COMO DNI");
                            buscaClienteJuridico(ConstantsCliente.TIPO_BUSQUEDA_RUC, textoBusqueda);
                            /*
                            txtClienteJuridico.setText("");
                            FarmaUtility.showMessage(this,"Ingrese un nro de RUC o DNI correcto",txtClienteJuridico);
                            */
                        }
                    }
                    
                }else{
                    log.info("[SELECCION DE TIPO COMPROBANTE]: SE BUSCARA COMO RAZON SOCIAL");
                    buscaClienteJuridico(ConstantsCliente.TIPO_BUSQUEDA_RAZSOC, textoBusqueda);
                }
            } else
                FarmaUtility.showMessage(this, "Ingrese 3 caracteres como minimo para realizar la busqueda",
                                         txtClienteJuridico);
        }

        if (e.getKeyCode() == KeyEvent.VK_F8) {
            //if (VariablesCliente.vIndicadorCargaCliente.equals(FarmaConstants.INDICADOR_N))
            cambiaTipoCliente();
        }
        chkKeyPressed(e);
    }

    private void buscaClienteJuridico(String pTipoBusqueda, String pBusqueda) {
        VariablesCliente.vTipoBusqueda = pTipoBusqueda;
        VariablesCliente.vRuc_RazSoc_Busqueda = pBusqueda;
        cargaClienteJuridico();
    }

    private void txtClienteJuridico_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblClienteJuridico, txtClienteJuridico, 2);
    }

    private void mostrarDatoCliente() {
        String nombre = tableModel.getValueAt(tblClienteJuridico.getSelectedRow(), 2).toString();
        txtClienteJuridico.setText(nombre);
        log.debug(nombre);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F3) {
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, txtClienteJuridico);
            else {
                if (rbtJuridico.isSelected()) {
                    mantenimientoClienteJuridico(ConstantsCliente.ACCION_INSERTAR);
                } else
                    mantenimientoClienteNatural(ConstantsCliente.ACCION_INSERTAR);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F4) {
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, txtClienteJuridico);
            else {
                if (tblClienteJuridico.getRowCount() <= 0)
                    return;
                if (rbtJuridico.isSelected()) {
                    guardaRegistroCliente();
                    mantenimientoClienteJuridico(ConstantsCliente.ACCION_MODIFICAR);
                } else {
                    VariablesCliente.vCodigo =
                            ((String)tblClienteJuridico.getValueAt(tblClienteJuridico.getSelectedRow(), 0)).trim();
                    guardaRegistroCliente();
                    mantenimientoClienteNatural(ConstantsCliente.ACCION_MODIFICAR);
                }
            }
        }

        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        } else if (UtilityPtoVenta.verificaVK_F11(e)) {
            guardaRegistroCliente();
            if(validaDatosCliente()){
                if (VariablesCliente.vIndicadorCargaCliente.equals(FarmaConstants.INDICADOR_S))
                    cerrarVentana(true);
            }
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
    private void guardaRegistroCliente() {
        if (tblClienteJuridico.getRowCount() > 0) {
            VariablesCliente.vArrayList_Cliente_Juridico.clear();
            VariablesCliente.vArrayList_Cliente_Juridico.add(tableModelListaClienteJuridico.data.get(tblClienteJuridico.getSelectedRow()));
            int i=tblClienteJuridico.getSelectedRow();
            VariablesVentas.vRuc_Cli_Ped = tableModelListaClienteJuridico.getValueAt(i,1).toString().trim();
            VariablesVentas.vNom_Cli_Ped = tableModelListaClienteJuridico.getValueAt(i,2).toString().trim();
        }
    }

    private void mantenimientoClienteJuridico(String pTipoAccion) {
        VariablesCliente.vTipo_Accion = pTipoAccion;
        DlgMantClienteJuridico dlgMantClienteJuridico = new DlgMantClienteJuridico(myParentFrame, "", true);
        dlgMantClienteJuridico.setVisible(true);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cargaClienteJuridico();

            if (VariablesCliente.vIndicadorCargaCliente.equals(FarmaConstants.INDICADOR_S))
                cerrarVentana(true);
        }
    }

    private void listadoTarjetasClientes(String pTipoAccion) {
        VariablesCliente.vTipo_Accion = pTipoAccion;
        DlgMantTarjetaCliente dlgMantTarjetaCliente = new DlgMantTarjetaCliente(myParentFrame, "", true);
        dlgMantTarjetaCliente.setVisible(true);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cerrarVentana(true);
        }
    }

    private void mantenimientoClienteNatural(String pTipoAccion) {
        VariablesCliente.vTipo_Accion = pTipoAccion;
        DlgMantClienteNatural dlgMantClienteNatural = new DlgMantClienteNatural(myParentFrame, "", true);
        dlgMantClienteNatural.setVisible(true);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            cargaClienteJuridico();
        }
    }

    private void tblClienteJuridico_keyPressed(KeyEvent e) {
        txtClienteJuridico_keyPressed(e);
    }

    private void btnClienteJuridico_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtClienteJuridico);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void btnRelacion_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocusJTable(tblClienteJuridico);
    }

    private void muestraMsj(String msj) {
        FarmaUtility.showMessage(this, msj, null);
    }
    
    private boolean validaDatosCliente() {
        if (VariablesVentas.vTip_Comp_Ped.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA)) {
            if (VariablesVentas.vRuc_Cli_Ped.trim().length()!=11) {
                FarmaUtility.showMessage(this, "El cliente no tiene un Nro de RUC Valido", txtClienteJuridico);
                return false;
            }
            /*
            if (VariablesVentas.vCod_Cli_Local.equalsIgnoreCase("")) {
                FarmaUtility.showMessage(this, "Falta Codigo del Cliente.Verifique!!!", txtClienteJuridico);
                return false;
            }*/
            if (VariablesVentas.vRuc_Cli_Ped.equalsIgnoreCase("")) {
                FarmaUtility.showMessage(this, "Falta RUC del Cliente.Verifique!!!", txtClienteJuridico);
                return false;
            }
            if (VariablesVentas.vNom_Cli_Ped.equalsIgnoreCase("")) {
                FarmaUtility.showMessage(this, "Falta Razon Social del Cliente.Verifique!!!", txtClienteJuridico);
                return false;
            }
        } else {
            // KMONCADA 19.11.2014 EN CASO DE BOLETAS SOLICITARA LOS DATOS
            if ("".equalsIgnoreCase(VariablesVentas.vRuc_Cli_Ped.trim())) {
                FarmaUtility.showMessage(this, "Falta DNI del Cliente.Verifique!!!", txtClienteJuridico);
                return false;
            }
            if ("".equalsIgnoreCase(VariablesVentas.vNom_Cli_Ped.trim())) {
                FarmaUtility.showMessage(this, "Falta NOMBRE del Cliente.Verifique!!!", txtClienteJuridico);
                return false;
            }
        }

        return true;
    }
}
