package estructuras.implementaciones;

import estructuras.modelos.Lista;

public class LEGLista<E> implements Lista<E> {
    protected NodoLEG<E> primero;
    protected int talla;

    public LEGLista() { primero = null; talla = 0; }

    public void insertar(E e, int i) {
        NodoLEG<E> nuevo = new NodoLEG<>(e);
        talla++;
        NodoLEG<E> aux = primero;
        NodoLEG<E> ant = null;
        for (int j = 0; j < i; j++) {
            ant = aux;
            aux = aux.siguiente;
        }
        nuevo.siguiente = aux;
        if (ant == null) primero = nuevo;
        else ant.siguiente = nuevo;
    }

    public void eliminar(int i) { /* implementar */ }
    public E recuperar(int i) { /* implementar */ return null; }
    public boolean esVacia() { return talla == 0; }
    public int talla() { return talla; }
}
