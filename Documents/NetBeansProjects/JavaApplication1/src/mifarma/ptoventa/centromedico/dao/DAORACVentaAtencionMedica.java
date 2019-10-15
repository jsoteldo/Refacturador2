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
public interface DAORACVentaAtencionMedica extends DAOTransaccion {

    public void savePedidoCabRAC(VtaPedidoAtencionMedica pVtaPedido) throws Exception;

    public void saveCompAtencion(VtaCompAtencionMedica vtaCompAtencionMedica) throws Exception;

    public VtaPedidoAtencionMedica getPedidoAtencion(String pTipComp, String pNumComp) throws Exception;

    public VtaCompAtencionMedica getCompAtencion(String pTipComp, String pNumComp) throws Exception;

    public VtaPedRecetaCab getPedidoRecetaCab(String pCodLocal, String pNumReceta) throws Exception;

    public ArrayList<VtaPedRecetaDet> getPedidoRecetaDet(String pCodLocal, String pNumReceta) throws Exception;

    public ArrayList<VtaPedRecetaPedidoDet> getPedidoRecetaPedidoDet(String pCodLocal, String pNumReceta) throws Exception;

    public void setPedidoRecetaCab(VtaPedRecetaCabRAC vtaPedRecetaCab, ArrayList<VtaPedRecetaDetRAC> lstPedRecetaDet) throws Exception;

    public void setPedidoRecetaDet(ArrayList<VtaPedRecetaDetRAC> lstPedRecetaDet) throws Exception;

    public void setPedidoRecetaPedidoDet(ArrayList<VtaPedRecetaPedidoDet> lstPedRecetaPedidoDet) throws Exception;

    public void savePaciente(CmePaciente cmePaciente) throws Exception;

    public void saveHistoriaClinica(CmeHistoriaClinica cmeHistoriaClinica) throws Exception;

    public CmePaciente getPaciente(String pCodPaciente) throws Exception;

    public CmeHistoriaClinica getHistoriaClinica(String pCodPaciente) throws Exception;

    public void saveEstCompAtencion(ArrayList<HistCompAtencionMedica> lstCompAtencionMedica) throws Exception;

    public CmeHCAntecedentesRAC getHCAntecedentes(String pCodPaciente, int pSecAntec,String pCodLocal) throws Exception;

    public CmeHCAntecedentesGinecoRAC getHCAntecedentesGineco(String pCodPaciente, int pSecAntec,String pCodLocal) throws Exception;

    public ArrayList<CmeHCAntecedentesDetalleRAC> getHCAntecedentesDetalle(String pCodPaciente, int pSecAntec,String pCodLocal) throws Exception;

    public ArrayList<CmeHCAntecedentesPatoloRAC> getHCAntecedentesPatolo(String pCodPaciente, int pSecAntec,String pCodLocal) throws Exception;

    public CmeAtencionMedicaRAC getAtencionMedica(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public CmeAtencionMedicaTriajeRAC getAtencionMedicaTriaje(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public CmeAtencionMedicaEnfRAC getAtencionMedicaEnfermedad(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public CmeAtencionMedicaExFiRAC getAtencionMedicaExamenFi(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public ArrayList<CmeAtencionMedicaDiagnosticoRAC> getAtencionMedicaDiagnostico(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public CmeAtencionMedicaTratamientoRAC getAtencionMedicaTratamiento(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public CmeAtencionMedicaExamenesRAC getAtencionMedicaExamenes(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public CmeAtencionMedicaProcedimientosRAC getAtencionMedicaProcedimientoss(String pCodCia, String pCodLocal, String pNumAtenMed) throws Exception;

    public ArrayList<HistCompAtencionMedica> getEstCompAtencion(String pCodLocal, String pNumPedVta) throws Exception;
}
