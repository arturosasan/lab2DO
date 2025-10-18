package practica1;

/**
 * class FiguresGroup.
 *
 * @author LTP
 * @version 2020-21
 */

public class FiguresGroup {
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
}