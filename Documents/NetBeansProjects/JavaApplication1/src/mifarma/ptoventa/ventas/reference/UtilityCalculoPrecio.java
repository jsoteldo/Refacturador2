package mifarma.ptoventa.ventas.reference;


import farmapuntos.bean.BeanTarjeta;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityCalculoPrecio {

    private static final Logger log = LoggerFactory.getLogger(UtilityCalculoPrecio.class);

    public UtilityCalculoPrecio() {
    }
    
    private static ArrayList<BeanDetalleVenta> vListaDetalleVenta =  new ArrayList<BeanDetalleVenta>(); 
    
    public static ArrayList<BeanDetalleVenta> getListaDetalleVenta(){
        return vListaDetalleVenta ;
    }
    
    public static int getSizeDetalleVenta(){
        return vListaDetalleVenta.size();
    }
    
    public static void clearDetalleVenta(){
        vListaDetalleVenta.clear();
        vListaDetalleVenta =  new ArrayList<BeanDetalleVenta>(); 
    }
    
    public static String getCadenaProductos(){
        String pCadena="";
        for (int i = 0; i < vListaDetalleVenta.size(); i++) {
            pCadena += (vListaDetalleVenta.get(i)).getVCodProd().trim() + "@";
        }
        return pCadena;
    }
    
    public static String getCadenaProdPercepcion(){
        String pCadena="";
        for (int i = 0; i < vListaDetalleVenta.size(); i++) {
            BeanDetalleVenta bean = vListaDetalleVenta.get(i);
            if(i== 0){
                pCadena = bean.getVCodProd()+"|"+bean.getVCtdVta()+"|"+bean.getVSubTotal();
            }else{
                pCadena = pCadena + "@"+bean.getVCodProd()+"|"+bean.getVCtdVta()+"|"+bean.getVSubTotal();
            }
        }
        return pCadena;
    }
    
    public static boolean isExisteCodProd(String pCodProdBusqueda){
        boolean existe = false;
        for (int i = 0; i < vListaDetalleVenta.size(); i++) {
            if (pCodProdBusqueda.equalsIgnoreCase(vListaDetalleVenta.get(i).getVCodProd())) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public static void loadDetalleVenta(ArrayList vLista){
        for (int i = 0; i < vListaDetalleVenta.size(); i++)
            vLista.add(getBeanToArray(i));
    }
    
    public static ArrayList getListaFormatoArray(){
        ArrayList vAux = new ArrayList();
        for(int i=0;i<vListaDetalleVenta.size();i++){
            vAux.add(getBeanToArray(i));
        }
        return vAux;
    }
    
    public static void reloadVenta(){
        ArrayList vLista = (ArrayList)getListaFormatoArray().clone();
        clearDetalleVenta();
        uploadDetalleVenta(vLista);
    }
    
    public static ArrayList getBeanToArray(int pos){
        ArrayList vAux = new ArrayList();
        BeanDetalleVenta bean = vListaDetalleVenta.get(pos);
        vAux.add(bean.getVCodProd());
        vAux.add(bean.getVDescProd());
        vAux.add(bean.getVUnidVta());
        vAux.add(bean.getVPrecioBase());
        vAux.add(bean.getVCtdVta());
        vAux.add(bean.getVPctDcto());
        vAux.add(bean.getVPrecioVta());
        vAux.add(bean.getVSubTotal());
        vAux.add(bean.getVValBono());
        vAux.add(bean.getVNomLab());
        vAux.add(bean.getVValFracVta());
        vAux.add(bean.getVPctIgv());
        vAux.add(bean.getVValIgv());
        vAux.add(bean.getVNumTelefonoRecarga());
        vAux.add(bean.getVIndProdVirtual());
        vAux.add(bean.getVTipoProdVirtual());
        vAux.add(bean.getVIndControlaStock());
        vAux.add(bean.getVValPrecioLista_1());
        vAux.add(bean.getVValPrecPublico());
        vAux.add(bean.getVIndOrigenProd());
        vAux.add(bean.getVIndProdPromocion());
        vAux.add(bean.getVPctDcto_2());
        vAux.add(bean.getVIndTratamiento());
        vAux.add(bean.getVCtdxDia());
        vAux.add(bean.getVCtdDias());
        vAux.add(bean.getVCodCupon());
        vAux.add(bean.getVSecRespaldoStk());
        vAux.add(bean.getVValorMultiplicacion());
        vAux.add(bean.getVTipoProducto());
        vAux.add(bean.getVValPrecioConvenio());
        vAux.add(bean.getVValPrecioLista_2());
        vAux.add(bean.getVCant_Ingresada_ItemQuote());
        if(bean.isPIndCampAcumula())
            vAux.add("S");
        else
            vAux.add("N");
        vAux.add(bean.getPCodCampAcumula());
        vAux.add(bean.getPCodProdRegalo());
        vAux.add(bean.getPCodEQCampAcumula());
        // INICIO: DMOSQUEIRA 20190710
		vAux.add(bean.getVIcbperVal());
		vAux.add(bean.getVIcbperTotal());
		// FIN: DMOSQUEIRA 20190710
        return vAux;
    }
    
    public static ArrayList<BeanDetalleVenta> getListBeanDetalleVenta(ArrayList vLista){
        ArrayList<BeanDetalleVenta> vListaDetBean = new ArrayList<BeanDetalleVenta>();
        
        for(int i=0;i<vLista.size();i++){
            BeanDetalleVenta bean = new BeanDetalleVenta();
            bean.setVCodProd(FarmaUtility.getValueFieldArrayList(vLista, i,0));
            bean.setVDescProd(FarmaUtility.getValueFieldArrayList(vLista, i,1));
            bean.setVUnidVta(FarmaUtility.getValueFieldArrayList(vLista, i,2));
            bean.setVPrecioBase(FarmaUtility.getValueFieldArrayList(vLista, i,3));
            bean.setVCtdVta(FarmaUtility.getValueFieldArrayList(vLista, i,4));
            bean.setVPctDcto(FarmaUtility.getValueFieldArrayList(vLista, i,5));
            bean.setVPrecioVta(FarmaUtility.getValueFieldArrayList(vLista, i,6));
            bean.setVSubTotal(FarmaUtility.getValueFieldArrayList(vLista, i,7));
            bean.setVValBono(FarmaUtility.getValueFieldArrayList(vLista, i,8));
            bean.setVNomLab(FarmaUtility.getValueFieldArrayList(vLista, i,9));
            bean.setVValFracVta(FarmaUtility.getValueFieldArrayList(vLista, i,10));
            bean.setVPctIgv(FarmaUtility.getValueFieldArrayList(vLista, i,11));
            bean.setVValIgv(FarmaUtility.getValueFieldArrayList(vLista, i,12));
            bean.setVNumTelefonoRecarga(FarmaUtility.getValueFieldArrayList(vLista, i,13));
            bean.setVIndProdVirtual(FarmaUtility.getValueFieldArrayList(vLista, i,14));
            bean.setVTipoProdVirtual(FarmaUtility.getValueFieldArrayList(vLista, i,15));
            bean.setVIndControlaStock(FarmaUtility.getValueFieldArrayList(vLista, i,16));
            bean.setVValPrecioLista_1(FarmaUtility.getValueFieldArrayList(vLista, i,17));
            bean.setVValPrecPublico(FarmaUtility.getValueFieldArrayList(vLista, i,18));
            bean.setVIndOrigenProd(FarmaUtility.getValueFieldArrayList(vLista, i,19));
            bean.setVIndProdPromocion(FarmaUtility.getValueFieldArrayList(vLista, i,20));
            bean.setVPctDcto_2(FarmaUtility.getValueFieldArrayList(vLista, i,21));
            bean.setVIndTratamiento(FarmaUtility.getValueFieldArrayList(vLista, i,22));
            bean.setVCtdxDia(FarmaUtility.getValueFieldArrayList(vLista, i,23));
            bean.setVCtdDias(FarmaUtility.getValueFieldArrayList(vLista, i,24));
            bean.setVCodCupon(FarmaUtility.getValueFieldArrayList(vLista, i,25));
            bean.setVSecRespaldoStk(FarmaUtility.getValueFieldArrayList(vLista, i,26));
            bean.setVValorMultiplicacion(FarmaUtility.getValueFieldArrayList(vLista, i,27));
            bean.setVTipoProducto(FarmaUtility.getValueFieldArrayList(vLista, i,28));
            bean.setVValPrecioConvenio(FarmaUtility.getValueFieldArrayList(vLista, i,29));
            bean.setVValPrecioLista_2(FarmaUtility.getValueFieldArrayList(vLista, i,30));
            // INI: JHAMRC 10072019
            bean.setVIcbperVal(FarmaUtility.getValueFieldArrayList(vLista, i,36));
            bean.setVIcbperTotal(FarmaUtility.getValueFieldArrayList(vLista, i,37));
            // FIN: JHAMRC 10072019
            vListaDetBean.add(bean);
        }
        return vListaDetBean;
    }
    
    public static BeanDetalleVenta getBeanPosDetalleVenta(int pos){
        return vListaDetalleVenta.get(pos);
    }

    public static void deleteBeannPosDetalleVenta(int pos){
        vListaDetalleVenta.remove(pos);
    }

    public static void uploadDetalleVenta(ArrayList vLista) {
        log.info("uploadDeta43" + "lleVenta ** " + vLista); // JHAMRC 10072019
        log.info(vLista+"");
        for (int i = 0; i < vLista.size(); i++){
            BeanDetalleVenta bean = new BeanDetalleVenta();
            log.debug("OPERA uploadDetalleVenta ARRAY= " + vLista.get(i));
            bean.setVCodProd(FarmaUtility.getValueFieldArrayList(vLista,i,0));
            bean.setVDescProd(FarmaUtility.getValueFieldArrayList(vLista,i,1));
            bean.setVUnidVta(FarmaUtility.getValueFieldArrayList(vLista,i,2));
            bean.setVPrecioBase(FarmaUtility.getValueFieldArrayList(vLista,i,3));
            bean.setVCtdVta(FarmaUtility.getValueFieldArrayList(vLista,i,4));
            bean.setVPctDcto(FarmaUtility.getValueFieldArrayList(vLista,i,5));
            bean.setVPrecioVta(FarmaUtility.getValueFieldArrayList(vLista,i,6));
            bean.setVSubTotal(FarmaUtility.getValueFieldArrayList(vLista,i,7));
            bean.setVValBono(FarmaUtility.getValueFieldArrayList(vLista,i,8));
            bean.setVNomLab(FarmaUtility.getValueFieldArrayList(vLista,i,9));
            bean.setVValFracVta(FarmaUtility.getValueFieldArrayList(vLista,i,10));
            bean.setVPctIgv(FarmaUtility.getValueFieldArrayList(vLista,i,11));
            bean.setVValIgv(FarmaUtility.getValueFieldArrayList(vLista,i,12));
            bean.setVNumTelefonoRecarga(FarmaUtility.getValueFieldArrayList(vLista,i,13));
            bean.setVIndProdVirtual(FarmaUtility.getValueFieldArrayList(vLista,i,14));
            bean.setVTipoProdVirtual(FarmaUtility.getValueFieldArrayList(vLista,i,15));
            bean.setVIndControlaStock(FarmaUtility.getValueFieldArrayList(vLista,i,16));
            bean.setVValPrecioLista_1(FarmaUtility.getValueFieldArrayList(vLista,i,17));
            if(FarmaUtility.getValueFieldArrayList(vLista,i,18).indexOf("*")!=-1)
                bean.setVValPrecPublico(FarmaUtility.getValueFieldArrayList(vLista,i,6));
            else
                bean.setVValPrecPublico(FarmaUtility.getValueFieldArrayList(vLista,i,18));
            bean.setVIndOrigenProd(FarmaUtility.getValueFieldArrayList(vLista,i,19));
            bean.setVIndProdPromocion(FarmaUtility.getValueFieldArrayList(vLista,i,20));
            bean.setVPctDcto_2(FarmaUtility.getValueFieldArrayList(vLista,i,21));
            bean.setVIndTratamiento(FarmaUtility.getValueFieldArrayList(vLista,i,22));
            bean.setVCtdxDia(FarmaUtility.getValueFieldArrayList(vLista,i,23));
            bean.setVCtdDias(FarmaUtility.getValueFieldArrayList(vLista,i,24));
            bean.setVCodCupon(FarmaUtility.getValueFieldArrayList(vLista,i,25));
            bean.setVSecRespaldoStk(FarmaUtility.getValueFieldArrayList(vLista,i,26));
            bean.setVValorMultiplicacion(FarmaUtility.getValueFieldArrayList(vLista,i,27));
            bean.setVTipoProducto(FarmaUtility.getValueFieldArrayList(vLista,i,28));
            bean.setVValPrecioConvenio(FarmaUtility.getValueFieldArrayList(vLista,i,29));
            bean.setVValPrecioLista_2(FarmaUtility.getValueFieldArrayList(vLista,i,30));
            //dubilluz X+1
            bean.setVCant_Ingresada_ItemQuote(FarmaUtility.getValueFieldArrayList(vLista,i,31));
            //FarmaUtility.showMessage(new JDialog(), "FarmaUtility.getValueFieldArrayList(vLista,i,31) "+ FarmaUtility.getValueFieldArrayList(vLista,i,32),null);
            //dubilluz X+1
            // ANALIZA PRECIO DE VENTA DE PRODUCTO PARA VER SI ES NECESARIO CAMBIAR
            //se agrega por X+1
            if(((ArrayList)vLista.get(i)).size()>31){
                log.debug("OPERA uploadDetalleVenta X+1 " + FarmaUtility.getValueFieldArrayList(vLista,i,32).trim());
                if(FarmaUtility.getValueFieldArrayList(vLista,i,32).trim().equalsIgnoreCase("N")){
                    bean.setPIndCampAcumula(false);
                    bean.setPCodCampAcumula("");
                    bean.setPCodProdRegalo("");
                }
                else{
                    bean.setPIndCampAcumula(true);
                    bean.setPCodCampAcumula(FarmaUtility.getValueFieldArrayList(vLista,i,33));
                    bean.setPCodProdRegalo(FarmaUtility.getValueFieldArrayList(vLista,i,34));
                }
                bean.setPCodEQCampAcumula(FarmaUtility.getValueFieldArrayList(vLista,i,35));
            }
            else{
                log.debug("OPERA uploadDetalleVenta es Menor de 31 " );
                bean.setPIndCampAcumula(false);
                bean.setPCodCampAcumula("");
                bean.setPCodProdRegalo("");
                bean.setPCodEQCampAcumula("");
            }
            
            
            try {
               /* FarmaUtility.showMessage(new JDialog(),
                bean.getVCtdVta()+ " -  "+ bean.getVValPrecioConvenio()+" - "+
                bean.getVPrecioVta()
                                         ,null);*/
                
                if(FarmaUtility.getValueFieldArrayList(vLista,i,29).trim().length()>0){
                    //precio de convenio
                    bean.setPCOD_PROD(bean.getVCodProd());
                    bean.setPDESC_PROD(bean.getVDescProd());
                    bean.setPUNID_VTA_FRAC(bean.getVUnidVta());
                    bean.setPVAL_FRAC_LOCAL(bean.getVValFracVta());
                    bean.setPPREC_X_FRACC(bean.getVValPrecioConvenio());
                    bean.setPSUB_TOTAL_CORRECTO(bean.getVSubTotal());
                    bean.setPSUB_TOTAL_IRREAL(bean.getVSubTotal());
                    bean.setPPREC_BASE(FarmaUtility.getValueFieldArrayList(vLista,i,29).trim());
                    bean.setPPORC_IGV(bean.getVPctIgv());
                    bean.setPVALOR_IGV(bean.getVValIgv());
                    bean.setPPREC_VTA_UNIT_NVO(bean.getVValPrecioConvenio());

                    
                    bean.setVPrecioBase(FarmaUtility.getValueFieldArrayList(vLista,i,29).trim());
                    bean.setVValIgv(bean.getVValIgv());
                    bean.setVValPrecioLista_2(FarmaUtility.getValueFieldArrayList(vLista,i,29).trim());
                    bean.setVPrecioVta(FarmaUtility.getValueFieldArrayList(vLista,i,29).trim());
                    bean.setVSubTotal(bean.getVSubTotal());
                }
                else{
                    
                    BeanProducto beanProd ;
                        if(bean.isPIndCampAcumula())
                            beanProd = DBCalculoPrecio.getCalculoPrecioProduco(bean.getPCodProdRegalo(), bean.getVCtdVta(), bean.getVValFracVta());
                        else
                            beanProd = DBCalculoPrecio.getCalculoPrecioProduco(bean.getVCodProd(), bean.getVCtdVta(), bean.getVValFracVta());
                    
                    bean.setPCOD_PROD(beanProd.getVCOD_PROD());
                    bean.setPDESC_PROD(beanProd.getVDESC_PROD());
                    bean.setPUNID_VTA_FRAC(beanProd.getVUNID_VTA_FRAC());
                    bean.setPVAL_FRAC_LOCAL(beanProd.getVVAL_FRAC_LOCAL());
                    bean.setPPREC_X_FRACC(beanProd.getVPREC_X_FRACC());
                    bean.setPPREC_X_FRACC_CASTIGO(beanProd.getVPREC_X_FRACC_CASTIGO());
                    bean.setPDESC_UNID_VTA_SUG(beanProd.getVDESC_UNID_VTA_SUG());
                    bean.setPVAL_FRAC_VTA_SUG(beanProd.getVVAL_FRAC_VTA_SUG());
                    bean.setPPREC_X_SUG_BLIST(beanProd.getVPREC_X_SUG_BLIST());
                    bean.setPDESC_UNID_PRESENT(beanProd.getVDESC_UNID_PRESENT());
                    bean.setPVAL_PREC_VTA_ENTERO(beanProd.getVVAL_PREC_VTA_ENTERO());
                    bean.setPCTD_ENT_VTA(beanProd.getVCTD_ENT_VTA());
                    bean.setPCTD_SUG_VTA(beanProd.getVCTD_SUG_VTA());
                    bean.setPCTD_FRAC_VTA(beanProd.getVCTD_FRAC_VTA());
                    bean.setPSUB_TOTAL_CORRECTO(beanProd.getVSUB_TOTAL_CORRECTO());
                    bean.setPSUB_TOTAL_IRREAL(beanProd.getVSUB_TOTAL_IRREAL());
                    bean.setPAHORRO_REDONDEO(beanProd.getVAHORRO_REDONDEO());
                    //bean.setPPREC_BASE(FarmaUtility.getValueFieldArrayList(vLista,i,3)); //(beanProd.getVPREC_BASE()); // DMOSQUEIRA 20190710
                    //dubilluz 2019.07.31
                    bean.setPPREC_BASE(beanProd.getVPREC_BASE());
                    bean.setPPORC_IGV(beanProd.getVPORC_IGV());
                    
                    bean.setVPrecioBase(beanProd.getVPREC_BASE());
                    bean.setVValIgv(beanProd.getVVALOR_IGV());
                    bean.setVValPrecioLista_2(beanProd.getVPREC_VTA_UNIT_NVO());
                    
                    /*if(bean.isPIndCampAcumula()){
                        bean.setPPREC_VTA_UNIT_NVO("0");
                        bean.setVPrecioVta("0");
                        bean.setVSubTotal("0");    
                        bean.setPVALOR_IGV("0");
                    }
                    else{*/
                        //bean.setPPREC_VTA_UNIT_NVO(FarmaUtility.getValueFieldArrayList(vLista,i,3)); //(beanProd.getVPREC_VTA_UNIT_NVO()); // DMOSQUEIRA 20190710
                        //bean.setVPrecioVta(FarmaUtility.getValueFieldArrayList(vLista,i,3)); //(beanProd.getVPREC_VTA_UNIT_NVO()); // DMOSQUEIRA 20190710
                        //dubilluz 2019.07.31
                        bean.setPPREC_VTA_UNIT_NVO(beanProd.getVPREC_VTA_UNIT_NVO());
                        bean.setVPrecioVta(beanProd.getVPREC_VTA_UNIT_NVO());                    
                        bean.setVSubTotal(beanProd.getVSUB_TOTAL_CORRECTO());
                        bean.setPVALOR_IGV(beanProd.getVVALOR_IGV());
                    //}
                    
                    
                }
                
                //FarmaUtility.showMessage(new JDialog(),bean.getVPrecioBase() + " @ "+ bean.getVPrecioVta(),null);
				// INI: JHAMRC 10072019
                bean.setVIcbperVal(FarmaUtility.getValueFieldArrayList(vLista,i,36));
                bean.setVIcbperTotal(FarmaUtility.getValueFieldArrayList(vLista,i,37));
                // FIN: JHAMRC 10072019
                
            } catch (Exception sqle) {
                // TODO: Add catch code
                sqle.printStackTrace();
            }
            //  

            vListaDetalleVenta.add(bean);
        }
    }
    
    public static ArrayList getArrayPosDetalleVenta(int pos) {
        BeanDetalleVenta bean = getBeanPosDetalleVenta(pos);
        ArrayList  vAux =  new ArrayList();            
        vAux.add(bean.getVCodProd());
        vAux.add(bean.getVDescProd());
        vAux.add(bean.getVUnidVta());
        vAux.add(bean.getVPrecioBase());
        vAux.add(bean.getVCtdVta());
        vAux.add(bean.getVPctDcto());
        vAux.add(bean.getVPrecioVta());
        vAux.add(bean.getVSubTotal());
        vAux.add(bean.getVValBono());
        vAux.add(bean.getVNomLab());
        vAux.add(bean.getVValFracVta());
        vAux.add(bean.getVPctIgv());
        vAux.add(bean.getVValIgv());
        vAux.add(bean.getVNumTelefonoRecarga());
        vAux.add(bean.getVIndProdVirtual());
        vAux.add(bean.getVTipoProdVirtual());
        vAux.add(bean.getVIndControlaStock());
        vAux.add(bean.getVValPrecioLista_1());
        vAux.add(bean.getVValPrecPublico());
        vAux.add(bean.getVIndOrigenProd());
        vAux.add(bean.getVIndProdPromocion());
        vAux.add(bean.getVPctDcto_2());
        vAux.add(bean.getVIndTratamiento());
        vAux.add(bean.getVCtdxDia());
        vAux.add(bean.getVCtdDias());
        vAux.add(bean.getVCodCupon());
        vAux.add(bean.getVSecRespaldoStk());
        vAux.add(bean.getVValorMultiplicacion());
        vAux.add(bean.getVTipoProducto());
        vAux.add(bean.getVValPrecioConvenio());
        vAux.add(bean.getVValPrecioLista_2());
        return vAux;
    }
    
    
    // este metodo no soporta aun para grabar productos de venta promocion PACKS
    public static void saveDetalleVenta(String secrespaldo,BeanDetalleVenta bean) throws SQLException {
        if(!bean.isPIndCampAcumula()){
            ArrayList parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodLocal);
            parametros.add(VariablesVentas.vNum_Ped_Vta);
            parametros.add(VariablesVentas.vSec_Ped_Vta_Det);
            if(bean.isPIndCampAcumula())
               parametros.add(bean.getPCodProdRegalo());
            else    
               parametros.add(bean.getVCodProd());
            parametros.add(new Integer(bean.getVCtdVta()));
            parametros.add(new Double(bean.getVPrecioVta().replace(",", "")));
            parametros.add(new Double(bean.getVSubTotal().replace(",", "")));
            parametros.add(new Double(bean.getVPctDcto().replace(",", "")));
            // SOLO CUANDO ES UNA PROMOCION
            if (bean.getVPctDcto_2().trim().length() != 0)
                parametros.add(new Double(bean.getVPctDcto_2()));
            else
                parametros.add(new Double(0)); //porc dcto 2
            parametros.add(new Double(0)); //porc dcto 3
            parametros.add(new Double(bean.getVPctDcto()));
            parametros.add(VariablesVentas.vEst_Ped_Vta_Det); //estado de detalle
            parametros.add(new Double(String.valueOf(FarmaUtility.getDecimalNumber(bean.getVValBono()))));
            parametros.add(new Integer(bean.getVValFracVta()));
            parametros.add(""); //secuncia de comprobante de pago
            parametros.add(VariablesVentas.vSec_Usu_Local);
            parametros.add(new Double(bean.getVPrecioVta().replace(",","")));
            parametros.add(new Double(bean.getVPctIgv()));
            parametros.add(bean.getVUnidVta());
            parametros.add(bean.getVNumTelefonoRecarga());
            parametros.add(FarmaVariables.vIdUsu);
            if(bean.getVValPrecPublico()==null)
                parametros.add(new Double(bean.getVValPrecPublico().replace(",","")));
            else
                parametros.add(new Double(bean.getVPrecioVta().replace(",","")));
            parametros.add("");// codigo de promocion de detalle 
            parametros.add( bean.getVIndOrigenProd());
            parametros.add(bean.getVCtdxDia());
            parametros.add(bean.getVCtdDias());
            parametros.add(new Double(FarmaUtility.getDecimalNumber(""))); //JCHAVEZ 20102009
            parametros.add(secrespaldo.trim());
            parametros.add(new Integer(bean.getVValorMultiplicacion())); //ASOSA - 08/08/2014
            parametros.add(VariablesVentas.vDniRimac);   //ASOSA - 07/01/2015 - RIMAC
            String codProdInscritoLealtad = ""; //32
            String indInscritoLealtad = "";//33
            
            //fue remplazado 2017.03.21 
            /*  if (VariablesConvenioBTLMF.vCodConvenio.equalsIgnoreCase("")){
                if(UtilityPuntos.isActivoFuncionalidad()){
                    BeanTarjeta tarjetaPuntos = VariablesPuntos.frmPuntos.getTarjetaBean();
                    if(tarjetaPuntos!=null){
                        FacadeLealtad facade = new FacadeLealtad();
                        if(tarjetaPuntos.getListaInscritos()!=null){
                            String codProdEquivalente = facade.verificaInscripcionProducto(tarjetaPuntos.getListaInscritos(), VariablesVentas.vCod_Prod);
                            if(codProdEquivalente!=null && codProdEquivalente.length()>0){
                                codProdInscritoLealtad = codProdEquivalente;
                                indInscritoLealtad = "S";
                            }
                        }
                    }
                }
            }else{
                log.info("PROGRAMA DE PUNTOS: venta convenio no participa en x+1");
            }*/
            //LTAVARA 2017.03.21 
            if(UtilityPuntos.isActivoFuncionalidad()){
            
              BeanTarjeta tarjetaPuntos = null;
              //LTAVARA VALIDA 2017.04.03
              if(VariablesPuntos.frmPuntos!= null){
                tarjetaPuntos =VariablesPuntos.frmPuntos.getBeanTarjeta();
                  }
              
              if(tarjetaPuntos!=null && tarjetaPuntos.getListaAuxiliarInscritos()!=null){
                  boolean detPago = true;
                  //ERIOS 28.03.2016 No acumula en convenio
                  if(!VariablesConvenioBTLMF.vCodConvenio.equalsIgnoreCase("") &&
                     "N".equals(DBPuntos.getPagoConvenio(VariablesVentas.vNum_Ped_Vta))){
                       detPago = false;  
                  }
                  //Ni comprobante manual
                 /* SE VALIDO EN EL BD PTOVTA_RESPALDO_STK.PVTA_P_GRAB_PED_VTA_DET
                  * if(pIngresoComprobanteManual){
                      detPago = false;
                  }*/
                  if(detPago){
                      FacadeLealtad facadeLealtad = new FacadeLealtad();
                      String codProdEquivalente = facadeLealtad.verificaInscripcionProducto(tarjetaPuntos.getListaAuxiliarInscritos(), 
                                                                                            VariablesVentas.vCod_Prod);
                      if(codProdEquivalente!=null && codProdEquivalente.length()>0){
                          codProdInscritoLealtad = codProdEquivalente;
                          indInscritoLealtad = "S";
                      }
                  }
              }
            }
            parametros.add(indInscritoLealtad);
            parametros.add(codProdInscritoLealtad);
            
            // GRABAR DATOS DE LA RECETA //
            // DUBILLUZ 2016.09.05
            if(VariablesVentas.vCodCia_Receta.trim().length()>0&&
            VariablesVentas.vCodLocal_Receta.trim().length()>0&&
               VariablesVentas.vNumPedido_Receta.trim().length()>0){
                    
                   boolean pPermite = false;
                   String vCant_Ingresada_Receta = "";
                   for(int i=0;i<VariablesVentas.vDetalleReceta.size();i++){
                       if(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, i, 0).trim().equalsIgnoreCase(VariablesVentas.vCod_Prod)){
                           pPermite = true;
                           if((new Integer(VariablesVentas.vCant_Ingresada.trim()))<=
                               (new Integer(FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, i, 1).trim()))
                               )
                               vCant_Ingresada_Receta = VariablesVentas.vCant_Ingresada.trim();
                           else
                               vCant_Ingresada_Receta = FarmaUtility.getValueFieldArrayList(VariablesVentas.vDetalleReceta, i, 1).trim();
                                   
                           break;
                       }
                   }               
                   
                   if(pPermite){
                       parametros.add(VariablesVentas.vCodCia_Receta);
                       parametros.add(VariablesVentas.vCodLocal_Receta);
                       parametros.add(VariablesVentas.vNumPedido_Receta);
                       parametros.add(new Integer(vCant_Ingresada_Receta.trim()));
                       parametros.add(new Integer(VariablesVentas.vVal_Frac.trim()));
                       parametros.add(VariablesVentas.vUnid_Vta);                   
                   }
                   else{
                       parametros.add("");
                       parametros.add("");
                       parametros.add("");
                       parametros.add(0);
                       parametros.add(0);
                       parametros.add("");
                   }
               }
            else{
                parametros.add("");
                parametros.add("");
                parametros.add("");
                parametros.add(0);
                parametros.add(0);
                parametros.add("");
            }
            
            
            parametros.add(bean.getPCOD_PROD());
            parametros.add(bean.getPDESC_PROD());
            parametros.add(bean.getPUNID_VTA_FRAC());
            parametros.add(bean.getPVAL_FRAC_LOCAL());
            parametros.add(bean.getPPREC_X_FRACC().replace(",",""));
            parametros.add(bean.getPPREC_X_FRACC_CASTIGO().replace(",",""));
            parametros.add(bean.getPDESC_UNID_VTA_SUG());
            parametros.add(bean.getPVAL_FRAC_VTA_SUG());
            parametros.add(bean.getPPREC_X_SUG_BLIST().replace(",",""));
            parametros.add(bean.getPDESC_UNID_PRESENT());
            parametros.add(bean.getPVAL_PREC_VTA_ENTERO().replace(",",""));
            parametros.add(bean.getPCTD_ENT_VTA());
            parametros.add(bean.getPCTD_SUG_VTA());
            parametros.add(bean.getPCTD_FRAC_VTA());
            parametros.add(bean.getPSUB_TOTAL_CORRECTO().replace(",",""));
            parametros.add(bean.getPSUB_TOTAL_IRREAL().replace(",",""));
            parametros.add(bean.getPAHORRO_REDONDEO().replace(",",""));
            
            //////////////////////////////////////////////////////////
            if (VariablesConvenioBTLMF.vCodConvenio.equalsIgnoreCase(""))
                parametros.add(bean.getPPREC_BASE().replace(",",""));
            else
                parametros.add((bean.getVPrecioVta().replace(",","")));
            //////////////////////////////////////////////////////////
            
            parametros.add(bean.getPPORC_IGV());
            parametros.add(bean.getPVALOR_IGV());
            parametros.add(bean.getPPREC_VTA_UNIT_NVO());
            
            /////////////////////////////////////////////////
            parametros.add(bean.getVCant_Ingresada_ItemQuote());
            
            // FarmaUtility.showMessage(new JDialog(), "FarmaUtility.getValueFieldArrayList(vLista,i,31) "+ bean.getVCant_Ingresada_ItemQuote(),null);
            /////////////////////////////////////////////////
            // DUBILLUZ 2016.09.05
            // GRABAR DATOS DE LA RECETA //
            // GRABA DATOS DE ACUMULA X+1
            if(bean.isPIndCampAcumula()){
                parametros.add("S");
                parametros.add(bean.getVCodProd());
            }
            else{
                parametros.add("N");
                parametros.add(" ");
            }
            
            //INI DMOSQUEIRA 20190710 - Se agregan los valores calculados del cobro al ICBPER
            parametros.add(new Double(FarmaUtility.getDecimalNumber(bean.getVIcbperVal())));
            parametros.add(new Double(FarmaUtility.getDecimalNumber(bean.getVIcbperTotal())));
            
            
            log.info("invocando  a PTOVTA_RESPALDO_STK.PVTA_P_GRAB_PED_VTA_DET(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                     "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                     "):" +
                      parametros);
            FarmaDBUtility.executeSQLStoredProcedure(null,
                                                     "PTOVTA_RESPALDO_STK.PVTA_P_GRAB_PED_VTA_DET(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                                                     "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"+
                                                     ")",
                                                     parametros, false);   
                                                     
            //FIN DMOSQUEIRA 20190710
        }
        else
        log.info("NO GRABA EL ITEM de regalo en este lado se grabara despues "+bean.getPCodCampAcumula());
    }
    
    public static boolean isProdCampAcumula(String pCadena){
        for(int i=0;i<vListaDetalleVenta.size();i++){
            BeanDetalleVenta bean = vListaDetalleVenta.get(i);
            if(bean.getVCodProd().equalsIgnoreCase(pCadena))
                return bean.isPIndCampAcumula();
        }
        return false;
    }
    
    public static boolean isPermiteAccionResumen(String pCadena){
        if(isProdCampAcumula(pCadena))
            return false;
        else
            return true;
    }
}
