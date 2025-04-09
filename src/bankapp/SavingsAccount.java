package bankapp;

public class SavingsAccount {
    private static int nextAccountNumber = 1000;
    private final int accountNumber;  // Made final since it shouldn't change
    private double balance;
    private final double interestRate;  // Made final if rate is fixed
    private final int userId;  // Made final since it shouldn't change

    public SavingsAccount(int userId, double initialDeposit) {
        this(userId, initialDeposit, 0.02);  // Default interest rate 2%
    }

    // More flexible constructor allowing custom interest rates
    public SavingsAccount(int userId, double initialDeposit, double interestRate) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        if (interestRate <= 0) {
            throw new IllegalArgumentException("Interest rate must be positive");
        }
        
        this.accountNumber = nextAccountNumber++;
        this.userId = userId;
        this.balance = initialDeposit;
        this.interestRate = interestRate;
    }

    public void printAccountDetails() {  
        System.out.printf("""
            Account Details:
            Account #: %d
            Balance: $%.2f
            Interest Rate: %.2f%%
            User ID: %d
            """, accountNumber, balance, interestRate * 100, userId);
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

    public void applyInterest() {  
        double interest = balance * interestRate;
        balance += interest;
        System.out.printf("Interest applied: $%.2f. New Balance: $%.2f%n", interest, balance);
    }

  
    private void validatePositiveAmount(double amount, String operation) {
        if (amount <= 0) {
            throw new IllegalArgumentException(operation + " amount must be positive");
        }
    }


    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public int getUserId() { return userId; }
    public double getInterestRate() { return interestRate; }

   
}