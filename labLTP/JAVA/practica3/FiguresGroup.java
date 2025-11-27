package practica3;

import java.util.*;


/**
 * Write a description of class FiguresGroup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FiguresGroup implements Printable {
    private static final int NUM_FIGURES = 10;
    private Figure[] figuresList = new Figure[NUM_FIGURES];
    private int numF = 0;

    public void add(Figure f) { figuresList[numF++] = f; }

    public String toString() {
        String s = "";
        for (int i = 0; i < numF; i++) {
            s += "\n" + figuresList[i];
        }
        return s;
    }

    private boolean found(Figure f) {
        for (int i = 0; i < numF; i++) {
        	if (figuresList[i].equals(f)) return true;
        }
        return false;
    }

    private boolean included(FiguresGroup g) {
		for (int i = 0; i < g.numF; i++) {
        	if (!found(g.figuresList[i])) return false;
        }
		return true;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof FiguresGroup)) {
            return false;
        }
        else {
            FiguresGroup other = (FiguresGroup) o;
            return this.included(other) && other.included(this);
        }
    }

    public double area() {
        double res = 0;
        for(int i=0; i<numF; i++) {
            res += figuresList[i].area();
        }
        return res;
    }

    public double greatestFigure() {
        Figure nextfigure = null;
        double result = 0;
        for (int i = 0; i<= numF; i++) {
            if (figuresList[i].area() > result) {
                result = figuresList[i].area();
                nextfigure = figuresList[i];
            }
        }
        return result;
    }
    
    public List<Figure> orderedList() {
        List<Figure> L = new ArrayList<Figure>();
        if (numF > 0) {
            L.add(figuresList[0]);
            for (int i = 0; i < numF; i++) {
                int aux = L.size() - 1;
                while (aux >= 0 && this.figuresList[i].compareTo(L.get(aux)) < 0) {
                    aux--;
                }
                L.add(aux + 1, figuresList[i]);
            }
        }
        return L;
    }
    
    public void print(char c) {
        for (int i = 0; i < numF; i++) {
            if (figuresList[i] instanceof Printable) {
                ((Printable)figuresList[i]).print(c);
            }
        }
    }
}