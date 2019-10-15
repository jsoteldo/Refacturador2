package mifarma.ptoventa.reportes;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reportes.modelo.ConcursoGarantizado.BeanPeriodoReporteGarantizado;
import mifarma.ptoventa.reportes.modelo.ConcursoGarantizado.BeanResumenReporteGarantizado;
import mifarma.ptoventa.reportes.reference.DBReportes;
import mifarma.ptoventa.reportes.reference.FacadeReporte;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgReporteGarantizados extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgReporteGarantizados.class);

    private Frame myParentFrame;
    
    private FarmaTableModel modelTblComisionGarantizados;
    private JTable tblComisionGarantizados = new JTable();

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();
    private JPanelHeader pnlCriterioBusqueda = new JPanelHeader();
    private JPanelTitle pnlInformacion = new JPanelTitle();
    private JPanelTitle pnlDetalleReporte = new JPanelTitle();
    private JScrollPane jScrollPane1 = new JScrollPane();
    
    private JButtonLabel btnPeriodo = new JButtonLabel();
    private JButton btnBuscar = new JButton();
    
    private JLabel lblPeriodoT = new JLabel();
    private JLabel lblPeriodo = new JLabel();
    private JLabel lblCategoriaT = new JLabel();
    private JLabel lblCategoria = new JLabel();
    
    private JLabel lblComisionA = new JLabel();

    private JLabelFunction lblEsc = new JLabelFunction();
    private JComboBox cmbPeriodo = new JComboBox();
    private JLabel lblMsjEnter = new JLabel();

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */
    public DlgReporteGarantizados() {
        this(null, "", false);
    }

    public DlgReporteGarantizados(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
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
        this.setSize(new Dimension(733, 468));
        this.getContentPane().setLayout(borderLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Concurso #Garantimanía");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        
        jContentPane.setFocusable(false);
        
        btnPeriodo.setText("Periodo :");
        btnPeriodo.setBounds(new Rectangle(180, 5, 60, 20));
        btnPeriodo.setMnemonic('p');
        btnPeriodo.setFocusable(false);
        btnPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPeriodo_actionPerformed(e);
            }
        });
        
        cmbPeriodo.setBounds(new Rectangle(240, 5, 150, 20));
        cmbPeriodo.setName("CMBPERIODO");
        cmbPeriodo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                eventoKeyPressed(e);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(new Rectangle(405, 5, 95, 20));
        btnBuscar.setMnemonic('b');
        btnBuscar.setFont(new Font("SansSerif", 1, 11));
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        
        pnlCriterioBusqueda.setBounds(new Rectangle(10, 15, 700, 30));
        pnlCriterioBusqueda.setFocusable(false);
        pnlCriterioBusqueda.add(cmbPeriodo, null);
        pnlCriterioBusqueda.add(btnBuscar, null);
        pnlCriterioBusqueda.add(btnPeriodo, null);
        
        lblPeriodoT.setText("Periodo :");
        lblPeriodoT.setBounds(new Rectangle(105, 5, 70, 20));
        lblPeriodoT.setFont(new Font("SansSerif", 1, 13));
        lblPeriodoT.setForeground(Color.white);
        lblPeriodoT.setFocusable(false);
        
        lblPeriodo.setText("[PERIODO]");
        lblPeriodo.setBounds(new Rectangle(180, 5, 130, 20));
        lblPeriodo.setFont(new Font("SansSerif", 1, 13));
        lblPeriodo.setForeground(Color.white);
        lblPeriodo.setFocusable(false);
        
        lblCategoriaT.setText("Categoría :");
        lblCategoriaT.setBounds(new Rectangle(360, 5, 80, 20));
        lblCategoriaT.setFont(new Font("SansSerif", 1, 13));
        lblCategoriaT.setForeground(Color.white);
        lblCategoriaT.setFocusable(false);
        
        lblCategoria.setText("[CATEGORIA]");
        lblCategoria.setBounds(new Rectangle(445, 5, 225, 20));
        lblCategoria.setFont(new Font("SansSerif", 1, 13));
        lblCategoria.setForeground(Color.white);
        lblCategoria.setFocusable(false);

        pnlDetalleReporte.setBounds(new Rectangle(10, 55, 700, 35));
        pnlDetalleReporte.setFocusable(false);
        pnlDetalleReporte.add(lblPeriodoT, null);
        pnlDetalleReporte.add(lblPeriodo, null);
        pnlDetalleReporte.add(lblCategoriaT, null);
        pnlDetalleReporte.add(lblCategoria, null);
        
        jScrollPane1.setBounds(new Rectangle(10, 100, 700, 270));
        jScrollPane1.setFocusable(false);
        
        tblComisionGarantizados.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblVentasVendedor_keyPressed(e);
            }
        });

        lblComisionA.setText("[Mensaje Comision A]");
        lblComisionA.setBounds(new Rectangle(5, 0, 610, 20));
        lblComisionA.setFont(new Font("SansSerif", 1, 11));
        lblComisionA.setForeground(Color.white);
        lblComisionA.setFocusable(false);

        pnlInformacion.setBounds(new Rectangle(10, 380, 700, 25));
        pnlInformacion.setFocusable(false);
        pnlInformacion.setBackground(new Color(43, 141, 39));

        lblEsc.setBounds(new Rectangle(620, 410, 90, 25));
        lblEsc.setText("[ ESC ] Cerrar");
        
        lblMsjEnter.setText("Presione [Enter] para realizar otra búsqueda");
        lblMsjEnter.setBounds(new Rectangle(10, 415, 340, 15));
        lblMsjEnter.setFont(new Font("SansSerif", 1, 14));
        lblMsjEnter.setForeground(new Color(43, 141, 39));
        lblMsjEnter.setVisible(false);

        jContentPane.add(lblMsjEnter, null);
        jContentPane.add(pnlCriterioBusqueda, null);
        jContentPane.add(pnlDetalleReporte, null);
        jScrollPane1.getViewport().add(tblComisionGarantizados, null);
        jContentPane.add(jScrollPane1, null);

        pnlInformacion.add(lblComisionA, null);
        jContentPane.add(pnlInformacion, null);
        jContentPane.add(lblEsc, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */
    private void initialize() {
        // CREA TABLA 
        FarmaColumnData[] cabeceraTabla = new FarmaColumnData[]{new FarmaColumnData("Código", 70, JLabel.CENTER),
                                                                new FarmaColumnData("Vendedor", 230, JLabel.LEFT),
                                                                new FarmaColumnData("Meta", 80, JLabel.RIGHT),
                                                                new FarmaColumnData("Avance", 80, JLabel.RIGHT),
                                                                new FarmaColumnData("% Proyectado", 85, JLabel.RIGHT),
                                                                new FarmaColumnData("Com.Garantizados (A)", 140, JLabel.RIGHT)};
        modelTblComisionGarantizados = new FarmaTableModel( cabeceraTabla,new Object[]{ " ", " ", " ", " ", " ", " "},0);
        
        FarmaUtility.initSimpleList(tblComisionGarantizados, modelTblComisionGarantizados, cabeceraTabla);
        
        cargarComboPeriodos();
        busqueda();
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */
    private void eventoKeyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBuscar.doClick();
        }else{
            chkKeyPressed(e);
        }
    }

    private void tblVentasVendedor_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER ){
            FarmaUtility.moveFocus(cmbPeriodo);
            lblMsjEnter.setVisible(false);
        }else{
            chkKeyPressed(e);
        }
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(cmbPeriodo);
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        busqueda();
    }

    private void btnPeriodo_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbPeriodo);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */
    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */
    private void busqueda() {
        String codigoSeleccionado = FarmaLoadCVL.getCVLCode(cmbPeriodo.getName(), cmbPeriodo.getSelectedIndex());
        
        if(codigoSeleccionado != null && codigoSeleccionado.trim().length() > 0){
            FacadeReporte facade = new FacadeReporte();
            facade.procesarVentasComisionGarantizado(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, codigoSeleccionado);
            List listaResumen = facade.obtenerResumenComisionGarantizado(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, codigoSeleccionado);
            lblPeriodo.setText((String)cmbPeriodo.getSelectedItem());
            modelTblComisionGarantizados.clearTable();
            
            if(listaResumen.size()==0){
                lblCategoria.setText("");
            }else{
                BeanResumenReporteGarantizado bean = (BeanResumenReporteGarantizado)listaResumen.get(0);
                lblCategoria.setText(bean.getCategoria());
                
                try{
                    DBReportes.obtenerReporteComisionGarantizado(modelTblComisionGarantizados, FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, codigoSeleccionado);
                }catch(Exception ex){
                    log.error("", ex);
                    FarmaUtility.showMessage(this, "Concurso Garantizados:\nError al cargar los datos de comision de ventas", cmbPeriodo);
                }
                
                FarmaUtility.moveFocusJTable(tblComisionGarantizados);
                lblMsjEnter.setVisible(true);
            }
        }
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void cargarComboPeriodos(){
        FacadeReporte facade = new FacadeReporte();
        List lista = facade.obtenerPeriodoReporteGarantizados(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal);
        String msjComisionA = facade.obtenerMsjInfoComisionGarantizadoA();
        
        if(lista==null){
            FarmaUtility.showMessage(this, "Concurso Garantizados:\nError al cargar los periodos de busqueda del reporte.\n" +
                                           "Reintente, si problema persiste comuniquese con Mesa de Ayuda!!!", null);
            cerrarVentana(false);
        }else{
            if(lista.size()==0){
                FarmaUtility.showMessage(this, "Concurso Garantizados:\nNo hay periodos que mostrar en el reporte.",null);
                cerrarVentana(false);
            }else{
                ArrayList listaPeriodo = new ArrayList();
                
                for(int i=0;i<lista.size();i++){
                    BeanPeriodoReporteGarantizado periodo = (BeanPeriodoReporteGarantizado)lista.get(i);
                    ArrayList fila = new ArrayList();
                    fila.add(periodo.getMesId());
                    fila.add(periodo.getPeriodo());
                    listaPeriodo.add(fila);
                }
                FarmaLoadCVL.loadCVLFromArrayList(cmbPeriodo, cmbPeriodo.getName(), listaPeriodo, true);
                lblComisionA.setText(msjComisionA);
            }
        }
    }
}