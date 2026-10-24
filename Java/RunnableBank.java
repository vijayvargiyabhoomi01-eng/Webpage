
class Bank {
    int balance = 1000;

    synchronized void deposit(int amount) {
        balance = balance + amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Balance after deposit: " + balance);
    }

    synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}

class DepositTask implements Runnable {
    Bank bank;

    DepositTask(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        bank.deposit(500);
    }
}

class WithdrawTask implements Runnable {
    Bank bank;

    WithdrawTask(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        bank.withdraw(300);
    }
}

public class RunnableBank {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Thread t1 = new Thread(new DepositTask(bank));
        Thread t2 = new Thread(new WithdrawTask(bank));

        t1.start();
        t2.start();
    }
}
