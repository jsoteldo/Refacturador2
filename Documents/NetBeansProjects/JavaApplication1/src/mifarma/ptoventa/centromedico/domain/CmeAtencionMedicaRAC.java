package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 14.09.2016
 */
public class CmeAtencionMedicaRAC extends CmeAtencionMedica{
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_cia){setCod_cia(cod_cia);}
    public void set3(String cod_local){setCod_local(cod_local);}
    public void set4(String num_aten_med){setNum_aten_med(num_aten_med);}
    public void set5(String cod_medico){setCod_medico(cod_medico);}
    public void set6(String cod_paciente){setCod_paciente(cod_paciente);}
    public void set7(String estado){setEstado(estado);}
    public void set8(Date fec_crea){setFec_crea(fec_crea);}
    public void set9(String usu_crea){setUsu_crea(usu_crea);}
    public void set10(Date fec_mod){setFec_mod(fec_mod);}
    public void set11(String usu_mod){setUsu_mod(usu_mod);}
    public void set12(String ind_anulado){setInd_anulado(ind_anulado);}
    public void set13(int cod_maes_det){setCod_maes_det(cod_maes_det);}
    public void set14(String cod_local_vta){setCod_local_vta(cod_local_vta);}
    public void set15(String num_ped_vta){setNum_ped_vta(num_ped_vta);}
    public void set16(int cod_tipo_atencion){setCod_tipo_atencion(cod_tipo_atencion);}
    
}
