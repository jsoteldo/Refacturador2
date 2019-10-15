package mifarma.ptoventa.tomainventario;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

public class DlgFiltroEstado extends JDialog {
    public JComboBox cmbCampo = new JComboBox();
    public String vDirecOrden = "";
    JPanel pnlSeleccionar = new JPanel();
    JLabel lblAceptarT = new JLabel();
    JLabel lblCancelarT = new JLabel();
    JButton btnColumna = new JButton();

    public DlgFiltroEstado() {
        this(null, "", false);
    }

    public DlgFiltroEstado(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
            FarmaUtility.centrarVentana(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(271, 154));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        pnlSeleccionar.setBounds(new Rectangle(15, 9, 234, 87));
        pnlSeleccionar.setBorder(BorderFactory.createTitledBorder("Seleccione el estado "));
        pnlSeleccionar.setLayout(null);
        pnlSeleccionar.setFont(new Font("SansSerif", 0, 11));
        cmbCampo.setBounds(new Rectangle(90, 40, 125, 20));
        cmbCampo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbCampo_keyPressed(e);
            }
        });
        lblAceptarT.setText("[ Enter ] Aceptar");
        lblAceptarT.setBounds(new Rectangle(35, 100, 90, 20));
        lblAceptarT.setFont(new Font("SansSerif", 1, 11));
        lblAceptarT.setForeground(new Color(255, 130, 14));
        lblCancelarT.setText("[ Esc ] Cancelar");
        lblCancelarT.setBounds(new Rectangle(140, 100, 85, 20));
        lblCancelarT.setFont(new Font("SansSerif", 1, 11));
        lblCancelarT.setForeground(new Color(255, 130, 14));
        btnColumna.setText("Ordenar por :");
        btnColumna.setBounds(new Rectangle(15, 40, 75, 20));
        btnColumna.setBorder(BorderFactory.createLineBorder(new Color(212, 208, 200), 1));
        btnColumna.setBorderPainted(false);
        btnColumna.setHorizontalAlignment(SwingConstants.LEFT);
        btnColumna.setMnemonic('c');
        btnColumna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnColumna_actionPerformed(e);
            }
        });
        pnlSeleccionar.add(btnColumna, null);
        pnlSeleccionar.add(cmbCampo, null);
        this.getContentPane().add(lblCancelarT, null);
        this.getContentPane().add(lblAceptarT, null);
        this.getContentPane().add(pnlSeleccionar, null);
    }


    void this_windowOpened(WindowEvent e) {
        cmbCampo.requestFocus();
    }

    void cmbCampo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cancelaOperacion();
            VariablesTomaInv.vAceptaOrdenar = false;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            VariablesTomaInv.vAceptaOrdenar = true;
            this.setVisible(false);
            this.dispose();

        }

    }

    void cancelaOperacion() {
        cmbCampo.removeAllItems();
        this.dispose();
        this.setVisible(false);
    }


    void btnColumna_actionPerformed(ActionEvent e) {
        cmbCampo.requestFocus();
    }

    public void setTitleBorder(String title) {
        pnlSeleccionar.setBorder(BorderFactory.createTitledBorder(title));
    }

}
