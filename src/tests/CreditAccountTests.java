package tests;

import bankapp.CreditAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTests {
    private CreditAccount creditAccount;

    @BeforeEach
    public void setUp() {
        // 1. Create a mock Customer
        mockCustomer = mock(Customer.class);
        when(mockCustomer.getUsername()).thenReturn("testUser");
        
        // 2. Create a mock CreditStatement
        mockCreditStatement = mock(CreditStatement.class);
        
        // 3. Initialize CreditAccount with dependencies
        creditAccount = new CreditAccount("testUser", mockCreditStatement);
    }

    @Test
    public void testInitialCreditBalance() {
        assertEquals(0.0, creditAccount.getCreditBalance(), "Initial credit balance should be 0");
    }

    @Test
    public void testDefaultCreditLimit() {
        assertEquals(5000, creditAccount.getCreditLimit(), "Default credit limit should be 5000");
    }

    @Test
    public void testBorrowCredit() {
        creditAccount.borrowCredit(500);
        assertEquals(500.0, creditAccount.getCreditBalance(), "Credit balance should be updated to 500");

        assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(-100),
            "Borrow amount cannot be negative");
    }

    @Test
    public void testBorrowCreditExceedsLimit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(6000));
        assertEquals("Cannot borrow more than the credit limit", exception.getMessage());
    }

    @Test
    public void testRepayCredit() {
        creditAccount.borrowCredit(500);
        creditAccount.repayCredit(200);
        assertEquals(300.0, creditAccount.getCreditBalance(), "Credit balance should be reduced to 300");

        assertThrows(IllegalArgumentException.class, () -> creditAccount.repayCredit(400),
            "Cannot repay more than the current credit balance");

        assertThrows(IllegalArgumentException.class, () -> creditAccount.repayCredit(-50),
            "Repayment amount cannot be negative");
    }

    @Test
    public void testRepayCreditTooMuch() {
        creditAccount.borrowCredit(500);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.repayCredit(600));
        assertEquals("Cannot repay more than the current credit balance", exception.getMessage());
    }

    @Test
    public void testSetCreditLimit() {
        creditAccount.setCreditLimit(10000);
        assertEquals(10000, creditAccount.getCreditLimit(), "Credit limit should be set to 10000");
    }

    @Test
    public void testSetInvalidCreditLimit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.setCreditLimit(-500));
        assertEquals("Credit limit cannot be negative", exception.getMessage());
    }

    @Test
    public void testEligibilityForCredit() {
        creditAccount.borrowCredit(2000);
        assertTrue(creditAccount.isEligibleForCredit(2000), "Should be eligible to borrow 2000 within the credit limit");
    }

    @Test
    public void testEligibilityForCreditExceedsLimit() {
        creditAccount.borrowCredit(2000);
        assertFalse(creditAccount.isEligibleForCredit(4000), "Should not be eligible to borrow more than the available credit limit");
    }

    @Test
    public void testBorrowCreditWhenLimitExceeded() {
        creditAccount.borrowCredit(5000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(1000));
        assertEquals("Cannot borrow more than the credit limit", exception.getMessage());
    }

    @Test
    public void testCashBackAppliedAfterBorrowing() {
        creditAccount.borrowCredit(1000);
        assertTrue(creditAccount.getCreditBalance() < 1000, "Cashback should be applied after borrowing.");
    }

    @Test
    public void testCashBackAmountCorrectness() {
        creditAccount.borrowCredit(1000);
        double expectedBalanceAfterCashBack = 1000 - 10; // Assuming 1% cashback
        assertEquals(expectedBalanceAfterCashBack, creditAccount.getCreditBalance(), "Credit balance after cashback should be correct.");
    }

    @Test
    public void testCashBackDoesNotGoNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(-100));
        assertEquals("Borrow amount cannot be negative", exception.getMessage());
    }

    @Test
    public void testCashBackWhenNoBorrowing() {
        assertEquals(0.0, creditAccount.getCreditBalance(), "Credit balance should be zero if no credit is borrowed.");
    }

    @Test
    public void testCreditScoreIncreases() {
        creditAccount.borrowCredit(500);
        int creditScoreBefore = creditAccount.getCreditScore();
        creditAccount.repayCredit(100);
        int creditScoreAfter = creditAccount.getCreditScore();
        int creditScoreDiff = creditScoreAfter - creditScoreBefore;
        assertEquals(1, creditScoreDiff, "Credit score should increase by 1 after repayment");
    }

    @Test
    public void testMaxCreditScoreConstant() {
        assertEquals(850, CreditAccount.MAX_CREDIT_SCORE, "MAX_CREDIT_SCORE should be 850");
    }
}