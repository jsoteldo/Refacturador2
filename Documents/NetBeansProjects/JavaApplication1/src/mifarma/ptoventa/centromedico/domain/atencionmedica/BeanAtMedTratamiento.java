package mifarma.ptoventa.centromedico.domain.atencionmedica;

public class BeanAtMedTratamiento {
    
    private String codGrupoCia;
    private String codCia;
    private String codLocal;
    private String nroAtencionMedica;
    private String nroPedidoReceta;
    private String validezReceta;
    private String indicacionesGenerales;
    private BeanAtMedTrataReceta receta;
    
    public BeanAtMedTratamiento() {
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

    public String getNroPedidoReceta() {
        return nroPedidoReceta;
    }

    public void setNroPedidoReceta(String nroPedidoReceta) {
        this.nroPedidoReceta = nroPedidoReceta;
    }

    public String getValidezReceta() {
        return validezReceta;
    }

    public void setValidezReceta(String validezReceta) {
        this.validezReceta = validezReceta;
    }

    public String getIndicacionesGenerales() {
        return indicacionesGenerales;
    }

    public void setIndicacionesGenerales(String indicacionesGenerales) {
        this.indicacionesGenerales = indicacionesGenerales;
    }

    public BeanAtMedTrataReceta getReceta() {
        return receta;
    }

    public void setReceta(BeanAtMedTrataReceta receta) {
        this.receta = receta;
    }
}
