package TestExamenLabRED2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Ejercicio3 extends Thread {
    Socket id;

    private static final String CRLF = "\r\n";

    private static final String[] OP = {"SUM", "DIF", "MUL", "DIV"};

    public Ejercicio3(Socket s) {
        id = s;
    }

    public static void main (String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(13000);
        System.out.println("Servidor escuchando en el puerto 13000...");

        while (true) {
            Socket cliente = servidor.accept();
            Ejercicio3 hilo = new Ejercicio3(cliente);
            hilo.start();
        }
    }

    @Override
    public void run() {
        try {
            Scanner entrada = new Scanner(id.getInputStream());
            PrintWriter salida = new PrintWriter(id.getOutputStream(), true);

            // Mensaje de bienvenida
            salida.printf("100-Bienvenido a la calculadora" + CRLF);

            while (true) {
                String linea = entrada.nextLine().trim();

                String[] params = linea.split("\\s+"); // IMPORTANTE, NO PONER EL ESPACIO, PONER "\\s+"

                // QUE HAY 3 PARAMETROS
                if (params.length != 3) {
                    salida.printf("402 Error en operandos" + CRLF);
                    continue;
                }

                // LA COMPROBACIÓN DE LA OPERACIÓN LO HAGO EN EL SWITCH CON EL CASO "DEFAULT" :)

                // COMPROBACIÓN DE ENTERO POSITIVO EN AMBOS PARÁMETROS

                int n1;
                try {
                    n1 = Integer.parseInt(params[1]);
                } catch (NumberFormatException e) {
                    salida.printf("402 Error en operandos" + CRLF);
                    continue;
                }

                int n2;
                try {
                    n2 = Integer.parseInt(params[2]);
                } catch (NumberFormatException e) {
                    salida.printf("402 Error en operandos" + CRLF);
                    continue;
                }

                int aux1 = Integer.parseInt(params[1]);
                int aux2 = Integer.parseInt(params[2]);

                int res = 0;

                String operacion = params[0];

                switch (operacion) {
                    case "SUM":
                        res = aux1 + aux2;
                        salida.printf("200 OK " + res + CRLF);
                        id.close();
                        break;
                    case "DIF":
                        res = aux1 - aux2;
                        salida.printf("200 OK " + res + CRLF);
                        id.close();
                        break;
                    case "MUL":
                        res = aux1 * aux2;
                        salida.printf("200 OK " + res + CRLF);
                        id.close();
                        break;
                    case "DIV":
                        if (aux2 == 0) {
                            salida.printf("403 División por cero" + CRLF);
                        } else {
                            res = aux1 / aux2;
                            salida.printf("200 OK " + res + CRLF);
                            id.close();
                            break;
                        }
                    default:
                        if (!params[0].equals(OP[3])) { // SI NO PONGO ESTO ME SALEN LOS
                        salida.printf("401 Operación incorrecta" + CRLF);
                        }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
