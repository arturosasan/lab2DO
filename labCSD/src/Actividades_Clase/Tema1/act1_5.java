package Actividades_Clase.Tema1;

public class act1_5 extends Thread{
    private String result = "Not calculated";
    public void run(){
        result = calculate();}
    private String calculate(){
        // Performs a long-time calculation
        try {Thread.sleep(10000);
        } catch(InterruptedException e){};
        System.out.println("Agent thread finishes its calculation");
        return "Calculation done";
    }
    public String getResults(){
        return result; }
}
class Example_1 {
    public static void main(String[] args){
        act1_5 agent =new act1_5();
        agent.start();
        // It does something during the calculation process
        System.out.println("Main in execution");

        // APARTADO 2
        while(agent.isAlive()) Thread.yield(); // ESPERA ACTIVA

        // APARTADO 3
        try {
            agent.join(); // ESPERA PASIVA
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        // Employs the result
        System.out.println(agent.getResults());
    }
}