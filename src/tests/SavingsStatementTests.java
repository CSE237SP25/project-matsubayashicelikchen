package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import bankapp.Customer;
import bankapp.SavingsAccount;
import bankapp.SavingsStatement;

class SavingsStatementTests {
    private SavingsAccount savingsAccount;
    private SavingsStatement savingsStatement;

    @BeforeEach
    void setUp() {
        savingsAccount = new SavingsAccount(1000.0);
        savingsStatement = new SavingsStatement(savingsAccount);
    }

    @Test
    void testConstructorWithNullAccount() {
        assertThrows(IllegalArgumentException.class, () -> new SavingsStatement(null));
    }

    @Test
    void testGenerateStatementWithNoTransactions() {
     
        assertEquals(1000.0, savingsAccount.getBalance(), 0.001);
        List<String> transactions = savingsAccount.getTransactionHistory();
        assertTrue(transactions.isEmpty());
        
       
        assertDoesNotThrow(() -> savingsStatement.generateStatement());
    }

    @Test
    void testGenerateStatementWithTransactions() {
 
        savingsAccount.deposit(500.0);
        savingsAccount.withdraw(200.0);
        
  
        List<String> transactions = savingsAccount.getTransactionHistory();
        assertEquals(2, transactions.size());
        
       
        assertEquals(1300.0, savingsAccount.getBalance(), 0.001);
        
     
        assertDoesNotThrow(() -> savingsStatement.generateStatement());
    }

    @Test
    void testTransactionOrder() {
    
        savingsAccount.deposit(500.0);
        savingsAccount.withdraw(200.0);
        savingsAccount.deposit(300.0);
        
      
        List<String> transactions = savingsAccount.getTransactionHistory();
        assertEquals(3, transactions.size());
        
      
        assertTrue(transactions.get(0).contains("DEPOSIT"));
        assertTrue(transactions.get(0).contains("500.00"));
        
     
        assertTrue(transactions.get(1).contains("WITHDRAWAL"));
        assertTrue(transactions.get(1).contains("200.00"));
        
      
        assertTrue(transactions.get(2).contains("DEPOSIT"));
        assertTrue(transactions.get(2).contains("300.00"));
    }

    @Test
    void testTransactionFormat() {
        savingsAccount.deposit(500.0);
        List<String> transactions = savingsAccount.getTransactionHistory();
        
 
        String transaction = transactions.get(0);
        String[] parts = transaction.split("\\|");
        assertEquals(4, parts.length); 
        
       
        assertTrue(parts[0].trim().matches("\\d{4}-\\d{2}-\\d{2}"));
        
     
        assertEquals("DEPOSIT", parts[1].trim());
        
   
        assertTrue(parts[2].trim().matches("\\d+\\.\\d{2}"));
        
       
        assertEquals("CREDIT", parts[3].trim());
    }
}