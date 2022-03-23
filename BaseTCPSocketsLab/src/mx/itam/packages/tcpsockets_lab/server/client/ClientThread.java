package mx.itam.packages.tcpsockets_lab.server.client;

public class ClientThread extends Thread{
    public ClientThread(){

    }
    @Override
    public void run(){
        TCPClient tcp = new TCPClient(100); // El cliente envía 100 mensajes
        tcp.call();
    }
}//class
