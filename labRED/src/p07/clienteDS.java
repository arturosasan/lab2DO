package p07;

import java.net.*;

public class clienteDS {
    public static void main (String[] args) throws SocketException{
        DatagramSocket ds = new DatagramSocket();
        System.out.println("Puerto local : " + ds.getLocalPort());
    }
}
