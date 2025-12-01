package p06;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(7800);

        Socket s = ss.accept();
        System.out.println("Cliente conectado");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String mensaje;

        while ((mensaje = br.readLine()) != null) {
            System.out.println("Cliente: " + mensaje);
        }

        System.out.println("Cliente desconectado");
        s.close();
        ss.close();
    }
}
