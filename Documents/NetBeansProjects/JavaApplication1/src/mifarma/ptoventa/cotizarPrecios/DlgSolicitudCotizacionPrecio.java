package mifarma.ptoventa.cotizarPrecios;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLengthText;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.referencia.ConstantesCotiza;
import mifarma.ptoventa.cotizarPrecios.referencia.FacadeCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.referencia.UtilCotizacion;
import mifarma.ptoventa.ventas.DlgDetalleProducto;
import mifarma.ptoventa.ventas.reference.VariablesVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgSolicitudCotizacionPrecio extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgSolicitudCotizacionPrecio.class);
    private Frame myParentFrame;
    private FarmaTableModel tblModelSolicitud;
    private JTable tblSolicitud = new JTable();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlFechas = new JPanel();
    private JPanel pnlListaSolicitud = new JPanel();
    private JScrollPane spnSolicitud = new JScrollPane();
    private JLabel lblTituloFechas = new JLabel();
    private JButton lblDe = new JButton();
    private JButton lblA = new JButton();
    private JTextFieldSanSerif txtFechaInicio = new JTextFieldSanSerif();
    private JTextFieldSanSerif txtFechaFin = new JTextFieldSanSerif();
    private JButton lblListaSolicitud = new JButton();
    private JLabelFunction lblSinSolicitud_F1 = new JLabelFunction();
    private JLabelFunction lblAnular_Del = new JLabelFunction();
    private JLabelFunction lblSalir_Esc = new JLabelFunction();
    private JLabel lblSolicitudCant = new JLabel();
    private JButton btnBuscar = new JButton();
    private String tituloVentana="";
    //1-compra/2-cotiza/3-cotizar sin solicitud
    private int tipoCotizacion=2;
    private JScrollPane scpScrolBar = new JScrollPane();
    private JScrollBar jsbScrolSolicitud = new JScrollBar();
    /*** INICIO CCASTILLO 24/08/2017 ***/
    private FarmaTableModel tblModelDetSolicitud;
    private JTable tblDetSolicitud = new JTable();
    private JPanel pnlDetSolicitud = new JPanel();
    private JButton lblDetSolicitud = new JButton();
    private JScrollPane spnDetSolicitud = new JScrollPane();
    private JScrollPane scpScrolDetBar = new JScrollPane();
    private JScrollBar jsbScrolDetSolicitud = new JScrollBar();
    private JPanel pnlMensaje = new JPanel();
    private JLabel lblMensaje = new JLabel();
    private JLabelFunction lblVerProducto = new JLabelFunction();
    private JLabelFunction lblCambiarProducto = new JLabelFunction();
    /*** FIN CCASTILLO 24/08/2017 ***/
     private JLabel lblPrioridadUrgente = new JLabel("", SwingConstants.CENTER);
    private JLabel lblPrioridadPrioritario = new JLabel("", SwingConstants.CENTER);
    private JLabel lblPrioridadNormal = new JLabel("", SwingConstants.CENTER);
    private JLabel lblPrioridad = new JLabel();
    public DlgSolicitudCotizacionPrecio() {
        super();
    }
    
    public DlgSolicitudCotizacionPrecio(Frame parent, String title, boolean modal,int tipoCotizacion) {
        super(parent, title, modal);
        myParentFrame = parent;
        tituloVentana = title;
       // if(conDoc){ //1-- con documento 2-- sin documento
            this.tipoCotizacion = tipoCotizacion;
           // VarCotizacionPrecio.vCondicion =tipoCotizacion;
        //}
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    private void imprimir(String msj) {
        System.out.println(msj);
    }
    private void jbInit() throws Exception {
        this.setSize(new Dimension(730, 483));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle(tituloVentana);
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

        pnlFechas.setBounds(new Rectangle(15, 5, 695, 75));
        pnlFechas.setBackground(new Color(43, 141, 39));
        pnlFechas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlFechas.setLayout(null);


        lblTituloFechas.setText("Rango de Fechas Solicitud");
        lblTituloFechas.setBounds(new Rectangle(5, 5, 200, 15));
        lblTituloFechas.setBackground(SystemColor.window);
        lblTituloFechas.setForeground(SystemColor.window);
        lblTituloFechas.setFont(new Font("SansSerif", 1, 14));
        
        lblDe.setText("De:");
        lblDe.setBounds(new Rectangle(70, 35, 34, 15));
        lblDe.setFont(new Font("SansSerif", 1, 12));
        lblDe.setForeground(Color.white);
        lblDe.setBackground(new Color(43, 141, 39));
        lblDe.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblDe.setBorderPainted(false);
        lblDe.setContentAreaFilled(false);
        lblDe.setDefaultCapable(false);
        lblDe.setFocusPainted(false);
        lblDe.setMnemonic('D');
        lblDe.setRequestFocusEnabled(false);
        lblDe.setHorizontalAlignment(SwingConstants.LEFT);
        lblDe.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblDe_ActionPerformed(e);
                }
            });
        lblDe.addContainerListener(new ContainerAdapter() {
                public void componentRemoved(ContainerEvent e) {
                    lblDe_ComponenRemoved(e);
                }
            });
        
        lblA.setText("Hasta:");
        lblA.setBounds(new Rectangle(285, 35, 50, 15));
        lblA.setFont(new Font("SansSerif", 1, 12));
        lblA.setForeground(Color.white);
        lblA.setBackground(new Color(43, 141, 39));
        lblA.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblA.setBorderPainted(false);
        lblA.setContentAreaFilled(false);
        lblA.setDefaultCapable(false);
        lblA.setFocusPainted(false);
        lblA.setMnemonic('H');
        lblA.setRequestFocusEnabled(false);
        lblA.setHorizontalAlignment(SwingConstants.LEFT);
        lblA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblA_ActionPerformed(e);
                }
            });
        lblA.addContainerListener(new ContainerAdapter() {
                public void componentRemoved(ContainerEvent e) {
                    lblA_ComponenRemoved(e);
                }
            });
        
        lblListaSolicitud.setText("Lista de Solicitudes");
        lblListaSolicitud.setBounds(new Rectangle(5, 2, 145, 15));
        lblListaSolicitud.setFont(new Font("SansSerif", 1, 12));
        lblListaSolicitud.setForeground(Color.white);
        lblListaSolicitud.setBackground(new Color(43, 141, 39));
        lblListaSolicitud.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblListaSolicitud.setBorderPainted(false);
        lblListaSolicitud.setContentAreaFilled(false);
        lblListaSolicitud.setDefaultCapable(false);
        lblListaSolicitud.setFocusPainted(false);
        lblListaSolicitud.setMnemonic('L');
        lblListaSolicitud.setRequestFocusEnabled(false);
        lblListaSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
        lblListaSolicitud.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblListaSolicitud_ActionPerformed(e);
                }
            });
        lblListaSolicitud.addContainerListener(new ContainerAdapter() {
                public void componentRemoved(ContainerEvent e) {
                    lblListaSolicitud_ComponenRemoved(e);
                }
            });
        lblSinSolicitud_F1.setText("<HTML><CENTER>[ F1 ] Cotiza<BR>Sin Solicitud</HTML></CENTER>");
        lblSinSolicitud_F1.setBounds(new Rectangle(485, 410, 110, 35));
        lblAnular_Del.setText("<HTML><CENTER>[ Enter ] Ingresa<BR>Cotizacion</HTML></CENTER>");
        lblAnular_Del.setBounds(new Rectangle(320, 410, 140, 35));
        lblSalir_Esc.setText("<HTML><CENTER>[ ESC ] Cerrar</HTML></CENTER>");
        lblSalir_Esc.setBounds(new Rectangle(615, 410, 95, 35));
        lblSolicitudCant.setBounds(new Rectangle(475, 0, 195, 15));
        lblSolicitudCant.setText("");
        lblSolicitudCant.setFont(new Font("SansSerif", 1, 12));
        lblSolicitudCant.setForeground(SystemColor.window);
        lblSolicitudCant.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSolicitudCant.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(525, 30, 130, 25));
        btnBuscar.setMnemonic('B');
        btnBuscar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnBuscar_ActionPerformed(e);
                }
            });
        scpScrolBar.setBounds(new Rectangle(690, 100, 20, 150));
        txtFechaInicio.setBounds(new Rectangle(110, 30, 130, 25));
        txtFechaInicio.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaIni_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtFechaIni_keyReleased(e);
            }
        });
        txtFechaInicio.setDocument(new FarmaLengthText(10));
        txtFechaFin.setBounds(new Rectangle(345, 30, 145, 25));
        txtFechaFin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtFechaFin_keyPressed(e);

            }

            public void keyReleased(KeyEvent e) {
                txtFechaFin_keyReleased(e);

            }
        });
        txtFechaFin.setDocument(new FarmaLengthText(10));
        pnlListaSolicitud.setBounds(new Rectangle(15, 80, 695, 20));
        pnlListaSolicitud.setBackground(new Color(255, 130, 14));
        pnlListaSolicitud.setLayout(null);
        

        spnSolicitud.setBounds(new Rectangle(15, 100, 678, 150));
        spnSolicitud.setBackground(new Color(255, 130, 14));
        spnSolicitud.setAutoscrolls(false);
        spnSolicitud.setVerticalScrollBar(jsbScrolSolicitud);
        /*** INICIO CCASTILLO 24/08/2017 ***/
        tblSolicitud.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblSolicitud_KeyPressed(e);
                }
            });
        tblSolicitud.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblSolicitud_mouseClicked(e);
            }
        });
        /*** FIN CCASTILLO 24/08/2017 ***/
        spnSolicitud.getViewport();


        pnlFechas.add(btnBuscar, null);
        pnlFechas.add(txtFechaFin, null);
        lblPrioridadUrgente.setText("Urgente");
        lblPrioridadUrgente.setBounds(new Rectangle(405, 390, 80, 15));
        lblPrioridadUrgente.setBackground(new Color(249, 145, 127));
        lblPrioridadUrgente.setOpaque(true);
        lblPrioridadUrgente.setForeground(SystemColor.black);
        lblPrioridadUrgente.setFont(new Font("SansSerif", 1, 11));
        lblPrioridadUrgente.setVerticalAlignment(SwingConstants.CENTER);

        lblPrioridadPrioritario.setText("Prioritario");
        lblPrioridadPrioritario.setBounds(new Rectangle(510, 390, 75, 15));
        lblPrioridadPrioritario.setBackground(new Color(127, 249, 154));
        lblPrioridadPrioritario.setOpaque(true);
        lblPrioridadPrioritario.setForeground(SystemColor.black);
        lblPrioridadPrioritario.setFont(new Font("SansSerif", 1, 11));

        lblPrioridadNormal.setText("Normal");
        lblPrioridadNormal.setBounds(new Rectangle(605, 390, 80, 15));
        lblPrioridadNormal.setBackground(new Color(249, 244, 139));
        lblPrioridadNormal.setOpaque(true);
        lblPrioridadNormal.setForeground(SystemColor.black);
        lblPrioridadNormal.setFont(new Font("SansSerif", 1, 11));

        lblPrioridad.setText("Prioridad: ");
        lblPrioridad.setBounds(new Rectangle(330, 390, 65, 15));
        lblPrioridad.setForeground(SystemColor.black);
        lblPrioridad.setFont(new Font("SansSerif", 1, 11));

        pnlFechas.add(txtFechaInicio, null);
        pnlFechas.add(lblA, null);
        pnlFechas.add(lblDe, null);
        lblVerProducto.setText("<HTML><CENTER>[ F11 ] Ver Info.<BR>Producto</HTML></CENTER>");
        lblVerProducto.setBounds(new Rectangle(15, 410, 130, 35));
        lblCambiarProducto.setText("<HTML><CENTER>[ F12 ] Cambiar<BR>Producto</HTML></CENTER>");
        lblCambiarProducto.setBounds(new Rectangle(170, 410, 125, 35));

        ctpContenido.add(lblVerProducto, null);
        ctpContenido.add(lblCambiarProducto, null);
        pnlDetSolicitud.setBounds(new Rectangle(15, 250, 695, 20));
        pnlDetSolicitud.setBackground(new Color(255, 130, 14));
        pnlDetSolicitud.setLayout(null);

        lblDetSolicitud.setText("Detalle Solicitud");
        lblDetSolicitud.setBounds(new Rectangle(5, 2, 145, 15));
        lblDetSolicitud.setFont(new Font("SansSerif", 1, 12));
        lblDetSolicitud.setForeground(Color.white);
        lblDetSolicitud.setBackground(new Color(43, 141, 39));
        lblDetSolicitud.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblDetSolicitud.setBorderPainted(false);
        lblDetSolicitud.setContentAreaFilled(false);
        lblDetSolicitud.setDefaultCapable(false);
        lblDetSolicitud.setFocusPainted(false);
        lblDetSolicitud.setMnemonic('S');
        lblDetSolicitud.setRequestFocusEnabled(false);
        lblDetSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
        lblDetSolicitud.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblDetSolicitud_ActionPerformed(e);
                }
            });
        lblDetSolicitud.addContainerListener(new ContainerAdapter() {
                public void componentRemoved(ContainerEvent e) {
                    lblDetSolicitud_ComponenRemoved(e);
                }
            });
        spnDetSolicitud.setBounds(new Rectangle(15, 270, 675, 85));
        spnDetSolicitud.setBackground(new Color(255, 130, 14));
        spnDetSolicitud.setAutoscrolls(false);
        spnDetSolicitud.setVerticalScrollBar(jsbScrolDetSolicitud);
        tblDetSolicitud.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblDetSolicitud_KeyPressed(e);
                }
            });



        spnDetSolicitud.getViewport();

        scpScrolDetBar.setBounds(new Rectangle(690, 270, 20, 85));
        pnlDetSolicitud.add(lblDetSolicitud, null);
        ctpContenido.add(pnlDetSolicitud, null);

        scpScrolDetBar.getViewport().add(jsbScrolDetSolicitud, null);
        ctpContenido.add(scpScrolDetBar, null);
        spnDetSolicitud.getViewport().add(tblDetSolicitud, null);
        ctpContenido.add(spnDetSolicitud, null);
        pnlMensaje.add(lblMensaje, null);
        ctpContenido.add(pnlMensaje, null);

        scpScrolBar.getViewport().add(jsbScrolSolicitud, null);
        ctpContenido.add(scpScrolBar, null);
        ctpContenido.add(lblSalir_Esc, null);
        lblMensaje.setText("¡ATENCI\u00d3N! Los productos de color rojo no cumplen con la unidad de venta permitida en su local");
        lblMensaje.setBounds(new Rectangle(10, 2, 565, 25));
        lblMensaje.setFont(new Font("SansSerif", 3, 12));
        lblMensaje.setForeground(SystemColor.window);

        lblMensaje.setVerticalTextPosition(SwingConstants.TOP);
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlMensaje.setBounds(new Rectangle(15, 355, 695, 30));
        pnlMensaje.setBackground(Color.red);
        pnlMensaje.setLayout(null);


        /*** FIN CCASTILLO 24/08/2017 ***/

        pnlFechas.add(lblTituloFechas, null);
        ctpContenido.add(pnlFechas, null);
        pnlListaSolicitud.add(lblSolicitudCant, null);
        pnlListaSolicitud.add(lblListaSolicitud, null);
        ctpContenido.add(pnlListaSolicitud, null);
        spnSolicitud.getViewport().add(tblSolicitud, null);
        ctpContenido.add(spnSolicitud, null);
        ctpContenido.add(lblAnular_Del, null);
        ctpContenido.add(lblSinSolicitud_F1, null);

        ctpContenido.add(lblPrioridadUrgente, null);
        ctpContenido.add(lblPrioridadPrioritario, null);
        ctpContenido.add(lblPrioridadNormal, null);
        ctpContenido.add(lblPrioridad, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);
    }
    
    private void initialize() {
        initListaSolicitud();
    }
    
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        muestraDatos_XDefecto();
        if(tblModelSolicitud.getRowCount()>0){
            FarmaUtility.moveFocusJTable(tblSolicitud);
        }else{
            FarmaUtility.moveFocus(txtFechaInicio);
        }
        /*
        try {
            
            String fechaActual = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            String fechaAtrasada =
                FarmaSearch.getFechaHoraAtrasadaBD(FarmaConstants.FORMATO_FECHA, 10); //Change by Cesar Huanes
            txtFechaInicio.setText(fechaAtrasada);
            txtFechaFin.setText(fechaActual); //Change by Cesar Huanes
            
            
        } catch (SQLException sql) {
            muestraMensaje("Error!!!!!.",
                            sql.getMessage());
        }
        */
    }

    private void this_windowClosing(WindowEvent e) {
        muestraMensaje("Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    
    private void muestraMensaje(String msj, Object cTxt) {
        FarmaUtility.showMessage(this, msj, cTxt);
    }
    
    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }
    
    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
            cerrarVentana();
            break;
        case KeyEvent.VK_ENTER:
            e.consume();
            btnBuscar.doClick();
            break;
        }
    }

    private void initListaSolicitud() {
        tblModelSolicitud =
                new FarmaTableModel(ConstantesCotiza.columnsListaSolicitud, ConstantesCotiza.defaultValuesListaSolicitud,
                                    0);
        FarmaUtility.initSimpleList(tblSolicitud, tblModelSolicitud, ConstantesCotiza.columnsListaSolicitud);

        tblModelDetSolicitud =
                new FarmaTableModel(ConstantesCotiza.columnsProducto_Solicitud, ConstantesCotiza.defaultValuesProducto_Solicitud,
                                    0);
        FarmaUtility.initSimpleList(tblDetSolicitud, tblModelDetSolicitud, ConstantesCotiza.columnsProducto_Solicitud);
        
        /*
        ArrayList rowsWithOtherColor = new ArrayList();
        for (int i = 0; i < tblModelSolicitud.data.size(); i++) {
            if (((ArrayList)tblModelSolicitud.data.get(i)).get(7).toString().trim().equalsIgnoreCase("2")) { 
                rowsWithOtherColor.add(String.valueOf(i));
            }
        }
        
        FarmaUtility.initSimpleListCleanColumns(tblSolicitud, tblModelSolicitud,
                                                ConstantesCotiza.columnsListaSolicitud, rowsWithOtherColor,
                                                Color.yellow, Color.blue, false);*/

        tblSolicitud.getTableHeader().setReorderingAllowed(false);
        tblSolicitud.getTableHeader().setResizingAllowed(false);

     
    }

    private void txtFechaIni_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtFechaFin);
        } else
            chkKeyPressed(e);
    }

    private void txtFechaIni_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaInicio, e);
    }

    private void txtFechaFin_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBuscar.doClick();
        } else
            chkKeyPressed(e);
    }

    private void txtFechaFin_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFechaFin, e);
    }

    private void btnBuscar_ActionPerformed(ActionEvent e) {
        if (validarCampos()) {
            fechaIni = txtFechaInicio.getText().trim();
            fechaFin = txtFechaFin.getText().trim();
            //codEstado = FarmaLoadCVL.getCVLCode("CMB_ESTADO_SOLIC", cmbEstado.getSelectedIndex());
            muestraDatos_Solicitud();
            cargarListaProductos_Solicitud(0);
        }
    }
    String fechaIni="";
    String fechaFin="";
    
    private void muestraDatos_Solicitud() {
        fechaIni = txtFechaInicio.getText();
        fechaFin = txtFechaFin.getText();
        tblModelSolicitud.clearTable();
        tblModelDetSolicitud.clearTable();
        int existenDatos = UtilCotizacion.existenSolicitudes(fechaIni, fechaFin,tipoCotizacion);
        if (existenDatos > 0) {
            UtilCotizacion.cargaTabla_Solicitud(tblModelSolicitud, fechaIni, fechaFin,tipoCotizacion);
            FarmaUtility.moveFocusJTable(tblSolicitud);
           
        }else{
            muestraMensaje("No se encontraron registros para las fechas ingresadas", txtFechaInicio);
        }
        lblSolicitudCant.setText(tblModelSolicitud.getRowCount()+" Solicitudes encontradas");
        
        if (tblSolicitud.getRowCount() > 0) {
            /*FarmaUtility.ordenar(tblSolicitud, tblModelSolicitud, Constants.COL_NUMERO_DOCUMENTO,
                                 FarmaConstants.ORDEN_ASCENDENTE);*/
            int cols = tblSolicitud.getColumnCount();
            for (int i = 0; i < cols; i++) {
                tblSolicitud.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
            }
          
        }
        //   FarmaUtility.moveFocusJTable(tblSolicitud);
        tblSolicitud.getSelectionModel().setSelectionInterval (0, 0);
        tblSolicitud.getTableHeader().setReorderingAllowed(false);
        tblSolicitud.getTableHeader().setResizingAllowed(false);
    }
    
    private boolean validarCampos() {
        boolean retorno = true;
        if (txtFechaInicio.getText().trim().equals("")) {
            retorno = false;
             muestraMensaje("Ingrese Fecha de Inicio.", txtFechaInicio);
        } else if (txtFechaFin.getText().trim().equals("")) {
            retorno = false;
             muestraMensaje("Ingrese Fecha de Fin. ", txtFechaFin);
        } else if (!FarmaUtility.validaFecha(txtFechaInicio.getText(), "dd/MM/yyyy")) {
            retorno = false;
             muestraMensaje("Formato Incorrecto de Fecha.", txtFechaInicio);
        } else if (!FarmaUtility.validaFecha(txtFechaFin.getText(), "dd/MM/yyyy")) {
            retorno = false;
             muestraMensaje("Formato Incorrecto de Fecha.", txtFechaFin);
        } else if (!FarmaUtility.validarRangoFechas(this, txtFechaInicio, txtFechaFin, false, true, true, true))
            retorno = false;
        return retorno;
    }

    private void muestraDatos_XDefecto() {
        tblModelSolicitud.clearTable();
        tblModelDetSolicitud.clearTable();// 2017.11.09
        int existenDatos = UtilCotizacion.existenSolicitudesXEstado("P",tipoCotizacion);
        if (existenDatos > 0) {
            UtilCotizacion.cargaTabla_SolicitudXDefecto(tblModelSolicitud,"P",tipoCotizacion);
            
            /*** INICIO CCASTILLO 24/08/2017 ***/
            //pintaFilasPrioridad();
            //UtilCotizacion.cargaTabla_SolicitudXDefecto(tblModelDetSolicitud,"P",tipoCotizacion);
            /*** INICIO CCASTILLO 24/08/2017 ***/
            
            if (tblSolicitud.getRowCount() > 0) {
                /*FarmaUtility.ordenar(tblSolicitud, tblModelSolicitud, Constants.COL_NUMERO_DOCUMENTO,
                                     FarmaConstants.ORDEN_ASCENDENTE);*/
                int cols = tblSolicitud.getColumnCount();
                for (int i = 0; i < cols; i++) {
                    tblSolicitud.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
                }
              
            }
         //   FarmaUtility.moveFocusJTable(tblSolicitud);
            tblSolicitud.getSelectionModel().setSelectionInterval (0, 0);
        
        /*    ArrayList rowsWithOtherColor = new ArrayList();
            for (int i = 0; i < tblModelSolicitud.data.size(); i++) {
                if (((ArrayList)tblModelSolicitud.data.get(i)).get(7).toString().trim().equalsIgnoreCase("2")) { 
                    rowsWithOtherColor.add(String.valueOf(i));
                }
            }
            
            FarmaUtility.initSimpleList(tblSolicitud, tblModelSolicitud,
                                                    ConstantesCotiza.columnsListaSolicitud, rowsWithOtherColor,
                                                    Color.yellow, Color.blue, false);
            
            
            ArrayList rowsWithOtherColor2 = new ArrayList();
            for (int i = 0; i < tblModelSolicitud.data.size(); i++) {
                if (((ArrayList)tblModelSolicitud.data.get(i)).get(7).toString().trim().equalsIgnoreCase("3")) { 
                    rowsWithOtherColor2.add(String.valueOf(i));
                }
            }
            FarmaUtility.initSimpleList(tblSolicitud, tblModelSolicitud,
                                                    ConstantesCotiza.columnsListaSolicitud, rowsWithOtherColor2,
                                                    Color.GREEN, Color.blue, false);
           FarmaUtility.initSimpleListCleanColumns(tblSolicitud, tblModelSolicitud,
                                                    ConstantesCotiza.columnsListaSolicitud, rowsWithOtherColor2,
                                                    Color.GREEN, Color.blue, false);

            tblSolicitud.getTableHeader().setReorderingAllowed(false);
            tblSolicitud.getTableHeader().setResizingAllowed(false);*/
            
            cargarListaProductos_Solicitud(0);
        }
        lblSolicitudCant.setText(tblModelSolicitud.getRowCount()+" Solicitudes encontradas");
    }
    
    /*** INICIO CCASTILLO 28/08/2017 ***/
    
    private void tblDetSolicitud_KeyPressed(KeyEvent e) {
     switch (e.getKeyCode()) {
        case KeyEvent.VK_F1:
          funcionCotizacionSinSolicitud();
              break;
        case KeyEvent.VK_F11:
            abrirInfoProd();
            break;
        case KeyEvent.VK_F12:
            seleccionaregistro();
            permitirCambiarProducto();
            //cargarDatos_Cotizacion();
            //permitirCambiarProducto();
            //cargarDatos_Cotizacion(); 
            break;
        case KeyEvent.VK_ENTER:
          e.consume();
          funcionEnter();
          break;
       case KeyEvent.VK_TAB:
          FarmaUtility.moveFocus(txtFechaInicio);
           break;
       default:
        chkKeyPressed(e);
      }
    }
    /*** FIN CCASTILLO 28/08/2017 ***/

    private void tblSolicitud_KeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_F1:
            funcionCotizacionSinSolicitud();
              break;
        case KeyEvent.VK_UP:
            cargarListaProductos_Solicitud(-1);
            break;
        case KeyEvent.VK_DOWN:
            cargarListaProductos_Solicitud(1);
            break;
        case KeyEvent.VK_F11:
            abrirInfoProd();
            break;

        case KeyEvent.VK_ENTER:
            e.consume();
            funcionEnter();
            break;
        case KeyEvent.VK_TAB:
            FarmaUtility.moveFocus(txtFechaInicio);
            break;
    case KeyEvent.VK_F12:
          //FarmaUtility.moveFocusJTable(tblDetSolicitud);
            muestraMensaje("Debe seleccionar un producto", tblDetSolicitud);
            tblDetSolicitud.getSelectionModel().setSelectionInterval (0, 0);
        break;
        default:
            chkKeyPressed(e);
        }
    }
    
    /*** INICIO CCASTILLO 28/08/2017 ***/
    public void funcionEnter(){
        
        //INI AOVIEDO 21/07/2017
        int i = tblSolicitud.getSelectedRow();
        VarCotizacionPrecio.vCompetencia = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COMPETENCIA);
        VarCotizacionPrecio.vRUC_Comp = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_RUC_COMPETENCIA);
        VarCotizacionPrecio.vCod_Solic = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COD_SOLIC);
        String estadoSolicitud=FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_ESTADO);
        Date fechaVigencia= FarmaUtility.getStringToDate(FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_FECHA_VIGENCIA), "dd/MM/yyyy");
        VarCotizacionPrecio.vTipo_Proceso = "02";
        VarCotizacionPrecio.vTipo_Origen = "04";
        VarCotizacionPrecio.vCod_Origen = FarmaVariables.vCodLocal;
        
        VarCotizacionPrecio.vFecha_Cotizacion = "";
        VarCotizacionPrecio.vTipo_Doc = "";
        VarCotizacionPrecio.vSerieDoc = "";
        VarCotizacionPrecio.vNroGuia = "";
        VarCotizacionPrecio.vRuta_Archivo = "";
        
        String fecha="";
        try {
            VarCotizacionPrecio.vFecha_Cotizacion = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            VarCotizacionPrecio.vFecha_Cotizacion = "";
            fecha = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
        } catch(SQLException sql) {
            log.error("", sql);
        }
        
        Date sysdate = FarmaUtility.getStringToDate(fecha, "dd/MM/yyyy");
       /* if (!sysdate.before(fechaVec)) {
            FarmaUtility.showMessage(this,
                                     "La Fecha de Vencimiento debe ser posterior a la fecha de hoy: " +
                                     fecha + " .", txtFechaVencimiento);
            retorno = false;
        }
        */
        if(estadoSolicitud.equals("Pendiente") && (sysdate.before(fechaVigencia) || sysdate.equals(fechaVigencia))){
            if(this.tipoCotizacion == 2){
                if(JConfirmDialog.rptaConfirmDialog(this, "¿Tiene el documento físico?")){
                    abrirPantallaCabecera(this.tipoCotizacion, VarCotizacionPrecio.vCompetencia, 
                                          VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic,
                                          FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_FECHA_SOLICITUD));
                }else{
                    VarCotizacionPrecio.vRuta_Archivo = "";
                    abrirPantallaDetalle(this.tipoCotizacion, VarCotizacionPrecio.vCompetencia, 
                                         VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic);
                }
            }else{
               
                abrirPantallaCabecera(this.tipoCotizacion, VarCotizacionPrecio.vCompetencia, 
                                 VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic,
                                      FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_FECHA_SOLICITUD));
               
            }
            //actualiza la lista
            if(FarmaVariables.vAceptar){
                    muestraDatos_XDefecto();
                }
            
        }else{
             muestraMensaje("La solicitud debe estar Pendiente y vigente para Ingresar la Cotización ", tblSolicitud);
            } 
    }
    /*** FIN CCASTILLO 28/08/2017 ***/
    
    private void abrirPantallaCabecera(int tipoProceso, String nombreTienda, String nroRUC, String nroSolicitud,String fechaSolicitud){
       DlgCotizacionPrecioCabecera dlgCotizacionPrecioCabecera = new DlgCotizacionPrecioCabecera(myParentFrame, "", true);
       dlgCotizacionPrecioCabecera.setTipoProceso(String.valueOf(tipoProceso));
       dlgCotizacionPrecioCabecera.setNombreTienda(nombreTienda);
       dlgCotizacionPrecioCabecera.setNroRUC(nroRUC);
       dlgCotizacionPrecioCabecera.setNroSolicitud(nroSolicitud);
       dlgCotizacionPrecioCabecera.setFechaSolicitud(fechaSolicitud);
       dlgCotizacionPrecioCabecera.setVisible(true);
        
       if (FarmaVariables.vAceptar) {
        
            
            //cerrarVentana();
          /*  DlgCotizacionPrecioDetalle dlgCotizacionPrecioDetalle = new DlgCotizacionPrecioDetalle(myParentFrame, "", true, this.tipoCotizacion+"");
            dlgCotizacionPrecioDetalle.setFechaCreacion(dlgCotizacionPrecioCabecera.getFechaCreacion());
            dlgCotizacionPrecioDetalle.setTipoProceso(dlgCotizacionPrecioCabecera.getTipoProceso());
            dlgCotizacionPrecioDetalle.setTipoComprobante(dlgCotizacionPrecioCabecera.getTipoComprobante());
            dlgCotizacionPrecioDetalle.setNroComprobante(dlgCotizacionPrecioCabecera.getNroComprobante());
            dlgCotizacionPrecioDetalle.setNroSolicitud(dlgCotizacionPrecioCabecera.getNroSolicitud());
          //  dlgCotizacionPrecioDetalle.setNombreTienda(nombreTienda);
          //  dlgCotizacionPrecioDetalle.setNroRUC(nroRUC);
          dlgCotizacionPrecioDetalle.setNombreTienda(dlgCotizacionPrecioCabecera.getNombreTienda());
                     dlgCotizacionPrecioDetalle.setNroRUC(dlgCotizacionPrecioCabecera.getNroRUC());
            dlgCotizacionPrecioDetalle.setVisible(true);*/
        }
    }
    
    private void abrirPantallaDetalle(int tipoProceso, String nombreTienda, String nroRUC, String nroSolicitud){
        DlgCotizacionPrecioDetalle dlgCotizacionPrecioDetalle = new DlgCotizacionPrecioDetalle(myParentFrame, "", true, this.tipoCotizacion+"");
        dlgCotizacionPrecioDetalle.setFechaCreacion("");
        dlgCotizacionPrecioDetalle.setTipoProceso(String.valueOf(tipoProceso));
        dlgCotizacionPrecioDetalle.setTipoComprobante("");
        dlgCotizacionPrecioDetalle.setNroComprobante("");
        dlgCotizacionPrecioDetalle.setNroSolicitud(nroSolicitud);
        dlgCotizacionPrecioDetalle.setNombreTienda(nombreTienda);
        dlgCotizacionPrecioDetalle.setNroRUC(nroRUC);
        dlgCotizacionPrecioDetalle.setVisible(true);
    }
    
    private void abrirDetalleSolicitud() {
        int i = tblSolicitud.getSelectedRow();
        VarCotizacionPrecio.vCod_Solic= FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COD_SOLIC);
        VarCotizacionPrecio.vRUC_Comp= FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_RUC_COMPETENCIA);
        VarCotizacionPrecio.vCompetencia= FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COMPETENCIA);
        VarCotizacionPrecio.vFec_Vigencia= FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_FECHA_VIGENCIA);
        
        DlgCotizaVerDetalle dlgVerDetalle = new DlgCotizaVerDetalle(myParentFrame,"",true,VarCotizacionPrecio.vCod_Solic);
        dlgVerDetalle.setConDocumento(this.tipoCotizacion);
        dlgVerDetalle.setVisible(true);
    }

    private void lblDe_ActionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFechaInicio);
    }

    private void lblDe_ComponenRemoved(ContainerEvent e) {
        FarmaUtility.moveFocus(txtFechaInicio);
    }

    private void lblA_ActionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtFechaFin);
    }

    private void lblA_ComponenRemoved(ContainerEvent e) {
        FarmaUtility.moveFocus(txtFechaFin);
    }

    private void lblListaSolicitud_ActionPerformed(ActionEvent e) {
        FarmaUtility.moveFocusJTable(tblSolicitud);
    }
    
    /*** INICIO CCASTILLO 24/08/2017 ***/
    private void lblDetSolicitud_ActionPerformed(ActionEvent e) {
        FarmaUtility.moveFocusJTable(tblDetSolicitud);
    }
    private void lblDetSolicitud_ComponenRemoved(ContainerEvent e) {
        FarmaUtility.moveFocusJTable(tblDetSolicitud);
    }

    private void tblSolicitud_mouseClicked(MouseEvent e) {
        cargarListaProductos_Solicitud(0);
    }
    
    private void cargarListaProductos_Solicitud(int position) {
        
        int i = tblSolicitud.getSelectedRow();
         i=i+position;
        if(i<0){i=0;}
        if(i==tblSolicitud.getRowCount()){i--;}
        String nroSolic = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COD_SOLIC);
        VarCotizacionPrecio.vCod_Solic= nroSolic;
        tblModelDetSolicitud.clearTable();
        UtilCotizacion.cargaTabla_Productos(tblModelDetSolicitud,nroSolic);
        pintarProds_NoValidos();
        tblDetSolicitud.getSelectionModel().setSelectionInterval (0, 0);
    }
    
    private void pintarProds_NoValidos() {
        ArrayList rowsWithOtherColor = new ArrayList();
        for (int i = 0; i < tblModelDetSolicitud.data.size(); i++) {
            if (((ArrayList)tblModelDetSolicitud.data.get(i)).get(ConstantesCotiza.INDEX_IND_VALIDO).toString().trim().equalsIgnoreCase("N")) { 
                rowsWithOtherColor.add(String.valueOf(i));
            }
        }
        
        FarmaUtility.initSimpleListCleanColumns(tblDetSolicitud, tblModelDetSolicitud, 
                                                ConstantesCotiza.columnsProducto_Solicitud, rowsWithOtherColor,
                                                Color.WHITE, Color.RED, false);

        tblDetSolicitud.getTableHeader().setReorderingAllowed(false);
        tblDetSolicitud.getTableHeader().setResizingAllowed(false);
    }
    
    
    private void abrirInfoProd() {
        int i = tblDetSolicitud.getSelectedRow();
        System.err.println("getSelectedRow  : "+i);
        if(i<0){
            muestraMensaje("Debe Seleccionar un registro del Detalle de Solicitud", tblDetSolicitud);
        }else{
        VariablesVentas.vCod_Prod=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, i, ConstantesCotiza.INDEX_COD_PROD);
        DlgDetalleProducto dlgDetalleProducto = new DlgDetalleProducto(myParentFrame, "", true,false);
        dlgDetalleProducto.setVisible(true);
        }
    }
    
    private void seleccionaregistro() {
        int j = tblDetSolicitud.getSelectedRow();
        VarCotizacionPrecio.vCod_Prod=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, j, ConstantesCotiza.INDEX_COD_PROD);
        VarCotizacionPrecio.vProducto=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, j, ConstantesCotiza.INDEX_PRODUCTO);
        VarCotizacionPrecio.vUnidad=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, j, ConstantesCotiza.INDEX_UNIDAD);
        VarCotizacionPrecio.vLaboratorio=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, j, ConstantesCotiza.INDEX_LABORATORIO);
        VarCotizacionPrecio.vCant_Solic=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, j, ConstantesCotiza.INDEX_CANTIDAD);
        VarCotizacionPrecio.vValido=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, j, ConstantesCotiza.INDEX_IND_VALIDO);
    }
    
    private void permitirCambiarProducto() {
        VarCotizacionPrecio.vValPrecVta=FarmaUtility.getValueFieldArrayList(tblModelDetSolicitud.data, tblDetSolicitud.getSelectedRow(), ConstantesCotiza.INDEX_VAL_PREC_VTA);
        DlgCambiarProd_Cotizar dlgCambiar = new DlgCambiarProd_Cotizar(myParentFrame, "", true);
        dlgCambiar.setVisible(true);        
    }
    
    private void cargarDatos_Cotizacion() {
        int i = tblDetSolicitud.getSelectedRow();
        String nroSolic = FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data, i, ConstantesCotiza.INDEX_COD_SOLIC);
        UtilCotizacion.cargaTabla_Productos(tblModelDetSolicitud,nroSolic);
        pintarProds_NoValidos();
        //lblSolicitudNro.setText("Solicitud "+codigoSolicitud+": Cant. de productos a cotizar: "+tblModelProductos.getRowCount());
        FarmaUtility.moveFocusJTable(tblDetSolicitud);
    }
    

     
     public String getValueColumna(int Fila, int Columna) {
          String pValor =
              FarmaUtility.getValueFieldArrayList(tblModelSolicitud.data,
                                                  Fila, Columna);
          return pValor;
      }

     /*** FIN CCASTILLO 21/08/2017 ***/ 

    private void lblListaSolicitud_ComponenRemoved(ContainerEvent e) {
        FarmaUtility.moveFocusJTable(tblSolicitud);
    }

    private void btnPrueba_ActionPerformed(ActionEvent e) {
        FacadeCotizacionPrecio f = new FacadeCotizacionPrecio();
        String rpta=f.pruebaSolicitudDeCambioProducto("1013",
                                                    FarmaVariables.vCodLocal,
                                                      "desarrollo1@mifarma.com.pe");
        FarmaUtility.showMessage(this,rpta,null);
    }
    
    public void funcionCotizacionSinSolicitud(){

        //INI LTAVARA 2017.09.12
        VarCotizacionPrecio.vCompetencia = "";
        VarCotizacionPrecio.vRUC_Comp ="";
        VarCotizacionPrecio.vCod_Solic ="";
        //COTIZACION SIN SOLICITUD
        VarCotizacionPrecio.vTipo_Proceso = "03";
        VarCotizacionPrecio.vTipo_Origen = "04";
        VarCotizacionPrecio.vCod_Origen = FarmaVariables.vCodLocal;
        
        VarCotizacionPrecio.vFecha_Cotizacion = "";
        VarCotizacionPrecio.vTipo_Doc = "";
        VarCotizacionPrecio.vSerieDoc = "";
        VarCotizacionPrecio.vNroGuia = "";
        VarCotizacionPrecio.vRuta_Archivo = "";
        
        try {
            VarCotizacionPrecio.vFecha_Cotizacion = FarmaSearch.getFechaHoraBD(FarmaConstants.FORMATO_FECHA);
            VarCotizacionPrecio.vFecha_Cotizacion = "";
        } catch(SQLException sql) {
            log.error("", sql);
        }
        if(JConfirmDialog.rptaConfirmDialog(this, "¿Deseas Cotizar sin Solicitud?")){
                    tipoCotizacion=3;
                    abrirPantallaCabecera(this.tipoCotizacion, VarCotizacionPrecio.vCompetencia, 
                                          VarCotizacionPrecio.vRUC_Comp, VarCotizacionPrecio.vCod_Solic,"");
                
        }
    }
    
    class CustomRenderer extends DefaultTableCellRenderer 
    {
    
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         
            if(tblModelDetSolicitud.data.size()>0){
            int  intValor=Integer.parseInt(tblSolicitud.getValueAt(row, 7).toString().trim());
            
            Color prioridad1 = new Color(249,145,127) ;//ALTA
            Color prioridad2 = new Color(127,249,154);//MEDIA
            Color prioridad3 = new Color(249,244,139);//BAJA

            cellComponent.setBackground(Color.white);
            if(intValor==1){
                    setBackground(prioridad1);
            }else if(intValor==2){
                    setBackground(prioridad2);
                }
            else if(intValor==3){
                    setBackground(prioridad3);
                }
            }
            return cellComponent;
        }
        
    
        
    }

}
