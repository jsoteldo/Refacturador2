package mifarma.ptoventa.centromedico.dao;

import java.util.ArrayList;

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
import mifarma.ptoventa.reference.DAOTransaccion;


/**
 * @author ERIOS
 * @since 24.08.2016
 */
public interface DAOVentaAtencionMedica extends DAOTransaccion {

    public VtaPedidoAtencionMedica getPedidoCabLocal(String pNumPedVta) throws Exception;

    public VtaCompAtencionMedica getCompAtencion(String pNumPedVta) throws Exception;

    public void generaCompAtencion(String pNumPedVta) throws Exception;

    public void setPedidoAtencion(VtaPedidoAtencionMedica vtaPedidoAtencionMedica) throws Exception;

    public void setCompAtencion(VtaCompAtencionMedica vtaCompAtencionMedica) throws Exception;

    public void setPedidoRecetaCab(VtaPedRecetaCab vtaPedRecetaCab) throws Exception;

    public void setPedidoRecetaDet(ArrayList<VtaPedRecetaDet> lstPedRecetaDet) throws Exception;

    public void setPedidoRecetaPedidoDet(ArrayList<VtaPedRecetaPedidoDet> lstPedRecetaPedidoDet) throws Exception;

    public VtaPedRecetaCabRAC getPedidoRecetaCab(String pCodLocal, String pNumReceta) throws Exception;

    public ArrayList<VtaPedRecetaDetRAC> getPedidoRecetaDet(String pCodLocal, String pNumReceta) throws Exception;

    public ArrayList<VtaPedRecetaPedidoDet> getPedidoRecetaPedidoDet(String pCodLocal, String pNumReceta) throws Exception;

    public CmePaciente getPaciente(String pCodPaciente) throws Exception;

    public CmeHistoriaClinica getHistoriaClinica(String pCodPaciente) throws Exception;

    public void setPaciente(CmePaciente cmePaciente) throws Exception;

    public void setPaciente2(CmePaciente cmePaciente) throws Exception;

    public void setHistoriaClinica(CmeHistoriaClinica cmeHistoriaClinica) throws Exception;

    public ArrayList<HistCompAtencionMedica> getEstCompAtencion(String pCodLocal, String pNumPedVta) throws Exception;

    public void setHCAntecedentes(CmeHCAntecedentesRAC cmeHCAntecedentesRAC) throws Exception;

    public void setHCAntecedentesGineco(CmeHCAntecedentesGinecoRAC cmeHCAntecedentesGinecoRAC) throws Exception;

    public void setHCAntecedentesDetalle(ArrayList<CmeHCAntecedentesDetalleRAC> lstHCAntecedentesDetalleRAC) throws Exception;

    public void setHCAntecedentesPatolo(ArrayList<CmeHCAntecedentesPatoloRAC> lstHCAntecedentesPatoloRAC) throws Exception;

    public void setAtencionMedica(CmeAtencionMedicaRAC cmeAtencionMedicaRAC) throws Exception;

    public void setAtencionMedicaTriaje(CmeAtencionMedicaTriajeRAC cmeAtencionMedicaTriajeRAC) throws Exception;

    public void setAtencionMedicaEnfermedad(CmeAtencionMedicaEnfRAC cmeAtencionMedicaEnfRAC) throws Exception;

    public void setAtencionMedicaExamenFi(CmeAtencionMedicaExFiRAC cmeAtencionMedicaExFiRAC) throws Exception;

    public void setAtencionMedicaDiagnostico(ArrayList<CmeAtencionMedicaDiagnosticoRAC> lstAtencionMedicaDiagnosticoRAC) throws Exception;

    public void setAtencionMedicaTratamiento(CmeAtencionMedicaTratamientoRAC cmeAtencionMedicaTratamientoRAC) throws Exception;

    public void setAtencionMedicaExamenes(CmeAtencionMedicaExamenesRAC cmeAtencionMedicaExamenesRAC) throws Exception;

    public void setAtencionMedicaProcedimientos(CmeAtencionMedicaProcedimientosRAC cmeAtencionMedicaProcedimientosRAC) throws Exception;

    public void setEstCompAtencion(ArrayList<HistCompAtencionMedica> lstCompAtencionMedica) throws Exception;
}
