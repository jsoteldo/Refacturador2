package mifarma.ptoventa.caja;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.fidelizacion.reference.VariablesFidelizacion;
import mifarma.ptoventa.main.FrmEconoFar;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.ventas.reference.ConstantsVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2013 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 11g<br>
 * Nombre de la Aplicación : DlgDatosTipoTarjetaCampanaR.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * AOVIEDO      22.06.2017   Creación<br>
 * <br>
 * @author AURELIO OVIEDO<br>
 * @version 1.0<br>
 *
 */
public class DlgDatosTipoTarjetaCampanaR extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgDatosTipoTarjetaCampanaR.class);

    private Frame myParentFrame;
    private JPanelWhite pnlFondo = new JPanelWhite();
    private JPanel pnlInfo = new JPanel();
    private JPanelTitle pnlTitle = new JPanelTitle();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();
    //private JTextFieldSanSerif txtNombreTarjeta = new JTextFieldSanSerif();
    private JTextField txtNombreTarjeta = new JTextField();
    private JButtonLabel lblNroTarjeta = new JButtonLabel();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabel lblSinFormaPago = new JLabel();

    private JPanel pnlTipoTarjeta = new JPanel();
    /*private JPanelImagenTransparente pnlAmex;
    private JPanelImagenTransparente pnlMasterCard;
    private JPanelImagenTransparente pnlVisa;
    private JPanelImagenTransparente pnlDiners;
    private JPanelImagenTransparente pnlCmr;*/

    private int cantidadLargo = 6;
    private List<JPanelImagenTransparente> lstPanelTarjetas = new ArrayList<JPanelImagenTransparente>();
    
    private List lstTarjetaLogo;
    
    private FarmaTableModel tableModelListaCampanas;
    private final int COL_CAMP = 0;
    private final int COL_DESC_CAMP = 1;
    private final int COL_MENSAJE = 2;
    private JPanel pnlCampanas = new JPanel();
    private JScrollPane scrListaCampanas = new JScrollPane();
    private JScrollPane scrMensajeCampana = new JScrollPane();
    private JTable tblCampanas = new JTable();
    private JTextArea txtDescCampana = new JTextArea();
    private String indMuestraMensaje = "";
    private String codFormaPagoDlg = "";
    private String tipoCampanaR = "";
    private ArrayList pListCupones = new ArrayList();
    private String numCupon = "";

    public DlgDatosTipoTarjetaCampanaR() {
        this(null, "", false, "");
    }

    public DlgDatosTipoTarjetaCampanaR(Frame parent, String title, boolean modal, String tipoCampanaR) {
        super(parent, title, modal);
        this.myParentFrame = parent;
        this.tipoCampanaR = tipoCampanaR;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(522, 511));
        this.getContentPane().setLayout(null);
        this.setTitle("Forma de Pago");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        
        /*pnlVisa = new JPanelImagenTransparente(0);
        pnlMasterCard = new JPanelImagenTransparente(1);
        pnlAmex = new JPanelImagenTransparente(2);
        pnlDiners = new JPanelImagenTransparente(3);
        pnlCmr = new JPanelImagenTransparente(4);*/

        pnlFondo.setBounds(new Rectangle(0, 0, 520, 490));
        
        pnlInfo.setBounds(new Rectangle(10, 25, 490, 150));
        pnlInfo.setBackground(Color.white);
        pnlInfo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlInfo.setLayout(null);
        pnlInfo.setFocusable(false);
        
        pnlTitle.setBounds(new Rectangle(10, 5, 490, 20));
        pnlTitle.setFocusable(false);
        
        jLabelWhite1.setText("Seleccione Forma de Pago");
        jLabelWhite1.setBounds(new Rectangle(5, 0, 160, 20));
        jLabelWhite1.setFocusable(false);
        
        txtNombreTarjeta.setBounds(new Rectangle(220, 120, 165, 20));
        txtNombreTarjeta.setEditable(false);
        txtNombreTarjeta.setFont(new Font("SansSerif", 1, 13));
        txtNombreTarjeta.setForeground(new Color(198, 0, 0));
        txtNombreTarjeta.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                txtNombreTarjeta_keyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                    txtNombreTarjeta_keyPressed(e);
                }
            
            public void keyReleased(KeyEvent e) {
                txtNombreTarjeta_keyReleased(e);
            }
        });

        lblNroTarjeta.setText("Forma de Pago");
        lblNroTarjeta.setBounds(new Rectangle(105, 120, 110, 20));
        lblNroTarjeta.setForeground(new Color(255, 130, 14));
        lblNroTarjeta.setMnemonic('n');
        lblNroTarjeta.setFocusable(false);
        lblNroTarjeta.setFont(new Font("SansSerif", 1, 13));
        lblNroTarjeta.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNroTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNroTarjeta_actionPerformed(e);
            }
        });
        
        pnlCampanas.setBounds(new Rectangle(10, 180, 495, 255));
        pnlCampanas.setBackground(Color.white);
        pnlCampanas.setLayout(null);
        pnlCampanas.setBorder(BorderFactory.createTitledBorder("Promociones: "));
        //pnlCampanas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        tblCampanas.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblCampanas_keyPressed(e);
                }
            });
        
        scrListaCampanas.setBounds(new Rectangle(5, 15, 485, 100));
        scrListaCampanas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrListaCampanas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                scrListaCampanas_keyPressed(e);
            }
        });
        scrListaCampanas.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                scrListaCampanas_focusGained(e);
            }
        });

        txtDescCampana.setEditable(false);
        txtDescCampana.setFont(new Font("Arial", 1, 22));
        txtDescCampana.setForeground(new Color(214, 107, 0));
        //txtDescCampana.setBounds(new Rectangle(5, 120, 380, 130));
        pnlTitle.add(jLabelWhite1, null);

        pnlFondo.add(lblF11, null);
        //pnlFondo.add(lblSinFormaPago, null);
        pnlFondo.add(pnlTitle, null);
        pnlFondo.add(pnlInfo, null);
        
        scrListaCampanas.getViewport().add(tblCampanas, null);
        pnlCampanas.add(scrListaCampanas, null);
        
        scrMensajeCampana.setBounds(new Rectangle(5, 120, 485, 130));
        scrMensajeCampana.getViewport().add(txtDescCampana, null);
        pnlCampanas.add(scrMensajeCampana, null);
        pnlFondo.add(pnlCampanas, null);
        pnlTipoTarjeta.setBackground(SystemColor.window);
        pnlTipoTarjeta.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                                                                                                  new Color(43, 141,
                                                                                                            39)),
                                                                  "Seleccione Forma de Pago",
                                                                  TitledBorder.DEFAULT_JUSTIFICATION,
                                                                  TitledBorder.DEFAULT_POSITION,
                                                                  new Font("SansSerif", 1, 12),
                                                                  new Color(43, 141, 39)));
        pnlTipoTarjeta.setLayout(null);

        pnlTipoTarjeta.setBounds(new Rectangle(10, 5, 470, 110));
        pnlTipoTarjeta.setOpaque(false);

        /*agregarPanelTarjeta(pnlVisa);
        agregarPanelTarjeta(pnlMasterCard);
        agregarPanelTarjeta(pnlAmex);
        agregarPanelTarjeta(pnlDiners);*/
        //agregarPanelTarjeta(pnlCmr); // KMONCADA 17.03.2016 CAMBIO SOLICITADO POR RCASTRO
        pnlInfo.add(pnlTipoTarjeta, null);
        pnlInfo.add(lblNroTarjeta, null);
        pnlInfo.add(txtNombreTarjeta, null);
        this.getContentPane().add(pnlFondo, null);
        //this.getContentPane().add(pnlCampanas, null);
        lblF11.setText("[ ENTER ] Aceptar");
        lblF11.setFocusable(false);
        lblSinFormaPago.setText("Si no encuentra una forma de pago, presione [ESC]");
        lblSinFormaPago.setFocusable(false);
        //lstPanelTarjetas.get(0).activaPanel(txtNombreTarjeta);
    }
    
    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableListaCampanas();
        listarCampanas(this.codFormaPagoDlg, true);
    }
    
    private void initTableListaCampanas() {
        tableModelListaCampanas = new FarmaTableModel(ConstantsVentas.columnsListaCampanas, 
                                                      ConstantsVentas.defaultValuesListaCampanas, COL_CAMP);
        FarmaUtility.initSimpleList(tblCampanas, tableModelListaCampanas, ConstantsVentas.columnsListaCampanas);
    }

    protected void agregarPanelTarjeta(JPanelImagenTransparente panel){
        int longPanel = 70;
        int altoPanel = 80;
        int cantidadPaneles = lstPanelTarjetas.size();
        int altoPanelContenedor = 30;
        int posX = 10;
        int posY = 20;
        int cantidadFila = (cantidadPaneles/cantidadLargo);
        posY = 20+(altoPanel*(cantidadFila));
        posX = 10+(longPanel*(cantidadPaneles%cantidadLargo));

        panel.setBounds(new Rectangle(posX, posY, longPanel+100, altoPanel));
        altoPanelContenedor = altoPanelContenedor + (altoPanel*(cantidadFila+1));
        pnlTipoTarjeta.setBounds(new Rectangle(10, 5, 470, altoPanelContenedor));
        if(VariablesFidelizacion.vNumCuponCampanasR == 1){
            pnlTipoTarjeta.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                                                                                                      new Color(43, 141,
                                                                                                                39)),
                                                                      "Seleccione Forma de Pago de Cupón: " + getNumCupon(),
                                                                      TitledBorder.DEFAULT_JUSTIFICATION,
                                                                      TitledBorder.DEFAULT_POSITION,
                                                                      new Font("SansSerif", 1, 12),
                                                                      new Color(43, 141, 39)));
        }else{
            pnlTipoTarjeta.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                                                                                                      new Color(43, 141,
                                                                                                                39)),
                                                                      "Seleccione Forma de Pago",
                                                                      TitledBorder.DEFAULT_JUSTIFICATION,
                                                                      TitledBorder.DEFAULT_POSITION,
                                                                      new Font("SansSerif", 1, 12),
                                                                      new Color(43, 141, 39)));
        }
        pnlTipoTarjeta.add(panel, null);
        lstPanelTarjetas.add(panel);
        this.setSize(new Dimension(516, (133+altoPanelContenedor+270)));
        txtNombreTarjeta.setBounds(new Rectangle(220, altoPanelContenedor+10, 165, 20));
        lblNroTarjeta.setBounds(new Rectangle(105, altoPanelContenedor+10, 110, 20));
        pnlInfo.setBounds(new Rectangle(10, 25, 490, altoPanelContenedor+40));
        lblF11.setBounds(new Rectangle(185, altoPanelContenedor+75+260, 120, 25));
        lblSinFormaPago.setBounds(new Rectangle(10, altoPanelContenedor+95+260, 360, 25));
        pnlFondo.setBounds(new Rectangle(0, 0, 515, (75+altoPanelContenedor+300)));
        repaint();
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        for(int i=0; i<lstTarjetaLogo.size(); i++){
            Map mapTarjeta = (Map)lstTarjetaLogo.get(i);
            JPanelImagenTransparente pan = new JPanelImagenTransparente(mapTarjeta);
            agregarPanelTarjeta(pan);
        }
        lstPanelTarjetas.get(0).activaPanel(txtNombreTarjeta, this.codFormaPagoDlg);
        txtNombreTarjeta.requestFocus();
    }
    
    private void teclasDireccionales(KeyEvent e){
        int posicionActual = -1;
        int posicionNueva = -1;
        int longitud = lstPanelTarjetas.size();
        for(int i = 0; i < longitud; i++){
            if( lstPanelTarjetas.get(i).getActivo() == 1){
                posicionActual=i;
                break;
            }
        }
        if(posicionActual!=-1){
            switch(e.getKeyCode()){
                case KeyEvent.VK_RIGHT :
                    posicionNueva = posicionActual + 1;
                    if((posicionNueva+1)>longitud){
                        posicionNueva = 0;
                    }
                    break;
                case KeyEvent.VK_LEFT :
                    posicionNueva = posicionActual - 1;
                    if(posicionNueva < 0){
                        posicionNueva = longitud - 1;
                    }
                    break;
                case KeyEvent.VK_DOWN :
                    posicionNueva = posicionActual + cantidadLargo;
                    if((posicionNueva+1)>longitud){
                        posicionNueva = posicionActual % cantidadLargo;
                    }
                    break;
                case KeyEvent.VK_UP :
                    posicionNueva = posicionActual - cantidadLargo;
                    if(posicionNueva < 0){
                        posicionNueva = (((longitud/cantidadLargo)*cantidadLargo) + posicionActual);
                        if((posicionNueva + 1) >longitud){
                            posicionNueva = posicionActual;
                        }
                    }
                    break;
            }
            
            if(posicionNueva!=-1){
                lstPanelTarjetas.get(posicionActual).desactivaPanel();
                lstPanelTarjetas.get(posicionNueva).activaPanel(txtNombreTarjeta, lstPanelTarjetas.get(posicionNueva).getCodFormaPago());
                this.setCodFormaPagoDlg(lstPanelTarjetas.get(posicionNueva).getCodFormaPago());
                repaint();
            }
        }
    }
    
    private void txtNombreTarjeta_keyTyped(KeyEvent e) {

    }
    
    private void txtNombreTarjeta_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblCampanas, txtNombreTarjeta, 1);
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER :
                VariablesFidelizacion.vCodFormaPagoCampanasR = this.getCodFormaPagoDlg();
                VariablesFidelizacion.vNumCuponCampanasR = 0;
                VariablesFidelizacion.vCodCuponIngresadoCampanasR = "";
                cerrarVentana(true);
                break;
            case KeyEvent.VK_RIGHT :
            case KeyEvent.VK_LEFT :
            case KeyEvent.VK_DOWN :
            case KeyEvent.VK_UP :
                teclasDireccionales(e);
                break;
            case KeyEvent.VK_ESCAPE:
                VariablesFidelizacion.vCodFormaPagoCampanasR = "";
                VariablesFidelizacion.vNumCuponCampanasR = 0;
                VariablesFidelizacion.vCodCuponIngresadoCampanasR = "";
                cerrarVentana(false);
                break;
        }
    }
    
    private void lblNroTarjeta_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtNombreTarjeta);
    }
    
    public JPanel getPanelSeleccionado(){
        JPanelImagenTransparente panel = null;
        for(int i=0;i<lstPanelTarjetas.size();i++){
            if(lstPanelTarjetas.get(i).getActivo()==1){
                panel  = lstPanelTarjetas.get(i);
                break;
            }
        }
        return panel;
    }

    public void setIndMuestraMensaje(String indMuestraMensaje) {
        this.indMuestraMensaje = indMuestraMensaje;
    }

    public String getIndMuestraMensaje() {
        return indMuestraMensaje;
    }

    public void setCodFormaPagoDlg(String codFormaPagoDlg) {
        this.codFormaPagoDlg = codFormaPagoDlg;
    }

    public String getCodFormaPagoDlg() {
        return codFormaPagoDlg;
    }

    public void setNumCupon(String numCupon) {
        this.numCupon = numCupon;
    }

    public String getNumCupon() {
        return numCupon;
    }

    public class JPanelImagenTransparente extends JPanel{
        private JLabel lblContenido = new JLabel();
        private JPanel pnlTransparencia = new JPanel();
        private String nombrePanel;
        private int activo=0;
        private int indiceTransparencia=50; 
        private String codFormaPago;
        private String rutaImagenAyuda;
        private String rutaImagenes = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/";
        
        public JPanelImagenTransparente(Map mapTarjeta){
            /*if(mapTarjeta.get("COD_FORMA_PAGO").equals("E0000")){
                lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource(rutaEfectivo+((String)mapTarjeta.get("IMG_LOGO")))));
            }else if(mapTarjeta.get("COD_FORMA_PAGO").equals("T0000")){
                lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource(rutaEfectivo+((String)mapTarjeta.get("IMG_LOGO")))));
            }else{
                try{
                    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource(rutaImagenes+((String)mapTarjeta.get("IMG_LOGO")))));
                }catch(Exception e){
                    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource(rutaEfectivo+((String)mapTarjeta.get("IMG_LOGO")))));
                }
            }*/
            
            try{
                String IMG_LOGO = (String)mapTarjeta.get("IMG_LOGO");
                String IMG_AYUDA = (String)mapTarjeta.get("IMG_AYUDA");
                String NOM_TARJETA = (String)mapTarjeta.get("NOM_TARJETA");
                String COD_FORMA_PAGO = (String)mapTarjeta.get("COD_FORMA_PAGO");
                log.info("\n--- DATOS TARJETA ---");
                log.info("\n"+IMG_LOGO+"\n"+IMG_AYUDA+"\n"+NOM_TARJETA+"\n"+COD_FORMA_PAGO);
                log.info("\n---------------------");
                
                lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource(rutaImagenes+((String)mapTarjeta.get("IMG_LOGO")))));
            }catch(Exception e){
                rutaImagenes = "/main/java/mifarma/ptoventa/imagenes/formaPagoCampanaR/";
                lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource(rutaImagenes+((String)mapTarjeta.get("IMG_LOGO")))));
            }
            
            this.nombrePanel = (String)mapTarjeta.get("NOM_TARJETA");
            this.codFormaPago = (String)mapTarjeta.get("COD_FORMA_PAGO");
            //setCodFormaPagoDlg(this.codFormaPago);
            this.rutaImagenAyuda = rutaImagenes+((String)mapTarjeta.get("IMG_AYUDA"));
            pnlTransparencia.setBounds(0,0,70,80);
            lblContenido.setBounds(0,0,70,80);
            
            pnlTransparencia.setBackground(new Color(0,0,0,0));
            setSize(70, 80);
            setBackground(new Color(0,0,0,0));
            setLayout(null);
            
            add(pnlTransparencia, "b");
            add(lblContenido, "a");
        }
        
        public JPanelImagenTransparente(int posicionLogo){
            switch(posicionLogo){
                case 0 :    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formaPagoCampanaR/efectivo.png")));
                            this.nombrePanel = "EFECTIVO";
                            this.codFormaPago = "E0000";
                            this.rutaImagenAyuda = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/efectivo.png";
                            break;
                case 1 :    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formaPagoCampanaR/visa.jpg")));
                            this.nombrePanel = "VISA";
                            this.codFormaPago = "00003";
                            this.rutaImagenAyuda = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/visa_voucher.png";
                            break;
                case 2 :    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formaPagoCampanaR/mastercard.png")));
                            this.nombrePanel = "MASTERCARD";
                            this.codFormaPago = "00006";
                            this.rutaImagenAyuda = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/mastercard_voucher.png";
                            break;
                case 3 :    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formaPagoCampanaR/amex.png")));
                            this.nombrePanel = "AMERICAN EXPRESS";
                            this.codFormaPago = "00017";
                            this.rutaImagenAyuda = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/amex_voucher.png";
                            break;
                case 4 :    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formaPagoCampanaR/diners-club.png")));
                            this.nombrePanel = "DINERS CLUB";
                            this.codFormaPago = "00009";
                            this.rutaImagenAyuda = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/diners-club_voucher.png";
                            break;
                case 5 :    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formaPagoCampanaR/banco_ripley.png")));
                            this.nombrePanel = "TARJETA RIPLEY";
                            this.codFormaPago = ConstantsPtoVenta.FORPAG_RIPLEY_POS;
                            this.rutaImagenAyuda = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/banco_ripley.png";
                            break;
                case 6 :    lblContenido = new JLabel(new ImageIcon(FrmEconoFar.class.getResource("/mifarma/ptoventa/imagenes/formaPagoCampanaR/otras_tarjetas.png")));
                            this.nombrePanel = "OTRAS TARJETAS";
                            this.codFormaPago = "T0000";
                            this.rutaImagenAyuda = "/mifarma/ptoventa/imagenes/formaPagoCampanaR/otras_tarjetas_2.png";
                            break;
            }
            //setCodFormaPagoDlg(this.codFormaPago);
            
            pnlTransparencia.setBounds(0,0,70,80);
            lblContenido.setBounds(0,0,70,80);
            
            pnlTransparencia.setBackground(new Color(0,0,0,0));
            setSize(70, 80);
            setBackground(new Color(0,0,0,0));
            setLayout(null);
            
            add(pnlTransparencia, "b");
            add(lblContenido, "a");
        }
        
        public String getNombrePanel(){
            return nombrePanel;
        }
        
        public int getActivo(){
            return this.activo;
        }
        
        public String getCodFormaPago(){
            return this.codFormaPago;
        }
        
        public void desactivaPanel(){
            pnlTransparencia.setBackground(new Color(0,0,0,0));
            activo = 0;
        }
                
        public void activaPanel(JTextField campoTexto, String codFormaPago){
            pnlTransparencia.setBackground(new Color(0,0,0,indiceTransparencia));
            activo = 1;
            if(campoTexto!=null){
                campoTexto.setText(nombrePanel);
            }
            listarCampanas(codFormaPago, false);
        }
        
        public String getRutaImagenAyuda(){
            return this.rutaImagenAyuda;
        }
    }
    
    public void setLstTarjetaLogo(List lstTarjetaLogo){
        this.lstTarjetaLogo = lstTarjetaLogo;
    }
    
    private void scrListaCampanas_keyPressed(KeyEvent e) {
        
    }
    
    private void scrListaCampanas_focusGained(FocusEvent e) {
        
    }
    
    private void tblCampanas_keyPressed(KeyEvent e) {
        
    }
    
    private void listarCampanas(String codFormaPago, boolean indAbrePantalla) {
        //GENERICAS
        if(!this.tipoCampanaR.equals("P")){
            UtilityVentas.listaCampanasGenericasFidelizacion(tableModelListaCampanas, 
                                                             VariablesFidelizacion.vDniCliente.trim(), 
                                                             codFormaPago,
                                                             this.tipoCampanaR,
                                                             VariablesVentas.vCod_Prod.trim());
        }

        //CUPON
        if(VariablesFidelizacion.vNumCuponCampanasR == 1 && this.tipoCampanaR.equals("P")){
            UtilityVentas.listaCampanasGenericasFidelizacion(tableModelListaCampanas, 
                                                             VariablesFidelizacion.vDniCliente.trim(), 
                                                             codFormaPago,
                                                             "G",
                                                             VariablesVentas.vCod_Prod.trim());
            
            UtilityVentas.listaCampanasPorCuponFidelizacion(pListCupones,
                                                            VariablesFidelizacion.vDniCliente.trim(), 
                                                            codFormaPago,
                                                            this.tipoCampanaR,
                                                            "");
            if(pListCupones.size() > 0){
                tableModelListaCampanas.data.add(pListCupones.get(0));
            }
        }
        
        //PRODUCTO
        if(VariablesFidelizacion.vNumCuponCampanasR != 1 && this.tipoCampanaR.equals("P")){
            UtilityVentas.listaCampanasGenericasFidelizacion(tableModelListaCampanas, 
                                                             VariablesFidelizacion.vDniCliente.trim(), 
                                                             codFormaPago,
                                                             this.tipoCampanaR,
                                                             VariablesVentas.vCod_Prod.trim());
        }
        
        if(indAbrePantalla){
            if(tableModelListaCampanas.data.size() > 0){
                if(tableModelListaCampanas.data.size()==2){
                    //this.setSize(new Dimension(767, 258));
                }else{
                    //this.setSize(new Dimension(762, 582));
                }
                
                FarmaVariables.vAceptar = true;
                this.repaint();
            }else{
                FarmaVariables.vAceptar = false;
                cerrarVentana(false);
            }
        }else{
            if(tableModelListaCampanas.data.size() <= 0){
                panelSinPromociones();
            }else {
                panelConPromociones();
            }
            
            this.repaint();
        }
        
        setJTable(tblCampanas);
    }
    
    private void mostrarDetalleCampana() {
        if (tblCampanas.getRowCount() > 0) {
            txtDescCampana.setLineWrap(true);
            txtDescCampana.setWrapStyleWord(true);
            int vFila = tblCampanas.getSelectedRow();
            indMuestraMensaje = FarmaUtility.getValueFieldArrayList(tableModelListaCampanas.data, tblCampanas.getSelectedRow(), 3);
            
            if (indMuestraMensaje.equals("S")) {
                txtDescCampana.setText(tblCampanas.getValueAt(vFila, COL_MENSAJE).toString());
            } else {
                txtDescCampana.setText("");
            }
            
            FarmaUtility.moveFocus(txtNombreTarjeta);
        }else{
            txtDescCampana.setText("Sin promoción");
        }
    }
    
    private void txtNombreTarjeta_keyReleased(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblCampanas, null, 1);
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
            mostrarDetalleCampana();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mostrarDetalleCampana();
        }
    }
    
    public void panelSinPromociones(){
        txtDescCampana.setText("Sin promoción");
        
        scrListaCampanas.setVisible(false);
        scrMensajeCampana.setBounds(new Rectangle(5, 20, 485, 130));
        pnlCampanas.setBounds(new Rectangle(10, 180, 495, 155));
        lblF11.setBounds(new Rectangle(185, (10+75+260), 120, 25));
        lblSinFormaPago.setBounds(new Rectangle(10, (10+95+260), 360, 25));
        pnlFondo.setBounds(new Rectangle(0, 0, 515, (75+10+300)));
        this.setSize(new Dimension(516, (133+10+270)));
    }
    
    public void panelConPromociones(){
        scrListaCampanas.setVisible(true);                
        scrMensajeCampana.setBounds(new Rectangle(5, 120, 485, 130));
        pnlCampanas.setBounds(new Rectangle(10, 180, 495, 255));
        lblF11.setBounds(new Rectangle(185, (110+75+260), 120, 25));
        lblSinFormaPago.setBounds(new Rectangle(10, (110+95+260), 360, 25));
        pnlFondo.setBounds(new Rectangle(0, 0, 515, (75+110+300)));
        this.setSize(new Dimension(516, (133+110+270)));
    }
    
    private void setJTable(JTable pJTable) {
        if (pJTable.getRowCount() > 0) {
            FarmaGridUtils.showCell(pJTable, 0, 0);
            mostrarDetalleCampana();
        }
        
        FarmaUtility.moveFocus(txtNombreTarjeta);
    }
    
    
}