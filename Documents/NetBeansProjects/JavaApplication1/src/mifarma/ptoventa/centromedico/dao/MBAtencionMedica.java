package mifarma.ptoventa.centromedico.dao;

import com.gs.mifarma.componentes.OptionComboBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.administracion.producto.dao.MapperProducto;
import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
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
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesGenerales;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesGinecologicos;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesPatologico;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.fidelizacion.modelo.BeanTipoDocIdentidad;
import mifarma.ptoventa.reference.BeanImpresion;
import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.MyBatisUtil;

import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.apache.ibatis.session.SqlSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MBAtencionMedica implements DAOAtencionMedica {
    
    private static final Logger log = LoggerFactory.getLogger(MBAtencionMedica.class);
    
    private SqlSession sqlSession = null;
    private MapperAtencionMedica mapper = null;
    private UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    
    public MBAtencionMedica() {
        super();
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
        mapper = sqlSession.getMapper(MapperAtencionMedica.class);
    }
    
    public ArrayList<ArrayList<String>> obtenerListaPacientes(String pCodGrupoCia, String pCodEstado, String pCodMedico) throws Exception{
        List<BeanResultado> tmpLista = null;
        ArrayList<ArrayList<String>> tmpArray;
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodEstado_in", pCodEstado);
        mapParametros.put("cCodMedico_in", pCodMedico);
        mapper.obtenerListaEspera(mapParametros);
        tmpLista = (List<BeanResultado>)mapParametros.get("listado");
        tmpArray = (new UtilityPtoVenta()).parsearResultadoMatriz(tmpLista);
        return tmpArray;
    }
    
    public void actualizarSolicitud(String pCodGrupoCia, String pCodLocal, String pNroAtencion, String pCodEstado, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cNumAtencion_in", pNroAtencion);
        mapParametros.put("cCodEstadoNew_in", pCodEstado);
        mapParametros.put("cUsuario_in", pUsuario);
        mapper.actualizarSolicutd(mapParametros);
    }
    
    public ArrayList obtenerListaProducto(String pCodGrupoCia, String pCodLocal)throws Exception{
        ArrayList lista = new ArrayList();
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapper.obtenerListaProducto(mapParametros);
        List<BeanResultado> listaResultado = (List<BeanResultado>)mapParametros.get("listado");
        lista = utilityPtoVenta.parsearResultadoMatriz(listaResultado);
        return lista;
    }

    
    public BeanPaciente buscarPaciente(String pCodGrupoCia, String pCodPaciente)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodPaciente_in", pCodPaciente);
        mapper.buscarPaciente(mapParametros);
        List<BeanPaciente> lst = (List<BeanPaciente>)mapParametros.get("listado");
        if(lst == null || (lst != null && lst.isEmpty()))
            return null;
        else
            return lst.get(0);
    }
    
    public void grabarRecetaCabecera(BeanAtMedTrataReceta beanReceta, String pFechaVigencia, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanReceta.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", beanReceta.getCodLocal());
        mapParametros.put("cNumReceta", beanReceta.getNroPedidoReceta());
        mapParametros.put("cCantItems_in", beanReceta.getCantidadItems());
        mapParametros.put("cFechaVigencia_in", pFechaVigencia);
        mapParametros.put("cUsuCrea_in", pUsuario);
        //2017.05.18 registras los datos del medico en la receta
        mapParametros.put("cCodMedico_in", beanReceta.getCodMedico());
        mapper.grabarRecetaCabecera(mapParametros);
        beanReceta.setNroPedidoReceta((String)mapParametros.get("cNumReceta"));
    }
    
    public void grabarRecetaDetalle(BeanAtMedTrataRecetaDetalle beanRecetaDetalle, String pNumReceta, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
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
        mapper.grabarRecetaDetalle(mapParametros);
    }
    
    public void grabarTratamiento(BeanAtMedTratamiento beanTratamiento, String pNumReceta, String pUsuario) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanTratamiento.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", beanTratamiento.getCodLocal());
        mapParametros.put("cNroAtencion_in", beanTratamiento.getNroAtencionMedica());
        mapParametros.put("cNumPedRec_in", pNumReceta);
        mapParametros.put("cValidezReceta_in", beanTratamiento.getValidezReceta());
        mapParametros.put("cIndicacionesGen_in", beanTratamiento.getIndicacionesGenerales());
        mapParametros.put("cUsuario_in", pUsuario);
        mapper.grabarAtencionMedicaTratamiento(mapParametros);
    }
    
    public void grabarAtencionMedicaEnfermedadActual(BeanAtMedEnfermedadActual beanEnfermedadActual, String pUsuario ) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
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
        mapper.grabarAtencionMedicaEnfermedadActual(mapParametros);
    }
    
    public void grabarAtencionMedicaTriaje(BeanAtMedTriaje beanTriaje, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
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
        mapper.grabarAtencionMedicaTriaje(mapParametros);
    }
    
    public void grabarAtencionMedicaExamenFisico(BeanAtMedExamenFisico beanExaFisico, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanExaFisico.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanExaFisico.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanExaFisico.getNroAtencionMedica() );
        mapParametros.put("cEstadoGeneral_in", beanExaFisico.getEstadoGeneral() );
        mapParametros.put("cEstadoConciencia_in", beanExaFisico.getEstadoConciencia() );
        mapParametros.put("cExaFisicoDirigido_in", beanExaFisico.getExamenFisicoDirigido() );
        mapParametros.put("cUsuCrea_in", pUsuario );
        mapper.grabarAtencionMedicaExaFisico(mapParametros);
    }
    
    public void grabarAtencionMedicaDiagnostico(BeanAtMedDiagnostico beanDiagnostico, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanDiagnostico.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanDiagnostico.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanDiagnostico.getNroAtencionMedica() );
        mapParametros.put("cSecuencial_in", beanDiagnostico.getSecuencial() );
        mapParametros.put("cCodDiagnostico_in", beanDiagnostico.getCodDiagnostico() );
        mapParametros.put("cTipoDiagnostico_in", beanDiagnostico.getTipoDiagnostico() );
        mapParametros.put("cUsuCrea_in", pUsuario );
        mapper.grabarAtencionMedicaDiagnostico(mapParametros);
    }
    
    public void grabarAtencionMedicaExamenAuxiliares(BeanAtMedExamenesAuxiliares beanExaAuxiliares, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", beanExaAuxiliares.getCodGrupoCia() );
        mapParametros.put("cCodLocal_in", beanExaAuxiliares.getCodLocal() );
        mapParametros.put("cNroAtencion_in", beanExaAuxiliares.getNroAtencionMedica() );
        mapParametros.put("cLaboratio_in", beanExaAuxiliares.getLaboratorio() );
        mapParametros.put("cImagenes_in", beanExaAuxiliares.getImagenes() );
        mapParametros.put("cUsuario_in", pUsuario );
        mapper.grabarAtencionMedicaExaAuxiliares(mapParametros);
    }
    
    public void grabarAtencionMedicaOtros(BeanAtMedOtros beanOtros, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
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
        mapper.grabarAtencionMedicaOtros(mapParametros);
    }
    
    public BeanAtencionMedica obtenerAtencionMedica(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", pCodCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cNroAtencion_in", pNroConsulta);
        mapper.obtenerAtencionMedica(mapParametros);
        List<BeanAtencionMedica> lst = (List<BeanAtencionMedica>)mapParametros.get("listado");
        if(lst == null || (lst != null && lst.isEmpty()))
            return null;
        else
            return lst.get(0);
    }
    
    public String obtenerNroAtencionMedica(String pCodGrupoCia, String pCodCia, String pCodLocal, String codPaciente)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", pCodCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("codPaciente", codPaciente);
        mapper.obtenerNroAtencionMedica(mapParametros);
        return mapParametros.get("resultado").toString();
    }
    
    public ArrayList<OptionComboBox> obtenerListaComboCheckBox(int codMaestro)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodMaestro_in", codMaestro);
        mapper.obtenerListaComboCheckBox(mapParametros);
        ArrayList<OptionComboBox> lst = (ArrayList<OptionComboBox>)mapParametros.get("listado");
        return lst;
    }
    
    public BeanHCAntecedentes obtenerAntecedenteHC(String pCodGrupoCia, String pCodLocal, String pCodPaciente, int nroSecuenciaAntecedente)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cCodPaciente_in", pCodPaciente);
        mapParametros.put("cSecuenciaHC_in", nroSecuenciaAntecedente);
        mapper.obtenerAntecedenteHC(mapParametros);
        List<BeanHCAntecedentes> lst = (List<BeanHCAntecedentes>)mapParametros.get("listado");
        if(lst == null || (lst != null && lst.isEmpty()))
            return null;
        else
            return lst.get(0);
    }
    
    public void grabarAntecedenteHCPatologicos(BeanHCAntecedentesPatologico patologico, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", patologico.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", patologico.getCodLocal());
        mapParametros.put("cCodPaciente_in", patologico.getCodPaciente());
        mapParametros.put("cSecuenciaHC_in", patologico.getSecuencialHC());
        mapParametros.put("cCodDiagnostico_in", patologico.getCodDiagnostico());
        mapParametros.put("cIndTipo_in", patologico.getTipoPatologico());
        mapParametros.put("cDescPariente_in", patologico.getDescripcionPariente());
        mapper.grabarAntecedenteHCPatologicos(mapParametros);
    }
    
    public void grabarAntecedenteHCFisiologico(BeanHCAntecedentesFisiologicos fisiologico, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", fisiologico.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", fisiologico.getCodLocal());
        mapParametros.put("cCodPaciente_in", fisiologico.getCodPaciente());
        mapParametros.put("cSecuenciaHC_in", fisiologico.getSecuencialHC());
        mapParametros.put("cCodMaestroDet_in", fisiologico.getCodAnteFisiologico());
        mapParametros.put("cIndTipo_in", fisiologico.getTipoFisiologico());
        mapParametros.put("cDescOtros_in", fisiologico.getDescAnteFisiologico());
        mapper.grabarAntecedenteHCFisiologico(mapParametros);
    }
    
    public void grabarAntecedenteHCGinecologico(BeanHCAntecedentesGinecologicos ginecologico, String pUsuario)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
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
        mapper.grabarAntecedenteHCGinecologico(mapParametros);
    }
    
    public void grabarAntecedenteHC(BeanHCAntecedentes antecedente, String pUsuario) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", antecedente.getCodGrupoCia());
        mapParametros.put("cCodLocal_in", antecedente.getCodLocal());
        mapParametros.put("cCodPaciente_in", antecedente.getCodPaciente());
        mapParametros.put("cSecuenciaHC_in", antecedente.getSecuencialHC());
        mapParametros.put("cMedicamentos_in", antecedente.getGenerales().getMedicamentos());
        mapParametros.put("cRam_in", antecedente.getGenerales().getRam());
        mapParametros.put("cOcupacionales_in", antecedente.getGenerales().getOcupacionales());
        mapParametros.put("cCodMedico_in", antecedente.getCodMedico());
        mapParametros.put("cUsuario_in", pUsuario);
        mapper.grabarAntecedenteHC(mapParametros);
        antecedente.setSecuencialHC(((Integer)mapParametros.get("cSecuenciaHC_in")).intValue());
    }
    
    public ArrayList obtenerProductoReceta(String pCodGrupoCia, String pCodProd)throws Exception{
        ArrayList lista = new ArrayList();
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodProd_in", pCodProd);
        mapper.obtenerProductoReceta(mapParametros);
        List<BeanResultado> listaResultado = (List<BeanResultado>)mapParametros.get("listado");
        lista = utilityPtoVenta.parsearResultadoMatriz(listaResultado);
        return (ArrayList)lista.get(0);
    }
    
    public ArrayList<ArrayList<String>> listarAntecedentesHCPaciente(String pCodGrupoCia, String pCodPaciente, String pFechaInicio, String pFechaFin) throws Exception{
        List<BeanResultado> tmpLista = null;
        ArrayList<ArrayList<String>> tmpArray;
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodPaciente_in", pCodPaciente);
        mapParametros.put("cFechaIni_in", pFechaInicio);
        mapParametros.put("cFechaFin_in", pFechaFin);
        mapper.listarAntecedentesHCPaciente(mapParametros);
        tmpLista = (List<BeanResultado>)mapParametros.get("listado");
        tmpArray = (new UtilityPtoVenta()).parsearResultadoMatriz(tmpLista);
        return tmpArray;
    }
    
    public String getIndicadorImpresionDigitalReceta()throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapper.getIndicadorImpresionRecetaDigital(mapParametros);
        return mapParametros.get("resultado").toString();
    }
    
    public String obtenerNombreImpresoraRecetaDigital(String pCodGrupoCia, String pCodLocal, String pIpPc)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cIpPC_in", pIpPc);
        mapper.obtenerNombreImpresoraRecetaDigital(mapParametros);
        return mapParametros.get("resultado").toString();
    }
    
    public List<BeanImpresion> obtenerFormatoTermicaReceta(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta) throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", pCodCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cNumAtenMedica_in", pNroConsulta);
        mapper.obtenerFormatoRecetaTermico(mapParametros);
        List<BeanImpresion> lstRetorno = (List<BeanImpresion>)mapParametros.get("listado");
        if(lstRetorno.size()>0){
            return lstRetorno;
        }else{
            return null;
        }
    }
    
    public ArrayList<ArrayList<String>> listarAtencionesMedicas(String pCodGrupoCia, String pCodPaciente, String pFechaInicio, String pFechaFin) throws Exception{
        List<BeanResultado> tmpLista = null;
        ArrayList<ArrayList<String>> tmpArray;
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodPaciente_in", pCodPaciente);
        mapParametros.put("cFechaInicio_in", pFechaInicio);
        mapParametros.put("cFechaFin_in", pFechaFin);
        mapper.listarHistoriaAtencionesMedicas(mapParametros);
        tmpLista = (List<BeanResultado>)mapParametros.get("listado");
        tmpArray = (new UtilityPtoVenta()).parsearResultadoMatriz(tmpLista);
        return tmpArray;
    }
    
    public int obtenerCantidadDiasVigenciaReceta()throws Exception{
        int cantidad = 0;
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapper.obtenerCantidaDiasVigenciaReceta(mapParametros);
        cantidad = (Integer)mapParametros.get("resultado");
        return cantidad;
    }

    @Override
    public ArrayList<ArrayList<String>> obtenerListadoCompPacientes(String pCodGrupoCia, String pCodLocal,
                                                                    String pTipo, VtaCompAtencionMedica vtaCAM,
                                                                    VtaPedidoAtencionMedica vtaPAM) {
        return null;
    }
    
    public String validaDatosAccesoConsulta(String tipoColegio, String pNroCMP, String pNroDNI)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cTipoColegio_in", tipoColegio);
        mapParametros.put("cNroCMP", pNroCMP);
        mapParametros.put("cNroDoc", pNroDNI);
        mapper.validarDatosAccesoMedico(mapParametros);
        return mapParametros.get("resultado").toString();
    }
    
    public ArrayList<ArrayList<String>> obtenerListaTipoColegiatura() throws Exception{
        List<BeanResultado> tmpLista = null;
        ArrayList<ArrayList<String>> tmpArray;
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapper.listarTipoColegiatura(mapParametros);
        tmpLista = (List<BeanResultado>)mapParametros.get("listado");
        tmpArray = (new UtilityPtoVenta()).parsearResultadoMatriz(tmpLista);
        return tmpArray;
    }
    
    public void borrarConsultaTemporal(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", pCodCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cNumAtencion_in", pNroConsulta);
        mapper.borrarAtencionTemporal(mapParametros);
    }

    @Override
    public void confirmarTransaccionRAC() {
    }
}
