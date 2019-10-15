package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 06.09.2016
 */
public class CmeHistoriaClinica {
    
    private String cod_grupo_cia = "";
    private String cod_paciente = "";
    private String grupo_sang = "";
    private String factor_rh = "";
    private String estado = "";
    private Date fec_crea;
    private String usu_crea = "";
    private Date fec_mod;
    private String usu_mod = "";
    private Date fecha_hc;
    private String ind_hc_fisica = "";
    private String nro_hc_fisica = "";
    private String nom_acom = "";
    private String cod_tip_doc_acom = "";
    private String num_doc_acom = "";
    private String cod_tip_acom = "";
    private String nro_hc_actual = "";

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

    public void setGrupo_sang(String grupo_sang) {
        this.grupo_sang = grupo_sang;
    }

    public String getGrupo_sang() {
        return grupo_sang;
    }

    public void setFactor_rh(String factor_rh) {
        this.factor_rh = factor_rh;
    }

    public String getFactor_rh() {
        return factor_rh;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setFec_crea(Date fec_crea) {
        this.fec_crea = fec_crea;
    }

    public Date getFec_crea() {
        return fec_crea;
    }

    public void setUsu_crea(String usu_crea) {
        this.usu_crea = usu_crea;
    }

    public String getUsu_crea() {
        return usu_crea;
    }

    public void setFec_mod(Date fec_mod) {
        this.fec_mod = fec_mod;
    }

    public Date getFec_mod() {
        return fec_mod;
    }

    public void setUsu_mod(String usu_mod) {
        this.usu_mod = usu_mod;
    }

    public String getUsu_mod() {
        return usu_mod;
    }

    public void setFecha_hc(Date fecha_hc) {
        this.fecha_hc = fecha_hc;
    }

    public Date getFecha_hc() {
        return fecha_hc;
    }

    public void setInd_hc_fisica(String ind_hc_fisica) {
        this.ind_hc_fisica = ind_hc_fisica;
    }

    public String getInd_hc_fisica() {
        return ind_hc_fisica;
    }

    public void setNro_hc_fisica(String nro_hc_fisica) {
        this.nro_hc_fisica = nro_hc_fisica;
    }

    public String getNro_hc_fisica() {
        return nro_hc_fisica;
    }

    public void setNom_acom(String nom_acom) {
        this.nom_acom = nom_acom;
    }

    public String getNom_acom() {
        return nom_acom;
    }

    public void setCod_tip_doc_acom(String cod_tip_doc_acom) {
        this.cod_tip_doc_acom = cod_tip_doc_acom;
    }

    public String getCod_tip_doc_acom() {
        return cod_tip_doc_acom;
    }

    public void setNum_doc_acom(String num_doc_acom) {
        this.num_doc_acom = num_doc_acom;
    }

    public String getNum_doc_acom() {
        return num_doc_acom;
    }

    public void setCod_tip_acom(String cod_tip_acom) {
        this.cod_tip_acom = cod_tip_acom;
    }

    public String getCod_tip_acom() {
        return cod_tip_acom;
    }

    public void setNro_hc_actual(String nro_hc_actual) {
        this.nro_hc_actual = nro_hc_actual;
    }

    public String getNro_hc_actual() {
        return nro_hc_actual;
    }
}
