package p07;

import java.net.*;
import java.io.*;

public class clienteUDP {
    public static void main (String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress dir = InetAddress.getByName("127.0.0.1");
        String msg = "Arturo Sarrión Sánchez\r\n";
        byte[] buf = new byte[256];
        buf = msg.getBytes();
        DatagramPacket p = new DatagramPacket(buf, buf.length,dir,7777);
        ds.send(p);
        ds.receive(p);
        String respuesta = new String(p.getData(), 0, p.getLength());
        ds.send(p);
        System.out.println(respuesta);
        ds.close();
    }
}
