package mx.itam.packages.rmicredentials.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import mx.itam.packages.rmicredentials.interfaces.Compute;
import mx.itam.packages.rmicredentials.interfaces.Credential;

public class ComputeClient {

    public static void main(String args[]) {
        System.setProperty("java.security.policy", "src/mx/itam/packages/rmicredentials/client/client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            //String serverAddress = "?.?.?.?";
            String serverAddress = "localhost";
            String serviceName = "Compute";
            Registry registry = LocateRegistry.getRegistry(serverAddress); // server's ip address args[0]
            Compute comp = (Compute) registry.lookup(serviceName);

            Credential credential = new Credential();

            System.out.println("3^2 = " + comp.square(3, credential));
            System.out.println("3^3 = " + comp.power(3, 3, credential));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
