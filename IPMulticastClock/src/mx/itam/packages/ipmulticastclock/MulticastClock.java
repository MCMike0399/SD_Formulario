package mx.itam.packages.ipmulticastclock;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Date;

/**
 * @author Octavio Gutierrez
 */
public class MulticastClock {

    public static void main(String args[]) throws InterruptedException {
        MulticastSocket socket = null;
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
            socket = new MulticastSocket(49159);
            socket.joinGroup(group);

            while (true) {
                String currentDate = (new Date()).toString(); // Crea la fecha
                byte[] m = currentDate.getBytes(); // Asigna los bytes a utilizar
                DatagramPacket messageOut =
                        new DatagramPacket(m, m.length, group, 49159); // Crea el mensaje
                socket.send(messageOut); // Envía el mensaje
                Thread.sleep(2000); // Se espera dos segundos
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socket != null) socket.close();
        }
    }
}
