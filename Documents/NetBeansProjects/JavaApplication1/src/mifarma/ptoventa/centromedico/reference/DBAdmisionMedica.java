package mifarma.ptoventa.centromedico.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;

import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;

import mifarma.ptoventa.centromedico.domain.historiaclinica.CmeAtencionMedicaTri;

import mifarma.ptoventa.reportes.reference.DBReportes;

import oracle.sql.CHAR;

public class DBAdmisionMedica {
    public DBAdmisionMedica() {
        super();
    }
    public static ArrayList obtenerListadoCompPacientes(String pCodGrupoCia, String pCodLocal, String pTipo,
                                                        VtaCompAtencionMedica vtaCAM,VtaPedidoAtencionMedica vtaPAM,
                                                        boolean vIndGestor) throws Exception {

        if(vIndGestor){
            FacadeAtencioMedica fAM = new FacadeAtencioMedica();
            return fAM.obtenerListadoCompPacientes(pCodGrupoCia, pCodLocal, pTipo, vtaCAM, vtaPAM);
        }      
        else{                                                      
            ArrayList resultado = new ArrayList();
            ArrayList parametros = new ArrayList();
            parametros.add(pCodGrupoCia);
            parametros.add(pCodLocal);
            parametros.add(pTipo);
            parametros.add(vtaCAM.getTip_comp_pago());
            parametros.add(vtaCAM.getNum_comp_pago());
            parametros.add(vtaPAM.getTip_documento());
            parametros.add(vtaPAM.getNum_documento());
            parametros.add(vtaPAM.getNombre());
            parametros.add(vtaPAM.getApe_pat());
            parametros.add(vtaPAM.getApe_mat());
            FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA_CME_ADM.CME_LISTA_PACIENTES(?,?,?,?,?,?,?,?,?,?)", parametros);
            return resultado;
        }
    }
    
    public static ArrayList validaComprobantePago(VtaCompAtencionMedica vtaCAM) throws SQLException {
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(vtaCAM.getTip_comp_pago());
        parametros.add(vtaCAM.getNum_comp_pago());
                                                                                    
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA_CME_ADM.CME_VALIDA_COMPROBANTE_PAGO(?,?,?,?)",
                                                          parametros);
        
        return resultado;
    }
    
    public static ArrayList obtieneBenficiario() throws SQLException {
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        //parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesCentroMedico.vCodLocalVtaComprobante);
        parametros.add(VariablesCentroMedico.vTipComprobante);
        parametros.add(VariablesCentroMedico.vNumComprobante);
        
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA_CME_ADM.CME_GET_BENEFICIARIO(?,?,?,?)",
                                                          parametros);
        
        return resultado;
    }
    
    public static ArrayList obtenerEdad(String fecNacimiento) throws SQLException {
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(fecNacimiento);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA_CME_ADM.CME_GET_EDAD(?)",
                                                          parametros);
        
        return resultado;
    }
    
    public static String grabarPaciente(String tipoOperacion,BeanPaciente paciente) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(tipoOperacion);
        
        parametros.add(paciente.getNumHistCli());
        parametros.add(paciente.getCodPaciente());
        
        parametros.add(paciente.getApellidoPaterno());
        parametros.add(paciente.getApellidoMaterno());
        parametros.add(paciente.getNombre());
        parametros.add(paciente.getTipDocumento());
        parametros.add(paciente.getNumDocumento());
        
        parametros.add(paciente.getNumHCFisica());
        parametros.add(paciente.getFecHCFisica());
        parametros.add(paciente.getTipAcom());
        parametros.add(paciente.getNomAcom());
        parametros.add(paciente.getTipDocAcom());
        parametros.add(paciente.getNumDocAcom());
        
        parametros.add(paciente.getSexo());
        parametros.add(paciente.getEstCivil());
        parametros.add(paciente.getFecNacimiento());
        parametros.add(paciente.getDireccion());
        
        /*parametros.add(paciente.getLugNacimiento());
        parametros.add(paciente.getLugProcedencia());
        parametros.add(paciente.getUbigeo());*/
        parametros.add(paciente.getDepUbigeo());
        parametros.add(paciente.getPvrUbigeo());
        parametros.add(paciente.getDisUbigeo());
        parametros.add(paciente.getDepLugNac());
        parametros.add(paciente.getPvrLugNac());
        parametros.add(paciente.getDisLugNac());
        parametros.add(paciente.getDepLugPro());
        parametros.add(paciente.getPvrLugPro());
        parametros.add(paciente.getDisLugPro());
        
        parametros.add(paciente.getGradoInstruccion());
        parametros.add(paciente.getOcupacion());
        parametros.add(paciente.getCentroEduLab());
        
        parametros.add(paciente.getGrupoSan());
        parametros.add(paciente.getFactorRH());
        
        parametros.add(paciente.getEmail());
        parametros.add(paciente.getTelFijo());
        parametros.add(paciente.getTelCelular());
        
        
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GRABAR_PACIENTE_HC(?,?,?,?,?," +
                                                                                                   "?,?,?,?,?," +
                                                                                                   "?,?,?,?,?," +
                                                                                                   "?,?,?,?,?," +
                                                                                                   "?,?,?,?,?," +
                                                                                                   "?,?,?,?,?," +
                                                                                                   "?,?,?,?,?," +
                                                                                                   "?,?,?)",
                                                           parametros);
    } 
    
    public static String insertAtencionMedica(BeanAtencionMedica ateMedica) throws SQLException {
            ArrayList parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodCia);
            parametros.add(FarmaVariables.vCodLocal);
            parametros.add(FarmaVariables.vIdUsu);
            parametros.add(VariablesCentroMedico.vTipComprobante);
            parametros.add(VariablesCentroMedico.vNumComprobante);
            parametros.add(VariablesCentroMedico.vNumPedVtaComprobante);
            parametros.add(VariablesCentroMedico.vCodLocalVtaComprobante);
            parametros.add(ateMedica.getCodPaciente());
            parametros.add(ateMedica.getCodMedico());
            parametros.add(ateMedica.getEstado());
            parametros.add(ateMedica.getCodEspecialidad());
            parametros.add(ateMedica.getCodTipAtencion());
            
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_INSERT_ATENCION_MEDICA(?,?,?,?,?,?,?,?,?,?,?,?,?)",parametros);       
    }
    
    public static String insertTriaje(CmeAtencionMedicaTri ateMedicaTri) throws SQLException {
            ArrayList parametros = new ArrayList();
            parametros.add(FarmaVariables.vCodGrupoCia);
            parametros.add(FarmaVariables.vCodCia);
            parametros.add(FarmaVariables.vCodLocal);
            parametros.add(FarmaVariables.vIdUsu);
            parametros.add(ateMedicaTri.getNumAtenMed());
            parametros.add(ateMedicaTri.getCantPA1());
            parametros.add(ateMedicaTri.getCantPA2());
            parametros.add(ateMedicaTri.getCantFR());
            parametros.add(ateMedicaTri.getCantFC());
            parametros.add(ateMedicaTri.getCantTemp());
            parametros.add(ateMedicaTri.getCantPeso());
            parametros.add(ateMedicaTri.getCantTalla());
        
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_INSERT_TRIAJE(?,?,?,?,?,?,?,?,?,?,?,?)",parametros);       
    }
    
    
    public static ArrayList obtenerTrazabilidad(String pCodGrupoCia, String fecIni, String fecFin) throws SQLException {
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        parametros.add(pCodGrupoCia);
        parametros.add(fecIni);
        parametros.add(fecFin);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA_CME_ADM.F_LISTA_TRAZABILIDAD(?,?,?)", parametros);
        return resultado;
    }
    
    public static String anularAtencionMedica(String numAtenMedica) throws SQLException {
                ArrayList parametros = new ArrayList();
                parametros.add(FarmaVariables.vCodGrupoCia);
                parametros.add(FarmaVariables.vCodCia);
                parametros.add(FarmaVariables.vCodLocal);
                parametros.add(numAtenMedica);
                parametros.add(FarmaVariables.vIdUsu);
                parametros.add("");//Tip Comp
                parametros.add("");//Num Comp
                
       return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_ANULAR_ATENCION_MEDICA(?,?,?,?,?,?,?)",parametros);       
    }
    
    public static void listaMedicos(FarmaTableModel pTableModel, String codMedico,String apellido,
                                               String nombre,String CMP) throws SQLException {
        pTableModel.clearTable();
        ArrayList parametros = new ArrayList();   
        parametros.add(codMedico);
        parametros.add(apellido);
        parametros.add(nombre);
        parametros.add(CMP);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_CME_ADM.CME_LISTA_MEDICOS(?,?,?,?)",
                                             parametros, false);
    }
    
    public static void buscarporCodigoMedico(ArrayList pTableModel, String codMedico,String apellido,
                                               String nombre,String CMP) throws Exception {
        ArrayList parametros = new ArrayList();   
        parametros.add(codMedico);
        parametros.add(apellido);
        parametros.add(nombre);
        parametros.add(CMP);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pTableModel, "PTOVENTA_CME_ADM.CME_LISTA_MEDICOS(?,?,?,?)",
                                             parametros);
    }
    
    public static void buscarporCodigoConsulta(ArrayList pTableModel, String codConsulta) throws Exception {
        ArrayList parametros = new ArrayList();   
        parametros.add(codConsulta);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(pTableModel, "PTOVENTA_CME_ADM.CME_GET_CONSULTA(?)",
                                             parametros);
    }
    
    public static String  getDatosComprobante(VtaCompAtencionMedica vtaCAM) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(vtaCAM.getTip_comp_pago());
        parametros.add(vtaCAM.getNum_comp_pago());
                                                                                    
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_F_VARC_DATO_COMP(?,?,?,?)",parametros);
        
    }    
    
    public static String getDatosCompVentaMedica(String numAtenMedica) throws SQLException {
                ArrayList parametros = new ArrayList();
                parametros.add(FarmaVariables.vCodGrupoCia);
                parametros.add(FarmaVariables.vCodLocal);
                parametros.add(numAtenMedica);
       return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_F_VARC_DATO_COMP_MEDICA(?,?,?)",parametros);       
    } 
    
    public static ArrayList obtenerDatosCliente(String NumeroDNI) throws SQLException {
        ArrayList resultado = new ArrayList();
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(NumeroDNI);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(resultado, "PTOVENTA_CME_ADM.CME_F_CUR_DATOS_EXISTE_DNI(?,?,?,?)",
                                                          parametros);
        
        return resultado;
    }
    
    public static String validaFecha(String fecha,String mensaje) throws SQLException {
        ArrayList parametros = new ArrayList();
        parametros = new ArrayList();
        parametros.add(fecha);
        parametros.add(mensaje);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_F_VALIDA_FECHA(?,?)",parametros);
        
    }
    
    public static List obtenerListaConsultas()throws Exception{
        ArrayList parametros = new ArrayList();
        return FarmaDBUtility.executeSQLStoredProcedureListMap("PTOVENTA_CME_ADM.F_LISTA_CONSULTAS", parametros);
    }
    
    /*CONSULTAS PARA LA OBTENCION DE DATOS DE LAS HISTORIAS CLINICAS PARA SU IMPRESION*/
    
    public static String obtenerDescEstCivil(String codEstCivil)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(codEstCivil);
        String desc_EstCivil = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_EST_CIVIL(?,?)", parametros);
        return desc_EstCivil;
    }
           
    public static String obtenerDescGradoInst(String gradoInst)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(gradoInst);
        String desc_GradoInst = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_GR_INS(?,?)", parametros);
        return desc_GradoInst;
    }
           
    public static String obtenerDescGrupoSanguineo(String grupoSang)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(grupoSang);
        String desc_GrupoSanguineo = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_GR_SANGUINEO(?,?)", parametros);
        return desc_GrupoSanguineo;
    }
           
    public static String obtenerDescFactorRH(String factor)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(factor);
        String desc_FactorRH = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_FACTOR_RH(?,?)", parametros);
        return desc_FactorRH;
    }
           
    public static String obtenerDescTipoInf(String tipoInf)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(tipoInf);
        String desc_TipoInf = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_TIPO_INF(?,?)", parametros);
        return desc_TipoInf;
    }
           
    public static String obtenerDescFuncion(String funcion)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(funcion);
        String desc_Funcion = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_FUN_BIO(?,?)", parametros);
        return desc_Funcion;
    }
           
    public static String obtenerEstGral(String estado)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(estado);
        String desc_Estado = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_ESTADO_GRAL(?,?)", parametros);
        return desc_Estado;
    }
           
    public static String obtenerLugar(String dep, String pro, String dis)throws Exception{
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(dep);
        parametros.add(pro);
        parametros.add(dis);
        String desc_Estado = FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_CME_ADM.CME_GET_DESC_LUGAR(?,?,?,?)", parametros);
        return desc_Estado;
    }
    
}
