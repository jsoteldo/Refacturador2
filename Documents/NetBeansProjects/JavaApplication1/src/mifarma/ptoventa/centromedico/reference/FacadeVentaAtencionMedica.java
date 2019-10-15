package mifarma.ptoventa.centromedico.reference;

import java.util.ArrayList;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.dao.DAORACVentaAtencionMedica;
import mifarma.ptoventa.centromedico.dao.DAOVentaAtencionMedica;
import mifarma.ptoventa.centromedico.dao.FactoryVentaAtencionMedica;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaDiagnosticoRAC;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaEnfRAC;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaExFiRAC;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaExamenesRAC;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaProcedimientosRAC;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaRAC;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaTratamientoRAC;
import mifarma.ptoventa.centromedico.domain.CmeAtencionMedicaTriajeRAC;
import mifarma.ptoventa.centromedico.domain.CmeHCAntecedentesDetalleRAC;
import mifarma.ptoventa.centromedico.domain.CmeHCAntecedentesGinecoRAC;
import mifarma.ptoventa.centromedico.domain.CmeHCAntecedentesPatoloRAC;
import mifarma.ptoventa.centromedico.domain.CmeHCAntecedentesRAC;
import mifarma.ptoventa.centromedico.domain.CmeHistoriaClinica;
import mifarma.ptoventa.centromedico.domain.CmePaciente;
import mifarma.ptoventa.centromedico.domain.HistCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaCab;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaCabRAC;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaDet;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaDetRAC;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaPedidoDet;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.reference.TipoImplementacionDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ERIOS
 * @since 24.08.2016
 */
public class FacadeVentaAtencionMedica {
    
    private static final Logger log = LoggerFactory.getLogger(FacadeVentaAtencionMedica.class);
    
    public FacadeVentaAtencionMedica() {
        super();
    }
    
    public boolean registrarVenta(String pNumPedVta){
        boolean bRetorno = false;
        VtaPedidoAtencionMedica vtaPedido = null;
        VtaCompAtencionMedica vtaCompAtencion = null;
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Lee cabecera
            vtaPedido = daoVentaAtencionMedica.getPedidoCabLocal(pNumPedVta);    
            //1.2 Recupera comp-atencion
            daoVentaAtencionMedica.generaCompAtencion(pNumPedVta);
            vtaCompAtencion = daoVentaAtencionMedica.getCompAtencion(pNumPedVta);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(vtaPedido == null){
            return bRetorno;
        }
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Graba cabecera
            daoRACVentaAtencionMedica.savePedidoCabRAC(vtaPedido);
            //2.2 Graba comp-atencion
            daoRACVentaAtencionMedica.saveCompAtencion(vtaCompAtencion);            
            
            registrarEstadoCompAten(FarmaVariables.vCodLocal,
                                    pNumPedVta);
            
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
        }
        
        
        
        //TODO para pruebas
        //recuperarHistoriaClinica("0041854034");
        //recuperarAntecedentes("0041854034", 1);
        
        //recuperarAntenciones("001", "001", "0000011111");
                                                                                  
        return bRetorno;
    }
    
    public boolean recuperarVenta(String pTipComp, String pNumComp){
        boolean bRetorno = false;
        VtaPedidoAtencionMedica vtaPedido = null;
        VtaCompAtencionMedica vtaCompAtencion = null;
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Lee cabecera
            vtaPedido = daoRACVentaAtencionMedica.getPedidoAtencion(pTipComp,pNumComp);
            //2.2 Lee comp-atencion            
            vtaCompAtencion = daoRACVentaAtencionMedica.getCompAtencion(pTipComp,pNumComp);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(vtaPedido == null){
            return bRetorno;
        }
        
        //TODO Para pruebas
        //vtaPedido.setCod_local("001");
        //vtaCompAtencion.setCod_local("001");
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Graba cabecera
            daoVentaAtencionMedica.setPedidoAtencion(vtaPedido);    
            //1.2 Graba comp-atencion
            daoVentaAtencionMedica.setCompAtencion(vtaCompAtencion);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
                
        return bRetorno;
    }
    
    public boolean recuperarReceta(String pCodLocal, String pNumReceta) {
        boolean bRetorno = false;
        VtaPedRecetaCab vtaPedRecetaCab = null;
        ArrayList<VtaPedRecetaDet> lstPedRecetaDet = null;
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Lee cabecera
            vtaPedRecetaCab = daoRACVentaAtencionMedica.getPedidoRecetaCab(pCodLocal,pNumReceta);
            //2.2 Lee detalle
            lstPedRecetaDet = daoRACVentaAtencionMedica.getPedidoRecetaDet(pCodLocal,pNumReceta);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(vtaPedRecetaCab == null){
            return bRetorno;
        }
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Graba cabecera
            daoVentaAtencionMedica.setPedidoRecetaCab(vtaPedRecetaCab);    
            //1.2 Graba detalle
            daoVentaAtencionMedica.setPedidoRecetaDet(lstPedRecetaDet);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);
        }
                
        return bRetorno;
    }
    
    public boolean recuperarRecetaPedidoDet(String pCodLocal, String pNumReceta){
        boolean bRetorno = false;
        ArrayList<VtaPedRecetaPedidoDet> vtaPedRecetaPedidoDet = null;
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.2 Lee historico de ventas por receta           
            vtaPedRecetaPedidoDet = daoRACVentaAtencionMedica.getPedidoRecetaPedidoDet(pCodLocal,pNumReceta);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        /*if(vtaPedido == null){
            return bRetorno;
        }*/
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.2 Graba historico de ventas por receta
            daoVentaAtencionMedica.setPedidoRecetaPedidoDet(vtaPedRecetaPedidoDet);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
                
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 05.09.2016
     * @param pCodLocal
     * @param pNumReceta
     * @return
     */
    public boolean registrarReceta(String pCodLocal, String pNumReceta){
        boolean bRetorno = false;
        VtaPedRecetaCabRAC vtaPedRecetaCab = null;
        ArrayList<VtaPedRecetaDetRAC> vtaPedRecetaDet = null;
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Lee cabecera
            vtaPedRecetaCab = daoVentaAtencionMedica.getPedidoRecetaCab(pCodLocal,pNumReceta);    
            //1.2 Lee detalle
            vtaPedRecetaDet = daoVentaAtencionMedica.getPedidoRecetaDet(pCodLocal,pNumReceta);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
        
        if(vtaPedRecetaCab == null){
            return bRetorno;
        }
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Graba cabecera
            daoRACVentaAtencionMedica.setPedidoRecetaCab(vtaPedRecetaCab,vtaPedRecetaDet);
            //2.2 Graba detalle
            daoRACVentaAtencionMedica.setPedidoRecetaDet(vtaPedRecetaDet);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
                
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 05.09.2016
     * @param pCodLocal
     * @param pNumReceta
     * @return
     */
    public boolean registrarRecetaPedidoDet(String pCodLocal, String pNumReceta){
        boolean bRetorno = false;
        ArrayList<VtaPedRecetaPedidoDet> vtaPedRecetaPedidoDet = null;
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.2 Lee historico de ventas por receta
            vtaPedRecetaPedidoDet = daoVentaAtencionMedica.getPedidoRecetaPedidoDet(pCodLocal,pNumReceta);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
        
        if(vtaPedRecetaPedidoDet == null){
            return bRetorno;
        }
        
        if(vtaPedRecetaPedidoDet.size() == 0){
            return bRetorno;
        }
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.2 Graba historico de ventas por receta
            daoRACVentaAtencionMedica.setPedidoRecetaPedidoDet(vtaPedRecetaPedidoDet);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
               
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 06.09.2016
     * @param pCodPaciente
     * @return
     */
    public boolean registrarHistoriaClinica(String pCodPaciente){
        boolean bRetorno = false;
        CmePaciente cmePaciente = null;
        CmeHistoriaClinica cmeHistoriaclinica = null;
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Lee paciente
            cmePaciente = daoVentaAtencionMedica.getPaciente(pCodPaciente);    
            //1.2 Lee historia clinica
            cmeHistoriaclinica = daoVentaAtencionMedica.getHistoriaClinica(pCodPaciente);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(cmePaciente == null){
            return bRetorno;
        }
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Graba paciente
            daoRACVentaAtencionMedica.savePaciente(cmePaciente);
            //2.2 Graba historia clinica
            daoRACVentaAtencionMedica.saveHistoriaClinica(cmeHistoriaclinica);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
        }
        
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 06.09.2016
     * @param pCodPaciente
     * @return
     */
    public boolean recuperarHistoriaClinica(String pCodPaciente){
        boolean bRetorno = false;
        CmePaciente cmePaciente = null;
        CmeHistoriaClinica cmeHistoriaClinica = null;
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Lee paciente
            cmePaciente = daoRACVentaAtencionMedica.getPaciente(pCodPaciente);
            //2.2 Lee historia clinica
            cmeHistoriaClinica = daoRACVentaAtencionMedica.getHistoriaClinica(pCodPaciente);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(cmePaciente == null){
            return bRetorno;
        }
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Graba paciente
            if(cmePaciente.getSec_antecedente_hc() == null || cmePaciente.getSec_antecedente_hc().trim().equals("")){
                daoVentaAtencionMedica.setPaciente2(cmePaciente);
            }else{
                daoVentaAtencionMedica.setPaciente(cmePaciente);
            }
            //1.2 Graba historia clinica
            daoVentaAtencionMedica.setHistoriaClinica(cmeHistoriaClinica);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
                
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 09.09.2016
     * @param pCodLocal
     * @param pNumPedVta
     * @return
     */
    public boolean registrarEstadoCompAten(String pCodLocal, String pNumPedVta){
        boolean bRetorno = false;
        ArrayList<HistCompAtencionMedica> lstEstComp = null;
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Lee cabecera
            lstEstComp = daoVentaAtencionMedica.getEstCompAtencion(pCodLocal,pNumPedVta);    
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(lstEstComp == null ){
            return bRetorno;
        }
        
        if(lstEstComp.size()==0){
            return bRetorno;
        }
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Graba cabecera
            daoRACVentaAtencionMedica.saveEstCompAtencion(lstEstComp);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
        }
        
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 13.09.2016
     * @param pCodPaciente
     * @param pSecAntec
     * @return
     */
    public boolean recuperarAntecedentes(String pCodPaciente, int pSecAntec,String pCodLocal){
        boolean bRetorno = false;
        CmeHCAntecedentesRAC cmeHCAntecedentes = null;
        CmeHCAntecedentesGinecoRAC cmeHCAntecedentesGineco = null;
        ArrayList<CmeHCAntecedentesDetalleRAC> lstHCAntecedentesDetalle = null;
        ArrayList<CmeHCAntecedentesPatoloRAC> lstHCAntecedentesPatolo = null;
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Lee cabecera
            cmeHCAntecedentes = daoRACVentaAtencionMedica.getHCAntecedentes(pCodPaciente,pSecAntec,pCodLocal);
            //2.2 Lee comp-atencion            
            cmeHCAntecedentesGineco = daoRACVentaAtencionMedica.getHCAntecedentesGineco(pCodPaciente,pSecAntec,pCodLocal);
            lstHCAntecedentesDetalle = daoRACVentaAtencionMedica.getHCAntecedentesDetalle(pCodPaciente,pSecAntec,pCodLocal);
            lstHCAntecedentesPatolo = daoRACVentaAtencionMedica.getHCAntecedentesPatolo(pCodPaciente,pSecAntec,pCodLocal);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(cmeHCAntecedentes == null){
            return bRetorno;
        }
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Graba cabecera
            daoVentaAtencionMedica.setHCAntecedentes(cmeHCAntecedentes);    
            //1.2 Graba comp-atencion
            daoVentaAtencionMedica.setHCAntecedentesGineco(cmeHCAntecedentesGineco);
            daoVentaAtencionMedica.setHCAntecedentesDetalle(lstHCAntecedentesDetalle);
            daoVentaAtencionMedica.setHCAntecedentesPatolo(lstHCAntecedentesPatolo);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
                
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 14.09.2016
     * @param pCodCia
     * @param pCodLocal
     * @param pNumAtenMed
     * @return
     */
    public boolean recuperarAntenciones(String pCodCia, String pCodLocal, String pNumAtenMed){
        boolean bRetorno = false;
        CmeAtencionMedicaRAC cmeAtencionMedica = null;
        CmeAtencionMedicaTriajeRAC cmeAtencionMedicaTriaje = null;
        CmeAtencionMedicaEnfRAC cmeAtencionMedicaEnf = null;
        CmeAtencionMedicaExFiRAC cmeAtencionMedicaExFi = null;
        ArrayList<CmeAtencionMedicaDiagnosticoRAC> lstAtencionMedicaDiagnostico = null;
        CmeAtencionMedicaTratamientoRAC cmeAtencionMedicaTratamiento = null;
        CmeAtencionMedicaExamenesRAC cmeAtencionMedicaExamenes = null;
        CmeAtencionMedicaProcedimientosRAC cmeAtencionMedicaProcedimientos = null;
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.1 Lee cabecera
            cmeAtencionMedica = daoRACVentaAtencionMedica.getAtencionMedica(pCodCia,pCodLocal,pNumAtenMed);
            //2.2 Lee comp-atencion            
            cmeAtencionMedicaTriaje = daoRACVentaAtencionMedica.getAtencionMedicaTriaje(pCodCia,pCodLocal,pNumAtenMed);
            cmeAtencionMedicaEnf = daoRACVentaAtencionMedica.getAtencionMedicaEnfermedad(pCodCia,pCodLocal,pNumAtenMed);
            cmeAtencionMedicaExFi = daoRACVentaAtencionMedica.getAtencionMedicaExamenFi(pCodCia,pCodLocal,pNumAtenMed);
            lstAtencionMedicaDiagnostico = daoRACVentaAtencionMedica.getAtencionMedicaDiagnostico(pCodCia,pCodLocal,pNumAtenMed);
            cmeAtencionMedicaTratamiento = daoRACVentaAtencionMedica.getAtencionMedicaTratamiento(pCodCia,pCodLocal,pNumAtenMed);
            
            cmeAtencionMedicaExamenes = daoRACVentaAtencionMedica.getAtencionMedicaExamenes(pCodCia,pCodLocal,pNumAtenMed);
            cmeAtencionMedicaProcedimientos = daoRACVentaAtencionMedica.getAtencionMedicaProcedimientoss(pCodCia,pCodLocal,pNumAtenMed);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        if(cmeAtencionMedica == null){
            return bRetorno;
        }
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.1 Graba cabecera
            daoVentaAtencionMedica.setAtencionMedica(cmeAtencionMedica);    
            //1.2 Graba comp-atencion
            daoVentaAtencionMedica.setAtencionMedicaTriaje(cmeAtencionMedicaTriaje);
            daoVentaAtencionMedica.setAtencionMedicaEnfermedad(cmeAtencionMedicaEnf);
            daoVentaAtencionMedica.setAtencionMedicaExamenFi(cmeAtencionMedicaExFi);
            daoVentaAtencionMedica.setAtencionMedicaDiagnostico(lstAtencionMedicaDiagnostico);
            daoVentaAtencionMedica.setAtencionMedicaTratamiento(cmeAtencionMedicaTratamiento);
            
            daoVentaAtencionMedica.setAtencionMedicaExamenes(cmeAtencionMedicaExamenes);
            daoVentaAtencionMedica.setAtencionMedicaProcedimientos(cmeAtencionMedicaProcedimientos);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
                
        recuperarReceta(cmeAtencionMedicaTratamiento.getCod_local(), cmeAtencionMedicaTratamiento.getNum_ped_rec());
                                                            
        return bRetorno;
    }
    
    /**
     * @author ERIOS
     * @since 15.09.2016
     * @param pCodLocal
     * @param pNumPedVta
     * @return
     */
    public boolean recuperarEstadoCompAten(String pCodLocal, String pNumPedVta){
        boolean bRetorno = false;
        ArrayList<HistCompAtencionMedica> lstEstComp = null;
        
        DAORACVentaAtencionMedica daoRACVentaAtencionMedica = null;
        TipoImplementacionDAO tipo;
        tipo = TipoImplementacionDAO.GESTORTX;
        try {
            //2.0 Abre conexion RAC
            daoRACVentaAtencionMedica = FactoryVentaAtencionMedica.getDAORACVentaAtencionMedica(tipo);
            daoRACVentaAtencionMedica.openConnection();
            //2.2 Lee historico de ventas por receta           
            lstEstComp = daoRACVentaAtencionMedica.getEstCompAtencion(pCodLocal,pNumPedVta);
            //2.6 Cierra conexion RAC
            daoRACVentaAtencionMedica.commit();
            
        } catch (Exception e) {
            daoRACVentaAtencionMedica.rollback();
            log.error("", e);
            return bRetorno;
        }
        
        /*if(vtaPedido == null){
            return bRetorno;
        }*/
        
        DAOVentaAtencionMedica daoVentaAtencionMedica = null;
        daoVentaAtencionMedica = FactoryVentaAtencionMedica.getDAOVentaAtencionMedica(TipoImplementacionDAO.MYBATIS);
        try {
            //1.0 Abre conexion local
            daoVentaAtencionMedica.openConnection();
            //1.2 Graba historico de ventas por receta
            daoVentaAtencionMedica.setEstCompAtencion(lstEstComp);
            //1.6 Cierre conexion local
            daoVentaAtencionMedica.commit();
            bRetorno = true;
        } catch (Exception e) {
            daoVentaAtencionMedica.rollback();
            log.error("", e);            
        }
                
        return bRetorno;
    }
}
