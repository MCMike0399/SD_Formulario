package mx.itam.packages.bagoftasks.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mx.itam.packages.bagoftasks.serializableobjects.Task;

public interface Bioinformatics extends Remote {
    public Task executeBioinformaticsTask(Task task) throws RemoteException;
}