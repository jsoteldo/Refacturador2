package mifarma.ptoventa.fraccion;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
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
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.fraccion.modelo.VariableProducto;
import mifarma.ptoventa.fraccion.reference.UtilityFraccion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgDetalleFraccion extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgDetalleFraccion.class);

    private Frame myParentFrame;
    private ArrayList<List> varMotivos = new ArrayList();

    private JPanelWhite ctpContenido = new JPanelWhite();
    private GridLayout gridLayout1 = new GridLayout();
    private JPanelTitle pnlMotivo = new JPanelTitle();
    private JButtonLabel btnSeleccion = new JButtonLabel();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JRadioButton rbtEntero = new JRadioButton();
    private JRadioButton rbtUnidad = new JRadioButton();
    private ButtonGroup grupoRadio = new ButtonGroup();
    private JLabelFunction lblTipoFraccion = new JLabelFunction();

    public DlgDetalleFraccion() {
        this(null, "", false);
    }

    public DlgDetalleFraccion(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(403, 187));
        this.getContentPane().setLayout(gridLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Detalle de fraccionamiento");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        btnSeleccion.setText("Seleccione motivo");
        btnSeleccion.setBounds(new Rectangle(5, 0, 160, 25));
        btnSeleccion.setBackground(new Color(255, 130, 14));
        btnSeleccion.setForeground(new Color(255, 130, 14));
        btnSeleccion.setMnemonic('S');
        btnSeleccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSeleccion_actionPerformed(e);
            }
        });

        pnlMotivo.setBounds(new Rectangle(5, 15, 375, 90));
        pnlMotivo.setBackground(Color.white);
        pnlMotivo.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        lblEnter.setBounds(new Rectangle(15, 125, 115, 20));
        lblEnter.setText("[ ENTER ] Aceptar");
        lblEsc.setBounds(new Rectangle(265, 125, 105, 20));
        lblEsc.setText("[ ESC ] Escape");


        rbtEntero.setText("Ent");
        rbtEntero.setBounds(new Rectangle(15, 20, 180, 20));
        rbtEntero.setBackground(SystemColor.window);
        rbtEntero.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    chkKeyPressed(e);
                }
            });
        rbtUnidad.setText("Unid");
        rbtUnidad.setBounds(new Rectangle(200, 20, 170, 20));
        rbtUnidad.setBackground(SystemColor.window);
        rbtUnidad.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    chkKeyPressed(e);
                }
            });
        lblTipoFraccion.setText("[F2] Seleccione opcion de fraccionamiento");
        lblTipoFraccion.setBounds(new Rectangle(50, 60, 265, 20));
        grupoRadio.add(rbtEntero);
        grupoRadio.add(rbtUnidad);


        pnlMotivo.add(btnSeleccion, null);
        pnlMotivo.add(rbtUnidad, null);
        pnlMotivo.add(rbtEntero, null);
        pnlMotivo.add(lblTipoFraccion, null);
        ctpContenido.add(lblEnter, null);
        ctpContenido.add(pnlMotivo, null);
        ctpContenido.add(lblEsc, null);
        this.getContentPane().add(ctpContenido, null);
    }

    private void initialize() {
        FarmaVariables.vAceptar = false;
        carga_optionTipo();
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        validaFraccion_Actual();
        btnSeleccion.doClick();        
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    
    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:cerrarVentana();break;
        case KeyEvent.VK_F2:seleccionaFraccion();break;
        case KeyEvent.VK_ENTER:seleccionaTipo_Fraccion();break;
        }
    }

    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    private void seleccionaTipo_Fraccion() {
        recuperaNombreFraccion();
        if(!VariableProducto.vTipo_Frac.equalsIgnoreCase("")){
            finalizaSelecion();
        }else{
            FarmaUtility.showMessage(this, "Selecciono un fraccionamiento no valido", null);
            btnSeleccion.doClick();
        }
    }

    private void seleccionaFraccion() {
        //FarmaUtility.showMessage(this, "AQUI", null);
        boolean valor=false;
        if(!rbtEntero.isEnabled()){
            valor=rbtUnidad.isSelected();
            rbtUnidad.setSelected(!valor);
        }else{
            valor=rbtEntero.isSelected();
            rbtEntero.setSelected(!valor);
        }
    }

    private void recuperaNombreFraccion() {
        if (rbtEntero.isSelected()) {
            VariableProducto.vTipo_Frac = option.get(0).get(0).toString().trim();
            VariableProducto.vDesc_Tip_Frac = option.get(0).get(1).toString().trim();
        }
        if (rbtUnidad.isSelected()) {
            VariableProducto.vTipo_Frac = option.get(1).get(0).toString().trim();
            VariableProducto.vDesc_Tip_Frac = option.get(1).get(1).toString().trim();
        }
        VariableProducto.vDesc_FracCambio=UtilityFraccion.recuperaFraccion_Cambio().trim();
    }

    ArrayList pProducto = new ArrayList();
    ArrayList pSeleccion = new ArrayList();

    private void almacenaSeleccionProductos() {

        pProducto.add(VariableProducto.vCod_Prod); //PA BD
        pProducto.add(VariableProducto.vCod_Lab); //PA BD
        pProducto.add(VariableProducto.vCod_Motivo_Frac); //PA BD
        pProducto.add(VariableProducto.vTipo_Frac); //PA BD
        pProducto.add(VariableProducto.vComentario); //PA BD(OJO)
        
        pSeleccion.add(VariableProducto.vCod_Prod);
        pSeleccion.add(VariableProducto.vDesc_Prod);
        pSeleccion.add(VariableProducto.vDesc_Unid_Pres);
        pSeleccion.add(VariableProducto.vDesc_Lab);
        pSeleccion.add(recuperaDesc_FraccionActual(VariableProducto.vCod_Prod));
        pSeleccion.add(VariableProducto.vDesc_FracCambio);
        pSeleccion.add(VariableProducto.vDesc_Tip_Frac);
        VariableProducto.listaProductos.add(pProducto);
        VariableProducto.listaSeleccion.add(pSeleccion);
    }
    
    private String recuperaDesc_FraccionActual(String codProd) {
        return UtilityFraccion.recuperaDesc_FraccionActual(codProd);
    }
    
    private void btnSeleccion_actionPerformed(ActionEvent e) {
        if(rbtEntero.isEnabled()){
            FarmaUtility.moveFocus(rbtEntero);
        }else{
            FarmaUtility.moveFocus(rbtUnidad);
        }
    }

    private void finalizaSelecion() {
        String fracValida=UtilityFraccion.validaFraccionamiento(FarmaVariables.vCodLocal,VariableProducto.vCod_Prod,VariableProducto.vTipo_Frac);
        if(fracValida.equalsIgnoreCase("Ok")){
            String msj =
                "Ha seleccionado como tipo de fraccionamiento: " 
                + VariableProducto.vDesc_Tip_Frac;
            
            if (JConfirmDialog.rptaConfirmDialog(this, msj)) {
                almacenaSeleccionProductos();
                DlgNuevaFraccion dlgNuevaFraccion = new DlgNuevaFraccion(myParentFrame, "", true);
                cerrarVentana();
                dlgNuevaFraccion.repaint();
            }
        }else{
            FarmaUtility.showMessage(this, fracValida+"\nNo se guardaran los datos", null);
            DlgNuevaFraccion dlgNuevaFraccion = new DlgNuevaFraccion(myParentFrame, "", true);
            cerrarVentana();
            dlgNuevaFraccion.repaint();
        }
    }

    ArrayList<ArrayList> option = new ArrayList();

    private void carga_optionTipo() {
        UtilityFraccion.recuperaOptionTipo(option, "2");
        rbtEntero.setText(option.get(0).get(1).toString());
        rbtUnidad.setText(option.get(1).get(1).toString());
    }

    private void validaFraccion_Actual() {
        int fraccionActual=UtilityFraccion.recuperaFraccionActual(VariableProducto.vCod_Prod);
        if(fraccionActual>0){
            switch(fraccionActual){
            case 1:rbtEntero.setEnabled(false);break;
            case 2:rbtUnidad.setEnabled(false);break;
            }
        }else if(fraccionActual==0){
            FarmaUtility.showMessage(this, "El producto no tiene un valor de fraccionamiento correcto", null);
            cerrarVentana();
        }else{
            FarmaUtility.showMessage(this, "Se produjo un error con la conexion a la base de datos", null);
            cerrarVentana();
        }
    }

    void insertarDetalle() {
        almacenaSeleccionProductos();
    }
}
