package estructuras.implementaciones;

import estructuras.modelos.ListaConPI;

public class LEGListaConPI<E> implements ListaConPI<E> {
    protected NodoLEG<E> pri, ant, ult;
    protected int talla;

    public LEGListaConPI() {
        pri = ult = ant = new NodoLEG<E>(null);
        talla = 0;
    }

    public void insertar(E e) {
        NodoLEG<E> nuevo = new NodoLEG<E>(e, ant.siguiente);
        ant.siguiente = nuevo;
        if (nuevo.siguiente == null) ult = nuevo;
        ant = ant.siguiente;
        talla++;
    }

    public void eliminar() { /* implementar */ }
    public E recuperar() { return ant.siguiente.dato; } // si !esFin()
    public void inicio() { ant = pri; }
    public void siguiente() { if (!esFin()) ant = ant.siguiente; }
    public boolean esFin() { return ant.siguiente == null; }
    public boolean esVacia() { return talla == 0; }
    public void fin() { ant = ult; }
    public int talla() { return talla; }
}
