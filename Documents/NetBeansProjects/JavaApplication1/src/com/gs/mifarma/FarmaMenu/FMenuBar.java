package com.gs.mifarma.FarmaMenu;


import com.gs.mifarma.FarmaMenu.Util.FMenu;
import com.gs.mifarma.FarmaMenu.Util.UtilMenu;

import java.util.ArrayList;

import javax.swing.JMenuBar;


public class FMenuBar extends JMenuBar{
    public FMenuBar() {
        ArrayList<ArrayList> listaMenu=UtilMenu.RecuperarListaMenu();
        
        /* Llamada de MiMenu que envía la matriz de opciones y el
             Método de la clase que lo invoca que resuelve las acciones
             del menú dado */
        FMenu mnPrin = new FMenu(listaMenu, "AccionesMenu");
        // Establecimiento de MiMenu como el menú de la aplicación
        setJMenuBar(mnPrin);
    }

    private void setJMenuBar(FMenu fMenu) {
        getRootPane().setMenuBar(fMenu);
    }
}
