package mifarma.ptoventa.centromedico.domain.atencionmedica;

import java.util.ArrayList;

public class BeanAtencionMedica {
    
    private String codGrupoCia;
    private String codCia;
    private String codLocal;
    private String nroAtencionMedica;
    private String codPaciente;
    private String codMedico;
    private String estado;
    private String fechaCreacion;
    private String indAdnulado;
    private int codEspecialidad;
    private String codTipAtencion;
    private String descripcionEspecialidad;
    private BeanAtMedEnfermedadActual enfermedadActual;
    private BeanAtMedTriaje triaje;
    private BeanAtMedExamenFisico examenFisico;
    private ArrayList<BeanAtMedDiagnostico> diagnostico;
    private BeanAtMedTratamiento tratamiento;
    private BeanAtMedExamenesAuxiliares examenesAuxiliares;
    private BeanAtMedOtros otros;

    
    
    public BeanAtencionMedica() {
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

    public String getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(String codMedico) {
        this.codMedico = codMedico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getIndAdnulado() {
        return indAdnulado;
    }

    public void setIndAdnulado(String indAdnulado) {
        this.indAdnulado = indAdnulado;
    }

    public int getCodEspecialidad() {
        return codEspecialidad;
    }

    public void setCodEspecialidad(int codEspecialidad) {
        this.codEspecialidad = codEspecialidad;
    }

    public String getDescripcionEspecialidad() {
        return descripcionEspecialidad;
    }

    public void setDescripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
    }

    public BeanAtMedTriaje getTriaje() {
        return triaje;
    }

    public void setTriaje(BeanAtMedTriaje triaje) {
        this.triaje = triaje;
    }

    public BeanAtMedEnfermedadActual getEnfermedadActual() {
        return enfermedadActual;
    }

    public void setEnfermedadActual(BeanAtMedEnfermedadActual enfermedadActual) {
        this.enfermedadActual = enfermedadActual;
    }

    public BeanAtMedExamenFisico getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(BeanAtMedExamenFisico examenFisico) {
        this.examenFisico = examenFisico;
    }

    public ArrayList<BeanAtMedDiagnostico> getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(ArrayList<BeanAtMedDiagnostico> diagnostico) {
        this.diagnostico = diagnostico;
    }

    public BeanAtMedTratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(BeanAtMedTratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public BeanAtMedExamenesAuxiliares getExamenesAuxiliares() {
        return examenesAuxiliares;
    }

    public void setExamenesAuxiliares(BeanAtMedExamenesAuxiliares examenesAuxiliares) {
        this.examenesAuxiliares = examenesAuxiliares;
    }

    public BeanAtMedOtros getOtros() {
        return otros;
    }

    public void setOtros(BeanAtMedOtros otros) {
        this.otros = otros;
    }

    public String getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    public String getCodTipAtencion() {
        return codTipAtencion;
    }

    public void setCodTipAtencion(String codTipAtencion) {
        this.codTipAtencion = codTipAtencion;
    }
}
