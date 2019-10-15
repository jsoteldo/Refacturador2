package mifarma.ptoventa.fraccion.reference;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.fraccion.DAO.BDConexion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UtilityConexion {

    static final Logger log = LoggerFactory.getLogger(UtilityConexion.class);
    private BDConexion conex = new BDConexion();

    public UtilityConexion() {
    }
    
    public boolean actualizarTemp_Hist_frac(ArrayList<ArrayList> fracAprobados) {
        boolean ok=true;
        Connection conn = conex.getConexionFraccion();
        try {
            if (!conn.isClosed()) {
                log.info("CONEXION ESTABLECIDA:\n\t" + conex.cadenaConexion);
            } else {
                log.info("LA CONEXION HA SIDO CERRADA");
            }
        } catch (SQLException e) {
            log.info("ERROR AL ESTABLECER CONEXION");
            e.printStackTrace();
            return false;
        }
        
        for (int i = 0; i < fracAprobados.size(); i++) {
            String codLocal = fracAprobados.get(i).get(0).toString().trim();
            String codProd = fracAprobados.get(i).get(1).toString().trim();
            int valFrac = Integer.parseInt(fracAprobados.get(i).get(2).toString().trim());            
            Date fecIni = cambiaFormato(fracAprobados.get(i).get(3).toString());
            String descFrac = fracAprobados.get(i).get(4).toString().trim();
            
            try {
                CallableStatement stmt =
                    conn.prepareCall("{call APPS.ADMCENTRAL_SOLICITA_FRACCION.INSERTA_DATOS_TEMPORALES(?,?,?,?,?)}");
                stmt.setString(1, codLocal);
                stmt.setString(2, codProd);
                stmt.setInt(3, valFrac);
                stmt.setDate(4, fecIni);
                stmt.setString(5, descFrac);
                stmt.execute();
                stmt.close();
                if(i == fracAprobados.size()-1){
                    log.info("CERRAR CONEXION");
                    if (!conn.isClosed())
                        conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                log.error("error al insertar la tabla temportal t_adm_hist_frac 1");
                e.printStackTrace();
                return false;
            } catch (Exception e){
                ok=false;
                log.error("error al insertar la tabla temportal t_adm_hist_frac 2");
                e.printStackTrace();
                return false;
            } 
        }
        return ok;
    }

    private Date cambiaFormato(String dato) {
        Date fecha =null;
        if(!dato.trim().equalsIgnoreCase("")){
            String dia=dato.substring(0,dato.indexOf("/"));
            String mes=dato.substring(dato.indexOf("/")+1, dato.lastIndexOf("/"));
            String anio=dato.substring(dato.lastIndexOf("/")+1);
            fecha= new Date(Integer.parseInt(anio)+100,Integer.parseInt(mes)-1,Integer.parseInt(dia));
        }
        return fecha;
    }
    
    boolean enviarDetalleSolicitud(String pCodLocal, String pCodSolic, String pCodProd, String pCodLab,
                                   String pTipoFrac, String pEstado, String pComenFrac, String pUsuMod, String pLocalCrea,
                                   int pSecHist, String pComenAten, String pFecAten, int pValFrac, String pDescFrac,
                                   int pSend, int pValFracHist, String pDescFracHist) {
        boolean ok=false;
        Date fechaAten=cambiaFormato(pFecAten);
        Connection conn = conex.getConexionFraccion();
        try {
            CallableStatement stmt = conn.prepareCall("{call APPS.ADMCENTRAL_SOLICITA_FRACCION.INSERT_DET_PROD_LOCAL" +
                                                        "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            stmt.setString(1, FarmaVariables.vCodGrupoCia);
            stmt.setString(2, pCodLocal);
            stmt.setString(3, pCodSolic);
            stmt.setString(4, pCodProd);
            stmt.setString(5, pCodLab);
            stmt.setString(6, pTipoFrac);
            stmt.setString(7, pEstado);
            stmt.setString(8, pComenFrac);
            stmt.setString(9, pUsuMod);
            stmt.setString(10, pLocalCrea);
            stmt.setInt(11, pSecHist);
            stmt.setString(12, pComenAten);
            stmt.setDate(13, fechaAten);
            stmt.setInt(14, pValFrac);
            stmt.setString(15, pDescFrac);
            stmt.setInt(16, pSend); 
            stmt.setInt(17, pValFracHist);
            stmt.setString(18, pDescFracHist);
            stmt.execute();
            stmt.close();
            if (!conn.isClosed())
                conn.close();
            conn = null;
            ok=true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return ok;
    }

    boolean enviarSolicitud(String cCodLocal, String cCodSolic, int cNroSolic, String cFecSolic, String cFecAtenc,
                            String cUserSolic, String cEstSolic, String cUserAtien, String cLocalCrea, int pSend) {
        boolean ok=false;
        Date fechaSolic=cambiaFormato(cFecSolic);
        Date fechaAten=cambiaFormato(cFecAtenc);
        Connection conn = conex.getConexionFraccion();
        try {
            CallableStatement stmt = conn.prepareCall("{call APPS.ADMCENTRAL_SOLICITA_FRACCION.INSERT_CAB_SOLIC_LOCAL" +
                                                        "(?,?,?,?,?,?,?,?,?,?,?)}");
            stmt.setString(1, FarmaVariables.vCodGrupoCia);
            stmt.setString(2, cCodLocal);
            stmt.setString(3, cCodSolic);
            stmt.setInt(4, cNroSolic);
            stmt.setDate(5, fechaSolic);
            stmt.setDate(6, fechaAten);
            stmt.setString(7, cUserSolic);
            stmt.setString(8, cEstSolic);
            stmt.setString(9, cUserAtien);
            stmt.setString(10, cLocalCrea);
            stmt.setInt(11, pSend);
            stmt.execute();
            stmt.close();
            if (!conn.isClosed())
                conn.close();
            conn = null;
            ok=true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return ok;
    }

}
