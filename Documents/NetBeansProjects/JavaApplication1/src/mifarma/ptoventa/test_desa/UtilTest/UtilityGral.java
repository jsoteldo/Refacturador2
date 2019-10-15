package mifarma.ptoventa.test_desa.UtilTest;

import java.awt.Component;

import javax.swing.JOptionPane;

import mifarma.common.FarmaUtility;

public class UtilityGral {
    public UtilityGral() {
        super();
    }
    
    public void muestraMensaje(Component component,String msj, int codMsj,String titulo, Object objFocus){
        int nroIcono=0;
        switch(codMsj){
            case 0:
            nroIcono=JOptionPane.ERROR_MESSAGE;
            if(titulo==null)
                titulo="Error de sistema";            
            break;
            case 1:
            nroIcono=JOptionPane.INFORMATION_MESSAGE;
            if(titulo==null)
                titulo="Mensaje de sistema";
            break;
            case 2:
            nroIcono=JOptionPane.WARNING_MESSAGE;
            if(titulo==null)
                titulo="Advertencia de sistema";
            break;
            case 3:
            nroIcono=JOptionPane.QUESTION_MESSAGE;
            if(titulo==null)
                titulo="Requerimiento de sistema";
            break;
            default:
            nroIcono=JOptionPane.WARNING_MESSAGE;
            if(titulo==null)
                titulo="Mensaje de sistema";
        }
        JOptionPane.showMessageDialog(component,msj,titulo,nroIcono);
        FarmaUtility.moveFocus(objFocus);
    }
    
}
