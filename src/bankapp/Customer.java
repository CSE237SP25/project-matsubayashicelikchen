package bankapp;

public class Customer {
    // Instance variables
    private final String username;       // immutable
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private final BankAccount checkingAccount;
    private SavingsAccount savingsAccount;
    private CreditAccount creditAccount;

    /**
     * Constructor for a customer with all details provided.
     * Initializes the checking account.
     *
     * @param username  the unique username (immutable)
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email address
     * @param phone     the phone number
     */
    public Customer(String username, String password, String firstName,
                    String lastName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        // Automatically initialize the checking account upon creation.
        this.checkingAccount = new BankAccount();
        // savingsAccount and creditAccount remain null until opened.
    }

    /**
     * Constructor for a customer where only username, password, firstName,
     * lastName, and email are provided 
     * The phone value is set to null.
     *
     * @param username  the unique username (immutable)
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email address
     */
    public Customer(String username, String password, String firstName,
                    String lastName, String email) {
        this(username, password, firstName, lastName, email, null);
    }

    // Getters for all fields

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
  
    public String getFirstName() {
        return firstName;
    }
  
    public String getLastName() {
        return lastName;
    }
  
    public String getEmail() {
        return email;
    }
  
    public String getPhone() {
        return phone;
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

    // Setters for mutable fields (username and account fields have no setters)
    public void setPassword(String password) {
        this.password = password;
    }
  
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
  
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
  
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Opens a savings account for the customer with an initial deposit.
     * If a savings account already exists, it will throw an IllegalStateException.
     *
     * @param initialDeposit the initial deposit for the savings account
     */
    public void openSavingsAccount(double initialDeposit) {
        if (savingsAccount != null) {
            throw new IllegalStateException("Savings account already exists.");
        }
        savingsAccount = new SavingsAccount(0,initialDeposit,0);
    }
  
    /**
     * Opens a credit account for the customer.
     * If a credit account already exists, it will throw an IllegalStateException.
     */
    public void openCreditAccount() {
        if (creditAccount != null) {
            throw new IllegalStateException("Credit account already exists.");
        }
        creditAccount = new CreditAccount();
    }
  
    @Override
    public String toString() {
    	String res = username+"\n"+password+"\n"+firstName+"\n"+lastName+"\n"+email+"\n"+phone;
    	return res;
        
    }
}
