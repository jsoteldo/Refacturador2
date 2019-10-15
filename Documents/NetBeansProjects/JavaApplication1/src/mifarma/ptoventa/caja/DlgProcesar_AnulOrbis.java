package mifarma.ptoventa.caja;

import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.worker.JDialogProgress;

import farmapuntos.bean.BeanAfiliado;
import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import java.awt.Rectangle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.UtilityTransactionPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgProcesar_AnulOrbis extends JDialogProgress {
    private static final Logger log = LoggerFactory.getLogger(DlgProcesar_AnulOrbis.class);
    private Frame myParentFrame;
    //private boolean vValorProceso;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private String vProductos="";
    private String CodException="";
    
    private boolean estadoProceso=false;
    public DlgProcesar_AnulOrbis() {
        this(null, "", false);
    }
    
    public DlgProcesar_AnulOrbis(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(238, 125));
        this.getContentPane().setLayout(null);
        this.setTitle("Procesando Información . . .");
        this.getContentPane().setLayout(borderLayout1);
        this.setDefaultCloseOperation(0);

        jContentPane.setBounds(new Rectangle(0, 0, 240, 90));

        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        this.setResizable(false);
        FarmaUtility.centrarVentana(this);
    }

    @Override
    public void ejecutaProceso() {
        anulaVta_Orviis();
        if(estadoProceso){
            try{
                DBCaja.eliminaVta_Orviis();
                FarmaUtility.aceptarTransaccion();
                JOptionPane.showOptionDialog(this,"Se libero la venta del sistema Orviis para permitir la anulacion" +
                                             "\nReinicie el proceso de anulacion.",
                                             "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                             JOptionPane.WARNING_MESSAGE,null,null,null);
                cerrarVentana(true);
            }catch(Exception f){
                FarmaUtility.liberarTransaccion();
                JOptionPane.showOptionDialog(this,"Se anulo el registro en orviis pero no en local" +
                                              "\nConsulte a mesa de ayuda...",
                                             "Error de sistema",JOptionPane.CLOSED_OPTION,
                                             JOptionPane.ERROR_MESSAGE,null,null,null);
                cerrarVentana(false);
            }
        }
        log.debug("Retorno de Procesar Anulacion venta orbis:" + estadoProceso);
        log.debug("Termino de procesar !!!!");
        cerrarVentana(estadoProceso);
    }
    
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void anulaVta_Orviis() {
        UtilityTransactionPuntos trnxPuntos = new UtilityTransactionPuntos(VariablesCaja.vNumPedVta_Anul);
        try {
            trnxPuntos.setVAnulaEnOrbis(true);
            trnxPuntos.setVAnulaOnline(true);
            List pListTarjPedido = DBPuntos.getPedAsocTarjeta(VariablesCaja.vNumPedVta_Anul);

            if (pListTarjPedido.size() == 1) {
                log.info("Pedido " + VariablesCaja.vNumPedVta_Anul + " SI tiene ASOCIADO UNA TARJETA ");
                UtilityPuntos.cargaFarmaPuntos();
                Map mapFila = new HashMap();
                mapFila = (HashMap)pListTarjPedido.get(0);

                String pTajPtoAux = mapFila.get("NUM_TARJ_PUNTOS").toString();
                String pDocCli = mapFila.get("DNI_CLI").toString();

                BeanAfiliado afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliadoSinTarjeta(pDocCli, "001");
                System.out.println(afiliado.getTarjetas());
                BeanTarjeta trjBean =
                    VariablesPuntos.frmPuntos.validarTarjetaAsociada(afiliado.getTarjetas().get(0).toString(), "001");

                String pTarjetaEnvOrbis =
                    DBPuntos.getTrajDNI_ClienteOrvee(pDocCli, pTajPtoAux, VariablesCaja.codAnul_Orviis,
                                                     trjBean.getNumeroTarjeta());

                String pTarjetaPedido = mapFila.get("COD_TARJETA").toString();
                trnxPuntos.setVTarjTransaccOrbis(pTarjetaEnvOrbis.trim());
                trnxPuntos.setVDniPedido(pDocCli.trim());
                trnxPuntos.setVTarjetaPedido(pTarjetaPedido.trim());

                List vDataOpera = DBPuntos.getDataTrnxAnula(VariablesCaja.vNumPedVta_Anul);
                Map mapTrnx = new HashMap();
                mapTrnx = (HashMap)vDataOpera.get(0);
                String vIdTrx = mapTrnx.get("ID_TRANSACCION").toString();
                String vNumAut = mapTrnx.get("NUMERO_AUTORIZACION").toString();


                log.info("¿se necesita anular el pedido en Orbis?");
                log.info("-----------------------------" + 
                         "\n         * numeroTarjeta: " + pTarjetaEnvOrbis.trim() +
                         "\n         * idTransaccion: " + vIdTrx + 
                         "\n         * numeroAutorizacion: " + vNumAut +
                         "\n         * numeroPedido: " + VariablesCaja.vNumPedVta_Anul + 
                         "\n         * idEmpleado: " + UtilityPuntos.getDNI_USU() + 
                         "\n----------------------------------" +UtilityPuntos.getDNI_USU() + 
                         "\n         - trjBean:\n" +
                         "\n         - DNI: " + trjBean.getDni() + 
                         "\n         - NroTarjeta: " + trjBean.getNumeroTarjeta() + 
                         "\n         - EstadoTarj: " + trjBean.getEstadoTarjeta() +
                        "\n----------------------------------");
                BeanTarjeta trnxValidaAnula =
                    VariablesPuntos.frmPuntos.eliminarPedido(pTarjetaEnvOrbis.trim(), vIdTrx, vNumAut,
                                                             VariablesCaja.vNumPedVta_Anul,
                                                             UtilityPuntos.getDNI_USU());
                if (WSClientConstans.EXITO.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                    estadoProceso=true;
                } else{
                    FarmaUtility.liberarTransaccion();
                    JOptionPane.showOptionDialog(this,"El error persistente: " +
                                                     "\nPor favor consulte con mesa de ayuda",
                                                     "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                                     JOptionPane.ERROR_MESSAGE,null,null,null);
                }
            } else {
                FarmaUtility.liberarTransaccion();
                JOptionPane.showOptionDialog(this,"La consulta de pedido a una tarjeta monedero presento errores" +
                                             "\nPor favor consulte con mesa de ayuda",
                                             "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                             JOptionPane.ERROR_MESSAGE,null,null,null);
            }

        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            JOptionPane.showOptionDialog(this,
                                         "Error no se logro realizar la reversion del registro en el sistema de puntos monedero" +
                                         "\nPor favor consulte con mesa de ayuda", "Mensaje de sistema",
                                         JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            log.error(e.getMessage());
            log.info("-----------------------------------------------------------------------------------------------");
            e.printStackTrace();
        }
    }

    public void setVProductos(String vProductos) {
        this.vProductos = vProductos;
    }

    public String getVProductos() {
        return vProductos;
    }
    
    public void setCodException(String CodException) {
        this.CodException = CodException;
    }

    public String getCodException() {
        return CodException;
    }
}
