package mifarma.ptoventa.centromedico.domain;

/**
 * @author ERIOS
 * @since 13.09.2016
 */
public class CmeHCAntecedentesDetalle {
    
    private String codGrupoCia;
    private String codPaciente;
    private int secuencialHC;
    private int codMaestroDetalle;
    private String tipoMaeDatalle;
    private String descripcionOtros;

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

    public void setCodMaestroDetalle(int codMaestroDetalle) {
        this.codMaestroDetalle = codMaestroDetalle;
    }

    public int getCodMaestroDetalle() {
        return codMaestroDetalle;
    }

    public void setTipoMaeDatalle(String tipoMaeDatalle) {
        this.tipoMaeDatalle = tipoMaeDatalle;
    }

    public String getTipoMaeDatalle() {
        return tipoMaeDatalle;
    }

    public void setDescripcionOtros(String descripcionOtros) {
        this.descripcionOtros = descripcionOtros;
    }

    public String getDescripcionOtros() {
        return descripcionOtros;
    }
}
