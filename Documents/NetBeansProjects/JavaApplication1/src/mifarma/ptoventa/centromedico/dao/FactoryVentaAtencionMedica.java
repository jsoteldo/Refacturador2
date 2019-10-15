package mifarma.ptoventa.centromedico.dao;

import mifarma.ptoventa.reference.TipoImplementacionDAO;

/**
 * @author ERIOS
 * @since 24.08.2016
 */
public class FactoryVentaAtencionMedica {

    public FactoryVentaAtencionMedica() {
        super();
    }

    public static DAOVentaAtencionMedica getDAOVentaAtencionMedica(TipoImplementacionDAO tipo) {
        DAOVentaAtencionMedica dao;
        switch (tipo) {
        case MYBATIS:
            dao = new MBVentaAtencionMedica();
            break;
        default:
            dao = null;
            break;
        }
        return dao;
    }
    
    public static DAORACVentaAtencionMedica getDAORACVentaAtencionMedica(TipoImplementacionDAO tipo) {
        DAORACVentaAtencionMedica dao;
        switch (tipo) {            
        case FRAMEWORK:
            dao = null;
            break;
        case MYBATIS:
            dao = null;
            break;
        case GESTORTX:
            dao = new GTRACVentaAtencionMedica();
            break;
        default:
            dao = null;
            break;
        }
        return dao;
    }

}
