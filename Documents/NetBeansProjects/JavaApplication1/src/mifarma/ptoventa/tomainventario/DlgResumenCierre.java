package mifarma.ptoventa.tomainventario;


import com.gs.mifarma.componentes.JButtonFunction;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelTitle;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import javax.swing.border.BevelBorder;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.UtilFarma.ConstantesUtil;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgResumenCierre extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgResumenCierre.class);

    Frame parentFrame;
    private int x;
    private int y;
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
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
    private JPanel jpl_detalle = new JPanel();
    private JLabelOrange jLabelOrange3 = new JLabelOrange();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JLabelWhite jLabelWhite1 = new JLabelWhite();
    private JLabelWhite jLabelWhite3 = new JLabelWhite();
    private JLabelWhite jLabelWhite2 = new JLabelWhite();
    private JButtonFunction jButtonFunction1 = new JButtonFunction();
    private JLabelWhite lblHoraInicio = new JLabelWhite();
    private JLabelWhite jLabelWhite4 = new JLabelWhite();
    private JLabelWhite lblHoraFin = new JLabelWhite();
    private JLabelWhite lbl_TipoToma = new JLabelWhite();
    private String fecFinToma = "";

    public DlgResumenCierre() {
        this(null, "", false, null);
    }

    public DlgResumenCierre(Frame parent, String title, boolean modal, String estado) {
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

    private void jbInit() throws Exception {
        this.setUndecorated(true);
        this.setSize(new Dimension(356, 446));
        this.getContentPane().setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanelWhite1.setBounds(new Rectangle(0, 0, 355, 420));
        jPanelWhite1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        lblSobranteT.setText("Sobrante :     S/");
        lblSobranteT.setBounds(new Rectangle(85, 25, 90, 15));
        lblSobranteT.setFont(new Font("Tahoma", 1, 11));
        lblFaltanteT.setText("Faltante :       S/");
        lblFaltanteT.setBounds(new Rectangle(85, 50, 90, 15));
        lblFaltanteT.setFont(new Font("Tahoma", 1, 11));
        lblDiferenciaT.setText("Diferencia :    S/");
        lblDiferenciaT.setBounds(new Rectangle(85, 75, 90, 15));
        lblDiferenciaT.setFont(new Font("Tahoma", 1, 11));
        lblTotalItemsToma.setBounds(new Rectangle(425, 395, 125, 20));
        lblTotalItemsToma.setForeground(SystemColor.windowText);
        lblTotalItemsToma.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalItemsInventa.setBounds(new Rectangle(560, 400, 125, 20));
        lblTotalItemsInventa.setForeground(SystemColor.windowText);
        lblTotalItemsInventa.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDiferencia.setBounds(new Rectangle(190, 75, 120, 15));
        lblDiferencia.setForeground(SystemColor.windowText);
        lblDiferencia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDiferencia.setFont(new Font("Tahoma", 1, 11));
        lblFaltante.setBounds(new Rectangle(190, 50, 120, 15));
        lblFaltante.setForeground(SystemColor.windowText);
        lblFaltante.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFaltante.setFont(new Font("Tahoma", 1, 11));
        lblSobrante.setBounds(new Rectangle(190, 25, 120, 15));
        lblSobrante.setForeground(SystemColor.windowText);
        lblSobrante.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSobrante.setFont(new Font("Tahoma", 1, 11));
        lblNumeroToma.setBounds(new Rectangle(160, 55, 125, 20));
        lblNumeroToma.setForeground(SystemColor.windowText);
        btnNumeroToma.setText("N\u00b0 Toma Inventario :");
        btnNumeroToma.setBounds(new Rectangle(35, 60, 120, 15));
        btnNumeroToma.setMnemonic('t');
        btnNumeroToma.setActionCommand("");
        btnNumeroToma.setFont(new Font("Tahoma", 1, 11));
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
        jSeparator2.setBounds(new Rectangle(20, 125, 285, 2));
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
        lblTituloCab.setBounds(new Rectangle(15, 0, 270, 30));
        lblTituloCab.setText("Resumen Final al Cierre de Toma");
        lblTotalDist.setText("Total items Distorsionados :");
        lblTotalDist.setBounds(new Rectangle(20, 100, 165, 15));
        lblTotalDist.setFont(new Font("Tahoma", 1, 11));
        lblTotalDistor.setBounds(new Rectangle(190, 95, 120, 20));
        lblTotalDistor.setForeground(SystemColor.windowText);
        lblTotalDistor.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalDistor.setFont(new Font("Tahoma", 1, 11));
        jLabelOrange1.setText("Stock valorizado inicial :");
        jLabelOrange1.setBounds(new Rectangle(35, 140, 135, 15));
        jLabelOrange1.setFont(new Font("Tahoma", 1, 11));
        jLabelOrange2.setText("Stock valorizado final :");
        jLabelOrange2.setBounds(new Rectangle(45, 165, 130, 15));
        jLabelOrange2.setFont(new Font("Tahoma", 1, 11));
        lblstoValIni.setBounds(new Rectangle(185, 140, 125, 15));
        lblstoValIni.setHorizontalAlignment(SwingConstants.RIGHT);
        lblstoValIni.setForeground(SystemColor.windowText);
        lblstoValIni.setFont(new Font("Tahoma", 1, 11));
        lblstoValFin.setBounds(new Rectangle(185, 165, 125, 15));
        lblstoValFin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblstoValFin.setForeground(SystemColor.windowText);
        lblstoValFin.setFont(new Font("Tahoma", 1, 11));
        jpl_detalle.setBounds(new Rectangle(15, 160, 325, 200));
        jpl_detalle.setLayout(null);
        jpl_detalle.setBorder(BorderFactory.createTitledBorder("Consolidado"));
        jLabelOrange3.setText("jLabelOrange3");
        jPanelTitle1.setBounds(new Rectangle(15, 45, 325, 110));
        jLabelWhite1.setText("Fecha y Hora de Inicio :");
        jLabelWhite1.setBounds(new Rectangle(20, 10, 140, 15));
        jLabelWhite1.setFont(new Font("Tahoma", 1, 11));
        jLabelWhite3.setText("Fecha y Hora de Fin :");
        jLabelWhite3.setBounds(new Rectangle(35, 35, 120, 15));
        jLabelWhite3.setFont(new Font("Tahoma", 1, 11));
        jLabelWhite2.setText("Tipo Inventario :");
        jLabelWhite2.setBounds(new Rectangle(55, 85, 100, 15));
        jLabelWhite2.setFont(new Font("Tahoma", 1, 11));
        jButtonFunction1.setText("Aceptar");
        jButtonFunction1.setBounds(new Rectangle(120, 375, 100, 30));
        jButtonFunction1.setFont(new Font("Tahoma", 1, 12));
        jButtonFunction1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButtonFunction1_actionPerformed(e);
                }
            });
        lblHoraInicio.setBounds(new Rectangle(160, 5, 135, 20));
        jLabelWhite4.setText("jLabelWhite4");
        lblHoraFin.setBounds(new Rectangle(160, 30, 110, 20));
        lbl_TipoToma.setBounds(new Rectangle(160, 80, 105, 20));
        jpl_detalle.add(lblFaltanteT, null);
        jpl_detalle.add(lblDiferenciaT, null);
        jpl_detalle.add(lblSobranteT, null);
        jpl_detalle.add(lblSobrante, null);
        jpl_detalle.add(lblFaltante, null);
        jpl_detalle.add(jLabelOrange1, null);
        jpl_detalle.add(jLabelOrange2, null);
        jpl_detalle.add(lblstoValFin, null);
        jpl_detalle.add(lblstoValIni, null);
        jpl_detalle.add(jSeparator2, null);
        jpl_detalle.add(lblTotalDist, null);
        jpl_detalle.add(lblTotalDistor, null);
        jpl_detalle.add(lblDiferencia, null);
        jPanelTitle1.add(lbl_TipoToma, null);
        jPanelTitle1.add(lblHoraFin, null);
        jPanelTitle1.add(lblHoraInicio, null);
        jPanelTitle1.add(jLabelWhite2, null);
        jPanelTitle1.add(jLabelWhite3, null);
        jPanelTitle1.add(jLabelWhite1, null);
        jPanelTitle1.add(btnNumeroToma, null);
        jPanelTitle1.add(lblNumeroToma, null);
        jPanelWhite1.add(jButtonFunction1, null);
        jPanelWhite1.add(jPanelTitle1, null);
        jPanelWhite1.add(jpl_detalle, null);
        jPanelWhite1.add(lblTotalItemsInventa, null);
        jPanelWhite1.add(lblTotalItemsToma, null);
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
        lblHoraInicio.setText(VariablesTomaInv.vFecIniToma);
        lblHoraFin.setText(this.getFecFinToma());
        lbl_TipoToma.setText(VariablesTomaInv.vDescTipoToma);
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

            DBTomaInv.getListaProdDifCargado(lista, "");
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
            VariablesTomaInv.vObtieneInformacionValorizada.clear();
            VariablesTomaInv.vObtieneInformacionValorizada =
                    DBTomaInv.obtieneInformacionValorizada(VariablesTomaInv.vSecToma);

            if (tipoPrecio.equals("P")) {
                VariablesTomaInv.vFaltante =
                        ((String)((ArrayList)VariablesTomaInv.vObtieneInformacionValorizada.get(0)).get(2)).trim();
                VariablesTomaInv.vSobrante =
                        ((String)((ArrayList)VariablesTomaInv.vObtieneInformacionValorizada.get(0)).get(3)).trim();
            } else {
                VariablesTomaInv.vFaltante =
                        ((String)((ArrayList)VariablesTomaInv.vObtieneInformacionValorizada.get(0)).get(0)).trim();
                VariablesTomaInv.vSobrante =
                        ((String)((ArrayList)VariablesTomaInv.vObtieneInformacionValorizada.get(0)).get(1)).trim();
            }

            lblSobrante.setText(VariablesTomaInv.vSobrante);
            lblFaltante.setText(VariablesTomaInv.vFaltante);
        } catch (SQLException sql) {
            VariablesTomaInv.vObtieneInformacionValorizada = new ArrayList();
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener información valorizada : \n" +
                    sql.getMessage(), null);
        }

    }
    

    private void inventarioValorizados(String tipoPrecio) {

        try {
            DBTomaInv.getResumenValorizadoInventario(listaInVal);
            double totalInicial = 0, totalFinal = 0;
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
                totalFinal = totalFinal + totalFin;
            }
            lblstoValIni.setText(FarmaUtility.formatNumber(totalInicial, 2));
            lblstoValFin.setText(FarmaUtility.formatNumber(totalFinal, 2));
        } catch (SQLException e) {
            e.printStackTrace();
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
            //            System.out.print(pDecimal + " -> " + errParse.getMessage());
            //Inicio ID:010
            log.error("No se puede parsear el valor " + pDecimal + " con " + pDecimales + "decimales");
            //Fin ID:010
            //Inicio ID:011
            //errParse.printStackTrace();
            //Fin ID:011
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

    private void calculaDiferencia() {
        double diferencia = 0;
        double sobrante = 0;
        double faltante = 0;
        sobrante = FarmaUtility.getDecimalNumber(VariablesTomaInv.vSobrante);
        faltante = FarmaUtility.getDecimalNumber(VariablesTomaInv.vFaltante);
        //diferencia = Math.abs(sobrante) - Math.abs(faltante);
        diferencia = sobrante + faltante;
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

    private void jButtonFunction1_actionPerformed(ActionEvent e) {
        cerrarVentana(false);
    }

    public String getFecFinToma() {
        return fecFinToma;
    }

    public void setFecFinToma(String fecFinToma) {
        this.fecFinToma = fecFinToma;
    }
}
