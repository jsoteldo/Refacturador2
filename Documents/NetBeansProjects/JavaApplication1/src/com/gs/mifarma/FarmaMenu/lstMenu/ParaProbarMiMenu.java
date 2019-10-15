package com.gs.mifarma.FarmaMenu.lstMenu;
// Objetos para la construcci�n de ventanas
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class ParaProbarMiMenu extends JFrame {

    // Constructor de la clase ParaProbarMiMenu

    public ParaProbarMiMenu() {
        super("Probando ... ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(50, 50, screenSize.width - 100, screenSize.height - 100);
        /* Definici�n e inicializaci�n de la matriz n x 2 contentiva
             de las opciones que se desea dar a MiMenu. Se espera que
             esta matriz contenga s�lo tipos String, y que cada elemento,
             en sentido vertical, est� compuesto por el par Clave, Opci�n,
             donde Clave es un n�mero que mantiene la jerarqu�a del
             �rbol de opciones, por ejemplo, 112 es subopci�n de 11, la
             cual es subopci�n de 1, y as� en todos los dem�s casos. */
        String vtOpciones[][] =
        { { "1", "Primero","P" }, { "11", "Uno","U" }, { "12", "Dos","D" }, { "121", "Avi�n","A" },
          { "122", "Barco","B" }, { "13", "Tres","T" }, { "2", "Segundo","S" }, { "21", "Primaria","P" },
          { "22", "Secundaria","S" }, { "221", "Completa","C" }, { "3", "Tercero","T" } };

        /* Llamada de MiMenu que env�a la matriz de opciones y el
             M�todo de la clase que lo invoca que resuelve las acciones
             del men� dado */
        MiMenu mnPrin = new MiMenu(vtOpciones, "AccionesMenu");
        // Establecimiento de MiMenu como el men� de la aplicaci�n
        setJMenuBar(mnPrin);

    } // Fin del constructor de ParaProbarMiMenu

    /* M�todo que resuelve las acciones a tomar cuando se ha seleccionado
        una opci�n de MiMenu, la cual pasa como par�metro String en Opc y
        representa una clave de la matriz de opciones definida. */

    public void AccionesMenu(String Opc) {

        /* En este ejemplo, estas son las claves de opciones terminales
         (esto es, aquellas que provocan acciones) definidas. Por
         supuesto si cambia el men� de opciones, ser� necesario
         alterar el contenido de este m�todo, en consecuencia. */
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
