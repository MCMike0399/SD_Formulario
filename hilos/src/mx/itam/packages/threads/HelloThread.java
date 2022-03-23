package mx.itam.packages.threads;

public class HelloThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++)
            System.out.println(i + " Hola soy el extends hilo: " + Thread.currentThread().getName());
    }
}

