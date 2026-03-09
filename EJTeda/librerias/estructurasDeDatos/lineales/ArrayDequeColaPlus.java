package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ColaPlus;

// EJERCICIO 3.2

public class ArrayDequeColaPlus<E> extends ArrayDequeCola<E> implements ColaPlus<E> {
    public int talla() {
        return elArray.size();
    }
}
