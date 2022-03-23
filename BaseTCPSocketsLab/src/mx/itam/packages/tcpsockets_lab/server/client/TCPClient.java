package mx.itam.packages.tcpsockets_lab.server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    private int n;
    public TCPClient(int n){
        this.n = n;
    }

    private double[] stdDev(double[] list){ // Desviación Estandar
        double sum = 0.0;
        double num= 0.0;
        for (int i=0; i< list.length; i++)
            sum+=list[i];
        double mean = sum / list.length;
        for (int i= 0; i< list.length; i++)
            num += Math.pow((list[i] -mean), 2);
        double [] res = {mean, Math.sqrt(num / list.length)};
        return res;
    }

    public void call() { // Metodo para hacer el Request, o la llamada (Es lo que estaba en el main en HolaTCP)

        Socket s = null;

        try {
            int serverPort = 49152;

            s = new Socket("localhost", serverPort);
            //s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            int id;
            String data;
            double[] startTime = new double[n];
            double st, ft;
            for(int i = 0; i < n; i++) {
                st = System.currentTimeMillis();
                id = (int) Math.floor(Math.random() * 4);
                //System.out.println(id);
                out.writeInt(id); // Envía la clave
                //System.out.println("Antes de recibir");
                data = in.readUTF(); // Recibe el nombre
                //System.out.println("Received: " + data);
                ft = System.currentTimeMillis();
                startTime[i] = ft - st;
            }
            double [] calcs = stdDev(startTime);
            System.out.println("Media: " + calcs[0] + " --------- Desviacion: " + calcs[1]);


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
