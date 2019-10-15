package mifarma.ptoventa.lealtad.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;


/**
 *
 * @author ERIOS
 * @since 05.02.2015
 */
public interface MapperLealtad {
    
    @Select(value =
            "{call #{nRetorno, mode=OUT, jdbcType=NUMERIC} := " + 
            "PKG_PROM_ACUMULA.IS_PROD_CAMP_ACUMULA(" +
            "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
            "   #{cCodProd_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void verificaAcumulaX1(Map<String, Object> mapParametros);
    // lais X+1
    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.FN_LISTA_PROG_ACUMULA_X1(" +
            "   #{PI_COGCIA}," + "   #{PI_CODLOC}," +
            "   #{PI_LSTPRG}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void listaProgramasAcumulaX1(Map<String, Object> mapParametros);
    // lais X+1
    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.LISTA_ACUMULA_X1(" +
            "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
            "   #{cCodProd_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void listaAcumulaX1(Map<String, Object> mapParametros);
    // lais X+1
    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.FN_DETALLE_LISTA_ACUMULA_X1(" +
            "   #{PI_COGCIA}," + "   #{PI_CODLOC}," +
            "   #{PI_CODPRO}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void detalleListaAcumulaX1(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.FN_DET_PROG_LISTA_ACUMULA_X1(" +
            "   #{PI_COGCIA}," + "   #{PI_CODLOC}," +
            "   #{PI_COPROG}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void detalleListaProgramasAcumulaX1(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.FN_LISTA_INSCRIPCION_AUX(" +
            "   #{PI_COGCIA}," + "   #{PI_CODLOC}," + "   #{PI_DNICLI}," + 
            "   #{PI_ESTREG}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void programasTemporalesX1(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.FN_OBTENER_PRODUCTOS(" +
            "   #{PI_COPROG}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void obtenerProductosDeProgramas(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.FN_OBTENER_PROGRAMAS_DE_PROD(" +
            " #{PI_DNICLI}," + 
            " #{PI_CODPRO}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void obtenerProgramasTemporalesDeProducto(Map<String, Object> mapParametros);
    
    @Select(value =
            "{call PTOVENTA.PKG_PROM_ACUMULA.REGISTRO_INSCRIPCION_TEMP(" +
            " #{PI_COGCIA}," + " #{PI_CODLOC}," + " #{PI_DNICLI}," + " #{PI_COPROG}," + " #{PI_ESTREG}," +
            " #{PI_USUINS}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void registrarProgramasTempX1(Map<String, Object> mapParametros);
                
    @Select(value =
            "{call #{nRetorno, mode=OUT, jdbcType=NUMERIC} := " + "FARMA_LEALTAD.FN_OBTENER_PORC_DESC_CONV(" +
            " #{PI_COGCIA}," + " #{PI_CODLOC}," + 
            " #{PI_CODCON}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    void obtenerPorcentajeDescuentoConvenio(Map<String, Object> mapParametros);
    // lais X+1
    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + 
            "PKG_PROM_ACUMULA.IND_IMPR_VOUCHER_X1}")
    @Options(statementType = StatementType.CALLABLE)
    void indImpresionVoucherX1(Map<String, Object> mapParametros);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_PARAMETROS_VENTA(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getParametrosVenta(HashMap<String, Object> object);

    @Select(value =
            "{call FARMA_LEALTAD.GET_ACTUALIZAR_VENTA(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in},"+ "   #{vIdTransaccion_in},"+ "   #{vNumAutorizacion_in},"+ "   #{vIdUsu_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getActualizarVenta(HashMap<String, Object> object);

    @Select(value =
            "{call FARMA_LEALTAD.GET_ELIMINA_BONIFICA(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void eliminaProdBonificacion(HashMap<String, Object> object);

    @Select(value =
            "{call FARMA_LEALTAD.GET_DESCARTAR_PEDIDO(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void descartarPedido(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_SALDO_PUNTOS(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getSaldoPuntos(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_MULTIPLO_PTO(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}" +
    "   "+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getMultiploPtos(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_MOSTRAR_PUNTOS(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getIndicadoresPuntos(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_USO_NCR(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}, #{cTipoBusqueda_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void verificaUsoNCR(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_FECHA_NCR(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}, #{cFechaNCR_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void verificaFechaNCR(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_CREDITO_NCR(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}, #{cFechaNCR_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void verificaCreditoNCR(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "FARMA_LEALTAD.GET_MONTO_NCR(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getMontoNCR(HashMap<String, Object> object);

    @Select(value =
            "{call PTOVENTA.FARMA_LEALTAD.REGISTRA_INSCRIPCION_TURNO(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cSecMovCaja_in}, #{vCodTarjeta_in}, #{cIdUsu_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void registrarInscripcionTurno(HashMap<String, Object> object);

    @Select(value =
            "{call #{vRetorno, mode=OUT, jdbcType=VARCHAR} := " + "PTOVENTA.FARMA_LEALTAD.GET_INSCRIPCION_TURNO(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cSecMovCaja_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getInscripcionTurno(HashMap<String, Object> object);
    
    @Select(value =
            "{call PTOVENTA.FARMA_PUNTOS.P_RECHAZO_AFILIACION_PTOS(" +
    "   #{cCodGrupoCia_in}," + "   #{cNumDocumento_in}," + "   #{cNumTarjeta_in}," +
    "   #{cSecMovCaja_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void rechazarIncripcionPuntos(HashMap<String, Object> object);
    // lais X+1
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.GET_PROD_PROG_XMAS1_PEDIDO(" +
    "   #{cCodGrupoCia_in}," + " #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void getProductoProgramaXmas1Pedido(HashMap<String, Object> object);
    
    @Select(value =
                "{call PTOVENTA.PKG_PROM_ACUMULA.P_ELIMINAR_PROG_INS_CLI(" +
    "   #{PI_DNICLI}," +
    "   #{PI_COPROG}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void eliminarProgramaInscripcionAux(HashMap<String, Object> object);

    @Select(value =
            "{call " + "#{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PKG_PROM_ACUMULA.FN_OBTENER_PROD_PROG_INS_COMUN(" +
            "   #{PI_COPROG}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerProductosDeProgramasInsComun(HashMap<String, Object> object);
	// lais X+1

    @Select(value =
            "{call FARMA_LEALTAD.IS_PERMITE_VTA_OFFLINE(" +
    "   #{cCodGrupoCia_in}," + "   #{cCodCia_in}," + "   #{cCodLocal_in}," +
    "   #{cNumPedVta_in}"+ ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void isPermiteVtaOffline(HashMap<String, Object> object);    
    
}
