package mifarma.ptoventa.centromedico.dao;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author ERIOS
 * @since 24.08.2016
 */
public abstract class SQLUtilProvider {
    
    public String selectTablaNumPedido(String pTabla) {
        return new SQL().SELECT("*").FROM(pTabla).WHERE("COD_GRUPO_CIA = #{cCodGrupoCia}").WHERE("COD_LOCAL = #{cCodLocal}").WHERE("NUM_PED_VTA = #{cNumPedVta}").toString();
    }
    
    public String selectTablaNumReceta(String pTabla) {
        return new SQL().SELECT("*").FROM(pTabla).WHERE("COD_GRUPO_CIA = #{cCodGrupoCia}").WHERE("COD_LOCAL = #{cCodLocal}").WHERE("NUM_PED_REC = #{cNumPedRec}").toString();
    }
    
}
