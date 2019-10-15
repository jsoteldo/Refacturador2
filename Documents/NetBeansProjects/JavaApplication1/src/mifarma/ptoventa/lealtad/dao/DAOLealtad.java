package mifarma.ptoventa.lealtad.dao;

import java.util.List;
import java.util.Map;

import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.DAOTransaccion;


public interface DAOLealtad extends DAOTransaccion {
    
    public int verificaAcumulaX1(String pCodGrupoCia, String strCodCia, String strCodLocal, 
                                String pCodProd) throws Exception;
    
    public List<BeanResultado> listaAcumulaX1(String pCodGrupoCia, String strCodCia, String strCodLocal, 
                                String pCodProd) throws Exception;
    
    // lais X+1
	public List<BeanResultado> listaProgramasAcumulaX1(String pCodGrupoCia, String strCodLocal,
                                                       String pListaProgramas)  throws Exception;
    // lais X+1
    public List voucherAcumulaX1(String pCodGrupoCia, String strCodCia, String strCodLocal, 
                                String pCodCamp, String pDniCli, String pCodProd) throws Exception;
    
    // lais X+1
	public List<BeanResultado> detalleListaAcumulaX1(String pCodGrupoCia, 
                                                     String strCodLocal,
                                                     String pCodProd)  throws Exception;
    
    public List<BeanResultado> detalleListaProgramasAcumulaX1(String pCodGrupoCia, 
                                                              String strCodLocal,
                                                              String lstProgramas)  throws Exception;
    
    public List<BeanResultado> programasTemporalesX1(String pCodGrupoCia, 
                                                     String pCodLocal,
                                                     String pDniCli,
                                                     String pEstPrograma)  throws Exception;
    
    public void registrarProgramasTempX1(String pCodGrupoCia, 
                                         String pCodLocal,
                                         String pDniCli,
                                         String pCodPrograma,
                                         String pEstPrograma,
                                         String pUsuReg)  throws Exception;
    
    public List voucherDesafiliacionX1(String pCodGrupoCia, 
                                       String pCodLocal, 
                                       String pCodPrograma, 
                                       String pDniCli,
                                       String pNumTarjeta) throws Exception;
    
    public Double obtenerPorcentajeDescuentoConvenio(String pCodGrupoCia, 
                                                     String pCodLocal,
                                                     String pCodConvenio) throws Exception;
    
    public List<BeanResultado> obtenerProductosDeProgramas(String listProgramas) throws Exception;
    
    public List<BeanResultado> obtenerProgramasTemporalesDeProducto(String codigoProducto,
                                                                    String numeroDocumentoCliente) throws Exception;
    
    public void recuperarProgramasX1(String pCodGrupoCia, 
                                     String strCodLocal, 
                                     String pNumPedVta,
                                     String pCadenaProgramas) throws Exception;
    // lais X+1

    public String registrarInscripcionX1(String pDniCli, String pCodMatrizAcu, String vIdUsu);

    public String indImpresionVoucherX1();

    public String obtenerParametrosVenta(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;

    public void actualizarPedido(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta, String pIdTransaccion,
                                 String pNumAutorizacion, String pIdUsu) throws Exception;

    public void eliminaProdBonificacion(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;

    public void descartarPedido(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;

    public String getSaldoPuntos(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;

    public Map getPuntosMaximo(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;
    
    public String getIndicadoresPuntos(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;

    public String verificaUsoNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta, String pTipoBusqueda) throws Exception;
    
    public String verificaFechaNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta, String pFechaNCR) throws Exception;

    public String verificaCreditoNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta, String pFechaNCR) throws Exception;

    public String getMontoNCR(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVtaNCR) throws Exception;

    public String obtenerIndicadoresVenta(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;

    public String getMultiploPtos(String pCodGrupoCia, String strCodCia, String strCodLocal) throws Exception;    
    
    public void registrarInscripcionTurno(String pCodGrupoCia, String strCodCia, String strCodLocal, String pSecMovCaja, String pNroTarjetaFidelizado, String pIdUsu) throws Exception;

    public String getInscripcionTurno(String pCodGrupoCia, String strCodCia, String strCodLocal, String pSecMovCaja) throws Exception; 
    
    public void rechazarIncripcionPuntos(String pCodGrupoCia, String pNumDocumento, String pSecMovCaja, String pNroTarjetaFidelizado) throws Exception;
	// lais X+1
    public  List<BeanResultado> getProductoProgramaXmas1Pedido(String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;
    
    public void eliminarProgramaInscripcionAux(
                                         String pDniCli,
                                         String pCodPrograma)  throws Exception;

    public List<BeanResultado> obtenerProductosDeProgramasInsComun(String string)  throws Exception;;
	// lais X+1
    
    public void isPermiteVtaOffline(String pCodGrupoCia, String strCodCia, String strCodLocal, String pNumPedVta) throws Exception;
    
}
