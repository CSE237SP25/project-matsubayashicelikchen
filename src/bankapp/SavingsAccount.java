package bankapp;

public class SavingsAccount {
    private double balance;
    private double interestRate; // Example: 2% interest rate

    // Constructor to open a savings account
    public SavingsAccount() {
        this.balance = 0;
        this.interestRate = 0.02; // Example: 2% interest
    }

    // Get account details
    public void getAccountDetails() {
        System.out.println("Balance: $" + balance);
        System.out.println("Interest Rate: " + (interestRate * 100) + "%");
    }

    // Deposit function
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw function (optional: enforce minimum balance rule)
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". New Balance: $" + balance);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
            return false;
        }
    }

    // Interest calculation function
    public void calculateInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest applied: $" + interest + ". New Balance: $" + balance);
    }

    // Getters (optional, if needed in other classes)
    public double getBalance() { return balance; }
}