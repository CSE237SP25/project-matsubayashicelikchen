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
    private BankAccount account;
    private SavingsAccount savingsAccount;
    private CreditAccount creditAccount;

    // Constructors (simplified to avoid duplication)
    public Customer(String firstName, String middleName, String lastName,
                   String email, String phoneNumber, String address, String password) {
        this.customerID = nextCustomerID++;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.account = new BankAccount();
    }

    public Customer(String firstName, String lastName,
                   String email, String phoneNumber, String address, String password) {
        this(firstName, null, lastName, email, phoneNumber, address, password);
    }

    // Credit account methods using your existing CreditAccount class
    public void openCreditAccount() {
        if (creditAccount != null) {
            throw new IllegalStateException("Customer already has a credit account");
        }
        this.creditAccount = new CreditAccount();
    }

    public CreditAccount getCreditAccount() {
        if (creditAccount == null) {
            throw new NoSuchElementException("Customer doesn't have a credit account");
        }
        return creditAccount;
    }

    // All other methods remain the same from previous solutions:
    public void openSavingsAccount(double initialDeposit) {
        if (savingsAccount != null) {
            throw new IllegalStateException("Customer already has a savings account");
        }
        this.savingsAccount = new SavingsAccount(this.customerID, initialDeposit);
    }

    public void transferToSavings(double amount) {
        if (savingsAccount == null) {
            throw new IllegalStateException("No savings account exists");
        }

  

        // Withdraw from checking and deposit into savings
        account.withdraw(amount); // Withdraw from checking
 

        savingsAccount.deposit(amount); // Deposit into savings
     
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
    
 // Check Credit Score for eligibility
    public boolean isEligibleForLoan(int minimumCreditScore) {
        if (creditAccount == null) {
            throw new NoSuchElementException("Customer doesn't have a credit account");
        }
        return creditAccount.getCreditScore() >= minimumCreditScore;
    }

    // Getters
    public int getCustomerID() { return customerID; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() {
        if (middleName == null) {
            throw new NoSuchElementException("This customer has no middle name.");
        }
        return middleName;
    }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public BankAccount getAccount() { return account; }
    public SavingsAccount getSavingsAccount() {
        if (savingsAccount == null) {
            throw new NoSuchElementException("Customer doesn't have a savings account");
        }
        return savingsAccount;
    }
    
   
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    
 // Get Credit Score
    public int getCreditScore() {
        if (creditAccount == null) {
            throw new NoSuchElementException("Customer doesn't have a credit account");
        }
        return creditAccount.getCreditScore();
    }

    // Setters
    public void changeFirstName(String newFirstName) { this.firstName = newFirstName; }
    public void changeMiddleName(String newMiddleName) { this.middleName = newMiddleName; }
    public void changeLastName(String newLastName) { this.lastName = newLastName; }
    public void changeEmail(String newEmail) { this.email = newEmail; }
    public void changePhoneNumber(String newPhoneNumber) { this.phoneNumber = newPhoneNumber; }
    public void changeAddress(String newAddress) { this.address = newAddress; }
    public static void setNextCustomerID(int nextID) { nextCustomerID = nextID; }
    
    public void updateCreditScore(int newScore) {
        if (creditAccount == null) {
            throw new NoSuchElementException("Customer doesn't have a credit account");
        }
        creditAccount.updateCreditScore(newScore);  // Assumes you add a setter in CreditAccount
    }

    @Override
    public String toString() {
        return "Customer: " + firstName + " (" + email + ")";
    }
}