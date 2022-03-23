package mx.itam.packages.ipmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 * @author Octavio Gutierrez
 */
public class MulticastSenderPeer {

    public static void main(String args[]) {
        MulticastSocket socket = null;
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
            socket = new MulticastSocket(49155);
            socket.joinGroup(group);
            socket.setTimeToLive(10);
            System.out.println("Messages' TTL (Time-To-Live): " + socket.getTimeToLive());
            String myMessage = "Hello";
            byte[] m = myMessage.getBytes(); // Transforma el mensaje en bytes
            DatagramPacket messageOut =
                    new DatagramPacket(m, m.length, group, 49155); // Crea el mensaje a enviar
            socket.send(messageOut); // envia el mensaje
            socket.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socket != null) socket.close();
        }
    }
}
