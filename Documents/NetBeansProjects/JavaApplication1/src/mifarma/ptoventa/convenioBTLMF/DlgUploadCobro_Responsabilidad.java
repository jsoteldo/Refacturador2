package mifarma.ptoventa.convenioBTLMF;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelOrange;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelWhite;
import com.gs.mifarma.componentes.JTextFieldSanSerif;

import com.itextpdf.text.Document;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.nio.file.FileSystems;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import mifarma.common.DlgLogin;
import mifarma.common.FarmaConstants;
import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.convenioBTLMF.reference.ConstantsConv_Responsabilidad;
import mifarma.ptoventa.convenioBTLMF.reference.DBConv_Responsabilidad;
import mifarma.ptoventa.cotizarPrecios.DAO.BDCotizacionPrecio;
import mifarma.ptoventa.cotizarPrecios.modelo.VarCotizacionPrecio;
import mifarma.ptoventa.inventario.DlgListaCompetencias;
import mifarma.ptoventa.inventario.reference.ConstantsInventario;
import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.reference.ConstantsPtoVenta;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import org.apache.commons.io.FilenameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright (c) 2008 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : DlgUploadCobro_Responsabilidad.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * JMONZALVE       24.04.2019   Creacion <br>
 * <br>
 * @author  Jhony Monzalve V.<br>
 * @version 1.0<br>
 *
 */

public class DlgUploadCobro_Responsabilidad extends JDialog {
    /* ********************************************************************** */
    /*                        DECLARACION PROPIEDADES                         */
    /* ********************************************************************** */
    private static final Logger log = LoggerFactory.getLogger(DlgUploadCobro_Responsabilidad.class);

    Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jContentPane = new JPanel();

    private JLabelFunction lblF1 = new JLabelFunction();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JPanelWhite jPanelImagen = new JPanelWhite();
    private JButton btnDocumento = new JButton();
    private JTextFieldSanSerif txtRutaDocumento = new JTextFieldSanSerif();
    private JPanelHeader jPanel1 = new JPanelHeader();
    private JLabelWhite lblTitulo = new JLabelWhite();
    private JButton btnDocElectronico = new JButton();
    private JTextField txtSerie = new JTextField();
    private JTextField txtCorrelativo = new JTextField();
    private JLabel jLabel2 = new JLabel();
    private JLabelFunction lblF11 = new JLabelFunction();
    private List<HashMap<String, String>> listaDocumentos = null;
    private String documentoUnificado = "";

    /* ********************************************************************** */
    /*                        CONSTRUCTORES                                   */
    /* ********************************************************************** */

    public DlgUploadCobro_Responsabilidad() {
        this(null, "", false);
    }

    public DlgUploadCobro_Responsabilidad(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /* ************************************************************************ */
    /*                                  METODO jbInit                           */
    /* ************************************************************************ */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(581, 231));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Carga de Documentos - Cobro por Responsabilidad");
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jContentPane.setSize(new Dimension(405, 229));


        lblF1.setText("[ F1 ] Adjuntar Documento");
        lblF1.setBounds(new Rectangle(10, 165, 160, 20));
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(455, 165, 110, 20));


        txtRutaDocumento.setBounds(new Rectangle(155, 15, 385, 20));
        txtRutaDocumento.setLengthText(15);
        txtRutaDocumento.setEditable(false);

        jPanel1.setBounds(new Rectangle(10, 15, 555, 40));
        lblTitulo.setText("Carga de Documentos de Cobro por Responsabilidad");
        lblTitulo.setBounds(new Rectangle(100, 5, 380, 30));
        lblTitulo.setBackground(new Color(255, 130, 14));
        lblTitulo.setFont(new Font("SansSerif", 1, 14));
        btnDocElectronico.setText("Documento Electr\u00f3nico :");
        btnDocElectronico.setBounds(new Rectangle(10, 55, 140, 20));
        btnDocElectronico.setBackground(new Color(255, 130, 14));
        btnDocElectronico.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDocElectronico.setBorderPainted(false);
        btnDocElectronico.setContentAreaFilled(false);
        btnDocElectronico.setDefaultCapable(false);
        btnDocElectronico.setFocusPainted(false);
        btnDocElectronico.setFont(new Font("SansSerif", 1, 11));
        btnDocElectronico.setForeground(new Color(255, 130, 14));
        btnDocElectronico.setHorizontalAlignment(SwingConstants.LEFT);
        btnDocElectronico.setMnemonic('D');
        btnDocElectronico.setRequestFocusEnabled(false);
        btnDocElectronico.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnDocElectronico_actionPerformed(e);
                }
            });
        txtSerie.setBounds(new Rectangle(155, 55, 90, 20));
        txtSerie.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtSerie_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtSerie_keyTyped(e);
                }
            });
        txtCorrelativo.setBounds(new Rectangle(265, 55, 275, 20));
        txtCorrelativo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCorrelativo_keyPressed(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtCorrelativo_keyTyped(e);
                }
            });
        jLabel2.setText("-");
        jLabel2.setBounds(new Rectangle(250, 55, 15, 15));
        jLabel2.setFont(new Font("Tahoma", 0, 30));
        jLabel2.setForeground(new Color(255, 66, 66));
        lblF11.setText("[ F11 Grabar ]");
        lblF11.setBounds(new Rectangle(180, 165, 110, 20));
        btnDocumento.setText("Seleccionar Documento :");
        btnDocumento.setBounds(new Rectangle(10, 15, 140, 20));
        btnDocumento.setBackground(new Color(255, 130, 14));
        btnDocumento.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDocumento.setBorderPainted(false);
        btnDocumento.setContentAreaFilled(false);
        btnDocumento.setDefaultCapable(false);
        btnDocumento.setFocusPainted(false);
        btnDocumento.setFont(new Font("SansSerif", 1, 11));
        btnDocumento.setForeground(new Color(255, 130, 14));
        btnDocumento.setHorizontalAlignment(SwingConstants.LEFT);
        btnDocumento.setMnemonic('S');
        btnDocumento.setRequestFocusEnabled(false);
        btnDocumento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDocumento_actionPerformed(e);
            }
        });

        jPanelImagen.setBounds(new Rectangle(10, 55, 555, 100));
        jPanelImagen.setBackground(Color.white);
        jPanelImagen.setLayout(null);
        jPanelImagen.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        jPanelImagen.setVisible(true);


        jContentPane.add(lblF11, null);
        jPanel1.add(lblTitulo, null);
        jContentPane.add(jPanel1, null);


        jContentPane.add(lblF1, null);
        jContentPane.add(lblEsc, null);
        jContentPane.add(jPanelImagen, null);
        jPanelImagen.add(jLabel2, null);
        jPanelImagen.add(txtCorrelativo, null);
        jPanelImagen.add(txtSerie, null);
        jPanelImagen.add(btnDocElectronico, null);
        jPanelImagen.add(txtRutaDocumento, null);
        jPanelImagen.add(btnDocumento, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */
    private void initialize() {
        FarmaVariables.vAceptar = false;
    }

    /* ************************************************************************ */
    /*                            METODOS DE EVENTOS                            */
    /* ************************************************************************ */
    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(txtSerie);
    }
    
    private void btnDocumento_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtSerie);
    }

    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, "Debe presionar la tecla ESC para cerrar la ventana.", null);
    }

    /* ************************************************************************ */
    /*                     METODOS AUXILIARES DE EVENTOS                        */
    /* ************************************************************************ */
    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            cargarArchivo();
        } else if (e.getKeyCode() == KeyEvent.VK_F11){
            if (validaDatos()) {
                if(JConfirmDialog.rptaConfirmDialog(this, "Está seguro de adjuntar documento(s)?")){
                    DlgLogin dlgLogin = new DlgLogin(myParentFrame, ConstantsPtoVenta.MENSAJE_LOGIN, true);
                    dlgLogin.setRolUsuario(FarmaConstants.ROL_ADMLOCAL);
                    dlgLogin.setVisible(true);
                    if(FarmaVariables.vAceptar){
                        if(JConfirmDialog.rptaConfirmDialog(this, "Está seguro de grabar el/los documento(s) seleccionado?")){
                            try {
                                boolean flagReGuardado = false;
                                String correlativo = FarmaUtility.caracterIzquierda(txtCorrelativo.getText().trim(), 8, "0");
                                String numDocumento = txtSerie.getText().trim() + correlativo;
                                if(!DBConv_Responsabilidad.verificaCargaResponsabilidades(numDocumento)){
                                    if(JConfirmDialog.rptaConfirmDialog(this, "Ya se ha realizado la carga del documento para el mes presente, desea volver a cargar el documento?")){
                                        flagReGuardado = true;
                                    }else{
                                        flagReGuardado = false;
                                    }
                                }else{
                                    flagReGuardado = true;
                                }
                                if(flagReGuardado){
                                    if(generarDocumentoPDF()){
                                        boolean flag = copiarArchivoRutaFTP(numDocumento);
                                        if(flag){
                                            if(DBConv_Responsabilidad.actualizaCargaResponsabilidades(numDocumento)){
                                                FarmaUtility.aceptarTransaccion();
                                                FarmaUtility.showMessage(this, "Documento cargado exitosamente !!!", txtSerie);
                                                txtRutaDocumento.setText("");
                                                txtSerie.setText("");
                                                txtCorrelativo.setText("");
                                                FarmaUtility.moveFocus(txtSerie);
                                                eliminarArchivos();
                                            }else{
                                                FarmaUtility.liberarTransaccion();
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException f) {
                                FarmaUtility.liberarTransaccion();
                                FarmaUtility.showMessage(this, "Ocurrió un error al cargar el documento !!!", txtSerie);
                            }
                        }
                    }
                }
            }
        }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }
    
    private void eliminarArchivos(){
        HashMap<String, String> documento = new HashMap<>();
        String separador = System.getProperty("file.separator");
        File archivo = null;
        for(int i = 0; i < listaDocumentos.size(); i++){
            documento = listaDocumentos.get(i);
            archivo = new File(System.getProperty("java.io.tmpdir") + separador + documento.get("nombreDocumento") + "." + ConstantsConv_Responsabilidad.extension);
            if(archivo.exists()){
                archivo.delete();
            }
        }
        archivo = new File(System.getProperty("java.io.tmpdir") + separador + "Documento_Unificado" + "." + ConstantsConv_Responsabilidad.extension);
        if(archivo.exists()){
            archivo.delete();
        }
        archivo = null;
        documento = null;
    }
    
    private boolean validaDatos(){
        if(txtRutaDocumento.getText().trim().length() <= 0){
            FarmaUtility.showMessage(this, "Debe de seleccionar documento(s) a cargar !!!", txtSerie);
            return false;
        }else if(txtSerie.getText().trim().length() <= 0){
            FarmaUtility.showMessage(this, "Debe de ingresar la serie del documento electrónico !!!", txtSerie);
            return false;
        }else if(txtCorrelativo.getText().trim().length() <= 0){
            FarmaUtility.showMessage(this, "Debe de ingresar el correlativo del documento electrónico !!!", txtCorrelativo);
            return false;
        }else if(txtSerie.getText().trim().length() < 4){
            FarmaUtility.showMessage(this, "La serie del documento debe de ser de 4 caracteres !!!", txtSerie);
            return false;
        }
        return true;
    }
    
    private boolean generarDocumentoPDF(){
        Document document = null;
        FileOutputStream fos = null;
        PdfWriter writer = null;
        try {
            for(int i = 0; i < listaDocumentos.size();i++){
                document = new Document();
                HashMap<String, String> mapaDocumento = listaDocumentos.get(i);
                if(!mapaDocumento.get("extensionDocumento").equalsIgnoreCase(ConstantsConv_Responsabilidad.extension)){
                    //fos = new FileOutputStream(mapaDocumento.get("rutaDocumento") + System.getProperty("file.separator") + mapaDocumento.get("nombreDocumento") + "." + ConstantsConv_Responsabilidad.extension);
                    fos = new FileOutputStream(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + mapaDocumento.get("nombreDocumento") + "." + ConstantsConv_Responsabilidad.extension);
                    writer = PdfWriter.getInstance(document, fos);
                    writer.open();
                    document.open();
                    document.add(Image.getInstance(mapaDocumento.get("rutaDocumento") + System.getProperty("file.separator") + mapaDocumento.get("nombreDocumento") + "." + mapaDocumento.get("extensionDocumento")));
                    document.close();
                    writer.close();
                    fos.flush();
                    fos.close();
                }
            }
            document = null;
            fos = null;
            writer = null;
            return unirDocumentosPDF();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error de carga de documento(s): " + e.getMessage());
            FarmaUtility.showMessage(this, "Error al cargar documento(s) seleccionado(s) !!!", txtSerie);
            return false;
        }
    }
    
    private boolean unirDocumentosPDF() throws Exception {
        List<InputStream> inputPdfList = new ArrayList<InputStream>();
        String separador = System.getProperty("file.separator");
        String rutaDoc = "";
        String nombreDoc = "";
        for(int i = 0; i < listaDocumentos.size(); i++){
            HashMap<String, String> documento = listaDocumentos.get(i);
            rutaDoc = documento.get("rutaDocumento");
            nombreDoc = documento.get("nombreDocumento");
            if(!documento.get("extensionDocumento").equalsIgnoreCase(ConstantsConv_Responsabilidad.extension)){
                inputPdfList.add(new FileInputStream(System.getProperty("java.io.tmpdir") + separador + nombreDoc + "." + ConstantsConv_Responsabilidad.extension));
            }else{
                inputPdfList.add(new FileInputStream(rutaDoc + separador + nombreDoc + "." + ConstantsConv_Responsabilidad.extension));
            }
        }
        OutputStream outputStream = new FileOutputStream(System.getProperty("java.io.tmpdir") + separador + "Documento_Unificado" + "." + ConstantsConv_Responsabilidad.extension);
        mergeArchivosPDF(inputPdfList, outputStream);
        outputStream = null;
        inputPdfList = null;
        documentoUnificado = System.getProperty("java.io.tmpdir") + separador + "Documento_Unificado" + "." + ConstantsConv_Responsabilidad.extension;
        return true;
    }
    
    private void mergeArchivosPDF(List<InputStream> inputPdfList, OutputStream outputStream) throws Exception{
        Document document = new Document();
        List<PdfReader> readers = new ArrayList<PdfReader>();
        int totalPages = 0;

        Iterator<InputStream> pdfIterator = inputPdfList.iterator();
        InputStream pdf = null;
        PdfReader pdfReader = null;
        while (pdfIterator.hasNext()) {
            pdf = pdfIterator.next();
            pdfReader = new PdfReader(pdf);
            readers.add(pdfReader);
            totalPages = totalPages + pdfReader.getNumberOfPages();
        }
        pdf = null;
        pdfReader = null;

        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte pageContentByte = writer.getDirectContent();

        PdfImportedPage pdfImportedPage;
        int currentPdfReaderPage = 1;
        Iterator<PdfReader> iteratorPDFReader = readers.iterator();

        while (iteratorPDFReader.hasNext()) {
                pdfReader = iteratorPDFReader.next();
                while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
                      document.newPage();
                      pdfImportedPage = writer.getImportedPage(pdfReader,currentPdfReaderPage);
                      pageContentByte.addTemplate(pdfImportedPage, 0, 0);
                      currentPdfReaderPage++;
                }
                currentPdfReaderPage = 1;
        }
        iteratorPDFReader = null;
        pdfImportedPage = null;
        pdfIterator = null;
        inputPdfList = null;
        outputStream.flush();
        document.close();
        writer.close();
        outputStream.close();
        outputStream = null;
        document = null;
        writer = null;
        readers = null;
    }
    
    private boolean copiarArchivoRutaFTP(String numDocumento){
        try {
            String separador = System.getProperty("file.separator");
            String rutaDestino = DBConv_Responsabilidad.obtenerRutaDestino(numDocumento);
            if(rutaDestino.equalsIgnoreCase("X")){
                FarmaUtility.showMessage(this,"El comprobante electronico ingresado no es correcto !!!",txtSerie);
                return false;
            }else if(rutaDestino.equalsIgnoreCase("N")){
                FarmaUtility.showMessage(this,"Ocurrió un error al grabar documento(s) !!!",txtSerie);
                return false;
            }else{
                String[] cadena = rutaDestino.split("@");
                File carpetaDestino = new File(cadena[0]);
                if(!carpetaDestino.exists()){
                    carpetaDestino.mkdir();
                }
                carpetaDestino = null;
                Path origenPath = FileSystems.getDefault().getPath(documentoUnificado);
                Path destinoPath = FileSystems.getDefault().getPath(cadena[0] + separador + cadena[1]);
                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                origenPath = null;
                destinoPath = null;
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al cargar documento: " + e.getMessage());
            return false;
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    /* ************************************************************************ */
    /*                     METODOS DE LOGICA DE NEGOCIO                         */
    /* ************************************************************************ */

    private void cargarArchivo() {
        File lfFile = new File("C:\\");
        JFileChooser filechooser = new JFileChooser(lfFile);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF, JPG, JPEG, PNG", "pdf", "jpg", "jpeg", "png");
        filechooser.setFileFilter(filter);
        filechooser.setDialogTitle("Seleccione documento(s) PDF, JPG, JPEG, PNG");
        filechooser.setAcceptAllFileFilterUsed(false);//desactiva todos los archivos
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filechooser.setMultiSelectionEnabled(true);
        
        if (filechooser.showSaveDialog(this.myParentFrame) != JFileChooser.APPROVE_OPTION){
            return;
        }
        File[] fileChoosen = filechooser.getSelectedFiles();
        if(fileChoosen != null && fileChoosen.length > 0){
            listaDocumentos = new ArrayList<>();
            String rutaDocumento = "";
            String nombreDocumento = "";
            String extensionDocumento = "";
            File archivo = null;
            for (int i=0; i< fileChoosen.length; i++) {
                archivo = fileChoosen[i];
                HashMap<String, String> documento = new HashMap<String, String>();
                rutaDocumento = archivo.getParent();                
                extensionDocumento = FilenameUtils.getExtension(archivo.getName());
                /*if(nombreDocumento.length() > 0){
                    nombreDocumento = nombreDocumento + " - " + archivo.getName();
                }else{
                    nombreDocumento = FilenameUtils.getBaseName(archivo.getName());
                }*/
                
                if(nombreDocumento.length() > 0){
                    nombreDocumento = nombreDocumento + " ; " + FilenameUtils.getBaseName(archivo.getName()) + "." + extensionDocumento;
                }else{
                    nombreDocumento = FilenameUtils.getBaseName(archivo.getName()) + "." + extensionDocumento;
                }
                
                documento.put("rutaDocumento", rutaDocumento);
                documento.put("nombreDocumento", FilenameUtils.getBaseName(archivo.getName()));
                documento.put("extensionDocumento", extensionDocumento);
                listaDocumentos.add(documento);
            }
            txtRutaDocumento.setText(nombreDocumento);
            archivo = null;
            fileChoosen = null;
        }else{
            FarmaUtility.showMessage(this,"Seleccionar documentos válidos",txtSerie);
        }
    }

    private void txtSerie_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtCorrelativo);
        }else{
            chkKeyPressed(e);
        }
    }

    private void txtCorrelativo_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            FarmaUtility.moveFocus(txtSerie);
        }else{
            chkKeyPressed(e);
        }
    }

    private void btnDocElectronico_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtSerie);
    }

    private void txtCorrelativo_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitos(txtCorrelativo, e);
        String digitos = txtCorrelativo.getText(); 
        if(digitos.length() >= 8){ 
            e.consume(); 
        } 
    }

    private void txtSerie_keyTyped(KeyEvent e) {
        String digitos = txtSerie.getText(); 
        if(digitos.length() >= 4){ 
            e.consume(); 
        } 
    }
}
