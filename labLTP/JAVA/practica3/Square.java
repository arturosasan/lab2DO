package practica3;

/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square extends Rectangle {
    private double side;
    public Square(double x, double y, double s) {
        super(x, y, s, s);
        side = s;
    }

    public double area() {
        return side * side;
    }

    public double perimeter(){
        return 4 * side;
    }
}