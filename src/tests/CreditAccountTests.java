package tests;

import bankapp.CreditAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTests {

    private CreditAccount creditAccount;

    @BeforeEach
    void setUp() {
        creditAccount = new CreditAccount();
    }

    @Test
    void testCashBackAppliedAfterBorrowing() {
        // Borrowing 1000 should apply cashback of 1000 * 0.01 = 10
        creditAccount.borrowCredit(1000);
        assertTrue(creditAccount.getCreditBalance() < 1000, "Cashback should be applied after borrowing.");
    }

    @Test
    void testCashBackAmountCorrectness() {
        // Borrowing 1000 should apply cashback of 1000 * 0.01 = 10
        creditAccount.borrowCredit(1000);
        double expectedBalanceAfterCashBack = 1000 - 10; // 1000 borrowed, 10 cashback
        assertEquals(expectedBalanceAfterCashBack, creditAccount.getCreditBalance(), "Credit balance after cashback should be correct.");
    }



    @Test
    void testCashBackDoesNotGoNegative() {
        // If a negative amount is borrowed, cashback should not be applied
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            creditAccount.borrowCredit(-100);  // Negative borrowing should throw an exception
        });
        assertEquals("Borrow amount cannot be negative", exception.getMessage());
    }

  
    @Test
    void testCashBackWhenNoBorrowing() {
        // Before any borrowing, cashback should be zero
        assertEquals(0, creditAccount.getCreditBalance(), "Credit balance should be zero if no credit is borrowed.");
    }
}


