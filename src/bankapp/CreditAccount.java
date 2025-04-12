package bankapp;

public class CreditAccount {
    public static final int MAX_CREDIT_SCORE = 850;
    private double creditBalance;
    private int cashBack; 
    private double cashBackRate;
    private double creditLimit;
    private int creditScore;
  
   public CreditAccount() {
        this.creditBalance = 0;
        this.cashBack = 0;
        this.cashBackRate = 0.01; 
        this.creditLimit = 5000;
        this.creditScore = 700;
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
      
        // Increase credit score only if it's less than MAX_CREDIT_SCORE
        if(this.creditScore < MAX_CREDIT_SCORE) {
            this.creditScore += 1; 
        }
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
  
   public int getCreditScore() {
        return creditScore;
    }   
}