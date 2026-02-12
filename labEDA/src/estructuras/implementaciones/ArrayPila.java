package estructuras.implementaciones;

import estructuras.modelos.Pila;

/*
    ENUNCIADO:
    Ejercicio 1.1 - Implementa las clases ArrayPila y LEGPila, implementaciones del modelo Pila
    mediante un array y una lista enlazada, respectivamente
 */

public class ArrayPila<E> implements Pila<E> {
    protected E elArray[];
    protected int tope;
    protected static final int CAPACIDAD_POR_DEFECTO = 50;

    @SuppressWarnings("unchecked")
    public ArrayPila() {
        elArray = (E[]) new Object[CAPACIDAD_POR_DEFECTO];
        tope = -1;
    }

    public void apilar(E x) {
        if (tope + 1 == elArray.length) duplicarPila();
        elArray[++tope] = x;
    }

    public E desapilar() { return elArray[tope--]; }
    public E tope() { return elArray[tope]; }
    public boolean esVacia() { return tope == -1; }

    private void duplicarPila() {
        @SuppressWarnings("unchecked")
        E[] nuevo = (E[]) new Object[elArray.length * 2];
        System.arraycopy(elArray, 0, nuevo, 0, elArray.length);
        elArray = nuevo;
    }
}