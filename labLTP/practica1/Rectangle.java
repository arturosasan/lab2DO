package practica1;

public class Rectangle extends Figure{
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
}
