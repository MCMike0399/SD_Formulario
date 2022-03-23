package mx.itam.packages.bagoftasks.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mx.itam.packages.bagoftasks.serializableobjects.Task;

public interface ImageProcessing extends Remote {
    public Task executeImageProcessingTask(Task task) throws RemoteException;
}
