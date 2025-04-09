ppackage tests;

import bankapp.CreditAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTests {

    private CreditAccount creditAccount;

    @BeforeEach
    public void setUp() {
        creditAccount = new CreditAccount();
    }

    // Test for default credit balance
    @Test
    public void testDefaultCreditBalance() {
        assertEquals(0, creditAccount.getCreditBalance(), "Initial credit balance should be 0");
    }

    // Test for default credit limit
    @Test
    public void testDefaultCreditLimit() {
        assertEquals(5000, creditAccount.getCreditLimit(), "Default credit limit should be 5000");
    }

    // Test for borrowing credit
    @Test
    public void testBorrowCredit() {
        creditAccount.borrowCredit(500);
        assertEquals(500, creditAccount.getCreditBalance(), "Credit balance should be 500 after borrowing 500");
    }

    // Test for borrowing credit that exceeds the credit limit
    @Test
    public void testBorrowCreditExceedsLimit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(6000));
        assertEquals("Cannot borrow more than the credit limit", exception.getMessage());
    }

    // Test for repayment of credit
    @Test
    public void testRepayCredit() {
        creditAccount.borrowCredit(500);
        creditAccount.repayCredit(200);
        assertEquals(300, creditAccount.getCreditBalance(), "Credit balance should be 300 after repayment of 200");
    }

    // Test for illegal repayment amount (more than balance)
    @Test
    public void testRepayCreditTooMuch() {
        creditAccount.borrowCredit(500);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.repayCredit(600));
        assertEquals("Cannot repay more than the current credit balance", exception.getMessage());
    }

    // Test for setting the credit limit
    @Test
    public void testSetCreditLimit() {
        creditAccount.setCreditLimit(10000);
        assertEquals(10000, creditAccount.getCreditLimit(), "Credit limit should be set to 10000");
    }

    // Test for setting an invalid credit limit (negative value)
    @Test
    public void testSetInvalidCreditLimit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.setCreditLimit(-500));
        assertEquals("Credit limit cannot be negative", exception.getMessage());
    }

    // Test for eligibility to borrow with available credit limit
    @Test
    public void testEligibilityForCredit() {
        creditAccount.borrowCredit(2000);
        assertTrue(creditAccount.isEligibleForCredit(2000), "Should be eligible to borrow 2000 within the credit limit");
    }

    // Test for ineligibility to borrow when exceeding the credit limit
    @Test
    public void testEligibilityForCreditExceedsLimit() {
        creditAccount.borrowCredit(2000);
        assertFalse(creditAccount.isEligibleForCredit(4000), "Should not be eligible to borrow more than the available credit limit");
    }

    // Test for borrowing credit when credit limit is exceeded
    @Test
    public void testBorrowCreditWhenLimitExceeded() {
        creditAccount.borrowCredit(5000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(1000));
        assertEquals("Cannot borrow more than the credit limit", exception.getMessage());
    }

    // Test: Cashback applied after borrowing
    @Test
    public void testCashBackAppliedAfterBorrowing() {
        creditAccount.borrowCredit(1000);
        assertTrue(creditAccount.getCreditBalance() < 1000, "Cashback should be applied after borrowing.");
    }

    // Test: Correct cashback amount
    @Test
    public void testCashBackAmountCorrectness() {
        creditAccount.borrowCredit(1000);
        double expectedBalanceAfterCashBack = 1000 - 10; // Assuming 1% cashback
        assertEquals(expectedBalanceAfterCashBack, creditAccount.getCreditBalance(), "Credit balance after cashback should be correct.");
    }

    // Test: Negative borrow amount throws error
    @Test
    public void testCashBackDoesNotGoNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(-100));
        assertEquals("Borrow amount cannot be negative", exception.getMessage());
    }

    // Test: Cashback before any borrowing
    @Test
    public void testCashBackWhenNoBorrowing() {
        assertEquals(0, creditAccount.getCreditBalance(), "Credit balance should be zero if no credit is borrowed.");
    }
}