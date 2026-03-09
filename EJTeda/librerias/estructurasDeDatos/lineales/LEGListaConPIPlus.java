package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

import javax.swing.*;


public class LEGListaConPIPlus<E> extends LEGListaConPI<E> implements ListaConPIPlus<E> {

    public boolean contiene (E e) {
        inicio();
        while (!esFin() && !e.equals(recuperar())) {
            siguiente();
        }
        return !esFin();
    }

    public boolean eliminarPrimero (E e) {
        inicio();
        while (!esFin()) {
            if (e.equals(recuperar())) {
                eliminar();
                return true;
            }
            siguiente();
        }
        return false;
    }

    public boolean eliminarUltimo(E e) {
        NodoLEG<E> ult = null;
        inicio();
        while (!esFin()) {
            if (e.equals(recuperar())) {
                ult = ant;
                siguiente();
            }
        }
        if (ult == null) { // NO SE HA ENCONTRADO EL ELEMENTO
            return false;
        } else {
            ant = ult;
            eliminar();
            return true;
        }
    }

    public boolean eliminarTodos(E e) {
        boolean eliminado = false;
        inicio();
        while (!esFin()) {
            if (e.equals(recuperar())) {
                eliminar();
                eliminado = true;
            } else {
                siguiente();
            }
        }
        return eliminado;
    }

    public void vaciar() {
        inicio();
        while (talla() > 0) {
            eliminar();
        }
    }

    public void concatenar(ListaConPI<E> otra) {
        // this.fin(); // DUDA ?????
        otra.inicio();
        while (!otra.esFin()) {
            E datoIns = otra.recuperar();
            this.insertar(datoIns);
            otra.siguiente();
        }
    }

    public void invertir() {
        while(!esVacia()) {
            inicio();
            E aux = recuperar(); // PRIMERO COJO EL DATO Y ME LO GUARDO
            eliminar();          // DESPUÉS LO ELIMINO
            invertir();          // LLAMADA RECURSIVA PARA ELIMINAR TODOS LOS ELEMENTOS ( EN ""PILA"" )
            insertar(aux);       // Y SE INSERTA EL DATO EN LA PILA, QUE ESTARÁ VACÍA PARA METER TODOS LOS DATOS EN ORDEN INVERSO
        }
    }

    public void buscar(E e) {
        inicio();
        while(!esVacia() && !e.equals(recuperar())) {
            siguiente();
        }
    }

    public String toString() {
        StringBuilder SB = new StringBuilder();
        while(!esVacia()) {
            SB.append("]")
                    .append(recuperar())
                        .append("]")
                            .append("\n");
        }
        return SB.toString();
    }

    // EJERCICIO 4.3

    public void moverAIzquierda() {
        while(!esFin()) {
            ult.siguiente = pri.siguiente;
            pri.siguiente = pri.siguiente.siguiente;
            ult = ult.siguiente;
            ult.siguiente = null;
        }
    }

    // EJERCICIO 4.4

    public static <E extends Comparable<E>> Pila<E> EXAMEN2021_1(ListaConPI<E> L1, ListaConPI<E> L2) {
        Pila<E> res = new ArrayPila<E>();
        L1.inicio();
        L2.inicio();
        while(!L1.esFin() && !L2.esFin()) {
            E aux1 = L1.recuperar();
            E aux2 = L2.recuperar();
            int comparador = aux1.compareTo(aux2);
            if (comparador == 0) { // IGUALES, NO IMPORTA EL ORDEN
                res.apilar(aux1); // O res.apilar(aux2)
                L1.eliminar();
                L2.eliminar();
            } else if (comparador < 0 ) { // ELEMENTO DE L1 ES MENOR
                L1.siguiente();
            } else {                     // ELEMENTO DE L2 ES MENOR
                L2.siguiente();
            }
        }
        return res;
    }

    // EJERCICIO 4.5 DUDA

    public static <E extends Comparable<E>> boolean EXAMEN2021_2(ListaConPI<E> L1, ListaConPI<E> L2) {
        L1.inicio();
        L2.fin();
        while (!L1.esFin()) {
            if (L1.recuperar() == L2.recuperar()) {
                return true;
            } else { return false; }
        }
        return false;
    }
}
