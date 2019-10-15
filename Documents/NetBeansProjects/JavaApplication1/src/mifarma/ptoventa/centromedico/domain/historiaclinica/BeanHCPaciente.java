package mifarma.ptoventa.centromedico.domain.historiaclinica;

import java.util.ArrayList;
import java.util.List;

import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;


public class BeanHCPaciente {
    
    private String codGrupoCia;
    private String codPaciente;
    private String nroHistoriaFisica;
    private String nroHistoriaActual;
    private BeanAtencionMedica beanConsultaMedica;
    private BeanHCAntecedentes beanAntecedente;
    private List<BeanHCAntecedentes> hcAntecedentes = new ArrayList<BeanHCAntecedentes>();
    
    
    public BeanHCPaciente() {
        super();
    }
   
    public String getCodGrupoCia() {
        return codGrupoCia;
    }

    public void setCodGrupoCia(String codGrupoCia) {
        this.codGrupoCia = codGrupoCia;
    }

    public String getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    public String getNroHistoriaFisica() {
        return nroHistoriaFisica;
    }

    public void setNroHistoriaFisica(String nroHistoriaFisica) {
        this.nroHistoriaFisica = nroHistoriaFisica;
    }

    public String getNroHistoriaActual() {
        return nroHistoriaActual;
    }

    public void setNroHistoriaActual(String nroHistoriaActual) {
        this.nroHistoriaActual = nroHistoriaActual;
    }

    public BeanHCAntecedentes getBeanAntecedente() {
        return beanAntecedente;
    }

    public void setBeanAntecedente(BeanHCAntecedentes antecedenteActual) {
        this.beanAntecedente = antecedenteActual;
    }

    public List<BeanHCAntecedentes> getHcAntecedentes() {
        return hcAntecedentes;
    }

    public void setHcAntecedentes(List<BeanHCAntecedentes> hcAntecedentes) {
        this.hcAntecedentes = hcAntecedentes;
    }

    public BeanAtencionMedica getBeanConsultaMedica() {
        return beanConsultaMedica;
    }

    public void setBeanConsultaMedica(BeanAtencionMedica beanConsultaActual) {
        this.beanConsultaMedica = beanConsultaActual;
    }
}
