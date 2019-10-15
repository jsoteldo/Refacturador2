package mifarma.ptoventa.cnx;

public class Prueba_Cnx {
    public Prueba_Cnx() {
        super();
    }

    public static void main(String[] args) {
        // Create the threads
        try {
            JdbcMTSample va = new JdbcMTSample();
            va.main_dos();
            
        } catch (Exception ie) {
            // TODO: Add catch code
            ie.printStackTrace();
        }
    }
}
