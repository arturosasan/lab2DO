package TestExamenLabRED2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Ejercicio4 extends Thread{
    Socket id;

    private static final String CRLF = "\r\n";

    public Ejercicio4(Socket s) {
        id = s;
    }

    public static void main (String[] args) throws Exception{
        ServerSocket servidor = new ServerSocket(15000);
        System.out.println("Servidor escuchando en el puerto 15000...");

        while (true) {
            Socket cliente = servidor.accept();
            Ejercicio4 hilo = new Ejercicio4(cliente);
            hilo.start();
        }
    }

    @Override
    public void run () {
        try {
            Scanner entrada = new Scanner(id.getInputStream());
            PrintWriter salida = new PrintWriter(id.getOutputStream(), true);

            // Mensaje de bienvenida
            salida.printf("100 Bienvenido al servicio despertador. " + CRLF);

            while (true) {
                String linea = entrada.nextLine().trim();

                String[] params = linea.split("\\s+"); // IMPORTANTE, NO PONER EL ESPACIO, PONER "\\s+"

                if (!linea.startsWith("ESPERA")) {
                    salida.printf("400 Orden no comprendida." + CRLF);
                    continue;
                }

                if (params.length != 2) {
                    salida.printf("401 Numero de segundos incorrecto." + CRLF);
                    continue;
                }

                int n;
                try {
                    n = Integer.parseInt(params[1]);
                    if (n > 100 || n < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    salida.printf("401 Numero de segundos incorrecto." + CRLF);
                    continue;
                }

                if (linea.startsWith("ESPERA")) {
                    int seg = Integer.parseInt(params[1]);
                    salida.printf("200 Orden correcta. Te despierto en " + seg + " segundos..." + CRLF);
                    Thread.sleep(seg*1000);
                    salida.printf("900 DESPIERTA!! Gracias por usar el servidor. " + CRLF);
                    id.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
