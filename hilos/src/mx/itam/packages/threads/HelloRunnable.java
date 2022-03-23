package mx.itam.packages.threads;

public class HelloRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++)
            System.out.println(i + " Hola soy el runnable hilo: " + Thread.currentThread().getName());
    }
}
