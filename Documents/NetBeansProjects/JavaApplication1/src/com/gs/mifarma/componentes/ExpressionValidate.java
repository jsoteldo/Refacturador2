package com.gs.mifarma.componentes;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import mifarma.ptoventa.main.EconoFar_Matriz;
import mifarma.ptoventa.main.MainFarmaVenta;

public class ExpressionValidate {
    public ExpressionValidate() {
        super();
    }
    
    public static String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"+"@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    //public static String SOLO_LETRA = "[a-zA-Z]";
    public static String SOLO_LETRA = "^[a-zA-Z]+";
    
    public static String LETRA_CON_ESPACIOS = "^[a-zA-Z]+";
    
    //public static String SOLO_NUMERO = "[0-9]";
    public static String SOLO_NUMERO = "^[0-9]+";
    
    public static String FECHA_DDMMYYYY = "^(?:(?:0?[1-9]|1\\d|2[0-8])(\\/|-)(?:0?[1-9]|1[0-2]))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(?:(?:31(\\/|-)(?:0?[13578]|1[02]))|(?:(?:29|30)(\\/|-)(?:0?[1,3-9]|1[0-2])))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(29(\\/|-)0?2)(\\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\\d\\d)?(?:0[48]|[2468][048]|[13579][26]))$";
    
    public static String NUM_2_DECIMALES = "^[0-9]+(\\.[0-9]{1,2})?$";
    
    public static String NUM_3_DECIMALES = "^[0-9]+(\\.[0-9]{1,2})?$";
    
    //public static String ALFANUMERICO = "[^0-9 a-zA-Z.%&_^-]";
    public static String ALFANUMERICO = "^[\\w]+$";
    
    public static String HORA_MIN_24 = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$";
    
    public static String HORA_MIN__SEG_24 = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
    
    
    
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(ALFANUMERICO);
        String pCadena = "CASMAANCASH";
        System.out.println(""+pCadena);
        Matcher matcher = pattern.matcher(pCadena);
        if (!matcher.matches()) {
            System.out.println("no cumple");
        } else {
            System.out.println("si cumple");
        }
        
         System.out.println(Pattern.matches(ALFANUMERICO, pCadena));
    }
}
