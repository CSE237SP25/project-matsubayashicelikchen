package tests;

import bankapp.CreditAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTests {
    
    private CreditAccount creditAccount;

    @BeforeEach
    public void setUp() {
        // Initialize CreditAccount before each test
        creditAccount = new CreditAccount();
    }

    @Test
    public void testInitialCreditBalance() {
        // Test that the initial credit balance is 0
        assertEquals(0.0, creditAccount.getCreditBalance(), "Initial credit balance should be 0");
    }

    @Test
    public void testBorrowCredit() {
        // Test borrowing a positive amount
        creditAccount.borrowCredit(500);
        assertEquals(500.0, creditAccount.getCreditBalance(), "Credit balance should be updated to 500");

        // Test borrowing a negative amount should throw exception
        assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(-100), 
            "Borrow amount cannot be negative");
    }

    @Test
    public void testRepayCredit() {
        // Set an initial credit balance
        creditAccount.borrowCredit(500);

        // Repay a valid amount
        creditAccount.repayCredit(200);
        assertEquals(300.0, creditAccount.getCreditBalance(), "Credit balance should be reduced to 300");

        // Repay more than the available balance should throw exception
        assertThrows(IllegalArgumentException.class, () -> creditAccount.repayCredit(400), 
            "Cannot repay more than the current credit balance");

        // Test repayment with a negative amount should throw exception
        assertThrows(IllegalArgumentException.class, () -> creditAccount.repayCredit(-50),
            "Repayment amount cannot be negative");
    }

    @Test
    public void testCreditScoreIncreases() {
        creditAccount.borrowCredit(500);
        int creditScoreBefore = creditAccount.getCreditScore();
        creditAccount.repayCredit(100); // credit score should increase by 1
        int creditScoreAfter = creditAccount.getCreditScore();
        int creditScoreDiff = creditScoreAfter - creditScoreBefore; 
        assertEquals(1, creditScoreDiff, "Credit score should increase by 1 after repayment");
    }

    @Test
    public void testMaxCreditScoreConstant() {
        // Check if MAX_CREDIT_SCORE constant is correctly defined
        assertEquals(850, CreditAccount.MAX_CREDIT_SCORE, "MAX_CREDIT_SCORE should be 850");
    }
}

