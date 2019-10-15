package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JMessageAlert;
import com.gs.mifarma.worker.JDialogProgress;

import java.awt.Frame;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JTextField;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.caja.reference.VariablesVirtual;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2006 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : DlgProcesarAnulacionRecarga.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * ASOSA       08/07/2014    Creaci�n<br>
 * <br>
 * @author Alfredo Sosa Dord�n<br>
 * @version 1.0<br>
 *
 */
public class DlgProcesarAnulacionRecarga extends JDialogProgress {

    private static final Logger log = LoggerFactory.getLogger(DlgProcesarAnulacionRecarga.class);
    private FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();

    private Frame myParentFrame;
    private JTable tblFormasPago;
    private JTextField txtNroPedido;
    private boolean vProcesoRecarga;
    private int cNroIntentos=0;
    private int intento = 1;
    private int cSecEspere=0;    
    
    public DlgProcesarAnulacionRecarga(Frame frame, String string, boolean b,int nroIntentos,int secEspera) {
        super(frame, string, b);
        myParentFrame = frame;
        cNroIntentos=nroIntentos;
        cSecEspere=secEspera;
    }
    public DlgProcesarAnulacionRecarga() {
        super();
    }

    @Override
    public void ejecutaProceso() {
        try {
            log.info("==========================================> INTENTO: "+intento+" de "+cNroIntentos);
            int minAntes = Calendar.getInstance().getTime().getMinutes();
            int secAntes = Calendar.getInstance().getTime().getSeconds();
            int minDespues = minAntes;
            int secDespues = secAntes + cSecEspere;
            if (secDespues >= 60) {
                minDespues = minAntes + 1;
                if (minDespues >= 60) {
                    minDespues = minDespues - 60;
                }
                secDespues = secDespues - 60;
            }
            boolean otraVez = true;
            do {
                minAntes = Calendar.getInstance().getTime().getMinutes();
                secAntes = Calendar.getInstance().getTime().getSeconds();
                if (minAntes == minDespues && secAntes == secDespues) {
                    intento++;
                    //Proceso
                    ejecutaAnulacionRecargaVirtual();
                    evaluaMsjVentaVirtualGenerado(VariablesCaja.vTipoProdVirtual);
                    otraVez=false;
                    this.setVProcesoRecarga(true);
                    FarmaUtility.showMessage(this, "El proceso de anulacion se realizo satisfactoriamente", null);
                }                
            } while (otraVez == true);            
            
        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            if(intento==cNroIntentos){
                this.setVProcesoRecarga(false);
                JMessageAlert.showMessage(myParentFrame, "Anulaci�n Recarga Virtual",
                                          "ERROR: No se pudo realizar la anulaci�n de recarga virtual",
                                          e.getMessage() + "@" + "Error en la Anulaci�n Recarga Virtual",
                                          "Verifique los datos necesarios", true);
            }else{
                ejecutaProceso();
            }
        }

    }

    private void ejecutaAnulacionRecargaVirtual() throws Exception {
        procesaAnulacionRecVirtual();
    }


    private void evaluaMsjVentaVirtualGenerado(String pTipoProdVirtual) {
        if (ConstantsVentas.TIPO_PROD_VIRTUAL_TARJETA.equalsIgnoreCase(pTipoProdVirtual))
            muestraTarjetaVirtualGenerado();
        else if (ConstantsVentas.TIPO_PROD_VIRTUAL_RECARGA.equalsIgnoreCase(pTipoProdVirtual)) {
            if (VariablesVirtual.vCodigoRespuesta.trim().equalsIgnoreCase(ConstantsCaja.COD_RESPUESTA_OK_TAR_VIRTUAL)) {
                log.info("La recarga autom�tica se realiz� satisfactoriamente.");                
            } else {
                FarmaUtility.showMessage(this, "Verifique la anulaci�n de la recarga virtual\n" +
                        "No se obtuvo respuesta del proveedor por problemas de conexi�n.", null);
            }
        }
    }

    private void procesaAnulacionRecVirtual() throws Exception {

        obtieneInfoPedidoVirtual();
        if ((VariablesVirtual.vArrayList_InfoProdVirtual != null) &&
            !(VariablesVirtual.vArrayList_InfoProdVirtual.size() == 1)) {
            throw new Exception("Error al validar info del pedido virtual");
        }
        colocaInfoPedidoVirtual();
        if (ConstantsVentas.TIPO_PROD_VIRTUAL_RECARGA.equalsIgnoreCase(VariablesCaja.vTipoProdVirtual)) {

            try {
                UtilityCaja.procAnulacionRecargaVirtual(this, txtNroPedido);                
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
            /*
                * Se grabara la respuesta obtenida por el proveedor al realizar la
                * recarga virtual
                */
            try {
                DBCaja.grabaRespuestaRecargaVirtual(VariablesVirtual.respuestaTXBean.getCodigoRespuesta(),
                                                    VariablesCaja.vNumPedVta);                
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            if (!validaCodigoRespuestaTransaccion()) {
                throw new Exception(VariablesVirtual.respuestaTXBean.getCodigoRespuesta() + " - " +
                                    VariablesVirtual.respuestaTXBean.getDescripcion());
            }

        }
    }

    private void muestraTarjetaVirtualGenerado() {
        DlgNumeroTarjetaGenerado dlgNumeroTarjetaGenerado = new DlgNumeroTarjetaGenerado(myParentFrame, "", true);
        dlgNumeroTarjetaGenerado.setVisible(true);
        FarmaVariables.vAceptar = false;
    }

    private void obtieneInfoPedidoVirtual() throws Exception {
        try {
            DBCaja.obtieneInfoPedidoVirtual(VariablesVirtual.vArrayList_InfoProdVirtual,
                                            VariablesCaja.vNumPedVta_Anul);
            log.debug("vArrayList_InfoProdVirtual : " + VariablesVirtual.vArrayList_InfoProdVirtual);
        } catch (SQLException sql) {
            log.error("", sql);
            throw new Exception("Error al obtener informacion del pedido virtual - \n" +
                    sql);
        }
    }

    private void colocaInfoPedidoVirtual() {
        try {
            ArrayList temp = VariablesVirtual.vArrayList_InfoProdVirtual;
            VariablesCaja.vCodProd = FarmaUtility.getValueFieldArrayList(temp, 0, 0);
            VariablesCaja.vTipoProdVirtual = FarmaUtility.getValueFieldArrayList(temp, 0, 1);
            VariablesCaja.vPrecioProdVirtual = FarmaUtility.getValueFieldArrayList(temp, 0, 2);
            VariablesCaja.vNumeroCelular = FarmaUtility.getValueFieldArrayList(temp, 0, 3);
            VariablesCaja.vCodigoProv = FarmaUtility.getValueFieldArrayList(temp, 0, 4);
            VariablesCaja.vTipoTarjeta = FarmaUtility.getValueFieldArrayList(temp, 0, 7);
            //ERIOS 31.08.2015 Variables necesarias para la anulacion.
            VariablesCaja.vTipoRcd = FarmaUtility.getValueFieldArrayList(temp, 0, 10);
            VariablesCaja.vCodTipoProducto = FarmaUtility.getValueFieldArrayList(temp, 0, 11);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private boolean validaCodigoRespuestaTransaccion() {
        boolean result = false;
        log.debug("VariablesVirtual.vCodigoRespuesta - " + VariablesVirtual.vCodigoRespuesta);
        if (VariablesVirtual.vCodigoRespuesta != null && ( //ASOSA - 12/09/2014
            VariablesVirtual.vCodigoRespuesta.trim().equalsIgnoreCase(ConstantsCaja.COD_RESPUESTA_OK_TAR_VIRTUAL) ||
            VariablesVirtual.vCodigoRespuesta.trim().equalsIgnoreCase(ConstantsCaja.COD_RESPUESTA_COBRA_REVISE_EST_TAR_VIRTUAL))) {
            result = true;
        }
        log.debug("validaCodigoRespuestaTransaccion result - " + result);
        return result;
    }

    public void setTblFormasPago(JTable tblFormasPago) {
        this.tblFormasPago = tblFormasPago;
    }

    public JTable getTblFormasPago() {
        return tblFormasPago;
    }

    public void setTxtNroPedido(JTextField txtNroPedido) {
        this.txtNroPedido = txtNroPedido;
    }

    public JTextField getTxtNroPedido() {
        return txtNroPedido;
    }

    public void setVProcesoRecarga(boolean vProcesoRecarga) {
        this.vProcesoRecarga = vProcesoRecarga;
    }

    public boolean isVProcesoRecarga() {
        return vProcesoRecarga;
    }

    public void actualizaFechaRespuesta() {
        ArrayList<Object> rptSix = null;
        Long codTrssc = null;
        try {
            codTrssc = Long.parseLong(VariablesVirtual.vNumTrace);
            rptSix = facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_RECARGA_SIX, "", codTrssc);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
