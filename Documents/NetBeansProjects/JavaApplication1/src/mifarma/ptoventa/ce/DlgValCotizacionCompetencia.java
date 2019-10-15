package mifarma.ptoventa.ce;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import javax.swing.text.LabelView;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.ce.reference.ConstantsCajaElectronica;
import mifarma.ptoventa.ce.reference.DBCajaElectronica;
import mifarma.ptoventa.ce.reference.VariablesCajaElectronica;
import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgValCotizacionCompetencia extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(DlgCierreCajaTurno.class);

    private Frame myParentFrame;
    private FarmaTableModel tableModel;

    private GridLayout gridLayout1 = new GridLayout();
    private JPanelWhite jPanelWhite1 = new JPanelWhite();
    private JPanelTitle jPanelTitle1 = new JPanelTitle();
    private JPanelHeader pnlValidacion = new JPanelHeader();

    private JButtonLabel btnDatosGenerales = new JButtonLabel();
    JLabel lblImagen = new JLabel();
    private JButton lblFormasPago = new JButton();
    private JButton lblEsc = new JButton();
    private JComboBox cmbTipoValida = new JComboBox();
    private JButtonLabel btnTipoValida = new JButtonLabel();
    private JButtonLabel btnVistoBuenoWeb = new JButtonLabel();
    private JButtonLabel btnObsWeb = new JButtonLabel();
    private JScrollPane scrObs = new JScrollPane();
    private JRadioButton rdbVBSIWeb = new JRadioButton();
    private JRadioButton rdbVBNOWeb = new JRadioButton();
    private JPanelWhite pnlRadioButonValidaWeb = new JPanelWhite();
    ButtonGroup btngroupWeb = new ButtonGroup();
    private TextArea txaGlosaWeb = new TextArea();
    private JButtonLabel btnVistoBuenoManual = new JButtonLabel();
    private JButtonLabel btnObsManual = new JButtonLabel();
    private JRadioButton rdbVBSIManual = new JRadioButton();
    private JRadioButton rdbVBNOManual = new JRadioButton();
    private JPanelWhite pnlRadioButonValidaManual = new JPanelWhite();
    ButtonGroup btngroupManual = new ButtonGroup();
    private TextArea txaGlosaManual = new TextArea();
    private JButtonLabel btnImporteTotal = new JButtonLabel();
    private JTextFieldSanSerif txtImporteTotal = new JTextFieldSanSerif();
    private JScrollPane scrLista = new JScrollPane();
    private JTable tblLista = new JTable();
    private JPanelTitle pnlObservaciones = new JPanelTitle();
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    private Label lblDocumento_T = new Label();
    private Label lblDocumento = new Label();
    private Label lblFecDoc_T = new Label();
    private Label lblFecDoc = new Label();
    private Label lblTipoDoc_T = new Label();
    private Label lblTipoDoc = new Label();
    private Label lblNombreUsu_T = new Label();
    private Label lblNombreUsu = new Label();
    private Label lblUsu_T = new Label();
    private Label lblUsu = new Label();
    private Label lblLocal_T = new Label();
    private Label lblLocal = new Label();

    private JPanel jPanelValidacionWeb = new JPanel();
    private JPanel jPanelValidacionManual = new JPanel();
    //JLabel lblImagen = new JLabel();
    private Label lblTituloImagen = new Label();
    private JScrollPane scrollImagen = new JScrollPane();
    private JPanelTitle pnlTituloImagen = new JPanelTitle();
    
    ArrayList<String> datosCotCom;
    String secMovCaja;
    String secCuadraCaja;
    String indValMan;
    String glosaMan;
    String indValWeb;
    String glosaWeb;
    String valor="";
    String codGrupoCia="";
    String codLocal="";
    double montoTotal=0;
    int MAXGLOSA=3000;
    boolean flagFirst=true;
    String numNota;

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgValCotizacionCompetencia() {
        this(null, "", false,new ArrayList());
    }

    public DlgValCotizacionCompetencia(Frame parent, String title, boolean modal,ArrayList<String> datos) {
        super(parent, title, modal);
        myParentFrame = parent;
        datosCotCom = datos;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }
    

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(1000, 603));
        this.getContentPane().setLayout(gridLayout1);
        this.setTitle("Validación Cotización Compentencia");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jPanelTitle1.setBounds(new Rectangle(10, 285, 610, 25));
        jPanelTitle1.setFocusable(false);
        pnlValidacion.setBounds(new Rectangle(625, 0, 360, 530));
        pnlValidacion.setBackground(Color.white);
        btnDatosGenerales.setText("Detalle Cotización");
        btnDatosGenerales.setBounds(new Rectangle(10, 5, 120, 15));
        btnDatosGenerales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDatosGenerales_actionPerformed(e);
            }
        });

        lblFormasPago.setBounds(new Rectangle(595, 540, 220, 25));
        lblFormasPago.setText("[ F11 ] Guardar");
        lblFormasPago.setBackground(SystemColor.scrollbar);
        lblFormasPago.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblFormasPago.setFont(new Font("Arial Black", 0, 10));
        lblFormasPago.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblFormasPago_actionPerformed(e);
                }
            });
        lblEsc.setBounds(new Rectangle(825, 540, 165, 25));
        lblEsc.setBackground(SystemColor.scrollbar);
        lblEsc.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblEsc.setFont(new Font("Arial Black", 0, 10));
       
        lblEsc.setText("[ ESC] Salir");

        lblEsc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblEsc_actionPerformed(e);
                }
            });

        
        btnVistoBuenoWeb.setText("Visto Bueno :");
        btnVistoBuenoWeb.setBounds(new Rectangle(10, 20, 85, 20));
        btnVistoBuenoWeb.setForeground(new Color(255, 130, 14));
        btnVistoBuenoWeb.setMnemonic('B');
        btnVistoBuenoWeb.setFocusable(false);
        btnVistoBuenoWeb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnVistoBuenoWeb_actionPerformed(e);
            }
        });

        btnObsWeb.setText("Observación:");
        btnObsWeb.setMnemonic('e');
        btnObsWeb.setForeground(new Color(255, 130, 14));
        btnObsWeb.setBounds(new Rectangle(10, 50, 85, 20));
        btnObsWeb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnObsWeb_actionPerformed(e);
            }
        });
        //scrObs.setBounds(new Rectangle(65, 5, 510, 70));
        rdbVBSIWeb.setText("SI");
        rdbVBSIWeb.setBounds(new Rectangle(10, 5, 40, 20));
        rdbVBSIWeb.setBackground(Color.WHITE);
        rdbVBSIWeb.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                rdbVBSIWeb_keyPressed(e);
            }
        });
        rdbVBSIWeb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    rdbVBSIWeb_actionPerformed(e);
                }

                private void rdbVBSIWeb_actionPerformed(ActionEvent actionEvent) {
                }
            });
        rdbVBNOWeb.setText("NO");
        rdbVBNOWeb.setBounds(new Rectangle(75, 5, 45, 20));
        rdbVBNOWeb.setBackground(Color.WHITE);
        rdbVBNOWeb.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                rdbVBNOWeb_keyPressed(e);
            }
        });
        btngroupWeb.add(rdbVBSIWeb);
        btngroupWeb.add(rdbVBNOWeb);
        //rdbVBNO.setSelected(true);
        pnlRadioButonValidaWeb.setBounds(new Rectangle(105, 15, 130, 30));
        pnlRadioButonValidaWeb.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        pnlRadioButonValidaWeb.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        pnlRadioButonValida_keyPressed(e);
                    }
                });
        //scrObs.getViewport().add(txaGlosaWeb, null);
        //pnlObservaciones.add(scrObs, null);
        txaGlosaWeb.setBounds(new Rectangle(10, 70, 240, 70));
        txaGlosaWeb.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txaGlosaWeb_keyPressed(e);
            }
        });
        
        btnVistoBuenoManual.setText("Visto Bueno :");
        btnVistoBuenoManual.setBounds(new Rectangle(10, 20, 75, 20));
        btnVistoBuenoManual.setForeground(new Color(255, 130, 14));
        btnVistoBuenoManual.setMnemonic('V');
        btnVistoBuenoManual.setFocusable(false);
        btnVistoBuenoManual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnVistoBuenoManual_actionPerformed(e);
            }
        });

        btnObsManual.setText("Observación:");
        btnObsManual.setMnemonic('O');
        btnObsManual.setForeground(new Color(255, 130, 14));
        btnObsManual.setBounds(new Rectangle(10, 50, 90, 20));
        btnObsManual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnObsManual_actionPerformed(e);
            }
        });
        //scrObs.setBounds(new Rectangle(65, 5, 510, 70));
        rdbVBSIManual.setText("SI");
        rdbVBSIManual.setBounds(new Rectangle(10, 5, 50, 20));
        rdbVBSIManual.setBackground(Color.WHITE);
        rdbVBSIManual.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                rdbVBSIManual_keyPressed(e);
            }
        });
        rdbVBSIManual.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                //    rdbVBSIManual_actionPerformed(e);
                }

            });
        rdbVBNOManual.setText("NO");
        rdbVBNOManual.setBounds(new Rectangle(75, 5, 50, 20));
        rdbVBNOManual.setBackground(Color.WHITE);
        rdbVBNOManual.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                rdbVBNOManual_keyPressed(e);
            }
        });
        rdbVBNOManual.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
            //        rdbVBNOManual_actionPerformed(e);
                }

            });
        btngroupManual.add(rdbVBSIManual);
        btngroupManual.add(rdbVBNOManual);
        //rdbVBNO.setSelected(true);
        pnlRadioButonValidaManual.setBounds(new Rectangle(105, 15, 130, 30));
        pnlRadioButonValidaManual.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        pnlRadioButonValidaManual.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        pnlRadioButonValida_keyPressed(e);
                    }
                });
        //scrObs.getViewport().add(txaGlosaWeb, null);
        //pnlObservaciones.add(scrObs, null);
        txaGlosaManual.setBounds(new Rectangle(10, 70, 250, 70));
        txaGlosaManual.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txaGlosaManual_keyPressed(e);
            }
        });
        
        btnImporteTotal.setText("Importe Total :");
        btnImporteTotal.setBounds(new Rectangle(395, 10, 90, 20));
        btnImporteTotal.setForeground(Color.white);
        btnImporteTotal.setMnemonic('I');
        btnImporteTotal.setFocusable(false);
        btnImporteTotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnImporteTotal_actionPerformed(e);
            }
        });

        txtImporteTotal.setHorizontalAlignment(JTextField.RIGHT);
        txtImporteTotal.setBounds(new Rectangle(490, 10, 105, 20));
        txtImporteTotal.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtImporteTotal_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtImporteTotal_keyTyped(e);
            }

            });

        txtImporteTotal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   // txtImporteTotal_actionPerformed(e);
                }
            });
        pnlObservaciones.setBounds(new Rectangle(10, 245, 610, 35));
        pnlObservaciones.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        scrLista.setBounds(new Rectangle(10, 310, 610, 220));
        tblLista.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblLista_keyPressed(e);
            }
            });
        jPanelHeader1.setBounds(new Rectangle(10, 0, 610, 70));
        lblLocal_T.setText("Cod. Local :");
        lblLocal_T.setBounds(new Rectangle(5, 10, 90, 15));
        lblLocal_T.setFont(new Font("SansSerif", 1, 11));
        lblLocal_T.setForeground(Color.white);
        lblLocal.setText(" - ");
        lblLocal.setBounds(new Rectangle(90, 10, 80, 15));
        lblLocal.setFont(new Font("SansSerif", 1, 11));
        lblLocal.setForeground(Color.white);
        
        lblDocumento_T.setText("N° Documento :");
        lblDocumento_T.setBounds(new Rectangle(180, 10, 90, 15));
        lblDocumento_T.setFont(new Font("SansSerif", 1, 11));
        lblDocumento_T.setForeground(Color.white);
        lblDocumento.setText(" - ");
        lblDocumento.setBounds(new Rectangle(275, 10, 115, 15));
        lblDocumento.setFont(new Font("SansSerif", 1, 11));
        lblDocumento.setForeground(Color.white);
        lblFecDoc_T.setText("Fec.Cotizacion:");
        lblFecDoc_T.setBounds(new Rectangle(5, 40, 85, 15));
        lblFecDoc_T.setFont(new Font("SansSerif", 1, 11));
        lblFecDoc_T.setForeground(Color.white);
        lblFecDoc.setText(" - ");
        lblFecDoc.setBounds(new Rectangle(95, 40, 75, 15));
        lblFecDoc.setFont(new Font("SansSerif", 1, 11));
        lblFecDoc.setForeground(Color.white);
        lblTipoDoc_T.setText("Tipo Documento :");
        lblTipoDoc_T.setBounds(new Rectangle(170, 40, 100, 15));
        lblTipoDoc_T.setFont(new Font("SansSerif", 1, 11));
        lblTipoDoc_T.setForeground(Color.white);
        lblTipoDoc.setText(" - ");
        lblTipoDoc.setBounds(new Rectangle(275, 40, 115, 15));
        lblTipoDoc.setFont(new Font("SansSerif", 1, 11));
        lblTipoDoc.setForeground(Color.white); 
        lblNombreUsu_T.setText("Nom. Usuario :");
        lblNombreUsu_T.setBounds(new Rectangle(410, 40, 85, 15));
        lblNombreUsu_T.setFont(new Font("SansSerif", 1, 11));
        lblNombreUsu_T.setForeground(Color.white);
        lblNombreUsu.setText(" - ");
        lblNombreUsu.setBounds(new Rectangle(495, 40, 115, 15));
        lblNombreUsu.setFont(new Font("SansSerif", 1, 11));
        lblNombreUsu.setForeground(Color.white);
        lblUsu_T.setText("Cod.Usuario :");
        lblUsu_T.setBounds(new Rectangle(410, 10, 85, 15));
        lblUsu_T.setFont(new Font("SansSerif", 1, 11));
        lblUsu_T.setForeground(Color.white);
        lblUsu.setText(" - ");
        lblUsu.setBounds(new Rectangle(495, 10, 115, 15));
        lblUsu.setFont(new Font("SansSerif", 1, 11));
        lblUsu.setForeground(Color.white);
        
        jPanelValidacionWeb.setBounds(new Rectangle(335, 75, 285, 165));
        jPanelValidacionWeb.setLayout(null);
        jPanelValidacionWeb.setBorder(BorderFactory.createTitledBorder("Validación Web: "));
        
        jPanelValidacionManual.setBounds(new Rectangle(10, 75, 275, 165));
        jPanelValidacionManual.setLayout(null);
        jPanelValidacionManual.setBorder(BorderFactory.createTitledBorder("Validación Manual: "));

        pnlTituloImagen.setBounds(new Rectangle(0, 0, 360, 30));
        pnlTituloImagen.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblTituloImagen.setText("Comprobante :");
        lblTituloImagen.setBounds(new Rectangle(5, 5, 105, 15));
        lblTituloImagen.setFont(new Font("SansSerif", 1, 11));
        lblTituloImagen.setForeground(Color.white);
   //  URL url = new URL("http://10.11.1.27/cotPrecios/imagenSustento/");
    //    JLabel lblImagen = new JLabel((Icon)new ImageIcon(url).getImage());
    //lblImagen.setIcon(new ImageIcon(url));

     /*  JLabel lblImagen = new JLabel(new ImageIcon("D:\\ProyectoZol\\PRECIO\\boletaEjemplo.png"));
        lblImagen.setVisible(true);*/
        
     
             lblImagen.setVisible(true);
        scrollImagen.setBounds(new Rectangle(0, 30, 360, 500));


        jPanelHeader1.add(lblDocumento_T, null);
        jPanelHeader1.add(lblDocumento, null);
        jPanelHeader1.add(lblFecDoc_T, null);
        jPanelHeader1.add(lblFecDoc, null);
        jPanelHeader1.add(lblTipoDoc_T, null);
        jPanelHeader1.add(lblTipoDoc, null);
        jPanelHeader1.add(lblNombreUsu_T, null);
        jPanelHeader1.add(lblNombreUsu, null);
        jPanelHeader1.add(lblUsu_T, null);
        jPanelHeader1.add(lblUsu, null);
        jPanelHeader1.add(lblLocal, null);
        jPanelHeader1.add(lblLocal_T, null);
        jPanelWhite1.add(jPanelHeader1, null);
        jPanelWhite1.add(jPanelValidacionWeb, null);
        jPanelWhite1.add(lblFormasPago, null);
        jPanelWhite1.add(jPanelValidacionManual, null);
        scrLista.getViewport().add(tblLista, null);
        jPanelWhite1.add(scrLista, null);
        pnlRadioButonValidaWeb.add(rdbVBSIWeb, null);
        // pnlObservaciones.add(btnObs, null);
        pnlRadioButonValidaWeb.add(rdbVBNOWeb, null);
        jPanelValidacionWeb.add(pnlRadioButonValidaWeb, null);
        jPanelValidacionWeb.add(btnVistoBuenoWeb, null);

        /*JLabel lblImagen = new JLabel(new ImageIcon("D:\\ProyectoZol\\PRECIO\\boletaEjemploGrande.png"));
        JScrollPane scrollpane = new JScrollPane(lblImagen);*/
        jPanelValidacionWeb.add(txaGlosaWeb, null);
        jPanelValidacionWeb.add(btnObsWeb, null);
        jPanelWhite1.add(lblEsc, null);
        jPanelTitle1.add(btnDatosGenerales, null);
        jPanelWhite1.add(jPanelTitle1, null);
        jPanelWhite1.add(pnlObservaciones, null);
        pnlRadioButonValidaManual.add(rdbVBSIManual, null);
        pnlRadioButonValidaManual.add(rdbVBNOManual, null);
        jPanelValidacionManual.add(pnlRadioButonValidaManual, null);
        jPanelValidacionManual.add(btnVistoBuenoManual, null);
        jPanelValidacionManual.add(txaGlosaManual, null);
        jPanelValidacionManual.add(btnObsManual, null);
        pnlTituloImagen.add(lblTituloImagen);
        // pnlValidacion.add(scrollImagen, null);
        pnlValidacion.add(scrollImagen, null);
        pnlValidacion.add(pnlTituloImagen, null);
        jPanelWhite1.add(pnlValidacion, null);
        scrollImagen.setViewportView(lblImagen);
        pnlObservaciones.add(btnImporteTotal, null);
        pnlObservaciones.add(txtImporteTotal, null);
        this.getContentPane().add(jPanelWhite1, null);
       // this.getContentPane().add(lblImagen, null);
        FarmaUtility.centrarVentana(this);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        cargarDatos();
        initCmbTipo();
        mostrarDetValCot();
    }
    
    public void cargarDatos(){
        lblLocal.setText(datosCotCom.get(2).toString());//local
        lblNombreUsu.setText(datosCotCom.get(1).toString());//sec_usu
        //lblFecDoc.setText(datosCotCom.get(3).toString());//total
        lblUsu.setText(datosCotCom.get(0).toString());//id_usu
        lblDocumento.setText(datosCotCom.get(13).toString().substring(0, 4)+"-"+datosCotCom.get(13).toString().substring(5, datosCotCom.get(13).toString().length()));
        lblTipoDoc.setText(datosCotCom.get(11).toString());
        codLocal=datosCotCom.get(2).toString();
        codGrupoCia=datosCotCom.get(4).toString();
        secMovCaja=datosCotCom.get(5).toString();
        secCuadraCaja=datosCotCom.get(6).toString();
        indValMan=datosCotCom.get(7).toString();
        glosaMan=datosCotCom.get(8).toString();
        indValWeb=datosCotCom.get(9).toString();
        glosaWeb=datosCotCom.get(10).toString();
        montoTotal=Double.parseDouble(datosCotCom.get(3).toString().trim().replaceAll(",",""));
        numNota=datosCotCom.get(14).toString();
        
        lblFecDoc.setText(datosCotCom.get(15).toString());
       
        try {
            URL url = new URL( DBCajaElectronica.getRutaServidorImagen()+datosCotCom.get(16).toString());
            lblImagen.setIcon(new ImageIcon(url));
        } catch (MalformedURLException e) {
            System.out.print(e.getMessage());
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }


    }
    
    public void initCmbTipo() {
        ArrayList parametros = new ArrayList();
        FarmaLoadCVL.loadCVLFromSP(cmbTipoValida, "cmbTipoValida",
                                   "PTOVENTA_CE.CE_LISTA_TIP_VAL_COT_COMP", parametros, true);
    }
    

    /* ************************************************************************ */
    /*                            METODOS INICIALIZACION                        */
    /* ************************************************************************ */





    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e) {
        initTableCotizacionCompetencia();
        cargaNotaDetalle();
        FarmaUtility.moveFocus(txtImporteTotal);
    }
    
    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void txaGlosaWeb_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtImporteTotal);  
        } else
            chkKeyPressed(e);
    }
    private void txaGlosaManual_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtImporteTotal);  
        } else
            chkKeyPressed(e);
    }
    private void rdbVBSIWeb_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            rdbVBSIWeb.setSelected(true);
        } else
            chkKeyPressed(e);
    }
    
    private void rdbVBNOWeb_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ;
        } else
            chkKeyPressed(e);
    }
    
    private void rdbVBSIManual_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
             rdbVBSIWeb.setSelected(true);
        } else
            chkKeyPressed(e);
    }
    private void rdbVBNOManual_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            rdbVBNOWeb.setSelected(true);
        } else
            chkKeyPressed(e);
    }
    
    private void pnlRadioButonValida_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ;
        } else
            chkKeyPressed(e);
    }
    
    private void txtCaja_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

        } else
            chkKeyPressed(e);
    }

    private void cmbTipoValida_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            ;//btnBuscar.doClick();
        else
            chkKeyPressed(e);
    }

    
    public void capturaValidaciones(){
        if(rdbVBSIManual.isSelected())
            indValMan="S";
        else 
            indValMan="N";
        
        glosaMan=txaGlosaManual.getText();
        
        glosaWeb=txaGlosaWeb.getText();
        if(rdbVBSIWeb.isSelected())
            indValWeb="S";
        else 
            indValWeb="N";

   // mostrarDetValCot();

    }
    
    public void mostrarDetValCot(){

        cambioRadioButtonWeb(indValWeb);
        cambioRadioButtonManual(indValMan);
        txaGlosaWeb.setText(glosaWeb);
        txaGlosaManual.setText(glosaMan);
        txtImporteTotal.setText("0.00");
        
    }
    
    public void cambioRadioButtonWeb(String ind){
        if(ind.equalsIgnoreCase("S"))
            rdbVBSIWeb.setSelected(true);
        else
            rdbVBNOWeb.setSelected(true);
    }
    public void cambioRadioButtonManual(String ind){
        if(ind.equalsIgnoreCase("S"))
            rdbVBSIManual.setSelected(true);
        else
            rdbVBNOManual.setSelected(true);
    }
    

    private void btnDatosGenerales_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocusJTable(tblLista);
    }

    private void btnVistoBuenoWeb_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(rdbVBSIWeb);
    }
    private void btnVistoBuenoManual_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(rdbVBSIManual);
    }
    
    private void btnObsManual_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txaGlosaManual);
    }
    private void btnObsWeb_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txaGlosaWeb);
    }
    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */

    private void chkKeyPressed(KeyEvent e){
      if (UtilityPtoVenta.verificaVK_F11(e)){
          evento_f11();
        }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
          if(JConfirmDialog.rptaConfirmDialog(this, "¿Desea salir sin guardar los cambios?")){
            cerrarVentana(false);
         }
      }
    }
    
    private void evento_f11(){
        if(validaCampo()){
            //System.err.println(FarmaLoadCVL.getCVLCode("cmbTipoValida", cmbTipoValida.getSelectedIndex()));
            grabarValidacionCotComp();
        }
    }
    
    private void grabarValidacionCotComp() {
      if(validaCampo()){
            boolean flag=DBCajaElectronica.grabaValidaCotCompetencia(codGrupoCia,codLocal,
                                                                     VariablesCajaElectronica.vCodCuadratura,
                                                                     secMovCaja,
                                                                     secCuadraCaja,
                                                                     indValMan,glosaMan,indValWeb,glosaWeb);
            if(flag){
                FarmaUtility.showMessage(this, "Datos guardados con éxito.", null);
                FarmaUtility.aceptarTransaccion();
                 cerrarVentana(true);
            }else{
                FarmaUtility.showMessage(this, "Ocurrió un error al guardar los datos.", null);
                //FarmaVariables.vNuSecUsu
            }
      }

    }
    
    public boolean validaCampo(){
        boolean flag=true;
        capturaValidaciones();
        if(glosaMan.length()>=MAXGLOSA){
            FarmaUtility.showMessage(this, "La Glosa de Validación Manual debe ser menor a 3000 carcacteres.", null);
            flag=false;
        }else if(glosaWeb.length()>=MAXGLOSA){
            FarmaUtility.showMessage(this, "La Glosa de Validación Web debe ser menor a 3000 carcacteres..", null);
            flag=false;

        } else if (txtImporteTotal.getText().trim().length() == 0) {
                FarmaUtility.showMessage(this, "Debe Ingresar el importe total del comprobante.", txtImporteTotal);
                flag = false;
        }else if (txtImporteTotal.getText().trim().length() > 0 && 
                  Double.parseDouble(txtImporteTotal.getText())!= montoTotal){
                FarmaUtility.showMessage(this, "El importe total es incorrecto", txtImporteTotal);
                flag = false;
            
            }
        return flag;
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void tblLista_keyPressed(KeyEvent keyEvent) {
    }

    private void txtImporteTotal_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            ;//btnBuscar.doClick();
        else
            chkKeyPressed(e);
    }

    private void txtImporteTotal_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtImporteTotal, e);
    }
    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */


    private void lblFormasPago_actionPerformed(ActionEvent e) {
    evento_f11();
  }

    private void lblEsc_actionPerformed(ActionEvent e) {
        if(JConfirmDialog.rptaConfirmDialog(this, "¿Desea salir sin guardar los cambios?")){
          cerrarVentana(false);
        }
        
    }
    private void initTableCotizacionCompetencia() {
        tableModel =
                new FarmaTableModel(ConstantsCajaElectronica.columsListaNotaDetalleCotizacionCompetencia, ConstantsCajaElectronica.defaultListaNotaDetalleCotizacionCompetencia,
                                    0);
        FarmaUtility.initSimpleList(tblLista, tableModel,
                                    ConstantsCajaElectronica.columsListaNotaDetalleCotizacionCompetencia);
    }

    private void cargaNotaDetalle() {
        try {
            BDCotizacionPrecio.cargaListaNotaDetalle(tableModel,numNota);
            if (tblLista.getRowCount() > 0)
                FarmaUtility.ordenar(tblLista, tableModel, 0, FarmaConstants.ORDEN_ASCENDENTE);
            FarmaUtility.moveFocusJTable(tblLista);
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al cargar los detalle de las cuadraturas en Cierre Dia\n" +
                    sql, null);
        }
    }

    private void btnImporteTotal_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtImporteTotal);
    }

}
