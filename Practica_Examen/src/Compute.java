import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Compute extends Remote{
    public String modificaMsj(String msj) throws RemoteException;
}
