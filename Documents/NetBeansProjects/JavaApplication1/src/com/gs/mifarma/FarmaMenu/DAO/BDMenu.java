package com.gs.mifarma.FarmaMenu.DAO;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BDMenu {
    private static final Logger log = LoggerFactory.getLogger(BDMenu.class);

    private static ArrayList parametros = new ArrayList();
    
    public BDMenu() {
        super();
    }

    public static void recuperaListaMenus(ArrayList<ArrayList> listaMenus) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodLocal);
        
        log.info("FARMA_MENU.F_CUR_MENU_FARMAVENTA(?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(listaMenus, 
                                                 "FARMA_MENU.F_CUR_MENU_FARMAVENTA(?,?,?)", 
                                                 parametros);
    }

    public static void Cargar_MenuPersonalizado(ArrayList<ArrayList> listaMenus) throws SQLException{
        
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vIdUsu);
        
        log.info("FARMA_MENU.GET_LISTA_MENU_FARMAVENTA(?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(listaMenus, 
                                                 "FARMA_MENU.GET_LISTA_MENU_FARMAVENTA(?,?,?,?)", 
                                                 parametros);
        
    }

    public static void Cargar_MenuDesarrollo(ArrayList<ArrayList> listaMenus, String codTblMenu) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(codTblMenu);        
        
        log.info("FARMA_MENU.GET_MENU_FARMAVENTA_DESA(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(listaMenus, 
                                                 "FARMA_MENU.GET_MENU_FARMAVENTA_DESA(?,?,?,?,?)", 
                                                 parametros);
    }

    public static String get_IndicadorMenuFarma()throws SQLException{
        parametros = new ArrayList();
        log.info("FARMA_MENU.GET_INDICADOR_MENU_FARMA"+parametros);
        String indicador=FarmaDBUtility.executeSQLStoredProcedureStr("FARMA_MENU.GET_INDICADOR_MENU_FARMA", 
                                                         parametros);
        return indicador;
    }


    public static void Cargar_MenuFarmaVenta(ArrayList<ArrayList> listaMenus, String codTblMenu) throws SQLException{
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(codTblMenu);
        
        log.info("FARMA_MENU.GET_MENU_FARMAVENTA(?,?,?,?,?)"+parametros);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(listaMenus, 
                                                 "FARMA_MENU.GET_MENU_FARMAVENTA(?,?,?,?,?)", 
                                                 parametros);
    }
}
