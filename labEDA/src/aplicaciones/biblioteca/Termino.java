package aplicaciones.biblioteca;

/**
 * Termino: clase de la Clave del Map que representa un termino del 
 * Indice Analitico de una Biblioteca Digital.
 * 
 * Para que sobrescriba eficientemente los metodos equals y hashCode
 * de Object, un Termino TIENE UN valorHash que almacena el resultado 
 * de la PRIMERA invocacion al metodo hashCode de la clase sobre el. 
 * De esta forma, el valor hash asociado a un Termino...
 ** (a) solo se calcula una vez, independientemente del numero de veces 
 **     que el metodo hashCode se aplique sobre el;
 ** (b) se puede usar en el metodo equals de la clase para comprobar la
 **     igualdad de dos terminos SOLO cuando sus valores Hash sean iguales.
 *
 * Ademas, para poder evaluar distintos metodos hashCode, un Termino TIENE 
 * UNA baseHashCode que almacena la base en la que se calcula su valorHash
 * 
 * @author  Profesores EDA 
 * @version Septiembre 2023
 */

public class Termino {
    
    public static final int BASE_TRIVIAL = 1;  
    public static final int BASE_JAVA_LANG_STRING = 31;
    public static final int BASE_MCKENZIE = 4;
    
    protected String palabra;    
    protected int valorHash;    /** por eficiencia: "caching the Hash code" o "Hash cache" */    
    protected int baseHashCode; /** para evaluar distintos metodos hashCode, con distintas bases */
    
    /** Crea el Termino asociado a la palabra p y 
     *  le asocia la base a emplear en el metodo hashCode */
    public Termino(String p, int base) { 
        palabra = p;  
        baseHashCode = base;
        valorHash = 0;
    }
    
    /** Crea el Termino asociado a la palabra p segun el estandar de Java */
    public Termino(String p) { this(p, BASE_JAVA_LANG_STRING); }
    
    /** Devuelve el valor Hash de un (this) Termino de forma EFICIENTE, 
     *  i.e.
     *  al aplicar este metodo por PRIMERA vez sobre un Termino de longitud n  = palabra.length(), 
     *  calcula su valor Hash, o valor de la siguiente funcion polinomial de base baseHashCode, 
     *  usando la regla de Horner y, por tanto, SIN usar metodos de la clase Math: 
     *  
     *  valorHash =   palabra.charAt(0) * baseHashCode^(n-1)  
     *                + palabra.charAt(1) * baseHashCode^(n-2) 
     *                + ...
     *                + palabra.charAt[n-1]
     *                   
     *  Si NO es la primera vez que se aplica el metodo, devuelve valorHash 
     */
    public int hashCode() { 
        int res = valorHash;
        if (res != 0) { return res;} // Para solo calcular el valor hash una vez
        
        /** COMPLETAR: calcular el valor de res de forma EFICIENTE, i.e. usando la regla de Horner */
        int n = 0;
        while (n < palabra.length()) {
            res = (res * baseHashCode) + palabra.charAt(n);
            n++;
        }
        
        valorHash = res;
        return res;
    }
   
    /** Comprueba si un (this) Termino es igual a otro de forma  
     *  eficiente, i.e. SOLO ejecuta el metodo equals de String 
     *  cuando los valores Hash de this y otro Termino son iguales
     */
    public boolean equals(Object otro) {
        if (!(otro instanceof Termino)) {
            return false;   
        } else {
            Termino aux = (Termino) otro;   // POO de los c******
            return (this.valorHash == aux.valorHash);
        }
    }
    
    /** Devuelve un String que representa un Termino en cierto formato texto */
    public String toString() { return palabra + " (" + valorHash + ")\n"; }
    
    /** Comprueba si una palabra, p, puede ser un termino del Indice de una BD, 
     *  i.e. si p esta formada por letras del alfabeto español en minusculas.
     */
    protected static boolean esValido(String p) {
        if (p.length() == 0) return false; 
        for (int i = 0; i < p.length(); i++) {
            if (!Character.isLetter(p.charAt(i))) return false; 
        }
        return true;
    }
}
