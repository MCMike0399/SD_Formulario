package mx.itam.packages.udpsockets;

import java.net.*;
import java.io.*;

public class UDPClient {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            String myMessage = "Hello";
            byte[] m = myMessage.getBytes(); // Transforma el string a bytes para encapsularlo y enviarlo

            InetAddress aHost = InetAddress.getByName("localhost"); // Conexión a servidor
//            InetAddress aHost = InetAddress.getByAddress("localhost", new byte[] {(byte) 127, (byte) 0, (byte) 0, (byte) 1});
            int serverPort = 49152; // localhost:49152
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000]; // Buffer que genera para recibir el mensaje
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length); // Creas el objeto para recibir el mensaje
            aSocket.receive(reply); // Recibes el mensaje
            System.out.println("Reply: " + (new String(reply.getData())).trim());
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}