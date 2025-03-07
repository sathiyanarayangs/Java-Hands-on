import java.util.ArrayList;
import java.util.List;

// Custom Exception for Insufficient Balance
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// Bank Account Class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized Deposit Method
    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " Deposited: Rs." + amount + " | New Balance: Rs." + balance);
    }

    // Synchronized Withdraw Method
    public synchronized void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(Thread.currentThread().getName() + "Insufficient balance for withdrawal of Rs." + amount);
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + " Withdrawn: Rs." + amount + " | New Balance: Rs." + balance);
    }

    // Get Balance Method
    public synchronized double getBalance() {
        return balance;
    }
}

// Transaction Task (Implements Runnable)
class Transaction implements Runnable {
    private BankAccount account;
    private boolean isDeposit;
    private double amount;

    public Transaction(BankAccount account, boolean isDeposit, double amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;  // ✅ FIXED: Assigning the amount correctly
    }

    @Override
    public void run() {
        try {
            if (isDeposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}

// Main Class
public class BankingSystem {
    public static void main(String[] args) {
        // Create Bank Account with ₹10,000 Initial Balance
        BankAccount account = new BankAccount(10000);

        // List of Threads
        List<Thread> threads = new ArrayList<>();

        // Create and Start Transactions
        threads.add(new Thread(new Transaction(account, false, 5000), "Alice"));
        threads.add(new Thread(new Transaction(account, true, 2000), "Bob"));
        threads.add(new Thread(new Transaction(account, false, 8000), "Charlie")); // Should trigger exception
        threads.add(new Thread(new Transaction(account, true, 3000), "David"));

        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for Threads to Complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print Final Balance
        System.out.println("Final Account Balance: Rs." + account.getBalance());
    }
}
