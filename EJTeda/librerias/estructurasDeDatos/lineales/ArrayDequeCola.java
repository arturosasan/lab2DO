package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.Cola;

// EJERCICIO 3.1

public class ArrayDequeCola<E> extends ArrayDeQueCola<E> implements Cola<E> {

    @SuppressWarnings("unchecked")
    ArrayDequeCola() {
        elArray = new ArrayDequeCola();
    }

    @SuppressWarnings("unchecked")
    public void encolar(E e) {
        elArray.add(e);
    }

    @SuppressWarnings("unchecked")
    public E desencolar() {
        return (E) elArray.poll();
    }

    @SuppressWarnings("unchecked")
    public E primero() {
        return (E) elArray.peekFirst();
    }

    @SuppressWarnings("unchecked")
    public boolean esVacía() {
        return primero() == null;
    }

    @SuppressWarnings("unchecked")
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append("[");
        for (int i = 0; i < elArray.size(); i++) {
            SB.append(elArray.toArray()[i].toString());
        }
        SB.append("]");
        return SB.toString();
    }
}
