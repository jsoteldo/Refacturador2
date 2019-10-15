package mifarma.ptoventa.centromedico.domain.atencionmedica;

import java.util.ArrayList;

public class BeanAtMedTrataReceta {
    
    private String codGrupoCia;
    private String codLocal;
    private String nroPedidoReceta;
    private int cantidadItems;
    private ArrayList<BeanAtMedTrataRecetaDetalle> detalles;
    //2017.05.18
    private String codMedico;
    
    public BeanAtMedTrataReceta() {
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

    public int getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(int cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public ArrayList<BeanAtMedTrataRecetaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<BeanAtMedTrataRecetaDetalle> detalles) {
        this.detalles = detalles;
    }

    public void setCodMedico(String codMedico) {
        this.codMedico = codMedico;
    }

    public String getCodMedico() {
        return codMedico;
    }
}
