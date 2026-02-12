package estructuras.modelos;

public interface Pila<E> {
    void apilar(E e);
    E desapilar();    // precondición: !esVacia()
    E tope();         // precondición: !esVacia()
    boolean esVacia();
}