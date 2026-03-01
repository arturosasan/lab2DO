package aplicaciones.censo;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/**
 * ListaElectores: representa una lista de habitantes, 
 *                 registrados en el censo, y por ello, electores
 * 
 * @author  Profesores EDA 
 * @version Septiembre 2023
 */

public class ListaElectores {
   
    private ListaConPI<Habitante> censo;
    private int talla;
    
    /**
     * Métodos consultores de atributos
     */
    public ListaConPI<Habitante> getCenso() { return censo; }
    public int getTalla() { return talla; }
    
    /**
     * Devuelve el String que representa una ListaElectores 
     * 
     * @return el String con la ListaElectores en el formato texto dado. 
     */
    public String toString() {
        String res = "";
        if (talla == 0) return res;
        censo.inicio();
        for (int pos = 0; pos <= censo.talla() - 2; pos++) {
            res += censo.recuperar() + ", \n";
            censo.siguiente();
        }
        res += censo.recuperar();
        return res;
    }
   
    /**
     * Crea una ListaElectores...
     * 
     * @param orden Un boolean que indica si el censo,  
     *              debe estar ordenada ascendentemente (true) o no (false). 
     *              
     * @param n     Un int que indica la talla, número de elementos, de la lista              
     */
    public ListaElectores(boolean orden, int n) {
        talla = n;
        if (orden) {
            censo = new LEGListaConPIOrdenada<Habitante>();
        } else {
            censo = new LEGListaConPI<Habitante>();
        }
        for (int i = 0; i < n; i++) {
            Habitante aux = new Habitante();
            if (indice(aux) != -1) {
                i--;
            } else {
                censo.insertar(aux);
            }
        }
    }
    
    /**
     * Devuelve el índice o posicion del Habitante h en una ListaElectores, 
     * o -1 si h no forma parte de la lista. 
     * 
     * @param h un Habitante
     * @return  el índice de h en un censo, un valor int
     *          0 o positivo si h esta en el censo      
     *          o -1 en caso contrario
     */
    protected int indice(Habitante h) {
        int posi = 0;
        censo.inicio();
        while (!censo.esFin()) {
            if (censo.recuperar().equals(h)) {
                return posi;
            } else {
                posi++;
                censo.siguiente();
            }
        }
        return -1;
    }

    // ACTIVIDADES EXTRA

    /**
     * Devolver una ListaElectores que sea el subconjunto de this ListaElectores
     * que contenga los habitantes cuyo código postal esté en el rango [cp1 .. cp2].
     * Si, al invocarlo, cp1 = cp2,
     * entonces se obtiene la ListaElectores de un único código postal.
     *
     * @param cp1 el primer código postal del rango (principio)
     *
     * @param cp2 el segundo código postal del rango (fin)
     *
     * @return  una lista de electores con habitantes de código postal entre los rangos indicados
     */
    public ListaElectores getCensoCP(int cp1, int cp2) {

        ListaElectores resultado = new ListaElectores(false, 0);

        censo.inicio();

        while (!censo.esFin()) {
            Habitante h = censo.recuperar();

            if (h.getCp() >= cp1 && h.getCp() <= cp2) {
                resultado.censo.insertar(h);
            }

            censo.siguiente();
        }
        return resultado;
    }

    /**
     * Devolver una ListaElectores que sea el subconjunto de this ListaElectores
     * que contenga los habitantes cuyo primer o segundo apellido comiencen por el String prefijo
     *
     * @param prefijo El prefijo del nombre a buscar
     *
     * @return  una lista de electores con habitantes de apellidos coincidentes con el prefijo dado
     */
    public ListaElectores buscador(String prefijo) {

        ListaElectores resultado = new ListaElectores(false, 0);

        censo.inicio();

        while (!censo.esFin()) {

            Habitante h = censo.recuperar();

            if (h.getApellido1().startsWith(prefijo) || h.getApellido2().startsWith(prefijo)) {

                resultado.censo.insertar(h);
            }

            censo.siguiente();
        }

        return resultado;
    }
}
