package bankapp;

import java.io.*;
import java.util.*;

public class CreditStatement {
    private final String pathToData = "data/user/";
    private final String filename = "credit_statement.txt";
    private final CreditAccount creditAccount;
    
    public CreditStatement(Customer customer) {
        this.creditAccount = customer.getCreditAccount();
    }

    // Log a credit transaction (borrow/repay) with description
    public void logTransaction(String type, double amount, String note) {
        String username = creditAccount.getUsername();
        File userDir = new File(pathToData + username);
        File statementFile = new File(userDir, filename);
        
        String transaction = String.format("%s|%.2f|%s|%s|%.2f|%.2f|%d",
                type,
                amount,
                note,
                new Date(),
                creditAccount.getCreditBalance(),
                creditAccount.getAvailableCredit(),
                creditAccount.getCreditScore());
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(statementFile, true))) {
            bw.write(transaction);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Failed to log transaction: " + e.getMessage());
        }
    }

    // Generate formatted statement
    public String generateStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("=== CREDIT ACCOUNT STATEMENT ===\n");
        statement.append(String.format("Credit Limit: $%.2f\n", creditAccount.getCreditLimit()));
        statement.append(String.format("Available Credit: $%.2f\n", creditAccount.getAvailableCredit()));
        statement.append(String.format("Credit Score: %d\n\n", creditAccount.getCreditScore()));
        
        String username = creditAccount.getUsername();
        File userDir = new File(pathToData + username);
        File statementFile = new File(userDir, filename);
        
        if (statementFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(statementFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    statement.append(String.format("[%s] %s: $%.2f\n",
                    	    parts[3],  // Date
                    	    parts[0],  // Type
                    	    Double.parseDouble(parts[1])));
                }
            } catch (IOException e) {
                statement.append("Error loading transaction history.\n");
            }
        } else {
            statement.append("No transactions found.\n");
        }
        return statement.toString();
    }
}