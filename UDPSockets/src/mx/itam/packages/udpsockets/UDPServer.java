package mx.itam.packages.udpsockets;

import java.net.*;
import java.io.*;

public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            int serverPort = 49152;
            aSocket = new DatagramSocket(serverPort);
            byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
            while (true) {
                System.out.println("Waiting for messages...");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request); // Espera a la cola de mensajes recibidos

                DatagramPacket reply = new
                        DatagramPacket(request.getData(), // Es el mismo mensaje que recibiste, un echo
                        request.getLength(),
                        request.getAddress(), // Dirección IP
                        request.getPort()); // Determina dónde lo va a enviar

                System.out.println("Server received a request from " + request.getAddress());
                aSocket.send(reply);
            }
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
