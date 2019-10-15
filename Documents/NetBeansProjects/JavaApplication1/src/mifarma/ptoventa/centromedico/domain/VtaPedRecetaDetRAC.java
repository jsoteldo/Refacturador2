package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;


/**
 * @author ERIOS
 * @since 02.09.2016
 */
public class VtaPedRecetaDetRAC extends VtaPedRecetaDet{
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_local){setCod_local(cod_local);}
    public void set3(String num_ped_rec){setNum_ped_rec(num_ped_rec);}
    public void set4(int sec_ped_rec_det){setSec_ped_rec_det(sec_ped_rec_det);}
    public void set5(String cod_prod){setCod_prod(cod_prod);}
    public void set6(int cant_atendida){setCant_atendida(cant_atendida);}
    public void set7(double val_prec_rec){setVal_prec_rec(val_prec_rec);}
    public void set8(double val_prec_total){setVal_prec_total(val_prec_total);}
    public void set9(double porc_dcto_1){setPorc_dcto_1(porc_dcto_1);}
    public void set10(double porc_dcto_2){setPorc_dcto_2(porc_dcto_2);}
    public void set11(double porc_dcto_3){setPorc_dcto_3(porc_dcto_3);}
    public void set12(double porc_dcto_total){setPorc_dcto_total(porc_dcto_total);}
    public void set13(String est_ped_rec_det){setEst_ped_rec_det(est_ped_rec_det);}
    public void set14(int val_frac){setVal_frac(val_frac);}
    public void set15(String usu_crea_ped_rec_det){setUsu_crea_ped_rec_det(usu_crea_ped_rec_det);}
    public void set16(Date fec_crea_ped_rec_det){setFec_crea_ped_rec_det(fec_crea_ped_rec_det);}
    public void set17(String usu_mod_ped_rec_det){setUsu_mod_ped_rec_det(usu_mod_ped_rec_det);}
    public void set18(Date fec_mod_ped_rec_det){setFec_mod_ped_rec_det(fec_mod_ped_rec_det);}
    public void set19(double val_prec_lista){setVal_prec_lista(val_prec_lista);}
    public void set20(double val_igv){setVal_igv(val_igv);}
    public void set21(String unid_vta){setUnid_vta(unid_vta);}
    public void set22(String ind_exonerado_igv){setInd_exonerado_igv(ind_exonerado_igv);}
    public void set23(int stk_fisico_disp){setStk_fisico_disp(stk_fisico_disp);}
    public void set24(int frecuencia_toma){setFrecuencia_toma(frecuencia_toma);}
    public void set25(int duracion_toma){setDuracion_toma(duracion_toma);}
    public void set26(int cod_maes_det){setCod_maes_det(cod_maes_det);}
    public void set27(String dosis_toma){setDosis_toma(dosis_toma);}
    
    @XmlElement(name = "c01")
    public String getCod_grupo_cia() {
        return super.getCod_grupo_cia();
    }
    @XmlElement(name = "c02")
    public String getCod_local() {
        return super.getCod_local();
    }
    
    @XmlElement(name = "c03")
    public String getNum_ped_rec() {
        return super.getNum_ped_rec();
    }
    
    @XmlElement(name = "n04")
    public int getSec_ped_rec_det() {
        return super.getSec_ped_rec_det();
    }
    
    @XmlElement(name = "c05")
    public String getCod_prod() {
        return super.getCod_prod();
    }
    
    @XmlElement(name = "n06")
    public int getCant_atendida() {
        return super.getCant_atendida();
    }
    
    @XmlElement(name = "n07")
    public double getVal_prec_rec() {
        return super.getVal_prec_rec();
    }
    
    @XmlElement(name = "n08")
    public double getVal_prec_total() {
        return super.getVal_prec_total();
    }
    
    @XmlElement(name = "n09")
    public double getPorc_dcto_1() {
        return super.getPorc_dcto_1();
    }
    
    @XmlElement(name = "n10")
    public double getPorc_dcto_2() {
        return super.getPorc_dcto_2();
    }
    
    @XmlElement(name = "n11")
    public double getPorc_dcto_3() {
        return super.getPorc_dcto_3();
    }
    
    @XmlElement(name = "n12")
    public double getPorc_dcto_total() {
        return super.getPorc_dcto_total();
    }
    
    @XmlElement(name = "c13")
    public String getEst_ped_rec_det() {
        return super.getEst_ped_rec_det();
    }
    
    @XmlElement(name = "n14")
    public int getVal_frac() {
        return super.getVal_frac();
    }
    
    @XmlElement(name = "v15")
    public String getUsu_crea_ped_rec_det() {
        return super.getUsu_crea_ped_rec_det();
    }
    
    @XmlElement(name = "d16")
    public Date getFec_crea_ped_rec_det() {
        return super.getFec_crea_ped_rec_det();
    }
    
    @XmlElement(name = "v17")
    public String getUsu_mod_ped_rec_det() {
        return super.getUsu_mod_ped_rec_det();
    }
    
    @XmlElement(name = "d18")
    public Date getFec_mod_ped_rec_det() {
        return super.getFec_mod_ped_rec_det();
    }
    
    @XmlElement(name = "n19")
    public double getVal_prec_lista() {
        return super.getVal_prec_lista();
    }
    
    @XmlElement(name = "n20")
    public double getVal_igv() {
        return super.getVal_igv();
    }
    
    @XmlElement(name = "v21")
    public String getUnid_vta() {
        return super.getUnid_vta();
    }
    
    @XmlElement(name = "c22")
    public String getInd_exonerado_igv() {
        return super.getInd_exonerado_igv();
    }
    
    @XmlElement(name = "n23")
    public int getStk_fisico_disp() {
        return super.getStk_fisico_disp();
    }
    
    @XmlElement(name = "n24")
    public int getFrecuencia_toma() {
        return super.getFrecuencia_toma();
    }

    @XmlElement(name = "n25")
    public int getDuracion_toma() {
        return super.getDuracion_toma();
    }

    @XmlElement(name = "n26")
    public int getCod_maes_det() {
        return super.getCod_maes_det();
    }

    @XmlElement(name = "v27")
    public String getDosis_toma() {
        return super.getDosis_toma();
    }
}
