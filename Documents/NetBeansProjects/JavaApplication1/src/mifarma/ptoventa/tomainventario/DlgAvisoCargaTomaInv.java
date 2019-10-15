package mifarma.ptoventa.tomainventario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

// INICIO AITURRIZAGA
public class DlgAvisoCargaTomaInv extends JDialog {

    Frame parentFrame;
    public static boolean aceptarTransaccion = false;
    //DlgListaTomaInventario toma = new DlgListaTomaInventario();
    ImageIcon imageIcono = new ImageIcon(getClass().getResource("images/danger64.png"));
    private JLabel lblTitulo1 = new JLabel();
    private JPanel pnlMain = new JPanel();
    private JButton btnAceptar = new JButton();
    private JButton btnCancel = new JButton();
    private JLabel lblImagen = new JLabel();


    public DlgAvisoCargaTomaInv() {
        this(null, "", false);
    }

    public DlgAvisoCargaTomaInv(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
            FarmaUtility.centrarVentana(this);
            parentFrame = parent;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(391, 159));
        this.getContentPane().setLayout(null);
        lblTitulo1.setText("");
        lblTitulo1.setFont(new Font("SansSerif", 1, 16));
        lblTitulo1.setBounds(new Rectangle(85, 20, 270, 60));
        pnlMain.setBounds(new Rectangle(0, 0, 390, 135));
        pnlMain.setBackground(new Color(255, 213, 79));
        pnlMain.setLayout(null);
        pnlMain.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        btnAceptar.setText("Aceptar");
        btnAceptar.setBounds(new Rectangle(265, 90, 85, 30));
       /* btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAceptar_actionPerformed(e);
            }
        });*/
        btnAceptar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                btnAceptar_keyPressed(e);
            }
        });
        btnAceptar.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    btnAceptar_mouseClicked(e);
                }
            });
        btnCancel.setText("Cancelar");
        btnCancel.setBounds(new Rectangle(155, 90, 90, 30));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancel_actionPerformed(e);
            }
        });
        btnCancel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    btnCancel_keyPressed(e);
                }
        });
        btnCancel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    btnCancel_mouseClicked(e);
                }
            });
        lblImagen.setBounds(new Rectangle(20, 25, 60, 55));
        lblImagen.setIcon(imageIcono);
        pnlMain.add(lblImagen, null);
        pnlMain.add(lblTitulo1, null);
        pnlMain.add(btnAceptar, null);
        pnlMain.add(btnCancel, null);
        this.getContentPane().add(pnlMain, null);

    }

    private void btnCancel_actionPerformed(ActionEvent e) {
        
    }
/*
    private void btnAceptar_actionPerformed(ActionEvent e) {
        aceptarTransaccion = true;
        this.dispose();
    }

*/
    public void setAviso(String mensaje) {
        lblTitulo1.setText(mensaje);
    }

    // FIN AITURRIZAGA
    //DFLORES: 07/02/2019

    private void btnCancel_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            JOptionPane.showMessageDialog(this, "Acción cancelada!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            aceptarTransaccion = false;
            this.setVisible(false);
            this.dispose();
        }
    }

    void cerrarVentana() {
        aceptarTransaccion = false;
        this.setVisible(false);
        this.dispose();
        VariablesTomaInv.vNumeroTomaInventario = "";
        VariablesTomaInv.vDescripLaboratorio = "";
        VariablesTomaInv.vDiferenciaFecha = "";
    }
    //FIN DFLORES

    private void btnAceptar_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            aceptarTransaccion = false;
            cerrarVentana();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            aceptarTransaccion = true;
            this.dispose();
        }
    }

    private void btnCancel_mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(this, "Acción cancelada!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        aceptarTransaccion = false;
        this.setVisible(false);
        this.dispose();
    }

    private void btnAceptar_mouseClicked(MouseEvent e) {
        aceptarTransaccion = true;
        this.dispose();
    }
}
