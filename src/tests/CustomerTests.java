package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.Customer;


import static org.junit.jupiter.api.Assertions.*;


public class CustomerTests {
	
	@BeforeEach
	public void resetCustomerID() {
		Customer.setNextCustomerID(1);
	}
	
	@Test
	public void testSimpleDeposit() {
		//1. Create objects to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		//2. Call the method being tested
		customer.getAccount().deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(customer.getAccount().getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		try {
			customer.getAccount().deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	@Test
	public void testCustomerID() {
		//1. Create object to be tested
		Customer customer1 = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		Customer customer2 = new Customer("Sarah", "Lilly", "Elliot", "Sarah@gmail.com",
				"3148267733", "1 Brookings Dr, St. Louis, MO 63130");
		
		Customer customer3 = new Customer("Jolly", "Holly", "Molly", "Jolly@gmail.com",
				"9999999999", "1 Brookings Dr, St. Louis, MO 63130");
		
		int customer1ID = customer1.getCustomerID();
		int customer2ID = customer2.getCustomerID();
		int customer3ID = customer3.getCustomerID();

		assertEquals(1, customer1ID);
		assertEquals(2, customer2ID);
		assertEquals(3, customer3ID);
		
	}
	
	@Test
	public void testModifyFirstName() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		customer.changeFirstName("Bradly");
		
		String actualFirstName = customer.getFirstName();
		String expectedFirstName = "Bradly";
		
		assertEquals(expectedFirstName, actualFirstName);
	}
	
	@Test
	public void testModifyMiddleName() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		customer.changeMiddleName("Bradly");
		
		String actualMiddleName = customer.getMiddleName();
		String expectedMiddleName = "Bradly";
		
		assertEquals(expectedMiddleName, actualMiddleName);
	}
	
	@Test
	public void testModifyLastName() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		customer.changeLastName("Bradly");
		
		String actualLastName = customer.getLastName();
		String expectedLastName = "Bradly";
		
		assertEquals(expectedLastName, actualLastName);
	}
	
	@Test
	public void testModifyEmail() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		customer.changeEmail("Bob@outlook.com");
		
		String actualEmail = customer.getEmail();
		String expectedEmail = "Bob@outlook.com";
		
		assertEquals(expectedEmail, actualEmail);
	}
	
	@Test
	public void testPhoneNumber() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		customer.changePhoneNumber("1112223333");
		
		String actualPhoneNumber = customer.getPhoneNumber();
		String expectedPhoneNumber = "1112223333";
		
		assertEquals(expectedPhoneNumber, actualPhoneNumber);
	}
	
	@Test
	public void testAddress() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		customer.changeAddress("Village East, Forest Park Pkwy, St. Louis, MO 63130");
		
		String actualAddress = customer.getAddress();
		String expectedAddress = "Village East, Forest Park Pkwy, St. Louis, MO 63130";
		
		assertEquals(expectedAddress, actualAddress);
	}
	
	@Test
    public void testOpenCreditAccount() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", "password123");
        customer.openCreditAccount();
        assertNotNull(customer.getCreditAccount(), "Credit account should be opened.");
    }

    @Test
    public void testGetCreditScore() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", "password123");
        customer.openCreditAccount();
        assertEquals(700, customer.getCreditScore(), "The default credit score should be 0.");
    }

    @Test
    public void testUpdateCreditScore() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", "password123");
        customer.openCreditAccount();
        customer.updateCreditScore(750);
        assertEquals(750, customer.getCreditScore(), "The credit score should be updated to 750.");
    }
    
    @Test
    public void testIsEligibleForLoan() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", "password123");
        customer.openCreditAccount();
        customer.updateCreditScore(750);
        assertTrue(customer.isEligibleForLoan(700), "Customer should be eligible for loan with a credit score of 750.");
        assertFalse(customer.isEligibleForLoan(800), "Customer should not be eligible for loan with a credit score of 750.");
    }

    @Test
    public void testOpenSavingsAccount() {
        Customer customer = new Customer("Jane", "Doe", "jane.doe@example.com", "0987654321", "456 Elm St", "password456");
        customer.openSavingsAccount(100.0);
        assertNotNull(customer.getSavingsAccount(), "Savings account should be opened.");
    }

   
    
    @Test
    public void testTransferToSavings() {
        Customer customer = new Customer("Jane", "Doe", "jane.doe@example.com", "0987654321", "456 Elm St", "password456");
        customer.openSavingsAccount(100.0);
        
        customer.getAccount().deposit(200.0);
        customer.transferToSavings(150.0);

        assertEquals(250.0, customer.getSavingsAccount().getBalance(), "Savings account balance should be 150 after transfer.");
    }


    @Test
    public void testTransferFromSavings() {
        Customer customer = new Customer("Jane", "Doe", "jane.doe@example.com", "0987654321", "456 Elm St", "password456");
        customer.openSavingsAccount(100.0);
        customer.getAccount().deposit(200.0);
        customer.transferToSavings(150.0);
        customer.transferFromSavings(50.0);
        assertEquals(100.0, customer.getAccount().getCurrentBalance(), "Account balance should be 100 after transferring from savings.");
    }

    @Test
    public void testAuthenticate() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", "password123");
        assertTrue(customer.authenticate("password123"), "Authentication should pass with correct password.");
        assertFalse(customer.authenticate("wrongpassword"), "Authentication should fail with incorrect password.");
    }

    @Test
    public void testChangeCustomerDetails() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", "password123");
        customer.changeFirstName("Jonathan");
        customer.changeLastName("Smith");
        customer.changeEmail("jonathan.smith@example.com");
        customer.changePhoneNumber("0987654321");
        customer.changeAddress("789 Oak St");

        assertEquals("Jonathan", customer.getFirstName(), "First name should be updated.");
        assertEquals("Smith", customer.getLastName(), "Last name should be updated.");
        assertEquals("jonathan.smith@example.com", customer.getEmail(), "Email should be updated.");
        assertEquals("0987654321", customer.getPhoneNumber(), "Phone number should be updated.");
        assertEquals("789 Oak St", customer.getAddress(), "Address should be updated.");
    }

    @Test
    public void testOpenMultipleAccounts() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "123 Main St", "password123");
        customer.openCreditAccount();
        customer.openSavingsAccount(500.0);

        assertNotNull(customer.getCreditAccount(), "Credit account should be opened.");
        assertNotNull(customer.getSavingsAccount(), "Savings account should be opened.");
    }
	
	

}
