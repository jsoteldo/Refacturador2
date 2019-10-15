package mifarma.ptoventa.fidelizacion.reference;


import com.gs.mifarma.worker.JDialogProgress;

import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.awt.Frame;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.caja.DlgDatosTipoTarjetaCampanaR;
import mifarma.ptoventa.caja.DlgDatosTipoTarjetaCampanaR.JPanelImagenTransparente;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.fidelizacion.DlgBusquedaMedicoCamp;
import mifarma.ptoventa.fidelizacion.DlgFidelizacionBuscarTarjetas;
import mifarma.ptoventa.hilos.Fidelizacion;
import mifarma.ptoventa.puntos.DlgMensajeBienvenida;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AuxiliarFidelizacion {

    private static String DLG_RESUMEN_PEDIDO = "R";
    private static String DLG_LISTA_PRODUCTOS = "L";

    private static final Logger log = LoggerFactory.getLogger(AuxiliarFidelizacion.class);

    public AuxiliarFidelizacion() {
    }

    public static void funcionF12(String pCodCampanaCupon, JTextField txtObj, Frame myFrame, JLabel lblObj,
                                  JLabel lblCli, JDialog dlgObj, String pTipo, JLabel lblDniSinComision,
                                  boolean vTeclaF12, boolean isPasoTarjeta, boolean isTarjetaCampania
                                  ) {
        log.debug("Funcion F12");
        VariablesFidelizacion.tmpCodCampanaCupon = pCodCampanaCupon;
        
        if(!UtilityPuntos.isActivoFuncionalidad()){
            if (VariablesVentas.vEsPedidoConvenio ||
                (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1)) {
                    FarmaUtility.showMessage(dlgObj, "No puede agregar una tarjeta a un " + "pedido por convenio.", txtObj);
                    return;
            }
        }
        //KMONCADA 08.07.2015
        //mostrarBuscarTarjetaPorDNI(myFrame, lblObj, lblCli, dlgObj, pTipo, txtObj,vTeclaF12, isPasoTarjeta, isVtaManual);
        mostrarBuscarTarjetaPorDNI(myFrame, lblObj, lblCli, dlgObj, pTipo, txtObj,vTeclaF12, isPasoTarjeta, isTarjetaCampania);
        
        //INI AOVIEDO 22/06/2017
        VariablesFidelizacion.vCodFormaPagoCampanasR = ""; //AOVIEDO 04/07/2017
        
        if (FarmaVariables.vAceptar) {
            List lstTarjetaLogo = new ArrayList();
            Map mapaFilaEfectivo = new HashMap();
            mapaFilaEfectivo.put("NOM_TARJETA", "EFECTIVO");
            mapaFilaEfectivo.put("IMG_LOGO", "efectivo.png");
            mapaFilaEfectivo.put("IMG_AYUDA", "efectivo.png");
            mapaFilaEfectivo.put("COD_FORMA_PAGO", "E0000");
            lstTarjetaLogo.add(mapaFilaEfectivo);
            
            List lstTarjetas = DBVentas.obtenerTarjetasLogoLocal(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
            for(int i = 0; i < lstTarjetas.size(); i++){
                Map map = (Map)lstTarjetas.get(i);
                lstTarjetaLogo.add(map);
            }
            
            String tipCupon = (VariablesFidelizacion.vTipCuponCampanasR.isEmpty()) ? "G" : VariablesFidelizacion.vTipCuponCampanasR;
            DlgDatosTipoTarjetaCampanaR dlgTipoTarjeta = new DlgDatosTipoTarjetaCampanaR(myFrame, "", true, tipCupon); //AOVIEDO 22/06/2017
            if (FarmaVariables.vAceptar) {
                /*Map mapaFilaTarjeta = new HashMap();
                mapaFilaTarjeta.put("NOM_TARJETA", "OTRAS TARJETAS");
                mapaFilaTarjeta.put("IMG_LOGO", "otras_tarjetas.png");
                mapaFilaTarjeta.put("IMG_AYUDA", "otras_tarjetas.png");
                mapaFilaTarjeta.put("COD_FORMA_PAGO", "T0000");
                lstTarjetaLogo.add(mapaFilaTarjeta);*/
                dlgTipoTarjeta.setLstTarjetaLogo(lstTarjetaLogo);
                String codForPago = "";
                for(int i = 0; i == 0; i++){
                    Map map = (Map)lstTarjetaLogo.get(i);
                    codForPago = (String)map.get("COD_FORMA_PAGO");
                }
                dlgTipoTarjeta.setCodFormaPagoDlg(codForPago);
                dlgTipoTarjeta.setNumCupon(VariablesFidelizacion.vCodCuponIngresadoCampanasR);
                //INI JMONZALVE 24042019
                try {
                    if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                        dlgTipoTarjeta.setVisible(false);
                        VariablesFidelizacion.vCodFormaPagoCampanasR = "";
                        VariablesFidelizacion.vNumCuponCampanasR = 0;
                        VariablesFidelizacion.vCodCuponIngresadoCampanasR = "";
                    }else{
                        dlgTipoTarjeta.setVisible(true);//JMONZALVE 24042019 linea ya existente
                    }
                } catch (SQLException e) {
                    log.error("Error al verificar convenio por Cobro de Responsabilidad: " + e.getMessage());
                }
                //FIN JMONZALVE 24042019
            }
            
            if (FarmaVariables.vAceptar) {
                JPanelImagenTransparente pnlTarjeta = (JPanelImagenTransparente)dlgTipoTarjeta.getPanelSeleccionado();
            }
        }
        
        //log.debug("VariablesFidelizacion.vListCampañasFidelizacion ANTES DE AGREGAR LAS R: " + VariablesFidelizacion.vListCampañasFidelizacion);
        actualizarCampanasR();
        //log.debug("VariablesFidelizacion.vListCampañasFidelizacion DESPUES DE AGREGAR LAS R: " + VariablesFidelizacion.vListCampañasFidelizacion);
        //FIN AOVIEDO 22/06/2017
        if(!VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(""))
            VariablesCaja.vCodFormaPago = VariablesFidelizacion.vCodFormaPagoCampanasR;
        else
            VariablesCaja.vCodFormaPago = "";
        
        VariablesFidelizacion.tmpCodCampanaCupon = "";

        if (UtilityFidelizacion.getIndComisionnew()) {

            /// LEVANTA EL HILO DE FIDELIZACION
            /////////////////////////////////////////////////////////////////
            // INICIO dubilluz 18.05.2012
            Fidelizacion subFidelacion =
                new Fidelizacion(VariablesFidelizacion.vDniCliente, dlgObj, txtObj, lblDniSinComision);
            subFidelacion.start();
            // FIN    dubilluz 18.05.2012
            /////////////////////////////////////////////////////////////////
        }
    }
    
    private static void mostrarBuscarTarjetaPorDNI(Frame myParentFrame, JLabel lblObj, JLabel lblCli, JDialog dlgObj,
                                                   String pTipo, JTextField txtObj,boolean vTeclaF12, boolean isPasoTarjeta,
                                                   boolean isTarjetaCampania) {

        if(isPasoTarjeta){
            DlgDatosProceso a = new DlgDatosProceso(myParentFrame," ",true);
            a.mostrar();
        }
        
        DlgFidelizacionBuscarTarjetas dlgBuscar = new DlgFidelizacionBuscarTarjetas(myParentFrame, "", true,vTeclaF12);
        dlgBuscar.setIsTarjetaCampania(isPasoTarjeta);
        if(vTeclaF12)
            dlgBuscar.setVisible(true);
        else{
            //porque fue x Lista de Productos o Resumen de Pedido
            dlgBuscar.setVisible(false);
            dlgBuscar.procesaFuncionalidadFid();
            dlgBuscar.procesaFuncionPuntos();
        }
        
        
        log.debug("vv DIEGO:" + FarmaVariables.vAceptar);
        log.debug("dat_1:" + VariablesFidelizacion.vDataCliente);
        log.debug(" VariablesFidelizacion.vNomCliente_1:" + VariablesFidelizacion.vNomCliente);
        log.debug(" VariablesFidelizacion.vDniCliente_1:" + VariablesFidelizacion.vDniCliente);
        if (FarmaVariables.vAceptar) {
            log.debug("en aceptar");
            log.debug("dat:" + VariablesFidelizacion.vDataCliente);
            ArrayList array = (ArrayList)VariablesFidelizacion.vDataCliente.get(0);
            log.debug("des 1");
            //JCALLO 02.10.2008
            //VariablesFidelizacion.vDniCliente = String.valueOf(array.get(0));
            //seteando los datos del cliente en las variables con los datos del array
            UtilityFidelizacion.setVariablesDatos(array);
            log.debug("des 2");
            /*FarmaUtility.showMessage(this,
                                     "Cliente encontrado con DNI " + VariablesFidelizacion.vDniCliente,
                                     null);*/
            log.debug(" VariablesFidelizacion.vNomCliente:" + VariablesFidelizacion.vNomCliente);
            log.debug(" VariablesFidelizacion.vDniCliente:" + VariablesFidelizacion.vDniCliente);
            log.debug(" VariablesFidelizacion.vFecNacimiento:" + VariablesFidelizacion.vFecNacimiento);
            
            if(UtilityPuntos.isActivoFuncionalidad()){
                // KMONCADA 09.02.2015 CANCELA LA TRANSACCION DE VENTA CON PUNTOS
                //LTAVARA VALIDA 2017.04.03
                BeanTarjeta tarjetaPuntos = null;
                    if(VariablesPuntos.frmPuntos!= null){
                          tarjetaPuntos=  VariablesPuntos.frmPuntos.getBeanTarjeta();
                        }
                   
                if(tarjetaPuntos != null){
                    boolean deslizaTarjeta = tarjetaPuntos.getDeslizaTarjeta();
                    String nroTarjeta = tarjetaPuntos.getNumeroTarjeta();
                    txtObj.setText(nroTarjeta);
                    // SOLO SE INGRESA ACA CUANDO ES UNA TARJETA O REGISTRO NUEVO EN ORBIS
                    if(WSClientConstans.EXITO.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion()) &&
                       WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                        //REALIZAR CONSULTA EN EL CASO DE QUE LA TRANSACCION ANTERIOR DE REGISTRO O CONSULTA FUE EXITO
                        //ADEMAS DE QUE EL ESTADO DE LA TARJETA SE HA INACTIVA DE NUEVO REGISTRO
                        UtilityPuntos.consultarTarjetaOrbis(myParentFrame, dlgObj, txtObj, deslizaTarjeta, false);
                        //INI ASOSA - 22/04/2015 - 
                        //log.info("getAfiliado().getDni(): " + VariablesFidelizacion.vDniCliente);
                        //UtilityFidelizacion.imprimirVoucherFid(VariablesFidelizacion.vDniCliente);
                        //UtilityFidelizacion.imprimirVoucherFid(tarjetaPuntos.getDni(), tarjetaPuntos.getNumeroTarjeta());
                        //FarmaUtility.showMessage(dlgObj, "Recoger voucher de verificación de datos", null);
                        //FIN ASOSA - 22/04/2015 - 
                    }else{
                        if(WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                            try{
                                String nroDocumento = DBFidelizacion.getDniClienteFidelizado(nroTarjeta);
                                if(nroDocumento.trim().length()>0){
                                    tarjetaPuntos.setDni(nroDocumento);
                                }
                            }catch(Exception ex){
                                log.error("ERROR EN FIDELIZACION CON TARJETAS ORBIS", ex);                                
                            }
                        }
                    }
                    txtObj.setText("");
                }
            }
            
            if(VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                /*** INICIO ARAVELLO 10/10/2019 ***///Comentado
//                DlgMensajeBienvenida.showMensajeBienvenida(myParentFrame);
                /*** FIN    ARAVELLO 10/10/2019 ***/
            }else{
                FarmaUtility.showMessage(dlgObj, "Bienvenido \n" +
                    VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente + " " +
                    VariablesFidelizacion.vApeMatCliente + "\n" +
                    "DNI: " + VariablesFidelizacion.vDniCliente, null);
            }
            
            //dubilluz 19.07.2011 - inicio
            if (VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim().length() > 0) {
                UtilityFidelizacion.grabaTarjetaUnica(VariablesFidelizacion.tmp_NumTarjeta_unica_Campana.trim(),
                                                      VariablesFidelizacion.vDniCliente);
                VariablesFidelizacion.vNumTarjeta = VariablesFidelizacion.tmp_NumTarjeta_unica_Campana;
            }
            //dubilluz 19.07.2011 - fin
            //jcallo 02.10.2008
            String msjPto = "";
            if(VariablesPuntos.frmPuntos != null && VariablesPuntos.frmPuntos.getBeanTarjeta() != null && 
               VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados()!= null){
                msjPto = FarmaUtility.formatNumber(VariablesPuntos.frmPuntos.getBeanTarjeta().getPuntosTotalAcumulados())+" - ";
            }
            boolean mostrarNombreClientePtos = true;
            if (VariablesConvenioBTLMF.vCodConvenio != null ){
                if(VariablesConvenioBTLMF.vCodConvenio.trim().length() > 1){
                    mostrarNombreClientePtos = false; 
                }
            }
            if(mostrarNombreClientePtos){
                lblCli.setText(msjPto + VariablesFidelizacion.vNomCliente + " " + VariablesFidelizacion.vApePatCliente + " " +
                           VariablesFidelizacion.vApeMatCliente);
            }
            
            //fin jcallo 02.10.2008
            //DAUBILLUZ -- Filtra los DNI anulados
            //25.05.2009
            VariablesFidelizacion.vDNI_Anulado = UtilityFidelizacion.isDniValido(VariablesFidelizacion.vDniCliente);
            VariablesFidelizacion.vAhorroDNI_x_Periodo =
                    UtilityFidelizacion.getAhorroDNIxPeriodoActual(VariablesFidelizacion.vDniCliente,
                                                                   VariablesFidelizacion.vNumTarjeta);
            VariablesFidelizacion.vMaximoAhorroDNIxPeriodo =
                    UtilityFidelizacion.getMaximoAhorroDnixPeriodo(VariablesFidelizacion.vDniCliente,
                                                                   VariablesFidelizacion.vNumTarjeta);
            log.info("Variable de DNI_ANULADO: " + VariablesFidelizacion.vDNI_Anulado);
            log.info("Variable de vAhorroDNI_x_Periodo: " + VariablesFidelizacion.vAhorroDNI_x_Periodo);
            log.info("Variable de vMaximoAhorroDNIxPeriodo: " + VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
            setMensajeDNIFidelizado(lblObj, pTipo, txtObj, dlgObj);
            if (VariablesFidelizacion.vDNI_Anulado) {
                if (VariablesFidelizacion.vNumTarjeta.trim().length() > 0)
                    UtilityFidelizacion.operaCampañasFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);

                //cargando las campañas automaticas limitadas en cantidad de usos desde matriz
                log.debug("**************************************");
                //VariablesFidelizacion.vIndConexion = FarmaUtility.getIndLineaOnLine(FarmaConstants.CONECTION_MATRIZ, FarmaConstants.INDICADOR_N);
                VariablesFidelizacion.vIndConexion = FarmaConstants.INDICADOR_N;
                log.debug("**************************************");
                //if(VariablesFidelizacion.vIndConexion.equals(FarmaConstants.INDICADOR_S)){//VER SI HAY LINEA CON MATRIZ   //VER SI HAY LINEA CON MATRIZ  JCHAVEZ 27092009. se comentó pues no es necesario que valide ya que se consultará al local
                log.debug("jjccaalloo:VariablesFidelizacion.vDniCliente" + VariablesFidelizacion.vDniCliente);
                VariablesVentas.vArrayList_CampLimitUsadosMatriz =
                        UtilityFidelizacion.CampLimitadasUsadosDeMatrizXCliente(VariablesFidelizacion.vDniCliente);

                log.debug("******VariablesVentas.vArrayList_CampLimitUsadosMatriz" +
                          VariablesVentas.vArrayList_CampLimitUsadosMatriz);
                // } // JCHAVEZ 27092009. se comentó pues no es necesario que valide ya que se consultará al local
                //cargando las campañas automaticas limitadas en cantidad de usos desde matriz
            } else {
                log.info("Cliente esta invalidado para descuento...");
            }
        }else{
            // KMONCADA 09.02.2015 CANCELA LA TRANSACCION DE VENTA CON PUNTOS
            VariablesFidelizacion.limpiaVariables();
            lblCli.setText("");
            UtilityFidelizacion.operaCampañasFidelizacion(" ", VariablesConvenioBTLMF.vCodConvenio);
        }
    }


    public static void setMensajeDNIFidelizado(JLabel lblObj, String pTipo, JTextField txtObj, JDialog dlgObj) {
        if (VariablesFidelizacion.vDniCliente.trim().length() > 7 &&
            VariablesFidelizacion.vNumTarjeta.trim().length() > 0) {
            if (!VariablesFidelizacion.vDNI_Anulado) {
                lblObj.setText("  DNI no afecto a Descuento.");
                lblObj.setVisible(true);
            } else {
                lblObj.setText("");
                lblObj.setVisible(false);
                ////////////////////////////////////////////
                if (pTipo.trim().equalsIgnoreCase(DLG_RESUMEN_PEDIDO)) {
                    //Se evalua si ya esta en el limite de ahorro diario
                    //DUBILLUZ 28.05.2009
                    log.info("VariablesFidelizacion.vAhorroDNI_Pedido:" + VariablesFidelizacion.vAhorroDNI_Pedido);
                    log.info("VariablesFidelizacion.vAhorroDNI_x_Periodo:" +
                             VariablesFidelizacion.vAhorroDNI_x_Periodo);
                    log.info("VariablesFidelizacion.vMaximoAhorroDNIxPeriodo:" +
                             VariablesFidelizacion.vMaximoAhorroDNIxPeriodo);
                    log.info("VariablesFidelizacion.vIndComprarSinDcto:" + VariablesFidelizacion.vIndComprarSinDcto);
                    if (VariablesFidelizacion.vAhorroDNI_Pedido + VariablesFidelizacion.vAhorroDNI_x_Periodo >=
                        VariablesFidelizacion.vMaximoAhorroDNIxPeriodo && !VariablesFidelizacion.vExcluyeAcumula) { // AOVIEDO 17/05/2017
                        if (!VariablesFidelizacion.vIndComprarSinDcto) {
                            FarmaUtility.showMessage(dlgObj,
                                                     "El tope de descuento por persona es de "+ConstantesUtil.simboloSoles+" " + FarmaUtility.formatNumber(VariablesFidelizacion.vMaximoAhorroDNIxPeriodo) +
                                                     "\n" +
                                    "El cliente ya llegó a su tope.", txtObj);
                            VariablesFidelizacion.vIndComprarSinDcto = true;
                        }

                    } else {
                        VariablesFidelizacion.vIndComprarSinDcto = false;
                    }
                }
                ////////////////////////////////////////////
            }

        }
    }

    public static void ingresoMedico(Frame myParentFrame, JLabel lbMed, JLabel lblMsj, JLabel lblCli, JDialog dlgObj,
                                     String pTipo_in, JLabel lblSinComision, JTextField txtObj) {
        String pPermiteIngresoMedido = UtilityFidelizacion.getPermiteIngresoMedido();

        if (pPermiteIngresoMedido.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
            if (VariablesVentas.vEsPedidoConvenio ||
                (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.trim().length() >
                 1)) {
                FarmaUtility.showMessage(dlgObj, "No puede ingresar el Médido porque tiene" + "seleccionado convenio.",
                                         txtObj);
                return;
            }
            DlgBusquedaMedicoCamp dlgLista =
                new DlgBusquedaMedicoCamp(myParentFrame, "", true, lbMed, lblMsj, lblCli, dlgObj, pTipo_in,
                                          lblSinComision);
            dlgLista.setVisible(true);
            /*
            if(FarmaVariables.vAceptar){
                pExiste = "S";
            }
            else{
                pExiste = "NO_SELECCIONO";
            }
            */
        } else
            FarmaUtility.showMessage(dlgObj, "Por el momento no existen promociones por Receta.", txtObj);
        log.debug("****** ====VARIABLES DE MEDICO ==========================******");
        log.debug("VariablesFidelizacion.V_NUM_CMP:" + VariablesFidelizacion.V_NUM_CMP);
        log.debug("VariablesFidelizacion.V_NOMBRE:" + VariablesFidelizacion.V_NOMBRE);
        log.debug("VariablesFidelizacion.V_DESC_TIP_COLEGIO:" + VariablesFidelizacion.V_DESC_TIP_COLEGIO);
        log.debug("VariablesFidelizacion.V_TIPO_COLEGIO:" + VariablesFidelizacion.V_TIPO_COLEGIO);
        log.debug("VariablesFidelizacion.V_COD_MEDICO:" + VariablesFidelizacion.V_COD_MEDICO);
        log.debug("****** ====VARIABLES DE MEDICO ==========================******");
    }
    
    public static void actualizarCampanasR(){
        try{
            log.info("Actualizando cmapañas por forma de pago...");
             if(VariablesFidelizacion.vCodFormaPagoCampanasR.length() > 0 && 
               !VariablesFidelizacion.vCodFormaPagoCampanasR.equals("E0000") && 
               VariablesFidelizacion.vNumTarjeta.length() > 0){
                List listCampanasR = DBFidelizacion.obtenerCampaniasR_XFidelizacion(VariablesFidelizacion.vNumTarjeta, VariablesConvenioBTLMF.vCodConvenio);
                if(VariablesFidelizacion.vCodFormaPagoCampanasR.equalsIgnoreCase(ConstantsFidelizacion.COD_FPAGO_TARJ_OH)){
                    List listCampOh = DBFidelizacion.getCampOh_Fidelizacion();
                    if(listCampOh != null && !listCampOh.isEmpty()){
                        listCampanasR.addAll(listCampOh);
                    }
                }
                eliminarCampanaR();
                if(listCampanasR.size() > 0){
                    for(int i = 0; i < listCampanasR.size(); i++){
                        Map mapaCampana = (Map)listCampanasR.get(i);
                        VariablesFidelizacion.vListCampañasFidelizacion.add(mapaCampana);
                    }
                }
                actualizaListaCupones();
            }
        }catch(Exception ex){
            log.error("", ex);
        }
    }
    
    public static boolean isCampanaTarjeta(String pTipoCampana){
        if(pTipoCampana.trim().equalsIgnoreCase("R") ||
            pTipoCampana.trim().equalsIgnoreCase("F"))
            return true;
        else
            return false;
    }

    public static boolean isNumero(String cadena) {
        double num;
        boolean res;
        try {
            num = Double.parseDouble(cadena.trim());
            res = true;
        } catch (NumberFormatException e) {
            res = false;
        }

        return res;
    }
    
    public static void actualizaListaCupones(){
        // borrar las R    VariablesVentas.vArrayList_Cupones
        Map mapAux = new HashMap(); //mapa de la campania del listado de cupones
        String campAux = "";
        String codCampCupon = "", nroCupon = "";
        ArrayList cuponesValidos = new ArrayList();
        
        for (int i = 0; i < VariablesVentas.vArrayList_Cupones.size(); i++) {
            log.debug("i:" + i);
            mapAux = (HashMap)VariablesVentas.vArrayList_Cupones.get(i);
            campAux = ((String)mapAux.get("COD_CAMP_CUPON")).trim() + "";
            if (!isCampanaTarjeta(((String)mapAux.get("TIPO_CAMPANA")).trim() + "")) {
                if(isNumero(campAux)){
                    log.debug("campAux:" + campAux);
                    //cuponesValidos.add(mapAux);
                    log.debug("ANTES");
                    nroCupon = ((String)mapAux.get("COD_CUPON")).trim() + "";
                    log.debug("despues");
                    // KMONCADA 22.02.2016 NUEVO FORMATO DE CUPON
                    codCampCupon = UtilityVentas.obtenerCampaniaDeCupon(nroCupon);
                    //codCampCupon = nroCupon.substring(0, 5);
                    try {
                        mapAux = new HashMap();
                        mapAux = DBVentas.getDatosCupon(codCampCupon, nroCupon, VariablesFidelizacion.vDniCliente);
                        mapAux.put("COD_CUPON", nroCupon);
                    } catch (SQLException e) {
                        //cuponesValidos.add(mapAux);
                    }
                    log.debug("agrega " + i);
                    cuponesValidos.add(mapAux);    
                }
            } else
                log.debug("no es cupon");
        }           

        log.debug("XXX cuponesValidos:" + cuponesValidos);
        VariablesVentas.vArrayList_Cupones = new ArrayList();
        VariablesVentas.vArrayList_Cupones = (ArrayList)cuponesValidos.clone();
        
        // todas las campanas que no SON R
        
        // agrega campana tipo R que se listo segun la forma de pago
        if (VariablesFidelizacion.vListCampañasFidelizacion.size() > 0) {

            Map mapCampFid = new HashMap(); //mapa de la campania de fidelizacion
            Map mapCampCup = new HashMap(); //mapa de la campania del listado de cupones

            String codCampFid = ""; //codigo de campania de fidelizacion
            String CodCampCup = ""; //codigo de campania de cupon de la lista general

            boolean existe = false;
            log.debug("VariablesFidelizacion.vListCampañasFidelizacion.size():" +
                      VariablesFidelizacion.vListCampañasFidelizacion.size());
            for (int i = 0; i < VariablesFidelizacion.vListCampañasFidelizacion.size(); i++) {
                mapCampFid = (HashMap)VariablesFidelizacion.vListCampañasFidelizacion.get(i);
                log.info("-----------------------------");
                log.info("TIPO_CAMPANA: "+((String)mapCampFid.get("TIPO_CAMPANA")).trim());
                log.info("COD_CAMP_CUPON: "+((String)mapCampFid.get("COD_CAMP_CUPON")).trim());
                log.info("-----------------------------");
                
                if (isCampanaTarjeta(((String)mapCampFid.get("TIPO_CAMPANA")).trim() + "")) {
                    codCampFid = ((String)mapCampFid.get("COD_CAMP_CUPON")).trim();
                    log.debug("mapCampFid:"+mapCampFid);
                    for (int j = 0; j < VariablesVentas.vArrayList_Cupones.size(); j++) {
                        mapCampCup = (HashMap)VariablesVentas.vArrayList_Cupones.get(j);
                        CodCampCup = ((String)mapCampCup.get("COD_CAMP_CUPON")).trim();
                        if (codCampFid.equalsIgnoreCase(CodCampCup)) { //ver si ya existe
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        String tipoCampFija=((String)mapCampFid.get("TIPO_CAMPANA")).trim();
                        VariablesVentas.vArrayList_Cupones.add(mapCampFid);
                    }    
                }
            }
            log.debug("VariablesVentas.vArrayList_Cupones.size():" + VariablesVentas.vArrayList_Cupones.size());
        }
        
    }
    
    public static void eliminarCampanaR(){
        for(int i = 0; i < VariablesFidelizacion.vListCampañasFidelizacion.size(); i++){
            Map mapaCampana = (Map)VariablesFidelizacion.vListCampañasFidelizacion.get(i);
            String tipoCampana = (String)mapaCampana.get("TIPO_CAMPANA");
            if(tipoCampana.equals("R")){
                VariablesFidelizacion.vListCampañasFidelizacion.remove(i);
                eliminarCampanaR();
            }
        }
    }
    
    static class DlgDatosProceso extends JDialogProgress{
        
        public DlgDatosProceso() {
            super();
        }
        
        public DlgDatosProceso(Frame frame, String string, boolean b) {
            super(frame, string, b);
        }
    
        
        public void ejecutaProceso() {
            realizaProceso();
        }
        
        private void realizaProceso() {
            for(int i=0;i<5000;i++){
                
            }
        }
    
    
    }

}
