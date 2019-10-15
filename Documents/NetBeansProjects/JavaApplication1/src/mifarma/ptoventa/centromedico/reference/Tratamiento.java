package mifarma.ptoventa.centromedico.reference;

public class Tratamiento {
    
    private String nro;
    private String medicamento;
    private String concentracion;
    private String dosis;
    private String frecuencia;
    private String via;
    
    public Tratamiento() {
        //Contructor de la clase Tratamiento
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getNro() {
        return nro;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getDosis() {
        return dosis;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getVia() {
        return via;
    }
}
