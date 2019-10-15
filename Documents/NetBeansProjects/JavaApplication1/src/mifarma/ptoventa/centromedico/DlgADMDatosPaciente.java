package mifarma.ptoventa.centromedico;

import com.gs.mifarma.componentes.ComponentTitledBorder;
import com.gs.mifarma.componentes.ExpressionValidate;
import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import com.gs.mifarma.componentes.JTextFieldValidate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.SystemColor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.Border;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.campAcumulada.reference.DBCampAcumulada;
import mifarma.ptoventa.centromedico.domain.BeanPaciente;
import mifarma.ptoventa.centromedico.reference.ConstantsCentroMedico;
import mifarma.ptoventa.centromedico.reference.UtilityAdmisionMedica;
import mifarma.ptoventa.centromedico.reference.UtilityAtencionMedica;
import mifarma.ptoventa.centromedico.reference.VariablesCentroMedico;
import mifarma.ptoventa.cliente.DlgRegistroCliente;
import mifarma.ptoventa.delivery.reference.DBDelivery;
import mifarma.ptoventa.delivery.reference.VariablesDelivery;
import mifarma.ptoventa.reference.UtilityPtoVenta;

import oracle.jdeveloper.layout.OverlayLayout2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DlgADMDatosPaciente extends  JDialog{


    /* ************************************************************************ */
    /*                          DECLARACION PROPIEDADES                         */
    /* ************************************************************************ */    
    
    private static final Logger log = LoggerFactory.getLogger(DlgADMDatosPaciente.class);
    //private static final long serialVersionUID = -2626325502788986022L;
    

    private JPanelWhite jContentPane = new JPanelWhite();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanelHeader jPanelHeader1 = new JPanelHeader();
    
    private JButtonLabel lblDatosPaciente = new JButtonLabel();
    private JButtonLabel lblHistoria = new JButtonLabel();
    private JButtonLabel lblNumHisCli = new JButtonLabel();
    private JButtonLabel lblNombre = new JButtonLabel();    
    private JButtonLabel lblTipDoc = new JButtonLabel();
    private JComboBox cmbTipDoc = new JComboBox();
    private JButtonLabel lblNumDoc = new JButtonLabel();
    private JButtonLabel lblHCAnterior = new JButtonLabel();    
    private JButtonLabel lblFecHCAnterior = new JButtonLabel();    
    private JButtonLabel lblAcompanante = new JButtonLabel();
    private JComboBox cmbTipAcompanante = new JComboBox();    
    private JButtonLabel lblTipDocAcom = new JButtonLabel();
    private JComboBox cmbTipDocAcom = new JComboBox();
    private JButtonLabel lblNumDocAcom = new JButtonLabel();
    private JButtonLabel lblTipSexo = new JButtonLabel();
    private JComboBox cmbTipSexo = new JComboBox();
    private JButtonLabel lblTipEstCivil= new JButtonLabel();
    private JComboBox cmbTipEstCivil = new JComboBox();
    private JButtonLabel lblFecNacimiento = new JButtonLabel();
    private JButtonLabel lblTipGradoIns= new JButtonLabel();
    private JComboBox cmbTipGradoIns = new JComboBox();
    private JButtonLabel lblOcupacion = new JButtonLabel();
    private JButtonLabel lblCentroEL = new JButtonLabel();
    private JButtonLabel lblTipGrupoSan = new JButtonLabel();
    private JComboBox cmbTipGrupoSan = new JComboBox();
    private JButtonLabel lblTipFactorRH= new JButtonLabel();
    private JComboBox cmbTipFactorRH = new JComboBox();
    private JButtonLabel lblCorreo = new JButtonLabel();
    private JButtonLabel lblTelFijo = new JButtonLabel();       
    private JButtonLabel lblTelCel = new JButtonLabel();
    private JLabel txtEdad = new JLabel();
    
    //private JTextFieldValidate txtNroComprobante = new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,true);
    private JTextFieldValidate txtApePaterno = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,true);
    private JTextFieldValidate txtApeMaterno = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,true);
    private JTextFieldValidate txtNombre = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,true);
    private JTextFieldValidate txtNumDoc = new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,true);
    
    private JTextFieldSanSerif txtFecHCAnterior = new JTextFieldSanSerif();
    private JTextFieldValidate txtNomAcompanante = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,false);
    private JTextFieldValidate txtNumdocAcom = new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,false);    
    private JTextFieldSanSerif txtFecNacimiento = new JTextFieldSanSerif();    
    private JTextFieldValidate txtOcupacion = new JTextFieldValidate(ExpressionValidate.LETRA_CON_ESPACIOS,false);    
    private JTextFieldValidate txtCorreo = new JTextFieldValidate(ExpressionValidate.EMAIL,false); 
    private JTextFieldValidate txtTelFijo= new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,false);
    private JTextFieldValidate txtTelCel = new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,false);
    
    private JTextFieldValidate txtHCAnterior = new JTextFieldValidate(ExpressionValidate.SOLO_NUMERO,false);
    private JTextFieldValidate txtDireccion = new JTextFieldValidate(ExpressionValidate.ALFANUMERICO,true);        
    private JTextFieldValidate txtCentroEL = new JTextFieldValidate(ExpressionValidate.ALFANUMERICO,false);    
    
    private BeanPaciente paciente;
    private String tipoPaciente;

    private Frame MyParentFrame;
    private JLabelFunction btnF11 = new JLabelFunction();
    private JLabelFunction btnEsc = new JLabelFunction();

    private TipoAtencionCM tipoAtencion;
    private BeanPaciente beanPaciente;
    private boolean flagDNIPac=true;
    private boolean flagTipPac=true;
    private boolean flagTipAcom=true;
    
    private boolean flagMayorEdad=true;
    private boolean flagDNIAcom=true;
    private JPanel jPanel1 = new JPanel();
    private JButtonLabel lblApeMat = new JButtonLabel();
    private JButtonLabel lblANombre = new JButtonLabel();
    
    private JLabel lblDptoUbigeo = new JLabel();
    private JLabel lblProviUbigeo = new JLabel();
    private JLabel lblDistUbigeo = new JLabel();
    private JComboBox cmbDptoUbigeo = new JComboBox();
    private JComboBox cmbProviUbigeo = new JComboBox();
    private JComboBox cmbDistUbigeo = new JComboBox(); 

    private JLabel lblDireccion = new JLabel();

    private JLabel lblDptoLugNac = new JLabel();
    private JLabel lblProviLugNac = new JLabel();
    private JLabel lblDistLugNac = new JLabel();
    
    private JComboBox cmbDptoLugNac = new JComboBox();
    private JComboBox cmbProviLugNac = new JComboBox();
    private JComboBox cmbDistLugNac = new JComboBox();
    
    private JLabel lblDptoLugPro = new JLabel();
    private JLabel lblProviLugPro = new JLabel();
    private JLabel lblDistLugPro = new JLabel();
    
    private JComboBox cmbDptoLugPro = new JComboBox();
    private JComboBox cmbProviLugPro = new JComboBox();
    private JComboBox cmbDistLugPro = new JComboBox();
    

    

    JCheckBox chbHClinica = new JCheckBox("Historia Clinica Anterior", true); 
    JCheckBox chbDatoMedico = new JCheckBox("Datos médicos", true); 
    JCheckBox chbLugarNacimiento = new JCheckBox("Lugar de Nacimiento", true); 
    JCheckBox chbProcedencia = new JCheckBox("Lugar de Procedencia", true); 
    JCheckBox chbDireccionActual = new JCheckBox("Dirección Actual", true); 
    JCheckBox chbDatosContacto = new JCheckBox("Datos de Contacto", true); 
    JCheckBox chbAcompanante = new JCheckBox("Datos de Acompañante", true); 
    JCheckBox chbDatoAdicional = new JCheckBox("Datos Adicionales", true);
    
    private JPanel pnlHCAntigua = new JPanel();
    private JPanel pnlDatoMedico = new JPanel();
    private JPanel pnlLugarNacimiento = new JPanel();
    private JPanel pnlProcedencia = new JPanel();
    private JPanel pnlDireccionActual = new JPanel();
    private JPanel pnlDatosContacto = new JPanel();
    private JPanel pnlAcompanante = new JPanel();
    
    private JPanel pnlDatoAdicional = new JPanel();
    
    private String auxNumDocPac="";
    
    private String auxItemValue="";
    /* ************************************************************************ */
    /*                             CONSTRUCTORES                                */
    /* ************************************************************************ */
    public DlgADMDatosPaciente() {
        super();
        try {
            jbInit();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgADMDatosPaciente(Frame parent, String title, boolean modal, String tipoPaciente, BeanPaciente paciente, TipoAtencionCM tipoAtencion) {
        super(parent, title, modal);


        try {
            MyParentFrame = parent;
            this.tipoPaciente=tipoPaciente;
            this.paciente=paciente;
            this.tipoAtencion = tipoAtencion;
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /* ************************************************************************ */
    /*                              METODO jbInit                               */
    /* ************************************************************************ */

    private void jbInit() throws Exception {
        this.setSize(new Dimension(1019, 630));
        this.setTitle("Ingrese Datos del Paciente");
        this.setDefaultCloseOperation(0);
        this.getContentPane().setLayout(null);
        this.addWindowListener(new WindowAdapter(){
              public void windowOpened(WindowEvent e){
                this_windowOpened(e);
              }

              public void windowClosing(WindowEvent e){
                this_windowClosing(e);
              }
            });
        jContentPane.setFocusable(false);
        jContentPane.setBounds(new Rectangle(0, 0, 1020, 625));
        jPanelHeader1.setBounds(new Rectangle(15, 20, 990, 30));

        pnlAcompanante.setBounds(new Rectangle(5, 400, 975, 65));
        pnlAcompanante.setBackground(Color.white);
        //pnlAcompanante.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        pnlAcompanante.setFocusable(false);

        pnlAcompanante.setLayout(null);
        
        pnlDatoAdicional.setBounds(new Rectangle(5, 330, 975, 65));
        pnlDatoAdicional.setBackground(Color.white);
        pnlDatoAdicional.setFocusable(false);

        pnlDatoAdicional.setLayout(null);



        
        chbHClinica.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbHClinica_keyPressed(e);
            }
        });
        chbDatoMedico.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbDatoMedico_keyPressed(e);
            }
        });
        chbLugarNacimiento.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbLugarNacimiento_keyPressed(e);
            }
        });
         chbProcedencia.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbProcedencia_keyPressed(e);
            }
        });
         chbDireccionActual.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbDireccionActual_keyPressed(e);
            }
        });
         chbDatosContacto.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbDatosContacto_keyPressed(e);
            }
        });
         chbAcompanante.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbAcompanante_keyPressed(e);
            }
        });
        chbDatoAdicional.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chbDatoAdicional_keyPressed(e);
            }
        });
        
        
        
        lblDatosPaciente.setText("Datos de Identificación del Paciente");
        lblDatosPaciente.setBounds(new Rectangle(10, 10, 230, 15));
        
        lblHistoria.setText("N° Historia Clinica");
        lblHistoria.setBackground(Color.white);
        lblHistoria.setFocusable(false);
        lblHistoria.setForeground(new Color(255, 90, 33));
        lblHistoria.setBounds(new Rectangle(795, 5, 100, 15));
        
        
        lblNumHisCli.setText("");
        lblNumHisCli.setBackground(Color.white);
        lblNumHisCli.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNumHisCli.setFocusable(false);
        lblNumHisCli.setForeground(new Color(255, 90, 33));
        lblNumHisCli.setBounds(new Rectangle(895, 5, 90, 15));
        
        
        /*** fila 1 ***/
        lblNombre.setText("Apellido Paterno");
        lblNombre.setBounds(new Rectangle(20, 40, 135, 15));
        lblNombre.setBackground(Color.white);
        lblNombre.setFocusable(false);
        lblNombre.setForeground(Color.red);
        lblNombre.setActionCommand("Apellido Paterno (*)");
        lblNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNombre_actionPerformed(e);
            }
        });

        txtApePaterno.setName("Apellido Paterno Paciente");
        txtApePaterno.setBounds(new Rectangle(20, 60, 175, 20));
        txtApePaterno.setLengthText(25);
        txtApePaterno.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtApePaterno_keyPressed(e);
            }
            
        });


        pnlAcompanante.add(lblAcompanante, null);
        pnlAcompanante.add(cmbTipAcompanante, null);
        pnlAcompanante.add(txtNomAcompanante, null);
        pnlAcompanante.add(lblTipDocAcom, null);
        pnlAcompanante.add(cmbTipDocAcom, null);
        pnlAcompanante.add(lblNumDocAcom, null);
        pnlAcompanante.add(txtNumdocAcom, null);
        txtApeMaterno.setName("Apellido Materno Paciente");
        txtApeMaterno.setBounds(new Rectangle(220, 60, 195, 20));
        txtApeMaterno.setLengthText(25);
        txtApeMaterno.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtApeMaterno_keyPressed(e);
                }

            });

        txtNombre.setName("Nombre Paciente");
        txtNombre.setBounds(new Rectangle(440, 60, 220, 20));
        txtNombre.setLengthText(25);
        txtNombre.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtNombre_keyPressed(e);
                }

            });

        lblTipDoc.setText("Tipo de Doc:  *");
        lblTipDoc.setBounds(new Rectangle(10, 10, 85, 20));
        lblTipDoc.setBackground(Color.white);
        lblTipDoc.setFocusable(false);
        lblTipDoc.setForeground(Color.red);
        lblTipDoc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipDoc.setActionCommand("Tipo de Doc:  ");
        lblTipDoc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTipDoc_actionPerformed(e);
                }
            });

        cmbTipDoc.setName("Documento Paciente");
        cmbTipDoc.setBounds(new Rectangle(110, 10, 120, 20));
        cmbTipDoc.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipDoc_keyPressed(e);

                }
            });
        cmbTipDoc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cmbTipDoc_actionPerformed() /*e*/;
                }
            });


        lblNumDoc.setText("Número de Doc:  *");
        lblNumDoc.setBounds(new Rectangle(265, 10, 105, 20));
        lblNumDoc.setFocusable(false);
        lblNumDoc.setBackground(Color.white);
        lblNumDoc.setForeground(Color.red);
        lblNumDoc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNumDoc.setActionCommand("N\u00famero de Doc:  ");
        lblNumDoc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblNumDoc_actionPerformed(e);
                }
            });

        txtNumDoc.setName("Número Documento Paciente");
        txtNumDoc.setBounds(new Rectangle(380, 10, 175, 20));
        txtNumDoc.setLengthText(12);
        txtNumDoc.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtNumDoc_keyPressed(e);
                }

            });


        /*** fila 2 ***/
        lblTipSexo.setText("Sexo:");
        lblTipSexo.setBounds(new Rectangle(20, 90, 40, 20));
        lblTipSexo.setBackground(Color.white);
        lblTipSexo.setFocusable(false);
        lblTipSexo.setForeground(Color.red);
        lblTipSexo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipSexo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTipSexo_actionPerformed(e);
                }
            });

        cmbTipSexo.setName("Sexo");
        cmbTipSexo.setBounds(new Rectangle(65, 90, 120, 20));
        cmbTipSexo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //cmbTipSexo_actionPerformed(e);

                }
            });
        cmbTipSexo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipSexo_keyPressed(e);

                }
            });

        lblTipEstCivil.setText("Estado Civil:  ");
        lblTipEstCivil.setHorizontalAlignment(JLabel.RIGHT); //Agregado
        lblTipEstCivil.setBounds(new Rectangle(440, 95, 85, 20));
        lblTipEstCivil.setBackground(Color.white);
        lblTipEstCivil.setFocusable(false);
        lblTipEstCivil.setForeground(new Color(255, 90, 33));
        lblTipEstCivil.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTipEstCivil_actionPerformed(e);
                }
            });

        cmbTipEstCivil.setName("Estado Civil");
        cmbTipEstCivil.setBounds(new Rectangle(530, 95, 130, 20));
        cmbTipEstCivil.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //cmbTipEstCivil_actionPerformed(e);

                }
            });
        cmbTipEstCivil.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipEstCivil_keyPressed(e);

                }
            });


        txtEdad.setName("Edad");
        txtEdad.setBounds(new Rectangle(300, 115, 115, 15));

        txtEdad.setHorizontalAlignment(JLabel.RIGHT);
        //txtEdad.setLengthText(3);
        txtEdad.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        txtEdad.setFont(new Font("Tahoma", 0, 10));
        txtEdad.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtEdad_keyPressed(e);
                }
            });

        lblFecNacimiento.setText("<html><center>Fecha de<br>Nacimiento (*)</center></html>");
        lblFecNacimiento.setBounds(new Rectangle(220, 90, 80, 25));
        lblFecNacimiento.setBackground(Color.white);
        lblFecNacimiento.setFocusable(false);
        lblFecNacimiento.setForeground(Color.red);
        lblFecNacimiento.setActionCommand("<html><center>Fecha de<br>Nacimiento (*)</center></html>");
        lblFecNacimiento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblFecNacimiento_actionPerformed(e);
                }
            });

        txtFecNacimiento.setName("Fecha Nacimiento");
        txtFecNacimiento.setBounds(new Rectangle(330, 90, 85, 20));
        txtFecNacimiento.setLengthText(10);
        txtFecNacimiento.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtFecNacimiento_keyPressed(e);
                }

                public void keyReleased(KeyEvent e) {
                    txtFecNacimiento_keyReleased(e);
                }

                public void keyTyped(KeyEvent e) {
                    txtFecNacimiento_keyTyped(e);
                }
            });
        txtFecNacimiento.addFocusListener(new FocusListener() {
                public void focusLost(FocusEvent e) {
                    //setEdad(txtFecNacimiento.getText().trim());
                }

                public void focusGained(FocusEvent e) {
                }
            });


        /*** fila 3 ***/


        /*** fila 4 ***/
        lblHCAnterior.setText("H.C Anterior:  ");
        lblHCAnterior.setBounds(new Rectangle(10, 20, 90, 25));
        lblHCAnterior.setBackground(Color.white);
        lblHCAnterior.setFocusable(false);
        lblHCAnterior.setForeground(new Color(255, 90, 33));
        lblHCAnterior.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHCAnterior.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblHCAnterior_actionPerformed(e);
                }
            });

        txtHCAnterior.setName("Historia Clinica Anterior");
        txtHCAnterior.setBounds(new Rectangle(105, 25, 155, 20));
        txtHCAnterior.setLengthText(20);
        txtHCAnterior.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtHCAnterior_keyPressed(e);
                }
            });

        lblFecHCAnterior.setText("<html><center>Fecha de<br> Registro H.C:  </center></html>");
        lblFecHCAnterior.setHorizontalAlignment(JLabel.RIGHT); //Agregado
        lblFecHCAnterior.setBounds(new Rectangle(20, 50, 80, 35));
        lblFecHCAnterior.setBackground(Color.white);
        lblFecHCAnterior.setFocusable(false);
        lblFecHCAnterior.setForeground(new Color(255, 90, 33));
        lblFecHCAnterior.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblFecHCAnterior_actionPerformed(e);
                }
            });

        txtFecHCAnterior.setName("Fecha H. Clinica Anterior");
        txtFecHCAnterior.setBounds(new Rectangle(105, 60, 155, 20));
        txtFecHCAnterior.setLengthText(10);
        txtFecHCAnterior.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtFecHCAnterior_keyPressed(e);

                }

                public void keyReleased(KeyEvent e) {
                    txtFecHCAnterior_keyReleased(e);
                }
                public void keyTyped(KeyEvent e) {
                    txtFecHCAnterior_keyTyped(e);
                }
            });


        /*** fila 5 ***/
        lblAcompanante.setText("Acompa\u00f1ante:");
        lblAcompanante.setBounds(new Rectangle(40, 30, 80, 15));
        lblAcompanante.setBackground(Color.white);
        lblAcompanante.setFocusable(false);
        lblAcompanante.setForeground(new Color(255, 90, 33));
        lblAcompanante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblAcompanante_actionPerformed(e);
                }
            });

        cmbTipAcompanante.setName("Tipo Acompañante");
        cmbTipAcompanante.setBounds(new Rectangle(130, 30, 100, 20));
        cmbTipAcompanante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cmbTipAcompanante_actionPerformed() /*e*/;

                }
            });
        cmbTipAcompanante.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipAcompanante_keyPressed(e);

                }
            });


        txtNomAcompanante.setName("Nombre Acompañante");
        txtNomAcompanante.setBounds(new Rectangle(240, 30, 230, 20));
        txtNomAcompanante.setLengthText(50);
        txtNomAcompanante.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtNomAcompanante_keyPressed(e);
                }
            });

        lblTipDocAcom.setText("<html><center>Tipo<br>Documento:</center></html>");
        lblTipDocAcom.setBounds(new Rectangle(480, 20, 65, 30));
        lblTipDocAcom.setBackground(Color.white);
        lblTipDocAcom.setFocusable(false);
        lblTipDocAcom.setForeground(new Color(255, 90, 33));
        lblTipDocAcom.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTipDocAcom_actionPerformed(e);
                }
            });

        cmbTipDocAcom.setName("Tipo Documento Acompañante");
        cmbTipDocAcom.setBounds(new Rectangle(560, 25, 135, 20));
        cmbTipDocAcom.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipDocAcom_keyPressed(e);


                }
            });
        cmbTipDocAcom.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cmbTipDocAcom_actionPerformed() /*e*/;
                }
            });

        lblNumDocAcom.setText("<html><center>N\u00famero<br>Documento:</center></html>");
        lblNumDocAcom.setBounds(new Rectangle(720, 20, 65, 30));
        lblNumDocAcom.setBackground(Color.white);
        lblNumDocAcom.setFocusable(false);
        lblNumDocAcom.setForeground(new Color(255, 90, 33));
        lblNumDocAcom.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblNumDocAcom_actionPerformed(e);
                }
            });

        txtNumdocAcom.setName("Número Documento Acompañante");
        txtNumdocAcom.setBounds(new Rectangle(815, 25, 115, 20));
        txtNumdocAcom.setLengthText(10);
        txtNumdocAcom.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtNumdocAcom_keyPressed(e);

                }

            });


        /*** fila 6 ***/


        //lblLugProcedencia.setHorizontalAlignment(JLabel.RIGHT); //Agregado


        /*** fila 7 ***/


        //lblDireccion.setHorizontalAlignment(JLabel.RIGHT); //Agregado


        txtDireccion.setName("Dirección");
        //txtDireccion.setToolTipText("REGION/DISTRITO/PROVINCIA");
        txtDireccion.setBounds(new Rectangle(95, 55, 535, 20));
        txtDireccion.setLengthText(100);
        txtDireccion.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtDireccion_keyPressed(e);
                }

            });


        /*** fila 8 ***/
        lblTipGradoIns.setText("<html><center>Grado de<br>Instrucci\u00f3n:</center></html>");
        lblTipGradoIns.setBounds(new Rectangle(20, 20, 100, 45));
        lblTipGradoIns.setBackground(Color.white);
        lblTipGradoIns.setFocusable(false);
        lblTipGradoIns.setForeground(new Color(255, 90, 33));
        lblTipGradoIns.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTipGradoIns_actionPerformed(e);
                }
            });

        cmbTipGradoIns.setName("Grado de Instrucción");
        cmbTipGradoIns.setBounds(new Rectangle(130, 30, 270, 20));
        cmbTipGradoIns.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //cmbTipGradoIns_actionPerformed(e);

                }
            });
        cmbTipGradoIns.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipGradoIns_keyPressed(e);

                }
            });

        lblOcupacion.setText("Ocupación:  ");
        lblOcupacion.setBounds(new Rectangle(410, 30, 70, 20));
        lblOcupacion.setBackground(Color.white);
        lblOcupacion.setFocusable(false);
        lblOcupacion.setForeground(new Color(255, 90, 33));
        lblOcupacion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblOcupacion_actionPerformed(e);
                }
            });

        txtOcupacion.setName("Ocupación");
        txtOcupacion.setBounds(new Rectangle(485, 30, 165, 20));
        txtOcupacion.setLengthText(100);
        txtOcupacion.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtOcupacion_keyPressed(e);
                }

            });

        lblCentroEL.setText("C.Educativo/Laboral:  ");
        lblCentroEL.setBounds(new Rectangle(655, 30, 120, 20));
        lblCentroEL.setBackground(Color.white);
        lblCentroEL.setFocusable(false);
        lblCentroEL.setForeground(new Color(255, 90, 33));
        lblCentroEL.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblCentroEL_actionPerformed(e);
                }
            });

        txtCentroEL.setName("Centro Laboral/Educativo");
        txtCentroEL.setBounds(new Rectangle(780, 30, 150, 20));
        txtCentroEL.setLengthText(50);
        txtCentroEL.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCentroEL_keyPressed(e);
                }
            });


        /*** fila 9 ***/
        lblTipGrupoSan.setText("<html><center>Grupo<br>Sangu\u00edneo:</center></html>");
        lblTipGrupoSan.setBounds(new Rectangle(10, 20, 90, 30));
        lblTipGrupoSan.setBackground(Color.white);
        lblTipGrupoSan.setFocusable(false);
        lblTipGrupoSan.setForeground(new Color(255, 90, 33));
        lblTipGrupoSan.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipGrupoSan.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTipGrupoSan_actionPerformed(e);
                }
            });

        cmbTipGrupoSan.setName("Grupo Sanguíneo");
        cmbTipGrupoSan.setBounds(new Rectangle(110, 25, 150, 20));
        cmbTipGrupoSan.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //cmbTipGrupoSan_actionPerformed(e);

                }
            });
        cmbTipGrupoSan.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipGrupoSan_keyPressed(e);

                }
            });

        lblTipFactorRH.setText("Factor RH:  ");
        lblTipFactorRH.setHorizontalAlignment(JLabel.RIGHT); //Agregado
        lblTipFactorRH.setBounds(new Rectangle(10, 60, 90, 25));
        lblTipFactorRH.setBackground(Color.white);
        lblTipFactorRH.setFocusable(false);
        lblTipFactorRH.setForeground(new Color(255, 90, 33));
        lblTipFactorRH.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTipFactorRH_actionPerformed(e);
                }
            });

        cmbTipFactorRH.setName("Factor RH");
        cmbTipFactorRH.setBounds(new Rectangle(110, 60, 150, 20));
        cmbTipFactorRH.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //cmbTipFactorRH_actionPerformed(e);

                }
            });
        cmbTipFactorRH.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbTipFactorRH_keyPressed(e);

                }
            });

        /*** fila 10 ***/
        lblCorreo.setText("Correo:  ");
        lblCorreo.setBounds(new Rectangle(10, 75, 50, 25));
        lblCorreo.setBackground(Color.white);
        lblCorreo.setFocusable(false);
        lblCorreo.setForeground(new Color(255, 90, 33));
        lblCorreo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblCorreo_actionPerformed(e);
                }
            });

        txtCorreo.setName("Correo");
        txtCorreo.setBounds(new Rectangle(60, 80, 225, 20));
        txtCorreo.setLengthText(60);
        txtCorreo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtCorreo_keyPressed(e);
                }
            });

        lblTelFijo.setText("<html><center>Telef.<br>fijo:</center></html>");
        lblTelFijo.setBounds(new Rectangle(10, 50, 40, 25));
        lblTelFijo.setBackground(Color.white);
        lblTelFijo.setFocusable(false);
        lblTelFijo.setForeground(new Color(255, 90, 33));
        lblTelFijo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTelFijo_actionPerformed(e);
                }
            });

        txtTelFijo.setName("Teléfono Fijo");
        txtTelFijo.setBounds(new Rectangle(60, 50, 165, 20));
        txtTelFijo.setLengthText(10);
        txtTelFijo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtTelFijo_keyPressed(e);
                }
            });

        lblTelCel.setText("Celular:  ");
        lblTelCel.setBounds(new Rectangle(10, 20, 50, 20));
        lblTelCel.setBackground(Color.white);
        lblTelCel.setFocusable(false);
        lblTelCel.setForeground(new Color(255, 90, 33));
        lblTelCel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lblTelCel_actionPerformed(e);
                }
            });

        txtTelCel.setName("Teléfono Celular");
        txtTelCel.setBounds(new Rectangle(60, 25, 165, 20));
        txtTelCel.setLengthText(9);
        txtTelCel.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    txtTelCel_keyPressed(e);
                }
            });

        btnF11.setBounds(new Rectangle(720, 565, 117, 19));
        btnF11.setText("[F11] Aceptar");
        btnF11.setFocusable(false);
        btnEsc.setBounds(new Rectangle(870, 565, 117, 19));
        btnEsc.setText("[ESC] Cerrar");
        btnEsc.setFocusable(false);

        jPanel1.setBounds(new Rectangle(15, 55, 985, 500));
        jPanel1.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 14), 1));
        jPanel1.setLayout(null);
        jPanel1.setBackground(SystemColor.window);
        lblApeMat.setText("Apellido Materno");
        lblApeMat.setBounds(new Rectangle(220, 40, 110, 15));
        lblApeMat.setBackground(Color.white);
        lblApeMat.setForeground(Color.red);
        lblApeMat.setActionCommand("Apellido Materno (*)");
        lblANombre.setText("Nombres");
        lblANombre.setBackground(Color.white);
        lblANombre.setForeground(Color.red);
        lblANombre.setBounds(new Rectangle(440, 40, 125, 15));
        lblANombre.setActionCommand("Nombres (*)");
        pnlDireccionActual.setBounds(new Rectangle(15, 130, 651, 90));
        pnlDireccionActual.setLayout(null);
        //pnlDireccionActual.setBorder(BorderFactory.createTitledBorder("Dirección Actual"));
        lblDptoUbigeo.setText("Departamento (*) ");
        lblDptoUbigeo.setBounds(new Rectangle(10, 25, 85, 20));
        lblDptoUbigeo.setForeground(Color.red);
        lblDptoUbigeo.setFont(new Font("SansSerif", 1, 10));
        lblProviUbigeo.setText("Provincia (*)");
        lblProviUbigeo.setBounds(new Rectangle(235, 25, 70, 20));
        lblProviUbigeo.setForeground(Color.red);
        lblProviUbigeo.setFont(new Font("SansSerif", 1, 10));
        lblDistUbigeo.setText("Distrito (*)");
        lblDistUbigeo.setBounds(new Rectangle(430, 25, 65, 20));
        lblDistUbigeo.setForeground(Color.red);
        lblDistUbigeo.setFont(new Font("SansSerif", 1, 10));
        cmbDptoUbigeo.setName("cmbDptoUbigeo");
        cmbDptoUbigeo.setBounds(new Rectangle(95, 25, 135, 20));
        cmbDptoUbigeo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cmbDptoUbigeo_itemStateChanged(e,cmbDptoUbigeo,cmbProviUbigeo);
            }
        });
        cmbDptoUbigeo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbDptoUbigeo_keyPressed(e);

                }
            });
        cmbProviUbigeo.setName("cmbProviUbigeo");
        cmbProviUbigeo.setBounds(new Rectangle(295, 25, 135, 20));
        cmbProviUbigeo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cmbProviUbigeo_itemStateChanged(e,cmbDptoUbigeo,cmbProviUbigeo,cmbDistUbigeo);
            }
        });
        cmbProviUbigeo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbProviUbigeo_keyPressed(e);

                }
            });
        cmbDistUbigeo.setName("cmbDistUbigeo");
        cmbDistUbigeo.setBounds(new Rectangle(480, 25, 150, 20));
        cmbDistUbigeo.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbDistUbigeo_keyPressed(e);

                }
            });
        lblDireccion.setText("Direcci\u00f3n (*)");
        lblDireccion.setBounds(new Rectangle(30, 55, 60, 20));
        lblDireccion.setForeground(Color.red);
        lblDireccion.setFont(new Font("SansSerif", 1, 10));
        pnlHCAntigua.setBounds(new Rectangle(675, 10, 290, 100));
        pnlHCAntigua.setLayout(null);
        //pnlHCAntigua.setBorder(BorderFactory.createTitledBorder("Historia Clinica "));
        
        
        cargaBorderCheck();
                

        cmbDptoLugNac.setName("cmbDptoLugNac");
        cmbDptoLugNac.setBounds(new Rectangle(95, 20, 135, 20));
        cmbDptoLugNac.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cmbDptoLugNac_itemStateChanged(e,cmbDptoLugNac,cmbProviLugNac);
            }
        });
        cmbDptoLugNac.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbDptoLugNac_keyPressed(e);

                }
            });
        cmbProviLugNac.setName("cmbProviLugNac");
        cmbProviLugNac.setBounds(new Rectangle(295, 20, 135, 20));
        cmbProviLugNac.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cmbProviLugNac_itemStateChanged(e,cmbDptoLugNac,cmbProviLugNac,cmbDistLugNac);
            }
        });
        cmbProviLugNac.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbProviLugNac_keyPressed(e);

                }
            });
        cmbDistLugNac.setName("cmbDistLugNac");
        cmbDistLugNac.setBounds(new Rectangle(495, 20, 135, 20));
        cmbDistLugNac.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbDistLugNac_keyPressed(e);

                }
            });
        cmbDptoLugPro.setName("cmbDptoLugPro");
        cmbDptoLugPro.setBounds(new Rectangle(95, 20, 135, 20));
        cmbDptoLugPro.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cmbDptoLugPro_itemStateChanged(e,cmbDptoLugPro,cmbProviLugPro);
            }
        });
        cmbDptoLugPro.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbDptoLugPro_keyPressed(e);

                }
            });
        cmbProviLugPro.setName("cmbProviLugPro");
        cmbProviLugPro.setBounds(new Rectangle(295, 20, 135, 20));
        cmbProviLugPro.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cmbProviLugPro_itemStateChanged(e,cmbDptoLugPro,cmbProviLugPro,cmbDistLugPro);
            }
        });
        cmbProviLugPro.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbProviLugPro_keyPressed(e);

                }
            });
        cmbDistLugPro.setName("cmbDistLugPro");
        cmbDistLugPro.setBounds(new Rectangle(495, 20, 135, 20));
        cmbDistLugPro.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    cmbDistLugPro_keyPressed(e);

                }
            });
        
        lblDptoLugNac.setText("Departamento:");
        lblDptoLugNac.setBounds(new Rectangle(10, 25, 75, 15));
        lblDptoLugNac.setForeground(new Color(231, 115, 0));
        lblDptoLugNac.setFont(new Font("SansSerif", 1, 10));
        lblProviLugNac.setText("Provincia:");
        lblProviLugNac.setBounds(new Rectangle(245, 20, 50, 20));
        lblProviLugNac.setForeground(new Color(231, 115, 0));
        lblProviLugNac.setFont(new Font("SansSerif", 1, 10));
        lblDistLugNac.setText("Distrito:");
        lblDistLugNac.setBounds(new Rectangle(445, 20, 45, 20));
        lblDistLugNac.setForeground(new Color(231, 115, 0));
        lblDistLugNac.setFont(new Font("SansSerif", 1, 10));
        lblDptoLugPro.setText("Departamento:");
        lblDptoLugPro.setBounds(new Rectangle(10, 20, 75, 20));
        lblDptoLugPro.setForeground(new Color(231, 115, 0));
        lblDptoLugPro.setFont(new Font("SansSerif", 1, 10));
        lblProviLugPro.setText("Provincia:");
        lblProviLugPro.setBounds(new Rectangle(240, 20, 50, 20));
        lblProviLugPro.setForeground(new Color(231, 115, 0));
        lblProviLugPro.setFont(new Font("SansSerif", 1, 10));
        lblDistLugPro.setText("Distrito:");
        lblDistLugPro.setBounds(new Rectangle(445, 20, 40, 15));
        lblDistLugPro.setForeground(new Color(231, 115, 0));
        lblDistLugPro.setFont(new Font("SansSerif", 1, 10));
        pnlDatoMedico.setBounds(new Rectangle(675, 115, 295, 100));
        pnlDatoMedico.setLayout(null);
        //pnlDatoMedico.setBorder(BorderFactory.createTitledBorder("Dato Médico"));
        pnlLugarNacimiento.setBounds(new Rectangle(15, 225, 650, 50));
        pnlLugarNacimiento.setLayout(null);
        //pnlLugarNacimiento.setBorder(BorderFactory.createTitledBorder("Lugar Nacimiento"));
        pnlProcedencia.setBounds(new Rectangle(15, 275, 650, 55));
        //pnlProcedencia.setBorder(BorderFactory.createTitledBorder("Procedencia"));
        pnlProcedencia.setLayout(null);
        pnlDatosContacto.setBounds(new Rectangle(675, 215, 295, 115));
        pnlDatosContacto.setLayout(null);
        //jPanel7.setBorder(BorderFactory.createTitledBorder("Datos para Contacto"));
        pnlDireccionActual.add(lblDireccion, null);
        pnlDireccionActual.add(cmbDistUbigeo, null);
        pnlDireccionActual.add(cmbProviUbigeo, null);
        pnlDireccionActual.add(cmbDptoUbigeo, null);
        pnlDireccionActual.add(lblDistUbigeo, null);
        pnlDireccionActual.add(lblProviUbigeo, null);
        pnlDireccionActual.add(lblDptoUbigeo, null);
        pnlDireccionActual.add(txtDireccion, null);
        pnlHCAntigua.add(txtFecHCAnterior, null);
        pnlHCAntigua.add(lblFecHCAnterior, null);
        pnlHCAntigua.add(txtHCAnterior, null);
        pnlHCAntigua.add(lblHCAnterior, null);
        pnlDatoMedico.add(cmbTipGrupoSan, null);
        pnlDatoMedico.add(cmbTipFactorRH, null);
        pnlDatoMedico.add(lblTipFactorRH, null);
        pnlDatoMedico.add(lblTipGrupoSan, null);
        pnlLugarNacimiento.add(lblDptoLugNac, null);
        pnlLugarNacimiento.add(cmbDptoLugNac, null);
        pnlLugarNacimiento.add(lblProviLugNac, null);
        pnlLugarNacimiento.add(cmbProviLugNac, null);
        pnlLugarNacimiento.add(lblDistLugNac, null);
        pnlLugarNacimiento.add(cmbDistLugNac, null);
        pnlDatosContacto.add(txtTelCel, null);
        pnlDatosContacto.add(lblTelCel, null);
        pnlDatosContacto.add(txtTelFijo, null);
        pnlDatosContacto.add(lblTelFijo, null);
        pnlDatosContacto.add(txtCorreo, null);
        pnlDatosContacto.add(lblCorreo, null);
        jPanel1.add(pnlDatosContacto, null);
        jPanel1.add(pnlLugarNacimiento, null);
        jPanel1.add(pnlDatoMedico, null);
        jPanel1.add(pnlHCAntigua, null);
        jPanel1.add(pnlDireccionActual, null);
        jPanel1.add(lblANombre, null);
        jPanel1.add(lblApeMat, null);
        jPanel1.add(txtFecNacimiento, null);
        jPanel1.add(lblFecNacimiento, null);
        jPanel1.add(txtEdad, null);
        jPanel1.add(cmbTipEstCivil, null);
        jPanel1.add(lblTipEstCivil, null);
        jPanel1.add(cmbTipSexo, null);
        jPanel1.add(lblTipSexo, null);
        jPanel1.add(txtNumDoc, null);
        jPanel1.add(lblNumDoc, null);
        jPanel1.add(cmbTipDoc, null);
        jPanel1.add(lblTipDoc, null);
        jPanel1.add(txtNombre, null);
        jPanel1.add(txtApeMaterno, null);
        jPanel1.add(txtApePaterno, null);
        jPanel1.add(lblNombre, null);


        /*jPanel1.add(lblTipGradoIns, null);
        jPanel1.add(txtCentroEL, null);
        jPanel1.add(lblCentroEL, null);
        jPanel1.add(txtOcupacion, null);
        jPanel1.add(lblOcupacion, null);
        jPanel1.add(cmbTipGradoIns, null);*/
        jPanel1.add(pnlDatoAdicional, null);
        pnlDatoAdicional.add(lblTipGradoIns, null);
        pnlDatoAdicional.add(cmbTipGradoIns, null);
        pnlDatoAdicional.add(lblCentroEL, null);
        pnlDatoAdicional.add(txtCentroEL, null);
        pnlDatoAdicional.add(lblOcupacion, null);
        pnlDatoAdicional.add(txtOcupacion, null);
        jPanel1.add(pnlProcedencia, null);
        jPanel1.add(pnlAcompanante, null);
        pnlProcedencia.add(lblDptoLugPro, null);
        pnlProcedencia.add(cmbDptoLugPro, null);
        pnlProcedencia.add(lblProviLugPro, null);
        pnlProcedencia.add(cmbProviLugPro, null);
        pnlProcedencia.add(lblDistLugPro, null);
        pnlProcedencia.add(cmbDistLugPro, null);
        jContentPane.add(jPanel1, null);
        jContentPane.add(lblNumHisCli, null);
        jContentPane.add(btnF11, null);
        jContentPane.add(btnEsc, null);
        jContentPane.add(lblHistoria, null);
        jPanelHeader1.add(lblDatosPaciente, null);
        jContentPane.add(jPanelHeader1, null);
        
        
        
        
        this.getContentPane().add(jContentPane, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }
    
    /* ************************************************************************ */
    /*                           METODO initialize                              */
    /* ************************************************************************ */

    private void initialize(){
          initComponentes();
          cargaCombo();
          initCampos();
          //setUbigeoLocal();
      FarmaVariables.vAceptar = false;
    }
    
    /* ************************************************************************ */
    /*                         METODOS INICIALIZACION                           */
    /* ************************************************************************ */

    private void initCampos() {
        VariablesCentroMedico.vCodPaciente=paciente.getCodPaciente();
            lblNumHisCli.setText(paciente.getNumHistCli());
            txtApePaterno.setText(paciente.getApellidoPaterno());
            txtApeMaterno.setText(paciente.getApellidoMaterno());
            txtNombre.setText(paciente.getNombre());
            txtNumDoc.setText(paciente.getNumDocumento());
            auxNumDocPac=txtNumDoc.getText().trim();
            txtHCAnterior.setText(paciente.getNumHCFisica());
            txtFecHCAnterior.setText(paciente.getFecHCFisica());
            txtNomAcompanante.setText(paciente.getNomAcom());
            txtNumdocAcom.setText(paciente.getNumDocAcom());
            txtFecNacimiento.setText(paciente.getFecNacimiento());
            if(!(txtFecNacimiento.getText().trim().equals("")))
                setEdad(txtFecNacimiento.getText().trim());
            //txtLugNacimiento.setText(paciente.getLugNacimiento()); 
            //txtLugProcedencia.setText(paciente.getLugProcedencia()); 
            //txtUbigeo.setText(paciente.getUbigeo());
            txtDireccion.setText(paciente.getDireccion());
            txtOcupacion.setText(paciente.getOcupacion());
            txtCentroEL.setText(paciente.getCentroEduLab());
            txtCorreo.setText(paciente.getEmail());
            txtTelFijo.setText(paciente.getTelFijo());
            txtTelCel.setText(paciente.getTelCelular());
            
             
            //chbDireccionActual.setEnabled(false);
            //chbDatoMedico.setEnabled(false);
            
          /****/  
          /*chbLugarNacimiento.setSelected(false);
          actionPerformed_chk(chbLugarNacimiento,pnlLugarNacimiento);
          chbProcedencia.setSelected(false);
          actionPerformed_chk(chbProcedencia,pnlProcedencia);
          chbDatosContacto.setSelected(false);
          actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
          chbAcompanante.setSelected(false);
          actionPerformed_chk(chbAcompanante ,pnlAcompanante);
          flagDNIAcom=false;
          chbHClinica.setSelected(false);
          actionPerformed_chk(chbHClinica ,pnlHCAntigua);
          flagTipPac=false;*/
          /***/
                       
            if(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_NUEVO)){
                //cmbTipPaciente.setEnabled(true);
                //FarmaLoadCVL.setSelectedValueInComboBox(cmbTipPaciente, "cmbTipoPaciente",ConstantsCentroMedico.COMBOBOX_PACIENTE_NUEVO);
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipAcompanante, "cmbTipoAcompanante", ConstantsCentroMedico.COMBOBOX_ACOMPANANTE_NA);
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipDocAcom, "cmbTipoDocumentoAcom", ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NA);    
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipGradoIns, "cmbTipoGradoInstruc", ConstantsCentroMedico.COMBOBOX_GRADOINSTRUCCION_NR);
                chbDatoMedico.setSelected(false);
                actionPerformed_chk(chbDatoMedico,pnlDatoMedico);
                chbLugarNacimiento.setSelected(false);
                actionPerformed_chk(chbLugarNacimiento,pnlLugarNacimiento);
                chbProcedencia.setSelected(false);
                actionPerformed_chk(chbProcedencia,pnlProcedencia);
                chbDatosContacto.setSelected(false);
                actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                chbDatoAdicional.setSelected(false);
                actionPerformed_chk(chbDatoAdicional ,pnlDatoAdicional);
                chbAcompanante.setSelected(false);
                actionPerformed_chk(chbAcompanante ,pnlAcompanante);
                flagDNIAcom=false;
                chbHClinica.setSelected(false);
                actionPerformed_chk(chbHClinica ,pnlHCAntigua);
                flagTipPac=false;
                
                paciente.setSexo("");
                setUbigeoLocal();

                
            }else if(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_CONTINUADOR) ||
                     tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_ACTUALIZAR)  ||
                     tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR)){
                
                //cmbTipPaciente.setEnabled(false);
                //FarmaLoadCVL.setSelectedValueInComboBox(cmbTipPaciente, "cmbTipoPaciente",ConstantsCentroMedico.COMBOBOX_PACIENTE_CONTINUADOR);  
                chbHClinica.setEnabled(false);
                actionPerformed_chk(false, pnlHCAntigua);
                flagTipPac=false; // que no valide tipo paciente para modificar
                if(!(paciente.getTipDocumento().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipDoc, "cmbDocumento",paciente.getTipDocumento().trim()); 
                if(!(paciente.getTipAcom().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipAcompanante, "cmbTipoAcompanante",paciente.getTipAcom().trim()); 
                if(!(paciente.getTipDocAcom().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipDocAcom, "cmbTipoDocumentoAcom",paciente.getTipDocAcom().trim());
                if(!(paciente.getSexo().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipSexo, "cmbTipoSexo",paciente.getSexo().trim());
                if(!(paciente.getEstCivil().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipEstCivil, "cmbTipoEstadoCivil",paciente.getEstCivil().trim());
                if(!(paciente.getGradoInstruccion().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipGradoIns, "cmbTipoGradoInstruc",paciente.getGradoInstruccion().trim());
                if(!(paciente.getGrupoSan().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipGrupoSan, "cmbTipoGrupoSan",paciente.getGrupoSan().trim());
                if(!(paciente.getFactorRH().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbTipFactorRH, "cmbTipoFactorRH",paciente.getFactorRH().trim());
                
                if(!(paciente.getDepUbigeo().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDptoUbigeo, cmbDptoUbigeo.getName(),paciente.getDepUbigeo().trim());
                if(!(paciente.getPvrUbigeo().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbProviUbigeo,cmbProviUbigeo.getName(),paciente.getPvrUbigeo().trim());
                if(!(paciente.getDisUbigeo().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDistUbigeo, cmbDistUbigeo.getName(),paciente.getDisUbigeo().trim());
                if(!(paciente.getDepLugNac().equals("")))//
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDptoLugNac, cmbDptoLugNac.getName(),paciente.getDepLugNac().trim());
                if(!(paciente.getPvrLugNac().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbProviLugNac,cmbProviLugNac.getName(),paciente.getPvrLugNac().trim());
                if(!(paciente.getDisLugNac().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDistLugNac, cmbDistLugNac.getName(),paciente.getDisLugNac().trim());
                if(!(paciente.getDepLugPro().equals("")))//
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDptoLugPro, cmbDptoLugPro.getName(),paciente.getDepLugPro().trim());
                if(!(paciente.getPvrLugPro().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbProviLugPro,cmbProviLugPro.getName(),paciente.getPvrLugPro().trim());
                if(!(paciente.getDisLugPro().equals("")))
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDistLugPro, cmbDistLugPro.getName(),paciente.getDisLugPro().trim());
                
                if(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_ACTUALIZAR)){
                    
                    chbAcompanante.setEnabled(false);
                    cmbTipAcompanante.setEnabled(false);
                    txtNomAcompanante.setEditable(false);
                    cmbTipDocAcom.setEnabled(false);
                    txtNumdocAcom.setEditable(false);
                    
                    cmbTipGrupoSan.setEnabled(false);
                    cmbTipFactorRH.setEnabled(false);                  
                }else if(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR)){
                    /*log.info("Dehabilitar todo los componentes (Modo lectura on)");
                    Component comp[] = jPanel1.getComponents(); 
                    for(int i = 0; i<comp.length; i++){ 
                        comp[i].setEnabled(false); 
                    }*/
                    chbHClinica.setEnabled(false);
                    chbDatoMedico.setEnabled(false); 
                    chbLugarNacimiento.setEnabled(false); 
                    chbProcedencia.setEnabled(false); 
                    chbDireccionActual.setEnabled(false); 
                    chbDatosContacto.setEnabled(false); 
                    chbAcompanante.setEnabled(false); 
                    chbDatoAdicional.setEnabled(false);
                    actionPerformed_chk(true, pnlHCAntigua);
                    actionPerformed_chk(true, pnlDatoMedico);
                    actionPerformed_chk(true,pnlLugarNacimiento);
                    actionPerformed_chk(true,pnlProcedencia);
                    actionPerformed_chk(true ,pnlDireccionActual);
                    actionPerformed_chk(true ,pnlDatosContacto);
                    actionPerformed_chk(true ,pnlAcompanante);
                    actionPerformed_chk(true ,pnlDatoAdicional);
                    cmbTipDoc.setEnabled(false);
                    txtNumDoc.setEditable(false);
                    txtApePaterno.setEditable(false);
                    txtApeMaterno.setEditable(false);
                    txtNombre.setEditable(false);
                    cmbTipSexo.setEnabled(false);
                    txtFecNacimiento.setEditable(false);
                    cmbTipEstCivil.setEnabled(false);
                    
                    txtHCAnterior.setEditable(false);
                    txtFecHCAnterior.setEditable(false);
                    cmbDptoUbigeo.setEnabled(false);
                    cmbProviUbigeo.setEnabled(false);
                    cmbDistUbigeo.setEnabled(false);
                    txtDireccion.setEditable(false);
                    cmbDptoLugNac.setEnabled(false);
                    cmbProviLugNac.setEnabled(false);
                    cmbDistLugNac.setEnabled(false);
                    cmbDptoLugPro.setEnabled(false);
                    cmbProviLugPro.setEnabled(false);
                    cmbDistLugPro.setEnabled(false);
                    
                    cmbTipFactorRH.setEnabled(false);
                    cmbTipGrupoSan.setEnabled(false);
                    txtTelCel.setEditable(false);
                    txtCorreo.setEditable(false);
                    txtTelFijo.setEditable(false);
                    
                    cmbTipGradoIns.setEnabled(false);
                    txtOcupacion.setEditable(false);
                    txtCentroEL.setEditable(false);
                    cmbTipAcompanante.setEnabled(false);
                    txtNomAcompanante.setEditable(false);
                    cmbTipDocAcom.setEnabled(false);
                    txtNumdocAcom.setEditable(false);
     
                }
                
                
                /**********/
                //log.info("Comienzo");
                if(paciente.getDepLugNac().trim().equals("") && paciente.getPvrLugNac().trim().equals("") && paciente.getDisLugNac().trim().equals("")){
                    chbLugarNacimiento.setSelected(false);
                    actionPerformed_chk(chbLugarNacimiento,pnlLugarNacimiento);
                }
                
                if(paciente.getDepLugPro().trim().equals("") && paciente.getPvrLugPro().trim().equals("") && paciente.getDisLugPro().trim().equals("")){
                    chbProcedencia.setSelected(false);
                    actionPerformed_chk(chbProcedencia,pnlProcedencia);
                }
                
                if(paciente.getGrupoSan().trim().equals("") && paciente.getFactorRH().trim().equals("")){
                    chbDatoMedico.setSelected(false);
                    actionPerformed_chk(chbDatoMedico,pnlDatoMedico);
                }
                
                if(paciente.getTelCelular().trim().equals("") && paciente.getTelFijo().trim().equals("") && paciente.getEmail().trim().equals("")){
                    chbDatosContacto.setSelected(false);
                    actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                }
                
                if(paciente.getGradoInstruccion().trim().equals(ConstantsCentroMedico.COMBOBOX_GRADOINSTRUCCION_NR) && txtOcupacion.getText().trim().equals("") &&  
                   txtCentroEL.getText().trim().equals("")){
                    chbDatoAdicional.setSelected(false);
                    actionPerformed_chk(chbDatoAdicional ,pnlDatoAdicional);
                }
                
                if(paciente.getTipAcom().trim().equals(ConstantsCentroMedico.COMBOBOX_ACOMPANANTE_NA)){
                    chbAcompanante.setSelected(false);
                    actionPerformed_chk(chbAcompanante ,pnlAcompanante);
                }
                /***********/
            
            }
            

          
          if(!(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR))){
          cmbTipDoc_actionPerformed();
          }
          
          if(!(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR)) && 
             !(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_NUEVO))){
          cmbTipAcompanante_actionPerformed();    
          cmbTipDocAcom_actionPerformed();
          }
      }





    
    /* ************************************************************************ */
    /*                           METODOS DE EVENTOS                             */
    /* ************************************************************************ */

    private void this_windowOpened(WindowEvent e){
        FarmaUtility.centrarVentana(this);
        if(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR))
        FarmaUtility.moveFocus(txtApePaterno);
        else
        FarmaUtility.moveFocus(cmbTipDoc);
    }

    private void this_windowClosing(WindowEvent e){
      FarmaUtility.showMessage(this,"Debe presionar la tecla ESC para cerrar la ventana.",txtApePaterno);
    }
    
    /* ************************************************************************ */
    /*                      METODOS AUXILIARES DE EVENTOS                       */
    /* ************************************************************************ */

    private void cargaCombo() {
        cargaDptos();
        //cargaProvincias();
        //cargaDistritos();
        //setUbigeoLocal();
        
        ArrayList parametros = new ArrayList();
        parametros.add(FarmaVariables.vCodGrupoCia);
        FarmaLoadCVL.loadCVLFromSP(cmbTipDoc, "cmbDocumento", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_DOCUMENTO(?)",
                                   parametros, true, true);
        //FarmaLoadCVL.loadCVLFromSP(cmbTipPaciente, "cmbTipoPaciente", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_PACIENTE(?)",
       //                            parametros, true, true);
        FarmaLoadCVL.loadCVLFromSP(cmbTipAcompanante, "cmbTipoAcompanante", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_PARENTESCO(?)",
                                   parametros, true, true);
        FarmaLoadCVL.loadCVLFromSP(cmbTipDocAcom, "cmbTipoDocumentoAcom", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_DOCUMENTO(?)",
                                   parametros, true, true);
        FarmaLoadCVL.loadCVLFromSP(cmbTipSexo, "cmbTipoSexo", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_SEXO(?)",
                                   parametros, true, true);
        FarmaLoadCVL.loadCVLFromSP(cmbTipEstCivil, "cmbTipoEstadoCivil", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_EST_CIVIL(?)",
                                   parametros, true, true);
        FarmaLoadCVL.loadCVLFromSP(cmbTipGradoIns, "cmbTipoGradoInstruc", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_GRADO_INS(?)",
                                   parametros, true, true);
        FarmaLoadCVL.loadCVLFromSP(cmbTipFactorRH, "cmbTipoFactorRH", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_FACTORRH(?)",
                                   parametros, false, true);
        FarmaLoadCVL.loadCVLFromSP(cmbTipGrupoSan, "cmbTipoGrupoSan", "PTOVENTA_CME_ADM.CME_LISTA_TIPOS_GRUPOSAN(?)",
                                   parametros, false, true);
    }
    
    private void chkKeyPressed(KeyEvent e){
      if (UtilityPtoVenta.verificaVK_F11(e)){
          evento_f11();
                  
      }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
          if(JConfirmDialog.rptaConfirmDialog(this, "¿Desea salir sin guardar los cambios?")){
            cerrarVentana(false);
         }
      }
    }
    
    private boolean validaDatos(){
        //if(!(setEdad(txtFecNacimiento.getText().trim()))) return false;
        //if(!(validaFecHC())) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtApePaterno,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtApeMaterno,this)) return false;
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtNombre,this)) return false;
        if(flagDNIPac) // si no es NR ni NA
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtNumDoc,this)) return false; 
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtFecNacimiento,this)) return false;
        if(!(setEdad(txtFecNacimiento.getText().trim()))) return false;
        if(flagTipPac){
            if(UtilityAdmisionMedica.valorCampoEsNulo(txtHCAnterior,this)) return false;
            if(UtilityAdmisionMedica.valorCampoEsNulo(txtFecHCAnterior,this)) return false;
            if(!(validaFecHC())) return false;
        }
        if(!(flagMayorEdad)){
            if(UtilityAdmisionMedica.valorCampoEsNulo(txtNomAcompanante,false,this)){
                FarmaUtility.showMessage(this, "El campo Nombre de Acompañante es obligatorio para pacientes menores de edad", //txtNomAcompanante);
                                        (txtNomAcompanante.isEnabled()) ? txtNomAcompanante : cmbTipAcompanante);
                return false;
            }
            if(UtilityAdmisionMedica.valorCampoEsNulo(txtNumdocAcom,false,this)){ 
                FarmaUtility.showMessage(this, "El campo Número Doc. de Acompañante es obligatorio para pacientes menores de edad", //txtNumdocAcom);
                                        (txtNumdocAcom.isEnabled()) ? txtNumdocAcom :/*else*/ (cmbTipDocAcom.isEnabled()) ? cmbTipDocAcom : cmbTipAcompanante);
                return false;
            }                                                                      
        }
        
        if(UtilityAdmisionMedica.valorCampoEsNulo(cmbDptoUbigeo,false,this)){
            FarmaUtility.showMessage(this, "No ha selecionado ningún departamento en la Dirección Actual",cmbDptoUbigeo);
            return false;
        }
        if(UtilityAdmisionMedica.valorCampoEsNulo(cmbProviUbigeo,false,this)){
            FarmaUtility.showMessage(this, "No ha selecionado ninguna provincia en la Dirección Actual",cmbProviUbigeo);
            return false; 
        }
        if(UtilityAdmisionMedica.valorCampoEsNulo(cmbDistUbigeo,false,this)){ 
            FarmaUtility.showMessage(this, "No ha selecionado ningún distrito en la Dirección Actual",cmbDistUbigeo);
            return false; 
        }
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtDireccion,this)) return false;
        
        if(flagDNIAcom)
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtNumdocAcom,this)) return false;      
        //if(UtilityAdmisionMedica.valorCampoEsNulo(txtUbigeo,this)) return false;
  
        /*if(flagTipAcom) // si es NA
        if(UtilityAdmisionMedica.valorCampoEsNulo(txtNomAcompanante,this)) return false;*/
        
        return Boolean.TRUE;
    }
    
    
    private void cmbTipDoc_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(txtNumDoc.isEnabled())
            FarmaUtility.moveFocus(txtNumDoc); 
            else
            FarmaUtility.moveFocus(txtApePaterno);    
        }
        chkKeyPressed(e);
    }
    
    private void txtNumDoc_keyPressed(KeyEvent e) {
        
        if((txtFecNacimiento.getText().trim().equals("")))
              txtEdad.setText("");
        
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            //if(!(auxNumDocPac.equals(txtNumDoc.getText().trim())))  // si ya el document es igual no buscar
            mostrarDatosPaciente();
            FarmaUtility.moveFocus(txtApePaterno);  
        }
        chkKeyPressed(e);
    }
    
    private void txtApePaterno_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtApeMaterno);
        }
        chkKeyPressed(e);
    }

    private void txtApeMaterno_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtNombre);
        }
        chkKeyPressed(e);
    }
    private void txtNombre_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbTipSexo);
        }
        chkKeyPressed(e);
    }
   
   private void cmbTipSexo_keyPressed(KeyEvent e) {
       if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           FarmaUtility.moveFocus(txtFecNacimiento);
       }
       chkKeyPressed(e);
   }
   
    private void txtFecNacimiento_keyPressed(KeyEvent e) {
        //chkKeyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if(setEdad(txtFecNacimiento.getText().trim())){
                if(txtEdad.getText().trim().length()>0 && flagMayorEdad==false)
                FarmaUtility.showMessage(this, "Paciente menor de edad, por favor ingresar acompañante", null);
                
                FarmaUtility.moveFocus(cmbTipEstCivil);
            }
            
        }else{
            chkKeyPressed(e); 
        }
    }
    
    private void txtEdad_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }   

    private void cmbTipEstCivil_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(txtFecHCAnterior.isEnabled())
            FarmaUtility.moveFocus(txtHCAnterior); 
            else
            FarmaUtility.moveFocus(cmbDptoUbigeo);
        }
        chkKeyPressed(e);
    }
    
    //
    private void txtHCAnterior_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtFecHCAnterior);        
        }
        chkKeyPressed(e);
    }
    private void txtFecHCAnterior_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if((validaFecHC()))
            FarmaUtility.moveFocus(cmbDptoUbigeo);
        }
        chkKeyPressed(e);
    }
    
    //
    private void cmbDptoUbigeo_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbProviUbigeo);     
        }
        chkKeyPressed(e);
    }
    private void cmbProviUbigeo_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbDistUbigeo);     
        }
        chkKeyPressed(e);
    }
    private void cmbDistUbigeo_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtDireccion);     
        }
        chkKeyPressed(e);
    }
    private void txtDireccion_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(cmbTipGrupoSan.isEnabled())
            FarmaUtility.moveFocus(cmbTipGrupoSan); 
            else
            FarmaUtility.moveFocus(cmbDptoUbigeo);
        }
        chkKeyPressed(e);
    }
    
    //
    private void cmbTipGrupoSan_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbTipFactorRH);
        }
        chkKeyPressed(e);
    }
    private void cmbTipFactorRH_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(cmbDptoLugNac.isEnabled())
            FarmaUtility.moveFocus(cmbDptoLugNac); 
            else if(cmbDptoLugPro.isEnabled())
            FarmaUtility.moveFocus(cmbDptoLugPro);
            else if(txtTelCel.isEnabled())
            FarmaUtility.moveFocus(txtTelCel);
            else
            FarmaUtility.moveFocus(cmbTipGradoIns);   
        }
        chkKeyPressed(e);
    }
    
    //
    private void cmbDptoLugNac_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbProviLugNac);     
        }
        chkKeyPressed(e);
    }
    private void cmbProviLugNac_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbDistLugNac);     
        }
        chkKeyPressed(e);
    }
    private void cmbDistLugNac_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbDptoLugPro);     
        }
        chkKeyPressed(e);
    }
    //
    private void cmbDptoLugPro_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbProviLugPro);     
        }
        chkKeyPressed(e);
    }
    private void cmbProviLugPro_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbDistLugPro);     
        }
        chkKeyPressed(e);
    }
    private void cmbDistLugPro_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtTelCel);     
        }
        chkKeyPressed(e);
    }
    
    //
    private void txtTelCel_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtTelFijo);
        }
        chkKeyPressed(e);
    }
    private void txtTelFijo_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtCorreo);//
        }
        chkKeyPressed(e);
    }
    private void txtCorreo_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(cmbTipGradoIns);
        }
        chkKeyPressed(e);
    }
    
    //
    private void cmbTipGradoIns_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtOcupacion);
        }
        chkKeyPressed(e);
    }
    private void txtOcupacion_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtCentroEL);
        }
        chkKeyPressed(e);
    }
    private void txtCentroEL_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(cmbTipAcompanante.isEnabled())
            FarmaUtility.moveFocus(cmbTipAcompanante);
            else
            FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }

    private void cmbTipAcompanante_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(txtNomAcompanante.isEnabled())
            FarmaUtility.moveFocus(txtNomAcompanante);
            /*else if(cmbTipDocAcom.isEnabled())
            FarmaUtility.moveFocus(cmbTipDocAcom);*/
            else
            FarmaUtility.moveFocus(cmbTipDoc);    
        }
        chkKeyPressed(e);
    }
    private void txtNomAcompanante_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(cmbTipDocAcom.isEnabled())
            FarmaUtility.moveFocus(cmbTipDocAcom);
            else if(txtNumdocAcom.isEnabled())
            FarmaUtility.moveFocus(txtNumdocAcom);
            else
            FarmaUtility.moveFocus(cmbTipDoc);     
        }
        chkKeyPressed(e);
    }
    private void cmbTipDocAcom_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(txtNumdocAcom.isEnabled())
            FarmaUtility.moveFocus(txtNumdocAcom);    
            else
            FarmaUtility.moveFocus(cmbTipDoc);  
        }
        chkKeyPressed(e);
    }
    private void txtNumdocAcom_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbHClinica_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbDatoMedico_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbLugarNacimiento_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbProcedencia_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbDireccionActual_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbDatosContacto_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbAcompanante_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    private void chbDatoAdicional_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
           //FarmaUtility.moveFocus(cmbTipDoc);
        }
        chkKeyPressed(e);
    }
    

    private void cmbTipPaciente_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if(txtHCAnterior.isEnabled())
            FarmaUtility.moveFocus(txtHCAnterior);
            else
            FarmaUtility.moveFocus(cmbTipAcompanante);    
        }
        chkKeyPressed(e);
    }
    
    private void txtLugNacimiento_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          //  FarmaUtility.moveFocus(txtLugProcedencia);
        }
        chkKeyPressed(e);
    }    
    private void txtLugProcedencia_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          //  FarmaUtility.moveFocus(txtUbigeo);
        }
        chkKeyPressed(e);
    }
    private void txtUbigeo_keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            FarmaUtility.moveFocus(txtDireccion);
        }
        chkKeyPressed(e);
    }


    
    private void cmbTipDoc_actionPerformed(/*ActionEvent e*/){
        String vTipDocPac = FarmaLoadCVL.getCVLCode("cmbDocumento", cmbTipDoc.getSelectedIndex());
        if(vTipDocPac.equals(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NR) ||
           vTipDocPac.equals(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NA) ||
           tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR)){
            txtNumDoc.setText("");
            flagDNIPac=false;
        }else{
            flagDNIPac=true;
        }
        txtNumDoc.setEnabled(flagDNIPac);
        txtNumDoc.setEditable(flagDNIPac);
    }
    
    private void cmbTipPaciente_actionPerformed(ActionEvent e){
        String vTipComp = "";
            //FarmaLoadCVL.getCVLCode("cmbTipoPaciente", cmbTipPaciente.getSelectedIndex());
        if(vTipComp.equals(ConstantsCentroMedico.COMBOBOX_PACIENTE_NUEVO)){
            flagTipPac=false;
            txtHCAnterior.setText("");
            txtFecHCAnterior.setText("");
        }else if(vTipComp.equals(ConstantsCentroMedico.COMBOBOX_PACIENTE_CONTINUADOR)){
            flagTipPac=true;
        }
        
        txtHCAnterior.setEnabled(flagTipPac);
        txtHCAnterior.setEditable(flagTipPac);
        txtFecHCAnterior.setEditable(flagTipPac);
        txtFecHCAnterior.setEnabled(flagTipPac);
    }
    
    private void cmbTipAcompanante_actionPerformed(/*ActionEvent e*/){ 
        String vTipAcomp = FarmaLoadCVL.getCVLCode("cmbTipoAcompanante", cmbTipAcompanante.getSelectedIndex());
        if(vTipAcomp.equals(ConstantsCentroMedico.COMBOBOX_ACOMPANANTE_NA)){
            txtNomAcompanante.setText("");
            FarmaLoadCVL.setSelectedValueInComboBox(cmbTipDocAcom, "cmbTipoDocumentoAcom", ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NR);
            cmbTipDocAcom_actionPerformed();
            flagTipAcom=false;
        }else{
            flagTipAcom=true;
        }
        
        cmbTipDocAcom.setEnabled(flagTipAcom);
        txtNomAcompanante.setEnabled(flagTipAcom);
        txtNomAcompanante.setEditable(flagTipAcom);
    }

    private void cmbTipDocAcom_actionPerformed(/*ActionEvent e*/){
        String vTipDocAcom = FarmaLoadCVL.getCVLCode("cmbTipoDocumentoAcom", cmbTipDocAcom.getSelectedIndex());
        if(vTipDocAcom.equals(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NR) ||
           vTipDocAcom.equals(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NA)){
            txtNumdocAcom.setText("");
            flagDNIAcom=false;
        }else{
            flagDNIAcom=true;
        }
        txtNumdocAcom.setEnabled(flagDNIAcom);
        txtNumdocAcom.setEditable(flagDNIAcom);
    }
    
    
    

    private void txtFecHCAnterior_keyReleased(KeyEvent e) {
        FarmaUtility.dateComplete(txtFecHCAnterior, e);
    }
    
    private void txtFecNacimiento_keyReleased(KeyEvent e) {
        //FarmaUtility.dateComplete(txtFecNacimiento, e);
        char keyChar = e.getKeyChar();
                if (Character.isDigit(keyChar)) {
                    if (txtFecNacimiento.getText().trim().length() == 5) {
                     //   log.debug("released 1" + txtFecNacimiento.getText().trim());
                        txtFecNacimiento.setText(txtFecNacimiento.getText().trim() + "/19");
                     //   log.debug("released 2 " + txtFecNacimiento.getText().trim());
                    }
                }        
    }
    
    
    private void lblNombre_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtNombre);
    }

    private void lblTipDoc_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(cmbTipDoc);
    }

    private void lblNumDoc_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(txtNumDoc);
    }
    
    private void lblHCAnterior_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtHCAnterior);
    }
    private void lblFecHCAnterior_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtFecHCAnterior);
    }
    private void lblAcompanante_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(cmbTipAcompanante);
    }
    private void lblTipDocAcom_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(cmbTipDocAcom);
    }
    private void lblNumDocAcom_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtNumdocAcom);
    }
    private void lblTipSexo_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(cmbTipSexo);
    }
    private void lblTipEstCivil_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(cmbTipEstCivil);
    }
    private void lblEdad_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtEdad);
    }
    private void lblFecNacimiento_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtFecNacimiento);
    }
   
    private void lblTipGradoIns_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(cmbTipGradoIns);
    }
    private void lblOcupacion_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtOcupacion);
    }
    private void lblCentroEL_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtCentroEL);
    }
    private void lblTipGrupoSan_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(cmbTipGrupoSan);
    }
    private void lblTipFactorRH_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(cmbTipFactorRH);
    }
    private void lblCorreo_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtCorreo);
    }
    private void lblTelFijo_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtTelFijo);
    }
    private void lblTelCel_actionPerformed(ActionEvent e) {
      FarmaUtility.moveFocus(txtTelCel);
    }

    
    
    private void cerrarVentana(boolean pAceptar){
      FarmaVariables.vAceptar = pAceptar;
      this.setVisible(false);
      this.dispose();
    }

    /* ************************************************************************ */
    /*                      METODOS DE LOGICA DE NEGOCIO                        */
    /* ************************************************************************ */
    
    public boolean setEdad(String fecha){
        boolean flag=true;
      if(fecha.length()!=0){
          try{
                ArrayList lista = UtilityAdmisionMedica.obtenerEdad(fecha);
                    String indMEdad=((String)((ArrayList)lista.get(0)).get(2)).trim();
                    if(indMEdad.equals("S"))
                      flagMayorEdad=true;
                    else
                      flagMayorEdad=false; 
                    
                txtEdad.setText(((String)((ArrayList)lista.get(0)).get(0)).trim()); 
              
            }catch (SQLException sql) {
                            FarmaUtility.liberarTransaccion();
                            if (sql.getErrorCode() > 20000) {
                            FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                     txtFecNacimiento);
                            } 
                            else {
                            FarmaUtility.showMessage(this, "Error al obtener la edad.\n" + sql.getMessage(), txtFecNacimiento);
                            log.error("", sql);
                            }
                flagMayorEdad=true; 
                flag=false;           
            }catch(Exception er){
                            FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+er.getMessage(), txtFecNacimiento);  
                            log.error("", er);
                flagMayorEdad=true; 
                flag=false;    
            }   
          
      }
    
        log.info("edad : "+txtEdad.getText());
        log.info("flagMayorEdad : "+flagMayorEdad);
        return flag;
    }
    
    public boolean validaFecHC(){
        return UtilityAdmisionMedica.validaFecha(this,txtFecHCAnterior,"de la HC");
    }
    
    
	
    public boolean grabar(){
        boolean flag=false;
        String codPaciente="";
        String tipoOperacion="I";
        if(paciente.getCodPaciente().equals("0"))
            tipoOperacion="I";
        else
            tipoOperacion="U";
        
        paciente.setNumHistCli(lblNumHisCli.getText().trim());//El paciente no existe   
        
        paciente.setApellidoPaterno(txtApePaterno.getText().trim());
        paciente.setApellidoMaterno(txtApeMaterno.getText().trim());
        paciente.setNombre(txtNombre.getText().trim());
        paciente.setTipDocumento(FarmaLoadCVL.getCVLCode("cmbDocumento", cmbTipDoc.getSelectedIndex()));
        paciente.setNumDocumento(txtNumDoc.getText().trim());
        
        paciente.setNumHCFisica(txtHCAnterior.getText().trim());
        paciente.setFecHCFisica(txtFecHCAnterior.getText().trim());
        paciente.setTipAcom(FarmaLoadCVL.getCVLCode("cmbTipoAcompanante", cmbTipAcompanante.getSelectedIndex()));
        paciente.setNomAcom(txtNomAcompanante.getText().trim());
        paciente.setTipDocAcom(FarmaLoadCVL.getCVLCode("cmbTipoDocumentoAcom", cmbTipDocAcom.getSelectedIndex()));
        paciente.setNumDocAcom(txtNumdocAcom.getText().trim());
        
        paciente.setSexo(FarmaLoadCVL.getCVLCode("cmbTipoSexo", cmbTipSexo.getSelectedIndex()));
        paciente.setEstCivil(FarmaLoadCVL.getCVLCode("cmbTipoEstadoCivil", cmbTipEstCivil.getSelectedIndex()));
        paciente.setFecNacimiento(txtFecNacimiento.getText().trim());
        paciente.setDireccion(txtDireccion.getText().trim());
        
       // paciente.setLugProcedencia(txtLugNacimiento.getText().trim());
       // paciente.setLugNacimiento(txtLugProcedencia.getText().trim());
       // paciente.setUbigeo(txtUbigeo.getText().trim());
        paciente.setDepUbigeo(FarmaLoadCVL.getCVLCode(cmbDptoUbigeo.getName(),  cmbDptoUbigeo.getSelectedIndex()));
        paciente.setPvrUbigeo(FarmaLoadCVL.getCVLCode(cmbProviUbigeo.getName(), cmbProviUbigeo.getSelectedIndex()));
        paciente.setDisUbigeo(FarmaLoadCVL.getCVLCode(cmbDistUbigeo.getName(),  cmbDistUbigeo.getSelectedIndex()));
        
        paciente.setDepLugNac(FarmaLoadCVL.getCVLCode(cmbDptoLugNac.getName(),  cmbDptoLugNac.getSelectedIndex()));
        paciente.setPvrLugNac(FarmaLoadCVL.getCVLCode(cmbProviLugNac.getName(), cmbProviLugNac.getSelectedIndex()));
        paciente.setDisLugNac(FarmaLoadCVL.getCVLCode(cmbDistLugNac.getName(),  cmbDistLugNac.getSelectedIndex()));
        
        paciente.setDepLugPro(FarmaLoadCVL.getCVLCode(cmbDptoLugPro.getName(),  cmbDptoLugPro.getSelectedIndex()));
        paciente.setPvrLugPro(FarmaLoadCVL.getCVLCode(cmbProviLugPro.getName(), cmbProviLugPro.getSelectedIndex()));
        paciente.setDisLugPro(FarmaLoadCVL.getCVLCode(cmbDistLugPro.getName(),  cmbDistLugPro.getSelectedIndex()));
    
        
        paciente.setGradoInstruccion(FarmaLoadCVL.getCVLCode("cmbTipoGradoInstruc", cmbTipGradoIns.getSelectedIndex()));
        paciente.setOcupacion(txtOcupacion.getText().trim());
        paciente.setCentroEduLab(txtCentroEL.getText().trim());
        
        paciente.setFactorRH(FarmaLoadCVL.getCVLCode("cmbTipoFactorRH", cmbTipFactorRH.getSelectedIndex()));
        paciente.setGrupoSan(FarmaLoadCVL.getCVLCode("cmbTipoGrupoSan", cmbTipGrupoSan.getSelectedIndex()));
        
        paciente.setEmail(txtCorreo.getText().trim());
        paciente.setTelFijo(txtTelFijo.getText().trim());
        paciente.setTelCelular(txtTelCel.getText().trim());
        
        /*if(!(chbHClinica.isSelected())){
            paciente.setNumHCFisica("");
            paciente.setFecHCFisica("");
        }
        if(!(chbLugarNacimiento.isSelected())){
            paciente.setDepLugNac("");
            paciente.setPvrLugNac("");
            paciente.setDisLugNac("");
        }
        if(!(chbProcedencia.isSelected())){
            paciente.setDepLugPro("");
            paciente.setPvrLugPro("");
            paciente.setDisLugPro("");
        }
        if(!(chbDatosContacto.isSelected())){
            paciente.setTelCelular("");
            paciente.setTelFijo("");
            paciente.setEmail("");
        }
        if(!(chbAcompanante.isSelected())){
            paciente.setTipDocAcom(ConstantsCentroMedico.COMBOBOX_ACOMPANANTE_NA);
            paciente.setNomAcom("");
            paciente.setTipDocAcom(ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NA);
            paciente.setNumDocAcom("");
        }*/
        
        
        try{
            VariablesCentroMedico.vCodPaciente = UtilityAdmisionMedica.grabarPaciente(tipoOperacion,paciente);
        FarmaUtility.aceptarTransaccion();    
        
        // graba paciente historia clinica en RAC , por gestor
            UtilityAtencionMedica.registraHClinicaPaciente(VariablesCentroMedico.vCodPaciente);    
            
            
        paciente.setCodPaciente(VariablesCentroMedico.vCodPaciente);
            flag=true;
        }catch (SQLException sql) {
         FarmaUtility.liberarTransaccion();    
         log.error("", sql);
                    FarmaUtility.liberarTransaccion();
                    if (sql.getErrorCode() == 20001) {
                        FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                 txtFecNacimiento);
                    }else if (sql.getErrorCode() == 20002) {
                        FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                 txtFecHCAnterior);
                    }else if (sql.getErrorCode() == 20003) {
                        FarmaUtility.showMessage(this, sql.getMessage().substring(10, sql.getMessage().indexOf("ORA-06512")),
                                                 txtNumDoc);
                    }else {
                        FarmaUtility.showMessage(this, "Error al grabar paciente.\n" +
                                sql.getMessage(), txtApePaterno);
                    }
    
            flag=false;    
        }catch(Exception er){
            FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+er.getMessage(), txtApePaterno);
            flag=false;    
        }          
        /*}catch(SQLException ex){
            FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+ ex.getMessage(), txtApePaterno);
            flag=false;
        }catch(Exception er){
            FarmaUtility.showMessage(this, "Ocurrió el siguiente error\n "+er.getMessage(), txtApePaterno);
            flag=false;    
        }*/
        
        log.info("codPaciente : "+paciente.getCodPaciente());
        return flag;
    }
        
        

    public BeanPaciente getBeanPaciente() {
        return beanPaciente;
    }

    public void setBeanPaciente(BeanPaciente beanPaciente) {
        this.beanPaciente = beanPaciente;
    }
    
    private void initComponentes(){
       /* txtLugNacimiento.addCharacterValidate(" ");
        txtLugNacimiento.addCharacterValidate(",");
        txtLugNacimiento.addCharacterValidate("/");
        txtLugNacimiento.addCharacterValidate("-");
        txtLugNacimiento.addCharacterValidate("Ñ");
        txtLugNacimiento.addCharacterValidate("ñ");
        
        txtLugProcedencia.addCharacterValidate(" ");
        txtLugProcedencia.addCharacterValidate(",");
        txtLugProcedencia.addCharacterValidate("/");
        txtLugProcedencia.addCharacterValidate("-");
        txtLugProcedencia.addCharacterValidate("Ñ");
        txtLugProcedencia.addCharacterValidate("ñ");
        
        txtUbigeo.addCharacterValidate(" ");
        txtUbigeo.addCharacterValidate(",");
        txtUbigeo.addCharacterValidate("/");
        txtUbigeo.addCharacterValidate("-");
        txtUbigeo.addCharacterValidate("Ñ");
        txtUbigeo.addCharacterValidate("ñ");*/
        
        txtDireccion.addCharacterValidate(" ");
        txtDireccion.addCharacterValidate(",");
        txtDireccion.addCharacterValidate("/");
        txtDireccion.addCharacterValidate("-");
        txtDireccion.addCharacterValidate("Ñ");
        txtDireccion.addCharacterValidate("ñ");
        
        txtCentroEL.addCharacterValidate(" ");
        txtCentroEL.addCharacterValidate(",");
        txtCentroEL.addCharacterValidate("/");
        txtCentroEL.addCharacterValidate("-");
        txtCentroEL.addCharacterValidate(".");
        txtCentroEL.addCharacterValidate("Ñ");
        txtCentroEL.addCharacterValidate("ñ");
    }

    private void cargaBorderCheck() {
    
        chbHClinica.setFocusPainted(false); 
        ComponentTitledBorder componentBorder = 
                new ComponentTitledBorder(chbHClinica, pnlHCAntigua 
                , BorderFactory.createEtchedBorder()); 
        chbHClinica.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                
            if(chbHClinica.isSelected()){ // si seleccionó el check
                actionPerformed_chk(chbHClinica, pnlHCAntigua);
                if(chbHClinica.isSelected())
                flagTipPac=true;
                else
                flagTipPac=false;
                
            }else{ //si desseleccionó el check
                if(JConfirmDialog.rptaConfirmDialog(this, "Se perderán los cambios realizados en la Historia Clinica Anterior\n"+         
                                    FarmaUtility.completeWithSymbol("¿Desea Continuar?",54 , " ", "I") )){  
                    txtHCAnterior.setText("");
                    txtFecHCAnterior.setText("");
                    actionPerformed_chk(chbHClinica, pnlHCAntigua);
                    if(chbHClinica.isSelected())
                    flagTipPac=true;
                    else
                    flagTipPac=false;
                }else
                 chbHClinica.setSelected(true);
            }

         } 
             
        }); 
        pnlHCAntigua.setBorder(componentBorder); 
        ////////////////////////////////////////////////////////
        chbDatoMedico.setFocusPainted(false); 
        ComponentTitledBorder componentBorder_2 = 
                new ComponentTitledBorder(chbDatoMedico, pnlDatoMedico 
                , BorderFactory.createEtchedBorder()); 
        chbDatoMedico.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //actionPerformed_chk(chbDatoMedico, pnlDatoMedico);
                if(chbDatoMedico.isSelected()){ // si seleccionó el check
                    actionPerformed_chk(chbDatoMedico, pnlDatoMedico);
                }else{ //si desseleccionó el check
                    if(JConfirmDialog.rptaConfirmDialog(this, "Se perderán los cambios realizados en los Datos médicos\n"+         
                                    FarmaUtility.completeWithSymbol("¿Desea Continuar?",46 , " ", "I") )){  
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbTipFactorRH, "cmbTipoFactorRH","");
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbTipGrupoSan, "cmbTipoGrupoSan","");
                        actionPerformed_chk(chbDatoMedico, pnlDatoMedico);
                    }else
                     chbDatoMedico.setSelected(true);
                }
            } 
        }); 
        pnlDatoMedico.setBorder(componentBorder_2);
        ////////////////////////////////////////////////////////
        chbLugarNacimiento.setFocusPainted(false); 
        ComponentTitledBorder componentBorder_3 = 
                new ComponentTitledBorder(chbLugarNacimiento,pnlLugarNacimiento  
                , BorderFactory.createEtchedBorder()); 
        chbLugarNacimiento .addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //actionPerformed_chk(chbLugarNacimiento,pnlLugarNacimiento);
                if(chbLugarNacimiento.isSelected()){ // si seleccionó el check
                    actionPerformed_chk(chbLugarNacimiento,pnlLugarNacimiento);
                }else{ //si desseleccionó el check
                    if(JConfirmDialog.rptaConfirmDialog(this, "Se perderán los cambios realizados en el Lugar de Nacimiento\n"+         
                                    FarmaUtility.completeWithSymbol("¿Desea Continuar?",50 , " ", "I") )){   
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbDptoLugNac, cmbDptoLugNac.getName(),"");
                        actionPerformed_chk(chbLugarNacimiento,pnlLugarNacimiento);
                    }else
                     chbLugarNacimiento.setSelected(true);
                }
                
            } 
        }); 
        pnlLugarNacimiento.setBorder(componentBorder_3);
        ////////////////////////////////////////////////////////
        chbProcedencia.setFocusPainted(false); 
        ComponentTitledBorder componentBorder_4 = 
                new ComponentTitledBorder(chbProcedencia,pnlProcedencia  
                , BorderFactory.createEtchedBorder()); 
        chbProcedencia .addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //actionPerformed_chk(chbProcedencia,pnlProcedencia);
                if(chbProcedencia.isSelected()){ // si seleccionó el check
                    actionPerformed_chk(chbProcedencia,pnlProcedencia);
                }else{ //si desseleccionó el check
                    if(JConfirmDialog.rptaConfirmDialog(this, "Se perderán los cambios realizados en el Lugar de Procedencia\n"+         
                                    FarmaUtility.completeWithSymbol("¿Desea Continuar?",52 , " ", "I") )){   
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbDptoLugPro, cmbDptoLugPro.getName(),"");
                        actionPerformed_chk(chbProcedencia,pnlProcedencia);
                    }else
                     chbProcedencia.setSelected(true);
                }
            } 
        }); 
        pnlProcedencia.setBorder(componentBorder_4);
        ////////////////////////////////////////////////////////       
        
        chbDireccionActual.setFocusPainted(false); 
        ComponentTitledBorder componentBorder_5 = 
                new ComponentTitledBorder(chbDireccionActual ,pnlDireccionActual  
                , BorderFactory.createEtchedBorder()); 
        chbDireccionActual  .addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                actionPerformed_chk(chbDireccionActual ,pnlDireccionActual);
            } 
        }); 
        //pnlDireccionActual.setBorder(componentBorder_5);
        //pnlDireccionActual.setBorder(BorderFactory.createEtchedBorder());
        pnlDireccionActual.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()
                                                                      ,"Dirección Actual"));
        ////////////////////////////////////////////////////////    
        
        
        chbDatosContacto.setFocusPainted(false); 
        ComponentTitledBorder componentBorder_6 = 
                new ComponentTitledBorder(chbDatosContacto ,pnlDatosContacto  
                , BorderFactory.createEtchedBorder()); 
        chbDatosContacto  .addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                if(chbDatosContacto.isSelected()){ // si seleccionó el check
                    actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                }else{ //si desseleccionó el check
                    if(JConfirmDialog.rptaConfirmDialog(this, "Se perderán los cambios realizados en los Datos de Contacto\n"+         
                                    FarmaUtility.completeWithSymbol("¿Desea Continuar?",50 , " ", "I") )){   
                        txtTelCel.setText("");
                        txtTelFijo.setText("");
                        txtCorreo.setText("");
                        actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                    }else
                     chbDatosContacto.setSelected(true);
                }
            } 
        }); 
        pnlDatosContacto.setBorder(componentBorder_6);
        //////////////////////////////////////////////////////
        
        
        
        chbAcompanante.setFocusPainted(false); 
        ComponentTitledBorder componentBorder_7 = 
                new ComponentTitledBorder(chbAcompanante ,pnlAcompanante
                , BorderFactory.createEtchedBorder()); 
        chbAcompanante.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                /*actionPerformed_chk(chbAcompanante ,pnlAcompanante);
                if(chbAcompanante.isSelected())
                flagDNIAcom=true;
                else
                flagDNIAcom=false;*/
                if(chbAcompanante.isSelected()){ // si seleccionó el check
                    actionPerformed_chk(chbAcompanante ,pnlAcompanante);
                    //if(chbAcompanante.isSelected())
                    flagDNIAcom=true;
                    /*else
                    flagDNIAcom=false;*/
                }else{ //si desseleccionó el check
                    if(JConfirmDialog.rptaConfirmDialog(this, "Se perderán los cambios realizados en los Datos de Acompañante\n"+         
                                    FarmaUtility.completeWithSymbol("¿Desea Continuar?",54 , " ", "I") )){   
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbTipAcompanante, "cmbTipoAcompanante", ConstantsCentroMedico.COMBOBOX_ACOMPANANTE_NA);
                        ///FarmaLoadCVL.setSelectedValueInComboBox(cmbTipAcompanante, "cmbTipoAcompanante","");
                        txtNomAcompanante.setText("");
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbTipDocAcom, "cmbTipoDocumentoAcom", ConstantsCentroMedico.COMBOBOX_DNIPACIENTE_NA);    
                        //FarmaLoadCVL.setSelectedValueInComboBox(cmbTipDocAcom, "cmbTipoDocumentoAcom","");
                        txtNumdocAcom.setText("");
                        actionPerformed_chk(chbAcompanante ,pnlAcompanante);
                        /*if(chbAcompanante.isSelected())
                        flagDNIAcom=true;
                        else*/
                        flagDNIAcom=false;
                    }else
                     chbAcompanante.setSelected(true);
                }
            } 
        }); 
        pnlAcompanante.setBorder(componentBorder_7);
        //////////////////////////////////////////////////////
        
        
        chbDatoAdicional.setFocusPainted(false); 
        ComponentTitledBorder componentBorder_8 = 
                new ComponentTitledBorder(chbDatoAdicional ,pnlDatoAdicional
                , BorderFactory.createEtchedBorder()); 
        chbDatoAdicional.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                if(chbDatoAdicional.isSelected()){ // si seleccionó el check
                    actionPerformed_chk(chbDatoAdicional ,pnlDatoAdicional);
                }else{ //si desseleccionó el check
                    if(JConfirmDialog.rptaConfirmDialog(this, "Se perderán los cambios realizados en los Datos Adicionales\n"+         
                                    FarmaUtility.completeWithSymbol("¿Desea Continuar?",52 , " ", "I") )){   
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbTipGradoIns,"cmbTipoGradoInstruc", ConstantsCentroMedico.COMBOBOX_GRADOINSTRUCCION_NR);
                        txtOcupacion.setText("");
                        txtCentroEL.setText("");
                        actionPerformed_chk(chbDatoAdicional ,pnlDatoAdicional);
                    }else
                     chbDatoAdicional.setSelected(true);
                }
            } 
        }); 
        pnlDatoAdicional.setBorder(componentBorder_8);
        //////////////////////////////////////////////////////
        
        
        
        
    }
    
    
  private void actionPerformed_chk(JCheckBox checkbox,JPanel panel){
        boolean enable = checkbox.isSelected();
        actionPerformed_chk(enable,panel);
  }
  private void actionPerformed_chk(boolean enable,JPanel panel){
      Component comp[] = panel.getComponents(); 
      for(int i = 0; i<comp.length; i++){ 
          comp[i].setEnabled(enable); 
      } 
  }
    
    
    //Referencia DlgMantClienteDireccionNew
    
    private void cmbDptoUbigeo_itemStateChanged(ItemEvent e,JComboBox comboDpto,JComboBox comboProvi) {
        cargaProvincias(comboDpto,comboProvi);
    }
    private void cmbProviUbigeo_itemStateChanged(ItemEvent e,JComboBox comboDpto,JComboBox comboProvi,JComboBox comboDist) {
        cargaDistritos(comboDpto,comboProvi,comboDist);
    }
    private void cmbDptoLugNac_itemStateChanged(ItemEvent e,JComboBox comboDpto,JComboBox comboProvi) {
        cargaProvincias(comboDpto,comboProvi);
    }
    private void cmbProviLugNac_itemStateChanged(ItemEvent e,JComboBox comboDpto,JComboBox comboProvi,JComboBox comboDist) {
        cargaDistritos(comboDpto,comboProvi,comboDist);
    }
    private void cmbDptoLugPro_itemStateChanged(ItemEvent e,JComboBox comboDpto,JComboBox comboProvi) {
        cargaProvincias(comboDpto,comboProvi);
    }
    private void cmbProviLugPro_itemStateChanged(ItemEvent e,JComboBox comboDpto,JComboBox comboProvi,JComboBox comboDist) {
        cargaDistritos(comboDpto,comboProvi,comboDist);
    }
    
    private void cargaDptos() {
        ArrayList parametros = new ArrayList();
        FarmaLoadCVL.loadCVLFromSP(cmbDptoUbigeo, cmbDptoUbigeo.getName(), "PTOVENTA_DEL_CLI.GET_DPTOS",
                                   parametros, false, true);
        FarmaLoadCVL.loadCVLFromSP(cmbDptoLugNac, cmbDptoLugNac.getName(), "PTOVENTA_DEL_CLI.GET_DPTOS",
                                   parametros, false, true);
        FarmaLoadCVL.loadCVLFromSP(cmbDptoLugPro, cmbDptoLugPro.getName(), "PTOVENTA_DEL_CLI.GET_DPTOS",
                                   parametros, false, true);
    }


    private void cargaProvincias(JComboBox comboDpto,JComboBox comboProvi) {
        //log.info("** cargaProvincias **");
        //log.info("comboDpto : "+comboDpto.getName()+"  --  "+"  comboProvi : "+comboProvi.getName());
        String vsCodDpto;
        ArrayList parametros = new ArrayList();
        
        vsCodDpto = FarmaLoadCVL.getCVLCode(comboDpto.getName(), comboDpto.getSelectedIndex());
        parametros.add(vsCodDpto);
        FarmaLoadCVL.unloadCVL(comboProvi, comboProvi.getName());
        FarmaLoadCVL.loadCVLFromSP(comboProvi, comboProvi.getName(), "PTOVENTA_DEL_CLI.GET_PROVI(?)",
                                   parametros, false, true);
    }

    private void cargaDistritos(JComboBox comboDpto,JComboBox comboProvi,JComboBox comboDist) {
        //log.info("** cargaDistritos **");
        //log.info("comboDpto : "+comboDpto.getName()+"  --  "+"  comboProvi : "+comboProvi.getName()+"  --  "+"  comboDist : "+comboDist.getName());
        String vsCodDpto, vsCodProvi;
        ArrayList parametros = new ArrayList();
        
        vsCodDpto = FarmaLoadCVL.getCVLCode(comboDpto.getName(), comboDpto.getSelectedIndex());
        vsCodProvi = FarmaLoadCVL.getCVLCode(comboProvi.getName(), comboProvi.getSelectedIndex());
        parametros.add(vsCodDpto);
        parametros.add(vsCodProvi);

        FarmaLoadCVL.unloadCVL(comboDist, comboDist.getName());
        FarmaLoadCVL.loadCVLFromSP(comboDist, comboDist.getName(), "PTOVENTA_DEL_CLI.GET_DIST(?,?)",
                                   parametros, false, true);

    }
    
    public void setUbigeoLocal() {
        String presultado = "";
        ArrayList parametros;
        try {
            presultado = DBDelivery.getUbigeoLocal();
            String[] pCadena = presultado.split("@");
            
            if (pCadena.length == 3) {
                String pCodDep = pCadena[0].trim();
                String pCodDistri = pCadena[1].trim();
                String pCodProv = pCadena[2].trim();
            
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDptoUbigeo, cmbDptoUbigeo.getName(), pCodDep.trim());
                
                cargaProvincias(cmbDptoUbigeo,cmbProviUbigeo);
                FarmaLoadCVL.setSelectedValueInComboBox(cmbProviUbigeo, cmbProviUbigeo.getName(), pCodProv.trim());
                cargaDistritos(cmbDptoUbigeo,cmbProviUbigeo,cmbDistUbigeo);
                //FarmaLoadCVL.setSelectedValueInComboBox(cmbDistUbigeo, cmbDistUbigeo.getName(), pCodDistri.trim());
                /*parametros = new ArrayList();
                parametros.add(pCodDep);
                FarmaLoadCVL.unloadCVL(cmbProviUbigeo, cmbProviUbigeo.getName());
                FarmaLoadCVL.loadCVLFromSP(cmbProviUbigeo, cmbProviUbigeo.getName(), "PTOVENTA_DEL_CLI.GET_PROVI(?)",
                                           parametros, false, true);
                FarmaLoadCVL.setSelectedValueInComboBox(cmbProviUbigeo, cmbProviUbigeo.getName(), pCodProv.trim());
                
                parametros = new ArrayList();
                parametros.add(pCodDep);
                parametros.add(pCodProv);
                FarmaLoadCVL.unloadCVL(cmbDistUbigeo, cmbDistUbigeo.getName());
                FarmaLoadCVL.loadCVLFromSP(cmbDistUbigeo, cmbDistUbigeo.getName(), "PTOVENTA_DEL_CLI.GET_DIST(?,?)",
                                           parametros, false, true);
                FarmaLoadCVL.setSelectedValueInComboBox(cmbDistUbigeo, cmbDistUbigeo.getName(), pCodDistri.trim());*/
            }

        } catch (Exception sql) {
            log.error("", sql);
        }   
        
    }

    private void txtFecNacimiento_keyTyped(KeyEvent e) {
        //FarmaUtility.dateComplete(txtFechNac, e);
        
        char keyChar = e.getKeyChar();

        if (Character.isDigit(keyChar) ||  keyChar == e.VK_SLASH){
        
            if (Character.isDigit(keyChar)) {
                if (txtFecNacimiento.getText().trim().length() == 2) {
                    txtFecNacimiento.setText(txtFecNacimiento.getText().trim() + "/");
                } else {
                    log.debug("fecha 2 " + txtFecNacimiento.getText().trim());
                }
            }
            
        }else{
            e.consume();
        }
        
    }
    
    private void txtFecHCAnterior_keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (!(Character.isDigit(keyChar) ||  keyChar == e.VK_SLASH))
            e.consume();
        
    }
    

    private void evento_f11() {


        if(tipoAtencion == TipoAtencionCM.ADMISION){ 
           if(!(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_SINMODIFICAR))){ // modo lectura
                if(validaDatos()){
                  if(JConfirmDialog.rptaConfirmDialog(this, "¿Desea grabar los datos del Paciente?")){
                    if(grabar()){
                       if(!(tipoPaciente.equals(ConstantsCentroMedico.TIPO_PACIENTE_ACTUALIZAR))){
                            DlgADMConsultaMedica dlgConMed = new DlgADMConsultaMedica(MyParentFrame,"",true);
                            dlgConMed.setLocationRelativeTo(MyParentFrame);
                            dlgConMed.setVisible(true);
                                           
                            if(FarmaVariables.vAceptar){
                                cerrarVentana(true);
                            }
                       }else{
                           FarmaUtility.showMessage(this,"Datos del paciente actualizados correctamente.",null);
                           cerrarVentana(true);
                       }
                    }
                }
              } 
           }else{
               log.info("VariablesCentroMedico.vCodPaciente : " + VariablesCentroMedico.vCodPaciente);
               DlgADMConsultaMedica dlgConMed = new DlgADMConsultaMedica(MyParentFrame,"",true);
               dlgConMed.setLocationRelativeTo(MyParentFrame);
               dlgConMed.setVisible(true);
                              
               if(FarmaVariables.vAceptar){
                   cerrarVentana(true);
               }
           }
        }else{
            if(tipoAtencion == TipoAtencionCM.CONSULTA){
                cerrarVentana(true);
            }     
        }                               

    }
    
    private void mostrarDatosPaciente() {
    
        String vdni = txtNumDoc.getText().trim();
        try {
            if(!vdni.equals("")){
                txtEdad.setText("");
                ArrayList lista = UtilityAdmisionMedica.obtenerDatosCliente(vdni);
                if(lista.size() > 0){
                    log.info("datos de reniec");
                    //txtNumDoc.setText(((String)((ArrayList)lista.get(0)).get(0)).trim());
                    txtNombre.setText(((String)((ArrayList)lista.get(0)).get(1)).trim());
                    txtApePaterno.setText(((String)((ArrayList)lista.get(0)).get(2)).trim());
                    txtApeMaterno.setText(((String)((ArrayList)lista.get(0)).get(3)).trim());
                    txtDireccion.setText(((String)((ArrayList)lista.get(0)).get(6)).trim());
                    txtTelFijo.setText(((String)((ArrayList)lista.get(0)).get(7)).trim());
                    txtTelCel.setText(((String)((ArrayList)lista.get(0)).get(8)).trim());
                    txtCorreo.setText(((String)((ArrayList)lista.get(0)).get(9)).trim());
                    
                    if( (!(txtTelFijo.getText().equals(""))) || 
                        (!(txtTelCel.getText().equals("")))  ||
                        (!(txtCorreo.getText().equals("")))  ){
                        chbDatosContacto.setSelected(true);
                    }else{
                        chbDatosContacto.setSelected(false);
                    }
                        actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                    
                    String sexo=((String)((ArrayList)lista.get(0)).get(4)).trim();
                    if(sexo.equals(ConstantsCentroMedico.COMBOBOX_SEXO_MASCULINO) || sexo.equals(ConstantsCentroMedico.COMBOBOX_SEXO_FEMENINO))
                        FarmaLoadCVL.setSelectedValueInComboBox(cmbTipSexo, "cmbTipoSexo",sexo);    
                    txtFecNacimiento.setText(((String)((ArrayList)lista.get(0)).get(5)).trim());
                    if(!(txtFecNacimiento.getText().trim().equals("")))
                                setEdad(txtFecNacimiento.getText().trim());
                    
                    //setUbigeoLocal();
                              
                }else if(auxNumDocPac.equals(txtNumDoc.getText().trim())) {
                    log.info("datos de beneficiario o paciente");
                    txtApePaterno.setText(paciente.getApellidoPaterno());
                    txtApeMaterno.setText(paciente.getApellidoMaterno());
                    txtNombre.setText(paciente.getNombre());
                    txtDireccion.setText(paciente.getDireccion());
                    txtTelFijo.setText(paciente.getTelFijo());
                    txtTelCel.setText(paciente.getTelCelular());
                    txtCorreo.setText(paciente.getEmail());
                    
                    if( (txtTelFijo.getText().equals("")) && 
                        (txtTelCel.getText().equals(""))  &&
                        (txtCorreo.getText().equals(""))  ){
                        chbDatosContacto.setSelected(false);
                    }else{
                        chbDatosContacto.setSelected(true);
                    }
                        actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                          
                    txtFecNacimiento.setText(paciente.getFecNacimiento());
                    if(!(txtFecNacimiento.getText().trim().equals("")))
                        setEdad(txtFecNacimiento.getText().trim());
                    
                    
                    if(!(paciente.getSexo().equals("")))
                    FarmaLoadCVL.setSelectedValueInComboBox(cmbTipSexo, "cmbTipoSexo",paciente.getSexo().trim());
                    else
                    FarmaLoadCVL.setSelectedValueInComboBox(cmbTipSexo, "cmbTipoSexo", ConstantsCentroMedico.COMBOBOX_SEXO_MASCULINO);        
    
                    /*if(!(paciente.getDepUbigeo().equals("")))
                    FarmaLoadCVL.setSelectedValueInComboBox(cmbDptoUbigeo, cmbDptoUbigeo.getName(),paciente.getDepUbigeo().trim());
                    if(!(paciente.getPvrUbigeo().equals("")))
                    FarmaLoadCVL.setSelectedValueInComboBox(cmbProviUbigeo,cmbProviUbigeo.getName(),paciente.getPvrUbigeo().trim());
                    if(!(paciente.getDisUbigeo().equals("")))
                    FarmaLoadCVL.setSelectedValueInComboBox(cmbDistUbigeo, cmbDistUbigeo.getName(),paciente.getDisUbigeo().trim());
                    */  

                }else{
                    log.info("No hay datos de reniec");
                    //txtNumDoc.setText(((String)((ArrayList)lista.get(0)).get(0)).trim());
                    txtNombre.setText("");
                    txtApePaterno.setText("");
                    txtApeMaterno.setText("");
                    txtDireccion.setText("");
                    txtTelFijo.setText("");
                    txtTelCel.setText("");
                    txtCorreo.setText("");
                    FarmaLoadCVL.setSelectedValueInComboBox(cmbTipSexo, "cmbTipoSexo", ConstantsCentroMedico.COMBOBOX_SEXO_MASCULINO);    
                    txtFecNacimiento.setText("");
                    //setUbigeoLocal();
                    
                    chbDatosContacto.setSelected(false);
                    actionPerformed_chk(chbDatosContacto ,pnlDatosContacto);
                }
                
            }
            
        } catch (Exception ex) {
            log.error("", ex);
        }
        
    }
    
}
