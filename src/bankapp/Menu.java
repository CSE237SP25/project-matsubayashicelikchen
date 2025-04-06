package bankapp;

import java.util.Scanner;

public class Menu {
    // Constants for menu options
    private static final int OPTION_DEPOSIT = 1;
    private static final int OPTION_WITHDRAW = 2;
    private static final int OPTION_BORROW_CREDIT = 3;
    private static final int OPTION_REPAY_CREDIT = 4;
    private static final int OPTION_CHECK_BALANCE = 5;
    private static final int OPTION_CHECK_CREDIT = 6;
    private static final int OPTION_STATEMENT = 7;
    private static final int OPTION_EXIT = 8;
    private static final int MAX_OPTION = 8;
 
    private final Scanner scanner;
    private final BankAccount account;

    public Menu() {
        scanner = new Scanner(System.in);
        account = new BankAccount();
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            if (!processChoice(choice)) {
                break; // gracefully exit loop
            }
        }
    }

    // Display the menu
    private void displayMenu() {
        System.out.println("\nYour current balance is: $" + account.getCurrentBalance());
        System.out.println("Your credit balance is: $" + account.getCreditBalance());
        System.out.println("Select an option:");
        System.out.println(OPTION_DEPOSIT + ". Deposit");
        System.out.println(OPTION_WITHDRAW+". Withdraw");
        System.out.println(OPTION_BORROW_CREDIT + ". Borrow Credit");
        System.out.println(OPTION_REPAY_CREDIT + ". Repay Credit");
        System.out.println(OPTION_CHECK_BALANCE + ". Check Balance");
        System.out.println(OPTION_CHECK_CREDIT + ". Check Credit Balance");
        System.out.println(OPTION_STATEMENT+". Display statement");
        System.out.println(OPTION_EXIT + ". Exit");
    }

    // Get user menu selection
    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next(); // clear invalid input
        }
        int input = scanner.nextInt();
        if (input < 1 || input > MAX_OPTION) {
            System.out.println("Invalid choice. Please select a number from 1 to " + MAX_OPTION + ".");
            return getUserChoice(); // prompt again
        }
        return input;
    }

    // Process menu selection, return false if user wants to exit
    private boolean processChoice(int choice) {
        switch (choice) {
            case OPTION_DEPOSIT:
                handleDeposit();
                break;
            case OPTION_WITHDRAW:
            	this.handleWithdraw();
            	break;
            case OPTION_BORROW_CREDIT:
                handleBorrow();
                break;
            case OPTION_REPAY_CREDIT:
                handleRepay();
                break;
            case OPTION_CHECK_BALANCE:
                System.out.println("Current balance: $" + account.getCurrentBalance());
                break;
            case OPTION_CHECK_CREDIT:
                System.out.println("Credit balance: $" + account.getCreditBalance());
                break;
            case OPTION_STATEMENT:
            	this.handleFinancialStatement();
            	break;
            case OPTION_EXIT:
                System.out.println("Exiting.");
                scanner.close();
                return false; // exit the loop
            default:
                System.out.println("Invalid option.");
        }
        return true;
    }

    // Handle deposit logic
    private void handleDeposit() {
        System.out.print("Enter deposit amount: ");
        double amount = getPositiveDouble();
        try {
            account.deposit(amount);
            System.out.println("Deposited successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void handleWithdraw() {
    	System.out.print("Enter withdraw amount: ");
        double amount = getPositiveDouble();
        try {
            account.withdraw(amount);
            System.out.println("withdraw successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Handle borrowing logic
    private void handleBorrow() {
        System.out.print("Enter amount to borrow: ");
        double amount = getPositiveDouble();
        try {
            account.borrowCredit(amount);
            System.out.println("Credit borrowed successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Handle credit re-payment
    private void handleRepay() {
        System.out.print("Enter amount to repay: ");
        double amount = getPositiveDouble();
        try {
            account.repayCredit(amount);
            System.out.println("Credit repaid successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Ensure user enters a positive number
    private double getPositiveDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.nextLine();
        }
        double value = scanner.nextDouble();
        if (value <= 0) {
            System.out.println("Amount must be positive.");
            return getPositiveDouble();
        }
        return value;
    }
    private void handleFinancialStatement() {
    	System.out.println(this.account.getStatement());
    	System.out.println("Enter any key to continue");
    	this.scanner.next();
    }
    // Entry point
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}
