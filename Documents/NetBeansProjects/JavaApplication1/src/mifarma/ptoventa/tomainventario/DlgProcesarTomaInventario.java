package mifarma.ptoventa.tomainventario;

import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.worker.JDialogProgress;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.FileOutputStream;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.recaudacion.DlgProcesarVentaCMR;

import mifarma.ptoventa.recaudacion.reference.ConstantsRecaudacion;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgProcesarTomaInventario extends JDialogProgress {
    private static final Logger log = LoggerFactory.getLogger(DlgProcesarVentaCMR.class);

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelWhite jContentPane = new JPanelWhite();

    private Frame myParentFrame;

    String ruta;
    JTable tblRelacionDiferenciasProductos;
    String indGuardar = "N";

    public DlgProcesarTomaInventario() {
        super();
    }

    public DlgProcesarTomaInventario(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.myParentFrame = parent;
        try {
            //jbInit();

        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    @Override
    public void ejecutaProceso() {
        exportarReporteDiferencia();

    }


    /**
     * Indicador de Conciliacion En Linea
     * @author LTAVARA
     * @since 2016.10.11
     */
    public void exportarReporteDiferencia() {

        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet();
        try {
            FileOutputStream elFichero = new FileOutputStream(ruta);
            log.debug("Filas totales: "+tblRelacionDiferenciasProductos.getRowCount());
            for (int i = 0; i < tblRelacionDiferenciasProductos.getRowCount()+1; i++) {
                indGuardar = "S";

                HSSFRow fila = hoja.createRow(i);
                if (i == 0) {
                    for (int j = 0; j < tblRelacionDiferenciasProductos.getColumnCount(); j++) {
                        HSSFCell celda = fila.createCell(j);
                        HSSFCellStyle cellStyle = libro.createCellStyle();
                        HSSFFont hSSFFont = libro.createFont();
                        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
                        hSSFFont.setFontHeightInPoints((short)11);
                        hSSFFont.setColor(HSSFColor.BLACK.index);
                        cellStyle.setFont(hSSFFont);
                        celda.setCellStyle(cellStyle);
                        
                        if (j == 8 || j == 9) {
                            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                             
                                celda.setCellValue(new HSSFRichTextString(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j).getHeaderValue().toString()));
                                log.info(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j).getHeaderValue().toString());
                            }
                            else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                     
                                celda.setCellValue(new HSSFRichTextString(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j +
                                                                                                                                     2).getHeaderValue().toString()));
                                log.info(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j +
                                                                                                    2).getHeaderValue().toString());
                            }
                        } else if (j == 10 || j == 11) {
                            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                              
                                celda.setCellValue(new HSSFRichTextString(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j).getHeaderValue().toString()));
                                log.info(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j).getHeaderValue().toString());

                            }
                        }    else {
                            celda.setCellValue(new HSSFRichTextString(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j).getHeaderValue().toString()));
                            log.info(tblRelacionDiferenciasProductos.getColumnModel().getColumn(j).getHeaderValue().toString());

                        }
                    }
                } else {
                    for (int j = 0; j < tblRelacionDiferenciasProductos.getColumnCount(); j++) {
                        HSSFCell celda = fila.createCell(j);
                        if (tblRelacionDiferenciasProductos.getValueAt(i-1, j) != null) {
                            if (j == 8 || j == 9 || j == 10 || j == 11) {
                                if (j == 8 || j == 9) {
                                    if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                                        Double precio =
                                            Double.parseDouble(tblRelacionDiferenciasProductos.getValueAt(i-1,j).toString().replaceAll(",",
                                                                                                                                   ""));
                                        log.debug("" + precio);
                                        celda.setCellValue(precio);
                                    }
                                    else if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)) {
                                        Double precio =
                                            Double.parseDouble(tblRelacionDiferenciasProductos.getValueAt(i-1,
                                                                                                          j + 2).toString().replaceAll(",",
                                                                                                                                       ""));
                                        log.debug("" + precio);
                                        celda.setCellValue(precio);
                                    }
                                }
                                if (j == 10 || j == 11) {
                                    if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)) {
                                        Double precio =
                                            Double.parseDouble(tblRelacionDiferenciasProductos.getValueAt(i-1,j).toString().replaceAll(",",
                                                                                                                                   ""));
                                        log.debug("" + precio);
                                        celda.setCellValue(precio);
                                    }
                                }
                            } else {
                                if(j==0){
                                    log.debug("Codigo" + tblRelacionDiferenciasProductos.getValueAt(i-1, j).toString()); 
                                }
                                celda.setCellValue(new HSSFRichTextString(tblRelacionDiferenciasProductos.getValueAt(i-1,
                                                                                                                     j).toString()));
                            }
                        }
                    }
                }

            }
            libro.write(elFichero);
            elFichero.close();

        } catch (Exception a) {
            indGuardar = "N";
            log.error("", a);
            FarmaUtility.showMessage(this, "Error al guardar el archivo.\n" +
                    a.getMessage(), null);
            cerrarVentana();


        }
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setTblRelacionDiferenciasProductos(JTable tblRelacionDiferenciasProductos) {
        this.tblRelacionDiferenciasProductos = tblRelacionDiferenciasProductos;
    }

    public JTable getTblRelacionDiferenciasProductos() {
        return tblRelacionDiferenciasProductos;
    }

    public String getIndGuardar() {
        return indGuardar;
    }
}
