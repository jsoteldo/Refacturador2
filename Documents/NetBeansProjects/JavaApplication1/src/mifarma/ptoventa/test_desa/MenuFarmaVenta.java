package mifarma.ptoventa.test_desa;


import com.gs.mifarma.FarmaMenu.Util.FMenu;
import com.gs.mifarma.FarmaMenu.Util.UtilMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.main.DlgProcesar;
import mifarma.ptoventa.recaudacion.reference.FacadeRecaudacion;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.ventas.reference.UtilityVentas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MenuFarmaVenta  extends JFrame {
    
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */

    private static final Logger log = LoggerFactory.getLogger(MenuFarmaVenta.class);

    public static String tituloBaseFrame = "FarmaVenta";
    int ind = 0;
    JLabel statusBar = new JLabel();
    BorderLayout borderLayout1 = new BorderLayout();
    JLabel lblLogo = new JLabel();
    
    public MenuFarmaVenta() {
        try {
            jbInit();
            initMenus();
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);            
        } catch (Exception e) {
            log.error("", e);
        }
    }
   
    private void cargaVariablesBD() {
        FarmaVariables.vPUERTO = ConstantsPtoVenta.PUERTO;
        try {
            (new FacadeRecaudacion()).obtenerTipoCambio();
        } catch (SQLException sql) {
            log.error("", sql);
            FarmaUtility.showMessage(this, "Error al obtener Tipo de Cambio del Dia . \n " + sql.getMessage(), null);
        }
    }
    
    private void jbInit() throws Exception {
        this.getContentPane().setLayout(borderLayout1);

        this.setSize(new Dimension(800, 600));
        this.setFont(new Font("SansSerif", 0, 11));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //this.setJMenuBar(jMenuFarma);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                salir(e);
            }

            public void windowClosed(WindowEvent e) {
                this_windowClosed(e);
            }
        });
    }
    
    private void salir(WindowEvent e) {
        salirSistema();
    }
    
    private void this_windowClosed(WindowEvent e) {
        UtilityVentas.eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
    }
    
    private void this_windowOpened(WindowEvent e) {
        DlgProcesar dlgProcesar = new DlgProcesar(this, "", true);
        dlgProcesar.mostrar();        
    }
    
    private void mnuSalir_SalirSistema_actionPerformed(ActionEvent e) {
        salirSistema();
    }
    
    private void salirSistema() {
        UtilityVentas.eliminaCodBarra();
        UtilityVentas.eliminaBoletaTxt();
        if (FarmaVariables.vEconoFar_Matriz)
            this.dispose();
        else
            System.exit(0);
    }

    private void initMenus() {
        ArrayList<ArrayList> listaMenu=new ArrayList<ArrayList>();
        listaMenu=UtilMenu.RecuperarListaMenu();
        FMenu menuBar=new FMenu(listaMenu,"AcctionPerformed");
        menuBar.setFont(new Font("SansSerif", 0, 11));
        this.setJMenuBar(menuBar);
        
    }
    
    public void AcctionPerformed(String Opc) {

        /* En este ejemplo, estas son las claves de opciones terminales
         (esto es, aquellas que provocan acciones) definidas. Por
         supuesto si cambia el menú de opciones, será necesario
         alterar el contenido de este método, en consecuencia. */
        if (Opc.equals("001")) {
            System.out.print("\nAccion: 001");
        } else if (Opc.equals("002")) {
            System.out.print("\nAccion: 002");
        } else if (Opc.equals("003")) {
            System.out.print("\nAccion: 003");
        } else if (Opc.equals("004")) {
            System.out.print("\nAccion: 004");
        } else if (Opc.equals("005")) {
            System.out.print("\nAccion: 005");
        } else if (Opc.equals("006")) {
            System.out.print("\nAccion: 006");
        } else if (Opc.equals("007")) {
            System.out.print("\nAccion: 007");
        } else if (Opc.equals("008")) {
            System.out.print("\nAccion: 008");
        } else if (Opc.equals("009")) {
            System.out.print("\nAccion: 009");
        } else if (Opc.equals("010")) {
            System.out.print("\nAccion: 010");
        } else if (Opc.equals("011")) {
            System.out.print("\nAccion: 011");
        } else if (Opc.equals("012")) {
            System.out.print("\nAccion: 012");
        }else if (Opc.equals("013")) {
            System.out.print("\nAccion: 013");
        }else if (Opc.equals("014")) {
            System.out.print("\nAccion: 014");
        }else if (Opc.equals("015")) {
            System.out.print("\nAccion: 015");
        }else if (Opc.equals("016")) {
            System.out.print("\nAccion: 016");
        }else if (Opc.equals("017")) {
            System.out.print("\nAccion: 017");
        }else if (Opc.equals("018")) {
            System.out.print("\nAccion: 018");
        }else if (Opc.equals("019")) {
            System.out.print("\nAccion: 019");
        }else if (Opc.equals("020")) {
            System.out.print("\nAccion: 020");
        }      
    } // Fin de AccionesMenu

    // Principal de ParaProbarMiMenu
}
