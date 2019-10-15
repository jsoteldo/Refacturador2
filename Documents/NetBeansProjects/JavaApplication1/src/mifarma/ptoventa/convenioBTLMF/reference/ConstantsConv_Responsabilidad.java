package mifarma.ptoventa.convenioBTLMF.reference;

import java.util.ArrayList;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;

/**
 * Copyright (c) 2006 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : ConstantsConv_Responsabilidad.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * JMONZALVE      25.04.2019   Creación<br>
 * <br>
 * @author Jhony Monzalve V.<br>
 * @version 1.0<br>
 *
 */

public class ConstantsConv_Responsabilidad {
    
    public static final String NOM_HASTABLE_CMBMOTIVOS_RESPONSABILIDAD = "CMB_MOTIVOS_RESPONSABILIDAD";
    public static ArrayList listaEmpleados_CobroResponsabilidad = new ArrayList();
    public static final String COD_FPAGO_COBRO_RESP = "00097";
    public static final String DESC_FPAGO_COBRO_RESP = "TARJ. COBRO RESPONSABILIDAD";    
    public static String vFechaInicio = "";
    public static String vFechaFin = "";
    public static String extension = "pdf";

    public ConstantsConv_Responsabilidad() {
    }
    
    public static final FarmaColumnData columnsListaEmpleado_Cobro[] =
    {   new FarmaColumnData("Código Trabajador", 120, JLabel.CENTER), 
        new FarmaColumnData("Datos Trabajador", 350, JLabel.LEFT),
        new FarmaColumnData("Codigo Motivo", 0, JLabel.RIGHT), 
        new FarmaColumnData("Motivo Regularizacion", 0, JLabel.LEFT),
        new FarmaColumnData("Importe", 100, JLabel.RIGHT),
        new FarmaColumnData("Estado", 110, JLabel.CENTER) };

    public static final Object[] defaultValuesListaEmpleado_Cobro =
    { " ", " ", " ", " ", " ", " " };
    
    public static final FarmaColumnData columnsListaVentas[] =
    {   new FarmaColumnData("Empresa", 0, JLabel.CENTER), 
        new FarmaColumnData("Cod. Local", 70, JLabel.CENTER), 
        new FarmaColumnData("Descripción Local", 175, JLabel.LEFT),
        new FarmaColumnData("Numero Pedido Venta", 0, JLabel.CENTER),
        new FarmaColumnData("Fecha Emisión", 100, JLabel.CENTER),
        new FarmaColumnData("Serie", 60, JLabel.CENTER), 
        new FarmaColumnData("Correlativo", 95, JLabel.CENTER),
        new FarmaColumnData("Importe Total", 90, JLabel.RIGHT),
        new FarmaColumnData("Cajero", 125, JLabel.CENTER) };

    public static final Object[] defaultValuesListadoVentas =
    { " ", " ", " ", " ", " ", " ", " ", " ", " " };

    public static final FarmaColumnData columnsDetalleVentas[] =
    {   new FarmaColumnData("Numero Pedido Venta", 0, JLabel.CENTER), 
        new FarmaColumnData("Cod. Prod.", 85, JLabel.CENTER),
        new FarmaColumnData("Descripción", 250, JLabel.LEFT),
        new FarmaColumnData("Unidad", 100, JLabel.CENTER), 
        new FarmaColumnData("Laboratorio", 0, JLabel.CENTER), 
        new FarmaColumnData("Cantidad Presentacion", 0, JLabel.RIGHT),
        new FarmaColumnData("Cantidad", 70, JLabel.RIGHT),
        new FarmaColumnData("Entero", 0, JLabel.CENTER), 
        new FarmaColumnData("Fracción", 0, JLabel.CENTER), 
        new FarmaColumnData("Precio Unitario", 100, JLabel.RIGHT),
        new FarmaColumnData("Importe Total", 100, JLabel.RIGHT),
        new FarmaColumnData("Usuario", 0, JLabel.CENTER), 
        new FarmaColumnData("Codigo Motivo", 0, JLabel.RIGHT) , 
        new FarmaColumnData("Descripcion Motivo", 0, JLabel.RIGHT), 
        new FarmaColumnData("Quimico", 0, JLabel.RIGHT), 
        new FarmaColumnData("Nro. Facturacion", 0, JLabel.RIGHT) };

    public static final Object[] defaultValuesDetalleVentas =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
    
    public static final FarmaColumnData columnsDetalleEmpleados[] =
    {   new FarmaColumnData("Compañia", 0, JLabel.CENTER), 
        new FarmaColumnData("Numero Pedido Venta", 0, JLabel.CENTER),
        new FarmaColumnData("Cod. Empleado", 100, JLabel.CENTER),
        new FarmaColumnData("Datos Empleado", 250, JLabel.LEFT),
        new FarmaColumnData("Fecha Emision", 100, JLabel.CENTER), 
        new FarmaColumnData("Moneda", 70, JLabel.RIGHT),
        new FarmaColumnData("Monto", 100, JLabel.RIGHT),
        new FarmaColumnData("Porcentaje", 0, JLabel.RIGHT),
        new FarmaColumnData("Concepto", 100, JLabel.CENTER),
        new FarmaColumnData("Local", 180, JLabel.LEFT),
        new FarmaColumnData("Quimico", 0, JLabel.RIGHT),
        new FarmaColumnData("Nro. Facturacion", 0, JLabel.RIGHT),
        new FarmaColumnData("Estado", 120, JLabel.CENTER) };

    public static final Object[] defaultValuesDetalleEmpleados =
    { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };

}

