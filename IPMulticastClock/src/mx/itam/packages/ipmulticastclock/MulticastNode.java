package mx.itam.packages.ipmulticastclock;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 * @author Octavio Gutierrez
 */
public class MulticastNode {

    public static void main(String args[]) {
        MulticastSocket socket = null;
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
            socket = new MulticastSocket(49159);
            socket.joinGroup(group);
            byte[] buffer = new byte[1000];
            System.out.println("Waiting for messages");
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            socket.receive(messageIn);
            System.out.println("Message: " + new String(messageIn.getData()).trim() + " from: " + messageIn.getAddress());
            socket.leaveGroup(group); // Termina el proceso de recibir mensajes
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socket != null) socket.close();
        }
    }
}
