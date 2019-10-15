package mifarma.ptoventa.recaudacion.RecaudacionBFP;

public class RegistroTarjeta {
    private String nroTarjeta="";
    private String montoMin="";
    private String montoMes="";
    private String montoTotal="";
    private String otroMonto="";
    
    public RegistroTarjeta() {
        super();
    }
    
    public RegistroTarjeta(String nroTarjeta,String montoMin,String montoMes, String montoTotal,String otroMonto) {
        this.nroTarjeta=nroTarjeta;
        this.montoMin=montoMin;
        this.montoMes=montoMes;
        this.montoTotal=montoTotal;
        this.otroMonto=otroMonto;
    }
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getMontoMin() {
        return montoMin;
    }

    public void setMontoMin(String montoMin) {
        this.montoMin = montoMin;
    }

    public String getMontoMes() {
        return montoMes;
    }

    public void setMontoMes(String montoMes) {
        this.montoMes = montoMes;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getOtroMonto() {
        return otroMonto;
    }

    public void setOtroMonto(String otroMonto) {
        this.otroMonto = otroMonto;
    }
}
