package com.gs.mifarma.FarmaMenu.lstMenu;
// Objetos para la elaboraci�n de men�s
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.Method;

import java.util.Hashtable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


// Objetos para las teclas de abreviaci�n
// Objetos de la interfaz
// Objetos de lista ordenada por clave

// Objetos para las teclas de abreviaci�n
// Objetos de la interfaz

// Objetos de lista ordenada por clave

// Declaraci�n de la clase MiMenu
public class MiMenu extends JMenuBar implements ActionListener {
    private String vtOpc[][]; // Matriz de opciones
    private String MetodoAccion = ""; // Nombre del m�todo de acciones
    private int LonOpc; // N�mero de filas de la matriz de opciones

    // Constructor de la clase MiMenu

    public MiMenu(String vtOpcPar[][], String MetAccion) {

        // Asignaci�n de par�metros y variables de instancia
        vtOpc = vtOpcPar;
        MetodoAccion = MetAccion;
        LonOpc = vtOpc.length;
        /* Definici�n de la orientaci�n y aspecto del men�. Necesaria
           para que opciones de acci�n directamente colocadas en la
           barra de men�s restrinjan su tama�o al m�nimo */
        setLayout(new FlowLayout(FlowLayout.LEFT));
        // Definici�n de la lista que contendr� los objetos del men�
        Hashtable Lista = new Hashtable();
        // Encontrando los tipos de objetos y llenando la lista con ellos
        JMenu tmpMenu;
        JMenuItem tmpItem;
        for (int i = 0; i < LonOpc; i++) {
            if (TieneHijos(vtOpc[i][0])) {
                // Si tiene hijos, debe ser un submen�
                tmpMenu = new JMenu(vtOpc[i][0]);
                // El nombre del objeto es la clave de la opci�n
                tmpMenu.setName(vtOpc[i][0]);
                // El texto que se mostrar� va sin el se�alador del mnem�nico 
                tmpMenu.setText(QuitaCar(vtOpc[i][1], '_'));
                // El mnem�nico se asigna como el caracter desp�es del se�alador
                tmpMenu.setMnemonic(EncuentraMnemonico(vtOpc[i][2], '_'));
                // Se agrega el objeto a la lista, ordenado por su clave
                Lista.put(vtOpc[i][0], tmpMenu);
            } else {
                // Si no, debe ser una opci�n de acci�n
                tmpItem = new JMenuItem(vtOpc[i][0]);
                // El nombre del objeto es la clave de la opci�n
                tmpItem.setName(vtOpc[i][0]);
                /* El texto que se mostrar� va sin el se�alador del
                mnem�nico */
                tmpItem.setText(QuitaCar(vtOpc[i][1], '_'));
                /* El mnem�nico se asigna como el caracter desp�es del
                    se�alador */
                char Nemonico = EncuentraMnemonico(vtOpc[i][1], '_');
                tmpItem.setMnemonic(Nemonico);
                // La tecla de aceleraci�n es el mismo mnem�nico
                tmpItem.setAccelerator(KeyStroke.getKeyStroke(Nemonico, ActionEvent.ALT_MASK));
                /* El comando de acci�n del objeto es tambi�n la clave
                    de la opci�n */
                tmpItem.setActionCommand(vtOpc[i][0]);
                /* Este es de acciones por lo que debe ser escuchado
                    por el sistema */
                tmpItem.addActionListener(this);
                // Se agrega el objeto a la lista, ordenado por su clave
                Lista.put(vtOpc[i][0], tmpItem);
            }
        }
        String IdPapa;
        // Variable temporal utilizada s�lo para la comparaci�n de clases
        JMenu tmpMenuPrb = new JMenu();
        /* Conectando los objetos de la lista de acuerdo con las
             jerarqu�as establecidas */
        for (int i = 0; i < LonOpc; i++) {
            if (EsPrincipal(vtOpc[i][0])) {
                /* Si es una opci�n principal, no tiene padre y se agrega a
                     la barra de men�s. Dependiendo del tipo de objeto se
                     recupera de la lista por su clave */
                if (Lista.get(vtOpc[i][0]).getClass() == tmpMenuPrb.getClass()) {
                    add((JMenu)Lista.get(vtOpc[i][0]));
                } else {
                    add((JMenuItem)Lista.get(vtOpc[i][0]));
                }
            } else {
                /* Si no, tiene un padre. Dependiendo del tipo de objeto
                 se recupera de la lista por su clave y se anexa al padre
                 encontrado */
                IdPapa = vtOpc[i][0].substring(0, vtOpc[i][0].length() - 1);
                tmpMenu = (JMenu)Lista.get(IdPapa);
                if (Lista.get(vtOpc[i][0]).getClass() == tmpMenuPrb.getClass()) {
                    tmpMenu.add((JMenu)Lista.get(vtOpc[i][0]));
                } else {
                    tmpMenu.add((JMenuItem)Lista.get(vtOpc[i][0]));
                }
            }
        }
    } // Fin del constructor MiMenu
    /* M�todo que determina, dada la clave de una opci�n,
        si �sta tiene subopciones */

    private boolean TieneHijos(String Item) {

        /* Cuenta el n�mero de veces que aparece la clave dada iniciando
             otras claves. Si �sta aparece m�s de una vez, la opci�n tiene
             subopciones */
        int NVeces = 0;
        int LonItem = Item.length();
        for (int i = 0; i < LonOpc; i++) {
            if (vtOpc[i][0].length() >= LonItem) {
                if (vtOpc[i][0].substring(0, LonItem).equals(Item)) {
                    NVeces++;
                    if (NVeces > 1) {
                        return true;
                    }
                }
            }

        }
        return (NVeces > 1);
    } // Fin de TieneHijos
    /* M�todo que determina, dada la clave de una opci�n,
        si �sta pertenece a la barra de men�s */

    private boolean EsPrincipal(String Item) {

        // En la barra de men�s se esperan claves de un solo d�gito
        return (Item.length() == 1);

    } // Fin de EsPrincipal
    /* M�todo de prop�sito general que quita un caracter espec�fico
        de una cadena */

    private String QuitaCar(String Cad, char c) {

        int Pos = Cad.indexOf(c);
        int Lon = Cad.length();
        if (Pos != -1) {
            // Si est� el caracter
            if (Pos == 0) {
                return Cad.substring(1, Lon);
            } else {
                if (Pos == Lon - 1) {
                    return Cad.substring(0, Lon - 1);
                } else {
                    return Cad.substring(0, Pos) + Cad.substring(Pos + 1, Lon);
                }
            }
        }
        return Cad;

    } // Fin de QuitaCar

    /* M�todo que retorna el caracter siguiente al se�alado,
        entendiendo que dicho caracter es el mnem�nico de una
        opci�n de men� */

    private char EncuentraMnemonico(String Cad, char c) {

        int Pos = Cad.indexOf(c);
        /*
        if (Pos >= Cad.length() - 1) {
            //El se�alador de mnem�nico no debe ser el �ltimo caracter de la cadena 
            return 0;
        }*/
        return Cad.charAt(Pos + 1);

    } // Fin de EncuentraMnemonico

    /* M�todo para la resoluci�n de las acciones en respuesta a
        los eventos de MiMenu que est�n siendo escuchados */

    public void actionPerformed(ActionEvent e) {

        try {
            /* Si el contexto en que se utiliza el objeto MiMenu es
              una ventana, dentro de la cual hay una barra de men�s,
              extendiendo la barra MiMenu, el objeto que instancia
              la clase est� en la tercera generaci�n. Si este no es el
              caso, habr� que alterar la instrucci�n, referenciando
              el objeto padre en la generaci�n correcta */
            Object objPapa = getParent().getParent().getParent();
            Class MiPapa = getParent().getParent().getParent().getClass();
            /* Estableciendo que los par�metros del m�todo de acciones
              en la clase que llama a MiMenu son de tipo String y
              pasando como argumento a dicho m�todo la clave de la
              opci�n seleccionada */
            Class[] TiposParametros = new Class[] { String.class };
            Object[] argumentos = new Object[] { e.getActionCommand() };
            /* Definiendo el m�todo de acciones de la clase que llama
              a MiMenu y sus par�metros para luego invocarlo
              ocasionando su ejecuci�n */
            Method target = objPapa.getClass().getMethod(MetodoAccion, TiposParametros);
            Object param[] = { e.getActionCommand() };
            target.invoke(objPapa, argumentos);

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    } // Fin de actionPerformed

} // Fin de la clase MiMenu
