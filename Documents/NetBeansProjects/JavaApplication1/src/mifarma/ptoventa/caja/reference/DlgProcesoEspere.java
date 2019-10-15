package mifarma.ptoventa.caja.reference;


import com.gs.mifarma.RespuestaTXBean;
import com.gs.mifarma.componentes.JMessageAlert;
import com.gs.mifarma.worker.JDialogProgress;

import java.awt.Frame;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.DlgNumeroTarjetaGenerado;
import mifarma.ptoventa.caja.DlgProcesarAnulacionRecarga;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgProcesoEspere extends JDialogProgress {

    private static final Logger log = LoggerFactory.getLogger(DlgProcesarAnulacionRecarga.class);
    private Frame myParentFrame;
    private static boolean indIntenta=false;
    private static String msjProceso="";
    private JTextField txtNroPedido;
    private int cNroIntentos=0;
    private int intento = 0;
    private int cSecEspere=0;
    private boolean okAnulacion=false;
    private boolean vProcesoRecarga;
    
    public DlgProcesoEspere(Frame frame, String string, boolean b,int nroIntentos, int secEspera) {
        super(frame, string, b);
        myParentFrame = frame;
        cNroIntentos=nroIntentos;
        cSecEspere=secEspera;
    }

    public DlgProcesoEspere() {
        super();
    }

    @Override
    public void ejecutaProceso() {
        try {
            log.info("Proceso de "+cNroIntentos+" intentos cada "+cSecEspere+" Segundos para anualar recarga");
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
                    if(okAnulacion==true){
                        otraVez=false;
                    }
                    //fin proceso
                    secDespues = secAntes + cSecEspere;
                    minDespues = minAntes;
                    if (secDespues >= 60) {
                        minDespues = minAntes + 1;
                        if (minDespues >= 60) {
                            minDespues = minDespues - 60;
                        }
                        secDespues = secDespues - 60;
                    }
                }
                if (intento == cNroIntentos) {
                    otraVez = false;
                }
                log.info("BUCLE: "+intento+" OTRA_VEZ: "+otraVez);
            } while (otraVez == true);
            
        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            if(intento==cNroIntentos){
                JMessageAlert.showMessage(myParentFrame, "Anulación Recarga Virtual",
                                          "ERROR: No se pudo realizar la anulación de recarga virtual",
                                          e.getMessage() + "@" + "Error en la Anulación Recarga Virtual",
                                          "Verifique los datos necesarios", true);
            }else{
                ejecutaProceso();
            }
            vProcesoRecarga = false;

        }

    }
/*    
    private boolean prosesandoAnulacion(){
        boolean otraVez=true;
        String codigo = "";
        String descripcion = "";
        boolean isAnulOk = true;
        UtilityRecargaVirtual util = new UtilityRecargaVirtual();
        RespuestaTXBean obj = new RespuestaTXBean();
        obj = util.respuestaSolicitudRecarga(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, VariablesCaja.vNumPedVta_Anul);
        
        if (obj != null) {
            if(obj.getDescripcion().equals("NO_CONEXION_RECARGA")){
                isAnulOk = false;
                indIntenta=false;
                //FarmaUtility.showMessage(this, "No hay conexión con matriz. \n No se puede anular el pedido", null);
                msjProceso="No hay conexión con matriz. \\n No se puede anular el pedido";
            }else if (obj.getCodigoRespuesta() == null) {
                indIntenta=true;
                codigo = (obj.getCodigoRespuesta() == null) ? "666" : obj.getCodigoRespuesta();
                descripcion =
                        (obj.getDescripcion() == null) ? "La recarga aún no tiene respuesta \n Si no puede anular intente en unos minutos más" :
                        obj.getDescripcion();
                //FarmaUtility.showMessage(this, descripcion, null);
                msjProceso=descripcion;
            } else {
                codigo = (obj.getCodigoRespuesta() == null) ? "666" : obj.getCodigoRespuesta();
                descripcion =
                        (obj.getDescripcion() == null) ? "La recarga no se realizo \n Se procedera a continuar con la anulación" :
                        obj.getDescripcion();
                
                if (codigo.trim().equals("00")) {
                    // KMONCADA 19.01.2015 SE VERIFICA QUE LA RECARGA NO TENGA UN EXTORNO PREVIO PARA REALIZARLO EN EL SIX
                    if (util.getIsRecargaAnulada(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, VariablesCaja.vNumPedVta_Anul, obj.getNumeroTrace())) {
                        //FarmaUtility.showMessage(this, "La recarga se encuentra extornada y/o anulada, se continuara con la anulación", null);
                        msjProceso="La recarga se encuentra extornada y/o anulada, se continuara con la anulación";
                        otraVez=false;
                    }else{
                        indIntenta=true;
                    }
                } else {
                    //FarmaUtility.showMessage(this,"La recarga no se realizo \n Se procedera a continuar con la anulación",null);
                    msjProceso="La recarga no se realizo \nSe procedera a continuar con la anulación";
                }
            }
        } else {
            indIntenta=true;
            codigo = "666";
            descripcion =
                    "La recarga aún no tiene respuesta \n Si no puede anular intente en unos minutos más";
            //FarmaUtility.showMessage(this, descripcion, null);
            msjProceso=descripcion;
        }
        return otraVez;
    }
/*
    public boolean isIndIntenta() {
        return indIntenta;
    }

    public void setIndIntenta(boolean indIntenta) {
        this.indIntenta = indIntenta;
    }

    public String getMsjProceso() {
        return msjProceso;
    }

    public void setMsjProceso(String msjProceso) {
        this.msjProceso = msjProceso;
    }
*/
    private void evaluaMsjVentaVirtualGenerado(String pTipoProdVirtual) {
        if (ConstantsVentas.TIPO_PROD_VIRTUAL_TARJETA.equalsIgnoreCase(pTipoProdVirtual))
            muestraTarjetaVirtualGenerado();
        else if (ConstantsVentas.TIPO_PROD_VIRTUAL_RECARGA.equalsIgnoreCase(pTipoProdVirtual)) {
            if (VariablesVirtual.vCodigoRespuesta.trim().equalsIgnoreCase(ConstantsCaja.COD_RESPUESTA_OK_TAR_VIRTUAL)) {
                log.info("La recarga automática se realizó satisfactoriamente.");
            } else {
                FarmaUtility.showMessage(this, "Verifique la anulación de la recarga virtual\n" +
                        "No se obtuvo respuesta del proveedor por problemas de conexión.", null);
            }
        }
    }
    
    private void muestraTarjetaVirtualGenerado() {
        DlgNumeroTarjetaGenerado dlgNumeroTarjetaGenerado = new DlgNumeroTarjetaGenerado(myParentFrame, "", true);
        dlgNumeroTarjetaGenerado.setVisible(true);
        FarmaVariables.vAceptar = false;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void ejecutaAnulacionRecargaVirtual() throws Exception {
        procesaAnulacionRecVirtual();
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
                okAnulacion=true;
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
                okAnulacion=true;
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

            if (!validaCodigoRespuestaTransaccion()) {
                throw new Exception(VariablesVirtual.respuestaTXBean.getCodigoRespuesta() + " - " +
                                    VariablesVirtual.respuestaTXBean.getDescripcion());
            }

        }
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
}
