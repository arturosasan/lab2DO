package p01;

import java.net.*;
import java.io.*;

public class ClienteTCP2 {
    public static void main (String[] args) throws UnknownHostException, IOException {
        Socket b = new Socket("wwww.upv.es",80);
        System.out.println("¡Conectado!");
        b.close();
    }
}