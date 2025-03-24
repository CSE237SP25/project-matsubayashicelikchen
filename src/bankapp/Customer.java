package bankapp;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Customer {

	private Scanner userInput;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private BankAccount bankAccount;

	public Customer(String firstName, String middleName, String lastName, String email, String phoneNumber,
			String address) {

		this.firstName = initializeFirstName();
		this.middleName = initializeMiddleName();
		this.lastName = initializeLastName();
		this.email = initializeEmail();
		this.phoneNumber = initializePhoneNumber();
		this.address = initializeAddress();
		this.bankAccount = new BankAccount();
	}
	
	private String initializeFirstName() {
		System.out.println("Enter your first name: ");
		String firstName = userInput.nextLine();
		return firstName;
	}
	
	private String initializeMiddleName() {
		System.out.println("Enter your middle name: ");
		String middleName = userInput.nextLine();
		return middleName;
	}
	
	private String initializeLastName() {
		System.out.println("Enter your last name: ");
		String lastName = userInput.nextLine();
		return lastName;
	}
	
	private String initializeEmail() {
		System.out.println("Enter your email address: ");
		String emailAddress = userInput.nextLine();
		return emailAddress;
	}
	
	private String initializePhoneNumber() {
		System.out.println("Enter your email address: ");
		String phoneNumber = userInput.nextLine();
		return phoneNumber;
	}
	
	private String initializeAddress() {
		System.out.println("Enter your email address: ");
		String address = userInput.nextLine();
		return address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getMiddleName() {
		if (middleName == null) {
			throw new NoSuchElementException("This customer has no middle name.");
		}
		return middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
}
