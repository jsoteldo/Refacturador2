package mifarma.ptoventa.centromedico.domain.historiaclinica;

public class BeanHCAntecedentesFisiologicos {
    
    private String codGrupoCia;
    private String codLocal;
    private String codPaciente;
    private int secuencialHC;
    private String tipoFisiologico;
    private int codAnteFisiologico;
    private int codGrupo;
    private String descAnteFisiologico;
    private String opcionOtro;
    public BeanHCAntecedentesFisiologicos() {
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

    public String getTipoFisiologico() {
        return tipoFisiologico;
    }

    public void setTipoFisiologico(String tipoFisiologico) {
        this.tipoFisiologico = tipoFisiologico;
    }

    public int getCodAnteFisiologico() {
        return codAnteFisiologico;
    }

    public void setCodAnteFisiologico(int codAnteFisiologico) {
        this.codAnteFisiologico = codAnteFisiologico;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getDescAnteFisiologico() {
        return descAnteFisiologico;
    }

    public void setDescAnteFisiologico(String descAnteFisiologico) {
        this.descAnteFisiologico = descAnteFisiologico;
    }

    public String getOpcionOtro() {
        return opcionOtro;
    }

    public void setOpcionOtro(String opcionOtro) {
        this.opcionOtro = opcionOtro;
    }
}
