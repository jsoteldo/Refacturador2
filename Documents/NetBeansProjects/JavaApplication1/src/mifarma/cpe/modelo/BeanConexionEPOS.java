package mifarma.cpe.modelo;

public class BeanConexionEPOS {
    
    private String ipServidor;
    private int puerto;
    private String modo;
    private String codLocal;
    private String rucCia;
    
    public BeanConexionEPOS() {
        super();
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getModo() {
        return modo;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setRucCia(String rucCia) {
        this.rucCia = rucCia;
    }

    public String getRucCia() {
        return rucCia;
    }
}
