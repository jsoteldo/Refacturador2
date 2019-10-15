package mifarma.ptoventa.iscbolsas.reference;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.reference.DBReceta;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.ventas.DlgIngresoCantidad;
import mifarma.ptoventa.ventas.DlgListaProductos;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;

import mifarma.ptoventa.ventas.reference.UtilityCalculoPrecio;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UtilityISCBolsas {

    private static final Log logger = LogFactory.getLog(UtilityISCBolsas.class);

    public static void agregarProductoISCBolsa() {

    }

    public static void obtenerProductosISC(ArrayList pArrayList) {
        try {
            DBISCBolsas.obtenerProductosISC(pArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error al obtener productos ISC");
        }
    }

    public static boolean pblTabGral_ICBPer() {
        boolean icbper = false;
        try {
            icbper = DBISCBolsas.getPblTabGral_ICBPer();
        } catch (SQLException e) {
            logger.error("Error al obtener productos ISC");
        }
        return icbper;
    }

    public static boolean getPblTabGral_BolsasAmarillas() {
        boolean resul = false;
        try {
            resul = DBISCBolsas.getPblTabGral_BolsasAmarillas();
        } catch (SQLException e) {
            logger.error("Error al obtener productos ISC");
        }
        return resul;
    }

    private static void agregarProductosISCAPedidos() {

        for (int i = 0; i < VariablesISCBolsas.vArrayList_ProductosPedidos.size(); i++) {

            String codigoProducto =
                FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosPedidos, i, 0);
            int cantidad =
                FarmaUtility.trunc(FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosPedidos,
                                                                                                     i, 6)));

            //if(updateStockProductos(codigoProducto, cantidad, "A")){
            //VariablesVentas.arrayPedidos.add((ArrayList) VariablesISCBolsas.vArrayList_ProductosPedidos.get(i));
            VariablesVentas.vArrayList_PedidoVenta.add((ArrayList)VariablesISCBolsas.vArrayList_ProductosPedidos.get(i));
            //}
            //FarmaUtility.operaListaProd(VariablesVentas.vArrayList_PedidoVenta, VariablesISCBolsas.vArrayList_ProductosPedidos.get(i), true, 0);

        }

        UtilityCalculoPrecio.clearDetalleVenta();
        UtilityCalculoPrecio.uploadDetalleVenta(VariablesVentas.vArrayList_PedidoVenta);


    }


    public static String generarArrayProductosISCPedidos() {
        String mensaje = "";
        /*VariablesISCBolsas.vArrayList_ProductosPedidos = new ArrayList();

        //ArrayList arrayInfoBolsa = null;
        //ArrayList arrayElementos = null;
        for(int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC_Temp.size(); i++){
            String codigoProducto = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 0);
            String costoISC = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 1);
            String precVta = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 2);
            String tipo = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 3);
            String cantidad = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 4);
            String subTotal = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 5);
            String totalISC = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 6);
            String total = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 7);

            // DMOSQUEIRA 17072019: MAPEO DE ARRAYLIST -- INICIO
            ArrayList myArray = new ArrayList();
            myArray.add(codigoProducto);            //-----------------------------------------------------------------------------  0: codProducto
            myArray.add(tipo.trim().equals(VariablesISCBolsas.vTipoBolsaMediano)?"BOLSA MEDIANA":"BOLSA GRANDE"); //---------------  1: descripcion
            myArray.add("BOLSA"); //-----------------------------------------------------------------------------------------------  2: unidadVenta
            myArray.add(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(precVta), 3)); //----------------------------------  3: precioVenta

            int cant = ((Double)FarmaUtility.getDecimalNumber(cantidad)).intValue();
            myArray.add(String.valueOf(cant)); // ---------------------------------------------------------------------------------  4: cantidad

            myArray.add(""); //myArray.add(VariablesVentas.vPorc_Dcto_1);se supone que este descuento ya no se aplica -------------  5: vPctDcto
            myArray.add(precVta); //-----------------------------------------------------------------------------------------------  6: precioVenta
            myArray.add(FarmaUtility.formatNumber(FarmaUtility.getDecimalNumber(subTotal), 2)); //---------------------------------  7: subTotal
            myArray.add(""); //----------------------------------------------------------------------------------------------------  8: vValBono
            myArray.add(""); //9 --------------------------------------------------------------------------------------------------  9: vNomLab
            myArray.add("1"); //----------------------------------------------------------------------------------------------------  10: vValFracVta
            myArray.add("0"); //---------------------------------------------------------------------------------------------------  11: vPctIGV
            myArray.add("0.0"); //-------------------------------------------------------------------------------------------------  12: vValIgv
            myArray.add(""); //NUMERO TELEFONICO SI ES RECARGA AUTOMATICA ---------------------------------------------------------  13: vNumTelefRecarga
            myArray.add(""); //INDICADOR DE PRODUCTO VIRTUAL ----------------------------------------------------------------------  14: vIndProdVirtual
            myArray.add(""); //TIPO DE PRODUCTO VIRTUAL ---------------------------------------------------------------------------  15: vTipoProdVirtual
            myArray.add(""); //INDICADOR PROD CONTROLA STOCK ----------------------------------------------------------------------  16: vIndControlaStock
            myArray.add(""); //PRECIO DE LISTA ORIGINAL SI ES QUE SE MODIFICO -----------------------------------------------------  17: vValPrecioLista_1
            myArray.add(""); //----------------------------------------------------------------------------------------------------  18: vValPrecPublico
            myArray.add(""); //19 -------------------------------------------------------------------------------------------------  19: vIndOrigenProd
            myArray.add(""); //20 Indicador Promocion -----------------------------------------------------------------------------  20: vIndProdPromocion
            myArray.add(""); //21 -------------------------------------------------------------------------------------------------  21: vPctDcto_2
            myArray.add(""); //22 -------------------------------------------------------------------------------------------------  22: vIndTratamiento
            myArray.add(""); //23 -------------------------------------------------------------------------------------------------  23: vCtdXDia
            myArray.add(""); //24 -------------------------------------------------------------------------------------------------  24: vCtdDias
            myArray.add(""); //25 -------------------------------------------------------------------------------------------------  25: vCodCupon
            myArray.add(""); //ASOSA, 01.07.2010 ----------------------------------------------------------------------------------  26: vSecRespaldoStk
            //logger.info("<<TCT 1>>Producto agregado al pedidoVenta: " + myArray);

            myArray.add(""); // ---------------------------------------------------------------------------------------------------  27: vValorMultiplicacion
            myArray.add(""); // ---------------------------------------------------------------------------------------------------  28: vTipoProducto
            // PRECIO DE CONVENIO PARA VERIFICAR EN ALGORITMO DE MEJOR PRECIO
            myArray.add(""); //----------------------------------------------------------------------------------------------------  29: vValPrecConvenio
            myArray.add("");//30 --------------------------------------------------------------------------------------------------  30: vValPrecioLista_2
            myArray.add(""); //31 -------------------------------------------------------------------------------------------------  31: vCant_Ingresada_ItemQuote
            //logger.debug("ini UtilityVentas: " + VariablesVentas.vArrayList_PedidoVenta);
            //Se agrega el codigo de regalo de referencia
            myArray.add("N");//----------------------------------------------------------------------------------------------------  32: indice
            myArray.add("");//-----------------------------------------------------------------------------------------------------  33: pCodCampAcumula
            myArray.add("");//-----------------------------------------------------------------------------------------------------  34: pCodProdRegalo
            myArray.add("");//-----------------------------------------------------------------------------------------------------  35: pCodEQCampAcumula

            myArray.add("RECETARIO+POS36");//--------------------------------------------------------------------------------------  36: vProcentajeInsumo
            myArray.add("RECETARIO+POS37");//--------------------------------------------------------------------------------------  37: vFactorConversion
            myArray.add("RECETARIO+POS38");//--------------------------------------------------------------------------------------  38: no es seteado en BeanDetalleVenta
            myArray.add(costoISC); //39 -------------------------------------------------------------------------------------------  39: Costo ICBPER
            myArray.add(totalISC); //40 -------------------------------------------------------------------------------------------  40: Total ICBPER

            // DMOSQUEIRA 17072019: MAPEO DE ARRAYLIST -- FIN
            FarmaUtility.operaListaProd(VariablesISCBolsas.vArrayList_ProductosPedidos, myArray, true, 0);
            logger.debug("fin UtilityISCBolsas: " + VariablesISCBolsas.vArrayList_ProductosPedidos);
        }
        agregarProductosISCAPedidos();*/

        return mensaje;
    }

    private static void obtieneInfoProdEnArrayList(ArrayList pArrayList) {
        try {
            //ERIOS 06.06.2008 Solución temporal para evitar la venta sugerida por convenio
            if (VariablesVentas.vEsPedidoConvenio) {
                DBVentas.obtieneInfoDetalleProducto(pArrayList, VariablesVentas.vCod_Prod);
            } else {
                DBVentas.obtieneInfoDetalleProductoVta(pArrayList, VariablesVentas.vCod_Prod);
            }

        } catch (SQLException sql) {
            logger.info(null, sql.fillInStackTrace());
            //FarmaUtility.showMessage(this, "Error al obtener informacion del producto. \n" + sql.getMessage(), txtCantidad);
        }
    }

    /**
     * Setea los valores adicionales en las variables si es que existen.
     * La primera vez entrara el producto porque la variable de codigo de barras tiene datos, las demas veces ya no
     * @author ASOSA
     * @since 06/08/2014
     */
    public static void setearValoresAdicionales() {

        String[] list = UtilityVentas.obtenerArrayValoresBd(ConstantsVentas.TIPO_VAL_ADIC_COD_BARRA);
        String new_frac = "";
        String new_desc = "";
        String new_precio = "";
        String stockLocal = "";
        String fracProdLocal = "";
        if (!list[0].equals("THERE ISNT")) {
            new_frac = list[0];
            new_desc = list[1];
            new_precio = list[2];
            stockLocal = list[3];
            fracProdLocal = list[4];

            VariablesVentas.vStk_Prod = "" + (Integer.parseInt(stockLocal) / Integer.parseInt(new_frac));

            //Double valMult = 1.00 / Integer.parseInt(new_frac);
            VariablesVentas.vValorMultiplicacion = new_frac.trim();
            //VariablesVentas.vVal_Frac = "" + valMult;
            VariablesVentas.vDesc_Prod = VariablesVentas.vDesc_Prod + " " + new_desc;
            VariablesVentas.vVal_Prec_Vta = new_precio.trim();

            VariablesVentas.existenValoresAdicionales = true;
        } else {
            //VariablesVentas.vValorMultiplicacion = "1";
            VariablesVentas.existenValoresAdicionales = false;
        }
        VariablesVentas.vCodigoBarra = "";
        logger.debug("VariablesVentas.vCodigoBarra 09: " + VariablesVentas.vCodigoBarra);
        logger.debug("VariablesVentas.vVal_Frac" + VariablesVentas.vVal_Frac);
        logger.debug("VariablesVentas.vUnid_Vta" + VariablesVentas.vUnid_Vta);
        logger.debug("VariablesVentas.vVal_Prec_Vta" + VariablesVentas.vVal_Prec_Vta);
        logger.debug("VariablesVentas.vStk_Prod" + VariablesVentas.vStk_Prod);

    }

    public static boolean verificarProductoBolsa(String codigoProducto) {
        for (int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC.size(); i++) {
            if (FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, ConstantsISCBolsas.COL_CO_PRODUCTO).equals(codigoProducto)) {
                return true;
            }
        }
        return false;
    }

    public static void removeItemBolsa(ArrayList productosActuales, String pCodProducto) {
        try {
            for (int i = productosActuales.size() - 1; i > -1; i--) {
                ArrayList temp = (ArrayList)productosActuales.get(i);

                if (((String)temp.get(0)).trim().length() < 8) {
                    if (((String)temp.get(0)).trim().equals(pCodProducto))
                        productosActuales.remove(i);
                }
            }
        } catch (Exception ex) {
            logger.error("Problemas al tratar de restablecer stock.", ex);
        }
    }
    
    //INI ZVILLAFUERTE 20190806
    public static void obtieneInfoProdEnArrayList(JDialog pJDialog, ArrayList pArrayList, Object pObjectFocus, String pCodProd) {        
        try {
            DBVentas.obtieneInfoProductoVta(pArrayList, pCodProd);
        } catch (SQLException sql) {
            logger.error("", sql);
            FarmaUtility.showMessage(pJDialog, "Error al obtener informacion del producto en Arreglo. \n" +
                    sql.getMessage(), pObjectFocus);
        }
    }
    //FIN ZVILLAFUERTE 20190806


    public static void obtenerProductosISC_Prod(ArrayList pArrayList,String pCodProd) {
        try {
            DBISCBolsas.obtenerProductosISC(pArrayList,pCodProd);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error al obtener productos ISC");
        }
    }
    
    //INI ZVILLAFUERTE 20190806
    public static String obtenerCodigoProducto(String tipo){
        String codigoProducto = "";
        for(int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC.size(); i++){
            
            if(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, ConstantsISCBolsas.COL_TI_BOLSA).trim().equals(tipo)){
                codigoProducto = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, ConstantsISCBolsas.COL_CO_PRODUCTO);
           }    
            
        }
        return codigoProducto;
    }
    //FIN ZVILLAFUERTE 20190806

}
