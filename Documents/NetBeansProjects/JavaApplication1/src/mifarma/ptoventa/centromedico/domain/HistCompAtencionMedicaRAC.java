package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 09.09.2016
 */
public class HistCompAtencionMedicaRAC extends HistCompAtencionMedica {
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_local){setCod_local(cod_local);}
    public void set3(String num_ped_vta){setNum_ped_vta(num_ped_vta);}
    public void set4(int sec_hist_med){setSec_hist_med(sec_hist_med);}
    public void set5(Date fec_hist_med){setFec_hist_med(fec_hist_med);}
    public void set6(String cod_cia){setCod_cia(cod_cia);}
    public void set7(String cod_local_cme){setCod_local_cme(cod_local_cme);}
    public void set8(String num_aten_med){setNum_aten_med(num_aten_med);}
    public void set9(String estado){setEstado(estado);}
    public void set10(Date fec_crea){setFec_crea(fec_crea);}
    public void set11(String usu_crea){setUsu_crea(usu_crea);}
    public void set12(Date fec_mod){setFec_mod(fec_mod);}
    public void set13(String usu_mod){setUsu_mod(usu_mod);}
    
}
