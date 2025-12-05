package p07;

import java.net.*;
import java.util.*;

public class servidorUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(7777);
        DatagramPacket p = new DatagramPacket(new byte[512], 512);
        while (true) {
            ds.receive(p);
            Date fecha = new Date();
            String fecha_string = fecha.toString() + "\r\n";
            p.setData(fecha_string.getBytes());
            p.setLength((fecha_string.getBytes()).length);
            ds.send(p);
        }
    }
}
