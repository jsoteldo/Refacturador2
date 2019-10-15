package mifarma.ptoventa.caja.UtilNuevoCobro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;

import mifarma.common.FarmaConnection;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.UtilityCPE;

import mifarma.ptoventa.caja.daoNuevoCobro.BDNuevoCobro;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityNuevoCobro {
    private static final Logger log = LoggerFactory.getLogger(UtilityNuevoCobro.class);
    
    public UtilityNuevoCobro() {
        super();
    }
    
    public static String verifica_NuevoProcesoCobroActivo() {
        String inidcador;
        try {
            inidcador = BDNuevoCobro.verifica_NuevoProcesoCobroActivo();
        } catch (SQLException e) {
            inidcador="N";
        }
        return inidcador;
    }
    
    public static boolean validarDatos_Cobro(JDialog pJDialog, String pNumPedido,Object tblFormasPago) {
        if (!existeStockPedido(pJDialog, pNumPedido)){
            return false;}
        if (!validacionesCobroPedido(false, pJDialog, tblFormasPago)){
            return false;}
        if (!UtilityCaja.validaCajaAbierta(pJDialog)){
            return false;}
        return true;
    }

    private static boolean existeStockPedido(JDialog jDialog, String pNumPedido) {
        boolean pRes = false;
        String pCadena = "N";
        String pCadenaInsumo = "N";
        if (VariablesVentas.vEsPedidoDelivery &&
            !(VariablesCaja.vIndDeliveryAutomatico.trim().equalsIgnoreCase("S"))) {
            pRes = true;
        } else {
            try {
                pCadena = BDNuevoCobro.getPermiteCobrarPedido(pNumPedido);
                pCadenaInsumo = BDNuevoCobro.getPermiteCobrarInsumosPedido(pNumPedido);
            } catch (SQLException e) {
                pCadena = "N";
                pCadenaInsumo = "N";
                log.error("", e);
            }

            if (pCadena != null && "S".equalsIgnoreCase(pCadena.trim())) {
                pRes = true;
                
                if (pCadenaInsumo != null && "S".equalsIgnoreCase(pCadenaInsumo.trim())) {
                    pRes = true;
                }else{
                    pRes = false;
                    FarmaUtility.liberarTransaccion();
                    FarmaUtility.showMessage(jDialog, "("+pCadenaInsumo+") No puede cobrar el pedido\n" +
                            "Porque no hay stock suficiente para poder generarlo ó\n" +
                            "Existe un Problema en la fracción de productos.", null);
                }
            } else {
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(jDialog, "("+pCadena+") No puede cobrar el pedido\n" +
                        "Porque no hay stock suficiente para poder generarlo ó\n" +
                        "Existe un Problema en la fracción de productos.", null);
            }
        }
        return pRes;
    }

    private static boolean validacionesCobroPedido(boolean validaTotalCubierto, JDialog pJDialog,
                                                  Object tblFormasPago) {
        try { //valida que haya sido seleccionado un pedido.
            if (FarmaConstants.INDICADOR_N.equalsIgnoreCase(VariablesCaja.vIndPedidoSeleccionado))
                return false;

            //validando que se haya cubierta el total del monto del pedido
            if (validaTotalCubierto && !VariablesCaja.vIndTotalPedidoCubierto) {
                FarmaUtility.showMessage(pJDialog, "El Pedido tiene saldo pendiente de cobro.Verifique!!!",
                                         tblFormasPago);
                return false;
            }

            //validando que el pedido este en esta PENDIENTE DE COBRO
            if (!UtilityCaja.verificaEstadoPedidoNuevoCobro(pJDialog, VariablesCaja.vNumPedVta,
                                                            ConstantsCaja.ESTADO_PENDIENTE, null)) {
                return false;
            }

            //Validacion para cajeros
            if (!UtilityCaja.existeCajaUsuarioImpresora(pJDialog, null)) { //cerrarVentana(false);
                return false;
            }

            /*
            * Validacion de Fecha actual del sistema contra
            * la fecha del cajero que cobrara
            */
            if (!UtilityCaja.validaFechaMovimientoCaja(pJDialog, tblFormasPago)) {
                FarmaUtility.liberarTransaccion();
                return false;
            }

            //valida que exista RUC si es FACTURA
            if (ConstantsVentas.TIPO_COMP_FACTURA.equalsIgnoreCase(VariablesVentas.vTip_Comp_Ped) &&
                (null == VariablesConvenioBTLMF.vCodConvenio ||
                 VariablesConvenioBTLMF.vCodConvenio.trim().length() == 0)) {
                if (VariablesVentas.vRuc_Cli_Ped != null && "".equalsIgnoreCase(VariablesVentas.vRuc_Cli_Ped.trim())) {
                    FarmaUtility.liberarTransaccion();
                    //FarmaUtility.showMessage(pJDialog, "Debe ingresar el numero de RUC!!!", tblFormasPago);
                    FarmaUtility.showMessage(pJDialog, "No se ha ingresado el RUC del cliente correctamente", tblFormasPago);    
                    return false;
                }
            }

            if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(pJDialog, null) &&
                VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF) {
                if (VariablesConvenioBTLMF.vFlgValidaLincreBenef.equals("1")) {
                    if (FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar) >
                        FarmaUtility.getDecimalNumber(VariablesConvenioBTLMF.vMontoSaldo)) {
                        FarmaUtility.showMessage(pJDialog, "Saldo insuficiente del Benificiario!!!", tblFormasPago);
                        return false;
                    }
                }
            } 
            /*
            * Bloqueo de caja
            */
            return true;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    public static ArrayList<String> recuperaDatosClientePedido() {
        ArrayList<String> datosClientePedido=new ArrayList<String>();
        datosClientePedido.add(FarmaVariables.vCodGrupoCia);
        datosClientePedido.add(FarmaVariables.vCodLocal);
        datosClientePedido.add(VariablesCaja.vNumPedVta);
        datosClientePedido.add(VariablesVentas.vCod_Cli_Local);
        datosClientePedido.add(VariablesVentas.vNom_Cli_Ped);
        datosClientePedido.add(VariablesVentas.vDir_Cli_Ped);
        datosClientePedido.add(VariablesVentas.vRuc_Cli_Ped);
        datosClientePedido.add(FarmaVariables.vIdUsu);
        return datosClientePedido;
    }

    public static ArrayList recuperaFormaPagoPedido(String pCodFormaPago, String pImPago, String pTipMoneda,
                                            String pTipoCambio, String pVuelto, String pImTotalPago, String pNumTarj,
                                            String pFecVencTarj, String pNomCliTarj, String pCantCupon,
                                            String pDNITarj, String pCodAutori, String pCodLote,
                                            String pNumPedVtaNCR) {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodFormaPago);
        parametros.add(VariablesCaja.vNumPedVta);
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pImPago))); //
        parametros.add(pTipMoneda);
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pTipoCambio)));
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pVuelto)));
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pImTotalPago))); //se pagacon esta cantdad
        parametros.add(getCadenaVacio(pNumTarj));
        parametros.add(" "); //pFecVencTarj
        parametros.add(getCadenaVacio(pNomCliTarj));
        parametros.add(new Integer(pCantCupon));
        parametros.add(FarmaVariables.vIdUsu);
        
        
        parametros.add(getCadenaVacio(pDNITarj));
        parametros.add(getCadenaVacio(pCodAutori));
        parametros.add(getCadenaVacio(pCodLote));
        parametros.add(getCadenaVacio(pNumPedVtaNCR));
        return parametros;
    }
    
    public static String getCadenaVacio(String pCadena){
        if(pCadena.trim().length()>0)
         return pCadena;
        else
        return " ";
    }
    
    public static ArrayList recupFormaPagoPedido_P1(String pCodFormaPago, String pImPago, String pTipMoneda,
                                            String pTipoCambio, String pVuelto, String pImTotalPago, String pNumTarj,
                                            String pFecVencTarj, String pNomCliTarj, String pCantCupon,
                                            String pDNITarj, String pCodAutori, String pCodLote,
                                            String pNumPedVtaNCR) {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodFormaPago);
        parametros.add(VariablesCaja.vNumPedVta);
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pImPago))); //
        parametros.add(pTipMoneda);
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pTipoCambio)));
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pVuelto)));
        parametros.add(new Double(FarmaUtility.getDecimalNumber(pImTotalPago))); //se pagacon esta cantdad
        parametros.add("NumTarjeta");
        parametros.add("FecVencTarj"); //pFecVencTarj
        parametros.add("NomClieTarj");
        parametros.add("CanCupon");
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add("DNITarj");
        parametros.add("COdAotoriz");
        parametros.add(pCodLote);
        parametros.add("NumPedVtaNCR");
        return parametros;
    }
    
    public static ArrayList<String> datosCobroPedido_Concenio() {
        log.debug("VariablesVentas.vTipoPedido : " + VariablesVentas.vTipoPedido);
        ArrayList<String> parametros = new ArrayList<String>();

        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vNuSecUsu);
        parametros.add(VariablesCaja.vNumPedVta);
        parametros.add(VariablesCaja.vSecMovCaja);
        parametros.add(FarmaConstants.COD_NUMERA_SEC_COMP_PAGO);
        if (VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_MESON))
            parametros.add(ConstantsPtoVenta.MOT_KARDEX_VENTA_NORMAL);
        else if (VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_DELIVERY))
            parametros.add(ConstantsPtoVenta.MOT_KARDEX_VENTA_DELIVERY);
        else if (VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL))
            parametros.add(ConstantsPtoVenta.MOT_KARDEX_VENTA_ESPECIAL);
        parametros.add(ConstantsPtoVenta.TIP_DOC_KARDEX_VENTA);
        parametros.add(FarmaConstants.COD_NUMERA_SEC_KARDEX);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(VariablesConvenio.vPorcCoPago);
        //parametros.add(VariablesCaja.vDescripcionDetalleFormasPago);
        parametros.add("");
        //parametros.add(UtilityEposTrx.validacionEmiteElectronico() == true ? "1" : "0"); //LTAVARA 27.08.2014 AGREGAR EL FLAG DE PROCESO ELECTRONICO
        
        //parametros.add(UtilityCPE.isActivoFuncionalidad() == true ? "1" : "0"); //LTAVARA 27.08.2014 AGREGAR EL FLAG DE PROCESO ELECTRONICO
        if(UtilityCPE.isEstaContigenciaEPOS())
            parametros.add("0");
        else
            parametros.add("1");
        return parametros;
    }
    
    private static ARRAY transformaLista_Array(Connection conn, ArrayList<String> arrayList) throws SQLException {
        conn = FarmaConnection.getConnection();
        ArrayDescriptor desc = ArrayDescriptor.createDescriptor("PTOVENTA.VARCHAR2_TABLE", conn);
        ARRAY array = new ARRAY(desc, conn, arrayList.toArray());
        return array;
    }
    
    public static String cobraPedido_Local(ArrayList<String> datosClientePedido_up,
                                           ArrayList<ArrayList> datosDetallePagoPedido_in,
                                           ArrayList<String> datosCobroPedido_in,
                                           ArrayList<String> listCuponesUsadosPedido, String pNumPedVta,
                                           String tipoCobro) throws SQLException {
        String msj = "ERROR";
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        Connection conn;

        conn = FarmaConnection.getConnection();
        log.info("UTILITY_NUEVO_COBRE -> Conexion establecida");
        log.info("Conexion establecida");
        ArrayList<String> listaDetallePedido = new ArrayList<String>();
        for (int i = 0; i < datosDetallePagoPedido_in.size(); i++) {
            String detalle = datosDetallePagoPedido_in.get(i).get(0).toString();
            for (int j = 1; j < datosDetallePagoPedido_in.get(i).size(); j++) {
                detalle = detalle + "Ã" + datosDetallePagoPedido_in.get(i).get(j).toString();
            }
            listaDetallePedido.add(detalle);
        }
        log.info("UilityNuevoCobro ========================================================");
        log.info("---------------IMPRENSION DE VARIABLES DE COBRO----------------");
        log.info("==> datosClientePedido_up: tam = " + datosClientePedido_up.size());
        for (int i = 0; i < datosClientePedido_up.size(); i++) {
            log.info("[" + i + "] " + datosClientePedido_up.get(i));
        }
        log.info("==> listaDetallePedido: tam = " + listaDetallePedido.size());
        for (int i = 0; i < listaDetallePedido.size(); i++) {
            String[] listaRespuesta = null;
            listaRespuesta = listaDetallePedido.get(i).toString().split("Ã");
            for (int j = 0; j < listaRespuesta.length; j++) {
                log.info("[" + j + "] " + listaRespuesta[j].toString());
            }
        }
        log.info("==> datosCobroPedido_in: tam = " + datosCobroPedido_in.size());
        for (int i = 0; i < datosCobroPedido_in.size(); i++) {
            log.info("[" + i + "] " + datosCobroPedido_in.get(i));
        }
        log.info("==> listCuponesUsadosPedido: tam = " + listCuponesUsadosPedido.size());
        for (int i = 0; i < listCuponesUsadosPedido.size(); i++) {
            log.info("[" + i + "] " + listCuponesUsadosPedido.get(i));
        }
        log.info("--------------------------------------------------------------");

        mapParametros.put(getMayuscula("a_Dato1"), FarmaVariables.vCodGrupoCia);
        mapParametros.put(getMayuscula("a_Dato2"), FarmaVariables.vCodLocal);
        mapParametros.put(getMayuscula("a_Dato3"), FarmaVariables.vIdUsu);
        mapParametros.put(getMayuscula("a_Dato4"), pNumPedVta);
        mapParametros.put(getMayuscula("a_Dato5"), tipoCobro);

        ARRAY arrayClientePedido = transformaLista_Array(conn, datosClientePedido_up);
        mapParametros.put(getMayuscula("a_Dato6"), arrayClientePedido);

        ARRAY arrayDetallePedido = transformaLista_Array(conn, listaDetallePedido);
        mapParametros.put(getMayuscula("a_Dato7"), arrayDetallePedido);

        ARRAY arrayCobroPedido = transformaLista_Array(conn, datosCobroPedido_in);
        mapParametros.put(getMayuscula("a_Dato8"), arrayCobroPedido);

        ARRAY arrayCuponesUsados = transformaLista_Array(conn, listCuponesUsadosPedido);
        mapParametros.put(getMayuscula("a_Dato9"), arrayCuponesUsados);

        String procedure = "PTOVENTA.PTOVENTA_PROCESA_COBRO_GRAL.PROCESA_COBRO_LOCAL(?,?,?,?,?,?,?,?,?)";
        msj = BDNuevoCobro.executeSQLStoredProcedureMap_enviarArray(procedure, mapParametros);
        
        return msj;
    }
    
    public static String getMayuscula(String pCadena) {
        return pCadena.toUpperCase();
    }
    
    public static String enviarArreglo(ArrayList arrayDatos) {
        String msj="Error";
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        
        Connection conn;
        try {
            conn = FarmaConnection.getConnection();
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("PTOVENTA.VARCHAR2_TABLE", conn);
            
            //************************************ARRAY*******************************************//
            ARRAY datos = new ARRAY(desc, conn, arrayDatos.toArray());
            
            mapParametros.put(getMayuscula("a_Datos"), datos);
            String procedure="PTOVENTA.PTOVENTA_PROCESA_COBRO_GRAL.PROCESA_ARREGLO(?)";
            msj=BDNuevoCobro.executeSQLStoredProcedureMap_enviarArray(procedure, mapParametros);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msj;
    }

    public static ArrayList<String> recuperaCuponesUsados(String pNumPedVta, ArrayList listaCuponesUsados) {
        ArrayList<String> lista = new ArrayList<String>();
        String vCodCupon = "";
        int COL_COD_CUPON = 0;
        if (listaCuponesUsados != null && listaCuponesUsados.size() > 0) {
            // SE LISTA DATOS DEL CUPON
            for (int i = 0; i < listaCuponesUsados.size(); i++) {
                vCodCupon = FarmaUtility.getValueFieldArrayList(listaCuponesUsados, i, COL_COD_CUPON);
                lista.add(vCodCupon);
            }
        }
        return lista;
    }

    public static ArrayList getCampAutomaticasPedido(String vNumPedVta) {
        ArrayList listaCampAutomaticas = new ArrayList();
        try {
            listaCampAutomaticas = DBCaja.getListaCampAutomaticasVta(vNumPedVta);
            if (listaCampAutomaticas != null && listaCampAutomaticas.size() > 0) {
                listaCampAutomaticas = (ArrayList)listaCampAutomaticas.get(0);
            }
            log.debug("listaCampAutomaticas listaCampAutomaticas ===> " + listaCampAutomaticas);
        } catch (Exception e) {
            log.error("",e);
        }
        return listaCampAutomaticas;
    }

    public static ArrayList CampLimitadasUsadosDeLocalXCliente(String dniCliente) {
        ArrayList listaCampLimitUsadosLocal = new ArrayList();
        try {
            listaCampLimitUsadosLocal = DBCaja.getListaCampUsadosLocalXCliente(dniCliente);
            if (listaCampLimitUsadosLocal != null && listaCampLimitUsadosLocal.size() > 0) {
                listaCampLimitUsadosLocal = (ArrayList)listaCampLimitUsadosLocal.get(0);
            }
            log.debug("listaCampLimitUsadosLocal listaCampLimitUsadosLocal ===> " + listaCampLimitUsadosLocal);
        } catch (Exception e) {
            log.error("error al obtener las campañas limitadas ya usados por cliente en LOCAL : ", e);
        }
        return listaCampLimitUsadosLocal;
    }

    public static ArrayList<String> datosCobroPedido_gral(String pTipComp, String vPermiteCampaña, String Dni) throws SQLException {
        log.debug("VariablesVentas.vTipoPedido : " + VariablesVentas.vTipoPedido);
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesCaja.vNumPedVta);
        parametros.add(VariablesCaja.vSecMovCaja);
        parametros.add(FarmaConstants.COD_NUMERA_SEC_COMP_PAGO);
        parametros.add(pTipComp);
        if (VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_MESON))
            parametros.add(ConstantsPtoVenta.MOT_KARDEX_VENTA_NORMAL);
        else if (VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_DELIVERY))
            parametros.add(ConstantsPtoVenta.MOT_KARDEX_VENTA_DELIVERY);
        else if (VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL))
            parametros.add(ConstantsPtoVenta.MOT_KARDEX_VENTA_ESPECIAL);
        parametros.add(ConstantsPtoVenta.TIP_DOC_KARDEX_VENTA);
        parametros.add(FarmaConstants.COD_NUMERA_SEC_KARDEX);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(VariablesCaja.vDescripcionDetalleFormasPago);
        parametros.add(vPermiteCampaña); //jcortez
        parametros.add(Dni); //jcortez 18.08.09
        if(UtilityCPE.isEstaContigenciaEPOS())
            parametros.add("S");
        else
            parametros.add("N");
        //log.debug("PTOVENTA_CAJ.CAJ_COBRA_PEDIDO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)" + parametros);
        return parametros;
    }

    public static boolean consultaMesCumpleanio() {
        System.out.println("------------------------>"+VariablesFidelizacion.vFecNacimiento+" : VariablesFidelizacion.vFecNacimiento");
        String fecNac=VariablesFidelizacion.vFecNacimiento;
        System.out.println("========================>"+fecNac+" : fecNac");
        
        int mesActual=Calendar.getInstance().getTime().getMonth()+1;
        String mesNac=fecNac.substring(fecNac.indexOf("/")+1, fecNac.lastIndexOf("/"));
        System.out.println("==> mesActual: "+mesActual+" mesNac: "+mesNac);
        if(mesActual==Integer.parseInt(mesNac)){
            boolean emiteCupon=UtilityPuntos.verificaEmitirCuponCumpleanios(VariablesFidelizacion.vDniCliente.trim(),
                                                                            VariablesFidelizacion.vFecNacimiento.trim(),
                                                                            (Calendar.getInstance().getTime().getYear()+1900));    
            if(emiteCupon){
                //Logica para emitir el cupon cumpleaños
                String rpta="";
                try {
                    rpta = DBCaja.generaCuponCumpleanios();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("Error al verificar la fecha de cumpleaños del cliente");
                }
            }
        }
        return false;
    }
}
