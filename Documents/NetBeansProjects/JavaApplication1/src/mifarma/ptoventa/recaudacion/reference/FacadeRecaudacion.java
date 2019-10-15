package mifarma.ptoventa.recaudacion.reference;

import java.awt.Frame;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.DlgCobroFinanciero;
import mifarma.ptoventa.caja.DlgNuevoCobro;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.PrintConsejo;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.cnx.FarmaVentaCnxUtility;
import mifarma.ptoventa.fidelizacion.DlgConfirmacionImpresion;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.recaudacion.dao.DAORecaudacion;
import mifarma.ptoventa.recaudacion.dao.FactoryRecaudacion;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FacadeRecaudacion {
    private static final Logger log = LoggerFactory.getLogger(FacadeRecaudacion.class);

    private ArrayList<ArrayList<Object>> lstDetalle = new ArrayList<>();

    private DAORecaudacion daoRecaudacion;
    public FacadeRecaudacion() {
        super();
        daoRecaudacion = FactoryRecaudacion.getDAORecaudacion(FactoryRecaudacion.Tipo.MYBATIS);
    }

    public String grabaCabeRecau(ArrayList<Object> arrayCabecera, String pDni) {
        String tmpCodigo = "";
        try {
            if (arrayCabecera.size() > 0) {
                tmpCodigo =
                        daoRecaudacion.grabaCabRecaudacion(arrayCabecera.get(0).toString().trim(), 
                                                           arrayCabecera.get(1).toString().trim(),
                                                           arrayCabecera.get(2).toString().trim(),
                                                           arrayCabecera.get(3).toString().trim(),
                                                           arrayCabecera.get(4).toString().trim(),
                                                           arrayCabecera.get(5).toString().trim(),
                                                           arrayCabecera.get(6).toString().trim(),
                                                           arrayCabecera.get(7).toString().trim(),
                                                           arrayCabecera.get(8).toString().trim(),
                                                           //Tipo de moneda Origen
                                                           //arrayCabecera.get(35).toString().trim(),
                                                           arrayCabecera.get(9).toString().trim(),
                                                           arrayCabecera.get(10).toString().trim(),
                                                           arrayCabecera.get(11).toString().trim(),
                                                           arrayCabecera.get(12).toString().trim(),
                                                           arrayCabecera.get(13).toString().trim(),
                                                           arrayCabecera.get(14).toString().trim(),
                                                           arrayCabecera.get(15).toString().trim(),
                                                           arrayCabecera.get(16).toString().trim(),
                                                           arrayCabecera.get(17).toString().trim(),
                                                           arrayCabecera.get(18).toString().trim(),
                                                           arrayCabecera.get(19).toString().trim(),
                                                           arrayCabecera.get(20).toString().trim(),
                                                           arrayCabecera.get(21).toString().trim(),
                                                           arrayCabecera.get(22).toString().trim(),
                                                           arrayCabecera.get(23).toString().trim(),
                                                           arrayCabecera.get(24).toString().trim(),
                                                           arrayCabecera.get(25).toString().trim(),
                                                           pDni);    //ASOSA - 06/08/2015 - RAIZ
            }

        } catch (Exception ex) {
            log.error("", ex);
        }
        return tmpCodigo;
    }

    public ArrayList<ArrayList<String>> obtenerRcdPend(String strCodGrupoCia, String strCodCia, String strCodLocal, String strCodRecau) {
        ArrayList<ArrayList<String>> tmpArray = new ArrayList<>();
        try {
            tmpArray = daoRecaudacion.obtenerRcdPendiente(strCodGrupoCia, strCodCia, strCodLocal, strCodRecau);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return tmpArray;
    }

    public ArrayList<String> obtenerCodFormsPagoRCD() {
        ArrayList<String> tmpArray = new ArrayList<>();
        try {
            tmpArray = daoRecaudacion.obtenerCodFormsPagoRCD();
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return tmpArray;
    }

    public boolean grabaFormPagoRecau(ArrayList<ArrayList<Object>> lstDetallePago, String pVuelto, String codAutorizacion, Long codTrssc,
                                      String estTrsscSix, String strFechaOrigen) throws Exception {
        lstDetalle = lstDetallePago;
        colocaVueltoDetallePago(pVuelto);
        boolean vRetorno;
        String vCodigoRecaudacion = (lstDetallePago.get(0)).get(3).toString().trim();
        try {
            daoRecaudacion.openConnection();
            for (int i = 0; i < lstDetallePago.size(); i++) {
                daoRecaudacion.grabaFormPagoRCD((lstDetallePago.get(i)).get(0).toString().trim(),
                                                (lstDetallePago.get(i)).get(1).toString().trim(),
                                                (lstDetallePago.get(i)).get(2).toString().trim(),
                                                (lstDetallePago.get(i)).get(3).toString().trim(),
                                                (lstDetallePago.get(i)).get(4).toString().trim(),
                                                (lstDetallePago.get(i)).get(5).toString().trim(),
                                                (lstDetallePago.get(i)).get(6).toString().trim(),
                                                (lstDetallePago.get(i)).get(7).toString().trim(),
                                                (lstDetallePago.get(i)).get(8).toString().trim(),
                                                (lstDetallePago.get(i)).get(9).toString().trim(),
                                                (lstDetallePago.get(i)).get(10).toString().trim(),
                                                (lstDetallePago.get(i)).get(11).toString().trim(),
                                                (lstDetallePago.get(i)).get(12).toString().trim(),
                                                (lstDetallePago.get(i)).get(13).toString().trim())
                    //,((ArrayList)lstDetallePago.get(i)).get(14).toString().trim()
                    ;
            }

            daoRecaudacion.cambiarIndicadorRCD((lstDetallePago.get(0)).get(0).toString().trim(),
                                               (lstDetallePago.get(0)).get(1).toString().trim(),
                                               (lstDetallePago.get(0)).get(2).toString().trim(),
                                               vCodigoRecaudacion, 
                                               ConstantsRecaudacion.ESTADO_COBRADO,
                                               FarmaVariables.vIdUsu.trim(), 
                                               "",
                                               ConstantsRecaudacion.TIP_IMPRE,
                                               // Parametro para actualizar el estado de impresion.  RLLantoy 25.06.2013
                                               codTrssc, 
                                               estTrsscSix,
                                               codAutorizacion, 
                                               VariablesCaja.vSecMovCaja, 
                                               strFechaOrigen, 
                                               "", 
                                               "", 
                                               "");
            daoRecaudacion.commit();
            vRetorno = true;
        } catch (Exception ex) {
            daoRecaudacion.rollback();
            ex.printStackTrace();
            FarmaUtility.enviaCorreoPorBD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal,
                                          VariablesPtoVenta.vDestEmailErrorCobro, "Error al cobrar Recaudacion",
                                          "Error al procesar cobro de Recaudacion",
                                          "Codigo Recaudacion : " + vCodigoRecaudacion + "<br>" + "IP PC: " +
                                          FarmaVariables.vIpPc + "<br>" + "Error: " + ex.getMessage() + "<br>", "");
            log.error("", ex);
            vRetorno = false;
            throw ex;
        }

        return vRetorno;
    }

    public void actualizarCabRcdEstadoVentaCMR(String strCodRecau, Long codTrssc, String estTrsscSix,
                                               String codAutorizacion, String nroCuotas, String fechaVencCuota,
                                               String strMontoPagarCuota) {
        try {
            daoRecaudacion.openConnection();
            daoRecaudacion.cambiarIndicadorRCD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                               FarmaVariables.vCodLocal, strCodRecau,
                                               ConstantsRecaudacion.ESTADO_COBRADO, FarmaVariables.vIdUsu.trim(), "",
                                               ConstantsRecaudacion.TIP_IMPRE, codTrssc, estTrsscSix, codAutorizacion,
                                               VariablesCaja.vSecMovCaja, "", nroCuotas, fechaVencCuota,
                                               strMontoPagarCuota);
            daoRecaudacion.commit();
        } catch (Exception ex) {
            daoRecaudacion.rollback();
            log.error("", ex);
        }
    }


    public void anularRCDPend(String strCodRecau, Long codTrssc) {
        try {
            daoRecaudacion.openConnection();
            daoRecaudacion.cambiarIndicadorRCD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                               FarmaVariables.vCodLocal, strCodRecau,
                                               ConstantsRecaudacion.ESTADO_ANULADO, "", "", "", codTrssc, "", null,
                                               "-", "", "", "", "");
            daoRecaudacion.commit();
        } catch (Exception ex) {
            daoRecaudacion.rollback();
            log.error("", ex);
        }
    }

    private void colocaVueltoDetallePago(String pVuelto) {
        if (lstDetalle.size() <= 0) {
            return;
        }
        boolean existeSoles = false;
        boolean existeDolares = false;
        int filaSoles = 0;
        int filaDolares = 0;
        for (int i = 0; i < lstDetalle.size(); i++) {
            if (((lstDetalle.get(i)).get(4)).toString().equalsIgnoreCase(ConstantsCaja.FORMA_PAGO_EFECTIVO_SOLES)) {
                existeSoles = true;
                filaSoles = i;
                break;
            } else if (((lstDetalle.get(i)).get(4)).toString().equalsIgnoreCase(ConstantsCaja.FORMA_PAGO_EFECTIVO_DOLARES)) {
                existeDolares = true;
                filaDolares = i;
            }
        }
        if (existeSoles) {
            (lstDetalle.get(filaSoles)).set(8, pVuelto);
        } else if (existeDolares && !existeSoles) {
            (lstDetalle.get(filaDolares)).set(8, pVuelto);
        }
    }

    public ArrayList<ArrayList<String>> listarRcdCanceladas(String strCodGrupoCia, String strCodCia, String strCodLocal, String pCodRcd,
                                         String pMonRcd, String pTipocobro) {
        ArrayList<ArrayList<String>> tmpArrayRcd = new ArrayList<>();
        try {
            daoRecaudacion.openConnection();
            tmpArrayRcd =
                    daoRecaudacion.obtenerRcdCanceladas(strCodGrupoCia, strCodCia, strCodLocal, pCodRcd, pMonRcd, pTipocobro);
            daoRecaudacion.commit();
        } catch (Exception ex) {
            daoRecaudacion.rollback();
            log.error("" + ex);
        }
        return tmpArrayRcd;
    }
    
    public ArrayList<ArrayList<String>> listarRcdCanceladasFechas(String strCodGrupoCia, String strCodCia, String strCodLocal,
                                                                  String pFechaIni,String pFechaFin, String pTipocobro) {
        ArrayList<ArrayList<String>> tmpArrayRcd = new ArrayList<>();
        try {
            daoRecaudacion.openConnection();
            tmpArrayRcd =
                    daoRecaudacion.obtenerRcdCanceladasFechas(strCodGrupoCia, strCodCia, strCodLocal, pFechaIni, pFechaFin, pTipocobro);
            daoRecaudacion.commit();
        } catch (Exception ex) {
            daoRecaudacion.rollback();
            log.error("" + ex);
        }
        return tmpArrayRcd;
    }
    
    public void anularRCDCancelada(String strCodGrupoCia, String strCodCia, String strCodLocal, String strCodRecau,
                                   String pUsuAnuRcd, String pFecAnuRcd) {
        try {
            daoRecaudacion.openConnection();
            daoRecaudacion.cambiarIndicadorRCD(strCodGrupoCia, strCodCia, strCodLocal, strCodRecau,
                                               ConstantsRecaudacion.ESTADO_ANULADO, pUsuAnuRcd, pFecAnuRcd, "", null,
                                               "", null, "-", "", "", "", "");
            daoRecaudacion.commit();
        } catch (Exception ex) {
            daoRecaudacion.rollback();
            log.error("", ex);
        }
    }

    public ArrayList<String> obtenerMontoPagarCitiPres(String pCodCliCiti) {
        ArrayList<String> tmpArrayRcd = new ArrayList<>();
        try {
            tmpArrayRcd = daoRecaudacion.obtenerMontoPagarCitiPres(pCodCliCiti);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
        return tmpArrayRcd;
    }


    /**
     * Arma la trama de consulta para Recaudacion Prestamos Citibank
     * @author GFonseca
     * @since 17.Junio.2013
     */
    public String getTramaPresCiti(String opConsulta, String codConsulta, String tipoMoneda, String indTransaccion,
                                   String codLocal, 
                                   String codServ   //ASOSA - 07/08/2015 - RAIZ
                                   ) {
        String resultado = "";
        try {
            resultado = daoRecaudacion.getTramaPresCiti(opConsulta, codConsulta, tipoMoneda, indTransaccion, codLocal, codServ);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
        return resultado;
    }


    /**
     * Envia una trama de consulta hacia el servidor central en caso de prestamos Citibank y retorna una trama de respuesta
     * @author GFonseca
     * @since 17.Junio.2013
     */
    public String setTramaPresCiti(String trama) {
        String resultado = "";
        try {
            resultado = daoRecaudacion.setTramaPresCiti(trama);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
        return resultado;
    }

    /**
     * Procesa la trama recibida desde el servidor central en caso de prestamos Citibank
     * @author GFonseca
     * @since 17.Junio.2013
     */
    public ArrayList<ArrayList<String>> obtenerDatosPagarCitiPres(String trama) {
        ArrayList<ArrayList<String>> tmpArrayRcd = new ArrayList<>();
        try {
            tmpArrayRcd = daoRecaudacion.obtenerDatosPagarCitiPres(trama);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
        return tmpArrayRcd;
    }

    /**
     * Llama a la pantalla de nuevo cobro
     * @author ERIOS
     * @since 20.06.2013
     * @param myParentFrame
     * @param tmpCodigo
     * @param arrayCabecera
     * @param tipoRec
     * @return
     */
    public boolean cobrarRecaudacion(Frame myParentFrame, String tmpCodigo, ArrayList<Object> arrayCabecera,
                                     String tipoRec) {
        boolean vRetorno = false;
        //jvara. En la siguiente linea se carga el Dlg de logueo y se imprime un voucher de confirmacion de donacion
        if (cargaLogin_verifica(myParentFrame)) {
            //JVARA 26-09-2017 FLAG QUE INDICA SI SE DEBE SEGUIR CON EL FLUJO DE IMPRESION POR DEFECTO O QUE DEBE
            String flag = "";
            if (tipoRec != null && ConstantsRecaudacion.TIPO_REC_TELETON.equals(tipoRec)) {
                try {
                    flag = DBCaja.getFlagImpresionTeleton();
                } catch (SQLException e) {
                    flag = "S";
                }
            }

            if ((flag != null && flag.trim().equals("S")) || flag.trim().equals("")) {
            
                if (tipoRec.equalsIgnoreCase(ConstantsRecaudacion.TIPO_REC_FINANCIERO)) {
                    System.out.println("------------------------------------------");
                    for(int i=0;i<arrayCabecera.size();i++){
                        String dato=arrayCabecera.get(i).toString();
                        System.out.println(" ["+i+"] --> "+dato);
                    }
                    System.out.println("******************************************");
                    if(VariablesRecaudacion.vVersionBFP.equalsIgnoreCase("02")){
                        DlgCobroFinanciero dlgNuevoCobro = new DlgCobroFinanciero(myParentFrame, "", true, true);
                        VariablesRecaudacion.vCodCabRecau=tmpCodigo;
                        dlgNuevoCobro.setVisible(true);
                    }else{
                        DlgNuevoCobro dlgNuevoCobro = new DlgNuevoCobro(myParentFrame, "", true);
                        dlgNuevoCobro.setIndPedirLogueo(false);
                        dlgNuevoCobro.setIndTipoCobro(ConstantsCaja.COBRO_RECAUDACION);
                        dlgNuevoCobro.setCodRecau(tmpCodigo);
                        dlgNuevoCobro.setStrMonedaPagar(arrayCabecera.get(7).toString());
                        dlgNuevoCobro.setArrayCabeRcd(arrayCabecera);
                        dlgNuevoCobro.setVisible(true);
                    }
                }else{
                    DlgNuevoCobro dlgNuevoCobro = new DlgNuevoCobro(myParentFrame, "", true);
                    dlgNuevoCobro.setIndPedirLogueo(false);
                    dlgNuevoCobro.setIndTipoCobro(ConstantsCaja.COBRO_RECAUDACION);
                    dlgNuevoCobro.setCodRecau(tmpCodigo);
                    dlgNuevoCobro.setStrMonedaPagar(arrayCabecera.get(7).toString());
                    dlgNuevoCobro.setArrayCabeRcd(arrayCabecera);
                    
                    if (ConstantsRecaudacion.TIPO_REC_CITI.equals(tipoRec) ||
                        ConstantsRecaudacion.TIPO_REC_CMR.equals(tipoRec) ||
                        ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(tipoRec)) {
                        dlgNuevoCobro.setNumTarjeta(arrayCabecera.get(3).toString());
                        //ERIOS 21.01.2014 Se quita la funcionalidad de cargar el monto de pago
                        /*if(arrayCabecera.get(9).toString().equals(ConstantsCaja.EFECTIVO_SOLES ))
                        dlgNuevoCobro.setMontoPagado(arrayCabecera.get(10).toString());
                    else
                        dlgNuevoCobro.setMontoPagadoDolares(arrayCabecera.get(10).toString()); */
                    }
                    //jvara . Despues de esta linea , se imprime un voucher de verificacion de la donacion.
                    dlgNuevoCobro.setVisible(true);
                }
            } else { //JVARA 26-09-2017
                DlgConfirmacionImpresion dlgConfirmacionImpresion =
                    new DlgConfirmacionImpresion(myParentFrame, "", true);
                List listDocumento = new ArrayList();

                if (ConstantsRecaudacion.TIPO_REC_TELETON.equals(arrayCabecera.get(4).toString())) {
                    UtilityCaja utilityCaja = new UtilityCaja();
                    String monto = arrayCabecera.get(10).toString(); //ok
                    String dniCe = arrayCabecera.get(3).toString();
                    String tipoMoneda = arrayCabecera.get(7).toString();

                    try {
                        listDocumento =
                                DBCaja.imprVouVerifRecaudacion(monto, dniCe, ConstantsRecaudacion.TIPO_REC_TELETON,
                                                               tipoMoneda);
                    } catch (SQLException e) {
                        log.error("", e);

                    }

                    String cadenaTotal = "";
                    String cadenaDepurada = "";

                    cadenaTotal = cadenaResultante(listDocumento);

                    cadenaDepurada = cadenaDepurada(cadenaTotal);

                    String[] datos = cadenaDepurada.split("@");

                    dlgConfirmacionImpresion.getLblVerificacion().setText(datos[0]);
                    dlgConfirmacionImpresion.getLblLocal().setText(datos[1]);
                    dlgConfirmacionImpresion.getLblFecha().setText(datos[2]);
                    dlgConfirmacionImpresion.getLblDonacion().setText(datos[3]);
                    dlgConfirmacionImpresion.getLblDNI().setText(datos[4]);
                    dlgConfirmacionImpresion.getLblDNIValor().setText(datos[5]);
                    dlgConfirmacionImpresion.getLblMonto().setText(datos[6]);
                    dlgConfirmacionImpresion.getLblMontoValor().setText(datos[7]);

                    VariablesCaja.vTmpCodigo = tmpCodigo;
                    VariablesCaja.vArrayCabecera = arrayCabecera;
                    VariablesCaja.vTipoRec = tipoRec;
                    dlgConfirmacionImpresion.setVisible(true);

                }
            }

            if (FarmaVariables.vAceptar) {
                vRetorno = true;
            }
        }

        return vRetorno;
    }

    /*
    public boolean cobrarRecaudacion_BFP(Frame myParentFrame, String tmpCodigo, ArrayList<Object> arrayCabecera,
                                     String tipoRec) {

        return false;
    }*/
    private String cadenaResultante(List listDocumento){
        String cadenaVoucher = "";
        String cadenaResultante = "";
        
        for (int i = 0; i < listDocumento.size(); i++) {            
           cadenaVoucher=(String)((HashMap)listDocumento.get(i)).get("VALOR"); 
            if(cadenaVoucher!=null && !cadenaVoucher.trim().equals("")){
                cadenaResultante = cadenaResultante +cadenaVoucher+"@";
            }                         
        }
        
        return cadenaResultante;
    }  
    
    private String cadenaDepurada(String cadenaTotal){
        String cadenaDepurada = "";          
         
         for (int i = 0; i < cadenaTotal.length(); i++) {
         if(i!=cadenaTotal.length()-1){
             cadenaDepurada=cadenaDepurada+ cadenaTotal.charAt(i);
         } 
    
    } 
    
    return cadenaDepurada;
    
    } 

    private boolean cargaLogin_verifica(Frame myParentFrame) {
        DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
        dlgLogin.setRolUsuario(FarmaConstants.ROL_CAJERO);
        dlgLogin.setVisible(true);
        if (FarmaVariables.vAceptar) {
            return true;
        }
        return false;
    }

    public boolean cargaLogin_verifica_quimico(Frame myParentFrame) {
        DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
        dlgLogin.setRolUsuario(FarmaConstants.ROL_ADMLOCAL);
        dlgLogin.setVisible(true);
        if (FarmaVariables.vAceptar) {
            return true;
        }
        return false;
    }

    /**
     * Busca informacion de tarjeta Recaudaci�n Citibank
     * @author RLLANTOY
     * @since 14.06.2013
     */
    public ArrayList<ArrayList<String>> obtieneInfoTarjetaRecaudacion(String pNroTarjeta) {
        ArrayList<ArrayList<String>> InfoTarj = new ArrayList<>();
        try {
            InfoTarj =
                    daoRecaudacion.getInfoTarjetaRecaudacion(FarmaVariables.vCodGrupoCia, pNroTarjeta, ConstantsCaja.TIPO_ORIGEN_PAGO_RECAU);
            log.debug("Informacion de tarjeta :");
            log.debug("", InfoTarj);
        } catch (SQLException e) {
            log.error("Error al obtener informaci�n de tarjeta", e);
        }
        return InfoTarj;
    }


    /**
     * Imprime Comprobante Recaudacion
     * @author RLLANTOY
     * @since 19.06.2013
     */
    public void imprimirComprobantePagoRecaudacion(String pNumeroRecaudacion) throws Exception {
        String CompPagoCiti;

        CompPagoCiti =
                daoRecaudacion.impCompPagoRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                      pNumeroRecaudacion);
        log.debug("Comprobante Recaudacion: " + CompPagoCiti);

        //Impresion Original
        PrintConsejo.imprimirHtml(CompPagoCiti, VariablesPtoVenta.vImpresoraActual,
                                  VariablesPtoVenta.vTipoImpTermicaxIp);
        //Copia Impresion
        PrintConsejo.imprimirHtml(CompPagoCiti, VariablesPtoVenta.vImpresoraActual,
                                  VariablesPtoVenta.vTipoImpTermicaxIp);

    }

    /**
     * Impresi�n para el caso de Venta CMR
     * @author GFonseca
     * @since 20.08.2013
     */
    public void imprimirComprobantePagoRecauVentaCMR(String pNumeroRecaudacion, String strNumCuotas,
                                                     String strMontoPagarCuota, String strFechaVencCuota,
                                                     String strCodAutorizacion) {
        String templHtmlVentaCMROriginal = "";
        String templHtmlVentaCMRCopia = "";
        try {
            templHtmlVentaCMROriginal =
                    daoRecaudacion.getCompPagoRecauVentaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                            FarmaVariables.vCodLocal, pNumeroRecaudacion, strNumCuotas,
                                                            strMontoPagarCuota, strFechaVencCuota, strCodAutorizacion,
                                                            VariablesCaja.vNumCaja,
                                                            ConstantsRecaudacion.COD_COMPROBANTE_ORIGINAL);

            templHtmlVentaCMRCopia =
                    daoRecaudacion.getCompPagoRecauVentaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                            FarmaVariables.vCodLocal, pNumeroRecaudacion, strNumCuotas,
                                                            strMontoPagarCuota, strFechaVencCuota, strCodAutorizacion,
                                                            VariablesCaja.vNumCaja,
                                                            ConstantsRecaudacion.COD_COMPROBANTE_COPIA);

            log.debug("Comprobante Recaudacion venta CMR:");
            log.debug(templHtmlVentaCMROriginal);
            log.debug(templHtmlVentaCMRCopia);
            //Impresion Original
            PrintConsejo.imprimirHtml(templHtmlVentaCMROriginal, VariablesPtoVenta.vImpresoraActual,
                                      VariablesPtoVenta.vTipoImpTermicaxIp);
            //Copia Impresion
            PrintConsejo.imprimirHtml(templHtmlVentaCMRCopia, VariablesPtoVenta.vImpresoraActual,
                                      VariablesPtoVenta.vTipoImpTermicaxIp);
        } catch (SQLException e) {
            log.error("Error al imprimir comprobante de pago venta CMR", e);
        }
    }

    /**
     * Impresi�n para anulacion de recaudaci�n
     * @author GFonseca
     * @since 27.10.2013
     */
    public void imprimirComprobanteAnulRecaudacion(String strCodRecau) throws Exception {
        //ERIOS 2.4.0 Solo una copia
        String templHtmlAnulRecauOriginal = "";


        templHtmlAnulRecauOriginal =
                daoRecaudacion.imprimirComprobanteAnulRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                  FarmaVariables.vCodLocal, VariablesCaja.vNumCaja,
                                                                  strCodRecau,
                                                                  ConstantsRecaudacion.COD_COMPROBANTE_ORIGINAL);
        log.debug("Comprobante Anulacion recaudacion.");
        log.debug(templHtmlAnulRecauOriginal);
        //log.debug(templHtmlVentaCMRCopia);
        //Impresion Original
        PrintConsejo.imprimirHtml(templHtmlAnulRecauOriginal, VariablesPtoVenta.vImpresoraActual,
                                  VariablesPtoVenta.vTipoImpTermicaxIp);

    }
    
    public void reimprimirComprobanteAnulRecaudacion(String strCodRecau) throws Exception {
        //ERIOS 2.4.0 Solo una copia
        String templHtmlAnulRecauOriginal = "";


        templHtmlAnulRecauOriginal =
                daoRecaudacion.imprimirComprobanteAnulRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                  FarmaVariables.vCodLocal, VariablesCaja.vNumCaja,
                                                                  strCodRecau,
                                                                  ConstantsRecaudacion.COD_COMPROBANTE_COPIA);
        log.debug("Comprobante Anulacion recaudacion.");
        log.debug(templHtmlAnulRecauOriginal);
        //log.debug(templHtmlVentaCMRCopia);
        //Impresion Original
        PrintConsejo.imprimirHtml(templHtmlAnulRecauOriginal, VariablesPtoVenta.vImpresoraActual,
                                  VariablesPtoVenta.vTipoImpTermicaxIp);

    }
    
    public void imprimirComprobanteAnulRecauVentaCMR(String strCodRecau) {
        String templHtmlVentaCMROriginal = "";
        String templHtmlVentaCMRCopia = "";
        try {

            templHtmlVentaCMROriginal =
                    daoRecaudacion.getCompAnulRecauVentaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                            FarmaVariables.vCodLocal, strCodRecau,
                                                            VariablesCaja.vNumCaja,
                                                            ConstantsRecaudacion.COD_COMPROBANTE_ORIGINAL);

            templHtmlVentaCMRCopia =
                    daoRecaudacion.getCompAnulRecauVentaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                            FarmaVariables.vCodLocal, strCodRecau,
                                                            VariablesCaja.vNumCaja,
                                                            ConstantsRecaudacion.COD_COMPROBANTE_COPIA);

            log.debug("Comprobante Recaudacion venta CMR:");
            log.debug(templHtmlVentaCMROriginal);
            log.debug(templHtmlVentaCMRCopia);
            //Impresion Original
            PrintConsejo.imprimirHtml(templHtmlVentaCMROriginal, VariablesPtoVenta.vImpresoraActual,
                                      VariablesPtoVenta.vTipoImpTermicaxIp);
            //Copia Impresion
            PrintConsejo.imprimirHtml(templHtmlVentaCMRCopia, VariablesPtoVenta.vImpresoraActual,
                                      VariablesPtoVenta.vTipoImpTermicaxIp);
        } catch (SQLException e) {
            log.error("Error al imprimir comprobante de anulacion venta CMR", e);
        }
    }


    /**
     * Obtiene Estado de Impresion Recaudacion
     * @author RLLANTOY
     * @since 24.06.2013
     */

    public boolean obtieneEstadoImpresionRacaudacion(String pNumeroRecaudacion) {
        String EstImpRec = "";
        boolean estado = false;
        try {
            EstImpRec =
                    daoRecaudacion.getEstadoImpRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                           pNumeroRecaudacion).trim();
            if (EstImpRec.equals(ConstantsRecaudacion.TIP_IMPRE))
                estado = true;
            else if (EstImpRec.equals(ConstantsRecaudacion.TIP_REIMPRE))
                estado = false;

            log.debug("Estado de Impresion Recaudacion:");
            log.debug(EstImpRec);
        } catch (SQLException e) {
            log.error("Error al obtener estado de impresion recaudacion", e);
        }
        return estado;
    }

    /**
     * Actualiza el estado de Impresion de Recaudacion
     * @author RLLANTOY
     * @since 24.06.2013
     */

    public void actualizaEstadoImpresionRacaudacion(String pNumeroRecaudacion, String pEstImpRecaudacion) {
        try {
            daoRecaudacion.updateEstadoImpRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                      FarmaVariables.vCodLocal, pNumeroRecaudacion,
                                                      pEstImpRecaudacion);
            log.debug("Nuevo estado de Impresi�n Recaudaci�n:");
            log.debug(pEstImpRecaudacion);
        } catch (SQLException e) {
            log.error("Error al actualizar estado de impresi�n de recaudaci�n", e);
        }
    }

    /**
     * Anulacion de una recaudacion
     * @author GFonseca
     * @since 25.Junio.2013
     */
    public String anularRecaudacion(String pNumeroRecaudacion, String vNumCaja, String pUsuModRecau, Long codTrsscAnul,
                                    String strCodRecauAnul) throws Exception {
        String resultado = "";

        resultado =
                daoRecaudacion.anularRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                 pNumeroRecaudacion, vNumCaja, pUsuModRecau, codTrsscAnul,
                                                 strCodRecauAnul);
        log.info("RESPUESTA DE ANULACION: resultado-----> "+resultado);
        return resultado;
    }

    /**
     * Anulacion de una recaudacion
     * @author GFonseca
     * @since 25.Junio.2013
     */
    public String anularRecaudacionVentaCMR(String pCodGrupoCia, String strCodCia, String strCodLocal,
                                            String pNumeroRecaudacion, String vNumCaja, String pUsuModRecau,
                                            Long codTrsscAnul) {
        String resultado = "";
        try {
            resultado =
                    daoRecaudacion.anularRecaudacionVentaCMR(pCodGrupoCia, strCodCia, strCodLocal, pNumeroRecaudacion,
                                                             vNumCaja, pUsuModRecau, codTrsscAnul);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
        return resultado;
    }

    /**
     * M�todo para validar tiempo de anulaci�n de una recaudaci�n
     * @author RLLantoy
     * @since 11.07.2013
     */

    public String validaTiempoAnulacion(String pNumeroRecaudacion) {
        String resultado = "";
        try {
            resultado =
                    daoRecaudacion.valTiempoAnulacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                      pNumeroRecaudacion);
        } catch (Exception e) {
            log.error("ERROR ES => " + e.getMessage());
        }

        return resultado;
    }

    /**
     * M�todo para obtener el tiempo m�ximo de anulaci�n de una recaudaci�n
     * @author RLLantoy
     * @since 11.07.2013
     */

    public String tiempoMaxAnulacionRecaudacion(String pTipoLlave) {
        String resultado = "";
        try {
            resultado = daoRecaudacion.tiempoMaxAnulacionRecau(pTipoLlave);
        } catch (Exception e) {
            log.error("ERROR ES => " + e.getMessage());
        }

        return resultado;
    }


    /**
     * M�todo para obtener estado de recaudaci�n
     * @author RLLantoy
     * @since 09.07.2013
     */

    public String obtieneEstadoRecaudacion(String pNumeroRecaudacion) {
        String resultado = "";
        try {
            resultado =
                    daoRecaudacion.getEstadoRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                        pNumeroRecaudacion);
        } catch (Exception e) {
            log.error("ERROR ES => " + e.getMessage());
        }

        return resultado;
    }

    /**
     * Actualiza el monto cobrado para Recaudacion prestamos Citibank
     * @author GFonseca
     * @since 27.Junio.2013
     */
    public ArrayList<String> actualizarMontoCobradoPresCiti(String pNumeroRecaudacion) {
        ArrayList<String> resultado = new ArrayList<String>();
        try {
            String vResultado =
                daoRecaudacion.actualizarMontoCobradoPresCiti(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                              FarmaVariables.vCodLocal, pNumeroRecaudacion);
            String[] aResultado = vResultado.split("�");
            resultado.add(aResultado[0]);
            resultado.add(aResultado[1]);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
        return resultado;
    }


    /**
     * Registra una transaccion de recaudacion en el servidor ADM para la comunicacion con el SIX
     * @author GFonseca
     * @since 04.Julio.2013
     */
    public Long registrarTrsscConsultaDeudaClaro(String terminal, String telefono,
                                                 String tipoProdServ) throws Exception {
        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.registrarTrsscConsultaDeudaClaro(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                FarmaVariables.vCodLocal,
                                                                ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                                ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                                ConstantsRecaudacion.TRNS_CNSULTA_SRV,
                                                                ConstantsRecaudacion.TIPO_REC_CLARO, terminal,
                                                                FarmaVariables.vDescCortaLocal, //comercio
                    FarmaVariables.vCodLocal, //sucursal
                    FarmaVariables.vDescCortaDirLocal, //ubicacion
                    telefono, tipoProdServ, FarmaVariables.vIdUsu);

        return tmpCodigo;
    }

    /**
     * Obtiene el estado de una transaccion de recaudacion en el servidor ADM para la comunicacion con el SIX
     * @author GFonseca
     * @since 09.Julio.2013
     */
    public String obtenerEstTrsscReacudacion(Long strCodRecauTrssc, String strModoRecau) throws Exception {
        String tmpCodigo = "";

        tmpCodigo = daoRecaudacion.obtenerEstTrsscReacudacion(strCodRecauTrssc, strModoRecau);

        return tmpCodigo;
    }

    /**
     * Obtiene una transaccion de respuesta de recaudacion en el servidor ADM para la comunicacion con el SIX
     * @author GFonseca
     * @since 11.Julio.2013
     */
    public ArrayList<ArrayList<String>> obtenerDatosTrsscSix(Long strCodRecauTrssc, String strModoRecau) throws Exception {
        ArrayList<ArrayList<String>> tmpArray = new ArrayList<>();

        tmpArray = daoRecaudacion.obtenerDatosTrsscSix(strCodRecauTrssc, strModoRecau);

        return tmpArray;
    }
    /**
     * Obtiene una transaccion de respuesta de recaudacion extorno de financiero en el servidor ADM para la comunicacion con el SIX
     * @author RArgumedo
     * @since 09.Agosto.2017
     */
    public ArrayList<ArrayList<String>> consultaRptaAnulSix_financiero(Long codTrssc, String strModoRecau, String tipMSJ) throws Exception {
        ArrayList<ArrayList<String>> rptaConsulta_Anul = new ArrayList<ArrayList<String>>();
        rptaConsulta_Anul = daoRecaudacion.consultaRptaAnulSix_financiero(codTrssc, strModoRecau, tipMSJ);
        
        return rptaConsulta_Anul;
    }
    
    /**
     * Registra una transaccion de recaudacion CMR en el servidor ADM para la comunicacion con el SIX
     * @author RLLANTOY
     * @since 24.07.2013
     */
    public Long registrarTrsscConsultaDeudaCMR(String codGrupoCia, String strCodCia, String strCodLocal,
                                               String strEstTrsscRecau, String strTipMsjRecau, String tipoTrssc,
                                               String tipoRcd, String strNroTarjeta, int numCuotas) {
        Long tmpCodigo = null;
        try {
            tmpCodigo =
                    daoRecaudacion.registrarTrsscConsultaDeudaCMR(codGrupoCia, strCodCia, strCodLocal, strEstTrsscRecau,
                                                                  strTipMsjRecau, tipoTrssc, tipoRcd, strNroTarjeta,
                                                                  numCuotas);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return tmpCodigo;
    }


    /**
     * Registra un pago de recaudacion CMR en el servidor ADM para la comunicacion con el SIX
     * @author GFonseca
     * @since 31.07.2013
     */
    public Long registrarTrsscPagoDeudaCMR(String strNroTarjeta, String strMonto, String strTerminal,
                                           String strComercio, String strUbicacion, String strNroCuotas,
                                           String strNroCaja, String strIdCajero, String strUsuario,
                                           String strCodRecau, String strDniUsu, double dblTipoCambio,
                                           String strTipoMoneda, double dblTotalPagar,
                                           int pRecaudOnline) throws Exception {

        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.registrarTrsscPagoDeudaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                          ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                          ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                          ConstantsRecaudacion.TRNS_PAG_TARJ, //tipo transaccion
                    ConstantsRecaudacion.TIPO_REC_CMR, //tipo recaudacion
                    strNroTarjeta, strMonto, strTerminal, strComercio, strUbicacion, strNroCuotas, strNroCaja,
                    strIdCajero, strUsuario, strCodRecau, strDniUsu, dblTipoCambio, strTipoMoneda, dblTotalPagar,
                    pRecaudOnline);

        return tmpCodigo;
    }

    /**
     * Registra un pago de recaudacion Ripley en el servidor ADM para la comunicacion con el SIX
     * @author CVILCA
     * @since 10.10.2013
     */
    public Long registrarTrsscPagoDeudaRipley(String strNroTarjeta, String strMonto, String strTerminal,
                                              String strComercio, String strUbicacion, String strNroCuotas,
                                              String strNroCaja, String strIdCajero, String strUsuario,
                                              String strCodRecau, String strDniUsu, double dblTipoCambio,
                                              String strTipoMoneda, double dblTotalPagar,
                                              int pRecaudOnline) throws Exception {

        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.registrarTrsscPagoDeudaRipley(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                             ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                             ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                             ConstantsRecaudacion.TRNS_PAG_TARJ, //tipo transaccion
                    ConstantsRecaudacion.TIPO_REC_RIPLEY, //tipo recaudacion
                    strNroTarjeta, strMonto, strTerminal, strComercio, strUbicacion, strNroCuotas, strNroCaja,
                    strIdCajero, strUsuario, strCodRecau, strDniUsu, dblTipoCambio, strTipoMoneda, dblTotalPagar,
                    pRecaudOnline);

        return tmpCodigo;
    }

    /**
     * Registra un pago de Claro en ADM para la comunicacion con el SIX
     * @author GFonseca
     * @since 31.07.2013
     */
    public Long registrarTrsscPagoDeudaClaro(String pCodGrupoCia, String strCodCia, String strCodLocal,
                                             String strTipMsjRecau, String strEstTrsscRecau, String strTipoTrssc,
                                             String strTipoRcd, String strMonto, String strTerminal,
                                             String strComercio, String strUbicacion, String strTelefono,
                                             String tipoProdServ, String numRecibo, String strUsuario,
                                             String strCodRecau, String strDniUsu, double dblTipoCambio,
                                             String strTipoMoneda, double dblTotalPagar,
                                             int pRecaudOnline) throws Exception {

        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.registrarTrsscPagoDeudaClaro(pCodGrupoCia, strCodCia, strCodLocal, strTipMsjRecau, strEstTrsscRecau,
                                                            strTipoTrssc, strTipoRcd, strMonto, strTerminal,
                                                            strComercio, strUbicacion, strTelefono, tipoProdServ,
                                                            numRecibo, strUsuario, strCodRecau, strDniUsu,
                                                            dblTipoCambio, strTipoMoneda, dblTotalPagar,
                                                            pRecaudOnline);

        return tmpCodigo;
    }


    /**
     * Registra una transaccion de anulacion CMR en ADM.
     * @author RLLANTOY
     * @since 31.07.2013
     */
    //x;
    public Long anularPagoTarjetaCMR(String strTipMsjRecau, String strEstTrsscRecau, String tipoTrssc, String tipoRcd,
                                     String strNroTarjeta, String numCuotas, String strMonto, String strNumCaja,
                                     String strIdCajero, String strMotExtorno, String strCodTrsscSix, String terminal,
                                     String comercio, String ubicacion, String strUsuario, String strCodRecau,
                                     String strDniUsu, double dblTipoCambio, String strTipoMoneda,
                                     String strCodRecauAnular, String strFechaRecauAnular,
                                     String strCodAutorizRecauAnular, double dblTotalPagar,
                                     int pRecaudOnline) throws Exception {
        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.anularPagoTarjetaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                    strTipMsjRecau, strEstTrsscRecau, tipoTrssc, tipoRcd,
                                                    strNroTarjeta, numCuotas, strMonto, strNumCaja, strIdCajero,
                                                    strMotExtorno, strCodTrsscSix, terminal, comercio, ubicacion,
                                                    strUsuario, strCodRecau, strDniUsu, dblTipoCambio, strTipoMoneda,
                                                    strCodRecauAnular, strFechaRecauAnular, strCodAutorizRecauAnular,
                                                    dblTotalPagar, pRecaudOnline);

        return tmpCodigo;
    }


    /**
     * Registra una transaccion de anulacion Ripley en ADM.
     * @author GFONSECA
     * @since 28.10.2013
     */
    public Long anularPagoTarjetaRipley(String codAuditoria, String strNroTarjeta, String numCuotas, String strMonto,
        //String strMotExtorno,
        String strCodTrsscSix, String fechaOrigen, String terminal, String strCodRecau, String strDniUsu,
        double dblTipoCambio, String strTipoMoneda, String strCodRecauAnular, String strFechaRecauAnular,
        String strCodAutorizRecauAnular, double dblTotalPagar, int pRecaudOnline) throws Exception {
        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.anularPagoTarjetaRipley(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                       ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                       ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                       ConstantsRecaudacion.TRNS_ANU_PAG_TARJ,
                                                       ConstantsRecaudacion.TIPO_REC_RIPLEY, strNroTarjeta, numCuotas,
                                                       strMonto, VariablesCaja.vNumCaja, FarmaVariables.vNuSecUsu,
                    //strMotExtorno,
                    strCodTrsscSix, fechaOrigen, codAuditoria, terminal, FarmaVariables.vDescCortaLocal,
                    FarmaVariables.vDescCortaDirLocal, FarmaVariables.vIdUsu, strCodRecau, strDniUsu, dblTipoCambio,
                    strTipoMoneda, strCodRecauAnular, strFechaRecauAnular, strCodAutorizRecauAnular, dblTotalPagar,
                    pRecaudOnline);

        return tmpCodigo;
    }

    /**
     * REALIZAR UNA ANULACION DE VENTA CMR
     * @author GFONSECA
     * @since 05.09.2013
     * @param mapParametros
     */
    public Long anularPagoTarjetaVentaCMR(String strTipMsjRecau, String strEstTrsscRecau, String tipoTrssc,
                                          String tipoRcd,

        String strNroTarjeta, String strMonto, String strTerminal, String strComercio, String strUbicacion,
        String strNumCuotas, String strIdCajero, String strNumDoc, String strIdAnular, String strUsuario) {
        Long tmpCodigo = null;
        try {
            tmpCodigo =
                    daoRecaudacion.anularPagoTarjetaVentaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                             strTipMsjRecau, strEstTrsscRecau, tipoTrssc, tipoRcd,
                                                             strNroTarjeta,

                        strMonto, strTerminal, strComercio, strUbicacion, strNumCuotas, strIdCajero, strNumDoc,
                        strIdAnular, strUsuario);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return tmpCodigo;
    }


    /**
     * Anula una transaccion de recaudacion CLARO en el servidor ADM para la comunicacion con el SIX
     * @author RLLANTOY
     * @since 31.07.2013
     */
    public Long anularPagoServicioCLARO(String codAuditoriaPago, String codGrupoCia, String strCodCia,
                                        String strCodLocal, String strTipMsjRecau, String strEstTrsscRecau,
                                        String tipoTrssc, String tipoRcd, String strMonto, String strTerminal,
                                        String strComercio, String strUbicacion, String strCodSucursal,
                                        String strUsuCrea, String strCodRecau, String strDniUsu, double dblTipoCambio,
                                        String strTipoMoneda, String strCodRecauAnular, String strFechaRecauAnular,
                                        String strCodAutorizRecauAnular, double dblTotalPagar,
                                        int pRecaudOnline) throws Exception {
        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.anularPagoServicioCLARO(codAuditoriaPago, codGrupoCia, strCodCia, strCodLocal, strTipMsjRecau,
                                                       strEstTrsscRecau, tipoTrssc, tipoRcd, strMonto, strTerminal,
                                                       strComercio, strUbicacion, strCodSucursal, strUsuCrea,
                                                       strCodRecau, strDniUsu, dblTipoCambio, strTipoMoneda,
                                                       strCodRecauAnular, strFechaRecauAnular,
                                                       strCodAutorizRecauAnular, dblTotalPagar, pRecaudOnline);

        return tmpCodigo;
    }


    /**
    * Anula una transacci�n de venta CMR
    * @author GFONSECA
    * @since 04.09.2013
    */
    /*public Long anularPagoTargetaVentaCMR(String strTipMsjRecau, String strEstTrsscRecau,
                                      String tipoTrssc, String tipoRcd, String strNroTarjeta, String numCuotas, String strMonto, String strNumCaja,
                                      String strIdCajero, String strMotExtorno, String strCodTrsscSix,
                                      String terminal, String comercio, String ubicacion, String strUsuario ){
         Long tmpCodigo = null;
          try{
                tmpCodigo = daoRecaudacion.anularPagoTarjetaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                                strTipMsjRecau, strEstTrsscRecau , tipoTrssc, tipoRcd, strNroTarjeta,
                                                                   numCuotas, strMonto, strNumCaja, strIdCajero, strMotExtorno, strCodTrsscSix,
                                                                      terminal, comercio, ubicacion,strUsuario);
         }catch(SQLException ex){
             log.error("",ex);
         }
         return tmpCodigo;
     }*/

    /**
     * Permite preguntar el estado de la transaccion en ADM y obtener el resultado de la transaccion registrado por el demonio.
     * @author GFONSECA
     * @since 07.08.2013
     */
    public ArrayList<Object> obtenerRespuestaSix(String modo, String operacion, Long codTrssc) throws Exception {
        boolean rpt = false;
        boolean msj = false;
        String strResponseCode = "";
        String strMontoPagar = "";
        String strCodAutorizacion = "";
        String strCodAuditoria = "";
        String strFechaVencCuota = "";
        String strNumOperacCobranza = "";
        String strNumOperacAcreedor = "";
        String strFechaOrig = "";
        String strMensaje = "";
        String strNumRecibo = "";

        ArrayList<Object> datosResp = new ArrayList<>();
        ArrayList<ArrayList<String>> datosRespSix = null;

        //ERIOS 2.4.0 Todas las recaudaciones son centralizadas
        String indicador = "";
        datosRespSix = UtilityRecaudacion.obtenerDatosTrsscSix(codTrssc, modo, indicador);

        if (indicador.equals(ConstantsRecaudacion.ESTADO_FIN_TAREA)) {
            log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                     ConstantsRecaudacion.RCD_PAGO_SIX_NO_HAY_RESP);
            msj = true; //FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_PAGO_SIX_MSJ_COBRO , null);
        } else if (ConstantsRecaudacion.ESTADO_SIX_TERMINADO.equals(ConstantsRecaudacion.vCOD_ESTADO_TRSSC_RECAU)) {
            log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                     ConstantsRecaudacion.RCD_PAGO_SIX_SI_HAY_RESP);
            rpt = true;

            if (ConstantsRecaudacion.RCD_MODO_PAGO_SIX.equals(modo) ||
                ConstantsRecaudacion.RCD_MODO_CONSULTA_SIX.equals(modo) ||
                ConstantsRecaudacion.RCD_MODO_RECARGA_SIX.equals(modo)) {
                strResponseCode = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 0); //Codigo de respuesta
                strCodAutorizacion =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 3); //Codigo Autorizacion (COMPRA Y VENTA CMR) (RECARGAS VIRTUALES)
                strMontoPagar = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 1); //Monto a pagar
                strFechaVencCuota =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 4); //Fecha de vencimiento cuota (venta CMR)
                strNumOperacCobranza =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 6); //Codigo de transaccion   (PAGO CLARO)
                strNumOperacAcreedor =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 7); //Codigo de transaccion interna de claro (PAGO CLARO)
                strFechaOrig =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 8); //Fecha origen usado para anulacion pago Ripley
                strMensaje =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 9); //Mensaje de la operaci�n devuelto por el Six
            }
            if (ConstantsRecaudacion.RCD_MODO_CONSULTA_SIX.equals(modo)) {
                strMontoPagar = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 1); //Monto a pagar
                strCodAuditoria = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 2); //Codigo Auditoria
                strNumRecibo = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 5); //Numero de recibo
            }
            if (ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(strResponseCode)) {
                log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                         ConstantsRecaudacion.RCD_SIX_RESP_OK + strResponseCode);
            } else {
                log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                         ConstantsRecaudacion.RCD_SIX_RESP_ERROR + strResponseCode);
                msj = true; //FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_PAGO_SIX_MSJ_COBRO , null);
            }
        }
        datosResp.add(rpt); //0  SI ES TRUE INDICA QUE EXISTE RESPUESTA DEL SIX
        datosResp.add(msj); //1  SI ES TRUE INDICA QUE EXISTE ERROR DEL SIX (RESPUESTA ERRONEA O SIMPLEMENTE NO HUBO RESPUESTA)
        datosResp.add(strResponseCode); //2  CODIGO DE RESPUESTA DEL SIX '00' = OK
        datosResp.add(strMontoPagar); //3  MONTO A PAGAR DE LA DEUDA
        datosResp.add(strCodAuditoria); //4  CODIGO DE AUDITORIA
        datosResp.add(strCodAutorizacion); //5  CODIGO DE AUTORIZACION
        datosResp.add(strFechaVencCuota); //6  FECHA VENCIMIENTO CUOTA PARA VENTA CMR
        datosResp.add(codTrssc.toString()); //7  CODIGO DE TRSSC EN ADM
        datosResp.add(strNumRecibo); //8  NUMERO DE RECIBO DE PAGO
        datosResp.add(strNumOperacCobranza); //9  NUMERO DE TRANSACCION
        datosResp.add(strNumOperacAcreedor); //10 NUMERO DE TRANSACCION INTERNA DE CLARO
        datosResp.add(strFechaOrig); //11 FECHA DE ORIGEN USADO EN ANULACION PAGO RIPLEY
        datosResp.add(strMensaje); //12 MENSAJE DE RESPUESTA DE SIX

        return datosResp;
    }

    /**
     * OBTENER EL ESTADO DE LA TRANSACCION PROCESADA POR EL DEMONIO
     * @author GFonseca
     * @since 08.08.2013
     */
    public String obtenerEstadoTrssc(Long codTrssc, String strModoRecau) throws Exception {
        String tmpEst = "";

        tmpEst = daoRecaudacion.obtenerEstadoTrssc(codTrssc, strModoRecau);

        return tmpEst;
    }

    /**
     * Permite actualizar el estado una recaudacion con el six
     * @author GFonseca
     * @since 09.08.2013
     */
    public void actualizarEstRecauTrsscSix(String codRecaudacion, String estRecauTrsscSix, String strCodAutoriz) {
        try {
            daoRecaudacion.actualizarEstRecauTrsscSix(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                      FarmaVariables.vCodLocal, codRecaudacion, estRecauTrsscSix,
                                                      strCodAutoriz);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
    }

    /**
     * Obtener el DNI de un usuario
     * @author GFonseca
     * @since 19.08.2013
     */
    public String obtenerDniUsuario(String numSecUsu) {
        String strDniUsuario = "";
        try {
            strDniUsuario = daoRecaudacion.obtenerDniUsuario(numSecUsu);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return strDniUsuario;

    }


    /**
     * Registrar datos de recaudacion en Fasa, para la conciliaci�n
     * @author GFonseca
     * @since 09.08.2013
     * @mod LLEIVA 06-Nov-2013 Cambio de tipos de datos a parametros de entrada
     */
    public String setDatosRecauConciliacion(String pPLOCAL, String pPID_VENDEDOR, String PFECHA_VENTA,
                                            double PMONTO_VENTA, double PNUM_CUOTAS, String PCOD_AUTORIZACION,
                                            String PTRACK2, String PCOD_AUTORIZACION_PRE, double PFPA_VALORXCUOTA,
                                            int PCAJA, String PTIPO_TRANSACCION, String PCODISERV,
                                            String PFPA_NUM_OBJ_PAGO, String PNOMBCLIE, long PVOUCHER,
                                            long PNRO_COMP_ANU, String PFECH_COMP_ANU, String PCODIAUTOORIG,
                                            double PFPA_TIPOCAMBIO, long PFPA_NROTRACE, int PCOD_ALIANZA,
                                            int PCOD_MONEDA_TRX, String PMON_ESTPAGO, String descProceso) {

        String vSalida = "";
        //ERIOS 2.3.1 Conciliacion offline de ventas y recaudacion
        String vConciliacionOnline = DlgProcesar.cargaIndConciliaconOnline();
        if (vConciliacionOnline.equals(FarmaConstants.INDICADOR_N)) {
            return vSalida;
        }

        String PLOCAL = getCodLocalMigra();
        String PID_VENDEDOR = obtenerDniUsuario(FarmaVariables.vNuSecUsu).trim();

        //Registra log conciliacion
        guardarLogConciliacion2(PID_VENDEDOR, PFECHA_VENTA, PMONTO_VENTA, PNUM_CUOTAS, PCOD_AUTORIZACION, PTRACK2,
                                PCOD_AUTORIZACION_PRE, PFPA_VALORXCUOTA, PCAJA, PTIPO_TRANSACCION, PCODISERV,
                                PFPA_NUM_OBJ_PAGO, PNOMBCLIE, PVOUCHER, PNRO_COMP_ANU, PFECH_COMP_ANU, PCODIAUTOORIG,
                                PFPA_TIPOCAMBIO, PFPA_NROTRACE, PCOD_ALIANZA, PCOD_MONEDA_TRX, PMON_ESTPAGO,
                                descProceso, vSalida, PLOCAL);

        try {
            //Registra conciliacion
            vSalida =
                    daoRecaudacion.setDatosRecauConciliacion(PLOCAL, PID_VENDEDOR, PFECHA_VENTA, PMONTO_VENTA, PNUM_CUOTAS,
                                                             PCOD_AUTORIZACION, PTRACK2, PCOD_AUTORIZACION_PRE,
                                                             PFPA_VALORXCUOTA, PCAJA, PTIPO_TRANSACCION, PCODISERV,
                                                             PFPA_NUM_OBJ_PAGO, PNOMBCLIE, PVOUCHER, PNRO_COMP_ANU,
                                                             PFECH_COMP_ANU, PCODIAUTOORIG, PFPA_TIPOCAMBIO,
                                                             PFPA_NROTRACE, PCOD_ALIANZA, PCOD_MONEDA_TRX,
                                                             PMON_ESTPAGO);
            if (!vSalida.equals("") && !vSalida.equals("OK")) {
                log.warn("Resultado de conciliacion [" + PVOUCHER + "]:" + vSalida);
                vSalida = "NOK";
            }
        } catch (Exception sqlE) {
            log.error("", sqlE);
            vSalida = "ERR";
        }

        //Acualiza respuesta al log de conciliacion
        try {
            String pvoucher = String.valueOf(PVOUCHER);
            daoRecaudacion.actualizarEstLogConciliacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                        FarmaVariables.vCodLocal, PCOD_AUTORIZACION, PFECHA_VENTA,
                                                        PTIPO_TRANSACCION, pvoucher, vSalida);
        } catch (Exception sqlE) {
            log.error("", sqlE);
        }
        return vSalida;
    }

    /**
     * Registrar datos de recaudacion en Fasa, para la conciliaci�n de recargas
     * @author GFonseca
     * @since 27.Agosto.2013
     */
    public String setDatosRecargaConciliacion(String PCL_COD_ID_CONCENTRADOR, String PCL_NUMERO_TELEFONO,
                                              String PCL_COD_AUTORIZACION, String PCL_COD_VENDEDOR,
                                              String PCL_FECHA_VENTA, String PCL_HORA_VENTA,
                                              String PCL_NUMERO_DOCUMENTO, String PCL_COD_COMERCIO,
                                              String PCL_COD_TERMINAL, String PCL_MONTO_VENTA,
                                              String PCL_ID_TRANSACCION) {
        String vSalida = "";
        try {
            vSalida =
                    daoRecaudacion.setDatosRecargaConciliacion(PCL_COD_ID_CONCENTRADOR, PCL_NUMERO_TELEFONO, PCL_COD_AUTORIZACION,
                                                               PCL_COD_VENDEDOR, PCL_FECHA_VENTA, PCL_HORA_VENTA,
                                                               PCL_NUMERO_DOCUMENTO, PCL_COD_COMERCIO,
                                                               PCL_COD_TERMINAL, PCL_MONTO_VENTA, PCL_ID_TRANSACCION);
        } catch (SQLException sqlE) {
            log.error("ERROR ES => " + sqlE.getMessage());
        }
        return vSalida;
    }


    /**
     * Obtener el c�digo homologo de un local
     * @author GFonseca
     * @since 19.08.2013
     */
    public String getCodLocalMigra() {
        String strCodLocalMigra = "";
        try {
            strCodLocalMigra =
                    daoRecaudacion.getCodLocalMigra(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return strCodLocalMigra;
    }

    /**
     * Retorna el monto total de recaudaciones asociadas a un movimiento de caja
     * @author GFonseca
     * @since 21.Agosto.2013
     */
    public String getMontoTotalRecaudacionByMovCaja(String strSecMovCaja) {
        String strMontoTot = "";
        try {
            strMontoTot =
                    daoRecaudacion.getMontoTotalRecaudacionByMovCaja(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                     FarmaVariables.vCodLocal, strSecMovCaja);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return strMontoTot;
    }


    /**
     * RETORNA DATOS DE UNA RECAUDACION VENTA CMR, PARA SU ANULACION
     * @author GFonseca
     * @since 04.09.2013
     */
    public ArrayList<ArrayList<String>> getDatosAnulacionVentaCMR(String strNumPedido) {
        ArrayList<ArrayList<String>> tmpArray = new ArrayList<>();
        try {
            tmpArray =
                    daoRecaudacion.getDatosAnulacionVentaCMR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                             strNumPedido);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return tmpArray;
    }


    /**
     * RETORNA DATOS DE UNA RECAUDACION VENTA CMR DESDE ADM, PARA SU ANULACION
     * @author GFonseca
     * @since 04.09.2013
     */
    public ArrayList<ArrayList<String>> getDatosAnulacionVentaCMR_SIX(String strCodTrsscSix) {
        ArrayList<ArrayList<String>> tmpArray = new ArrayList<>();
        try {
            tmpArray = daoRecaudacion.getDatosAnulacionVentaCMR_SIX(strCodTrsscSix);
        } catch (SQLException ex) {
            log.error("", ex);
        }
        return tmpArray;
    }

    /**
     * Obtiene el monto total de recaudacion por cierre de dia
     * @author ERIOS
     * @since 30.09.2013
     * @param strFechaCierreDia
     * @return
     */
    public String getMontoTotalRecaudacionByCierreDia(String strFechaCierreDia) throws Exception {
        String strMontoTot = "";
        daoRecaudacion.openConnection();
        try {
            strMontoTot =
                    daoRecaudacion.getMontoTotalRecaudacionByCierreDia(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                       FarmaVariables.vCodLocal, strFechaCierreDia);
            daoRecaudacion.commit();
        } catch (Exception e) {
            daoRecaudacion.rollback();
            throw e;
        }
        return strMontoTot;
    }

    /**
     * RETORNA UN LISTADO CONSOLIDADO DE LAS RECAUDACIONES PERTENECIENTES A UNA SECUENCIA DE MOVIMIENTO DE CAJA
     * @author LLEIVA
     * @since 25.Sep.2013
     */
    public ArrayList<ArrayList<String>> listarReporteCierreTurno(String strSecMovCaja) {
        ArrayList<ArrayList<String>> lstListado = null;
        try {
            lstListado = daoRecaudacion.listarReporteCierreTurno(strSecMovCaja);
        } catch (Exception e) {
            log.error("", e);
        }
        return lstListado;
    }

    public void guardarLogConciliacion(String codProceso, String descProceso, String estConciliacion) {
        try {
            daoRecaudacion.guardarLogConciliacion(codProceso, descProceso, estConciliacion);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private void guardarLogConciliacion2(String dniVendedor, String fechaVenta, double monto, double numCuotas,
                                         String codAutoriz, String track2, String codAutorizPre, double valorPorCuota,
                                         int caja, String tipoTrans, String codServ, String numObjPago,
                                         String nomCliente, long codVoucher, long numComprobAnulado,
                                         String fechaComprobAnulado, String codAutorizOrig, double tipoCambio,
                                         long numTrace, int codAlianza, int codMoneda, String monEstPago,
                                         String descProceso, String estConciliacion, String codLocalMigra) {
        try {
            daoRecaudacion.openConnection();
            daoRecaudacion.guardarLogConciliacion2(dniVendedor, fechaVenta, monto, numCuotas, codAutoriz, track2,
                                                   codAutorizPre, valorPorCuota, caja, tipoTrans, codServ, numObjPago,
                                                   nomCliente, codVoucher, numComprobAnulado, fechaComprobAnulado,
                                                   codAutorizOrig, tipoCambio, numTrace, codAlianza, codMoneda,
                                                   monEstPago, descProceso, estConciliacion, codLocalMigra);
            daoRecaudacion.commit();
        } catch (Exception ex) {
            daoRecaudacion.rollback();
            log.error("", ex);
        }
    }

    public ArrayList<ArrayList<String>> getListaConciliacionNOK() {
        ArrayList<ArrayList<String>> lstListado = null;

        try {
            lstListado = daoRecaudacion.getListaConciliacionNOK();
        } catch (Exception e) {
            log.error("", e);
        }
        return lstListado;
    }

    public void actualizaRegConciliacion(String codConciliacion) {
        try {
            daoRecaudacion.actualizaRegConciliacion(codConciliacion);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    public ArrayList<ArrayList<String>> obtenerDetallePedidoFomasPago(String strNumPedVta) {
        ArrayList<ArrayList<String>> lista = new ArrayList<>();
        try {
            lista = daoRecaudacion.obtenerDetallePedidoFomasPago(strNumPedVta);
        } catch (Exception ex) {
            log.error("", ex);
        }
        return lista;
    }

    public int getCodigoAlianzaConciliacion(String formaPago) {
        int codAlianza = 0;
        try {
            codAlianza = Integer.parseInt(daoRecaudacion.getCodigoAlianzaConciliacion(formaPago));
        } catch (Exception ex) {
            log.error("", ex);
        }
        return codAlianza;
    }

    public ArrayList<ArrayList<String>> getNumTarjCodAutoriz(String numPedido) {
        ArrayList<ArrayList<String>> lista = new ArrayList<>();
        try {
            lista = daoRecaudacion.getNumTarjCodAutoriz(numPedido);
        } catch (Exception ex) {
            log.error("", ex);
        }
        return lista;
    }

    public String getNumPedidoNegativo(String numPedido) {
        String numPedNegat = "";
        try {
            numPedNegat = daoRecaudacion.getNumPedidoNegativo(numPedido);
        } catch (Exception ex) {
            log.error("", ex);
        }
        return numPedNegat;
    }

    /**
     * Tipos de Cambio
     * @author ERIOS
     * @since 18.11.2013
     */
    public void obtenerTipoCambio() throws SQLException {

        String pFecha;

        pFecha = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);

        FarmaVariables.vTipCambio = daoRecaudacion.getTipoCambio(pFecha, "V");
        VariablesRecaudacion.vTipoCambioVenta = daoRecaudacion.getTipoCambio(pFecha, "V");
        VariablesRecaudacion.vTipoCambioCompra = daoRecaudacion.getTipoCambio(pFecha, "C");
    }

    /**
     * Tipos de Cambio
     * @author ERIOS
     * @since 18.11.2013
     */
    @Deprecated
    public void obtenerTipoCambio(String pTipoRcd) {
        String pFecha;
        try {
            pFecha = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
        } catch (SQLException e) {
            pFecha = "";
        }
        FarmaVariables.vTipCambio = daoRecaudacion.getTipoCambio(pFecha, "");
        VariablesRecaudacion.vTipoCambioVenta = daoRecaudacion.getTipoCambio(pFecha, "");
        VariablesRecaudacion.vTipoCambioCompra = daoRecaudacion.getTipoCambio(pFecha, pTipoRcd);
    }

    /**
     * Guarda log de conciliacion de recargas
     * @author ERIOS
     * @since 2.2.8
     * @param PCL_COD_ID_CONCENTRADOR
     * @param PCL_NUMERO_TELEFONO
     * @param PCL_COD_AUTORIZACION
     * @param PCL_COD_VENDEDOR
     * @param PCL_FECHA_VENTA
     * @param PCL_HORA_VENTA
     * @param PCL_NUMERO_DOCUMENTO
     * @param PCL_COD_COMERCIO
     * @param PCL_COD_TERMINAL
     * @param PCL_MONTO_VENTA
     * @param PCL_ID_TRANSACCION
     * @return
     */
    public String guardaLogConciliacionRecargas(String PCL_COD_ID_CONCENTRADOR, String PCL_NUMERO_TELEFONO,
                                                String PCL_COD_AUTORIZACION, String PCL_COD_VENDEDOR,
                                                String PCL_FECHA_VENTA, String PCL_HORA_VENTA,
                                                String PCL_NUMERO_DOCUMENTO, String PCL_COD_COMERCIO,
                                                String PCL_COD_TERMINAL, String PCL_MONTO_VENTA,
                                                String PCL_ID_TRANSACCION) {
        String vSalida = "";

        //1. Guarda datos en la log de recargas
        daoRecaudacion.guardarLogRecargas(VariablesCaja.vNumPedVta, PCL_COD_ID_CONCENTRADOR, PCL_NUMERO_TELEFONO,
                                          PCL_COD_AUTORIZACION, PCL_COD_VENDEDOR, PCL_FECHA_VENTA, PCL_HORA_VENTA,
                                          PCL_NUMERO_DOCUMENTO, PCL_COD_COMERCIO, PCL_COD_TERMINAL,
                                          FarmaUtility.getDecimalNumber(PCL_MONTO_VENTA), PCL_ID_TRANSACCION,
                                          FarmaVariables.vIdUsu);
        //2. Envia datos online
        String vConciliacionOnline = DlgProcesar.cargaIndConciliaconOnline();
        if (vConciliacionOnline.equals(FarmaConstants.INDICADOR_N)) {
            return vSalida;
        }
        vSalida =
                setDatosRecargaConciliacion(PCL_COD_ID_CONCENTRADOR, PCL_NUMERO_TELEFONO, PCL_COD_AUTORIZACION, PCL_COD_VENDEDOR,
                                            PCL_FECHA_VENTA, PCL_HORA_VENTA, PCL_NUMERO_DOCUMENTO, PCL_COD_COMERCIO,
                                            PCL_COD_TERMINAL, PCL_MONTO_VENTA, PCL_ID_TRANSACCION);
        //3. Actualiza estado
        daoRecaudacion.actualizarEstadoLogRecargas(VariablesCaja.vNumPedVta, vSalida, FarmaVariables.vIdUsu);

        return vSalida;
    }

    /**
     * Valida conexion con RAC
     * @author ERIOS
     * @since 2.3.4
     * @return
     */
    public boolean validarConexionRAC() throws Exception {
        boolean pResultado = false;
        String pIndConexion;
        pIndConexion = (new FarmaVentaCnxUtility()).getIndLineaRAC();
        if (pIndConexion.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
            log.debug("No existe linea se cerrara la conexion ...");
            pResultado = false;
        } else {
            pResultado = true;
        }
        return pResultado;
    }


    /**
     * Valida conexion con TICO
     * @author RHERRERA
     * @since 2.4.6
     * @return
     */
    public boolean validarConexionTICO() throws Exception {
        boolean pResultado = false;
        String pIndConexion;
        pIndConexion = (new FarmaVentaCnxUtility()).getIndLineaTICO();
        if (pIndConexion.trim().equalsIgnoreCase(FarmaConstants.INDICADOR_N)) {
            log.debug("No existe linea se cerrara la conexion ...");
            pResultado = false;
        } else {
            pResultado = true;
        }
        return pResultado;
    }

    public Long registrarTrsscPagoCitibank(String strTipoRecau, double dblTotalPagar, String strCodRecau,
                                           String strCodCliente, String strNroTarjeta, String strCodAutorizacion,
                                           String vIdUsu, String strDniUsu, double dblTipoCambio, String strTipoMoneda,
                                           int pRecaudOnline) throws Exception {
        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.registrarTrsscPagoCitibank(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                          strTipoRecau, dblTotalPagar, strCodRecau, strCodCliente,
                                                          strNroTarjeta, strCodAutorizacion, vIdUsu, strDniUsu,
                                                          dblTipoCambio, strTipoMoneda, pRecaudOnline);

        return tmpCodigo;
    }

    public Long anularTrsscPagoCitibank(String strTipoRecau, double dblTotalPagar, String strCodRecau,
                                        String strCodCliente, String strNroTarjeta, String strCodAutorizacion,
                                        String vIdUsu, String strDniUsu, double dblTipoCambio, String strTipoMoneda,
                                        String strCodRecauAnular, String strFechaRecauAnular,
                                        String strCodAutorizRecauAnular, int pRecaudOnline) throws Exception {
        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.anularTrsscPagoCitibank(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                       strTipoRecau, dblTotalPagar, strCodRecau, strCodCliente,
                                                       strNroTarjeta, strCodAutorizacion, vIdUsu, strDniUsu,
                                                       dblTipoCambio, strTipoMoneda, strCodRecauAnular,
                                                       strFechaRecauAnular, strCodAutorizRecauAnular, pRecaudOnline);

        return tmpCodigo;
    }

    public String getCodRecauAnul() throws Exception {
        String resultado = "";

        resultado =
                daoRecaudacion.getCodRecauAnul(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal);

        return resultado;
    }

    /**
     * Anulacion de las recaudacion.
     * @author KMONCADA
     * @since 11.06.2014
     * @return
     */
    public void anularRecaudaciones(Long codTrssc) throws Exception {

        daoRecaudacion.anulaTrsccPagoRecaudacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, codTrssc);

    }
    
    /**
     * @author ASOSA - INCASUR
     * @since 19/05/2016
     * @param pCodCliente
     * @param pCodServ
     * @return
     */
    public ArrayList<ArrayList<String>> listarRecibosPendientesFasaProd(String pCodCliente,
                                                                        String pCodServ) {
        ArrayList<ArrayList<String>> tmpArrayRcd = new ArrayList<>();
       
            tmpArrayRcd =
                    daoRecaudacion.listarRecibosPendientesFasaProd(pCodCliente, 
                                                                   pCodServ);
        return tmpArrayRcd;
    }
    
    /**
     * Registra una transaccion de consulta en el servidor ADM para la comunicacion con el SIX
     * @author AOVIEDO
     * @since 02/08/2017
     */
    public Long registrarTrsscConsultaFinanciero(String terminal, String nroDNI, 
                                                 String tipoProdServ,String tipoOperacionBFP) throws Exception {
        Long tmpCodigo = null;

        tmpCodigo =
                daoRecaudacion.registrarTrsscConsultaFinanciero(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                FarmaVariables.vCodLocal,
                                                                ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                                ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                                ConstantsRecaudacion.TRNS_CNSULTA_SRV,
                                                                ConstantsRecaudacion.TIPO_REC_FINANCIERO, terminal,
                                                                FarmaVariables.vDescCortaLocal, //comercio
                                                                FarmaVariables.vCodLocal, //sucursal
                                                                FarmaVariables.vDescCortaDirLocal, //ubicacion
                                                                nroDNI, tipoProdServ, FarmaVariables.vIdUsu,
                                                                tipoOperacionBFP);

        return tmpCodigo;
    }

    public ArrayList<ArrayList<String>> solicitaPagos_BancoFinanciero(Long tmpCodigo) throws Exception {
        ArrayList<ArrayList<String>> listaPagosTarjetaCredito = new ArrayList<>();
        listaPagosTarjetaCredito = daoRecaudacion.solicitaPagos_BancoFinanciero(tmpCodigo);
        return listaPagosTarjetaCredito;
    }
    
    /**
     * Registra una transaccion de recaudacion en el servidor ADM para la comunicacion con el SIX
     * @author AOVIEDO
     * @since 01/08/2017
     */
    public Long registrarTrsscPagoFinanciero(String terminal, String nroDNI, String nroTarjeta, 
                                             String monto, String tipoMoneda, int recaudOnline,
                                             double totalPagar,
                                             String siglaMoneda, String monMinPagar, String monMesPagar, 
                                             String monTotPagar, String redMinPagar, String redMesPagar, 
                                             String redTotPagar) throws Exception {
        Long tmpCodigo = null;
        tmpCodigo = daoRecaudacion.registrarTrsscPagoFinanciero(
                                                                FarmaVariables.vCodGrupoCia, 
                                                                FarmaVariables.vCodCia,
                                                                FarmaVariables.vCodLocal,
                                                                ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                                ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                                ConstantsRecaudacion.TRNS_PAGO_SRV_FINANCIERO,
                                                                ConstantsRecaudacion.TIPO_REC_FINANCIERO, 
                                                                nroTarjeta, 
                                                                FarmaUtility.getDecimalNumber(monto),
                                                                terminal,
                                                                FarmaVariables.vDescCortaLocal, //comercio
                                                                FarmaVariables.vDescCortaDirLocal, //ubicacion
                                                                "1", //nroCuotas
                                                                VariablesCaja.vNumCaja, //nroCaja
                                                                FarmaVariables.vNuSecUsu, //idCajero
                                                                FarmaVariables.vIdUsu, //usuCrea
                                                                terminal, //codRecau
                                                                nroDNI, 
                                                                VariablesRecaudacion.vTipoCambioVenta, //tipoCambio
                                                                tipoMoneda,
                                                                recaudOnline, 
                                                                totalPagar,
                                                                siglaMoneda,
                                                                monMinPagar,
                                                                monMesPagar,
                                                                monTotPagar,
                                                                redMinPagar,
                                                                redMesPagar,
                                                                redTotPagar
                                                                );
        return tmpCodigo;
    }
    
    /**
     * Permite preguntar el estado de la transaccion en ADM y obtener el resultado de la transaccion registrado por el demonio. Para Banco Financiero
     * @author AOVIEDO
     * @since 03/08/2017
     */
    public ArrayList<Object> obtenerRespuestaSixFinanciero(String modo, String operacion, Long codTrssc) throws Exception {
        boolean rpt = false;
        boolean msj = false;
        String strResponseCode = "";
        String strMontoPagar = "";
        String strCodAutorizacion = "";
        String strCodAuditoria = "";
        String strFechaVencCuota = "";
        String strNumOperacCobranza = "";
        String strNumOperacAcreedor = "";
        String strFechaOrig = "";
        String strMensaje = "";
        String strNumRecibo = "";

        ArrayList<Object> datosResp = new ArrayList<>();
        ArrayList<ArrayList<String>> datosRespSix = null;

        //ERIOS 2.4.0 Todas las recaudaciones son centralizadas
        String indicador = "";
        VariablesRecaudacion.vIndicadorSix = "";
        datosRespSix = UtilityRecaudacion.obtenerDatosTrsscSixFinanciero(codTrssc, modo, indicador);
        log.info("===>");
        System.out.println("--------------------------------------------");
        for (int i=0;i<datosRespSix.size();i++){
            System.out.println("["+i+"] "+datosRespSix.get(i).toString());
        }
        System.out.println("--------------------------------------------");
        indicador = VariablesRecaudacion.vIndicadorSix;

        if (indicador.equals(ConstantsRecaudacion.ESTADO_FIN_TAREA)) {
            log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                     ConstantsRecaudacion.RCD_PAGO_SIX_NO_HAY_RESP);
            msj = true; //FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_PAGO_SIX_MSJ_COBRO , null);
        } else if (ConstantsRecaudacion.ESTADO_SIX_TERMINADO.equals(ConstantsRecaudacion.vCOD_ESTADO_TRSSC_RECAU)) {
            log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                     ConstantsRecaudacion.RCD_PAGO_SIX_SI_HAY_RESP);
            rpt = true;

            if (ConstantsRecaudacion.RCD_MODO_PAGO_SIX.equals(modo) ||
                ConstantsRecaudacion.RCD_MODO_CONSULTA_SIX.equals(modo) ||
                ConstantsRecaudacion.RCD_MODO_RECARGA_SIX.equals(modo)) {
                strResponseCode = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 0); //Codigo de respuesta
                strCodAutorizacion =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 3); //Codigo Autorizacion (COMPRA Y VENTA CMR) (RECARGAS VIRTUALES)
                strMontoPagar = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 1); //Monto a pagar
                strFechaVencCuota =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 4); //Fecha de vencimiento cuota (venta CMR)
                strNumOperacCobranza =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 6); //Codigo de transaccion   (PAGO CLARO)
                strNumOperacAcreedor =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 7); //Codigo de transaccion interna de claro (PAGO CLARO)
                strFechaOrig =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 8); //Fecha origen usado para anulacion pago Ripley
                strMensaje =
                        FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 9); //Mensaje de la operaci�n devuelto por el Six
            }
            if (ConstantsRecaudacion.RCD_MODO_CONSULTA_SIX.equals(modo)) {
                strMontoPagar = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 1); //Monto a pagar
                strCodAuditoria = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 2); //Codigo Auditoria
                strNumRecibo = FarmaUtility.getValueFieldArrayList(datosRespSix, 0, 5); //Numero de recibo
            }
            if (ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(strResponseCode)) {
                log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                         ConstantsRecaudacion.RCD_SIX_RESP_OK + strResponseCode);
            } else {
                log.info(operacion + codTrssc + ConstantsRecaudacion.RCD_PAGO_SIX_CONCAT +
                         ConstantsRecaudacion.RCD_SIX_RESP_ERROR + strResponseCode);
                msj = true; //FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_PAGO_SIX_MSJ_COBRO , null);
            }
        }
        datosResp.add(rpt); //0  SI ES TRUE INDICA QUE EXISTE RESPUESTA DEL SIX
        datosResp.add(msj); //1  SI ES TRUE INDICA QUE EXISTE ERROR DEL SIX (RESPUESTA ERRONEA O SIMPLEMENTE NO HUBO RESPUESTA)
        datosResp.add(strResponseCode); //2  CODIGO DE RESPUESTA DEL SIX '00' = OK
        datosResp.add(strMontoPagar); //3  MONTO A PAGAR DE LA DEUDA
        datosResp.add(strCodAuditoria); //4  CODIGO DE AUDITORIA
        datosResp.add(strCodAutorizacion); //5  CODIGO DE AUTORIZACION
        datosResp.add(strFechaVencCuota); //6  FECHA VENCIMIENTO CUOTA PARA VENTA CMR
        datosResp.add(codTrssc.toString()); //7  CODIGO DE TRSSC EN ADM
        datosResp.add(strNumRecibo); //8  NUMERO DE RECIBO DE PAGO
        datosResp.add(strNumOperacCobranza); //9  NUMERO DE TRANSACCION
        datosResp.add(strNumOperacAcreedor); //10 NUMERO DE TRANSACCION INTERNA DE CLARO
        datosResp.add(strFechaOrig); //11 FECHA DE ORIGEN USADO EN ANULACION PAGO RIPLEY
        datosResp.add(strMensaje); //12 MENSAJE DE RESPUESTA DE SIX

        return datosResp;
    }
    
    /**
     * Obtiene una transaccion de respuesta de recaudacion en el servidor ADM para la comunicacion con el SIX Para Banco Financiero
     * @author GFonseca
     * @since 11.Julio.2013
     */
    public ArrayList<ArrayList<String>> obtenerDatosTrsscSixFinanciero(Long strCodRecauTrssc, String strModoRecau) throws Exception {
        ArrayList<ArrayList<String>> tmpArray = new ArrayList<>();

        tmpArray = daoRecaudacion.obtenerDatosTrsscSixFinanciero(strCodRecauTrssc, strModoRecau);

        return tmpArray;
    }

    public Long anularPagoTarj_Financiero(String msjSix_400, String estadoTrss, String codTipoTrss_Anul, String tipoRCD_Financiero,
                                          String nroTarjeta, String nroCuotas, String montoPagado, String vNumCaja,
                                          String vNuSecUsu, String mtvExtorno, String codRCD_Anul, String vDescCortaLocal,
                                          String vDescCortaDirLocal, String vIdUsu, String codRecauNegativo, String strDniUsu, double tipoCambio_vta,
                                          String codMoneda, String fechaRecauAnular, String codAutorizRecauAnular, int pRecaudOnline, double dblMontoPagado) {
    
        Long tmpCodigo = null;
        String strCodRecauAnular=codRCD_Anul;

        tmpCodigo =
                daoRecaudacion.anularPagoTarj_Financiero(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                                        msjSix_400, estadoTrss, codTipoTrss_Anul, tipoRCD_Financiero,
                                                        nroTarjeta, nroCuotas, montoPagado, vNumCaja, vNuSecUsu,
                                                        mtvExtorno, codRCD_Anul, vDescCortaLocal, vDescCortaDirLocal,
                                                        vIdUsu, codRecauNegativo, strDniUsu, tipoCambio_vta, codMoneda,
                                                        strCodRecauAnular, fechaRecauAnular, codAutorizRecauAnular,
                                                        dblMontoPagado, pRecaudOnline);

        return tmpCodigo;
    }

    /*
    private ArrayList<ArrayList<String>> consultaRptaAnulSix_financiero(Long pCodTrssc, String strModoRecau,
                                                                        int nroIntentos, int segEspere) {
        ArrayList<ArrayList<String>> rpta = new ArrayList<ArrayList<String>>();
        int intento = 1;
        int minAntes = Calendar.getInstance().getTime().getMinutes();
        int secAntes = Calendar.getInstance().getTime().getSeconds();
        int minDespues = minAntes;
        int secDespues = secAntes + segEspere;
        if (secDespues >= 60) {
            minDespues = minAntes + 1;
            if (minDespues >= 60) {
                minDespues = minDespues - 60;
            }
            secDespues = secDespues - 60;
        }

        try {
            boolean otraVez = true;
            while (otraVez == true) {
                log.info("=============================> INTENTO CONSULTA ANULACION: " + intento + " de " +
                         nroIntentos);
                minAntes = Calendar.getInstance().getTime().getMinutes();
                secAntes = Calendar.getInstance().getTime().getSeconds();
                if (minAntes == minDespues && secAntes == secDespues) {
                    intento++;
                }
                rpta=daoRecaudacion.obtenerDatosTrsscSixFinanciero(pCodTrssc, strModoRecau);
                System.out.println("====> RPTA ANUL FINACIERO: "+rpta);
            }
        } catch (Exception e) {
        }
        return null;
    }*/
    
    /**
     * Imprime Comprobante Recaudacion Banco Financiero
     * @author AOVIEDO
     * @since 08/08/2017
     */
    public void imprimirComprobantePagoRecaudacionFinanciero(String pNumeroRecaudacion,double tipoCambioBFP,String nroPrestamo) throws Exception {
        String CompPago;

        CompPago = daoRecaudacion.impCompPagoRecaudacionFinanciero(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, 
                                                                   FarmaVariables.vCodLocal, pNumeroRecaudacion,tipoCambioBFP,
                                                                   nroPrestamo);
        log.debug("Comprobante Recaudacion Banco Financiero: " + CompPago);

        //Impresion Original
        PrintConsejo.imprimirHtml(CompPago, VariablesPtoVenta.vImpresoraActual,
                                  VariablesPtoVenta.vTipoImpTermicaxIp);
        //Copia Impresion
        PrintConsejo.imprimirHtml(CompPago, VariablesPtoVenta.vImpresoraActual,
                                  VariablesPtoVenta.vTipoImpTermicaxIp);

    }
    
    public String recupera_msjError_BFP(Long tmpCodigo) throws Exception {
        String msjError=daoRecaudacion.recuperaMsjError_BancoFinanciero(tmpCodigo);
        return msjError;
    }

    public String recupera_NombreCliente_BFP(Long tmpCodigo) throws Exception {
        String nombreCliente=daoRecaudacion.recupera_NombreCliente_BFP(tmpCodigo);
        return nombreCliente;
    }
    
    /**
     * Recupera el estado de la transaccion SI o NO, cuando se de el responce_code "06-TRASS YA EXTORNADA" 
     * @author GFonseca
     * @since 25.Junio.2013
     */
    public String verificaEstado_Recaudacion_Anulado(String pNumeroRecaudacion) throws Exception {
        String resultado = "";

        resultado =
                daoRecaudacion.verificaEstado_Recaudacion_Anulado(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, 
                                                                  FarmaVariables.vCodLocal,pNumeroRecaudacion);
        return resultado;
    }

    public String recupera_NroCuotaPrestamo(Long tmpCodigo) {
        String nroCuota="";
        try {
            nroCuota = daoRecaudacion.recupera_NroCuotaPrestamo(tmpCodigo);
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return nroCuota;
    }

    public String verificaEstado_PagoSix(String tipRcdCod, String codSix, String codRecauAnular) throws Exception {
        String resultado = "";
        resultado =
                daoRecaudacion.verificaEstado_PagoSix(tipRcdCod, codSix,codRecauAnular);
        return resultado;
    }

    public ArrayList<ArrayList<String>>  verificaEstado_TrsscSix(String tipRcdCod, String codSix, String codRecauAnular) throws Exception {
        ArrayList<ArrayList<String>> tmpArray = new ArrayList<ArrayList<String>>();
        tmpArray=daoRecaudacion.verificaEstado_TrsscSix(tipRcdCod, codSix,codRecauAnular);
        return tmpArray;
    }

    public String actualizaEstado_Local(String codRpta,String tipRcdCod, String codSix, String codRecauAnular) throws Exception {
        String resultado = "";
        resultado =
                daoRecaudacion.actualizaEstado_Local(codRpta,tipRcdCod, codSix,codRecauAnular);
        return resultado;
    }

}
