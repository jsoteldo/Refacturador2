package mifarma.ptoventa.centromedico.dao;


import com.fasterxml.jackson.databind.ObjectMapper;

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
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.apache.commons.beanutils.BeanMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ERIOS
 * @since 24.08.2016
 */
public class GTRACVentaAtencionMedica implements DAORACVentaAtencionMedica {
    
    private static final Logger log = LoggerFactory.getLogger(GTRACVentaAtencionMedica.class);
    
    private UpdateBuilder updateBuilder = null;
    private ClienteIntegrador clienteIntegrador = null;
    private UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    
    @Override
    public void savePedidoCabRAC(VtaPedidoAtencionMedica pVtaPedido) throws Exception{
        
        String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_PEDIDO_ATENC_MED(:cod_grupo_cia," +
            ":cod_local," +
            ":num_ped_vta," +
            ":tip_documento," +
            ":num_documento," +
            ":nombre," +
            ":ape_pat," +
            ":ape_mat," +
            ":usu_crea," +
            ":estado," +
            ":fec_crea," +
            ":fec_mod," +
            ":usu_mod)}";
        Map<Object, Object> esDetasMap = new BeanMap(pVtaPedido);
        updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
        
        //UpdateStatus status = clienteIntegrador.updateOnline(updateBuilder.getParams());
        //status.exceptionOnError();
        
    }

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
    public void saveCompAtencion(VtaCompAtencionMedica vtaCompAtencionMedica) throws Exception{
        
        String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_COMP_ATENC_MED(:cod_grupo_cia," +
            ":cod_local," +
            ":num_ped_vta," +
            ":tip_comp_pago," +
            ":num_comp_pago," +
            ":estado," +
            ":fec_crea," +
            ":usu_crea," +
            ":fec_mod," +
            ":usu_mod)}";
        Map<Object, Object> esDetasMap = new BeanMap(vtaCompAtencionMedica);
        updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
        
        UpdateStatus status = clienteIntegrador.updateOnline(updateBuilder.getParams());
        status.exceptionOnError();
    }

    @Override
    public VtaPedidoAtencionMedica getPedidoAtencion(String pTipComp, String pNumComp) {
        VtaPedidoAtencionMedica bean = new VtaPedidoAtencionMedica();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_PEDIDO_ATENC_MED(?,?,?)}", FarmaVariables.vCodGrupoCia, pTipComp, pNumComp)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        Map<String, Object> first = resultList.get(0);
        
        ObjectMapper m = new ObjectMapper();
        bean = m.convertValue(first, VtaPedidoAtencionMedicaRAC.class);
        
        return bean;
    }

    @Override
    public VtaCompAtencionMedica getCompAtencion(String pTipComp, String pNumComp) {
        VtaCompAtencionMedica bean = new VtaCompAtencionMedica();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_COMP_ATENC_MED(?,?,?)}", FarmaVariables.vCodGrupoCia, pTipComp, pNumComp)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        Map<String, Object> first = resultList.get(0);
        
        ObjectMapper m = new ObjectMapper();
        bean = m.convertValue(first, VtaCompAtencionMedicaRAC.class);
        
        return bean;
    }

    @Override
    public VtaPedRecetaCab getPedidoRecetaCab(String pCodLocal, String pNumReceta) {
        VtaPedRecetaCab bean = new VtaPedRecetaCab();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_PED_RECETA_CAB(?,?,?)}", FarmaVariables.vCodGrupoCia, pCodLocal, pNumReceta)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        Map<String, Object> first = resultList.get(0);
        
        ObjectMapper m = new ObjectMapper();
        bean = m.convertValue(first, VtaPedRecetaCabRAC.class);
        
        return bean;
    }

    @Override
    public ArrayList<VtaPedRecetaDet> getPedidoRecetaDet(String pCodLocal, String pNumReceta) {
        ArrayList<VtaPedRecetaDet> lst = new ArrayList<>();
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_PED_RECETA_DET(?,?,?)}", FarmaVariables.vCodGrupoCia, pCodLocal, pNumReceta)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        ObjectMapper m = new ObjectMapper();
        
        for(Map<String, Object> first:resultList){            
            VtaPedRecetaDet bean = new VtaPedRecetaDet();
            bean = m.convertValue(first, VtaPedRecetaDetRAC.class);
            lst.add(bean);
        }
        
        return lst;
    }

    @Override
    public ArrayList<VtaPedRecetaPedidoDet> getPedidoRecetaPedidoDet(String pCodLocal, String pNumReceta) {
        ArrayList<VtaPedRecetaPedidoDet> lst = new ArrayList<>();
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_RECETA_PEDIDO_DET(?,?,?)}", FarmaVariables.vCodGrupoCia, pCodLocal, pNumReceta)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() > 0) {
           
            ObjectMapper m = new ObjectMapper();
            
            for(Map<String, Object> first:resultList){            
                VtaPedRecetaPedidoDet bean = new VtaPedRecetaPedidoDet();
                bean = m.convertValue(first, VtaPedRecetaPedidoDetRAC.class);
                lst.add(bean);
            }
        }
        return lst;
    }

    @Override
    public void setPedidoRecetaCab(VtaPedRecetaCabRAC vtaPedRecetaCab, ArrayList<VtaPedRecetaDetRAC> lstPedRecetaDet) throws Exception {
        
        String xmlInString = "";
        if(lstPedRecetaDet != null){
            /*ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(lstPedRecetaDet);
            log.debug(jsonInString);*/
            
            vtaPedRecetaCab.setList(lstPedRecetaDet);
            xmlInString = jaxbObjectToXML(vtaPedRecetaCab,false);
            log.debug(xmlInString);            
        }
            
        String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_RECETA_CAB(:cod_grupo_cia," +
            ":cod_local," +
            ":num_ped_rec," +
            ":cdg_apm," +
            ":matricula," +
            ":fec_ped_rec," +
            ":val_bruto_ped_rec," +
            ":val_neto_ped_rec," +
            ":val_redondeo_ped_rec," +
            ":val_igv_ped_rec," +
            ":val_dcto_ped_rec," +
            ":val_tip_cambio_ped_rec," +
            ":cant_items_ped_rec," +
            ":est_ped_rec," +
            ":usu_crea_ped_rec_cab," +
            ":fec_crea_ped_rec_cab," +
            ":usu_mod_ped_rec_cab," +
            ":fec_mod_ped_rec_cab," +
            ":fecha_vencimiento," +
            ":dets" +
            ")}";
        
        Map<Object, Object> esDetasMap = new BeanMap(vtaPedRecetaCab);
        esDetasMap.put("dets", xmlInString);
        updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
    }

    @Override
    public void setPedidoRecetaDet(ArrayList<VtaPedRecetaDetRAC> lstPedRecetaDet) throws Exception {
        
        /*String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_RECETA_DET(:cod_grupo_cia," +
            ":cod_local," +
            ":num_ped_rec," +
            ":sec_ped_rec_det," +
            ":cod_prod," +
            ":cant_atendida," +
            ":val_prec_rec," +
            ":val_prec_total," +
            ":porc_dcto_1," +
            ":porc_dcto_2," +
            ":porc_dcto_3," +
            ":porc_dcto_total," +
            ":est_ped_rec_det," +
            ":val_frac," +
            ":usu_crea_ped_rec_det," +
            ":fec_crea_ped_rec_det," +
            ":usu_mod_ped_rec_det," +
            ":fec_mod_ped_rec_det," +
            ":val_prec_lista," +
            ":val_igv," +
            ":unid_vta," +
            ":ind_exonerado_igv," +
            ":stk_fisico_disp)}";
        for(VtaPedRecetaDet vtaPedRecetaDet:lstPedRecetaDet){
            Map<Object, Object> esDetasMap = new BeanMap(vtaPedRecetaDet);
            updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
        }*/
        
        UpdateStatus status = clienteIntegrador.updateOnline(updateBuilder.getParams());
        status.exceptionOnError();
    }

    @Override
    public void setPedidoRecetaPedidoDet(ArrayList<VtaPedRecetaPedidoDet> lstPedRecetaPedidoDet) {
        String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_RECETA_PEDIDO_DET(:cod_grupo_cia," +
            ":cod_local," +
            ":num_ped_rec," +
            ":cod_local_vta," +
            ":num_ped_vta," +
            ":sec_ped_vta_det," +
            ":cod_prod," +
            ":cant_atendida," +
            ":val_frac," +
            ":estado," +
            ":fec_crea," +
            ":usu_crea," +
            ":fec_mod," +
            ":usu_mod," +
            ":unid_vta)}";
        for(VtaPedRecetaPedidoDet vtaPedRecetaPedidoDet:lstPedRecetaPedidoDet){
            Map<Object, Object> esDetasMap = new BeanMap(vtaPedRecetaPedidoDet);
            updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
        }
        
        UpdateStatus status = clienteIntegrador.updateOnline(updateBuilder.getParams());
        status.exceptionOnError();
    }

    @Override
    public void savePaciente(CmePaciente cmePaciente) {
        String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_PACIENTE(:cod_grupo_cia," +
            ":cod_paciente," +            
            ":nom_cli," +
            ":ape_pat_cli," +
            ":ape_mat_cli," +
            ":fono_cli," +
            ":sexo_cli," +
            ":dir_cli," +
            ":fec_nac_cli," +
            ":fec_crea_cliente," +
            ":usu_crea_cliente," +
            ":fec_mod_cliente," +
            ":usu_mod_cliente," +
            ":ind_estado," +
            ":email," +
            ":cell_cli," +
            ":cod_tip_documento," +
            ":num_documento," +
            ":id_usu_confir," +
            ":cod_local_confir," +
            ":ip_confir," +
            ":departamento," +
            ":provincia," +
            ":distrito," +
            ":tipo_direccion," +
            ":tipo_lugar," +
            ":referencias," +
            ":estado_civil," +
            ":grado_instruccion," +
            ":ocupacion," +
            ":centro_edu_lab," +
            ":sec_antecedente_hc," +
            ":cod_local_antecedente_hc," +
            ":dep_ubigeo," +
            ":prv_ubigeo," +
            ":dis_ubigeo," +
            ":dep_lug_nac," +
            ":prv_lug_nac," +
            ":dis_lug_nac," +
            ":dep_lug_pro," +
            ":prv_lug_pro," +
            ":dis_lug_pro"+
            ")}";
        Map<Object, Object> esDetasMap = new BeanMap(cmePaciente);
        updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
    }

    @Override
    public void saveHistoriaClinica(CmeHistoriaClinica cmeHistoriaClinica) {        
        String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_HISTORIA_CLINICA(:cod_grupo_cia," +
            ":cod_paciente," +
            ":grupo_sang," +
            ":factor_rh," +
            ":estado," +
            ":fec_crea," +
            ":usu_crea," +
            ":fec_mod," +
            ":usu_mod," +
            ":fecha_hc," +
            ":ind_hc_fisica," +
            ":nro_hc_fisica," +
            ":nom_acom," +
            ":cod_tip_doc_acom," +
            ":num_doc_acom," +
            ":cod_tip_acom," +
            ":nro_hc_actual" +
            ")}";
        Map<Object, Object> esDetasMap = new BeanMap(cmeHistoriaClinica);
        updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
        
        UpdateStatus status = clienteIntegrador.updateOnline(updateBuilder.getParams());
        status.exceptionOnError();
    }

    @Override
    public CmePaciente getPaciente(String pCodPaciente) {
        CmePaciente bean = new CmePaciente();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_PACIENTE(?,?)}", FarmaVariables.vCodGrupoCia, pCodPaciente)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        Map<String, Object> first = resultList.get(0);
        
        ObjectMapper m = new ObjectMapper();
        bean = m.convertValue(first, CmePacienteRAC.class);
        
        return bean;
    }

    @Override
    public CmeHistoriaClinica getHistoriaClinica(String pCodPaciente) {
        CmeHistoriaClinica bean = new CmeHistoriaClinica();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_HISTORIA_CLINICA(?,?)}", FarmaVariables.vCodGrupoCia, pCodPaciente)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        Map<String, Object> first = resultList.get(0);
        
        ObjectMapper m = new ObjectMapper();
        bean = m.convertValue(first, CmeHistoriaClinicaRAC.class);
        
        return bean;
    }

    @Override
    public void saveEstCompAtencion(ArrayList<HistCompAtencionMedica> lstCompAtencionMedica) {
        String detalleSql = "{ call PTOVENTA.PTOVENTA_MATRIZ_CME.P_GRABA_ESTADO_ATENCION(" +
            ":cod_grupo_cia," +
            ":cod_local," +
            ":num_ped_vta," +
            ":sec_hist_med," +
            ":fec_hist_med," + 
            ":cod_cia," +
            ":cod_local_cme," +
            ":num_aten_med," +            
            ":estado," +
            ":fec_crea," +
            ":usu_crea," +
            ":fec_mod," +
            ":usu_mod" +
            ")}";
        for(HistCompAtencionMedica histCompAtencionMedica:lstCompAtencionMedica){
            Map<Object, Object> esDetasMap = new BeanMap(histCompAtencionMedica);
            updateBuilder.addUpdateStatement(detalleSql, esDetasMap);
        }
        
        log.info("no pe");
        
        if(clienteIntegrador==null){
            log.info("es null");
        }
        
        if(updateBuilder.getParams()==null){
            log.info("es null getParams");
        }
        
        UpdateStatus status = clienteIntegrador.updateOnline(updateBuilder.getParams());
        //UpdateStatus status = clienteIntegrador.updateRetry(updateBuilder.getParams());
        status.exceptionOnError();
    }
    
    public static String printObjectToXML(final Object object) throws TransformerFactoryConfigurationError,
            TransformerConfigurationException, SOAPException, TransformerException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(baos);
        xmlEncoder.writeObject(object);
        xmlEncoder.close();

        String xml = baos.toString();
        //System.out.println(xml);

        return xml.toString();
    }
    
    private static String jaxbObjectToXML(VtaPedRecetaCabRAC customer, boolean pretty) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(VtaPedRecetaCabRAC.class);
            Marshaller m = context.createMarshaller();
            if(pretty){
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            }
            StringWriter sw = new StringWriter();
            m.marshal(customer, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }
    
    
    public static void main(String[] args){
        ArrayList<VtaPedRecetaDetRAC> lstPedRecetaDet = new ArrayList<>();
        VtaPedRecetaDetRAC bean = new VtaPedRecetaDetRAC();
        bean.setCod_grupo_cia("001");
        lstPedRecetaDet.add(bean);
        VtaPedRecetaDetRAC bean2 = new VtaPedRecetaDetRAC();
        bean2.setCod_grupo_cia("002");
        lstPedRecetaDet.add(bean2);
        
        VtaPedRecetaCabRAC dets = new VtaPedRecetaCabRAC();
        dets.setList(lstPedRecetaDet);
        GTRACVentaAtencionMedica n = new GTRACVentaAtencionMedica();
        String xmlInString = "";
        
        xmlInString = n.jaxbObjectToXML(dets,true);
        
        log.debug(xmlInString);
    }

    @Override
    public CmeHCAntecedentesRAC getHCAntecedentes(String pCodPaciente, int pSecAntec,String pCodLocal) {
        CmeHCAntecedentesRAC bean = new CmeHCAntecedentesRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ANTECEDENTES(?,?,?,?)}",
                         FarmaVariables.vCodGrupoCia, 
                         pCodPaciente, 
                         pSecAntec,
                         pCodLocal)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        Map<String, Object> first = resultList.get(0);
        
        ObjectMapper m = new ObjectMapper();
        bean = m.convertValue(first, CmeHCAntecedentesRAC.class);
        
        return bean;
    }

    @Override
    public CmeHCAntecedentesGinecoRAC getHCAntecedentesGineco(String pCodPaciente, int pSecAntec,String pCodLocal) {
        CmeHCAntecedentesGinecoRAC bean = new CmeHCAntecedentesGinecoRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ANTECEDENTES_GINECO(?,?,?,?)}", 
                         FarmaVariables.vCodGrupoCia, 
                         pCodPaciente, pSecAntec,pCodLocal)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() >= 0) {
               
            Map<String, Object> first = resultList.get(0);
            
            ObjectMapper m = new ObjectMapper();
            bean = m.convertValue(first, CmeHCAntecedentesGinecoRAC.class);
        }
        return bean;
    }
    
    @Override
    public ArrayList<CmeHCAntecedentesDetalleRAC> getHCAntecedentesDetalle(String pCodPaciente, int pSecAntec,String pCodLocal) {
        ArrayList<CmeHCAntecedentesDetalleRAC> lst = new ArrayList<>();
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ANTECEDENTES_DETALLE(?,?,?,?)}", 
                         FarmaVariables.vCodGrupoCia,
                         pCodPaciente, pSecAntec,pCodLocal)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() > 0) {
           
            ObjectMapper m = new ObjectMapper();
            
            for(Map<String, Object> first:resultList){            
                CmeHCAntecedentesDetalleRAC bean = new CmeHCAntecedentesDetalleRAC();
                bean = m.convertValue(first, CmeHCAntecedentesDetalleRAC.class);
                lst.add(bean);
            }
        }
        return lst;
    }

    @Override
    public ArrayList<CmeHCAntecedentesPatoloRAC> getHCAntecedentesPatolo(String pCodPaciente, int pSecAntec,String pCodLocal) {
        ArrayList<CmeHCAntecedentesPatoloRAC> lst = new ArrayList<>();
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ANTECEDENTES_PATOLO(?,?,?,?)}", 
                         FarmaVariables.vCodGrupoCia, 
                         pCodPaciente, pSecAntec,pCodLocal)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() > 0) {
           
            ObjectMapper m = new ObjectMapper();
            
            for(Map<String, Object> first:resultList){            
                CmeHCAntecedentesPatoloRAC bean = new CmeHCAntecedentesPatoloRAC();
                bean = m.convertValue(first, CmeHCAntecedentesPatoloRAC.class);
                lst.add(bean);
            }
        }
        return lst;
    }

    @Override
    public CmeAtencionMedicaRAC getAtencionMedica(String pCodCia, String pCodLocal, String pNumAtenMed) {
        CmeAtencionMedicaRAC bean = new CmeAtencionMedicaRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal, pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() == 0) {
            throw new RuntimeException("No se encontraron registros...");
        }

        Map<String, Object> first = resultList.get(0);
        
        ObjectMapper m = new ObjectMapper();
        bean = m.convertValue(first, CmeAtencionMedicaRAC.class);
        
        return bean;
    }

    @Override
    public CmeAtencionMedicaTriajeRAC getAtencionMedicaTriaje(String pCodCia, String pCodLocal, String pNumAtenMed) {
        CmeAtencionMedicaTriajeRAC bean = new CmeAtencionMedicaTriajeRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED_TRI(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal, pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() >= 0) {

            Map<String, Object> first = resultList.get(0);
            
            ObjectMapper m = new ObjectMapper();
            bean = m.convertValue(first, CmeAtencionMedicaTriajeRAC.class);
        }
        return bean;
    }

    @Override
    public CmeAtencionMedicaEnfRAC getAtencionMedicaEnfermedad(String pCodCia, String pCodLocal, String pNumAtenMed) {
        CmeAtencionMedicaEnfRAC bean = new CmeAtencionMedicaEnfRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED_ENF_ACT(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal, pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() >= 0) {

            Map<String, Object> first = resultList.get(0);
            
            ObjectMapper m = new ObjectMapper();
            bean = m.convertValue(first, CmeAtencionMedicaEnfRAC.class);
        }
        return bean;
    }

    @Override
    public CmeAtencionMedicaExFiRAC getAtencionMedicaExamenFi(String pCodCia, String pCodLocal, String pNumAtenMed) {
        CmeAtencionMedicaExFiRAC bean = new CmeAtencionMedicaExFiRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED_EX_FI(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal, pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() >= 0) {

            Map<String, Object> first = resultList.get(0);
            
            ObjectMapper m = new ObjectMapper();
            bean = m.convertValue(first, CmeAtencionMedicaExFiRAC.class);
        }
        return bean;
    }

    @Override
    public ArrayList<CmeAtencionMedicaDiagnosticoRAC> getAtencionMedicaDiagnostico(String pCodCia, String pCodLocal,
                                                                                   String pNumAtenMed) {
        ArrayList<CmeAtencionMedicaDiagnosticoRAC> lst = new ArrayList<>();
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED_DIAG(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal,pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() > 0) {
           
            ObjectMapper m = new ObjectMapper();
            
            for(Map<String, Object> first:resultList){            
                CmeAtencionMedicaDiagnosticoRAC bean = new CmeAtencionMedicaDiagnosticoRAC();
                bean = m.convertValue(first, CmeAtencionMedicaDiagnosticoRAC.class);
                lst.add(bean);
            }
        }
        return lst;
    }

    @Override
    public CmeAtencionMedicaTratamientoRAC getAtencionMedicaTratamiento(String pCodCia, String pCodLocal,
                                                                        String pNumAtenMed) {
        CmeAtencionMedicaTratamientoRAC bean = new CmeAtencionMedicaTratamientoRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED_TRA(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal, pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() >= 0) {

            Map<String, Object> first = resultList.get(0);
            
            ObjectMapper m = new ObjectMapper();
            bean = m.convertValue(first, CmeAtencionMedicaTratamientoRAC.class);
        }
        return bean;
    }

    @Override
    public CmeAtencionMedicaExamenesRAC getAtencionMedicaExamenes(String pCodCia, String pCodLocal,
                                                                  String pNumAtenMed) {
        CmeAtencionMedicaExamenesRAC bean = new CmeAtencionMedicaExamenesRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED_EXA(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal, pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() >= 0) {

            Map<String, Object> first = resultList.get(0);
            
            ObjectMapper m = new ObjectMapper();
            bean = m.convertValue(first, CmeAtencionMedicaExamenesRAC.class);
        }
        return bean;
    }

    @Override
    public CmeAtencionMedicaProcedimientosRAC getAtencionMedicaProcedimientoss(String pCodCia, String pCodLocal,
                                                                               String pNumAtenMed) {
        CmeAtencionMedicaProcedimientosRAC bean = new CmeAtencionMedicaProcedimientosRAC();
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ATEN_MED_PRO(?,?,?,?)}", FarmaVariables.vCodGrupoCia, pCodCia, pCodLocal, pNumAtenMed)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() >= 0) {

            Map<String, Object> first = resultList.get(0);
            
            ObjectMapper m = new ObjectMapper();
            bean = m.convertValue(first, CmeAtencionMedicaProcedimientosRAC.class);
        }
        return bean;
    }

    @Override
    public ArrayList<HistCompAtencionMedica> getEstCompAtencion(String pCodLocal, String pNumPedVta) {
        ArrayList<HistCompAtencionMedica> lst = new ArrayList<>();
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpCursor("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CME.F_GET_ESTADO_ATENCION(?,?,?)}", FarmaVariables.vCodGrupoCia, pCodLocal,pNumPedVta)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> resultList = status.getResult();

        if (resultList.size() > 0) {
           
            ObjectMapper m = new ObjectMapper();
            
            for(Map<String, Object> first:resultList){            
                HistCompAtencionMedica bean = new HistCompAtencionMedica();
                bean = m.convertValue(first, HistCompAtencionMedicaRAC.class);
                lst.add(bean);
            }
        }
        return lst;
    }
}
