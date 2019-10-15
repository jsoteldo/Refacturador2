package mifarma.ptoventa.convenioBTLMF.reference;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.UtilityCPE;

import mifarma.electronico.UtilityImpCompElectronico;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convenioBTLMF.dao.DAOConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.dao.DAORACConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.dao.FactoryConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.domain.RacConPedVta;
import mifarma.ptoventa.convenioBTLMF.domain.RacVtaCompPago;
import mifarma.ptoventa.convenioBTLMF.domain.RacVtaFormaPagoPedido;
import mifarma.ptoventa.convenioBTLMF.domain.RacVtaPedidoVtaCab;
import mifarma.ptoventa.convenioBTLMF.domain.RacVtaPedidoVtaDet;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.reference.BeanImpresion;
import mifarma.ptoventa.reference.TipoImplementacionDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FacadeConvenioBTLMF {

    private static final Logger log = LoggerFactory.getLogger(FacadeConvenioBTLMF.class);

    //private DAOConvenioBTLMF daoConvenioBTLMF;
    //private DAORACConvenioBTLMF daoRACConvenioBTLMF;

    public FacadeConvenioBTLMF() {
        super();
        //daoConvenioBTLMF = FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
    }

    public ArrayList<ArrayList<String>> listarBeneficRemoto(FarmaTableModel tableModelListaDatos) {
        ArrayList<ArrayList<String>> lstListado = null;
        DAORACConvenioBTLMF daoRACConvenioBTLMF;
        daoRACConvenioBTLMF = null;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.MYBATIS;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx().equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        try{
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            lstListado = daoRACConvenioBTLMF.listaBenefRemoto();
            daoRACConvenioBTLMF.commit();
            tableModelListaDatos.clearTable();
            tableModelListaDatos.data = lstListado;
        } catch (Exception e) {
            daoRACConvenioBTLMF.rollback();
            log.error("", e);
        }
        return lstListado;
    }
    
    public String grabarTemporalesRAC(String pNumPedVta) throws Exception{
        //return grabarTemporalesRAC(pNumPedVta, FarmaConstants.INDICADOR_N);
        return grabarPedidVta_RAC(pNumPedVta,FarmaConstants.INDICADOR_N);
    }

    public String grabarDatosPedidos_RAC(String pNumPedVta, String pIndicadorNC) {
        String resultado = "S";
        // conexion local para recuperal listas
        RacVtaPedidoVtaCab vtaPedidoVtaCabLocal = null;
        List<RacVtaPedidoVtaDet> lstPedidoVtaDetLocal = null;
        List<RacVtaCompPago> lstCompPagoLocal = null;
        List<RacVtaFormaPagoPedido> lstFormaPagoPedidoLocal = null;
        List<RacConPedVta> lstConPedVtaLocal = null;
        log.warn("Abre conexion local para inicializar lista temporales");
        DAOConvenioBTLMF daoConvenioBTLMFLocal =
            FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoConvenioBTLMFLocal.openConnection();
            //1.1 Lee cabecera
            vtaPedidoVtaCabLocal = daoConvenioBTLMFLocal.getPedidoCabLocal(pNumPedVta, pIndicadorNC);
            //1.2 Lee detalle
            lstPedidoVtaDetLocal = daoConvenioBTLMFLocal.getPedidoDetLocal(pNumPedVta, pIndicadorNC);
            //1.3 Lee comprobantes
            lstCompPagoLocal = daoConvenioBTLMFLocal.getCompPagoLocal(pNumPedVta, pIndicadorNC);
            //1.4 Lee formas de pago
            lstFormaPagoPedidoLocal = daoConvenioBTLMFLocal.getFormaPagoPedidoLocal(pNumPedVta, pIndicadorNC);
            //1.5 Lee info convenio
            lstConPedVtaLocal = daoConvenioBTLMFLocal.getConPedVtaLocal(pNumPedVta, pIndicadorNC);
            //1.6 Cierre conexion local
            daoConvenioBTLMFLocal.commit();
        } catch (Exception e) {
            daoConvenioBTLMFLocal.rollback();
            log.error("****************** ERROR AL OBTENER PEDIDO PARA ENVIAR AL RAC ********************");
            log.error("", e);
            resultado = e.getMessage();
            return resultado;
        }
        if (resultado.equalsIgnoreCase("S")) {
            log.warn("Se recuperaron los datos para el rac");
            //fin de conexion local
            DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
            TipoImplementacionDAO tipo = TipoImplementacionDAO.MYBATIS;
            //ERIOS 07.10.2015 Determina si esta activo el Gestor
            if (DlgProcesar.getIndGestorTx().equals(FarmaConstants.INDICADOR_S)) {
                tipo = TipoImplementacionDAO.GESTORTX;
            }
            //Temporales
            try {
                log.warn("Intentando conexion al rac");
                //2.0 Abre conexion RAC
                daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
                daoRACConvenioBTLMF.openConnection();
                log.warn("Conexion realizada: inserta temporales");
                //2.0 Borra anteriores
                daoRACConvenioBTLMF.deletePedidoCabRAC(pNumPedVta);
                //2.1 Graba cabecera
                daoRACConvenioBTLMF.savePedidoCabRAC(vtaPedidoVtaCabLocal);
                //2.2 Graba detalle
                daoRACConvenioBTLMF.savePedidoDetRAC(lstPedidoVtaDetLocal);
                //2.3 Graba comprobantes
                daoRACConvenioBTLMF.saveCompPagoRAC(lstCompPagoLocal);
                //2.4 Graba info convenio
                daoRACConvenioBTLMF.saveFormaPagoPedidoRAC(lstFormaPagoPedidoLocal);
                //2.5 Graba info convenio
                daoRACConvenioBTLMF.saveConPedVtaRAC(lstConPedVtaLocal);
                //-----> Graba pedido en rac
                try {
                    log.warn("Conexion realizada: GRABA DATOS EN RAC");
                    //Abre conexion RAC
                    resultado =
                            daoRACConvenioBTLMF.cobrarPedidoRAC(FarmaVariables.vCodLocal, FarmaVariables.vCodGrupoCia,
                                                                pNumPedVta, FarmaConstants.INDICADOR_N);
                    log.warn("Respuesta de rac (cobrarPedidoRAC): " + resultado);
                    //2.6 Cierra conexion RAC
                    log.warn("Se cierra la conexion al rac");
                    daoRACConvenioBTLMF.commit();
                    return resultado;
                } catch (Exception ex) {
                    log.error("****************** ex.printStackTrace() ********************");
                    ex.printStackTrace();
                    log.error("****************** ERROR DE COBRO EN RAC ********************");
                    log.error("", ex);
                    daoRACConvenioBTLMF.rollback();
                    String mensajeError = "";
                    if (ex instanceof RuntimeException) {
                        String[] lstError = ex.getMessage().trim().split(";");
                        for (int i = 0; i < lstError.length; i++) {
                            if (lstError[i].trim().startsWith("ORA-"))
                                mensajeError = mensajeError + "\n" +
                                        lstError[i].trim();
                        }
                    } else {
                        mensajeError = ex.getMessage();
                    }
                    resultado = mensajeError;
                    if (resultado == null)
                        resultado = "NO ESPECIFICADO";
                    return resultado;
                }
                //-----> FIN de graba pedido en rac
            } catch (Exception ex) {
                try {
                    daoRACConvenioBTLMF.rollback();
                } catch (NullPointerException x) {
                    ;
                } catch (Exception c) {
                    log.error("", c);
                }

                log.error("****************** ERROR AL GRABAR TEMPORALES EN RAC ********************");
                log.error("", ex);
                String mensajeError = "";
                if (ex instanceof RuntimeException) {
                    String[] lstError = ex.getMessage().trim().split(";");
                    for (int i = 0; i < lstError.length; i++) {
                        if (lstError[i].trim().startsWith("ORA-"))
                            mensajeError = mensajeError + "\n" +
                                    lstError[i].trim();
                    }
                } else {
                    mensajeError = ex.getMessage();
                }
                resultado = mensajeError;
            }
            //fin de temporales
        } else {
            resultado = "Se produjo un error al recuperar los datos para acceder a RAC";
        }
        return resultado;
    }
    //********************************************************************************************
    public String grabarPedidVta_RAC(String pNumPedVta, String pIndicadorNC) {
        String resultado = "S";
        // conexion local para recuperal listas
        RacVtaPedidoVtaCab vtaPedidoVtaCabLocal = null;
        List<RacVtaPedidoVtaDet> lstPedidoVtaDetLocal = null;
        List<RacVtaCompPago> lstCompPagoLocal = null;
        List<RacVtaFormaPagoPedido> lstFormaPagoPedidoLocal = null;
        List<RacConPedVta> lstConPedVtaLocal = null;
        log.warn("Abre conexion local para inicializar lista temporales");
        DAOConvenioBTLMF daoConvenioBTLMFLocal =
            FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoConvenioBTLMFLocal.openConnection();
            //1.1 Lee cabecera
            vtaPedidoVtaCabLocal = daoConvenioBTLMFLocal.getPedidoCabLocal(pNumPedVta, pIndicadorNC);
            //1.2 Lee detalle
            lstPedidoVtaDetLocal = daoConvenioBTLMFLocal.getPedidoDetLocal(pNumPedVta, pIndicadorNC);
            //1.3 Lee comprobantes
            lstCompPagoLocal = daoConvenioBTLMFLocal.getCompPagoLocal(pNumPedVta, pIndicadorNC);
            //1.4 Lee formas de pago
            lstFormaPagoPedidoLocal = daoConvenioBTLMFLocal.getFormaPagoPedidoLocal(pNumPedVta, pIndicadorNC);
            //1.5 Lee info convenio
            lstConPedVtaLocal = daoConvenioBTLMFLocal.getConPedVtaLocal(pNumPedVta, pIndicadorNC);
            log.warn("Se recuperaron los datos para el rac");
            //fin de conexion local
            DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
            TipoImplementacionDAO tipo = TipoImplementacionDAO.MYBATIS;
            //ERIOS 07.10.2015 Determina si esta activo el Gestor
            if (DlgProcesar.getIndGestorTx().equals(FarmaConstants.INDICADOR_S)) {
                tipo = TipoImplementacionDAO.GESTORTX;
            }
            //Temporales
            try {
                log.warn("Intentando conexion al rac");
                //2.0 Abre conexion RAC
                daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
                daoRACConvenioBTLMF.openConnection();
                log.warn("Conexion realizada: inserta temporales");
                //2.0 Borra anteriores
                daoRACConvenioBTLMF.deletePedidoCabRAC(pNumPedVta);
                //2.1 Graba cabecera
                daoRACConvenioBTLMF.savePedidoCabRAC(vtaPedidoVtaCabLocal);
                //2.2 Graba detalle
                daoRACConvenioBTLMF.savePedidoDetRAC(lstPedidoVtaDetLocal);
                //2.3 Graba comprobantes
                daoRACConvenioBTLMF.saveCompPagoRAC(lstCompPagoLocal);
                //2.4 Graba info convenio
                daoRACConvenioBTLMF.saveFormaPagoPedidoRAC(lstFormaPagoPedidoLocal);
                //2.5 Graba info convenio
                daoRACConvenioBTLMF.saveConPedVtaRAC(lstConPedVtaLocal);
                //-----> Graba pedido en rac
                try {
                    log.warn("Conexion realizada: GRABA DATOS EN RAC");
                    //Abre conexion RAC
                    resultado =
                            daoRACConvenioBTLMF.cobrarPedidoRAC(FarmaVariables.vCodLocal, FarmaVariables.vCodGrupoCia,
                                                                pNumPedVta, FarmaConstants.INDICADOR_N);
                    log.warn("Respuesta de rac (cobrarPedidoRAC): " + resultado);
                    
                    //2.6 Cierra conexion RAC
                    log.warn("Se cierra la conexion al rac");
                    daoRACConvenioBTLMF.commit();
                    
                    //1.6 Cierre conexion local
                    daoConvenioBTLMFLocal.commit();
                    
                    return resultado;
                } catch (Exception ex) {
                    log.error("****************** ex.printStackTrace() ********************");
                    ex.printStackTrace();
                    log.error("****************** ERROR DE COBRO EN RAC ********************");
                    log.error("", ex);
                    //2.6 Cierra conexion RAC
                    daoRACConvenioBTLMF.rollback();
                    //1.6 Cierre conexion local
                    daoConvenioBTLMFLocal.rollback();
                    String mensajeError = "";
                    if (ex instanceof RuntimeException) {
                        String[] lstError = ex.getMessage().trim().split(";");
                        for (int i = 0; i < lstError.length; i++) {
                            if (lstError[i].trim().startsWith("ORA-"))
                                mensajeError = mensajeError + "\n" +
                                        lstError[i].trim();
                        }
                    } else {
                        mensajeError = ex.getMessage();
                    }
                    resultado = mensajeError;
                    if (resultado == null)
                        resultado = "NO ESPECIFICADO";
                    return resultado;
                }
                //-----> FIN de graba pedido en rac
            } catch (Exception ex) {
                try {
                    //2.6 Cierra conexion RAC
                    daoRACConvenioBTLMF.rollback();
                    //1.6 Cierre conexion local
                    daoConvenioBTLMFLocal.rollback();
                } catch (NullPointerException x) {
                    ;
                } catch (Exception c) {
                    log.error("", c);
                }

                log.error("****************** ERROR AL GRABAR TEMPORALES EN RAC ********************");
                log.error("", ex);
                String mensajeError = "";
                if (ex instanceof RuntimeException) {
                    String[] lstError = ex.getMessage().trim().split(";");
                    for (int i = 0; i < lstError.length; i++) {
                        if (lstError[i].trim().startsWith("ORA-"))
                            mensajeError = mensajeError + "\n" +
                                    lstError[i].trim();
                    }
                } else {
                    mensajeError = ex.getMessage();
                }
                resultado = mensajeError;
            }
            //fin de temporales
        } catch (Exception e) {
            //1.6 Cierre conexion local
            daoConvenioBTLMFLocal.rollback();
            log.error("****************** ERROR AL OBTENER PEDIDO PARA ENVIAR AL RAC ********************");
            resultado = "Se produjo un error al recuperar los datos para acceder a RAC";
            log.error("", e);
            resultado = e.getMessage();
            return resultado;
        }
        return resultado;
    }
    //********************************************************************************************
    /**
     * Graba tablas temporales en RAC
     * @author ERIOS
     * @since 2.4.4
     */
    public String grabarTemporalesRAC(String pNumPedVta, String pIndicadorNC) throws Exception{
        String vRetorno = "S";
        RacVtaPedidoVtaCab vtaPedidoVtaCabLocal = null;
        List<RacVtaPedidoVtaDet> lstPedidoVtaDetLocal = null;
        List<RacVtaCompPago> lstCompPagoLocal = null;
        List<RacVtaFormaPagoPedido> lstFormaPagoPedidoLocal = null;
        List<RacConPedVta> lstConPedVtaLocal = null;
        
        DAOConvenioBTLMF daoConvenioBTLMFLocal = FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoConvenioBTLMFLocal.openConnection();
            //1.1 Lee cabecera
            vtaPedidoVtaCabLocal = daoConvenioBTLMFLocal.getPedidoCabLocal(pNumPedVta, pIndicadorNC);
            //1.2 Lee detalle
            lstPedidoVtaDetLocal = daoConvenioBTLMFLocal.getPedidoDetLocal(pNumPedVta, pIndicadorNC);
            //1.3 Lee comprobantes
            lstCompPagoLocal = daoConvenioBTLMFLocal.getCompPagoLocal(pNumPedVta, pIndicadorNC);
            //1.4 Lee formas de pago
            lstFormaPagoPedidoLocal = daoConvenioBTLMFLocal.getFormaPagoPedidoLocal(pNumPedVta, pIndicadorNC);
            //1.5 Lee info convenio
            lstConPedVtaLocal = daoConvenioBTLMFLocal.getConPedVtaLocal(pNumPedVta, pIndicadorNC);
            //1.6 Cierre conexion local
            daoConvenioBTLMFLocal.commit();
        } catch (Exception e) {
            daoConvenioBTLMFLocal.rollback();
            log.error("****************** ERROR AL OBTENER PEDIDO PARA ENVIAR AL RAC ********************");
            log.error("", e);
            vRetorno = e.getMessage();
            return vRetorno;
        }
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.MYBATIS;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx().equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        try {
            //2.0 Abre conexion RAC
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            //2.0 Borra anteriores
            daoRACConvenioBTLMF.deletePedidoCabRAC(pNumPedVta);
            //2.1 Graba cabecera
            daoRACConvenioBTLMF.savePedidoCabRAC(vtaPedidoVtaCabLocal);
            //2.2 Graba detalle
            daoRACConvenioBTLMF.savePedidoDetRAC(lstPedidoVtaDetLocal);
            //2.3 Graba comprobantes
            daoRACConvenioBTLMF.saveCompPagoRAC(lstCompPagoLocal);
            //2.4 Graba info convenio
            daoRACConvenioBTLMF.saveFormaPagoPedidoRAC(lstFormaPagoPedidoLocal);
            //2.5 Graba info convenio
            daoRACConvenioBTLMF.saveConPedVtaRAC(lstConPedVtaLocal);
            //2.6 Cierra conexion RAC
            daoRACConvenioBTLMF.commit();
        } catch (Exception ex) {
            // kmoncada 14.08.2014 controla el error de duplicidad de indice.
            /*if (e instanceof PersistenceException) {
                SQLException sqlExcep = (SQLException)e.getCause();
                if (sqlExcep.getErrorCode() == 1) {
                    vRetorno = "S";
                }
            }*/
            try {
                daoRACConvenioBTLMF.rollback();
            } catch (NullPointerException x) {
                ;
            } catch (Exception c) {
                log.error("", c);
            }
            
            log.error("****************** ERROR AL GRABAR TEMPORALES EN RAC ********************");
            log.error("", ex);
            String mensajeError = "";
            if(ex instanceof RuntimeException){
                String[] lstError = ex.getMessage().trim().split(";");
                for(int i=0;i<lstError.length;i++){
                    if(lstError[i].trim().startsWith("ORA-"))
                        mensajeError = mensajeError + "\n" + lstError[i].trim();
                }
            }else{
                mensajeError = ex.getMessage();
            }
            vRetorno = mensajeError;
            }
        //vRetorno = "S";
        return vRetorno;
    }
    
    public String grabarAnulacionPedidoRAC(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pLstPedidosNC){
        String vResultado = "S";
        
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.MYBATIS;
        if(DlgProcesar.getIndGestorTx(false).equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        
        try{
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            String indLocalElectronico = "N";
            if(UtilityCPE.isActivoFuncionalidad()) indLocalElectronico = "S";
            vResultado = daoRACConvenioBTLMF.grabarAnulacionPedidoRAC(pCodGrupoCia, pCodLocal, pNumPedVta, indLocalElectronico, pLstPedidosNC);
            daoRACConvenioBTLMF.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoRACConvenioBTLMF.rollback();
            String mensajeError = "";
            
            if(ex instanceof RuntimeException){
                String[] lstError = ex.getMessage().trim().split(";");
                for(int i=0;i<lstError.length;i++){
                    if(lstError[i].trim().startsWith("ORA-"))
                        mensajeError = mensajeError + "\n" + lstError[i].trim();
                }
                
                if(mensajeError.trim().length()==0){
                    IllegalStateException a = (IllegalStateException)ex.getCause();
                    String[] lineas = a.getMessage().split("\n");
                    for(int i=0;i<lineas.length;i++){
                        if(lineas[i].trim().length()!=0 && !(
                            lineas[i].trim().contains("oracle.jdbc.driver")||
                            lineas[i].trim().contains("org.springframework")||
                            lineas[i].trim().startsWith("at ")
                           )){
                               mensajeError = mensajeError + "\n" + lineas[i].trim();
                           }
                    }
                }
                
                if(mensajeError.trim().length()==0)
                    mensajeError = ex.getMessage();
            }else{
                mensajeError = ex.getMessage();
            }
            vResultado = mensajeError;
            if(vResultado==null)
                vResultado = "ERROR NO DETERMINADO EN LA ANULACION DE PEDIDOS EN EL RAC";
        }
        
        return vResultado;
    }
    
    public String grabarCobroPedidoRac(String pNumPedVta, 
                                       String pCodLocal, 
                                       String pCodGrupoCia,
                                       String indNotaCredito) throws Exception{
        String resultado = "N";
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.MYBATIS;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx(false).equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        
        try {
            //Abre conexion RAC
            log.warn("abre conexion en rac");
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            //Cobra Pedido en RAC
            log.warn("Graba pedido en rac");
            resultado = daoRACConvenioBTLMF.cobrarPedidoRAC(pCodLocal, pCodGrupoCia, pNumPedVta, indNotaCredito);
            log.warn("Respuesta de rac (cobrarPedidoRAC): "+resultado);
            daoRACConvenioBTLMF.commit();
       } catch (Exception ex) {
            log.error("****************** ERROR DE COBRO EN RAC ********************");
            log.error("", ex);
            daoRACConvenioBTLMF.rollback();
            String mensajeError = "";
            if(ex instanceof RuntimeException){
                String[] lstError = ex.getMessage().trim().split(";");
                for(int i=0;i<lstError.length;i++){
                    if(lstError[i].trim().startsWith("ORA-"))
                        mensajeError = mensajeError + "\n" + lstError[i].trim();
                }
            }else{
                mensajeError = ex.getMessage();
            }
            resultado = mensajeError;
            if(resultado==null)
                resultado = "NO ESPECIFICADO";
        }
        return resultado;
    }

    public String actualizaFechaProcesoRac_SinCommit(String pCodGrupoCia, 
                                                     String pCodLocal, 
                                                     String pNumPedVta)throws Exception {
        String resultado = "N";
        DAOConvenioBTLMF daoConvenioBTLMFLocal = FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoConvenioBTLMFLocal.openConnection();
            resultado = daoConvenioBTLMFLocal.actualizaFechaProcesoRac(pCodLocal, pCodGrupoCia, pNumPedVta);
        } catch (Exception ex) {
            daoConvenioBTLMFLocal.rollback();
            log.error("",ex);
            resultado = "N";
        }
        return resultado;
    }
    
    public String actualizaFechaProcesoRac(String pCodGrupoCia, 
                                           String pCodLocal, 
                                           String pNumPedVta)throws Exception {
        String resultado = "N";
        DAOConvenioBTLMF daoConvenioBTLMFLocal = FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoConvenioBTLMFLocal.openConnection();
            resultado = daoConvenioBTLMFLocal.actualizaFechaProcesoRac(pCodLocal, pCodGrupoCia, pNumPedVta);
            //1.6 Cierre conexion local
            daoConvenioBTLMFLocal.commit();
        } catch (Exception ex) {
            daoConvenioBTLMFLocal.rollback();
            log.error("",ex);
            resultado = "N";
        }
        return resultado;
    }

    /**
     * Se obtiene beneficiario remoto
     * @author ERIOS
     * @since 2.4.8
     * @param pCodCliConv
     * @return
     */
    public ArrayList<String> obtieneBeneficRemoto(String pCodCliConv) {
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        ArrayList<ArrayList<String>> lstListado = null;
        ArrayList<String> benef = null;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.MYBATIS;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx(false).equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        try {
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            lstListado = daoRACConvenioBTLMF.obtieneBenefRemoto(pCodCliConv);
            daoRACConvenioBTLMF.commit();
            if(lstListado.size()!=0){
                benef = lstListado.get(0);
            }else{
                benef = null;
            }
        } catch (Exception e) {
            daoRACConvenioBTLMF.rollback();
            log.error("", e);
            benef = null;
        }
        return benef;
    }
    
    /**
     * Imprime voucher de copia de guia
     * @author ERIOS
     * @since 21.07.2015
     * @param pNumPedVta
     * @return
     */
    public boolean impresionVoucherCopiaGuia(String pNumPedVta, String pSecCompPago){
        boolean resultado = true;
        DAOConvenioBTLMF daoConvenioBTLMFLocal = FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try{
            daoConvenioBTLMFLocal.openConnection();
            List<BeanImpresion> lista = daoConvenioBTLMFLocal.getVoucherCopiaGuia(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta, pSecCompPago);
            daoConvenioBTLMFLocal.commit();
            if(lista!=null){
                new UtilityImpCompElectronico().impresionTermica(lista, null);
            }else{
                resultado = false;
            }            
        }catch(Exception ex){
            daoConvenioBTLMFLocal.rollback();
            log.error("", ex);            
            resultado = false;
        }
        
        return resultado;
    }    
    
    public boolean impresionVoucher(String pNumPedVta){
        boolean resultado = true;
        DAOConvenioBTLMF daoConvenioBTLMFLocal = FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try{
            daoConvenioBTLMFLocal.openConnection();
            List<BeanImpresion> lista = daoConvenioBTLMFLocal.getVoucher(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            daoConvenioBTLMFLocal.commit();
            
            if(lista!=null){
                new UtilityImpCompElectronico().impresionTermica(lista, null);
            }else{
                resultado = false;
            }            
        }catch(Exception ex){
            daoConvenioBTLMFLocal.rollback();
            log.error("", ex);            
            resultado = false;
        }
        
        return resultado;
    } 
    
    public String consultarSaldCreditoBenif(JDialog pDialogo) {

        log.debug("Metodo: consultarSaldCreditoBenif");
        String resp = "N";
        //String montoCosumo = "";
        double montoConsumo = 0;
        double LineCredito = 0;
        double montoSaldo = 0;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.FRAMEWORK;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx(false).equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        try {
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            montoConsumo = daoRACConvenioBTLMF.obtieneComsumoBenif();
            daoRACConvenioBTLMF.commit();
            
            log.debug("montoCosumo>>>>>>>>>>>>>>>>>>>><" + montoConsumo);
            //montoConsumo =  FarmaUtility.getDecimalNumber(montoCosumo);
            LineCredito = FarmaUtility.getDecimalNumber(VariablesConvenioBTLMF.vLineaCredito);
            montoSaldo = LineCredito - montoConsumo;

            VariablesConvenioBTLMF.vMontoSaldo = FarmaUtility.formatNumber(montoSaldo);

            log.debug("LCré:"+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(LineCredito));
            log.debug("Sald:"+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(montoSaldo));
            log.debug("Cons:"+ConstantesUtil.simboloSoles + FarmaUtility.formatNumber(montoConsumo));

            VariablesConvenioBTLMF.vDatoLCredSaldConsumo =
                    "LCrédito:"+ConstantesUtil.simboloSoles+" " + FarmaUtility.formatNumber(LineCredito) + "    Sald:"+ConstantesUtil.simboloSoles+" " +
                    FarmaUtility.formatNumber(montoSaldo) + "    Cons:"+ConstantesUtil.simboloSoles+" " + FarmaUtility.formatNumber(montoConsumo);


            log.debug("VariablesConvenioBTLMF.vDatoLCredSaldConsumo:" + VariablesConvenioBTLMF.vDatoLCredSaldConsumo);


        } catch (Exception sqlException) {
            daoRACConvenioBTLMF.rollback();
            log.error("", sqlException);

            FarmaUtility.showMessage(pDialogo, sqlException.getMessage(), null);
        }
        return resp;
    }
    
    /**
     * @author ERIOS
     * @since 09.10.2015
     * @param pIndNotaCred
     * @return
     */
    public String anularPedidoRac(String pIndNotaCred) throws Exception{
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        String strRetorno;
        // KMONCADA 09.12.2014 ANULA CON NOTA DE CREDITO EN CASO DE ELECTRONICO ACTIVO
        //if (EposVariables.vFlagComprobanteE) {
        if (UtilityCPE.isActivoFuncionalidad()) {
            pIndNotaCred = "S";
        }
        /*
        TipoImplementacionDAO tipo = TipoImplementacionDAO.FRAMEWORK;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx().equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }*/
        //try {
            //daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            //daoRACConvenioBTLMF.openConnection();
            strRetorno = daoRACConvenioBTLMF.anularPedidoRac(pIndNotaCred);
            //daoRACConvenioBTLMF.commit();
        /*} catch (Exception sqlException) {
            //daoRACConvenioBTLMF.rollback();
            log.error("", sqlException);
            strRetorno = "N";
        }*/
        return strRetorno;
    }

    public boolean isExisteComprobanteEnRAC(String pCodGrupoCia, String pCodLocal, String pTipoComprobante, String pNumComprobante,
                                         String pIndComprobanteElectronico) {
        int resultado;
        boolean bRetorno;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.FRAMEWORK;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx().equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        try {
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            resultado = daoRACConvenioBTLMF.isExisteComprobanteEnRAC(pCodGrupoCia,pCodLocal,pTipoComprobante,pNumComprobante,pIndComprobanteElectronico);
            daoRACConvenioBTLMF.commit();
            
            if (resultado == 0){
                bRetorno = false;
            }else{
                bRetorno = true;
            }
        } catch (Exception sqlException) {
            daoRACConvenioBTLMF.rollback();
            log.error("", sqlException);
            bRetorno = false;
        }
        return bRetorno;
    }

    public boolean validacionPedidoRAC(String pCodLocal, String pNumPedVta, String pCodConvenio,
                                              String pCodCliente, String pTipoDoc, double pMonto,
                                              String pVtaFin,
                                       String pNumReceta, String pFchReceta, String pCodLocalReceta, String pCodMedico, String pCadCodDiagnostico
                                       ) throws Exception {
        String resultado;
        boolean bRetorno;
        TipoImplementacionDAO tipo = TipoImplementacionDAO.FRAMEWORK;
        //ERIOS 07.10.2015 Determina si esta activo el Gestor
        if(DlgProcesar.getIndGestorTx().equals(FarmaConstants.INDICADOR_S)){
            tipo = TipoImplementacionDAO.GESTORTX;
        }
        DAORACConvenioBTLMF daoRACConvenioBTLMF = null;
        try {
            daoRACConvenioBTLMF = FactoryConvenioBTLMF.getDAORACConvenioBTLMF(tipo);
            daoRACConvenioBTLMF.openConnection();
            resultado = daoRACConvenioBTLMF.validacionPedidoRAC(pCodLocal, pNumPedVta, pCodConvenio,
                                              pCodCliente, pTipoDoc, pMonto, pVtaFin,
                                              pNumReceta, pFchReceta, pCodLocalReceta, pCodMedico, pCadCodDiagnostico);
            daoRACConvenioBTLMF.commit();
            
            if ("S".equalsIgnoreCase(resultado)){
                bRetorno = true;
            }else{
                bRetorno = false;
            }
        } catch (Exception e) {
            daoRACConvenioBTLMF.rollback();
            //log.error("", sqlException);
            //bRetorno = false;
            throw e;
        }
        return bRetorno;
    }
    
    public String agregarCabeceraNotaCredito(String numPedVta, String tipCambio, String motivoAnulacion)throws Exception{
        String nroPedidos = "";
        DAOConvenioBTLMF daoConvenioBTLMF = FactoryConvenioBTLMF.getDAOConvenioBTLMF(TipoImplementacionDAO.MYBATIS);
        try{
            daoConvenioBTLMF.openConnection();
            nroPedidos = daoConvenioBTLMF.agregarCabeceraNotaCredito(FarmaVariables.vCodGrupoCia,
                                                                     FarmaVariables.vCodLocal,
                                                                     numPedVta,
                                                                     new Double(tipCambio),
                                                                     FarmaVariables.vIdUsu,
                                                                     new Integer(VariablesCaja.vNumCaja.trim()),
                                                                     motivoAnulacion);
            daoConvenioBTLMF.commit();
        }catch(Exception ex){
            daoConvenioBTLMF.rollback();
            log.error("", ex);
            nroPedidos = null;
            throw new Exception(ex.getMessage());
        }
        return nroPedidos;
    }
}
