package mifarma.ptoventa.ventas.reference;

import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaDBUtilityRemoto;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.convenioBTLMF.reference.UtilityConvenioBTLMF;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenio;
import mifarma.ptoventa.convenioBTLMF.reference.VariablesConvenioBTLMF;
import mifarma.ptoventa.delivery.reference.VariablesDelivery;
import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.recetario.reference.VariablesRecetario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBCalculoPrecio {
    private static final Logger log = LoggerFactory.getLogger(DBCalculoPrecio.class);

    private static ArrayList parametros = new ArrayList();
    private static List prmts = new ArrayList();

    public DBCalculoPrecio() {
    }
    
    public static BeanProducto getCalculoPrecioProduco(String pCodProd, 
                                                     String pCtdVta,
                                                     String pValFracVta) throws SQLException {
        ArrayList prmtros = new ArrayList();
        prmtros.add(FarmaVariables.vCodGrupoCia);
        prmtros.add(FarmaVariables.vCodLocal);
        prmtros.add(pCodProd.trim());
        prmtros.add(pCtdVta.trim());
        prmtros.add(pValFracVta.trim());
        log.info("invocando PKG_UTIL_REDONDEO a PKG_UTIL_REDONDEO.FN_GET_CALCULO_PRECIO_PROD(?,?,?,?,?):" + prmtros);
        
        List vListaSinReceta = 
        FarmaDBUtility.executeSQLStoredProcedureListMap("PKG_UTIL_REDONDEO.FN_GET_CALCULO_PRECIO_PROD(?,?,?,?,?)",
                                                                       prmtros);
        Map mapAux = new HashMap(); 
        mapAux = (HashMap)vListaSinReceta.get(0);

        BeanProducto bean = new BeanProducto();
        bean.setVCOD_PROD(((String)mapAux.get("COD_PROD")).trim());
        bean.setVDESC_PROD(((String)mapAux.get("DESC_PROD")).trim());
        bean.setVUNID_VTA_FRAC(((String)mapAux.get("UNID_VTA_FRAC")).trim());
        bean.setVVAL_FRAC_LOCAL(((String)mapAux.get("VAL_FRAC_LOCAL")).trim());
        bean.setVPREC_X_FRACC(((String)mapAux.get("PREC_X_FRACC")).trim());
        bean.setVPREC_X_FRACC_CASTIGO(((String)mapAux.get("PREC_X_FRACC_CASTIGO")).trim());
        bean.setVDESC_UNID_VTA_SUG(((String)mapAux.get("DESC_UNID_VTA_SUG")).trim());
        bean.setVVAL_FRAC_VTA_SUG(((String)mapAux.get("VAL_FRAC_VTA_SUG")).trim());
        bean.setVPREC_X_SUG_BLIST(((String)mapAux.get("PREC_X_SUG_BLIST")).trim());
        bean.setVDESC_UNID_PRESENT(((String)mapAux.get("DESC_UNID_PRESENT")).trim());
        bean.setVVAL_PREC_VTA_ENTERO(((String)mapAux.get("VAL_PREC_VTA_ENTERO")).trim());
        bean.setVCTD_ENT_VTA(((String)mapAux.get("CTD_ENT_VTA")).trim());
        bean.setVCTD_SUG_VTA(((String)mapAux.get("CTD_SUG_VTA")).trim());
        bean.setVCTD_FRAC_VTA(((String)mapAux.get("CTD_FRAC_VTA")).trim());
        bean.setVSUB_TOTAL_CORRECTO(((String)mapAux.get("SUB_TOTAL_CORRECTO")).trim());
        bean.setVSUB_TOTAL_IRREAL(((String)mapAux.get("SUB_TOTAL_IRREAL")).trim());
        bean.setVAHORRO_REDONDEO(((String)mapAux.get("AHORRO_REDONDEO")).trim());
        bean.setVPREC_BASE(((String)mapAux.get("PREC_BASE")).trim());
        bean.setVPORC_IGV(((String)mapAux.get("PORC_IGV")).trim());
        bean.setVVALOR_IGV(((String)mapAux.get("VALOR_IGV")).trim());
        bean.setVPREC_VTA_UNIT_NVO(((String)mapAux.get("PREC_VTA_UNIT_NVO")).trim());
        
        return bean;
    }    
    
    
    public static BeanInfoPrecioProd getInfoPrecioProd(String pCodProd) throws SQLException {
        ArrayList prmtros = new ArrayList();
        prmtros.add(FarmaVariables.vCodGrupoCia);
        prmtros.add(FarmaVariables.vCodLocal);
        prmtros.add(pCodProd.trim());
        log.info("invocando PKG_UTIL_REDONDEO a PKG_UTIL_REDONDEO.FN_GET_INFO_PRECIO_PROD_VER_2(?,?,?):" + prmtros);
        
        List vListaSinReceta = 
        FarmaDBUtility.executeSQLStoredProcedureListMap("PKG_UTIL_REDONDEO.FN_GET_INFO_PRECIO_PROD_VER_2(?,?,?)",
                                                                       prmtros);
        
        if(vListaSinReceta.size()>0){
            Map mapAux = new HashMap(); 
            mapAux = (HashMap)vListaSinReceta.get(0);

            BeanInfoPrecioProd bean = new BeanInfoPrecioProd();
            bean.setVCOD_PROD(pCodProd);
            
            bean.setVMonedero_DATOS_FRACCION(((String)mapAux.get("MON_DATOS_FRACCION")).trim());
            bean.setVMonedero_DATOS_SUGERIDO(((String)mapAux.get("MON_DATOS_SUGERIDO")).trim());
            
            bean.setVMayor_DATOS_FRACCION(((String)mapAux.get("MAY_DATOS_FRACCION")).trim());
            bean.setVMayor_DATOS_SUGERIDO(((String)mapAux.get("MAY_DATOS_SUGERIDO")).trim());
            
            bean.setVDATOS_FRACCION(((String)mapAux.get("DATOS_FRACCION")).trim());
            bean.setVDATOS_SUGERIDO(((String)mapAux.get("DATOS_SUGERIDO")).trim());
            
            
            //bean.setVAHORRO_REDONDEO(((String)mapAux.get("AHORRO_REDONDEO")).trim());
            return bean;
        }
        else
            return null;

    } 
    
    public static BeanInfoPrecioMonedero getDatoPrecioFidelizado(String pCodProd,String vDni) throws SQLException {
        ArrayList prmtros = new ArrayList();
        prmtros.add(FarmaVariables.vCodGrupoCia);
        prmtros.add(FarmaVariables.vCodLocal);
        prmtros.add(pCodProd.trim());
        prmtros.add(vDni.trim());
        //dubilluz 2017.02.15
        if(VariablesFidelizacion.vIsPrimeraCompraFidelizadoNuevo)
            prmtros.add("S");
        else
            prmtros.add("N");
        //dubilluz 2017.02.15
        log.info("invocando PKG_UTIL_REDONDEO a PKG_UTIL_MONEDERO.P_GET_PREC_PROD_LOCAL(?,?,?,?,?):" + prmtros);
        List vListaSinReceta = FarmaDBUtility.executeSQLStoredProcedureListMap("PKG_UTIL_MONEDERO.P_GET_PREC_PROD_LOCAL(?,?,?,?,?)",
                                                                       prmtros);

            prmtros = new ArrayList();
                prmtros.add(FarmaVariables.vCodGrupoCia);
                prmtros.add(FarmaVariables.vCodLocal);
                prmtros.add(pCodProd.trim());
                log.info("invocando PKG_UTIL_REDONDEO a PKG_UTIL_MONEDERO.P_GET_PREC_PROD_LOCAL_NO_FID(?,?,?):" + prmtros);
                List vListaSinFidelizar = FarmaDBUtility.executeSQLStoredProcedureListMap("PKG_UTIL_MONEDERO.P_GET_PREC_PROD_LOCAL_NO_FID(?,?,?)",
                                                                               prmtros);
        
        FarmaUtility.aceptarTransaccion();
        
        
        if(vListaSinReceta.size()>0){
            
            log.info("pCodProd "+pCodProd);
            Map mapAux = new HashMap(); 
            BeanInfoPrecioMonedero bean = new BeanInfoPrecioMonedero();
            bean.setVCOD_PROD(pCodProd);
            
            log.info("precio monedero y mayor edad "+pCodProd);
            for(int i=0;i<vListaSinReceta.size();i++){
                mapAux  = new HashMap(); 
                mapAux = (HashMap)vListaSinReceta.get(i);
                String pIndMayorEdad = ((String)mapAux.get("IND_CAMP_ADULTO_MAYOR"));
                if(pIndMayorEdad.equalsIgnoreCase("N"))
                {   
                    log.info(".. para Monedero");
                    if(mapAux.get("PREC_VTA_FRACC")!=null){
                        bean.setVMonedero_Precio_FRACCION(((String)mapAux.get("PREC_VTA_FRACC")).trim());
                        log.info("..tiene prec monedero fracc "+((String)mapAux.get("PREC_VTA_FRACC")).trim());
                    }
                    else{
                        bean.setVMonedero_Precio_FRACCION("99999");
                        log.info("..NO TIENE prec monedero frac 99999 ");
                    }
                    
                    if(mapAux.get("PREC_VTA_SUGERIDO")!=null){
                        bean.setVMonedero_Precio_SUGERIDO(((String)mapAux.get("PREC_VTA_SUGERIDO")).trim());    
                        log.info("..tiene prec monedero sugerido "+((String)mapAux.get("PREC_VTA_SUGERIDO")).trim());

                    }
                    else{
                        bean.setVMonedero_Precio_SUGERIDO("99999");    
                        log.info("..NO TIENE prec monedero sugerido 99999 ");
                    }
                }
                else
                {   
                    log.info(".. para Mayor de edad ");
                    if(mapAux.get("PREC_VTA_FRACC")!=null){
                        log.info("..tiene prec mayor edad fracc "+((String)mapAux.get("PREC_VTA_FRACC")).trim());
                        bean.setVMayor_Precio_FRACCION(((String)mapAux.get("PREC_VTA_FRACC")).trim());
                    }
                    else{
                        log.info("..tiene prec mayor de edad fracc 99999 ");
                        bean.setVMayor_Precio_FRACCION("99999");
                    }
                      
                    
                    if(mapAux.get("PREC_VTA_SUGERIDO")!=null){
                        log.info("..tiene prec mayor edad sugerido "+((String)mapAux.get("PREC_VTA_SUGERIDO")).trim());
                        bean.setVMayor_Precio_SUGERIDO(((String)mapAux.get("PREC_VTA_SUGERIDO")).trim());  
                    }
                    else{
                        bean.setVMayor_Precio_SUGERIDO("99999");         
                        log.info("..tiene prec mayor edad sugerido 99999");
                    }
                }
            }
            
            if(vListaSinFidelizar.size()>0){
                log.info("..lista de fidelizado ");
                mapAux = new HashMap(); 
                mapAux =  (HashMap)vListaSinFidelizar.get(0);
                
                if(mapAux.get("PREC_VTA_FRACC")!=null){
                    log.info("..valor prec vta fracc  "+((String)mapAux.get("PREC_VTA_FRACC")).trim());
                    bean.setVNoFid_Precio_FRACCION(((String)mapAux.get("PREC_VTA_FRACC")).trim());
                }
                else{
                    log.info("..valor prec vta fracc  NO HAY");
                    bean.setVNoFid_Precio_FRACCION(((String)mapAux.get("PREC_VTA_FRACC")).trim()); 
                }
                
                if(mapAux.get("PREC_VTA_SUGERIDO")!=null){
                    log.info("..valor prec vta sugerido  "+((String)mapAux.get("PREC_VTA_SUGERIDO")).trim());
                    bean.setVNoFid_Precio_SUGERIDO(((String)mapAux.get("PREC_VTA_SUGERIDO")).trim());    
                }
                else{
                    log.info("..valor prec vta fracc  NO HAY");
                    bean.setVNoFid_Precio_FRACCION(((String)mapAux.get("PREC_VTA_FRACC")).trim());
                }
                   
                
            }
            
            return bean;
        }
        else
            return null;
    } 
    
    public static BeanInfoPrecioNormal getInfoPrecioNormal(String pCodProd) throws SQLException {
        ArrayList prmtros = new ArrayList();
        prmtros.add(FarmaVariables.vCodGrupoCia);
        prmtros.add(FarmaVariables.vCodLocal);
        prmtros.add(pCodProd.trim());
        log.info("invocando PKG_UTIL_REDONDEO a PKG_UTIL_REDONDEO.FN_GET_PRECIOS_PROD(?,?,?):" + prmtros);
        
        List vListaSinReceta = 
        FarmaDBUtility.executeSQLStoredProcedureListMap("PKG_UTIL_REDONDEO.FN_GET_PRECIOS_PROD(?,?,?)",
                                                                       prmtros);
        
        if(vListaSinReceta.size()>0){
            Map mapAux = new HashMap(); 
            mapAux = (HashMap)vListaSinReceta.get(0);

            BeanInfoPrecioNormal bean = new BeanInfoPrecioNormal();
            bean.setVCOD_PROD(pCodProd);
            bean.setVPRECIO_NORMAL(((String)mapAux.get("VAL_PREC_NORMAL")).trim());
            bean.setVPRECIO_MONEDERO(((String)mapAux.get("VAL_PREC_MONEDERO")).trim());
            bean.setVPRECIO_MAYOR(((String)mapAux.get("VAL_PREC_MAYOR")).trim());
            
            
            //bean.setVAHORRO_REDONDEO(((String)mapAux.get("AHORRO_REDONDEO")).trim());
            return bean;
        }
        else
            return null;

    }
}
