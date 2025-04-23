package bankapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount {
	private double balance;
    private List<String> transactionHistory;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor to initialize a SavingsAccount with an initial deposit.
     * @param initialDeposit The initial deposit amount.
     * @throws IllegalArgumentException if the initial deposit is negative.
     */
    public SavingsAccount(double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Prints the account details including the balance.
     * The balance is formatted to two decimal places.
     */
    public void printAccountDetails() {  
        System.out.printf("""
            Account Details:
            Balance: $%.2f
            """, balance);
    }

    /**
     * Deposits an amount into the account.
     * Tracks the deposit in the transaction history.
     * @param amount The amount to deposit.
     * @throws IllegalArgumentException if the deposit amount is negative or zero.
     */
    public void deposit(double amount) {
        validatePositiveAmount(amount, "Deposit");
        balance += amount;
        String transaction = String.format("%s | DEPOSIT    | %10.2f | CREDIT", 
            LocalDate.now().format(DATE_FORMATTER), amount);
        transactionHistory.add(transaction);
        System.out.printf("Deposited $%.2f. New Balance: $%.2f%n", amount, balance);
    }

    /**
     * Withdraws an amount from the account.
     * Tracks the withdrawal in the transaction history.
     * @param amount The amount to withdraw.
     * @return true if the withdrawal was successful, false otherwise.
     * @throws IllegalArgumentException if the withdrawal amount is negative or zero.
     */
    public boolean withdraw(double amount) {
        validatePositiveAmount(amount, "Withdrawal");
        
        if (amount > balance) {
            System.out.printf("Withdrawal failed. Requested: $%.2f, Available: $%.2f%n", 
                            amount, balance);
            return false;
        }
        
        balance -= amount;
        String transaction = String.format("%s | WITHDRAWAL | %10.2f | DEBIT", 
            LocalDate.now().format(DATE_FORMATTER), amount);
        transactionHistory.add(transaction);
        System.out.printf("Withdrew $%.2f. New Balance: $%.2f%n", amount, balance);
        return true;
    }
    
    
    /**
     * Gets the transaction history of the account.
     * Each transaction is formatted with the date, type, and amount.
     * @return a list of all transactions in the account.
     */
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory); // Return a copy for immutability
    }

    /**
    * Validates that the amount is positive.
    * @param amount The amount to validate.
    * @param operation The operation being performed (e.g., "Deposit", "Withdrawal").
    * @throws IllegalArgumentException if the amount is not positive.
    */  
    private void validatePositiveAmount(double amount, String operation) {
        if (amount <= 0) {
            throw new IllegalArgumentException(operation + " amount must be positive");
        }
    }

    //Getter for balance
    public double getBalance() { return balance; }
}