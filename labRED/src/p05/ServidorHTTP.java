package p05;

import java.util.Scanner;
import java.io.*;
import java.net.*;


public class ServidorHTTP {
    private static final String CRLF = "\r\n";

    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        String res = "";
        while (true) {
            Socket s = ss.accept();

            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            String respuesta = sc.nextLine();

            while (respuesta.length() > 0) {
                res += respuesta + CRLF;
                respuesta = sc.nextLine();
            }

                pw.printf("HTTP/1.0 200 OK\r\n");
                pw.printf("Content-Type: text/plan\r\n");
                pw.printf("\r\n");
                pw.printf(res);

                s.close();
            }
    }
}