package com.gs.mifarma.FarmaMenu;


import com.gs.mifarma.FarmaMenu.Util.FMenu;
import com.gs.mifarma.FarmaMenu.Util.UtilMenu;

import java.util.ArrayList;

import javax.swing.JMenuBar;


public class FMenuBar extends JMenuBar{
    public FMenuBar() {
        ArrayList<ArrayList> listaMenu=UtilMenu.RecuperarListaMenu();
        
        /* Llamada de MiMenu que env�a la matriz de opciones y el
             M�todo de la clase que lo invoca que resuelve las acciones
             del men� dado */
        FMenu mnPrin = new FMenu(listaMenu, "AccionesMenu");
        // Establecimiento de MiMenu como el men� de la aplicaci�n
        setJMenuBar(mnPrin);
    }

    private void setJMenuBar(FMenu fMenu) {
        getRootPane().setMenuBar(fMenu);
    }
}
