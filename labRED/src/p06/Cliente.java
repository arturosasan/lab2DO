package p06;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente extends Thread{
    Socket id;

    public Cliente(Socket s) {
        id = s;
    }

    public static void main (String[] args) throws IOException {
        Socket s = new Socket("localhost",7800);
        System.out.println("CONECTADO");

        Scanner sc = new Scanner(s.getInputStream());
        Cliente c = new Cliente(s);
        c.start();

        while(sc.hasNext()){
            System.out.println(sc.nextLine());
        }

        System.out.println("CONEXIÓN CERRADA");
        s.close();
    }

    public void run() {
        try {
            String msg = "";
            PrintWriter pw = new PrintWriter(id.getOutputStream(),true);
            Scanner sc = new Scanner(System.in);

            while (!msg.equals("FIN")) {
                msg = sc.nextLine();
                pw.printf(msg+"\r\n");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
