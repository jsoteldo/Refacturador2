package mifarma.ptoventa.lealtad.reference;

import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.awt.Frame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.HiloImpresion;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.lealtad.DlgRedencionPuntos;
import mifarma.ptoventa.lealtad.dao.DAOLealtad;
import mifarma.ptoventa.lealtad.dao.MBLealtad;
import mifarma.ptoventa.puntos.reference.ConstantsPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.UtilityTransactionPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.ventas.DlgConsultaXCorrelativo;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author ERIOS
 * @since 05.02.2015
 */
public class FacadeLealtad {
    
    private static final Logger log = LoggerFactory.getLogger(FacadeLealtad.class);
    
    private DAOLealtad daoLealtad;
    UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();

    public FacadeLealtad() {
        super();
        daoLealtad = new MBLealtad();
    }
    
    public int verificaAcumulaX1(String pCodProd){
        int nRetorno = 0;
        try {
            daoLealtad.openConnection();
            nRetorno = daoLealtad.verificaAcumulaX1(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, 
                                         pCodProd);
            daoLealtad.commit();
        } catch (Exception e) {
            daoLealtad.rollback();
            log.error("", e);
        }
        return nRetorno;
    }
    
    public void listaAcumulaX1(FarmaTableModel pTableModel, String pCodProd) {
        
        try {
            daoLealtad.openConnection();
			// lais X+1
            List<BeanResultado> lstListado = daoLealtad.listaAcumulaX1(FarmaVariables.vCodGrupoCia, 
                                                                       FarmaVariables.vCodCia,
                                                                       FarmaVariables.vCodLocal, 
                                         pCodProd);
			// lais X+1
            utilityPtoVenta.parsearResultado(lstListado, pTableModel, false, "@");
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        } 
    }
    // lais X+1
    /**
     * @autor Desarrollo5 - Alejandro Nu�ez
     * @param pTableModel
     * @param listBeanResultado
     * @since 05.11.2015
     */
    public void listaAcumulaX1(FarmaTableModel pTableModel, List<BeanResultado> listBeanResultado) {
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        try {
            utilityPtoVenta.parsearResultado(listBeanResultado, pTableModel, false, "@");
        } catch (Exception ex) {
            log.error("",ex);
        }
    }
	// lais X+1
    
    public ArrayList<ArrayList<String>> listaAcumulaX1(String pCodProd) {
        ArrayList<ArrayList<String>> lista = null;
        try {
            daoLealtad.openConnection();
            List<BeanResultado> lstListado = daoLealtad.listaAcumulaX1(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, 
                                         pCodProd);
            lista = utilityPtoVenta.parsearResultadoMatriz(lstListado,"@");
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        } 
        return lista;
    }
    // lais X+1
    public void impresionVoucher(String pDniCli, String pCodCamp, String pCodProd){
        try{
            daoLealtad.openConnection();
            String pConsulta = daoLealtad.indImpresionVoucherX1();
            daoLealtad.commit();
            String[] pIndicadores = pConsulta.trim().split("@");
            String indImpresion = pIndicadores[0];
            String indLogo = pIndicadores[1];
            
            if(indImpresion.equals(FarmaConstants.INDICADOR_S)){
                List lstImpresionTicket = daoLealtad.voucherAcumulaX1(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, 
                                             pCodCamp, pDniCli, pCodProd);
                if (lstImpresionTicket.size() > 0) {
        
                    HiloImpresion hilo = new HiloImpresion(lstImpresionTicket);
                    if(indLogo.equals(FarmaConstants.INDICADOR_S)){
                        hilo.setImprimirLogo(true);
                    }
                    hilo.start();
                }
            }
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        }
    }
	// lais X+1
    public void inscribeAcumulaX1(JDialog pJDialog, String pCodProd, String pCodCamp, String pCodEqui, String pCodMatrizAcu,
                                  BeanTarjeta tarjetaCliente) {
        /*String vRetorno;    
        vRetorno = daoLealtad.registrarInscripcionX1(tarjetaCliente.getDni(), pCodMatrizAcu, FarmaVariables.vIdUsu);
        if(vRetorno.equals("1")){
            List<String> listaInscritos = tarjetaCliente.getListaInscritos();
            listaInscritos.add(pCodEqui);  
            impresionVoucher(tarjetaCliente.getDni(),pCodCamp,pCodProd);
        }else if (vRetorno.length() > 0){
            FarmaUtility.showMessage(pJDialog, vRetorno, null);
        }*/
    }

    public String verificaInscripcionProducto(List<String> pListaInscritos, String pCodProd) {
        String bRetorno = "";
        for(String campEq : pListaInscritos){
            try{
                if(campEq.substring(0, 6).equals(pCodProd)){
                    bRetorno = campEq;
                    break;
                }
            }catch(StringIndexOutOfBoundsException e){
                // lais X+1
				bRetorno = "Error";
            }
        }
        return bRetorno;
    }

    public boolean registrarVenta(JDialog pJDialog, String pNumPedVta) throws Exception {
        boolean bRetorno = true;
        if(UtilityPuntos.isActivoFuncionalidad() && VariablesPuntos.frmPuntos != null){
            BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
            
            if(tarjetaCliente!=null){
                
                String prodBonifica = "";
                try{
                    daoLealtad.openConnection();
                    
                    String indicadoresVenta = daoLealtad.obtenerIndicadoresVenta(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
                    String[] indicadores = indicadoresVenta.split("@");
                    String indRedimido = indicadores[0];
                    String indAcumula = indicadores[1];
                    
                    //ERIOS 27.03.2015 Invoca al QUOTE
                    if("S".equals(indRedimido) && ("0".equals(indAcumula) || "1".equals(indAcumula))){
                       // UtilityPuntos.procesarQuote(tarjetaCliente, pNumPedVta);
                    }
                    
                    String parametrosVenta = daoLealtad.obtenerParametrosVenta(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
                    String[] parametros = parametrosVenta.split("@");
                    String nroTarjeta = parametros[0];
                    String numeroPedido = parametros[1];
                    Date fechaPedido = FarmaUtility.getStringToDate(parametros[2], "dd/MM/yyyy");
                    double importeVenta = FarmaUtility.getDecimalNumber(parametros[3]);
                    String usuarioVenta = parametros[4];
                    double saldoRedimido = FarmaUtility.getDecimalNumber(parametros[5]);
                    prodBonifica = parametros[6];
                    String puntosAcumula = parametros[7];
                    String campAcumula = parametros[8];
                    //DUBILLUZ REVISAR ERROR COBRO CONVENIO 2017.07.21
                    double puntosExtas = FarmaUtility.getDecimalNumber(parametros[9]);
					// lais X+1
                    Long tiempoInicial;
                    Long tiempoFinal;
                    
                    
                    
                    if(prodBonifica.equals("0") && puntosAcumula.equals("0") && campAcumula.equals("0") && saldoRedimido == 0 && puntosExtas == 0){
                        daoLealtad.descartarPedido(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
                        
                    }else if(tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.EXITO) &&
                            !tarjetaCliente.getEstadoTarjeta().equals(WSClientConstans.EstadoTarjeta.BLOQUEADA)){
                        
                        //online                        
                        try{
                            String idTransaccion = tarjetaCliente.getIdTransaccion();
                            ArrayList listaProducto = (ArrayList)UtilityPuntos.listaProdPuntos(pNumPedVta);                            
                            
                            tarjetaCliente.setNumeroTarjeta(nroTarjeta);
                            
                            tiempoInicial=   System.currentTimeMillis();
                            
                            tarjetaCliente = VariablesPuntos.frmPuntos.registrarVentaOnline(listaProducto, saldoRedimido+puntosExtas, numeroPedido, 
                                                                                            fechaPedido, importeVenta, usuarioVenta);                    
                            
                            System.out.append("PROGRAMA DE PUNTOS [SALE] [INPUT]"+ VariablesPuntos.frmPuntos.getBitacora().getVInput_in());
                            System.out.append("PROGRAMA DE PUNTOS [SALE] [OUTPUT]"+VariablesPuntos.frmPuntos.getBitacora().getVOutput());

                            tiempoFinal=   System.currentTimeMillis();
                            
                            log.info("PROGRAMA DE PUNTOS [SALE] : TIEMPO DE RESPUESTA DE SALE CON PROVEEDOR "+(tiempoFinal-tiempoInicial)+"milisegundos") ;
                            // probar OFFLINE
                            // NO DESCOMENTAR , ES SOLO PARA PROBAR DE FORMA OFFLINE
                            //tarjetaCliente.setEstadoOperacion(WSClientConstans.NO_CONEXION_ORBIS);
                            // probar OFFLINE
                            
                            if(tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.EXITO)){
                                String numeroAutorizacion = tarjetaCliente.getNumeroAutororizacion();
                                
                                daoLealtad.actualizarPedido(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta,
                                                            idTransaccion, numeroAutorizacion, FarmaVariables.vIdUsu);
                                
                                
                            }else if(tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.NO_CONEXION_ORBIS)){ 
                                
                                marcaPedidoOffline(pJDialog,prodBonifica,pNumPedVta);
                            }
                            
                        }catch(Exception ex){
                            //daoLealtad.rollback();
                            log.error("", ex);
                            marcaPedidoOffline(pJDialog,prodBonifica,pNumPedVta);
                        }
                        //2016.09.20 LTAVARA SI NO ES EXITO ES OFFLINE                    
                    }else if(!tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.EXITO) &&
                        !tarjetaCliente.getEstadoTarjeta().equals(WSClientConstans.EstadoTarjeta.BLOQUEADA)){ 
                        marcaPedidoOffline(pJDialog,prodBonifica,pNumPedVta);
                    }
                    
                    daoLealtad.commit();
                }
                catch(Exception ex){
                    daoLealtad.rollback();
                    log.error("", ex);
                    //FarmaUtility.showMessage(pJDialog,"Ha ocurrido un error inesperado.\n"+ex.getMessage(),null);
                    bRetorno = false;
                    throw new Exception(""+ex.getMessage()); 
                }      
            }
        }
        return bRetorno;
    }

    public boolean anularVenta(JDialog pJDialog, String pNumPedVta) throws Exception{
        boolean bRetorno = true;
        /*if(UtilityPuntos.isActivoFuncionalidad() && VariablesPuntos.frmPuntos != null){
            BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
            
            if(tarjetaCliente!=null){
                
                String prodBonifica = "";
                try{
                    daoLealtad.openConnection();
                    String parametrosVenta = daoLealtad.obtenerParametrosVenta(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
                    String[] parametros = parametrosVenta.split("@");
                    String nroTarjeta = parametros[0];
                    String numeroPedido = parametros[1];
                    //Date fechaPedido = FarmaUtility.getStringToDate(parametros[2], "dd/MM/yyyy");
                    String fechaPedido = parametros[2];
                    double importeVenta = FarmaUtility.getDecimalNumber(parametros[3]);
                    String usuarioVenta = parametros[4];
                    double saldoRedimido = FarmaUtility.getDecimalNumber(parametros[5]);
                    prodBonifica = parametros[6];
                    String puntosAcumula = parametros[7];
                    String campAcumula = parametros[8];
                    double puntosExtas = FarmaUtility.getDecimalNumber(parametros[9]);
                    Long tiempoInicial;
                    Long tiempoFinal;
                    
                    if(prodBonifica.equals("0") && puntosAcumula.equals("0") && campAcumula.equals("0") && saldoRedimido == 0 && puntosExtas == 0){
                        daoLealtad.descartarPedido(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
                    }else if(tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.EXITO) &&
                            !tarjetaCliente.getEstadoTarjeta().equals(WSClientConstans.EstadoTarjeta.BLOQUEADA)){
                        try{
                            String idTransaccion = tarjetaCliente.getIdTransaccion();
                            ArrayList listaProducto = (ArrayList)UtilityPuntos.listaProdPuntos(pNumPedVta);
                            tarjetaCliente.setNumeroTarjeta(nroTarjeta);
                            tiempoInicial=   System.currentTimeMillis();
                            tarjetaCliente = VariablesPuntos.frmPuntos.anularPedido(nroTarjeta, listaProducto, pNumPedVta, saldoRedimido+puntosExtas, fechaPedido, importeVenta, usuarioVenta);
                            
                        }catch(Exception ex){
                            log.error("", ex);
                        }
                    }else if(!tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.EXITO) &&
                        !tarjetaCliente.getEstadoTarjeta().equals(WSClientConstans.EstadoTarjeta.BLOQUEADA)){ 
                        marcaPedidoOffline(pJDialog,prodBonifica,pNumPedVta);
                    }
                    daoLealtad.commit();
                }
                catch(Exception ex){
                    daoLealtad.rollback();
                    log.error("", ex);
                    bRetorno = false;
                    throw new Exception(""+ex.getMessage()); 
                }      
            }
        }*/
        daoLealtad.openConnection();
        String parametrosVenta = daoLealtad.obtenerParametrosVenta(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
        String[] parametros = parametrosVenta.split("@");
        String nroTarjeta = parametros[0];
        UtilityTransactionPuntos trx_Anulacion = new UtilityTransactionPuntos(pNumPedVta);
        trx_Anulacion.setVAnulaEnOrbis(true);
        trx_Anulacion.setVAnulaOnline(true);
        trx_Anulacion.setVTarjTransaccOrbis(nroTarjeta);
        boolean val = trx_Anulacion.previoAnulaPuntos();
        if(val){
            return trx_Anulacion.anulaOrbis();
        }else{
            return val;
        }
    }

    private void marcaPedidoOffline(JDialog pJDialog, String prodBonifica, String pNumPedVta) throws Exception {
        
        
        daoLealtad.isPermiteVtaOffline(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);            
        
       // throw new Exception(" FALLO NO PUEDE HACERSE VENTA OFFLINE" );
        //Se envia a offline
        if(!prodBonifica.equals("") && prodBonifica.equals("1")){
            //se quita los productos bonificados y el pedido se guarda como pendiente de enviar por el proceso offline        
            daoLealtad.eliminaProdBonificacion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);            
            //INI AOVIEDO 13/09/2017
            //FarmaUtility.showMessage(pJDialog,"Los productos bonificados han sido eliminados del pedido.",null);
            log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            log.info(":                                                                                               :");
            log.info(":::::::::::::::: LOS PRODUCTOS BONIFICADOS HAN SIDO ENTREGADOS DE MANERA OFFLINE ::::::::::::::::");
            log.info(":                                                                                               :");
            log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            //FIN AOVIEDO 13/09/2017
        }
    }

    /**
     * Redencion de puntos
     * @author ERIOS
     * @since 19.02.2015
     */
    public void ingresarPuntosRedencion(Frame myParentFrame, JDialog pJDialog, String pNumPedVta, JLabel lblPuntosRedimidos, JLabel lblMontoRedencion, JPanel panPuntosRedencion) {
        
        try {            
                
            if(!mostrarIngresoTarjeta(myParentFrame,pJDialog)){
                return;
            }
        
            daoLealtad.openConnection();
            
            String vRetorno = daoLealtad.getSaldoPuntos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            String[] pIndicadores = vRetorno.trim().split("@");
            
            //saldoPuntos = FarmaUtility.getDecimalNumber(pIndicadores[0]);
            //montoMinino = FarmaUtility.getDecimalNumber(pIndicadores[1]);
            //montoVenta = FarmaUtility.getDecimalNumber(pIndicadores[2]);
            String indPuntosRedimir = pIndicadores[3];
            
            Map calculo = daoLealtad.getPuntosMaximo(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            daoLealtad.commit();
            double montoMaximo,puntosMaximo;
            montoMaximo = FarmaUtility.getDecimalNumber(calculo.get("MONTO_MAXIMO").toString());
            puntosMaximo = FarmaUtility.getDecimalNumber(calculo.get("PTOS_MAXIMO").toString());
            
            //Muestra pantalla de redencion            
            DlgRedencionPuntos dlgRedencionPuntos = new DlgRedencionPuntos(myParentFrame,"",true,indPuntosRedimir);
            dlgRedencionPuntos.setFacadeLealtad(this);        
            dlgRedencionPuntos.setNumPedVta(pNumPedVta);
            dlgRedencionPuntos.setPuntosMaximo(puntosMaximo);
            dlgRedencionPuntos.setMontoMaximo(montoMaximo);
            dlgRedencionPuntos.setVisible(true);
            
            String puntos = dlgRedencionPuntos.getPuntos();
            String monto = dlgRedencionPuntos.getMonto();            
            obtieneDatosFormaPagoPedidoPuntos(puntos,monto);
            
            //Mostrar puntos redimidos
            lblPuntosRedimidos.setText(puntos);
            lblMontoRedencion.setText(monto);
            
            try{
                double ptos = Double.parseDouble(puntos);
                if(ptos == 0){
                    panPuntosRedencion.setVisible(false);
                }else{
                    panPuntosRedencion.setVisible(true);
                }
            }catch(Exception ex){
                log.info("ERROR AL REALIZAR LA CONVERSION DE PUNTOS REDIMIDOS");
                log.error("", ex);
                panPuntosRedencion.setVisible(false);
            }
            
            dlgRedencionPuntos = null;
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog,"Ha ocurrido un error inesperado.\n"+ex.getMessage(),null);            
        } 
    }

    public boolean muestraRendencion(JDialog pJDialog, String pNumPedVta, JPanel pnlPuntos,
                                    JPanel pnlPuntosAcumulados, 
                                     JLabel lblPuntosAcumulados_T, JLabel lblPuntosAcumulados, 
                                    JPanel pnlPuntosRedimidos, 
                                     JLabel lblPuntosRedimidos_T, JPanel pnlMontoRedencion,
                                    JPanel pnlPuntos2,
                                    JLabel lblPuntosRedimir_T, JLabel lblPuntosRedimir,
                                     JLabel lblSaldoPuntos_T, JLabel lblSaldoPuntos,
                                     JLabel lblF9){
        boolean bRetorno = false;
        if(UtilityPuntos.isActivoFuncionalidad() && VariablesPuntos.frmPuntos != null){
            BeanTarjeta tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
            if(tarjetaCliente!=null){
                
                if(tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.EXITO) && !(
                   tarjetaCliente.getEstadoTarjeta().equals(WSClientConstans.EstadoTarjeta.BLOQUEADA) ||
                    tarjetaCliente.getEstadoTarjeta().equals(WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR)
                   )){
                    
                    
                    bRetorno = calculoRedencion(pNumPedVta);                    
                }
                
                //LTAVARA 17.04.2015 Redencion en offline
                if( tarjetaCliente.getEstadoOperacion().equals(WSClientConstans.NO_CONEXION_ORBIS) && 
                    validaInitPedido(pNumPedVta)){
                    bRetorno = calculoRedencion(pNumPedVta);
                }
            }
            
            //ERIOS 31.03.2015 Redencion en offline
            if(tarjetaCliente==null && validaInitPedido(pNumPedVta)){

                bRetorno = calculoRedencion(pNumPedVta);                    
            }
                
            mostrarPanelPuntos(pnlPuntos, pNumPedVta, pnlPuntosAcumulados, lblPuntosAcumulados_T,lblPuntosAcumulados, pnlPuntosRedimidos,
                               lblPuntosRedimidos_T, pnlMontoRedencion, bRetorno, pnlPuntos2, lblPuntosRedimir_T, lblPuntosRedimir,
                               lblSaldoPuntos_T, lblSaldoPuntos);
            
        }
        pnlPuntosRedimidos.setVisible(false);
        return bRetorno;
    }

    private boolean calculoRedencion(String pNumPedVta) {
        double montoMinino,montoMaximo,puntosMaximo;
        double saldoPuntos,montoSaldo,montoVenta;
        String vRetorno;
        boolean bRetorno = false;
        try {
            daoLealtad.openConnection();
            
            vRetorno = daoLealtad.getSaldoPuntos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            String[] pIndicadores = vRetorno.trim().split("@");
            
            saldoPuntos = FarmaUtility.getDecimalNumber(pIndicadores[0]);
            montoMinino = FarmaUtility.getDecimalNumber(pIndicadores[1]);
            montoVenta = FarmaUtility.getDecimalNumber(pIndicadores[2]);
            
            Map calculo = daoLealtad.getPuntosMaximo(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            montoMaximo = FarmaUtility.getDecimalNumber(calculo.get("MONTO_MAXIMO").toString());
            puntosMaximo = FarmaUtility.getDecimalNumber(calculo.get("PTOS_MAXIMO").toString());
            
            if(puntosMaximo > 0.0){
                montoSaldo = FarmaUtility.getDecimalNumberRedondeado((saldoPuntos*montoMaximo)/puntosMaximo);
            }else{
                montoSaldo = 0.0;
            }
            
            //Determina        
            if(montoSaldo >= montoMinino && montoVenta >= montoMinino && montoMaximo > montoMinino){
                bRetorno = true;
            }
            
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        } 
        
        return bRetorno;
    }

    public boolean validaRedencion(JDialog pJDialog, Object pObjectFocus, String pNumPedVta, double puntosRedimir, double puntosMaximo, double montoMaximo) {
        double montoMinino,montoVenta,montoMaximoR;
        double saldoPuntos,montoSaldo,montoRedimir;
        String vRetorno;
        boolean bRetorno = false;
        
        if(!VariablesConvenioBTLMF.vHayDatosIngresadosConvenioBTLMF){
            try {
                daoLealtad.openConnection();
                
                vRetorno = daoLealtad.getSaldoPuntos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
                String[] pIndicadores = vRetorno.trim().split("@");
                
                saldoPuntos = FarmaUtility.getDecimalNumber(pIndicadores[0]);
                montoMinino = FarmaUtility.getDecimalNumber(pIndicadores[1]);
                montoVenta = FarmaUtility.getDecimalNumber(pIndicadores[2]);
                
                montoSaldo = FarmaUtility.getDecimalNumberRedondeado((saldoPuntos*montoMaximo)/puntosMaximo);
                
                //Determina maximo
                montoMaximoR = montoMaximo;
                if(montoSaldo < montoMaximoR){
                    montoMaximoR = montoSaldo;
                }
                if(montoVenta < montoMaximoR){
                    montoMaximoR =  montoVenta;
                }
                
                daoLealtad.commit();
                
                montoRedimir = cacularMonto(puntosRedimir,puntosMaximo, montoMaximo);
                
                //Determina        
                if(montoRedimir < montoMinino){
                    FarmaUtility.showMessage(pJDialog, "Debe ingresar un valor mayor.", pObjectFocus);
                }else if(montoRedimir > montoMaximoR){
                    FarmaUtility.showMessage(pJDialog, "Debe ingresar un valor menor.", pObjectFocus);
                }else if(montoRedimir >= montoMinino && montoRedimir <= montoMaximo){
                    bRetorno = true;
                }            
                
            } catch (Exception ex) {
                daoLealtad.rollback();
                log.error("", ex);
            } 
        }
        
        return bRetorno;
    }

    public String obtienePuntosRedimir(String pNumPedVta, double montoMaximo, double puntosMaximo,
                                       boolean pLabel) {
        double saldoPuntos,puntosRedimir,montoVenta,puntosVenta,montoMinino,puntosMinimo;
        String vRetorno = "";
        try {
            daoLealtad.openConnection();
            
            vRetorno = daoLealtad.getSaldoPuntos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            String[] pIndicadores = vRetorno.trim().split("@");
            
            saldoPuntos = FarmaUtility.getDecimalNumber(pIndicadores[0]);
            //montoMinino = FarmaUtility.getDecimalNumber(pIndicadores[1]);
            //puntosMinimo = FarmaUtility.getDecimalNumberRedondeado((puntosMaximo*montoMinino)/montoMaximo);
            if(pLabel)
            //DUBILLUZ 22.04.2015
                puntosMinimo = FarmaUtility.getDecimalNumber(pIndicadores[4]);
            else{
                montoMinino = FarmaUtility.getDecimalNumber(pIndicadores[1]);
                puntosMinimo = FarmaUtility.getDecimalNumberRedondeado((puntosMaximo*montoMinino)/montoMaximo);
            }
            
            
            montoVenta = FarmaUtility.getDecimalNumber(pIndicadores[2]);
            puntosVenta = FarmaUtility.getDecimalNumberRedondeado((puntosMaximo*montoVenta)/montoMaximo);
            
            if(saldoPuntos < 0.0) saldoPuntos = 0.0;
            
            //Determina maximo
            puntosRedimir = puntosMaximo;
            if(saldoPuntos < puntosRedimir){
                puntosRedimir = saldoPuntos;
            }
            if(puntosVenta < puntosRedimir){
                puntosRedimir = puntosVenta;
            }
            if(puntosMinimo >= puntosRedimir){
                puntosRedimir = 0.0;
            }
            vRetorno = FarmaUtility.formatNumber(puntosRedimir);
            
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        } 
        
        return vRetorno;
    }

    public double cacularMonto(double puntosRedimir, double puntosMaximo, double montoMaximo) throws Exception{
        double montoRedimir=0;
        
        montoRedimir = FarmaUtility.getDecimalNumberRedondeado((puntosRedimir*montoMaximo)/puntosMaximo);
            
        return montoRedimir;
    }

    
    public void obtieneDatosFormaPagoPedidoPuntos(String puntos, String monto) {
        //VariablesCaja.vValEfectivo = "";

        VariablesCaja.vCodFormaPago = ConstantsCaja.FORMA_PAGO_EFECTIVO_PUNTOS;
        VariablesCaja.vDescFormaPago = ConstantsCaja.DESC_FORMA_PAGO_PUNTOS;
        VariablesCaja.vCantidadCupon = "0";

        VariablesCaja.vCodMonedaPago = ConstantsCaja.EFECTIVO_SOLES;
        VariablesCaja.vDescMonedaPago =
                FarmaLoadCVL.getCVLDescription(FarmaConstants.HASHTABLE_MONEDA, VariablesCaja.vCodMonedaPago);

        VariablesCaja.vValMontoPagado = FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(puntos));//puntos

        VariablesCaja.vValTotalPagado = monto;//soles
        
        VariablesCaja.vNumPedVtaNCR = "";
    }

    private void mostrarPanelPuntos(JPanel pnlPuntos, String pNumPedVta, 
                                    JPanel pnlPuntosAcumulados, 
                                    JLabel lblPuntosAcumulados_T, JLabel lblPuntosAcumulados, 
                                    JPanel pnlPuntosRedimidos, 
                                    JLabel lblPuntosRedimidos_T, JPanel pnlMontoRedencion,
                                    boolean mostrarRedencion,
                                    JPanel pnlPuntos2,
                                    JLabel lblPuntosRedimir_T, JLabel lblPuntosRedimir,
                                    JLabel lblSaldoPuntos_T, JLabel lblSaldoPuntos) {
        pnlPuntos.setVisible(true);
        pnlPuntos2.setVisible(true);
        try {
            daoLealtad.openConnection();
            
            String vRetorno = daoLealtad.getIndicadoresPuntos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            String[] pIndicadores = vRetorno.trim().split("@");
            
            String puntosAcumulados = pIndicadores[0];
            String verPuntosAcumulados = pIndicadores[1];
            String verPuntosRedimidos = pIndicadores[2];
            String verMontoRedencion = pIndicadores[3];
            String saldoPuntos = pIndicadores[4];
            String msjSaldoPuntos = pIndicadores[5];
            String msjPuntosMaximo = pIndicadores[6];
            String msjPuntosAcumulados = pIndicadores[7];
            String msjPuntosRedimidos = pIndicadores[8];
            
            Map calculo = daoLealtad.getPuntosMaximo(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            double puntosMaximo,montoMaximo;
            montoMaximo = FarmaUtility.getDecimalNumber(calculo.get("MONTO_MAXIMO").toString());
            puntosMaximo = FarmaUtility.getDecimalNumber(calculo.get("PTOS_MAXIMO").toString());
            
            daoLealtad.commit();
            
            String strPuntosMaximo = obtienePuntosRedimir(pNumPedVta, montoMaximo, puntosMaximo,true);
                                                        
            pnlPuntosAcumulados.setVisible(verPuntosAcumulados.equals(FarmaConstants.INDICADOR_S));
            lblPuntosAcumulados_T.setText(msjPuntosAcumulados);
            lblPuntosAcumulados.setText(puntosAcumulados);
            
            lblPuntosRedimidos_T.setText(msjPuntosRedimidos);
            
            //if(mostrarRedencion){
                pnlPuntosRedimidos.setVisible(verPuntosRedimidos.equals(FarmaConstants.INDICADOR_S));
                pnlMontoRedencion.setVisible(verMontoRedencion.equals(FarmaConstants.INDICADOR_S));                
            //}
            
            lblSaldoPuntos_T.setText(msjSaldoPuntos);
            lblSaldoPuntos.setText(saldoPuntos);
            
            // DUBILLUZ 22.04.2015   
            double ptoMostrar =0.0;
            try {
                ptoMostrar = FarmaUtility.getDecimalNumber(strPuntosMaximo);
            } catch (Exception e) {
                // TODO: Add catch code
                log.error("",e);
                ptoMostrar=0.0;
            }
            if(ptoMostrar>0){
                lblPuntosRedimir_T.setText(msjPuntosMaximo);
                lblPuntosRedimir.setText(strPuntosMaximo);
            }
            else{
                lblPuntosRedimir_T.setText("");
                lblPuntosRedimir.setText("");
            }
            // DUBILLUZ 22.04.2015
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        }
    }

    public void ingresarNotaCredito(Frame myParentFrame, JDialog pJDialog, String pNumPedVta, String pMontoAPagar) {
        double dblTotalAPagar = FarmaUtility.getDecimalNumber(pMontoAPagar);
        if(dblTotalAPagar <= 0.0){
            FarmaUtility.showMessage(pJDialog, "El monto del pedido ya fue cubierto.", null);      
        }else{
            DlgConsultaXCorrelativo dlgConsulta = new DlgConsultaXCorrelativo(myParentFrame, "", true);
            dlgConsulta.cargaComboNCR();
            dlgConsulta.setVisible(true);
            
            if (FarmaVariables.vAceptar) {            
                if(verificaUsoNCR(pJDialog,VariablesVentas.vNumPedVta_new,VariablesVentas.vFechaPedVta_new)){
                    
                    String strMontoNCR = getMontoNCR(VariablesVentas.vNumPedVta_new);
                    double dblMontoNCR = FarmaUtility.getDecimalNumber(strMontoNCR);
                    String vMonto = "0.0";
                    if(dblMontoNCR > dblTotalAPagar){
                        vMonto = pMontoAPagar;
                    }else{
                        vMonto = strMontoNCR;
                    }
                    obtieneDatosFormaPagoPedidoNCR(VariablesVentas.vNumPedVta_new,vMonto);
                }else{
                    FarmaVariables.vAceptar = false;
                }
            }
        }
    }

    public void obtieneDatosFormaPagoPedidoNCR(String pNumPedVtaNCR, String vMonto) {
        VariablesCaja.vCodFormaPago = ConstantsCaja.FORMA_PAGO_EFECTIVO_NCR;
        VariablesCaja.vDescFormaPago = ConstantsCaja.DESC_FORMA_PAGO_NCR;
        VariablesCaja.vCantidadCupon = "0";

        VariablesCaja.vCodMonedaPago = ConstantsCaja.EFECTIVO_SOLES;
        VariablesCaja.vDescMonedaPago =
                FarmaLoadCVL.getCVLDescription(FarmaConstants.HASHTABLE_MONEDA, VariablesCaja.vCodMonedaPago);

        VariablesCaja.vValMontoPagado = vMonto;

        VariablesCaja.vValTotalPagado = vMonto;
        
        VariablesCaja.vNumPedVtaNCR = pNumPedVtaNCR;
    }

    private boolean verificaUsoNCR(JDialog pJDialog, String pNumPedVtaNCR, String pFechaNCR) {
        boolean bRetorno = false;
        try {
            daoLealtad.openConnection();            
            String uso;                        
            //1. Valida dias de uso
            uso = daoLealtad.verificaFechaNCR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,pNumPedVtaNCR,pFechaNCR);
            if(uso.equals(FarmaConstants.INDICADOR_S)){
                FarmaUtility.showMessage(pJDialog, "La NCR ha excedido el tiempo de uso.", null);            
            }else{
                //0. Valida documento credito
                //uso= daoLealtad.verificaCreditoNCR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,pNumPedVtaNCR,pFechaNCR);
                //if(uso.equals(FarmaConstants.INDICADOR_S)){
                //    FarmaUtility.showMessage(pJDialog, "La NCR corresponde a un comprobante de cr�dito.", null);    
                //}else{            
                    //2. Valida uso de NCR
                    uso = daoLealtad.verificaUsoNCR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,pNumPedVtaNCR,"NCR");
                    if(uso.equals(FarmaConstants.INDICADOR_S)){
                        FarmaUtility.showMessage(pJDialog, "La NCR ya fue usada. No puede registrarlo.", null);
                    }else{
                        bRetorno = true;
                    }
                //}
            }
        } catch (Exception ex) {            
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Ha ocurrido un error al consultar la NCR.\n"+ex.getMessage(), null);
        }finally{
            daoLealtad.rollback();
        }
        return bRetorno;
    }

    public boolean mostrarIngresoTarjeta(Frame myParentFrame, JDialog pJDialog) throws Exception {
        boolean isMuestraPantalla = UtilityPuntos.validaDocumentoRedimirBonificar(myParentFrame, pJDialog, ConstantsPuntos.REDENCION_PTOS);    
        return isMuestraPantalla;
    }

    private String getMontoNCR(String pNumPedVtaNCR) {
        String vRetorno = "";
        try {
            daoLealtad.openConnection();
            vRetorno =
                    daoLealtad.getMontoNCR(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                              pNumPedVtaNCR);
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        }

        return vRetorno;
    }

    private boolean validaInitPedido(String pNumPedVta) {
        double saldoPuntos;
        boolean bRetorno = false;
        try {
            daoLealtad.openConnection();
            
            String vRetorno = daoLealtad.getSaldoPuntos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNumPedVta);
            String[] pIndicadores = vRetorno.trim().split("@");
            
            saldoPuntos = FarmaUtility.getDecimalNumber(pIndicadores[0]);        
            
            if(saldoPuntos > 0){
                bRetorno = true;
            }
            
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        }
        
        return bRetorno;
    }

    public String getMultiploIngresoPtos() {
        String vRetorno = "0.0";
        try {
            daoLealtad.openConnection();
            vRetorno = daoLealtad.getMultiploPtos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal);
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        } 
        return vRetorno;
    }    
    
    /**
     * Registra inscripcion por turno
     * @author ERIOS
     * @since 22.06.2015
     * @param pSecMovCaja
     * @param pNroTarjetaFidelizado
     */
    public void registrarInscripcionTurno(String pSecMovCaja, String pNroTarjetaFidelizado) {
        try {
            daoLealtad.openConnection();
            daoLealtad.registrarInscripcionTurno(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                              pSecMovCaja,pNroTarjetaFidelizado,FarmaVariables.vIdUsu);
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        }
    }

    /**
     * Obtiene la cantidad de vouchers por inscripcion
     * @author ERIOS
     * @since 22.06.2015
     * @param pSecMovCaja
     * @return
     */
    public String getInscripcionTurno(String pSecMovCaja) {
        String strRetorno = "";
        try {
            daoLealtad.openConnection();
            strRetorno = daoLealtad.getInscripcionTurno(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal,
                                              pSecMovCaja);
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        }
        return strRetorno;
    }
    
    public void rechazarIncripcionPuntos(String pNroDocumento, String pSecMovCaja, String pNroTarjetaFidelizado) {
        try {
            daoLealtad.openConnection();
            daoLealtad.rechazarIncripcionPuntos(FarmaVariables.vCodGrupoCia, pNroDocumento, pSecMovCaja,pNroTarjetaFidelizado);
            daoLealtad.commit();
        } catch (Exception ex) {
            daoLealtad.rollback();
            log.error("", ex);
        }
    }
}
