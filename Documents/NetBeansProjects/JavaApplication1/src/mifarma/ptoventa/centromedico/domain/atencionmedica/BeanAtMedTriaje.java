package mifarma.ptoventa.centromedico.domain.atencionmedica;

public class BeanAtMedTriaje {

    private String codGrupoCia;
    private String codCia;
    private String codLocal;
    private String nroAtencionMedica;
    private int funcionVitalPA1;
    private int funcionVitalPA2;
    private int funcionVitalFR;
    private int funcionVitalFC;
    private double funcionVitalT;
    private double funcionVitalPeso;
    private double funcionvitalTalla;
    
    public BeanAtMedTriaje() {
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

    public int getFuncionVitalPA1() {
        return funcionVitalPA1;
    }

    public void setFuncionVitalPA1(int funcionVitalPA1) {
        this.funcionVitalPA1 = funcionVitalPA1;
    }

    public int getFuncionVitalPA2() {
        return funcionVitalPA2;
    }

    public void setFuncionVitalPA2(int funcionVitalPA2) {
        this.funcionVitalPA2 = funcionVitalPA2;
    }

    public int getFuncionVitalFR() {
        return funcionVitalFR;
    }

    public void setFuncionVitalFR(int funcionVitalFR) {
        this.funcionVitalFR = funcionVitalFR;
    }

    public int getFuncionVitalFC() {
        return funcionVitalFC;
    }

    public void setFuncionVitalFC(int funcionVitalFC) {
        this.funcionVitalFC = funcionVitalFC;
    }

    public double getFuncionVitalT() {
        return funcionVitalT;
    }

    public void setFuncionVitalT(double funcionVitalT) {
        this.funcionVitalT = funcionVitalT;
    }

    public double getFuncionVitalPeso() {
        return funcionVitalPeso;
    }

    public void setFuncionVitalPeso(double funcionVitalPeso) {
        this.funcionVitalPeso = funcionVitalPeso;
    }

    public double getFuncionvitalTalla() {
        return funcionvitalTalla;
    }

    public void setFuncionvitalTalla(double funcionvitalTalla) {
        this.funcionvitalTalla = funcionvitalTalla;
    }
}
