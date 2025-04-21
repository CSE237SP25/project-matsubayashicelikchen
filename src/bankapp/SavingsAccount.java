package bankapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount {
	private double balance;
    private List<String> transactionHistory;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  

    public SavingsAccount(double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
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
        String transaction = String.format("%s | DEPOSIT    | %10.2f | CREDIT", 
            LocalDate.now().format(DATE_FORMATTER), amount);
        transactionHistory.add(transaction);
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
        String transaction = String.format("%s | WITHDRAWAL | %10.2f | DEBIT", 
            LocalDate.now().format(DATE_FORMATTER), amount);
        transactionHistory.add(transaction);
        System.out.printf("Withdrew $%.2f. New Balance: $%.2f%n", amount, balance);
        return true;
    }
    
    
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory); // Return a copy for immutability
    }

    
  
    private void validatePositiveAmount(double amount, String operation) {
        if (amount <= 0) {
            throw new IllegalArgumentException(operation + " amount must be positive");
        }
    }


    
    public double getBalance() { return balance; }

   
}