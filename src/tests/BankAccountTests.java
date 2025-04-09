package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import bankapp.BankAccount;

public class BankAccountTests {

    @Test
    public void testSimpleDeposit() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        assertEquals(25.0, account.getCurrentBalance(), 0.005);
    }

    @Test
    public void testNegativeDeposit() {
        BankAccount account = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-25));
    }

    @Test
    public void testSimpleWithdrawal() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        account.withdraw(5);
        assertEquals(20.0, account.getCurrentBalance(), 0.005);
    }

    @Test
    public void testNegativeWithdrawal() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5.25));
    }

    @Test
    public void testOverdrawingWithdrawal() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(500));
    }
}
