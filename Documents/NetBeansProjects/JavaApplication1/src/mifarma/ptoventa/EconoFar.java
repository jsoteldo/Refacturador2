package mifarma.ptoventa;


import com.gs.encripta.FarmaEncripta;

import farmaciasperuanas.model.BeanVenta;

import farmaciasperuanas.reference.VariablesRefacturadorElectronico;

import java.util.ArrayList;

import mifarma.ptoventa.main.EconoFar_Matriz;
import mifarma.ptoventa.main.MainFarmaVenta;

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

public class EconoFar {

    static private final Logger log = LoggerFactory.getLogger(EconoFar.class);

    public static void main(String[] args) {
        BeanVenta vBeanVenta = new BeanVenta();
        vBeanVenta.setCodGrupoCia("001");
        vBeanVenta.setCodLocal("071");
//        vBeanVenta.setNumCompPagoE("F07101343158");
        vBeanVenta.setNumCompPagoE("B07104167334");
        vBeanVenta.setMotivoAnulacion("Comprobante rechazado por SUNAT");
        vBeanVenta.setCompleto(false);
//        BeanVenta vBeanVenta2 = new BeanVenta();
//        vBeanVenta2.setCodGrupoCia("001");
//        vBeanVenta2.setCodLocal("071");
//        vBeanVenta2.setNumCompPagoE("F07101342093");
//        vBeanVenta2.setMotivoAnulacion("Comprobante rechazado por SUNAT");
//        vBeanVenta2.setCompleto(false);
        VariablesRefacturadorElectronico.vListComprobantes.add(vBeanVenta);
        VariablesRefacturadorElectronico.vComprobanteActual = VariablesRefacturadorElectronico.vListComprobantes.get(0);
//        VariablesRefacturadorElectronico.vListComprobantes.add(vBeanVenta2);
      //  String pValor  = FarmaEncripta.desencripta("amxwXzAwMF8xeDM=");        
      //  System.out.println(pValor);

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

}
