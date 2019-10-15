package mifarma.ptoventa.cotizarPrecios.referencia;

import mifarma.ptoventa.ce.reference.VariablesCajaElectronica;
import mifarma.ptoventa.cotizarPrecios.DAO.DAORACCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.DAO.FactoryCotizacionPrecio;
import mifarma.ptoventa.reference.TipoImplementacionDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FacadeCotizacionPrecio {
    private static final Logger log = LoggerFactory.getLogger(FacadeCotizacionPrecio.class);
    
    public FacadeCotizacionPrecio() {
        super();
    }
    
    /**
     * @author LTAVARA
     * @since 2017.07.21
     * @return
     */
    public boolean validarAutorizacionAnular(
                                            String pCodCia,
                                            String pCodLocal,
                                            String pNumNotaEs,
                                            String pCodSolicitud){
        boolean bRetorno = false;
        
        DAORACCotizacionPrecio daoRACCotizacionPrecio = null;
      /*  TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;*/
        try {
            //2.0 Abre conexion RAC
            daoRACCotizacionPrecio = FactoryCotizacionPrecio.getDAORACCotizacionPrecio(TipoImplementacionDAO.GESTORTX);
            daoRACCotizacionPrecio.openConnection(); 
            bRetorno= daoRACCotizacionPrecio.validarAutorizacionAnular(pCodCia, pCodLocal, pNumNotaEs, pCodSolicitud);
            //2.6 Cierra conexion RAC
            daoRACCotizacionPrecio.commit();
            
        } catch (Exception e) {
            daoRACCotizacionPrecio.rollback();
            log.error("", e);
            return bRetorno;
        }

        return bRetorno;
    }
    
    public String verificaAutorizacionDeCambio(String pCodCia,
                                                String pCodLocal,
                                                String pCodSolicitud,
                                                String pCodProducto){
        String bRetorno = "";
        
        DAORACCotizacionPrecio daoRACCotizacionPrecio = null;
      /*  TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;*/
        try {
            //2.0 Abre conexion RAC
            daoRACCotizacionPrecio = FactoryCotizacionPrecio.getDAORACCotizacionPrecio(TipoImplementacionDAO.GESTORTX);
            daoRACCotizacionPrecio.openConnection(); 
            bRetorno= daoRACCotizacionPrecio.verificaAutorizacionDeCambio(pCodCia, pCodLocal, pCodSolicitud, pCodProducto);
            //2.6 Cierra conexion RAC
            daoRACCotizacionPrecio.commit();
            
        } catch (Exception e) {
            daoRACCotizacionPrecio.rollback();
            log.error("", e);
            return bRetorno;
        }

        return bRetorno;
    }

    public String enviarSolicitudDeCambioProducto(String pCodCia,
                                                  String pCodLocal,
                                                  String pCodSolicitud,
                                                  String pCodProducto,
                                                  String codNuevoProd,
                                                  String pDatosProductoSolicitud
                                            ){
        
        String bRetorno = "";
        
        DAORACCotizacionPrecio daoRACCotizacionPrecio = null;
        try {
            daoRACCotizacionPrecio = FactoryCotizacionPrecio.getDAORACCotizacionPrecio(TipoImplementacionDAO.GESTORTX);
            daoRACCotizacionPrecio.openConnection(); 
            bRetorno= daoRACCotizacionPrecio.enviarSolicitudDeCambioProducto(pCodCia, pCodLocal, pCodSolicitud, pCodProducto,codNuevoProd,pDatosProductoSolicitud);
            daoRACCotizacionPrecio.commit();            
        } catch (Exception e) {
            daoRACCotizacionPrecio.rollback();
            log.error("", e);
            return null;
        }

        return bRetorno;
    }
    
    public void insertaCotizacion(String pCodGrupoCia, 
                                  String pCodLocal,
                                  String pCodSolicitud,
                                  String pNumNota){
        
        boolean bRetorno = false;
        DAORACCotizacionPrecio daoRACCotizacionPrecio = null;
        
        try {
            daoRACCotizacionPrecio = FactoryCotizacionPrecio.getDAORACCotizacionPrecio(TipoImplementacionDAO.GESTORTX);
            daoRACCotizacionPrecio.openConnection(); 
            bRetorno = daoRACCotizacionPrecio.insertaCotizacion(pCodGrupoCia, pCodLocal, pCodSolicitud, pNumNota);
            daoRACCotizacionPrecio.commit();            
        } catch (Exception e) {
            daoRACCotizacionPrecio.rollback();
            log.error("", e);
        }
    }
    
    /**
     * @author LTAVARA
     * @since 2017.07.21
     * @return
     */
    public boolean anularCotizacion(
                                            String pCodCia,
                                            String pCodLocal,
                                            String pNumNotaEs,
                                            String pCodSolicitud){
        boolean bRetorno = false;
        
        DAORACCotizacionPrecio daoRACCotizacionPrecio = null;
      /*  TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;*/
        try {
            //2.0 Abre conexion RAC
            daoRACCotizacionPrecio = FactoryCotizacionPrecio.getDAORACCotizacionPrecio(TipoImplementacionDAO.GESTORTX);
            daoRACCotizacionPrecio.openConnection(); 
            bRetorno= daoRACCotizacionPrecio.anularCotizacion(pCodCia, pCodLocal, pNumNotaEs, pCodSolicitud);
            //2.6 Cierra conexion RAC
            daoRACCotizacionPrecio.commit();
            
        } catch (Exception e) {
            daoRACCotizacionPrecio.rollback();
            log.error("", e);
            return bRetorno;
        }

        return bRetorno;
    }

    public String pruebaSolicitudDeCambioProducto(String codSolic, String local,String recibe) {
        String bRetorno = "";
        
        DAORACCotizacionPrecio daoRACCotizacionPrecio = null;
        try {
            daoRACCotizacionPrecio = FactoryCotizacionPrecio.getDAORACCotizacionPrecio(TipoImplementacionDAO.GESTORTX);
            daoRACCotizacionPrecio.openConnection(); 
            bRetorno= daoRACCotizacionPrecio.pruebaSolicitudDeCambioProducto(codSolic, local,recibe);
            daoRACCotizacionPrecio.commit();            
        } catch (Exception e) {
            daoRACCotizacionPrecio.rollback();
            log.error("", e);
            return null;
        }

        return bRetorno;
    }
    
  
    
  
    
}
