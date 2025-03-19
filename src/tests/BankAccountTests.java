package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

public class BankAccountTests {

	@Test
	public void testSimpleDeposit() {
		//1. Create objects to be tested
		BankAccount account = new BankAccount(0);
		
		//2. Call the method being tested
		account.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(account.getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		BankAccount account = new BankAccount(0);

		try {
			account.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	public void testConstructorNegativeCredit() {
        try{
            new BankAccount(-100);
            fail();
        } catch (IllegalArgumentException e){
            assertTrue(e != null);
        }
    }

    // Test that the constructor correctly sets the credit balance 
    // and that getCreditBalance give the correct value
    @Test
    public void testGetCreditBalance() {
        BankAccount account = new BankAccount(500);
        assertEquals(500, account.getCreditBalance(), 0.001);
    }

    // Test that an exception is thrown when attempting to change the credit balance to a negative value
    @Test
    public void testChangeCreditToNegative() {
        BankAccount account = new BankAccount(500);
        try{
            account.changeCredit(-200);
        } catch (IllegalArgumentException e){
            assertTrue(e != null);
        }
   
    }

    // Test that after a valid change to the credit balance
    @Test
    public void testChangeCreditValid() {
        BankAccount account = new BankAccount(500);
        account.changeCredit(300);
        assertEquals(300, account.getCreditBalance(), 0.001);
    }
}
