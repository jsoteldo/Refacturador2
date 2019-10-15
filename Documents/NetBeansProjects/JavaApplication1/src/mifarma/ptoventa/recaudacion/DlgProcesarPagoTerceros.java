package mifarma.ptoventa.recaudacion;


import com.gs.mifarma.worker.JDialogProgress;

import java.awt.Frame;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JTextField;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.UtilityRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgProcesarPagoTerceros extends JDialogProgress {

    private static final Logger log = LoggerFactory.getLogger(DlgProcesarPagoTerceros.class);

    FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();

    private String strIndProc = "";
    private String strDniUsu = "99999999";

    //Pago
    private ArrayList<Object> tmpArrayCabRcd;
    private String strCodRecau = "";
    private FarmaTableModel tableModelDetallePago;
    private JLabel lblVuelto;
    private JTextField txtMontoPagadoDolares;
    private JTextField txtMontoPagado;

    //Anulacion
    private Frame myParentFrame;
    Long codTrsscAnulTemp = null;
    String numTarjeta;
    String numTelefono;
    String codSix;
    String montoPagado;
    String tipoRcdDesc;
    String codRecauAnular;
    String estTrsscSix;
    String tipRcdCod;
    String codMoneda;
    String fechaRecauAnular;
    String codAutorizRecauAnular;
    String fechaOrigen;

    //Consulta claro
    String terminal;
    String nroTelefono;
    String tipProdServ;
    ArrayList<Object> rptSix = null;
    private boolean bProcesarCobro = false;

    private Long codTrssc = null;
    
    //INI AOVIEDO 01/08/2017
    ArrayList<ArrayList<String>> tmpLista = new ArrayList<>();
    private String nroDNI;
    private String tipoOperacionBFP;
    private int cNroIntentos = 0;
    private int intento = 1;
    private int cSecEspere = 0;
    private boolean vProcesoConsulta;
    private boolean verificaEstado=false;
    private String rptaPruebaEstado="";
    private int codRptaPruebaEstado=0;
    
    String nroTarjeta;
    String monto;
    double totalPagar;
    String tipoMoneda;
    //FIN AOVIEDO 01/08/2017

    public DlgProcesarPagoTerceros() {
        super();
    }

    public DlgProcesarPagoTerceros(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            //jbInit();

        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void realizarProcesos() {
        FarmaUtility.centrarVentana(this);
        strDniUsu = facadeRecaudacion.obtenerDniUsuario(FarmaVariables.vNuSecUsu);
        //ERIOS 2.2.8 Control de errores	
        try {
            if(!verificaEstado){
                if (ConstantsRecaudacion.RCD_IND_PROCESO_PAGO.equals(strIndProc)) {
                    procesarPago();
                } else if (ConstantsRecaudacion.RCD_IND_PROCESO_ANULACION.equals(strIndProc)) {
                    procesarAnulacion();
                } else if (ConstantsRecaudacion.RCD_IND_PROCESO_CONSU_CLARO.equals(strIndProc) || 
                           ConstantsRecaudacion.RCD_IND_PROCESO_CONSU_FINANCIERO.equals(strIndProc)) {
                    this.setTitle("Consultando . . .");
                    procesarConsulta();
                }
            }else{
                if(ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(tipRcdCod)){
                    verificarEstado_PagoBFP();
                }else{
                    
                }
            }
        } catch (Exception e) {
            log.error("", e);
            //ERIOS 2.4.4 Anulacion automatica de recaudacion
            if (bProcesarCobro) {
                String msjError="";
                if (ConstantsRecaudacion.RCD_IND_PROCESO_PAGO.equals(strIndProc)) {
                    //kmoncada 11.06.2014 anulacion automatica de la recaudacion en casos de error.
                    try {
                        if (!facadeRecaudacion.validarConexionRAC() &&
                            (!ConstantsRecaudacion.TIPO_REC_TELETON.equals(tmpArrayCabRcd.get(4).toString()) ||
                             !ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(tmpArrayCabRcd.get(4).toString())) //ASOSA - 10/08/2016 - TELETON
                            ) {
                            msjError="No se realizo la anulacion de la recaudación porque no existe linea con el RAC.";
                        }
                        if (codTrssc != null) {
                            if (!ConstantsRecaudacion.TIPO_REC_TELETON.equals(tmpArrayCabRcd.get(4).toString()) ||
                                !ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(tmpArrayCabRcd.get(4).toString())) { //ASOSA - 10/08/2016 - TELETON
                                facadeRecaudacion.anularRecaudaciones(codTrssc);
                            }                            
                            facadeRecaudacion.anularRCDPend(strCodRecau, codTrssc);
                        }
                        msjError="Se produjo un error al realizar la recaudacion!\n" +
                            "Verifique el estado de la transaccion:\n" +
                            "Nro. Operacion: "+strCodRecau+"\n" +
                            "Nro. Trasaccion: "+codTrssc;
                    } catch (Exception ex) {
                        log.error("Error al realizar la anulacion automatica de la recaudacion.", ex);
                        msjError="Error al realizar la anulacion automatica de la recaudacion.";
                    }
                }
                FarmaUtility.showMessage(this,msjError,null);
                log.info("----------------------------------------------------------------");
                log.error("", e.getMessage());
                log.info("----------------------------------------------------------------");
                return;
            }else{
                FarmaUtility.showMessage(this,"Se produjo un error al realizar la recaudacion" +
                    "\nVerifique el estado en matriz." +
                    "\n(Consulte a mesa de ayuda)",null);//e.getMessage(), null);
                return;
            }
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    
    public void procesarPagoTerceros(ArrayList<Object> tmpArrayCabRcd, String strCodRecau,
                                     FarmaTableModel tableModelDetallePago, JLabel lblVuelto,
                                     JTextField txtMontoPagado, JTextField txtMontoPagadoDolares) {
        log.info("****************tmpArrayCabRcd*******************");
        for(int i=0; i<tmpArrayCabRcd.size();i++){
            log.info("["+i+"] "+tmpArrayCabRcd.get(i).toString());
        }
        log.info("*************************************************");
        log.info(" => strCodRecau: "+strCodRecau);
        log.info(" => lblVuelto: "+lblVuelto.getText());
        log.info(" => txtMontoPagadoDolares: "+txtMontoPagadoDolares.getText());
        log.info(" => txtMontoPagado: "+txtMontoPagado.getText());
        log.info("************tableModelDetallePago****************");
        for(int i=0; i<tableModelDetallePago.getRowCount();i++){
            log.info("===> Fila ["+i+"]");
            for(int j=0;j<tableModelDetallePago.getColumnCount();j++){
                log.info("["+i+"]["+j+"]"+tableModelDetallePago.getValueAt(i, j));
            }
        }
        log.info("*************************************************");
        this.tmpArrayCabRcd = tmpArrayCabRcd;
        this.strCodRecau = strCodRecau;
        this.tableModelDetallePago = tableModelDetallePago;
        this.lblVuelto = lblVuelto;
        this.txtMontoPagadoDolares = txtMontoPagadoDolares;
        this.txtMontoPagado = txtMontoPagado;
    }
    
    public void procesarPagoTerceros(ArrayList<Object> tmpArrayCabRcd, String strCodRecau, FarmaTableModel tableModelDetallePago,
                                     JTextField txtVuelto, JTextField txtMontoPagado, JTextField txtMontoPagadoDolares) {
        this.lblVuelto = new JLabel();
        this.txtMontoPagadoDolares = new JTextField();  
        this.txtMontoPagado = new JTextField();
        
        log.info("****************tmpArrayCabRcd*******************");
        for(int i=0; i<tmpArrayCabRcd.size();i++){
            log.info("["+i+"] "+tmpArrayCabRcd.get(i).toString());
        }
        log.info("*************************************************");
        log.info(" => strCodRecau: "+strCodRecau);
        log.info(" => lblVuelto: "+txtVuelto.getText());
        log.info(" => txtMontoPagadoDolares: "+txtMontoPagadoDolares.getText());
        log.info(" => txtMontoPagado: "+txtMontoPagado.getText());
        log.info("************tableModelDetallePago****************");
        for(int i=0; i<tableModelDetallePago.getRowCount();i++){
            log.info("===> Fila ["+i+"]");
            for(int j=0;j<tableModelDetallePago.getColumnCount();j++){
                log.info("["+i+"]["+j+"]"+tableModelDetallePago.getValueAt(i, j));
            }
        }
        log.info("*************************************************");
        
        double vuelto=Double.parseDouble(txtVuelto.getText().trim());
        double montoDolar=0.0;
        if(txtMontoPagadoDolares!=null && !txtMontoPagadoDolares.getText().isEmpty()){
            montoDolar=FarmaUtility.getDecimalNumber(txtMontoPagadoDolares.getText().trim());
        }
        double montoSoles=FarmaUtility.getDecimalNumber(txtMontoPagado.getText().trim());
        
        this.tmpArrayCabRcd = tmpArrayCabRcd;
        this.strCodRecau = strCodRecau;
        this.tableModelDetallePago = tableModelDetallePago;
        this.lblVuelto.setText(String.valueOf(vuelto));
        this.txtMontoPagadoDolares.setText(String.valueOf(montoDolar));
        this.txtMontoPagado.setText(String.valueOf(montoSoles));
        
    }
    
    private void procesarPago() throws Exception {
        //Long codTrssc = null;
        codTrssc = null;
        String strTotalPagar = "";
        ArrayList<Object> rptSix = null;
        String estTrsscSix = "";
        String PCOD_AUTORIZACION_TEMP = "";
        String PFPA_NROTRACE_TEMP = "";
        boolean bRpt;
        boolean bMsj;
        String strResponseCode = "";
        String strMontoPagar = "";
        String strCodAutorizacion = tmpArrayCabRcd.get(21).toString();
        String strCodAuditoria = "";
        String strFechaOrigen = "";
        String strEstCta = tmpArrayCabRcd.get(7).toString();
        String strTipoMoneda ="";
        
        String descProceso = "";
        String strTipoRecau = tmpArrayCabRcd.get(4).toString();
        //ERIOS 2.4.1 Indicador de recaudacion centralizada
        int pRecaudOnline = DlgProcesar.cargaIndRecaudacionCentralizada();
        
        if(ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(strTipoRecau)){
            strTipoMoneda = VariablesRecaudacion.vTipoMonedaOrigen;
        }else{
            strTipoMoneda = tmpArrayCabRcd.get(9).toString();
        }
        

        boolean vIsFinanciero = false;
        //ERIOS 2.3.3 Valida conexion con RAC
              
        if(!ConstantsRecaudacion.TIPO_REC_TELETON.equals(tmpArrayCabRcd.get(4).toString()) ||   //ASOSA - 12/08/2016 - TELETON
           !ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(tmpArrayCabRcd.get(4).toString())){  //ASOSA - 04/09/2017 - TELEROJA
            if (!facadeRecaudacion.validarConexionRAC()) {
                return;
            }
        }  
        

        if (ConstantsRecaudacion.TIPO_REC_CMR.equals(strTipoRecau) ||
            ConstantsRecaudacion.TIPO_REC_CLARO.equals(strTipoRecau) ||
            ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(strTipoRecau) ||
            ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(strTipoRecau)) {


            if (ConstantsRecaudacion.TIPO_REC_CMR.equals(strTipoRecau)) {

                strTotalPagar = VariablesCaja.vValTotalPagar;
                String strNumComp = FarmaVariables.vCodLocal + strCodRecau;
                String nroCuotas = "1";
                descProceso = "RPC";
                double dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                codTrssc =
                        facadeRecaudacion.registrarTrsscPagoDeudaCMR(tmpArrayCabRcd.get(3).toString(), //numero de tarjeta
                            strTotalPagar, // monto a pagar
                            strCodRecau, //terminal: Identificamos el terminal con el numero de recaudacion
                            FarmaVariables.vDescCortaLocal, // comercio
                            FarmaVariables.vDescCortaDirLocal, //ubicacion
                            nroCuotas, // *TODO Uso futuro //cuotas
                            VariablesCaja.vNumCaja, FarmaVariables.vNuSecUsu, FarmaVariables.vIdUsu, strCodRecau,
                            strDniUsu, VariablesRecaudacion.vTipoCambioVenta, strTipoMoneda, dblTotalPagar,
                            pRecaudOnline);
                //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
                if (codTrssc == null) {
                    FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                    return;
                }
                rptSix =
                        facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, ConstantsRecaudacion.RCD_PAGO_SIX_CMR,
                                                              codTrssc);

            } else if (ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(strTipoRecau)) {
                //FarmaUtility.showMessage(this, "opcion no disponible." , null);
                strTotalPagar = VariablesCaja.vValTotalPagar;
                String strNumComp = FarmaVariables.vCodLocal + strCodRecau;
                String nroCuotas = "1";
                descProceso = "RPR";
                double dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                codTrssc =
                        facadeRecaudacion.registrarTrsscPagoDeudaRipley(tmpArrayCabRcd.get(3).toString(), //numero de tarjeta
                            strTotalPagar, // monto a pagar
                            strCodRecau, //terminal: Identificamos el terminal con el numero de recaudacion
                            FarmaVariables.vDescCortaLocal, // comercio
                            FarmaVariables.vDescCortaDirLocal, //ubicacion
                            nroCuotas, // *TODO Uso futuro //cuotas
                            VariablesCaja.vNumCaja, FarmaVariables.vNuSecUsu, FarmaVariables.vIdUsu, strCodRecau,
                            strDniUsu, VariablesRecaudacion.vTipoCambioVenta, strTipoMoneda, dblTotalPagar,
                            pRecaudOnline);
                //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
                if (codTrssc == null) {
                    FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                    return;
                }
                rptSix =
                        facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, ConstantsRecaudacion.RCD_PAGO_SIX_RIPLEY,
                                                              codTrssc);

            } else if (ConstantsRecaudacion.TIPO_REC_CLARO.equals(strTipoRecau)) {
                //ERIOS 23.10.2013 Se calcula el monto a abonar
                double dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                double dblMontoSoles =
                    FarmaUtility.getDecimalNumber(txtMontoPagado.getText()) + (FarmaUtility.getDecimalNumber(txtMontoPagadoDolares.getText()) *
                                                                               FarmaUtility.getDecimalNumber(VariablesCaja.vValTipoCambioPedido));
                if (dblTotalPagar <= dblMontoSoles) {
                    strTotalPagar = FarmaUtility.formatNumber(dblTotalPagar);
                } else {
                    strTotalPagar = FarmaUtility.formatNumber(dblMontoSoles);
                }
                descProceso = "RPS";
                dblTotalPagar = FarmaUtility.getDecimalNumber(strTotalPagar);
                codTrssc =
                        facadeRecaudacion.registrarTrsscPagoDeudaClaro(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                       FarmaVariables.vCodLocal,
                                                                       ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                                       ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                                       ConstantsRecaudacion.TRNS_PAG_PRE_AUTORI_SRV,
                                                                       //tipo transaccion
                            strTipoRecau, //tipo recaudacion
                            strTotalPagar, //monto
                            strCodRecau, //terminal: Identificamos la transaccion con el numero de recaudacion
                            FarmaVariables.vDescCortaLocal, // comercio
                            FarmaVariables.vDescCortaDirLocal, //ubicacion
                            tmpArrayCabRcd.get(8).toString(), // telefono
                            tmpArrayCabRcd.get(24).toString(), //tipo producto/servicio
                            tmpArrayCabRcd.get(25).toString(),
                            // número de recibo de pago
                            FarmaVariables.vIdUsu, strCodRecau, strDniUsu, VariablesRecaudacion.vTipoCambioVenta,
                            strTipoMoneda, dblTotalPagar, pRecaudOnline);
                //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
                if (codTrssc == null) {
                    FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                    return;
                }
                rptSix =
                        facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, ConstantsRecaudacion.RCD_PAGO_SIX_CLARO,
                                                              codTrssc);
            } else if (ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(strTipoRecau)) {
                
                vIsFinanciero = true;
                double dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                double dblMontoSoles =
                    FarmaUtility.getDecimalNumber(txtMontoPagado.getText()) + (FarmaUtility.getDecimalNumber(txtMontoPagadoDolares.getText()) *
                                                                               FarmaUtility.getDecimalNumber(VariablesCaja.vValTipoCambioPedido));
                if (dblTotalPagar <= dblMontoSoles) {
                    strTotalPagar = FarmaUtility.formatNumber(dblTotalPagar);
                } else {
                    strTotalPagar = FarmaUtility.formatNumber(dblMontoSoles);
                }
                descProceso = "RPF";
                dblTotalPagar = FarmaUtility.getDecimalNumber(strTotalPagar);
                
                codTrssc = facadeRecaudacion.registrarTrsscPagoFinanciero("0"+FarmaVariables.vCodLocal,//es Termimal, era: strCodRecau, 
                                                                          tmpArrayCabRcd.get(34).toString(), //nroTarjeta
                                                                          tmpArrayCabRcd.get(3).toString(), //nroDNI
                                                                          tmpArrayCabRcd.get(11).toString(), //monto
                                                                          VariablesRecaudacion.vTipoMonedaOrigen,
                                                                          //tmpArrayCabRcd.get(9).toString(), //tipoMoneda
                                                                          pRecaudOnline,
                                                                          dblTotalPagar,
                                                                          tmpArrayCabRcd.get(27).toString(), //siglaMoneda
                                                                          tmpArrayCabRcd.get(28).toString(), //monMinPagar
                                                                          tmpArrayCabRcd.get(29).toString(), //monMesPagar 
                                                                          tmpArrayCabRcd.get(30).toString(), //monTotPagar
                                                                          tmpArrayCabRcd.get(31).toString(), //redMinPagar
                                                                          tmpArrayCabRcd.get(32).toString(), //redMesPagar 
                                                                          tmpArrayCabRcd.get(33).toString() //redTotPagar
                                                                          );
                //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
                if (codTrssc == null) {
                    FarmaUtility.showMessage(this, "Ocurrió un error al registrar la transacción de "+VariablesRecaudacion.RAZON_SOCIAL_BFP, null);
                    return;
                }
                rptSix =
                        facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, 
                                                              ConstantsRecaudacion.RCD_PAGO_SIX_FINANCIERO, codTrssc);
                String responseCode = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPONSE_CODE);
                if (ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(responseCode)) {
                    log.info("ES PAGO FINANCIERO >>> "+codTrssc);
                    VariablesRecaudacion.vNombre_Cliente_BFP = facadeRecaudacion.recupera_NombreCliente_BFP(codTrssc);
                    if(Integer.parseInt(VariablesRecaudacion.vTipoOperacion_BFP)>=50)
                        VariablesRecaudacion.vCuotaPrestamo_BFP = facadeRecaudacion.recupera_NroCuotaPrestamo(codTrssc);
                    
                    log.info("-------------------------------");
                    log.info("COD TRSSC SIX:  >>> -"+codTrssc.toString()+"-" );
                    log.info("COD_RECUAUDACION:  >>> -"+VariablesRecaudacion.vCodCabRecau+"-" );
                    log.info("CLIENTE RECAUD BFP:  >>> +"+VariablesRecaudacion.vNombre_Cliente_BFP+"-");
                    log.info("NRO CUOTA PRESTAMO:  >>> -"+VariablesRecaudacion.vCuotaPrestamo_BFP+"-" );
                    log.info("-------------------------------");
                    
                    UtilityRecaudacion.saveCliente_CuotaPrestamoBFP(codTrssc.toString(),
                                                                    VariablesRecaudacion.vCodCabRecau,
                                                                    VariablesRecaudacion.vNombre_Cliente_BFP,
                                                                    VariablesRecaudacion.vCuotaPrestamo_BFP);                    
                }
            }

            bRpt = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPUESTA);
            bMsj = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MSJ);
            strResponseCode = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPONSE_CODE);
            strMontoPagar = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MONTO_PAGAR);
            strCodAuditoria = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUDITORIA);
            strCodAutorizacion =
                    (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUTORIZ); // SE GUARDA EN LA CABECERA DE RECAUDACION PARA COMPRA Y VENTA CMR
            if(!vIsFinanciero)
                codTrssc = new Long(rptSix.get(7).toString());
            
            strFechaOrigen = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_FECHA_ORIG);

            if (ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(strResponseCode)) {
                estTrsscSix = ConstantsRecaudacion.RCD_PAGO_SIX_EST_TRSSC_CORRECTA;
                PCOD_AUTORIZACION_TEMP = strCodAutorizacion;
                PFPA_NROTRACE_TEMP = UtilityRecaudacion.obtenerNroTraceConciliacion(codTrssc.toString());
                bProcesarCobro = true;
            } else {
                estTrsscSix = ConstantsRecaudacion.RCD_PAGO_SIX_EST_TRSSC_FALLIDA;
                if(VariablesRecaudacion.vMensajeError==null || VariablesRecaudacion.vMensajeError.trim().equalsIgnoreCase("")){
                    try {
                        String msjError = facadeRecaudacion.recupera_msjError_BFP(codTrssc);
                        if(msjError!=null){
                            VariablesRecaudacion.vMensajeError=msjError;
                        }else{
                            VariablesRecaudacion.vMensajeError="No se obtuvo respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP;
                        }
                    } catch (Exception f) {
                        System.out.println("*****************************************************");
                        log.info("Error: "+f.getMessage());
                        VariablesRecaudacion.vMensajeError="Error al consultar respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP+"...\n"+f.getMessage();
                        System.out.println("*****************************************************");
                    }
                }
            }

        } else { //Recaudaciones que no pasan por el six
            double dblTotalPagar = 0.0;
            PFPA_NROTRACE_TEMP = UtilityRecaudacion.obtenerNroTraceConciliacion(strCodRecau);
            if (ConstantsRecaudacion.TIPO_REC_CITI.equals(strTipoRecau)) {
                strCodAutorizacion = FarmaVariables.vCodLocal + strCodRecau;
                dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                descProceso = "RPT";
            } if (ConstantsRecaudacion.TIPO_REC_TELETON.equals(strTipoRecau)) { //ASOSA - 10/08/2016 - TELETON
                strCodAutorizacion = FarmaVariables.vCodLocal + strCodRecau;
                dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                descProceso = "DTT";
            } else if (ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(strTipoRecau)) { //ASOSA - 10/08/2016 - TELETON
                strCodAutorizacion = FarmaVariables.vCodLocal + strCodRecau;
                dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                descProceso = "RCR";
            } else if(ConstantsRecaudacion.TIPO_REC_RAIZ.equals(strTipoRecau)){ //ASOSA - 06/08/2015 - RAIZ
                dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                descProceso = "RRA";
                pRecaudOnline = 1;  //TODOS LOS PAGOS VAN A RAC PARA RAIZ
            } else if(ConstantsRecaudacion.TIPO_REC_INCASUR.equals(strTipoRecau)){ //ASOSA - 17/05/2016 - INCASUR
                dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                descProceso = "RIS";    //ESTA ES SOLO UNA DESCRIPCION QUE HACE TIEMPO SE USABA PERO YA NO PORQUE AHORA SE MANEJA POR CODIGOS Y HACIA RAC
                pRecaudOnline = 1;  //TODOS LOS PAGOS VAN A RAC PARA INCASUR
            } else {
                dblTotalPagar = FarmaUtility.getDecimalNumber(VariablesCaja.vValTotalPagar);
                descProceso = "RPP";
            }
            bProcesarCobro = true;

            //ERIOS 2.4.0 Recaudacon Citibank centralizada
            if(ConstantsRecaudacion.TIPO_REC_TELETON.equals(tmpArrayCabRcd.get(4).toString()) ||    //ASOSA - 10/08/2016 - TELETON
               ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(tmpArrayCabRcd.get(4).toString())){   //ASOSA - 04/09/2017 - TELEROJA
                codTrssc = ConstantsRecaudacion.COD_TRSSC_SIN_RAC;
            } else {
                codTrssc = facadeRecaudacion.registrarTrsscPagoCitibank(strTipoRecau, //tipo recaudacion
                            dblTotalPagar, //monto
                            strCodRecau, //terminal: Identificamos la transaccion con el numero de recaudacion
                            tmpArrayCabRcd.get(8).toString(), // cod cliente
                            tmpArrayCabRcd.get(3).toString(), //numero de tarjeta
                            strCodAutorizacion, //codigoautorizacion
                            FarmaVariables.vIdUsu, strDniUsu, VariablesRecaudacion.vTipoCambioVenta, strTipoMoneda,
                            pRecaudOnline);
            }
            //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
            if (codTrssc == null) {
                FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                return;
            }
        }
        
        //bProcesarCobro = true; //BORRAR BORRAR BORRAR AOVIEDO

        if (bProcesarCobro) {

            //Grabar forma de pago Recaudacion
            ArrayList tmpArray = new ArrayList();
            ArrayList<Object> tmpColm = null;
            ArrayList<ArrayList<Object>> arrayFormasPago = new ArrayList<>();
            tmpArray = tableModelDetallePago.data;
            String vCodMoneda = "";
            double vTipoCambio = 0.00;
            String vMontoPagado = "0.00";
            String vTotalPagado = "0.00";
            for (int i = 0; i < tmpArray.size(); i++) {
                //ERIOS 22.11.2013 Tipo cambio compra
                vCodMoneda = (((ArrayList)tmpArray.get(i)).get(6)).toString();
                vMontoPagado = (((ArrayList)tmpArray.get(i)).get(4)).toString();
                vTipoCambio = VariablesRecaudacion.vTipoCambioVenta;
                vTotalPagado = "0.00";
                if (strEstCta.equals(ConstantsRecaudacion.EST_CTA_DOLARES) &&
                    vCodMoneda.equals(ConstantsCaja.EFECTIVO_SOLES)) {
                    vTipoCambio = VariablesRecaudacion.vTipoCambioCompra;
                }

                if (vCodMoneda.equals(ConstantsCaja.EFECTIVO_SOLES)) {
                    vTotalPagado = vMontoPagado;
                } else {
                    vTotalPagado =
                            FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(vMontoPagado) * vTipoCambio);
                }

                tmpColm = new ArrayList<>();
                tmpColm.add(FarmaVariables.vCodGrupoCia);
                tmpColm.add(FarmaVariables.vCodCia);
                tmpColm.add(FarmaVariables.vCodLocal);
                tmpColm.add(strCodRecau);
                tmpColm.add(((ArrayList)tmpArray.get(i)).get(0));
                tmpColm.add(((ArrayList)tmpArray.get(i)).get(4)); //MONTO PAGADO    cImp_Total_in
                tmpColm.add(vCodMoneda); //MONEDA
                tmpColm.add(vTipoCambio);
                tmpColm.add(((ArrayList)tmpArray.get(i)).get(7)); //VUELTO
                tmpColm.add(vTotalPagado); //TOTAL PAGADO  cIm_Total_Pago_in (total soles)
                tmpColm.add(obtenerFecha());
                tmpColm.add(FarmaVariables.vIdUsu);
                tmpColm.add(""); //12
                tmpColm.add(""); //13
                arrayFormasPago.add(tmpColm);
            }

            boolean bCobro =
                facadeRecaudacion.grabaFormPagoRecau(arrayFormasPago, lblVuelto.getText().trim(), strCodAutorizacion,
                                                     codTrssc,
                                                     ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(strTipoRecau) ||
                                                     ConstantsRecaudacion.TIPO_REC_CMR.equals(strTipoRecau) ||
                                                     ConstantsRecaudacion.TIPO_REC_CLARO.equals(strTipoRecau) ||
                                                     ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(strTipoRecau) ? //AOVIEDO 03/08/2017
                                                     estTrsscSix : "", strFechaOrigen);

            //bCobro = true;//BORRAR BORRAR BORRAR AOVIEDO
            
            if (bCobro) {
                //GFonseca 26.06.2013 Se agrega logica para actualizar el monto pagado, para el caso de prestamos citibank.
                String strMontoCobrado = "";
                String strMontoMonedaCobrado = "";
                if (ConstantsRecaudacion.TIPO_REC_PRES_CITI.equals(strTipoRecau) ||
                    ConstantsRecaudacion.TIPO_REC_RAIZ.equals(strTipoRecau) || //ASOSA - 06/08/2015 - RAIZ
					ConstantsRecaudacion.TIPO_REC_INCASUR.equals(strTipoRecau) || //ASOSA - 17/05/2016 - INCASUR
                    ConstantsRecaudacion.TIPO_REC_TELETON.equals(strTipoRecau) || //ASOSA - 10/08/2016 - TELETON
                    ConstantsRecaudacion.TIPO_REC_CRUZ_ROJA.equals(strTipoRecau) || //ASOSA - 04/09/2017 - TELEROJA
                    ConstantsRecaudacion.TIPO_REC_CLARO.equals(strTipoRecau) ||
                    strEstCta.equals(ConstantsRecaudacion.EST_CTA_DOLARES)) {
                    ArrayList<String> aMontoCobrado = facadeRecaudacion.actualizarMontoCobradoPresCiti(strCodRecau);
                    strMontoCobrado = aMontoCobrado.get(0);
                    strMontoMonedaCobrado = aMontoCobrado.get(1);
                }

                //Imprimir
                if(ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(strTipoRecau)){
                    facadeRecaudacion.imprimirComprobantePagoRecaudacionFinanciero(strCodRecau,
                                                                                   VariablesRecaudacion.vTipoCambioBFP,
                                                                                   VariablesRecaudacion.vCuotaPrestamo_BFP);
                }else{
                    facadeRecaudacion.imprimirComprobantePagoRecaudacion(strCodRecau);
                }

                //Abrir Gabeta
                UtilityCaja.abrirGabeta(myParentFrame, false);
                //MUestra el mensaje: RCD_PAGO_SIX_MSJ_COBRO_EXITO = "La Recaudacion se realizo satisfactoriamente."
                //antes "Guardado con exito"
                FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_PAGO_SIX_MSJ_COBRO_EXITO, null);

            }
        } else {

            if (ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_NO_RESPUESTA, null);
            } else if (strResponseCode.equals("91") || strResponseCode.equals("94")) {
                if (ConstantsRecaudacion.TIPO_REC_CLARO.equals(strTipoRecau)) {
                    FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_CLARO_SERV_INACTIVO, null);
                } else if (ConstantsRecaudacion.TIPO_REC_CMR.equals(strTipoRecau)) {
                    FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_CMR_SERV_INACTIVO, null);
                } else {
                    FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_PAGO_SIX_MSJ_COBRO_FALLIDO, null);
                }
            } else if (!ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                //ERIOS 2.2.9 Mensaje del operador
                if(ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(strTipoRecau)){
                        try {
                            String msjError = facadeRecaudacion.recupera_msjError_BFP(codTrssc);
                            if(msjError!=null){
                                VariablesRecaudacion.vMensajeError=msjError;
                            }else{
                                VariablesRecaudacion.vMensajeError="No se obtuvo respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP;
                            }
                        } catch (Exception f) {
                            System.out.println("*****************************************************");
                            log.info("Error: "+f.getMessage());
                            VariablesRecaudacion.vMensajeError="Error al consultar respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP+"...\n"+f.getMessage();
                            System.out.println("*****************************************************");
                        }
                    FarmaUtility.showMessage(this, "[Mensaje Operador]" + " :\n" +
                            VariablesRecaudacion.vMensajeError, null);
                }else{
                    FarmaUtility.showMessage(this, "[Mensaje Operador]" + " :\n" +
                            (String)rptSix.get(12), null);
                }
            } else {
                FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_PAGO_SIX_MSJ_COBRO_FALLIDO, null);
            }
        }
        cerrarVentana(true);

    }
/*
myParentFrame, 
numTarjeta, 
numTelefono, 
codSix,
montoPagado, 
tipoRcdDesc, 
codRecauAnular,
estTrsscSix, 
tipRcdCod, 
codMoneda,
fechaRecauAnular,
codAutorizRecauAnular,
fechaOrigen 
*/
    public void procesarAnulacionTerceros(Frame myParentFrame, 
                                          String numTarjeta, 
                                          String numTelefono,
                                          String codSix,
                                          String montoPagado,
                                          String tipoRcdDesc, 
                                          String codRecauAnular,
                                          String estTrsscSix, 
                                          String tipRcdCod, 
                                          String codMoneda,
                                          String fechaRecauAnular, 
                                          String codAutorizRecauAnular,
                                          String fechaOrigen) {
        
        this.myParentFrame = myParentFrame;
        this.numTarjeta = numTarjeta;
        this.numTelefono = numTelefono;
        this.codSix = codSix;
        this.montoPagado = montoPagado;
        this.tipoRcdDesc = tipoRcdDesc;
        this.codRecauAnular = codRecauAnular;
        this.estTrsscSix = estTrsscSix;
        this.tipRcdCod = tipRcdCod;
        this.codMoneda = codMoneda;
        this.fechaRecauAnular = fechaRecauAnular;
        this.codAutorizRecauAnular = codAutorizRecauAnular;
        this.fechaOrigen = fechaOrigen;
    }


    public void procesarAnulacion() throws Exception {

        String codRecauNegativo = "";

        //VARIABLES PARA LA CONCILIACION
        //ERIOS 2.3.1 Conciliacion offline

        //String descProceso = "";

        codRecauNegativo = facadeRecaudacion.getCodRecauAnul();
        if (codRecauNegativo.equals("")) {
            FarmaUtility.showMessage(this, "Hubo un error al recuperar codigo de anulacion.", null);
            return;
        }

        double dblMontoPagado = FarmaUtility.getDecimalNumber(montoPagado);

        //ERIOS 2.4.1 Indicador de recaudacion centralizada
        int pRecaudOnline = DlgProcesar.cargaIndRecaudacionCentralizada();

        //ERIOS 2.3.3 Valida conexion con RAC
        if (!facadeRecaudacion.validarConexionRAC()) {
            return;
        }

        //Long codTrssc = null;
        codTrssc = null;
        if (ConstantsRecaudacion.TIPO_REC_CMR.equals(tipRcdCod) ||
            ConstantsRecaudacion.TIPO_REC_CLARO.equals(tipRcdCod) ||
            ConstantsRecaudacion.TIPO_REC_FINANCIERO.equals(tipRcdCod) ||
            ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(tipRcdCod)) {

            ArrayList<Object> rptSix = null;
            boolean bRpt;
            boolean bMsj;
            String strResponseCode = "";
            String strMontoPagar = "";
            String strCodAutorizacionSix = "";
            String strCodAuditoria = "";
            String arrayDatosTrssc[] = new String[2];
            String srtEstTrssc = "";
            String tmpEst =
                facadeRecaudacion.obtenerEstadoTrssc(new Long(codSix), ConstantsRecaudacion.RCD_MODO_CONSULTA_SIX);
            arrayDatosTrssc = tmpEst.split(",");
            srtEstTrssc = arrayDatosTrssc[0].trim(); //Estado OK / FA

            if (ConstantsRecaudacion.RCD_PAGO_SIX_EST_TRSSC_CORRECTA.equals(srtEstTrssc)) {
                if (tipRcdCod.equals(ConstantsRecaudacion.TIPO_REC_CMR)) {
                    String numCuota = "1";
                    String motivoExtorno = "85";
                    codTrssc =
                            facadeRecaudacion.anularPagoTarjetaCMR(ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                                   ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                                   ConstantsRecaudacion.TRNS_ANU_PAG_TARJ,
                                                                   ConstantsRecaudacion.TIPO_REC_CMR, 
                                                                   numTarjeta,
                                                                   numCuota, 
                                                                   montoPagado, 
                                                                   VariablesCaja.vNumCaja,
                                                                   FarmaVariables.vNuSecUsu, 
                                                                   motivoExtorno,
                                                                   codAutorizRecauAnular, 
                                                                   codRecauAnular,
                                                                   FarmaVariables.vDescCortaLocal,
                                                                   FarmaVariables.vDescCortaDirLocal,
                                                                   FarmaVariables.vIdUsu, 
                                                                   codRecauNegativo, 
                                                                   strDniUsu,
                                                                   VariablesRecaudacion.vTipoCambioVenta, 
                                                                   codMoneda,
                                                                   codRecauAnular,
                                                                   //Anulacion-Nro de Comprobante origen
                                                                   fechaRecauAnular, //Anulacion-Fecha Origen del Comprobante
                                                                   codAutorizRecauAnular, 
                                                                   dblMontoPagado,
                                                                   pRecaudOnline);
                    //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
                    if (codTrssc == null) {
                        FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                        return;
                    }
                    rptSix =
                            facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, ConstantsRecaudacion.RCD_ANU_PAGO_SIX_CMR,
                                                                  codTrssc);
                    bRpt = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPUESTA);
                    bMsj = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MSJ);
                    strResponseCode = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPONSE_CODE);
                    strMontoPagar = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MONTO_PAGAR);
                    strCodAuditoria = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUDITORIA);
                    strCodAutorizacionSix =
                            (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUTORIZ); // SE GUARDA EN LA CABECERA DE RECAUDACION PARA COMPRA Y VENTA CMR

                    if (bRpt && ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(strResponseCode)) {
                        //se genera el pedido negativo
                        facadeRecaudacion.anularRecaudacion(codRecauAnular, VariablesCaja.vNumCaja,
                                                            FarmaVariables.vIdUsu, codTrssc, codRecauNegativo);

                        //GFONSECA 27.10.2013 Imprimir anulacion de recaudacion
                        if (!codRecauNegativo.equals("")) {
                            facadeRecaudacion.imprimirComprobanteAnulRecaudacion(codRecauNegativo);
                        }

                        //Abrir Gabeta
                        UtilityCaja.abrirGabeta(myParentFrame, false);

                        FarmaUtility.showMessage(this, "La recaudación fue anulada.", null);

                    } else {
                        if (ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                            FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_NO_RESPUESTA, null);
                        } else if (ConstantsRecaudacion.COD_SERV_INACTIVO.equals(strResponseCode)) {
                            FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_CMR_SERV_INACTIVO, null);
                        } else {
                            FarmaUtility.showMessage(this, "La recaudación no se pudo anular.", null);
                        }
                    }
                } //fin CMR
                else if (tipRcdCod.equals(ConstantsRecaudacion.TIPO_REC_CLARO)) {
                    codTrssc =
                            facadeRecaudacion.anularPagoServicioCLARO(codSix, FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia,
                                                                      FarmaVariables.vCodLocal,
                                                                      ConstantsRecaudacion.MSJ_SIX_PETICION_TRSSC_200,
                                                                      ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                                      ConstantsRecaudacion.TRNS_ANU_PAG_SRV,
                                                                      ConstantsRecaudacion.TIPO_REC_CLARO, montoPagado,
                                                                      codRecauAnular, FarmaVariables.vDescCortaLocal,
                                                                      //comercio
                                FarmaVariables.vDescCortaDirLocal, FarmaVariables.vNuSecUsu, FarmaVariables.vIdUsu,
                                codRecauNegativo, strDniUsu, VariablesRecaudacion.vTipoCambioVenta, codMoneda,
                                codRecauAnular, //Anulacion-Nro de Comprobante origen
                                fechaRecauAnular, //Anulacion-Fecha Origen del Comprobante
                                codAutorizRecauAnular, dblMontoPagado, pRecaudOnline);
                    //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
                    if (codTrssc == null) {
                        FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                        return;
                    }
                    rptSix =
                            facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, ConstantsRecaudacion.RCD_ANU_PAGO_SIX_CLARO,
                                                                  codTrssc);

                    bRpt = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPUESTA);
                    bMsj = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MSJ);
                    strResponseCode = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPONSE_CODE);
                    strMontoPagar = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MONTO_PAGAR);
                    strCodAuditoria = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUDITORIA);
                    strCodAutorizacionSix =
                            (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUTORIZ); // SE GUARDA EN LA CABECERA DE RECAUDACION PARA COMPRA Y VENTA CMR

                    if (ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(strResponseCode)) {
                        //se genera el pedido negativo
                        facadeRecaudacion.anularRecaudacion(codRecauAnular, VariablesCaja.vNumCaja,
                                                            FarmaVariables.vIdUsu, codTrssc, codRecauNegativo);

                        //GFONSECA 27.10.2013 Imprimir anulacion de recaudacion
                        if (!codRecauNegativo.equals("")) {
                            facadeRecaudacion.imprimirComprobanteAnulRecaudacion(codRecauNegativo);
                        }
                        FarmaUtility.showMessage(this, "La recaudación fue anulada.", null);
                    } else {
                        if (ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                            FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_NO_RESPUESTA, null);
                        } else if (strResponseCode.equals("91") || strResponseCode.equals("94")) {
                            FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_CLARO_SERV_INACTIVO, null);
                        } else {
                            //ERIOS 2.2.9 Mensaje del operador
                            FarmaUtility.showMessage(this, "[Mensaje Operador]" + "\n" +
                                                           (String)rptSix.get(12), null);
                        }                        
                    }
                } //fin claro
                else if (tipRcdCod.equals(ConstantsRecaudacion.TIPO_REC_RIPLEY)) {
                    String numCuota = "1";
                    //String motivoExtorno="85";
                    codTrssc = facadeRecaudacion.anularPagoTarjetaRipley(codSix, numTarjeta, numCuota, montoPagado,
                                //motivoExtorno,
                                codAutorizRecauAnular, fechaOrigen, codRecauAnular, codRecauNegativo, strDniUsu,
                                VariablesRecaudacion.vTipoCambioVenta, codMoneda, codRecauAnular,
                                //Anulacion-Nro de Comprobante origen
                                fechaRecauAnular, //Anulacion-Fecha Origen del Comprobante
                                codAutorizRecauAnular, dblMontoPagado, pRecaudOnline);
                    //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con el pago
                    if (codTrssc == null) {
                        FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                        return;
                    }
                    rptSix =
                            facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, ConstantsRecaudacion.RCD_ANU_PAGO_SIX_CMR,
                                                                  codTrssc);
                    bRpt = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPUESTA);
                    bMsj = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MSJ);
                    strResponseCode = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPONSE_CODE);
                    strMontoPagar = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MONTO_PAGAR);
                    strCodAuditoria = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUDITORIA);
                    strCodAutorizacionSix =
                            (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUTORIZ); // SE GUARDA EN LA CABECERA DE RECAUDACION PARA COMPRA Y VENTA CMR

                    if (bRpt && ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(strResponseCode)) {
                        //se genera el pedido negativo
                        facadeRecaudacion.anularRecaudacion(codRecauAnular, VariablesCaja.vNumCaja,
                                                            FarmaVariables.vIdUsu, codTrssc, codRecauNegativo);

                        //GFONSECA 27.10.2013 Imprimir anulacion de recaudacion
                        if (!codRecauNegativo.equals("")) {
                            facadeRecaudacion.imprimirComprobanteAnulRecaudacion(codRecauNegativo);
                        }
                        FarmaUtility.showMessage(this, "La recaudación fue anulada.", null);
                    } else {
                        if (ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                            FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_NO_RESPUESTA, null);
                        } else if (ConstantsRecaudacion.COD_SERV_INACTIVO.equals(strResponseCode)) {
                            FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_RIPLEY_SERV_INACTIVO, null);
                        } else {
                            FarmaUtility.showMessage(this, "La recaudación no se pudo anular.", null);
                        }
                    }
                } //fin Ripley
                else if (tipRcdCod.equals(ConstantsRecaudacion.TIPO_REC_FINANCIERO)) {
                    log.info("=========> EXTORNO FINANCIERO");
                    
                    String numCuota = "1";
                    String motivoExtorno = ConstantsRecaudacion.COD_EXT_ANULACION_PAGO;
                    codTrssc =
                        facadeRecaudacion.anularPagoTarj_Financiero(ConstantsRecaudacion.MSJ_SIX_ANULACION_TRSSC_400,
                                                                    ConstantsRecaudacion.ESTADO_SIX_PENDIENTE,
                                                                    ConstantsRecaudacion.TRNS_ANU_PAG_TARJ,
                                                                    ConstantsRecaudacion.TIPO_REC_FINANCIERO,
                                                                    
                                                                    numTarjeta,
                                                                    numCuota,
                                                                    montoPagado,
                                                                    
                                                                    VariablesCaja.vNumCaja,
                                                                    FarmaVariables.vNuSecUsu,
                                                                    motivoExtorno,/**85 ??????????????????*/
                                                                    codRecauAnular,/**cCod_Trssc_Six_in (Local)*/
                                                                    //Anulacion-Nro de Comprobante origen
                                                                    FarmaVariables.vDescCortaLocal,
                                                                    FarmaVariables.vDescCortaDirLocal,
                                                                    FarmaVariables.vIdUsu,
                                                                    codRecauNegativo,////??????? facadeRecaudacion.getCodRecauAnul()
                                                                    strDniUsu,
                                                                    VariablesRecaudacion.vTipoCambioVenta,
                                                                    codMoneda,
                                                                    fechaRecauAnular, 
                                                                    //Anulacion-Fecha Origen del Comprobante
                                                                    codSix,//,codAutorizRecauAnular,
                                                                    pRecaudOnline,
                                                                    dblMontoPagado);
                    if (codTrssc == null) {
                        FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                        return;
                    }
                    
                    rptSix = facadeRecaudacion.obtenerRespuestaSixFinanciero(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, 
                                                                             ConstantsRecaudacion.RCD_ANU_PAGO_SIX_FINANCIERO, 
                                                                             codTrssc);
                    
                    /*rptSix = facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_PAGO_SIX, 
                                                                   ConstantsRecaudacion.RCD_ANU_PAGO_SIX_FINANCIERO, 
                                                                   codTrssc);*/
                    
                    bRpt = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPUESTA);
                    bMsj = (Boolean)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MSJ);
                    strResponseCode = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPONSE_CODE);
                    strMontoPagar = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_MONTO_PAGAR);
                    strCodAuditoria = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUDITORIA);
                    strCodAutorizacionSix =
                            (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_COD_AUTORIZ); // SE GUARDA EN LA CABECERA DE RECAUDACION PARA COMPRA Y VENTA CMR
                    /*
                    if(rptaAnul.equalsIgnoreCase("OK")){
                        FarmaUtility.showMessage(this, "La anulacion del pago de tarjeta de credito financiero se realizo correctamente", null);
                    }else{
                        FarmaUtility.showMessage(this, "Error no se extorno el pago", null);
                    }
                    */
                    if (bRpt && ConstantsRecaudacion.COD_SOLICITUD_EXITOSA.equals(strResponseCode)) {
                        log.info("===> bRpt: "+bRpt+" - "+strResponseCode);
                        //se genera el pedido negativo
                        facadeRecaudacion.anularRecaudacion(codRecauAnular, VariablesCaja.vNumCaja,
                                                            FarmaVariables.vIdUsu, codTrssc, codRecauNegativo);

                        //GFONSECA 27.10.2013 Imprimir anulacion de recaudacion
                        log.info("codRecauNegativo ---> "+codRecauNegativo);
                        log.info("codRecauNegativo -------------------------");
                        if (!codRecauNegativo.equals("")) {
                            log.info("************** ENTRO A IMPRIMIR ******************");
                            facadeRecaudacion.imprimirComprobanteAnulRecaudacion(codRecauNegativo);
                        }
                        //String msjRpta
                        //Abrir Gabeta
                        UtilityCaja.abrirGabeta(myParentFrame, false);

                        FarmaUtility.showMessage(this, "La recaudación fue anulada.", null);

                    } else {
                        if (ConstantsRecaudacion.COD_ERR_TRSS_EXTORNADA_TC.equals(strResponseCode) ||
                            ConstantsRecaudacion.COD_ERR_TRSS_EXTORNADA_DC.equals(strResponseCode)) {
                            String est_Anulado=facadeRecaudacion.verificaEstado_Recaudacion_Anulado(codRecauAnular);
                            if(est_Anulado.equalsIgnoreCase("NO")){
                                facadeRecaudacion.anularRecaudacion(codRecauAnular, VariablesCaja.vNumCaja,
                                                                    FarmaVariables.vIdUsu, codTrssc, codRecauNegativo);
                                //GFONSECA 27.10.2013 Imprimir anulacion de recaudacion
                                if (!codRecauNegativo.equals("")) {
                                    facadeRecaudacion.imprimirComprobanteAnulRecaudacion(codRecauNegativo);
                                }
                                FarmaUtility.showMessage(this, "La recaudación esta anulada en el proveedor\nSe anulo tambien en el local.", null);
                            }else if(est_Anulado.equalsIgnoreCase("SI")){
                                FarmaUtility.showMessage(this, "La transaccion ya se encuentra anulada en la Entidad Bancaria y MiFarma.", null);
                            }
                        }else{
                            if (ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                                FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_NO_RESPUESTA, null);
                            } else if (ConstantsRecaudacion.COD_SERV_INACTIVO.equals(strResponseCode)) {
                                FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_CMR_SERV_INACTIVO, null);
                            } else {
                                FarmaUtility.showMessage(this, "La recaudación no se pudo anular.", null);
                            }
                        }
                    }
                }//fin financiero
            } else {
                FarmaUtility.showMessage(this, "No se pudo anular la recaudación, intente nuevamente.", null);
            }

        } else { //Recaudaciones que no pasan por el six

            //ERIOS 2.4.0 Recaudacon Citibank centralizada            
            String strCodAutorizacion = FarmaVariables.vCodLocal + codRecauNegativo;
            //INI ASOSA - 11/08/2015 - RAIZ
            if (tipRcdCod.equals(ConstantsRecaudacion.TIPO_REC_RAIZ) ||
                tipRcdCod.equals(ConstantsRecaudacion.TIPO_REC_INCASUR) //ASOSA - 17/05/2016 - INCASUR
                ) {
                pRecaudOnline = 1;
            }
            //FIN ASOSA - 11/08/2015 - RAIZ
            codTrssc = facadeRecaudacion.anularTrsscPagoCitibank(tipRcdCod, //tipo recaudacion
                        dblMontoPagado, //monto
                        codRecauNegativo, //terminal: Identificamos la transaccion con el numero de recaudacion
                        numTelefono, // cod cliente
                        numTarjeta, //numero de tarjeta
                        strCodAutorizacion, //codigoautorizacion
                        FarmaVariables.vIdUsu, strDniUsu, VariablesRecaudacion.vTipoCambioVenta, codMoneda,
                        codRecauAnular, //Anulacion-Nro de Comprobante origen
                        fechaRecauAnular, //Anulacion-Fecha Origen del Comprobante
                        codAutorizRecauAnular, pRecaudOnline);

            //se genera el pedido negativo
            facadeRecaudacion.anularRecaudacion(codRecauAnular, VariablesCaja.vNumCaja, FarmaVariables.vIdUsu,
                                                codTrssc, codRecauNegativo);

            //GFONSECA 27.10.2013 Imprimir anulacion de recaudacion
            if (!codRecauNegativo.equals("")) {
                facadeRecaudacion.imprimirComprobanteAnulRecaudacion(codRecauNegativo);
            }
            FarmaUtility.showMessage(this, "La recaudación fue anulada.", null);
        }

        cerrarVentana(true);
    }


    public void procesarConsultaClaro(String terminal, String nroTelefono, String tipProdServ) {
        this.terminal = terminal;
        this.nroTelefono = nroTelefono;
        this.tipProdServ = tipProdServ;
    }


    public void procesarConsulta() throws Exception {
        if(ConstantsRecaudacion.RCD_IND_PROCESO_CONSU_CLARO.equals(strIndProc)){
            //Long codTrssc = null;
            codTrssc = null;
            //ERIOS 2.3.3 Valida conexion con RAC
            try {
                facadeRecaudacion.validarConexionRAC();
            } catch (Exception e) {
                FarmaUtility.showMessage(this, e.getMessage(), null);
                return;
            }

            codTrssc = facadeRecaudacion.registrarTrsscConsultaDeudaClaro(terminal, nroTelefono, tipProdServ);
            //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con la consulta
            if (codTrssc == null) {
                FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción.", null);
                return;
            }
            setRptSix(facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_CONSULTA_SIX,
                                                            ConstantsRecaudacion.RCD_CONSULTA_PAGO_SIX_CLARO, codTrssc));
            cerrarVentana(true);
        }else if(ConstantsRecaudacion.RCD_IND_PROCESO_CONSU_FINANCIERO.equals(strIndProc)){
            String strResponseCode = "";
            String estTrsscSix = "";
            
            //REGISTRA SOLICITUD DE CONSULTA
            registrarConsultaFinanciero();
            
            //OBTIENE CONSULTA
            strResponseCode = (String)rptSix.get(ConstantsRecaudacion.RCD_PAGO_RESPONSE_CODE);
            log.info("RESPUESTA SIX strResponseCode: " + strResponseCode);
            //strResponseCode = "00"; //BORRAR BORRAR BORRAR AOVIEDO
            
            if(strResponseCode.equals(ConstantsRecaudacion.COD_SOLICITUD_EXITOSA)){
                estTrsscSix = ConstantsRecaudacion.RCD_PAGO_SIX_EST_TRSSC_CORRECTA;
                obtieneConsultaFinanciero();
            }else{
                estTrsscSix = ConstantsRecaudacion.RCD_PAGO_SIX_EST_TRSSC_FALLIDA;
                if (ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                    FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_MSJ_NO_RESPUESTA, null);
                } else if (strResponseCode.equals("91") || strResponseCode.equals("94")) {
                    FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_CONSULTA_SIX_MSJ_CONSULTA_FALLIDO, null);
                } else if (!ConstantsRecaudacion.COD_NO_RESPUESTA.equals(strResponseCode)) {
                    //ERIOS 2.2.9 Mensaje del operador
                    /*
                    FarmaUtility.showMessage(this, "Mensaje Operador" + ":\n" + 
                                                   (String)rptSix.get(12)+"\n Consulte con su banco", null);
                    */
                    //if(VariablesRecaudacion.vMensajeError==null || VariablesRecaudacion.vMensajeError.trim().equalsIgnoreCase("")){
                        try {
                            String msjError = facadeRecaudacion.recupera_msjError_BFP(codTrssc);
                            if(msjError!=null){
                                VariablesRecaudacion.vMensajeError=msjError;
                            }else{
                                VariablesRecaudacion.vMensajeError="No se obtuvo respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP;
                            }
                        } catch (Exception f) {
                            System.out.println("*****************************************************");
                            log.info("Error: "+f.getMessage());
                            VariablesRecaudacion.vMensajeError="Error al consultar respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP+"...\n"+f.getMessage();
                            System.out.println("*****************************************************");
                        }
                    //}
                } else {
                    FarmaUtility.showMessage(this, ConstantsRecaudacion.RCD_CONSULTA_SIX_MSJ_CONSULTA_FALLIDO, null);
                }
            }
        }
    }
    
    public void obtieneConsultaFinanciero() {
        try {
            log.info("==========================================> INTENTO CONSULTA: "+intento+" de "+VariablesRecaudacion.vNroIntentos);
            int minAntes = Calendar.getInstance().getTime().getMinutes();
            int secAntes = Calendar.getInstance().getTime().getSeconds();
            int minDespues = minAntes;
            int secDespues = secAntes + VariablesRecaudacion.vCantSegEspere;
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
                    intento ++;
                    
                    tmpLista = new ArrayList<>();
                    tmpLista = facadeRecaudacion.solicitaPagos_BancoFinanciero(codTrssc);
                    
                    if (tmpLista == null || tmpLista.size() <= 0) {
                        //FarmaUtility.showMessage(this, "Ocurrió un error al listar solicitudes de pago.", null);
                        otraVez = true;
                        this.setVProcesoConsulta(false);
                        //return;
                    }else{
                        VariablesRecaudacion.vNombre_Cliente_BFP=facadeRecaudacion.recupera_NombreCliente_BFP(codTrssc);
                        otraVez = false;
                        this.setVProcesoConsulta(true);                        
                    }
                    
                    //ejecutaSolicitudesPagoBancoFinanciero();
                    //evaluaMsjVentaVirtualGenerado(VariablesCaja.vTipoProdVirtual);
                    //FarmaUtility.showMessage(this, "El proceso de anulacion se realizo satisfactoriamente", null);
                }                
            } while (otraVez == true);
        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            if(intento == VariablesRecaudacion.vNroIntentos){
                this.setVProcesoConsulta(false);
                /*
                JMessageAlert.showMessage(myParentFrame, "Consulta Tarjetas de Crédito",
                                          "ERROR: No se pudo realizar la Consulta de Tarjetas de Crédito",
                                          e.getMessage() + "@" + "Error en la Consulta de Tarjetas de Crédito",
                                          "Verifique los datos necesarios", true);
                */
                try {
                    String msjError = facadeRecaudacion.recupera_msjError_BFP(codTrssc);
                    if(msjError!=null){
                        VariablesRecaudacion.vMensajeError=msjError;
                    }else{
                        VariablesRecaudacion.vMensajeError="No se obtuvo respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP;
                    }
                } catch (Exception f) {
                    System.out.println("*****************************************************");
                    log.info("Error: "+f.getMessage());
                    VariablesRecaudacion.vMensajeError="Error al consultar respuesta de "+VariablesRecaudacion.RAZON_SOCIAL_BFP+"...\n"+f.getMessage();
                    System.out.println("*****************************************************");
                }
            }else{
                obtieneConsultaFinanciero();
            }
        }
    }
    
    //INI AOVIEDO 01/08/2017
    public void procesarConsultaFinanciero(String terminal, String nroDNI, String tipoOperacionBFP) {
        this.terminal = terminal;
        this.nroDNI = nroDNI;
        this.tipoOperacionBFP = tipoOperacionBFP;
    }
    
    public void registrarConsultaFinanciero() throws Exception {
        codTrssc = null;
        //ERIOS 2.3.3 Valida conexion con RAC
        try {
            facadeRecaudacion.validarConexionRAC();
        } catch (Exception e) {
            FarmaUtility.showMessage(this, e.getMessage(), null);
            return;
        }

        codTrssc = facadeRecaudacion.registrarTrsscConsultaFinanciero(terminal, nroDNI, tipProdServ,tipoOperacionBFP);
        //GFonseca 21/11/2013 Si falla el insert de la peticion, ya no continua con la consulta
        if (codTrssc == null) {
            FarmaUtility.showMessage(this, "Ocurrio un error al registrar la transacción de consulta.", null);
            return;
        }
        setRptSix(facadeRecaudacion.obtenerRespuestaSix(ConstantsRecaudacion.RCD_MODO_CONSULTA_SIX, 
                                                        ConstantsRecaudacion.RCD_CONSULTA_PAGO_SIX_FINANCIERO, codTrssc));
        //cerrarVentana(true);
    }
    
    /*private void ejecutaSolicitudesPagoBancoFinanciero() throws Exception {
        try {
            facadeRecaudacion.solicitaPagos_BancoFinanciero(nroDNI, tipoConsultaBFP);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }*/
    
    public void procesarPagoFinanciero(String terminal, String nroDNI, String tipoOperacionBFP, String nroTarjeta, 
                                       String monto, String tipoMoneda, double totalPagar) {
        this.terminal = terminal;
        this.nroDNI = nroDNI;
        this.tipoOperacionBFP = tipoOperacionBFP;
        this.nroTarjeta = nroTarjeta;
        this.monto = monto;
        this.tipoMoneda = tipoMoneda;
        this.totalPagar = totalPagar;
    }
    
    public String obtenerFecha() {
        String fechaSys = "";
        try {
            fechaSys = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener la fecha y hora. \n " + sql.getMessage(), null);
        }
        return fechaSys;
    }

    public String obtenerObjetoPago() {
        String codObjePago = "";
        String strTipoRecau = tmpArrayCabRcd.get(4).toString();
        if (ConstantsRecaudacion.TIPO_REC_RIPLEY.equals(strTipoRecau) ||
            ConstantsRecaudacion.TIPO_REC_CMR.equals(strTipoRecau) ||
            ConstantsRecaudacion.TIPO_REC_CITI.equals(strTipoRecau)) {
            codObjePago = tmpArrayCabRcd.get(3).toString(); //nro tarjeta
        } else if (ConstantsRecaudacion.TIPO_REC_CLARO.equals(strTipoRecau) ||
                   ConstantsRecaudacion.TIPO_REC_PRES_CITI.equals(strTipoRecau)) {
            codObjePago = tmpArrayCabRcd.get(8).toString(); //codigo de recibo o de cliente
        }
        return codObjePago;
    }


    public String getStrIndProc() {
        return strIndProc;
    }

    public void setStrIndProc(String strIndProc) {
        this.strIndProc = strIndProc;
    }

    public ArrayList<Object> getRptSix() {
        return rptSix;
    }

    public void setRptSix(ArrayList<Object> rptSix) {
        this.rptSix = rptSix;
    }

    @Override
    public void ejecutaProceso() {
        realizarProcesos();
    }

    public void setVProcesoConsulta(boolean vProcesoConsulta) {
        this.vProcesoConsulta = vProcesoConsulta;
    }

    public boolean isVProcesoConsulta() {
        return vProcesoConsulta;
    }

    public void setTmpLista(ArrayList<ArrayList<String>> tmpLista) {
        this.tmpLista = tmpLista;
    }

    public ArrayList<ArrayList<String>> getTmpLista() {
        return tmpLista;
    }

    void setDatos_Verificar(String tipRcdCod, String codSix, String codRecauAnular) {
        verificaEstado=true;
        this.tipRcdCod=tipRcdCod;
        this.codSix=codSix;
        this.codRecauAnular=codRecauAnular;
    }

    private void verificarEstado_PagoBFP() {
        try {
            /*
            rptaPruebaEstado=
                facadeRecaudacion.verificaEstado_PagoSix(tipRcdCod,codSix,codRecauAnular);
            */
            log.info("---------------------------------------------");
            log.info("tipRcdCod => "+tipRcdCod);
            log.info("codSix => "+codSix);
            log.info("codRecauAnular => "+codRecauAnular);
            log.info("---------------------------------------------");
            ArrayList<ArrayList<String>> rptaDatos=
            facadeRecaudacion.verificaEstado_TrsscSix(tipRcdCod,codSix,codRecauAnular);
            /*
            00 -> Estado Pagado
            01 -> Estado Extornado
            02 -> Error en pago
            03 -> Error al extornar (Estado Pagado)
            04 -> registro no existe
            */
            String codRpta_RAC=rptaDatos.get(0).get(0).toString().trim();
            String msjRpta=rptaDatos.get(0).get(1).toString().trim();
            codSix = rptaDatos.get(0).get(2).toString().trim();
            
            String msjFv=facadeRecaudacion.actualizaEstado_Local(codRpta_RAC,tipRcdCod,codSix,codRecauAnular);
            String codRpta_Fv=msjFv.substring(0, 2).trim();
            if(codRpta_Fv.equalsIgnoreCase("04")){
                int index = msjFv.indexOf("/");
                String msj=msjFv.substring(3,index).trim()+"\nError:\n"
                            +msjFv.substring(index+1).trim();
                msjFv=msj;                
            }else{
                msjFv=msjFv.substring(3).trim();
            }
            
            rptaPruebaEstado = msjRpta;
            if((codRpta_RAC.equalsIgnoreCase("00")||codRpta_RAC.equalsIgnoreCase("01")) 
               && (codRpta_Fv.equalsIgnoreCase("00") || codRpta_Fv.equalsIgnoreCase("01"))){
                if(codRpta_Fv.equalsIgnoreCase("00")){//Cobrado
                    long codTrssc=Long.parseLong(codSix);
                    VariablesRecaudacion.vTipoOperacion_BFP=UtilityRecaudacion.recuperaTipoOperacio_BFP(codSix,codRecauAnular);
                    VariablesRecaudacion.vNombre_Cliente_BFP=facadeRecaudacion.recupera_NombreCliente_BFP(codTrssc);
                    
                    if(!VariablesRecaudacion.vTipoOperacion_BFP.equalsIgnoreCase("10") 
                       && !VariablesRecaudacion.vTipoOperacion_BFP.equalsIgnoreCase("30")){
                        VariablesRecaudacion.vCuotaPrestamo_BFP = facadeRecaudacion.recupera_NroCuotaPrestamo(codTrssc);
                    }else{
                        VariablesRecaudacion.vCuotaPrestamo_BFP = null;
                    }
                    facadeRecaudacion.imprimirComprobantePagoRecaudacionFinanciero(codRecauAnular,
                                                                                   -1,
                                                                                   VariablesRecaudacion.vCuotaPrestamo_BFP);
                }else{//Anulado
                    VariablesCaja.vNumCaja = DBCaja.obtenerCajaUsuario();
                    facadeRecaudacion.reimprimirComprobanteAnulRecaudacion(codRecauAnular);
                }
            }
            
            if(msjFv!=null && !msjFv.trim().equalsIgnoreCase("")){
                rptaPruebaEstado = rptaPruebaEstado+"\n"+msjFv;
            }
        } catch (Exception e) {
            log.error("=> "+e.getMessage(),e);
            rptaPruebaEstado = "Error al actualizar los estados de recaudacion. \n"+e.getMessage();
        }
    }

    public void setRptaPruebaEstado(String rptaPruebaEstado) {
        this.rptaPruebaEstado = rptaPruebaEstado;
    }

    public String getRptaPruebaEstado() {
        return rptaPruebaEstado;
    }

    public int getCodRptaPruebaEstado() {
        return codRptaPruebaEstado;
    }

    public void setCodRptaPruebaEstado(int codRptaPruebaEstado) {
        this.codRptaPruebaEstado = codRptaPruebaEstado;
    }
}
