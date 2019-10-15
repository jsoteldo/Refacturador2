package mifarma.ptoventa.centromedico.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface MapperAtencionMedica {

    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PTOVENTA.CENTRO_MEDICO.F_LISTA_ESPERA("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodEstado_in}," + 
            "#{cCodMedico_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerListaEspera(HashMap<String, Object> object);
    
    @Select(value =
            "{call PTOVENTA.CENTRO_MEDICO.F_UPDATE_SOLICITUD_ATENCION(" +
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," + 
            "#{cNumAtencion_in}," + 
            "#{cCodEstadoNew_in}," + 
            "#{cUsuario_in}" +            
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void actualizarSolicutd(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + 
            "PTOVENTA.CENTRO_MEDICO.VTA_LISTA_PROD_RECETA("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerListaProducto(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=paciente} := " + "PTOVENTA.CENTRO_MEDICO.F_OBTENER_DATOS_PACIENTE("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodPaciente_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void buscarPaciente(HashMap<String, Object> object);
    
    @Select(value =
            "{call " + 
            "PTOVENTA.CENTRO_MEDICO.P_GRABA_RECETA_CABECERA(" +
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," + 
            "#{cNumReceta, mode=OUT, jdbcType=CHAR}," + 
            "#{cCantItems_in}," + 
            "#{cFechaVigencia_in}," + 
            "#{cUsuCrea_in}," + 
            "#{cCodMedico_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarRecetaCabecera(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABA_RECETA_DETALLE(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNumPedRec_in}," + 
                "#{cSecuencia_in}," + 
                "#{cCodProd_in}," + 
                "#{cCantidad_in}," + 
                "#{cValFrac_in}," + 
                "#{cUnidVta_in}," + 
                "#{cFrecuencia_in}," +
                "#{cDuracion_in}," +
                "#{cViaAdministracion_in}," +
                "#{cDosis_in}," +
                "#{cUsuario_in}" +
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarRecetaDetalle(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_TRATAMIENTO(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNroAtencion_in}," + 
                "#{cNumPedRec_in}," + 
                "#{cValidezReceta_in}," + 
                "#{cIndicacionesGen_in}," + 
                "#{cUsuario_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAtencionMedicaTratamiento(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_ENFER_ACTUAL(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNroAtencion_in}," + 
                "#{cMotivoConsulta_in}," + 
                "#{cTipoInformante_in}," + 
                "#{cTiempoEnfermedad_in}," + 
                "#{cFormaInicio_in}," + 
                "#{cSignos_in}," + 
                "#{cSintomas_in}," + 
                "#{cRelatoCronologico_in}," + 
                "#{cTipoApetito_in}," + 
                "#{cTipoSed_in}," + 
                "#{cTipoSueno_in}," + 
                "#{cTipoOrina_in}," + 
                "#{cTipoDeposicion_in}," + 
                "#{cUsuCrea_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAtencionMedicaEnfermedadActual(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_TRIAJE(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNroAtencion_in}," + 
                "#{cPA_1_in}," + 
                "#{cPA_2_in}," + 
                "#{cFR_in}," + 
                "#{cFC_in}," + 
                "#{cTemp_in}," + 
                "#{cPeso_in}," + 
                "#{cTalla_in}," + 
                "#{cUsuCrea_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAtencionMedicaTriaje(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_EXAMEN_FISICO(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNroAtencion_in}," + 
                "#{cEstadoGeneral_in}," + 
                "#{cEstadoConciencia_in}," + 
                "#{cExaFisicoDirigido_in}," + 
                "#{cUsuCrea_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAtencionMedicaExaFisico(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_DIAGNOSTICO(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNroAtencion_in}," + 
                "#{cSecuencial_in}," +
                "#{cCodDiagnostico_in}," + 
                "#{cTipoDiagnostico_in}," + 
                "#{cUsuCrea_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAtencionMedicaDiagnostico(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_EXAM_AUXILIA(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNroAtencion_in}," + 
                "#{cLaboratio_in}," + 
                "#{cImagenes_in}," + 
                "#{cUsuario_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAtencionMedicaExaAuxiliares(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_AT_MED_OTROS(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," + 
                "#{cNroAtencion_in}," + 
                "#{cProcedimiento_in}," + 
                "#{cInterconsulta_in}," + 
                "#{cTransferencia_in}," + 
                "#{cDescansoFisicoInicio_in}," + 
                "#{cDescansoFisicoFin_in}," + 
                "#{cProximaCita_in}," + 
                "#{cUsuario_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAtencionMedicaOtros(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=atencionMedica} := " + "PTOVENTA.CENTRO_MEDICO.F_OBTENER_ATENCION_MEDICA("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodCia_in}," + 
            "#{cCodLocal_in}," + 
            "#{cNroAtencion_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerAtencionMedica(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.PTOVENTA_CME_ADM.F_OBTENER_NRO_ATENCION_MED("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodCia_in}," + 
            "#{cCodLocal_in}," + 
            "#{codPaciente}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerNroAtencionMedica(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=opcionComboCheck} := " + "PTOVENTA.CENTRO_MEDICO.F_LST_CMB_CHECK_MAESTRO("+
            "#{cCodMaestro_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerListaComboCheckBox(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=hcAntecedente} := " + "PTOVENTA.CENTRO_MEDICO.F_OBTENER_ANTECEDENTE_HC("+
            "#{cCodGrupoCia_in}," +
            "#{cCodLocal_in}," +
            "#{cCodPaciente_in}," + 
            "#{cSecuenciaHC_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerAntecedenteHC(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTE_HC_PATOLOGICO(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," +
                "#{cCodPaciente_in}," + 
                "#{cSecuenciaHC_in}," + 
                "#{cCodDiagnostico_in}," + 
                "#{cIndTipo_in}," + 
                "#{cDescPariente_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAntecedenteHCPatologicos(HashMap<String, Object> object);
    
    @Select(value =
                "{call " + 
                "PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTE_HC_FISIOLOGICO(" +
                "#{cCodGrupoCia_in}," + 
                "#{cCodLocal_in}," +
                "#{cCodPaciente_in}," + 
                "#{cSecuenciaHC_in}," + 
                "#{cCodMaestroDet_in}," + 
                "#{cIndTipo_in}," + 
                "#{cDescOtros_in}" + 
                ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAntecedenteHCFisiologico(HashMap<String, Object> object);
    
    @Select(value =
            "{call " + 
            "PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTE_HC_GINECOLOGICO(" +
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," +
            "#{cCodPaciente_in}," + 
            "#{cSecuenciaHC_in}," + 
            "#{cEdadMenarquia_in}," + 
            "#{cRcMenstruacion_in}," + 
            "#{cCicloMenstruacion_in}," + 
            "#{cFechaFUR_in}," + 
            "#{cFechaFPP_in}," + 
            "#{cRS_in}," + 
            "#{cDisminorrea_in}," + 
            "#{cNroGestaciones_in}," + 
            "#{cParidad_in}," + 
            "#{cFechaFUP_in}," + 
            "#{cNroCesareas_in}," + 
            "#{cPAP_in}," + 
            "#{cMamografia_in}," + 
            "#{cMac_in}," + 
            "#{cOtros_in}," + 
            "#{cIndReglaRegular_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAntecedenteHCGinecologico(HashMap<String, Object> object);
    
    @Select(value =
            "{call " + 
            "PTOVENTA.CENTRO_MEDICO.P_GRABAR_ANTECEDENTES_HC(" +
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," +
            "#{cCodPaciente_in}," + 
            "#{cSecuenciaHC_in, mode=OUT, jdbcType=INTEGER}," + 
            "#{cMedicamentos_in}," + 
            "#{cRam_in}," + 
            "#{cOcupacionales_in}," + 
            "#{cCodMedico_in}," + 
            "#{cUsuario_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void grabarAntecedenteHC(HashMap<String, Object> object);
    
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PTOVENTA.CENTRO_MEDICO.F_GET_PRODUCTOS_RECETA("+
            "#{cCodGrupoCia_in}," +
            "#{cCodProd_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerProductoReceta(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + 
            "PTOVENTA.CENTRO_MEDICO.F_LISTA_HIST_ANTECEDENTES("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodPaciente_in}," + 
            "#{cFechaIni_in}," + 
            "#{cFechaFin_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void listarAntecedentesHCPaciente(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.CENTRO_MEDICO.F_IS_IMPRESION_DIGITAL_RECETA"+
            "}")
    @Options(statementType = StatementType.CALLABLE)
    public void getIndicadorImpresionRecetaDigital(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + "PTOVENTA.PTOVENTA_ADMIN_IMP.F_OBTENER_IMPR_RECETA_DIG("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodLocal_in}," + 
            "#{cIpPC_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerNombreImpresoraRecetaDigital(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=impresion} := " + "PTOVENTA.CENTRO_MEDICO.F_IMPR_VOU_RECETA_MEDICA_CM(" +
            "#{cCodGrupoCia_in}," + 
            "#{cCodCia_in}," + 
            "#{cCodLocal_in}," + 
            "#{cNumAtenMedica_in}"+ 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerFormatoRecetaTermico(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + 
            "PTOVENTA.CENTRO_MEDICO.F_LISTA_HIST_ATEN_MEDICA("+
            "#{cCodGrupoCia_in}," + 
            "#{cCodPaciente_in}," + 
            "#{cFechaInicio_in}," + 
            "#{cFechaFin_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void listarHistoriaAtencionesMedicas(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=INTEGER} := " + 
            "PTOVENTA.CENTRO_MEDICO.F_MAX_DIAS_VALIDEZ_RECETA"+
            "}")
    @Options(statementType = StatementType.CALLABLE)
    public void obtenerCantidaDiasVigenciaReceta(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{resultado, mode=OUT, jdbcType=CHAR} := " + 
            "PTOVENTA.CENTRO_MEDICO.F_VALIDAR_ACCESO_MEDICO("+
            "#{cTipoColegio_in}," +
            "#{cNroCMP}," +
            "#{cNroDoc}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void validarDatosAccesoMedico(HashMap<String, Object> object);
    
    @Select(value =
            "{call #{listado, mode=OUT, jdbcType=CURSOR, resultMap=resultado} := " + "PTOVENTA.CENTRO_MEDICO.F_LST_TIPO_COLEGIO"+
            "}")
    @Options(statementType = StatementType.CALLABLE)
    public void listarTipoColegiatura(HashMap<String, Object> object);
    
    @Select(value =
            "{call " + 
            "PTOVENTA.CENTRO_MEDICO.P_BORRAR_GRABADOS_TEMPORAL(" +
            "#{cCodGrupoCia_in}," + 
            "#{cCodCia_in}," +
            "#{cCodLocal_in}," + 
            "#{cNumAtencion_in}" + 
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    public void borrarAtencionTemporal(HashMap<String, Object> object);
}
