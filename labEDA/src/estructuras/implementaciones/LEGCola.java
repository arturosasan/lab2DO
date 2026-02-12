package estructuras.implementaciones;

import estructuras.modelos.Cola;

public class LEGCola<E> implements Cola<E> {
    protected NodoLEG<E> primero, fin;
    public LEGCola() { primero = fin = null; }

    public void encolar(E e) {
        if (primero == null) primero = fin = new NodoLEG<>(e);
        else {
            fin.siguiente = new NodoLEG<>(e);
            fin = fin.siguiente;
        }
    }

    public E desencolar() {
        E elPrimero = primero.dato;
        primero = primero.siguiente;
        if (primero == null) fin = null;
        return elPrimero;
    }

    public E primero() { return primero.dato; }
    public boolean esVacia() { return primero == null; }
}
