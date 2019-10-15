package com.gs.mifarma.componentes;

import java.util.EventListener;
import java.util.Set;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import mifarma.common.FarmaUtility;

public class JComboWithCheck extends JComboBox {

    private List<ObjCheckBox> lstObjMostrarCheckBox;
    private transient Map<Object, Boolean> mapObjComboBox;
    private transient Object nullObject = new Object();
    private boolean optionSelectedAll = false;
    private boolean optionSelectedNone = false;
    private boolean presionoEnter = false;
    private transient Object objTransferFocus = null;
    private boolean seleccionable = true;
    private boolean permiteEventoEsc = true;
    private boolean permiteEventoEnter = true;
    private int contadorEventoEsc = 0;
    private boolean seleccionMultiple = true;
    /**
     * Constructor con lista de objetos a ubicar en el ComboBox
     * @param setObjects lista de objectos de la clase Option
     */
    /*public JComboWithCheck(final Set setObjects) {
        this(setObjects, false);
    }*/
    
    public JComboWithCheck() {
        mapObjComboBox = new LinkedHashMap<Object, Boolean>();
        reset();
    }

    /**
     * Constructor con lista de objetos a ubicar en el ComboBox con valor de true/false todos los items
     * @param setObjects lista de objectos de la clase Option
     * @param selected este indicador marcara todos las opciones segun el valor true/false
     */
    public JComboWithCheck(final Set setObjects, boolean selected) {
        mapObjComboBox = new LinkedHashMap<Object, Boolean>();
        for (Object obj : setObjects) {
            mapObjComboBox.put(obj, selected);
        }
        reset();
    }
    
    /**
     * Constructor con la lista de objetos a ubicar en el ComboBox con su indicador C/U si esta seleccionado/marcado.
     * @param mapObjSelected lista de objectos <Option, Boolean>
     */
    public JComboWithCheck(Map<Object, Boolean> mapObjSelected) {
        this.mapObjComboBox = mapObjSelected;
        reset();
    }
    
    /**
     * devuelve la lista de Objectos que se encuentran marcadas en el combobox
     * @return NULO en caso ninguna opcion este marcada.
     */
    public Object[] getSelectedItems() {
        List<Object> lstObj = new ArrayList<Object>();
        for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
            OptionComboBox obj = (OptionComboBox)entry.getKey();
            Boolean selected = entry.getValue();

            if (selected) {
                lstObj.add(obj);
            }
        }

        if (lstObj.isEmpty())
            return null;

        return lstObj.toArray(new Object[lstObj.size()]);
    }
    
    /**
     * Agrega item al combobox
     * @param obj
     * @param isSelect
     */
    public void addItem(Object obj, boolean isSelect) {
        if(obj instanceof OptionComboBox){
            mapObjComboBox.put(obj, isSelect);
            reset();
        }else
            throw new RuntimeException("objecto a agregar no es de tipo OptionComboBox.");
    }
    
    /**
     * Agrega item al combobox en la posicion indicada
     * @param obj objeto que se agregara
     * @param isSelect indicador si item estara marcado o no
     * @param posicion posicion donde se agregara el item: posicion inicial = 0
     */
    public void addItem(Object obj, boolean isSelect, int posicion) {
        if(posicion >= 0 && posicion < mapObjComboBox.size()){
            Map<Object, Boolean> mapTemp = mapObjComboBox;
            mapObjComboBox = new LinkedHashMap<Object, Boolean>();
            int cont = 0;
            for(Map.Entry<Object, Boolean> entry : mapTemp.entrySet()){
                OptionComboBox objEntry = (OptionComboBox)entry.getKey();
                Boolean selected = objEntry.isSeleccionado();//entry.getValue();
                if(cont == posicion)
                    mapObjComboBox.put(obj, isSelect);
                mapObjComboBox.put(objEntry, selected);
                cont++;
            }
            reset();
        }else{
            throw new RuntimeException("posicion erronea: "+posicion+", posiciones [0, "+(mapObjComboBox.size()-1)+"]");
        }
    }
    
    /**
     *
     * @param mapObjSelected
     */
    public void addItems(Map<Object, Boolean> mapObjSelected) {
        this.mapObjComboBox = mapObjSelected;
        reset();
    }
    /**
     * Agrega la lista de objetos al comboBox
     * @param c
     */
    public void addSelectedItems(Collection c) {
        if (c == null)
            return;

        for (Object obj : c) {
            if (mapObjComboBox.containsKey(obj)) {
                mapObjComboBox.put(obj, true);
            }
        }

        reset();
        repaint();
    }
    
    /**
     * Agrega la lista de objetos al comboBox
     * @param objs
     */
    public void addSelectedItems(Object[] objs) {
        if (objs == null)
            return;

        for (Object obj : objs) {
            if (mapObjComboBox.containsKey(obj)) {
                mapObjComboBox.put(obj, true);
            }
        }

        reset();
        repaint();
    }
    
    /**
     * 
     * @return
     */
    public int totalItems(){
        return mapObjComboBox.size();
    }
    
    public Map getElementos(){
        return mapObjComboBox;
    }
    
    public ArrayList<OptionComboBox> getElementosSeleccionados(){
        ArrayList<OptionComboBox> list = new ArrayList<OptionComboBox>();
        for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
            OptionComboBox obj = (OptionComboBox)entry.getKey();
            Boolean selected = entry.getValue();
            if(selected)
                list.add(obj);
        }
        return list;
    }
    
    /**
     *
     * @param index
     * @return
     */
    public Object getCodigoElementAt(){
        Object codigo = null;
        for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
            OptionComboBox obj = (OptionComboBox)entry.getKey();
            Boolean selected = entry.getValue();
            if(selected){
                codigo = obj.getCodigo();
            }
        }
        return codigo;

    }
    
    /**
     *
     * @param index
     * @return
     */
    public String getLabelElementAt(){
        String label = null;
        for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
            OptionComboBox obj = (OptionComboBox)entry.getKey();
            Boolean selected = entry.getValue();
            if(selected){
                label = obj.getLabel();
            }
        }
        return label;

    }
    
    /**
     * 
     */
    private void reset() {
        this.setFont(new Font("SansSerif", 0, 11));
        this.removeAllItems();
        initCBs();
        //  se crea la primera opcion del como en blanco para mostrar lo seleccionado
        this.addItem(new String());
        // se agrega los demas CheckBox al comboBox
        for (JCheckBox cb : lstObjMostrarCheckBox) {
            this.addItem(cb);
        }
        // se agrega el render para mostrar correctamente el comboBox con los checkBox
        setRenderer(new CheckBoxRenderer(lstObjMostrarCheckBox));
        // se agrega el listener al comboBox para capturar la tecla Enter.
        addKeyListener(new CheckBoxKey());
        addActionListener(this);
    }
    
    /**
     * indicador para agregar opcion MARCAR TODO en el combobox
     */
    public void addItemSelectedAll() {
        optionSelectedAll = true;
        reset();
    }
    
    /**
     * indicador para agregar opcion DESMARCAR TODO en el combobox
     */
    public void addItemSelectedNone() {
        optionSelectedNone = true;
        reset();
    }
    
    /**
     * inicializa/construye el combobox
     * 
     */
    private void initCBs() {
        lstObjMostrarCheckBox = new Vector<ObjCheckBox>();
        boolean isSelectedNone = true;
        boolean isSelectedAll = true;
        ObjCheckBox cb;
        for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
            OptionComboBox obj = (OptionComboBox)entry.getKey();
            Boolean selected = obj.isSeleccionado();//entry.getValue();

            if (selected) {
                isSelectedNone = false;
            } else {
                isSelectedAll = false;
            }

            cb = new ObjCheckBox(obj);
            cb.setSelected(selected);
            lstObjMostrarCheckBox.add(cb);
        }
        
        //
        /*if(optionSelectOther){
            cb = new ObjCheckBox("Otros");
            cb.setSelected(false);
            lstObjMostrarCheckBox.add(cb);
        }*/
        // valida si se agrega la opcion de marcar todo
        if (optionSelectedAll) {
            cb = new ObjCheckBox("Marcar todo");
            cb.setSelected(isSelectedAll);
            lstObjMostrarCheckBox.add(cb);
        }
        
        // valida si se agrega la opcion de desmarcar todo
        if (optionSelectedNone) {
            cb = new ObjCheckBox("Desmarcar todo");
            cb.setSelected(isSelectedNone);
            lstObjMostrarCheckBox.add(cb);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int opcionSeleccionada = getSelectedIndex();
        if (opcionSeleccionada == 0) {
            //getUI().setPopupVisible(this, false);
            //cuando la lista este llena no hace nada
            menuComboOcultar(false);
        } else if (opcionSeleccionada > 0) {
            if(isSeleccionable()){
                //cuando se pulsa click sobre la opcion del combobox
                boolean aplicaCheck = false;
                if (e.getModifiers() == ActionEvent.MOUSE_EVENT_MASK)
                    aplicaCheck = true;
                else //cuando se pulse enter sobre la opcion del combobox
                    aplicaCheck = presionoEnter;
                //valida si aplicara o no logica de dar check
                if (aplicaCheck) 
                    checkBoxSelectionChanged(opcionSeleccionada - 1);
            }
        }
        this.setSelectedIndex(-1); // clear selection
    }
    
    private void checkBoxSelectionChanged(int indiceSeleccionado) {
        checkBoxSelectionChanged(indiceSeleccionado, null);
    }
    /**
     *
     * @param indiceSeleccionado
     */
    private void checkBoxSelectionChanged(int indiceSeleccionado, String label) {
        int totalOpciones = lstObjMostrarCheckBox.size();
        if (indiceSeleccionado < 0 || indiceSeleccionado >= totalOpciones)
            return;
        // desmarcado todo
        if (optionSelectedNone && indiceSeleccionado == (totalOpciones - 1)) {
            for (Object obj : mapObjComboBox.keySet()) 
                mapObjComboBox.put(obj, false);

            for (int i = 0; i < totalOpciones - 1; i++) 
                lstObjMostrarCheckBox.get(i).setSelected(false);
            
            lstObjMostrarCheckBox.get(totalOpciones - 1).setSelected(true);
        } else {
            // marcar todo
            if ((optionSelectedAll && !optionSelectedNone && indiceSeleccionado == (totalOpciones - 1)) ||
                (optionSelectedAll && optionSelectedNone && indiceSeleccionado == (totalOpciones - 2))) {
                for (Object obj : mapObjComboBox.keySet()) 
                    if (obj != nullObject) 
                        mapObjComboBox.put(obj, true);

                for (int i = 0; i < totalOpciones; i++) 
                    if (lstObjMostrarCheckBox.get(i) != nullObject) 
                        lstObjMostrarCheckBox.get(i).setSelected(true);
                
                if (optionSelectedNone) 
                    lstObjMostrarCheckBox.get(totalOpciones - 1).setSelected(false);
                
            } else {
                ObjCheckBox cb = lstObjMostrarCheckBox.get(indiceSeleccionado);
                OptionComboBox option = (OptionComboBox)cb.getObj();
                // si es una opcion que permite ingreso de datos "OPCION OTROS".
                if(option.isOpcionOtro()){
                    String campoOtro = "";
                    if(cb.isSelected())
                        campoOtro = null;
                    else{
                        if(label == null)
                            campoOtro = JOptionPane.showInputDialog(null, "Ingrese datos:", campoOtro);
                        else
                            campoOtro = label;
                    }
                    
                    if(campoOtro == null){
                        cb.setSelected(false);
                        option.setLabel("OTROS");
                        lstObjMostrarCheckBox.get(indiceSeleccionado).setText("OTROS");
                    }else{
                        cb.setSelected(true);
                        option.setLabel("["+campoOtro+"]");
                        lstObjMostrarCheckBox.get(indiceSeleccionado).setText("["+campoOtro+"]");
                    }
                    mapObjComboBox.put(cb.getObj(), cb.isSelected());
                }else{
                    //ObjCheckBox cb = lstObjMostrarCheckBox.get(indiceSeleccionado);
                    if (cb.getObj() == nullObject) 
                        return;
                    
                    //desmarcar
                    if (cb.isSelected()) {
                        cb.setSelected(false);
                        
                        mapObjComboBox.put(cb.getObj(), false);
                        //marcar todo
                        if (optionSelectedAll) {
                            int posicion = totalOpciones - 2;
                            if (!optionSelectedNone)
                                posicion = totalOpciones - 1;
                            lstObjMostrarCheckBox.get(posicion).setSelected(false); //Select all
                        }
                        // desmarcar todo
                        if (optionSelectedNone) 
                            lstObjMostrarCheckBox.get(totalOpciones - 1).setSelected(getSelectedItems() == null); // select none
                        
                    } else {
                        //marcar
                        cb.setSelected(true);
                        
                        mapObjComboBox.put(cb.getObj(), true);
                        //marcar todo
                        if (optionSelectedAll) {
                            int posicion = totalOpciones - 2;
                            if (!optionSelectedNone)
                                posicion = totalOpciones - 1;
                            Object[] sobjs = getSelectedItems();
                            lstObjMostrarCheckBox.get(posicion).setSelected(sobjs != null &&
                                                                            sobjs.length == (posicion + 1)); // Select all
                        }
                        // desmarcar todo
                        if (optionSelectedNone) 
                            lstObjMostrarCheckBox.get(totalOpciones - 1).setSelected(false); // select none
                    }
                }
                evaluarMarcaUnaOpcion(indiceSeleccionado);
            }
        }
    }
    
    private void evaluarMarcaUnaOpcion(int indiceSeleccionado){
        if(!isSeleccionMultiple()){
            for(int i=0;i<lstObjMostrarCheckBox.size();i++){
                if(i!=indiceSeleccionado){
                    ObjCheckBox cb = lstObjMostrarCheckBox.get(i);
                    cb.setSelected(false);
                    mapObjComboBox.put(cb.getObj(), false);
                }
            }
            menuComboOcultar(true);
        }
    }
    
    public boolean isPermiteEventoEsc(){
        return this.permiteEventoEsc;
    }
    
    @Override
    public void setPopupVisible(boolean flag) { }
    
    public void setNextObjTransferFocus(Object objTransferFocus){
        this.objTransferFocus = objTransferFocus;
    }
    
    public boolean isDesplegadoMenu(){
        return getUI().isPopupVisible(this);
    }
    
    public void transferFocus(){
        presionoEnter = false;
        if(objTransferFocus instanceof Component)
            ((Component)objTransferFocus).requestFocus();
    }
    
    public boolean isPermiteEnter(){
        return !presionoEnter;
    }

    public boolean isSeleccionable() {
        return seleccionable;
    }

    public void setSeleccionable(boolean seleccionable) {
        this.seleccionable = seleccionable;
    }
    
    private void menuComboMostrar(){
        if(!mapObjComboBox.isEmpty() && !isDesplegadoMenu()){
            getUI().setPopupVisible(this, true);
            if(!mapObjComboBox.isEmpty()){
                this.setSelectedIndex(1);
                permiteEventoEsc = true;
                contadorEventoEsc = 0;
            }
        }
    }
    
    private void menuComboOcultar(boolean flag){
        if(isDesplegadoMenu()){
            if(mapObjComboBox.isEmpty() || flag){
                if(flag)
                    contadorEventoEsc = 1;
                getUI().setPopupVisible(this, false);
                permiteEventoEsc = false;
            }
        }
    }

    public boolean isSeleccionMultiple() {
        return seleccionMultiple;
    }

    public void setSeleccionMultiple(boolean seleccionMultiple) {
        this.seleccionMultiple = seleccionMultiple;
    }

    public boolean isPermiteEventoEnter() {
        return permiteEventoEnter;
    }

    public void setPermiteEventoEnter(boolean permiteEventoEnter) {
        this.permiteEventoEnter = permiteEventoEnter;
    }
    
    /**
     * Metodo para seleccionar item mediante el codigo del item.
     * @param value
     */
    public void selectItemIdentificador(Object value){
        if(value !=null){
            int contador = -1;
            boolean encontro = false;
            for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
                contador++;
                OptionComboBox obj = (OptionComboBox)entry.getKey();
                String codigo = (String)obj.getCodigo();
                if(value instanceof Integer){
                    try{
                        if(Integer.parseInt(codigo) == (Integer)value){
                            encontro = true;
                            break;
                        }
                    }catch(Exception ex){
                        contador = -1;
                        break;
                    }
                }else if(value instanceof String){
                    if(codigo.equalsIgnoreCase((String)value)){
                        encontro = true;
                        break;
                    }
                }
            }
            if(!encontro)
                contador = -1;
            checkBoxSelectionChanged(contador);
        }
    }
    
    public void selectItemIdentificador(Object value, String label){
        if(value !=null){
            int contador = -1;
            for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
                contador++;
                OptionComboBox obj = (OptionComboBox)entry.getKey();
                String codigo = (String)obj.getCodigo();
                if(value instanceof Integer){
                    try{
                        if(Integer.parseInt(codigo) == (Integer)value)
                            break;
                    }catch(Exception ex){
                        contador = -1;
                        break;
                    }
                }else if(value instanceof String){
                    if(codigo.equalsIgnoreCase((String)value))
                        break;
                }
            }
            checkBoxSelectionChanged(contador, label);
        }
    }
    
    /**
     * Metodo para seleccionar mediante el indice de la lista, primer valor indice "0"
     * @param index indice del item a seleccionar, comienza en 0 
     */
    public void selectItem(int index){
        if(index==0){
            for (Map.Entry<Object, Boolean> entry : mapObjComboBox.entrySet()) {
                OptionComboBox obj = (OptionComboBox)entry.getKey();
                obj.setSeleccionado(false);
                mapObjComboBox.put(obj, false);
            }
            reset();
        }else
            checkBoxSelectionChanged(index-1);
    }
    
    class CheckBoxKey implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent e) { 
        }

        @Override
        public void keyPressed(KeyEvent e) { 
            capturarEnter(e);
        }

        @Override
        public void keyReleased(KeyEvent e) { 
        }
        
        /**
         * metodo que activa indicador de haber pulsado Enter en la lista del combobox
         * @param e
         */
        private void capturarEnter(KeyEvent e) {
            presionoEnter = (e.getKeyCode() == KeyEvent.VK_ENTER);
            if(e.getKeyCode() == KeyEvent.VK_ENTER && !isDesplegadoMenu()){
                e.consume();
                transferFocus();
            }else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                if(isDesplegadoMenu()){
                    menuComboOcultar(true);
                    e.consume();
                }else{
                    if(contadorEventoEsc==2 && !permiteEventoEsc){
                        contadorEventoEsc = 0;
                        permiteEventoEsc = true;
                    }
                    contadorEventoEsc=2;
                }
                
                
                /*if(cont==0){
                    menuComboOcultar(true);
                    e.consume();
                }
                if(cont==1){
                    aux = false;
                }*/
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                menuComboMostrar();
            }
        }
    }
    
    class CheckBoxRenderer implements ListCellRenderer {
        private final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
        private JSeparator separator;
        private final List<ObjCheckBox> cbs;

        public CheckBoxRenderer(final List<ObjCheckBox> cbs) {
            this.cbs = cbs;
            separator = new JSeparator(JSeparator.HORIZONTAL);
        }

        //@Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            // aplicara para las opciones menos las primera
            if (index > 0 && index <= cbs.size()) {
                ObjCheckBox cb = cbs.get(index - 1);
                if (cb.getObj() == nullObject) {
                    return separator;
                }

                cb.setBackground(isSelected ? Color.blue : Color.white);
                cb.setForeground(isSelected ? Color.white : Color.black);

                return cb;
            }
            
            // capturamos opciones marcadas para mostrar en la cabecera del combobox.
            String str;
            Object[] objs = getSelectedItems();
            Vector<String> strs = new Vector();
            if (objs == null) {
                if(mapObjComboBox.isEmpty())
                    str = "No hay opciones a mostrar";
                else
                    str = "Seleccione una opción";
            } else {
                for (Object obj : objs) {
                    strs.add(obj.toString());
                }
                str = strs.toString().substring(1, strs.toString().length() - 1);
            }
            return defaultRenderer.getListCellRendererComponent(list, str, index, isSelected, cellHasFocus);
        }
    }

    class ObjCheckBox extends JCheckBox {
        private final transient Object obj;

        public ObjCheckBox(final Object obj) {
            super(obj.toString());
            this.obj = obj;
            this.setFont(new Font("SansSerif", 0, 11));
        }

        public Object getObj() {
            return obj;
        }
    }

    interface CheckComboBoxSelectionChangedListener extends EventListener {
        public void selectionChanged(int idx);
    }
    
    public class OpcionCombo{
        private String etiqueta;
        
        public OpcionCombo(){
            
        }
    }
    
}