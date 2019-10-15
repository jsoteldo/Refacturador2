package mifarma.ptoventa.centromedico.domain;

import java.util.Date;

/**
 * @author ERIOS
 * @since 14.09.2016
 */
public class CmeAtencionMedicaEnfRAC extends CmeAtencionMedicaEnfAct{
    
    public void set1(String cod_grupo_cia){setCod_grupo_cia(cod_grupo_cia);}
    public void set2(String cod_cia){setCod_cia(cod_cia);}
    public void set3(String cod_local){setCod_local(cod_local);}
    public void set4(String num_aten_med){setNum_aten_med(num_aten_med);}
    public void set5(String motivo_consulta){setMotivo_consulta(motivo_consulta);}
    public void set6(String tipo_informante){setTipo_informante(tipo_informante);}
    public void set7(int tiempo_enfermedad){setTiempo_enfermedad(tiempo_enfermedad);}
    public void set8(String forma_inicio){setForma_inicio(forma_inicio);}
    public void set9(String signos){setSignos(signos);}
    public void set10(String sintomas){setSintomas(sintomas);}
    public void set11(String relato_cronologico){setRelato_cronologico(relato_cronologico);}
    public void set12(String tipo_apetito){setTipo_apetito(tipo_apetito);}
    public void set13(String tipo_sed){setTipo_sed(tipo_sed);}
    public void set14(String tipo_sueno){setTipo_sueno(tipo_sueno);}
    public void set15(String tipo_orina){setTipo_orina(tipo_orina);}
    public void set16(String tipo_deposicion){setTipo_deposicion(tipo_deposicion);}
    public void set17(String estado){setEstado(estado);}
    public void set18(Date fec_crea){setFec_crea(fec_crea);}
    public void set19(String usu_crea){setUsu_crea(usu_crea);}
    public void set20(Date fec_mod){setFec_mod(fec_mod);}
    public void set21(String usu_mod){setUsu_mod(usu_mod);}
    
}
