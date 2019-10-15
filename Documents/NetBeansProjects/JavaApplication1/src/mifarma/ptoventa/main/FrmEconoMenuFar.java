package mifarma.ptoventa.main;


import com.gs.mifarma.FarmaMenu.Util.FMenu;
import com.gs.mifarma.FarmaMenu.Util.UtilMenu;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.administracion.DlgMovimientosCaja;
import mifarma.ptoventa.administracion.DlgProcesaViajero;
import mifarma.ptoventa.administracion.cajas.DlgListaCajas;
import mifarma.ptoventa.administracion.fondoSencillo.DlgListadoCajeros;
import mifarma.ptoventa.administracion.fondoSencillo.reference.UtilityFondoSencillo;
import mifarma.ptoventa.administracion.impresoras.DlgListaIPSImpresora;
import mifarma.ptoventa.administracion.impresoras.DlgListaImpresoraTermCreaMod;
import mifarma.ptoventa.administracion.impresoras.DlgListaImpresoras;
import mifarma.ptoventa.administracion.mantenimiento.DlgControlHoras;
import mifarma.ptoventa.administracion.mantenimiento.DlgListaPresupuestoVentas;
import mifarma.ptoventa.administracion.mantenimiento.DlgParametros;
import mifarma.ptoventa.administracion.otros.DlgListaProbisa;
import mifarma.ptoventa.administracion.producto.DlgPrecioProdCambiados;
import mifarma.ptoventa.administracion.producto.DlgProdImpresion;
import mifarma.ptoventa.administracion.roles.DlgListaRolesTmp;
import mifarma.ptoventa.administracion.usuarios.DlgBuscaTrabajadorLocal;
import mifarma.ptoventa.administracion.usuarios.DlgCambioClave;
import mifarma.ptoventa.administracion.usuarios.DlgListaUsuarios;
import mifarma.ptoventa.caja.DlgAnularPedido;
import mifarma.ptoventa.caja.DlgConfiguracionComprobante;
import mifarma.ptoventa.caja.DlgControlSobres;
import mifarma.ptoventa.caja.DlgDatosTarjetaPinpad;
import mifarma.ptoventa.caja.DlgFormaPago;
import mifarma.ptoventa.caja.DlgListaRemito;
import mifarma.ptoventa.caja.DlgListaTicketsAnulados;
import mifarma.ptoventa.caja.DlgMovCaja;
import mifarma.ptoventa.caja.DlgPedidosPendientesImpresion;
import mifarma.ptoventa.caja.DlgPruebaImpresora;
import mifarma.ptoventa.caja.DlgVerificacionComprobantes;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.ce.DlgCierreCajaTurno;
import mifarma.ptoventa.ce.DlgHistoricoCierreDia;
import mifarma.ptoventa.ce.DlgReciboPagoSencillo;
import mifarma.ptoventa.ce.DlgValidaAperturaCaja;
import mifarma.ptoventa.ce.reference.ConstantsCajaElectronica;
import mifarma.ptoventa.ce.reference.DBCajaElectronica;
import mifarma.ptoventa.centromedico.DlgADMBuscarComprobantePago;
import mifarma.ptoventa.centromedico.DlgADMListaPacientes;
import mifarma.ptoventa.centromedico.DlgADMTrazabilidad;
import mifarma.ptoventa.centromedico.DlgListaEspera;
import mifarma.ptoventa.centromedico.DlgLoginMedico;
import mifarma.ptoventa.centromedico.TipoAtencionCM;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.cliente.DlgBuscaClienteJuridico;
import mifarma.ptoventa.cliente.reference.VariablesCliente;
import mifarma.ptoventa.controlAsistencia.DlgControlIngreso;
import mifarma.ptoventa.controlAsistencia.DlgHistoricoTemperatura;
import mifarma.ptoventa.controlAsistencia.reference.ConstantsControlAsistencia;
import mifarma.ptoventa.controlAsistencia.reference.UtilityControlAsistencia;
import mifarma.ptoventa.convalidado.DlgConvalidado;
import mifarma.ptoventa.cotizarPrecios.DlgCotizacionPrecioCabecera;
import mifarma.ptoventa.cotizarPrecios.DlgSolicitudCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.delivery.DlgUltimosPedidos;
import mifarma.ptoventa.fidelizacion.DlgFidelizacionBuscarTarjetas;
import mifarma.ptoventa.fraccion.DlgSolicitudFraccion;
import mifarma.ptoventa.fraccion.reference.UtilityFraccion;
import mifarma.ptoventa.inventario.DlgAjustesporFecha;
import mifarma.ptoventa.inventario.DlgDevolucionesLista;
import mifarma.ptoventa.inventario.DlgDevolucionesLista_02;
import mifarma.ptoventa.inventario.DlgGuiasIngresosRecibidas;
import mifarma.ptoventa.inventario.DlgGuiasRemision;
import mifarma.ptoventa.inventario.DlgKardex;
import mifarma.ptoventa.inventario.DlgKardexInsumos;
import mifarma.ptoventa.inventario.DlgListaPedidosEspeciales;
import mifarma.ptoventa.inventario.DlgListadoGuias;
import mifarma.ptoventa.inventario.DlgMercaderiaDirectaBuscar;
import mifarma.ptoventa.inventario.DlgPedidoReposicionAdicionalNuevo;
import mifarma.ptoventa.inventario.DlgPedidoReposicionRealizados;
import mifarma.ptoventa.inventario.DlgRecepcionProductosGuias;
import mifarma.ptoventa.inventario.DlgTransferenciasLocal;
import mifarma.ptoventa.inventario.DlgTransferenciasRealizadas;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.inventario.transfDelivery.DlgListaTransfPendientes;
import mifarma.ptoventa.inventariociclico.DlgInicioInvCiclico;
import mifarma.ptoventa.inventariociclico.DlgListaTomasInventarioCiclico;
import mifarma.ptoventa.inventariodiario.DlgListaDiferenciasToma;
import mifarma.ptoventa.inventariodiario.DlgListaTomasInventarioDiario;
import mifarma.ptoventa.otros.DlgAcercaDe;
import mifarma.ptoventa.otros.DlgListaMedidaPresion;
import mifarma.ptoventa.pinpad.DlgCierrePinpad;
import mifarma.ptoventa.pinpad.DlgEleccionTranAnularPinpad;
import mifarma.ptoventa.pinpad.DlgListadoPedidoPinpad;
import mifarma.ptoventa.pinpad.DlgReimpresionLotePinpad;
import mifarma.ptoventa.pinpad.DlgReimpresionPinpad;
import mifarma.ptoventa.pinpad.DlgReporteDetalladoPinpad;
import mifarma.ptoventa.pinpad.DlgReporteTotalPinpad;
import mifarma.ptoventa.pinpad.DlgTransaccionesPinpad;
import mifarma.ptoventa.pinpad.visa.VariablesPinpad;
import mifarma.ptoventa.psicotropicos.DlgReportePsicotropicos;
import mifarma.ptoventa.puntos.DlgConsultaSaldo;
import mifarma.ptoventa.puntos.DlgRecuperarPuntos;
import mifarma.ptoventa.puntos.DlgVerificaDocRedencionBonifica;
import mifarma.ptoventa.puntos.reference.ConstantsPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.recaudacion.DlgRecaudacionCitibank;
import mifarma.ptoventa.recaudacion.DlgRecaudacionClaro;
import mifarma.ptoventa.recaudacion.DlgRecaudacionCmr;
import mifarma.ptoventa.recaudacion.DlgRecaudacionFinanciero;
import mifarma.ptoventa.recaudacion.DlgRecaudacionPrestamosCitibank;
import mifarma.ptoventa.recaudacion.DlgRecaudacionPrestamosIncasur;
import mifarma.ptoventa.recaudacion.DlgRecaudacionPrestamosRaiz;
import mifarma.ptoventa.recaudacion.DlgRecaudacionRipley;
import mifarma.ptoventa.recaudacion.DlgRecaudacionesRealizadas;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recepcionCiega.DlgHistoricoRecepcion;
import mifarma.ptoventa.recepcionCiega.DlgListaTransportistas;
import mifarma.ptoventa.recepcionCiega.DlgRptBandejas;
import mifarma.ptoventa.recetario.DlgListaRecetarios;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.reportes.DlgDetalleVentas;
import mifarma.ptoventa.reportes.DlgProdSinVtaNDias;
import mifarma.ptoventa.reportes.DlgProductoFaltaCero;
import mifarma.ptoventa.reportes.DlgProductosABC;
import mifarma.ptoventa.reportes.DlgRegistroVentas;
import mifarma.ptoventa.reportes.DlgRegistroVentasConvenio;
import mifarma.ptoventa.reportes.DlgRegistroVentasDelivery;
import mifarma.ptoventa.reportes.DlgReporteGigante;
import mifarma.ptoventa.reportes.DlgReporteGuia;
import mifarma.ptoventa.reportes.DlgUnidVtaLocal;
import mifarma.ptoventa.reportes.DlgVentasDiaMes;
import mifarma.ptoventa.reportes.DlgVentasPorHora;
import mifarma.ptoventa.reportes.DlgVentasPorProducto;
import mifarma.ptoventa.reportes.DlgVentasPorVendedor;
import mifarma.ptoventa.reportes.DlgVentasResumenPorDia;
import mifarma.ptoventa.reportes.reference.ConstantsReporte;
import mifarma.ptoventa.reportes.reference.FacadeReporte;
import mifarma.ptoventa.reportes.reference.VariablesReporte;
import mifarma.ptoventa.stocknegativo.DlgListadoStockNegativo;
import mifarma.ptoventa.test_desa.DlgAcceso;
import mifarma.ptoventa.tomainventario.DlgInicioToma;
import mifarma.ptoventa.tomainventario.DlgListaHistoricoTomas;
import mifarma.ptoventa.tomainventario.DlgListaLaboratorios;
import mifarma.ptoventa.tomainventario.DlgListaTomasInventario;
import mifarma.ptoventa.ventas.DlgConsultarRecargaCorrelativo_AS;
import mifarma.ptoventa.ventas.DlgListaProdDIGEMID;
import mifarma.ptoventa.ventas.DlgResumenPedido;
import mifarma.ptoventa.ventas.DlgResumenPedidoGratuito;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FrmEconoMenuFar extends JFrame {

    private static final Logger log = LoggerFactory.getLogger(FrmEconoFar.class);

    public static String tituloBaseFrame = "FarmaVenta";
    int ind = 0;
    JLabel statusBar = new JLabel();
    private JPanel pnlEconoFar = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JLabel lblLogo = new JLabel();
    private JButtonLabel btnRevertir = new JButtonLabel();
    private JTextFieldSanSerif txtRevertir = new JTextFieldSanSerif();
    private JLabel lblMensaje = new JLabel("EN PRUEBAS", JLabel.CENTER);
    private JMenuBar mnuPtoVenta = new JMenuBar();
    
    public int TIPO_COTIZA_COMPRAR = 1;
    public int TIPO_COTIZA_COTIZAR = 2;
    public int TIPO_COTIZA_SIN_SOLICITUD = 3;

    public FrmEconoMenuFar() {
        try {
            //cargaVariablesBD();
            tituloBaseFrame = FarmaVariables.vNombreModulo + " " + FarmaVariables.vVersion + " - " + FarmaVariables.vCompilacion;
            jbInit();
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            initialize();
            initMenus();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("", e);
        }
    }
    
    private void initMenus() {
        ArrayList<ArrayList> listaMenu=new ArrayList<ArrayList>();
        String ipLocal=FarmaUtility.getHostAddress();
        if(ipLocal.indexOf("10.18.")!=-1){
            listaMenu=UtilMenu.Cargar_MenuDesarrollo("FARMAVENTA");
        }else{
            listaMenu=UtilMenu.Cargar_MenuFarmaventa("FARMAVENTA");
        }
        
        FMenu menuBar=new FMenu(listaMenu,"AcctionPerformed");
        menuBar.setFont(new Font("SansSerif", 0, 11));
        this.setJMenuBar(menuBar);        
    }
    
    private void cargaVariablesBD() {
        //FarmaVariables.vUsuarioBD = ConstantsPtoVenta.USUARIO_BD;
        //FarmaVariables.vClaveBD = ConstantsPtoVenta.CLAVE_BD;
        //FarmaVariables.vSID = ConstantsPtoVenta.SID;
        FarmaVariables.vPUERTO = ConstantsPtoVenta.PUERTO;
        try {
            (new FacadeRecaudacion()).obtenerTipoCambio();


        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener Tipo de Cambio del Dia . \n " + sql.getMessage(), null);
        }
    }

    private void jbInit() throws Exception {

        this.getContentPane().setLayout(borderLayout1);

        this.setSize(new Dimension(800, 600));
        this.setFont(new Font("SansSerif", 0, 11));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                salir(e);
            }

            public void windowClosed(WindowEvent e) {
                this_windowClosed(e);
            }
        });
        
        pnlEconoFar.setLayout(null);
        pnlEconoFar.setFont(new Font("SansSerif", 0, 11));
        pnlEconoFar.setBackground(Color.white);

        statusBar.setText("Copyright (c) 2005 - 2015");
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        
        this.getContentPane().add(pnlEconoFar, BorderLayout.CENTER);

        ConstantesUtil.simboloSoles_DB();
        ConstantesUtil.describeSoles_DB();
    }

    private void this_windowOpened(WindowEvent e) {
        DlgProcesar dlgProcesar = new DlgProcesar(this, "", true);
        dlgProcesar.mostrar();
    }

    private void salir(WindowEvent e) {
        salirSistema();
    }

    private void this_windowClosed(WindowEvent e) {
        UtilityVentas.eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
    }

    private void salirSistema() {
        UtilityVentas.eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
        if (FarmaVariables.vEconoFar_Matriz)
            this.dispose();
        else
            System.exit(0);
    }
    
    private void initialize() {
       
        //FarmaCnxPool.loadListaProductos();
        //ERIOS 2.3.3 Carga de listado de productos 
        /*VariablesVentas.tableModelListaGlobalProductos =
                new FarmaTableModel(ConstantsVentas.columnsListaProdPrecios,
                                    ConstantsVentas.defaultValuesListaProdPrecios, 0);
       SubProcesos subproceso1 = new SubProcesos("GET_PROD_VENTA");
       subproceso1.start();
       while (subproceso1.isAlive()) {
           ;
       }*/
                       
        //eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
        cargaIcono();
        UtilityPtoVenta.muestraUser(this);
        inicializa();
        
        //INICIA LAS CLASES Y VARIABLES DE FARMA PUNTOS
        if(UtilityPuntos.cargaFarmaPuntos())
        {
            cargaDireccionFiscal();
            obtieneIndDireMat();
            //ERIOS 12.09.2013 Obtiene indicador direccion local
            obtieneIndDireLocal();
            //ERIOS 12.09.2013 Obtiene indicador de registro de venta restringida
            obtieneIndRegistroVentaRestringida();
        }
        else
        {   
            FarmaUtility.showMessage(this, "Error al iniciar FarmaPuntos, se va cerrar el Sistema.\n" +
                                           "Por favor de comunicarse con Mesa de Ayuda", null);
            salirSistema();
        }
    }
    
    public static void obtieneIndRegistroVentaRestringida() {
        try {
            VariablesPtoVenta.vIndRegistroVentaRestringida = DBPtoVenta.obtieneIndRegistroVentaRestringida();
        } catch (SQLException sql) {
            log.error("", sql);
        }
        log.debug("VariablesPtoVenta.vIndRegistroVentaRestringida=" + VariablesPtoVenta.vIndRegistroVentaRestringida);
    }
    
    public void obtieneIndDireLocal() {
        try {
            VariablesPtoVenta.vIndDirLocal = DBPtoVenta.obtieneIndDirLocal();
        } catch (SQLException sql) {
            log.error("", sql);
        }
        log.debug("VariablesPtoVenta.vIndDirLocal=" + VariablesPtoVenta.vIndDirLocal);
    }
    
    private void obtieneIndDireMat() {
        try {
            VariablesPtoVenta.vIndDirMatriz = DBPtoVenta.obtieneIndDirMatriz();
        } catch (SQLException sql) {
            log.error("", sql);
        }
    }
    
    private void cargaDireccionFiscal() {
        try {
            ArrayList lstDirecFiscal = DBPtoVenta.obtieneDireccionMatriz();
            VariablesPtoVenta.vDireccionMatriz = FarmaUtility.getValueFieldArrayList(lstDirecFiscal, 0, 0);
            VariablesPtoVenta.vDireccionCortaMatriz = FarmaUtility.getValueFieldArrayList(lstDirecFiscal, 0, 1);
        } catch (SQLException sql) {
            log.warn("Error al obtener la dirección de la Dirección Fiscal." + sql.getMessage());
        }
    }
    
  
    
    private void cargaIcono() {
        //LTERRAZOS 01.03.2013 Se llama a la tabla PBL_CIA para mostrar la ruta
        String strRutaJpg = "";
        try {
            strRutaJpg = DBPtoVenta.obtieneRutaImagen();
        } catch (Exception e) {
            log.error("", e);
            log.debug("Problemas al carga el icono");
            strRutaJpg = "Mifarma.jpg";
        }
        ImageIcon imageIcono =
            new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/Icono" + strRutaJpg));
        this.setIconImage(imageIcono.getImage());
    }
    
    private void recuperaStock() {

        try {
            // RECUPERANDO STOCK COMPROMETIDO
            DBPtoVenta.ejecutaRespaldoStock("", "", ConstantsPtoVenta.TIP_OPERACION_RESPALDO_EJECUTAR, 0, 0, "");
            log.info("RECUPERO STOCK COMPROMETIDO DESDE RESPALDO");
            FarmaUtility.aceptarTransaccion();
        } catch (SQLException sqlException) {
            FarmaUtility.liberarTransaccion();
            log.error("", sqlException);
            FarmaUtility.showMessage(this,
                                     "Error al recuperar Stock de Respaldo.\nPonganse en contacto con el area de Sistemas",
                                     null);
            salirSistema();
        }

    }
    
   
    private void muestraUser() {

        String addon = " Usu.Actual : ";
        addon = addon + FarmaVariables.vIdUsu;
        this.setTitle(tituloBaseFrame + " /  Local : " + FarmaVariables.vDescCortaLocal + " / " + addon + " /  IP : " +
                      FarmaVariables.vIpPc);
    }
    
    private boolean llamarDlgLogin() {
        String mensajeLogin = "Acceso " + tituloBaseFrame;
        DlgLogin dlgLogin = new DlgLogin(this, mensajeLogin, true);
        FarmaVariables.dlgLogin = dlgLogin;
        dlgLogin.setVisible(true);
        if (!FarmaVariables.vAceptar) {
            FarmaConnection.closeConnection();
            salirSistema();
        }
        boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(this,
                                                            ConstantsControlAsistencia.ID_ENTRADA);
        
        System.out.println("verificaRolUsuario - llamarDlgLogin");
        verificaRolUsuario();
        
        if (!flagCA) {
            llamarDlgLogin();
        }
        return FarmaVariables.vAceptar;
    }
    
    private void inicializa() {
        try {
            VariablesPtoVenta.vIndRecepCiega = DBPtoVenta.obtieneIndicadorTipoRecepcionAlmacen();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this,
                                     "Ocurrió un error al obtener el indicador del tipo de recepción de almacen : \n" +
                    sql.getMessage(), null);
        }
        
        //pruebas de punto de venta
        //if (validaEsOperador()){
        if (validaCantidadPruebasCompleta()) {
            lblMensaje.setVisible(true);
            pnlEconoFar.setBackground(Color.ORANGE);
            btnRevertir.setBackground(Color.ORANGE);
            txtRevertir.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            txtRevertir.setBackground(Color.ORANGE);
            btnRevertir.setForeground(Color.ORANGE);
        }
        //}
    }
    
    private boolean validaCantidadPruebasCompleta() {
        int cantidad = -1;
        try {
            cantidad = DBPtoVenta.obtieneCantidadPruebasCompletas();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al verificar inicio y fin de prueba. \n" +
                    sql.getMessage(), null);

        }
        if (cantidad == 1)
            return true;
        return false;
    }
    
    public void AcctionPerformed(String Opc) {

        /* En este ejemplo, estas son las claves de opciones terminales
         (esto es, aquellas que provocan acciones) definidas. Por
         supuesto si cambia el menú de opciones, será necesario
         alterar el contenido de este método, en consecuencia. */
        switch (Opc){
            case "0012": //Generar Pedido
                if (UtilityPtoVenta.getIndLoginCajUnicaVez()) {
                    DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                    dlgLogin.setRolUsuario(FarmaConstants.ROL_VENDEDOR);
                    dlgLogin.setVisible(true);
                    if (FarmaVariables.vAceptar) {
                        FarmaVariables.vAceptar = false;
                    } else
                        return;
                }
    
                generarPedido();
                llamarDlgLogin();
                break;
            case "0013"://Distribucion Gratuita
                distribucionGratuita();
                break;
            case "0014"://Delivery por atender
                DlgUltimosPedidos dlgVentaDelivery = new DlgUltimosPedidos(this, "", true);
                dlgVentaDelivery.setTipoVenta(ConstantsVentas.TIPO_PEDIDO_DELIVERY);
                dlgVentaDelivery.setVisible(true);
                llamarDlgLogin();
                break;
            case "0015"://Empresas
                DlgUltimosPedidos dlgVentaEmpresa = new DlgUltimosPedidos(this, "", true);
                dlgVentaEmpresa.setTipoVenta(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL);
                dlgVentaEmpresa.setVisible(true);
                llamarDlgLogin();
                break;
            case "0016"://Medida Presion
                DlgListaMedidaPresion dlgListaMedidaPresion = new DlgListaMedidaPresion(this, "", true);
                dlgListaMedidaPresion.setVisible(true);
                break;
            case "0018"://Cobro Pedido Inv. Diario
                DlgFidelizacionBuscarTarjetas dlgBuscar = new DlgFidelizacionBuscarTarjetas(this, "", true,false);
                dlgBuscar.setIsMantenimientoCliente(true);
                dlgBuscar.setVisible(true);
                break;
            case "0019"://Lista precios consumidor
                DlgListaProdDIGEMID objDIGEMID = new DlgListaProdDIGEMID(this, "", true); //ASOSA 04.02.2010
                objDIGEMID.setVisible(true);
                break;
            case "0020"://Correlativo
                DlgConsultarRecargaCorrelativo_AS dlgRecargaCorrelativo =
                    new DlgConsultarRecargaCorrelativo_AS(this, "Consulta de recarga por correlativo", true);
                dlgRecargaCorrelativo.setVisible(true);
                break;
            case "0022"://Configurar caja
                mnuCaja_ConfigurarCaja_actionPerformed();
                break;
            case "0023"://Cobrar Pedido Pendiente
                DlgFormaPago dlgFormaPago = new DlgFormaPago(this, "", true);
                dlgFormaPago.setVisible(true);
                if (!FarmaVariables.vAceptar) {
                    verificaRolUsuario();
                }
                llamarDlgLogin(); 
                break;
            case "0025"://Correccion de comprobantes
                DlgVerificacionComprobantes dlgVerificacionComprobantes = new DlgVerificacionComprobantes(this, "", true);
                dlgVerificacionComprobantes.setVisible(true);
                break;
            case "0026"://Reimpresion de pedido
                DlgPedidosPendientesImpresion dlgPedidosPendientesImpresion =
                    new DlgPedidosPendientesImpresion(this, "", true);
                dlgPedidosPendientesImpresion.setVisible(true);
                break;
            case "0027"://Regularizacion de venta manual
                mnuCaja_RegulaVtaManual_actionPerformed();
                break;
            case "0029"://Reimpresion de tickets anulados
                DlgListaTicketsAnulados dlgListaTicketsAnulados = new DlgListaTicketsAnulados(this, "", true);
                dlgListaTicketsAnulados.setVisible(true);
                break;
            case "0030"://Control sobres parciales
                DlgControlSobres dlgcontrol = new DlgControlSobres(this, "", true);
                dlgcontrol.setVisible(true);
                break;
            case "0032"://Pedidos pagados con Pinpad
                DlgListadoPedidoPinpad dlgListadoPedidoPinpad = new DlgListadoPedidoPinpad(this, "", true);
                dlgListadoPedidoPinpad.setVisible(true);
                break;
            case "0033"://Aperturar Caja
                mnuCaja_AperturarCaja_actionPerformed();
                break;
            case "0034"://Cerrar caja
                mnuCaja_CerrarCaja_actionPerformed();
                break;
            case "0035"://Por cambio de producto
                VariablesPtoVenta.vIndAnulacionCambioProducto = FarmaConstants.INDICADOR_S;
                DlgAnularPedido dlgAnularCambioProd = new DlgAnularPedido(this, "", true);
                dlgAnularCambioProd.setVisible(true);
                break;
            case "0036"://Pedido completo
                VariablesPtoVenta.vIndAnulacionCambioProducto = FarmaConstants.INDICADOR_N;
                DlgAnularPedido dlgAnularPedido = new DlgAnularPedido(this, "", true);
                dlgAnularPedido.setVisible(true);
                break;
            case "0037"://Prueba Impresora
                DlgPruebaImpresora dlgPruebaImpresora = new DlgPruebaImpresora(this, "", true);
                dlgPruebaImpresora.setVisible(true);
                break;
            case "0039"://Abrir Gabeta
                UtilityCaja.abrirGabeta(this, true);
                break;
            case "0040"://Servicios Visa
                DlgTransaccionesPinpad dlgServicioVisa = new DlgTransaccionesPinpad(this,"",true);
                dlgServicioVisa.setTipoOperacion(VariablesPinpad.OP_FINANCIERA);
                dlgServicioVisa.setVisible(true);
                break;
            case "0041"://Oper. No Financieras Visa
                DlgTransaccionesPinpad dlgPinpadVisa = new DlgTransaccionesPinpad(this, "", true);
                dlgPinpadVisa.setVisible(true);
                break;
            case "0042"://Cierre Mastercard
                DlgCierrePinpad dlgCierreMasterCard = new DlgCierrePinpad(this, "", true);
                dlgCierreMasterCard.setVisible(true);
                break;
            case "0043"://Reporte Detallado Mastercard
                DlgReporteDetalladoPinpad dlgReporteDetalladoPinpad = new DlgReporteDetalladoPinpad(this, "", true);
                dlgReporteDetalladoPinpad.setVisible(true);
                break;
            case "0044"://Reporte Total Mastercard
                DlgReporteTotalPinpad dlgReporteTotalPinpad = new DlgReporteTotalPinpad(this, "", true);
                dlgReporteTotalPinpad.setVisible(true);
                break;
            case "0045"://Reimpresión Voucher Mastercard
                DlgReimpresionPinpad dlgReimpresionPinpad = new DlgReimpresionPinpad(this, "", true);
                dlgReimpresionPinpad.setVisible(true);
                break;
            case "0046"://Anulación Transacción Pinpad
                DlgEleccionTranAnularPinpad dlgEleccionTranAnularPinpad = new DlgEleccionTranAnularPinpad(this, "", true);
                dlgEleccionTranAnularPinpad.setVisible(true);
                break;
            case "0047"://Reimpresión Lote Mastercard
                DlgReimpresionLotePinpad dlgReimpresionLotePinpad = new DlgReimpresionLotePinpad(this, "", true);
                dlgReimpresionLotePinpad.setVisible(true);
                break;
            case "0048"://CMR
                DlgRecaudacionCmr dlgRecaudaCmr = new DlgRecaudacionCmr(this, "", true);
                dlgRecaudaCmr.setVisible(true);
                break;
            case "0049"://Citibank
                DlgRecaudacionCitibank dlgRecaudCitibank = new DlgRecaudacionCitibank(this, "", true);
                dlgRecaudCitibank.setVisible(true);
                break;
            case "0050"://Claro
                DlgRecaudacionClaro dlgRecaudaClaro = new DlgRecaudacionClaro(this, "", true);
                dlgRecaudaClaro.setVisible(true);
                break;
            case "0051"://Prestamo Citibank
                DlgRecaudacionPrestamosCitibank dlgRecadaPrestaCitibank = new DlgRecaudacionPrestamosCitibank(this, "", true);
                dlgRecadaPrestaCitibank.setVisible(true);
                break;
            case "0052"://Ripley
                DlgRecaudacionRipley dlgRecaudadaRipley = new DlgRecaudacionRipley(this, "", true);
                dlgRecaudadaRipley.setVisible(true);
                break;
            case "0053"://Registro de pagos
                DlgRecaudacionesRealizadas dlgRecaudaRealizadas = new DlgRecaudacionesRealizadas(this, "", true);
                dlgRecaudaRealizadas.setTipoCobro(ConstantsRecaudacion.TIPO_COBRO_RECAUDACION);
                dlgRecaudaRealizadas.setVisible(true);
                break;
            case "0054"://Registro venta CMR
                DlgRecaudacionesRealizadas dlgRegVtaCMR = new DlgRecaudacionesRealizadas(this, "", true);
                dlgRegVtaCMR.setTipoCobro(ConstantsRecaudacion.TIPO_COBRO_VENTA_CMR);
                dlgRegVtaCMR.setVisible(true);
                break;
            case "0055"://Financiera Raiz
                DlgRecaudacionPrestamosRaiz dlgRecaudacionPrestamosRaiz = new DlgRecaudacionPrestamosRaiz(this, "", true);
                dlgRecaudacionPrestamosRaiz.setVisible(true);
                break;
            case "0056"://Caja Incasur
                DlgRecaudacionPrestamosIncasur dlgRecaudacionPrestamosIncasur = new DlgRecaudacionPrestamosIncasur(this, "", true);
                dlgRecaudacionPrestamosIncasur.setVisible(true);
                break;
            case "0057"://Diners Club y Banco Financiero
                DlgRecaudacionFinanciero dlgRecaudacionFinanciero = new DlgRecaudacionFinanciero(this, "", true);
                dlgRecaudacionFinanciero.setVisible(true);
                break;
            case "0058"://Guia de Ingreso
                DlgGuiasIngresosRecibidas dlgGuiasIngresosRecibidas = new DlgGuiasIngresosRecibidas(this, "", true);
                dlgGuiasIngresosRecibidas.setVisible(true);
                break;
            case "0059"://Kardex
                DlgKardex dlgKardex = new DlgKardex(this, "", true);
                dlgKardex.setTipo_reposicion(" "); //05.08.2014 todos los insumos
                //dlgKardex.cargaListaProductos();  //05.08.2014 lista los productos
                //dlgKardex.cargarNombre();         //08.08.2014 titulo de venta
                dlgKardex.setVisible(true);
                break;
            case "0063"://Correccion Guias
                DlgGuiasRemision dlgGuiasRemision = new DlgGuiasRemision(this, "", true);
                dlgGuiasRemision.setVisible(true);
                break;
            case "0064"://Ajuste por Fecha
                DlgAjustesporFecha dlgAjustesporFecha = new DlgAjustesporFecha(this, "", true);
                dlgAjustesporFecha.setVisible(true);
                break;
            case "0065"://Recepcion Locales
                DlgTransferenciasLocal dlgTransferenciasLocal = new DlgTransferenciasLocal(this, "", true);
                dlgTransferenciasLocal.setVisible(true);
                break;
            case "0067": //Stock Negativo
                DlgListadoStockNegativo dlgListadoStockNegativo = new DlgListadoStockNegativo(this, "", true);
                dlgListadoStockNegativo.setVisible(true);
                break;
            case "0068": //Productos de insumo
                DlgKardexInsumos dlgKardexInsumo = new DlgKardexInsumos(this, "", true);
                //dlgKardexInsumo.setTipo_reposicion(ConstantsCaja.TIPO_REPOSICION_INSUMOS);////05.08.2014
                //dlgKardexInsumo.cargaListaProductos();  //05.08.2014 lista los productos
                //dlgKardexInsumo.cargarNombre();         //08.08.2014 titulo de venta
                dlgKardexInsumo.setVisible(true);
                break;
            case "0069": //Registro de Mermas
                DlgConvalidado dlgConvalidado = new DlgConvalidado(this, "", true);
                dlgConvalidado.setVisible(true);
                break;
            case "0070"://Transferencia Local
                DlgTransferenciasRealizadas dlgTransferenciasRealizadas = new DlgTransferenciasRealizadas(this, "", true);
                dlgTransferenciasRealizadas.setVisible(true);
                break;
            case "0071"://Transferencia Delivery
                DlgListaTransfPendientes dlgListaTranfPendientes = new DlgListaTransfPendientes(this, "", true);
                dlgListaTranfPendientes.setVisible(true);
                break;
            case "0072"://Guias de Salida
                DlgListadoGuias dlgGuiasSalida = new DlgListadoGuias(this, "", true);
                dlgGuiasSalida.setVisible(true);
                break;
            case "0073"://Ingreso Transportista
                DlgListaTransportistas dlgListaTransp = new DlgListaTransportistas(this, "", true);
                dlgListaTransp.setVisible(true);
                break;
            case "0074"://Recepcion Ciega
                DlgHistoricoRecepcion dlgRecepcion = new DlgHistoricoRecepcion(this, "", true);
                dlgRecepcion.setVisible(true);
                break;
            case "0075": //Recepcion de Almacén
                DlgRecepcionProductosGuias dlgRecepcionProductosGuias = new DlgRecepcionProductosGuias(this, "", true);
                dlgRecepcionProductosGuias.setVisible(true);
                break;
            case "0076": //Bandejas por Devolver
                DlgRptBandejas dlgBandeja = new DlgRptBandejas(this, "",true);
                dlgBandeja.setVisible(true);
                break;
            case "0077"://Pedido Reposición
                DlgPedidoReposicionRealizados dlgPedidoReposicionRealizados =
                    new DlgPedidoReposicionRealizados(this, "", true);
                dlgPedidoReposicionRealizados.setVisible(true);
                break;
            case "0078"://PVM
                DlgPedidoReposicionAdicionalNuevo dlgPedidoAdicional = new DlgPedidoReposicionAdicionalNuevo(this, "", true);
                dlgPedidoAdicional.setVisible(true);
                break;
            case "0079"://Pedido Especial
                DlgListaPedidosEspeciales dlglistaPE = new DlgListaPedidosEspeciales(this, "", true,ConstantsInventario.TIP_PED_ESP_INS);
                dlglistaPE.setVisible(true);
                break;
            case "0080"://Pedido Especial Insumo
                DlgListaPedidosEspeciales dlglistaPEI = new DlgListaPedidosEspeciales(this, "", true,ConstantsInventario.TIP_PED_ESP_INS);
                dlglistaPEI.setVisible(true);
                break;
            case "0081"://Solicitar Fraccionamiento
                DlgSolicitudFraccion dlgFraccion = new DlgSolicitudFraccion(this, "", true);
                String menuActivo=UtilityFraccion.verificaActivacion_Menu();
                if(menuActivo.trim().equalsIgnoreCase("S")){
                    dlgFraccion.setVisible(true);
                }else{
                    FarmaUtility.showMessage(this,"Esta función aún no está activa ", null);
                }
                break;
            case "0082"://Recepcion
                DlgMercaderiaDirectaBuscar dlgMercaderiaDirectaBuscar = new DlgMercaderiaDirectaBuscar(this, "", true);
                dlgMercaderiaDirectaBuscar.setVisible(true);
                break;
            case "0083"://Devolucion
                DlgDevolucionesLista dlgListaDevoluciones = new DlgDevolucionesLista(this, "", true);
                dlgListaDevoluciones.setVisible(true);
                break;
            case "0084"://Devolucion X Sobrante
                DlgDevolucionesLista_02 dlgListaDevoluciones_02 = new DlgDevolucionesLista_02(this, "", true);
                dlgListaDevoluciones_02.setVisible(true);
                break;
            case "0088"://Historico
                DlgListaHistoricoTomas dlgListaHistoricoTomas = new DlgListaHistoricoTomas(this, "", true);
                dlgListaHistoricoTomas.setVisible(true);
                break;
            case "0089"://Items por laboratorio
                DlgListaLaboratorios dlgListaLaboratorios = new DlgListaLaboratorios(this, "", true);
                dlgListaLaboratorios.setVisible(true);
                break;
            case "0090"://Inicio
                DlgInicioToma dlgInicioToma = new DlgInicioToma(this, "", true);
                dlgInicioToma.setVisible(true);
                break;
            case "0091"://Toma
                DlgListaTomasInventario dlgListaTomasInventario = new DlgListaTomasInventario(this, "", true);
                dlgListaTomasInventario.setVisible(true);
                break;
            case "0092"://Inicio
                DlgInicioInvCiclico dlgInicioInvCiclico = new DlgInicioInvCiclico(this, "", true);
                dlgInicioInvCiclico.setVisible(true);
                break;
            case "0093"://Toma
                DlgListaTomasInventarioCiclico dlgListaTomasInventarioCiclico =
                    new DlgListaTomasInventarioCiclico(this, "", true);
                dlgListaTomasInventarioCiclico.setVisible(true);
                break;
            case "0094"://Toma
                DlgListaTomasInventarioDiario dlgListaTomasInventarioDiario =
                    new DlgListaTomasInventarioDiario(this, "", true);
                dlgListaTomasInventarioDiario.setVisible(true);
                break;
            case "0095"://Diferencia
                if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) || validarAsistAudit()) {
                    DlgListaDiferenciasToma dlgListaDiferenciasToma = new DlgListaDiferenciasToma(this, "", true);
                    dlgListaDiferenciasToma.setVisible(true);
                } else {
                    FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opción", null);
                }
                break;
            case "0099"://Regenerar Viajero
                DlgProcesaViajero dlgProcesaViajero = new DlgProcesaViajero(this, "", true);
                dlgProcesaViajero.setVisible(true);
                break;
            case "0100"://Control de Horas
                DlgControlHoras dlgControlHoras = new DlgControlHoras(this, "", true);
                dlgControlHoras.setVisible(true);
                break;
            case "0102"://Fondo de Sencillo
                if (UtilityFondoSencillo.indActivoFondo()) {
    
                    DlgListadoCajeros dlgListado = new DlgListadoCajeros(this, "", true);
                    dlgListado.setVisible(true);
                } else {
                    FarmaUtility.showMessage(this, "Aún no se encuentra habilitada está opción en su Local.", null);
                }
                break;
            case "0104"://Presupuesto de Ventas
                DlgListaPresupuestoVentas dlgListaPresupuestoVentas = new DlgListaPresupuestoVentas(this,"",true);
                dlgListaPresupuestoVentas.setVisible(true);
                break;
            case "0105"://Cambiar de Usuario
                muestraLoginCambioUser();    
                this.repaint();
                break;
            case "0106"://Modificar mi clave
                DlgCambioClave dlgCambioClave = new DlgCambioClave(this, "", true);
                dlgCambioClave.setVisible(true);
                break;
            case "0107"://Usuarios
                DlgListaUsuarios dlgListaUsuarios = new DlgListaUsuarios(this, "", true);
                dlgListaUsuarios.setVisible(true);
                break;
            case "0108"://Cajas
                DlgListaCajas dlgListaCajas = new DlgListaCajas(this, "", true);
                dlgListaCajas.setVisible(true);
                break;
            case "0109"://Impresoras
                DlgListaImpresoras dlgListaImpresoras = new DlgListaImpresoras(this, "", true);
                dlgListaImpresoras.setVisible(true);
                break;
            case "0110"://Clientes
                DlgBuscaClienteJuridico dlgBuscaClienteJuridico = new DlgBuscaClienteJuridico(this, "", true);
                VariablesCliente.vIndicadorCargaCliente = FarmaConstants.INDICADOR_N;
                dlgBuscaClienteJuridico.setVisible(true);
                break;
            case "0111"://Parametros
                DlgParametros dlgParametros = new DlgParametros(this, "", true);
                dlgParametros.setVisible(true);
                break;
            case "0112"://Carné Sanidad
                DlgBuscaTrabajadorLocal dlgBuscaTrab = new DlgBuscaTrabajadorLocal(this, "", true);
                dlgBuscaTrab.setTitle("Lista de Trabajadores Local");
                dlgBuscaTrab.setVisible(true);
                break;
            case "0113"://Maquina - IP
                DlgListaIPSImpresora DlgIP = new DlgListaIPSImpresora(this, "", true);
                DlgIP.setVisible(true);
                break;
            case "0114"://Impresoras Térmicas
                DlgListaImpresoraTermCreaMod dlgLstImprTermCreaMod = new DlgListaImpresoraTermCreaMod(this, "", true);
                dlgLstImprTermCreaMod.setVisible(true);
                break;
            case "0115"://Administrador Temporal
                DlgListaRolesTmp dlgLstRolTmp = new DlgListaRolesTmp(this, "", true);
                dlgLstRolTmp.setVisible(true);
                break;
            case "0116"://Cliente Fidelizado
                //ASOSA - 20/04/2015 - 
                /*DlgFidelizacionIngresoDoc dlgFidelizacionIngresoDoc = new DlgFidelizacionIngresoDoc(this, "", true);
                dlgFidelizacionIngresoDoc.setVisible(true);*/
                DlgFidelizacionBuscarTarjetas dlgClienteFidelizado = new DlgFidelizacionBuscarTarjetas(this, "", true,false);
                dlgClienteFidelizado.setIsMantenimientoCliente(true);
                dlgClienteFidelizado.setVisible(true);
                break;
            case "0117"://Registrar Ventas
                VariablesPtoVenta.vTipOpMovCaja = ConstantsPtoVenta.TIP_OPERACION_MOV_CAJA_REGISTRAR_VENTA;
                DlgMovimientosCaja dlgRegVentas = new DlgMovimientosCaja(this, "", true);
                dlgRegVentas.setVisible(true);
                break;
            case "0118"://Consultar Movimientos
                VariablesPtoVenta.vTipOpMovCaja = ConstantsPtoVenta.TIP_OPERACION_MOV_CAJA_CONSULTA;
                DlgMovimientosCaja dlgMovimientosCaja = new DlgMovimientosCaja(this, "", true);
                dlgMovimientosCaja.setVisible(true);
                break;
            case "0119"://Mant. PROBISA
                DlgListaProbisa dlgListaProbisa = new DlgListaProbisa(this, "", true);
                dlgListaProbisa.setVisible(true);
                break;
            case "0120"://Precio Cambiado
                DlgPrecioProdCambiados dlgPrecioCambiad = new DlgPrecioProdCambiados(this, "", true);
                dlgPrecioCambiad.setVisible(true);
                break;
            case "0121"://Impresión de Stickers
                DlgProdImpresion dlgProdImpresion = new DlgProdImpresion(this, "", true);
                dlgProdImpresion.setVisible(true);
                break;
            case "0122"://Registro de Ventas
                DlgRegistroVentas dlgRegistroVentas = new DlgRegistroVentas(this, "", true);
                dlgRegistroVentas.setVisible(true);
                break;
            case "0124"://Detalle de ventas
                DlgDetalleVentas dlgDetalleVentas = new DlgDetalleVentas(this, "", true);
                dlgDetalleVentas.setVisible(true);
                break;
            case "0125"://Ventas resumen por día
                DlgVentasResumenPorDia dlgVentasResumenPorDia = new DlgVentasResumenPorDia(this, "", true);
                dlgVentasResumenPorDia.setVisible(true);
                break;
            case "0126"://Ventas por producto
                DlgVentasPorProducto dlgVentasPorProducto = new DlgVentasPorProducto(this, "", true);
                dlgVentasPorProducto.setVisible(true);
                break;
            case "0128"://Ventas por linea
                log.info("Opcion no desarrollada");
                break;
            case "0129"://Ventas por hora
                DlgVentasPorHora dlgVentasPorHora = new DlgVentasPorHora(this, "", true);
                dlgVentasPorHora.setVisible(true);
                break;
            case "0130"://Ventas por día del mes
                VariablesReporte.vOrdenar = ConstantsReporte.vVentasDiaMes;
                DlgVentasDiaMes dlgVentasDiaMes = new DlgVentasDiaMes(this, "", true);
                dlgVentasDiaMes.setVisible(true);
                break;
            case "0131"://Productos Falta Cero
                DlgProductoFaltaCero dlgProductoFaltaCero = new DlgProductoFaltaCero(this, "", true);
                dlgProductoFaltaCero.setVisible(true);
                break;
            case "0132"://Productos ABC
                DlgProductosABC dlgProductosABC = new DlgProductosABC(this, "", true);
                dlgProductosABC.setVisible(true);
                break;
            case "0133"://Unidad Venta Local
                DlgUnidVtaLocal dlgUnidVtaLocal = new DlgUnidVtaLocal(this, "", true);
                dlgUnidVtaLocal.setVisible(true);
                break;
            case "0134"://Productos sin venta en 90 dias
                DlgProdSinVtaNDias dlgProdSinVtaNDias = new DlgProdSinVtaNDias(this, "", true);
                dlgProdSinVtaNDias.setVisible(true);
                break;
            case "0135"://Registro de Psicotropicos
                DlgReportePsicotropicos dlgReportePsicotropicos = new DlgReportePsicotropicos(this, "", true);
                dlgReportePsicotropicos.setVisible(true);
                break;
            case "0136"://Kardex de Recetario
                DlgListaRecetarios dlgListaRecetario = new DlgListaRecetarios(this, "", true);
                dlgListaRecetario.setVisible(true);
                break;
            case "0137"://Reporte de Guias
                DlgReporteGuia dlgReporteGuia = new DlgReporteGuia(this, "", true);
                dlgReporteGuia.setVisible(true);
                break;
            case "0138"://Registro de Ventas Delivery
                DlgRegistroVentasDelivery dlgRegistroVentasDeliv = new DlgRegistroVentasDelivery(this, "", true);
                dlgRegistroVentasDeliv.setVisible(true);
                break;
            case "0139"://Ventas Totales (sin mayorista)
                VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_TOTALES;
                //ERIOS 18.04.2016 Determina el tipo de comision
                VariablesReporte.indVerTipoComision = (new FacadeReporte()).getVerTipoComision();
                
                DlgVentasPorVendedor dlgVentasTotal = new DlgVentasPorVendedor(this, "", true);
                dlgVentasTotal.setVisible(true);
                break;
            case "0140"://Ventas Meson
                VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_MEZON;
    
                DlgVentasPorVendedor dlgVentasMezon = new DlgVentasPorVendedor(this, "", true);
                dlgVentasMezon.setTitle("Ventas por Vendedor Meson");
                dlgVentasMezon.setVisible(true);
                break;
            case "0141"://Ventas Delivery
                VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_DELIVERY;
    
                DlgVentasPorVendedor dlgVentasDelivery = new DlgVentasPorVendedor(this, "", true);
                dlgVentasDelivery.setTitle("Ventas por Vendedor Delivery");
                dlgVentasDelivery.setVisible(true);
                break;
            case "0142"://Ventas Mayorista
                VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_INSTITUCIONAL;
    
                DlgVentasPorVendedor dlgVentasMayorista = new DlgVentasPorVendedor(this, "", true);
                dlgVentasMayorista.setTitle("Ventas por Vendedor Institucional");
                dlgVentasMayorista.setVisible(true);
                break;
            case "0143"://Concurso Garantimania
                DlgReporteGigante dlgConcuroGigante = new DlgReporteGigante(this, "", true);
                dlgConcuroGigante.setTitle("Ventas por Vendedor Meson");
                dlgConcuroGigante.setVisible(true);
                break;
            case "0144"://Reporte Convenios
                DlgRegistroVentasConvenio dlgVentasConvenio = new DlgRegistroVentasConvenio(this, "", true);
                dlgVentasConvenio.setTipo("R");
                dlgVentasConvenio.setVisible(true);
                break;
            case "0145"://Liquidacion Convenios
                DlgRegistroVentasConvenio dlgLiquidaConvenio = new DlgRegistroVentasConvenio(this, "", true);
                dlgLiquidaConvenio.setTipo("L");
                dlgLiquidaConvenio.setVisible(true);
                break;
            case "0146"://Cierre Turno
                DlgCierreCajaTurno dlgCierreCajaTurno = new DlgCierreCajaTurno(this, "", true);
                dlgCierreCajaTurno.setLocationRelativeTo(null);
                dlgCierreCajaTurno.setVisible(true);
                llamarDlgLogin();
                break;
            case "0147"://Cierre Local
                DlgHistoricoCierreDia dlgHistoricoCierreDia = new DlgHistoricoCierreDia(this, "", true);
                dlgHistoricoCierreDia.setVisible(true);
                break;
            case "0148"://Portavalor
                if(isLocalProsegur()){
                    DlgListaRemito dlgListaRem = new DlgListaRemito(this, "", true);
                    dlgListaRem.setVisible(true);
                }else{
                    FarmaUtility.showMessage(this,"Opcion no habilitada para el local", null);
                }
                break;
            case "0150"://Reprocesar conciliaciones
                DlgDatosTarjetaPinpad.reprocesarConciliaciones();
                break;
            case "0151"://Recibo e Sencillo
                DlgReciboPagoSencillo dlgReciboSencillo = new DlgReciboPagoSencillo(this, "", true, false);
                dlgReciboSencillo.setTitle(ConstantsCajaElectronica.TIT_RECIBO_SENCILLO);
                if (dlgReciboSencillo.permisoAbrirVentana()) {
                    dlgReciboSencillo.setVisible(true);
                }
                break;
            case "0152"://Pago de Sencillo
                DlgReciboPagoSencillo dlgPagoSencillo = new DlgReciboPagoSencillo(this, "", true, true);
                dlgPagoSencillo.setTitle(ConstantsCajaElectronica.TIT_PAGO_SENCILLO);
                if (dlgPagoSencillo.permisoAbrirVentana()) {
                    dlgPagoSencillo.setVisible(true);
                }
                break;
            case "0153"://Ingreso
                DlgControlIngreso dlgControlIngreso = new DlgControlIngreso(this, "", true);
                dlgControlIngreso.setVisible(true);
                break;
            case "0154"://Ingreso Temperatura
                DlgHistoricoTemperatura dlghistorico = new DlgHistoricoTemperatura(this, "", true);
                dlghistorico.setVisible(true);
                break;
            case "0155"://Admisión
                DlgADMBuscarComprobantePago dlgBuscarComp = new DlgADMBuscarComprobantePago(this, "", true);
                dlgBuscarComp.setVisible(true);
                break;
            case "0156"://Triaje
                /*DlgListaEsperaTriaje dlgListaEspera = new DlgListaEsperaTriaje(this,"",true, TipoAtencionCM.TRIAJE);
                dlgListaEspera.setVisible(true);*/
                DlgListaEspera dlgListaEsperaTriaje = new DlgListaEspera(this,"",true, TipoAtencionCM.ADMISION);
                dlgListaEsperaTriaje.setVisible(true);
                break;
            case "0157"://Consulta
                DlgLoginMedico dlgLogin = new DlgLoginMedico(this,"", true);
                dlgLogin.setVisible(true);
                /*DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                dlgLogin.setRolUsuario(FarmaConstants.ROL_VENDEDOR);
                dlgLogin.setVisible(true);*/
                if (FarmaVariables.vAceptar) {
                    FarmaVariables.vAceptar = false;
                    String codMedico = dlgLogin.getCodMedico();
                    DlgListaEspera dlgListaEsperaConsulta = new DlgListaEspera(this,"",true, TipoAtencionCM.CONSULTA);
                    dlgListaEsperaConsulta.setPCodMedico(codMedico);
                    dlgListaEsperaConsulta.setVisible(true);
                }
                break;
            case "0158"://Trazabilidad
                DlgADMTrazabilidad dlgTrazabilidad = new DlgADMTrazabilidad(this, "", true);
                dlgTrazabilidad.setVisible(true);
                break;
            case "0159"://Actualizar Paciente
                DlgADMListaPacientes dlgListaPac = new DlgADMListaPacientes(this, "", true,new VtaCompAtencionMedica(),new VtaPedidoAtencionMedica(),"A");
                dlgListaPac.setVisible(true);
                break;
            case "0160"://Salir de Sistema
                salirSistema();
                break;
            case "0161"://Acerca de …
                DlgAcercaDe dlgAcercaDe = new DlgAcercaDe(this, "", true);
                dlgAcercaDe.setVisible(true);
                break;
            case "0162"://Test Desarrollo
                DlgAcceso dlg = new DlgAcceso(this, "", true);
                dlg.setVisible(true);    
                break;
            case "0164":
                DlgConsultaSaldo dlgSaldoPunto = new DlgConsultaSaldo(this, "Consular Saldo Monedero - Escanear tarjeta",true);
                dlgSaldoPunto.setVisible(true);
                break;
            case "0165":
                DlgRecuperarPuntos dlgRecupera = new DlgRecuperarPuntos(this, "",true);
                dlgRecupera.setVisible(true);
                break;
            case "0166":
                DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(this, "", true, ConstantsPuntos.BLOQUEO_TARJETA);
                dlgVerifica.setIsRequiereDni(false);
                dlgVerifica.setIsRequiereTarjeta(true);
                dlgVerifica.setVisible(true);
                break;
            case "0167":
                DlgConsultaSaldo dlgDesafiliaXmas1 = new DlgConsultaSaldo(this, "Desafiliacion Programa Monedero - Escanear Tarjeta",true,true);
                dlgDesafiliaXmas1.setVisible(true);
                break;
            case "0169":
                DlgSolicitudCotizacionPrecio dlgConDoc=new DlgSolicitudCotizacionPrecio(this, "Cotización de Precios - Solicitud - Comprar", true,TIPO_COTIZA_COMPRAR);
                dlgConDoc.setVisible(true);
                break;
            case "0170":
                DlgSolicitudCotizacionPrecio dlgSinDoc=new DlgSolicitudCotizacionPrecio(this, "Cotización de Precios - Solicitud - Cotizar", true,TIPO_COTIZA_COTIZAR);
                dlgSinDoc.setVisible(true);
                break;
            case "0171":
                mnuCotiza_SinSolicitud();
                break;
        }
    } // Fin de AccionesMenu
    
    private void mnuCotiza_SinSolicitud() {
       // VarCotizacionPrecio.vTipo_Proceso = "03";
        VarCotizacionPrecio.vTipo_Origen = "04";
        VarCotizacionPrecio.vCod_Origen = FarmaVariables.vCodLocal;
        VarCotizacionPrecio.vFecha_Cotizacion = "";
        VariablesInventario.vFileImagen=null;
        
        VarCotizacionPrecio.vSerieDoc="";
         VarCotizacionPrecio.vNroGuia="";
        VarCotizacionPrecio.vRuta_Archivo="";
        
        DlgCotizacionPrecioCabecera dlgCotizacionPrecioCabecera = new DlgCotizacionPrecioCabecera(this, "", true);
        dlgCotizacionPrecioCabecera.setTipoProceso(String.valueOf(TIPO_COTIZA_SIN_SOLICITUD));
        dlgCotizacionPrecioCabecera.setNombreTienda("");
        dlgCotizacionPrecioCabecera.setNroRUC("");
        dlgCotizacionPrecioCabecera.setNroSolicitud("");
        dlgCotizacionPrecioCabecera.setFechaSolicitud("");
        dlgCotizacionPrecioCabecera.setVisible(true);
    }
    
    private void generarPedido() {
        DlgResumenPedido dlgResumenPedido = new DlgResumenPedido(this, "", true, false);
        dlgResumenPedido.setFrameMenuFar(this);
        dlgResumenPedido.setVisible(true);
        dlgResumenPedido = null;
        while (FarmaVariables.vAceptar) {
            if (VariablesVentas.vIndPrecioCabeCliente.equalsIgnoreCase("S")) { //Inicio: ASOSA 03.02.2010
                VariablesVentas.vIndPrecioCabeCliente = "N";
                ind = 1;
                break;
            } else {
                if (ind != 1) {
                    log.info("Go Menu");
                    generarPedido();
                } else {
                    ind = 0;
                    break;
                }
            } //Fin: ASOSA 03.02.2010
        }        
    }    
    
    private void distribucionGratuita() {
        DlgResumenPedidoGratuito dlgResumenPedidoGratuito = new DlgResumenPedidoGratuito(this, "", true);
        dlgResumenPedidoGratuito.setVisible(true);
        while (FarmaVariables.vAceptar)
            distribucionGratuita();
        if (!FarmaVariables.vAceptar)
            verificaRolUsuario();
    }
    
    private void mnuCaja_AperturarCaja_actionPerformed() {
        
        //INI ASOSA - 22/09/2015 - CTRLASIST
        boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(this,
                                                            ConstantsControlAsistencia.ID_ENTRADA);
        if (!flagCA) {
            llamarDlgLogin();
        }
        
        //FIN ASOSA - 22/09/2015 - CTRLASIST
        
        if(validaCierresCajero(FarmaVariables.vNuSecUsu)){
        VariablesCaja.vTipMovCaja = ConstantsCaja.MOVIMIENTO_APERTURA;
        DlgMovCaja dlgMovCaja = new DlgMovCaja(this, "", true);
        try {
            dlgMovCaja.validarParamsUser();
            dlgMovCaja.verificaAperturaCaja();
            dlgMovCaja.setVisible(true);
        } catch (SQLException ex) {
            String mensaje = "";
            if (ex.getErrorCode() == 20011)
                mensaje = "El usuario no posee ninguna caja activa asociada.";
            else if (ex.getErrorCode() == 20012)
                mensaje = "La caja del usuario ya se encuentra aperturada.";
            else if (ex.getErrorCode() == 20013)
                mensaje = "La caja del usuario ya se encuentra cerrada.";
            else if (ex.getErrorCode() == 20020)
                mensaje = "No puede aperturar una caja cuando ya existe una fecha de \n" +
                        "cierre de dia de venta registrada para el dia de hoy.";
            else
                mensaje = ex.getMessage();

            Object obj = new Object();
            obj = null;
            FarmaUtility.showMessage(dlgMovCaja, mensaje, obj);
            dlgMovCaja.dispose();
        }
    }
        else{
            //FarmaUtility.showMessage(this, "No puede aperturar la caja, por tener turnos pendientes de dar visto bueno.", null);
            //getListaCajaSinVBCajero
            ArrayList vLista = new ArrayList();
            vLista = getListaCajaSinVBCajero(FarmaVariables.vNuSecUsu);
            log.info("Caja sin cerrar "+vLista);
            DlgValidaAperturaCaja dlgMovCaja = new DlgValidaAperturaCaja(this, "", true,vLista);
            dlgMovCaja.setVisible(true);
            
        }
    }
    
    private boolean validaCierresCajero(String vSecUsuLogin) {
        try {
            return DBCajaElectronica.getValidaCajaApertura(vSecUsuLogin);
        } catch (Exception sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
            log.info(sqle.getMessage());
            return false;
        }
    }
    
    private ArrayList getListaCajaSinVBCajero(String vSecUsuLogin) {
        ArrayList vLista = new ArrayList();
        try {
            return DBCajaElectronica.getCajaSinVBCajero(vSecUsuLogin);
        } catch (Exception sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
            log.info(sqle.getMessage());
            return vLista;
        }
    }
    
    private void mnuCaja_CerrarCaja_actionPerformed() {
        VariablesCaja.vTipMovCaja = ConstantsCaja.MOVIMIENTO_CIERRE;
        DlgMovCaja dlgMovCaja = new DlgMovCaja(this, "", true);
        try {
            dlgMovCaja.validarParamsUser();
            dlgMovCaja.setVisible(true);
        } catch (SQLException ex) {
            String mensaje = "";

            if (ex.getErrorCode() == 20011) {
                mensaje = "El usuario no posee ninguna caja activa asociada.";
            } else if (ex.getErrorCode() == 20012) {
                mensaje = "La caja del usuario ya se encuentra aperturada.";
            } else if (ex.getErrorCode() == 20013) {
                mensaje = "La caja del usuario ya se encuentra cerrada.";
            } else {
                mensaje = ex.getMessage();
            }

            Object obj = new Object();
            obj = null;
            FarmaUtility.showMessage(dlgMovCaja, mensaje, obj);
            dlgMovCaja.dispose();
        }
    }
    
    private void mnuCaja_ConfigurarCaja_actionPerformed() {
        int indIpValida = 0;
        try {
            indIpValida = DBPtoVenta.verificaIPValida();
            if (indIpValida == 0)
                FarmaUtility.showMessage(this,
                                         "La estación actual no se encuentra autorizada para efectuar la operación. ",
                                         null);
            else {
                DlgConfiguracionComprobante dlgConfiguracionComprobante =
                    new DlgConfiguracionComprobante(this, "", true);
                dlgConfiguracionComprobante.setVisible(true);
                FarmaVariables.vAceptar = false;
            }
        } catch (SQLException ex) {
            log.error("", ex);
            FarmaUtility.showMessage(this, "Error al validar IP de Configuracion de Comprobantes.\n" +
                    ex, null);
            indIpValida = 0;
        }
    }
    
    private void mnuCaja_RegulaVtaManual_actionPerformed() {
        DlgResumenPedido dlgResumenPedido = new DlgResumenPedido(this, "", true, true);
        dlgResumenPedido.setFrameMenuFar(this);
        dlgResumenPedido.setVisible(true);
        dlgResumenPedido = null;
    }
    
    private boolean validarAsistAudit() {
        boolean flag = false;
        String ind = "";
        try {
            ind =
    DBInventario.validarAsistenteAuditoria(FarmaVariables.vCodCia, FarmaVariables.vCodLocal, FarmaVariables.vNuSecUsu);
            if (ind.equalsIgnoreCase("S"))
                flag = true;
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "ERROR en validarAsistAudit \n : " + sql.getMessage(), null);
        }
        return flag;
    }
    
    public void muestraLoginCambioUser() {

        DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
        dlgLogin.setVisible(true);
        
        if (FarmaVariables.vAceptar) {
            FarmaVariables.dlgLogin = dlgLogin;
            VariablesCaja.vVerificaCajero = true;
            actualizaMenu();
            UtilityPtoVenta.muestraUser(this);
            boolean flagCA = UtilityControlAsistencia.determinarMarcacionGeneral(this,
                                                                ConstantsControlAsistencia.ID_ENTRADA);
            if (!flagCA) {
                llamarDlgLogin();
            }
            //FIN ASOSA - 22/09/2015 - CTRLASIST
        } 
        
    }
    
    private boolean isLocalProsegur() {
        String pIndCESeguridad = "";
        boolean pVisible = false;
        try {
            pVisible = DBCaja.obtieneIndicadorProsegur();

        } catch (SQLException sql) {
            pIndCESeguridad = FarmaConstants.INDICADOR_N;
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al grabar forma pago pedido \n" +
                    sql.getMessage(), null);
        }

        return pVisible;
    }
    
    private void verificaRolUsuario() {
    }

    private void actualizaMenu() {
        
        try {
            jbInit();
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            UtilityPtoVenta.Actualiza_IndicadoresMenu();
            ArrayList<ArrayList> listaMenu=new ArrayList<ArrayList>();
            String ipLocal=FarmaUtility.getHostAddress();
            if(ipLocal.indexOf("10.18.")!=-1){
                listaMenu=UtilMenu.Cargar_MenuDesarrollo("FARMAVENTA");
            }else{
                listaMenu=UtilMenu.Cargar_MenuFarmaventa("FARMAVENTA");
            }
            FMenu menuBar=new FMenu(listaMenu,"AcctionPerformed");
            menuBar.setFont(new Font("SansSerif", 0, 11));
            this.setJMenuBar(menuBar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
