package ejercicios.tema1;

import estructuras.modelos.Cola;
import estructuras.implementaciones.LEGListaConPI;

/*
    ENUNCIADO:
    Ejercicio 2.- Implementa la interfaz Cola mediante una ListaConPI (suponer que tenemos la clase
    LEGListaConPI como implementación de esta interfaz) --> IMPORTADAS
 */

public class LPICola<E> extends LEGListaConPI<E> implements Cola<E> {

    @Override
    public void encolar(E e) {
        fin();          // colocamos PI detrás del último
        insertar(e);    // insertar antes del PI => al final real
    }

    @Override
    public E desencolar() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacía");
        }
        inicio();                // PI al inicio
        E primero = recuperar(); // obtenemos primer elemento
        eliminar();              // lo eliminamos
        return primero;
    }

    @Override
    public E primero() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacía");
        }
        inicio();
        return recuperar();
    }
}
