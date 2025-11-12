import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TestExamenLabRED {
    public static void main(String[] args) {
        final String HOST = "zoltar.redes.upv.es";
        final int PORT = 12345;
        
        try {
            Socket s = new Socket(HOST, PORT);
            System.out.println("Conectado a " + HOST + " :" + PORT);

            PrintWriter salida = new PrintWriter(s.getOutputStream());

            Scanner entrada = new Scanner(s.getInputStream());

            String respuesta = entrada.nextLine();

            System.out.println(respuesta);

            // ID "ESPACIO" "****"
            salida.print("ID \r\n");
            salida.flush();
            respuesta = entrada.nextLine();

            System.out.println(respuesta);


            while (!respuesta.equals("\r\n")) {
                respuesta = entrada.nextLine();
                System.out.println(respuesta);
                if (respuesta.startsWith("VAL")) {
                    int n1 = Integer.parseInt(entrada.nextLine());
                    int n2 = Integer.parseInt(entrada.nextLine());

                    System.out.println("RES "+ n1 + n2);
                    salida.print("RES " + (n1 + n2) + "\r\n");
                    salida.flush();
                    respuesta = entrada.nextLine();
                }

                //System.out.println(respuesta);

                if (respuesta.startsWith("200")) {
                    System.out.println(respuesta);
                    if (respuesta.startsWith("=")) {
                        respuesta = entrada.nextLine();
                        System.out.println(respuesta);
                        int contador = 1;
                        while ((respuesta = entrada.nextLine()) != null) {
                            // Si la línea está vacía, terminamos
                            if (respuesta.isEmpty()) {
                                break;
                            }
                            // Si la línea contiene "Linea", la contamos
                            if (respuesta.contains("=")) {
                                contador++;
                            }
                        }
                        System.out.println("RES2 "+ contador);
                        salida.print("RES2 " + contador + "\r\n");
                        salida.flush();
                        respuesta = entrada.nextLine();
                    }
                }
            }





            System.out.println("Conexión cerrada.");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}