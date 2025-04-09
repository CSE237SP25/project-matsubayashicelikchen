package bankapp;

public class CreditAccount {
    private double creditBalance;
    private double creditLimit;

    public CreditAccount() {
        this.creditBalance = 0;
        this.creditLimit = 5000;  // Default credit limit (you can modify this value)
    }

    // Getter for credit balance
    public double getCreditBalance() {
        return creditBalance;
    }

    // Getter for credit limit
    public double getCreditLimit() {
        return creditLimit;
    }

    // Setter for credit limit (optional, in case it needs to be changed)
    public void setCreditLimit(double creditLimit) {
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Credit limit cannot be negative");
        }
        this.creditLimit = creditLimit;
    }

    // Repay credit (reduce balance)
    public void repayCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Repayment amount cannot be negative");
        }
        if (amount > this.creditBalance) {
            throw new IllegalArgumentException("Cannot repay more than the current credit balance");
        }
        this.creditBalance -= amount;
    }

    // Borrow credit (increase balance)
    public void borrowCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Borrow amount cannot be negative");
        }
        if (this.creditBalance + amount > this.creditLimit) {
            throw new IllegalArgumentException("Cannot borrow more than the credit limit");
        }
        this.creditBalance += amount;
    }

    // Check if the account can borrow a certain amount based on the credit limit
    public boolean isEligibleForCredit(double amountToBorrow) {
        return (this.creditBalance + amountToBorrow) <= this.creditLimit;
    }
}

