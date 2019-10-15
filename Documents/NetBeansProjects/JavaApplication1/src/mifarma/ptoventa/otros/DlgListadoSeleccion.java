package mifarma.ptoventa.otros;

import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.JDialog;

import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.administracion.impresoras.reference.DBImpresoras;
import mifarma.ptoventa.centromedico.DlgListaEspera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgListadoSeleccion extends JDialog {

    private static final Logger log = LoggerFactory.getLogger(DlgListadoSeleccion.class);
    private Frame myParentFrame;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite pnlContenedor = new JPanelWhite();
    private JLabelOrange lblBuscar = new JLabelOrange();
    private JTextFieldSanSerif txtBuscar = new JTextFieldSanSerif();
    private JPanelTitle panTitulo = new JPanelTitle();
    private JLabelWhite lblTitulo = new JLabelWhite();
    private JScrollPane scrListado = new JScrollPane();
    private JTable tblListado = new JTable();
    private FarmaTableModel modelTblListado;
    private JLabelFunction lblF11 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private ArrayList<ArrayList<Object>> lstListadoTabla = new ArrayList<ArrayList<Object>>();
    private ArrayList<Object> filaSeleccionada = new ArrayList<Object>();
    private boolean seleccionMultiple = false;
    private boolean incluyeOtro = false;


    public DlgListadoSeleccion() {
        this(null, "", false);
    }

    public DlgListadoSeleccion(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.myParentFrame = parent;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(397, 321));
        this.getContentPane().setLayout(borderLayout1);
        
        lblBuscar.setText("Buscar");
        lblBuscar.setBounds(new Rectangle(15, 15, 50, 20));
        lblBuscar.setFont(new Font("SansSerif", 1, 12));
        txtBuscar.setBounds(new Rectangle(65, 15, 315, 20));
        pnlContenedor.add(txtBuscar, null);
        pnlContenedor.add(lblBuscar, null);
        
        lblTitulo.setText("Listado a buscar");
        lblTitulo.setBounds(new Rectangle(5, 0, 130, 20));
        panTitulo.setBounds(new Rectangle(15, 40, 365, 20));
        panTitulo.add(lblTitulo, null);
        pnlContenedor.add(panTitulo, null);
        
        scrListado.setBounds(new Rectangle(15, 60, 365, 195));
        scrListado.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrListado.getViewport().add(tblListado, null);
        pnlContenedor.add(scrListado, null);
        
        lblF11.setBounds(new Rectangle(260, 265, 117, 19));
        lblF11.setText("[ F11 ] Aceptar");
        lblEsc.setBounds(new Rectangle(125, 265, 117, 19));
        lblEsc.setText("[ Esc ] Salir");
        pnlContenedor.add(lblEsc, null);
        pnlContenedor.add(lblF11, null);
        
        this.getContentPane().add(pnlContenedor, BorderLayout.CENTER);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
    }
    
    private void initialize(){
        
    }
    
    private void cargarTabla(){
        if(lstListadoTabla.isEmpty()){
            FarmaUtility.showMessage(this, "No hay elementos a seleccionar", txtBuscar);
            cerrarVentana(false);
        }else{
            for(int i=0; i < lstListadoTabla.size(); i++){
                ArrayList<Object> lstFila = lstListadoTabla.get(i);
                if(seleccionMultiple){
                    lstFila.add(0, new Boolean(false));
                }
                modelTblListado.data.add(lstFila);
            }
            modelTblListado.fireTableDataChanged();
        }
    }
    
    private void this_windowOpened(WindowEvent e){
        FarmaUtility.centrarVentana(this);
        cargarTabla();
    }
    
    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }
    
    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }
    
    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        }
    }

    public ArrayList<ArrayList<Object>> getLstListadoTabla() {
        return lstListadoTabla;
    }

    public void setLstListadoTabla(ArrayList<ArrayList<Object>> lstListadoTabla) {
        this.lstListadoTabla = lstListadoTabla;
    }

    public ArrayList<Object> getFilaSeleccionada() {
        return filaSeleccionada;
    }

    public void setFilaSeleccionada(ArrayList<Object> filaSeleccionada) {
        this.filaSeleccionada = filaSeleccionada;
    }

    public boolean isSeleccionMultiple() {
        return seleccionMultiple;
    }

    public void setSeleccionMultiple(boolean seleccionMultiple) {
        this.seleccionMultiple = seleccionMultiple;
    }

    public boolean isIncluyeOtro() {
        return incluyeOtro;
    }

    public void setIncluyeOtro(boolean incluyeOtro) {
        this.incluyeOtro = incluyeOtro;
    }
}
