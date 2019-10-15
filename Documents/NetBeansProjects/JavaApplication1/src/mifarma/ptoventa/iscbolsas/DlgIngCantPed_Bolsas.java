package mifarma.ptoventa.iscbolsas;

import com.gs.mifarma.componentes.FarmaHora;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JLabelWhite;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.JTextField;

import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.iscbolsas.reference.UtilityISCBolsas;

import mifarma.ptoventa.iscbolsas.reference.VariablesISCBolsas;

import mifarma.ptoventa.ventas.DlgListaProductos;

import mifarma.ptoventa.ventas.reference.DBVentas;
import mifarma.ptoventa.ventas.reference.UtilityVentas;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import oracle.jdeveloper.layout.XYConstraints;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DlgIngCantPed_Bolsas extends JDialog {

    private static final Log logger = LogFactory.getLog(DlgIngCantPed_Bolsas.class);

    private JPanel jContentPane = new JPanel();
    Frame myParentFrame;
    private JPanelTitle pnlTitle1 = new JPanelTitle();
    private JLabelFunction lblEsc = new JLabelFunction();

    private JLabelFunction lblF10 = new JLabelFunction();


    private BorderLayout borderLayout1 = new BorderLayout();

    private JLabelOrange lblSubTotal = new JLabelOrange();
    private JLabelOrange lblISC = new JLabelOrange();
    private JLabelOrange lblTotal = new JLabelOrange();

    private JButtonLabel lblPrecioTMed = new JButtonLabel();
    private JButtonLabel lblPrecioMed = new JButtonLabel();
    private JButtonLabel lblCantidadTMed = new JButtonLabel();

    private JButtonLabel lblPrecioTGran = new JButtonLabel();
    private JButtonLabel lblPrecioGran = new JButtonLabel();
    private JButtonLabel lblCantidadTGran = new JButtonLabel();


    private JTextFieldSanSerif txtPrecioMed = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtPrecioGran = new JTextFieldSanSerif();

    private JButtonLabel lblSubTotalT = new JButtonLabel();
    private JButtonLabel lblSubTotalS = new JButtonLabel();
    private JButtonLabel lblISCT = new JButtonLabel();
    private JButtonLabel lblISCTS = new JButtonLabel();
    private JButtonLabel lblTotalT = new JButtonLabel();
    private JButtonLabel lblTotalTS = new JButtonLabel();

    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JLabelWhite lblStockTMed = new JLabelWhite();


    private JPanelTitle jPanelTitle2 = new JPanelTitle();
    private JLabelWhite lblStockTGran = new JLabelWhite();

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JLabelFunction lblLimpiar = new JLabelFunction();

    private JPanel jPanel4 = new JPanel(); // DMOSQUEIRA 22072019
    private JLabel lblICBPER = new JLabel(); // DMOSQUEIRA 22072019
    private boolean isGrande = false; // DMOSQUEIRA 22072019


    public DlgIngCantPed_Bolsas() {
        this(null, "", false);
    }

    public DlgIngCantPed_Bolsas(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
            FarmaUtility.centrarVentana(this);
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(395, 369));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Ingreso Cantidad de Bolsas");
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jContentPane.setBackground(Color.white);
        jContentPane.setFont(new Font("SansSerif", 0, 11));
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(796, 346));
        pnlTitle1.setBounds(new Rectangle(10, 10, 370, 320));
        pnlTitle1.setBackground(Color.white);
        pnlTitle1.setLayout(null);
        pnlTitle1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        txtPrecioMed.setBounds(new Rectangle(250, 10, 65, 20));
        txtPrecioMed.setFont(new Font("SansSerif", 0, 13));
        txtPrecioMed.setText("0");
        txtPrecioMed.setHorizontalAlignment(JTextField.RIGHT);
        txtPrecioMed.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                /*if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    FarmaUtility.moveFocus(txtPrecioGran);
                }

                chkKeyPressed(e);*/
                chkKeyPressed_txtPrecioMed(e);
            }

            public void keyTyped(KeyEvent e) {
                chkKeyTyped_txtPrecioMed(e);
            }

            public void keyReleased(KeyEvent e) {
                chkKeyReleased_txtPrecioMed(e);
            }

        });
        txtPrecioMed.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            }
        });
        txtPrecioMed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        txtPrecioGran.setBounds(new Rectangle(250, 10, 65, 20));
        txtPrecioGran.setFont(new Font("SansSerif", 0, 13));
        txtPrecioGran.setText("0");
        txtPrecioGran.setHorizontalAlignment(JTextField.RIGHT);
        txtPrecioGran.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                /*if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    FarmaUtility.moveFocus(txtPrecioMed);
                }

                chkKeyPressed(e);*/
                chkKeyPressed_txtPrecioGran(e);
            }

            public void keyTyped(KeyEvent e) {
                chkKeyTyped_txtPrecioGran(e);
            }

            public void keyReleased(KeyEvent e) {
                chkKeyReleased_txtPrecioGran(e);
            }
        });

        txtPrecioGran.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });
        lblEsc.setText("[ESC] Cerrar");
        lblEsc.setBounds(new Rectangle(10, 265, 85, 35));
        lblF10.setText("[F11] Aceptar");
        lblF10.setBounds(new Rectangle(265, 265, 95, 35));
        lblSubTotal.setText("9,990.00");
        lblSubTotal.setBounds(new Rectangle(270, 10, 55, 20));
        lblSubTotal.setForeground(SystemColor.window);
        lblSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSubTotal.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblSubTotal.setFont(new Font("SansSerif", 1, 13));

        lblISC.setText("9,990.00");
        lblISC.setBounds(new Rectangle(270, 30, 55, 20));
        lblISC.setForeground(SystemColor.window);
        lblISC.setHorizontalAlignment(SwingConstants.RIGHT);
        lblISC.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblISC.setFont(new Font("SansSerif", 1, 13));

        lblTotal.setText("9,990.00");
        lblTotal.setBounds(new Rectangle(270, 50, 55, 20));
        lblTotal.setForeground(new Color(231, 0, 0));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblTotal.setFont(new Font("SansSerif", 1, 13));

        lblPrecioTMed.setText("Precio Unit: ");
        lblPrecioTMed.setBounds(new Rectangle(35, 10, 80, 20));
        lblPrecioTMed.setForeground(new Color(32, 105, 29));
        lblPrecioTMed.setActionCommand("Precio Unit: ");

        lblPrecioTMed.setFont(new Font("SansSerif", 1, 13));
        lblPrecioMed.setText("S/. 0.10");
        lblPrecioMed.setBounds(new Rectangle(115, 10, 50, 20));
        lblPrecioMed.setForeground(new Color(32, 105, 29));
        lblPrecioMed.setActionCommand("S/. 0.10");

        lblPrecioMed.setFont(new Font("SansSerif", 1, 13));
        lblCantidadTMed.setText("Cantidad : ");
        lblCantidadTMed.setBounds(new Rectangle(180, 10, 70, 20));
        lblCantidadTMed.setForeground(new Color(32, 105, 29));
        lblCantidadTMed.setActionCommand("Cantidad : ");


        lblCantidadTMed.setFont(new Font("SansSerif", 1, 13));
        lblPrecioTGran.setText("Precio Unit: ");
        lblPrecioTGran.setBounds(new Rectangle(35, 10, 80, 20));
        lblPrecioTGran.setForeground(new Color(32, 105, 29));
        lblPrecioTGran.setActionCommand("Precio Unit: ");

        lblPrecioTGran.setFont(new Font("SansSerif", 1, 13));
        lblPrecioGran.setText("S/. 0.20");
        lblPrecioGran.setBounds(new Rectangle(115, 10, 50, 20));
        lblPrecioGran.setForeground(new Color(32, 105, 29));
        lblPrecioGran.setActionCommand("S/. 0.20");

        lblPrecioGran.setFont(new Font("SansSerif", 1, 13));
        lblCantidadTGran.setText("Cantidad : ");
        lblCantidadTGran.setBounds(new Rectangle(180, 10, 70, 20));
        lblCantidadTGran.setForeground(new Color(32, 105, 29));
        lblCantidadTGran.setActionCommand("Cantidad : ");


        lblCantidadTGran.setFont(new Font("SansSerif", 1, 13));
        jPanel1.add(lblPrecioTMed, null);
        jPanel1.add(lblPrecioMed, null);
        jPanel1.add(lblCantidadTMed, null);
        jPanel1.add(txtPrecioMed, null);
        jPanel3.add(lblSubTotalT, null);
        jPanel3.add(lblSubTotalS, null);
        jPanel3.add(lblISCT, null);
        jPanel3.add(lblISCTS, null);
        jPanel3.add(lblTotalT, null);
        jPanel3.add(lblTotalTS, null);
        jPanel3.add(lblSubTotal, null);
        jPanel3.add(lblISC, null);

        jPanel3.add(lblTotal, null);
        jPanel3.add(jPanel4, null);
        pnlTitle1.add(jPanel1, null);
        pnlTitle1.add(jPanel2, null);

        pnlTitle1.add(jPanel3, null);

        pnlTitle1.add(lblF10, null);
        pnlTitle1.add(lblLimpiar, null);
        pnlTitle1.add(lblEsc, null);
        jPanelTitle1.add(lblStockTMed, null);
        pnlTitle1.add(jPanelTitle1, null);
        jPanelTitle2.add(lblStockTGran, null);
        pnlTitle1.add(jPanelTitle2, null);
        pnlTitle1.add(lblICBPER, null);
        lblSubTotalT.setText("Sub Total:");
        lblSubTotalT.setBounds(new Rectangle(180, 10, 65, 20));
        lblSubTotalT.setFont(new Font("SansSerif", 1, 13));

        lblSubTotalS.setText("S/. ");
        lblSubTotalS.setBounds(new Rectangle(260, 10, 20, 20));
        lblSubTotalS.setFont(new Font("SansSerif", 1, 12));

        lblISCT.setText("ICBPER:");
        lblISCT.setBounds(new Rectangle(180, 30, 65, 20));
        lblISCT.setFont(new Font("SansSerif", 1, 13));

        lblISCTS.setText("S/. ");
        lblISCTS.setBounds(new Rectangle(260, 30, 20, 20));
        lblISCTS.setFont(new Font("SansSerif", 1, 12));

        lblTotalT.setText("TOTAL:");
        lblTotalT.setBounds(new Rectangle(180, 50, 65, 20));
        lblTotalT.setFont(new Font("SansSerif", 1, 13));
        lblTotalT.setForeground(new Color(231, 0, 0));

        lblTotalTS.setText("S/. ");
        lblTotalTS.setBounds(new Rectangle(260, 50, 20, 20));
        lblTotalTS.setFont(new Font("SansSerif", 1, 12));
        lblTotalTS.setForeground(new Color(231, 0, 0));

        jPanelTitle1.setBounds(new Rectangle(10, 15, 350, 25));
        lblStockTMed.setText("Bolsas Medianas");
        lblStockTMed.setBounds(new Rectangle(5, 0, 125, 25));

        lblStockTMed.setFont(new Font("SansSerif", 1, 13));
        jPanelTitle2.setBounds(new Rectangle(10, 95, 350, 25));
        lblStockTGran.setText("Bolsas Grandes");
        lblStockTGran.setBounds(new Rectangle(5, 0, 125, 25));


        /*jPanel3*/
        lblStockTGran.setFont(new Font("SansSerif", 1, 13));
        jPanel2.add(lblPrecioTGran, null);
        jPanel2.add(lblPrecioGran, null);
        jPanel2.add(lblCantidadTGran, null);
        jPanel2.add(txtPrecioGran, null);
        jPanel2.add(lblPrecioTGran, null);
        jPanel2.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        jPanel2.setForeground(new Color(214, 107, 0));
        jPanel2.setBackground(SystemColor.window);
        /*jPanel3*/

        /*jPanel2*/
        jPanel1.setBounds(new Rectangle(10, 40, 350, 45));
        jPanel1.setLayout(null);
        jPanel1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        jPanel1.setForeground(new Color(214, 107, 0));
        jPanel1.setBackground(SystemColor.window);
        /*jPanel2*/

        /*jPanel3*/
        jPanel2.setBounds(new Rectangle(10, 120, 350, 45));
        jPanel2.setLayout(null);
        /*jPanel3*/

        /*jPanel1*/
        jPanel3.setBounds(new Rectangle(10, 175, 350, 80));
        jPanel3.setLayout(null);
        jPanel3.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        jPanel3.setBackground(new Color(0, 132, 0));
        /*jPanel1*/

        lblLimpiar.setText("[F9] Limpiar");
        lblLimpiar.setBounds(new Rectangle(135, 265, 90, 35));

        lblICBPER.setText("* ICBPER = IMPUESTO AL CONSUMO DE BOLSAS PER\u00DA");
        lblICBPER.setBounds(new Rectangle(10, 253, 275, 15));
        lblICBPER.setFont(new Font("Tahoma", 0, 8));
        lblICBPER.setAlignmentY((float)0.0);

        jPanel4.setBounds(new Rectangle(175, 50, 155, 20));
        jContentPane.add(pnlTitle1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void initialize() {
        initProductosISC();
        txtPrecioMed.setText("0");
        txtPrecioGran.setText("0");
        calcularTotales();
    }

    private void initProductosISC() {
        try {
            VariablesISCBolsas.vArrayList_ProductosISC = new ArrayList();
            UtilityISCBolsas.obtenerProductosISC(VariablesISCBolsas.vArrayList_ProductosISC);

            /*Setea Precio*/

            String precioMed = "";
            String precioGran = "";

            for (int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC.size(); i++) {

                if (FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i,
                                                        3).trim().equals(VariablesISCBolsas.vTipoBolsaMediano)) {
                    
                    // INI: JRODRIGUEZ 20190731
                    double vprecioMed = DBVentas.getSubTotalBolsa(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, 3));
                    if(vprecioMed==0){
                        precioMed = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, 2);
                    }else{
                        precioMed = FarmaUtility.formatNumber(vprecioMed);
                    }
                    // FIN: JRODRIGUEZ 20190731
                } else {
                    precioGran = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, 2);
                }

            }


            lblPrecioMed.setText(precioMed);
            lblPrecioGran.setText(precioGran);
            /*Setea Precio*/

            if (VariablesISCBolsas.vArrayList_ProductosISC.isEmpty()) {
                throw new Exception("No se encontró productos ISC.");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            FarmaUtility.showMessage(this, ex.getMessage(), null);
        }
    }

    private void agregarProductos() {
        String cadenactdMediano = txtPrecioMed.getText().trim().isEmpty() ? "0" : txtPrecioMed.getText().trim();
        String cadenactdGrande = txtPrecioGran.getText().trim().isEmpty() ? "0" : txtPrecioGran.getText().trim();

        int cantMediano = Integer.parseInt(cadenactdMediano);
        int cantGrande = Integer.parseInt(cadenactdGrande);

        VariablesISCBolsas.vArrayList_ProductosISC_Temp = new ArrayList();
        ArrayList tempProductosISC = null;

        for (int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC.size(); i++) {
            tempProductosISC = new ArrayList();
            String codigoProducto =
                FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, 0);
            String costoISC = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, 1);
            String tipoBolsa = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, 3);
            String precioUnitario =
                FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC, i, 2);


            String tempCantidad = "0";
            if (tipoBolsa.trim().equals(VariablesISCBolsas.vTipoBolsaMediano)) {
                tempCantidad = cadenactdMediano;
            } else if (tipoBolsa.trim().equals(VariablesISCBolsas.vTipoBolsaGrande)) {
                tempCantidad = cadenactdGrande;
            }

            if (FarmaUtility.getDecimalNumber(tempCantidad) > 0) {
                double subTotal =
                    FarmaUtility.getDecimalNumber(tempCantidad) * FarmaUtility.getDecimalNumber(precioUnitario);
                double totalISC =
                    FarmaUtility.getDecimalNumber(tempCantidad) * FarmaUtility.getDecimalNumber(costoISC);
                double total = subTotal + totalISC;

                tempProductosISC.add(codigoProducto); //codigoProducto
                tempProductosISC.add(costoISC); //costoISC
                tempProductosISC.add(precioUnitario); //precioUnitario
                tempProductosISC.add(tipoBolsa); //tipoBolsa
                tempProductosISC.add(tempCantidad); //cantidad
                tempProductosISC.add(FarmaUtility.formatNumber(subTotal)); //subTotal
                tempProductosISC.add(FarmaUtility.formatNumber(totalISC)); //totalISC
                tempProductosISC.add(FarmaUtility.formatNumber(total)); //total

                VariablesISCBolsas.vArrayList_ProductosISC_Temp.add(tempProductosISC);
            }
        }
    }


    private void calcularTotales() {

        agregarProductos(); // Agregar a array Temporal para calcular

        double tempSubTotal = 0.00;
        double tempTotalISC = 0.00;
        double tempTotal = 0.00;

        for (int i = 0; i < VariablesISCBolsas.vArrayList_ProductosISC_Temp.size(); i++) {
            try {
                /*String codidoProducto = EckerdUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 0);
                String costoISC = EckerdUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 1);
                String precioUnitario = EckerdUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 2);
                String tipoBolsa = EckerdUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 3);
                String cantidad = EckerdUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 4);*/

                // INI: JRODRIGUEZ 20190731
                String subTotal = "";
                String totalISC = "";
                String total = "";
                double vsubTotal = DBVentas.getSubTotalBolsa(FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 3));
                if(vsubTotal==0){
                    subTotal = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 5);
                    totalISC = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 6);
                    total = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 7);
                }else{
                    subTotal = FarmaUtility.formatNumber(vsubTotal);
                    totalISC = FarmaUtility.getValueFieldArrayList(VariablesISCBolsas.vArrayList_ProductosISC_Temp, i, 6);
                    double vtotalISC = FarmaUtility.getDecimalNumber(totalISC);
                    double vtotal = vsubTotal + vtotalISC;
                    total = FarmaUtility.formatNumber(vtotal);
                }
                // FIN: JRODRIGUEZ 20190731

                tempSubTotal += FarmaUtility.getDecimalNumber(subTotal);
                tempTotalISC += FarmaUtility.getDecimalNumber(totalISC);
                tempTotal += FarmaUtility.getDecimalNumber(total);

            } catch (Exception ex) {
                logger.error(ex);
            }
        }

        lblSubTotal.setText(FarmaUtility.formatNumber(tempSubTotal));
        lblISC.setText(FarmaUtility.formatNumber(tempTotalISC));
        lblTotal.setText(FarmaUtility.formatNumber(tempTotal));
    }

    //Agregar Producto a venta

    private void agregarProducto() {
        calcularTotales();
        logger.debug("vArrayList_ProductosISC_Temp: " + VariablesISCBolsas.vArrayList_ProductosISC_Temp);
        //String mensaje = UtilityISCBolsas.generarArrayProductosISCPedidos();

        if (VariablesISCBolsas.vArrayList_ProductosISC_Temp.size() > 0) {
            DlgListaProductos dlgListaProductos = new DlgListaProductos(myParentFrame, "", true);
            dlgListaProductos.procesaBolsa();
        }

        //if(!mensaje.isEmpty()){
        //FarmaUtility.showMessage(this, mensaje, "Mensaje de Sistema");
        //}

        closeWindow(true);
    }

    private void closeWindow(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.dispose();
        this.setVisible(false);
    }

    private void this_windowOpened(WindowEvent e) {
        if (UtilityISCBolsas.pblTabGral_ICBPer()) {
            if (isGrande) {
                FarmaUtility.moveFocus(txtPrecioGran);
            } else {
                FarmaUtility.moveFocus(txtPrecioMed);
            }
        } else {
            agregarProducto();
        }

    }

    private void this_windowClosing(WindowEvent e) {

    }

    private void chkKeyPressed_txtPrecioMed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            agregarProducto();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB ||
                   e.getKeyCode() == KeyEvent.VK_DOWN) {
            FarmaUtility.moveFocus(txtPrecioGran);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closeWindow(false);
        }
    }

    private void chkKeyTyped_txtPrecioMed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (!Character.isDigit(keyChar)) {
            e.consume();
        }
    }

    private void chkKeyReleased_txtPrecioMed(KeyEvent e) {
        //INI ZVILLAFUERTE 20190806
        String codigoProdMed = UtilityISCBolsas.obtenerCodigoProducto(VariablesISCBolsas.vTipoBolsaMediano);
        String cantidad = txtPrecioMed.getText().trim();
        validaStock(codigoProdMed, cantidad, txtPrecioMed);
        //FIN ZVILLAFUERTE 20190806
        calcularTotales();
    }

    private void chkKeyPressed_txtPrecioGran(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            agregarProducto();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB ||
                   e.getKeyCode() == KeyEvent.VK_DOWN) {
            FarmaUtility.moveFocus(txtPrecioMed);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closeWindow(false);
        }
    }

    private void chkKeyTyped_txtPrecioGran(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (!Character.isDigit(keyChar)) {
            e.consume();
        }
    }

    private void chkKeyReleased_txtPrecioGran(KeyEvent e) {
        //INI ZVILLAFUERTE 20190806
        String codigoProdGran = UtilityISCBolsas.obtenerCodigoProducto(VariablesISCBolsas.vTipoBolsaGrande);
        String cantidad = txtPrecioGran.getText().trim();
        validaStock(codigoProdGran, cantidad, txtPrecioGran);
        //FIN ZVILLAFUERTE 20190806
        calcularTotales();
    }
    
    //INI ZVILLAFUERTE 20190806
    private void validaStock(String pCodProd, String pCantIngresada, Object pObjectFocus){
        String stkProd = validaStockActual(pCodProd, pCantIngresada ,pObjectFocus);
        
        if (FarmaUtility.getDecimalNumber(pCantIngresada) > FarmaUtility.getDecimalNumber(stkProd)) {
            ((JTextField) pObjectFocus).setText("0");
            
            FarmaUtility.showMessage(this, "La cantidad ingresada no puede ser mayor al Stock del Producto.",
                                                        pObjectFocus);
        }
    }
    
    private String validaStockActual(String pCodProd, String pCantIngresada, Object pObjectFocus){
        
        ArrayList myArray = new ArrayList();

        UtilityISCBolsas.obtieneInfoProdEnArrayList(this, myArray, pObjectFocus, pCodProd);
        logger.debug("Tamaño en muestra info" + myArray.size());
        String stkProd;
        
        if (myArray.size() == 1) {
            stkProd = ((String)((ArrayList)myArray.get(0)).get(0)).trim();   
        }else{
            stkProd = "";
        }
        
        return stkProd;
    }
    //FIN ZVILLAFUERTE 20190806

    public void setTxtPrecioMed(String qBolsaMed) {
        txtPrecioMed.setText(qBolsaMed);
    }

    public void setTxtPrecioGran(String qBolsaGran) {
        txtPrecioGran.setText(qBolsaGran);
    }

    public boolean getIsGrande() {
        return isGrande;
    }

    public void setIsGrande(boolean isGrande) {
        this.isGrande = isGrande;
    }
}
