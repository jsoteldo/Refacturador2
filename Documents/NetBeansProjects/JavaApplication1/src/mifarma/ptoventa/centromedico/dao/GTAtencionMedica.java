package mifarma.ptoventa.centromedico.dao;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.gs.mifarma.componentes.OptionComboBox;

import com.mifarma.query.builder.ClienteIntegrador;
import com.mifarma.query.builder.QueryBuilder;
import com.mifarma.query.builder.UpdateBuilder;
import com.mifarma.query.request.QueryParams;
import com.mifarma.query.response.QueryStatus;
import com.mifarma.query.response.UpdateStatus;

import java.beans.XMLEncoder;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.BeanPaciente;
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
import mifarma.ptoventa.centromedico.domain.CmeHistoriaClinicaRAC;
import mifarma.ptoventa.centromedico.domain.CmePaciente;
import mifarma.ptoventa.centromedico.domain.CmePacienteRAC;
import mifarma.ptoventa.centromedico.domain.HistCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.HistCompAtencionMedicaRAC;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedicaRAC;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaCab;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaCabRAC;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaDet;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaDetRAC;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaPedidoDet;
import mifarma.ptoventa.centromedico.domain.VtaPedRecetaPedidoDetRAC;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedicaRAC;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedDiagnostico;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedEnfermedadActual;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedExamenFisico;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedExamenesAuxiliares;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedOtros;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTrataReceta;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTrataRecetaDetalle;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTratamiento;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTriaje;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentes;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesFisiologicos;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesGinecologicos;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesPatologico;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.reference.BeanImpresion;
import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.apache.commons.beanutils.BeanMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ERIOS
 * @since 24.08.2016
 */
public class GTAtencionMedica implements DAOAtencionMedica {
    
    private static final Logger log = LoggerFactory.getLogger(GTAtencionMedica.class);
    
    private UpdateBuilder updateBuilder = null;
    private ClienteIntegrador clienteIntegrador = null;
    private UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    
    @Override
    public void commit() {
    }

    @Override
    public void rollback() {
    }

    @Override
    public void openConnection() {
        updateBuilder = new UpdateBuilder()
                                  .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
                                  .requestId("centromedico-merge-rac-ped");
        
        clienteIntegrador = new ClienteIntegrador(VariablesPtoVenta.conexionGTX.getIPBD()+":"+VariablesPtoVenta.conexionGTX.getPORT());
        clienteIntegrador.bypassGateway(); 
    }


    @Override
    public ArrayList<ArrayList<String>> obtenerListaPacientes(String pCodGrupoCia, String pCodEstado,
                                                              String pCodMedico) {
        return null;
    }

    @Override
    public void actualizarSolicitud(String pCodGrupoCia, String pCodLocal, String pNroAtencion, String pCodEstado,
                                    String pUsuario) {
        
        String consulta = "call PTOVENTA.CENTRO_MEDICO.F_UPDATE_SOLICITUD_ATENCION(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNumAtencion_in," + 
                          ":cCodEstadoNew_in," +
                          ":cUsuario_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cNumAtencion_in", pNroAtencion);
        mapParametros.put("cCodEstadoNew_in", pCodEstado);
        mapParametros.put("cUsuario_in", pUsuario);
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");
        
        

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError();*/
    }

    @Override
    public ArrayList obtenerListaProducto(String pCodGrupoCia, String pCodLocal) {
        return null;
    }

    @Override
    public BeanPaciente buscarPaciente(String pCodGrupoCia, String pCodPaciente) {
        return null;
    }

    @Override
    public void grabarRecetaCabecera(BeanAtMedTrataReceta beanReceta, String pFechaVigencia, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABA_RECETA_CABECERA(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNumReceta," + 
                          ":cCantItems_in," +
                          ":cFechaVigencia_in," +
                          ":cUsuCrea_in," +
                          ":cCodMedico_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanReceta.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", beanReceta.getCodLocal());
        mapParametros.put("cNumReceta", beanReceta.getNroPedidoReceta());
        mapParametros.put("cCantItems_in", beanReceta.getCantidadItems());
        mapParametros.put("cFechaVigencia_in", pFechaVigencia);
        mapParametros.put("cUsuCrea_in", pUsuario);
        mapParametros.put("cCodMedico_in", beanReceta.getCodMedico());
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError();*/
    }

    @Override
    public void grabarRecetaDetalle(BeanAtMedTrataRecetaDetalle beanRecetaDetalle, String pNumReceta,
                                    String pUsuario) {
        
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABA_RECETA_DETALLE(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNumPedRec_in," + 
                          ":cSecuencia_in," +
                          ":cCodProd_in," +
                          ":cCantidad_in," +
                          ":cValFrac_in," +
                          ":cUnidVta_in," +
                          ":cFrecuencia_in," +
                          ":cDuracion_in," +
                          ":cViaAdministracion_in," +
                          ":cDosis_in," +
                          ":cUsuario_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanRecetaDetalle.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", beanRecetaDetalle.getCodLocal());
        mapParametros.put("cNumPedRec_in", pNumReceta);
        mapParametros.put("cSecuencia_in", beanRecetaDetalle.getSecuencialDetalle());
        mapParametros.put("cCodProd_in", beanRecetaDetalle.getCodProducto());
        mapParametros.put("cCantidad_in", beanRecetaDetalle.getCantidadToma());
        mapParametros.put("cValFrac_in", beanRecetaDetalle.getValFraccionamiento());
        mapParametros.put("cUnidVta_in", beanRecetaDetalle.getUnidadVenta());
        mapParametros.put("cFrecuencia_in", beanRecetaDetalle.getFrecuenciaToma());
        mapParametros.put("cDuracion_in", beanRecetaDetalle.getDuracionToma());
        mapParametros.put("cViaAdministracion_in", beanRecetaDetalle.getCodViaAdministracion());
        mapParametros.put("cDosis_in", beanRecetaDetalle.getDosis());
        mapParametros.put("cUsuario_in", pUsuario);
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError();*/
    }

    @Override
    public void grabarTratamiento(BeanAtMedTratamiento beanTratamiento, String pNumReceta, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_TRATAMIENTO(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNroAtencion_in," + 
                          ":cNumPedRec_in," +
                          ":cValidezReceta_in," +
                          ":cIndicacionesGen_in," +
                          ":cUsuario_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanTratamiento.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", beanTratamiento.getCodLocal());
        mapParametros.put("cNroAtencion_in", beanTratamiento.getNroAtencionMedica());
        mapParametros.put("cNumPedRec_in", pNumReceta);
        mapParametros.put("cValidezReceta_in", beanTratamiento.getValidezReceta());
        mapParametros.put("cIndicacionesGen_in", beanTratamiento.getIndicacionesGenerales());
        mapParametros.put("cUsuario_in", pUsuario);
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError();*/
    }

    @Override
    public void grabarAtencionMedicaTriaje(BeanAtMedTriaje beanTriaje, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_TRIAJE(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNroAtencion_in," + 
                          ":cPA_1_in," +
                          ":cPA_2_in," +
                          ":cFR_in," +
                          ":cFC_in," +
                          ":cTemp_in," +
                          ":cPeso_in," +
                          ":cTalla_in," +
                          ":cUsuCrea_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanTriaje.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanTriaje.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanTriaje.getNroAtencionMedica() );
        mapParametros.put("cPA_1_in", beanTriaje.getFuncionVitalPA1());
        mapParametros.put("cPA_2_in", beanTriaje.getFuncionVitalPA2());
        mapParametros.put("cFR_in", beanTriaje.getFuncionVitalFR());
        mapParametros.put("cFC_in", beanTriaje.getFuncionVitalFC());
        mapParametros.put("cTemp_in", beanTriaje.getFuncionVitalT());
        mapParametros.put("cPeso_in", beanTriaje.getFuncionVitalPeso());
        mapParametros.put("cTalla_in", beanTriaje.getFuncionvitalTalla());
        mapParametros.put("cUsuCrea_in", pUsuario);
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");
    }

    @Override
    public void grabarAtencionMedicaEnfermedadActual(BeanAtMedEnfermedadActual beanEnfermedadActual, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_ENFER_ACTUAL(" +
                          ":cCodGrupoCia_in, "+
                          ":cCodLocal_in," + 
                          ":cNroAtencion_in," + 
                          ":cMotivoConsulta_in," +
                          ":cTipoInformante_in," +
                          ":cTiempoEnfermedad_in," +
                          ":cFormaInicio_in," +
                          ":cSignos_in," +
                          ":cSintomas_in," +
                          ":cRelatoCronologico_in," +
                          ":cTipoApetito_in," +
                          ":cTipoSed_in," +
                          ":cTipoSueno_in," +
                          ":cTipoOrina_in," +
                          ":cTipoDeposicion_in," +
                          ":cUsuCrea_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanEnfermedadActual.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanEnfermedadActual.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanEnfermedadActual.getNroAtencionMedica() );
        mapParametros.put("cMotivoConsulta_in", beanEnfermedadActual.getMotivoConsulta() );
        mapParametros.put("cTipoInformante_in", beanEnfermedadActual.getTipoInformante() );
        mapParametros.put("cTiempoEnfermedad_in", beanEnfermedadActual.getTiempoEnfermedad() );
        mapParametros.put("cFormaInicio_in", beanEnfermedadActual.getFormaInicio() );
        mapParametros.put("cSignos_in", beanEnfermedadActual.getSignos() );
        mapParametros.put("cSintomas_in", beanEnfermedadActual.getSintomas() );
        mapParametros.put("cRelatoCronologico_in", beanEnfermedadActual.getRelatoCronologico() );
        mapParametros.put("cTipoApetito_in", beanEnfermedadActual.getTipoApetito() );
        mapParametros.put("cTipoSed_in", beanEnfermedadActual.getTipoSed() );
        mapParametros.put("cTipoSueno_in", beanEnfermedadActual.getTipoSuenio() );
        mapParametros.put("cTipoOrina_in", beanEnfermedadActual.getTipoOrina() );
        mapParametros.put("cTipoDeposicion_in", beanEnfermedadActual.getTipoDeposicion() );
        mapParametros.put("cUsuCrea_in", pUsuario );
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

    }

    @Override
    public void grabarAtencionMedicaExamenFisico(BeanAtMedExamenFisico beanExaFisico, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_EXAMEN_FISICO(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNroAtencion_in," + 
                          ":cEstadoGeneral_in," +
                          ":cEstadoConciencia_in," +
                          ":cExaFisicoDirigido_in," +
                          ":cUsuCrea_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanExaFisico.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanExaFisico.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanExaFisico.getNroAtencionMedica() );
        mapParametros.put("cEstadoGeneral_in", beanExaFisico.getEstadoGeneral() );
        mapParametros.put("cEstadoConciencia_in", beanExaFisico.getEstadoConciencia() );
        mapParametros.put("cExaFisicoDirigido_in", beanExaFisico.getExamenFisicoDirigido() );
        mapParametros.put("cUsuCrea_in", pUsuario );
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
    }

    @Override
    public void grabarAtencionMedicaDiagnostico(BeanAtMedDiagnostico beanDiagnostico, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_DIAGNOSTICO(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNroAtencion_in," + 
                          ":cSecuencial_in," +
                          ":cCodDiagnostico_in," +
                          ":cTipoDiagnostico_in," +
                          ":cUsuCrea_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanDiagnostico.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanDiagnostico.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanDiagnostico.getNroAtencionMedica() );
        mapParametros.put("cSecuencial_in", beanDiagnostico.getSecuencial() );
        mapParametros.put("cCodDiagnostico_in", beanDiagnostico.getCodDiagnostico() );
        mapParametros.put("cTipoDiagnostico_in", beanDiagnostico.getTipoDiagnostico() );
        mapParametros.put("cUsuCrea_in", pUsuario );
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
    }

    @Override
    public void grabarAtencionMedicaExamenAuxiliares(BeanAtMedExamenesAuxiliares beanExaAuxiliares, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_EXAM_AUXILIA(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNroAtencion_in," + 
                          ":cLaboratio_in," +
                          ":cImagenes_in," +
                          ":cUsuario_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanExaAuxiliares.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanExaAuxiliares.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanExaAuxiliares.getNroAtencionMedica() );
        mapParametros.put("cLaboratio_in", beanExaAuxiliares.getLaboratorio() );
        mapParametros.put("cImagenes_in", beanExaAuxiliares.getImagenes() );
        mapParametros.put("cUsuario_in", pUsuario );
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
    }

    @Override
    public void grabarAtencionMedicaOtros(BeanAtMedOtros beanOtros, String pUsuario) {
        
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_OTROS(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cNroAtencion_in," + 
                          ":cProcedimiento_in," +
                          ":cInterconsulta_in," +
                          ":cTransferencia_in," +
                          ":cDescansoFisicoInicio_in," +
                          ":cDescansoFisicoFin_in," +
                          ":cProximaCita_in," +
                          ":cUsuario_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanOtros.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanOtros.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanOtros.getNroAtencionMedica() );
        mapParametros.put("cProcedimiento_in", beanOtros.getProcedimiento() );
        mapParametros.put("cInterconsulta_in", beanOtros.getInterconsulta() );
        mapParametros.put("cTransferencia_in", beanOtros.getTransferencia() );
        mapParametros.put("cDescansoFisicoInicio_in", beanOtros.getDescansoMedicoInicio() );
        mapParametros.put("cDescansoFisicoFin_in", beanOtros.getDescansoMedicoFin() );
        mapParametros.put("cProximaCita_in", beanOtros.getProximaCita() );
        mapParametros.put("cUsuario_in", pUsuario );
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
    }

    @Override
    public BeanAtencionMedica obtenerAtencionMedica(String pCodGrupoCia, String pCodCia, String pCodLocal,
                                                    String pNroConsulta) {
        return null;
    }
    
    @Override
    public String obtenerNroAtencionMedica(String pCodGrupoCia, String pCodCia, String pCodLocal,
                                                    String codPaciente) {
        return null;
    }

    @Override
    public ArrayList<OptionComboBox> obtenerListaComboCheckBox(int codMaestro) {
        return null;
    }

    @Override
    public BeanHCAntecedentes obtenerAntecedenteHC(String pCodGrupoCia, String pCodLocal, String pCodPaciente,
                                                   int nroSecuenciaAntecedente) {
        return null;
    }

    @Override
    public void grabarAntecedenteHCPatologicos(BeanHCAntecedentesPatologico patologico, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTE_HC_PATOLOGICO(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cCodPaciente_in," + 
                          ":cSecuenciaHC_in," +
                          ":cCodDiagnostico_in," +
                          ":cIndTipo_in," +
                          ":cDescPariente_in" +
                          ")"; 
        
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", patologico.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", patologico.getCodLocal());
        mapParametros.put("cCodPaciente_in", patologico.getCodPaciente());
        mapParametros.put("cSecuenciaHC_in", patologico.getSecuencialHC());
        mapParametros.put("cCodDiagnostico_in", patologico.getCodDiagnostico());
        mapParametros.put("cIndTipo_in", patologico.getTipoPatologico());
        mapParametros.put("cDescPariente_in", patologico.getDescripcionPariente());
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
    }

    @Override
    public void grabarAntecedenteHCFisiologico(BeanHCAntecedentesFisiologicos fisiologico, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTE_HC_FISIOLOGICO(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cCodPaciente_in," + 
                          ":cSecuenciaHC_in," +
                          ":cCodMaestroDet_in," +
                          ":cIndTipo_in," +
                          ":cDescOtros_in" +
                          ")"; 
        
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", fisiologico.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", fisiologico.getCodLocal());
        mapParametros.put("cCodPaciente_in", fisiologico.getCodPaciente());
        mapParametros.put("cSecuenciaHC_in", fisiologico.getSecuencialHC());
        mapParametros.put("cCodMaestroDet_in", fisiologico.getCodAnteFisiologico());
        mapParametros.put("cIndTipo_in", fisiologico.getTipoFisiologico());
        mapParametros.put("cDescOtros_in", fisiologico.getDescAnteFisiologico());
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
    }

    @Override
    public void grabarAntecedenteHCGinecologico(BeanHCAntecedentesGinecologicos ginecologico, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTE_HC_GINECOLOGICO(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cCodPaciente_in," + 
                          ":cSecuenciaHC_in," +
                          ":cEdadMenarquia_in," +
                          ":cRcMenstruacion_in," +
                          ":cCicloMenstruacion_in," +
                          ":cFechaFUR_in," +
                          ":cFechaFPP_in," +
                          ":cRS_in," +
                          ":cDisminorrea_in," +
                          ":cNroGestaciones_in," +
                          ":cParidad_in," +
                          ":cFechaFUP_in," +
                          ":cNroCesareas_in," +
                          ":cPAP_in," +
                          ":cMamografia_in," +
                          ":cMac_in," +
                          ":cOtros_in," +
                          ":cIndReglaRegular_in" +
                          ")"; 
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", ginecologico.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", ginecologico.getCodLocal());
        mapParametros.put("cCodPaciente_in", ginecologico.getCodPaciente());
        mapParametros.put("cSecuenciaHC_in", ginecologico.getSecuencialHC());
        mapParametros.put("cEdadMenarquia_in", ginecologico.getEdadMenarquia());
        mapParametros.put("cRcMenstruacion_in", ginecologico.getRcMenstruacion());
        mapParametros.put("cCicloMenstruacion_in", ginecologico.getRcCicloMenstrual());
        mapParametros.put("cFechaFUR_in", ginecologico.getFur());
        mapParametros.put("cFechaFPP_in", ginecologico.getFpp());
        mapParametros.put("cRS_in", ginecologico.getRs());
        mapParametros.put("cDisminorrea_in", ginecologico.getDisminorrea());
        mapParametros.put("cNroGestaciones_in", ginecologico.getNroGestaciones());
        mapParametros.put("cParidad_in", ginecologico.getParidad());
        mapParametros.put("cFechaFUP_in", ginecologico.getFup());
        mapParametros.put("cNroCesareas_in", ginecologico.getNroCesareas());
        mapParametros.put("cPAP_in", ginecologico.getPap());
        mapParametros.put("cMamografia_in", ginecologico.getMamografia());
        mapParametros.put("cMac_in", ginecologico.getMac());
        mapParametros.put("cOtros_in", ginecologico.getOtros());
        mapParametros.put("cIndReglaRegular_in", ginecologico.getIndReglaRegular());
        
        updateBuilder.addUpdateStatement(consulta, mapParametros ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
    }

    @Override
    public void grabarAntecedenteHC(BeanHCAntecedentes antecedente, String pUsuario) {
        String consulta = "call PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTECEDENTES_HC(" +
                          ":cCodGrupoCia_in, " +
                          ":cCodLocal_in," + 
                          ":cCodPaciente_in," + 
                          ":cSecuenciaHC_in," +
                          ":cMedicamentos_in," +
                          ":cRam_in," +
                          ":cOcupacionales_in," +
                          ":cCodMedico_in," +
                          ":cUsuario_in" +
                          ")"; 

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cCodGrupoCia_in", antecedente.getCodGrupoCia());
        paramsMap.put("cCodLocal_in", antecedente.getCodLocal());
        paramsMap.put("cCodPaciente_in", antecedente.getCodPaciente());
        paramsMap.put("cSecuenciaHC_in", antecedente.getSecuencialHC());
        paramsMap.put("cMedicamentos_in", antecedente.getGenerales().getMedicamentos());
        paramsMap.put("cRam_in", antecedente.getGenerales().getRam());
        paramsMap.put("cOcupacionales_in", antecedente.getGenerales().getOcupacionales());
        paramsMap.put("cCodMedico_in", antecedente.getCodMedico());
        paramsMap.put("cUsuario_in", pUsuario);

        updateBuilder.addUpdateStatement(consulta, paramsMap ,"CME");

        /*UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError(); */
        
    }

    @Override
    public ArrayList obtenerProductoReceta(String pCodGrupoCia, String pCodProd) {
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> listarAntecedentesHCPaciente(String pCodGrupoCia, String pCodPaciente,
                                                                     String pFechaInicio, String pFechaFin) {
        ArrayList<ArrayList<String>> tmpArray;
        List<BeanResultado> tmpLista = null;
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.CENTRO_MEDICO.F_LISTA_HIST_ANTECEDENTES(?,?,?,?)}",  
                         pCodGrupoCia,
                         pCodPaciente,
                         pFechaInicio,
                         pFechaFin)
            .getParams();
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        tmpLista = utilityPtoVenta.retornaBean(resultList);
        tmpArray = utilityPtoVenta.parsearResultadoMatriz(tmpLista);
        
        return tmpArray;
    }

    @Override
    public String getIndicadorImpresionDigitalReceta() {
        return null;
    }

    @Override
    public String obtenerNombreImpresoraRecetaDigital(String pCodGrupoCia, String pCodLocal, String pIpPc) {
        return null;
    }

    @Override
    public List<BeanImpresion> obtenerFormatoTermicaReceta(String pCodGrupoCia, String pCodCia, String pCodLocal,
                                                           String pNroConsulta) {
        return Collections.emptyList();
    }

    @Override
    public ArrayList<ArrayList<String>> listarAtencionesMedicas(String pCodGrupoCia, String pCodPaciente,
                                                                String pFechaInicio, String pFechaFin) {
        ArrayList<ArrayList<String>> tmpArray;
        List<BeanResultado> tmpLista = null;
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.CENTRO_MEDICO.F_LISTA_HIST_ATEN_MEDICA(?,?,?,?)}",  
                         pCodGrupoCia,
                         pCodPaciente,
                         pFechaInicio,
                         pFechaFin)
            .getParams();
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        tmpLista = utilityPtoVenta.retornaBean(resultList);
        tmpArray = utilityPtoVenta.parsearResultadoMatriz(tmpLista);
        
        return tmpArray;
    }

    @Override
    public int obtenerCantidadDiasVigenciaReceta() {
        return 0;
    }
    
    @Override
    public ArrayList<ArrayList<String>> obtenerListadoCompPacientes(String pCodGrupoCia, 
                                                                    String pCodLocal, 
                                                                    String pTipo,
                                                                    VtaCompAtencionMedica vtaCAM,
                                                                    VtaPedidoAtencionMedica vtaPAM) throws Exception{
        
        ArrayList<ArrayList<String>> tmpArray;
        List<BeanResultado> tmpLista = null;
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.ptoventa_matriz_cme.cme_lista_pacientes(?,?,?,?,?,?,?,?,?,?)}",  
                         pCodGrupoCia,
                         pCodLocal,
                         pTipo,
                         vtaCAM.getTip_comp_pago(),
                         vtaCAM.getNum_comp_pago(),
                         vtaPAM.getTip_documento(),
                         vtaPAM.getNum_documento(),
                         vtaPAM.getNombre(),
                         vtaPAM.getApe_pat(),
                         vtaPAM.getApe_mat())
            .getParams();
        /*
        System.out.println(pCodGrupoCia);
        System.out.println(pCodLocal);
        System.out.println(pTipo);
        System.out.println(vtaCAM.getTip_comp_pago());
        System.out.println(vtaCAM.getNum_comp_pago());
        System.out.println(vtaPAM.getTip_documento());
        System.out.println(vtaPAM.getNum_documento());
        System.out.println(vtaPAM.getNombre());
        System.out.println(vtaPAM.getApe_pat());
        System.out.println(vtaPAM.getApe_mat());        
        */
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        tmpLista = utilityPtoVenta.retornaBean(resultList);
        tmpArray = utilityPtoVenta.parsearResultadoMatriz(tmpLista);
        
        return tmpArray;
    }

    @Override
    public String validaDatosAccesoConsulta(String tipoColegio, String pNroCMP, String pNroDNI) {
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> obtenerListaTipoColegiatura() {
        return null;
    }

    @Override
    public void borrarConsultaTemporal(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta) {
    }
    
    public void confirmarTransaccionRAC(){
        log.info("paramts "+updateBuilder.getParams());
        UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());  
        status.exceptionOnError();
    }


}
