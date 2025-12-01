package p07;

import java.net.*;
import java.io.*;

public class clienteUDP {
    public static void main (String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress dir = InetAddress.getByName("localhost");
        String msg = "Tampoco funciona\r\n";
        byte[] buf = new byte[256];
        buf = msg.getBytes();
        DatagramPacket p = new DatagramPacket(buf, buf.length,dir,7777);
        ds.send(p);
        ds.receive(p);
        String respuesta = new String(p.getData(), 0, p.getLength());
        System.out.println(respuesta);
        ds.close();
    }
}
