package mifarma.ptoventa.centromedico.domain.historiaclinica;

public class BeanHCAntecedentesPatologico {
    
    private String codGrupoCia;
    private String codLocal;
    private String codPaciente;
    private int secuencialHC;
    private String tipoPatologico;
    private String codCIE10;
    private String codDiagnostico;
    private String nombreDiagnostico;
    private String descripcionPariente;
    
    public BeanHCAntecedentesPatologico() {
        super();
    }


    public String getCodGrupoCia() {
        return codGrupoCia;
    }

    public void setCodGrupoCia(String codGrupoCia) {
        this.codGrupoCia = codGrupoCia;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    public String getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    public int getSecuencialHC() {
        return secuencialHC;
    }

    public void setSecuencialHC(int secuencialHC) {
        this.secuencialHC = secuencialHC;
    }

    public String getTipoPatologico() {
        return tipoPatologico;
    }

    public void setTipoPatologico(String tipoPatologico) {
        this.tipoPatologico = tipoPatologico;
    }

    public String getCodCIE10() {
        return codCIE10;
    }

    public void setCodCIE10(String codCIE10) {
        this.codCIE10 = codCIE10;
    }

    public String getCodDiagnostico() {
        return codDiagnostico;
    }

    public void setCodDiagnostico(String codDiagnostico) {
        this.codDiagnostico = codDiagnostico;
    }

    public String getNombreDiagnostico() {
        return nombreDiagnostico;
    }

    public void setNombreDiagnostico(String nombreDiagnostico) {
        this.nombreDiagnostico = nombreDiagnostico;
    }

    public String getDescripcionPariente() {
        return descripcionPariente;
    }

    public void setDescripcionPariente(String descripcionPariente) {
        this.descripcionPariente = descripcionPariente;
    }
}
