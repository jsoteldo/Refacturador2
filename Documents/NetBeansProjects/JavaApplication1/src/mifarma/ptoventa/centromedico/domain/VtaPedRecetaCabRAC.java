package mifarma.ptoventa.centromedico.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author ERIOS
 * @since 02.09.2016
 */
 @XmlRootElement(name = "receta")
 @XmlAccessorType(XmlAccessType.NONE)
public class VtaPedRecetaCabRAC extends VtaPedRecetaCab{
    String dets = "";
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_local){setCod_local(cod_local);}
    public void set3(String num_ped_rec){setNum_ped_rec(num_ped_rec);}
    public void set4(String cdg_apm){setCdg_apm(cdg_apm);}
    public void set5(String matricula){setMatricula(matricula);}
    public void set6(Date fec_ped_rec){setFec_ped_rec(fec_ped_rec);}
    public void set7(double val_bruto_ped_rec){setVal_bruto_ped_rec(val_bruto_ped_rec);}
    public void set8(double val_neto_ped_rec){setVal_neto_ped_rec(val_neto_ped_rec);}
    public void set9(double val_redondeo_ped_rec){setVal_redondeo_ped_rec(val_redondeo_ped_rec);}
    public void set10(double val_igv_ped_rec){setVal_igv_ped_rec(val_igv_ped_rec);}
    public void set11(double val_dcto_ped_rec){setVal_dcto_ped_rec(val_dcto_ped_rec);}
    public void set12(double val_tip_cambio_ped_rec){setVal_tip_cambio_ped_rec(val_tip_cambio_ped_rec);}
    public void set13(int cant_items_ped_rec){setCant_items_ped_rec(cant_items_ped_rec);}
    public void set14(String est_ped_rec){setEst_ped_rec(est_ped_rec);}
    public void set15(String usu_crea_ped_rec_cab){setUsu_crea_ped_rec_cab(usu_crea_ped_rec_cab);}
    public void set16(Date fec_crea_ped_rec_cab){setFec_crea_ped_rec_cab(fec_crea_ped_rec_cab);}
    public void set17(String usu_mod_ped_rec_cab){setUsu_mod_ped_rec_cab(usu_mod_ped_rec_cab);}
    public void set18(Date fec_mod_ped_rec_cab){setFec_mod_ped_rec_cab(fec_mod_ped_rec_cab);}
    public void set19(Date fecha_vencimiento){setFecha_vencimiento(fecha_vencimiento);}
    public void set20(String num_cmp){setNum_cmp(num_cmp);}
    public void set21(String des_nom_medico){setDes_nom_medico(des_nom_medico);}
    public void set22(String des_ape_medico){setDes_ape_medico(des_ape_medico);}
    public void set23(String cod_medico){setCod_medico(cod_medico);}

    public void setDets(String dets) {
        this.dets = dets;
    }

    public String getDets() {
        return dets;
    }
    
    @XmlElement(name = "det")
    private List<VtaPedRecetaDetRAC> list;

    public void setList(List<VtaPedRecetaDetRAC> list) {
        this.list = list;
    }

    /*public List<VtaPedRecetaDetRAC> getList() {
        return list;
    }*/
}
