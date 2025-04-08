package bankapp;

import java.util.NoSuchElementException;

public class Customer {

	private static int nextCustomerID = 1;
	private int customerID;

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

		this.customerID = nextCustomerID++;
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

		this.customerID = nextCustomerID++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.account = new BankAccount();
	}

	public static void setNextCustomerID(int nextID) {
        nextCustomerID = nextID;
    }

	public int getCustomerID() {
		return customerID;
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



	public void changeFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}

	public void changeMiddleName(String newMiddleName) {
		this.middleName = newMiddleName;
	}

	public void changeLastName(String newLastName) {
		this.lastName = newLastName;
	}

	public void changeEmail(String newEmail) {
		this.email = newEmail;
	}

	public void changePhoneNumber(String newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}

	public void changeAddress(String newAddress) {
		this.address = newAddress;
	}



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

