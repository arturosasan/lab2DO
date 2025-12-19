package p08;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSession;

class ClienteHTTPS_v00 {
    static String nombre_servidor;
    static Socket socket; // Cambiar por SSLSocket    
    static ScannerRedes entrada;
    static PrintWriter salida;
    static String CRLF = "\r\n";
    static String imagen1 = "/imagenes/poli-consulta-blanco.png";    
    static Scanner Teclado;
    static int BPL = 32; // Bytes por linea para impresión
    static int longitud_cuerpo=0;
    static boolean Chunked =false ;

    public static void conexion_SSL(String servidor, int puerto) {
        try {
            // Obtener la fábrica de sockets SSL

            // Crear el socket SSL conectado al host/puerto
            socket = new Socket (servidor, puerto); // modificar por SSLSocket

            // Iniciar handshake SSL/TLS           

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void envia_peticion(String objeto) {
        String solicitud = "GET " + objeto + " HTTP/1.1\r\n" + 
            "Host: " + nombre_servidor + "\r\n" +
            "Connection: close\r\n\r\n";
        salida.printf( solicitud);
        salida.flush();

    }

    public static void lee_linea_estado() {
        System.out.println(">>>>>>>>>>>>>>> LINEA DE ESTADO <<<<<<<<<<<<<<<");
        System.out.println(entrada.nextLine());

    }

    public static void lee_cabeceras() {
        System.out.println(">>>>>>>>>>>>>>>    CABECERAS    <<<<<<<<<<<<<<<");    
        String linea = "";
        while (true)
        {
            linea = entrada.nextLine();
            if (linea.isEmpty())
                break;
            System.out.println(linea);        

        }

    }

    public static void lee_cuerpo_texto() {
        System.out.println(">>>>>>>>>>>>>>>   CUERPO TEXTO  <<<<<<<<<<<<<<<");
        String linea = "";

        while (entrada.hasNext())
        {
            linea = entrada.nextLine();
            System.out.println(linea);        

        }
    }

    public static void lee_cuerpo_binario(String nombre_fichero) {
        System.out.println(">>>>>>>>>>>>>>>  CUERPO BINARIO <<<<<<<<<<<<<<<");
        int b=0;
        try
        {
            FileOutputStream fichero = new FileOutputStream(nombre_fichero);
            while(b!=-1)
            {
                b = socket.getInputStream().read();
                if (b!=-1)
                    fichero.write(b);
                //System.out.print(b);

            }
            //System.out.println();
            fichero.close();
        }

        catch (IOException e) {System.out.println("Excepción " + e);}

    }

    public static void lee_cuerpo_binario_pantalla() {
        System.out.println(">>>>>>>>>>>>>>>  CUERPO BINARIO <<<<<<<<<<<<<<<");
        int b=0;
        int cont=0;
        StringBuilder sb = new StringBuilder();      
        try
        {            
            while(b!=-1)
            {
                b = socket.getInputStream().read();
                if (b!=-1)
                    sb.append(String.format("%02X ", b));
                cont++;
                if (cont == BPL) {
                    sb.append("\r\n");
                    cont = 0;
                }
            }
            System.out.println(sb.toString());
        }
        catch (IOException e) {System.out.println("Excepción " + e);}
    }

    public static void main(String args[]) throws Exception {
        nombre_servidor = "www.upv.es";
        //nombre_servidor = "www.redes.upv.es";
        // A sustituir por conexion_SSL
        conexion_SSL(nombre_servidor, 80); // Establece la conexión. 
        //conexion_SSL(nombre_servidor, 443); // Establece la conexión SSL
        entrada = new ScannerRedes(socket.getInputStream());
        salida = new PrintWriter(socket.getOutputStream());

        //System.out.println("Antes petición");
        envia_peticion("/");
        // Peticion alternativa: imagen1 en www.upv.es
        // envia_peticion(imagen1);
        lee_linea_estado();
        lee_cabeceras();
        
        // Peticion alternativa: imagen1 en www.upv.es
        // Ver volcado de la imagen con         
        //lee_cuerpo_binario_pantalla();
        
        lee_cuerpo_texto();

        //System.out.println("Antes del close");
        socket.close();
        //System.out.println("Tras el close");

    }
}
