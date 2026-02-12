package Actividades_Clase.Tema1;

public class act1_4 extends Thread {
    public void run() {
        for (int i = 0; i < 3; i++) printMsg();
    }
    public void printMsg() {
        System.out.println ("name=" + Thread.currentThread().getName());
    }
    public static void main(String[] args) {

        for ( int i = 0; i < 10; i++ ) {
            act1_4 tt= new act1_4();
            tt.setName("MyThread" + i);
            if (i<5) tt.start();
        }
    }
}
