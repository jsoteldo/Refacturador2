package mifarma.ptoventa.tomainventario;


import com.gs.mifarma.componentes.JButtonLabel;
import com.gs.mifarma.componentes.JLabelFunction;
import com.gs.mifarma.componentes.JPanelTitle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaTableModel;
import mifarma.common.FarmaUtility;

import mifarma.common.FarmaVariables;

import mifarma.ptoventa.reference.UtilityPtoVenta;
import mifarma.ptoventa.tomainventario.reference.ConstantsTomaInv;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***/
//import mifarma.ptoventa.inventariociclico.reference.VariablesInvCiclico;

public class DlgListaHistoricoTomas extends JDialog {
    private static final Logger log = LoggerFactory.getLogger(DlgListaHistoricoTomas.class);

    FarmaTableModel tableModelHistoricoTomas;

    Frame myParentFrame;

    private BorderLayout borderLayout1 = new BorderLayout();

    private JPanel jContentPane = new JPanel();

    private JPanelTitle jPanelTitle1 = new JPanelTitle();

    private JScrollPane jScrollPane1 = new JScrollPane();

    private JTable tblRelacionHistoricoTomas = new JTable();

    private JButtonLabel btnRelacionHistoricosTomas = new JButtonLabel();

    private JLabelFunction lblF1 = new JLabelFunction();

    private JLabelFunction lblEscape = new JLabelFunction();
    private JLabelFunction jLabelFunction1 = new JLabelFunction();

    // **************************************************************************
    // Constructores
    // **************************************************************************

    public DlgListaHistoricoTomas() {
        this(null, "", false);
    }

    public DlgListaHistoricoTomas(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        myParentFrame = parent;
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    // **************************************************************************
    // M�todo "jbInit()"
    // **************************************************************************

    private void jbInit() throws Exception {
        this.setSize(new Dimension(617, 388));
        this.getContentPane().setLayout(borderLayout1);
        this.setTitle("Historico de Tomas de Inventario");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jContentPane.setBackground(Color.white);
        jContentPane.setLayout(null);
        jPanelTitle1.setBounds(new Rectangle(15, 10, 585, 30));
        jScrollPane1.setBounds(new Rectangle(15, 40, 585, 270));
        tblRelacionHistoricoTomas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblRelacionHistoricoTomas_keyPressed(e);
            }
        });
        btnRelacionHistoricosTomas.setText("Relacion de Historicos de Tomas de Inventario");
        btnRelacionHistoricosTomas.setBounds(new Rectangle(10, 5, 275, 20));
        btnRelacionHistoricosTomas.setMnemonic('r');
        btnRelacionHistoricosTomas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionHistoricosTomas_actionPerformed(e);
            }
        });
        lblF1.setBounds(new Rectangle(15, 320, 125, 20));
        lblF1.setText("[ ENTER ] Seleccionar");
        lblF1.setBackground(SystemColor.window);
        lblF1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblF1.setFont(new Font("SansSerif", 1, 11));
        lblF1.setForeground(new Color(32, 105, 29));
        lblEscape.setBounds(new Rectangle(485, 320, 115, 20));
        lblEscape.setText("[ ESCAPE ] Cerrar");
        lblEscape.setBackground(SystemColor.window);
        lblEscape.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblEscape.setFont(new Font("SansSerif", 1, 11));
        lblEscape.setForeground(new Color(32, 105, 29));
        jLabelFunction1.setBounds(new Rectangle(150, 320, 230, 20));
        jLabelFunction1.setText("[ F2 ] Ver Diferencias de Productos");
        jLabelFunction1.setBackground(SystemColor.window);
        jLabelFunction1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelFunction1.setFont(new Font("SansSerif", 1, 11));
        jLabelFunction1.setForeground(new Color(32, 105, 29));
        jScrollPane1.getViewport().add(tblRelacionHistoricoTomas, null);
        jContentPane.add(jLabelFunction1, null);
        jContentPane.add(lblEscape, null);
        jContentPane.add(lblF1, null);
        jContentPane.add(jScrollPane1, null);
        jPanelTitle1.add(btnRelacionHistoricosTomas, null);
        jContentPane.add(jPanelTitle1, null);
        this.getContentPane().add(jContentPane, BorderLayout.CENTER);
    }

    // **************************************************************************
    // M�todo "initialize()"
    // **************************************************************************

    private void initialize() {
        FarmaVariables.vAceptar = false;
        initTableListaHistoricoTomas();
    }

    // **************************************************************************
    // M�todos de inicializaci�n
    // **************************************************************************

    private void initTableListaHistoricoTomas() {
        tableModelHistoricoTomas =
                new FarmaTableModel(ConstantsTomaInv.columnsHistoricoTomas, ConstantsTomaInv.defaultValuesHistoricoTomas,
                                    0);
        FarmaUtility.initSimpleList(tblRelacionHistoricoTomas, tableModelHistoricoTomas,
                                    ConstantsTomaInv.columnsHistoricoTomas);
        cargaHistoricoTomas();
    }

    // **************************************************************************
    // Metodos de eventos
    // **************************************************************************

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
        FarmaUtility.moveFocus(tblRelacionHistoricoTomas);
    }

    private void tblRelacionHistoricoTomas_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }

    private void btnRelacionHistoricosTomas_actionPerformed(ActionEvent e) {
        FarmaUtility.moveFocus(tblRelacionHistoricoTomas);
    }

    // **************************************************************************
    // Metodos auxiliares de eventos
    // **************************************************************************

    private void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tieneRegistroSeleccionado(tblRelacionHistoricoTomas)) {
                cargarRegistroSeleccionado();
                VariablesTomaInv.vTipOp = ConstantsTomaInv.TIPO_OPERACION_CONSULTA_HIST;
                DlgLaboratoriosPorToma dlgLaboratoriosPorToma =
                    new DlgLaboratoriosPorToma(myParentFrame, "", true, true);
                dlgLaboratoriosPorToma.setVisible(true);
            }
        } else if (UtilityPtoVenta.verificaVK_F2(e)) {
            int filaSelect = tblRelacionHistoricoTomas.getSelectedRow();
            //VariablesInvCiclico.vSecToma = (String) tblRelacionHistoricoTomas.getValueAt(filaSelect,0);
            VariablesTomaInv.vSecToma = (String)tblRelacionHistoricoTomas.getValueAt(filaSelect, 0);
            DlgListaDiferenciasConsolidado dlgListaDif = new DlgListaDiferenciasConsolidado(myParentFrame, "", true);
            dlgListaDif.setVisible(true);
        }
    }

    // **************************************************************************
    // Metodos de l�gica de negocio
    // **************************************************************************

    private void cargaHistoricoTomas() {
        try {
            DBTomaInv.getListaTomasHist(tableModelHistoricoTomas);
            if (tblRelacionHistoricoTomas.getRowCount() > 0)
                FarmaUtility.ordenar(tblRelacionHistoricoTomas, tableModelHistoricoTomas, 0,
                                     FarmaConstants.ORDEN_DESCENDENTE);
        } catch (SQLException sql) {
            FarmaUtility.showMessage(this, "Ocurri� un error al cargar la lista de tomas de inventario :\n" +
                    sql.getMessage(), tblRelacionHistoricoTomas);
        }
    }

    private boolean tieneRegistroSeleccionado(JTable pTabla) {
        boolean rpta = false;
        if (pTabla.getSelectedRow() != -1) {
            rpta = true;
        }
        return rpta;
    }

    private void cargarRegistroSeleccionado() {
        VariablesTomaInv.vSecToma =
                tblRelacionHistoricoTomas.getValueAt(tblRelacionHistoricoTomas.getSelectedRow(), 0).toString().trim();
        VariablesTomaInv.vDescTipoToma =
                tblRelacionHistoricoTomas.getValueAt(tblRelacionHistoricoTomas.getSelectedRow(), 2).toString().trim();
        VariablesTomaInv.vFecIniToma =
                tblRelacionHistoricoTomas.getValueAt(tblRelacionHistoricoTomas.getSelectedRow(), 3).toString().trim();
        VariablesTomaInv.vFecFinToma =
                tblRelacionHistoricoTomas.getValueAt(tblRelacionHistoricoTomas.getSelectedRow(), 4).toString().trim();
        VariablesTomaInv.vDescEstadoToma =
                tblRelacionHistoricoTomas.getValueAt(tblRelacionHistoricoTomas.getSelectedRow(), 5).toString().trim();
        VariablesTomaInv.vCodEstadoToma =
                tblRelacionHistoricoTomas.getValueAt(tblRelacionHistoricoTomas.getSelectedRow(), 6).toString().trim();

    }
}
