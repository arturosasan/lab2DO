package aplicaciones.biblioteca;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * BuscadorBD: clase que representa un Buscador eficiente,
 * i.e. con un Indice, de una Biblioteca Digital (BD).
 * Por ello, su funcionalidad basica es...
 * 1.- INDEXAR: crear el Indice de la biblioteca, representado mediante
 * un Map implementado mediante una Tabla Hash Enlazada.
 * 2.- BUSCAR: recuperar del Indice de la biblioteca la informacion que
 * exista sobre una palabra dada (Posting List) en ella.
 * 
 * @author Profesores EDA
 * @version Septiembre 2023
 */

public class BuscadorBD {

  // Un BuscadorBD TIENE...

  // UN String listaLibros, el fichero de texto que contiene los nombres de
  // los (ficheros .txt de los) libros de una biblioteca digital. Su valor
  // por defecto es el que figura a continuacion, por lo que debe cambiarse
  // si procede
  protected static String listaLibros = "lista10.txt"; // "lista.txt"

  // UN String dirListaLibros, el directorio donde se ubica listaLibros.
  // Su valor por defecto es el que figura a continuacion, por lo que debe
  // cambiarse si procede
  protected static String dirListaLibros = "aplicaciones" + File.separator
      + "biblioteca" + File.separator;

  // UN String dirLibros, el directorio donde se ubican los (ficheros .txt
  // de los) libros de listaLibros. Su valor por defecto es el que figura a
  // continuacion, por lo que debe cambiarse si procede
  protected static String dirLibros = "aplicaciones" + File.separator
      + "biblioteca" + File.separator
      + "TXT" + File.separator;

  // UN String separadores, la expresion regular que define los
  // separadores de palabras que aparecen en los libros de listaLibros.
  // Su valor por defecto es el que figura a continuacion, por lo que
  // debe cambiarse si procede
  protected static String separadores = "[[ ]*|[,]*|[\\.]*|[\t]*|[:]*|[;]*|[(]*|[)]*|[/]*|[!]*|[?]*|[¿]*|[“]*|[”]*|[+]*]+";

  // UN int maxTerminos, el numero de terminos que, como maximo, contienen
  // los libros de listaLibros. Su valor por defecto es el que figura a
  // continuacion, por lo que debe cambiarse si procede
  protected static int maxTerminos = 22310; // 105985 para "lista.txt"

  // UN Map index, que representa el Indice de una biblioteca digital.
  // La clase de sus Claves es Termino y la de sus Valores ListaConPI<Posting>
  protected Map<Termino, ListaConPI<Posting>> index;

  // UN boolean verb, que activa/desactiva el modo "verbose" del buscador.
  // Su valor por defecto es el que figura a continuacion, por lo que debe
  // cambiarse (a false) si procede
  protected static boolean verb = true; // false;

  public static void setVerb(boolean b) {
    verb = b;
  }

  /**
   * Crea el Buscador de la BD que forman los libros de listaLibros.
   * Basicamente, supone crear el Indice de la BD con, como maximo, maxTerminos.
   * Si no encuentra (el fichero .txt de) un libroLanza FileNotFoundException.
   */
  public BuscadorBD() throws FileNotFoundException {
    boolean res = true;
    Scanner fich = new Scanner(new File(dirListaLibros + listaLibros));
    if (verb) {
      System.out.println("Creando el Indice de la biblioteca... " + listaLibros);
    }

    // Inicializar el Map index que tiene el Buscador
    // usando como tipo dinamico la clase TablaHash.
    // NOTA: el constructor de TablaHash requiere como argumento el
    // numero de Entradas que, como maximo, contendra la tabla
    index = new TablaHash<Termino, ListaConPI<Posting>>(maxTerminos);
    while (fich.hasNext()) {
      String nombreLibro = fich.next();
      String fichLibro = dirLibros + nombreLibro;
      res = indexarLibro(fichLibro);
    }
    if (verb) {
      System.out.println("Terminos del Indice (talla del Map) = " + index.talla());
    }
    if (!res)
      throw new FileNotFoundException();
  }

  /**
   * Devuelve true tras actualizar el Buscador de una BD
   * con los terminos que contiene fichLibro (fichero .txt).
   * Basicamente, supone actualizar el Indice de la biblioteca
   * con los terminos de fichlibro.
   * El metodo devuelve false si fichlibro no existe.
   */
  public boolean indexarLibro(String fichLibro) {
    boolean res = true;
    try {
      Scanner libro = new Scanner(new File(fichLibro));
      int posSep = fichLibro.lastIndexOf(File.separator);
      String titulo = fichLibro.substring(posSep + 1);
      if (verb) {
        System.out.println("Indexando libro... "
            + titulo.substring(0, titulo.indexOf(".txt")));
      }
      int numLin = 0;
      while (libro.hasNext()) {
        String linea = libro.nextLine();
        numLin++;
        String[] palabras = linea.split(separadores);
        Posting nuevo = new Posting(titulo, numLin);
        for (int i = 0; i < palabras.length; i++) {
          String palabra = palabras[i];
          if (Termino.esValido(palabra)) {
            // Actualizar index con el "Posting" nuevo asociado a clave.
            Termino clave = new Termino(palabra.toLowerCase());
            ListaConPI<Posting> valor = index.recuperar(clave);
            if (valor == null)
              valor = new LEGListaConPI<Posting>();
            valor.insertar(nuevo);
            index.insertar(clave, valor);
          }
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error: no se ha encontrado el libro " + fichLibro);
      res = false;
    }
    return res;
  }

  /**
   * Devuelve en formato texto (String) el resultado de la busqueda del termino
   * asociado a una palabra, p, en el Indice de una BD (frecuencia de aparicion
   * del
   * termino y, en su caso, el listado que contiene los titulos y lineas de los
   * libros de la biblioteca en los que aparece, i.e. su Posting List).
   */
  public String buscar(String p) {
    String res = "";
    Termino clave = new Termino(p.toLowerCase());
    ListaConPI<Posting> valor = index.recuperar(clave);
    if (valor == null) {
      res = "La palabra \"" + p + "\" no aparece en ningun libro de esta biblioteca";
    } else {
      res = "Encontradas " + valor.talla() + " apariciones de la palabra \"" + p
          + "\" en...\n" + valor.toString();
    }
    return res;
  }

  /**
   * Devuelve una ListaConPI con aquellos terminos del Indice de una BD que
   * aparecen n veces en sus libros, o null si no existe ninguno.
   */
  public ListaConPI<Termino> terminosNveces(int n) {
    ListaConPI<Termino> res = new LEGListaConPI<Termino>();
    ListaConPI<Termino> aux = index.claves();

    aux.inicio();
    while (!aux.esFin()) {
      Termino auxT = aux.recuperar(); // Termino asociado a la lista de CLAVES (1er elemento de la 2-tupla
                                      // Map<Termino,<Posting>>)
      ListaConPI<Posting> posi = index.recuperar(auxT); // Nº de veces que ha aparecido el termino en la lista de
                                                        // indices
      if (n == posi.talla()) {
        res.insertar(auxT);
      }
      aux.siguiente();
    }
    if (res.esVacia()) {
      return null; // ???
    } else {
      return res;
    }
  }

  /**
   * Devuelve una ListaConPI con aquellos terminos del Indice de una BD que
   * aparecen solo una vez en sus libros, i.e. los llamados "hapax legomena"
   * de la BD, o null si no existe ninguno.
   * PISTA: Implementar mediante invocacion al metodo terminosNveces
   */
  public ListaConPI<Termino> hapax() {
    return terminosNveces(1); // Retorna null si esVacia() ??
  }
}
