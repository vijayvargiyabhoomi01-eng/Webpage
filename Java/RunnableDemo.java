
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        MyRunnable obj = new MyRunnable();

        Thread t1 = new Thread(obj, "Thread 1");
        Thread t2 = new Thread(obj, "Thread 2");
        Thread t3 = new Thread(obj, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
