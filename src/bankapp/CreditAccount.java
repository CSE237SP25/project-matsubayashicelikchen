package bankapp;

public class CreditAccount {
    private double creditBalance;
    private int cashBack; 
    private double cashBackRate;
    private double creditLimit;

    public CreditAccount() {
        this.creditBalance = 0;
        this.cashBack = 0;
        this.cashBackRate = 0.01; 
        this.creditLimit = 5000;
    }
  
    public void setCreditLimit(double creditLimit) {
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Credit limit cannot be negative");
        }
        this.creditLimit = creditLimit;
    }
  
    public void repayCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Repayment amount cannot be negative");
        }
        if (amount > this.creditBalance) {
            throw new IllegalArgumentException("Cannot repay more than the current credit balance");
        }
        this.creditBalance -= amount;
        cashBack += amount * cashBackRate;
        creditBalance -= cashBack; //cashBack is automatically applied. 
    }

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
    
    public double getCashBackRate() {
    	return cashBackRate; 
    }
      
    public double getCreditBalance() {
        return creditBalance;
    }

    public double getCreditLimit() {
        return creditLimit;
    }
    
}

