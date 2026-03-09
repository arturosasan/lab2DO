package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

// EJERCICIO 2.1

public class LEGPilaExt<E extends Comparable<E>> extends LEGPila<E> implements PilaExt<E> {

    // SOLUCIÓN A) -> Accediendo a los atributos de LEGPila
    public E base() {
        NodoLEG<E> aux = tope;
        while (aux.siguiente != null) {
            aux = aux.siguiente;
        }
        return aux.dato;
    }

    //SOLUCIÓN B) -> Utilizando únicamente los métodos del modelo
    public E baseB() {
        E res, aux = desapilar(); // HERENCIA
        if (esVacia()) {
            res = aux;
        } else {
            res = base(); // NO HERENCIA
        }
        apilar(aux); // HERENCIA
        return res;
    }

    public E minimo() {
        NodoLEG<E> aux = tope;
        E min = null;
        while (aux != null) {
            if (min == null || aux.dato.compareTo(min) < 0) {
                min = aux.dato;
            }
            aux = aux.siguiente;
        }
        return min;
    }

    // EJERCICIO 5.1

    public E minimoB() {
        if (esVacia()) return null;
        E aux = desapilar();
        E min = minimoB();
        apilar(aux);
        if (min == null || aux.compareTo(min) < 0) {
            return aux;
        }
        return min;
    }
}
