package mifarma.ptoventa.administracion.reference;

import javax.swing.JDialog;

import mifarma.common.FarmaTableModel;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.administracion.impresoras.reference.DBImpresoras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityAdministracion {
    
    private static final Logger log = LoggerFactory.getLogger(UtilityAdministracion.class);
    
    public static boolean actualizarIPTipoLocalVenta(JDialog pJDialog, String pIpPc, String pCodTipoLocalVenta){
        boolean isActualizo = false;
        try{
            DBImpresoras.actualizarIpTipoLocalVenta(FarmaVariables.vCodGrupoCia, 
                                                    FarmaVariables.vCodLocal, 
                                                    pIpPc, 
                                                    pCodTipoLocalVenta, 
                                                    FarmaVariables.vIdUsu);
            FarmaUtility.aceptarTransaccion();
            isActualizo = true;
        }catch(Exception ex){
            FarmaUtility.liberarTransaccion();
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Error al actualizar IP.\nReintente, si problema persiste comuniquese con Mesa de Ayuda.", null);
        }
        return isActualizo;
    }
    
    public static boolean actualizarIPImpresoraRecetaDigital(JDialog pJDialog, String pIpPc, String pNombreImpresora){
        boolean isActualizo = false;
        try{
            DBImpresoras.actualizarIpImpresoraReceta(FarmaVariables.vCodGrupoCia, 
                                                     FarmaVariables.vCodLocal, 
                                                     pIpPc, 
                                                     pNombreImpresora, 
                                                     FarmaVariables.vIdUsu);
            FarmaUtility.aceptarTransaccion();
            isActualizo = true;
        }catch(Exception ex){
            FarmaUtility.liberarTransaccion();
            log.error("", ex);
            FarmaUtility.showMessage(pJDialog, "Error al actualizar IP.\nReintente, si problema persiste comuniquese con Mesa de Ayuda.", null);
        }
        return isActualizo;
    }
    
    
}
