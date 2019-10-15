package mifarma.ptoventa.administracion.roles.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;

/**
 * Copyright (c) 2015 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : ConstantsRolesTmp.java<br>
 * <br>
 * Hist�rico de Creaci�n/_fModificaci�n<br>
 * CESAR     25.02.2015   Creaci�n<br>
 * <br>
 * @author Cesar Huanes<br>
 * @version 1.0<br>
 *
 */
public class ConstantsRolesTmp {
    public ConstantsRolesTmp() {
      
    }
    
    public static final FarmaColumnData[] columnsListaRolesTmp =
    { new FarmaColumnData("DNI", 70, JLabel.CENTER),
      new FarmaColumnData("NOMBRES", 180, JLabel.LEFT),
      new FarmaColumnData("FEC_INICIO",120, JLabel.CENTER), 
      new FarmaColumnData("FEC_FIN",100, JLabel.CENTER),
      new FarmaColumnData("USU_CREACI�N",100, JLabel.LEFT),
      new FarmaColumnData("FEC_CREACI�N",125, JLabel.LEFT)
      };

    public static final Object[] defaultValuesListaRolTmp = { " ", " ", " ", " ", " "};
    
    
    /**
     *CHUANES -17-02-2015
     * CONSTANTES DE DOCUMENTOS ANULADOS
     * */
    public static final FarmaColumnData[] columnsListaAnulados =
    { new FarmaColumnData("Tipo Comprobante", 120, JLabel.CENTER),
      new FarmaColumnData("Cantidad", 120, JLabel.CENTER),
      new FarmaColumnData("Importe ",120, JLabel.CENTER)
      };

    public static final Object[] defaultValuesListaAnulados =
    { " ", " ", " ", " ", " " };
    
    public static final FarmaColumnData[] columsListaAnualdos =
    {  new FarmaColumnData("Nombre Cajero", 190, JLabel.LEFT),
       new FarmaColumnData("Qu�mico Farmaceutico", 190, JLabel.LEFT),
      new FarmaColumnData("Tipo Comprobante", 140, JLabel.CENTER),
      new FarmaColumnData("Cantidad", 80, JLabel.CENTER),
      new FarmaColumnData("Importe("+ConstantesUtil.simboloSoles+") ",90, JLabel.RIGHT)
        } ;
    public static final Object[] defaultListaAnuladosResumen = {  " ", " ", " ", " "  };
    
    public static final FarmaColumnData[] columsListaTransferencias =
    { new FarmaColumnData("Tipo",130, JLabel.LEFT),
      new FarmaColumnData("Local Origen",190, JLabel.LEFT),
      new FarmaColumnData("Local Destino",190, JLabel.LEFT),
      new FarmaColumnData("Cant. Transf",90, JLabel.CENTER),
      new FarmaColumnData("N� Items",90, JLabel.CENTER)
        } ;
    public static final Object[] defaultListaTransferencias= { " ", " ", " ", " ", " "," "};
    
    
    public static final FarmaColumnData[] columsListaCierreTurnoSinVB =
    { new FarmaColumnData("Caja", 40, JLabel.CENTER),
      new FarmaColumnData("Turno", 40, JLabel.CENTER),
      new FarmaColumnData("Fec.Venta",80, JLabel.CENTER),
      new FarmaColumnData("Nombre Cajero",160, JLabel.LEFT),
      new FarmaColumnData("V�B�C.",45, JLabel.CENTER),
      new FarmaColumnData("V�B�Q.",45, JLabel.CENTER),
      new FarmaColumnData("Usu. Modificaci�n",160, JLabel.CENTER),
      new FarmaColumnData("Fecha de Modificaci�n",140, JLabel.CENTER)};
      
    public static final Object[] defaultCierreTurnoSinVB = { " ", " ", " ", " ", " " };
    
    public static final String ACCION_INSERTAR = "I";
    public static final String ACCION_MODIFICAR = "M";
    public static final String RESULTADO_GRABAR_CLIENTE_EXITO = "TRUE";
    public static final String RESULTADO_GRABAR_CLIENTE_ERROR = "FALSE";
    
    public static final String NOM_HASTABLE_CMBCOLUMNAROL = "CMB_COLUMNA_ROL";
    public static final String[] vDescColum = { "0", "1", "2", "3", "4", "5"};
    public static final String[] vCodColum =
    { "DNI", "NOMBRES", "FEC_INICIO", "FEC_FIN", "USU_CREACI�N", "FEC_CREACI�N" };
    
    public static final String[] vCodOrdenRol = { "ASC", "DESC" };
    public static final String[] vDescOrdenRol = { FarmaConstants.ORDEN_ASCENDENTE, FarmaConstants.ORDEN_DESCENDENTE };
    public static final String NOM_HASTABLE_CMBORDENROL = "CMB_ORDERN_ROL";

    
    
}
