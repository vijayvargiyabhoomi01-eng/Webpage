
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

class DepositThread extends Thread {
    Bank bank;

    DepositThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        bank.deposit(500);
    }
}

class WithdrawThread extends Thread {
    Bank bank;

    WithdrawThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        bank.withdraw(300);
    }
}

public class BankTransactions {
    public static void main(String[] args) {
        Bank bank = new Bank();

        DepositThread t1 = new DepositThread(bank);
        WithdrawThread t2 = new WithdrawThread(bank);

        t1.start();
        t2.start();
    }
}
