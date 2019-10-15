package mifarma.ptoventa.centromedico.reference;

import java.awt.Component;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.domain.VtaCompAtencionMedica;
import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;

import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;
import mifarma.ptoventa.centromedico.domain.historiaclinica.CmeAtencionMedicaTri;
import mifarma.ptoventa.ventas.reference.DBVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityAdmisionMedica {
    
    private static final Logger log = LoggerFactory.getLogger(UtilityAdmisionMedica.class);
    
    public UtilityAdmisionMedica() {
        super();
    }

    public static void cargarListaCompPacientes(FarmaTableModel model,String ptipo,
                                                VtaCompAtencionMedica vtaCAM,
                                                VtaPedidoAtencionMedica vtaPAM,
                                                boolean vIndRac) throws Exception{
        model.clearTable();
        /*String tipComPago="";
        String numComPago="";
        String tipDoc="";
        String numDoc="";
        String nombre="";
        String apePat="";
        String apeMat="";*/
        
        //try{
            /*if(ptipo.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_COMPROBANTE)){//Datos del Comprobante
               tipComPago=vtaCAM.getTip_comp_pago();
               numComPago=vtaCAM.getNum_comp_pago();
            }else if(ptipo.equals(ConstantsCentroMedico.TIPO_BUSQUEDA_PACIENTE)){//Datos del Paciente
               tipDoc=vtaPAM.getTip_documento();
               numDoc=vtaPAM.getNum_documento();
               nombre=vtaPAM.getNombre();
               apePat=vtaPAM.getApe_pat();
               apeMat=vtaPAM.getApe_mat();
            }*/
            model.data = DBAdmisionMedica.obtenerListadoCompPacientes(FarmaVariables.vCodGrupoCia,
                                                                      /*FarmaVariables.vCodLocal*/
                    VariablesCentroMedico.vCodLocalVtaComprobante,
                                                                      ptipo,
                                                                      vtaCAM,
                                                                      vtaPAM,
                                                                      vIndRac);
            
            
        /*}catch(Exception ex){
            log.error("", ex);
        }*/
        model.fireTableDataChanged();
    }
    
    
    public static void getPacienteRacToLocal(String pCodPaciente){
            FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
            facadeVtaAtenMed.recuperarHistoriaClinica(pCodPaciente);
    }
    
    public static ArrayList validaComprobantePago(VtaCompAtencionMedica vtaCAM) throws SQLException {
          
        ArrayList lista = new ArrayList();
        lista = DBAdmisionMedica.validaComprobantePago(vtaCAM);
        return lista;
    }
    
    public static ArrayList obtieneBenficiario() throws SQLException {
        ArrayList lista = new ArrayList();
        lista = DBAdmisionMedica.obtieneBenficiario();
        return lista;
    }
    
    public static ArrayList obtenerEdad(String fecNacimiento) throws SQLException {
        ArrayList lista = new ArrayList();
        //try{
        lista = DBAdmisionMedica.obtenerEdad(fecNacimiento);
        
        /*}catch(Exception ex){
            log.error("", ex);
            lista=new ArrayList();
        }*/
        return lista;
    }
    
    public static String validaFecha(String fecha,String mensaje) throws SQLException {
        return DBAdmisionMedica.validaFecha(fecha,mensaje);
         
    }    
    
    public static boolean validaFecha(JDialog dialog,JTextField pJTextField,String mensaje){
        boolean flag=true;
        String fecha=pJTextField.getText().trim();
      if(fecha.length()!=0){
          try{
                String fec = UtilityAdmisionMedica.validaFecha(fecha,mensaje); 
            }catch (SQLException sql) {
                            if (sql.getErrorCode() > 20000) {
                            FarmaUtility.showMessage(dialog, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                     pJTextField);
                            } 
                            else {
                            FarmaUtility.showMessage(dialog, "Error al obtener fecha.\n" + sql.getMessage(), pJTextField);
                            log.error("", sql);
                            }
                flag=false;           
            }catch(Exception er){
                            FarmaUtility.showMessage(dialog, "Ocurrió el siguiente error\n "+er.getMessage(), pJTextField);  
                            log.error("", er);
                flag=false;    
            }   
          
      }
        return flag;
    }
        
    
    public static String grabarPaciente(String operacion,BeanPaciente paciente) throws SQLException {
        String codPaciente="";
        codPaciente = DBAdmisionMedica.grabarPaciente(operacion,paciente);        
        return codPaciente;
    }
    
    public static String insertAtencionMedica(BeanAtencionMedica atenMedica) throws SQLException{
        String salida="1";
        salida = DBAdmisionMedica.insertAtencionMedica(atenMedica);
        return salida;
    }
    
    public static String insertAtencionMedicaTri(CmeAtencionMedicaTri atenMedicaTri) throws SQLException {
        String salida="1";
        salida = DBAdmisionMedica.insertTriaje(atenMedicaTri);
        return salida;
    }
    
    public static void obtenerTrazabilidad(FarmaTableModel model, String fecIni, String fecFin) throws SQLException{
        model.clearTable();
        model.data = DBAdmisionMedica.obtenerTrazabilidad(FarmaVariables.vCodGrupoCia, fecIni, fecFin);
        model.fireTableDataChanged();
    }
    
    public static String anularAtencionMedica(String numAtenMedica) throws SQLException {
        String salida="S";
        salida = DBAdmisionMedica.anularAtencionMedica(numAtenMedica);
        return salida;
    }
    
    public static boolean valorCampoEsNulo(Component component,JDialog dialog){
        return valorCampoEsNulo(component, true,dialog);
    }
    
    public static boolean valorCampoEsNulo(Component component, boolean mostrarMensaje,JDialog dialog){
        boolean vacio = false;
        if(component instanceof JTextComponent){
            String texto = ((JTextComponent)component).getText();
            if(texto == null || (texto != null && texto.trim().length() == 0)){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(dialog, "El campo "+component.getName()+" no puede ser vacío.", component);
            }
        }else if(component instanceof JTable){
            if(((JTable)component).getRowCount() == 0){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(dialog, "No ha agregado registro a la lista de "+component.getName()+". verifique!!!", component);
            }
        }else if(component instanceof JComboBox){
            if(((JComboBox)component).getSelectedIndex() < 1){
                vacio = true;
                if(mostrarMensaje)
                    FarmaUtility.showMessage(dialog, "No ha seleccionado un item en el campo "+component.getName()+". verifique!!!", component);
            }
        }
        return vacio;
    }
    
    
    public static String getDatosComprobante(VtaCompAtencionMedica vtaCAM){
        try {
            return DBAdmisionMedica.getDatosComprobante(vtaCAM);
        } catch (Exception sqle) {
            // TODO: Add catch code
            log.info(sqle.getMessage());
            return "N";
        }
    }
    
    public static ArrayList obtenerDatosCliente(String NumeroDNI) throws SQLException {
        ArrayList lista = new ArrayList();
        lista = DBAdmisionMedica.obtenerDatosCliente(NumeroDNI);
        return lista;
    }
    
    public ArrayList obtenerListaConsultas(){
        List lista;
        ArrayList lstRespuesta = new ArrayList();
        try{
            lista = DBAdmisionMedica.obtenerListaConsultas();
            for(int i=0; i<lista.size();i++){
                ArrayList fila = new ArrayList();
                Map map = (Map)lista.get(i);
                /*fila.add((String)map.get("COD_CIE_10"));
                fila.add((String)map.get("DES_DIAGNOSTICO"));
                fila.add((String)map.get("COD_DIAGNOSTICO"));*/
                fila.add((String)map.get("COD_CONSULTA"));
                fila.add((String)map.get("DESC_CONSULTA"));
                lstRespuesta.add(fila);
            }
        }catch(Exception ex){
            
        }
        return lstRespuesta;
    }
    
    public static String obtenerEstCivil(String codEstCivil){
        try {
            return DBAdmisionMedica.obtenerDescEstCivil(codEstCivil);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String obtenerGradoInst(String codGrInst){
        try {
            return DBAdmisionMedica.obtenerDescGradoInst(codGrInst);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String obtenerGrupoSanguineo(String codGrSang){
        try {
            return DBAdmisionMedica.obtenerDescGrupoSanguineo(codGrSang);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String obtenerFactorRH(String codFactor){
        try {
            return DBAdmisionMedica.obtenerDescFactorRH(codFactor);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String obtenerTipoInf(String tipoInf){
        try {
            return DBAdmisionMedica.obtenerDescTipoInf(tipoInf);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String obtenerDescFuncion(String funcion){
        try {
            return DBAdmisionMedica.obtenerDescFuncion(funcion);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String obtenerEstGral(String estado){
        try {
            return DBAdmisionMedica.obtenerEstGral(estado);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String obtenerLugar(String dep, String pro, String dis){
        try {
            return DBAdmisionMedica.obtenerLugar(dep, pro, dis);
        } catch (Exception e) {
            return null;
        }
    }
    
}
