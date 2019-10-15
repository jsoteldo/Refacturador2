package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 14.09.2016
 */
public class CmeAtencionMedicaTratamientoRAC extends CmeAtencionMedicaTratamiento{
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_cia){setCod_cia(cod_cia);}
    public void set3(String cod_local){setCod_local(cod_local);}
    public void set4(String num_aten_med){setNum_aten_med(num_aten_med);}
    public void set5(String num_ped_rec){setNum_ped_rec(num_ped_rec);}
    public void set6(Date validez_receta){setValidez_receta(validez_receta);}
    public void set7(String indicaciones_generales){setIndicaciones_generales(indicaciones_generales);}
    public void set8(String estado){setEstado(estado);}
    public void set9(Date fec_crea){setFec_crea(fec_crea);}
    public void set10(String usu_crea){setUsu_crea(usu_crea);}
    public void set11(Date fec_mod){setFec_mod(fec_mod);}
    public void set12(String usu_mod){setUsu_mod(usu_mod);}
    
}
