package mx.itam.packages.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import mx.itam.packages.rmi.interfaces.Compute;

public class ComputeClient {

    public static void main(String args[]) {
        System.setProperty("java.security.policy", "src/mx/itam/packages/rmi/client/client.policy"); // Setupea el policy

        if (System.getSecurityManager() == null) { // Si no tiene un security manager internamente, que lo cree
            System.setSecurityManager(new SecurityManager());
        }
        try {
            //String serverAddress = "?.?.?.?";
            String serverAddress = "localhost"; // dónde lo queremos enviar
            String serviceName = "Compute"; // Qué servicio queremos utlizar
            Registry registry = LocateRegistry.getRegistry(serverAddress); // server's ip address args[0]
            // obtenemos el registro del servidor que fue ligado al binder
            Compute comp = (Compute) registry.lookup(serviceName); // Busca el servicio del servidor y crea un objeto del mismo servicio
            // Ejecutamos los procedimientos del servicio Compute
            System.out.println("3^2 = " + comp.square(3));
            System.out.println("3^3 = " + comp.power(3, 3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
