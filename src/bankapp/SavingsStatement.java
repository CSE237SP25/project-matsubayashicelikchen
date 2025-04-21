package bankapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SavingsStatement {
    private final SavingsAccount account;
    private final LocalDate statementDate;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SavingsStatement(SavingsAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        this.account = account;
        this.statementDate = LocalDate.now();
    }

    public void generateStatement() {
        System.out.println("\n=== SAVINGS ACCOUNT STATEMENT ===");
        System.out.println("Statement Date: " + statementDate.format(DATE_FORMATTER));
        System.out.println("Account Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("\nTransaction History:");
        System.out.println("-----------------------------------------------");
        System.out.println("Date       | Description | Amount     | Type");
        System.out.println("-----------------------------------------------");
        
        List<String> transactions = account.getTransactionHistory();
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded");
        } else {
            transactions.forEach(System.out::println);
        }
        
        System.out.println("-----------------------------------------------");
        System.out.println("End of Statement\n");
    }
}
