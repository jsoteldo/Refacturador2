package mifarma.ptoventa.fraccion.DAO;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.fraccion.modelo.VariableProducto;
import mifarma.ptoventa.fraccion.modelo.VariableSolicitud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BDFraccion {
    private static final Logger log = LoggerFactory.getLogger(BDFraccion.class);

    private static ArrayList parametros = new ArrayList();
    
    public BDFraccion() {}

    public static void cargarListaProductos(FarmaTableModel pTableModel)throws SQLException{
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_SOLICITA.FRAC_LISTA_PRODUCTOS(?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, 
                                                 "PTOVENTA_FRACCION_SOLICITA.FRAC_LISTA_PRODUCTOS(?,?)", 
                                                 parametros, false);        
    }
    
    public static void cargarListaProductos_Memoria(ArrayList<ArrayList> pListaProductos)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_SOLICITA.FRAC_LISTA_PRODUCTOS(?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pListaProductos, 
                                                 "PTOVENTA_FRACCION_SOLICITA.FRAC_LISTA_PRODUCTOS(?,?)", 
                                                 parametros);  
    }
    
    public static void recuperaMotivos(FarmaTableModel pTableModel)throws SQLException{
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_MOTIVO_FRAC(?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, 
                                                 "PTOVENTA_FRACCION_SOLICITA.RECUPERA_MOTIVO_FRAC(?,?)", 
                                                 parametros, false);
    }

    public static ArrayList<ArrayList> grabaNuevaSolicitud_Fracionamiento()throws SQLException{
        ArrayList<ArrayList>  listError=new ArrayList();
        parametros = new ArrayList();
        parametros.add(VariableSolicitud.vCod_GrupCIA);//FarmaVariables.vCodGrupoCia
        parametros.add(VariableSolicitud.vCod_Local);//FarmaVariables.vCodLocal
        parametros.add(VariableSolicitud.vUsua_Solic);
        parametros.add(VariableSolicitud.vCod_Local_Mod);//FarmaVariables.vCodLocal
        log.info("PTOVENTA_FRACCION_SOLICITA.INSERT_NUEVA_SOLICITUD_FRAC(?,?,?,?)"+parametros);
        String codSolic_Frac=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.INSERT_NUEVA_SOLICITUD_FRAC(?,?,?,?)", 
                                                 parametros);
        System.out.println("==> codSolic_Frac"+codSolic_Frac);
        if(codSolic_Frac!=null && !codSolic_Frac.equalsIgnoreCase("")){
            for(int i=0;i<VariableProducto.listaProductos.size();i++){
                ArrayList error=new ArrayList();
                parametros = new ArrayList();
                parametros.add(VariableSolicitud.vCod_GrupCIA);//FarmaVariables.vCodGrupoCia
                parametros.add(VariableSolicitud.vCod_Local);//FarmaVariables.vCodLocal
                parametros.add(codSolic_Frac);
                parametros.add(VariableProducto.listaProductos.get(i).get(VariableProducto.INDEX_Cod_Prod));
                parametros.add(VariableProducto.listaProductos.get(i).get(VariableProducto.INDEX_Cod_Lab));
                parametros.add(VariableProducto.listaProductos.get(i).get(VariableProducto.INDEX_Tipo_Frac).toString().trim());
                parametros.add(VariableProducto.listaProductos.get(i).get(VariableProducto.INDEX_Comentario));
                parametros.add(FarmaVariables.vCodLocal);
                parametros.add(FarmaVariables.vIdUsu);
                
                log.info("PTOVENTA_FRACCION_SOLICITA.INSERT_DETALLE_SOLICITUD_FRAC(?,?,?,?,?,?,?,?,?)"+parametros);
                String msjError=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.INSERT_DETALLE_SOLICITUD_FRAC(?,?,?,?,?,?,?,?,?)", 
                                                                 parametros);
                if(!msjError.equalsIgnoreCase("0")){
                    error.add(VariableProducto.listaProductos.get(i).get(VariableProducto.INDEX_Cod_Prod));
                    error.add("Error Nro. "+msjError);
                    listError.add(error);
                }
            }
        }else{
            ArrayList error=new ArrayList();
            error.add("ERROR");
            error.add("valores Nulos?");
            listError.add(error);
        }
        if(listError.size()==0){
            FarmaUtility.aceptarTransaccion();
        }else{
            FarmaUtility.liberarTransaccion();
        }
        return listError;
    }

    public static int recuperaCantidad_Solicitudes( String fechaInicio, String fechaFin,String codEstado)
    throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaInicio);
        parametros.add(fechaFin);
        parametros.add(codEstado.trim());
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_CANT_SOLIC_FRAC(?,?,?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureInt("PTOVENTA_FRACCION_SOLICITA.RECUPERA_CANT_SOLIC_FRAC(?,?,?,?,?)", 
                                                 parametros); 
    }

    public static void recuperoProducto_Fracionable(ArrayList pTableProducto,String vCodProd)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(vCodProd);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_PRODUCTO_FRAC(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pTableProducto, 
                                                 "PTOVENTA_FRACCION_SOLICITA.RECUPERA_PRODUCTO_FRAC(?,?,?)", 
                                                 parametros);
        
    }
    
    
    public static void cargaTabla_SolicitudFrac(FarmaTableModel pTablaSolic, String fechaIni, String fechaFin,
                                                String codEstado)throws SQLException{
        pTablaSolic.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaIni);
        parametros.add(fechaFin);
        parametros.add(codEstado);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_SOLIC_FRAC(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTablaSolic,
                                                 "PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_SOLIC_FRAC(?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaLista_SolicitudFrac(ArrayList<ArrayList> pListaSolic, String fechaIni, String fechaFin,
                                                String codEstado)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(fechaIni);
        parametros.add(fechaFin);
        parametros.add(codEstado);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_SOLIC_FRAC(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pListaSolic,
                                                 "PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_SOLIC_FRAC(?,?,?,?,?)",
                                                 parametros);
    }

    public static void cargaTabla_ProductosFrac(FarmaTableModel pTablaProd, String nroSolic,
                                                String codLocalCrea)throws SQLException{
        pTablaProd.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(nroSolic);
        parametros.add(codLocalCrea);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_PROD_FRAC(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTablaProd,
                                                 "PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_PROD_FRAC(?,?,?,?)",
                                                 parametros, false);
    }

    public static void cargaLista_ProductosFrac(ArrayList<ArrayList> pListaProd, String nroSolic)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(nroSolic);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_PROD_FRAC(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pListaProd,
                                                          "PTOVENTA_FRACCION_SOLICITA.RECUPERA_LISTA_PROD_FRAC(?,?,?)",
                                                          parametros);
    }

    public static int actualiza_AnulacionSolicitud(String nroSolic) throws SQLException{
        int ok=0;
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(nroSolic);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(FarmaVariables.vCodLocal);//Local que modifica
        log.info("PTOVENTA_FRACCION_SOLICITA.ACTUALIZA_ANULACION(?,?,?,?,?)"+parametros);
        String flag=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.ACTUALIZA_ANULACION(?,?,?,?,?)",
                                                                parametros);
        System.out.println("---------> "+flag);
        if(flag.equalsIgnoreCase("ok")){
            ok=1;
            FarmaUtility.aceptarTransaccion();
        }else if(flag.equalsIgnoreCase("null")){
            ok=2;
            FarmaUtility.liberarTransaccion();
        }else{
            FarmaUtility.liberarTransaccion();
            ok=3;
        }
        return ok;
    }

    public static void recuperaOptionTipo(ArrayList<ArrayList> option, String codOption)throws SQLException{
        parametros = new ArrayList();
        parametros.add(codOption);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_OPTIONS(?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(option,
                                                          "PTOVENTA_FRACCION_SOLICITA.RECUPERA_OPTIONS(?)",
                                                          parametros);
    }

    public static int recuperaFraccionActual(String codProd)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codProd);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_FRACCION_ACTUAL(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureInt("PTOVENTA_FRACCION_SOLICITA.RECUPERA_FRACCION_ACTUAL(?,?,?)",parametros);
    }
    
    public static String recuperaDesc_FraccionActual(String codProd)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codProd);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUP_DESC_FRAC_ACTUAL(?,?,?)"+parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.RECUP_DESC_FRAC_ACTUAL(?,?,?)",parametros);
    }
    
    public static void recuperaSolicitudes_Pendientes(ArrayList<ArrayList> solicPendientes)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_SOLIC_PENDIENTES(?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(solicPendientes, 
                                                          "PTOVENTA_FRACCION_SOLICITA.RECUPERA_SOLIC_PENDIENTES(?,?)", 
                                                          parametros);
    }

    public static void recuperaProductos_Solicitud(ArrayList<ArrayList> prodPendientes,
                                                    String codSolic,String cEstado)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolic);
        parametros.add(cEstado);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_PROD_SOLICITUD(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(prodPendientes, 
                                                          "PTOVENTA_FRACCION_SOLICITA.RECUPERA_PROD_SOLICITUD(?,?,?,?)", 
                                                         parametros);
    }
    /**********************************************************************************************************/
/*    public static void atenderFracc_Detalle(String codLocal, String codSolic, String codProd, 
                                                String comentario,String codEstado)throws SQLException {
            parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(codLocal);
            parametros.add(codSolic);
            parametros.add(codProd);
            parametros.add(comentario);
            parametros.add(FarmaVariables.vIdUsu);
            parametros.add(FarmaVariables.vCodLocal);
            parametros.add(codEstado);
            log.info("PTOVENTA_FRACCION_SOLICITA.ATENDER_FRAC_AUTOMATICO(?,?,?,?,?,?,?,?)"+parametros);
            String dato=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.ATENDER_FRAC_AUTOMATICO(?,?,?,?,?,?,?,?)", 
                                                                     parametros);
            log.info("==> FIN ATENCION DETALLE: "+dato);
        }
/*
    public static void atenderFracc_Cabecera(String codSolic,
                                             String codEstado) throws SQLException{
            parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodLocal);
            parametros.add(codSolic);
            parametros.add(codEstado);
            parametros.add(FarmaVariables.vIdUsu);
            parametros.add(FarmaVariables.vCodLocal);
            log.info("PTOVENTA_FRACCION_SOLICITA.ATIENDE_SOLICITUD_CAB(?,?,?,?,?,?)"+parametros);
            int dato=FarmaDBUtility.executeSQLStoredProcedureInt("PTOVENTA_FRACCION_SOLICITA.ATIENDE_SOLICITUD_CAB(?,?,?,?,?,?)", 
                                                                     parametros);
            log.info("==> FIN CABECERA: "+dato);
        }
 */
    public static void recuperaSolicitud_Frac(ArrayList<ArrayList> solicitud, String codSolic,String codLocalCrea) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codSolic);
        parametros.add(codLocalCrea);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_SOLICITUD_FRAC(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(solicitud, 
                                                          "PTOVENTA_FRACCION_SOLICITA.RECUPERA_SOLICITUD_FRAC(?,?,?,?)", 
                                                         parametros);
    }
/*
    public static void recuperaProd_Aprobados(ArrayList<ArrayList> fracAprobados) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_FRAC_APROBADOS(?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(fracAprobados, 
                                                          "PTOVENTA_FRACCION_SOLICITA.RECUPERA_FRAC_APROBADOS(?)", 
                                                         parametros);
    }
/*
    public static void ejecutaViajero()throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_SOLICITA.EJECUTA_VIAJERO(?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, 
                                                 "PTOVENTA_FRACCION_SOLICITA.EJECUTA_VIAJERO(?)", 
                                                 parametros,false);
    }*/
/**********************************************************************************************************/
    public static void recuperarSolic_SinEnviar(ArrayList<ArrayList> solicSinEnviar) throws SQLException{
        //cCodLocal_in
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_PROCESA.RECUPERA_SOLIC_PARA_ENVIAR(?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(solicSinEnviar, 
                                                         "PTOVENTA_FRACCION_PROCESA.RECUPERA_SOLIC_PARA_ENVIAR(?)", 
                                                         parametros);
    }

    public static void recuperaProductos_Solic_PaEnviar(ArrayList<ArrayList> prodSolic, String codLocal,
                                                        String codSolic,String codLocalCrea) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(codLocal);
        parametros.add(codSolic);
        parametros.add(codLocalCrea);
        log.info("PTOVENTA_FRACCION_PROCESA.RECUPERA_PROD_PARA_ENVIAR(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(prodSolic, 
                                                         "PTOVENTA_FRACCION_PROCESA.RECUPERA_PROD_PARA_ENVIAR(?,?,?,?)",
                                                         parametros);
    }

    public static void actualiza_SendReceive_Producto(String codLocal, String codLocalCrea, String codSolic, 
                                                      String codProd,int send)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(codLocal);
        parametros.add(codLocalCrea);
        parametros.add(codSolic);
        parametros.add(codProd);
        parametros.add(send);
        log.info("PTOVENTA_FRACCION_PROCESA.ACTUALIZAR_EST_PROD_ENVIADO(?,?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, 
                                                 "PTOVENTA_FRACCION_PROCESA.ACTUALIZAR_EST_PROD_ENVIADO(?,?,?,?,?,?)",
                                                 parametros,false);
    }

    public static void actualiza_SendReceive_Solicitud(String codLocal, String codLocalCrea, 
                                                       String codSolic, int send)throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(codLocal);
        parametros.add(codLocalCrea);
        parametros.add(codSolic);
        parametros.add(send);
        log.info("PTOVENTA_FRACCION_PROCESA.ACTUALIZAR_EST_SOLIC_ENVIADO(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, 
                                                 "PTOVENTA_FRACCION_PROCESA.ACTUALIZAR_EST_SOLIC_ENVIADO(?,?,?,?,?)",
                                                 parametros,false);
    }

    public static void procesarSolcitud() throws SQLException{
        log.info("==> INICIA PROCESO");
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.info("PTOVENTA_FRACCION_PROCESA.PROCESA_SOLICITUD_FRAC(?,?)"+parametros);
        String dato=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_PROCESA.PROCESA_SOLICITUD_FRAC(?,?)", 
                                                                 parametros);
        log.info("==> FIN PROCESO: "+dato);
    }


    public static void ejecutaVIajero()throws SQLException{
        log.info("==> INICIA PROCESO: VIAJERO");
        parametros = new ArrayList();        
        log.info("ptoventa_viaj_fraccion.ejecuta_viajero"+parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null,"ptoventa_viaj_fraccion.ejecuta_viajero", 
                                                                 parametros,false);
        log.info("==> FIN PROCESO: VIAJERO");
    }

    public static String recuperaFraccion_Cambio() throws SQLException{
        parametros = new ArrayList();
        parametros.add(VariableProducto.vCod_Prod);
        parametros.add(VariableProducto.vTipo_Frac);
        log.info("PTOVENTA_FRACCION_SOLICITA.RECUPERA_FRAC_CAMBIO(?,?)"+parametros);
        String dato=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.RECUPERA_FRAC_CAMBIO(?,?)", 
                                                                 parametros);
        return dato;
    }

    public static String validaFraccionamiento(String codLocal, String codProd, int tipoFrac) throws SQLException{
        parametros = new ArrayList();
        parametros.add(codLocal);
        parametros.add(codProd);
        parametros.add(tipoFrac);
        log.info("PTOVENTA_FRACCION_LOGICA.PRUEBA_STOCK(?,?,?)"+parametros);
        String dato=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_LOGICA.PRUEBA_STOCK(?,?,?)", 
                                                                 parametros);
        return dato;
    }

    public static String verificaActivacion_Menu()throws SQLException{
        parametros = new ArrayList();
        log.info("PTOVENTA_FRACCION_SOLICITA.MENU_ACTIVO()"+parametros);
        String dato=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.MENU_ACTIVO()", 
                                                                 parametros);
        return dato;
    }

    public static String buscaCodProd_CodBarra(String codBarra) throws SQLException{
        parametros = new ArrayList();
        parametros.add(codBarra);
        log.info("PTOVENTA_FRACCION_SOLICITA.BUSCA_CODPROD_CODBARRA(?)"+parametros);
        String codProd=FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_FRACCION_SOLICITA.BUSCA_CODPROD_CODBARRA(?)", 
                                                                 parametros);
        return codProd;
    }

    public static int verificaProd_EnSolicitudes(String codProd) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codProd);
        log.info("PTOVENTA_FRACCION_SOLICITA.VERIFICA_PROD_SOLICITUD(?,?,?)"+parametros);
        int rpta=FarmaDBUtility.executeSQLStoredProcedureInt("PTOVENTA_FRACCION_SOLICITA.VERIFICA_PROD_SOLICITUD(?,?,?)",
                                                                 parametros);
        return rpta;
    }
}
