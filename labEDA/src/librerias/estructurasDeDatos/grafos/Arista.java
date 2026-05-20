package librerias.estructurasDeDatos.grafos;

/** Clase Arista: representa una arista de un grafo.
 *  
 *  @version noviembre 2021
 */
 
public class Arista {
    
    // UNA Arista TIENE UN vertice origen y UN vertice destino:
    private int vOrigen, vDestino;
    
    // UNA Arista TIENE UN peso:
    private double peso;
    
    /** Crea una arista (v, w) con peso p.
      *
      * @param v  Vertice origen
      * @param w  Vertice destino
      * @param p  Peso
     */
    public Arista(int v, int w, double p) {
        this.vOrigen = v; this.vDestino = w;
        this.peso = p;
    }

    /** Devuelve el vertice origen de una arista.
      *
      * @return int vertice origen
     */
    public int getOrigen() {
        return this.vOrigen;

    }
    
    /** Devuelve el vertice destino de una arista.
      *
      * @return int vertice destino
     */
    public int getDestino() {
        return this.vDestino;
    }
    
    /** Devuelve el peso de una arista.
      *
      * @return double Peso de la arista
     */
    public double getPeso() {
        return this.peso;
    }
    
    /** Devuelve un String que representa una arista
      * en formato (origen, destino, peso)
      *
      * @return  String que representa la arista
     */
    @Override
    public String toString() {
        return "(" + this.vOrigen + ", " + this.vDestino + ", " + this.peso + ")";
    }
}