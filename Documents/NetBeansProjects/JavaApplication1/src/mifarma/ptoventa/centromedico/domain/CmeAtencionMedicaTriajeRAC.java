package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 14.09.2016
 */
public class CmeAtencionMedicaTriajeRAC extends CmeAtencionMedicaTriaje{
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_cia){setCod_cia(cod_cia);}
    public void set3(String cod_local){setCod_local(cod_local);}
    public void set4(String num_aten_med){setNum_aten_med(num_aten_med);}
    public void set5(int pa_1){setPa_1(pa_1);}
    public void set6(int pa_2){setPa_2(pa_2);}
    public void set7(int fr){setFr(fr);}
    public void set8(int fc){setFc(fc);}
    public void set9(double temp){setTemp(temp);}
    public void set10(double peso){setPeso(peso);}
    public void set11(double talla){setTalla(talla);}
    public void set12(String estado){setEstado(estado);}
    public void set13(Date fec_crea){setFec_crea(fec_crea);}
    public void set14(String usu_crea){setUsu_crea(usu_crea);}
    public void set15(Date fec_mod){setFec_mod(fec_mod);}
    public void set16(String usu_mod){setUsu_mod(usu_mod);}
    
}
