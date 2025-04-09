package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.CreditAccount;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTests {

    private CreditAccount creditAccount;

    @BeforeEach
    public void setUp() {
        // Initialize CreditAccount before each test
        creditAccount = new CreditAccount();
    }

    // Test for default credit balance
    @Test
    public void testDefaultCreditBalance() {
        assertEquals(0, creditAccount.getCreditBalance(), "Initial credit balance should be 0");
    }

    // Test for borrowing credit
    @Test
    public void testBorrowCredit() {
        creditAccount.borrowCredit(500);
        assertEquals(500, creditAccount.getCreditBalance(), "Credit balance should be 500 after borrowing 500");
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

    // Test for setting the credit score
    @Test
    public void testUpdateCreditScore() {
        creditAccount.updateCreditScore(750);
        assertEquals(750, creditAccount.getCreditScore(), "Credit score should be 750 after updating");
    }

    // Test for setting an invalid credit score (less than 300)
    @Test
    public void testUpdateCreditScoreInvalidLow() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.updateCreditScore(250));
        assertEquals("Credit score must be between 300 and 850", exception.getMessage());
    }

    // Test for setting an invalid credit score (greater than 850)
    @Test
    public void testUpdateCreditScoreInvalidHigh() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.updateCreditScore(900));
        assertEquals("Credit score must be between 300 and 850", exception.getMessage());
    }

    // Test for eligibility to borrow with a good credit score
    @Test
    public void testEligibilityForCreditGoodScore() {
        creditAccount.updateCreditScore(700);
        assertTrue(creditAccount.isEligibleForCredit(700), "Should be eligible to borrow with a credit score of 700");
    }

    // Test for eligibility to borrow with a low credit score
    @Test
    public void testEligibilityForCreditLowScore() {
        creditAccount.updateCreditScore(600);
        assertFalse(creditAccount.isEligibleForCredit(600), "Should be ineligible to borrow with a credit score of 600");
    }

    // Test for eligibility to borrow with a poor credit score
    @Test
    public void testEligibilityForCreditPoorScore() {
        creditAccount.updateCreditScore(600);
        assertFalse(creditAccount.isEligibleForCredit(10000), "Should not be eligible to borrow a large amount with a low credit score");
    }

    // Test for borrowing credit when score is too low
    @Test
    public void testBorrowCreditLowScore() {
        creditAccount.updateCreditScore(500);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> creditAccount.borrowCredit(1000));
        assertEquals("Credit score too low for borrowing this amount", exception.getMessage());
    }
}
