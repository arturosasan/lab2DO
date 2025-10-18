package practica1;
/**
 * class FiguresGroupUse.
 * 
 * @author LTP 
 * @version 2020-21
 */

import java.util.Scanner;
import java.util.Locale;

public class FiguresGroupUse {
    /*public static void main(String[] args) {
        FiguresGroup g = new FiguresGroup();
        g.add(new Circle(10, 5, 3.5));
        g.add(new Triangle(10, 5, 6.5, 32));
        System.out.println(g);
    }*/

    public static void main (String[] args) {
        FiguresGroup g = new FiguresGroup();
        Scanner kbd = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("¿Qué figura quieres crear?");
        System.out.println("[c] --> Círculo | [r] --> Rectángulo | [s] --> Square | [t] --> Triángulo");
        String respuesta = kbd.nextLine();
        switch (respuesta) {
            case "c":
                System.out.println("Dame el radio del círculo");
                double radio_C = kbd.nextDouble();
                System.out.println("Dame la posición X del círculo");
                double X_C = kbd.nextDouble();
                System.out.println("Dame la posición Y del círculo");
                double Y_C = kbd.nextDouble();

                g.add(new Circle(X_C, Y_C, radio_C));
                break;
            case "r":
                System.out.println("Dame la base del rectángulo");
                double base_R = kbd.nextDouble();
                System.out.println("Dame la altura del rectángulo");
                double height_R = kbd.nextDouble();
                System.out.println("Dame la posición X del rectángulo");
                double X_R = kbd.nextDouble();
                System.out.println("Dame la posición Y del réctangulo");
                double Y_R = kbd.nextDouble();

                g.add(new Rectangle(X_R, Y_R,base_R,height_R));
                break;
            case "s":
                System.out.println("Dame el lado del cuadrado");
                double side_S = kbd.nextDouble();
                System.out.println("Dame la posición X del cuadrado");
                double X_S = kbd.nextDouble();
                System.out.println("Dame la posición Y del cuadrado");
                double Y_S = kbd.nextDouble();

                g.add(new Square(X_S, Y_S, side_S));
                break;
            case "t":
                System.out.println("Dame la base del triángulo");
                double base_T = kbd.nextDouble();
                System.out.println("Dame la altura del triángulo");
                double height_T = kbd.nextDouble();
                System.out.println("Dame la posición X del triángulo");
                double X_T = kbd.nextDouble();
                System.out.println("Dame la posición Y del triángulo");
                double Y_T = kbd.nextDouble();

                g.add(new Triangle(X_T, Y_T,base_T,height_T));
                break;
            default:
                System.out.println("ERROR: FIGURA NO RECONOCIDA");
                break;
        }
        System.out.println(g);
    }
}