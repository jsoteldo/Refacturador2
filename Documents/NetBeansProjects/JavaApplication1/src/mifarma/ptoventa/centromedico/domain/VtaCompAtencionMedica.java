package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 31.08.2016
 */
public class VtaCompAtencionMedica {
    
    private String cod_grupo_cia = "";
    private String cod_local = "";
    private String num_ped_vta = "";
    private String estado = "";
    private Date fec_crea;
    private String usu_crea = "";
    private Date fec_mod;
    private String usu_mod = "";
    private String tip_comp_pago = "";
    private String num_comp_pago = "";
    
    public VtaCompAtencionMedica() {
        super();
    }

    public void setCod_grupo_cia(String cod_grupo_cia) {
        this.cod_grupo_cia = cod_grupo_cia;
    }

    public String getCod_grupo_cia() {
        return cod_grupo_cia;
    }

    public void setCod_local(String cod_local) {
        this.cod_local = cod_local;
    }

    public String getCod_local() {
        return cod_local;
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

    public void setTip_comp_pago(String tip_comp_pago) {
        this.tip_comp_pago = tip_comp_pago;
    }

    public String getTip_comp_pago() {
        return tip_comp_pago;
    }

    public void setNum_comp_pago(String num_comp_pago) {
        this.num_comp_pago = num_comp_pago;
    }

    public String getNum_comp_pago() {
        return num_comp_pago;
    }
}
