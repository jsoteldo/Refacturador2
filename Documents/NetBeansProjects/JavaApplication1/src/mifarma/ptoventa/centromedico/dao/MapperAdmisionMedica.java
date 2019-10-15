package mifarma.ptoventa.centromedico.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface MapperAdmisionMedica {
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PTOVENTA.CENTRO_MEDICO.CME_LISTA_PACIENTES("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodEstado_in}," + 
            "#{cCodMedico_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void listPedidoATM(HashMap<String, Object> object);
}
