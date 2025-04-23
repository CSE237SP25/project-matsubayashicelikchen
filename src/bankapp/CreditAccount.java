package bankapp;

public class CreditAccount {
	private final String username;
    public static final int MAX_CREDIT_SCORE = 850;
    private double creditBalance;
    private double creditLimit;
    private double availableCredit; 
    private int creditScore;
    private CreditStatement creditStatement;
    
    /**
     * Constructor to initialize a CreditAccount with a username and a credit statement.
     * @param username The username of the account holder.
     * @param creditStatement The credit statement associated with the account.
     */
    public CreditAccount(String username, CreditStatement creditStatement) {
        this.username = username;
        this.creditStatement = creditStatement;  // Store the provided statement
        this.creditBalance = 0;
        this.creditLimit = 5000;
        this.availableCredit = 5000;
        this.creditScore = 700;
    }

    /**
     * Sets the credit statement for the account.
     * @param statement The credit statement to be associated with the account.
     */
    public void setCreditStatement(CreditStatement statement) {
        this.creditStatement = statement;
    }

    /**
     * Sets the credit limit for the account.
     * @param creditLimit The new credit limit to be set.
     * @throws IllegalArgumentException if the credit limit is negative.
     */
    public void setCreditLimit(double creditLimit) {
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Credit limit cannot be negative");
        }
        this.creditLimit = creditLimit;
    }
  
    /**
     * Repays a certain amount of credit.
     * This method updates the credit balance and available credit accordingly.
     * It also logs the transaction in the credit statement.
     * @param amount The amount to be repaid.
     */
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

    /**
     * Borrows a certain amount of credit.
     * This method updates the credit balance and available credit accordingly.
     * It also logs the transaction in the credit statement.
     * @param amount The amount to be borrowed.
     */
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

    /**
     * Checks if the account is eligible for a certain amount of credit.
     * @param amountToBorrow The amount to check eligibility for.
     * @return true if eligible, false otherwise.
     */
    public boolean isEligibleForCredit(double amountToBorrow) {
        return (this.creditBalance + amountToBorrow) <= getAvailableCredit();
    }
    
    
    //Getters
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