package mifarma.ptoventa.centromedico.domain;


/**
 * @autor ERIOS
 * @since 13.09.2016
 */
public class CmeHCAntecedentesDetalleRAC extends CmeHCAntecedentesDetalle{
    
    public void set1(String cod_grupo_cia){setCodGrupoCia(cod_grupo_cia);}
    public void set2(String cod_paciente){setCodPaciente(cod_paciente);}
    public void set3(int secuencialHC){setSecuencialHC(secuencialHC);}    
    public void set4(int codMaestroDetalle){setCodMaestroDetalle(codMaestroDetalle);}
    public void set5(String tipoMaeDatalle){setTipoMaeDatalle(tipoMaeDatalle);}
    public void set6(String descripcionOtros){setDescripcionOtros(descripcionOtros);}
    
}
