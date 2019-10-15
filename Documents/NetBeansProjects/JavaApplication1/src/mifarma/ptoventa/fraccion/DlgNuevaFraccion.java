package mifarma.ptoventa.fraccion;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaGridUtils;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.fraccion.modelo.VariableProducto;
import mifarma.ptoventa.fraccion.modelo.VariableSolicitud;
import mifarma.ptoventa.fraccion.reference.ConstantsDatos;
import mifarma.ptoventa.fraccion.reference.UtilityFraccion;
import mifarma.ptoventa.ventas.DlgListaProductos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgNuevaFraccion extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgListaProductos.class);

    private Frame myParentFrame;    
    private FarmaTableModel tblModelProductos;
    private FarmaTableModel tblModelSeleccion;
    private JTable tblProductos = new JTable();
    private JTable tblSeleccion = new JTable();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel ctpContenido = new JPanel();
    private JPanel pnlTitulo = new JPanel();
    private JPanel pnBusquedaProducto = new JPanel();
    private JPanel pnlProducto = new JPanel();
    private JPanel pnlListaProductos = new JPanel();
    private JScrollPane spnProducto = new JScrollPane();
    private JScrollPane scpSeleccion = new JScrollPane();
    private JLabel lblBuscarProducto = new JLabel();
    private JLabel lblListaProducto = new JLabel();
    private JLabelFunction lblRetiraFraccion_F2 = new JLabelFunction();
    private JLabelFunction lblSalir_Esc = new JLabelFunction();
    private JLabel lblBusqueda = new JLabel();
    private JTextField txtProducto = new JTextField();
    private JLabelFunction lblGrabar_F11 = new JLabelFunction();
    private JLabelOrange lblLeyenda = new JLabelOrange();
    private JLabel lblCantProd = new JLabel();
    private JLabelFunction lbBtnFraccion = new JLabelFunction();


    public DlgNuevaFraccion() {
        this(null, "", false);
    }

    public DlgNuevaFraccion(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(803, 506));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Nueva solicitud de fraccionamiento");
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

        pnlTitulo.setBounds(new Rectangle(15, 5, 770, 45));
        pnlTitulo.setBackground(new Color(43, 141, 39));
        pnlTitulo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlTitulo.setLayout(null);

        scpSeleccion.setBounds(new Rectangle(15, 320, 765, 110));
        scpSeleccion.setBackground(new Color(255, 130, 14));

        scpSeleccion.setNextFocusableComponent(txtProducto);
        scpSeleccion.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    tblSeleccion_focusGained(e);
                }
            });
        lblBuscarProducto.setText("Busqueda de Productos");
        lblBuscarProducto.setBounds(new Rectangle(5, 2, 145, 15));
        lblBuscarProducto.setFont(new Font("SansSerif", 1, 12));
        lblBuscarProducto.setForeground(SystemColor.window);
        
        lblListaProducto.setText("Productos seleccionados");
        lblListaProducto.setBounds(new Rectangle(10, 2, 160, 15));
        lblListaProducto.setFont(new Font("SansSerif", 1, 12));
        lblListaProducto.setForeground(SystemColor.window);


        lblRetiraFraccion_F2.setText("[F3] Retirar Fraccionamiento");
        lblRetiraFraccion_F2.setBounds(new Rectangle(20, 440, 185, 25));
        lblSalir_Esc.setText("[ESC] Cerrar");
        lblSalir_Esc.setBounds(new Rectangle(615, 440, 130, 25));

        lblBusqueda.setText("Buscar: ");
        lblBusqueda.setBounds(new Rectangle(35, 5, 50, 15));
        lblBusqueda.setBackground(SystemColor.window);
        lblBusqueda.setFont(new Font("SansSerif", 1, 12));
        txtProducto.setBounds(new Rectangle(90, 2, 480, 20));
        txtProducto.setNextFocusableComponent(tblSeleccion);
        txtProducto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtProducto_keyPressed(e);

            }

            public void keyReleased(KeyEvent e) {
                txtProducto_keyReleased(e);
            }
        });
        lblGrabar_F11.setText("[F11] Grabar");
        lblGrabar_F11.setBounds(new Rectangle(475, 440, 130, 25));
        
        lblLeyenda.setText("Productos fraccionables se muestran de color amarillo");
        lblLeyenda.setBounds(new Rectangle(295, 270, 485, 25));
        lblLeyenda.setForeground(Color.red);
        lblLeyenda.setFont(new Font("DialogInput", 1, 15));
        lblLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLeyenda.setVisible(false);
        
        lblCantProd.setBounds(new Rectangle(515, 2, 240, 15));
        lblCantProd.setFont(new Font("SansSerif", 1, 12));
        lblCantProd.setForeground(SystemColor.window);

        lblCantProd.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCantProd.setHorizontalTextPosition(SwingConstants.RIGHT);
        lbBtnFraccion.setText("[F1] Cerrar");
        lbBtnFraccion.setBounds(new Rectangle(15, 270, 280, 25));
        lbBtnFraccion.setFont(new Font("DialogInput", 1, 15));
        pnlProducto.setBounds(new Rectangle(0, 20, 770, 25));
        pnlProducto.setBackground(SystemColor.window);
        pnlProducto.setLayout(null);


        pnBusquedaProducto.setBounds(new Rectangle(15, 50, 770, 45));
        pnBusquedaProducto.setBackground(new Color(255, 130, 14));
        pnBusquedaProducto.setLayout(null);

        spnProducto.setBounds(new Rectangle(15, 95, 765, 165));
        spnProducto.setBackground(new Color(255, 130, 14));

        pnlListaProductos.setBounds(new Rectangle(15, 300, 765, 20));
        pnlListaProductos.setBackground(new Color(255, 130, 14));
        pnlListaProductos.setLayout(null);

        tblProductos.setFont(new Font("SansSerif", 0, 12));
        tblProductos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblProductos_mouseClicked(e);
            }
        });
        tblProductos.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblProductos_keyPressed(e);
                }
            });
        tblSeleccion.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tblSeleccion_mouseClicked(e);
            }
        });

        tblSeleccion.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    tblSeleccion_keyPressed(e);
                }
            });
        tblSeleccion.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    tblSeleccion_focusGained(e);
                }
            });
        scpSeleccion.getViewport();
        spnProducto.getViewport();

        ctpContenido.add(lbBtnFraccion, null);
        ctpContenido.add(lblLeyenda, null);
        ctpContenido.add(lblGrabar_F11, null);
        ctpContenido.add(lblSalir_Esc, null);
        ctpContenido.add(lblRetiraFraccion_F2, null);
        ctpContenido.add(pnlTitulo, null);
        scpSeleccion.getViewport().add(tblSeleccion, null);
        ctpContenido.add(scpSeleccion, null);
        pnBusquedaProducto.add(lblCantProd, null);
        pnBusquedaProducto.add(lblBuscarProducto, null);

        pnlProducto.add(txtProducto, null);
        pnlProducto.add(lblBusqueda, null);
        pnBusquedaProducto.add(pnlProducto, null);
        ctpContenido.add(pnBusquedaProducto, null);
        spnProducto.getViewport().add(tblProductos, null);
        ctpContenido.add(spnProducto, null);
        pnlListaProductos.add(lblListaProducto, null);
        ctpContenido.add(pnlListaProductos, null);
        this.getContentPane().add(ctpContenido, BorderLayout.CENTER);
    }

    private void initialize() {
        initListaProductos();
        initListaSeleccion();
        lbBtnFraccion.setVisible(false);        
    }

    void initListaProductos() {
        tblModelProductos =
                new FarmaTableModel(ConstantsDatos.columnsListaProducto, ConstantsDatos.defaultValuesListaProducto, 0);
        FarmaUtility.initSimpleList(tblProductos, tblModelProductos, ConstantsDatos.columnsListaProducto);
        //cargaRelacionProductos(VariableProducto.tblModelRelacion_Prod);
        CargarListaProductos();
    }

    void initListaSeleccion() {
        tblModelSeleccion =
                new FarmaTableModel(ConstantsDatos.columnsProducto_Seleccion, ConstantsDatos.defaultValuesProducto_Seleccion,
                                    0);
        FarmaUtility.initSimpleList(tblSeleccion, tblModelSeleccion, ConstantsDatos.columnsProducto_Seleccion);
        cargarListaSeleccion();
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        VariableSolicitud.vCod_GrupCIA=FarmaVariables.vCodGrupoCia;
        VariableSolicitud.vCod_Local=FarmaVariables.vCodLocal;
        VariableSolicitud.vUsua_Solic=FarmaVariables.vIdUsu;
        VariableSolicitud.vCod_Local_Mod=FarmaVariables.vCodLocal;
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    private void cerrarVentana() {
        if(tblSeleccion.getRowCount()>0){
            String msj = "Tiene productos seleccionados para fraccionamiento\nSi sale se perderan los datos";
            boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
            if (ok ) {
                this.setVisible(false);
                this.dispose();
            }
        }else{
            this.setVisible(false);
            this.dispose();
        }
    }

    private void CargarListaProductos() {
        UtilityFraccion.cargarListaProductos_Memoria(tblModelProductos);
        //UtilityFraccion.cargarListaProductos(tblModelProductos);
        lblCantProd.setText("Cant. Prod. " + tblModelProductos.getRowCount());
        ArrayList rowsWithOtherColor = new ArrayList();
        for (int i = 0; i < tblModelProductos.data.size(); i++) {
            if (((ArrayList)tblModelProductos.data.get(i)).get(8).toString().trim().equalsIgnoreCase("S")) { 
                rowsWithOtherColor.add(String.valueOf(i));
            }
        }
        
        FarmaUtility.initSimpleListCleanColumns(tblProductos, tblModelProductos,
                                                ConstantsDatos.columnsListaProducto, rowsWithOtherColor,
                                                Color.yellow, Color.blue, false);

        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.getTableHeader().setResizingAllowed(false);
        
    }

    private void cargarListaSeleccion() {
        tblModelSeleccion.clearTable();
        tblModelSeleccion.data=VariableProducto.listaSeleccion;
        /*if (VariableProducto.listaSeleccion.size() > 0) {
            for (int i = 0; i < VariableProducto.listaSeleccion.size(); i++) {
                tblModelSeleccion.insertRow(VariableProducto.listaSeleccion.get(i));
            }
        }*/
        txtProducto.setText("");
    }

    private void txtProducto_keyPressed(KeyEvent e) {
        FarmaGridUtils.aceptarTeclaPresionada(e, tblProductos, txtProducto, 1);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //e.consume();
            boolean buscaXcodigo=false;
            boolean buscar=true;
            String txtAux=txtProducto.getText().trim();
            if(txtAux.length()<=20){
                String codProd=UtilityFraccion.buscaCodProd_CodBarra(txtAux);
                if(codProd!=null){
                    txtAux=codProd;
                    buscaXcodigo=true;
                }else{
                    buscaXcodigo=verificaDigitos(txtAux);
                }
            }
            
            if(buscaXcodigo){
                buscaCodigoEnJtable(txtAux);
                int vFila = tblProductos.getSelectedRow();
                String codAux = FarmaUtility.getValueFieldJTable(tblProductos, vFila, 0);
                if(!txtAux.equalsIgnoreCase(codAux)){
                    FarmaUtility.showMessage(this, "Codigo no encontrado", txtProducto);
                    lbBtnFraccion.setVisible(false);
                    buscar=false;
                }
            }
            if(buscar){
                if (tieneRegistroSeleccionado(tblProductos)) {
                    String codProd = (String)tblProductos.getValueAt(tblProductos.getSelectedRow(), 0);
                    boolean prodSelec = buscarCodigo_enSeleccion(codProd);
                    if (!prodSelec) {
                        if (esFraccionable(codProd)) {
                            VariableProducto.vTipo_Frac = "";
                            VariableProducto.vCod_Motivo_Frac = "";
                            //DlgDetalleFraccion dlgFraccionProducto = new DlgDetalleFraccion(myParentFrame, "", true);
                            //dlgFraccionProducto.setVisible(true);
                            seleccionaProducto();
                        } else {
                            FarmaUtility.showMessage(this, "Advertencia: El producto no puede ser fraccionado", txtProducto);
                            lbBtnFraccion.setText("Producto no fraccionable");
                            lbBtnFraccion.setForeground(Color.red);
                            lbBtnFraccion.setVisible(true);
                        }
                    } else {
                        FarmaUtility.showMessage(this, "El producto ya fue seleccionado", txtProducto);
                        lbBtnFraccion.setVisible(false);
                    }
                }
            }   
        }else{
            switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                seleccionaProducto();
                break;
            case KeyEvent.VK_TAB:
                lbBtnFraccion.setVisible(false);
                if(tblSeleccion.getRowCount()>0){
                    FarmaUtility.moveFocusJTable(tblSeleccion);
                }else{
                    FarmaUtility.moveFocus(txtProducto);
                }
                break;
            default:
                chkKeyPressed(e);
            }
        }        
    }
    
    private void seleccionaProducto() {
        int i = tblProductos.getSelectedRow();
        String codProd = FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i, 0);
        String indFrac = FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i, 8);
        if(indFrac.equalsIgnoreCase("S")){
            VariableProducto.vCod_Prod=codProd;
            int fraccionActual=UtilityFraccion.recuperaFraccionActual(VariableProducto.vCod_Prod);
            if(fraccionActual>0){
                evaluarProducto();
            }
        }else{
            lbBtnFraccion.setText("Producto no fraccionable");
            lbBtnFraccion.setForeground(Color.red);
            lbBtnFraccion.setVisible(true);
        }
    }
    
    private void buscaCodigoEnJtable(String pCodBusqueda) {
        String codTmp = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            codTmp = FarmaUtility.getValueFieldJTable(tblProductos, i, 0);
            if (codTmp.equalsIgnoreCase(pCodBusqueda)) {
                FarmaGridUtils.showCell(tblProductos, i, 0);
                FarmaUtility.setearRegistro(tblProductos, i, txtProducto, 1);
                FarmaUtility.moveFocus(txtProducto);
                return;
            }
        }
    }
    
    private boolean verificaDigitos(String txtAux) {
       boolean noCod=true;
        for(int i=0;i<txtAux.length();i++){
            String cChar =txtAux.substring(i, i+1);
            try{
                if(Integer.parseInt(cChar)>=0){
                    log.info("NUMERO");
                }
            }catch(Exception e){
                log.info("***CARACTER**");
                noCod=false;
                break;
            }
        }
        return noCod;
    }
    
    private void txtProducto_keyReleased(KeyEvent e) {
        FarmaGridUtils.buscarDescripcion(e, tblProductos, txtProducto, 1);
    }

    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;
        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    private boolean esFraccionable(String codProd) {
        VariableProducto.vCod_Prod = codProd.toString();
        UtilityFraccion.recuperoProducto_Fracionable();
        String vFracion = VariableProducto.vInd_Fracionable;
        if (vFracion.equalsIgnoreCase("S")) {
            return true;
        } else {
            return false;
        }
    }


    private void retiraFraccion() {
        //FarmaUtility.showMessage(this, "=> "+tblSeleccion.getRowCount()+"\n"+tblSeleccion.getSelectedRow(), txtProducto);
        String msj="Desea retirar el producto de la lista de fraccionamientos";
        boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
        if(ok){
            if (tblSeleccion.getRowCount() > 0) {
                int index = tblSeleccion.getSelectedRow();
                String codProd=FarmaUtility.getValueFieldArrayList(tblModelSeleccion.data, index, 0);
                for (int i = 0; i < VariableProducto.listaSeleccion.size(); i++) {
                    String codLista = VariableProducto.listaSeleccion.get(i).get(0).toString();
                    if (codProd.equalsIgnoreCase(codLista)) {
                        VariableProducto.listaSeleccion.remove(i);
                        break;
                    }
                }
                for (int i = 0; i < VariableProducto.listaProductos.size(); i++) {
                    String codLista = VariableProducto.listaProductos.get(i).get(0).toString();
                    if (codProd.equalsIgnoreCase(codLista)) {
                        VariableProducto.listaProductos.remove(i);
                        break;
                    }
                }
                tblModelSeleccion.clearTable();
                cargarListaSeleccion();
                if(tblSeleccion.getRowCount()>0){
                    FarmaUtility.moveFocusJTable(tblSeleccion);
                }else{
                    txtProducto.setText("");
                    FarmaUtility.moveFocus(txtProducto);
                }
            } else {
                FarmaUtility.showMessage(this, "No existen productos seleccionados para fraccionamiento", txtProducto);
            }
        }
    }

    private void tblProductos_mouseClicked(MouseEvent e) {
        int i = tblProductos.getSelectedRow();
        txtProducto.setText(FarmaUtility.getValueFieldArrayList(tblModelProductos.data, i, 2));
        FarmaUtility.moveFocus(txtProducto);
        /* if (e.isMetaDown() ){//boton derecho del mouese
             vEjecutaAccionTeclaListado = false;
         }*/
    }

    private void tblSeleccion_mouseClicked(MouseEvent e) {
        if(tblSeleccion.getRowCount()>0){
            txtProducto.setText("");
        }else{
            FarmaUtility.moveFocus(txtProducto);
        }
    }

    private void grabaSolicitud_Fraccionamiento() {
        String msj="Confirma que desea grabar los datos como nueva solicitud ";
        boolean ok=JConfirmDialog.rptaConfirmDialogDefaultNo(this,msj, "Si");
        if(ok){
            ArrayList<ArrayList> okSave = UtilityFraccion.grabaNuevaSolicitud_Fracionamiento();
            if (okSave.size() == 0) {
                try{
                    String msjAtencion=UtilityFraccion.atenderSolicitudes_Pendientes();
                    if(msjAtencion==null){
                        //UtilityFraccion.enviarDatos_APPS();
                        FarmaUtility.showMessage(this, "Se grabaron correctamento todos los datos", null);
                        tblModelSeleccion.clearTable();
                    }else{
                        FarmaUtility.showMessage(this, msjAtencion, null);
                        tblModelSeleccion.clearTable();
                    }
                }catch(Exception e ){
                    FarmaUtility.showMessage(this, "Se produjo un error al enviar la solicitud a matriz!", null);
                    e.printStackTrace();
                }
                cerrarVentana();
            } else {
                if (okSave.size() == 1) {
                    String detError = okSave.get(0).get(0).toString();
                    String codError = okSave.get(0).get(1).toString();
                    FarmaUtility.showMessage(this,
                                             "Se produjo errores en el proseso de grabar los datos\nIntentelo despues." +
                                             "\n(" + detError + "-" + codError+")", null);
                } else {
                    String cad="Errores:\n";
                    for(int i=0;i<okSave.size();i++){
                        for(int j=0;j<okSave.get(i).size();j++){
                            System.out.println("==> "+okSave.get(i).get(j));
                            cad+=okSave.get(i).get(j)+" ";
                        }
                        cad+="\n";
                    }
                    FarmaUtility.showMessage(this,
                                             "Se produjo multiples errores en el proseso de grabar los datos\n" +cad+
                                             "\nIntentelo despues.",
                                             txtProducto);
                }
                cerrarVentana();
            }
        }
    }

    private void tblProductos_keyPressed(KeyEvent e) {        
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            if(tblSeleccion.getRowCount()>0){
                FarmaUtility.moveFocusJTable(tblSeleccion);
            }else{
                FarmaUtility.moveFocus(txtProducto);
            }
        }else{
            chkKeyPressed(e);
        }
    }
    
    private void tblSeleccion_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F3) {
            if (tblSeleccion.getRowCount() > 0) {
                if (tieneRegistroSeleccionado(tblSeleccion)) {
                    retiraFraccion();
                }
                FarmaUtility.moveFocusJTable(tblSeleccion);
            } else {
                FarmaUtility.moveFocus(txtProducto);
            }
            e.consume();
        }
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            FarmaUtility.moveFocus(txtProducto);
            e.consume();
        }
        chkKeyPressed(e);
        /*
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            FarmaUtility.moveFocus(txtProducto);
        }else{
            chkKeyPressed(e);
        }*/
    }

    private void chkKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
        case KeyEvent.VK_ESCAPE: cerrarVentana();break;
        case KeyEvent.VK_F1:
        case KeyEvent.VK_F2:
            if(lbBtnFraccion.isVisible()){
                ArrayList<ArrayList> option = new ArrayList();
                UtilityFraccion.recuperaOptionTipo(option, "2");
                String txt = lbBtnFraccion.getText();
                String txtFuncion = (String)txt.subSequence(0,txt.indexOf(" "));
                if(txtFuncion.equalsIgnoreCase("[F1]")||txtFuncion.equalsIgnoreCase("[F2]")){
                    if((txtFuncion.equalsIgnoreCase("[F1]")&&(e.getKeyCode()==KeyEvent.VK_F1))){
                        //FarmaUtility.showMessage(this, "=> "+txtFuncion+" Cerrar Fracion", null);
                        VariableProducto.vTipo_Frac = option.get(0).get(0).toString().trim();
                        VariableProducto.vDesc_Tip_Frac = option.get(0).get(1).toString().trim();
                        VariableProducto.vDesc_FracCambio=UtilityFraccion.recuperaFraccion_Cambio().trim();
                        buscarProducto();                        
                    }
                    if((txtFuncion.equalsIgnoreCase("[F2]")&&(e.getKeyCode()==KeyEvent.VK_F2))){
                        //FarmaUtility.showMessage(this, "=> "+txtFuncion+" Fracionar producto", null);
                        VariableProducto.vTipo_Frac = option.get(1).get(0).toString().trim();
                        VariableProducto.vDesc_Tip_Frac = option.get(1).get(1).toString().trim();
                        VariableProducto.vDesc_FracCambio=UtilityFraccion.recuperaFraccion_Cambio().trim();
                        buscarProducto();                        
                    }
                    cargarListaSeleccion();
                }
            }
            break;
        case KeyEvent.VK_F11:
            if(tblSeleccion.getRowCount()>0){
                grabaSolicitud_Fraccionamiento();
                DlgSolicitudFraccion dlg=new DlgSolicitudFraccion();
                dlg.repaint();
            }else{
                FarmaUtility.showMessage(this, "Debe de seleccionar productos para fraccionar" +
                    "\nantes de proceder a grabar la nueva solicitud", txtProducto);
            }
            break;
        default:
            lbBtnFraccion.setVisible(false);
        }
    }

    private void tblSeleccion_focusGained(FocusEvent e) {
        if (tblSeleccion.getRowCount() > 0) {
            FarmaUtility.moveFocusJTable(tblSeleccion);
        } else {
            FarmaUtility.moveFocus(txtProducto);
        }
    }

    private boolean buscarCodigo_enSeleccion(String codProd) {
        boolean seleccionado=false;
        String codTmp = "";
        for (int i = 0; i < tblSeleccion.getRowCount(); i++) {
            codTmp = FarmaUtility.getValueFieldJTable(tblSeleccion, i, 0);
            if (codTmp.equalsIgnoreCase(codProd)) {
                seleccionado=true;
                String descProd=FarmaUtility.getValueFieldJTable(tblSeleccion, i, 1);
                txtProducto.setText(descProd);
                break;
            }
        }
        return seleccionado;
    }

    private void evaluarProducto() {
        int fraccionActual=UtilityFraccion.recuperaFraccionActual(VariableProducto.vCod_Prod);
        if(fraccionActual>0){
            switch(fraccionActual){
            case 1:
                lbBtnFraccion.setText("[F2] Fracionar Producto");
                lbBtnFraccion.setForeground(Color.BLACK);
                lbBtnFraccion.setVisible(true);
                break;
            case 2:
                lbBtnFraccion.setText("[F1] Cerrar Fraccion");
                lbBtnFraccion.setForeground(Color.BLACK);
                lbBtnFraccion.setVisible(true);
                break;
            }
        }else if(fraccionActual==0){
            FarmaUtility.showMessage(this, "El producto no tiene un valor de fraccionamiento correcto", null);
            cerrarVentana();
        }else{
            FarmaUtility.showMessage(this, "Se produjo un error con la conexion a la base de datos", null);
            cerrarVentana();
        }
        
    }

    private void finalizaSelecion() {
        String fracValida=UtilityFraccion.validaFraccionamiento(FarmaVariables.vCodLocal,VariableProducto.vCod_Prod,VariableProducto.vTipo_Frac);
        if(fracValida.equalsIgnoreCase("Ok")){
            DlgDetalleFraccion dlg = new DlgDetalleFraccion();
            dlg.insertarDetalle();
            cargarListaSeleccion();
        }else{
            FarmaUtility.showMessage(this, fracValida, null);            
        }
    }

    private void buscarProducto() {
        boolean prodSelec = buscarCodigo_enSeleccion(VariableProducto.vCod_Prod);
        if (!prodSelec) {
            int nroReg=UtilityFraccion.verificaProdEnSolicitudes(VariableProducto.vCod_Prod);
            if(nroReg==0){
                UtilityFraccion.recuperoProducto_Fracionable();
                seleccionaProducto();
                finalizaSelecion();
            }else if(nroReg>0){
                FarmaUtility.showMessage(this, "El producto ya fue solicitado en un registro anterior." +
                    "\nEspere a que la solicitud sea atendiada por Administracion.", txtProducto);
                lbBtnFraccion.setVisible(false);
            }else{
                FarmaUtility.showMessage(this, "Se produjo un error al consultar la BD", txtProducto);
                lbBtnFraccion.setVisible(false);
            }
        } else {
            FarmaUtility.showMessage(this, "El producto ya fue seleccionado", txtProducto);
            lbBtnFraccion.setVisible(false);
        }
    }
}
