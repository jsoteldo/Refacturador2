package mifarma.ptoventa.tomainventario;

import com.gs.mifarma.worker.JDialogProgress;

import com.gs.mifarma.worker.JFarmaWorker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.SystemColor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionAdapter;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.inventario.reference.DBInventario;
import mifarma.ptoventa.inventario.reference.VariablesInventario;
import mifarma.ptoventa.tomainventario.reference.DBTomaInv;
import mifarma.ptoventa.tomainventario.reference.VariablesTomaInv;
//SLEYVA-2019
public class DlgGenerarData extends JDialog {
    Frame parentFrame;
    private int x;
    private int y;
    ImageIcon imageAndroidIcon = new ImageIcon(getClass().getResource("images/android_icon_x24.png"));
    ImageIcon imageWindowsIcon = new ImageIcon(getClass().getResource("images/windows_icon_x24.png"));
    ImageIcon imageNoneIcon = new ImageIcon(getClass().getResource("images/disconnected_icon_x24.png"));
    private JPanel jPanel1 = new JPanel();
    private JPanel pnlCabezera = new JPanel();
    private JLabel lblTituloCab = new JLabel();
    private JPanel pnlButton = new JPanel();
    private JButton btnCerrar = new JButton();
    private JButton btnDetectPda = new JButton();
    private JButton btnGenerarData = new JButton();
    private JButton btnEnviarData = new JButton();
    private JLabel lblEstPDA = new JLabel();

    public DlgGenerarData() {
        this(null, "", false);
    }

    public DlgGenerarData(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
            parentFrame = parent;
            this.lblTituloCab.setText(title);
            inicializarDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void inicializarDialog() {
        checkCurrentDevice();
    }
    
    public boolean verificarPDA(String OS) {
        boolean estado = false;
        String[] commands = { VariablesTomaInv.PATH_PDANET_API, "verificar", OS };
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
                        System.err.println("Dispositivo PDA no conectado");
                        estado = false;
                    } else if (stdInput.readLine() != null) {
                        System.out.println("Dispositivo PDA conectado");
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

    private void jbInit() throws Exception {
        this.setUndecorated(true);
        this.setSize(new Dimension(400, 280));
        this.getContentPane().setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanel1.setBounds(new Rectangle(0, 0, 400, 280));
        jPanel1.setBackground(SystemColor.window);
        jPanel1.setLayout(null);
        jPanel1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        pnlCabezera.setBounds(new Rectangle(0, 0, 400, 30));
        pnlCabezera.setBackground(new Color(255, 130, 14));
        pnlCabezera.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 1));
        pnlCabezera.setLayout(null);
        pnlCabezera.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pnlCabezera_mousePressed(e);
            }
        });
        pnlCabezera.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                pnlCabezera_mouseDragged(e);
            }
        });
        lblTituloCab.setBounds(new Rectangle(15, 0, 205, 30));
        lblTituloCab.setForeground(SystemColor.window);
        pnlButton.setBounds(new Rectangle(340, 0, 45, 25));
        pnlButton.setLayout(null);
        pnlButton.setBackground(new Color(254, 6, 38));
        btnCerrar.setText("X");
        btnCerrar.setBounds(new Rectangle(0, 0, 45, 25));
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setForeground(SystemColor.window);
        btnCerrar.setFont(new Font("Tahoma", 1, 15));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFocusable(false);
        btnCerrar.setRequestFocusEnabled(false);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCerrar_actionPerformed(e);
            }
        });
        btnDetectPda.setText("Detectar PDA");
        btnDetectPda.setBounds(new Rectangle(265, 75, 110, 35));
        btnDetectPda.setActionCommand("btnConectar_actionPerformed");
        btnDetectPda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnConectar_actionPerformed(e);
            }
        });
        btnDetectPda.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnDetectPda_keyPressed(e);
                }
            });
        btnGenerarData.setText("Generar Data de Materiales y Producto Barra");
        btnGenerarData.setBounds(new Rectangle(25, 130, 350, 35));
        btnGenerarData.setActionCommand("");
        btnGenerarData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnExportarData_actionPerformed(e);
            }
        });
        btnGenerarData.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnGenerarData_keyPressed(e);
                }
            });
        btnEnviarData.setText("Enviar Data a PDA");
        btnEnviarData.setBounds(new Rectangle(25, 185, 350, 35));
        btnEnviarData.setActionCommand("");
        btnEnviarData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCopiarBD_actionPerformed(e);
            }
        });
        btnEnviarData.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    btnEnviarData_keyPressed(e);
                }
            });
        lblEstPDA.setText("Dispositivo PDA no conectado");
        lblEstPDA.setBounds(new Rectangle(25, 75, 230, 35));
        lblEstPDA.setFont(new Font("Tahoma", 0, 12));
        lblEstPDA.setBackground(new Color(254, 6, 38));
        lblEstPDA.setOpaque(true);
        lblEstPDA.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 2));
        lblEstPDA.setForeground(SystemColor.window);
        //lblEstPDA.setIcon(imageNoneIcon);
        lblEstPDA.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(lblEstPDA, null);
        jPanel1.add(btnDetectPda, null);
        jPanel1.add(pnlCabezera, null);
        pnlCabezera.add(lblTituloCab, null);
        pnlButton.add(btnCerrar, null);
        pnlCabezera.add(pnlButton, null);
        this.getContentPane().add(btnEnviarData, null);
        this.getContentPane().add(btnGenerarData, null);
        this.getContentPane().add(jPanel1, null);
       
    }

    private void pnlCabezera_mouseDragged(MouseEvent e) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }

    private void pnlCabezera_mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    private void btnCerrar_actionPerformed(ActionEvent e) {
        cerrarVentana();
    }

    private void this_windowOpened(WindowEvent e) {
        FarmaUtility.centrarVentana(this);
    }

    void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

    private void btnConectar_actionPerformed(ActionEvent e) {
        checkCurrentDevice();
    }
   
    private void btnExportarData_actionPerformed(ActionEvent e) {
        Cargando carga = new Cargando(parentFrame, "", true);
        try {
            Cargando.estado = "G";
            carga.mostrar();
            
            JOptionPane.showMessageDialog(this, "Productos registrados: " + carga.getCountProducto() + "\n" +
                    "Producto Barra registrados: " + carga.getCountBarra(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("Termino a las  " + new Date());
        } catch (Exception f) {
            f.printStackTrace();
        }
    }
    
    public void checkCurrentDevice() {
        if (verificarPDA(VariablesTomaInv.WINDOWS_OS)) {
            lblEstPDA.setIcon(imageWindowsIcon);
            lblEstPDA.setText("Dispositivo PDA conectado");
            lblEstPDA.setBackground(new Color(56, 112, 2));
            btnEnviarData.setEnabled(true);
        } else if (verificarPDA(VariablesTomaInv.ANDROID_OS)) {
            lblEstPDA.setIcon(imageAndroidIcon);
            lblEstPDA.setText("Dispositivo PDA conectado");
            lblEstPDA.setBackground(new Color(56, 112, 2));
            btnEnviarData.setEnabled(true);
        } else {
            lblEstPDA.setIcon(imageNoneIcon);
            lblEstPDA.setText("Dispositivo PDA no conectado");
            lblEstPDA.setBackground(new Color(254, 6, 38));
            btnEnviarData.setEnabled(false);
        }
    }

    private void btnCopiarBD_actionPerformed(ActionEvent e) {
        Cargando carga = new Cargando(parentFrame, "", true);
        boolean checking = false;
        boolean androidEckerd = false;
        if (verificarPDA(VariablesTomaInv.WINDOWS_OS)) {
            checking = true;
        } else if(verificarPDA(VariablesTomaInv.ANDROID_OS)) {
            checking = true;
            androidEckerd = true;
        } 
        if(checking) {
            DBTomaInv dbi = new DBTomaInv();
            Connection conUser = null;
            String[] options = { "Aceptar" };
            JPanel panel = new JPanel(new GridLayout(2,2));
            JLabel lblNamePda = new JLabel("Nombre del PDA: ");
            final JTextField txtNamePda = new JTextField(20);
            panel.add(lblNamePda);
            panel.add(txtNamePda);
            
            int selectedOption =
                JOptionPane.showOptionDialog(null, panel, "Configurar nombre", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                             null, options, txtNamePda);

            if (selectedOption == 0) {
                String vNombre = txtNamePda.getText();
                vNombre = vNombre.replaceAll("^\\s+", ""); // ltrim
                vNombre = vNombre.replaceAll("\\s+$", ""); // rtrim
                if (vNombre.equals("")) {
                    JOptionPane.showMessageDialog(this, "Ingrese el nombre del PDA", "Mensaje",
                                                  JOptionPane.WARNING_MESSAGE);
                    return;
                }
                File userNameFile = new File(DBTomaInv.pathOriginal + VariablesTomaInv.USER_NAME_PDA);

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(userNameFile));
                    writer.write(vNombre);
                    writer.close();
                    
                    dbi.borrarUsuario();
                    conUser = dbi.connect();
                    dbi.setPdaUserName(vNombre, conUser);
                    conUser.commit();
                    conUser.close();
                    
                    Cargando.estado = "E";
                    carga.setAndroidEckerd(androidEckerd);
                    carga.mostrar();

                    System.out.println("Archivo copiado al PDA");
                    JOptionPane.showMessageDialog(this, "BD copiada existosamente", "Mensaje",
                                                  JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    err.printStackTrace();
                    //ProcessMsgBlocker.instance().removeMessage();
                    JOptionPane.showMessageDialog(this, "BD no existe, volver a generar.", "Mensaje",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    btnEnviarData.setEnabled(false);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Conecte el PDA a la computadora", "Mensaje",
                                                      JOptionPane.WARNING_MESSAGE);
            lblEstPDA.setIcon(imageNoneIcon);
            lblEstPDA.setText("Dispositivo PDA no conectado");
            lblEstPDA.setBackground(new Color(254, 6, 38));
            btnEnviarData.setEnabled(false);
            return;
        }
    }
    
    public static boolean enviarArchivoAlPDA(String pathApi, String action, String localPath, String localFile,
                                      String newName) {
        boolean estado = false;
        String[] commands = { pathApi, action, localPath, localFile, newName };
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
            estado = true;
            System.out.println("Archivo enviado al PDA");
        } catch (Exception err) {
            estado = false;
            err.printStackTrace();
        }
        return estado;
    }

    private void btnDetectPda_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana();
        }
    }

    private void btnGenerarData_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana();
        }
    }

    private void btnEnviarData_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana();
        }
    }
}
