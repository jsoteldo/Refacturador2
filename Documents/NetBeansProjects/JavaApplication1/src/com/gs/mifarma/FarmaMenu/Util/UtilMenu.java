package com.gs.mifarma.FarmaMenu.Util;


import com.gs.mifarma.FarmaMenu.DAO.BDMenu;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaVariables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilMenu {
    private static final Logger log = LoggerFactory.getLogger(UtilMenu.class);
    public UtilMenu() {
        super();
    }

    public static ArrayList<ArrayList> RecuperarListaMenu() {
        ArrayList<ArrayList> lista=new ArrayList<ArrayList>();
        try {
            BDMenu.recuperaListaMenus(lista);
        } catch (SQLException e) {
            log.error("Error al recuperar la lista de menus");
        }
        return lista;
    }

    public static ArrayList<ArrayList> Cargar_MenuPersonalizado() {
        ArrayList<ArrayList> lista=new ArrayList<ArrayList>();
        try {
            System.out.println("==> GRUPO_CIA: "+FarmaVariables.vCodGrupoCia);
            System.out.println("==> CIA: "+FarmaVariables.vCodCia);
            System.out.println("==> LOCAL: "+FarmaVariables.vCodLocal);
            System.out.println("==> ID_USUARIO: "+FarmaVariables.vIdUsu);
            BDMenu.Cargar_MenuPersonalizado(lista);
        } catch (SQLException e) {
            log.error("Error al recuperar la lista de menus");
        }
        return lista;
    }

    public static ArrayList<ArrayList> Cargar_MenuDesarrollo(String codTblMenu) {
        ArrayList<ArrayList> lista=new ArrayList<ArrayList>();
        try {
            System.out.println("==> GRUPO_CIA: "+FarmaVariables.vCodGrupoCia);
            System.out.println("==> CIA: "+FarmaVariables.vCodCia);
            System.out.println("==> LOCAL: "+FarmaVariables.vCodLocal);
            System.out.println("==> ID_USUARIO: "+FarmaVariables.vIdUsu);
            BDMenu.Cargar_MenuDesarrollo(lista,codTblMenu);
        } catch (SQLException e) {
            log.error("Error al recuperar la lista de menus");
        }
        return lista;
    }

    public static String Recupera_IndicadorMenu() {
        String indicador="N";
        try {
            indicador=BDMenu.get_IndicadorMenuFarma();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error al recuperar la lista de menus "+e.getMessage());
        }
        return indicador;
    }

    public static ArrayList<ArrayList> Cargar_MenuFarmaventa(String codTblMenu) {
        ArrayList<ArrayList> lista=new ArrayList<ArrayList>();
        try {
            System.out.println("==> GRUPO_CIA: "+FarmaVariables.vCodGrupoCia);
            System.out.println("==> CIA: "+FarmaVariables.vCodCia);
            System.out.println("==> LOCAL: "+FarmaVariables.vCodLocal);
            System.out.println("==> ID_USUARIO: "+FarmaVariables.vIdUsu);
            System.out.println("==> COD_MENU: "+codTblMenu);
            BDMenu.Cargar_MenuFarmaVenta(lista,codTblMenu);
        } catch (SQLException e) {
            log.error("Error al recuperar la lista de menus");
        }
        return lista;
    }
}
