package estructuras.implementaciones;

public class NodoLEG<E> {
    protected E dato;
    protected NodoLEG<E> siguiente;
    public NodoLEG(E dato, NodoLEG<E> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    public NodoLEG(E dato) {
        this(dato, null);
    }
}