package bankapp;

import java.util.Scanner;

public class Menu {
    private Scanner keyboardInput;
    private Customer currentCustomer;

    public Menu() {
        keyboardInput = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Menu bankMenu = new Menu();
        bankMenu.runMainMenu();
    }

    private void runMainMenu() {
        boolean exit = false;
        int selectionScreen = 0;

        while(!exit) {
            switch(selectionScreen) {
                case 0: // Main Menu
                    printOptions1();
                    int option = getUserInput();
                    if (option == 1) {
                        exit = true;
                    }
                    chooseOption1(option);
                    selectionScreen = option;
                    break;

                case 1: // Exit
                    exit = true;
                    break;

                case 2: // Sign in (placeholder)
                    System.out.println("Sign in functionality coming soon");
                    selectionScreen = 0;
                    break;

                case 3: // Account Operations
                    if(currentCustomer != null) {
                        runAccountMenu();
                    }
                    selectionScreen = 0;
                    break;
            }
        }
        keyboardInput.close();
    }

    private void runAccountMenu() {
        boolean back = false;

        while(!back) {
            printOptions2();
            int option = getUserInput();

            switch(option) {
                case 1: // Exit
                    back = true;
                    break;

                case 2: // Checking Account
                    runCheckingMenu();
                    break;

                case 3: // Savings Account
                    runSavingsMenu();
                    break;

                case 4: // Credit Account
                    runCreditMenu();
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void runSavingsMenu() {
        boolean back = false;

        while(!back) {
            System.out.println("\n--- Savings Account ---");
            System.out.println("1. Open Savings Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer to Checking");
            System.out.println("5. Transfer from Checking");
            System.out.println("6. Check Balance");
            System.out.println("7. Calculate Interest");
            System.out.println("8. Back");

            try {
                int option = getUserInput();

                switch(option) {
                    case 1: // Open Savings
                        System.out.print("Enter initial deposit: $");
                        double initialDeposit = keyboardInput.nextDouble();
                        currentCustomer.openSavingsAccount(initialDeposit);
                        System.out.println("Savings account opened!");
                        break;

                    case 2: // Deposit
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = keyboardInput.nextDouble();
                        currentCustomer.getSavingsAccount().deposit(depositAmount);
                        System.out.println("Deposit successful!");
                        break;

                    case 3: // Withdraw
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = keyboardInput.nextDouble();
                        if(currentCustomer.getSavingsAccount().withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Insufficient funds!");
                        }
                        break;

                    case 4: // Transfer to Checking
                        System.out.print("Enter transfer amount: $");
                        double toChecking = keyboardInput.nextDouble();
                        currentCustomer.transferFromSavings(toChecking);
                        System.out.println("Transfer complete!");
                        break;

                    case 5: // Transfer from Checking
                        System.out.print("Enter transfer amount: $");
                        double fromChecking = keyboardInput.nextDouble();
                        currentCustomer.transferToSavings(fromChecking);
                        System.out.println("Transfer complete!");
                        break;

                    case 6: // Check Balance
                        System.out.println("Balance: $" +
                            currentCustomer.getSavingsAccount().getBalance());
                        break;

                    case 7: // Calculate Interest
                        currentCustomer.getSavingsAccount().calculateInterest();
                        System.out.println("Interest applied. New balance: $" +
                            currentCustomer.getSavingsAccount().getBalance());
                        break;

                    case 8: // Back
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid option");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Keep all the existing utility methods from development branch:
    public void printOptions1() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Exit");
        System.out.println("2. Sign into Bank Account");
        System.out.println("3. Create Bank Account");
    }

    public void printOptions2() {
        System.out.println("\n--- Account Menu ---");
        System.out.println("1. Exit");
        System.out.println("2. Checking Account");
        System.out.println("3. Savings Account");
        System.out.println("4. Credit Account");
    }

    public void chooseOption1(int option) {
        switch(option) {
            case 1:
                System.out.println("Exiting...");
                break;
            case 2:
                System.out.println("Sign in selected");
                break;
            case 3:
                currentCustomer = createCustomer();
                System.out.println("Account created successfully!");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    public int getUserInput() {
        System.out.print("Enter your choice: ");
        return keyboardInput.nextInt();
    }

    public Customer createCustomer() {
        keyboardInput.nextLine(); // Clear buffer
        System.out.println("\n--- Create Account ---");

        System.out.print("First Name: ");
        String firstName = keyboardInput.nextLine();

        System.out.print("Middle Name (optional, press Enter to skip): ");
        String middleName = keyboardInput.nextLine();

        System.out.print("Last Name: ");
        String lastName = keyboardInput.nextLine();

        System.out.print("Email: ");
        String email = keyboardInput.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = keyboardInput.nextLine();

        System.out.print("Address: ");
        String address = keyboardInput.nextLine();

        if(middleName.isEmpty()) {
            return new Customer(firstName, lastName, email, phoneNumber, address);
        } else {
            return new Customer(firstName, middleName, lastName, email, phoneNumber, address);
        }
    }

    // Placeholder methods for other menus
    private void runCheckingMenu() {
        System.out.println("\nChecking account menu coming soon");
    }

    private void runCreditMenu() {
        System.out.println("\nCredit account menu coming soon");
    }
}