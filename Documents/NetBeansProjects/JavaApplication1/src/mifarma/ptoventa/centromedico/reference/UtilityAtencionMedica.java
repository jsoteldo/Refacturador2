package mifarma.ptoventa.centromedico.reference;

import com.gs.mifarma.componentes.OptionComboBox;

import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.print.PrinterJob;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.net.URL;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JTextField;

import mifarma.common.FarmaConnection;
import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;


import mifarma.electronico.UtilityImpCompElectronico;

import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTrataReceta;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedTratamiento;
import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtencionMedica;
import mifarma.ptoventa.caja.reference.VariablesCaja;


import mifarma.ptoventa.centromedico.DlgADMDatosPaciente;
import mifarma.ptoventa.centromedico.DlgAtencionMedica;

import mifarma.ptoventa.centromedico.DlgHistoriaClinicaAntecedentes;
import mifarma.ptoventa.centromedico.TipoAtencionCM;
import mifarma.ptoventa.centromedico.domain.BeanPaciente;

import mifarma.ptoventa.centromedico.domain.atencionmedica.BeanAtMedDiagnostico;
import mifarma.ptoventa.centromedico.domain.historiaclinica.BeanHCAntecedentes;

import mifarma.ptoventa.main.FrmEconoFar;

import mifarma.ptoventa.reference.DBPtoVenta;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import net.sf.jasperreports.view.JasperViewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityAtencionMedica {
    
    private static final Logger log = LoggerFactory.getLogger(UtilityAtencionMedica.class);
    
    public UtilityAtencionMedica() {
        super();
    }
    
    public static void cargarListaEsperaPacientes(FarmaTableModel model, String pCodEstado, String pCodMedico){
        if(pCodMedico==null)
            pCodMedico = "";
        else
            pCodMedico = pCodMedico.trim();
        model.clearTable();
        /*
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        model.data = facade.obtenerListadoPacientesEspera(FarmaVariables.vCodGrupoCia, pCodEstado, pCodMedico);
        */
        try{
            model.data = DBAtencionMedica.obtenerListadoPacientesEspera(FarmaVariables.vCodGrupoCia, pCodEstado, pCodMedico);
            
        }catch(Exception ex){
            log.error("", ex);
        }
        model.fireTableDataChanged();
    }
    
    public static void actualizarEstadoSolicitudAtencion(JDialog pJDialog, String pNroSolicitud, String pCodEstado){
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        boolean actualizo = facade.actualizaSolicutudAtencion(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, pNroSolicitud, pCodEstado, FarmaVariables.vIdUsu);
        if(!actualizo)
            FarmaUtility.showMessage(pJDialog, "Centro Medico\nNo se pudo actualizar el estado de la solicitud\n"+
                                           "Reintente, si persiste comuniquese con Mesa de Ayuda!!!", null);
    }
    
    public ArrayList cargarComboTipoInformante(JDialog pJDialog){
        ArrayList lstResultado = null;
        try{
            lstResultado = DBAtencionMedica.obtenerListaTipoInformante();
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Error al cargar listado de via de administracion.\n"+ex.toString(), null);
            lstResultado = new ArrayList();
        }
        return lstResultado;
    }
    
    public ArrayList cargarComboEstadoFuncionesBiologicas(JDialog pJDialog){
        ArrayList lstResultado = null;
        try{
            lstResultado = DBAtencionMedica.obtenerListaEstadoFuncionesBiologicas();
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Error al cargar listado de via de administracion.\n"+ex.toString(), null);
            lstResultado = new ArrayList();
        }
        return lstResultado;
    }
    
    public ArrayList cargarComboEstadoGeneral(JDialog pJDialog){
        ArrayList lstResultado = null;
        try{
            lstResultado = DBAtencionMedica.obtenerListaEstadoGeneralExaFisico();
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Error al cargar listado de via de administracion.\n"+ex.toString(), null);
            lstResultado = new ArrayList();
        }
        return lstResultado;
    }
    
    public ArrayList cargarComboTipoDiagnostico(JDialog pJDialog){
        ArrayList lstResultado = null;
        try{
            lstResultado = DBAtencionMedica.obtenerListaTipoDiagnostico();
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Error al cargar listado de via de administracion.\n"+ex.toString(), null);
            lstResultado = new ArrayList();
        }
        return lstResultado;
    }
    
    public ArrayList cargarComboViaAdministracion(JDialog pJDialog){
        ArrayList lstResultado = null;
        try{
            lstResultado = DBAtencionMedica.obtenerListaViaAdministracion();
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Error al cargar listado de via de administracion.\n"+ex.toString(), null);
            lstResultado = new ArrayList();
        }
        return lstResultado;
    }
    
    public ArrayList obtenerListaDiagnostico(){
        List lista;
        ArrayList lstRespuesta = new ArrayList();
        try{
            lista = DBAtencionMedica.obtenerListaDiagnostico();
            for(int i=0; i<lista.size();i++){
                ArrayList fila = new ArrayList();
                Map map = (Map)lista.get(i);
                fila.add((String)map.get("COD_CIE_10"));
                fila.add((String)map.get("DES_DIAGNOSTICO"));
                fila.add((String)map.get("COD_DIAGNOSTICO"));
                lstRespuesta.add(fila);
            }
        }catch(Exception ex){
            
        }
        return lstRespuesta;
    }
    
    public ArrayList obtenerListadoProductos(){
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        ArrayList lst = facade.obtenerListaProductos(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
        return lst;
    }
    
    public ArrayList obtenerProductoReceta(String pCodProd){
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        ArrayList lst = facade.obtenerProductoReceta(FarmaVariables.vCodGrupoCia, pCodProd);
        return lst;
    }

    /**
     * @author KMONCADA
     * @param beanAtencion
     * @return
     */
    public boolean grabarConsulta(JDialog pJDialog, BeanAtencionMedica beanAtencion, boolean grabaTemporal){
        boolean grabo = true;
        try{
            FacadeAtencioMedica facade = new FacadeAtencioMedica();
            grabo = facade.grabarConsulta(beanAtencion, FarmaVariables.vIdUsu, grabaTemporal);
            if(grabo && !grabaTemporal){
                /*FacadeVentaAtencionMedica facadeRAC = new FacadeVentaAtencionMedica();
                facadeRAC.registrarReceta(beanAtencion.getCodLocal(), beanAtencion.getTratamiento().getNroPedidoReceta());*/
            }
        }catch(Exception ex){
            grabo = false;
            FarmaUtility.showMessage(pJDialog, "Consulta Medica:\nError al registrar la consulta medica.\n"+ex.toString(), null);
        }
        return grabo;
    }

    
    public static boolean vExisteRecetaLocalBD(String pCodLocalReceta,String pNumPedReceta){
        try {
            if (DBReceta.isExisteRecetaBD_Local(FarmaVariables.vCodGrupoCia, pCodLocalReceta,
                                                pNumPedReceta).trim().equalsIgnoreCase("S"))
                return true;
            else
                return false;
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
            return false;
        }
    }
    
    public static boolean vIsPedidoConReceta(String pNumPedVta){
        try {
            if (DBReceta.isPedidoConReceta(pNumPedVta).trim().equalsIgnoreCase("S"))
                return true;
            else
                return false;
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
            return false;
        }
    }
    
    public static void grabaHistoricoRecetaRac(String pNumPedVta){
        String pCodLocal = "";
        String pNumReceta = "";
        String pCadena = "";
        if(vIsPedidoConReceta(pNumPedVta)){
            
            try {
                pCadena = DBReceta.getDatosRecetaDePedido(pNumPedVta);
            } catch (SQLException sqle) {
                // TODO: Add catch code
                sqle.printStackTrace();
            }
            
            pCodLocal = pCadena.split("@")[0].trim();
            pNumReceta = pCadena.split("@")[1].trim();
            
            FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
            facadeVtaAtenMed.registrarRecetaPedidoDet(pCodLocal, pNumReceta);    
        }
    }
    
    public static void grabaHistoricoEstadoCompRac(String pNumPedVta){
        UtilityAtencionMedica.registraEstadoCompAtencionMedica(FarmaVariables.vCodLocal,pNumPedVta);
        
    }
    
    public static void getHistoricoRecetaPedido(String pCodLocal,String pNumReceta){
            FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
            facadeVtaAtenMed.recuperarRecetaPedidoDet(pCodLocal, pNumReceta);    
    }
    
    public boolean crearAntecedentesPaciente(Frame myParentFrame, JDialog pJDialog, BeanPaciente beanPaciente){
        return mostrarPantallaAntecedentes(myParentFrame, pJDialog, beanPaciente, false, false, false);
    }
    
    public boolean editarAntecedentesPaciente(Frame myParentFrame, JDialog pJDialog, BeanPaciente beanPaciente){//, String pCodMedico){
        /*String pCodLocal = beanPaciente.getHistoriaClinia().getBeanAntecedente().getCodLocal();
        String pCodPaciente = beanPaciente.getHistoriaClinia().getBeanAntecedente().getCodPaciente();
        int nroSecuenciaHC = beanPaciente.getHistoriaClinia().getBeanAntecedente().getSecuencialHC();
        if(pCodLocal == null) pCodLocal = "";
        if(pCodPaciente == null) pCodPaciente = "";*/
        
        return mostrarPantallaAntecedentes(myParentFrame, pJDialog, beanPaciente, false, false, false);
    }
    
    public boolean verAntecedentePaciente(Frame myParentFrame, JDialog pJDialog, String pCodLocal, String pCodPaciente, int nroSecuenciaHC){
        //return mostrarPantallaAntecedentes(myParentFrame, pJDialog, pCodLocal, pCodPaciente, nroSecuenciaHC, "", true, false);
        return false;
    }
    
    public void verAntecedentePaciente(Frame myParentFrame, JDialog pJDialog, BeanPaciente beanPaciente){
        boolean edita = mostrarPantallaAntecedentes(myParentFrame, pJDialog, beanPaciente, true, false, false);
        if(edita){
            editarAntecedentesPaciente(myParentFrame, pJDialog, beanPaciente);
        }
    }
    
    public void verAntecedenteHistoricoPaciente(Frame myParentFrame, JDialog pJDialog, BeanPaciente beanPaciente){
        mostrarPantallaAntecedentes(myParentFrame, pJDialog, beanPaciente, true, false, true);
    }
    
    private boolean mostrarPantallaAntecedentes(Frame myParentFrame, JDialog pJDialog, BeanPaciente beanPaciente, 
                                                boolean modoVisual, boolean conectarRAC, boolean modoVistaHistorico){
        
        BeanHCAntecedentes beanAntecedente = beanPaciente.getHistoriaClinia().getBeanAntecedente();
        String pCodLocal = beanAntecedente.getCodLocal();
        String pCodPaciente = beanAntecedente.getCodPaciente();
        int nroSecuenciaHC = beanAntecedente.getSecuencialHC();
        String pCodMedico = beanAntecedente.getCodMedico();
        
        if(pCodLocal==null) pCodLocal = "";
        if(pCodPaciente==null) pCodPaciente = "";
        if(pCodMedico==null) pCodMedico = "";
        
        boolean cargo = true;
        if(conectarRAC){
            // recuperara el ultimo antecedente del paciente del RAC.
            FacadeVentaAtencionMedica facadeRac = new FacadeVentaAtencionMedica();
            if(!facadeRac.recuperarAntecedentes(pCodPaciente, nroSecuenciaHC, pCodLocal)){
                FarmaUtility.showMessage(pJDialog, "Antecedentes del paciente:\n" +
                                                   "No se pudo recuperar la información.\n"+
                                                   "Reintente, si problema persiste comuniquese con mesa de ayuda", null);
                return false;
            }
        }
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        //BeanHCAntecedentes antecedente = facade.obtenerAntecedenteHC(FarmaVariables.vCodGrupoCia, 
        BeanHCAntecedentes beanAntecedenteNuevo = facade.obtenerAntecedenteHC(FarmaVariables.vCodGrupoCia, 
                                                      pCodLocal,
                                                      pCodPaciente, 
                                                      nroSecuenciaHC);
        if(beanAntecedenteNuevo!=null){
            // cuando los antecedentes son de otro local consulta en el RAC y no hay en el local.
            if(beanAntecedenteNuevo.getSecuencialHC()==0 && 
               (pCodLocal.trim().length()!=0 && 
                !FarmaVariables.vCodLocal.equalsIgnoreCase(pCodLocal))){
                if(!conectarRAC)
                    cargo = mostrarPantallaAntecedentes(myParentFrame, pJDialog, beanPaciente, modoVisual, true, modoVistaHistorico);
                else 
                    cargo = false;
            }else{
                if(!modoVisual /*&& beanAntecedente.getSecuencialHC()==0*/){
                    beanAntecedenteNuevo.setCodLocal(pCodLocal);
                    beanAntecedenteNuevo.setCodMedico(pCodMedico);
                }
                beanPaciente.getHistoriaClinia().setBeanAntecedente(beanAntecedenteNuevo);
                DlgHistoriaClinicaAntecedentes dlgAntecedente = new DlgHistoriaClinicaAntecedentes(myParentFrame, "", true, beanPaciente);
                dlgAntecedente.setModoVisual(modoVisual);
                dlgAntecedente.setModoVisualHistorico(modoVistaHistorico);
                dlgAntecedente.setVisible(true);
                cargo = FarmaVariables.vAceptar;
                if(modoVistaHistorico){
                    beanPaciente.getHistoriaClinia().setBeanAntecedente(beanAntecedente);
                }
            }
        }else{
            cargo = false;
            FarmaUtility.showMessage(pJDialog, "Antecedentes:\nError al consultar antecedente del paciente.\n" +
                                               "Reintente, en caso persista comuniquese con Mesa de Ayuda!!!.", null);
        }
        return cargo;
    }
    
    public void realizarAtencionPaciente(Frame myParentFrame, JDialog pJDialog, String pCodPaciente, String nroAtencion, String pCodMedico, boolean isAtencionNueva, boolean isImpresion){
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        BeanPaciente paciente = facade.obtenerDatosPaciente(FarmaVariables.vCodGrupoCia, pCodPaciente);
        boolean continuar = false;
        if(paciente != null){
            DlgADMDatosPaciente dlgDatos  = new DlgADMDatosPaciente(myParentFrame, "", true, ConstantsCentroMedico.TIPO_PACIENTE_CONTINUADOR, paciente, TipoAtencionCM.CONSULTA);
            dlgDatos.setVisible(true);
            continuar = FarmaVariables.vAceptar;
            if(continuar){
                if(paciente.getNroHCAntecedente() == 0){
                    FarmaUtility.showMessage(pJDialog, "Consulta Médica:\nPaciente no cuenta con antecedentes médicos en su historia clínica.", null);
                    paciente.getHistoriaClinia().getBeanAntecedente().setCodLocal(FarmaVariables.vCodLocal);
                    paciente.getHistoriaClinia().getBeanAntecedente().setCodMedico(pCodMedico);
                    //continuar = editarAntecedentesPaciente(myParentFrame, pJDialog, FarmaVariables.vCodLocal, pCodPaciente, 0, pCodMedico);
                    //continuar = editarAntecedentesPaciente(myParentFrame, pJDialog, paciente);//, pCodMedico);
                    continuar = crearAntecedentesPaciente(myParentFrame, pJDialog, paciente);
                    if(!continuar){
                        paciente.setCodLocalAntecedente(null);
                    }
                }
                if(continuar){
                    if(isAtencionNueva)
                        crearAtencionMedica(myParentFrame, pJDialog, paciente, FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, nroAtencion, isImpresion);
                    else
                        modificarAtencionMedica(myParentFrame, pJDialog, paciente, FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, nroAtencion, isImpresion);
                }
            }
        }else{
                FarmaUtility.showMessage(pJDialog, "Consulta Medica:\nError al obtener datos del paciente.\n"+
                                                   "Reintente, si problema persiste comuniquese con Mesa de Ayuda.", null);
            }
        FarmaVariables.vAceptar = false;
    }
    
    
    public boolean crearAtencionMedica(Frame myParentFrame, JDialog pJDialog, BeanPaciente paciente, String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroAtencion, boolean isImpresion){
        return mostrarPantallaConsultaMedica(myParentFrame, pJDialog, paciente, pCodGrupoCia, pCodCia, pCodLocal, pNroAtencion, false, false, true, isImpresion);
    }
    
    public boolean modificarAtencionMedica(Frame myParentFrame, JDialog pJDialog, BeanPaciente paciente, String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroAtencion, boolean isImpresion){
        return mostrarPantallaConsultaMedica(myParentFrame, pJDialog, paciente, pCodGrupoCia, pCodCia, pCodLocal, pNroAtencion, false, false, false, isImpresion);
    }
    
    public boolean verAtencionMedica(Frame myParentFrame, JDialog pJDialog, String pCodGrupoCia, String pCodCia, String pCodLocal, String pNroAtencion, String pCodPaciente, boolean isImpresion){
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        BeanPaciente paciente = facade.obtenerDatosPaciente(FarmaVariables.vCodGrupoCia, pCodPaciente);
        return mostrarPantallaConsultaMedica(myParentFrame, pJDialog, paciente, pCodGrupoCia, pCodCia, pCodLocal, pNroAtencion, true, false, false, isImpresion);
    }
    
    private boolean mostrarPantallaConsultaMedica(Frame myParentFrame, JDialog pJDialog, BeanPaciente paciente, 
                                                  String pCodGrupoCia, String pCodCia, String pCodLocal, 
                                                  String pNroAtencion, boolean modoVisual, boolean consultaRAC, 
                                                  boolean isNuevaAtencion, boolean isImpresion){
        if(consultaRAC){
            FacadeVentaAtencionMedica facadeRac = new FacadeVentaAtencionMedica();
            if(!facadeRac.recuperarAntenciones(pCodCia, pCodLocal, pNroAtencion)){
                FarmaUtility.showMessage(pJDialog, "Consulta Medica:\n"+
                                                   "No se pudo obtener la informacion de la consulta medica.\n"+
                                                   "Reintente, en caso persista comuniquese con Mesa de Ayuda.", null);
                return false;
            }
        }
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        BeanAtencionMedica atencionMedica = facade.obtenerConsultaMedica(pCodGrupoCia, pCodCia, pCodLocal, pNroAtencion);
        paciente.getHistoriaClinia().setBeanConsultaMedica(atencionMedica);
        if(atencionMedica != null){
            DlgAtencionMedica dlgAtencionMedica = new DlgAtencionMedica(myParentFrame, "", true);
            dlgAtencionMedica.setBeanPaciente(paciente);
            dlgAtencionMedica.setBeanAtencionMedica(atencionMedica);
            dlgAtencionMedica.setModoVisual(modoVisual);
            dlgAtencionMedica.setAtencionNueva(isNuevaAtencion);
            dlgAtencionMedica.setBandImpresion(isImpresion);
            dlgAtencionMedica.setAtencion(pNroAtencion);
            dlgAtencionMedica.setVisible(true);
            return FarmaVariables.vAceptar;
        }else{
            FarmaUtility.showMessage(pJDialog, "Consulta Medica:\nError al obtener datos de la consulta medica.\n"+
                                               "Reintente, si problema persiste comuniquese con Mesa de Ayuda.", null);
            return false;
        }
    }
    
    public ArrayList<OptionComboBox> obtenerListaComboCheckBox(int codMaestro){
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        return facade.obtenerListaComboCheckBox(codMaestro);
    }
    
    /**
     * @author KMONCADA
     * @since 13.09.2016
     * @param beanAntecedente
     * @return
     */
    public boolean grabarAntecedentePaciente(JDialog pJDialog, BeanHCAntecedentes beanAntecedente){
        boolean grabo = true;
        try{
            FacadeAtencioMedica facade = new FacadeAtencioMedica();
            facade.grabarAntecedenteHC(beanAntecedente, FarmaVariables.vIdUsu);
            FarmaUtility.showMessage(pJDialog, "Antecedentes de Paciente:\nSe Registro exitosamente los antecedentes.", null);
        }catch(Exception ex){
            grabo = false;
            FarmaUtility.showMessage(pJDialog, "Antecedentes de Paciente:\nError al registrar la consulta medica.\n"+ex.toString(), null);
        }
        return grabo;
    }
    
    public int listarAntecedentesPaciente(JDialog pJDialog, FarmaTableModel model, String pCodPaciente, String pFechaInicio, String pFechaFin){
        int rspta = 0;
        model.clearTable();
        boolean conectoCentral = true;
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        // realiza la conexion con RAC
        ArrayList<ArrayList<String>> lista = facade.obtenerListaAntecedentesHCPaciente(FarmaVariables.vCodGrupoCia, pCodPaciente, pFechaInicio, pFechaFin, true);
        if(lista == null){
            conectoCentral = false;
            rspta = 1;
        }else if(lista.isEmpty()){
            rspta = 2;
            conectoCentral = false;
        }
        if(!conectoCentral){
            model.data = facade.obtenerListaAntecedentesHCPaciente(FarmaVariables.vCodGrupoCia, pCodPaciente, pFechaInicio, pFechaFin, false);
        }else{
            model.data = lista;
        }
        if(model.data.size()==0)
            FarmaUtility.showMessage(pJDialog, "Historial de Antecedentes:\nNo se han encontrado resultados en la búsqueda.", null);
        model.fireTableDataChanged();
        return rspta;
    }
    
    public int listarAtencionesMedicas(JDialog pJDialog, FarmaTableModel model, String pCodPaciente, String pFechaInicio, String pFechaFin){
        int rspta = 0;
        model.clearTable();
        boolean conectoCentral = true;
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        // realiza la conexion con RAC
        ArrayList<ArrayList<String>> lista = facade.obtenerListaAtencionesMedicas(FarmaVariables.vCodGrupoCia, pCodPaciente, pFechaInicio, pFechaFin, true);
        if(lista == null){
            conectoCentral = false;
            rspta = 1;
        }else if(lista.isEmpty()){
            rspta = 2;
            conectoCentral = false;
        }
        if(!conectoCentral){
            model.data = facade.obtenerListaAtencionesMedicas(FarmaVariables.vCodGrupoCia, pCodPaciente, pFechaInicio, pFechaFin, false);
        }else{
            model.data = lista;
        }
        if(model.data.size()==0)
            FarmaUtility.showMessage(pJDialog, "Historial de Atenciones:\nNo se han encontrado resultados en la búsqueda.", null);
        model.fireTableDataChanged();
        return rspta;
    }
    
    public boolean imprimirRecetaMedica(JDialog pJDialog, String pNroConsulta){
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        boolean imprimeDigital = false;
        try{
            imprimeDigital = facade.imprimeRecetaFormatoDigital();
        }catch(Exception ex){
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Atencion Medica:\nError al obtener indicador de tipo de impresión\n"+
                                               "Reintente, si problema persiste comuniquese con Mesa de Ayuda.", null);
            return false;
        }
        
        log.info("[CENTRO MEDICO] IMPRESION DE RECETA: IMPRESION EN LASER -->"+imprimeDigital);
        if(imprimeDigital){
            String nombreImpresora = facade.obtenerNombreImpresoraRecetaDigital(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, FarmaVariables.vIpPc);
            if(nombreImpresora == null){
                FarmaUtility.showMessage(pJDialog, "Impresión de Receta Medica:\n"+
                                                   "Error al obtener nombre de impresora configurada.\n"+
                                                   "Reintente, si problema persiste comuniquese con Mesa de Ayuda.", null);
                return false;
            }else if(nombreImpresora.trim().length()==0){
                FarmaUtility.showMessage(pJDialog, "Impresión de Receta Medica:\n"+
                                                   "No se encuentra configurada la impresora.\n"+
                                                   "Verifique en la opción de Mantenimiento de Maquina IP.", null);
                return false;
            }else{
                FarmaUtility.showMessage(pJDialog, "Impresión de Receta:\n"+
                                                   "Se va imprimir en la siguiente Impresora "+nombreImpresora+
                                                   "\nVerifique que se encuentre encedendida y con papel", null);
                
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
                PrintService selectedService = null;
                
                if(services.length != 0 || services != null){
                    for(PrintService service : services){
                        String existingPrinter = service.getName().toLowerCase();
                        if(existingPrinter.equalsIgnoreCase(nombreImpresora)){
                            selectedService = service;
                            break;
                        }
                    }

                    if(selectedService != null){
                        try{
                            printerJob.setPrintService(selectedService);
                            // KMONCADA 25.10.2016 [CENTRO MEDICO] OBTENER RUTA DE FORMATOS JASPER
                            String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
                            String carpetaFormatoJasper = DBPtoVenta.getDirectorioFormatosJasper();
                            
                            //String rutaJasper = FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formatos_jasper/Receta.jasper").getPath();
                            //String rutaJasper = FrmEconoFar.class.getResource("/mifarma/ptoventa/formatos_jasper").getPath()+"//";
                            String rutaJasper = carpetaRaiz+"//"+carpetaFormatoJasper+"//";
                            
                            FileInputStream fis = new FileInputStream(rutaJasper+"Receta.jasper");
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
                            
                            Map<String,Object> map = new HashMap<String, Object>();
                            map.put("cCodGrupoCia", FarmaVariables.vCodGrupoCia);
                            map.put("cCodCia", FarmaVariables.vCodCia);
                            map.put("cCodLocal", FarmaVariables.vCodLocal);
                            map.put("cNroAtencion", pNroConsulta);
                            map.put("SUBREPORT_DIR", rutaJasper);
                            
                            // compile report
                            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, FarmaConnection.getConnection());
                            
                            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                            MediaSizeName mediaSizeName = MediaSize.ISO.A4.getMediaSizeName(); 
                            printRequestAttributeSet.add(mediaSizeName);  
                            printRequestAttributeSet.add(new Copies(1));
                            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, selectedService);
                            exporter.exportReport();
                        } catch (Exception ex) {
                            log.error("", ex);
                            FarmaUtility.showMessage(pJDialog, "Impresión de Receta Medica Formato Digital:\n"+
                                                               "Error al realizar la impresion de la receta..\n"+
                                                               "Reintente, en caso persista el problema comuniquese con Mesa de Ayuda.", null);
                            return false;
                        }
                    }else{
                        FarmaUtility.showMessage(pJDialog, "Impresión de Receta Medica Formato Digital:\n"+
                                                           "No se ha ubicado la impresiora"+nombreImpresora+" en las impresoras instaladas en la PC.\n"+
                                                           "Comuniquese con Mesa de Ayuda.", null);
                        return false;
                    }
                }else{
                    FarmaUtility.showMessage(pJDialog, "Impresión de Receta Medica Formato Digital:\n"+
                                                       "No se han podido obtener las impresoras instaladas en la PC\n"+
                                                       "Comuniquese con Mesa de Ayuda.", null);
                    return false;
                }
            }
        }else{
            // impresion en formato termico
            List lstImpresion = facade.obtenerFormatoRecetaTermica(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, pNroConsulta);
            try{
                FarmaUtility.showMessage(pJDialog, "Impresión de Receta:\n"+
                                                   "Se va imprimir en la impresora térmica, verifique se encuentre encendida.", null);
                if(lstImpresion!=null){
                    (new UtilityImpCompElectronico()).impresionTermica(lstImpresion, null);
                }else{
                    FarmaUtility.showMessage(pJDialog, "Impresión de Receta Medica:\n"+
                                                       "Error al obtener formato de receta para imprimir en termica.\n"+
                                                       "Reintente, si problema persiste comuniquese con Mesa de Ayuda.", null);
                    return false;
                }
            }catch(Exception ex){
                log.error("", ex);
                FarmaUtility.showMessage(pJDialog, "Impresión de Receta Medica:\n"+
                                                   "Error al obtener formato de receta para imprimir en termica.\n"+
                                                   ex.toString(), null);
                return false;
            }
        }
        return true;
    }
    
    public int obtenerCantidadDiasVigencia(JDialog pJDialog){
        int cantidad = 0;
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        cantidad = facade.obtenerCantidadDiasVigenciaReceta();
        if(cantidad==-1){
            FarmaUtility.showMessage(pJDialog, "No se pudo obtener la cantidad de días de vigencia de la receta.\n"+
                                               "Se considerara 1 (un) día.", null);
            cantidad = 1;
        }
        return cantidad;
    }
    
    public static boolean getComprobanteConsultaMedica(String pTipComp,String pNumCompPago){
        FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
        boolean flag = facadeVtaAtenMed.recuperarVenta(pTipComp, pNumCompPago);
        return flag;
    }
    
    
    public static void recuperaEstadosComp(String pcodLocal,String pNumPedVta){
            FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
            facadeVtaAtenMed.recuperarEstadoCompAten(pcodLocal,pNumPedVta);
    }
    
    public static boolean registraHClinicaPaciente(String pCodPaciente){
        FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
        boolean flag = facadeVtaAtenMed.registrarHistoriaClinica(pCodPaciente);
        return flag;
    }
    
    public static boolean registraEstadoCompAtencionMedica(String pcodLocal,String pNumPedVta){
        FacadeVentaAtencionMedica facadeVtaAtenMed = new FacadeVentaAtencionMedica();
        boolean flag = facadeVtaAtenMed.registrarEstadoCompAten(pcodLocal,pNumPedVta);
        return flag;
    }
    
    public String validarDatosAccesoConsulta(String tipoColegio, String nroCmp, String nroDni) throws Exception{
        String codMedico = "";
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        try{
            codMedico = facade.isValidaDatosAccesoMedico(tipoColegio, nroCmp, nroDni);
        }catch(Exception ex){
            codMedico = "";
            throw new Exception(ex.getCause().getCause());
        }
        return codMedico;
    }
    
    public boolean procesarImpresion(String cod_Paciente, String numAtencion){
        try{
            String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
            String carpetaFormatoJasper = DBPtoVenta.getDirectorioFormatosJasper();
            String carpetaLogo = DBPtoVenta.getDirectorioImagenes();
            
            String rutaJasper = carpetaRaiz+"//"+carpetaFormatoJasper+"//";
            String rutaLogo = carpetaRaiz+"//"+carpetaLogo+"//";
            
            FileInputStream fis = new FileInputStream(rutaJasper+"Reporte_HC_Page01.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            
            Map<String,Object> map = cargarParametros(rutaJasper, cod_Paciente, rutaLogo, numAtencion);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map);
            JRExporter exporter = new JRPdfExporter(); 
            
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            String carpetaPdf = DBPtoVenta.getDirectorioPdfsGenerados();
            String rutaPdf = carpetaRaiz+"//"+carpetaPdf+"//";
            File file = new File(rutaPdf);
            if(!file.exists()){
                file.mkdir();
            }
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaPdf + cod_Paciente + "_" + numAtencion + "_" + obtenerFechaActual(false) + ".pdf"));
            exporter.exportReport(); 
            JasperViewer.viewReport(jasperPrint, false);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
        
    private Map<String, Object> cargarParametros(String rutaJasper, String codPaciente, String rutaLogo, String numAtencion){
        Map<String, Object> map = new HashMap<String, Object>();
        BeanPaciente paciente=new BeanPaciente();  
        
        FacadeAtencioMedica facade = new FacadeAtencioMedica();
        paciente = facade.obtenerDatosPaciente(FarmaVariables.vCodGrupoCia, codPaciente);
        
        BeanAtencionMedica atencionMedica = facade.obtenerConsultaMedica(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodCia, FarmaVariables.vCodLocal, numAtencion);
        paciente.getHistoriaClinia().setBeanConsultaMedica(atencionMedica);
        
        map.put("RutaLogo", rutaLogo + "Logo_reporte.PNG");
        map.put("HoraReporte", obtenerHoraActual());
        map.put("FechaReporte", obtenerFechaActual(true));
        map.put("Nro_HC", paciente.getNumHistCli());
        /*-----------------------------------------------*/
        map.put("P_DatosPersonales", paciente.getNombre() + " " + paciente.getApellidoPaterno() + " " + paciente.getApellidoMaterno());
        map.put("P_Dni", paciente.getNumDocumento());
        map.put("P_DatosAcompañante", paciente.getNomAcom());
        map.put("P_DniAcompañante", paciente.getNumDocAcom());
        if(paciente.getSexo().equalsIgnoreCase("M")){
            map.put("P_Sexo", "Masculino");
        }else if(paciente.getSexo().equalsIgnoreCase("F")){
            map.put("P_Sexo", "Femenino");
        }
        map.put("P_EstadoCivil", obtenerEstCivil(paciente.getEstCivil()));
        map.put("P_Raza", "");
        map.put("P_Religion", "");
        map.put("P_Edad", obtenerEdad(paciente.getFecNacimiento()));
        map.put("P_FechaNac", paciente.getFecNacimiento());
        map.put("P_LugarNac", obtenerLugar(paciente.getDepLugNac(), paciente.getPvrLugNac(), paciente.getDisLugNac()));
        map.put("P_Procedencia", obtenerLugar(paciente.getDepLugPro(), paciente.getPvrLugPro(), paciente.getDisLugPro()));
        map.put("P_Direccion", paciente.getDireccion());
        map.put("P_GradoInstruccion", obtenerGradoInst(paciente.getGradoInstruccion()));
        map.put("P_CentroEducativoLaboral", paciente.getCentroEduLab());
        map.put("P_GrupoSanguineo", obtenerGrupoSanguineo(paciente.getGrupoSan()));
        map.put("P_FactorRH", obtenerFactorRH(paciente.getFactorRH()));
        map.put("P_telefonoFijo", paciente.getTelFijo());
        map.put("P_Celular", paciente.getTelCelular());
        map.put("P_Correo", paciente.getEmail());
        map.put("SUBREPORT_DIR", rutaJasper);
        /*-----------------------------------------------*/
        if(paciente.getHistoriaClinia().getBeanAntecedente().getSecuencialHC()==0){
            BeanHCAntecedentes antecedente = new BeanHCAntecedentes();
            antecedente.setCodGrupoCia(paciente.getCodGrupoCia());
            antecedente.setCodLocal(paciente.getCodLocalAntecedente());
            antecedente.setCodPaciente(paciente.getCodPaciente());
            antecedente.setSecuencialHC(paciente.getNroHCAntecedente());
            antecedente.setCodMedico(atencionMedica.getCodMedico());
            paciente.getHistoriaClinia().setBeanAntecedente(antecedente);
        }
        BeanHCAntecedentes beanAntecedente = paciente.getHistoriaClinia().getBeanAntecedente();
        String pCodLocal = beanAntecedente.getCodLocal();
        String pCodPaciente = beanAntecedente.getCodPaciente();
        int nroSecuenciaHC = beanAntecedente.getSecuencialHC();
        if(pCodLocal==null) pCodLocal = "";
        BeanHCAntecedentes beanAntecedenteNuevo = facade.obtenerAntecedenteHC(FarmaVariables.vCodGrupoCia, 
                                                      pCodLocal,
                                                      pCodPaciente, 
                                                      nroSecuenciaHC);
        paciente.getHistoriaClinia().setBeanAntecedente(beanAntecedenteNuevo);
        String prenatal = "";
        String parto = "";
        String inmunizacion = "";
        String otrosHabitos = "";
        String a = "No", b = "No", c = "No", d = "No";
        for(int i = 0; i < paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().size(); i++){
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodGrupo() == 31){
                prenatal = prenatal + paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getDescAnteFisiologico() + " ";
            }
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodGrupo() == 32){
                parto = parto +  paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getDescAnteFisiologico() + " ";
            }
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodGrupo() == 33){
                inmunizacion = inmunizacion +  paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getDescAnteFisiologico() + " ";
            }
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodAnteFisiologico() == 231){
                a = "Si";
            }
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodAnteFisiologico() == 232){
                b = "Si";
            }
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodAnteFisiologico() == 233){
                c = "Si";
            }
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodAnteFisiologico() == 234){
                d = "Si";
            }
            if(paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getOpcionOtro() != null || 
            paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getCodAnteFisiologico() == 236){
                otrosHabitos = otrosHabitos +  paciente.getHistoriaClinia().getBeanAntecedente().getFisiologico().get(i).getDescAnteFisiologico() + " ";
            }
        }
        map.put("An_Prenatal", prenatal);
        map.put("An_Parto", parto);
        map.put("An_inmunizacion", inmunizacion);
        map.put("An_Tabaco", a);
        map.put("An_OH", b);
        map.put("An_Droga", c);
        map.put("An_Cafe", d);
        map.put("An_Medicamento", paciente.getHistoriaClinia().getBeanAntecedente().getGenerales().getMedicamentos());
        map.put("An_OtrosHabitos", otrosHabitos);
        map.put("An_Menarquia", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getEdadMenarquia() <= 0)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getEdadMenarquia()+"");
        map.put("An_RC", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getRcMenstruacion() <= 0)? "": 
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getRcMenstruacion() + " / " + 
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getRcCicloMenstrual());
        map.put("An_FUR", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getFur() == null)? "": 
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getFur());
        map.put("An_FPP", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getFpp() == null)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getFpp());
        map.put("An_RS", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getRs() <= 0)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getRs()+"");
        if(paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getDisminorrea() != null && 
           paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getDisminorrea().equalsIgnoreCase("S")){
            map.put("An_Dismenorrea", "Si");
        }else{
            map.put("An_Dismenorrea", "No");
        }
        map.put("An_G", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getNroGestaciones() <= 0)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getNroGestaciones()+""); //Averiguar de donde viene este campo
        map.put("An_P", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getParidad() == null)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getParidad()); //Averiguar de donde viene este campo
        map.put("An_FUP", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getFup() == null)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getFup());
        map.put("An_Cesarea", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getNroCesareas() <= 0)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getNroCesareas()+"");
        map.put("An_PAP", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getPap() == null)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getPap());
        map.put("An_mamografia", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getMamografia() == null)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getMamografia());
        map.put("An_MAC", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getMac() == null)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getMac());
        map.put("An_Otros", (paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getOtros() == null)? "":
                paciente.getHistoriaClinia().getBeanAntecedente().getGinecologico().getOtros());
        map.put("An_Hepatitis", "");
        map.put("An_Febril", "");
        map.put("An_Alergia", "");
        map.put("An_TBC", "");
        map.put("An_Cirugia", "");
        map.put("An_Hipertension", "");
        map.put("An_RAM", paciente.getHistoriaClinia().getBeanAntecedente().getGenerales().getRam());
        map.put("An_NombreMedicamento", "");
        String patologicos = "";
        String familiares = "";
        for(int i = 0; i < paciente.getHistoriaClinia().getBeanAntecedente().getPatologicos().size(); i++){
            if(paciente.getHistoriaClinia().getBeanAntecedente().getPatologicos().get(i).getTipoPatologico() != null){
                if(paciente.getHistoriaClinia().getBeanAntecedente().getPatologicos().get(i).getTipoPatologico().equalsIgnoreCase("PA")){
                    patologicos = patologicos + paciente.getHistoriaClinia().getBeanAntecedente().getPatologicos().get(i).getNombreDiagnostico() + " ";
                }
                if(paciente.getHistoriaClinia().getBeanAntecedente().getPatologicos().get(i).getTipoPatologico().equalsIgnoreCase("FA")){
                    familiares = familiares +  paciente.getHistoriaClinia().getBeanAntecedente().getPatologicos().get(i).getNombreDiagnostico() + " ";
                }
            }
        }
        map.put("An_Patologico", patologicos);
        map.put("An_Familiar", familiares);
        map.put("An_Ocupacional", paciente.getHistoriaClinia().getBeanAntecedente().getGenerales().getOcupacionales());
        /*-----------------------------------------------*/
        map.put("En_SegunInformante", obtenerTipoInf(paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getTipoInformante()));
        map.put("En_Tiempo", paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getTiempoEnfermedad());
        map.put("En_FormaInicio", paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getFormaInicio());
        map.put("En_Sintomas", paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getSignos());
        map.put("En_RelatoCronologico", paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getRelatoCronologico());
        /*-----------------------------------------------*/
        map.put("Bio_Apetito", obtenerDescFuncion(paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getTipoApetito()));
        map.put("Bio_Sed", obtenerDescFuncion(paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getTipoSed()));
        map.put("Bio_Sueño", obtenerDescFuncion(paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getTipoSuenio()));
        map.put("Bio_Orina", obtenerDescFuncion(paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getTipoOrina()));
        map.put("Bio_Deposicion", obtenerDescFuncion(paciente.getHistoriaClinia().getBeanConsultaMedica().getEnfermedadActual().getTipoDeposicion()));
        /*-----------------------------------------------*/
        /*--------PARAMETROS PARA EL SUBREPORTE----------*/
        /*-----------------------------------------------*/
        map.put("EnAc_PA", (paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalPA1() <= 0)? "": 
                paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalPA1()+"");
        map.put("EnAc_MMHG", (paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalPA2() <= 0)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalPA2()+"");
        map.put("EnAc_FR", (paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalFR() <= 0)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalFR()+"");
        map.put("EnAc_FC", (paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalFC() <= 0)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalFC()+"");
        map.put("EnAc_T", (paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalT() <= 0)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalT()+"");
        map.put("EnAc_PESO", (paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalPeso() <= 0)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionVitalPeso()+"");
        map.put("EnAc_TALLA", (paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionvitalTalla() <= 0)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getTriaje().getFuncionvitalTalla()+"");
        /*-----------------------------------------------*/
        map.put("ExFi_EstGeneral", obtenerEstadoGral(paciente.getHistoriaClinia().getBeanConsultaMedica().getExamenFisico().getEstadoGeneral()));
        map.put("ExFi_EstConciencia", paciente.getHistoriaClinia().getBeanConsultaMedica().getExamenFisico().getEstadoConciencia());
        map.put("ExFi_Fisico", paciente.getHistoriaClinia().getBeanConsultaMedica().getExamenFisico().getExamenFisicoDirigido());
        /*-----------------------------------------------*/
        int nroRegistros_P = paciente.getHistoriaClinia().getBeanConsultaMedica().getDiagnostico().size();
        ArrayList<BeanAtMedDiagnostico> listDiagnosticoMed = new ArrayList<BeanAtMedDiagnostico>();
        for(int i = 0; i < nroRegistros_P; i++){
            if(paciente.getHistoriaClinia().getBeanConsultaMedica().getDiagnostico().get(i).getTipoDiagnostico().equalsIgnoreCase("P")){
                listDiagnosticoMed.add(paciente.getHistoriaClinia().getBeanConsultaMedica().getDiagnostico().get(i));
            }
        }
        if(listDiagnosticoMed.size() > 0){
            map.put("DiPr_1",listDiagnosticoMed.get(0).getCodCIE());
            map.put("DiPr_2","1");
            map.put("DiPr_3",listDiagnosticoMed.get(0).getDescripcionDiagnostico());
            if(listDiagnosticoMed.size() > 1){
                map.put("DiPr_4",listDiagnosticoMed.get(1).getCodCIE());
                map.put("DiPr_5","2");
                map.put("DiPr_6",listDiagnosticoMed.get(1).getDescripcionDiagnostico());
                if(listDiagnosticoMed.size() > 2){
                    map.put("DiPr_7",listDiagnosticoMed.get(2).getCodCIE());
                    map.put("DiPr_8","3");
                    map.put("DiPr_9",listDiagnosticoMed.get(2).getDescripcionDiagnostico());
                }else{
                    map.put("DiPr_7","");
                    map.put("DiPr_8","");
                    map.put("DiPr_9","");
                }
            }else{
                map.put("DiPr_4","");
                map.put("DiPr_5","");
                map.put("DiPr_6","");
                map.put("DiPr_7","");
                map.put("DiPr_8","");
                map.put("DiPr_9","");
            }
        }else{
            map.put("DiPr_1","");
            map.put("DiPr_2","");
            map.put("DiPr_3","");
            map.put("DiPr_4","");
            map.put("DiPr_5","");
            map.put("DiPr_6","");
            map.put("DiPr_7","");
            map.put("DiPr_8","");
            map.put("DiPr_9","");
        }
        /*-----------------------------------------------*/
        map.put("ExAu_Laboratorio", (paciente.getHistoriaClinia().getBeanConsultaMedica().getExamenesAuxiliares().getLaboratorio() == null)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getExamenesAuxiliares().getLaboratorio());
        map.put("ExAu_Imagen", (paciente.getHistoriaClinia().getBeanConsultaMedica().getExamenesAuxiliares().getImagenes() == null)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getExamenesAuxiliares().getImagenes());
        map.put("ExAu_Otro", (paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getTransferencia() == null)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getTransferencia());
        map.put("ExAu_Procedimiento", (paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getProcedimiento() == null)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getProcedimiento());
        map.put("ExAu_Interconsulta", (paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getInterconsulta() == null)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getInterconsulta());
        /*-----------------------------------------------*/
        int nroRegistros_D = paciente.getHistoriaClinia().getBeanConsultaMedica().getDiagnostico().size();
        ArrayList<BeanAtMedDiagnostico> listDiagnosticoMed_D = new ArrayList<BeanAtMedDiagnostico>();
        for(int i = 0; i < nroRegistros_D; i++){
            if(paciente.getHistoriaClinia().getBeanConsultaMedica().getDiagnostico().get(i).getTipoDiagnostico().equalsIgnoreCase("D")){
                listDiagnosticoMed_D.add(paciente.getHistoriaClinia().getBeanConsultaMedica().getDiagnostico().get(i));
            }
        }
        if(listDiagnosticoMed_D.size() > 0){
            map.put("DiDe_1", listDiagnosticoMed_D.get(0).getCodCIE());
            map.put("DiDe_2", "1");
            map.put("DiDe_3", listDiagnosticoMed_D.get(0).getDescripcionDiagnostico());
        }else{
            map.put("DiDe_1","");
            map.put("DiDe_2","");
            map.put("DiDe_3","");
        }
        
        /*-----------------------------------------------*/
        Tratamiento trt = null;
        List<Tratamiento> listTratamiento = new ArrayList<Tratamiento>();
        for(int i = 0; i < paciente.getHistoriaClinia().getBeanConsultaMedica().getTratamiento().getReceta().getCantidadItems(); i++){
            trt = new Tratamiento();
            trt.setNro("" + (i + 1));
            trt.setMedicamento(paciente.getHistoriaClinia().getBeanConsultaMedica().getTratamiento().getReceta().getDetalles().get(i).getDescProducto());
            trt.setConcentracion("" + paciente.getHistoriaClinia().getBeanConsultaMedica().getTratamiento().getReceta().getDetalles().get(i).getDuracionToma());
            trt.setDosis(paciente.getHistoriaClinia().getBeanConsultaMedica().getTratamiento().getReceta().getDetalles().get(i).getDosis());
            trt.setFrecuencia("" + paciente.getHistoriaClinia().getBeanConsultaMedica().getTratamiento().getReceta().getDetalles().get(i).getFrecuenciaToma());
            trt.setVia("" + paciente.getHistoriaClinia().getBeanConsultaMedica().getTratamiento().getReceta().getDetalles().get(i).getDescViaAdministracion());
            listTratamiento.add(trt);
        }
        map.put("trt", new JRBeanCollectionDataSource(listTratamiento));

         /*-----------------------------------------------*/
        map.put("Trat_MedHiguienicas","");//No se sabe de donde sacar esta informacion
        map.put("Trat_MedPreventivas","");//No se sabe de donde sacar esta informacion
        map.put("Trat_Transferencia","");//No se sabe de donde sacar esta informacion
        map.put("Trat_DescMedPeriodo", (paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getDescansoMedicoInicio() == null)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getDescansoMedicoInicio() + " - " + 
                paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getDescansoMedicoFin());
        
        Calendar cal1=Calendar.getInstance();
        Calendar cal2=Calendar.getInstance();
        DateFormat formatter;
        Date date1, date2;
        try { 
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            if(paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getDescansoMedicoInicio() != null &&
            paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getDescansoMedicoFin() != null){
                date1 = (Date)formatter.parse( paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getDescansoMedicoInicio()); 
                date2 = (Date)formatter.parse( paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getDescansoMedicoFin()); 
                cal1.setTime(date1);
                cal2.setTime(date2);
                map.put("Trat_DescMedDias",FarmaUtility.diferenciaEnDias(cal2, cal1) + "");
            }else{
                map.put("Trat_DescMedDias","0");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        map.put("Trat_ProxCita",(paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getProximaCita() == null)? "":
                paciente.getHistoriaClinia().getBeanConsultaMedica().getOtros().getProximaCita());
        return map;
    }
    
    private static String obtenerFechaActual(boolean flag){
        Date fechaActual = new Date();
        if(flag){//Para fechas dentro del reporte pdf
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(fechaActual);
        }else{//Para fechas en el nombre del reporte pdf
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            return dateFormat.format(fechaActual);
        }
    }
    
    private static String obtenerHoraActual(){
        Date horaActual = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(horaActual);
    }
    
    private String obtenerEdad(String fecNacimiento){
        if(fecNacimiento.length() <= 0){
            return "";
        }
        ArrayList lista;
        try {
            lista = UtilityAdmisionMedica.obtenerEdad(fecNacimiento);
        } catch (SQLException e) {
            return "";
        }
        return ((String)((ArrayList)lista.get(0)).get(0)).trim();
    }
    
    private String obtenerEstCivil(String codEstCivil){
        if(codEstCivil.length() > 0){
            return UtilityAdmisionMedica.obtenerEstCivil(codEstCivil);
        }else{
            return "";
        }
    }
    
    private String obtenerGradoInst(String codGradoInst){
        if(codGradoInst.length() > 0){
            return UtilityAdmisionMedica.obtenerGradoInst(codGradoInst);
        }else{
            return "";
        }
    }
    
    private String obtenerGrupoSanguineo(String codGrupoSanguineo){
        String resul = "";
        if(codGrupoSanguineo.length() > 0){
            resul = UtilityAdmisionMedica.obtenerGrupoSanguineo(codGrupoSanguineo);
            if(resul == null){
                resul = "";
            }
        }
        return resul;
    }
    
    private String obtenerTipoInf(String tipoInf){
        if(tipoInf.length() > 0){
            return UtilityAdmisionMedica.obtenerTipoInf(tipoInf);
        }else{
            return "";
        }
    }
    
    private String obtenerFactorRH(String codFactorRH){
        String result = "";
        if(codFactorRH.length() > 0){
            result =  UtilityAdmisionMedica.obtenerFactorRH(codFactorRH);
            if(result == null){
                result = "";
            }
        }
        return result;
    }
    
    private String obtenerDescFuncion(String funcion){
        String result = "";
        if(funcion.length() > 0){
            result = UtilityAdmisionMedica.obtenerDescFuncion(funcion);
            if(result == null){
                return "";
            }
        }
        return result;
    }    
    
    private String obtenerEstadoGral(String estado){
        if(estado.length() > 0){
            return UtilityAdmisionMedica.obtenerEstGral(estado);
        }else{
            return "";
        }
    }    
    
    private String obtenerLugar(String dep, String pro, String dis){
        String resul = "";
        if(dep.length() > 0 && pro.length() > 0 && dis.length() > 0){
            resul = UtilityAdmisionMedica.obtenerLugar(dep, pro, dis);
            if(resul == null){
                resul = "";
            }
        }
        return resul;
    }
    
}
