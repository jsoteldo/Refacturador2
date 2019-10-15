package mifarma.ptoventa.caja;

import farmapuntos.bean.BeanAfiliado;
import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.electronico.impresion.dao.ConstantesDocElectronico;

import mifarma.ptoventa.caja.reference.DBCaja;
import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.fraccion.modelo.VariableProducto;
import mifarma.ptoventa.puntos.reference.DBPuntos;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.UtilityTransactionPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.test_desa.DlgTestMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgAnulacionOrviis extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgTestMenu.class);
    private Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlTitulo = new JPanel();
    private JPanel pnlSubTitle = new JPanel();
    private JButton lblTitulo = new JButton();
    private JButton btnDesligarVenta = new JButton();
    private JButton btnAnulaOrviis = new JButton();
    private JButton btnSalir = new JButton();
    private String vProductos="";
    private String CodException="";
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private boolean autoProceso=false;
    private JPanel pnlManual = new JPanel();
    private JPanel pnlAutomatico = new JPanel();
    private JLabel lblMsj = new JLabel();

    public DlgAnulacionOrviis() {
        super();
    }
    
    public DlgAnulacionOrviis(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(318, 223));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Menu de pruebas");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.white);
        this.setBackground(Color.white);
                
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        ctpContenido.setBackground(Color.white);
        ctpContenido.setLayout(null);
        ctpContenido.setSize(new Dimension(623, 439));
        ctpContenido.setForeground(Color.white);

        pnlTitulo.setBounds(new Rectangle(5, 5, 295, 40));
        pnlTitulo.setBackground(new Color(43, 141, 39));
        pnlTitulo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlTitulo.setLayout(null);


        lblTitulo.setText("LIBERACION DE VENTA LOCAL DE VENTA MONEDERO");
        lblTitulo.setBounds(new Rectangle(5, 10, 285, 20));
        lblTitulo.setBackground(SystemColor.window);
        lblTitulo.setForeground(SystemColor.window);
        lblTitulo.setFont(new Font("SansSerif", 1, 14));
        
        lblTitulo.setMnemonic('I');
        lblTitulo.setBackground(new Color(255, 130, 14));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblTitulo.setBorderPainted(false);
        lblTitulo.setContentAreaFilled(false);
        lblTitulo.setDefaultCapable(false);
        lblTitulo.setFocusPainted(false);
        lblTitulo.setForeground(Color.white);
        lblTitulo.setFont(new Font("SansSerif", 1, 11));
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitulo.setRequestFocusEnabled(false);
        
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    lblTitulo_KeyPressed(e);
                }
            });
        
        btnDesligarVenta.setText("[F3] Actualizar venta de local");
        btnDesligarVenta.setBounds(new Rectangle(45, 60, 210, 20));
        btnDesligarVenta.setMnemonic('A');
        btnDesligarVenta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnDesligaVenta_ActionPerformed(e);
                }
            });
        btnAnulaOrviis.setText("[F2] Revertir venta monedero");
        btnAnulaOrviis.setBounds(new Rectangle(45, 15, 210, 20));
        btnAnulaOrviis.setMnemonic('R');
        btnAnulaOrviis.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAnulaOrviis_ActionPerformed(e);
                }
            });
        btnSalir.setText("[ESC] Salir");
        btnSalir.setBounds(new Rectangle(45, 160, 210, 20));
        btnSalir.setMnemonic('s');
        btnSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnSalir_ActionPerformed(e);
                }
            });
        jLabel1.setText("1. Paso Nro 1:");
        jLabel1.setBounds(new Rectangle(30, 0, 120, 15));
        jLabel1.setFont(new Font("Tahoma", 1, 12));
        jLabel2.setText("2. Paso Nro 2:");
        jLabel2.setBounds(new Rectangle(30, 45, 115, 15));
        jLabel2.setFont(new Font("Tahoma", 1, 12));
        
        pnlAutomatico.setBackground(SystemColor.window);
        pnlAutomatico.setLayout(null);
        lblMsj.setText("<HTML><CENTER>POR FAVOR ESPERE<BR>El proceso termina en un momento</HTML></CENTER>");
        lblMsj.setBounds(new Rectangle(10, 15, 265, 55));
        lblMsj.setFont(new Font("Tahoma", 1, 14));
        lblMsj.setForeground(new Color(33, 33, 255));
        lblMsj.setHorizontalAlignment(SwingConstants.CENTER);
        lblMsj.setHorizontalTextPosition(SwingConstants.CENTER);
        pnlManual.setBackground(SystemColor.window);
        pnlManual.setLayout(null);
        
        pnlSubTitle.setBounds(new Rectangle(5, 40, 295, 20));
        pnlSubTitle.setBackground(new Color(255, 130, 14));
        pnlSubTitle.setLayout(null);

        pnlAutomatico.add(lblMsj, null);
        ctpContenido.add(pnlAutomatico, null);
        ctpContenido.add(pnlManual, null);
        ctpContenido.add(btnSalir, null);
        pnlManual.add(btnAnulaOrviis, null);
        pnlManual.add(btnDesligarVenta, null);
        pnlManual.add(jLabel2, null);
        pnlManual.add(jLabel1, null);
        pnlTitulo.add(lblTitulo, null);
        ctpContenido.add(pnlTitulo, null);
        ctpContenido.add(pnlSubTitle, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaVariables.vAceptar=false;
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(lblTitulo);
        if(autoProceso){
            pnlManual.setBounds(new Rectangle(5, 190, 295, 90));
            pnlManual.setVisible(false);
            pnlAutomatico.setBounds(new Rectangle(5, 60, 295, 90));
            pnlAutomatico.setVisible(true);
            liberacionAutomatica();
        }else{
            pnlManual.setBounds(new Rectangle(5, 60, 295, 90));
            pnlManual.setVisible(true);
            pnlAutomatico.setBounds(new Rectangle(5, 190, 295, 90));
            pnlAutomatico.setVisible(false);
        }
    }

    private void this_windowClosing(WindowEvent e) {
        muestraMensaje("Debe presionar la tecla ESC para cerrar la ventana.", null);
        FarmaUtility.moveFocus(lblTitulo);
    }

    private void evento_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    
    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            cerrarVentana(false);
            break;
        case KeyEvent.VK_F2:
            btnAnulaOrviis.doClick();
            break;
        case KeyEvent.VK_F3:
            if(FarmaVariables.vAceptar){
                btnDesligarVenta.doClick();
            }else{
                JOptionPane.showOptionDialog(this,"No se puede desligar el registro de la venta en el local del sistema de puntos monedero" +
                                                  "\nAntes debe de anular el registro del sistema de puntos monedero ",
                                                  "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                                  JOptionPane.ERROR_MESSAGE,null,null,null);
            }
            break;
        }
    }

    private void cerrarVentana(boolean valorAnul) {
        FarmaVariables.vAceptar=valorAnul;
        this.setVisible(false);
        this.dispose();
    }
    private void muestraMensaje(String msj, Object cTxt) {
        FarmaUtility.showMessage(this, msj, cTxt);
    }

    private void lblTitulo_KeyPressed(KeyEvent e) {
        evento_keyPressed(e);
    }

    private void btnDesligaVenta_ActionPerformed(ActionEvent e) {
        desligarVentaOrviis();
    }

    private void btnAnulaOrviis_ActionPerformed(ActionEvent e) {
        anulaVta_Orviis();
    }

    private void btnSalir_ActionPerformed(ActionEvent e) {
        cerrarVentana(false);
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
    
    private void desligarVentaOrviis(){
        try {
            DBCaja.eliminaVta_Orviis();
            FarmaUtility.aceptarTransaccion();
            JOptionPane.showOptionDialog(this,"Se modifico el registro de ventas. Venta ya no corresponde a monedero" +
                                         "\nReinicie el proceso de anulacion.",
                                         "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                         JOptionPane.WARNING_MESSAGE,null,null,null);
            cerrarVentana(true);
        } catch (SQLException e) {
            FarmaUtility.liberarTransaccion();
            JOptionPane.showOptionDialog(this,"No se logro modificar el registro de venta\nConsulte con mesa de ayuda...",
                                         "Error de sistema",JOptionPane.CLOSED_OPTION,
                                         JOptionPane.ERROR_MESSAGE,null,null,null);
            log.error(e.getErrorCode()+"\n"+e.getMessage());
        }
    }
    
    private void anulaVta_Orviis(){
        FarmaVariables.vAceptar=false;
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
                
                String pTajPtoAux= mapFila.get("NUM_TARJ_PUNTOS").toString();
                String pDocCli = mapFila.get("DNI_CLI").toString();
                
                BeanAfiliado afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliadoSinTarjeta(pDocCli, "001");
                System.out.println(afiliado.getTarjetas());
                BeanTarjeta trjBean = VariablesPuntos.frmPuntos.validarTarjetaAsociada(afiliado.getTarjetas().get(0).toString(), "001");
                
                String pTarjetaEnvOrbis =DBPuntos.getTrajDNI_ClienteOrvee(pDocCli,pTajPtoAux,VariablesCaja.codAnul_Orviis,trjBean.getNumeroTarjeta());
                
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
                log.info("----------------------------------" + 
                "\n         * numeroTarjeta: " + pTarjetaEnvOrbis.trim()+
                "\n         * idTransaccion: " + vIdTrx+
                "\n         * numeroAutorizacion: " + vNumAut+
                "\n         * numeroPedido: " + VariablesCaja.vNumPedVta_Anul+
                "\n         * idEmpleado: " + UtilityPuntos.getDNI_USU()+
                "\n----------------------------------" + UtilityPuntos.getDNI_USU()+
                "\n         - trjBean:\n"+
                "\n         - DNI: "+trjBean.getDni()+
                "\n         - NroTarjeta: "+trjBean.getNumeroTarjeta()+
                "\n         - EstadoTarj: "+trjBean.getEstadoTarjeta()+
                "\n----------------------------------");
                BeanTarjeta trnxValidaAnula = 
                    VariablesPuntos.frmPuntos.eliminarPedido(pTarjetaEnvOrbis.trim(), 
                                                             vIdTrx, 
                                                             vNumAut, 
                                                             VariablesCaja.vNumPedVta_Anul, 
                                                             UtilityPuntos.getDNI_USU());
                
                if (WSClientConstans.EXITO.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                    boolean rptaAnul=trnxPuntos.anulaOrbis();
                    if(rptaAnul){
                        FarmaUtility.aceptarTransaccion();
                        JOptionPane.showOptionDialog(this,"Se revirtio el registro de venta del monedero",
                                                     "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                                     JOptionPane.INFORMATION_MESSAGE,null,null,null);
                        FarmaVariables.vAceptar=true;
                    }else{
                        FarmaUtility.liberarTransaccion();
                        JOptionPane.showOptionDialog(this,"No se puede realizar la anulacion de la venta en orviis" +
                                                     "\nPor favor consulte con mesa de ayuda",
                                                     "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                                     JOptionPane.ERROR_MESSAGE,null,null,null);
                    }
                }else{
                    FarmaUtility.liberarTransaccion();
                    JOptionPane.showOptionDialog(this,"El error persistente: " +
                                                     "\nPor favor consulte con mesa de ayuda",
                                                     "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                                     JOptionPane.ERROR_MESSAGE,null,null,null);
                }
            }else{
                FarmaUtility.liberarTransaccion();
                JOptionPane.showOptionDialog(this,"La consulta de pedido a una tarjeta monedero presento errores" +
                                             "\nPor favor consulte con mesa de ayuda",
                                             "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                             JOptionPane.ERROR_MESSAGE,null,null,null);
            }
        } catch (Exception e) {
            FarmaUtility.liberarTransaccion();
            JOptionPane.showOptionDialog(this,"Error no se logro realizar la reversion del registro en el sistema de puntos monedero" +
                                         "\nPor favor consulte con mesa de ayuda",
                                         "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                         JOptionPane.ERROR_MESSAGE,null,null,null);
            e.printStackTrace();
        }
    }

    private void liberacionAutomatica() {        
        FarmaVariables.vAceptar=false;
        UtilityTransactionPuntos trnxPuntos = new UtilityTransactionPuntos(VariablesCaja.vNumPedVta_Anul);
        try{
            trnxPuntos.setVAnulaEnOrbis(true);
            trnxPuntos.setVAnulaOnline(true);
            List pListTarjPedido = DBPuntos.getPedAsocTarjeta(VariablesCaja.vNumPedVta_Anul);
            
            if (pListTarjPedido.size() == 1) {
                log.info("Pedido " + VariablesCaja.vNumPedVta_Anul + " SI tiene ASOCIADO UNA TARJETA ");
                UtilityPuntos.cargaFarmaPuntos();
                Map mapFila = new HashMap();
                mapFila = (HashMap)pListTarjPedido.get(0);
                
                String pTajPtoAux= mapFila.get("NUM_TARJ_PUNTOS").toString();
                String pDocCli = mapFila.get("DNI_CLI").toString();
                
                BeanAfiliado afiliado = VariablesPuntos.frmPuntos.obtenerDatosAfiliadoSinTarjeta(pDocCli, "001");
                System.out.println(afiliado.getTarjetas());
                BeanTarjeta trjBean = VariablesPuntos.frmPuntos.validarTarjetaAsociada(afiliado.getTarjetas().get(0).toString(), "001");
                
                String pTarjetaEnvOrbis =DBPuntos.getTrajDNI_ClienteOrvee(pDocCli,pTajPtoAux,VariablesCaja.codAnul_Orviis,trjBean.getNumeroTarjeta());
                
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
                log.info("----------------------------------" + 
                "\n         * numeroTarjeta: " + pTarjetaEnvOrbis.trim()+
                "\n         * idTransaccion: " + vIdTrx+
                "\n         * numeroAutorizacion: " + vNumAut+
                "\n         * numeroPedido: " + VariablesCaja.vNumPedVta_Anul+
                "\n         * idEmpleado: " + UtilityPuntos.getDNI_USU()+
                "\n----------------------------------" + UtilityPuntos.getDNI_USU()+
                "\n         - trjBean:\n"+
                "\n         - DNI: "+trjBean.getDni()+
                "\n         - NroTarjeta: "+trjBean.getNumeroTarjeta()+
                "\n         - EstadoTarj: "+trjBean.getEstadoTarjeta()+
                "\n----------------------------------");
                BeanTarjeta trnxValidaAnula = 
                    VariablesPuntos.frmPuntos.eliminarPedido(pTarjetaEnvOrbis.trim(), 
                                                             vIdTrx, 
                                                             vNumAut, 
                                                             VariablesCaja.vNumPedVta_Anul, 
                                                             UtilityPuntos.getDNI_USU());
                
                if (WSClientConstans.EXITO.equalsIgnoreCase(trnxValidaAnula.getEstadoOperacion())) {
                    FarmaUtility.aceptarTransaccion();
                    FarmaVariables.vAceptar=true;
                }else
                    FarmaUtility.liberarTransaccion();                
            }else
                FarmaUtility.liberarTransaccion();            
            
            if(FarmaVariables.vAceptar){
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
            }else{
                FarmaUtility.liberarTransaccion();
                JOptionPane.showOptionDialog(this,"No se logro anular el registro de venta en orviis" +
                                             "\nPor favor consulte con mesa de ayuda",
                                             "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                             JOptionPane.ERROR_MESSAGE,null,null,null);
                cerrarVentana(false);
            }
        }catch(Exception e){
            FarmaUtility.liberarTransaccion();
            JOptionPane.showOptionDialog(this,"Error no se logro realizar la reversion del registro en el sistema de puntos monedero" +
                                         "\nPor favor consulte con mesa de ayuda",
                                         "Mensaje de sistema",JOptionPane.CLOSED_OPTION,
                                         JOptionPane.ERROR_MESSAGE,null,null,null);
            e.printStackTrace();
            cerrarVentana(false);
        }
    }

    public void setAutoProceso(boolean autoProceso) {
        this.autoProceso = autoProceso;
    }

    public boolean isAutoProceso() {
        return autoProceso;
    }
}
