
/*
package eckerd.iscbolsas;

import eckerd.iscbolsas.reference.ConstantsISCBolsas;
import eckerd.iscbolsas.reference.UtilityISCBolsas;

import java.awt.event.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.TreeMap;

import javax.swing.*;

import mifarma.common.FarmaGridUtils;

import oracle.jdeveloper.layout.XYConstraints;

public class DlgISCBolsas extends JFrame {

    // JFrame
    static JFrame f;

    // JButton
    static JButton b, b1, b2;

    // label to diaplay text
    static JLabel l;

    static Map map = new TreeMap();

    // main class

    public static void main(String[] args) {
        // create a new frame to stor text field and button
        f = new JFrame("panel");
        f.getContentPane().setLayout(null);
        // create a label to display text
        l = new JLabel("panel label");

        // create a new buttons
        b = new JButton("button1");
        b.setBounds(new Rectangle(5, 5, 100, 20));

        b1 = new JButton("button2");
        b1.setBounds(new Rectangle(5, 25, 100, 20));

        b2 = new JButton("button3");
        b2.setBounds(new Rectangle(5, 45, 100, 20));


        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerfomed(e);
            }
        });


        final ArrayList lst = UtilityISCBolsas.productosCobrarISCBolsa();
        
        JTextField jD = null;
        int temp = 65;
        
        for (int i = 0; i < lst.size(); i++) {
            jD = new JTextField();
            jD.setName(ConstantsISCBolsas.PREFIX_COMPONENT + i);
            
            jD.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println("keyTyped::: " + e.getComponent().getName());
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        String name = e.getComponent().getName();
                        
                        String number = name.substring(name.length() - 1);
                        
                        int pos = Integer.parseInt(number);
                        
                        if(pos < lst.size() - 1) {
                            ((JTextField) map.get(ConstantsISCBolsas.PREFIX_COMPONENT + (pos + 1))).requestFocus();
                        }else{
                            ((JTextField) map.get(ConstantsISCBolsas.PREFIX_COMPONENT + "0")).requestFocus();
                        }
                        
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.println("KeyReleased::: " + e.getComponent());
                    System.out.println("Texto!!!! " + ((JTextField)map.get(e.getComponent().getName())).getText());
                }
            });
            
            jD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("actionPerformed::: " + e.getActionCommand());
                }
            });
            
            jD.setBounds(new Rectangle(5, temp, 100, 20));

            f.getContentPane().add(jD);
            map.put(ConstantsISCBolsas.PREFIX_COMPONENT + i, jD);
            temp = temp + 20;
        }
        // add buttons and textfield to panel
        f.getContentPane().add(b);
        f.getContentPane().add(b1);
        f.getContentPane().add(b2);
        f.getContentPane().add(l);

        // setbackground of panel

        // add panel to frame

        // set the size of frame
        f.setSize(300, 300);

        f.show();
    }

    public static void actionPerfomed(ActionEvent e) {

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado

            //se recupera el contenido del JTextfield
            String name = ((JTextField)entry.getValue()).getText();
            //mostramos resultado
            JOptionPane.showMessageDialog(null, "Se presiono boton " + itm + " \n Hola " + name);

        }
    }
   
} */
