import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TestExamenLabRED {
    private static final String HOST = "zoltar.redes.upv.es";
    private static final int PORT = 12345;
    private static final String CRLF = "\r\n";

    public static void main(String[] args) {
        // LO DECLARO TODO VACÍO PARA CERRARLOS EN EL FINALLY
        Socket s = null;
        PrintWriter salida = null;
        Scanner entrada = null;
        Scanner teclado = new Scanner(System.in);

        try {
            s = new Socket(HOST, PORT);
            System.out.println("Conectando a " + HOST + ":" + PORT);

            // Crear flujos de entrada y salida
            salida = new PrintWriter(s.getOutputStream());
            entrada = new Scanner(s.getInputStream());

            // COMPRUEBO QUE TODO OK
            String line = entrada.nextLine();
            System.out.println(line);

            // PIDO DNI 
            System.out.print("Introduce tu DNI/NIE (sin letra y sin ID): ");
            String dni = teclado.nextLine();

            // ENVÍO ID + DNI
            enviarMensaje(salida, "ID " + dni);

            // LEO 
            line = entrada.nextLine();
            System.out.println("[SERVIDOR] " + line);

            // BUCLE PARA SALTAR A CADA EJERCICIO
            while (entrada.hasNextLine()) {
                line = entrada.nextLine();
                System.out.println("[SERVIDOR] " + line);

                // EJERCICIO 1
                if (line.contains("Te envio dos numeros")) {
                    EJ1(entrada, salida);
                }

                // EJERCICIO 2
                else if (line.contains("Te envio <n> lineas de texto")) {
                    EJ2(entrada, salida);
                }

                // EJERCICIO 3
                else if (line.contains("MAYOR', 'IGUAL' o 'MENOR'")) {
                    EJ3(entrada, salida);
                }
            }

            System.out.println("\nExamen completado.");

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            teclado.close();
            salida.close();
            entrada.close();

        }
    }

    private static void EJ1(Scanner entrada, PrintWriter salida) {
        // SI NO LO HAGO ASÍ NO ME ESCRIBE BIEN EL ENUNCIADO (??)
        String valaux = entrada.nextLine();
        System.out.println("[SERVIDOR] " + valaux);


        String num1S = entrada.nextLine();
        System.out.println("[SERVIDOR] " + num1S);
        int num1 = Integer.parseInt(num1S);

        String num2S = entrada.nextLine();
        System.out.println("[SERVIDOR] " + num2S);
        int num2 = Integer.parseInt(num2S);


        int suma = num1 + num2;

        enviarMensaje(salida, "RES " + suma);
    }

    private static void EJ2(Scanner entrada, PrintWriter salida) {
        int contador = 0;
        String linea;

        while (entrada.hasNextLine()) {
            linea = entrada.nextLine();
            System.out.println("[SERVIDOR] " + linea);

            if (linea.isEmpty()) {
                break;
            }

            contador++;
        }

        enviarMensaje(salida, "RES2 " + contador);
    }

    private static void EJ3(Scanner entrada, PrintWriter salida) {
        String comando;

        while (entrada.hasNextLine()) {
            comando = entrada.nextLine();
            System.out.println(comando);

            if (comando.equals("SALIR")) {
                break;
            }

            int numero = 5; // Valor por defecto

            if (comando.equals("MAYOR")) {
                numero = 6; // Cualquier número mayor que 5
                enviarMensaje(salida, "RES3 " + numero);
            } else if (comando.equals("MENOR")) {
                numero = 4; // Cualquier número menor que 5
                enviarMensaje(salida, "RES3 " + numero);
            } else if (comando.equals("IGUAL")) {
                numero = 5; // Exactamente 5
                enviarMensaje(salida, "RES3 " + numero);
            }



            if (entrada.hasNextLine()) {
                String respuesta = entrada.nextLine();
                System.out.println(respuesta);
            }


        }
        enviarMensaje(salida, "SALIR");
    }

    // FUNCIÓN AUX PARA VER LO QUE SE ENVÍA AL SERVIDOR Y PARA NO PONER "\r\n" TODO EL P*** RATO
    private static void enviarMensaje(PrintWriter salida, String mensaje) {
        System.out.println("Enviando: " + mensaje);
        salida.print(mensaje + CRLF);
        salida.flush();
    }
}
