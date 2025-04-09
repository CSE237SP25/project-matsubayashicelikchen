package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import bankapp.Customer;
import bankapp.BankAccount;
import bankapp.SavingsAccount;
import bankapp.CreditAccount;

public class CustomerTests {

    @Test
    public void testCustomerConstructorAll() {
        Customer customer = new Customer("johnDoe", "password123", "John", "Doe", "johndoe@example.com", "1234567890");
        assertEquals("johnDoe", customer.getUsername());
        assertEquals("password123", customer.getPassword());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("johndoe@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhone());
        assertNotNull(customer.getCheckingAccount());
        assertNull(customer.getSavingsAccount());
        assertNull(customer.getCreditAccount());
    }

    @Test
    public void testCustomerConstructorNoPhone() {
        Customer customer = new Customer("janeDoe", "pass456", "Jane", "Doe", "janedoe@example.com");
        assertEquals("janeDoe", customer.getUsername());
        assertEquals("pass456", customer.getPassword());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("janedoe@example.com", customer.getEmail());
        assertNull(customer.getPhone());
        assertNotNull(customer.getCheckingAccount());
        assertNull(customer.getSavingsAccount());
        assertNull(customer.getCreditAccount());
    }

    @Test
    public void testToStringMethod() {
        Customer customer = new Customer("johnDoe", "password123", "John", "Doe", "johndoe@example.com", "1234567890");
        String expected = "johnDoe\npassword123\nJohn\nDoe\njohndoe@example.com\n1234567890";
        assertEquals(expected, customer.toString());
    }

    @Test
    public void testSetters() {
        Customer customer = new Customer("johnDoe", "password123", "John", "Doe", "johndoe@example.com", "1234567890");
        customer.setPassword("newPassword");
        customer.setFirstName("Jonathan");
        customer.setLastName("Smith");
        customer.setEmail("jonathan.smith@example.com");
        customer.setPhone("0987654321");
        
        assertEquals("newPassword", customer.getPassword());
        assertEquals("Jonathan", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("jonathan.smith@example.com", customer.getEmail());
        assertEquals("0987654321", customer.getPhone());
    }
    
    // Note: The open savings account functionality is not fully implemented yet.
    // This test is disabled and can be ignored for now.
    @Disabled("Open savings account method is not implemented yet; ignore this test")
    @Test
    public void testOpenSavingsAccount() {
        Customer customer = new Customer("johnDoe", "password123", "John", "Doe", "johndoe@example.com", "1234567890");
        assertNull(customer.getSavingsAccount());
        customer.openSavingsAccount(100.0);
        assertNotNull(customer.getSavingsAccount());
        // Attempt to open a savings account a second time should throw an exception.
        assertThrows(IllegalStateException.class, () -> customer.openSavingsAccount(50.0));
    }

    @Test
    public void testOpenCreditAccount() {
        Customer customer = new Customer("johnDoe", "password123", "John", "Doe", "johndoe@example.com", "1234567890");
        assertNull(customer.getCreditAccount());
        customer.openCreditAccount();
        assertNotNull(customer.getCreditAccount());
        // Attempting to open another credit account should throw an exception.
        assertThrows(IllegalStateException.class, () -> customer.openCreditAccount());
    }
}
