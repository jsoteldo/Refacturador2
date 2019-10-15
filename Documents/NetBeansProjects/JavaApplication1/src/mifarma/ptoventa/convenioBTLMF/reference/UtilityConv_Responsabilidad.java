package mifarma.ptoventa.convenioBTLMF.reference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;

import mifarma.ptoventa.reference.DBPtoVenta;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

public class UtilityConv_Responsabilidad {
    
    public static boolean exportarProductos(FarmaTableModel tableModelDetalleVentas, String descEmpresa, String descLocal, String fechaEmision){
        ArrayList<ArrayList> lista = new ArrayList();
        lista = tableModelDetalleVentas.data;
        HSSFWorkbook wb = null;
        FileInputStream fs = null;
        FileOutputStream fileOut = null;
        try {
            String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
            String nombrePlantillas = DBPtoVenta.getDirectorioPlantillas();
            fs = new FileInputStream(carpetaRaiz + "/plantillas/" + nombrePlantillas);
            wb = new HSSFWorkbook(fs, true);
            HSSFSheet hojaProductosVendidos = null;
            int index = -1;
            String motivo = lista.get(0).get(12).toString().trim();
            if(motivo.equals("01")){
                index = wb.getSheetIndex("Reporte_Faltantes");
                hojaProductosVendidos = wb.getSheet("Reporte_Faltantes");
            }else if(motivo.equals("02")){
                index = wb.getSheetIndex("Reporte_Expirados");
                hojaProductosVendidos = wb.getSheet("Reporte_Expirados");
            }else if(motivo.equals("03")){
                index = wb.getSheetIndex("Reporte_Deteriorados");
                hojaProductosVendidos = wb.getSheet("Reporte_Deteriorados");
            }
            int countHojas = wb.getNumberOfSheets();
            for(int i = 0; i < countHojas; i++){
                if(i != index){
                    wb.setSheetHidden(i, true);
                }else{
                    wb.setActiveSheet(i);
                }
            }
            CellStyle styleData = wb.createCellStyle();
            styleData.setBorderBottom(BorderStyle.THIN);
            styleData.setBorderTop(BorderStyle.THIN);
            styleData.setBorderLeft(BorderStyle.THIN);
            styleData.setBorderRight(BorderStyle.THIN);
            org.apache.poi.ss.usermodel.Font hfontTextoTamanio = wb.createFont();
            hfontTextoTamanio.setFontName("Arial");
            CellStyle styleTipoTextoTamanio = wb.createCellStyle();
            styleTipoTextoTamanio.setFont(hfontTextoTamanio);
            CellStyle styleNegrita = wb.createCellStyle();
            org.apache.poi.ss.usermodel.Font hfont = wb.createFont();
            hfont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            hfont.setFontName("Arial");
            styleNegrita.setFont(hfont);//Estilo de la fuente
            
            Row rowCabecera1 = hojaProductosVendidos.createRow((short) 4);
            Cell cellEmpresa = rowCabecera1.createCell(3);cellEmpresa.setCellValue("Empresa :");cellEmpresa.setCellStyle(styleNegrita);
            Cell cellDescEmpresa = rowCabecera1.createCell(4);cellDescEmpresa.setCellValue(descEmpresa);cellDescEmpresa.setCellStyle(styleTipoTextoTamanio);
            Cell cellFechaEmision = rowCabecera1.createCell(6);cellFechaEmision.setCellValue("Fecha de Emision :");cellFechaEmision.setCellStyle(styleNegrita);
            Cell cellDescFechaEmison = rowCabecera1.createCell(7);cellDescFechaEmison.setCellValue(fechaEmision);cellDescFechaEmison.setCellStyle(styleTipoTextoTamanio);
            Cell cellQuimicoLocal = rowCabecera1.createCell(9);cellQuimicoLocal.setCellValue("Quimico del Local :");cellQuimicoLocal.setCellStyle(styleNegrita);
            Cell cellDescQuimicoLocal = rowCabecera1.createCell(10);cellDescQuimicoLocal.setCellValue(lista.get(0).get(14).toString());cellDescQuimicoLocal.setCellStyle(styleTipoTextoTamanio);
            
            Row rowCabecera2 = hojaProductosVendidos.createRow((short) 5);
            Cell cellLocal = rowCabecera2.createCell(3);cellLocal.setCellValue("Local :");cellLocal.setCellStyle(styleNegrita);
            Cell cellDescLocal = rowCabecera2.createCell(4);cellDescLocal.setCellValue(descLocal);cellDescLocal.setCellStyle(styleTipoTextoTamanio);
            Cell cellFacturacion = rowCabecera2.createCell(6);cellFacturacion.setCellValue("Nro. Facturación :");cellFacturacion.setCellStyle(styleNegrita);
            Cell cellDescFacturacion = rowCabecera2.createCell(7);cellDescFacturacion.setCellValue(lista.get(0).get(15).toString());cellDescFacturacion.setCellStyle(styleTipoTextoTamanio);
            Cell cellFecha = rowCabecera2.createCell(9);cellFecha.setCellValue("Fecha :");cellFecha.setCellStyle(styleNegrita);
            Date fecha = new Date();SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = formato.format(fecha);
            Cell cellDescFecha = rowCabecera2.createCell(10);cellDescFecha.setCellValue(fechaActual);cellDescFecha.setCellStyle(styleTipoTextoTamanio);
            
            int rowIndex=8;
            //filas viene a ser el size() y las columnas
            double totalPrecioVenta = 0;
            double precioTotal = 0;
            for(int rows = 0; rows < lista.size(); rows++){ //For each table row
                Row newRow = hojaProductosVendidos.createRow((short) rowIndex);
                
                Cell cell1 = newRow.createCell(2);cell1.setCellValue(rows+1);cell1.setCellStyle(styleData);
                Cell cell2 = newRow.createCell(3);cell2.setCellValue(lista.get(rows).get(1).toString());cell2.setCellStyle(styleData);
                Cell cell3 = newRow.createCell(4);cell3.setCellValue(lista.get(rows).get(2).toString());cell3.setCellStyle(styleData);
                Cell cell4 = newRow.createCell(5);cell4.setCellValue(lista.get(rows).get(3).toString());cell4.setCellStyle(styleData);
                Cell cell5 = newRow.createCell(6);cell5.setCellValue(lista.get(rows).get(4).toString());cell5.setCellStyle(styleData);
                Cell cell6 = newRow.createCell(7);cell6.setCellValue(lista.get(rows).get(5).toString());cell6.setCellStyle(styleData);
                Cell cell7 = newRow.createCell(8);cell7.setCellStyle(styleData);
                Cell cell8 = newRow.createCell(9);cell8.setCellValue(lista.get(rows).get(7).toString());cell8.setCellStyle(styleData);
                Cell cell9 = newRow.createCell(10);cell9.setCellValue(lista.get(rows).get(8).toString());cell9.setCellStyle(styleData);
                Cell cell10 = newRow.createCell(11);cell10.setCellValue(lista.get(rows).get(9).toString());cell10.setCellStyle(styleData);
                Cell cell11 = newRow.createCell(12);cell11.setCellValue(lista.get(rows).get(10).toString());cell11.setCellStyle(styleData);
                rowIndex++;
                totalPrecioVenta = totalPrecioVenta + Double.parseDouble(lista.get(rows).get(9).toString().replace(",", ""));
                precioTotal = precioTotal + Double.parseDouble(lista.get(rows).get(10).toString().replace(",", ""));
            }
            CellStyle styleTotales = wb.createCellStyle();
            styleTotales.setBorderBottom(BorderStyle.THIN);
            styleTotales.setBorderTop(BorderStyle.THIN);
            styleTotales.setBorderLeft(BorderStyle.THIN);
            styleTotales.setBorderRight(BorderStyle.THIN);
            styleTotales.setFont(hfont);
            Row rowTotales = hojaProductosVendidos.createRow((short) rowIndex);
            Cell cell0 = rowTotales.createCell(11);cell0.setCellValue(FarmaUtility.formatNumber(totalPrecioVenta, 2));cell0.setCellStyle(styleTotales);
            Cell cell1 = rowTotales.createCell(12);cell1.setCellValue(FarmaUtility.formatNumber(precioTotal, 2));cell1.setCellStyle(styleTotales);
            
            FileNameExtensionFilter filterXls = new FileNameExtensionFilter("Documentos MS Excel 95/2003", "xls");
            File lfFile = new File("C:\\");
            final JFileChooser fc = new JFileChooser(lfFile);
            fc.setFileFilter(filterXls);
            int returnVal = fc.showSaveDialog(null);
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String ruta = fc.getSelectedFile().getAbsolutePath(); 
                ruta = rutaExtension(ruta);
                fileOut = new FileOutputStream(ruta); 
                wb.write(fileOut);
            }else{
                return false;
            }
            return true;
        } catch (IOException| SQLException e) {
            System.out.println("Error al crear archivo !!!");
        }finally{
            try{
                if(fileOut != null){
                    fileOut.close();
                }
                if(fs != null){
                    fs.close();
                }
                if(wb != null){
                    wb.close();
                }
            }catch(IOException ex){
                
            }
        }
        return false;
    }
    
    public static boolean exportarDetalleProductos(FarmaTableModel tableModelDetalleEmpleados){
        ArrayList<ArrayList> lista = new ArrayList();
        lista = tableModelDetalleEmpleados.data;
        FileInputStream fs = null;
        HSSFWorkbook wb = null;
        FileOutputStream fileOut = null;
        try {
            String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
            String nombrePlantillas = DBPtoVenta.getDirectorioPlantillas();
            fs = new FileInputStream(carpetaRaiz + "/plantillas/" + nombrePlantillas);
            wb = new HSSFWorkbook(fs, true);
            int index = wb.getSheetIndex("Reporte_Empleados");
            int countHojas = wb.getNumberOfSheets();
            for(int i = 0; i < countHojas; i++){
                if(i != index){
                    wb.setSheetHidden(i, true);
                }else{
                    wb.setActiveSheet(i);
                }
            }
            HSSFSheet hojaEmpleados = wb.getSheet("Reporte_Empleados");
            CellStyle styleData = wb.createCellStyle();
            styleData.setBorderBottom(BorderStyle.THIN);
            styleData.setBorderTop(BorderStyle.THIN);
            styleData.setBorderLeft(BorderStyle.THIN);
            styleData.setBorderRight(BorderStyle.THIN);
            styleData.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            org.apache.poi.ss.usermodel.Font hfontTextoTamanio = wb.createFont();
            hfontTextoTamanio.setFontName("Arial");
            CellStyle styleTipoTextoTamanio = wb.createCellStyle();
            styleTipoTextoTamanio.setFont(hfontTextoTamanio);
            CellStyle styleNegrita = wb.createCellStyle();
            org.apache.poi.ss.usermodel.Font hfont = wb.createFont();
            hfont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            hfont.setFontName("Arial");
            styleNegrita.setFont(hfont);//Estilo de la fuente
            
            Row rowCabecera1 = hojaEmpleados.createRow((short) 4);
            Cell cellEmpresa = rowCabecera1.createCell(3);cellEmpresa.setCellValue("Empresa :");cellEmpresa.setCellStyle(styleNegrita);
            Cell cellDescEmpresa = rowCabecera1.createCell(4);cellDescEmpresa.setCellValue(lista.get(0).get(0).toString());cellDescEmpresa.setCellStyle(styleTipoTextoTamanio);
            Cell cellFechaEmision = rowCabecera1.createCell(6);cellFechaEmision.setCellValue("Fecha de Emision :");cellFechaEmision.setCellStyle(styleNegrita);
            Cell cellDescFechaEmision = rowCabecera1.createCell(7);cellDescFechaEmision.setCellValue(lista.get(0).get(4).toString());cellDescFechaEmision.setCellStyle(styleTipoTextoTamanio);
            Cell cellQuimicoLocal = rowCabecera1.createCell(9);cellQuimicoLocal.setCellValue("Quimico del Local :");cellQuimicoLocal.setCellStyle(styleNegrita);
            Cell cellDescQuimicoLocal = rowCabecera1.createCell(10);cellDescQuimicoLocal.setCellValue(lista.get(0).get(10).toString());cellDescQuimicoLocal.setCellStyle(styleTipoTextoTamanio);
            
            Row rowCabecera2 = hojaEmpleados.createRow((short) 5);
            Cell cellLocal = rowCabecera2.createCell(3);cellLocal.setCellValue("Local :");cellLocal.setCellStyle(styleNegrita);
            Cell cellDescLocal = rowCabecera2.createCell(4);cellDescLocal.setCellValue(lista.get(0).get(9).toString());cellDescLocal.setCellStyle(styleTipoTextoTamanio);
            Cell cellFacturacion = rowCabecera2.createCell(6);cellFacturacion.setCellValue("Nro. Facturación :");cellFacturacion.setCellStyle(styleNegrita);
            Cell cellDescFacturacion = rowCabecera2.createCell(7);cellDescFacturacion.setCellValue(lista.get(0).get(11).toString());cellDescFacturacion.setCellStyle(styleTipoTextoTamanio);
            Cell cellFecha = rowCabecera2.createCell(9);cellFecha.setCellValue("Fecha :");cellFecha.setCellStyle(styleNegrita);
            Date fecha = new Date();SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = formato.format(fecha);
            Cell cellDescFecha = rowCabecera2.createCell(10);cellDescFecha.setCellValue(fechaActual);cellDescFecha.setCellStyle(styleTipoTextoTamanio);
            
            int rowIndex=11;
            //filas viene a ser el size() y las columnas
            double totalPrecioVenta = 0;
            double porcentaje = 0;
            for(int rows = 0; rows < lista.size(); rows++){ //For each table row
                Row newRow = hojaEmpleados.createRow((short) rowIndex);
                newRow.setHeight((short) 800);
                
                Cell cell1 = newRow.createCell(2);cell1.setCellValue(rows+1);cell1.setCellStyle(styleData);
                Cell cell2 = newRow.createCell(3);cell2.setCellValue(lista.get(rows).get(2).toString());cell2.setCellStyle(styleData);
                Cell cell3 = newRow.createCell(4);cell3.setCellValue(lista.get(rows).get(3).toString());cell3.setCellStyle(styleData);
                Cell cell4 = newRow.createCell(5);cell4.setCellStyle(styleData);
                Cell cell5 = newRow.createCell(6);cell5.setCellValue(lista.get(rows).get(6).toString());cell5.setCellStyle(styleData);
                Cell cell6 = newRow.createCell(7);cell6.setCellValue(lista.get(rows).get(7).toString() + "%");cell6.setCellStyle(styleData);
                CellRangeAddress regionFirmaData = CellRangeAddress.valueOf("I" + (rowIndex + 1) + ":J" + (rowIndex + 1)); 
                hojaEmpleados.addMergedRegion(regionFirmaData);
                Cell cell7 = newRow.createCell(8);cell7.setCellStyle(styleData);
                Cell cell8 = newRow.createCell(9);cell8.setCellStyle(styleData);
                CellRangeAddress regionHuella = CellRangeAddress.valueOf("K" + (rowIndex + 1) + ":L" + (rowIndex + 1)); 
                hojaEmpleados.addMergedRegion(regionHuella);
                Cell cell9 = newRow.createCell(10);cell9.setCellStyle(styleData);
                Cell cell10 = newRow.createCell(11);cell10.setCellStyle(styleData);
                rowIndex++;
                totalPrecioVenta = totalPrecioVenta + Double.parseDouble(lista.get(rows).get(6).toString().replace(",", ""));
                porcentaje = porcentaje + Double.parseDouble(lista.get(rows).get(7).toString().replace(",", ""));
            }
            CellStyle styleTotales = wb.createCellStyle();
            styleTotales.setBorderBottom(BorderStyle.THIN);
            styleTotales.setBorderTop(BorderStyle.THIN);
            styleTotales.setBorderLeft(BorderStyle.THIN);
            styleTotales.setBorderRight(BorderStyle.THIN);
            styleTotales.setFont(hfont);
            Row rowTotales = hojaEmpleados.createRow((short) rowIndex);
            Cell cell0 = rowTotales.createCell(6);cell0.setCellValue(FarmaUtility.formatNumber(totalPrecioVenta, 2));cell0.setCellStyle(styleTotales);
            Cell cell1 = rowTotales.createCell(7);cell1.setCellValue("100.00%");cell1.setCellStyle(styleTotales);
            
            rowIndex = rowIndex + 4;
            Row rowMensaje1 = hojaEmpleados.createRow((short) rowIndex);
            rowMensaje1.setHeight((short)900);
            CellRangeAddress regionMensaje = CellRangeAddress.valueOf("C" + (rowIndex + 1) + ":L" + (rowIndex + 1)); 
            CellStyle styleMensaje = wb.createCellStyle();
            styleMensaje.setWrapText(true);
            org.apache.poi.ss.usermodel.Font hfontMensaje = wb.createFont();
            hfontMensaje.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            hfontMensaje.setFontHeight((short)150);
            styleMensaje.setFont(hfontMensaje);
            hojaEmpleados.addMergedRegion(regionMensaje);
            String mensaje = "Del mismo modo, los colaboradores firmantes  reconocen el incumplimiento de sus obligaciones laborales y que ello genera daños económicos a la Empresa, " +
                "por lo cual autorizan el descuento de los montos detallados, los mismos que son de su responsabilidad, de sus remuneraciones y de ser el caso de sus beneficios sociales " +
                "tales como, las Gratificaciones Legales, Compensación por Tiempo de Servicios y Utilidades  y/o de la liquidación de beneficios sociales, en caso de producirse la " +
                "extinción del vinculo laboral.";
            Cell cellMensaje1 = rowMensaje1.createCell(2);cellMensaje1.setCellValue(mensaje);cellMensaje1.setCellStyle(styleMensaje);
            
            rowIndex = rowIndex + 6;
            CellStyle styleFirma = wb.createCellStyle();
            styleFirma.setFont(hfont);//Estilo de la fuente
            styleFirma.setBorderTop(BorderStyle.MEDIUM);
            styleFirma.setAlignment(styleFirma.ALIGN_CENTER);
            Row rowFirma = hojaEmpleados.createRow((short) rowIndex);
            Cell cellFirma1 = rowFirma.createCell(4);cellFirma1.setCellValue("Firma y Sello del Auditor");cellFirma1.setCellStyle(styleFirma);
            CellRangeAddress regionFirmaDoc = CellRangeAddress.valueOf("I" + (rowIndex + 1) + ":J" + (rowIndex + 1)); 
            hojaEmpleados.addMergedRegion(regionFirmaDoc); 
            Cell cellFirma2 = rowFirma.createCell(8);cellFirma2.setCellValue("Firma y Sello del Q.F.");cellFirma2.setCellStyle(styleFirma);
            Cell cellFirma3 = rowFirma.createCell(9);cellFirma3.setCellStyle(styleFirma);
            
            FileNameExtensionFilter filterXls = new FileNameExtensionFilter("Documentos MS Excel 95/2003", "xls");
            File lfFile = new File("C:\\");
            final JFileChooser fc = new JFileChooser(lfFile);
            fc.setFileFilter(filterXls);
            int returnVal = fc.showSaveDialog(null);
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String ruta = fc.getSelectedFile().getAbsolutePath(); 
                ruta = rutaExtension(ruta);
                fileOut = new FileOutputStream(ruta); 
                wb.write(fileOut);
            }else{
                return false;
            }
            return true;
        } catch (IOException | SQLException e) {
            System.out.println("Error al crear archivo !!!" + e.getMessage());
        }finally{
            try{
                if(fileOut != null){
                    fileOut.close();
                }
                if(fs != null){
                    fs.close();
                }
                if(wb != null){
                    wb.close();
                }
            }catch(IOException ex){
                
            }
        }
        return false;
    }
    
    public static boolean exportarVentaCobroResponsabilidad(FarmaTableModel tableModelDetalleEmpleados, FarmaTableModel tableModelDetalleProductos, String descEmpresa, String descLocal, String fechaEmision){
        ArrayList<ArrayList> listaEmpleados = new ArrayList();
        ArrayList<ArrayList> listaProductos = new ArrayList();
        listaEmpleados = tableModelDetalleEmpleados.data;
        listaProductos = tableModelDetalleProductos.data;
        FileInputStream fs = null;
        HSSFWorkbook wb = null;
        HSSFSheet hojaProductosVendidos = null;
        FileOutputStream fileOut = null;
        try {
            String carpetaRaiz = DBPtoVenta.getDirectorioRaiz();
            String nombrePlantillas = DBPtoVenta.getDirectorioPlantillas();
            fs = new FileInputStream(carpetaRaiz + "/plantillas/" + nombrePlantillas);
            wb = new HSSFWorkbook(fs, true);
            HSSFSheet hojaEmpleados = wb.getSheet("Reporte_Empleados");
            int index1 = wb.getSheetIndex("Reporte_Empleados");
            int index2 = -1;
            int countHojas = wb.getNumberOfSheets();
            String motivo = listaProductos.get(0).get(12).toString().trim();
            if(motivo.equals("01")){
                index2 = wb.getSheetIndex("Reporte_Faltantes");
                hojaProductosVendidos = wb.getSheet("Reporte_Faltantes");
            }else if(motivo.equals("02")){
                index2 = wb.getSheetIndex("Reporte_Expirados");
                hojaProductosVendidos = wb.getSheet("Reporte_Expirados");
            }else if(motivo.equals("03")){
                index2 = wb.getSheetIndex("Reporte_Deteriorados");
                hojaProductosVendidos = wb.getSheet("Reporte_Deteriorados");
            }
            for(int i = 0; i < countHojas; i++){
                if(i != index1 && i != index2){
                    wb.setSheetHidden(i, true);
                }else{
                    wb.setActiveSheet(i);
                }
            }
            /*CellStyle styleDataEmp = wb.createCellStyle();
            styleDataEmp.setBorderBottom(BorderStyle.THIN);
            styleDataEmp.setBorderTop(BorderStyle.THIN);
            styleDataEmp.setBorderLeft(BorderStyle.THIN);
            styleDataEmp.setBorderRight(BorderStyle.THIN);
            styleDataEmp.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            CellStyle styleNegritaEmp = wb.createCellStyle();
            org.apache.poi.ss.usermodel.Font hFontEmp = wb.createFont();
            hFontEmp.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            styleNegritaEmp.setFont(hFontEmp);*///Estilo de la fuente
            
            CellStyle styleDataEmp = wb.createCellStyle();
            styleDataEmp.setBorderBottom(BorderStyle.THIN);
            styleDataEmp.setBorderTop(BorderStyle.THIN);
            styleDataEmp.setBorderLeft(BorderStyle.THIN);
            styleDataEmp.setBorderRight(BorderStyle.THIN);
            styleDataEmp.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            org.apache.poi.ss.usermodel.Font hfontTextoTamanio = wb.createFont();
            hfontTextoTamanio.setFontName("Arial");
            CellStyle styleTipoTextoTamanio = wb.createCellStyle();
            styleTipoTextoTamanio.setFont(hfontTextoTamanio);
            CellStyle styleNegritaEmp = wb.createCellStyle();
            org.apache.poi.ss.usermodel.Font hfontEmp = wb.createFont();
            hfontEmp.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            hfontEmp.setFontName("Arial");
            styleNegritaEmp.setFont(hfontEmp);//Estilo de la fuente
            
            Row rowCabecera1 = hojaEmpleados.createRow((short) 4);
            Cell cellEmpresa = rowCabecera1.createCell(3);cellEmpresa.setCellValue("Empresa :");cellEmpresa.setCellStyle(styleNegritaEmp);
            Cell cellDescEmpresa = rowCabecera1.createCell(4);cellDescEmpresa.setCellValue(listaEmpleados.get(0).get(0).toString());cellDescEmpresa.setCellStyle(styleTipoTextoTamanio);
            Cell cellFechaEmision = rowCabecera1.createCell(6);cellFechaEmision.setCellValue("Fecha de Emision :");cellFechaEmision.setCellStyle(styleNegritaEmp);
            Cell cellDescFechaEmision = rowCabecera1.createCell(7);cellDescFechaEmision.setCellValue(listaEmpleados.get(0).get(4).toString());cellDescFechaEmision.setCellStyle(styleTipoTextoTamanio);
            Cell cellQuimicoLocal = rowCabecera1.createCell(9);cellQuimicoLocal.setCellValue("Quimico del Local :");cellQuimicoLocal.setCellStyle(styleNegritaEmp);
            Cell cellDescQuimicoLocal = rowCabecera1.createCell(10);cellDescQuimicoLocal.setCellValue(listaEmpleados.get(0).get(10).toString());cellDescQuimicoLocal.setCellStyle(styleTipoTextoTamanio);
            
            Row rowCabecera2 = hojaEmpleados.createRow((short) 5);
            Cell cellLocalEmp = rowCabecera2.createCell(3);cellLocalEmp.setCellValue("Local :");cellLocalEmp.setCellStyle(styleNegritaEmp);
            Cell cellDescLocalEmp = rowCabecera2.createCell(4);cellDescLocalEmp.setCellValue(listaEmpleados.get(0).get(9).toString());cellDescLocalEmp.setCellStyle(styleTipoTextoTamanio);
            Cell cellFacturacionEmp = rowCabecera2.createCell(6);cellFacturacionEmp.setCellValue("Nro. Facturación :");cellFacturacionEmp.setCellStyle(styleNegritaEmp);
            Cell cellDescFacturacionEmp = rowCabecera2.createCell(7);cellDescFacturacionEmp.setCellValue(listaEmpleados.get(0).get(11).toString());cellDescFacturacionEmp.setCellStyle(styleTipoTextoTamanio);
            Cell cellFechaEmp = rowCabecera2.createCell(9);cellFechaEmp.setCellValue("Fecha :");cellFechaEmp.setCellStyle(styleNegritaEmp);
            Date fecha = new Date();SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = formato.format(fecha);
            Cell cellDescFechaEmp = rowCabecera2.createCell(10);cellDescFechaEmp.setCellValue(fechaActual);cellDescFechaEmp.setCellStyle(styleTipoTextoTamanio);
            
            /*Row rowCabecera1 = hojaEmpleados.createRow((short) 4);
            Cell cellEmpresaEmp = rowCabecera1.createCell(3);cellEmpresaEmp.setCellValue("Empresa :");cellEmpresaEmp.setCellStyle(styleNegritaEmp);
            Cell cellDescEmpresa = rowCabecera1.createCell(4);cellDescEmpresa.setCellValue(listaEmpleados.get(0).get(0).toString());cellDescEmpresa.setCellStyle(styleNegritaEmp);
            Cell cellQuimicoLocal = rowCabecera1.createCell(6);cellQuimicoLocal.setCellValue("Quimico del Local :");cellQuimicoLocal.setCellStyle(styleNegritaEmp);
            Cell cellDescQuimicoLocal = rowCabecera1.createCell(7);cellDescQuimicoLocal.setCellValue(listaEmpleados.get(0).get(10).toString());cellDescQuimicoLocal.setCellStyle(styleNegritaEmp);
            
            Row rowCabecera2 = hojaEmpleados.createRow((short) 5);
            Cell cellLocalEmp = rowCabecera2.createCell(3);cellLocalEmp.setCellValue("Local :");cellLocalEmp.setCellStyle(styleNegritaEmp);
            Cell cellDescLocalEmp = rowCabecera2.createCell(4);cellDescLocalEmp.setCellValue(listaEmpleados.get(0).get(9).toString());cellDescLocalEmp.setCellStyle(styleNegritaEmp);
            Cell cellFechaEmp = rowCabecera2.createCell(6);cellFechaEmp.setCellValue("Fecha :");cellFechaEmp.setCellStyle(styleNegritaEmp);
            Date fecha = new Date();SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = formato.format(fecha);
            Cell cellDescFechaEmp = rowCabecera2.createCell(7);cellDescFechaEmp.setCellValue(fechaActual);cellDescFechaEmp.setCellStyle(styleNegritaEmp);
            Cell cellFacturacionEmp = rowCabecera2.createCell(10);cellFacturacionEmp.setCellValue("Nro. facturación :");cellFacturacionEmp.setCellStyle(styleNegritaEmp);
            Cell cellDescFacturacionEmp = rowCabecera2.createCell(11);cellDescFacturacionEmp.setCellValue(listaEmpleados.get(0).get(11).toString());*/
            
            int rowIndex=11;
            //filas viene a ser el size() y las columnas
            double totalPrecioVentaEmp = 0;
            double porcentaje = 0;
            for(int rows = 0; rows < listaEmpleados.size(); rows++){ //For each table row
                Row newRow = hojaEmpleados.createRow((short) rowIndex);
                newRow.setHeight((short) 800);
                
                Cell cell1Emp = newRow.createCell(2);cell1Emp.setCellValue(rows+1);cell1Emp.setCellStyle(styleDataEmp);
                Cell cell2 = newRow.createCell(3);cell2.setCellValue(listaEmpleados.get(rows).get(2).toString());cell2.setCellStyle(styleDataEmp);
                Cell cell3 = newRow.createCell(4);cell3.setCellValue(listaEmpleados.get(rows).get(3).toString());cell3.setCellStyle(styleDataEmp);
                Cell cell4 = newRow.createCell(5);cell4.setCellStyle(styleDataEmp);
                Cell cell5 = newRow.createCell(6);cell5.setCellValue(listaEmpleados.get(rows).get(6).toString());cell5.setCellStyle(styleDataEmp);
                Cell cell6 = newRow.createCell(7);cell6.setCellValue(listaEmpleados.get(rows).get(7).toString() + "%");cell6.setCellStyle(styleDataEmp);
                CellRangeAddress regionFirmaData = CellRangeAddress.valueOf("I" + (rowIndex + 1) + ":J" + (rowIndex + 1)); 
                hojaEmpleados.addMergedRegion(regionFirmaData);
                Cell cell7 = newRow.createCell(8);cell7.setCellStyle(styleDataEmp);
                Cell cell8 = newRow.createCell(9);cell8.setCellStyle(styleDataEmp);
                CellRangeAddress regionHuella = CellRangeAddress.valueOf("K" + (rowIndex + 1) + ":L" + (rowIndex + 1)); 
                hojaEmpleados.addMergedRegion(regionHuella);
                Cell cell9 = newRow.createCell(10);cell9.setCellStyle(styleDataEmp);
                Cell cell10 = newRow.createCell(11);cell10.setCellStyle(styleDataEmp);
                rowIndex++;
                totalPrecioVentaEmp = totalPrecioVentaEmp + Double.parseDouble(listaEmpleados.get(rows).get(6).toString().replace(",", ""));
                porcentaje = porcentaje + Double.parseDouble(listaEmpleados.get(rows).get(7).toString().replace(",", ""));
            }
            CellStyle styleTotalesEmp = wb.createCellStyle();
            styleTotalesEmp.setBorderBottom(BorderStyle.THIN);
            styleTotalesEmp.setBorderTop(BorderStyle.THIN);
            styleTotalesEmp.setBorderLeft(BorderStyle.THIN);
            styleTotalesEmp.setBorderRight(BorderStyle.THIN);
            styleTotalesEmp.setFont(hfontEmp);
            Row rowTotalesEmp = hojaEmpleados.createRow((short) rowIndex);
            Cell cell0Emp = rowTotalesEmp.createCell(6);cell0Emp.setCellValue(FarmaUtility.formatNumber(totalPrecioVentaEmp, 2));cell0Emp.setCellStyle(styleTotalesEmp);
            Cell cell1Emp = rowTotalesEmp.createCell(7);cell1Emp.setCellValue("100.00%");cell1Emp.setCellStyle(styleTotalesEmp);
            
            rowIndex = rowIndex + 4;
            Row rowMensaje1 = hojaEmpleados.createRow((short) rowIndex);
            rowMensaje1.setHeight((short)900);
            CellRangeAddress regionMensaje = CellRangeAddress.valueOf("C" + (rowIndex + 1) + ":L" + (rowIndex + 1)); 
            CellStyle styleMensaje = wb.createCellStyle();
            styleMensaje.setWrapText(true);
            org.apache.poi.ss.usermodel.Font hfontMensaje = wb.createFont();
            hfontMensaje.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            hfontMensaje.setFontHeight((short)150);
            styleMensaje.setFont(hfontMensaje);
            hojaEmpleados.addMergedRegion(regionMensaje);
            String mensaje = "Del mismo modo, los colaboradores firmantes  reconocen el incumplimiento de sus obligaciones laborales y que ello genera daños económicos a la Empresa, " +
                "por lo cual autorizan el descuento de los montos detallados, los mismos que son de su responsabilidad, de sus remuneraciones y de ser el caso de sus beneficios sociales " +
                "tales como, las Gratificaciones Legales, Compensación por Tiempo de Servicios y Utilidades  y/o de la liquidación de beneficios sociales, en caso de producirse la " +
                "extinción del vinculo laboral.";
            Cell cellMensaje1 = rowMensaje1.createCell(2);cellMensaje1.setCellValue(mensaje);cellMensaje1.setCellStyle(styleMensaje);
            
            rowIndex = rowIndex + 6;
            CellStyle styleFirma = wb.createCellStyle();
            styleFirma.setFont(hfontEmp);//Estilo de la fuente
            styleFirma.setBorderTop(BorderStyle.MEDIUM);
            styleFirma.setAlignment(styleFirma.ALIGN_CENTER);
            Row rowFirma = hojaEmpleados.createRow((short) rowIndex);
            Cell cellFirma1 = rowFirma.createCell(4);cellFirma1.setCellValue("Firma y Sello del Auditor");cellFirma1.setCellStyle(styleFirma);
            CellRangeAddress regionFirmaDoc = CellRangeAddress.valueOf("I" + (rowIndex + 1) + ":J" + (rowIndex + 1)); 
            hojaEmpleados.addMergedRegion(regionFirmaDoc); 
            Cell cellFirma2 = rowFirma.createCell(8);cellFirma2.setCellValue("Firma y Sello del Q.F.");cellFirma2.setCellStyle(styleFirma);
            Cell cellFirma3 = rowFirma.createCell(9);cellFirma3.setCellStyle(styleFirma);
            
            /************************************************************************************************************************************************************            
             ************************************************************************************************************************************************************            
             ************************************************************************************************************************************************************/
            
            /*CellStyle styleData = wb.createCellStyle();
            styleData.setBorderBottom(BorderStyle.THIN);
            styleData.setBorderTop(BorderStyle.THIN);
            styleData.setBorderLeft(BorderStyle.THIN);
            styleData.setBorderRight(BorderStyle.THIN);
            CellStyle styleNegrita = wb.createCellStyle();
            styleNegrita.setFont(hfontEmp);*///Estilo de la fuente
            
            Row rowCabecera3 = hojaProductosVendidos.createRow((short) 4);
            Cell cellEmpresaDet = rowCabecera3.createCell(3);cellEmpresaDet.setCellValue("Empresa :");cellEmpresaDet.setCellStyle(styleNegritaEmp);
            Cell cellDescEmpresaDet = rowCabecera3.createCell(4);cellDescEmpresaDet.setCellValue(descEmpresa);cellDescEmpresaDet.setCellStyle(styleTipoTextoTamanio);
            Cell cellFechaEmisionDet = rowCabecera3.createCell(6);cellFechaEmisionDet.setCellValue("Fecha de Emision :");cellFechaEmisionDet.setCellStyle(styleNegritaEmp);
            Cell cellDescFechaEmisonDet = rowCabecera3.createCell(7);cellDescFechaEmisonDet.setCellValue(fechaEmision);cellDescFechaEmisonDet.setCellStyle(styleTipoTextoTamanio);
            Cell cellQuimicoLocalDet = rowCabecera3.createCell(9);cellQuimicoLocalDet.setCellValue("Quimico del Local :");cellQuimicoLocalDet.setCellStyle(styleNegritaEmp);
            Cell cellDescQuimicoLocalDet = rowCabecera3.createCell(10);cellDescQuimicoLocalDet.setCellValue(listaProductos.get(0).get(14).toString());cellDescQuimicoLocalDet.setCellStyle(styleTipoTextoTamanio);
            
            Row rowCabecera4 = hojaProductosVendidos.createRow((short) 5);
            Cell cellLocalDet = rowCabecera4.createCell(3);cellLocalDet.setCellValue("Local :");cellLocalDet.setCellStyle(styleNegritaEmp);
            Cell cellDescLocalDet = rowCabecera4.createCell(4);cellDescLocalDet.setCellValue(descLocal);cellDescLocalDet.setCellStyle(styleTipoTextoTamanio);
            Cell cellFacturacionDet = rowCabecera4.createCell(6);cellFacturacionDet.setCellValue("Nro. Facturación :");cellFacturacionDet.setCellStyle(styleNegritaEmp);
            Cell cellDescFacturacionDet = rowCabecera4.createCell(7);cellDescFacturacionDet.setCellValue(listaProductos.get(0).get(15).toString());cellDescFacturacionDet.setCellStyle(styleTipoTextoTamanio);
            Cell cellFechaDet = rowCabecera4.createCell(9);cellFechaDet.setCellValue("Fecha :");cellFechaDet.setCellStyle(styleNegritaEmp);
            Cell cellDescFechaDet = rowCabecera4.createCell(10);cellDescFechaDet.setCellValue(fechaActual);cellDescFechaDet.setCellStyle(styleTipoTextoTamanio);
            
            
            /*Row rowLocal = hojaProductosVendidos.createRow((short) 4);
            Cell cellLocal = rowLocal.createCell(3);cellLocal.setCellValue("Local :");cellLocal.setCellStyle(styleNegrita);
            Cell cellDescLocal = rowLocal.createCell(4);cellDescLocal.setCellValue(descLocal);
            Cell cellFacturacion = rowLocal.createCell(6);cellFacturacion.setCellValue("Nro. facturación :");cellFacturacion.setCellStyle(styleNegrita);
            Cell cellDescFacturacion = rowLocal.createCell(7);cellDescFacturacion.setCellValue(listaProductos.get(0).get(14).toString());
            
            Row rowFecha = hojaProductosVendidos.createRow((short) 5);
            Cell cellFecha = rowFecha.createCell(3);cellFecha.setCellValue("Fecha :");cellFecha.setCellStyle(styleNegrita);
            Cell cellDescFecha = rowFecha.createCell(4);cellDescFecha.setCellValue(fechaActual);*/
            
            rowIndex=8;
            //filas viene a ser el size() y las columnas
            double totalPrecioVenta = 0;
            double precioTotal = 0;
            for(int rows = 0; rows < listaProductos.size(); rows++){ //For each table row
                Row newRow = hojaProductosVendidos.createRow((short) rowIndex);
                
                Cell cell1 = newRow.createCell(2);cell1.setCellValue(rows+1);cell1.setCellStyle(styleDataEmp);
                Cell cell2 = newRow.createCell(3);cell2.setCellValue(listaProductos.get(rows).get(1).toString());cell2.setCellStyle(styleDataEmp);
                Cell cell3 = newRow.createCell(4);cell3.setCellValue(listaProductos.get(rows).get(2).toString());cell3.setCellStyle(styleDataEmp);
                Cell cell4 = newRow.createCell(5);cell4.setCellValue(listaProductos.get(rows).get(3).toString());cell4.setCellStyle(styleDataEmp);
                Cell cell5 = newRow.createCell(6);cell5.setCellValue(listaProductos.get(rows).get(4).toString());cell5.setCellStyle(styleDataEmp);
                Cell cell6 = newRow.createCell(7);cell6.setCellValue(listaProductos.get(rows).get(5).toString());cell6.setCellStyle(styleDataEmp);
                Cell cell7 = newRow.createCell(8);cell7.setCellStyle(styleDataEmp);
                Cell cell8 = newRow.createCell(9);cell8.setCellValue(listaProductos.get(rows).get(7).toString());cell8.setCellStyle(styleDataEmp);
                Cell cell9 = newRow.createCell(10);cell9.setCellValue(listaProductos.get(rows).get(8).toString());cell9.setCellStyle(styleDataEmp);
                Cell cell10 = newRow.createCell(11);cell10.setCellValue(listaProductos.get(rows).get(9).toString());cell10.setCellStyle(styleDataEmp);
                Cell cell11 = newRow.createCell(12);cell11.setCellValue(listaProductos.get(rows).get(10).toString());cell11.setCellStyle(styleDataEmp);
                rowIndex++;
                totalPrecioVenta = totalPrecioVenta + Double.parseDouble(listaProductos.get(rows).get(9).toString().replace(",", ""));
                precioTotal = precioTotal + Double.parseDouble(listaProductos.get(rows).get(10).toString().replace(",", ""));
            }
            CellStyle styleTotales = wb.createCellStyle();
            styleTotales.setBorderBottom(BorderStyle.THIN);
            styleTotales.setBorderTop(BorderStyle.THIN);
            styleTotales.setBorderLeft(BorderStyle.THIN);
            styleTotales.setBorderRight(BorderStyle.THIN);
            styleTotales.setFont(hfontEmp);
            Row rowTotales = hojaProductosVendidos.createRow((short) rowIndex);
            Cell cell0 = rowTotales.createCell(11);cell0.setCellValue(FarmaUtility.formatNumber(totalPrecioVenta, 2));cell0.setCellStyle(styleTotales);
            Cell cell1 = rowTotales.createCell(12);cell1.setCellValue(FarmaUtility.formatNumber(precioTotal, 2));cell1.setCellStyle(styleTotales);
            
            FileNameExtensionFilter filterXls = new FileNameExtensionFilter("Documentos MS Excel 95/2003", "xls");
            File lfFile = new File("C:\\");
            final JFileChooser fc = new JFileChooser(lfFile);
            fc.setFileFilter(filterXls);
            int returnVal = fc.showSaveDialog(null);
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String ruta = fc.getSelectedFile().getAbsolutePath(); 
                ruta = rutaExtension(ruta);
                fileOut = new FileOutputStream(ruta); 
                wb.write(fileOut);
            }else{
                return false;
            }
            return true;
        } catch (IOException | SQLException e) {
            System.out.println("Error al crear archivo !!!" + e.getMessage());
        }finally{
            try{
                if(fileOut != null){
                    fileOut.close();
                }
                if(fs != null){
                    fs.close();
                }
                if(wb != null){
                    wb.close();
                }
            }catch(IOException ex){
                
            }
        }
        return false;
    }
    
    public static String rutaExtension(String ruta){
        if (ruta.lastIndexOf(".") != -1){
            return ruta + ".xls";
        }else{
            if(!ruta.substring(ruta.lastIndexOf(".") + 1).equalsIgnoreCase("xls")){
                return ruta + ".xls";
            }
        }
        return ruta;
    }
    
}
