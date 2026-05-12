//
// This file must be implemented when completing "ChatRobot activity"
//

import faces.IChatChannel;
import faces.IChatMessage;
import faces.IChatServer;
import faces.IChatUser;
import faces.INameServer;
import faces.MessageListener;
import impl.ChatUserImpl;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

import utils_rmi.*;

/**
 * ChatRobot implementation
 * 
 * <p>
 * Notice ChatRobot implements MessageListener. MUST not extend ChatClient.
 * 
 */

public class ChatRobot implements MessageListener {

  private ChatConfiguration conf;
  private IChatServer srv = null;
  private IChatUser usuarioBotillo = null;
  private IChatChannel myChannel = null;
  
  public ChatRobot(ChatConfiguration conf) {
    this.conf = conf;
  }

  @Override
  public void messageArrived(IChatMessage msg) {
    // *****************************************************************
    // Activity: implement robot message processing

  }

  private void work() {

    String channelName = conf.getChannelName();
    if (channelName == null) {
      channelName = "#Linux";
      System.out.println("CAMBIANDO A CANAL POR DEFECTO (#LINUX)");
    }
    System.out.println("Robot will connect to server: '" + conf.getServerName() + "'" +
        ", channel: '" + channelName + "'" +
        ", nick: '" + conf.getNick() + "'" +
        ", using name server: '" + conf.getNameServerHost() + ":" + conf.getNameServerPort() + "'");

    try {
      // *****************************************************************
      // Activity: implement robot connection and joining to channel
      
      // Paso 5.4.- Obtener la lista de canales

      /*
      * Un usuario puede conectarse a cualquier canal (y posteriormente desconectarse). ChatServerImpl
      * implementa el método listChannels, que devuelve la lista de canales disponibles. Se debería obtener esa 
      * información antes de intentar conectarse a un canal. En concreto:
      *   • ChatClient necesita la lista de canales para mostrarlos en pantalla y permitir al usuario elegir el canal deseado.
      *   • ChatRobot recibe el canal al que conectarse como argumento (channelName), pero debería verificar que 
      *     el valor de dicho argumento aparece en la lista de canales.
      *
      */

      IChatChannel[] canales = srv.listChannels();
      System.out.println("LISTA DE CANALES DEL SERVIDOR: "); System.out.println(canales); 
      System.out.println("CANAL ELEGIDO => " + channelName);

      // Paso 5.5.- Unirse al canal

      /*
      * Para unirse a un canal hay que obtener previamente la referencia al canal correspondiente (con el método 
      * getChannel de IChatServer) e invocar el método join sobre dicha referencia. Al conectarse a un canal se 
      * obtiene la lista de usuarios del mismo, que incluirá al propio usuario que ha pedido unirse al canal.  
      */

      IChatChannel miCanal = srv.getChannel(channelName);
      IChatUser usuariosCanal[] = miCanal.join(usuarioBotillo);
      System.out.println("CONECTADO A CANAL => " + miCanal);
      System.out.println("LISTA USUARIOS DEL CANAL " + miCanal + " => "); System.out.println(usuariosCanal);

    } catch (Exception e) {
      System.err.print("Something went wrong: " + e.getStackTrace());
    }

  }

private void connect() {

    try {
      
      // Paso 5.1.- Obtener referencia al NameServer

      /*
       * El robot debe recoger desde línea de órdenes los argumentos nshost, nsport,
       * serverName,
       * channelName, y nick. Para ello se utiliza la clase ChatConfiguration.
       * Una vez conocido el valor de nshost y nsport, deben utilizarse para acceder
       * al NameServer, como paso
       * previo para localizar al ChatServer
       *
       */

      INameServer reg = INameServer.getNameServer(conf.getNameServerHost(), conf.getNameServerPort());
      System.out.println("Ubicación NameServer => " + RemoteUtils.remote2String(reg));

      // Paso 5.2.- Obtener la referencia al ChatServer

      /* 
       * Cuando arranca el ChatServer, crea un objeto remoto y lo registra en el NameServer bajo un nombre X
       * determinado. El robot recibe en el argumento serverName el nombre del ChatServer con el que debe
       * contactar (supongamos que es X). Para obtener acceso al objeto remoto correspondiente, el robot debe
       * preguntar al NameServer por el objeto remoto llamado X. Este procedimiento es similar al realizado por ChatClient.
       *
      */

      srv = (IChatServer) reg.lookup(conf.getNameServerHost());
      if (srv == null) { 
        throw new Exception("ERROR, SERVIDOR " + conf.getServerName() + " NO ENCONTRADO");} 
      else {
        System.out.println("Ubicación objeto remoto de chat en NameServer => " + RemoteUtils.remote2String(srv));
      }

      // Paso 5.3.- Conectarse al ChatServer

      /*
      * Una vez que el robot tiene acceso al servidor, necesita conectarse al mismo. 
      * De esta forma, ChatServerpuede:
      *   • Validar el nick proporcionado (el nick de cada usuario del chat debe ser único y no puede estar vacío)
      *   • Obtener una referencia al IChatUser correspondiente: cada usuario del chat implementa un objeto remoto y 
      *     le pasa la referencia al ChatServer, de forma que el ChatServer puede invocar determinadas operaciones 
      *     sobre cada usuario registrado.
      * Para registrarse, ChatRobot debe invocar el método connectUser de IChatServer, pasando como
      * argumento un objeto de tipo ChatUser (que contiene el nick del robot)
      * 
      */
     if (conf.getNick() == null) {
        throw new Exception("ERROR, EL NICKNAME NO PUEDE ESTAR VACÍO");
     } else {
        usuarioBotillo = new ChatUserImpl(conf.getNick(), this);
        srv.connectUser(usuarioBotillo);
        System.out.println("BOTILLO CONECTADO AL SERVIDOR");
     }
    } catch (java.rmi.ConnectException e) {
      System.err.print("Error de conexión al servidor => " + e.getStackTrace());
    } catch (java.rmi.AlreadyBoundException e) { // nickname ya en uso?
      System.err.print("Nickname " + conf.getNick() + " ya en uso");
    } catch (Exception e) {
      System.err.print("ERROR => " + e.getStackTrace());
    }
  }

  public static void main(String args[]) {
    ChatRobot cr = new ChatRobot(ChatConfiguration.parse(args));
    cr.work();
    cr.connect();
  }
}
