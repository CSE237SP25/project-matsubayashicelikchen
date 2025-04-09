package tests;

public class BankAccountTests{
    private double balance;
    private double creditBalance;

    public BankAccountTests() {
        this.balance = 0;
        this.creditBalance = 0;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
    }

    public double getCurrentBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (ensureValid(amount)) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean ensureValid(double withdrawalAmt) {
        if (withdrawalAmt > balance) {
            throw new IllegalArgumentException("Attempting to withdraw sum greater than balance");
        } else if (withdrawalAmt <= 0) {
            throw new IllegalArgumentException("Must withdraw a positive sum of money");
        }
        return true;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void borrowCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot borrow a negative amount");
        }
        creditBalance += amount;
    }

    public void repayCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot repay a negative amount");
        }
        if (amount > creditBalance) {
            throw new IllegalArgumentException("Repay amount exceeds credit balance");
        }
        creditBalance -= amount;
    }
}
