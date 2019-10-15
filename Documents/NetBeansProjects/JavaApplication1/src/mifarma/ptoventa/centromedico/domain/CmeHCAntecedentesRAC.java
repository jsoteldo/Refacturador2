package mifarma.ptoventa.centromedico.domain;

import java.util.Date;


/**
 * @author ERIOS
 * @since 13.09.2016
 */
public class CmeHCAntecedentesRAC extends CmeHCAntecedentes{
            
    public void set1(String cod_grupo_cia){setCodGrupoCia(cod_grupo_cia);}
    public void set2(String cod_paciente){setCodPaciente(cod_paciente);}
    public void set3(int secuencialHC){setSecuencialHC(secuencialHC);}
    public void set4(String medicamentos){setMedicamentos(medicamentos);}
    public void set5(String ram){setRam(ram);}
    public void set6(String ocupacionales){setOcupacionales(ocupacionales);}
    public void set7(String estado){setEstado(estado);}
    public void set8(Date fec_crea){setFec_crea(fec_crea);}
    public void set9(String usu_crea){setUsu_crea(usu_crea);}
    public void set10(Date fec_mod){setFec_mod(fec_mod);}
    public void set11(String usu_mod){setUsu_mod(usu_mod);}

}
