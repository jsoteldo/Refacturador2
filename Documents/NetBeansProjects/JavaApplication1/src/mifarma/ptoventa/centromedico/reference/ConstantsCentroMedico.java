package mifarma.ptoventa.centromedico.reference;

import javax.swing.JLabel;

import mifarma.common.FarmaColumnData;
import mifarma.common.FarmaConstants;


/**
 * Copyright (c) 2016 MIFARMA S.A.C.<br>
 * <br>
 * Entorno de Desarrollo   : Oracle JDeveloper 10g<br>
 * Nombre de la Aplicación : ConstantsInventario.java<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * ERIOS      22.08.2016   Creación<br>
 * <br>
 * @author Christiam Castillo Gonzales<br>
 * @version 1.0<br>
 *
 */
public class ConstantsCentroMedico {
    public ConstantsCentroMedico() {
    }
    
    public static final String ATE_MEDICA_PEND_TRIAJE = "T";
    public static final String ATE_MEDICA_PEND_ATENCION = "P";
    public static final String ATE_MEDICA_EN_CONSULTA = "C";
    public static final String ATE_MEDICA_ATENDIDO = "A";
    public static final String ATE_MEDICA_GUARDADA = "G";
    
    public static final String LISTA_ESPERA_ADMISION = "1";
    public static final String LISTA_ESPERA_CONSULTA = "2";

    public static final String LISTA_MEDICOS = "CME01"; 
    public static final String LISTA_ESPECIALIDADES= "CME02"; 
    
    public static final String TIPO_PACIENTE_NUEVO = "N";
    public static final String TIPO_PACIENTE_CONTINUADOR = "C";
    public static final String TIPO_PACIENTE_ACTUALIZAR = "A";
    public static final String TIPO_PACIENTE_SINMODIFICAR = "S";
    
    
    public static final String TIPO_BUSQUEDA_COMPROBANTE = "C";
    public static final String TIPO_BUSQUEDA_PACIENTE = "P";
    public static final String TIPO_BUSQUEDA_ACTUALIZAR = "A";
    
    public static final String COMBOBOX_PACIENTE_NUEVO = "165";
    public static final String COMBOBOX_PACIENTE_CONTINUADOR = "166";
    public static final String COMBOBOX_DNIPACIENTE_DNI = "01";
    public static final String COMBOBOX_DNIPACIENTE_NR = "06";
    public static final String COMBOBOX_DNIPACIENTE_NA = "07";
    public static final String COMBOBOX_ACOMPANANTE_NA = "177";
    public static final String COMBOBOX_GRADOINSTRUCCION_NR = "192";
    public static final String COMBOBOX_SEXO_MASCULINO = "M";
    public static final String COMBOBOX_SEXO_FEMENINO = "F";
    
    
    public static final String TIPO_BUSQUEDA_MAE_MEDICO = "M";
    public static final String TIPO_BUSQUEDA_MAE_ATENCION = "A";

    
    /**
     * Se declaran las variables para el listado de Pacientes
     * @author CCASTILLO
     * @since  22.08.2016
     */
    public static final FarmaColumnData columnsListaPacientes[] =
    { new FarmaColumnData("Cod.Pac.", 0, JLabel.CENTER),  //0
      new FarmaColumnData("Cod. Tip Doc", 0, JLabel.CENTER), //1 
      new FarmaColumnData("Tip. Doc.", 60, JLabel.CENTER), //2
      new FarmaColumnData("Num. Doc.", 90, JLabel.CENTER), //3
      new FarmaColumnData("Ape Pat ", 110, JLabel.CENTER), //4
      new FarmaColumnData("Ape Mat ", 110, JLabel.CENTER), //5
      new FarmaColumnData("Nombre ", 110, JLabel.CENTER), //6
      new FarmaColumnData("Nombre Completo", 0, JLabel.LEFT), //7
      new FarmaColumnData("Estado", 60, JLabel.CENTER), //8
      
      new FarmaColumnData("Sexo", 0, JLabel.CENTER), //9
      new FarmaColumnData("Estado Civil", 0, JLabel.CENTER), //10
      new FarmaColumnData("Fecha Nacimiento", 0, JLabel.CENTER), //11
      new FarmaColumnData("Lugar Nacimiento", 0, JLabel.CENTER), //12    // without data
      new FarmaColumnData("Lugar Procedencia", 0, JLabel.CENTER), //13   // without data
      new FarmaColumnData("Ubigeo", 0, JLabel.CENTER), //14              // without data
      new FarmaColumnData("Dirección", 0, JLabel.CENTER), //15
      new FarmaColumnData("Grado Instrucción", 0, JLabel.CENTER),  //16
      new FarmaColumnData("Ocupacion", 0, JLabel.CENTER), //17
      new FarmaColumnData("C. Educativo/Labor", 0, JLabel.CENTER), //18
      new FarmaColumnData("Correo", 0, JLabel.CENTER), //19
      new FarmaColumnData("Telefono fijo", 0, JLabel.CENTER), //20
      new FarmaColumnData("Celular", 0, JLabel.CENTER), //21
      new FarmaColumnData("Tipo Acompanante", 0, JLabel.CENTER), //22
      new FarmaColumnData("Nombre Acompanante", 0, JLabel.CENTER), //23
      new FarmaColumnData("Tipo Doc Acompanante", 0, JLabel.CENTER), //24
      new FarmaColumnData("Num Doc Acompanante", 0, JLabel.CENTER), //25
      new FarmaColumnData("Num Historia", 0, JLabel.CENTER), //26
      new FarmaColumnData("Fecha Historia", 0, JLabel.CENTER), //27
      new FarmaColumnData("FactorRH", 0, JLabel.CENTER), //28
      new FarmaColumnData("GrupoSan", 0, JLabel.CENTER), //29
      new FarmaColumnData("Nro HC Actual", 0, JLabel.CENTER), //30
                          
      new FarmaColumnData("Dep  Ubigeo", 0, JLabel.CENTER), //31
      new FarmaColumnData("Prov Ubigeo", 0, JLabel.CENTER), //32
      new FarmaColumnData("Dis  Ubigeo", 0, JLabel.CENTER), //33
      new FarmaColumnData("Dep  LugNac", 0, JLabel.CENTER), //34
      new FarmaColumnData("Prov LugNac", 0, JLabel.CENTER), //35
      new FarmaColumnData("Dis  LugNac", 0, JLabel.CENTER), //36
      new FarmaColumnData("Dep  LugPro", 0, JLabel.CENTER), //37
      new FarmaColumnData("Prov LugPro", 0, JLabel.CENTER), //38
      new FarmaColumnData("Dis  LugPro", 0, JLabel.CENTER)  //39
                          
    };
    
    public static final FarmaColumnData columnsListaMedicos[] =
    { new FarmaColumnData("Cod. Doc.", 0, JLabel.CENTER),  //0
      new FarmaColumnData("CMP ", 80, JLabel.CENTER), //1 
      new FarmaColumnData("Nombre Completo", 0, JLabel.CENTER), //2
      new FarmaColumnData("Apellido", 150, JLabel.CENTER), //3
      new FarmaColumnData("Nombre ", 130, JLabel.CENTER), //4
      new FarmaColumnData("Especialidad", 160, JLabel.CENTER) //5
    };

    public static final Object[] defaultcolumnsListaPacientes = { " ", " ", " ", " "," "," " };

     public static final FarmaColumnData[] columnasListaEspera =
     { new FarmaColumnData("Hora", 60, JLabel.CENTER),
       new FarmaColumnData("Nro HC", 80, JLabel.CENTER), 
       new FarmaColumnData("Paciente", 220, JLabel.LEFT),
       new FarmaColumnData("Edad", 0, JLabel.CENTER),
       new FarmaColumnData("Tipo", 0, JLabel.CENTER),
       new FarmaColumnData("Especialidad", 140, JLabel.LEFT),
       new FarmaColumnData("Médico", 170, JLabel.LEFT),
       new FarmaColumnData("Estado", 125, JLabel.LEFT)
     };
     
    
    public static final FarmaColumnData columnsListaRegistroReceta[] =
    { new FarmaColumnData("Correlativo", 85, JLabel.CENTER), //0
      new FarmaColumnData("Fecha", 120, JLabel.CENTER), //1
      new FarmaColumnData("Medico", 180, JLabel.LEFT), //2
      new FarmaColumnData("Paciente", 180, JLabel.LEFT), //3
      new FarmaColumnData("Dni Medico", 100, JLabel.LEFT), //4
      new FarmaColumnData("Monto", 100, JLabel.RIGHT), //5
      new FarmaColumnData("dia_order", 0, JLabel.CENTER),// 6
      new FarmaColumnData("cia", 0, JLabel.CENTER),//7
      new FarmaColumnData("local", 0, JLabel.CENTER) //8
    };

    public static final Object[] defaultValuesListaRegistroReceta =
    { " ", " ", " ", " ", " ", " ", " " , " ", " "};

    public static final FarmaColumnData columnsListaDetalleVentas[] =
    { 
      new FarmaColumnData("Código", 50, JLabel.CENTER), 
      new FarmaColumnData("Descripción", 180, JLabel.LEFT), 
    new FarmaColumnData("Laboratorio", 120, JLabel.LEFT), 
      new FarmaColumnData("Unidad", 90, JLabel.CENTER),
      new FarmaColumnData("Recetado", 80, JLabel.CENTER),
      new FarmaColumnData("Obs", 80, JLabel.CENTER),
      new FarmaColumnData("Por Atender",80, JLabel.CENTER)
     } ;

    public static final Object[] defaultValuesListaDetalleVentas =
    { " ", " ", " ", " ", " ", " ", " " , " " };


    public static final String[] ORDEN = { "ASC", "DESC" };
    public static final String[] ORDENVAL = { FarmaConstants.ORDEN_ASCENDENTE, FarmaConstants.ORDEN_DESCENDENTE };
     
       
}