package  mifarma.ptoventa.tomainventario.valueobjects;

import java.text.ParseException;

import mifarma.common.FarmaUtility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.

 * ID     PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIÓN
 * 000    RAZANERO       20/09/2006 18:50:00   Creación <br>
 * 001    JREBATTA       10/10/2011 11:54:00   Modificación         Hacer que se almacene el VA_COSTO_PRODUCTO en las tablas de inventario<br>
 * 002    GAZABACHE      25/10/2018 11:54:00   Modificación         obtener el precio de venta<br>
 */
public class ProductoLaboratorio {

    private String coCompania;
    private String coLocal;

    private String nuRevision = "0";
    private String nuAnaquel;
    private String deUnidadFraccion;
    private Number vaFraccion;
    private String inProdFraccionado;
    private String caStockDisponible;
    private String coLaboratorio;
    private String deLaboratorio;
    private String deCortaProducto;
    private String deProducto;
    private String deUnidad;
    private String caEntero;
    private String caFraccion;
    private String difEntero;
    private String difFraccion;
    private String caEnteroPDA;
    private String caFraccionPDA;
    private String esNuevo = "N";
    private String codigoBarra;
    private String vaPrecioPromedio;
    private String faltante = "1";
    //Inicio ID:002
    private String  vaPrecioVenta;
    //Fin ID:002
    private String coProducto;
    private Number vaVenta;
    private String coMoneda;
    private Number vaContenidoFraccion;
    private Number pcDescuento;
    private String esProdLocal;

    private String caEnteroDispinible;
    private String caFraccionDisponible;

    private final Log logger = LogFactory.getLog(getClass());

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getNuRevision() {
        return nuRevision;
    }

    public void setNuRevision(String nuRevision) {
        this.nuRevision = nuRevision;
    }

    public String getDeUnidadFraccion() {
        return deUnidadFraccion;
    }

    public void setDeUnidadFraccion(String deUnidadFraccion) {
        this.deUnidadFraccion = deUnidadFraccion;
    }

    public Number getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(Number vaFraccion) {
        this.vaFraccion = vaFraccion;
    }

    public String getInProdFraccionado() {
        return inProdFraccionado;
    }

    public void setInProdFraccionado(String inProdFraccionado) {
        this.inProdFraccionado = inProdFraccionado;
    }

    public String getCaStockDisponible() {
        return caStockDisponible;
    }

    public void setCaStockDisponible(String caStockDisponible) {
        this.caStockDisponible = caStockDisponible;
    }

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
    }

    public String getDeLaboratorioCustom() {
        char comilla = '\'';
        char space = ' ';
        return deLaboratorio.replace(comilla, space);
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public String getDeCortaProducto() {
        return deCortaProducto;
    }

    public void setDeCortaProducto(String deCortaProducto) {
        this.deCortaProducto = deCortaProducto;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public String getDeProductoCustom() {
        char comilla = '\'';
        char space = ' ';
        return deProducto.replace(comilla, space);
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }

    public String getDeUnidad() {
        return deUnidad;
    }

    public void setDeUnidad(String deUnidad) {
        this.deUnidad = deUnidad;
    }


    public String getCaEntero() {
        return caEntero;
    }

    public void setCaEntero(String caEntero) {
        this.caEntero = caEntero;
    }

    public String getCaFraccion() {
        return caFraccion;
    }

    public void setCaFraccion(String caFraccion) {
        this.caFraccion = caFraccion;
    }

    public int getCaCuenta() {
        int cantidadCuenta = Integer.parseInt(getCaEntero());
        if (getInProdFraccionado().equals("S")) {
            cantidadCuenta = (cantidadCuenta * getVaFraccion().intValue() + Integer.parseInt(getCaFraccion()));
        }

        return cantidadCuenta;
    }

    public ArrayList toArrray() {
        ArrayList fila = new ArrayList();
        fila.add(coProducto);
        fila.add(deProducto);
        fila.add(deLaboratorio);
        fila.add(deUnidad);
        fila.add("");

        return fila;
    }

    public String toString() {
        StringBuffer str = new StringBuffer()
                .append(" coProducto :")
                .append(coProducto)
                .append(" deProducto :")
                .append(deProducto)
                .append(" deUnidad :")
                .append(deUnidad)
                .append(" codigoLaboratorio :")
                .append(coLaboratorio)
                .append(" DeLaboratorio :")
                .append(deLaboratorio)
                .append(" inProductoFraccionado :")
                .append(inProdFraccionado)
                .append(" vaFraccion :")
                .append(vaFraccion)
                .append(" vaFraccion :")
                .append(vaFraccion)
                .append(" vaPrecioPromedio:")
                .append(vaPrecioPromedio)
                .append(" difEntera:")
                .append(difEntero)
                .append(" difFracc:")
                .append(difFraccion)
                .append(" caEntero :")
                .append(caEntero)
                .append(" caFrac :")
                .append(caFraccion);

        return str.toString();
    }

    public String getCaEnteroPDA() {
        return caEnteroPDA;
    }

    public void setCaEnteroPDA(String caEnteroPDA) {
        this.caEnteroPDA = caEnteroPDA;
    }

    public String getCaFraccionPDA() {
        return caFraccionPDA;
    }

    public void setCaFraccionPDA(String caFraccionPDA) {
        this.caFraccionPDA = caFraccionPDA;
    }

    public String getEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(String esNuevo) {
        this.esNuevo = esNuevo;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Number getVaVenta() {
        return vaVenta;
    }

    public void setVaVenta(Number vaVenta) {
        this.vaVenta = vaVenta;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public Number getVaContenidoFraccion() {
        return vaContenidoFraccion;
    }

    public void setVaContenidoFraccion(Number vaContenidoFraccion) {
        this.vaContenidoFraccion = vaContenidoFraccion;
    }

    public Number getPcDescuento() {
        return pcDescuento;
    }

    public void setPcDescuento(Number pcDescuento) {
        this.pcDescuento = pcDescuento;
    }

    public String getEsProdLocal() {
        return esProdLocal;
    }

    public void setEsProdLocal(String esProdLocal) {
        this.esProdLocal = esProdLocal;
    }


    public String getDifEntero() {
        return difEntero;
    }

    public void setDifEntero(String difEntero) {
        this.difEntero = String.valueOf(FarmaUtility.trunc(difEntero.trim().equals("") ? "0" : difEntero.trim()));
    }

    public String getDifFraccion() {
        return difFraccion;
    }

    public void setDifFraccion(String difFraccion) {
        this.difFraccion = String.valueOf(FarmaUtility.trunc(difFraccion.trim().equals("") ? "0" : difFraccion.trim()));
    }

    public String getVaPrecioPromedio() {
        return vaPrecioPromedio;
    }

    public void setVaPrecioPromedio(String vaPrecioPromedio) {
        this.vaPrecioPromedio = String.valueOf(getDecimalNumber(vaPrecioPromedio.trim().equals("") ? "0" : vaPrecioPromedio.trim(), 5));
    }
    
    public static double getDecimalNumber(String pDecimal, int pDecimales) {
        double decimalNumber = 0.00;
        try {
            java.text.DecimalFormat formatDecimal = new java.text.DecimalFormat("###,##0." + caracterIzquierda("", pDecimales, "0"));
            java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();

            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            formatDecimal.setDecimalFormatSymbols(symbols);
            decimalNumber = formatDecimal.parse(pDecimal).doubleValue();
        } catch (ParseException errParse) {
    //            System.out.print(pDecimal + " -> " + errParse.getMessage());
            //Inicio ID:010
          //  log.error("No se puede parsear el valor "+pDecimal+" con "+pDecimales+"decimales");
            //Fin ID:010
            //Inicio ID:011
            //errParse.printStackTrace();
            //Fin ID:011
        }
        return decimalNumber;
    }
    
    public static String caracterIzquierda(int parmint, int parmLen, String parmCaracter) {
        return caracterIzquierda(String.valueOf(parmint), parmLen, parmCaracter);
    }
    


    
    public static String caracterIzquierda(String parmString, int parmLen, String parmCaracter) {

        String tempString = parmString;

        if (tempString.length() > parmLen)
            tempString = tempString.substring(tempString.length() - parmLen,
                    tempString.length());
        else {
            while (tempString.length() < parmLen)
                tempString = parmCaracter + tempString;
        }

        return tempString;

    }

    public int getTotalUnidades() {
        int total = 0;
        total = Integer.parseInt(difEntero.equals("") ? "0" : difEntero);
        if (inProdFraccionado.equals("S")) {
            total = total * vaFraccion.intValue();
            total += Integer.parseInt(difFraccion.equals("") ? "0" : difFraccion);
        }
        setFaltante(total <= 0 ? "1" : "0");
        return total;
    }

    public double getTotalCostoPromedio() {
        double total = 0;

        try {
            //Inicio ID: 001
            if (inProdFraccionado.equals("S")) {
                total = (Integer.parseInt(difEntero.trim())) * vaFraccion.intValue() * Double.parseDouble(vaPrecioPromedio.trim());
                total += (Integer.parseInt(difFraccion.trim())) * Double.parseDouble(vaPrecioPromedio);
            } else {
                total = (Integer.parseInt(difEntero.trim())) * Double.parseDouble(vaPrecioPromedio.trim());
            }

            /*total = (Integer.parseInt(difEntero)) * Double.parseDouble(vaPrecioPromedio);
            if (inProdFraccionado.equals("S")) {
                total += (Integer.parseInt(difFraccion)) * ((Double.parseDouble(vaPrecioPromedio)) / vaFraccion.intValue());
            } */
            //Fin ID:001
        } catch (NumberFormatException e) {
            logger.info(toString());
            e.printStackTrace();
        }

        setFaltante(getTotalUnidades() < 0 ? "1" : "0");
        return total;
    }

    //Inicio ID:002
    public double getTotalCostoPromedioPrecioVenta() {
        double total = 0;

        try {
            /*if (inProdFraccionado.equals("S")) {
                total = (Integer.parseInt(difEntero.trim())) * vaFraccion.intValue() * Double.parseDouble(vaPrecioVenta.trim());
                total += (Integer.parseInt(difFraccion.trim())) * Double.parseDouble(vaPrecioVenta);
            } else {
                total = (Integer.parseInt(difEntero.trim())) * Double.parseDouble(vaPrecioVenta.trim());
            }*/
            //AITURRIZAGA: 07/02/2019
            if (inProdFraccionado.equals("S")) {
                total = (Integer.parseInt(difEntero.trim())) * FarmaUtility.trunc(vaFraccion.toString()) * FarmaUtility.getDecimalNumber(vaPrecioVenta.trim());
                total += (Integer.parseInt(difFraccion.trim())) * FarmaUtility.getDecimalNumber(vaPrecioVenta);
            } else {
                total = (Integer.parseInt(difEntero.trim())) * FarmaUtility.getDecimalNumber(vaPrecioVenta.trim());
            }

        } catch (NumberFormatException e) {
            logger.info(toString());
            e.printStackTrace();
        }

        setFaltante(getTotalUnidades() < 0 ? "1" : "0");
        return total;
    }
    //Fin ID:002
    public double getTotalCostoPromedioInicial() {         
        double total = 0;

        try {
            //Inicio ID: 001
            if (inProdFraccionado.equals("S")) {
                total = (Integer.parseInt(caEnteroDispinible.trim())) * vaFraccion.intValue() * Double.parseDouble(vaPrecioPromedio.trim());
                total += (Integer.parseInt(caFraccionDisponible.trim())) * Double.parseDouble(vaPrecioPromedio.trim());
            } else {
                total = (Integer.parseInt(caEnteroDispinible.trim())) * Double.parseDouble(vaPrecioPromedio);
            }

            /*total = EckerdUtility.trunc(caEnteroDispinible.trim()) * EckerdUtility.getDecimalNumber(vaPrecioPromedio);
            if (inProdFraccionado.equals("S")) {
                total += (EckerdUtility.trunc(caFraccionDisponible.trim())) * ((EckerdUtility.getDecimalNumber(vaPrecioPromedio)) / vaFraccion.intValue());
            }*/
            //Fin ID: 001
        } catch (NumberFormatException e) {
            logger.info(toString());
            e.printStackTrace();
        }

        return total;
    }

    public String getFaltante() {
        setFaltante(getTotalUnidades() < 0 ? "1" : "0");
        return faltante;
    }

    public void setFaltante(String faltante) {
        this.faltante = faltante;
    }

    public String getCaEnteroDispinible() {
        return caEnteroDispinible;
    }

    public void setCaEnteroDispinible(String caEnteroDispinible) {
        this.caEnteroDispinible = caEnteroDispinible;
    }

    public String getCaFraccionDisponible() {
        return caFraccionDisponible;
    }

    public void setCaFraccionDisponible(String caFraccionDisponible) {
        this.caFraccionDisponible = caFraccionDisponible;
    }

    //Inicio ID:002
    public void setVaPrecioVenta(String vaPrecioVenta) {
        this.vaPrecioVenta = vaPrecioVenta;
    }

    public String getVaPrecioVenta() {
        return vaPrecioVenta;
    }
    //Fin ID:002
    
    //DFLORES: 01/01/2019
    public String getNuAnaquel() {
        return nuAnaquel;
    }
    
    //DFLORES: 01/01/2019
    public void setNuAnaquel(String nuAnaquel) {
        this.nuAnaquel = nuAnaquel;
    }
}
