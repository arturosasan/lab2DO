import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClienteEco {
    public static void main (String[] args) throws UnknownHostException, IOException {
        try {
            Socket e = new Socket("zoltar.redes.upv.es",7); // VPN SI NO ESTÁS EN EL CAMPUS
            System.out.println("Conectado");
            PrintWriter salida = new PrintWriter(e.getOutputStream());
            salida.printf("Hola Mundo!\r\n");
            salida.flush(); // EJERCICIO 6
            Scanner entrada = new Scanner(e.getInputStream());
            System.out.println(entrada.nextLine());
            e.close();
            System.out.println("Desconectado");
        }
        catch (UnknownHostException s) {
            System.out.println("Servidor no encontrado");
        }
        catch (IOException p) {
            System.out.println("Puerto no encontrado");
        }
    }
}

