package mifarma.ptoventa.programaXmas1.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mifarma.common.FarmaUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AbstractClass {
    private static final Logger log = LoggerFactory.getLogger(AbstractClass.class);
    
    /**
     * Valida si una variable cualquiera es nula o vacia
     * @autor Desarrollo5 - Alejandro nuñez
     * @param object
     * @return Boolean
     * @since 03.11.2015
     */
    protected Boolean _isEmpty(Object object){
        try{
            if (object == null) {
                return true;
            }
            if (object instanceof String) {
                return object.toString().trim().length() == 0;
            }
            if (object instanceof StringBuilder) {
                return object.toString().trim().length() == 0;
            }
            if (object instanceof List<?> || object instanceof ArrayList<?>) {
                return ((List<?>)object).isEmpty();
            }
            if (object instanceof Map<?, ?> || object instanceof HashMap<?, ?>) {
                return ((Map<?, ?>)object).isEmpty();
            }
            return false;
        }catch(Exception e){
            mensajesLogErrores("Error al comparar si la argumento es nulo","AbstractClass","_isEmpty","E", e);
            return false;
        }
    }
    
    /**
     * Metodo que compara dos variables cualquiera
     * @autor Desarrollo5 - alejandro nuñez
     * @param object1
     * @param object2
     * @return Boolean
     * @since 03.11.2015
     */
    protected Boolean _equiv(Object object1, Object object2) {
        try{
            if (_isEmpty(object1) && !_isEmpty(object2) || !_isEmpty(object1) && _isEmpty(object2)) {
                return false;
            }
            if (_isEmpty(object1) && _isEmpty(object2) || object1 == object2) {
                return true;
            }
    
            if (object1 instanceof String && object2 instanceof String) {
                return _toBlank(object1.toString()).equals(_toBlank(object2.toString()));
            }
            return object1.equals(object2);
        }catch(Exception e){
            mensajesLogErrores("Error al comparar dos valores","AbstractClass","_equiv","E", e);
            return false;
        }
    }
    
    /**
     * Metodo que devuelve una variable String nula como vacia, y si no es nula devuelve el mismo valor
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param cadena
     * @return String
     * @since 03.11.2015
     */
    protected String _toBlank(String cadena) {
        return _isEmpty(cadena) ? "" : cadena;
    }
    
    /**
     * Metodo que devuelve una variable String nula como vacia, y si no es nula devuelve el mismo valor quitandole los espacios
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param cadena
     * @return String
     * @since 03.11.2015
     */
    protected String _toBlank(String cadena, Boolean indicadorTrim) {
        return _isEmpty(cadena) ? "" : cadena.trim();
    }
    
    /**
     * Metodo que convierte una variable cualquiera a un valor a String
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param cadena
     * @return String
     * @since 03.11.2015
     */
    protected String _toStr(Object cadena) {
        return _isEmpty(cadena) ? null : _toBlank(cadena.toString());
    }

    /**
     * Metodo busca una cadena en una arreglo de cadenas.
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param cadena
     * @param valores
     * @return Boolean
     * @since 03.11.2015
     */
    protected Boolean inList(String cadena, String... valores) {
        for (String valor : valores) {
            if (cadena.equals(valor)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodo que centraliza la impresion del log
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param clase
     * @param metodo
     * @param tipoError(D=Debug,E=Error,I=info)
     * @since 04.11.2015
     */
    protected void mensajesLogErrores(String mensaje, String clase, String metodo, String tipoError, Exception e){        
        mensaje = "Clase: "+clase+", Metodo: "+metodo+", Mensaje:"+mensaje;
        
        switch (tipoError){
            case "D": log.debug(mensaje);
                break;
            case "E": log.error(mensaje,e);
                break;
            case "I": log.info(mensaje);
                break;
            default : log.warn(mensaje);
        }
    }
    
    /**
     * Metodo que centraliza la impresion del log
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param mensaje
     * @param clase
     * @param metodo
     * @param tipoError(D=Debug,E=Error,I=info)
     */
    protected void mensajesLogErrores(String mensaje, String clase, String metodo, String tipoError){
        mensajesLogErrores(mensaje, clase, metodo, tipoError, null);
    }
    
    /**
     * Metodo que completa con un simbolo especifico a una cadena
     * @autor Desarrollo5 - Alejandro Nuñez
     * @param cadena
     * @param tamanio
     * @param simbolo
     * @param alineacion(I=izquierda,D=derecha)
     * @return String
     * @since 10.11.2015
     */
    protected String completaCadena(String cadena, 
                                    Integer tamanio,
                                    String simbolo, 
                                    String alineacion){
        String cadenaResultado = "";
        
        if(!_isEmpty(cadena) && !_isEmpty(simbolo) && !_isEmpty(alineacion) && tamanio > 0){
            if(_equiv(alineacion, "I") || _equiv(alineacion, "D")){
                cadenaResultado = FarmaUtility.completeWithSymbol(cadena, tamanio, simbolo, alineacion);
            }
        }
        return cadenaResultado;
    }
    
    protected String completaCadena(String cadena, 
                                    Integer tamanio,
                                    String simbolo, 
                                    String alineacion,
                                    Boolean espacios){
        return completaCadena(_toBlank(cadena,true), tamanio, _toBlank(simbolo,true), _toBlank(alineacion,true));
    }
}
