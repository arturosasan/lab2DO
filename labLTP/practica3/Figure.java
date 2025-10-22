package practica3;


/**
 * Write a description of class Figure here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Figure implements Comparable <Figure>{
    private double x;
    private double y;
    
    public Figure(double x, double y) {
        this.x = x; 
        this.y = y; 
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Figure)) { return false; }
        Figure f = (Figure) o;
        return x == f.x && y == f.y;
    }
    
    public String toString() {
        return "Position: (" + x + ", " + y + ")"; 
    }

    public abstract double area();

    public abstract double perimeter();
    
    public int compareTo(Figure f) {
        if (this.area() == f.area()) {return 0;}
        else if (this.area() > f.area()) {return 1;}
        else {return -1;}
    }
}