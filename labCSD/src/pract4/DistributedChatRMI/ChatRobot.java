import faces.*;
import impl.*;
import java.rmi.*;
import java.util.*;
import utils_rmi.*;

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
        try {
            // Depuración: ver qué llega al robot
            // System.out.println("Mensaje recibido: " + msg.getText() + " | Privado: " + msg.isPrivate());

            if (msg.isPrivate()) {
                String nickEmisor = msg.getSender().getNick();
                
                // IMPORTANTE: Compara el nick de quien escribe con el login del robot
                if (nickEmisor.equals("asarsan")) {
                    String respuestaTexto = "Saludos " + nickEmisor + " - " + msg.getText();
                    
                    IChatMessage respuesta = new ChatMessageImpl(usuarioBotillo, msg.getSender(), respuestaTexto);
                    
                    msg.getSender().sendMessage(respuesta);
                    System.out.println(">>> Respondido privado a " + nickEmisor);
                } else {
                    System.out.println(">>> Ignorado privado de " + nickEmisor + " (solo respondo a asarsan)");
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void work() {
        String channelName = conf.getChannelName() != null ? conf.getChannelName() : "#Friends";
        
        try {
            INameServer ns = INameServer.getNameServer(conf.getNameServerHost(), conf.getNameServerPort());
            srv = (IChatServer) ns.lookup(conf.getServerName());

            if (conf.getNick() == null) {
                System.out.println("ERROR: Debes lanzar el robot con nick=TuNombre");
                return;
            }

            usuarioBotillo = new ChatUserImpl(conf.getNick(), this);
            srv.connectUser(usuarioBotillo);
            System.out.println("1. Robot conectado al servidor como: " + conf.getNick());
            
            myChannel = srv.getChannel(channelName);
            if (myChannel != null) {
                myChannel.join(usuarioBotillo);
                System.out.println("2. Robot unido al canal: " + channelName);
                System.out.println("3. ESPERANDO MENSAJES PRIVADOS DE: " + "asarsan");
            } else {
                System.out.println("ERROR: El canal " + channelName + " no existe en el servidor.");
            }

        } catch (Exception e) {
            System.err.println("ERROR en el arranque:");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new ChatRobot(ChatConfiguration.parse(args)).work();
    }
}