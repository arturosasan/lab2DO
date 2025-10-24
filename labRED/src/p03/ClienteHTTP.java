package p03;

import java.net.*;
import java.io.*;
import java.util.*;

class ClienteHTTP {

  static String nombre_servidor;
  static Socket s;
  static ScannerRedes entrada;
  static PrintWriter salida;

  public static void envia_peticion(String objeto) {
    salida.printf("GET " + objeto + " HTTP/1.1\r\n");

    salida.printf("Host: " + nombre_servidor + "\r\n");

    salida.printf("Connection: Close\r\n");

    salida.printf("\r\n");

    salida.flush();
  }

  public static void lee_linea_estado() {
    System.out.println(">>>>>>>>>>>>>>> LINEA DE ESTADO <<<<<<<<<<<<<<<");

    System.out.println(entrada.nextLine());
  }

  public static void lee_cabeceras() {
    System.out.println(">>>>>>>>>>>>>>>    CABECERAS    <<<<<<<<<<<<<<<");

    String l = entrada.nextLine();

    while (!l.isEmpty()) {
        System.out.println(l);
        l = entrada.nextLine();
    }
  }

  public static void lee_cuerpo_texto() {
    System.out.println(">>>>>>>>>>>>>>>   CUERPO TEXTO  <<<<<<<<<<<<<<<");

    String l = entrada.nextLine();

    while (!l.isEmpty()) {
        System.out.println(l);
        l = entrada.nextLine();
    }
  }

  public static void lee_cuerpo_binario(String nombre_fichero) {
    System.out.println(">>>>>>>>>>>>>>>  CUERPO BINARIO <<<<<<<<<<<<<<<");

    int contador = 0;

    try {

        int c = s.getInputStream().read();

        FileOutputStream fichero = new FileOutputStream(nombre_fichero);

        while (c != -1) {
            fichero.write(c);

            c = s.getInputStream().read();

            contador++;
        }

        fichero.close();
    }
    catch (IOException e) {
        System.out.println(e);
    }
    finally {
        System.out.println("Bytes leídos --> " + contador);

    }
  }



  public static void main(String args[]) throws Exception {
    nombre_servidor = "zoltar.redes.upv.es";
    s = new Socket(nombre_servidor, 80);
    entrada = new ScannerRedes(s.getInputStream());
    salida = new PrintWriter(s.getOutputStream());
    System.out.println("Descargando '/' ...");
    envia_peticion("/");
    lee_linea_estado();
    lee_cabeceras();
    lee_cuerpo_texto();
    s.close();

    System.out.println("Descargando '/oto1.jpg' ...");
    envia_peticion("/oto1.jpg");
    lee_linea_estado();
    lee_cabeceras();
    lee_cuerpo_binario("oto1.jpg");
    s.close();

    System.out.println("Descargando '/oto2.jpg' ...");
    envia_peticion("/oto2.jpg");
    lee_linea_estado();
    lee_cabeceras();
    lee_cuerpo_binario("oto2.jpg");
    s.close();

    System.out.println("Descarga completa.");
  }

}
