package mifarma.ptoventa.inventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import mifarma.common.FarmaConnectionRemoto;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;

import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgTransferenciasRealizadas extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgTransferenciasRealizadas.class);
    Frame myParentFrame;
    FarmaTableModel tableModel;
    private String filtro;
    private String CodOrigenTranf;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JScrollPane scrListaTransferencias = new JScrollPane();
    private JTable tblListaTransferencias = new JTable();
    private JButtonLabel btnRelacionTransferencias = new JButtonLabel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblF8 = new JLabelFunction();
    private JButtonLabel lblCantTransfereniasT = new JButtonLabel();
    private JButtonLabel lblCantTransferencias = new JButtonLabel();
    private JLabelFunction lblF6 = new JLabelFunction();
    private JLabelFunction lblF7 = new JLabelFunction();
    
    private JPanelHeader pnlFiltrosBusqueda = new JPanelHeader();
    private JButtonLabel btnTItulo1 = new JButtonLabel();
    private JButtonLabel btnFechaDe = new JButtonLabel();
    private JTextField txtFechaInicio = new JTextField();
    private JButtonLabel btnHasta = new JButtonLabel();
    private JTextField txtFechaFin = new JTextField();
    private JButtonLabel btnTitulo2 = new JButtonLabel();
    private JButton btnBuscar = new JButton();
    private JButtonLabel btnTipTransfer = new JButtonLabel();
    private JComboBox cmbTipoTransferencia = new JComboBox();
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgTransferenciasRealizadas() {
        this(null, "", false);
    }

    public DlgTransferenciasRealizadas(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            FarmaUtility.centrarVentana(this);
            initCombo();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(799, 469));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Relacion de Transferencias");
        this.setDefaultCloseOperation(0);
        //this.setBounds(new Rectangle(10, 10, 790, 394));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlTitle1.setBounds(new Rectangle(10, 80, 770, 25));
        scrListaTransferencias.setBounds(new Rectangle(10, 105, 770, 260));
        tblListaTransferencias.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblListaTransferencias_keyPressed(e);
            }
        });
        btnRelacionTransferencias.setText("Relacion de Transferencias");
        btnRelacionTransferencias.setBounds(new Rectangle(5, 5, 165, 15));
        btnRelacionTransferencias.setMnemonic('R');
        btnRelacionTransferencias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionTransferencias_actionPerformed(e);
            }
        });
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(675, 410, 90, 20));
        lblF2.setText("[ F2 ] Ver Transferencia");
        lblF2.setBounds(new Rectangle(185, 380, 155, 20));
        lblF1.setText("[ F1 ] Nueva Transferencia");
        lblF1.setBounds(new Rectangle(15, 380, 160, 20));
        lblF8.setText("[ F8 ] Por Confirmar");
        lblF8.setBounds(new Rectangle(610, 380, 155, 20));
        lblCantTransfereniasT.setBounds(new Rectangle(715, 5, 45, 15));
        lblCantTransferencias.setText("Cantidad de Transferencias");
        lblCantTransferencias.setBounds(new Rectangle(545, 5, 160, 15));
        lblF6.setBounds(new Rectangle(350, 380, 117, 19));
        lblF6.setText("[ F6 ] Filtrar");
        lblF7.setBounds(new Rectangle(480, 380, 117, 19));
        lblF7.setText("[ F7 ] Quitar Filtro");
        pnlFiltrosBusqueda.setBounds(new Rectangle(10, 5, 770, 75));
        btnTItulo1.setText("Busqueda por rango de fechas:");
        btnTItulo1.setBounds(new Rectangle(10, 5, 185, 15));

        btnFechaDe.setText("De:");
        btnFechaDe.setBounds(new Rectangle(15, 35, 25, 20));
        btnFechaDe.setMnemonic('D');


        btnFechaDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnFechaDe_ActionPerformed(e);
            }
        });
        txtFechaInicio.setBounds(new Rectangle(40, 35, 100, 20));

        txtFechaInicio.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaIni_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaIni_keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {
                txtFechaIni_keyTyped(e);
            }
        });

        btnHasta.setText("Hasta:");
        btnHasta.setBounds(new Rectangle(150, 35, 35, 20));
        btnHasta.setMnemonic('H');

        btnHasta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnHasta_ActionPerformed(e);
            }
        });
        txtFechaFin.setBounds(new Rectangle(190, 35, 100, 20));
        txtFechaFin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaFin_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaFin_keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {
                txtFechaFin_KeyTyped(e);
            }
        });

        btnTitulo2.setText("Busqueda por codigo de transferencia:");
        btnTitulo2.setBounds(new Rectangle(300, 5, 220, 15));
        btnBuscar.setText("[F12] Buscar");
        btnBuscar.setBounds(new Rectangle(625, 35, 115, 20));
        btnBuscar.setMnemonic('B');

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_ActionPerformed(e);
            }
        });
        btnTipTransfer.setText("Tipo Transferencia:");
        btnTipTransfer.setBounds(new Rectangle(305, 35, 110, 20));
        btnTipTransfer.setMnemonic('T');

        btnTipTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //btnCodTransfer_ActionPerformed(e);
                btnTipTransfer_ActionPerformed(e);
            }
        });
        cmbTipoTransferencia.setBounds(new Rectangle(420, 35, 160, 20));
        cmbTipoTransferencia.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipoTransferrencia_KeyPressed(e);
            }
        });
        pnlFiltrosBusqueda.add(cmbTipoTransferencia, null);
        pnlFiltrosBusqueda.add(btnTipTransfer, null);
        pnlFiltrosBusqueda.add(btnBuscar, null);
        pnlFiltrosBusqueda.add(btnTitulo2, null);
        pnlFiltrosBusqueda.add(txtFechaFin, null);
        pnlFiltrosBusqueda.add(btnHasta, null);
        pnlFiltrosBusqueda.add(txtFechaInicio, null);
        pnlFiltrosBusqueda.add(btnFechaDe, null);
        pnlFiltrosBusqueda.add(btnTItulo1, null);
        jContentPane.add(pnlFiltrosBusqueda, null);
        scrListaTransferencias.getViewport().add(tblListaTransferencias, null);
        jContentPane.add(lblF7, null);
        jContentPane.add(lblF6, null);
        jContentPane.add(lblF8, null);
        jContentPane.add(lblF1, null);
        jContentPane.add(lblF2, null);
        jContentPane.add(lblEsc, null);
        scrListaTransferencias.getViewport().add(tblListaTransferencias, null);
        jContentPane.add(scrListaTransferencias, null);
        jContentPane.add(pnlTitle1, null);
        pnlTitle1.add(lblCantTransferencias, null);
        pnlTitle1.add(lblCantTransfereniasT, null);
        pnlTitle1.add(btnRelacionTransferencias, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        initTable();
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    private void initTable() {
        tableModel =
                new FarmaTableModel(ConstantsInventario.columnsListaTransferenciasRealizadas, ConstantsInventario.defaultValuesListaTransferenciasRealizadas,
                                    0);
        FarmaUtility.initSimpleList(tblListaTransferencias, tableModel,
                                    ConstantsInventario.columnsListaTransferenciasRealizadas);
        filtro = "%";
        cargaListaTransferencias();
    }

    private void cargaListaTransferencias() {
        try {
            DBInventario.cargaListaTransferencias(tableModel, filtro);
            lblCantTransfereniasT.setText("" + tblListaTransferencias.getRowCount());
            FarmaUtility.ordenar(tblListaTransferencias, tableModel, 0, FarmaConstants.ORDEN_DESCENDENTE);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al listar las transferencias: \n" +
                    sql.getMessage(), tblListaTransferencias);
        }
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void btnRelacionTransferencias_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblListaTransferencias);
    }

    private void tblListaTransferencias_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(tblListaTransferencias);
        try {
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            String fechaAtrasada =
                FarmaSearch.getFechaHoraAtrasadaBD(FarmaConstants.FORMATO_FECHA, 7); //Change by Cesar Huanes
            txtFechaInicio.setText(fechaAtrasada);
            txtFechaFin.setText(fechaActual); //Change by Cesar Huanes
        } catch (SQLException sql) {
            muestraMensaje("No existe solicitudes de fraccionamiento en la fecha indicada!!!!!.", sql.getMessage());
        }
    }

    private void muestraMensaje(String msj, Object cTxt) {
        FarmaUtility.showMessage(this, msj, cTxt);
    }

    private void txtFechaIni_keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
            FarmaUtility.moveFocus(txtFechaFin);
            break;
        case KeyEvent.VK_UP:
        case KeyEvent.VK_DOWN:
            FarmaUtility.moveFocusJTable(tblListaTransferencias);
            break;
        default:
            chkKeyPressed(e);
        }
    }
    
    private void txtFechaIni_keyReleased(KeyEvent e) {
            FarmaUtility.dateComplete(txtFechaInicio, e);
        }

        private void txtFechaFin_keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
            opcion = 1;
            btnBuscar.doClick();
            break;
        case KeyEvent.VK_UP:
        case KeyEvent.VK_DOWN:
            FarmaUtility.moveFocusJTable(tblListaTransferencias);
            break;
        default:
            chkKeyPressed(e);
        }
    }

    private void txtFechaFin_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaFin, e);
    }

    private boolean validarCampos() {
        boolean retorno = true;
        if (txtFechaInicio.getText().trim().equals("")) {
            retorno = false;
            muestraMensaje("Ingrese Fecha de Inicio.", txtFechaInicio);
        } else if (txtFechaFin.getText().trim().equals("")) {
            retorno = false;
            muestraMensaje("Ingrese Fecha de Fin. ", txtFechaFin);
        } else if (!FarmaUtility.validaFecha(txtFechaInicio.getText(), "dd/MM/yyyy")) {
            retorno = false;
            muestraMensaje("Formato Incorrecto de Fecha.", txtFechaInicio);
        } else if (!FarmaUtility.validaFecha(txtFechaFin.getText(), "dd/MM/yyyy")) {
            retorno = false;
            muestraMensaje("Formato Incorrecto de Fecha.", txtFechaFin);
        } else if (!FarmaUtility.validarRangoFechas(this, txtFechaInicio, txtFechaFin, false, true, true, true))
            retorno = false;
        return retorno;
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e) {
        //FarmaGridUtils.aceptarTeclaPresionada(e,tblListaTransferencias,null,0);

        if (UtilityPtoVenta.verificaVK_F1(e)) {
            if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_SUPERVISOR_VENTAS)) {
                if (FarmaVariables.vEconoFar_Matriz)
                    FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, tblListaTransferencias);
                else {
                    VariablesInventario.vArrayTransferenciaProductos = new ArrayList();
                    VariablesInventario.vHistoricoLote = true;
                    DlgTransferenciasNueva dlgTransferenciasNueva =
                        new DlgTransferenciasNueva(myParentFrame, "", true);
                    dlgTransferenciasNueva.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        //JCORTEZ 28.10.09 Se confirmara automaticamente si es que se generaron e imprimieron guias.
                        log.debug(":JCORTEZ:::::vTipoDestino_Transf::::::" + VariablesInventario.vTipoDestino_Transf);

                        if (VariablesInventario.vTipoDestino_Transf.equalsIgnoreCase("01")) { //solo confirmacion automatica para locales
                            if (validaGuiasEmitidas(VariablesInventario.vNumNotaEs)) {
                                log.debug(":JCORTEZ:::::Confirmando transferencia::::" +
                                          VariablesInventario.vNumNotaEs);
                                confirmaTransferencia();
                            }
                        } else {
                            FarmaUtility.showMessage(this, "Transferencia generada!", tblListaTransferencias);
                        }

                        cargaListaTransferencias();
                        FarmaVariables.vAceptar = false;
                    }
                }
            } else {
                FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción", null);
            }
        } else if (UtilityPtoVenta.verificaVK_F2(e)) { // JVARA 16-08_2017 VER TRANSFERENCIAS
            VariablesInventario.vPos = tblListaTransferencias.getSelectedRow();
            VariablesInventario.vNumNota =
                    tblListaTransferencias.getValueAt(tblListaTransferencias.getSelectedRow(), 0).toString();
            VariablesInventario.vEstadoNota =
                    tblListaTransferencias.getValueAt(tblListaTransferencias.getSelectedRow(), 4).toString();
            VariablesInventario.vTipoNota =
                    tblListaTransferencias.getValueAt(tblListaTransferencias.getSelectedRow(), 9).toString();
            VariablesInventario.vTipoNotaOrigen =
                    tblListaTransferencias.getValueAt(tblListaTransferencias.getSelectedRow(), 10).toString();
            DlgTransferenciasVer dlgTransferenciasVer = new DlgTransferenciasVer(myParentFrame, "", true);
            dlgTransferenciasVer.setVisible(true);
            if (FarmaVariables.vAceptar) {
                cargaListaTransferencias();
                FarmaVariables.vAceptar = false;
                FarmaGridUtils.showCell(tblListaTransferencias, VariablesInventario.vPos, VariablesInventario.vPos);
                //tblListaTransferencias.repaint();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F6) {
            filtrar();
        } else if (e.getKeyCode() == KeyEvent.VK_F7) {
            quitarFiltro();
        } else if (e.getKeyCode() == KeyEvent.VK_F8) {
            e.consume();
            if (FarmaVariables.vEconoFar_Matriz)
                FarmaUtility.showMessage(this, ConstantsPtoVenta.MENSAJE_MATRIZ, btnRelacionTransferencias);
            else
                funcionF8();
        } else if (e.getKeyCode() == KeyEvent.VK_F12) {
            opcion = 0;
            btnBuscar.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void funcionF8() {
        DlgTransferenciasPorConfirmar dlgTransferenciasPorConfirmar =
            new DlgTransferenciasPorConfirmar(myParentFrame, "", true);
        dlgTransferenciasPorConfirmar.setVisible(true);
        if (FarmaVariables.vAceptar) {
            cargaListaTransferencias();
            FarmaVariables.vAceptar = false;
        }
    }


    private void filtrar() {
        if (tblListaTransferencias.getRowCount() > 0) {
            VariablesInventario.vTipoNota = ConstantsPtoVenta.TIP_NOTA_SALIDA;
            VariablesInventario.vNomInHashtableGuias = ConstantsInventario.NOM_HASTABLE_CMBFILTRO_TRANSF;
            DlgFiltroGuias dlgFiltroGuias = new DlgFiltroGuias(myParentFrame, "", true);
            dlgFiltroGuias.setVisible(true);
            if (FarmaVariables.vAceptar) {
                filtro = VariablesInventario.vCodFiltro;
                cargaListaTransferencias();
                FarmaVariables.vAceptar = false;
            }
        }
    }

    private void quitarFiltro() {
        filtro = "%";
        cargaListaTransferencias();
    }

    /**
     * @AUTHOR JCORTEZ
     * @SINCE 28.10.09
     * */
    private boolean validaGuiasEmitidas(String numTransf) {

        boolean valor = false;
        String ind = "";

        String[] val;

        log.debug(":JCORTEZ:::::Confirmando Guias generadas e impresas::::" + numTransf);
        try {

            ind = DBInventario.validaGuiasTransf(numTransf); //si existe guias

            log.debug(":JCORTEZ:::ind:::" + ind);
            val = ind.split("Ã");
            log.debug(":JCORTEZ:::val:::" + val);

            if (val[0].trim().equalsIgnoreCase("S"))
                valor = true;
            else
                valor = false;

            CodOrigenTranf = val[1].trim(); //local origen
            log.debug(":JCORTEZ:::CodOrigenTranf:::" + CodOrigenTranf);
        } catch (SQLException e) {
            FarmaUtility.liberarTransaccion();
            valor = false;

            if (e.getErrorCode() == 20101) {
                FarmaUtility.showMessage(this,
                                         "NO SE PUEDE CONFIRMAR LA TRANSFERENCIA. EXISTEN GUIAS SIN IMPRIMIR. VERIFIQUE!!!",
                                         tblListaTransferencias);
            } else if (e.getErrorCode() == 20102) {
                FarmaUtility.showMessage(this, "LA TRANSFERENCIA NO HA GENERADO GUIAS. VERIFIQUE!!!",
                                         tblListaTransferencias);
            } else {
                log.error("", e);
                FarmaUtility.showMessage(this, "Ha ocurrido un error al confirmar.\n" +
                        e, tblListaTransferencias);
            }
        }

        return valor;
    }

    private void confirmaTransferencia() {
        if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_SUPERVISOR_VENTAS)) {

            try {
                //actualizar a estado confirmado el pedido de transferencia
                String numTransf = VariablesInventario.vNumNotaEs.trim();
                String codLocalDestino = VariablesInventario.vCodDestino_Transf;
                String tipoOrigenTransf = VariablesInventario.vTipoDestino_Transf;
                log.debug("tipoOrigenTransf:" + tipoOrigenTransf + "***");
                log.debug("codLocalDestino:" + codLocalDestino + "***");
                log.debug("numTransf:" + numTransf + "***");
                log.debug("VariablesInventario.vCodDestino_Transf:" + VariablesInventario.vCodDestino_Transf);

                DBInventario.grabaInicioFinConfirmacionTransferencia(numTransf, "I", "N");
                DBInventario.confirmarTransferencia(numTransf);
                DBInventario.grabaInicioFinConfirmacionTransferencia(numTransf, "F", "N");

                FarmaUtility.aceptarTransaccion();

                if (tipoOrigenTransf.equals("01")) { //si es TIPO LOCAL
                    log.debug("verificando si hay linea con matriz");
                    VariablesInventario.vIndLineaMatriz =
                            FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ,
                                                           FarmaConstants.INDICADOR_S);
                    log.debug("VariablesInventario.vIndLineaMatriz:" + VariablesInventario.vIndLineaMatriz);
                    //si hay linea con matriz, se intentara realizar la trasnferencia a matriz y local destino.
                    //si ocurriera algun error, se realizara solo la confirmacion en local origen
                    if (VariablesInventario.vIndLineaMatriz.equals(FarmaConstants.INDICADOR_S)) {
                        log.debug("tratando de realizar la transferencia a local destino y matriz con estado L");
                        DBInventario.grabaInicioFinConfirmacionTransferencia(numTransf, "I",
                                                                             "S"); //JCHAVEZ 10122009 graba inicio de confimacion remota de transferencia de local origen a matriz

                        //kmoncada 09.06.2014 try-catch para evitar bloqueo de la tablas de transferencia
                        //si hay linea con matriz, se intentara realizar la trasnferencia a matriz y local destino.
                        //si ocurriera algun error, se realizara solo la confirmacion en local origen
                        String resultado = "N";
                        try {
                            resultado =
                                    DBInventario.realizarTransfDestino(numTransf, codLocalDestino, FarmaConstants.INDICADOR_N).trim();
                            log.debug("despues de invocar a matriz RESPUESTA:" + resultado);
                            if (resultado.equals(FarmaConstants.INDICADOR_S)) {
                                //si la respuesta es afirmativa S, realiza el commit remoto
                                FarmaUtility.aceptarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                      FarmaConstants.INDICADOR_S);
                                log.debug("actualizando el estado de la transferencia en local");
                                DBInventario.actualizarEstadoTransf(numTransf, "L");
                            }
                        } catch (Exception e) {
                            log.error("", e);
                            FarmaUtility.liberarTransaccionRemota(FarmaConstants.CONECTION_MATRIZ,
                                                                  FarmaConstants.INDICADOR_S);
                        } finally {
                            log.debug("cerrando toda conexion remota");
                            FarmaConnectionRemoto.closeConnection();
                        }
                        //JCHAVEZ 10122009 graba fin de confimacion remota de transferencia de local origen a matriz
                        DBInventario.grabaInicioFinConfirmacionTransferencia(numTransf, "F", "S");
                        FarmaUtility.aceptarTransaccion();

                    }
                }
                FarmaUtility.showMessage(this, "Transferencia Confirmada.", btnRelacionTransferencias);
                FarmaUtility.moveFocus(tblListaTransferencias);
                //cerrarVentana(true);
            } catch (SQLException e) {
                FarmaUtility.liberarTransaccion();
                log.error("", e);
                FarmaUtility.showMessage(this, "Ha ocurrido un error al confirmar. Transferencia por confirmar\n" +
                        e, tblListaTransferencias);
            }
        } else {
            FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción", null);
        }
    }
    
    private void btnFechaDe_ActionPerformed(ActionEvent e) {
            FarmaUtility.moveFocus(txtFechaInicio);
        }

        private void btnHasta_ActionPerformed(ActionEvent e) {
            FarmaUtility.moveFocus(txtFechaFin);
        }
        
        private void txtFechaIni_keyTyped(KeyEvent e) {
            FarmaUtility.admitirDigitos(txtFechaInicio, e);
        }

        private void txtFechaFin_KeyTyped(KeyEvent e) {
            FarmaUtility.admitirDigitos(txtFechaFin, e);
        }
            
        private void buscarPorFechas() {
            if(validarCampos()){
                String fechaInicio=txtFechaInicio.getText();
                String fechaFin=txtFechaFin.getText();
                try {
                    DBInventario.cargaListaTransferencias_rangoFechas(tableModel,fechaInicio,fechaFin);
                    lblCantTransfereniasT.setText("" + tblListaTransferencias.getRowCount());
                    FarmaUtility.ordenar(tblListaTransferencias, tableModel, 0, FarmaConstants.ORDEN_DESCENDENTE);
                } catch (SQLException sql) {
                    log.error("", sql);
                    FarmaUtility.showMessage(this, "Ocurrió un error al listar las transferencias: \n" +
                            sql.getMessage(), tblListaTransferencias);
                }
            }
        }
        int opcion=0;
        private void btnBuscar_ActionPerformed(ActionEvent e) {
            if (opcion==1)
                buscarPorFechas();
            else if (opcion==2) 
                buscarxTipoTransaccion();
            else
                cargaListaTransferencias();
            FarmaUtility.moveFocusJTable(tblListaTransferencias);
        }

        private void initCombo() {
            ArrayList parametros = new ArrayList();
            FarmaLoadCVL.loadCVLFromSP(cmbTipoTransferencia, ConstantsInventario.NOM_HASHTABLE_CMBTIPO_TRANSF,
                                       "PTOVENTA_INV.INV_GET_TIPO_TRANSFERENCIA", parametros, false);
            parametros = null;
        }

        private void btnTipTransfer_ActionPerformed(ActionEvent e) {
            FarmaUtility.moveFocus(cmbTipoTransferencia);
        }

        private void cmbTipoTransferrencia_KeyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                String txtTipo=FarmaLoadCVL.getCVLCode(ConstantsInventario.NOM_HASHTABLE_CMBTIPO_TRANSF, 
                                                       cmbTipoTransferencia.getSelectedIndex()).trim();
                if(txtTipo!=null && !txtTipo.equalsIgnoreCase("")){
                    opcion=2;
                    btnBuscar.doClick();
                }else{
                    FarmaUtility.showMessage(this, "ERROR: Seleccione un tipo de tranferencia para realizar la busqueda", null);
                }
                break;
            /*
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                FarmaUtility.moveFocusJTable(tblListaTransferencias);
                break;
            */
            default:
                chkKeyPressed(e);
            }
        }

        private void buscarxTipoTransaccion() {
            try {
                String codTipoTrans=FarmaLoadCVL.getCVLCode(ConstantsInventario.NOM_HASHTABLE_CMBTIPO_TRANSF, 
                                                       cmbTipoTransferencia.getSelectedIndex()).trim();
                
                DBInventario.cargaListaTransferencias_TipoTranss(tableModel,codTipoTrans);
                lblCantTransfereniasT.setText("" + tblListaTransferencias.getRowCount());
                FarmaUtility.ordenar(tblListaTransferencias, tableModel, 0, FarmaConstants.ORDEN_DESCENDENTE);
            } catch (SQLException sql) {
                log.error("", sql);
                FarmaUtility.showMessage(this, "Ocurrió un error al listar las transferencias: \n" +
                        sql.getMessage(), tblListaTransferencias);
            }
        }
}
