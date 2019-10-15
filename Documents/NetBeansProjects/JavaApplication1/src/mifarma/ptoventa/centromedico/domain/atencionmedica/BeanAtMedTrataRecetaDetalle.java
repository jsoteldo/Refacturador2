package mifarma.ptoventa.centromedico.domain.atencionmedica;

public class BeanAtMedTrataRecetaDetalle {
    
    private String codGrupoCia;
    private String codLocal;
    private String nroPedidoReceta;
    private int secuencialDetalle;
    private String codProducto;
    private String descProducto;
    private String unidadVenta;//unidad de venta sugerida
    private int frecuenciaToma;
    private int duracionToma;
    private int codViaAdministracion;
    private String descViaAdministracion;
    private String dosis;
    private int cantidadToma;
    private int valFraccionamiento;
    private String unidadVentaFraccion;
    
    public BeanAtMedTrataRecetaDetalle() {
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

    public String getNroPedidoReceta() {
        return nroPedidoReceta;
    }

    public void setNroPedidoReceta(String nroPedidoReceta) {
        this.nroPedidoReceta = nroPedidoReceta;
    }

    public int getSecuencialDetalle() {
        return secuencialDetalle;
    }

    public void setSecuencialDetalle(int secuencialDetalle) {
        this.secuencialDetalle = secuencialDetalle;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    public String getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(String unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public int getFrecuenciaToma() {
        return frecuenciaToma;
    }

    public void setFrecuenciaToma(int frecuenciaToma) {
        this.frecuenciaToma = frecuenciaToma;
    }

    public int getDuracionToma() {
        return duracionToma;
    }

    public void setDuracionToma(int duracionToma) {
        this.duracionToma = duracionToma;
    }

    public int getCodViaAdministracion() {
        return codViaAdministracion;
    }

    public void setCodViaAdministracion(int codViaAdministracion) {
        this.codViaAdministracion = codViaAdministracion;
    }

    public String getDescViaAdministracion() {
        return descViaAdministracion;
    }

    public void setDescViaAdministracion(String descViaAdministracion) {
        this.descViaAdministracion = descViaAdministracion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public int getCantidadToma() {
        return cantidadToma;
    }

    public void setCantidadToma(int cantidadToma) {
        this.cantidadToma = cantidadToma;
    }

    public int getValFraccionamiento() {
        return valFraccionamiento;
    }

    public void setValFraccionamiento(int valFraccionamiento) {
        this.valFraccionamiento = valFraccionamiento;
    }


    public void setUnidadVentaFraccion(String unidadVentaFraccion) {
        this.unidadVentaFraccion = unidadVentaFraccion;
    }

    public String getUnidadVentaFraccion() {
        return unidadVentaFraccion;
    }
}
