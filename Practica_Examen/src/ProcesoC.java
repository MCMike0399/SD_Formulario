import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ProcesoC implements Compute {

    public ProcesoC() throws RemoteException {
        super();
    }

    @Override
    public String modificaMsj(String msj) {
        return msj + " modificado";
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "src/server.policy"); // setupea el policy
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
            ProcesoC engine = new ProcesoC(); // Crea el API (RMI)
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
