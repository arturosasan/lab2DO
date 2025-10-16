import java.net.*;
import java.io.*;
import java.util.*;

public class ClienteDayTime {
    public static void main (String[] args) throws UnknownHostException, IOException {
        try {
            Socket d = new Socket("zoltar.redes.upv.es",13); // VPN SI NO ESTÁS EN EL CAMPUS
            Scanner kb = new Scanner(d.getInputStream());
            while (kb.hasNext()) {
                System.out.println(kb.nextLine());
            }
            System.out.println("Bye!");
            d.close();
            kb.close();

        }
        catch (UnknownHostException s) {
            System.out.println("Servidor no encontrado");
        }
        catch (IOException p) {
            System.out.println("Puerto no encontrado");
        }
    }
}
