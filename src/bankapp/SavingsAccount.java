package bankapp;

public class SavingsAccount {
    private static int nextAccountNumber = 1000;
    private int accountNumber;
    private double balance;
    private double interestRate;
    private int userId;

    public SavingsAccount(int userId, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        this.accountNumber = nextAccountNumber++;
        this.userId = userId;
        this.balance = initialDeposit;
        this.interestRate = 0.02;
        // FileHandler.updateFile(this.accountNumber, this.userId, this.balance); // Uncomment if using file persistence
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        // FileHandler.updateFile(this.accountNumber, this.userId, this.balance); // Uncomment if needed
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        // FileHandler.updateFile(this.accountNumber, this.userId, this.balance); // Uncomment if needed
        return true;
    }
    
    public void calculateInterest() {
        double interest = balance * interestRate;
        balance += interest;
        // FileHandler.updateFile(this.accountNumber, this.userId, this.balance); // Uncomment if needed
    }

    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public int getUserId() { return userId; }
    public double getInterestRate() { return interestRate; }
}