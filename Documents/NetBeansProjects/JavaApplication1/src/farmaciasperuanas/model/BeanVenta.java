package farmaciasperuanas.model;

import java.util.ArrayList;

public class BeanVenta {
    private String codGrupoCia;
    private String codLocal;
    private String numPedidoVentaOld;
    private String numPedidoVentaNew;
    private String numCompPagoE;
    private String motivoAnulacion;
    private boolean isFidelizado;
    private String dniFidelizado;
    private String tipoComprobante;
    private String ruc;
    private boolean isConvenio;
    private ArrayList datosConvenio;
    private boolean isMotivoConvenio;
    private String coPago;
    private String codConvenio;
    private String codCliente;
    private String nomCliente;

    private String usuario;
    private String contraseña;
    private ArrayList detallePedido;
    private ArrayList formaPago;
    private ArrayList cabComprobante;
    
    private boolean isCompleto;


    public boolean isFidelizado() {
        return this.isFidelizado;
    }

    public void setFidelizado(boolean isFidelizado) {
        this.isFidelizado = isFidelizado;
    }

    public String getDniFidelizado() {
        return dniFidelizado;
    }

    public void setDniFidelizado(String dniFidelizado) {
        this.dniFidelizado = dniFidelizado;
    }

    public String getNumPedidoVentaOld() {
        return numPedidoVentaOld;
    }

    public void setNumPedidoVentaOld(String nroPedidoVenta) {
        this.numPedidoVentaOld = nroPedidoVenta;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public boolean isConvenio() {
        return isConvenio;
    }

    public void setIsConvenio(boolean isConvenio) {
        this.isConvenio = isConvenio;
    }

    public ArrayList<ArrayList> getDatosConvenio() {
        return datosConvenio;
    }

    public void setDatosConvenio(ArrayList datosConvenio) {
        this.datosConvenio = datosConvenio;
    }

    public ArrayList getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(ArrayList detallePedido) {
        this.detallePedido = detallePedido;
    }

    public ArrayList getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(ArrayList formaPago) {
        this.formaPago = formaPago;
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

    public String getNumCompPagoE() {
        return numCompPagoE;
    }

    public void setNumCompPagoE(String nroCompPagoE) {
        this.numCompPagoE = nroCompPagoE;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }

    public String getNumPedidoVentaNew() {
        return numPedidoVentaNew;
    }

    public void setNumPedidoVentaNew(String numPedidoVentaNew) {
        this.numPedidoVentaNew = numPedidoVentaNew;
    }

    public boolean isCompleto() {
        return isCompleto;
    }
    
    public void setCompleto(boolean completo) {
        isCompleto = completo;
    }
    
    public boolean isMotivoConvenio() {
        return isMotivoConvenio;
    }

    public void setMotivoConvenio(boolean motivoConvenio) {
        isMotivoConvenio = motivoConvenio;
    }


    public String getCoPago() {
        return coPago;
    }

    public void setCoPago(String coPago) {
        this.coPago = coPago;
    }

    public String getCodConvenio() {
        return codConvenio;
    }

    public void setCodConvenio(String codConvenio) {
        this.codConvenio = codConvenio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public ArrayList getCabComprobante() {
        return cabComprobante;
    }

    public void setCabComprobante(ArrayList cabComprobante) {
        this.cabComprobante = cabComprobante;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
