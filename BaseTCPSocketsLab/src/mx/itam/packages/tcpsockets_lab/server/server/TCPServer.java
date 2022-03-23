package mx.itam.packages.tcpsockets_lab.server.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String args[]) {
		try {
			int serverPort = 49152;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				System.out.println("Waiting for messages...");
				Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
				Connection c = new Connection(clientSocket);
				c.start(); // Inicia el hilo del servidor
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}

}

class Connection extends Thread {
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
	public void run() {
		try {
			AddressBook aB = new AddressBook();
			System.out.println("server run");
			while(true) {
				int data = in.readInt(); // Es la clave que envía el cliente
				System.out.println("Message received from : " + clientSocket.getRemoteSocketAddress());// recibo solicitud
				if(data > 4) // si la clave es mayor, muere el proceso
					break;
				out.writeUTF(aB.getRecord(data).getName());  // envio respuesta con el nombre
			}
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


