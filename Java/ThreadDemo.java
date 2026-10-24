
class MyThread extends Thread {
    public void run() {
        System.out.println(getName() + " is running");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(getName() + " has finished");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        System.out.println("Thread 1: " + t1.getState());
        System.out.println("Thread 2: " + t2.getState());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Thread 1: " + t1.getState());
        System.out.println("Thread 2: " + t2.getState());
    }
}
