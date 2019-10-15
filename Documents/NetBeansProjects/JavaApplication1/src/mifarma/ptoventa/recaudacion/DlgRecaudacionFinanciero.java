package mifarma.ptoventa.recaudacion;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.ConstantsCaja;
import mifarma.ptoventa.caja.reference.UtilityLectorTarjeta;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.BFPControlTblDeposito;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.BFPControlTblPrestamo;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.BFPControlTblTarjeta;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.BFPModelTblDeposito;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.BFPModelTblPrestamo;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.BFPModelTblTarjeta;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.BFPRenderTblTarjeta;
import mifarma.ptoventa.recaudacion.RecaudacionBFP.RegistroOperacion;
import mifarma.ptoventa.recaudacion.UtilFinanciero.Hora;
import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.recaudacion.reference.UtilityRecaudacion;
import mifarma.ptoventa.recaudacion.reference.VariablesRecaudacion;
import mifarma.ptoventa.reference.DBPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgRecaudacionFinanciero extends JDialog {
    static final Logger log = LoggerFactory.getLogger(DlgRecaudacionFinanciero.class);

    Frame myParentFrame;
    //private String tipoOperacion=VariablesRecaudacion.PAGO_TARJ_BFP;

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private FarmaTableModel tblModelRegistros;
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private JButton btnDocIdentidad = new JButton();
    private JButton btnMontoPagar = new JButton();
    private JTextFieldSanSerif txtDocIdentidad = new JTextFieldSanSerif();
    private JPanel jPanel3 = new JPanel();
    private JLabel lblUsuario = new JLabel();
    private JLabel txtUsuario = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel txtFecha = new JLabel();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();


    private JLabel lbl01 = new JLabel();
    private FacadeRecaudacion facadeRecaudacion = new FacadeRecaudacion();
    UtilityPtoVenta utilityPtoVenta = new UtilityPtoVenta();
    UtilityRecaudacion utilityRecaudacion = new UtilityRecaudacion();
    UtilityLectorTarjeta utilityLectorTarjeta = new UtilityLectorTarjeta();
    private JScrollPane srcDatos = new JScrollPane();
    private JTable tblTarjetas = new JTable();

    private JPanelTitle pnlListaTarjetas = new JPanelTitle();
    private JButtonLabel btnListaTarjeta = new JButtonLabel();
    private JLabelOrange lblNombre = new JLabelOrange();
    private JLabelFunction lblF2 = new JLabelFunction();
    private JLabel lblTitulo = new JLabel();
    private double lim_max_rcd_Soles = 0;
    private double lim_max_rcd_Dolares = 0;
    private boolean realizarValidacion = false;
    private JLabel lblTituloTCambio = new JLabel();
    private JLabelOrange lblTipoCambio = new JLabelOrange();
    private JTextField txtMontoPagar = new JTextField();
    private JLabel lblHora = new JLabel();
    private JLabel txtHora = new Hora();
    private ButtonGroup grupoRadio = new ButtonGroup();
    private JRadioButton rdoPagoTarjeta = new JRadioButton();
    private JRadioButton rdoPagoPrestamo = new JRadioButton();
    private JRadioButton rdoDepositoCta = new JRadioButton();
    private JPanel pnlOpciones = new JPanel();
    private JLabel jLabel1 = new JLabel();
    
    private boolean opTarjeta=true;
    private boolean opPrestamo=false;
    private boolean opDeposito=false;
    private String nroVersionBFP;
    private String titulo=VariablesRecaudacion.RAZON_SOCIAL_BFP;
    /*
    private JScrollPane pnlNewTable = new JScrollPane();
    private JTable tblBFP = new JTable();
    private ModelTable modelBFP=new ModelTable();
    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgRecaudacionFinanciero() {
        this(null, "", false);
    }

    public DlgRecaudacionFinanciero(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            utilityRecaudacion.initMensajesVentana(this, txtFecha, txtUsuario, ConstantsRecaudacion.TIPO_REC_FINANCIERO);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(560, 468));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Recaudación "+titulo);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jPanel1.setLayout(null);
        jPanel1.setForeground(Color.white);
        jPanel1.setBackground(Color.white);
        btnDocIdentidad.setText("DNI: ");
        btnDocIdentidad.setBackground(Color.white);
        btnDocIdentidad.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDocIdentidad.setBorderPainted(false);
        btnDocIdentidad.setContentAreaFilled(false);
        btnDocIdentidad.setDefaultCapable(false);
        btnDocIdentidad.setFocusPainted(false);
        btnDocIdentidad.setFont(new Font("SansSerif", 1, 11));
        btnDocIdentidad.setForeground(new Color(255, 130, 14));
        btnDocIdentidad.setHorizontalAlignment(SwingConstants.LEFT);
        btnDocIdentidad.setMnemonic('D');
        btnDocIdentidad.setRequestFocusEnabled(false);
        btnDocIdentidad.setBounds(new Rectangle(15, 20, 50, 20));
        btnDocIdentidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDocIdentidad_actionPerformed(e);
            }
        });
        btnMontoPagar.setText("Monto a Pagar :");
        btnMontoPagar.setBackground(Color.white);
        btnMontoPagar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnMontoPagar.setBorderPainted(false);
        btnMontoPagar.setContentAreaFilled(false);
        btnMontoPagar.setDefaultCapable(false);
        btnMontoPagar.setFocusPainted(false);
        btnMontoPagar.setFont(new Font("SansSerif", 1, 11));
        btnMontoPagar.setForeground(new Color(255, 130, 14));
        btnMontoPagar.setHorizontalAlignment(SwingConstants.LEFT);
        btnMontoPagar.setMnemonic('P');
        btnMontoPagar.setRequestFocusEnabled(false);
        btnMontoPagar.setBounds(new Rectangle(15, 390, 90, 20));
        btnMontoPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnMontoPagar_actionPerformed(e);
            }
        });

        txtDocIdentidad.setBounds(new Rectangle(115, 20, 80, 20));
        txtDocIdentidad.setDocument(new FarmaLengthText(10));
        txtDocIdentidad.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtDocIdentidad_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtDocIdentidad_keyTyped(e);
            }
        });
        jPanel3.setBounds(new Rectangle(10, 5, 530, 70));
        jPanel3.setBackground(new Color(43, 141, 39));
        jPanel3.setForeground(new Color(0, 132, 66));
        jPanel3.setLayout(null);
        lblUsuario.setText("Usuario :");
        lblUsuario.setSize(new Dimension(30, 20));
        lblUsuario.setFont(new Font("Dialog", 1, 11));
        lblUsuario.setBounds(new Rectangle(10, 40, 55, 20));
        lblUsuario.setForeground(Color.white);
        txtUsuario.setBounds(new Rectangle(70, 40, 110, 20));
        txtUsuario.setFont(new Font("Dialog", 1, 11));
        txtUsuario.setForeground(Color.white);
        lblFecha.setText("Fecha :");
        lblFecha.setBounds(new Rectangle(185, 40, 75, 20));
        lblFecha.setForeground(Color.white);
        lblFecha.setFont(new Font("Dialog", 1, 11));
        lblFecha.setSize(new Dimension(75, 20));
        txtFecha.setForeground(Color.white);
        txtFecha.setFont(new Font("Dialog", 1, 11));
        txtFecha.setBounds(new Rectangle(230, 40, 115, 20));
        lblF11.setText("[ F11 ] Aceptar");
        lblF11.setBounds(new Rectangle(340, 390, 90, 20));
        lblF2.setText("[ F2 ] Buscar");
        lblF2.setBounds(new Rectangle(25, 390, 90, 20));
        lblF2.setVisible(false);

        lblTitulo.setText("RECAUDACIÓN "+titulo.toUpperCase().trim());
        lblTitulo.setBounds(new Rectangle(0, 5, 525, 20));
        //lblTitulo.setSize(new Dimension(30, 20));
        lblTitulo.setFont(new Font("Dialog", 1, 14));
        lblTitulo.setForeground(Color.white);

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloTCambio.setText("Tipo de cambio referencial:");
        lblTituloTCambio.setBounds(new Rectangle(280, 20, 155, 20));
        lblTituloTCambio.setBackground(SystemColor.window);
        lblTituloTCambio.setForeground(new Color(255, 130, 14));
        lblTituloTCambio.setFont(new Font("SansSerif", 1, 11));

        lblTipoCambio.setBounds(new Rectangle(435, 20, 90, 20));
        lblTipoCambio.setText("");
        lblTipoCambio.setForeground(SystemColor.windowText);
        lblTipoCambio.setFont(new Font("SansSerif", 3, 11));

        lblTipoCambio.setHorizontalAlignment(SwingConstants.LEFT);
        txtMontoPagar.setBounds(new Rectangle(110, 390, 120, 20));
        txtMontoPagar.setFont(new Font("Tahoma", 1, 11));
        txtMontoPagar.setDisabledTextColor(new Color(231, 0, 0));
        txtMontoPagar.setForeground(Color.red);
        txtMontoPagar.setEditable(false);
        txtMontoPagar.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    txtMontoPagar_keyTyped(e);
                }

                public void keyPressed(KeyEvent e) {
                    txtMontoPagar_keyPressed(e);
                }
            });
        
        lblHora.setText("Hora:");
        lblHora.setBounds(new Rectangle(350, 40, 40, 20));
        lblHora.setForeground(Color.white);
        lblHora.setFont(new Font("Dialog", 1, 11));
        lblHora.setSize(new Dimension(75, 20));

        txtHora.setBounds(new Rectangle(390, 40, 115, 20));
        txtHora.setForeground(Color.white);
        txtHora.setFont(new Font("Dialog", 1, 11));

        grupoRadio.add(rdoPagoTarjeta);
        grupoRadio.add(rdoPagoPrestamo);
        grupoRadio.add(rdoDepositoCta);
        
        rdoPagoTarjeta.setText("Pago Tarjeta cr\u00e9dito");
        rdoPagoTarjeta.setBounds(new Rectangle(25, 15, 160, 20));
        rdoPagoTarjeta.setBackground(SystemColor.window);
        rdoPagoTarjeta.setFont(new Font("SansSerif", 3, 11));
        rdoPagoTarjeta.setBackground(SystemColor.window);
        rdoPagoTarjeta.setSelected(true);

        rdoPagoTarjeta.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    rdoPagoTarjeta_KeyPressed(e);
                }
            });
        rdoPagoPrestamo.setText("Pago Pr\u00e9stamo");
        rdoPagoPrestamo.setBounds(new Rectangle(195, 15, 130, 20));
        rdoPagoPrestamo.setForeground(SystemColor.windowText);
        rdoPagoPrestamo.setFont(new Font("SansSerif", 3, 11));
        rdoPagoPrestamo.setBackground(SystemColor.window);

        rdoPagoPrestamo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    rdoPagoPrestamo_KeyPressed(e);
                }
            });
        rdoDepositoCta.setText("Dep\u00f3sito a cuenta");
        rdoDepositoCta.setBounds(new Rectangle(345, 15, 130, 20));
        rdoDepositoCta.setForeground(SystemColor.windowText);
        rdoDepositoCta.setFont(new Font("SansSerif", 3, 11));
        rdoDepositoCta.setBackground(SystemColor.window);

        rdoDepositoCta.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    rdoDepositoCta_KeyPressed(e);
                }
            });
        pnlOpciones.setBounds(new Rectangle(10, 155, 530, 40));
        pnlOpciones.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlOpciones.setBackground(Color.white);
        pnlOpciones.setLayout(null);
        pnlOpciones.setForeground(Color.white);

        jLabel1.setText("[F2] Cambiar transaccion");
        jLabel1.setBounds(new Rectangle(5, 0, 160, 15));
        jLabel1.setBackground(SystemColor.window);
        jLabel1.setFont(new Font("SansSerif", 3, 12));
        jLabel1.setForeground(new Color(255, 130, 14));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(340, 220, 90, 20));
        lblEsc.setBounds(new Rectangle(450, 390, 90, 20));
        lbl01.setText("Nombre:");
        lbl01.setBounds(new Rectangle(15, 55, 80, 20));
        lbl01.setBackground(SystemColor.window);
        lbl01.setForeground(new Color(255, 130, 14));
        lbl01.setFont(new Font("SansSerif", 1, 11));
        srcDatos.setBounds(new Rectangle(10, 220, 530, 155));
        pnlListaTarjetas.setBounds(new Rectangle(10, 195, 530, 25));
        btnListaTarjeta.setText("Consulta de cuentas");
        btnListaTarjeta.setBounds(new Rectangle(5, 0, 165, 25));
        btnListaTarjeta.setMnemonic('C');
        btnListaTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnListaTarjeta_actionPerformed(e);
            }
        });
        lblNombre.setText("");
        lblNombre.setBounds(new Rectangle(115, 55, 270, 20));
        lblNombre.setForeground(SystemColor.windowText);
        lblNombre.setFont(new Font("SansSerif", 3, 11));

        jPanel2.setBounds(new Rectangle(10, 75, 530, 85));
        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        jPanel2.setBackground(Color.white);
        jPanel2.setLayout(null);
        jPanel2.setForeground(Color.white);
        jPanel3.add(txtHora, null);
        jPanel3.add(lblHora, null);
        jPanel3.add(lblTitulo, null);
        jPanel3.add(txtFecha, null);
        jPanel3.add(lblFecha, null);
        jPanel3.add(txtUsuario, null);
        jPanel3.add(lblUsuario, null);

        tblTarjetas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblTarjetas_KeyPressed(e);
            }
        });

        pnlOpciones.add(rdoDepositoCta, null);
        pnlOpciones.add(rdoPagoPrestamo, null);
        pnlOpciones.add(rdoPagoTarjeta, null);
        pnlOpciones.add(jLabel1, null);
        jPanel1.add(pnlOpciones, null);
        jPanel1.add(txtMontoPagar, null);
        pnlListaTarjetas.add(btnListaTarjeta, null);
        jPanel1.add(pnlListaTarjetas, null);
        srcDatos.getViewport().add(tblTarjetas, null);
        jPanel1.add(srcDatos, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jPanel3, null);
        jPanel1.add(lblF11, null);
        jPanel1.add(lblF2, null);
        jPanel1.add(lblEsc, null);
        jPanel1.add(btnMontoPagar, null);
        jPanel2.add(lblTipoCambio, null);
        /*
        pnlNewTable.setBounds(new Rectangle(15, 365, 520, 125));
        jPanel1.add(pnlNewTable, null);
        pnlNewTable.getViewport().add(tblBFP, null);
        /* */
        jPanel2.add(lblTituloTCambio, null);
        jPanel2.add(lblNombre, null);
        jPanel2.add(lbl01, null);
        jPanel2.add(btnDocIdentidad, null);
        jPanel2.add(txtDocIdentidad, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        initTable();
        try {
            ArrayList<ArrayList> param = UtilityRecaudacion.getCantIntentosTiempoEsperaBancoFinanciero();
            VariablesRecaudacion.vNroIntentos = Integer.parseInt(param.get(0).get(0).toString());
            VariablesRecaudacion.vCantSegEspere = Integer.parseInt(param.get(0).get(1).toString());
        } catch (Exception e) {
            VariablesRecaudacion.vNroIntentos = 3;
            VariablesRecaudacion.vCantSegEspere = 3;
            log.error("Error parametros: " + e);
        }
    }

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */
    

    private void initTable() {
        configuraNuevaTabla(new ArrayList<RegistroOperacion> ());
        if(opTarjeta){
            tblModelRegistros =
                    new FarmaTableModel(ConstantsRecaudacion.columnsListaBFP_Tarjeta, ConstantsRecaudacion.defaultValuesListaBFP_Tarjeta,
                                        0);            
        }else if(opPrestamo){
            tblModelRegistros =
                    new FarmaTableModel(ConstantsRecaudacion.columnsListaBFP_Prestamo, ConstantsRecaudacion.defaultValuesListaBFP_Prestamo,
                                        0);            
        }else if(opDeposito){
            tblModelRegistros =
                    new FarmaTableModel(ConstantsRecaudacion.columnsListaBFP_Deposito, ConstantsRecaudacion.defaultValuesListaBFP_Deposito,
                                        0);
        }
        /*
        FarmaUtility.initSimpleList(tblTarjetas, tblModelTarjetas, ConstantsRecaudacion.columnsListaConsultaBFP);
        */        
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        lblTituloTCambio.setText("");
        lblTipoCambio.setText("");
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtDocIdentidad);
        lim_max_rcd_Soles = utilityRecaudacion.recupera_limiteRecacudacionSoles();
        lim_max_rcd_Dolares = utilityRecaudacion.recupera_limiteRecacudacionDolares();
        realizarValidacion = utilityRecaudacion.recupera_indicadorRealiaValidacion();
        VariablesRecaudacion.vDNI_Usuario = recuperaDNI_Usuario();
        
        try {
            nroVersionBFP = DBPtoVenta.obtenerVersion_Carga_BFP().trim();
            if(nroVersionBFP.equalsIgnoreCase("V1.0")){
                pnlOpciones.setVisible(false);
            }
        } catch (SQLException f) {
            nroVersionBFP="V1.0";
            pnlOpciones.setVisible(false);
        }
        log.info("=============> '"+nroVersionBFP+"'");
        
        txtHora = new Hora();
        txtHora.setFont(new Font("Trebuchet MS",Font.BOLD,20));
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void btnMontoPagar_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtMontoPagar);
    }
    int fila_aux=0;
    int colum_aux=0;
    String monto_aux="";
    private void txtMontoPagar_keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            log.info("<<<<======= ROW: "+tblModelRegistros.getRowCount()+" - COLUMN: "+tblModelRegistros.getColumnCount()+"=======>>>>");
            for(int i=0;i<tblModelRegistros.getRowCount();i++){
                System.out.println("----> ROW: "+i);
                for(int j=0;j<tblModelRegistros.getColumnCount();j++){
                    System.out.println("----> COLUMN: "+j+" - "+tblModelRegistros.getValueAt(i,j));
                }
            }
            log.info("----------------------------------");
            try{
                String otroMonto=txtMontoPagar.getText().trim();
                txtMontoPagar.setEditable(false);
                if(opTarjeta){
                    String valorTxt="";
                    String valorTbl="";
                    String tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila_aux, 9).trim();
                    if(!otroMonto.equalsIgnoreCase("")){
                        if(Double.parseDouble(tipCambio)!=0d){
                            valorTxt=Double.parseDouble(otroMonto)*Double.parseDouble(tipCambio)+"";
                            valorTbl=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_DOLARES, otroMonto);
                            valorTxt=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,Double.parseDouble(valorTxt)+"");
                        }else{
                            valorTxt=otroMonto;
                            valorTbl=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, otroMonto);
                            valorTxt=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,Double.parseDouble(valorTxt)+"");
                        }
                        txtMontoPagar.setText(valorTxt);
                        tblTarjetas.setValueAt(valorTbl, fila_aux, colum_aux);
                        tblModelRegistros.setValueAt(valorTbl,fila_aux, 10);
                    }else{
                        txtMontoPagar.setText("");
                        tblTarjetas.setValueAt("", fila_aux, colum_aux);
                        tblModelRegistros.setValueAt("",fila_aux, 10);
                    }
                    FarmaUtility.moveFocusJTable(tblTarjetas);
                    tblTarjetas.setRowSelectionInterval(fila_aux,fila_aux);
                    tblTarjetas.setColumnSelectionInterval(colum_aux,colum_aux);
                }else if(opPrestamo){
                    String tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila_aux, 9).trim();
                    String valorTxt="";
                    String valorTbl="";
                    if(!otroMonto.equalsIgnoreCase("")){
                        if(Double.parseDouble(tipCambio)!=0d){
                            valorTxt=Double.parseDouble(otroMonto)*Double.parseDouble(tipCambio)+"";
                            valorTbl=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_DOLARES, otroMonto);
                            valorTxt=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,Double.parseDouble(valorTxt)+"");
                        }else{
                            valorTxt=otroMonto;
                            valorTbl=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, otroMonto);
                            valorTxt=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,Double.parseDouble(valorTxt)+"");
                        }
                        txtMontoPagar.setText(valorTxt);
                        tblTarjetas.setValueAt(valorTbl, fila_aux, 5);
                        tblModelRegistros.setValueAt(valorTbl,fila_aux, 10);
                    }else{
                        txtMontoPagar.setText("");
                        tblTarjetas.setValueAt("", fila_aux, colum_aux);
                        tblModelRegistros.setValueAt("",fila_aux, 10);
                    }
                    FarmaUtility.moveFocusJTable(tblTarjetas);
                    tblTarjetas.setRowSelectionInterval(fila_aux,fila_aux);
                    tblTarjetas.setColumnSelectionInterval(5,5);
                }else if(opDeposito){
                    String tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila_aux, 6).trim();
                    String valorTxt="";
                    String valorTbl="";
                    if(!otroMonto.equalsIgnoreCase("")){
                        if(Double.parseDouble(tipCambio)!=0d){
                            valorTxt=Double.parseDouble(otroMonto)*Double.parseDouble(tipCambio)+"";
                            valorTbl=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_DOLARES, otroMonto);
                            valorTxt=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,Double.parseDouble(valorTxt)+"");
                        }else{
                            valorTxt=otroMonto;
                            valorTbl=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, otroMonto);
                            valorTxt=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,Double.parseDouble(valorTxt)+"");
                        }
                        txtMontoPagar.setText(valorTxt);
                        tblTarjetas.setValueAt(valorTbl, fila_aux, 3);
                        tblModelRegistros.setValueAt(valorTbl,fila_aux, 7);
                    }else{
                        txtMontoPagar.setText("");
                        tblTarjetas.setValueAt("", fila_aux, 3);
                        tblModelRegistros.setValueAt("",fila_aux, 7);
                    }
                    FarmaUtility.moveFocusJTable(tblTarjetas);
                    tblTarjetas.setRowSelectionInterval(fila_aux,fila_aux);
                    tblTarjetas.setColumnSelectionInterval(3,3);
                }
            }catch(Exception f){
                txtMontoPagar.setText("");
                muestraMensaje("Ingrese un valor valido!","E",null,txtMontoPagar);
                txtMontoPagar.setEditable(true);
            }
        }
    }
    
    private void txtMontoPagar_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtMontoPagar, e);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */
    
    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            if(opTarjeta)
                registarPagoTarjeta();
            if(opPrestamo)
                registarPagoPrestamo();
            if(opDeposito)
                registarPagoDeposito();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }else if(e.getKeyCode() == KeyEvent.VK_F2) {
            if(nroVersionBFP.equalsIgnoreCase("V2.0")){
                boolean chkTC=rdoPagoTarjeta.isSelected();
                boolean chkPP=rdoPagoPrestamo.isSelected();
                boolean chkDC=rdoDepositoCta.isSelected();
                if(chkTC){
                    rdoPagoTarjeta.setSelected(false);
                    rdoPagoPrestamo.setSelected(true);
                    rdoDepositoCta.setSelected(false);
                    opTarjeta=false;
                    opPrestamo=true;
                    opDeposito=false;
                }else if(chkPP){
                    rdoPagoTarjeta.setSelected(false);
                    rdoPagoPrestamo.setSelected(false);
                    rdoDepositoCta.setSelected(true);
                    opTarjeta=false;
                    opPrestamo=false;
                    opDeposito=true;
                }else if(chkDC){
                    rdoPagoTarjeta.setSelected(true);
                    rdoPagoPrestamo.setSelected(false);
                    rdoDepositoCta.setSelected(false);
                    opTarjeta=true;
                    opPrestamo=false;
                    opDeposito=false;
                }
                initTable();
                FarmaUtility.moveFocus(txtDocIdentidad);
                txtDocIdentidad.setText("");
                lblNombre.setText("");
            }else{
                e.consume();
            }
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        VariablesRecaudacion.vNombre_Cliente_BFP = "";
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */
    /*
    private void buscarSolicitudesPago() {
        if(validarBusqueda()) {
            ArrayList<ArrayList<String>> tmpLista = new ArrayList<>();
            //tmpLista = facadeRecaudacion.solicitaPagos_BancoFinanciero("");
            llenarTabla(tblModelTarjetas, tmpLista);
        }

        FarmaUtility.showMessage(this, "Implementar búsqueda de tarjetas", null);
    }*/

    private boolean validarBusqueda() {
        boolean resultado = true;
        if (txtDocIdentidad.getText().trim().isEmpty()) {
            FarmaUtility.showMessage(this, "Ingrese DNI para realizar una búsqueda", null);
            resultado = false;
        } else if (txtDocIdentidad.getText().trim().length() < 8) {
            FarmaUtility.showMessage(this, "Ingrese DNI válido", null);
            resultado = false;
        }
        return resultado;
    }

    public void llenarTabla(FarmaTableModel tableModel, ArrayList<ArrayList<String>> tmpArrayLista) {
        ArrayList<RegistroOperacion> listaTarjeta = new ArrayList();

        if (tmpArrayLista.size() > 0) {
            if(opTarjeta){
                for (int i = 0; i < tmpArrayLista.size(); i++) {
                    RegistroOperacion tarjeta = new RegistroOperacion();
                    
                    String mMin =
                        formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, tmpArrayLista.get(i).get(2).toString().trim());
                    String mMes =
                        formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, tmpArrayLista.get(i).get(3).toString().trim());
                    String mTotal =
                        formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, tmpArrayLista.get(i).get(4).toString().trim());
                
                    String simbolo = tmpArrayLista.get(i).get(5).toString().trim();
                    String mMinO = formatoMonedoPago(simbolo, tmpArrayLista.get(i).get(6).toString().trim());
                    String mMesO = formatoMonedoPago(simbolo, tmpArrayLista.get(i).get(7).toString().trim());
                    String mTotalO = formatoMonedoPago(simbolo, tmpArrayLista.get(i).get(8).toString().trim());
                
                    tarjeta.setNroTarjeta(tmpArrayLista.get(i).get(0).toString().trim());
                    tarjeta.setMontoMinTarj(mMinO);
                    tarjeta.setMontoMesTarj(mMesO);
                    tarjeta.setMontoTotalTarj(mTotalO);
                    tarjeta.setOtroMonto("");
                    listaTarjeta.add(tarjeta);
                
                    tmpArrayLista.get(i).set(2, mMin);
                    tmpArrayLista.get(i).set(3, mMes);
                    tmpArrayLista.get(i).set(4, mTotal);
                    tmpArrayLista.get(i).set(6, mMinO);
                    tmpArrayLista.get(i).set(7, mMesO);
                    tmpArrayLista.get(i).set(8, mTotalO);
                    tmpArrayLista.get(i).add("");
                    tableModel.insertRow(tmpArrayLista.get(i));
                }
            }else if (opPrestamo){
                for (int i = 0; i < tmpArrayLista.size(); i++) {
                    RegistroOperacion prestamo = new RegistroOperacion();
                    
                    String tipPrestamo =tmpArrayLista.get(i).get(0).toString().trim();
                    String desctipPres =tmpArrayLista.get(i).get(1).toString().trim();
                    String nroPrestamo =tmpArrayLista.get(i).get(2).toString().trim();
                    String fechPagoPres =tmpArrayLista.get(i).get(3).toString().trim();
                    String descPrestamo =tmpArrayLista.get(i).get(4).toString().trim();
                    String monedaPago =tmpArrayLista.get(i).get(5).toString().trim();
                    String totalPago =formatoMonedoPago(monedaPago, tmpArrayLista.get(i).get(6).toString().trim());
                    String monedaOrige =tmpArrayLista.get(i).get(7).toString().trim();
                    String totalOrigen =formatoMonedoPago(monedaOrige, tmpArrayLista.get(i).get(8).toString().trim());
                    String tipoCambio =tmpArrayLista.get(i).get(9).toString().trim();
                    
                    prestamo.setTipoPrestamo(desctipPres);
                    prestamo.setNroPrestamo(nroPrestamo.substring(0,12));
                    prestamo.setFechaPagoPrestamo(fechPagoPres);
                    prestamo.setDesc_Prestamo(descPrestamo);
                    prestamo.setMontoPagoPres(totalOrigen);
                    prestamo.setOtroMonto("");
                    listaTarjeta.add(prestamo);
                    
                    tmpArrayLista.get(i).set(6, totalPago);
                    tmpArrayLista.get(i).set(8, totalOrigen);
                    tmpArrayLista.get(i).add("");
                    tableModel.insertRow(tmpArrayLista.get(i));
                }
            }else if (opDeposito){
                for (int i = 0; i < tmpArrayLista.size(); i++) {
                    RegistroOperacion deposito = new RegistroOperacion();
                    
                    String idCtaDeposito =tmpArrayLista.get(i).get(0).toString().trim();
                    String descCtaDeposito  =tmpArrayLista.get(i).get(1).toString().trim();
                    String monedaCta =tmpArrayLista.get(i).get(2).toString().trim().trim();
                    String saldoCta =tmpArrayLista.get(i).get(3).toString().trim();
                    String monedaCtaOrig =tmpArrayLista.get(i).get(4).toString().trim().trim();
                    String saldoOrig = tmpArrayLista.get(i).get(5).toString().trim();
                    
                    if(monedaCta.equalsIgnoreCase("PEN")||monedaCta.equalsIgnoreCase("S/.")
                       || monedaCta.equalsIgnoreCase(ConstantsRecaudacion.SIMBOLO_SOLES)){
                        saldoCta=
                            formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, saldoCta);
                    }else{
                        saldoCta=
                            formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_DOLARES, saldoCta);
                    }
                    
                    if(monedaCtaOrig.equalsIgnoreCase("PEN")||monedaCtaOrig.equalsIgnoreCase("S/.")
                       || monedaCtaOrig.equalsIgnoreCase(ConstantsRecaudacion.SIMBOLO_SOLES)){
                        saldoOrig=
                            formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES, saldoOrig);
                    }else{
                        saldoOrig=
                            formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_DOLARES, saldoOrig);
                    }
                    
                    deposito.setIdCtaDeposito(idCtaDeposito);
                    deposito.setDesc_CtaDeposito(descCtaDeposito);
                    deposito.setMonedaCtaDeposito(monedaCtaOrig);
                    deposito.setSaldoCtaDeposito(saldoOrig);
                    deposito.setOtroMonto("");
                    listaTarjeta.add(deposito);
                    
                    tmpArrayLista.get(i).set(3, saldoCta);
                    tmpArrayLista.get(i).set(5, saldoOrig);
                    tmpArrayLista.get(i).add("");
                    tableModel.insertRow(tmpArrayLista.get(i));
                }
            }
        }

        if (tblModelRegistros.getRowCount() > 0) {
            /*
            for(int i=0;i<tableModel.getRowCount();i++){
                System.out.println("------------------------");
                System.out.println("         FILA : "+i);
                for(int j=0;j<tableModel.getColumnCount();j++){
                    String valor=tblModelTarjetas.getValueAt(i, j).toString();
                    System.out.println("["+i+"]["+j+"] "+valor);
                }
            }
            */
            configuraNuevaTabla(listaTarjeta);
            FarmaUtility.moveFocusJTable(tblTarjetas);
            if(opTarjeta){
                int i = tblTarjetas.getSelectedRow();
                String monto = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 2);
                txtMontoPagar.setText(monto);
            }else if(opPrestamo){
                int i = tblTarjetas.getSelectedRow();
                String monto = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6);
                txtMontoPagar.setText(monto);
            }else if(opDeposito){
                txtMontoPagar.setText("");
            }
        } else {
            FarmaUtility.moveFocus(txtDocIdentidad);
        }
    }
    
    double vMontoMin=0.0;
    double vMontoMes=0.0;
    double vMontoTotal=0.0;
    
    double vMontoMinOrig=0.0;
    double vMontoMesOrig=0.0;
    double vMontoTotalOrig=0.0;
    
    double vMontoCuota=0.0;
    
    private void registarPagoTarjeta() {
        String strEstadoCuenta = "";
        String strMonedaPago = "";
        String strNumDNI = txtDocIdentidad.getText().trim();
        String montoPagar = txtMontoPagar.getText().substring(2, 3).trim();
        if (montoPagar.equalsIgnoreCase(".")) {
            montoPagar = txtMontoPagar.getText().substring(3).trim();
        } else {
            montoPagar = txtMontoPagar.getText().substring(2).trim();
        }
        vMontoCuota = FarmaUtility.getDecimalNumber(montoPagar);
        VariablesRecaudacion.vMontoPago = String.valueOf(vMontoCuota);
        selecionaTipoOperacion(false);
        int i = tblTarjetas.getSelectedRow();
        String nroTarjeta = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 0);
        
        String siglaMoneda_Pago = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 1);
        String sMontoMinimoPagar = recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 2));
        String sMontoMesPagar = recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 3));
        String sMontoTotalPagar = recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 4));
        
        vMontoMin=FarmaUtility.getDecimalNumber(sMontoMinimoPagar);
        vMontoMes=FarmaUtility.getDecimalNumber(sMontoMesPagar);
        vMontoTotal=FarmaUtility.getDecimalNumber(sMontoTotalPagar);
        
        String siglaMoneda_Origen = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 5);
        String sRedMinimoPagar = recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6));
        String sRedMesPagar = recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 7));
        String sRedTotalPagar = recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 8));
        
        vMontoMinOrig=FarmaUtility.getDecimalNumber(sRedMinimoPagar);
        vMontoMesOrig=FarmaUtility.getDecimalNumber(sRedMesPagar);
        vMontoTotalOrig=FarmaUtility.getDecimalNumber(sRedTotalPagar);
        
        double dTipoCambioOrigen =Double.parseDouble(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));
            //FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));

        if (validarCamposPagoTarjeta()) {            
            strMonedaPago = obtieneTipoMoneda(siglaMoneda_Pago); //de grilla
            VariablesRecaudacion.vTipoMonedaOrigen = obtieneTipoMoneda(siglaMoneda_Origen); //de grilla
            if (VariablesRecaudacion.vTipoMonedaOrigen.contentEquals(ConstantsCaja.EFECTIVO_DOLARES)) {
                VariablesRecaudacion.vTipoCambioBFP = dTipoCambioOrigen;
            } else {
                VariablesRecaudacion.vTipoCambioBFP = 0.00d;
            }
            strEstadoCuenta = ConstantsRecaudacion.EST_CTA_SOLES;

            /*if (strMonedaPago.equals("01")) {
                    dMontoPagarSoles = dCuantoPaga;
                    TotalVenta = dMontoPagarSoles;
                    strEstadoCuenta = ConstantsRecaudacion.EST_CTA_SOLES;
                } else if (strMonedaPago.equals("02")) {
                    dMontoPagardolares = dCuantoPaga;
                    TotalVenta = dMontoPagardolares * VariablesRecaudacion.vTipoCambioVenta;
                    strEstadoCuenta = ConstantsRecaudacion.EST_CTA_DOLARES;
                    tipmoneda = false;
                }*/

            ArrayList<Object> arrayCabecera = new ArrayList<Object>();
            /*0*/arrayCabecera.add(FarmaVariables.vCodGrupoCia);
            /*1*/arrayCabecera.add(FarmaVariables.vCodCia);
            /*2*/arrayCabecera.add(FarmaVariables.vCodLocal);
            /*3*/arrayCabecera.add(nroTarjeta); /// 3
            /*4*/arrayCabecera.add(ConstantsRecaudacion.TIPO_REC_FINANCIERO);
            /*5*/arrayCabecera.add(VariablesRecaudacion.vTipoOperacion_BFP); // TIPO PAGO //FarmaLoadCVL.getCVLCode(ConstantsRecaudacion.NOM_HASTABLE_CMB_TIPO_PAGO, cmbTipoPago.getSelectedIndex())
            /*6*/arrayCabecera.add(ConstantsRecaudacion.ESTADO_PENDIENTE); // ESTADO
            /*7*/arrayCabecera.add(strEstadoCuenta); //ESTADO DE CUENTA
            /*8*/arrayCabecera.add(""); //CODIGO DEL CLIENTE
            /*9*/arrayCabecera.add(strMonedaPago); //TIPO DE MONEDA //
            /*10*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto total(moneda original)
            /*11*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto soles
            /*12*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto minimo
            /*13*/arrayCabecera.add(dTipoCambioOrigen); /*VariablesRecaudacion.vTipoCambioVenta*/ //Tipo cambio
            /*14*/arrayCabecera.add(""); //Fecha DE VENCIMIENTO DE RECAUDACION
            /*15*/arrayCabecera.add(""); //NOMBRE DEL CLIENTE
            /*16*/arrayCabecera.add(""); //Fecha Venc Tarj (CMR)
            /*17*/arrayCabecera.add(ConstantsRecaudacion.FECHA_RCD); //FECHA DE CREACION
            /*18*/arrayCabecera.add(FarmaVariables.vIdUsu); // USUARIO CREADOR
            /*19*/arrayCabecera.add(""); //FECHA DE MODIFICACION
            /*20*/arrayCabecera.add(""); //USUARIO MODIFICADOR
            /*21*/arrayCabecera.add(""); //codigo de autorizacion
            /*22*/arrayCabecera.add(""); //mov de caja
            /*23*/arrayCabecera.add(""); //numero de pedido solo valido para VENTA CMR
            /*24*/arrayCabecera.add(""); //tipo producto servicio
            /*25*/arrayCabecera.add(""); //numero de recibo de pago
            /*26*/arrayCabecera.add(nroTarjeta); //numero de tarjeta
            /*27*/arrayCabecera.add(siglaMoneda_Pago); //sigla de moneda
            /*28*/arrayCabecera.add(vMontoMin); //monto minimo a pagar
            /*29*/arrayCabecera.add(vMontoMes); //monto mes a pagar
            /*30*/arrayCabecera.add(vMontoTotal); //monto total a pagar
            /*31*/arrayCabecera.add(vMontoMinOrig); //redondeo minimo a pagar
            /*32*/arrayCabecera.add(vMontoMesOrig); //redondeo mes a pagar
            /*33*/arrayCabecera.add(vMontoTotalOrig); //redondeo total a pagar
            /*34*/arrayCabecera.add(strNumDNI); //numero de DNI // 34
                  
            String tmpCodigo =facadeRecaudacion.grabaCabeRecau(arrayCabecera, strNumDNI);//"03935";//
            
            if (!tmpCodigo.equals("")) {
                //ABRE PANTALLA DE COBRO
                VariablesRecaudacion.vVersionBFP = "02";
                VariablesRecaudacion.arrayCabecera=arrayCabecera;
                VariablesRecaudacion.vCodCabRecau=tmpCodigo;
                if (facadeRecaudacion.cobrarRecaudacion(myParentFrame, tmpCodigo, arrayCabecera,
                                                        ConstantsRecaudacion.TIPO_REC_FINANCIERO)) {
                    /*DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                        dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_PAGO);
                        dlgProcesarPagoTerceros.procesarPagoFinanciero("TER1", strNumDNI, "T", nroTarjeta,
                                                                       dCuantoPaga, strMonedaPago, dMontoPagoTotal);
                        dlgProcesarPagoTerceros.mostrar();*/

                    cerrarVentana(true);
                }
            } else {
                FarmaUtility.showMessage(this, "Error al procesar el cobro.", txtDocIdentidad);
            }
            //}
        }
    }
    private boolean validarCamposPagoPrestamo() {
        boolean resultado = true;
        String tipoMonedaOrigen = "";
        String tipCambio_Ref = "";
        double dMontoPagar = 0;
        double dImportePagar = 0;
        double dCuantoPaga = 0;
        double tipoCambioOrigen = 0;
        double lim_max_rcd_Cambio = 0;

        int i = tblTarjetas.getSelectedRow();
        dMontoPagar =
            FarmaUtility.getDecimalNumber(recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6)));
                
        tipoMonedaOrigen = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 7);
        tipoCambioOrigen =
                FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));
        String importePagar = txtMontoPagar.getText().substring(2, 3);
        if (importePagar.equalsIgnoreCase(".")) {
            importePagar = txtMontoPagar.getText().substring(3).trim();
        } else {
            importePagar = txtMontoPagar.getText().substring(2).trim();
        }
        dCuantoPaga = FarmaUtility.getDecimalNumber(importePagar);
        
        if (i >= 0) {
            if (importePagar.isEmpty()) {
                FarmaUtility.showMessage(this, "Ingrese Monto a Pagar válido", null);
                resultado = false;
            } else {
                if (realizarValidacion) {
                    if (dCuantoPaga == 0) {
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser mayor cero " + importePagar, null);
                        resultado = false;
                    }

                    if (obtieneTipoMoneda(tipoMonedaOrigen).equals(ConstantsCaja.EFECTIVO_SOLES)) {
                        if (dCuantoPaga > lim_max_rcd_Soles) {
                            FarmaUtility.showMessage(this,
                                                     "Monto a Pagar excede al máximo permitido para este canal: " +
                                                     ConstantsRecaudacion.SIMBOLO_SOLES + " " + lim_max_rcd_Soles +
                                                     " soles", null);
                            resultado = false;
                        }
                    } else {
                        lim_max_rcd_Cambio = lim_max_rcd_Dolares * tipoCambioOrigen;
                        if (dCuantoPaga > lim_max_rcd_Cambio) {
                            FarmaUtility.showMessage(this,
                                                     "Monto a Pagar excede al máximo permitido para este canal:\n" +
                                    ConstantsRecaudacion.SIMBOLO_SOLES + " " + lim_max_rcd_Cambio + " soles " +
                                    "o su equivalente en moneda extranjera a " + ConstantsRecaudacion.SIMBOLO_DOLARES +
                                    " " + lim_max_rcd_Dolares, null);
                            resultado = false;
                        }
                    }
                }
            }
        } else {
            FarmaUtility.showMessage(this, "Debe realizar una búsqueda para proceder con el pago", txtDocIdentidad);
            resultado = false;
        }
        return resultado;
    }
    
    private boolean validarCamposDepositoCta() {
        boolean resultado = true;
        String tipoMonedaOrigen = "";
        double dCuantoPaga = 0;
        double tipoCambioOrigen = 0;
        double lim_max_rcd_Cambio = 0;

        int i = tblTarjetas.getSelectedRow();
        tipoMonedaOrigen = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 4);
        tipoCambioOrigen =
                FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6));
        
        String importePagar = txtMontoPagar.getText().substring(2, 3);
        if (importePagar.equalsIgnoreCase(".")) {
            importePagar = txtMontoPagar.getText().substring(3).trim();
        } else {
            importePagar = txtMontoPagar.getText().substring(2).trim();
        }
        dCuantoPaga = FarmaUtility.getDecimalNumber(importePagar);
        
        if (i >= 0) {
            if (importePagar.isEmpty()) {
                FarmaUtility.showMessage(this, "Ingrese Monto a Pagar válido", null);
                resultado = false;
            } else {
                if (realizarValidacion) {
                    if (dCuantoPaga == 0) {
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser mayor cero " + importePagar, null);
                        resultado = false;
                    }

                    if (obtieneTipoMoneda(tipoMonedaOrigen).equals(ConstantsCaja.EFECTIVO_SOLES)) {
                        if (dCuantoPaga > lim_max_rcd_Soles) {
                            FarmaUtility.showMessage(this,
                                                     "Monto a Pagar excede al máximo permitido para este canal: " +
                                                     ConstantsRecaudacion.SIMBOLO_SOLES + " " + lim_max_rcd_Soles +
                                                     " soles", null);
                            resultado = false;
                        }
                    } else {
                        lim_max_rcd_Cambio = lim_max_rcd_Dolares * tipoCambioOrigen;
                        if (dCuantoPaga > lim_max_rcd_Cambio) {
                            FarmaUtility.showMessage(this,
                                                     "Monto a Pagar excede al máximo permitido para este canal:\n" +
                                    ConstantsRecaudacion.SIMBOLO_SOLES + " " + lim_max_rcd_Cambio + " soles " +
                                    "o su equivalente en moneda extranjera a " + ConstantsRecaudacion.SIMBOLO_DOLARES +
                                    " " + lim_max_rcd_Dolares, null);
                            resultado = false;
                        }
                    }
                }
            }
        } else {
            FarmaUtility.showMessage(this, "Debe realizar una búsqueda para proceder con el pago", txtDocIdentidad);
            resultado = false;
        }
        return resultado;
    }
    
    private boolean validarCamposPagoTarjeta() {
        boolean resultado = true;
        String nroTarjeta = "";
        String marcaTarjeta = "";
        String tipoMonedaOrigen = "";
        String tipCambio_Ref = "";
        double dMontoMinimoPagar = 0;
        double dMontoMesPagar = 0;
        double dMontoTotalPagar = 0;
        double dMontoTotalDeuda = 0;
        double dCuantoPaga = 0;
        double tipoCambioOrigen = 0;
        double lim_max_rcd_Cambio = 0;

        int i = tblTarjetas.getSelectedRow();
        nroTarjeta = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 0);
        dMontoMinimoPagar =
            FarmaUtility.getDecimalNumber(recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 2)));
                
        dMontoMesPagar =
            FarmaUtility.getDecimalNumber(recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 3)));
                
        dMontoTotalPagar =
            FarmaUtility.getDecimalNumber(recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 4)));
                
        tipoMonedaOrigen = FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 5);
        tipoCambioOrigen =
                FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));
        String montoPagar = txtMontoPagar.getText().substring(2, 3);
        if (montoPagar.equalsIgnoreCase(".")) {
            montoPagar = txtMontoPagar.getText().substring(3).trim();
        } else {
            montoPagar = txtMontoPagar.getText().substring(2).trim();
        }
        dCuantoPaga = FarmaUtility.getDecimalNumber(montoPagar);
        marcaTarjeta = utilityRecaudacion.obtieneMarcaTarjetaCreditoFinanciero(nroTarjeta);
        if (tipoCambioOrigen == 0) {
            dMontoTotalDeuda =
                    FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 8));
            tipoCambioOrigen = dMontoTotalPagar / dMontoTotalDeuda;
            tipCambio_Ref = "\n(Tipo de cambio referencial de MiFarma)";
        }

        if (i >= 0) {
            if (montoPagar.isEmpty()) {
                FarmaUtility.showMessage(this, "Ingrese Monto a Pagar válido", null);
                resultado = false;
            } else {
                if (realizarValidacion) {
                    if (dCuantoPaga == 0) {
                        FarmaUtility.showMessage(this, "Monto a Pagar debe ser mayor cero " + montoPagar, null);
                        resultado = false;
                    }

                    if (obtieneTipoMoneda(tipoMonedaOrigen).equals(ConstantsCaja.EFECTIVO_SOLES)) {
                        if (dCuantoPaga > lim_max_rcd_Soles) {
                            FarmaUtility.showMessage(this,
                                                     "Monto a Pagar excede al máximo permitido para este canal: " +
                                                     ConstantsRecaudacion.SIMBOLO_SOLES + " " + lim_max_rcd_Soles +
                                                     " soles", null);
                            resultado = false;
                        }
                    } else {
                        lim_max_rcd_Cambio = lim_max_rcd_Dolares * tipoCambioOrigen;
                        if (dCuantoPaga > lim_max_rcd_Cambio) {
                            FarmaUtility.showMessage(this,
                                                     "Monto a Pagar excede al máximo permitido para este canal:\n" +
                                    ConstantsRecaudacion.SIMBOLO_SOLES + " " + lim_max_rcd_Cambio + " soles " +
                                    "o su equivalente en moneda extranjera a " + ConstantsRecaudacion.SIMBOLO_DOLARES +
                                    " " + lim_max_rcd_Dolares, null);
                            resultado = false;
                        }
                    }
                }
            }
        } else {
            FarmaUtility.showMessage(this, "Debe realizar una búsqueda para proceder con el pago", txtDocIdentidad);
            resultado = false;
        }
        return resultado;
    }
    
    private void txtDocIdentidad_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            VariablesRecaudacion.vNombre_Cliente_BFP = "";
            lblNombre.setText("");
            txtMontoPagar.setText("");
            selecionaTipoOperacion(true);
            if (validarBusqueda()) {
                boolean isConsultaOk = true;
                ArrayList<ArrayList<String>> tmpLista = new ArrayList<>();
                 
                DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_CONSU_FINANCIERO);
                dlgProcesarPagoTerceros.procesarConsultaFinanciero("0"+FarmaVariables.vCodLocal, txtDocIdentidad.getText().trim(), VariablesRecaudacion.vTipoOperacion_BFP);
                dlgProcesarPagoTerceros.mostrar();
                
                isConsultaOk = dlgProcesarPagoTerceros.isVProcesoConsulta();//true;//
                /* */
                
                if (isConsultaOk) {
                    tblModelRegistros.clearTable();
                    tmpLista = dlgProcesarPagoTerceros.getTmpLista();
                    //tmpLista = dataPrueba();                    
                    if (tmpLista.size() == 1) {
                        String col1 = FarmaUtility.getValueFieldArrayList(tmpLista, 0, 0);
                        if (col1.length() == 3) {
                            FarmaUtility.showMessage(this, "No se encontraron resultados para el DNI ingresado", null);
                        } else {
                            lblNombre.setText(VariablesRecaudacion.vNombre_Cliente_BFP);
                            llenarTabla(tblModelRegistros, tmpLista);
                        }
                    } else if (tmpLista.size() > 1) {
                        lblNombre.setText(VariablesRecaudacion.vNombre_Cliente_BFP);
                        llenarTabla(tblModelRegistros, tmpLista);
                    }
                    setFrame_tipoCambio();
                } else {
                    tblModelRegistros.clearTable();
                    initTable();
                    if (VariablesRecaudacion.vMensajeError != null &&
                        !VariablesRecaudacion.vMensajeError.trim().equalsIgnoreCase("")) {
                        FarmaUtility.showMessage(this, "[Mensaje Operador]" + " :\n" +
                                VariablesRecaudacion.vMensajeError, txtDocIdentidad);
                        VariablesRecaudacion.vMensajeError="";
                    }
                }
            }
        } else {
            chkKeyPressed(e);
        }
    }

    private void txtDocIdentidad_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtDocIdentidad, e);
    }

    private void btnDocIdentidad_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtDocIdentidad);
    }

    private void btnListaTarjeta_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocusJTable(tblTarjetas);
        txtMontoPagar.setText("");
    }

    private String obtieneTipoMoneda(String siglaMoneda) {
        String resultado = "";
        siglaMoneda=siglaMoneda.trim();
        System.out.println("=============[SIGLA MONEDA] ingreso ===========> "+siglaMoneda);
        if (siglaMoneda.equals(ConstantsRecaudacion.SIMBOLO_SOLES) || 
            siglaMoneda.equals("S/.")|| siglaMoneda.equals("PEN")) {
            resultado = ConstantsCaja.EFECTIVO_SOLES;
        } else if (siglaMoneda.equals("US$")||siglaMoneda.equals("USD")) {
            resultado = ConstantsCaja.EFECTIVO_DOLARES;
        }
        System.out.println("=============[SIGLA MONEDA] Salida ===========> "+resultado);
        return resultado;
    }
    
    private void tblTarjetas_KeyPressed(KeyEvent e) {
        int fila = tblTarjetas.getSelectedRow();
        int columna = tblTarjetas.getSelectedColumn();
        if(fila==0 && columna==-1){
            if(opTarjeta)
                columna=1;
            if(opPrestamo)
                columna=4;
            if(opDeposito)
                columna=3;
        }
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ENTER:
            e.consume();
            if(opTarjeta)
                columna=4;
            if(opPrestamo)
                columna=5;
            if(opDeposito)
                columna=3;
            editarOtroMonto(fila,columna);
            break;
        case KeyEvent.VK_UP:
            seleccionaFila(-1,columna);
            break;
        case KeyEvent.VK_DOWN:
            seleccionaFila(1,columna);
            break;
        case KeyEvent.VK_RIGHT:
            seleccionaCelda(fila,columna+1);
            break;
        case KeyEvent.VK_LEFT:
            seleccionaCelda(fila,columna-1);
            break;
        default:
            chkKeyPressed(e);
        }
    }

    private void setFrame_tipoCambio() {
        int indexTC=6;
        if(opTarjeta || opPrestamo)
            indexTC=9;
        for (int i = 0; i < tblModelRegistros.getRowCount(); i++) {
            double tipoCambioOrigen =
                FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, indexTC));
            if (tipoCambioOrigen != 0) {
                lblTituloTCambio.setText("Tipo de Cambio Referencial");
                lblTipoCambio.setText(ConstantsRecaudacion.SIMBOLO_SOLES + " " + tipoCambioOrigen);
                break;
            }
        }
    }

    private ArrayList<ArrayList<String>> dataPrueba() {
        ArrayList<ArrayList<String>> tmpLista = new ArrayList();
        if(opTarjeta){
            VariablesRecaudacion.vNombre_Cliente_BFP="Ricardo G*********";
            ArrayList lista1 = new ArrayList();
            ArrayList lista2 = new ArrayList();
            ArrayList lista3 = new ArrayList();
            lista1.add("362333****2202");
            lista1.add("S/.");
            lista1.add("814.62");
            lista1.add("1000.00");
            lista1.add("17593.17");
            lista1.add("S/.");
            lista1.add("814.62");
            lista1.add("1000.00");
            lista1.add("17593.17");
            lista1.add("0.00");

            lista2.add("514940******8121");
            lista2.add("S/.");
            lista2.add("270.840");
            lista2.add("9750.860");
            lista2.add("9750.860");
            lista2.add("US$");
            lista2.add("81.09");
            lista2.add("2919.42");
            lista2.add("2919.42");
            lista2.add("3.340");

            lista3.add("432343******0118");
            lista3.add("S/.");
            lista3.add("197.15");
            lista3.add("500.00");
            lista3.add("1822.73");
            lista3.add("S/.");
            lista3.add("197.15");
            lista3.add("500.00");
            lista3.add("1822.73");
            lista3.add("0.00");

            tmpLista.add(lista1);
            tmpLista.add(lista2);
            tmpLista.add(lista3);
        }else if(opPrestamo){
            VariablesRecaudacion.vNombre_Cliente_BFP="Erick Ale*********";
            ArrayList lista1 = new ArrayList();
            ArrayList lista2 = new ArrayList();
            ArrayList lista3 = new ArrayList();
            lista1.add("1");
            lista1.add("Personal");
            lista1.add("*******2400325022015");
            lista1.add("25/02/2015");
            lista1.add("CRD.MIC.C/");
            lista1.add("S/.");
            lista1.add("1000.00");
            lista1.add("S/.");
            lista1.add("1000.00");
            lista1.add("0.0");
            
            lista2.add("2");
            lista2.add("Microempresa");
            lista2.add("*******8207805102015");
            lista2.add("05/10/2015");
            lista2.add("Ptmo Credi");
            lista2.add("S/.");
            lista2.add("1500.00");
            lista2.add("S/.");
            lista2.add("1500.00");
            lista2.add("0.0");
            
            lista3.add("3");
            lista3.add("Consumo");
            lista3.add("*******4630914062017");
            lista3.add("14/06/2017");
            lista3.add("C.Hipot. M");
            lista3.add("S/.");
            lista3.add("325.00");
            lista3.add("US$");
            lista3.add("100.00");
            lista3.add("3.25");
            
            tmpLista.add(lista1);
            tmpLista.add(lista2);
            tmpLista.add(lista3);
            
        }else if(opDeposito){
            VariablesRecaudacion.vNombre_Cliente_BFP="Roger Chi*********";
            ArrayList lista1 = new ArrayList();
            ArrayList lista2 = new ArrayList();
            ArrayList lista3 = new ArrayList();
            
            lista1.add("000******722");
            lista1.add("AHORRO CUE");
            lista1.add("S/.");
            lista1.add("0.00");
            lista1.add("S/.");
            lista1.add("0.00");
            lista1.add("0.00");
            
            lista2.add("000******326");
            lista2.add("DpCtaCte");
            lista2.add("S/.");
            lista2.add("0.00");
            lista2.add("US$");
            lista2.add("0.00");
            lista2.add("3.45");
            
            lista3.add("000******722");
            lista3.add("CUENTA AZU");
            lista3.add("S/.");
            lista3.add("0.00");
            lista3.add("US$");
            lista3.add("0.00");
            lista3.add("3.45");
            
            tmpLista.add(lista1);
            tmpLista.add(lista2);
            tmpLista.add(lista3);
        }
        
        return tmpLista;
    }

    private void seleccionaFila(int position,int columna) {
        int i = tblTarjetas.getSelectedRow();
        i = i + position;
        if (i < 0) {
            i = 0;
        }
        if (i == tblTarjetas.getRowCount()) {
            i--;
        }
        String monto = "";
        String tipCambio="";
        if(opTarjeta){
            if(columna==0)
                columna=1;
            monto = recuperaValorPago_Tarjeta(i,columna);
        }
        if(opPrestamo){
            tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9).trim();
            if(columna<=4)
                monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6).trim();
            else{
                monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 10).trim();
                if(!monto.equalsIgnoreCase("")){
                    if(Double.parseDouble(tipCambio)!=0){
                        try{
                            monto=monto.substring(3).trim().replace(",", "");
                        }catch(Exception e){
                            monto=monto.substring(3).trim();
                        }
                        double valorCambio=Double.parseDouble(monto)*Double.parseDouble(tipCambio);
                        monto=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,valorCambio+"");
                    }
                }
            }
        }
        if(opDeposito){
            monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 7).trim();
            if(!monto.equalsIgnoreCase("")){
                tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6).trim();
                if(Double.parseDouble(tipCambio)!=0 && !monto.equalsIgnoreCase("")){
                    try{
                        monto=monto.substring(3).trim().replace(",", "");
                    }catch(Exception e){
                        monto=monto.substring(3).trim();
                    }
                    double valorCambio=Double.parseDouble(monto)*Double.parseDouble(tipCambio);
                    monto=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,valorCambio+"");
                }
            }
        }
        txtMontoPagar.setText(monto);
    }

    private void seleccionaCelda(int fila,int columna) {
        String monto ="";
        String tipCambio="";
        if(columna<=0){
            if(opTarjeta)
                columna=1;
            if(opPrestamo)
                columna=4;
            if(opDeposito)
                columna=3;
        }
        log.info("----------------------------> ["+fila+"]["+columna+"]");
        if(opTarjeta){
            if(columna>0 && columna<=4){
                monto = recuperaValorPago_Tarjeta(fila,columna);
            }
        }else if(opPrestamo){
            tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, 9).trim();
            if(columna<=4){
                monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, 6).trim();
            }else{
                monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, 10).trim();
                if(!monto.equalsIgnoreCase("")){
                    if(Double.parseDouble(tipCambio)!=0){
                        try{
                            monto=monto.substring(3).trim().replace(",", "");
                        }catch(Exception e){
                            monto=monto.substring(3).trim();
                        }
                        double valorCambio=Double.parseDouble(monto)*Double.parseDouble(tipCambio);
                        monto=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,valorCambio+"");
                    }
                }
            }
        }else if(opDeposito){
            monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, 7).trim();
            if(!monto.equalsIgnoreCase("")){
                tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, 6).trim();
                if(Double.parseDouble(tipCambio)!=0){
                    try{
                        monto=monto.substring(3).trim().replace(",", "");
                    }catch(Exception e){
                        monto=monto.substring(3).trim();
                    }
                    double valorCambio=Double.parseDouble(monto)*Double.parseDouble(tipCambio);
                    monto=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,valorCambio+"");
                }
            }
        }
        txtMontoPagar.setText(monto);
    }
    private void editarOtroMonto(int fila,int columna) {
        if(opTarjeta){
            if(columna==4){
                fila_aux=fila;
                colum_aux=columna;
                monto_aux=txtMontoPagar.getText();
                txtMontoPagar.setEditable(true);
                txtMontoPagar.setText("");
                FarmaUtility.moveFocus(txtMontoPagar);
            }
        }else if(opPrestamo){
            if(columna==5){
                fila_aux=fila;
                colum_aux=columna;
                monto_aux=txtMontoPagar.getText();
                txtMontoPagar.setEditable(true);
                txtMontoPagar.setText("");
                FarmaUtility.moveFocus(txtMontoPagar);
            }
        }else if(opDeposito){
            columna=3;
            fila_aux=fila;
            colum_aux=columna;
            monto_aux=txtMontoPagar.getText();
            txtMontoPagar.setEditable(true);
            txtMontoPagar.setText("");
            FarmaUtility.moveFocus(txtMontoPagar);            
        }
    }
    private String formatoMonedoPago(String simboloMoneda, String monto) {
        int tamanio=0;
        int factor=monto.length()-3;
        if(opTarjeta)
            tamanio = 18;
        if(opPrestamo)
            tamanio = 11;
        if(opDeposito)
            tamanio = 21;
        String simbolo = "";
        System.out.println("==[simboloMoneda - Ingreso]==> '"+simboloMoneda+"'");
        if (simboloMoneda.equalsIgnoreCase(ConstantsRecaudacion.SIMBOLO_SOLES) ||
            simboloMoneda.equalsIgnoreCase("S/.")) {
            simbolo = ConstantsRecaudacion.SIMBOLO_SOLES + " ";
        } else {
            simbolo = ConstantsRecaudacion.SIMBOLO_DOLARES + "  ";
        }
        System.out.println("==[simboloMoneda - Salida]==> "+simbolo);
        switch (monto.length()) {
        case 3:
            tamanio = (tamanio-factor*2) + monto.length();
            break;
        case 4:
            tamanio = (tamanio-factor*2) + monto.length();
            break; //1
        case 5:
            tamanio = (tamanio-factor*2) + monto.length();
            break; //2
        case 6:
            tamanio = (tamanio-factor*2) + monto.length();
            break; //3
        case 7:
            tamanio = (tamanio-factor*2) + monto.length();
            break; //4
        case 8:
            tamanio = (tamanio-factor*2) + monto.length();
            break; //5
        case 9:
            tamanio = (tamanio-factor*2) + monto.length();
            break; //6
        case 10:
            tamanio = (tamanio-factor*2) + monto.length();
            break; //7
        default:
            tamanio = (tamanio-factor*2) + monto.length();
        }
        String retorno = monto;
        System.out.println(" monto: '"+monto+"'");
        try{
            if (Double.parseDouble(monto) == 0) {
                retorno = "0.00";
            } else {
                retorno = FarmaUtility.formatNumber(Double.parseDouble(monto.trim()));//monto;
            }
            while (retorno.length() <= tamanio) {
                retorno = " " + retorno;
            }
            retorno = simbolo + retorno;
        }catch(Exception e){
            retorno ="";
        }
        return retorno;
    }

    private void configuraNuevaTabla(ArrayList<RegistroOperacion> registroOperacion) {
        if(opTarjeta){
            BFPModelTblTarjeta modelBFP = new BFPModelTblTarjeta();
            BFPControlTblTarjeta control;
            if (registroOperacion != null && !registroOperacion.isEmpty()) 
                control = new BFPControlTblTarjeta(modelBFP, registroOperacion);
            else
                control = new BFPControlTblTarjeta(modelBFP);
            modelBFP = control.getModelo();
            BFPRenderTblTarjeta render = new BFPRenderTblTarjeta();
            render.setTipoOperacion(1);
            tblTarjetas = new JTable(modelBFP);
            tblTarjetas.setRowHeight(20); // Un alto a las filas para que quepan los iconos.
            srcDatos.setViewportView(tblTarjetas);
            srcDatos.setColumnHeaderView(tblTarjetas.getTableHeader());
            tblTarjetas.setColumnSelectionInterval(0,1);
            
            tblTarjetas.setDefaultRenderer(String.class, render);
            tblTarjetas.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblTarjetas_KeyPressed(e);
                }
            });
        }else if(opPrestamo){
            BFPModelTblPrestamo modelBFP = new BFPModelTblPrestamo();
            BFPControlTblPrestamo control;
            if (registroOperacion != null && !registroOperacion.isEmpty())
                control = new BFPControlTblPrestamo(modelBFP, registroOperacion);
            else
                control = new BFPControlTblPrestamo(modelBFP);
                
            modelBFP = control.getModelo();
            BFPRenderTblTarjeta render = new BFPRenderTblTarjeta();
            render.setTipoOperacion(2);
            tblTarjetas = new JTable(modelBFP);
            tblTarjetas.setRowHeight(20); // Un alto a las filas para que quepan los iconos.
            srcDatos.setViewportView(tblTarjetas);
            srcDatos.setColumnHeaderView(tblTarjetas.getTableHeader());
            tblTarjetas.setColumnSelectionInterval(0,5);
            
            tblTarjetas.setDefaultRenderer(String.class, render);
            tblTarjetas.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblTarjetas_KeyPressed(e);
                }
            });
        }else if(opDeposito){
            BFPModelTblDeposito modelBFP = new BFPModelTblDeposito();
            BFPControlTblDeposito control;
            if (registroOperacion != null && !registroOperacion.isEmpty())
                control = new BFPControlTblDeposito(modelBFP, registroOperacion);
            else
                control = new BFPControlTblDeposito(modelBFP);
            modelBFP = control.getModelo();
            BFPRenderTblTarjeta render = new BFPRenderTblTarjeta();
            render.setTipoOperacion(3);
            tblTarjetas = new JTable(modelBFP);
            tblTarjetas.setRowHeight(20); // Un alto a las filas para que quepan los iconos.
            srcDatos.setViewportView(tblTarjetas);
            srcDatos.setColumnHeaderView(tblTarjetas.getTableHeader());
            tblTarjetas.setColumnSelectionInterval(0,3);
            
            tblTarjetas.setDefaultRenderer(String.class, render);
            tblTarjetas.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblTarjetas_KeyPressed(e);
                }
            });
        }
    }

    private String recuperaValorPago_Tarjeta(int fila, int columna) {
        String monto;
        String tipCambio;
        int nvoColumna=0;
        switch(columna){
            case 1:nvoColumna=2;break;
            case 2:nvoColumna=3;break;
            case 3:nvoColumna=4;break;
            case 4:nvoColumna=10;break;
        }
        tipCambio=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, 9).trim();
        log.info("===================================> ["+fila+"]["+nvoColumna+"<=="+columna+"]");
        if(nvoColumna==10){
            monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, nvoColumna).trim();
            if(!monto.equalsIgnoreCase("")){
                if(Double.parseDouble(tipCambio)!=0){
                    try{
                        monto=monto.substring(3).trim().replace(",", "");
                    }catch(Exception e){
                        monto=monto.substring(3).trim();
                    }
                    double valorCambio=Double.parseDouble(monto)*Double.parseDouble(tipCambio);
                    monto=formatoMonedoPago(ConstantsRecaudacion.SIMBOLO_SOLES,valorCambio+"");
                }
            }
        }else{
            monto=FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, fila, nvoColumna).trim();
        }
        return monto;
    }
    
    private String recuperaMonto(String montoTabla) {
        String monto="";
        String moneda=montoTabla.substring(0, 2).trim();
        String punto=montoTabla.substring(2,3).trim();
        if(moneda.equalsIgnoreCase(ConstantsRecaudacion.SIMBOLO_SOLES.trim())){
            if(punto.equalsIgnoreCase(".")){
                monto=montoTabla.substring(3).trim();
            }else{
                monto=montoTabla.substring(2).trim();
            }
        }
        return monto;
    }

    private String recuperaDNI_Usuario() {
        String DNI_user=utilityRecaudacion.recuperaDNI_Usuario();
        return DNI_user;
    }

    private void rdoPagoTarjeta_KeyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void rdoPagoPrestamo_KeyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void rdoDepositoCta_KeyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }
    
    int tipoPrestamo=50;
    private void selecionaTipoOperacion(boolean esConsulta) {
        boolean chkTC=opTarjeta;
        boolean chkPP=opPrestamo;
        boolean chkDC=opDeposito;
        if(chkTC){
            VariablesRecaudacion.vTipoOperacion_BFP=VariablesRecaudacion.PAGO_TARJ_BFP;
        }else if(chkPP){
            if(esConsulta){
                VariablesRecaudacion.vTipoOperacion_BFP=VariablesRecaudacion.PAGO_PRESTAMO_BFP;
            }else{//Es pago prestamo
                int i = tblTarjetas.getSelectedRow();
                tipoPrestamo=Integer.parseInt(tblModelRegistros.getValueAt(i,0).toString().trim());
                switch(tipoPrestamo){
                case 1://Personal
                    VariablesRecaudacion.vTipoOperacion_BFP=VariablesRecaudacion.PRESTAMO_PERSONAL;
                    break;
                case 2://MicroEmpresa
                    VariablesRecaudacion.vTipoOperacion_BFP=VariablesRecaudacion.PRESTAMO_MICROEMPRESA;
                    break;
                case 3://Consumo
                case 4://Consumo
                    VariablesRecaudacion.vTipoOperacion_BFP=VariablesRecaudacion.PRESTAMO_CONSUMO;
                    break;
                default:
                    VariablesRecaudacion.vTipoOperacion_BFP=VariablesRecaudacion.PAGO_PRESTAMO_BFP;
                }
            }
        }else if(chkDC){
            VariablesRecaudacion.vTipoOperacion_BFP=VariablesRecaudacion.DEPOSITO_CTA_BFP;
        }
    }

    private void registarPagoPrestamo() {
        for(int i=0;i<tblModelRegistros.getRowCount();i++){
            System.out.println("==> FILA: "+i);
            for(int j=0;j<tblModelRegistros.getColumnCount();j++){
                System.out.println("======> Column: ["+j+"] = '"+tblModelRegistros.getValueAt(i, j).toString()+"'");
            }
        }
        String strEstadoCuenta = "";
        String strMonedaPago = "";
        String strNumDNI = txtDocIdentidad.getText().trim();
        String montoPagar = txtMontoPagar.getText().substring(2, 3).trim();
        if (montoPagar.equalsIgnoreCase(".")) {
            montoPagar = txtMontoPagar.getText().substring(3).trim();
        } else {
            montoPagar = txtMontoPagar.getText().substring(2).trim();
        }
        vMontoCuota = FarmaUtility.getDecimalNumber(montoPagar);
        VariablesRecaudacion.vMontoPago = String.valueOf(vMontoCuota);
        selecionaTipoOperacion(false);
        int i = tblTarjetas.getSelectedRow();
        
        String siglaMoneda_Pago = tblModelRegistros.getValueAt(i, 5).toString().trim();//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 5);
        String sMontoPagar = recuperaMonto(tblModelRegistros.getValueAt(i, 6).toString().trim());//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6));
        
        vMontoMin=FarmaUtility.getDecimalNumber(sMontoPagar);
        
        
        String siglaMoneda_Origen = tblModelRegistros.getValueAt(i, 7).toString().trim();//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 7);
        String sRedPagar_Origen = tblModelRegistros.getValueAt(i, 8).toString().trim();//recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 8));
        
        vMontoMinOrig=FarmaUtility.getDecimalNumber(sRedPagar_Origen);
        
        
        double dTipoCambioOrigen =Double.parseDouble(tblModelRegistros.getValueAt(i, 9).toString().trim());
            //FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));
            //FarmaUtility.getDecimalNumber(tblModelRegistros.getValueAt(i, 9).toString().trim());//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));
            
            strEstadoCuenta = ConstantsRecaudacion.EST_CTA_SOLES;
            
        if (validarCamposPagoPrestamo()) {
            strMonedaPago = obtieneTipoMoneda(siglaMoneda_Pago); //de grilla
            VariablesRecaudacion.vTipoMonedaOrigen = obtieneTipoMoneda(siglaMoneda_Origen); //de grilla
            if (VariablesRecaudacion.vTipoMonedaOrigen.contentEquals(ConstantsCaja.EFECTIVO_DOLARES)) {
                VariablesRecaudacion.vTipoCambioBFP = dTipoCambioOrigen;
            } else {
                VariablesRecaudacion.vTipoCambioBFP = 0.00d;
            }
            String idPrestamo=regeneraIdPrestmo();
            ArrayList<Object> arrayCabecera = new ArrayList<Object>();
            /*0*/arrayCabecera.add(FarmaVariables.vCodGrupoCia);
            /*1*/arrayCabecera.add(FarmaVariables.vCodCia);
            /*2*/arrayCabecera.add(FarmaVariables.vCodLocal);
            /*3*/arrayCabecera.add(idPrestamo); /// 3
            /*4*/arrayCabecera.add(ConstantsRecaudacion.TIPO_REC_FINANCIERO);
            /*5*/arrayCabecera.add(VariablesRecaudacion.vTipoOperacion_BFP); // TIPO PAGO //FarmaLoadCVL.getCVLCode(ConstantsRecaudacion.NOM_HASTABLE_CMB_TIPO_PAGO, cmbTipoPago.getSelectedIndex())
            /*6*/arrayCabecera.add(ConstantsRecaudacion.ESTADO_PENDIENTE); // ESTADO
            /*7*/arrayCabecera.add(strEstadoCuenta); //ESTADO DE CUENTA
            /*8*/arrayCabecera.add(""); //CODIGO DEL CLIENTE
            /*9*/arrayCabecera.add(strMonedaPago); //TIPO DE MONEDA //
            /*10*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto total(moneda original)
            /*11*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto soles
            /*12*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto minimo
            /*13*/arrayCabecera.add(dTipoCambioOrigen); /*VariablesRecaudacion.vTipoCambioVenta*/ //Tipo cambio
            /*14*/arrayCabecera.add(""); //Fecha DE VENCIMIENTO DE RECAUDACION
            /*15*/arrayCabecera.add(""); //NOMBRE DEL CLIENTE
            /*16*/arrayCabecera.add(""); //Fecha Venc Tarj (CMR)
            /*17*/arrayCabecera.add(ConstantsRecaudacion.FECHA_RCD); //FECHA DE CREACION
            /*18*/arrayCabecera.add(FarmaVariables.vIdUsu); // USUARIO CREADOR
            /*19*/arrayCabecera.add(""); //FECHA DE MODIFICACION
            /*20*/arrayCabecera.add(""); //USUARIO MODIFICADOR
            /*21*/arrayCabecera.add(""); //codigo de autorizacion
            /*22*/arrayCabecera.add(""); //mov de caja
            /*23*/arrayCabecera.add(""); //numero de pedido solo valido para VENTA CMR
            /*24*/arrayCabecera.add(""); //tipo producto servicio
            /*25*/arrayCabecera.add(""); //numero de recibo de pago
            /*26*/arrayCabecera.add(idPrestamo); //numero de tarjeta
            /*27*/arrayCabecera.add(siglaMoneda_Pago); //sigla de moneda
            /*28*/arrayCabecera.add(vMontoMin); //monto minimo a pagar
            /*29*/arrayCabecera.add(vMontoMes); //monto mes a pagar
            /*30*/arrayCabecera.add(vMontoTotal); //monto total a pagar
            /*31*/arrayCabecera.add(vMontoMinOrig); //redondeo minimo a pagar
            /*32*/arrayCabecera.add(vMontoMesOrig); //redondeo mes a pagar
            /*33*/arrayCabecera.add(vMontoTotalOrig); //redondeo total a pagar
            /*34*/arrayCabecera.add(strNumDNI); //numero de DNI // 34
                  
            String tmpCodigo =facadeRecaudacion.grabaCabeRecau(arrayCabecera, strNumDNI);//"03935";//
            
            if (!tmpCodigo.equals("")) {
                //ABRE PANTALLA DE COBRO
                VariablesRecaudacion.vVersionBFP = "02";
                VariablesRecaudacion.arrayCabecera=arrayCabecera;
                VariablesRecaudacion.vCodCabRecau=tmpCodigo;
                if (facadeRecaudacion.cobrarRecaudacion(myParentFrame, tmpCodigo, arrayCabecera,
                                                        ConstantsRecaudacion.TIPO_REC_FINANCIERO)) {
                    /*DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                        dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_PAGO);
                        dlgProcesarPagoTerceros.procesarPagoFinanciero("TER1", strNumDNI, "T", nroTarjeta,
                                                                       dCuantoPaga, strMonedaPago, dMontoPagoTotal);
                        dlgProcesarPagoTerceros.mostrar();*/

                    cerrarVentana(true);
                }
            } else {
                FarmaUtility.showMessage(this, "Error al procesar el cobro.", txtDocIdentidad);
            }
            //}
        }
    }

    private String regeneraIdPrestmo() {
        int i = tblTarjetas.getSelectedRow();
        String id="";
        String tipPrestamo = tblModelRegistros.getValueAt(i,0).toString().trim();
        String nroPrestamo = tblModelRegistros.getValueAt(i,2).toString().trim();
        //String fecPrestamo = tblModelRegistros.getValueAt(i,3).toString().trim();
        //fecPrestamo=fecPrestamo.replace("/", "");
        id=tipPrestamo+nroPrestamo;//+fecPrestamo;
        System.out.println("==> "+id);
        return id;
    }

    private void registarPagoDeposito() {
        String strEstadoCuenta = "";
        String strMonedaPago = "";
        String strNumDNI = txtDocIdentidad.getText().trim();
        String montoPagar = txtMontoPagar.getText().substring(2, 3).trim();
        if (montoPagar.equalsIgnoreCase(".")) {
            montoPagar = txtMontoPagar.getText().substring(3).trim();
        } else {
            montoPagar = txtMontoPagar.getText().substring(2).trim();
        }
        vMontoCuota = FarmaUtility.getDecimalNumber(montoPagar);
        VariablesRecaudacion.vMontoPago = String.valueOf(vMontoCuota);
        selecionaTipoOperacion(false);
        int i = tblTarjetas.getSelectedRow();
        String siglaMoneda_Pago = tblModelRegistros.getValueAt(i, 2).toString().trim();//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 5);
        String sMontoPagar = recuperaMonto(tblModelRegistros.getValueAt(i, 3).toString().trim());//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 6));
        
        vMontoMin=FarmaUtility.getDecimalNumber(sMontoPagar);
        
        strEstadoCuenta = ConstantsRecaudacion.EST_CTA_SOLES;
        
        
        String siglaMoneda_Origen = tblModelRegistros.getValueAt(i, 4).toString().trim();//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 7);
        String sRedPagar_Origen = tblModelRegistros.getValueAt(i,5).toString().trim();//recuperaMonto(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 8));
        
        vMontoMinOrig=FarmaUtility.getDecimalNumber(sRedPagar_Origen);
        
        
        double dTipoCambioOrigen =Double.parseDouble(tblModelRegistros.getValueAt(i, 6).toString().trim());
            //FarmaUtility.getDecimalNumber(FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));
            //FarmaUtility.getDecimalNumber(tblModelRegistros.getValueAt(i, 6).toString().trim());//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 9));

        if (validarCamposDepositoCta()) {
            strMonedaPago = obtieneTipoMoneda(siglaMoneda_Pago); //de grilla
            VariablesRecaudacion.vTipoMonedaOrigen = obtieneTipoMoneda(siglaMoneda_Origen); //de grilla
            if (VariablesRecaudacion.vTipoMonedaOrigen.contentEquals(ConstantsCaja.EFECTIVO_DOLARES)) {
                VariablesRecaudacion.vTipoCambioBFP = dTipoCambioOrigen;
            } else {
                VariablesRecaudacion.vTipoCambioBFP = 0.00;
            }
            String idCtaDeposito=tblModelRegistros.getValueAt(i, 0).toString().trim();//FarmaUtility.getValueFieldArrayList(tblModelRegistros.data, i, 0);
            ArrayList<Object> arrayCabecera = new ArrayList<Object>();
            /*0*/arrayCabecera.add(FarmaVariables.vCodGrupoCia);
            /*1*/arrayCabecera.add(FarmaVariables.vCodCia);
            /*2*/arrayCabecera.add(FarmaVariables.vCodLocal);
            /*3*/arrayCabecera.add(idCtaDeposito); /// 3
            /*4*/arrayCabecera.add(ConstantsRecaudacion.TIPO_REC_FINANCIERO);
            /*5*/arrayCabecera.add(VariablesRecaudacion.vTipoOperacion_BFP); // TIPO PAGO //FarmaLoadCVL.getCVLCode(ConstantsRecaudacion.NOM_HASTABLE_CMB_TIPO_PAGO, cmbTipoPago.getSelectedIndex())
            /*6*/arrayCabecera.add(ConstantsRecaudacion.ESTADO_PENDIENTE); // ESTADO
            /*7*/arrayCabecera.add(strEstadoCuenta); //ESTADO DE CUENTA
            /*8*/arrayCabecera.add(""); //CODIGO DEL CLIENTE
            /*9*/arrayCabecera.add(strMonedaPago); //TIPO DE MONEDA //
            /*10*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto total(moneda original)
            /*11*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto soles
            /*12*/arrayCabecera.add(String.valueOf(vMontoCuota)); //Monto minimo
            /*13*/arrayCabecera.add(dTipoCambioOrigen); /*VariablesRecaudacion.vTipoCambioVenta*/ //Tipo cambio
            /*14*/arrayCabecera.add(""); //Fecha DE VENCIMIENTO DE RECAUDACION
            /*15*/arrayCabecera.add(""); //NOMBRE DEL CLIENTE
            /*16*/arrayCabecera.add(""); //Fecha Venc Tarj (CMR)
            /*17*/arrayCabecera.add(ConstantsRecaudacion.FECHA_RCD); //FECHA DE CREACION
            /*18*/arrayCabecera.add(FarmaVariables.vIdUsu); // USUARIO CREADOR
            /*19*/arrayCabecera.add(""); //FECHA DE MODIFICACION
            /*20*/arrayCabecera.add(""); //USUARIO MODIFICADOR
            /*21*/arrayCabecera.add(""); //codigo de autorizacion
            /*22*/arrayCabecera.add(""); //mov de caja
            /*23*/arrayCabecera.add(""); //numero de pedido solo valido para VENTA CMR
            /*24*/arrayCabecera.add(""); //tipo producto servicio
            /*25*/arrayCabecera.add(""); //numero de recibo de pago
            /*26*/arrayCabecera.add(idCtaDeposito); //numero de tarjeta
            /*27*/arrayCabecera.add(siglaMoneda_Pago); //sigla de moneda
            /*28*/arrayCabecera.add(vMontoMin); //monto minimo a pagar
            /*29*/arrayCabecera.add(vMontoMes); //monto mes a pagar
            /*30*/arrayCabecera.add(vMontoTotal); //monto total a pagar
            /*31*/arrayCabecera.add(vMontoMinOrig); //redondeo minimo a pagar
            /*32*/arrayCabecera.add(vMontoMesOrig); //redondeo mes a pagar
            /*33*/arrayCabecera.add(vMontoTotalOrig); //redondeo total a pagar
            /*34*/arrayCabecera.add(strNumDNI); //numero de DNI // 34
                  
            String tmpCodigo =facadeRecaudacion.grabaCabeRecau(arrayCabecera, strNumDNI);//"03935";//
            
            if (!tmpCodigo.equals("")) {
                //ABRE PANTALLA DE COBRO
                VariablesRecaudacion.vVersionBFP = "02";
                VariablesRecaudacion.arrayCabecera=arrayCabecera;
                VariablesRecaudacion.vCodCabRecau=tmpCodigo;
                if (facadeRecaudacion.cobrarRecaudacion(myParentFrame, tmpCodigo, arrayCabecera,
                                                        ConstantsRecaudacion.TIPO_REC_FINANCIERO)) {
                    /*DlgProcesarPagoTerceros dlgProcesarPagoTerceros = new DlgProcesarPagoTerceros(myParentFrame, "", true);
                        dlgProcesarPagoTerceros.setStrIndProc(ConstantsRecaudacion.RCD_IND_PROCESO_PAGO);
                        dlgProcesarPagoTerceros.procesarPagoFinanciero("TER1", strNumDNI, "T", nroTarjeta,
                                                                       dCuantoPaga, strMonedaPago, dMontoPagoTotal);
                        dlgProcesarPagoTerceros.mostrar();*/

                    cerrarVentana(true);
                }
            } else {
                FarmaUtility.showMessage(this, "Error al procesar el cobro.", txtDocIdentidad);
            }
            //}
        }
    }
    
    private void muestraMensaje(String msj, String tipoMsj,String titulo, Object cTxt){
        int nroIcono=0;
        int idMsj=0;
        if(tipoMsj.equalsIgnoreCase("E"))
            idMsj=1;
        else if(tipoMsj.equalsIgnoreCase("Q"))
            idMsj=2;
        else if(tipoMsj.equalsIgnoreCase("W"))
            idMsj=3;
        else if(tipoMsj.equalsIgnoreCase("I"))
            idMsj=4;
        
        switch(idMsj){
            case 1:
            nroIcono=JOptionPane.ERROR_MESSAGE;
            if(titulo==null)
                titulo="Error de sistema";
            break;
            case 2:
            nroIcono=JOptionPane.QUESTION_MESSAGE;
            if(titulo==null)
                titulo="Requerimiento de sistema";
            break;
            case 3:
            nroIcono=JOptionPane.WARNING_MESSAGE;
            if(titulo==null)
                titulo="Advertencia de sistema";
            break;
            default:
            nroIcono=JOptionPane.INFORMATION_MESSAGE;
            if(titulo==null)
                titulo="Mensaje de sistema";
        }
        JOptionPane.showMessageDialog(this,msj,titulo,nroIcono);
        FarmaUtility.moveFocus(cTxt);
    }
}
