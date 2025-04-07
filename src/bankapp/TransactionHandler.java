package bankapp;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHandler {
    private static final String TRANSACTIONS_FILE = "transactions.csv";
    private static final String ACCOUNTS_FILE = "accounts.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Performs a transfer between two accounts and records the transaction
     * @param fromAccount Account to transfer from
     * @param toAccount Account to transfer to
     * @param amount Amount to transfer
     * @param description Description of the transaction
     * @return true if transfer was successful, false otherwise
     */
    public static boolean transfer(bankapp.BankAccount fromAccount, bankapp.BankAccount toAccount,
                                   double amount, String description) {
        try {
            // Validate the transfer
            if (amount <= 0) {
                throw new IllegalArgumentException("Transfer amount must be positive");
            }
            if (fromAccount.getCurrentBalance() < amount) {
                throw new IllegalArgumentException("Insufficient funds for transfer");
            }

            // Perform the transfer
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            // Record the transaction
            recordTransaction(fromAccount, toAccount, amount, description);

            // Update account balances in persistent storage
            updateAccountBalances(fromAccount, toAccount);

            return true;
        } catch (Exception e) {
            System.err.println("Transfer failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Records a transaction in the transactions CSV file
     * Format: timestamp,fromAccountId,toAccountId,amount,description
     */
    private static void recordTransaction(bankapp.BankAccount fromAccount, bankapp.BankAccount toAccount,
                                          double amount, String description) {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
            // Note: In a real system, we'd use actual account IDs here
            String line = String.format("%s,%s,%s,%.2f,%s%n",
                    timestamp,
                    "FROM_ACCOUNT_ID", // Replace with actual account ID if available
                    "TO_ACCOUNT_ID",   // Replace with actual account ID if available
                    amount,
                    description);
            writer.write(line);
        } catch (IOException e) {
            System.err.println("Error recording transaction: " + e.getMessage());
        }
    }

    /**
     * Updates account balances in persistent storage
     */
    private static void updateAccountBalances(bankapp.BankAccount... accounts) {
        try (FileWriter writer = new FileWriter(ACCOUNTS_FILE)) {
            for (bankapp.BankAccount account : accounts) {
                // Note: In a real system, we'd use actual account IDs here
                String line = String.format("%s,%.2f%n",
                        "ACCOUNT_ID", // Replace with actual account ID if available
                        account.getCurrentBalance());
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error updating account balances: " + e.getMessage());
        }
    }

    /**
     * Gets the current balance from persistent storage
     * (This would be called when initializing an account)
     */
    public static double getSavedBalance(String accountId) {
        // Implementation would read from ACCOUNTS_FILE
        // and return the balance for the specified account
        // For now, returning 0 as a placeholder
        return 0.0;
    }
}
