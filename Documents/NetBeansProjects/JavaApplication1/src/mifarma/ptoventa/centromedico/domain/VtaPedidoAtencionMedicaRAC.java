package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 01.09.2016
 */
public class VtaPedidoAtencionMedicaRAC extends VtaPedidoAtencionMedica {
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_local){setCod_local(cod_local);}
    public void set3(String num_ped_vta){setNum_ped_vta(num_ped_vta);}
    public void set4(String tip_documento){setTip_documento(tip_documento);}
    public void set5(String num_documento){setNum_documento(num_documento);}
    public void set6(String ape_pat){setApe_pat(ape_pat);}
    public void set7(String ape_mat){setApe_mat(ape_mat);}
    public void set8(String nombre){setNombre(nombre);}
    public void set9(String estado){setEstado(estado);}
    public void set10(Date fec_crea){setFec_crea(fec_crea);}
    public void set11(String usu_crea){setUsu_crea(usu_crea);}
    public void set12(Date fec_mod){setFec_mod(fec_mod);}
    public void set13(String usu_mod){setUsu_mod(usu_mod);}
    
}
