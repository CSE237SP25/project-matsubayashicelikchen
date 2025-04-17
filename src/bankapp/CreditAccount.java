package bankapp;

public class CreditAccount {
	private final String username;
    public static final int MAX_CREDIT_SCORE = 850;
    private double creditBalance;
//    private int cashBack; 
//    private double cashBackRate;
    private double creditLimit;
    private double availableCredit; 
    private int creditScore;
    private CreditStatement creditStatement;
    
    public CreditAccount(String username, CreditStatement creditStatement) {
        this.username = username;
        this.creditStatement = creditStatement;  // Store the provided statement
        this.creditBalance = 0;
        this.creditLimit = 5000;
        this.availableCredit = 5000;
        this.creditScore = 700;
    }
  
//   public CreditAccount(String username) {
//        this.creditBalance = 0;
////        this.cashBack = 0;
////        this.cashBackRate = 0.01; 
//        this.creditLimit = 5000;
//        this.availableCredit = 5000; 
//        this.creditScore = 700;
//        this.username = username;
//    }
    
    public void setCreditStatement(CreditStatement statement) {
        this.creditStatement = statement;
    }

 
    public void setCreditLimit(double creditLimit) {
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Credit limit cannot be negative");
        }
        this.creditLimit = creditLimit;
    }
  
    public void repayCredit(double amount) {
        if (amount < 0) {
            System.out.println("Repayment amount cannot be negative.");
            return;
        }
        if (amount > this.creditBalance) {
            System.out.println("Cannot repay more than the current credit balance.");
            return;
        }
        
        // Process payment
        this.creditBalance -= amount;
        this.availableCredit += amount;
        
        // Log the transaction
        if (this.creditStatement != null) {
            this.creditStatement.logTransaction("REPAY", amount, "Payment");
        }
        
        System.out.println("Credit account payment successful!");
        
        // Increase credit score
        if (this.creditScore < MAX_CREDIT_SCORE) {
            this.creditScore += 1; 
        }
    }

    public void borrowCredit(double amount) {
        if (amount < 0) {
        	System.out.println("Borrow amount cannot be negative.");
        	return; 
        }
        if (this.creditBalance + amount > this.creditLimit) {
            System.out.println("Cannot borrow more than the credit limit.");
            return; 
        }
        System.out.println("You borrowed " + amount + " dollar from the bank.");
        creditStatement.logTransaction("BORROW", amount, "Description");
        this.creditBalance += amount;
        this.availableCredit -= amount;
    }

    // Check if the account can borrow a certain amount based on the available credit
    public boolean isEligibleForCredit(double amountToBorrow) {
        return (this.creditBalance + amountToBorrow) <= getAvailableCredit();
    }
    
//    public double getCashBackRate() {
//    	return cashBackRate; 
//    }
      
    public double getCreditBalance() {
        return creditBalance;
    }

    public double getCreditLimit() {
        return creditLimit;
    }
    
    public double getAvailableCredit() {
    	return availableCredit;
    }
  
   public int getCreditScore() {
        return creditScore;
    }
   
   public CreditStatement getCreditStatement() {
	    return this.creditStatement;
	}
   
   public String getUsername() {
       return username;
   }
}