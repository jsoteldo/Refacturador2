package mifarma.ptoventa.test_desa.reloj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;

public class Hora extends JLabel implements Runnable{
    
    private Thread t;
    private String formato;
    
    public Hora(String format) {
        t = new Thread(this);
        formato = format;
        t.start();
    }

    public Hora() {
        t = new Thread(this);
        formato = "HH:mm:ss a";
        t.start();
    }

    @Override
    public void run() {
        while(true){
            DateFormat D_Formato = new SimpleDateFormat(formato);
            Date date = new Date();
            this.setText(D_Formato.format(date));
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                Logger.getLogger(Hora.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
}
