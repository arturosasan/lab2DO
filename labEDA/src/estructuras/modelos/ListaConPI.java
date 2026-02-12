package estructuras.modelos;

public interface ListaConPI<E> {
    void insertar(E e);    // inserta antes del PI
    void eliminar();       // si !esFin()
    E recuperar();         // si !esFin()
    void inicio();         // sitúa PI en inicio
    void siguiente();      // si !esFin()
    boolean esFin();
    boolean esVacia();
    void fin();            // sitúa PI detrás del último elemento
    int talla();
}

