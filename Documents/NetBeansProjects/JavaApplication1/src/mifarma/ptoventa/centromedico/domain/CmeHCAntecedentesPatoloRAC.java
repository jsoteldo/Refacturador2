package mifarma.ptoventa.centromedico.domain;

import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentesPatologico;

/**
 * @author ERIOS
 * @since 13.09.2016
 */
public class CmeHCAntecedentesPatoloRAC extends BeanHCAntecedentesPatologico{
    
    public void set1(String cod_grupo_cia){setCodGrupoCia(cod_grupo_cia);}
    public void set2(String cod_paciente){setCodPaciente(cod_paciente);}
    public void set3(int secuencialHC){setSecuencialHC(secuencialHC);}    
    public void set4(String codDiagnostico){setCodDiagnostico(codDiagnostico);}
    public void set5(String tipoPatologico){setTipoPatologico(tipoPatologico);}
    public void set6(String descripcionPariente){setDescripcionPariente(descripcionPariente);}
    
}
