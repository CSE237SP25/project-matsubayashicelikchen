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
    private String password;
    private BankAccount checkingAccount;
    private SavingsAccount savingsAccount;
    private CreditAccount creditAccount;

    // Constructor if customer has a middle name
    public Customer(String firstName, String middleName, String lastName, String email, String phoneNumber,
                    String address, String password) {

        this.customerID = nextCustomerID++;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.checkingAccount = new BankAccount();
        this.savingsAccount = new SavingsAccount();
        this.creditAccount = new CreditAccount();

    }

    // Constructor if customer has no middle name
    public Customer(String firstName, String lastName, String email, String phoneNumber,
                    String address, String password) {

        this.customerID = nextCustomerID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.checkingAccount = new BankAccount();
        this.savingsAccount = new SavingsAccount();
        this.creditAccount = new CreditAccount();    }

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

    public BankAccount getCheckingAccount() {
        return checkingAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public CreditAccount getCreditAccount() {
        return creditAccount;
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

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Customer: " + firstName + " (" + email + ")";
    }
}