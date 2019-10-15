package mifarma.ptoventa.recaudacion.RecaudacionBFP;

public class RegistroOperacion {
    private String nroTarjeta="";
    private String montoMinTarj="";
    private String montoMesTarj="";
    private String montoTotalTarj="";
    private String otroMonto="";
    
    private String tipoPrestamo="";
    private String nroPrestamo="";
    private String fechaPagoPrestamo="";
    private String desc_Prestamo="";
    private String montoPagoPres="";
    
    private String idCtaDeposito="";
    private String desc_CtaDeposito="";
    private String monedaCtaDeposito="";
    private String saldoCtaDeposito="";
    
    public RegistroOperacion() {
        super();
    }
    
    public RegistroOperacion(String nroTarjeta,String montoMin,String montoMes, String montoTotal,String otroMonto) {
        this.nroTarjeta=nroTarjeta;
        this.montoMinTarj=montoMin;
        this.montoMesTarj=montoMes;
        this.montoTotalTarj=montoTotal;
        this.otroMonto=otroMonto;
    }
    
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getMontoMinTarj() {
        return montoMinTarj;
    }

    public void setMontoMinTarj(String montoMin) {
        this.montoMinTarj = montoMin;
    }

    public String getMontoMesTarj() {
        return montoMesTarj;
    }

    public void setMontoMesTarj(String montoMes) {
        this.montoMesTarj = montoMes;
    }

    public String getMontoTotalTarj() {
        return montoTotalTarj;
    }

    public void setMontoTotalTarj(String montoTotal) {
        this.montoTotalTarj = montoTotal;
    }

    public String getOtroMonto() {
        return otroMonto;
    }

    public void setOtroMonto(String otroMonto) {
        this.otroMonto = otroMonto;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setNroPrestamo(String nroPrestamo) {
        this.nroPrestamo = nroPrestamo;
    }

    public String getNroPrestamo() {
        return nroPrestamo;
    }

    public void setFechaPagoPrestamo(String fechaPago) {
        this.fechaPagoPrestamo = fechaPago;
    }

    public String getFechaPagoPrestamo() {
        return fechaPagoPrestamo;
    }

    public void setDesc_Prestamo(String desc_Prestamo) {
        this.desc_Prestamo = desc_Prestamo;
    }

    public String getDesc_Prestamo() {
        return desc_Prestamo;
    }

    public void setMontoPagoPres(String montoPagoPres) {
        this.montoPagoPres = montoPagoPres;
    }

    public String getMontoPagoPres() {
        return montoPagoPres;
    }

    public void setIdCtaDeposito(String idCtaDeposito) {
        this.idCtaDeposito = idCtaDeposito;
    }

    public String getIdCtaDeposito() {
        return idCtaDeposito;
    }

    public void setDesc_CtaDeposito(String desc_CtaDeposito) {
        this.desc_CtaDeposito = desc_CtaDeposito;
    }

    public String getDesc_CtaDeposito() {
        return desc_CtaDeposito;
    }

    public void setMonedaCtaDeposito(String monedaCta) {
        this.monedaCtaDeposito = monedaCta;
    }

    public String getMonedaCtaDeposito() {
        return monedaCtaDeposito;
    }

    public void setSaldoCtaDeposito(String saldoCta) {
        this.saldoCtaDeposito = saldoCta;
    }

    public String getSaldoCtaDeposito() {
        return saldoCtaDeposito;
    }
}
