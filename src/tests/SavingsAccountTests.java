package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import bankapp.SavingsAccount;

public class SavingsAccountTests {

    @Test
    public void testOpenSavingsAccount() {
        // Test default constructor (2% interest)
        SavingsAccount account = new SavingsAccount(101, 500.0);
        assertEquals(500.0, account.getBalance(), 0.005);
        assertEquals(0.02, account.getInterestRate(), 0.001);
    }

    @Test
    public void testOpenSavingsAccountWithCustomInterest() {
        // Test custom interest rate (5%)
        SavingsAccount account = new SavingsAccount(101, 1000.0, 0.05);
        assertEquals(1000.0, account.getBalance(), 0.005);
        assertEquals(0.05, account.getInterestRate(), 0.001);
    }

    @Test
    public void testNegativeInitialDeposit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingsAccount(101, -100.0);
        });
    }

    @Test
    public void testInvalidInterestRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingsAccount(101, 100.0, 0.0); // 0% interest not allowed
        });
    }

    @Test
    public void testDeposit() {
        SavingsAccount account = new SavingsAccount(101, 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.005);
    }

    @Test
    public void testNegativeDeposit() {
        SavingsAccount account = new SavingsAccount(101, 100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-20.0);
        });
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }

    @Test
    public void testWithdrawValid() {
        SavingsAccount account = new SavingsAccount(101, 200.0);
        assertTrue(account.withdraw(50.0));
        assertEquals(150.0, account.getBalance(), 0.005);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        SavingsAccount account = new SavingsAccount(101, 50.0);
        assertFalse(account.withdraw(100.0));
        assertEquals(50.0, account.getBalance(), 0.005);
    }

    @Test
    public void testApplyInterest() {  // Renamed from testCalculateInterest
        SavingsAccount account = new SavingsAccount(101, 1000.0);
        account.applyInterest();  // Changed from calculateInterest()
        assertEquals(1020.0, account.getBalance(), 0.005); // 2% of 1000 = 20
    }

    @Test
    public void testNegativeWithdrawal() {
        SavingsAccount account = new SavingsAccount(101, 200.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        });
        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }

 
}