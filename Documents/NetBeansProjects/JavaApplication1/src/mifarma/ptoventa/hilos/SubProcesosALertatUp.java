package mifarma.ptoventa.hilos;


import com.gs.mifarma.componentes.PopupAlert;
import com.gs.mifarma.componentes.PopupConstants;

import java.awt.Frame;

import java.util.ArrayList;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.reference.DBAlertUp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubProcesosALertatUp extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SubProcesosALertatUp.class);

    private int tiempoInactividad;
    boolean indTerminoProceso = false;
    // asignar nombre a subproceso, llamando al constructor de la superclase
    Frame myParentFrame;
    int SEGUNDOS_REINTENTO = 3 * 60; // 3 MINUTOS

    public SubProcesosALertatUp(Frame parent) {
        myParentFrame = parent;
    }

    // el m�todo run es el c�digo a ejecutar por el nuevo subproceso

    public void run() {
        try {
            Thread.sleep(1000 * 10); //ESPERE INICIALMENTE 10 SEGUNDOS
            while (true) {
                operaproceso();
                Thread.sleep(1000 * SEGUNDOS_REINTENTO); //CADA 3 MINUTOS
            }
        } catch (Exception excepcion) {
            log.error("", excepcion);
        }
    }

    public void operaproceso() {
        try {
            ArrayList vListaAlertas = new ArrayList();
            DBAlertUp db = new DBAlertUp();
            vListaAlertas = db.getAlertaMensajes();
            /*
               >>  EJEMPLO DE LA ALERTA
               �Alerta Delivery!�N�5
               �Alerta Venta Mayorista!�N�5
               �Alerta Delivery!�N�5
               �Alerta Transferencia!�N�5
               �Alerta Delivery!�Tiene mas de 5 minutos,@1 Pedido Delivery Pendiente@Por Favor de Atenderlo.�5
               �Alerta Venta Mayorista!�Tienes mas de 5 minutos,@      5 Pedidos Mayorista Pendientes@Por Favor de Atenderlos.�5
               �Alerta Delivery!�N�5
               �Alerta Transferencia!�N�5
               >>  EJEMPLO DE LA ALERTA
                * */
            String pTitulo, pMSJ;
            int pTiempo;
            for (int i = 0; i < vListaAlertas.size(); i++) {
                pTitulo = FarmaUtility.getValueFieldArrayList(vListaAlertas, i, 0).trim();
                pMSJ = FarmaUtility.getValueFieldArrayList(vListaAlertas, i, 1).trim();
                pTiempo = Integer.parseInt(FarmaUtility.getValueFieldArrayList(vListaAlertas, i, 2).trim());

                if (!pMSJ.trim().equalsIgnoreCase("N")) {
                    PopupAlert f =
                        new PopupAlert(new PopupConstants().NOTIFICACION, pTitulo, pTiempo, myParentFrame, true);
                    String[] listaMSJ = pMSJ.split("@");
                    for (int a = 0; a < listaMSJ.length; a++)
                        f.addMsg(listaMSJ[a].trim());
                    f.open();
                    Thread.sleep(1000 * (10));
                }

            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
        }

    }

}
