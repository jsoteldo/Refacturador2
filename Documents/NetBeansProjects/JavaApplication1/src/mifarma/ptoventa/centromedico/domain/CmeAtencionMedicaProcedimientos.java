package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 14.09.2016
 */
public class CmeAtencionMedicaProcedimientos {
    
    private String cod_grupo_cia = "";
    private String cod_cia = "";
    private String cod_local = "";
    private String num_aten_med = "";
    private String procedimiento = "";
    private String interconsulta = "";
    private String transferencia = "";
    private Date descanso_medico_ini;
    private Date descanso_medico_fin;
    private Date proxima_cita;
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

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setInterconsulta(String interconsulta) {
        this.interconsulta = interconsulta;
    }

    public String getInterconsulta() {
        return interconsulta;
    }

    public void setTransferencia(String transferencia) {
        this.transferencia = transferencia;
    }

    public String getTransferencia() {
        return transferencia;
    }

    public void setDescanso_medico_ini(Date descanso_medico_ini) {
        this.descanso_medico_ini = descanso_medico_ini;
    }

    public Date getDescanso_medico_ini() {
        return descanso_medico_ini;
    }

    public void setDescanso_medico_fin(Date descanso_medico_fin) {
        this.descanso_medico_fin = descanso_medico_fin;
    }

    public Date getDescanso_medico_fin() {
        return descanso_medico_fin;
    }

    public void setProxima_cita(Date proxima_cita) {
        this.proxima_cita = proxima_cita;
    }

    public Date getProxima_cita() {
        return proxima_cita;
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
