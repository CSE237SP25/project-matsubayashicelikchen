package tests;

import bankapp.BankAccount;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTests {

    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount();
    }

    @Test
    public void testDepositIncreasesBalance() {
        account.deposit(100.0);
        assertEquals(100.0, account.getCurrentBalance(), "Deposit should increase balance by 100");
    }

    @Test
    public void testDepositNegativeAmountThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50.0);
        });
        assertEquals("Deposit amount cannot be negative", exception.getMessage());
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        account.deposit(200.0);
        boolean result = account.withdraw(50.0);
        assertTrue(result, "Withdrawal of valid amount should return true");
        assertEquals(150.0, account.getCurrentBalance(), "Balance should decrease by 50");
    }

    @Test
    public void testWithdrawMoreThanBalanceThrowsException() {
        account.deposit(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150.0);
        });
        assertEquals("Attempting to withdraw sum greater than balance", exception.getMessage());
    }

    @Test
    public void testWithdrawNonPositiveAmountThrowsException() {
        account.deposit(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0);
        });
        assertEquals("Must withdraw a positive sum of money", exception.getMessage());
    }
}
