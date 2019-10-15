package mifarma.ptoventa.cotizarPrecios.DAO;

import mifarma.ptoventa.ce.reference.VariablesCajaElectronica;
import mifarma.ptoventa.reference.DAOTransaccion;

public interface DAORACCotizacionPrecio  extends DAOTransaccion{
    public boolean validarAutorizacionAnular(String pCodGrupoCia, 
                                            String pCodLocal,
                                            String pNumNotaEs,
                                            String pCodSolicitud) throws Exception;
    
    public String verificaAutorizacionDeCambio(String pCodCia, 
                                                 String pCodLocal,
                                                 String pCodSolicitud,
                                                 String pCodProducto) throws Exception;

    public String enviarSolicitudDeCambioProducto(String pCodCia, 
                                                  String pCodLocal,
                                                  String pCodSolicitud,
                                                  String pCodProducto,
                                                  String pNuevoProd,
                                                  //LTAVARA 2017.09.15
                                                  String pDatosProductoSolicitud) throws Exception;
    
    public boolean insertaCotizacion(String pCodGrupoCia, 
                                     String pCodLocal,
                                     String pCodSolicitud,
                                     String pNumNota) throws Exception;

    public boolean anularCotizacion(
                                                String pCodGrupoCia, 
                                                String pCodLocal,
                                                String pNumNotaEs,
                                                String pCodSolicitud)throws Exception;

    public String pruebaSolicitudDeCambioProducto(String solicitud, String producto, String recibe)throws Exception;
    
    
                                                     
}
