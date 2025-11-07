package p04;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientePOP3 {

        static void error(String cadena) {
		System.out.println(cadena);
		System.exit(0);
	}

        static void PrintMenu() {
		System.out.println("\r\n-----------------------------");
		System.out.println("Ordenes de POP3 implementadas:");
		System.out.println("\t1: LIST");
		System.out.println("\t2: RETR");
		System.out.println("\t3: DELE ");
		System.out.println("\t4: RSET");
		System.out.println("\t5: QUIT");
		System.out.println("Elige orden (1..5):");
		System.out.println("-----------------------------\r\n");
	}

	public static void main(String args[]) {
	try{
		Socket s=new Socket("serveis-rdc.redes.upv.es", 110);
		System.out.println("Conectado al servidor POP3 de serveis-rdc");
		PrintWriter salida = new PrintWriter(s.getOutputStream());
		Scanner entrada=new Scanner(s.getInputStream());
		String respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("+OK")) {error(respuesta);}

		System.out.println("Enviando nombre de usuario");
		salida.print("USER redes08\r\n");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("+OK")) {error(respuesta);}

		System.out.println("Enviando password");
		salida.print("PASS bloqueo\r\n");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("+OK")) {error(respuesta);}

		Scanner teclado = new Scanner(System.in);
		Boolean continuar = true;
		int numero;
		// a continucion viene el bucle para leer, listar
		// o borrar correos y salir del programa
		while (continuar) {
			PrintMenu();
			int orden = teclado.nextInt();
			switch (orden) {
            			case 1: salida.print("LIST\r\n");
					salida.flush();
					respuesta = entrada.nextLine();
					// Aqui hace falta un bucle while para leer
					//todas las lineas de la respuesta
					while (!respuesta.equals(".")) {
						respuesta = entrada.nextLine();
                        System.out.println(respuesta);
						// Aqui se leen
						//todas las lineas de la respuesta
					}
					break;
				case 2: System.out.println("Elige numero de correo a leer: ");
					numero = teclado.nextInt();
					salida.print("RETR " + numero + "\r\n");
					salida.flush();
					respuesta = entrada.nextLine();
					System.out.println(respuesta);
					if (respuesta.startsWith("+OK")) {
						// Aqui hace falta un bucle while para leer
						//todas las lineas de la respuesta
						while (!respuesta.equals(".")) {
                        respuesta = entrada.nextLine();
                        System.out.println(respuesta);
						}
					}
					break;
				case 3: System.out.println("Elige numero de correo a borrar");
					numero = teclado.nextInt();
					salida.print("DELE" + numero + "\r\n");
					salida.flush();
					System.out.println(entrada.nextLine());
					break;
				case 4: salida.print("RSET\r\n");
					salida.flush();
					System.out.println(entrada.nextLine());
					break;
				case 5: salida.print("QUIT\r\n");
					salida.flush();
					System.out.println(entrada.nextLine());
					continuar = false;
					break;
				default:System.out.println("Opcion no implementada");
					break;
			}
		}

		s.close();
		System.out.println("Desconectado");

	} catch (UnknownHostException e) {
		System.out.println("Host desconocido");
		System.out.println(e);
	} catch (IOException e) {
		System.out.println("No se puede conectar");
		System.out.println(e);
	}
	}
}
