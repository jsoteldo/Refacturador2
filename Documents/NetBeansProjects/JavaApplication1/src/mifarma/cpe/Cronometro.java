package mifarma.cpe;


import java.util.Timer;
import java.util.TimerTask;


public class Cronometro {

    final private Timer timer = new Timer();
    private int segundos = 0;

    //Clase interna que funciona como contador

    class Contador extends TimerTask {
        public void run() {
            segundos++;
            //System.out.println("segundo: " + segundos);
        }
    }
    //Crea un timer, inicia segundos a 0 y comienza a contar

    public void contar() {
        this.segundos = 0;
        this.timer.scheduleAtFixedRate(new Contador(), 0, 1000);
    }
    //Detiene el contador

    public void Detener() {
        timer.cancel();
    }
    //Metodo que retorna los segundos transcurridos

    public int getSegundos() {
        return this.segundos;
    }
}
