package bankapp;

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
    private static final int LOGOUT = 11;
    private static final int OPEN_CREDIT = 7;
    private static final int OPEN_SAVING = 8;
    private static final int VIEW_CREDIT= 9;
    private static final int VIEW_SAVING = 10;
    private static final int CREDIT_BORROW = 1;
    private static final int CREDIT_PAY = 2;
    private static final int CREDIT_EXIT = 3;
    private Scanner keyboardInput;
    private Customer currentUser;
    private CustomerBase userRepository;
    private boolean isExit = false;
    private boolean isCredit = false;
    public Menu() {
        keyboardInput = new Scanner(System.in);
        this.userRepository = new CustomerBase();
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
    			}else {
    				this.handleUser();
    			}
    		}
    		System.out.println();
    	}
    }
    public void login() {
    	System.out.println("input your username or input q to quit");
    	String input = this.handleUserInput();
    	if(input.equals("q")) {
    		return;
    	}
    	String username = input;
    	System.out.println("input your password");
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
    	this.creditPanel();
    	int option = this.handleOptionInput();
    	switch(option) {
    		case CREDIT_BORROW:
    			this.borrow();
    			break;
    		case CREDIT_PAY:
    			this.pay();
    			break;
    		case CREDIT_EXIT:
    			this.isCredit = false;
    			break;
    		default:
    			System.out.println("invalid option");
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
    		System.out.println("unreconginize option, try again");
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
    			this.openCredit();
    			break;
    		case OPEN_SAVING:
    			this.openSaving();
    			break;
    		case VIEW_CREDIT:
    			this.viewCredit();
    			break;
    		default:
    			System.out.println("Invalid Option");
    	}
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
    		System.out.println("you can't pay exceed the balance");
    		return;
    	}
    	this.currentUser.getCreditAccount().repayCredit(amount);
    	System.out.println("pay success");
    }
    private void viewCredit() {
    	if(this.currentUser.getCreditAccount()== null) {
    		System.out.println("You dont have credit account");
    		return;
    	}
    	this.isCredit = true;
    }
    private void openCredit() {
    	this.currentUser.openCreditAccount();
    	System.out.println("finsih oepn credit Account");
    }
    private void openSaving() {
    	System.out.println("Enter you initial deposit");
    	int amount = this.handleOptionInput();
    	this.currentUser.openSavingsAccount(amount);
    	System.out.println("finish oepn saving account");
    }
    private void withdraw() {
    	System.out.println("Enter the number you want to WITHDRAW");
    	int amount = this.handleOptionInput();
    	if(amount > this.currentUser.getCheckingAccount().getCurrentBalance()) {
    		System.out.println("the amount should not larger than you balance");
    		return;
    	}
    	this.currentUser.getCheckingAccount().withdraw(amount);
    }
    private void viewBalance() {
    	System.out.println("Your current Balance is "+this.currentUser.getCheckingAccount().getCurrentBalance());
    }
    private void deposit() {
    	System.out.println("Enter the amoutn you want to deposit");
    	int amount = this.handleOptionInput();
    	if(amount < 0) {
    		System.out.println("amount can't be negative");
    		return;
    	}
    	this.currentUser.getCheckingAccount().deposit(amount);
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
    	System.out.println("8. oepn a saving account");
    	System.out.println("9. view credit(workable but probably have some bug)");
    	System.out.println("10. view saving(not finish)");
    	System.out.println("11. logout");
    }
    public void creditPanel() {
    	System.out.println("1. borrow");
    	System.out.println("2. pay");
    	System.out.println("3. exit");
    }
    public static void main(String[] args) {
        Menu bankMenu = new Menu();
        bankMenu.run();
    }
}