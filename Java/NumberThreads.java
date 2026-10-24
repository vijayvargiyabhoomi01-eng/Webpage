
class NumberThread extends Thread {
    int start, end;

    NumberThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(getName() + ": " + i);
        }
    }
}

public class NumberThreads {
    public static void main(String[] args) {
        NumberThread t1 = new NumberThread(1, 5);
        NumberThread t2 = new NumberThread(6, 10);
        NumberThread t3 = new NumberThread(11, 15);

        t1.start();
        t2.start();
        t3.start();
    }
}
