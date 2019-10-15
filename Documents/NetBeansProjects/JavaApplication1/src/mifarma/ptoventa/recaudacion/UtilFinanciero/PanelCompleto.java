package mifarma.ptoventa.recaudacion.UtilFinanciero;
/*
 * Javier Abellán. 26 de octubre de 2003
 *
 * PanelCompleto.java
 *
 * Ejemplo de manejo de JTable y TableModel
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Panel con toda la parte visual del ejemplo. Crea un JScrollPane con el JTable
 * en su interior y dos JButton para añadir y borrar elementos de la tabla.
 */
public class PanelCompleto extends JPanel
 {
     /**
      * Constructor que recibe el modelo de la tabla y el control. Guarda ambos
      * y llama al metodo construyeVentana() que se encarga de crear los
      * componentes.
      */
     public PanelCompleto(ModeloTabla modelo, ControlTabla control)
     {
         super (new GridBagLayout());
         this.modelo = modelo;
         this.control = control;
         construyeVentana();
     }
     
     /**
      * Se le pasa un render para la tabla. Lo añade para las columnas de la
      * misma.
      */
     public void tomaRender (TableCellRenderer render)
     {
         if (tabla == null)
             return;
         
         // Se pasa el render para las columnas con String y las que tienen
         // Integer.
         tabla.setDefaultRenderer (String.class, render);
         tabla.setDefaultRenderer (Integer.class, render);
     }
     
     /**
      * Crea los componentes de este panel.
      * Un JScrollPane, el JTable que va dentro y dos JButton para añadir y
      * quitar elementos del JTable.
      */
     private void construyeVentana()
     {
         // Para poner las restricciones a los componentes (posicioes dentro
         // del panel)
         GridBagConstraints restricciones = new GridBagConstraints();
         
         // Un JScrollPane con el JTable dentro.
         // Las restricciones...
         restricciones.gridx = 0;
         restricciones.gridy = 0;
         restricciones.gridwidth = GridBagConstraints.REMAINDER;
         restricciones.fill = GridBagConstraints.BOTH;
         restricciones.weightx = 1.0;
         restricciones.weighty = 1.0;
         
         // Se crea el JScrollPane, el JTable y se pone la cabecera...
         JScrollPane scroll = new JScrollPane();
         tabla = new JTable (modelo);
         tabla.setRowHeight(32);   // Un alto a las filas para que quepan los iconos.
         scroll.setViewportView(tabla);
         scroll.setColumnHeaderView (tabla.getTableHeader());
         
         // ... y se añade todo al panel
         this.add(scroll, restricciones);
         
         // Un botón para añadir nuevas filas.
         // Su posición en el panel ...
         restricciones.gridy = 1;
         restricciones.gridwidth = 1;
         restricciones.weighty = 0.0;
         restricciones.fill = GridBagConstraints.NONE;
         restricciones.anchor = GridBagConstraints.WEST;
         
         // Se crea el botón 
         JButton botonAnhadir = new JButton ("Añadir");
         
         // Se añade al panel
         this.add (botonAnhadir, restricciones);
         
         // y se le pone la acción a ejecutar cuando se pulse.
         botonAnhadir.addActionListener(new ActionListener() {
             public void actionPerformed (ActionEvent evento)
             {
                 control.anhadeFila();
             }
         });
         
         // Un botón para borrar filas.
         // La posición en el panel...
         restricciones.gridx = 3;
         restricciones.weightx = 0.0;
         restricciones.anchor = GridBagConstraints.EAST;
         
         // se crea el botón
         JButton botonBorrar = new JButton ("Borrar");
         
         // se añade al panel
         this.add (botonBorrar, restricciones);
         
         // y se le añade la acción a ajecutar.
         botonBorrar.addActionListener(new ActionListener() {
             public void actionPerformed (ActionEvent evento)
             {
                 control.borraFila();
             }
         });
     }
     
     /** Modelo de la tabla */
     private ModeloTabla modelo = null;
     
     /** Para modificar el modelo */
     private ControlTabla control = null;
     
     /** El JTable */
     private JTable tabla = null;
     
     /** El render del JTable */
     TableCellRenderer render = null;
}