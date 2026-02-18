package librerias.estructurasDeDatos.lineales;

class NodoLEG<E> {
    E dato;
    NodoLEG<E> siguiente;
   
    NodoLEG(E e, NodoLEG<E> n) {
        dato = e; siguiente = n;
    }

    NodoLEG(E dato) { this(dato, null); } 
}