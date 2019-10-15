package mifarma.ptoventa.programaXmas1.facade;

import farmapuntos.bean.BeanTarjeta;

import farmapuntos.FarmaPuntos;

import farmapuntos.orbis.WSClientConstans;

import java.awt.Frame;

//import java.awt.List;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.HiloImpresion;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.lealtad.DlgListaAcumulaX1;
import mifarma.ptoventa.lealtad.dao.DAOLealtad;
import mifarma.ptoventa.lealtad.dao.MBLealtad;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.puntos.reference.UtilityProgramaAcumula;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import mifarma.ptoventa.ventas.DlgResumenPedido;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @autor Desarrollo5 - Alejandro Nuñez
 * @since 04.11.2015
 */
public class ProgramaXmas1Facade extends AbstractClass {
    private DAOLealtad daoLealtad;
    static private final Logger log = LoggerFactory.getLogger(ProgramaXmas1Facade.class);

    private static final String VALOR_NULO = null;
    private static final String INSCRIPCION_EXITOSA = "S";
    private static final String RECHAZO_INSCRIPCION = "N";
    private static final String ESTADO_PENDIENTE = "P";
    private static final String SIMBOLO_CERO = "0";
    private static final String SIMBOLO_ARROBA = "@";
    private static final String ALINEACION_IZQUIERDA = "I";
    private static final String LOG_ERROR = "E";
    private static final Integer COLUMNA_CODIGO_EQUIVALENTE = 1;
    private String IndIngresarCantProdXmas1PF=FarmaConstants.INDICADOR_N;
    
    
    /**
     * Metodo que verifica si el producto seleccionado permite la inscripcion al programa x + 1 
     * @param pJDialog
     * @param myParentFrame
     * @param pCodProd
     * @param descProd
     * @since 04.11.2015
     */
    public void validaInscripcionCampAcumula(JDialog pJDialog, Frame myParentFrame, String pCodProd, String descProd) {
        String mensajeBox = "";
        Integer cantidad;
        BeanTarjeta tarjetaCliente=null;
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        String retorno;
        ArrayList<ArrayList<String>> arrayDetalleResultado;
        List<BeanResultado> listBeanResultado;
        ArrayList<String> listaProductos = null;
        ArrayList<String> listaProdProgInsComun   = new ArrayList<String>(); // lista de los productos por programas inscritos y en comun
        String cadenaProgramas="";
        //1. Verifica si el producto participa de X+1
        cantidad = facadeLealtad.verificaAcumulaX1(pCodProd);
        if (cantidad > 0) {
            //2. Verifica si el cliente ha sido afiliado
            //LTAVARA VALIDA 2017.04.03
            if(VariablesPuntos.frmPuntos!=null){
                    tarjetaCliente = VariablesPuntos.frmPuntos.getBeanTarjeta();
            }
            
            if(_isEmpty(tarjetaCliente)){
                if (!_isEmpty(descProd)) {
                    mensajeBox = "Este producto participa en el Programa de X+1:\n" + descProd + "\n";
                }
                
                //FarmaUtility.showMessage(pJDialog,mensajeBox + "Debe afiliarse para poder participar.",VALOR_NULO);
                // no se mostrara ningun mensaje relacionado
                return;
            }
            
            retorno = RECHAZO_INSCRIPCION;
            setIndIngresarCantProdXmas1PF(FarmaConstants.INDICADOR_N);
            
            if (!_isEmpty(tarjetaCliente) && _equiv(tarjetaCliente.getEstadoOperacion(), WSClientConstans.EXITO) &&
               (_equiv(tarjetaCliente.getEstadoTarjeta(), WSClientConstans.EstadoTarjeta.ACTIVA) ||
                _equiv(tarjetaCliente.getEstadoTarjeta(), WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR))) {
                //tarjetaCliente.getListaInscritos().add("2121191200003");
                listaProductos = (ArrayList<String>)tarjetaCliente.getListaAuxiliarInscritos();
                //CARGAR TODOS LOS PRODUCTOS DE TODOS LOS PROGRAMA INSCRITOS Y LOS PRODUCTOS DE LOS PROGRAMA QUE TIENEN EN COMUN DICHO PRODUCTO
                //EJEMPLO SI ESTAS INSCRITO AL PROGRAMA 00001 VA CARGAR LOS DEL PROGRAMA 00005 POR TIENE EN COMUN UN PRODUCTO.
                if(!_isEmpty(listaProductos)){
                      
                        for (Integer bucle=0; bucle < listaProductos.size();bucle++) {
                            cadenaProgramas += listaProductos.get(bucle).substring(8);
                            if (bucle < listaProductos.size()-1) {
                                cadenaProgramas+="@";
                            }
                        }
                    listaProdProgInsComun.addAll(obtenerProductosDeProgramasInsComun(cadenaProgramas));
                    }
                //if(_isEmpty(facadeLealtad.verificaInscripcionProducto(listaProductos, pCodProd))) { //se cambio el 2016.09.26
                //2016.09.29 se consulta a la lista de los productos de los programas inscritos y en comun por el producto
                if(_isEmpty(facadeLealtad.verificaInscripcionProducto(listaProdProgInsComun, pCodProd))) {
                    arrayDetalleResultado = new ArrayList<ArrayList<String>>();
                    listBeanResultado = listaAcumulaX1noParseado(pCodProd);
                    arrayDetalleResultado = detalleListaAcumulaX1(pCodProd);
                    
                    retorno = UtilityProgramaAcumula.afiliar_programa_dni(pCodProd);
                    
                    /*
                    DlgListaAcumulaX1 dlgListaAcumulaX1 = new DlgListaAcumulaX1(myParentFrame, "", true);
                    dlgListaAcumulaX1.setpCodProd(pCodProd);
                    dlgListaAcumulaX1.setFacadeLealtad(facadeLealtad);
                    dlgListaAcumulaX1.setDescProducto(descProd);
                    dlgListaAcumulaX1.setListBeanResultado(listBeanResultado);
                    dlgListaAcumulaX1.setDetalleListaX1(arrayDetalleResultado);
                    dlgListaAcumulaX1.setVisible(true);
                    retorno = dlgListaAcumulaX1.getRetorno();
                    */
                    
                }else{ // ya está inscrito en el programa
                //2016.09.15  Si existe en la lista de inscrito, validar si parcipa en x+1 debe ingresar nuevamente la cantidad por el ITEMQUOTE
                    if (!_isEmpty(facadeLealtad.verificaInscripcionProducto(listaProductos, pCodProd))){
                        setIndIngresarCantProdXmas1PF(FarmaConstants.INDICADOR_S);
                    }
                }
                
                if(_equiv(retorno, INSCRIPCION_EXITOSA)){
                    //2016.09.15 validar si parcipa en x+1 debe ingresar nuevamente la cantidad por el ITEMQUOTE
                    setIndIngresarCantProdXmas1PF(FarmaConstants.INDICADOR_S);
                    log.info("Se afilió al programa con éxito ...");
                    //FarmaUtility.showMessage(pJDialog, "Se afilió al programa con éxito", VALOR_NULO);
                }
            }
        }
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pDniCli
     * @param pEstPrograma
     * @return ArrayList<ArrayList<String>>
     * @since 09.11.2015
     */
    public ArrayList<ArrayList<String>> obtenerProgramasTemporalesX1(String pDniCli,
                                                                     String pEstPrograma) {
        List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            listBeanResultado = daoLealtad.programasTemporalesX1(FarmaVariables.vCodGrupoCia, 
                                                                 FarmaVariables.vCodLocal, 
                                                                 pDniCli,
                                                                 pEstPrograma);
            lista = utilityPtoVenta.parsearResultadoMatriz(listBeanResultado,SIMBOLO_ARROBA);
            daoLealtad.commit();
        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener lista de programas temporales", 
                               "ProgramaXmas1Facade", 
                               "obtenerProgramasTemporalesX1", 
                               LOG_ERROR, 
                               ex);
        }
        return lista;
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pDniCli
     * @param CodPrograma
     * @param pEstPrograma
     * @since 09.11.2015
     */
    public void registrarProgramasTempX1(String pDniCli,
                                         String pCodPrograma,
                                         String pCodProducto,
                                         String pEstPrograma,
                                         List<BeanResultado> listBeanResultado) {
        daoLealtad = new MBLealtad();
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        String programa;
        String cadenaProgramas = "";
        ArrayList<String> listProgramas = null;
        ArrayList<ArrayList<String>> listaProgramas = null;
        try {
            daoLealtad.openConnection();
            daoLealtad.registrarProgramasTempX1(FarmaVariables.vCodGrupoCia, 
                                                FarmaVariables.vCodLocal, 
                                                pDniCli,
                                                pCodPrograma,
                                                pEstPrograma,
                                                FarmaVariables.vIdUsu);
            daoLealtad.commit();
            
            if(!_isEmpty(listBeanResultado)){
                listaProgramas = parsearResultadoArray(listBeanResultado);
                listProgramas = new ArrayList<String>();
                for (Integer bucle = 0; bucle < listaProgramas.size(); bucle++) {
                    programa = listaProgramas.get(bucle).get(1);
                    if(!_isEmpty(programa)){
                        listProgramas.add(completaCadena(programa, 
                                          13,
                                          SIMBOLO_CERO, 
                                          ALINEACION_IZQUIERDA));
                    }
                }
            }
            
            if (!_isEmpty(listProgramas)) {
                for (Integer bucle=0; bucle < listProgramas.size();bucle++) {
                    cadenaProgramas += listProgramas.get(bucle).substring(8);
                    if (bucle < listProgramas.size()-1) {
                        cadenaProgramas+="@";
                    }
                }
                if (!_isEmpty(cadenaProgramas)) {
              //  VariablesPuntos.frmPuntos.getTarjetaBean().getListaAuxiliarInscritos().addAll(obtenerProductosDeProgramas(cadenaProgramas));
            // sólo el programa que acepto inscribirse 2016.09.23
                VariablesPuntos.frmPuntos.getBeanTarjeta().getListaAuxiliarInscritos().addAll(obtenerProductosDeProgramas(pCodPrograma));

                }
            }
            /*
            if (!_equiv(pEstPrograma, ESTADO_PENDIENTE)) {
                facadeLealtad.impresionVoucher(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni(),
                                               pCodPrograma,
                                               pCodProducto);
            }*/
            
        } catch (Exception ex) {
            mensajesLogErrores("Error al registrar programa temporal", 
                               "ProgramaXmas1Facade", 
                               "registrarProgramasTempX1", 
                               LOG_ERROR, 
                               ex);
        }
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param listProgramas
     * @return
     * @since 19.11.2015
     * obtener los productos del programa enviado
     */
    public ArrayList<String> obtenerProductosDeProgramas(String listProgramas) {
        ArrayList<String> listProductos = new ArrayList<String>();
        
        List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            listBeanResultado = daoLealtad.obtenerProductosDeProgramas(listProgramas);
            lista = utilityPtoVenta.parsearResultadoMatriz(listBeanResultado,SIMBOLO_ARROBA);
            daoLealtad.commit();
            
            if (!_isEmpty(lista)) {
                listProductos = new ArrayList<String>();
                for(Integer bucle = 0;bucle < lista.size();bucle++){
                    listProductos.add(lista.get(bucle).get(COLUMNA_CODIGO_EQUIVALENTE));
                }
            }
        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener lista de productos de una lista de programas", 
                               "ProgramaXmas1Facade", 
                               "obtenerProductosDeProgramas", 
                               LOG_ERROR, 
                               ex);
        }
        
        return listProductos;
    }
    
    /**
     * @autor LTAVARA 
     * @param listProgramas
     * @return
     * @since 2016.09.26
     * Obtener los producto de los programas inscritos y en comun por el producto.
     */
    public ArrayList<String> obtenerProductosDeProgramasInsComun(String listProgramas) {
        ArrayList<String> listProductos = null;
        
        List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            listBeanResultado = daoLealtad.obtenerProductosDeProgramasInsComun(listProgramas);
            lista = utilityPtoVenta.parsearResultadoMatriz(listBeanResultado,SIMBOLO_ARROBA);
            daoLealtad.commit();
            
            if (!_isEmpty(lista)) {
                listProductos = new ArrayList<String>();
                for(Integer bucle = 0;bucle < lista.size();bucle++){
                    listProductos.add(lista.get(bucle).get(COLUMNA_CODIGO_EQUIVALENTE));
                }
            }
        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener lista de productos de una lista de programas", 
                               "ProgramaXmas1Facade", 
                               "obtenerProductosDeProgramas", 
                               LOG_ERROR, 
                               ex);
        }
        
        return listProductos;
    }
    
    /**
     * Carga la lista de programas pendientes con la lista de programas inscritos del INIT()
     * @autor Desarrollo5 - Alejandro Nuñez
     * @since 11.11.2015
     */
    public void reemplazarListaProgramasPorProductos(){
        ArrayList<ArrayList<String>> listProgramasPendientes;
        ArrayList<String> listProgramas;
        
        String programaPendiente;
        String cadenaProgramas = "";
        //LTAVARA VALIDA 2017.04.03
     //   if(VariablesPuntos.frmPuntos!=null){
        //NO SE DEBE DE BLANQUEAR LOS DATOS
        //VariablesPuntos.frmPuntos.getBeanTarjeta().listaAuxiliarInscritos.clear();
        
        listProgramasPendientes =
                obtenerProgramasTemporalesX1(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni(),
                                             ESTADO_PENDIENTE);
        if(!_isEmpty(listProgramasPendientes)){
            for (Integer bucle = 0; bucle < listProgramasPendientes.size(); bucle++) {
                programaPendiente = listProgramasPendientes.get(bucle).get(0);
                if(!_isEmpty(programaPendiente)){
                    VariablesPuntos.frmPuntos.getBeanTarjeta().getListaInscritos().add(completaCadena(programaPendiente, 
                                                                                                      13,
                                                                                                      SIMBOLO_CERO, 
                                                                                                      ALINEACION_IZQUIERDA));
                }
            }
        }
        
        if (!_isEmpty(VariablesPuntos.frmPuntos.getBeanTarjeta().getListaInscritos())) {
            listProgramas = new ArrayList<String>(); 
            listProgramas.addAll(VariablesPuntos.frmPuntos.getBeanTarjeta().getListaInscritos());
            
            for (Integer bucle=0; bucle < listProgramas.size();bucle++) {
                cadenaProgramas += listProgramas.get(bucle).substring(8);
                if (bucle < listProgramas.size()-1) {
                    cadenaProgramas+="@";
                }
            }
            
            VariablesPuntos.frmPuntos.getBeanTarjeta().getListaAuxiliarInscritos().addAll(obtenerProductosDeProgramas(cadenaProgramas));
        }
   //     }
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @return Double
     * @since 13.11.2015
     */
    public Double obtenerPorcentajeDescuentoConvenio(){
        Double porcDescuento = 0.0;
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            porcDescuento = daoLealtad.obtenerPorcentajeDescuentoConvenio(FarmaVariables.vCodGrupoCia, 
                                                                          FarmaVariables.vCodLocal,
                                                                          VariablesConvenioBTLMF.vCodConvenio);
            daoLealtad.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return porcDescuento;
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pJDialog
     * @param myParentFrame
     * @param tarjetaBean
     * @since 20.11.2015
     */
    public void desafiliacionProgramaX1(JDialog pJDialog, Frame myParentFrame, BeanTarjeta tarjetaBean) {
        FacadeLealtad facadeLealtad = new FacadeLealtad();
        String retorno;
        ArrayList<ArrayList<String>> arrayDetalleResultado;
        List<BeanResultado> listProgramasBeanResultado = null;
        ArrayList<String> listaProgramas = null;
        
        retorno = RECHAZO_INSCRIPCION;
        if (!_isEmpty(tarjetaBean) && _equiv(tarjetaBean.getEstadoOperacion(), WSClientConstans.EXITO) &&
           (_equiv(tarjetaBean.getEstadoTarjeta(), WSClientConstans.EstadoTarjeta.ACTIVA) ||
            _equiv(tarjetaBean.getEstadoTarjeta(), WSClientConstans.EstadoTarjeta.BLOQUEADA_REDIMIR))) {
            //tarjetaBean.getListaInscritos().add("2121191200003");
            listaProgramas = (ArrayList<String>)tarjetaBean.getListaInscritos();
            
            arrayDetalleResultado = new ArrayList<ArrayList<String>>();
            listProgramasBeanResultado = listaProgramasX1noParseado(listaProgramas);
            arrayDetalleResultado = detalleListaProgramasAcumulaX1(listaProgramas);
            DlgListaAcumulaX1 dlgListaAcumulaX1 = new DlgListaAcumulaX1(myParentFrame, "", true,true);
            dlgListaAcumulaX1.setFacadeLealtad(facadeLealtad);
            dlgListaAcumulaX1.setListBeanResultado(listProgramasBeanResultado);
            dlgListaAcumulaX1.setDetalleListaX1(arrayDetalleResultado);
            dlgListaAcumulaX1.setVisible(true);
            String codProgramaDesafiliado=dlgListaAcumulaX1.getCodProgramaDesafiliado();
            retorno = dlgListaAcumulaX1.getRetorno();
            
            if(_equiv(retorno, INSCRIPCION_EXITOSA) ){
                if(!codProgramaDesafiliado.equals(FarmaConstants.INDICADOR_N)){
                    try {
                       eliminarProgInscripcionAuxiliar(tarjetaBean.getDni(),codProgramaDesafiliado);
                        
                    } catch (Exception e) {
                        log.info("Error al elimiar el programa de la inscripcion auxiliar"+e);
                    }
                }
                FarmaUtility.showMessage(pJDialog, "Se desafilio al programa con exito", VALOR_NULO);
            }
        }
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodProd
     * @return List<BeanResultado>
     * @since 05.11.2015
     */
    public List<BeanResultado> listaAcumulaX1noParseado(String pCodProd) {
        List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            listBeanResultado = daoLealtad.listaAcumulaX1(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, 
                                                          pCodProd);
            daoLealtad.commit();
        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener lista programas por producto", 
                               "ProgramaXmas1Facade", 
                               "listaAcumulaX1noParseado(String)", 
                               "E", 
                               ex);
        }
        return listBeanResultado;
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pCodProd
     * @return ArrayList<ArrayList<String>>
     * @since 06.11.2015
     */
    public ArrayList<ArrayList<String>> detalleListaAcumulaX1(String pCodProd) {
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            listBeanResultado = daoLealtad.detalleListaAcumulaX1(FarmaVariables.vCodGrupoCia, 
                                                                 FarmaVariables.vCodLocal, 
                                                                 pCodProd);
            lista = utilityPtoVenta.parsearResultadoMatriz(listBeanResultado,"@");
            daoLealtad.commit();
        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener programas por producto e introducirlo en tabla", 
                               "ProgramaXmas1Facade", 
                               "listaAcumulaX1(String)", 
                               "E", 
                               ex);
        }
        return lista;
    }
         
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param pTableModel
     * @param listBeanResultado
     * @since 05.11.2015
     */
    public void parsearResultado(FarmaTableModel pTableModel,List<BeanResultado> listBeanResultado){
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        
        utilityPtoVenta.parsearResultado(listBeanResultado, pTableModel, false, "@");
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param listBeanResultado
     * @since 12.11.2015
     */
    public ArrayList<ArrayList<String>> parsearResultadoArray(List<BeanResultado> listBeanResultado){
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        ArrayList<ArrayList<String>> lista = null;
        try {
            lista = utilityPtoVenta.parsearResultadoMatriz(listBeanResultado,"@");
        } catch (Exception ex) {
            mensajesLogErrores("Error al introducir convertir listBeanResultado en array", 
                               "ProgramaXmas1Facade", 
                               "parsearResultadoArray(List<BeanResultado>)", 
                               "E", 
                               ex);
        }
        return lista;
    }
    
    /**
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param listaProgramas
     * @return List<BeanResultado>
     * @since 16.11.2015
    */
    public List<BeanResultado> listaProgramasX1noParseado(ArrayList<String> listaProgramas) {
        List<BeanResultado> listBeanResultado = null;
        String lstProgramas = "";
        
        if (!_isEmpty(listaProgramas)) {
            listBeanResultado = new ArrayList<BeanResultado>();
            lstProgramas = armaCadenaProgramasDeArray(listaProgramas);
        }
        if (!_isEmpty(listaProgramas)) {
            daoLealtad = new MBLealtad();
            try {
                daoLealtad.openConnection();
                listBeanResultado = daoLealtad.listaProgramasAcumulaX1(FarmaVariables.vCodGrupoCia, 
                                                                       FarmaVariables.vCodLocal, 
                                                                       lstProgramas);
                daoLealtad.commit();
            } catch (Exception ex) {
                mensajesLogErrores("Error al obtener descripcion de programas por lista de programas", 
                                   "ProgramaXmas1Facade", 
                                   "listaProgramasX1noParseado(ArrayList<String>)", 
                                   "E", 
                                   ex);
            }
        }
        
        return listBeanResultado;
    }
    

    public ArrayList<ArrayList<String>> detalleListaProgramasAcumulaX1(ArrayList<String> listaProgramas) {
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        String lstProgramas = "";
        
        if (!_isEmpty(listaProgramas)) {
            listBeanResultado = new ArrayList<BeanResultado>();
            lstProgramas = armaCadenaProgramasDeArray(listaProgramas);
        }
        if (!_isEmpty(listaProgramas)) {
            daoLealtad = new MBLealtad();
            try {
                daoLealtad.openConnection();
                listBeanResultado = daoLealtad.detalleListaProgramasAcumulaX1(FarmaVariables.vCodGrupoCia, 
                                                                              FarmaVariables.vCodLocal, 
                                                                              lstProgramas);
                lista = utilityPtoVenta.parsearResultadoMatriz(listBeanResultado,"@");
                daoLealtad.commit();
            } catch (Exception ex) {
                mensajesLogErrores("Error al obtener programas por producto e introducirlo en tabla", 
                                   "ProgramaXmas1Facade", 
                                   "detalleListaProgramasAcumulaX1(ArrayList<String>)", 
                                   "E", 
                                   ex);
            }
        }
        return lista;
    }
    
    public String armaCadenaProgramasDeArray(ArrayList<String> listaProgramas){
        String lstProgramas = "";
        
        if (!_isEmpty(listaProgramas)) {
            for (Integer i=0; i<listaProgramas.size(); i++) {
                if (i < listaProgramas.size() - 1) {
                    lstProgramas += listaProgramas.get(i).substring(8, 13)+"@";
                }else{
                    lstProgramas += listaProgramas.get(i).substring(8, 13);
                }
            }
        }
        
        return lstProgramas;
    }
    
    public BeanTarjeta registrarDesafiliacion(String codPrograma){
      /*  String pIdIPPOS = UtilityPuntos.getIdEpos();
        String pkey = UtilityPuntos.getCodAutorizacion();
        String pWsOrbis = UtilityPuntos.getWsOrbis();
        String pTimeOut = UtilityPuntos.pTimeOutOrbis();
        FarmaPuntos farmaPuntos = new FarmaPuntos(FarmaVariables.vCodLocal, pIdIPPOS, pWsOrbis, pkey,
                                                                Integer.parseInt(pTimeOut.trim()));*/
        BeanTarjeta tarjetaBean= VariablesPuntos.frmPuntos.DesafiliarProgramaX1(VariablesPuntos.frmPuntos.getBeanTarjeta().getNumeroTarjeta(), 
                                                       codPrograma, 
                                                      UtilityPuntos.getDNI_USU());
        
        if (tarjetaBean.getEstadoOperacion().equals(WSClientConstans.EXITO)) {
            impresionDesafiliacion(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni(),
                                   codPrograma,
                                   UtilityPuntos.enmascararTarjeta(VariablesPuntos.frmPuntos.getBeanTarjeta().getNumeroTarjeta()));
        }
        
        return tarjetaBean;
    }
   /* public BeanTarjeta registrarDesafiliacion(String codPrograma){

        BeanTarjeta tarjetaBean = UtilityPuntos.desafiliacionProgramaXmas1(VariablesPuntos.frmPuntos.getTarjetaBean().getNumeroTarjeta(),
                                                                           objeto, pDialog, );
            farmaPuntos.DesafiliarProgramaX1(VariablesPuntos.frmPuntos.getTarjetaBean().getNumeroTarjeta(), 
                                                       codPrograma, 
                                                       FarmaVariables.vCodUsuMatriz);
        
        if (tarjetaBean.getEstadoOperacion().equals(WSClientConstans.EXITO)) {
            impresionDesafiliacion(VariablesPuntos.frmPuntos.getTarjetaBean().getDni(),
                                   codPrograma,
                                   UtilityPuntos.enmascararTarjeta(VariablesPuntos.frmPuntos.getTarjetaBean().getNumeroTarjeta()));
        }
        
        return tarjetaBean;
    }*/
    
    public void impresionDesafiliacion(String pDniCli, String pCodPrograma, String pNumTarjeta){
        try{
            daoLealtad = new MBLealtad();
            daoLealtad.openConnection();
            String pConsulta = daoLealtad.indImpresionVoucherX1();
            daoLealtad.commit();
            String[] pIndicadores = pConsulta.trim().split("@");
            String indImpresion = pIndicadores[0];
            String indLogo = pIndicadores[1];
            
            if(indImpresion.equals(FarmaConstants.INDICADOR_S)){
                List lstImpresionTicket = daoLealtad.voucherDesafiliacionX1(FarmaVariables.vCodGrupoCia, 
                                                                            FarmaVariables.vCodLocal, 
                                                                            pCodPrograma, 
                                                                            pDniCli,
                                                                            pNumTarjeta);
                if (lstImpresionTicket.size() > 0) {
        
                    HiloImpresion hilo = new HiloImpresion(lstImpresionTicket);
                    if(indLogo.equals(FarmaConstants.INDICADOR_S)){
                        hilo.setImprimirLogo(true);
                    }
                    hilo.start();
                }
            }
        } catch (Exception ex) {
            daoLealtad.rollback();
        }
    }
    
    public void recuperacionProgramaX1(String listaProgramas, String numeroPedido){
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            daoLealtad.recuperarProgramasX1(FarmaVariables.vCodGrupoCia, 
                                            FarmaVariables.vCodLocal, 
                                            numeroPedido,
                                            listaProgramas);
            daoLealtad.commit();
        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener lista de productos de una lista de programas", 
                               "ProgramaXmas1Facade", 
                               "obtenerProductosDeProgramas", 
                               LOG_ERROR, 
                               ex);
        }
    }
    
    public void quitarInscripcionPendiente(String codigoProducto,
                                           String numeroDocumentoCliente){
        ArrayList<String> listProductos = null;
        ArrayList<String> listProductosAuxiliar = null;
        List<BeanResultado> listBeanResultado = new ArrayList<BeanResultado>();
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
        Integer contador = 0;
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            listBeanResultado = daoLealtad.obtenerProgramasTemporalesDeProducto(codigoProducto,numeroDocumentoCliente);
            lista = utilityPtoVenta.parsearResultadoMatriz(listBeanResultado,SIMBOLO_ARROBA);
            daoLealtad.commit();
            
            if(lista.size() == 1){
                listProductos = new ArrayList<String>();
                listProductos.addAll(obtenerProductosDeProgramas(_toStr(lista.get(0).get(0))));
                if (!_isEmpty(listProductos)) {
                    listProductosAuxiliar = new ArrayList<String>();
                    for (Integer i = 0 ; i < VariablesPuntos.frmPuntos.getBeanTarjeta().getListaAuxiliarInscritos().size() ; i++) {
                        for (Integer e = 0 ; e < listProductos.size() ; e++) {
                            if (_equiv(VariablesPuntos.frmPuntos.getBeanTarjeta().getListaAuxiliarInscritos().get(i),listProductos.get(e))) {
                                contador++;
                                break;
                            }
                        }
                        if(contador == 0){
                            listProductosAuxiliar.add(VariablesPuntos.frmPuntos.getBeanTarjeta().getListaAuxiliarInscritos().get(i));
                        }
                        contador = 0;
                    }
                    VariablesPuntos.frmPuntos.getBeanTarjeta().getListaAuxiliarInscritos().clear();
                    VariablesPuntos.frmPuntos.getBeanTarjeta().setListaAuxiliarInscritos(listProductosAuxiliar);
                }
            }
        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener lista de programas grabados temporalmente(estado:T)", 
                               "ProgramaXmas1Facade", 
                               "quitarInscripcionPendiente", 
                               LOG_ERROR, 
                               ex);
        }
    }
    
    public List<BeanResultado> getProductoProgramaXmas1Pedido(String numeroPedido){

        List<BeanResultado> listaProductoProg = new ArrayList<BeanResultado>();
        daoLealtad = new MBLealtad();
        
        try {
            daoLealtad.openConnection();
            listaProductoProg= daoLealtad.getProductoProgramaXmas1Pedido(FarmaVariables.vCodGrupoCia, 
                                            FarmaVariables.vCodLocal, 
                                            numeroPedido);
            daoLealtad.commit();

        } catch (Exception ex) {
            mensajesLogErrores("Error al obtener lista de productos de una lista de programas", 
                               "ProgramaXmas1Facade", 
                               "getProductoProgramaXmas1Pedido", 
                               LOG_ERROR, 
                               ex);
        }
        return listaProductoProg;
    }
    
    public void eliminarProgInscripcionAuxiliar(    String pDniCli,
                                                    String pCodPrograma){
        daoLealtad = new MBLealtad();
        try {
            daoLealtad.openConnection();
            daoLealtad.eliminarProgramaInscripcionAux(
                                            pDniCli,
                                            pCodPrograma);
            daoLealtad.commit();
        } catch (Exception ex) {
            mensajesLogErrores("Error al eliminar el programa de la inscripcion auxiliar", 
                               "ProgramaXmas1Facade", 
                               "eliminarProgramaInscripcionAux", 
                               LOG_ERROR, 
                               ex);
        }
    }


    public void setIndIngresarCantProdXmas1PF(String IndIngresarCantProdXmas1PF) {
        this.IndIngresarCantProdXmas1PF = IndIngresarCantProdXmas1PF;
    }

    public String getIndIngresarCantProdXmas1PF() {
        return IndIngresarCantProdXmas1PF;
    }
}
