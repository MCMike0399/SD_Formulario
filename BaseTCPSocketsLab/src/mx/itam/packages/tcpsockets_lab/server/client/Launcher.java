package mx.itam.packages.tcpsockets_lab.server.client;

public class Launcher {

    public static void main(String [] args){
        int clientCount = 150; // 150 clientes envían 100 mensajes
        for (int i = 0; i < clientCount; i++) {
            ClientThread clientThread = new ClientThread();
            clientThread.start(); // Crea el hilo, y este mismo hilo hace 100 mensajes
        }
    }//main
}
