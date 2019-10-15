package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 01.09.2016
 */
public class VtaCompAtencionMedicaRAC extends VtaCompAtencionMedica {
    public VtaCompAtencionMedicaRAC() {
        super();
    }
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_local){setCod_local(cod_local);}
    public void set3(String num_ped_vta){setNum_ped_vta(num_ped_vta);}
    public void set4(String estado){setEstado(estado);}
    public void set5(Date fec_crea){setFec_crea(fec_crea);}
    public void set6(String usu_crea){setUsu_crea(usu_crea);}
    public void set7(Date fec_mod){setFec_mod(fec_mod);}
    public void set8(String usu_mod){setUsu_mod(usu_mod);}
    public void set9(String tip_comp_pago){setTip_comp_pago(tip_comp_pago);}
    public void set10(String num_comp_pago){setNum_comp_pago(num_comp_pago);}
    
}
