package aplicaciones.hospital;

import aplicaciones.censo.Habitante;

/** Clase Paciente: representa un paciente a operar.
 *      
 *  @author  Profesores EDA
 *  @version Septiembre 2023
 */
 
public class Paciente implements Comparable<Paciente> {
    
    private Habitante id;
    
    /** tipo de cirugia
     *  ...
     */
    private String cirugia; 
    
    /** indicador de la gravedad del paciente, 
     *  i.e., de la urgencia de su cirugia
     *  rango de valores [0..9], mayor valor -> menos urgente
     */
    private int gravedad; 
    
    /** tiempo (en numero de horas) de ingreso del paciente
     *  en Lista de Espera (atributo: ingresoEnEspera)
     *  y en Quirofano (atributo: ingresoEnQuirofano)
     */
    private int ingresoEnEspera; 
    private int ingresoEnQuirofano; 
    
    /** Crea un Paciente de cirugia c, gravedad g
     *  y enviado a "lista espera" en la hora h. 
     *  @param c   String
     *  @param g   int
     *  @param h   int (horas)
     */
    public Paciente(String c, int g, int h) {
        id = new Habitante();
        cirugia = c;
        gravedad = g;
        ingresoEnEspera = h;
    }
    
    /** Devuelve la cirugia de un Paciente.
     *  @return   String
     */
    public String getCirugia() { return cirugia; }
    
    /** Devuelve la gravedad de un Paciente.
     *  @return  int
     */
    public int getGravedad() { return gravedad; }
    
    /** Devuelve las horas de ingreso de un Paciente.
     *  @return   int (horas)
     */
    public int getIngresoEnEspera() { return ingresoEnEspera; }
    public int getIngresoEnQuirofano() { return ingresoEnQuirofano; }
    
    /** Devuelve el tiempo (en horas) que un Paciente esta en la Lista de Espera
     *  @return   int (horas)
     */
    public int getDemora() { return ingresoEnQuirofano - ingresoEnEspera; }
    
    public void setIngresoEnQuirofano(int t) { ingresoEnQuirofano = t; }
        
    /** Devuelve el valor int que resulta de comparar la gravedad
     *  de un Paciente (this) con la de otro. Dicho valor sera...
     *  
     *  ** NEGATIVO si un paciente (this) tiene MAS gravedad que el otro,
     *     (MAS gravedad -> menor valor del atributo gravedad)
     *     i.e. si su cirugia es MAS prioritaria que la del otro
     *     
     *  ** POSITIVO si un paciente (this) tiene MENOS gravedad que otro, 
     *     i.e. si su cirugia es MENOS prioritaria que la del otro
     *     
     *  ** CERO si ambos pacientes tienen la misma gravedad
     *  
     *  @param otro   Paciente  
     *  @return int   resultado de la comparacion de un Paciente (this) con otro 
     */
    public int compareTo(Paciente otro) {
        //if (!(otro instanceof Paciente)) return 0;
        //if (this.gravedad < otro.gravedad) { return -1; }
        //else if (this.gravedad > otro.gravedad) { return 1; }
        //else return 0;
        return (this.gravedad - otro.gravedad);
        // + EFICIENTE
        // RESTA DE GRAVEDADES Y DEVOLVER RESTA
    }
    
    /** Devuelve el String que representa un Paciente en un cierto formato texto.
     *  @return   String
     */
    public String toString() {
        return cirugia + " (gravedad: " + gravedad + ") " 
            + ", \tEspera: " + ((ingresoEnQuirofano - ingresoEnEspera) / 24) 
            + " DIAS. \t " + id;
    }
}
