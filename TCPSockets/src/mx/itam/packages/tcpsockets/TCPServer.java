package mx.itam.packages.tcpsockets;

import java.net.*;
import java.io.*;

public class TCPServer {

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

			out.writeUTF(data);                // envio respuesta (puede ser writeInt, writeByte, etc).

		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}


