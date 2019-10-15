package mifarma.ptoventa.main;


import com.gs.encripta.FarmaEncripta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import javax.swing.JFrame;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.BeanConexion;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.VariablesPtoVenta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ERIOS
 * @since 05.11.2015
 */
abstract class ReadProperties {

    static private final Logger log = LoggerFactory.getLogger(ReadProperties.class);

    static JFrame myparent = new JFrame();

    String prop1, prop2, prop3;

    /**
     * Realiza la lectura del archivo Properties para determinar el seteo de
     * variables
     */
    protected boolean readFileProperties() {
        boolean propertiesServidorCorrecto = false;
        boolean propertiesClienteCorrecto = true;
        try {
            InputStream fis = null;
            Properties properties = null;
            File archivo = null;
            // LEE PROPERTIES DEL SERVIDOR
            fis = this.getClass().getResourceAsStream("/PtoVentaServ.properties");

            if (fis == null) {
                archivo = new File(prop1);
                fis = new FileInputStream(archivo);
            }
            if (fis != null) {
                properties = new Properties();
                properties.load(fis);
                FarmaVariables.vCodGrupoCia = properties.getProperty("CodigoGrupoCompania");
                FarmaVariables.vCodCia = properties.getProperty("CodigoCompania");
                FarmaVariables.vCodLocal = properties.getProperty("CodigoLocal");
                FarmaVariables.vImprReporte = properties.getProperty("ImpresoraReporte");
                FarmaVariables.vIPBD = properties.getProperty("IpServidor");

                propertiesServidorCorrecto = true;
            } else {
                FarmaUtility.showMessage(myparent,
                                         "Archivo de Configuracion del Servidor no Encontrado.\nP�ngase en contacto con el �rea de sistemas.",
                                         null);
                propertiesServidorCorrecto = false;
            }
            if (propertiesServidorCorrecto && propertiesClienteCorrecto)
                return true;
            else
                return false;
        } catch (FileNotFoundException fnfException) {
            log.error("", fnfException);
            FarmaUtility.showMessage(myparent,
                                     "Archivo de Configuracion del Servidor no Encontrado.\nP�ngase en contacto con el �rea de sistemas.",
                                     null);
        } catch (IOException ioException) {
            log.error("", ioException);
            FarmaUtility.showMessage(myparent,
                                     "Error al leer archivo de Configuracion.\nP�ngase en contacto con el �rea de sistemas.",
                                     null);
        }
        myparent.dispose();
        return false;
    }

    /**
     * Realiza la lectura del archivo Properties para determinar la clave
     * de conexion con BD
     */
    protected boolean readFilePasswordProperties() {
        boolean propertiesClaveCorrecto = true;
        try {
            InputStream fis = null;
            Properties properties = null;
            File archivo = null;
            // LEE PROPERTIES DE LA CLAVE
            fis = this.getClass().getResourceAsStream(FarmaConstants.RUTA_PROPERTIES_CLAVE);

            if (fis == null) {
                archivo = new File(prop2);
                fis = new FileInputStream(archivo);
            }
            if (fis != null) {
                properties = new Properties();
                properties.load(fis);
                FarmaVariables.vClaveBD = FarmaEncripta.desencripta(properties.getProperty("ClaveBD"));

                FarmaVariables.vSID = properties.getProperty("SID");
                if (FarmaVariables.vSID == null) {
                    FarmaVariables.vSID = ConstantsPtoVenta.SID;
                }

                FarmaVariables.vUsuarioBD = properties.getProperty("UsuarioBD");
                if (FarmaVariables.vUsuarioBD == null) {
                    FarmaVariables.vUsuarioBD = ConstantsPtoVenta.USUARIO_BD;
                }

                propertiesClaveCorrecto = true;
            } else {
                FarmaUtility.showMessage(myparent,
                                         "Archivo de Configuracion de Clave no Encontrado.\nP�ngase en contacto con el �rea de sistemas.",
                                         null);
                propertiesClaveCorrecto = false;
            }
            if (propertiesClaveCorrecto)
                return true;
            else
                return false;
        } catch (FileNotFoundException fnfException) {
            log.error("", fnfException);
            FarmaUtility.showMessage(myparent,
                                     "Archivo de Configuracion de Clave no Encontrado.\nP�ngase en contacto con el �rea de sistemas.",
                                     null);
        } catch (IOException ioException) {
            log.error("", ioException);
            FarmaUtility.showMessage(myparent,
                                     "Error al leer archivo de Configuracion de Clave.\nP�ngase en contacto con el �rea de sistemas.",
                                     null);
        }
        myparent.dispose();
        return false;
    }

    /**
     * Realiza la lectura del archivo Properties para determinar los valores de las bases de datos remotas
     */
    protected boolean readFileServRemotosProperties() {
        boolean propertiesServidorCorrecto = false;
        boolean propertiesClienteCorrecto = true;
        try {
            InputStream fis = null;
            Properties properties = null;
            File archivo = null;

            fis = this.getClass().getResourceAsStream("/PtoVentaServRemotos.properties");

            if (fis == null) {
                archivo = new File(prop3);
                fis = new FileInputStream(archivo);
            }
            if (fis != null) {
                properties = new Properties();
                properties.load(fis);

                /*FarmaVariables.vIdUsuDBMatriz = properties.getProperty("UsuarioMatriz");
                FarmaVariables.vClaveBDMatriz = FarmaEncripta.desencripta(properties.getProperty("ClaveMatriz"));
                FarmaVariables.vIpServidorDBMatriz = properties.getProperty("IpServidorMatriz");
                FarmaVariables.vSidDBMatriz = properties.getProperty("SidMatriz");*/


                /*FarmaVariables.vIdUsuDBDelivery = properties.getProperty("UsuarioDelivery");
                FarmaVariables.vClaveBDDelivery = FarmaEncripta.desencripta(properties.getProperty("ClaveDelivery"));
                FarmaVariables.vIpServidorDBDelivery = properties.getProperty("IpServidorDelivery");
                FarmaVariables.vSidDBDelivery = properties.getProperty("SidDelivery");*/


                /*FarmaVariables.vIdUsuDBADMCentral = properties.getProperty("UsuarioADMCentral")+FarmaVariables.vCodLocal;
                FarmaVariables.vClaveBDADMCentral =FarmaEncripta.desencripta(properties.getProperty("ClaveAdmCentral"));
                FarmaVariables.vIpServidorDBADMCentral = properties.getProperty("IpServidorADMCentral");
                FarmaVariables.vSidDBADMCentral = properties.getProperty("SidADMCentral");*/


                /*FarmaVariables.vIdUsuDBRac = properties.getProperty("UsuarioRAC");
                FarmaVariables.vClaveBDRac = FarmaEncripta.desencripta(properties.getProperty("ClaveRAC"));
                FarmaVariables.vIpServidorDBRac = properties.getProperty("IpServidorRAC");
                FarmaVariables.vSidDBRac = properties.getProperty("SidRAC");*/

                //ERIOS 19.06.2013 Se lee las variables para la conexion remota con el servidor FASA
                BeanConexion beanFasa = new BeanConexion();
                beanFasa.setUsuarioBD(properties.getProperty("UsuarioFASA"));
                beanFasa.setClaveBD(FarmaEncripta.desencripta(properties.getProperty("ClaveFASA")));
                beanFasa.setIPBD(properties.getProperty("IpServidorFASA"));
                beanFasa.setSID(properties.getProperty("SidFASA"));
                beanFasa.setPORT(properties.getProperty("PortFASA"));
                VariablesPtoVenta.conexionFasa = beanFasa;

                //GFONSECA 01.07.2013 Se lee las variables para la conexion remota con el servidor ADM
                /*BeanConexion beanAdm = new BeanConexion();
                beanAdm.setUsuarioBD(FarmaVariables.vIdUsuDBADMCentral);
                beanAdm.setClaveBD(FarmaVariables.vClaveBDADMCentral);
                beanAdm.setIPBD(FarmaVariables.vIpServidorDBADMCentral);
                beanAdm.setSID(FarmaVariables.vSidDBADMCentral);
                VariablesPtoVenta.conexionAdm = beanAdm;*/

                propertiesServidorCorrecto = true;
            } else {
                FarmaUtility.showMessage(myparent, "Archivo de Configuracion del Servidor no Encontrado.\n" +
                        "P�ngase en contacto con el �rea de sistemas.", null);
                propertiesServidorCorrecto = false;
            }
            if (propertiesServidorCorrecto && propertiesClienteCorrecto)
                return true;
            else
                return false;
        } catch (FileNotFoundException fnfException) {
            log.error("", fnfException);
            FarmaUtility.showMessage(myparent, "Archivo de Configuracion del Servidor no Encontrado.\n" +
                    "P�ngase en contacto con el �rea de sistemas.", null);
        } catch (IOException ioException) {
            log.error("", ioException);
            FarmaUtility.showMessage(myparent, "Error al leer archivo de Configuracion.\n" +
                    "P�ngase en contacto con el �rea de sistemas.", null);
        }
        myparent.dispose();
        return false;
    }

    abstract void init();
}
