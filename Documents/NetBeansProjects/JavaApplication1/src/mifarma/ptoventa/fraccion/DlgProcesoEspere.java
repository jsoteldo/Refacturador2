package mifarma.ptoventa.fraccion;

import com.gs.mifarma.worker.JDialogProgress;

import java.awt.Frame;

import java.util.Calendar;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.caja.DlgProcesarAnulacionRecarga;

import mifarma.ptoventa.fraccion.modelo.VariableProducto;
import mifarma.ptoventa.fraccion.reference.UtilityFraccion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgProcesoEspere  extends JDialogProgress {
    private static final Logger log = LoggerFactory.getLogger(DlgProcesarAnulacionRecarga.class);
    private Frame myParentFrame;
    private static boolean indIntenta=false;
    private static String msjProceso="";
    
    
    public DlgProcesoEspere(Frame frame, String string, boolean b) {
        super(frame, string, b);
        myParentFrame = frame;
    }

    public DlgProcesoEspere() {
        super();
    }

    @Override
    public void ejecutaProceso() {
        try {
            log.info("PROCESO DE CARGAR PRODUCTOS A LISTA");
            UtilityFraccion.cargaLista_ProductosMemoria(VariableProducto.listaMemoriaProd);  
        } catch (Exception e) {
            msjProceso="Error al cargar la lista de productos\n"+e.getMessage()+"\n"+e;
        }
    }
}
