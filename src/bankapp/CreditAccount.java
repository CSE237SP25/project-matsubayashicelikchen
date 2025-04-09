package bankapp;

public class CreditAccount {
    private double creditBalance;
    private int cashBack; 
    private double cashBackRate; 

    public CreditAccount() {
        this.creditBalance = 0;
        this.cashBack = 0;
        this.cashBackRate = 0.01; 
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void borrowCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Borrow amount cannot be negative");
        }
        this.creditBalance += amount;
        cashBack += amount * cashBackRate;
        creditBalance -= cashBack; //cashBack is automatically applied. 
    }

    public void repayCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Repayment amount cannot be negative");
        }
        if (amount > this.creditBalance) {
            throw new IllegalArgumentException("Cannot repay more than the current credit balance");
        }
        this.creditBalance -= amount;
    }
    
    public double getCashBackRate() {
    	return cashBackRate; 
    }
    
}
