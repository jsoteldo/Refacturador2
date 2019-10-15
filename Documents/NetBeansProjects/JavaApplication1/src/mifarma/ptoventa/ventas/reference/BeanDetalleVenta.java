package mifarma.ptoventa.ventas.reference;

import java.math.RoundingMode;

import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.Map;

import mifarma.common.FarmaUtility;

public class BeanDetalleVenta {
    private String vCodProd="";
    private String vDescProd="";
    private String vUnidVta="";
    private String vPrecioBase="";
    private String vCtdVta="";
    private String vPctDcto="";
    private String vPrecioVta="";
    private String vSubTotal="";
    private String vValBono="";
    private String vNomLab="";
    private String vValFracVta="";
    private String vPctIgv="";
    private String vValIgv="";
    private String vNumTelefonoRecarga="";
    private String vIndProdVirtual="";
    private String vTipoProdVirtual="";
    private String vIndControlaStock="";
    private String vValPrecioLista_1="";
    private String vValPrecPublico="";
    private String vIndOrigenProd="";
    private String vIndProdPromocion="";
    private String vPctDcto_2="";
    private String vIndTratamiento="";
    private String vCtdxDia="";
    private String vCtdDias="";
    private String vCodCupon="";
    private String vSecRespaldoStk="";
    private String vValorMultiplicacion="";
    private String vTipoProducto="";
    private String vValPrecioConvenio="";
    private String vValPrecioLista_2="";
    
    private String pCOD_PROD = "";
    private String pDESC_PROD = "";
    private String pUNID_VTA_FRAC = "";
    private String pVAL_FRAC_LOCAL = "";
    private String pPREC_X_FRACC = "";
    private String pPREC_X_FRACC_CASTIGO = "";
    private String pDESC_UNID_VTA_SUG = "";
    private String pVAL_FRAC_VTA_SUG = "";
    private String pPREC_X_SUG_BLIST = "";
    private String pDESC_UNID_PRESENT = "";
    private String pVAL_PREC_VTA_ENTERO = "";
    private String pCTD_ENT_VTA = "";
    private String pCTD_SUG_VTA = "";
    private String pCTD_FRAC_VTA = "";
    private String pSUB_TOTAL_CORRECTO = "";
    private String pSUB_TOTAL_IRREAL = "";
    private String pAHORRO_REDONDEO = "";
    private String pPREC_BASE = "";
    private String pPORC_IGV = "";
    private String pVALOR_IGV = "";
    private String pPREC_VTA_UNIT_NVO = "";
    
    private String pahorroPedido = "";
    
    private String vCant_Ingresada_ItemQuote = "";
    
    
    private String pCodProdRegalo = "";
    private String pCodCampAcumula ="";
    private boolean pIndCampAcumula = false;
    private String pCodEQCampAcumula = "";
    
	// Variables para calculo de ICBPER
    private String vIcbperVal = "";  //-------------------- 59  // JHAMRC 10072019
    private String vIcbperTotal = ""; //------------------- 60  // JHAMRC 10072019
    // FIN DMOSQUEIRA 20190710
    
    public void setVCodProd(String vCodProd) {
        this.vCodProd = vCodProd;
    }

    public String getVCodProd() {
        return vCodProd;
    }

    public void setVDescProd(String vDescProd) {
        this.vDescProd = vDescProd;
    }

    public String getVDescProd() {
        return vDescProd;
    }

    public void setVUnidVta(String vUnidVta) {
        this.vUnidVta = vUnidVta;
    }

    public String getVUnidVta() {
        return vUnidVta;
    }

    public void setVPrecioBase(String vPrecioBase) {
        this.vPrecioBase = vPrecioBase;
    }

    public String getVPrecioBase() {
        return vPrecioBase;
    }

    public void setVCtdVta(String vCtdVta) {
        this.vCtdVta = vCtdVta;
    }

    public String getVCtdVta() {
        return vCtdVta;
    }

    public void setVPctDcto(String vPctDcto) {
        this.vPctDcto = vPctDcto;
    }

    public String getVPctDcto() {
        calculoPctDcto();
        return vPctDcto;
    }

    public void setVPrecioVta(String vPrecioVta) {
        this.vPrecioVta = vPrecioVta;
    }

    public String getVPrecioVta() {
        return vPrecioVta;
    }

    public void setVSubTotal(String vSubTotal) {
        this.vSubTotal = vSubTotal;
    }

    public String getVSubTotal() {
        return vSubTotal;
    }

    public void setVValBono(String vValBono) {
        this.vValBono = vValBono;
    }

    public String getVValBono() {
        return vValBono;
    }

    public void setVNomLab(String vNomLab) {
        this.vNomLab = vNomLab;
    }

    public String getVNomLab() {
        return vNomLab;
    }

    public void setVValFracVta(String vValFracVta) {
        this.vValFracVta = vValFracVta;
    }

    public String getVValFracVta() {
        return vValFracVta;
    }

    public void setVPctIgv(String vPctIgv) {
        this.vPctIgv = vPctIgv;
    }

    public String getVPctIgv() {
        return vPctIgv;
    }

    public void setVValIgv(String vValIgv) {
        this.vValIgv = vValIgv;
    }

    public String getVValIgv() {
        return vValIgv;
    }

    public void setVNumTelefonoRecarga(String vNumTelefonoRecarga) {
        this.vNumTelefonoRecarga = vNumTelefonoRecarga;
    }

    public String getVNumTelefonoRecarga() {
        return vNumTelefonoRecarga;
    }

    public void setVIndProdVirtual(String vIndProdVirtual) {
        this.vIndProdVirtual = vIndProdVirtual;
    }

    public String getVIndProdVirtual() {
        return vIndProdVirtual;
    }

    public void setVTipoProdVirtual(String vTipoProdVirtual) {
        this.vTipoProdVirtual = vTipoProdVirtual;
    }

    public String getVTipoProdVirtual() {
        return vTipoProdVirtual;
    }

    public void setVIndControlaStock(String vIndControlaStock) {
        this.vIndControlaStock = vIndControlaStock;
    }

    public String getVIndControlaStock() {
        return vIndControlaStock;
    }

    public void setVValPrecioLista_1(String vValPrecioLista_1) {
        this.vValPrecioLista_1 = vValPrecioLista_1;
    }

    public String getVValPrecioLista_1() {
        return vValPrecioLista_1;
    }

    public void setVValPrecPublico(String vValPrecPublico) {
        this.vValPrecPublico = vValPrecPublico;
    }

    public String getVValPrecPublico() {
        return vValPrecPublico;
    }

    public void setVIndOrigenProd(String vIndOrigenProd) {
        this.vIndOrigenProd = vIndOrigenProd;
    }

    public String getVIndOrigenProd() {
        return vIndOrigenProd;
    }

    public void setVIndProdPromocion(String vIndProdPromocion) {
        this.vIndProdPromocion = vIndProdPromocion;
    }

    public String getVIndProdPromocion() {
        return vIndProdPromocion;
    }

    public void setVPctDcto_2(String vPctDcto_2) {
        this.vPctDcto_2 = vPctDcto_2;
    }

    public String getVPctDcto_2() {
        return vPctDcto_2;
    }

    public void setVIndTratamiento(String vIndTratamiento) {
        this.vIndTratamiento = vIndTratamiento;
    }

    public String getVIndTratamiento() {
        return vIndTratamiento;
    }

    public void setVCtdxDia(String vCtdxDia) {
        this.vCtdxDia = vCtdxDia;
    }

    public String getVCtdxDia() {
        return vCtdxDia;
    }

    public void setVCtdDias(String vCtdDias) {
        this.vCtdDias = vCtdDias;
    }

    public String getVCtdDias() {
        return vCtdDias;
    }

    public void setVCodCupon(String vCodCupon) {
        this.vCodCupon = vCodCupon;
    }

    public String getVCodCupon() {
        return vCodCupon;
    }

    public void setVSecRespaldoStk(String vSecRespaldoStk) {
        this.vSecRespaldoStk = vSecRespaldoStk;
    }

    public String getVSecRespaldoStk() {
        return vSecRespaldoStk;
    }

    public void setVValorMultiplicacion(String vValorMultiplicacion) {
        this.vValorMultiplicacion = vValorMultiplicacion;
    }

    public String getVValorMultiplicacion() {
        return vValorMultiplicacion;
    }

    public void setVTipoProducto(String vTipoProducto) {
        this.vTipoProducto = vTipoProducto;
    }

    public String getVTipoProducto() {
        return vTipoProducto;
    }

    public void setVValPrecioConvenio(String vValPrecioConvenio) {
        if(vValPrecioConvenio.trim().length()>0){
            
            double input = Double.parseDouble(vValPrecioConvenio.replace(",",""));
            DecimalFormat df2 = new DecimalFormat("#.#");
            df2.setRoundingMode(RoundingMode.UP);
            this.vValPrecioConvenio = (df2.format(input)).replace(",",".").trim();
                //FarmaUtility.formatNumber(Double.parseDouble(vValPrecioConvenio.replace(",","")), 1);        
        }
            
        else
            this.vValPrecioConvenio = vValPrecioConvenio;
    }

    public String getVValPrecioConvenio() {
        return vValPrecioConvenio;
    }

    public void setVValPrecioLista_2(String vValPrecioLista_2) {
        this.vValPrecioLista_2 = vValPrecioLista_2;
    }

    public String getVValPrecioLista_2() {
        return vValPrecioLista_2;
    }

    public void setPCOD_PROD(String pCOD_PROD) {
        this.pCOD_PROD = pCOD_PROD;
    }

    public String getPCOD_PROD() {
        return pCOD_PROD;
    }

    public void setPDESC_PROD(String pDESC_PROD) {
        this.pDESC_PROD = pDESC_PROD;
    }

    public String getPDESC_PROD() {
        return pDESC_PROD;
    }

    public void setPUNID_VTA_FRAC(String pUNID_VTA_FRAC) {
        this.pUNID_VTA_FRAC = pUNID_VTA_FRAC;
    }

    public String getPUNID_VTA_FRAC() {
        return pUNID_VTA_FRAC;
    }

    public void setPVAL_FRAC_LOCAL(String pVAL_FRAC_LOCAL) {
        this.pVAL_FRAC_LOCAL = pVAL_FRAC_LOCAL;
    }

    public String getPVAL_FRAC_LOCAL() {
        return pVAL_FRAC_LOCAL;
    }

    public void setPPREC_X_FRACC(String pPREC_X_FRACC) {
        this.pPREC_X_FRACC = pPREC_X_FRACC;
    }

    public String getPPREC_X_FRACC() {
        return pPREC_X_FRACC;
    }

    public void setPPREC_X_FRACC_CASTIGO(String pPREC_X_FRACC_CASTIGO) {
        this.pPREC_X_FRACC_CASTIGO = pPREC_X_FRACC_CASTIGO;
    }

    public String getPPREC_X_FRACC_CASTIGO() {
        return pPREC_X_FRACC_CASTIGO;
    }

    public void setPDESC_UNID_VTA_SUG(String pDESC_UNID_VTA_SUG) {
        this.pDESC_UNID_VTA_SUG = pDESC_UNID_VTA_SUG;
    }

    public String getPDESC_UNID_VTA_SUG() {
        return pDESC_UNID_VTA_SUG;
    }

    public void setPVAL_FRAC_VTA_SUG(String pVAL_FRAC_VTA_SUG) {
        this.pVAL_FRAC_VTA_SUG = pVAL_FRAC_VTA_SUG;
    }

    public String getPVAL_FRAC_VTA_SUG() {
        return pVAL_FRAC_VTA_SUG;
    }

    public void setPPREC_X_SUG_BLIST(String pPREC_X_SUG_BLIST) {
        this.pPREC_X_SUG_BLIST = pPREC_X_SUG_BLIST;
    }

    public String getPPREC_X_SUG_BLIST() {
        return pPREC_X_SUG_BLIST;
    }

    public void setPDESC_UNID_PRESENT(String pDESC_UNID_PRESENT) {
        this.pDESC_UNID_PRESENT = pDESC_UNID_PRESENT;
    }

    public String getPDESC_UNID_PRESENT() {
        return pDESC_UNID_PRESENT;
    }

    public void setPVAL_PREC_VTA_ENTERO(String pVAL_PREC_VTA_ENTERO) {
        this.pVAL_PREC_VTA_ENTERO = pVAL_PREC_VTA_ENTERO;
    }

    public String getPVAL_PREC_VTA_ENTERO() {
        return pVAL_PREC_VTA_ENTERO;
    }

    public void setPCTD_ENT_VTA(String pCTD_ENT_VTA) {
        this.pCTD_ENT_VTA = pCTD_ENT_VTA;
    }

    public String getPCTD_ENT_VTA() {
        return pCTD_ENT_VTA;
    }

    public void setPCTD_SUG_VTA(String pCTD_SUG_VTA) {
        this.pCTD_SUG_VTA = pCTD_SUG_VTA;
    }

    public String getPCTD_SUG_VTA() {
        return pCTD_SUG_VTA;
    }

    public void setPCTD_FRAC_VTA(String pCTD_FRAC_VTA) {
        this.pCTD_FRAC_VTA = pCTD_FRAC_VTA;
    }

    public String getPCTD_FRAC_VTA() {
        return pCTD_FRAC_VTA;
    }

    public void setPSUB_TOTAL_CORRECTO(String pSUB_TOTAL_CORRECTO) {
        this.pSUB_TOTAL_CORRECTO = pSUB_TOTAL_CORRECTO;
    }

    public String getPSUB_TOTAL_CORRECTO() {
        return pSUB_TOTAL_CORRECTO;
    }

    public void setPSUB_TOTAL_IRREAL(String pSUB_TOTAL_IRREAL) {
        this.pSUB_TOTAL_IRREAL = pSUB_TOTAL_IRREAL;
    }

    public String getPSUB_TOTAL_IRREAL() {
        return pSUB_TOTAL_IRREAL;
    }

    public void setPAHORRO_REDONDEO(String pAHORRO_REDONDEO) {
        this.pAHORRO_REDONDEO = pAHORRO_REDONDEO;
    }

    public String getPAHORRO_REDONDEO() {
        return pAHORRO_REDONDEO;
    }

    public void setPPREC_BASE(String pPREC_BASE) {
        this.pPREC_BASE = pPREC_BASE;
    }

    public String getPPREC_BASE() {
        return pPREC_BASE;
    }

    public void setPPORC_IGV(String pPORC_IGV) {
        this.pPORC_IGV = pPORC_IGV;
    }

    public String getPPORC_IGV() {
        return pPORC_IGV;
    }

    public void setPVALOR_IGV(String pVALOR_IGV) {
        this.pVALOR_IGV = pVALOR_IGV;
    }

    public String getPVALOR_IGV() {
        return pVALOR_IGV;
    }

    public void setPPREC_VTA_UNIT_NVO(String pPREC_VTA_UNIT_NVO) {
        this.pPREC_VTA_UNIT_NVO = pPREC_VTA_UNIT_NVO;
    }

    public String getPPREC_VTA_UNIT_NVO() {
        return pPREC_VTA_UNIT_NVO;
    }

    public void calculoPctDcto() {
            
        if(isPIndCampAcumula()){
            setVPrecioVta("0.0");
            setVSubTotal("0.0");
            double pBase = 0.0;
            pBase = Double.parseDouble(getVPrecioBase().replace(",", ""));
            double pPVenta = Double.parseDouble(getVPrecioVta().replace(",", ""));
            double pCtd = Double.parseDouble(getVCtdVta().replace(",", ""));
            double ahorro = pCtd *(pPVenta);
            setVPctDcto("100");
            setPahorroPedido(FarmaUtility.formatNumber((ahorro), 2));
        }
        else{
            double pBase = 0.0;
            pBase = Double.parseDouble(getVPrecioBase().replace(",", ""));
            double pPVenta = Double.parseDouble(getVPrecioVta().replace(",", ""));
            double pCtd = Double.parseDouble(getVCtdVta().replace(",", ""));
            double ahorro = pCtd * (pBase - pPVenta);
            if(ahorro>0){
                setVPctDcto(FarmaUtility.formatNumber((ahorro * 100 / (pBase * pCtd)), 2));
                setPahorroPedido(FarmaUtility.formatNumber((ahorro), 2));
            }
            else
                setVPctDcto(FarmaUtility.formatNumber(0, 2));
        }   
    }

    public void setPahorroPedido(String pahorroPedido) {
        this.pahorroPedido = pahorroPedido;
    }

    public String getPahorroPedido() {
        return pahorroPedido;
    }

    public void setVCant_Ingresada_ItemQuote(String vCant_Ingresada_ItemQuote) {
        this.vCant_Ingresada_ItemQuote = vCant_Ingresada_ItemQuote;
    }

    public String getVCant_Ingresada_ItemQuote() {
        return vCant_Ingresada_ItemQuote;
    }


    public void setVValPrecioConvenio1(String vValPrecioConvenio) {
        this.vValPrecioConvenio = vValPrecioConvenio;
    }

    public void setPCodProdRegalo(String pCodProdRegalo) {
        this.pCodProdRegalo = pCodProdRegalo;
    }

    public String getPCodProdRegalo() {
        return pCodProdRegalo;
    }

    public void setPCodCampAcumula(String pCodCampAcumula) {
        this.pCodCampAcumula = pCodCampAcumula;
    }

    public String getPCodCampAcumula() {
        return pCodCampAcumula;
    }

    public void setPIndCampAcumula(boolean pIndCampAcumula) {
        this.pIndCampAcumula = pIndCampAcumula;
    }

    public boolean isPIndCampAcumula() {
        return pIndCampAcumula;
    }

    public void setPCodEQCampAcumula(String pCodEQCampAcumula) {
        this.pCodEQCampAcumula = pCodEQCampAcumula;
    }

    public String getPCodEQCampAcumula() {
        return pCodEQCampAcumula;
    }
    
    // INI: JHAMRC 10072019
    public String getVIcbperVal() {
        return vIcbperVal;
    }

    public void setVIcbperVal(String vIcbperVal) {
        this.vIcbperVal = vIcbperVal;
    }
    
    public String getVIcbperTotal() {
        return vIcbperTotal;
    }

    public void setVIcbperTotal(String vIcbperTotal) {
        this.vIcbperTotal = vIcbperTotal;
    }
    // FIN: JHAMRC 10072019
}
