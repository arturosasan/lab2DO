package aplicaciones.editor;

public class NodoPalabra implements Comparable<NodoPalabra> {
    protected String palabra;
    protected int frecuencia;
    protected int prioridad;
    private static final int MAX = 25979921;
    public NodoPalabra(String s, int i) { 
        palabra = s;
        frecuencia = i;
        prioridad = MAX - frecuencia;
    }
    public int compareTo(NodoPalabra ip) {
        return (this.frecuencia - ip.frecuencia);
    }
    public InfoPalabra convertir() {
        return new InfoPalabra(palabra, frecuencia);
    }
    public String toString() {
        return palabra + " (" + frecuencia + ")";
    }
}