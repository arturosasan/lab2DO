package librerias.estructurasDeDatos.modelos;

public interface PilaExt<E extends Comparable<E>> extends Pila<E> {
    E base();

    E minimo();
}
