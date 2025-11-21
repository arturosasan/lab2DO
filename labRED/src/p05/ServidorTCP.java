package p05;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class ServidorTCP {
    private static final String CRLF = "\r\n";

    public static void main(String args[]) throws IOException {
        PrintWriter envia = null;
        Socket s = null;
        ServerSocket ss = null;
        Scanner recibe = null;
        try {
            ss = new ServerSocket(7777);
            while (true) {
                s = ss.accept(); // espera un cliente
                System.out.println("Se ha conectado un cliente al servidor");

                envia = new PrintWriter(s.getOutputStream(), true);
                recibe = new Scanner(s.getInputStream());
                String respuesta = recibe.nextLine();
                envia.printf(respuesta + CRLF);

                System.out.println("Client IP: " + s.getLocalAddress().getHostAddress()); // EJERCICIO 2
                System.out.println("Client port : " + s.getLocalPort());                  // EJERCICIO 2
                System.out.println("Server IP : " + s.getInetAddress().getHostAddress()); // EJERCICIO 2
                System.out.println("Server port : " + s.getPort());                       // EJERCICIO 2

                s.close();
            }
        } finally {
            envia.close();
            ss.close();
            recibe.close();
        }
    }
}

