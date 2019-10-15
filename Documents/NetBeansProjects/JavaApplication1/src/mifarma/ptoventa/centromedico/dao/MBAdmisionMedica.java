package mifarma.ptoventa.centromedico.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.encuesta.modelo.BeanEncuesta;
import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.MyBatisUtil;

import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.apache.ibatis.session.SqlSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MBAdmisionMedica implements DAOAdmisionMedica {
    
    private static final Logger log = LoggerFactory.getLogger(MBAdmisionMedica.class);
    
    private SqlSession sqlSession = null;
    private MapperAdmisionMedica mapper = null;
    
    public MBAdmisionMedica() {
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
        mapper = sqlSession.getMapper(MapperAdmisionMedica.class);
    }
    
    /*public ArrayList<ArrayList<String>> listPedidoATM(String pCodGrupoCia, String pCodEstado, String pCodMedico) throws Exception{
        List<BeanResultado> tmpLista = null;
        ArrayList<ArrayList<String>> tmpArray;
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", pCodGrupoCia);
        mapParametros.put("cCodEstado_in", pCodEstado);
        mapParametros.put("cCodMedico_in", pCodMedico);
        mapper.listPedidoATM(mapParametros);
        tmpLista = (List<BeanResultado>)mapParametros.get("listado");
        tmpArray = (new UtilityPtoVenta()).parsearResultadoMatriz(tmpLista);
        return tmpArray;
    }*/
    public List<VtaPedidoAtencionMedica> listPedidoATM(VtaPedidoAtencionMedica vPAM) throws Exception{
    //(String pCodGrupoCia, String pCodEstado, String pCodMedico) throws Exception{
        List<VtaPedidoAtencionMedica> lst = null;
        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
        mapParametros.put("cCodGrupoCia_in", vPAM.getCod_grupo_cia());
        mapParametros.put("cCodEstado_in", vPAM.getCod_grupo_cia());
        mapParametros.put("cCodMedico_in", vPAM.getCod_grupo_cia());

        mapper.listPedidoATM(mapParametros);

        lst = (List<VtaPedidoAtencionMedica>)mapParametros.get("listado");

        return lst;
    }
    
}
