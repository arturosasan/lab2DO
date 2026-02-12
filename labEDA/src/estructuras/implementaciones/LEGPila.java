package estructuras.implementaciones;

import estructuras.modelos.Pila;

/*
    ENUNCIADO:
    Ejercicio 1.2 - Implementa las clases ArrayPila y LEGPila, implementaciones del modelo Pila
    mediante un array y una lista enlazada, respectivamente
 */

public class LEGPila<E> implements Pila<E> {
    protected NodoLEG<E> tope;
    public LEGPila() { tope = null; }
    public void apilar(E x) { tope = new NodoLEG<>(x, tope); }
    public E desapilar() {
        E x = tope.dato;
        tope = tope.siguiente;
        return x;
    }
    public E tope() { return tope.dato; }
    public boolean esVacia() { return tope == null; }
}