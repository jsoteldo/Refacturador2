package mifarma.ptoventa.reportes.reference;

import java.awt.Frame;

import java.sql.SQLException;

import java.util.List;

import mifarma.ptoventa.reportes.dao.DAOReportes;
import mifarma.ptoventa.reportes.dao.MBReporte;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FacadeReporte {
    
    private static final Logger log = LoggerFactory.getLogger(FacadeReporte.class);
    private DAOReportes daoReportes;
    private Frame myParentFrame;
    
    public FacadeReporte() {
        super();
        daoReportes = new MBReporte();
    }
    
    public FacadeReporte(Frame myParentFrame) {
        super();
        daoReportes = new MBReporte();
        this.myParentFrame = myParentFrame;
    }
    
    public List obtenerPeriodoReporteGigantes(String pCodGrupoCia, String pCodLocal){
        List listaPeriodo = null;
        try{
            daoReportes.openConnection();
            listaPeriodo = daoReportes.obtenerPeriodoReporteGigantes(pCodGrupoCia, pCodLocal);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoReportes.rollback();
            listaPeriodo = null;
        }
        return listaPeriodo;
    }
    
    public String obtenerMsjInfoComisionGiganteA(){
        return obtenerMsjInfoComisionGigante("A");
    }
    
    public String obtenerMsjInfoComisionGiganteB(){
        return obtenerMsjInfoComisionGigante("B");
    }
    
    private String obtenerMsjInfoComisionGigante(String tipoMensaje){
        String mensaje = "";
        try{
            daoReportes.openConnection();
            mensaje = daoReportes.obtenerInfoComisionGigante(tipoMensaje);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoReportes.rollback();
        }
        return mensaje;
    }
    
    public List obtenerResumenComisionGigante(String pCodGrupoCia, String pCodLocal, String pMesId){
        List listaResumen = null;
        try{
            daoReportes.openConnection();
            listaResumen = daoReportes.obtenerResumenComisionGigante(pCodGrupoCia, pCodLocal, pMesId);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoReportes.rollback();
            listaResumen = null;
        }
        return listaResumen;
    }
    
    public void procesarVentasComisionGigante(String pCodGrupoCia, String pCodLocal, String pMesId){
        try{
            daoReportes.openConnection();
            daoReportes.procesarComisionGigante(pCodGrupoCia, pCodLocal, pMesId);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoReportes.rollback();
        }
    }

    /**
     * @author ERIOS
     * @since 18.04.2016
     * @return
     */
    public String getVerTipoComision() {
        String strRetorno;
        try {
             strRetorno = DBReportes.getVerTipoComision();
        } catch (SQLException e) {
            log.error("",e);
            strRetorno = "A";
        }
        return strRetorno;
    }

    /**
     * @author ERIOS
     * @since 16.09.2016
     * @return
     */
    public String getVerRentables() {
        String strRetorno;
        try {
             strRetorno = DBReportes.getVerRentables();
        } catch (SQLException e) {
            log.error("",e);
            strRetorno = "A";
        }
        return strRetorno;
    }
    
    //INI AOVIEDO 18/04/2017
    public List obtenerPeriodoReporteGarantizados(String pCodGrupoCia, String pCodLocal){
        List listaPeriodo = null;
        try{
            daoReportes.openConnection();
            listaPeriodo = daoReportes.obtenerPeriodoReporteGarantizados(pCodGrupoCia, pCodLocal);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoReportes.rollback();
            listaPeriodo = null;
        }
        return listaPeriodo;
    }
    
    public String obtenerMsjInfoComisionGarantizadoA(){
        return obtenerMsjInfoComisionGarantizado("A");
    }
    
    private String obtenerMsjInfoComisionGarantizado(String tipoMensaje){
        String mensaje = "";
        
        try{
            daoReportes.openConnection();
            mensaje = daoReportes.obtenerInfoComisionGarantizado(tipoMensaje);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoReportes.rollback();
        }
        return mensaje;
    }
    
    public void procesarVentasComisionGarantizado(String pCodGrupoCia, String pCodLocal, String pMesId){
        try{
            daoReportes.openConnection();
            daoReportes.procesarComisionGarantizado(pCodGrupoCia, pCodLocal, pMesId);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoReportes.rollback();
        }
    }
    
    public List obtenerResumenComisionGarantizado(String pCodGrupoCia, String pCodLocal, String pMesId){
        List listaResumen = null;
        try{
            daoReportes.openConnection();
            listaResumen = daoReportes.obtenerResumenComisionGarantizado(pCodGrupoCia, pCodLocal, pMesId);
            daoReportes.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoReportes.rollback();
            listaResumen = null;
        }
        return listaResumen;
    }
    //FIN AOVIEDO 18/04/2017
}
