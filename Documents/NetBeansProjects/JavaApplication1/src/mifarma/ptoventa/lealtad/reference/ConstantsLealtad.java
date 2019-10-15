package mifarma.ptoventa.lealtad.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;

/**
 * 
 * @author ERIOS
 * @since 05.02.2015
 */
public class ConstantsLealtad {
    // lais X+1
    public static final FarmaColumnData[] columnsListaAcumula =
    { new FarmaColumnData("CodigoMatriz", 0, JLabel.CENTER),
      new FarmaColumnData("Codigo", 80, JLabel.CENTER), 
      new FarmaColumnData("Descripcion de Programa", 405, JLabel.LEFT),
      new FarmaColumnData("Cantidad", 0, JLabel.LEFT), 
      new FarmaColumnData("Bonificacion", 0, JLabel.LEFT),
      new FarmaColumnData("CodEquivalenteAcumulado", 0, JLabel.LEFT)
    } ;

    //Inicio[Desarrollo5 - Alejandro Nuñez] 18.11.2015
    public static final Object[] defaultValuesListaAcumula = { " ", " ", " ", " ", " "};
    
    public static final FarmaColumnData[] columnsDetalleListaAcumula =
    { 
      new FarmaColumnData("Producto", 230, JLabel.LEFT)
    } ;

    public static final Object[] defaultValuesDetalleListaAcumula = { " "};
    
    public static final FarmaColumnData[] columnsDetalleListaBonifica =
    { 
      new FarmaColumnData("Producto", 230, JLabel.LEFT)
    } ;

    public static final Object[] defaultValuesDetalleListaBonifica = { " "};
    //Fin[Desarrollo5 - Alejandro Nuñez] 18.11.2015
	// lais X+1
}
