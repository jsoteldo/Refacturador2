package mifarma.ptoventa.delivery.reference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mifarma.common.FarmaVariables;

import mifarma.electronico.UtilityImpCompElectronico;

import printerFarma.FarmaPrinterFacade;


public class UtilityImpresionDelivery {

    public UtilityImpresionDelivery() {
    }


    public boolean impresionComanda(List listComanda, String tipoImpresora, String ruta) {
        String impresora = "";
        char c = ruta.charAt(0);
        if (c != '\\') {
            ruta = "\\\\" + FarmaVariables.vIpPc + "\\" + ruta;
        }
        //FarmaPrinterFacade printer=new FarmaPrinterFacade(impresora,ruta);//manda imprimir segun el modelo de impresora
        FarmaPrinterFacade printer =
            new FarmaPrinterFacade(tipoImpresora, ruta, false, "",""); //manda imprimir segun el modelo de impresora
        if (!printer.startPrintService()) {
            return false;
        } else {

            printer.inicializate(); //INICIALIZAR LA IMPRESORA--VALORES POR DEFECTO

            Map mapComanda = new HashMap();
            String valor, tamanio, alineacion, bold, ajuste;
            for (int i = 0; i < listComanda.size(); i++) {
                mapComanda = (HashMap)listComanda.get(i);
                valor = reemplazarCaracterRaros(((String)mapComanda.get("VALOR")));
                tamanio = ((String)mapComanda.get("TAMANIO"));
                alineacion = ((String)mapComanda.get("ALINEACION"));
                bold = ((String)mapComanda.get("BOLD"));
                ajuste = ((String)mapComanda.get("AJUSTE"));

                if ("*".equalsIgnoreCase(valor)) {
                    printer.printLineDotted(15);
                } else {
                    if ("2".equalsIgnoreCase(tamanio)) {
                        printer.printLineDoubleSize(valor);
                    } else {
                        if ("B".equalsIgnoreCase(bold)) {
                            printer.printLineBold(valor);
                        } else {
                            switch (alineacion) {
                            case "C":
                                printer.printLineCenter(valor);
                                break;
                            case "D":
                                printer.printLineRigth(valor);
                                break;
                            case "I":
                                printer.printLine(valor);
                                break;
                            }
                        }
                    }
                }

            }
            printer.endPrintService(false);
            
            /*** CCASTILLO 05/05/2016 ***/
            //Descuento del papel termico
            UtilityImpCompElectronico.calculaPapelTermicoVta(listComanda);
            /*** CCASTILLO 05/05/2016 ***/
            return true;
        }
    }

    private String reemplazarCaracterRaros(String pText) {
        pText = pText.replaceAll("�", "A");
        pText = pText.replaceAll("�", "E");
        pText = pText.replaceAll("�", "I");
        pText = pText.replaceAll("�", "O");
        pText = pText.replaceAll("�", "U");
        pText = pText.replaceAll("�", "a");
        pText = pText.replaceAll("�", "e");
        pText = pText.replaceAll("�", "i");
        pText = pText.replaceAll("�", "o");
        pText = pText.replaceAll("�", "u");
        pText = pText.replaceAll("�", "n");
        pText = pText.replaceAll("�", "N");
        pText = pText.replaceAll("�", "");
        return pText;
    }

}
