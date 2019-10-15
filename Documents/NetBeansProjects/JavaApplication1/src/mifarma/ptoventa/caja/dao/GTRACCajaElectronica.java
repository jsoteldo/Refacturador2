

package mifarma.ptoventa.caja.dao;


import com.mifarma.query.builder.ClienteIntegrador;
import com.mifarma.query.builder.QueryBuilder;
import com.mifarma.query.builder.UpdateBuilder;
import com.mifarma.query.request.QueryParams;
import com.mifarma.query.response.QueryStatus;

import java.util.List;
import java.util.Map;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.dao.DAORACCajaElectronica;
import mifarma.ptoventa.ce.reference.VariablesCajaElectronica;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import oracle.sql.CHAR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GTRACCajaElectronica implements DAORACCajaElectronica { 
    private static final Logger log = LoggerFactory.getLogger(GTRACCajaElectronica.class);
    
    private UpdateBuilder updateBuilder = null;
    private ClienteIntegrador clienteIntegrador = null;
    private UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    
    @Override
    public void commit() {
    }

    @Override
    public void rollback() {
    }

    @Override
    public void openConnection() {
        updateBuilder = new UpdateBuilder()
                                  .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
                                  .requestId("cajaelectronica-merge-rac-ped");
        
        clienteIntegrador = new ClienteIntegrador(VariablesPtoVenta.conexionGTX.getIPBD()+":"+VariablesPtoVenta.conexionGTX.getPORT());
        clienteIntegrador.bypassGateway(); 
    }
        
    //JVARA 07-09-2017
    @Override
    public String enviaSolicitudVBCajero(String pCodGrupoCia, String pCodLocal, Double pMontoSobrante , String pSecMov) {
        log.info("PTOVENTA.PTOVENTA_MATRIZ_CAJ.F_SOL_APROB_VB_CAJERO(?,?,?,?,?): ["
                 +pCodGrupoCia+","+pCodLocal+","+pMontoSobrante+","+FarmaVariables.vIdUsu+","+pSecMov+"]");
      
        
        QueryParams params = new QueryBuilder()
            .codLocalOrgDst(FarmaVariables.vCodLocal, "CME")
            .qrySpVarchar("{ ? = call PTOVENTA.PTOVENTA_MATRIZ_CAJ.F_SOL_APROB_VB_CAJERO(?,?,?,?,?)}",
                          pCodGrupoCia, pCodLocal,pMontoSobrante,FarmaVariables.vIdUsu,pSecMov)
            .getParams();
        
        QueryStatus status = clienteIntegrador.queryGeneric(params);
        status.exceptionOnError();
        
        List<Map<String, Object>> result = status.getResult(); 
        Map<String, Object> first = result.get(0);
        
        String resultado = String.valueOf(first.get("1"));

        return resultado;
    }
    
   
    
}
