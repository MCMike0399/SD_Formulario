package mx.itam.packages.bagoftasks.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Deployer {

    public static void main(String[] args) throws RemoteException {

        //String serverAddress = "?.?.?.?";
        String serverAddress = "localhost";
        System.setProperty("java.rmi.server.hostname", serverAddress);

        // start the rmiregistry
        LocateRegistry.createRegistry(1099);   /// default port

        SlaveNode[] slaveNodes = {new SlaveNode(), new SlaveNode(), new SlaveNode()};

        String[] services = new String[3];

        slaveNodes[0].deploy("Bioinformatics");
        slaveNodes[1].deploy("DataMining");
        slaveNodes[2].deploy("ImageProcessing");

    }
}
