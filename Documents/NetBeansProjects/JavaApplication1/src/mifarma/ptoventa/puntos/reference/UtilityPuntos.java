package mifarma.ptoventa.puntos.reference;


import com.gs.mifarma.componentes.JConfirmDialog;

import farmapuntos.FarmaPuntos;

import farmapuntos.bean.BeanAfiliado;
import farmapuntos.bean.BeanClientOrbis;
import farmapuntos.bean.BeanOperacion;
import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.awt.Frame;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.electronico.UtilityImpCompElectronico;

import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.fidelizacion.reference.AuxiliarFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.ConstantsFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.DBFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.UtilityFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.programaXmas1.facade.ProgramaXmas1Facade;
import mifarma.ptoventa.puntos.DlgValidaDocumento;
import mifarma.ptoventa.puntos.DlgVerificaDocRedencionBonifica;
import mifarma.ptoventa.puntos.modelo.BeanAfiliadoLocal;
import mifarma.ptoventa.ventas.reference.BeanDetalleVenta;
import mifarma.ptoventa.ventas.reference.UtilityCalculoPrecio;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityPuntos {

    private static final Logger log = LoggerFactory.getLogger(UtilityPuntos.class);

    private static final String ESTADO_PENDIENTE = "P";
    private static final String SIMBOLO_CERO = "0";
    private static final String ALINEACION_IZQUIERDA = "I";     
    
    public UtilityPuntos() {
    }
    
    public static String obtenerMensajeErrorLealtad(String estadoOperacion, String pMsj) {
        String mensaje = "";
        VariablesCaja.codAnul_Orviis=estadoOperacion;
        try {
            mensaje = DBPuntos.getMensajeErrorLealtad(estadoOperacion);
            if (mensaje.trim().length() == 0)
                mensaje = pMsj;

            if (mensaje.trim().equalsIgnoreCase("VACIO"))
                mensaje = pMsj;

        } catch (Exception e) {
            // TODO: Add catch code
            mensaje = pMsj;
            log.error("", e);
        }
        if (estadoOperacion.equalsIgnoreCase("13")) {
            return mensaje;
        } else {
            return estadoOperacion + "-" + mensaje;
        }
    }
    
    public static String enmascararTarjeta(String numTarj) {
        String res = "";
        int tam = numTarj.length();
        if (tam > 8) {
            String prim = numTarj.substring(0, 4);
            String ult = numTarj.substring(tam - 4, tam);
            String centro = FarmaUtility.caracterIzquierda("", tam - 8, "*");
            res = prim + centro + ult;
        }else{
            return numTarj;
        }
        return res;
    }

    public static void consultaSaldo(String vNumTarjeta,JTextField objeto,JDialog pDialog, Frame myParentFrame) {
        /*UtilityImprPuntos.imprimeSaldo(
                                      pDialog,objeto,
                                      "DIEGO",
                                      "123*123*123",
                                      20.15
                                      );*/
        if(isTarjetaValida(vNumTarjeta)){
            String pDNI = getDNI_USU();
            
            if(pDNI.trim().length()>0&&!pDNI.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N)){
                BeanTarjeta beanSaldo = VariablesPuntos.frmPuntos.consultarSaldo(vNumTarjeta, pDNI);
                
                if(beanSaldo.getEstadoOperacion().equalsIgnoreCase(WSClientConstans.NO_CONEXION_ORBIS))
                {
                    FarmaUtility.showMessage(pDialog, "No hay conexión con el sistema de puntos.", objeto);     
                    return ;
                }
                else
                if(beanSaldo.getEstadoOperacion().equalsIgnoreCase(WSClientConstans.EXITO)||
                   // KMONCADA 17.07.2015 SE MOSTRARA SALDO CUANDO LA TARJETA ESTA BLOQUEADA
                   //beanSaldo.getEstadoOperacion().equalsIgnoreCase(WSClientConstans.EstadoTarjeta.BLOQUEADA)||
                    beanSaldo.getEstadoOperacion().equalsIgnoreCase(WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR)
                  ){
                
                if(beanSaldo==null){
                    FarmaUtility.showMessage(pDialog, "Hay un problema de comunicación con el sistema de puntos.", objeto);     
                    return ;
                }
                
                if (beanSaldo.getPuntosTotalAcumulados()==null) {
                FarmaUtility.showMessage(pDialog, "No se pudo obtener el saldo actual de puntos.", objeto);     
                return ;
                }
                
                
                if(VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                imprimeSaldo(pDialog ,objeto,beanSaldo,vNumTarjeta,enmascararTarjeta(vNumTarjeta));
                //INVOCAR AL METODO DE KENNY PARA CREAR EL CLIENTE SI NO EXISTE EN PBL_CLIENTE
                               
                //ESTE IRA A ORBIS OBTENDRA LOS DATOS DEL CLIENTE Y LO CREARA EN BD DE LOCAL
                /* *********************************************************************************/
                validaCliente(beanSaldo.getNumeroTarjeta(),beanSaldo.getDni());
                /* *********************************************************************************/
                FarmaUtility.showMessage(pDialog, "Por favor de recojer su constancia de saldos de la impresora.", objeto);     
                }
                else{
                    FarmaUtility.showMessage(pDialog, "No se pudo obtener el saldo de la tarjeta. ", objeto);
                    }
                }
                else{
                    FarmaUtility.showMessage(pDialog, //beanSaldo.getMensaje()
                    UtilityPuntos.obtenerMensajeErrorLealtad(beanSaldo.getEstadoOperacion(),beanSaldo.getMensaje())
                                             , objeto);
                    return ;
                }
            }
            else             
            FarmaUtility.showMessage(pDialog, "No se tiene el DNI , no se puede hacer la consulta de saldos.. ", objeto);   
        }
        else
          FarmaUtility.showMessage(pDialog, "Tarjeta inválida , verique e ingrese nuevamente la tarjeta. ", objeto);
    }
    
    public static void getRecuperaPuntos(String vNumTarjeta, JTextField objeto, JDialog pDialog, String pNumPedVta,
                                         String pFechaPedido,String vNetoPedido) throws Exception {
        List<String> listaProgramas = null;
        ArrayList<ArrayList<String>> listProgramasPendientes = null;
        ProgramaXmas1Facade programaXmas1Facade = null;
        String cadenaListaProgramas = "";
        String programaPendiente = "";
        try {
            if (isTarjetaValida(vNumTarjeta)) {
                //DNI DEL VENDEDOR o PERSONA LOGIN
                String pDNI = getDNI_USU();
                if (pDNI.trim().length() > 0 && !pDNI.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
                    if(!DBPuntos.getIndPedActuaPuntos(pNumPedVta))
                    {
                        //calculaPuntosRecuperar(pNumPedVta);
                        programaXmas1Facade = new ProgramaXmas1Facade();
                        listaProgramas = new ArrayList<String>();
                        
                        listProgramasPendientes =
                                programaXmas1Facade.obtenerProgramasTemporalesX1(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni(),
                                                                                 ESTADO_PENDIENTE);
                        if(listProgramasPendientes != null && listProgramasPendientes.size() > 0){
                            for (Integer bucle = 0; bucle < listProgramasPendientes.size(); bucle++) {
                                programaPendiente = listProgramasPendientes.get(bucle).get(0);
                                if(programaPendiente != null && programaPendiente.length() > 0){
                                    VariablesPuntos.frmPuntos.getBeanTarjeta().getListaInscritos().add(FarmaUtility.completeWithSymbol(programaPendiente, 
                                                                                                                                       13,
                                                                                                                                       SIMBOLO_CERO, 
                                                                                                                                       ALINEACION_IZQUIERDA));
                                }
                            }
                        }
                        
                        listaProgramas = VariablesPuntos.frmPuntos.getBeanTarjeta().getListaInscritos();
                        
                        for (Integer i = 0; i < listaProgramas.size(); i++) {
                            cadenaListaProgramas = cadenaListaProgramas + listaProgramas.get(i).substring(8) + "@";
                        }
                        
                        programaXmas1Facade.recuperacionProgramaX1(cadenaListaProgramas, pNumPedVta);
                        
                    calculaPuntos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, pNumPedVta, false);
                        // si la suma de puntos
                        // es CERO
                        // ELL METODO D KENNY HIZO OK
                        // Este no SE ENVIA A ORBIS
                        // es NULO
                        // algo fallo en tu metodo y se mostrara mensaje
                        // hubo error en el calculo
                        boolean pPuntosCero = DBPuntos.isPuntosReloadCero(pNumPedVta);
                        boolean pPuntosNulo = DBPuntos.isPuntosReloadNulo(pNumPedVta);
                        boolean pPuntosMayorCero = DBPuntos.isPuntosReloadMayorCero(pNumPedVta);
                        if (!pPuntosCero && !pPuntosNulo && pPuntosMayorCero) {
                            ArrayList listaProducto = listaProdPuntos(pNumPedVta);
                            double pCtdPuntosAcum =
                                FarmaUtility.getDecimalNumber(DBPuntos.getCtdPuntosAcumVta(pNumPedVta));
                            double vNetPedido = FarmaUtility.getDecimalNumber(vNetoPedido);
                            //KMONCADA 09.07.2015 SE OBTIENE CANTIDAD DE PUNTOS EXTRAS
                            double ctdPtosExtras = FarmaUtility.getDecimalNumber(DBPuntos.getCtdPuntosExtras(pNumPedVta));
                            BeanTarjeta beanSaldo =

                                VariablesPuntos.frmPuntos.recuperarPuntos(vNumTarjeta, listaProducto, ctdPtosExtras,  pNumPedVta,
                                                                          //FarmaUtility.getStringToDate(pFechaPedido,"DD/mm/yyyy")
                                                                          pFechaPedido.trim()
                                                                          , vNetPedido, pDNI);
                            
                            log.info("***************"+beanSaldo.getMensaje());
                            log.info("***************"+UtilityPuntos.obtenerMensajeErrorLealtad(beanSaldo.getEstadoOperacion(),beanSaldo.getMensaje()));
                            // SOLO PARA PROBAR
                            //beanSaldo.setEstadoOperacion(WSClientConstans.NO_CONEXION_ORBIS);
                            if (beanSaldo == null) {
                                FarmaUtility.showMessage(pDialog, "No se pudo recuperar puntos por:\n" +
                                        "El estado de la respuesta no es válido.", objeto);
                            } else if (beanSaldo.getEstadoOperacion().equalsIgnoreCase(WSClientConstans.EXITO) || 
                                      beanSaldo.getEstadoOperacion().equalsIgnoreCase(WSClientConstans.NUMERO_YA_APLICADA) ){//[Desarrollo5] 23.10.2015 se le agrego esta validacion para cuando se haya perdido la conexion con orbis por time out
                                /* *********************************************************************************/
                                validaCliente(vNumTarjeta, beanSaldo.getDni());
                                /* *********************************************************************************/
                                //[Desarrollo5] 22.10.2015
                                UtilityPuntos.actualizarEstadoEnvioAfiliacion(vNumTarjeta,
                                                                              beanSaldo.getDni(),
                                                                              ConstantsPuntos.TRSX_ORBIS_ENVIADA);
                                
                                // ES ONLINE x eso va el TRUE
                                DBPuntos.pRecuperaPuntos(vNumTarjeta, pNumPedVta, true,beanSaldo.getIdTransaccion(),beanSaldo.getNumeroAutororizacion());
                                FarmaUtility.aceptarTransaccion();
                                
                                BeanTarjeta beanConsultaSaldo =null;
                                try {
                                    beanConsultaSaldo = VariablesPuntos.frmPuntos.consultarSaldo(vNumTarjeta, pDNI);
                                } catch (Exception e) {
                                    // TODO: Add catch code
                                    log.info("Fallo VariablesPuntos.frmPuntos.consultarSaldo");
                                    log.error("",e);
                                    log.error("",e);
                                }
                                
                                // INI - AGREGADO DUBILLUZ 07.05.2015
                                // NO SE EVALUO NUNCA SI HUBO O NO CONEXION 
                                // SE INDICO ANTERIORMENTE QUE ESTE METODO DE CONSULTAR SALDO SIEMBRE DEVUELVE UN BEAN NO NULO
                                // Y DATOS VACIOS y/o CERO:
                                if (beanSaldo == null) {
                                    imprimePuntosAcumulados(pCtdPuntosAcum, vNumTarjeta, "",
                                                            "", true, pDialog, objeto,0,
                                                            pNumPedVta,
                                                            cadenaListaProgramas);
                                    FarmaUtility.showMessage(pDialog, "Exito,se hizo la recuperación de puntos. ", objeto);                                
                                } else {
                                    
                                    try {
                                        imprimePuntosAcumulados(pCtdPuntosAcum, vNumTarjeta,
                                                                beanConsultaSaldo.getDni(),
                                                                beanConsultaSaldo.getNombreCompleto(), true, pDialog,
                                                                objeto, beanConsultaSaldo.getPuntosTotalAcumulados(),
                                                                pNumPedVta,
                                                                cadenaListaProgramas);
                                        FarmaUtility.showMessage(pDialog, "Exito,se hizo la recuperación de puntos. ",
                                                                 objeto);
                                    } catch (Exception e) {
                                        // TODO: Add catch code
                                        log.error("",e);
                                        imprimePuntosAcumulados(pCtdPuntosAcum, vNumTarjeta, "",
                                                                "", false, pDialog, objeto,0,
                                                                pNumPedVta,
                                                                cadenaListaProgramas);
                                        FarmaUtility.showMessage(pDialog, "Exito,se hizo la recuperación de puntos. ", objeto);
                                        
                                    }                                
                                }
                                // FIN - AGREGADO DUBILLUZ 07.05.2015                                
                                /*
                                imprimePuntosAcumulados(pCtdPuntosAcum, vNumTarjeta, beanConsultaSaldo.getDni(),
                                                        beanConsultaSaldo.getNombreCompleto(), true, pDialog, objeto,beanConsultaSaldo.getPuntosTotalAcumulados(),
                                                        pNumPedVta);
                                FarmaUtility.showMessage(pDialog, "Exito,se hizo la recuperación de puntos. ", objeto);
                                */
                                    
                            } else {
                                if (beanSaldo.getEstadoOperacion().equalsIgnoreCase(WSClientConstans.NO_CONEXION_ORBIS) && 
                                    DBPuntos.compruebaRegistroTarjetaLocal(vNumTarjeta) == 1) {//[Desarrollo5] 21.10.2015 comprueba si existe registro local del cliente
                                    //El metodo de acumula puntos se registra el ind en cabecera.
                                    //pero como no va estar el fecha proceso se va hacer con el JOB de modo OFF LINE
                                    // ES OFF LINE x eso va el false
                                    DBPuntos.pRecuperaPuntos(vNumTarjeta, pNumPedVta, false,beanSaldo.getIdTransaccion(),beanSaldo.getNumeroAutororizacion());
                                    FarmaUtility.aceptarTransaccion();
                                    
                                    
                                    BeanTarjeta beanConsultaSaldo =null;
                                    try {
                                        beanConsultaSaldo = VariablesPuntos.frmPuntos.consultarSaldo(vNumTarjeta, pDNI);
                                        imprimePuntosAcumulados(pCtdPuntosAcum, vNumTarjeta, beanConsultaSaldo.getDni(),
                                                                beanConsultaSaldo.getNombreCompleto(), false, pDialog, objeto,beanConsultaSaldo.getPuntosTotalAcumulados(),
                                                                pNumPedVta,
                                                                cadenaListaProgramas);                                        
                                        FarmaUtility.showMessage(pDialog, "Exito,se hizo la recuperación de puntos. ",
                                                                 objeto);
                                    } catch (Exception e) {
                                        // TODO: Add catch code
                                        log.error("",e);
                                        imprimePuntosAcumulados(pCtdPuntosAcum, vNumTarjeta, "",
                                                                "", false, pDialog, objeto,1,
                                                                pNumPedVta,
                                                                cadenaListaProgramas);                                                                                
                                        FarmaUtility.showMessage(pDialog,
                                                                 "La recuperación se verá reflejada en el transcurso del día.\n" +
                                                "Porque no hay conexión con servicio de puntos", objeto);                                                                            
                                    }
                                    
                                    
                                    
                                } else {
                                    DBPuntos.pRevierteAcumulaPuntos(pNumPedVta);
                                    FarmaUtility.aceptarTransaccion();
                                    FarmaUtility.showMessage(pDialog, "No se pudo recuperar puntos por :\n" +
                                            UtilityPuntos.obtenerMensajeErrorLealtad(beanSaldo.getEstadoOperacion(),beanSaldo.getMensaje()), objeto);
                                }
                            }
                        } else {
                            if (pPuntosCero) {
                                // Se muestra mensaje de que ya recupero CERO PUNTOS y se marca que no intente lo mismo
                                FarmaUtility.showMessage(pDialog, "Se registraron los puntos correctamente.\n" +
                                        "Puntos acumulados para el pedido es cero", objeto);
                            }
                            if (pPuntosNulo) {
                                // Se muestra mensaje de que ya recupero CERO PUNTOS y se marca que no intente lo mismo
                                /*   FarmaUtility.showMessage(pDialog,
                                                         "No se pudo calcular cuantos puntos acumula la venta.\n" +
                                        "Por favor de volver a intentarlo", objeto);*/
                                
                                FarmaUtility.showMessage(pDialog, "No se pudo recuperar los puntos del pedido.\n" +
                                        //"Por favor vuelva a intentarlo." +
                                        "Gracias.", objeto);
                            }
                            
                            actualizarEstTrxOrbis(pNumPedVta, ConstantsPuntos.TRSX_ORBIS_NO_APLICA);
                            FarmaUtility.aceptarTransaccion();

                        }
                    }
                    else{
                    FarmaUtility.showMessage(pDialog, 
                                             "No se puede recuperar, porque el pedido ya acumulo Puntos", objeto);
                    }

                } else
                    FarmaUtility.showMessage(pDialog, "No se tiene el DNI del usuario del sistema ,\n" +
                            "No se puede hacer la recuperacion de puntos ", objeto);
                
            } else
                FarmaUtility.showMessage(pDialog, "Tarjeta invalida , verique e ingrese nuevamente la tarjeta. ",
                                         objeto);
        } catch (SQLException e) {
            log.error("",e);
            FarmaUtility.liberarTransaccion();
            if (e.getErrorCode() == 20200) {
                throw new Exception(e.getMessage().substring(11, e.getMessage().indexOf("ORA-06512")));
                /*FarmaUtility.showMessage(pDialog,
                                         e.getMessage().substring(11, e.getMessage().indexOf("ORA-06512")),
                                         objeto);*/
            }            
            else
            throw new Exception(e.getMessage());
            /*FarmaUtility.showMessage(pDialog, "Error al momento de Recuperar los Puntos ,\n" +
                    "" + e.getMessage(), objeto);*/
        }
        catch(Exception a){
            log.error("",a);
            /*
            FarmaUtility.showMessage(pDialog, "Error al momento de Recuperar los Puntos ,\n" +
                    "" + a.getMessage(), objeto);*/
            throw new Exception(a.getMessage());
        }
    }
    
    private static void actualizarEstTrxOrbis(String pNumPedVta, String pEstadoTrxOrbis){
        try{
            DBPuntos.actualizaEstadoTrx(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, pNumPedVta, pEstadoTrxOrbis);
        }catch(Exception ex){
            log.error(" ", ex);
            
        }
    }

    public static String getCodAutorizacion() {
        String pKey = "";
        try {
            pKey = DBPuntos.getCodAutorizacion();
        } catch (Exception e) {
            log.error("",e);
            pKey = "";
            log.info("ERROR getCodAutorizacion FARMA PUNTOS");
            log.error("",e);
        }
        return pKey;
    }
    
    public static String getWsOrbis() {
        String pKey = "";
        try {
            pKey = DBPuntos.getWsOrbis();
        } catch (Exception e) {
            log.error("",e);
            pKey = "";
            log.info("ERROR getCodAutorizacion FARMA PUNTOS");
            log.error("",e);
        }
        return pKey;
    }    
    

    /**
     * INSTANCIA LA CLASE FARMA PUNTOS
     */
    public static boolean cargaFarmaPuntos() {
        if (isActivoFuncionalidad()) {
            try {
                // SE INICIA LA CLASE FARMA PUNTOS
                log.info(" Antes de iniciar VariablesPtoVenta.frmPuntos");
                log.info("Variables");
                String pCodEquiValente = DBPuntos.getCodigoEquivalenteNum(FarmaVariables.vCodLocal);
                String pIdIPPOS = getIdEpos();
                String pkey = getCodAutorizacion();
                String pWsOrbis = getWsOrbis();
                String pTimeOut = pTimeOutOrbis();

                log.info(" " + pCodEquiValente);
                log.info(" " + pIdIPPOS);
                log.info(" " + pkey);
                log.info(" " + pWsOrbis);
                log.info(" " + pTimeOut);
                //if(pCodAutorizacion.)

                if (FarmaVariables.vCodLocal.trim().length() > 0 && pIdIPPOS.trim().length() > 0 &&
                    pkey.trim().length() > 0 && pWsOrbis.trim().length() > 0) {
                    try {
                        BeanClientOrbis bean = new BeanClientOrbis();
                        bean.setPCodEquivalente(pCodEquiValente);
                        bean.setIdCaja(pIdIPPOS);
                        bean.setDireccionWSPuntos(pWsOrbis);
                        bean.setCodigoAutorizacion(pkey);
                        
                        String[] vTime = DBPuntos.getTimeOutsOrbis().trim().split("@");
                        
                        bean.setTimeout_cnx_orbis(Integer.parseInt(vTime[0].trim()));
                        bean.setTimeout_cnx_ws(Integer.parseInt(vTime[1].trim()));
                        bean.setTimeout_exec_ws(Integer.parseInt(vTime[2].trim()));
                        
                        
                        bean.setPCodGrupoCia(FarmaVariables.vCodGrupoCia);
                        bean.setPCodCia(FarmaVariables.vCodCia);
                        bean.setPIP(FarmaVariables.vIpPc);
                        bean.setPUsuBD(FarmaVariables.vUsuarioBD);
                        bean.setPClaveBD(FarmaVariables.vClaveBD);
                        bean.setPIpBD(FarmaVariables.vIPBD);
                        bean.setPSidBD(FarmaVariables.vSID);
                        bean.setPPuertoBD(FarmaVariables.vPUERTO);
                        VariablesPuntos.frmPuntos = new FarmaPuntos(bean);
                        
                    } catch (Exception e) {
                        // TODO: Add catch code
                        log.error("", e);
                        log.error("", e);
                        //FarmaUtility.showMessage(myfrm,"No se pudo iniciar Servicio Monedero", null);
                    }
                    log.info("Finalizo VariablesPtoVenta.frmPuntos");
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                // TODO: Add catch code
                log.error("", e);
                return true;
                //FarmaUtility.showMessage(myfrm,"No se pudo iniciar Servicio Monedero", null);
            }
        }
        else{
           log.info(" NO ESTA ACTIVO Funcionalidad Farma Puntos");
            return true;
        }
        
    }
    
    public static String getIdEpos() {
        String[] cadena = FarmaUtility.getHostAddress().split("\\.");
        return cadena[3].toString();
    }

    public static boolean isActivoFuncionalidad() {
        String pInd = "";
        try {
            pInd = DBPuntos.getIndActPuntos();
        } catch (Exception e) {
            log.error("",e);
            pInd = "N";
            log.info("ERROR isActivoFuncionalidad FARMA PUNTOS");
            log.error("",e);
        }
        
        if(pInd.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }
    
    /**
     * @since 2015.02 VALIDA TARJETAS EN ORBIS
     * @param pTarjeta
     * @return
     */
    public static boolean isTarjetaValida(String pTarjeta) {
        String pInd = "";
        try {
            pInd = DBPuntos.isTarjetaValida(pTarjeta);
        } catch (Exception e) {
            log.error("",e);
            pInd = "N";
            log.info("ERROR isTarjetaValida FARMA PUNTOS");
            log.error("",e);
        }
        if(pInd.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S))
            return true;
        else
            return false;
    }    
    
    //getDniUsuario
    public static String getDNI_USU() {
        String pDni = "";
        try {
            pDni = DBPuntos.getDniUsuario();
        } catch (Exception e) {
            log.error("",e);
            pDni = "N";
            log.info("ERROR getDNI_USU FARMA PUNTOS");
            log.error("",e);
        }
        
        return pDni;
    }
    
    /**
     *
     * @param rpsDatoOrbis
     */
    public static void imprimeSaldo(JDialog pDialog, Object pFoco, BeanTarjeta rpsDatoOrbis, String pTarjeta,
                                    String pTarjEnmascarada) {
        
            try {
                UtilityImprPuntos.imprimeSaldo(pDialog, pFoco, rpsDatoOrbis.getNombreCompleto(), pTarjEnmascarada,
                                               rpsDatoOrbis.getPuntosTotalAcumulados());

            } catch (Exception sqle) {
                log.error("",sqle);
                log.error("",sqle);
            }
        
    }
    
    /**
     * INIT DE ORBIS - REALIZA LA CONSULTA DEL NRO DE TARJETA 
     * @author KMONCADA
     * @since 2015.03.06
     * @param pJDialog
     * @param campoTexto
     * @param isLectorTarjeta
     * @param pTarjetaAnterior
     * @return
     */
    public static int consultarTarjetaOrbis(Frame myParentFrame, JDialog pJDialog, JTextField campoTexto, boolean isLectorTarjeta, boolean pTarjetaAnterior){
        
        boolean consulto = true;
        String campoTextoField = campoTexto.getText();
        String nroDocumentoAux = "";
        Long tiempoInicial;
        Long tiempoFinal;

        try{
            if(isActivoFuncionalidad()){
                log.info("FUNCIONALIDAD DE LEALTAD ACTIVA");
                String nroTarjetaPuntos, nroDni, nroTarjetaFidelizado;
                nroTarjetaPuntos = "";
                nroDni = "";
                nroTarjetaFidelizado = "";
                if(pTarjetaAnterior){
                    nroTarjetaPuntos = campoTextoField;
                    nroTarjetaFidelizado = nroTarjetaPuntos;
                    nroDni = DBFidelizacion.getDniClienteFidelizado(nroTarjetaPuntos);
                    if(!isTarjetaValida(nroTarjetaPuntos)){
                        nroTarjetaPuntos = nroDni;
                    }
                    campoTextoField = nroTarjetaPuntos;
                }
                // KMONCADA 28.04.2015 VALIDA SI REQUIERE TARJETA DE PUNTOS PARA REALIZAR VENTA
                if(DBPuntos.requerieTarjetaAsociada()){
                    log.info("PROGRAMA PUNTOS [CONSULTA TARJETA] : SOLICITA TARJETA OBLIGATORIO");
                    //BENEFICIOS DE PUNTOS SOLO CUANDO TIENE ALGUNA TARJETA
                    if(!isTarjetaValida(campoTextoField)){
                        log.info("PROGRAMA PUNTOS [CONSULTA TARJETA] : NO ES UNA TARJETA DE PUNTOS --> "+campoTextoField);
                        nroDocumentoAux = campoTextoField;
                        try{
                            int cantidadTarjetaAdicional = cantidadTieneTarjetasAdicionales(myParentFrame, pJDialog, nroDocumentoAux, false, false);
                            log.info("PROGRAMA PUNTOS [CONSULTA TARJETA] : CANTIDAD DE TARJETAS ASOCIADAS --> "+campoTextoField+" -- "+cantidadTarjetaAdicional);
                            if(cantidadTarjetaAdicional == 0){
                                String mensajeAviso = DBPuntos.getMensajeSinTarjeta();
                                if(JConfirmDialog.rptaConfirmDialog(pJDialog, mensajeAviso,"Aceptar","Rechazar")){
                                    // REALIZA AFILIACION DE TARJETA POR OBLIGACION
                                    DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(myParentFrame, "", true, ConstantsPuntos.VALIDA_TARJETA_ASOCIADA);
                                    dlgVerifica.setNroDocumento(campoTextoField);
                                    dlgVerifica.setIsRequiereDni(false);
                                    dlgVerifica.setIsRequiereTarjeta(true);
                                    dlgVerifica.setVisible(true);
                                    if(FarmaVariables.vAceptar){
                                        campoTextoField = dlgVerifica.getTextTarjeta().trim();
                                        isLectorTarjeta = true;
                                    }else{
                                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                        //return false;
                                        return ConstantsPuntos.RECHAZA_AFILIACION_TARJ;
                                    }
                                }else{
                                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                    //return false;
                                    return ConstantsPuntos.RECHAZA_AFILIACION_TARJ;
                                }
                            } else{
                                if(cantidadTarjetaAdicional == -1){
                                    //KMONCADA 08.07.2015 VALIDA SI CLIENTE SE ENCUENTRA REGISTRADO EN EL LOCAL
                                    if(!isLectorTarjeta){
                                        isLectorTarjeta = DBPuntos.isValidaClienteAfiliadoLocal(campoTextoField);
                                    }
                                    String mensaje = "Para poder acceder a los beneficios por la Tarjeta Monedero, debe escanearla\n" + 
                                                     "¿Cliente cuenta con Tarjeta Monedero o desea afiliarse?";
                                    
                                    if(!isLectorTarjeta){
                                        log.info("CONSULTA TARJETA ORBIS [OFFLINE] :\nNRO DOCUMENTO --> "+nroDni+"\n"+
                                                 "SOLICITARA NRO TARJETA DE PUNTOS");
                                        
                                        if(JConfirmDialog.rptaConfirmDialog(pJDialog, mensaje)){
                                            DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(myParentFrame, "", true, ConstantsPuntos.SOLICITA_TARJ_OFFLINE);
                                            dlgVerifica.setNroDocumento(campoTextoField);
                                            dlgVerifica.setIsRequiereDni(false);
                                            dlgVerifica.setIsRequiereTarjeta(true);
                                            dlgVerifica.setVisible(true);
                                            boolean resultado = FarmaVariables.vAceptar;
                                            if(resultado){
                                                campoTextoField = dlgVerifica.getTextTarjeta().trim();
                                                isLectorTarjeta = true;
                                            }else{
                                                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                                //return false;
                                                return ConstantsPuntos.RECHAZA_AFILIACION_TARJ;
                                            }
                                        }else{
                                            VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                            //return false;
                                            return ConstantsPuntos.RECHAZA_AFILIACION_TARJ;
                                        }
                                    }
                                    //return false;
                                    //return ConstantsPuntos.TARJETA_OFFLINE;
                                }else{
                                    if(cantidadTarjetaAdicional < -1){
                                        return ConstantsPuntos.RECHAZA_AFILIACION_TARJ;
                                    }
                                }
                            } 
                        }catch(Exception ex){
                            
                        }
                    }
                }
                
                tiempoInicial=   System.currentTimeMillis();
                log.info("PROGRAMA PUNTOS [CONSULTA TARJETA] : NRO DE TARJETA --> "+campoTextoField);
                BeanTarjeta tarjetaPuntos =  VariablesPuntos.frmPuntos.validarTarjetaAsociada(campoTextoField.trim(),  getDNI_USU());
                tiempoFinal=   System.currentTimeMillis();
                if (tarjetaPuntos !=null){
                try{
                    log.info("[INIT] [INPUT] ----"+VariablesPuntos.frmPuntos.getBitacora().getVInput_in());
                    log.info("[INIT] [OUTPUT]---"+VariablesPuntos.frmPuntos.getBitacora().getVOutput());
                    }catch(Exception ex){
                        log.error("", ex);
                    }
                }
                log.info("PROGRAMA DE PUNTOS [INIT] : TIEMPO DE RESPUESTA DE INIT CON PROVEEDOR "+(tiempoFinal-tiempoInicial)+"milisegundos") ;
                
                tarjetaPuntos.setDeslizaTarjeta(isLectorTarjeta); // indicador de desliza tarjeta
                tarjetaPuntos.setEscaneaDNI(false);
                /* INICIO DE DATOS DE PRUEBA */
                /* tarjetaPuntos.setNumeroTarjeta(campoTexto.getText());
                List list = DBPuntos.consultaTarje(campoTexto.getText());
                Map mapa = (Map)list.get(0);
                tarjetaPuntos.setDni((String)mapa.get("NRO_DOCUMENTO"));
                tarjetaPuntos.setEstadoTarjeta((String)mapa.get("ESTADO"));
                tarjetaPuntos.setEstadoOperacion(WSClientConstans.EXITO);
                VariablesPuntos.frmPuntos.getTarjetaBean().setNumeroTarjeta(campoTexto.getText()); */
                /* FIN DE DATOS DE PRUEBA */
                /*FarmaUtility.showMessage(pJDialog, "NRO TARJETA --> " +tarjetaPuntos.getNumeroTarjeta()+"\n"+
                                                    "NRO DNI --> "+tarjetaPuntos.getDni()+"\n"+
                                                    "DESLIZO TARJETA --> "+tarjetaPuntos.getDeslizaTarjeta()+"\n"+
                                                    "ESTADO DE TARJETA --> "+tarjetaPuntos.getEstadoTarjeta()+"\n"+
                                                    "ESTADO DE TRANSACCION--> "+tarjetaPuntos.getEstadoOperacion()+"\n"+
                                                    "NOMBRE --> "+tarjetaPuntos.getNombreCompleto());*/
                log.info("PROGRAMA PUNTOS [CONSULTA TARJETA] :\nNRO TARJETA --> " +tarjetaPuntos.getNumeroTarjeta()+"\n"+
                                                    "NRO DNI --> "+tarjetaPuntos.getDni()+"\n"+
                                                    "DESLIZO TARJETA --> "+tarjetaPuntos.getDeslizaTarjeta()+"\n"+
                                                    "ESTADO DE TARJETA --> "+tarjetaPuntos.getEstadoTarjeta()+"\n"+
                                                    "ESTADO DE TRANSACCION--> "+tarjetaPuntos.getEstadoOperacion()+"\n"+
                                                    "NOMBRE --> "+tarjetaPuntos.getNombreCompleto()+"\n"+
                                                    "ID TRANSACCION--> "+tarjetaPuntos.getIdTransaccion()+"\n"+
                                                    "INIT ID TRANSACCION--> "+tarjetaPuntos.getIdTransaccionInit());
                
                
                // VALIDACIONES DE ESTADO DE TARJETA
                if(WSClientConstans.EstadoTarjeta.BLOQUEADA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                    VariablesFidelizacion.limpiaVariables();
                    campoTexto.setText("");
                    
                    String msjError=obtenerMensajeErrorLealtad(tarjetaPuntos.getEstadoTarjeta(), "TARJETA BLOQUEADA/BLOQUEADA REDIMIR/INVALIDA");
                    String[] array = msjError.split("@");
                    String msj = array[0].trim();
                    for(int i=1;i<array.length;i++){
                        msj=msj+"\n"+array[i].trim();
                    }
                    //FarmaUtility.showMessage(pJDialog, "Programa Monedero:\n TARJETA BLOQUEADA.", campoTexto);
                    FarmaUtility.showMessage(pJDialog, msj, campoTexto);
                    //return false;
                    return ConstantsPuntos.TARJETA_BLOQUEADA;
                }
                if(WSClientConstans.EstadoTarjeta.INVALIDA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                    VariablesFidelizacion.limpiaVariables();
                    FarmaUtility.showMessage(pJDialog, "Programa Monedero:\n TARJETA INVALIDA.", campoTexto);
                    //return false;
                    return ConstantsPuntos.TARJETA_INVALIDA;
                }
                if(WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                    log.info("PROGRAMA MONEDERO TARJETA INACTIVA SE REGISTRARA CLIENTE NUEVO");
                    FarmaUtility.showMessage(pJDialog, "Programa Monedero:\n TARJETA NUEVA.", campoTexto);
                    // KMONCADA 26.06.2015 ESTE PROCEDIMIENTO POR DEFENICION YA NO SE DEBERIA DE DAR.
                    if(pTarjetaAnterior){
                        log.info("PROGRAMA DE PUNTOS [] : ESTE PROCEDIMIENTO POR DEFENICION YA NO SE DEBERIA DE DAR.");
                        // KMONCADA 05.05.2015 REGISTRO AUTOMATICO DE CLIENTES 
                        // construir bean de afiliado en base al nro de dni;
                        registroAfiliadoAutomatico(nroDni, campoTextoField, nroTarjetaFidelizado);
                        // KMONCADA VOUCHER DE AFILIADO
                        log.info("PROGRAMA PUNTOS [CLIENTES ANTIGUOS] " + nroDni);
                        //UtilityFidelizacion.imprimirVoucherFid(nroDni, nroTarjetaFidelizado);
                        //FarmaUtility.showMessage(pJDialog, "Recoger voucher de verificación de datos", null);
                        //return true;
                        return ConstantsPuntos.TARJETA_ACTIVA;
                    }
                    //return false;
                    return ConstantsPuntos.TARJETA_NUEVA;
                }
                if(WSClientConstans.EXITO.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())
                || WSClientConstans.EstadoTarjeta.ACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                || WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                    
                    // ESTO ES PARA DEFINIR QUE ES SU PRIMERA COMPRA PORQUE NO TIENE ACUMULADO NADA
                    // dubilluz 2017.02.16
                   try {
                        if (tarjetaPuntos.getPuntosTotalAcumulados() == 0) {
                            VariablesFidelizacion.vIsPrimeraCompraFidelizadoNuevo = true;
                        }
                    } catch (Exception e) {
                        // TODO: Add catch code
                        log.info(e.getMessage());
                    }
                    //validaCliente(tarjetaPuntos.getNumeroTarjeta(), tarjetaPuntos.getDni());
                    /*FarmaUtility.showMessage(pJDialog, "BIENVENIDO AL PROGRAMA PUNTOS, \n trasaccion: "+tarjetaPuntos.getIdTransaccion()+"\n"+
                                                       "tarjeta : "+tarjetaPuntos.getNumeroTarjeta()+"\n"+
                                                       "dni: "+tarjetaPuntos.getDni(), campoTexto);*/
                    log.info("BIENVENIDO AL PROGRAMA MONEDERO");
                    //return true;
                    return ConstantsPuntos.TARJETA_ACTIVA;
                }
                
                if(WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                    log.info("CONSULTA DE CLIENTE OFFLINE --> VENTA OFFLINE "+campoTextoField);
                  //  FarmaUtility.showMessage(pJDialog, "PROGRAMA DE PUNTOS:\nVenta en modo OFFLINE", campoTexto);
                    String mensaje = "Para poder acceder a los beneficios por la Tarjeta Monedero, debe escanearla\n" + 
                                     "¿Cliente cuenta con Tarjeta Monedero?";
                    
                    if(!isLectorTarjeta){
                        log.info("CONSULTA TARJETA ORBIS [OFFLINE] :\nNRO DOCUMENTO --> "+nroDni+"\n"+
                                 "SOLICITARA NRO TARJETA DE PUNTOS");
                        
                        if(JConfirmDialog.rptaConfirmDialog(pJDialog, mensaje)){
                            DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(myParentFrame, "", true, ConstantsPuntos.SOLICITA_TARJ_OFFLINE);
                            dlgVerifica.setNroDocumento(campoTextoField);
                            dlgVerifica.setIsRequiereDni(false);
                            dlgVerifica.setIsRequiereTarjeta(true);
                            dlgVerifica.setVisible(true);
                            boolean resultado = FarmaVariables.vAceptar;
                            if(resultado){
                                VariablesPuntos.frmPuntos.getBeanTarjeta().setDni(nroDocumentoAux);
                                VariablesPuntos.frmPuntos.getBeanTarjeta().setNumeroTarjeta(dlgVerifica.getTextTarjeta().trim());
                            }else{
                                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                            }
                        }else{
                            VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                        }
                    }else{
                        
                        // seteando el nro de tarjeta
                        if(VariablesPuntos.frmPuntos.getBeanTarjeta().getNumeroTarjeta()== null || 
                            (VariablesPuntos.frmPuntos.getBeanTarjeta().getNumeroTarjeta()!= null &&VariablesPuntos.frmPuntos.getBeanTarjeta().getNumeroTarjeta().trim().length()==0)
                          ){
                            
                            if(!campoTextoField.equalsIgnoreCase(nroDocumentoAux)){
                                VariablesPuntos.frmPuntos.getBeanTarjeta().setNumeroTarjeta(campoTextoField);
                            }
                              
                          }
                        // seteando el nro de dni
                        if(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni()== null || 
                            (VariablesPuntos.frmPuntos.getBeanTarjeta().getDni()!= null &&VariablesPuntos.frmPuntos.getBeanTarjeta().getDni().trim().length()==0)
                          ){
                            if(nroDocumentoAux.trim().length()>0){
                                VariablesPuntos.frmPuntos.getBeanTarjeta().setDni(nroDocumentoAux);
                            }else{
                                try{
                                    VariablesPuntos.frmPuntos.getBeanTarjeta().setDni(DBFidelizacion.getDniClienteFidelizado(campoTextoField));
                                }catch(Exception ex){
                                    log.error(" ", ex);
                                }
                                
                            }
                        }
                    }
                    
                    //return false;
                    return ConstantsPuntos.TARJETA_OFFLINE;
                }else{
                    // ERROR DE ORBIS
                    String estadoOperacion = tarjetaPuntos.getEstadoOperacion();
                    String mensaje = UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPuntos.getEstadoOperacion(),tarjetaPuntos.getMensaje());
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();  
                        VariablesFidelizacion.limpiaVariables();
                    }
                    
                    log.info("ERROR EN INIT DE ORBIS --> \n CODIGO DE ERROR "+estadoOperacion+"\n MENSAJE "+mensaje+
                             "NRO TARJETA "+campoTextoField+
                             "PASO POR LECTORA "+isLectorTarjeta);
                    
                    throw new Exception("Programa Monedero:\n" +
                                        "Error en consulta de tarjeta, se continuara como una venta normal,"+
                                        "estado de operacion ("+estadoOperacion+")\n" +
                                        mensaje);
                }
                
                
            }else{
                log.info("FUNCIONALIDAD DE LEALTAD INACTIVA");
                //consulto = false;
                return ConstantsPuntos.FUNCION_PTOS_DESACTIVA;
            }
        }catch(Exception ex){
            log.error("", ex);
            if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();    
            }
            FarmaUtility.showMessage(pJDialog, "PROGRAMA MONEDERO:\n"+
                                               "Error en consulta de tarjeta de puntos.\n"+
                                               ex.getMessage(), campoTexto);
            return ConstantsPuntos.ERROR_CONSULTA_TARJETA;
        }

        //return consulto;
    }
    
    /**
     * @author KMONCADA
     * @since 2015.02.09
     * @param pTarjeta
     * @param pDni
     */
    private static void validaCliente(String pTarjeta, String pDni) {
        try{
            ArrayList array = new ArrayList();
            if(pTarjeta!=null){
                pTarjeta = pTarjeta.trim();
            }
            DBFidelizacion.buscarTarjetasXDNI(array, pDni, pTarjeta);
            log.info("tamanio de array : "+array.size()+"  -  "+array);
            if(!isTarjetaValida(pTarjeta)){
                pTarjeta = null;
            }
            if(array.size()==0){
                // SI NO SE CREO CLIENTE ENTONCES SE CREA LA TARJETA
                String nroTarjeta = UtilityFidelizacion.generaNuevaTarjeta(ConstantsFidelizacion.PREFIJO_TARJETA_FIDELIZACION, FarmaVariables.vCodLocal, pTarjeta);
                //en caso el cliente no exista en el local se consultan datos a orbis
                 consultarAfiliado(null, nroTarjeta);
            }else{
                pTarjeta = (String)(((ArrayList)array.get(0)).get(0));
                //String indExisteLocal = DBFidelizacion.validaClienteLocal(pTarjeta);
                //log.info("utilitypuntos.validacliente "+ indExisteLocal);
                //if("0".equalsIgnoreCase(indExisteLocal)){
                consultarAfiliado(null, pTarjeta);
                //}
            }
        }catch(Exception ex){
            log.error("",ex);
        }
        
    }
    
    /**
     * Registro de afiliado en ORBIS
     * @author KMONCADA
     * @since 2015.02.09
     * @param pNroTarjetaLealtad
     * @param afiliado
     * @return
     */
    public static boolean registrarCliente(String pNroTarjetaLealtad, BeanAfiliado afiliado)throws Exception{
        
        boolean registroExitoso = true;
        
        BeanTarjeta tarjetaPtos = VariablesPuntos.frmPuntos.registrarAfiliado(pNroTarjetaLealtad, afiliado, getDNI_USU());
        // DATOS DE PRUEBA
     /*    DBPuntos.registroCliente(pNroTarjetaLealtad, afiliado.getDni(), afiliado.getNombre()); */
        // DATOS DE PRUEBA
        if (WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(tarjetaPtos.getEstadoOperacion())) {
            registroExitoso = false;
        }
        if (WSClientConstans.PARAMETRO_INCOMPLETO.equalsIgnoreCase(tarjetaPtos.getEstadoOperacion())) {
            registroExitoso = false;
            throw new Exception("Programa Monedero:\n" +
                                "Datos incompletos, "+
            UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPtos.getEstadoOperacion(),tarjetaPtos.getMensaje())+
                                "\nVerifique!!!");
        }
        if (WSClientConstans.PARAMETRO_INCORRECTO.equalsIgnoreCase(tarjetaPtos.getEstadoOperacion())) {
            registroExitoso = false;
            throw new Exception("Programa Monedero:\n" +
                                "Datos erroneos, verifique!!!");
        }
        if (WSClientConstans.YA_EXISTE_REGISTRO_TARJETA.equalsIgnoreCase(tarjetaPtos.getEstadoOperacion())) {
            registroExitoso = true;
        }
        if (!WSClientConstans.EXITO.equalsIgnoreCase(tarjetaPtos.getEstadoOperacion())) {
            log.info("Programa Monedero:\nError en el registro de afiliado.\nError ("+tarjetaPtos.getEstadoOperacion()+
                     ")\nMensaje: "+UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPtos.getEstadoOperacion(),tarjetaPtos.getMensaje()));
            registroExitoso = false;
        }
        
        
        // DATOS DE PRUEBA
        /*FarmaUtility.showMessage(null, "SE REGISTRO EN ORBIS\n TarjetaRegistro:"+pNroTarjetaLealtad+
                                            "\nAfiliado"+afiliado.getDni()+
                                            "\nNombre: "+afiliado.getApellidos()+" "+afiliado.getNombre());*/
        
        log.info("SE REGISTRO EN ORBIS\n TarjetaRegistro:"+pNroTarjetaLealtad+
                                            "\nAfiliado"+afiliado.getDni()+
                                            "\nNombre: "+afiliado.getApParterno()+" "+afiliado.getApMarterno()+" "+afiliado.getNombre());
        // DATOS DE PRUEBA
        
        return registroExitoso;
    }

    public static void calculaPuntos(String pCodGrupoCia, String pCodLocal, String pNumPedVta, boolean isRecalculo)throws Exception{
        calculaPuntos(pCodGrupoCia, pCodLocal, pNumPedVta, isRecalculo, false);
    }
    
    /**
     * @author DESAROLLO3
     * @param pCodGrupoCia
     * @param pCodLocal
     * @param pNumPedVta
     * @param isRecalculo
     * @throws Exception
     */
    public static void calculaPuntos(String pCodGrupoCia, String pCodLocal, String pNumPedVta, boolean isRecalculo, boolean isRedime)throws Exception{
        DBPuntos.calculoPuntos(pCodGrupoCia, pCodLocal, pNumPedVta, isRecalculo, isRedime);
    }
    
    /**
     * @author KMONCADA
     * @since 2015.03.12
     * @param pNroDni
     * @param pNroTarjetaPuntos
     * @throws Exception
     */
    public static boolean registroAfiliadoAutomatico(String pNroDni, String pNroTarjetaPuntos, String pNroTarjetaFidelizado){
        boolean procesoRegistro = false;
        try{
            
            BeanAfiliadoLocal afiliado = null;
            try{
                FacadePuntos facadeAfiliado = new FacadePuntos(); 
                afiliado = facadeAfiliado.obtenerClienteFidelizado(pNroDni);
                procesoRegistro = registrarCliente(pNroTarjetaPuntos, afiliado);
            }catch(Exception ex){
                afiliado = null;
                procesoRegistro = false;
                log.error("",ex);
            }
             if(afiliado !=null ){
                //DBFidelizacion.setIndEnvioRegistroPuntos(pNroTarjetaPuntos, afiliado.getDni(), procesoRegistro, pNroTarjetaFidelizado);
            }else{
                log.info("Programa Monedero: no cargo datos de afiliado");
            }
            return procesoRegistro;
        }catch(Exception e){
            log.error("",e);
            procesoRegistro = false;
        }
        return procesoRegistro;
    }
    
    /**
     * CONSULTA POR DATOS DE AFILIADO
     * @author KMONCADA
     * @since 2015.02.06
     * @param pParent
     * @param pNroTarjeta
     * @return
     * @throws Exception
     */
    private static BeanAfiliado consultarAfiliado(JDialog pParent, String pNroTarjeta)throws Exception{
        BeanAfiliado afiliado = null;
        BeanTarjeta tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
        if(tarjetaPuntos != null){
            String nroDni = tarjetaPuntos.getDni();
            if(nroDni == null) {
                log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] NRO DE DNI NULO");
                FarmaUtility.showMessage(pParent, "Nro Documento de Identidad invalido, \n"+
                                                          "No se pudo consultar datos de Afiliado", null);
            }else{
                if(WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                    log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] NO SE CONSULTA AFILIADO POR ESTAR TARJETA INACTIVA");
                    afiliado = new BeanAfiliado();
                }else{
                    if(WSClientConstans.EstadoTarjeta.INVALIDA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                        log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] TARJETA INVALIDA");
                        FarmaUtility.showMessage(pParent, "Tarjeta de Lealtad:\nTarjeta invalida.", null);
                        afiliado = null;
                    }else{
                        if(WSClientConstans.EXITO.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                            log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] CONSULTANDO EN ORBIS \n NRO DOCUMENTO --> "+nroDni+"\nNRO TARJETA --> "+pNroTarjeta);
                            // CONSULTA A PROGRAMA DE PUNTOS DE AFILIADO
                            afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliado(nroDni, getDNI_USU());
                            if(afiliado != null ){
                                if(afiliado.getDni().length()>0){
                                    log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] CONSULTA CON EXITO\n"+
                                             "NOMBRE.AFILIADO : "+afiliado.getNombre()+"\n"+
                                             "APE.PAT.AFILIADO : "+afiliado.getApParterno()+"\n"+
                                             "APE.MAT.AFILIADO : "+afiliado.getApMarterno()+"\n"+
                                             "NRO DOCUMENTO : "+afiliado.getDni());
                                    VariablesFidelizacion.vNumTarjeta = pNroTarjeta;
                                    VariablesFidelizacion.vIndEstado = "A";
                                    DBFidelizacion.insertarClienteFidelizacion(ConstantsPuntos.IND_PROCESO_ORBIS_ACT, 
                                                                               ConstantsPuntos.TRSX_ORBIS_ENVIADA, 
                                                                               afiliado);    
                                }else{
                                    log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] : TARJETA SIN AFILIADO O " +
                                             "ERROR AL TRAER DATOS DE AFILIADO \n NRO DOCUMENTO --> "+nroDni);
                                }
                            }else{
                                log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] CONSULTA DE AFILIADO ERROR");
                            }
                        }
                        
                    }
                }
            }
        }else{
            FarmaUtility.showMessage(pParent, "Tarjeta Monedero:\nError al obtener Tarjeta.", null);
        }
        return afiliado;
    }


    public static ArrayList listaProdPuntos(String vNumPedVta) throws Exception{
        // Calculara los puntos del sistema pendientes para acumular y grabar
        // DEBE DE HACER 2 COSAS.
        // DEBE DE GRABAR en Cabecera de Pedido el PUNTO ACUMULADO
        // DEBE DE GRABAR en Detalle el Punto Acumulado por Producto        
        return DBPuntos.getListProdPuntosRecupera(vNumPedVta);
        
    }

    private static void imprimePuntosAcumulados(double pPuntosAcum,
                                                String pTarjeta,
                                                String pDNI,
                                                String pNombre,
                                                boolean pOnline,
                                                JDialog pDialog,JTextField objeto,
                                                double pPtoSaldoAct,
                                                String pNumPedVta,
                                                String pListaProgramas) {
        if (pPuntosAcum > 0) {
            try {
                if(pPtoSaldoAct<0)
                    pPtoSaldoAct = 0;
                UtilityImprPuntos.imprimeRecuperaPuntos(pDialog, objeto, pNombre,pDNI, enmascararTarjeta(pTarjeta),
                                                        pPuntosAcum,pOnline,pPtoSaldoAct,pNumPedVta,pListaProgramas);

            } catch (Exception sqle) {
                log.error("",sqle);
                log.error("",sqle);
                FarmaUtility.showMessage(pDialog, "Error en la Impresion de Recuperación de Puntos\n"+
                                                  sqle.getMessage(), objeto);
            }
        }
    }
    
    
    
    /**
     * REALIZA LA OPERACION DE QUOTE CON PROVEEDOR, PERO SIN INCLUIR LOS PRODUCTOS BONIFICADOS
     * @author DESARROLLO3 
     * @since 2015.03.26 
     * @param tarjetaPuntos
     * @param pNumPedVta
     * @return
     * @throws Exception
     */
    public static boolean procesarQuoteSinBonificados(BeanTarjeta tarjetaPuntos, String pNumPedVta)throws Exception{
        log.info("PROGRAMA DE PUNTOS [QUOTE] : QUOTE SIN BONIFICADOS");
        // return procesarQuote(tarjetaPuntos, pNumPedVta, false);
        HiloFarmaPuntos hilo = new HiloFarmaPuntos("HILO_QUOTE", tarjetaPuntos,  pNumPedVta,  false);
        hilo.start();
        
        return true;
    }
    
    
    
    public static int obtieneTiempoMaximoLectora(){
        try{
            return DBPuntos.getTiempoMaxLectora();
        }catch(Exception ex){
            log.error("PROGRAMA PUNTOS: ERROR AL OBTENER MAXIMO TIEMPO DE LECTORA DE BARRAS");
            log.error("",ex);
            return 200;
        }
    }
    
    /**
     *
     * @author KMONCADA
     * @param tarjetaPuntos
     * @param myParentFrame
     * @param pJDialog
     * @param nroDocumento
     * @return
     * @throws Exception
     */
    public static boolean validaTarjetaAdicional(BeanTarjeta tarjetaPuntos, Frame myParentFrame, JDialog pJDialog, String nroDocumento)throws Exception{
        boolean resultado = true;
        
        if(WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta()) &&
            tarjetaPuntos.getDeslizaTarjeta()){
            
            int cantidadAdicional = cantidadTieneTarjetasAdicionales(myParentFrame, pJDialog, nroDocumento, true, true);
            if(cantidadAdicional<=-1){
                resultado = false;
            }else{
                if(cantidadAdicional >= 0){
                    if(cantidadAdicional == 0){
                        // imprimo voucher y continuo con afiliacion
                    }
                    resultado = true;
                }
            }
            /* 
            Afiliado afiliado = afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliado(nroDocumento, getDNI_USU());
            if(afiliado != null){
                List<String> lstTarjeta = afiliado.getTarjetas();
                log.info("PROGRAMA DE PUNTOS : [CONSULTA DE AFILIADO - ADICIONAL] TARJETAS REGISTRADAS "+lstTarjeta);
                int contador = 0;
                for(int i=0;i<lstTarjeta.size();i++){
                    String nroDocAux = lstTarjeta.get(i).trim();
                    if(nroDocAux.trim().length()>0 && !afiliado.getDni().trim().equalsIgnoreCase(nroDocAux)){
                        contador++;
                    }
                }
                log.info("PROGRAMA DE PUNTOS : [CONSULTA DE AFILIADO - ADICIONAL] TOTAL DE TARJETA EXISTENTES -->"+contador);
                if(contador>0){
                    DlgValidaDocumento dlg = new DlgValidaDocumento(myParentFrame, "",true);
                    dlg.setNroDocumento(nroDocumento);
                    dlg.setVisible(true);
                    String nroDocumentoDeslizo = dlg.getTextNroDocumento().trim();
                    if(nroDocumentoDeslizo.trim().length() == 0){
                        FarmaUtility.showMessage(pJDialog, "Debe ingresar el Nro Documento de Identidad.", null);
                        resultado = false;
                    }
                }else{
                    resultado = true;
                    log.info("PROGRAMA DE PUNTOS : [CONSULTA DE AFILIADO - ADICIONAL] NO ES TARJETA ADICIONAL");
                }
            }else{
                resultado = false;
                log.info("PROGRAMA DE PUNTOS : [CONSULTA DE AFILIADO - ADICIONAL] NO SE PUDO OBTENER AFILIADO");
                //throw new Exception("PROGRAMA PUNTOS [CONSULTA DE AFILIADO - ADICIONAL] NO SE PUDO OBTENER DATOS DE AFILIADO");
            } */
        }else{
            log.info("PROGRAMA DE PUNTOS : [CONSULTA DE AFILIADO - ADICIONAL] NO CUMPLE CON REQUERIMIENTOS DE VALIDACION DE TARJETA ADICIONAL. \n"+
                     "DELIZO TARJETA --> "+tarjetaPuntos.getDeslizaTarjeta()+"\n"+
                     "ESTADO DE TARJETA --> "+tarjetaPuntos.getEstadoTarjeta());
        }
        return resultado;
    }
    
    
    
    /**
     *update LTAVARA 2016.07.25 - indicar bonifica 
     * @param myParentFrame
     * @param pJDialog
     * @param tipoOperacion
     * @return
     * @throws Exception
     */
    public static boolean validaDocumentoRedimirBonificar(Frame myParentFrame, JDialog pJDialog, String tipoOperacion,
                                                          boolean indMuestraMensaje)throws Exception{
        boolean resultado = true;
        if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
            BeanTarjeta tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
            String docBonifica;
            if(ConstantsPuntos.BONIFICACION_PRODUCTOS.equalsIgnoreCase(tipoOperacion)){ 
            //Sólo para bonificar productos x+1
                docBonifica= DBPuntos.obtenerIndicadorDocBonificaProgramaXmas1();
            }else{
                
                docBonifica= DBPuntos.obtenerIndicadorDocBonificaPuntos(); 
                }
            
            // T: DESLIZA TARJETA, D:INGRESO DE DNI, A: AMBOS
            boolean isRequiereTarjeta = false;
            boolean isRequiereDni = false;
            
            if(ConstantsPuntos.BONIFICA_CON_TARJETA.equalsIgnoreCase(docBonifica)){
                resultado = tarjetaPuntos.getDeslizaTarjeta();
                isRequiereTarjeta = true;
                isRequiereDni = false;
            }else{
                if(ConstantsPuntos.BONIFICA_CON_DNI.equalsIgnoreCase(docBonifica)){
                    //ERIOS 22.06.2015 Solo valida el estado del digitar el DNI
                    resultado = tarjetaPuntos.getEscaneaDNI();
                    isRequiereTarjeta = false;
                    isRequiereDni = true;
                }else{
                    if(ConstantsPuntos.BONIFICA_CON_AMBOS.equalsIgnoreCase(docBonifica)){
                        resultado = tarjetaPuntos.getDeslizaTarjeta() && tarjetaPuntos.getEscaneaDNI();
                        isRequiereTarjeta = true;
                        isRequiereDni = true; 
                    }
                }
            }
            if(!resultado){
                String descMsjOperacion = "";
                
                if(ConstantsPuntos.REDENCION_PTOS.equalsIgnoreCase(tipoOperacion)){
                    descMsjOperacion = "Canje";
                }else if(ConstantsPuntos.BONIFICACION_PRODUCTOS.equalsIgnoreCase(tipoOperacion)){
                    descMsjOperacion = "Bonificación";
                }
                String mensaje = "Para realizar la operación de "+descMsjOperacion+", solicitar:\n\n";
                if(isRequiereTarjeta && !tarjetaPuntos.getDeslizaTarjeta()){
                    mensaje = mensaje + "--> TARJETA DE PROGRAMA PUNTOS";
                }
                if(isRequiereDni && !tarjetaPuntos.getEscaneaDNI()){
                    mensaje = mensaje + "\n--> DOCUMENTO DE IDENTIDAD";
                }
                
                if(ConstantsPuntos.REDENCION_PTOS.equalsIgnoreCase(tipoOperacion)){
                    mensaje = mensaje+"\n\n"+"¿Desea canjear?";
                }else if(ConstantsPuntos.BONIFICACION_PRODUCTOS.equalsIgnoreCase(tipoOperacion)){
                    mensaje = mensaje+"\n\n"+"¿Desea bonificar?";
                }
                
                boolean rspta = false;
                
                if(indMuestraMensaje)
                    rspta = JConfirmDialog.rptaConfirmDialog(pJDialog, mensaje);
                else
                    rspta = true;
                if(rspta){
                    DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(myParentFrame, "", true, tipoOperacion);
                   //LTAVARA 2015.07.25 
                    if(isRequiereTarjeta && tarjetaPuntos.getDeslizaTarjeta()){
                        dlgVerifica.setIsRequiereTarjeta(false); // no requiere deslizar la tarjeta porque ya lo tiene 
                    }else{
                    dlgVerifica.setIsRequiereTarjeta(isRequiereTarjeta);
                        }
                    
                    if(isRequiereDni && tarjetaPuntos.getEscaneaDNI()){
                        dlgVerifica.setIsRequiereDni(false); // no requiere escanear nuevamente el DNI porque ya lo tiene 
                    }else{
                        dlgVerifica.setIsRequiereDni(isRequiereDni);
                        }
                        
                   // dlgVerifica.setIsRequiereDni(isRequiereDni);
                    //dlgVerifica.setIsRequiereTarjeta(isRequiereTarjeta);
                    
                    dlgVerifica.setNroDocumento(tarjetaPuntos.getDni());
                    if(isRequiereTarjeta && tarjetaPuntos.getDeslizaTarjeta() && !tarjetaPuntos.getDni().equalsIgnoreCase(tarjetaPuntos.getNumeroTarjeta())){
                        dlgVerifica.setTextTarjeta(tarjetaPuntos.getNumeroTarjeta());
                    }
                    dlgVerifica.setVisible(true);
                    resultado = FarmaVariables.vAceptar;
                }else{
                    resultado = false;
                }
            }else{
                resultado = true;
            }
            
        }else{
            resultado = false;
        }
        return resultado;
    }
    
    public static boolean validaDocumentoRedimirBonificar(Frame myParentFrame, JDialog pJDialog, String tipoOperacion)throws Exception{
        boolean resultado = true;
        if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
            BeanTarjeta tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
            String docBonifica;
            if(ConstantsPuntos.BONIFICACION_PRODUCTOS.equalsIgnoreCase(tipoOperacion)){ 
            //Sólo para bonificar productos x+1
                docBonifica= DBPuntos.obtenerIndicadorDocBonificaProgramaXmas1();
            }else{    
                docBonifica= DBPuntos.obtenerIndicadorDocBonificaPuntos(); 
            }
            
            String dniTarjOpcReq = DBPuntos.obtenerIndicadorDniTarjetaOpcReq();
            // T: DESLIZA TARJETA, D:INGRESO DE DNI, A: AMBOS
            boolean isRequiereTarjeta = false;
            boolean isRequiereDni = false;
            
            if(ConstantsPuntos.BONIFICA_CON_TARJETA.equalsIgnoreCase(docBonifica)){
                resultado = tarjetaPuntos.getDeslizaTarjeta();
                isRequiereTarjeta = true;
                isRequiereDni = false;
            }else{
                if(ConstantsPuntos.BONIFICA_CON_DNI.equalsIgnoreCase(docBonifica)){
                    //ERIOS 22.06.2015 Solo valida el estado del digitar el DNI
                    resultado = tarjetaPuntos.getEscaneaDNI();
                    isRequiereTarjeta = false;
                    isRequiereDni = true;
                }else{
                    if(ConstantsPuntos.BONIFICA_CON_AMBOS.equalsIgnoreCase(docBonifica)){
                        //resultado = tarjetaPuntos.getDeslizaTarjeta() && tarjetaPuntos.getEscaneaDNI();
                        resultado = tarjetaPuntos.getDeslizaTarjeta() || tarjetaPuntos.getEscaneaDNI();
                        isRequiereTarjeta = true;
                        isRequiereDni = true; 
                    }
                }
            }
            if(!resultado){
                String descMsjOperacion = "";
                
                if(ConstantsPuntos.REDENCION_PTOS.equalsIgnoreCase(tipoOperacion)){
                    descMsjOperacion = "Canje";
                }else if(ConstantsPuntos.BONIFICACION_PRODUCTOS.equalsIgnoreCase(tipoOperacion)){
                    descMsjOperacion = "Bonificación";
                }
                String mensaje = "Para realizar la operación de "+descMsjOperacion+", solicitar:\n\n";
                if(isRequiereTarjeta && !tarjetaPuntos.getDeslizaTarjeta()){
                    mensaje = mensaje + "--> TARJETA DE PROGRAMA PUNTOS";
                }
                if(isRequiereDni && !tarjetaPuntos.getEscaneaDNI()){
                    mensaje = mensaje + "\n--> DOCUMENTO DE IDENTIDAD";
                }
                
                if(ConstantsPuntos.REDENCION_PTOS.equalsIgnoreCase(tipoOperacion)){
                    mensaje = mensaje+"\n\n"+"¿Desea canjear?";
                }else if(ConstantsPuntos.BONIFICACION_PRODUCTOS.equalsIgnoreCase(tipoOperacion)){
                    mensaje = mensaje+"\n\n"+"¿Desea bonificar?";
                }
                
                boolean rspta = JConfirmDialog.rptaConfirmDialog(pJDialog, mensaje);
                if(rspta){
                    DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(myParentFrame, "", true, tipoOperacion);
                   //LTAVARA 2015.07.25 
                    if(isRequiereTarjeta && tarjetaPuntos.getDeslizaTarjeta()){
                        dlgVerifica.setIsRequiereTarjeta(false); // no requiere deslizar la tarjeta porque ya lo tiene 
                    }else{
                    dlgVerifica.setIsRequiereTarjeta(isRequiereTarjeta);
                        }
                    
                    if(isRequiereDni && tarjetaPuntos.getEscaneaDNI()){
                        dlgVerifica.setIsRequiereDni(false); // no requiere escanear nuevamente el DNI porque ya lo tiene 
                    }else{
                        dlgVerifica.setIsRequiereDni(isRequiereDni);
                        }
                        
                   // dlgVerifica.setIsRequiereDni(isRequiereDni);
                    //dlgVerifica.setIsRequiereTarjeta(isRequiereTarjeta);
                    
                    
                    if(ConstantsPuntos.REDENCION_PTOS_DNI_TARJETA_REQ.equalsIgnoreCase(dniTarjOpcReq)){
                        if(ConstantsPuntos.BONIFICA_CON_AMBOS.equalsIgnoreCase(docBonifica)){
                            dlgVerifica.setDniTarjOpcReq(true);
                        }else{
                            dlgVerifica.setDniTarjOpcReq(false);
                        }
                    }else if(ConstantsPuntos.REDENCION_PTOS_DNI_TARJETA_OPC.equalsIgnoreCase(dniTarjOpcReq)){
                        dlgVerifica.setDniTarjOpcReq(false);
                    }
                                        
                    dlgVerifica.setNroDocumento(tarjetaPuntos.getDni());
                    if(isRequiereTarjeta && tarjetaPuntos.getDeslizaTarjeta() && !tarjetaPuntos.getDni().equalsIgnoreCase(tarjetaPuntos.getNumeroTarjeta())){
                        dlgVerifica.setTextTarjeta(tarjetaPuntos.getNumeroTarjeta());
                    }
                    dlgVerifica.setVisible(true);
                    resultado = FarmaVariables.vAceptar;
                }else{
                    resultado = false;
                }
            }else{
                resultado = true;
            }
            
        }else{
            resultado = false;
        }
        return resultado;
    }
    
    
    /**
     *
     * @author KMONCADA
     * @since 
     * @param myParentFrame
     * @param nroTarjeta
     * @param tipoOperacion
     * @param isDeslizaTarjeta
     * @param nroDocIdentidad
     * @return
     * @throws Exception
     */
    public static String obtenerEstadoTarjeta(Frame myParentFrame, JDialog pDialog, String nroTarjeta, String tipoOperacion, boolean isDeslizaTarjeta, String nroDocIdentidad)throws Exception{
        if(VariablesPuntos.frmPuntos != null){
            if(!UtilityPuntos.isTarjetaValida(nroTarjeta)){
                log.info("PROGRAMA DE PUNTOS [CONSULTA ESTADO TARJETA] : TARJETA INGRESADA INVALIDA --> " + nroTarjeta);
                throw new Exception("PROGRAMA DE PUNTOS: \nTARJETA INVALIDA, VERIFIQUE!!!");
            }
            String rsptaTarjeta = VariablesPuntos.frmPuntos.getEstadoTarjeta(nroTarjeta, getDNI_USU());
            String[] array = rsptaTarjeta.split("@");
            String estadoTarjeta = array[0].trim();
            if(WSClientConstans.EstadoTarjeta.SIN_ESTADO.equalsIgnoreCase(estadoTarjeta)){
                log.info("PROGRAMA DE PUNTOS : [CONSULTA TARJETA] OPERACION MODO OFFLINE");
                if(ConstantsPuntos.REDENCION_PTOS.equalsIgnoreCase(tipoOperacion))
                    return nroDocIdentidad;
                else{
                    throw new Exception("PROGRAMA DE PUNTOS : "+obtenerMensajeErrorLealtad(estadoTarjeta, ""));
                }
            }else if(WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(estadoTarjeta)){
                BeanTarjeta tarjetaPuntos = new BeanTarjeta();
                tarjetaPuntos.setDeslizaTarjeta(isDeslizaTarjeta);
                tarjetaPuntos.setEstadoTarjeta(array[0].trim());
                
                boolean rsptaValida = validaTarjetaAdicional(tarjetaPuntos, myParentFrame, new JDialog(), nroDocIdentidad);
                if(rsptaValida){
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta() == null){
                        tarjetaPuntos.setDni(nroDocIdentidad);
                        VariablesPuntos.frmPuntos.setBeanTarjeta(tarjetaPuntos);
                    }
                    /* int cantidadTarjetaAdicional = cantidadTieneTarjetasAdicionales(myParentFrame, new JDialog(), nroDocIdentidad, false, false);
                    if(cantidadTarjetaAdicional == 0){
                        //UtilityFidelizacion.imprimirVoucherFid(nroDocIdentidad);
                        UtilityFidelizacion.imprimirVoucherFid(nroDocIdentidad, tarjetaPuntos.getNumeroTarjeta());
                        rsptaValida =  JConfirmDialog.rptaConfirmDialog(pDialog, "Recoger voucher de verificación de datos.\n" +
                                                                                 "¿Cliente firmó Voucher?");
                        //FarmaUtility.showMessage(pDialog, "Recoger voucher de verificación de datos", null);
                        
                        if(rsptaValida){
                            log.info("PROGRAMA PUNTOS : CLIENTE FIRMO VOUCHER");
                            //ERIOS 22.06.2015 Se registra inscripcion por turno
                            FacadeLealtad facadeLealtad = new FacadeLealtad();
                            facadeLealtad.registrarInscripcionTurno(VariablesCaja.vSecMovCaja, nroTarjeta);
                            rsptaValida = registroAfiliadoAutomatico(nroDocIdentidad, nroTarjeta, nroTarjeta);
                            return nroDocIdentidad;
                        }else{
                            log.info("PROGRAMA PUNTOS : CLIENTE NO FIRMO VOUCHER");
                            FacadeLealtad facadeLealtad = new FacadeLealtad();
                            facadeLealtad.rechazarIncripcionPuntos(nroDocIdentidad,VariablesCaja.vSecMovCaja, tarjetaPuntos.getNumeroTarjeta());
                            throw new Exception("PROGRAMA DE PUNTOS: \nSE CANCELA EL PROCESO DE AFILIACION DEL CLIENTE.");
                        }
                    }else{
                        rsptaValida = registroAfiliadoAutomatico(nroDocIdentidad, nroTarjeta, nroTarjeta);
                        return nroDocIdentidad;
                    } */
                    return nroDocIdentidad;
                    /*
                    rsptaValida = registroAfiliadoAutomatico(nroDocIdentidad, nroTarjeta, nroTarjeta);
                    
                    //KMONCADA 17.06.2015
                    if(rsptaValida){
                        //UtilityFidelizacion.imprimirVoucherFid(nroDocIdentidad);
                        UtilityFidelizacion.imprimirVoucherFid(tarjetaPuntos.getDni(), tarjetaPuntos.getNumeroTarjeta());
                        FarmaUtility.showMessage(pDialog, "Recoger voucher de verificación de datos", null);
                    }*/
                    
                }
                log.info("PROGRAMA DE PUNTOS [CONSULTA ESTADO TARJETA] : VALIDACION DE TARJETA ADICIONAL NO EXITOSA.");
                throw new Exception("PROGRAMA DE PUNTOS: \n"+obtenerMensajeErrorLealtad(estadoTarjeta, ""));
                
            }else if(WSClientConstans.EstadoTarjeta.BLOQUEADA.equalsIgnoreCase(estadoTarjeta) ||
                     WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR.equalsIgnoreCase(estadoTarjeta) ||
                     WSClientConstans.EstadoTarjeta.INVALIDA.equalsIgnoreCase(estadoTarjeta)){
                log.info("PROGRAMA DE PUNTOS [CONSULTA ESTADO TARJETA] : ESTADO DE TARJETA --> "+estadoTarjeta);
                throw new Exception(/*"PROGRAMA DE PUNTOS: \n"+*/obtenerMensajeErrorLealtad(estadoTarjeta, "TARJETA BLOQUEADA/BLOQUEADA REDIMIR/INVALIDA"));
            }
            log.info("PROGRAMA DE PUNTOS [CONSULTA ESTADO TARJETA] : OK - ESTADO DE TARJETA --> "+estadoTarjeta);
            return array[1].trim();
        }else{
            log.info("PROGRAMA DE PUNTOS [CONSULTA ESTADO TARJETA] : VARIABLES NO INICIALIZADAS.");
            throw new Exception("PROGRAMA DE PUNTOS: \nVARIABLES NO INICIALIZADAS.");
        }
    }
    
    /**
     *
     * @param myParentFrame
     * @param pJDialog
     * @param tarjetaPuntosOld
     */
    public static void transferirInscripcionBonificados(Frame myParentFrame, JDialog pJDialog, BeanTarjeta tarjetaPuntosOld,Boolean permiteValidacionX1){
        UtilityVentas utilityVentas = new UtilityVentas();
      
        if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
            log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] INICIO");
            BeanTarjeta tarjetaPuntosActual = VariablesPuntos.frmPuntos.getBeanTarjeta();
            log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] TARJETA ANTIGUA "+tarjetaPuntosOld.getListaInscritos());
            log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] TARJETA NUEVA "+tarjetaPuntosActual.getListaInscritos());
            if(tarjetaPuntosActual.getDni().trim().length() > 0 && tarjetaPuntosOld.getDni().trim().length() > 0){
                if(tarjetaPuntosActual.getDni().trim().equalsIgnoreCase(tarjetaPuntosOld.getDni().trim())){
                    log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] MISMO CLIENTE "+tarjetaPuntosActual.getDni() +" - "+tarjetaPuntosOld.getDni());
                    tarjetaPuntosActual.setListaInscritos(tarjetaPuntosOld.getListaInscritos());
                    log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] TARJETA ANTIGUA "+tarjetaPuntosOld.getListaInscritos());
                    log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] TARJETA NUEVA "+tarjetaPuntosActual.getListaInscritos());
                }
                String codProd = "";
                String descProd = "";
                
                for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                    BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
                    codProd = bean.getVCodProd();
                    descProd = codProd+" - "+bean.getVDescProd();
                    log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] CODIGO DE PRODUCTO "+codProd);
                    utilityVentas.verificaInscripcionX1(pJDialog,myParentFrame,codProd, descProd, permiteValidacionX1);
                }
        
                for (int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++) {
                    codProd = (String)((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i)).get(0);
                    descProd = codProd+" - "+(String)((ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(i)).get(1);
                    log.info("PROGRAMA DE PUNTOS : [TRANSFERENCIA DE INSCRITOS] CODIGO DE PRODUCTO "+codProd);
                    utilityVentas.verificaInscripcionX1(pJDialog,myParentFrame,codProd,descProd, permiteValidacionX1);
                }
            }
        }
    }
    
    public static int cantidadTieneTarjetasAdicionales2(Frame myParentFrame, JDialog pJDialog, String nroDocumento, boolean solicitaValidaDocumento)throws Exception{
        int cantidadTarjeta = -1;
        BeanAfiliado afiliado = afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliado(nroDocumento, getDNI_USU());
        if(WSClientConstans.EXITO.equalsIgnoreCase(VariablesPuntos.frmPuntos.getBeanTarjeta().getEstadoOperacion())){
            if(afiliado != null){
                List<String> lstTarjeta = afiliado.getTarjetas();
                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] TARJETAS ASOCIADAS A DOCUMENTO --> "+nroDocumento+"\n"+
                         "TARJETAS --> "+lstTarjeta);
                int contador = 0;
                for(int i=0;i<lstTarjeta.size();i++){
                    String nroDocAux = lstTarjeta.get(i).trim();
                    if(nroDocAux.trim().length()>0 && !afiliado.getDni().trim().equalsIgnoreCase(nroDocAux)){
                        contador++;
                    }
                }
                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] TOTAL DE TARJETA EXISTENTES ASOCIADAS -->"+contador);
                if(contador>0){
                    cantidadTarjeta = contador;
                    if(solicitaValidaDocumento){
                        log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] SOLICITA VALIDA DOCUMENTO DE IDENTIDAD");
                        DlgValidaDocumento dlg = new DlgValidaDocumento(myParentFrame, "",true);
                        dlg.setNroDocumento(nroDocumento);
                        dlg.setVisible(true);
                        String nroDocumentoDeslizo = dlg.getTextNroDocumento().trim();
                        if(nroDocumentoDeslizo.trim().length() == 0){
                            FarmaUtility.showMessage(pJDialog, "Debe ingresar el Nro Documento de Identidad.", null);
                            cantidadTarjeta = -1;
                            //resultado = false;
                        }
                    }
                }else{
                    cantidadTarjeta = 0;
                    //cantidadTarjeta = true;
                    log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] NO ES TARJETA ADICIONAL");
                }
            }else{
                //m cantidadTarjeta = false;
                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] : NO SE PUDO OBTENER AFILIADO --> "+nroDocumento);
                //throw new Exception("PROGRAMA PUNTOS: ERROR AL OBTENER DATOS DE AFILIADO");
            }
        }
        return cantidadTarjeta;
        
    }
    /**
     *
     * @param myParentFrame componente 
     * @param pJDialog Dialog donde se ejecutar la accion
     * @param nroDocumento nro de documento del cliente
     * @param solicitaValidaDocumento indica si se validara documento de identidad
     * @param actualizaAfiliado 
     * @return
     * @throws Exception
     */
    public static int cantidadTieneTarjetasAdicionales(Frame myParentFrame, JDialog pJDialog, String nroDocumento, boolean solicitaValidaDocumento, boolean isValidaTarjetaOtroPrograma)throws Exception{
        int cantidadTarjeta = -1;
        boolean isValidaTarjetaConvenio = false;
        boolean isBloqueAsociarTarjOrbis = restrigueAsociarTarjetasOrbis();
        log.info("RECUEPERA DATOS DE CLIENTE");
        BeanAfiliado afiliado = afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliadoSinTarjeta(nroDocumento, getDNI_USU());
        log.info("CLIENTE RECUEPRADO: "+afiliado.apParterno+" - "+afiliado.getFechaNacimiento());
        if(afiliado != null){
            if(afiliado.getDni().trim().length()==0){
                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] CLIENTE NUEVO NO REGISTRO EN ORBIS --> "+nroDocumento+"\n");
                cantidadTarjeta = 0;
            }else{
                List<String> lstTarjeta = afiliado.getTarjetas();
                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] TARJETAS ASOCIADAS A DOCUMENTO --> "+nroDocumento+"\n"+
                         "TARJETAS --> "+lstTarjeta);
                int contador = 0;
                for(int i=0;i<lstTarjeta.size();i++){
                    String nroDocAux = lstTarjeta.get(i).trim();
                    if(nroDocAux.trim().length()>0 && !afiliado.getDni().trim().equalsIgnoreCase(nroDocAux)){
                        if(isValidaTarjetaOtroPrograma){
                            if(UtilityPuntos.isTarjetaOtroPrograma(nroDocAux, true)){
                                log.info("PROGRAMA DE PUNTOS [CANTIDAD DE TARJETAS] : TARJETA " + nroDocAux + " ASOCIADA A OTRO PROGRAMA, NO VALIDA COMO TARJETA ADICIONAL");
                            }else{
                                contador++;
                            }
                        }else{
                            contador++;
                        }
                    }
                }
                
                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] TOTAL DE TARJETA EXISTENTES ASOCIADAS -->"+contador);
                if(contador>0){
                    cantidadTarjeta = contador;
                    if(solicitaValidaDocumento){
                        log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] SOLICITA VALIDA DOCUMENTO DE IDENTIDAD");
                        String mensaje = DBPuntos.getMensajeTarjetAdicional();
                        log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] MENSAJE A MOSTRAR --> "+mensaje);
                        boolean isContinuar = JConfirmDialog.rptaConfirmDialog(pJDialog, mensaje, "Aceptar", "Rechazar");
                        //FarmaUtility.showMessage(pJDialog, "PROGRAMA DE PUNTOS: TARJETA ADICIONAL:\nSOLICITE DOCUMENTO DE IDENTIDAD E INGRESELO MEDIANTE EL LECTOR DE BARRAS.", null);
                        if(isContinuar){
                            if(isValidaTarjetaConvenio){
                                FarmaUtility.showMessage(pJDialog, "CLIENTE CUENTA CON UNA TARJETA ASOCIADA ESPECIAL.\nLA CUAL NO PUEDE SER BLOQUEADA.", null);
                                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] CLIENTE CUENTA CON TARJETA DISTINTA A LA NUMERADA POR ORBIS");
                                cantidadTarjeta = -2;
                            }else{
                                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] SE ACEPTO VALIDACION DE TARJETA ADICIONAL");
                                boolean isValida = DBPuntos.isValidaDocumentoTarjetaAdicional();
                                if(isValida){
                                    log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] REQUIERE DOCUMENTO DE IDENTIDAD PARA VALIDAR TARJETA ADICIONAL");
                                    DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(myParentFrame, "", true, ConstantsPuntos.MNTO_FIDELIZACION);
                                    dlgVerifica.setNroDocumento(nroDocumento);
                                    dlgVerifica.setIsRequiereDni(true);
                                    dlgVerifica.setIsRequiereTarjeta(false);
                                    dlgVerifica.setVisible(true);
                                    if(!FarmaVariables.vAceptar){
                                        FarmaUtility.showMessage(pJDialog, "Debe ingresar el Nro Documento de Identidad.", null);
                                        cantidadTarjeta = -2;
                                    }
                                }else{
                                    log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] NO REQUIERE DOCUMENTO DE IDENTIDAD PARA VALIDAD TARJETA ADICIONAL");
                                    VariablesPuntos.frmPuntos.getBeanTarjeta().setEscaneaDNI(true);
                                }
                            }
                        }else{
                            log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] SE RECHAZO VALIDACION DE TARJETA ADICIONAL");
                            cantidadTarjeta = -2;
                        }
                    }
                }else{
                    cantidadTarjeta = 0;
                    //cantidadTarjeta = true;
                    log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] NO TIENE OTRAS TARJETAS");
                }
                /*
                if(actualizaAfiliado){
                    if(afiliado != null){
                        log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] CONSULTA CON EXITO\n"+
                                 "NOMBRE.AFILIADO : "+afiliado.getNombre()+"\n"+
                                 "APE.PAT.AFILIADO : "+afiliado.getApParterno()+"\n"+
                                 "APE.MAT.AFILIADO : "+afiliado.getApMarterno()+"\n"+
                                 "NRO DOCUMENTO : "+afiliado.getDni());
                        String aux = VariablesFidelizacion.vNumTarjeta;
                        VariablesFidelizacion.vNumTarjeta = "";
                        VariablesFidelizacion.vIndEstado = "A";
                        
                        DBFidelizacion.insertarClienteFidelizacion(ConstantsPuntos.IND_PROCESO_ORBIS, 
                                                                   ConstantsPuntos.TRSX_ORBIS_ENVIADA, 
                                                                   afiliado);    
                        
                        VariablesFidelizacion.vNumTarjeta = aux;
                    }else{
                        log.info("PROGRAMA DE PUNTOS [CONSULTA DE AFILIADO] CONSULTA DE AFILIADO ERROR");
                    }
                }
                */
            }
            
        }else{
            //m cantidadTarjeta = false;
            log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] : NO SE PUDO OBTENER AFILIADO --> "+nroDocumento);
            //throw new Exception("PROGRAMA PUNTOS: ERROR AL OBTENER DATOS DE AFILIADO");
            
            if(solicitaValidaDocumento){
                log.info("PROGRAMA DE PUNTOS [VALIDA TARJETAS ADICIONALES] SOLICITA VALIDA DOCUMENTO DE IDENTIDAD");
                FarmaUtility.showMessage(pJDialog, "PROGRAMA DE PUNTOS: \nSOLICITE DOCUMENTO DE IDENTIDAD E INGRESELO MEDIANTE EL LECTOR DE BARRAS.", null);
                DlgVerificaDocRedencionBonifica dlgVerifica = new DlgVerificaDocRedencionBonifica(myParentFrame, "", true, ConstantsPuntos.MNTO_FIDELIZACION);
                dlgVerifica.setNroDocumento(nroDocumento);
                dlgVerifica.setIsRequiereDni(true);
                dlgVerifica.setIsRequiereTarjeta(false);
                dlgVerifica.setVisible(true);
                cantidadTarjeta = 0;
                if(!FarmaVariables.vAceptar){
                    FarmaUtility.showMessage(pJDialog, "Debe ingresar el Nro Documento de Identidad.", null);
                    cantidadTarjeta = -2;
                }
                /*
                DlgValidaDocumento dlg = new DlgValidaDocumento(myParentFrame, "",true);
                dlg.setNroDocumento(nroDocumento);
                dlg.setVisible(true);
                String nroDocumentoDeslizo = dlg.getTextNroDocumento().trim();
                if(nroDocumentoDeslizo.trim().length() == 0){
                    FarmaUtility.showMessage(pJDialog, "Debe ingresar el Nro Documento de Identidad.", null);
                    cantidadTarjeta = -1;
                    //resultado = false;
                }*/
            }
        }
        
        return cantidadTarjeta;
        
    }
    
    /**
     *
     * @param pParent
     * @param pJDialog
     * @param nroTarjeta
     * @param nroDocumento
     * @return
     */
    public static boolean impresionVoucherAfiliacion(Frame pParent, JDialog pJDialog, String nroTarjeta, String nroDocumento){
        boolean imprime = false;
        int cantidad = 0;
        boolean isAceptoAfiliado = false;
        // valida cantidad de tarjetas asociadas al nro de documento del cliente.
        try{
            cantidad = cantidadTieneTarjetasAdicionales(pParent, pJDialog, nroDocumento, false, false);
            //Afiliado afiliado = consultarAfiliado(pJDialog, nroDocumento);
            if(cantidad == 0){
                imprime = true;
            }else{
                if(cantidad == -1){
                    imprime = !DBPuntos.isValidaClienteAfiliadoLocal(nroDocumento);
                    //imprime = !DBPuntos.isTarjetaAsociada(nroDocumento, nroTarjeta);
                }
            }
        }catch(Exception ex){
            log.error(" ", ex);
        }
        // si cliente no tiene ningun tarjeta asociada se imprime voucher
        if(imprime){
            //KMONCADA 25.06.2015 SE REALIZA IMPRESION DE VOUCHER
            FacadePuntos facadePtos = new FacadePuntos();
            facadePtos.impresionVoucherAfiliacion(nroDocumento);
            //UtilityFidelizacion.imprimirVoucherFid(nroDocumento);
            isAceptoAfiliado =  JConfirmDialog.rptaConfirmDialog(pJDialog, "Recoger voucher de verificacion de datos.\n" +
                                                                           "¿Cliente firmó Voucher?");
            if(isAceptoAfiliado){
                //ERIOS 22.06.2015 Se registra inscripcion por turno
                FacadeLealtad facadeLealtad = new FacadeLealtad();
                facadeLealtad.registrarInscripcionTurno(VariablesCaja.vSecMovCaja, nroTarjeta);
                VariablesFidelizacion.vIsPrimeraCompraFidelizadoNuevo = true;
            }else{
                //KMONCADA 30.06.2015 SE BORRA LOS REGISTROS
                VariablesFidelizacion.limpiaVariables();
                FacadeLealtad facadeLealtad = new FacadeLealtad();
                facadeLealtad.rechazarIncripcionPuntos(nroDocumento,VariablesCaja.vSecMovCaja, nroTarjeta);
                VariablesFidelizacion.vIsPrimeraCompraFidelizadoNuevo = false;
            }
        }else{
            isAceptoAfiliado = true;
        }
        
        // KMONCADA 07.08.2015 SE REGISTRARA LA ENTREGA DE LA TARJETA AL CLIENTE COMO MOVIMIENTO DE KARDEX
        if(isAceptoAfiliado){
            log.info("REGISTRARA EL MOVIENTO EN EL KARDEX DE LA ENTREGA DE LA TARJETA: NRO TARJETA --> "+nroTarjeta+"\nDOCUMENTO --> "+nroDocumento);
            FacadePuntos facadePtos = new FacadePuntos();
            facadePtos.registroEntregaTarjetaPtos(nroTarjeta, nroDocumento);
        }
        return isAceptoAfiliado;
    }
    
    public static boolean actualizarEstadoEnvioAfiliacion(String pNroTarjeta, String pNroDocumento, String pEstado){
        FacadePuntos facadePtos = new FacadePuntos();
        return facadePtos.actualizarEstadoAfiliacion(pNroTarjeta, pNroDocumento, pEstado);
    }
    /*
    public static boolean evaluaTarjeta(String nroTarjeta, String pTipoTarjeta){
        FacadePuntos facadePtos = new FacadePuntos();
        return facadePtos.evaluaTipoTarjeta(nroTarjeta, pTipoTarjeta);
    }
    */
    public static boolean isTarjetaOtroPrograma(String nroTarjeta, boolean isIncluidoEnPtos){
        FacadePuntos facadePtos = new FacadePuntos();
        return facadePtos.isTarjetaOtroPrograma(nroTarjeta, isIncluidoEnPtos);
    }
    
    public static boolean restrigueAsociarTarjetasOrbis(){
        FacadePuntos facadePtos = new FacadePuntos();
        return facadePtos.restrigueAsociarTarjetasOrbis();
    }
    
    public static boolean isBloqueaVtaConvenio(){
        boolean bloquea = false;
        // CUANDO ESTA ACTIVO PTOS PERMITE VENTA CONVENIO
        bloquea = !isActivoFuncionalidad();
        if(!bloquea){
            // SI PERMITE LA VENTA VERIFICAR SI ES UNA VENTA CON TARJETA DE CREDITO CON CAPAÑA
            if(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana!=null && VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.length()>0){
                bloquea = true;
            }
        } 
        return bloquea;
    }
    

    public static boolean bloqueoTarjeta(JDialog pDialog, String vNumTarjeta, JTextField campoTexto) {
        boolean bloqueado = false;
        log.info("[BLOQUEO DE TARJETA] SE REALIZARA EL BLOQUEO DE LA TARJETA: "+vNumTarjeta);
        if(VariablesPuntos.frmPuntos!=null){
            VariablesPuntos.frmPuntos.bloquearTarjeta(vNumTarjeta, getDNI_USU());
            BeanTarjeta tarjeta = VariablesPuntos.frmPuntos.getBeanTarjeta();
            if(WSClientConstans.EXITO.equalsIgnoreCase(tarjeta.getEstadoOperacion())){
                FacadePuntos facade = new FacadePuntos();
                facade.bloqueoTarjeta(vNumTarjeta);
                FarmaUtility.showMessage(pDialog, "PROGRAMA MONEDERO:\nSE REALIZO EL BLOQUEO DE LA TARJETA CON EXITO", campoTexto);
                bloqueado = true;
            }else{
                String mensaje = obtenerMensajeErrorLealtad(tarjeta.getEstadoOperacion(),tarjeta.getMensaje());
                log.info("[BLOQUEO DE TARJETA] SE PRESENTO UN PROBLEMA.\nCODIGO DE ERROR"+tarjeta.getEstadoOperacion()+
                         "MENSAJE DE ERROR: "+tarjeta.getMensaje());
                FarmaUtility.showMessage(pDialog, "PROGRAMA MONEDERO:\nSe presento al procesar la operación\n"+
                                                  mensaje+"\nReintente por favor, si persiste comuniquese con Mesa de Ayuda.", campoTexto);
                
            }
        }
        campoTexto.setText("");
        return bloqueado;
    }
    
    /**
     * 
     */
    public static boolean quitarPacksFidelizados(){
        
        boolean quito = false;
        if(VariablesPuntos.frmPuntos.getBeanTarjeta()==null){
            for(int w=0;w<VariablesVentas.vArrayList_Promociones.size();w++){
                ArrayList lineaAux = (ArrayList)VariablesVentas.vArrayList_Promociones.get(w);
                String codProm = (String)lineaAux.get(0);
                String indFidelizado = (String)lineaAux.get(25);
                if("S".equalsIgnoreCase(indFidelizado)){
                    boolean termino = false;
                    int cont = 0;
                    boolean borro = false;
                    while(!termino){
                        ArrayList lstProdProm = (ArrayList)VariablesVentas.vArrayList_Prod_Promociones.get(cont);
                        String codPromProd = (String)lstProdProm.get(18);
                        if(codProm.equalsIgnoreCase(codPromProd)){
                            VariablesVentas.vArrayList_Prod_Promociones.remove(cont);
                            borro = true;
                            if(VariablesVentas.vArrayList_Prod_Promociones.size() <= cont){
                                termino = true;
                            }
                        }else{
                            cont++;
                            if(borro){
                                termino = true;
                            }
                        }
                    }
                    VariablesVentas.vArrayList_Promociones.remove(w);
                    quito = true;
                }
            }
        }
        return quito;
    }
    /** 2016.07.26
     *LTAVARA
     * @param myParentFrame
     * @param pJDialog
     * @param cantidad
     * @param producto
     * @return
     * @throws Exception
     */
    public static Integer cantidadBonificarCotiza(Frame myParentFrame, JDialog pJDialog, Integer pCantidad,
                                                  String pCodProducto,String pValFrac , String pUnidad,boolean mostrarMensaje,
                                                  ArrayList vDatos,
                                                  String prodEquivalente) throws Exception {
        ArrayList listaProductos;
        String[] listaNombre;
        int cantidadPagar=pCantidad;
        //String prodEquivalente;
        String item;
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
        
        
        if (tarjetaCliente != null) {
            listaNombre=tarjetaCliente.getNombreCompleto().split("\\s+");
            listaProductos = (ArrayList<String>)tarjetaCliente.getListaAuxiliarInscritos();
            //obtener el codigo equivalente del producto
            //prodEquivalente = facadeLealtad.verificaInscripcionProducto(listaProductos, pCodProducto);
            if (prodEquivalente!="") {
                item=prodEquivalente+","+pCantidad;
                //obtener la cantidad del ITEM QUOTE
               tarjetaCliente= VariablesPuntos.frmPuntos.getCantidadBonificarPorProducto(tarjetaCliente.getNumeroTarjeta(), 
                                                                                         //FarmaVariables.vCodUsuMatriz ,
                                                                                         getDNI_USU(),
                                                                                         item);
                BeanOperacion operacion = VariablesPuntos.frmPuntos.getBitacora();
                log.info("[ITEMQUOTE] [INPUT] "+operacion.getVInput_in());
               log.info("[ITEMQUOTE] [OUTPUT] "+operacion.getVOutput());
               //Solo cuando el producto acumulado es el mismo al bonificado
               List<String> listaPendienteAcumular=tarjetaCliente.getListaPendienteAcumular();
                int cantidadPagarQuote=0;
                int cantiadBonificaQuote=0;
                boolean indPendienteAcumular =false;
                if(listaPendienteAcumular.size()>=1 ){
                for(String pendienteAcumular: listaPendienteAcumular){//validar si en la respuesta se encuenta el codigo equivalente enviado
                    String[] datoProd =pendienteAcumular.split(",");
                    String prodEq=datoProd[0].trim();
                    if(prodEquivalente.equals(prodEq) ){
                            cantidadPagarQuote=Integer.parseInt(datoProd[1].trim());
                            cantiadBonificaQuote=Integer.parseInt( datoProd[3].trim());
                            indPendienteAcumular=true;
                        }
                    }
                }
                
                if(indPendienteAcumular){//porque en la respuesta del ItemQuote se encontro el codigo equivalente
                //LAS CANTIDADES SON ENTERAS
                   String vCtdNormal =String.valueOf(cantidadPagarQuote);// DBPuntos.getCantProdFracLocal(cantidadPagarQuote, pCodProducto,pValFrac, "OBTENER");
                   String vCtdBono =String.valueOf(cantiadBonificaQuote);// DBPuntos.getCantProdFracLocal(cantiadBonificaQuote,pCodProducto,pValFrac, "OBTENER");
                    
                    
                   int cantidadPagarQuoteFracLocal= Integer.parseInt(vCtdNormal);
                   int cantiadBonificaQuoteFracLocal= Integer.parseInt(vCtdBono);
                    
                    if(cantidadPagarQuoteFracLocal>0)
                        vDatos.add(vCtdNormal);
                    
                    if(cantiadBonificaQuoteFracLocal>0)
                        vDatos.add(vCtdBono);
                    else
                        vDatos.add("0");
                    
                       // La cantidad a pagar del cantidadPagarQuote si es menor a la cantidad ingresada mostrar el mensaje
                           if (cantidadPagarQuote < pCantidad && cantidadPagarQuoteFracLocal>0){
                               cantidadPagar=cantidadPagarQuoteFracLocal;
                               List<String> listaProducto= tarjetaCliente.getListaProdItemQuote();
                               for (int i=0; i<listaProducto.size(); i++){
                                    String[] lista=listaProducto.get(i).split(",");
                                   if(lista[0].equals(prodEquivalente)){
                                        listaProducto.remove(i);
                                        break;
                                       }
                               }
    
                               listaProducto.add(prodEquivalente+","+cantidadPagar);
                               tarjetaCliente.setListaProdItemQuote(listaProducto);
                               /*if(mostrarMensaje){
                               FarmaUtility.showMessage(pJDialog, "¡ATENCION! "+listaNombre[0]+", \n SI COMPRAS "+cantidadPagarQuoteFracLocal+" "+pUnidad+" GANAS GRATIS "+cantiadBonificaQuoteFracLocal+" "+pUnidad , "");
                               }*/
                           }else{
                                //devuelve el mismo valor ingresado
                                //cantidadPagar= Integer.parseInt(DBPuntos.getCantProdFracLocal(pCantidad, pCodProducto,pValFrac, "OBTENER"));
                                cantidadPagar= pCantidad;
                               }
                }else{ //devuelve el mismo valor ingresado
                      //cantidadPagar= Integer.parseInt(DBPuntos.getCantProdFracLocal(pCantidad, pCodProducto,pValFrac, "OBTENER"));
                      cantidadPagar= pCantidad;
                    }
           
            }
            return cantidadPagar;
        }
        return cantidadPagar;
    }
    
    
    /**
     * LTAVARA 2016.08.04
     * @param pNumPedVta
     */
    public static void imprVoucherContinuaBonificando(String pNumPedVta,String pProductoAcumualdoProgramaX1) {
        UtilityImpCompElectronico impresion;
        try {
            
            List lstImpresionVoucher = new ArrayList();
            lstImpresionVoucher = DBPuntos.voucherContinuaBonificandoProgramaX1(pNumPedVta,pProductoAcumualdoProgramaX1);
            
            impresion = new UtilityImpCompElectronico();
            boolean rest = impresion.impresionTermica(lstImpresionVoucher, null);
            
            log.info("pNumPedVta: " + pNumPedVta);

        } catch (Exception sqlException) {
            log.error("",sqlException);

        }
    }
    
    public static String getListaProductoAcumCliente(String nroDocumento) {
        String listaProductoAcumCliente="N"; 
       BeanTarjeta tarjetaCliente =   VariablesPuntos.frmPuntos.getGanarBonificaciones(nroDocumento, getDNI_USU());
       if(tarjetaCliente!=null && !tarjetaCliente.getListaPendienteAcumular().isEmpty()){
               List lstProducto = tarjetaCliente.getListaPendienteAcumular();
               for(int i=0;i<lstProducto.size();i++){
                   String[] prod = lstProducto.get(i).toString().split(",");
                   String codProdEQ=prod[0];
                   String cantPendAcum=prod[1];
                   String datosProducto=codProdEQ.substring(0, 6)+","+codProdEQ+","+cantPendAcum+","+codProdEQ.substring(8, 13);
                   if(!datosProducto.equals("") && listaProductoAcumCliente.startsWith("N")){
                           listaProductoAcumCliente="";
                       }
                   
                   if(listaProductoAcumCliente.length()>0){
                       listaProductoAcumCliente = listaProductoAcumCliente + "@";
                   }
                   listaProductoAcumCliente = listaProductoAcumCliente + datosProducto;
               }
           
           }
        return listaProductoAcumCliente;
    }
    
    //Devuelve true si la cadena que llega es un numero decimal, false en caso contrario
     public static boolean esDecimal(String cad)
     {
     try
     {
       Double.parseDouble(cad);
       return true;
     }
     catch(NumberFormatException nfe)
     {
       return false;
     }
     }

    public static String pTimeOutOrbis(){
        String vValor="";
        
        try {
            vValor = DBPuntos.getTimeOutOrbis();
        } catch (Exception e) {
            // TODO: Add catch code
            log.error("",e);
            //Si ocurre cualquier error se tiene 1 Segundo x Defecto.
            vValor="1000";
        }
        
        return vValor.trim();
    }
    
  
        
  
    /**
     * Insertar determinados procesos del fv para puntos.
     * @author ASOSA
     * @since 27.02.2015
     *  
     * @param operacionDTO
     * @throws SQLException
     */
    public static void insertarLogOperacion(BeanOperacion operacionDTO) {
        try {
            DBPuntos.insertarLogOperacion(operacionDTO);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public static void fideliza_cliente(String pCodCampanaCupon,
                           boolean vTeclaF12, 
                           boolean isPasoTarjeta,
                           Frame   myFrame,
                           JDialog jDialog) {
        ProgramaXmas1Facade programaXmas1Facade = new ProgramaXmas1Facade();
        log.debug("Funcion F12");
        AuxiliarFidelizacion.funcionF12(pCodCampanaCupon, 
                                        //txtProducto, 
                                        new JTextField(),
                                        myFrame, 
                                        //lblMensajeCampaña,
                                        //lblCliente, 
                                        new JLabel(),
                                        new JLabel(),
                                        //this, 
                                        jDialog,
                                        "L", 
                                        //lblDNI_SIN_COMISION,
                                        new JLabel(),
                                        vTeclaF12, 
                                        isPasoTarjeta, 
                                        false);
        
        if(isActivoFuncionalidad()) //AOVIEDO 13/07/2017 SE AGREGA LA VALIDACION PARA LOCALES TICO
            programaXmas1Facade.reemplazarListaProgramasPorProductos();
    }
    
    /**
     * Convierte la cantidad a fraccion.
     * @author RPASCACIO
     * @since 15.06.2017
     
     */
    public static String cantidadFraccion(String pCodProd,String pCodEqui,String pValFrac, String pCant) {
       String cantidad="";
        try {
            log.info("Antes de getConversionFraccion >> "+"cantidadFraccion");
           cantidad= DBPuntos.getConversionFraccion(pCodProd,pCodEqui,pValFrac,pCant);
        } catch (Exception e) {
            FarmaUtility.showMessage(new JDialog(),"Ocurrió en obtener la cantidad para acumular del programa \n"+
                                                   pCodProd + " - " + pCodEqui+"\n"+
                                                   "Cantidad "+pCant+"\n"+
                                                   "Fracción "+pValFrac+" \n"+
                                                   "Comunicarse con Mesa de Ayuda.", 
                                                null);
            cantidad = "0";
            log.error("", e);
        }
        return cantidad;
    }
    public static boolean verificaEmitirCuponCumpleanios(String pDNI_Cliente, String fecNacimiento, int anio) {
        int cantRegistros=0;
        try{
            String fechaNac="";
            int index1=fecNacimiento.indexOf("/");
            int index2=fecNacimiento.lastIndexOf("/");
            String diaNac=fecNacimiento.substring(0,index1);
            String mesNac=fecNacimiento.substring(index1+1,index2);
            String anioNac=fecNacimiento.substring(index2+1);
            if(Integer.parseInt(diaNac)<10){
                diaNac="0"+Integer.parseInt(diaNac);}
            if(Integer.parseInt(mesNac)<10){
                mesNac="0"+Integer.parseInt(mesNac);}
            anioNac=""+(Integer.parseInt(anioNac.trim())+1900);
            fechaNac=diaNac+"/"+mesNac+"/"+anioNac;
            
            ArrayList parametros = new ArrayList();
            parametros.add(pDNI_Cliente);
            parametros.add(fechaNac);
            parametros.add(anio);
            parametros.add(FarmaVariables.vCodLocal);
            log.info("Consulta cupon cumpleanos: "+"FARMA_PUNTOS.RECUP_REG_CUPON_CUMPLE(?,?,?,?) "+ parametros);
            cantRegistros=FarmaDBUtility.executeSQLStoredProcedureInt("FARMA_PUNTOS.RECUP_REG_CUPON_CUMPLE(?,?,?,?)", 
                                                                      parametros);
            log.info("VALOR RECUPERADO: "+cantRegistros);
            if(cantRegistros==0){
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al recuperar el registro de cupon cumpleaños: \nCAUSA: "
                               +e.getCause()+"\nMSJ ERROR:"+e.getMessage());
        }
        return false;
    }
    /**
     * @author AOVIEDO
     * @param pParent
     * @param pJDialog
     * @param nroTarjeta
     * @param nroDocumento
     * @return
     */
    public static boolean impresionVoucherAfiliacionTICO(Frame pParent, JDialog pJDialog, String nroTarjeta, String nroDocumento){
        boolean imprime = true;
        int cantidad = 0;
        boolean isAceptoAfiliado = false;
        // valida cantidad de tarjetas asociadas al nro de documento del cliente.
        /*try{
            cantidad = cantidadTieneTarjetasAdicionales(pParent, pJDialog, nroDocumento, false, false);
            //Afiliado afiliado = consultarAfiliado(pJDialog, nroDocumento);
            if(cantidad == 0){
                imprime = true;
            }else{
                if(cantidad == -1){
                    imprime = !DBPuntos.isValidaClienteAfiliadoLocal(nroDocumento);
                    //imprime = !DBPuntos.isTarjetaAsociada(nroDocumento, nroTarjeta);
                }
            }
        }catch(Exception ex){
            log.error(" ", ex);
        }*/
        // si cliente no tiene ningun tarjeta asociada se imprime voucher
        if(imprime){
            //KMONCADA 25.06.2015 SE REALIZA IMPRESION DE VOUCHER
            FacadePuntos facadePtos = new FacadePuntos();
            facadePtos.impresionVoucherAfiliacion(nroDocumento);
            //UtilityFidelizacion.imprimirVoucherFid(nroDocumento);
            isAceptoAfiliado =  JConfirmDialog.rptaConfirmDialog(pJDialog, "Recoger voucher de verificación de datos.\n" +
                                                                           "¿Cliente firmó Voucher?");
            if(isAceptoAfiliado){
                //ERIOS 22.06.2015 Se registra inscripcion por turno
                FacadeLealtad facadeLealtad = new FacadeLealtad();
                facadeLealtad.registrarInscripcionTurno(VariablesCaja.vSecMovCaja, nroTarjeta);
                VariablesFidelizacion.vIsPrimeraCompraFidelizadoNuevo = true;
            }else{
                //KMONCADA 30.06.2015 SE BORRA LOS REGISTROS
                VariablesFidelizacion.limpiaVariables();
                FacadeLealtad facadeLealtad = new FacadeLealtad();
                facadeLealtad.rechazarIncripcionPuntos(nroDocumento,VariablesCaja.vSecMovCaja, nroTarjeta);
                VariablesFidelizacion.vIsPrimeraCompraFidelizadoNuevo = false;
            }
        }else{
            isAceptoAfiliado = true;
        }
        
        // KMONCADA 07.08.2015 SE REGISTRARA LA ENTREGA DE LA TARJETA AL CLIENTE COMO MOVIMIENTO DE KARDEX
        if(isAceptoAfiliado){
            log.info("REGISTRARA EL MOVIENTO EN EL KARDEX DE LA ENTREGA DE LA TARJETA: NRO TARJETA --> "+nroTarjeta+"\nDOCUMENTO --> "+nroDocumento);
            FacadePuntos facadePtos = new FacadePuntos();
            facadePtos.registroEntregaTarjetaPtos(nroTarjeta, nroDocumento);
        }
        return isAceptoAfiliado;
    }

    public static String recuperaMensajeCumpleanios() {
        try {
            ArrayList parametros = new ArrayList();
            log.info("recupera mensaje cumpleanos: "+"FARMA_PUNTOS.RECUP_MSJ_CUMPLE"+ parametros);
            return FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_PUNTOS.RECUP_MSJ_CUMPLE",
                                                                  parametros);
        } catch (SQLException e) {
            log.info("Error al recuperar el mensaje de cumpleaños");
            e.printStackTrace();
            return "DESCUENTO EXCLUSIVO PARA TI~~FELICIDADES ESTE MES CUMPLES UN AÑO MAS~Y MIFARMA TE PREMIA";
        }
    }
}
