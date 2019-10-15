package com.gs.mifarma.FarmaMenu.Util;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class FMenu extends JMenuBar implements ActionListener {
    private ArrayList<ArrayList> vtOpc = new ArrayList<ArrayList>();
    private String MetodoAccion = ""; // Nombre del método de acciones
    private int LonOpc; // Número de filas de la matriz de opciones

    // Constructor de la clase MiMenu

    public FMenu(ArrayList<ArrayList> vtOpcPar, String MetAccion) {
        // Asignación de parámetros y variables de instancia
        vtOpc = vtOpcPar;
        MetodoAccion = MetAccion;
        LonOpc=vtOpc.size();
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        Hashtable Lista = new Hashtable();
        JMenu tmpMenu;
        JMenuItem tmpItem;
        System.out.println("=> vtOpc.size(): "+vtOpc.size());
        for (int i = 0; i < vtOpc.size(); i++) {
            String idMenu=vtOpc.get(i).get(0).toString().trim();
            String descripcion=vtOpc.get(i).get(1).toString().trim();
            String mnemonic=vtOpc.get(i).get(2).toString().trim();
            String tipoDato=vtOpc.get(i).get(3).toString().trim();
            String nivel = vtOpc.get(i).get(4).toString().trim();
            String menuPadre = vtOpc.get(i).get(5).toString().trim();
            String visible = vtOpc.get(i).get(6).toString().trim();
            String enabled = vtOpc.get(i).get(7).toString().trim();
            boolean setVisible=false;
            boolean setEnable=false;
            /*
            System.out.println("=> idMenu: "+idMenu);
            System.out.println("=> descripcion: "+descripcion);
            System.out.println("=> mnemonic: "+mnemonic);
            System.out.println("=> tipoDato: "+tipoDato);
            System.out.println("=> nivel: "+nivel);
            System.out.println("=> menuPadre: "+menuPadre);
            System.out.println("=> visible: "+visible);
            System.out.println("=> enabled: "+enabled);
            */
            if(nivel.equalsIgnoreCase("0")){
                descripcion=descripcion.substring(3);
            }
            if(visible.equalsIgnoreCase("1"))
                setVisible=true;
            
            if(enabled.equalsIgnoreCase("1"))
                setEnable=true;
            
            if(tipoDato.equalsIgnoreCase("L")){//Es menu --> tiene opciones
                tmpMenu = new JMenu(idMenu);
                tmpMenu.setName(idMenu);
                tmpMenu.setText(descripcion);
                tmpMenu.setFont(new Font("SansSerif", 0, 11));
                if(mnemonic!=null && !mnemonic.equalsIgnoreCase("")){
                    tmpMenu.setMnemonic(mnemonic.charAt(0));
                }
                tmpMenu.setVisible(setVisible);
                tmpMenu.setEnabled(setEnable);
                
                Lista.put(idMenu,tmpMenu);
                
            }else{//Es item --> opcion con accion
                tmpItem = new JMenuItem(idMenu);
                tmpItem.setName(idMenu);
                tmpItem.setText(descripcion);
                tmpItem.setFont(new Font("SansSerif", 0, 11));
                if(mnemonic!=null && !mnemonic.equalsIgnoreCase("")){
                    tmpItem.setMnemonic(mnemonic.charAt(0));
                    tmpItem.setAccelerator(KeyStroke.getKeyStroke(mnemonic.charAt(0), ActionEvent.ALT_MASK));
                }
                
                tmpItem.setVisible(setVisible);
                tmpItem.setEnabled(setEnable);
                
                tmpItem.setActionCommand(idMenu);
                tmpItem.addActionListener(this);
                Lista.put(idMenu, tmpItem);
            }
        }
        
        String IdPapa;
        JMenu tmpMenuPrb = new JMenu();
        System.out.println("=> LonOpc: "+LonOpc);
        for (int i = 0; i < LonOpc; i++) {
            String idMenu=vtOpc.get(i).get(0).toString().trim();
            String nivel = vtOpc.get(i).get(4).toString().trim();
            String menuPadre = vtOpc.get(i).get(5).toString().trim();
            
            if(nivel.equalsIgnoreCase("0")){
                if (Lista.get(idMenu).getClass() == tmpMenuPrb.getClass()) {
                    add((JMenu)Lista.get(idMenu));
                } else {
                    add((JMenuItem)Lista.get(idMenu));
                }
            }else{
                IdPapa = menuPadre;
                tmpMenu = (JMenu)Lista.get(IdPapa);
                if (Lista.get(idMenu).getClass() == tmpMenuPrb.getClass()) {
                    try{
                        tmpMenu.add((JMenu)Lista.get(idMenu));
                    }catch(Exception e){
                        
                    }                    
                } else {
                    if(Lista.get(idMenu)!=null){
                        try{
                            tmpMenu.add((JMenuItem)Lista.get(idMenu));
                        }catch(Exception e){
                            
                        }
                        
                    }else{
                    }                    
                }
            }
        }
    } // Fin del constructor MiMenu
    /* Método que determina, dada la clave de una opción, si ésta tiene subopciones */

   @Override
    public void actionPerformed(ActionEvent e) {

        try {
            /* Si el contexto en que se utiliza el objeto MiMenu es
              una ventana, dentro de la cual hay una barra de menús,
              extendiendo la barra MiMenu, el objeto que instancia
              la clase está en la tercera generación. Si este no es el
              caso, habrá que alterar la instrucción, referenciando
              el objeto padre en la generación correcta */
            Object objPapa = getParent().getParent().getParent();
            Class MiPapa = getParent().getParent().getParent().getClass();
            /* Estableciendo que los parámetros del método de acciones
              en la clase que llama a MiMenu son de tipo String y
              pasando como argumento a dicho método la clave de la
              opción seleccionada */
            Class[] TiposParametros = new Class[] { String.class };
            Object[] argumentos = new Object[] { e.getActionCommand() };
            /* Definiendo el método de acciones de la clase que llama
              a MiMenu y sus parámetros para luego invocarlo
              ocasionando su ejecución */
            Method target = objPapa.getClass().getMethod(MetodoAccion, TiposParametros);
            Object param[] = { e.getActionCommand() };
            target.invoke(objPapa, argumentos);

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    } // Fin de actionPerformed
}
