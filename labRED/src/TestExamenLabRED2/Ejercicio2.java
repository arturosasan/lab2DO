package TestExamenLabRED2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Ejercicio2 extends Thread{

    Socket id;

    private static final String CRLF = "\r\n";

    public Ejercicio2(Socket s) {
        id = s;
    }

    private static final int n_adiv = (int) (Math.random() * 10) + 1;

    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor escuchando en el puerto 6000...");

        System.out.println("NUMERO ==> " + n_adiv); // La chuleta

        while (true) {
            Socket cliente = servidor.accept();
            Ejercicio2 hilo = new Ejercicio2(cliente);
            hilo.start();
        }
    }

    @Override
    public void run() {
        try {
            Scanner entrada = new Scanner(id.getInputStream());
            PrintWriter salida = new PrintWriter(id.getOutputStream(), true);

            // Mensaje de bienvenida
            salida.printf("100 Adivina el numero que he pensado de 1 a 10" + CRLF);

            while (true) {
                String linea = entrada.nextLine().trim();

                if (!linea.startsWith("NUM") && !linea.startsWith("SALIR")) { // && PARA QUE NO SE REPITAN CON LOS OTROS IF'S
                    salida.printf("400 Orden no comprendida" + CRLF);
                    continue; // IMPORTANTE: volver a esperar
                }

                if (linea.startsWith("NUM")) {
                    String[] aux = linea.split("\\s+"); // IMPORTANTE, NO PONER EL ESPACIO, PONER "\\s+"

                    // SI HAY DOS PARÁMETROS

                    if (aux.length != 2) {
                        salida.printf("401 Parámetro incorrecto" + CRLF); // OK
                        continue;
                    }

                    // SI ES ENTERO POSITIVO

                    int n;
                    try {
                        n = Integer.parseInt(aux[1]);
                        if (n <= 0) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        salida.printf("401 Parámetro incorrecto" + CRLF);
                        continue;
                    }

                    if (Integer.parseInt(aux[1]) == n_adiv) {
                        salida.printf("200 Acertaste. Fin de juego" + CRLF);
                        break;
                    }
                    else if (n < n_adiv) {
                        salida.printf("201 Numero demasiado bajo" + CRLF);
                    } else {
                        salida.printf("202 Numero demasiado alto" + CRLF);
                    }
                }

                if (linea.startsWith("SALIR")) {
                    salida.printf("300 Te has rendido, adios" + CRLF);
                    break;
                }
            }
            id.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}