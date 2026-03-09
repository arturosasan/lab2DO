package ejemplos.tema2;

import java.util.Arrays;

public class ejt2 {

  // EJERCICIO 1.1

  public static int puntoCruce(int[] v) {
    return puntoCruce(v, 0, v.length - 1);
  }

  public static int puntoCruce(int[] v, int posI, int posF) {
    int mitad = (posI + posF) / 2;
    if (v[mitad] >= 0) {
      if (v[mitad + 1] > 0)
        return mitad;
      else
        return puntoCruce(v, mitad + 1, posF);
    } else
      return puntoCruce(v, posI, mitad - 1);
  }

  // EJERCICIO 1.2

  public static int minimoCC(int[] v) {
    return minimoCC(v, 0, v.length - 1);
  }

  public static int minimoCC(int[] v, int posI, int posF) {
      int m = (posI + posF) / 2;
      /*
      if(m==0) {
        if(v[0]<v[1]) return 0;
        else return 1;
      }
       */
      if (/*(m == v.length - 1) ||*/ v[m] < v[m - 1] && v[m] < v[m + 1]) return m;
      else {
          if (v[m - 1] < v[m]) return minimoCC(v, posI, m - 1);
          else return minimoCC(v, m + 1, posF);
      }
    }

    // EJERCICIO 1.3

    public static int posIguales(int[] v) {
      return posIguales(v, 0, v.length - 1);
    }

    public static int posIguales(int[] v, int posI, int posF) {
        int m = (posI + posF) / 2;
        if (posF > posI) return -1;
        if (v[m] == m) {
            return m;
        } else {
            if (v[m] < m) return posIguales(v, posI, m - 1);
            else return posIguales(v, m + 1, posF);
        }
    }

    // EJERCICIO 1.4

    public static boolean dosStrings(String[] v, String x, String y) {
        return dosStrings(v, x, y, 0, v.length - 1);
    }

    public static boolean dosStrings(String[] v, String x, String y, int posI, int posF){
        int m = (posI + posF) / 2;
        if (v[m].compareTo(x) == 0) { // StringX encontrado
            return v[m + 1].compareTo(y) == 0; // Si el siguiente es igual a X, devolverá true
        } else if (v[m].compareTo(x) > 0) {
            return dosStrings(v, x, y, 0, m - 1);
        } else {
            return dosStrings(v, x, y, m + 1, posF);
        }
    }

    // EJERCICIO 2.2
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeDV(T[] v, int i, int m, int j) {
      T[] res = (T[]) new Comparable[j - i + 1];
      if (i > j) {
          int aux1 = i;
          int aux2 = j;
          int k = 0;
          while (aux1 < m && aux2 <= j) {
              if (v[i].compareTo(v[j]) < 0) {
                  res[k++] = res[aux1++];
              } else {
                  res[k++] = res[aux2++];
              }
          }
          for (int aux3 = aux1; aux3 < m; aux3++) res[k++] = res[aux3];
          for (int aux3 = aux2; aux3 < m; aux3++) res[k++] = res[aux3];
          for (int aux3 = 0; aux3 < res.length; aux3++) v[aux3 + i] = res[aux3];
      }
    }

    /*
    public static int subMax(int[] v) {
      return subMax(v, 0, v.length - 1);
    }

    public static int subMax(int[] v, int posI, int posF){
      int m = (posI + posF) / 2;
      int subMaxI = subMax(v, 0, m - 1);
      int subMaxD = subMax(v, m + 1, posF);

      int subMaxInicio, subMaxFinal = 0;
      for (int i = m; i < subMaxFinal; i--) {

      }
    }
     */

    // EJERCICIO 2.5

    public static int EXREC2021 (int[] v) {
        return EXREC2021(v, 0, v.length - 1);
    }
    public static int EXREC2021 (int[] v, int posI, int posF) {
        if (posI < posF) {
            int m = (posI + posF) / 2;
            if (v[m] == v[m - 1] || v[m] == v[m + 1]) {
                return v[m];
            } else if (v[m] > v[m - 1]) {
                return EXREC2021(v, 0, m - 1);
            } else return EXREC2021(v, m + 1, posF);
        } else return -1;
    }

    public static void main (String[] args) {
        int[] ej1 = {1, 2, 3, 4, 4, 5, 6, 7, 8}; // OK
        System.out.println("EJEMPLO 1 " + Arrays.toString(ej1) + " -> " + EXREC2021(ej1) + " == 4");

        int[] ej2 = {1, 2, 2, 3, 4, 5, 6, 7, 8}; // OK
        System.out.println("EJEMPLO 2 " + Arrays.toString(ej2) + " -> " + EXREC2021(ej2) + " == 2");

        int[] ej3 = {1, 2, 3, 4, 5, 6, 7, 8, 8}; // ERROR
        System.out.println("EJEMPLO 3 " + Arrays.toString(ej3) + " -> " + EXREC2021(ej3) + " != 8");

        int[] ej4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; // OK
        System.out.println("EJEMPLO 4 " + Arrays.toString(ej4) + " -> " + EXREC2021(ej4) + " == -1");

        /*
        int[] ej5 = {1, 1, 2, 3, 4}; // NULLPOINTEREXCEPTION
        System.out.println("EJEMPLO 5 " + Arrays.toString(ej5) + " -> " + EXREC2021(ej5));
        */

        int[] ej6 = {3, 4, 4}; // OK
        System.out.println("EJEMPLO 6 " + Arrays.toString(ej6) + " -> " + EXREC2021(ej6) + " == 4");
    }
}