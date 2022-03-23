package mx.itam.packages.threads;

public class Hilos {

    public static void main(String[] args) {
   //    HelloThread h0 = new HelloThread();
     //   HelloThread h1 = new HelloThread();
//        Thread h1 = new Thread(new HelloRunnable());
//        h0.start();
//        System.out.println("Instruccion cualquiera");
//        try {
//            h0.join(10); // sincronizacion via barrera
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Continua el main()");
//       h1.start();
        Counter c = new Counter(0);
        SynchronizedThread h0 = new SynchronizedThread(c);
        SynchronizedThread h1 = new SynchronizedThread(c);
        h0.start();
        h1.start();
    }
}
