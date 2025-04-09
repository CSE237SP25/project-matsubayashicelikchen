package bankapp;

public class CreditAccount {
	public static final int MAX_CREDIT_SCORE = 850;
    private double creditBalance;
    private int creditScore;

    public CreditAccount() {
        this.creditBalance = 0;
        this.creditScore = 700;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void borrowCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Borrow amount cannot be negative");
        }
        this.creditBalance += amount;
    }

    public void repayCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Repayment amount cannot be negative");
        }
        if (amount > this.creditBalance) {
            throw new IllegalArgumentException("Cannot repay more than the current credit balance");
        }
        this.creditBalance -= amount;
        
     // Increase credit score only if it's less than MAX_CREDIT_SCORE
        if(this.creditScore < MAX_CREDIT_SCORE) {
            this.creditScore += 1; 
        }
    }
    
    public int getCreditScore() {
        return creditScore;
    }
}



