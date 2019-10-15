package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 09.09.2016
 */
public class HistCompAtencionMedica {
    
    private String cod_grupo_cia = "";
    private String cod_local = "";
    private String num_ped_vta = "";    
    private int sec_hist_med;
    private Date fec_hist_med;
    private String cod_cia = "";
    private String cod_local_cme = "";
    private String num_aten_med = "";    
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

    public void setSec_hist_med(int sec_hist_med) {
        this.sec_hist_med = sec_hist_med;
    }

    public int getSec_hist_med() {
        return sec_hist_med;
    }

    public void setFec_hist_med(Date fec_hist_med) {
        this.fec_hist_med = fec_hist_med;
    }

    public Date getFec_hist_med() {
        return fec_hist_med;
    }

    public void setNum_aten_med(String num_aten_med) {
        this.num_aten_med = num_aten_med;
    }

    public String getNum_aten_med() {
        return num_aten_med;
    }

    public void setCod_local_cme(String cod_local_cme) {
        this.cod_local_cme = cod_local_cme;
    }

    public String getCod_local_cme() {
        return cod_local_cme;
    }

    public void setNum_ped_vta(String num_ped_vta) {
        this.num_ped_vta = num_ped_vta;
    }

    public String getNum_ped_vta() {
        return num_ped_vta;
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
