package bankapp;

public class CreditAccount {
    private double creditBalance;
    private int creditScore;

    public CreditAccount() {
        this.creditBalance = 0;
        this.creditScore = 700;
    }

    public double getCreditBalance() {
        return creditBalance;
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
    
    
    public int getCreditScore() {
        return creditScore;
    }
    
    public void updateCreditScore(int newScore) {
        if (newScore < 300 || newScore > 850) {
            throw new IllegalArgumentException("Credit score must be between 300 and 850");
        }
        this.creditScore = newScore;
    }
    
 // check if the account is eligible for borrowing based on credit score value. 
    public boolean isEligibleForCredit(double amountToBorrow) {
        if (this.creditScore >= 650) {
            return true;
        } else {
            return false;
        }
    }
    
    public void borrowCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Borrow amount cannot be negative");
        }
        if (!isEligibleForCredit(amount)) {
            throw new IllegalArgumentException("Credit score too low for borrowing this amount");
        }
        this.creditBalance += amount;
    }
    
    
}
