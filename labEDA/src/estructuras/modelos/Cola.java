package estructuras.modelos;

public interface Cola<E> {
    void encolar(E e);
    E desencolar();   // precondición: !esVacia()
    E primero();      // precondición: !esVacia()
    boolean esVacia();
}
