package mifarma.ptoventa.cotizarPrecios.DAO;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.fraccion.DAO.BDFraccion;
import mifarma.ptoventa.reference.ConstantsPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BDCotizacionPrecio {
    
    private static final Logger log = LoggerFactory.getLogger(BDFraccion.class);

    private static ArrayList parametros = new ArrayList();
    
    public BDCotizacionPrecio() {
        super();
    }
    
    public static int existenSolicitudesXEstado(String estado,int conDocumento)
    throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(estado);
        parametros.add(conDocumento);
        log.info("PKG_COTIZACION_PRECIO.EXISTE_X_ESTADO(?,?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureInt("PKG_COTIZACION_PRECIO.EXISTE_X_ESTADO(?,?,?,?)", 
                                                 parametros); 
    }

    public static void cargaTabla_SolicitudXDefecto(FarmaTableModel pTablaSolic, String estado,int conDocumento)
                                            throws SQLException{
        pTablaSolic.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(estado);
        parametros.add(conDocumento);
        log.info("PKG_COTIZACION_PRECIO.RECUP_SOLIC_COTIZA_PENDIENTES(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTablaSolic,
                                                 "PKG_COTIZACION_PRECIO.RECUP_SOLIC_COTIZA_PENDIENTES(?,?,?,?)",
                                                 parametros, false);
    }
    
    public static int recuperaCantidad_Solicitudes( String fechaInicio, String fechaFin,int conDocumento)
    throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaInicio);
        parametros.add(fechaFin);
        parametros.add(conDocumento);
        log.info("PKG_COTIZACION_PRECIO.RECUPERA_CANT_SOLICITUDES(?,?,?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureInt("PKG_COTIZACION_PRECIO.RECUPERA_CANT_SOLICITUDES(?,?,?,?,?)", 
                                                 parametros); 
    }

    public static void cargaTabla_Solicitud(FarmaTableModel pTablaSolic, String fechaIni, String fechaFin,int conDocumento)
                                            throws SQLException{
        pTablaSolic.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaIni);
        parametros.add(fechaFin);
        parametros.add(conDocumento);
        log.info("PKG_COTIZACION_PRECIO.RECUPERA_SOLICITUD_COTIZACION(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTablaSolic,
                                                 "PKG_COTIZACION_PRECIO.RECUPERA_SOLICITUD_COTIZACION(?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaTabla_Productos(FarmaTableModel pTablaProd, String codSolicitud) 
                                            throws SQLException {
        pTablaProd.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolicitud);
        log.info("PKG_COTIZACION_PRECIO.RECUPERA_PRODUCTOS_SOLICITUD(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTablaProd,
                                                 "PKG_COTIZACION_PRECIO.RECUPERA_PRODUCTOS_SOLICITUD(?,?,?)",
                                                 parametros, false);
    }

    public static void getTabla_Productos(FarmaTableModel pTablaProd, String codSolicitud) 
                                            throws SQLException {
        pTablaProd.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolicitud);
        log.info("PKG_COTIZACION_PRECIO.GET_LISTA_PROD_DETALLE(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTablaProd,
                                                 "PKG_COTIZACION_PRECIO.GET_LISTA_PROD_DETALLE(?,?,?)",
                                                 parametros, false);
    }

    public static void cargaListaProductosSustitutos(FarmaTableModel pTableModel,
                                                     String pCodProd) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd);
        log.debug("PKG_COTIZACION_PRECIO.VTA_LISTA_PROD_SUSTITUTOS(?,?,?):" + parametros);
        
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,"PKG_COTIZACION_PRECIO.VTA_LISTA_PROD_SUSTITUTOS(?,?,?)",
                                                 parametros, false);
    }

    public static String cambiarProducto_Cotizacion(String codNuevo_Prod) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VarCotizacionPrecio.vCod_Solic);
        parametros.add(VarCotizacionPrecio.vCod_Prod);
        parametros.add(codNuevo_Prod);//Local que modifica
        log.info("PKG_COTIZACION_PRECIO.CAMBIAR_PROD_COTIZACION(?,?,?,?,?)"+parametros);
        String flag=FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.CAMBIAR_PROD_COTIZACION(?,?,?,?,?)",
                                                                parametros);
        return flag;
    }

    public static String verificaAutorizacion_deCambio(String codSolicitud, String codProducto) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolicitud);
        parametros.add(codProducto);
        log.info("PKG_COTIZACION_PRECIO.VERIFICA_AUTORIZACION_CAMBIO(?,?,?,?)"+parametros);
        String flag=FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.VERIFICA_AUTORIZACION_CAMBIO(?,?,?,?)",
                                                                parametros);
        return flag;
    }

    public static String agregarCabeceraGuiaIngreso(String fechaGuia,   //VariablesInventario.vFecGuia
                                                    String tipDoc,      //VariablesInventario.vTipoDoc 
                                                    String numDoc,      //VariablesInventario.vNumDoc
                                                    String tipOrigen,   //VariablesInventario.vTipoOrigen
                                                    String codOrigen,   //VariablesInventario.vCodOrigen
                                                    String cantItems, 
                                                    String valTotal,
                                                    String nomTienda,   //VariablesInventario.vNombreTienda
                                                    String ciudadTienda,//ariablesInventario.vCiudadTienda
                                                    String rucTienda,   //VariablesInventario.vRucTienda
                                                    String codTipProceso,
                                                    String codSolicitud,
                                                    String nom_sustento,
                                                    String tipoCotizacionCompetencia
                                                    )//VariablesInventario.vCodTipProceso
    throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaGuia);
        parametros.add(tipDoc);
        parametros.add(numDoc);
        parametros.add(tipOrigen);
        parametros.add(codOrigen);
        parametros.add(new Integer(cantItems));
        parametros.add(new Double(valTotal));
        parametros.add(nomTienda);
        parametros.add(ciudadTienda);
        parametros.add(rucTienda);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(codTipProceso);
        parametros.add(codSolicitud);
        parametros.add(nom_sustento);
        parametros.add(tipoCotizacionCompetencia);
        
        log.debug("invocando a PTOVENTA_INV.INV_AGREGA_CAB_GUIA_INGRESO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?):" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_INV.INV_AGREGA_CAB_GUIA_INGRESO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                                           parametros);
    }

    public static void agregarDetalleGuiaIngreso(String numGuia, String tipOrigen, String codProd, String valUnit,
                                                 String valTotal, String cantMov, String fecGuia, String fecVcto,
                                                 String numLote, String pValFrac, String pSolicitudCotiza, String  pTipoCotizacion) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numGuia);
        parametros.add(tipOrigen);
        parametros.add(codProd);
        parametros.add(new Double(valUnit));
        parametros.add(new Double(valTotal));
        parametros.add(new Integer(cantMov));
        parametros.add(fecGuia);
        parametros.add(fecVcto);
        parametros.add(numLote);
        parametros.add(ConstantsPtoVenta.MOT_KARDEX_COTIZACION_COMPETENCIA);
        parametros.add(ConstantsPtoVenta.TIP_DOC_KARDEX_GUIA_ES);
        parametros.add(new Integer(pValFrac));
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add("");
        parametros.add("");
        parametros.add(pSolicitudCotiza);
        parametros.add(pTipoCotizacion);
        
        log.debug("invocando a PTOVENTA_INV.INV_AGREGA_DET_GUIA_INGRESO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_INV.INV_AGREGA_DET_GUIA_INGRESO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                                 parametros, false);
    }
    
    public static void actualizaCantIngreSolicitud(String codSolicitud, String codProducto, String cantIngresada,
                                                   /*** INICIO CCASTILLO 31/08/2017 ***/
                                                   String nom_sustento,
                                                   String cod_motivo  
                                                    /*** FIN CCASTILLO 31/08/2017 ***/
                                                   ) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolicitud);
        parametros.add(codProducto);
        parametros.add(cantIngresada);
        parametros.add(nom_sustento);
        parametros.add(cod_motivo);
        log.debug("invocando a PKG_COTIZACION_PRECIO.ACTUALIZA_CANT_INGRE_SOLICITUD(?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PKG_COTIZACION_PRECIO.ACTUALIZA_CANT_INGRE_SOLICITUD(?,?,?,?,?,?,?)",
                                                 parametros, false);
    }
    
    public static String getNombreCarpetaCotizacion() throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PKG_COTIZACION_PRECIO.GET_NOMB_CARPETA_COTIZACION(?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.GET_NOMB_CARPETA_COTIZACION(?,?)", 
                                                 parametros); 
    }
    
    public static String validaPrecioCompetencia(String pCodProd, double pPrecUnit) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pCodProd);
        parametros.add(pPrecUnit);
        log.debug("PKG_COTIZACION_PRECIO.F_VALIDA_PRECIO_COMPETENCIA(?,?,?,?):" + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.F_VALIDA_PRECIO_COMPETENCIA(?,?,?,?)",
                                                           parametros);
    }
    
    public static void cargaTabla_ProductosCotizacion(FarmaTableModel pTablaProd, String codSolicitud) 
                                            throws SQLException {
        pTablaProd.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolicitud);
        log.info("PKG_COTIZACION_PRECIO.RECUPERA_PRODUCTOS_COTIZACION(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTablaProd,
                                                 "PKG_COTIZACION_PRECIO.RECUPERA_PRODUCTOS_COTIZACION(?,?,?)",
                                                 parametros, false);
    }
    
    public static String getIndicadorLoteFechaCotizacion() throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PKG_COTIZACION_PRECIO.GET_IND_LOTE_FECHA_COTIZA(?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.GET_IND_LOTE_FECHA_COTIZA(?,?)", 
                                                 parametros); 
    }
    
    public static String actualizaEstadoSolicitud(String codSolicitud) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolicitud);
        log.debug("invocando a PKG_COTIZACION_PRECIO.ACTUALIZA_ESTADO_SOLICITUD(?,?,?):" + parametros);
       String resultado= FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.ACTUALIZA_ESTADO_SOLICITUD(?,?,?)",
                                                 parametros);
       return resultado;
    }
    
    
    public static boolean getIndActivaCotizacionPrecio() throws SQLException{
        
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PKG_COTIZACION_PRECIO.GET_IND_ACTIVA_COTIZA_PRECIO(?,?)"+parametros);
       
       
        String resultado = FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.GET_IND_ACTIVA_COTIZA_PRECIO(?,?)", 
                                                 parametros); 
        if(resultado.equals("S")){
            return true;
        }else{
            return false;
        }
    }
    
    public static String getLimiteCargaDocumento() throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PKG_COTIZACION_PRECIO.GET_LIMITE_CARGA_DOCUMENTO(?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PKG_COTIZACION_PRECIO.GET_LIMITE_CARGA_DOCUMENTO(?,?)", 
                                                 parametros); 
    }
    
    public static void cargaListaNotaDetalle(FarmaTableModel pTableModel,
                                                     String pNumNota) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pNumNota);
        log.debug("PKG_COTIZACION_PRECIO.GET_NOTA_DET(?,?,?):" + parametros);
        
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel,"PKG_COTIZACION_PRECIO.GET_NOTA_DET(?,?,?)",
                                                 parametros, false);
    }

    public static void actualizaNombreSustento(String numNotaEs, String nombreSustento) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numNotaEs);
        parametros.add(nombreSustento);
        log.debug("invocando a PKG_COTIZACION_PRECIO.ACTUALIZA_NOMBRE_SUSTENTO_NOTA(?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PKG_COTIZACION_PRECIO.ACTUALIZA_NOMBRE_SUSTENTO_NOTA(?,?,?,?)",
                                                 parametros, false);
    }
    
    public static void insertarSolicitudCotizaNota(String codSolicitud, String numNotaCab ) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(codSolicitud);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(numNotaCab);
        parametros.add(FarmaVariables.vIdUsu);
        log.debug("invocando a PKG_COTIZACION_PRECIO.P_INSERT_SOLICITUD_COTIZA_NOTA(?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PKG_COTIZACION_PRECIO.P_INSERT_SOLICITUD_COTIZA_NOTA(?,?,?,?,?)",
                                                 parametros, false);
    }
}
