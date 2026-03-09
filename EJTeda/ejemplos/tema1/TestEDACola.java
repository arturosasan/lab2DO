package ejemplos.tema1;

import librerias.estructurasDeDatos.lineales.*;
import librerias.estructurasDeDatos.modelos.*;

public class TestEDACola {
  public static void main(String[] args) {      
      Cola<Integer> q = new ArrayCola<Integer>(); // HACER LA COLA DE ENTEROS

      int tallaCola = 0;

      System.out.println("Creada una Cola con " + tallaCola + " Integer, q = " + q.toString());

      q.encolar(new Integer(10));
      tallaCola++;

      q.encolar(new Integer(20));
      tallaCola++;

      q.encolar(new Integer(30));
      tallaCola++;

      System.out.println("La Cola de Integer actual es q = " + q.toString());
      System.out.println("Usando otros metodos para mostrar sus Datos el resultado es ...");
      String datosQ = "";
      while (!q.esVacia()) {
          Integer primero = q.primero();
          if (primero.equals(q.desencolar())) {
              datosQ += primero + " ";
              tallaCola--;
          }
          else datosQ += "ERROR ";
      }
      System.out.println(" el mismo, " + datosQ 
          + ", PERO q se vacia, q = " + q.toString());
  }
}