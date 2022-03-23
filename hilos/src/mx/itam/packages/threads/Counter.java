package mx.itam.packages.threads;

public class Counter {

    private int count = 0;

    public Counter(int count) {
        this.count = count;
    }

    public void increaseAndPrint(String hiloId) {
        for (int i = 0; i < 10000; i++) {
            synchronized (this) {
                count++;
                System.out.println(count + " " + hiloId);
            }
        }
    }
}
