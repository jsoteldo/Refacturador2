package mifarma.cpe.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mifarma.cpe.modelo.BeanConexionEPOS;

import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.MyBatisUtil;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.apache.ibatis.session.SqlSession;


public class MBCPElectronico implements DAOCPElectronico {
    
    private SqlSession sqlSession = null;
    private MapperCPE mapper = null;
    
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
        mapper = sqlSession.getMapper(MapperCPE.class);
    }
    
    public String obtenerIndicadorFuncionalidad(String pCodGrupoCia, String pCodLocal)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapper.obtenerIndicadorFuncionalidad(mapParametros);
        return mapParametros.get("resultado").toString();
    }
    
    public List<BeanConexionEPOS> obtenerDatosConexion(String pCodGrupoCia, String pCodCia, String pCodLocal)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodCia_in", pCodCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapper.obtenerDatosConexion(mapParametros);
        List<BeanConexionEPOS> lstRetorno = (List<BeanConexionEPOS>)mapParametros.get("listado");
        if(lstRetorno == null){
            return (new ArrayList<BeanConexionEPOS>());
        }else{
            return lstRetorno;
        }
    }
    
    public String getPCRegistradaEnEpos(String pCodGrupoCia, String pCodLocal, String pIpPC)throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cIpPc_in", pIpPC);
        mapper.getPCRegistradaEnEpos(mapParametros);
        return mapParametros.get("resultado").toString();
    }
    
    public void actualizarEstadoPCRegistrada(String pCodGrupoCia, String pCodLocal, String pIpPc, String pEstado, String pMensaje)throws Exception{
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cPcIp_in", pIpPc);
        mapParametros.put("cEstadoRegistro_in", pEstado);
        mapParametros.put("cMensaje_in", pMensaje);
        mapper.actualizarEstadoPCRegistrada(mapParametros);
    }
    
    public void actualizarComprobanteProcesado(String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pCodPDF417, String pTipoCP)throws Exception{
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        mapParametros.put("cSecCompPago_in", pSecCompPago);
        mapParametros.put("cCodPDF417_in", pCodPDF417);
        mapParametros.put("cTipCompPago_in", pTipoCP);
        mapper.actualizarEstadoComprobante(mapParametros);
    }
    
    public void actualizarComprobanteProcesado_code(String pCodGrupoCia, String pCodLocal, String pNumPedVta, 
                                                    String pSecCompPago, String pCodPDF417, 
                                                    String pTipoCP,String pTipoCode)throws Exception{
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cNumPedVta_in", pNumPedVta);
        mapParametros.put("cSecCompPago_in", pSecCompPago);
        mapParametros.put("cCodPDF417_in", pCodPDF417);
        mapParametros.put("cTipCompPago_in", pTipoCP);
        mapParametros.put("cTipoCode_in", pTipoCode);
        mapper.actualizarEstadoComprobante_code(mapParametros);
    }
    
    public void enviarMensajeCorreo(String pCodGrupoCia, String pCodLocal, int tipoMensaje, String mensaje)throws Exception{
        Map<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodLocal_in", pCodLocal);
        mapParametros.put("cTipoMensaje_in", tipoMensaje);
        mapParametros.put("cMensaje_in", mensaje);
        mapper.enviarMensajeCorreo(mapParametros);
    }
    
    @Override
    public String obtenerTramaCab(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaCab(mapParametros);
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaExt(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaExt(mapParametros);
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaPerc(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaPerc(mapParametros);
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaDoc(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaDoc(mapParametros);
        //return mapParametros.get("resultado").toString();
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaNot(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaNot(mapParametros);
        //return mapParametros.get("resultado").toString();
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public ArrayList<ArrayList<String>> obtenerTramaDet(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        List<BeanResultado> tmpLista = null;
        ArrayList<ArrayList<String>> tmpArray = new ArrayList<>();
        
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaDet(mapParametros);
        tmpLista = (List<BeanResultado>)mapParametros.get("listado");
        tmpArray = (new UtilityPtoVenta()).parsearResultadoMatriz(tmpLista);
        return tmpArray;
    }
    
    @Override
    public String obtenerTramaIG(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaIG(mapParametros);
        //return mapParametros.get("resultado").toString();
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaRef(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaRef(mapParametros);
        //return mapParametros.get("resultado").toString();
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaPP(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaPP(mapParametros);
        //return mapParametros.get("resultado").toString();
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaMsjBF(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaMsjBF(mapParametros);
        //return mapParametros.get("resultado").toString();
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaMsjAF(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaMsjAF(mapParametros);
        //return mapParametros.get("resultado").toString();
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }
    
    @Override
    public String obtenerTramaMsjPersonalizado(HashMap<String, Object> mapParametros){//String pCodGrupoCia, String pCodLocal, String pNumPedVta, String pSecCompPago, String pTipoCompPago, String pTipoClienteConv) throws Exception{
        /*HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cGrupoCia", pCodGrupoCia);
        mapParametros.put("cCodLocal", pCodLocal);
        mapParametros.put("cNumPedidoVta", pNumPedVta);
        mapParametros.put("cSecCompPago", pSecCompPago);
        mapParametros.put("cTipoDocumento", pTipoCompPago);
        mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);*/
        mapper.obtenerTramaMsjPersonalizado(mapParametros);
        //return mapParametros.get("resultado").toString();
        System.out.println("MSJ_PERSONALIZADO: "+mapParametros.get("resultado").toString());
        if(mapParametros.get("resultado") == null)
            return null;
        else
            return mapParametros.get("resultado").toString();
    }

    @Override
    public String obtenerTiempoInactividadHilo()throws Exception{
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapper.obtenerTiempoInactividadHilo(mapParametros);
        return mapParametros.get("resultado").toString();
    }
}