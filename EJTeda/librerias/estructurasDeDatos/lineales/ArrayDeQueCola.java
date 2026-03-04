package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.Cola;

import java.util.ArrayDeque;

public class ArrayDeQueCola<E> extends ArrayDeque<E> implements Cola<E> {
    protected ArrayDeque elArray;
    /** constructor **/
    @SuppressWarnings("unchecked")
    public ArrayDeQueCola() {
        elArray = new ArrayDeque();
    }
    /** inserta e al final de la cola **/
// SII la inserción supera el tamaño de la matriz, ArrayDeque
// duplica automáticamente su tamaño hasta 64 e incrementa size()/2
// a partir de tamaños mayores.
    public void encolar(E e) {
        elArray.add(e);
    }
    /** SII !esVacia ():
     * retira y borra el elemento al principio de la cola **/
    public E desencolar() {
        return (E) elArray.poll();
    }
    /** SII !isEmpty():
     ** retira pero no borra el elemento al principio de la cola, en orden
     * de inserción **/
    public E primero() { return (E) elArray.peekFirst(); }
    /** comprueba si la cola está vacía **/
    public boolean esVacia() { return elArray.size() == 0; }
    /** obtiene un String con los Elementos de una Cola en orden FIFO,
     * u orden de inserción, y en el formato utilizado en el estándar Java.
     * Así, por ejemplo, si tienes una Cola con Enteros 1 a 4,
     * en orden FIFO, toString devuelve [1, 2, 3, 4];
     * si la cola está vacía, devuelve [].*/
    public String toString() {
// NOTA: Se utiliza la clase StringBuilder en lugar de String,
// por razones de eficiencia
        StringBuilder res = new StringBuilder();
        res.append("[");
//Iterator i = elArray.iterator();
//while (i.hasNext())
// res.append(i.toString() + ", ");
        for(Integer j = 0; j < elArray.size();j++ )
            res.append(elArray.toArray()[j].toString());
        res.append("]");
        return res.toString();
    }
}
