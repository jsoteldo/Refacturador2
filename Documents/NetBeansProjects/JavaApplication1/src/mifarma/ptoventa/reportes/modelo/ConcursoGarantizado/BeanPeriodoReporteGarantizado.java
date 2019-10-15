package mifarma.ptoventa.reportes.modelo.ConcursoGarantizado;

public class BeanPeriodoReporteGarantizado {
    
    private String mesId;
    private String periodo;
    
    public BeanPeriodoReporteGarantizado() {
        super();
    }

    public void setMesId(String mesId) {
        this.mesId = mesId;
    }

    public String getMesId() {
        return mesId;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }
}