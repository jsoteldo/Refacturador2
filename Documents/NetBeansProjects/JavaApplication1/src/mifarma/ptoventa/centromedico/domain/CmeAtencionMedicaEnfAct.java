package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 14.09.2016
 */
public class CmeAtencionMedicaEnfAct {
    
    private String cod_grupo_cia = "";
    private String cod_cia = "";
    private String cod_local = "";
    private String num_aten_med = "";
    private String motivo_consulta = "";
    private String tipo_informante = "";
    private int tiempo_enfermedad;
    private String forma_inicio = "";
    private String signos = "";
    private String sintomas = "";
    private String relato_cronologico = "";
    private String tipo_apetito = "";
    private String tipo_sed = "";
    private String tipo_sueno = "";
    private String tipo_orina = "";
    private String tipo_deposicion = "";
    private String estado = "";
    private Date fec_crea;
    private String usu_crea = "";
    private Date fec_mod;
    private String usu_mod = "";

    public void setCod_grupo_cia(String cod_grupo_cia) {
        this.cod_grupo_cia = cod_grupo_cia;
    }

    public String getCod_grupo_cia() {
        return cod_grupo_cia;
    }

    public void setCod_cia(String cod_cia) {
        this.cod_cia = cod_cia;
    }

    public String getCod_cia() {
        return cod_cia;
    }

    public void setCod_local(String cod_local) {
        this.cod_local = cod_local;
    }

    public String getCod_local() {
        return cod_local;
    }

    public void setNum_aten_med(String num_aten_med) {
        this.num_aten_med = num_aten_med;
    }

    public String getNum_aten_med() {
        return num_aten_med;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public void setTipo_informante(String tipo_informante) {
        this.tipo_informante = tipo_informante;
    }

    public String getTipo_informante() {
        return tipo_informante;
    }

    public void setTiempo_enfermedad(int tiempo_enfermedad) {
        this.tiempo_enfermedad = tiempo_enfermedad;
    }

    public int getTiempo_enfermedad() {
        return tiempo_enfermedad;
    }

    public void setForma_inicio(String forma_inicio) {
        this.forma_inicio = forma_inicio;
    }

    public String getForma_inicio() {
        return forma_inicio;
    }

    public void setSignos(String signos) {
        this.signos = signos;
    }

    public String getSignos() {
        return signos;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setRelato_cronologico(String relato_cronologico) {
        this.relato_cronologico = relato_cronologico;
    }

    public String getRelato_cronologico() {
        return relato_cronologico;
    }

    public void setTipo_apetito(String tipo_apetito) {
        this.tipo_apetito = tipo_apetito;
    }

    public String getTipo_apetito() {
        return tipo_apetito;
    }

    public void setTipo_sed(String tipo_sed) {
        this.tipo_sed = tipo_sed;
    }

    public String getTipo_sed() {
        return tipo_sed;
    }

    public void setTipo_sueno(String tipo_sueno) {
        this.tipo_sueno = tipo_sueno;
    }

    public String getTipo_sueno() {
        return tipo_sueno;
    }

    public void setTipo_orina(String tipo_orina) {
        this.tipo_orina = tipo_orina;
    }

    public String getTipo_orina() {
        return tipo_orina;
    }

    public void setTipo_deposicion(String tipo_deposicion) {
        this.tipo_deposicion = tipo_deposicion;
    }

    public String getTipo_deposicion() {
        return tipo_deposicion;
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
}
