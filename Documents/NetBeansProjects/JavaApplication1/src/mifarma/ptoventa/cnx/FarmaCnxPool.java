package mifarma.ptoventa.cnx;

import java.util.ArrayList;

import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.hilos.SubProcesos;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

public class FarmaCnxPool {
    public FarmaCnxPool() {
        super();
    }
    
    public static void loadListaProductos(){
        if(VariablesVentas.vInd_Carga_Prod_Vta.equalsIgnoreCase("S")){                
                
                VariablesVentas.tableModelListaGlobalProductos =
                        new FarmaTableModel(ConstantsVentas.columnsListaProdPrecios,
                                            ConstantsVentas.defaultValuesListaProdPrecios, 0);
                
                ArrayList parametros = new ArrayList();
                        parametros.add(FarmaVariables.vCodGrupoCia);
                        parametros.add(FarmaVariables.vCodLocal);
                        
                VariablesVentas.tableModelListaGlobalProductos.clearTable();
                VariablesVentas.vCadenaMetodo = "PTOVENTA_VTA_LISTA.VTA_LISTA_PROD_NEW(?,?)";
                VariablesVentas.vIndCheck = true;
                            
                ProcesoCnxPool va = new ProcesoCnxPool(parametros,VariablesVentas.vCadenaMetodo,VariablesVentas.vIndCheck);
                va.load();
            }
    }
}
