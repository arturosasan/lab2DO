package ejercicios.tema1;

import estructuras.implementaciones.LEGListaConPI;

/*
    ENUNCIADO:
    Ejercicio 3.- Amplia la funcionalidad de la EDA Lista con Punto de Interés vía herencia con los
    siguientes métodos:

        • void buscar(E x): sitúa el PI sobre x. Si el dato no se encuentra se colocará el PI al final de la lista
        • void vaciar(): vacía la lista
        • int talla(): devuelve el número de elementos que contiene la lista
        • String toString(): devuelve una cadena con la descripción de los elementos de la lista
        • void invertir(): invierte el orden de los elementos de la lista
        • void eliminar(E x): elimina de la lista todos los elementos iguales a x.

    Utiliza para ello únicamente los métodos existentes en el modelo ListaConPI
 */

public class LEGListaConPIExt<E> extends LEGListaConPI<E> {

    public void buscar(E x) {
        inicio();
        while (!esFin() && !recuperar().equals(x)) {
            siguiente();
        }
    }

    public void vaciar() {
        inicio();
        while (!esFin()) {
            eliminar();
        }
    }

    @Override
    public int talla() {
        int contador = 0;
        inicio();
        while (!esFin()) {
            contador++;
            siguiente();
        }
        return contador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        inicio();
        while (!esFin()) {
            sb.append(recuperar());
            siguiente();
            if (!esFin()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public void invertir() {
        LEGListaConPIExt<E> aux = new LEGListaConPIExt<>();
        inicio();
        while (!esFin()) {
            E elem = recuperar();
            aux.inicio();
            aux.insertar(elem);  // insertar siempre al principio
            siguiente();
        }

        vaciar();

        aux.inicio();
        while (!aux.esFin()) {
            insertar(aux.recuperar());
            aux.siguiente();
        }
    }

    public void eliminar(E x) {
        inicio();
        while (!esFin()) {
            if (recuperar().equals(x)) {
                eliminar();
            } else {
                siguiente();
            }
        }
    }
}
