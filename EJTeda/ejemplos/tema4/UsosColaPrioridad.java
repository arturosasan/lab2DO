package ejemplos.tema4;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * class UsosColaPrioridad.
 * 
 * @author FTG 
 * @version 1.0
 */

public class UsosColaPrioridad {
    
    /** Problema 1: 
     *  Disenyar un metodo estatico e iterativo cPSort 
     *  que, con la ayuda de una Cola de Prioridad, 
     *  ordene un array v de elementos Comparable. 
     */
    public static <E extends Comparable<E>> void cPSort(E[] v) {
    }
    
    /** Problema 2:
     *  Disenyar un metodo estatico, generico e iterativo cPFusionar 
     *  que devuelva una ListaConPI con los datos de 2 Colas de Prioridad dadas, 
     *  cP1 y cP2, ordenados ascendentemente. 
     *  El metodo no puede usar ninguna EDA auxiliar para calcular su resultado 
     *  y, ademas, cP1 y cP2 deben quedar vacias al concluir su ejecucion.
     */
    public static <E extends Comparable<E>> ListaConPI<E> cPFusionar(
        ColaPrioridad<E> cP1, ColaPrioridad<E> cP2) {

        ListaConPI<E> res = new LEGListaConPI<E>();

        while (!cP1.esVacia() && !cP2.esVacia()) {
            if (cP1.recuperarMin().compareTo(cP2.recuperarMin()) < 0) {
                res.insertar(cP1.eliminarMin());
            } else {
                res.insertar(cP2.eliminarMin());
            }
        }

        while (!cP1.esVacia()) { res.insertar(cP1.eliminarMin()); }

        while (!cP2.esVacia()) { res.insertar(cP2.eliminarMin()); }

        return res;
    }
    
    /** Problema 3:
     *  Disenyar un metodo estatico e iterativo cPEsLineal 
     *  que determine si un conjunto de valores reales se ajusta (aprox.) 
     *  a una funcion lineal creciente usando el siguiente algoritmo: 
     *  comprobar si la diferencia entre todo par de valores consecutivos, 
     *  en orden ascendente, esta acotada por un epsilon dado. 
     */
    public static boolean cPEsLineal(ColaPrioridad<Double> cP, double epsilon) {
        // COMPLETAR
        return true;
    }
    
    /** Problema 4:
     *  Disenyar un metodo estatico, generico e iterativo cPTopK 
     *  que, dado un array de datos v y un entero k, 
     *  devuelva una Cola de Prioridad con los k mejores (Top K) datos de v. 
     *  El metodo debe tener un coste O(X log k), siendo X la longitud de v.
     */
    public static <E extends Comparable<E>> ColaPrioridad<E> cPTopK(
        E[] v, int k) {

        ColaPrioridad<E> res = new MonticuloBinario<>(k + 1); // Solo se crea un Mont. de K elemntos

        for (int i = 0; i < v.length; i++) {
            res.insertar(v[i]);
            if (res.recuperarMin().compareTo(v[i]) > k) { // Solo meto los que sean mejores que el peor ( el de valor minimo )
                res.eliminarMin();
                res.insertar(v[i]);
            }
        }

        return res;
    }
    
}