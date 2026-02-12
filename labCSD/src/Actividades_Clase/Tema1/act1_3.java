package Actividades_Clase.Tema1;

public class act1_3 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); {
            for (int i = 0; i<10;i++) {
                new Thread("MyThread"+i) {
                    public void run(){
                        System.out.println("executed by"+
                                Thread.currentThread().getName());
                    }
                }.start();
            }
        }
    }
}
