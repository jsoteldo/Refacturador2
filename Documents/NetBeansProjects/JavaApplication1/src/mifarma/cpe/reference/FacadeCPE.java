package mifarma.cpe.reference;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.cpe.UtilityCPE;
import mifarma.cpe.dao.DAOCPElectronico;
import mifarma.cpe.dao.MBCPElectronico;
import mifarma.cpe.modelo.BeanConexionEPOS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FacadeCPE {

    private static final Logger log = LoggerFactory.getLogger(FacadeCPE.class);

    private DAOCPElectronico daoCPE;
    private String pCodGrupoCia;
    private String pCodLocal;

    public FacadeCPE() {
        super();
        this.pCodGrupoCia = FarmaVariables.vCodGrupoCia;
        this.pCodLocal = FarmaVariables.vCodLocal;
    }

    public String getIndicadorFuncionalidad() {
        String rspta = "N";
        daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            rspta = daoCPE.obtenerIndicadorFuncionalidad(pCodGrupoCia, pCodLocal);
            daoCPE.commit();
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
        }
        return rspta;
    }

    public BeanConexionEPOS obtenerDatosConexion(String pCodCia) {
        BeanConexionEPOS bean;
        List<BeanConexionEPOS> lstBean = new ArrayList<BeanConexionEPOS>();
        DAOCPElectronico daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            lstBean = daoCPE.obtenerDatosConexion(pCodGrupoCia, pCodCia, pCodLocal);
            daoCPE.commit();
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
            lstBean = new ArrayList<BeanConexionEPOS>();
        }
        if (lstBean.size() == 1) {
            bean = lstBean.get(0);
        } else
            bean = null;
        return bean;
    }

    public String getPCRegistradaEnEpos(String pIpPC) {
        String rspta = "N";
        daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            rspta = daoCPE.getPCRegistradaEnEpos(pCodGrupoCia, pCodLocal, pIpPC);
            daoCPE.commit();
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
        }
        return rspta;
    }

    public void actualizarRegistroPC(String pIpPc, String pEstado, String pMensaje) {
        daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            daoCPE.actualizarEstadoPCRegistrada(pCodGrupoCia, pCodLocal, pIpPc, pEstado, pMensaje);
            daoCPE.commit();
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
        }
    }

    public boolean actualizaComprobantePago(String pNumPedVta, String pSecCompPago, String pCodPDF417,
                                            String pTipoCP) {
        boolean resultado = false;
        daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            daoCPE.actualizarComprobanteProcesado(pCodGrupoCia, pCodLocal, pNumPedVta, pSecCompPago, pCodPDF417,
                                                  pTipoCP);
            daoCPE.commit();
            resultado = true;
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
        }
        return resultado;
    }

    public boolean actualizaComprobantePago_code(String pNumPedVta, String pSecCompPago, String pCodPDF417,
                                                 String pTipoCP, String pTipoCode) {
        boolean resultado = false;
        daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            daoCPE.actualizarComprobanteProcesado_code(pCodGrupoCia, pCodLocal, pNumPedVta, pSecCompPago, pCodPDF417,
                                                       pTipoCP, pTipoCode);
            daoCPE.commit();
            resultado = true;
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
        }
        return resultado;
    }

    public void enviarCorreo(int tipoMensaje, String mensaje) {
        daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            daoCPE.enviarMensajeCorreo(pCodGrupoCia, pCodLocal, tipoMensaje, mensaje);
            daoCPE.commit();
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
        }
    }

    public int obtenerTiempoInactividadHilo() {
        int tiempo = 10;
        daoCPE = null;
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            String valor = daoCPE.obtenerTiempoInactividadHilo();
            tiempo = FarmaUtility.trunc(valor);
            daoCPE.commit();
        } catch (Exception ex) {
            log.error("", ex);
            daoCPE.rollback();
            tiempo = 10;
        } finally {
            daoCPE = null;
        }
        return tiempo;
    }

    public String obtenerTramaCPE(String pNumPedVta, String pSecCompPago, String pTipoComprobante,
                                  String pTipoClienteConv) throws Exception {
        daoCPE = null;
        String tramaEPOS = "";
        try {
            daoCPE = new MBCPElectronico();
            daoCPE.openConnection();
            // GENERA TRAMA DEL COMPROBANTE
            HashMap<String, Object> mapParametros = new HashMap<String, Object>();
            mapParametros.put("cGrupoCia", pCodGrupoCia);
            mapParametros.put("cCodLocal", pCodLocal);
            mapParametros.put("cNumPedidoVta", pNumPedVta);
            mapParametros.put("cSecCompPago", pSecCompPago);
            mapParametros.put("cTipoDocumento", pTipoComprobante);
            mapParametros.put("cTipoClienteConvenio", pTipoClienteConv);

            String partCab = daoCPE.obtenerTramaCab(mapParametros);
            UtilityCPE.isValidaParteTrama(partCab, "CABECERA");

            String partExt = daoCPE.obtenerTramaExt(mapParametros);
            UtilityCPE.isValidaParteTrama_V2(partExt, "CABECERA EXTENSION");

            String partPerc = daoCPE.obtenerTramaPerc(mapParametros);
            UtilityCPE.isValidaParteTrama_V2(partPerc, "PERCEPCION");

            String partDoc = daoCPE.obtenerTramaDoc(mapParametros);
            UtilityCPE.isValidaParteTrama(partDoc, "DOCUMENTO");

            if(partDoc.equalsIgnoreCase("SI")){
                partDoc="";
            }
            
            String partNotas = daoCPE.obtenerTramaNot(mapParametros);
            UtilityCPE.isValidaParteTrama(partNotas, "NOTAS");

            String partDet = "";
            ArrayList<ArrayList<String>> lstDet = daoCPE.obtenerTramaDet(mapParametros);
            for (int i = 0; i < lstDet.size(); i++) {
                ArrayList<String> lstDetFila = lstDet.get(i);
                for (int j = 0; j < lstDetFila.size(); j++) {
                    partDet = partDet + lstDetFila.get(j);
                }
            }
            UtilityCPE.isValidaParteTrama(partDet, "DETALLE");
            partDet = partDet;

            String partIG = daoCPE.obtenerTramaIG(mapParametros);
            if (partIG == null)
                partIG = "";

            String partREF = daoCPE.obtenerTramaRef(mapParametros);
            if (partREF == null)
                partREF = "";

            String partPP = daoCPE.obtenerTramaPP(mapParametros);
            if (partPP == null)
                partPP = "";

            String partMsjBF = daoCPE.obtenerTramaMsjBF(mapParametros);
            UtilityCPE.isValidaParteTrama(partMsjBF, "MENSAJES ANTES DE TIMBRE");

            String partMsjAF = daoCPE.obtenerTramaMsjAF(mapParametros);
            UtilityCPE.isValidaParteTrama(partMsjAF, "MENSAJES DESPUES DE TIMBRE");

            String partMsjPersonaliza = daoCPE.obtenerTramaMsjPersonalizado(mapParametros);
            if (partMsjPersonaliza == null)
                partMsjPersonaliza = "";

            tramaEPOS =
                    partCab + partExt.replaceAll(" ", "") + partPerc.replaceAll(" ", "") + partDoc + partNotas + partDet + partIG + partREF + partPP + partMsjBF +
                    partMsjAF + partMsjPersonaliza;
            //guardarTrama(tramaEPOS);
            System.out.println("TRAMA_EPOS ---> " + tramaEPOS);
            if (tramaEPOS == null || (tramaEPOS != null && tramaEPOS.trim().length() == 0)) {
                throw new Exception("[EPOS - GENERA TRAMA] La trama esta sin datos");
            }

            daoCPE.commit();
        } catch (Exception ex) {
            String mensajeError = "";
            if (ex.getCause() instanceof SQLException) {
                mensajeError = ((SQLException)ex.getCause()).getMessage();
            } else {
                mensajeError = ex.getMessage();
            }
            daoCPE.rollback();
            throw new Exception("ERROR AL OBTENER TRAMA DE COMPROBANTE DE PAGO\n" +
                    mensajeError);
        }
        return tramaEPOS;

    }

    /*private void guardarTrama(String trama) {
        try {
            String ruta = "C:\\Users\\desarrollo9\\Desktop\\Trama\\trama.txt";
            File archivo = new File(ruta);
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(trama);
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(trama);
            }
            bw.close();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }*/
}
