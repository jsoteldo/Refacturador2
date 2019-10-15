package mifarma.ptoventa.inventario.precioCompetencia.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaDBUtility;

import mifarma.common.FarmaVariables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (c) 2006 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo     : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DBPrecioCompetencia.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * CLARICO      09.12.2014   Creación<br>
 * <br>
 * @author Celso Larico Mullisaca<br>
 * @version 1.0<br>
 *
 */

public class DBPrecioCompetencia {
    private static final Logger log = LoggerFactory.getLogger(DBPrecioCompetencia.class);
    private static ArrayList parametros = new ArrayList();
    
    public DBPrecioCompetencia() {
        
    }
    
    public static void registrarPrecioCompetencia(String pCodProd,
                                                  String pCodLocal,
                                                  String pCodUsuario,
                                                  String pCodTipoCotizacion,
                                                  String pValFrac,
                                                  String pCantidad ,
                                                  String pPrecioUnitario,
                                                  String cCompetidor,
                                                  String pSustento ,
                                                  String pNumDoc,
                                                  String pFechaDocumento ,
                                                  String pArchivoSustento,
                                                  String pCondicion ,
                                                  String cNumNota,
                                                  String cMotivoNoImagen 
                                                    ) throws SQLException {
      parametros = new ArrayList();
      parametros.add(pCodProd);
      parametros.add(pCodLocal);
      parametros.add(pCodUsuario);
      parametros.add(pCodTipoCotizacion);
      parametros.add(new Double(pValFrac));
      parametros.add(new Integer(pCantidad));
      parametros.add(new Double(pPrecioUnitario));
      parametros.add(cCompetidor);
      parametros.add(pSustento);
      parametros.add(pNumDoc);
      parametros.add(pFechaDocumento);
      parametros.add(pArchivoSustento);
      parametros.add(pCondicion);
      parametros.add(cNumNota);
      parametros.add(cMotivoNoImagen);
      
      log.debug("invocando a PTOVENTA_PRECIO_COMPETENCIA.PREC_REGISTRA_COTIZACION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?):"+parametros);
      FarmaDBUtility.executeSQLStoredProcedure(null,"PTOVENTA_PRECIO_COMPETENCIA.PREC_REGISTRA_COTIZACION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",parametros,false);
      }   
    
    //LTAVARA 2016.11.28 SUBIR IMAGEN AL PRODUCTO DE COMPETENCIA
    public static void registrarSustentoPrecioCompetencia(String pCodLocal,
                                                     String pArchivoSustento,
                                                     String cNumNota
                                                       ) throws SQLException {
         parametros = new ArrayList();
         parametros.add(pCodLocal);
         parametros.add(pArchivoSustento);
         parametros.add(cNumNota);
         
         log.debug("invocando a PTOVENTA_PRECIO_COMPETENCIA.PREC_REGISTRA_SUSTENTO_COTI(?,?,?):"+parametros);
         FarmaDBUtility.executeSQLStoredProcedure(null,"PTOVENTA_PRECIO_COMPETENCIA.PREC_REGISTRA_SUSTENTO_COTI(?,?,?)",parametros,false);
}
       
       public static void registrarAnulacionPrecioCompetencia(String pCodLocal,
                                                     String cNumNota
                                                       ) throws SQLException {
         parametros = new ArrayList();
         parametros.add(pCodLocal);
         parametros.add(cNumNota);
         
         log.debug("invocando a PTOVENTA_PRECIO_COMPETENCIA.PREC_REGISTRA_ANULACION_COTI(?,?):"+parametros);
         FarmaDBUtility.executeSQLStoredProcedure(null,"PTOVENTA_PRECIO_COMPETENCIA.PREC_REGISTRA_ANULACION_COTI(?,?)",parametros,false);
         }
       
       public static void listarPrecioCompetenciaPendiente(ArrayList pArray) throws SQLException {
         parametros = new ArrayList();
         
         log.debug("invocando a PTOVENTA_PRECIO_COMPETENCIA.PREC_LISTA_COT_PENDIENTES:"+parametros);
         FarmaDBUtility.executeSQLStoredProcedureArrayList(pArray,"PTOVENTA_PRECIO_COMPETENCIA.PREC_LISTA_COT_PENDIENTES",parametros);
         }
       
       /*
        * 2017.04.05 LTAVARA RUTA DE LA IMAGEN
        * */
        public static String getRutaImagenTemporal() throws SQLException {
             parametros = new ArrayList();
             parametros.add(FarmaVariables.vCodGrupoCia);
             parametros.add(FarmaVariables.vCodLocal);
             
             log.debug("invocando a PTOVENTA_PRECIO_COMPETENCIA.F_RUTA_IMAGEN(?,?):"+parametros);
           return  FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_PRECIO_COMPETENCIA.F_RUTA_IMAGEN(?,?)",parametros);
        }
        
        /*
         * 2017.04.05 LTAVARA RUTA DE LA IMAGEN para subir por PHP
         * */
         public static String getRutaSubirImagenPHP() throws SQLException {
              parametros = new ArrayList();

             parametros.add(FarmaVariables.vCodGrupoCia);
             parametros.add(FarmaVariables.vCodLocal);
              
              log.debug("invocando a PTOVENTA_PRECIO_COMPETENCIA.F_RUTA_SUBIR_IMAGENPHP(?,?):"+parametros);
            return  FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_PRECIO_COMPETENCIA.F_RUTA_SUBIR_IMAGENPHP(?,?)",parametros);
         }
       
    }

