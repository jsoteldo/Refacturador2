package mifarma.ptoventa.convenioBTLMF.reference;

import farmapuntos.orbis.WSClientConstans;

import java.awt.Frame;
import java.awt.event.KeyEvent;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.print.PrintService;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaPrintService;
import mifarma.common.FarmaPrintServiceTicket;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.UtilityCPE;
import mifarma.cpe.reference.DBCPElectronico;

import mifarma.electronico.UtilityImpCompElectronico;
import mifarma.electronico.impresion.dao.ConstantesDocElectronico;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.administracion.impresoras.reference.DBImpresoras;
import mifarma.ptoventa.administracion.impresoras.reference.FormatoImpresion;
import mifarma.ptoventa.administracion.impresoras.reference.Impresora;
import mifarma.ptoventa.administracion.impresoras.reference.ImpresoraTicket;
import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.DocumentRendererConsejo;
import mifarma.ptoventa.caja.reference.UtilityCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convenioBTLMF.DlgProcesarDatosConvenio;
import mifarma.ptoventa.despacho.reference.UtilityDespacho;
import mifarma.ptoventa.hilos.SubProcesosConvenios;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;
import mifarma.ptoventa.ventas.DlgMensajeImpresion;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityConvenioBTLMF {

    private static final Logger log = LoggerFactory.getLogger(UtilityConvenioBTLMF.class);

    private static int acumuCorreGuia = 0;
    private static int acumuCorreFac = 0;
    private static int acumuCorreTik = 0;
    private static int acumuCorreBol = 0;

    public UtilityConvenioBTLMF() {
        super();

    }

    public UtilityConvenioBTLMF(int pAnchoLinea) {
        super();

    }

    /*** INICIO ARAVELLO 11/10/2019 ***/

    public static boolean indDatoConvenio(String pCodigoConvenio) {
        boolean resul = false;
        String indConv = "";
        String indProdConv = "";

        try {
            indConv = DBConvenioBTLMF.pideDatoConvenio(pCodigoConvenio);
            log.debug("INDICADOR PIDE DATO CONV = " + indConv);
            if (indConv.equalsIgnoreCase("S")) {
                resul = true;
            } else if (indConv.equalsIgnoreCase("T")) {
                //indProdConv =  DBConvenioBTLMF.existeProdConvenio();

                //            	  if (indProdConv.equalsIgnoreCase("N"))
                //            	  {
                //            		   FarmaUtility.showMessage(pDialogo, "No hay productos cubiertos para el Convenio. e\n", pObjeto);
                //            		   resul = false;
                //                   	   VariablesConvenioBTLMF.vAceptar = false;
                //
                //            	  }
                //            	  else
                // {
                resul = false;
                VariablesConvenioBTLMF.vAceptar = true;

                //}
            } else if (indConv.equalsIgnoreCase("P")) {
                VariablesConvenioBTLMF.vAceptar = true;
                resul = false;
                //FarmaUtility.showMessage(pDialogo, "El convenio no tiene datos registrados. e\n", pObjeto);
            }

        } catch (SQLException sql) {
            log.error("", sql);
//            FarmaUtility.showMessage(pDialogo, "Error en buscar si debe mostrase datos convenios\n" +
//                    sql.getMessage(), pObjeto);
            resul = true;
        }
        return resul;

    }
    /*** FIN    ARAVELLO 11/10/2019 ***/
    /*** INICIO ARAVELLO 11/10/2019 ***/
    public static List listaDatosConvenio(String pCodConvenio) {
        List lista = null;
        try {
            lista = DBConvenioBTLMF.listaDatosConvenio(pCodConvenio);
        } catch (SQLException sqlException) {
            log.error("", sqlException);
            //FarmaUtility.showMessage(pDialogo, "Error al obtener datos del convenio!!!", pObjeto);
        }
        log.debug("ListaDatConv" + lista);
        return lista;
    }
    /*** FIN    ARAVELLO 11/10/2019 ***/
    public static Map obtienePantallaMensaje(String pNroResolucion, String pPosicion, JDialog pDialogo,
                                             Object pObjeto) {
        Map map = null;
        try {
            map = DBConvenioBTLMF.obtienePantallaMensaje(pNroResolucion, pPosicion);
        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al obtener datos de la pantalla!!!", pObjeto);
        }
        log.debug("Map Pantalla:" + map);
        return map;
    }

    public static String obtieneDocVerificacion(String pCodConvenio, String pFlgRetencion, String pNomBenificiario,
                                                JDialog pDialogo, Object pObjeto) {
        String msg = "";

        try {
            msg = DBConvenioBTLMF.ObtieneDocVerificacion(pCodConvenio, pFlgRetencion, pNomBenificiario);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al obtener datos de Documentos de verificaci�n!!!", "");
        }

        log.debug("msg:" + msg);
        return msg;
    }


    public static void listaMensaje(ArrayList lista, String pCodConvenio, String pFlgRetencion, JDialog pDialogo,
                                    Object pObjeto) {

        try {
            DBConvenioBTLMF.listaMensajes(lista, pCodConvenio, pFlgRetencion);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al obtener datos de Documentos de verificaci�n!!!", "");
        }


    }


    public static Map obtieneBenificiario(String pCodConvenio, String pDni, JDialog pDialogo) {
        Map benif = null;

        try {
            benif = DBConvenioBTLMF.obtieneBenificiario(pCodConvenio, pDni);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al buscar Benificiario", "");
        }


        log.debug("benif:" + benif);
        return benif;
    }

    public static String existeBenificiario(String pCodConvenio, String pDni, JDialog pDialogo) {
        String benif = null;

        try {
            benif = DBConvenioBTLMF.existeBenificiario(pCodConvenio, pDni);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al buscar Benificiario", "");
        }


        log.debug("benif:" + benif);
        return benif;
    }


    public static Map obtenerTarjeta(String pCodBarra, JDialog pDialogo) {
        Map benif = null;

        try {
            benif = DBConvenioBTLMF.obtenerTarjeta(pCodBarra);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al buscar Benificiario con Tarjeta", "");
        }


        log.debug("msg:" + benif.get(ConstantsConvenioBTLMF.COL_DNI));
        return benif;
    }


    public static Map obtenerCliente(String pCodCliente, JDialog pDialogo) {
        Map cliente = null;

        try {
            cliente = DBConvenioBTLMF.obtenerCliente(pCodCliente);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al obtener cliente", "");
        }

        log.debug("msg:" + cliente.get(ConstantsConvenioBTLMF.COL_DNI));
        return cliente;
    }


    public static Map obtieneDiagnostico(String pCodConvenio, String pCODCIE10, JDialog pDialogo) {
        Map benif = null;

        try {
            benif = DBConvenioBTLMF.obtieneDiagnostico(pCODCIE10);
            VariablesConvenioBTLMF.codDiagnostico =
                    (String)benif.get(ConstantsConvenioBTLMF.COD_DIAGNOSTICO); //CHUANES 28.03.2014 EXTRAEMOS EL CODIGO DE DIAGNOSTICO
        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al buscar Diagn�stico", "");
        }

        log.debug("msg:" + benif.get(ConstantsConvenioBTLMF.COL_COD_CIE_10));
        return benif;
    }


    public static Map obtieneMedico(String pCodConvenio, String pCodMedico, JDialog pDialogo) {
        Map medico = null;

        try {
            medico = DBConvenioBTLMF.obtieneMedico(pCodConvenio, pCodMedico);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al buscar M�dico", "");
        }

        log.debug("msg:" + medico.get(ConstantsConvenioBTLMF.COL_NUM_CMP));
        return medico;
    }

    public static Map obtenerConvenio(String pCodConvenio, JDialog pDialogo, Frame myParentFrame) {
        Map medico = null;
        DlgProcesarDatosConvenio dlgProcesarDatosConvenio = new DlgProcesarDatosConvenio(myParentFrame, "", true);
        dlgProcesarDatosConvenio.setPDialogo(pDialogo);
        dlgProcesarDatosConvenio.setPCodConvenio(pCodConvenio);
        dlgProcesarDatosConvenio.mostrar();
        medico = dlgProcesarDatosConvenio.getMedico();
        return medico;

    }

    public static void filtraDescripcion2(KeyEvent evento, FarmaTableModel tableModelo, ArrayList listaDatos,
                                          JTextField jtext, int columna) {

        ArrayList listaConvenio = filtraListaDato2(evento, listaDatos, jtext, columna);

        copiaTablaModelo(tableModelo, listaConvenio, false);

    }


    public static void filtraCampoString(KeyEvent evento, FarmaTableModel tableModelo, ArrayList listaDatos,
                                         JTextField jtext, int columna) {

        ArrayList listaConvenio = filtraListaDatoString(evento, listaDatos, jtext, columna);

        copiaTablaModelo(tableModelo, listaConvenio, false);

    }


    private static void copiaTablaModelo(FarmaTableModel pTableModel, ArrayList lista, boolean pWithCheck) {
        log.debug("<<<<<<<<<<<<<Metdo: copiaTablaModelo >>>>>>>>> " + lista);

        if (pTableModel != null) {

            //ERIOS 2.4.6 Seleccion multi-diagnostico
            ArrayList seleccionados = new ArrayList();
            if (VariablesConvenioBTLMF.vCodTipoCampo.equals(ConstantsConvenioBTLMF.COD_DATO_CONV_DIAGNOSTICO_UIE)) {
                for (int i = 0; i < pTableModel.data.size(); i++) {
                    ArrayList row = (ArrayList)pTableModel.data.get(i);
                    if (((Boolean)row.get(0)).booleanValue()) {
                        seleccionados.add(row);
                    }
                }
            }

            pTableModel.clearTable();

            ArrayList myArray = null;
            pTableModel.clearTable();

            for (int i = 0; i < seleccionados.size(); i++) {
                ArrayList row = (ArrayList)seleccionados.get(i);
                pTableModel.insertRow(row);
            }

            for (int i = 0; i < lista.size(); i++) {
                //String[] arg = (String[])lista.get(i);
                Object[] arg = (Object[])lista.get(i);

                boolean existe = false;
                for (int j = 0; j < seleccionados.size(); j++) {
                    ArrayList row = (ArrayList)seleccionados.get(j);
                    if (arg[0].toString().equals(row.get(1).toString())) {
                        seleccionados.remove(j);
                        existe = true;
                        break;
                    }
                }

                if (arg.length > 0 && !existe) {
                    myArray = new ArrayList();
                    //ERIOS 2.4.6 Seleccion multi-diagnostico
                    if (VariablesConvenioBTLMF.vCodTipoCampo.equals(ConstantsConvenioBTLMF.COD_DATO_CONV_DIAGNOSTICO_UIE)) {
                        myArray.add(new Boolean(false));
                    }
                    for (int y = 0; y < arg.length; y++) {
                        myArray.add(arg[y]);
                    }
                    pTableModel.insertRow(myArray);
                }
            }
        }
    }

    private static ArrayList filtraListaDato2(KeyEvent e, ArrayList listaTodo, JTextField pTextoDeBusqueda,
                                              int pColumna) {
        log.debug("<<<<<<<<<<<<Metodo: filtraListaDato2  >>>>>>>>>>>>>>>" + pTextoDeBusqueda.getText().trim());
        log.debug("<<<<<<<<<<<<Tamano::::  >>>>>>>>>>>>>>>" + listaTodo.size());

        ArrayList lista = new ArrayList();


        if ((e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) && ((e.getKeyCode() != KeyEvent.VK_ESCAPE))) {


            String vFindText = pTextoDeBusqueda.getText().toUpperCase();


            String vCodigo = "";
            String vDescrip = "";
            String vDescripcion = "";
            for (int k = 0; k < listaTodo.size(); k++) {


                vCodigo = ((String[])listaTodo.get(k))[0];
                vDescrip = ((String[])listaTodo.get(k))[1];
                vDescripcion = vDescrip;

                if (VariablesConvenioBTLMF.vCodTipoCampo.equals(ConstantsConvenioBTLMF.COD_DATO_CONV_DIAGNOSTICO_UIE)) {
                    //DUBILLUZ 04.06.2014
                    if (vCodigo.toUpperCase().indexOf(vFindText.toUpperCase()) == 0) {
                        
                        String[] dato = (String[])listaTodo.get(k);

                        lista.add(dato);
                    } else
                    //DUBILLUZ 04.06.2014
                    if (vDescrip.toUpperCase().indexOf(vFindText.toUpperCase()) == 0) {


                        String[] dato = (String[])listaTodo.get(k);

                        lista.add(dato);
                    }
                } else {
                    //DUBILLUZ 04.06.2014
                    if (vCodigo.toUpperCase().indexOf(vFindText.toUpperCase()) != -1) {
                       
                        String[] dato = (String[])listaTodo.get(k);

                        lista.add(dato);
                    } else
                    //DUBILLUZ 04.06.2014
                    if (vDescrip.toUpperCase().indexOf(vFindText.toUpperCase()) != -1) {


                        String[] dato = (String[])listaTodo.get(k);

                        lista.add(dato);
                    }
                }
                
            }
        }

        return lista;

    }


    private static ArrayList filtraListaDatoString(KeyEvent e, ArrayList listaTodo, JTextField pTextoDeBusqueda,
                                                   int pColumna) {
        log.debug("<<<<<<<<<<<<Metodo: filtraListaDatoString  >>>>>>>>>>>>>>>" + pTextoDeBusqueda.getText().trim());
        log.debug("<<<<<<<<<<<<Tamano::::  >>>>>>>>>>>>>>>" + listaTodo.size());
        ArrayList lista = new ArrayList();

        if ((e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) && ((e.getKeyCode() != KeyEvent.VK_ESCAPE))) {

            String vFindText = pTextoDeBusqueda.getText().toUpperCase();
            String vCodigo = "";
            for (int k = 0; k < listaTodo.size(); k++) {
                if (((Object[])listaTodo.get(k))[pColumna] instanceof String) {
                    vCodigo = (String)((Object[])listaTodo.get(k))[pColumna];
                    if (vCodigo.toUpperCase().indexOf(vFindText.toUpperCase()) != -1) {
                        Object[] dato = (Object[])listaTodo.get(k);
                        lista.add(dato);
                    }
                }
            }
        }
        return lista;
    }

    public static boolean esTarjetaConvenio(String dato) {

        return existeTarjeta(dato, null);
    }


    public static boolean existeTarjeta(String dato, JDialog dialog) {
        log.debug("<<<<<<<<<<<<Metodo: existeTarjeta>>>>>>>>>>>>");
        Map tarjeta = null;
        boolean retorno = false;
        tarjeta = UtilityConvenioBTLMF.obtenerTarjeta(dato.trim(), dialog);
        if (tarjeta.get(ConstantsConvenioBTLMF.COL_COD_BARRA) != null) {
            retorno = true;
            VariablesConvenioBTLMF.vCodCliente = (String)tarjeta.get(ConstantsConvenioBTLMF.COL_COD_CLIENTE);
            VariablesConvenioBTLMF.vCodConvenioAux = (String)tarjeta.get(ConstantsConvenioBTLMF.COL_COD_CONVENIO);
            log.debug("vCreacionCliente:::>" + VariablesConvenioBTLMF.vCreacionCliente);
        }


        return retorno;
    }

    public static boolean existeCliente(String pCodCliente, JDialog dialog) {
        log.debug("<<<<<<<<<<<<Metodo: existeCliente>>>>>>>>>>>>");
        Map benif = null;
        boolean retorno = false;
        benif = UtilityConvenioBTLMF.obtenerCliente(pCodCliente, dialog);

        if (benif.get(ConstantsConvenioBTLMF.COL_COD_CLIENTE) != null) {
            retorno = true;
            VariablesConvenioBTLMF.vDni = (String)benif.get(ConstantsConvenioBTLMF.COL_DNI);
            VariablesConvenioBTLMF.vNombre = (String)benif.get(ConstantsConvenioBTLMF.COL_DES_NOM_CLIENTE);
            VariablesConvenioBTLMF.vNomCliente = (String)benif.get(ConstantsConvenioBTLMF.COL_DES_APE_CLIENTE);
            VariablesConvenioBTLMF.vDescripcion = VariablesConvenioBTLMF.vNombre;
            VariablesConvenioBTLMF.vApellidoPat = (String)benif.get(ConstantsConvenioBTLMF.COL_DES_APE_CLIENTE);
            VariablesConvenioBTLMF.vLineaCredito = (String)benif.get(ConstantsConvenioBTLMF.COL_LCREDITO);
            VariablesConvenioBTLMF.vEstado = (String)benif.get(ConstantsConvenioBTLMF.COL_ESTADO);


            String numPoliza = (String)benif.get(ConstantsConvenioBTLMF.COL_NUM_POLIZA);
            String numPlan = (String)benif.get(ConstantsConvenioBTLMF.COL_NUM_PLAN);
            String codAsegurado = (String)benif.get(ConstantsConvenioBTLMF.COL_COD_ASEGURADO);
            String numItem = (String)benif.get(ConstantsConvenioBTLMF.COL_NUM_IEM);
            String prt = (String)benif.get(ConstantsConvenioBTLMF.COL_PRT);
            String numContrado = (String)benif.get(ConstantsConvenioBTLMF.COL_NUM_CONTRATO);
            String tipoAsegurado = (String)benif.get(ConstantsConvenioBTLMF.COL_TIPO_ASEGURADO);

            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_NUM_POLIZA, numPoliza);
            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_NUM_PLAN, numPlan);
            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_COD_ASEGURADO,
                                                             codAsegurado);
            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_NUM_IEM, numItem);
            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_NOMB_CLIENTE,
                                                             VariablesConvenioBTLMF.vNombre);
            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_PRT, prt);
            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_NUM_CONTRATO, numContrado);
            VariablesConvenioBTLMF.listaDatosNoEditables.put(ConstantsConvenioBTLMF.CODIGO_TIPO_ASEGURADO,
                                                             tipoAsegurado);


        }

        return retorno;
    }


    public static boolean existeConvenio(String pCodConvenio, JDialog dialog, Frame myParentFrame) {
        log.debug("<<<<<<<<<<<<Metodo: existeConvenio>>>>>>>>>>>>");
        Map convenio = null;
        boolean retorno = false;
        convenio = UtilityConvenioBTLMF.obtenerConvenio(pCodConvenio, dialog, myParentFrame);

        if (convenio.get(ConstantsConvenioBTLMF.COL_COD_CONVENIO) != null) {
            retorno = true;

            VariablesConvenioBTLMF.vCodConvenio = (String)convenio.get(ConstantsConvenioBTLMF.COL_COD_CONVENIO);
            VariablesConvenioBTLMF.vNomConvenio = (String)convenio.get(ConstantsConvenioBTLMF.COL_DES_CONVENIO);
            VariablesConvenioBTLMF.vCodConvenioRel = (String)convenio.get(ConstantsConvenioBTLMF.COL_COD_CONVENIO_REL);
            VariablesConvenioBTLMF.vFlgCreacionCliente =
                    (String)convenio.get(ConstantsConvenioBTLMF.COL_FLG_CREACION_CLIENTE);
            VariablesConvenioBTLMF.vFlgTipoConvenio =
                    (String)convenio.get(ConstantsConvenioBTLMF.COL_FLG_TIPO_CONVENIO);


        }

        log.debug("vCodConvenio=" + VariablesConvenioBTLMF.vCodConvenio);
        log.debug("vNomConvenio=" + VariablesConvenioBTLMF.vNomConvenio);
        log.debug("vCodConvenioRel=" + VariablesConvenioBTLMF.vCodConvenioRel);
        log.debug("vFlgTipoConvenio=" + VariablesConvenioBTLMF.vFlgTipoConvenio);
        log.debug("vFlgCreacionCliente=" + VariablesConvenioBTLMF.vFlgCreacionCliente);


        return retorno;
    }


    /**
     * Imprimir Mensaje
     * @author Fredy Ramirez
     * @since  09/11/2011
     */

    public static void imprimirMensaje(String pDni, JDialog pDialogo, Object pObject,boolean vImprimir) {
        if(vImprimir){
            try {
                String vMensaje = DBConvenioBTLMF.imprimirMensaje(pDni);
                //FarmaUtility.showMessage(pDialogo, "esta:"+vMensaje, pObject);
                imprimirMensaje(vMensaje, VariablesPtoVenta.vImpresoraActual, VariablesPtoVenta.vTipoImpTermicaxIp);
                FarmaUtility.showMessage(pDialogo, "No existe Beneficiario para este convenio.", pObject);
            } catch (SQLException sqlException) {
                log.error("", sqlException);
                FarmaUtility.showMessage(pDialogo, "Error al imprimir el mensaje", pObject);
            }            
        }
    }    

    /**
     * metodo encargado de imprimirMensaje
     * @param pConsejos
     * @param pImpresora
     * @param pTipoImprConsejo
     */
    private static void imprimirMensaje(String pConsejos, PrintService pImpresora, String pTipoImprConsejo) {
        DocumentRendererConsejo dr = new DocumentRendererConsejo(pImpresora);
        JEditorPane editor = new JEditorPane();

        try {
            // Marcamos el editor para que use HTML
            editor.setContentType("text/html");
            editor.setText(pConsejos);
            dr.print(editor, pTipoImprConsejo);

        } catch (Exception e) {
            log.error("", e);
        }
    }

    public static boolean esActivoConvenioBTLMF(JDialog pDialogo, Object pObjeto) {
        boolean resul = false;
        String esActivoConv = "";
        try {
            esActivoConv = DBConvenioBTLMF.esActivoConvenioBTLMF();
            log.debug("esActivoConv " + esActivoConv);
            if (esActivoConv.equalsIgnoreCase("S")) {
                resul = true;
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialogo,
                                     "Error al obtener de la base de datos el estado convenio BTLMF" + sql.getMessage(),
                                     pObjeto);
            resul = true;
        }
        return resul;

    }


    public static String obtieneFormaPago(JDialog pDialogo, Object pObjeto, String pCodFormaPago) {
        boolean resul = false;
        String descripcion = "";
        try {
            descripcion = DBConvenioBTLMF.obtieneFormaPago(pCodFormaPago);

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialogo,
                                     "Error al obtener de la base de datos el estado convenio BTLMF" + sql.getMessage(),
                                     pObjeto);
            resul = true;
        }
        return descripcion;

    }

    public static double obtieneMontoCredito(JDialog pDialogo, Object pObjeto, Double monto, String nroPedido,
                                             String codConvenio, double vValorSelCopago) {
        double montoCredito = 0.00;
        try {
            if (vValorSelCopago == -1) {
                montoCredito = DBConvenioBTLMF.obtieneMontoCredito(monto, nroPedido, codConvenio);
            } else {
                montoCredito = ((100 - vValorSelCopago) / 100) * monto.doubleValue();
            }
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialogo,
                                     "Error al obtener de la base de el monto credito convenio BTLMF" + sql.getMessage(),
                                     pObjeto);
        }
        return montoCredito;

    }

    public static void Busca_Estado_ProdConv(JDialog pJDialog) {
        
        try {
            log.debug("VariablesVentas.vCod_Prod:" + VariablesVentas.vCod_Prod);
            VariablesVentas.vEstadoProdConvenio = DBConvenioBTLMF.getEstadoProdConv(VariablesVentas.vCod_Prod);
        } catch (Exception sql) {
            VariablesVentas.vEstadoProdConvenio = "N";
            log.error("Error al obtener estado del producto " + VariablesVentas.vCod_Prod + "para el convenio " +
                      VariablesConvenioBTLMF.vCodConvenio, sql);
            FarmaUtility.showMessage(pJDialog, "Error al verificar estado del producto convenio.\n" +
                    sql.getMessage(), null);
        }

        log.debug("VariablesVentas.vEstadoProdConvenio:" + VariablesVentas.vEstadoProdConvenio);
    }


    public static String Conv_Buscar_Precio() {
        
        String sValPrecio = "0.00";
        try {
            log.debug("VariablesVentas.vCod_Prod:" + VariablesVentas.vCod_Prod);
            VariablesVentas.vEstadoProdConvenio = DBConvenioBTLMF.getEstadoProdConv(VariablesVentas.vCod_Prod);
            log.debug("VariablesVentas.vEstadoProdConvenio:" + VariablesVentas.vEstadoProdConvenio);
            sValPrecio = DBConvenioBTLMF.getPrecioProdConv(VariablesVentas.vCod_Prod);
        } catch (Exception sql) {
            VariablesVentas.vEstadoProdConvenio = "N";
            log.error("Error al obtener estado del producto " + VariablesVentas.vCod_Prod + "para el convenio " +
                      VariablesConvenioBTLMF.vCodConvenio, sql);
        }
        return sValPrecio;
    }
    
    public static void procesoImpresionComprobante(JDialog pJDialog, Object pObjectFocus, boolean isReimpresion) {

        procesoImpresionComprobante(pJDialog, pObjectFocus, false, null, isReimpresion);

    }
    
    public static void procesoReImpresionComprobante(JDialog pJDialog, Object pObjectFocus, String pSecCompPago) {

        procesoImpresionComprobante(pJDialog, pObjectFocus, false, pSecCompPago, true);

    }

    private static void procesoImpresionComprobante(JDialog pJDialog, Object pObjectFocus, boolean vIndImpresionAnulado,
                                                   String pSecCompPago, boolean isReimpresion) {
        long tmpT1, tmpT2;
        long tmpInicio, tmpFinal;
        log.debug("******PROCESO IMPRESION COMPROBANTES DEL CONVENIO********");
        tmpInicio = System.currentTimeMillis();

        try {
            UtilityCaja.obtieneInfoCajero(VariablesCaja.vSecMovCaja);
            //cambiando el estado de pedido al estado C -- que es estado IMPRESO y COBRADO
            tmpT1 = System.currentTimeMillis();
            //JMIRANDA 23/07/09 posee Throws SQLException

            //UtilityCaja.actualizaEstadoPedido(VariablesCaja.vNumPedVta, ConstantsCaja.ESTADO_COBRADO);
            //rherrera 10.11.2014 actualiza estaod cobrado al final de imprimir comprobantes.

            boolean solicitaDatos =
                UtilityConvenioBTLMF.indDatoConvenio(VariablesConvenioBTLMF.vCodConvenio);
            if (solicitaDatos && !listaDatosConvenioAdic(pJDialog, pObjectFocus)) {
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(pJDialog,
                                         "No se pudo determinar los datos adicionales del convenio. Verifique!!!.",
                                         pObjectFocus);
                return;
            }
            log.debug("Imprimiendo comprobantes ... ");
            tmpT1 = System.currentTimeMillis();
            String fechaCreacionComp = "";
            String RefTipComp = "";
            String vPrctBeneficiario = "0";
            String vPrctEmpresa = "0";
            String vIndImprimeEmpresa = "0";

            //
            acumuCorreFac = 0;
            acumuCorreGuia = 0;
            acumuCorreTik = 0;
            acumuCorreBol = 0;
            /*
            //KMONCADA 08.11.2016
            if(UtilityCPE.isActivoFuncionalidad()){
                boolean proceso = (new UtilityCPE()).procesarPedidoEnEPOS(pJDialog, VariablesCaja.vNumPedVta);
                if(!proceso){
                    return;
                }
            }
            */
            int contComprobantesImpresos = 0;
            //KMONCADA 17.11.2014 SE CREA METODO PARA LISTAR LOS COMPROBANTES PARA IMPRESION Y REIMPRESION
            if (obtieneCompPagoImpresion(pJDialog, "", null)) {
                for (int j = 0; j < VariablesConvenioBTLMF.vArray_ListaComprobante.size(); j++) {
                    String secCompPago = ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(1)).trim();
                    if((isReimpresion && pSecCompPago!=null && pSecCompPago.equalsIgnoreCase(secCompPago)) || 
                       (pSecCompPago == null)){
                        
                        VariablesConvenioBTLMF.vNumCompPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(0)).trim();
                        VariablesConvenioBTLMF.vSecCompPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(1)).trim();
                        VariablesConvenioBTLMF.vTipoCompPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(2)).trim();
                        VariablesConvenioBTLMF.vValIgvCompPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(3)).trim();
                        VariablesConvenioBTLMF.vValNetoCompPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(4)).trim();
                        VariablesConvenioBTLMF.vValCopagoCompPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(5)).trim();
                        VariablesConvenioBTLMF.vValIgvCompCoPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(6)).trim();
                        VariablesConvenioBTLMF.vNumCompPagoRef =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(7)).trim();
                        VariablesConvenioBTLMF.vTipClienConvenio =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(8)).trim();
                        VariablesConvenioBTLMF.vFlgImprDatAdic =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(9)).trim();
                        VariablesConvenioBTLMF.vCodTipoConvenio =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(10)).trim();
                        fechaCreacionComp =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(11)).trim();
                        RefTipComp =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(12)).trim();
                        VariablesConvenioBTLMF.vValRedondeoCompPago =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(13)).trim();
                        vPrctBeneficiario =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(14)).trim();
                        vPrctEmpresa =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(15)).trim();
                        vIndImprimeEmpresa =
                                ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(16)).trim();
                        int valor = j;
    
                        log.debug("VariablesConvenioBTLMF.vNumCompPago      :  " + VariablesConvenioBTLMF.vNumCompPago);
                        log.debug("VariablesConvenioBTLMF.vSecCompPago      :  " + VariablesConvenioBTLMF.vSecCompPago);
                        log.debug("VariablesConvenioBTLMF.vTipoCompPago     :  " + VariablesConvenioBTLMF.vTipoCompPago);
                        log.debug("VariablesConvenioBTLMF.vValIgvCompPago   :  " + VariablesConvenioBTLMF.vValIgvCompPago);
                        log.debug("VariablesConvenioBTLMF.vValNetoCompPago  :  " +
                                  VariablesConvenioBTLMF.vValNetoCompPago);
                        log.debug("VariablesConvenioBTLMF.vValCopagoCompPago:  " +
                                  VariablesConvenioBTLMF.vValCopagoCompPago);
                        log.debug("VariablesConvenioBTLMF.vValIgvCompCoPago :  " +
                                  VariablesConvenioBTLMF.vValIgvCompCoPago);
                        log.debug("VariablesConvenioBTLMF.vNumCompPagoRef   :  " + VariablesConvenioBTLMF.vNumCompPagoRef);
                        log.debug("VariablesConvenioBTLMF.vTipClienConvenio :  " +
                                  VariablesConvenioBTLMF.vTipClienConvenio);
                        log.debug("VariablesConvenioBTLMF.vFlgImprDatAdic   :  " + VariablesConvenioBTLMF.vFlgImprDatAdic);
                        log.debug("VariablesConvenioBTLMF.vCodTipoConvenio  :  " +
                                  VariablesConvenioBTLMF.vCodTipoConvenio);
    
                        log.debug("fechaCreacionComp                        :  " + fechaCreacionComp);
                        log.debug("RefTipComp                               :  " + RefTipComp);
                        log.debug("VariablesConvenioBTLMF.vValRedondeoCompPago  :  " +
                                  VariablesConvenioBTLMF.vValRedondeoCompPago);
    
                        
    
                        //JMIRANDA 23/07/09 Posee try-catch interno. Envia Error via Email
                        if (!obtieneDetalleComp(pJDialog, VariablesConvenioBTLMF.vSecCompPago,
                                                VariablesConvenioBTLMF.vTipoCompPago,
                                                VariablesConvenioBTLMF.vTipClienConvenio, pObjectFocus)) {
                            FarmaUtility.liberarTransaccion();
                            FarmaUtility.showMessage(pJDialog,
                                                     "No se pudo obtener el detalle del comprobante a imprimir. Verifique!!!",
                                                     pObjectFocus);
                            return;
                        }
    
                        log.debug("VariablesConvenioBTLMF.vSecCompPago : " + VariablesConvenioBTLMF.vSecCompPago);
                        //JMIRANDA 23/07/09 Posee try-catch interno. Envia Error via Email
                        if (!UtilityCaja.obtieneTotalesComprobante(pJDialog, VariablesConvenioBTLMF.vSecCompPago,
                                                                   pObjectFocus)) {
                            FarmaUtility.liberarTransaccion();
                            FarmaUtility.showMessage(pJDialog,
                                                     "No se pudo determinar los Totales del Comprobante. Verifique!!!.",
                                                     pObjectFocus);
                            return;
                        }
    
                        tmpT1 = System.currentTimeMillis();
                        //JMIRANDA 23/07/09 Posee try-catch interno. Envia Error via Email
                        //Comentado//VariablesCaja.vRutaImpresora = obtieneRutaImpresora(secImprLocal.trim());
                        tmpT2 = System.currentTimeMillis();
                        log.debug("Tiempo 9: Obtiene Ruta Impresora:" + (tmpT2 - tmpT1) + " milisegundos");
                        tmpT1 = System.currentTimeMillis();
    
    
                        /*
                        * @author LTAVARA
                        * Obtener el numero de comprobante sea electronico
                        * @since 19.09.2014
                        * */
                        //VALIDAR SI EL COMPROBANTE ES ELECTRONICO
                        //String indicElectronico = DBImpresoras.getIndicCompElectronico(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago); //indicadorElectronico
                        
                        boolean isComprobanteElectronico = DBCPElectronico.isComprobanteElectronico(FarmaVariables.vCodGrupoCia, 
                                                                                           FarmaVariables.vCodLocal, 
                                                                                           VariablesCaja.vNumPedVta, 
                                                                                           VariablesConvenioBTLMF.vSecCompPago);
                        //if (indicElectronico.equalsIgnoreCase(ConstantesDocElectronico.INDELECTRONICO)) { //COMPROBANTE ELECTRONICO
                        
                        //FIN LTAVARA
                        
                        /*UtilityCaja.actualizaComprobanteImpreso(VariablesConvenioBTLMF.vSecCompPago,
                                                                VariablesConvenioBTLMF.vTipoCompPago,
                                                                VariablesConvenioBTLMF.vNumCompPago);
                        */
                        //ERIOS 2.4.3 Envia el porcentajes
                        UtilityConvenioBTLMF.imprimeComprobantePago(pJDialog,
                                                                    VariablesConvenioBTLMF.vArray_ListaDetComprobante,
                                                                    VariablesCaja.vArrayList_TotalesComp,
                                                                    VariablesConvenioBTLMF.vTipoCompPago,
                                                                    VariablesConvenioBTLMF.vNumCompPago,
                                                                    VariablesConvenioBTLMF.vValNetoCompPago,
                                                                    VariablesConvenioBTLMF.vValIgvCompPago,
                                                                    VariablesConvenioBTLMF.vValCopagoCompPago,
                                                                    VariablesConvenioBTLMF.vValIgvCompCoPago,
                                                                    VariablesConvenioBTLMF.vNumCompPagoRef,
                                                                    VariablesConvenioBTLMF.vFlgImprDatAdic,
                                                                    VariablesConvenioBTLMF.vTipClienConvenio,
                                                                    VariablesConvenioBTLMF.vCodTipoConvenio,
                                                                    fechaCreacionComp, RefTipComp,
                                                                    VariablesConvenioBTLMF.vValRedondeoCompPago,
                                                                    vIndImpresionAnulado, valor, vPrctBeneficiario,
                                                                    vPrctEmpresa, vIndImprimeEmpresa, isReimpresion,
                                                                    isComprobanteElectronico);
    
                        tmpT2 = System.currentTimeMillis();
                        log.debug("Tiempo 10: Imprime Comprobante:" + (tmpT2 - tmpT1) + " milisegundos");
    
                        //rherrera 10.11.2014 actualiza fecha de impresion.
                        if (FarmaVariables.vAceptar) {
                            contComprobantesImpresos++;
                            UtilityCaja.actualizarFechaImpresion(VariablesConvenioBTLMF.vSecCompPago,
                                                                 VariablesConvenioBTLMF.vTipoCompPago);
                        }
                        FarmaUtility.aceptarTransaccion();
                        log.debug("imprimiendo comprobantes ... " + valor);
                    }
                }
                if(contComprobantesImpresos != VariablesConvenioBTLMF.vArray_ListaComprobante.size()){
                    FarmaUtility.showMessage(pJDialog, "Impresion de Comprobantes de Pago:\n"
                                                       +"Han quedado "+(VariablesConvenioBTLMF.vArray_ListaComprobante.size()-contComprobantesImpresos)+
                                                       " comprobante (s) pendientes de impresi�n", pObjectFocus);
                    return;
                }
                // KMONCADA 06.05.2016 [ALBIS] IMPRESION DE CUPONES PARA CASOS DE CONVENIOS
                int cantidadCupones = 0;
                if(!ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL.equalsIgnoreCase(VariablesVentas.vTipoPedido))
                    cantidadCupones = UtilityCaja.imprimeCupones(pJDialog);
                
                //FIN DE QUE SE HAYA COBRADO EXITOSAMENTE
                //imprimirVoucher(pJDialog, null, VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vCodConvenio);
                FacadeConvenioBTLMF facadeConvenioBTLMF = new FacadeConvenioBTLMF();
                facadeConvenioBTLMF.impresionVoucher(VariablesCaja.vNumPedVta);
                 
                //rherrera 10.11.2014 CAMBIO ESTADO IMPRESION AL FINAL
                log.debug("Actualiza estado de pedido a cobrado");
                UtilityCaja.actualizaEstadoPedido(VariablesCaja.vNumPedVta, ConstantsCaja.ESTADO_COBRADO);

                FarmaUtility.aceptarTransaccion();
                log.debug("FIN imprimiendo comprobantes ... ");
            }

            /*//ERIOS 20.11.2014 Cambio necesario para la importacion de ventas en linea. (JLUNA)
            UtilityCaja.actualizaEstadoPedido(VariablesCaja.vNumPedVta, ConstantsCaja.ESTADO_COBRADO);
            FarmaUtility.aceptarTransaccion();
            log.debug("FIN imprimiendo comprobantes ... ");*/


            tmpT2 = System.currentTimeMillis();
            log.debug("Tiempo 11: Fin de Impresion de Comprobantes:" + (tmpT2 - tmpT1) + " milisegundos");

            //ERIOS 15.10.2013 Impresion de ticket anulado
            if (vIndImpresionAnulado) {
                FarmaUtility.showMessage(pJDialog, "�Pedido Anulado!", pObjectFocus);
                return;
            }

            tmpFinal = System.currentTimeMillis();
            // /////////////////////////////////////////////////////////////
            // IMPRESION DE PEDIDOS DELIVERY PROVINCIA
            // /////////////////////////////////////////////////////////////
            // 30.06.2014 DUBILLUZ
            if (VariablesVentas.vEsPedidoDelivery) {
                log.info("SI IMPRIME imprimeDatosDeliveryLocal PROVINCIA");
                UtilityCaja.imprimeDatosDeliveryLocal(pJDialog, VariablesCaja.vNumPedVta);
            } else {
                log.info("NO IMPRIME imprimeDatosDeliveryLocal PROVINCIA");
            }
            ////////////////////////////////////////////////////////////////

            // /////////////////////////////////////////////////////////////
            // IMPRESION DE PEDIDOS DELIVERY AUTOMATICO
            // /////////////////////////////////////////////////////////////
            // 30.06.2014 DUBILLUZ
            if (VariablesCaja.vIndDeliveryAutomatico.trim().equalsIgnoreCase("S") &&
                !VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL)) {
                log.info("SI IMPRIME imprimeDatosDeliveryLocal AUTOMTICO " + VariablesCaja.vNumPedVta);
                String vNumPedDely = DBCaja.obtieneNumPedDelivery(VariablesCaja.vNumPedVta);
                log.info("SI IMPRIME imprimeDatosDeliveryLocal AUTOMTICO " + vNumPedDely);
                UtilityCaja.imprimeDatosDelivery(pJDialog, vNumPedDely);
            } else {
                log.info("NO IMPRIME imprimeDatosDeliveryLocal AUTOMTICO");
            }
            ////////////////////////////////////////////////////////////////
            
            //KMONCADA 12.05.2016 - IMPRESION DE COMANDA DE DESPACHO
            try{
                (new UtilityDespacho()).imprimirComandaDespacho(pJDialog, VariablesCaja.vNumPedVta);
            }catch(Exception ex){
                log.info("[IMPRESION DE COMANDA DESPACHO] ERROR EN IMPRESION: "+ex.getMessage());
            }

            if (VariablesCaja.vEstadoSinComprobanteImpreso.equals("N")) {
                log.debug("T18-Tiempo Final de Metodo de Impresion: Obtiene unidades Camp.xCliente localmente:" +
                          (tmpFinal - tmpInicio) + " milisegundos");
                FarmaUtility.showMessage(pJDialog, "Pedido Cobrado con �xito. \n" +
                        "Comprobantes Impresos con �xito " + "", pObjectFocus);
            } else {
                log.debug("T18-Tiempo Final de Metodo de Impresion: Obtiene unidades Camp.xCliente localmente:" +
                          (tmpFinal - tmpInicio) + " milisegundos");
                FarmaUtility.showMessage(pJDialog,
                                         "Pedido Cobrado con �xito." + "\nCOMPROBANTES NO IMPRESOS, Verifique Impresora: " +
                                         VariablesCaja.vRutaImpresora + "\nReimprima Comprobante, Correlativo :" +
                                         VariablesCaja.vNumPedVta, pObjectFocus);
            }
            
            //INI JMONZALVE 24042019
            if(DBConv_Responsabilidad.verificaConvenioCobroPorResponsabilidad(VariablesConvenioBTLMF.vCodConvenio)){
                FarmaTableModel tableModelDetalleEmpleados =  new FarmaTableModel(ConstantsConv_Responsabilidad.columnsDetalleEmpleados, ConstantsConv_Responsabilidad.defaultValuesDetalleEmpleados, 0);
                JTable tblDetalleEmpleados = new JTable();
                FarmaUtility.initSimpleList(tblDetalleEmpleados, tableModelDetalleEmpleados, ConstantsConv_Responsabilidad.columnsDetalleEmpleados);
                DBConv_Responsabilidad.obtenerDetalleEmpleadosPorVenta(tableModelDetalleEmpleados, VariablesCaja.vNumPedVta);
                FarmaTableModel tableModelDetalleProductos = new FarmaTableModel(ConstantsConv_Responsabilidad.columnsDetalleVentas, ConstantsConv_Responsabilidad.defaultValuesDetalleVentas,0);
                JTable tblDetalleProductos = new JTable();
                FarmaUtility.initSimpleList(tblDetalleProductos, tableModelDetalleProductos,ConstantsConv_Responsabilidad.columnsDetalleVentas);
                DBConv_Responsabilidad.obtenerDetalleVentas(tableModelDetalleProductos, VariablesCaja.vNumPedVta);
                String descEmpresa = FarmaUtility.getValueFieldJTable(tblDetalleEmpleados, 0, 0);
                String descLocal = FarmaUtility.getValueFieldJTable(tblDetalleEmpleados, 0, 2);
                String fechaEmision = FarmaUtility.getValueFieldJTable(tblDetalleEmpleados, 0, 4);
                UtilityConv_Responsabilidad.exportarVentaCobroResponsabilidad(tableModelDetalleEmpleados, tableModelDetalleProductos, descEmpresa, descLocal, fechaEmision);
            }
            //FIN JMONZALVE 24042019
            
            //INI ASOSA - 10/02/2015 - 
            
            //if (UtilityPuntos.isActivoFuncionalidad()) {
            if (VariablesPuntos.frmPuntos!=null && VariablesPuntos.frmPuntos.getBeanTarjeta()!=null) {
                boolean flag = UtilityCaja.getIndVariosCompPedido(VariablesCaja.vNumPedVta);
                if (flag) {
                    UtilityCaja.imprVouPtosCliente(VariablesCaja.vNumPedVta);
                }
            }
            // lais X+1
            //INI LTAVARA 2016.09.09 IMPRIMIR VOUCHER CONTINUA BONIFICANDO CONVENIO
            String listaImpVouContinuaBonif = "N";
            
            if(VariablesConvenioBTLMF.vCodConvenio != null && VariablesConvenioBTLMF.vCodConvenio.length() > 0) {
                log.info("NO VA IMPRIMIR NADA DE X+1 para convenio");
                }
            else
            if (VariablesPuntos.frmPuntos!=null){
                if(  VariablesPuntos.frmPuntos.getBeanTarjeta()!=null){
                //imprimir si el pedido finalizo online
                    if(!VariablesPuntos.frmPuntos.getBeanTarjeta().getEstadoOperacion().equals(WSClientConstans.NO_CONEXION_ORBIS)) {
                
                    listaImpVouContinuaBonif = UtilityPuntos.getListaProductoAcumCliente(VariablesPuntos.frmPuntos.getBeanTarjeta().getDni());
                    if (!listaImpVouContinuaBonif.equals(FarmaConstants.INDICADOR_N)) {
                        if(DBPuntos.getIndImprVouContinuaBonificando(VariablesCaja.vNumPedVta, listaImpVouContinuaBonif).equals(FarmaConstants.INDICADOR_S)){  
                        UtilityPuntos.imprVoucherContinuaBonificando(VariablesCaja.vNumPedVta,listaImpVouContinuaBonif);
                        }
                    }
                    }
                }
            }
            //FIN LTAVARA 2016.08.04 
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);            
            FarmaUtility.showMessage(pJDialog, "Error en BD al Imprimir los Comprobantes del Pedido.\n" +
                    sql, pObjectFocus);
            //JMIRANDA 22/07/09 envia via email el error generado cuando no imprime
            UtilityCaja.enviaErrorCorreoPorDB(sql.toString(), VariablesCaja.vNumPedVta_Anul);
        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            log.error("", e);
            FarmaUtility.showMessage(pJDialog, "Error en la Aplicacion al Imprimir los Comprobantes del Pedido.\n" +
                    e, pObjectFocus);
            //JMIRANDA 22/07/09 envia via email el error generado cuando no imprime
            UtilityCaja.enviaErrorCorreoPorDB(e.toString(), VariablesCaja.vNumPedVta_Anul);
        }
    }

    private static boolean obtieneDetalleComp(JDialog pJDialog, String pSecCompPago, String pTipoCompPago,
                                              String pTipCliConv, Object pObjectFocus) {
        VariablesConvenioBTLMF.vArray_ListaDetComprobante = new ArrayList();
        boolean valor = true;
        long tmpT1, tmpT2;
        tmpT1 = System.currentTimeMillis();
        try {
            DBConvenioBTLMF.obtieneDetalleCompPagos(VariablesConvenioBTLMF.vArray_ListaDetComprobante, pSecCompPago,
                                                    pTipoCompPago, pTipCliConv);
            if (VariablesConvenioBTLMF.vArray_ListaDetComprobante.size() == 0) {
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(pJDialog, "No se pudo determinar el detalle del Pedido. Verifique!!!.",
                                         pObjectFocus);
                valor = false;
            }
            log.info("VariablesConvenioBTLMF.vArray_ListaDetComprobante : " +
                     VariablesConvenioBTLMF.vArray_ListaDetComprobante.size());
            //valor = true;
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(pJDialog, "Error al obtener Detalle de Impresion de Comprobante.", pObjectFocus);
            
            valor = false;
            UtilityCaja.enviaErrorCorreoPorDB(sql.toString(), null);
        }

        tmpT2 = System.currentTimeMillis();
        log.debug("Tiempo 4: Det.Comp Pago:" + (tmpT2 - tmpT1) + " milisegundos");


        return valor;
    }

    public static boolean obtieneCompPago(JDialog pJDialog, String pTipClienteConv, Object pObjectFocus) {
        VariablesConvenioBTLMF.vArray_ListaComprobante = new ArrayList();
        boolean valor = true;
        long tmpT1, tmpT2;
        tmpT1 = System.currentTimeMillis();
        try {
            DBConvenioBTLMF.obtieneCompPagos(VariablesConvenioBTLMF.vArray_ListaComprobante, pTipClienteConv);
            if (VariablesConvenioBTLMF.vArray_ListaComprobante.size() == 0) {
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(pJDialog, "No se pudo determinar los datos del comprobante. Verifique!!!.",
                                         pObjectFocus);
                valor = false;
            }
            log.info("VariablesConvenioBTLMF.vArray_ListaComprobante : " +
                     VariablesConvenioBTLMF.vArray_ListaComprobante.size());
            valor = true;
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(pJDialog, "Error al obtener los datos de Impresion del Comprobante.",
                                     pObjectFocus);
            
            valor = false;
            UtilityCaja.enviaErrorCorreoPorDB(sql.toString(), null);
        }
        tmpT2 = System.currentTimeMillis();
        log.debug("Tiempo 4: Det.Comp Pago:" + (tmpT2 - tmpT1) + " milisegundos");
        return valor;
    }


    public static boolean obtieneCompPagoImpresion(JDialog pJDialog, String pTipClienteConv, Object pObjectFocus) {
        VariablesConvenioBTLMF.vArray_ListaComprobante = new ArrayList();
        boolean valor = true;
        long tmpT1, tmpT2;
        tmpT1 = System.currentTimeMillis();
        try {
            DBConvenioBTLMF.obtieneCompPagosImpresion(VariablesConvenioBTLMF.vArray_ListaComprobante, pTipClienteConv);
            if (VariablesConvenioBTLMF.vArray_ListaComprobante.size() == 0) {
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(pJDialog, "No se pudo determinar los datos del comprobante. Verifique!!!.",
                                         pObjectFocus);
                valor = false;
            }
            log.info("VariablesConvenioBTLMF.vArray_ListaComprobante : " +
                     VariablesConvenioBTLMF.vArray_ListaComprobante.size());
            valor = true;
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(pJDialog, "Error al obtener los datos de Impresion del Comprobante.",
                                     pObjectFocus);
            
            valor = false;
            UtilityCaja.enviaErrorCorreoPorDB(sql.toString(), null);
        }
        tmpT2 = System.currentTimeMillis();
        log.debug("Tiempo 4: Det.Comp Pago:" + (tmpT2 - tmpT1) + " milisegundos");
        return valor;
    }


    public static void imprimeComprobantePago(JDialog pJDialog, ArrayList pDetalleComprobante,
                                              ArrayList pTotalesComprobante, String pTipCompPago,
                                              String pNumComprobante, String pValTotalNeto, String pValIgvComPago,
                                              String pValCopagoCompPago, String pValIgvCompCoPago, String pNumCompRef,
                                              String pImprDatAdic, String pTipoClienteConvenio,
                                              String pCodTipoConvenio, String pFechaCreacionComp, String pRefTipComp,
                                              String pValRedondeoCompPago, boolean vIndImpresionAnulado, int valor,
                                              String vPrctBeneficiario, String vPrctEmpresa, String vIndImprimeEmpresa,
                                              boolean isReimpresion, boolean isComprobanteElectronico) throws Exception {
        String pPorcIgv = ((String)((ArrayList)pTotalesComprobante.get(0)).get(6)).trim();
        String pValTotalAhorro = ((String)((ArrayList)pTotalesComprobante.get(0)).get(11)).trim();

        String ruta = "";
        ruta = UtilityPtoVenta.obtieneDirectorioComprobantes();
        Date vFecImpr = new Date();
        String fechaImpresion;
        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        fechaImpresion = sdf.format(vFecImpr);
        //String secImprLocal = "";
        log.debug("fecha : " + fechaImpresion);
        
        if(isComprobanteElectronico){
            // KMONCADA 24.11.2016 [FACTURACION ELECTRONICA 2.0]
            // PROCESAR EL COMPROBANTE DE PAGO EN EL EPOS
            boolean procesadoEnEPOS = (new UtilityCPE()).procesarComprobanteAlEPOS(pJDialog, 
                                                                                   VariablesCaja.vNumPedVta, 
                                                                                   VariablesConvenioBTLMF.vSecCompPago, 
                                                                                   pNumComprobante);
            if(procesadoEnEPOS){
                if (pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_BOLETA) ||
                    pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA) ||
                    pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_NOTA_CREDITO)
                ){
                    if(pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_BOLETA)){
                        ruta = ruta + fechaImpresion + "_" + "B_E_" + VariablesCaja.vNumPedVta + "_" + pNumComprobante + ".TXT";
                    }
                    if(pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA)){
                        ruta = ruta + fechaImpresion + "_" + "F_E_" + VariablesCaja.vNumPedVta + "_" + pNumComprobante + ".TXT";
                    }
                    
                    if(pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_NOTA_CREDITO)){
                        ruta = ruta + fechaImpresion + "_" + "NC_E_" + VariablesCaja.vNumPedVta + "_" + pNumComprobante + ".TXT";
                    }
                    
                    UtilityImpCompElectronico impresionElectronico = new UtilityImpCompElectronico();
                    impresionElectronico.setRutaFileTestigo(ruta);
                    FarmaVariables.vAceptar = impresionElectronico.printDocumento(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago, false, true);    
                    //ERIOS 25.06.2015 Se solicita imprimir doble
                    if(VariablesVentas.vTipoPedido.equalsIgnoreCase(ConstantsVentas.TIPO_PEDIDO_INSTITUCIONAL) && !isReimpresion &&
                        (pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_BOLETA) ||
                        pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA)) ){
                        try{
                            impresionElectronico.printDocumento(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago, true, true);    
                        }catch(Exception e){
                            log.error("Imprimir copia de Venta Empresa",e);
                        }
                    }
                }else{
                    FarmaUtility.showMessage(pJDialog, "ERROR AL IMPRIMIR DOCUMENTO ELECTRONICO, TIPO DE COMPROBANTE: "+pTipCompPago+", VERIFIQUE!!!", null);
                }
            }else{
                FarmaVariables.vAceptar = false;
            }
        }else{
            /*boolean isContingencia = DBCaja.isComprobanteEnContingencia(FarmaVariables.vCodGrupoCia, 
                                                                        FarmaVariables.vCodLocal, 
                                                                        VariablesCaja.vNumPedVta, 
                                                                        VariablesConvenioBTLMF.vSecCompPago);
            */
            //ERIOS 2.4.3 margen derecho
            int margen = UtilityPtoVenta.getMargenImpresionComp();
            
            VariablesConvenioBTLMF.vTipoCompPagoAux = pTipCompPago;
            if (pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_BOLETA)) {
                //Imprimiendo mensajes
                muestraMensajeImpresion(pJDialog, ConstantsVentas.TIPO_COMP_BOLETA, ++acumuCorreBol,
                                        VariablesConvenioBTLMF.vSecCompPago, VariablesCaja.vNumPedVta, false,
                                        isReimpresion); //pNumComprobante --Cambio por CESAR HUANES 18.09.2014
                // muestraMensajeImpresion(pJDialog,ConstantsVentas.TIPO_COMP_BOLETA,++acumuCorreBol,pNumComprobante);//LTAVARA 18.09.2014 VOLVIO A SU ESTADO ANTIGUO PORQUE SE MODIFICO EL PROCEDIMIENTO

                ruta = ruta + fechaImpresion + "_" + "B_" + VariablesCaja.vNumPedVta + "_" + pNumComprobante + ".TXT";
                //secImprLocal = DBCaja.getObtieneSecImpPorIP(FarmaVariables.vIpPc, ConstantsVentas.TIPO_COMP_TICKET, "N");
                VariablesCaja.vRutaImpresora = UtilityConvenioBTLMF.obtieneRutaImpresora(pTipCompPago);

                if (FarmaVariables.vAceptar) {
                    //KMONCADA 06.05.2016 IMPRESION DE COMPROBANTES MANUALES
                    if(FarmaVariables.vCodCia.equals(ConstantsPtoVenta.CODCIA_ARCANGEL) || 
                            FarmaVariables.vCodCia.equals(ConstantsPtoVenta.CODCIA_JORSA)){
                        UtilityCaja.imprimirComprobantePago(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago,  ruta);
                    }else{
                        imprimeBoleta(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante, pValIgvComPago,
                                  pValCopagoCompPago, pValIgvCompCoPago, pNumCompRef, ruta, true, pImprDatAdic,
                                  pTipoClienteConvenio, pCodTipoConvenio, pFechaCreacionComp, pValRedondeoCompPago);
                    }
                }

            } else if (pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_TICKET)) { //JCORTEZ  25.03.09
                muestraMensajeImpresion(pJDialog, ConstantsVentas.TIPO_COMP_TICKET, ++acumuCorreTik,
                                        VariablesConvenioBTLMF.vSecCompPago, VariablesCaja.vNumPedVta, false,
                                        isReimpresion); //pNumComprobante --Cambio por CESAR HUANES 18.09.2014
                //muestraMensajeImpresion(pJDialog,ConstantsVentas.TIPO_COMP_TICKET,++acumuCorreTik,pNumComprobante);//LTAVARA 18.09.2014 VOLVIO A SU ESTADO ANTIGUO PORQUE SE MODIFICO EL PROCEDIMIENTO

                ruta = ruta + fechaImpresion + "_" + "TB_" + VariablesCaja.vNumPedVta + "_" + pNumComprobante + ".TXT";
                //secImprLocal = DBCaja.getObtieneSecImpPorIP(FarmaVariables.vIpPc, ConstantsVentas.TIPO_COMP_TICKET, "N");
                VariablesCaja.vRutaImpresora = UtilityConvenioBTLMF.obtieneRutaImpresora(pTipCompPago);

                //impresion
                if (FarmaVariables.vAceptar) {
                    // KMONCADA 24.07.2015 OBTIENE DATOS DE PUNTOS OBTENIDOS
                    List lstPuntos = null;
                    try{
                        lstPuntos = DBPuntos.imprVouPtosTicket(VariablesCaja.vNumPedVta, "N");
                    }catch(Exception ex){
                        lstPuntos = new ArrayList();
                    }
                    
                    imprimeTicket(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante, pValIgvComPago,
                                  pValCopagoCompPago, pValIgvCompCoPago, pNumCompRef, ruta, vIndImpresionAnulado,
                                  pImprDatAdic, pTipoClienteConvenio, pCodTipoConvenio, pFechaCreacionComp,
                                  pValRedondeoCompPago, valor, pRefTipComp, pTipCompPago, vPrctBeneficiario,
                                  vPrctEmpresa, vIndImprimeEmpresa, lstPuntos);
                }


            } else if (pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_GUIA)) {
                muestraMensajeImpresion(pJDialog, ConstantsVentas.TIPO_COMP_GUIA, ++acumuCorreGuia,
                                        VariablesConvenioBTLMF.vSecCompPago, VariablesCaja.vNumPedVta, false,
                                        isReimpresion); //pNumComprobante --Cambio por CESAR HUANES 18.09.2014
                //muestraMensajeImpresion(pJDialog,ConstantsVentas.TIPO_COMP_GUIA,++acumuCorreGuia,pNumComprobante);//LTAVARA 18.09.2014 VOLVIO A SU ESTADO ANTIGUO PORQUE SE MODIFICO EL PROCEDIMIENTO
                if (FarmaVariables.vAceptar) {
                    ruta = ruta + fechaImpresion + "_" + "G_" + VariablesCaja.vNumPedVta + "_" + pNumComprobante + ".TXT";
                    //secImprLocal = VariablesCaja.vSecImprLocalGuia;
                    VariablesCaja.vRutaImpresora = UtilityConvenioBTLMF.obtieneRutaImpresora(pTipCompPago);
                    //impresion
                    if (FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_MIFARMA)) {
                        log.info("****************************************IMPRIMIENDO FORMATO MIFARMA*********************************");
                        imprimeGuia(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante, pValIgvComPago,
                                    pValCopagoCompPago, pValIgvCompCoPago, pNumCompRef, ruta, true, pImprDatAdic,
                                    pTipoClienteConvenio, pCodTipoConvenio, pFechaCreacionComp, pRefTipComp,
                                    pValRedondeoCompPago,
                                    vPrctBeneficiario, vPrctEmpresa);
                    } else if (FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_FASA)) {
                        log.info("****************************************IMPRIMIENDO FORMATO FASA*********************************");
                        imprimeGuiaFasa(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante, pValIgvComPago,
                                        pValCopagoCompPago, pValIgvCompCoPago, pNumCompRef, ruta, true, pImprDatAdic,
                                        pTipoClienteConvenio, pCodTipoConvenio, pFechaCreacionComp, pRefTipComp,
                                        pValRedondeoCompPago,
                                        vPrctBeneficiario, vPrctEmpresa);

                    }

                    else if (FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_BTL) ||
                               FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_BTL_AMAZONIA)) {
                        log.info("****************************************IMPRIMIENDO FORMATO BTL*********************************");
                        imprimeGuiaBTL(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante, pValIgvComPago,
                                       pValCopagoCompPago, pValIgvCompCoPago, pNumCompRef, ruta, true, pImprDatAdic,
                                       pTipoClienteConvenio, pCodTipoConvenio, pFechaCreacionComp, pRefTipComp,
                                       pValRedondeoCompPago, vPrctBeneficiario, vPrctEmpresa, margen);
                    //KMONCADA 06.05.2016 IMPRESION DE COMPROBANTES MANUALES
                    }else if(FarmaVariables.vCodCia.equals(ConstantsPtoVenta.CODCIA_ARCANGEL) || 
                            FarmaVariables.vCodCia.equals(ConstantsPtoVenta.CODCIA_JORSA)){
                        UtilityCaja.imprimirComprobantePago(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago,  ruta);
                    }
                }
                //ERIOS 21.07.2015 Imprime voucher copia guia
                FacadeConvenioBTLMF facadeConvenioBTLMF = new FacadeConvenioBTLMF();
                facadeConvenioBTLMF.impresionVoucherCopiaGuia(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago);
            } else if (pTipCompPago.equalsIgnoreCase(ConstantsVentas.TIPO_COMP_FACTURA)) {
                muestraMensajeImpresion(pJDialog, ConstantsVentas.TIPO_COMP_FACTURA, ++acumuCorreFac,
                                        VariablesConvenioBTLMF.vSecCompPago, VariablesCaja.vNumPedVta, false,
                                        isReimpresion); //pNumComprobante --Cambio por CESAR HUANES 18.09.2014
                // muestraMensajeImpresion(pJDialog,ConstantsVentas.TIPO_COMP_FACTURA,++acumuCorreFac,pNumComprobante);//LTAVARA 18.09.2014 VOLVIO A SU ESTADO ANTIGUO PORQUE SE MODIFICO EL PROCEDIMIENTO

                ruta = ruta + fechaImpresion + "_" + "F_" + VariablesCaja.vNumPedVta + "_" + pNumComprobante + ".TXT";
                //secImprLocal = VariablesCaja.vSecImprLocalFactura;
                VariablesCaja.vRutaImpresora = UtilityConvenioBTLMF.obtieneRutaImpresora(pTipCompPago);

                //impresion
                if (FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_MIFARMA)) {
                    log.info("****************************************IMPRIMIENDO FACTURA MIFARMA*********************************");
                    if (FarmaVariables.vAceptar) {
                        imprimeFactura(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante, pValIgvComPago,
                                       pValCopagoCompPago, pValIgvCompCoPago, pNumCompRef, ruta, true, pImprDatAdic,
                                       pTipoClienteConvenio, pCodTipoConvenio, pFechaCreacionComp, pRefTipComp,
                                       pValRedondeoCompPago);
                    }

                } else if (FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_FASA)) {
                    log.info("****************************************IMPRIMIENDO FACTURA FASA*********************************");
                    if (FarmaVariables.vAceptar) {
                        imprimeFacturaFasa(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante,
                                           pValIgvComPago, pValCopagoCompPago, pValIgvCompCoPago, pNumCompRef, ruta,
                                           true, pImprDatAdic, pTipoClienteConvenio, pCodTipoConvenio,
                                           pFechaCreacionComp, pRefTipComp, pValRedondeoCompPago);
                    }

                } else if (FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_BTL)||
                            FarmaVariables.vCodCia.equals(ConstantsRecaudacion.CODGRUPOCIA_BTL_AMAZONIA)) {
                    if (FarmaVariables.vAceptar) {
                        imprimeFacturaBTL(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante,
                                          pValIgvComPago, pValCopagoCompPago, pPorcIgv, pNumCompRef, ruta,
                                          pValTotalAhorro, pImprDatAdic, pTipoClienteConvenio, pCodTipoConvenio,
                                          pFechaCreacionComp, pRefTipComp, pValRedondeoCompPago, vPrctBeneficiario,
                                          vPrctEmpresa, margen);
                    }
                //KMONCADA 06.05.2016 IMPRESION DE COMPROBANTES MANUALES
                }else if(FarmaVariables.vCodCia.equals(ConstantsPtoVenta.CODCIA_ARCANGEL) || 
                            FarmaVariables.vCodCia.equals(ConstantsPtoVenta.CODCIA_JORSA)){
                    if (FarmaVariables.vAceptar) {
                        UtilityCaja.imprimirComprobantePago(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago,  ruta);
                    }
                }


            }
        }
        
        // KMONCADA 18.12.2014 SOLO EN EL CASO DE NO ELECTRONICO
        //if (!EposVariables.vFlagComprobanteE) {
        if (!UtilityCPE.isActivoFuncionalidad()) {
            UtilityCaja.abrirGabeta(null, false, VariablesCaja.vNumPedVta);
        }
    }

    private static void imprimeFactura(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                       String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                       String pValIgvComCoPago, String pNumCompCoPago, String pRuta, boolean bol,
                                       String pImprDatAdic, String pTipoClienteConvenio, String pCodTipoConvenio,
                                       String pFechaBD, String pRefTipComp,
                                       String pValRedondeoComPago) throws Exception {
        log.debug("IMPRIMIR FACTURA No : " + pNumComprobante);

        VariablesCaja.vIndPedidoConProdVirtualImpresion = false;
        float subTotal = 0;
        float montoIGV = 0;
        float SumSubTotal = 0;
        double SumMontoIGV = 0;


        String pNomImpreso = " ";
        String pDirImpreso = " ";

        //Comentado por FRAMIREZ
        //FarmaPrintService vPrint = new FarmaPrintService(36, VariablesCaja.vRutaImpresora, false);
        //VariablesCaja.vRutaImpresora = "/\\/10.11.1.54/reporte1";
        FarmaPrintService vPrint = new FarmaPrintService(36, VariablesCaja.vRutaImpresora, false);

        //JCORTEZ 16.07.09 Se genera archivo linea por linea
        FarmaPrintServiceTicket vPrintArchivo = new FarmaPrintServiceTicket(666, pRuta, false);
        vPrintArchivo.startPrintService();

        log.debug("vRutaImpresora : " + VariablesCaja.vRutaImpresora);
        log.debug("Ruta : " + pRuta);
        //  if ( !vPrint.startPrintService() )  throw new Exception("Error en Impresora. Verifique !!!");
        log.debug("VariablesCaja.vNumPedVta:" + VariablesCaja.vNumPedVta);
        if (!vPrint.startPrintService()) {
            VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            log.info("**** Fecha :" + pFechaBD);
            log.info("**** CORR :" + VariablesCaja.vNumPedVta);
            log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
            log.info("**** IP :" + FarmaVariables.vIpPc);
            log.info("ERROR DE IMPRESORA : No se pudo imprimir la boleta");
        } else {
            try {
                vPrint.activateCondensed();
                String dia = pFechaBD.substring(0, 2);
                String mesLetra = FarmaUtility.devuelveMesEnLetras(Integer.parseInt(pFechaBD.substring(3, 5)));
                String ano = pFechaBD.substring(6, 10);
                String hora = ""; // pFechaBD.substring(11,19);

                if (VariablesPtoVenta.vIndDirMatriz) {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz,
                                            true);
                }
                //JMIRANDA 22.08.2011 Cambio para verificar si imprime
                if (UtilityVentas.getIndImprimeCorrelativo()) {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                     VariablesCaja.vNumPedVta, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                            VariablesCaja.vNumPedVta, true);
                } else {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(17) +
                                     VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim(), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(17) +
                                            VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim(), true);
                }

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(17) +
                                 FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vDireccion.trim(), 70), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(17) +
                                        FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vDireccion.trim(), 70),
                                        true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(17) + VariablesConvenioBTLMF.vRuc.trim(), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(17) + VariablesConvenioBTLMF.vRuc.trim(), true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(17) + dia + " de " + mesLetra + " del " + ano +
                                 "     " + hora + FarmaPRNUtility.llenarBlancos(50) + "No. " +
                                 pNumComprobante.substring(0, 3) + "-" + pNumComprobante.substring(3, 10), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(17) + dia + " de " + mesLetra + " del " + ano +
                                        "     " + hora + FarmaPRNUtility.llenarBlancos(50) + "No. " +
                                        pNumComprobante.substring(0, 3) + "-" + pNumComprobante.substring(3, 10),
                                        true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(12) + "     ", true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(12) + "     ", true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                int linea = 0;

                int vNroEspacio = 0;
                for (int i = 0; i < pDetalleComprobante.size(); i++) {
                    vPrint.printLine(" " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                      6) + " " +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                    11) + "   " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                      38) + "   " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                                      14) + "   " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                                      20) + FarmaPRNUtility.llenarBlancos(2) +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(4)).trim(),
                                                                    13) + FarmaPRNUtility.llenarBlancos(4) +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim(),
                                                                    10), true);

                    vPrintArchivo.printLine(" " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                             6) + " " +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                           11) + "   " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                             38) + "   " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                                             14) + "   " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                                             20) + FarmaPRNUtility.llenarBlancos(2) +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(4)).trim(),
                                                                           13) + FarmaPRNUtility.llenarBlancos(4) +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim(),
                                                                           10), true);

                    log.debug("SubTotal String:::" + ((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim());
                    linea += 1;
                    subTotal =
                            new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim())).floatValue();
                    montoIGV =
                            new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(18)).trim())).floatValue();
                    SumMontoIGV = SumMontoIGV + montoIGV;
                    log.debug("SubTotal:" + subTotal);
                    SumSubTotal = SumSubTotal + subTotal;
                }

                log.debug("SumSubTotal:" + SumSubTotal);

                //*************************************INFORMACION DEL CONVENIO***********************************************//
                double porcCopago =
                    Math.round((FarmaUtility.getDecimalNumber(pValCopagoCompPago) / (FarmaUtility.getDecimalNumber(pValTotalNeto) +
                                                                                     FarmaUtility.getDecimalNumber(pValCopagoCompPago))) *
                               100);
                SumMontoIGV = SumMontoIGV - ((SumMontoIGV * porcCopago) / 100);
                double ValCopagoCompPagoSinIGV = ((SumSubTotal * porcCopago) / 100);

                vPrint.printLine("      " + FarmaPRNUtility.alinearIzquierda(" ", 85) + "        " +
                                 "    Sub Total   "+ConstantesUtil.simboloSoles+" " +
                                 FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10), true);
                vPrintArchivo.printLine("      " + FarmaPRNUtility.alinearIzquierda(" ", 85) + "        " +
                                        "    Sub Total   "+ConstantesUtil.simboloSoles+" "+
                                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10),
                                        true);

                double pValTotalNetoRedondeo =
                    FarmaUtility.getDecimalNumber(pValTotalNeto) + FarmaUtility.getDecimalNumber(pValRedondeoComPago);
                pValTotalNetoRedondeo =
                        FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(pValTotalNetoRedondeo, 2)); //CHUANES 12.03.2014 SE PONE EL FORMATO DE 2 DECIMALES AL VALOR REDONDEADO
                //ERIOS 12.09.2013 Imprime direccion local
                String vLinea = "", vLineaDirecLocal1 = "", vLineaDirecLocal2 = "", vLineaDirecLocal3 = "";
                if (VariablesPtoVenta.vIndDirLocal) {
                    ArrayList lstDirecLocal =
                        FarmaUtility.splitString("NUEVA DIRECCION: " + FarmaVariables.vDescCortaDirLocal, 46);
                    vLineaDirecLocal1 = lstDirecLocal.get(0).toString();
                    vLineaDirecLocal2 = ((lstDirecLocal.size() > 1) ? lstDirecLocal.get(1).toString() : "");
                    vLineaDirecLocal3 = ((lstDirecLocal.size() > 2) ? lstDirecLocal.get(2).toString() : "");
                }
                
                if (pCodTipoConvenio.equals("1") || pCodTipoConvenio.equals("3")) {
                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  SON: " + FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo),
                                                             85) + "            " + "Coaseguro(" +
                            FarmaUtility.formatNumber(porcCopago, 0) + "%)    "+ConstantesUtil.simboloSoles+" " +
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(ValCopagoCompPagoSinIGV), 10);
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("              " + "     ", 85) + "                       ---------------------";
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Instituci�n: " + VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim() +
                                                             "  (" + FarmaUtility.formatNumber(100 - porcCopago, "") +
                                                             ")%", 85) + "                                  " +
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal -
                                                                                     ValCopagoCompPagoSinIGV), 10);
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase().trim(),
                                                             85) + vLineaDirecLocal1;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Beneficiario: " + VariablesConvenioBTLMF.vNomCliente, 85) +
                            vLineaDirecLocal2;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Documento de Referencia Nro : " + pNumCompCoPago, 85) +
                            vLineaDirecLocal3;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Doc refe de la Empresa Monto:"+ConstantesUtil.simboloSoles + pValCopagoCompPago +
                                                             " y (" + FarmaUtility.formatNumber(porcCopago, "") + "%)",
                                                             85) + "                       ";
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                } else {
                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  SON: " + FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo),
                                                             85) + "            ";
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("              " + "     ", 85) + "                       ---------------------";
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Instituci�n: " + VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim() +
                                                             "  (" + FarmaUtility.formatNumber(100 - porcCopago, "") +
                                                             ")%", 85) + vLineaDirecLocal1;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase().trim(),
                                                             85) + vLineaDirecLocal2;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    String lineaBeneficiario = FormatoImpresion.muestraBeneficiario();
                    vLinea = FarmaPRNUtility.alinearIzquierda(lineaBeneficiario, 85) + vLineaDirecLocal3;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);
                }

                int var = 0;
                if (pImprDatAdic.equals("1")) {
                    if (VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic != null &&
                        VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 0) {
                        var = 3;
                    }
                }

                if (linea == 5)
                    vNroEspacio = 3 - var;
                if (linea == 4)
                    vNroEspacio = 4 - var;
                if (linea == 3)
                    vNroEspacio = 5 - var;
                if (linea == 2)
                    vNroEspacio = 6 - var;
                if (linea == 1)
                    vNroEspacio = 7 - var;

                for (int c = 0; c < vNroEspacio; c++) {
                    vPrintArchivo.printLine(" " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " ", true);
                    vPrint.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65), true);
                }

                if (pImprDatAdic.equals("1")) {
                    if (VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic != null &&
                        VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 0) {
                        vPrintArchivo.printLine("  Datos Adicionales " + " ", true);
                        vPrint.printLine("  Datos Adicionales", true);

                        for (int j = 0; j < VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size(); j++) {
                            Map datosAdicConv = (Map)VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.get(j);

                            String pNombCampo = (String)datosAdicConv.get("NOMBRE_CAMPO");
                            String pDesCampo = (String)datosAdicConv.get("DESCRIPCION_CAMPO");
                            String vFlgImprime = (String)datosAdicConv.get("FLG_IMPRIME");
                            String vCodCampo = (String)datosAdicConv.get("COD_CAMPO");

                            log.debug("pDesCampo   :" + pNombCampo);
                            log.debug("pNombCampo  :" + pNombCampo);
                            log.debug("vFlgImprime :" + vFlgImprime);
                            log.debug("vCodCampo :" + vCodCampo);

                            if (vFlgImprime.equals("1") || vFlgImprime.equals("2")) {
                                if (vCodCampo.equals(ConstantsConvenioBTLMF.COD_NOMB_TITULAR) ||
                                    vCodCampo.equals(ConstantsConvenioBTLMF.COD_NRO_ATENCION)) {
                                    vPrintArchivo.printLine("  - " + pNombCampo + "    " + pDesCampo + " ", false);
                                    vPrint.printLine("  - " + pNombCampo + "    " + pDesCampo + " ", false);
                                }
                            }
                        }
                    }
                }

                vPrintArchivo.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " ", true);
                vPrint.printLine("   " + FarmaPRNUtility.alinearIzquierda(" ", 65), true);
                vPrintArchivo.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " ", true);
                vPrint.printLine("   " + FarmaPRNUtility.alinearIzquierda(" ", 65), true);

                //LLEIVA 26-Nov-2013 A�ade linea de Porc de IGV
                vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda(" ", 90) + 
                                        "" + UtilityVentas.obtenerIgvLocal() + //ASOSA - 25/06/2015 - IGVAZONIA
                                        "%", true);
                vPrint.printLine(FarmaPRNUtility.alinearIzquierda(" ", 90) + 
                                 "" + UtilityVentas.obtenerIgvLocal() + //ASOSA - 25/06/2015 - IGVAZONIA
                                 "%", true);

                //ERIOS 03.03.2015 El modifica el calculo de subtotal
                SumMontoIGV = FarmaUtility.getDecimalNumber(pValIgvComPago);
                double neto = pValTotalNetoRedondeo - SumMontoIGV;
                vLinea = "     " +
                                 FarmaPRNUtility.alinearIzquierda("                                                       " +
                                                                  FarmaUtility.formatNumber(neto),85) 
                         + FarmaUtility.formatNumber(SumMontoIGV, 2) +"               " 
                         + FarmaUtility.formatNumber(pValTotalNetoRedondeo) + "          ";
                vPrint.printLine(vLinea,true);
                vPrintArchivo.printLine(vLinea, true);

                vPrintArchivo.printLine(" " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " ", true);
                vPrint.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65), true);


                //		vPrintArchivo.printLine("  Instituci�n: " +VariablesConvenioBTLMF.vInstitucion.toUpperCase() + " ",true);
                //		vPrint.printLine("  Instituci�n: " +VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim(),true);
                //		vPrintArchivo.printLine("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase(),true);
                //		vPrint.printLine("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase().trim(),true);
                //		vPrintArchivo.printLine("  Beneficiario: " + VariablesConvenioBTLMF.vNomCliente,true);
                //		vPrint.printLine("  Beneficiario: " + VariablesConvenioBTLMF.vNomCliente,true);

                if (pCodTipoConvenio.equals("1")) {
                    //		 vPrintArchivo.printLine("  Documento de Referencia Nro: "+pNumCompCoPago+" ",true);
                    //		 vPrint.printLine("  Documento de Referencia Nro "+pNumCompCoPago+": ",true);
                    //		 vPrintArchivo.printLine("  Doc refe de la Empresa Monto:"+ConstantesUtil.simboloSoles + pValCopagoCompPago +" y ("+FarmaUtility.formatNumber(porcCopago,"")+"%)",true);
                    //		 vPrint.printLine("  Doc refe de la Empresa Monto:"+ConstantesUtil.simboloSoles + pValCopagoCompPago +" y ("+FarmaUtility.formatNumber(porcCopago,"")+"%)",true);
                }


                vPrint.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " + VariablesCaja.vNomCajeroImpreso +
                                 " " + VariablesCaja.vApePatCajeroImpreso + " " + " CAJA: " +
                                 VariablesCaja.vNumCajaImpreso + " TURNO: " + VariablesCaja.vNumTurnoCajaImpreso +
                                 " VEND: " + VariablesCaja.vNomVendedorImpreso + " " +
                                 VariablesCaja.vApePatVendedorImpreso, true);
                vPrintArchivo.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " +
                                        VariablesCaja.vNomCajeroImpreso + " " + VariablesCaja.vApePatCajeroImpreso +
                                        " " + " CAJA: " + VariablesCaja.vNumCajaImpreso + " TURNO: " +
                                        VariablesCaja.vNumTurnoCajaImpreso + " VEND: " +
                                        VariablesCaja.vNomVendedorImpreso + " " + VariablesCaja.vApePatVendedorImpreso,
                                        true);


                vPrint.deactivateCondensed();
                vPrint.endPrintService();
                vPrintArchivo.endPrintService();

                log.info("Fin al imprimir la boleta: " + pNumComprobante);
                VariablesCaja.vEstadoSinComprobanteImpreso = "N";

                //JCORTEZ 16.07.09 Se guarda fecha de impresion por comprobantes
                DBCaja.actualizaFechaImpr(VariablesCaja.vNumPedVta, pNumComprobante, "C");
                log.debug("Guardando fecha impresion cobro..." + pNumComprobante);
            } catch (SQLException sql) {
                log.error("", sql);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                // enviaErrorCorreoPorDB(sql.toString(),VariablesCaja.vNumPedVta);
            } catch (Exception e) {
                log.error("", e);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                log.info("Error al imprimir la Factura: " + e);
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                //enviaErrorCorreoPorDB(e.toString(),VariablesCaja.vNumPedVta);
            }
        }
    }

    private static void imprimeTicket(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                      String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                      String pValIgvComCoPago, String pNumCompCoPago, String pRuta,
                                      boolean vIndImpresionAnulado, String pImprDatAdic, String pTipoClienteConvenio,
                                      String pCodTipoConvenio, String pFechaBD, String pValRedondeoComPago, int valor,
                                      String pRefTipComp, String pTipCompPago, String vPrctBeneficiario,
                                      String vPrctEmpresa, String vIndImprimeEmpresa, List lstPuntos) throws Exception {

        String secImprLocal = DBCaja.getObtieneSecImpPorIP(FarmaVariables.vIpPc, ConstantsVentas.TIPO_COMP_TICKET, "N");
        VariablesCaja.vModeloImpresora = DBImpresoras.getModeloImpresora(secImprLocal);

        //*****
        ArrayList<String> vPrint = new ArrayList<String>();
        String indProdVirtual = "";
        VariablesCaja.vIndPedidoConProdVirtualImpresion = false;

        FarmaVariables.vNroPedidoNoImp = VariablesCaja.vNumPedVta;

        try {

            ImpresoraTicket ticketera = new ImpresoraTicket();
            ticketera.generarDocumentoConvenio(pJDialog, vPrint, "", "", pFechaBD, pNumComprobante,
                                               pDetalleComprobante, pValTotalNeto, "0.0", "0.0",
                                               VariablesCaja.vModeloImpresora, vIndImpresionAnulado, pTipCompPago,
                                               valor, pValCopagoCompPago, pCodTipoConvenio, pTipoClienteConvenio,
                                               pValRedondeoComPago, pRefTipComp, pNumCompCoPago, pImprDatAdic,
                                               vPrctBeneficiario, vPrctEmpresa, vIndImprimeEmpresa, lstPuntos);
            boolean bImprimio =
                ticketera.imprimir(vPrint, VariablesCaja.vModeloImpresora, VariablesCaja.vRutaImpresora, true,
                                   pNumComprobante, "C", VariablesCaja.vNumPedVta, pTipCompPago);

            if (!bImprimio) {
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            } else {
                VariablesCaja.vEstadoSinComprobanteImpreso = "N";
            }
        } catch (SQLException sql) {
            log.error("", sql);
            VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            
            log.info("**** Fecha :" + pFechaBD);
            log.info("**** CORR :" + VariablesCaja.vNumPedVta);
            log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
            log.info("**** IP :" + FarmaVariables.vIpPc);
            
            //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
            // enviaErrorCorreoPorDB(sql.toString(),VariablesCaja.vNumPedVta);
        } catch (Exception e) {
            log.error("", e);
            VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            log.info("**** Fecha :" + pFechaBD);
            log.info("**** CORR :" + VariablesCaja.vNumPedVta);
            log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
            log.info("**** IP :" + FarmaVariables.vIpPc);
            log.info("Error al imprimir la Ticket: " + e);
            //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
            //enviaErrorCorreoPorDB(e.toString(),VariablesCaja.vNumPedVta);
        }

    }


    private static void imprimeGuia(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                    String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                    String pValIgvComCoPago, String pNumCompCoPago, String pRuta, boolean bol,
                                    String pImprDatAdic, String pTipoClienteConvenio, String pCodTipoConvenio,
                                    String pFechaBD, String pRefTipComp, String pValRedondeoComPago,
                                    String vPrctBeneficiario, String vPrctEmpresa) throws Exception

    {

        String pNomImpreso = "";
        //String pDirImpreso = "";

        log.debug("IMPRIMIR GUIA No : " + pNumComprobante);

        VariablesCaja.vIndPedidoConProdVirtualImpresion = false;
        float subTotal = 0;
        float SumSubTotal = 0;
        float montoIGV = 0;
        double SumMontoIGV = 0;

        //Comentado por FRAMIREZ
        //FarmaPrintService vPrint = new FarmaPrintService(30, VariablesCaja.vRutaImpresora, false);
        //VariablesCaja.vRutaImpresora = "/\\/10.11.1.54/reporte1";
        FarmaPrintService vPrint = new FarmaPrintService(36, VariablesCaja.vRutaImpresora, false);

        //JCORTEZ 16.07.09 Se genera archivo linea por linea
        FarmaPrintServiceTicket vPrintArchivo = new FarmaPrintServiceTicket(666, pRuta, false);
        vPrintArchivo.startPrintService();

        log.debug("vRutaImpresora : " + VariablesCaja.vRutaImpresora);
        log.debug("Ruta : " + pRuta);

        //  if ( !vPrint.startPrintService() )  throw new Exception("Error en Impresora. Verifique !!!");
        log.debug("VariablesCaja.vNumPedVta:" + VariablesCaja.vNumPedVta);
        if (!vPrint.startPrintService()) {
            VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            log.info("**** Fecha :" + pFechaBD);
            log.info("**** CORR :" + VariablesCaja.vNumPedVta);
            log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
            log.info("**** IP :" + FarmaVariables.vIpPc);
            log.info("ERROR DE IMPRESORA : No se pudo imprimir la boleta");
        } else {
            try {

                vPrint.activateCondensed();

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(30) + " ", true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(30) + " ", true);

                if (VariablesPtoVenta.vIndDirMatriz) {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz,
                                            true);
                } else {
                    vPrint.printLine(" ", true);
                    vPrintArchivo.printLine(" ", true);
                }

                //ERIOS 12.09.2013 Imprime direccion local
                if (VariablesPtoVenta.vIndDirLocal) {
                    vPrint.printLine("     " + "NUEVA DIRECCION: " + FarmaVariables.vDescCortaDirLocal, true);
                } else {
                    vPrint.printLine("", true);
                }

                //JMIRANDA 22.08.2011 Cambio para verificar si imprime
                if (UtilityVentas.getIndImprimeCorrelativo()) {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                     VariablesCaja.vNumPedVta, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                            VariablesCaja.vNumPedVta, true);
                } else {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + " ", true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + " ", true);
                }

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                 FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 60), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                        FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 60), true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                 FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 60), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                        FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 60), true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + FarmaPRNUtility.alinearIzquierda(pFechaBD, 60) +
                                 "   No. " + pNumComprobante.substring(0, 3) + "-" + pNumComprobante.substring(3, 10),
                                 true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                        FarmaPRNUtility.alinearIzquierda(pFechaBD, 60) + "   No. " +
                                        pNumComprobante.substring(0, 3) + "-" + pNumComprobante.substring(3, 10),
                                        true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + VariablesConvenioBTLMF.vInstitucion.trim(), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + VariablesConvenioBTLMF.vInstitucion.trim(),
                                        true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + VariablesConvenioBTLMF.vRuc.trim(), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + VariablesConvenioBTLMF.vRuc.trim(), true);
                log.info(" -> VariablesConvenioBTLMF.vDireccion: "+VariablesConvenioBTLMF.vDireccion.trim());
                boolean impMasDatos=false;
                if(VariablesConvenioBTLMF.vDireccion.trim().length()<=60){
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                     FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vDireccion.trim(), 60), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                            FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vDireccion.trim(), 60),
                                            true);
                }else{
                    String direc_p1=VariablesConvenioBTLMF.vDireccion.trim().substring(0,58)+"_";
                    String direc_p2=VariablesConvenioBTLMF.vDireccion.trim().substring(58);
                    impMasDatos = true;
                    
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                     FarmaPRNUtility.alinearIzquierda(direc_p1, 60), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                            FarmaPRNUtility.alinearIzquierda(direc_p1, 60),
                                            true);
                    
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                     FarmaPRNUtility.alinearIzquierda(direc_p2, 60), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                            FarmaPRNUtility.alinearIzquierda(direc_p2, 60),
                                            true);
                }
                //------------------------------------------------------------------------------------------------------
                log.info("VariablesVentas.vPuntoLlegada: "+VariablesVentas.vPuntoLlegada.trim());
                log.info("VariablesConvenioBTLMF.vPuntoLlegada: "+VariablesConvenioBTLMF.vPuntoLlegada.trim());
                
                if(VariablesConvenioBTLMF.vPuntoLlegada.trim().length()!=0){
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                     FarmaPRNUtility.alinearIzquierda("OC: " + VariablesConvenioBTLMF.vNumOrdeCompra, 20)+
                                     FarmaPRNUtility.llenarBlancos(49)+
                                     FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vPuntoLlegada, 50), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                     FarmaPRNUtility.alinearIzquierda("OC: " + VariablesConvenioBTLMF.vNumOrdeCompra, 20)+
                                     FarmaPRNUtility.llenarBlancos(49)+
                                     FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vPuntoLlegada, 50), true);
                    
                }else{
                    //------------------------------------------------------------------------------------------------------
                    //INI ASOSA  - 27/07/2018 - MODIFICARGUIAMF
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                    FarmaPRNUtility.alinearIzquierda("OC: " + VariablesConvenioBTLMF.vNumOrdeCompra, 20), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                    FarmaPRNUtility.alinearIzquierda("OC: " + VariablesConvenioBTLMF.vNumOrdeCompra, 20), true);
                    //FIN ASOSA  - 27/07/2018 - MODIFICARGUIAMF
                }
                
                if(!impMasDatos){
                    vPrint.printLine(" ", true);
                    vPrintArchivo.printLine(" ", true);
                }
                
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);


                int linea = 0;
                for (int i = 0; i < pDetalleComprobante.size(); i++) {
                    //Agregado por DVELIZ 13.10.08

                    String punitario = " ";
                    String valor = " ";

                    String colSubTotal = " ";
                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {
                        valor = ((String)((ArrayList)pDetalleComprobante.get(i)).get(16)).toString().trim();
                        log.debug("valor 1:" + valor);
                        if (valor.equals("0.000"))
                            valor = " ";
                        //fin DVELIZ
                        log.debug("Deta " + (ArrayList)pDetalleComprobante.get(i));
                        log.debug("valor 2:" + valor);
                        colSubTotal = (String)((ArrayList)pDetalleComprobante.get(i)).get(5);
                        punitario = (String)((ArrayList)pDetalleComprobante.get(i)).get(4).toString().trim();
                    }

                    vPrint.printLine("" +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                      6) + " " +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                    11) + "   " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                      27) + " " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                                      11) + "  " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                                      16) + "  " + 
                                     FarmaPRNUtility.alinearDerecha(punitario, 10) + " " +
                                     //INI ASOSA  - 27/07/2018 - MODIFICARGUIAMF
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(7)).trim(), 6) + " " +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(14)).trim(), 10) + " " +
                                     //FIN ASOSA  - 27/07/2018 - MODIFICARGUIAMF
                                    //Agregado por DVELIZ 10.10.08
                                     FarmaPRNUtility.alinearDerecha(valor, 8) + "" + 
                                     FarmaPRNUtility.alinearDerecha(colSubTotal.trim(), 10)
                                                ,true);


                    vPrintArchivo.printLine("" +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                             6) + " " +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                           11) + "   " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                             27) + " " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                                             11) + "  " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                                             16) + "  " +
                                            FarmaPRNUtility.alinearDerecha(punitario, 10) + " " +
                                            //INI ASOSA  - 27/07/2018 - MODIFICARGUIAMF
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(7)).trim(), 6) + " " +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(14)).trim(), 10) + " " +
                                            //FIN ASOSA  - 27/07/2018 - MODIFICARGUIAMF
                            //Agregado por DVELIZ 10.10.08
                            FarmaPRNUtility.alinearDerecha(valor, 8) + "" +
                            FarmaPRNUtility.alinearDerecha(colSubTotal.trim(), 10), true);

                    linea += 1;
                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {
                        subTotal =
                                new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim())).floatValue();
                        log.debug("SubTotal:" + subTotal);
                        SumSubTotal = SumSubTotal + subTotal;
                        montoIGV =
                                new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(18)).trim())).floatValue();
                        SumMontoIGV = SumMontoIGV + montoIGV;

                    }
                }


                log.debug("SumSubTotal:" + SumSubTotal);

                //*************************************INFORMACION DEL CONVENIO***********************************************//
                double porcCopagoBenef = 0;
                double porcCopagoEmpresa = 0;
                double ValCopagoCompPagoSinIGV = 0;

                porcCopagoBenef = FarmaUtility.getDecimalNumber(vPrctBeneficiario);
                porcCopagoEmpresa = FarmaUtility.getDecimalNumber(vPrctEmpresa);

                ValCopagoCompPagoSinIGV =
                        FarmaUtility.getDecimalNumber(pValCopagoCompPago) - FarmaUtility.getDecimalNumber(pValIgvComCoPago);

                String vRefTipComp = "";

                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_BOLETA))
                    vRefTipComp = "BOL";
                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_FACTURA))
                    vRefTipComp = "FAC";
                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_GUIA))
                    vRefTipComp = "GUIA";
                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_TICKET))
                    vRefTipComp = "TKB";

                String vLinea;
                if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {

                    vLinea = FarmaPRNUtility.llenarBlancos(69)+
                            FarmaPRNUtility.alinearDerecha("Sub Total   "+ConstantesUtil.simboloSoles+" ",22) +
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10);
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);
                }

                // KMONCADA 17.11.2014 MOSTRAR DOCUMENTO DE REFERENCIA
                if (pCodTipoConvenio.equals("1") || pCodTipoConvenio.equals("3")) {
                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {

                        double pValTotalNetoRedondeo =
                            FarmaUtility.getDecimalNumber(pValTotalNeto) + FarmaUtility.getDecimalNumber(pValRedondeoComPago);
                        pValTotalNetoRedondeo =
                                FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(pValTotalNetoRedondeo,
                                                                                        2)); //CHUANES 12.03.2014 SE PONE EL FORMATO DE 2 DECIMALES AL VALOR REDONDEADO
                        
                        vLinea = FarmaPRNUtility.alinearIzquierda(" SON:" +FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo).trim(),69)+
                                FarmaPRNUtility.alinearDerecha("Coaseguro(" + FarmaUtility.formatNumber(porcCopagoEmpresa, 0) + "%)" + "   "+ConstantesUtil.simboloSoles+" ",22)+
                                FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(ValCopagoCompPagoSinIGV),10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                        
                        vLinea = FarmaPRNUtility.llenarBlancos(78)+"---------------------";
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                        
                        vLinea = FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase() +
                                                                          "  (" +
                                                                          FarmaUtility.formatNumber(100 - porcCopagoEmpresa,
                                                                                                    "") + ")%",69)+
                                FarmaPRNUtility.alinearDerecha(ConstantesUtil.simboloSoles+" ",22)+
                                FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal -ValCopagoCompPagoSinIGV),10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);

                        vLinea = FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          69)+
                                FarmaPRNUtility.alinearDerecha("IGV   "+ConstantesUtil.simboloSoles+" ",22)+
                                FarmaPRNUtility.alinearDerecha(pValIgvComPago, 10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                        
                        vLinea = FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +VariablesConvenioBTLMF.vNomCliente, 78)+"---------------------";
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                        
                        String lineaReferencia = " ";
                        if (vRefTipComp != null && !vRefTipComp.trim().equals("")) {
                            lineaReferencia = "  #REF: " + vRefTipComp + " " +pNumCompCoPago + "(" +
                                                                              FarmaUtility.formatNumber(porcCopagoEmpresa,
                                                                                                        "") + ")" +
                                                                              " - " + ""+ConstantesUtil.simboloSoles + pValCopagoCompPago;                             
                        }
                        vLinea = FarmaPRNUtility.alinearIzquierda(lineaReferencia,69)+
                        FarmaPRNUtility.alinearDerecha("Total   "+ConstantesUtil.simboloSoles+" ",22)+
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo), 10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                    } else {
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase() +
                                                                          "  (" +
                                                                          FarmaUtility.formatNumber(100 - porcCopagoEmpresa,
                                                                                                    "") + ")%", 65) +
                                         "                      ", true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                                 VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase() +
                                                                                 "  (" +
                                                                                 FarmaUtility.formatNumber(100 -
                                                                                                           porcCopagoEmpresa,
                                                                                                           "") + ")%",
                                                                                 65) + "                            ",
                                                true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          65) + "     " + " " + "          ", true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                          VariablesConvenioBTLMF.vNomCliente, 65) +
                                         "     " + " " + "          ", true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                                 VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                                 65) + "     " + " " + "          ",
                                                true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                 VariablesConvenioBTLMF.vNomCliente,
                                                                                 65) + "     " + " " + "          ",
                                                true);

                        if (vRefTipComp != null && !vRefTipComp.trim().equals("")) {
                            vPrint.printLine("  #REF: " + vRefTipComp + " " +
                                             FarmaPRNUtility.alinearIzquierda(pNumCompCoPago, 65), true);
                            vPrintArchivo.printLine("  #REF: " + vRefTipComp + " " +
                                                    FarmaPRNUtility.alinearIzquierda(pNumCompCoPago, 65), true);
                        }
                    }
                } else {

                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                          65), true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                                 VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                                 65) + "                          ",
                                                true);

                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          65) + "     " + " " +
                                         "          IGV    "+ConstantesUtil.simboloSoles+" " +
                                         FarmaPRNUtility.alinearDerecha(pValIgvComPago, 10),
                                         true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                          VariablesConvenioBTLMF.vNomCliente, 65) +
                                         "     " + " " + "          ---------------------", true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                                 VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                                 65) + "     " + " " +
                                                "          IGV    "+ConstantesUtil.simboloSoles+" " +
                                                FarmaPRNUtility.alinearDerecha(pValIgvComPago,
                                                                               10), true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                 VariablesConvenioBTLMF.vNomCliente,
                                                                                 65) + "     " + " " +
                                                "          ---------------------", true);

                        double pValTotalNetoRedondeo =
                            FarmaUtility.getDecimalNumber(pValTotalNeto) + FarmaUtility.getDecimalNumber(pValRedondeoComPago);

                        vPrint.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                         "        Total    "+ConstantesUtil.simboloSoles+" " +
                                         FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo),
                                                                        10), true);
                        vPrintArchivo.printLine("   " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                                "        Total    "+ConstantesUtil.simboloSoles+" " +
                                                FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo),
                                                                               10), true);
                    } else {
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                          65), true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                                 VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                                 65) + "                          ",
                                                true);

                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          65) + "     " + " " + " ", true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                          VariablesConvenioBTLMF.vNomCliente, 65) +
                                         "     " + " " + "          ", true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                                 VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                                 65) + "     " + " " +
                                                "                     ", true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                 VariablesConvenioBTLMF.vNomCliente,
                                                                                 65) + "     " + " " + "          ",
                                                true);


                    }


                }


                vPrintArchivo.printLine(" ", true);
                vPrint.printLine("  ", true);

                vPrint.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " + VariablesCaja.vNomCajeroImpreso +
                                 " " + VariablesCaja.vApePatCajeroImpreso + " " + " CAJA: " +
                                 VariablesCaja.vNumCajaImpreso + " TURNO: " + VariablesCaja.vNumTurnoCajaImpreso +
                                 " VEND: " + VariablesCaja.vNomVendedorImpreso + " " +
                                 VariablesCaja.vApePatVendedorImpreso, true);
                vPrintArchivo.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " +
                                        VariablesCaja.vNomCajeroImpreso + " " + VariablesCaja.vApePatCajeroImpreso +
                                        " " + " CAJA: " + VariablesCaja.vNumCajaImpreso + " TURNO: " +
                                        VariablesCaja.vNumTurnoCajaImpreso + " VEND: " +
                                        VariablesCaja.vNomVendedorImpreso + " " + VariablesCaja.vApePatVendedorImpreso,
                                        true);


                if (pImprDatAdic.equals("1")) {

                    if (VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic != null &&
                        VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 0) {
                        vPrintArchivo.printLine("  Datos Adicionales", true);
                        vPrint.printLine("  Datos Adicionales", true);

                        int nroDatosAdi = VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size();

                        // if (VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 4 )
                        // {
                        // 	nroDatosAdi = 4;
                        // }

                        for (int j = 0; j < nroDatosAdi; j++) {
                            Map datosAdicConv = (Map)VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.get(j);

                            String pCodigoCampo = (String)datosAdicConv.get("COD_CAMPO");
                            String pNombCampo = (String)datosAdicConv.get("NOMBRE_CAMPO");

                            String pDesCampo = (String)datosAdicConv.get("DESCRIPCION_CAMPO");
                            String vFlgImprime = (String)datosAdicConv.get("FLG_IMPRIME");

                            log.debug("pDesCampo   :" + pCodigoCampo);
                            log.debug("pNombCampo  :" + pNombCampo);
                            log.debug("vFlgImprime :" + vFlgImprime);

                            // if (!pCodigoCampo.trim().equalsIgnoreCase(ConstantsConvenioBTLMF.COD_DATO_CONV_BENIFICIARIO))
                            //{
                            // if (pCodigoCampo.equals(ConstantsConvenioBTLMF.COD_NOMB_TITULAR) || pCodigoCampo.equals(ConstantsConvenioBTLMF.COD_NRO_ATENCION)
                            //    || pCodigoCampo.equals(ConstantsConvenioBTLMF.COD_DATO_CONV_MEDICO)
                            //    )
                            // {
                            if (vFlgImprime.equals("1")) {
                                vPrintArchivo.printLine("  - " + pNombCampo + "    " + pDesCampo + " ", true);
                                vPrint.printLine("  - " + pNombCampo + "    " + pDesCampo + " ", true);
                            }
                            // }
                            //}
                        }
                    }
                }
                log.debug("Nro de Lineas::" + vPrint.getActualLine());

                vPrint.deactivateCondensed();
                vPrint.endPrintService();
                vPrintArchivo.endPrintService();

                log.info("Fin al imprimir la GUIA: " + pNumComprobante);
                VariablesCaja.vEstadoSinComprobanteImpreso = "N";

                //JCORTEZ 16.07.09 Se guarda fecha de impresion por comprobantes
                DBCaja.actualizaFechaImpr(VariablesCaja.vNumPedVta, pNumComprobante, "C");
                log.debug("Guardando fecha impresion cobro..." + pNumComprobante);
            } catch (SQLException sql) {
                log.error("", sql);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                // enviaErrorCorreoPorDB(sql.toString(),VariablesCaja.vNumPedVta);
            }

            catch (Exception e) {
                log.error("", e);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                log.info("Error al imprimir la Guia: " + e);
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                //enviaErrorCorreoPorDB(e.toString(),VariablesCaja.vNumPedVta);
            }


        }

    }


    private static void imprimeBoleta(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                      String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                      String pValIgvComCoPago, String pNumCompCoPago, String pRuta, boolean bol,
                                      String pImprDatAdic, String pTipoClienteConvenio, String pCodTipoConvenio,
                                      String pFechaBD, String pValRedondeoComPago) throws Exception {


        String pNomImpreso = "";
        String pDirImpreso = "";

        log.debug("IMPRIMIR BOLETA No : " + pNumComprobante);

        VariablesCaja.vIndPedidoConProdVirtualImpresion = false;
        float subTotal = 0;
        float SumSubTotal = 0;

        FarmaPrintService vPrint = new FarmaPrintService(24, VariablesCaja.vRutaImpresora, false);

        //JCORTEZ 16.07.09 Se genera archivo linea por linea
        FarmaPrintServiceTicket vPrintArchivo = new FarmaPrintServiceTicket(666, pRuta, false);
        vPrintArchivo.startPrintService();

        log.debug("Ruta : " + pRuta);
        //  if ( !vPrint.startPrintService() )  throw new Exception("Error en Impresora. Verifique !!!");
        log.debug("VariablesCaja.vNumPedVta:" + VariablesCaja.vNumPedVta);
        if (!vPrint.startPrintService()) {
            VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            log.info("**** Fecha :" + pFechaBD);
            log.info("**** CORR :" + VariablesCaja.vNumPedVta);
            log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
            log.info("**** IP :" + FarmaVariables.vIpPc);
            log.info("ERROR DE IMPRESORA : No se pudo imprimir la boleta");
        }

        else {
            try {
                vPrint.activateCondensed();
                if (VariablesPtoVenta.vIndDirMatriz) {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz,
                                            true);
                }
                //JMIRANDA 22.08.2011 Cambio para verificar si imprime
                if (UtilityVentas.getIndImprimeCorrelativo()) {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                     VariablesCaja.vNumPedVta, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                            VariablesCaja.vNumPedVta, true);
                } else {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD, true);
                }
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                 FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 60), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                        FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 60), true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                 FarmaPRNUtility.alinearIzquierda(pDirImpreso.trim(), 60) + "   No. " +
                                 pNumComprobante.substring(0, 3) + "-" + pNumComprobante.substring(3, 10), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                        FarmaPRNUtility.alinearIzquierda(pDirImpreso.trim(), 60) + "   No. " +
                                        pNumComprobante.substring(0, 3) + "-" + pNumComprobante.substring(3, 10),
                                        true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                int linea = 0;


                for (int i = 0; i < pDetalleComprobante.size(); i++) {
                    //Agregado por DVELIZ 13.10.08
                    String valor = ((String)((ArrayList)pDetalleComprobante.get(i)).get(16)).toString().trim();
                    log.debug("valor 1:" + valor);
                    if (valor.equals("0.000"))
                        valor = " ";
                    //fin DVELIZ
                    log.debug("Deta " + (ArrayList)pDetalleComprobante.get(i));
                    log.debug("valor 2:" + valor);
                    vPrint.printLine("" +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                    11) + "   " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                      27) + " " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                                      11) + "  " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                                      16) + "  " +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(4)).trim(),
                                                                    10) + " " +
                            //Agregado por DVELIZ 10.10.08
                            FarmaPRNUtility.alinearDerecha(valor, 8) + "" +
                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim(),
                                                           10), true);

                    vPrintArchivo.printLine("" +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                           11) + "   " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                             27) + " " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                                             11) + "  " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                                             16) + "  " +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(4)).trim(),
                                                                           10) + " " +
                            //Agregado por DVELIZ 10.10.08
                            FarmaPRNUtility.alinearDerecha(valor, 8) + "" +
                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim(),
                                                           10), true);

                    log.debug("SubTotal String:::" + ((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim());
                    linea += 1;
                    subTotal =
                            new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim())).floatValue();

                    log.debug("SubTotal:" + subTotal);


                    SumSubTotal = SumSubTotal + subTotal;
                }


                log.debug("SumSubTotal:" + SumSubTotal);

                //*************************************INFORMACION DEL CONVENIO***********************************************//

                double igv =
                    FarmaUtility.getDecimalNumber(pValIgvComPago);// + FarmaUtility.getDecimalNumber(pValIgvComCoPago);


                double porcCopago =
                    Math.round((FarmaUtility.getDecimalNumber(pValCopagoCompPago) / (FarmaUtility.getDecimalNumber(pValTotalNeto) +
                                                                                     FarmaUtility.getDecimalNumber(pValCopagoCompPago))) *
                               100);

                vPrint.printLine("      " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " + "    Sub Total   "+ConstantesUtil.simboloSoles+" " +
                                 FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10), true);
                vPrintArchivo.printLine("      " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                        "    Sub Total   "+ConstantesUtil.simboloSoles+" " +
                                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10),
                                        true);

                if (pCodTipoConvenio.equals("1")) {
                    vPrint.printLine("    " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " + "  CoPago(" +
                                     FarmaUtility.formatNumber(porcCopago, 0) + "%)    "+ConstantesUtil.simboloSoles+" " +
                                     FarmaPRNUtility.alinearDerecha(pValCopagoCompPago, 10), true);
                    vPrint.printLine("     " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                     "          ---------------------", true);
                    vPrintArchivo.printLine("    " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " + "  CoPago(" +
                                            FarmaUtility.formatNumber(porcCopago, "") + "%)    "+ConstantesUtil.simboloSoles+" " +
                                            FarmaPRNUtility.alinearDerecha(pValCopagoCompPago, 10), true);
                    vPrintArchivo.printLine("     " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                            "          ---------------------", true);
                }

                double pValTotalNetoRedondeo =
                    FarmaUtility.getDecimalNumber(pValTotalNeto) + FarmaUtility.getDecimalNumber(pValRedondeoComPago);
                pValTotalNetoRedondeo =
                        FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(pValTotalNetoRedondeo, 2)); //CHUANES 12.03.2014 SE PONE EL FORMATO DE 2 DECIMALES AL VALOR REDONDEADO
                //ERIOS 03.03.2015 El modifica el calculo de subtotal
                double neto = pValTotalNetoRedondeo - igv;

                String vLinea = "      " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " + "                    " +
                                                 FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(neto),10);
                vPrint.printLine(vLinea, true);
                vPrintArchivo.printLine(vLinea, true);

                vPrint.printLine("     " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " + "          IGV    "+ConstantesUtil.simboloSoles+" " +
                                 FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(igv), 10), true);
                vPrint.printLine("     " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                 "          ---------------------", true);
                vPrintArchivo.printLine("     " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                        "          IGV    "+ConstantesUtil.simboloSoles+" " +
                                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(igv), 10), true);
                vPrintArchivo.printLine("     " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                        "          ---------------------", true);
                vPrint.printLine(" SON:" +
                                 FarmaPRNUtility.alinearIzquierda(FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo).trim(),
                                                                  65) + " " + "          Total    "+ConstantesUtil.simboloSoles+" " +
                                 FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo), 10),
                                 true);
                vPrintArchivo.printLine(" SON:" +
                                        FarmaPRNUtility.alinearIzquierda(FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo).trim(),
                                                                         65) + " " + "          Total    "+ConstantesUtil.simboloSoles+" " +
                                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo),
                                                                       10), true);


                vPrintArchivo.printLine(" " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " ", true);
                vPrint.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65), true);

                vPrintArchivo.printLine("  Convenio: " +
                                        FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vNomConvenio.toUpperCase(),
                                                                         65) + " ", true);
                vPrint.printLine("  Convenio: " +
                                 FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vNomConvenio.toUpperCase(),
                                                                  65), true);
                vPrintArchivo.printLine("  Beneficiario: " +
                                        FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vNomCliente, 65) + " ",
                                        true);
                vPrint.printLine("  Beneficiario: " +
                                 FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vNomCliente, 65), true);

                if (pCodTipoConvenio.equals("1")) {
                    vPrintArchivo.printLine("  Documento de Referencia Nro: " + pNumCompCoPago + " " + " ", true);
                    vPrint.printLine("  Documento de Referencia Nro " + pNumCompCoPago + ": " + " ", true);
                    vPrintArchivo.printLine("  Doc refe de la Empresa Monto:"+ConstantesUtil.simboloSoles + pValCopagoCompPago + " y (" +
                                            FarmaUtility.formatNumber(porcCopago, "") + "%)", true);
                    vPrint.printLine("  Doc refe de la Empresa Monto:"+ConstantesUtil.simboloSoles + pValCopagoCompPago + " y (" +
                                     FarmaUtility.formatNumber(porcCopago, "") + "%)", true);
                }
                if (pImprDatAdic.equals("1")) {
                    if (VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic != null &&
                        VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 0) {
                        vPrintArchivo.printLine("  Datos Adicionales", true);
                        vPrint.printLine("  Datos Adicionales", true);
                        for (int j = 0; j < VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size(); j++) {
                            Map datosAdicConv = (Map)VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.get(j);

                            String pNombCampo = (String)datosAdicConv.get("NOMBRE_CAMPO");
                            String pDesCampo = (String)datosAdicConv.get("DESCRIPCION_CAMPO");
                            String vFlgImprime = (String)datosAdicConv.get("FLG_IMPRIME");

                            log.debug("pDesCampo   :" + pNombCampo);
                            log.debug("pNombCampo  :" + pNombCampo);
                            log.debug("vFlgImprime :" + vFlgImprime);

                            if (vFlgImprime.equals("1") || vFlgImprime.equals("2")) {
                                vPrintArchivo.printLine("  - " + pNombCampo + "    " + pDesCampo + " ", true);
                                vPrint.printLine("  - " + pNombCampo + "    " + pDesCampo + " ", true);
                            }
                        }
                    }
                }

                vPrintArchivo.printLine(" " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " ", true);
                vPrint.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65), true);

                vPrint.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " + VariablesCaja.vNomCajeroImpreso +
                                 " " + VariablesCaja.vApePatCajeroImpreso + " " + " CAJA: " +
                                 VariablesCaja.vNumCajaImpreso + " TURNO: " + VariablesCaja.vNumTurnoCajaImpreso +
                                 " VEND: " + VariablesCaja.vNomVendedorImpreso + " " +
                                 VariablesCaja.vApePatVendedorImpreso, true);
                vPrintArchivo.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " +
                                        VariablesCaja.vNomCajeroImpreso + " " + VariablesCaja.vApePatCajeroImpreso +
                                        " " + " CAJA: " + VariablesCaja.vNumCajaImpreso + " TURNO: " +
                                        VariablesCaja.vNumTurnoCajaImpreso + " VEND: " +
                                        VariablesCaja.vNomVendedorImpreso + " " + VariablesCaja.vApePatVendedorImpreso,
                                        true);

                vPrint.deactivateCondensed();
                vPrint.endPrintService();
                vPrintArchivo.endPrintService();

                log.info("Fin al imprimir la boleta: " + pNumComprobante);
                VariablesCaja.vEstadoSinComprobanteImpreso = "N";

                //JCORTEZ 16.07.09 Se guarda fecha de impresion por comprobantes
                DBCaja.actualizaFechaImpr(VariablesCaja.vNumPedVta, pNumComprobante, "C");
                log.debug("Guardando fecha impresion cobro..." + pNumComprobante);
            } catch (SQLException sql) {
                log.error("",sql);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                // enviaErrorCorreoPorDB(sql.toString(),VariablesCaja.vNumPedVta);
            }

            catch (Exception e) {
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                log.info("Error al imprimir la boleta: " + e);
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                //enviaErrorCorreoPorDB(e.toString(),VariablesCaja.vNumPedVta);
            }


        }

    }

    private static String obtieneRutaImpresora(String pTipCompPag) throws SQLException {
        return DBConvenioBTLMF.obtieneRutaImpresoraVenta(pTipCompPag);
    }


    public static boolean listaDatosConvenioAdic(JDialog pJDialog, Object pObjectFocus) {
        VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic = new ArrayList();
        boolean valor = true;
        long tmpT1, tmpT2;
        tmpT1 = System.currentTimeMillis();
        List lista = new ArrayList();
        try {
            lista = DBConvenioBTLMF.listaDatosConvenioAdicXpedido();
            VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic = lista;
            VariablesConvenioBTLMF.vNomClienteDigitado = "";
            //CHUANES 31.03.2014
            //EXTRAEMOS DE LOS DATOS ADICIONALES EL NOMBRE DEL BENEFICIARIO
            for (int i = 0; i < VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size(); i++) {
                Map datosAdicConv = (Map)VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.get(i);

                String pNombCampo = (String)datosAdicConv.get("NOMBRE_CAMPO");
                String pDesCampo = (String)datosAdicConv.get("DESCRIPCION_CAMPO");
                //ERIOS 2.4.7 Busca nombre de beneficiario
                String vCodCampo = (String)datosAdicConv.get("COD_CAMPO");
                if (vCodCampo.equals(ConstantsConvenioBTLMF.COD_DATO_CONV_BENIFICIARIO)) {
                    if (VariablesConvenioBTLMF.vNomCliente.trim().equals("")) {
                        VariablesConvenioBTLMF.vNomCliente = pDesCampo;
                    }
                }
                //SI LA VARIABLE CONTIENE pNombCampo BENEFICIARIO ENTONCES pDesCampo CONTIENE EL NOMBRE DEL BENEFICIARIO
                if (pNombCampo.contains("Beneficiario")) {
                    VariablesConvenioBTLMF.vNomClienteDigitado = pDesCampo;
                }
            }
            if (lista.size() == 0) {
                FarmaUtility.liberarTransaccion();
                FarmaUtility.showMessage(pJDialog,
                                         "No se pudo determinar el listado de datos adicionales del convenio. Verifique!!!.",
                                         pObjectFocus);
                valor = false;
            } else {
                log.info("VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic : " +
                         VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size());
                valor = true;
            }
        } catch (SQLException sql) {
            FarmaUtility.liberarTransaccion();
            log.error("", sql);
            FarmaUtility.showMessage(pJDialog, "Error al obtener los datos adicionales del convenio.", pObjectFocus);
            
            valor = false;
            UtilityCaja.enviaErrorCorreoPorDB(sql.toString(), null);
        }

        tmpT2 = System.currentTimeMillis();
        log.debug("Tiempo 4: Det.Comp Pago:" + (tmpT2 - tmpT1) + " milisegundos");

        return valor;
    }


    public static Map obtieneConvenioXpedido(String nroPedido, JDialog pDialogo) {
        Map pedidoConvenio = null;

        try {
            pedidoConvenio = DBConvenioBTLMF.obtenerConvenioXPedido(VariablesCaja.vNumPedVta_Anul);

        } catch (SQLException sqlException) {
            log.error("", sqlException);
            FarmaUtility.showMessage(pDialogo, "Error al obtener los datos del pedido convenio", pDialogo);
        }
        return pedidoConvenio;
    }

    public static String esDiaVencimientoReceta(JDialog pDialogo, Object pObjeto, String fechVencimietnoRecta) {
        String res = null;

        try {
            res = DBConvenioBTLMF.esDiaVigenciaReceta(fechVencimietnoRecta);

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialogo, "Error al validar la fecha de vigencia de la receta" + sql.getMessage(),
                                     pObjeto);
        }

        if (res.equals("P")) {
            FarmaUtility.showMessage(pDialogo, "La Fecha de Vigencia de Receta es posterior al d�a de hoy.", pObjeto);
        } else if (res.equals("N")) {
            FarmaUtility.showMessage(pDialogo, "La Fecha de Vigencia de Receta est� caducado.", pObjeto);
        }
        return res;
    }


    public static String obtieneTipoConvenio(JDialog pDialogo, Object pObjeto, String pCodConvenio) {
        String resTipoConvenio = null;

        try {
            resTipoConvenio = DBConvenioBTLMF.geTipoConvenio(pCodConvenio);

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialogo, "Error al obtener el tipo Convenio" + sql.getMessage(), pObjeto);
        }

        return resTipoConvenio;
    }

    public static Map obtieneMsgCompPagoImpr(JDialog pDialogo, Object pObjeto) {
        Map resTipoConvenio = null;

        try {
            resTipoConvenio = DBConvenioBTLMF.obtieneMsgCompPagoImpr();

        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialogo, "Error al obtener el mensaje de Impresion" + sql.getMessage(), pObjeto);
        }

        return resTipoConvenio;
    }

    public static void aceptarTransaccionRempota(FarmaTableModel pTableModel, Object pObjeto,
                                                 String pIndCloseConecction) {

        try {
            DBConvenioBTLMF.aceptarTransaccionRempota(pTableModel, pIndCloseConecction);
        } catch (SQLException sql) {
            log.error("", sql);
            //				//FarmaUtility.showMessage(pDialogo,
            //				"Error al verificar la existencia de productos en convenio" +
            //				sql.getMessage(), pObjeto);
        }


    }


    public static void liberarTransaccionRempota(FarmaTableModel pTableModel, Object pObjeto,
                                                 String pIndCloseConecction) {

        try {
            DBConvenioBTLMF.liberarTransaccionRempota(pTableModel, pIndCloseConecction);
        } catch (SQLException sql) {
            log.error("", sql);
            //				//FarmaUtility.showMessage(pDialogo,
            //				"Error al verificar la existencia de productos en convenio" +
            //				sql.getMessage(), pObjeto);
        }


    }


    public static boolean esMontoPrecioCero(String monto, JDialog pDialogo) {
        boolean result = false;
        if (FarmaUtility.getDecimalNumber(monto) == 0) {
            FarmaUtility.showMessage(pDialogo, "El precio venta del producto convenio es cero", null);
            result = true;
        }

        return result;
    }

    public static boolean esCompCredito(JDialog pDialogo) {

        String result = "";
        boolean resp = true;
        try {
            result = DBConvenioBTLMF.esCompCredito();

            if (result.equals("N")) {
                resp = false;
            }

        } catch (SQLException sql) {
            log.error("", sql);
        }

        return resp;
    }

    public static String indConvenioBTLMF(JDialog pDialogo) {
        String indConvenioBTLMF = "";
        try {
            Map vtaPedido = (Map)DBConvenioBTLMF.obtenerConvenioXPedido(VariablesCaja.vNumPedVta_Anul);
            indConvenioBTLMF = (String)vtaPedido.get("IND_CONV_BTL_MF");


        } catch (SQLException sql) {
            log.error("", sql);
        }


        return indConvenioBTLMF;
    }

    /*** INICIO ARAVELLO 11/10/2019 ***/

    public static boolean indCopagoConvenio(String pCodigoConvenio) {
        boolean resul = false;
        String indConv = "";
        String indProdConv = "";

        try {
            indConv = DBConvenioBTLMF.pideCopagoConvenio(pCodigoConvenio);
            log.debug("INDICADOR PIDE COPAGO CONV = " + indConv);
            if (indConv.equalsIgnoreCase("S")) {
                resul = true;
            } else {
                resul = false;
            }

        } catch (SQLException sql) {
            log.error("", sql);
//            FarmaUtility.showMessage(pDialogo, "Error en buscar si debe mostrase datos convenios\n" +
//                    sql.getMessage(), pObjeto);
            resul = false;
        }
        return resul;
    }
    /*** FIN    ARAVELLO 11/10/2019 ***/
    private static void imprimeFacturaFasa(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                           String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                           String pValIgvComCoPago, String pNumCompCoPago, String pRuta, boolean bol,
                                           String pImprDatAdic, String pTipoClienteConvenio, String pCodTipoConvenio,
                                           String pFechaBD, String pRefTipComp,
                                           String pValRedondeoComPago) throws Exception {
        log.debug("IMPRIMIR FACTURA No : " + pNumComprobante);
        String indProdVirtual = "";
        int nroArticulos = 0;
        //jcortez 06.07.09 Se verifica ruta
        // if(bol) VariablesCaja.vRutaImpresora=pRuta;

        VariablesCaja.vIndPedidoConProdVirtualImpresion = false;
        float subTotal = 0;
        float montoIGV = 0;
        float SumSubTotal = 0;
        double SumMontoIGV = 0;

        String pNomImpreso = VariablesConvenioBTLMF.vInstitucion;
        String pDirImpreso = VariablesConvenioBTLMF.vDireccion;
        String pNumDocImpreso = VariablesConvenioBTLMF.vRuc;

        //FarmaPrintService vPrint = new FarmaPrintService(36,VariablesCaja.vRutaImpresora + "factura" + pNumComprobante + ".txt",false);
        FarmaPrintService vPrint = new FarmaPrintService(66, VariablesCaja.vRutaImpresora, false);

        //JCORTEZ 16.07.09 Se genera archivo linea por linea
        FarmaPrintServiceTicket vPrintArchivo = new FarmaPrintServiceTicket(666, pRuta, false);
        vPrintArchivo.startPrintService();


        log.debug("Ruta : " + VariablesCaja.vRutaImpresora + "factura" + pNumComprobante + ".txt");
        if (!vPrint.startPrintService()) {
            VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            log.info("**** Fecha :" + pFechaBD);
            log.info("**** CORR :" + VariablesCaja.vNumPedVta);
            log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
            log.info("**** IP :" + FarmaVariables.vIpPc);
            log.info("ERROR DE IMPRESORA : No se pudo imprimir la factura");
        } else {

            try {

                String dia = pFechaBD.substring(0, 2);
                String mesLetra = FarmaUtility.devuelveMesEnLetras(Integer.parseInt(pFechaBD.substring(3, 5)));
                String ano = pFechaBD.substring(6, 10);
                String hora = pFechaBD.substring(11, 19);
                vPrint.activateCondensed();


                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                //LOCAL
                ArrayList lstDirecMatriz = FarmaUtility.splitString(VariablesPtoVenta.vDireccionMatriz, 32);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(50) + lstDirecMatriz.get(0).toString() +
                                 FarmaPRNUtility.llenarBlancos(10) + "No. " + pNumComprobante.substring(0, 3) + "-" +
                                 pNumComprobante.substring(3, 10), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(50) + lstDirecMatriz.get(0).toString() +
                                        FarmaPRNUtility.llenarBlancos(10) + "No. " + pNumComprobante.substring(0, 3) +
                                        "-" + pNumComprobante.substring(3, 10), true);

                //SENIOR(ES)-SI LA LONGITUD DE NOMBRE IMPRESO ES MAYOR A  40 SE CORTA EN EL ULTIMO ESPACIO EN BLANCO Y LA PALABRA SOBRANTE
                //SE IMPRIME EN EL SIGUIENTE REGLON
                if (pNomImpreso.length() > 40) {
                    int posBlanc =
                        pNomImpreso.lastIndexOf(" ", pNomImpreso.length()); //SE OBTIENE LA POSCION EN BLANCO
                    String lastNomImpreso =
                        pNomImpreso.substring(posBlanc, pNomImpreso.length()); //SE OBTIENE LA ULTIMA PALABRA
                    pNomImpreso = pNomImpreso.substring(0, posBlanc);
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                     FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 40) +
                                     lstDirecMatriz.get(1).toString(), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                            FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 40) +
                                            lstDirecMatriz.get(1).toString(), true);

                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                     FarmaPRNUtility.alinearIzquierda(lastNomImpreso.trim(), 40) +
                                     lstDirecMatriz.get(2).toString(), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                            FarmaPRNUtility.alinearIzquierda(lastNomImpreso.trim(), 40) +
                                            lstDirecMatriz.get(2).toString(), true);

                } else {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                     FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 40) +
                                     lstDirecMatriz.get(1).toString(), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                            FarmaPRNUtility.alinearIzquierda(pNomImpreso.trim(), 40) +
                                            lstDirecMatriz.get(1).toString(), true);

                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(50) + lstDirecMatriz.get(2).toString(), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(50) + lstDirecMatriz.get(2).toString(),
                                            true);
                }

                //DIRECCION
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                 FarmaPRNUtility.alinearIzquierda(pDirImpreso.trim(), 60), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(10) +
                                        FarmaPRNUtility.alinearIzquierda(pDirImpreso.trim(), 60), true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                //RUC y FECHA
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(10) + pNumDocImpreso.trim() +
                                 FarmaPRNUtility.llenarBlancos(73) + dia + " de " + mesLetra + " del " + ano +
                                 "     " + hora, true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(10) + pNumDocImpreso.trim() +
                                        FarmaPRNUtility.llenarBlancos(73) + dia + " de " + mesLetra + " del " + ano +
                                        "     " + hora, true);
                // ESPACIOS ENTRE LA CABECERA Y EL DETALLE DE LA FACTURA
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                //CHUANES 2.2.8 SE IMPRIME EL NUMERO DE GUIA
                //SE VALIDA PORQUE ALGUNOS CONVENIOS NO GENERAN GUIAS POR LO TANTO pNumCompCoPago ES NULLO O VACIO
                if (pNumCompCoPago == null || pNumCompCoPago.equals("") ||
                    !pRefTipComp.equals(ConstantsPtoVenta.TIP_COMP_GUIA)) {
                    vPrint.printLine(" ", true);
                    vPrintArchivo.printLine(" ", true);
                } else {
                    log.debug("No.Guia: " + pNumCompCoPago.trim());
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(73) + "No.Guia " + pNumCompCoPago.substring(0, 3) +
                                     "-" + pNumCompCoPago.substring(3, 10), true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(73) + "No.Guia " +
                                            pNumCompCoPago.substring(0, 3) + "-" + pNumCompCoPago.substring(3, 10),
                                            true);
                }
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                int linea = 0;
                double pMontoOld = 0, pMontoNew = 0, pMontoDescuento = 0;
                log.info("" + VariablesVentas.vTipoPedido);

                int vNroEspacio = 0;
                for (int i = 0; i < pDetalleComprobante.size(); i++) {
                    nroArticulos++; //= nroArticulos + Integer.parseInt(((ArrayList)pDetalleComprobante.get(i)).get(0).toString());
                    vPrint.printLine(" " +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                      6) + FarmaPRNUtility.llenarBlancos(5) +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                      60) + FarmaPRNUtility.llenarBlancos(3) +
                                     FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                    11) + FarmaPRNUtility.llenarBlancos(5) +
                            //UNIDAD DE MEDIDA
                            //FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),14) + "   " +
                            // LABORATORIO
                            //FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),20) + FarmaPRNUtility.llenarBlancos(2) +
                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(4)).trim(),
                                                           13) + FarmaPRNUtility.llenarBlancos(14) +
                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim(),
                                                           10), true);

                    vPrintArchivo.printLine(" " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                             6) + FarmaPRNUtility.llenarBlancos(5) +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                             60) + FarmaPRNUtility.llenarBlancos(3) +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                           11) + FarmaPRNUtility.llenarBlancos(5) +
                            //UNIDAD DE MEDIDA
                            //FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),14) + "   " +
                            // LABORATORIO
                            //FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),20) + FarmaPRNUtility.llenarBlancos(2) +
                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(4)).trim(),
                                                           13) + FarmaPRNUtility.llenarBlancos(14) +
                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim(),
                                                           10), true);

                    log.debug("SubTotal String:::" + ((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim());
                    linea += 1;
                    subTotal =
                            new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim())).floatValue();
                    montoIGV =
                            new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(18)).trim())).floatValue();
                    SumMontoIGV = SumMontoIGV + montoIGV;
                    log.debug("SubTotal:" + subTotal);
                    SumSubTotal = SumSubTotal + subTotal;

                    //linea += 1;
                    indProdVirtual = FarmaUtility.getValueFieldArrayList(pDetalleComprobante, i, 8);

                    if (VariablesCaja.vIndDistrGratuita.equalsIgnoreCase(FarmaConstants.INDICADOR_S)) {
                        linea++;
                    }
                }
                //MODIFICADO POR DVELIZ 13.10.08
                //
                for (int j = linea; j < ConstantsPtoVenta.TOTAL_LINEAS_POR_BOLETA; j++)
                    vPrint.printLine(" ", true);

                //*************************************INFORMACION DEL CONVENIO***********************************************//

                double porcCopago =
                    Math.round((FarmaUtility.getDecimalNumber(pValCopagoCompPago) / (FarmaUtility.getDecimalNumber(pValTotalNeto) +
                                                                                     FarmaUtility.getDecimalNumber(pValCopagoCompPago))) *
                               100);
                SumMontoIGV = SumMontoIGV - ((SumMontoIGV * porcCopago) / 100);
                double ValCopagoCompPagoSinIGV = ((SumSubTotal * porcCopago) / 100);

                vPrint.printLine("      " + FarmaPRNUtility.alinearIzquierda(" ", 85) + "        " +
                                 "    Sub Total   "+ConstantesUtil.simboloSoles+" " +
                                 FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10), true);
                vPrintArchivo.printLine("      " + FarmaPRNUtility.alinearIzquierda(" ", 85) + "        " +
                                        "    Sub Total   "+ConstantesUtil.simboloSoles+" " +
                                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10),
                                        true);

                double pValTotalNetoRedondeo =
                    FarmaUtility.getDecimalNumber(pValTotalNeto) + FarmaUtility.getDecimalNumber(pValRedondeoComPago);

                pValTotalNetoRedondeo =
                        FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(pValTotalNetoRedondeo, 2)); //CHUANES 12.03.2014 SE PONE EL FORMATO DE 2 DECIMALES AL VALOR REDONDEADO

                //ERIOS 12.09.2013 Imprime direccion local
                String vLinea = "", vLineaDirecLocal1 = "", vLineaDirecLocal2 = "", vLineaDirecLocal3 = "";
                if (VariablesPtoVenta.vIndDirLocal) {
                    ArrayList lstDirecLocal =
                        FarmaUtility.splitString("NUEVA DIRECCION: " + FarmaVariables.vDescCortaDirLocal, 46);
                    vLineaDirecLocal1 = lstDirecLocal.get(0).toString();
                    vLineaDirecLocal2 = ((lstDirecLocal.size() > 1) ? lstDirecLocal.get(1).toString() : "");
                    vLineaDirecLocal3 = ((lstDirecLocal.size() > 2) ? lstDirecLocal.get(2).toString() : "");
                }

                if (pCodTipoConvenio.equals("1")) {
                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  SON: " + FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo),
                                                             85) + "            " + "Coaseguro(" +
                            FarmaUtility.formatNumber(porcCopago, 0) + "%)    "+ConstantesUtil.simboloSoles+" " +
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(ValCopagoCompPagoSinIGV), 10);
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("              " + "     ", 85) + "                       ---------------------";
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Instituci�n: " + VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim() +
                                                             "  (" + FarmaUtility.formatNumber(100 - porcCopago, "") +
                                                             ")%", 85) + "                                  " +
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal -
                                                                                     ValCopagoCompPagoSinIGV), 10);
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase().trim(),
                                                             85) + vLineaDirecLocal1;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Beneficiario: " + VariablesConvenioBTLMF.vNomCliente, 85) +
                            vLineaDirecLocal2;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Documento de Referencia Nro " + pNumCompCoPago + " " + "(" +
                                                             FarmaUtility.formatNumber(porcCopago, "") + "%)" + " - " +
                                                             ""+ConstantesUtil.simboloSoles + pValCopagoCompPago, 85) + vLineaDirecLocal3;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                } else {
                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  SON: " + FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo),
                                                             85) + "            ";
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("              " + "     ", 85) + "                       ---------------------";
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Instituci�n: " + VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim() +
                                                             "  (" + FarmaUtility.formatNumber(100 - porcCopago, "") +
                                                             ")%", 85) + vLineaDirecLocal1;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    vLinea =
                            FarmaPRNUtility.alinearIzquierda("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase().trim(),
                                                             85) + vLineaDirecLocal2;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);

                    String lineaBeneficiario = FormatoImpresion.muestraBeneficiario();
                    vLinea = FarmaPRNUtility.alinearIzquierda(lineaBeneficiario, 85) + vLineaDirecLocal3;
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);
                }
                linea = 0;
                if (pImprDatAdic.equals("1")) {
                    if (VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic != null &&
                        VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 0) {
                        vPrintArchivo.printLine("  Datos Adicionales " + " ", true);
                        vPrint.printLine("  Datos Adicionales", true);
                        linea++;
                        String lineaInfAdic = "";
                        for (int j = 0; j < VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size(); j++) {
                            Map datosAdicConv = (Map)VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.get(j);

                            String pNombCampo = (String)datosAdicConv.get("NOMBRE_CAMPO");
                            String pDesCampo = (String)datosAdicConv.get("DESCRIPCION_CAMPO");
                            String vFlgImprime = (String)datosAdicConv.get("FLG_IMPRIME");
                            String vCodCampo = (String)datosAdicConv.get("COD_CAMPO");

                            log.debug("pDesCampo   :" + pNombCampo);
                            log.debug("pNombCampo  :" + pNombCampo);
                            log.debug("vFlgImprime :" + vFlgImprime);
                            log.debug("vCodCampo :" + vCodCampo);

                            /*             if(vFlgImprime.equals("1") || vFlgImprime.equals("2"))
                                              {
                                                  if (vCodCampo.equals(ConstantsConvenioBTLMF.COD_NOMB_TITULAR) || vCodCampo.equals(ConstantsConvenioBTLMF.COD_NRO_ATENCION))
                                                  {
                                                      vPrintArchivo.printLine("  - "+pNombCampo +  "    "+pDesCampo+" ",false);
                                                      vPrint.printLine("  - "+pNombCampo +  "    "+pDesCampo+" ",false);
                                                  }

                                              } */
                            /* ------ */
                            if (vFlgImprime.equals("1") && pNombCampo != null && pDesCampo != null) {
                                //se imprimen dos informaciones en una linea
                                String temp =
                                    FarmaPRNUtility.alinearIzquierda("  - " + pNombCampo + ": " + pDesCampo, 60);

                                if ("".equalsIgnoreCase(lineaInfAdic)) { //si no existe linea, se coloca esta
                                    lineaInfAdic = temp;
                                } else { //si existe una linea, se coloca la siguiente anexa, se imprime y luego se resetea
                                    lineaInfAdic = lineaInfAdic + temp;
                                    vPrintArchivo.printLine(lineaInfAdic, true);
                                    vPrint.printLine(lineaInfAdic, true);
                                    linea++;
                                    lineaInfAdic = "";
                                    //vPrint.printLine("  - "+pNombCampo +  ":    "+pDesCampo+" ",true);
                                }
                            }
                            /* ------ */
                        }
                    }
                }


                //              vPrintArchivo.printLine("  Instituci�n: " +VariablesConvenioBTLMF.vInstitucion.toUpperCase() + " ",true);
                //              vPrint.printLine("  Instituci�n: " +VariablesConvenioBTLMF.vInstitucion.toUpperCase().trim(),true);
                //              vPrintArchivo.printLine("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase(),true);
                //              vPrint.printLine("  Convenio: " + VariablesConvenioBTLMF.vNomConvenio.toUpperCase().trim(),true);
                //              vPrintArchivo.printLine("  Beneficiario: " + VariablesConvenioBTLMF.vNomCliente,true);
                //              vPrint.printLine("  Beneficiario: " + VariablesConvenioBTLMF.vNomCliente,true);

                if (pCodTipoConvenio.equals("1")) {
                    //               vPrintArchivo.printLine("  Documento de Referencia Nro: "+pNumCompCoPago+" ",true);
                    //               vPrint.printLine("  Documento de Referencia Nro "+pNumCompCoPago+": ",true);
                    //               vPrintArchivo.printLine("  Doc refe de la Empresa Monto:"+ConstantesUtil.simboloSoles + pValCopagoCompPago +" y ("+FarmaUtility.formatNumber(porcCopago,"")+"%)",true);
                    //               vPrint.printLine("  Doc refe de la Empresa Monto:"+ConstantesUtil.simboloSoles + pValCopagoCompPago +" y ("+FarmaUtility.formatNumber(porcCopago,"")+"%)",true);
                }
                for (int j = linea; j < ConstantsPtoVenta.TOTAL_LINEAS_POR_BOLETA; j++)
                    vPrint.printLine(" ", true);

                vLinea =
                        " REDO:" + pValRedondeoComPago + " CAJERO:" + VariablesCaja.vNomCajeroImpreso + " " + VariablesCaja.vApePatCajeroImpreso +
                        " " + " CAJA:" + VariablesCaja.vNumCajaImpreso + " TURNO:" +
                        VariablesCaja.vNumTurnoCajaImpreso + " VEND:" + VariablesCaja.vNomVendedorImpreso + " " +
                        VariablesCaja.vApePatVendedorImpreso;
                vLinea = FarmaPRNUtility.alinearIzquierda(vLinea, 85);
                vPrint.printLine(vLinea, true);
                vPrintArchivo.printLine(vLinea, true);

                /*vLinea = " Forma(s) de pago: " + VariablesCaja.vFormasPagoImpresion + FarmaPRNUtility.llenarBlancos(11) + VariablesVentas.vTituloDelivery;
      vLinea = FarmaPRNUtility.alinearIzquierda(vLinea,85)+vLineaDirecLocal3;
      vPrint.printLine(vLinea,true);
      vPrintArchivo.printLine(vLinea,true);*/

                /*if(!VariablesCaja.vImprimeFideicomizo){
            vPrintArchivo.printLine(" ",true);
            vPrint.printLine(" ",true);
            vPrint.printLine(" ",true);
            vPrintArchivo.printLine(" ",true);
        }
        if(VariablesCaja.vImprimeFideicomizo){
            String[] lineas = VariablesCaja.vCadenaFideicomizo.trim().split("@");
            log.info("********************"+  VariablesCaja.vCadenaFideicomizo+"]");
            if(lineas.length>0){
                for(int i=0;i<lineas.length;i++){
                    if(lineas[i].trim().length() > 0){
                        log.info("******** imprimiendo [" + i + "] : "+ lineas[i].trim());
                        vPrint.printLine(""+lineas[i].trim(),true);
                        vPrintArchivo.printLine(""+lineas[i].trim(),true);
                    }
                }
            }
        }*/
                //l�neas necesarias para que al imprimir la 2da factura hacia adelante, se imprima en la posici�n correcta.
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                String pPorcIgv =
                    FarmaUtility.formatNumber(100 * ((pValTotalNetoRedondeo / (pValTotalNetoRedondeo - SumMontoIGV)) -
                                                     1));
                double vIgvRed =
                    Math.round(Double.parseDouble(pPorcIgv)); //Cesar Huanes --redondeo al numero mas cercano, siempre sera  18.
                String valor = String.valueOf(vIgvRed) + "0";                
                
                //ERIOS 03.03.2015 El modifica el calculo de subtotal
                SumMontoIGV = FarmaUtility.getDecimalNumber(pValIgvComPago);
                
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(85) + FarmaPRNUtility.alinearDerecha(valor, 6) + "%",
                                 true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(85) + FarmaPRNUtility.alinearDerecha(valor, 6) +
                                        "%", true);

                vPrint.printLine("     " + FarmaPRNUtility.alinearDerecha(nroArticulos, 10) +
                                 FarmaPRNUtility.llenarBlancos(65) +
                        //FarmaPRNUtility.alinearDerecha(pValTotalBruto,10) + FarmaPRNUtility.llenarBlancos(10) +
                        ConstantesUtil.simboloSoles+" " + FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumMontoIGV, 2), 10) +
                        FarmaPRNUtility.llenarBlancos(25) +ConstantesUtil.simboloSoles+" " +
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo), 10), true);
                vPrintArchivo.printLine("     " + FarmaPRNUtility.alinearDerecha(nroArticulos, 10) +
                                        FarmaPRNUtility.llenarBlancos(65) +
                        //FarmaPRNUtility.alinearDerecha(pValTotalBruto,10) + FarmaPRNUtility.llenarBlancos(10) +
                        ConstantesUtil.simboloSoles+" " + FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumMontoIGV, 2), 10) +
                        FarmaPRNUtility.llenarBlancos(25) +ConstantesUtil.simboloSoles+" " +
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo), 10), true);


                vPrint.endPrintServiceSinCompletar();
                vPrintArchivo.endPrintService();

                //JCORTEZ 16.07.09 Se guarda fecha de impresion por comprobantes
                DBCaja.actualizaFechaImpr(VariablesCaja.vNumPedVta, pNumComprobante, "C");
                log.debug("Guardando fecha impresion cobro..." + pNumComprobante);
                log.info("Fin al imprimir la factura: " + pNumComprobante);
                VariablesCaja.vEstadoSinComprobanteImpreso = "N";
            } catch (Exception e) {
                log.error("", e);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                log.info("Error al imprimir Factura: " + e);

                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                UtilityCaja.enviaErrorCorreoPorDB(e.toString(), VariablesCaja.vNumPedVta);

            }
        }
    }

    private static void imprimeGuiaFasa(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                        String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                        String pValIgvComCoPago, String pNumCompCoPago, String pRuta, boolean bol,
                                        String pImprDatAdic, String pTipoClienteConvenio, String pCodTipoConvenio,
                                        String pFechaBD, String pRefTipComp,
                                        String pValRedondeoComPago,
                                        String vPrctBeneficiario, String vPrctEmpresa) throws Exception {
        //String pNomImpreso = "";
        //String pDirImpreso = "";

        log.debug("IMPRIMIR GUIA No : " + pNumComprobante);

        VariablesCaja.vIndPedidoConProdVirtualImpresion = false;
        float subTotal = 0;
        float SumSubTotal = 0;
        float montoIGV = 0;
        double SumMontoIGV = 0;

        //Comentado por FRAMIREZ
        //FarmaPrintService vPrint = new FarmaPrintService(30, VariablesCaja.vRutaImpresora, false);
        //VariablesCaja.vRutaImpresora = "/\\/10.11.1.54/reporte1";
        FarmaPrintService vPrint = new FarmaPrintService(66, VariablesCaja.vRutaImpresora, false);

        //JCORTEZ 16.07.09 Se genera archivo linea por linea
        FarmaPrintServiceTicket vPrintArchivo = new FarmaPrintServiceTicket(666, pRuta, false);
        vPrintArchivo.startPrintService();

        log.debug("vRutaImpresora : " + VariablesCaja.vRutaImpresora);
        log.debug("Ruta : " + pRuta);

        //  if ( !vPrint.startPrintService() )  throw new Exception("Error en Impresora. Verifique !!!");
        log.debug("VariablesCaja.vNumPedVta:" + VariablesCaja.vNumPedVta);
        if (!vPrint.startPrintService()) {
            VariablesCaja.vEstadoSinComprobanteImpreso = "S";
            log.info("**** Fecha :" + pFechaBD);
            log.info("**** CORR :" + VariablesCaja.vNumPedVta);
            log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
            log.info("**** IP :" + FarmaVariables.vIpPc);
            log.info("ERROR DE IMPRESORA : No se pudo imprimir la boleta");
        } else {
            try {
                vPrint.activateCondensed();
                vPrint.printLine(FarmaPRNUtility.llenarBlancos(30) + " ", true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(30) + " ", true);

                /*if(VariablesPtoVenta.vIndDirMatriz)   //ERIOS 11.11.2013 No hay espacio la guia
                {   vPrint.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz ,true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(30) + VariablesPtoVenta.vDireccionMatriz ,true);
                }
                else
                {*/
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                //}

                //ERIOS 12.09.2013 Imprime direccion local
                /*if(VariablesPtoVenta.vIndDirLocal)    //ERIOS 11.11.2013 No hay espacio la guia
                {   vPrint.printLine("     "+"NUEVA DIRECCION: "+FarmaVariables.vDescCortaDirLocal,true);
                }
                else
                {*/
                vPrint.printLine("", true);
                vPrintArchivo.printLine(" ", true);
                //}

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(100) + "   No. " + pNumComprobante.substring(0, 3) +
                                 "-" + pNumComprobante.substring(3, 10), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(100) + "   No. " +
                                        pNumComprobante.substring(0, 3) + "-" + pNumComprobante.substring(3, 10),
                                        true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + FarmaPRNUtility.alinearIzquierda(pFechaBD, 60),
                                 true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                        FarmaPRNUtility.alinearIzquierda(pFechaBD, 60), true);

                //JMIRANDA 22.08.2011 Cambio para verificar si imprime
                if (UtilityVentas.getIndImprimeCorrelativo()) {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                     VariablesCaja.vNumPedVta, true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + pFechaBD + "   CORR." +
                                            VariablesCaja.vNumPedVta, true);
                } else {
                    vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + " ", true);
                    vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + " ", true);
                }

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                 FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vDireccion.trim(), 60), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) +
                                        FarmaPRNUtility.alinearIzquierda(VariablesConvenioBTLMF.vDireccion.trim(), 60),
                                        true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(11) + VariablesConvenioBTLMF.vInstitucion.trim(), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(11) + VariablesConvenioBTLMF.vInstitucion.trim(),
                                        true);

                vPrint.printLine(FarmaPRNUtility.llenarBlancos(65) + VariablesConvenioBTLMF.vRuc.trim(), true);
                vPrintArchivo.printLine(FarmaPRNUtility.llenarBlancos(65) + VariablesConvenioBTLMF.vRuc.trim(), true);

                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine(" ", true);
                vPrintArchivo.printLine(" ", true);

                int linea = 0;

                //imprime el detalle de los productos del comprobante
                for (int i = 0; i < pDetalleComprobante.size(); i++) {
                    //Agregado por DVELIZ 13.10.08
                    String punitario = " ";
                    String valor = " ";

                    String colSubTotal = " ";
                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {
                        valor = ((String)((ArrayList)pDetalleComprobante.get(i)).get(16)).toString().trim();
                        log.debug("valor 1:" + valor);
                        if (valor.equals("0.000"))
                            valor = " ";
                        //fin DVELIZ
                        log.debug("Deta " + (ArrayList)pDetalleComprobante.get(i));
                        log.debug("valor 2:" + valor);
                        colSubTotal = (String)((ArrayList)pDetalleComprobante.get(i)).get(5);
                        punitario = (String)((ArrayList)pDetalleComprobante.get(i)).get(4).toString().trim();
                    }

                    vPrint.printLine("" +
                                     FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                      6) + " " + //Codigo
                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                           11) + "   " + //Cant
                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                             27) + " " + //Descripcion
                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                             11) + "  " + //Presentacion
                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                             16) + "  " + //Prec. Unit.
                            FarmaPRNUtility.

                            alinearDerecha(punitario, 10) + " " +
                            //Agregado por DVELIZ 10.10.08
                            FarmaPRNUtility.

                            alinearDerecha(valor, 8) + "" + //Precio Unit.
                            FarmaPRNUtility.alinearDerecha(colSubTotal.trim(), 10), true); //Monto Total

                    vPrintArchivo.printLine("" +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(6)).trim(),
                                                                             6) + " " +
                                            FarmaPRNUtility.alinearDerecha(((String)((ArrayList)pDetalleComprobante.get(i)).get(0)).trim(),
                                                                           11) + "   " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(1)).trim(),
                                                                             27) + " " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(2)).trim(),
                                                                             11) + "  " +
                                            FarmaPRNUtility.alinearIzquierda(((String)((ArrayList)pDetalleComprobante.get(i)).get(3)).trim(),
                                                                             16) + "  " +
                                            FarmaPRNUtility.alinearDerecha(punitario, 10) + " " +
                            //Agregado por DVELIZ 10.10.08
                            FarmaPRNUtility.alinearDerecha(valor, 8) + "" +
                            FarmaPRNUtility.alinearDerecha(colSubTotal.trim(), 10), true);

                    linea += 1;
                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {
                        subTotal =
                                new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(5)).trim())).floatValue();
                        log.debug("SubTotal:" + subTotal);
                        SumSubTotal = SumSubTotal + subTotal;
                        montoIGV =
                                new Double(FarmaUtility.getDecimalNumber(((String)((ArrayList)pDetalleComprobante.get(i)).get(18)).trim())).floatValue();
                        SumMontoIGV = SumMontoIGV + montoIGV;
                    }
                }

                for (int j = linea; j < ConstantsPtoVenta.TOTAL_LINEAS_POR_BOLETA; j++)
                    vPrint.printLine(" ", true);

                log.debug("SumSubTotal:" + SumSubTotal);

                //*************************************INFORMACION DEL CONVENIO***********************************************//
                double porcCopagoBenef = 0;
                double porcCopagoEmpresa = 0;
                double ValCopagoCompPagoSinIGV = 0;

                porcCopagoBenef = FarmaUtility.getDecimalNumber(vPrctBeneficiario);
                porcCopagoEmpresa = FarmaUtility.getDecimalNumber(vPrctEmpresa);

                ValCopagoCompPagoSinIGV =
                        FarmaUtility.getDecimalNumber(pValCopagoCompPago) - FarmaUtility.getDecimalNumber(pValIgvComCoPago);

                String vRefTipComp = "";

                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_BOLETA))
                    vRefTipComp = "BOL";
                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_FACTURA))
                    vRefTipComp = "FAC";
                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_GUIA))
                    vRefTipComp = "GUIA";
                if (pRefTipComp.equals(ConstantsVentas.TIPO_COMP_TICKET))
                    vRefTipComp = "TKB";

                String vLinea;
                if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {

                    vLinea = FarmaPRNUtility.llenarBlancos(69)+
                            FarmaPRNUtility.alinearDerecha("Sub Total   "+ConstantesUtil.simboloSoles+" ",22) +
                            FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal), 10);
                    vPrint.printLine(vLinea, true);
                    vPrintArchivo.printLine(vLinea, true);
                }

                // KMONCADA 17.11.2014 MOSTRAR DOCUMENTO DE REFERENCIA
                if (pCodTipoConvenio.equals("1") || pCodTipoConvenio.equals("3")) {
                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {
                        double pValTotalNetoRedondeo =
                            FarmaUtility.getDecimalNumber(pValTotalNeto) + FarmaUtility.getDecimalNumber(pValRedondeoComPago);
                        pValTotalNetoRedondeo =
                                FarmaUtility.getDecimalNumber(FarmaUtility.formatNumber(pValTotalNetoRedondeo,
                                                                                        2)); //CHUANES 12.03.2014 SE PONE EL FORMATO DE 2 DECIMALES AL VALOR REDONDEADO

                        vLinea = FarmaPRNUtility.alinearIzquierda(" SON:" +FarmaPRNUtility.montoEnLetras(pValTotalNetoRedondeo).trim(),69)+
                                FarmaPRNUtility.alinearDerecha("Coaseguro(" + FarmaUtility.formatNumber(porcCopagoEmpresa, 0) + "%)" + "   "+ConstantesUtil.simboloSoles+" ",22)+
                                FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(ValCopagoCompPagoSinIGV),10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                        
                        vLinea = FarmaPRNUtility.llenarBlancos(78)+"---------------------";
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                        
                        vLinea = FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase() +
                                                                          "  (" +
                                                                          FarmaUtility.formatNumber(100 - porcCopagoEmpresa,
                                                                                                    "") + ")%",69)+
                                FarmaPRNUtility.alinearDerecha(ConstantesUtil.simboloSoles+" ",22)+
                                FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(SumSubTotal -ValCopagoCompPagoSinIGV),10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);
                        
                        vLinea = FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          69)+
                                FarmaPRNUtility.alinearDerecha("IGV   "+ConstantesUtil.simboloSoles+" ",22)+
                                FarmaPRNUtility.alinearDerecha(pValIgvComPago, 10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);

                        vLinea = FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +VariablesConvenioBTLMF.vNomCliente, 78)+"---------------------";
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);

                        String lineaReferencia = " ";
                        if (vRefTipComp != null && !vRefTipComp.trim().equals("")) {
                            lineaReferencia = "  #REF: " + vRefTipComp + " " +pNumCompCoPago + "(" +
                                                                              FarmaUtility.formatNumber(porcCopagoEmpresa,
                                                                                                        "") + ")" +
                                                                              " - " + ""+ConstantesUtil.simboloSoles + pValCopagoCompPago;                             
                        }
                        vLinea = FarmaPRNUtility.alinearIzquierda(lineaReferencia,69)+
                        FarmaPRNUtility.alinearDerecha("Total   "+ConstantesUtil.simboloSoles+" ",22)+
                        FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo), 10);
                        vPrint.printLine(vLinea, true);
                        vPrintArchivo.printLine(vLinea, true);                        
                    } else {
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase() +
                                                                          "  (" +
                                                                          FarmaUtility.formatNumber(100 - porcCopagoEmpresa,
                                                                                                    "") + ")%", 65) +
                                         "                      ", true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          65) + "     " + " " + "          ", true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                          VariablesConvenioBTLMF.vNomCliente, 65) +
                                         "     " + " " + "          ", true);
                        if (vRefTipComp != null && !vRefTipComp.trim().equals("")) {
                            vPrint.printLine("  #REF: " + vRefTipComp + " " +
                                             FarmaPRNUtility.alinearIzquierda(pNumCompCoPago, 65), true);
                        }


                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                                 VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase() +
                                                                                 "  (" +
                                                                                 FarmaUtility.formatNumber(100 -
                                                                                                           porcCopagoEmpresa,
                                                                                                           "") + ")%",
                                                                                 65) + "                            ",
                                                true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                                 VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                                 65) + "     " + " " + "          ",
                                                true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                 VariablesConvenioBTLMF.vNomCliente,
                                                                                 65) + "     " + " " + "          ",
                                                true);
                        if (vRefTipComp != null && !vRefTipComp.trim().equals("")) {
                            vPrintArchivo.printLine("  #REF: " + vRefTipComp + " " +
                                                    FarmaPRNUtility.alinearIzquierda(pNumCompCoPago, 65), true);
                        }
                    }
                } else {
                    if (VariablesConvenioBTLMF.vFlgImprimeImportes.equals("1")) {
                        double pValTotalNetoRedondeo =
                            FarmaUtility.getDecimalNumber(pValTotalNeto) + FarmaUtility.getDecimalNumber(pValRedondeoComPago);

                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                          65), true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          65) + "     " + " " +
                                         "          IGV    "+ConstantesUtil.simboloSoles+" " +
                                         FarmaPRNUtility.alinearDerecha(pValIgvComPago, 10),
                                         true);
                        if (VariablesConvenioBTLMF.vNomCliente.trim().equals("")) {
                            if (VariablesConvenioBTLMF.vNomClienteDigitado.trim().equals("")) {
                                //vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +"Conv. no cuenta con Beneficiario",65) +"     " +  " " + "          ---------------------",true);
                                vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " + "*", 65) +
                                                 "     " + " " + "          ---------------------", true);
                            } else {
                                vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                  VariablesConvenioBTLMF.vNomClienteDigitado,
                                                                                  65) + "     " + " " +
                                                 "          ---------------------", true);
                            }
                        } else {
                            vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                              VariablesConvenioBTLMF.vNomCliente, 65) +
                                             "     " + " " + "          ---------------------", true);
                        }
                        vPrint.printLine("  " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                         "        Total    "+ConstantesUtil.simboloSoles+" " +
                                         FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo),
                                                                        10), true);


                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                                 VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                                 65) + "                          ",
                                                true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                                 VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                                 65) + "     " + " " +
                                                "          IGV    "+ConstantesUtil.simboloSoles+" " +
                                                FarmaPRNUtility.alinearDerecha(pValIgvComPago,
                                                                               10), true);
                        if (VariablesConvenioBTLMF.vNomCliente.trim().equals("")) {
                            if (VariablesConvenioBTLMF.vNomClienteDigitado.trim().equals("")) {
                                //vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +"Conv. no cuenta con Beneficiario",65) +"     " +  " " + "          ---------------------",true);
                                vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " + "*", 65) +
                                                 "     " + " " + "          ---------------------", true);
                            } else {
                                vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                         VariablesConvenioBTLMF.vNomClienteDigitado,
                                                                                         65) + "     " + " " +
                                                        "          ---------------------", true);
                            }
                        } else {
                            vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                     VariablesConvenioBTLMF.vNomCliente,
                                                                                     65) + "     " + " " +
                                                    "          ---------------------", true);
                        }
                        vPrintArchivo.printLine("   " + FarmaPRNUtility.alinearIzquierda(" ", 65) + " " +
                                                "        Total    "+ConstantesUtil.simboloSoles+" " +
                                                FarmaPRNUtility.alinearDerecha(FarmaUtility.formatNumber(pValTotalNetoRedondeo),
                                                                               10), true);
                    } else {
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                          VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                          65), true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                          VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                          65) + "     " + " " + " ", true);
                        vPrint.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                          VariablesConvenioBTLMF.vNomCliente, 65) +
                                         "     " + " " + "          ", true);

                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Instituci�n: " +
                                                                                 VariablesConvenioBTLMF.vInstitucion.trim().toUpperCase(),
                                                                                 65) + "                          ",
                                                true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Convenio: " +
                                                                                 VariablesConvenioBTLMF.vNomConvenio.trim().toUpperCase(),
                                                                                 65) + "     " + " " +
                                                "                     ", true);
                        vPrintArchivo.printLine(FarmaPRNUtility.alinearIzquierda("  Beneficiario: " +
                                                                                 VariablesConvenioBTLMF.vNomCliente,
                                                                                 65) + "     " + " " + "          ",
                                                true);
                    }
                }

                //se imprime la cabecera de la infomaci�n del convenio
                vPrintArchivo.printLine(" ", true);
                vPrint.printLine("  ", true);

                vPrint.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " + VariablesCaja.vNomCajeroImpreso +
                                 " " + VariablesCaja.vApePatCajeroImpreso + " " + " CAJA: " +
                                 VariablesCaja.vNumCajaImpreso + " TURNO: " + VariablesCaja.vNumTurnoCajaImpreso +
                                 " VEND: " + VariablesCaja.vNomVendedorImpreso + " " +
                                 VariablesCaja.vApePatVendedorImpreso, true);
                vPrintArchivo.printLine(" REDO: " + pValRedondeoComPago + " CAJERO: " +
                                        VariablesCaja.vNomCajeroImpreso + " " + VariablesCaja.vApePatCajeroImpreso +
                                        " " + " CAJA: " + VariablesCaja.vNumCajaImpreso + " TURNO: " +
                                        VariablesCaja.vNumTurnoCajaImpreso + " VEND: " +
                                        VariablesCaja.vNomVendedorImpreso + " " + VariablesCaja.vApePatVendedorImpreso,
                                        true);

                if (pImprDatAdic.equals("1")) {
                    if (VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic != null &&
                        VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 0) {
                        vPrintArchivo.printLine("  Datos Adicionales", true);
                        vPrint.printLine("  Datos Adicionales", true);

                        int nroDatosAdi = VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size();

                        //if(VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.size() > 4 )
                        //{ nroDatosAdi = 4;
                        //}

                        //Se imprime la informacion adicional del convenio
                        String lineaInfAdic = "";
                        for (int j = 0; j < nroDatosAdi; j++) {
                            Map datosAdicConv = (Map)VariablesConvenioBTLMF.vArrayList_DatosConvenioAdic.get(j);

                            String pCodigoCampo = (String)datosAdicConv.get("COD_CAMPO");
                            String pNombCampo = (String)datosAdicConv.get("NOMBRE_CAMPO");

                            String pDesCampo = (String)datosAdicConv.get("DESCRIPCION_CAMPO");
                            String vFlgImprime = (String)datosAdicConv.get("FLG_IMPRIME");

                            log.debug("pDesCampo   :" + pCodigoCampo);
                            log.debug("pNombCampo  :" + pNombCampo);
                            log.debug("vFlgImprime :" + vFlgImprime);

                            //if(!pCodigoCampo.trim().equalsIgnoreCase(ConstantsConvenioBTLMF.COD_DATO_CONV_BENIFICIARIO))
                            //{
                            //  if (pCodigoCampo.equals(ConstantsConvenioBTLMF.COD_NOMB_TITULAR) ||
                            //      pCodigoCampo.equals(ConstantsConvenioBTLMF.COD_NRO_ATENCION) ||
                            //      pCodigoCampo.equals(ConstantsConvenioBTLMF.COD_DATO_CONV_MEDICO))
                            //  {

                            if (vFlgImprime.equals("1") && pNombCampo != null && pDesCampo != null) {
                                //se imprimen dos informaciones en una linea
                                String temp =
                                    FarmaPRNUtility.alinearIzquierda("  - " + pNombCampo + ": " + pDesCampo, 60);

                                if ("".equalsIgnoreCase(lineaInfAdic)) { //si no existe linea, se coloca esta

                                    lineaInfAdic = temp;

                                } else { //si existe una linea, se coloca la siguiente anexa, se imprime y luego se resetea

                                    lineaInfAdic = lineaInfAdic + temp;
                                    vPrintArchivo.printLine(lineaInfAdic, true);
                                    vPrint.printLine(lineaInfAdic, true);
                                    lineaInfAdic = "";

                                    //vPrint.printLine("  - "+pNombCampo +  ":    "+pDesCampo+" ",true);
                                }
                            }
                            //  }
                            //}
                        }
                        //si al terminar de imprimir quedaron datos, imprimir los mismo
                        if (!"".equalsIgnoreCase(lineaInfAdic)) {
                            vPrintArchivo.printLine(lineaInfAdic, true);
                            vPrint.printLine(lineaInfAdic, true);
                            lineaInfAdic = "";
                        }
                    }
                }
                log.debug("Nro de Lineas::" + vPrint.getActualLine());

                //LLEIVA 12-Nov-2013 - La cant de las lineas para la guia es 42, si falta se completa con lineas en blanco
                if (vPrint.getActualLine() < 40) {
                    int dif = 40 - vPrint.getActualLine();
                    for (int i = 0; i < dif; i++) {
                        vPrint.printLine(" ", true);
                        vPrintArchivo.printLine(" ", true);
                    }
                }

                vPrint.deactivateCondensed();
                //vPrint.endPrintService();
                vPrint.endPrintServiceSinCompletarDelivery();
                vPrintArchivo.endPrintService();

                log.info("Fin al imprimir la GUIA: " + pNumComprobante);
                VariablesCaja.vEstadoSinComprobanteImpreso = "N";

                //JCORTEZ 16.07.09 Se guarda fecha de impresion por comprobantes
                DBCaja.actualizaFechaImpr(VariablesCaja.vNumPedVta, pNumComprobante, "C");
                log.debug("Guardando fecha impresion cobro..." + pNumComprobante);
            } catch (SQLException sql) {
                log.error("", sql);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                // enviaErrorCorreoPorDB(sql.toString(),VariablesCaja.vNumPedVta);
            } catch (Exception e) {
                log.error("", e);
                VariablesCaja.vEstadoSinComprobanteImpreso = "S";
                log.info("**** Fecha :" + pFechaBD);
                log.info("**** CORR :" + VariablesCaja.vNumPedVta);
                log.info("**** NUMERO COMPROBANTE :" + pNumComprobante);
                log.info("**** IP :" + FarmaVariables.vIpPc);
                log.info("Error al imprimir la Guia: " + e);
                //JMIRANDA 23/07/09 Envia Error al Imprimir a Email
                //enviaErrorCorreoPorDB(e.toString(),VariablesCaja.vNumPedVta);
            }
        }
    }

    /**
     * Se recupera los comprobantes que emite el convenio
     * @author ERIOS
     * @since 23.04.2014
     * @param pJDialog
     * @return
     */
    public static String[] getComprobantesConvenio(JDialog pJDialog) {
        String comprobantes = "";
        try {
            comprobantes = DBConvenioBTLMF.getCompConvenio();
        } catch (SQLException e) {
            log.error("", e);
            FarmaUtility.showMessage(pJDialog,
                                     "Error al recuperar comprobantes del convenio. Se continua con el cobro.", null);
        }
        String[] lineas = comprobantes.trim().split("@");
        return lineas;
    }

    private static void imprimeFacturaBTL(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                          String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                          String pPorcIgv, String pNumCompCoPago, String pRuta, String pValTotalAhorro,
                                          String pImprDatAdic, String pTipoClienteConvenio, String pCodTipoConvenio,
                                          String pFechaBD, String pRefTipComp, String pValRedondeoComPago,
                                          String vPrctBeneficiario, String vPrctEmpresa, int margen) throws Exception {

        FormatoImpresion formatoImpresion = new FormatoImpresion();
        Impresora impresora = new Impresora();
        ArrayList<String> vPrint =
            formatoImpresion.formatoFacturaBTL(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante,
                                               pValIgvComPago, pValCopagoCompPago, pPorcIgv, pNumCompCoPago,
                                               pValTotalAhorro, true, pImprDatAdic, pTipoClienteConvenio,
                                               pCodTipoConvenio, pFechaBD, pRefTipComp, pValRedondeoComPago,
                                               VariablesConvenioBTLMF.vInstitucion.trim(),
                                               VariablesConvenioBTLMF.vDireccion.trim(),
                                               VariablesConvenioBTLMF.vRuc.trim(), vPrctBeneficiario, vPrctEmpresa,
                                               margen);
        int vlineaFacC = 36; //rherrera
        impresora.imprimir(vPrint, VariablesCaja.vRutaImpresora, true, VariablesCaja.vNumPedVta, pNumComprobante, "C",
                           pRuta, vlineaFacC);


    }

    private static void imprimeGuiaBTL(JDialog pJDialog, ArrayList pDetalleComprobante, String pValTotalNeto,
                                       String pNumComprobante, String pValIgvComPago, String pValCopagoCompPago,
                                       String pValIgvComCoPago, String pNumCompCoPago, String pRuta, boolean bol,
                                       String pImprDatAdic, String pTipoClienteConvenio, String pCodTipoConvenio,
                                       String pFechaBD, String pRefTipComp, String pValRedondeoComPago,
                                       String vPrctBeneficiario, String vPrctEmpresa, int margen) throws Exception {
        FormatoImpresion formatoImpresion = new FormatoImpresion();
        Impresora impresora = new Impresora();
        ArrayList<String> vPrint =
            formatoImpresion.formatoGuiaBTL(pJDialog, pDetalleComprobante, pValTotalNeto, pNumComprobante,
                                            pValIgvComPago, pValCopagoCompPago, pValIgvComCoPago, pNumCompCoPago,
                                            pRuta, bol, pImprDatAdic, pTipoClienteConvenio, pCodTipoConvenio, pFechaBD,
                                            pRefTipComp, pValRedondeoComPago,
                                            VariablesConvenioBTLMF.vInstitucion.trim(),
                                            VariablesConvenioBTLMF.vDireccion.trim(),
                                            VariablesConvenioBTLMF.vRuc.trim(), vPrctBeneficiario, vPrctEmpresa,
                                            margen);

        int vlineaGuiC = 48; //rherrera

        impresora.imprimir(vPrint, VariablesCaja.vRutaImpresora, true, VariablesCaja.vNumPedVta, pNumComprobante, "C",
                           pRuta, vlineaGuiC);
    }
    
    public static boolean imprimeTicketBTLMF(JDialog pJDialog, String pNumeroPedido, String pCajero,
                                             String pTurno) throws Exception {
        return imprimeTicketBTLMF(pJDialog, pNumeroPedido, pCajero, pTurno, null);
    }
    public static boolean imprimeTicketBTLMF(JDialog pJDialog, String pNumeroPedido, String pCajero,
                                             String pTurno, String[] pListaPedidosAnula) throws Exception {

        boolean vResultado = false;
        boolean bRes1 = true;
        boolean bRes2 = true;
        if(UtilityCPE.isActivoFuncionalidad()){
            for (int i = 0; i < pListaPedidosAnula.length; i++) {
                String pNumPedVta = pListaPedidosAnula[i].toString();
                ArrayList lstComprobantes = UtilityCaja.obtieneComprobantesPago(pNumPedVta);
                if(lstComprobantes.size()==0){
                    UtilityCaja.actualizaEstadoPedido(pNumPedVta, ConstantsCaja.ESTADO_COBRADO);
                }
                for (int k = 0; k < lstComprobantes.size(); k++) {
                    String tipoCP = ((String)((ArrayList)lstComprobantes.get(k)).get(4)).trim(); 
                    String secCompPago = ((String)((ArrayList)lstComprobantes.get(k)).get(1)).trim(); 
                    String nroComprobante = ((String)((ArrayList)lstComprobantes.get(k)).get(5)).trim(); 
                    if(tipoCP.equalsIgnoreCase("03")){
                        UtilityCaja.actualizaEstadoPedido(pNumPedVta, ConstantsCaja.ESTADO_COBRADO);
                    }else{
                        bRes1 = (new UtilityCPE()).procesarComprobanteAlEPOS(pJDialog, pNumPedVta, secCompPago, nroComprobante);
                        if(bRes1){
                            UtilityImpCompElectronico impresionElectronico = new UtilityImpCompElectronico();
                            vResultado = impresionElectronico.printDocumento(pNumPedVta, secCompPago, false, true);
                            if (vResultado) {
                                UtilityCaja.actualizarFechaImpresion(pNumPedVta, secCompPago, tipoCP);
                                UtilityCaja.actualizaEstadoPedido(pNumPedVta, ConstantsCaja.ESTADO_COBRADO);
                            }
                            
                        }
                        
                    }
                }
                
            }
        }else{
            // ANULA PEDIDO
            VariablesCaja.vNumPedVta = pNumeroPedido;
            if (UtilityConvenioBTLMF.obtieneCompPago(pJDialog, "", null)) {
                for (int j = 0; j < VariablesConvenioBTLMF.vArray_ListaComprobante.size(); j++) {
                    VariablesConvenioBTLMF.vNumCompPago =
                            ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(0)).trim();
                    bRes1 =
                            UtilityCaja.imprimeMensajeTicketAnulacion(pCajero, pTurno, pNumeroPedido, "00", "N", VariablesConvenioBTLMF.vNumCompPago);
                    //para montos inafectos
                    bRes2 =
                            UtilityCaja.imprimeMensajeTicketAnulacion(pCajero, pTurno, pNumeroPedido, "01", "N", VariablesConvenioBTLMF.vNumCompPago);
                }
            }
        }
        
        if (bRes1 || bRes2)
            vResultado = true;
        else
            vResultado = false;
        return vResultado;
    }
    
    public static boolean imprimeTicketBTLMF_old(JDialog pJDialog, String pNumeroPedido, String pCajero,
                                             String pTurno) throws Exception {

        boolean vResultado = false, bRes1 = true, bRes2 = true;
        if (ConstantesDocElectronico.lstPedidos != null) {
            //GENERA NOTA DE CREDITO
            for (int i = 0; i < ConstantesDocElectronico.lstPedidos.length; i++) {
                VariablesCaja.vNumPedVta = ConstantesDocElectronico.lstPedidos[i].toString();

                UtilityImpCompElectronico impresionElectronico = new UtilityImpCompElectronico();
                if (UtilityCaja.obtieneCompPago()) {
                    for (int k = 0; k < VariablesVentas.vArray_ListaComprobante.size(); k++) {
                        VariablesConvenioBTLMF.vNumCompPago =
                                ((String)((ArrayList)VariablesVentas.vArray_ListaComprobante.get(k)).get(4)).trim(); //LTAVARA 23.09.2014 OBTENER NUMERO ELECTRONICO
                        VariablesConvenioBTLMF.vSecCompPago =
                                ((String)((ArrayList)VariablesVentas.vArray_ListaComprobante.get(k)).get(1)).trim();
                        VariablesConvenioBTLMF.vTipoCompPago =
                                ((String)((ArrayList)VariablesVentas.vArray_ListaComprobante.get(k)).get(2)).trim();
                        VariablesConvenioBTLMF.vTipClienConvenio =
                                ((String)((ArrayList)VariablesVentas.vArray_ListaComprobante.get(k)).get(3)).trim();
                        String indicElectronico =
                            DBImpresoras.getIndicCompElectronico(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago); //indicadorElectronico

                        if (indicElectronico.equalsIgnoreCase(ConstantesDocElectronico.INDELECTRONICO)) {
                            //vResultado=impresionElectronico.printDocumento(pJDialog, VariablesCaja.vNumPedVta ,  VariablesConvenioBTLMF.vSecCompPago,  VariablesConvenioBTLMF.vTipoCompPago,  VariablesConvenioBTLMF.vTipClienConvenio ,  VariablesConvenioBTLMF.vNumCompPago );
                            vResultado =
                                    impresionElectronico.printDocumento(VariablesCaja.vNumPedVta, VariablesConvenioBTLMF.vSecCompPago,
                                                                        false, true);
                            if (vResultado) { //LTAVARA 17.10.2014
                                // KMONCADA 02.12.2014 ACTUALIZA FECHA DE IMPRESION
                                UtilityCaja.actualizarFechaImpresion(VariablesConvenioBTLMF.vSecCompPago,
                                                                     VariablesConvenioBTLMF.vTipoCompPago);
                                UtilityCaja.actualizaEstadoPedido(VariablesCaja.vNumPedVta,
                                                                  ConstantsCaja.ESTADO_COBRADO);
                            }
                        }

                    }
                } else {
                    if (UtilityCaja.obtieneCompPago_GUI()) { //rherrera 18.11.2014

                        for (int k = 0; k < VariablesVentas.vArray_ListaComprobante.size(); k++) {
                            VariablesConvenioBTLMF.vTipoCompPago =
                                    ((String)((ArrayList)VariablesVentas.vArray_ListaComprobante.get(k)).get(0)).trim();
                            if (VariablesConvenioBTLMF.vTipoCompPago.equals("03"))
                                UtilityCaja.actualizaEstadoPedido(VariablesCaja.vNumPedVta,
                                                                  ConstantsCaja.ESTADO_COBRADO);
                        }

                    } //fin
                }
            }

        } else {
            // ANULA PEDIDO
            VariablesCaja.vNumPedVta = pNumeroPedido;
            if (UtilityConvenioBTLMF.obtieneCompPago(pJDialog, "", null)) {
                for (int j = 0; j < VariablesConvenioBTLMF.vArray_ListaComprobante.size(); j++) {
                    VariablesConvenioBTLMF.vNumCompPago =
                            ((String)((ArrayList)VariablesConvenioBTLMF.vArray_ListaComprobante.get(j)).get(0)).trim();
                    bRes1 =
                            UtilityCaja.imprimeMensajeTicketAnulacion(pCajero, pTurno, pNumeroPedido, "00", "N", VariablesConvenioBTLMF.vNumCompPago);
                    //para montos inafectos
                    bRes2 =
                            UtilityCaja.imprimeMensajeTicketAnulacion(pCajero, pTurno, pNumeroPedido, "01", "N", VariablesConvenioBTLMF.vNumCompPago);
                }
            }
        }

        if (bRes1 || bRes2)
            vResultado = true;
        else
            vResultado = false;
        return vResultado;
    }

    public static void cargarVariablesConvenio(String pCodConvenio, JDialog pDialogo, Frame myParentFrame) {
        Map map = UtilityConvenioBTLMF.obtenerConvenio(pCodConvenio, pDialogo, myParentFrame);
        String nombConvenio = (String)map.get(ConstantsConvenioBTLMF.COL_DES_CONVENIO);

        VariablesConvenioBTLMF.vFlgImprimeImportes = (String)map.get(ConstantsConvenioBTLMF.COL_FLG_IMPRIME_IMPORTES);
        VariablesConvenioBTLMF.vIndVtaComplentaria =
                (String)map.get(ConstantsConvenioBTLMF.COL_IND_VTA_COMPLEMENTARIA);
        VariablesConvenioBTLMF.vFlgValidaLincreBenef =
                (String)map.get(ConstantsConvenioBTLMF.COL_FLG_VALIDA_LINCRE_BENEF);
        VariablesConvenioBTLMF.vRuc = (String)map.get(ConstantsConvenioBTLMF.COL_RUC);
        VariablesConvenioBTLMF.vDireccion = (String)map.get(ConstantsConvenioBTLMF.COL_DIRECCION);
        VariablesConvenioBTLMF.vInstitucion = (String)map.get(ConstantsConvenioBTLMF.COL_INSTITUCION);

        VariablesConvenioBTLMF.vNomConvenio = nombConvenio;
    }

    //25.07.2014 muestra mensaje de impresi�n

    /**
     *
     * @param pJDialog
     * @param pTipoComprobante
     * @param pCantDoc
     * @param pSecCompPago
     * @param vNumPedVta
     * @param isMsjContingencia INDICA SI MENSAJE ES PARA CASOS DE CONTIGENCIA DE FACTURACION ELECTRONICA
     */
    public static boolean muestraMensajeImpresion(JDialog pJDialog, String pTipoComprobante, int pCantDoc,
                                                  String pNumDocImpresion, String vNumPedVta,
                                                  boolean isMsjContingencia, boolean isReimpresion) {
        
        log.info("Inicio captura de msj de impresion: "+vNumPedVta+" - "+pNumDocImpresion);
        Map map = obtieneMsgImpresion(pJDialog, null,isMsjContingencia,pTipoComprobante,vNumPedVta,pCantDoc,pNumDocImpresion,isReimpresion);
        log.info("Fin captura de msj de impresion: "+vNumPedVta+" - "+pNumDocImpresion);
        if(map!=null){
            DlgMensajeImpresion dlgMensajeImpresion = new DlgMensajeImpresion(pJDialog, "", true,map);
            dlgMensajeImpresion.setVTipoComprobante(pTipoComprobante);
            dlgMensajeImpresion.setVNumDocImpreso(pCantDoc);
            dlgMensajeImpresion.setVNumeroDocumento(pNumDocImpresion);
            dlgMensajeImpresion.setVNumPedVta(vNumPedVta);
            // KMONCADA 02.12.2014 INDICADOR DE MENSAJE DE CONTIGENCIA
            dlgMensajeImpresion.setIsMsjContingencia(isMsjContingencia);
            dlgMensajeImpresion.setIsReimpresion(isReimpresion);
            dlgMensajeImpresion.setVisible(true);
            log.info("Termina msj de impresion: "+vNumPedVta+" - "+pNumDocImpresion);
            return dlgMensajeImpresion.getMostrarMensaje();
        }
        return false;
        
    }
    
    public static Map obtieneMsgImpresion(JDialog pDialogo, Object pObjeto,boolean isMsjContingencia,
                                    String vTipoComprobante,String vNumPedVta,
                                    int vNumDocImpreso,String vNumeroDocumento,boolean isReimpresion
                                    ) {
        Map resTipo = null;

        try {
            // KMONCADA 02.12.2014 EVALUA SI EL MENSAJE A MOSTRAR EN POR CONTIGENCIA O IMPRESION
            if (isMsjContingencia) {
                //resTipo = DBEpos.obtieneMsgContingencia(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, vNumPedVta, vTipoComprobante);
                resTipo = DBCPElectronico.obtieneMsgContingencia(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, vNumPedVta, vTipoComprobante);
            } else {
                resTipo = DBVentas.obtieneMsgImpFactura(vTipoComprobante, vNumDocImpreso, vNumeroDocumento, vNumPedVta, isReimpresion);
            }
        } catch (Exception sql) {
            log.error("", sql);
            FarmaUtility.showMessage(pDialogo, "Error al obtener el mensaje de impresi�n:\n" +
                    sql.getMessage(), pObjeto);
            resTipo = null;
        }

        return resTipo;
    }

    public static void obtienePrecioConvenio(JDialog pJDialog, Object pObjectFocus) {
        if (UtilityConvenioBTLMF.esActivoConvenioBTLMF(pJDialog, null) &&
            VariablesConvenioBTLMF.vCodConvenio != null &&
            VariablesConvenioBTLMF.vCodConvenio.trim().length() > 0 &&
            VariablesVentas.vCod_Prod.trim().length() == 6 && VariablesVentas.vEstadoProdConvenio.equals("I")) {

            VariablesConvenioBTLMF.vValidaPrecio = true;
            SubProcesosConvenios precConv = new SubProcesosConvenios();
            precConv.start();
        } else {
            UtilityVentas.guardaInfoProdVariables(pJDialog,pObjectFocus);
        }
    }
    
    /**
     * VALIDA SI VENTA DE PRODUCTO POR CONVENIO ES GRATUITO
     * @author KMONCADA
     * @since 23.06.2016
     * @param pNumPedVta
     * @param pCodConvenio
     * @param pCodProd
     * @return
     */
    public static boolean isVtaProdConvenioGratuito(String pNumPedVta, String pCodConvenio, String pCodProd){
        boolean isPermite = false;
        if(pNumPedVta == null || (pNumPedVta!=null && pNumPedVta.trim().length()==0)){
            pNumPedVta = "";
        }
        if(pCodConvenio == null || (pCodConvenio!=null && pCodConvenio.trim().length()==0)){
            pCodConvenio = "";
        }
        try{
            String respuesta = DBConvenioBTLMF.getVtaProdConvenioGratuita(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, pNumPedVta, pCodProd, pCodConvenio);
            if("S".equalsIgnoreCase(respuesta)){
                isPermite = true;
            }
        }catch(Exception ex){
            log.error("", ex);
            isPermite = false;
        }
        return isPermite;
    }
    
    /**
     * valida si el convenio es competencia.
     * @creado KMONCADA
     * @param pCodConvenio
     * @return
     * @since 12.10.2016
     */
    public static boolean isConvenioCompetencia(String pCodConvenio){
        boolean isConvCompetencia = false;
        try{
            String indicador = DBConvenioBTLMF.indicadorConvenioCompetencia(pCodConvenio);
            isConvCompetencia = "S".equalsIgnoreCase(indicador);
        }catch(Exception ex){
            log.error("", ex);
            isConvCompetencia = false;
        }
        return isConvCompetencia;
    }
    
    public static boolean isObligatorioAnularRaC(){
        String rspta = "";
        boolean isObligatorio = false;
        try{
            rspta = DBConvenioBTLMF.getIndAnulacionRacObligatoria();
            if("S".equalsIgnoreCase(rspta))
                isObligatorio = true;
        }catch(Exception ex){
            log.error("", ex);
            isObligatorio = false;
        }
        return isObligatorio;
    }
}
