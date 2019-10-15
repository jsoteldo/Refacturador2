package mifarma.ptoventa.centromedico.reference;

import com.gs.mifarma.componentes.OptionComboBox;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import mifarma.common.FarmaVariables;

import mifarma.electronico.UtilityImpCompElectronico;

import mifarma.ptoventa.centromedico.dao.DAOAtencionMedica;

import mifarma.ptoventa.centromedico.dao.FactoryAtencionMedica;
import mifarma.ptoventa.centromedico.dao.MBAtencionMedica;

import mifarma.ptoventa.centromedico.domain.BeanPaciente;

import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedDiagnostico;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTrataReceta;

import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTrataRecetaDetalle;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTratamiento;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;

import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentes;

import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesFisiologicos;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesGenerales;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesGinecologicos;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesPatologico;
import mifarma.ptoventa.reference.BeanImpresion;

import mifarma.ptoventa.reference.TipoImplementacionDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacadeAtencioMedica {
    
    private static final Logger log = LoggerFactory.getLogger(FacadeAtencioMedica.class);
    private DAOAtencionMedica daoAtencionMedica;
    
    public FacadeAtencioMedica() {
        super();
    }
    
    public ArrayList<ArrayList<String>> obtenerListadoPacientesEspera(String pCodGrupoCia, String pCodEstado, String pCodMedico){
        ArrayList<ArrayList<String>> lista = null;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            lista = daoAtencionMedica.obtenerListaPacientes(pCodGrupoCia, pCodEstado, pCodMedico);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            lista = new ArrayList<ArrayList<String>>();
        }
        return lista;
    }
    
    public boolean actualizaSolicutudAtencion(String pCodGrupoCia, String pCodLocal, String pNroAtencion, String pCodEstado, String pUsuario){
        boolean actualizado = true;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            daoAtencionMedica.actualizarSolicitud(pCodGrupoCia, pCodLocal, pNroAtencion, pCodEstado, pUsuario);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            actualizado = false;
        }
        return actualizado;
    }
    
    public ArrayList obtenerListaProductos(String pCodGrupoCia, String pCodLocal){
        ArrayList lista = new ArrayList();
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            lista = daoAtencionMedica.obtenerListaProducto(pCodGrupoCia, pCodLocal);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            lista = new ArrayList();
        }
        return lista;
    }
    
    
    
    public BeanPaciente obtenerDatosPaciente(String pCodGrupoCia, String pCodPaciente){
        BeanPaciente paciente = null;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            paciente = daoAtencionMedica.buscarPaciente(pCodGrupoCia, pCodPaciente);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            paciente = null;
        }
        return paciente;
    }
    
    public boolean grabarConsulta(BeanAtencionMedica beanAtencion, String pUsuario, boolean grabaTemporal)throws Exception{
        return grabarConsulta(beanAtencion, pUsuario, grabaTemporal, false);
    }
    
    public boolean grabarConsulta(BeanAtencionMedica beanAtencion, String pUsuario, boolean grabaTemporal, boolean conectaRAC)throws Exception{
        boolean grabo = true;
        try{
            if(conectaRAC)
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.GESTORTX);
            else
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            
            
                daoAtencionMedica.openConnection();
                daoAtencionMedica.borrarConsultaTemporal(beanAtencion.getCodGrupoCia(), beanAtencion.getCodCia(), beanAtencion.getCodLocal(), beanAtencion.getNroAtencionMedica());
                daoAtencionMedica.grabarAtencionMedicaEnfermedadActual(beanAtencion.getEnfermedadActual(), pUsuario);
                if(beanAtencion.getTriaje()!=null)
                    daoAtencionMedica.grabarAtencionMedicaTriaje(beanAtencion.getTriaje(), pUsuario);
                daoAtencionMedica.grabarAtencionMedicaExamenFisico(beanAtencion.getExamenFisico(), pUsuario);
                for(int i=0; i<beanAtencion.getDiagnostico().size(); i++){
                    daoAtencionMedica.grabarAtencionMedicaDiagnostico(beanAtencion.getDiagnostico().get(i), pUsuario);
                }
                
                BeanAtMedTratamiento tratamiento = beanAtencion.getTratamiento();
                if(tratamiento!=null){
                    BeanAtMedTrataReceta receta = tratamiento.getReceta();
                    //dubilluz 19.12.2016
                    if(receta.getDetalles().size()>0){
                        //2017.05.18 set el codigo de medico del tratamiento
                        receta.setCodMedico(beanAtencion.getCodMedico());
                        
                        daoAtencionMedica.grabarRecetaCabecera(receta, tratamiento.getValidezReceta(), pUsuario);
                        for(int i=0; i < receta.getDetalles().size();i++){
                            BeanAtMedTrataRecetaDetalle detalle = receta.getDetalles().get(i);
                            daoAtencionMedica.grabarRecetaDetalle(detalle, receta.getNroPedidoReceta(), pUsuario);
                        }
                        daoAtencionMedica.grabarTratamiento(tratamiento, receta.getNroPedidoReceta(), pUsuario);
                        beanAtencion.getTratamiento().setNroPedidoReceta(receta.getNroPedidoReceta());    
                    }
                }
                if(beanAtencion.getExamenesAuxiliares()!=null)
                    daoAtencionMedica.grabarAtencionMedicaExamenAuxiliares(beanAtencion.getExamenesAuxiliares(), pUsuario);
                if(beanAtencion.getOtros()!=null)
                    daoAtencionMedica.grabarAtencionMedicaOtros(beanAtencion.getOtros(), pUsuario);

                if(grabaTemporal)
                    daoAtencionMedica.actualizarSolicitud(beanAtencion.getCodGrupoCia(), beanAtencion.getCodLocal(), beanAtencion.getNroAtencionMedica(), ConstantsCentroMedico.ATE_MEDICA_GUARDADA, pUsuario);
                else 
                    daoAtencionMedica.actualizarSolicitud(beanAtencion.getCodGrupoCia(), beanAtencion.getCodLocal(), beanAtencion.getNroAtencionMedica(), ConstantsCentroMedico.ATE_MEDICA_ATENDIDO, pUsuario);
                
                if(conectaRAC && !grabaTemporal)
                    daoAtencionMedica.confirmarTransaccionRAC();
                
                daoAtencionMedica.commit();
                
                if(!conectaRAC && !grabaTemporal){
                    try{
                        grabarConsulta(beanAtencion, pUsuario, grabaTemporal, true);
                    }catch(Exception x){
                        x.printStackTrace();
                    }
                }
            
        }catch(Exception ex){
            ex.printStackTrace();
           // log.error("",ex);
            daoAtencionMedica.rollback();
            grabo = false;
            throw new Exception(ex.toString());
        }
        return grabo;
    }
    
    /**
     * @author KMONCADA
     * @since 12.09.2016
     * @param pCodGrupoCia
     * @param pCodCia
     * @param pCodLocal
     * @param pNroConsulta
     * @return
     */
    public BeanAtencionMedica obtenerConsultaMedica(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta){
        BeanAtencionMedica consultaMedica = null;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            consultaMedica = daoAtencionMedica.obtenerAtencionMedica(pCodGrupoCia, pCodCia, pCodLocal, pNroConsulta);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            consultaMedica = null;
        }
        return consultaMedica;
    }
    
    /**
     * @author JMONZALVE
     * @since 17.01.2018
     * @param pCodGrupoCia
     * @param pCodCia
     * @param pCodLocal
     * @param pNroConsulta
     * @return
     */
    public String obtenerNroAtencionMedica(String pCodGrupoCia, String pCodCia, String pCodLocal, String codPaciente){
        String nroAtencionMedica = "";
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            nroAtencionMedica = daoAtencionMedica.obtenerNroAtencionMedica(pCodGrupoCia, pCodCia, pCodLocal, codPaciente);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            nroAtencionMedica = "";
        }
        return nroAtencionMedica;
    }
    
    public ArrayList<OptionComboBox> obtenerListaComboCheckBox(int codMaestro){
        ArrayList<OptionComboBox> lista = null;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            lista = daoAtencionMedica.obtenerListaComboCheckBox(codMaestro);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            lista = new ArrayList<OptionComboBox>();
        }
        return lista;
    }
    
    public BeanHCAntecedentes obtenerAntecedenteHC(String pCodGrupoCia, String pCodLocal, String pCodPaciente, int nroSecuenciaAntecedente){
        BeanHCAntecedentes antecedente = null;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            if(pCodLocal==null)
                pCodLocal = "";
            if(pCodPaciente==null)
                pCodPaciente = "";
            antecedente = daoAtencionMedica.obtenerAntecedenteHC(pCodGrupoCia, pCodLocal, pCodPaciente, nroSecuenciaAntecedente);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            antecedente = null;
        }
        return antecedente;
    }
    
    public boolean grabarAntecedenteHC(BeanHCAntecedentes antecedente, String pUsuario)throws Exception{
        return grabarAntecedenteHC(antecedente, pUsuario, false);
    }
    
    private boolean grabarAntecedenteHC(BeanHCAntecedentes antecedente, String pUsuario, boolean conectaRAC)throws Exception{
        boolean grabo = true;
        try{
            if(conectaRAC)
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.GESTORTX);
            else
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            daoAtencionMedica.grabarAntecedenteHC(antecedente, pUsuario);
            if(antecedente.getGinecologico()!=null){
                BeanHCAntecedentesGinecologicos ginecologico = antecedente.getGinecologico();
                ginecologico.setCodGrupoCia(antecedente.getCodGrupoCia());
                ginecologico.setCodLocal(antecedente.getCodLocal());
                ginecologico.setCodPaciente(antecedente.getCodPaciente());
                ginecologico.setSecuencialHC(antecedente.getSecuencialHC());
                daoAtencionMedica.grabarAntecedenteHCGinecologico(ginecologico, pUsuario);
            }
            
            if(antecedente.getFisiologico()!=null){
                for(int i=0; i<antecedente.getFisiologico().size(); i++){
                    BeanHCAntecedentesFisiologicos fisiologico = antecedente.getFisiologico().get(i);
                    fisiologico.setCodGrupoCia(antecedente.getCodGrupoCia());
                    fisiologico.setCodLocal(antecedente.getCodLocal());
                    fisiologico.setCodPaciente(antecedente.getCodPaciente());
                    fisiologico.setSecuencialHC(antecedente.getSecuencialHC());
                    daoAtencionMedica.grabarAntecedenteHCFisiologico(fisiologico, pUsuario);
                }
            }
            
            if(antecedente.getPatologicos()!=null){
                for(int i=0; i<antecedente.getPatologicos().size();i++){
                    BeanHCAntecedentesPatologico patologico = antecedente.getPatologicos().get(i);
                    patologico.setCodGrupoCia(antecedente.getCodGrupoCia());
                    patologico.setCodLocal(antecedente.getCodLocal());
                    patologico.setCodPaciente(antecedente.getCodPaciente());
                    patologico.setSecuencialHC(antecedente.getSecuencialHC());
                    daoAtencionMedica.grabarAntecedenteHCPatologicos(patologico, pUsuario);
                }
            }
            
            if(conectaRAC) {
                daoAtencionMedica.confirmarTransaccionRAC();
            }
            daoAtencionMedica.commit();
            log.info("[CENTRO MEDICO]:SE GRABARA EN EL RAC.");
            if(!conectaRAC){
                try{
                    grabarAntecedenteHC(antecedente, pUsuario, true);
                }catch(Exception x){                
                }
            }
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            grabo = false;
            throw new Exception(ex.toString());
        }
        return grabo;
    }
    
    public ArrayList obtenerProductoReceta(String pCodGrupoCia, String pCodProd){
        ArrayList lista = new ArrayList();
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            lista = daoAtencionMedica.obtenerProductoReceta(pCodGrupoCia, pCodProd);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            lista = new ArrayList();
        }
        return lista;
    }
    
    public ArrayList<ArrayList<String>> obtenerListaAntecedentesHCPaciente(String pCodGrupoCia, String pCodPaciente, String pFechaInicio, String pFechaFin, boolean conexionRAC){
        ArrayList<ArrayList<String>> lista = null;
        try{
            if(conexionRAC)
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.GESTORTX);
            else
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            lista = daoAtencionMedica.listarAntecedentesHCPaciente(pCodGrupoCia, pCodPaciente, pFechaInicio, pFechaFin);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            lista = null;
        }
        return lista;
    }
    
    public boolean imprimeRecetaFormatoDigital()throws Exception{
        boolean imprime = true;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            String rspta = daoAtencionMedica.getIndicadorImpresionDigitalReceta();
            imprime = "S".equalsIgnoreCase(rspta);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            daoAtencionMedica.rollback();
            imprime = false;
            throw new Exception(ex.toString());
        }
        return imprime;
    }
    
    public String obtenerNombreImpresoraRecetaDigital(String pCodGrupoCia, String pCodLocal, String pIpPc){
        String nombreImpresora = "";
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            nombreImpresora = daoAtencionMedica.obtenerNombreImpresoraRecetaDigital(pCodGrupoCia, pCodLocal, pIpPc);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            nombreImpresora = null;
        }
        return nombreImpresora;
    }
    
    public List<BeanImpresion> obtenerFormatoRecetaTermica(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta){
        List<BeanImpresion> lista  = null;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            lista = daoAtencionMedica.obtenerFormatoTermicaReceta(pCodGrupoCia, pCodCia, pCodLocal, pNroConsulta);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("", ex);
            daoAtencionMedica.rollback();
            lista  = null;
        }
        return lista;
    }
    
    public ArrayList<ArrayList<String>> obtenerListaAtencionesMedicas(String pCodGrupoCia, String pCodPaciente, String pFechaInicio, String pFechaFin, boolean conectaRAC){
        ArrayList<ArrayList<String>> lista = null;
        try{
            if(conectaRAC)
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.GESTORTX);
            else
                daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            lista = daoAtencionMedica.listarAtencionesMedicas(pCodGrupoCia, pCodPaciente, pFechaInicio, pFechaFin);
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            lista = new ArrayList<ArrayList<String>>();
        }
        return lista;
    }
    
    public int obtenerCantidadDiasVigenciaReceta(){
        int cantidad = 0;
        try{
            daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
            daoAtencionMedica.openConnection();
            cantidad = daoAtencionMedica.obtenerCantidadDiasVigenciaReceta();
            daoAtencionMedica.commit();
        }catch(Exception ex){
            log.error("",ex);
            daoAtencionMedica.rollback();
            cantidad = -1;
        }
        return cantidad;
    }
    
   public ArrayList<ArrayList<String>> obtenerListadoCompPacientes(String pCodGrupoCia, String pCodLocal, String pTipo,
                                                                    VtaCompAtencionMedica vtaCAM, VtaPedidoAtencionMedica vtaPAM)throws Exception{
       
       ArrayList<ArrayList<String>> lista = null;
       try{
           daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.GESTORTX);
           daoAtencionMedica.openConnection();
           lista = daoAtencionMedica.obtenerListadoCompPacientes(pCodGrupoCia, pCodLocal, pTipo, vtaCAM, vtaPAM);
           daoAtencionMedica.commit();
       }catch(Exception ex){
           log.error("",ex);
           daoAtencionMedica.rollback();
           lista = new ArrayList<ArrayList<String>>();
       }
       return lista;
   }
   
   public String isValidaDatosAccesoMedico(String tipoColegio, String pNroCMP, String pNroDNI)throws Exception{
       String codigoPaciente = "";
       try{
           daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
           daoAtencionMedica.openConnection();
           codigoPaciente = daoAtencionMedica.validaDatosAccesoConsulta(tipoColegio, pNroCMP, pNroDNI);
           daoAtencionMedica.commit();
       }catch(Exception ex){
           //log.error("",ex);
           daoAtencionMedica.rollback();
           codigoPaciente = "";
           throw new Exception(ex);
       }
       return codigoPaciente;
   }
   
   public ArrayList obtenerListaTipoColegiatura(){
       ArrayList lista = new ArrayList();
       try{
           daoAtencionMedica = FactoryAtencionMedica.getDAOAtencionMedica(TipoImplementacionDAO.MYBATIS);
           daoAtencionMedica.openConnection();
           lista = daoAtencionMedica.obtenerListaTipoColegiatura();
           daoAtencionMedica.commit();
       }catch(Exception ex){
           log.error("",ex);
           daoAtencionMedica.rollback();
           lista = new ArrayList();
       }
       return lista;
   }
}
