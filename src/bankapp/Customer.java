package bankapp;

public class Customer {
    private final String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private final BankAccount checkingAccount;
    private SavingsAccount savingsAccount;
    private CreditAccount creditAccount;
    private SavingsStatement savingsStatement;
    private HouseLoan houseLoan;

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
        // House loan stays null until opened.
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
    
    public HouseLoan getHouseLoan() {
    	return houseLoan;
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
    
    public void reSetHouseLoan() {
    	houseLoan = null;
    }
    
    /**
     * Opens a savings account for the customer with an initial deposit.
     *
     * @param initialDeposit the initial deposit for the savings account.
     * @throws IllegalStateException If a savings account already exists.
     */
    public void openSavingsAccount(double initialDeposit) {
        if (savingsAccount != null) {
            throw new IllegalStateException("Savings account already exists.");
        }
        savingsAccount = new SavingsAccount(initialDeposit);
    }
    
    /**
     * Opens a credit account for the customer.
     * @throws IllegalStateException If a credit account already exists.
     */
    public void openCreditAccount() {
        if (creditAccount != null) {
            throw new IllegalStateException("Credit account already exists");
        }
        creditAccount = new CreditAccount(username, new CreditStatement(this));
        creditAccount.setCreditStatement(new CreditStatement(this));  
    }
    
    /**
     * Gives a house loan to the customer.
     * 
     * @param homePrice The price of the house the customer wants to buy.
     * @param downPayment The amount of down payment the customer will put.
     * @throws IllegalStateException If a house loan already exists.
     */
    public void getLoanForHouse(double homePrice, double downPayment) {
        if (houseLoan != null) {
            throw new IllegalStateException("House loan already taken");
        }
        houseLoan = new HouseLoan(homePrice, downPayment);
        System.out.println("success");
        System.out.println("Loan Amount $: " + (homePrice - downPayment));
        System.out.println("Amount you have to pay over the next 30 years after interest $: " + 
        					houseLoan.getLeftOverLoan());
    }
    
    /**
     * Generates a savings statement for the customer.
     * 
     * @throws IllegalStateException If a savings account does not exist.
     */
    public void generateSavingsStatement() {
        if (savingsAccount == null) {
            throw new IllegalStateException("No savings account exists");
        }
        new SavingsStatement(savingsAccount).generateStatement();
    }
    
  
    @Override
    public String toString() {
    	String res = username+"\n"+password+"\n"+firstName+"\n"+lastName+"\n"+email+"\n"+phone;
    	return res;
    }
    
    
 // Update accountsToString in Customer class
    public String accountsToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CHECKING:").append(checkingAccount.getCurrentBalance()).append("\n");
        
        if (savingsAccount != null) {
            sb.append("SAVINGS:").append(savingsAccount.getBalance()).append("\n");
        }
        
        if (creditAccount != null) {
            sb.append("CREDIT:").append(creditAccount.getCreditBalance()).append("\n");
        }
        
        if (houseLoan != null) {
            sb.append("HOUSELOAN:").append(houseLoan.toDataString()).append("\n");
        }
        
        return sb.toString();
    }

    // Update updateAccountsFromString in Customer class
    public void updateAccountsFromString(String accountData) {
        String[] lines = accountData.split("\n");
        for (String line : lines) {
            String[] parts = line.split(":", 2); // Split only on first colon
            if (parts.length != 2) continue;
            
            String accountType = parts[0];
            String data = parts[1];
            
            switch (accountType) {
                case "CHECKING":
                    checkingAccount.setCurrentBalance(Double.parseDouble(data));
                    break;
                case "SAVINGS":
                    if (savingsAccount == null) {
                        openSavingsAccount(Double.parseDouble(data));
                    } else {
                        savingsAccount.setBalance(Double.parseDouble(data));
                    }
                    break;
                case "CREDIT":
                    if (creditAccount == null) {
                        openCreditAccount();
                    }
                    creditAccount.setCreditBalance(Double.parseDouble(data));
                    break;
                case "HOUSELOAN":
                    if (houseLoan == null) {
                        // Create a dummy loan first
                        houseLoan = new HouseLoan(0, 0);
                    }
                    houseLoan.fromDataString(data);
                    break;
            }
        }
    }
}
