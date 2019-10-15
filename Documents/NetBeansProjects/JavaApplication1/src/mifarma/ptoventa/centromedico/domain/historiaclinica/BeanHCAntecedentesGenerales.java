package mifarma.ptoventa.centromedico.domain.historiaclinica;

public class BeanHCAntecedentesGenerales {
    
    private String codGrupoCia;
    private String codLocal;
    private String codPaciente;
    private int secuencialHC;
    private String medicamentos;
    private String ram;
    private String ocupacionales;
    public BeanHCAntecedentesGenerales() {
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

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOcupacionales() {
        return ocupacionales;
    }

    public void setOcupacionales(String ocupacionales) {
        this.ocupacionales = ocupacionales;
    }
}
