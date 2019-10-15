package mifarma.ptoventa.centromedico.reference;

import java.awt.Frame;

import java.util.ArrayList;

import java.util.List;

import mifarma.ptoventa.centromedico.dao.DAOAdmisionMedica;
import mifarma.ptoventa.centromedico.dao.DAOAtencionMedica;
import mifarma.ptoventa.centromedico.dao.MBAdmisionMedica;
import mifarma.ptoventa.centromedico.dao.MBAtencionMedica;

import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.encuesta.dao.MBEncuesta;
import mifarma.ptoventa.encuesta.reference.FacadeEncuesta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacadeAdmisionMedica {

    private static final Logger log = LoggerFactory.getLogger(FacadeAdmisionMedica.class);
    private DAOAdmisionMedica daoAdmisionMedica;
    
    //private Frame myParentFrame;
    
    public FacadeAdmisionMedica() {
        super();
        daoAdmisionMedica = new MBAdmisionMedica();
    }
    
    /*public FacadeAdmisionMedica(Frame myParentFrame) {
        super();
        daoAdmisionMedica = new MBAdmisionMedica();
        this.myParentFrame = myParentFrame;
    }*/
    
    public List<VtaPedidoAtencionMedica>listPedidoATM(VtaPedidoAtencionMedica vtaPAM){//(String pCodGrupoCia, String pCodEstado, String pCodMedico){
        List<VtaPedidoAtencionMedica> lista = null;
        try{
            daoAdmisionMedica.openConnection();
            lista = daoAdmisionMedica.listPedidoATM(vtaPAM);
            daoAdmisionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAdmisionMedica.rollback();
            lista = new ArrayList<VtaPedidoAtencionMedica>();
        }
        return lista;
    }
   

        
}
