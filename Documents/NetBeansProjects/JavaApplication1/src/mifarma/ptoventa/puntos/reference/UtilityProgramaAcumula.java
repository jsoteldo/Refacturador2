package mifarma.ptoventa.puntos.reference;


import com.gs.mifarma.componentes.JConfirmDialog;

import farmapuntos.FarmaPuntos;

import farmapuntos.bean.BeanAfiliado;
        
import farmapuntos.bean.BeanClientOrbis;
import farmapuntos.bean.BeanTarjeta;
import farmapuntos.bean.BeanOperacion;
import farmapuntos.FarmaPuntos;

import farmapuntos.orbis.WSClientConstans;

import java.awt.Frame;
import java.awt.HeadlessException;

import mifarma.electronico.UtilityImpCompElectronico;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convenioBTLMF.reference.DBConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.fidelizacion.reference.ConstantsFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.DBFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.OperacionDTO;
import mifarma.ptoventa.fidelizacion.reference.UtilityFidelizacion;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.lealtad.dao.MBLealtad;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.programaXmas1.facade.ProgramaXmas1Facade;
import mifarma.ptoventa.puntos.DlgRegaloAcumula;
import mifarma.ptoventa.puntos.DlgValidaDocumento;
import mifarma.ptoventa.puntos.DlgVerificaDocRedencionBonifica;
import mifarma.ptoventa.puntos.modelo.BeanAfiliadoLocal;
import mifarma.ptoventa.puntos.modelo.BeanListaProductos;
import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.ventas.DlgResumenPedido;
import mifarma.ptoventa.ventas.reference.BeanDetalleVenta;
import mifarma.ptoventa.ventas.reference.BeanInfoPrecioProd;
import mifarma.ptoventa.ventas.reference.UtilityCalculoPrecio;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityProgramaAcumula {

    private static final Logger log = LoggerFactory.getLogger(UtilityProgramaAcumula.class);

    private static final String ESTADO_PENDIENTE = "P";
    private static final String SIMBOLO_CERO = "0";
    private static final String ALINEACION_IZQUIERDA = "I";     


    private static String  ESTADO_TEMPORAL_PROGRAMA = "T";
    private static Integer COL_COD_PROG = 1;
    private static String  RETORNO_INSCRITO = "S";
    private static String  RETORNO_NO_INSCRITO = "N";
    
    public static String pAccion = "N";
    public static ArrayList vListaAceptaRegaloPedido = new ArrayList();
    public static ArrayList vListaRechazoRegaloPedido = new ArrayList();
    
    public static boolean vAutomaticoIngresoCantidad = false;
    public static String vCantidad = "";
    
    public static boolean SinDNI_QuitarBonificados = false;
    public static boolean PermiteContinuar_DNI =false;
    
    public UtilityProgramaAcumula() {
    }
    
    public static String afiliar_programa_dni(String pCodProd) {
        ProgramaXmas1Facade programaXmas1Facade;
        BeanTarjeta tarjetaBean = null;
        String codProgramaDesafiliado="N";
        List<BeanResultado> listBeanResultado =  new ArrayList();
        try {
            listBeanResultado = listaAcumulaX1noParseado(pCodProd);
            programaXmas1Facade = new ProgramaXmas1Facade();
            ArrayList listaProgramas = programaXmas1Facade.parsearResultadoArray(listBeanResultado);
            
            
            codProgramaDesafiliado = FarmaUtility.getValueFieldArrayList(listaProgramas,0,0);
            tarjetaBean = VariablesPuntos.frmPuntos.getBeanTarjeta();
                
                programaXmas1Facade.registrarProgramasTempX1(tarjetaBean.getDni(), 
                                                             codProgramaDesafiliado,
                                                             pCodProd,
                                                             ESTADO_TEMPORAL_PROGRAMA,
                                                             listBeanResultado);
            if (tarjetaBean != null) {
                if (tarjetaBean.getEstadoOperacion().equals(WSClientConstans.EXITO)) {
                    log.debug("Exito al Afiliar");
                    /*FarmaUtility.showMessage(new JDialog(),
                                             "Se afilió de forma atumática de forma exitosa", 
                                             null);*/
                    return "S";
                } else {
                    if (tarjetaBean.getEstadoOperacion().equals(WSClientConstans.NO_CONEXION_ORBIS)) {
                        FarmaUtility.showMessage(new JDialog(),
                                                 UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaBean.getEstadoOperacion(),
                                                                                          ""), null);
                        return "N";
                    } else {
                        FarmaUtility.showMessage(new JDialog(), tarjetaBean.getMensaje(), null);
                        return "N";
                    }
                }
            }
            
        } catch (Exception e) {
            //this.retorno = RETORNO_NO_INSCRITO;
            log.error("Error al Registrar Programa[DlgListaAcumulaX1] + NO SE PUDO AFILIAR", e);
        } 
        //private final String  RETORNO_INSCRITO = "S";
        //private final String  RETORNO_NO_INSCRITO = "N";
        return "N";
    }
    
    

        public static List<BeanResultado> listaAcumulaX1noParseado(String pCodProd) {
            List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
            MBLealtad daoLealtad = new MBLealtad();
            try {
                daoLealtad.openConnection();
                listBeanResultado = daoLealtad.listaAcumulaX1(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, 
                                                              pCodProd);
                daoLealtad.commit();
            } catch (Exception ex) {
                FarmaUtility.showMessage(new JDialog(),"Error al obtener lista programas por producto"+
                                        "ProgramaXmas1Facade\n"+ex.getMessage(),null);
            }
            return listBeanResultado;
        }
        
    /**
         *
         * @param pJDialog
         * @param pNumPedVta
         * @param myParentFrame
         * @param isLlevaPromociones
         * @param pIngresoComprobanteManual
         * @param plistacodProdEqItemQUOTE
         * @param pEsperaProceso "S" -> el proceso QUOTE se ejecuta sin Hilo. "N"-> el proceso QUOTE se ejecuta en hilo
         * @throws Exception
         */    
    public static void enviarPedidoPuntos(JDialog pJDialog,Frame myParentFrame, 
                                          boolean pIngresoComprobanteManual
                          )throws Exception{
        
        
        BeanTarjeta tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
        ArrayList lstProdBonifRechazados = new ArrayList();
        String auxLista = "";
        boolean isLlevaRegalos=false;
        boolean isMuestraPantalla = true;
        FarmaVariables.vAceptar = true;
        if(tarjetaPuntos!=null){
            if(WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())
            || WSClientConstans.EstadoTarjeta.SIN_ESTADO.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())){
                log.info("PROGRAMA DE PUNTOS [QUOTE] : Proceso en Offline o tarjeta sin estado, cancela operacion de quote.");
                pAccion = "C";
                return;
            }
            //ERIOS 13.04.2016 Determinar cuando debe esperar la respuesta o ejecutar por hilo.
            isMuestraPantalla = procesarQuote(tarjetaPuntos);
            
            List lstProducto = tarjetaPuntos.getListaBonificados();
            for(int i=0;i<lstProducto.size();i++){
                String aux = (String)lstProducto.get(i);
                if(auxLista.length()>0){
                    auxLista = auxLista + "@";
                }
                auxLista = auxLista + aux;
            }
            log.info("PROGRAMA DE PUNTOS [QUOTE] : LISTA DE PRODUCTOS, \nRESPUESTA DE PROVEEDOR "+auxLista);

            ArrayList lstBonificados = new ArrayList();
            Long t1 = System.currentTimeMillis();
            DBPuntos.getListaBonificados(lstBonificados,FarmaVariables.vCodGrupoCia,FarmaVariables.vCodLocal,auxLista);
            Long t2 = System.currentTimeMillis();
            log.info("PROGRAMA DE PUNTOS [QUOTE] : tiempo de metodo DB verificaBonificados "+(t2-t1)+"\ntotal de productos -->"+lstProducto.size()+"\n"+lstProducto+
                     "\ntotal de bonificados --> "+lstBonificados.size()+"\nlista "+lstBonificados);
            
            boolean pIndPedConvenio = false;
            if (VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
                pIndPedConvenio = true;
            }
            //ERIOS 31.03.2016 No bonifica para convenios.
            if(pIndPedConvenio || pIngresoComprobanteManual){
                isMuestraPantalla = false;
                if(lstBonificados.size()>0){
                    //Agregar lista de rechazados
                    for(int i=0;i<lstBonificados.size();i++){
                        int cantidadOfrecida = (int)Integer.parseInt((String)((ArrayList)lstBonificados.get(i)).get(5));
                        lstProdBonifRechazados.add(((ArrayList)lstBonificados.get(i)).get(6)+","+cantidadOfrecida);
                    }
                    tarjetaPuntos.setListaBonifRechazo(lstProdBonifRechazados); //LTAVARA 2016.11.24 GUARDA LA LISTA DE BONIFICACIONES A RECHAZAR 
                    VariablesPuntos.frmPuntos.rechazarBonificado(lstProdBonifRechazados, UtilityPuntos.getDNI_USU());
                }
            }
            
            // SE MUESTRA PANTALLA DE BONIFICADOS
            if(isMuestraPantalla && lstBonificados.size()>0&&!pIndPedConvenio){
                boolean vPreRechazo = false;
                        log.info("PROGRAMA DE PUNTOS [QUOTE] : ENCONTRO PRODUCTOS BONIFICADOS "+lstBonificados);
                        DlgRegaloAcumula dlg = new DlgRegaloAcumula(myParentFrame, "", true);
                        dlg.setLstProductoBonificados(lstBonificados);
                        dlg.setVisible(true);
                        ArrayList lstProdBonif = new ArrayList();
                    
                        if(FarmaVariables.vAceptar){
                            ArrayList vListaRegalosAceptados = new ArrayList();
                            vListaRegalosAceptados = dlg.getLista_regalo();
                            if(vListaRegalosAceptados.size()>0)
                                isLlevaRegalos = true;
                            else
                                isLlevaRegalos = false;
                            
                            boolean escaneoDocumento = false;
                            if(isLlevaRegalos){
                                // NO PEDIRA DNI , si el regalo es porque lo acumulo todo aqui.
                                //los regalos aceptados
                            boolean obligaDNI = true;
                                BeanTarjeta tarjetaCliente =   VariablesPuntos.frmPuntos.getGanarBonificaciones(tarjetaPuntos.getNumeroTarjeta(),
                                                                                                               "001");
                              if(tarjetaCliente!=null && !tarjetaCliente.getListaPendienteAcumular().isEmpty()){
                                       List lstProductoNuevo = tarjetaCliente.getListaPendienteAcumular();
                                       for(int i=0;i<lstProductoNuevo.size();i++){
                                           String[] prod = lstProducto.get(i).toString().split(",");
                                           String codProdEQ=prod[0];
                                           if(codProdEQ.length()>6){
                                               String cantPendAcum=prod[1];
                                               log.info(codProdEQ.substring(8)+" "+codProdEQ + " "+" faltan "+cantPendAcum);    
                                           }
                                       }
                                   }
                                else{
                                    log.info("No afiliado a nada >> save_hist_acumulado_x_dni");
                                    obligaDNI = false;
                                }                
                                    
                                if(obligaDNI){
                                    UtilityProgramaAcumula.PermiteContinuar_DNI = true;
                                    // validara que el regalo da y no tiene balance el item.
                                    escaneoDocumento = validaDocumentoObligatorio();
                                    UtilityProgramaAcumula.PermiteContinuar_DNI = true;
                                    if(!escaneoDocumento){
                                        log.info("NO ESCANEO EL DOCUMENTO >>>> SE RECHAZARAN TODOS LOS BONIFICADOS");
                                        isLlevaRegalos = false;
                                        vPreRechazo = rechazaRegaloEnValidaTarjetaDNI();
                                    }
                                    else{
                                        log.info("SI ESCANEO EL DOCUMENTO >>>> CONTINUA CON EL COBRO");
                                    }
                                    
                                }
                                else{
                                    escaneoDocumento = true;
                                    UtilityProgramaAcumula.PermiteContinuar_DNI = true;
                                }
                                    
                                
                                
                                    
                                
                            }
                            
                            // si no escaneo el DNI debe de mandar a resumen de pedido
                            
                            if(isLlevaRegalos && escaneoDocumento)
                            {   
                                lstProdBonif = UtilityProgramaAcumula.vListaAceptaRegaloPedido;
                            }
                            
                            if(!vPreRechazo){
                                // cargar lista de rechazos que se deben hacer
                                for(int i=0;i<UtilityProgramaAcumula.vListaRechazoRegaloPedido.size();i++){
                                    int cantidadOfrecida = (int)Integer.parseInt((String)((ArrayList)UtilityProgramaAcumula.vListaRechazoRegaloPedido.get(i)).get(5));
                                    lstProdBonifRechazados.add(((ArrayList)UtilityProgramaAcumula.vListaRechazoRegaloPedido.get(i)).get(6)+","+cantidadOfrecida);
                                }
                                
                                    if(lstProdBonifRechazados.size()>0){
                                        log.info("PROGRAMA DE PUNTOS [RECHAZA BONIFICADO] : PROCESA RECHAZADOS BONIFICADOS "+lstProdBonif+"\n Rechazados --> "+lstProdBonifRechazados);
                                        VariablesPuntos.frmPuntos.rechazarBonificado(lstProdBonifRechazados, UtilityPuntos.getDNI_USU());
                                        tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
                                        if(tarjetaPuntos != null){
                                            log.info("PROGRAMA DE PUNTOS [QUOTE] : RECHAZO DE BONIFICADOS \n"+
                                                     "Estado Operacion: "+tarjetaPuntos.getEstadoOperacion()+
                                                     "\n Estado Tarjeta: "+ tarjetaPuntos.getEstadoTarjeta()+
                                                     "\n Mensaje: "+tarjetaPuntos.getMensaje()+
                                                     "\n RESPUESTA DE RECHAZO -->"+tarjetaPuntos.getListaBonificados());
                                            
                                            if(WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                                                FarmaUtility.showMessage(pJDialog, "Programa Monedero: Por el momento no se podra bonificar productos.\n" +
                                                                                    "Solo se podra acumular puntos.", null);
                                                log.info("RECHAZO FALLO:CONTINUAR OFFLINE ");
                                                pAccion = "C";
                                                return ;
                                            }else{
                                                if(WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta()) 
                                                ){
                                                    FarmaUtility.showMessage(pJDialog, "Programa Monedero:\nTarjeta Bloqueada para redencion.\n" +
                                                                                        "Se cancela la bonificación de Productos y solo se podra acumular puntos.", null);
                                                    pAccion = "C";
                                                    return ;
                                                }else{
                                                    if(WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta()) ||
                                                        WSClientConstans.EstadoTarjeta.INVALIDA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta()) ||
                                                        WSClientConstans.EstadoTarjeta.BLOQUEADA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                                                    ){
                                                        String texto = UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPuntos.getEstadoOperacion(), tarjetaPuntos.getMensaje());
                                                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                                        FarmaUtility.showMessage(pJDialog, "Programa Monedero:\n" + texto + 
                                                                                           "Se cancelara la bonificación de Productos y " +
                                                                                           "los puntos acumulados en esta venta." , null);
                                                        pAccion = "C";
                                                        return ;
                                                    }else{
                                                        if(!WSClientConstans.EXITO.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                                                            log.info("PROGRAMA DE PUNTOS [QUOTE] : ERROR INESPERADO ");
                                                            FarmaUtility.showMessage(pJDialog, "Programa Monedero:\n"+
                                                                                               "Error inesperado: Se cancela la bonificación de productos. Continua con la venta", null);
                                                            log.info("RECHAZO FALLO:CONTINUAR OFFLINE ");
                                                           // VariablesPuntos.frmPuntos.eliminarTarjetaBean();
                                                            //no proceder el método porque tiene productos para rechazar
                                                            //procesarQuoteSinBonificados(tarjetaPuntos, pNumPedVta);
                                                        }else{
                                                            //LTAVARA 2016.11.24 GUARDA LA LISTA DE BONIFICACIONES A RECHAZAR 
                                                            tarjetaPuntos.setListaBonifRechazo(lstProdBonifRechazados);
                                                            List lstProductoQ = tarjetaPuntos.getListaBonificados();
                                                            String auxLista2 = "";
                                                            for(int i=0;i<lstProductoQ.size();i++){
                                                                String aux = (String)lstProductoQ.get(i);
                                                                if(auxLista2.length()>0){
                                                                    auxLista2 = auxLista2 + "@";
                                                                }
                                                                auxLista2 = auxLista2 + aux;
                                                            }
                                                            
                                                            /*DBPuntos.ejecutarQuote(FarmaVariables.vCodGrupoCia, 
                                                                                   FarmaVariables.vCodLocal, 
                                                                                   pNumPedVta, 
                                                                                   auxLista2);*/
                                                            pAccion = "C";
                                                            return ;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }else{
                                        log.info("PROGRAMA DE PUNTOS [QUOTE] : ELIJIO TODOS BONIFICADOS " + lstProdBonif + "\n AUXILIAR --> " + auxLista);
                                    }
                            }
                            
                                
                            if(!isLlevaRegalos)
                                pAccion = "C";
                            else{
                                if(escaneoDocumento){
                                   pAccion = "C";
                                }
                                else{
                                  FarmaUtility.showMessage(new JDialog(), "Debe de escanear el documento de cliente si va llevar los regalos.", null);
                                  pAccion = "P";
                                }     
                            }   
                        }else{
                            log.info("PROGRAMA DE PUNTOS [QUOTE] : CERRO LA VENTANA DE PRODUCTOS BONIFICADOS, PRESIONO ESC");
                            pAccion = "C";
                        }
                        
                }else{
                    log.info("PROGRAMA DE PUNTOS [QUOTE] : NO ENCONTRO PRODUCTOS BONIFICADOS ");
                log.info("PROGRAMA DE PUNTOS: [QUOTE] MUESTRA PANTALLA : variable --> "+isMuestraPantalla+" INDICADOR DB  --> "+DBPuntos.indicadorMuestrPantallaBonifica());
                pAccion = "C";
            }
        }else{
            pAccion = "C";
            log.info("PROGRAMA DE PUNTOS: [QUOTE] TARJETA NO INICIALIZADA");
        }
    }        
    
    /**
     * REALIZA LA OPERACION DE QUOTE CON PROVEEDOR NORMAL INCLUYENDO LOS PRODUCTOS MODIFICADOS.
     * @author DESARROLLO3
     * @since 2015.03.26
     * @param tarjetaPuntos
     * @param pNumPedVta
     * @return
     * @throws Exception
     */
    public static boolean procesarQuote(BeanTarjeta tarjetaPuntos, String pNumPedVta, String pEsperaProceso)throws Exception{
        log.info("PROGRAMA DE PUNTOS [QUOTE] : QUOTE CON BONIFICADOS");
        if (pEsperaProceso.equals("S")) {
            return procesarQuote(tarjetaPuntos);
        } else {
     HiloFarmaPuntos hilo = new HiloFarmaPuntos("HILO_QUOTE", tarjetaPuntos,  pNumPedVta,  true);
      hilo.start();
        }
     return true;
    }    

    /**
     * REALIZA LA OPERACION DEL QUOTE CON PROVEEDOR
     * @author DESARROLLO3 
     * @since 2015.03.26
     * @param tarjetaPuntos
     * @param pNumPedVta
     * @param isAdicionarBonificado indica si lista de envio incluira productos bonificados
     * @return
     * @throws Exception
     */
    public static boolean procesarQuote(BeanTarjeta tarjetaPuntos)throws Exception{
        VariablesPuntos.frmPuntos.getBeanTarjeta().setIdTransaccion("");
        try {
            log.info("" + tarjetaPuntos.getListaInscritos().size());
            log.info("inscritos " + tarjetaPuntos.getListaInscritos());
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
        boolean resultado = true;
        // LISTA DE PRODUCTOS PARA QUOTE
        Long t1 = System.currentTimeMillis();
        ArrayList listaProducto = new ArrayList();
        Long t2 = System.currentTimeMillis();
        // lista de productos normales //
        for(int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++){
              BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i); 
              boolean vEquivalente = false;
              
            try {
                if (bean.getPCodEQCampAcumula().trim().length() > 0) {
                    String vCodEq = bean.getPCodEQCampAcumula().trim();
                    if (vCodEq.indexOf(bean.getPCOD_PROD()) == -1) {
                        bean.setPCodEQCampAcumula("");
                        log.info("01 Producto no pertenece a la campana equivalente asociada");
                    }
                }
            } catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
                log.info(e.getMessage());
            }
             log.info("bean.isPIndCampAcumula(): "+bean.isPIndCampAcumula()); 
            //si es true va al else; true es no acumula.
             if(!bean.isPIndCampAcumula()){
                 String codProd=""; 
                 if(bean.getPCodEQCampAcumula().trim().length()>0){
                     String vCodEq = bean.getPCodEQCampAcumula().trim();
                     if (vCodEq.indexOf(bean.getPCOD_PROD()) == -1) {
                         bean.setPCodEQCampAcumula("");
                         log.info("02 Producto no pertenece a la campana equivalente asociada");
                         codProd=bean.getPCOD_PROD();
                         vEquivalente = false;
                     }
                     else{
                         codProd=bean.getPCodEQCampAcumula(); 
                         vEquivalente = true;    
                     }
                 }
                 else
                    codProd=bean.getPCOD_PROD();
                 
                 String cantidad= bean.getVCtdVta();
                 String prec_unit= "1";
                 double subTotal= Double.parseDouble(bean.getVSubTotal().replace(",",""));
                 //String puntosAcumulados = bean.getVSubTotal();
                 String puntosExtra = "";
                 String pCadAhorro= bean.getPAHORRO_REDONDEO().replace(",","");
                 if(pCadAhorro!=null&&pCadAhorro.trim().length()>0)
                    puntosExtra = ""+(int)(Double.parseDouble(pCadAhorro)*100);
                 else
                    puntosExtra = ""+"0";
                 
                 ArrayList vCadena = new ArrayList();
                 
                 //INICIO RPASCACIO 15.06.2017
                 if(codProd.length()>6){
                     log.info("Antes de getConversionFraccion >> "+"procesarQuote");
                      cantidad=DBPuntos.getConversionFraccion(bean.getPCOD_PROD(),
                                                              bean.getPCodEQCampAcumula(),
                                                              bean.getVValFracVta(),
                                                              cantidad);
                 }
                 //FIN RPASCACIO 15.06.2017
                 
                 if(codProd.trim().length()>6){
                     
                     if(vIsClienteInscrito(tarjetaPuntos,codProd)){
                         vCadena.add(codProd+","+
                                     cantidad+","+
                                      prec_unit+","+
                                      subTotal+","+
                                      "0"+","+
                                      puntosExtra);
                         
                         listaProducto.add(vCadena);
                     }
                     else{
                         if(!isExistePACK_VIGENTE_CampAcum(codProd)){
                             vCadena.add(codProd+","+
                                         cantidad+","+
                                          prec_unit+","+
                                          subTotal+","+
                                          "0"+","+
                                          puntosExtra);
                             
                             listaProducto.add(vCadena);
                         }
                     }
                 }
                 else{
                         
                    vCadena.add(codProd+","+
                                cantidad+","+
                                 prec_unit+","+
                                 subTotal+","+
                                 "0"+","+
                                 puntosExtra);
                 
                 listaProducto.add(vCadena);      
                 
                 }
                 //vEquivalente = false;
                 if(vEquivalente){
                     codProd=bean.getPCOD_PROD();
                     
                    
                     ArrayList vCadena2 = new ArrayList();
                     vCadena2.add(codProd+","+
                                 bean.getVCtdVta()+","+
                                  "1"+","+
                                  Double.parseDouble(bean.getVSubTotal().replace(",",""))+","+
                                  Double.parseDouble(bean.getVSubTotal().replace(",",""))+","+
                                  ""+(int)(Double.parseDouble(bean.getPAHORRO_REDONDEO().replace(",",""))*100)
                                                       );
                     
                     listaProducto.add(vCadena2);                                                                                                                   
                     
                     vEquivalente = false;
                     
                 }
            }
        }
        
        // Lista de producto Promociones //
        for(int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++){
            ArrayList vCadena2 = new ArrayList();
            String codProd = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones, i, 0);
            String ctd = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones, i, 4);
            String SubtTotal = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones, i, 6);
            vCadena2.add(codProd+","+
                         ctd+","+
                         "1"+","+
                         SubtTotal+","+
                         SubtTotal+","+
                         "0");
            
            listaProducto.add(vCadena2);               
        }
        
        
        // dubilluz 20171020
        for(int i = 0; i < VariablesVentas.vArrayList_Prod_Promociones.size(); i++){
            ArrayList vCadena2 = new ArrayList();
            String codProd_EQ = getObtenerCodEQ_PACK(FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones, i, 0));
            String ctd = FarmaUtility.getValueFieldArrayList(VariablesVentas.vArrayList_Prod_Promociones, i, 4);
            String SubtTotal = "1";
            if(!codProd_EQ.trim().equalsIgnoreCase("N")){
                if(vIsClienteInscrito(tarjetaPuntos,codProd_EQ)){
                    vCadena2.add(codProd_EQ+","+
                                 ctd+","+
                                 "1"+","+
                                 SubtTotal+","+
                                 SubtTotal+","+
                                 "0");    
                    listaProducto.add(vCadena2);                    
                }
                else{
                    if(!isExistePACK_VIGENTE_CampAcum(codProd_EQ)){
                        vCadena2.add(codProd_EQ+","+
                                     ctd+","+
                                     "1"+","+
                                     SubtTotal+","+
                                     SubtTotal+","+
                                     "0");    
                        listaProducto.add(vCadena2);    
                    }
                }
                    
            }
                           
        }
        // dubilluz 20171020
            
        log.info("PROGRAMA DE PUNTOS [QUOTE] : \nTIEMPO PARA OBTENER LISTA DESDE DB PARA ENVIO QUOTE "+(t2-t1)+"\nTotal de productos -->"+listaProducto.size()+"\nLista de Productos -->"+listaProducto);
        t1 = System.currentTimeMillis();
        // REALIZA EL QUOTE
        log.info("PROGRAMA DE PUNTOS [QUOTE] : ENVIO DE QUOTE A PROVEEDOR \nLista de Productos -->"+listaProducto);
        VariablesPuntos.frmPuntos.obtenerCotizacion(listaProducto, UtilityPuntos.getDNI_USU());
        t2 = System.currentTimeMillis();
        log.info("PROGRAMA DE PUNTOS [QUOTE] : TIEMPO DE RESPUESTA DE QUOTE CON PROVEEDOR --> "+(t2-t1)+"\nLista de Producto Respuesta --> "+tarjetaPuntos.getListaBonificados());
        tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
        //2017.03.31 guadar la trama de envio y respuesta en BD
        BeanOperacion operacion = VariablesPuntos.frmPuntos.getBitacora();
        if(operacion!=null){
                log.info("[TRAMA MONEDERO] [QUOTE] INPUT-->"+operacion.getVInput_in());
                log.info("[TRAMA MONEDERO] [QUOTE] OUTPUT-->"+operacion.getVOutput());
                UtilityPuntos.insertarLogOperacion(operacion);
            }
        //
        log.info("PROGRAMA DE PUNTOS [QUOTE] : Estado de tarjeta --> "+tarjetaPuntos.getEstadoTarjeta()
                 +" ESTADO --> "+tarjetaPuntos.getEstadoOperacion()+" \n MENSAJE: --> "+
                 UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPuntos.getEstadoOperacion(),tarjetaPuntos.getMensaje()));
        
        //SOLO PRUEBA FUERZA OFFLINE
        //tarjetaPuntos.setEstadoOperacion("901");
        //SOLO PRUEBA FUERZA OFFLINE
        
        if(WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
            resultado = false;
            log.info("PROGRAMA DE PUNTOS [QUOTE] : MODO OFFLINE");
        }else{
            if(WSClientConstans.EstadoTarjeta.BLOQUEADA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                || WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                || WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                || WSClientConstans.EstadoTarjeta.INVALIDA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
            ){
                resultado = false;
                log.info("PROGRAMA DE PUNTOS [QUOTE] : ESTADO DE TARJETA--> "+tarjetaPuntos.getEstadoTarjeta()+"\n"+
                         "mensaje --> "+ UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPuntos.getEstadoOperacion(),tarjetaPuntos.getMensaje()));
                if(WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                   || WSClientConstans.EstadoTarjeta.INVALIDA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                   || WSClientConstans.EstadoTarjeta.BLOQUEADA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                ){
                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                    log.info("PROGRAMA DE PUNTOS [QUOTE] : se cancelo proceso de venta por puntos");
                }
            }else{
                if(!WSClientConstans.EXITO.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                    resultado = false;
                    log.info("PROGRAMA DE PUNTOS [QUOTE] : ERROR EN ORBIS \n CODIGO DE ERROR--> "+tarjetaPuntos.getEstadoTarjeta()+"\n"+
                             "mensaje --> "+ UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPuntos.getEstadoOperacion(),tarjetaPuntos.getMensaje()));    
                    VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                }
            }
            
        }
        return resultado;
    }
    
    public static void operacion_acumula_bonifica(DlgResumenPedido jDlg,
                                                  Frame vFrame,
                                                  boolean pIngresoComprobanteManual){
        
        if(VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
            log.info("NO VA CALCULAR NADA DE X+1 para convenio");
            pAccion = "C";
            
                try{
                    if(UtilityPuntos.isActivoFuncionalidad()){
                        //LTAVARA VALIDA 2017.04.03
                        BeanTarjeta tarjetaPuntos =null;
                        if( VariablesPuntos.frmPuntos!= null){
                        tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
                        }
                        if(tarjetaPuntos!=null){
                            // CALCULO DE PUNTOS
                            Long t1 = System.currentTimeMillis();
                                // INICIO LTAVARA 2016.08.02 obtener los codProdEquivalente que aceptaron la cantidad del ItemQUOTE
                                ArrayList listacodProdEqItemQUOTE=new ArrayList<String>();
                                // FIN LTAVARA 2016.08.02
                                enviarPedidoPuntos(jDlg, 
                                                   vFrame,  
                                                   pIngresoComprobanteManual);
                                Long t2 = System.currentTimeMillis();
                                log.info("operacion_acumula_bonifica PROGRAMA DE PUNTOS [PUNTOS]: TIEMPO DE CALCULO DE operacion_acumula_bonifica: "+(t2-t1));
                        }
                        else
                            pAccion = "C";
                    }
                    else
                        pAccion = "C";
                    
                }catch(Exception ex)
                {
                    log.error("", ex);
                    if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                        VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                        log.error("PROGRAMA DE PUNTOS : SE ELIMINO LA TARJETA DEL PROGRAMA");
                    }
                    pAccion = "C";
                }
            
            }
        else{
                
        try{
            if(UtilityPuntos.isActivoFuncionalidad()){
                //LTAVARA VALIDA 2017.04.03
                BeanTarjeta tarjetaPuntos =null;
                if( VariablesPuntos.frmPuntos!= null){
                tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
                }
                if(tarjetaPuntos!=null){
                    // CALCULO DE PUNTOS
                    Long t1 = System.currentTimeMillis();
                        // INICIO LTAVARA 2016.08.02 obtener los codProdEquivalente que aceptaron la cantidad del ItemQUOTE
                        ArrayList listacodProdEqItemQUOTE=new ArrayList<String>();
                        List<String> listaProdItemQuote=tarjetaPuntos.getListaProdItemQuote();

                         for (int i = 0; i < UtilityCalculoPrecio.getSizeDetalleVenta(); i++) {
                              BeanDetalleVenta bean =  UtilityCalculoPrecio.getBeanPosDetalleVenta(i); 
                             if(!bean.isPIndCampAcumula()){
                                 String codProd=bean.getVCodProd();
                                 String cantidadLocal= bean.getVCtdVta();//cantidad del local
                                   if(!listaProdItemQuote.isEmpty()){
                                       for (String producto: listaProdItemQuote) {
                                          String[] listaProducto=producto.split(",");
                                           String prod=listaProducto[0].substring(0, 6);
                                           if(prod.equals(codProd) && listaProducto[1].equals(cantidadLocal)){
                                               listacodProdEqItemQUOTE.add(listaProducto[0]);
                                               };
                                       }
                                   }   
                             }
                        }
                        // FIN LTAVARA 2016.08.02
                        enviarPedidoPuntos(jDlg, 
                                           vFrame,  
                                           pIngresoComprobanteManual);
                        Long t2 = System.currentTimeMillis();
                        log.info("operacion_acumula_bonifica PROGRAMA DE PUNTOS [PUNTOS]: TIEMPO DE CALCULO DE operacion_acumula_bonifica: "+(t2-t1));
                }
                else
                    pAccion = "C";
            }
            else
                pAccion = "C";
            
        }catch(Exception ex)
        {
            log.error("", ex);
            if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                log.error("PROGRAMA DE PUNTOS : SE ELIMINO LA TARJETA DEL PROGRAMA");
            }
            pAccion = "C";
        }
        }

    }
    
    public static void operacion_calculo_puntos(String vNumPedVta){
        try{
            if(UtilityPuntos.isActivoFuncionalidad()){
                //LTAVARA VALIDA 2017.04.03
                BeanTarjeta tarjetaPuntos =null;
                if( VariablesPuntos.frmPuntos!= null){
                tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
                    /*if(tarjetaPuntos==null){
                        BeanAfiliado af = VariablesPuntos.frmPuntos.obtenerDatosAfiliadoSinTarjeta(VariablesFidelizacion.vDniCliente,
                                                                                                         "001");   
                        VariablesPuntos.frmPuntos.validarTarjetaAsociada(af.getTarjetas().get(0).toString(), "001");
                        tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
                    }*/
                }
                
                if(tarjetaPuntos!=null){
                    // CALCULO DE PUNTOS
                    Long t1 = System.currentTimeMillis();
                    UtilityPuntos.calculaPuntos(FarmaVariables.vCodGrupoCia, 
                                                FarmaVariables.vCodLocal, 
                                                vNumPedVta, 
                                                false);
                    Long t2 = System.currentTimeMillis();
                    log.info("operacion_calculo_puntos >> " +
                             "PROGRAMA DE PUNTOS [PUNTOS]: TIEMPO DE CALCULO DE PUNTOS: "+(t2-t1));
                    // INICIO DE METODO QUOTE
                    log.info("********************************************************************************");
                    log.info("****** PROGRAMA PUNTOS [QUOTE]: INICIO DE LOGICA PARA ENVIO DE METODO QUOTE");
                    log.info("********************************************************************************");
                    FarmaUtility.aceptarTransaccion();
                }
            }
        }catch(Exception ex){
            log.error("", ex);
            if(VariablesPuntos.frmPuntos.getBeanTarjeta() != null){
                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                log.error("PROGRAMA DE PUNTOS : SE ELIMINO LA TARJETA DEL PROGRAMA");
            }
        }
    }
    
    public static void pSaveRegaloRechazoPedido(String pNumPedVta) throws Exception{
        log.info("pSaveRegaloRechazoPedido :  cuantos bonifica "+UtilityProgramaAcumula.vListaAceptaRegaloPedido.size());
        if(UtilityProgramaAcumula.vListaAceptaRegaloPedido.size()>0){
            log.info(""+UtilityProgramaAcumula.vListaAceptaRegaloPedido);
            for(int i=0;i<UtilityProgramaAcumula.vListaAceptaRegaloPedido.size();i++){
                DBPuntos.save_prod_regalo_acumula(pNumPedVta,    
                                                  FarmaUtility.getValueFieldArrayList(UtilityProgramaAcumula.vListaAceptaRegaloPedido,
                                                                                      i,
                                                                                      1),
                                                  /*FarmaUtility.getValueFieldArrayList(UtilityProgramaAcumula.vListaAceptaRegaloPedido,
                                                                                      i,
                                                                                      5),*/
                                                  FarmaUtility.getValueFieldArrayList(UtilityProgramaAcumula.vListaAceptaRegaloPedido,
                                                                                     i,
                                                                                     9),                                                  
                                                    FarmaUtility.getValueFieldArrayList(UtilityProgramaAcumula.vListaAceptaRegaloPedido,
                                                                                        i,
                                                                                        6),
                                    
                                                    FarmaUtility.getValueFieldArrayList(UtilityProgramaAcumula.vListaAceptaRegaloPedido,
                                                                                        i,
                                                                                        7) ,
                                                    FarmaUtility.getValueFieldArrayList(UtilityProgramaAcumula.vListaAceptaRegaloPedido,
                                                                                        i,
                                                                                        8)                                                   
                                                 );
            }
            
        }
        
        log.info("pSaveRegaloRechazoPedido :  cuantos rechazo "+UtilityProgramaAcumula.vListaRechazoRegaloPedido.size());
        if(UtilityProgramaAcumula.vListaRechazoRegaloPedido.size()>0){
            log.info(""+UtilityProgramaAcumula.vListaRechazoRegaloPedido);
        }
    }
    
    public static void save_hist_acumulado_x_dni(String pDni,
                                          String pNumPedVta)  throws Exception{
        BeanAfiliado afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliadoSinTarjeta(pDni, "001");
        BeanTarjeta tar = VariablesPuntos.frmPuntos.getBeanTarjeta();
        System.out.println(afiliado.getTarjetas());
        BeanTarjeta trjBean = VariablesPuntos.frmPuntos.validarTarjetaAsociada(afiliado.getTarjetas().get(0).toString(), "001");
        BeanTarjeta tarjetaCliente =   VariablesPuntos.frmPuntos.getGanarBonificaciones(trjBean.getNumeroTarjeta(),"001");
        if(tarjetaCliente!=null && !tarjetaCliente.getListaPendienteAcumular().isEmpty()){
               List lstProducto = tarjetaCliente.getListaPendienteAcumular();
               for(int i=0;i<lstProducto.size();i++){
                   String[] prod = lstProducto.get(i).toString().split(",");
                   String codProdEQ=prod[0];
                   String cantPendAcum=prod[1];
                   log.info(codProdEQ.substring(8)+" "+codProdEQ + " "+" faltan "+cantPendAcum);
                   DBPuntos.graba_hist_acumula_x_dni(pNumPedVta,
                                                     codProdEQ.substring(8),
                                                     codProdEQ,
                                                     cantPendAcum);
               }
           }
        else{
            log.info("No afiliado a nada >> save_hist_acumulado_x_dni");
        }
        
    }
    
    public static boolean  validaDocumentoObligatorio(){
        boolean vAccion = false;
        try {
            vAccion =
                    UtilityPuntos.validaDocumentoRedimirBonificar(new Frame(), new JDialog(), ConstantsPuntos.BONIFICACION_PRODUCTOS,
                                                                  false);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
        if(!vAccion){
            if(JConfirmDialog.rptaConfirmDialogDefaultNo(new JDialog(),
                                                         "Si no tiene los documentos necesarios para verificación.\n" +
                                                         "No podrá llevar los productos bonificados.\n"+
                                                         "¿Esta seguro de continuar con el cobro?")) {
                return false;
            }
            else{
                return validaDocumentoObligatorio();
            }
        }
        else
            return vAccion;
    }
    
    public static boolean rechazaRegaloEnValidaTarjetaDNI(){
        //INICIO RPASCACIO 07.06.2017
          
        String pAccion = "N";
        String auxLista = "";
        BeanTarjeta tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
        ArrayList lstBonificados = new ArrayList();
        
        List lstProducto = tarjetaPuntos.getListaBonificados();
        for(int i=0;i<lstProducto.size();i++){
            String aux = (String)lstProducto.get(i);
            if(auxLista.length()>0){
                auxLista = auxLista + "@";
            }
            auxLista = auxLista + aux;
        }

         try {
             DBPuntos.getListaBonificados(lstBonificados,FarmaVariables.vCodGrupoCia,FarmaVariables.vCodLocal,auxLista);
         } catch (Exception f) {
             log.info("ERROR");
             
         }
         
         {
             
            UtilityProgramaAcumula.vListaAceptaRegaloPedido.clear();
            
            ArrayList lstProdBonifRechazados = new ArrayList();
            // cargar lista de rechazos que se deben hacer
            
            
            if(lstBonificados.size()>0){
                //Agregar lista de rechazados
                for(int i=0;i<lstBonificados.size();i++){
                    int cantidadOfrecida = (int)Integer.parseInt((String)((ArrayList)lstBonificados.get(i)).get(5));
                    lstProdBonifRechazados.add(((ArrayList)lstBonificados.get(i)).get(6)+","+cantidadOfrecida);
                }
            }
            

            if(lstProdBonifRechazados.size()>0){
                VariablesPuntos.frmPuntos.rechazarBonificado(lstProdBonifRechazados, UtilityPuntos.getDNI_USU());
                tarjetaPuntos = VariablesPuntos.frmPuntos.getBeanTarjeta();
                if(tarjetaPuntos != null){
                    log.info("PROGRAMA DE PUNTOS [QUOTE] : RECHAZO DE BONIFICADOS \n"+
                             "Estado Operacion: "+tarjetaPuntos.getEstadoOperacion()+
                             "\n Estado Tarjeta: "+ tarjetaPuntos.getEstadoTarjeta()+
                             "\n Mensaje: "+tarjetaPuntos.getMensaje()+
                             "\n RESPUESTA DE RECHAZO -->"+tarjetaPuntos.getListaBonificados());
                    
                    if(WSClientConstans.NO_CONEXION_ORBIS.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                        FarmaUtility.showMessage(new JDialog(),"Programa Monedero: Por el momento no se podra bonificar productos.\n" +
                                                            "Solo se podra acumular puntos.", null);
                        log.info("RECHAZO FALLO:CONTINUAR OFFLINE ");
                        pAccion = "C";
                        return true;
                    }else{
                        if(WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta()) 
                        ){
                            FarmaUtility.showMessage(new JDialog(), "Programa Monedero:\nTarjeta Bloqueada para redencion.\n" +
                                                                "Se cancela la bonificación de Productos y solo se podra acumular puntos.", null);
                            pAccion = "C";
                            return false;
                        }else{
                            if(WSClientConstans.EstadoTarjeta.INACTIVA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta()) ||
                                WSClientConstans.EstadoTarjeta.INVALIDA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta()) ||
                                WSClientConstans.EstadoTarjeta.BLOQUEADA.equalsIgnoreCase(tarjetaPuntos.getEstadoTarjeta())
                            ){
                                String texto = UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaPuntos.getEstadoOperacion(), tarjetaPuntos.getMensaje());
                                VariablesPuntos.frmPuntos.eliminarBeanTarjeta();
                                FarmaUtility.showMessage(new JDialog(), "Programa Monedero:\n" + texto + 
                                                                   "Se cancelara la bonificación de Productos y " +
                                                                   "los puntos acumulados en esta venta." , null);
                                pAccion = "C";
                                return true;
                            }else{
                                if(!WSClientConstans.EXITO.equalsIgnoreCase(tarjetaPuntos.getEstadoOperacion())){
                                    log.info("PROGRAMA DE PUNTOS [QUOTE] : ERROR INESPERADO ");
                                    FarmaUtility.showMessage(new JDialog(), "Programa Monedero:\n"+
                                                                       "Error inesperado: Se cancela la bonificación de productos. Continua con la venta", null);
                                    log.info("RECHAZO FALLO:CONTINUAR OFFLINE ");
                                }else{
                                    /// AQUI ES SOLO EXITO
                                    tarjetaPuntos.setListaBonifRechazo(lstProdBonifRechazados);
                                    List lstProductoQ = tarjetaPuntos.getListaBonificados();
                                    String auxLista2 = "";
                                    for(int i=0;i<lstProductoQ.size();i++){
                                        String aux = (String)lstProductoQ.get(i);
                                        if(auxLista2.length()>0){
                                            auxLista2 = auxLista2 + "@";
                                        }
                                        auxLista2 = auxLista2 + aux;
                                    }
                                    pAccion = "C";
                                    return true;
                                }
                            }
                        }
                    }
                }
            }else{
               log.info("NO HAY PRODUCTOS BONIFICADOS PARA RECHAZAR");
                return true;
            }  
         //FIN RPASCACIO 07.06.2017                   
        }
         
        return false;
    }
        
        
    public static String getCtdRegaloConversion(String pCodProg,
                                         String pCodProd,
                                         String pCantidad,
                                         String pValFracVta){
        String pCtdNueva = "";
        try {
            pCtdNueva = DBPuntos.getConversionRegaloOrbis(pCodProg, pCodProd, pCantidad, pValFracVta);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            FarmaUtility.showMessage(new JDialog(),"Error en la conversión\n" +
                                     ""+e.getMessage() , null);
        }
        return pCtdNueva.trim();
    }
    
    // dubilluz 20171020
    private static String getObtenerCodEQ_PACK(String vCodProd) {
        String pCodEquivalente = "";
        try {
            pCodEquivalente = DBPuntos.getCodEquivalente_Prod_PACK(vCodProd);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            FarmaUtility.showMessage(new JDialog(),"Error en obtener codigo equivalente para producto pack\n" +
                                     ""+e.getMessage() , null);
        }
        return pCodEquivalente.trim();
    }
    // 
    
    private static boolean isExistePACK_VIGENTE_CampAcum(String vCodEquivalente) {
        String pCodEquivalente = "";
        try {
            pCodEquivalente = DBPuntos.isExistePACK_VIGENTE_CodEquivalente(vCodEquivalente);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            FarmaUtility.showMessage(new JDialog(),"Error en obtener codigo equivalente para producto pack\n" +
                                     ""+e.getMessage() , null);
        }
        
        if(pCodEquivalente.trim().equalsIgnoreCase("S"))
            return true;
        else
            return false;
            
    }
    
    public static boolean vIsClienteInscrito(BeanTarjeta tarjetaPuntos,
                                             String pCodProdEQ){
        // EL CLIENTE ES AFILIADO AL PROGRAMA?
        // DUBILLUZ 2017.10.20
        boolean vPermiteAgregar = false;
        ArrayList vListaInscritos = (ArrayList)tarjetaPuntos.getListaInscritos();
        for(int a=0;a<vListaInscritos.size();a++){
            if(pCodProdEQ.trim().equalsIgnoreCase(vListaInscritos.get(a).toString().trim())){
                log.info("Si esta inscrito al programa "+pCodProdEQ);
                vPermiteAgregar = true;
                break;
            }
        }
        return vPermiteAgregar;
    }
    
    // dubilluz 20171020    
}
