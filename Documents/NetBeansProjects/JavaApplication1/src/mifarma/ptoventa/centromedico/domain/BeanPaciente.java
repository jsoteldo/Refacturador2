package mifarma.ptoventa.centromedico.domain;

import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentes;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCPaciente;

public class BeanPaciente implements Cloneable {
    
    private String codGrupoCia;
    private String codPaciente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private String tipDocumento;
    private String numDocumento;
    private String estCivil;
    private String fecNacimiento;
    private String direccion;
    private String gradoInstruccion;
    private String ocupacion;
    private String centroEduLab;
    private String email;
    private String telFijo;
    private String telCelular;
    private String tipAcom;
    private String nomAcom;
    private String tipDocAcom;
    private String numDocAcom;
    private String numHCFisica;
    private String fecHCFisica;
    private String grupoSan;
    private String factorRH;
    private String numHistCli;
    private String codLocal;
    private String depUbigeo; 
    private String pvrUbigeo;
    private String disUbigeo;
    private String depLugNac; 
    private String pvrLugNac;
    private String disLugNac;
    private String depLugPro; 
    private String pvrLugPro;
    private String disLugPro;
    private int nroHCAntecedente;
    private String codLocalAntecedente;
    private BeanHCPaciente historiaClinia;
    //private BeanHCAntecedentes antecedenteActual;
     
    public BeanPaciente() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BeanHCPaciente getHistoriaClinia() {
        return historiaClinia;
    }

    public void setHistoriaClinia(BeanHCPaciente historiaClinia) {
        this.historiaClinia = historiaClinia;
    }

    public String getTipDocumento() {
        return tipDocumento;
    }

    public void setTipDocumento(String tipDocumento) {
        this.tipDocumento = tipDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }
    
    public String getGrupoSan() {
        return grupoSan;
    }

    public void setGrupoSan(String grupoSan) {
        this.grupoSan = grupoSan;
    }

    public String getFactorRH() {
        return factorRH;
    }

    public void setFactorRH(String factorRH) {
        this.factorRH = factorRH;
    }
    
    

    
    public String toString(){
        String texto="";
        texto=texto+"***getTipDocumento : "+getTipDocumento();
        texto=texto+"***getNumDocumento : "+getNumDocumento();
        texto=texto+"***getApellidoPaterno : "+getApellidoPaterno();
        texto=texto+"***getApellidoMaterno : "+getApellidoMaterno();
        texto=texto+"***getNombre : "+getNombre();
        return texto;
    }

    public String getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(String estCivil) {
        this.estCivil = estCivil;
    }

    public String getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(String fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getCentroEduLab() {
        return centroEduLab;
    }

    public void setCentroEduLab(String centroEduLab) {
        this.centroEduLab = centroEduLab;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getTipAcom() {
        return tipAcom;
    }

    public void setTipAcom(String tipAcom) {
        this.tipAcom = tipAcom;
    }

    public String getNomAcom() {
        return nomAcom;
    }

    public void setNomAcom(String nomAcom) {
        this.nomAcom = nomAcom;
    }

    public String getTipDocAcom() {
        return tipDocAcom;
    }

    public void setTipDocAcom(String tipDocAcom) {
        this.tipDocAcom = tipDocAcom;
    }

    public String getNumDocAcom() {
        return numDocAcom;
    }

    public void setNumDocAcom(String numDocAcom) {
        this.numDocAcom = numDocAcom;
    }

    public String getGradoInstruccion() {
        return gradoInstruccion;
    }

    public void setGradoInstruccion(String gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
    }

    public String getNumHCFisica() {
        return numHCFisica;
    }

    public void setNumHCFisica(String numHCFisica) {
        this.numHCFisica = numHCFisica;
    }

    public String getFecHCFisica() {
        return fecHCFisica;
    }

    public void setFecHCFisica(String fecHCFisica) {
        this.fecHCFisica = fecHCFisica;
    }

    public String getNumHistCli() {
        return numHistCli;
    }

    public void setNumHistCli(String numHistCli) {
        this.numHistCli = numHistCli;
    }

    /*public int getNroHCAntecedente() {
        return nroHCAntecedente;
    }

    public void setNroHCAntecedente(int nroHCAntecedente) {
        this.nroHCAntecedente = nroHCAntecedente;
    }*/

    public String getDepUbigeo() {
        return depUbigeo;
    }

    public void setDepUbigeo(String depUbigeo) {
        this.depUbigeo = depUbigeo;
    }

    public String getPvrUbigeo() {
        return pvrUbigeo;
    }

    public void setPvrUbigeo(String pvrUbigeo) {
        this.pvrUbigeo = pvrUbigeo;
    }

    public String getDisUbigeo() {
        return disUbigeo;
    }

    public void setDisUbigeo(String disUbigeo) {
        this.disUbigeo = disUbigeo;
    }

    public String getDepLugNac() {
        return depLugNac;
    }

    public void setDepLugNac(String depLugNac) {
        this.depLugNac = depLugNac;
    }

    public String getPvrLugNac() {
        return pvrLugNac;
    }

    public void setPvrLugNac(String pvrLugNac) {
        this.pvrLugNac = pvrLugNac;
    }

    public String getDisLugNac() {
        return disLugNac;
    }

    public void setDisLugNac(String disLugNac) {
        this.disLugNac = disLugNac;
    }

    public String getDepLugPro() {
        return depLugPro;
    }

    public void setDepLugPro(String depLugPro) {
        this.depLugPro = depLugPro;
    }

    public String getPvrLugPro() {
        return pvrLugPro;
    }

    public void setPvrLugPro(String pvrLugPro) {
        this.pvrLugPro = pvrLugPro;
    }

    public String getDisLugPro() {
        return disLugPro;
    }

    public void setDisLugPro(String disLugPro) {
        this.disLugPro = disLugPro;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    public String getCodLocal() {
        return codLocal;
    }
    
    public int getNroHCAntecedente() {
        return nroHCAntecedente;
    }

    public void setNroHCAntecedente(int nroHCAntecedente) {
        this.nroHCAntecedente = nroHCAntecedente;
    }

    public String getCodLocalAntecedente() {
        return codLocalAntecedente;
    }

    public void setCodLocalAntecedente(String codLocalAntecedente) {
        this.codLocalAntecedente = codLocalAntecedente;
    }
    /*
    public BeanHCAntecedentes getAntecedenteActual() {
        return antecedenteActual;
    }

    public void setAntecedenteActual(BeanHCAntecedentes antecedenteActual) {
        this.antecedenteActual = antecedenteActual;
    }
    */
    
    public Object clone(){
        BeanPaciente obj = null;
        try{
            obj = (BeanPaciente)super.clone();
        }catch(Exception ex){
            
        }
        return obj;
    }
}
