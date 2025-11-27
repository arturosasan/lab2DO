package practica3;

/**
 * Write a description of class Rectangle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rectangle extends Figure implements ComparableRange <Figure>, Printable {
    public double base;
    public double height;

    public Rectangle(double x, double y, double b, double h){
        super(x, y);
        base = b;
        height = h;
    }

    public String toString() {
        return "Rectangle:\n\t" +
        super.toString() +
        "\n\tBase: " + base +
        "\n\tHeight: " + height +
        "\n\tÁrea: " + area() +
        "\n\tPerímetro: " + perimeter();
    }

    public boolean equals(Object o){
        if(!(o instanceof Rectangle)){return false;}
        else{
            Rectangle other = (Rectangle) o;
            return this.base == other.base
            && this.height == other.height
            && super.equals((Rectangle) o);
        }
    }

    public double area(){
        return base*height;
    }

    public double perimeter(){
        return 2*(base+height);
    }

    public int compareToRange (Figure r) {
        if (Math.abs(this.area() - r.area()) <= 0.1 * (this.area() + r.area())) {
            return 1;
        } else if (this.area() == r.area()) {
            return 0;
        } else {
            return -1;
        }
    }
    
    public void print (char c) {
        int h = (int)height;
        int b = (int)base;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < b; j++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}