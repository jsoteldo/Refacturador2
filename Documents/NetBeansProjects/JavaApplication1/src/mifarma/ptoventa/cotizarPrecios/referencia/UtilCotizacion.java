package mifarma.ptoventa.cotizarPrecios.referencia;

import java.sql.SQLException;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;

import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilCotizacion {
    static final Logger log = LoggerFactory.getLogger(UtilCotizacion.class);
    
    public UtilCotizacion() {
        super();
    }

    public static int existenSolicitudes(String fecInicio, String fecFin, int conDocumento) {
        try {
            return BDCotizacionPrecio.recuperaCantidad_Solicitudes(fecInicio,fecFin,conDocumento);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public static void cargaTabla_Solicitud(FarmaTableModel pTablaSolicitudes, String fechaIni, String fechaFin, int conDocumento) {
        try {
            BDCotizacionPrecio.cargaTabla_Solicitud(pTablaSolicitudes,fechaIni,fechaFin,conDocumento);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static int existenSolicitudesXEstado(String estado, int conDocumento) {
        try {
            return BDCotizacionPrecio.existenSolicitudesXEstado(estado,conDocumento);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public static void cargaTabla_SolicitudXDefecto(FarmaTableModel pTablaSolicitudes, String estado, int conDocumento) {
        try {
            BDCotizacionPrecio.cargaTabla_SolicitudXDefecto(pTablaSolicitudes,estado,conDocumento);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void cargaTabla_Productos(FarmaTableModel pTablaProductos,String codSolicitud) {
        try {
            BDCotizacionPrecio.cargaTabla_Productos(pTablaProductos,codSolicitud);
            FarmaUtility.aceptarTransaccion();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void getTabla_Productos(FarmaTableModel pTablaProductos,String codSolicitud) {
        try {
            BDCotizacionPrecio.getTabla_Productos(pTablaProductos,codSolicitud);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static String cambiarProducto_Cotizacion(String codNuevo_Prod) {
        try {
            String rpta=BDCotizacionPrecio.cambiarProducto_Cotizacion(codNuevo_Prod);
            if(rpta.equalsIgnoreCase("Ok")){
                FarmaUtility.aceptarTransaccion();
                return "Se realizo correctamente el cambio de producto a cotizar";
            }else{
                FarmaUtility.liberarTransaccion();
                return "Se produjo un error en el procedimiento de actualizacion\nNo se insertaron los datos";
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            FarmaUtility.liberarTransaccion();
            return "Se produjo un error en la ejecucion: "+e.getErrorCode()+"\n"+e.getMessage();
        }
    }

    public static String verificaAutorizacion_deCambio(String codSolicitud, String codProducto) {
        try {
            String rpta=BDCotizacionPrecio.verificaAutorizacion_deCambio(codSolicitud,codProducto);
            return rpta;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            FarmaUtility.liberarTransaccion();
            return "X";
        }   
    }
    
    public static void cargaTabla_ProductosCotizacion(FarmaTableModel pTablaProductos,String codSolicitud) {
        try {
            BDCotizacionPrecio.cargaTabla_ProductosCotizacion(pTablaProductos,codSolicitud);
            FarmaUtility.aceptarTransaccion();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
