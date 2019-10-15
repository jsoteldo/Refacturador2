package mifarma.ptoventa.centromedico.reference.proceso.proceso;

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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mifarma.common.FarmaUtility;

import mifarma.ptoventa.ventas.DlgListaProductos;


public class DlgPrpcesaRecetaPedido extends JDialog {

    private Frame myParentFrame;
    private JPanel jPanel1 = new JPanel();
    private JProgressBar jpbProceso = new JProgressBar();
    private JLabel lblAvance = new JLabel();
    private MiWorker_Receta trabajador;
    private DlgListaProductos  dlgCompra =  new DlgListaProductos();
    private String vCodCia = "";
    private String vCodLocal = "";
    private String vNumReceta = "";
    
    public DlgPrpcesaRecetaPedido() {
        this(null, "", false,null,"","","");
    }

    public DlgPrpcesaRecetaPedido(Frame parent, String title, boolean modal,DlgListaProductos dlgAux,
                       String pCodCia,String pCodLocal,String pNumReceta) {
        super(parent, title, modal);
        myParentFrame = parent;
        dlgCompra = dlgAux;
        vCodCia = pCodCia;
        vCodLocal = pCodLocal;
        vNumReceta = pNumReceta;
        
        try {
            jbInit();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(319, 133));
       // this.getContentPane().setLayout(gridLayout1);
        this.setTitle("Procesando");
        this.setDefaultCloseOperation(0);
        this.setResizable(true);
        this.setModal(true);
        this.addWindowListener(new WindowAdapter() {
                    public void windowOpened(WindowEvent e) {
                        this_windowOpened(e);
                    }

                    public void windowClosing(WindowEvent e) {
                        this_windowClosing(e);
                    }
                });

        jPanel1.setBackground(Color.white);
        jPanel1.setLayout(null);
        jpbProceso.setBounds(new Rectangle(10, 10, 290, 25));
        lblAvance.setText("");
        lblAvance.setBounds(new Rectangle(75, 45, 175, 20));
        jPanel1.add(lblAvance, null);
        jPanel1.add(jpbProceso, null);
        this.getContentPane().add(jPanel1, null);
    }

    /* ************************************************************************ */
    /*                                  METODO initialize                       */
    /* ************************************************************************ */

    private void initialize() {
        
    }    

    private void this_windowOpened(WindowEvent e) {
        iniciaProcesoProgressBar();
        FarmaUtility.centrarVentana(this);
    }    
    
    private void this_windowClosing(WindowEvent e) {
        FarmaUtility.showMessage(this, 
                                 "Acción prohibida, esperar que termine el proceso.", 
                                 null);
    }

    private void iniciaProcesoProgressBar(){
        if (trabajador == null) {
            trabajador = new MiWorker_Receta(this,dlgCompra,lblAvance,jpbProceso,vCodCia,vCodLocal,vNumReceta);

            //Agrego un Listener para el cambio de la propiedad "progress"
            trabajador.addPropertyChangeListener(new PropertyChangeListener() {
                        public void propertyChange(PropertyChangeEvent evt) {
                            if ("progress".equals(evt.getPropertyName())) {
                                lblAvance.setText(evt.getNewValue() + 
                                                      " %");
                            }
                        }
                    });
        }
        trabajador.execute();
    }
    
    /*
     * Esta es mi inner class "MiWorker", si se fijan, estoy
     * instrumentando Generics para decirle a esta clase, que deberá
     * retornar del "doInBackGround" un String, y que los demás métodos
     * no deben retornar nada (void)
     */
    /*class MiWorker extends SwingWorker<String, Void>{
        
        boolean pEjecutaProceso =  false;
        DlgMifarmaSolCompra dlgAuxCompra = new DlgMifarmaSolCompra();
        JDialog dlgAux = new JDialog();
        
        SubProcesos subProc = new SubProcesos("aa");
            
        public MiWorker(JDialog dlg,DlgMifarmaSolCompra dlgCompra){
            dlgAux = dlg;
            dlgAuxCompra = dlgCompra;
        }
            
            @Override
            protected String doInBackground() throws Exception {
                    int i=0;
                    
                    while(i<=100 && !isCancelled() && operacionBD() ){
                            jpbProceso.setValue(i);
                            setProgress(i);
                            i++;
                            Thread.sleep(100);
                    }
                    
                    return "Operacion finalizada";
            }
            
            @Override
            public void done(){
                    try {
                            lblAvance.setText(get());
                        if(lblAvance.getText().trim().equalsIgnoreCase("Operacion finalizada")){
                            dlgAux.setVisible(false);
                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
            }
            
            public boolean operacionBD(){
                boolean valor = false;
                if(!pEjecutaProceso){
                pEjecutaProceso = true;
                    
                    subProc.start();     
                    //this.cancel(false);
                 //dlgAuxCompra.iniciaListadoPantalla();
               
                }
                valor = true;
                return valor;
            }
    }    */
    
}
