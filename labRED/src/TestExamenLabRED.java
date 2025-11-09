import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestExamenLabRED {
    public static void main(String[] args) {
        final String HOST = "zoltar.redes.upv.es";
        final int PORT = 12345;
        
        try {
            Socket socket = new Socket(HOST, PORT);
            socket.setSoTimeout(1000); // Timeout de 1 segundo
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner teclado = new Scanner(System.in);
            
            System.out.println("Conectado a " + HOST + ":" + PORT);
            
            // Pedir DNI y enviar ID
            System.out.print("Introduce tu DNI/NIE: ");
            String dni = teclado.nextLine();
            out.println("ID " + dni);
            
            // Leer todas las respuestas iniciales
            try {
                while (true) {
                    System.out.println("[SERVER] " + in.readLine());
                }
            } catch (Exception e) {} // Timeout alcanzado, ya no hay más datos
            
            // Bucle: lee del teclado y envía
            while (teclado.hasNextLine()) {
                String linea = teclado.nextLine();
                
                if (linea.equalsIgnoreCase("/quit")) {
                    break;
                }
                
                out.println(linea);
                
                // Leer todas las respuestas del servidor
                try {
                    while (true) {
                        System.out.println("[SERVER] " + in.readLine());
                    }
                } catch (Exception e) {} // Timeout alcanzado, ya no hay más datos
            }
            
            socket.close();
            System.out.println("Conexión cerrada.");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}