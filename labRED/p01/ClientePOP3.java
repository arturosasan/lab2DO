import java.net.*;
import java.io.*;
import java.util.*;

public class ClientePOP3 {
    public static void main (String[] args) throws UnknownHostException, IOException{
        try{
            Socket f = new Socket("pop3.upv.es",110);
            System.out.println("Conectado");
            PrintWriter salida = new PrintWriter(f.getOutputStream());
            Scanner entrada = new Scanner(f.getInputStream());
            salida.printf("QUIT"+"\r\n");
            salida.flush();
            System.out.println(entrada.nextLine());
            f.close();
        }
        catch (UnknownHostException s) {
            System.out.println("Servidor no encontrado");
        }
        catch (IOException p) {
            System.out.println("Puerto no encontrado");
        }
    }
}
