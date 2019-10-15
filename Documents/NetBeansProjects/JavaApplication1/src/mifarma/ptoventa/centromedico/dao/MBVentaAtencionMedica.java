package mifarma.ptoventa.centromedico.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mifarma.common.FarmaVariables;

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
import mifarma.ptoventa.convenioBTLMF.dao.MapperConvenioBTLMF;
import mifarma.ptoventa.reference.MyBatisUtil;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.apache.ibatis.session.SqlSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ERIOS
 * @since 24.08.2016
 */
public class MBVentaAtencionMedica implements DAOVentaAtencionMedica {

    private static final Logger log = LoggerFactory.getLogger(MBVentaAtencionMedica.class);

    private SqlSession sqlSession = null;
    private UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    private MapperConvenioBTLMF mapperTrsscSix = null;
    private MapperVentaAtencionMedica mapper = null;

    public VtaPedidoAtencionMedica getPedidoCabLocal(String pNumPedVta) throws Exception {
        VtaPedidoAtencionMedica vtaPedido;
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodLocal", FarmaVariables.vCodLocal);
        mapParametros.put("cNumPedVta", pNumPedVta);
        
        vtaPedido = mapper.getPedidoCabLocal(mapParametros);

        return vtaPedido;
    }

    @Override
    public void commit() {
        sqlSession.commit(true);
        sqlSession.close();
    }

    @Override
    public void rollback() {
        sqlSession.rollback(true);
        sqlSession.close();
    }

    @Override
    public void openConnection() {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        mapper = sqlSession.getMapper(MapperVentaAtencionMedica.class);
    }

    @Override
    public VtaCompAtencionMedica getCompAtencion(String pNumPedVta) throws Exception {
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodLocal", FarmaVariables.vCodLocal);
        mapParametros.put("cNumPedVta", pNumPedVta);
        
        return mapper.getCompAtencion(mapParametros);
    }

    @Override
    public void generaCompAtencion(String pNumPedVta) throws Exception {
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodCia_in", FarmaVariables.vCodCia);
        mapParametros.put("cCodLocal_in", FarmaVariables.vCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        mapParametros.put("cIdUsu_in",FarmaVariables.vIdUsu);
        
        mapper.generaCompAtencion(mapParametros);
    }

    @Override
    public void setPedidoAtencion(VtaPedidoAtencionMedica vtaPedidoAtencionMedica) throws Exception {
        mapper.setPedidoAtencion(vtaPedidoAtencionMedica);
    }

    @Override
    public void setCompAtencion(VtaCompAtencionMedica vtaCompAtencionMedica) throws Exception {
        mapper.setCompAtencion(vtaCompAtencionMedica);
    }

    @Override
    public void setPedidoRecetaCab(VtaPedRecetaCab vtaPedRecetaCab) {
        mapper.setPedidoRecetaCab(vtaPedRecetaCab);
    }

    @Override
    public void setPedidoRecetaDet(ArrayList<VtaPedRecetaDet> lstPedRecetaDet) {
        for(VtaPedRecetaDet vtaPedRecetaDet:lstPedRecetaDet){
            mapper.setPedidoRecetaDet(vtaPedRecetaDet);
        }
    }

    @Override
    public void setPedidoRecetaPedidoDet(ArrayList<VtaPedRecetaPedidoDet> lstPedRecetaPedidoDet) {
        for(VtaPedRecetaPedidoDet vtaPedRecetaDet:lstPedRecetaPedidoDet){
            mapper.setRecetaPedidoDet(vtaPedRecetaDet);
        }
    }

    @Override
    public VtaPedRecetaCabRAC getPedidoRecetaCab(String pCodLocal, String pNumReceta) throws Exception {
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedRec", pNumReceta);
        
        return mapper.getPedidoRecetaCab(mapParametros);
    }

    @Override
    public ArrayList<VtaPedRecetaDetRAC> getPedidoRecetaDet(String pCodLocal, String pNumReceta) throws Exception {
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedRec", pNumReceta);
        
        return mapper.getPedidoRecetaDet(mapParametros);
    }

    @Override
    public ArrayList<VtaPedRecetaPedidoDet> getPedidoRecetaPedidoDet(String pCodLocal, String pNumReceta) throws Exception {
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedRec", pNumReceta);
        
        return mapper.getPedidoRecetaPedidoDet(mapParametros);
    }

    @Override
    public CmePaciente getPaciente(String pCodPaciente) {
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodPaciente", pCodPaciente);
        
        return mapper.getPaciente(mapParametros);
    }

    @Override
    public CmeHistoriaClinica getHistoriaClinica(String pCodPaciente) {
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodPaciente", pCodPaciente);
        
        return mapper.getHistoriaClinica(mapParametros);
    }

    @Override
    public void setPaciente(CmePaciente cmePaciente) {
        mapper.setPaciente(cmePaciente);
    }

    @Override
    public void setPaciente2(CmePaciente cmePaciente) {
        mapper.setPaciente2(cmePaciente);
    }

    @Override
    public void setHistoriaClinica(CmeHistoriaClinica cmeHistoriaClinica) {
        mapper.setHistoriaClinica(cmeHistoriaClinica);
    }

    @Override
    public ArrayList<HistCompAtencionMedica> getEstCompAtencion(String pCodLocal, String pNumPedVta) {
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedVta", pNumPedVta);
        
        return mapper.getEstCompAtencion(mapParametros);
    }

    @Override
    public void setHCAntecedentes(CmeHCAntecedentesRAC cmeHCAntecedentesRAC) {
        mapper.setHCAntecedentes(cmeHCAntecedentesRAC);
    }

    @Override
    public void setHCAntecedentesGineco(CmeHCAntecedentesGinecoRAC cmeHCAntecedentesGinecoRAC) {
        mapper.setHCAntecedentesGineco(cmeHCAntecedentesGinecoRAC);
    }

    @Override
    public void setHCAntecedentesDetalle(ArrayList<CmeHCAntecedentesDetalleRAC> lstHCAntecedentesDetalleRAC) {
        for(CmeHCAntecedentesDetalleRAC cmeHCAntecedentesDetalleRAC:lstHCAntecedentesDetalleRAC){
            mapper.setHCAntecedentesDetalle(cmeHCAntecedentesDetalleRAC);
        }
    }

    @Override
    public void setHCAntecedentesPatolo(ArrayList<CmeHCAntecedentesPatoloRAC> lstHCAntecedentesPatoloRAC) {
        for(CmeHCAntecedentesPatoloRAC cmeHCAntecedentesPatoloRAC:lstHCAntecedentesPatoloRAC){
            mapper.setHCAntecedentesPatolo(cmeHCAntecedentesPatoloRAC);
        }
    }

    @Override
    public void setAtencionMedica(CmeAtencionMedicaRAC cmeAtencionMedicaRAC) {
        mapper.setAtencionMedica(cmeAtencionMedicaRAC);
    }

    @Override
    public void setAtencionMedicaTriaje(CmeAtencionMedicaTriajeRAC cmeAtencionMedicaTriajeRAC) {
        mapper.setAtencionMedicaTriaje(cmeAtencionMedicaTriajeRAC);
    }

    @Override
    public void setAtencionMedicaEnfermedad(CmeAtencionMedicaEnfRAC cmeAtencionMedicaEnfRAC) {
        mapper.setAtencionMedicaEnfermedad(cmeAtencionMedicaEnfRAC);
    }

    @Override
    public void setAtencionMedicaExamenFi(CmeAtencionMedicaExFiRAC cmeAtencionMedicaExFiRAC) {
        mapper.setAtencionMedicaExamenFi(cmeAtencionMedicaExFiRAC);
    }

    @Override
    public void setAtencionMedicaDiagnostico(ArrayList<CmeAtencionMedicaDiagnosticoRAC> lstAtencionMedicaDiagnosticoRAC) {
        for(CmeAtencionMedicaDiagnosticoRAC cmeAtencionMedicaDiagnosticoRAC:lstAtencionMedicaDiagnosticoRAC){
            mapper.setAtencionMedicaDiagnostico(cmeAtencionMedicaDiagnosticoRAC);
        }
    }

    @Override
    public void setAtencionMedicaTratamiento(CmeAtencionMedicaTratamientoRAC cmeAtencionMedicaTratamientoRAC) {
        mapper.setAtencionMedicaTratamiento(cmeAtencionMedicaTratamientoRAC);
    }

    @Override
    public void setAtencionMedicaExamenes(CmeAtencionMedicaExamenesRAC cmeAtencionMedicaExamenesRAC) {
        mapper.setAtencionMedicaExamenes(cmeAtencionMedicaExamenesRAC);
    }

    @Override
    public void setAtencionMedicaProcedimientos(CmeAtencionMedicaProcedimientosRAC cmeAtencionMedicaProcedimientosRAC) {
        mapper.setAtencionMedicaProcedimientos(cmeAtencionMedicaProcedimientosRAC);
    }

    @Override
    public void setEstCompAtencion(ArrayList<HistCompAtencionMedica> lstCompAtencionMedica) {
        for(HistCompAtencionMedica histCompAtencionMedica:lstCompAtencionMedica){
            mapper.setEstCompAtencion(histCompAtencionMedica);
        }
    }
}
