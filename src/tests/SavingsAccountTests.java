package tests;

import org.junit.jupiter.api.Test;

import bankapp.SavingsAccount;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {
    private SavingsAccount account;
    
    @BeforeEach
    void setUp() {
        account = new SavingsAccount(100.00); // Initial balance for most tests
    }
    
    @Test
    void testInitialDeposit() {
        assertEquals(100.00, account.getBalance(), 0.001);
    }
    
    @Test
    void testNegativeInitialDepositThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingsAccount(-50.00);
        });
    }
    
    @Test
    void testDepositPositiveAmount() {
        account.deposit(50.00);
        assertEquals(150.00, account.getBalance(), 0.001);
    }
    
    @Test
    void testDepositNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10.00);
        });
    }
    
    @Test
    void testDepositZeroAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0.00);
        });
    }
    
    @Test
    void testSuccessfulWithdrawal() {
        assertTrue(account.withdraw(50.00));
        assertEquals(50.00, account.getBalance(), 0.001);
    }
    
    @Test
    void testFailedWithdrawal() {
        assertFalse(account.withdraw(150.00));
        assertEquals(100.00, account.getBalance(), 0.001);
    }
    
    @Test
    void testWithdrawalNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-10.00);
        });
    }
    
    @Test
    void testWithdrawalZeroAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0.00);
        });
    }
    
    @Test
    void testGetBalance() {
        assertEquals(100.00, account.getBalance(), 0.001);
    }
    
    @Test
    void testPrintAccountDetails() {
        // This just verifies no exception is thrown
        assertDoesNotThrow(() -> account.printAccountDetails());
    }
    
    @Test
    void testPrecisionHandling() {
        account.deposit(0.001);
        assertEquals(100.001, account.getBalance(), 0.0001);
    }
}