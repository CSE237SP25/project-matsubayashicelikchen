package bankapp;

public class BankAccount {
    private double balance;
    private double creditBalance;

    public BankAccount() {
        this.balance = 0;
        this.creditBalance = 0;
    }
    
    public void deposit(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
    }
    
    public double getCurrentBalance() {
        return balance;
    }
    
    public double getCreditBalance() {
        return creditBalance;
    }
    
    public void borrowCredit(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Borrow amount cannot be negative");
        }
        this.creditBalance += amount;
    }
    
    public void repayCredit(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Repayment amount cannot be negative");
        }
        if(amount > this.creditBalance) {
            throw new IllegalArgumentException("Cannot repay more than the current credit balance");
        }
        this.creditBalance -= amount;
    }
}
