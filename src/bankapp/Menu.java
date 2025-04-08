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
        
        while(!exit) {
            printMainOptions();
            int option = getUserInput();
            
            switch(option) {
                case 1:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    currentCustomer = createCustomer();
                    System.out.println("Account created! Your Customer ID is: " + currentCustomer.getCustomerID());
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        keyboardInput.close();
    }

    private void printMainOptions() {
        System.out.println("\n--- Welcome to the Bank ---");
        System.out.println("1. Exit");
        System.out.println("2. Sign into Bank Account");
        System.out.println("3. Create Bank Account");
    }

    public void signIn() {
        System.out.print("Enter Customer ID: ");
        int id = keyboardInput.nextInt();

        System.out.print("Enter Password: ");
        String password = keyboardInput.next();

        // Authentication logic would go here
        currentCustomer = new Customer("John", "Doe", "john@example.com", "1234567890", "123 Main St", "password");
        
        if(currentCustomer.authenticate(password)) {
            System.out.println("Welcome, " + currentCustomer.getFirstName() + "!");
            runAccountMenu();
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void runAccountMenu() {
        boolean back = false;
        
        while(!back && currentCustomer != null) {
            printAccountOptions();
            int option = getUserInput();

            switch(option) {
                case 1:
                    back = true;
                    break;
                case 2:
                    runCheckingMenu();
                    break;
                case 3:
                    runSavingsMenu();
                    break;
                case 4:
                    runCreditMenu();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printAccountOptions() {
        System.out.println("\n--- Account Menu ---");
        System.out.println("1. Log Out");
        System.out.println("2. Checking Account");
        System.out.println("3. Savings Account");
        System.out.println("4. Credit Account");
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
                    case 1:
                        if (currentCustomer.getSavingsAccount() != null) {
                            System.out.println("You already have a savings account!");
                            break;
                        }
                        System.out.print("Enter initial deposit: $");
                        double initialDeposit = keyboardInput.nextDouble();
                        currentCustomer.openSavingsAccount(initialDeposit);
                        System.out.println("Savings account opened!");
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = keyboardInput.nextDouble();
                        currentCustomer.getSavingsAccount().deposit(depositAmount);
                        System.out.println("Deposit successful!");
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = keyboardInput.nextDouble();
                        if (currentCustomer.getSavingsAccount().withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Insufficient funds!");
                        }
                        break;
                    case 4:
                        System.out.print("Enter transfer amount to checking: $");
                        double toChecking = keyboardInput.nextDouble();
                        currentCustomer.transferFromSavings(toChecking);
                        System.out.println("Transfer complete!");
                        break;
                    case 5:
                        System.out.print("Enter transfer amount from checking: $");
                        double fromChecking = keyboardInput.nextDouble();
                        currentCustomer.transferToSavings(fromChecking);
                        System.out.println("Transfer complete!");
                        break;
                    case 6:
                        System.out.println("Balance: $" + currentCustomer.getSavingsAccount().getBalance());
                        break;
                    case 7:
                    	currentCustomer.getSavingsAccount().applyInterest();
                        System.out.println("Interest applied. New balance: $" + 
                            currentCustomer.getSavingsAccount().getBalance());
                        break;
                    case 8:
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

    private void runCheckingMenu() {
        boolean back = false;
        
        while(!back) {
            System.out.println("\n--- Checking Account ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transfer to Savings");
            System.out.println("5. Back");
            
            try {
                int option = getUserInput();
                
                switch(option) {
                    case 1:
                        System.out.print("Enter deposit amount: $");
                        double deposit = keyboardInput.nextDouble();
                        currentCustomer.getAccount().deposit(deposit);
                        System.out.println("Deposit successful!");
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: $");
                        double withdraw = keyboardInput.nextDouble();
                        if(currentCustomer.getAccount().withdraw(withdraw)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Insufficient funds!");
                        }
                        break;
                    case 3:
                        System.out.println("Balance: $" + currentCustomer.getAccount().getCurrentBalance());
                        break;
                    case 4:
                        System.out.print("Enter transfer amount to savings: $");
                        double toSavings = keyboardInput.nextDouble();
                        currentCustomer.transferToSavings(toSavings);
                        System.out.println("Transfer complete!");
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
                keyboardInput.nextLine();
            }
        }
    }

    private void runCreditMenu() {
        boolean back = false;
        
        while(!back) {
            System.out.println("\n--- Credit Account ---");
            System.out.println("1. Open Credit Account");
            System.out.println("2. Borrow Credit");
            System.out.println("3. Repay Credit");
            System.out.println("4. Check Credit Balance");
            System.out.println("5. Back");
            
            try {
                int option = getUserInput();
                
                switch(option) {
                    case 1:
                        if (currentCustomer.getCreditAccount() != null) {
                            System.out.println("You already have a credit account!");
                            break;
                        }
                        currentCustomer.openCreditAccount();
                        System.out.println("Credit account opened!");
                        break;
                    case 2:
                        if (currentCustomer.getCreditAccount() == null) {
                            System.out.println("Please open a credit account first!");
                            break;
                        }
                        System.out.print("Enter amount to borrow: $");
                        double borrow = keyboardInput.nextDouble();
                        currentCustomer.getCreditAccount().borrowCredit(borrow);
                        System.out.println("Credit borrowed!");
                        break;
                    case 3:
                        if (currentCustomer.getCreditAccount() == null) {
                            System.out.println("Please open a credit account first!");
                            break;
                        }
                        System.out.print("Enter amount to repay: $");
                        double repay = keyboardInput.nextDouble();
                        currentCustomer.getCreditAccount().repayCredit(repay);
                        System.out.println("Payment applied!");
                        break;
                    case 4:
                        if (currentCustomer.getCreditAccount() == null) {
                            System.out.println("No credit account exists!");
                            break;
                        }
                        System.out.println("Credit balance: $" + 
                            currentCustomer.getCreditAccount().getCreditBalance());
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
                keyboardInput.nextLine();
            }
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
        
        System.out.print("Password: ");
        String password = keyboardInput.nextLine();

        if(middleName.isEmpty()) {
            return new Customer(firstName, lastName, email, phoneNumber, address, password);
        } else {
            return new Customer(firstName, middleName, lastName, email, phoneNumber, address, password);
        }
    }
}