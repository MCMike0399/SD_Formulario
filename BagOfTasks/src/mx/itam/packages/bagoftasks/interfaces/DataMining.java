package mx.itam.packages.bagoftasks.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mx.itam.packages.bagoftasks.serializableobjects.Task;

public interface DataMining extends Remote {
    public Task executeDataMiningTask(Task task) throws RemoteException;
}
