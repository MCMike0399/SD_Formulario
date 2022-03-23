package mx.itam.packages.ipmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 * @author Octavio Gutierrez
 */
public class MulticastReceivingPeer {

    public static void main(String args[]) {
        MulticastSocket socket = null;
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
            socket = new MulticastSocket(49155); // Crea el socket en el puerto 49155
            socket.joinGroup(group);
            byte[] buffer = new byte[1000]; // Destina el buffer
            for (int i = 0; i < 3; i++) {
                System.out.println("Waiting for messages");
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length); // Crea el objeto para recibir el mensaje
				socket.receive(messageIn); // Recibe el mensaje
                System.out.println("Message: " + new String(messageIn.getData()).trim() + " from: " + messageIn.getAddress());
            }
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
