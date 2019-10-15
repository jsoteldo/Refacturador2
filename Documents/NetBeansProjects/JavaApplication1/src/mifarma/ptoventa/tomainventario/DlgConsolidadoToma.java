package mifarma.ptoventa.tomainventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import java.util.ArrayList;

import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgConsolidadoToma extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgConsolidadoToma.class);

    Frame parentFrame;
    private int x;
    private int y;
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private JLabelOrange lblTotalItemsTomaT = new JLabelOrange();
    private JLabelOrange lblTotalItemsInventaT = new JLabelOrange();
    private JLabelOrange lblInforamcion = new JLabelOrange();
    private JLabelOrange lblSobranteT = new JLabelOrange();
    private JLabelOrange lblFaltanteT = new JLabelOrange();
    private JLabelOrange lblDiferenciaT = new JLabelOrange();
    private JLabelOrange lblTotalItemsToma = new JLabelOrange();
    private JLabelOrange lblTotalItemsInventa = new JLabelOrange();
    private JLabelOrange lblDiferencia = new JLabelOrange();
    private JLabelOrange lblFaltante = new JLabelOrange();
    private JLabelOrange lblSobrante = new JLabelOrange();
    private JLabelOrange lblNumeroToma = new JLabelOrange();
    private JButtonLabel btnNumeroToma = new JButtonLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JSeparator jSeparator2 = new JSeparator();
    private GridLayout gridLayout2 = new GridLayout();
    private JPanel pnlCabResumenInv = new JPanel();
    private JPanel pnlButton = new JPanel();
    private JButton btnCerrar = new JButton();
    private JLabel lblTituloCab = new JLabel();
    private JLabelOrange lblTotalDist = new JLabelOrange();
    private JLabelOrange lblTotalDistor = new JLabelOrange();
    ArrayList lista = new ArrayList();
    ArrayList listaInVal = new ArrayList();
    private JLabelOrange jLabelOrange1 = new JLabelOrange();
    private JLabelOrange jLabelOrange2 = new JLabelOrange();
    private JLabelOrange lblstoValIni = new JLabelOrange();
    private JLabelOrange lblstoValFin = new JLabelOrange();

    private double totalInicial = 0, totalFinal = 0;
    private boolean isHistorico = false;

    public DlgConsolidadoToma() {
        this(null, "", false, null);
    }

    public DlgConsolidadoToma(Frame parent, String title, boolean modal, String estado) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            initialize();
            this.lblTituloCab.setText(title);
            inventarioValorizados(estado);
            obtieneInformacionValorizada(estado);
        } catch (Exception e) {
            log.error("", e);
        }

    }
    
    public DlgConsolidadoToma(Frame parent, String title, boolean modal, String estado, boolean isHistorico) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            initialize();
            this.lblTituloCab.setText(title);
            this.isHistorico = isHistorico;
            inventarioValorizados(estado);
            obtieneInformacionValorizada(estado);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setUndecorated(true);
        this.setSize(new Dimension(355, 375));
        this.getContentPane().setLayout(null);
        this.setTitle("Consolidado de la Toma");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanelWhite1.setBounds(new Rectangle(0, 0, 355, 375));
        jPanelWhite1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        lblTotalItemsTomaT.setText("Total Items de la Toma :");
        lblTotalItemsTomaT.setBounds(new Rectangle(20, 85, 150, 20));
        lblTotalItemsTomaT.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblTotalItemsTomaT_keyPressed(e);
            }
        });
        lblTotalItemsInventaT.setText("Total Items Inventariados :");
        lblTotalItemsInventaT.setBounds(new Rectangle(20, 120, 150, 20));
        lblInforamcion.setText("Informacion Valorizada :");
        lblInforamcion.setBounds(new Rectangle(25, 185, 150, 20));
        lblSobranteT.setText("Sobrante :     S/");
        lblSobranteT.setBounds(new Rectangle(85, 215, 90, 15));
        lblFaltanteT.setText("Faltante :       S/");
        lblFaltanteT.setBounds(new Rectangle(85, 240, 90, 15));
        lblDiferenciaT.setText("Diferencia :    S/");
        lblDiferenciaT.setBounds(new Rectangle(85, 265, 90, 15));
        lblTotalItemsToma.setBounds(new Rectangle(180, 85, 125, 20));
        lblTotalItemsToma.setForeground(SystemColor.windowText);
        lblTotalItemsToma.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalItemsInventa.setBounds(new Rectangle(180, 120, 125, 20));
        lblTotalItemsInventa.setForeground(SystemColor.windowText);
        lblTotalItemsInventa.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDiferencia.setBounds(new Rectangle(185, 265, 125, 15));
        lblDiferencia.setForeground(SystemColor.windowText);
        lblDiferencia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFaltante.setBounds(new Rectangle(185, 240, 125, 15));
        lblFaltante.setForeground(SystemColor.windowText);
        lblFaltante.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSobrante.setBounds(new Rectangle(185, 215, 125, 15));
        lblSobrante.setForeground(SystemColor.windowText);
        lblSobrante.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNumeroToma.setBounds(new Rectangle(95, 55, 145, 15));
        lblNumeroToma.setForeground(new Color(43, 141, 39));
        btnNumeroToma.setText("Toma Nº :");
        btnNumeroToma.setBounds(new Rectangle(20, 55, 75, 15));
        btnNumeroToma.setForeground(SystemColor.windowText);
        btnNumeroToma.setMnemonic('t');
        btnNumeroToma.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnNumeroToma_keyPressed(e);
            }
        });
        btnNumeroToma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNumeroToma_actionPerformed(e);
            }
        });
        jSeparator1.setBounds(new Rectangle(20, 75, 285, 2));
        jSeparator1.setForeground(new Color(198, 198, 198));
        jSeparator2.setBounds(new Rectangle(25, 295, 285, 2));
        jSeparator2.setForeground(new Color(198, 198, 198));
        pnlCabResumenInv.setBounds(new Rectangle(0, 0, 355, 30));
        pnlCabResumenInv.setBackground(new Color(255, 130, 14));
        pnlCabResumenInv.setLayout(null);
        pnlCabResumenInv.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        pnlCabResumenInv.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                pnlCabResumenInv_mouseDragged(e);
            }
        });
        pnlCabResumenInv.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pnlCabResumenInv_mousePressed(e);
            }
        });
        pnlButton.setBounds(new Rectangle(295, 0, 45, 25));
        pnlButton.setLayout(null);
        pnlButton.setBackground(new Color(254, 6, 38));
        btnCerrar.setText("X");
        btnCerrar.setBounds(new Rectangle(0, 0, 45, 25));
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCerrar.setForeground(SystemColor.window);
        btnCerrar.setFont(new Font("Tahoma", 1, 15));
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCerrar_actionPerformed(e);
            }
        });
        btnCerrar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnCerrar_keyPressed(e);
            }
        });
        lblTituloCab.setBackground(SystemColor.window);
        lblTituloCab.setForeground(SystemColor.window);
        lblTituloCab.setBounds(new Rectangle(15, 0, 145, 30));
        lblTotalDist.setText("Total items Distorsionados :");
        lblTotalDist.setBounds(new Rectangle(20, 155, 165, 15));
        lblTotalDistor.setBounds(new Rectangle(185, 150, 120, 25));
        lblTotalDistor.setForeground(SystemColor.windowText);
        lblTotalDistor.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelOrange1.setText("Stock valorizado inicial:");
        jLabelOrange1.setBounds(new Rectangle(25, 310, 135, 15));
        jLabelOrange2.setText("Stock valorizado final:");
        jLabelOrange2.setBounds(new Rectangle(25, 340, 130, 15));
        lblstoValIni.setBounds(new Rectangle(185, 310, 125, 15));
        lblstoValIni.setHorizontalAlignment(SwingConstants.RIGHT);
        lblstoValIni.setForeground(SystemColor.windowText);
        lblstoValFin.setBounds(new Rectangle(185, 340, 125, 15));
        lblstoValFin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblstoValFin.setForeground(SystemColor.windowText);
        jPanelWhite1.add(lblstoValFin, null);
        jPanelWhite1.add(lblstoValIni, null);
        jPanelWhite1.add(jLabelOrange2, null);
        jPanelWhite1.add(jLabelOrange1, null);
        jPanelWhite1.add(lblTotalDistor, null);
        jPanelWhite1.add(lblTotalDist, null);
        jPanelWhite1.add(jSeparator2, null);
        jPanelWhite1.add(jSeparator1, null);
        jPanelWhite1.add(lblSobrante, null);
        jPanelWhite1.add(lblFaltante, null);
        jPanelWhite1.add(lblDiferencia, null);
        jPanelWhite1.add(lblTotalItemsInventa, null);
        jPanelWhite1.add(lblTotalItemsToma, null);
        jPanelWhite1.add(lblDiferenciaT, null);
        jPanelWhite1.add(lblFaltanteT, null);
        jPanelWhite1.add(lblSobranteT, null);
        jPanelWhite1.add(lblInforamcion, null);
        jPanelWhite1.add(lblTotalItemsInventaT, null);
        jPanelWhite1.add(lblTotalItemsTomaT, null);
        jPanelWhite1.add(btnNumeroToma, null);
        jPanelWhite1.add(lblNumeroToma, null);
        pnlButton.add(btnCerrar, null);
        pnlCabResumenInv.add(lblTituloCab, null);
        pnlCabResumenInv.add(pnlButton, null);
        this.getContentPane().add(pnlCabResumenInv, null);
        this.getContentPane().add(jPanelWhite1, null);
    }

    // **************************************************************************
    // Método "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariables.vAceptar = false;

    }


    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        obtieneTotalesToma();
        calculaDiferencia();
        lblNumeroToma.setText(VariablesTomaInv.vSecToma);
        FarmaUtility.moveFocus(btnNumeroToma);
    }

    private void obtieneTotalesToma() {

        try {
            VariablesTomaInv.vObtieneTotales.clear();
            VariablesTomaInv.vObtieneTotales = DBTomaInv.obtieneTotalItemsToma(VariablesTomaInv.vSecToma);
            VariablesTomaInv.vTotalItems =
                    ((String)((ArrayList)VariablesTomaInv.vObtieneTotales.get(0)).get(0)).trim();
            VariablesTomaInv.vTotalTomados =
                    ((String)((ArrayList)VariablesTomaInv.vObtieneTotales.get(0)).get(1)).trim();

            VariablesTomaInv.vDiferenciasXToma = false;
            
            if (isHistorico) {
                DBTomaInv.getListaProdsDiferenciasXEstado(lista, "", VariablesTomaInv.vCodEstadoToma);
            } else {
                DBTomaInv.getListaProdsDiferenciasTotales(lista, "");
            }
            
            // DBTomaInv.getListaProdsDiferenciasTotales(lista, "");
            int tdist = lista.size();
            lblTotalDistor.setText(String.valueOf(tdist));
            lblTotalItemsToma.setText(VariablesTomaInv.vTotalItems);
            lblTotalItemsInventa.setText(VariablesTomaInv.vTotalTomados);
        } catch (SQLException sql) {
            VariablesTomaInv.vObtieneTotales = new ArrayList();
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener información de los totales : \n" +
                    sql.getMessage(), null);
        }
    }
    //SLEYVA-INICIO-2019

    private void obtieneInformacionValorizada(String tipoPrecio) {
        try {
            ArrayList lista = new ArrayList();
            lista.clear();
            DBTomaInv.listaDiferenciasConsolidadoExport(lista,
                                                        VariablesTomaInv.vSecToma);
            Double faltFinal = 0.0;
            Double sobrFinal = 0.0;
            
            for(int i=0;i<lista.size();i++){
                
                
                    if (tipoPrecio.equals("P")) {
                    
                        String preCosto = (String)((ArrayList)lista.get(i)).get(9);
                        preCosto =preCosto.replace(",", "");
                        Double vPreCosto = Double.valueOf(preCosto);
                    
                        if(vPreCosto>0){
                                sobrFinal = sobrFinal + vPreCosto;
                            } 
                        
                        if(vPreCosto<0){
                                faltFinal = faltFinal + vPreCosto;
                            } 
                        
                        
                        
                        
                    } else {
                        String preVenta = (String)((ArrayList)lista.get(i)).get(11);
                        preVenta =preVenta.replace(",", "");
                        Double vpreVenta = Double.valueOf(preVenta);
                        
                        if(vpreVenta>0){
                                sobrFinal = sobrFinal + vpreVenta;
                            } 
                        
                        if(vpreVenta<0){
                                faltFinal = faltFinal + vpreVenta;
                            } 
                        
                    }
                
                }
            
            DecimalFormat df = new DecimalFormat("###,###,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
            VariablesTomaInv.vFaltante = df.format(faltFinal); 
            VariablesTomaInv.vSobrante  = df.format(sobrFinal); 
            
            lblSobrante.setText(VariablesTomaInv.vSobrante);
            lblFaltante.setText(VariablesTomaInv.vFaltante);
        } catch (SQLException sql) {
            lista = new ArrayList();
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener información valorizada : \n" +
                    sql.getMessage(), null);
        }

    }


    private void inventarioValorizados(String tipoPrecio) {

        try {
            DBTomaInv.getResumenValorizadoInventario(listaInVal);
            //double totalInicial = 0, totalFinal = 0;
            for (int i = 0; i < listaInVal.size(); i++) {
                double totalIni = 0;
                double totalFin = 0;

                String caEnteroContado = ((String)((ArrayList)listaInVal.get(i)).get(0)).trim();
                caEnteroContado = caEnteroContado.trim().equals("") ? "0" : caEnteroContado;
                String caFraccionContado = ((String)((ArrayList)listaInVal.get(i)).get(1)).trim();
                caFraccionContado = caFraccionContado.trim().equals("") ? "0" : caFraccionContado;
                String vaPrecioPromedio = ((String)((ArrayList)listaInVal.get(i)).get(2)).trim();
                String inFraccion = ((String)((ArrayList)listaInVal.get(i)).get(3)).trim();
                String caEnteroDiponible = ((String)((ArrayList)listaInVal.get(i)).get(4)).trim();
                String caFraccionDiponible = ((String)((ArrayList)listaInVal.get(i)).get(5)).trim();
                String vaFraccion = ((String)((ArrayList)listaInVal.get(i)).get(6)).trim();
                String vaPrecioVenta = ((String)((ArrayList)listaInVal.get(i)).get(7)).trim();
                String precioProducto;
                if (tipoPrecio.equals("P")) {
                    precioProducto = vaPrecioPromedio;
                } else {
                    precioProducto = vaPrecioVenta;
                }
                if (inFraccion.equals("S")) {
                    totalIni =
                            FarmaUtility.trunc(caEnteroDiponible) * FarmaUtility.trunc(vaFraccion) * getDecimalNumber(precioProducto,
                                                                                                                      5);
                    totalFin =
                            FarmaUtility.trunc(caEnteroContado) * FarmaUtility.trunc(vaFraccion) * getDecimalNumber(precioProducto,
                                                                                                                    5);

                    totalIni += FarmaUtility.trunc(caFraccionDiponible.trim()) * getDecimalNumber(precioProducto, 5);
                    totalFin += FarmaUtility.trunc(caFraccionContado.trim()) * getDecimalNumber(precioProducto, 5);
                } else {
                    totalIni = FarmaUtility.trunc(caEnteroDiponible) * getDecimalNumber(precioProducto, 5);
                    totalFin = FarmaUtility.trunc(caEnteroContado) * getDecimalNumber(precioProducto, 5);
                }
                totalInicial = totalInicial + totalIni;
                //totalFinal = totalFinal + totalFin;
            }
            
            lblstoValIni.setText(FarmaUtility.formatNumber(totalInicial, 2));
            //lblstoValFin.setText(FarmaUtility.formatNumber(totalFinal, 2));
        } catch (SQLException e) {
            log.error("Se produjo un error al iniciar toma consolidado", e);

        }
    }


    public static double getDecimalNumber(String pDecimal, int pDecimales) {
        double decimalNumber = 0.00;
        try {
            DecimalFormat formatDecimal = new DecimalFormat("###,##0." + caracterIzquierda("", pDecimales, "0"));
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();

            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            formatDecimal.setDecimalFormatSymbols(symbols);
            decimalNumber = formatDecimal.parse(pDecimal).doubleValue();
        } catch (ParseException errParse) {
            log.error("No se puede parsear el valor " + pDecimal + " con " + pDecimales + "decimales");
        }
        return decimalNumber;
    }


    public static String caracterIzquierda(int parmint, int parmLen, String parmCaracter) {
        return caracterIzquierda(String.valueOf(parmint), parmLen, parmCaracter);
    }


    public static String caracterIzquierda(String parmString, int parmLen, String parmCaracter) {

        String tempString = parmString;

        if (tempString.length() > parmLen)
            tempString = tempString.substring(tempString.length() - parmLen, tempString.length());
        else {
            while (tempString.length() < parmLen)
                tempString = parmCaracter + tempString;
        }

        return tempString;

    }
    //SLEYVA-FIN-2019

    double cantDifeGlobalTotal = 0;
    private void calculaDiferencia() {
        double diferencia = 0;
        double sobrante = 0;
        double faltante = 0;
        sobrante = FarmaUtility.getDecimalNumber(VariablesTomaInv.vSobrante);
        faltante = FarmaUtility.getDecimalNumber(VariablesTomaInv.vFaltante);
        diferencia = sobrante + faltante;
        totalFinal = totalInicial + diferencia;
        lblstoValFin.setText(FarmaUtility.formatNumber(totalFinal, 2));
        lblDiferencia.setText("" + FarmaUtility.formatNumber(diferencia));

    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void lblTotalItemsTomaT_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }

    }

    private void btnNumeroToma_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(btnNumeroToma);
    }

    private void btnNumeroToma_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }


    private void pnlCabResumenInv_mouseDragged(MouseEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }

    private void pnlCabResumenInv_mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    private void btnCerrar_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void btnCerrar_actionPerformed(ActionEvent e) {
        cerrarVentana(false);
    }

    public double getTotalInicial() {

        return totalInicial;
    }

    public double getTotalFinal() {

        return totalFinal;
    }
}
