package mifarma.ptoventa.centromedico.domain.atencionmedica;

public class BeanAtMedOtros {
    
    private String codGrupoCia;
    private String codCia;
    private String codLocal;
    private String nroAtencionMedica;
    private String procedimiento;
    private String interconsulta;
    private String transferencia;
    private String descansoMedicoInicio;
    private String descansoMedicoFin;
    private String proximaCita;
    
    public BeanAtMedOtros() {
        super();
    }

    public String getCodGrupoCia() {
        return codGrupoCia;
    }

    public void setCodGrupoCia(String codGrupoCia) {
        this.codGrupoCia = codGrupoCia;
    }

    public String getCodCia() {
        return codCia;
    }

    public void setCodCia(String codCia) {
        this.codCia = codCia;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    public String getNroAtencionMedica() {
        return nroAtencionMedica;
    }

    public void setNroAtencionMedica(String nroAtencionMedica) {
        this.nroAtencionMedica = nroAtencionMedica;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getInterconsulta() {
        return interconsulta;
    }

    public void setInterconsulta(String interconsulta) {
        this.interconsulta = interconsulta;
    }

    public String getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(String transferencia) {
        this.transferencia = transferencia;
    }

    public String getDescansoMedicoInicio() {
        return descansoMedicoInicio;
    }

    public void setDescansoMedicoInicio(String descansoMedicoInicio) {
        this.descansoMedicoInicio = descansoMedicoInicio;
    }

    public String getDescansoMedicoFin() {
        return descansoMedicoFin;
    }

    public void setDescansoMedicoFin(String descansoMedicoFin) {
        this.descansoMedicoFin = descansoMedicoFin;
    }

    public String getProximaCita() {
        return proximaCita;
    }

    public void setProximaCita(String proximaCita) {
        this.proximaCita = proximaCita;
    }
}
