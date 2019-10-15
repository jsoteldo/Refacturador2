package mifarma.ptoventa.centromedico.dao;

import java.util.ArrayList;
import java.util.Map;

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

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.StatementType;


/**
 * @author ERIOS
 * @since 24.08.2016
 */
public interface MapperVentaAtencionMedica {

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectPedidoCabLocal")
    VtaPedidoAtencionMedica getPedidoCabLocal(Map<String, Object> mapParametros);

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectCompAtencionMedica")
    public VtaCompAtencionMedica getCompAtencion(Map<String, Object> object);

    @Select(value =
            "{call PTOVENTA.PTOVENTA_CME.P_COMP_ATENCION(" +
            "#{cCodGrupoCia_in}," + "#{cCodCia_in}," + "#{cCodLocal_in}," + "#{cNumPedVta_in}," + "#{cIdUsu_in}" + ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void generaCompAtencion(Map<String, Object> object);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "insertPedidoAtencionMedica")
    public void setPedidoAtencion(VtaPedidoAtencionMedica vtaPedidoAtencionMedica);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "insertCompAtencionMedica")
    public void setCompAtencion(VtaCompAtencionMedica vtaCompAtencionMedica);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "insertPedRecetaCab")
    public void setPedidoRecetaCab(VtaPedRecetaCab vtaPedRecetaCab);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "insertPedRecetaDet")
    public void setPedidoRecetaDet(VtaPedRecetaDet vtaPedRecetaDet);
    
    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergePedRecetaPedidoDet")
    public void setRecetaPedidoDet(VtaPedRecetaPedidoDet vtaPedRecetaPedidoDet);

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectPedidoRecetaCab")
    public VtaPedRecetaCabRAC getPedidoRecetaCab(Map<String, Object> object);

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectPedidoRecetaDet")
    public ArrayList<VtaPedRecetaDetRAC> getPedidoRecetaDet(Map<String, Object> object);

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectRecetaPedidoDet")
    public ArrayList<VtaPedRecetaPedidoDet> getPedidoRecetaPedidoDet(Map<String, Object> object);

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectPaciente")
    public CmePaciente getPaciente(Map<String, Object> object);

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectHistoriaClinica")
    public CmeHistoriaClinica getHistoriaClinica(Map<String, Object> object);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergePaciente")
    public void setPaciente(CmePaciente cmePaciente);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergePaciente2")
    public void setPaciente2(CmePaciente cmePaciente);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeHistoriaClinica")
    public void setHistoriaClinica(CmeHistoriaClinica cmeHistoriaClinica);

    @SelectProvider(type = SQLVentaAtencionMedicaProvider.class, method = "selectEstCompAtencion")
    public ArrayList<HistCompAtencionMedica> getEstCompAtencion(Map<String, Object> object);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAntecedentes")
    public void setHCAntecedentes(CmeHCAntecedentesRAC cmeHCAntecedentesRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAntecedentesGineco")
    public void setHCAntecedentesGineco(CmeHCAntecedentesGinecoRAC cmeHCAntecedentesGinecoRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAntecedentesDetalle")
    public void setHCAntecedentesDetalle(CmeHCAntecedentesDetalleRAC cmeHCAntecedentesDetalleRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAntecedentesPatolo")
    public void setHCAntecedentesPatolo(CmeHCAntecedentesPatoloRAC cmeHCAntecedentesPatoloRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedica")
    public void setAtencionMedica(CmeAtencionMedicaRAC cmeAtencionMedicaRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedicaTriaje")
    public void setAtencionMedicaTriaje(CmeAtencionMedicaTriajeRAC cmeAtencionMedicaTriajeRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedicaEnfermedad")
    public void setAtencionMedicaEnfermedad(CmeAtencionMedicaEnfRAC cmeAtencionMedicaEnfRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedicaExamenFi")
    public void setAtencionMedicaExamenFi(CmeAtencionMedicaExFiRAC cmeAtencionMedicaExFiRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedicaDiagnostico")
    public void setAtencionMedicaDiagnostico(CmeAtencionMedicaDiagnosticoRAC cmeAtencionMedicaDiagnosticoRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedicaTratamiento")
    public void setAtencionMedicaTratamiento(CmeAtencionMedicaTratamientoRAC cmeAtencionMedicaTratamientoRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedicaExamenes")
    public void setAtencionMedicaExamenes(CmeAtencionMedicaExamenesRAC cmeAtencionMedicaExamenesRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeAtencionMedicaProcedimientos")
    public void setAtencionMedicaProcedimientos(CmeAtencionMedicaProcedimientosRAC cmeAtencionMedicaProcedimientosRAC);

    @InsertProvider(type = SQLVentaAtencionMedicaProvider.class, method = "mergeEstCompAtencion")
    public void setEstCompAtencion(HistCompAtencionMedica histCompAtencionMedica);
}
