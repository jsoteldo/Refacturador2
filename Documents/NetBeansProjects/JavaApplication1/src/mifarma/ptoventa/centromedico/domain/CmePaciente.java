package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 06.09.2016
 */
public class CmePaciente {
    
    private String cod_grupo_cia = "";
    private String cod_paciente = "";
    private String nom_cli = "";
    private String ape_pat_cli = "";
    private String ape_mat_cli = "";
    private String fono_cli = "";
    private String sexo_cli = "";
    private String dir_cli = "";
    private Date fec_nac_cli;
    private Date fec_crea_cliente;
    private String usu_crea_cliente = "";
    private Date fec_mod_cliente;
    private String usu_mod_cliente = "";
    private String ind_estado = "";
    private String email = "";
    private String cell_cli = "";
    private String cod_tip_documento = "";
    private String num_documento = "";
    private String id_usu_confir = "";
    private String cod_local_confir = "";
    private String ip_confir = "";
    private String departamento = "";
    private String provincia = "";
    private String distrito = "";
    private String tipo_direccion = "";
    private String tipo_lugar = "";
    private String referencias = "";
    //private String lugar_nacimiento = "";
    //private String ubigeo = "";
    private String estado_civil = "";
    //private String lugar_procedencia = "";
    private String grado_instruccion = "";
    private String ocupacion = "";
    private String centro_edu_lab = "";
    //DUBILLUZ 19.09.2016
    private String sec_antecedente_hc="";
    private String cod_local_antecedente_hc ="";
    private String dep_ubigeo ="";
    private String prv_ubigeo ="";
    private String dis_ubigeo ="";
    private String dep_lug_nac ="";
    private String prv_lug_nac ="";
    private String dis_lug_nac ="";
    private String dep_lug_pro ="";
    private String prv_lug_pro ="";
    private String dis_lug_pro="";
    
    

    public void setCod_grupo_cia(String cod_grupo_cia) {
        this.cod_grupo_cia = cod_grupo_cia;
    }

    public String getCod_grupo_cia() {
        return cod_grupo_cia;
    }

    public void setCod_paciente(String cod_paciente) {
        this.cod_paciente = cod_paciente;
    }

    public String getCod_paciente() {
        return cod_paciente;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setApe_pat_cli(String ape_pat_cli) {
        this.ape_pat_cli = ape_pat_cli;
    }

    public String getApe_pat_cli() {
        return ape_pat_cli;
    }

    public void setApe_mat_cli(String ape_mat_cli) {
        this.ape_mat_cli = ape_mat_cli;
    }

    public String getApe_mat_cli() {
        return ape_mat_cli;
    }

    public void setFono_cli(String fono_cli) {
        this.fono_cli = fono_cli;
    }

    public String getFono_cli() {
        return fono_cli;
    }

    public void setSexo_cli(String sexo_cli) {
        this.sexo_cli = sexo_cli;
    }

    public String getSexo_cli() {
        return sexo_cli;
    }

    public void setDir_cli(String dir_cli) {
        this.dir_cli = dir_cli;
    }

    public String getDir_cli() {
        return dir_cli;
    }

    public void setFec_nac_cli(Date fec_nac_cli) {
        this.fec_nac_cli = fec_nac_cli;
    }

    public Date getFec_nac_cli() {
        return fec_nac_cli;
    }

    public void setFec_crea_cliente(Date fec_crea_cliente) {
        this.fec_crea_cliente = fec_crea_cliente;
    }

    public Date getFec_crea_cliente() {
        return fec_crea_cliente;
    }

    public void setUsu_crea_cliente(String usu_crea_cliente) {
        this.usu_crea_cliente = usu_crea_cliente;
    }

    public String getUsu_crea_cliente() {
        return usu_crea_cliente;
    }

    public void setFec_mod_cliente(Date fec_mod_cliente) {
        this.fec_mod_cliente = fec_mod_cliente;
    }

    public Date getFec_mod_cliente() {
        return fec_mod_cliente;
    }

    public void setUsu_mod_cliente(String usu_mod_cliente) {
        this.usu_mod_cliente = usu_mod_cliente;
    }

    public String getUsu_mod_cliente() {
        return usu_mod_cliente;
    }

    public void setInd_estado(String ind_estado) {
        this.ind_estado = ind_estado;
    }

    public String getInd_estado() {
        return ind_estado;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCell_cli(String cell_cli) {
        this.cell_cli = cell_cli;
    }

    public String getCell_cli() {
        return cell_cli;
    }

    public void setCod_tip_documento(String cod_tip_documento) {
        this.cod_tip_documento = cod_tip_documento;
    }

    public String getCod_tip_documento() {
        return cod_tip_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setId_usu_confir(String id_usu_confir) {
        this.id_usu_confir = id_usu_confir;
    }

    public String getId_usu_confir() {
        return id_usu_confir;
    }

    public void setCod_local_confir(String cod_local_confir) {
        this.cod_local_confir = cod_local_confir;
    }

    public String getCod_local_confir() {
        return cod_local_confir;
    }

    public void setIp_confir(String ip_confir) {
        this.ip_confir = ip_confir;
    }

    public String getIp_confir() {
        return ip_confir;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setTipo_direccion(String tipo_direccion) {
        this.tipo_direccion = tipo_direccion;
    }

    public String getTipo_direccion() {
        return tipo_direccion;
    }

    public void setTipo_lugar(String tipo_lugar) {
        this.tipo_lugar = tipo_lugar;
    }

    public String getTipo_lugar() {
        return tipo_lugar;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getEstado_civil() {
        return estado_civil;
    }


    public void setGrado_instruccion(String grado_instruccion) {
        this.grado_instruccion = grado_instruccion;
    }

    public String getGrado_instruccion() {
        return grado_instruccion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setCentro_edu_lab(String centro_edu_lab) {
        this.centro_edu_lab = centro_edu_lab;
    }

    public String getCentro_edu_lab() {
        return centro_edu_lab;
    }


    public void setSec_antecedente_hc(String sec_antecedente_hc) {
        this.sec_antecedente_hc = sec_antecedente_hc;
    }

    public String getSec_antecedente_hc() {
        return sec_antecedente_hc;
    }

    public void setCod_local_antecedente_hc(String cod_local_antecedente_hc) {
        this.cod_local_antecedente_hc = cod_local_antecedente_hc;
    }

    public String getCod_local_antecedente_hc() {
        return cod_local_antecedente_hc;
    }

    public void setDep_ubigeo(String dep_ubigeo) {
        this.dep_ubigeo = dep_ubigeo;
    }

    public String getDep_ubigeo() {
        return dep_ubigeo;
    }

    public void setPrv_ubigeo(String prv_ubigeo) {
        this.prv_ubigeo = prv_ubigeo;
    }

    public String getPrv_ubigeo() {
        return prv_ubigeo;
    }

    public void setDis_ubigeo(String dis_ubigeo) {
        this.dis_ubigeo = dis_ubigeo;
    }

    public String getDis_ubigeo() {
        return dis_ubigeo;
    }

    public void setDep_lug_nac(String dep_lug_nac) {
        this.dep_lug_nac = dep_lug_nac;
    }

    public String getDep_lug_nac() {
        return dep_lug_nac;
    }

    public void setPrv_lug_nac(String prv_lug_nac) {
        this.prv_lug_nac = prv_lug_nac;
    }

    public String getPrv_lug_nac() {
        return prv_lug_nac;
    }

    public void setDis_lug_nac(String dis_lug_nac) {
        this.dis_lug_nac = dis_lug_nac;
    }

    public String getDis_lug_nac() {
        return dis_lug_nac;
    }

    public void setDep_lug_pro(String dep_lug_pro) {
        this.dep_lug_pro = dep_lug_pro;
    }

    public String getDep_lug_pro() {
        return dep_lug_pro;
    }

    public void setPrv_lug_pro(String prv_lug_pro) {
        this.prv_lug_pro = prv_lug_pro;
    }

    public String getPrv_lug_pro() {
        return prv_lug_pro;
    }

    public void setDis_lug_pro(String dis_lug_pro) {
        this.dis_lug_pro = dis_lug_pro;
    }

    public String getDis_lug_pro() {
        return dis_lug_pro;
    }
}
