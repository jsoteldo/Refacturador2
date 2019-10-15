package mifarma.ptoventa.administracion.impresoras;


import com.gs.mifarma.componentes.JConfirmDialog;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JLabelWhite;
import com.gs.mifarma.componentes.JPanelHeader;
import com.gs.mifarma.componentes.JPanelTitle;
import com.gs.mifarma.componentes.JPanelWhite;

import com.gs.mifarma.componentes.JTextFieldSanSerif;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.print.PrinterJob;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Vector;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import mifarma.common.FarmaLoadCVL;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

import mifarma.ptoventa.administracion.impresoras.reference.DBImpresoras;
import mifarma.ptoventa.administracion.impresoras.reference.VariablesImpresoras;

import mifarma.ptoventa.administracion.reference.UtilityAdministracion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DlgEditarIPSImpresora extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgEditarIPSImpresora.class);
    Frame myParentFrame;
    private JPanelWhite pnlFondo = new JPanelWhite();
    private CardLayout cardLayout1 = new CardLayout();
    private JPanelTitle pnlTitle = new JPanelTitle();
    private JPanelHeader pnlBody = new JPanelHeader();
    private JLabelFunction lblEsc = new JLabelFunction();
    private JLabelFunction lblEnter = new JLabelFunction();
    private JLabelFunction lblF5 = new JLabelFunction();
    private JLabelWhite lblTitle = new JLabelWhite();
    private JLabelWhite lblImpBoleta = new JLabelWhite();
    private JLabelWhite lblImpFactura = new JLabelWhite();
    private JLabelWhite lblImpTermica = new JLabelWhite();
    private JLabelWhite lblIP = new JLabelWhite();
    private JLabelWhite lblIP_T = new JLabelWhite();
    private JLabel lblImpBol_T = new JLabel();
    private JLabel lblImpFac_T = new JLabel();
    private JLabel lblImpTer_T = new JLabel();
    
    //KMONCADA 26.04.2016
    private JLabelWhite lblTipoLocalVenta = new JLabelWhite();
    private JLabel lblTipoLocalVenta_T = new JLabel();
    private JLabelWhite lblImpReceta = new JLabelWhite();
    private JTextFieldSanSerif txtImpReceta = new JTextFieldSanSerif();
    private boolean centroMedico = false;
    private JLabel lblMsjCambio = new JLabel();
    private String auxImpresoraReceta = "";


    public DlgEditarIPSImpresora() {
        this(null, "", false);
    }

    public DlgEditarIPSImpresora(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(591, 361));
        this.getContentPane().setLayout(cardLayout1);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        pnlFondo.setFocusable(false);
        pnlTitle.setBounds(new Rectangle(10, 10, 565, 25));
        pnlTitle.setFocusable(false);
        pnlBody.setBounds(new Rectangle(10, 35, 565, 255));
        pnlBody.setFocusable(false);
        lblEsc.setText("[ ESC ] Cerrar");
        lblEsc.setBounds(new Rectangle(440, 305, 135, 25));
        lblEsc.setFocusable(false);
        lblEnter.setText("[ Enter ] Cambiar"); // kmoncada cambia tecla de seleccion de campo
        lblEnter.setBounds(new Rectangle(10, 305, 125, 25));
        lblEnter.setFocusable(false);
        lblTitle.setText("Detalle de Impresoras configuradas para la IP");
        lblTitle.setBounds(new Rectangle(20, 0, 305, 25));
        lblTitle.setFocusable(false);
        lblImpBoleta.setText("Impresora Boletas:");
        lblImpBoleta.setBounds(new Rectangle(25, 55, 120, 20));
        lblImpBoleta.setFocusable(false);
        lblImpBoleta.setSize(new Dimension(120, 20));
        lblImpFactura.setText("Impresora Facturas:");
        lblImpFactura.setBounds(new Rectangle(25, 95, 125, 20));
        lblImpFactura.setFocusable(false);
        lblImpTermica.setText("Impresora Térmica:");
        lblImpTermica.setBounds(new Rectangle(25, 135, 130, 20));
        lblImpTermica.setFocusable(false);
        lblIP.setText("IP:");
        lblIP.setBounds(new Rectangle(25, 20, 110, 20));
        lblIP.setFocusable(false);
        lblIP_T.setText("___.___.___.___");
        lblIP_T.setBounds(new Rectangle(155, 20, 185, 20));
        lblIP_T.setFocusable(false);
        lblImpBol_T.setBounds(new Rectangle(150, 55, 395, 20));
        lblImpBol_T.setBackground(Color.white);
        lblImpBol_T.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        lblImpBol_T.setOpaque(true);
        lblImpBol_T.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                lblImpBol_T_focusGained(e);
            }

            public void focusLost(FocusEvent e) {
                lblImpBol_T_focusLost(e);
            }
        });
        lblImpBol_T.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblImpBol_T_keyPressed(e);
            }
        });
        lblImpFac_T.setBounds(new Rectangle(150, 95, 395, 20));
        lblImpFac_T.setBackground(Color.white);
        lblImpFac_T.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        lblImpFac_T.setOpaque(true);
        lblImpFac_T.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                lblImpFac_T_focusGained(e);
            }

            public void focusLost(FocusEvent e) {
                lblImpFac_T_focusLost(e);
            }
        });
        lblImpFac_T.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblImpFac_T_keyPressed(e);
            }
        });
        
        lblImpTer_T.setBounds(new Rectangle(150, 135, 395, 20));
        lblImpTer_T.setBackground(Color.white);
        lblImpTer_T.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        lblImpTer_T.setOpaque(true);
        lblImpTer_T.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                lblImpTer_T_focusGained(e);
            }

            public void focusLost(FocusEvent e) {
                lblImpTer_T_focusLost(e);
            }
        });
        lblImpTer_T.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblImpTer_T_keyPressed(e);
            }
        });
        
        lblF5.setText("[ F5 ] Desasignar");
        lblF5.setBounds(new Rectangle(145, 305, 125, 25));
        
        lblTipoLocalVenta.setText("Tipo Local Venta:");
        lblTipoLocalVenta.setBounds(new Rectangle(25, 175, 130, 20));
        lblTipoLocalVenta.setFocusable(false);
        
        lblImpTer_T.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblImpTer_T_keyPressed(e);
            }
        });
        
        lblTipoLocalVenta_T.setBounds(new Rectangle(150, 175, 395, 20));
        lblTipoLocalVenta_T.setFont(new Font("SansSerif", 1, 12));
        lblTipoLocalVenta_T.setBackground(Color.white);
        lblTipoLocalVenta_T.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        lblTipoLocalVenta_T.setOpaque(true);
        lblTipoLocalVenta_T.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                lblTipoLocalVenta_T_focusGained(e);
            }

            public void focusLost(FocusEvent e) {
                lblTipoLocalVenta_T_focusLost(e);
            }
        });
        lblTipoLocalVenta_T.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblTipoLocalVenta_T_keyPressed(e);
            }
        });


        lblImpReceta.setText("Impresora Receta Digital:");
        lblImpReceta.setBounds(new Rectangle(25, 215, 145, 20));
        txtImpReceta.setBounds(new Rectangle(180, 215, 365, 20));

        txtImpReceta.setFont(new Font("SansSerif", 1, 12));
        txtImpReceta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                lblImpBol_T_keyPressed(e);
            }
        });
        txtImpReceta.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                focoGanado(e);
            }

            public void focusLost(FocusEvent e) {
                focoPerdido(e);
            }
        });
        lblMsjCambio.setText("Presione ENTER para grabar");
        lblMsjCambio.setBounds(new Rectangle(180, 235, 365, 15));
        lblMsjCambio.setFont(new Font("SansSerif", 1, 11));
        lblMsjCambio.setForeground(SystemColor.window);
        pnlFondo.add(lblF5, null);
        pnlFondo.add(lblEnter, null);
        pnlFondo.add(lblEsc, null);
        pnlBody.add(lblMsjCambio, null);
        pnlBody.add(lblImpReceta, null);
        pnlBody.add(txtImpReceta, null);
        pnlBody.add(lblImpTer_T, null);
        pnlBody.add(lblImpFac_T, null);
        pnlBody.add(lblImpBol_T, null);
        pnlBody.add(lblIP_T, null);
        pnlBody.add(lblIP, null);
        pnlBody.add(lblImpTermica, null);
        pnlBody.add(lblImpFactura, null);
        pnlBody.add(lblImpBoleta, null);
        pnlBody.add(lblTipoLocalVenta, null);
        pnlBody.add(lblTipoLocalVenta_T, null);
        pnlFondo.add(pnlBody, null);
        pnlTitle.add(lblTitle, null);
        pnlFondo.add(pnlTitle, null);
        this.getContentPane().add(pnlFondo, "pnlFondo");

        

        FarmaUtility.centrarVentana(this);
        this.setResizable(false);
    }

    public void setDatos(String ip) {
        lblIP_T.setText(ip);
    }
    
    private void configurarTeclasDesplazamiento(){
        Vector<Component> order = new Vector<Component>(3);
        order.add(lblImpBol_T);
        order.add(lblImpFac_T);
        order.add(lblImpTer_T);
        order.add(lblTipoLocalVenta_T);
        if(isCentroMedico())
            order.add(txtImpReceta);
        MyOwnFocusTraversalPolicy newPolicy = new MyOwnFocusTraversalPolicy(order);
        this.setFocusTraversalPolicy(newPolicy);
    }

    private void cargarImpresoras() {
        ArrayList temp = new ArrayList();
        try {
            DBImpresoras.getImpresorasIP(lblIP_T.getText(), temp);

            ArrayList temp2 = (ArrayList)temp.get(0);

            lblImpBol_T.setText(temp2.get(0).toString());
            lblImpFac_T.setText(temp2.get(1).toString());
            lblImpTer_T.setText(temp2.get(2).toString());
            // KMONCADA 26.04.2016 ARCANGEL-CENTRO MEDICO
            lblTipoLocalVenta_T.setText(temp2.get(3).toString());
            //KMONCADA 26.04.2016
            txtImpReceta.setText(temp2.get(4).toString().trim());
            mostrarCamposReceta(temp2.get(5).toString());
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    private void mostrarCamposReceta(String indCentroMedico){
        setCentroMedico("S".equalsIgnoreCase(indCentroMedico));
        int valor = 0;
        if(isCentroMedico())
            valor = 40;
        this.setSize(new Dimension(591, (321+valor)));
        lblF5.setBounds(new Rectangle(145, (265+valor), 125, 25));
        lblEsc.setBounds(new Rectangle(440, (265+valor), 135, 25));
        lblEnter.setBounds(new Rectangle(10, (265+valor), 125, 25));
        pnlBody.setBounds(new Rectangle(10, 35, 565, (215+valor)));
        lblImpReceta.setVisible(isCentroMedico());
        txtImpReceta.setVisible(isCentroMedico());
        txtImpReceta.setEditable(false);
        lblMsjCambio.setVisible(false);
        repaint();
    }

    private void chkKeyPressed(KeyEvent e) {
        if(e.getSource() instanceof JLabel)
            e.consume();

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            cambiarFoco(e); // kmoncada control de teclado
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
        /*else if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            ((JComponent)e.getSource()).transferFocus();
        }*/
        else if (e.getKeyCode() == KeyEvent.VK_F5) {
            if (e.getSource() == lblImpBol_T) {
                if (JConfirmDialog.rptaConfirmDialog(this,
                                                     "Esta seguro de eliminar la asignacion de la impresora de boletas?")) {
                    quitarAsignacion("B");
                    cargarImpresoras();
                }
            } else if (e.getSource() == lblImpFac_T) {
                if (JConfirmDialog.rptaConfirmDialog(this,
                                                     "Esta seguro de eliminar la asignacion de la impresora de facturas?")) {
                    quitarAsignacion("F");
                    cargarImpresoras();
                }
            } else if (e.getSource() == lblImpTer_T) {
                if (JConfirmDialog.rptaConfirmDialog(this,
                                                     "Esta seguro de eliminar la asignacion de la impresora termica?")) {
                    quitarAsignacion("T");
                    cargarImpresoras();
                }
            }else if (e.getSource() == txtImpReceta) {
                if (JConfirmDialog.rptaConfirmDialog(this, "Esta seguro de eliminar la asignacion de la impresora de receta?")) {
                    quitarAsignacion("R");
                    cargarImpresoras();
                }
            }
        } else if (e.getKeyCode() ==
                   KeyEvent.VK_ENTER) //UtilityPtoVenta.verificaVK_F11(e)) //e.getKeyCode() == KeyEvent.VK_F11)
        {
            if (e.getSource() == lblImpBol_T) {
                DlgListaImpresoraMaquina dlglista = new DlgListaImpresoraMaquina(this.myParentFrame, "", true);
                dlglista.setValores("B");
                dlglista.setVisible(true);
                cargarImpresoras();
            } else if (e.getSource() == lblImpFac_T) {
                DlgListaImpresoraMaquina dlglista = new DlgListaImpresoraMaquina(this.myParentFrame, "", true);
                dlglista.setValores("F");
                dlglista.setVisible(true);
                cargarImpresoras();
            } else if (e.getSource() == lblImpTer_T) {
                DlgListaImpresoraTermica dlglista = new DlgListaImpresoraTermica(this.myParentFrame, "", true);
                dlglista.setVisible(true);
                cargarImpresoras();
            } else if (e.getSource() == lblTipoLocalVenta_T) {
                DlgListaLocalTipoVenta dlglista = new DlgListaLocalTipoVenta(this.myParentFrame, "Tipo Local Venta", true);
                dlglista.setIpPc(lblIP_T.getText());
                dlglista.setVisible(true);
                cargarImpresoras();
            } else if (e.getSource() == txtImpReceta) {
                if(lblMsjCambio.isVisible()){
                    String nombreImpresora = txtImpReceta.getText();
                    if(nombreImpresora!=null && nombreImpresora.trim().length()>0){
                        if(validarImpresora(nombreImpresora)){
                            if(UtilityAdministracion.actualizarIPImpresoraRecetaDigital(this, lblIP_T.getText(), nombreImpresora)){
                                cargarImpresoras();
                                txtImpReceta.transferFocus();
                            }
                        }else{
                            txtImpReceta.setText("");
                            FarmaUtility.showMessage(this, "No se ha encontrado en el nombre de la Impresora\n"+
                                                           "Verifique en las impresoras configurada a su PC y reintente.", txtImpReceta);
                        }
                    }
                }else{
                    auxImpresoraReceta = txtImpReceta.getText();
                    lblMsjCambio.setVisible(true);
                    txtImpReceta.setEditable(true);
                }
            }
        }
    }
    
    private boolean validarImpresora(String nombreImpresora){
        boolean valido = false;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        if(services.length != 0 || services != null){
            for(PrintService service : services){
                String existingPrinter = service.getName().toUpperCase();
                if(existingPrinter.equals(nombreImpresora.toUpperCase())){
                    valido = true;
                    break;
                }
            }
        } 
        return valido;
    }
    private void quitarAsignacion(String indTipoComp) {
        String Sec = VariablesImpresoras.vSecIP;
        String IP = lblIP_T.getText();
        try {
            DBImpresoras.quitarAsignacion(Sec + "", indTipoComp);
            FarmaUtility.aceptarTransaccion();
            FarmaUtility.showMessage(this, "Actualización exitosa.", null);
        } catch (SQLException sql) {
            //log.error("",sql);
            FarmaUtility.showMessage(this, "Ocurrió un error al eliminar asignación.\n" +
                    sql.getMessage(), null);
        }
    }

    private void cerrarVentana(boolean pAceptar) {
        FarmaVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private void this_windowOpened(WindowEvent e) {
        cargarImpresoras();
        configurarTeclasDesplazamiento();
        lblImpBol_T.grabFocus();
    }
    
    private void focoPerdido(FocusEvent e){
        ((JComponent)e.getSource()).setBackground(Color.white);
        if(e.getSource() == txtImpReceta && lblMsjCambio.isVisible()){
            txtImpReceta.setText(auxImpresoraReceta);
            lblMsjCambio.setVisible(false);
            txtImpReceta.setEditable(false);
        }
    }
    
    private void focoGanado(FocusEvent e){
        ((JComponent)e.getSource()).setBackground(Color.yellow);
    }

    private void lblFocusGained(FocusEvent e) {
        ((JComponent)e.getSource()).setBackground(Color.yellow);
    }

    private void lblFocusLost(FocusEvent e) {
        ((JComponent)e.getSource()).setBackground(Color.white);
    }

    private void lblImpBol_T_focusGained(FocusEvent e) {
        lblFocusGained(e);
    }

    private void lblImpFac_T_focusGained(FocusEvent e) {
        lblFocusGained(e);
    }

    private void lblImpTer_T_focusGained(FocusEvent e) {
        lblFocusGained(e);
    }
    
    private void lblTipoLocalVenta_T_focusGained(FocusEvent e) {
        lblFocusGained(e);
    }
    
    private void lblImpBol_T_focusLost(FocusEvent e) {
        lblFocusLost(e);
    }

    private void lblImpFac_T_focusLost(FocusEvent e) {
        lblFocusLost(e);
    }

    private void lblImpTer_T_focusLost(FocusEvent e) {
        lblFocusLost(e);
    }
    
    private void lblTipoLocalVenta_T_focusLost(FocusEvent e) {
        lblFocusLost(e);
    }
    
    
    private void lblImpBol_T_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void lblImpFac_T_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void lblImpTer_T_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    // kmoncada permite realizar el cambio de foco entre los textfields

    private void cambiarFoco(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            ((JComponent)evt.getSource()).transferFocus();
        } else {
            ((JComponent)evt.getSource()).transferFocusBackward();
        }
    }
    
    private void lblTipoLocalVenta_T_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    public boolean isCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(boolean centroMedico) {
        this.centroMedico = centroMedico;
    }

    public static class MyOwnFocusTraversalPolicy extends FocusTraversalPolicy {
        Vector<Component> order;

        public MyOwnFocusTraversalPolicy(Vector<Component> order) {
            this.order = new Vector<Component>(order.size());
            this.order.addAll(order);
        }

        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            int idx = (order.indexOf(aComponent) + 1) % order.size();
            return order.get(idx);
        }

        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            int idx = order.indexOf(aComponent) - 1;
            if (idx < 0) {
                idx = order.size() - 1;
            }
            return order.get(idx);
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
            return order.get(0);
        }

        public Component getLastComponent(Container focusCycleRoot) {
            return order.lastElement();
        }

        public Component getFirstComponent(Container focusCycleRoot) {
            return order.get(0);
        }
    }
}
