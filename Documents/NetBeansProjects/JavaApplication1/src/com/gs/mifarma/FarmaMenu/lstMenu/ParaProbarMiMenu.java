package com.gs.mifarma.FarmaMenu.lstMenu;
// Objetos para la construcción de ventanas
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class ParaProbarMiMenu extends JFrame {

    // Constructor de la clase ParaProbarMiMenu

    public ParaProbarMiMenu() {
        super("Probando ... ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(50, 50, screenSize.width - 100, screenSize.height - 100);
        /* Definición e inicialización de la matriz n x 2 contentiva
             de las opciones que se desea dar a MiMenu. Se espera que
             esta matriz contenga sólo tipos String, y que cada elemento,
             en sentido vertical, esté compuesto por el par Clave, Opción,
             donde Clave es un número que mantiene la jerarquía del
             árbol de opciones, por ejemplo, 112 es subopción de 11, la
             cual es subopción de 1, y así en todos los demás casos. */
        String vtOpciones[][] =
        { { "1", "Primero","P" }, { "11", "Uno","U" }, { "12", "Dos","D" }, { "121", "Avión","A" },
          { "122", "Barco","B" }, { "13", "Tres","T" }, { "2", "Segundo","S" }, { "21", "Primaria","P" },
          { "22", "Secundaria","S" }, { "221", "Completa","C" }, { "3", "Tercero","T" } };

        /* Llamada de MiMenu que envía la matriz de opciones y el
             Método de la clase que lo invoca que resuelve las acciones
             del menú dado */
        MiMenu mnPrin = new MiMenu(vtOpciones, "AccionesMenu");
        // Establecimiento de MiMenu como el menú de la aplicación
        setJMenuBar(mnPrin);

    } // Fin del constructor de ParaProbarMiMenu

    /* Método que resuelve las acciones a tomar cuando se ha seleccionado
        una opción de MiMenu, la cual pasa como parámetro String en Opc y
        representa una clave de la matriz de opciones definida. */

    public void AccionesMenu(String Opc) {

        /* En este ejemplo, estas son las claves de opciones terminales
         (esto es, aquellas que provocan acciones) definidas. Por
         supuesto si cambia el menú de opciones, será necesario
         alterar el contenido de este método, en consecuencia. */
        if (Opc.equals("11")) {
            System.out.print("\nAccion: 11");
        } else if (Opc.equals("121")) {
            System.out.print("\nAccion: 121");
        } else if (Opc.equals("122")) {
            System.out.print("\nAccion: 122");
        } else if (Opc.equals("13")) {
            System.out.print("\nAccion: 13");
        } else if (Opc.equals("21")) {
            System.out.print("\nAccion: 21");
        } else if (Opc.equals("221")) {
            System.out.print("\nAccion: 221");
        } else if (Opc.equals("3")) {
            System.out.print("\nAccion: 3");
        }
    } // Fin de AccionesMenu

    // Principal de ParaProbarMiMenu

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        ParaProbarMiMenu frame = new ParaProbarMiMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } // Fin de main
} // Fin de la clase ParaProbarMiMenu
