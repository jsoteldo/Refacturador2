package mifarma.ptoventa.centromedico.dao;

import mifarma.ptoventa.reference.TipoImplementacionDAO;

public class FactoryAtencionMedica {
    public FactoryAtencionMedica() {
        super();
    }
    
    public static DAOAtencionMedica getDAOAtencionMedica(TipoImplementacionDAO tipo) {
        DAOAtencionMedica dao;
        switch(tipo){
            case FRAMEWORK: 
                dao = null; 
                break;
            case MYBATIS:
                dao = new MBAtencionMedica(); 
                break;
            case GESTORTX:
                dao = new GTAtencionMedica(); 
                break;
            default:
                dao = null;
                break;
        }
        return dao;
    }
}
