import java.net.*;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProcesoB {
    public static void main(String args[]) {
        try {
            int serverPort = 49152;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
                // Como debes tener un acuerdo entre el cliente y servidor, bloquea el socket hasta que se logre la conexión
                Connection c = new Connection(clientSocket);
                c.start(); // Inicia el hilo de la conexión
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}
class Connection extends Thread { // Primero debes crear la conexión, este debe ser un hilo separado para que pueda estar esperando los mensajes
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() { // como es un hilo tiene su método run
        try {
            // an echo server
            String data = in.readUTF();         // recibo solicitud (puede ser readInt,readByte, etc).
            System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());

            String serverAddress = "localhost"; // dónde lo queremos enviar
            String serviceName = "Compute"; // Qué servicio queremos utlizar
            Registry registry = LocateRegistry.getRegistry(serverAddress); // server's ip address args[0]
            // obtenemos el registro del servidor que fue ligado al binder
            Compute comp = (Compute) registry.lookup(serviceName); // Busca el servicio del servidor y crea un objeto del mismo servicio
            // Ejecutamos los procedimientos del servicio Compute
            String mensajeModificado = null;
            mensajeModificado = comp.modificaMsj(data);
            out.writeUTF(mensajeModificado);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}