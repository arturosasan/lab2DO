package practica1;

/**
 * class Triangle.
 * 
 * @author LTP
 * @version 2020-21
 */

public class Triangle extends Figure {
    private double base; 
    private double height;

    public Triangle(double x, double y, double b, double h) {
        super(x, y);
        base = b;
        height = h;
    }

    public String toString() {
        return "Triangle:\n\t" +
            super.toString() +
            "\n\tBase: " + base +
            "\n\tHeight: " + height +
            "\n\tÁrea: " + area() +
            "\n\tPerímetro: " + perimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Triangle)) {
            return false;
        }
        else {
            Triangle other = (Triangle) o;
            return super.equals(o) && this.base == other.base && this.height == other.base;
        }
    }

    public double area() {
        return (base * height)/2;
    }

    public double perimeter() {
        return -1;
        // Devuelvo -1 porque si no habría que cambiar toda
        // la estructura de las clases de herencia
    }
}