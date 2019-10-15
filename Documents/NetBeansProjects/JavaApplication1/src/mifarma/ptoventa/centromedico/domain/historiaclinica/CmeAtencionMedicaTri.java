package mifarma.ptoventa.centromedico.domain.historiaclinica;

public class CmeAtencionMedicaTri {

    private String codGrupoCia;
    private String codCia;
    private String codLocal;
    private String usuario;
    private String numAtenMed;
    private String cantPA1;
    private String cantPA2;
    private String cantFR;
    private String cantFC;
    private String cantTemp;
    private String cantPeso;
    private String cantTalla;
    
    public CmeAtencionMedicaTri() {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumAtenMed() {
        return numAtenMed;
    }

    public void setNumAtenMed(String numAtenMed) {
        this.numAtenMed = numAtenMed;
    }

    public String getCantPA1() {
        return cantPA1;
    }

    public void setCantPA1(String cantPA1) {
        this.cantPA1 = cantPA1;
    }

    public String getCantPA2() {
        return cantPA2;
    }

    public void setCantPA2(String cantPA2) {
        this.cantPA2 = cantPA2;
    }

    public String getCantFR() {
        return cantFR;
    }

    public void setCantFR(String cantFR) {
        this.cantFR = cantFR;
    }

    public String getCantFC() {
        return cantFC;
    }

    public void setCantFC(String cantFC) {
        this.cantFC = cantFC;
    }

    public String getCantTemp() {
        return cantTemp;
    }

    public void setCantTemp(String cantTemp) {
        this.cantTemp = cantTemp;
    }

    public String getCantPeso() {
        return cantPeso;
    }

    public void setCantPeso(String cantPeso) {
        this.cantPeso = cantPeso;
    }

    public String getCantTalla() {
        return cantTalla;
    }

    public void setCantTalla(String cantTalla) {
        this.cantTalla = cantTalla;
    }
}
