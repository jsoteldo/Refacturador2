package mifarma.ptoventa.ventas.reference;


import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JDialog;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityPacksPromo {

    private static final Logger log = LoggerFactory.getLogger(UtilityPacksPromo.class);

    public UtilityPacksPromo() {
    }
    
    public static void proceso_automatico_packs()throws SQLException {
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(new JDialog(), null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
            log.info("NO aplica pack automatico a CONVENIO");
        }else{
            String pID_PC_DOC = "";
            pID_PC_DOC = FarmaVariables.vIpPc;
            // carga datos vendidos en tablas
            previoAnalisis(pID_PC_DOC);
            
            for(int i=0;i<UtilityCalculoPrecio.getSizeDetalleVenta();i++){
                BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
                if(!(bean.getPCodEQCampAcumula().trim().length()>0||bean.getPCodProdRegalo().trim().length()>0)){
                    // el producto que acumula no enviara
                    // los productos de regalo tampoco enviara
                    upProdAnalisis(pID_PC_DOC,
                                   bean.getVCodProd(),
                                   bean.getVCtdVta(),
                                   bean.getVValFracVta());
                }
            }
            
            for(int v=0;v<VariablesVentas.vArrayList_Prod_Prom_Aux_XX.size();v++){
                ArrayList vAux = (ArrayList)VariablesVentas.vArrayList_Prod_Prom_Aux_XX.get(v);
                upProdAnalisis(pID_PC_DOC,
                               vAux.get(0).toString().trim(),//cod_prod
                               vAux.get(4).toString().trim(),//cantidad
                               vAux.get(10).toString().trim());//valFrac
            }
            
            VariablesVentas.vArrayList_Prom_Aux_XX = new ArrayList();
            VariablesVentas.vArrayList_Prod_Prom_Aux_XX = new ArrayList();
            VariablesVentas.vArrayList_Prod_Promociones_temporal = new ArrayList();
            VariablesVentas.vArrayList_Promociones_temporal = new ArrayList();    
            
            pasoUnoPacksPromo(pID_PC_DOC);
            
            pasoDosOperaCtdPacks(pID_PC_DOC);
            
            //FarmaUtility.showMessage(new JDialog(), "antes ", null);
            ArrayList vCabPack = new ArrayList();
            ArrayList vDetPack = new ArrayList();
            ArrayList vNormal  = new ArrayList();
            getCabPack(pID_PC_DOC,vCabPack);
            //
            if(vCabPack.size()> 0){
                getDetPack(pID_PC_DOC,vDetPack);
                getProdNormal(pID_PC_DOC,vNormal);
            }
            else{
                    vCabPack = new ArrayList();
                    vDetPack = new ArrayList();
                    vNormal  = new ArrayList();
              }
                                      
            opera_variedad_automatica(vCabPack,vDetPack,vNormal,pID_PC_DOC);
        }
    }

    public static void previoAnalisis(String pIpPc) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.P_PREVIO_ANALISIS(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_PREVIO_ANALISIS(?,?,?)",
                                                 parametros,false);
    } 
    
    public static void upProdAnalisis(String pIpPc,
                                      String pCodProd,
                                      String pCtd,
                                      String pValFrac) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        parametros.add(pCodProd);
        parametros.add(pCtd);
        parametros.add(pValFrac);
        if(VariablesFidelizacion.vDniCliente.trim().length()>0){
            parametros.add("S");
        }
        else {
            parametros.add("N");
        }
        log.info("invocando a PKG_PACKS_PROMO.P_CARGA_PROD_ANALISIS(?,?,?,?,?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_CARGA_PROD_ANALISIS(?,?,?,?,?,?,?)",
                                                 parametros,false);
    } 
    
    public static void pasoUnoPacksPromo(String pIpPc) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.P_PASO_01_ANALISIS(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_PASO_01_ANALISIS(?,?,?)",
                                                 parametros,false);
    } 
    
    public static void pasoDosOperaCtdPacks(String pIpPc) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.P_PASO_02_OPERA_CTD_X_PACK(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_PASO_02_OPERA_CTD_X_PACK(?,?,?)",
                                                 parametros,false);
    }
    public static void getCabPack(String pIpPc,ArrayList vLista) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.F_CUR_GET_CAB_PACK(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vLista,
                                                 "PKG_PACKS_PROMO.F_CUR_GET_CAB_PACK(?,?,?)",
                                                 parametros);
    }
    
    public static void getDetPack(String pIpPc,ArrayList vLista) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.F_CUR_GET_DET_PACK(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vLista,
                                                 "PKG_PACKS_PROMO.F_CUR_GET_DET_PACK(?,?,?)",
                                                 parametros);
    }
    
    public static void getProdNormal(String pIpPc,ArrayList vLista) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.F_CUR_GET_NORMAL(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vLista,
                                                 "PKG_PACKS_PROMO.F_CUR_GET_NORMAL(?,?,?)",
                                                 parametros);
    }

    public static void getProdBorra(String pIpPc,ArrayList vLista) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.F_CUR_GET_BORRA_PROD(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vLista,
                                                 "PKG_PACKS_PROMO.F_CUR_GET_BORRA_PROD(?,?,?)",
                                                 parametros);
    }    


    private static void opera_variedad_automatica(ArrayList vListaCabPack, 
                                                  ArrayList vListaDetPack, 
                                                  ArrayList vListaProdNormal,
                                                  String   pID_PC_DOC)throws SQLException {
        if(vListaCabPack.size()>0){
            
            ArrayList vListaBorra = new ArrayList();
            getProdBorra(pID_PC_DOC,vListaBorra);
            
            // 01 borrar todos los productos que son parte de pack y lista normal
            for(int i=0;i<vListaBorra.size();i++){
                String pCodProdBorra = FarmaUtility.getValueFieldArrayList(vListaBorra, i,0);
                for(int a=0;a<UtilityCalculoPrecio.getSizeDetalleVenta();a++){
                    BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(a);
                    if(!(bean.getPCodEQCampAcumula().trim().length()>0||bean.getPCodProdRegalo().trim().length()>0)){
                        if(bean.getVCodProd().equalsIgnoreCase(pCodProdBorra)){
                            String pCtdNueva = "";
                            boolean vActualiza = false;
                            // 02 agrega el de packs las CABECERAS
                            for(int j=0;j<vListaProdNormal.size();j++){
                               String pCodProdAux = FarmaUtility.getValueFieldArrayList(vListaProdNormal,j,0);   
                               pCtdNueva = FarmaUtility.getValueFieldArrayList(vListaProdNormal,j,1);   
                                if(pCodProdAux.equalsIgnoreCase(pCodProdBorra)){
                                    vActualiza = true;
                                    break;
                                }
                            }
                            if(vActualiza){
                                bean.setVCtdVta(pCtdNueva);
                            }
                            else
                            UtilityCalculoPrecio.deleteBeannPosDetalleVenta(a);
                        }
                            
                    }
                }
            }
            
            double igvGlobalXX = 0.0;
            for(int i=0;i<vListaDetPack.size();i++){
                igvGlobalXX = igvGlobalXX + FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 12));
            }
            
            // 02 agrega el de packs las CABECERAS
            for(int i=0;i<vListaCabPack.size();i++){
                //llenado de arreglo de promociones
                ArrayList myArrayP = new ArrayList();
                //double total = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 4));
                double total = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 3));
                double cantidad = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 2));
                String pagototal = FarmaUtility.formatNumber(cantidad * total, 2);
                myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 0));
                myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 1));
                myArrayP.add(" "); //vacio
                myArrayP.add(FarmaUtility.formatNumber(total, 3));
                myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 2));
                myArrayP.add(VariablesVentas.vDes_Prom); //vacio
                myArrayP.add(FarmaUtility.formatNumber(total,
                                                       3)); //el precio venta seria el mismo que precio , ya que no ahi descuento
                myArrayP.add("" + pagototal);
                myArrayP.add("0.00");
                myArrayP.add(" "); //9
                myArrayP.add(" ");
                myArrayP.add(" ");
                myArrayP.add(igvGlobalXX + "");
                myArrayP.add(" "); //NUMERO TELEFONICO SI ES RECARGA AUTOMATICA
                myArrayP.add(" "); //INDICADOR DE PRODUCTO VIRTUAL
                myArrayP.add(" "); //TIPO DE PRODUCTO VIRTUAL
                myArrayP.add(" "); //INDICADOR PROD CONTROLA STOCK
                myArrayP.add(" "); //VENTA
                myArrayP.add(" "); //18 myArray.add(VariablesVentas.vVal_Prec_Pub);
                myArrayP.add(" "); //19 myArray.add(VariablesVentas.vIndOrigenProdVta);
                myArrayP.add("S"); //20 es promocion
                myArrayP.add("0"); //21 dscto 2
                myArrayP.add("N"); //22 ind. tratamiento
                myArrayP.add(" "); //23 cantxDia tratamiento
                myArrayP.add(" "); //24 cantxDias tratamiento
                // KMONCADA 10.08.2015
                myArrayP.add("N");//25 INDICA SI PACK APLICA PARA CLIENTE FIDELIZADO O NO
                FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Promociones, myArrayP, true, 0);                
            }
            
            for(int i=0;i<vListaDetPack.size();i++){
                ArrayList myArray2 = new ArrayList();
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 1));// cod prod
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 2));//des prod
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 3));//unid vta
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7));//val prec lista
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 5)); //ingresa cantidad
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 9)); //proc_Dcto_1;
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7));//precio venta unitario
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 10) + "");//sub total
                    myArray2.add(" "); //val_Bono
                    myArray2.add(" "); //9
                    myArray2.add("1");//VALOR FRACCION
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 11));//% igv
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 12)); //generado // valor igv
                    myArray2.add(" "); //vacio
                    myArray2.add("N");
                    myArray2.add(" "); //vacio
                    myArray2.add("S"); //Indicador de Stock
                    myArray2.add(" ");
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 0)); //codigo de la promocion donde esta el producto
                    myArray2.add("S"); //indica q esta en una promocion
                    myArray2.add("0"); //proc_Dcto_2;
                    myArray2.add("" +FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7)); //prec_publico; // numero 23
                    myArray2.add("" +FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 8)); //JCHAVEZ 20102009 columna 22
                    myArray2.add(VariablesVentas.vInd_Origen_Prod_Prom); //JCHAVEZ 20102009 columna 23
                    myArray2.add(VariablesVentas.secRespStk); 
                    myArray2.add(VariablesVentas.vPrecFijo);                
                    FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Prod_Promociones,(ArrayList)(myArray2.clone()), true, 0);
            }
            
            //UtilityCalculoPrecio.reloadVenta();
        }
    }
    
    /*public void recalcula{
        * asc ___
        * asca ___
        Para brindarle los beneficios con monedero.
    }*/
    
    //INI AOVIEDO 16/06/2017
    public static void proceso_automatico_packs_XY()throws SQLException {
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(new JDialog(), null) && VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
            log.info("NO aplica pack automatico a CONVENIO");
        }else{
            String pID_PC_DOC = "";
            pID_PC_DOC = FarmaVariables.vIpPc;
            // carga datos vendidos en tablas
            previoAnalisis_XY(pID_PC_DOC);
            
            for(int i=0;i<UtilityCalculoPrecio.getSizeDetalleVenta();i++){
                BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(i);
                if(!(bean.getPCodEQCampAcumula().trim().length()>0||bean.getPCodProdRegalo().trim().length()>0)){
                    // el producto que acumula no enviara
                    // los productos de regalo tampoco enviara
                    upProdAnalisis_XY(pID_PC_DOC,
                                      bean.getVCodProd(),
                                      bean.getVCtdVta(),
                                      bean.getVValFracVta());
                }
            }
            
            pasoUnoPacksPromo_XY(pID_PC_DOC);
            
            //INI AOVIEDO 06/10/2017
            //pasoDosOperaCtdPacks_XY(pID_PC_DOC);
            pasoDosOperaCtdPacks_XY_version2(pID_PC_DOC);
            //FIN AOVIEDO 06/10/2017
            
            ArrayList vCabPack = new ArrayList();
            ArrayList vDetPack = new ArrayList();
            ArrayList vNormal  = new ArrayList();
            getCabPack(pID_PC_DOC,vCabPack);
            //
            if(vCabPack.size()> 0){
                getDetPack(pID_PC_DOC,vDetPack);
                getProdNormal(pID_PC_DOC,vNormal);
            }else{
                vCabPack = new ArrayList();
                vDetPack = new ArrayList();
                vNormal  = new ArrayList();
            }
                                      
            opera_variedad_automatica_XY(vCabPack,vDetPack,vNormal,pID_PC_DOC);
        }
    }
    
    public static void previoAnalisis_XY(String pIpPc) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.P_PREVIO_ANALISIS(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_PREVIO_ANALISIS(?,?,?)",
                                                 parametros,false);
    }
    
    public static void upProdAnalisis_XY(String pIpPc,
                                         String pCodProd,
                                         String pCtd,
                                         String pValFrac) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        parametros.add(pCodProd);
        parametros.add(pCtd);
        parametros.add(pValFrac);
        
        if(VariablesFidelizacion.vDniCliente.trim().length()>0){
            parametros.add("S");
        }
        else {
            parametros.add("N");
        }
        
        log.info("invocando a PKG_PACKS_PROMO.P_CARGA_PROD_ANALISIS_XMASY(?,?,?,?,?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_CARGA_PROD_ANALISIS_XMASY(?,?,?,?,?,?,?)",
                                                 parametros,false);
    }
    
    public static void pasoUnoPacksPromo_XY(String pIpPc) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.P_PASO_01_ANALISIS_XMASY(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_PASO_01_ANALISIS_XMASY(?,?,?)",
                                                 parametros,false);
    }
    
    public static void pasoDosOperaCtdPacks_XY(String pIpPc) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.P_PASO_02_OPERA_CTD_X_PACK_XY(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_PASO_02_OPERA_CTD_X_PACK_XY(?,?,?)",
                                                 parametros,false);
    }
    
    //INI AOVIEDO 06/10/2017
    public static void pasoDosOperaCtdPacks_XY_version2(String pIpPc) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(pIpPc);
        log.info("invocando a PKG_PACKS_PROMO.P_PASO_02_OPERA_CTD_X_PACK_XY2(?,?,?)");
        FarmaDBUtility.executeSQLStoredProcedure(null,
                                                 "PKG_PACKS_PROMO.P_PASO_02_OPERA_CTD_X_PACK_XY2(?,?,?)",
                                                 parametros,false);
    }
    //FIN AOVIEDO 06/10/2017
    
    private static void opera_variedad_automatica_XY(ArrayList vListaCabPack, 
                                                     ArrayList vListaDetPack, 
                                                     ArrayList vListaProdNormal,
                                                     String pID_PC_DOC)throws SQLException {
        if(vListaCabPack.size()>0){
            ArrayList vListaBorra = new ArrayList();
            getProdBorra(pID_PC_DOC,vListaBorra);
            
            // 01 borrar todos los productos que son parte de pack y lista normal
            for(int i=0;i<vListaBorra.size();i++){
                String pCodProdBorra = FarmaUtility.getValueFieldArrayList(vListaBorra, i,0);
                for(int a=0;a<UtilityCalculoPrecio.getSizeDetalleVenta();a++){
                    BeanDetalleVenta bean = UtilityCalculoPrecio.getBeanPosDetalleVenta(a);
                    if(!(bean.getPCodEQCampAcumula().trim().length()>0||bean.getPCodProdRegalo().trim().length()>0)){
                        if(bean.getVCodProd().equalsIgnoreCase(pCodProdBorra)){
                            String pCtdNueva = "";
                            boolean vActualiza = false;
                            // 02 agrega el de packs las CABECERAS
                            for(int j=0;j<vListaProdNormal.size();j++){
                               String pCodProdAux = FarmaUtility.getValueFieldArrayList(vListaProdNormal,j,0);   
                               pCtdNueva = FarmaUtility.getValueFieldArrayList(vListaProdNormal,j,1);   
                                if(pCodProdAux.equalsIgnoreCase(pCodProdBorra)){
                                    vActualiza = true;
                                    break;
                                }
                            }
                            if(vActualiza){
                                bean.setVCtdVta(pCtdNueva);
                            }else
                                UtilityCalculoPrecio.deleteBeannPosDetalleVenta(a);
                        }
                    }
                }
            }
            
            double igvGlobalXY = 0.0;
            for(int i=0;i<vListaDetPack.size();i++){
                igvGlobalXY = igvGlobalXY + FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 12));
            }
            
            // 02 agrega el de packs las CABECERAS
            for(int i=0;i<vListaCabPack.size();i++){
                //llenado de arreglo de promociones
                ArrayList myArrayP = new ArrayList();
                double total = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 3));
                double cantidad = FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 2));
                String pagototal = FarmaUtility.formatNumber(cantidad * total, 2);
                double igvTotal = FarmaUtility.getDecimalNumber(pagototal) - (FarmaUtility.getDecimalNumber(pagototal)/(1+FarmaUtility.getDecimalNumber("18")/100));
                myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 0));
                myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 1));
                myArrayP.add(" "); //vacio
                myArrayP.add(FarmaUtility.formatNumber(total, 3));
                myArrayP.add(FarmaUtility.getValueFieldArrayList(vListaCabPack, i, 2));
                myArrayP.add(VariablesVentas.vDes_Prom); //vacio
                myArrayP.add(FarmaUtility.formatNumber(total,
                                                       3)); //el precio venta seria el mismo que precio , ya que no ahi descuento
                myArrayP.add("" + pagototal);
                myArrayP.add("0.00");
                myArrayP.add(" "); //9
                myArrayP.add(" ");
                myArrayP.add(" ");
                myArrayP.add(igvGlobalXY + "");
                myArrayP.add(" "); //NUMERO TELEFONICO SI ES RECARGA AUTOMATICA
                myArrayP.add(" "); //INDICADOR DE PRODUCTO VIRTUAL
                myArrayP.add(" "); //TIPO DE PRODUCTO VIRTUAL
                myArrayP.add(" "); //INDICADOR PROD CONTROLA STOCK
                myArrayP.add(" "); //VENTA
                myArrayP.add(" "); //18 myArray.add(VariablesVentas.vVal_Prec_Pub);
                myArrayP.add(" "); //19 myArray.add(VariablesVentas.vIndOrigenProdVta);
                myArrayP.add("S"); //20 es promocion
                myArrayP.add("0"); //21 dscto 2
                myArrayP.add("N"); //22 ind. tratamiento
                myArrayP.add(" "); //23 cantxDia tratamiento
                myArrayP.add(" "); //24 cantxDias tratamiento
                // KMONCADA 10.08.2015
                myArrayP.add("N");//25 INDICA SI PACK APLICA PARA CLIENTE FIDELIZADO O NO
                FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Promociones, myArrayP, true, 0);                
            }
            
            for(int i=0;i<vListaDetPack.size();i++){
                ArrayList myArray2 = new ArrayList();
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 1));// cod prod
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 2));//des prod
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 3));//unid vta
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7));//val prec lista
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 5)); //ingresa cantidad
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 9)); //proc_Dcto_1;
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7));//precio venta unitario
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 10) + "");//sub total
                    myArray2.add(" "); //val_Bono
                    myArray2.add(" "); //9
                    myArray2.add("1");//VALOR FRACCION
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 11));//% igv
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 12)); //generado // valor igv
                    myArray2.add(" "); //vacio
                    myArray2.add("N");
                    myArray2.add(" "); //vacio
                    myArray2.add("S"); //Indicador de Stock
                    myArray2.add(" ");
                    myArray2.add(FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 0)); //codigo de la promocion donde esta el producto
                    myArray2.add("S"); //indica q esta en una promocion
                    myArray2.add("0"); //proc_Dcto_2;
                    myArray2.add("" +FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 7)); //prec_publico; // numero 23
                    myArray2.add("" +FarmaUtility.getValueFieldArrayList(vListaDetPack, i, 8)); //JCHAVEZ 20102009 columna 22
                    myArray2.add(VariablesVentas.vInd_Origen_Prod_Prom); //JCHAVEZ 20102009 columna 23
                    myArray2.add(VariablesVentas.secRespStk); 
                    myArray2.add(VariablesVentas.vPrecFijo);                
                    FarmaUtility.operaListaProd(VariablesVentas.vArrayList_Prod_Promociones,(ArrayList)(myArray2.clone()), true, 0);
            }
            
            //UtilityCalculoPrecio.reloadVenta();
        }
    }
    //INI AOVIEDO 16/06/2017
    
    //INI AOVIEDO 21/06/2017
    public static String verificaFidelizacionPromocion(String codigoPromocion) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(codigoPromocion);
        log.info("invocando a PKG_PACKS_PROMO.F_GET_IND_FID_PROMO(?,?,?)");
        return FarmaDBUtility.executeSQLStoredProcedureStr("PKG_PACKS_PROMO.F_GET_IND_FID_PROMO(?,?,?)", parametros);
    }
    //FIN AOVIEDO 21/06/2017
}
