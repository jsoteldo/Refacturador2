package mifarma.ptoventa.cotizarPrecios.DAO;

import mifarma.ptoventa.centromedico.dao.DAORACVentaAtencionMedica;
import mifarma.ptoventa.centromedico.dao.DAOVentaAtencionMedica;
import mifarma.ptoventa.centromedico.dao.FactoryVentaAtencionMedica;
import mifarma.ptoventa.centromedico.dao.GTRACVentaAtencionMedica;
import mifarma.ptoventa.centromedico.dao.MBVentaAtencionMedica;
import mifarma.ptoventa.reference.TipoImplementacionDAO;

public class FactoryCotizacionPrecio {
    public FactoryCotizacionPrecio() {
        super();
    }

    public static DAORACCotizacionPrecio getDAORACCotizacionPrecio(TipoImplementacionDAO tipo) {
        DAORACCotizacionPrecio dao;
        switch (tipo) {            
        case FRAMEWORK:
            dao = null;
            break;
        case MYBATIS:
            dao = null;
            break;
        case GESTORTX:
            dao = new GTRACCotizacionPrecio();
            break;
        default:
            dao = null;
            break;
        }
        return dao;
    }
}
