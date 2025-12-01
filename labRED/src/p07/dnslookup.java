package p07;

import java.io.IOException;
import java.net.*;

// EJERCICIO 1
public class dnslookup {
    public static void main (String[] args) {
        try {
            InetAddress ipServer = InetAddress.getByName("www.eltiempo.es");
            System.out.printf("Host\t\t/ IP \n" + ipServer.toString());
            // POR TERMINAL
            // InetAddress ipServer = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.err.println(e);
        }

    }
}
