package bankapp;

import java.util.NoSuchElementException;

public class Customer {

	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private BankAccount account;
	private SavingsAccount savingsAccount;

	// Constructor if customer has a middle name
	public Customer(String firstName, String middleName, String lastName, String email, String phoneNumber,
			String address) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.account = new BankAccount();
	}
	
	// Constructor if customer has no middle name
	public Customer(String firstName, String lastName, String email, String phoneNumber,
			String address) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.account = new BankAccount();
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
	
	public BankAccount getaccount() {
		return account;
	}
	
	/* move these into Menu class

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
	*/
	
	
	public SavingsAccount getSavingsAccount() {
		if (savingsAccount == null) {
			throw new NoSuchElementException("Customer doesn't have a savings account");
		}
		return savingsAccount;
	}
	
	public void openSavingsAccount(double initialDeposit) {
		if (savingsAccount != null) {
			throw new IllegalStateException("Customer already has a savings account");
		}
		this.savingsAccount = new SavingsAccount(this.hashCode(), initialDeposit);
	}
	
	public void transferToSavings(double amount) {
		if (savingsAccount == null) {
			throw new IllegalStateException("No savings account exists");
		}
		account.withdraw(amount);
		savingsAccount.deposit(amount);
	}
	
	public void transferFromSavings(double amount) {
		if (savingsAccount == null) {
			throw new IllegalStateException("No savings account exists");
		}
		if (savingsAccount.withdraw(amount)) {
		account.deposit(amount);
		} else {
			throw new IllegalArgumentException("Transfer failed - insufficient funds in savings");
		}
	}
	
	
	
}
