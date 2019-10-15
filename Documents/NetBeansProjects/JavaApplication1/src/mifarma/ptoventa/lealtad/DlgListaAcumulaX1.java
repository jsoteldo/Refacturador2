package mifarma.ptoventa.lealtad;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;

import farmapuntos.bean.BeanTarjeta;

import farmapuntos.orbis.WSClientConstans;

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

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaJTable;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.lealtad.reference.ConstantsLealtad;
import mifarma.ptoventa.lealtad.reference.FacadeLealtad;
import mifarma.ptoventa.programaXmas1.facade.ProgramaXmas1Facade;
import mifarma.ptoventa.puntos.reference.UtilityPuntos;
import mifarma.ptoventa.puntos.reference.VariablesPuntos;
import mifarma.ptoventa.reference.BeanResultado;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgListaAcumulaX1 extends JDialog {
    
    private static final Logger log = LoggerFactory.getLogger(DlgListaAcumulaX1.class);

    private final String  ESTADO_TEMPORAL_PROGRAMA = "T";
    private final Integer COL_COD_PROG = 1;
    private final String  RETORNO_INSCRITO = "S";
    private final String  RETORNO_NO_INSCRITO = "N";

    private Frame myParentFrame;
    private FarmaTableModel tableModelLista;
    private FarmaTableModel tableModelDetalleAcumula;
    private FarmaTableModel tableModelDetalleBonifica;
    private JPanel jPanelContenedorCabecera = new JPanel();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblF11 = new JLabelFunction();
    private JScrollPane scrLista = new JScrollPane();
    private JScrollPane scrDetalleAcumulaLista = new JScrollPane();
    private JScrollPane scrDetalleBonificaLista = new JScrollPane();
    private FarmaJTable tablaLista = new FarmaJTable();
    private FarmaJTable tablaDetalleAcumulaLista = new FarmaJTable();
    private FarmaJTable tablaDetalleBonificaLista = new FarmaJTable();
    private JButtonLabel jLabel1 = new JButtonLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    
    Boolean isDesafiliacion = false;
    private String pCodProd;
    private FacadeLealtad facadeLealtad;
    private String retorno;
    private String descProducto;

    List<BeanResultado> listBeanResultado;
    //List<BeanResultado> listBeanResultadoDetalle;
    ArrayList<ArrayList<String>> arrayDetalleResultado;
    
    String codProgramaDesafiliado="N";

    public DlgListaAcumulaX1() {
        this(null, "", false);
    }

    public DlgListaAcumulaX1(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }

    }

    public DlgListaAcumulaX1(Frame parent, String title, boolean modal,Boolean isDesafiliacion) {
        super(parent, title, modal);
        myParentFrame = parent;
        this.isDesafiliacion = isDesafiliacion;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(527, 490));
        this.getContentPane().setLayout( null );
        this.setDefaultCloseOperation(0);
        jPanelContenedorCabecera.setBounds(new Rectangle(0, 0, 515, 455));
        jPanelContenedorCabecera.setLayout(null);
        jPanelContenedorCabecera.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));


        jPanelContenedorCabecera.setBackground(SystemColor.window);
        lblEsc.setBounds(new Rectangle(410, 430, 100, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblF11.setBounds(new Rectangle(310, 430, 100, 20));
        lblF11.setText("[ F11 ] Aceptar");
        
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });

        this.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    this_keyReleased(e);
            }
        });
        scrLista.setBounds(new Rectangle(5, 70, 505, 145));
        scrLista.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));

        scrDetalleAcumulaLista.setBounds(new Rectangle(5, 265, 250, 140));
        scrDetalleAcumulaLista.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        scrDetalleBonificaLista.setBounds(new Rectangle(260, 265, 250, 140));
        scrDetalleBonificaLista.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        tablaLista.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    tablaLista_keyReleased(e);
            }
        });
        jLabel1.setBounds(new Rectangle(5, 10, 75, 15));
        jLabel1.setFont(new Font("Arial", 0, 12));

        jLabel1.setForeground(SystemColor.windowText);
        jLabel1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jLabel1_actionPerformed(e);
    }
            });
        jLabel2.setBounds(new Rectangle(5, 30, 500, 15));
        jLabel2.setFont(new Font("Arial", 0, 12));
        jLabel3.setBounds(new Rectangle(90, 10, 415, 15));
        jLabel3.setFont(new Font("Arial", 1, 12));
        if(isDesafiliacion){
            jLabel4.setText("¿Acepta desafiliarse en el programa seleccionado?");

        }else{
            jLabel4.setText("¿Acepta inscribirse en el programa seleccionado?");
            }
        jLabel4.setBounds(new Rectangle(5, 405, 505, 20));
        jLabel4.setFont(new Font("Arial", 1, 12));
        jLabel5.setText("Acumula:");
        jLabel5.setBounds(new Rectangle(5, 245, 250, 20));
        jLabel5.setFont(new Font("Arial", 1, 12));
        jLabel6.setText("Bonifica:");
        jLabel6.setBounds(new Rectangle(260, 250, 250, 15));
        jLabel6.setFont(new Font("Arial", 1, 12));
        jLabel7.setText("  Detalle de Programa:");
        jLabel7.setBounds(new Rectangle(5, 225, 505, 20));
        jLabel7.setFont(new Font("Arial", 1, 12));
        jLabel7.setBackground(new java.awt.Color(255, 130, 14));
        jLabel7.setOpaque(true);
        jLabel7.setForeground(SystemColor.window);
        jLabel8.setText("  Programa X+1");
        jLabel8.setBounds(new Rectangle(5, 50, 505, 20));
        jLabel8.setFont(new Font("Arial", 1, 12));
        jLabel8.setBackground(new java.awt.Color(255, 130, 14));
        jLabel8.setOpaque(true);
        jLabel8.setForeground(SystemColor.window);
        jPanelContenedorCabecera.add(jLabel8, null);
        jPanelContenedorCabecera.add(jLabel7, null);
        jPanelContenedorCabecera.add(jLabel6, null);
        jPanelContenedorCabecera.add(jLabel5, null);
        jPanelContenedorCabecera.add(jLabel4, null);
        jPanelContenedorCabecera.add(jLabel3, null);
        jPanelContenedorCabecera.add(jLabel2, null);
        jPanelContenedorCabecera.add(jLabel1, null);
        scrDetalleBonificaLista.getViewport().add(tablaDetalleBonificaLista, null);
        jPanelContenedorCabecera.add(scrDetalleBonificaLista, null);
        scrDetalleAcumulaLista.getViewport().add(tablaDetalleAcumulaLista, null);
        jPanelContenedorCabecera.add(scrDetalleAcumulaLista, null);
        scrLista.getViewport().add(tablaLista, null);
        jPanelContenedorCabecera.add(scrLista, null);
        jPanelContenedorCabecera.add(lblEsc, null);
        jPanelContenedorCabecera.add(lblF11, null);
        this.getContentPane().add(jPanelContenedorCabecera, null);
    }

    private void initialize() {
        FarmaVariables.vAceptar = false;
        
        initTable();
    }

    private void initTable() {
        tableModelLista =
                new FarmaTableModel(ConstantsLealtad.columnsListaAcumula, ConstantsLealtad.defaultValuesListaAcumula,
                                    0);
        FarmaUtility.initSimpleList(tablaLista, tableModelLista,
                                    ConstantsLealtad.columnsListaAcumula);
        
        tableModelDetalleAcumula =
                new FarmaTableModel(ConstantsLealtad.columnsDetalleListaAcumula, ConstantsLealtad.defaultValuesDetalleListaAcumula,
                                    0);
        FarmaUtility.initSimpleList(tablaDetalleAcumulaLista, tableModelDetalleAcumula,
                                    ConstantsLealtad.columnsDetalleListaAcumula);

        tableModelDetalleBonifica =
                new FarmaTableModel(ConstantsLealtad.columnsDetalleListaBonifica, ConstantsLealtad.defaultValuesDetalleListaBonifica,
                                    0);
        FarmaUtility.initSimpleList(tablaDetalleBonificaLista, tableModelDetalleBonifica,
                                    ConstantsLealtad.columnsDetalleListaBonifica);
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        facadeLealtad.listaAcumulaX1(tableModelLista,this.listBeanResultado);
        if (isDesafiliacion) {
            this.setTitle("Desafiliacion a un Programa X + 1");
            jLabel2.setText("El Cliente esta Inscrito en los siguientes Programas X+1");
        }else{
            this.setTitle("Inscripcion al Programa X + 1");
            jLabel3.setText(this.descProducto);
            jLabel2.setText("Participa en los siguientes programas X+1:");
            jLabel1.setText("El producto - ");
    }

        FarmaUtility.moveFocusJTable(tablaLista);
        actualizaTablaDetalle();
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void chkKeyPressed(KeyEvent e) {
        if (UtilityPtoVenta.verificaVK_F11(e)) {
            funcionF11();            
            cerrarVentana(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.retorno = RETORNO_NO_INSCRITO;
            cerrarVentana(false);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void tablaLista_keyReleased(KeyEvent e) {
        if (tablaLista.getRowCount() >= 0 && tablaLista.getRowCount() > 0 && e.getKeyChar() != '+') {
            if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_PAGE_UP) ||
                (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) ||
                (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                actualizaTablaDetalle();
            }else{
                chkKeyPressed(e);
            }
        }
    }

    private void jLabel1_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tablaLista);
    }
    
    private void this_keyReleased(KeyEvent e) {
        tablaLista_keyReleased(e);
    }
    
    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void funcionF11() {
        ProgramaXmas1Facade programaXmas1Facade;
        BeanTarjeta tarjetaBean = null;
        int fila ;
        codProgramaDesafiliado="N";
        try {
            fila = tablaLista.getSelectedRow();
        if(fila >= 0){
                programaXmas1Facade = new ProgramaXmas1Facade();
                codProgramaDesafiliado = FarmaUtility.getValueFieldJTable(tablaLista, fila, 1);
                tarjetaBean = VariablesPuntos.frmPuntos.getBeanTarjeta();
                
                if (isDesafiliacion) {
                    if(JConfirmDialog.rptaConfirmDialog(this, "¿Esta Seguro que Desea Desafiliarse de este Programa X+1?")){
                        tarjetaBean =programaXmas1Facade.registrarDesafiliacion(codProgramaDesafiliado);
                    }
                }else{
                    programaXmas1Facade.registrarProgramasTempX1(tarjetaBean.getDni(), 
                                                                 codProgramaDesafiliado,
                                                                 this.pCodProd,
                                                                 ESTADO_TEMPORAL_PROGRAMA,
                                                                 this.listBeanResultado);
                }
                if (tarjetaBean != null) {
                    if (tarjetaBean.getEstadoOperacion().equals(WSClientConstans.EXITO)) {
                        this.retorno = RETORNO_INSCRITO;
            cerrarVentana(true);
        }else{
                        if(isDesafiliacion && tarjetaBean.getEstadoOperacion().equals(WSClientConstans.NO_CONEXION_ORBIS)){
                            FarmaUtility.showMessage(this,
                                                     UtilityPuntos.obtenerMensajeErrorLealtad(tarjetaBean.getEstadoOperacion(),""),
                                                     null);
                        }else{
                            FarmaUtility.showMessage(this,
                                                     tarjetaBean.getMensaje(),
                                                     null);
                        }
                    }
                }
            }else{
                FarmaUtility.showMessage(this, "¡Debe seleccionar una campaña!", tablaLista);
            }
        } catch (Exception e) {
            this.retorno = RETORNO_NO_INSCRITO;
            log.error("Error al Registrar Programa[DlgListaAcumulaX1]", e);
        }
    }

    private void actualizaTablaDetalle() {
        Integer vFila;
        Integer verifica = 0;
        Integer extraccion = 0;
        String codigoPrograma;
        String filaConcatenadaAcumula = "";
        String filaConcatenadaBonifica = "";
        BeanResultado beanResultado = null;
        ArrayList<BeanResultado> listBeanResultadoDetalleAcumula = null;
        ArrayList<BeanResultado> listBeanResultadoDetalleBonifica = null;
        
        try {
            if(tableModelDetalleAcumula != null && tableModelDetalleBonifica != null){
                tableModelDetalleAcumula.clearTable();
                tableModelDetalleAcumula.fireTableDataChanged();
                
                tableModelDetalleBonifica.clearTable();
                tableModelDetalleBonifica.fireTableDataChanged();
            }
            vFila = tablaLista.getSelectedRow();
            if (vFila >= 0) {
                codigoPrograma = FarmaUtility.getValueFieldJTable(tablaLista, vFila, COL_COD_PROG);
                if(this.arrayDetalleResultado.size()>0){
                    listBeanResultadoDetalleAcumula = new ArrayList<BeanResultado>();
                    listBeanResultadoDetalleBonifica = new ArrayList<BeanResultado>();
                    
                    for (Integer fila = 0; fila < this.arrayDetalleResultado.size(); fila++) {
                        if(this.arrayDetalleResultado.get(fila).get(0).equals(codigoPrograma)){
                            filaConcatenadaAcumula = this.arrayDetalleResultado.get(fila).get(3);
                            
                            if(extraccion == 0){
                                jLabel5.setText("Acumula: " + this.arrayDetalleResultado.get(fila).get(4) + " unidades de:");
                                jLabel6.setText("Bonifica: " + this.arrayDetalleResultado.get(fila).get(7) + " unidades de:");
                                extraccion++;
                            }
                            
                            beanResultado = new BeanResultado();
                            beanResultado.setStrResultado(filaConcatenadaAcumula);
                            listBeanResultadoDetalleAcumula.add(beanResultado);
                            
                            filaConcatenadaBonifica = this.arrayDetalleResultado.get(fila).get(6);
                            for (BeanResultado beanResultadoVerificacion : listBeanResultadoDetalleBonifica) {
                                if(filaConcatenadaBonifica.equals(beanResultadoVerificacion.getStrResultado())){
                                    verifica++;
                                }
                            }
                            if (verifica == 0) {
                                beanResultado = new BeanResultado();
                                beanResultado.setStrResultado(filaConcatenadaBonifica);
                                listBeanResultadoDetalleBonifica.add(beanResultado);
                            }else{
                                verifica=0;
                            }
                            
                        }
                    }
                    
                    facadeLealtad.listaAcumulaX1(tableModelDetalleAcumula,listBeanResultadoDetalleAcumula);
                    facadeLealtad.listaAcumulaX1(tableModelDetalleBonifica,listBeanResultadoDetalleBonifica);

                }
                tablaDetalleAcumulaLista.repaint();
                tablaDetalleBonificaLista.repaint();
            }
        } catch (Exception e) {
            log.error(null, e);
            FarmaUtility.showMessage(this, "Error al Listar Productos Sustitutos.\n" +
                    e,null);
        }
    }
    
    /**
     * Devuelve un resultado despues de la operacion de registro
     * @return
     */
    public String getRetorno(){
        return this.retorno;
    }
    
    /**
     * Carga la descripcion del producto
     * @param descProducto
     */
    public void setDescProducto(String descProducto){
        this.descProducto = descProducto;
    }

    public void setpCodProd(String pCodProd) {
        this.pCodProd = pCodProd;
    }

    public void setFacadeLealtad(FacadeLealtad facadeLealtad) {
        this.facadeLealtad = facadeLealtad;
    }
    
    public void setListBeanResultado(List<BeanResultado> listBeanResultado){
        this.listBeanResultado = listBeanResultado;
    }

    public void setDetalleListaX1(ArrayList<ArrayList<String>> arrayDetalleResultado){
        this.arrayDetalleResultado = arrayDetalleResultado;
    }

    public void setCodProgramaDesafiliado(String codProgramaDesafiliado) {
        this.codProgramaDesafiliado = codProgramaDesafiliado;
    }

    public String getCodProgramaDesafiliado() {
        return codProgramaDesafiliado;
    }
}
