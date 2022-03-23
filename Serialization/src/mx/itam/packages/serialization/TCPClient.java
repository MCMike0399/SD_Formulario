package mx.itam.packages.serialization;

import java.net.*;
import java.io.*;

/**
 * @author Octavio Gutierrez
 */
public class TCPClient {

    public static void main(String args[]) {

        Socket socket = null;

        try {
            int serverPort = 49152;
            socket = new Socket("localhost", serverPort); // dónde lo vas a enviar y el puerto

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); // EL objecto con el que vas a enviar

            Person me = new Person("Octavio", "San Luis", 1971); // Creas la persona
            out.writeObject(me); // Envias el objeto

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // REcibes los bytes del servidor, el mensaje mas no la persona en sí

            try {
                me = (Person) in.readObject(); // Lees el objeto dentro del mensaje y casteas a Person
                System.out.println("Received: " + "Name: " + me.getName() + ". Birth place: " + me.getPlace() + ". Year: " + me.getYear());
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
    }
}
