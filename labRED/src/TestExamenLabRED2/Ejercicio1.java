package TestExamenLabRED2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Ejercicio1 extends Thread {

    Socket id;

    private static final String CRLF = "\r\n";

    public Ejercicio1(Socket s) {
        id = s;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(15500);
        System.out.println("Servidor escuchando en el puerto 15500...");

        while (true) {
            Socket cliente = servidor.accept();
            Ejercicio1 hilo = new Ejercicio1(cliente);
            hilo.start();
        }
    }

    @Override
    public void run() {
        try {
            Scanner entrada = new Scanner(id.getInputStream());
            PrintWriter salida = new PrintWriter(id.getOutputStream(), true);

            // Mensaje de bienvenida
            salida.printf("100 Bienvenido al servicio repetidor." + CRLF);

            while (true) {
                String linea = entrada.nextLine().trim();

                // Validación general
                if (!linea.startsWith("REPITE")) {
                    salida.printf("400 Orden no comprendida." + CRLF);
                    continue; // vuelve a esperar
                }

                String[] aux = linea.split("\\s+"); // IMPORTANTE, NO PONER EL ESPACIO, PONER "\\s+"

                if (aux.length != 2) {
                    salida.printf("400 Orden no comprendida." + CRLF);
                    continue;
                }

                // Validar que es entero positivo
                int n;
                try {
                    n = Integer.parseInt(aux[1]);
                    if (n <= 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    salida.printf("400 Orden no comprendida." + CRLF);
                    continue;
                }

                salida.printf("200 Orden correcta. Aqui van tus " + n + " lineas." + CRLF);

                for (int i = 0; i < n; i++) {
                    salida.printf("Servidor de Redes 2025" + CRLF);
                }
                salida.printf("." + CRLF);
                salida.printf("900 ADIOS!! Gracias por usar el servidor." + CRLF);

                break;
            }

            id.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
