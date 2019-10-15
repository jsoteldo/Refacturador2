package mifarma.ptoventa.tomainventario.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;


public class ConstantsTomaInv {
    public ConstantsTomaInv() {
    }

    public static final String TIPO_OPERACION_TOMA_INV = "I";

    public static final String TIPO_OPERACION_CONSULTA_HIST = "H";
    /*
       * 22/03/2006   Paulo   Variables creadas para identificar el tipo de laboratorio
  */
    public static final String TIPO_FARMA = "01";
    public static final String TIPO_NO_FARMA = "02";
    public static final String TIPO_TODOS = "03";
    public static final String TIPO_PRINCIPIO_ACTIVO = "1";
    public static final String TIPO_ACCION_TERAPEUTICA = "2";
    public static final String TIPO_LABORATORIO = "3";


    /*Estado toma de inventario*/
    public static final String TOMA_CERRADA = "C";


    public static final FarmaColumnData[] columnsListaLaboratorios =
    { new FarmaColumnData("Sel", 40, JLabel.CENTER), new FarmaColumnData("Codigo", 75, JLabel.CENTER),
      new FarmaColumnData("Laboratorio", 290, JLabel.LEFT),
        /*
       * 22/03/2006   Paulo   Agregacion de una nueva columna para la lista de laboratorios
       */
        new FarmaColumnData("Tipo", 0, JLabel.LEFT), };

    public static final Object[] defaultValuesListaLaboratorios = { " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsHistoricoTomas =
    { new FarmaColumnData("Nro Toma", 65, JLabel.LEFT), new FarmaColumnData("Tipo Inv.", 90, JLabel.LEFT),
      new FarmaColumnData("Tipo Toma", 80, JLabel.LEFT), new FarmaColumnData("Fecha Inicio", 125, JLabel.LEFT),
      new FarmaColumnData("Fecha Fin", 125, JLabel.LEFT), new FarmaColumnData("Estado", 80, JLabel.LEFT),
      new FarmaColumnData("C.Estado", 0, JLabel.LEFT) };

    public static final Object[] defaultValuesHistoricoTomas = { " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsTomasInventario =
    { new FarmaColumnData("Nro Toma", 120, JLabel.LEFT),
      new FarmaColumnData("Tipo Toma", 200, JLabel.LEFT),
      new FarmaColumnData("Fecha Inicio", 250, JLabel.LEFT),
      new FarmaColumnData("Estado", 127, JLabel.LEFT),
      new FarmaColumnData("", 0, JLabel.LEFT), //};
        /**
       * Usuario de Creacion de la Toma
       * @author : dubilluz
       * @since  : 11.07.2007
       */
        new FarmaColumnData("", 0, JLabel.LEFT) };

    public static final Object[] defaultValuesTomasInventario = { " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsLaboratoriosToma =
    { new FarmaColumnData("Codigo", 100, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 352, JLabel.LEFT),
      new FarmaColumnData("Estado", 130, JLabel.LEFT), };

    public static final Object[] defaultValuesLaboratoriosToma = { " ", " ", " " };

    public static final FarmaColumnData[] columnsListaProductosXLaboratorio =
    { new FarmaColumnData("Codigo", 70, JLabel.CENTER), new FarmaColumnData("Descripcion", 235, JLabel.LEFT),
      new FarmaColumnData("Unid. Presentacion", 120, JLabel.LEFT), new FarmaColumnData("C. Ent", 60, JLabel.RIGHT),
      new FarmaColumnData("C. Frac", 60, JLabel.RIGHT), new FarmaColumnData("Valor Frac", 0, JLabel.RIGHT),
      new FarmaColumnData("Unid. Venta", 0, JLabel.RIGHT), new FarmaColumnData("Estado", 0, JLabel.RIGHT),
      new FarmaColumnData("Anaquel", 90, JLabel.RIGHT),
      new FarmaColumnData("Origen", 70, JLabel.RIGHT) //estado JVARA 29-09-2017
        } ;


    public static final Object[] defaultValuesListaProductosXLaboratorio =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " " }; // JVARA 29-09-2017

    public static final FarmaColumnData[] columnsListaDiferenciasProductos =
    { new FarmaColumnData("Codigo", 65, JLabel.CENTER), new FarmaColumnData("Descripcion", 220, JLabel.LEFT),
      new FarmaColumnData("Unidad", 110, JLabel.CENTER), new FarmaColumnData("Unidad Frac.", 100, JLabel.CENTER),
      new FarmaColumnData("U. Venta", 80, JLabel.CENTER), new FarmaColumnData("Cant.Actual", 75, JLabel.RIGHT),
      new FarmaColumnData("Cant.Frac.", 75, JLabel.RIGHT), new FarmaColumnData("Dif.", 75, JLabel.RIGHT),
      new FarmaColumnData("Dif. Frac.", 75, JLabel.RIGHT), new FarmaColumnData("P. Venta Unit.", 100, JLabel.RIGHT),
      new FarmaColumnData("P. Venta Total.", 100, JLabel.RIGHT), new FarmaColumnData("Anaquel", 90, JLabel.RIGHT),
      new FarmaColumnData("Costo", 90, JLabel.RIGHT) };


    public static final FarmaColumnData[] columnsListaDiferenciasProductosQumico =
    { new FarmaColumnData("Codigo", 65, JLabel.CENTER), new FarmaColumnData("Descripcion", 220, JLabel.LEFT),
      new FarmaColumnData("Unidad", 110, JLabel.CENTER), new FarmaColumnData("Unidad Frac.", 100, JLabel.CENTER),
      new FarmaColumnData("U. Venta", 110, JLabel.CENTER), new FarmaColumnData("Cant.Actual", 75, JLabel.RIGHT),
      new FarmaColumnData("Cant.Frac.", 75, JLabel.RIGHT), new FarmaColumnData("Dif.", 75, JLabel.RIGHT),
      new FarmaColumnData("Dif. Frac.", 75, JLabel.RIGHT), new FarmaColumnData("P. Venta Unit.", 100, JLabel.RIGHT),
      new FarmaColumnData("P. Venta Total.", 100, JLabel.RIGHT), new FarmaColumnData("Anaquel", 90, JLabel.RIGHT),
      new FarmaColumnData("Costo", 0, JLabel.RIGHT) };

    public static final Object[] defaultValuesListaDiferenciasProductos = { " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsListaLaboratoriosIxL =
    { new FarmaColumnData("Codigo", 65, JLabel.CENTER), new FarmaColumnData("Descripcion", 250, JLabel.LEFT),
      new FarmaColumnData("Dirección", 270, JLabel.LEFT), new FarmaColumnData("Teléfono", 130, JLabel.LEFT) };

    public static final Object[] defaultValuesListaLaboratoriosIxL = { " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsListaProductosIxL =
    { new FarmaColumnData("Codigo", 70, JLabel.CENTER), new FarmaColumnData("Descripcion", 225, JLabel.LEFT),
      new FarmaColumnData("Unidad Compra", 110, JLabel.LEFT), new FarmaColumnData("Unidad Venta", 110, JLabel.LEFT),
      new FarmaColumnData("Stk Min.", 45, JLabel.RIGHT), new FarmaColumnData("Ent", 45, JLabel.RIGHT),
      new FarmaColumnData("Frac", 45, JLabel.RIGHT), new FarmaColumnData("Precio", 55, JLabel.RIGHT), };

    public static final Object[] defaultValuesListaProductosIxL = { " ", " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsListaDiferenciasConsolidado =
    { new FarmaColumnData("Codigo", 65, JLabel.CENTER), new FarmaColumnData("Descripcion", 220, JLabel.LEFT),
      new FarmaColumnData("Unid Presentacion", 110, JLabel.LEFT), new FarmaColumnData("Laboratorio", 75, JLabel.RIGHT),
      new FarmaColumnData("Fracción", 75, JLabel.RIGHT), new FarmaColumnData("Cant Entera", 65, JLabel.RIGHT),
      new FarmaColumnData("Cant Fracción", 150, JLabel.LEFT), new FarmaColumnData("Cant Total ", 0, JLabel.LEFT),
      new FarmaColumnData("P.Costo", 0, JLabel.LEFT), new FarmaColumnData("P.Costo Total", 0, JLabel.LEFT),
      new FarmaColumnData("P.Venta", 0, JLabel.LEFT), new FarmaColumnData("P.Venta Total", 0, JLabel.LEFT) };


    public static final Object[] defaultValuesListaDiferenciasConsolidado =
    { " ", " ", " ", " ", " ", " ", " ", " ", " " };


    public static final FarmaColumnData[] columnsListaDiferenciasConsolidadoDiario =
    { new FarmaColumnData("Codigo", 65, JLabel.CENTER), new FarmaColumnData("Descripcion", 220, JLabel.LEFT),
      new FarmaColumnData("Unid Presentacion", 110, JLabel.LEFT),
      new FarmaColumnData("Stock Actual", 75, JLabel.RIGHT), new FarmaColumnData("Diferencia", 75, JLabel.RIGHT),
      new FarmaColumnData("Precio", 75, JLabel.RIGHT), new FarmaColumnData("Laboratorio", 150, JLabel.LEFT),
      new FarmaColumnData("ValFrac", 0, JLabel.LEFT) };

    public static final Object[] defaultValuesListaDiferenciasConsolidadoDiario =
    { " ", " ", " ", " ", " ", " ", " ", " " };


    public static final FarmaColumnData[] columnsListaDiferenciasConsolidados =
    { new FarmaColumnData("Codigo", 65, JLabel.CENTER), new FarmaColumnData("Descripcion", 220, JLabel.LEFT),
      new FarmaColumnData("Unid Presentacion", 110, JLabel.LEFT),
      new FarmaColumnData("Laboratorio", 150, JLabel.LEFT) };

    public static final Object[] defaultValuesListaDiferenciasConsolidados =
    { " ", " ", " ", " " }; //, " ", " "," " };


    public static String vNombreInHashtableDiferencias = "IND_CAMPO_ORDENAR_DIFERENCIAS";
    public static String[] vCodDiferencia = { "0", "1", "2", "3", "4", "5", "6" };
    public static String[] vDescCampoDiferencia =
    { "Codigo", "Descripcion", "Unidad Presentacion", "Stock Actual", "Diferencia", "Precio", "Laboratorio" };

    /**
     *
     */
    public static String NOM_HASTABLE_CMBESTADO_LAB = "CMBESTADO";


    public static final FarmaColumnData columnsListaProductosConteo[] =
    { new FarmaColumnData("Código Prod.", 80, JLabel.LEFT), new FarmaColumnData("Descripción", 170, JLabel.LEFT),
      new FarmaColumnData("Unidad Presentación", 130, JLabel.LEFT), new FarmaColumnData("Cant.Ent", 60, JLabel.RIGHT),
      new FarmaColumnData("Cant.Frac", 60, JLabel.RIGHT), new FarmaColumnData("Laboratorio", 190, JLabel.LEFT),
      new FarmaColumnData("Ingreso", 60, JLabel.CENTER) };

    public static final Object[] defaultValuesListaProductosConteo = { " ", " ", " ", " ", " ", " ", " ", " ", " " };


    /**
     * Diferencias TOtales
     * dubilluz 29.12.2009
     */


    public static final FarmaColumnData[] columnsListaDiferenciasTotales =
    { new FarmaColumnData("Laboratorio", 150, JLabel.LEFT),
      new FarmaColumnData("Codigo", 65, JLabel.CENTER),
      new FarmaColumnData("Descripcion", 300, JLabel.LEFT),
      new FarmaColumnData("Unidad", 110, JLabel.LEFT),
      new FarmaColumnData("U. Fraccion", 55, JLabel.LEFT),
      new FarmaColumnData("U. Venta", 55, JLabel.RIGHT),
      new FarmaColumnData("Cant", 55, JLabel.RIGHT),
      new FarmaColumnData("Cant. Fracc", 65, JLabel.RIGHT), 
      new FarmaColumnData("Dif.", 65, JLabel.RIGHT),
      new FarmaColumnData("Dif. Fracc", 65, JLabel.RIGHT), 
      new FarmaColumnData("Total Prod", 0, JLabel.RIGHT),
      new FarmaColumnData("P. Venta", 80, JLabel.RIGHT), 
      new FarmaColumnData("P.Venta Total", 80, JLabel.RIGHT),
      new FarmaColumnData("P. Costo", 80, JLabel.RIGHT), 
      new FarmaColumnData("P. Costo Total", 100, JLabel.RIGHT),
      new FarmaColumnData("Anaquel", 65, JLabel.RIGHT), 
      new FarmaColumnData("Anaq", 0, JLabel.RIGHT) };

    // TABLA PARA ROL QUIMICO


    public static final FarmaColumnData[] columnsListaDiferenciasTotalesQuimico =
    { new FarmaColumnData("Laboratorio", 150, JLabel.LEFT),
      new FarmaColumnData("Codigo", 65, JLabel.CENTER),
      new FarmaColumnData("Descripcion", 300, JLabel.LEFT),
      new FarmaColumnData("Unidad", 110, JLabel.LEFT),
      new FarmaColumnData("U. Fraccion", 55, JLabel.LEFT),
      new FarmaColumnData("U. Venta", 55, JLabel.RIGHT),
      new FarmaColumnData("Cant", 55, JLabel.RIGHT),
      new FarmaColumnData("Cant. Fracc", 65, JLabel.RIGHT), 
      new FarmaColumnData("Dif.", 65, JLabel.RIGHT),
      new FarmaColumnData("Dif. Fracc", 65, JLabel.RIGHT), 
      new FarmaColumnData("Total Prod", 0, JLabel.RIGHT),
      new FarmaColumnData("P. Venta", 80, JLabel.RIGHT), 
      new FarmaColumnData("P.Venta Total", 80, JLabel.RIGHT),
      new FarmaColumnData("P. Costo", 0, JLabel.RIGHT), 
      new FarmaColumnData("TOTAL_PREC_AUDITOR", 0, JLabel.RIGHT),
      new FarmaColumnData("Anaquel", 65, JLabel.RIGHT) };


    public static final Object[] defaultValuesListaDiferenciasTotales =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData[] columnsListaProductos =
    { new FarmaColumnData("Codigo", 70, JLabel.CENTER), 
      new FarmaColumnData("Descripcion", 235, JLabel.LEFT),
      new FarmaColumnData("Unidad", 120, JLabel.LEFT), 
      new FarmaColumnData("Laboratorio.", 100, JLabel.LEFT), 
      new FarmaColumnData("C. Ent", 60, JLabel.RIGHT),
      new FarmaColumnData("C. Frac", 60, JLabel.RIGHT), 
      new FarmaColumnData("Valor Frac", 0, JLabel.RIGHT),
      new FarmaColumnData("Unid. Venta", 0, JLabel.RIGHT), 
      new FarmaColumnData("Estado", 0, JLabel.RIGHT),
      new FarmaColumnData("Anaquel", 90, JLabel.RIGHT), 
      new FarmaColumnData("Origen", 70, JLabel.RIGHT),
        //estado SLEYVA 08/04/2019
        new FarmaColumnData("Lab", 0, JLabel.RIGHT) };


    public static final Object[] defaultValuesListaProductos =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " }; // SLEYVA 08/04/2019

}
