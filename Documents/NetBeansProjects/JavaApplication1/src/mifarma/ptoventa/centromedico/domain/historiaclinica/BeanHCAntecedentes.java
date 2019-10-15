package mifarma.ptoventa.centromedico.domain.historiaclinica;

import java.util.ArrayList;

public class BeanHCAntecedentes {
    
    private String codGrupoCia;
    private String codLocal;
    private String codPaciente;
    private int secuencialHC;
    private String estado;
    //private boolean activo;
    //private String sexoPaciente;
    private String codMedico;
    private ArrayList<BeanHCAntecedentesFisiologicos> fisiologico;
    private BeanHCAntecedentesGenerales generales;
    private BeanHCAntecedentesGinecologicos ginecologico;
    private ArrayList<BeanHCAntecedentesPatologico> patologicos;
    
    public BeanHCAntecedentes() {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /*public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }*/

    /*public String getSexoPaciente() {
        return sexoPaciente;
    }

    public void setSexoPaciente(String sexoPaciente) {
        this.sexoPaciente = sexoPaciente;
    }*/

    public ArrayList<BeanHCAntecedentesFisiologicos> getFisiologico() {
        return fisiologico;
    }

    public void setFisiologico(ArrayList<BeanHCAntecedentesFisiologicos> fisiologico) {
        this.fisiologico = fisiologico;
    }

    public BeanHCAntecedentesGenerales getGenerales() {
        return generales;
    }

    public void setGenerales(BeanHCAntecedentesGenerales generales) {
        this.generales = generales;
    }

    public BeanHCAntecedentesGinecologicos getGinecologico() {
        return ginecologico;
    }

    public void setGinecologico(BeanHCAntecedentesGinecologicos ginecologico) {
        this.ginecologico = ginecologico;
    }

    public ArrayList<BeanHCAntecedentesPatologico> getPatologicos() {
        return patologicos;
    }

    public void setPatologicos(ArrayList<BeanHCAntecedentesPatologico> patologicos) {
        this.patologicos = patologicos;
    }

    public String getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(String codMedico) {
        this.codMedico = codMedico;
    }
}
