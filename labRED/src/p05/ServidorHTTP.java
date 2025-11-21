package p05;

import java.util.Scanner;
import java.io.*;
import java.net.*;


public class ServidorHTTP {
    private static final String CRLF = "\r\n";

    public static void main(String args[]) throws IOException {
        PrintWriter envia = null;
        Socket s = null;
        ServerSocket ss = null;
        Scanner recibe = null;
        String res = "";
        try {
            ss = new ServerSocket(8080);
            while (true) {
                s = ss.accept(); // espera un cliente
                System.out.println("Se ha conectado un cliente al servidor");

                envia = new PrintWriter(s.getOutputStream(), true);
                recibe = new Scanner(s.getInputStream());

               String entrada = "a";

                while (entrada.length() > 0) {
                    String respuesta = recibe.nextLine();
                    res += respuesta + CRLF;
                    if (res.length() == 0) break;
                }

                envia.print("HTTP/1.0 200 OK\r\n");
                envia.print("Content-Type: text/plan\r\n");
                envia.print("\r\n");
                envia.print(res);
                s.close();
            }
        } finally {
            envia.close();
            ss.close();
            recibe.close();
        }
    }
}