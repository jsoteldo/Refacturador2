package mifarma.ptoventa.centromedico.domain.atencionmedica;

public class BeanAtMedDiagnostico {
    
    private String codGrupoCia;
    private String codCia;
    private String codLocal;
    private String nroAtencionMedica;
    private int secuencial;
    private String codCIE;
    private String codDiagnostico;
    private String descripcionDiagnostico;
    private String tipoDiagnostico;
    private String descTipoDiagnostico;
    
    public BeanAtMedDiagnostico() {
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

    public int getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(int secuencial) {
        this.secuencial = secuencial;
    }

    public String getCodDiagnostico() {
        return codDiagnostico;
    }

    public void setCodDiagnostico(String codDiagnostico) {
        this.codDiagnostico = codDiagnostico;
    }

    public String getDescripcionDiagnostico() {
        return descripcionDiagnostico;
    }

    public void setDescripcionDiagnostico(String descripcionDiagnostico) {
        this.descripcionDiagnostico = descripcionDiagnostico;
    }

    public String getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(String tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public String getDescTipoDiagnostico() {
        return descTipoDiagnostico;
    }

    public void setDescTipoDiagnostico(String descTipoDiagnostico) {
        this.descTipoDiagnostico = descTipoDiagnostico;
    }

    public String getCodCIE() {
        return codCIE;
    }

    public void setCodCIE(String codCIE) {
        this.codCIE = codCIE;
    }
}
