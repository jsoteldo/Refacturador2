package mifarma.ptoventa.tomainventario;

import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.worker.JDialogProgress;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.BorderLayout;
import java.awt.Frame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.Connection;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.swing.JOptionPane;

import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.recaudacion.DlgProcesarVentaCMR;

import mifarma.ptoventa.tomainventario.reference.DBTomaInv;

import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import mifarma.ptoventa.tomainventario.valueobjects.ProductoLaboratorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cargando extends JDialogProgress {
    private static final Logger log = LoggerFactory.getLogger(DlgProcesarVentaCMR.class);

    public static String estado = "";
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();

    int countProducto = 0;
    int countBarra = 0;
    private Frame myParentFrame;
    public static int cantPdaUp = 0;
    public String cantPdas;
    private boolean androidEckerd;
    private String message = "";
    public final static String PATH_PDANET_API = "C:/PdaNetApi/PdaNetApi.exe";


    public Cargando() {
        super();
    }

    public Cargando(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.myParentFrame = parent;
        try {
            //jbInit();

        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void cerrarVentana() {
        estado = "";
        this.setVisible(false);
        this.dispose();
    }

    @Override
    public void ejecutaProceso() {
        if (estado.equalsIgnoreCase("G")) {
            generarDatos();
        } else if (estado.equalsIgnoreCase("E")) {
            enviarDatos();
        }
        else if (estado.equalsIgnoreCase("L")) {
            buscarArchivoPDA();
        }
        this.cerrar();
        this.cerrarVentana();
    }

    public void generarDatos() {
        try {
            DBTomaInv.pathOriginal = DBTomaInv.obtenerRutaConfigPda();
            if (!new File(DBTomaInv.pathOriginal).exists()) {
                new File(DBTomaInv.pathOriginal).mkdirs();
            }
            DBTomaInv dbi = new DBTomaInv();
            dbi.creaBD();
            ArrayList dataPBarra = dbi.getDataPBarra();
            ArrayList dataProducto = dbi.getDataProducto();
            System.out.println("Comenzo a las  " + new Date());

            Connection conPB = null;
            conPB = dbi.connect();
            System.out.println("Cargando data de producto Barra");
            for (countBarra = 0; countBarra < dataPBarra.size(); countBarra++) {
                ArrayList prefijo = (ArrayList)dataPBarra.get(countBarra);
                dbi.setDataPBarra(prefijo.get(1).toString(), prefijo.get(0).toString(), conPB);
            }
            conPB.commit();
            conPB.close();

            Connection conPO = null;
            conPO = dbi.connect();
            System.out.println("Cargando data de producto");

            for (countProducto = 0; countProducto < dataProducto.size(); countProducto++) {
                ArrayList prefijo = (ArrayList)dataProducto.get(countProducto);
                dbi.setDataProducto(prefijo.get(0).toString(), prefijo.get(1).toString(), prefijo.get(2).toString(),
                                    prefijo.get(3).toString(), prefijo.get(4).toString(), prefijo.get(5).toString(),
                                    prefijo.get(6).toString(), prefijo.get(7).toString(), conPO);
            }

            conPO.commit();
            conPO.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String buscarArchivoPDA() {
        try {
            if (buscarArchivoDesdePDA("eckerd.s3db")) {

                log.info("-- El PDA se encuentra pendiente de exportación de datos --");
                this.setMessage("PED");
            }
            else {
                DBTomaInv.pathOriginal = DBTomaInv.obtenerRutaConfigPda();
                if (buscarArchivoDesdePDA(VariablesTomaInv.PRODUCTOS_INVENTARIO)) {
                    log.info("el archivo productoInventario.txt existe");
                    copyFileInDevice(VariablesTomaInv.PRODUCTOS_INVENTARIO,
                                     VariablesTomaInv.PRODUCTOS_INVENTARIO_SIN_TXT + "_bk.txt");
                    traerArchivoDesdePDA(VariablesTomaInv.PATH_PDANET_API, "traer", DBTomaInv.pathOriginal, VariablesTomaInv.PRODUCTOS_INVENTARIO);

                    try {
                        VariablesTomaInv.cantPdaUp = DBTomaInv.getCantPdaCargadoTomaInv();
                        if(muestraContenido(DBTomaInv.pathOriginal + VariablesTomaInv.PRODUCTOS_INVENTARIO)){
                            this.setMessage("SSD");
                        }// procedimiento que carga los datos

                    } catch (FileNotFoundException f) {
                        f.printStackTrace();
                    } catch (IOException f) {
                        f.printStackTrace();
                    } catch (SQLException f) {
                        f.printStackTrace();
                    }
                } else {
                    log.info("No se encuentran datos para exportar, no existe el archivo " + VariablesTomaInv.PRODUCTOS_INVENTARIO);
                    this.setMessage("NSD");

                } // fin else

            } // fin else
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return message;
    }

    public boolean copyFileInDevice(String oldFileName, String newFileName) {
        boolean estado = false;
        String[] commands = { PATH_PDANET_API, "copiar", oldFileName, newFileName };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                    if (stdError.readLine() != null) {
                        System.err.println("El archivo " + oldFileName + " no existe");
                        estado = false;
                    } else if (stdInput.readLine() != null) {
                        System.out.println("El archivo " + oldFileName + " fue encontrado");
                        estado = true;
                    }
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return estado;
    }


    private boolean muestraContenido(String archivo) throws FileNotFoundException, IOException, SQLException {
        boolean status = false;
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        int Diferencia = 0;
        String anaquel = "";

        try {
            while ((cadena = b.readLine()) != null) {
                String[] arrOfStr = cadena.split("@");
                List<String> itemList = Arrays.asList(arrOfStr);

                String[] arregloanaquel = itemList.get(6).toString().split(",");
                VariablesTomaInv.vCodProd = itemList.get(0);
                log.info(arregloanaquel.toString());

                List listArreloAnaquelesPDA = Arrays.asList(arregloanaquel);
                ArrayList listaAnaquelesBD = new ArrayList();
                DBTomaInv.ListaAnaquelesTomaInventario(listaAnaquelesBD);

                String splitArrayBD = "";
                if (!listaAnaquelesBD.isEmpty()) {
                    splitArrayBD = listaAnaquelesBD.get(0).toString().replaceAll("[\\[\\ \\]]", ""); 
                }

                String[] bdSplit = splitArrayBD.split(",");

                Set listaTres = new HashSet();

                for (int i = 0; i < listArreloAnaquelesPDA.size(); i++) {
                    listaTres.add(listArreloAnaquelesPDA.get(i));
                }
                for (int i = 0; i < bdSplit.length; i++) {
                    listaTres.add(bdSplit[i]);

                }
                listaTres.remove("0");
                anaquel = listaTres.toString();

                anaquel = anaquel.replaceAll("[\\[\\ \\]]", "");
                if (itemList.get(2).equalsIgnoreCase("S")) {
                    Diferencia =
                            (Integer.parseInt(itemList.get(4)) * Integer.parseInt(itemList.get(3))) + Integer.parseInt(itemList.get(5));
                } else if (itemList.get(2).equalsIgnoreCase("N")) {
                    Diferencia = Integer.parseInt(itemList.get(4));
                }

                DBTomaInv.cargaTemporalTomaInv(FarmaVariables.vCodLocal, VariablesTomaInv.vSecToma,
                                               String.valueOf(itemList.get(0)), Integer.parseInt(itemList.get(3)),
                                               Diferencia, FarmaVariables.vIdUsu, anaquel, "P");
            }
            DBTomaInv.cargaTomaInv(FarmaVariables.vCodLocal, VariablesTomaInv.vSecToma, FarmaVariables.vIdUsu);
            cantPdaUp = VariablesTomaInv.cantPdaUp + 1;
            b.close();
            renombrarFileInventario(DBTomaInv.pathOriginal + VariablesTomaInv.PRODUCTOS_INVENTARIO);
            DBTomaInv.updateLgtcTomaInventarioCantPda(cantPdaUp);
            FarmaUtility.aceptarTransaccion();
            eliminarArchivoDesdePDA(VariablesTomaInv.PATH_PDANET_API, "borrar", VariablesTomaInv.PRODUCTOS_INVENTARIO);
            eliminarArchivoDesdePDA(VariablesTomaInv.PATH_PDANET_API, "borrar", "PdaUser.txt");
            eliminarArchivoDesdePDA(VariablesTomaInv.PATH_PDANET_API, "borrar", "eckerd_bk.s3db");
            status = true;
        } catch (FileNotFoundException e) {
            status = false;
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Mensaje del Sistema",
                                          JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        } catch (IOException t) {
            status = false;
            JOptionPane.showMessageDialog(this, "Error: " + t.getMessage(), "Mensaje del Sistema",
                                          JOptionPane.INFORMATION_MESSAGE);
            t.printStackTrace();
        } catch (SQLException g) {
            status = false;
            JOptionPane.showMessageDialog(this, "Error: " + g.getMessage(), "Mensaje del Sistema",
                                          JOptionPane.INFORMATION_MESSAGE);
            g.printStackTrace();
        }
        return status;
    }

    public void renombrarFileInventario(String dire) {
        File fileInventario = new File(dire);
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {

            if (!new File(DBTomaInv.pathOriginal + "old_txt").exists()) {
                new File(DBTomaInv.pathOriginal + "old_txt").mkdirs();
            }
            String pathMove = DBTomaInv.pathOriginal +"old_txt\\productoInventario_" + hourFormat.format(date) + ".txt";
            Path origenPath = FileSystems.getDefault().getPath(dire);
            Path destinoPath = FileSystems.getDefault().getPath(pathMove);
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void obtieneCantPdaCargadoTomaInv() {
        try {
            DlgLaboratoriosPorToma toma;
            VariablesTomaInv.cantPdaUp = DBTomaInv.getCantPdaCargadoTomaInv();
            if (VariablesTomaInv.cantPdaUp <= 0) {
                cantPdas = "0";
            } else {
                cantPdas = String.valueOf(VariablesTomaInv.cantPdaUp);
            }
            toma = new DlgLaboratoriosPorToma();
            toma.lblCantPda.setText(cantPdas);
        } catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
        }
    }

    public boolean buscarArchivoDesdePDA(String fileName) {
        boolean estado = false;
        String[] commands = { VariablesTomaInv.PATH_PDANET_API, "buscar", fileName };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                    if (stdError.readLine() != null) {
                        System.err.println("El archivo " + fileName + " no existe");
                        estado = false;
                    } else if (stdInput.readLine() != null) {
                        System.out.println("El archivo " + fileName + " fue encontrado");
                        estado = true;
                    }
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return estado;
    }

    public void traerArchivoDesdePDA(String pathApi, String action, String localPath, String deviceFile) {
        //                    Ruta del API    Acción   Ruta  Nombre del archivo
        String[] commands = { pathApi, action, localPath, deviceFile };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
            System.out.println("Archivo " + deviceFile + " copiado a la PC");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void enviarDatos() {
        try {
            DBTomaInv.pathOriginal = DBTomaInv.obtenerRutaConfigPda();
            // Enviando el archivo de base de datos SQLite
            DlgGenerarData.enviarArchivoAlPDA(VariablesTomaInv.PATH_PDANET_API, "enviar", DBTomaInv.pathOriginal, "eckerd.s3db",
                                              "eckerd.s3db");
            if (!isAndroidEckerd()) {
                // Enviando el archivo backup de base de datos SQLite
                DlgGenerarData.enviarArchivoAlPDA(VariablesTomaInv.PATH_PDANET_API, "enviar", DBTomaInv.pathOriginal, "eckerd.s3db",
                                                  "eckerd_bk.s3db");

                // Enviando el archivo con el nombre de usuario
                DlgGenerarData.enviarArchivoAlPDA(VariablesTomaInv.PATH_PDANET_API, "enviar", DBTomaInv.pathOriginal,
                                                  VariablesTomaInv.USER_NAME_PDA, VariablesTomaInv.USER_NAME_PDA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarArchivoDesdePDA(String pathApi, String action, String deviceFile) {
        String[] commands = { pathApi, action, deviceFile };
        try {
            Process p = Runtime.getRuntime().exec(commands);
            boolean no_exit = true;

            while (no_exit) {
                try {
                    p.exitValue();
                    no_exit = false;
                } catch (IllegalThreadStateException exception) {
                    no_exit = true;
                }
            }
            System.out.println("Archivo " + deviceFile + " eliminado del PDA");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }


    public int getCountProducto() {
        return countProducto;
    }

    public void setCountProducto(int countProducto) {
        this.countProducto = countProducto;
    }


    public int getCountBarra() {
        return countBarra;
    }


    public String getcantPdas() {
        return cantPdas;
    }

    public void setCountBarra(int countBarra) {
        this.countBarra = countBarra;
    }

    public boolean isAndroidEckerd() {
        return androidEckerd;
    }

    public void setAndroidEckerd(boolean androidEckerd) {
        this.androidEckerd = androidEckerd;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
