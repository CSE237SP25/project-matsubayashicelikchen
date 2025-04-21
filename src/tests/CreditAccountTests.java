package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import bankapp.Customer;
import bankapp.CreditStatement;
import bankapp.CreditAccount;

class CreditAccountTest {
    private CreditAccount creditAccount;
    private Customer customer;
    private TestCreditStatement testStatement;

    // Test stub that tracks transactions
    static class TestCreditStatement extends CreditStatement {
        String lastTransactionType;
        double lastTransactionAmount;
        String lastTransactionNote;
        
        public TestCreditStatement(Customer customer) {
            super(customer);
        }
        
        @Override
        public void logTransaction(String type, double amount, String note) {
            this.lastTransactionType = type;
            this.lastTransactionAmount = amount;
            this.lastTransactionNote = note;
        }
    }

    @BeforeEach
    void setUp() {
        // Create a real customer with minimal required fields
        customer = new Customer("testUser", "password", "Test", "User", "test@user.com");
        
        // Initialize the credit account through the proper channel
        customer.openCreditAccount();
        creditAccount = customer.getCreditAccount();
        
        // Replace the real statement with our test version
        testStatement = new TestCreditStatement(customer);
        creditAccount.setCreditStatement(testStatement);
    }

    @Test
    void testInitialState() {
        assertEquals("testUser", creditAccount.getUsername());
        assertEquals(0, creditAccount.getCreditBalance());
        assertEquals(5000, creditAccount.getCreditLimit());
        assertEquals(5000, creditAccount.getAvailableCredit());
        assertEquals(700, creditAccount.getCreditScore());
        assertNotNull(creditAccount.getCreditStatement());
    }

    @Test
    void testValidBorrow() {
        creditAccount.borrowCredit(1000);
        assertEquals(1000, creditAccount.getCreditBalance());
        assertEquals(4000, creditAccount.getAvailableCredit());
        assertEquals("BORROW", testStatement.lastTransactionType);
        assertEquals(1000, testStatement.lastTransactionAmount);
    }

    @Test
    void testInvalidBorrow() {
        // Test negative amount
        creditAccount.borrowCredit(-100);
        assertEquals(0, creditAccount.getCreditBalance());
        assertNull(testStatement.lastTransactionType);
        
        // Test exceeding limit
        creditAccount.borrowCredit(6000);
        assertEquals(0, creditAccount.getCreditBalance());
        assertNull(testStatement.lastTransactionType);
    }

    @Test
    void testValidRepay() {
        creditAccount.borrowCredit(2000);
        testStatement.lastTransactionType = null; // Reset
        
        creditAccount.repayCredit(500);
        assertEquals(1500, creditAccount.getCreditBalance());
        assertEquals(3500, creditAccount.getAvailableCredit());
        assertEquals(701, creditAccount.getCreditScore());
        assertEquals("REPAY", testStatement.lastTransactionType);
        assertEquals(500, testStatement.lastTransactionAmount);
    }

    @Test
    void testInvalidRepay() {
        // Test negative amount
        creditAccount.repayCredit(-100);
        assertEquals(0, creditAccount.getCreditBalance());
        assertNull(testStatement.lastTransactionType);
        
        // Test over-repayment
        creditAccount.borrowCredit(1000);
        testStatement.lastTransactionType = null;
        creditAccount.repayCredit(1500);
        assertEquals(1000, creditAccount.getCreditBalance());
        assertNull(testStatement.lastTransactionType);
    }

    
}