package aplicaciones.editor;

import librerias.estructurasDeDatos.jerarquicos.ABB;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.ListaConPI;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.util.Ordenacion;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;

/** Clase EditorPredictivoPlus: 
 *  es un Arbol Binario de Busqueda de elementos de tipo String,   
 *  que representan las palabras de un idioma. 
 *  Esta clase proporciona los metodos necesarios 
 *  para la gestion de un editor predictivo. 
 *  
 *  @author  Profesores EDA
 *  @version Febrero 2026
 */

public class EditorPredictivoPlus extends ABB<NodoPalabra> {

    /** Constructor de un EditorPredictivo vacio
     */
    public EditorPredictivoPlus() { super(); }
    
    /** Constructor de un Editor Predictivo a partir de las palabras 
     *  que contiene un fichero de texto...
     */
    public EditorPredictivoPlus(String nombreFichero) {   
        super();
        try {         
            Scanner fPalabras = new Scanner(new File(nombreFichero), "UTF-8"); 
            int talla = fPalabras.nextInt(); 
            fPalabras.nextLine();
            NodoPalabra[] palabras = new NodoPalabra[talla];
            int nLinea = 0;
            while (fPalabras.hasNext() && nLinea < talla) {
                String linea = fPalabras.nextLine();
                String[] laLinea = linea.split("\t");
                String p = laLinea[0].trim();  
                int f = Integer.parseInt(laLinea[1].trim());
                palabras[nLinea] = new NodoPalabra(p,f);
                nLinea++;                
            }
            fPalabras.close();
            Ordenacion.quickSort(palabras);
            this.raiz = construirEquilibrado(palabras, 0, talla - 1);            
        } catch (FileNotFoundException eChecked) {
            System.out.println("El fichero " + nombreFichero 
                + " no es accesible para lectura, comprueba "
                + "su correcta ubicaci\u00f3n");
        }
    }
        
    /** Anyade la palabra nueva a un Editor Predictivo.
     *  @param  nueva  Palabra a anyadir a las que ya tiene un Editor Predictivo.
     */
    public void incluir(String nueva) { 
        this.insertar(new NodoPalabra(nueva.toLowerCase().trim(),0)); 
    }

    /** Guarda en orden alfabetico las palabras de un Editor Predictivo 
     *  a partir de la segunda linea de un fichero de texto, escribiendo 
     *  su numero en la primera linea.
     *  @param nombreFichero   Nombre del fichero de texto donde se guardaran 
     *                         la talla y palabras en orden alfabetico de un 
     *                         Editor Predictivo.
     */
    public void guardar(String nombreFichero) {
        try { 
            PrintWriter fPalabras = new PrintWriter(nombreFichero, "UTF-8");
            Comparable[] palabras = this.toArrayInOrden();
            fPalabras.println(palabras.length);
            for (int i = 0; i < palabras.length; i++) {
                fPalabras.println((String) palabras[i]);
            }
            fPalabras.close();
        } catch (IOException eChecked) {
            System.out.println("Error guardando el fichero " + nombreFichero 
                + ": " + eChecked);
        }
    }
    
    /** Devuelve una ListaConPI con, como maximo, los n siguientes 
     *  sucesores de prefijo; en el primer lugar de esta lista figurara 
     *  el propio prefijo siempre y cuando sea ya una palabra del editor.
     *  @param  prefijo    Prefijo a partir del que se buscan los "n" 
     *                     siguientes sucesores
     *  @param  n          Numero maximo de sucesores a recuperar
     *  @return            ListaConPI<String>   
     *                     ListaConPI con los sucesores de prefijo obtenidos; 
     *                     su primer elemento es el propio prefijo 
     *                     siempre y cuando sea una palabra del editor.
     */
    public ListaConPI<String> recuperarSucesoresAlfa(String prefijo, int n) {
        LEGListaConPI<String> sucesores = new LEGListaConPI<String>();

        if(prefijo == null) return sucesores;

        NodoPalabra aux = sucesor(new NodoPalabra(prefijo, 0)); // frecuencia a 0
        while (n > 0 && aux != null && aux.palabra.startsWith(prefijo)) {
            sucesores.insertar(aux.palabra);
            aux = sucesor(new NodoPalabra(aux.palabra, 0)); // frecuencia a 0?
            n--;
        }

        return sucesores;
    }
    
    public ColaPrioridad<NodoPalabra> recuperarSucesoresFrec(String prefijo, int n) {
        MonticuloBinario<NodoPalabra> res = new MonticuloBinario<NodoPalabra>();
        if (prefijo == null) return res;

        int cont = 0;
        NodoPalabra aux = sucesor(new NodoPalabra(prefijo, 0));
        while (aux != null && aux.palabra.startsWith(prefijo)) {
            res.insertar(aux);
            cont++;
            if (cont > n) { res.eliminarMin(); cont--; }
            aux = sucesor(new NodoPalabra(aux.palabra, 0));
        }

        NodoPalabra prefijoNodo = this.recuperar(new NodoPalabra(prefijo, 0));
        if (prefijoNodo != null) {
            res.insertar(prefijoNodo);
            cont++;
            if (cont > n) { res.eliminarMin(); }
        }

        return res;
    }

}