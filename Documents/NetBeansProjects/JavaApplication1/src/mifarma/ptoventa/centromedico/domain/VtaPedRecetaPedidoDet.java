package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 02.09.2016
 */
public class VtaPedRecetaPedidoDet {
    
    private String cod_grupo_cia = "";
    private String cod_local = "";
    private String num_ped_rec = "";
    private String cod_local_vta = "";
    private String num_ped_vta = "";
    private int sec_ped_vta_det;
    private String cod_prod = "";
    private int cant_atendida;
    private int val_frac;
    private String estado = "";
    private Date fec_crea;
    private String usu_crea = "";
    private Date fec_mod;
    private String usu_mod = "";       
    private String unid_vta = "";       
    
    public VtaPedRecetaPedidoDet() {
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

    public void setNum_ped_rec(String num_ped_rec) {
        this.num_ped_rec = num_ped_rec;
    }

    public String getNum_ped_rec() {
        return num_ped_rec;
    }

    public void setCod_local_vta(String cod_local_vta) {
        this.cod_local_vta = cod_local_vta;
    }

    public String getCod_local_vta() {
        return cod_local_vta;
    }

    public void setNum_ped_vta(String num_ped_vta) {
        this.num_ped_vta = num_ped_vta;
    }

    public String getNum_ped_vta() {
        return num_ped_vta;
    }

    public void setSec_ped_vta_det(int sec_ped_vta_det) {
        this.sec_ped_vta_det = sec_ped_vta_det;
    }

    public int getSec_ped_vta_det() {
        return sec_ped_vta_det;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public String getCod_prod() {
        return cod_prod;
    }

    public void setCant_atendida(int cant_atendida) {
        this.cant_atendida = cant_atendida;
    }

    public int getCant_atendida() {
        return cant_atendida;
    }

    public void setVal_frac(int val_frac) {
        this.val_frac = val_frac;
    }

    public int getVal_frac() {
        return val_frac;
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

    public void setUnid_vta(String unid_vta) {
        this.unid_vta = unid_vta;
    }

    public String getUnid_vta() {
        return unid_vta;
    }
}
