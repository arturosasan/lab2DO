package estructuras.implementaciones;

import estructuras.modelos.Cola;

public class ArrayCola<E> implements Cola<E> {
    protected E elArray[];
    protected static final int CAPACIDAD_POR_DEFECTO = 30000;
    protected int finalC, principioC, talla;

    public ArrayCola() {
        elArray = (E[]) new Object[CAPACIDAD_POR_DEFECTO];
        talla = 0; principioC = 0; finalC = 0;
    }

    protected int incrementar(int index) {
        if (++index == elArray.length) index = 0;
        return index;
    }

    protected void duplicarArrayCircular() {
        // implementar: crear array nuevo de doble tamaño y copiar elementos en orden
    }

    public void encolar(E e) {
        if (talla == elArray.length) duplicarArrayCircular();
        elArray[finalC] = e;
        finalC = incrementar(finalC);
        talla++;
    }

    public E desencolar() {
        E result = elArray[principioC];
        elArray[principioC] = null;
        principioC = incrementar(principioC);
        talla--;
        return result;
    }

    public E primero() {
        return elArray[principioC];
    }
    public boolean esVacia() {
        return talla == 0;
    }
}
