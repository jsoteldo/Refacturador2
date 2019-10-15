package mifarma.ptoventa.caja.dao;
import mifarma.ptoventa.ce.reference.VariablesCajaElectronica;
import mifarma.ptoventa.reference.DAOTransaccion;

public interface DAORACCajaElectronica  extends DAOTransaccion{
    
    //JVARA 05-09-2017
    public String enviaSolicitudVBCajero(String pCodGrupoCia, String pCodLocal, Double pMontoSobrante, String pSecMovCaja) throws Exception;
                                                     
}
