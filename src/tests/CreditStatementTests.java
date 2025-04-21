package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import bankapp.Customer;
import bankapp.CreditStatement;
import bankapp.CreditAccount;

class CreditStatementTests {
    private Customer customer;
    private CreditAccount creditAccount;
    private CreditStatement creditStatement;

    @BeforeEach
    void setUp() {
        customer = new Customer("testUser", "pass", "Test", "User", "test@user.com");
        customer.openCreditAccount();
        creditAccount = customer.getCreditAccount();
        creditStatement = new CreditStatement(customer);
    }

    @Test
    void testStatementGeneration() {
        String statement = creditStatement.generateStatement();
        assertNotNull(statement);
        assertTrue(statement.contains("CREDIT ACCOUNT STATEMENT"));
    }

    @Test
    void testTransactionLogging() {
        assertDoesNotThrow(() -> {
            creditStatement.logTransaction("TEST", 100.0, "Sample");
        });
    }

    @Test
    void testEmptyStatement() {
        String statement = creditStatement.generateStatement();
        assertTrue(statement.contains("No transactions") || 
                  statement.contains("CREDIT ACCOUNT STATEMENT"));
    }

    @Test
    void testWithCreditAccount() {
        assertNotNull(creditAccount);
        assertEquals("testUser", creditAccount.getUsername());
    }
}