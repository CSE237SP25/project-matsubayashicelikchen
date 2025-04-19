package bankapp;

public class SavingsAccount {
    private double balance;

  

    // More flexible constructor allowing custom interest rates
    public SavingsAccount(double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        
        this.balance = initialDeposit;
    }

    public void printAccountDetails() {  
        System.out.printf("""
            Account Details:
            Balance: $%.2f
            """, balance);
    }

    public void deposit(double amount) {
        validatePositiveAmount(amount, "Deposit");
        balance += amount;
        System.out.printf("Deposited $%.2f. New Balance: $%.2f%n", amount, balance);
    }

    public boolean withdraw(double amount) {
        validatePositiveAmount(amount, "Withdrawal");
        
        if (amount > balance) {
            System.out.printf("Withdrawal failed. Requested: $%.2f, Available: $%.2f%n", 
                            amount, balance);
            return false;
        }
        
        balance -= amount;
        System.out.printf("Withdrew $%.2f. New Balance: $%.2f%n", amount, balance);
        return true;
    }

    
  
    private void validatePositiveAmount(double amount, String operation) {
        if (amount <= 0) {
            throw new IllegalArgumentException(operation + " amount must be positive");
        }
    }


    
    public double getBalance() { return balance; }

   
}