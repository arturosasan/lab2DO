package librerias.estructurasDeDatos.lineales;

public class LEGListaConPIOrdenada <E extends Comparable<E>> extends LEGListaConPI<E> {

    public void insertar (E e){
        inicio();
        while (!esFin() && recuperar().compareTo(e) < 0) {
            siguiente();
        }
        super.insertar(e);
    }
}
