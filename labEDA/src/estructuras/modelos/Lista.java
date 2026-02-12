package estructuras.modelos;

public interface Lista<E> {
    void insertar(E e, int i); // 0 <= i <= talla()
    void eliminar(int i);      // 0 <= i < talla()
    E recuperar(int i);        // 0 <= i < talla()
    boolean esVacia();
    int talla();
}