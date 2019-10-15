package mifarma.cpe.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mifarma.cpe.modelo.BeanComprobantesEPOS;

import mifarma.cpe.modelo.BeanConexionEPOS;

import mifarma.ptoventa.reference.DAOTransaccion;

public interface DAOCPElectronico extends DAOTransaccion {
    
    public String obtenerIndicadorFuncionalidad(String pCodGrupoCia, String pCodLocal)throws Exception;
    public List<BeanConexionEPOS> obtenerDatosConexion(String pCodGrupoCia, String pCodCia, String pCodLocal)throws Exception;
    public String getPCRegistradaEnEpos(String pCodGrupoCia, String pCodLocal, String pIpPC)throws Exception;
    public void actualizarEstadoPCRegistrada(String pCodGrupoCia, String pCodLocal, String pIpPc, String pEstado, String pMensaje)throws Exception;
    public void actualizarComprobanteProcesado(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pCodPDF417, String pTipoCP)throws Exception;
    public void actualizarComprobanteProcesado_code(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pCodPDF417, String pTipoCP,String pTipoCode)throws Exception;
    public void enviarMensajeCorreo(String pCodGrupoCia, String pCodLocal, int tipoMensaje, String mensaje)throws Exception;
    public String obtenerTramaCab(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaExt(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago) throws Exception;
    public String obtenerTramaPerc(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago) throws Exception;
    public String obtenerTramaDoc(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaNot(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public ArrayList<ArrayList<String>> obtenerTramaDet(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaIG(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaRef(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaPP(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaMsjBF(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaMsjAF(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTramaMsjPersonalizado(HashMap<String, Object> parametros);//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception;
    public String obtenerTiempoInactividadHilo()throws Exception;
}
