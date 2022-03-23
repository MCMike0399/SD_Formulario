package mx.itam.packages.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import mx.itam.packages.rmi.interfaces.Compute;

public class ComputeServer implements Compute { // Interface que define los métodos de nuetro API (RMI)

    public ComputeServer() throws RemoteException {
        super();
    }

    @Override
    public double square(int number) throws RemoteException { // Implementamos los métodos
        return (number * number);
    }

    @Override
    public double power(int num1, int num2) throws RemoteException {
        return (Math.pow(num1, num2));
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "src/mx/itam/packages/rmi/server/server.policy"); // setupea el policy

        if (System.getSecurityManager() == null) { // Si no tiene un security manager internamente, que lo cree
            System.setSecurityManager(new SecurityManager());
        }

        try {
            //String serverAddress = "?.?.?.?";
            String serverAddress = "localhost";
            System.setProperty("java.rmi.server.hostname", serverAddress); // 1. Obtiene la dirección

            // start the rmiregistry --> Registra la dirección con el puerto 1099
            LocateRegistry.createRegistry(1099);   // default port
            // if the rmiregistry is not started by using java code then
            // 1) Start it as follows: rmiregistry -J-classpath -J"c:/.../RMI.jar" or
            // 2) Add this to the classpath C:\...\RMI.jar and then start the rmiregistry

            String serviceName = "Compute"; // Define el nombre del servicio a utilizar
            ComputeServer engine = new ComputeServer(); // Crea el API (RMI)
            Compute stub =
                    (Compute) UnicastRemoteObject.exportObject(engine, 0); // Exporta nuestro API (RMI) para ser utilizado
            Registry registry = LocateRegistry.getRegistry(); // Crea el registro
            registry.rebind(serviceName, stub); // Lo agrega al Binder, liga la dirección localhost con el servicio que estás por implementar
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}