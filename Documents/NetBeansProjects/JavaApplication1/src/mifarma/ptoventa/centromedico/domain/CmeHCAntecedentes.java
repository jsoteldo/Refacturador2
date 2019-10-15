package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 13.09.2016
 */
public class CmeHCAntecedentes {
    
    private String codGrupoCia;
    private String codPaciente;
    private int secuencialHC;
    private String medicamentos;
    private String ram;
    private String ocupacionales;
    private String estado;
    private Date fec_crea;
    private String usu_crea = "";
    private Date fec_mod;
    private String usu_mod = "";

    public void setCodGrupoCia(String codGrupoCia) {
        this.codGrupoCia = codGrupoCia;
    }

    public String getCodGrupoCia() {
        return codGrupoCia;
    }

    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    public String getCodPaciente() {
        return codPaciente;
    }

    public void setSecuencialHC(int secuencialHC) {
        this.secuencialHC = secuencialHC;
    }

    public int getSecuencialHC() {
        return secuencialHC;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRam() {
        return ram;
    }

    public void setOcupacionales(String ocupacionales) {
        this.ocupacionales = ocupacionales;
    }

    public String getOcupacionales() {
        return ocupacionales;
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
