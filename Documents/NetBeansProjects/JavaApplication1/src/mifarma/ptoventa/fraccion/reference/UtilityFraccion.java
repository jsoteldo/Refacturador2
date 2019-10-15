package mifarma.ptoventa.fraccion.reference;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;

import mifarma.ptoventa.fraccion.DAO.BDFraccion;
import mifarma.ptoventa.fraccion.modelo.VariableProducto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityFraccion {
    static final Logger log = LoggerFactory.getLogger(UtilityFraccion.class);
    
    public UtilityFraccion() {        
    }
    
    public static void cargarListaProductos_Memoria(FarmaTableModel pTableProductos) {
        pTableProductos.clearTable();
        pTableProductos.data=VariableProducto.listaMemoriaProd;
    }
    
    public static void cargarListaProductos(FarmaTableModel pTableProductos) {
        try {
            BDFraccion.cargarListaProductos(pTableProductos);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void cargaLista_ProductosMemoria(ArrayList<ArrayList> pListaProductos) {
        try {
            BDFraccion.cargarListaProductos_Memoria(pListaProductos);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public static ArrayList recuperaMotivos() {
        ArrayList<List> listaMotivos = null;
        try {
            listaMotivos = new ArrayList();
            FarmaColumnData colum[]={new FarmaColumnData("codigo", 80, JLabel.CENTER),
                                     new FarmaColumnData("motivo", 80, JLabel.CENTER)};
            String datos[]={"",""};
            FarmaTableModel modelMotivo;
            modelMotivo =new FarmaTableModel(colum,datos,0);
            
            BDFraccion.recuperaMotivos(modelMotivo);
            
            for(int i=0;i<modelMotivo.getRowCount();i++){
                List<String> motivo=new ArrayList();
                motivo.add(modelMotivo.getRow(i).get(0).toString());
                motivo.add(modelMotivo.getRow(i).get(1).toString());
                listaMotivos.add(motivo);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return listaMotivos;
    }

    public static void recuperoProducto_Fracionable() {
        try {
            ArrayList pTableProducto=new ArrayList();
            BDFraccion.recuperoProducto_Fracionable(pTableProducto,VariableProducto.vCod_Prod);
            VariableProducto.vCod_Prod=((String) ((ArrayList) pTableProducto.get(0)).get(0)).trim();
            VariableProducto.vDesc_Prod=((String) ((ArrayList) pTableProducto.get(0)).get(1)).trim();
            VariableProducto.vCod_Unid_Pres=((String) ((ArrayList) pTableProducto.get(0)).get(2)).trim();
            VariableProducto.vDesc_Unid_Pres=((String) ((ArrayList) pTableProducto.get(0)).get(3)).trim();
            VariableProducto.vCod_Lab=((String) ((ArrayList) pTableProducto.get(0)).get(4)).trim();
            VariableProducto.vDesc_Lab=((String) ((ArrayList) pTableProducto.get(0)).get(5)).trim();
            VariableProducto.vInd_Fracionable=((String) ((ArrayList) pTableProducto.get(0)).get(6)).trim();
            
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static ArrayList<ArrayList> grabaNuevaSolicitud_Fracionamiento() {
        ArrayList<ArrayList> resultado=null;
        try {
            resultado=BDFraccion.grabaNuevaSolicitud_Fracionamiento();
        } catch (SQLException e) {
            resultado=null;
            log.error(e.getMessage(), e);
        }
        return resultado;
    }

    public static int existenDatos(String fechaIni, String fechaFin,String CodEstado) {
        try {
            return BDFraccion.recuperaCantidad_Solicitudes(fechaIni,fechaFin,CodEstado);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public static void cargaTabla_SolicitudFrac(FarmaTableModel pTablaSolicitudes, String fechaIni, String fechaFin,
                                                String CodEstado) {
        try {
            BDFraccion.cargaTabla_SolicitudFrac(pTablaSolicitudes,fechaIni,fechaFin,CodEstado);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void cargaLista_SolicitudFrac(ArrayList<ArrayList> pListaSolic, String fechaIni, String fechaFin,
                                                String CodEstado) {
        try {
            BDFraccion.cargaLista_SolicitudFrac(pListaSolic,fechaIni,fechaFin,CodEstado);
            if(pListaSolic!=null){
                System.out.println("TAMAÑO LISTA: "+pListaSolic.size());
                for(int i=0;i<pListaSolic.size();i++){
                    System.out.println("------------------");
                    for(int j=0;j<pListaSolic.get(i).size();j++){
                    System.out.println("["+i+"]["+j+"] "+pListaSolic.get(i).get(j));
                    }
                }
            }else{
                System.out.println("Lista NULA");
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public static void cargaTabla_ProductosFrac(FarmaTableModel pTablaProductos, String nroSolic,String codLocalCrea) {
        try {
            BDFraccion.cargaTabla_ProductosFrac(pTablaProductos,nroSolic,codLocalCrea);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public static void cargaLista_ProductosFrac(String nroSolic) {
        try {
            ArrayList<ArrayList> pListaProductos=new ArrayList();
            BDFraccion.cargaLista_ProductosFrac(pListaProductos,nroSolic);
            if(pListaProductos!=null){
                System.out.println("TAMAÑO LISTA: "+pListaProductos.size());
                for(int i=0;i<pListaProductos.size();i++){
                    System.out.println("------------------");
                    for(int j=0;j<pListaProductos.get(i).size();j++){
                    System.out.println("["+i+"]["+j+"] "+pListaProductos.get(i).get(j));
                    }
                }
            }else{
                System.out.println("Lista NULA");
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static int actualiza_AnulacionSolicitud(String nroSolic) {
        try {
            return BDFraccion.actualiza_AnulacionSolicitud(nroSolic);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    public static void recuperaOptionTipo(ArrayList<ArrayList> option, String codOption) {
        try{
            ArrayList<ArrayList> optionAux=new ArrayList();
            BDFraccion.recuperaOptionTipo(optionAux,codOption);
                if(!optionAux.isEmpty()){
                    ArrayList todo=new ArrayList();
                    if(codOption.equalsIgnoreCase("1")){
                        todo.add("%");
                        todo.add("Todos");
                        option.add(todo);
                    }
                    for(int i=0;i<optionAux.size();i++){
                        option.add(optionAux.get(i));
                    }
                }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static int  recuperaFraccionActual(String codProducto) {
        try{
            int codFraccion=BDFraccion.recuperaFraccionActual(codProducto);
            return codFraccion;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public static String atenderSolicitudes_Pendientes(){
        String resul=null;
        try{
            BDFraccion.procesarSolcitud();
            FarmaUtility.aceptarTransaccion(); 
            //BDFraccion.recuperaSolicitudes_Pendientes(listaPendientes);
            log.info("Se realizo el faccionamiento de los productos seleccionados");
        }catch(SQLException e){
            FarmaUtility.liberarTransaccion(); 
            log.error("Error al Procesar la solicitudes de fraccionamiento");
            e.printStackTrace();
            resul="Error al Procesar la solicitudes de fraccionamiento\n"+e.getMessage()+"\n"+e.getErrorCode();
            e.printStackTrace();
        }        
        return resul;
    }

    public static int recuperaSolicitud_Frac(String nroSolic,String codLocalCrea) {
        int cod=0;
        try {
            String codSolic=completaCeros(nroSolic,10);
            ArrayList<ArrayList> solicitud = new ArrayList();
            BDFraccion.recuperaSolicitud_Frac(solicitud,codSolic,codLocalCrea);
            if(solicitud!=null && !solicitud.isEmpty()&&solicitud.size()==1){
                String codEstdo=solicitud.get(0).get(7).toString();
                int send=Integer.parseInt(solicitud.get(0).get(10).toString());
                if(send==0){
                    if(codEstdo.equalsIgnoreCase("01")){
                        cod=1;//procesar solicitud
                    }else{
                        cod=Integer.parseInt(codEstdo);//La solicitud ya fue procesada/anulado
                    }
                }else{
                    cod=5;//la solicitud ya fue enviada a matriz
                }
            }else{
                cod=6;//No se encontro el registro con los datos enviados
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cod;
    }
    
    private static String completaCeros(String cadena,int tamanio) {
        String cdaValida="";
        try{
            int val=Integer.parseInt(cadena);
            if(val<10){
                cdaValida="0"+val;
            }else{
                cdaValida=""+val;
            }
            while(cdaValida.length()<tamanio){
                cdaValida="0"+cdaValida;                
            }
        }catch(Exception e){
            cdaValida="";
        }
        return cdaValida;
    }

    public static void enviarDatos_APPS() {
        
        ArrayList<ArrayList> solic_SinEnviar=new ArrayList();
        try{
            BDFraccion.recuperarSolic_SinEnviar(solic_SinEnviar);
            if(solic_SinEnviar!=null && !solic_SinEnviar.isEmpty()){
                boolean okProd=true;
                UtilityConexion utilConex=new UtilityConexion();
                for(int i=0;i<solic_SinEnviar.size();i++){
                    String cCodLocal=solic_SinEnviar.get(i).get(1).toString().trim();
                    String cCodSolic=solic_SinEnviar.get(i).get(2).toString().trim();
                    int cNroSolic=Integer.parseInt(solic_SinEnviar.get(i).get(3).toString().trim());
                    String cFecSolic=solic_SinEnviar.get(i).get(4).toString().trim();
                    String cFecAtenc=solic_SinEnviar.get(i).get(5).toString().trim();
                    String cUserSolic=solic_SinEnviar.get(i).get(6).toString().trim();
                    String cEstSolic=solic_SinEnviar.get(i).get(7).toString().trim();
                    String cUserAtien=solic_SinEnviar.get(i).get(8).toString().trim();
                    String cLocalCrea=solic_SinEnviar.get(i).get(9).toString().trim();
                    int cSend=Integer.parseInt(solic_SinEnviar.get(i).get(10).toString().trim());
                    
                    ArrayList<ArrayList> prod_SinEnviar=new ArrayList();
                    BDFraccion.recuperaProductos_Solic_PaEnviar(prod_SinEnviar,cCodLocal,cCodSolic,cLocalCrea);
                    
                    if(prod_SinEnviar!=null && !prod_SinEnviar.isEmpty()){
                        boolean enviado=enviandoProd_APPS(prod_SinEnviar,utilConex);
                        if(enviado){
                            boolean ok=utilConex.enviarSolicitud(cCodLocal,cCodSolic,cNroSolic,cFecSolic,cFecAtenc,
                                                                 cUserSolic,cEstSolic,cUserAtien,cLocalCrea,cSend+1);
                            if(ok){
                                try{
                                    BDFraccion.actualiza_SendReceive_Solicitud(cCodLocal,cLocalCrea,cCodSolic,1);
                                }catch(SQLException e){
                                    log.error("Error al actualizar el envio de la solicitud");
                                    okProd=false;
                                    e.printStackTrace();
                                    break;
                                }
                            }else{
                                log.error("Error al actualizar el estado de envio de los productos");
                                okProd=false;
                                break;
                            }
                        }else{
                            log.error("Error al enviar los datos de fraccionamiento a matriz");
                            okProd=false;
                            break;
                        }
                    }else{
                        log.error("Error al recuperar los productos de la solicitud");
                        okProd=false;
                        break;
                    }
                }//fin de for de solicitud
                log.info("FIN DE PROCESO DE TRANSFERENCIA");
                if(okProd){
                    FarmaUtility.aceptarTransaccion(); 
                }else{
                    FarmaUtility.liberarTransaccion();
                }
            }else{
                log.error("NO EXISTE SOLICITUDES PARA ENVIAR");
            }
        } catch (SQLException e) {
            log.error("Error grave en la ejecucion del procedimiento");
            e.printStackTrace();
        }
        
    }

    private static boolean enviandoProd_APPS(ArrayList<ArrayList> prod_SinEnviar, UtilityConexion utilConex) {
        boolean enviadoOk=false;
        for(int j=0;j<prod_SinEnviar.size();j++){
            String pCodLocal=prod_SinEnviar.get(j).get(1).toString().trim();
            String pCodSolic=prod_SinEnviar.get(j).get(2).toString().trim();
            String pCodProd=prod_SinEnviar.get(j).get(3).toString().trim();
            String pCodLab=prod_SinEnviar.get(j).get(4).toString().trim();
            String pTipoFrac=prod_SinEnviar.get(j).get(5).toString().trim();
            String pEstado=prod_SinEnviar.get(j).get(6).toString().trim();
            String pComenFrac=prod_SinEnviar.get(j).get(7).toString().trim();
            String pUsuMod=prod_SinEnviar.get(j).get(8).toString().trim();
            String pLocalCrea=prod_SinEnviar.get(j).get(9).toString().trim();
            int pSecHist=Integer.parseInt(prod_SinEnviar.get(j).get(10).toString().trim());
            String pComenAten=prod_SinEnviar.get(j).get(11).toString().trim();
            String pFecAten=prod_SinEnviar.get(j).get(12).toString().trim();
            int pValFrac=Integer.parseInt(prod_SinEnviar.get(j).get(13).toString().trim());
            String pDescFrac=prod_SinEnviar.get(j).get(14).toString().trim();
            int pSend=Integer.parseInt(prod_SinEnviar.get(j).get(15).toString().trim());
            int pValFracHist=Integer.parseInt(prod_SinEnviar.get(j).get(16).toString().trim());
            String pDescFracHist=prod_SinEnviar.get(j).get(17).toString().trim();
            
            if(pSend==0){
                pSend=1;
                
                boolean ok=utilConex.enviarDetalleSolicitud(pCodLocal,pCodSolic,pCodProd,pCodLab,
                                                            pTipoFrac,pEstado,pComenFrac,pUsuMod,pLocalCrea,
                                                            pSecHist,pComenAten,pFecAten,pValFrac,pDescFrac,
                                                            pSend,pValFracHist,pDescFracHist);
                if(ok){
                    try{
                        BDFraccion.actualiza_SendReceive_Producto(pCodLocal,pLocalCrea,pCodSolic,pCodProd,pSend);
                        enviadoOk=true;
                    }catch(SQLException e){
                        log.error("Error al actualizar el envio de la data");
                        enviadoOk=false;
                        e.printStackTrace();
                        break;
                    }
                }else{
                    log.error("Error al enviar los datos de producto a matriz");
                    enviadoOk=false;
                    break;
                }
            }
        }// fin del for de productos
        return enviadoOk;
    }

    public static String recuperaDesc_FraccionActual(String codProducto) {
        try{
            String FraccionActual=BDFraccion.recuperaDesc_FraccionActual(codProducto);
            return FraccionActual;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static void ejecutaViajero() {
        try{
            BDFraccion.ejecutaVIajero();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static String recuperaFraccion_Cambio() {
        try{
            return BDFraccion.recuperaFraccion_Cambio();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        }
    }

    public static String validaFraccionamiento(String codLocal, String codProd, String tipoFrac) {
        String msj=null;
        try{
            msj=BDFraccion.validaFraccionamiento(codLocal,codProd,Integer.parseInt(tipoFrac.trim()));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        }
        return msj;
    }

    public static String verificaActivacion_Menu() {
        String msj=null;
        try{
            msj=BDFraccion.verificaActivacion_Menu();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        }
        return msj;
    }

    public static String buscaCodProd_CodBarra(String codBarra) {
        String codProd=null;
        try{
            codProd=BDFraccion.buscaCodProd_CodBarra(codBarra);
            return codProd;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        }
    }

    public static int verificaProdEnSolicitudes(String codProd) {
        int nroReg=-1;
        try{
            nroReg=BDFraccion.verificaProd_EnSolicitudes(codProd);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            
        }
        return nroReg;
    }
}
