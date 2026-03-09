package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

// EJERCICIO 1.2

public class LEGCola<E> implements Cola<E>{
    protected NodoLEG<E> principioC, finalC;
    protected int talla;

    public LEGCola() {
        principioC = finalC = null;
        talla = 0;
    }

    public void encolar(E e) {
        NodoLEG<E> nuevo = new NodoLEG<E>(e);
        if ( finalC == null ) {
            principioC = nuevo; // Si está vacia, el elemento a encolar es el primero
        } else {
            finalC.siguiente = nuevo;
        }
        finalC = nuevo;
        talla++;
    }

    public E desencolar () {
        E datoPI = principioC.dato;
        principioC = principioC.siguiente;
        if ( principioC == null) {
            finalC = null;
        }
        talla--;
        return datoPI;
    }

    public E primero() {
        return principioC.dato;
    }

    public boolean esVacia() {
        return ( principioC == null );
    }
}
