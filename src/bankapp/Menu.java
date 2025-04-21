package bankapp;

import java.util.List;
import java.util.Scanner;

public class Menu {
	private static final int LOGIN_OPTION = 1;
    private static final int REGISTER_OPTION = 2;
    private static final int EXIT = 3;
    private static final int VIEW_INFO = 1;
    private static final int CHANGE_PASSWORD = 2;
    private static final int EDIT_INFO = 3;
    private static final int DELETE_ACCOUNT = 4;
    private static final int DEPOSIT= 5;
    private static final int WITHDRAW = 6;
    private static final int LOGOUT = 15; 
    private static final int OPEN_CREDIT = 7;
    private static final int OPEN_SAVING = 8;
    private static final int VIEW_CREDIT= 9;
    private static final int VIEW_SAVING = 10;
    private static final int CREDIT_BORROW = 1;
    private static final int CREDIT_PAY = 2;
    private static final int CREDIT_EXIT = 3;
	private static final int TRANSFER_FUNDS = 11;
	private static final int CHECKING_STATEMENT = 12;
	private static final int CREDIT_STATEMENT = 13;
	private static final int SAVING_STATEMENT = 14; // Add this line
    private Scanner keyboardInput;
    private Customer currentUser;
    private CustomerBase userRepository;
    private boolean isExit = false;
    private boolean isCredit = false;
    private boolean isSaving = false;
    private Statement checkingStatement;
    public Menu() {
        keyboardInput = new Scanner(System.in);
        this.userRepository = new CustomerBase();
        this.checkingStatement = new Statement("checking.txt");
        this.currentUser = null;
    }
    public int handleOptionInput() {
    	int option;
    	while(true) {
    		if (this.keyboardInput.hasNextInt()) {
    			option = this.keyboardInput.nextInt();
    			break;
    		}else {
    			this.keyboardInput.next();
    			System.out.println("Invalid Input, enter a option");
    		}
    	}
    	this.keyboardInput.nextLine();
    	return option;
    }
    public String handleUserInput() {
    	while(true) {
    		if(this.keyboardInput.hasNextLine()) {
    			return this.keyboardInput.nextLine();
    		}
    	}
    }
    public void run() {
    	while(!isExit) {
    		if(this.currentUser == null) {
    			this.handleStart();
    		}else {
    			if(this.isCredit) {
    				this.handleCredit();
    			}else if(this.isSaving) {
    				this.handleSaving();
    			}else {
    				this.handleUser();
    			}
    		}
    		System.out.println();
    	}
    }
    public void login() {
    	System.out.println("Enter your username or input q to quit");
    	String input = this.handleUserInput();
    	if(input.equals("q")) {
    		return;
    	}
    	String username = input;
    	System.out.println("Enter your password");
    	input = this.handleUserInput();
    	String password = input;
    	if(!this.userRepository.exist(username)) {
    		System.out.println("username or password incorrect");
    		return;
    	}
    	Customer user = this.userRepository.get(username);
    	if(!user.getPassword().equals(password)) {
    		System.out.println("username or password incorrect");
    		return;
    	}
    	this.currentUser = user;
    }
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
    public void handleCredit() {
    	System.out.println("Your current credit balance is "+this.currentUser.getCreditAccount().getCreditBalance());
    	System.out.println("Your current available credit is "+this.currentUser.getCreditAccount().getAvailableCredit());
    	System.out.println("Your current credit limit is "+this.currentUser.getCreditAccount().getCreditLimit());
    	System.out.println("Your current credit score is "+this.currentUser.getCreditAccount().getCreditScore());
    	this.creditPanel();
    	int option = this.handleOptionInput();
    	switch(option) {
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
    
    public void handleSaving() {
    	System.out.println("Your current savings account balance is "+this.currentUser.getSavingsAccount().getBalance());
    	this.savingPanel();
    	int option = this.handleOptionInput();
    	switch(option) {
    		case CREDIT_BORROW:
    			System.out.println("Enter the amount you want to withdraw");
    	    	int amountWithdraw = this.handleOptionInput();
    	    	if(amountWithdraw > this.currentUser.getSavingsAccount().getBalance()) {
    	    		System.out.println("Cannot withdraw more than you have in your savings account. ");
    	    		return;
    	    	}
    	    	if(amountWithdraw <= 0) {
    	    		System.out.println("Cannot withdraw 0 or negative amount. ");
    	    		return;
    	    	}
    			this.currentUser.getSavingsAccount().withdraw(amountWithdraw);
    			break;
    		case CREDIT_PAY:
    			System.out.println("Enter the amount you want to deposit");
    	    	int amountDeposit = this.handleOptionInput();
    	    	if(amountDeposit <= 0) {
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
    public void handleStart() {
    	this.startPanel();
    	int option = this.handleOptionInput();
    	switch(option) {
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
    public void handleUser() {
    	this.userPanel();
    	int option = this.handleOptionInput();
    	switch(option) {
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
				
    		default:
    			System.out.println("Invalid Option");
    	}
    }
    private void viewStatement(List<Integer> activities) {
    	System.out.println("Here is your statement");
    	for(Integer activity:activities) {
    		System.out.println(activity);
    	}
    	System.out.println("enter any key to go back");
    	this.handleUserInput();
    }
    
	private boolean largeSumWithdraw(){
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
	
	private void borrow() {
    	System.out.println("Enter the amount you want to borrow");
    	int amount = this.handleOptionInput();
    	if(amount < 0) {
    		System.out.println("amount can't be negative");
    		return;
    	}
		this.currentUser.getCreditAccount().borrowCredit(amount);
   		System.out.println("borrow success");
    }

    private void pay() {
    	System.out.println("Enter the amount you want to pay");
    	int amount = this.handleOptionInput();
    	if(amount > this.currentUser.getCreditAccount().getCreditBalance()) {
    		System.out.println("You can't pay an amount that exceeds your balance");
    		return;
    	}
    	this.currentUser.getCreditAccount().repayCredit(amount);
    	System.out.println("pay success");
    }
    private void viewCredit() {
    	if(this.currentUser.getCreditAccount()== null) {
    		System.out.println("You don't have a credit account");
    		return;
    	}
    	this.isCredit = true;
    }
    

    
    private void openSaving() {
        if(currentUser.getSavingsAccount() != null) {
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
    
    private void viewSaving() {
    	if(this.currentUser.getSavingsAccount()== null) {
    		System.out.println("You don't have a savings account");
    		return;
    	}
    	this.isSaving = true;
    }
    
    private void viewSavingStatement() {
        if (currentUser.getSavingsAccount() == null) {
            System.out.println("No savings account exists. Please open one first.");
            return;
        }
        currentUser.generateSavingsStatement();
        System.out.println("Press any key to continue...");
        this.handleUserInput();
    }
    
    private void withdraw() {
    	System.out.println("Enter the number you want to WITHDRAW");
    	int amount = this.handleOptionInput();
    	if(amount > this.currentUser.getCheckingAccount().getCurrentBalance()) {
    		System.out.println("The amount should not be larger than your balance");
    		return;
    	}
		if (amount > 500) {
			if (!largeSumWithdraw()){
				System.out.println("Withdrawal cancelled.");
				return;
			}
		}
    	this.currentUser.getCheckingAccount().withdraw(amount);
    	this.checkingStatement.add(currentUser, -amount);
    }

    private void viewBalance() {
    	System.out.println("Your current Balance is "+this.currentUser.getCheckingAccount().getCurrentBalance());
    }
    private void deposit() {
    	System.out.println("Enter the amount you want to deposit");
    	int amount = this.handleOptionInput();
    	if(amount < 0) {
    		System.out.println("amount can't be negative");
    		return;
    	}
    	this.currentUser.getCheckingAccount().deposit(amount);
    	this.checkingStatement.add(currentUser, amount);
    }
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

	private void transferFunds() {
		System.out.println("Enter the amount you want to transfer: ");
		int amount = this.handleOptionInput();
		if(amount < 0) {
			System.out.println("Amount can't be negative");
			return;
		}
		if(this.currentUser.getCheckingAccount().getCurrentBalance() < amount) {
			System.out.println("You don't have enough available funds");
			return;
		}
		System.out.println("Enter the username of the recipient: ");
		String recipientUsername = this.handleUserInput();
		if(!this.userRepository.exist(recipientUsername)) {
			System.out.println("Recipient does not exist");
			return;
		}
		if (amount > 500) {
			if (!largeSumWithdraw()){
				System.out.println("Transfer cancelled");
				return;
			}

		//TODO: Verify customer email, phone, user
		System.out.println("Are you sure you want to transfer $" + amount + 
			" to " + this.userRepository.get(recipientUsername).getEmail() + "? (y/n)");
		String confirm = this.handleUserInput();
		if(!confirm.equalsIgnoreCase("y")) {
			System.out.println("Transfer canceled");
			return;

		}
		this.currentUser.getCheckingAccount().withdraw(amount);
		this.checkingStatement.add(currentUser, -amount);
		this.userRepository.get(recipientUsername).getCheckingAccount().deposit(amount);
		this.checkingStatement.add(this.userRepository.get(recipientUsername), amount);
		System.out.println("Transfer successful");
		}
	}

    public void startPanel() {
    	System.out.println("Welcome to our bank");
    	System.out.println("1. Login");
    	System.out.println("2. Register");
    	System.out.println("3. Exit");
    }
    public void userPanel() {
    	System.out.println("Welcome "+this.currentUser.getUsername());
    	this.viewBalance();
    	System.out.println("1. view info");
    	System.out.println("2. change password");
    	System.out.println("3. edit info");
    	System.out.println("4. delete account");
    	System.out.println("5. deposit");
    	System.out.println("6. withdraw");
    	System.out.println("7. open a credit account");
    	System.out.println("8. open a saving account");
    	System.out.println("9. view credit");
    	System.out.println("10. view saving");
		System.out.println("11. transfer funds"); //altered
		System.out.println("12. view statement");
		System.out.println("13. view credit account statement"); //altered
		System.out.println("14. view savings account statement");
    	System.out.println("15. logout"); //altered
    }
    public void creditPanel() {
    	System.out.println("1. borrow");
    	System.out.println("2. pay");
    	System.out.println("3. exit");
    }
    
    public void savingPanel() {
    	System.out.println("1. withdraw");
    	System.out.println("2. deposit");
    	System.out.println("3. exit");
    }
    public static void main(String[] args) {
        Menu bankMenu = new Menu();
        bankMenu.run();
    }
}