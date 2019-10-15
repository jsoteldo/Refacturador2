package mifarma.cpe.modelo;

public class BeanComprobantesEPOS {
    
    private String codGrupoCia;
    private String codLocal;
    private String numPedVta;
    private String secCompPago;
    private String tipCompPago;
    private String tipClienteConvenio;
    private String indCredito;
    private boolean compCredito;
    private String serie;
    private String correlativo;
    private String dobleConfirmacion;
    private boolean enviaDobleConfirmacion;
    private String nroComprobanteE;
    
    public BeanComprobantesEPOS() {
        super();
    }

    public void setCodGrupoCia(String codGrupoCia) {
        this.codGrupoCia = codGrupoCia;
    }

    public String getCodGrupoCia() {
        return codGrupoCia;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setNumPedVta(String numPedVta) {
        this.numPedVta = numPedVta;
    }

    public String getNumPedVta() {
        return numPedVta;
    }

    public void setSecCompPago(String secCompPago) {
        this.secCompPago = secCompPago;
    }

    public String getSecCompPago() {
        return secCompPago;
    }

    public void setTipCompPago(String tipCompPago) {
        this.tipCompPago = tipCompPago;
    }

    public String getTipCompPago() {
        return tipCompPago;
    }

    public void setTipClienteConvenio(String tipClienteConvenio) {
        this.tipClienteConvenio = tipClienteConvenio;
    }

    public String getTipClienteConvenio() {
        return tipClienteConvenio;
    }

    public void setIndCredito(String indCredito) {
        this.indCredito = indCredito;
        setCompCredito("S".equalsIgnoreCase(this.indCredito));
    }

    public String getIndCredito() {
        return indCredito;
    }

    public void setCompCredito(boolean compCredito) {
        this.compCredito = compCredito;
    }

    public boolean isCompCredito() {
        return compCredito;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setDobleConfirmacion(String dobleConfirmacion) {
        this.dobleConfirmacion = dobleConfirmacion;
    }

    public String getDobleConfirmacion() {
        return dobleConfirmacion;
    }

    public void setEnviaDobleConfirmacion(boolean enviaDobleConfirmacion) {
        this.enviaDobleConfirmacion = enviaDobleConfirmacion;
    }

    public boolean isEnviaDobleConfirmacion() {
        return enviaDobleConfirmacion;
    }

    public void setNroComprobanteE(String nroComprobanteE) {
        this.nroComprobanteE = nroComprobanteE;
    }

    public String getNroComprobanteE() {
        return nroComprobanteE;
    }
}
