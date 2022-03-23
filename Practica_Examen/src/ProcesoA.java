import java.net.*;
import java.io.*;

public class ProcesoA {
    public static void main(String[] args) {
        Socket s = null;

        try {
            int serverPort = 49152;

            s = new Socket("localhost", serverPort); // Dónde lo va a enviar
            //s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeUTF("Mensaje desde Proceso A");            // UTF is a string encoding, envía el mensaje al servidor

            String data = in.readUTF(); // Recibe la respuesta
            System.out.println("Received: " + data);

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
