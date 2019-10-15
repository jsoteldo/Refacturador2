package mifarma.ptoventa.centromedico.domain;

import java.util.Date;


/**
 * @author ERIOS
 * @since 02.09.2016
 */
public class VtaPedRecetaDet {
    
    private String cod_grupo_cia = "";
    private String cod_local = "";
    private String num_ped_rec = "";
    private int sec_ped_rec_det;
    private String cod_prod = "";
    private int cant_atendida;
    private double val_prec_rec;
    private double val_prec_total;
    private double porc_dcto_1;
    private double porc_dcto_2;
    private double porc_dcto_3;
    private double porc_dcto_total;
    private String est_ped_rec_det = "";
    private int val_frac;
    private String usu_crea_ped_rec_det = "";
    private Date fec_crea_ped_rec_det;
    private String usu_mod_ped_rec_det = "";
    private Date fec_mod_ped_rec_det;
    private double val_prec_lista;
    private double val_igv;
    private String unid_vta = "";
    private String ind_exonerado_igv = "";
    private int stk_fisico_disp;        
    private int frecuencia_toma;
    private int duracion_toma;
    private int cod_maes_det;
    private String dosis_toma = "";
    
    public VtaPedRecetaDet() {
        super();
    }
    
    public void setCod_grupo_cia(String cod_grupo_cia) {
        this.cod_grupo_cia = cod_grupo_cia;
    }
    
    public String getCod_grupo_cia() {
        return cod_grupo_cia;
    }
    
    public void setCod_local(String cod_local) {
        this.cod_local = cod_local;
    }
    
    public String getCod_local() {
        return cod_local;
    }
    
    public void setNum_ped_rec(String num_ped_rec) {
        this.num_ped_rec = num_ped_rec;
    }
    
    public String getNum_ped_rec() {
        return num_ped_rec;
    }
    
    public void setSec_ped_rec_det(int sec_ped_rec_det) {
        this.sec_ped_rec_det = sec_ped_rec_det;
    }
    
    public int getSec_ped_rec_det() {
        return sec_ped_rec_det;
    }
    
    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }
    
    public String getCod_prod() {
        return cod_prod;
    }
    
    public void setCant_atendida(int cant_atendida) {
        this.cant_atendida = cant_atendida;
    }
    
    public int getCant_atendida() {
        return cant_atendida;
    }
    
    public void setVal_prec_rec(double val_prec_rec) {
        this.val_prec_rec = val_prec_rec;
    }
    
    public double getVal_prec_rec() {
        return val_prec_rec;
    }
    
    public void setVal_prec_total(double val_prec_total) {
        this.val_prec_total = val_prec_total;
    }
    
    public double getVal_prec_total() {
        return val_prec_total;
    }
    
    public void setPorc_dcto_1(double porc_dcto_1) {
        this.porc_dcto_1 = porc_dcto_1;
    }
    
    public double getPorc_dcto_1() {
        return porc_dcto_1;
    }
    
    public void setPorc_dcto_2(double porc_dcto_2) {
        this.porc_dcto_2 = porc_dcto_2;
    }
    
    public double getPorc_dcto_2() {
        return porc_dcto_2;
    }
    
    public void setPorc_dcto_3(double porc_dcto_3) {
        this.porc_dcto_3 = porc_dcto_3;
    }
    
    public double getPorc_dcto_3() {
        return porc_dcto_3;
    }
    
    public void setPorc_dcto_total(double porc_dcto_total) {
        this.porc_dcto_total = porc_dcto_total;
    }
    
    public double getPorc_dcto_total() {
        return porc_dcto_total;
    }
    
    public void setEst_ped_rec_det(String est_ped_rec_det) {
        this.est_ped_rec_det = est_ped_rec_det;
    }
    
    public String getEst_ped_rec_det() {
        return est_ped_rec_det;
    }
    
    public void setVal_frac(int val_frac) {
        this.val_frac = val_frac;
    }
    
    public int getVal_frac() {
        return val_frac;
    }
    
    public void setUsu_crea_ped_rec_det(String usu_crea_ped_rec_det) {
        this.usu_crea_ped_rec_det = usu_crea_ped_rec_det;
    }
    
    public String getUsu_crea_ped_rec_det() {
        return usu_crea_ped_rec_det;
    }
    
    public void setFec_crea_ped_rec_det(Date fec_crea_ped_rec_det) {
        this.fec_crea_ped_rec_det = fec_crea_ped_rec_det;
    }
    
    public Date getFec_crea_ped_rec_det() {
        return fec_crea_ped_rec_det;
    }
    
    public void setUsu_mod_ped_rec_det(String usu_mod_ped_rec_det) {
        this.usu_mod_ped_rec_det = usu_mod_ped_rec_det;
    }
    
    public String getUsu_mod_ped_rec_det() {
        return usu_mod_ped_rec_det;
    }
    
    public void setFec_mod_ped_rec_det(Date fec_mod_ped_rec_det) {
        this.fec_mod_ped_rec_det = fec_mod_ped_rec_det;
    }
    
    public Date getFec_mod_ped_rec_det() {
        return fec_mod_ped_rec_det;
    }
    
    public void setVal_prec_lista(double val_prec_lista) {
        this.val_prec_lista = val_prec_lista;
    }
    
    public double getVal_prec_lista() {
        return val_prec_lista;
    }
    
    public void setVal_igv(double val_igv) {
        this.val_igv = val_igv;
    }
    
    public double getVal_igv() {
        return val_igv;
    }
    
    public void setUnid_vta(String unid_vta) {
        this.unid_vta = unid_vta;
    }
    
    public String getUnid_vta() {
        return unid_vta;
    }
    
    public void setInd_exonerado_igv(String ind_exonerado_igv) {
        this.ind_exonerado_igv = ind_exonerado_igv;
    }
    
    public String getInd_exonerado_igv() {
        return ind_exonerado_igv;
    }
    
    public void setStk_fisico_disp(int stk_fisico_disp) {
        this.stk_fisico_disp = stk_fisico_disp;
    }
    
    public int getStk_fisico_disp() {
        return stk_fisico_disp;
    }

    public void setFrecuencia_toma(int frecuencia_toma) {
        this.frecuencia_toma = frecuencia_toma;
    }

    public int getFrecuencia_toma() {
        return frecuencia_toma;
    }

    public void setDuracion_toma(int duracion_toma) {
        this.duracion_toma = duracion_toma;
    }

    public int getDuracion_toma() {
        return duracion_toma;
    }

    public void setCod_maes_det(int cod_maes_det) {
        this.cod_maes_det = cod_maes_det;
    }

    public int getCod_maes_det() {
        return cod_maes_det;
    }

    public void setDosis_toma(String dosis_toma) {
        this.dosis_toma = dosis_toma;
    }

    public String getDosis_toma() {
        return dosis_toma;
    }
}
