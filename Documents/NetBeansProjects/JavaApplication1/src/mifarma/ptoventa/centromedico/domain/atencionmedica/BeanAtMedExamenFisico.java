package mifarma.ptoventa.centromedico.domain.atencionmedica;

public class BeanAtMedExamenFisico {
    
    private String codGrupoCia;
    private String codCia;
    private String codLocal;
    private String nroAtencionMedica;
    private String estadoGeneral;
    private String estadoConciencia;
    private String examenFisicoDirigido;
    
    public BeanAtMedExamenFisico() {
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

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public String getEstadoConciencia() {
        return estadoConciencia;
    }

    public void setEstadoConciencia(String estadoConciencia) {
        this.estadoConciencia = estadoConciencia;
    }

    public String getExamenFisicoDirigido() {
        return examenFisicoDirigido;
    }

    public void setExamenFisicoDirigido(String examenFisicoDirigido) {
        this.examenFisicoDirigido = examenFisicoDirigido;
    }
}
