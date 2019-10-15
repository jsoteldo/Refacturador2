package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 14.09.2016
 */
public class CmeAtencionMedica {
    public CmeAtencionMedica() {
        super();
    }
    
    private String cod_grupo_cia = "";
    private String cod_cia = "";
    private String cod_local = "";
    private String num_aten_med = "";
    private String cod_medico = "";
    private String cod_paciente = "";
    private String estado = "";
    private Date fec_crea;
    private String usu_crea = "";
    private Date fec_mod;
    private String usu_mod = "";
    private String ind_anulado = "";
    private int cod_maes_det;
    private String cod_local_vta = "";
    private String num_ped_vta = "";
    private int cod_tipo_atencion;

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

    public void setCod_medico(String cod_medico) {
        this.cod_medico = cod_medico;
    }

    public String getCod_medico() {
        return cod_medico;
    }

    public void setCod_paciente(String cod_paciente) {
        this.cod_paciente = cod_paciente;
    }

    public String getCod_paciente() {
        return cod_paciente;
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

    public void setInd_anulado(String ind_anulado) {
        this.ind_anulado = ind_anulado;
    }

    public String getInd_anulado() {
        return ind_anulado;
    }

    public void setCod_maes_det(int cod_maes_det) {
        this.cod_maes_det = cod_maes_det;
    }

    public int getCod_maes_det() {
        return cod_maes_det;
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

    public void setCod_tipo_atencion(int cod_tipo_atencion) {
        this.cod_tipo_atencion = cod_tipo_atencion;
    }

    public int getCod_tipo_atencion() {
        return cod_tipo_atencion;
    }
}
