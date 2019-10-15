package mifarma.cpe.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface MapperCPE {
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_IS_ACT_FUNC_E("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerIndicadorFuncionalidad(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=conexionEPOS} := " + 
            "PTOVENTA.FARMA_CNX_REMOTO.F_CNX_LOCAL_EPOS(" +
            "#{cCodGrupoCia_in}," + 
            "#{cCodCia_in}," + 
            "#{cCodLocal_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerDatosConexion(HashMap<String, Object> mapParametros);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VALIDA_REGISTRO_CAJA_EPOS("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," + 
            "#{cIpPc_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getPCRegistradaEnEpos(HashMap<String, Object> object);
    
    @Select(value =
            "{call  " + "PTOVENTA.FARMA_EPOS.F_REGISTRO_PC_EN_EPOS(" + 
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," +
            "#{cPcIp_in}," +
            "#{cEstadoRegistro_in}," +
            "#{cMensaje_in}" + 
            ")}")
    public void actualizarEstadoPCRegistrada(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call  " + "PTOVENTA.FARMA_EPOS.F_ACTUALIZA_PROCESO_COMP_PAGO(" + 
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," +
            "#{cNumPedVta_in}," +
            "#{cSecCompPago_in}," +
            "#{cCodPDF417_in}," + 
            "#{cTipCompPago_in}"+
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void actualizarEstadoComprobante(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call  " + "PTOVENTA.FARMA_EPOS.F_ACTUALIZA_PROCESO_COMP_PAGO(" + 
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," +
            "#{cNumPedVta_in}," +
            "#{cSecCompPago_in}," +
            "#{cCodPDF417_in}," + 
            "#{cTipCompPago_in},"+
            "#{cTipoCode_in}"+
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void actualizarEstadoComprobante_code(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call  " + "PTOVENTA.FARMA_EPOS.F_ENVIA_MAIL_ERROR(" + 
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," +
            "#{cTipoMensaje_in}," +
            "#{cMensaje_in}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void enviarMensajeCorreo(Map<String, Object> mapParametros);

    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_CAB("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaCab(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_EXT("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaExt(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_PERC("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaPerc(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_DOC("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaDoc(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_NOTAS("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaNot(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=valor} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_DET("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaDet(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_IG("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaIG(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_REF("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaRef(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_PP("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaPP(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_MSJ_BF("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaMsjBF(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_MSJ_AF("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaMsjAF(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_VAR_TRM_GET_MSJ_PERSONALIZA("+
            "#{cGrupoCia}," + 
            "#{cCodLocal}," + 
            "#{cNumPedidoVta}," + 
            "#{cSecCompPago}," + 
            "#{cTipoDocumento}," + 
            "#{cTipoClienteConvenio}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTramaMsjPersonalizado(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.FARMA_EPOS.F_GET_TIMPO_ESPERA_HILO_EPOS"+
            "}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerTiempoInactividadHilo(HashMap<String, Object> object);
    
}
