package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ColaExt;

// EJERCICIO 2.2

public class ArrayColaExt<E> extends ArrayCola<E> implements ColaExt<E> {

    // SOLUCIÓN A) -> Accediendo a los atributos de ArrayCola
    public void invertir() {
        int i = principioC, j = finalC;
        for (int cont = 0; cont < talla/2; cont++) {
            E aux = elArray[i];
            elArray[i] = elArray[j];
            elArray[j] = aux;
            if (++i == elArray.length) {
                i = 0;
            }
            if (--j == -1) {
                j = elArray.length - 1;
            }
        }
    }

    // SOLUCIÓN B) -> Utilizando únicamente los métodos del modelo
    public void invertirB() {
        if (!esVacia()) {
            E tmp = desencolar();
            invertir();
            encolar(tmp);
        }
    }
}
