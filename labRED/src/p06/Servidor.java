package p06;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    public static void main() throws IOException{
        ServerSocket ss = new ServerSocket(7777);

        while (true) {
            Socket s = ss.accept();
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);

            String msg = sc.nextLine();
            pw.print(msg);
            s.close();
        }

    }
}
