package bankapp;

import java.util.List;
import java.util.Scanner;

public class Menu {
	// Options for start panel
	private static final int LOGIN_OPTION = 1;
	private static final int REGISTER_OPTION = 2;
	private static final int EXIT = 3;

	// Options for user panel
	private static final int VIEW_INFO = 1;
	private static final int CHANGE_PASSWORD = 2;
	private static final int EDIT_INFO = 3;
	private static final int DELETE_ACCOUNT = 4;
	private static final int DEPOSIT = 5;
	private static final int WITHDRAW = 6;
	private static final int OPEN_CREDIT = 7;
	private static final int OPEN_SAVING = 8;
	private static final int VIEW_CREDIT = 9;
	private static final int VIEW_SAVING = 10;
	private static final int TRANSFER_FUNDS = 11;
	private static final int CHECKING_STATEMENT = 12;
	private static final int CREDIT_STATEMENT = 13;
	private static final int SAVING_STATEMENT = 14;
	private static final int VIEW_HOUSE_LOAN = 15;
	private static final int LOGOUT = 16;

	// Options for credit panel
	private static final int CREDIT_BORROW = 1;
	private static final int CREDIT_PAY = 2;
	private static final int CREDIT_EXIT = 3;

	// Options for house loan panel
	private static final int GET_HOUSE_LOAN = 1;
	private static final int PAY_HOUSE_LOAN = 2;
	private static final int EXIT_HOUSE_LOAN = 3;

	// Utility instance variables
	private Scanner keyboardInput;
	private Customer currentUser;
	private CustomerBase userRepository;
	private Statement checkingStatement;

	// Variables to keep track of which panel to be in
	private boolean isExit = false;
	private boolean isCredit = false;
	private boolean isSaving = false;
	private boolean isHouseLoan = false;

	/*
	 * Simulates a bank application.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		Menu bankMenu = new Menu();
		bankMenu.run();
	}
	
	/**
	 * Constructor for Menu class
	 * Initializes the instance variables for the class
	 */
	public Menu() {
		keyboardInput = new Scanner(System.in);
		this.userRepository = new CustomerBase();
		this.checkingStatement = new Statement("checking.txt");
		this.currentUser = null;
	}
	
	/**
	 * Utility method to select the appropriate handler for the user
	 */
	public void run() {
		while (!isExit) {
			if (this.currentUser == null) {
				this.handleStart();
			} else {
				if (this.isCredit) {
					this.handleCredit();
				} else if (this.isSaving) {
					this.handleSaving();
				} else if (isHouseLoan) {
					this.handleHouseLoan();
				} else {
					this.handleUser();
				}
			}
			System.out.println();
		}
	}

	/**
	 * Retrieves the option the user wants to select.
	 * Can be used to handle integer inputs for things other than selecting an option.
	 * 
	 * @return The option number selected by the user.
	 */
	public int handleOptionInput() {
		int option;
		while (true) {
			if (this.keyboardInput.hasNextInt()) {
				option = this.keyboardInput.nextInt();
				break;
			} else {
				this.keyboardInput.next();
				System.out.println("Invalid Input, enter a option");
			}
		}
		this.keyboardInput.nextLine();
		return option;
	}

	/**
	 * Retrieves the input of the user.
	 * 
	 * @return The input written by the user.
	 */
	public String handleUserInput() {
		while (true) {
			if (this.keyboardInput.hasNextLine()) {
				return this.keyboardInput.nextLine();
			}
		}
	}

	/**
	 * Retrieves a positive double from the user.
	 * Generally for things like payments.
	 * 
	 * @return A positive double value written by the user.
	 */
	public double handlePositiveDoubleValue() {
		double value;

		while (true) {
			if (keyboardInput.hasNextDouble()) {
				value = this.keyboardInput.nextDouble();

				while (value < 0) {
					System.out.println("Enter a positive value");
					value = this.keyboardInput.nextDouble();
				}
				break;

			} else {
				this.keyboardInput.next();
				System.out.println("Invalid Input, enter a valid amount");
			}
		}
		this.keyboardInput.nextLine();
		return value;
	}

	/**
	 * Checks whether the user exists in the data base and whether the
	 * password is correct. If both are correct sets the current user to
	 * be the user that logged in.
	 */
	public void login() {
		System.out.println("Enter your username or input q to quit");
		String input = this.handleUserInput();
		if (input.equals("q")) {
			return;
		}
		String username = input;
		System.out.println("Enter your password");
		input = this.handleUserInput();
		String password = input;
		if (!this.userRepository.exist(username)) {
			System.out.println("username or password incorrect");
			return;
		}
		Customer user = this.userRepository.get(username);
		if (!user.getPassword().equals(password)) {
			System.out.println("username or password incorrect");
			return;
		}
		this.currentUser = user;
	}

	/**
	 * Creates a customer account for the user if a unique username is used.
	 */
	private void register() {
		System.out.print("Enter username or q to quit: ");
		String username = this.handleUserInput();
		if (userRepository.exist(username)) {
			System.out.println("Username already exists.");
			return;
		}
		if (username.equals("q")) {
			return;
		}
		initializeNewCustomer(username);
	}
	
	/**
	 * A helper function for register() that creates and initializes a new customer
	 * @param username A unique username for the customer
	 */
	private void initializeNewCustomer(String username) {
		System.out.print("Enter password: ");
		String password = this.handleUserInput();
		System.out.print("Enter first name: ");
		String firstName = this.handleUserInput();
		System.out.print("Enter last name: ");
		String lastName = this.handleUserInput();
		System.out.print("Enter email: ");
		String email = this.handleUserInput();
		System.out.print("Enter phone: ");
		String phone = this.handleUserInput();
		Customer newCustomer = new Customer(username, password, firstName, lastName, email, phone);
		if (userRepository.add(newCustomer)) {
			System.out.println("Registration successful.");
			currentUser = newCustomer;
		} else {
			System.out.println("Registration failed.");
		}
	}

	/**
	 * Handles the starting view and actions before any user logs in or registers.
	 */
	public void handleStart() {
		this.startPanel();
		int option = this.handleOptionInput();
		switch (option) {
		case LOGIN_OPTION:
			this.login();
			break;
		case REGISTER_OPTION:
			this.register();
			break;
		case EXIT:
			this.isExit = true;
			break;
		default:
			System.out.println("Unrecognized option, try again");
		}

	}
	
	/**
	 * Handles the view and actions after a user logs in
	 */
	public void handleUser() {
		this.userPanel();
		int option = this.handleOptionInput();
		switch (option) {
		case VIEW_INFO:
			this.viewInfo();
			break;
		case CHANGE_PASSWORD:
			this.changePassword();
			break;
		case EDIT_INFO:
			this.editInfo();
			break;
		case DELETE_ACCOUNT:
			this.deleteAccount();
			break;
		case DEPOSIT:
			this.deposit();
			break;
		case WITHDRAW:
			this.withdraw();
			break;
		case LOGOUT:
			this.currentUser = null;
			break;
		case OPEN_CREDIT:
			if (currentUser.getCreditAccount() != null) {
				System.out.println("Credit account already exists.");
			} else {
				currentUser.openCreditAccount();
				System.out.println("Credit account opened successfully.");
			}
			break;
		case OPEN_SAVING:
			this.openSaving();
			break;
		case VIEW_CREDIT:
			this.viewCredit();
			break;
		case VIEW_SAVING:
			this.viewSaving();
			break;
		case TRANSFER_FUNDS:
			this.transferFunds();
			break;
		case CHECKING_STATEMENT:
			this.viewStatement(this.checkingStatement.getStatement(currentUser));
			break;
		case CREDIT_STATEMENT:
			if (currentUser.getCreditAccount() == null) {
				System.out.println("No credit account exists. Please open one first.");
			} else {
				CreditStatement statement = currentUser.getCreditAccount().getCreditStatement();
				if (statement != null) {
					System.out.println(statement.generateStatement());
				} else {
					System.out.println("Statement system not initialized");
				}
			}
			break;
		case SAVING_STATEMENT:
			this.viewSavingStatement();
			break;
		case VIEW_HOUSE_LOAN:
			isHouseLoan = true;
			break;
		default:
			System.out.println("Invalid Option");
		}
	}
	
	/**
	 * Helper method for handleUser()
	 * Displays user's personal information 
	 */
	private void viewInfo() {
		if (currentUser == null) {
			System.out.println("No user is logged in.");
			return;
		}
		System.out.println("Username: " + currentUser.getUsername());
		System.out.println("First Name: " + currentUser.getFirstName());
		System.out.println("Last Name: " + currentUser.getLastName());
		System.out.println("Email: " + currentUser.getEmail());
		System.out.println("Phone: " + currentUser.getPhone());
	}
	
	/**
	 * Helper method for handleUser()
	 * Validates user and allows user to change their password.
	 */
	private void changePassword() {
		System.out.print("Enter current password: ");
		String currentPass = handleUserInput();
		if (!currentPass.equals(currentUser.getPassword())) {
			System.out.println("Incorrect current password.");
			return;
		}
		System.out.print("Enter new password: ");
		String newPass = handleUserInput();
		System.out.print("Confirm new password: ");
		String confirmPass = handleUserInput();
		if (!newPass.equals(confirmPass)) {
			System.out.println("New password and confirmation do not match.");
			return;
		}
		currentUser.setPassword(newPass);
		if (userRepository.update(currentUser)) {
			System.out.println("Password changed successfully.");
		} else {
			System.out.println("Password update failed.");
		}
	}
	
	/**
	 * Helper method for handleUser()
	 * Allows user to modify personal information
	 */
	private void editInfo() {
		System.out.print("Enter new first name (" + currentUser.getFirstName() + "): ");
		String newFirstName = handleUserInput();
		if (!newFirstName.trim().isEmpty()) {
			currentUser.setFirstName(newFirstName);
		}
		System.out.print("Enter new last name (" + currentUser.getLastName() + "): ");
		String newLastName = handleUserInput();
		if (!newLastName.trim().isEmpty()) {
			currentUser.setLastName(newLastName);
		}
		System.out.print("Enter new email (" + currentUser.getEmail() + "): ");
		String newEmail = handleUserInput();
		if (!newEmail.trim().isEmpty()) {
			currentUser.setEmail(newEmail);
		}
		System.out.print("Enter new phone (" + currentUser.getPhone() + "): ");
		String newPhone = handleUserInput();
		if (!newPhone.trim().isEmpty()) {
			currentUser.setPhone(newPhone);
		}
		if (userRepository.update(currentUser)) {
			System.out.println("Information updated successfully.");
		} else {
			System.out.println("Failed to update information.");
		}
	}
	
	/**
	 * Helper method for handleUser()
	 * Confirms request to delete account. If confirmed deletes account.
	 */
	private void deleteAccount() {
		System.out.print("Are you sure you want to delete your account? (y/n): ");
		String confirm = handleUserInput();
		if (!confirm.equalsIgnoreCase("y")) {
			System.out.println("Account deletion canceled.");
			return;
		}
		if (userRepository.delete(currentUser)) {
			System.out.println("Account deleted successfully.");
			currentUser = null;
		} else {
			System.out.println("Failed to delete account.");
		}
	}
	
	/**
	 * Helper method for handleUser()
	 * Deposits money into the checking account if a valid amount is entered.
	 */
	private void deposit() {
		System.out.println("Enter the amount you want to deposit");
		int amount = this.handleOptionInput();
		if (amount < 0) {
			System.out.println("amount can't be negative");
			return;
		}
		this.currentUser.getCheckingAccount().deposit(amount);
		this.checkingStatement.add(currentUser, amount);
	}
	
	/**
	 * Helper method for handleUser()
	 * Withdraws money from the checking account if a valid amount is requested.
	 */
	private void withdraw() {
		System.out.println("Enter the number you want to WITHDRAW");
		int amount = this.handleOptionInput();
		if (amount > this.currentUser.getCheckingAccount().getCurrentBalance()) {
			System.out.println("The amount should not be larger than your balance");
			return;
		}
		if (amount > 500) {
			if (!largeSumWithdraw()) {
				System.out.println("Withdrawal cancelled.");
				return;
			}
		}
		this.currentUser.getCheckingAccount().withdraw(amount);
		this.checkingStatement.add(currentUser, -amount);
	}
	
	/**
	 * Helper method for handleUser()
	 * Opens a savings account with an initial deposit for the customer
	 * if they do not have one already.
	 */
	private void openSaving() {
		if (currentUser.getSavingsAccount() != null) {
			System.out.println("You already opened a savings account.");
			return;
		}
		System.out.println("Enter your initial deposit");
		int amount = this.handleOptionInput();
		if (amount <= 0) {
			System.out.println("Initial deposit must be positive");
			return;
		}
		this.currentUser.openSavingsAccount(amount);
		System.out.println("Savings account opened successfully");
	}
	
	/**
	 * Helper method for handleUser().
	 * checks if a credit account exists. 
	 * If it does sets state to view the credit panel.
	 */
	private void viewCredit() {
		if (this.currentUser.getCreditAccount() == null) {
			System.out.println("You don't have a credit account");
			return;
		}
		this.isCredit = true;
	}

	/**
	 * Helper method for handleUser().
	 * checks if a savings account exists. 
	 * If it does sets state to view the savings panel.
	 */
	private void viewSaving() {
		if (this.currentUser.getSavingsAccount() == null) {
			System.out.println("You don't have a savings account");
			return;
		}
		this.isSaving = true;
	}
	
	/**
	 * Helper method for handleUser().
	 * Transfers funds from one customer's checking account to 
	 * another customer's checking account. If the amount the user
	 * wants to transfer is a large sum of money it is validated by 
	 * password protection.
	 */
	private void transferFunds() {
		System.out.println("Enter the amount you want to transfer: ");
		int amount = this.handleOptionInput();
		if (amount < 0) {
			System.out.println("Amount can't be negative");
			return;
		}
		if (this.currentUser.getCheckingAccount().getCurrentBalance() < amount) {
			System.out.println("You don't have enough available funds");
			return;
		}
		System.out.println("Enter the username of the recipient: ");
		String recipientUsername = this.handleUserInput();
		if (!this.userRepository.exist(recipientUsername)) {
			System.out.println("Recipient does not exist");
			return;
		}
		int largeSumOfMoney = 500;
		if (amount > largeSumOfMoney) {
			if (!largeSumWithdraw()) {
				System.out.println("Transfer cancelled");
				return;
			}
		}

		System.out.println("Are you sure you want to transfer $" + amount + " to "
				+ this.userRepository.get(recipientUsername).getEmail() + "? (y/n)");
		String confirm = this.handleUserInput();
		if (!confirm.equalsIgnoreCase("y")) {
			System.out.println("Transfer canceled");
			return;

		}
		this.currentUser.getCheckingAccount().withdraw(amount);
		this.checkingStatement.add(currentUser, -amount);
		this.userRepository.get(recipientUsername).getCheckingAccount().deposit(amount);
		this.checkingStatement.add(this.userRepository.get(recipientUsername), amount);
		System.out.println("Transfer successful");
	}
	
	/**
	 * Ensures the current user really wants to transact a large sum of money
	 * and password protects this transaction.
	 * 
	 * @return The status of the transaction i.e. whether it was successful or not.
	 */
	private boolean largeSumWithdraw() {
		System.out.println("You are trying to move a large sum of money, please confirm your identity.");
		System.out.print("Enter your password: ");
		String password = handleUserInput();
		if (!password.equals(currentUser.getPassword())) {
			System.out.println("Incorrect password. Withdrawal cancelled.");
			return false;
		}
		System.out.println("Identity confirmed.");
		return true;
	}
	
	/**
	 * Helper method for handleUser()
	 * If a savings account exists for the customer it prints the savings statement
	 */
	private void viewSavingStatement() {
		if (currentUser.getSavingsAccount() == null) {
			System.out.println("No savings account exists. Please open one first.");
			return;
		}
		currentUser.generateSavingsStatement();
		System.out.println("Press any key to continue...");
		this.handleUserInput();
	}
	
	/**
	 * Handles the view and actions relating to a user's credit account.
	 */
	public void handleCredit() {
		System.out.println("Your current credit balance is " + this.currentUser.getCreditAccount().getCreditBalance());
		System.out.println(
				"Your current available credit is " + this.currentUser.getCreditAccount().getAvailableCredit());
		System.out.println("Your current credit limit is " + this.currentUser.getCreditAccount().getCreditLimit());
		System.out.println("Your current credit score is " + this.currentUser.getCreditAccount().getCreditScore());
		this.creditPanel();
		int option = this.handleOptionInput();
		switch (option) {
		case CREDIT_BORROW:
			System.out.println("Enter the amount you want to borrow");
			int amountToBrorrow = this.handleOptionInput();
			this.currentUser.getCreditAccount().borrowCredit(amountToBrorrow);
			break;
		case CREDIT_PAY:
			System.out.println("Enter the amount you want to pay");
			int amountToPay = this.handleOptionInput();
			this.currentUser.getCreditAccount().repayCredit(amountToPay);
			break;
		case CREDIT_EXIT:
			this.isCredit = false;
			break;
		default:
			System.out.println("Invalid option");
		}
	}

	/**
	 * Handles the view and actions relating to a user's savings account.
	 */
	public void handleSaving() {
		System.out.println(
				"Your current savings account balance is " + this.currentUser.getSavingsAccount().getBalance());
		this.savingPanel();
		int option = this.handleOptionInput();
		switch (option) {
		case CREDIT_BORROW:
			System.out.println("Enter the amount you want to withdraw");
			int amountWithdraw = this.handleOptionInput();
			if (amountWithdraw > this.currentUser.getSavingsAccount().getBalance()) {
				System.out.println("Cannot withdraw more than you have in your savings account. ");
				return;
			}
			if (amountWithdraw <= 0) {
				System.out.println("Cannot withdraw 0 or negative amount. ");
				return;
			}
			this.currentUser.getSavingsAccount().withdraw(amountWithdraw);
			break;
		case CREDIT_PAY:
			System.out.println("Enter the amount you want to deposit");
			int amountDeposit = this.handleOptionInput();
			if (amountDeposit <= 0) {
				System.out.println("Cannot deposit a negative amount or 0. ");
				return;
			}
			this.currentUser.getSavingsAccount().deposit(amountDeposit);
			break;
		case CREDIT_EXIT:
			this.isSaving = false;
			break;
		default:
			System.out.println("Invalid option");
		}

	}

	/**
	 * Handles the view and actions relating to a user's house loan.
	 */
	public void handleHouseLoan() {
		houseLoanPanel();
		int option = handleOptionInput();

		switch (option) {
		case GET_HOUSE_LOAN:
			if (currentUser.getHouseLoan() != null) {
				System.out.println("We do not offer more than one house loan at the same time");
			} else {
				System.out.println("Enter the price of the house you want to buy");
				double housePrice = handlePositiveDoubleValue();
				System.out.println("Enter the down payment you want to put on the house");
				double downPayment = handlePositiveDoubleValue();
				while (downPayment > housePrice) {
					System.out.println("Down payment cannot be more than house price");
					System.out.println("Re-enter valid down payment amount");
					downPayment = handlePositiveDoubleValue();
				}
				currentUser.getLoanForHouse(housePrice, downPayment);
			}
			break;
		case PAY_HOUSE_LOAN:
			if (currentUser.getHouseLoan() == null) {
				System.out.println("No outstanding loans to pay");
				break;
			}
			if (currentUser.getHouseLoan().getLeftOverLoan() == 0) {
				currentUser.reSetHouseLoan();
				System.out.println("No outstanding loans to pay");
				break;
			}
			if (currentUser.getHouseLoan() != null) {
				System.out.println("Enter the amount you want to pay");
				double payment = handlePositiveDoubleValue();
				currentUser.getHouseLoan().makePayment(payment);
			}
			break;
		case EXIT_HOUSE_LOAN:
			isHouseLoan = false;
			break;
		default:
			System.out.println("Invalid option");
		}
	}

	/**
	 * Prints the checking account statement
	 * 
	 * @param activities A list containing all transactions
	 */
	private void viewStatement(List<String> activities) {
		System.out.println("Here is your statement");
		for (String activity : activities) {
			System.out.println(activity);
		}
		System.out.println("enter any key to go back");
		this.handleUserInput();
	}

	/**
	 * Prints options before login
	 */
	public void startPanel() {
		System.out.println("Welcome to our bank");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
	}

	/**
	 * Displays various account balances if opened and displays options 
	 * for the customer to select.
	 */
	public void userPanel() {
		System.out.println("Welcome " + this.currentUser.getUsername());
		this.viewBalance();
		System.out.println("1. view info");
		System.out.println("2. change password");
		System.out.println("3. edit info");
		System.out.println("4. delete account");
		System.out.println("5. deposit");
		System.out.println("6. withdraw");
		System.out.println("7. open a credit account");
		System.out.println("8. open a saving account");
		System.out.println("9. Credit account service");
		System.out.println("10. Savings account service");
		System.out.println("11. transfer funds");
		System.out.println("12. view statement");
		System.out.println("13. view credit account statement");
		System.out.println("14. view savings account statement");
		System.out.println("15. House loan service");
		System.out.println("16. logout");
	}
	
	/**
	 * Helper method for userPanel()
	 * Displays the checking account balance. If credit and savings accounts exist
	 * it also displays their balance too.
	 */
	private void viewBalance() {
		System.out.println("Your current Balance is " + this.currentUser.getCheckingAccount().getCurrentBalance());
		if (this.currentUser.getCreditAccount() != null) {
			System.out.println(
					"Your current credit account balance is " + this.currentUser.getCreditAccount().getCreditBalance());
		} else {
			System.out.println("Currently you don't have a credit account, open one if you need one.");
		}
		if (this.currentUser.getSavingsAccount() != null) {
			System.out.println(
					"Your current savings account balance is" + this.currentUser.getSavingsAccount().getBalance());
		} else {
			System.out.println("Currently you don't have a savings account, open one if you need one.");
		}
	}

	
	/**
	 * Displays options for credit services.
	 */
	public void creditPanel() {
		System.out.println("1. borrow");
		System.out.println("2. pay");
		System.out.println("3. exit");
	}

	/**
	 * Displays options for savings services.
	 */
	public void savingPanel() {
		System.out.println("1. withdraw");
		System.out.println("2. deposit");
		System.out.println("3. exit");
	}

	/**
	 * Displays options for house loans;
	 */
	public void houseLoanPanel() {
		System.out.println("1. Take out a loan for a house");
		System.out.println("2. Make payment");
		System.out.println("3. Exit");
	}
}