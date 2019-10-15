package mifarma.ptoventa.tomainventario;


import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import java.awt.event.KeyEvent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.util.ArrayList;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaRowEditorModel;
import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.caja.reference.FarmaRecargaException;
import mifarma.ptoventa.cnx.FarmaVentaCnxUtility;

import mifarma.ptoventa.inventariodiario.DlgLaboratoriosPorTomaDiario;
import mifarma.ptoventa.tomainventario.reference.FarmaVariablesdos;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import printerUtil.FarmaUtil;

public class DlgFiltroEstadoItems extends JDialog {


    private static final Logger LOG = LoggerFactory.getLogger(DlgFiltroEstadoItems.class);

    public JComboBox cmbCampo = new JComboBox();
    public String vDirecOrden = "";
    public boolean varRadioPagina = false;
    public String varTipoPrecio = "";
    JPanel pnlSeleccionar = new JPanel();
    JLabel lblAceptarT = new JLabel();
    JLabel lblCancelarT = new JLabel();
    JButton btnColumna = new JButton();
    JPanel pnlFiltro = new JPanel();
    JRadioButton optTodos = new JRadioButton();
    JRadioButton optFiltrado = new JRadioButton();
    ButtonGroup groupOptions = new ButtonGroup();
    ButtonGroup groupPrecio = new ButtonGroup();
   public JTextField txtMayoresDiferencias = new JTextField();

    String[] IND_DESCRIP_CAMPOS;
    String[] IND_CAMPOS;


    /** Almacena todos los objetos y datos asociados a un ComboBox */
    private static Hashtable tableList = new Hashtable();

    private String filtro = "";

    private int cantRegistros = 140;
    private int cantPagIni = 1;
    private int cantPagFin = 1;
    public JTextField txtPagIni = new JTextField();
    public JTextField txtPagFin = new JTextField();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    public JRadioButton optPagina = new JRadioButton();
    public JPanel pnlFiltro1 = new JPanel();
    public JRadioButton rbnPreVenta = new JRadioButton();
    public JRadioButton rbnPreCosto = new JRadioButton();

    public DlgFiltroEstadoItems() {
        this(null, "", false);
    }

    public DlgFiltroEstadoItems(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
            initialize();
            FarmaVariablesdos.vMayorDiferencia = "";
            FarmaUtility.centrarVentana(this);
        } catch (Exception e) {
            LOG.error("Se produjo un error al iniciar ventana filtro estado", e);
        }

    }

    private void jbInit() throws Exception {

        this.setSize(new Dimension(274, 416));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        pnlSeleccionar.setBounds(new Rectangle(5, 10, 235, 65));
        pnlSeleccionar.setBorder(BorderFactory.createTitledBorder("Seleccione el estado "));
        pnlSeleccionar.setLayout(null);
        pnlSeleccionar.setFont(new Font("SansSerif", 0, 11));
        cmbCampo.setBounds(new Rectangle(85, 30, 135, 20));
        cmbCampo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    cmbCampo_keyPressed(e);
                }
        });
        cmbCampo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cmbCampo.getItemCount() == 0) {
                    return;
                }

                setSelected(e);
                txtMayoresDiferencias.setEnabled(false);

                //DFLORES: 29.01.2019 comentado para Impresion
                /*
                String vFiltro = EckerdLoadCVL.getCVLCode(VariablesReportes.vNombreInHashtable, cmbCampo.getSelectedIndex());

                if (vFiltro.equals("C") || vFiltro.equals("D")) {
                    optTodos.setEnabled(true);
                    optFiltrado.setEnabled(true);
                    optPagina.setEnabled(true);
                    optFiltrado.setSelected(true);
                    cmbCampo.requestFocus();
                } else {
                    optTodos.setEnabled(false);
                    optFiltrado.setEnabled(false);
                    optPagina.setEnabled(false);
                }*/
            }
        });
        cmbCampo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmbCampo_actionPerformed(e);
            }
        });


        lblAceptarT.setText("[ Enter ] Aceptar");
        lblAceptarT.setBounds(new Rectangle(25, 325, 90, 20));
        lblAceptarT.setFont(new Font("SansSerif", 1, 11));
        lblAceptarT.setForeground(new Color(32, 105, 29));
        lblCancelarT.setText("[ Esc ] Cancelar");
        lblCancelarT.setBounds(new Rectangle(125, 325, 85, 20));
        lblCancelarT.setFont(new Font("SansSerif", 1, 11));
        lblCancelarT.setForeground(new Color(32, 105, 29));
        btnColumna.setText("Ordenar por :");
        btnColumna.setBounds(new Rectangle(10, 30, 75, 20));
        btnColumna.setBorder(BorderFactory.createLineBorder(new Color(212, 208, 200), 1));
        btnColumna.setBorderPainted(false);
        btnColumna.setHorizontalAlignment(SwingConstants.LEFT);
        btnColumna.setMnemonic('O');
        btnColumna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnColumna_actionPerformed(e);
            }
        });
        pnlFiltro.setBounds(new Rectangle(5, 80, 235, 100));
        pnlFiltro.setLayout(null);
        pnlFiltro.setBorder(BorderFactory.createTitledBorder(""));
        optTodos.setText("Todos");
        optTodos.setBounds(new Rectangle(10, 15, 130, 20));
        optTodos.setSelected(true);
        optTodos.setMnemonic('T');
        optTodos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    cmbCampo_keyPressed(e);
                }
        });
        optFiltrado.setText("Mayores Diferencias : ");
        optFiltrado.setBounds(new Rectangle(10, 40, 150, 25));
        optFiltrado.setMnemonic('M');
        optFiltrado.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    cmbCampo_keyPressed(e);
                }
        });
        optFiltrado.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                txtMayoresDiferencias.setEnabled(optFiltrado.isSelected());

                if (txtMayoresDiferencias.isEnabled()) {
                    txtMayoresDiferencias.requestFocus();
                    txtMayoresDiferencias.selectAll();
                }
            }
        });
        txtMayoresDiferencias.setText("140");
        txtMayoresDiferencias.setBounds(new Rectangle(170, 40, 60, 20));
        txtMayoresDiferencias.setHorizontalAlignment(JTextField.RIGHT);
        txtMayoresDiferencias.setEnabled(false);
        txtMayoresDiferencias.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                txtMayoresDiferencias_keyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                    cmbCampo_keyPressed(e);
                }
        });
        txtPagIni.setBounds(new Rectangle(40, 290, 60, 25));
        txtPagIni.setEnabled(false);
        txtPagIni.setText("1");
        txtPagIni.setHorizontalAlignment(JTextField.RIGHT);
        txtPagIni.setFont(new Font("Tahoma", 1, 12));
        txtPagIni.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtPagIni_keyPressed(e);
                }

            public void keyTyped(KeyEvent e) {
                txtPagIni_keyTyped(e);
            }
        });
        txtPagFin.setBounds(new Rectangle(125, 290, 60, 25));
        txtPagFin.setEnabled(false);
        txtPagFin.setText("1");
        txtPagFin.setHorizontalAlignment(JTextField.RIGHT);
        txtPagFin.setFont(new Font("Tahoma", 1, 12));
        txtPagFin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    txtPagFin_keyPressed(e);
                }

            public void keyTyped(KeyEvent e) {
                txtPagFin_keyTyped(e);
            }
        });
        jLabel1.setText("Pag. Ini");
        jLabel1.setBounds(new Rectangle(50, 265, 55, 15));
        jLabel1.setFont(new Font("Tahoma", 1, 14));
        jLabel2.setText("Pag. Fin");
        jLabel2.setBounds(new Rectangle(130, 265, 60, 15));
        jLabel2.setFont(new Font("Tahoma", 1, 14));
        jLabel3.setText("-");
        jLabel3.setBounds(new Rectangle(110, 295, 34, 14));
        jLabel3.setFont(new Font("Tahoma", 1, 13));
        optPagina.setText("Paginado");
        optPagina.setBounds(new Rectangle(10, 70, 86, 18));
        optPagina.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                txtPagIni.setEnabled(optPagina.isSelected());
                txtPagFin.setEnabled(optPagina.isSelected());

                if (txtPagIni.isEnabled()) {
                    txtPagIni.requestFocus();
                    txtPagIni.selectAll();
                }
            }
        });

        optPagina.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbCampo_keyPressed(e);
                }
            });
        pnlFiltro1.setBounds(new Rectangle(5, 190, 235, 55));
        pnlFiltro1.setBorder(BorderFactory.createTitledBorder("Elegir Tipo Precio"));
        rbnPreVenta.setText("Precio Venta");


        rbnPreVenta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    rbnPreVenta_keyPressed(e);
                }
        });
        rbnPreVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    rbnPreVenta_actionPerformed(e);
                }
        });
        rbnPreCosto.setText("Precio Costo");
        rbnPreCosto.setEnabled(false);
        rbnPreCosto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    rbnPreCosto_keyPressed(e);
                }
        });
        rbnPreCosto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rbnPreCosto_actionPerformed(e);
            }
        });
        pnlSeleccionar.add(btnColumna, null);
        pnlSeleccionar.add(cmbCampo, null);
        pnlFiltro.add(optPagina, null);
        pnlFiltro.add(txtMayoresDiferencias, null);
        pnlFiltro.add(optFiltrado, null);
        pnlFiltro.add(optTodos, null);
        pnlFiltro.add(optPagina, null);
        pnlFiltro1.add(rbnPreVenta, null);
        pnlFiltro1.add(rbnPreCosto, null);
        this.getContentPane().add(pnlFiltro1, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(txtPagFin, null);
        this.getContentPane().add(txtPagIni, null);
        this.getContentPane().add(pnlFiltro, null);
        this.getContentPane().add(lblCancelarT, null);
        this.getContentPane().add(lblAceptarT, null);
        this.getContentPane().add(pnlSeleccionar, null);

        groupOptions.add(optTodos);
        groupOptions.add(optFiltrado);
        groupOptions.add(optPagina);

        groupPrecio.add(rbnPreVenta);
        groupPrecio.add(rbnPreCosto);


    }


    private void initialize() {

        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA) && FarmaVariablesdos.reporteDiferenciasValorizadas) {
            cmbCampo.removeAllItems();
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporProducto);
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporPrecio);
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporCantidad);    
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporLaboratorio);

            varTipoPrecio = "costo";
            rbnPreVenta.setEnabled(true);
            rbnPreCosto.setEnabled(true);
            rbnPreCosto.setSelected(true);
            txtMayoresDiferencias.setEnabled(false);
            optFiltrado.setEnabled(true);
            txtMayoresDiferencias.setText("");
        } else {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)&& FarmaVariablesdos.reporteDiferenciasValorizadas) {
                cmbCampo.removeAllItems();
                cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporProducto);
                cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporLaboratorio);

                optFiltrado.setEnabled(false);
                txtMayoresDiferencias.setEnabled(false);
                txtMayoresDiferencias.setText("");
                rbnPreVenta.setSelected(true);
                rbnPreVenta.setEnabled(false);
                rbnPreCosto.setEnabled(false);
                
                varTipoPrecio = "venta";
            }
        }
        if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_AUDITORIA)&& FarmaVariablesdos.reporteDiferencias) {
           
            cmbCampo.removeAllItems();
           
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporProducto);
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporPrecio);
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporCantidad);   
            cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporLaboratorio);
          
           
           

            varTipoPrecio = "costo";
            rbnPreVenta.setEnabled(true);
            rbnPreCosto.setEnabled(true);
            rbnPreCosto.setSelected(true);
            txtMayoresDiferencias.setEnabled(false);
            optFiltrado.setEnabled(true);
        } else  {
            if (FarmaVariables.dlgLogin.verificaRol(FarmaConstants.ROL_ADMLOCAL)  && FarmaVariablesdos.reporteDiferencias) {
                
                cmbCampo.removeAllItems();
                cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporLaboratorio);
                cmbCampo.addItem(FarmaVariablesdos.cmbOrdenarporProducto);

                optFiltrado.setEnabled(false);
                txtMayoresDiferencias.setEnabled(false);
                rbnPreVenta.setSelected(true);
                rbnPreVenta.setEnabled(false);
                rbnPreCosto.setEnabled(false);
                varTipoPrecio = "venta";
            }
        }
    }


    void this_windowOpened(WindowEvent e) {
        cmbCampo.requestFocus();
    }

    void cmbCampo_keyPressed(KeyEvent e) {
        // DFLORES: 30/01/2019
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            FarmaVariables.vAceptar = false;
            cancelaOperacion();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validaDatos()) {
                if(!txtMayoresDiferencias.getText().equalsIgnoreCase("")){
                    cantRegistros = Integer.parseInt(txtMayoresDiferencias.getText().trim());
                }
                cantPagIni = Integer.parseInt(txtPagIni.getText().trim());
                cantPagFin = Integer.parseInt(txtPagFin.getText().trim());

                if ((cantPagIni > cantPagFin) || cantPagIni <= 0 || cantPagFin <= 0) {
                    FarmaUtility.showMessage(this, "La Cantidad de Fin debe ser menor que Cantidad Inicio!", "");
                } else {

                    varRadioPagina = false;
                    filtro = getCVLCode(VariablesTomaInv.vNombreInHashtable, cmbCampo.getSelectedIndex());

                    if (optPagina.isSelected()) {
                        varRadioPagina = true;
                    }

                    FarmaVariables.vAceptar = true;
                    this.setVisible(false);

                    if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporLaboratorio)) {
                        FarmaVariablesdos.estadoCmb = "LAB";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporProducto)) {
                        FarmaVariablesdos.estadoCmb = "PRD";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporUnidades)) {
                        FarmaVariablesdos.estadoCmb = "UNI";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporCantidad)) {
                        FarmaVariablesdos.estadoCmb = "CNT";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio)) {
                        FarmaVariablesdos.estadoCmb = "R";
                    }
                }
            }
        }

    }

    public static String getCVLCode(String tableName, int index) {
        String code = new String("");
        if (tableList.containsKey(tableName)) {
            ArrayList list = (ArrayList)tableList.get(tableName);
            ArrayList data = (ArrayList)list.get(index);
            code = (String)data.get(0);
        }
        return code;
    }

    private boolean validaDatos() {
        if (!optFiltrado.isSelected()) {
            //return true;
            //DFLORES: 11/02/2018
            txtMayoresDiferencias.setText("");
            cantPagIni = Integer.parseInt(txtPagIni.getText().trim());
            cantPagFin = Integer.parseInt(txtPagFin.getText().trim());
            if (!(cantPagIni > cantPagFin)) {
                return true;
            } else {
                FarmaUtility.showMessage(this, "La Pagina de Inicio no debe ser Mayor a la Pagina Fin!!!",
                                         txtMayoresDiferencias);
                return false;
            }
            //Fin DFLORES
        }

        try {
            int cantidad = Integer.parseInt(txtMayoresDiferencias.getText().trim());
            String cantidadCampo = txtMayoresDiferencias.getText().trim();
            
            FarmaVariablesdos.vMayorDiferencia = cantidadCampo;

            if ((cantidad % 2) == 0) {
                return true;
            } else {
                FarmaUtility.showMessage(this, "La Cantidad de Registros a mostrar debe ser par. Verifique!!!",
                                         txtMayoresDiferencias);
                return false;
            }


        } catch (Exception e) {
            FarmaUtility.showMessage(this, "La Cantidad de Registros a mostrar no es válida. Verifique!!!",
                                     txtMayoresDiferencias);
            return false;
        }
    }

    void cancelaOperacion() {
        cmbCampo.removeAllItems();
        this.setVisible(false);
    }

    void btnColumna_actionPerformed(ActionEvent e) {
        cmbCampo.requestFocus();
    }

    public void setTitleBorder(String title) {
        pnlSeleccionar.setBorder(BorderFactory.createTitledBorder(title));
    }

    public void loadOptions(String[] descripcion, String[] codigos) {
        IND_CAMPOS = codigos;
        IND_DESCRIP_CAMPOS = descripcion;

        loadCVLfromArraysCustom(cmbCampo, VariablesTomaInv.vNombreInHashtable, IND_CAMPOS, IND_DESCRIP_CAMPOS, true,
                                true);


    }

    public static void loadCVLfromArraysCustom(JComboBox combo, String nameInHashTable, String[] fieldCode,
                                               String[] fieldValue, boolean isMandatory, boolean rewrite) {

        if (!tableList.containsKey(nameInHashTable) || rewrite)
            loadNewCVLfromArrays(combo, nameInHashTable, fieldCode, fieldValue, isMandatory);

        if (!tableList.containsKey(nameInHashTable)) {
            ArrayList arrayCVL = new ArrayList();
            ArrayList myArray;
            if (!isMandatory) {
                myArray = new ArrayList();
                myArray.add("");
                myArray.add("");
                arrayCVL.add(myArray);
            }
            for (int i = 0; i < fieldCode.length; i++) {
                myArray = new ArrayList();
                myArray.add(fieldCode[i]);
                myArray.add(fieldValue[i]);
                arrayCVL.add(myArray);
            }
            tableList.put(nameInHashTable, arrayCVL);
        }

        ArrayList list = (ArrayList)tableList.get(nameInHashTable);
        for (int i = 0; i < list.size(); i++) {
            ArrayList data = (ArrayList)list.get(i);
            combo.addItem(data.get(1));
        }
    }

    public static void loadNewCVLfromArrays(JComboBox combo, String nameInHashTable, String[] fieldCode,
                                            String[] fieldValue, boolean isMandatory) {

        ArrayList arrayCVL = new ArrayList();
        ArrayList myArray;
        if (!isMandatory) {
            myArray = new ArrayList();
            myArray.add("");
            myArray.add("");
            arrayCVL.add(myArray);
        }
        for (int i = 0; i < fieldCode.length; i++) {
            myArray = new ArrayList();
            myArray.add(fieldCode[i]);
            myArray.add(fieldValue[i]);
            arrayCVL.add(myArray);
        }
        tableList.put(nameInHashTable, arrayCVL);
    }


    private void txtMayoresDiferencias_keyTyped(KeyEvent e) {

        FarmaUtility.admitirDigitosDecimales(txtMayoresDiferencias, e);
    }

    public String getFiltro() {
        return filtro;
    }

    public int getCantRegistros() {
        return cantRegistros;
    }
    // DFLORES: 30/01/2019

    public int getCantPagIni() {
        return cantPagIni;
    }
    
    
    // DFLORES: 30/01/2019

    public int getCantPagFin() {
        return cantPagFin;
    }

    public boolean isFiltro() {
        return optFiltrado.isSelected();
    }
    // DFLORES: 30/01/2019

    public boolean getRadioPagina() {
        return varRadioPagina;
    }

    private void setSelected(ItemEvent e) {
    }

    public String getTipoPrecioImp() {
        return varTipoPrecio;
    }

    private void txtPagIni_keyPressed(KeyEvent e) {
        // DFLORES: 30/01/2019
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            FarmaVariables.vAceptar = false;
            cancelaOperacion();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validaDatos()) {
                if(!txtMayoresDiferencias.getText().equalsIgnoreCase("")){
                    cantRegistros = Integer.parseInt(txtMayoresDiferencias.getText().trim());
                }
                cantPagIni = Integer.parseInt(txtPagIni.getText().trim());
                cantPagFin = Integer.parseInt(txtPagFin.getText().trim());

                if ((cantPagIni > cantPagFin) || cantPagIni <= 0 || cantPagFin <= 0) {
                    FarmaUtility.showMessage(this, "La Cantidad de Fin debe ser menor que Cantidad Inicio!", "");
                } else {

                    varRadioPagina = false;
                    filtro = getCVLCode(VariablesTomaInv.vNombreInHashtable, cmbCampo.getSelectedIndex());

                    if (optPagina.isSelected()) {
                        varRadioPagina = true;
                        
                    }

                    FarmaVariables.vAceptar = true;
                    this.setVisible(false);
                    
                    if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporLaboratorio)) {
                        FarmaVariablesdos.estadoCmb = "LAB";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporProducto)) {
                        FarmaVariablesdos.estadoCmb = "PRD";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporUnidades)) {
                        FarmaVariablesdos.estadoCmb = "UNI";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporCantidad)) {
                        FarmaVariablesdos.estadoCmb = "CNT";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio)) {
                        FarmaVariablesdos.estadoCmb = "R";
                    }
                }
            }
        }

    }

    private void txtPagFin_keyPressed(KeyEvent e) {
        // DFLORES: 30/01/2019
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            FarmaVariables.vAceptar = false;
            cancelaOperacion();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validaDatos()) {
                if(!txtMayoresDiferencias.getText().equalsIgnoreCase("")){
                    cantRegistros = Integer.parseInt(txtMayoresDiferencias.getText().trim());
                }
                cantPagIni = Integer.parseInt(txtPagIni.getText().trim());
                cantPagFin = Integer.parseInt(txtPagFin.getText().trim());

                if ((cantPagIni > cantPagFin) || cantPagIni <= 0 || cantPagFin <= 0) {
                    FarmaUtility.showMessage(this, "La Cantidad de Fin debe ser menor que Cantidad Inicio!", "");
                } else {

                    varRadioPagina = false;
                    filtro = getCVLCode(VariablesTomaInv.vNombreInHashtable, cmbCampo.getSelectedIndex());

                    if (optPagina.isSelected()) {
                        varRadioPagina = true;
                    }

                    FarmaVariables.vAceptar = true;

                    this.setVisible(false);
                    
                    if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporLaboratorio)) {
                        FarmaVariablesdos.estadoCmb = "LAB";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporProducto)) {
                        FarmaVariablesdos.estadoCmb = "PRD";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporUnidades)) {
                        FarmaVariablesdos.estadoCmb = "UNI";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporCantidad)) {
                        FarmaVariablesdos.estadoCmb = "CNT";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio)) {
                        FarmaVariablesdos.estadoCmb = "R";
                    }
                }
            }
        }
    }

    private void txtPagIni_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtPagIni, e);

    }

    private void txtPagFin_keyTyped(KeyEvent e) {
        FarmaUtility.admitirDigitosDecimales(txtPagIni, e);
    }

    private void rbnPreVenta_keyPressed(KeyEvent e) {
        varTipoPrecio = "venta";

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            FarmaVariables.vAceptar = false;
            cancelaOperacion();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validaDatos()) {
                if(!txtMayoresDiferencias.getText().equalsIgnoreCase("")){
                    cantRegistros = Integer.parseInt(txtMayoresDiferencias.getText().trim());
                }
                cantPagIni = Integer.parseInt(txtPagIni.getText().trim());
                cantPagFin = Integer.parseInt(txtPagFin.getText().trim());

                if ((cantPagIni > cantPagFin) || cantPagIni <= 0 || cantPagFin <= 0) {
                    FarmaUtility.showMessage(this, "La Cantidad de Fin debe ser menor que Cantidad Inicio!", "");
                } else {

                    varRadioPagina = false;
                    filtro = getCVLCode(VariablesTomaInv.vNombreInHashtable, cmbCampo.getSelectedIndex());

                    if (optPagina.isSelected()) {
                        varRadioPagina = true;
                    }

                    FarmaVariables.vAceptar = true;
                    this.setVisible(false);
                    
                    
                    if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporLaboratorio)) {
                        FarmaVariablesdos.estadoCmb = "LAB";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporProducto)) {
                        FarmaVariablesdos.estadoCmb = "PRD";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporUnidades)) {
                        FarmaVariablesdos.estadoCmb = "UNI";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporCantidad)) {
                        FarmaVariablesdos.estadoCmb = "CNT";
                    } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio)) {
                        FarmaVariablesdos.estadoCmb = "VTA";
                    }
                }
                
                
                
                
                
                
            }
        }

    }

    private void rbnPreCosto_keyPressed(KeyEvent e) {
        varTipoPrecio = "costo";

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            FarmaVariables.vAceptar = false;
            cancelaOperacion();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            
           if (validaDatos()) {
               if(!txtMayoresDiferencias.getText().equalsIgnoreCase("")){
                   cantRegistros = Integer.parseInt(txtMayoresDiferencias.getText().trim());
               }
               cantPagIni = Integer.parseInt(txtPagIni.getText().trim());
               cantPagFin = Integer.parseInt(txtPagFin.getText().trim());

               if ((cantPagIni > cantPagFin) || cantPagIni <= 0 || cantPagFin <= 0) {
                   FarmaUtility.showMessage(this, "La Cantidad de Fin debe ser menor que Cantidad Inicio!", "");
               } else {

                   varRadioPagina = false;
                   filtro = getCVLCode(VariablesTomaInv.vNombreInHashtable, cmbCampo.getSelectedIndex());

                   if (optPagina.isSelected()) {
                       varRadioPagina = true;
                   }

                   FarmaVariables.vAceptar = true;
                   this.setVisible(false);

                   if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporLaboratorio)) {
                       FarmaVariablesdos.estadoCmb = "LAB";
                   } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporProducto)) {
                       FarmaVariablesdos.estadoCmb = "PRD";
                   } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporUnidades)) {
                       FarmaVariablesdos.estadoCmb = "UNI";
                   } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporCantidad)) {
                       FarmaVariablesdos.estadoCmb = "CNT";
                   } else if (cmbCampo.getSelectedItem().toString().equalsIgnoreCase(FarmaVariablesdos.cmbOrdenarporPrecio)) {
                       FarmaVariablesdos.estadoCmb = "CTO";
                   }
               }
           }


        }
    }

    private void rbnPreVenta_actionPerformed(ActionEvent e) {
        varTipoPrecio = "venta";

    }

    private void rbnPreCosto_actionPerformed(ActionEvent e) {
        varTipoPrecio = "costo";
    }

    private void cmbCampo_actionPerformed(ActionEvent e) {
    }
}
