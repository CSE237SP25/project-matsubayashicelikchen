package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.Customer;

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
		customer.getaccount().deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(customer.getaccount().getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		try {
			customer.getaccount().deposit(-25);
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

}
