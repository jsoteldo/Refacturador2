package mifarma.ptoventa.cotizarPrecios.DAO;


import com.mifarma.query.builder.ClienteIntegrador;
import com.mifarma.query.builder.QueryBuilder;
import com.mifarma.query.builder.UpdateBuilder;
import com.mifarma.query.request.QueryParams;
import com.mifarma.query.response.QueryStatus;

import java.util.List;
import java.util.Map;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import oracle.sql.CHAR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GTRACCotizacionPrecio implements DAORACCotizacionPrecio {
    private static final Logger log = LoggerFactory.getLogger(GTRACCotizacionPrecio.class);
    
    private UpdateBuilder updateBuilder = null;
    private ClienteIntegrador clienteIntegrador = null;
    private UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    @Override
    public boolean validarAutorizacionAnular(String pCodGrupoCia, 
                                            String pCodLocal,
                                            String pNumNotaEs,
                                            String pCodSolicitud) {
        boolean respuesta=false;
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpVarchar("{ ? = call PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.F_GET_AUTORIZA_ANULAR_COTIZA(?,?,?,?)}", 
                          FarmaVariables.vCodGrupoCia, pCodLocal, pNumNotaEs,pCodSolicitud)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> result = status.getResult(); 
        Map<String, Object> first = result.get(0);
        
        String resultado = String.valueOf(first.get("1"));
        
        if ("S".equalsIgnoreCase(resultado)){
            respuesta = true;
        }
        return respuesta;
    }
    
    @Override
    public void commit() {
    }

    @Override
    public void rollback() {
    }

    @Override
    public void openConnection() {
        updateBuilder = new UpdateBuilder()
                                  .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
                                  .requestId("centromedico-merge-rac-ped");
        
        clienteIntegrador = new ClienteIntegrador(VariablesPtoVenta.conexionGTX.getIPBD()+":"+VariablesPtoVenta.conexionGTX.getPORT());
        clienteIntegrador.bypassGateway(); 
    }
    
    @Override
    public String verificaAutorizacionDeCambio(String pCodGrupoCia, 
                                             String pCodLocal,
                                             String pCodSolicitud,
                                             String pCodProducto) {
        boolean respuesta=false;
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpVarchar("{ ? = call PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.F_AUTORIZA_CAMBIO_PROD_COTIZA(?,?,?,?)}", pCodGrupoCia, pCodLocal, pCodSolicitud,pCodProducto)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> result = status.getResult(); 
        Map<String, Object> first = result.get(0);
        
        String resultado = String.valueOf(first.get("1"));

        return resultado;
    }
    
    @Override
    public String enviarSolicitudDeCambioProducto(String pCodGrupoCia, 
                                                  String pCodLocal,
                                                  String pCodSolicitud,
                                                  String pCodProducto,
                                                  String pNuevoProd,
                                                  String pDatosProductoSolicitud) {
        log.info("PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.F_SOLICITA_CAMBIO_PROD_COTIZA(?,?,?,?,?,?,?): ["
                 +pCodGrupoCia+","+pCodLocal+","+pCodSolicitud+","+pCodProducto+","+pNuevoProd
                 +","+FarmaVariables.vIdUsu+","+pDatosProductoSolicitud +"]");
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpVarchar("{ ? = call PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.F_SOLICITA_CAMBIO_PROD_COTIZA(?,?,?,?,?,?,?)}",
                          pCodGrupoCia, pCodLocal, pCodSolicitud,pCodProducto,pNuevoProd,FarmaVariables.vIdUsu,pDatosProductoSolicitud)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> result = status.getResult(); 
        Map<String, Object> first = result.get(0);
        
        String resultado = String.valueOf(first.get("1"));

        return resultado;
    }  
    
    @Override
    public boolean insertaCotizacion(String pCodGrupoCia, 
                                     String pCodLocal,
                                     String pCodSolicitud,
                                     String pNumNota) {
        boolean respuesta = false;
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpVarchar("{ ? = call PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.P_INSERTA_COTIZACION(?,?,?,?)}", 
                          pCodGrupoCia, pCodLocal, pCodSolicitud, pNumNota)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> result = status.getResult(); 
        Map<String, Object> first = result.get(0);
        
        String resultado = String.valueOf(first.get("1"));
        
        if ("S".equalsIgnoreCase(resultado)){
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public boolean anularCotizacion(    String pCodGrupoCia, 
                                        String pCodLocal,
                                        String pNumNotaEs,
                                        String pCodSolicitud) {
        boolean respuesta=false;
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpVarchar("{ ? = call PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.F_ANULAR_COTIZACION(?,?,?,?)}", 
                          FarmaVariables.vCodGrupoCia, pCodLocal, pCodSolicitud,pNumNotaEs)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> result = status.getResult(); 
        Map<String, Object> first = result.get(0);
        
        String resultado = String.valueOf(first.get("1"));
        
        if ("S".equalsIgnoreCase(resultado)){
            respuesta = true;
        }
        return respuesta;
    }
    
    public String pruebaSolicitudDeCambioProducto(String solicitud, String producto, String recibe)throws Exception{
        log.info("PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.NOTIFICA_APROBACION(?,?,?): ["
                 +solicitud+","+producto+","+recibe+"]");
        int solic=1013;
        String codLocal="026";
        String email=recibe="desarrollo1@mifarma.com.pe";
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpVarchar("{ ? = call PTOVENTA.PKG_MATRIZ_COTIZACION_PRECIO.NOTIFICA_APROBACION(?,?,?)}",
                          solic, codLocal,email)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> result = status.getResult(); 
        Map<String, Object> first = result.get(0);
        
        String resultado = String.valueOf(first.get("1"));

        return resultado;
    }
  
    
}
