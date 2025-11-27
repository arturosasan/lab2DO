package practica3;


/**
 * Write a description of class Circle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Circle extends Figure implements Printable {
    private double radius;

    public Circle(double x, double y, double r) {
        super(x, y);
        radius = r;
    }

    public String toString() {
        return "Circle:\n\t" +
            super.toString() +
            "\n\tRadius: " + radius +
            "\n\tÁrea: " + area() +
            "\n\tPerímetro: " + perimeter();

    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Circle)) {
            return false;
        }
        else {
            Circle other = (Circle) o;
            return super.equals(o) && this.radius == other.radius;
        }

    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }
    
    public void print(char c) {
        int n = (int)radius;
        for (int j = 0; j <= n * 2; j++) {
            for (int i = 0; i <= n * 2; i++) {
                if (Math.pow(j - n, 2.0) + Math.pow(j - n, 2.0)
                <= (int)Math.pow(n , 2)) {
                    System.out.print(c);
                } else {
                    System.out.println(" ");
                }
            }
            System.out.println();
        }
    }
}