package mifarma.ptoventa.centromedico.dao;

import com.gs.mifarma.componentes.OptionComboBox;

import java.util.ArrayList;
import java.util.List;

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
import mifarma.ptoventa.reference.BeanImpresion;
import mifarma.ptoventa.reference.DAOTransaccion;

public interface DAOAtencionMedica extends DAOTransaccion {
    
    public ArrayList<ArrayList<String>> obtenerListaPacientes(String pCodGrupoCia, String pCodEstado, String pCodMedico) throws Exception;
    public void actualizarSolicitud(String pCodGrupoCia, String pCodLocal, String pNroAtencion, String pCodEstado, String pUsuario)throws Exception;
    public ArrayList obtenerListaProducto(String pCodGrupoCia, String pCodLocal)throws Exception;
    public BeanPaciente buscarPaciente(String pCodGrupoCia, String pCodPaciente)throws Exception;
    
    public void grabarRecetaCabecera(BeanAtMedTrataReceta beanReceta, String pFechaVigencia, String pUsuario)throws Exception;
    public void grabarRecetaDetalle(BeanAtMedTrataRecetaDetalle beanRecetaDetalle, String pNumReceta, String pUsuario)throws Exception;
    public void grabarTratamiento(BeanAtMedTratamiento beanTratamiento, String pNumReceta, String pUsuario) throws Exception;
    public void grabarAtencionMedicaEnfermedadActual(BeanAtMedEnfermedadActual beanEnfermedadActual, String pUsuario ) throws Exception;
    public void grabarAtencionMedicaTriaje(BeanAtMedTriaje beanTriaje, String pUsuario)throws Exception;
    public void grabarAtencionMedicaExamenFisico(BeanAtMedExamenFisico beanExaFisico, String pUsuario)throws Exception;
    public void grabarAtencionMedicaDiagnostico(BeanAtMedDiagnostico beanDiagnostico, String pUsuario)throws Exception;
    public void grabarAtencionMedicaExamenAuxiliares(BeanAtMedExamenesAuxiliares beanExaAuxiliares, String pUsuario)throws Exception;
    public void grabarAtencionMedicaOtros(BeanAtMedOtros beanOtros, String pUsuario)throws Exception;
    public BeanAtencionMedica obtenerAtencionMedica(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta)throws Exception;
    public String obtenerNroAtencionMedica(String pCodGrupoCia, String pCodCia, String pCodLocal, String codPaciente)throws Exception;
    
    public ArrayList<OptionComboBox> obtenerListaComboCheckBox(int codMaestro)throws Exception;
    public BeanHCAntecedentes obtenerAntecedenteHC(String pCodGrupoCia, String pCodLocal, String pCodPaciente, int nroSecuenciaAntecedente)throws Exception;
    
    public void grabarAntecedenteHCPatologicos(BeanHCAntecedentesPatologico patologico, String pUsuario)throws Exception;
    public void grabarAntecedenteHCFisiologico(BeanHCAntecedentesFisiologicos fisiologico, String pUsuario)throws Exception;
    public void grabarAntecedenteHCGinecologico(BeanHCAntecedentesGinecologicos ginecologico, String pUsuario)throws Exception;
    public void grabarAntecedenteHC(BeanHCAntecedentes antecedente, String pUsuario) throws Exception;
    
    public ArrayList obtenerProductoReceta(String pCodGrupoCia, String pCodProd)throws Exception;
    public ArrayList<ArrayList<String>> listarAntecedentesHCPaciente(String pCodGrupoCia, String pCodPaciente, String pFechaInicio, String pFechaFin) throws Exception;
    public String getIndicadorImpresionDigitalReceta()throws Exception;
    public String obtenerNombreImpresoraRecetaDigital(String pCodGrupoCia, String pCodLocal, String pIpPc)throws Exception;
    public List<BeanImpresion> obtenerFormatoTermicaReceta(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta) throws Exception;
    
    public ArrayList<ArrayList<String>> listarAtencionesMedicas(String pCodGrupoCia, String pCodPaciente, String pFechaInicio, String pFechaFin) throws Exception;
    public int obtenerCantidadDiasVigenciaReceta()throws Exception;
    public ArrayList<ArrayList<String>> obtenerListadoCompPacientes(String pCodGrupoCia, String pCodLocal, String pTipo,
                                                                    VtaCompAtencionMedica vtaCAM, VtaPedidoAtencionMedica vtaPAM) throws Exception;
    
    public String validaDatosAccesoConsulta(String tipoColegio, String pNroCMP, String pNroDNI)throws Exception;
    
    public ArrayList<ArrayList<String>> obtenerListaTipoColegiatura() throws Exception;
    
    public void borrarConsultaTemporal(String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroConsulta)throws Exception;
    
    public void confirmarTransaccionRAC();
}
