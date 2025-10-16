import java.net.*;
import java.io.*;

public class ClienteTCP3 {

    /*public static void main (String[] args) throws UnknownHostException, IOException { // EJERCICIO 3
        try {
            Socket c = new Socket("www.upv.es",80);
            System.out.println("¡Conectado!");
            c.close();
        }
        catch (UnknownHostException s) {
            System.out.println("Servidor no encontrado");
        }
        catch (IOException p) {
            System.out.println("Puerto no encontrado");
        }
    }*/
    public static void main(String args[]) { // EJERCICIO 9
        try {
            Socket s = new Socket("www.upv.es", 80);
            System.out.println("Conectado");
            System.out.println("Dirección remota: " + s.getInetAddress());
            System.out.println("Puerto remoto: " + s.getPort());
            System.out.println("Dirección local: " + s.getLocalAddress());
            System.out.println("Puerto local: " + s.getLocalPort());
            s.close();
        } catch(UnknownHostException e){
            System.out.println("Nombre del servidor desconocido");
        } catch(IOException e){
            System.out.println("NO conectado");
        }
    }
}
