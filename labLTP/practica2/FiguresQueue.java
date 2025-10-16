package practica2;

import practica1.Figure;
import practica2.librerias.implementaciones.QueueAL;

/**
 * class FiguresQueue.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class FiguresQueue<T extends Figure> extends QueueAL<T> {
    public double area() {
        double res = 0; 
        for (int i = 0; i < this.size(); i++){
            T items = this.dequeue ();
            res = res + items.area();
            this.enqueue(items);  
        }
        return res;
    }
}