package mifarma.ptoventa.main;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import farmaciasperuanas.model.BeanVenta;

import farmaciasperuanas.reference.DBRefacturadorElectronico;
import farmaciasperuanas.reference.VariablesRefacturadorElectronico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;

import java.lang.reflect.Field;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.text.html.HTML;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.SubProcesoCPE;

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
import mifarma.ptoventa.caja.DlgAnularPedidoComprobante;
import mifarma.ptoventa.caja.DlgConfiguracionComprobante;
import mifarma.ptoventa.caja.DlgControlSobres;
import mifarma.ptoventa.caja.DlgDatosTarjetaPinpad;
import mifarma.ptoventa.caja.DlgFormaPago;
import mifarma.ptoventa.caja.DlgListaRemito;
import mifarma.ptoventa.caja.DlgListaTicketsAnulados;
import mifarma.ptoventa.caja.DlgMovCaja;
import mifarma.ptoventa.caja.DlgPedidosPendientes;
import mifarma.ptoventa.caja.DlgPedidosPendientesImpresion;
import mifarma.ptoventa.caja.DlgPruebaImpresora;
import mifarma.ptoventa.caja.DlgVerificacionComprobantes;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.FacadeCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.ce.DlgCierreCajaTurno;
import mifarma.ptoventa.ce.DlgHistoricoCierreDia;
import mifarma.ptoventa.ce.DlgReciboPagoSencillo;
import mifarma.ptoventa.ce.DlgValidaAperturaCaja;
import mifarma.ptoventa.ce.reference.ConstantsCajaElectronica;
import mifarma.ptoventa.ce.reference.DBCajaElectronica;
import mifarma.ptoventa.centromedico.DlgADMBuscarComprobantePago;
import mifarma.ptoventa.centromedico.DlgADMBuscarPaciente;
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
import mifarma.ptoventa.controlAsistencia.DlgListadoHorario;
import mifarma.ptoventa.controlAsistencia.DlgListadoPlantilla;
import mifarma.ptoventa.controlAsistencia.reference.ConstantsControlAsistencia;
import mifarma.ptoventa.controlAsistencia.reference.UtilityControlAsistencia;
import mifarma.ptoventa.controlAsistencia.reference.VariablesControlAsistencia;
import mifarma.ptoventa.convalidado.DlgConvalidado;
import mifarma.ptoventa.convenioBTLMF.DlgReporteProductosFaltantes;
import mifarma.ptoventa.convenioBTLMF.DlgUploadCobro_Responsabilidad;
import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.DlgCotizacionPrecioCabecera;
import mifarma.ptoventa.cotizarPrecios.DlgSolicitudCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.delivery.DlgUltimosPedidos;
import mifarma.ptoventa.fidelizacion.DlgFidelizacionBuscarTarjetas;
import mifarma.ptoventa.fraccion.DlgSolicitudFraccion;
import mifarma.ptoventa.fraccion.reference.UtilityFraccion;
import mifarma.ptoventa.hilos.SubProcesoAlertUpCotizacionPrecio;
import mifarma.ptoventa.hilos.SubProcesoFarmaVenta;
import mifarma.ptoventa.hilos.SubProcesos;
import mifarma.ptoventa.hilos.SubProcesosALertatUp;
import mifarma.ptoventa.inventario.DlgAjustesporFecha;
import mifarma.ptoventa.inventario.DlgDevolucionesLista;
import mifarma.ptoventa.inventario.DlgDevolucionesLista_02;
import mifarma.ptoventa.tomainventario.DlgGenerarData;
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
import mifarma.ptoventa.inventario.reference.UtilityInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.inventario.transfDelivery.DlgListaTransfPendientes;
import mifarma.ptoventa.inventariociclico.DlgInicioInvCiclico;
import mifarma.ptoventa.inventariociclico.DlgListaTomasInventarioCiclico;
import mifarma.ptoventa.inventariodiario.DlgInicioInveDiario;
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
import mifarma.ptoventa.recaudacion.reference.UtilityRecaudacion;
import mifarma.ptoventa.recepcionCiega.DlgHistoricoRecepcion;
import mifarma.ptoventa.recepcionCiega.DlgListaTransportistas;
import mifarma.ptoventa.recepcionCiega.DlgRptBandejas;
import mifarma.ptoventa.recepcionCiega.reference.UtilityRecepCiega;
import mifarma.ptoventa.recetario.DlgListaGuiaProductoVirtualRM;
import mifarma.ptoventa.recetario.DlgListaRecetarios;
import mifarma.ptoventa.reference.BeanVariables;
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
import mifarma.ptoventa.reportes.DlgReporteGarantizados;
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
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.ventas.DlgConsultarRecargaCorrelativo_AS;
import mifarma.ptoventa.ventas.DlgListaProdDIGEMID;
import mifarma.ptoventa.ventas.DlgResumenPedido;
import mifarma.ptoventa.ventas.DlgResumenPedidoGratuito;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import mifarma.ptoventa.pruebas.DlgMenuPruebas;


/**
 * Copyright (c) 2005 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : FrmEconoFar.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * LMESIA      27.12.2005   Creaci�n<br>
 * ERIOS       20.06.2013   Modificacion<br>
 * ASOSA       21.10.2015   Modificacion<br>
 * <br>
 * @author Luis Mesia Rivera<br>
 * @version 1.0<br>
 *
 */
public class FrmEconoFar extends JFrame {

    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(FrmEconoFar.class);

    //public static String tituloBaseFrame = "FarmaVenta";
    int ind = 0;
    JLabel statusBar = new JLabel();
    private JPanel pnlEconoFar = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JLabel lblLogo = new JLabel();
    private JButtonLabel btnRevertir = new JButtonLabel();
    private JTextFieldSanSerif txtRevertir = new JTextFieldSanSerif();
    private JLabel lblMensaje = new JLabel("EN PRUEBAS", JLabel.CENTER);
    private JMenuBar mnuPtoVenta = new JMenuBar();
    private JMenu mnuEconoFar_Ventas = new JMenu();
    private JMenuItem mnuVentas_GenerarPedido = new JMenuItem();
    private JMenuItem mnuVentas_DistribucionGratuita = new JMenuItem();
    private JMenuItem mnuVentas_DeliveryAutomatico = new JMenuItem();
    private JMenuItem mnuVentas_Empresa = new JMenuItem();
    private JMenuItem mnuVentas_MedidaPresion = new JMenuItem();
    private JMenu mnuVentas_Recargas = new JMenu();
    private JMenuItem mnuVentas_Correlativo = new JMenuItem();
    private JMenuItem mnuVentas_InvDiario = new JMenuItem();
    private JMenuItem mnuVentas_Digemid = new JMenuItem();
    private JMenu mnuEconoFar_Caja = new JMenu();
    private JMenu mnuCaja_MovimientosCaja = new JMenu();
    private JMenuItem mnuCaja_AperturarCaja = new JMenuItem();
    private JMenuItem mnuCaja_CerrarCaja = new JMenuItem();
    private JMenuItem mnuCaja_ConfigurarCaja = new JMenuItem();
    private JMenuItem mnuCaja_CobrarPedido = new JMenuItem();
    private JMenu mnuCaja_AnularVentas = new JMenu();
    private JMenuItem mnuCaja_CambioProducto = new JMenuItem();
    private JMenuItem mnuCaja_PedidoCompleto = new JMenuItem();
    private JMenuItem mnuCaja_AnularComprobante = new JMenuItem();
    private JMenuItem mnuCaja_CorreccionComprobantes = new JMenuItem();
    private JMenuItem mnuCaja_ReimpresionPedido = new JMenuItem();
    private JMenu mnuCaja_Utilitarios = new JMenu();
    private JMenuItem mnuCaja_PruebaImpresora = new JMenuItem();
    private JMenu mnuCaja_Pinpad = new JMenu();
    private JMenuItem mnuCaja_Pinpad_Visa = new JMenuItem();
    private JMenuItem mnuCaja_Pinpad_Mastercard = new JMenuItem();
    private JMenuItem mnuCaja_reporDet_Pinpad_MC = new JMenuItem();
    private JMenuItem mnuCaja_reporTot_Pinpad_MC = new JMenuItem();
    private JMenuItem mnuCaja_Reimpresion_Pinpad_MC = new JMenuItem();
    private JMenuItem mnuCaja_AnulacionTran_Pinpad_Visa = new JMenuItem();
    private JMenuItem mnuCaja_AbrirGabeta = new JMenuItem();
    private JMenuItem mnuCaja_ReimpresionTicketsAnulados = new JMenuItem();
    private JMenuItem mnuCaja_ControlSobresParciales = new JMenuItem();
    private JMenu mnuCaja_Recaudacion = new JMenu();
    private JMenuItem mnuCaja_CMR = new JMenuItem();
    private JMenuItem mnuCaja_Citibank = new JMenuItem();
    private JMenuItem mnuCaja_Claro = new JMenuItem();
    private JMenuItem mnuCaja_Prestamos_Citibank = new JMenuItem();
    private JMenuItem mnuCaja_Ripley = new JMenuItem();
    private JMenuItem mnuCaja_Registro_Recaudaciones = new JMenuItem();
    private JMenuItem mnuCaja_Registro_VentaCMR = new JMenuItem();

    private JMenu mnuEconoFar_Inventario = new JMenu();
    private JMenuItem mnuInventario_GuiaEntrada = new JMenuItem();
    private JMenuItem mnuInventario_Kardex = new JMenuItem(); //05.08.2014
    //private JMenu mnuInventario_Kardex = new JMenu();       //05.08.2014
    private JMenuItem mnuKardex_Kardex = new JMenuItem(); //05.08.2014
    private JMenuItem mnuKardex_ConsumoInter = new JMenuItem(); //05.08.2014

    private JMenu mnuInventario_Transferencias = new JMenu();
    private JMenuItem mnuInventario_Transf_local = new JMenuItem();
    private JMenuItem mnuInventario_Transf_delivery = new JMenuItem();
    private JMenuItem mnuInventario_Transf_guias = new JMenuItem();
    private JMenu mnuInventario_Mercaderia = new JMenu();
    private JMenuItem mnuInventario_Recepcion = new JMenuItem();
    private JMenuItem mnuInventario_RecepcionProductos = new JMenuItem();
    private JMenuItem mnuInventario_IngTransportista = new JMenuItem();
    private JMenu mnuInventario_PedidosLocales = new JMenu();
    private JMenuItem mnuInventario_PedidoReposicion = new JMenuItem();
    private JMenuItem mnuInventario_PedidoAdicional = new JMenuItem();
    private JMenuItem mnuInventario_PedidoEspecial = new JMenuItem();

    /*** Inicio CCASTILLO 10/10/2016 ***/
    private JMenuItem mnuInventario_PedidoEspecial_Insumo = new JMenuItem();

    /*** Fin  CCASTILLO 10/10/2016 ***/
    private JMenuItem mnuInventario_Guias = new JMenuItem();
    private JMenuItem mnuInventario_Ajustes = new JMenuItem();
    private JMenuItem mnuInventario_RecepcionLocales = new JMenuItem();
    private JMenu mnuInventario_MercaderiaDirecta = new JMenu();
    private JMenuItem mnuInventario_RecepcionMerDirec = new JMenuItem();
    private JMenuItem mnuInventario_Devolucion = new JMenuItem();
    private JMenuItem mnuInventario_DevolucionSobrante = new JMenuItem();

    private JMenu mnuEconoFar_TomaInventario = new JMenu();
    private JMenu mnuTomaInventario_Tradicional = new JMenu();
    private JMenuItem mnuInventario_Inicio = new JMenuItem();
    private JMenuItem mnuInventario_Toma = new JMenuItem();
    private JMenu mnuTomaInventario_Ciclico = new JMenu();
    private JMenuItem mnuInventarioCiclico_Inicio = new JMenuItem();
    private JMenuItem mnuInventarioCiclico_Toma = new JMenuItem();
    private JMenu mnuTomaInventario_Diario = new JMenu();
    private JMenuItem mnuInventarioDiario_Toma = new JMenuItem();
    private JMenuItem mnuInventarioDiario_Diferencia = new JMenuItem();
    private JMenuItem mnuTomaInventario_Historico = new JMenuItem();
    private JMenuItem mnuTomaInVentario_ItemsXLab = new JMenuItem();
    private JMenuItem mnuGenerarData = new JMenuItem();

    private JMenu mnuEconoFar_Administracion = new JMenu();
    private JMenu mnuAdministracion_Usuarios = new JMenu();
    private JMenuItem mnuUsuarios_CambioUsuario = new JMenuItem();
    private JMenuItem mnuUsuarios_CambioClave = new JMenuItem();
    private JMenu mnuAdministracion_Mantenimiento = new JMenu();
    private JMenuItem mnuMantenimiento_Usuarios = new JMenuItem();
    private JMenuItem mnuMantenimiento_Cajas = new JMenuItem();
    private JMenuItem mnuMantenimiento_Impresoras = new JMenuItem();
    private JMenuItem mnuMantenimiento_Clientes = new JMenuItem();
    private JMenuItem mnuMantenimiento_Parametros = new JMenuItem();
    private JMenuItem mnuMantenimiento_Carne = new JMenuItem();
    private JMenuItem mnuMantenimiento_MaquinaIP = new JMenuItem();
    private JMenuItem mnuMantenimiento_ImpresoraTermica = new JMenuItem();
    private JMenuItem mnuMantenimiento_RolTemporal = new JMenuItem();
    private JMenuItem mnuAdministracion_PresupuestoVentas = new JMenuItem();
    private JMenu mnuAdministracion_MovCaja = new JMenu();
    private JMenuItem mnuMovCaja_RegistrarVentas = new JMenuItem();
    private JMenuItem mnuMovCaja_ConsultarMov = new JMenuItem();
    private JMenuItem mnuAdministracion_RegViajero = new JMenuItem();
    private JMenuItem mnuAdministracion_ControlHoras = new JMenuItem();
    private JMenu mnuAdministracion_Otros = new JMenu();
    private JMenuItem mnuOtros_MantProbisa = new JMenuItem();
    private JMenuItem mnuAdministracion_FondoSencillo = new JMenuItem();
    private JMenu mnuAdministracion_ProdCambiados = new JMenu();
    private JMenuItem mnuProdCambiados_PrecioCambProd = new JMenuItem();
    //01.10.2013 CVILCA
    private JMenuItem mnuProductos_Impresion = new JMenuItem();

    private JMenu mnuEconoFar_Reportes = new JMenu();
    private JMenuItem mnuReportes_RegistroVentas = new JMenuItem();
    private JMenu mnuReportes_VentasVendedor = new JMenu();
    private JMenuItem mnuReportes_VentasVendedor_Total = new JMenuItem();
    private JMenuItem mnuReportes_VentasVendedor_Mezon = new JMenuItem();
    private JMenuItem mnuReportes_VentasVendedor_Delivery = new JMenuItem();
    private JMenuItem mnuReportes_VentasVendedor_Institucional = new JMenuItem();
    private JMenuItem mnuReportes_VentasVendedor_Gigante = new JMenuItem();
    private JMenuItem mnuReportes_DetalleVentas = new JMenuItem();
    private JMenuItem mnuReportes_ResumenVentaDia = new JMenuItem();
    private JMenuItem mnuReportes_VentasProducto = new JMenuItem();
    private JMenu mnuReportes_VentasConvenio = new JMenu();
    private JMenuItem mnuReportes_VentasConvenio_Reporte = new JMenuItem();
    private JMenuItem mnuReportes_VentasConvenio_Liquidacion = new JMenuItem();
    private JMenuItem mnuReportes_VentasLinea = new JMenuItem();
    private JMenuItem mnuReportes_VentasHora = new JMenuItem();
    private JMenuItem mnuReportes_VentasDiaMes = new JMenuItem();
    private JMenuItem mnuReportes_FaltaCero = new JMenuItem();
    private JMenuItem mnuReportes_ProductosABC = new JMenuItem();
    private JMenuItem mnuReportes_UnidVtaLocal = new JMenuItem();
    private JMenuItem mnuReportes_ProdSinVtaNDias = new JMenuItem();
    private JMenuItem mnuReportes_RegVtaDelivery = new JMenuItem();

    private JMenu mnuEconofar_CajaElectronica = new JMenu();
    private JMenuItem mnuCE_CierreTurno = new JMenuItem();
    private JMenuItem mnuCE_CierreLocal = new JMenuItem();
    private JMenuItem mnuCE_Prosegur = new JMenuItem();

    //09.09.2013 wvillagomez
    private JMenu mnuCE_PagoSencilloETV = new JMenu();
    private JMenuItem mnuCE_ReciboSencillo = new JMenuItem();
    private JMenuItem mnuCE_PagoSencillo = new JMenuItem();

    private JMenu mnuEconoFar_ControlIngreso = new JMenu();
    private JMenuItem mnuIngreso = new JMenuItem();
    private JMenuItem mnuIngresoTemperatura = new JMenuItem();


    private JMenu mnuEconoFar_Salir = new JMenu();
    private JMenuItem mnuSalir_SalirSistema = new JMenuItem();

    private JMenu mnuEconoFar_Ayuda = new JMenu();
    private JMenuItem mnuAyuda_AcercaDe = new JMenuItem();
    private JMenuItem mnCaja_ReimpresionLote_Pinpad_MC = new JMenuItem();
    private JMenuItem mnuReportes_ListPsicotropicos = new JMenuItem();
    private JMenuItem mnuReportes_ListRecetario = new JMenuItem();
    private JMenuItem mnuCE_ReprocesaConcil = new JMenuItem();
    private JMenuItem mnuInventario_RecepcionRM = new JMenuItem();
    private JMenuItem prueba = new JMenuItem();
    private JMenuItem mnuInventario_StockNegativo = new JMenuItem();
    private JMenuItem mnuCaja_PedidoPinpad = new JMenuItem();
    private JMenuItem mnuReportes_ListGuias = new JMenuItem();
    private JMenuItem mnuCaja_RegulaVtaManual = new JMenuItem();
    private JMenu jmnPuntos = new JMenu();
    private JMenuItem jMenuItem1 = new JMenuItem();
    private JMenuItem jmmRecueraPuntos = new JMenuItem();
    private JMenuItem jmmBloqueoTarjeta = new JMenuItem();
    private JMenuItem jmmDesafiliacionx1 = new JMenuItem();
    private JMenuItem mnuCaja_ServiciosVisa = new JMenuItem();
    private JMenuItem mnuAdm_mntoCli = new JMenuItem();
    private JMenuItem mnuInventario_BandejasDevolver = new JMenuItem();
    private JMenuItem mnuCaja_Raiz = new JMenuItem();
    private JMenuItem mnuCaja_Incasur = new JMenuItem();

    // KMONCADA 19.08.2016 [CENTRO MEDICO]
    private JMenu mnuEconoFar_CM = new JMenu();
    private JMenuItem mnuCM_Triaje = new JMenuItem();
    private JMenuItem mnuCM_Consulta = new JMenuItem();

    // CCASTILLO 25.08.2016
    private JMenuItem mnuCM_ADMBuscarComprobantePago = new JMenuItem();
    private JMenuItem mnuCM_ADMTrazabilidad = new JMenuItem();
    private JMenuItem mnuCM_ADMActualizarPaciente = new JMenuItem();

    // AOVIEDO 17/04/2017
    private JMenuItem mnuReportes_VentasVendedor_Garantizados = new JMenuItem();
    private JMenuItem mnuInventario_SolicitudFraccion = new JMenuItem();
    private JMenu mnuCotizacionPrecio = new JMenu();
    private JMenuItem mnuCotiza_ConDocumento = new JMenuItem();
    private JMenuItem mnuCotiza_SinDocumento = new JMenuItem();

    //INI AOVIEDO 31/07/2017
    private JMenuItem mnuCaja_Financiero = new JMenuItem();
    private JMenuItem mnuInv_Pruebas = new JMenuItem();
    //FIN AOVIEDO 31/07/2017

    private JMenuItem mnuCotiza_SinSolicitud = new JMenuItem();

    public int TIPO_COTIZA_COMPRAR = 1;
    public int TIPO_COTIZA_COTIZAR = 2;
    public int TIPO_COTIZA_SIN_SOLICITUD = 3;
    private JMenuItem jMenuItem2 = new JMenuItem();
    private JMenuItem mnuCaja_IRS_Tesoreria = new JMenuItem();
    private JMenuItem mnuCobro_Responsabilidad = new JMenuItem();//JMONZALVE 24042019
    private JMenuItem mnuVentas_UploadCobro_Responsabilidad = new JMenuItem(); //JMONZALVE 24042019
    
    
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public FrmEconoFar() {

        try {

            jbInit();
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            initialize();
            DBTomaInv.cantTomaInventarios();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

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

        mnuPtoVenta.setFont(new Font("SansSerif", 0, 11));
        this.setJMenuBar(mnuPtoVenta);


        // MODULO VENTAS
        mnuEconoFar_Ventas.setText("Ventas");
        mnuEconoFar_Ventas.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_Ventas.setMnemonic('v');
        mnuPtoVenta.add(mnuEconoFar_Ventas);
        mnuVentas_GenerarPedido.setText("1. Generar Pedido");
        mnuVentas_GenerarPedido.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_GenerarPedido.setMnemonic('1');
        mnuVentas_GenerarPedido.setActionCommand("Realizar Venta");
        mnuVentas_GenerarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuVentas_GenerarPedido_actionPerformed(e);
            }

        });
        mnuEconoFar_Ventas.add(mnuVentas_GenerarPedido);
        mnuVentas_DistribucionGratuita.setText("2. Distribucion Gratuita");
        mnuVentas_DistribucionGratuita.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_DistribucionGratuita.setMnemonic('2');
        mnuVentas_DistribucionGratuita.setEnabled(false);
        mnuVentas_DistribucionGratuita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuVentas_DistribucionGratuita_actionPerformed(e);
            }
        });
        mnuEconoFar_Ventas.add(mnuVentas_DistribucionGratuita);
        mnuVentas_DeliveryAutomatico.setText("3. Delivery por atender");
        mnuVentas_DeliveryAutomatico.setMnemonic('3');
        mnuVentas_DeliveryAutomatico.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_DeliveryAutomatico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuVentas_DeliveryAutomatico_actionPerformed(e);
            }
        });
        mnuEconoFar_Ventas.add(mnuVentas_DeliveryAutomatico);

        mnuVentas_Empresa.setText("4. Empresas");
        mnuVentas_Empresa.setMnemonic('4');
        mnuVentas_Empresa.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_Empresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuVentas_Empresas_actionPerformed(e);
            }
        });
        mnuEconoFar_Ventas.add(mnuVentas_Empresa);

        mnuVentas_MedidaPresion.setText("5. Medida Presion");
        mnuVentas_MedidaPresion.setMnemonic('5');
        mnuVentas_MedidaPresion.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_MedidaPresion.setEnabled(false);
        mnuVentas_MedidaPresion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuVentas_MedidaPresion_actionPerformed(e);
            }
        });
        mnuEconoFar_Ventas.add(mnuVentas_MedidaPresion);
        mnuVentas_Recargas.setText("6. Recargas Telef�nicas");
        mnuVentas_Recargas.setMnemonic('6');
        mnuVentas_Recargas.setFont(new Font("Sanserif", 0, 11));
        mnuVentas_Recargas.setEnabled(false);
        mnuEconoFar_Ventas.add(mnuVentas_Recargas);
        mnuVentas_Correlativo.setText("1. Correlativo");
        mnuVentas_Correlativo.setMnemonic('1');
        mnuVentas_Correlativo.setFont(new Font("Sanserif", 0, 11));
        mnuVentas_Correlativo.setEnabled(true);
        mnuVentas_Correlativo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mnuVentas_Correlativo_actionPerformed(e);
            }
        });
        mnuVentas_Recargas.add(mnuVentas_Correlativo);
        mnuVentas_InvDiario.setText("7. Cobro Pedido Inv. Diario");
        mnuVentas_InvDiario.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_InvDiario.setMnemonic('7');
        mnuVentas_InvDiario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuAdm_mntoCli_actionPerformed(e);
            }
        });
        mnuEconoFar_Ventas.add(mnuVentas_InvDiario);
        mnuVentas_Digemid.setText("8. Lista Precios Consumidor");
        mnuVentas_Digemid.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_Digemid.setMnemonic('8');
        mnuVentas_Digemid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem4_DIGEMID(e);
            }
        });
        mnuEconoFar_Ventas.add(mnuVentas_Digemid);


        // MODULO CAJA
        jmnPuntos.add(jMenuItem1);
        jmnPuntos.add(jmmRecueraPuntos);
        jmnPuntos.add(jmmBloqueoTarjeta);
        jmnPuntos.add(jmmDesafiliacionx1);
        mnuVentas_UploadCobro_Responsabilidad.setText("9. Cargar Doc. Responsabilidad");//JMONZALVE 24042019
        if (UtilityPuntos.isActivoFuncionalidad()){
            mnuEconoFar_Ventas.add(jmnPuntos);
            mnuVentas_UploadCobro_Responsabilidad.setText("10. Cargar Doc. Responsabilidad");//JMONZALVE 24042019
        }
        mnuEconoFar_Ventas.add(mnuVentas_UploadCobro_Responsabilidad);//JMONZALVE 24042019

        mnuEconoFar_Caja.setText("Caja");
        mnuEconoFar_Caja.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_Caja.setMnemonic('c');
        mnuPtoVenta.add(mnuEconoFar_Caja);
        mnuCaja_MovimientosCaja.setText("1. Movimientos de Caja");
        mnuCaja_MovimientosCaja.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_MovimientosCaja.setMnemonic('1');
        mnuEconoFar_Caja.add(mnuCaja_MovimientosCaja);
        mnuCaja_AperturarCaja.setText("1. Aperturar Caja");
        mnuCaja_AperturarCaja.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_AperturarCaja.setMnemonic('1');
        mnuCaja_AperturarCaja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_AperturarCaja_actionPerformed(e);
            }
        });
        mnuCaja_MovimientosCaja.add(mnuCaja_AperturarCaja);
        mnuCaja_CerrarCaja.setText("2. Cerrar Caja");
        mnuCaja_CerrarCaja.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_CerrarCaja.setMnemonic('2');
        mnuCaja_CerrarCaja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_CerrarCaja_actionPerformed(e);
            }
        });
        mnuCaja_MovimientosCaja.add(mnuCaja_CerrarCaja);
        mnuCaja_ConfigurarCaja.setText("2. Configurar Caja");
        mnuCaja_ConfigurarCaja.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_ConfigurarCaja.setMnemonic('2');
        mnuCaja_ConfigurarCaja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_ConfigurarCaja_actionPerformed(e);
            }
        });
        mnuEconoFar_Caja.add(mnuCaja_ConfigurarCaja);
        mnuCaja_CobrarPedido.setText("3. Cobrar Pedido Pendiente");
        mnuCaja_CobrarPedido.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_CobrarPedido.setMnemonic('3');
        mnuCaja_CobrarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_CobrarPedido_actionPerformed(e);
            }
        });
        mnuEconoFar_Caja.add(mnuCaja_CobrarPedido);
        mnuCaja_AnularVentas.setText("4. Anular Ventas");
        mnuCaja_AnularVentas.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_AnularVentas.setMnemonic('4');
        mnuEconoFar_Caja.add(mnuCaja_AnularVentas);
        mnuCaja_CambioProducto.setText("1. Por Cambio Producto");
        mnuCaja_CambioProducto.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_CambioProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_CambioProducto_actionPerformed(e);
            }
        });
        mnuCaja_PedidoCompleto.setMnemonic('1');
        mnuCaja_PedidoCompleto.setText("2. Pedido Completo");
        mnuCaja_PedidoCompleto.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_PedidoCompleto.setMnemonic('2');
        mnuCaja_PedidoCompleto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_PedidoCompleto_actionPerformed(e);
            }
        });
        mnuCaja_AnularVentas.add(mnuCaja_CambioProducto);
        mnuCaja_AnularVentas.add(mnuCaja_PedidoCompleto);
        mnuCaja_AnularComprobante.setText("2. Anular Comprobante");
        mnuCaja_AnularComprobante.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_AnularComprobante.setActionCommand("Anular Pedidos");
        mnuCaja_AnularComprobante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_AnularComprobante_actionPerformed(e);
            }
        });
        //mnuCaja_AnularVentas.add(mnuCaja_AnularComprobante);
        mnuCaja_CorreccionComprobantes.setText("5. Correcci�n de comprobantes");
        mnuCaja_CorreccionComprobantes.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_CorreccionComprobantes.setMnemonic('5');
        mnuCaja_CorreccionComprobantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_CorreccionComprobantes_actionPerformed(e);
            }
        });
        mnuEconoFar_Caja.add(mnuCaja_CorreccionComprobantes);
        mnuCaja_ReimpresionPedido.setText("6. Reimpresion de Pedido");
        mnuCaja_ReimpresionPedido.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_ReimpresionPedido.setMnemonic('6');
        mnuCaja_ReimpresionPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_ReimpresionPedido_actionPerformed(e);
            }
        });
        mnuEconoFar_Caja.add(mnuCaja_ReimpresionPedido);
        mnuEconoFar_Caja.add(mnuCaja_RegulaVtaManual);
        mnuEconoFar_Caja.add(mnuCaja_Utilitarios);
        mnuCaja_Utilitarios.setText("8. Utilitarios");
        mnuCaja_Utilitarios.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Utilitarios.setMnemonic('7');
        mnuCaja_PruebaImpresora.setText("1. Prueba Impresora");
        mnuCaja_PruebaImpresora.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_PruebaImpresora.setMnemonic('1');
        mnuCaja_PruebaImpresora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_PruebaImpresora_actionPerformed(e);
            }
        });
        mnuCaja_Pinpad.setText("2. Prueba Pinpad");
        mnuCaja_Pinpad.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Pinpad_Visa.setText("1. Oper. No Financieras Visa");
        mnuCaja_Pinpad_Visa.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Pinpad_Visa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Pinpad_Visa_actionPerformed(e);
            }
        });
        mnuCaja_Pinpad_Mastercard.setText("2. Cierre Mastercard");
        mnuCaja_Pinpad_Mastercard.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Pinpad_Mastercard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Pinpad_Mastercard_actionPerformed(e);
            }
        });
        mnuCaja_reporDet_Pinpad_MC.setText("3. Reporte Detallado Mastercard");
        mnuCaja_reporDet_Pinpad_MC.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_reporDet_Pinpad_MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_reporDet_Pinpad_MC_actionPerformed(e);
            }
        });
        mnuCaja_reporTot_Pinpad_MC.setText("4. Reporte Total Mastercard");
        mnuCaja_reporTot_Pinpad_MC.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_reporTot_Pinpad_MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_reporTot_Pinpad_MC_actionPerformed(e);
            }
        });
        mnuCaja_Reimpresion_Pinpad_MC.setText("5. Reimpresi�n Voucher Mastercard");
        mnuCaja_Reimpresion_Pinpad_MC.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Reimpresion_Pinpad_MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Reimpresion_Pinpad_MC_actionPerformed(e);
            }
        });
        mnuCaja_AnulacionTran_Pinpad_Visa.setText("6. Anulaci�n Transacci�n Pinpad");
        mnuCaja_AnulacionTran_Pinpad_Visa.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_AnulacionTran_Pinpad_Visa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_AnulacionTran_Pinpad_Visa_actionPerformed(e);
            }
        });
        mnuCaja_Pinpad.add(mnuCaja_Pinpad_Visa);
        mnuCaja_Pinpad.add(mnuCaja_Pinpad_Mastercard);
        mnuCaja_Pinpad.add(mnuCaja_reporDet_Pinpad_MC);
        mnuCaja_Pinpad.add(mnuCaja_reporTot_Pinpad_MC);
        mnuCaja_Pinpad.add(mnuCaja_Reimpresion_Pinpad_MC);
        mnuCaja_Pinpad.add(mnuCaja_AnulacionTran_Pinpad_Visa);
        mnuCaja_Pinpad.add(mnCaja_ReimpresionLote_Pinpad_MC);
        mnuCaja_Utilitarios.add(mnuCaja_PruebaImpresora);
        mnuCaja_Utilitarios.add(mnuCaja_Pinpad);
        mnuCaja_Utilitarios.add(mnuCaja_AbrirGabeta);
        mnuCaja_Utilitarios.add(mnuCaja_ServiciosVisa);
        mnuCaja_AbrirGabeta.setText("3. Abrir Gabeta");
        mnuCaja_AbrirGabeta.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_AbrirGabeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_AbrirGabeta_actionPerformed(e);
            }
        });
        mnuCaja_ServiciosVisa.setText("4. Servicios Visa");
        mnuCaja_ServiciosVisa.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_ServiciosVisa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_ServiciosVisa_actionPerformed(e);
            }
        });
        mnuAdm_mntoCli.setText("10. Cliente Fidelizado");
        mnuAdm_mntoCli.setFont(new Font("SansSerif", 0, 11));
        mnuAdm_mntoCli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuAdm_mntoCli_actionPerformed(e);
            }
        });
        mnuInventario_BandejasDevolver.setText("4. Bandejas por Devolver");
        mnuInventario_BandejasDevolver.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_BandejasDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_BandejasDevolver_actionPerformed(e);
            }
        });
        mnuCaja_Raiz.setText("8. Financiera Raiz");
        mnuCaja_Raiz.setDisplayedMnemonicIndex(0);
        mnuCaja_Raiz.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Raiz.setMnemonic('8');
        mnuCaja_Raiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Raiz_actionPerformed(e);
            }
        });
        mnuCaja_Incasur.setText("9. Caja Incasur");
        mnuCaja_Incasur.setDisplayedMnemonicIndex(0);
        mnuCaja_Incasur.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Incasur.setMnemonic('8');
        mnuCaja_Incasur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Incasur_actionPerformed(e);
            }
        });

        mnuCaja_ReimpresionTicketsAnulados.setText("9. Reimpresi\u00f3n de tickets anulados");
        mnuCaja_ReimpresionTicketsAnulados.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_ReimpresionTicketsAnulados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_ReimpresionTicketsAnulados_actionPerformed(e);
            }
        });
        mnuEconoFar_Caja.add(mnuCaja_ReimpresionTicketsAnulados);
        mnuCaja_ControlSobresParciales.setText("10. Control Sobres Parciales");
        mnuCaja_ControlSobresParciales.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_ControlSobresParciales.setEnabled(false);
        mnuCaja_ControlSobresParciales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_ControlSobresParciales_actionPerformed(e);
            }
        });
        mnuEconoFar_Caja.add(mnuCaja_ControlSobresParciales);
        mnuCaja_Recaudacion.setText("11.Pago de Terceros");
        mnuCaja_Recaudacion.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_Caja.add(mnuCaja_Recaudacion);
        mnuEconoFar_Caja.add(mnuCaja_PedidoPinpad);

        mnuEconoFar_Caja.add(mnuCaja_IRS_Tesoreria);
        mnuCaja_CMR.setText("1. CMR");
        mnuCaja_CMR.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_CMR.setMnemonic('1');
        mnuCaja_CMR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_CMR_actionPerformed(e);
            }
        });
        mnuCaja_Recaudacion.add(mnuCaja_CMR);
        mnuCaja_Citibank.setText("2. Citibank");
        mnuCaja_Citibank.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Citibank.setMnemonic('2');
        mnuCaja_Citibank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem5_actionPerformed(e);
            }
        });
        mnuCaja_Recaudacion.add(mnuCaja_Citibank);
        mnuCaja_Claro.setText("3. Claro");
        mnuCaja_Claro.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Claro.setMnemonic('3');
        mnuCaja_Claro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Claro_actionPerformed(e);
            }
        });
        mnuCaja_Recaudacion.add(mnuCaja_Claro);
        mnuCaja_Prestamos_Citibank.setText("4. Prestamos Citibank");
        mnuCaja_Prestamos_Citibank.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Prestamos_Citibank.setMnemonic('4');
        mnuCaja_Prestamos_Citibank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Prestamos_Citibank_actionPerformed(e);
            }
        });
        //GFonseca 20.09.2013 - opcion de recaudacion Ripley
        mnuCaja_Recaudacion.add(mnuCaja_Prestamos_Citibank);
        mnuCaja_Ripley.setText("5. Ripley");
        mnuCaja_Ripley.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Ripley.setMnemonic('5');
        mnuCaja_Ripley.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Ripley_actionPerformed(e);
            }
        });
        mnuCaja_Recaudacion.add(mnuCaja_Ripley);

        mnuCaja_Registro_Recaudaciones.setText("6. Registro de pagos");
        mnuCaja_Registro_Recaudaciones.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Registro_Recaudaciones.setMnemonic('6');
        mnuCaja_Registro_Recaudaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Registro_Recaudaciones_actionPerformed(e);
            }
        });
        mnuCaja_Recaudacion.add(mnuCaja_Registro_Recaudaciones);

        mnuCaja_Registro_VentaCMR.setText("7. Registro Venta CMR");
        mnuCaja_Registro_VentaCMR.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Registro_VentaCMR.setMnemonic('7');
        mnuCaja_Registro_VentaCMR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Registro_Venta_CMR_actionPerformed(e);
            }
        });
        mnuCaja_Recaudacion.add(mnuCaja_Registro_VentaCMR);

        // MODULO INVENTARIO
        mnuCaja_Recaudacion.add(mnuCaja_Raiz);
        mnuCaja_Recaudacion.add(mnuCaja_Incasur);
        mnuCaja_Recaudacion.add(mnuCaja_Financiero);
        mnuEconoFar_Inventario.setText("Inventario");
        mnuEconoFar_Inventario.setMnemonic('i');
        mnuEconoFar_Inventario.setFont(new Font("SansSerif", 0, 11));
        mnuPtoVenta.add(mnuEconoFar_Inventario);
        mnuInventario_GuiaEntrada.setText("1. Guia de Ingreso");
        mnuInventario_GuiaEntrada.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_GuiaEntrada.setMnemonic('1');
        mnuInventario_GuiaEntrada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_GuiaEntrada_actionPerformed(e);
            }
        });
        mnuEconoFar_Inventario.add(mnuInventario_GuiaEntrada);
        mnuInventario_Kardex.setText("2. Kardex");
        mnuInventario_Kardex.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Kardex.setMnemonic('2');
        mnuInventario_Kardex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Kardex_actionPerformed(e);
            }
        }); //rherrera 25.09.2014

        /*  mnuKardex_Kardex.setText("1. Productos para venta");
         mnuKardex_Kardex.setFont(new Font("SansSerif", 0, 11));
         mnuKardex_Kardex.setMnemonic('1');
         mnuKardex_Kardex.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                            mnuInventario_Kardex_actionPerformed(e);
                         }
                     });
          */
        mnuKardex_ConsumoInter.setText("11. Productos de insumos");
        mnuKardex_ConsumoInter.setFont(new Font("SansSerif", 0, 11));
        mnuKardex_ConsumoInter.setMnemonic('2');
        mnuKardex_ConsumoInter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReposicion_Insumo_actionPerformed(e);
            }
        });
        //--------------------------------------------------------------

        mnuEconoFar_Inventario.add(mnuInventario_Kardex);
        //mnuInventario_Kardex.add(mnuKardex_Kardex);         //05.08.2014
        //mnuInventario_Kardex.add(mnuKardex_ConsumoInter);   //05.08.2014


        mnuInventario_Transferencias.setText("3. Transferencias");
        mnuInventario_Transferencias.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Transferencias.setMnemonic('3');
        mnuEconoFar_Inventario.add(mnuInventario_Transferencias);
        mnuInventario_Transf_local.setText("1. Transferencias Local");
        mnuInventario_Transf_local.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Transf_local.setMnemonic('1');
        mnuInventario_Transf_local.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Transf_local_actionPerformed(e);
            }
        });
        mnuInventario_Transferencias.add(mnuInventario_Transf_local);
        mnuInventario_Transf_delivery.setText("2. Transferencias Delivery");
        mnuInventario_Transf_delivery.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Transf_delivery.setMnemonic('2');
        mnuInventario_Transf_delivery.setEnabled(true);
        mnuInventario_Transf_delivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Transf_delivery_actionPerformed(e);
            }
        });
        mnuInventario_Transferencias.add(mnuInventario_Transf_delivery);
        mnuInventario_Transf_guias.setText("3. Guias de Salida");
        mnuInventario_Transf_guias.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Transf_guias.setMnemonic('3');
        mnuInventario_Transf_guias.setEnabled(true);
        mnuInventario_Transf_guias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Transf_guias_actionPerformed(e);
            }
        });
        mnuInventario_Transferencias.add(mnuInventario_Transf_guias);
        mnuInventario_Mercaderia.setText("4. Recepcion de Productos");
        mnuInventario_Mercaderia.setDisplayedMnemonicIndex(0);
        mnuInventario_Mercaderia.setMnemonic('4');
        mnuInventario_Mercaderia.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Mercaderia.setFocusable(false);
        mnuEconoFar_Inventario.add(mnuInventario_Mercaderia);
        mnuInventario_Recepcion.setText("2. Recepcion Ciega ");
        mnuInventario_Recepcion.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Recepcion.setMnemonic('1');
        mnuInventario_Recepcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Recepcion_actionPerformed(e);
            }
        });
        mnuInventario_Mercaderia.add(mnuInventario_IngTransportista);
        mnuInventario_Mercaderia.add(mnuInventario_Recepcion);
        mnuInventario_RecepcionProductos.setText("3. Recepci\u00f3n de Almac\u00e9n");
        mnuInventario_RecepcionProductos.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_RecepcionProductos.setMnemonic('2');
        mnuInventario_RecepcionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem4_actionPerformed(e);
            }
        });
        mnuInventario_Mercaderia.add(mnuInventario_RecepcionProductos);
        mnuInventario_IngTransportista.setText("1. Ingreso Transportista");
        mnuInventario_IngTransportista.setMnemonic('3');
        mnuInventario_IngTransportista.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_IngTransportista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuIngTransportista_actionPerformed(e);
            }
        });
        mnuInventario_Mercaderia.add(mnuInventario_BandejasDevolver);
        mnuInventario_PedidosLocales.setText("5. Pedidos Locales");
        mnuInventario_PedidosLocales.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_PedidosLocales.setEnabled(true);
        mnuInventario_PedidosLocales.setMnemonic('4');
        mnuEconoFar_Inventario.add(mnuInventario_PedidosLocales);
        mnuInventario_PedidoReposicion.setText("1. Pedido Reposici�n");
        mnuInventario_PedidoReposicion.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_PedidoReposicion.setMnemonic('1');
        mnuInventario_PedidoReposicion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_PedidoReposicion_actionPerformed(e);
            }
        });
        mnuInventario_PedidosLocales.add(mnuInventario_PedidoReposicion);
        mnuInventario_PedidoAdicional.setText("2. PVM");
        mnuInventario_PedidoAdicional.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_PedidoAdicional.setMnemonic('1');
        mnuInventario_PedidoAdicional.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuPedidoAdicional_actionPerformed(e);
            }
        });
        mnuInventario_PedidosLocales.add(mnuInventario_PedidoAdicional);
        mnuInventario_PedidoEspecial.setText("3. Pedido Especial");
        mnuInventario_PedidoEspecial.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_PedidoEspecial.setMnemonic('1');
        mnuInventario_PedidoEspecial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuPedidoEspecial_actionPerformed(e);
            }
        });
        mnuInventario_PedidosLocales.add(mnuInventario_PedidoEspecial);
        /*** Incio CCASTILLO 10/10/2016 ***/
        mnuInventario_PedidoEspecial_Insumo.setText("4. Pedido Especial Insumos");
        mnuInventario_PedidoEspecial_Insumo.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_PedidoEspecial_Insumo.setMnemonic('1');
        mnuInventario_PedidoEspecial_Insumo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_PedidoEspecial_Insumo_actionPerformed(e);
            }
        });
        mnuInventario_PedidosLocales.add(mnuInventario_PedidoEspecial_Insumo);
        /*** Fin CCASTILLO 10/10/2016 ***/
        mnuInventario_PedidosLocales.add(mnuInventario_SolicitudFraccion);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mnuInventario_Guias.setText("6. Correccion Guias");
        mnuInventario_Guias.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Guias.setMnemonic('6');
        mnuInventario_Guias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Guias_actionPerformed(e);
            }
        });
        mnuEconoFar_Inventario.add(mnuInventario_Guias);
        mnuInventario_Ajustes.setText("7. Ajustes por Fecha");
        mnuInventario_Ajustes.setMnemonic('7');
        mnuInventario_Ajustes.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Ajustes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Ajustes_actionPerformed(e);
            }
        });
        mnuEconoFar_Inventario.add(mnuInventario_Ajustes);
        mnuInventario_RecepcionLocales.setText("8. Recepcion Locales");
        mnuInventario_RecepcionLocales.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_RecepcionLocales.setMnemonic('8');
        mnuInventario_RecepcionLocales.setEnabled(false);
        mnuInventario_RecepcionLocales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_RecepcionLocales_actionPerformed(e);
            }
        });
        mnuEconoFar_Inventario.add(mnuInventario_RecepcionLocales);
        mnuInventario_MercaderiaDirecta.setText("9. Mercaderia Directa");
        mnuInventario_MercaderiaDirecta.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_MercaderiaDirecta.setMnemonic('9');
        mnuEconoFar_Inventario.add(mnuInventario_MercaderiaDirecta);
        //mnuEconoFar_Inventario.add(prueba);
        mnuEconoFar_Inventario.add(mnuInventario_StockNegativo);
        //rherrera 19.09.2014 ajuste de insumos
        mnuEconoFar_Inventario.add(mnuKardex_ConsumoInter);

        if (BDCotizacionPrecio.getIndActivaCotizacionPrecio()) {
            mnuCotizacionPrecio.add(mnuCotiza_ConDocumento);
            mnuCotizacionPrecio.add(mnuCotiza_SinDocumento);
            mnuCotizacionPrecio.add(mnuCotiza_SinSolicitud); //LTAVARA 2017.09.12
            mnuEconoFar_Inventario.add(mnuCotizacionPrecio);
        }
        mnuEconoFar_Inventario.add(jMenuItem2);
        mnuInventario_RecepcionMerDirec.setText("1. Recepcion");
        mnuInventario_RecepcionMerDirec.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_RecepcionMerDirec.setMnemonic('1');
        mnuInventario_RecepcionMerDirec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_RecepcionMerDirec_actionPerformed(e);
            }
        });
        mnuInventario_MercaderiaDirecta.add(mnuInventario_RecepcionMerDirec);
        mnuInventario_Devolucion.setText("2. Devolucion");
        mnuInventario_Devolucion.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Devolucion.setMnemonic('2');
        mnuInventario_Devolucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Devolucion_actionPerformed(e);
            }
        });
        mnuInventario_MercaderiaDirecta.add(mnuInventario_Devolucion);


        mnuInventario_DevolucionSobrante.setText("3. Devoluci�n X Sobrante");
        mnuInventario_DevolucionSobrante.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_DevolucionSobrante.setMnemonic('3');
        mnuInventario_DevolucionSobrante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_DevolucionSobrante_actionPerformed(e);
            }
        });
        mnuInventario_MercaderiaDirecta.add(mnuInventario_DevolucionSobrante);


        // MODULO TOMA INVENTARIO
        //mnuInventario_MercaderiaDirecta.add(mnuInventario_RecepcionRM);
        mnuEconoFar_TomaInventario.setText("Toma Inventario");
        mnuEconoFar_TomaInventario.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_TomaInventario.setMnemonic('t');
        mnuPtoVenta.add(mnuEconoFar_TomaInventario);
        mnuTomaInventario_Tradicional.setText("1. Tradicional");
        mnuTomaInventario_Tradicional.setFont(new Font("SansSerif", 0, 11));
        mnuTomaInventario_Tradicional.setMnemonic('1');
        mnuEconoFar_TomaInventario.add(mnuTomaInventario_Tradicional);
        mnuInventario_Inicio.setText("1.Crear Toma");
        mnuInventario_Inicio.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Inicio.setMnemonic('1');
        mnuInventario_Inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Inicio_actionPerformed(e);
            }
        });
        mnuTomaInventario_Tradicional.add(mnuInventario_Inicio);
        mnuInventario_Toma.setText("2. Toma");
        mnuInventario_Toma.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_Toma.setMnemonic('2');
        mnuInventario_Toma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_Toma_actionPerformed(e);
            }
        });
        mnuTomaInventario_Tradicional.add(mnuInventario_Toma);
        mnuTomaInventario_Ciclico.setText("2. C�clico");
        mnuTomaInventario_Ciclico.setFont(new Font("SansSerif", 0, 11));
        mnuTomaInventario_Ciclico.setMnemonic('2');
        mnuEconoFar_TomaInventario.add(mnuTomaInventario_Ciclico);
        mnuInventarioCiclico_Inicio.setText("1. Inicio");
        mnuInventarioCiclico_Inicio.setFont(new Font("SansSerif", 0, 11));
        mnuInventarioCiclico_Inicio.setMnemonic('1');
        mnuInventarioCiclico_Inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventarioCiclico_Inicio_actionPerformed(e);
            }
        });
        mnuTomaInventario_Ciclico.add(mnuInventarioCiclico_Inicio);
        mnuInventarioCiclico_Toma.setText("2. Toma");
        mnuInventarioCiclico_Toma.setFont(new Font("SansSerif", 0, 11));
        mnuInventarioCiclico_Toma.setMnemonic('2');
        mnuInventarioCiclico_Toma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventarioCiclico_Toma_actionPerformed(e);
            }
        });
        mnuTomaInventario_Ciclico.add(mnuInventarioCiclico_Toma);
        mnuTomaInventario_Diario.setText("3. Diario");
        mnuTomaInventario_Diario.setFont(new Font("SansSerif", 0, 11));
        mnuTomaInventario_Diario.setMnemonic('3');
        mnuEconoFar_TomaInventario.add(mnuTomaInventario_Diario);
        mnuInventarioDiario_Toma.setText("1. Toma");
        mnuInventarioDiario_Toma.setFont(new Font("SansSerif", 0, 11));
        mnuInventarioDiario_Toma.setMnemonic('1');
        mnuInventarioDiario_Toma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventarioDiario_Toma_actionPerformed(e);
            }
        });
        mnuTomaInventario_Diario.add(mnuInventarioDiario_Toma);
        mnuInventarioDiario_Diferencia.setText("2. Diferencia");
        mnuInventarioDiario_Diferencia.setFont(new Font("SansSerif", 0, 11));
        mnuInventarioDiario_Diferencia.setMnemonic('2');
        mnuInventarioDiario_Diferencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventarioDiario_Diferencia_actionPerformed(e);
            }
        });
        mnuTomaInventario_Diario.add(mnuInventarioDiario_Diferencia);
        mnuTomaInventario_Historico.setText("4. Historico");
        mnuTomaInventario_Historico.setFont(new Font("SansSerif", 0, 11));
        mnuTomaInventario_Historico.setMnemonic('4');
        mnuTomaInventario_Historico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuTomaInventario_Historico_actionPerformed(e);
            }
        });
        mnuEconoFar_TomaInventario.add(mnuTomaInventario_Historico);
        mnuTomaInVentario_ItemsXLab.setText("5. Items por laboratorio");
        mnuTomaInVentario_ItemsXLab.setFont(new Font("SansSerif", 0, 11));
        mnuTomaInVentario_ItemsXLab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuTomaInVentario_ItemsXLab_actionPerformed(e);
            }
        });
        mnuEconoFar_TomaInventario.add(mnuTomaInVentario_ItemsXLab);
        mnuGenerarData.setText("6. Configurar PDA");
        mnuGenerarData.setFont(new Font("SansSerif", 0, 11));
        mnuGenerarData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    mnuGenerarData_actionPerformed(e);
                } catch (SQLException f) {
                    f.getMessage();
                }
            }
        });
        mnuEconoFar_TomaInventario.add(mnuGenerarData);

        // MODULO ADMINISTRACION
        mnuEconoFar_Administracion.setText("Administracion");
        mnuEconoFar_Administracion.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_Administracion.setMnemonic('a');
        mnuEconoFar_Administracion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuAdministracion_Mov_actionPerformed(e);
            }
        });
        mnuPtoVenta.add(mnuEconoFar_Administracion);
        mnuAdministracion_Usuarios.setText("1. Usuarios");
        mnuAdministracion_Usuarios.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_Usuarios.setMnemonic('1');
        mnuEconoFar_Administracion.add(mnuAdministracion_Usuarios);
        mnuUsuarios_CambioUsuario.setText("1. Cambiar de Usuario");
        mnuUsuarios_CambioUsuario.setFont(new Font("SansSerif", 0, 11));
        mnuUsuarios_CambioUsuario.setMnemonic('1');
        mnuUsuarios_CambioUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuUsuarios_CambioUsuario_actionPerformed(e);
            }
        });
        mnuAdministracion_Usuarios.add(mnuUsuarios_CambioUsuario);
        mnuUsuarios_CambioClave.setText("2. Modificar mi Clave");
        mnuUsuarios_CambioClave.setFont(new Font("SansSerif", 0, 11));
        mnuUsuarios_CambioClave.setMnemonic('2');
        mnuUsuarios_CambioClave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuUsuarios_CambioClave_actionPerformed(e);
            }
        });
        mnuAdministracion_Usuarios.add(mnuUsuarios_CambioClave);
        mnuAdministracion_Mantenimiento.setText("2. Mantenimiento");
        mnuAdministracion_Mantenimiento.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_Mantenimiento.setMnemonic('2');
        mnuEconoFar_Administracion.add(mnuAdministracion_Mantenimiento);
        mnuMantenimiento_Usuarios.setText("1. Usuarios");
        mnuMantenimiento_Usuarios.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_Usuarios.setMnemonic('1');
        mnuMantenimiento_Usuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_Usuarios_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_Usuarios);
        mnuMantenimiento_Cajas.setText("2. Cajas");
        mnuMantenimiento_Cajas.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_Cajas.setMnemonic('2');
        mnuMantenimiento_Cajas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_Cajas_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_Cajas);
        mnuMantenimiento_Impresoras.setText("3. Impresoras");
        mnuMantenimiento_Impresoras.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_Impresoras.setMnemonic('3');
        mnuMantenimiento_Impresoras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_Impresoras_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_Impresoras);
        mnuMantenimiento_Clientes.setText("4. Clientes");
        mnuMantenimiento_Clientes.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_Clientes.setMnemonic('4');
        mnuMantenimiento_Clientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_Clientes_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_Clientes);
        mnuMantenimiento_Parametros.setText("5. Parametros");
        mnuMantenimiento_Parametros.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_Parametros.setMnemonic('5');
        mnuMantenimiento_Parametros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_Parametros_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_Parametros);
        mnuMantenimiento_Carne.setText("6. Carn� Sanidad");
        mnuMantenimiento_Carne.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_Carne.setMnemonic('6');
        mnuMantenimiento_Carne.setEnabled(false);
        mnuMantenimiento_Carne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_Carne_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_Carne);
        mnuMantenimiento_MaquinaIP.setText("7. Maquina - IP");
        mnuMantenimiento_MaquinaIP.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_MaquinaIP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_MaquinaIP_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_MaquinaIP);
        mnuMantenimiento_ImpresoraTermica.setText("8. Impresoras T�rmicas");
        mnuMantenimiento_ImpresoraTermica.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_ImpresoraTermica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_ImpresoraTermica_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_ImpresoraTermica);
        mnuMantenimiento_RolTemporal.setText("9. Administrador Temporal");
        mnuMantenimiento_RolTemporal.setFont(new Font("SansSerif", 0, 11));
        mnuMantenimiento_RolTemporal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMantenimiento_RolTemporal_actionPerformed(e);
            }
        });
        mnuAdministracion_Mantenimiento.add(mnuMantenimiento_RolTemporal);
        mnuAdministracion_Mantenimiento.add(mnuAdm_mntoCli);
        mnuAdministracion_MovCaja.setText("3. Movimientos Caja");
        mnuAdministracion_MovCaja.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_MovCaja.setMnemonic('3');
        mnuEconoFar_Administracion.add(mnuAdministracion_MovCaja);
        mnuMovCaja_RegistrarVentas.setText("Registrar Ventas");
        mnuMovCaja_RegistrarVentas.setFont(new Font("SansSerif", 0, 11));
        mnuMovCaja_RegistrarVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMovCaja_RegistrarVentas_actionPerformed(e);
            }
        });
        mnuAdministracion_MovCaja.add(mnuMovCaja_RegistrarVentas);
        mnuMovCaja_ConsultarMov.setText("Consultar Movimientos");
        mnuMovCaja_ConsultarMov.setFont(new Font("SansSerif", 0, 11));
        mnuMovCaja_ConsultarMov.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuMovCaja_ConsultarMov_actionPerformed(e);
            }
        });
        mnuAdministracion_MovCaja.add(mnuMovCaja_ConsultarMov);
        mnuAdministracion_RegViajero.setText("4. Regenerar Viajero");
        mnuAdministracion_RegViajero.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_RegViajero.setMnemonic('4');
        mnuAdministracion_RegViajero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuAdministracion_RegViajero_actionPerformed(e);
            }
        });
        mnuEconoFar_Administracion.add(mnuAdministracion_RegViajero);
        mnuAdministracion_ControlHoras.setText("5. Control de Horas");
        mnuAdministracion_ControlHoras.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_ControlHoras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuAdministracion_ControlHoras_actionPerformed(e);
            }
        });
        mnuEconoFar_Administracion.add(mnuAdministracion_ControlHoras);
        mnuAdministracion_Otros.setText("6. Otros");
        mnuAdministracion_Otros.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_Otros.setMnemonic('5');
        mnuEconoFar_Administracion.add(mnuAdministracion_Otros);
        mnuOtros_MantProbisa.setText("1. Mant. PROBISA");
        mnuOtros_MantProbisa.setFont(new Font("SansSerif", 0, 11));
        mnuOtros_MantProbisa.setMnemonic('1');
        mnuOtros_MantProbisa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuOtros_MantProbisa_actionPerformed(e);
            }
        });
        mnuAdministracion_Otros.add(mnuOtros_MantProbisa);
        mnuAdministracion_FondoSencillo.setText("7. Fondo de Sencillo");
        mnuAdministracion_FondoSencillo.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_FondoSencillo.setMnemonic('7');
        mnuAdministracion_FondoSencillo.setVisible(false);
        mnuAdministracion_FondoSencillo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuFondoSencillo_actionPerformed(e);
            }
        });
        mnuEconoFar_Administracion.add(mnuAdministracion_FondoSencillo);
        mnuAdministracion_ProdCambiados.setText("8. Producto");
        mnuAdministracion_ProdCambiados.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_Administracion.add(mnuAdministracion_ProdCambiados);
        mnuProdCambiados_PrecioCambProd.setText("1. Precios cambiado");
        mnuProdCambiados_PrecioCambProd.setFont(new Font("SansSerif", 0, 11));
        mnuProdCambiados_PrecioCambProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuPrecioCambProd_actionPerformed(e);
            }
        });
        mnuAdministracion_ProdCambiados.add(mnuProdCambiados_PrecioCambProd);
        //01.10.2013 CVILCA
        mnuProductos_Impresion.setText("2. Impresi�n de Stickers");
        mnuProductos_Impresion.setFont(new Font("SansSerif", 0, 11));
        mnuProductos_Impresion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuProdImpresion_actionPerformed(e);
            }
        });
        mnuAdministracion_ProdCambiados.add(mnuProductos_Impresion);
        mnuAdministracion_PresupuestoVentas.setText("9. Presupuesto de Ventas");
        mnuAdministracion_PresupuestoVentas.setFont(new Font("SansSerif", 0, 11));
        mnuAdministracion_PresupuestoVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuAdministracion_PresupuestoVentas_actionPerformed(e);
            }
        });
        mnuEconoFar_Administracion.add(mnuAdministracion_PresupuestoVentas);

        // MODULO REPORTES
        mnuEconoFar_Reportes.setText("Reportes");
        mnuEconoFar_Reportes.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_Reportes.setMnemonic('r');
        mnuPtoVenta.add(mnuEconoFar_Reportes);
        mnuReportes_RegistroVentas.setText("1. Registro de Ventas");
        mnuReportes_RegistroVentas.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_RegistroVentas.setMnemonic('1');
        mnuReportes_RegistroVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem1_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_RegistroVentas);
        mnuReportes_VentasVendedor.setText("2. Ventas por vendedor");
        mnuReportes_VentasVendedor.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasVendedor.setMnemonic('2');
        mnuEconoFar_Reportes.add(mnuReportes_VentasVendedor);
        mnuReportes_VentasVendedor_Total.setText("1. Ventas Totales (sin Mayorista)");
        mnuReportes_VentasVendedor_Total.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasVendedor_Total.setMnemonic('1');
        mnuReportes_VentasVendedor_Total.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasVendedor_Total_actionPerformed(e);
            }

        });
        mnuReportes_VentasVendedor.add(mnuReportes_VentasVendedor_Total);
        mnuReportes_VentasVendedor_Mezon.setText("2. Ventas Meson");
        mnuReportes_VentasVendedor_Mezon.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasVendedor_Mezon.setMnemonic('2');
        mnuReportes_VentasVendedor_Mezon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasVendedor_Mezon_actionPerformed(e);
            }
        });
        mnuReportes_VentasVendedor.add(mnuReportes_VentasVendedor_Mezon);
        mnuReportes_VentasVendedor_Delivery.setText("3. Ventas Delivery");
        mnuReportes_VentasVendedor_Delivery.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasVendedor_Delivery.setMnemonic('3');
        mnuReportes_VentasVendedor_Delivery.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasVendedor_Delivery_actionPerformed(e);
            }


        });
        mnuReportes_VentasVendedor.add(mnuReportes_VentasVendedor_Delivery);
        mnuReportes_VentasVendedor_Institucional.setText("4. Ventas Mayorista");
        mnuReportes_VentasVendedor_Institucional.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasVendedor_Institucional.setMnemonic('4');
        mnuReportes_VentasVendedor_Institucional.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasVendedor_Institucional_actionPerformed(e);
            }


        });
        mnuReportes_VentasVendedor.add(mnuReportes_VentasVendedor_Institucional);

        mnuReportes_VentasVendedor_Gigante.setText("5. Concurso Garantiman�a");
        mnuReportes_VentasVendedor_Gigante.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasVendedor_Gigante.setMnemonic('5');
        mnuReportes_VentasVendedor_Gigante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReporteConcursoGigante(e);
            }
        });
        mnuReportes_VentasVendedor.add(mnuReportes_VentasVendedor_Gigante);

        // INI AOVIEDO 17/04/2017
        mnuReportes_VentasVendedor_Garantizados.setText("6. Concurso #Garantiman�a");
        mnuReportes_VentasVendedor_Garantizados.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasVendedor_Garantizados.setMnemonic('6');
        mnuReportes_VentasVendedor_Garantizados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReporteConcursoGarantizados(e);
            }
        });
        mnuInventario_SolicitudFraccion.setText("5. Solicitar Fraccionamiento");
        mnuInventario_SolicitudFraccion.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_SolicitudFraccion.setMnemonic('5');
        mnuInventario_SolicitudFraccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_SolicitudFraccion_ActionPerformed(e);
            }
        });
        mnuCotizacionPrecio.setText("12. Cotizacion por Competencia");
        mnuCotizacionPrecio.setFont(new Font("SansSerif", 0, 11));
        mnuCotiza_ConDocumento.setText("1. Compra");
        mnuCotiza_ConDocumento.setFont(new Font("SansSerif", 0, 11));
        mnuCotiza_ConDocumento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCotiza_ConDocumento(e);
            }
        });
        mnuCotiza_SinDocumento.setText("2. Cotizacion");
        mnuCotiza_SinDocumento.setFont(new Font("SansSerif", 0, 11));
        mnuCotiza_SinDocumento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCotiza_SinDocumento(e);
            }
        });
        mnuCotiza_SinSolicitud.setText("3. Sin Solicitud");
        mnuCotiza_SinSolicitud.setFont(new Font("SansSerif", 0, 11));
        mnuCotiza_SinSolicitud.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCotiza_SinSolicitud(e);
            }
        });
        //mnuReportes_VentasVendedor.add(mnuReportes_VentasVendedor_Garantizados); //Se deja de mostrar por reemplazo de Concurso Gigante AOVIEDO 04/10/2017
        // FIN AOVIEDO 17/04/2017

        jMenuItem2.setText("13. Registro de Mermas");
        jMenuItem2.setFont(new Font("SansSerif", 0, 11));
        jMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem2_actionPerformed(e);
            }
        });
        boolean vEnabled_IRS_Tesoreria = UtilityPtoVenta.get_habilita_Tesoreria();
        mnuCaja_IRS_Tesoreria.setEnabled(vEnabled_IRS_Tesoreria);
        mnuCaja_IRS_Tesoreria.setText("13. IRS Web Tesoreria");
        mnuCaja_IRS_Tesoreria.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_IRS_Tesoreria.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    IRS_Tesoreria_ActionPerformed(e);
                }
            });
        //INI JMONZALVE 24042019
        mnuCobro_Responsabilidad.setText("18. Cobro por Responsabilidad");
        mnuCobro_Responsabilidad.setFont(new Font("SansSerif", 0, 11));
        mnuCobro_Responsabilidad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mnuCobro_Responsabilidad_actionPerformed(e);
                }
            });
        
        mnuVentas_UploadCobro_Responsabilidad.setFont(new Font("SansSerif", 0, 11));
        mnuVentas_UploadCobro_Responsabilidad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mnuVentas_UploadCobro_Responsabilidad_actionPerformed(e);
                }
            });
        //FIN JMONZALVE 24042019
        mnuReportes_DetalleVentas.setText("3. Detalle de ventas");
        mnuReportes_DetalleVentas.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_DetalleVentas.setMnemonic('3');
        mnuReportes_DetalleVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_DetalleVentas_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_DetalleVentas);
        mnuReportes_ResumenVentaDia.setText("4. Ventas resumen por d�a");
        mnuReportes_ResumenVentaDia.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_ResumenVentaDia.setMnemonic('4');
        mnuReportes_ResumenVentaDia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_ResumenVentaDia_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_ResumenVentaDia);
        mnuReportes_VentasProducto.setText("5. Ventas por producto");
        mnuReportes_VentasProducto.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasProducto.setMnemonic('5');
        mnuReportes_VentasProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasProducto_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_VentasProducto);
        mnuReportes_VentasConvenio.setText("6. Ventas por convenio");
        mnuReportes_VentasConvenio.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasConvenio.setMnemonic('6');
        mnuReportes_VentasConvenio_Reporte.setText("1. Reporte Convenios");
        mnuReportes_VentasConvenio_Reporte.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasConvenio_Reporte.setMnemonic('1');
        mnuReportes_VentasConvenio_Reporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasConvenio_Reporte_actionPerformed(e);
            }
        });
        mnuReportes_VentasConvenio_Liquidacion.setText("2. Liquidacion Convenios");
        mnuReportes_VentasConvenio_Liquidacion.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasConvenio_Liquidacion.setMnemonic('2');
        mnuReportes_VentasConvenio_Liquidacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasConvenio_Liquidacion_actionPerformed(e);
            }
        });
        mnuReportes_VentasConvenio.add(mnuReportes_VentasConvenio_Reporte);
        mnuReportes_VentasConvenio.add(mnuReportes_VentasConvenio_Liquidacion);
        mnuEconoFar_Reportes.add(mnuReportes_VentasConvenio);
        mnuReportes_VentasLinea.setText("7. Ventas por l�nea");
        mnuReportes_VentasLinea.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasLinea.setMnemonic('7');
        mnuReportes_VentasLinea.setEnabled(false);
        mnuEconoFar_Reportes.add(mnuReportes_VentasLinea);
        mnuReportes_VentasHora.setText("8. Ventas por hora");
        mnuReportes_VentasHora.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasHora.setMnemonic('8');
        mnuReportes_VentasHora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasHora_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_VentasHora);
        mnuReportes_VentasDiaMes.setText("9. Ventas por d�a del mes");
        mnuReportes_VentasDiaMes.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_VentasDiaMes.setMnemonic('9');
        mnuReportes_VentasDiaMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_VentasDiaMes_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_VentasDiaMes);
        mnuReportes_FaltaCero.setText("10. Productos Falta Cero");
        mnuReportes_FaltaCero.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_FaltaCero.setMnemonic('0');
        mnuReportes_FaltaCero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_FaltaCero_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_FaltaCero);
        mnuReportes_ProductosABC.setText("11. Productos ABC");
        mnuReportes_ProductosABC.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_ProductosABC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_ProductosABC_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_ProductosABC);
        mnuReportes_UnidVtaLocal.setText("12. Unidad Venta Local");
        mnuReportes_UnidVtaLocal.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_UnidVtaLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_UnidVtaLocal_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_UnidVtaLocal);
        mnuReportes_ProdSinVtaNDias.setText("13. Productos Sin Ventas en 90 Dias");
        mnuReportes_ProdSinVtaNDias.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_ProdSinVtaNDias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_ProdSinVtaNDias_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_ProdSinVtaNDias);
        mnuReportes_ListPsicotropicos.setText("14. Registro de Psicotropicos");
        mnuReportes_ListPsicotropicos.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_ListPsicotropicos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_ListPsicotropicos_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_ListPsicotropicos);
        mnuReportes_ListRecetario.setText("15. Kardex de Recetario");
        mnuReportes_ListRecetario.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_ListRecetario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_ListRecetario_actionPerformed(e);
            }
        });
        mnuCE_ReprocesaConcil.setText("5. Reprocesar conciliaciones");
        mnuCE_ReprocesaConcil.setFont(new Font("SansSerif", 0, 11));
        mnuCE_ReprocesaConcil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCE_ReprocesaConcil_actionPerformed(e);
            }
        });
        mnuInventario_RecepcionRM.setText("3. Recepcion Recetario Magistral");
        mnuInventario_RecepcionRM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_RecepcionRM_actionPerformed(e);
            }
        });
        prueba.setText("prueba impresion guia");
        prueba.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prueba_actionPerformed(e);
            }
        });

        mnuInventario_StockNegativo.setText("10.Stock Negativo");
        mnuInventario_StockNegativo.setFont(new Font("SansSerif", 0, 11));
        mnuInventario_StockNegativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuInventario_StockNegativo_actionPerformed(e);
            }
        });

        mnuCaja_PedidoPinpad.setText("12.Pedidos pagados con Pinpad");
        mnuCaja_PedidoPinpad.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_PedidoPinpad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_PedidoPinpad_actionPerformed(e);
            }
        });

        mnuReportes_ListGuias.setText("16. Reporte de Gu�as ");
        mnuReportes_ListGuias.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_ListGuias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_ListGuias_actionPerformed(e);
            }
        });
        mnuCaja_RegulaVtaManual.setText("7. Regularizaci\u00f3n Venta Manual");
        mnuCaja_RegulaVtaManual.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_RegulaVtaManual.setMnemonic('7');
        mnuCaja_RegulaVtaManual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_RegulaVtaManual_actionPerformed(e);
            }
        });
        jmnPuntos.setText("9. Puntos");
        jmnPuntos.setFont(new Font("SansSerif", 0, 11));
        jMenuItem1.setText(" 1. Consulta Saldos");
        jMenuItem1.setFont(new Font("SansSerif", 0, 11));
        jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saldoPuntos_actionPerformed(e);
            }
        });
        jmmRecueraPuntos.setText("2. Recuperaci�n Monedero");
        jmmRecueraPuntos.setFont(new Font("SansSerif", 0, 11));

        jmmRecueraPuntos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recuperaPuntos_actionPerformed(e);
            }
        });

        jmmBloqueoTarjeta.setText("3. Bloqueo Tarjeta");
        jmmBloqueoTarjeta.setFont(new Font("SansSerif", 0, 11));
        jmmBloqueoTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jmmBloqueoTarjeta_actionPerformed(e);
            }
        });


        jmmDesafiliacionx1.setText("4. Desafiliaci�n de Programa X + 1");
        jmmDesafiliacionx1.setFont(new Font("SansSerif", 0, 11));
        jmmDesafiliacionx1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jmmDesafiliacionx1_actionPerformed(e);
            }
        });

        mnuEconoFar_Reportes.add(mnuReportes_ListPsicotropicos);
        mnuEconoFar_Reportes.add(mnuReportes_ListRecetario);
        mnuEconoFar_Reportes.add(mnuReportes_ListGuias);

        mnuReportes_RegVtaDelivery.setText("17. Registro de Ventas Delivery");
        mnuReportes_RegVtaDelivery.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_RegVtaDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuReportes_RegVtaDelivery_actionPerformed(e);
            }
        });
        mnuEconoFar_Reportes.add(mnuReportes_RegVtaDelivery);

        // MODULO CAJA ELECTRONICA


        mnuEconoFar_Reportes.add(mnuCobro_Responsabilidad); //JMONZALVE 24042019
        mnuEconofar_CajaElectronica.setText("Caja Electronica");
        mnuEconofar_CajaElectronica.setMnemonic('E');
        mnuEconofar_CajaElectronica.setFont(new Font("SansSerif", 0, 11));
        mnuPtoVenta.add(mnuEconofar_CajaElectronica);
        mnuCE_CierreTurno.setText("1. Cierre Turno");
        mnuCE_CierreTurno.setMnemonic('1');
        mnuCE_CierreTurno.setFont(new Font("SansSerif", 0, 11));
        mnuCE_CierreTurno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCE_CierreTurno_actionPerformed(e);
            }
        });
        mnuEconofar_CajaElectronica.add(mnuCE_CierreTurno);
        mnuCE_CierreLocal.setText("2. Cierre Local");
        mnuCE_CierreLocal.setMnemonic('2');
        mnuCE_CierreLocal.setFont(new Font("SansSerif", 0, 11));
        mnuCE_CierreLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCE_CierreLocal_actionPerformed(e);
            }
        });
        mnuEconofar_CajaElectronica.add(mnuCE_CierreLocal);
        mnuCE_Prosegur.setText("3. Portavalor");
        mnuCE_Prosegur.setFont(new Font("SansSerif", 0, 11));
        mnuCE_Prosegur.setEnabled(false);
        mnuCE_Prosegur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCE_Prosegur_actionPerformed(e);
            }
        });
        mnuEconofar_CajaElectronica.add(mnuCE_Prosegur);

        //09.09.2013 wvillagomez
        mnuCE_PagoSencilloETV.setText("4. Fondo de Sencillo ETV");
        mnuCE_PagoSencilloETV.setFont(new Font("SansSerif", 0, 11));
        mnuCE_PagoSencilloETV.setMnemonic('4');
        mnuEconofar_CajaElectronica.add(mnuCE_PagoSencilloETV);
        mnuEconofar_CajaElectronica.add(mnuCE_ReprocesaConcil);
        mnuCE_ReciboSencillo.setText("1. Recibo de Sencillo");
        mnuCE_ReciboSencillo.setFont(new Font("SansSerif", 0, 11));
        mnuCE_ReciboSencillo.setMnemonic('1');
        mnuCE_ReciboSencillo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCE_ReciboSencillo_actionPerformed(e);
            }
        });
        mnuCE_PagoSencilloETV.add(mnuCE_ReciboSencillo);
        mnuCE_PagoSencillo.setText("2. Pago de Sencillo");
        mnuCE_PagoSencillo.setFont(new Font("SansSerif", 0, 11));
        mnuCE_PagoSencillo.setMnemonic('2');
        mnuCE_PagoSencillo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCE_PagoSencillo_actionPerformed(e);
            }
        });
        mnuCE_PagoSencilloETV.add(mnuCE_PagoSencillo);

        // MODULO CONTROL INGRESO
        mnuEconoFar_ControlIngreso.setText("Control de Ingreso");
        mnuEconoFar_ControlIngreso.setMnemonic('d');
        mnuEconoFar_ControlIngreso.setFont(new Font("SansSerif", 0, 11));
        mnuPtoVenta.add(mnuEconoFar_ControlIngreso);
        mnuIngreso.setText("1. Ingreso");
        mnuIngreso.setFont(new Font("SansSerif", 0, 11));
        mnuIngreso.setMnemonic('1');
        mnuIngreso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuIngreso_actionPerformed(e);
            }
        });
        mnuEconoFar_ControlIngreso.add(mnuIngreso);
        mnuIngresoTemperatura.setText("2. Ingreso Temperatura");
        mnuIngresoTemperatura.setFont(new Font("SansSerif", 0, 11));
        mnuIngresoTemperatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mmuIngresoTemperatura_actionPerformed(e);
            }
        });
        mnuEconoFar_ControlIngreso.add(mnuIngresoTemperatura);
        //CHUANES 28/04/2015
        mnuEconoFar_Salir.setText("Salir");
        mnuEconoFar_Salir.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_Salir.setMnemonic('s');
        mnuPtoVenta.add(mnuEconoFar_CM);
        mnuPtoVenta.add(mnuEconoFar_Salir);
        mnuSalir_SalirSistema.setText("Salir del Sistema");
        mnuSalir_SalirSistema.setFont(new Font("SansSerif", 0, 11));
        mnuSalir_SalirSistema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuSalir_SalirSistema_actionPerformed(e);
            }
        });
        mnuEconoFar_Salir.add(mnuSalir_SalirSistema);

        mnuEconoFar_Ayuda.setText("?");
        mnuEconoFar_Ayuda.setFont(new Font("SansSerif", 0, 11));
        mnuAyuda_AcercaDe.setText("Acerca de ...");
        mnuAyuda_AcercaDe.setFont(new Font("SansSerif", 0, 11));
        mnuAyuda_AcercaDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuAyuda_AcercaDe_actionPerformed(e);
            }
        });
        mnCaja_ReimpresionLote_Pinpad_MC.setText("7. Reimpresi�n Lote Mastercard");
        mnCaja_ReimpresionLote_Pinpad_MC.setFont(new Font("SansSerif", 0, 11));
        mnCaja_ReimpresionLote_Pinpad_MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnCaja_ReimpresionLote_Pinpad_MC_actionPerformed(e);
            }
        });
        /*mnuReportes_ListPsicotropicos.setText("14. Registro de Psicotropicos");
        mnuReportes_ListPsicotropicos.setFont(new Font("SansSerif", 0, 11));
        mnuReportes_ListPsicotropicos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mnuReportes_ListPsicotropicos_actionPerformed(e);
                }
            });*/

        /*mnuReportes_ListRecetario.setText("15. Kardex de Recetario");
        mnuReportes_ListRecetario.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mnuReportes_ListRecetario_actionPerformed(e);
                }
            });
        mnuReportes_ListRecetario.setFont(new Font("SansSerif", 0, 11));*/

        mnuEconoFar_Ayuda.add(mnuAyuda_AcercaDe);
        mnuEconoFar_Ayuda.add(mnuInv_Pruebas);
        mnuPtoVenta.add(mnuEconoFar_Ayuda);


        //KMONCADA 19.08.2016 [CENTRO MEDICO]
        mnuEconoFar_CM.setText("Centro Medico");
        mnuEconoFar_CM.setFont(new Font("SansSerif", 0, 11));
        mnuEconoFar_CM.setMnemonic('M');
        mnuCM_Triaje.setText("2. Triaje");
        mnuCM_Triaje.setFont(new Font("SansSerif", 0, 11));
        mnuCM_Triaje.setMnemonic('6');
        mnuCM_Triaje.setActionCommand("Atencion de Triaje");
        mnuCM_Triaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCM_Triaje_actionPerformed(e);
            }
        });

        mnuCM_Consulta.setText("3. Consulta");
        mnuCM_Consulta.setFont(new Font("SansSerif", 0, 11));
        mnuCM_Consulta.setMnemonic('2');
        mnuCM_Consulta.setActionCommand("Atencion de Consulta");
        mnuCM_Consulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCM_Consulta_actionPerformed(e);
            }
        });
        //CCASTILLO 25.08.2016
        mnuCM_ADMBuscarComprobantePago.setText("1. Admisi\u00f3n");
        mnuCM_ADMBuscarComprobantePago.setFont(new Font("SansSerif", 0, 11));
        mnuCM_ADMBuscarComprobantePago.setMnemonic('7');
        //mnuCM_BuscarComprobantePago.setActionCommand("Buscar Comprobante de Pago");
        mnuCM_ADMBuscarComprobantePago.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCM_BuscarComprobantePago_actionPerformed(e);
            }
        });

        //mnuCM_BuscarPacienteDatos.setActionCommand("Buscar Paciente");

        mnuIngreso.setEnabled(false);

        mnuCM_ADMTrazabilidad.setText("4. Trazabilidad");
        mnuCM_ADMTrazabilidad.setFont(new Font("SansSerif", 0, 11));
        mnuCM_ADMTrazabilidad.setMnemonic('9');
        //mnuCM_BuscarPacienteDatos.setActionCommand("Buscar Paciente");
        mnuCM_ADMTrazabilidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCM_ADMTrazabilidad_actionPerformed(e);
            }
        });


        mnuCM_ADMActualizarPaciente.setText("5. Actualizar Paciente");
        mnuCM_ADMActualizarPaciente.setFont(new Font("SansSerif", 0, 11));
        mnuCM_ADMActualizarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCM_ADMActualizarPaciente_actionPerformed(e);
            }
        });


        mnuEconoFar_CM.add(mnuCM_ADMBuscarComprobantePago);
        mnuEconoFar_CM.add(mnuCM_Triaje); // borrar
        mnuEconoFar_CM.add(mnuCM_Consulta);
        mnuEconoFar_CM.add(mnuCM_ADMTrazabilidad);
        mnuEconoFar_CM.add(mnuCM_ADMActualizarPaciente);

        //INI AOVIEDO 31/07/2017
        String nuevoRazonBPP = UtilityRecaudacion.getNuevoTituloBFP("minus");
        //mnuCaja_Financiero.setText("10. Diners Club y Banco Financiero");
        mnuCaja_Financiero.setText("10. " + nuevoRazonBPP);
        mnuCaja_Financiero.setFont(new Font("SansSerif", 0, 11));
        mnuCaja_Financiero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuCaja_Financiero_actionPerformed(e);
            }
        });
        //FIN AOVIEDO 31/07/2017

        // Se desconoce el uso del codigo
        /*lblLogo.setBounds(new Rectangle(615, 395, 160, 145));
        lblLogo.setFont(new Font("SansSerif", 0, 11));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        btnRevertir.setText("Revertirx");
        btnRevertir.setMnemonic('x');
        btnRevertir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRevertir_actionPerformed(e);
                }
            });
        txtRevertir.setBounds(new Rectangle(0, 540, 70, 10));
        txtRevertir.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txtRevertir.setLengthText(2);
        txtRevertir.setFont(new Font("SansSerif", 1, 1));
        txtRevertir.setForeground(Color.WHITE);
        txtRevertir.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtRevertir_keyPressed(e);
                }
            });
        lblMensaje.setText("EN PRUEBAS");
        lblMensaje.setBounds(new Rectangle(155, 20, 515, 60));
        lblMensaje.setFont(new Font("Serif", Font.BOLD, 60));
        lblMensaje.setVisible(false);*/
        /* mnuInv_Pruebas.setText("6. Prueba");
        mnuInv_Pruebas.setFont(new Font("SansSerif", 0, 11));
        mnuInv_Pruebas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mnuPrueba_ActionPerformed(e);
                }
            });*/
        mnuInv_Pruebas.setVisible(false);
        mnuInv_Pruebas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuTest_ActionPerformed(e);
            }
        });
        statusBar.setText("Copyright (c) 2005 - 2015");
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        /*pnlEconoFar.add(lblMensaje, null);
        pnlEconoFar.add(txtRevertir, null);
        pnlEconoFar.add(btnRevertir, null);
        pnlEconoFar.add(lblLogo, null);*/
        this.getContentPane().add(pnlEconoFar, BorderLayout.CENTER);

        ConstantesUtil.simboloSoles_DB();
        ConstantesUtil.describeSoles_DB();
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {


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

        //readFileFarmaVentaProperties();
        cargaIcono();

        //muestraLogin();
        UtilityPtoVenta.muestraUser(this);
        inicializa();

        //INICIA LAS CLASES Y VARIABLES DE FARMA PUNTOS
        if (UtilityPuntos.cargaFarmaPuntos()) {
            cargaDireccionFiscal();
            obtieneIndDireMat();
            //ERIOS 12.09.2013 Obtiene indicador direccion local
            obtieneIndDireLocal();
            //ERIOS 12.09.2013 Obtiene indicador de registro de venta restringida
            obtieneIndRegistroVentaRestringida();
        } else {
            FarmaUtility.showMessage(this, "Error al iniciar FarmaPuntos, se va cerrar el Sistema.\n" +
                    "Por favor de comunicarse con Mesa de Ayuda", null);
            salirSistema();
        }
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        habilitaMenu_Prueba();
        jmnPuntos.setEnabled(false);
        DlgProcesar dlgProcesar = new DlgProcesar(this, "", true);
        dlgProcesar.mostrar();
        jmnPuntos.setEnabled(false);
        verificaRolUsuario();
        verificaJorsa();
        if (FarmaVariables.vAceptar) {
            mnuReportes_ProdSinVtaNDias.setText("13. Productos Sin Ventas en " +
                                                VariablesPtoVenta.vNumeroDiasSinVentas.trim() + " Dias");
        }
        /*** INICIO ARAVELLO 23/09/2019 ***/
        for(BeanVenta vBeanVenta : VariablesRefacturadorElectronico.vListComprobantes){
            try{
                VariablesRefacturadorElectronico.vComprobanteActual=vBeanVenta;
                String vCodGrupoCia = vBeanVenta.getCodGrupoCia();
                String vCodLocal = vBeanVenta.getCodLocal();
                String vNumCompPagoE = vBeanVenta.getNumCompPagoE();
                String vNumPedVentaOld = DBRefacturadorElectronico.getNumPedidoVenta(vCodGrupoCia, vCodLocal, vNumCompPagoE);
                vBeanVenta.setNumPedidoVentaOld(vNumPedVentaOld);
                boolean isPedidoAnulado  = DBRefacturadorElectronico.isPedidoAnulado(vCodGrupoCia, vCodLocal, vNumPedVentaOld);
                
                DBRefacturadorElectronico.backupPedidoVenta(vCodGrupoCia, vCodLocal, vNumPedVentaOld);
                
                ArrayList vListVentaCab = DBRefacturadorElectronico.findVentaCabecera(vCodGrupoCia, vCodLocal, vNumPedVentaOld);
                ArrayList vVentaCab = (ArrayList) vListVentaCab.get(0);
                String vIndFidelizado = (String) vVentaCab.get(0);
                vBeanVenta.setFidelizado(vIndFidelizado.trim().equalsIgnoreCase("S") ? true : false );
                String vDniFidelizado = (String) vVentaCab.get(1);
                vBeanVenta.setDniFidelizado(vDniFidelizado);
                String vRuc = (String) vVentaCab.get(2);
                vBeanVenta.setRuc(vRuc);
                String vIndConvenio = (String) vVentaCab.get(3);
                vBeanVenta.setIsConvenio(vIndConvenio.equalsIgnoreCase("S") ? true : false);
                if(vBeanVenta.isConvenio()){
                    String vCoConvenio = (String) vVentaCab.get(4);
                    vBeanVenta.setCodConvenio(vCoConvenio.trim());
                    ArrayList vDatosConvenio = DBRefacturadorElectronico.findInfoConvenio(vCodGrupoCia, vCodLocal);
                    vBeanVenta.setDatosConvenio(vDatosConvenio);
                    String vCoPago = (String) vVentaCab.get(5);
                    vBeanVenta.setCoPago(vCoPago);
                    String vCodCliente = (String) vVentaCab.get(6);
                    vBeanVenta.setCodCliente(vCodCliente.trim());
                    String vNomCliente = (String) vVentaCab.get(7);
                    vBeanVenta.setNomCliente(vNomCliente.trim());
                }
                //if(isPedidoAnulado){
                    ArrayList vDetalle = DBRefacturadorElectronico.buscarDetallePedidoVenta(vCodGrupoCia, vCodLocal);
                    vBeanVenta.setDetallePedido(vDetalle);
                ArrayList vListComprobantes = DBRefacturadorElectronico.consultaComprobante(vCodGrupoCia, vCodLocal, vNumCompPagoE);
                ArrayList vComprobante = (ArrayList) vListComprobantes.get(0);
                vBeanVenta.setCabComprobante(vComprobante);
                    //mnuCaja_PedidoCompleto.doClick();
                    mnuVentas_GenerarPedido.doClick(); 
                    vBeanVenta.setCompleto(true);
                //}
            }catch(Throwable ex){
                log.error("",ex);
            }
            
        }
        /*** FIN    ARAVELLO 23/09/2019 ***/
    }

    private void mnuSalir_SalirSistema_actionPerformed(ActionEvent e) {
        salirSistema();
    }

    private void mnuVentas_GenerarPedido_actionPerformed(ActionEvent e) {
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
        /*** INICIO ARAVELLO 10/10/2019 ***///Comentado
        //        llamarDlgLogin();
        /*** FIN    ARAVELLO 10/10/2019 ***/
    }

    private void mnuCaja_CobrarPedido_actionPerformed(ActionEvent e) {

        /*DlgFormaPago dlgFormaPago = new DlgFormaPago(this, "", true);
        dlgFormaPago.setVisible(true);
        if (!FarmaVariables.vAceptar) {
            verificaRolUsuario();
        }

        llamarDlgLogin();        */

        DlgPedidosPendientes dlgPedidosPendientes = new DlgPedidosPendientes(this, "", true);
        dlgPedidosPendientes.setVisible(true);
        if (!FarmaVariables.vAceptar) {
            verificaRolUsuario();
        }
        llamarDlgLogin();


    }

    private void mnuCaja_AnularComprobante_actionPerformed(ActionEvent e) {
        DlgAnularPedidoComprobante dlgAnularPedidoComprobante = new DlgAnularPedidoComprobante(this, "", true);
        dlgAnularPedidoComprobante.setVisible(true);
    }

    private void mnuCaja_AperturarCaja_actionPerformed(ActionEvent e) {

        //INI ASOSA - 22/09/2015 - CTRLASIST
        boolean flagCA =
            UtilityControlAsistencia.determinarMarcacionGeneral(this, ConstantsControlAsistencia.ID_ENTRADA);
        if (!flagCA) {
            llamarDlgLogin();
        }

        //FIN ASOSA - 22/09/2015 - CTRLASIST

        if (validaCierresCajero(FarmaVariables.vNuSecUsu)) {
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
        } else {
            //FarmaUtility.showMessage(this, "No puede aperturar la caja, por tener turnos pendientes de dar visto bueno.", null);
            //getListaCajaSinVBCajero
            ArrayList vLista = new ArrayList();
            vLista = getListaCajaSinVBCajero(FarmaVariables.vNuSecUsu);
            log.info("Caja sin cerrar " + vLista);
            DlgValidaAperturaCaja dlgMovCaja = new DlgValidaAperturaCaja(this, "", true, vLista);
            dlgMovCaja.setVisible(true);

        }
    }

    private void mnuCaja_CerrarCaja_actionPerformed(ActionEvent e) {
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


    private void mnuInventario_GuiaEntrada_actionPerformed(ActionEvent e) {
        DlgGuiasIngresosRecibidas dlgGuiasIngresosRecibidas = new DlgGuiasIngresosRecibidas(this, "", true);
        dlgGuiasIngresosRecibidas.setVisible(true);
    }


    private void mnuInventario_Transf_local_actionPerformed(ActionEvent e) {
        DlgTransferenciasRealizadas dlgTransferenciasRealizadas = new DlgTransferenciasRealizadas(this, "", true);
        dlgTransferenciasRealizadas.setVisible(true);
    }

    private void mnuInventario_Transf_delivery_actionPerformed(ActionEvent e) {
        DlgListaTransfPendientes dlgListaTranfPendientes = new DlgListaTransfPendientes(this, "", true);
        dlgListaTranfPendientes.setVisible(true);

    }

    private void mnuInventario_Transf_guias_actionPerformed(ActionEvent e) {
        DlgListadoGuias dlgGuiasSalida = new DlgListadoGuias(this, "", true);
        dlgGuiasSalida.setVisible(true);
    }

    private void mnuInventario_PedidoReposicion_actionPerformed(ActionEvent e) {
        DlgPedidoReposicionRealizados dlgPedidoReposicionRealizados =
            new DlgPedidoReposicionRealizados(this, "", true);
        dlgPedidoReposicionRealizados.setVisible(true);
    }

    private void jMenuItem4_actionPerformed(ActionEvent e) {
        DlgRecepcionProductosGuias dlgRecepcionProductosGuias = new DlgRecepcionProductosGuias(this, "", true);
        dlgRecepcionProductosGuias.setVisible(true);

    }


    private void jMenuItem4_DIGEMID(ActionEvent e) {

        DlgListaProdDIGEMID objDIGEMID = new DlgListaProdDIGEMID(this, "", true); //ASOSA 04.02.2010
        objDIGEMID.setVisible(true);
    }

    private void mnuCaja_ConfigurarCaja_actionPerformed(ActionEvent e) {
        int indIpValida = 0;
        try {
            indIpValida = DBPtoVenta.verificaIPValida();
            if (indIpValida == 0)
                FarmaUtility.showMessage(this,
                                         "La estaci�n actual no se encuentra autorizada para efectuar la operaci�n. ",
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

    private void mnuCaja_CambioProducto_actionPerformed(ActionEvent e) {
        VariablesPtoVenta.vIndAnulacionCambioProducto = FarmaConstants.INDICADOR_S;
        DlgAnularPedido dlgAnularPedido = new DlgAnularPedido(this, "", true);
        dlgAnularPedido.setVisible(true);
    }

    private void mnuCaja_PedidoCompleto_actionPerformed(ActionEvent e) {
        VariablesPtoVenta.vIndAnulacionCambioProducto = FarmaConstants.INDICADOR_N;
        DlgAnularPedido dlgAnularPedido = new DlgAnularPedido(this, "", true);
        dlgAnularPedido.setVisible(true);
    }

    private void mnuAdministracion_Mov_actionPerformed(ActionEvent e) {
        DlgMovimientosCaja dlgMovimientosCaja = new DlgMovimientosCaja(this, "", true);
        dlgMovimientosCaja.setVisible(true);
    }

    private void mnuTomaInventario_Historico_actionPerformed(ActionEvent e) {
        DlgListaHistoricoTomas dlgListaHistoricoTomas = new DlgListaHistoricoTomas(this, "", true);
        dlgListaHistoricoTomas.setVisible(true);
    }


    private void mnuInventario_Kardex_actionPerformed(ActionEvent e) {
        DlgKardex dlgKardex = new DlgKardex(this, "", true);
        dlgKardex.setTipo_reposicion(" "); //05.08.2014 todos los insumos
        //dlgKardex.cargaListaProductos();  //05.08.2014 lista los productos
        //dlgKardex.cargarNombre();         //08.08.2014 titulo de venta
        dlgKardex.setVisible(true);
    }

    //05.08.2014

    private void mnuReposicion_Insumo_actionPerformed(ActionEvent e) {
        DlgKardexInsumos dlgKardexInsumo = new DlgKardexInsumos(this, "", true);
        //dlgKardexInsumo.setTipo_reposicion(ConstantsCaja.TIPO_REPOSICION_INSUMOS);////05.08.2014
        //dlgKardexInsumo.cargaListaProductos();  //05.08.2014 lista los productos
        //dlgKardexInsumo.cargarNombre();         //08.08.2014 titulo de venta
        dlgKardexInsumo.setVisible(true);
    }


    private void jMenuItem1_actionPerformed(ActionEvent e) {
        DlgRegistroVentas dlgRegistroVentas = new DlgRegistroVentas(this, "", true);
        dlgRegistroVentas.setVisible(true);
    }

    private void mnuReportes_RegVtaDelivery_actionPerformed(ActionEvent e) {
        DlgRegistroVentasDelivery dlgRegistroVentasDeliv = new DlgRegistroVentasDelivery(this, "", true);
        dlgRegistroVentasDeliv.setVisible(true);
    }

    private void mnuUsuarios_CambioClave_actionPerformed(ActionEvent e) {
        DlgCambioClave dlgCambioClave = new DlgCambioClave(this, "", true);
        dlgCambioClave.setVisible(true);
    }

    private void mnuUsuarios_CambioUsuario_actionPerformed(ActionEvent e) {
        muestraLoginCambioUser();

        this.repaint();
    }

    private void mnuMantenimiento_Usuarios_actionPerformed(ActionEvent e) {
        DlgListaUsuarios dlgListaUsuarios = new DlgListaUsuarios(this, "", true);
        dlgListaUsuarios.setVisible(true);
    }

    private void mnuMantenimiento_Cajas_actionPerformed(ActionEvent e) {
        DlgListaCajas dlgListaCajas = new DlgListaCajas(this, "", true);
        dlgListaCajas.setVisible(true);
    }

    private void mnuMantenimiento_Impresoras_actionPerformed(ActionEvent e) {
        DlgListaImpresoras dlgListaImpresoras = new DlgListaImpresoras(this, "", true);
        dlgListaImpresoras.setVisible(true);
    }

    private void mnuTomaInVentario_ItemsXLab_actionPerformed(ActionEvent e) {
        DlgListaLaboratorios dlgListaLaboratorios = new DlgListaLaboratorios(this, "", true);
        dlgListaLaboratorios.setVisible(true);
    }

    private void mnuMovCaja_RegistrarVentas_actionPerformed(ActionEvent e) {
        VariablesPtoVenta.vTipOpMovCaja = ConstantsPtoVenta.TIP_OPERACION_MOV_CAJA_REGISTRAR_VENTA;
        DlgMovimientosCaja dlgMovimientosCaja = new DlgMovimientosCaja(this, "", true);
        dlgMovimientosCaja.setVisible(true);
    }

    private void mnuMovCaja_ConsultarMov_actionPerformed(ActionEvent e) {
        VariablesPtoVenta.vTipOpMovCaja = ConstantsPtoVenta.TIP_OPERACION_MOV_CAJA_CONSULTA;
        DlgMovimientosCaja dlgMovimientosCaja = new DlgMovimientosCaja(this, "", true);
        dlgMovimientosCaja.setVisible(true);
    }

    private void mnuCaja_CorreccionComprobantes_actionPerformed(ActionEvent e) {
        DlgVerificacionComprobantes dlgVerificacionComprobantes = new DlgVerificacionComprobantes(this, "", true);
        dlgVerificacionComprobantes.setVisible(true);
    }

    private void mnuMantenimiento_Clientes_actionPerformed(ActionEvent e) {
        DlgBuscaClienteJuridico dlgBuscaClienteJuridico = new DlgBuscaClienteJuridico(this, "", true);
        VariablesCliente.vIndicadorCargaCliente = FarmaConstants.INDICADOR_N;
        dlgBuscaClienteJuridico.setVisible(true);
    }

    private void mnuCaja_ReimpresionPedido_actionPerformed(ActionEvent e) {
        DlgPedidosPendientesImpresion dlgPedidosPendientesImpresion =
            new DlgPedidosPendientesImpresion(this, "", true);
        dlgPedidosPendientesImpresion.setVisible(true);
    }

    private void mnuVentas_DistribucionGratuita_actionPerformed(ActionEvent e) {
        distribucionGratuita();
    }

    private void mnuReportes_DetalleVentas_actionPerformed(ActionEvent e) {
        DlgDetalleVentas dlgDetalleVentas = new DlgDetalleVentas(this, "", true);
        dlgDetalleVentas.setVisible(true);
    }


    private void mnuReportes_VentasVendedor_Total_actionPerformed(ActionEvent e) {

        VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_TOTALES;
        //ERIOS 18.04.2016 Determina el tipo de comision
        VariablesReporte.indVerTipoComision = (new FacadeReporte()).getVerTipoComision();

        DlgVentasPorVendedor dlgVentasPorVendedor = new DlgVentasPorVendedor(this, "", true);
        dlgVentasPorVendedor.setVisible(true);


    }

    private void mnuReportes_VentasVendedor_Mezon_actionPerformed(ActionEvent e) {

        VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_MEZON;

        DlgVentasPorVendedor dlgVentasPorVendedor = new DlgVentasPorVendedor(this, "", true);
        dlgVentasPorVendedor.setTitle("Ventas por Vendedor Meson");
        dlgVentasPorVendedor.setVisible(true);

    }

    private void mnuReportes_VentasVendedor_Delivery_actionPerformed(ActionEvent e) {

        VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_DELIVERY;

        DlgVentasPorVendedor dlgVentasPorVendedor = new DlgVentasPorVendedor(this, "", true);
        dlgVentasPorVendedor.setTitle("Ventas por Vendedor Delivery");
        dlgVentasPorVendedor.setVisible(true);


    }

    private void mnuReportes_VentasVendedor_Institucional_actionPerformed(ActionEvent e) {
        VariablesReporte.ACCION_MOSTRAR_TIPO_VENTA = ConstantsReporte.ACCION_MOSTRAR_INSTITUCIONAL;

        DlgVentasPorVendedor dlgVentasPorVendedor = new DlgVentasPorVendedor(this, "", true);
        dlgVentasPorVendedor.setTitle("Ventas por Vendedor Institucional");
        dlgVentasPorVendedor.setVisible(true);

    }

    private void mnuReportes_VentasProducto_actionPerformed(ActionEvent e) {
        DlgVentasPorProducto dlgVentasPorProducto = new DlgVentasPorProducto(this, "", true);
        dlgVentasPorProducto.setVisible(true);
    }

    private void mnuReportes_VentasConvenio_Reporte_actionPerformed(ActionEvent e) {
        DlgRegistroVentasConvenio dlgVentasConvenio = new DlgRegistroVentasConvenio(this, "", true);
        dlgVentasConvenio.setTipo("R");
        dlgVentasConvenio.setVisible(true);
    }

    private void mnuReportes_VentasConvenio_Liquidacion_actionPerformed(ActionEvent e) {
        DlgRegistroVentasConvenio dlgVentasConvenio = new DlgRegistroVentasConvenio(this, "", true);
        dlgVentasConvenio.setTipo("L");
        dlgVentasConvenio.setVisible(true);
    }

    private void mnuReportes_VentasDiaMes_actionPerformed(ActionEvent e) {
        VariablesReporte.vOrdenar = ConstantsReporte.vVentasDiaMes;
        DlgVentasDiaMes dlgVentasDiaMes = new DlgVentasDiaMes(this, "", true);
        dlgVentasDiaMes.setVisible(true);
    }

    private void mnuReportes_ResumenVentaDia_actionPerformed(ActionEvent e) {
        DlgVentasResumenPorDia dlgVentasResumenPorDia = new DlgVentasResumenPorDia(this, "", true);
        dlgVentasResumenPorDia.setVisible(true);
    }

    private void mnuReportes_VentasHora_actionPerformed(ActionEvent e) {
        DlgVentasPorHora dlgVentasPorHora = new DlgVentasPorHora(this, "", true);
        dlgVentasPorHora.setVisible(true);
    }

    private void mnuAdministracion_RegViajero_actionPerformed(ActionEvent e) {
        DlgProcesaViajero dlgProcesaViajero = new DlgProcesaViajero(this, "", true);
        dlgProcesaViajero.setVisible(true);
    }

    private void mnuInventario_Guias_actionPerformed(ActionEvent e) {
        DlgGuiasRemision dlgGuiasRemision = new DlgGuiasRemision(this, "", true);
        dlgGuiasRemision.setVisible(true);
    }

    private void mnuReportes_FaltaCero_actionPerformed(ActionEvent e) {
        DlgProductoFaltaCero dlgProductoFaltaCero = new DlgProductoFaltaCero(this, "", true);
        dlgProductoFaltaCero.setVisible(true);
    }

    private void mnuCaja_PruebaImpresora_actionPerformed(ActionEvent e) {
        DlgPruebaImpresora dlgPruebaImpresora = new DlgPruebaImpresora(this, "", true);
        dlgPruebaImpresora.setVisible(true);
    }

    private void mnuMantenimiento_Parametros_actionPerformed(ActionEvent e) {
        DlgParametros dlgParametros = new DlgParametros(this, "", true);
        dlgParametros.setVisible(true);
    }


    private void mnuMantenimiento_Carne_actionPerformed(ActionEvent e) {
        DlgBuscaTrabajadorLocal dlgBuscaTrab = new DlgBuscaTrabajadorLocal(this, "", true);
        dlgBuscaTrab.setTitle("Lista de Trabajadores Local");
        dlgBuscaTrab.setVisible(true);
    }

    private void mnuReportes_ProductosABC_actionPerformed(ActionEvent e) {
        DlgProductosABC dlgProductosABC = new DlgProductosABC(this, "", true);
        dlgProductosABC.setVisible(true);
    }

    private void mnuInventario_Ajustes_actionPerformed(ActionEvent e) {
        DlgAjustesporFecha dlgAjustesporFecha = new DlgAjustesporFecha(this, "", true);
        dlgAjustesporFecha.setVisible(true);
    }

    private void mnuInventario_RecepcionLocales_actionPerformed(ActionEvent e) {
        DlgTransferenciasLocal dlgTransferenciasLocal = new DlgTransferenciasLocal(this, "", true);
        dlgTransferenciasLocal.setVisible(true);
    }

    private void mnuInventario_Devolucion_actionPerformed(ActionEvent e) {
        DlgDevolucionesLista dlgListaDevoluciones = new DlgDevolucionesLista(this, "", true);
        dlgListaDevoluciones.setVisible(true);
    }

    private void mnuInventario_DevolucionSobrante_actionPerformed(ActionEvent e) {
        DlgDevolucionesLista_02 dlgListaDevoluciones_02 = new DlgDevolucionesLista_02(this, "", true);
        dlgListaDevoluciones_02.setVisible(true);
    }


    private void mnuInventario_RecepcionMerDirec_actionPerformed(ActionEvent e) {
        DlgMercaderiaDirectaBuscar dlgMercaderiaDirectaBuscar = new DlgMercaderiaDirectaBuscar(this, "", true);
        dlgMercaderiaDirectaBuscar.setVisible(true);
    }

    private void mnuCE_CierreTurno_actionPerformed(ActionEvent e) {
        DlgCierreCajaTurno dlgCierreCajaTurno = new DlgCierreCajaTurno(this, "", true);
        dlgCierreCajaTurno.setLocationRelativeTo(null);
        dlgCierreCajaTurno.setVisible(true);
        llamarDlgLogin();
    }

    private void mnuCE_CierreLocal_actionPerformed(ActionEvent e) {
        DlgHistoricoCierreDia dlgHistoricoCierreDia = new DlgHistoricoCierreDia(this, "", true);
        dlgHistoricoCierreDia.setVisible(true);
    }

    private void mnuInventarioCiclico_Toma_actionPerformed(ActionEvent e) {
        DlgListaTomasInventarioCiclico dlgListaTomasInventarioCiclico =
            new DlgListaTomasInventarioCiclico(this, "", true);
        dlgListaTomasInventarioCiclico.setVisible(true);
    }
    //INICIO-SLEYVA-2019

    private void mnuInventario_Inicio_actionPerformed(ActionEvent e) {
        DBTomaInv.cantTomaInventarios();
        if (DBTomaInv.CANT_TOMAS >= 1) {
            FarmaUtility.showMessage(this,
                                     "<HTML><CENTER>Existen " + DBTomaInv.CANT_TOMAS + " toma(s) creada(s)!<BR>Debe anular la toma para crear Otra.</CENTER></HTML>",
                                     null);
        } else {
            DlgInicioToma dlgInicioToma = new DlgInicioToma(this, "", true);
            dlgInicioToma.setVisible(true);
        }
    }
    //FIN-SLEYVA-2019

    private void mnuInventario_Toma_actionPerformed(ActionEvent e) {
        DlgListaTomasInventario dlgListaTomasInventario = new DlgListaTomasInventario(this, "", true);
        dlgListaTomasInventario.setVisible(true);
    }
    //INICIO-SLEYVA-2019

    private void mnuInventarioCiclico_Inicio_actionPerformed(ActionEvent e) {
        DBTomaInv.cantTomaInventarios();
        if (DBTomaInv.CANT_TOMAS >= 1) {
            FarmaUtility.showMessage(this,
                                     "<HTML><CENTER>Existen " + DBTomaInv.CANT_TOMAS + " toma(s) creada(s)!<BR>Debe anular la toma para crear Otra.</CENTER></HTML>",
                                     null);
        } else {
            DlgInicioInvCiclico dlgInicioInvCiclico = new DlgInicioInvCiclico(this, "", true);
            dlgInicioInvCiclico.setVisible(true);
        }
    }
    //FIN-SLEYVA-2019

    private void mnuVentas_DeliveryAutomatico_actionPerformed(ActionEvent e) {
        DlgUltimosPedidos dlgUltimosPedidos = new DlgUltimosPedidos(this, "", true);
        dlgUltimosPedidos.setTipoVenta(ConstantsVentas.TIPO_PEDIDO_DELIVERY);
        dlgUltimosPedidos.setVisible(true);
        llamarDlgLogin();
    }

    private void mnuVentas_Empresas_actionPerformed(ActionEvent e) {
        DlgUltimosPedidos dlgUltimosPedidos = new DlgUltimosPedidos(this, "", true);
        dlgUltimosPedidos.setTipoVenta(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL);
        dlgUltimosPedidos.setVisible(true);
        llamarDlgLogin();
    }


    private void mnuVentas_MedidaPresion_actionPerformed(ActionEvent e) {
        DlgListaMedidaPresion dlgListaMedidaPresion = new DlgListaMedidaPresion(this, "", true);
        dlgListaMedidaPresion.setVisible(true);
    }

    private void mnuVentas_Correlativo_actionPerformed(ActionEvent e) {

        DlgConsultarRecargaCorrelativo_AS dlgRecargaCorrelativo =
            new DlgConsultarRecargaCorrelativo_AS(this, "Consulta de recarga por correlativo", true);
        dlgRecargaCorrelativo.setVisible(true);

    }

    private void mnuAdministracion_ControlHoras_actionPerformed(ActionEvent e) {
        DlgControlHoras dlgControlHoras = new DlgControlHoras(this, "", true);
        dlgControlHoras.setVisible(true);

    }

    private void mnuOtros_MantProbisa_actionPerformed(ActionEvent e) {
        DlgListaProbisa dlgListaProbisa = new DlgListaProbisa(this, "", true);
        dlgListaProbisa.setVisible(true);
    }

    private void mnuReportes_UnidVtaLocal_actionPerformed(ActionEvent e) {
        DlgUnidVtaLocal dlgUnidVtaLocal = new DlgUnidVtaLocal(this, "", true);
        dlgUnidVtaLocal.setVisible(true);
    }

    private void mnuReportes_ProdSinVtaNDias_actionPerformed(ActionEvent e) {
        DlgProdSinVtaNDias dlgProdSinVtaNDias = new DlgProdSinVtaNDias(this, "", true);
        dlgProdSinVtaNDias.setVisible(true);
    }

    private void mnuPedidoAdicional_actionPerformed(ActionEvent e) {
        if (!UtilityInventario.is_Migra_Sap()) {
            DlgPedidoReposicionAdicionalNuevo dlgPedidoAdicional =
                new DlgPedidoReposicionAdicionalNuevo(this, "", true);
            dlgPedidoAdicional.setVisible(true);
        } else {
            FarmaUtility.showMessage(this, "El local no tiene activo la funcionalidad.", null);
        }
    }

    private void mnuPedidoEspecial_actionPerformed(ActionEvent e) {
        if (!UtilityInventario.is_Migra_Sap()) {
            /*** Inicio CCASTILLO 10/10/2016 ***/
            DlgListaPedidosEspeciales dlglistaPE =
                new DlgListaPedidosEspeciales(this, "", true, ConstantsInventario.TIP_PED_ESP_NOR);
            /*** Fin  CCASTILLO 10/10/2016 ***/
            dlglistaPE.setVisible(true);
        } else {
            FarmaUtility.showMessage(this, "El local no tiene activo la funcionalidad.", null);
        }
    }

    /*** Inicio CCASTILLO 10/10/2016 ***/
    private void mnuInventario_PedidoEspecial_Insumo_actionPerformed(ActionEvent e) {
        if (!UtilityInventario.is_Migra_Sap()) {
            DlgListaPedidosEspeciales dlglistaPE =
                new DlgListaPedidosEspeciales(this, "", true, ConstantsInventario.TIP_PED_ESP_INS);
            dlglistaPE.setVisible(true);
        } else {
            FarmaUtility.showMessage(this, "El local no tiene activo la funcionalidad.", null);
        }
    }

    /*** Fin  CCASTILLO 10/10/2016 ***/


    private void mmuIngresoTemperatura_actionPerformed(ActionEvent e) {
        DlgHistoricoTemperatura dlghistorico = new DlgHistoricoTemperatura(this, "", true);
        dlghistorico.setVisible(true);
    }

    private void mmuGestionPlantilla_actionPerformed(ActionEvent e) {

        DlgListadoPlantilla dlgPlantilla = new DlgListadoPlantilla(this, "", true);
        dlgPlantilla.setVisible(true);
    }

    private void mmuGestionHorario_actionPerformed(ActionEvent e) {

        DlgListadoHorario dlgHorario = new DlgListadoHorario(this, "", true);
        dlgHorario.setVisible(true);
    }


    private void mnuInventarioDiario_Inicio_actionPerformed(ActionEvent e) {

        DlgInicioInveDiario dlgInicioInveDiario = new DlgInicioInveDiario(this, "", true);
        dlgInicioInveDiario.setVisible(true);

    }

    private void mnuInventarioDiario_Toma_actionPerformed(ActionEvent e) {

        DlgListaTomasInventarioDiario dlgListaTomasInventarioDiario =
            new DlgListaTomasInventarioDiario(this, "", true);
        dlgListaTomasInventarioDiario.setVisible(true);

    }

    private void mnuInventarioDiario_Diferencia_actionPerformed(ActionEvent e) {

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) || validarAsistAudit()) {
            DlgListaDiferenciasToma dlgListaDiferenciasToma = new DlgListaDiferenciasToma(this, "", true);
            dlgListaDiferenciasToma.setVisible(true);
        } else {
            FarmaUtility.showMessage(this, "No posee privilegios suficientes para acceder a esta opci�n", null);
        }

    }

    private void mnuMantenimiento_MaquinaIP_actionPerformed(ActionEvent e) {

        DlgListaIPSImpresora DlgIP = new DlgListaIPSImpresora(this, "", true);
        DlgIP.setVisible(true);

    }

    private void mnuMantenimiento_ImpresoraTermica_actionPerformed(ActionEvent e) {

        DlgListaImpresoraTermCreaMod dlgLstImprTermCreaMod = new DlgListaImpresoraTermCreaMod(this, "", true);
        dlgLstImprTermCreaMod.setVisible(true);


    }

    private void mnuMantenimiento_RolTemporal_actionPerformed(ActionEvent e) {

        DlgListaRolesTmp dlgLstRolTmp = new DlgListaRolesTmp(this, "", true);
        dlgLstRolTmp.setVisible(true);


    }

    private void mnuAdm_mntoCli_actionPerformed(ActionEvent e) {
        //ASOSA - 20/04/2015 -
        /*DlgFidelizacionIngresoDoc dlgFidelizacionIngresoDoc = new DlgFidelizacionIngresoDoc(this, "", true);
        dlgFidelizacionIngresoDoc.setVisible(true);*/
        DlgFidelizacionBuscarTarjetas dlgBuscar = new DlgFidelizacionBuscarTarjetas(this, "", true, false);
        dlgBuscar.setIsMantenimientoCliente(true);
        dlgBuscar.setVisible(true);
    }

    private void mnuCaja_ReimpresionTicketsAnulados_actionPerformed(ActionEvent e) {

        DlgListaTicketsAnulados dlgListaTicketsAnulados = new DlgListaTicketsAnulados(this, "", true);
        dlgListaTicketsAnulados.setVisible(true);
    }

    private void mnuInventario_Recepcion_actionPerformed(ActionEvent e) {

        DlgHistoricoRecepcion dlgRecepcion = new DlgHistoricoRecepcion(this, "", true);
        dlgRecepcion.setVisible(true);


    }

    private void mnuIngreso_actionPerformed(ActionEvent e) {
        DlgControlIngreso dlgControlIngreso = new DlgControlIngreso(this, "", true);
        dlgControlIngreso.setVisible(true);
    }

    private void mnuIngTransportista_actionPerformed(ActionEvent e) {
        DlgListaTransportistas dlgListaTransp = new DlgListaTransportistas(this, "", true);
        dlgListaTransp.setVisible(true);
    }

    private void mnuFondoSencillo_actionPerformed(ActionEvent e) {
        if (UtilityFondoSencillo.indActivoFondo()) {

            DlgListadoCajeros dlgListado = new DlgListadoCajeros(this, "", true);
            dlgListado.setVisible(true);
        } else {
            FarmaUtility.showMessage(this, "A�n no se encuentra habilitada est� opci�n en su Local.", null);
        }
    }

    private void mnuCE_Prosegur_actionPerformed(ActionEvent e) {
        DlgListaRemito dlgListaRem = new DlgListaRemito(this, "", true);
        dlgListaRem.setVisible(true);
    }

    private void mnuCaja_ControlSobresParciales_actionPerformed(ActionEvent e) {
        DlgControlSobres dlgcontrol = new DlgControlSobres(this, "", true);
        dlgcontrol.setVisible(true);
    }

    private void mnuPrecioCambProd_actionPerformed(ActionEvent e) {
        DlgPrecioProdCambiados dlgPrecioCambiad = new DlgPrecioProdCambiados(this, "", true);
        dlgPrecioCambiad.setVisible(true);
    }

    //01.10.2013 CVILCA

    private void mnuProdImpresion_actionPerformed(ActionEvent e) {
        DlgProdImpresion dlgProdImpresion = new DlgProdImpresion(this, "", true);
        dlgProdImpresion.setVisible(true);
    }

    private void mnuCaja_CMR_actionPerformed(ActionEvent e) {
        DlgRecaudacionCmr dlgRecaudaCmr = new DlgRecaudacionCmr(this, "", true);
        dlgRecaudaCmr.setVisible(true);
    }

    private void jMenuItem5_actionPerformed(ActionEvent e) {
        DlgRecaudacionCitibank dlgRecaudCitibank = new DlgRecaudacionCitibank(this, "", true);
        dlgRecaudCitibank.setVisible(true);
    }

    private void mnuCaja_Claro_actionPerformed(ActionEvent e) {
        DlgRecaudacionClaro dlgRecaudaClaro = new DlgRecaudacionClaro(this, "", true);
        dlgRecaudaClaro.setVisible(true);
    }

    private void mnuCaja_Prestamos_Citibank_actionPerformed(ActionEvent e) {
        DlgRecaudacionPrestamosCitibank dlgRecadaPrestaCitibank = new DlgRecaudacionPrestamosCitibank(this, "", true);
        dlgRecadaPrestaCitibank.setVisible(true);
    }

    private void mnuCaja_Ripley_actionPerformed(ActionEvent e) {
        DlgRecaudacionRipley dlgRecaudadaRipley = new DlgRecaudacionRipley(this, "", true);
        dlgRecaudadaRipley.setVisible(true);
    }

    private void mnuCaja_Registro_Recaudaciones_actionPerformed(ActionEvent e) {
        DlgRecaudacionesRealizadas dlgRecaudaRealizadas = new DlgRecaudacionesRealizadas(this, "", true);
        dlgRecaudaRealizadas.setTipoCobro(ConstantsRecaudacion.TIPO_COBRO_RECAUDACION);
        dlgRecaudaRealizadas.setVisible(true);
    }

    private void mnuCaja_Registro_Venta_CMR_actionPerformed(ActionEvent e) {
        DlgRecaudacionesRealizadas dlgRecaudaRealizadas = new DlgRecaudacionesRealizadas(this, "", true);
        dlgRecaudaRealizadas.setTipoCobro(ConstantsRecaudacion.TIPO_COBRO_VENTA_CMR);
        dlgRecaudaRealizadas.setVisible(true);

    }


    private void mnuCaja_Pinpad_Visa_actionPerformed(ActionEvent e) {
        DlgTransaccionesPinpad dlgTransaccionesPinpad = new DlgTransaccionesPinpad(this, "", true);
        dlgTransaccionesPinpad.setVisible(true);
    }


    private void mnuCaja_AbrirGabeta_actionPerformed(ActionEvent e) {
        UtilityCaja.abrirGabeta(this, true);
    }

    private void mnuCaja_ServiciosVisa_actionPerformed(ActionEvent e) {
        DlgTransaccionesPinpad dlgTransaccionesPinpad = new DlgTransaccionesPinpad(this, "", true);
        dlgTransaccionesPinpad.setTipoOperacion(VariablesPinpad.OP_FINANCIERA);
        dlgTransaccionesPinpad.setVisible(true);
    }

    private void this_windowClosed(WindowEvent e) {
        UtilityVentas.eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private boolean llamarDlgLogin() {
        System.out.println("llamarDlgLogin");
        String mensajeLogin = "Acceso " + VariablesPtoVenta.tituloBaseFrame;
        DlgLogin dlgLogin = new DlgLogin(this, mensajeLogin, true);
        FarmaVariables.dlgLogin = dlgLogin;
        dlgLogin.setVisible(true);
        if (!FarmaVariables.vAceptar) {
            FarmaConnection.closeConnection();
            salirSistema();
        }
        boolean flagCA =
            UtilityControlAsistencia.determinarMarcacionGeneral(this, ConstantsControlAsistencia.ID_ENTRADA);
        System.out.println("verificaRolUsuario - llamarDlgLogin");
        verificaRolUsuario();
        if (!flagCA) {
            llamarDlgLogin();
        }
        return FarmaVariables.vAceptar;
    }

    void muestraLogin() {

        //if ( readFileProperties() )
        if (true) {
            /*
      if(!validaNamePc()){
        FarmaUtility.showMessage(this,"Error al obtener el Nombre de la PC.", null);
        salirSistema();
      }
      try{
        int cantSesiones = DBPtoVenta.obtieneCantidadSesiones(FarmaVariables.vNamePc, FarmaVariables.vUsuarioBD);
        if(cantSesiones > 1){
          FarmaUtility.showMessage(this,"Ya existe una aplicacion iniciada en esta PC.\nPor favor comunicarse con el area de Sistemas", null);
          salirSistema();
        }
      } catch(SQLException sql)
      {
        FarmaUtility.showMessage(this,"Error al obtener cantidad de Sesiones Abiertas.\nPor favor comunicarse con el area de Sistemas", null);
        salirSistema();
      }
      */
            obtieneInfoLocal();
            //obtener info del local
            if (!validaIpPc()) {
                FarmaUtility.showMessage(this, "Error al obtener la IP de la PC.", null);
                salirSistema();
            }
            BeanVariables.vAccion = "A";

            String mensajeLogin = "Acceso " + VariablesPtoVenta.tituloBaseFrame;
            DlgLogin dlgLogin = new DlgLogin(this, mensajeLogin, true);
            dlgLogin.setVisible(true);
            if (!FarmaVariables.vAceptar) {
                FarmaConnection.closeConnection();
                salirSistema();
            } else {
                //inicio
                //lapaz dubilluz 17.09.2010


                log.info("Inicio de Hilo");
                // crear y nombrar a cada subproceso

                //SubProcesos subproceso1 = new SubProcesos("GET_PROD_VENTA" );
                SubProcesos subproceso2 = new SubProcesos("GET_PROD_ESPECIALES");
                SubProcesos subproceso3 = new SubProcesos("CARGA_IMP_TERMICA");

                log.debug("Iniciando subprocesos");
                SubProcesoFarmaVenta subproceso4 = new SubProcesoFarmaVenta(FarmaVariables.vNuSecUsu, this);

                //subproceso1.start();
                subproceso2.start();
                subproceso3.start();
                subproceso4.start();

                log.info("Fin de Hilo");
                //fin
                //lapaz dubilluz 17.09.2010

                //JCORTEZ 04.09.09 Se valida cambio de clave para el usuario
                String valida = "";
                String numDiasVenc = ""; //CHUANES 25.02.2015
                String usuDebeCambiarClave = ""; //CHUANES 11.03.2015
                String recCambioClave = ""; //CHUANES 12.03.2015
                try {
                    usuDebeCambiarClave = DBPtoVenta.usuDebeCambiarClave().trim();
                    valida = DBPtoVenta.validaCambioClave().trim();
                    numDiasVenc = DBPtoVenta.recFecVenClave().trim();
                    recCambioClave = DBPtoVenta.recodCambioClave().trim();
                } catch (SQLException x) {
                    log.error("", x);
                }
                //CHUANES 11.03.2015
                if (usuDebeCambiarClave.equalsIgnoreCase("S")) {
                    if (!recCambioClave.equalsIgnoreCase("N")) { //CHUANES 12.03.2015 SOLO DEBE RECORDAR UNA VEZ AL DIA EL VENCIMIENTO DE LA CLAVE
                        if (!numDiasVenc.equalsIgnoreCase("N")) {
                            if (JConfirmDialog.rptaConfirmDialog(this,
                                                                 "Estimado usuario le falta " + numDiasVenc + " dia(s) para que expire su clave\n" +
                                    "�Desea cambiarla ahora?")) {
                                ;

                                DlgCambioClave dlgcambio = new DlgCambioClave(this, "", true);
                                dlgcambio.setVisible(true);

                                if (FarmaVariables.vAceptar) {
                                    FarmaVariables.dlgLogin = dlgLogin;
                                    recuperaStock();
                                    //INI ASOSA - 14/09/2015 - CTRLASIST
                                    boolean flagCA =
                                        UtilityControlAsistencia.determinarMarcacionGeneral(this, ConstantsControlAsistencia.ID_ENTRADA);
                                    if (!flagCA) {
                                        llamarDlgLogin();
                                    }
                                    //FIN ASOSA - 24/09/2015 - CTRLASIST
                                } else {
                                    // salirSistema();no debe salir del sistema porque esta vigente
                                }
                            }

                        }
                    }

                    log.info("cambiar password :" + valida);
                    if (valida.trim().equalsIgnoreCase("S")) {

                        FarmaUtility.showMessage(this, "Usted debera cambiar su clave para poder entrar el sistema.",
                                                 null);
                        DlgCambioClave dlgcambio = new DlgCambioClave(this, "", true);
                        dlgcambio.setVisible(true);

                        if (FarmaVariables.vAceptar) {
                            FarmaVariables.dlgLogin = dlgLogin;
                            recuperaStock();
                            //INI ASOSA - 14/09/2015 - CTRLASIST
                            boolean flagCA =
                                UtilityControlAsistencia.determinarMarcacionGeneral(this, ConstantsControlAsistencia.ID_ENTRADA);
                            if (!flagCA) {
                                llamarDlgLogin();
                            }
                            //FIN ASOSA - 24/09/2015 - CTRLASIST
                        } else {
                            salirSistema();
                        }

                    } else {
                        FarmaVariables.dlgLogin = dlgLogin;
                        recuperaStock();
                        //INI ASOSA - 14/09/2015 - CTRLASIST
                        boolean flagCA =
                            UtilityControlAsistencia.determinarMarcacionGeneral(this, ConstantsControlAsistencia.ID_ENTRADA);
                        if (!flagCA) {
                            llamarDlgLogin();
                        }
                        //FIN ASOSA - 24/09/2015 - CTRLASIST
                    }
                } else { //CHUANES 29/04/2015 SOLUCIONA OPERADOR Y SOBRE PARCIAL
                    FarmaVariables.dlgLogin = dlgLogin;
                    recuperaStock();

                    //INI ASOSA - 14/09/2015 - CTRLASIST
                    boolean flagCA =
                        UtilityControlAsistencia.determinarMarcacionGeneral(this, ConstantsControlAsistencia.ID_ENTRADA);
                    if (!flagCA) {
                        llamarDlgLogin();
                    }
                    //FIN ASOSA - 24/09/2015 - CTRLASIST
                }

                //FIN CHUANES 11.03.2015


                SubProcesosALertatUp subprocesoAlertUp = new SubProcesosALertatUp(this);
                subprocesoAlertUp.start();

                // KMONCADA 09.08.2016 [FACT.ELECTRONICA 2.0]

                SubProcesoCPE subProcesoCompPagoElectronico = new SubProcesoCPE();
                subProcesoCompPagoElectronico.start();

                //LTAVARA 2016.07.24 INICIO  ALERTA DE COTIZACIONES PENDIENTES
                SubProcesoAlertUpCotizacionPrecio subProcesoAlertUpCotizacionPrecio =
                    new SubProcesoAlertUpCotizacionPrecio(this);
                subProcesoAlertUpCotizacionPrecio.start();

                //LTAVARA 2016.07.24  FIN  ALERTA DE COTIZACIONES PENDIENTES
            }
        } else
            salirSistema();
        BeanVariables.vAccion = "";
    }

    private void recuperaStock() {

        try {
            // RECUPERANDO STOCK COMPROMETIDO
            DBPtoVenta.ejecutaRespaldoStock("", "", ConstantsPtoVenta.TIP_OPERACION_RESPALDO_EJECUTAR, 0, 0, "");
            log.info("RECUPERO STOCK COMPROMETIDO DESDE RESPALDO");
            FarmaUtility.aceptarTransaccion();
            verificaRolUsuario();
        } catch (SQLException sqlException) {
            FarmaUtility.liberarTransaccion();
            log.error("", sqlException);
            FarmaUtility.showMessage(this,
                                     "Error al recuperar Stock de Respaldo.\nPonganse en contacto con el area de Sistemas",
                                     null);
            salirSistema();
        }

    }

    private boolean validaNamePc() {
        FarmaVariables.vNamePc = FarmaUtility.getHostName();
        if (FarmaVariables.vNamePc.trim().length() == 0)
            return false;
        return true;
    }

    private boolean validaIpPc() {
        FarmaVariables.vIpPc = FarmaUtility.getHostAddress();
        if (FarmaVariables.vIpPc.trim().length() == 0)
            return false;
        return true;
    }

    private void muestraUser() {

        String addon = " Usu.Actual : ";
        addon = addon + FarmaVariables.vIdUsu;
        this.setTitle(VariablesPtoVenta.tituloBaseFrame + " /  Local : " + FarmaVariables.vDescCortaLocal + " / " +
                      addon + " /  IP : " + FarmaVariables.vIpPc);
    }

    public void obtieneInfoLocal() {
        try {
            ArrayList infoLocal = DBPtoVenta.obtieneDatosLocal();
            FarmaVariables.vDescCortaLocal = ((String)((ArrayList)infoLocal.get(0)).get(0)).trim();
            FarmaVariables.vDescLocal = ((String)((ArrayList)infoLocal.get(0)).get(1)).trim();
            FarmaVariables.vTipLocal = ((String)((ArrayList)infoLocal.get(0)).get(2)).trim();
            FarmaVariables.vTipCaja = ((String)((ArrayList)infoLocal.get(0)).get(3)).trim();

            FarmaVariables.vNomCia = ((String)((ArrayList)infoLocal.get(0)).get(4)).trim();
            FarmaVariables.vNuRucCia = ((String)((ArrayList)infoLocal.get(0)).get(5)).trim();
            FarmaVariables.vMinutosPedidosPendientes = ((String)((ArrayList)infoLocal.get(0)).get(6)).trim();
            FarmaVariables.vImprReporte = ((String)((ArrayList)infoLocal.get(0)).get(7)).trim();
            FarmaVariables.vIndHabilitado = ((String)((ArrayList)infoLocal.get(0)).get(8)).trim();
            FarmaVariables.vDescCortaDirLocal = ((String)((ArrayList)infoLocal.get(0)).get(9)).trim();

            VariablesPtoVenta.vRazonSocialCia = DBPtoVenta.obtieneRazSoc();
            VariablesPtoVenta.vTelefonoCia = DBPtoVenta.obtieneTelfCia();
            VariablesPtoVenta.vNombreMarcaCia = DBPtoVenta.obtieneNombreMarcaCia();

            /*** INICIO CCASTILLO 09/05/2016 ***/
            //Se agrega columna para saber el tipo de venta del local
            VariablesPtoVenta.vTipoLocalVenta = ((String)((ArrayList)infoLocal.get(0)).get(10)).trim();
            /*** FIN  CCASTILLO 09/05/2016 ***/
            /*** INICIO CCASTILLO 16/08/2017 ***/
            VariablesPtoVenta.vIndPinPad = ((String)((ArrayList)infoLocal.get(0)).get(11)).trim();
            /*** Fin ccastillo 16/08/2017 ***/

            if (UtilityPtoVenta.isLocalTipoVentaTICO()) {
                VariablesVentas.tableModelListaGlobalProductosInsumo =
                        new FarmaTableModel(ConstantsVentas.columnsListaProdPrecios,
                                            ConstantsVentas.defaultValuesListaProdPrecios, 0);

                DBVentas.cargaListaProductosInsumo(VariablesVentas.tableModelListaGlobalProductosInsumo);
            }

        } catch (SQLException sqlException) {

            log.error("", sqlException);
        }
    }

    /** INICIO CCASTILLO 16/08/2017 ***/
    public void habilitaPinPad() {
        if (VariablesPtoVenta.vIndPinPad.equalsIgnoreCase("N"))
            mnuCaja_Pinpad.setEnabled(false);
        else
            mnuCaja_Pinpad.setEnabled(true);
    }

    /*** FIN CCASTILLO 16/08/2017 ***/

    private void generarPedido() {
        DlgResumenPedido dlgResumenPedido = new DlgResumenPedido(this, "", true, false);
        dlgResumenPedido.setFrameEconoFar(this);
        dlgResumenPedido.setVisible(true);
        dlgResumenPedido = null;
//        while (FarmaVariables.vAceptar) {
//            if (VariablesVentas.vIndPrecioCabeCliente.equalsIgnoreCase("S")) { //Inicio: ASOSA 03.02.2010
//                VariablesVentas.vIndPrecioCabeCliente = "N";
//                ind = 1;
//                break;
//            } else {
//                if (ind != 1) {
//                    log.info("Go Menu");
//                    generarPedido();
//                } else {
//                    ind = 0;
//                    break;
//                }
//            } //Fin: ASOSA 03.02.2010
//        }
//        if (!FarmaVariables.vAceptar)
//            verificaRolUsuario_sinAdmin();
    }


    private void verificaRolUsuario() {
        ArrayList rolesUsuario = new ArrayList();
        boolean vEnabled_IRS_Tesoreria = UtilityPtoVenta.get_habilita_Tesoreria();
        mnuCaja_IRS_Tesoreria.setEnabled(vEnabled_IRS_Tesoreria);
        try {
            rolesUsuario = recuperaRoles();
            System.out.println("verificaRolUsuario");
            for (int i = 0; i < rolesUsuario.size(); i++) {
                System.out.println(" ==> ROL[" + i + "]: " + rolesUsuario.get(i).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_MEDICENTRO)) {
            opcionesMedicentro();
        }
        else{
            if(//FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL) ||
               FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_OPERADOR_SISTEMAS)
                )
              quitaOpcionesAgregaTodos();
            else
              quitaOpcionesAgregaSinCentroMedico();
        }*/


        if (medicoAuxiliar()) {
            if (FarmaVariables.dlgLogin.verificaRol("062")) {
                mnuEconoFar_CM.setEnabled(true);
            } else {
                mnuEconoFar_CM.setEnabled(false);
            }

            //dubilluz 28.09.2015
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                String indNewCtrlAsist =
                    UtilityInventario.obtenerParametroString(ConstantsControlAsistencia.ID_TAB_IND_ACTIVO_NEW_CTRL_ASIST);
                if (indNewCtrlAsist.equals(FarmaConstants.INDICADOR_S)) {
                    UtilityControlAsistencia.muestraTrabajadoresCesados();
                }
            }
            //dubilluz 28.09.2015

            opcionesComunes();
            jmnPuntos.setEnabled(false);
            mnuAdm_mntoCli.setVisible(false);
            //------------SOLO PARA PRUEBA TICO
            // VariablesPtoVenta.vTipoLocalVenta=ConstantsPtoVenta.TIPO_LOCAL_VENTA_TICO;
            //------------SOLO PARA PRUEBA TICO
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_VENDEDOR)) {
                opcionesVendedor();
                //LTAVARA 2017.06.22 CONSTANCIA DE RECEPCION DE MERCADERIA
                if (UtilityPtoVenta.isLocalTipoVentaTICO()) {
                    mnuInventario_MercaderiaDirecta.setEnabled(true);
                }
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_CAJERO)) {
                opcionesCajero();
                //LTAVARA 2017.06.22 CONSTANCIA DE RECEPCION DE MERCADERIA
                if (UtilityPtoVenta.isLocalTipoVentaTICO()) {
                    mnuInventario_MercaderiaDirecta.setEnabled(true);
                }
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                opcionesAuditoria();
                opcionesAdministrador();
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                opcionesAdministrador();
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_CONTABILIDAD) ||
                FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_LECTURA_REPORTES)) {
                opcionesProsegur();
                //2017.10.06
                mnuCE_CierreLocal.setEnabled(true);
            }

            if (FarmaVariables.dlgLogin.verificaRol(ConstantsPtoVenta.ROL_ASISTENTE_AUDITORIA)) {
                opcionesAsistenteAuditoria();
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_OPERADOR_SISTEMAS)) {
                opcionesOperador();
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_INVENTARIADOR)) {
                opcionesInventariador();
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_LECTURA_REPORTES)) {
                opcionesReportes();
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_LECTURA_INVENTARIO)) {
                opcionesLecturaInventario();
            }

            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_SUPERVISOR_VENTAS)) {
                opcionesSupervisorVentas();
            }
            bloquearOpcionesPorCompania();
            bloquearAlvis();
        }
        if (!VariablesControlAsistencia.isValidarPingPcMarcacion || !VariablesControlAsistencia.isConexionPcHuella) {
            mnuIngreso.setEnabled(true);
        }
        //desactivar la opcion "CORRECCION GUIA"

        try {

            if (DBInventario.getIndDesactivarCorreccionGuia()) {
                mnuInventario_Guias.setEnabled(false);
            }
        } catch (Exception e) {
        }
    }

    private void bloquearAlvis() {
        if (FarmaVariables.vCodCia.equals("007")) {
            mnuInventario_BandejasDevolver.setEnabled(false);
        } else {
            mnuCaja_Incasur.setVisible(false); //ASOSA - 17/05/2016 - INCASUR
        }
    }

    private void validaOpcionesMenu(boolean pValor) {
        // MODULO VENTAS
        mnuEconoFar_Ventas.setEnabled(pValor);
        mnuVentas_GenerarPedido.setEnabled(pValor);
        //mnuVentas_DistribucionGratuita.setEnabled(pValor);
        mnuVentas_DeliveryAutomatico.setEnabled(pValor);
        //TODO MedidaPresion
        //TODO Recargas
        //TODO InvDiario
        mnuVentas_Digemid.setEnabled(pValor);
        //[Desarrollo5 - Alejandro Nu�ez] 26.11.2015
        jmmRecueraPuntos.setEnabled(pValor);
        jmmDesafiliacionx1.setEnabled(pValor);

        // MODULO CAJA
        mnuEconoFar_Caja.setEnabled(pValor);
        mnuCaja_MovimientosCaja.setEnabled(pValor);
        mnuCaja_AperturarCaja.setEnabled(pValor);
        mnuCaja_CerrarCaja.setEnabled(pValor);
        mnuCaja_ConfigurarCaja.setEnabled(pValor);
        mnuCaja_CobrarPedido.setEnabled(pValor);
        mnuCaja_AnularVentas.setEnabled(pValor);
        mnuCaja_CambioProducto.setEnabled(pValor);
        mnuCaja_PedidoCompleto.setEnabled(pValor);
        mnuCaja_AnularComprobante.setEnabled(pValor);
        mnuCaja_CorreccionComprobantes.setEnabled(pValor);
        mnuCaja_ReimpresionPedido.setEnabled(pValor);
        mnuCaja_RegulaVtaManual.setEnabled(pValor);
        mnuCaja_Utilitarios.setEnabled(pValor);
        mnuCaja_PruebaImpresora.setEnabled(pValor);
        /*** INICIO CCASTILLO 16/08/2017 ***/
        //mnuCaja_Pinpad.setEnabled(pValor);
        habilitaPinPad();
        /*** FIN CCASTILLO 16/08/2017 ***/
        mnuCaja_Pinpad_Visa.setEnabled(pValor);
        mnuCaja_Pinpad_Mastercard.setEnabled(pValor);
        mnuCaja_reporDet_Pinpad_MC.setEnabled(pValor);
        mnuCaja_reporTot_Pinpad_MC.setEnabled(pValor);
        mnuCaja_Reimpresion_Pinpad_MC.setEnabled(pValor);
        mnuCaja_AnulacionTran_Pinpad_Visa.setEnabled(pValor);
        mnCaja_ReimpresionLote_Pinpad_MC.setEnabled(pValor);
        mnuCaja_AbrirGabeta.setEnabled(pValor);
        mnuCaja_ServiciosVisa.setEnabled(pValor);
        //TODO mnuCaja_ReimpresionTicketsAnulados
        //TODO mnuCaja_ControlSobreParciales
        mnuCaja_Recaudacion.setEnabled(pValor);
        mnuCaja_CMR.setEnabled(pValor);
        mnuCaja_Citibank.setEnabled(pValor);
        mnuCaja_Claro.setEnabled(pValor);
        mnuCaja_Prestamos_Citibank.setEnabled(pValor);
        mnuCaja_Registro_Recaudaciones.setEnabled(pValor);

        // MODULO INVENTARIO
        mnuEconoFar_Inventario.setEnabled(pValor);
        mnuInventario_GuiaEntrada.setEnabled(pValor);
        mnuCotizacionPrecio.setEnabled(pValor);
        mnuInventario_Kardex.setEnabled(pValor);
        mnuInventario_Transferencias.setEnabled(pValor);
        //mnuInventario_Transf_local
        //mnuInventario_Transf_delivery
        //mnuInventario_Mercaderia
        //mnuInventario_Recepcion
        mnuInventario_RecepcionProductos.setEnabled(pValor);
        //mnuInventario_IngTransportista
        //mnuInventario_PedidosLocales
        //mnuInventario_PedidoReposicion
        //mnuInventario_PedidoAdicional
        //mnuInventario_PedidoEspecial
        mnuInventario_Guias.setEnabled(pValor);
        mnuInventario_Ajustes.setEnabled(pValor);
        mnuInventario_RecepcionLocales.setEnabled(pValor);
        mnuInventario_MercaderiaDirecta.setEnabled(pValor);
        //mnuInventario_RecepcionMerDirec
        //mnuInventario_Devolucion

        // MODULO TOMA INVENTARIO
        mnuEconoFar_TomaInventario.setEnabled(pValor);
        mnuTomaInventario_Tradicional.setEnabled(pValor);
        //mnuInventario_Inicio
        //mnuInventario_Toma
        mnuTomaInventario_Ciclico.setEnabled(pValor);
        //mnuInventarioCiclico_Inicio
        //mnuInventarioCiclico_Toma
        //mnuTomaInventario_Diario
        //mnuInventarioDiario_Toma
        //mnuInventarioDiario_Diferencia
        mnuTomaInventario_Historico.setEnabled(pValor);
        mnuTomaInVentario_ItemsXLab.setEnabled(pValor);

        // MODULO ADMINISTRACION
        mnuEconoFar_Administracion.setEnabled(pValor);
        mnuAdministracion_Usuarios.setEnabled(pValor);
        mnuUsuarios_CambioUsuario.setEnabled(pValor);
        mnuUsuarios_CambioClave.setEnabled(pValor);
        mnuAdministracion_Mantenimiento.setEnabled(pValor);
        mnuMantenimiento_Usuarios.setEnabled(pValor);
        mnuMantenimiento_Cajas.setEnabled(pValor);
        mnuMantenimiento_Impresoras.setEnabled(pValor);
        mnuMantenimiento_Clientes.setEnabled(pValor);
        mnuMantenimiento_Parametros.setEnabled(pValor);
        //mnuMantenimiento_Carne
        mnuMantenimiento_MaquinaIP.setEnabled(pValor);
        //mnuMantenimiento_ImpresoraTermica
        mnuAdministracion_MovCaja.setEnabled(pValor);
        mnuMovCaja_RegistrarVentas.setEnabled(pValor);
        mnuMovCaja_ConsultarMov.setEnabled(pValor);
        mnuAdministracion_RegViajero.setEnabled(pValor);
        mnuAdministracion_ControlHoras.setEnabled(pValor);
        mnuAdministracion_Otros.setEnabled(pValor);
        //mnuOtros_MantProbisa
        mnuAdministracion_FondoSencillo.setEnabled(pValor);
        mnuAdministracion_ProdCambiados.setEnabled(pValor);
        mnuProdCambiados_PrecioCambProd.setEnabled(pValor);
        mnuAdministracion_PresupuestoVentas.setEnabled(pValor);

        // MODULO REPORTES
        mnuEconoFar_Reportes.setEnabled(pValor);
        mnuReportes_RegistroVentas.setEnabled(pValor);
        mnuReportes_VentasVendedor.setEnabled(pValor);
        //mnuReportes_VentasVendedor_Total
        //mnuReportes_VentasVendedor_Mezon
        //mnuReportes_VentasVendedor_Delivery
        //mnuReportes_VentasVendedor_Institucional
        mnuReportes_DetalleVentas.setEnabled(pValor);
        mnuReportes_ResumenVentaDia.setEnabled(pValor);
        mnuReportes_VentasProducto.setEnabled(pValor);
        //mnuReportes_VentasConvenio.setEnabled(pValor);
        //mnuReportes_VentasLinea.setEnabled(pValor);
        mnuReportes_VentasHora.setEnabled(pValor);
        mnuReportes_VentasDiaMes.setEnabled(pValor);
        mnuReportes_FaltaCero.setEnabled(pValor);
        //mnuReportes_ProductosABC
        //mnuReportes_UnidVtaLocal
        //mnuReportes_ProdSinVtaNDias

        // MODULO CAJA ELECTRONICA
        mnuEconofar_CajaElectronica.setEnabled(pValor);
        mnuCE_CierreTurno.setEnabled(pValor);
        mnuCE_CierreLocal.setEnabled(pValor);
        mnuCE_Prosegur.setEnabled(pValor);

        //MODULO CONTROL INGRESO
        //mnuEconoFar_ControlIngreso
        //mnuIngreso
        //mnuIngreso.setEnabled(pValor);
        mnuIngresoTemperatura.setEnabled(pValor);

    }

    public void muestraLoginCambioUser() {

        DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
        dlgLogin.setVisible(true);
        if (FarmaVariables.vAceptar) {
            FarmaVariables.dlgLogin = dlgLogin;
            VariablesCaja.vVerificaCajero = true;
            verificaRolUsuario();
            if (FarmaVariables.vEconoFar_Matriz) {
                mnuInventario_PedidoEspecial.setEnabled(false);
            }
            //INI ASOSA - 22/09/2015 - CTRLASIST
            boolean flagCA =
                UtilityControlAsistencia.determinarMarcacionGeneral(this, ConstantsControlAsistencia.ID_ENTRADA);
            if (!flagCA) {
                llamarDlgLogin();
            }
            //FIN ASOSA - 22/09/2015 - CTRLASIST
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


    private void salirSistema() {
        UtilityVentas.eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
        if (FarmaVariables.vEconoFar_Matriz)
            this.dispose();
        else
            System.exit(0);
    }

    private void salir(WindowEvent e) {
        salirSistema();
    }

    private void inicializa() {
        try {
            VariablesPtoVenta.vIndRecepCiega = DBPtoVenta.obtieneIndicadorTipoRecepcionAlmacen();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this,
                                     "Ocurri� un error al obtener el indicador del tipo de recepci�n de almacen : \n" +
                    sql.getMessage(), null);
        }
        if (VariablesPtoVenta.vIndRecepCiega.equalsIgnoreCase("S")) {
            mnuInventario_Mercaderia.setEnabled(true);
            mnuInventario_RecepcionProductos.setEnabled(true);
            mnuInventario_Recepcion.setEnabled(true);
        } else {
            mnuInventario_Mercaderia.setEnabled(true);
            mnuInventario_RecepcionProductos.setEnabled(true);
            mnuInventario_Recepcion.setEnabled(false);
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

    private void btnRevertir_actionPerformed(ActionEvent e) {

        //JCORTEZ 19.01.10 Se verificara indicador para validar proceso de pruebas.
        if (validaIndicadorPruebas()) {
            if (validaEsOperador()) {
                DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                dlgLogin.setRolUsuario(FarmaConstants.ROL_OPERADOR_SISTEMAS);
                dlgLogin.setVisible(true);

                if (FarmaVariables.vAceptar) {
                    FarmaUtility.moveFocus(txtRevertir);
                }
            }
        } else
            FarmaUtility.showMessage(this, "La opci�n de Revertir no se encuentra activa.", null);

    }

    private boolean validaIndicadorPruebas() {

        boolean resutl = false;
        String ind = "";
        try {
            ind = DBPtoVenta.obtenerIndReverLocal();
            if ((ind.trim()).equalsIgnoreCase("S")) {
                resutl = true;
            }
        } catch (SQLException sql) {
            log.error("", sql);
        }
        return resutl;

    }


    private void txtRevertir_keyPressed(KeyEvent e) {
        log.debug("Entra a revertir");
        log.debug("tecla ini cio: " + e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_INSERT) {

            log.debug(":::JCORTEZ::::VALIDACION PROCESO REVERSION");
            //JCORTEZ 19.01.10 Se verificara indicador para validar proceso de pruebas.
            if (validaIndicadorPruebas()) {
                if (validaEsOperador()) {
                    DlgLogin dlgLogin1 = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                    dlgLogin1.setRolUsuario(FarmaConstants.ROL_OPERADOR_SISTEMAS);
                    dlgLogin1.setVisible(true);

                    if (FarmaVariables.vAceptar) {
                        if (validaIndicadorRevertirLocal()) {
                            //    if (validaCantidadPruebasCompleta()){
                            VariablesPtoVenta.vFechaInicioPruebas = obtieneFechaInicioPrueba();
                            if (!VariablesPtoVenta.vFechaInicioPruebas.equalsIgnoreCase("")) {
                                boolean rptaRevertir = false;
                                rptaRevertir =
                                        JConfirmDialog.rptaConfirmDialogDefaultNo(null, "Va a empezar a iniciar el proceso de revertir. \n �Est� seguro de revertir las pruebas realizadas en el local nuevo a partir de la fecha " +
                                                                                  VariablesPtoVenta.vFechaInicioPruebas +
                                                                                  " ?.\n Si desea continuar acepte.");

                                if (rptaRevertir) {
                                    if (validaEsOperador()) {

                                        DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                                        dlgLogin.setRolUsuario(FarmaConstants.ROL_OPERADOR_SISTEMAS);
                                        dlgLogin.setVisible(true);

                                        if (FarmaVariables.vAceptar) {
                                            if (validaIndicadorRevertirLocal()) {
                                                if (validaCantidadPruebasCompleta()) {
                                                    revertirPruebasDeLocalNuevo();
                                                } else {
                                                    FarmaUtility.showMessage(this,
                                                                             "Ya no se puede revertir los datos de prueba en el local.\n Ya inici� y finaliz� la sesi�n de pruebas \n Comun�quese con el Operador.",
                                                                             null);
                                                }

                                            } else
                                                FarmaUtility.showMessage(this,
                                                                         "Ya no se puede revertir los datos de prueba en el local.\n Comun�quese con el Operador.",
                                                                         null);

                                        } else {
                                            FarmaUtility.showMessage(this, "La clave ingresada no es correcta. \n",
                                                                     null);
                                        }

                                    }
                                } else {
                                    return;
                                }
                                // }else{
                                //         FarmaUtility.showMessage(this,"Ya no se puede revertir los datos de prueba en el local.\n Ya inici� y finaliz� la sesi�n de pruebas \n Comun�quese con el Operador.",null);
                                //      }

                            }
                        } else
                            FarmaUtility.showMessage(this,
                                                     "Ya no se puede revertir los datos de prueba en el local.\n Comun�quese con el Operador.",
                                                     null);
                    }

                }
            }
        } else if (e.getKeyCode() == 36) {
            log.debug(":::JCORTEZ::::VALIDACION PROCESO REVERSION");
            //JCORTEZ 19.01.10 Se verificara indicador para validar proceso de pruebas.
            if (validaIndicadorPruebas()) {
                if (validaEsOperador()) {
                    DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                    dlgLogin.setRolUsuario(FarmaConstants.ROL_OPERADOR_SISTEMAS);
                    dlgLogin.setVisible(true);

                    if (FarmaVariables.vAceptar) {
                        if (validaIndicadorRevertirLocal()) {
                            if (validaCantidadPruebas()) {
                                grabaInicioFinPrueba("I");
                                lblMensaje.setVisible(true);
                                pnlEconoFar.setBackground(Color.ORANGE);
                                btnRevertir.setBackground(Color.ORANGE);
                                txtRevertir.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
                                txtRevertir.setBackground(Color.ORANGE);
                                btnRevertir.setForeground(Color.ORANGE);
                            } else {
                                FarmaUtility.showMessage(this,
                                                         "Ya no puede iniciar la prueba, porque ya se hizo una. \n Comun�quese con el Operador.",
                                                         null);
                                return;
                            }
                        } else {
                            FarmaUtility.showMessage(this,
                                                     "Ya no puede iniciar la prueba, ya se revirti� las pruebas en el local. \n Comun�quese con el Operador.",
                                                     null);
                            return;
                        }

                    }
                }
            }
        } /*else if (e.getKeyCode() == 35){
                if (validaEsOperador()){
                    DlgLogin dlgLogin = new DlgLogin(this,ConstantsPtoVenta.MENSAJE_LOGIN,true);
                    dlgLogin.setRolUsuario(FarmaConstants.ROL_OPERADOR_SISTEMAS);
                    dlgLogin.setVisible(true);

                    if ( FarmaVariables.vAceptar ){
                        grabaInicioFinPrueba("F");
                        lblMensaje.setVisible(false);
                        pnlEconoFar.setBackground(Color.WHITE);
                        txtRevertir.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        btnRevertir.setBackground(Color.WHITE);
                        txtRevertir.setBackground(Color.WHITE);
                        btnRevertir.setForeground(Color.WHITE);
                    }
                }
            }*/

        /*}else
            FarmaUtility.showMessage(this,"La opcion de reversi�n no esta habilitada en el local.",null);    */
    }

    private boolean validaEsOperador() {
        boolean vResultado = false;
        if (FarmaVariables.vNuSecUsu.equalsIgnoreCase(FarmaConstants.ROL_OPERADOR_SISTEMAS))
            vResultado = true;
        else
            vResultado = false;
        return vResultado;
    }

    private boolean validarPermiteRevertir() {
        boolean flag = false;
        String ind = "";
        try {
            ind = DBPtoVenta.obtenerIndReverPermitido();
            if ((ind.trim()).equalsIgnoreCase("S")) {
                flag = true;
            }
        } catch (SQLException sql) {
            log.error("", sql);
        }
        return flag;
    }

    private void revertirPruebasDeLocalNuevo() {
        boolean rptaRevertir = false;
        rptaRevertir =
                JConfirmDialog.rptaConfirmDialogDefaultNo(null, "�Est� seguro de revertir las pruebas realizadas en el local nuevo  a partir de la fecha " +
                                                          VariablesPtoVenta.vFechaInicioPruebas +
                                                          " ?.\n Si acepta se borrar� toda la informaci�n generada.");

        if (rptaRevertir) {
            if (validarPermiteRevertir()) {
                DlgProcesarRevertir vProcesaRevertir = new DlgProcesarRevertir(this, "", true);
                vProcesaRevertir.setVisible(true);
                if (FarmaVariables.vAceptar) {
                    FarmaUtility.showMessage(this, "El proceso de revertir se realiz� con �xito.\n" +
                            "Para continuar salir de la aplicaci�n.\n", null);
                    grabaInicioFinPrueba("F");
                    lblMensaje.setVisible(false);
                    pnlEconoFar.setBackground(Color.WHITE);
                    txtRevertir.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    btnRevertir.setBackground(Color.WHITE);
                    txtRevertir.setBackground(Color.WHITE);
                    btnRevertir.setForeground(Color.WHITE);

                } else {
                    FarmaUtility.showMessage(this, "Ocurri� un error al revertir, comun�quese con el Operador.", null);
                }
            } else {
                FarmaUtility.showMessage(this, "Ya pasaron m�s de 2 dias de pruebas, no se pueden revertir los cambio",
                                         null);
            }
        } else
            return;
    }

    private boolean validaIndicadorRevertirLocal() {
        boolean vResultado = false;
        try {
            vResultado = DBPtoVenta.obtieneIndicadorRevertirLocal();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al obtener el indicador de revertir del local. \n" +
                    sql.getMessage(), null);
            vResultado = false;
        }
        return vResultado;
    }

    private void grabaInicioFinPrueba(String tipo) {

        try {
            DBPtoVenta.grabaInicioFinPrueba(tipo);
            FarmaUtility.aceptarTransaccion();
        } catch (SQLException sql) {
            if (sql.getErrorCode() == 20000) {
                log.error("", sql);
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(this, "Ocurri� un error al grabar inicio fin. \n" +
                        sql.getMessage(), null);
            } else {
                log.error("", sql);
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(this, "Ocurri� un error al grabar inicio fin. \n" +
                        sql.getMessage(), null);
            }

        }
    }

    private boolean validaCantidadPruebas() {
        int cantidad = -1;
        try {
            cantidad = DBPtoVenta.obtieneCantidadPruebas();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al verificar inicio de prueba. \n" +
                    sql.getMessage(), null);

        }
        if (cantidad == 0)
            return true;
        return false;
    }

    private boolean validaCantidadPruebasCompleta() {
        int cantidad = -1;
        try {
            cantidad = DBPtoVenta.obtieneCantidadPruebasCompletas();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al verificar inicio y fin de prueba. \n" +
                    sql.getMessage(), null);

        }
        if (cantidad == 1)
            return true;
        return false;
    }

    private String obtieneFechaInicioPrueba() {
        String fecha = "";
        try {
            fecha = DBPtoVenta.obtieneFechaInicioDePruebas();
            log.debug("fecha 1: " + fecha);
            if (!fecha.equalsIgnoreCase("N")) {
                return fecha;
            } else {
                fecha = "";
                return fecha;
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al obtener la fecha inicial de pruebas. \n" +
                    sql.getMessage(), null);
            fecha = "";
        }
        log.debug("fecha 2: " + fecha);
        return fecha;
    }

    /**
     * Recupera la direccion Domicilio Fiscal
     * @author ERIOS
     * @since 06.06.2013
     */
    private void cargaDireccionFiscal() {
        try {
            ArrayList lstDirecFiscal = DBPtoVenta.obtieneDireccionMatriz();
            VariablesPtoVenta.vDireccionMatriz = FarmaUtility.getValueFieldArrayList(lstDirecFiscal, 0, 0);
            VariablesPtoVenta.vDireccionCortaMatriz = FarmaUtility.getValueFieldArrayList(lstDirecFiscal, 0, 1);
        } catch (SQLException sql) {
            log.warn("Error al obtener la direcci�n de la Direcci�n Fiscal." + sql.getMessage());
        }
    }

    private void obtieneIndDireMat() {
        try {
            VariablesPtoVenta.vIndDirMatriz = DBPtoVenta.obtieneIndDirMatriz();
        } catch (SQLException sql) {
            log.error("", sql);
        }
    }

    /**
     * Obtiene indicador de Direccion Local
     * @author ERIOS
     * @since 12.09.2013
     */
    public void obtieneIndDireLocal() {
        try {
            VariablesPtoVenta.vIndDirLocal = DBPtoVenta.obtieneIndDirLocal();
        } catch (SQLException sql) {
            log.error("", sql);
        }
        log.debug("VariablesPtoVenta.vIndDirLocal=" + VariablesPtoVenta.vIndDirLocal);
    }

    /**
     * Obtiene indicador de Direccion Local
     * @author ERIOS
     * @since 12.09.2013
     */
    public static void obtieneIndRegistroVentaRestringida() {
        try {
            VariablesPtoVenta.vIndRegistroVentaRestringida = DBPtoVenta.obtieneIndRegistroVentaRestringida();
        } catch (SQLException sql) {
            log.error("", sql);
        }
        log.debug("VariablesPtoVenta.vIndRegistroVentaRestringida=" + VariablesPtoVenta.vIndRegistroVentaRestringida);
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

    private boolean validaIndicadorControlSobres() {
        boolean vResultado = false;
        try {
            vResultado = DBCaja.obtieneIndicadorControlSobres();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Ocurri� un error al obtener el indicador control sobre. \n" +
                    sql.getMessage(), null);
            vResultado = false;
        }
        return vResultado;
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

    private void verificaRolUsuario_sinAdmin() {
        ArrayList rolesUsuario = new ArrayList();
        try {
            rolesUsuario = recuperaRoles();
            System.out.println("verificaRolUsuario_sinAdmin");
            for (int i = 0; i < rolesUsuario.size(); i++) {
                System.out.println(" ==> ROL[" + i + "]: " + rolesUsuario.get(i).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        opcionesComunes();

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_VENDEDOR)) {
            opcionesVendedor();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_CAJERO)) {
            opcionesCajero();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_CONTABILIDAD) ||
            FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_LECTURA_REPORTES)) {
            opcionesProsegur();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
            opcionesAuditoria();
        }

        if (FarmaVariables.dlgLogin.verificaRol(ConstantsPtoVenta.ROL_ASISTENTE_AUDITORIA)) {
            opcionesAsistenteAuditoria();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_OPERADOR_SISTEMAS)) {
            opcionesOperador();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_INVENTARIADOR)) {
            opcionesInventariador();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_LECTURA_REPORTES)) {
            opcionesReportes();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_LECTURA_INVENTARIO)) {
            opcionesLecturaInventario();
        }

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_SUPERVISOR_VENTAS)) {
            opcionesSupervisorVentas();
        }

        bloquearOpcionesPorCompania();
    }

    /**
     * Lee properties para recuperar la version del sistema
     * @author ERIOS
     * @since 19.02.2013
     * @return
     */
    private boolean readFileFarmaVentaProperties() {
        Properties prop = new Properties();
        try {
            prop.load(FrmEconoFar.class.getResourceAsStream("/farmaventa.properties"));
            //ERIOS 2.2.9 Se guarda los datos de la version
            FarmaVariables.vNombreModulo = "FarmaVenta";
            FarmaVariables.vVersion = prop.getProperty("version");
            FarmaVariables.vCompilacion = prop.getProperty("compilacion");

            FarmaConnection.setVersion();
            DlgProcesar.setVersion();

            VariablesPtoVenta.tituloBaseFrame =
                    FarmaVariables.vNombreModulo + " " + FarmaVariables.vVersion + " - " + FarmaVariables.vCompilacion;
            log.info("Version: " + VariablesPtoVenta.tituloBaseFrame);
        } catch (IOException | SQLException e) {
            log.error("", e);
            return false;
        }
        return true;
    }

    /**
     * Lee y carga el icono del sistema.
     * @author ERIOS
     * @since 27.02.2013
     */
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

    /**
     * Habilita opciones del rol Cajero
     * @author ERIOS
     * @since 20.06.2013
     */
    private void opcionesCajero() {

        //mnuEconoFar_Ventas
        mnuVentas_Recargas.setEnabled(true);

        mnuEconoFar_Caja.setEnabled(true);
        mnuCaja_MovimientosCaja.setEnabled(true);
        mnuCaja_AperturarCaja.setEnabled(true);
        mnuCaja_CerrarCaja.setEnabled(true);
        mnuCaja_ConfigurarCaja.setEnabled(true);
        //ERIOS 2.4.4 Habilitado para cobro de pedido pendiente
        //if(FarmaVariables.vTipCaja.equalsIgnoreCase(ConstantsPtoVenta.TIP_CAJA_TRADICIONAL)){
        mnuCaja_CobrarPedido.setEnabled(true);


        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(true);
        log.info("Habilita opcion Producto Insumo");

        //}

        //mnuCaja_CorreccionComprobantes.setEnabled(true);
        mnuCaja_ReimpresionPedido.setEnabled(true);
        mnuCaja_RegulaVtaManual.setEnabled(true);
        mnuCaja_Utilitarios.setEnabled(true);
        mnuCaja_PruebaImpresora.setEnabled(true);
        //mnuCaja_Pinpad.setEnabled(true);
        //mnuCaja_Pinpad_Visa.setEnabled(true);
        //mnuCaja_Pinpad_Mastercard.setEnabled(true);
        mnuCaja_AbrirGabeta.setEnabled(true);
        mnuCaja_ControlSobresParciales.setEnabled(validaIndicadorControlSobres());
        mnuCaja_Recaudacion.setEnabled(true);
        mnuCaja_CMR.setEnabled(true);
        mnuCaja_Citibank.setEnabled(true);
        mnuCaja_Claro.setEnabled(true);
        mnuCaja_Prestamos_Citibank.setEnabled(true);

        //mnuEconoFar_Inventario
        mnuInventario_PedidosLocales.setEnabled(false);
        if (FarmaVariables.vEconoFar_Matriz) {
            mnuInventario_PedidoAdicional.setVisible(false);
            mnuInventario_PedidoEspecial.setVisible(false);
        }

        //mnuEconoFar_Administracion
        mnuAdministracion_ControlHoras.setEnabled(false);

        mnuEconofar_CajaElectronica.setEnabled(true);
        mnuCE_CierreTurno.setEnabled(true);
    }

    private void opcionesVendedor() {
        mnuEconoFar_Ventas.setEnabled(true);
        mnuVentas_GenerarPedido.setEnabled(true);
        mnuVentas_Recargas.setEnabled(true);
        mnuVentas_DeliveryAutomatico.setEnabled(true);
        mnuCaja_ServiciosVisa.setEnabled(true);
        mnuKardex_ConsumoInter.setEnabled(true);
        mnuAdministracion_ControlHoras.setEnabled(false);
        mnuInventario_PedidosLocales.setEnabled(false);
        mnuKardex_ConsumoInter.setEnabled(true);
        if (FarmaVariables.vEconoFar_Matriz) {
            mnuInventario_PedidoAdicional.setVisible(false);
            mnuInventario_PedidoEspecial.setVisible(false);
        }
        mnuVentas_MedidaPresion.setEnabled(true);
        mnuEconoFar_Inventario.setEnabled(false);
        mnuInventario_Mercaderia.setEnabled(true);
        jmnPuntos.setEnabled(true);

    }

    private void opcionesAdministrador() {
        validaOpcionesMenu(true);


        if (UtilityFondoSencillo.indActivoFondo()) {
            mnuAdministracion_FondoSencillo.setVisible(true);
        }
        mnuVentas_Digemid.setEnabled(true);

        mnuAdministracion_ControlHoras.setEnabled(false);
        mnuInventarioCiclico_Inicio.setEnabled(false);

        mnuInventario_PedidosLocales.setEnabled(true);
        if (FarmaVariables.vEconoFar_Matriz) {
            mnuInventario_PedidoAdicional.setVisible(false);
            mnuInventario_PedidoEspecial.setVisible(false);
        }

        //mnuAdm_mntoCli.setVisible(UtilityPuntos.isActivoFuncionalidad());   //ASOSA - 28/04/2015 - PTOSYAYAYAYA
        mnuAdm_mntoCli.setVisible(true); //AOVIEDO - 14/07/2017

        mnuVentas_MedidaPresion.setEnabled(true);
        mnuVentas_Recargas.setEnabled(true);
        mnuMantenimiento_Carne.setEnabled(true);
        mnuCE_Prosegur.setEnabled(true);

        mnuCaja_ControlSobresParciales.setEnabled(validaIndicadorControlSobres());
        mnuCE_Prosegur.setEnabled(isLocalProsegur());
        //CHUANES 28/08/2015
        //CAMBIO DEL ROLES AHORA SOLO A CONTROL DE INGRESO DEBE INGRESAR EL ADMINISTRADOR
        //mnuIngreso.setEnabled(true);
        mnuIngresoTemperatura.setEnabled(true);
        mnuInventario_Mercaderia.setEnabled(true);

        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(true);
        log.info("Habilita opcion Producto Insumo");


        if (UtilityFondoSencillo.indActivoFondo()) {
            mnuAdministracion_FondoSencillo.setEnabled(true);
        } else {
            mnuAdministracion_FondoSencillo.setEnabled(false);
        }

        if (UtilityRecepCiega.indHabDatosTransp()) {
            mnuInventario_IngTransportista.setEnabled(true);
        }

        jmnPuntos.setEnabled(true); // AGREGAR ESTA LINEA

        if (!FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
            mnuTomaInventario_Historico.setEnabled(false);
        } else {
            mnuTomaInventario_Historico.setEnabled(true);
        }
        mnuEconoFar_Inventario.setEnabled(true);
        mnuEconoFar_Ventas.setEnabled(true);
        mnuTomaInventario_Ciclico.setEnabled(true);
        mnuTomaInventario_Diario.setEnabled(true);
        mnuGenerarData.setEnabled(true);
        mnuInventario_Inicio.setEnabled(true);
        mnuEconoFar_ControlIngreso.setEnabled(true);

    }

    private void opcionesComunes() {
        UtilityPtoVenta.muestraUser(this);
        validaOpcionesMenu(false);

        mnuEconoFar_Administracion.setEnabled(true);
        mnuAdministracion_Usuarios.setEnabled(true);
        mnuUsuarios_CambioUsuario.setEnabled(true);
        mnuUsuarios_CambioClave.setEnabled(true);
        mnuInventario_IngTransportista.setEnabled(false);
        mnuVentas_MedidaPresion.setEnabled(false);
        mnuAdministracion_FondoSencillo.setVisible(false);

    }

    private void opcionesProsegur() {
        mnuCE_Prosegur.setEnabled(isLocalProsegur());
    }

    private void opcionesAuditoria() {
        mnuAdministracion_Mantenimiento.setEnabled(true);
        mnuEconoFar_TomaInventario.setEnabled(true);
        mnuMantenimiento_Usuarios.setEnabled(true);
        mnuInventarioCiclico_Inicio.setEnabled(true);
        mnuAdministracion_ControlHoras.setEnabled(false);
        if (!FarmaVariables.vEconoFar_Matriz) {
            // MODULO INVENTARIO
            mnuEconoFar_Inventario.setEnabled(true);
            mnuInventario_GuiaEntrada.setEnabled(true);
            mnuCotizacionPrecio.setEnabled(true);
            mnuInventario_Kardex.setEnabled(true);
            mnuInventario_Transferencias.setEnabled(true);
            mnuInventario_MercaderiaDirecta.setEnabled(true);
            mnuInventario_RecepcionProductos.setEnabled(true);

            mnuInventario_Guias.setEnabled(true);
            mnuInventario_Ajustes.setEnabled(true);

            //--@@ RHERRERA: 26.09.2014
            mnuKardex_ConsumoInter.setEnabled(false);
            log.info("Deshabilita opcion Producto Insumo");
            //Toma Inventario
            mnuTomaInventario_Tradicional.setEnabled(true);
            mnuTomaInventario_Ciclico.setEnabled(true);
            mnuTomaInventario_Historico.setEnabled(true);
            mnuTomaInVentario_ItemsXLab.setEnabled(true);
            mnuInventario_PedidosLocales.setEnabled(true);
            mnuInventario_PedidoAdicional.setVisible(true);
            mnuInventario_PedidoEspecial.setVisible(true);
            mnuKardex_ConsumoInter.setEnabled(false);

            mnuEconoFar_Inventario.setEnabled(true);
            mnuEconoFar_Ventas.setEnabled(true);
            mnuTomaInventario_Ciclico.setEnabled(true);
            mnuTomaInventario_Diario.setEnabled(true);
            mnuGenerarData.setEnabled(true);
            mnuInventario_Inicio.setEnabled(true);
            mnuEconoFar_ControlIngreso.setEnabled(true);
        } else {
            mnuTomaInventario_Tradicional.setEnabled(false);
            mnuTomaInventario_Ciclico.setEnabled(true);
            mnuTomaInventario_Historico.setEnabled(true);

            mnuInventario_PedidosLocales.setEnabled(true);
            mnuInventario_PedidoAdicional.setVisible(false);
            mnuInventario_PedidoEspecial.setVisible(false);
        }
    }

    private void opcionesAdmLocal_2() {
        mnuEconoFar_TomaInventario.setEnabled(true);
        mnuInventarioCiclico_Inicio.setEnabled(true);
        if (!FarmaVariables.vEconoFar_Matriz) {
            // MODULO INVENTARIO
            mnuEconoFar_Inventario.setEnabled(true);
            mnuInventario_GuiaEntrada.setEnabled(true);
            mnuCotizacionPrecio.setEnabled(true);
            mnuInventario_Kardex.setEnabled(true);
            mnuInventario_Transferencias.setEnabled(true);
            mnuInventario_MercaderiaDirecta.setEnabled(true);
            mnuInventario_RecepcionProductos.setEnabled(true);

            mnuInventario_Guias.setEnabled(true);
            mnuInventario_Ajustes.setEnabled(true);

            //--@@ RHERRERA: 26.09.2014
            //Toma Inventario
            mnuTomaInventario_Tradicional.setEnabled(true);
            mnuTomaInventario_Ciclico.setEnabled(true);
            mnuTomaInventario_Historico.setEnabled(true);
            mnuTomaInVentario_ItemsXLab.setEnabled(true);
            mnuInventario_PedidosLocales.setEnabled(true);
            mnuInventario_PedidoAdicional.setVisible(true);
            mnuInventario_PedidoEspecial.setVisible(true);
        } else {
            mnuTomaInventario_Ciclico.setEnabled(true);
            mnuTomaInventario_Historico.setEnabled(true);

            mnuInventario_PedidosLocales.setEnabled(true);
        }
    }

    private void opcionesAsistenteAuditoria() {
        mnuEconoFar_TomaInventario.setEnabled(true);
        mnuInventarioCiclico_Inicio.setEnabled(true);
        mnuAdministracion_ControlHoras.setEnabled(false);
        if (!FarmaVariables.vEconoFar_Matriz) {
            // MODULO INVENTARIO
            mnuEconoFar_Inventario.setEnabled(true);
            mnuInventario_GuiaEntrada.setEnabled(true);
            mnuCotizacionPrecio.setEnabled(true);
            mnuInventario_Kardex.setEnabled(true);
            mnuInventario_Transferencias.setEnabled(true);
            mnuInventario_MercaderiaDirecta.setEnabled(true);
            mnuInventario_RecepcionProductos.setEnabled(true);
            //--@@ RHERRERA: 26.09.2014
            mnuKardex_ConsumoInter.setEnabled(false);
            log.info("Habilita opcion Producto Insumo");

            mnuInventario_Guias.setEnabled(true);
            mnuInventario_Ajustes.setEnabled(true);
            //Toma Inventario
            mnuTomaInventario_Tradicional.setEnabled(true);
            mnuTomaInventario_Ciclico.setEnabled(true);
            mnuTomaInventario_Historico.setEnabled(true);
            mnuTomaInVentario_ItemsXLab.setEnabled(true);
            mnuInventario_PedidosLocales.setEnabled(true);
            mnuInventario_PedidoAdicional.setVisible(true);
            mnuInventario_PedidoEspecial.setVisible(true);

            mnuKardex_ConsumoInter.setEnabled(false);

        } else {
            mnuTomaInventario_Tradicional.setEnabled(false);
            mnuTomaInventario_Ciclico.setEnabled(true);
            mnuTomaInventario_Historico.setEnabled(true);
            mnuInventario_PedidosLocales.setEnabled(true);
            mnuInventario_PedidoAdicional.setVisible(false);
            mnuInventario_PedidoEspecial.setVisible(false);
        }
    }

    private void opcionesOperador() {
        validaOpcionesMenu(true);

        mnuVentas_Digemid.setEnabled(true);

        if (UtilityFondoSencillo.indActivoFondo()) {
            mnuAdministracion_FondoSencillo.setVisible(true);
        }

        if (UtilityRecepCiega.indHabDatosTransp()) {
            mnuInventario_IngTransportista.setEnabled(true);
        }
        mnuInventarioCiclico_Inicio.setEnabled(true);

        mnuInventario_PedidosLocales.setEnabled(true);
        if (FarmaVariables.vEconoFar_Matriz) {
            mnuInventario_PedidoAdicional.setVisible(false);
            mnuInventario_PedidoEspecial.setVisible(false);
        }
        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(true);

        mnuVentas_MedidaPresion.setEnabled(false);

        mnuVentas_Recargas.setEnabled(true);

        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(true);
        log.info("Habilita opcion Producto Insumo");

        mnuCaja_ControlSobresParciales.setEnabled(validaIndicadorControlSobres());
        mnuCE_Prosegur.setEnabled(isLocalProsegur());

        if (UtilityFondoSencillo.indActivoFondo()) {
            mnuAdministracion_FondoSencillo.setEnabled(true);
        } else {
            mnuAdministracion_FondoSencillo.setEnabled(false);
        }

    }

    private void opcionesInventariador() {
        mnuEconoFar_TomaInventario.setEnabled(true);
        mnuTomaInventario_Tradicional.setEnabled(true);
        mnuInventario_Toma.setEnabled(true);
        mnuTomaInventario_Ciclico.setEnabled(true);
        mnuInventarioCiclico_Inicio.setEnabled(false);
        mnuInventarioCiclico_Toma.setEnabled(true);
        mnuAdministracion_ControlHoras.setEnabled(false);
        mnuInventario_PedidosLocales.setEnabled(true);
        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(false);
        mnuInventario_PedidosLocales.setEnabled(true);
        if (FarmaVariables.vEconoFar_Matriz) {
            mnuInventario_PedidoAdicional.setVisible(false);
            mnuInventario_PedidoEspecial.setVisible(false);
        }
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_INVENTARIADOR)) {
            mnuEconoFar_Caja.setEnabled(false);
            mnuEconoFar_Inventario.setEnabled(false);
            mnuEconoFar_Ventas.setEnabled(false);
            mnuTomaInventario_Ciclico.setEnabled(false);
            mnuTomaInventario_Diario.setEnabled(false);
            mnuGenerarData.setEnabled(false);
            mnuInventario_Inicio.setEnabled(false);
            mnuEconoFar_ControlIngreso.setEnabled(false);
        }
    }

    private void opcionesReportes() {
        // MODULO INVENTARIO
        mnuEconoFar_Inventario.setEnabled(true);
        mnuInventario_GuiaEntrada.setEnabled(true);
        mnuCotizacionPrecio.setEnabled(true);
        // MODULO ADMINISTRACION
        mnuEconoFar_Administracion.setEnabled(true);
        mnuAdministracion_Usuarios.setEnabled(false);
        mnuUsuarios_CambioUsuario.setEnabled(false);
        mnuUsuarios_CambioClave.setEnabled(false);
        mnuAdministracion_MovCaja.setEnabled(true);
        mnuMovCaja_ConsultarMov.setEnabled(true);
        // MODULO REPORTES
        mnuEconoFar_Reportes.setEnabled(true);
        mnuReportes_RegistroVentas.setEnabled(true);
        mnuReportes_VentasVendedor.setEnabled(true);
        mnuReportes_DetalleVentas.setEnabled(true);
        mnuReportes_ResumenVentaDia.setEnabled(true);
        mnuReportes_VentasProducto.setEnabled(true);
        mnuReportes_VentasHora.setEnabled(true);
        mnuReportes_VentasDiaMes.setEnabled(true);
        mnuReportes_FaltaCero.setEnabled(true);
        // MODULO CAJA ELECTRONICA
        mnuEconofar_CajaElectronica.setEnabled(true);
        mnuCE_CierreLocal.setEnabled(true);
        mnuCE_CierreTurno.setEnabled(true);
        mnuAdministracion_ControlHoras.setEnabled(false);
    }

    private void opcionesLecturaInventario() {
        // MODULO INVENTARIO
        mnuEconoFar_Inventario.setEnabled(true);
        mnuInventario_GuiaEntrada.setEnabled(true);
        mnuCotizacionPrecio.setEnabled(true);
        mnuInventario_Kardex.setEnabled(true);
        mnuInventario_Transferencias.setEnabled(true);
        mnuInventario_MercaderiaDirecta.setEnabled(true);
        mnuAdministracion_ControlHoras.setEnabled(false);
        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(false);
    }

    private void opcionesSupervisorVentas() {
        mnuEconoFar_Ventas.setEnabled(true);
        mnuVentas_DeliveryAutomatico.setEnabled(true);

        mnuEconoFar_Caja.setEnabled(true);
        mnuCaja_CorreccionComprobantes.setEnabled(true);

        mnuEconoFar_Inventario.setEnabled(true);
        mnuInventario_GuiaEntrada.setEnabled(true);
        mnuCotizacionPrecio.setEnabled(true);
        mnuInventario_Kardex.setEnabled(true);
        mnuInventario_Transferencias.setEnabled(true);

        mnuInventario_MercaderiaDirecta.setEnabled(true);

        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(true);

        mnuInventario_Ajustes.setEnabled(true);
        //--@@ RHERRERA: 26.09.2014
        mnuKardex_ConsumoInter.setEnabled(true);
        log.info("Habilita opcion Producto Insumo");
        mnuInventario_RecepcionLocales.setEnabled(true);

        mnuInventario_MercaderiaDirecta.setEnabled(true);

        mnuEconoFar_Administracion.setEnabled(true);
        mnuAdministracion_MovCaja.setEnabled(true);
        mnuMovCaja_ConsultarMov.setEnabled(true);

        mnuEconoFar_Reportes.setEnabled(true);
        mnuReportes_RegistroVentas.setEnabled(true);
        mnuReportes_VentasVendedor.setEnabled(true);
        mnuReportes_DetalleVentas.setEnabled(true);
        mnuReportes_ResumenVentaDia.setEnabled(true);
        mnuReportes_VentasProducto.setEnabled(true);
        mnuReportes_VentasHora.setEnabled(true);
        mnuReportes_VentasDiaMes.setEnabled(true);
        mnuReportes_FaltaCero.setEnabled(true);
        mnuReportes_ProductosABC.setEnabled(true);
        mnuReportes_UnidVtaLocal.setEnabled(true);
        mnuReportes_ProdSinVtaNDias.setEnabled(true);

        mnuEconofar_CajaElectronica.setEnabled(true);
        mnuCE_CierreLocal.setEnabled(true);

        mnuAdministracion_ControlHoras.setEnabled(true);
    }

    private void mnuCaja_Pinpad_Mastercard_actionPerformed(ActionEvent e) {
        DlgCierrePinpad dlgCierrePinpad = new DlgCierrePinpad(this, "", true);
        dlgCierrePinpad.setVisible(true);
    }

    private void mnuCaja_Reimpresion_Pinpad_MC_actionPerformed(ActionEvent e) {
        DlgReimpresionPinpad dlgReimpresionPinpad = new DlgReimpresionPinpad(this, "", true);
        dlgReimpresionPinpad.setVisible(true);
    }

    private void mnuCaja_reporTot_Pinpad_MC_actionPerformed(ActionEvent e) {
        DlgReporteTotalPinpad dlgReporteTotalPinpad = new DlgReporteTotalPinpad(this, "", true);
        dlgReporteTotalPinpad.setVisible(true);
    }

    private void mnuCaja_reporDet_Pinpad_MC_actionPerformed(ActionEvent e) {
        DlgReporteDetalladoPinpad dlgReporteDetalladoPinpad = new DlgReporteDetalladoPinpad(this, "", true);
        dlgReporteDetalladoPinpad.setVisible(true);
    }

    private void mnuCaja_AnulacionTran_Pinpad_Visa_actionPerformed(ActionEvent e) {
        DlgEleccionTranAnularPinpad dlgEleccionTranAnularPinpad = new DlgEleccionTranAnularPinpad(this, "", true);
        dlgEleccionTranAnularPinpad.setVisible(true);
    }

    private void mnuAyuda_AcercaDe_actionPerformed(ActionEvent e) {
        DlgAcercaDe dlgAcercaDe = new DlgAcercaDe(this, "", true);
        dlgAcercaDe.setVisible(true);
    }

    private void mnuCE_ReciboSencillo_actionPerformed(ActionEvent e) {
        DlgReciboPagoSencillo dlgReciboPagoSencillo = new DlgReciboPagoSencillo(this, "", true, false);
        dlgReciboPagoSencillo.setTitle(ConstantsCajaElectronica.TIT_RECIBO_SENCILLO);
        if (dlgReciboPagoSencillo.permisoAbrirVentana()) {
            dlgReciboPagoSencillo.setVisible(true);
        }
    }

    private void mnuCE_PagoSencillo_actionPerformed(ActionEvent e) {
        DlgReciboPagoSencillo dlgReciboPagoSencillo = new DlgReciboPagoSencillo(this, "", true, true);
        dlgReciboPagoSencillo.setTitle(ConstantsCajaElectronica.TIT_PAGO_SENCILLO);
        if (dlgReciboPagoSencillo.permisoAbrirVentana()) {
            dlgReciboPagoSencillo.setVisible(true);
        }
    }

    private void mnCaja_ReimpresionLote_Pinpad_MC_actionPerformed(ActionEvent e) {
        DlgReimpresionLotePinpad dlgReimpresionLotePinpad = new DlgReimpresionLotePinpad(this, "", true);
        dlgReimpresionLotePinpad.setVisible(true);
    }

    private void mnuReportes_ListPsicotropicos_actionPerformed(ActionEvent e) {
        DlgReportePsicotropicos dlgReportePsicotropicos = new DlgReportePsicotropicos(this, "", true);
        dlgReportePsicotropicos.setVisible(true);
    }

    private void mnuReportes_ListRecetario_actionPerformed(ActionEvent e) {
        DlgListaRecetarios dlgListaRecetario = new DlgListaRecetarios(this, "", true);
        dlgListaRecetario.setVisible(true);
    }

    private void bloquearOpcionesPorCompania() {
        log.debug("[INICIO] - bloquearOpcionesPorCompania");
        try {
            FacadeCaja facadeCaja = new FacadeCaja();
            ArrayList<ArrayList<String>> lista = facadeCaja.obtenerOpcionesBloqueadas();
            if (lista != null && lista.size() > 0) {
                int indiceEncontrado = -1;
                for (Field f : getClass().getDeclaredFields()) {
                    //if(f.getType().toString().equals(TIPO_VARIABLE_MENUITEM)){
                    String nombre = f.getName();
                    for (int i = 0; i < lista.size(); i++) {
                        String nombreBloqueado = lista.get(i).get(0);
                        if (nombre.equals(nombreBloqueado)) {
                            log.debug("------------bloqueando....-------------------");
                            log.debug(" nombre : " + f.getName());
                            JMenuItem object = (JMenuItem)f.get(this);
                            object.setEnabled(false);
                            indiceEncontrado = i;
                            break;
                        }
                    }
                    if (indiceEncontrado > -1) {
                        lista.remove(indiceEncontrado);
                        indiceEncontrado = -1;
                    }
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        log.debug("[FIN] - bloquearOpcionesPorCompania");
    }

    private void mnuCE_ReprocesaConcil_actionPerformed(ActionEvent e) {
        DlgDatosTarjetaPinpad.reprocesarConciliaciones();
    }

    private void mnuInventario_RecepcionRM_actionPerformed(ActionEvent e) {
        /*DlgRecepcionRM dlgRecepcionRM = new DlgRecepcionRM(this,"",true);
        dlgRecepcionRM.setVisible(true);*/
    }

    private void prueba_actionPerformed(ActionEvent e) {
        try {
            UtilityInventario.imprimeGuia(null, "15/11/2013", null, false);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private void mnuInventario_StockNegativo_actionPerformed(ActionEvent e) {
        DlgListadoStockNegativo dlgListadoStockNegativo = new DlgListadoStockNegativo(this, "", true);
        dlgListadoStockNegativo.setVisible(true);
    }

    public void verificaRolUsuNuevo() {
        verificaRolUsuario();
    }

    //LLEIVA 19-Feb-2014 Menu para listado de pedidos pagados con pinpad

    private void mnuCaja_PedidoPinpad_actionPerformed(ActionEvent e) {
        DlgListadoPedidoPinpad dlgListadoPedidoPinpad = new DlgListadoPedidoPinpad(this, "", true);
        dlgListadoPedidoPinpad.setVisible(true);
    }

    private void mnuReportes_ListGuias_actionPerformed(ActionEvent e) {
        DlgReporteGuia dlgReporteGuia = new DlgReporteGuia(this, "", true);
        dlgReporteGuia.setVisible(true);
    }


    /** Valida que usuario logueado sea del Rol Quimico
     * LTAVARA
     * 28.08.2014
     * */

    private boolean cargaLoginAdmLocal() {
        String numsec = FarmaVariables.vNuSecUsu;
        String idusu = FarmaVariables.vIdUsu;
        String nomusu = FarmaVariables.vNomUsu;
        String apepatusu = FarmaVariables.vPatUsu;
        String apematusu = FarmaVariables.vMatUsu;

        try {
            DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
            //Se validara por el rol dependiendo del tipo de pedido
            //14.11.2007  dubilluz modificacion

            dlgLogin.setRolUsuario(FarmaConstants.ROL_ADMLOCAL);

            dlgLogin.setVisible(true);
            FarmaVariables.vNuSecUsu = numsec;
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
            FarmaUtility.showMessage(this, "Ocurri� un error al validar rol de usuariario \n : " + e.getMessage(),
                                     null);
        }
        return FarmaVariables.vAceptar;
    }

    private void ingresoComprobanteManual() {
        DlgResumenPedido dlgResumenPedido = new DlgResumenPedido(this, "", true, true);
        dlgResumenPedido.setFrameEconoFar(this);
        dlgResumenPedido.setVisible(true);
        dlgResumenPedido = null;
    }

    private void mnuCaja_RegulaVtaManual_actionPerformed(ActionEvent e) {
        boolean isMigraSAP = UtilityInventario.is_Migra_Sap();
        if (isMigraSAP) {
            mnuCaja_RegulaVtaManual.setEnabled(true);
            FarmaUtility.showMessage(this, "El local no tiene activo la funcionalidad.", null);
        } else {
            ingresoComprobanteManual();
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

    private void saldoPuntos_actionPerformed(ActionEvent e) {
        if (UtilityPuntos.isActivoFuncionalidad()) {
            DlgConsultaSaldo dlgResumenPedido =
                new DlgConsultaSaldo(this, "Consular Saldo Monedero - Escanear tarjeta", true);
            dlgResumenPedido.setVisible(true);
        } else
            FarmaUtility.showMessage(this, "No esta activa la opci�n.", null);
    }

    private void recuperaPuntos_actionPerformed(ActionEvent e) {
        if (UtilityPuntos.isActivoFuncionalidad()) {
            DlgRecuperarPuntos dlgRecupera = new DlgRecuperarPuntos(this, "", true);
            dlgRecupera.setVisible(true);
        } else
            FarmaUtility.showMessage(this, "No esta activa la opci�n.", null);
    }

    private void mnuInventario_BandejasDevolver_actionPerformed(ActionEvent e) {
        DlgRptBandejas dlgBandeja = new DlgRptBandejas(this, "", true);
        dlgBandeja.setVisible(true);
    }

    private void jmmBloqueoTarjeta_actionPerformed(ActionEvent e) {
        if (UtilityPuntos.isActivoFuncionalidad()) {
            DlgVerificaDocRedencionBonifica dlgVerifica =
                new DlgVerificaDocRedencionBonifica(this, "", true, ConstantsPuntos.BLOQUEO_TARJETA);
            dlgVerifica.setIsRequiereDni(false);
            dlgVerifica.setIsRequiereTarjeta(true);
            dlgVerifica.setVisible(true);
            if (FarmaVariables.vAceptar) {
            } else {
            }
        } else {
            FarmaUtility.showMessage(this, "No esta activa la funcionalidad de Monedero.", null);
        }
    }

    private void jmmDesafiliacionx1_actionPerformed(ActionEvent e) {
        if (UtilityPuntos.isActivoFuncionalidad()) {
            DlgConsultaSaldo dlgResumenPedido =
                new DlgConsultaSaldo(this, "Desafiliacion Programa Monedero - Escanear Tarjeta", true, true);
            dlgResumenPedido.setVisible(true);
        } else
            FarmaUtility.showMessage(this, "No esta activa la opci�n.", null);
    }

    /**
     * @author ASOSA
     * @since 07/08/2015
     * @type RAIZ
     * @param e
     */
    private void mnuCaja_Raiz_actionPerformed(ActionEvent e) {
        DlgRecaudacionPrestamosRaiz dlgRecaudacionPrestamosRaiz = new DlgRecaudacionPrestamosRaiz(this, "", true);
        dlgRecaudacionPrestamosRaiz.setVisible(true);
    }

    /**
     * @author ASOSA
     * @since 17/05/2016
     * @type INCASUR
     * @param e
     */
    private void mnuCaja_Incasur_actionPerformed(ActionEvent e) {
        DlgRecaudacionPrestamosIncasur dlgRecaudacionPrestamosIncasur =
            new DlgRecaudacionPrestamosIncasur(this, "", true);
        dlgRecaudacionPrestamosIncasur.setVisible(true);
    }

    /**
     * @author KMONCADA
     * @since 05.10.2015
     * @param e
     */
    private void mnuReporteConcursoGigante(ActionEvent e) {
        DlgReporteGigante dlgConcuroGigante = new DlgReporteGigante(this, "", true);
        dlgConcuroGigante.setTitle("Ventas por Vendedor Meson");
        dlgConcuroGigante.setVisible(true);
    }

    /**
     * @author ERIOS
     * @since 18.07.2016
     * @param e
     */
    private void mnuAdministracion_PresupuestoVentas_actionPerformed(ActionEvent e) {
        DlgListaPresupuestoVentas dlgListaPresupuestoVentas = new DlgListaPresupuestoVentas(this, "", true);
        dlgListaPresupuestoVentas.setVisible(true);
    }

    //KMONCADA 19.08.2016 [CENTRO MEDICO]

    private void mnuCM_Admision_actionPerformed(ActionEvent e) {
        DlgListaEspera dlgListaEspera = new DlgListaEspera(this, "", true, TipoAtencionCM.ADMISION);
        dlgListaEspera.setVisible(true);
    }

    private void mnuCM_Triaje_actionPerformed(ActionEvent e) {
        /*DlgListaEsperaTriaje dlgListaEspera = new DlgListaEsperaTriaje(this,"",true, TipoAtencionCM.TRIAJE);
        dlgListaEspera.setVisible(true);*/
        DlgListaEspera dlgListaEspera = new DlgListaEspera(this, "", true, TipoAtencionCM.ADMISION);
        dlgListaEspera.setVisible(true);
    }

    private void mnuCM_Consulta_actionPerformed(ActionEvent e) {
        DlgLoginMedico dlgLogin = new DlgLoginMedico(this, "", true);
        dlgLogin.setVisible(true);
        /*DlgLogin dlgLogin = new DlgLogin(this, ConstantsPtoVenta.MENSAJE_LOGIN, true);
        dlgLogin.setRolUsuario(FarmaConstants.ROL_VENDEDOR);
        dlgLogin.setVisible(true);*/
        if (FarmaVariables.vAceptar) {
            FarmaVariables.vAceptar = false;
            String codMedico = dlgLogin.getCodMedico();
            DlgListaEspera dlgListaEspera = new DlgListaEspera(this, "", true, TipoAtencionCM.CONSULTA);
            dlgListaEspera.setPCodMedico(codMedico);
            dlgListaEspera.setVisible(true);
        }

    }

    /*** CCASTILLO 22/08/2016 ***/
    private void mnuCM_BuscarComprobantePago_actionPerformed(ActionEvent e) {
        DlgADMBuscarComprobantePago dlgBuscarComp = new DlgADMBuscarComprobantePago(this, "", true);
        dlgBuscarComp.setVisible(true);
    }

    private void mnuCM_BuscarPacienteDatos_actionPerformed(ActionEvent e) {
        DlgADMBuscarPaciente dlgBuscarPaciente = new DlgADMBuscarPaciente(this, "", true);
        dlgBuscarPaciente.setVisible(true);
    }

    private void mnuCM_ADMTrazabilidad_actionPerformed(ActionEvent e) {
        DlgADMTrazabilidad dlgTrazabilidad = new DlgADMTrazabilidad(this, "", true);
        dlgTrazabilidad.setVisible(true);
    }

    private void mnuCM_ADMActualizarPaciente_actionPerformed(ActionEvent e) {
        DlgADMListaPacientes dlgListaPac =
            new DlgADMListaPacientes(this, "", false, new VtaCompAtencionMedica(), new VtaPedidoAtencionMedica(), "A");
        dlgListaPac.setVisible(true);

    }


    private void opcionesMedicentro() {
        //mnuEconoFar_CM
        // mnuEconoFar_Salir
        // mnuEconoFar_Administracion
        this.remove(mnuPtoVenta);
        mnuPtoVenta.removeAll();
        mnuPtoVenta.add(mnuEconoFar_CM);
        mnuPtoVenta.add(mnuEconoFar_Administracion);
        mnuPtoVenta.add(mnuEconoFar_Salir);
        this.setJMenuBar(mnuPtoVenta);
    }

    private void quitaOpcionesAgregaTodos() {
        this.remove(mnuPtoVenta);
        mnuPtoVenta.removeAll();
        mnuPtoVenta.add(mnuEconoFar_Ventas);
        mnuPtoVenta.add(mnuEconoFar_Caja);
        mnuPtoVenta.add(mnuEconoFar_Inventario);
        mnuPtoVenta.add(mnuEconoFar_TomaInventario);
        mnuPtoVenta.add(mnuEconoFar_Administracion);
        mnuPtoVenta.add(mnuEconoFar_Reportes);
        mnuPtoVenta.add(mnuEconofar_CajaElectronica);
        mnuPtoVenta.add(mnuEconoFar_ControlIngreso);
        mnuPtoVenta.add(mnuEconoFar_CM);
        mnuPtoVenta.add(mnuEconoFar_Salir);
        mnuPtoVenta.add(mnuEconoFar_Ayuda);

        this.setJMenuBar(mnuPtoVenta);
    }

    private void quitaOpcionesAgregaSinCentroMedico() {
        this.remove(mnuPtoVenta);
        mnuPtoVenta.removeAll();
        mnuPtoVenta.add(mnuEconoFar_Ventas);
        mnuPtoVenta.add(mnuEconoFar_Caja);
        mnuPtoVenta.add(mnuEconoFar_Inventario);
        mnuPtoVenta.add(mnuEconoFar_TomaInventario);
        mnuPtoVenta.add(mnuEconoFar_Administracion);
        mnuPtoVenta.add(mnuEconoFar_Reportes);
        mnuPtoVenta.add(mnuEconofar_CajaElectronica);
        mnuPtoVenta.add(mnuEconoFar_ControlIngreso);
        mnuPtoVenta.add(mnuEconoFar_Salir);
        mnuPtoVenta.add(mnuEconoFar_Ayuda);
        this.setJMenuBar(mnuPtoVenta);
    }

    private boolean medicoAuxiliar() {
        boolean vContinuar = false;

//        if (FarmaVariables.dlgLogin.verificaRol("065")) {
//            this.remove(mnuPtoVenta);
//            mnuPtoVenta.removeAll();
//            mnuPtoVenta.add(mnuEconoFar_Administracion);
//            mnuEconoFar_CM.setEnabled(true);
//            mnuEconoFar_CM.removeAll();
//            mnuEconoFar_CM.add(mnuCM_Consulta);
//
//            mnuEconoFar_Administracion.removeAll();
//            mnuAdministracion_Usuarios.removeAll();
//
//            mnuAdministracion_Usuarios.add(mnuUsuarios_CambioUsuario);
//            mnuEconoFar_Administracion.add(mnuAdministracion_Usuarios);
//
//            mnuPtoVenta.add(mnuEconoFar_CM);
//            mnuPtoVenta.add(mnuEconoFar_Salir);
//            mnuPtoVenta.add(mnuEconoFar_Ayuda);
//            this.setJMenuBar(mnuPtoVenta);
//            vContinuar = false;
//        } else {
            mnuEconoFar_Administracion.removeAll();
            mnuAdministracion_Usuarios.removeAll();
            mnuEconoFar_CM.removeAll();

            mnuEconoFar_Administracion.add(mnuAdministracion_Usuarios);
            mnuEconoFar_Administracion.add(mnuAdministracion_Mantenimiento);
            mnuEconoFar_Administracion.add(mnuAdministracion_MovCaja);
            mnuEconoFar_Administracion.add(mnuAdministracion_RegViajero);
            mnuEconoFar_Administracion.add(mnuAdministracion_ControlHoras);
            mnuEconoFar_Administracion.add(mnuAdministracion_Otros);
            mnuEconoFar_Administracion.add(mnuAdministracion_FondoSencillo);
            mnuEconoFar_Administracion.add(mnuAdministracion_ProdCambiados);
            mnuEconoFar_Administracion.add(mnuAdministracion_PresupuestoVentas);

            mnuAdministracion_Usuarios.add(mnuUsuarios_CambioUsuario);
            mnuAdministracion_Usuarios.add(mnuUsuarios_CambioClave);


            mnuEconoFar_CM.add(mnuCM_ADMBuscarComprobantePago);
            mnuEconoFar_CM.add(mnuCM_Triaje); // borrar
            mnuEconoFar_CM.add(mnuCM_Consulta);
            mnuEconoFar_CM.add(mnuCM_ADMTrazabilidad);
            mnuEconoFar_CM.add(mnuCM_ADMActualizarPaciente);

            quitaOpcionesAgregaTodos();
            vContinuar = true;
//        }
        return vContinuar;
    }

    /**
     * @author AOVIEDO
     * @since 17.04.2017
     * @param e
     */
    private void mnuReporteConcursoGarantizados(ActionEvent e) {
        DlgReporteGarantizados dlgReporteGarantizados = new DlgReporteGarantizados(this, "", true);
        //dlgReporteGarantizados.setTitle("Ventas por Vendedor Meson");
        dlgReporteGarantizados.setVisible(true);
    }

    private void mnuInventario_SolicitudFraccion_ActionPerformed(ActionEvent e) {
        DlgSolicitudFraccion dlgFraccion = new DlgSolicitudFraccion(this, "", true);
        String menuActivo = UtilityFraccion.verificaActivacion_Menu();
        if (menuActivo.trim().equalsIgnoreCase("S")) {
            dlgFraccion.setVisible(true);
        } else {
            FarmaUtility.showMessage(this, "Esta funci�n a�n no est� activa ", null);
        }

    }

    private void mnuCotiza_ConDocumento(ActionEvent e) {
        DlgSolicitudCotizacionPrecio dlg =
            new DlgSolicitudCotizacionPrecio(this, "Cotizaci�n de Precios - Solicitud - Comprar", true,
                                             TIPO_COTIZA_COMPRAR);
        dlg.setVisible(true);
    }

    private void mnuCotiza_SinDocumento(ActionEvent e) {
        DlgSolicitudCotizacionPrecio dlg =
            new DlgSolicitudCotizacionPrecio(this, "Cotizaci�n de Precios - Solicitud - Cotizar", true,
                                             TIPO_COTIZA_COTIZAR);
        dlg.setVisible(true);
    }

    private void mnuCotiza_SinSolicitud(ActionEvent e) {

        // VarCotizacionPrecio.vTipo_Proceso = "03";
        VarCotizacionPrecio.vTipo_Origen = "04";
        VarCotizacionPrecio.vCod_Origen = FarmaVariables.vCodLocal;
        VarCotizacionPrecio.vFecha_Cotizacion = "";
        VariablesInventario.vFileImagen = null;

        VarCotizacionPrecio.vSerieDoc = "";
        VarCotizacionPrecio.vNroGuia = "";
        VarCotizacionPrecio.vRuta_Archivo = "";

        DlgCotizacionPrecioCabecera dlgCotizacionPrecioCabecera = new DlgCotizacionPrecioCabecera(this, "", true);
        dlgCotizacionPrecioCabecera.setTipoProceso(String.valueOf(TIPO_COTIZA_SIN_SOLICITUD));
        dlgCotizacionPrecioCabecera.setNombreTienda("");
        dlgCotizacionPrecioCabecera.setNroRUC("");
        dlgCotizacionPrecioCabecera.setNroSolicitud("");
        dlgCotizacionPrecioCabecera.setFechaSolicitud("");
        dlgCotizacionPrecioCabecera.setVisible(true);

        /*
            //INI LTAVARA 2017.09.12
            VarCotizacionPrecio.vCompetencia = "";
            VarCotizacionPrecio.vRUC_Comp ="";
            VarCotizacionPrecio.vCod_Solic ="";
            //COTIZACION SIN SOLICITUD
            VarCotizacionPrecio.vTipo_Proceso = "03";
            VarCotizacionPrecio.vTipo_Origen = "04";
            VarCotizacionPrecio.vCod_Origen = FarmaVariables.vCodLocal;

            VarCotizacionPrecio.vFecha_Cotizacion = "";
            VarCotizacionPrecio.vTipo_Doc = "";
            VarCotizacionPrecio.vSerieDoc = "";
            VarCotizacionPrecio.vNroGuia = "";
            VarCotizacionPrecio.vRuta_Archivo = "";

            try {
                VarCotizacionPrecio.vFecha_Cotizacion = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
                VarCotizacionPrecio.vFecha_Cotizacion = "";
            } catch(SQLException sql) {
                log.error("", sql);
            }
            if(JConfirmDialog.rptaConfirmDialog(this, "�Deseas Cotizar sin Solicitud?")){
                        tipoCotizacion=3;
                        abrirPantallaCabecera(this.tipoCotizacion, VarCotizacionPrecio.vCompetencia,
                                              VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic,"");

            }*/

    }

    /*private void mnuPrueba_ActionPerformed(ActionEvent e) {
        DlgMenuPruebas dlg = new DlgMenuPruebas(this, "", true);
        dlg.setVisible(true);
    }*/

    private void habilitaMenu_Prueba() {
        String ipLocal = FarmaVariables.vIpPc;
        if (ipLocal.indexOf("10.18.") != -1) {
            mnuInv_Pruebas.setVisible(true);
        }
    }

    private void mnuTest_ActionPerformed(ActionEvent e) {
        DlgAcceso dlg = new DlgAcceso(this, "", true);
        dlg.setVisible(true);
    }


    //INI AOVIEDO 31/07/2017

    private void mnuCaja_Financiero_actionPerformed(ActionEvent e) {
        //INI LMEZA 04/01/2019
        if (ConstantsPtoVenta.CODCIA_JORSA.equalsIgnoreCase(FarmaVariables.vCodCia)) {
            FarmaUtility.showMessage(this,
                                     "Las Recaudaciones de Dinners y Banco Pichicha" + "\n No se pueden Realizar en locales de JORSA.",
                                     null);
        } else {
            DlgRecaudacionFinanciero dlgRecaudacionFinanciero = new DlgRecaudacionFinanciero(this, "", true);
            dlgRecaudacionFinanciero.setVisible(true);
        }
        return;
        //FIN LMEZA 04/01/2019

        //DlgRecaudacionFinanciero dlgRecaudacionFinanciero = new DlgRecaudacionFinanciero(this, "", true);--LMEZA 04/01/2019
        //dlgRecaudacionFinanciero.setVisible(true);--LMEZA 04/01/2019
        /*
        if(nroVersionBFP.equalsIgnoreCase("V2.0")){
            DlgRecaudacionFinanciero dlgRecaudacionFinanciero = new DlgRecaudacionFinanciero(this, "", true);
            dlgRecaudacionFinanciero.setVisible(true);
        }else{
            //DlgRecauTarjetaFinanciero dlgRecauTarjetaFinanciero = new DlgRecauTarjetaFinanciero(this, "", true);
            //dlgRecauTarjetaFinanciero.setVisible(true);
            DlgRecaudacionBFP dlgRCD_BFP = new DlgRecaudacionBFP(this, "", true);
            dlgRCD_BFP.setVisible(true);
        }*/
        /*
        int nroOp=2;
        if(nroOp==1){
            DlgRecaudacionFinanciero dlgRecaudacionFinanciero = new DlgRecaudacionFinanciero(this, "", true);
            dlgRecaudacionFinanciero.setVisible(true);
        }else{
            DlgRecauTarjetaBFP dlgRCD_BFP = new DlgRecauTarjetaBFP(this, "", true);
            dlgRCD_BFP.setVisible(true);
        }
        */
    }
    //FIN AOVIEDO 31/07/2017

    private void jMenuItem2_actionPerformed(ActionEvent e) {
        DlgConvalidado dlgConvalidado = new DlgConvalidado(this, "", true);
        dlgConvalidado.setVisible(true);
    }

    private ArrayList recuperaRoles() throws SQLException {
        System.out.println("===========================");
        System.out.println("FarmaVariables.vCodGrupoCia: " + FarmaVariables.vCodGrupoCia);
        System.out.println("FarmaVariables.vCodLocal: " + FarmaVariables.vCodLocal);
        System.out.println("FarmaVariables.vNuSecUsu: " + FarmaVariables.vNuSecUsu);
        System.out.println("===========================");
        ArrayList vArrayList = new ArrayList();
        ArrayList vParameters = new ArrayList();
        vParameters.add(FarmaVariables.vCodGrupoCia);
        vParameters.add(FarmaVariables.vCodLocal);
        vParameters.add(FarmaVariables.vNuSecUsu);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vArrayList, "FARMA_SECURITY.LISTA_ROL(?,?,?)", vParameters);

        return vArrayList;
    }

    private void IRS_Tesoreria_ActionPerformed(ActionEvent e) {
        String rutaWeb = UtilityPtoVenta.get_URL_Tesoreria();
        if (rutaWeb != null) {
            try {
                //FarmaUtility.showMessage(this, "URL: "+rutaWeb, null);
                //String[] cmd={"cmd","/C","start","chrome",rutaWeb};
                //Runtime.getRuntime().exec(cmd);
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + rutaWeb);
            } catch (IOException f) {
                FarmaUtility.showMessage(this, "Error al abrir el explorador de internet", null);
                f.printStackTrace();
            }
        } else {
            FarmaUtility.showMessage(this, "Error al recuperar el URL de acceso", null);
        }
    }

    private void verificaJorsa() {
        boolean isMigraSAP = UtilityInventario.is_Migra_Sap();
        if (isMigraSAP) {
            mnuInventario_Devolucion.setEnabled(false);
        }
    }

    private void mnuGenerarData_actionPerformed(ActionEvent e) throws SQLException {
        validacionGenerarData();
    }

    void validacionGenerarData() throws SQLException {
        //int cant = Integer.parseInt(DBInventario.getPedidosPendientes()) ;
        int cant = 0;
        if (cant > 0) {
            FarmaUtility.showMessage(this, "Hay " + cant + " productos pendientes.", mnuGenerarData);
        } else if (cant == 0) {
            DlgGenerarData dlgGenerarData = new DlgGenerarData(this, "Configuraci�n PDA", true);
            dlgGenerarData.setVisible(true);

        }
    }
    
    //INI JMONZALVE 24042019
    private void mnuCobro_Responsabilidad_actionPerformed(ActionEvent e) {
        DlgReporteProductosFaltantes dlgReporteProductosFaltantes = new DlgReporteProductosFaltantes(this, "", true);
        dlgReporteProductosFaltantes.setVisible(true);
    }

    private void mnuVentas_UploadCobro_Responsabilidad_actionPerformed(ActionEvent e) {
        DlgUploadCobro_Responsabilidad dlgUploadCobro_Responsabilidad = new DlgUploadCobro_Responsabilidad(this, "", true);
        dlgUploadCobro_Responsabilidad.setVisible(true);
    }
    //FIN JMONZALVE 24042019
}
