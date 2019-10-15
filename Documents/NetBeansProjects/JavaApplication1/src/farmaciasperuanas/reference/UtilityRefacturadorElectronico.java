package farmaciasperuanas.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaVariables;

public class UtilityRefacturadorElectronico {
    public static void autenticar(String pUsuario) throws Throwable{
        ArrayList vUserInfoList = new ArrayList();
        ArrayList vParameters = new ArrayList();
        vParameters.add(FarmaVariables.vCodGrupoCia);
        vParameters.add(FarmaVariables.vCodLocal);
        vParameters.add(pUsuario);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vUserInfoList,
                                                          "FARMA_SECURITY.OBTIENE_DATO_USUARIO_LOGIN(?,?,?)",
                                                          vParameters);
        if (vUserInfoList.size() > 0) {
            FarmaVariables.vNuSecUsu = ((String)((ArrayList)vUserInfoList.get(0)).get(0)).trim();
            FarmaVariables.vIdUsu = ((String)((ArrayList)vUserInfoList.get(0)).get(4)).trim();
            FarmaVariables.vNomUsu = ((String)((ArrayList)vUserInfoList.get(0)).get(3)).trim();
            FarmaVariables.vPatUsu = ((String)((ArrayList)vUserInfoList.get(0)).get(1)).trim();
            FarmaVariables.vMatUsu = ((String)((ArrayList)vUserInfoList.get(0)).get(2)).trim();
            FarmaVariables.vIdUsuCE = ((String)((ArrayList)vUserInfoList.get(0)).get(4)).trim();
            String clave =
                DBRefacturadorElectronico.getClaveUsu(VariablesRefacturadorElectronico.vComprobanteActual.getCodGrupoCia(),
                                                      VariablesRefacturadorElectronico.vComprobanteActual.getCodLocal(),
                                                      FarmaVariables.vNuSecUsu);
            VariablesRefacturadorElectronico.vComprobanteActual.setContraseña(clave);
        } else {
            throw new Exception("No se encontro datos del usuario");
        }

        ArrayList vRoleList = new ArrayList();
        vParameters = new ArrayList();
        vParameters.add(FarmaVariables.vCodGrupoCia);
        vParameters.add(FarmaVariables.vCodLocal);
        vParameters.add(FarmaVariables.vNuSecUsu);
        FarmaDBUtility.executeSQLStoredProcedureArrayList(vRoleList, "FARMA_SECURITY.LISTA_ROL(?,?,?)", vParameters);

        if (FarmaVariables.vEconoFar_Matriz) {

            FarmaVariables.vCodUsuMatriz = FarmaVariables.vNuSecUsu;
            FarmaVariables.vClaveMatriz = VariablesRefacturadorElectronico.vComprobanteActual.getContraseña();
        }
    }
}
