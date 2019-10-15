package mifarma.ptoventa.convalidado.reference;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaDBUtility;
import mifarma.common.FarmaDBUtilityRemoto;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.VariablesCaja;
import mifarma.ptoventa.convalidado.reference.VariablesConvalidado;
import mifarma.ptoventa.matriz.mantenimientos.productos.references.VariablesProducto;
import mifarma.ptoventa.recepcionCiega.reference.VariablesRecepCiega;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2006 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo     : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DBConvalidado.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * <br>
 * @author Jhony Monzalve V.<br>
 * @version 1.0<br>
 *
 */
public class DBConvalidado {
    private static final Logger log = LoggerFactory.getLogger(DBConvalidado.class);
    private static ArrayList parametros = new ArrayList();

    public DBConvalidado() {
        //Constructor de la clase DBConvalidado
    }
    
    public static void getListaProdsAK(FarmaTableModel pTableModel) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        log.debug("invocando a PTOVENTA_MERMA.MER_LISTA_ITEMS_AK(?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_MERMA.MER_LISTA_ITEMS_AK(?,?)", parametros, false);
    }
    
    public static void ingresaAjusteKardex(String pCodMotKardex, String pNeoCant, String pGlosa) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesConvalidado.vCodProd);
        parametros.add(pCodMotKardex);
        parametros.add(pNeoCant);
        parametros.add(pGlosa);
        parametros.add(ConstantsPtoVenta.TIP_DOC_KARDEX_AJUSTE);
        parametros.add(FarmaVariables.vIdUsu);
        log.debug("invocando a PTOVENTA_MERMA.MER_INGRESA_AJUSTE_KARDEX(?,?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_MERMA.MER_INGRESA_AJUSTE_KARDEX(?,?,?,?,?,?,?,?)",
                                                 parametros, false);
    }
    
    public static void ingresaReversaMerma(String pCodMotKardex, String pNeoCant, String pGlosa, String secKardex) throws SQLException {
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesConvalidado.vCodProd);
        parametros.add(pCodMotKardex);
        parametros.add(pNeoCant);
        parametros.add(pGlosa);
        parametros.add(ConstantsPtoVenta.TIP_DOC_KARDEX_AJUSTE);
        parametros.add(FarmaVariables.vIdUsu);
        parametros.add(secKardex);
        log.debug("invocando a PTOVENTA_MERMA.MER_REVERSA_AJUSTE_KARDEX(?,?,?,?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(null, "PTOVENTA_MERMA.MER_REVERSA_AJUSTE_KARDEX(?,?,?,?,?,?,?,?,?)",
                                                 parametros, false);
    }

    public static void getListaMovsKardex(FarmaTableModel pTableModel, String filtro) throws SQLException {
        pTableModel.clearTable();
        parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        parametros.add(FarmaVariables.vCodLocal);
        parametros.add(VariablesConvalidado.vCodProd);
        parametros.add(VariablesConvalidado.vFecIniMovKardex);
        parametros.add(VariablesConvalidado.vFecFinMovKardex);
        parametros.add(filtro);
        log.debug("invocando a PTOVENTA_MERMA.MER_LISTA_MOVS_KARDEX(?,?,?,?,?,?):" + parametros);
        FarmaDBUtility.executeSQLStoredProcedure(pTableModel, "PTOVENTA_MERMA.MER_LISTA_MOVS_KARDEX(?,?,?,?,?,?)",
                                                 parametros, false);
    }
    
    public static String validarAsistenteAuditoria(String codcia, String codloc, String secusu) throws SQLException {
        parametros = new ArrayList();
        parametros.add(codcia);
        parametros.add(codloc);
        parametros.add(secusu);
        log.debug("invocando a PTOVENTA_MERMA.MER_F_VALIDA_ASIST_AUDIT: " + parametros);
        return FarmaDBUtility.executeSQLStoredProcedureStr("PTOVENTA_MERMA.MER_F_VALIDA_ASIST_AUDIT(?,?,?)", parametros);
    }
    
}
