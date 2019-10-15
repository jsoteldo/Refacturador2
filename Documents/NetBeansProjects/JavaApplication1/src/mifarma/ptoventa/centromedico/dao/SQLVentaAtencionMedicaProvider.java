package mifarma.ptoventa.centromedico.dao;

import org.apache.ibatis.jdbc.SQL;


/**
 * Proveedor de sentencias
 * @author ERIOS
 * @since 24.08.2016
 */
public class SQLVentaAtencionMedicaProvider extends SQLUtilProvider{
    public SQLVentaAtencionMedicaProvider() {
        super();
    }

    public String selectPedidoCabLocal() {
        return selectTablaNumPedido("VTA_PEDIDO_ATENCION_MEDICA");
    }
    
    public String selectCompAtencionMedica() {
        return selectTablaNumPedido("VTA_COMP_ATENCION_MEDICA");
    }

    public String insertPedidoAtencionMedica(){
        SQL sql = new SQL();
        sql.INSERT_INTO("PTOVENTA.VTA_PEDIDO_ATENCION_MEDICA");
        sql.VALUES("cod_grupo_cia", "#{cod_grupo_cia}");
        sql.VALUES("cod_local", "#{cod_local}");
        sql.VALUES("num_ped_vta", "#{num_ped_vta}");        
        sql.VALUES("tip_documento", "#{tip_documento}");
        sql.VALUES("num_documento", "#{num_documento}");
        sql.VALUES("ape_pat", "#{ape_pat}");
        sql.VALUES("ape_mat", "#{ape_mat}");
        sql.VALUES("nombre", "#{nombre}");
        sql.VALUES("estado", "#{estado}");
        sql.VALUES("fec_crea", "#{fec_crea}");
        sql.VALUES("usu_crea", "#{usu_crea}");
        sql.VALUES("fec_mod", "#{fec_mod}");
        sql.VALUES("usu_mod", "#{usu_mod}");
        return sql.toString();
    }
    
    public String insertCompAtencionMedica(){
        SQL sql = new SQL() {{
                INSERT_INTO("PTOVENTA.VTA_COMP_ATENCION_MEDICA");
                VALUES("cod_grupo_cia", "#{cod_grupo_cia}");
                VALUES("cod_local", "#{cod_local}");
                VALUES("num_ped_vta", "#{num_ped_vta}");
                VALUES("estado", "#{estado}");
                VALUES("fec_crea", "#{fec_crea}");
                VALUES("usu_crea", "#{usu_crea}");
                VALUES("fec_mod", "#{fec_mod}");
                VALUES("usu_mod", "#{usu_mod}");
                VALUES("tip_comp_pago", "#{tip_comp_pago}");
                VALUES("num_comp_pago", "#{num_comp_pago}");
            }};
        return sql.toString();
    }
    
    public String insertPedRecetaCab(){
        SQL sql = new SQL() {{
                INSERT_INTO("PTOVENTA.VTA_PED_RECETA_CAB");
                VALUES("cod_grupo_cia", "#{cod_grupo_cia}");
                VALUES("cod_local", "#{cod_local}");
                VALUES("num_ped_rec", "#{num_ped_rec}");
                VALUES("cdg_apm", "#{cdg_apm}");
                VALUES("matricula", "#{matricula}");
                VALUES("fec_ped_rec", "#{fec_ped_rec}");
                VALUES("val_bruto_ped_rec", "#{val_bruto_ped_rec}");
                VALUES("val_neto_ped_rec", "#{val_neto_ped_rec}");
                VALUES("val_redondeo_ped_rec", "#{val_redondeo_ped_rec}");
                VALUES("val_igv_ped_rec", "#{val_igv_ped_rec}");
                VALUES("val_dcto_ped_rec", "#{val_dcto_ped_rec}");
                VALUES("val_tip_cambio_ped_rec", "#{val_tip_cambio_ped_rec}");
                VALUES("cant_items_ped_rec", "#{cant_items_ped_rec}");
                VALUES("est_ped_rec", "#{est_ped_rec}");
                VALUES("usu_crea_ped_rec_cab", "#{usu_crea_ped_rec_cab}");
                VALUES("fec_crea_ped_rec_cab", "#{fec_crea_ped_rec_cab}");
                VALUES("usu_mod_ped_rec_cab", "#{usu_mod_ped_rec_cab}");
                VALUES("fec_mod_ped_rec_cab", "#{fec_mod_ped_rec_cab}");
                VALUES("FECHA_VENCIMIENTO", "#{fecha_vencimiento}");
                VALUES("num_cmp", "#{num_cmp}");
                VALUES("des_nom_medico", "#{des_nom_medico}");
                VALUES("des_ape_medico", "#{des_ape_medico}");
                VALUES("cod_medico", "#{cod_medico}");
            }};
    
        return sql.toString();
    }
    
    public String insertPedRecetaDet(){
        SQL sql = new SQL() {{
                INSERT_INTO("PTOVENTA.VTA_PED_RECETA_DET");
                VALUES("cod_grupo_cia", "#{cod_grupo_cia}");
                VALUES("cod_local", "#{cod_local}");
                VALUES("num_ped_rec", "#{num_ped_rec}");
                VALUES("sec_ped_rec_det", "#{sec_ped_rec_det}");
                VALUES("cod_prod", "#{cod_prod}");
                VALUES("cant_atendida", "#{cant_atendida}");
                VALUES("val_prec_rec", "#{val_prec_rec}");
                VALUES("val_prec_total", "#{val_prec_total}");
                VALUES("porc_dcto_1", "#{porc_dcto_1}");
                VALUES("porc_dcto_2", "#{porc_dcto_2}");
                VALUES("porc_dcto_3", "#{porc_dcto_3}");
                VALUES("porc_dcto_total", "#{porc_dcto_total}");
                VALUES("est_ped_rec_det", "#{est_ped_rec_det}");
                VALUES("val_frac", "#{val_frac}");
                VALUES("usu_crea_ped_rec_det", "#{usu_crea_ped_rec_det}");
                VALUES("fec_crea_ped_rec_det", "#{fec_crea_ped_rec_det}");
                VALUES("usu_mod_ped_rec_det", "#{usu_mod_ped_rec_det}");
                VALUES("fec_mod_ped_rec_det", "#{fec_mod_ped_rec_det}");
                VALUES("val_prec_lista", "#{val_prec_lista}");
                VALUES("val_igv", "#{val_igv}");
                VALUES("unid_vta", "#{unid_vta}");
                VALUES("ind_exonerado_igv", "#{ind_exonerado_igv}");
                VALUES("stk_fisico_disp", "#{stk_fisico_disp}");                
                VALUES("frecuencia_toma", "#{frecuencia_toma}");
                VALUES("duracion_toma", "#{duracion_toma}");
                VALUES("cod_maes_det", "#{cod_maes_det}");
                VALUES("dosis_toma", "#{dosis_toma}");
            }};
        return sql.toString();
    }
    
    public String mergePedRecetaPedidoDet(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.VTA_PED_RECETA_PEDIDO_DET M USING DUAL ON (M.cod_grupo_cia = #{cod_grupo_cia}");
        strBuilder.append(" AND M.cod_local = #{cod_local}");
        strBuilder.append(" AND M.num_ped_rec = #{num_ped_rec}");
        strBuilder.append(" AND M.cod_local_vta = #{cod_local_vta}");
        strBuilder.append(" AND M.num_ped_vta = #{num_ped_vta}");
        strBuilder.append(" AND M.sec_ped_vta_det = #{sec_ped_vta_det})");
        strBuilder.append(" WHEN MATCHED THEN");
        strBuilder.append(" UPDATE SET");
        strBuilder.append(" M.cod_prod = #{cod_prod},");
        strBuilder.append(" M.cant_atendida = #{cant_atendida},");
        strBuilder.append(" M.val_frac = #{val_frac},");
        strBuilder.append(" M.estado = #{estado},");
        strBuilder.append(" M.usu_crea = #{usu_crea},");
        strBuilder.append(" M.fec_crea = #{fec_crea},");
        strBuilder.append(" M.usu_mod = #{usu_mod},");
        strBuilder.append(" M.fec_mod = #{fec_mod},");
        strBuilder.append(" M.unid_vta = #{unid_vta}");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(cod_grupo_cia,cod_local,num_ped_rec,cod_local_vta,num_ped_vta,sec_ped_vta_det," +
            "cod_prod,cant_atendida,val_frac," +
            "estado,usu_crea,fec_crea,usu_mod,fec_mod," +
                          "unid_vta)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_local},#{num_ped_rec},#{cod_local_vta},#{num_ped_vta},#{sec_ped_vta_det}," +
            "#{cod_prod},#{cant_atendida},#{val_frac}," +
            "#{estado},#{usu_crea},#{fec_crea},#{usu_mod},#{fec_mod}," +
                          "#{unid_vta})");
        
        return strBuilder.toString();
    }
    
    public String selectPedidoRecetaCab() {
        return selectTablaNumReceta("VTA_PED_RECETA_CAB");
    }
    
    public String selectPedidoRecetaDet() {
        return selectTablaNumReceta("VTA_PED_RECETA_DET");
    }
    
    public String selectRecetaPedidoDet() {
        return selectTablaNumReceta("VTA_PED_RECETA_PEDIDO_DET");
    }
    
    public String selectPaciente(){
        return "SELECT * FROM PTOVENTA.CME_PACIENTE WHERE COD_GRUPO_CIA = #{cCodGrupoCia} AND COD_PACIENTE = #{cCodPaciente}";
    }
    
    public String selectHistoriaClinica(){
        return "SELECT * FROM PTOVENTA.CME_HISTORIA_CLINICA WHERE COD_GRUPO_CIA = #{cCodGrupoCia} AND COD_PACIENTE = #{cCodPaciente}";
    }
    
    public String mergePaciente(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_PACIENTE M USING DUAL ON (M.cod_grupo_cia = #{cod_grupo_cia}");
        strBuilder.append(" AND M.cod_paciente = #{cod_paciente}");
        strBuilder.append(" )");
        strBuilder.append(" WHEN MATCHED THEN");
        strBuilder.append(" UPDATE SET");
        strBuilder.append(" M.nom_cli = #{nom_cli},");
        strBuilder.append(" M.ape_pat_cli = #{ape_pat_cli},");
        strBuilder.append(" M.ape_mat_cli = #{ape_mat_cli},");
        strBuilder.append(" M.fono_cli = #{fono_cli},");
        strBuilder.append(" M.sexo_cli = #{sexo_cli},");
        strBuilder.append(" M.dir_cli = #{dir_cli},");
        strBuilder.append(" M.fec_nac_cli = #{fec_nac_cli},");
        strBuilder.append(" M.fec_crea_cliente = #{fec_crea_cliente},");
        strBuilder.append(" M.usu_crea_cliente = #{usu_crea_cliente},");
        strBuilder.append(" M.fec_mod_cliente = #{fec_mod_cliente},");
        strBuilder.append(" M.usu_mod_cliente = #{usu_mod_cliente},");
        strBuilder.append(" M.ind_estado = #{ind_estado},");
        strBuilder.append(" M.email = #{email},");
        strBuilder.append(" M.cell_cli = #{cell_cli},");
        strBuilder.append(" M.cod_tip_documento = #{cod_tip_documento},");
        strBuilder.append(" M.num_documento = #{num_documento},");
        strBuilder.append(" M.id_usu_confir = #{id_usu_confir},");
        strBuilder.append(" M.cod_local_confir = #{cod_local_confir},");
        strBuilder.append(" M.ip_confir = #{ip_confir},");
        strBuilder.append(" M.departamento = #{departamento},");
        strBuilder.append(" M.provincia = #{provincia},");
        strBuilder.append(" M.distrito = #{distrito},");
        strBuilder.append(" M.tipo_direccion = #{tipo_direccion},");
        strBuilder.append(" M.tipo_lugar = #{tipo_lugar},");
        strBuilder.append(" M.referencias = #{referencias},");
        strBuilder.append(" M.ESTADO_CIVIL = #{estado_civil},");
        strBuilder.append(" M.GRADO_INSTRUCCION = #{grado_instruccion},");
        strBuilder.append(" M.OCUPACION = #{ocupacion},");
        strBuilder.append(" M.CENTRO_EDU_LAB = #{centro_edu_lab},");
        strBuilder.append(" M.SEC_ANTECEDENTE_HC = #{sec_antecedente_hc},");
        strBuilder.append(" M.COD_LOCAL_ANTECEDENTE_HC  = #{cod_local_antecedente_hc},");
        strBuilder.append(" M.DEP_UBIGEO  = #{dep_ubigeo},");
        strBuilder.append(" M.PRV_UBIGEO  = #{prv_ubigeo},");
        strBuilder.append(" M.DIS_UBIGEO  = #{dis_ubigeo},");
        strBuilder.append(" M.DEP_LUG_NAC  = #{dep_lug_nac},");
        strBuilder.append(" M.PRV_LUG_NAC  = #{prv_lug_nac},");
        strBuilder.append(" M.DIS_LUG_NAC  = #{dis_lug_nac},");
        strBuilder.append(" M.DEP_LUG_PRO  = #{dep_lug_pro},");
        strBuilder.append(" M.PRV_LUG_PRO  = #{prv_lug_pro},");
        strBuilder.append(" M.DIS_LUG_PRO = #{dis_lug_pro}");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(cod_grupo_cia,cod_paciente,nom_cli,ape_pat_cli,ape_mat_cli,fono_cli,sexo_cli,dir_cli," +
            "fec_nac_cli,fec_crea_cliente,usu_crea_cliente,fec_mod_cliente,usu_mod_cliente,ind_estado,email,cell_cli," +
            "cod_tip_documento,num_documento,id_usu_confir,cod_local_confir,ip_confir,departamento,provincia,distrito," +
            "tipo_direccion,tipo_lugar,referencias," +
            "ESTADO_CIVIL,GRADO_INSTRUCCION,OCUPACION,CENTRO_EDU_LAB," +
            "SEC_ANTECEDENTE_HC,COD_LOCAL_ANTECEDENTE_HC," +
            "DEP_UBIGEO, PRV_UBIGEO, DIS_UBIGEO, DEP_LUG_NAC, " +
            "PRV_LUG_NAC, DIS_LUG_NAC, DEP_LUG_PRO, PRV_LUG_PRO, DIS_LUG_PRO)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_paciente},#{nom_cli},#{ape_pat_cli},#{ape_mat_cli},#{fono_cli},#{sexo_cli},#{dir_cli}," +
            "#{fec_nac_cli},#{fec_crea_cliente},#{usu_crea_cliente},#{fec_mod_cliente},#{usu_mod_cliente},#{ind_estado},#{email},#{cell_cli}," +
            "#{cod_tip_documento},#{num_documento},#{id_usu_confir},#{cod_local_confir},#{ip_confir},#{departamento},#{provincia},#{distrito}," +
            "#{tipo_direccion},#{tipo_lugar},#{referencias}," +
            "#{estado_civil},#{grado_instruccion},#{ocupacion},#{centro_edu_lab}," +
            "#{sec_antecedente_hc},#{cod_local_antecedente_hc}," +
            "#{dep_ubigeo},#{prv_ubigeo},#{dis_ubigeo},#{dep_lug_nac},#{prv_lug_nac},#{dis_lug_nac},#{dep_lug_pro},#{prv_lug_pro},#{dis_lug_pro}" +
                          ")");
        
        return strBuilder.toString();
    }
    

    
    public String mergePaciente2(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_PACIENTE M USING DUAL ON (M.cod_grupo_cia = #{cod_grupo_cia}");
        strBuilder.append(" AND M.cod_paciente = #{cod_paciente}");
        strBuilder.append(" )");
        strBuilder.append(" WHEN MATCHED THEN");
        strBuilder.append(" UPDATE SET");
        strBuilder.append(" M.nom_cli = #{nom_cli},");
        strBuilder.append(" M.ape_pat_cli = #{ape_pat_cli},");
        strBuilder.append(" M.ape_mat_cli = #{ape_mat_cli},");
        strBuilder.append(" M.fono_cli = #{fono_cli},");
        strBuilder.append(" M.sexo_cli = #{sexo_cli},");
        strBuilder.append(" M.dir_cli = #{dir_cli},");
        strBuilder.append(" M.fec_nac_cli = #{fec_nac_cli},");
        strBuilder.append(" M.fec_crea_cliente = #{fec_crea_cliente},");
        strBuilder.append(" M.usu_crea_cliente = #{usu_crea_cliente},");
        strBuilder.append(" M.fec_mod_cliente = #{fec_mod_cliente},");
        strBuilder.append(" M.usu_mod_cliente = #{usu_mod_cliente},");
        strBuilder.append(" M.ind_estado = #{ind_estado},");
        strBuilder.append(" M.email = #{email},");
        strBuilder.append(" M.cell_cli = #{cell_cli},");
        strBuilder.append(" M.cod_tip_documento = #{cod_tip_documento},");
        strBuilder.append(" M.num_documento = #{num_documento},");
        strBuilder.append(" M.id_usu_confir = #{id_usu_confir},");
        strBuilder.append(" M.cod_local_confir = #{cod_local_confir},");
        strBuilder.append(" M.ip_confir = #{ip_confir},");
        strBuilder.append(" M.departamento = #{departamento},");
        strBuilder.append(" M.provincia = #{provincia},");
        strBuilder.append(" M.distrito = #{distrito},");
        strBuilder.append(" M.tipo_direccion = #{tipo_direccion},");
        strBuilder.append(" M.tipo_lugar = #{tipo_lugar},");
        strBuilder.append(" M.referencias = #{referencias},");
        strBuilder.append(" M.ESTADO_CIVIL = #{estado_civil},");
        strBuilder.append(" M.GRADO_INSTRUCCION = #{grado_instruccion},");
        strBuilder.append(" M.OCUPACION = #{ocupacion},");
        strBuilder.append(" M.CENTRO_EDU_LAB = #{centro_edu_lab},");
        strBuilder.append(" M.DEP_UBIGEO  = #{dep_ubigeo},");
        strBuilder.append(" M.PRV_UBIGEO  = #{prv_ubigeo},");
        strBuilder.append(" M.DIS_UBIGEO  = #{dis_ubigeo},");
        strBuilder.append(" M.DEP_LUG_NAC  = #{dep_lug_nac},");
        strBuilder.append(" M.PRV_LUG_NAC  = #{prv_lug_nac},");
        strBuilder.append(" M.DIS_LUG_NAC  = #{dis_lug_nac},");
        strBuilder.append(" M.DEP_LUG_PRO  = #{dep_lug_pro},");
        strBuilder.append(" M.PRV_LUG_PRO  = #{prv_lug_pro},");
        strBuilder.append(" M.DIS_LUG_PRO = #{dis_lug_pro}");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(cod_grupo_cia,cod_paciente,nom_cli,ape_pat_cli,ape_mat_cli,fono_cli,sexo_cli,dir_cli," +
            "fec_nac_cli,fec_crea_cliente,usu_crea_cliente,fec_mod_cliente,usu_mod_cliente,ind_estado,email,cell_cli," +
            "cod_tip_documento,num_documento,id_usu_confir,cod_local_confir,ip_confir,departamento,provincia,distrito," +
            "tipo_direccion,tipo_lugar,referencias," +
            "ESTADO_CIVIL,GRADO_INSTRUCCION,OCUPACION,CENTRO_EDU_LAB," +
            "SEC_ANTECEDENTE_HC,COD_LOCAL_ANTECEDENTE_HC," +
            "DEP_UBIGEO, PRV_UBIGEO, DIS_UBIGEO, DEP_LUG_NAC, " +
            "PRV_LUG_NAC, DIS_LUG_NAC, DEP_LUG_PRO, PRV_LUG_PRO, DIS_LUG_PRO)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_paciente},#{nom_cli},#{ape_pat_cli},#{ape_mat_cli},#{fono_cli},#{sexo_cli},#{dir_cli}," +
            "#{fec_nac_cli},#{fec_crea_cliente},#{usu_crea_cliente},#{fec_mod_cliente},#{usu_mod_cliente},#{ind_estado},#{email},#{cell_cli}," +
            "#{cod_tip_documento},#{num_documento},#{id_usu_confir},#{cod_local_confir},#{ip_confir},#{departamento},#{provincia},#{distrito}," +
            "#{tipo_direccion},#{tipo_lugar},#{referencias}," +
            "#{estado_civil},#{grado_instruccion},#{ocupacion},#{centro_edu_lab}," +
            "#{sec_antecedente_hc},#{cod_local_antecedente_hc}," +
            "#{dep_ubigeo},#{prv_ubigeo},#{dis_ubigeo},#{dep_lug_nac},#{prv_lug_nac},#{dis_lug_nac},#{dep_lug_pro},#{prv_lug_pro},#{dis_lug_pro}" +
                          ")");
        
        return strBuilder.toString();
    }
    
    public String mergeHistoriaClinica(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_HISTORIA_CLINICA M USING DUAL ON (M.cod_grupo_cia = #{cod_grupo_cia}");
        strBuilder.append(" AND M.cod_paciente = #{cod_paciente}");
        strBuilder.append(" )");
        strBuilder.append(" WHEN MATCHED THEN");
        strBuilder.append(" UPDATE SET");
        strBuilder.append(" M.grupo_sang = #{grupo_sang},");
        strBuilder.append(" M.factor_rh = #{factor_rh},");
        strBuilder.append(" M.estado = #{estado},");
        strBuilder.append(" M.usu_crea = #{usu_crea},");
        strBuilder.append(" M.fec_crea = #{fec_crea},");
        strBuilder.append(" M.usu_mod = #{usu_mod},");
        strBuilder.append(" M.fec_mod = #{fec_mod},");
        strBuilder.append(" M.FECHA_HC = #{fecha_hc},");
        strBuilder.append(" M.IND_HC_FISICA = #{ind_hc_fisica},");
        strBuilder.append(" M.NRO_HC_FISICA = #{nro_hc_fisica},");
        strBuilder.append(" M.NOM_ACOM = #{nom_acom},");
        strBuilder.append(" M.COD_TIP_DOC_ACOM = #{cod_tip_doc_acom},");
        strBuilder.append(" M.NUM_DOC_ACOM = #{num_doc_acom},");
        strBuilder.append(" M.COD_TIP_ACOM = #{cod_tip_acom},");
        strBuilder.append(" M.NRO_HC_ACTUAL = #{nro_hc_actual}");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_PACIENTE,GRUPO_SANG,FACTOR_RH," +
            "ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD," +
            "FECHA_HC,IND_HC_FISICA,NRO_HC_FISICA,NOM_ACOM,COD_TIP_DOC_ACOM,NUM_DOC_ACOM,COD_TIP_ACOM,NRO_HC_ACTUAL)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_paciente},#{grupo_sang},#{factor_rh}," +
            "#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod}," +
            "#{fecha_hc},#{ind_hc_fisica},#{nro_hc_fisica},#{nom_acom},#{cod_tip_doc_acom},#{num_doc_acom},#{cod_tip_acom},#{nro_hc_actual})");
        
        return strBuilder.toString();
    }
    
    public String selectEstCompAtencion() {
        return selectTablaNumPedido("HIST_COMP_ATENCION_MEDICA");
    }
    
    public String mergeAntecedentes(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_HC_ANTECEDENTES M USING DUAL ON (");
        strBuilder.append(" M.cod_grupo_cia = #{codGrupoCia}");
        strBuilder.append(" AND M.cod_paciente = #{codPaciente}");
        strBuilder.append(" AND M.COD_LOCAL = #{codLocal}");        
        strBuilder.append(" AND M.SEC_HC_ANTECEDENTES = #{secuencialHC}");
        strBuilder.append(" )");
        strBuilder.append(" WHEN MATCHED THEN");
        strBuilder.append(" UPDATE SET");
        strBuilder.append(" M.MEDICAMENTOS = #{medicamento},");
        strBuilder.append(" M.RAM = #{ram},");
        strBuilder.append(" M.OCUPACIONALES = #{ocupacionales},");
        strBuilder.append(" M.estado = #{estado},");
        strBuilder.append(" M.usu_crea = #{usu_crea},");
        strBuilder.append(" M.fec_crea = #{fec_crea},");
        strBuilder.append(" M.usu_mod = #{usu_mod},");
        strBuilder.append(" M.fec_mod = #{fec_mod}");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(cod_grupo_cia,cod_paciente,SEC_HC_ANTECEDENTES," +
            "MEDICAMENTOS,RAM,OCUPACIONALES," +
            "estado,usu_crea,fec_crea,usu_mod,fec_mod,COD_LOCAL)");
        strBuilder.append(" VALUES(#{codGrupoCia},#{codPaciente},#{secuencialHC}," +
            "#{medicamento},#{ram},#{ocupacionales}," +
            "#{estado},#{usu_crea},#{fec_crea},#{usu_mod},#{fec_mod},#{codLocal})");
        
        return strBuilder.toString();
    }
    
    public String mergeAntecedentesGineco(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_HC_ANTECEDENTES_GINECO M USING DUAL ON (");
        strBuilder.append(" M.cod_grupo_cia = #{codGrupoCia}");
        strBuilder.append(" AND M.cod_paciente = #{codPaciente}");
        strBuilder.append(" AND M.COD_LOCAL = #{codLocal}");
        strBuilder.append(" AND M.SEC_HC_ANTECEDENTES = #{secuencialHC}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(cod_grupo_cia,cod_paciente,SEC_HC_ANTECEDENTES," +
            "EDAD_MENARQUIA,RC_MENSTRUACION,RC_CICLO_MENSTRUAL,FUR,FPP,RS,DISMINORREA,GESTACIONES," +
            "PARTOS,FUP,CESAREAS,PAP,MAMOGRAFIA,MAC,OTROS,COD_LOCAL)");
        strBuilder.append(" VALUES(#{codGrupoCia},#{codPaciente},#{secuencialHC}," +
            "#{edadMenarquia},#{rcMenstruacion},#{rcCicloMenstrual},#{fur},#{fpp},#{rs},#{disminorrea},#{nroGestaciones}," +
            "#{nroPartos},#{fup},#{nroCesareas},#{pap},#{mamografia},#{mac},#{otros},#{codLocal})");
        
        return strBuilder.toString();
    }
    
    public String mergeAntecedentesDetalle(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_HC_ANTECEDENTE_MAE_DETALLE M USING DUAL ON (");
        strBuilder.append(" M.cod_grupo_cia = #{codGrupoCia}");
        strBuilder.append(" AND M.cod_paciente = #{codPaciente}");
        strBuilder.append(" AND M.COD_LOCAL = #{codLocal}");
        strBuilder.append(" AND M.SEC_HC_ANTECEDENTES = #{secuencialHC}");
        strBuilder.append(" AND M.COD_MAES_DET = #{codMaestroDetalle}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(cod_grupo_cia,cod_paciente,SEC_HC_ANTECEDENTES," +
            "COD_MAES_DET,IND_TIPO,DESC_OTROS,COD_LOCAL)");
        strBuilder.append(" VALUES(#{codGrupoCia},#{codPaciente},#{secuencialHC}," +
            "#{codMaestroDetalle},#{tipoMaeDatalle},#{descripcionOtros},#{codLocal})");
        
        return strBuilder.toString();
    }
    
    public String mergeAntecedentesPatolo(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_HC_ANTECEDENTE_PATOLO_DIAG M USING DUAL ON (");
        strBuilder.append(" M.cod_grupo_cia = #{codGrupoCia}");
        strBuilder.append(" AND M.COD_LOCAL = #{codLocal}");
        strBuilder.append(" AND M.cod_paciente = #{codPaciente}");
        strBuilder.append(" AND M.SEC_HC_ANTECEDENTES = #{secuencialHC}");
        strBuilder.append(" AND M.COD_DIAGNOSTICO = #{codDiagnostico}");
        strBuilder.append(" AND M.IND_TIPO = #{tipoPatologico}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(cod_grupo_cia,cod_paciente,SEC_HC_ANTECEDENTES," +
            "COD_DIAGNOSTICO,IND_TIPO,DESC_PARIENTES,COD_LOCAL)");
        strBuilder.append(" VALUES(#{codGrupoCia},#{codPaciente},#{secuencialHC}," +
            "#{codDiagnostico},#{tipoPatologico},#{descripcionPariente},#{codLocal})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedica(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,COD_MEDICO,COD_PACIENTE," +
            "ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD,IND_ANULADO,COD_MAES_DET,COD_LOCAL_VTA," +
            "NUM_PED_VTA,COD_TIPO_ATENCION)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{cod_medico},#{cod_paciente}," +
            "#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod},#{ind_anulado},#{cod_maes_det},#{cod_local_vta}," +
            "#{num_ped_vta},#{cod_tipo_atencion})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedicaTriaje(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA_TRI M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,PA_1,PA_2,FR,FC," +
            "TEMP,PESO,TALLA,ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{pa_1},#{pa_2},#{fr},#{fc}," +
            "#{temp},#{peso},#{talla},#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedicaEnfermedad(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA_ENF_ACT M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,MOTIVO_CONSULTA," +
            "TIPO_INFORMANTE,TIEMPO_ENFERMEDAD,FORMA_INICIO,SIGNOS,SINTOMAS,RELATO_CRONOLOGICO," +
            "TIPO_APETITO,TIPO_SED,TIPO_SUENO,TIPO_ORINA,TIPO_DEPOSICION," +
            "ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{motivo_consulta}," +
            "#{tipo_informante},#{tiempo_enfermedad},#{forma_inicio},#{signos},#{sintomas},#{relato_cronologico}," +
            "#{tipo_apetito},#{tipo_sed},#{tipo_sueno},#{tipo_orina},#{tipo_deposicion}," +
            "#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedicaExamenFi(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA_EX_FI M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,ESTADO_GENERAL," +
            "ESTADO_CONCIENCIA,ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD,EXA_FISICO_DIRIGIDO)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{estado_general}," +
            "#{estado_conciencia},#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod},#{exa_fisico_dirigido})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedicaDiagnostico(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA_DIAG M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" AND M.SECUENCIAL = #{secuencial}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,COD_DIAGNOSTICO," +
            "ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD,TIPO_DIAGNOSTICO,SECUENCIAL)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{cod_diagnostico}," +
            "#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod},#{tipo_diagnostico},#{secuencial})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedicaTratamiento(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA_TRA M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" AND M.NUM_PED_REC = #{num_ped_rec}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,NUM_PED_REC," +
            "VALIDEZ_RECETA,INDICACIONES_GENERALES,ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{num_ped_rec}," +
            "#{validez_receta},#{indicaciones_generales},#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedicaExamenes(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA_EXA M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,IMAGENES," +
            "ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD,LABORATORIOS)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{imagenes}," +
            "#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod},#{laboratorios})");
        
        return strBuilder.toString();
    }
    
    public String mergeAtencionMedicaProcedimientos(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.CME_ATENCION_MEDICA_PRO M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_CIA = #{cod_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_ATEN_MED = #{num_aten_med}");
        strBuilder.append(" )");
        //strBuilder.append(" WHEN MATCHED THEN");
        //strBuilder.append(" UPDATE SET");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_CIA,COD_LOCAL,NUM_ATEN_MED,PROCEDIMIENTO," +
            "INTERCONSULTA,TRANSFERENCIA,DESCANSO_MEDICO_INI,DESCANSO_MEDICO_FIN,PROXIMA_CITA," +
            "ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_cia},#{cod_local},#{num_aten_med},#{procedimiento}," +
            "#{interconsulta},#{transferencia},#{descanso_medico_ini},#{descanso_medico_fin},#{proxima_cita}," +
            "#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod})");
        
        return strBuilder.toString();
    }
    
    public String mergeEstCompAtencion(){
        StringBuilder strBuilder = new StringBuilder();
        
        strBuilder.append(" MERGE INTO PTOVENTA.HIST_COMP_ATENCION_MEDICA M USING DUAL ON (1=1");
        strBuilder.append(" AND M.COD_GRUPO_CIA = #{cod_grupo_cia}");
        strBuilder.append(" AND M.COD_LOCAL = #{cod_local}");
        strBuilder.append(" AND M.NUM_PED_VTA = #{num_ped_vta}");
        strBuilder.append(" AND M.SEC_HIST_MED = #{sec_hist_med}");
        strBuilder.append(" )");
        strBuilder.append(" WHEN MATCHED THEN");
        strBuilder.append(" UPDATE SET");
        strBuilder.append(" M.FEC_HIST_MED = #{fec_hist_med},");
        strBuilder.append(" M.COD_CIA = #{cod_cia},");
        strBuilder.append(" M.COD_LOCAL_CME = #{cod_local_cme},");
        strBuilder.append(" M.NUM_ATEN_MED = #{num_aten_med},");
        strBuilder.append(" M.ESTADO = #{estado},");
        strBuilder.append(" M.FEC_CREA = #{fec_crea},");
        strBuilder.append(" M.USU_CREA = #{usu_crea},");
        strBuilder.append(" M.FEC_MOD = #{fec_mod},");
        strBuilder.append(" M.USU_MOD = #{usu_mod}");
        strBuilder.append(" WHEN NOT MATCHED THEN");
        strBuilder.append(" INSERT(COD_GRUPO_CIA,COD_LOCAL,NUM_PED_VTA,SEC_HIST_MED,FEC_HIST_MED," +
            "COD_CIA,COD_LOCAL_CME,NUM_ATEN_MED,ESTADO,FEC_CREA,USU_CREA,FEC_MOD,USU_MOD)");
        strBuilder.append(" VALUES(#{cod_grupo_cia},#{cod_local},#{num_ped_vta},#{sec_hist_med},#{fec_hist_med}," +
            "#{cod_cia},#{cod_local_cme},#{num_aten_med},#{estado},#{fec_crea},#{usu_crea},#{fec_mod},#{usu_mod})");
        
        return strBuilder.toString();
    }
}
