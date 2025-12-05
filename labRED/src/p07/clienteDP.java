package p07;

import java.net.*;
import java.io.*;

public class clienteDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress dir = InetAddress.getByName("localhost");
        String msg = "Arturo Sarrión Sánchez\r\n";
        byte[] buf = new byte[512];
        buf = msg.getBytes();
        DatagramPacket p = new DatagramPacket(buf, buf.length, dir, 7777);
        ds.send(p);
        ds.close();
    }
}
