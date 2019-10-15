package mifarma.ptoventa.main;


import com.gs.mifarma.FarmaMenu.Util.UtilMenu;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2005 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicaci�n : EconoFar.java<br>
 * <br>
 * Hist�rico de Creaci�n/Modificaci�n<br>
 * LMESIA      27.12.2005   Creaci�n<br>
 * ERIOS       20.06.2013   Modificacion<br>
 * <br>
 * @author Luis Mesia Rivera<br>
 * @version 1.0<br>
 *
 */

public class MainFarmaVenta extends ReadProperties {

    static private final Logger log = LoggerFactory.getLogger(MainFarmaVenta.class);

    /**
     * Frame principal de la Aplicaci�n
     */
    public MainFarmaVenta() {
        init();
    }

    public static void main(String[] args) {
        //20.12.2007 ERIOS Se modifica el metodo para cargar desde el jar.
        if (args.length == 3) {
            log.debug(args[0]);
            log.debug(args[1]);
            log.debug(args[2]);
            new MainFarmaVenta(args[0], args[1], args[2]);
        } else if (args.length == 2) /* 25.01.2008 ERIOS Ejecuta Ptoventa_Matriz */
        {
            new EconoFar_Matriz(args[0], args[1]);
        } else {
            new MainFarmaVenta();
        }
    }

    /**
     * Constructor que recibe parametros de properties
     * @param arch1 Properties FarmaVenta
     * @param arch2 Properties Clave
     * @param arch3 Properties Servidor Remoto
     * @author Edgar Rios Navarro
     * @since 20.12.2007
     */
    public MainFarmaVenta(String arch1, String arch2, String arch3) {
        prop1 = arch1;
        prop2 = arch2;
        prop3 = arch3;
        init();
    }

    @Override
    void init() {
        if (readFileProperties() && readFilePasswordProperties() && readFileServRemotosProperties()) {
            UtilityPtoVenta.cargaVariablesBD();
            String pCadena = UtilityPtoVenta.readFileFarmaVentaProperties();
            if(pCadena.equalsIgnoreCase("N")){
                
            }
            else {
                UtilityPtoVenta.muestraLogin(pCadena);
                //String indMenu="S";
                //String indMenu="N";
                String indMenu=UtilMenu.Recupera_IndicadorMenu();
                
                if(indMenu.equalsIgnoreCase("S")){
                   
                    UtilityPtoVenta.Actualiza_IndicadoresMenu();
                    Frame frame = new FrmEconoMenuFar();//
                    frame.setLocationRelativeTo(null);
                    frame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    frame.setVisible(true);
                }else{
                    Frame frame = new FrmEconoFar();//
                    frame.setLocationRelativeTo(null);
                    frame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    frame.setVisible(true);
                }   
            }
        }
    }
}
