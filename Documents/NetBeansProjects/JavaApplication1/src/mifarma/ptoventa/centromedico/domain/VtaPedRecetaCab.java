package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * @author ERIOS
 * @since 02.09.2016
 */
 @XmlAccessorType(XmlAccessType.NONE)
public class VtaPedRecetaCab {
    
    private String cod_grupo_cia = "";
    private String cod_local = "";
    private String num_ped_rec = "";
    private String cdg_apm = "";
    private String matricula = "";
    private Date fec_ped_rec;
    private double val_bruto_ped_rec;
    private double val_neto_ped_rec;
    private double val_redondeo_ped_rec;
    private double val_igv_ped_rec;
    private double val_dcto_ped_rec;
    private double val_tip_cambio_ped_rec;
    private int cant_items_ped_rec;
    private String est_ped_rec = "";
    private String usu_crea_ped_rec_cab = "";
    private Date fec_crea_ped_rec_cab;
    private String usu_mod_ped_rec_cab = "";
    private Date fec_mod_ped_rec_cab;
    private Date fecha_vencimiento;
    //2017.05.23
    private String num_cmp;
    private String des_nom_medico;
    private String des_ape_medico;
    private String cod_medico;

    
    public VtaPedRecetaCab() {
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

    public void setCdg_apm(String cdg_apm) {
        this.cdg_apm = cdg_apm;
    }

    public String getCdg_apm() {
        return cdg_apm;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setFec_ped_rec(Date fec_ped_rec) {
        this.fec_ped_rec = fec_ped_rec;
    }

    public Date getFec_ped_rec() {
        return fec_ped_rec;
    }

    public void setVal_bruto_ped_rec(double val_bruto_ped_rec) {
        this.val_bruto_ped_rec = val_bruto_ped_rec;
    }

    public double getVal_bruto_ped_rec() {
        return val_bruto_ped_rec;
    }

    public void setVal_neto_ped_rec(double val_neto_ped_rec) {
        this.val_neto_ped_rec = val_neto_ped_rec;
    }

    public double getVal_neto_ped_rec() {
        return val_neto_ped_rec;
    }

    public void setVal_redondeo_ped_rec(double val_redondeo_ped_rec) {
        this.val_redondeo_ped_rec = val_redondeo_ped_rec;
    }

    public double getVal_redondeo_ped_rec() {
        return val_redondeo_ped_rec;
    }

    public void setVal_igv_ped_rec(double val_igv_ped_rec) {
        this.val_igv_ped_rec = val_igv_ped_rec;
    }

    public double getVal_igv_ped_rec() {
        return val_igv_ped_rec;
    }

    public void setVal_dcto_ped_rec(double val_dcto_ped_rec) {
        this.val_dcto_ped_rec = val_dcto_ped_rec;
    }

    public double getVal_dcto_ped_rec() {
        return val_dcto_ped_rec;
    }

    public void setVal_tip_cambio_ped_rec(double val_tip_cambio_ped_rec) {
        this.val_tip_cambio_ped_rec = val_tip_cambio_ped_rec;
    }

    public double getVal_tip_cambio_ped_rec() {
        return val_tip_cambio_ped_rec;
    }

    public void setCant_items_ped_rec(int cant_items_ped_rec) {
        this.cant_items_ped_rec = cant_items_ped_rec;
    }

    public int getCant_items_ped_rec() {
        return cant_items_ped_rec;
    }

    public void setEst_ped_rec(String est_ped_rec) {
        this.est_ped_rec = est_ped_rec;
    }

    public String getEst_ped_rec() {
        return est_ped_rec;
    }

    public void setUsu_crea_ped_rec_cab(String usu_crea_ped_rec_cab) {
        this.usu_crea_ped_rec_cab = usu_crea_ped_rec_cab;
    }

    public String getUsu_crea_ped_rec_cab() {
        return usu_crea_ped_rec_cab;
    }

    public void setFec_crea_ped_rec_cab(Date fec_crea_ped_rec_cab) {
        this.fec_crea_ped_rec_cab = fec_crea_ped_rec_cab;
    }

    public Date getFec_crea_ped_rec_cab() {
        return fec_crea_ped_rec_cab;
    }

    public void setUsu_mod_ped_rec_cab(String usu_mod_ped_rec_cab) {
        this.usu_mod_ped_rec_cab = usu_mod_ped_rec_cab;
    }

    public String getUsu_mod_ped_rec_cab() {
        return usu_mod_ped_rec_cab;
    }

    public void setFec_mod_ped_rec_cab(Date fec_mod_ped_rec_cab) {
        this.fec_mod_ped_rec_cab = fec_mod_ped_rec_cab;
    }

    public Date getFec_mod_ped_rec_cab() {
        return fec_mod_ped_rec_cab;
    }


    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }


    public void setNum_cmp(String num_cmp) {
        this.num_cmp = num_cmp;
    }

    public String getNum_cmp() {
        return num_cmp;
    }

    public void setDes_nom_medico(String des_nom_medico) {
        this.des_nom_medico = des_nom_medico;
    }

    public String getDes_nom_medico() {
        return des_nom_medico;
    }

    public void setDes_ape_medico(String des_ape_medico) {
        this.des_ape_medico = des_ape_medico;
    }

    public String getDes_ape_medico() {
        return des_ape_medico;
    }

    public void setCod_medico(String cod_medico) {
        this.cod_medico = cod_medico;
    }

    public String getCod_medico() {
        return cod_medico;
    }
}
